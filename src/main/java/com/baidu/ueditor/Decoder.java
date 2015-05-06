package com.baidu.ueditor;


public class Decoder {

	public static String toString(String input) {

		StringBuffer string = new StringBuffer();

		String[] hex = input.split("\\\\u");

		for (int i = 1; i < hex.length; i++) {

			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);

			// 追加成string
			string.append((char) data);
		}

		return string.toString();
	}
}
