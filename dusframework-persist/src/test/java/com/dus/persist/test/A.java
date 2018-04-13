package com.dus.persist.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class A {

	private String a;

    private Class entityClass;
    private String namespace;
    
	public A() {
        Class c = this.getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType)type).getActualTypeArguments();
            this.entityClass = (Class)parameterizedType[0];
            this.namespace = this.entityClass.getName();
        }
	}
	
	
	public Class getEntityClass() {
		return entityClass;
	}


	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}


	public String getNamespace() {
		return namespace;
	}


	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}


	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	
}
