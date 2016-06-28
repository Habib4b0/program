package com.stpl.app.service.persistence;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.model.ContractMaster;
import com.stpl.app.util.Constants;
import com.stpl.portal.kernel.dao.orm.Query;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.ArrayList;

public class ContractMasterFinderImpl extends BasePersistenceImpl<ContractMaster> implements ContractMasterFinder {

    private static final Logger LOGGER = Logger.getLogger(ContractMasterFinderImpl.class);

    public List getContractPriceInfo(int contractSystemId, int cfpId, int ifpId, int psId) {

        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            LOGGER.info("enters getContractPriceInfo()");
            session = openSession();

            sql = CustomSQLUtil.get("contractPriceDetails");

            if (contractSystemId != 0) {
                sql += " AND IFPC.CONTRACT_MASTER_SID=" + contractSystemId + " ";
            }
            if (cfpId != 0) {

                sql += " AND IFPC.CFP_CONTRACT_SID=" + cfpId + " ";
            }

            if (ifpId != 0) {
                sql += " AND IFPC.IFP_CONTRACT_SID=" + ifpId + " ";

            }

            if (psId != 0) {
                sql += "AND PSC.PS_CONTRACT_SID=" + psId + " ";

            }
            SQLQuery sqlQuery = session.createSQLQuery(sql);
            LOGGER.info("End of getContractPriceInfo()");
            return sqlQuery.list();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
            return null;
        } finally {
            closeSession(session);
        }

    }

    public List getTradingPartnerList(String companyId, String companyNo, String companyName, int companyStatus, int companyType, Map<String, Object> filterMap, int start, int offset, String column, String orederBy) {
        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            if (StringUtils.isNotBlank(companyId)) {

            } else {
                companyId = "%";
            }
            if (orederBy != null) {

            } else {
                orederBy = "ASC";
            }

            sql = "SELECT cm.COMPANY_MASTER_SID, cm.COMPANY_ID, cm.COMPANY_NO, cm.COMPANY_NAME, cm.COMPANY_STATUS, cm.COMPANY_TYPE, htype.DESCRIPTION as ctype, hstatus.DESCRIPTION as cstatus"
                    + " from COMPANY_MASTER cm LEFT JOIN HelPER_TABLE htype on htype.HELPER_TABLE_SID =  cm.COMPANY_TYPE"
                    + " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID =  cm.COMPANY_STATUS"
                    + " WHERE cm.INBOUND_STATUS <> 'D' AND cm.COMPANY_TYPE not in (select HELPER_TABLE_SID from HELPER_TABLE where LIST_NAME = 'COMP_TYPE' and DESCRIPTION ='BUNIT')"
                    + " AND cm.COMPANY_TYPE not in (select HELPER_TABLE_SID from HELPER_TABLE where LIST_NAME = 'COMP_TYPE' and DESCRIPTION ='" + Constants.MANUFACTURER.toUpperCase() + "')";

            if (StringUtils.isNotBlank(companyId) && StringUtils.isNotEmpty(companyId)) {
                sql += " AND cm.COMPANY_ID LIKE '" + companyId + "'";

            }
            if (StringUtils.isNotBlank(companyNo) && StringUtils.isNotEmpty(companyNo)) {
                sql += " AND cm.COMPANY_NO LIKE '" + companyNo + "'";

            }
            if (StringUtils.isNotBlank(companyName) && StringUtils.isNotEmpty(companyName)) {
                sql += " AND cm.COMPANY_NAME LIKE '" + companyName + "'";

            }
            if (companyStatus != 0) {
                sql += " AND cm.COMPANY_STATUS = " + companyStatus;

            }
            if (companyType != 0) {
                sql += " AND cm.COMPANY_TYPE = " + companyType;

            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("companyId")))) {
                sql += " AND cm.COMPANY_ID LIKE '" + String.valueOf(filterMap.get("companyId")) + "'";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("companyNo")))) {
                sql += " AND cm.COMPANY_NO LIKE '" + String.valueOf(filterMap.get("companyNo")) + "'";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("companyName")))) {
                sql += " AND cm.COMPANY_NAME LIKE '" + String.valueOf(filterMap.get("companyName")) + "'";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("companyStatus")))) {
                sql += " AND cm.COMPANY_STATUS =" + Integer.valueOf(String.valueOf(filterMap.get("companyStatus")));
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("companyType")))) {
                sql += " AND cm.COMPANY_TYPE =" + Integer.valueOf(String.valueOf(filterMap.get("companyType")));
            }

            sql += " ORDER BY " + column + " " + orederBy + " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";

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

    public List getContractList(String contractId, String contractNo, String contractName, int contractStatus, int contractType, String tradeClass,
            int tradingPartner, Map<String, Object> filterMap, String orderBy, String column, int start, int offset, boolean isCount) {
        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();

            sql = "SELECT con.CONTRACT_MASTER_SID,con.CONTRACT_ID,con.CONTRACT_NO,con.CONTRACT_NAME,con.CONTRACT_STATUS,con.CONTRACT_TYPE,con.CONTRACT_TRADE_CLASS,con.CONT_HOLD_COMPANY_MASTER_SID,"
                    + "hstatus.DESCRIPTION as constatus,htype.DESCRIPTION as contype,htradeclass.DESCRIPTION as contradeclass, comptrade.COMPANY_NAME as compname,con.RECORD_LOCK_STATUS FROM CONTRACT_MASTER con "
                    + "LEFT JOIN helper_table hstatus ON hstatus.helper_table_sid = isnull(con.contract_status,0) "
                    + "LEFT JOIN helper_table htype ON htype.helper_table_sid = isnull(con.contract_type,0) "
                    + "LEFT JOIN helper_table htradeclass ON htradeclass.helper_table_sid = isnull(con.contract_trade_class,0) ";
            sql += "LEFT JOIN COMPANY_MASTER comptrade on comptrade.COMPANY_MASTER_SID =  con.CONT_HOLD_COMPANY_MASTER_SID ";

            sql += "WHERE con.INBOUND_STATUS <> 'D'";
            if (contractStatus != 0) {
                sql += " AND con.CONTRACT_STATUS =" + contractStatus;
            }

            if (contractType != 0) {
                sql += " AND con.CONTRACT_TYPE =" + contractType;
            }

            if (Integer.valueOf(String.valueOf(filterMap.get("contractStatus"))) != 0) {
                sql += " AND con.CONTRACT_STATUS =" + Integer.valueOf(String.valueOf(filterMap.get("contractStatus")));
            }

            if (Integer.valueOf(String.valueOf(filterMap.get("contractType"))) != 0) {
                sql += " AND con.CONTRACT_TYPE =" + Integer.valueOf(String.valueOf(filterMap.get("contractType")));
            }

            if (StringUtils.isNotBlank(contractId)) {
                sql += " AND con.CONTRACT_ID LIKE '" + contractId + "'";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("contractId")))) {
                sql += " AND con.CONTRACT_ID LIKE '" + String.valueOf(filterMap.get("contractId")) + "'";
            }

            if (StringUtils.isNotBlank(contractNo)) {
                sql += " AND con.CONTRACT_NO LIKE '" + contractNo + "'";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("contractNo")))) {
                sql += " AND con.CONTRACT_NO LIKE '" + String.valueOf(filterMap.get("contractNo")) + "'";
            }

            if (StringUtils.isNotBlank(contractName)) {
                sql += " AND con.CONTRACT_NAME LIKE '" + contractName + "'";
            }

            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("contractName")))) {
                sql += " AND con.CONTRACT_NAME LIKE '" + String.valueOf(filterMap.get("contractName")) + "'";
            }

            if (StringUtils.isNotBlank(tradeClass)) {
                sql += " AND htradeclass.DESCRIPTION LIKE '" + tradeClass + "'";
            }

            if (tradingPartner != 0) {
                sql += " AND con.CONT_HOLD_COMPANY_MASTER_SID=" + tradingPartner;
            }
            if (StringUtils.isNotBlank(String.valueOf(filterMap.get("tradingPartnerName")))) {
                sql += " AND comptrade.COMPANY_NAME LIKE '" + String.valueOf(filterMap.get("tradingPartnerName")) + "'";
            }

            if (isCount) {
                sql += " ORDER BY " + column + " " + orderBy + " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            }

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

    public List fetchFieldsForSecurity(String moduleName, String tabName, Object obj1, Object obj2, Object obj3) {
        Session session = null;
        String query = StringUtils.EMPTY;
        try {
            session = openSession();

            query = "SELECT DISPLAY_NAME, PROPERTY_NAME FROM MODULE_PROPERTIES WHERE MODULE_NAME = '" + moduleName + "' "
                    + " AND TAB_NAME = '" + tabName + "' AND CATEGORY_NAME <> 'Button' ";

            SQLQuery sqlQuery = session.createSQLQuery(query);
            return sqlQuery.list();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            LOGGER.error(query);
            return null;
        } finally {
            closeSession(session);
        }

    }

    public List searchContractsForPromoteTp(Map<String, Object> parameters) {
        Session session = null;
        List resultList = null;
        StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
        try {
            LOGGER.info("Entering searchContractsForPromoteTp method");
            session = openSession();
            if (parameters.get("lazyLoadResults") != null && "lazyLoadResults".equalsIgnoreCase(String.valueOf(parameters.get("lazyLoadResults")))) {
                queryString.append(CustomSQLUtil.get("searchPromoteTpToChContract"));
            } else {
                queryString.append(CustomSQLUtil.get("searchPromoteTpToChContractCount"));
            }

            if (parameters.get("contractNo") != null) {
                queryString.append(" WHERE CM.CONTRACT_NO like '");
                queryString.append(String.valueOf(parameters.get("contractNo")));
                queryString.append("' ");
            }

            if ((parameters.get("isOrdered") == null || "false".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) && (parameters.get("lazyLoadResults") != null)) {
                queryString.append(" ORDER BY CM.CREATED_DATE DESC ");
            } else if (parameters.get("isOrdered") != null && "true".equalsIgnoreCase(String.valueOf(parameters.get("isOrdered")))) {
                if (parameters.get("orderBy~contractName") != null && !Constants.NULL.equals(String.valueOf(parameters.get("orderBy~contractName"))) && !StringUtils.isBlank(String.valueOf(parameters.get("orderBy~contractName")))) {
                    queryString.append(" ORDER BY CM.CONTRACT_NAME ");
                    queryString.append(String.valueOf(parameters.get("orderBy~contractName")));
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

    public Object executeSelectQueries(String[] queries) {
        Session session = null;
        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            session = openSession();
            for (String query : queries) {
                Query sqlQuery = session.createSQLQuery(query);
                returnList.addAll(sqlQuery.list());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(queries);
        } finally {
            closeSession(session);
        }
        return returnList;
    }

}
