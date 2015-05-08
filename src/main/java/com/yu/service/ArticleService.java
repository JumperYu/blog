package com.yu.service;

import javax.annotation.PostConstruct;

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
	 * 添加文章
	 * 
	 * @param Article
	 *            PO
	 * 
	 * @return int effected rows
	 */
	public int addArticle(Article article) {
		return articleMapper.saveEntity(article);
	}
	
	/**
	 * 获取文章
	 * 
	 * @param path
	 * @return
	 */
	public Article getArticle(String path) {
		Article article = new Article();
		article.setPath(path);
		return articleMapper.queryArticle(article);
	}

}
