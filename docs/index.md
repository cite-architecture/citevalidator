---
title: HCMID validator library
layout: page
---

**Library version 1.0.1**

## The `CiteValidator` trait

The  `CiteValidator` library defines abstract interfaces that can be implemented to validate editing a CITE library. Type-parameterized implementations apply some specific type of validation to elements of a CITE Library, and include methods for validating an entire library, or for validating contents related to a specific text-bearing surface in the DSE model.  

The results of their validation are also type parameterized.  Results of different types can be gathered in a `TestResultGroup`.


-- See an [example implementation of a `CiteValidator`](./dse/)


## Validation results

- `TestResult`.  See an [example with DSE validation](./results/)
- `TestResultGroup`
