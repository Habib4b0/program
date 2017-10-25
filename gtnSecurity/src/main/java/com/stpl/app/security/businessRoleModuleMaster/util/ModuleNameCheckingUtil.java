package com.stpl.app.security.businessRoleModuleMaster.util;

public class ModuleNameCheckingUtil {
	
	public static final String[] SubModuleName = {"Item Hierarchy","Audit Inbound",
		"Average Shelf Life Master","Formula Details","Master Data Attribute","Item Hierarchy Definition",
		"Actual Master","Sales Master","GL Balance","Forecast Sales","Best Price","Lot Master","GL Cost Center","Cpi Index"};
	public static boolean moduleNameCheckingFun(String subModuleName){
		String[] str=ModuleNameCheckingUtil.SubModuleName;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(subModuleName)){				
				return true;
			}
		}
		return false;
		
	}
	
}
