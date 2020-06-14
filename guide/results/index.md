---
title: Using results of validation and verification
layout: page
---


## Test results

This [walkthrough example](../dse/) used a `DseValidator` to validate one page of a manuscript.


```scala mdoc:silent
import edu.holycross.shot.citevalidator._
import edu.holycross.shot.scm._
val lib = CiteLibrarySource.fromFile("jvm/src/test/resources/csample.cex")

val dseValidator = DseValidator(lib)
val validators = Vector(dseValidator)

import edu.holycross.shot.cite._
val pg = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
val rslts = LibraryValidator.validate(pg,validators)
```

The output of the validation was a Vector of `TestResult`s.  `TestResult`s have a Boolean `success` member so you can easily distinguish success and failures.



```scala mdoc:silent
val good = rslts.filter(_.success)
val bad = rslts.filterNot(_.success)
```

The `DseValidator` has a `markdownResults` method you can use to format a summary of the results in markdown.

```scala mdoc:silent
println("**Successes**: " + good.size + "\n")
println(dseValidator.markdownResults(good))

println("\n**Failures**: " + bad.size + "\n")
println(dseValidator.markdownResults(bad))
```
```scala mdoc:passthrough
println(">**Successes**: " + good.size + "\n")
println(dseValidator.markdownResults(good))

println("\n>**Failures**: " + bad.size + "\n")
println(dseValidator.markdownResults(bad))
```

## `TestResultGroup`

You can also make a `TestResultGroup` from a Vector of `TestResult`.  This offers a few short-cut methods for viewing or printing summaries of results.  See the API docs for details.


## Verification

The `DseValidator` can also write a Markdown-formatted text for human verification of the results.

```scala mdoc:silent
dseValidator.verify(pg)
```
```scala mdoc:passthrough
println(">" + dseValidator.verify(pg))
```
