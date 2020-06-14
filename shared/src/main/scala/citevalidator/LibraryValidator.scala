package edu.holycross.shot.citevalidator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.dse._
import edu.holycross.shot.scm._


import wvlet.log._
//import wvlet.log.LogFormatter.SourceCodeLogFormatter

import scala.scalajs.js.annotation._

@JSExportAll object LibraryValidator  extends LogSupport {
  //Logger.setDefaultLogLevel(LogLevel.INFO)


/*
  def validate(surfaces: Vector[Cite2Urn],  validators: Vector[CiteValidator[Any]]) : Vector[TestResult[Any]] = {
    val testGroupsByPage = for (surface <- surfaces) yield {
      validate(surface, validators)
    }
    testGroupsByPage.flatten
  }
  */

  def validate(pg: Cite2Urn, validators: Vector[CiteValidator[Any]] ) : Vector[TestResult[Any]] = {
    info("Applying " + validators.size + s" MID Validators to page ${pg} ")
    val testGroups = for (validator <- validators) yield {
      debug("Use " + validator.label)
      validator.validate(pg)
    }
    val allTests = testGroups.flatten
    //debug("Total tests: " + allTests.size)
    allTests
  }




  /** Collect all validation results for a text-bearing using
  * by apply all [[CiteValidator]]s in a given list.
  *
  * @param surface Text-bearing surface to test.
  * @param validators [[CiteValidator]]s to apply.
  */
  def validate(lib: CiteLibrary, validators: Vector[CiteValidator[Any]]) : Vector[TestResult[Any]] = {

    info("Applying " + validators.size + " MID Validators to library " + lib.name)
    val testGroups = for (validator <- validators) yield {
      debug("Use " + validator.label)
      validator.validate(lib)
    }
    val allTests = testGroups.flatten
    //debug("Total tests: " + allTests.size)
    allTests
  }
}
