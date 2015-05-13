package com.yu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.yu.article.mapper.ArticleMapper;
import com.yu.article.po.Article;
import com.yu.common.service.MyBatisBaseService;

/**
 * 
 * 文章相关操作
 *
 * @author zengxm
 * @date 2015年5月7日
 *
 */
@Service
public class ArticleService extends MyBatisBaseService {

	ArticleMapper articleMapper;

	@PostConstruct
	public void init() {
		articleMapper = getMyBatisBaseDAO().getSqlSession().getMapper(
				ArticleMapper.class);
	}

	/**
	 * 添加或者更新文章
	 * 
	 * @param Article articleId决定
	 * 
	 * @return int effected rows
	 */
	public int saveOrUpdateArticle(Article article) {
		if (article.getArticleId() == 0)
			return articleMapper.saveEntity(article);
		else
			return articleMapper.updateEntity(article);
	}

	/**
	 * 获取文章
	 * 
	 * @param path
	 * @return
	 */
	public Article getArticle(long path) {
		Article article = new Article();
		article.setArticleId(path);
		return articleMapper.queryArticle(article);
	}

	/**
	 * 按最新时间批量获取文章简介
	 * 
	 * 
	 */
	public List<Article> getAllArticles(String year, String month, String day) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("year", StringUtils.isBlank(year) ? null : year);
		params.put("month", StringUtils.isBlank(month) ? null : month);
		params.put("day", StringUtils.isBlank(day) ? null : day);
		return articleMapper.queryAllArticles(params);
	}
}
