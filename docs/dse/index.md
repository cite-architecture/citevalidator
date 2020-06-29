---
title: Walk through an example
layout: page
---

**Library version 1.2.4**



```scala
// Import the generic validator library:
import edu.holycross.shot.citevalidator._
```


Works from a library, so let's get a sample:

```scala
import edu.holycross.shot.scm._

val lib = CiteLibrarySource.fromFile("jvm/src/test/resources/csample.cex")
```



For this example, we'll apply a single validator:

```scala
val dseValidator = DseValidator(lib)
// dseValidator: DseValidator = DseValidator(
//   CiteLibrary(
//     "Chants project",
//     Cite2Urn("urn:cite2:hcmid:chants.cex.testdata:all"),
//     "Creative Commons Attribution, Non-Commercial 4.0 License <https://creativecommons.org/licenses/by-nc/4.0/>.",
//     Vector(),
//     Some(
//       TextRepository(
//         Corpus(
//           Vector(
//             CitableNode(
//               CtsUrn("urn:cts:chant:massordinary.eins121.text_xml:h007_2.h00.1"),
//               "<ab n=\"1\" xmlns=\"http://www.tei-c.org/ns/1.0\">DOM PRXIMA</ab>"
//             ),
//             CitableNode(
//               CtsUrn("urn:cts:chant:massordinary.eins121.text_xml:h007_2.h02.1"),
//               "<ab n=\"1\" xmlns=\"http://www.tei-c.org/ns/1.0\">MEMENTO NOSTRI DOMINE IN BEneplacito populi tui visita nos in salutari tuo ad uidendum in bonitate electorum tuorum in <w>la<unclear>ti</unclear>tia</w> gentis tue ut lauderis cum hereditate tua</ab>"
//             ),
//             CitableNode(
//               CtsUrn(
//                 "urn:cts:chant:massordinary.eins121.text_xml:h007_2.h05.rubric"
//               ),
//               "<ab n=\"rubric\" xmlns=\"http://www.tei-c.org/ns/1.0\"><abbr>Ps</abbr></ab>"
//             ),
//             CitableNode(
//               CtsUrn("urn:cts:chant:massordinary.eins121.text_xml:h007_2.h05.1"),
//               "<ab n=\"1\" xmlns=\"http://www.tei-c.org/ns/1.0\">Confitemini <unclear>ii</unclear></ab>"
//             ),
//             CitableNode(
//               CtsUrn(
//                 "urn:cts:chant:massordinary.eins121.text_xml:h007_2.h09.rubric"
//               ),
//               "<ab n=\"rubric\" xmlns=\"http://www.tei-c.org/ns/1.0\"><abbr>RG</abbr></ab>"
//             ),
//             CitableNode(
//               CtsUrn("urn:cts:chant:massordinary.eins121.text_xml:h007_2.h09.1"),
//               "<ab n=\"1\" xmlns=\"http://www.tei-c.org/ns/1.0\"><w><abbr>Ppe</a...
val validators = Vector(dseValidator)
// validators: Vector[DseValidator] = Vector(
//   DseValidator(
//     CiteLibrary(
//       "Chants project",
//       Cite2Urn("urn:cite2:hcmid:chants.cex.testdata:all"),
//       "Creative Commons Attribution, Non-Commercial 4.0 License <https://creativecommons.org/licenses/by-nc/4.0/>.",
//       Vector(),
//       Some(
//         TextRepository(
//           Corpus(
//             Vector(
//               CitableNode(
//                 CtsUrn(
//                   "urn:cts:chant:massordinary.eins121.text_xml:h007_2.h00.1"
//                 ),
//                 "<ab n=\"1\" xmlns=\"http://www.tei-c.org/ns/1.0\">DOM PRXIMA</ab>"
//               ),
//               CitableNode(
//                 CtsUrn(
//                   "urn:cts:chant:massordinary.eins121.text_xml:h007_2.h02.1"
//                 ),
//                 "<ab n=\"1\" xmlns=\"http://www.tei-c.org/ns/1.0\">MEMENTO NOSTRI DOMINE IN BEneplacito populi tui visita nos in salutari tuo ad uidendum in bonitate electorum tuorum in <w>la<unclear>ti</unclear>tia</w> gentis tue ut lauderis cum hereditate tua</ab>"
//               ),
//               CitableNode(
//                 CtsUrn(
//                   "urn:cts:chant:massordinary.eins121.text_xml:h007_2.h05.rubric"
//                 ),
//                 "<ab n=\"rubric\" xmlns=\"http://www.tei-c.org/ns/1.0\"><abbr>Ps</abbr></ab>"
//               ),
//               CitableNode(
//                 CtsUrn(
//                   "urn:cts:chant:massordinary.eins121.text_xml:h007_2.h05.1"
//                 ),
//                 "<ab n=\"1\" xmlns=\"http://www.tei-c.org/ns/1.0\">Confitemini <unclear>ii</unclear></ab>"
//               ),
//               CitableNode(
//                 CtsUrn(
//                   "urn:cts:chant:massordinary.eins121.text_xml:h007_2.h09.rubric"
//                 ),
//                 "<ab n=\"rubric\" xmlns=\"http://www.tei-c.org/ns/1.0\"><abbr>RG...
```

Now we can use the `LibraryValidator` object to validate a page in the library using the given list of validators:

```scala
import edu.holycross.shot.cite._

val pg = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
// pg: Cite2Urn = Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
val rslts = LibraryValidator.validate(pg,validators)
// rslts: Vector[TestResult[Any]] = Vector(
//   TestResult(
//     true,
//     "Compare text urn:cts:chant:massordinary.eins121.text_xml:h007_2.h00.1 to image [![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/citeecod/einsiedeln121imgs/v1/sbe_0121_21.tif/pct:42.24,67.53,29.02,6.05/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:citeecod:einsiedeln121imgs.v1:sbe_0121_21@0.4224,0.6753,0.2902,0.06050)Text passage urn:cts:chant:massordinary.eins121.text_xml:h007_2.h00.1 found in corpus. ",
//     DsePassage(
//       Cite2Urn("urn:cite2:validate:tempDse.temp:record_0"),
//       "Passage 0",
//       CtsUrn("urn:cts:chant:massordinary.eins121.text_xml:h007_2.h00.1"),
//       Cite2Urn(
//         "urn:cite2:citeecod:einsiedeln121imgs.v1:sbe_0121_21@0.4224,0.6753,0.2902,0.06050"
//       ),
//       Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
//     )
//   ),
//   TestResult(
//     false,
//     "Compare text urn:cts:chant:massordinary.eins121.text_xml:h007_2.h02.1@Mem-h02.1@in to image [![Linked to zoomble image](http://www.homermultitext.org/iipsrv?IIIF=/project/homer/pyramidal/deepzoom/citeecod/einsiedeln121imgs/v1/sbe_0121_21.tif/pct:5.747,65.69,67.69,20.52/2000,/0/default.jpg)](http://www.homermultitext.org/ict2/?urn=urn:cite2:citeecod:einsiedeln121imgs.v1:sbe_0121_21@0.05747,0.6569,0.6769,0.2052)Indexed passage urn:cts:chant:massordinary.eins121.text_xml:h007_2.h02.1@Mem-h02.1@in **NOT FOUND** in text corpus.",
//     DsePassage(
//       Cite2Urn("urn:cite2:validate:tempDse.temp:record_1"),
//       "Passage 1",
//       CtsUrn(
//         "urn:cts:chant:massordinary.eins121.text_xml:h007_2.h02.1@Mem-h02.1@in"
//       ),
//       Cite2Urn(
//         "urn:cite2:citeecod:einsiedeln121imgs.v1:sbe_0121_21@0.05747,0.6569,0.6769,0.2052"
//       ),
//       Cite2Urn("urn:cite2:ecod:eins121pages.v1:21")
//     )
//   )
// )
```

The resulting Vector of `TestResult`s has lots of interesing information you could use to write reports or study the quality of your editorial work.
