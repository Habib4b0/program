
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.logic;

import com.stpl.app.gcm.promotetptocontract.dao.PromoteTpDAO;
import com.stpl.app.gcm.promotetptocontract.dao.impl.PromoteTpDAOImpl;
import com.stpl.app.gcm.util.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class DataQueryLogic {

    private static final Logger LOGGER = org.jboss.logging.Logger.getLogger(DataQueryLogic.class);

    /**
     * Contract Holder Query
     *
     * @param parameters
     * @return
     */
    public List getContractHolders(final Map<String, Object> parameters) {

        try {
            LOGGER.info("Entering getContractHolders method");

            StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
            if (parameters.get("indicator") != null && "ContractHolder".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {

                queryString.append("select distinct CON.CONT_HOLD_COMPANY_MASTER_SID, CM.COMPANY_NO, CM.COMPANY_NAME, compStatus.DESCRIPTION as companyStatus,\n"
                        + " compType.DESCRIPTION as companyType\n"
                        + " FROM COMPANY_MASTER CM \n"
                        + " JOIN CONTRACT_MASTER CON ON CON.CONT_HOLD_COMPANY_MASTER_SID=CM.COMPANY_MASTER_SID\n"
                        + " LEFT JOIN HELPER_TABLE compType on compType.HELPER_TABLE_SID = CM.COMPANY_TYPE \n"
                        + " LEFT JOIN HELPER_TABLE compStatus on compStatus.HELPER_TABLE_SID = CM.COMPANY_STATUS"
                        + " WHERE CON.INBOUND_STATUS <>'D'");

                if (parameters.get("contractHolderId") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("contractHolderId")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("contractHolderId")))) {
                    queryString.append(" AND CON.CONT_HOLD_COMPANY_MASTER_SID LIKE '");
                    queryString.append(String.valueOf(parameters.get("contractHolderId")));
                    queryString.append("'");
                }
                if (parameters.get("contractHolderNo") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("contractHolderNo")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("contractHolderNo")))) {
                    queryString.append(" AND CM.COMPANY_NO LIKE '");
                    queryString.append(String.valueOf(parameters.get("contractHolderNo")));
                    queryString.append("'");
                }
                if (parameters.get("contractHolderName") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("contractHolderName")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("contractHolderName")))) {
                    queryString.append(" AND CM.COMPANY_NAME LIKE '");
                    queryString.append(String.valueOf(parameters.get("contractHolderName")));
                    queryString.append("'");
                }

                if (parameters.get("contractHolderStatus") != null && !Constants.NULL.equals(parameters.get("contractHolderStatus"))) {
                    queryString.append(" AND CM.COMPANY_STATUS in (select HT.HELPER_TABLE_SID from HELPER_TABLE HT where HT.DESCRIPTION='");
                    queryString.append(String.valueOf(parameters.get("contractHolderStatus")));
                    queryString.append("')");
                }
                if (parameters.get("contractHolderType") != null && !Constants.NULL.equals(parameters.get("contractHolderType"))) {
                    queryString.append(" AND CM.COMPANY_TYPE in (select HT.HELPER_TABLE_SID from HELPER_TABLE HT where HT.DESCRIPTION='");
                    queryString.append(String.valueOf(parameters.get("contractHolderType")));
                    queryString.append("')");
                }
            }
            LOGGER.info("Contract Holder Query---->" + queryString.toString());
            PromoteTpDAO promoteTpDAO = new PromoteTpDAOImpl();
            List list2 = new ArrayList();
            list2 = (List) promoteTpDAO.executeSelectQuery(queryString.toString());
            return list2;

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    /**
     * This method to get Rebate Plan Information
     *
     * @param parameters
     * @return
     */
    public List getRebatePlanInfo(final Map<String, Object> parameters) {

        try {
            LOGGER.info("Entering getRebatePlanInfo method");

            StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
            if (parameters.get("indicator") != null && "RebatePlan".equalsIgnoreCase(String.valueOf(parameters.get("indicator")))) {

                queryString.append("SELECT \n"
                        + "  RPM.REBATE_PLAN_MASTER_SID,\n"
                        + "  RPM.REBATE_PLAN_ID,\n"
                        + "  RPM.REBATE_PLAN_NO,\n"
                        + "  RPM.REBATE_PLAN_NAME,\n"
                        + "  HT.DESCRIPTION as rebatePlanStatus,\n"
                        + "  RPM.REBATE_PLAN_TYPE,\n"
                        + "  HT.HELPER_TABLE_SID\n"
                        + "  FROM REBATE_PLAN_MASTER RPM\n"
                        + "  JOIN HELPER_TABLE HT\n"
                        + "  ON RPM.REBATE_PLAN_STATUS = HT.HELPER_TABLE_SID\n"
                        + "  AND HT.LIST_NAME='STATUS'\n"
                        + "  WHERE RPM.INBOUND_STATUS <>'D' ");

                if (parameters.get("rebatePlanId") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("rebatePlanId")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("rebatePlanId")))) {
                    queryString.append(" AND RPM.REBATE_PLAN_ID LIKE '");
                    queryString.append(String.valueOf(parameters.get("rebatePlanId")));
                    queryString.append("'");
                }
                if (parameters.get("rebatePlanNo") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("rebatePlanNo")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("rebatePlanNo")))) {
                    queryString.append(" AND RPM.REBATE_PLAN_NO LIKE '");
                    queryString.append(String.valueOf(parameters.get("rebatePlanNo")));
                    queryString.append("'");
                }
                if (parameters.get("rebatePlanName") != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get("rebatePlanName")))
                        && !StringUtils.isBlank(String.valueOf(parameters.get("rebatePlanName")))) {
                    queryString.append(" AND RPM.REBATE_PLAN_NAME LIKE '");
                    queryString.append(String.valueOf(parameters.get("rebatePlanName")));
                    queryString.append("'");
                }

                if (parameters.get("rebatePlanStatus") != null && !Constants.NULL.equals(parameters.get("rebatePlanStatus"))) {
                    queryString.append(" AND RPM.REBATE_PLAN_STATUS in (select HT.HELPER_TABLE_SID from HELPER_TABLE HT where HT.DESCRIPTION='");
                    queryString.append(String.valueOf(parameters.get("rebatePlanStatus")));
                    queryString.append("')");
                }
                if (parameters.get("rebatePlanType") != null && !Constants.NULL.equals(parameters.get("rebatePlanType"))) {
                    queryString.append(" AND RPM.REBATE_PLAN_TYPE in (select HT.HELPER_TABLE_SID from HELPER_TABLE HT where HT.DESCRIPTION='");
                    queryString.append(String.valueOf(parameters.get("rebatePlanType")));
                    queryString.append("')");
                }
            }
            LOGGER.info("Rebate Plan Query---->" + queryString.toString());
            PromoteTpDAO promoteTpDAO = new PromoteTpDAOImpl();
            List list2 = new ArrayList();
            list2 = (List) promoteTpDAO.executeSelectQuery(queryString.toString());
            return list2;

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
