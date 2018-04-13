package com.dus.dusframework.context.spel;

public interface IDusExpressionParser {

	void setValue(String el, Object value, Object root);
	<T> T getValue(String el, Object root, Class<T> clazz);
	Object getValue(String el, Object root);
	
	IDusExpression parseExpression(String el);
}
