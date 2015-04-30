package com.yu.common.dao;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * MyBatisDAO基础支持类 依赖于Spring的SqlSessionFactory
 * 
 * 目前不使用这种方法, 主要通过getSession.getMapper(Object.class);
 * 
 * @author zengxm
 * @date 2015年4月28日
 *
 */
@Component
public class MyBatisBaseDAO extends SqlSessionDaoSupport {
	
	protected static final Logger log = LoggerFactory.getLogger(MyBatisBaseDAO.class);
	
	/**
	 * insert操作
	 * 
	 * @param key
	 *            - mapper里的key
	 * @param object
	 *            - 要操作的对象
	 * @return The number of rows affected by the insert.
	 */
	public int insert(String key, Object object) {
		return getSqlSession().insert(key, object);
	}

	/**
	 * delete操作
	 * 
	 * @param key
	 *            - mapper里的key
	 * @param obj
	 *            - 要删除对象的参数
	 * @return The number of rows affected by the delete.
	 */
	public int delete(String key, Object obj) {
		return getSqlSession().delete(key, obj);
	}

	/**
	 * update 操作
	 * 
	 * @param key
	 *            - mapper里的key
	 * @param params
	 * @return The number of rows affected by the update.
	 */
	public int update(String key, Object params) {
		return getSqlSession().update(key, params);
	}

	/**
	 * select一条记录
	 * 
	 * @param key
	 *            - mapper里的key
	 * @param params
	 *            - 参数
	 * @return
	 */
	public <T> T get(String key, Object params) {
		return (T) getSqlSession().<T> selectOne(key, params);
	}

	/**
	 * select一条记录
	 * 
	 * @param key
	 * @return
	 */
	public <T> T get(String key) {
		return (T) getSqlSession().<T> selectOne(key);
	}

	/**
	 * 返回列表
	 * 
	 * @param key
	 *            - mapper里的key
	 * @return 不为null的列表
	 */
	public <T> List<T> list(String key) {
		List<T> list = getSqlSession().selectList(key);
		return (list == null ? Collections.<T> emptyList() : list);
	}

	/**
	 * 返回列表
	 * 
	 * @param key
	 *            - mapper里的key
	 * @param params
	 *            - 参数
	 * @return 不为null的列表
	 */
	public <T> List<T> list(String key, Object params) {
		List<T> list = getSqlSession().selectList(key, params);
		return (list == null ? Collections.<T> emptyList() : list);
	}

	@Resource(name = "sqlSessionFactory")
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

}
