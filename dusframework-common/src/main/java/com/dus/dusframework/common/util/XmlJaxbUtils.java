package com.dus.dusframework.common.util;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlJaxbUtils {

	private static final Logger log = LoggerFactory.getLogger(XmlJaxbUtils.class);
	

	public static <T> T unmarshaer(Class<T> t, InputStream in) {
		if (in == null) {
			log.error("输入xml资源为空 ");
			throw new RuntimeException("输入xml资源为空 ");
		}
		
		JAXBContext jcontext = null;
		
		try {
			jcontext = JAXBContext.newInstance(t);
			
			Unmarshaller un = jcontext.createUnmarshaller();
			return (T)un.unmarshal(in);
		} catch (JAXBException e) {
			log.error("解析xml资源异常 " , e );
			throw new RuntimeException("解析xml资源异常", e);
		}

	}
	
}
