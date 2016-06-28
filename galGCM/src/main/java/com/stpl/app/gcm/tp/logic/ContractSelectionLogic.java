

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.logic;

import com.stpl.app.gcm.common.HelperListUtil;
import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.gcm.tp.dao.TradingPartnerDAO;
import com.stpl.app.gcm.tp.dao.impl.TradingPartnerDAOImpl;
import com.stpl.app.gcm.tp.dto.ComponentInformationDTO;
import com.stpl.app.gcm.util.AbstractNotificationUtils;
import com.stpl.app.gcm.util.CommonUtils;
import static com.stpl.app.gcm.util.CommonUtils.convertToInteger;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.*;
import static com.stpl.app.gcm.util.Converters.convertNullToEmpty;
import static com.stpl.app.gcm.util.Converters.formatDate;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sriram
 */
public class ContractSelectionLogic {

    private static final Logger LOGGER = Logger.getLogger(ContractSelectionLogic.class);
    TradingPartnerDAO dao = new TradingPartnerDAOImpl();
    static CommonDao DAO = CommonImpl.getInstance();
    private final HelperListUtil helperListUtil = HelperListUtil.getInstance();

    public int getComponentInformationCount(String componentSelectionValue, String[] id, Set<Container.Filter> filters) {
        List<Object[]> componentInformationList = getComponentInformationData(componentSelectionValue, id, false, true, 0, 0, filters);
        return CommonUtils.getDdlbCountThroughList(componentInformationList);
    }

    public List getComponentInformation(String componentSelectionValue, String[] id, int start, int end, Set<Container.Filter> filters) {
        List<Object[]> componentInformationList = getComponentInformationData(componentSelectionValue, id, true, false, start, end, filters);
        List<ComponentInformationDTO> componentInfoList = new ArrayList<ComponentInformationDTO>();
        Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
        if (componentInformationList != null && !componentInformationList.isEmpty()) {

            ComponentInformationDTO dto;
            if (componentInformationList != null && !componentInformationList.isEmpty()) {
                for (Object[] componentInformationList1 : componentInformationList) {
                    try {
                        final Object[] obj = (Object[]) componentInformationList1;
                        dto = new ComponentInformationDTO();

                        if (COMPANY_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
                            dto.setCompanyNo(convertNullToEmpty(obj[0]));
                            dto.setCompanyName(convertNullToEmpty(obj[1]));
                            dto.setCompanyStatus(convertNullToEmpty(obj[2]));
                            dto.setStartDate((Date) obj[3]);
                            dto.setEndDate((Date) obj[4]);
                            dto.setStatus(convertNullToEmpty(obj[5]));
                            dto.setTradeClass(convertNullToEmpty(obj[6]));
                            dto.setAttachedDate((Date) obj[7]);

                        } else if (ITEM_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
                            dto.setItemNo(convertNullToEmpty(obj[0]));
                            dto.setItemName(convertNullToEmpty(obj[1]));
                            dto.setBrand(convertNullToEmpty(obj[2]));
                            dto.setStatus(convertNullToEmpty(obj[3]));
                            dto.setStartDate((Date) obj[4]);
                            dto.setEndDate((Date) obj[5]);
                            dto.setAttachedDate((Date) obj[6]);

                        } else if (PRICE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
                            dto.setItemNo(convertNullToEmpty(obj[0]));
                            dto.setItemName(convertNullToEmpty(obj[1]));
                            dto.setBrand(convertNullToEmpty(obj[2]));
                            dto.setStatus(convertNullToEmpty(obj[3]));
                            dto.setStartDate((Date) obj[4]);
                            dto.setEndDate((Date) obj[5]);
                            dto.setAttachedDate((Date) obj[6]);
                            dto.setPriceType(convertNullToEmpty(obj[7]));
                            dto.setPricePlanNo(convertNullToEmpty(obj[8]));
                            dto.setPricePlanName(convertNullToEmpty(obj[9]));
                            dto.setPriceProtectionStatus(convertNullToEmpty(obj[10]));
                            dto.setPriceProtectionStartDate(formatDate(String.valueOf(obj[11])));
                            dto.setPriceProtectionEndDate(formatDate(String.valueOf(obj[12])));
                            dto.setPriceProtectionPriceType(convertNullToEmpty(obj[13]));
                            dto.setPriceToleranceInterval(convertNullToEmpty(obj[14]));
                            dto.setPriceToleranceFrequency(String.valueOf(obj[15]));
                            dto.setPriceToleranceType(convertNullToEmpty(obj[16]));
                            dto.setMaxIncrementalChange(convertNullToEmpty(obj[17]));
                            dto.setPriceTolerance(convertNullToEmpty(obj[18]));
                            dto.setResetEligible(convertNullToEmpty(obj[19]));
                            dto.setResetType(convertNullToEmpty(obj[20]));
                            dto.setResetDate(formatDate(String.valueOf(obj[21])));
                            dto.setResetInterval(convertNullToEmpty(obj[22]));
                            dto.setResetFrequency(convertNullToEmpty(obj[23]));

                        } else if (REBATE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
                            dto.setItemNo(convertNullToEmpty(obj[0]));
                            dto.setItemName(convertNullToEmpty(obj[1]));
                            dto.setBrand(convertNullToEmpty(obj[2]));
                            dto.setStatus(convertNullToEmpty(obj[3]));
                            dto.setStartDate((Date) obj[4]);
                            dto.setEndDate((Date) obj[5]);
                            dto.setFormulaType(convertNullToEmpty(obj[6]));
                            dto.setFormulaId(convertNullToEmpty(obj[7]));
                            dto.setFormulaName(convertNullToEmpty(obj[8]));
                            dto.setRebatePlanId(convertNullToEmpty(obj[9]));
                            dto.setRebatePlanName(convertNullToEmpty(obj[10]));
                            dto.setRebateAmount(convertNullToEmpty(obj[11]));
                            dto.setBundleNo(convertNullToEmpty(obj[12]));
                        }

                        componentInfoList.add(dto);
                    } catch (ParseException ex) {
                        LOGGER.error(ex);
                    }
                }
            }

        }
        return componentInfoList;
    }

    public void updateSubmitFlag(String moduleName, String screenName, String userId, String sessionId, boolean flag) {
        LOGGER.info(" Entering updateSubmitFlag");
        dao.updateSubmitFlag(moduleName, screenName, userId, sessionId, true, flag);
        LOGGER.info(" Exiting updateSubmitFlag");
    }

    public void updateSubmitFlagWithoutCheckRecord(String moduleName, String screenName, String userId, String sessionId, boolean flag) {
        LOGGER.info(" Entering updateSubmitFlagWithoutCheckRecord");
        dao.updateSubmitFlag(moduleName, screenName, userId, sessionId, false, flag);
        LOGGER.info(" Exiting updateSubmitFlagWithoutCheckRecord");
    }

    public void insertDataIntoTempTable(String userId, String sessionId, List<String> companyMasterSids, String screenName, boolean isInverse) {

        String CompanyMasterSid = CommonUtils.CollectionToString(companyMasterSids, true);
        List input = new ArrayList();
        input.add(CompanyMasterSid);
        input.add(companyMasterSids.size());
        input.add(userId);
        input.add(sessionId);
        input.add(screenName);
        ItemQueries.itemUpdate(input, isInverse ? "Company Contract Search" : "Company Contract Search Inverse");

    }

    public int getDataCount(List<String> companyMasterSid, boolean isInverse) {
        String CompanyMasterSids = CommonUtils.CollectionToString(companyMasterSid, true);
        String equalitySign = " in ";
        if (isInverse) {
            equalitySign = " not in ";
        }
        List input = new ArrayList();
        if (!isInverse) {
            input.add(" CFP_CON_DET.COMPANY_START_DATE AS 'COMPANY_START_DATE', ");
        } else {
            input.add(StringUtils.EMPTY);
        }
        input.add(equalitySign);
        input.add(CompanyMasterSids);
        input.add(equalitySign);
        input.add(CompanyMasterSids);
        input.add(CompanyMasterSids);
        input.add(companyMasterSid.size());
        List finalList = ItemQueries.getItemData(input, "Get Data Count", null);
        return CommonUtils.convertToInteger(String.valueOf(finalList.get(0)));
    }

    public List<String> getSubmitValidationData(String userId, String sessionId, String screenName, String validationType) {
        List<String> validationData = new ArrayList<String>();
        List<Object[]> list = getSubmitValidation(userId, sessionId, screenName, validationType);
        if (list != null && !list.isEmpty()) {

            if (validationType.equals("check")) {
                if (convertToInteger(String.valueOf(list.get(0))) != 0) {
                    validationData.add(String.valueOf(list.get(0)));
                } else {
                    validationData.add(Constants.ZEROSTRING);
                }
            }
            if (validationType.equals(Constants.START_DATE) || validationType.equals(Constants.END_DATE) || validationType.equals("status")) {
                for (Object ob : list) {

                    validationData.add(String.valueOf(ob));
                }
            }
        }
        return validationData;
    }

    public boolean isAnyRecordSelected(String userId, String sessionId, String screenName) {
        List<Object[]> checkList = dao.isAnyRecordSelected(userId, sessionId, screenName);

        if (checkList.size() > 0 && convertToInteger(String.valueOf(checkList.get(0))) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getSelectedCompaniesList(String searchSessionId, String updateType) {
        List<String> resultList = new ArrayList<String>();

        String query = " SELECT COMPANY_MASTER_SID FROM GCM_COMPANY_DETAILS where CHECK_RECORD = '1' AND SESSION_ID = '" + searchSessionId + "'";
        List list = (List) DAO.executeSelect(query);
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                resultList.add(String.valueOf(list.get(i)));
            }
        }

        return resultList;
    }

    public static boolean isStartDateGreaterThanEndDate(String userId, String sessionId, String date) {
        String query = "select count(*) from GCM_GLOBAL_DETAILS TEMP where  TEMP.USER_ID='" + userId + "' and TEMP.SESSION_ID='" + sessionId + "' and TEMP.CHECK_RECORD = '1' and TEMP.START_DATE > '" + date + "'";

        int count = CommonUtils.convertToInteger(String.valueOf(((List) DAO.executeSelect(query)).get(0)));
        return count > 0;
    }

    public static boolean isTCStartDateGreaterThanEndDate(String userId, String sessionId, String date, String Startdate) {
        String query = "select count(*) from GCM_GLOBAL_DETAILS TEMP where  TEMP.USER_ID='" + userId + "' and TEMP.SESSION_ID='" + sessionId + "' and TEMP.CHECK_RECORD = '1' and '" + Startdate + "' > '" + date + "'";
        int count = CommonUtils.convertToInteger(String.valueOf(((List) DAO.executeSelect(query)).get(0)));
        return count > 0;
    }

    public List getNonAssociatedProducts(String sessionId) {
        List input = new ArrayList();
        input.add(sessionId);
        input.add(sessionId);
        return ItemQueries.getItemData(input, "Get Non associated Products", null);
    }

    public boolean isSalesPresentAlready(int projectionid, String givenDate, List<String> companyMasterSids, int contractMasterSid, String forecastFlavour) {
        String tableName = StringUtils.EMPTY;
        if (forecastFlavour.equals(NON_MANDATED.getConstant())) {
            tableName = "NM_SALES_PROJECTION";
        } else if (forecastFlavour.equals(MANDATED.getConstant())) {
            tableName = "M_SALES_PROJECTION";
        }

        String query = "SELECT count(*) from " + tableName + " SP \n"
                + "JOIN PERIOD PER ON SP.PERIOD_SID = PER.PERIOD_SID AND PERIOD_DATE>='" + givenDate + "' \n"
                + "JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = SP.PROJECTION_DETAILS_SID and PD.PROJECTION_MASTER_SID = '" + projectionid + "'\n"
                + "JOIN CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID \n"
                + "WHERE CCP.COMPANY_MASTER_SID in (" + CommonUtils.CollectionToString(companyMasterSids, true) + ") and CCP.CONTRACT_MASTER_SID = '" + contractMasterSid + "'";

        int count = CommonUtils.convertToInteger(String.valueOf(((List) DAO.executeSelect(query)).get(0)));
        return count > 0;
    }

    public boolean isAnyDataSubmitted(String userId, String sessionId, String moduleName, String screenName) {
        String udc = StringUtils.EMPTY;
        if (ADD_TRADING_PARTNER.getConstant().equals(moduleName) || TAB_TRANSFER_CONTRACT.getConstant().equals(screenName)) {
            udc = "2";
        } else {
            udc = "1";
        }
        String query = "select count(*) from GCM_GLOBAL_DETAILS TEMP where  TEMP.USER_ID='" + userId + "' and TEMP.SESSION_ID='" + sessionId + "' and TEMP.OPERATION = '" + udc + "'";

        int count = CommonUtils.convertToInteger(String.valueOf(((List) DAO.executeSelect(query)).get(0)));
        return count > 0;
    }

    public boolean isHavingAnyCommonProducts(List<String> sourceCompanySids, List<String> destinationCompanySids, int sourceProjectionId, int destProjectionId, int sourceContractId, int destContractId) {
        LOGGER.info("Inside isHavingAnyCommonProducts");
        List<String> customersWithUncommonProducts = new ArrayList<String>(sourceCompanySids);
        List<String> newSourceCustomerSids = new ArrayList<String>(sourceCompanySids);
        List<String> newDestinationCompanySids = new ArrayList<String>(destinationCompanySids);

        String customerMappings = CommmonLogic.generateCustomerMappings(sourceCompanySids, destinationCompanySids);
        List<String> customersList = dao.getCustomersHavingCommonItems(sourceProjectionId, destProjectionId, sourceContractId, destContractId, customerMappings);

        for (String customer : customersList) {
            int index = 0;
            if (!Constants.NULL.equals(customer) && !StringUtils.EMPTY.equals(customer)) {
                index = sourceCompanySids.indexOf(customer);
                newSourceCustomerSids.remove(index);
                newDestinationCompanySids.remove(index);
                customersWithUncommonProducts.remove(customer);
            }
        }
        LOGGER.info("Exiting isHavingAnyCommonProducts");
        if (customersWithUncommonProducts.isEmpty()) {
            return true;
        } else {
            List<String> sourceCompanies = CommmonLogic.getCustomerName(newSourceCustomerSids);
            List<String> destinationCompanies = CommmonLogic.getCustomerName(newDestinationCompanySids);
            AbstractNotificationUtils.getAlertNotification("Items Mismatch", "The following company combinations has no items in common. \n Kindly select the companies which has atleast any one common item(s) between them."
                    + " \n " + CommmonLogic.generateCustomerMappings(sourceCompanies, destinationCompanies));
            return false;
        }
    }

    public List<Object[]> getComponentInformationData(String componentSelectionValue, String[] id, boolean isTableLoad, boolean isCount, int start, int offset, Set<Container.Filter> filters) {

        String queryName = null;
        List FinalList = new ArrayList();
        List input = new ArrayList();
        input.add(id[0]);
        input.add(id[1]);
        if (COMPANY_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
            queryName = "CFP Component Search";
        } else if (ITEM_FAMILY_PLAN.getConstant().equals(componentSelectionValue)) {
            input.add(id[2]);
            queryName = "IFP Component Search";
        }
        if (PRICE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
            input.add(id[2]);
            input.add(id[3]);
            queryName = "PS Component Search";
        }
        if (REBATE_SCHEDULE.getConstant().equals(componentSelectionValue)) {
            input.add(id[2]);
            input.add(id[3]);
            input.add(id[4]);
            queryName = "RS Component Search";
        }
        if (isTableLoad) {
            queryName = queryName + " For Table";
            input.add(start);
            input.add(offset);

        } else if (isCount) {
            queryName = queryName + " Count For Table";
        }
        if (filters != null) {
            FinalList.addAll(ItemQueries.getItemData(input, queryName, null, filters));
        } else {
            FinalList.addAll(ItemQueries.getItemData(input, queryName, null));
        }
        return FinalList;
    }

    public List getSubmitValidation(String userId, String sessionId, String screenName, String validationType) {

        List input = new ArrayList();
        input.add(userId);
        input.add(sessionId);
        input.add(screenName);

        String query = StringUtils.EMPTY;
        if ("check".equals(validationType)) {
            query = "Check Record Check";
        } else if (Constants.START_DATE.equals(validationType)) {
            query = "startDate Check";
        } else if (Constants.END_DATE.equals(validationType)) {
            query = "endDate Check";
        } else if ("status".equals(validationType)) {
            query = "status Check";
        }

        return ItemQueries.getItemData(input, query, null);
    }

    public void removeStartDateFromTempTable(String userId, String sessionId, String screenName) {
        List input = new ArrayList();
        input.add(userId);
        input.add(sessionId);
        input.add(screenName);
        ItemQueries.itemUpdate(input, "tp.RemoveStartDateFromTemp");
    }

}
