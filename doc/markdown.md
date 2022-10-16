# Markdown


## Parsing

Markdown can't really be parsed with a LR parser.

Note: a parser needs to be:
* incremental (to quickly update when the document changes without re-parsing the entire text)
* error-tolerant (highlighting doesn't break when you have a syntax error somewhere in your file)
* practical (ie produces a syntax tree in a format that the highlighter can consume)

Parsing constraints [Ref](https://marijnhaverbeke.nl/blog/lezer.html):
  * the document is constantly changing.
  * you can't do anything expensive. If the parsing works takes too long, it'll introduce latency that makes editing feel sluggish and unresponsive.
  * The input is often not in a finished, syntactically correct form. But you still have to make some sense of it—nobody wants an editor where most features stop working when you have a syntax error in your document.
  * You often want to be able to mix several languages/grammars in a single document (think HTML with JavaScript and CSS embedded in it).

The state must be copyable, so that the editor can strategically store tokenizer states 
from a previous run, and after a change, resume one close to that change to avoid re-tokenizing the entire document.
Because we are usually only interested in the code in the visible viewport, this means the complexity of re-tokenizing is bounded by the distance between the change and the end of the viewport. Since most changes happen inside of that viewport, this works well in practice.


Once you get past trivial grammars (where their declarative simplicity does look really nice), 
they don't really help that much with abstraction. 
Manually designing complicated state machines is a chore. 
Regular expressions, which are bad enough on their own, become downright [terrifying](https://github.com/jeff-hykin/cpp-textmate-grammar/blob/e7b680238e59a87231322159749d74351c9d774a/syntaxes/cpp.tmLanguage.yaml#L264) 
when you have to construct all your edges out of them, often stuffing multiple tokens into a single expression to avoid creating intermediate states. This “abstraction” has a tendency to produce uglier, less maintainable code than what you'd get when writing the tokenizer as plain code.

Classical parser generators based on context-free grammars were never going to work in this context
When you need to implement something like automatic semicolon insertion or whitespace-sensitivity, which would be a couple of lines of code in a handwritten grammar, you can't express that directly, and have to somehow escape the context-free abstraction.

[TreeSitter](http://tree-sitter.github.io/tree-sitter/) is a parser system written with the code editor use case in mind

A strict separation between the tokenizer and parser is problematic.
But just because this type of parser is traditionally ran with a completely separate tokenizer doesn't mean it has to be. Having the parse state drive the tokenizer is largely unproblematic. You can even have the parser generator set this up automatically, without user involvement.

A naively generated LR parser is huge, and many tools spit out embarrassingly big files (due to the state machine map)
But with careful parser state deduplication and table compression such a parser can be made about as compact as a hand-written one.

https://marijnhaverbeke.nl/blog/lezer.html#error-recovery

### CommonMark parsing

Any lines not claimed by some block parser are claimed by the core ParagraphParser.

Pass to the active component the start of the line for space/indentation processing (creating an indentation token)
Pass to the component the fact that this is a start line (for block indentation based on space)

Disable block indentation based on space by default

Allow or disallow p inside a mode

### Common-markup-state-machine

It uses the [Common markup state machine](https://github.com/micromark/common-markup-state-machine)


The parser parses in three stages with their own state machines: 
  * flow ([flow state machine](https://github.com/micromark/common-markup-state-machine#flow-state-machine)) to make up the structure of the document (such as outline headings or thematic breaks)
    * content ([content state machine](https://github.com/micromark/common-markup-state-machine#content-state-machine)) to tokenize the inline constructs part of content blocks 
  * and text ([text state machine](https://github.com/micromark/common-markup-state-machine#text-state-machine)) to tokenize the inline constructs part of rich text (such as regular resources and emphasis) or plain text (such as regular character escapes or character references)


## Library
### FlexMark

[flexmark-java](https://github.com/vsch/flexmark-java) is a Java implementation of CommonMark (spec 0.28) parser using the blocks first,
inlines after Markdown parsing architecture.

It is a fork of [commonmark-java project](https://github.com/commonmark/commonmark-java), modified to generate an AST which reflects all the elements in the original source, full source position tracking for all elements in the AST and easier JetBrains Open API PsiTree generation.

### Gfm

The [lexer code](https://github.com/JetBrains/markdown/blob/master/src/commonMain/kotlin/org/intellij/markdown/flavours/gfm/lexer/_GFMLexer.kt) was generated by JFlex 1.7.0 tweaked for IntelliJ platform



### Lezer (CodeMirror)

[Markdown package](https://codemirror.net/examples/lang-package/) that wraps the [parser](https://github.com/lezer-parser/markdown) (that extends the [parser class](https://lezer.codemirror.net/docs/ref/#common.Parser))

A lot of CodeMirror features are only available through its [API](https://codemirror.net/5/doc/manual.html#api). Thus, you need to write code 
(or use [addons](https://codemirror.net/5/doc/manual.html#addons)) 
if you want to expose them to your users.  

Minimal editor [Ref](https://codemirror.net/docs/guide/):
```javascript
import {EditorState} from "@codemirror/state"
import {EditorView, keymap} from "@codemirror/view"
import {defaultKeymap} from "@codemirror/commands"

let startState = EditorState.create({
doc: "Hello World",
extensions: [keymap.of(defaultKeymap)]
})

let view = new EditorView({
state: startState,
parent: document.body
})
```


Javascript with the [codemirror bundle](https://codemirror.net/docs/ref/#codemirror0)

```javascript
import {EditorView, basicSetup} from "codemirror"
import {javascript} from "@codemirror/lang-javascript"

let view = new EditorView({
  extensions: [basicSetup, javascript()],
  parent: document.body
})
```

### Unified (Remark)

[micromark](https://github.com/micromark/micromark) is the parser that creates
 the syntax tree and have plugin.


Then [Remark](https://github.com/remarkjs/remark) is the tree processor and have [plugins](https://github.com/remarkjs/remark/blob/main/doc/plugins.md#list-of-plugins) that modify it via tree traversal.

Example:
* [ramark-hint](https://github.com/sergioramos/remark-hint), transform a [paragraph node](https://github.com/sergioramos/remark-hint/blob/master/index.js#L29) to add a class.
* [new image syntax](https://github.com/remarkjs/remark-images/blob/main/index.js#L44) visit text node


## Ref

[Test File](https://github.com/vsch/flexmark-java/blob/7c187fb2bcf3bbdad4a75226a746aee752255675/flexmark-test-specs/src/main/resources/spec.txt)

