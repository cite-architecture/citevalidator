---
title: Walk through an example
layout: page
---

**Library version @VERSION@**



```scala mdoc
// Import the generic validator library:
import edu.holycross.shot.citevalidator._
```


Works from a library, so let's get a sample:

```scala mdoc:silent
import edu.holycross.shot.scm._

val lib = CiteLibrarySource.fromFile("jvm/src/test/resources/csample.cex")
```



For this example, we'll apply a single validator:

```scala mdoc
val dseValidator = DseValidator(lib)
val validators = Vector(dseValidator)
```

Now we can use the `LibraryValidator` object to validate a page in the library using the given list of validators:

```scala mdoc
import edu.holycross.shot.cite._

val pg = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
val rslts = LibraryValidator.validate(pg,validators)
```

The resulting Vector of `TestResult`s has lots of interesing information you could use to write reports or study the quality of your editorial work.
