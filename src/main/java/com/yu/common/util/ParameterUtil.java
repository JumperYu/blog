package com.yu.common.util;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.yu.common.exception.ServiceException;
import com.yu.common.po.ResultCode;

/**
 * 
 * 参数判断工具
 *
 * @author zengxm
 * @date 2015年5月4日
 *
 */
public class ParameterUtil {

	/**
	 * 限定参数不能为空（null，""），也不可以全部字符是空格，否则抛出ServiceException
	 * 
	 * @param s
	 *            - 字符串
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertNotBlank(String s, String errorMessage) {
		if (StringUtils.isBlank(s)) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 限定参数必须为true，否则抛出ServiceException
	 * 
	 * @param b
	 *            - 布尔值
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 * 
	 * @author liuying 2014-08-18
	 */
	public static void assertTrue(boolean b, String errorMessage) {
		if (!b) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 限定参数的长度不能超过指定值，否则抛出异常。 当 s 不等于null，并且长度超出maxLength才抛出异常。
	 * 
	 * @param s
	 *            - 要判断的字符串
	 * @param maxLength
	 *            - 指定长度
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertMaxLength(String s, int maxLength,
			String errorMessage) {
		if (s != null && s.length() > maxLength) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 限定一个整数不能是负数，否则抛出ServiceException
	 * 
	 * @param n
	 *            - 要判断的整数
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertNonnegativeInt(int n, String errorMessage) {
		if (n < 0) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 限定a必须大于等于b，否则抛出ServiceException
	 * 
	 * @param a
	 * @param b
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertGreaterThanOrEqual(int a, int b,
			String errorMessage) {
		if (a < b) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 限定a必须等于b，否则抛出ServiceException
	 * 
	 * @param a
	 * @param b
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertEqual(int a, int b, String errorMessage) {
		if (a != b) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 限定a必须大于等于b，否则抛出ServiceException
	 * 
	 * @param a
	 * @param b
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertGreaterThanOrEqual(long a, long b,
			String errorMessage) {
		if (a < b) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 限定输入值必须是指定列表的其中之一，否则抛出ServiceException
	 * 
	 * @param a
	 *            - 输入值
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @param values
	 *            - 指定列表
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertOneOfThem(short a, String errorMessage,
			short... values) {
		boolean pass = false;
		if (values != null) {
			for (short n : values) {
				if (a == n) {
					pass = true;
					break;
				}
			}
		} else {
			throw new IllegalArgumentException("values为空");
		}
		if (!pass) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 判断参数必须大于 0，否则抛出异常
	 * 
	 * @param n
	 *            - 要判断的数字
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertGreaterThanZero(int n, String errorMessage) {
		if (n < 1) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 判断参数列表里必须有一个大于0
	 * 
	 * @param values
	 *            - 要判断的数字组合
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertOneOfThemThanZero(String errorMessage,
			int... values) {
		boolean exception = true;
		if (values != null) {
			for (int n : values) {
				if (n >= 0) {
					exception = false;
					break;
				}
			}
		}
		if (exception) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}

	}

	/**
	 * 判断参数不能为null，否则抛出异常
	 * 
	 * @param o
	 *            - 要判断的参数
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertNotNull(Object o, String errorMessage) {
		if (o == null) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 判断时间范围的合法性，end>=start，否则抛出异常
	 * 
	 * @param start
	 *            - 开始时间，不能为null
	 * @param end
	 *            - 结束时间，不能为null
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertTimeRange(Date start, Date end, String errorMessage) {
		if (start == null || end == null) {
			throw new IllegalArgumentException("start或end为空");
		}
		if (start.after(end)) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 确保页码是正确的
	 * 
	 * @param page
	 *            - 要判断的页码
	 * @return 如果页面错误，令页码=1，否则返回传入的值
	 */
	public static int ensurePage(int page) {
		if (page < 1) {
			page = 1;
		}
		return page;
	}

	private static final int DEFAULT_PAGE_SIZE = 15;

	/**
	 * 确保每页的数量是正确的
	 * 
	 * @param pageSize
	 *            - 要判断的数值
	 * @return 如果pageSize<1，令pageSize=QueryCriteria.DEFAULT_PAGE_SIZE，否则返回传入的值
	 */
	public static int ensurePageSize(int pageSize) {
		if (pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}
}
