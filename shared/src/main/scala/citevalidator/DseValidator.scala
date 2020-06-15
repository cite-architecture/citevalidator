package edu.holycross.shot.citevalidator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._
import edu.holycross.shot.citebinaryimage._
//import scala.reflect.runtime.universe._


import wvlet.log._
//import wvlet.log.LogFormatter.SourceCodeLogFormatter


import scala.scalajs.js.annotation._

@JSExportAll  case class DseValidator(
    citeLibrary: CiteLibrary,
    baseUrl : String  = "http://www.homermultitext.org/iipsrv?",
    basePath: String = "/project/homer/pyramidal/deepzoom/",
    ictUrl: String = "http://www.homermultitext.org/ict2?"
  ) extends CiteValidator[DsePassage] with LogSupport {
  //Logger.setDefaultLogLevel(LogLevel.INFO)

  def label : String = "Validate DsePassage relations"

  lazy val corpus = citeLibrary.textRepository.get.corpus
  lazy val collections = citeLibrary.collectionRepository.get

  /** Library must implement the DSE model in at least one collection.*/
  lazy val dsev = DseVector.fromCiteLibrary(citeLibrary)

  /** Get ordered list of all text-bearing surfaces in the library.
  */
  def tbs: Vector[Cite2Urn] = {
    val tbsUrn = Cite2Urn("urn:cite2:cite:datamodels.v1:tbsmodel")

    val tbsCollections: Vector[Cite2Urn] = citeLibrary.collectionsForModel(tbsUrn)
    tbsCollections.flatMap (c =>
      (collections ~~ c).map(_.urn)
    )
  }

  /** Validate DSE relations page-by-page for an entire library.
  *
  * @param citeLibrary Library to validate.
  */
  def validate(citeLibrary: CiteLibrary) : Vector[TestResult[DsePassage]]  = {
    tbs.flatMap(validate(_))
  }

  /** Validate DSE content on a single text-bearing surface.
  * Required method for implementation of CiteValidator trait.
  *
  * @param surface Validate DSE content on this text-bearing surface.
  */
  def validate(surface: Cite2Urn) : Vector[TestResult[DsePassage]] = {
    info("DSE validate for " + surface + " : start computing DSE...")
    val surfaceDse = dsev.passages.filter(_.surface == surface)

    info("Done. DSE validating " + surfaceDse.size + " DSE passages.")

    val ok = filterDsePassages(surfaceDse, true).
      map(psg =>
        TestResult(true,  psg.markdown(baseUrl, basePath) + "Text passage " + psg.passage + " found in corpus. " , psg)
      )
    val bad = filterDsePassages(surfaceDse, false).
      map(psg =>
        TestResult(false, psg.markdown(baseUrl, basePath) + "Indexed passage " + psg.passage + " **NOT FOUND** in text corpus."  , psg)
      )

    ok ++ bad
  }

  /** Filter a Vector of DsePassages, keeping or rejecting
  * passage based on setting of Boolean valid.
  *
  * @param dsePassages List of DsePassages to filter.
  * @param valid If true, keep valid passages.
  */
  def filterDsePassages(dsePassages : Vector[DsePassage], valid: Boolean) : Vector[DsePassage] = {
    val opts = for ((dsePsg, count) <- dsePassages.zipWithIndex) yield {
      info(s"\t${count + 1}/${dsePassages.size} ${dsePsg.passage}")
      debug("Validating DSE passage " + dsePsg)
      val matches = corpus ~~ dsePsg.passage
      debug(s"Text ${dsePsg.passage} on " + dsePsg.surface + ": " + matches.size + " in corpus")

      val checked = matches.size match {
        case 0 => {
          valid match {
            case true => None
            case false => Some(dsePsg)
          }
        }
        case  _ =>  {
          valid match {
            case true => Some(dsePsg)
            case false => None
          }
        }
      }
      checked
    }
    opts.flatten
  }

  /** Compose markdown section on coverage for
  * required "verify" method.
  */
  def coverage(psgs: Vector[DsePassage]): String = {
    val images = psgs.map(dse => "urn=" + dse.imageroi)
    val linkUrl = ictUrl + images.mkString("&")
    s"## Coverage\n\nTo verify that coverage of DSE indexing is complete, use [this link](${linkUrl})\n\n"

  }

  /** Compose markdown section on accuracy for
  * required "verify" method.
  */
  def accuracy(psgs: Vector[DsePassage]): String = {
    val rows = for (psg <- psgs) yield {
      val imgPath = PathUtility.expandedPath(psg.imageroi.dropSelector)
      val fullPath = basePath + imgPath
      val iiif = IIIFApi(baseUrl,fullPath)
      val linkedImg = iiif.linkedMarkdownImage(psg.imageroi)
      linkedImg + "\n" + textContents(psg)
    }
    s"## Accuracy of valid passages\n\n" + rows.mkString("\n\n---\n\n")
  }



  def textContents(psg: DsePassage): String = {
    val subcorpus = citeLibrary.textRepository.get.corpus ~~ psg.passage.dropSubref
    subcorpus.nodes.map(_.text).mkString(" ")
  }

  /** Composes markdown string for visual verification of a surface.
  *
  * @param surface Surface to verify.
  */
  def verify(surface: Cite2Urn) : String = {
    val surfaceDse = dsev.passages.filter(_.surface == surface)

    val goodPassages = filterDsePassages(surfaceDse, true)
    val summary = s"**${goodPassages.size}** valid / ${surfaceDse.size} passages\n\n"
    val header = s"## Verification: ${surface.objectComponent}\n\n"
    header + summary + coverage(surfaceDse) + accuracy(goodPassages)
  }


}
