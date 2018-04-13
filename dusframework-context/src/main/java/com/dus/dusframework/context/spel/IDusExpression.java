package com.dus.dusframework.context.spel;

public interface IDusExpression {

	void setValue(Object obj, Object root);
	Object getValue(Object root);
	<T> T getValue(Object root, Class<T> clazz);
}
