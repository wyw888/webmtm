package org.fkit.mapper;

import java.util.List;

import org.fkit.domain.Article;

public interface ArticleMapper {

	List<Article> selectArticleByOrderId(int id);
	
}
