package com.stpl.ifs.util;

public class ValidationUtils {
	
	public static String specialCharacter="([0-9|a-z|A-Z|\\.|\\%|\\_|\\s])*";
	public static String specialCharacter_Message="value can contain only digits,alphabets";

	public static String numericValidation="([0-9])*";
	public static String numericValidationMessage="value can contain only numbers";

	public static String price="([0-9|\\.|\\,])*";
	public static String priceMessage="Item Price  can contain only digits";

	public static String Search_specialCharacter="([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*";
	public static String Search_specialCharacter_Message="Allowed Special characters are @,*,#,.,$,&,_,-";
      
}
