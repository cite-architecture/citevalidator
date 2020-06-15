package edu.holycross.shot.citevalidator

import scala.scalajs.js.annotation._
import edu.holycross.shot.cite._

import java.io.PrintWriter

/** Results of testing a single artifact.
*
* @param success True if tested object passed the test.
* @param summary Human-readable summary of the test.
*/
@JSExportAll case class TestResultGroup(title: String, results: Vector[TestResult[Any]]) {

  /** All successful tests.*/
  def successes : Vector[TestResult[Any]] = {
    results.filter(_.success)
  }

  /** All failed tests.*/
  def failures : Vector[TestResult[Any]] = {
    results.filterNot(_.success)
  }

  /** Summary of test results.*/
  def summary: String = {
    "## Summary of results\n\n" +
    s"Successes/total tests: **${successes.size}/${results.size}**."
  }

  /** Format markdown string to report on test results.*/
  def markdown : String = {
    s"#${title}\n\n" + summary + s"\n\n## Details for ${failures.size} failed tests\n\n" + failures.map(rslt => "- " + rslt.summary).mkString("\n") + "\n\n"
  }

  /** Write report to a local file.
  * @param fileName Name of file for output.
  */
  def write(fileName: String) : Unit = {
    new PrintWriter(fileName) { write(markdown); close; }
  }
}
