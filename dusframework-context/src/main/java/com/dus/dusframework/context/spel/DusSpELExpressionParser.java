package com.dus.dusframework.context.spel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class DusSpELExpressionParser implements IDusExpressionParser{

	private Map<String, IDusExpression> catcher = new HashMap<String, IDusExpression>(64);
	
	private ExpressionParser parser;
	
	public DusSpELExpressionParser() {
		this.parser = new SpelExpressionParser();
	}
	
	@Override
	public void setValue(String el, Object value, Object root) {
		IDusExpression exp = this.parseExpression(el);
		exp.setValue(value, root);
	}

	@Override
	public <T> T getValue(String el, Object root, Class<T> clazz) {
		IDusExpression exp = this.parseExpression(el);
		return exp.getValue(root, clazz);
	}

	@Override
	public Object getValue(String el, Object root) {
		IDusExpression exp = this.parseExpression(el);
		
		return exp.getValue(root);
	}

	@Override
	public IDusExpression parseExpression(String el) {
		IDusExpression exp = null;
		
		exp = this.catcher.get(el);
		
		if (exp != null) {
			return exp;
		}
		
		exp = new DusSpELExpression(this.parser.parseExpression(el));
		
		this.catcher.put(el, exp);
		
		return exp;
	}

}
