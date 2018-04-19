package com.stpl.gtn.gtn2o.ui;

public class Test {

	static int a;
	public  static void main(String[] args){
		a=10;
		after();
	}
	public static void after(){
		System.out.println(a);
		a=a+5;
		System.out.println(a);
	}
	
}
