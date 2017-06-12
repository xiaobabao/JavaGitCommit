package com.atguigu.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.CLASS)
@Inherited
@Target({ElementType.FIELD,ElementType.METHOD})
@interface MyAnnotation {
	public int id() default 520;
	String value(); 
}

//@MyAnnotation("1")
public class A implements Comparable<A>{

	@MyAnnotation(value = "2")
	private int age;
	private String name;

	public A() {
		super();
	}

	public A(int age, /*@MyAnnotation("2")*/ String name) {
		super();
		this.age = age;
		this.name = name;
	}

	@MyAnnotation("3")
	@Override
	public String toString() {
		return "A [age=" + age + ", name=" + name + "]";
	}

	public int getAge() {
		return age;
	}

	@MyAnnotation("4")
	@SuppressWarnings("unused")
	public void setAge(int age) {
		this.age = age;
		//@MyAnnotation("5")
		int a;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(A o) {
		
		return 0;
	}
}
