package com.yu.utils;

import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class MyPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		
		String clasz = props.getProperty("db.class");
		String url = props.getProperty("db.url");
		String user = props.getProperty("db.user");
		String identity = props.getProperty("db.identity");

		// 赋值
		props.setProperty("db.class",
				new String(Base64.decodeBase64(clasz.getBytes())));
		props.setProperty("db.url",
				new String(Base64.decodeBase64(url.getBytes())));
		props.setProperty("db.user",
				new String(Base64.decodeBase64(user.getBytes())));
		props.setProperty("db.identity",
				new String(Base64.decodeBase64(identity.getBytes())));
		super.processProperties(beanFactoryToProcess, props);
	}

}
