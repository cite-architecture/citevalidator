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
  /** Label or title for this validator.*/
  def label: String

  def validate(library: CiteLibrary) : Vector[TestResult[T]]

  def validate(surface: Cite2Urn) : Vector[TestResult[T]]

  def validate(surfaces: Vector[Cite2Urn]) : Vector[TestResult[T]]  = {
    surfaces.flatMap(validate(_))
  }

  /** Concatenate result summaries as a Markdown list.
  */
  def markdownResults[T](results: Vector[TestResult[T]]) : String = {
    val markdownList = results.map(r => "- " + r.summary)
    markdownList.mkString("\n")
  }

  /** Markdown source for human verification.*/
  def verify(surface: Cite2Urn) : String

}
