# Features (Connection point)


## Extensions


https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html#declaring-extensions

You need to dig into: https://plugins.jetbrains.com/intellij-platform-explorer/extensions

## Module


| com.intellij.modules.platform | Messaging, Themes, UI Components, Files, Documents, Actions, Components, Services, Extensions, Editors |
| com.intellij.modules.lang | File Type, Lexer, Parser, Highlighting, References, Code Completion, Find, Rename, Formatter, Code Navigation |
| com.intellij.modules.xml | XML, XML DOM, XSD/DTD, DOM Model |
| com.intellij.modules.vcs | VCS Revision Numbers, File Status, Change Lists, File History, Annotations |
| com.intellij.modules.xdebugger | Debug Session, Stack Frames, Break Points, Source Positions, Memory Views, Tracked Instances |


## Program Structure Interface (PSI)

The Program Structure Interface, commonly referred to as just PSI, 
is the layer in the IntelliJ Platform responsible for parsing files 
and creating the syntactic and semantic code model that powers so many of the platform's features

A ASTNode is node in the AST tree. The AST is an intermediate parsing tree created by PsiBuilder, 
out of which a PSI tree is then created.

https://plugins.jetbrains.com/docs/intellij/psi.html

## Language Injection (MultiHostInjector)
Language Injection: allows you to have syntax highlighting for one language into another, 
such as having syntax highlighting for SQL inside a String when editing a java file.

It's possible to write a custom plugin MultiHostInjector implementation 
that would support any custom annotation or whatever syntax for injection in Dart string literals.


https://www.jetbrains.com/help/idea/using-language-injections.html
https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/lang/injection/MultiHostInjector.java

These injected fragments are treated by the IDE as separate tiny files in a specific language and all corresponding code insight features
(completion, highlighting, navigation) become available there.

Example: 

