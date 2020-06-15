package edu.holycross.shot.citevalidator
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._

import org.scalatest.FlatSpec


//import wvlet.log._

class DseReportingSpec extends FlatSpec {


  val lib1 = CiteLibrarySource.fromFile("jvm/src/test/resources/csample.cex")
  val dseValidator = DseValidator(lib1)

  "A DseValidator" should "create nice reporting output" in  {
    val pg = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
    val pageList = Vector(pg)
    val rslts = dseValidator.validate(pageList)


    //println("Validated " + pageList.size + " surfaces, and got " + rslts.size + " results.")
    //println(rslts)
    // and summary should link to an ICT
    //println("WITH SUMMARIES:\n" + rslts.map(_.summary).mkString("\n"))
    //println(rslts.head.summary)
  }

  it should "format a list of DSE validation results as markdown" in {
    val pg = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
    val pageList = Vector(pg)
    val rslts = dseValidator.validate(pageList)
    println("MD:\n" + dseValidator.markdownResults(rslts))

    import java.io.PrintWriter
    new PrintWriter("dse-out1.md"){write(dseValidator.markdownResults(rslts));close}
  }

  it should "feed nicely into a TestResultGroup" in {
    val pg = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
    val pageList = Vector(pg)
    val rslts = dseValidator.validate(pageList)
    val tgr = TestResultGroup(s"Results for ${pg.objectComponent}", rslts)

    import java.io.PrintWriter
    new PrintWriter("trg-out1.md"){write(tgr.markdown);close}
  }

}
