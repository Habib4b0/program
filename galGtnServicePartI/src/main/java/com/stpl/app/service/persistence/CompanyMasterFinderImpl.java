package com.stpl.app.service.persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.model.CompanyMaster;
import com.stpl.app.util.ConstantUtil;
import com.stpl.portal.kernel.dao.orm.ORMException;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.ArrayList;

public class CompanyMasterFinderImpl extends BasePersistenceImpl<CompanyMaster> implements CompanyMasterFinder {

    private static final Logger LOGGER = Logger.getLogger(CompanyMasterFinderImpl.class);

    public List findCompanyMaster(String companyId, String companyNo,
            String companyName, String companyStatus, String companyType,
            String tradeClass, int identifierType, String identifier, String orderByColumn, Boolean sortOrder) {

        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            sql = CustomSQLUtil
                    .get("com.companymaster.service.persistence.CompanyMasterFinder.findCompanyMaster");

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

            Query sqlQuery = session.createQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }

    public List findCompanyMasterV1(String companyId, String companyNo,
            String companyName, String companyStatus, String companyType, String companyCategory, String companyGroup,
            String tradeClass, int identifierType, String identifier, String orderByColumn, Boolean sortOrder, Object index, Object next, Map<String, Object> parameters) {
//        Map<String, Object> parameters = new HashMap<String, Object>();
        Session session = null;
        StringBuilder sql = new StringBuilder();
        try {
            session = openSession();

            String andOperator = "";
            if (identifierType == 0 && identifier == null) {

                sql = new StringBuilder(CustomSQLUtil.get("com.companymaster.service.persistence.CompanyMasterFinder.findCompanyMasterWithoutIdentifier"));
                sql.append(" where ");
            } else {

                sql = new StringBuilder(CustomSQLUtil.get("com.companymaster.service.persistence.CompanyMasterFinder.findCompanyMasterWithIdentifier"));
                sql.append(" where ");
                if (identifierType != 0) {
                    sql.append(" crti.COMPANY_QUALIFIER_SID=").append(identifierType).append(" ");
                    andOperator = " AND ";
                }
                if (identifier.length() != 0) {
                    identifier = identifier.replace('*', '%');
                    sql.append(andOperator).append(" crti.COMPANY_IDENTIFIER_VALUE like '").append(identifier).append("' ");
                    andOperator = " AND ";
                }

            }

            if (companyId.length() != 0) {
                sql.append(andOperator).append(" cm.COMPANY_ID like '").append(companyId).append("' ");
                andOperator = " AND ";
            }

            if (companyNo.length() != 0) {
                sql.append(andOperator).append(" cm.COMPANY_NO like '").append(companyNo).append("' ");
                andOperator = " AND ";

            }

            if (companyName.length() != 0) {
                sql.append(andOperator).append(" cm.COMPANY_NAME like '").append(companyName).append("' ");
                andOperator = " AND ";
            }

            if (!"0".equals(companyStatus) && StringUtils.isNotBlank(companyStatus)) {
                sql.append(andOperator).append(" cm.COMPANY_STATUS='").append(companyStatus).append("' ");
                andOperator = " AND ";

            }

            if (!"0".equals(companyType) && StringUtils.isNotBlank(companyType)) {
                sql.append(andOperator).append(" cm.COMPANY_TYPE='").append(companyType).append("' ");
                andOperator = " AND ";

            }
            if (!"0".equals(companyCategory) && StringUtils.isNotBlank(companyCategory)) {
                sql.append(andOperator).append(" cm.COMPANY_CATEGORY='").append(companyCategory).append("' ");
                andOperator = " AND ";

            }
            if (!"0".equals(companyGroup) && StringUtils.isNotBlank(companyGroup)) {
                sql.append(andOperator).append(" cm.COMPANY_GROUP='").append(companyGroup).append("' ");
                andOperator = " AND ";

            }
            if (!"0".equals(tradeClass) && StringUtils.isNotBlank(tradeClass)) {
                sql.append(andOperator).append(" trade.COMPANY_TRADE_CLASS='").append(tradeClass).append("' ");
                andOperator = " AND ";

            }

            sql.append(andOperator).append(" cm.INBOUND_STATUS <> 'D' and trade.INBOUND_STATUS <> 'D'");

            if ((parameters.get("compStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("compStartDatefrom"))))) {
                String startDate = parameters.get("compStartDatefrom").toString();
                sql.append(andOperator).append(" cm.COMPANY_START_DATE >= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("compStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("compStartDateto"))))) {
                String endDate = parameters.get("compStartDateto").toString();
                sql.append(andOperator).append(" cm.COMPANY_START_DATE <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("createdDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdDatefrom"))))) {
                String startDate = parameters.get("createdDatefrom").toString();
                sql.append(andOperator).append(" crti.CREATED_DATE >= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("createdDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("createdDateto"))))) {
                String endDate = parameters.get("createdDateto").toString();
                sql.append(andOperator).append(" crti.CREATED_DATE <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("compEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("compEndDatefrom"))))) {
                String startDate = parameters.get("compEndDatefrom").toString();
                sql.append(andOperator).append(" cm.COMPANY_END_DATE >= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("compEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("compEndDateto"))))) {
                String endDate = parameters.get("compEndDateto").toString();
                sql.append(andOperator).append(" cm.COMPANY_END_DATE <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("tradeStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("tradeStartDatefrom"))))) {
                String startDate = parameters.get("tradeStartDatefrom").toString();
                sql.append(andOperator).append(" trade.TRADE_CLASS_START_DATE >= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("tradeStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("tradeStartDateto"))))) {
                String endDate = parameters.get("tradeStartDateto").toString();
                sql.append(andOperator).append(" trade.TRADE_CLASS_START_DATE <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("tradeEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("tradeEndDatefrom"))))) {
                String startDate = parameters.get("tradeEndDatefrom").toString();
                sql.append(andOperator).append(" trade.TRADE_CLASS_END_DATE >= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("tradeEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("tradeEndDateto"))))) {
                String endDate = parameters.get("tradeEndDateto").toString();
                sql.append(andOperator).append(" trade.TRADE_CLASS_END_DATE <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("parentSDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentSDatefrom"))))) {
                String startDate = parameters.get("parentSDatefrom").toString();
                sql.append(andOperator).append(" parent.PARENT_START_DATE >= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("parentSDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentSDateto"))))) {
                String endDate = parameters.get("parentSDateto").toString();
                sql.append(andOperator).append(" parent.PARENT_START_DATE <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("parentEDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentEDatefrom"))))) {
                String startDate = parameters.get("parentEDatefrom").toString();
                sql.append(andOperator).append(" parent.PARENT_END_DATE >= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("parentEDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentEDateto"))))) {
                String endDate = parameters.get("parentEDateto").toString();
                sql.append(andOperator).append(" parent.PARENT_END_DATE <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("priorParentSDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priorParentSDatefrom"))))) {
                String startDate = parameters.get("priorParentSDatefrom").toString();
                sql.append(andOperator).append(" parent.PRIOR_PARENT_START_DATE >= '").append(startDate).append("' ");
                andOperator = " AND ";
            }
            if ((parameters.get("priorParentSDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priorParentSDateto"))))) {
                String endDate = parameters.get("priorParentSDateto").toString();
                sql.append(andOperator).append(" parent.PRIOR_PARENT_START_DATE <= '").append(endDate).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~companyId") != null) {
                String company = parameters.get("filter~companyId").toString();
                sql.append(andOperator).append(" cm.COMPANY_ID like '").append(company).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~parentCompanyNo") != null) {
                String compNo = parameters.get("filter~parentCompanyNo").toString();
                sql.append(andOperator).append(" comp.COMPANY_NO like '").append(compNo).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~companyNo") != null) {
                String compNo = parameters.get("filter~companyNo").toString();
                sql.append(andOperator).append(" cm.COMPANY_NO like '").append(compNo).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~companyName") != null) {
                String compName = parameters.get("filter~companyName").toString();
                sql.append(andOperator).append(" cm.COMPANY_NAME like '").append(compName).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~systemId") != null) {
                String sysId = parameters.get("filter~systemId").toString();
                sql.append(andOperator).append(" cm.COMPANY_MASTER_SID like '").append(sysId).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("companyStatus") != null) {
                String compStatus = parameters.get("companyStatus").toString();
                sql.append(andOperator).append(" cm.COMPANY_STATUS = '").append(compStatus).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("companyType") != null) {
                String compType = parameters.get("companyType").toString();
                sql.append(andOperator).append(" cm.COMPANY_TYPE like '").append(compType).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~companyStartDate") != null) {
                String compStart = parameters.get("filter~companyStartDate").toString();
                sql.append(andOperator).append(" cm.COMPANY_START_DATE like '").append(compStart).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("tradeClass") != null) {
                String trade = parameters.get("tradeClass").toString();
                sql.append(andOperator).append(" trade.COMPANY_TRADE_CLASS like '").append(trade).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~lives") != null) {
                String lives = parameters.get("filter~lives").toString();
                sql.append(andOperator).append(" cm.LIVES like '").append(lives).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~identifier") != null) {
                String iden = parameters.get("filter~identifier").toString();
                sql.append(andOperator).append(" crti.COMPANY_IDENTIFIER_VALUE like '").append(iden).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("companyGroup") != null) {
                String group = parameters.get("companyGroup").toString();
                sql.append(andOperator).append(" cm.COMPANY_GROUP like '").append(group).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("companyCategory") != null) {
                String category = parameters.get("companyCategory").toString();
                sql.append(andOperator).append(" cm.COMPANY_CATEGORY like '").append(category).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("orgKey") != null) {
                String orgKey = parameters.get("orgKey").toString();
                sql.append(andOperator).append(" cm.ORGANIZATION_KEY like '").append(orgKey).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~identifierTypeDesc") != null) {
                String qualifier = parameters.get("filter~identifierTypeDesc").toString();
                sql.append(andOperator).append(" crtq.COMPANY_QUALIFIER_NAME like '").append(qualifier).append("' ");
                andOperator = " AND ";
            }

            if (parameters.get("filter~identifierType") != null) {
                String qualifier = parameters.get("filter~identifierType").toString();
                sql.append(andOperator).append(" crtq.COMPANY_QUALIFIER_NAME like '").append(qualifier).append("' ");
                andOperator = " AND ";
            }

            if (parameters.get("filter~financialSystem") != null) {
                String company = parameters.get("filter~financialSystem").toString();
                sql.append(andOperator).append(" cm.FINANCIAL_SYSTEM like '").append(company).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~regionCode") != null) {
                String code = parameters.get("filter~regionCode").toString();
                sql.append(andOperator).append(" cm.REGION_CODE like '").append(code).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("udc1") != null) {
                String udc1 = parameters.get("udc1").toString();
                sql.append(andOperator).append(" udc.UDC1 like '").append(udc1).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("udc2") != null) {
                String udc2 = parameters.get("udc2").toString();
                sql.append(andOperator).append(" udc.UDC2 like '").append(udc2).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("udc3") != null) {
                String udc3 = parameters.get("udc3").toString();
                sql.append(andOperator).append(" udc.UDC3 like '").append(udc3).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("udc4") != null) {
                String udc4 = parameters.get("udc4").toString();
                sql.append(andOperator).append(" udc.UDC4 like '").append(udc4).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("udc5") != null) {
                String udc5 = parameters.get("udc5").toString();
                sql.append(andOperator).append(" udc.UDC5 like '").append(udc5).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("udc6") != null) {
                String udc6 = parameters.get("udc6").toString();
                sql.append(andOperator).append(" udc.UDC6 like '").append(udc6).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~address1") != null) {
                String address1 = parameters.get("filter~address1").toString();
                sql.append(andOperator).append(" cm.ADDRESS1 like '").append(address1).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~address2") != null) {
                String address2 = parameters.get("filter~address2").toString();
                sql.append(andOperator).append(" cm.ADDRESS2 like '").append(address2).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~zipCode") != null) {
                String zipCode = parameters.get("filter~zipCode").toString();
                sql.append(andOperator).append(" cm.ZIP_CODE like '").append(zipCode).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~city") != null) {
                String city = parameters.get("filter~city").toString();
                sql.append(andOperator).append(" cm.CITY like '").append(city).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("state") != null) {
                String state = parameters.get("state").toString();
                sql.append(andOperator).append(" cm.STATE like '").append(state).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("country") != null) {
                String country = parameters.get("country").toString();
                sql.append(andOperator).append(" cm.COUNTRY like '").append(country).append("' ");
                andOperator = " AND ";
            }
//            if (parameters.get("filter~parentCompanyNo") != null) {
//                String parentCompNo= parameters.get("filter~parentCompanyNo").toString();
//                sql.append(andOperator).append(" cm.PARENT_COMPANY_MASTER_SID like '").append(parentCompNo).append("' ");
//                andOperator = " AND ";
//            }
            if (parameters.get("identifierStatus") != null) {
                String identifierStatus = parameters.get("identifierStatus").toString();
                sql.append(andOperator).append(" crti.IDENTIFIER_STATUS like '").append(identifierStatus).append("' ");
                andOperator = " AND ";
            }
            if (parameters.get("filter~systemId~>") != null) {
                String value = parameters.get("filter~systemId~>").toString();
                String[] value1 = value.split("--");
                String companySid = value1[0];
                String operator = value1[1];
                if (operator.equals(">0")) {
                    sql.append(" AND (cm.COMPANY_MASTER_SID >'").append(companySid).append("' ").append(" or");
                    sql.append(" cm.COMPANY_MASTER_SID ='").append("0").append("' )");
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
                    sql.append(" cm.COMPANY_MASTER_SID ='").append("0").append("') ");
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
                sql.append(companySidGreater).append("'");
                sql.append(" AND CM.COMPANY_MASTER_SID < '");
//                sql.append("'AND '");
                sql.append(companySidLesser).append("'");
            }

            if (index != null && next != null) {

                if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                    sql.append(" ORDER BY COMPANY_ID").append((!sortOrder) ? " ASC " : " DESC ");
                } else {
                    sql.append("ORDER BY ").append(orderByColumn).append((!sortOrder) ? " ASC " : " DESC ");
                }

                sql.append(" " + "OFFSET ");
                sql.append(index);
                sql.append(" ROWS FETCH NEXT ");
                sql.append(((Integer) next - (Integer) index));
                sql.append(" ROWS ONLY;");
            }

            SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql.toString());
            return null;
        } finally {
            closeSession(session);
        }
    }

    @SuppressWarnings("rawtypes")
    public List getCompanyTradeClass(int companySystemId) {

        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            sql = "SELECT COMPANY_TRADE_CLASS,trade_Class_Start_Date,trade_Class_End_Date,company_Master_Sid,"
                    + "prior_Trade_Class,CREATED_BY,created_Date,company_Trade_Class_Sid,prior_Trade_Class_Start_Date,inbound_Status,modified_By,modified_Date "
                    + "FROM Company_Trade_Class  WHERE company_Master_Sid=" + companySystemId;
            Query sqlQuery = session.createSQLQuery(sql);

            List list = sqlQuery.list();

            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }

    @SuppressWarnings("rawtypes")
    public List getCompanyTradeClassUniqueCheck(String tradeClass, Date tradeStartDate) {

        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String date = df.format(tradeStartDate);

            sql = "SELECT CM.COMPANY_TRADE_CLASS,CM.TRADE_CLASS_START_DATE,CM.TRADE_CLASS_END_DATE,CM.COMPANY_MASTER_SID FROM COMPANY_TRADE_CLASS CM WHERE CM.COMPANY_TRADE_CLASS='" + tradeClass + "'" + " AND CM.TRADE_CLASS_START_DATE='"
                    + date + "'";

            SQLQuery sqlQuery = session.createSQLQuery(sql);
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }

    @SuppressWarnings("rawtypes")
    public List deleteCompanyTradeClassForUpdate(int companySystemId) {

        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            sql = "DELETE FROM COMPANY_TRADE_CLASS WHERE COMPANY_MASTER_SID=" + companySystemId;

            SQLQuery sqlQuery = session.createSQLQuery(sql);
            return null;
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
            LOGGER.error(sql);

            return null;
        } finally {
            closeSession(session);
        }

    }

    //Admin Console
    public List getCustomerSearchDetails(String customerNo, String tradeClass, String customerStatus, String state, String customerName, String customerType, String city, String zipCode) {
        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            sql = CustomSQLUtil.get("getCustomerDetails");
			// String sql = CustomSQLUtil
            // .get("com.companymaster.service.persistence.CompanyMasterFinder.getCustomerDetails");

            // LOGGER.info("sql query------------>"+sql);
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
            // sql += " and CM.inboundStatus not like '"+ConstantsUtils.INBOUND_STATUS_D+"'";

            LOGGER.info("Final sql statement----------->" + sql);
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            //  Query sqlQuery = session.createQuery(sql);
            //   LOGGER.info("Size of the list in the IMPLllllllllllllll  "+sqlQuery.list().size());
            return sqlQuery.list();
        } catch (Exception e) {

            LOGGER.error(e.getMessage());
            return null;
        } finally {
            closeSession(session);
        }

    }

    public List getPriorParentNo(String priorSystemId) {
        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            sql = CustomSQLUtil.get("getPriorCustomerNo");
            if (priorSystemId.length() != 0) {
                sql += " WHERE COMPANY_MASTER.COMPANY_MASTER_SID like '" + priorSystemId + "' ";
            }
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            return sqlQuery.list();

        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }

    public List getAvailableSearchResults(final String searchCriteria) {
        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            sql = CustomSQLUtil.get("getCustomerDetails");

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

            SQLQuery sqlQuery = session.createSQLQuery(sql);
            //  Query sqlQuery = session.createQuery(sql);
            return sqlQuery.list();

        } catch (Exception e) {
            LOGGER.error(e);
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }
    }

    public Object executeSelectQuery(String query, Object udc1, Object udc2) {
        Session session = null;
        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            session = openSession();

            if (query != null && StringUtils.isNotBlank(query)) {
                Query sqlQuery = session.createSQLQuery(query);
                returnList = sqlQuery.list();
            }

        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
            clearCache();
        }
        return returnList;
    }

    /**
     * Used for Promote TP top Contract Holder Search
     *
     * @param parameters
     * @return
     */
    public List searchTPCompanies(Map<String, Object> parameters) {
        Session session = null;
        List resultList = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.info("Entering searchTPCompanies method");
            session = openSession();
            if (parameters.get("lazyLoadResults") != null && "lazyLoadResults".equalsIgnoreCase(String.valueOf(parameters.get("lazyLoadResults")))) {
                queryString.append(CustomSQLUtil.get("searchPromoteTpToChCompany"));
            } else {
                queryString.append(CustomSQLUtil.get("searchPromoteTpToChCompanyCount"));
            }

            if (parameters.get("companyName") != null) {
                queryString.append(" AND CM.COMPANY_NAME like '");
                queryString.append(String.valueOf(parameters.get("companyName")));
                queryString.append("' ");
            }

            if (parameters.get("companyId") != null) {
                queryString.append(" AND CM.COMPANY_ID like '");
                queryString.append(String.valueOf(parameters.get("companyId")));
                queryString.append("' ");
            }

            if (parameters.get("companyNo") != null) {
                queryString.append(" AND CM.COMPANY_NO like '");
                queryString.append(String.valueOf(parameters.get("companyNo")));
                queryString.append("' ");
            }

            if (parameters.get("companyType") != null) {
                queryString.append(" AND CM.COMPANY_TYPE in (select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION='");
                queryString.append(String.valueOf(parameters.get("companyType")));
                queryString.append("' and LIST_NAME='COMPANY_TYPE' )");
            }

            if (parameters.get("companyCategory") != null) {
                queryString.append(" AND CM.COMPANY_CATEGORY in (select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION='");
                queryString.append(String.valueOf(parameters.get("companyCategory")));
                queryString.append("' and LIST_NAME='COMPANY_CATEGORY' )");
            }

            if (parameters.get("tradeClass") != null) {
                queryString.append(" AND trade.COMPANY_TRADE_CLASS in (select HELPER_TABLE_SID from HELPER_TABLE where DESCRIPTION='");
                queryString.append(String.valueOf(parameters.get("tradeClass")));
                queryString.append("' and LIST_NAME='COMPANY_TRADE_CLASS' )");
            }

            /* coded for filter */
            if (parameters.get("filter~companySystemId") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companySystemId"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companySystemId")))) {
                queryString.append(" AND cm.COMPANY_MASTER_SID like '");
                queryString.append(String.valueOf(parameters.get("filter~companySystemId")));
                queryString.append("' ");
            }

            if (parameters.get("filter~companyName") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companyName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companyName")))) {
                queryString.append(" AND cm.COMPANY_NAME like '");
                queryString.append(String.valueOf(parameters.get("filter~companyName")));
                queryString.append("' ");
            }
            if (parameters.get("filter~companyId") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companyId"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companyId")))) {
                queryString.append(" AND cm.COMPANY_ID like '");
                queryString.append(String.valueOf(parameters.get("filter~companyId")));
                queryString.append("' ");
            }
            if (parameters.get("filter~companyNo") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companyNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companyNo")))) {
                queryString.append(" AND cm.COMPANY_NO like '");
                queryString.append(String.valueOf(parameters.get("filter~companyNo")));
                queryString.append("' ");
            }

            if (parameters.get("filter~companyType") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companyType"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companyType")))) {
                String cType = String.valueOf(parameters.get("filter~companyType"));
                cType = cType.replaceAll("%", "");
                queryString.append(" AND CM.COMPANY_TYPE in (");
                queryString.append(cType);
                queryString.append(")");
            }

            if (parameters.get("filter~companyCategory") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companyCategory"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companyCategory")))) {
                String category = String.valueOf(parameters.get("filter~companyCategory"));
                category = category.replaceAll("%", "");
                queryString.append(" AND CM.COMPANY_CATEGORY in (");
                queryString.append(category);
                queryString.append(")");
            }

            if (parameters.get("filter~tradeClass") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~tradeClass"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~tradeClass")))) {
                String tradeClass = String.valueOf(parameters.get("filter~tradeClass"));
                tradeClass = tradeClass.replaceAll("%", "");
                queryString.append(" AND trade.COMPANY_TRADE_CLASS in (");
                queryString.append(tradeClass);
                queryString.append(" )");
            }

            if (parameters.get("filter~address1") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~address1"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~address1")))) {
                queryString.append(" AND cm.ADDRESS1 like '");
                queryString.append(String.valueOf(parameters.get("filter~address1")));
                queryString.append("' ");
            }
            if (parameters.get("filter~address2") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~address2"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~address2")))) {
                queryString.append(" AND cm.ADDRESS1 like '");
                queryString.append(String.valueOf(parameters.get("filter~address2")));
                queryString.append("' ");
            }

            if (parameters.get("filter~city") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~city"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~city")))) {
                queryString.append(" AND cm.CITY like '");
                queryString.append(String.valueOf(parameters.get("filter~city")));
                queryString.append("' ");
            }
            if (parameters.get("filter~zip") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~zip"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~zip")))) {
                queryString.append(" AND cm.ZIP_CODE like '");
                queryString.append(String.valueOf(parameters.get("filter~zip")));
                queryString.append("' ");
            }

            if (parameters.get("filter~state") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~state"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~state")))) {
                String state = String.valueOf(parameters.get("filter~state"));
                state = state.replaceAll("%", "");
                queryString.append(" AND CM.STATE in (");
                queryString.append(state);
                queryString.append(" )");
            }

            if ((parameters.get("isOrdered") == null || "false".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) && (parameters.get("lazyLoadResults") != null)) {
                queryString.append(" ORDER BY CM.CREATED_DATE DESC ");
            } else if (parameters.get("isOrdered") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) {
                if (parameters.get("orderBy~companyName") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("orderBy~companyName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~companyName")))) {
                    queryString.append(" ORDER BY CM.COMPANY_NAME ");
                    queryString.append(String.valueOf(parameters.get("orderBy~companyName")));
                }
            }
            if (parameters.get("lazyLoadResults") != null) {
                if (parameters.get("startIndex") != null && parameters.get("offset") != null) {
                    int startIndex = Integer.parseInt(String.valueOf(parameters.get("startIndex")));
                    int offset = Integer.parseInt(String.valueOf(parameters.get("offset")));
                    queryString.append(" OFFSET ");
                    queryString.append(startIndex);
                    queryString.append(" ROWS FETCH NEXT ");
                    queryString.append(offset);
                    queryString.append(" ROWS ONLY;");
                }
            }
            LOGGER.info("queryString-------------->" + queryString);
            Query query = session.createSQLQuery(queryString.toString());
            resultList = query.list();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(queryString.toString());
            //return null;
        } finally {
            closeSession(session);
        }
        return resultList;
    }

    public List getColumnNames(String tablename) {
        Session session = null;
        List resultList = null;
        session = openSession();
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        queryString.append("select Column_name from INFORMATION_SCHEMA.COLUMNS where TABLE_NAME='" + tablename + "'");
        try {
            Query query = session.createSQLQuery(queryString.toString());
            LOGGER.info("query" + query.toString());
            resultList = query.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(queryString.toString());
        } finally {
            closeSession(session);
        }
        return resultList;
    }

    /**
     * Method to get Company Type Count
     *
     * @param parameters
     * @return
     */
    public List getCompanyTypeCount(Map<String, Object> parameters) {
        Session session = null;
        List resultList = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            session = openSession();
            if (parameters.get("lazyLoadResults") != null && "lazyLoadResults".equalsIgnoreCase(String.valueOf(parameters.get("lazyLoadResults")))) {

                queryString.append(CustomSQLUtil.get("getCompanyTypeForPromoteTpToCh"));
            } else {
                queryString.append(CustomSQLUtil.get("getCompanyTypeCountForPromoteTpToCh"));
            }
             LOGGER.info(" Company search getCompanyTypeCount Query -- "+queryString.toString());
            Query query = session.createSQLQuery(queryString.toString());
            resultList = query.list();

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(queryString.toString());
        } finally {
            closeSession(session);
        }
        return resultList;
    }

    @Override
    public List searchCompanies(Map<String, Object> parameters) {
        Session session = null;
        List resultList = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {

            session = openSession();
            if (parameters.get("lazyLoadResults") != null && "lazyLoadResults".equalsIgnoreCase(String.valueOf(parameters.get("lazyLoadResults")))) {
                LOGGER.info("Entering search companies method");
                if (parameters.get("identifier") != null || parameters.get("identifierType") != null) {
                    queryString.append(CustomSQLUtil.get("searchCompanyWithIdentifier"));
                } else {
                    queryString.append(CustomSQLUtil.get("searchCompany"));
                }
            } else {
                LOGGER.info("Entering searchTPCompanies count method");
                if (parameters.get("identifier") != null || parameters.get("identifierType") != null) {
                    queryString.append(CustomSQLUtil.get("searchCompanyCountWithIdentifier"));
                } else {
                    queryString.append(CustomSQLUtil.get("searchCompanyCount"));
                }
            }

            // Used to get data for the selected companies in Transfer projection details module
            if (parameters.get("companyMasterSids") != null && !String.valueOf(parameters.get("companyMasterSids")).isEmpty()) {
                queryString.append(" AND cm.COMPANY_MASTER_SID in(" + String.valueOf(parameters.get("companyMasterSids")) + ")");
            }
            // Used to restrict the data for the selected companies in Transfer projection details module
            if (parameters.get("pdt.companyRestrictionSessionId") != null && !String.valueOf(parameters.get("pdt.companyRestrictionSessionId")).isEmpty()) {
                queryString.append(" AND cm.COMPANY_MASTER_SID not in( Select COMPANY_MASTER_SID from GCM_COMPANY_LINK WHERE SESSION_ID = '" + String.valueOf(parameters.get("pdt.companyRestrictionSessionId")) + "' AND LINK_ID <> 'R')");
            }

            if (parameters.get("recordLockStatus") != null && !String.valueOf(parameters.get("recordLockStatus")).isEmpty()) {
                queryString.append(" AND cm.RECORD_LOCK_STATUS = ");
                queryString.append(String.valueOf(parameters.get("recordLockStatus")));
            }

            if (parameters.get("companyName") != null) {
                queryString.append(" AND cm.COMPANY_NAME like '");
                queryString.append(String.valueOf(parameters.get("companyName")));
                queryString.append("' ");
            }
            if (parameters.get("companyNo") != null) {
                queryString.append(" AND cm.COMPANY_NO like '");
                queryString.append(String.valueOf(parameters.get("companyNo")));
                queryString.append("' ");
            }
            if (parameters.get("companyId") != null) {
                queryString.append(" AND cm.COMPANY_ID like '");
                queryString.append(String.valueOf(parameters.get("companyId")));
                queryString.append("' ");
            }
            if (parameters.get("companyType") != null) {
                queryString.append(" AND cm.COMPANY_TYPE like '");
                queryString.append(String.valueOf(parameters.get("companyType")));
                queryString.append("' ");
            }
            if (parameters.get("companyCategory") != null) {
                queryString.append(" AND cm.COMPANY_CATEGORY like '");
                queryString.append(String.valueOf(parameters.get("companyCategory")));
                queryString.append("' ");
            }
            if (parameters.get("companyTradeClass") != null) {
                queryString.append(" AND trade.COMPANY_TRADE_CLASS like '");
                queryString.append(String.valueOf(parameters.get("companyTradeClass")));
                queryString.append("' ");
            }
            if (parameters.get("identifierType") != null) {
                queryString.append(" AND crti.COMPANY_QUALIFIER_SID like '");
                queryString.append(String.valueOf(parameters.get("identifierType")));
                queryString.append("' ");
            }
            if (parameters.get("identifier") != null) {
                queryString.append(" AND crti.COMPANY_IDENTIFIER_VALUE like '");
                queryString.append(String.valueOf(parameters.get("identifier")));
                queryString.append("' ");
            }
            if (parameters.get("companyStatus") != null) {
                queryString.append(" AND cm.COMPANY_STATUS like '");
                queryString.append(String.valueOf(parameters.get("companyStatus")));
                queryString.append("' ");
            }
            if (parameters.get("parentCompanyNo") != null) {
//                queryString.append(" AND parent.PARENT_COMPANY_MASTER_SID like '");
                queryString.append(" AND comp.COMPANY_NO like '");
                queryString.append(String.valueOf(parameters.get("parentCompanyNo")));
                queryString.append("' ");
            }
            if (parameters.get("parentCompanyName") != null) {
//                queryString.append(" AND parent.PARENT_COMPANY_MASTER_SID like '");
                queryString.append(" AND comp.COMPANY_NAME like '");
                queryString.append(String.valueOf(parameters.get("parentCompanyName")));
                queryString.append("' ");
            }
            if (parameters.get("filter~companyId") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companyId"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companyId")))) {
                queryString.append(" AND cm.COMPANY_ID like '");
                queryString.append(String.valueOf(parameters.get("filter~companyId")));
                queryString.append("' ");
            }
            if (parameters.get("filter~companyNo") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companyNo"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companyNo")))) {
                queryString.append(" AND cm.COMPANY_NO like '");
                queryString.append(String.valueOf(parameters.get("filter~companyNo")));
                queryString.append("' ");
            }
            if (parameters.get("filter~companyName") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companyName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companyName")))) {
                queryString.append(" AND cm.COMPANY_NAME like '");
                queryString.append(String.valueOf(parameters.get("filter~companyName")));
                queryString.append("' ");
            }
            if (parameters.get("filter~companyType") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companyType")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companyType"))) && !"0".equals(String.valueOf(parameters.get("filter~companyType")))) {
                queryString.append(" AND cm.COMPANY_TYPE like '");
                queryString.append(String.valueOf(parameters.get("filter~companyType")));
                queryString.append("' ");
            }
            if (parameters.get("filter~companyCategory") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companyCategory")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companyCategory"))) && !"0".equals(String.valueOf(parameters.get("filter~companyCategory")))) {
                queryString.append(" AND cm.COMPANY_CATEGORY like '");
                queryString.append(String.valueOf(parameters.get("filter~companyCategory")));
                queryString.append("' ");
            }
            if (parameters.get("filter~companyStatus") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companyStatus")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companyStatus"))) && !"0".equals(String.valueOf(parameters.get("filter~companyStatus")))) {
                queryString.append(" AND cm.COMPANY_STATUS like '");
                queryString.append(String.valueOf(parameters.get("filter~companyStatus")));
                queryString.append("' ");
            }
            if (parameters.get("filter~companySystemId") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~companySystemId")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~companySystemId"))) && !"0".equals(String.valueOf(parameters.get("filter~companySystemId")))) {
                queryString.append(" AND cm.COMPANY_MASTER_SID like '");
                queryString.append(String.valueOf(parameters.get("filter~companySystemId")));
                queryString.append("' ");
            }
            if (parameters.get("filter~tradeClass") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~tradeClass")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~tradeClass"))) && !"0".equals(String.valueOf(parameters.get("filter~tradeClass")))) {
                queryString.append(" AND trade.COMPANY_TRADE_CLASS like '");
                queryString.append(String.valueOf(parameters.get("filter~tradeClass")));
                queryString.append("' ");
            }
            if (parameters.get("filter~address1") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~address1")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~address1"))) && !"0".equals(String.valueOf(parameters.get("filter~address1")))) {
                queryString.append(" AND cm.ADDRESS1 like '");
                queryString.append(String.valueOf(parameters.get("filter~address1")));
                queryString.append("' ");
            }
            if (parameters.get("filter~address2") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~address2")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~address2"))) && !"0".equals(String.valueOf(parameters.get("filter~address2")))) {
                queryString.append(" AND cm.ADDRESS2 like '");
                queryString.append(String.valueOf(parameters.get("filter~address2")));
                queryString.append("' ");
            }
            if (parameters.get("filter~city") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~city")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~city"))) && !"0".equals(String.valueOf(parameters.get("filter~city")))) {
                queryString.append(" AND cm.CITY like '");
                queryString.append(String.valueOf(parameters.get("filter~city")));
                queryString.append("' ");
            }
            if (parameters.get("filter~state") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~state")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~state"))) && !"0".equals(String.valueOf(parameters.get("filter~state")))) {
                queryString.append(" AND cm.\"STATE\" like '");
                queryString.append(String.valueOf(parameters.get("filter~state")));
                queryString.append("' ");
            }
            if (parameters.get("filter~zip") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~zip")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~zip"))) && !"0".equals(String.valueOf(parameters.get("filter~zip")))) {
                queryString.append(" AND cm.ZIP_CODE like '");
                queryString.append(String.valueOf(parameters.get("filter~zip")));
                queryString.append("' ");
            }
            if (parameters.get("filter~parentNo") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~parentNo")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~parentNo"))) && !"0".equals(String.valueOf(parameters.get("filter~parentNo")))) {
                queryString.append(" AND comp.COMPANY_NO like '");
                queryString.append(String.valueOf(parameters.get("filter~parentNo")));
                queryString.append("' ");
            }
            if (parameters.get("filter~parentName") != null && !ConstantUtil.NULL.equals(String.valueOf(parameters.get("filter~parentName")))
                    && !StringUtils.isBlank(String.valueOf(parameters.get("filter~parentName"))) && !"0".equals(String.valueOf(parameters.get("filter~parentName")))) {
                queryString.append(" AND comp.COMPANY_NAME like '");
                queryString.append(String.valueOf(parameters.get("filter~parentName")));
                queryString.append("' ");
            }
            if (parameters.get("lazyLoadResults") != null && parameters.get("searchSessionId") != null) {
                queryString.append(" AND temp.SESSION_ID = '");
                queryString.append(String.valueOf(parameters.get("searchSessionId")));
                queryString.append("' ");
            }
            if (parameters.get("checkRecord") != null) {
                queryString.append(" AND temp.CHECK_RECORD = 1 ");
            }
            if ((parameters.get("isOrdered") == null || StringUtils.isEmpty(String.valueOf(parameters.get("isOrdered"))) || StringUtils.isEmpty(String.valueOf(parameters.get("orderBy~companyName"))) || "false".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) && (parameters.get("lazyLoadResults") != null)) {
                queryString.append(" ORDER BY CM.CREATED_DATE DESC ");
            } else if (parameters.get("isOrdered") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) {
                if (parameters.get("orderBy~companyName") != null && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~companyName")))) {
                    queryString.append(" ORDER BY ").append(String.valueOf(parameters.get("orderBy~companyName"))).append(" ");
                    queryString.append(String.valueOf(parameters.get("orderBy")));
                }
            }
            if (parameters.get("lazyLoadResults") != null) {

                if (parameters.get("startIndex") != null && parameters.get("offset") != null) {
                    int startIndex = Integer.parseInt(String.valueOf(parameters.get("startIndex")));
                    int offset = Integer.parseInt(String.valueOf(parameters.get("offset")));
                    queryString.append(" OFFSET ");
                    queryString.append(startIndex);
                    queryString.append(" ROWS FETCH NEXT ");
                    queryString.append(offset);
                    queryString.append(" ROWS ONLY;");
                }
            }
            LOGGER.info(" Company search Query -- \n"+queryString.toString());
            Query query = session.createSQLQuery(queryString.toString());
            resultList = query.list();

        } catch (ORMException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(queryString.toString());
            //return null;
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(queryString.toString());
            //return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(queryString.toString());
            //return null;
        } finally {
            closeSession(session);
        }
        return resultList;
    }

    public List executeQuery(String query) {
        List list = new ArrayList();
        Session session = null;
        try {
            session = openSession();
            SQLQuery sqlQuery = session.createSQLQuery(query);
            list.addAll(sqlQuery.list());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
        }
        //LOGGER.info("findByTableName return List list=" + list.size());
        return list;
    }

    public int executeUpdateQuery(String query) {
        Session session = null;
        int count = 0;
        try {
            session = openSession();

            Query sqlQuery = session.createSQLQuery(query);
            count = sqlQuery.executeUpdate();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(query);
        } finally {
            closeSession(session);
        }
        return count;
    }
}
