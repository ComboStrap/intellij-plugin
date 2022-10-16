# Ascii doc

## About
[AsciiDoc](https://docs.asciidoctor.org/asciidoc/latest/) is the language.
[Asciidoctor](https://asciidoctor.org/) is the processor.

## Extension 
`adoc`, `.asciidoc`, `.ad`

## Example

```adoc
= Document Title
:reproducible:

This is a basic AsciiDoc document by {author}.

This document contains two paragraphs.
It also has a header that specifies the document title.
```

## Structure

[Structure](https://docs.asciidoctor.org/asciidoc/latest/document-structure/) is line by line
with different rule by [doctype](https://docs.asciidoctor.org/asciidoc/latest/document/doctype/)
* Article (article): The default doctype.
* Book (book): Compound of article to write a book
* Man page (manpage): Used for producing a roff or HTML-formatted manual page (man page) for Unix
* Inline (inline): Only inline where only the inline formatting is needed (AsciiDoc in Javadoc)

## Lexer / Parser

The Intellij plugin is based on jflex grammar, [see asciidoc.flex](https://github.com/asciidoctor/asciidoctor-intellij-plugin/blob/main/src/main/java/org/asciidoc/intellij/lexer/asciidoc.flex)
with a lot of boilerplate to drive the lexing.

