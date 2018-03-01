/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.impl;

import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.app.gcm.util.DataTypeConverter;
import com.stpl.app.gcm.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class CompanyMasterImpl {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyMasterImpl.class);
    private static final String CONST_AND = " AND ";
    private static final String COMP_START_DATE_FROM = "compStartDatefrom";
    private static final String COMP_START_DATE_TO = "compStartDateto";
    private static final String CREATED_DATE_FROM = "createdDatefrom";
    private static final String CREATED_DATE_TO = "createdDateto";
    private static final String COMP_END_DATE_FROM = "compEndDatefrom";
    private static final String COMP_END_DATE_TO = "compEndDateto";
    private static final String TRADE_START_DATE_FROM = "tradeStartDatefrom";
    private static final String TRADE_START_DATE_TO = "tradeStartDateto";
    private static final String TRADE_END_DATE_FROM = "tradeEndDatefrom";
    private static final String TRADE_END_DATE_TO = "tradeEndDateto";
    private static final String PARENT_START_DATE_FROM = "parentSDatefrom";
    private static final String PARENT_START_DATE_TO = "parentSDateto";
    private static final String PARENT_END_DATE_FROM = "parentEDatefrom";
    private static final String PARENT_END_DATE_TO = "parentEDateto";
    private static final String PRIOR_PARENT_START_DATE_FROM = "priorParentSDatefrom";
    private static final String PRIOR_PARENT_START_DATE_TO = "priorParentSDateto";
    private static final String FILTER_COMPANY_ID = "filter~companyId";        
    private static final String FILTER_COMPANY_NO = "filter~companyNo";
    private static final String FILTER_COMPANY_NAME= "filter~companyName";
    private static final String COMP_STATUS = "companyStatus";
    private static final String COMP_TYPE = "companyType";        
    private static final String TRADE_CLASS = "tradeClass";        
    private static final String COMPANY_CATEGORY = "companyCategory";        
    private static final String FILTER_ADDRESS = "filter~address1"; 
            
    private static final String FILTER_ADDRESS_2 = "filter~address2";
    private static final String FILTER_CITY = "filter~city";        
    private static final String ROWS_FETCH_NEXT = " ROWS FETCH NEXT ";
    private static final String ROWS_ONLY = " ROWS ONLY;";
    private static final String LAZY_LOAD_RESULTS = "lazyLoadResults";
            
    private static final String COMP_NAME = "companyName";         
    private static final String COMP_ID = "companyId";        
    private static final String COMP_NO = "companyNo";
    private static final String FILTER_COMPANY_SYS_ID = "filter~companySystemId";
    private static final String AND_CM_COMP_NAME_LIKE = " AND cm.COMPANY_NAME like '";
            
               
    private static final String AND_CM_COMP_ID_LIKE = " AND cm.COMPANY_ID like '";        
    private static final String AND_CM_COMP_NO_LIKE = " AND cm.COMPANY_NO like '";        
    private static final String FILTER_COMPANY_TYPE = "filter~companyType";
    private static final String FILTER_COMPANY_CATEGORY = "filter~companyCategory";
    private static final String FILTER_TRADE_CLASS = "filter~tradeClass";
            
               
    private static final String AND_CM_ADDRESS_1_LIKE = " AND cm.ADDRESS1 like '";        
    private static final String FILTER_ZIP = "filter~zip";        
    private static final String FILTER_STATE = "filter~state";
    private static final String IS_ORDERED = "isOrdered";
    private static final String ORDER_BY_COMP_NAME = "orderBy~companyName";
            
               
    private static final String START_INDEX = "startIndex";         
    private static final String OFFSET = "offset";        
    private static final String IDENTIFIER = "identifier";
    private static final String IDENTIFIER_TYPE = "identifierType";
    private static final String COMPANY_MASTER_SID = "companyMasterSids";
    
    private static final String PDT_COMP_RESTRICTION_SESS_ID = "pdt.companyRestrictionSessionId";
    private static final String FILTER_COMPANY_STATUS = "filter~companyStatus";                             
    private static final String FILTER_PARENT_NO = "filter~parentNo";
    private static final String FILTER_PARENT_NAME = "filter~parentName";  
    private static final String PROJ_ID = "projectionId"; 
    private static final String SELECT_CM_CNT_MASTER_SID_FROM_CNT_MASTER = ", (SELECT CM.CONTRACT_MASTER_SID FROM CONTRACT_MASTER CM";
    private static final String CNT_MASTER = "CONTRACT_MASTER"; 
    private static final String J1_WHERE_J1 = " J1 WHERE J1."; 
    private static final String SELECT_IM_ITEM_MASTER_SID_FROM_ITEM_MASTER = ", (SELECT IM.ITEM_MASTER_SID FROM ITEM_MASTER IM";
            
            public List findCompanyMaster(String companyId, String companyNo,
            String companyName, String companyStatus, String companyType,
            String tradeClass, int identifierType, String identifier, String orderByColumn, Boolean sortOrder) {

        
        String sql = StringUtils.EMPTY;
        try {

            sql = SQlUtil.getQuery("com.companymaster.service.persistence.CompanyMasterFinder.findCompanyMaster");

            if (identifierType != 0) {
                sql += " and crti.companyQualifierSid="
                        + identifierType + " ";
            }
            if (identifier.length() != 0) {
                identifier = identifier.replace('*', '%');
                sql += " and crti.companyIdentifierValue like '"
                        + identifier + "' ";
            }

            if (companyId.length() != 0) {
                sql += " and cm.companyId like '" + companyId + "' ";

            }

            if (companyNo.length() != 0) {
                sql += " and cm.companyNo like '" + companyNo + "' ";

            }

            if (companyName.length() != 0) {
                sql += " and cm.companyName like '" + companyName + "' ";
            }

            if (!"0".equals(companyStatus)) {
                sql += " and cm.companyStatus='" + companyStatus + "' ";

            }

            if (!"0".equals(companyType)) {
                sql += " and cm.companyType='" + companyType + "' ";

            }

            sql += " and crti.inboundStatus <> '" + "D" + "' ";

            String orderByColumnChanged = "";
            if (StringUtils.isNotBlank(orderByColumn)) {
                if (orderByColumn.equals("companyQualifierSid")) {
                    orderByColumnChanged = "crti.companyQualifierSid";
                } else if (orderByColumn.equals("companyIdentifierValue")) {
                    orderByColumnChanged = "crti.companyIdentifierValue";
                } else {
                    orderByColumnChanged = "cm." + orderByColumn;
                }
                sql += " order by " + orderByColumnChanged;
                if (!sortOrder) {
                    sql += " desc";
                }
            }

            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } 

    }

    public List findCompanyMasterV1(String companyId, String companyNo,
            String companyName, String companyStatus, String companyType, String companyCategory, String companyGroup,
            String tradeClass, int identifierType, String identifier, String orderByColumn, Boolean sortOrder, Object index, Object next, Map<String, Object> parameters) {
        StringBuilder sql = new StringBuilder();
        try {

            String andOperator = "";
            if (identifierType == 0 && identifier == null) {

                sql = new StringBuilder(SQlUtil.getQuery("com.companymaster.service.persistence.CompanyMasterFinder.findCompanyMasterWithoutIdentifier"));
                sql.append(" where ");
            } else {

                sql = new StringBuilder(SQlUtil.getQuery("com.companymaster.service.persistence.CompanyMasterFinder.findCompanyMasterWithIdentifier"));
                sql.append(" where ");
                if (identifierType != 0) {
                    sql.append(" crti.COMPANY_QUALIFIER_SID=").append(identifierType).append(' ');
                    andOperator = CONST_AND;
                }
                if (identifier.length() != 0) {
                    identifier = identifier.replace('*', '%');
                    sql.append(andOperator).append(" crti.COMPANY_IDENTIFIER_VALUE like '").append(identifier).append("' ");
                    andOperator = CONST_AND;
                }

            }

            if (companyId.length() != 0) {
                sql.append(andOperator).append(" cm.COMPANY_ID like '").append(companyId).append("' ");
                andOperator = CONST_AND;
            }

            if (companyNo.length() != 0) {
                sql.append(andOperator).append(" cm.COMPANY_NO like '").append(companyNo).append("' ");
                andOperator = CONST_AND;

            }

            if (companyName.length() != 0) {
                sql.append(andOperator).append(" cm.COMPANY_NAME like '").append(companyName).append("' ");
                andOperator = CONST_AND;
            }

            if (!"0".equals(companyStatus) && StringUtils.isNotBlank(companyStatus)) {
                sql.append(andOperator).append(" cm.COMPANY_STATUS='").append(companyStatus).append("' ");
                andOperator = CONST_AND;

            }

            if (!"0".equals(companyType) && StringUtils.isNotBlank(companyType)) {
                sql.append(andOperator).append(" cm.COMPANY_TYPE='").append(companyType).append("' ");
                andOperator = CONST_AND;

            }
            if (!"0".equals(companyCategory) && StringUtils.isNotBlank(companyCategory)) {
                sql.append(andOperator).append(" cm.COMPANY_CATEGORY='").append(companyCategory).append("' ");
                andOperator = CONST_AND;

            }
            if (!"0".equals(companyGroup) && StringUtils.isNotBlank(companyGroup)) {
                sql.append(andOperator).append(" cm.COMPANY_GROUP='").append(companyGroup).append("' ");
                andOperator = CONST_AND;

            }
            if (!"0".equals(tradeClass) && StringUtils.isNotBlank(tradeClass)) {
                sql.append(andOperator).append(" trade.COMPANY_TRADE_CLASS='").append(tradeClass).append("' ");
                andOperator = CONST_AND;

            }

            sql.append(andOperator).append(" cm.INBOUND_STATUS <> 'D' and trade.INBOUND_STATUS <> 'D'");

            if ((parameters.get(COMP_START_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(COMP_START_DATE_FROM))))) {
                String startDate = parameters.get(COMP_START_DATE_FROM).toString();
                sql.append(andOperator).append(" cm.COMPANY_START_DATE >= '").append(startDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(COMP_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(COMP_START_DATE_TO))))) {
                String endDate = parameters.get(COMP_START_DATE_TO).toString();
                sql.append(andOperator).append(" cm.COMPANY_START_DATE <= '").append(endDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(CREATED_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CREATED_DATE_FROM))))) {
                String startDate = parameters.get(CREATED_DATE_FROM).toString();
                sql.append(andOperator).append(" crti.CREATED_DATE >= '").append(startDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(CREATED_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CREATED_DATE_TO))))) {
                String endDate = parameters.get(CREATED_DATE_TO).toString();
                sql.append(andOperator).append(" crti.CREATED_DATE <= '").append(endDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(COMP_END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(COMP_END_DATE_FROM))))) {
                String startDate = parameters.get(COMP_END_DATE_FROM).toString();
                sql.append(andOperator).append(" cm.COMPANY_END_DATE >= '").append(startDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(COMP_END_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(COMP_END_DATE_TO))))) {
                String endDate = parameters.get(COMP_END_DATE_TO).toString();
                sql.append(andOperator).append(" cm.COMPANY_END_DATE <= '").append(endDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(TRADE_START_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(TRADE_START_DATE_FROM))))) {
                String startDate = parameters.get(TRADE_START_DATE_FROM).toString();
                sql.append(andOperator).append(" trade.TRADE_CLASS_START_DATE >= '").append(startDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(TRADE_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(TRADE_START_DATE_TO))))) {
                String endDate = parameters.get(TRADE_START_DATE_TO).toString();
                sql.append(andOperator).append(" trade.TRADE_CLASS_START_DATE <= '").append(endDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(TRADE_END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(TRADE_END_DATE_FROM))))) {
                String startDate = parameters.get(TRADE_END_DATE_FROM).toString();
                sql.append(andOperator).append(" trade.TRADE_CLASS_END_DATE >= '").append(startDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(TRADE_END_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(TRADE_END_DATE_TO))))) {
                String endDate = parameters.get(TRADE_END_DATE_TO).toString();
                sql.append(andOperator).append(" trade.TRADE_CLASS_END_DATE <= '").append(endDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(PARENT_START_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(PARENT_START_DATE_FROM))))) {
                String startDate = parameters.get(PARENT_START_DATE_FROM).toString();
                sql.append(andOperator).append(" parent.PARENT_START_DATE >= '").append(startDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(PARENT_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(PARENT_START_DATE_TO))))) {
                String endDate = parameters.get(PARENT_START_DATE_TO).toString();
                sql.append(andOperator).append(" parent.PARENT_START_DATE <= '").append(endDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(PARENT_END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(PARENT_END_DATE_FROM))))) {
                String startDate = parameters.get(PARENT_END_DATE_FROM).toString();
                sql.append(andOperator).append(" parent.PARENT_END_DATE >= '").append(startDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(PARENT_END_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(PARENT_END_DATE_TO))))) {
                String endDate = parameters.get(PARENT_END_DATE_TO).toString();
                sql.append(andOperator).append(" parent.PARENT_END_DATE <= '").append(endDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(PRIOR_PARENT_START_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(PRIOR_PARENT_START_DATE_FROM))))) {
                String startDate = parameters.get(PRIOR_PARENT_START_DATE_FROM).toString();
                sql.append(andOperator).append(" parent.PRIOR_PARENT_START_DATE >= '").append(startDate).append("' ");
                andOperator = CONST_AND;
            }
            if ((parameters.get(PRIOR_PARENT_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(PRIOR_PARENT_START_DATE_TO))))) {
                String endDate = parameters.get(PRIOR_PARENT_START_DATE_TO).toString();
                sql.append(andOperator).append(" parent.PRIOR_PARENT_START_DATE <= '").append(endDate).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get(FILTER_COMPANY_ID) != null) {
                String company = parameters.get(FILTER_COMPANY_ID).toString();
                sql.append(andOperator).append(" cm.COMPANY_ID like '").append(company).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("filter~parentCompanyNo") != null) {
                String compNo = parameters.get("filter~parentCompanyNo").toString();
                sql.append(andOperator).append(" comp.COMPANY_NO like '").append(compNo).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get(FILTER_COMPANY_NO) != null) {
                String compNo = parameters.get(FILTER_COMPANY_NO).toString();
                sql.append(andOperator).append(" cm.COMPANY_NO like '").append(compNo).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get(FILTER_COMPANY_NAME) != null) {
                String compName = parameters.get(FILTER_COMPANY_NAME).toString();
                sql.append(andOperator).append(" cm.COMPANY_NAME like '").append(compName).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("filter~systemId") != null) {
                String sysId = parameters.get("filter~systemId").toString();
                sql.append(andOperator).append(" cm.COMPANY_MASTER_SID like '").append(sysId).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get(COMP_STATUS) != null) {
                String compStatus = parameters.get(COMP_STATUS).toString();
                sql.append(andOperator).append(" cm.COMPANY_STATUS = '").append(compStatus).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get(COMP_TYPE) != null) {
                String compType = parameters.get(COMP_TYPE).toString();
                sql.append(andOperator).append(" cm.COMPANY_TYPE like '").append(compType).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("filter~companyStartDate") != null) {
                String compStart = parameters.get("filter~companyStartDate").toString();
                sql.append(andOperator).append(" cm.COMPANY_START_DATE like '").append(compStart).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get(TRADE_CLASS) != null) {
                String trade = parameters.get(TRADE_CLASS).toString();
                sql.append(andOperator).append(" trade.COMPANY_TRADE_CLASS like '").append(trade).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("filter~lives") != null) {
                String lives = parameters.get("filter~lives").toString();
                sql.append(andOperator).append(" cm.LIVES like '").append(lives).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("filter~identifier") != null) {
                String iden = parameters.get("filter~identifier").toString();
                sql.append(andOperator).append(" crti.COMPANY_IDENTIFIER_VALUE like '").append(iden).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("companyGroup") != null) {
                String group = parameters.get("companyGroup").toString();
                sql.append(andOperator).append(" cm.COMPANY_GROUP like '").append(group).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get(COMPANY_CATEGORY) != null) {
                String category = parameters.get(COMPANY_CATEGORY).toString();
                sql.append(andOperator).append(" cm.COMPANY_CATEGORY like '").append(category).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("orgKey") != null) {
                String orgKey = parameters.get("orgKey").toString();
                sql.append(andOperator).append(" cm.ORGANIZATION_KEY like '").append(orgKey).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("filter~identifierTypeDesc") != null) {
                String qualifier = parameters.get("filter~identifierTypeDesc").toString();
                sql.append(andOperator).append(" crtq.COMPANY_QUALIFIER_NAME like '").append(qualifier).append("' ");
                andOperator = CONST_AND;
            }

            if (parameters.get("filter~identifierType") != null) {
                String qualifier = parameters.get("filter~identifierType").toString();
                sql.append(andOperator).append(" crtq.COMPANY_QUALIFIER_NAME like '").append(qualifier).append("' ");
                andOperator = CONST_AND;
            }

            if (parameters.get("filter~financialSystem") != null) {
                String company = parameters.get("filter~financialSystem").toString();
                sql.append(andOperator).append(" cm.FINANCIAL_SYSTEM like '").append(company).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("filter~regionCode") != null) {
                String code = parameters.get("filter~regionCode").toString();
                sql.append(andOperator).append(" cm.REGION_CODE like '").append(code).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("udc1") != null) {
                String udc1 = parameters.get("udc1").toString();
                sql.append(andOperator).append(" udc.UDC1 like '").append(udc1).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("udc2") != null) {
                String udc2 = parameters.get("udc2").toString();
                sql.append(andOperator).append(" udc.UDC2 like '").append(udc2).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("udc3") != null) {
                String udc3 = parameters.get("udc3").toString();
                sql.append(andOperator).append(" udc.UDC3 like '").append(udc3).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("udc4") != null) {
                String udc4 = parameters.get("udc4").toString();
                sql.append(andOperator).append(" udc.UDC4 like '").append(udc4).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("udc5") != null) {
                String udc5 = parameters.get("udc5").toString();
                sql.append(andOperator).append(" udc.UDC5 like '").append(udc5).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("udc6") != null) {
                String udc6 = parameters.get("udc6").toString();
                sql.append(andOperator).append(" udc.UDC6 like '").append(udc6).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get(FILTER_ADDRESS) != null) {
                String address1 = parameters.get(FILTER_ADDRESS).toString();
                sql.append(andOperator).append(" cm.ADDRESS1 like '").append(address1).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get(FILTER_ADDRESS_2) != null) {
                String address2 = parameters.get(FILTER_ADDRESS_2).toString();
                sql.append(andOperator).append(" cm.ADDRESS2 like '").append(address2).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("filter~zipCode") != null) {
                String zipCode = parameters.get("filter~zipCode").toString();
                sql.append(andOperator).append(" cm.ZIP_CODE like '").append(zipCode).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get(FILTER_CITY) != null) {
                String city = parameters.get(FILTER_CITY).toString();
                sql.append(andOperator).append(" cm.CITY like '").append(city).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("state") != null) {
                String state = parameters.get("state").toString();
                sql.append(andOperator).append(" cm.STATE like '").append(state).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("country") != null) {
                String country = parameters.get("country").toString();
                sql.append(andOperator).append(" cm.COUNTRY like '").append(country).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("identifierStatus") != null) {
                String identifierStatus = parameters.get("identifierStatus").toString();
                sql.append(andOperator).append(" crti.IDENTIFIER_STATUS like '").append(identifierStatus).append("' ");
                andOperator = CONST_AND;
            }
            if (parameters.get("filter~systemId~>") != null) {
                String value = parameters.get("filter~systemId~>").toString();
                String[] value1 = value.split("--");
                String companySid = value1[0];
                String operator = value1[1];
                if (operator.equals(">0")) {
                    sql.append(" AND (cm.COMPANY_MASTER_SID >'").append(companySid).append("' ").append(" or");
                    sql.append(" cm.COMPANY_MASTER_SID ='").append('0').append("' )");
                }
                if (operator.equals(">")) {
                    sql.append(" AND cm.COMPANY_MASTER_SID >'").append(companySid).append("' ");
                }
            }
            if (parameters.get("filter~systemId~<") != null) {
                String value = parameters.get("filter~systemId~<").toString();
                String[] value1 = value.split("--");
                String companySid = value1[0];
                String operator = value1[1];
                if (operator.equals("<0")) {
                    sql.append(" AND (cm.COMPANY_MASTER_SID <'").append(companySid).append("' ").append(" or");
                    sql.append(" cm.COMPANY_MASTER_SID ='").append('0').append("') ");
                }
                if (operator.equals("<")) {
                    sql.append(" AND cm.COMPANY_MASTER_SID <'").append(companySid).append("' ");
                }
            }
            if (parameters.get("filter~systemId~=") != null) {
                String value = parameters.get("filter~systemId~=").toString();
                String[] value1 = value.split("--");
                String companySid = value1[0];
                String operator = value1[1];
                if (operator.equals("0") || operator.equals("=")) {
                    sql.append(" AND cm.COMPANY_MASTER_SID ='").append(companySid).append("' ");
                }
            }
            if (parameters.get("filter~systemId~<<") != null || parameters.get("filter~systemId~>>") != null) {
                String lesser = parameters.get("filter~systemId~<<").toString();
                String greater = parameters.get("filter~systemId~>>").toString();
                String[] lesser1 = lesser.split("--");
                String[] greater1 = greater.split("--");
                String companySidLesser = lesser1[0];
                String companySidGreater = greater1[0];
                sql.append(" AND CM.COMPANY_MASTER_SID > '");
                sql.append(companySidGreater).append('\'');
                sql.append(" AND CM.COMPANY_MASTER_SID < '");
                sql.append(companySidLesser).append('\'');
            }

            if (index != null && next != null) {

                if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                    sql.append(" ORDER BY COMPANY_ID").append((!sortOrder) ? " ASC " : " DESC ");
                } else {
                    sql.append("ORDER BY ").append(orderByColumn).append((!sortOrder) ? " ASC " : " DESC ");
                }

                sql.append(" " + "OFFSET ");
                sql.append(index);
                sql.append(ROWS_FETCH_NEXT);
                sql.append(((Integer) next - (Integer) index));
                sql.append(ROWS_ONLY);
            }

           
            return  HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } 
    }

    @SuppressWarnings("rawtypes")
    public List getCompanyTradeClass(int companySystemId) {

        String sql = StringUtils.EMPTY;
        try {

            sql = "SELECT COMPANY_TRADE_CLASS,trade_Class_Start_Date,trade_Class_End_Date,company_Master_Sid,"
                    + "prior_Trade_Class,CREATED_BY,created_Date,company_Trade_Class_Sid,prior_Trade_Class_Start_Date,inbound_Status,modified_By,modified_Date "
                    + "FROM Company_Trade_Class  WHERE company_Master_Sid=" + companySystemId;

            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        }

    }

    @SuppressWarnings("rawtypes")
    public List getCompanyTradeClassUniqueCheck(String tradeClass, Date tradeStartDate) {

        String sql = StringUtils.EMPTY;
        try {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String date = df.format(tradeStartDate);

            sql = "SELECT CM.COMPANY_TRADE_CLASS,CM.TRADE_CLASS_START_DATE,CM.TRADE_CLASS_END_DATE,CM.COMPANY_MASTER_SID FROM COMPANY_TRADE_CLASS CM WHERE CM.COMPANY_TRADE_CLASS='" + tradeClass + "'" + " AND CM.TRADE_CLASS_START_DATE='"
                    + date + "'";

            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } 

    }

    @SuppressWarnings("rawtypes")
    public List deleteCompanyTradeClassForUpdate(int companySystemId) {

        String sql = StringUtils.EMPTY;
        try {

            sql = "DELETE FROM COMPANY_TRADE_CLASS WHERE COMPANY_MASTER_SID=" + companySystemId;

            HelperTableLocalServiceUtil.executeSelectQuery(sql);
            return null;
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
            LOGGER.error(sql);

            return null;
        }

    }

    public List getCustomerSearchDetails(String customerNo, String tradeClass, String customerStatus, String state, String customerName, String customerType, String city, String zipCode) {
        String sql = StringUtils.EMPTY;
        try {

            sql = SQlUtil.getQuery("getCustomerDetails");
            if (customerNo.length() != 0) {
                sql += " AND CM.company_No like '" + customerNo + "' ";
            }
            if (tradeClass.length() != 0) {
                sql += " and HT_TC.DESCRIPTION ='" + tradeClass + "' " + " AND CTC.COMPANY_TRADE_CLASS=HT_TC.HELPER_TABLE_SID ";
            }

            if (customerStatus.length() != 0) {
                sql += " and HT_CS.DESCRIPTION = '" + customerStatus + "' " + " AND CM.COMPANY_STATUS = HT_CS.HELPER_TABLE_SID ";

            }

            if (state.length() != 0) {
                sql += " and HT_ST.DESCRIPTION like '" + state + "' " + " AND CM.STATE = HT_ST.HELPER_TABLE_SID ";

            }

            if (customerName.length() != 0) {
                sql += " and CM.company_Name like '" + customerName + "' ";
            }

            if (customerType.length() != 0) {
                sql += " and HT_CT.DESCRIPTION like '" + customerType + "' " + " AND CM.COMPANY_TYPE = HT_CT.HELPER_TABLE_SID ";

            }
            if (city.length() != 0) {
                sql += " and CM.city like'" + city + "' ";

            }
            if (zipCode.length() != 0) {
                String zip = String.valueOf(zipCode);
                sql += " and CM.zip_Code like '" + zip + "' ";

            }

            LOGGER.debug("Final sql statement----------->" + sql);
            return HelperTableLocalServiceUtil.executeSelectQuery(sql);
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
            return null;
        }

    }

    public List getPriorParentNo(String priorSystemId) {
        String sql = StringUtils.EMPTY;
        try {

            sql = SQlUtil.getQuery("getPriorCustomerNo");
            if (priorSystemId.length() != 0) {
                sql += " WHERE COMPANY_MASTER.COMPANY_MASTER_SID like '" + priorSystemId + "' ";
            }
            return HelperTableLocalServiceUtil.executeSelectQuery(sql);

        } catch (Exception e) {
            LOGGER.error("",e);
            LOGGER.error(sql);
            return null;
        } 

    }

    public List getAvailableSearchResults(final String searchCriteria) {
        String sql = StringUtils.EMPTY;
        try {

            sql = SQlUtil.getQuery("getCustomerDetails");

            if (searchCriteria.contains("~") && !searchCriteria.contains("*")) {
                String arr[] = searchCriteria.split("~");
                for (String arr1 : arr) {
                    sql += arr1;
                }
            } else {
                if (searchCriteria.length() != 0 && !searchCriteria.contains("*")) {
                    sql += searchCriteria;
                }
            }

            return HelperTableLocalServiceUtil.executeSelectQuery(sql);

        } catch (Exception e) {
            LOGGER.error("",e);
            LOGGER.error(sql);
            return null;
        } 
    }

    public Object executeSelectQuery(String query, Object udc1, Object udc2) {
        List<Object[]> returnList = new ArrayList<>();
        try {

            if (query != null && StringUtils.isNotBlank(query)) {
                returnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } 
        return returnList;
    }

    /**
     * Used for Promote TP top Contract Holder Search
     *
     * @param parameters
     * @return
     */
    public static List searchTPCompanies(Map<String, Object> parameters) {
        List resultList = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.debug("Entering searchTPCompanies method");
            if (parameters.get(LAZY_LOAD_RESULTS) != null && LAZY_LOAD_RESULTS.equalsIgnoreCase(String.valueOf(parameters.get(LAZY_LOAD_RESULTS)))) {
                queryString.append(SQlUtil.getQuery("searchPromoteTpToChCompany"));
            } else {
                queryString.append(SQlUtil.getQuery("searchPromoteTpToChCompanyCount"));
            }

            if (parameters.get(COMP_NAME) != null) {
                queryString.append(" AND CM.COMPANY_NAME like '");
                queryString.append(String.valueOf(parameters.get(COMP_NAME)));
                queryString.append("' ");
            }

            if (parameters.get(COMP_ID) != null) {
                queryString.append(" AND CM.COMPANY_ID like '");
                queryString.append(String.valueOf(parameters.get(COMP_ID)));
                queryString.append("' ");
            }

            if (parameters.get(COMP_NO) != null) {
                queryString.append(" AND CM.COMPANY_NO like '");
                queryString.append(String.valueOf(parameters.get(COMP_NO)));
                queryString.append("' ");
            }

            if (parameters.get(COMP_TYPE) != null) {
                queryString.append(" AND CM.COMPANY_TYPE in (select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION='");
                queryString.append(String.valueOf(parameters.get(COMP_TYPE)));
                queryString.append("' and LIST_NAME='COMPANY_TYPE' )");
            }

            if (parameters.get(COMPANY_CATEGORY) != null) {
                queryString.append(" AND CM.COMPANY_CATEGORY in (select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION='");
                queryString.append(String.valueOf(parameters.get(COMPANY_CATEGORY)));
                queryString.append("' and LIST_NAME='COMPANY_CATEGORY' )");
            }

            if (parameters.get(TRADE_CLASS) != null) {
                queryString.append(" AND trade.COMPANY_TRADE_CLASS in (select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION='");
                queryString.append(String.valueOf(parameters.get(TRADE_CLASS)));
                queryString.append("' and LIST_NAME='COMPANY_TRADE_CLASS' )");
            }

            /* coded for filter */
            if (parameters.get(FILTER_COMPANY_SYS_ID) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_SYS_ID))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_SYS_ID)))) {
                queryString.append(" AND cm.COMPANY_MASTER_SID like '");
                queryString.append(String.valueOf(parameters.get(FILTER_COMPANY_SYS_ID)));
                queryString.append("' ");
            }

            if (parameters.get(FILTER_COMPANY_NAME) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_NAME)))) {
                queryString.append(AND_CM_COMP_NAME_LIKE);
                queryString.append(String.valueOf(parameters.get(FILTER_COMPANY_NAME)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_COMPANY_ID) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_ID))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_ID)))) {
                queryString.append(AND_CM_COMP_ID_LIKE);
                queryString.append(String.valueOf(parameters.get(FILTER_COMPANY_ID)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_COMPANY_NO) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_NO))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_NO)))) {
                queryString.append(AND_CM_COMP_NO_LIKE);
                queryString.append(String.valueOf(parameters.get(FILTER_COMPANY_NO)));
                queryString.append("' ");
            }

            if (parameters.get(FILTER_COMPANY_TYPE) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_TYPE))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_TYPE)))) {
                String cType = String.valueOf(parameters.get(FILTER_COMPANY_TYPE));
                cType = cType.replaceAll("%", "");
                queryString.append(" AND CM.COMPANY_TYPE in (");
                queryString.append(cType);
                queryString.append(')');
            }

            if (parameters.get(FILTER_COMPANY_CATEGORY) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_CATEGORY))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_CATEGORY)))) {
                String category = String.valueOf(parameters.get(FILTER_COMPANY_CATEGORY));
                category = category.replaceAll("%", "");
                queryString.append(" AND CM.COMPANY_CATEGORY in (");
                queryString.append(category);
                queryString.append(')');
            }

            if (parameters.get(FILTER_TRADE_CLASS) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_TRADE_CLASS))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_TRADE_CLASS)))) {
                String tradeClass = String.valueOf(parameters.get(FILTER_TRADE_CLASS));
                tradeClass = tradeClass.replaceAll("%", "");
                queryString.append(" AND trade.COMPANY_TRADE_CLASS in (");
                queryString.append(tradeClass);
                queryString.append(" )");
            }

            if (parameters.get(FILTER_ADDRESS) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_ADDRESS))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_ADDRESS)))) {
                queryString.append(AND_CM_ADDRESS_1_LIKE);
                queryString.append(String.valueOf(parameters.get(FILTER_ADDRESS)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_ADDRESS_2) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_ADDRESS_2))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_ADDRESS_2)))) {
                queryString.append(AND_CM_ADDRESS_1_LIKE);
                queryString.append(String.valueOf(parameters.get(FILTER_ADDRESS_2)));
                queryString.append("' ");
            }

            if (parameters.get(FILTER_CITY) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_CITY))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_CITY)))) {
                queryString.append(" AND cm.CITY like '");
                queryString.append(String.valueOf(parameters.get(FILTER_CITY)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_ZIP) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_ZIP))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_ZIP)))) {
                queryString.append(" AND cm.ZIP_CODE like '");
                queryString.append(String.valueOf(parameters.get(FILTER_ZIP)));
                queryString.append("' ");
            }

            if (parameters.get(FILTER_STATE) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_STATE))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_STATE)))) {
                String state = String.valueOf(parameters.get(FILTER_STATE));
                state = state.replaceAll("%", "");
                queryString.append(" AND CM.STATE in (");
                queryString.append(state);
                queryString.append(" )");
            }

            if ((parameters.get(IS_ORDERED) == null || "false".equalsIgnoreCase(String.valueOf(parameters.get(IS_ORDERED)))) && (parameters.get(LAZY_LOAD_RESULTS) != null)) {
                queryString.append(" ORDER BY CM.CREATED_DATE DESC ");
            } else if (parameters.get(IS_ORDERED) != null && "true".equalsIgnoreCase(String.valueOf(parameters.get(IS_ORDERED)))) {
                if (parameters.get(ORDER_BY_COMP_NAME) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(ORDER_BY_COMP_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(ORDER_BY_COMP_NAME)))) {
                    queryString.append(" ORDER BY CM.COMPANY_NAME ");
                    queryString.append(String.valueOf(parameters.get(ORDER_BY_COMP_NAME)));
                }
            }
            if (parameters.get(LAZY_LOAD_RESULTS) != null) {
                if (parameters.get(START_INDEX) != null && parameters.get(OFFSET) != null) {
                    int startIndex = Integer.parseInt(String.valueOf(parameters.get(START_INDEX)));
                    int offset = Integer.parseInt(String.valueOf(parameters.get(OFFSET)));
                    queryString.append(" OFFSET ");
                    queryString.append(startIndex);
                    queryString.append(ROWS_FETCH_NEXT);
                    queryString.append(offset);
                    queryString.append(ROWS_ONLY);
                }
            }
            LOGGER.debug("queryString-------------->" + queryString);
            
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(queryString.toString());
        } 
        return resultList;
    }

    public static List getColumnNames(String tablename) {
        List resultList = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        queryString.append("select Column_name from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME='" + tablename + "'");
        try {
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(queryString.toString());
        } 
        return resultList;
    }

    /**
     * Method to get Company Type Count
     *
     * @param parameters
     * @return
     */
    public static List getCompanyTypeCount(Map<String, Object> parameters) {
        List resultList = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            if (parameters.get(LAZY_LOAD_RESULTS) != null && LAZY_LOAD_RESULTS.equalsIgnoreCase(String.valueOf(parameters.get(LAZY_LOAD_RESULTS)))) {

                queryString.append(SQlUtil.getQuery("getCompanyTypeForPromoteTpToCh"));
            } else {
                queryString.append(SQlUtil.getQuery("getCompanyTypeCountForPromoteTpToCh"));
            }
             LOGGER.debug(" Company search getCompanyTypeCount Query -- "+queryString.toString());
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(queryString.toString());
        } 
        return resultList;
    }

   
    public static List searchCompanies(Map<String, Object> parameters) {
        List resultList = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {

            if (parameters.get(LAZY_LOAD_RESULTS) != null && LAZY_LOAD_RESULTS.equalsIgnoreCase(String.valueOf(parameters.get(LAZY_LOAD_RESULTS)))) {
                LOGGER.debug("Entering search companies method");
                if (parameters.get(IDENTIFIER) != null || parameters.get(IDENTIFIER_TYPE) != null) {
                    queryString.append(SQlUtil.getQuery("searchCompanyWithIdentifier"));
                } else {
                    queryString.append(SQlUtil.getQuery("searchCompany"));
                }
            } else {
                LOGGER.debug("Entering searchTPCompanies count method");
                if (parameters.get(IDENTIFIER) != null || parameters.get(IDENTIFIER_TYPE) != null) {
                    queryString.append(SQlUtil.getQuery("searchCompanyCountWithIdentifier"));
                } else {
                    queryString.append(SQlUtil.getQuery("searchCompanyCount"));
                }
            }

            if (parameters.get(COMPANY_MASTER_SID) != null && !String.valueOf(parameters.get(COMPANY_MASTER_SID)).isEmpty()) {
                queryString.append(" AND cm.COMPANY_MASTER_SID in(" + String.valueOf(parameters.get(COMPANY_MASTER_SID)) + ")");
            }
            if (parameters.get(PDT_COMP_RESTRICTION_SESS_ID) != null && !String.valueOf(parameters.get(PDT_COMP_RESTRICTION_SESS_ID)).isEmpty()) {
                queryString.append(" AND cm.COMPANY_MASTER_SID not in( Select COMPANY_MASTER_SID from GCM_COMPANY_LINK WHERE SESSION_ID = '" + String.valueOf(parameters.get(PDT_COMP_RESTRICTION_SESS_ID)) + "' AND LINK_ID <> 'R')");
            }

            if (parameters.get(COMP_NAME) != null) {
                queryString.append(AND_CM_COMP_NAME_LIKE);
                queryString.append(String.valueOf(parameters.get(COMP_NAME)));
                queryString.append("' ");
            }
            if (parameters.get(COMP_NO) != null) {
                queryString.append(AND_CM_COMP_NO_LIKE);
                queryString.append(String.valueOf(parameters.get(COMP_NO)));
                queryString.append("' ");
            }
            if (parameters.get(COMP_ID) != null) {
                queryString.append(AND_CM_COMP_ID_LIKE);
                queryString.append(String.valueOf(parameters.get(COMP_ID)));
                queryString.append("' ");
            }
            if (parameters.get(COMP_TYPE) != null) {
                queryString.append(" AND cm.COMPANY_TYPE like '");
                queryString.append(String.valueOf(parameters.get(COMP_TYPE)));
                queryString.append("' ");
            }
            if (parameters.get(COMPANY_CATEGORY) != null) {
                queryString.append(" AND cm.COMPANY_CATEGORY like '");
                queryString.append(String.valueOf(parameters.get(COMPANY_CATEGORY)));
                queryString.append("' ");
            }
            if (parameters.get("companyTradeClass") != null) {
                queryString.append(" AND trade.COMPANY_TRADE_CLASS like '");
                queryString.append(String.valueOf(parameters.get("companyTradeClass")));
                queryString.append("' ");
            }
            if (parameters.get(IDENTIFIER_TYPE) != null) {
                queryString.append(" AND crti.COMPANY_QUALIFIER_SID like '");
                queryString.append(String.valueOf(parameters.get(IDENTIFIER_TYPE)));
                queryString.append("' ");
            }
            if (parameters.get(IDENTIFIER) != null) {
                queryString.append(" AND crti.COMPANY_IDENTIFIER_VALUE like '");
                queryString.append(String.valueOf(parameters.get(IDENTIFIER)));
                queryString.append("' ");
            }
            if (parameters.get(COMP_STATUS) != null) {
                queryString.append(" AND cm.COMPANY_STATUS like '");
                queryString.append(String.valueOf(parameters.get(COMP_STATUS)));
                queryString.append("' ");
            }
            if (parameters.get("parentCompanyNo") != null) {
                queryString.append(" AND comp.COMPANY_NO like '");
                queryString.append(String.valueOf(parameters.get("parentCompanyNo")));
                queryString.append("' ");
            }
            if (parameters.get("parentCompanyName") != null) {
                queryString.append(" AND comp.COMPANY_NAME like '");
                queryString.append(String.valueOf(parameters.get("parentCompanyName")));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_COMPANY_ID) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_ID))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_ID)))) {
                queryString.append(AND_CM_COMP_ID_LIKE);
                queryString.append(String.valueOf(parameters.get(FILTER_COMPANY_ID)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_COMPANY_NO) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_NO))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_NO)))) {
                queryString.append(AND_CM_COMP_NO_LIKE);
                queryString.append(String.valueOf(parameters.get(FILTER_COMPANY_NO)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_COMPANY_NAME) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_NAME)))) {
                queryString.append(AND_CM_COMP_NAME_LIKE);
                queryString.append(String.valueOf(parameters.get(FILTER_COMPANY_NAME)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_COMPANY_TYPE) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_TYPE)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_TYPE))) && !"0".equals(String.valueOf(parameters.get(FILTER_COMPANY_TYPE)))) {
                queryString.append(" AND cm.COMPANY_TYPE like '");
                queryString.append(String.valueOf(parameters.get(FILTER_COMPANY_TYPE)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_COMPANY_CATEGORY) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_CATEGORY)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_CATEGORY))) && !"0".equals(String.valueOf(parameters.get(FILTER_COMPANY_CATEGORY)))) {
                queryString.append(" AND cm.COMPANY_CATEGORY like '");
                queryString.append(String.valueOf(parameters.get(FILTER_COMPANY_CATEGORY)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_COMPANY_STATUS) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_STATUS)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_STATUS))) && !"0".equals(String.valueOf(parameters.get(FILTER_COMPANY_STATUS)))) {
                queryString.append(" AND cm.COMPANY_STATUS like '");
                queryString.append(String.valueOf(parameters.get(FILTER_COMPANY_STATUS)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_COMPANY_SYS_ID) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_COMPANY_SYS_ID)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_COMPANY_SYS_ID))) && !"0".equals(String.valueOf(parameters.get(FILTER_COMPANY_SYS_ID)))) {
                queryString.append(" AND cm.COMPANY_MASTER_SID like '");
                queryString.append(String.valueOf(parameters.get(FILTER_COMPANY_SYS_ID)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_TRADE_CLASS) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_TRADE_CLASS)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_TRADE_CLASS))) && !"0".equals(String.valueOf(parameters.get(FILTER_TRADE_CLASS)))) {
                queryString.append(" AND trade.COMPANY_TRADE_CLASS like '");
                queryString.append(String.valueOf(parameters.get(FILTER_TRADE_CLASS)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_ADDRESS) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_ADDRESS)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_ADDRESS))) && !"0".equals(String.valueOf(parameters.get(FILTER_ADDRESS)))) {
                queryString.append(AND_CM_ADDRESS_1_LIKE);
                queryString.append(String.valueOf(parameters.get(FILTER_ADDRESS)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_ADDRESS_2) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_ADDRESS_2)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_ADDRESS_2))) && !"0".equals(String.valueOf(parameters.get(FILTER_ADDRESS_2)))) {
                queryString.append(" AND cm.ADDRESS2 like '");
                queryString.append(String.valueOf(parameters.get(FILTER_ADDRESS_2)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_CITY) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_CITY)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_CITY))) && !"0".equals(String.valueOf(parameters.get(FILTER_CITY)))) {
                queryString.append(" AND cm.CITY like '");
                queryString.append(String.valueOf(parameters.get(FILTER_CITY)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_STATE) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_STATE)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_STATE))) && !"0".equals(String.valueOf(parameters.get(FILTER_STATE)))) {
                queryString.append(" AND cm.\"STATE\" like '");
                queryString.append(String.valueOf(parameters.get(FILTER_STATE)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_ZIP) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_ZIP)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_ZIP))) && !"0".equals(String.valueOf(parameters.get(FILTER_ZIP)))) {
                queryString.append(" AND cm.ZIP_CODE like '");
                queryString.append(String.valueOf(parameters.get(FILTER_ZIP)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_PARENT_NO) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_PARENT_NO)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_PARENT_NO))) && !"0".equals(String.valueOf(parameters.get(FILTER_PARENT_NO)))) {
                queryString.append(" AND comp.COMPANY_NO like '");
                queryString.append(String.valueOf(parameters.get(FILTER_PARENT_NO)));
                queryString.append("' ");
            }
            if (parameters.get(FILTER_PARENT_NAME) != null && !ConstantsUtils.NULL.equals(String.valueOf(parameters.get(FILTER_PARENT_NAME)))
                    && !StringUtils.isBlank(String.valueOf(parameters.get(FILTER_PARENT_NAME))) && !"0".equals(String.valueOf(parameters.get(FILTER_PARENT_NAME)))) {
                queryString.append(" AND comp.COMPANY_NAME like '");
                queryString.append(String.valueOf(parameters.get(FILTER_PARENT_NAME)));
                queryString.append("' ");
            }
            if (parameters.get("searchSessionId") != null) {
                queryString.append(" AND temp.SESSION_ID = '");
                queryString.append(String.valueOf(parameters.get("searchSessionId")));
                queryString.append("' ");
            }
            if (parameters.get("checkRecord") != null) {
                queryString.append(" AND temp.CHECK_RECORD = 1 ");
            }
            if ((parameters.get(IS_ORDERED) == null || StringUtils.isEmpty(String.valueOf(parameters.get(IS_ORDERED))) || StringUtils.isEmpty(String.valueOf(parameters.get(ORDER_BY_COMP_NAME))) || "false".equalsIgnoreCase(String.valueOf(parameters.get(IS_ORDERED)))) && (parameters.get(LAZY_LOAD_RESULTS) != null)) {
                queryString.append(" ORDER BY CM.CREATED_DATE DESC ");
            } else if (parameters.get(IS_ORDERED) != null && "true".equalsIgnoreCase(String.valueOf(parameters.get(IS_ORDERED)))) {
                if (parameters.get(ORDER_BY_COMP_NAME) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ORDER_BY_COMP_NAME)))) {
                    queryString.append(" ORDER BY ").append(String.valueOf(parameters.get(ORDER_BY_COMP_NAME))).append(' ');
                    queryString.append(String.valueOf(parameters.get("orderBy")));
                }
            }
            if (parameters.get(LAZY_LOAD_RESULTS) != null) {

                if (parameters.get(START_INDEX) != null && parameters.get(OFFSET) != null) {
                    int startIndex = Integer.parseInt(String.valueOf(parameters.get(START_INDEX)));
                    int offset = Integer.parseInt(String.valueOf(parameters.get(OFFSET)));
                    queryString.append(" OFFSET ");
                    queryString.append(startIndex);
                    queryString.append(ROWS_FETCH_NEXT);
                    queryString.append(offset);
                    queryString.append(ROWS_ONLY);
                }
            }
            else {
                queryString.append(" ) A ");
                 
            }
            LOGGER.debug(" Company search Query -- \n"+queryString.toString());
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(queryString.toString());
        }
        return resultList;
    }

    public List executeQuery(String query) {
        List list = new ArrayList();
        try {
            list.addAll(HelperTableLocalServiceUtil.executeSelectQuery(query));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(query);
        } 
        return list;
    }

    public int executeUpdateQuery(String query) {
        int count = 0;
        try {

            count = HelperTableLocalServiceUtil.executeUpdateQueryCount(query);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } 
        return count;
    }
    
     public static void saveCcp(final Map<String, Object> parameters) {


        String hierarchyQuery = StringUtils.EMPTY;
        String levelQuery = StringUtils.EMPTY;
        StringBuilder ccpQueryList = new StringBuilder();
        try {
            LOGGER.debug("Entering saveCcp method ");


            if (parameters.get("indicator") != null && "indicator".equalsIgnoreCase(String.valueOf(parameters.get("saveCcp")))) {
                StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
                customSql.append(SQlUtil.getQuery("saveCcp"));

                if (parameters.get(PROJ_ID) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(PROJ_ID)));
                }

                if (parameters.get("hierarchyNo") != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get("hierarchyNo")));
                }

                if (parameters.get(PROJ_ID) != null) {
                    customSql.replace(customSql.indexOf("?"), customSql.indexOf("?") + 1, String.valueOf(parameters.get(PROJ_ID)));
                }

                LOGGER.debug("End of saveCcp method");
            } else {
                List list = null;
                try {
                    List<String> relationshipBuilderSids = (List<String>) parameters.get("relationshipBuilderSids");
                    if (relationshipBuilderSids != null && !relationshipBuilderSids.isEmpty()) {
                        for (Object temp : relationshipBuilderSids) {
                            Integer level;
                            hierarchyQuery = SQlUtil.getQuery("getHierarchyMapQuery").replace("?", String.valueOf(temp));
                            levelQuery = "SELECT max(RLD.LEVEL_NO)\n"
                                    + "FROM RELATIONSHIP_LEVEL_DEFINITION RLD, \n"
                                    + "HIERARCHY_LEVEL_DEFINITION HLD \n"
                                    + "where HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD.HIERARCHY_LEVEL_DEFINITION_SID \n"
                                    + "AND RLD.RELATIONSHIP_BUILDER_SID=" + temp + ";";
                            list = HelperTableLocalServiceUtil.executeSelectQuery(hierarchyQuery);
                            level = DataTypeConverter.convertObjectToInt(HelperTableLocalServiceUtil.executeSelectQuery(levelQuery).get(0));
                            List<StringBuilder> logic = new ArrayList<>();
                            List<String> condition = new ArrayList<>();
                            StringBuilder ccpQuery = new StringBuilder("MERGE CCP_MAP AS TARGET USING ( "
                                    + "SELECT distinct ? RELATIONSHIP_LEVEL_SID ,CCP.CCP_DETAILS_SID FROM CCP_DETAILS CCP ");
                            Integer prevNo = 0;
                            Integer count = 0;
                            String nextHierarchyNo = StringUtils.EMPTY;
                            for (int i = 0; i < list.size(); i++) {
                                Object[] tempRow = (Object[]) list.get(i);
                                Integer currNo = DataTypeConverter.convertObjectToInt(tempRow[2]);

                                if (i < list.size() - 1) {
                                    Object[] tempNextRow = (Object[]) list.get(i + 1);
                                    nextHierarchyNo = String.valueOf(tempNextRow[4]);
                                }
                                if (prevNo >= currNo) {
                                    while (currNo <= prevNo) {
                                        if (logic.size() > 0) {
                                            logic.remove(logic.get(logic.size() - 1));
                                        }
                                        if (condition.size() > 0) {
                                            condition.remove(condition.get(condition.size() - 1));
                                        }
                                        prevNo--;
                                    }
                                }
                                if ("Contract Holder".equals(tempRow[3]) && parameters.get(PROJ_ID) != null && Constants.NON_MANDATED.equals(parameters.get("screenName"))) {
                                    StringBuilder tempLogic = new StringBuilder(SELECT_CM_CNT_MASTER_SID_FROM_CNT_MASTER);
                                    if (CNT_MASTER.equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.CONT_HOLD_COMPANY_MASTER_SID=");
                                        tempLogic.append(tempRow[1]);
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.CONT_HOLD_COMPANY_MASTER_SID=J1.COMPANY_MASTER_SID");
                                    }
                                    tempLogic.append(") CH");
                                    logic.add(tempLogic);
                                    condition.add("CCP.CONTRACT_MASTER_SID=CH.CONTRACT_MASTER_SID");
                                } else if ("Market Type".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(SELECT_CM_CNT_MASTER_SID_FROM_CNT_MASTER);
                                    if (CNT_MASTER.equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.CONTRACT_TYPE=");
                                        tempLogic.append(tempRow[1]);
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.CONTRACT_TYPE=J1.HELPER_TABLE_SID");
                                    }
                                    tempLogic.append(") MT");
                                    logic.add(tempLogic);
                                    condition.add("CCP.CONTRACT_MASTER_SID=MT.CONTRACT_MASTER_SID");
                                } else if ("Contract".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(SELECT_CM_CNT_MASTER_SID_FROM_CNT_MASTER);
                                    if (CNT_MASTER.equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append('\'');
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("=' ");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.CONTRACT_MASTER_SID=J1.CONTRACT_MASTER_SID");
                                    }
                                    tempLogic.append(") CT");
                                    logic.add(tempLogic);
                                    condition.add("CCP.CONTRACT_MASTER_SID=CT.CONTRACT_MASTER_SID");
                                } else if (("Trading Partner".equals(tempRow[3]) || "Customer".equals(tempRow[3])) && "Customer Hierarchy".equals(tempRow[7])) {
                                    StringBuilder tempLogic = new StringBuilder(", (SELECT CM.COMPANY_MASTER_SID FROM COMPANY_MASTER CM");
                                    if ("COMPANY_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE CM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append('\'');
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND CM.COMPANY_MASTER_SID=J1.COMPANY_MASTER_SID");
                                    }
                                    tempLogic.append(") TP");
                                    logic.add(tempLogic);
                                    condition.add("CCP.COMPANY_MASTER_SID=TP.COMPANY_MASTER_SID");
                                } 
                                else if ("Brand".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(SELECT_IM_ITEM_MASTER_SID_FROM_ITEM_MASTER);
                                    if ("BRAND_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(" BM WHERE BM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.BRAND_MASTER_SID=BM.BRAND_MASTER_SID ");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.BRAND_MASTER_SID=J1.BRAND_MASTER_SID");
                                    }
                                    tempLogic.append(") BRAND");
                                    logic.add(tempLogic);
                                    condition.add("CCP.ITEM_MASTER_SID=BRAND.ITEM_MASTER_SID");
                                } else if ("Therapeutic Class".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(SELECT_IM_ITEM_MASTER_SID_FROM_ITEM_MASTER);
                                    if ("ITEM_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE IM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' ");
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.THERAPEUTIC_CLASS=J1.");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append("_SID");
                                    }
                                    tempLogic.append(") TPC");
                                    logic.add(tempLogic);
                                    condition.add("CCP.ITEM_MASTER_SID=TPC.ITEM_MASTER_SID");
                                } else if ("Item".equals(tempRow[3]) || "NDC".equals(tempRow[3]) || "Product".equals(tempRow[3])) {
                                    StringBuilder tempLogic = new StringBuilder(SELECT_IM_ITEM_MASTER_SID_FROM_ITEM_MASTER);
                                    if ("ITEM_MASTER".equals(tempRow[5])) {
                                        tempLogic.append(" WHERE IM.");
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append('\'');
                                    } else {
                                        tempLogic.append(", ");
                                        tempLogic.append(tempRow[5]);
                                        tempLogic.append(J1_WHERE_J1);
                                        tempLogic.append(tempRow[6]);
                                        tempLogic.append("='");
                                        tempLogic.append(tempRow[1]);
                                        tempLogic.append("' AND IM.ITEM_MASTER_SID=J1.ITEM_MASTER_SID");
                                    }
                                    tempLogic.append(") ITEM");
                                    logic.add(tempLogic);
                                    condition.add("CCP.ITEM_MASTER_SID=ITEM.ITEM_MASTER_SID");
                                }
                                prevNo = currNo;
                                if (level == currNo || !nextHierarchyNo.contains(String.valueOf(tempRow[4])) || list.size() == i + 1) {
                                    ccpQuery.replace(ccpQuery.indexOf("?"), ccpQuery.indexOf("?") + 1, String.valueOf(tempRow[0]));
                                    for (int j = logic.size() - 1; j >= 0; j--) {
                                        ccpQuery.append(logic.get(j));
                                    }
                                    ccpQuery.append(" WHERE ");
                                    for (int j = condition.size() - 1; j >= 0; j--) {
                                        ccpQuery.append(condition.get(j));
                                        if (j != 0) {
                                            ccpQuery.append(CONST_AND);
                                        }
                                    }
                                    ccpQuery.append(") AS SOURCE ON (TARGET.RELATIONSHIP_LEVEL_SID=SOURCE.RELATIONSHIP_LEVEL_SID AND "
                                            + "TARGET.CCP_DETAILS_SID=SOURCE.CCP_DETAILS_SID) WHEN NOT MATCHED THEN INSERT "
                                            + "(RELATIONSHIP_LEVEL_SID, CCP_DETAILS_SID) VALUES ( SOURCE.RELATIONSHIP_LEVEL_SID, SOURCE.CCP_DETAILS_SID);");

                                    ccpQueryList.append(ccpQuery);
                                    count++;
                                    if (count == 10 || list.size() == i + 1) {
                                        HelperTableLocalServiceUtil.executeUpdateQuery(ccpQueryList.toString());
                                        ccpQueryList = new StringBuilder();
                                        count = 0;
                                    }
                                    ccpQuery = new StringBuilder("MERGE CCP_MAP AS TARGET USING ( "
                                            + "SELECT distinct ? RELATIONSHIP_LEVEL_SID ,CCP.CCP_DETAILS_SID FROM CCP_DETAILS CCP ");
                                }

                            }
                        }
                    }

                } catch (Exception ex) {
                    LOGGER.error(ex.getMessage());
                    LOGGER.error(hierarchyQuery);
                    LOGGER.error(levelQuery);
                    LOGGER.error(ccpQueryList.toString());
                }
            }
        } catch (Exception e) {
            LOGGER.error("In saveCcp ->"+e.getMessage());
            LOGGER.error(hierarchyQuery);
            LOGGER.error(levelQuery);
            LOGGER.error(ccpQueryList.toString());
        } 
    }
    
}
