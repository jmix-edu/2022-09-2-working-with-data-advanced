package com.company.jmixpm.screen.article;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Article;

@UiController("Article.browse")
@UiDescriptor("article-browse.xml")
@LookupComponent("articlesTable")
public class ArticleBrowse extends StandardLookup<Article> {
}