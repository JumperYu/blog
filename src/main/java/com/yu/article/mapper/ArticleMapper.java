package com.yu.article.mapper;

import org.apache.ibatis.annotations.Insert;

import com.yu.article.po.Article;
import com.yu.common.mapper.BaseMapper;

public interface ArticleMapper extends BaseMapper<Article> {

	@Insert("insert into article(title,summary,content,createTime,updateTime) values(#{title},#{summary},#{content},now(),now())")
	public int saveEntity(Article article);
	
	public Article queryArticle(Article article);
}
