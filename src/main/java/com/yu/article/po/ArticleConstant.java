package com.yu.article.po;

/**
 * 文章常量
 *
 * @author zengxm
 * @date 2015年5月7日
 *
 */
public class ArticleConstant {

	/**
	 * 文件状态
	 * 
	 * @author Herbert
	 * 
	 */
	public static enum Status {
		/**
		 * 隐藏
		 */
		hidden, /**
		 * /** 公开的
		 */
		display,
	};

	/**
	 * 审核
	 * 
	 * @author Herbert
	 * 
	 */
	public static enum check {
		/**
		 * 已审核
		 */
		yes, /**
		 * /** 审核失败
		 */
		no, /**
		 * /** 未审核
		 */
		init,
	};
}
