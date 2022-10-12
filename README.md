# ComboStrap Intellij Plugin

![Build](https://github.com/ComboStrap/intellij-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)



## File Registration

* [FileType Registration](https://plugins.jetbrains.com/docs/intellij/registering-file-type.html#additional-features)

## Lexer

[Lexer](https://plugins.jetbrains.com/docs/intellij/implementing-lexer.html#lexer-state)

The lexical analyzer defines how the contents of a file are broken into tokens

An essential requirement for a syntax highlighting lexer is that its state must be represented by 
a single integer number returned from `Lexer.getState()`. 

That state will be passed to the `Lexer.start()` method, along with the start offset of the fragment to process, 
when lexing is resumed from the middle of a file. 

Lexers used in other contexts can always return 0 from getState().

## AST

The PsiFileBase is the top-level node of the tree of PsiElements

Tool > View PsiStructure: With the PsiViewer tool window, you can check how the lexer breaks 
the content of the file into tokens, and the parser transforms the tokens into PSI elements.

## Template ToDo list


Followed: https://plugins.jetbrains.com/docs/intellij/custom-language-support.html#topics


Followed: https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/simple_language_plugin



See also: https://github.com/JetBrains/intellij-community/tree/master/plugins/markdown

- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Get familiar with the [template documentation][template].
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the Plugin ID in the above README badges.
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.

<!-- Plugin description -->
This Fancy IntelliJ Platform Plugin is going to be your implementation of the brilliant ideas that you have.

This specific section is a source for the [plugin.xml](/src/main/resources/META-INF/plugin.xml) file which will be extracted by the [Gradle](/build.gradle.kts) during the build process.

To keep everything working, do not remove `<!-- ... -->` sections. 
<!-- Plugin description end -->

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "intellij-plugin"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/ComboStrap/intellij-plugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
