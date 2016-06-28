package com.stpl.ifs.util.numberfilter;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;

/**
 * @author Vimukthi
 * 
 */
@SuppressWarnings("serial")
public class NumberInterval implements Serializable {

    private final String lessThanValue;
    private final String greaterThanValue;
    private final String equalsValue;

    public NumberInterval(String lessThanValue, String greaterThanValue,
            String equalsValue) {
        this.lessThanValue = lessThanValue;
        this.greaterThanValue = greaterThanValue;
        this.equalsValue = equalsValue;
    }

    public String getLessThanValue() {
        return lessThanValue;
    }

    public String getGreaterThanValue() {
        return greaterThanValue;
    }

    public String getEqualsValue() {
        return equalsValue;
    }
    @Override
    public String toString() {
    	
    	StringBuilder value =new StringBuilder("");
    	if(StringUtils.isNotBlank(equalsValue)){
    		value = value.append("[x]= "+equalsValue);
    	}else {
    		if(StringUtils.isNotBlank(greaterThanValue)){    	
        		value =  value.append(greaterThanValue+" <");
        	}
    	value = value.append("[x]");
    	if(StringUtils.isNotBlank(lessThanValue)){
    		value =  value.append("< "+lessThanValue);
    	}
    	}
    	
    	return value.toString();
    }
    
    
    public static NumberInterval valueOf(String value){
    
    	String equalValue="", ltValue="",gtValue="";
    	if(StringUtils.isNotBlank(value)){ 
    		if(value.contains("="))
        	equalValue=value.substring(value.indexOf('=')+1, value.indexOf('<')).trim();
    		if(value.contains("<[x]"))
    			gtValue=value.substring(0, value.indexOf('<')).trim();
    		if(value.contains("[x]<"))
    			ltValue=value.substring(value.lastIndexOf('<')+1, value.length()).trim();	
    	}
    	
    	return new NumberInterval(ltValue, gtValue, equalValue);
    }
    
    public DynamicQuery getNumberFilterQuery(DynamicQuery dynamicQuery,String property,NumberInterval numberInterval){
    	try{
    		

    	  if(StringUtils.isNotBlank(numberInterval.getEqualsValue())){
    		 
       	   dynamicQuery.add(RestrictionsFactoryUtil.eq(property, Double.valueOf(numberInterval.getEqualsValue())));
    	  }
       	   else{
       		   
       		 if(StringUtils.isNotBlank(numberInterval.getGreaterThanValue())){
       		 

        			dynamicQuery.add(RestrictionsFactoryUtil.gt(property,  Double.valueOf(numberInterval.getGreaterThanValue())));
       		 }
       		 if(StringUtils.isNotBlank(numberInterval.getLessThanValue())){
       		 

      			dynamicQuery.add(RestrictionsFactoryUtil.lt(property,  Double.valueOf(numberInterval.getLessThanValue())));
       		 }
       	   }       			   
    	}catch (Exception e) {
			
		}
    	return dynamicQuery;
    }
    
   
     
    
}