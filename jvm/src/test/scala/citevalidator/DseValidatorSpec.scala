package edu.holycross.shot.citevalidator
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._

import org.scalatest.FlatSpec


//import wvlet.log._

class DseValidatorSpec extends FlatSpec {


  val lib1 = CiteLibrarySource.fromFile("jvm/src/test/resources/csample.cex")

  "A DseValidator" should "find a list of text-bearing surfaces in a library" in  {
    val dseValidator = DseValidator(lib1)
    val surfaces = dseValidator.tbs
    val expectedSurfaces = Vector(Cite2Urn("urn:cite2:ecod:eins121pages.v1:21"))

    assert(expectedSurfaces == surfaces)
  }

  it should "validate surfaces supplied in a Vector" in {
    val pg = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
    val pageList = Vector(pg)
    val dseValidator = DseValidator(lib1)
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
    val dseValidator = DseValidator(lib1)
    val rslts = dseValidator.validate(pageList)
    println("MD:\n" + dseValidator.markdownResults(rslts))
  }

  it should "validate a range of surfaces" in pending /*{
    val pageList = Vector(
      Cite2Urn("urn:cite2:ecod:eins121pages.v1:21"),
      Cite2Urn("urn:cite2:hmt:msA.v1:78r")
    )
    val dseValidator = DseValidator(lib1)
    val rslts = dseValidator.validate(pageList)
    println("Validated " + pageList.size + " surfaces, and got " + rslts.size + " results.")
  }*/



    /* "do things" in pending

 {
    val dsev = DseValidator(lib)
    val surfaces = dsev.tbs

    val pg =   Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
    val rslts = dsev.validate(pg)
    println("Tests: " + rslts.size)
    println("Pass/fail: " + dsev.successes(pg).size + "/" + dsev.failures(pg).size)

  } */


  it should "identify entries missing from the text corpus" in pending
  /*
{
    val dsev = DseValidator(hmtLib)
    val pg =   Cite2Urn("urn:cite2:ecod:sg359pages.v1:36")
    val missing = dsev.failures(pg)
    println(missing.map(_.unit.passage))
  }
*/

it should "collect the correct number of records" in {
  val pg = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
  val dseValidator = DseValidator(lib1)
  val surfaces = dseValidator.tbs
}

}
