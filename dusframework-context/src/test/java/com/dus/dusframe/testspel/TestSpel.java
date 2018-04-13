package com.dus.dusframe.testspel;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class TestSpel {

	@Test
	public void test4spel() {
		ExpressionParser parser = new SpelExpressionParser();
		
		Expression exp = parser.parseExpression("'[asdfa]'");
		
		System.out.println((String)exp.getValue());
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("123", "56");
		
		map.put("ccms.900.001.01", "iqieiad;;fadf");
		Expression eee = parser.parseExpression("['1233']");
		
		System.out.println(eee.getValue(map));
		
		eee = parser.parseExpression("['ccms.900.001.01']");
		
		System.out.println(eee.getValue(map));
	
	}
	
	class user extends HashMap{
		
	}
}
