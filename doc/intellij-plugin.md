# Intellij plugin (Extension point)


## Extensions


https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html#declaring-extensions

You need to dig into: https://plugins.jetbrains.com/intellij-platform-explorer/extensions

[OpenApi](https://github.com/JetBrains/intellij-community/tree/master/platform/platform-api/src/com/intellij/openapi)
contains all extension point

## Structure 
### Module


| com.intellij.modules.platform | Messaging, Themes, UI Components, Files, Documents, Actions, Components, Services, Extensions, Editors |
| com.intellij.modules.lang | File Type, Lexer, Parser, Highlighting, References, Code Completion, Find, Rename, Formatter, Code Navigation |
| com.intellij.modules.xml | XML, XML DOM, XSD/DTD, DOM Model |
| com.intellij.modules.vcs | VCS Revision Numbers, File Status, Change Lists, File History, Annotations |
| com.intellij.modules.xdebugger | Debug Session, Stack Frames, Break Points, Source Positions, Memory Views, Tracked Instances |


### Program Structure Interface (PSI)

The Program Structure Interface, commonly referred to as just PSI,
is the layer in the IntelliJ Platform responsible for parsing files
and creating the syntactic and semantic code model that powers so many of the platform's features

A ASTNode is node in the AST tree. The AST is an intermediate parsing tree created by PsiBuilder,
out of which a PSI tree is then created.

https://plugins.jetbrains.com/docs/intellij/psi.html

## Extension List


List of interesting plugin

### fileEditorProvider 
fileEditorProvider extension (WeighedFileEditorProvider) - [Markdown Editor](https://github.com/shuzijun/markdown-editor)
managed by the FileEditorManager



### Language Injection (MultiHostInjector)
Language Injection: allows you to have syntax highlighting for one language into another, 
such as having syntax highlighting for SQL inside a String when editing a java file.

It's possible to write a custom plugin MultiHostInjector implementation 
that would support any custom annotation or whatever syntax for injection in Dart string literals.


https://www.jetbrains.com/help/idea/using-language-injections.html
https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/injection/MultiHostInjector.java

These injected fragments are treated by the IDE as separate tiny files in a specific language and all corresponding code insight features
(completion, highlighting, navigation) become available there.

Example: 


## Install

https://plugins.jetbrains.com/docs/intellij/custom-language-support-tutorial.html

* `Ctrl+Alt+S`: Plugin: Install:
    * `Grammar-Kit`: Adds BNF Grammars files editing support, and a parser/PSI code generator.
    * `PsiViewer` : view the PSI tree
* Create an empty IntelliJ Platform Plugin project or start using IntelliJ Platform Plugin Template when creating a plugin hosted on GitHub.
