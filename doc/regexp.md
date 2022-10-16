# Regexp

## About
Regexp should implement finite automata to guarantee linear time matching on all inputs.

Java's standard regular expression package, java.util.regex, and many other widely used regular expression packages such as PCRE, Perl and Python use a backtracking implementation strategy

When a pattern presents two alternatives such as `a|b`, the engine will try to match subpattern `a` first, and if that yields no match, it will reset the input stream and try to match `b` instead.

If such choices are deeply nested, this strategy requires an exponential number of passes over the input data before it can detect whether the input matches. If the input is large, it is easy to construct a pattern whose running time would exceed the lifetime of the universe.

This creates a security risk when accepting regular expression patterns from untrusted sources, such as users of a web application.

In contrast, a nondeterministic finite automaton will explores all matches simultaneously in a single pass over the input data.

## Compilation

Compilation takes a string pattern and transform it as a table of state with transitions.

It is an antipattern to compile the same regular expression in a loop since compilation is typically expensive. (It takes anywhere from a few microseconds to a few milliseconds depending on the size of the regex.) Not only is compilation itself expensive, but this also prevents optimizations that reuse allocations internally to the matching engines.

## Library
* [rust](https://github.com/rust-lang/regex)
* [re2j](https://github.com/google/re2j)
* [automata js - simple](https://github.com/hokein/automata.js/)



## Course
* [course in java](https://dmitrysoshnikov.medium.com/building-a-regexp-machine-part-2-finite-automata-nfa-fragments-5a7c5c005ef0)
