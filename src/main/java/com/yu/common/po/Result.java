package com.yu.common.po;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.baidu.ueditor.Encoder;

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
	private T result;

	/**
	 * 是否成功，readonly
	 */
	private boolean success;

	public Result() {
	}

	public Result(int code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}

	public Result(int code, T result) {
		this.setCode(code);
		this.result = result;
	}

	public Result(int code, T result, String message) {
		this.setCode(code);
		this.result = result;
		this.setMessage(message);
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
		this.message = Encoder.toUnicode(message);
	}

	public T getResult() {
		return result;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
