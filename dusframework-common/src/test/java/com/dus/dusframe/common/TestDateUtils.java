package com.dus.dusframe.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;

import com.dus.dusframework.common.util.DateUtils;

public class TestDateUtils {

	@Test
	public void test4dateu() {
		System.out.println(DateUtils.getIsoDate14());
		System.out.println(DateUtils.getIsoDate8());
		
		System.out.println(DateUtils.getXmlGenCanlen().toXMLFormat());
	}
	
	@Test
	public void test4xmlgen() throws ParseException {
		
		Date date = null;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		date = df.parse("2017-09-09");
		
		DatatypeFactory dataTypeFactory;
		try {
		dataTypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
		throw new RuntimeException(e);
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(date.getTime());
		XMLGregorianCalendar xml = dataTypeFactory.newXMLGregorianCalendar(gc);
		
		System.out.println(xml.toString());
	}
}
