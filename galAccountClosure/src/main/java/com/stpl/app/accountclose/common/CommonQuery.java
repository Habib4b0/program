/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.common;

import com.stpl.app.accountclose.dao.CommonDao;
import com.stpl.app.accountclose.dao.impl.CommonDaoImpl;
import com.stpl.app.accountclose.dto.BaseRateSummaryDTO;
import com.stpl.app.accountclose.dto.TreeDTO;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import com.stpl.app.accountclose.utils.QueryUtils;
import com.vaadin.server.VaadinSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author vigneshkanna
 */
public class CommonQuery {

    private static final Logger LOGGER = Logger.getLogger(CommonQuery.class);
    final static CommonDao baseRateDAO = CommonDaoImpl.getInstance();
    QueryUtils queryUtils = new QueryUtils();
    CommonUtils utils = new CommonUtils();
    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
    public static final SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");

    public static List getDdlbData(List input, String queryName, String quaryName2) {
        LOGGER.info("Inside getData " + queryName);
        List list = (List<Object[]>) QueryUtils.executeSelectQuery(input, queryName);
        LOGGER.info("End of getDdlbData");
        return list;
    }

    public String getLoadDataQuery(TreeDTO dto, String queryName) {
        String query = StringUtils.EMPTY;
        String append = StringUtils.EMPTY;
        String CG = "JOIN COMPANY_GROUP_DETAILS CGD ON CM.COMPANY_MASTER_SID = CGD.COMPANY_MASTER_SID\n"
                + " JOIN COMPANY_GROUP CG ON CGD.COMPANY_GROUP_SID = CG.COMPANY_GROUP_SID";
        String IG = "JOIN ITEM_GROUP_DETAILS IGD ON IM.ITEM_MASTER_SID = IGD.ITEM_MASTER_SID\n"
                + " JOIN ITEM_GROUP IG ON IGD.ITEM_GROUP_SID = IG.ITEM_GROUP_SID";
        LOGGER.info("Entered getLoadDataQuery for available" + dto.getLevelNo() + "-" + dto.getItemSid());
        if (dto.getLevelNo() == 1) {
            append = "CM.COMPANY_ID,CM.COMPANY_MASTER_SID";
        } else if (dto.getLevelNo() == 2) {
            append = "CN.CONTRACT_ID,CN.CONTRACT_MASTER_SID";
        } else if (dto.getLevelNo() == 3) {
            append = "HT.DESCRIPTION,HT.HELPER_TABLE_SID";
        } else if (dto.getLevelNo() == 4) {
            append = "BM.BRAND_ID,BM.BRAND_MASTER_SID";
        } else if (dto.getLevelNo() == 5) {
            append = "IM.ITEM_ID,IM.ITEM_MASTER_SID";
        }

        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?QU?", append);
        inputMap.put("?CT?", dto.getContractTypeSid());
        inputMap.put("?UDC?", dto.getAcctTypeSid());
        inputMap.put("?RPT?", dto.getAcctSubTypeSid());
        if ("0".equals(dto.getContractSid()) || !StringUtils.isNotBlank(dto.getContractSid())) {
            inputMap.put("?CSID?", "%");
        } else {
            inputMap.put("?CSID?", dto.getContractSid());
        }
        if ("0".equals(dto.getItemSid()) || !StringUtils.isNotBlank(dto.getItemSid())) {
            inputMap.put("?ISID?", "%");
        } else {
            inputMap.put("?ISID?", dto.getItemSid());
        }
        if ("0".equals(dto.getCustGroupSid()) || !StringUtils.isNotBlank(dto.getCustGroupSid())) {
            inputMap.put("?CGSID?", StringUtils.EMPTY);
            inputMap.put("?CG?", StringUtils.EMPTY);
        } else {
            inputMap.put("?CGSID?", "AND CG.COMPANY_GROUP_SID LIKE '" + dto.getCustGroupSid() + "'");
            inputMap.put("?CG?", CG);
        }
        if ("0".equals(dto.getPrdGroupSid()) || !StringUtils.isNotBlank(dto.getPrdGroupSid())) {
            inputMap.put("?PGSID?", StringUtils.EMPTY);
            inputMap.put("?IG?", StringUtils.EMPTY);
        } else {
            inputMap.put("?PGSID?", "AND IG.ITEM_GROUP_SID LIKE '" + dto.getPrdGroupSid() + "'");
            inputMap.put("?IG?", IG);
        }
        inputMap.put("?UID?", userId);
        inputMap.put("?SID?", String.valueOf(dto.getSessionId()));
        inputMap.put("?RPT?", dto.getAcctSubTypeSid());
        query = CommonUtil.getQuery(inputMap, queryName);
        return query;

    }

    public String updateCheckQuery(TreeDTO dto, int val, String type) {

        String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.SESSION_ID));
        String query = getInsertDataQuery(dto, "ac.insertAvailCheck", val, type);
        return query;
    }

    public String getSummaryQuery(BaseRateSummaryDTO dto, String queryName) throws Exception {
        String query = StringUtils.EMPTY;
        String append = StringUtils.EMPTY;
        String filter = StringUtils.EMPTY;
        String itemApppend = StringUtils.EMPTY;
        String itemGroupBy = StringUtils.EMPTY;
        LOGGER.info("Entered getSummaryQuery" + dto.getAcMasterSid());
        try {
            if (dto.getLevelNo() == 1) {
                append = "CM.COMPANY_ID,CM.COMPANY_MASTER_SID";
            } else if (dto.getLevelNo() == 2) {
                append = "CN.CONTRACT_ID,CN.CONTRACT_MASTER_SID";
            } else if (dto.getLevelNo() == 3) {
                append = "HT.DESCRIPTION,HT.HELPER_TABLE_SID";
            } else if (dto.getLevelNo() == 4) {
                append = "BM.BRAND_ID,BM.BRAND_MASTER_SID";
            } else if (dto.getLevelNo() == 5) {
                append = "IM.ITEM_ID, IM.ITEM_MASTER_SID";
                itemApppend = ",ACBM.AC_BR_METHODOLOGY_DETAILS_SID,AVG(BRS.CURRENT_RATE) AS CURRENT_RATE,AVG(BRS.SUGGESTED_RATE) AS SUGGESTED_RATE,\n"
                        + " AVG(BRS.ACCRUAL_RATE) AS ACCRUAL_RATE,\n"
                        + " ACBM.METHODOLOGY_START_DATE,ACBM.METHODOLOGY_END_DATE,\n"
                        + " ACBM.METHODOLOGY_NAME,HTRC.DESCRIPTION,BRS.NOTES";
                itemGroupBy = " ,ACBM.AC_BR_METHODOLOGY_DETAILS_SID,ACBM.METHODOLOGY_START_DATE,ACBM.METHODOLOGY_END_DATE,\n"
                        + " ACBM.METHODOLOGY_NAME,HTRC.DESCRIPTION,BRS.NOTES";

            }

            if (!utils.getNull(dto.getStartDate()) && !utils.getNull(dto.getEndDate())) {
                Date sdate = new Date(dto.getStartDate());
                Date edate = new Date((dto.getEndDate()));
                filter = "AND ACBM.METHODOLOGY_START_DATE = '" + getDBDate(sdate) + "' "
                        + "AND ACBM.METHODOLOGY_END_DATE = '" + getDBDate(edate) + "'";
            }
            Map<String, String> inputMap = new HashMap<String, String>();
            inputMap.put("?SEL?", append);
            inputMap.put("?AID?", String.valueOf(dto.getAcMasterSid()));
            inputMap.put("?SID?", dto.getSessionId());
            inputMap.put("?UID?", userId);
            inputMap.put("?FIL?", filter);
            if (!utils.getNull(String.valueOf(dto.getMethodologySid())) && dto.getMethodologySid() != 0) {
                inputMap.put("?MID?", " AND ACBM.AC_BR_METHODOLOGY_DETAILS_SID = " + String.valueOf(dto.getMethodologySid()));
            } else {
                inputMap.put("?MID?", StringUtils.EMPTY);
            }
            if (dto.getLevelNo() == 5) {
                inputMap.put("?ITM?", itemApppend);
                inputMap.put("?ITMGR?", itemGroupBy);
            } else {
                inputMap.put("?ITM?", StringUtils.EMPTY);
                inputMap.put("?ITMGR?", StringUtils.EMPTY);
            }
            query = CommonUtil.getQuery(inputMap, queryName);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return query;

    }

    public String getDBDate(final Date input) {
        SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd");
        return input == null ? StringUtils.EMPTY : temp.format(input);
    }

    public String periodSidQuery(String checked, String fre,
            Map<String, Integer> salesMap, Map<String, Integer> paymentMap, Map<String, Integer> textMap) {
        String append = StringUtils.EMPTY;
        List yrList = new ArrayList();
        List freList = new ArrayList();
        String text = StringUtils.EMPTY;
        if (textMap.size() > 0) {
            text = " ," + textMap.get(checked) + " AS Value";
        }
        if (fre.equals(MONTHLY.getConstant())) {
            freList.add(utils.getMonthNo(checked.substring(0, 3)));
            yrList.add(checked.substring(4, 7));
        } else {
            freList.add(checked.substring(1, 2));
            yrList.add(checked.substring(2, 6));
        }
        if (fre.equals(QUARTERLY.getConstant())) {
            append = "and QUARTER in (" + utils.CollectionToString(freList, true) + ")";
        } else if (fre.equals(SEMI_ANNUALLY.getConstant())) {
            append = "and SEMI_ANNUAL in (" + utils.CollectionToString(freList, true) + ")";
        } else if (fre.equals(MONTHLY.getConstant())) {
            append = "and \"MONTH\" in (" + utils.CollectionToString(freList, true) + ")";
        } else if (fre.equals(ANNUALLY.getConstant())) {
        }
        String query = "select PERIOD_SID," + salesMap.get(checked)
                + " AS SALES," + paymentMap.get(checked) + " AS PAYMENTS" + text
                + " from \"PERIOD\" where \"YEAR\" in (" + utils.CollectionToString(yrList, true) + ")";
        query = query + append;
        return query;
    }

    public String getDataQuery(TreeDTO dto, String queryName) {

        String query = StringUtils.EMPTY;
        String append = StringUtils.EMPTY;
        String orderBy = StringUtils.EMPTY;
        String selectBy = StringUtils.EMPTY;
        String CG = "JOIN COMPANY_GROUP_DETAILS CGD ON comp.COMPANY_MASTER_SID = CGD.COMPANY_MASTER_SID\n"
                + " JOIN COMPANY_GROUP CG ON CGD.COMPANY_GROUP_SID = CG.COMPANY_GROUP_SID";
        String IG = "JOIN ITEM_GROUP_DETAILS IGD ON IM.ITEM_MASTER_SID = IGD.ITEM_MASTER_SID\n"
                + " JOIN ITEM_GROUP IG ON IGD.ITEM_GROUP_SID = IG.ITEM_GROUP_SID";
        LOGGER.info("Entered getLoadDataQuery for available" + dto.getLevelNo() + "-" + dto.getItemSid());
        if (dto.getLevelNo() == 1) {
            append = "company";
            orderBy = "COMPANY_ID";
            selectBy = "A.COMPANY_ID,A.COMPANY_MASTER_SID";
        } else if (dto.getLevelNo() == 2) {
            append = "Contract";
            orderBy = "CONTRACT_ID";
            selectBy = "A.CONTRACT_ID,A.CONTRACT_MASTER_SID";
        } else if (dto.getLevelNo() == 3) {
            append = "Therapeatic_class";
            orderBy = "THERAPEATIC_CLASS_DESC";
            selectBy = "A.THERAPEATIC_CLASS_DESC,A.THERAPEUTIC_CLASS";
        } else if (dto.getLevelNo() == 4) {
            append = "Brand";
            orderBy = "BRAND_ID";
            selectBy = "A.BRAND_ID,A.BRAND_MASTER_SID";
        } else if (dto.getLevelNo() == 5) {
            append = "Item";
            orderBy = "ITEM_ID";
            selectBy = "A.ITEM_ID,A.ITEM_MASTER_SID";
        }

        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?QU?", append);
        inputMap.put("?CT?", dto.getContractTypeSid());
        inputMap.put("?UDC?", dto.getAcctTypeSid());
        inputMap.put("?RPT?", dto.getAcctSubTypeSid());
        inputMap.put("?OBY?", orderBy);
        if ("0".equals(dto.getContractSid()) || !StringUtils.isNotBlank(dto.getContractSid())) {
            inputMap.put("?CSID?", "%");
        } else {
            inputMap.put("?CSID?", dto.getContractSid());
        }
        if ("0".equals(dto.getItemSid()) || !StringUtils.isNotBlank(dto.getItemSid())) {
            inputMap.put("?ISID?", "%");
        } else {
            inputMap.put("?ISID?", dto.getItemSid());
        }
        if ("0".equals(dto.getCustGroupSid()) || !StringUtils.isNotBlank(dto.getCustGroupSid())) {
            inputMap.put("?CGSID?", StringUtils.EMPTY);
            inputMap.put("?CG?", StringUtils.EMPTY);
        } else {
            inputMap.put("?CGSID?", "AND CG.COMPANY_GROUP_SID LIKE '" + dto.getCustGroupSid() + "'");
            inputMap.put("?CG?", CG);
        }
        if ("0".equals(dto.getPrdGroupSid()) || !StringUtils.isNotBlank(dto.getPrdGroupSid())) {
            inputMap.put("?PGSID?", StringUtils.EMPTY);
            inputMap.put("?IG?", StringUtils.EMPTY);
        } else {
            inputMap.put("?PGSID?", "AND IG.ITEM_GROUP_SID LIKE '" + dto.getPrdGroupSid() + "'");
            inputMap.put("?IG?", IG);
        }
        if ("0".equals(dto.getComapnySid()) || !StringUtils.isNotBlank(dto.getComapnySid())) {
            inputMap.put("?CMSID?", "%");
        } else {
            inputMap.put("?CMSID?", dto.getComapnySid());
        }
        if (!"0".equals(dto.getAccountClosureMasterSid()) || StringUtils.isNotBlank(dto.getAccountClosureMasterSid())) {
            inputMap.put("?AMID?", dto.getAccountClosureMasterSid());
        }
        inputMap.put("?UID?", userId);
        inputMap.put("?SID?", String.valueOf(dto.getSessionId()));
        inputMap.put("?RPT?", dto.getAcctSubTypeSid());
        inputMap.put("?MOD?", "BaseRateAvail");
        inputMap.put("?SELQUE?", selectBy);
        query = CommonUtil.getQuery(inputMap, queryName);
        return query;

    }

    public String getInsertDataQuery(TreeDTO dto, String queryName, int val, String type) {

        String query = StringUtils.EMPTY;

        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?CT?", dto.getContractTypeSid());
        inputMap.put("?UDC?", dto.getAcctTypeSid());
        inputMap.put("?RPT?", dto.getAcctSubTypeSid());

        if ("0".equals(dto.getComapnySid()) || !StringUtils.isNotBlank(dto.getComapnySid())) {
            inputMap.put("?CMSID?", "%");
        } else {
            inputMap.put("?CMSID?", dto.getComapnySid());
        }
        inputMap.put("?UID?", userId);
        inputMap.put("?SID?", String.valueOf(dto.getSessionId()));
        inputMap.put("?RPT?", dto.getAcctSubTypeSid());
        inputMap.put("?MOD?", type);
        inputMap.put("?VAL?", String.valueOf(val));
        query = CommonUtil.getQuery(inputMap, queryName);
        return query;

    }
}
