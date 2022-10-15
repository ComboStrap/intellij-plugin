# Markup Language


## List

https://astexplorer.net/
https://github.com/fkling/astexplorer/blob/master/README.md

### Ascii doc

[AsciiDoc](https://docs.asciidoctor.org/asciidoc/latest/) is the language.
[Asciidoctor](https://asciidoctor.org/) is the processor.

extension `adoc`, `.asciidoc`, `.ad`

Example:
```adoc
= Document Title
:reproducible:

This is a basic AsciiDoc document by {author}.

This document contains two paragraphs.
It also has a header that specifies the document title.
```

[Structure](https://docs.asciidoctor.org/asciidoc/latest/document-structure/) is line by line
with different rule by [doctype](https://docs.asciidoctor.org/asciidoc/latest/document/doctype/)
* Article (article): The default doctype. 
* Book (book): Compound of article to write a book 
* Man page (manpage): Used for producing a roff or HTML-formatted manual page (man page) for Unix 
* Inline (inline): Only inline where only the inline formatting is needed (AsciiDoc in Javadoc)

The Intellij plugin is based on jflex, [see asciidoc.flex](https://github.com/asciidoctor/asciidoctor-intellij-plugin/blob/032b2d7259cedb189089bad77257ab3cecb371f6/src/main/java/org/asciidoc/intellij/lexer/asciidoc.flex)

### Rst


[Restructured Text](http://docutils.sourceforge.net/rst.html)

### Org-mode files

https://orgmode.org/

## Java to Javascript

[TeaVm](https://github.com/konsoletyper/teavm) Compiler of Java bytecode to JavaScript

```javascript
const xpathResult = document.evaluate(xpathExpression, contextNode, namespaceResolver, resultType, result);
```
They just call node
```uglify
npm install --g uglify-js
```
