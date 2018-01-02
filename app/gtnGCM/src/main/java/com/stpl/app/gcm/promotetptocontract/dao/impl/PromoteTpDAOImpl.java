/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.promotetptocontract.dao.impl;

import com.stpl.app.model.HelperTable;
import com.stpl.app.gcm.promotetptocontract.dao.PromoteTpDAO;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.promotetptocontract.dto.CurrentContractDTO;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.COMPANY_FAMILY_PLAN;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.ITEM_FAMILY_PLAN;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.PRICE_SCHEDULE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.REBATE_SCHEDULE;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.TAB_TRANSFER_CONTRACT;
import com.stpl.app.model.GcmGlobalDetails;
import com.stpl.app.service.GcmGlobalDetailsLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import java.util.logging.Level;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class PromoteTpDAOImpl implements PromoteTpDAO {
public static final Logger LOGGER = Logger.getLogger(PromoteTpDAOImpl.class);
    @Override
    public List companyCount(final Map<String, Object> parameters) throws SystemException {

        return CompanyMasterLocalServiceUtil.searchTPCompanies(parameters);

    }

    @Override
    public List searchTPCompanies(final Map<String, Object> parameters) throws SystemException {

        return CompanyMasterLocalServiceUtil.searchTPCompanies(parameters);

    }

@Override
    public List loadCompanyTypeDDLB(Map<String, Object> parameters) {

        return CompanyMasterLocalServiceUtil.getCompanyTypeCount(parameters);
    }

@Override
    public int getCompanyTypeCount(DynamicQuery dynamicQuery) {
        try {
            return (int) CompanyMasterLocalServiceUtil.dynamicQueryCount(dynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return 0;
    }

@Override
    public List<Object[]> getCompanyTypes(DynamicQuery dynamicQuery) {
        try {
            return CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return Collections.emptyList();
    }

    @Override
    public List searchCurrentContracts(final Map<String, Object> parameters) throws SystemException {

        return ContractMasterLocalServiceUtil.searchContractsForPromoteTp(parameters);
    }

    /**
     * Get Selected Component Info
     *
     * @param componentSelectionValue
     * @param rebateId
     * @return
     */
@Override
    public List getComponentInfo(String componentSelectionValue, String rebateId) {
        String query1;
        String query2;
        if (REBATE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
            query1 = "select RS.RS_ID, RS.RS_NO, RS.RS_NAME, RS.RS_STATUS, \n"
                    + " '' AS RAR_TYPE, \n"
                    + "RS.REBATE_FREQUENCY, \n"
                    + "RS.INTEREST_BEARING_BASIS, \n"
                    + "RS.RS_START_DATE, RS.RS_END_DATE \n"
                    + "from RS_CONTRACT RS where RS.RS_CONTRACT_SID = " + rebateId;

            query2 = "Select IM.ITEM_NO, IM.ITEM_NAME, IM.THERAPEUTIC_CLASS, B.BRAND_NAME, \n"
                    + "IM.ITEM_STATUS, RSD.ITEM_REBATE_START_DATE, RSD.ITEM_REBATE_END_DATE, \n"
                    + "RP.REBATE_PLAN_NAME, \n"
                    + "RSD.FORMULA_ID \n"
                    + "from \n"
                    + "RS_CONTRACT RS JOIN RS_CONTRACT_DETAILS RSD ON RS.RS_CONTRACT_SID = " + rebateId + " and RSD.RS_CONTRACT_SID = RS.RS_CONTRACT_SID \n"
                    + "JOIN ITEM_MASTER IM ON RSD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID \n"
                    + "JOIN BRAND_MASTER B ON IM.BRAND_MASTER_SID = B.BRAND_MASTER_SID \n"
                    + "LEFT JOIN REBATE_PLAN_MASTER RP ON RSD.REBATE_PLAN_MASTER_SID = RP.REBATE_PLAN_MASTER_SID";
            String[] queries = new String[NumericConstants.TWO];
            queries[0] = query1;
            queries[1] = query2;

            return (List) ContractMasterLocalServiceUtil.executeSelectQueries(queries);
        }
        return new ArrayList();
    }

@Override
    public Object executeSelectQuery(String query) throws SystemException, PortalException {

        return HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

@Override
    public List<HelperTable> getHelperTableList(final DynamicQuery dynamicQuery) throws SystemException {
        return HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

@Override
    public void updateCFP(List<Object> input) {
        String sql = CustomSQLUtil.get("Existing.saveCFP");
        sql = sql.replaceFirst("[?]", input.get(0).toString());
        sql = sql.replaceFirst("[?]", input.get(1).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.TWO).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.THREE).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FOUR).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FIVE).toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);
    }

@Override
    public void updateIFP(List<Object> input) {
        String sql = CustomSQLUtil.get("Existing.saveIFP");
        sql = sql.replaceFirst("[?]", input.get(0).toString());
        sql = sql.replaceFirst("[?]", input.get(1).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.TWO).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.THREE).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FOUR).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FIVE).toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);
    }

@Override
    public void updatePS(List<Object> input) {
        String sql = CustomSQLUtil.get("Existing.savePS");
        sql = sql.replaceFirst("[?]", input.get(0).toString());
        sql = sql.replaceFirst("[?]", input.get(1).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.TWO).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.THREE).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FOUR).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FIVE).toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);
    }

@Override
    public void updateRS(List<Object> input) {
        String sql = CustomSQLUtil.get("Existing.saveRS");
        sql = sql.replaceFirst("[?]", input.get(0).toString());
        sql = sql.replaceFirst("[?]", input.get(1).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.TWO).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.THREE).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FOUR).toString());
        sql = sql.replaceFirst("[?]", input.get(NumericConstants.FIVE).toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(sql);
    }

@Override
    public List getItems(String query) throws SystemException {
        return (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }
  
@Override
      public void updateSubmitFlag(String screenName, String userId, String sessionId, boolean flag,CurrentContractDTO dto) {
    try {
        String udcValue = Constants.ZEROSTRING;
        if (flag) {
            udcValue = "1";
            if (TAB_TRANSFER_CONTRACT.getConstant().equals(screenName)) {
                udcValue = "2";
            }
        }
        GcmGlobalDetails temp = GcmGlobalDetailsLocalServiceUtil.createGcmGlobalDetails(0);
        if (dto.getProjectionId() != null && !dto.getProjectionId().isEmpty()) {
            temp.setProjectionMasterSid(Integer.valueOf(dto.getProjectionId()));
        }
        if (dto.getContractId() != null && !dto.getContractId().isEmpty()) {
            temp.setContractMasterSid(Integer.valueOf(dto.getContractId()));
        }
        if (dto.getIfpId() != null) {
            temp.setIfpModelSid(dto.getIFPId());
        }
        if (dto.getCfpId() != null) {
            temp.setCfpModelSid(dto.getCfpId());
        }
        if (dto.getRsId() != null) {
            temp.setRsModelSid(dto.getRsId());
        }
        temp.setOperation("Promote_TP_Submit");
        temp.setSessionId(sessionId);
        temp.setCheckRecord(true);
        temp.setUserId(Integer.valueOf(userId));
        GcmGlobalDetailsLocalServiceUtil.updateGcmGlobalDetails(temp);
             
        String updateQuery = "UPDATE GCM_GLOBAL_DETAILS set CHECK_RECORD = '0' , OPERATION = '" + udcValue + "' where USER_ID='" + userId + "' AND SESSION_ID='" + sessionId + "' AND CHECK_RECORD='1' AND SCREEN_NAME = '" + screenName + "'";
        CompanyMasterLocalServiceUtil.executeUpdateQuery(updateQuery);
    } catch (SystemException ex) {
        java.util.logging.Logger.getLogger(PromoteTpDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

@Override
    public List getComponentInformation(String componentSelectionValue, String[] id) {
        String query1 = StringUtils.EMPTY;
        String query2 = StringUtils.EMPTY;

        if (REBATE_SCHEDULE.getConstant().equals(componentSelectionValue)) {

            query1 = " select RS.RS_ID, RS.RS_NO, RS.RS_NAME,HT.DESCRIPTION as RS_STATUS,RS.RS_START_DATE, RS.RS_END_DATE,  '' AS RAR_TYPE,RS.REBATE_FREQUENCY, RS.INTEREST_BEARING_BASIS "
                    + " from RS_CONTRACT RS "
                    + " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=RS.RS_STATUS "
                    + " WHERE RS.CFP_CONTRACT_SID = " + id[0] + " and RS.IFP_CONTRACT_SID = " + id[1] + " and RS.PS_CONTRACT_SID = " + id[NumericConstants.TWO] + " and RS.RS_CONTRACT_SID = " + id[NumericConstants.THREE];

            query2 = "Select IM.ITEM_NO, IM.ITEM_NAME, B.BRAND_NAME, \n"
                    + "(case when  HT.HELPER_TABLE_SID=0 then ' ' else HT.DESCRIPTION end) AS ITEM_STATUS, RSD.ITEM_REBATE_START_DATE, RSD.ITEM_REBATE_END_DATE, \n"
                    + "'' AS FORMULA_TYPE, \n"
                    + "(case when  FDM.FORMULA_ID='0' AND FDM.FORMULA_ID='null' then ' ' else FDM.FORMULA_NO end), \n"
                    + "'' AS FORMULA_NAME,\n"
                    + "RP.REBATE_PLAN_ID, RP.REBATE_PLAN_NAME, RSD.REBATE_AMOUNT, RSD.BUNDLE_NO \n"
                    + "from RS_CONTRACT RS "
                    + "JOIN RS_CONTRACT_DETAILS RSD ON RSD.RS_CONTRACT_SID = RS.RS_CONTRACT_SID \n"
                    + "JOIN ITEM_MASTER IM ON RSD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID \n"
                    + "JOIN BRAND_MASTER B ON IM.BRAND_MASTER_SID =  B.BRAND_MASTER_SID \n"
                    + "JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = IM.ITEM_STATUS \n"
                    + "LEFT JOIN REBATE_PLAN_MASTER RP ON RSD.REBATE_PLAN_MASTER_SID = RP.REBATE_PLAN_MASTER_SID \n"
                    + " LEFT JOIN FORMULA_DETAILS_MASTER FDM ON FDM.FORMULA_ID = RSD.FORMULA_ID"
                    + " WHERE RS.CFP_CONTRACT_SID = " + id[0] + " and RS.IFP_CONTRACT_SID = " + id[1] + " and RS.PS_CONTRACT_SID = " + id[NumericConstants.TWO] + " and RS.RS_CONTRACT_SID = " + id[NumericConstants.THREE];

        } else if (COMPANY_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {

            query1 = "  select CFPM.CFP_ID,CPFC.CFP_NO,CPFC.CFP_NAME,HT.DESCRIPTION as CFP_STATUS,CPFC.CFP_START_DATE,CPFC.CFP_END_DATE,' ' as col1,' ' as col2, ' ' as col3   "
                    + " from CFP_CONTRACT CPFC join CFP_MODEL CFPM ON CPFC.CFP_MODEL_SID=CFPM.CFP_MODEL_SID "
                    + " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=CPFC.CFP_STATUS "
                    + " WHERE CPFC.CFP_CONTRACT_SID = " + id[0];
            query2 = " Select CM.COMPANY_NO, CM.COMPANY_NAME, CNM.CONTRACT_NO, CFP.CFP_START_DATE, CFP.CFP_END_DATE, (case when  HT.HELPER_TABLE_SID=0 then ' ' else HT.DESCRIPTION end) AS STATUS, (case when  HT_CFP.HELPER_TABLE_SID=0 then ' ' else HT_CFP.DESCRIPTION end) AS TRADE_CLASS, CFP.CFP_CONTRACT_ATTACHED_DATE \n"
                    + " from CFP_CONTRACT CFP \n"
                    + " JOIN CONTRACT_MASTER CNM ON CFP.CONTRACT_MASTER_SID = CNM.CONTRACT_MASTER_SID \n"
                    + " JOIN CFP_CONTRACT_DETAILS CFPD ON CFP.CFP_CONTRACT_SID = CFPD.CFP_CONTRACT_SID\n"
                    + " JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID = CFPD.COMPANY_MASTER_SID \n"
                    + " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = CFP.CFP_STATUS \n"
                    + " JOIN HELPER_TABLE HT_CFP ON HT_CFP.HELPER_TABLE_SID = CFP.CFP_TRADE_CLASS \n"
                    + " WHERE CFP.CFP_CONTRACT_SID = " + id[0];

        } else if (ITEM_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
            query1 = "  select IFPM.IFP_ID,IFPC.IFP_NO,IFPC.IFP_NAME,(case when  HT.HELPER_TABLE_SID=0 then ' ' else HT.DESCRIPTION end) as IFP_STATUS,IFPC.IFP_START_DATE,IFPC.IFP_END_DATE ,' ' as col1,' ' as col2, ' ' as col3 "
                    + " from IFP_CONTRACT IFPC join IFP_MODEL IFPM ON IFPM.IFP_MODEL_SID=IFPC.IFP_MODEL_SID "
                    + " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=IFPC.IFP_STATUS "
                    + " WHERE IFPC.CFP_CONTRACT_SID = " + id[0] + " and IFPC.IFP_CONTRACT_SID = " + id[1];
            query2 = "Select IM.ITEM_NO, IM.ITEM_NAME, B.BRAND_NAME, HT.DESCRIPTION, \n"
                    + "IFP.IFP_START_DATE, IFP.IFP_END_DATE, IFP.IFP_CONTRACT_ATTACHED_DATE \n"
                    + "from IFP_CONTRACT IFP \n"
                    + "JOIN IFP_CONTRACT_DETAILS IFPD ON IFPD.IFP_CONTRACT_SID = IFP.IFP_CONTRACT_SID \n"
                    + "JOIN ITEM_MASTER IM ON IFPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID \n"
                    + "JOIN BRAND_MASTER B ON IM.BRAND_MASTER_SID = B.BRAND_MASTER_SID \n"
                    + "JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = IM.ITEM_STATUS \n"
                    + " WHERE IFP.CFP_CONTRACT_SID = " + id[0] + " and IFP.IFP_CONTRACT_SID = " + id[1];
        } else if (PRICE_SCHEDULE.getConstant().equals(componentSelectionValue)) {

            query1 = "    select PSM.PS_ID,PSC.PS_NO,PSC.PS_NAME,(case when  HT.HELPER_TABLE_SID=0 then ' ' else HT.DESCRIPTION end) as PS_STATUS,PSC.PS_START_DATE,PSC.PS_START_DATE,' ' as col1,' ' as col2, ' ' as col3  from PS_CONTRACT PSC "
                    + " join PS_MODEL PSM ON PSM.PS_MODEL_SID=PSC.PS_MODEL_SID "
                    + " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=PSC.PS_STATUS WHERE "
                    + " PSC.CFP_CONTRACT_SID = " + id[0] + " and PSC.IFP_CONTRACT_SID = " + id[1] + " and PSC.PS_CONTRACT_SID = " + id[NumericConstants.TWO];
            query2 = "Select IM.ITEM_NO, IM.ITEM_NAME, B.BRAND_NAME, HT.DESCRIPTION, \n"
                    + "PS.PS_START_DATE, PS.PS_END_DATE, PS.PS_CONTRACT_ATTACHED_DATE, \n"
                    + "'' AS PRICE_TYPE, '' AS PRICE_PLAN_NO, '' AS PRICE_PLAN_NAME, \n"
                    + "'' AS PRICE_PROTECTION_STATUS, PSD.PRICE_PROTECTION_START_DATE, PSD.PRICE_PROTECTION_END_DATE,\n"
                    + "'' AS PRICE_PROTECTION_PRICE_TYPE, (case when  HT_PTI.HELPER_TABLE_SID=0 then ' ' else HT_PTI.DESCRIPTION end) AS PRICE_TOLERANCE_INTERVAL, \n"
                    + "(case when  HT_PTF.HELPER_TABLE_SID=0 then ' ' else HT_PTF.DESCRIPTION end) AS PRICE_TOLERANCE_FREQUENCY, (case when  HT_PTT.HELPER_TABLE_SID=0 then ' ' else HT_PTT.DESCRIPTION end) AS PRICE_TOLERANCE_TYPE, \n"
                    + "'' AS MAX_INCREMENTAL_CHANGE, PSD.PRICE_TOLERANCE, '' AS RESET_ELIGIBLE,'' AS RESET_TYPE, '' AS RESET_DATE, '' AS RESET_INTERVAL,'' AS RESET_FREQUENCY,\n"
                    + " PS.PS_CONTRACT_ATTACHED_DATE\n"
                    + " from PS_CONTRACT PS \n"
                    + " JOIN PS_CONTRACT_DETAILS PSD ON  PSD.PS_CONTRACT_SID = PS.PS_CONTRACT_SID \n"
                    + " JOIN ITEM_MASTER IM ON PSD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID \n"
                    + " JOIN BRAND_MASTER B ON IM.BRAND_MASTER_SID = B.BRAND_MASTER_SID \n"
                    + " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = IM.ITEM_STATUS \n"
                    + " LEFT JOIN HELPER_TABLE HT_PTI ON HT_PTI.HELPER_TABLE_SID = PSD.PRICE_TOLERANCE_INTERVAL \n"
                    + " LEFT JOIN HELPER_TABLE HT_PTF ON HT_PTF.HELPER_TABLE_SID = PSD.PRICE_TOLERANCE_FREQUENCY \n"
                    + " LEFT JOIN HELPER_TABLE HT_PTT ON HT_PTT.HELPER_TABLE_SID = PSD.PRICE_TOLERANCE_TYPE \n"
                    + " WHERE PS.CFP_CONTRACT_SID = " + id[0] + " and PS.IFP_CONTRACT_SID = " + id[1] + " and PS.PS_CONTRACT_SID = " + id[NumericConstants.TWO];
        }
        String[] queries = new String[NumericConstants.TWO];
        queries[0] = query1;
        queries[1] = query2;

        return (List) ContractMasterLocalServiceUtil.executeSelectQueries(queries);
    }

@Override
    public List startDateAndEndDateValidation(String userId, String sessionId, String screenName) {
        String query1 = "select count(*) from GCM_GLOBAL_DETAILS where USER_ID =  '" + userId + "' AND SESSION_ID =  '" + sessionId + "' and SCREEN_NAME =  '" + screenName + "' and CHECK_RECORD = '1'";
        String query2 = "select count(*) from GCM_GLOBAL_DETAILS where USER_ID = '" + userId + "' AND SESSION_ID = '" + sessionId + "' and SCREEN_NAME = '" + screenName + "' and CHECK_RECORD = '1' and END_DATE IS NOT NULL";

        String[] queries = new String[NumericConstants.TWO];
        queries[0] = query1;
        queries[1] = query2;

        return (List) ContractMasterLocalServiceUtil.executeSelectQueries(queries);
    }

@Override
    public List isAnyRecordSelected(String userId, String sessionId, String screenName) {
        String query = "select count(*) from GCM_GLOBAL_DETAILS where USER_ID = '" + userId + "' AND SESSION_ID = '" + sessionId + "' and SCREEN_NAME = '" + screenName + "' and CHECK_RECORD = '1'";

        return (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
    }

@Override
    public List searchCompanies(Map<String, Object> parameters) throws SystemException {
        return CompanyMasterLocalServiceUtil.searchCompanies(parameters);
    }
}
