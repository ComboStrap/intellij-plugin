package com.combostrap.intellij.markups.wiki;

import com.combostrap.intellij.markups.wiki.psi.WikiProperty;
import com.google.common.collect.Lists;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class WikiUtil {

    /**
     * Searches the entire project for wiki language files with instances
     * of the Wiki property with the given key.
     *
     * @param project current project
     * @param key     to check
     * @return matching properties
     */
    public static List<WikiProperty> findProperties(Project project, String key) {
        List<WikiProperty> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(WikiFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            WikiFile wikiFile = (WikiFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (wikiFile != null) {
                WikiProperty[] properties = PsiTreeUtil.getChildrenOfType(wikiFile, WikiProperty.class);
                if (properties != null) {
                    for (WikiProperty property : properties) {
                        if (key.equals(property.getKey())) {
                            result.add(property);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static List<WikiProperty> findProperties(Project project) {
        List<WikiProperty> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(WikiFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            WikiFile simpleFile = (WikiFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (simpleFile != null) {
                WikiProperty[] properties = PsiTreeUtil.getChildrenOfType(simpleFile, WikiProperty.class);
                if (properties != null) {
                    Collections.addAll(result, properties);
                }
            }
        }
        return result;
    }

    /**
     * Attempts to collect any comment elements above the Simple key/value pair.
     */
    public static @NotNull String findDocumentationComment(WikiProperty property) {
        List<String> result = new LinkedList<>();
        PsiElement element = property.getPrevSibling();
        while (element instanceof PsiComment || element instanceof PsiWhiteSpace) {
            if (element instanceof PsiComment) {
                String commentText = element.getText().replaceFirst("[!# ]+", "");
                result.add(commentText);
            }
            element = element.getPrevSibling();
        }
        return StringUtil.join(Lists.reverse(result),"\n ");
    }

}
