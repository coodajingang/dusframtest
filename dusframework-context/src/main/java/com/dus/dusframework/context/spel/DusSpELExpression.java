package com.dus.dusframework.context.spel;

import org.springframework.expression.Expression;

public class DusSpELExpression implements IDusExpression{

	private Expression expression;
	

	public DusSpELExpression(Expression parseExpression) {
		this.expression = parseExpression;
	}

	@Override
	public void setValue(Object obj, Object root) {
		this.expression.setValue(root, obj);
		
	}

	@Override
	public Object getValue(Object root) {
		// TODO Auto-generated method stub
		return this.expression.getValue(root);
	}

	@Override
	public <T> T getValue(Object root, Class<T> clazz) {
		// TODO Auto-generated method stub
		return this.expression.getValue(root, clazz);
	}
}
