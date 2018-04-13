package com.dus.dusframework.context;

import java.util.HashMap;

import com.dus.dusframework.context.spel.DusSpELExpressionParser;
import com.dus.dusframework.context.spel.IDusExpressionParser;

public class RunContext extends HashMap{

	private IDusExpressionParser parser;
	
	public RunContext() {
		this.parser = new DusSpELExpressionParser();
	}
	
	private static final long serialVersionUID = -4537656335906586084L;
	
	public Object getValue(String key) {
		return this.parser.getValue(key, this);
	}

	
	public <T> T getValue(String key, Class<T> clazz) {
		return this.parser.getValue(key, this, clazz);
	}
	
	public void setValue(String key, Object obj) {
		this.parser.setValue(key, obj, this);
	}
	
}
