package com.company.jmixpm.screen.article;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Article;

@UiController("Article.edit")
@UiDescriptor("article-edit.xml")
@EditedEntityContainer("articleDc")
public class ArticleEdit extends StandardEditor<Article> {
}