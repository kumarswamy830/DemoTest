package com.disney.utils;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		String test = Base64Coder.encodeString("Clandestine@78");
//		
//		System.out.println(test);
//		
//		test = Base64Coder.decodeString("Q2xhbmRlc3RpbmVANzg=");
//		
//		System.out.println(test);
		
		String test = "1234.0";
		
		
		int y= test.indexOf(".");
		
		
		System.out.println(Integer.parseInt(test.substring(0,y)));
		

	}

}
