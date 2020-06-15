---
title: Using results of validation and verification
layout: page
---


## Test results

This [walkthrough example](../dse/) used a `DseValidator` to validate one page of a manuscript.


```scala
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



```scala
val good = rslts.filter(_.success)
val bad = rslts.filterNot(_.success)
```

The `DseValidator` has a `markdownResults` method you can use to format a summary of the results in markdown.

```scala
println("**Successes**: " + good.size + "\n")
println(dseValidator.markdownResults(good))

println("\n**Failures**: " + bad.size + "\n")
println(dseValidator.markdownResults(bad))
```
>**Successes**: 1

- Compare text urn:cts:chant:massordinary.eins121.text_xml:h007_2.h00.1 to image [![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/citeecod/einsiedeln121imgs/v1/sbe_0121_21.tif/pct:42.24,67.53,29.02,6.05/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:citeecod:einsiedeln121imgs.v1:sbe_0121_21@0.4224,0.6753,0.2902,0.06050)Text passage urn:cts:chant:massordinary.eins121.text_xml:h007_2.h00.1 found in corpus. 

>**Failures**: 1

- Compare text urn:cts:chant:massordinary.eins121.text_xml:h007_2.h02.1@Mem-h02.1@in to image [![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/citeecod/einsiedeln121imgs/v1/sbe_0121_21.tif/pct:5.747,65.69,67.69,20.52/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:citeecod:einsiedeln121imgs.v1:sbe_0121_21@0.05747,0.6569,0.6769,0.2052)Indexed passage urn:cts:chant:massordinary.eins121.text_xml:h007_2.h02.1@Mem-h02.1@in **NOT FOUND** in text corpus.

## `TestResultGroup`

You can also make a `TestResultGroup` from a Vector of `TestResult`.  This offers a few short-cut methods for viewing or printing summaries of results.  See the API docs for details.


## Verification

The `DseValidator` can also write a Markdown-formatted text for human verification of the results.

```scala
dseValidator.verify(pg)
```
>## Verification: 21

**1** valid / 2 passages

## Coverage

To verify that coverage of DSE indexing is complete, use [this link](http://www.homermultitext.org/ict2?urn=urn:cite2:citeecod:einsiedeln121imgs.v1:sbe_0121_21@0.4224,0.6753,0.2902,0.06050&urn=urn:cite2:citeecod:einsiedeln121imgs.v1:sbe_0121_21@0.05747,0.6569,0.6769,0.2052)

## Accuracy of valid passages

[![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/citeecod/einsiedeln121imgs/v1/sbe_0121_21.tif/pct:42.24,67.53,29.02,6.05/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:citeecod:einsiedeln121imgs.v1:sbe_0121_21@0.4224,0.6753,0.2902,0.06050)
<ab n="1" xmlns="http://www.tei-c.org/ns/1.0">DOM PRXIMA</ab>
