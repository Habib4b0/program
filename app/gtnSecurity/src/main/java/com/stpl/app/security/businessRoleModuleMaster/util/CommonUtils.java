package com.stpl.app.security.businessRoleModuleMaster.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.stpl.app.security.businessRoleModuleMaster.dto.HelperDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.search.ParseException;
import com.stpl.portal.model.ListType;
import com.stpl.portal.service.ListTypeServiceUtil;
import com.vaadin.ui.NativeSelect;

public class CommonUtils {
	
    public static final String EMPTY = "";
    public static final String NOT_IN = " not in (";
    public static final String IN_BRACES = " in (";
    public static final String STRING_ASTERISK = "*";
    public static final String MMDDYYYY = "MM/dd/yyyy";
    public static final String MMDDYYYYHHMMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String STRING_NULL = "null";
    public static final String STRING_ZERO = "0";
    public static final char CHAR_PERCENT = '%';
    public static final char CHAR_ASTERISK = '*';
    public static final String BUSINESS_ROLE_NAME = "businessroleName";
    public static final String BUSINESS_ROLE_MASTERSID = "businessroleMasterSid";
    public static final String MODULE_NAME = "moduleName";
    public static final String SUB_MODULE_NAME = "submoduleName";
    public static final String CATEGORY_NAME = "categoryName";
    public static final String CATEGORY = "category";
    public static final String SUCCESS = "success";
    public static final String BRAND_ID = "brandId";
    public static final String DESCRIPTION = "description";
    public static final String CASECATEGORY = "Category";
    public static final String MYSTYLE = "mystyle";
    public static final String BRAND = "BRAND";
    public static final String USERGROUP_ID = "usergroupId";
    public static final String SELECT_ONE = "-Select One-";
    public static final String CATEGORY_SPACE_NAME = "Category Name";
    public static final String SUBMODULE_SPACE_NAME = "Submodule Name";
    public static final String MODULE_SPACE_NAME = "Module Name";
	/*
	 * @param listTypeId- listType typeid of listtype table to get the description
	 * @return String - The description for the specific listtype id
	 */
	private static final Logger LOGGER = LogManager
			.getLogger(CommonUtils.class.getName());
	public  NativeSelect getNativeSelect(NativeSelect select,List<HelperDTO> helperList) {
		for (HelperDTO helperDTO : helperList) {
			select.addItem(helperDTO.getDescription());
		}
		select.addItem("EditList");
		return select;
	}
	
	public  NativeSelect getStatusSelect(NativeSelect select) {	
			select.addItem("Active");
			select.addItem("InActive");
		return select;
	}
	
	public static String getListTypeName(int listTypeId) throws PortalException, SystemException{
		String listValue=EMPTY;
		if(listTypeId!=0&&listTypeId!=1&&listTypeId!=NumericConstants.TWO){
		ListType listType=ListTypeServiceUtil.getListType(listTypeId);
		listValue=listType.getName();
		}
		if(listValue == null || listValue.equals(STRING_NULL))
		    listValue=EMPTY;
		return listValue;
	}
	
	/*
	 * @param strDate - input date in string format
	 * @return Date - The Date object of the strDate in the format of "MM/dd/yyyy"
	 */
	public static Date convertStringToDate(String strDate)throws  java.text.ParseException {
		Date aDate = null;
		          if (strDate == null || strDate.equals(EMPTY) || strDate.equals(STRING_NULL)) {
                return null;
            }
            aDate = convertStringToDate(MMDDYYYY, strDate);
            return aDate;
	}

	/*
	 * @param aMask - Date format input
	 * @param strDate - input date in string format
	 * @return Date - The Date object of the strDate 
	 */
	public static final Date convertStringToDate(String aMask, String strDate) throws java.text.ParseException{
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);
		date = df.parse(strDate);
		return date;
	}

	/*
	 * @param aDate - The Date object to be converted to string
	 * @return String - date in string format in the format of "MM/dd/yyyy"
	 */
	public static final String convertDateToString(Date aDate){
		return getDateTime(MMDDYYYY, aDate);
	}

	/*
	 * @param aMask - Date format input
	 * @param aDate - The Date object to be converted to string
	 * @return String - date in string format
	 */
	public static final String getDateTime(String aMask, Date aDate){
		SimpleDateFormat df = null;
		String returnValue = EMPTY;
		if (aDate == null) {
		}
		else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return returnValue;
	}
	
	public static final int convertStringToInt(String aMask){
		int returnValue=0;
		if(aMask!=null){
			returnValue=Integer.parseInt(aMask);
		}
		return returnValue;
	}
	public static final String convertIntToString(int aMask){
		String returnValue;		
		returnValue=""+aMask;		
		return returnValue;
	}
	public static final String convertDoubleToString(Double aMask){
		String returnValue;
		returnValue=""+aMask;
		return returnValue;
	}
	public static final Double convertStringToDouble(String aMask){
		Double returnValue=0d;
		if(aMask!=null){
			returnValue=Double.parseDouble(aMask);
		}
		return returnValue;		
	}
	
	// Method to convert Char to String
	public static final String convertCharToString(char aMask){
		String returnValue;		
		returnValue = Character.toString(aMask);
		return returnValue;		
	}
	
	   public static final Character convertStringToChar(String aMask) {
        char returnValue = '0';

        if (aMask != null){
            returnValue = aMask.charAt(0);
        }

        return returnValue;	
		}
		
		/**
		 * 
		 * @param list1 -contains the value to be in IN query
		 * @param columnName - for which column this IN query mapping
		 * @return - proper IN query in String format
		 */
		public static String getSplitInQuery(@SuppressWarnings("rawtypes") List list1, String columnName) {

	    	@SuppressWarnings("rawtypes")
			List memList= list1;
	    	String requiredColumnName = columnName;
	    	int count=0;
	        int a=0;
	        int subqueryNocount=0;
   	        String memberIdArraytemp[] = new String[NumericConstants.THOUSAND];
	        String memberIdquery ="";
			 boolean xflag = true;
			 try{
			 while(count<memList.size()){
	         			Object o = (Object) memList.get(count);
	         			memberIdArraytemp[a]=(String)o;

	         			if(a==NumericConstants.NINE_NINTY_NINE){
	         				if(subqueryNocount==0){
	         				memberIdquery = " " + requiredColumnName + IN_BRACES+arrayToString2(memberIdArraytemp,",")+")";
	         				subqueryNocount = 1;
	         				}
	         				else{
	         					memberIdquery = memberIdquery + " or "+requiredColumnName+IN_BRACES+arrayToString2(memberIdArraytemp,",")+")";

	         				}
	         				a=0;
	         				memberIdArraytemp = new String[NumericConstants.THOUSAND];
							xflag = false;
	         			}
	         			else{

	         				a = a+1;
	         				xflag = true;


	         			}
	         			count= count + 1;
	         }

			
	         if(xflag){
					
	        	      if(memList.size()<NumericConstants.NINE_NINTY_NINE){
	 					count=0;
						while(count<memList.size()){
		            			Object o = (Object) memList.get(count);
		            			memberIdArraytemp[a]=(String)o;
		            			count= count + 1;
						}

						memberIdquery = " " + requiredColumnName + IN_BRACES+arrayToString2(memberIdArraytemp,",")+")";
	        	      }
	        	      else{
	        	    	  if(memList.size()==NumericConstants.NINE_NINTY_NINE){
	        	                               memberIdquery = "  " + requiredColumnName + IN_BRACES + arrayToString2(memberIdArraytemp, ",") + ")";
                                     } else {
                                         memberIdquery = memberIdquery + " or " + requiredColumnName + IN_BRACES + arrayToString2(memberIdArraytemp, ",") + ")";
                                     }
                                 }

                             }
                         } catch (Exception e) {
                             LOGGER.error(e);
                }
	    	return memberIdquery;
	    }
		
		/**
		 * 
		 *  @param list1 -contains the value to be in NOT IN query
		 * @param columnName - for which column this NOT IN query mapping
		 * @return - proper NOT IN query in String format
		 */
		 public static String getSplitInQueryNotInV2(@SuppressWarnings("rawtypes") List list1, String columnName){

		    	@SuppressWarnings("rawtypes")
				List memList= list1;
		    	String requiredColumnName = columnName;
		    	int count=0;
		        int a=0;
		        int subqueryNocount=0;

		        String memberIdArraytemp[] = new String[NumericConstants.THOUSAND];
		        String memberIdquery ="";
				 boolean xflag = true;
				 try{
				 while(count<memList.size()){
		         			Object o = (Object) memList.get(count);
		         			memberIdArraytemp[a]=(String)o;

		         			if(a==NumericConstants.NINE_NINTY_NINE){
		         				if(subqueryNocount==0){
		         				memberIdquery = " " + requiredColumnName + NOT_IN+arrayToString(memberIdArraytemp,",")+")";
		         				subqueryNocount = 1;
		         				}
		         				else{
		         					memberIdquery = memberIdquery + " and "+requiredColumnName+NOT_IN+arrayToString(memberIdArraytemp,",")+")";

		         				}
		         				a=0;
		         				memberIdArraytemp = new String[NumericConstants.THOUSAND];
								xflag = false;
		         			}
		         			else{
		         				a = a+1;
		         				xflag = true;


		         			}
		         			count= count + 1;
		         }

		         if(xflag){
						
		        	      if(memList.size()<NumericConstants.NINE_NINTY_NINE){
							count=0;
							while(count<memList.size()){
			            			Object o = (Object) memList.get(count);
			            			memberIdArraytemp[a]=(String)o;
			            			count= count + 1;
							}

							memberIdquery = " " + requiredColumnName + NOT_IN+arrayToString(memberIdArraytemp,",")+")";
		        	      }
		        	      else{
		        	    	  if(memList.size()==NumericConstants.NINE_NINTY_NINE){
		        	    		  memberIdquery = "  " + requiredColumnName + NOT_IN+arrayToString(memberIdArraytemp,",")+")";
		        	    	  }
		        	    	  else{
		        	    	  memberIdquery = memberIdquery + " and " + requiredColumnName + NOT_IN+arrayToString(memberIdArraytemp,",")+")";
		        	    	  }
		        	      }

					}
				 }catch(Exception e){
					 LOGGER.error(e);
				 }
		

		    	return memberIdquery;
		    }
		 
		 public static String arrayToString(String a[], String separator){
		        String result = "";
		                   if (a.length > 0) {
                         if (a[0] != null) {
                             result = "'" + a[0] + "'";    // start with the first element
                         }
                         for (int i = 1; i < a.length; i++) {
                             if (a[i] != null) {
                                 result = result + separator + "'" + a[i] + "'";
                             }
                         }
                     }
		        
		        return result;
		    }
		 
		  public static String arrayToString2(String a[], String separator){
		        String result = "";
		        if (a.length > 0) {
		        	
		            for (int i=0; i<a.length; i++) {
		            	if(a[i] != null){
		            		if(i == 0 ){
		            			result = a[i];
		            		}else{
		            			result = result + separator + a[i];
		            		}
		            	}
		            }
		        }
		        
		        return result;
		    }
		 
			/*
			 * @param aDate - The Date object to be converted to string
			 * @return String - date in string format in the format of "MM/dd/yyyy"
			 */
			public static final String convertDateTimeToString(Date aDate){
				return getDateTime(MMDDYYYYHHMMMSS, aDate);
			}
			
			/*
			 * @param strDate - input date in string format
			 * @return Date - The Date object of the strDate in the format of "MM/dd/yyyy"
			 */
			public static Date convertStringToDateTime(String strDate)throws  java.text.ParseException {
				Date aDate = null;
				                        if (strDate == null || strDate.equals(EMPTY) || strDate.equals(STRING_NULL)) {
                                return null;
                            }
                            aDate = convertStringToDate(MMDDYYYYHHMMMSS, strDate);
                            return aDate;
			}
	
}
