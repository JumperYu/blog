package com.yu.common;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 通用JSON序列化对象
 *
 * @author zengxm
 * @date 2015年4月23日
 *
 */
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 6925016030680603630L;

	/**
	 * 返回代码
	 */
	private int code;

	/**
	 * 错误信息
	 */
	private String message;

	/**
	 * 返回对象
	 */
	private T object;

	/**
	 * 是否成功，readonly
	 */
	private boolean success;

	/**
	 * 每页记录数
	 */
	private int pageSize;

	/**
	 * 当前页
	 */
	private int currentPage;

	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 总记录数
	 */
	private int count;

	public Result() {
	}

	public Result(int code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}

	public Result(int code, T object) {
		this.setCode(code);
		this.object = object;
	}

	public Result(int code, T object, String message) {
		this.setCode(code);
		this.object = object;
		this.setMessage(message);
	}

	public Result(int pageSize, int currentPage, int count, T object) {
		if (pageSize <= 0 || currentPage <= 0 || count < 0) {
			throw new IllegalArgumentException("The paramters is not valid.");
		}

		this.setCode(ResultCode.SUCCESS);
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.count = count;
		this.totalPage = getTotalPage(pageSize, count);
		this.object = object;
	}

	public boolean isSuccess() {
		return success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
		this.success = (code == ResultCode.SUCCESS);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 设置结果
	 * 
	 * @param code
	 *            - 代码
	 * @param message
	 *            - 信息
	 */
	public void setResult(int code, String message) {
		this.message = message;
		setCode(code);
	}

	private static int getTotalPage(int everyPage, int totalRecords) {
		int totalPage = 0;

		if (totalRecords % everyPage == 0)
			totalPage = totalRecords / everyPage;
		else
			totalPage = totalRecords / everyPage + 1;

		return totalPage;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * 只输出code, success, message信息的toString方法
	 * 
	 * @return
	 */
	public String toShortString() {
		StringBuilder buf = new StringBuilder(64);
		buf.append("Result[code=").append(code).append(",message=");
		if (message == null) {
			buf.append("<null>");
		} else {
			buf.append(message);
		}
		buf.append(",success=").append(success).append("]");
		return buf.toString();
	}

	public static void main(String[] args) {
		Result<Object> result = new Result<Object>();
		System.out.println(result.toString());
		System.out.println(result.toShortString());
	}
}
