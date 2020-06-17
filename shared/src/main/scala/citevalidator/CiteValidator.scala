package edu.holycross.shot.citevalidator

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import edu.holycross.shot.citerelation._
import edu.holycross.shot.citeobj._
import edu.holycross.shot.scm._


import wvlet.log._
//import wvlet.log.LogFormatter.SourceCodeLogFormatter

import scala.scalajs.js.annotation._

trait CiteValidator[+T]  extends LogSupport {

  // 4 type-parameterized methods CiteValidator classes must implement
  // 1.
  /** Label or title for this validator.*/
  def label: String


  // 2.
  /** Validate all contents of a CiteLibrary for type T.*/
  def validate(library: CiteLibrary) : Vector[TestResult[T]]

  // 3.
  /** Validate all contents of a CiteLibrary related to a physical surface for type T.*/
  def validate(surface: Cite2Urn) : Vector[TestResult[T]]


  // 4.
  /** Markdown source for human verification.*/
  def verify(surface: Cite2Urn) : String

  /** Validate all contents of a CiteLibrary for type T
  * related to a Vector of surfaces.
  */
  def validate(surfaces: Vector[Cite2Urn]) : Vector[TestResult[T]]  = {
    surfaces.flatMap(validate(_))
  }

  /** Concatenate result summaries as a Markdown list.
  */
  def markdownResults[T](results: Vector[TestResult[T]]) : String = {
    val markdownList = results.map(r => "- " + r.summary)
    markdownList.mkString("\n")
  }

}
