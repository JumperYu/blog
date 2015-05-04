package com.yu.common.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误代码
	 */
	protected int code;

	public int getCode() {
		return code;
	}

	public ServiceException(int code, String message) {
		super(message);
		this.code = code;
	}

	public ServiceException(int code, String message, Throwable t) {
		super(message, t);
		this.code = code;
	}

}
