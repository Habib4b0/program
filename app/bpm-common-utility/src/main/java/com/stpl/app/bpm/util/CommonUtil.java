package com.stpl.app.bpm.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.app.bpm.conn.JdbcConnection;
import com.stpl.app.bpm.dto.HeirarchyDefinition;

public class CommonUtil {
	public static Map<String, List<String>> columnNameMap = new HashMap<String, List<String>>();
	JdbcConnection conn = new JdbcConnection();
	public List<String> getStatus() {
		List<String> list = new ArrayList<String>();
		list.add(0, "Choose one");
		list.add("Active");
		list.add("InActive");
		return list;
	}
	public List<String> getCompanyType() {
		List<String> list = new ArrayList<String>();
		try {
			// conn.getContractType();
			list.add(0, "Choose one");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<String> getTableName() {
		List<String> list = conn.getTableName();
		list.add(0, "Choose one");
		return list;
	}
	public List<String> getEntityNames() {
		List<String> list = new ArrayList<String>();
		list.add(0, "Choose one");
		list.add( "ACCRUAL_MASTER");
		list.add( "ACTUALS_MASTER");
		list.add( "AUDIT_MASTER_INBOUND");
		list.add( "AVERAGE_SHELF_LIFE_MASTER");
		list.add( "BEST_PRICE_MASTER");
		list.add( "BRAND_MASTER");
		list.add( "BUSINESSROLE_MODULE");
		list.add( "CFP_CONTRACT_DETAILS");
		list.add( "CFP_CONTRACT");
		list.add( "CFP_DETAILS");
		list.add( "CFP_MODEL");
		list.add( "COMPANY_IDENTIFIER");
		list.add( "COMPANY_MASTER");
		list.add( "COMPANY_PARENT_DETAILS");
		list.add( "COMPANY_QUALIFIER");
		list.add( "COMPANY_TRADE_CLASS");
		list.add( "CONTRACT_ALIAS_MASTER");
		list.add( "CONTRACT_MASTER");
		list.add( "CPI_INDEX_MASTER");
		list.add( "FORECASTING_MASTER");
		list.add( "FORMULA_DETAILS_MASTER");
		list.add( "GL_BALANCE_MASTER");
		list.add( "GL_COST_CENTER_MASTER");
		list.add( "IFP_CONTRACT_DETAILS");
		list.add( "IFP_CONTRACT");
		list.add( "IFP_DETAILS");
		list.add( "IFP_MODEL");
		list.add( "IMTD_CFP_DETAILS");
		list.add( "IMTD_IFP_DETAILS");
		list.add( "IMTD_ITEM_PRICE_REBATE_DETAILS");
		list.add( "IMTD_PS_DETAILS");
		list.add( "IMTD_RS_DETAILS");
		list.add( "ITEM_HIERARCHY_DEF_MASTER");
		list.add( "ITEM_HIERARCHY_MASTER");
		list.add( "ITEM_IDENTIFIER");
		list.add( "ITEM_MASTER");
		list.add( "ITEM_PRICING_QUALIFIER");
		list.add( "ITEM_PRICING");
		list.add( "ITEM_QUALIFIER");
		list.add( "LOT_MASTER");
		list.add( "MASTER_DATA_ATTRIBUTE");
		list.add( "MODULE_PROPERTIES");
		list.add( "PS_CONTRACT_DETAILS");
		list.add( "PS_CONTRACT");
		list.add( "PS_DETAILS");
		list.add( "PS_MODEL");
		list.add( "REBATE_PLAN_MASTER");
		list.add( "REBATE_PLAN_TIER");
		list.add( "REBATE_TIER_FORMULA");
		list.add( "RS_CONTRACT_DETAILS");
		list.add( "RS_CONTRACT");
		list.add( "RS_DETAILS");
		list.add( "RS_MODEL");
		list.add( "SALES_MASTER");
		list.add( "UDCS");
		list.add( "USERGROUP_BUSINESSROLE");
		list.add( "USERGROUP_DOMAIN_MASTER");
		return list;
	}
	
	public List<String> getColumnName(String tableName) {
		System.out.println("tableName ------ : " + tableName);
		List<String> list = columnNameMap.get(tableName);
		if (list == null) {
			list = conn.getColumnName(tableName);
			columnNameMap.put(tableName, list);
		}
		
		return list;
	}
	public List<String> getHierarchyFields(){
		List<String> list=new ArrayList<String>();
		try {
			Field[] ad=HeirarchyDefinition.class.getDeclaredFields();
	    	for (Field field : ad) {
				list.add(field.getName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
        public List<String> getRuleList(String columnName) {
		List<String> ruleList = new ArrayList<String>();
		try {
                        ruleList = conn.getRuleOrRuleGroupValues(columnName);
//			List<String> fileList = getFilePath(new StplHierarchyRules().getArtifact());
//			for (String resource : fileList) {
//				getRuleListByFile(ruleList, resource, columnName);
//			}
			return ruleList;
		} catch (Exception e) {
                    e.printStackTrace();
		} 

		return null;
	}
	
}
