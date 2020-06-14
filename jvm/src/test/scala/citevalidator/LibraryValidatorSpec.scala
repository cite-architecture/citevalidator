package edu.holycross.shot.citevalidator
import edu.holycross.shot.ohco2._
import edu.holycross.shot.cite._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._

import org.scalatest.FlatSpec


class LibraryValidatorSpec extends FlatSpec {

  val lib = CiteLibrarySource.fromFile("jvm/src/test/resources/csample.cex")
  "A LibraryValidator" should "apply a list of validators of any type" in {
    val dsev = DseValidator(lib)
    val validatorList = Vector(dsev)
    println("DSE Vector for lib: " + DseVector.fromCiteLibrary(lib).size)
    val rslts = LibraryValidator.validate(lib, validatorList)
    println("Library validator: " + rslts.size + " tests run.")
  }


}
