package com.yu.common.po;

/**
 * 错误代码定义
 * @author tendy
 * 2014/1/24
 */
public class ResultCode {
	
	/**
	 * 失败
	 */
	public static final int FAILURE = 0;

	/**
	 * 成功
	 */
	public static final int SUCCESS = 1;
	
	/**
	 * 参数错误
	 */
	public static final int PARAMETER_ERROR = -10;
	
	/**
	 * 超时
	 */
	public static final int TIMEOUT = -20;
	
	/**
	 * 数据库错误
	 */
	public static final int DATABASE_ERROR = -30;
	
	/**
	 * 记录已存在(唯一)
	 */
	public static final int RECORD_EXISTED = -31;
	
	/**
	 * 记录不存在
	 */
	public static final int RECORD_NOT_EXIST = -31;
	
	/**
	 * 未知错误
	 */
	public static final int UNKNOWN_ERROR = -100;
}
