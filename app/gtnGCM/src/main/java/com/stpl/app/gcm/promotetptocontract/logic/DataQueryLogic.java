
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.logic;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.promotetptocontract.dao.PromoteTpDAO;
import com.stpl.app.gcm.promotetptocontract.dao.impl.PromoteTpDAOImpl;
import com.stpl.app.gcm.util.Constants;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author alok.v
 */
public class DataQueryLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataQueryLogic.class);

    /**
     * Contract Holder Query
     *
     * @param parameters
     * @return
     */
    public List getContractHolders(final Map<String, Object> parameters) {

        try {
            LOGGER.debug("Entering getContractHolders method");

            StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
            if (parameters.get(StringConstantsUtil.INDICATOR) != null && "ContractHolder".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {

                queryString.append("select distinct CON.CONT_HOLD_COMPANY_MASTER_SID, CM.COMPANY_NO, CM.COMPANY_NAME, compStatus.DESCRIPTION as companyStatus,\n"
                        + " compType.DESCRIPTION as companyType\n"
                        + " FROM COMPANY_MASTER CM \n"
                        + " JOIN CONTRACT_MASTER CON ON CON.CONT_HOLD_COMPANY_MASTER_SID=CM.COMPANY_MASTER_SID\n"
                        + " LEFT JOIN HELPER_TABLE compType on compType.HELPER_TABLE_SID = CM.COMPANY_TYPE \n"
                        + " LEFT JOIN HELPER_TABLE compStatus on compStatus.HELPER_TABLE_SID = CM.COMPANY_STATUS"
                        + " WHERE CON.INBOUND_STATUS <>'D'");

                if (parameters.get(StringConstantsUtil.CONTRACT_HOLDER_ID) != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_ID)))
                        && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_ID)))) {
                    queryString.append(" AND CON.CONT_HOLD_COMPANY_MASTER_SID LIKE '");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_ID)));
                    queryString.append('\'');
                }
                if (parameters.get(StringConstantsUtil.CONTRACT_HOLDER_NO) != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_NO)))
                        && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_NO)))) {
                    queryString.append(" AND CM.COMPANY_NO LIKE '");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_NO)));
                    queryString.append('\'');
                }
                if (parameters.get(StringConstantsUtil.CONTRACT_HOLDER_NAME) != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_NAME)))
                        && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_NAME)))) {
                    queryString.append(" AND CM.COMPANY_NAME LIKE '");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_NAME)));
                    queryString.append('\'');
                }

                if (parameters.get(StringConstantsUtil.CONTRACT_HOLDER_STATUS) != null && !Constants.NULL.equals(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_STATUS))) {
                    queryString.append(" AND CM.COMPANY_STATUS in (select HT.HELPER_TABLE_SID from HELPER_TABLE HT where HT.DESCRIPTION='");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_STATUS)));
                    queryString.append("')");
                }
                if (parameters.get(StringConstantsUtil.CONTRACT_HOLDER_TYPE) != null && !Constants.NULL.equals(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_TYPE))) {
                    queryString.append(" AND CM.COMPANY_TYPE =");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.CONTRACT_HOLDER_TYPE)));
                }
            }
            LOGGER.debug("Contract Holder Query----> {} " , queryString.toString());
            PromoteTpDAO promoteTpDAO = new PromoteTpDAOImpl();
            List list2;
            list2 = (List) promoteTpDAO.executeSelectQuery(queryString.toString());
            return list2;

        } catch (Exception e) {
            LOGGER.error("",e);
            return Collections.emptyList();
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
            LOGGER.debug("Entering getRebatePlanInfo method");

            StringBuilder queryString = new StringBuilder(StringUtils.EMPTY);
            if (parameters.get(StringConstantsUtil.INDICATOR) != null && "RebatePlan".equalsIgnoreCase(String.valueOf(parameters.get(StringConstantsUtil.INDICATOR)))) {

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

                if (parameters.get(StringConstantsUtil.REBATE_PLAN_ID) != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get(StringConstantsUtil.REBATE_PLAN_ID)))
                        && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.REBATE_PLAN_ID)))) {
                    queryString.append(" AND RPM.REBATE_PLAN_ID LIKE '");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.REBATE_PLAN_ID)));
                    queryString.append('\'');
                }
                if (parameters.get(StringConstantsUtil.REBATE_PLAN_NO) != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get(StringConstantsUtil.REBATE_PLAN_NO)))
                        && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.REBATE_PLAN_NO)))) {
                    queryString.append(" AND RPM.REBATE_PLAN_NO LIKE '");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.REBATE_PLAN_NO)));
                    queryString.append('\'');
                }
                if (parameters.get(StringConstantsUtil.REBATE_PLAN_NAME) != null
                        && !StringUtils.isEmpty(String.valueOf(parameters.get(StringConstantsUtil.REBATE_PLAN_NAME)))
                        && !StringUtils.isBlank(String.valueOf(parameters.get(StringConstantsUtil.REBATE_PLAN_NAME)))) {
                    queryString.append(" AND RPM.REBATE_PLAN_NAME LIKE '");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.REBATE_PLAN_NAME)));
                    queryString.append('\'');
                }

                if (parameters.get(StringConstantsUtil.REBATE_PLAN_STATUS) != null && !Constants.NULL.equals(parameters.get(StringConstantsUtil.REBATE_PLAN_STATUS))) {
                    queryString.append(" AND RPM.REBATE_PLAN_STATUS in (select HT.HELPER_TABLE_SID from HELPER_TABLE HT where HT.DESCRIPTION='");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.REBATE_PLAN_STATUS)));
                    queryString.append("')");
                }
                if (parameters.get(StringConstantsUtil.REBATE_PLAN_TYPE) != null && !Constants.NULL.equals(parameters.get(StringConstantsUtil.REBATE_PLAN_TYPE))) {
                    queryString.append(" AND RPM.REBATE_PLAN_TYPE in (select HT.HELPER_TABLE_SID from HELPER_TABLE HT where HT.DESCRIPTION='");
                    queryString.append(String.valueOf(parameters.get(StringConstantsUtil.REBATE_PLAN_TYPE)));
                    queryString.append("')");
                }
            }
            LOGGER.debug("Rebate Plan Query----> {} " , queryString.toString());
            PromoteTpDAO promoteTpDAO = new PromoteTpDAOImpl();
            List list2;
            list2 = (List) promoteTpDAO.executeSelectQuery(queryString.toString());
            return list2;

        } catch (Exception e) {
            LOGGER.error("",e);
            return Collections.emptyList();
        }
    }
}
