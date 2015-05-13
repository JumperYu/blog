package com.yu.article.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.yu.article.po.Article;
import com.yu.common.mapper.BaseMapper;

public interface ArticleMapper extends BaseMapper<Article> {
	
	@Insert("insert into article(title,summary,content,createTime,updateTime) values(#{title},#{summary},#{content},#{createTime},now())")
	public int saveEntity(Article article);
	
	@Update("update article set title=#{title},summary=#{summary},content=#{content},createTime=#{createTime},updateTime=now() where articleId=#{articleId}")
	public int updateEntity(Article article);

	public Article queryArticle(Article article);

	public List<Article> queryAllArticles(Map<String, Object> params);
}
