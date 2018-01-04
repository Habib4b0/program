package com.stpl.app.gcm.tp.dao.impl;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.tp.dao.TradingPartnerDAO;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.*;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gcm.impl.CompanyMasterImpl;
import com.stpl.app.gcm.util.xmlparser.SQlUtil;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sriram
 */
public class TradingPartnerDAOImpl implements TradingPartnerDAO {

   

    @Override
    public void updateSubmitFlag(String moduleName, String screenName, String userId, String sessionId, boolean checkCondition, boolean flag) {

        String udcValue = Constants.ZEROSTRING;
        if (flag) {
            udcValue = "1";
            if (TAB_TRANSFER_CONTRACT.getConstant().equals(screenName)|| ADD_TRADING_PARTNER.getConstant().equals(moduleName)) {
                udcValue = "2";
            }
        }
        String checkRecordCondition = StringUtils.EMPTY;
        if (checkCondition) {
            checkRecordCondition = "CHECK_RECORD = '0' ,";
        }
        String updateQuery = "UPDATE GCM_GLOBAL_DETAILS set " + checkRecordCondition + " OPERATION = '" + udcValue + "' where USER_ID='" + userId + "' AND SESSION_ID='" + sessionId + "' AND CHECK_RECORD='1' AND SCREEN_NAME = '" + screenName + "'";
        HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery);
    }

    @Override
    public List getSubmitValidationData(String userId, String sessionId, String screenName, String validationType) {
        String query = StringUtils.EMPTY;
        if ("check".equals(validationType)) {

            query = "select count(*) from GCM_GLOBAL_DETAILS where USER_ID = '" + userId + "' AND SESSION_ID = '" + sessionId + "' and SCREEN_NAME = '" + screenName + "' and CHECK_RECORD = '1'";
        } else if (Constants.START_DATE.equals(validationType)) {
            query = "select DISTINCT CM.CONTRACT_NAME from GCM_GLOBAL_DETAILS TEMP JOIN CONTRACT_MASTER CM on CM.CONTRACT_MASTER_SID = TEMP.CONTRACT_MASTER_SID  \n"
                    + "and TEMP.USER_ID = '" + userId + "' AND TEMP.SESSION_ID =  '" + sessionId + "' and TEMP.SCREEN_NAME =  '" + screenName + "' and TEMP.CHECK_RECORD = '1' and TEMP.START_DATE IS NULL";
        } else if (Constants.END_DATE.equals(validationType)) {
            query = "select DISTINCT CM.CONTRACT_NAME from GCM_GLOBAL_DETAILS TEMP JOIN CONTRACT_MASTER CM on CM.CONTRACT_MASTER_SID = TEMP.CONTRACT_MASTER_SID \n"
                    + "and TEMP.USER_ID =  '" + userId + "' AND TEMP.SESSION_ID = '" + sessionId + "' and TEMP.SCREEN_NAME = '" + screenName + "' and TEMP.CHECK_RECORD = '1' and TEMP.END_DATE IS NULL";
        } else if ("status".equals(validationType)) {
            query = "select DISTINCT CM.CONTRACT_NAME from GCM_GLOBAL_DETAILS TEMP JOIN CONTRACT_MASTER CM on CM.CONTRACT_MASTER_SID = TEMP.CONTRACT_MASTER_SID \n"
                    + "and TEMP.USER_ID = '" + userId + "' AND TEMP.SESSION_ID = '" + sessionId + "' and TEMP.SCREEN_NAME = '" + screenName + "' and TEMP.CHECK_RECORD = '1' and TEMP.STATUS IS NULL";

        }
        return (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public List isAnyRecordSelected(String userId, String sessionId, String screenName) {
        String query = "select count(*) from GCM_GLOBAL_DETAILS where USER_ID = '" + userId + "' AND SESSION_ID = '" + sessionId + "' and SCREEN_NAME = '" + screenName + "' and CHECK_RECORD = '1'";

        return (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public List searchCompanies(Map<String, Object> parameters) throws SystemException {
        return CompanyMasterImpl.searchCompanies(parameters);
    }

    @Override
    public Object executeSelectQuery(String query) {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public List searchList(String query) {
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public int insertCustomer(String companyMasterSid, String companyId, String companyNumber, String companyName, String linkId, String sessionId) {

        String query = "INSERT INTO GCM_COMPANY_LINK(COMPANY_MASTER_SID, COMPANY_ID, COMPANY_NO, COMPANY_NAME, SESSION_ID, LINK_ID) "
                + " VALUES ('" + companyMasterSid + "', '" + companyId + "', '" + companyNumber + "', '" + companyName + "', '" + sessionId + "', '" + linkId + "')";
        return HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
    }

    @Override
    public int updateCustomer(String companyMasterSid, String linkId, String sessionId) {
        String query = "UPDATE GCM_COMPANY_LINK SET LINK_ID = '" + linkId + "' WHERE SESSION_ID = '" + sessionId + "'  AND COMPANY_MASTER_SID ='" + companyMasterSid + "' ";
        return HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
    }

    @Override
    public List searchLinkedCompanies(Map<String, Object> parameters) {
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        if (parameters.get(StringConstantsUtil.QUERY_TYPE) != null) {
            if ("count".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.QUERY_TYPE)))) {
                queryString.append("SELECT COUNT(*) FROM (");
            }

            queryString.append(SQlUtil.getQuery("tp.searchLinkedCompanies"));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("searchSessionId")));
            queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(parameters.get("searchSessionId")));

            if (parameters.get(StringConstantsUtil.FILTERFROM_COMPANY_ID) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERFROM_COMPANY_ID))) && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERFROM_COMPANY_ID)))) {
                queryString.append(" AND CM1.COMPANY_ID like '");
                queryString.append(String.valueOf(parameters.get(StringConstantsUtil.FILTERFROM_COMPANY_ID)));
                queryString.append("' ");
            }
            if (parameters.get(StringConstantsUtil.FILTERFROM_COMPANY_NO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERFROM_COMPANY_NO))) && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERFROM_COMPANY_NO)))) {
                queryString.append(" AND CM1.COMPANY_NO like '");
                queryString.append(String.valueOf(parameters.get(StringConstantsUtil.FILTERFROM_COMPANY_NO)));
                queryString.append("' ");
            }
            if (parameters.get(StringConstantsUtil.FILTERFROM_COMPANY_NAME) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERFROM_COMPANY_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERFROM_COMPANY_NAME)))) {
                queryString.append(" AND CM1.COMPANY_NAME like '");
                queryString.append(String.valueOf(parameters.get(StringConstantsUtil.FILTERFROM_COMPANY_NAME)));
                queryString.append("' ");
            }
            if (parameters.get(StringConstantsUtil.FILTERTO_COMPANY_ID) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERTO_COMPANY_ID))) && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERTO_COMPANY_ID)))) {
                queryString.append(" AND CM2.COMPANY_ID like '");
                queryString.append(String.valueOf(parameters.get(StringConstantsUtil.FILTERTO_COMPANY_ID)));
                queryString.append("' ");
            }
            if (parameters.get(StringConstantsUtil.FILTERTO_COMPANY_NO) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERTO_COMPANY_NO))) && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERTO_COMPANY_NO)))) {
                queryString.append(" AND CM2.COMPANY_NO like '");
                queryString.append(String.valueOf(parameters.get(StringConstantsUtil.FILTERTO_COMPANY_NO)));
                queryString.append("' ");
            }
            if (parameters.get(StringConstantsUtil.FILTERTO_COMPANY_NAME) != null && !Constants.NULL.equals(String.valueOf(parameters.get(StringConstantsUtil.FILTERTO_COMPANY_NAME))) && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.FILTERTO_COMPANY_NAME)))) {
                queryString.append(" AND CM2.COMPANY_NAME like '");
                queryString.append(String.valueOf(parameters.get(StringConstantsUtil.FILTERTO_COMPANY_NAME)));
                queryString.append("' ");
            }

            if ("count".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.QUERY_TYPE)))) {
                queryString.append(")A");

            } else if (parameters.get("start") != null && parameters.get("offset") != null) {
                int startIndex = Integer.parseInt(String.valueOf(parameters.get("start")));
                int offset = Integer.parseInt(String.valueOf(parameters.get("offset")));
                queryString.append(" ORDER BY CM1.COMPANY_MASTER_SID OFFSET ");
                queryString.append(startIndex);
                queryString.append(" ROWS FETCH NEXT ");
                queryString.append(offset);
                queryString.append(" ROWS ONLY;");
            }

        }
        return HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
    }

    @Override
    public int updateCheckRecord(String fromCompanyMasterSid, String toCompanyMasterSid, boolean checkvalue, String sessionId) {
        String query = "UPDATE GCM_COMPANY_LINK SET CHECK_RECORD = '" + checkvalue + "' WHERE SESSION_ID = '" + sessionId + "'  AND COMPANY_MASTER_SID in('" + fromCompanyMasterSid + "','" + toCompanyMasterSid + "') ";
        return HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
    }

    @Override
    public int removeCustomerLink(String linkedCustomersSessionId) {
        String query = "UPDATE GCM_COMPANY_LINK SET LINK_ID = 'R', CHECK_RECORD = 0 WHERE SESSION_ID = '" + linkedCustomersSessionId + "'  AND CHECK_RECORD = 1 ";
        return HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
    }

    @Override
    public List<String> getLinkedCompaniesList(String linkedCustomersSessionId, boolean isFromCompany) {
        String operationSymbol = "<>";
        if (isFromCompany) {
            operationSymbol = "=";
        }
        String query = "SELECT COMPANY_MASTER_SID FROM GCM_COMPANY_LINK WHERE SESSION_ID = '" + linkedCustomersSessionId + "'  AND LINK_ID <> 'R' AND LINK_ID " + operationSymbol + " '0'";
        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public List getLinkedCustomersCheckedRecordCount(String linkedCustomersSessionId) {
        String query = "SELECT count(*) FROM GCM_COMPANY_LINK WHERE SESSION_ID = '" + linkedCustomersSessionId + "'  AND CHECK_RECORD = '1'";
        return (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

    @Override
    public List<String> getCustomersHavingCommonItems(int sourceProjectionId, int destProjectionId, int sourceContractId, int destContractId, String customerMappings) {
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        queryString.append(SQlUtil.getQuery("tp.getCustomersHavingCommonItems"));
        queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(sourceProjectionId));
        queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(destProjectionId));
        queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(sourceContractId));
        queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, String.valueOf(destContractId));
        queryString.replace(queryString.indexOf("?"), queryString.indexOf("?") + 1, customerMappings);
        return HelperTableLocalServiceUtil.executeSelectQuery(String.valueOf(queryString));
    }
    
    @Override
    public void insertIntoCcpMap(Map<String, Object> parameters) {
        CompanyMasterImpl.saveCcp(parameters);
    }
}
