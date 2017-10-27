/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.service.persistence;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.model.CfpContractDetails;
import com.stpl.portal.kernel.dao.orm.SQLQuery;
import com.stpl.portal.kernel.dao.orm.Session;
import com.stpl.portal.service.persistence.impl.BasePersistenceImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;

/**
 *
 * @author pvinoth
 */
public class CfpContractDetailsFinderImpl extends BasePersistenceImpl<CfpContractDetails> implements CfpContractDetailsFinder {

    private static final Logger LOGGER = Logger.getLogger(CfpContractDetailsFinderImpl.class);

    /**
     * Save the CFP Details to CFP Details Attached
     *
     * @param input
     * @param future
     * @return
     */
    public Boolean saveCfpDetailsAttached(final List<Object> input, final Object future) {
        Session session = null;
        Boolean retFlag;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();
            sql = CustomSQLUtil.get("com.contractDashboard.saveCFP");
            sql = sql.replaceFirst("[?]", input.get(0).toString());
            sql = sql.replaceFirst("[?]", input.get(1).toString());
            sql = sql.replaceFirst("[?]", input.get(2).toString());
            sql = sql.replaceFirst("[?]", input.get(3).toString());
            sql = sql.replaceFirst("[?]", input.get(4).toString());
            sql = sql.replaceFirst("[?]", input.get(5).toString());
            sql = sql.replace("@CFP_START_DATE", "'" + input.get(6).toString() + "'");
            sql = sql.replace("@CFP_END_DATE", String.valueOf(input.get(7)).equals("null") ? "NULL" : "'" + input.get(7).toString() + "'");

            SQLQuery q = session.createSQLQuery(sql);
            q.executeUpdate();
            retFlag = true;

        } catch (Exception e) {
            retFlag = false;
            LOGGER.error(e.getMessage());
            LOGGER.error(sql);
        } finally {
            closeSession(session);
        }
        return retFlag;
    }

    public List getCompaniesList(String searchField, String searchVal, Map<String, Object> filterMap, int start, int offset, String column, String orderBy, Object future1, Object future2) {

        Session session = null;
        String sql = StringUtils.EMPTY;
        try {
            session = openSession();
            if (orderBy == null) {
                orderBy = "ASC";
            }
            String companyId = String.valueOf(filterMap.get("companyId"));
            String companyNo = String.valueOf(filterMap.get("companyNo"));
            String companyName = String.valueOf(filterMap.get("companyName"));
            String companyStatus = String.valueOf(filterMap.get("companyStatus"));
            String companyType = String.valueOf(filterMap.get("companyType"));
            String companyTradeClass = String.valueOf(filterMap.get("tradeClass"));
            String companyCategory = String.valueOf(filterMap.get("companyCategory"));
            String companyGroup = String.valueOf(filterMap.get("companyGroup"));
            String andOperator = "";
            String whereCondition = " WHERE ";
            sql = ";WITH CTE\n"
                    + "     AS (SELECT DISTINCT cm.COMPANY_MASTER_SID,\n"
                    + "                         cm.COMPANY_ID,\n"
                    + "                         cm.COMPANY_NO,\n"
                    + "                         cm.COMPANY_NAME,\n"
                    + "                         cm.COMPANY_TYPE,\n"
                    + "                         cm.COMPANY_STATUS,\n"
                    + "                         cm.COMPANY_START_DATE,\n"
                    + "                         cm.COMPANY_END_DATE,\n"
                    + "                         HT.DESCRIPTION AS COMPANY_TRADE_CLASS,\n"
                    + "                         CTC.TRADE_CLASS_START_DATE,\n"
                    + "                         CTC. TRADE_CLASS_END_DATE,\n"
                    + "                         CM.COMPANY_CATEGORY,\n"
                    + "                         CM.COMPANY_GROUP,\n"
                    + "                         PROCESS = CASE\n"
                    + "                                     WHEN COMPANY_TRADE_CLASS IS NOT NULL THEN 1\n"
                    + "                                   END\n"
                    + "         FROM   COMPANY_MASTER cm\n"
                    + "                LEFT JOIN COMPANY_TRADE_CLASS ctc\n"
                    + "                       ON cM.COMPANY_MASTER_SID = ctc.COMPANY_MASTER_SID AND cm.INBOUND_STATUS <> 'D'\n"
                    + "                          AND Getdate() BETWEEN ctc.TRADE_CLASS_START_DATE AND ctc.TRADE_CLASS_END_DATE\n"
                    + "                         LEFT JOIN HELPER_TABLE HT\n"
                    + "                                  ON HT.HELPER_TABLE_SID = CTC.COMPANY_TRADE_CLASS\n"
                    + "                     ";

            if (StringUtils.isNotBlank(searchField) && StringUtils.isNotEmpty(searchVal)) {
                if ("COMPANY_TYPE".equalsIgnoreCase(searchField)) {
                    sql += " JOIN HELPER_TABLE HTT ON HTT.HELPER_TABLE_SID = cm.COMPANY_TYPE\n"
                            + "                                AND HTT.DESCRIPTION LIKE '" + searchVal + "'";
                } else if ("COMPANY_STATUS".equalsIgnoreCase(searchField)) {
                    sql += " JOIN   HELPER_TABLE HTT ON HTT.HELPER_TABLE_SID = cm.COMPANY_STATUS\n"
                            + "                                AND HTT.DESCRIPTION LIKE '" + searchVal + "'";
                } else {
                    sql += " WHERE cm." + searchField + " LIKE '" + searchVal + "'";
                }
            }

            
            sql += "),\n"
                    + "     TRADE_CLASS\n"
                    + "     AS (SELECT COMPANY_MASTER_SID,\n"
                    + "                HT.DESCRIPTION AS COMPANY_TRADE_CLASS\n"
                    + "         FROM   COMPANY_TRADE_CLASS C\n"
                    + "              LEFT JOIN HELPER_TABLE HT\n"
                    + "                                  ON HT.HELPER_TABLE_SID = C.COMPANY_TRADE_CLASS\n"
                    + "         WHERE  EXISTS (SELECT 1\n"
                    + "                        FROM   CTE CT\n"
                    + "                        WHERE  C.COMPANY_MASTER_SID = CT.COMPANY_MASTER_SID\n"
                    + "                               AND COMPANY_TRADE_CLASS IS NULL))\n "
                    + "select\n"
                    + "COMPANY_MASTER_SID,\n"
                    + "COMPANY_ID,\n"
                    + "COMPANY_NO,\n"
                    + "COMPANY_NAME,\n"
                    + "COMPANY_TYPE,\n"
                    + "COMPANY_STATUS,\n"
                    + "COMPANY_START_DATE,\n"
                    + "COMPANY_END_DATE,\n"
                    + "ctype,\n"
                    + " cstatus,\n"
                    + "ccategory,\n"
                    + "cgroup,\n"
                    + "COMPANY_TRADE_CLASS\n"
                    + "from\n"
                    + "("
                    + "SELECT C.COMPANY_MASTER_SID,"
                    + "ROW_NUMBER() OVER( PARTITION BY C.COMPANY_MASTER_SID ORDER BY C.TRADE_CLASS_START_DATE ASC ) AS RN,\n"
                    + "       C.COMPANY_ID,\n"
                    + "       C.COMPANY_NO,\n"
                    + "       C.COMPANY_NAME,\n"
                    + "       C.COMPANY_TYPE,\n"
                    + "       C.COMPANY_STATUS,\n"
                    + "       C.COMPANY_START_DATE,\n"
                    + "       C.COMPANY_END_DATE,\n"
                    + "       htype.DESCRIPTION     AS ctype,\n"
                    + "       hstatus.DESCRIPTION   AS cstatus,\n"
                    + "       hcategory.DESCRIPTION AS ccategory,\n"
                    + "       hgroup.DESCRIPTION    AS cgroup,\n"
                    + "       COMPANY_TRADE_CLASS= CASE\n"
                    + "                              WHEN PROCESS = 1 THEN C.COMPANY_TRADE_CLASS\n"
                    + "                              ELSE T.COMPANY_TRADE_CLASS\n"
                    + "                            END\n"
                    + "FROM   CTE C\n"
                    + "       LEFT JOIN TRADE_CLASS T\n"
                    + "              ON C.COMPANY_MASTER_SID = T.COMPANY_MASTER_SID\n"
                    + "       LEFT JOIN HelPER_TABLE htype\n"
                    + "              ON htype.HELPER_TABLE_SID = C.COMPANY_TYPE\n"
                    + "       LEFT JOIN HELPER_TABLE hstatus\n"
                    + "              ON hstatus.HELPER_TABLE_SID = C.COMPANY_STATUS\n"
                    + "       LEFT JOIN HELPER_TABLE hcategory\n"
                    + "              ON hcategory.HELPER_TABLE_SID = C.COMPANY_CATEGORY\n"
                    + "       LEFT JOIN HELPER_TABLE hgroup\n"
                    + "              ON hgroup.HELPER_TABLE_SID = C.COMPANY_GROUP \n"
                    + "       LEFT JOIN HELPER_TABLE htrade\n"
                    + "              ON htrade.HELPER_TABLE_SID = C.COMPANY_TRADE_CLASS  \n";

            if (StringUtils.isNotBlank(companyNo)) {
                sql += whereCondition + andOperator + "  c.COMPANY_NO LIKE '" + companyNo + "'";
                whereCondition = "";
                andOperator = " AND ";
            }

            if (StringUtils.isNotBlank(companyId)) {
                sql += whereCondition + andOperator + " c.COMPANY_ID LIKE '" + companyId + "'";
                whereCondition = "";
                andOperator= " AND ";
            }

            if (StringUtils.isNotBlank(companyName)) {
                sql += whereCondition + andOperator+ " c.COMPANY_NAME LIKE '" + companyName + "'";
                whereCondition = "";
                andOperator= " AND ";
            }

            if (StringUtils.isNotBlank(companyStatus)) {
                sql += whereCondition + andOperator+ " C.COMPANY_STATUS = " + companyStatus ;
                whereCondition = "";
                andOperator= " AND ";
            }

            if (StringUtils.isNotBlank(companyType)) {
                sql += whereCondition + andOperator+ " C.COMPANY_TYPE = " + companyType;
                whereCondition = "";
                andOperator= " AND ";
            }

            if (!StringUtils.EMPTY.equals(companyTradeClass)) {
                sql += whereCondition + andOperator+ " T.COMPANY_TRADE_CLASS LIKE '" + companyTradeClass + "'";
                whereCondition = "";
                andOperator= " AND ";
            }

            if (!StringUtils.EMPTY.equals(companyCategory)) {
                sql += whereCondition + andOperator+ " C.COMPANY_CATEGORY =" + companyCategory ;
                whereCondition = "";
                andOperator= " AND ";
            }

            if (!StringUtils.EMPTY.equals(companyGroup)) {
                sql += whereCondition+andOperator + " C.COMPANY_GROUP = " + companyGroup ;
            } 
            
            sql += ")\n"
                    + "B where rn=1";
            if (column != null) {
                sql += " ORDER BY " + column + " " + orderBy + " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            }
            LOGGER.debug("getCompaniesList" + sql);
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

}
