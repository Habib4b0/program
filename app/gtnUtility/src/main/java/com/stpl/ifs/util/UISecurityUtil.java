package com.stpl.ifs.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.stpl.app.security.permission.model.AppPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UISecurityUtil {
	public static final String CONTRACT_HEADER_FIELD_SECURE_HM= "permissionContractMasterField";
	public static final String CONTRACT_HEADER_FUNCTION_SECURE_HM= "permissionContractMasterFunction";
	public static final String CONTRACT_HEADER_TAB_SECURE_HM= "permissionContractMasterTab";

	public static final String CONTRACT_DASHBOARD_FIELD_SECURE_HM= "permissionContractDashboardField";
	public static final String CONTRACT_DASHBOARD_FUNCTION_SECURE_HM= "permissionContractDashboardFunction";
	public static final String CONTRACT_DASHBOARD_TAB_SECURE_HM= "permissionContractDashboardTab";	
	
	public static final String CONTRACT_HEADER= "Contract Header";
	public static final String CONTRACT_DASHBOARD= "Contract Dashboard";	
        private static final Logger LOGGER = LoggerFactory.getLogger(UISecurityUtil.class);
	
	
	public static TableResultCustom modifyTableResult(Object[] obj,String[] header,HashMap<String, AppPermission> fieldHM){
		TableResultCustom tblResultCustom=new TableResultCustom();
		String str="";	
		
		List<Object> objResultList=new ArrayList();
		List<String> objResultHeaderList=new ArrayList();
		try{
		for(int i=0;i<obj.length;i++){
			str=String.valueOf(obj[i]);
			if(fieldHM.get(str)!=null){
				AppPermission appPermission=fieldHM.get(str);				
				if(appPermission.isSearchFlag()){
					
					objResultList.add(obj[i]);
					objResultHeaderList.add(header[i]);					
				}
			}
		}
		Object[] objResult=new Object[objResultList.size()];
		String[] objResultHeader=new String[objResultList.size()];		
		for(int i=0;i<objResultList.size();i++){
			objResult[i]=objResultList.get(i);
			objResultHeader[i]=objResultHeaderList.get(i);			
		}
		tblResultCustom.setObjResult(objResult);
		tblResultCustom.setObjResultHeader(objResultHeader);
		
		}
		catch(Exception ex){
                    LOGGER.error(ex.getMessage());
		}
		return tblResultCustom;
	}
	
	
	public static TableResultCustom modifyAddTableResult(Object[] obj,String[] header,HashMap<String, AppPermission> fieldHM){
		TableResultCustom tblResultCustom=new TableResultCustom();
		String str="";		
		List<Object> objResultList=new ArrayList();
		List<String> objResultHeaderList=new ArrayList();
		try{
		for(int i=0;i<obj.length;i++){
			str=String.valueOf(obj[i]);
			if(fieldHM.get(str)!=null){
				AppPermission appPermission=fieldHM.get(str);				
				if(appPermission.isAddFlag()){					
					objResultList.add(obj[i]);
					objResultHeaderList.add(header[i]);					
				}
			}
		}
		Object[] objResult=new Object[objResultList.size()];
		String[] objResultHeader=new String[objResultList.size()];		
		for(int i=0;i<objResultList.size();i++){
			objResult[i]=objResultList.get(i);
			objResultHeader[i]=objResultHeaderList.get(i);			
		}
		tblResultCustom.setObjResult(objResult);
		tblResultCustom.setObjResultHeader(objResultHeader);
		
		}
		catch(Exception ex){
                    LOGGER.error(ex.getMessage());
		}
		return tblResultCustom;
	}
	
	public static TableResultCustom modifyEditTableResult(Object[] obj,String[] header,HashMap<String, AppPermission> fieldHM){
		TableResultCustom tblResultCustom=new TableResultCustom();
		String str="";		
		List<Object> objResultList=new ArrayList();
		List<String> objResultHeaderList=new ArrayList();
		try{
		for(int i=0;i<obj.length;i++){
			str=String.valueOf(obj[i]);
			if(fieldHM.get(str)!=null){
				AppPermission appPermission=fieldHM.get(str);				
				if(appPermission.isEditFlag()){
					
					objResultList.add(obj[i]);
					objResultHeaderList.add(header[i]);					
				}
			}
		}
		Object[] objResult=new Object[objResultList.size()];
		String[] objResultHeader=new String[objResultList.size()];		
		for(int i=0;i<objResultList.size();i++){
			objResult[i]=objResultList.get(i);
			objResultHeader[i]=objResultHeaderList.get(i);			
		}
		tblResultCustom.setObjResult(objResult);
		tblResultCustom.setObjResultHeader(objResultHeader);		
		}
		catch(Exception ex){
                    LOGGER.error(ex.getMessage());
		}
		return tblResultCustom;
	}
	
	public static TableResultCustom modifyViewTableResult(Object[] obj,String[] header,HashMap<String, AppPermission> fieldHM){
		TableResultCustom tblResultCustom=new TableResultCustom();
		String str="";		
		List<Object> objResultList=new ArrayList();
		List<String> objResultHeaderList=new ArrayList();
		try{
		for(int i=0;i<obj.length;i++){
			str=String.valueOf(obj[i]);
			if(fieldHM.get(str)!=null){
				AppPermission appPermission=fieldHM.get(str);				
				if(appPermission.isViewFlag()){					
					objResultList.add(obj[i]);
					objResultHeaderList.add(header[i]);					
				}
			}
		}
		Object[] objResult=new Object[objResultList.size()];
		String[] objResultHeader=new String[objResultList.size()];		
		for(int i=0;i<objResultList.size();i++){
			objResult[i]=objResultList.get(i);
			objResultHeader[i]=objResultHeaderList.get(i);			
		}
		tblResultCustom.setObjResult(objResult);
		tblResultCustom.setObjResultHeader(objResultHeader);		
		}
		catch(Exception ex){
                    LOGGER.error(ex.getMessage());
		}
		return tblResultCustom;
	}

}
