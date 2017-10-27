package com.stpl.app.contract.dashboard.util;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.contract.dashboard.logic.DashboardComponentSearchLogic;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.HelperDTO;
import java.util.List;

public class QueryUtil {
	
	/**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DashboardComponentSearchLogic.class);
	
    /**
     * To format date in GSK format
     */
    DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
	
    /**
     * To frame Contract query for component searchs 
     * @param searchComponentDTO
     * @param start
     * @param offset
     * @param filterMap
     * @param sortColumn
     * @param order
     * @param countFlag
     * @return Query String
     */
	public String frameContractComponentSearchQuery(CustomFieldGroup leftSearchBinder,int start,int offset,Map<String,Object> filterMap,String sortColumn,String order,boolean countFlag){
		
		LOGGER.debug("Inside Contract Component Search Query Util");
		
		StringBuilder query = new StringBuilder("");
		String wildCardValue="";
		if(!countFlag){
			query.append("select con.CONTRACT_MASTER_SID,con.CONTRACT_ID,con.CONTRACT_NO,con.CONTRACT_NAME,ht.DESCRIPTION as contractType,con.START_DATE,con.END_DATE from CONTRACT_MASTER con"
				+" Left Join COMPANY_MASTER cm on cm.COMPANY_MASTER_SID=con.CONT_HOLD_COMPANY_MASTER_SID"
				+" Left Join HELPER_TABLE ht on ht.HELPER_TABLE_SID=con.CONTRACT_TYPE where con.INBOUND_STATUS <> 'D'");
		}else{
			query.append("select count(*) from CONTRACT_MASTER con"
					+" Left Join COMPANY_MASTER cm on cm.COMPANY_MASTER_SID=con.CONT_HOLD_COMPANY_MASTER_SID"
					+" Left Join HELPER_TABLE ht on ht.HELPER_TABLE_SID=con.CONTRACT_TYPE where con.INBOUND_STATUS <> 'D'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_ID).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_ID).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_ID).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND con.CONTRACT_ID like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_NO).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_NO).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND con.CONTRACT_NO like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_NAME).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_NAME).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND con.CONTRACT_NAME like '"+wildCardValue+"'");
		}
		
		if(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_TYPE).getValue()!=null && ((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_TYPE).getValue()).getId()!=0){
			query.append(" AND con.CONTRACT_TYPE ="+((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_TYPE).getValue()).getId());
		}
		
		if(leftSearchBinder.getField("leftContractStartDate").getValue()!=null){
			wildCardValue=dateFormatter.format((Date)leftSearchBinder.getField("leftContractStartDate").getValue());
			query.append(" AND con.START_DATE ='"+wildCardValue+"'");
		}
		
		if(leftSearchBinder.getField("leftContractEndDate").getValue()!=null){
			wildCardValue=dateFormatter.format((Date)leftSearchBinder.getField("leftContractEndDate").getValue());
			query.append(" AND con.END_DATE ='"+wildCardValue+"'");
		}
				
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_HOLDER_NO).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_HOLDER_NO).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_HOLDER_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cm.COMPANY_NO like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_HOLDER_NAME).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_HOLDER_NAME).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CONTRACT_HOLDER_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cm.COMPANY_NAME like '"+wildCardValue+"'");
		}
                
                if(filterMap.get(ConstantUtil.MEMBER_ID)!=null){
                    wildCardValue=String.valueOf(filterMap.get(ConstantUtil.MEMBER_ID));
                    query.append(" AND con.CONTRACT_ID like '"+wildCardValue+"'");
                }
                
                if(filterMap.get(ConstantUtil.MEMBER_NO)!=null){
			wildCardValue=String.valueOf(filterMap.get(ConstantUtil.MEMBER_NO));
			query.append(" AND con.CONTRACT_NO like '"+wildCardValue+"'");
		}
		
		if(filterMap.get("name")!=null){
			wildCardValue=String.valueOf(filterMap.get("name"));
			query.append(" AND con.CONTRACT_NAME like '"+wildCardValue+"'");
		}
		
		if(filterMap.get(ConstantUtil.MEMBER_TYPE)!=null){
                    query.append(" AND con.CONTRACT_TYPE ="+String.valueOf(filterMap.get(ConstantUtil.MEMBER_TYPE)));
		}
		
		if(!countFlag){
			query.append(ConstantUtil.ORDER_BY + sortColumn + " " + order + ConstantUtil.OFFSET + start + ConstantUtil.ROWS_FETCH_NEXT + offset + ConstantUtil.ROWS_ONLY);
            }
		
		LOGGER.debug("Exits Contract Component Search Query Util");
		
		return query.toString();
	}
	
	/**
	 * To frame CFP query for component search
	 * @param searchComponentDTO
	 * @param start
	 * @param offset
	 * @param filterMap
	 * @param sortColumn
	 * @param order
	 * @param countFlag
	 * @return Query String
	 */
	public String frameCFPComponentSearchQuery(CustomFieldGroup leftSearchBinder,int start,int offset,Map<String,Object> filterMap,String sortColumn,String order,boolean countFlag){
		
		LOGGER.debug("Inside CFP Component Search Query Util");
		
		StringBuilder query = new StringBuilder("");
		String wildCardValue="";
		if(!countFlag){
			query.append("select DISTINCT cf.CFP_MODEL_SID,cf.CFP_ID,cf.CFP_NO,cf.CFP_NAME,ht.DESCRIPTION as cfpType, cf.CFP_START_DATE,cf.CFP_END_DATE from CFP_MODEL cf "
						+"	Join CFP_DETAILS cfd on cfd.CFP_MODEL_SID=cf.CFP_MODEL_SID"
						+"	Join COMPANY_MASTER cm on cm.COMPANY_MASTER_SID=cfd.COMPANY_MASTER_SID"
						+"	Left Join Helper_Table ht on ht.HELPER_TABLE_SID=cf.CFP_TYPE"
                                                +"	Left Join Helper_Table htcat on htcat.HELPER_TABLE_SID = cm.COMPANY_CATEGORY"
						+"	where cf.INBOUND_STATUS <> 'D'");
		}else{
			query.append("select count(DISTINCT cf.CFP_MODEL_SID) from CFP_MODEL cf"
						+"	Join CFP_DETAILS cfd on cfd.CFP_MODEL_SID=cf.CFP_MODEL_SID"
						+"	Join COMPANY_MASTER cm on cm.COMPANY_MASTER_SID=cfd.COMPANY_MASTER_SID"
						+"	Left Join Helper_Table ht on ht.HELPER_TABLE_SID=cf.CFP_TYPE"
						+"	Left Join Helper_Table htcat on htcat.HELPER_TABLE_SID = cm.COMPANY_CATEGORY"
						+"	where cf.INBOUND_STATUS <> 'D'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CFP_ID).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CFP_ID).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CFP_ID).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cf.CFP_ID like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CFP_NO).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CFP_NO).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CFP_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cf.CFP_NO like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CFP_NAME).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CFP_NAME).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_CFP_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cf.CFP_NAME like '"+wildCardValue+"'");
		}
		
		if(leftSearchBinder.getField(ConstantUtil.LEFT_CFP_TYPE).getValue()!=null && ((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_CFP_TYPE).getValue()).getId()!=0){
			query.append(" AND cf.CFP_TYPE ="+((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_CFP_TYPE).getValue()).getId());
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_COMPANY_NO).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_COMPANY_NO).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_COMPANY_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cm.COMPANY_NO like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_COMPANY_NAME).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_COMPANY_NAME).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_COMPANY_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cm.COMPANY_NAME like '"+wildCardValue+"'");
		}
		
		if(leftSearchBinder.getField(ConstantUtil.LEFT_COMPANY_TYPE).getValue()!=null && ((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_COMPANY_TYPE).getValue()).getId()!=0){
			query.append(" AND cm.COMPANY_TYPE ="+((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_COMPANY_TYPE).getValue()).getId());
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_COMPANY_CATEGORY).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_COMPANY_CATEGORY).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_COMPANY_CATEGORY).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND htcat.DESCRIPTION like '"+wildCardValue+"'");
		}
                
                 if(filterMap.get(ConstantUtil.MEMBER_ID)!=null){
                    wildCardValue=String.valueOf(filterMap.get(ConstantUtil.MEMBER_ID));
                    query.append(" AND cf.CFP_ID like '"+wildCardValue+"'");
                }
                
                if(filterMap.get(ConstantUtil.MEMBER_NO)!=null){
			wildCardValue=String.valueOf(filterMap.get(ConstantUtil.MEMBER_NO));
			query.append(" AND cf.CFP_NO like '"+wildCardValue+"'");
		}
		
		if(filterMap.get("name")!=null){
			wildCardValue=String.valueOf(filterMap.get("name"));
			query.append(" AND cf.CFP_NAME like '"+wildCardValue+"'");
		}
		
		if(filterMap.get(ConstantUtil.MEMBER_TYPE)!=null){
                    
			query.append(" AND cf.CFP_TYPE ="+String.valueOf(filterMap.get(ConstantUtil.MEMBER_TYPE)));
		}
		
		if(!countFlag){
			query.append(ConstantUtil.ORDER_BY + sortColumn + " " + order + ConstantUtil.OFFSET + start + ConstantUtil.ROWS_FETCH_NEXT + offset + ConstantUtil.ROWS_ONLY);
            }
		
		LOGGER.debug("Exits CFP Component Search Query Util");
		
		return query.toString();
	}
	
	/**
	 * To frame IFP query for component search
	 * @param searchComponentDTO
	 * @param start
	 * @param offset
	 * @param filterMap
	 * @param sortColumn
	 * @param order
	 * @param countFlag
	 * @return Query String
	 */
	public String frameIFPComponentSearchQuery(CustomFieldGroup leftSearchBinder,int start,int offset,Map<String,Object> filterMap,String sortColumn,String order,boolean countFlag){
		
		LOGGER.debug("Inside IFP Component Search Query Util");
		
		StringBuilder query = new StringBuilder("");
		String wildCardValue="";
		if(!countFlag){
			query.append("select DISTINCT ifm.IFP_MODEL_SID,ifm.IFP_ID,ifm.IFP_NO,ifm.IFP_NAME,ht.DESCRIPTION as ifpType, ifm.IFP_START_DATE, ifm.IFP_END_DATE from IFP_MODEL ifm"
						+" Join IFP_DETAILS ifd on ifd.IFP_MODEL_SID=ifm.IFP_MODEL_SID"
						+" Join ITEM_MASTER im on im.ITEM_MASTER_SID=ifd.ITEM_MASTER_SID "
						+" Left Join HELPER_TABLE ht on ht.HELPER_TABLE_SID=ifm.IFP_TYPE"
						+" where ifm.INBOUND_STATUS <> 'D'");
		}else{
			query.append("select count(DISTINCT ifm.IFP_MODEL_SID) from IFP_MODEL ifm"
					+" Join IFP_DETAILS ifd on ifd.IFP_MODEL_SID=ifm.IFP_MODEL_SID"
					+" Join ITEM_MASTER im on im.ITEM_MASTER_SID=ifd.ITEM_MASTER_SID"
					+" Left Join HELPER_TABLE ht on ht.HELPER_TABLE_SID=ifm.IFP_TYPE"
					+" where ifm.INBOUND_STATUS <> 'D'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_ID).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_ID).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_ID).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND ifm.IFP_ID like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_NO).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_NO).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND ifm.IFP_NO like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_NAME).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_NAME).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND ifm.IFP_NAME like '"+wildCardValue+"'");
		}
		
		if(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_TYPE).getValue()!=null && ((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_IFP_TYPE).getValue()).getId()!=0){
			query.append(" AND ifm.IFP_TYPE ="+((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_IFP_TYPE).getValue()).getId());
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_ITEM_NO).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_ITEM_NO).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_ITEM_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(ConstantUtil.AND_IM_ITEM_NO_LIKE+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_ITEM_NAME).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_ITEM_NAME).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_ITEM_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(ConstantUtil.AND_IM_ITEM_NAME_LIKE+wildCardValue+"'");
		}
		
		if(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_THERAPEUTIC_CLASS).getValue()!=null && ((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_IFP_THERAPEUTIC_CLASS).getValue()).getId()!=0){
			query.append(" AND im.THERAPEUTIC_CLASS ="+((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_IFP_THERAPEUTIC_CLASS).getValue()).getId());
		}
		
		if(leftSearchBinder.getField(ConstantUtil.LEFT_IFP_BRAND_NAME).getValue()!=null && ((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_IFP_BRAND_NAME).getValue()).getId()!=0){
			query.append(" AND im.BRAND_MASTER_SID = "+((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_IFP_BRAND_NAME).getValue()).getId());
		}
                
                if(filterMap.get(ConstantUtil.MEMBER_ID)!=null){
                    wildCardValue=String.valueOf(filterMap.get(ConstantUtil.MEMBER_ID));
                    query.append(" AND ifm.IFP_ID like '"+wildCardValue+"'");
                }
                
                if(filterMap.get(ConstantUtil.MEMBER_NO)!=null){
			wildCardValue=String.valueOf(filterMap.get(ConstantUtil.MEMBER_NO));
			query.append(" AND ifm.IFP_NO like '"+wildCardValue+"'");
		}
		
		if(filterMap.get("name")!=null){
			wildCardValue=String.valueOf(filterMap.get("name"));
			query.append(" AND ifm.IFP_NAME like '"+wildCardValue+"'");
		}
		
		if(filterMap.get(ConstantUtil.MEMBER_TYPE)!=null){
                    
			query.append(" AND ifm.IFP_TYPE ="+String.valueOf(filterMap.get(ConstantUtil.MEMBER_TYPE)));
		}
		
		if(!countFlag){
			query.append(ConstantUtil.ORDER_BY + sortColumn + " " + order + ConstantUtil.OFFSET + start + ConstantUtil.ROWS_FETCH_NEXT + offset + ConstantUtil.ROWS_ONLY);
            }
		
		LOGGER.debug("Exits IFP Component Search Query Util");
		
		return query.toString();
	}
	
	
	/**
	 * To frame PS query for component search
	 * @param searchComponentDTO
	 * @param start
	 * @param offset
	 * @param filterMap
	 * @param sortColumn
	 * @param order
	 * @param countFlag
	 * @return Query String
	 */
	public String framePSComponentSearchQuery(CustomFieldGroup leftSearchBinder,int start,int offset,Map<String,Object> filterMap,String sortColumn,String order,boolean countFlag){
		
		LOGGER.debug("Inside PS Component Search Query Util");
		
		StringBuilder query = new StringBuilder("");
		String wildCardValue="";
		if(!countFlag){
			query.append("select DISTINCT ps.PS_MODEL_SID,ps.PS_ID,ps.PS_NO,ps.PS_NAME,ht.DESCRIPTION as psType, ps.PS_START_DATE,ps.PS_END_DATE from PS_MODEL ps"
						+" Join PS_DETAILS psd on psd.PS_MODEL_SID=ps.PS_MODEL_SID"
						+" Join ITEM_MASTER im on im.ITEM_MASTER_SID=psd.ITEM_MASTER_SID "
						+" Left Join HELPER_TABLE ht on ht.HELPER_TABLE_SID=ps.PS_TYPE"
						+" where ps.INBOUND_STATUS <> 'D'");
		}else{
			query.append("select count(DISTINCT ps.PS_MODEL_SID) from PS_MODEL ps"
						+" Join PS_DETAILS psd on psd.PS_MODEL_SID=ps.PS_MODEL_SID"
						+" Join ITEM_MASTER im on im.ITEM_MASTER_SID=psd.ITEM_MASTER_SID"
						+" Left Join HELPER_TABLE ht on ht.HELPER_TABLE_SID=ps.PS_TYPE"
						+" where ps.INBOUND_STATUS <> 'D'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_ID).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_ID).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_ID).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND ps.PS_ID like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_NO).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_NO).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND ps.PS_NO like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_NAME).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_NAME).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND ps.PS_NAME like '"+wildCardValue+"'");
		}
		
		if(leftSearchBinder.getField(ConstantUtil.LEFT_PS_TYPE).getValue()!=null && ((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_PS_TYPE).getValue()).getId()!=0){
			query.append(" AND ps.PS_TYPE ="+((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_PS_TYPE).getValue()).getId());
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_ITEM_NO).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_ITEM_NO).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_ITEM_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(ConstantUtil.AND_IM_ITEM_NO_LIKE+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_ITEM_NAME).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_ITEM_NAME).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_PS_ITEM_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(ConstantUtil.AND_IM_ITEM_NAME_LIKE+wildCardValue+"'");
		}
		
		if(leftSearchBinder.getField(ConstantUtil.LEFT_PS_THERAPEUTIC_CLASS).getValue()!=null && ((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_PS_THERAPEUTIC_CLASS).getValue()).getId()!=0){
			query.append(" AND im.THERAPEUTIC_CLASS ="+((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_PS_THERAPEUTIC_CLASS).getValue()).getId());
		}
		
		if(leftSearchBinder.getField(ConstantUtil.LEFT_PS_BRAND_NAME).getValue()!=null && ((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_PS_BRAND_NAME).getValue()).getId()!=0){
			query.append(" AND im.BRAND_MASTER_SID ="+((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_PS_BRAND_NAME).getValue()).getId());
		}
                
                if(filterMap.get(ConstantUtil.MEMBER_ID)!=null){
                    wildCardValue=String.valueOf(filterMap.get(ConstantUtil.MEMBER_ID));
                    query.append(" AND ps.PS_ID like '"+wildCardValue+"'");
                }
                
                if(filterMap.get(ConstantUtil.MEMBER_NO)!=null){
			wildCardValue=String.valueOf(filterMap.get(ConstantUtil.MEMBER_NO));
			query.append(" AND ps.PS_NO like '"+wildCardValue+"'");
		}
		
		if(filterMap.get("name")!=null){
			wildCardValue=String.valueOf(filterMap.get("name"));
			query.append(" AND ps.PS_NAME like '"+wildCardValue+"'");
		}
		
		if(filterMap.get(ConstantUtil.MEMBER_TYPE)!=null){
                    
			query.append(" AND ps.PS_TYPE ="+String.valueOf(filterMap.get(ConstantUtil.MEMBER_TYPE)));
		}
                
		
		if(!countFlag){
			query.append(ConstantUtil.ORDER_BY + sortColumn + " " + order + ConstantUtil.OFFSET + start + ConstantUtil.ROWS_FETCH_NEXT + offset + ConstantUtil.ROWS_ONLY);
            }
		
		LOGGER.debug("Exits PS Component Search Query Util");
		
		return query.toString();
	}
	
	/**
	 * To frame RS query for component search
	 * @param searchComponentDTO
	 * @param start
	 * @param offset
	 * @param filterMap
	 * @param sortColumn
	 * @param order
	 * @param countFlag
	 * @return Query String
	 */
	public String frameRSComponentSearchQuery(CustomFieldGroup leftSearchBinder,int start,int offset,Map<String,Object> filterMap,String sortColumn,String order,boolean countFlag){
		
		LOGGER.debug("Inside RS Component Search Query Util");
		
		StringBuilder query = new StringBuilder("");
		String wildCardValue="";
		if(!countFlag){
			query.append("select DISTINCT rsm.RS_MODEL_SID,rsm.RS_ID,rsm.RS_NO,rsm.RS_NAME,ht.DESCRIPTION as rsType, rsm.RS_START_DATE,rsm.RS_END_DATE from RS_MODEL rsm"
						+" Join RS_DETAILS rsd on rsd.RS_MODEL_SID=rsm.RS_MODEL_SID"
						+" Join ITEM_MASTER im on im.ITEM_MASTER_SID=rsd.ITEM_MASTER_SID "
						+" Left Join HELPER_TABLE ht on ht.HELPER_TABLE_SID=rsm.RS_TYPE"
						+" where rsm.INBOUND_STATUS <> 'D'");
		}else{
			query.append("select count(DISTINCT rsm.RS_MODEL_SID) from RS_MODEL rsm"
					+" Join RS_DETAILS rsd on rsd.RS_MODEL_SID=rsm.RS_MODEL_SID"
					+" Join ITEM_MASTER im on im.ITEM_MASTER_SID=rsd.ITEM_MASTER_SID"
					+" Left Join HELPER_TABLE ht on ht.HELPER_TABLE_SID=rsm.RS_TYPE"
					+" where rsm.INBOUND_STATUS <> 'D'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_ID).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_ID).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_ID).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND rsm.RS_ID like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_NO).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_NO).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND rsm.RS_NO like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_NAME).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_NAME).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND rsm.RS_NAME like '"+wildCardValue+"'");
		}
		
		if(leftSearchBinder.getField(ConstantUtil.LEFT_RS_TYPE).getValue()!=null && ((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_RS_TYPE).getValue()).getId()!=0){
			query.append(" AND rsm.RS_TYPE ="+((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_RS_TYPE).getValue()).getId());
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_ITEM_NO).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_ITEM_NO).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_ITEM_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(ConstantUtil.AND_IM_ITEM_NO_LIKE+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_ITEM_NAME).getValue()))&& !"null".equals(String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_ITEM_NAME).getValue()))){
			wildCardValue=String.valueOf(leftSearchBinder.getField(ConstantUtil.LEFT_RS_ITEM_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(ConstantUtil.AND_IM_ITEM_NAME_LIKE+wildCardValue+"'");
		}
		
		if(leftSearchBinder.getField(ConstantUtil.LEFT_RS_BRAND_NAME).getValue()!=null && ((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_RS_BRAND_NAME).getValue()).getId()!=0){
			query.append(" AND im.BRAND_MASTER_SID ="+((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_RS_BRAND_NAME).getValue()).getId());
		}
                
                if(leftSearchBinder.getField(ConstantUtil.LEFT_RS_PROGRAM_CATEGORY).getValue()!=null && ((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_RS_PROGRAM_CATEGORY).getValue()).getId()!=0){
			query.append(" AND rsm.RS_MODEL_SID in (select distinct master_sid from udcs where master_type='RS_MODEL' and udc2="+((HelperDTO)leftSearchBinder.getField(ConstantUtil.LEFT_RS_PROGRAM_CATEGORY).getValue()).getId()+")");
		}
		
                if(filterMap.get(ConstantUtil.MEMBER_ID)!=null){
                    wildCardValue=String.valueOf(filterMap.get(ConstantUtil.MEMBER_ID));
                    query.append(" AND rsm.RS_ID like '"+wildCardValue+"'");
                }
                
                if(filterMap.get(ConstantUtil.MEMBER_NO)!=null){
			wildCardValue=String.valueOf(filterMap.get(ConstantUtil.MEMBER_NO));
			query.append(" AND rsm.RS_NO like '"+wildCardValue+"'");
		}
		
		if(filterMap.get("name")!=null){
			wildCardValue=String.valueOf(filterMap.get("name"));
			query.append(" AND rsm.RS_NAME like '"+wildCardValue+"'");
		}
		
		if(filterMap.get(ConstantUtil.MEMBER_TYPE)!=null){
                    query.append(" AND rsm.RS_TYPE ="+String.valueOf(filterMap.get(ConstantUtil.MEMBER_TYPE)));
		}
                
		if(!countFlag){
			query.append(ConstantUtil.ORDER_BY + sortColumn + " " + order + ConstantUtil.OFFSET + start + ConstantUtil.ROWS_FETCH_NEXT + offset + ConstantUtil.ROWS_ONLY);
            }
		
		LOGGER.debug("Exits RS Component Search Query Util");
		
		return query.toString();
	}
	
	/**
	 * To frame Right Component search
	 * @param searchComponentDTO
	 * @return
	 */
	public String getRightContractComponentSearch(CustomFieldGroup rightSearchBinder,int start,int offset,boolean countFlag){
		LOGGER.debug("Inside Right Contract Component Search Query Util");
		
		StringBuilder query = new StringBuilder("");
		String wildCardValue="";
		
                if(countFlag){
                    query.append("select count(*) from ( select DISTINCT cm.CONTRACT_MASTER_SID,cm.CONTRACT_ID,cm.CONTRACT_NO,cm.CONTRACT_NAME,cm.START_DATE,cm.END_DATE  from dbo.CONTRACT_MASTER cm"
					+"	Left Join dbo.CFP_CONTRACT cfc on cfc.CONTRACT_MASTER_SID=cm.CONTRACT_MASTER_SID and cfc.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.CFP_CONTRACT_DETAILS cfd on cfd.CFP_CONTRACT_SID=cfc.CFP_CONTRACT_SID and cfd.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.IFP_CONTRACT ifc on ifc.CONTRACT_MASTER_SID=cm.CONTRACT_MASTER_SID and ifc.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.IFP_CONTRACT_DETAILS ifd on ifd.IFP_CONTRACT_SID=ifc.IFP_CONTRACT_SID and ifd.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.PS_CONTRACT psc on psc.CONTRACT_MASTER_SID=cm.CONTRACT_MASTER_SID and psc.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.PS_CONTRACT_DETAILS psd on psd.PS_CONTRACT_SID=psc.PS_CONTRACT_SID and psd.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.RS_CONTRACT rsc on rsc.CONTRACT_MASTER_SID=cm.CONTRACT_MASTER_SID and rsc.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.RS_CONTRACT_DETAILS rsd on rsd.RS_CONTRACT_SID=rsc.RS_CONTRACT_SID and rsd.INBOUND_STATUS <> 'D'"
                                        +"      Left Join COMPANY_MASTER com on com.COMPANY_MASTER_SID=cm.CONT_HOLD_COMPANY_MASTER_SID"
                                        +"      Left Join COMPANY_MASTER cfpcomp on cfpcomp.COMPANY_MASTER_SID=cfd.COMPANY_MASTER_SID"
                                        +"      Left Join ITEM_MASTER ifpIm on ifpIm.ITEM_MASTER_SID=ifd.ITEM_MASTER_SID"
                                        +"      Left Join ITEM_MASTER psIm on psIm.ITEM_MASTER_SID=psd.ITEM_MASTER_SID"
                                        +"      Left Join ITEM_MASTER rsIm on rsIm.ITEM_MASTER_SID=rsd.ITEM_MASTER_SID"
					+"	where cm.PROCESS_STATUS = 1");
                }else{
                
                    query.append("select DISTINCT cm.CONTRACT_MASTER_SID,cm.CONTRACT_ID,cm.CONTRACT_NO,cm.CONTRACT_NAME,cm.START_DATE,cm.END_DATE  from dbo.CONTRACT_MASTER cm"
					+"	Left Join dbo.CFP_CONTRACT cfc on cfc.CONTRACT_MASTER_SID=cm.CONTRACT_MASTER_SID and cfc.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.CFP_CONTRACT_DETAILS cfd on cfd.CFP_CONTRACT_SID=cfc.CFP_CONTRACT_SID and cfd.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.IFP_CONTRACT ifc on ifc.CONTRACT_MASTER_SID=cm.CONTRACT_MASTER_SID and ifc.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.IFP_CONTRACT_DETAILS ifd on ifd.IFP_CONTRACT_SID=ifc.IFP_CONTRACT_SID and ifd.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.PS_CONTRACT psc on psc.CONTRACT_MASTER_SID=cm.CONTRACT_MASTER_SID and psc.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.PS_CONTRACT_DETAILS psd on psd.PS_CONTRACT_SID=psc.PS_CONTRACT_SID and psd.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.RS_CONTRACT rsc on rsc.CONTRACT_MASTER_SID=cm.CONTRACT_MASTER_SID and rsc.INBOUND_STATUS <> 'D'"
					+"	Left Join dbo.RS_CONTRACT_DETAILS rsd on rsd.RS_CONTRACT_SID=rsc.RS_CONTRACT_SID and rsd.INBOUND_STATUS <> 'D'"
                                        +"      Left Join COMPANY_MASTER com on com.COMPANY_MASTER_SID=cm.CONT_HOLD_COMPANY_MASTER_SID"
                                        +"      Left Join COMPANY_MASTER cfpcomp on cfpcomp.COMPANY_MASTER_SID=cfd.COMPANY_MASTER_SID"
                                        +"      Left Join ITEM_MASTER ifpIm on ifpIm.ITEM_MASTER_SID=ifd.ITEM_MASTER_SID"
                                        +"      Left Join ITEM_MASTER psIm on psIm.ITEM_MASTER_SID=psd.ITEM_MASTER_SID"
                                        +"      Left Join ITEM_MASTER rsIm on rsIm.ITEM_MASTER_SID=rsd.ITEM_MASTER_SID"
					+"	where cm.PROCESS_STATUS = 1");
                }
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_ID).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_ID).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_ID).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cm.CONTRACT_ID like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_NO).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_NO).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cm.CONTRACT_NO like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_NAME).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_NAME).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cm.CONTRACT_NAME like '"+wildCardValue+"'");
		}
		
		if(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_TYPE).getValue()!=null && ((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_TYPE).getValue()).getId()!=0){
			query.append(" AND cm.CONTRACT_TYPE ="+((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_TYPE).getValue()).getId());
		}
		
		if(rightSearchBinder.getField("rightContractStartDate").getValue()!=null){
			wildCardValue=dateFormatter.format((Date)rightSearchBinder.getField("rightContractStartDate").getValue());
			query.append(" AND cm.START_DATE ='"+wildCardValue+"'");
		}
		
		if(rightSearchBinder.getField("rightContractEndDate").getValue()!=null){
			wildCardValue=dateFormatter.format((Date)rightSearchBinder.getField("rightContractEndDate").getValue());
			query.append(" AND cm.END_DATE ='"+wildCardValue+"'");
		}
				
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_HOLDER_NO).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_HOLDER_NO).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_HOLDER_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND com.COMPANY_NO like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_HOLDER_NAME).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_HOLDER_NAME).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CONTRACT_HOLDER_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND com.COMPANY_NAME like '"+wildCardValue+"'");
		}
                
                
                if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CFP_ID).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CFP_ID).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CFP_ID).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cfc.CFP_MODEL_SID in (select cfid.CFP_MODEL_SID from CFP_MODEL cfid where cfid.CFP_ID like '"+wildCardValue+"')");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CFP_NO).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CFP_NO).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CFP_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cfc.CFP_MODEL_SID in (select cfno.CFP_MODEL_SID from CFP_MODEL cfno where cfno.CFP_NO like '"+wildCardValue+"')");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CFP_NAME).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CFP_NAME).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_CFP_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cfc.CFP_NAME like '"+wildCardValue+"'");
		}
		
		if(rightSearchBinder.getField(ConstantUtil.RIGHT_CFP_TYPE).getValue()!=null && ((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_CFP_TYPE).getValue()).getId()!=0){
			query.append(" AND cfc.CFP_TYPE ="+((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_CFP_TYPE).getValue()).getId());
		}
		
                if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_ID).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_ID).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_ID).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cfpcomp.COMPANY_ID like '"+wildCardValue+"'");
		}
                
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_NO).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_NO).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND cfpcomp.COMPANY_NO like '"+wildCardValue+"'");
		}
		
		          if (StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_NAME).getValue())) && !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_NAME).getValue()))) {
                wildCardValue = String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
                query.append(" AND cfpcomp.COMPANY_NAME like '" + wildCardValue + "'");
            }

            if (rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_TYPE).getValue() != null && ((HelperDTO) rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_TYPE).getValue()).getId() != 0) {

                final HelperDTO helperDTO = (HelperDTO) rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_TYPE).getValue();

                if (helperDTO != null && StringUtils.isNotBlank(helperDTO.getDescription())) {

                    wildCardValue = String.valueOf(helperDTO.getId());

                    query.append(" AND cfpcomp.COMPANY_TYPE like '" + wildCardValue + "'");
                }
            }

            if (StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_CATEGORY).getValue())) && !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_CATEGORY).getValue()))) {
                String sql = "select HELPER_TABLE_SID from dbo.HELPER_TABLE where DESCRIPTION like " + "'" + String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_COMPANY_CATEGORY).getValue()) + "'";
                List helperid = HelperTableLocalServiceUtil.executeSelectQuery(sql);
                query.append(" AND cfpcomp.COMPANY_CATEGORY like '" + helperid.get(0) + "'");
            }
                
                if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_ID).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_ID).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_ID).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND ifc.IFP_MODEL_SID in (select ifid.IFP_MODEL_SID from IFP_MODEL ifid where ifid.IFP_ID like '"+wildCardValue+"')");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_NO).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_NO).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND ifc.IFP_MODEL_SID in (select ifno.IFP_MODEL_SID from IFP_MODEL ifno where ifno.IFP_NO like '"+wildCardValue+"')");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_NAME).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_NAME).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND ifc.IFP_NAME like '"+wildCardValue+"'");
		}
		
		if(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_TYPE).getValue()!=null && ((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_TYPE).getValue()).getId()!=0){
			query.append(" AND ifc.IFP_TYPE ="+((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_TYPE).getValue()).getId());
		}
		
                if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_ITEM_ID).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_ITEM_ID).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_ITEM_ID).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND ifpIm.ITEM_ID like '"+wildCardValue+"'");
		}
                
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_ITEM_NO).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_ITEM_NO).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_ITEM_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND ifpIm.ITEM_NO like '"+wildCardValue+"'");
		}
		
                if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_ITEM_NAME).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_ITEM_NAME).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_ITEM_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND ifpIm.ITEM_NAME like '"+wildCardValue+"'");
		}
                
		if(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_THERAPEUTIC_CLASS).getValue()!=null && ((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_THERAPEUTIC_CLASS).getValue()).getId()!=0){
			query.append(" AND ifpIm.THERAPEUTIC_CLASS ="+((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_THERAPEUTIC_CLASS).getValue()).getId());
		}
		
		if(rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_BRAND_NAME).getValue()!=null && ((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_BRAND_NAME).getValue()).getId()!=0){
			query.append(" AND ifpIm.BRAND_MASTER_SID ="+((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_IFP_BRAND_NAME).getValue()).getId());
		}
                
                if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_ID).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_ID).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_ID).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND psc.PS_MODEL_SID in (select psid.PS_MODEL_SID from PS_MODEL psid where psid.PS_ID like '"+wildCardValue+"')");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_NO).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_NO).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND psc.PS_MODEL_SID in (select psno.PS_MODEL_SID from PS_MODEL psno where psno.PS_ID like '"+wildCardValue+"')");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_NAME).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_NAME).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND psc.PS_NAME like '"+wildCardValue+"'");
		}
		
		if(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_TYPE).getValue()!=null && ((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_PS_TYPE).getValue()).getId()!=0){
			query.append(" AND psc.PS_TYPE ="+((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_PS_TYPE).getValue()).getId());
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_ITEM_NO).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_ITEM_NO).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_ITEM_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND psIm.ITEM_NO like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_ITEM_NAME).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_ITEM_NAME).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_ITEM_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND psIm.ITEM_NAME like '"+wildCardValue+"'");
		}
		
		if(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_THERAPEUTIC_CLASS).getValue()!=null && ((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_PS_THERAPEUTIC_CLASS).getValue()).getId()!=0){
			query.append(" AND psIm.THERAPEUTIC_CLASS ="+((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_PS_THERAPEUTIC_CLASS).getValue()).getId());
		}
		
		if(rightSearchBinder.getField(ConstantUtil.RIGHT_PS_BRAND_NAME).getValue()!=null && ((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_PS_BRAND_NAME).getValue()).getId()!=0){
			query.append(" AND psIm.BRAND_MASTER_SID ="+((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_PS_BRAND_NAME).getValue()).getId());
		}
		
                if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_ID).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_ID).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_ID).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND rsc.RS_ID like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_NO).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_NO).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND rsc.RS_NO like '"+wildCardValue+"'");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_NAME).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_NAME).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND rsc.RS_NAME like '"+wildCardValue+"'");
		}
		
		if(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_TYPE).getValue()!=null && ((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_RS_TYPE).getValue()).getId()!=0){
			query.append(" AND rsc.RS_TYPE ="+((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_RS_TYPE).getValue()).getId());
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_ITEM_NO).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_ITEM_NO).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_ITEM_NO).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND rsd.ITEM_MASTER_SID in (select rsdimid.ITEM_MASTER_SID from ITEM_MASTER rsdimid where rsdimid.ITEM_ID like '"+wildCardValue+"')");
		}
		
		if(StringUtils.isNotBlank(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_ITEM_NAME).getValue()))&& !"null".equals(String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_ITEM_NAME).getValue()))){
			wildCardValue=String.valueOf(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_ITEM_NAME).getValue()).replace(Constants.CHAR_ASTERISK, Constants.CHAR_PERCENT);
			query.append(" AND rsd.ITEM_MASTER_SID in (select rsdimid.ITEM_MASTER_SID from ITEM_MASTER rsdimid where rsdimid.ITEM_NAME like '"+wildCardValue+"')");
		}
		
		if(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_BRAND_NAME).getValue()!=null && ((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_RS_BRAND_NAME).getValue()).getId()!=0){
			query.append(" AND rsd.ITEM_MASTER_SID in (select rsdBrnd.ITEM_MASTER_SID from ITEM_MASTER rsdBrnd where rsdBrnd.BRAND_MASTER_SID ="+((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_RS_BRAND_NAME).getValue()).getId()+")");
		}
                
                if(rightSearchBinder.getField(ConstantUtil.RIGHT_RS_PROGRAM_CATEGORY).getValue()!=null && ((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_RS_PROGRAM_CATEGORY).getValue()).getId()!=0){
			query.append(" AND rsc.RS_CONTRACT_SID in (select distinct master_sid from udcs where master_type='RS_CONTRACT' and udc2="+((HelperDTO)rightSearchBinder.getField(ConstantUtil.RIGHT_RS_PROGRAM_CATEGORY).getValue()).getId()+")");
		}
                
                if(!countFlag && start!=0 && offset!=0){
			query.append(ConstantUtil.ORDER_BY + "cm.CONTRACT_MASTER_SID" + " " + "ASC" + ConstantUtil.OFFSET + start + ConstantUtil.ROWS_FETCH_NEXT + offset + ConstantUtil.ROWS_ONLY);
                 }else if(countFlag){
                    query.append(") a");
                }
                LOGGER.debug("Exits Right Contract Component Search Query Util");
                
		return query.toString();
	}
	
	public String getCFPDetails(int modelSid,int start,int offset,boolean countFlag,Map<String,Object> filterMap){
            LOGGER.debug("Inside CFP Detail Component Search Query Util");
		StringBuilder query = new StringBuilder("");
                if(countFlag){
                    query.append("select count(cfd.CFP_DETAILS_SID)");
                }else{
                    query.append("select cm.COMPANY_NO,cm.COMPANY_NAME,htType.DESCRIPTION as companyType,htCategory.DESCRIPTION as category,htStatus.DESCRIPTION as status");
                }        
                    query.append("  from CFP_DETAILS cfd " +
                                " Join CFP_MODEL cf on cf.CFP_MODEL_SID=cfd.CFP_MODEL_SID" +
                                " Join COMPANY_MASTER cm on cm.COMPANY_MASTER_SID=cfd.COMPANY_MASTER_SID" +
                                " Left Join Helper_Table htType on htType.HELPER_TABLE_SID=cm.COMPANY_TYPE" +
                                " Left Join Helper_Table htCategory on htCategory.HELPER_TABLE_SID=cm.COMPANY_CATEGORY" +
                                " Left Join Helper_Table htStatus on htStatus.HELPER_TABLE_SID=cf.CFP_STATUS" +
                                " where cfd.INBOUND_STATUS <> 'D' and  cfd.CFP_MODEL_SID="+modelSid);
                    
                    
            if (filterMap.get("no") != null) {
                String wildCardValue = String.valueOf(filterMap.get("no"));
                query.append(" AND cm.COMPANY_NO like '%").append(wildCardValue).append("%'");
            }

            if (filterMap.get("name") != null) {
                String wildCardValue = String.valueOf(filterMap.get("name"));
                query.append(" AND cm.COMPANY_NAME like '%").append(wildCardValue).append("%'");
            }

            if (filterMap.get(ConstantUtil.STATUS_PROPERTY) != null) {
                query.append(" AND cf.CFP_STATUS =").append(String.valueOf(filterMap.get(ConstantUtil.STATUS_PROPERTY)));
            }
            if (filterMap.get("category") != null) {
                query.append(" AND cm.COMPANY_CATEGORY =").append(String.valueOf(filterMap.get("category")));
            }
            if (filterMap.get("type") != null) {
                query.append(" AND cm.COMPANY_TYPE =").append(String.valueOf(filterMap.get("type")));
            }
            
            
            if(!countFlag){
			query.append(ConstantUtil.ORDER_BY + "cfd.CFP_DETAILS_SID" + " " + "ASC" + ConstantUtil.OFFSET + start + ConstantUtil.ROWS_FETCH_NEXT + offset + ConstantUtil.ROWS_ONLY);
                 }
            LOGGER.debug("Exits CFP Detail Component Search Query Util"); 
            return query.toString();
        }
        
        public String getIFPDetails(int modelSid,Map<String,Object> filterMap,int start,int offset,boolean countFlag){
            LOGGER.debug("Inside IFP Detail Component Search Query Util");
            StringBuilder query = new StringBuilder("");
            String wildCardValue="";
            if(countFlag){
                query.append("select count(ifd.IFP_DETAILS_SID)");
            }else{
                query.append("select im.ITEM_NO,im.ITEM_NAME,htStatus.DESCRIPTION as status,ifm.IFP_START_DATE,ifm.IFP_END_DATE");
            }
            
                query.append(" from IFP_DETAILS ifd" +
                        " Join IFP_MODEL ifm on ifm.IFP_MODEL_SID=ifd.IFP_MODEL_SID" +
                        " Join ITEM_MASTER im on im.ITEM_MASTER_SID=ifd.ITEM_MASTER_SID" +
                        " Left Join Helper_Table htStatus on htStatus.HELPER_TABLE_SID=ifm.IFP_STATUS" +
                        " where ifd.INBOUND_STATUS <> 'D' and ifd.IFP_MODEL_SID="+modelSid);
                
            

            if(filterMap.get("no")!=null){
                    wildCardValue=String.valueOf(filterMap.get("no"));
                    query.append(ConstantUtil.AND_IM_ITEM_NO_LIKE+wildCardValue+"'");
            }

            if(filterMap.get("name")!=null){
                    wildCardValue=String.valueOf(filterMap.get("name"));
                    query.append(ConstantUtil.AND_IM_ITEM_NAME_LIKE+wildCardValue+"'");
            }

            if(filterMap.get(ConstantUtil.STATUS_PROPERTY)!=null){
                query.append(" AND ifm.IFP_STATUS ="+String.valueOf(filterMap.get(ConstantUtil.STATUS_PROPERTY)));
            }    
                
                
            if (filterMap.get(ConstantUtil.START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get(ConstantUtil.START_DATEFROM)))) { 
                String startDate = filterMap.get(ConstantUtil.START_DATEFROM).toString();
                query.append(" AND ifm.IFP_START_DATE >= '").append(startDate).append("' ");
                
            }
              if (filterMap.get(ConstantUtil.START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get(ConstantUtil.START_DATETO)))) { 
                String startDate = filterMap.get(ConstantUtil.START_DATETO).toString();
                query.append(" AND ifm.IFP_START_DATE <= '").append(startDate).append("' ");
            }
              if (filterMap.get(ConstantUtil.END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get(ConstantUtil.END_DATEFROM)))) { 
                String endDate = filterMap.get(ConstantUtil.END_DATEFROM).toString();
                query.append(" AND ifm.IFP_END_DATE >= '").append(endDate).append("' ");
            }
              if (filterMap.get(ConstantUtil.END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get(ConstantUtil.END_DATETO)))) { 
                String endDate = filterMap.get(ConstantUtil.END_DATETO).toString();
                query.append(" AND ifm.IFP_END_DATE <= '").append(endDate).append("' ");
            }
                
                
            if(!countFlag){
			query.append(ConstantUtil.ORDER_BY + "ifd.IFP_DETAILS_SID" + " " + "ASC" + ConstantUtil.OFFSET + start + ConstantUtil.ROWS_FETCH_NEXT + offset + ConstantUtil.ROWS_ONLY);
                 }
            LOGGER.debug("Exits IFP Detail Component Search Query Util"); 
            return query.toString();
        }
        
        public String getPSDetails(int modelSid,Map<String,Object> filterMap,int start,int offset,boolean countFlag){
            LOGGER.debug("Inside PS Detail Component Search Query Util");
            StringBuilder query = new StringBuilder("");
            String wildCardValue="";
            if(countFlag){
                query.append("select count(psd.PS_DETAILS_SID)");
            }else{
                query.append("select im.ITEM_NO,im.ITEM_NAME,htStatus.DESCRIPTION as status,ps.PS_START_DATE,ps.PS_END_DATE");
            }
                query.append(" from PS_DETAILS psd" +
                            " Join PS_MODEL ps on ps.PS_MODEL_SID=psd.PS_MODEL_SID" +
                            " Join ITEM_MASTER im on im.ITEM_MASTER_SID=psd.ITEM_MASTER_SID" +
                            " Left Join Helper_Table htStatus on htStatus.HELPER_TABLE_SID=ps.PS_STATUS" +
                            " where psd.INBOUND_STATUS <> 'D' and psd.PS_MODEL_SID="+modelSid);
                
                if(filterMap.get("no")!=null){
                        wildCardValue=String.valueOf(filterMap.get("no"));
                        query.append(ConstantUtil.AND_IM_ITEM_NO_LIKE+wildCardValue+"'");
                }

                if(filterMap.get("name")!=null){
                        wildCardValue=String.valueOf(filterMap.get("name"));
                        query.append(ConstantUtil.AND_IM_ITEM_NAME_LIKE+wildCardValue+"'");
                }

                if(filterMap.get(ConstantUtil.STATUS_PROPERTY)!=null){
                    query.append(" AND ps.PS_STATUS ="+String.valueOf(filterMap.get(ConstantUtil.STATUS_PROPERTY)));
                }    


                if (filterMap.get(ConstantUtil.START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get(ConstantUtil.START_DATEFROM)))) { 
                    String startDate = filterMap.get(ConstantUtil.START_DATEFROM).toString();
                    query.append(" AND ps.PS_START_DATE >= '").append(startDate).append("' ");

                }
                  if (filterMap.get(ConstantUtil.START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get(ConstantUtil.START_DATETO)))) { 
                    String startDate = filterMap.get(ConstantUtil.START_DATETO).toString();
                    query.append(" AND ps.PS_START_DATE <= '").append(startDate).append("' ");
                }
                  if (filterMap.get(ConstantUtil.END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get(ConstantUtil.END_DATEFROM)))) { 
                    String endDate = filterMap.get(ConstantUtil.END_DATEFROM).toString();
                    query.append(" AND ps.PS_END_DATE >= '").append(endDate).append("' ");
                }
                  if (filterMap.get(ConstantUtil.END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get(ConstantUtil.END_DATETO)))) { 
                    String endDate = filterMap.get(ConstantUtil.END_DATETO).toString();
                    query.append(" AND ps.PS_END_DATE <= '").append(endDate).append("' ");
                }
                
                
              if(!countFlag){
			query.append(ConstantUtil.ORDER_BY + "psd.PS_DETAILS_SID" + " " + "ASC" + ConstantUtil.OFFSET + start + ConstantUtil.ROWS_FETCH_NEXT + offset + ConstantUtil.ROWS_ONLY);
                 }
             LOGGER.debug("Exits PS Detail Component Search Query Util");
            return query.toString();
        }
        
        public String getRSDetails(int modelSid,Map<String,Object> filterMap,int start,int offset,boolean countFlag){
            LOGGER.debug("Inside RS Detail Component Search Query Util");
            StringBuilder query = new StringBuilder("");
            String wildCardValue="";
            if(countFlag){
                query.append("select count(rsd.RS_DETAILS_SID)");
            }else{
                query.append("select im.ITEM_NO,im.ITEM_NAME,htStatus.DESCRIPTION as status,rs.RS_START_DATE,rs.RS_END_DATE");
            }
            query.append(" from RS_DETAILS rsd" +
                            " Join RS_MODEL rs on rs.RS_MODEL_SID=rsd.RS_MODEL_SID" +
                            " Join ITEM_MASTER im on im.ITEM_MASTER_SID=rsd.ITEM_MASTER_SID" +
                            " Left Join Helper_Table htStatus on htStatus.HELPER_TABLE_SID=rs.RS_STATUS" +
                            " where rsd.INBOUND_STATUS <> 'D' and rsd.RS_MODEL_SID="+modelSid);
            
            if(filterMap.get("no")!=null){
                    wildCardValue=String.valueOf(filterMap.get("no"));
                    query.append(ConstantUtil.AND_IM_ITEM_NO_LIKE+wildCardValue+"'");
            }

            if(filterMap.get("name")!=null){
                    wildCardValue=String.valueOf(filterMap.get("name"));
                    query.append(ConstantUtil.AND_IM_ITEM_NAME_LIKE+wildCardValue+"'");
            }

            if(filterMap.get(ConstantUtil.STATUS_PROPERTY)!=null){
                query.append(" AND rs.RS_STATUS ="+String.valueOf(filterMap.get(ConstantUtil.STATUS_PROPERTY)));
            }    
                
                
            if (filterMap.get(ConstantUtil.START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get(ConstantUtil.START_DATEFROM)))) { 
                String startDate = filterMap.get(ConstantUtil.START_DATEFROM).toString();
                query.append(" AND rs.RS_START_DATE >= '").append(startDate).append("' ");
                
            }
              if (filterMap.get(ConstantUtil.START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get(ConstantUtil.START_DATETO)))) { 
                String startDate = filterMap.get(ConstantUtil.START_DATETO).toString();
                query.append(" AND rs.RS_START_DATE <= '").append(startDate).append("' ");
            }
              if (filterMap.get(ConstantUtil.END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get(ConstantUtil.END_DATEFROM)))) { 
                String endDate = filterMap.get(ConstantUtil.END_DATEFROM).toString();
                query.append(" AND rs.RS_END_DATE >= '").append(endDate).append("' ");
            }
              if (filterMap.get(ConstantUtil.END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(filterMap.get(ConstantUtil.END_DATETO)))) { 
                String endDate = filterMap.get(ConstantUtil.END_DATETO).toString();
                query.append(" AND rs.RS_END_DATE <= '").append(endDate).append("' ");
            }
            
            
             if(!countFlag){
			query.append(ConstantUtil.ORDER_BY + "rsd.RS_DETAILS_SID" + " " + "ASC" + ConstantUtil.OFFSET + start + ConstantUtil.ROWS_FETCH_NEXT + offset + ConstantUtil.ROWS_ONLY);
                 }
            LOGGER.debug("Exits RS Detail Component Search Query Util");
            return query.toString();
        }
        
        public String getContractActualsValidationQuery(String contractId){
            LOGGER.debug("Enters Actual Contract Validation Query Util");
            final StringBuilder queryBuilder = new StringBuilder("");
            queryBuilder.append("select Count(*) from ACTUALS_MASTER where CONTRACT_ID = '"+contractId+"'");
            LOGGER.debug("Exits Actual Contract Validation Query Util");
            return queryBuilder.toString();
        }
        
        public String getForecastTypeContractValidationQuery(int contractSid){
            LOGGER.debug("Enters Forecast Type getForecastTypeContractValidationQuery Query");
            final StringBuilder queryBuilder = new StringBuilder("");
            queryBuilder.append("select DISTINCT FORECASTING_TYPE FROM   PROJECTION_MASTER pm" +
                        " JOIN PROJECTION_DETAILS pd " +
                        ConstantUtil.ON_PM_PROJECTION_MASTER_SID_PD_PROJECTION +
                        " INNER JOIN CCP_DETAILS cd " +
                        ConstantUtil.ON_PD_CCP_DETAILS_SID_CD_CCP_DETAILS_SID +
                        " WHERE  contract_master_sid = "+contractSid+"  AND SAVE_FLAG = 1");
           LOGGER.debug("Exits Forecast Type Validation Query Util");
           return queryBuilder.toString();
        }
        
        public String getNMSalesContractValidationQuery(int contractSid){
            LOGGER.debug("Enters Forecast Type getNMSalesContractValidationQuery Query Util");
            final StringBuilder queryBuilder = new StringBuilder("");
            queryBuilder.append("select count(*) from NM_SALES_PROJECTION where PROJECTION_DETAILS_SID in (" +
                                " select PROJECTION_DETAILS_SID FROM  PROJECTION_MASTER pm" +
                                " JOIN PROJECTION_DETAILS pd" +
                                ConstantUtil.ON_PM_PROJECTION_MASTER_SID_PD_PROJECTION +
                                " INNER JOIN CCP_DETAILS cd " +
                                ConstantUtil.ON_PD_CCP_DETAILS_SID_CD_CCP_DETAILS_SID +
                                "  WHERE  contract_master_sid ="+contractSid+ ConstantUtil.AND_SAVE_FLAG);
           LOGGER.debug("Exits Forecast Type getNMSalesContractValidationQuery Query Util");
           return queryBuilder.toString();
        }
        
        public String getNMDiscountContractValidationQuery(int contractSid){
            LOGGER.debug("Enters Forecast Type getNMDiscountContractValidationQuery Query Util");
            final StringBuilder queryBuilder = new StringBuilder("");
            queryBuilder.append("select count(*) from NM_DISCOUNT_PROJECTION where PROJECTION_DETAILS_SID in (" +
                                "select PROJECTION_DETAILS_SID FROM  PROJECTION_MASTER pm" +
                                " JOIN PROJECTION_DETAILS pd " +
                                ConstantUtil.ON_PM_PROJECTION_MASTER_SID_PD_PROJECTION +
                                "  INNER JOIN CCP_DETAILS cd" +
                                ConstantUtil.ON_PD_CCP_DETAILS_SID_CD_CCP_DETAILS_SID +
                                "  WHERE  contract_master_sid ="+contractSid+ConstantUtil.AND_SAVE_FLAG);
           LOGGER.debug("Exits Forecast Type getNMDiscountContractValidationQuery Query Util");
           return queryBuilder.toString();
        }
        
        public String getMSalesContractValidationQuery(int contractSid){
            LOGGER.debug("Enters Forecast Type getMSalesContractValidationQuery Query Util");
            final StringBuilder queryBuilder = new StringBuilder("");
            queryBuilder.append("select count(*) from M_SALES_PROJECTION where PROJECTION_DETAILS_SID in (" +
                                "select PROJECTION_DETAILS_SID FROM   PROJECTION_MASTER pm " +
                                " JOIN PROJECTION_DETAILS pd" +
                                ConstantUtil.ON_PM_PROJECTION_MASTER_SID_PD_PROJECTION +
                                "  INNER JOIN CCP_DETAILS cd" +
                                ConstantUtil.ON_PD_CCP_DETAILS_SID_CD_CCP_DETAILS_SID +
                                " WHERE  contract_master_sid = "+contractSid+ConstantUtil.AND_SAVE_FLAG);
           LOGGER.debug("Exits Forecast Type getMSalesContractValidationQuery Query Util");
           return queryBuilder.toString();
        }
        
        public String getMDiscountContractValidationQuery(int contractSid){
            LOGGER.debug("Enters Forecast Type getMDiscountContractValidationQuery Query Util");
            final StringBuilder queryBuilder = new StringBuilder("");
            queryBuilder.append("select count(*) from M_DISCOUNT_PROJECTION where PROJECTION_DETAILS_SID in (" +
                                "select PROJECTION_DETAILS_SID FROM   PROJECTION_MASTER pm JOIN PROJECTION_DETAILS pd " +
                                ConstantUtil.ON_PM_PROJECTION_MASTER_SID_PD_PROJECTION +
                                "  INNER JOIN CCP_DETAILS cd " +
                                ConstantUtil.ON_PD_CCP_DETAILS_SID_CD_CCP_DETAILS_SID +
                                " WHERE  contract_master_sid ="+contractSid+ConstantUtil.AND_SAVE_FLAG);
           LOGGER.debug("Exits Forecast Type getMDiscountContractValidationQuery Query Util");
           return queryBuilder.toString();
        }
        
        
        public String getCHSalesContractValidationQuery(int contractSid){
            LOGGER.debug("Enters Forecast Type getCHSalesContractValidationQuery Query Util");
            final StringBuilder queryBuilder = new StringBuilder("");
            queryBuilder.append("select count(*) from CH_SALES_PROJECTION where PROJECTION_DETAILS_SID in (" +
                                "select PROJECTION_DETAILS_SID FROM   PROJECTION_MASTER pm  JOIN PROJECTION_DETAILS pd" +
                                ConstantUtil.ON_PM_PROJECTION_MASTER_SID_PD_PROJECTION +
                                "  INNER JOIN CCP_DETAILS cd " +
                                ConstantUtil.ON_PD_CCP_DETAILS_SID_CD_CCP_DETAILS_SID +
                                " WHERE  contract_master_sid ="+contractSid+ConstantUtil.AND_SAVE_FLAG);
           LOGGER.debug("Exits Forecast Type getCHSalesContractValidationQuery Query Util");
           return queryBuilder.toString();
        }
        
        public String getCHDiscountContractValidationQuery(int contractSid){
            LOGGER.debug("Enters Forecast Type getCHDiscountContractValidationQuery Query Util");
            final StringBuilder queryBuilder = new StringBuilder("");
            queryBuilder.append("select count(*) from CH_PROJECTION_DISCOUNT where PROJECTION_DETAILS_SID in (" +
                                "select PROJECTION_DETAILS_SID FROM   PROJECTION_MASTER pm JOIN PROJECTION_DETAILS pd" +
                                ConstantUtil.ON_PM_PROJECTION_MASTER_SID_PD_PROJECTION +
                                " INNER JOIN CCP_DETAILS cd" +
                                ConstantUtil.ON_PD_CCP_DETAILS_SID_CD_CCP_DETAILS_SID +
                                "  WHERE  contract_master_sid = "+contractSid+ConstantUtil.AND_SAVE_FLAG);
           LOGGER.debug("Exits Forecast Type getCHDiscountContractValidationQuery Query Util");
           return queryBuilder.toString();
        }
        
        public String addToTreeValidationQuery(String mode) {
        LOGGER.debug("Entering addToTreeValidationQuery");
        final StringBuilder sql = new StringBuilder();
        if (mode.equals("CFP")) {
            sql.append("SELECT CM.COMPANY_ID FROM CFP_DETAILS CD JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=CD.COMPANY_MASTER_SID"
                    + " WHERE CD.INBOUND_STATUS <> 'D' AND CD.CFP_MODEL_SID = @MODEL_SID "
                    + " AND ((CD.COMPANY_START_DATE < @START_DATE AND CD.COMPANY_END_DATE < @START_DATE) OR (CD.COMPANY_START_DATE > @END_DATE));");
        } else if (mode.equals("IFP")) {
            sql.append("SELECT IM.ITEM_ID FROM IFP_DETAILS ID JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID=ID.ITEM_MASTER_SID\n"
                    + " WHERE ID.INBOUND_STATUS <>'D' AND ID.IFP_MODEL_SID = @MODEL_SID\n"
                    + " AND ((ID.START_DATE < @START_DATE AND ID.END_DATE < @START_DATE) OR (ID.START_DATE > @END_DATE));");
        } else if (mode.equals("PS")) {
            sql.append("SELECT IM.ITEM_ID FROM dbo.PS_DETAILS PD JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID=PD.ITEM_MASTER_SID\n"
                    + " WHERE PD.INBOUND_STATUS NOT IN('D') AND PD.PS_MODEL_SID= @MODEL_SID \n"
                    + " AND ((PD.CONTRACT_PRICE_START_DATE < @START_DATE AND PD.CONTRACT_PRICE_END_DATE < @START_DATE) OR (PD.CONTRACT_PRICE_START_DATE > @END_DATE));");
        } else if (mode.equals("RS")) {
            sql.append("SELECT IM.ITEM_ID FROM dbo.RS_DETAILS RD JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID=RD.ITEM_MASTER_SID\n"
                    + " WHERE RD.INBOUND_STATUS NOT IN('D') AND RD.RS_MODEL_SID=@MODEL_SID \n"
                    + " AND ((RD.ITEM_REBATE_START_DATE < @START_DATE AND RD.ITEM_REBATE_END_DATE < @START_DATE) OR (RD.ITEM_REBATE_START_DATE > @END_DATE));");
        }
        LOGGER.debug("Ending addToTreeValidationQuery");
        return sql.toString();
    }
        
}
