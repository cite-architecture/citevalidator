package edu.holycross.shot.citevalidator
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._

import org.scalatest.FlatSpec

//import wvlet.log._

class DseVerificationSpec extends FlatSpec {


  val lib1 = CiteLibrarySource.fromFile("jvm/src/test/resources/csample.cex")

  "A DseValidator" should "compose a markdown string for visual verification using the ICT tool" in {
    val pg = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
    val pageList = Vector(pg)
    val dseValidator = DseValidator(lib1)

    val expected = "## Verification: 21\n\nTo verify that coverage of DSE indexing is complete, use [this link](http://www.homermultitext.org/ict2?urn=urn:cite2:citeecod:einsiedeln121imgs.v1:sbe_0121_21@0.4224,0.6753,0.2902,0.06050&urn=urn:cite2:citeecod:einsiedeln121imgs.v1:sbe_0121_21@0.05747,0.6569,0.6769,0.2052)\n"

    val actual = dseValidator.verify(pg)

    assert( actual == expected)
  }


}
