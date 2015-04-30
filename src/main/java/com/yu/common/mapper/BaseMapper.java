package com.yu.common.mapper;

import java.util.Map;

/**
 * 
 * 基础映射方法 - 还不完善 - CRUD
 *
 * @author zengxm
 * @date 2015年4月30日
 *
 */
public interface BaseMapper {

	/**
	 * 
	 * CRUD说明
	 * 
	 * @param params
	 *            Map类型 XML读取 Key的名称,Object的数据类型和值
	 * @param param
	 * 			所有对象类型, XML读取Object的数据类型和值
	 * @return int 
	 * 			影响的数据表的行数
	 */

	public int insert(Map<String, Object> params);

	public int insert(Object param);

	public int insert();

	public int update(Map<String, Object> params);

	public int update(Object param);

	public int update();

	public int delete(Map<String, Object> params);

	public int delete(Object param);

	public int delete();
	
	

}
