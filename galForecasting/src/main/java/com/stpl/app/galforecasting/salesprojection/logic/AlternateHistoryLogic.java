/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.salesprojection.logic;

import com.stpl.app.galforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import static com.stpl.app.galforecasting.nationalassumptions.logic.DataSelectionLogic.convertNullToEmpty;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import static com.stpl.app.galforecasting.utils.HeaderUtils.getMonthForInt;
import com.stpl.app.galforecasting.utils.TabNameUtil;
import com.stpl.app.galforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.util.Constants;
import static com.stpl.app.utils.Constants.LabelConstants;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import com.stpl.app.utils.QueryUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author nandhakumar
 */
public class AlternateHistoryLogic {

    QueryUtils queryUtils = new QueryUtils();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
    final DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
    String ACTUAL_SALES = "actualSales";
    String ACTUAL_UNITS = "actualUnits";
    String PROJECTED_SALES = "projectionSales";
    String PROJECTED_UNITS = "projectionUnits";
    String ACTUAL_PAYMENT = "actualPayments";
    String PROJECTED_PAYMENT = "projectionPayments";
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(AlternateHistoryLogic.class);

    public int companySearchCount(CustomFieldGroup customerSearchBinder, AlternateHistoryDTO altHistoryDTO, 
            Set<Container.Filter> filters,SessionDTO session) throws PortalException, SystemException {

//        LOGGER.info("--inside count--------->>>>");
        Map<String, Object> parameters = new HashMap<String, Object>();
        List resultList;
        LOGGER.info("--customer No-----" + altHistoryDTO.getCustomerNo());
        if (isValidCriteria(altHistoryDTO.getContractHolder())) {
            String contractHolder = altHistoryDTO.getContractHolder();
            contractHolder = contractHolder.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("contractHolder", contractHolder);
        }

        if (isValidCriteria(altHistoryDTO.getContractNo())) {
            String contractNo = altHistoryDTO.getContractNo();
            contractNo = contractNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("contractNo", contractNo);
        }

        if (isValidCriteria(altHistoryDTO.getCustomerNo())) {
            String customerNo = altHistoryDTO.getCustomerNo();
            customerNo = customerNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.CUSTOMER_NO, customerNo);
        }
        if (isValidCriteria(altHistoryDTO.getMarketType())) {
            String marketType = altHistoryDTO.getMarketType();
            marketType = marketType.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("marketType", marketType);
        }

        if (isValidCriteria(altHistoryDTO.getContractName())) {
            String contractName = altHistoryDTO.getContractName();
            contractName = contractName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("contractName", contractName);
        }

        if (isValidCriteria(altHistoryDTO.getCustomerName())) {
            String customerName = altHistoryDTO.getCustomerName();
            customerName = customerName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.CUSTOMER_NAME, customerName);
        }
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString =  stringFilter.getFilterString() + Constant.PERCENT;
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        int count = queryUtils.companyCount(parameters,session);
        return count;
    }

    

    public List<AlternateHistoryDTO> searchCompany(CustomFieldGroup customerSearchBinder, AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, int start, int offset, SessionDTO session) {

        Map<String, Object> parameters = new HashMap<String, Object>();
        List list=Collections.EMPTY_LIST;
        try{
        if (isValidCriteria(altHistoryDTO.getContractHolder())) {
            String contractHolder = altHistoryDTO.getContractHolder();
            contractHolder = contractHolder.replace(CommonUtils.CHAR_ASTERISK, '%');

            parameters.put("contractHolder", contractHolder);
        }

        if (isValidCriteria(altHistoryDTO.getContractNo())) {
            String contractNo = altHistoryDTO.getContractNo();
            contractNo = contractNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("contractNo", contractNo);
        }

        if (isValidCriteria(altHistoryDTO.getCustomerNo())) {
            String customerNo = altHistoryDTO.getCustomerNo();
            customerNo = customerNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.CUSTOMER_NO, customerNo);
        }
        if (isValidCriteria(altHistoryDTO.getMarketType())) {
            String marketType = altHistoryDTO.getMarketType();
            marketType = marketType.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("marketType", marketType);
        }

        if (isValidCriteria(altHistoryDTO.getContractName())) {
            String contractName = altHistoryDTO.getContractName();
            contractName = contractName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("contractName", contractName);
        }

        if (isValidCriteria(altHistoryDTO.getCustomerName())) {
            String customerName = altHistoryDTO.getCustomerName();
            customerName = customerName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.CUSTOMER_NAME, customerName);

        }

        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString =  stringFilter.getFilterString() + Constant.PERCENT;
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                }
            }
        }

        String query = queryUtils.companyValues(parameters, start, offset,  session);
//        if (isAddAll) {
//            HelperTableLocalServiceUtil.executeUpdateQuery(query);
//        } else {
            list = HelperTableLocalServiceUtil.executeSelectQuery(query);
//        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return getCustomizedDTO(list);
    }

    private boolean isValidCriteria(String value) {
        boolean isValid = false;
        if (value != null && !Constants.NULL.equals(String.valueOf(value)) && !StringUtils.EMPTY.equals(String.valueOf(value))
                && !StringUtils.isEmpty(String.valueOf(value)) && !StringUtils.isBlank(String.valueOf(value))) {
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    private List<AlternateHistoryDTO> getCustomizedDTO(List list) {

        List<AlternateHistoryDTO> companyList = new ArrayList<AlternateHistoryDTO>();
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                AlternateHistoryDTO dto = new AlternateHistoryDTO();
                Object[] obj = (Object[]) list.get(i);
                dto.setContractHolder(convertNullToEmpty(String.valueOf(obj[0])));
                dto.setContractNo(convertNullToEmpty(String.valueOf(obj[1])));
                dto.setContractName(convertNullToEmpty(String.valueOf(obj[2])));
                dto.setMarketType(convertNullToEmpty(String.valueOf(obj[3])));
                dto.setCustomerNo(convertNullToEmpty(String.valueOf(obj[4])));
                dto.setCustomerName(convertNullToEmpty(String.valueOf(obj[5])));
                dto.setContractMasterSid(Integer.valueOf(String.valueOf(obj[6])));
                dto.setCompanymasterSid(Integer.valueOf(String.valueOf(obj[7])));
                dto.setCheck(obj[8] == null ? false : (boolean) obj[8]);
                companyList.add(dto);
            }
        }

        return companyList;
    }

    

    private List<AlternateHistoryDTO> getCustomizedDTOFromTemp(List list) {

        List<AlternateHistoryDTO> companyList = new ArrayList<>();
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                AlternateHistoryDTO dto = new AlternateHistoryDTO();
                Object[] obj = (Object[]) list.get(i);
                dto.setContractName(convertNullToEmpty(String.valueOf(obj[0])));
                dto.setContractNo(convertNullToEmpty(String.valueOf(obj[1])));
                dto.setMarketType(convertNullToEmpty(String.valueOf(obj[2])));
                dto.setCustomerName(convertNullToEmpty(String.valueOf(obj[3])));
                dto.setCustomerNo(convertNullToEmpty(String.valueOf(obj[4])));
                dto.setCompanymasterSid(Integer.valueOf(convertNullToEmpty(String.valueOf(obj[5]))));
                dto.setContractHolder(convertNullToEmpty(String.valueOf(obj[6])));
                dto.setContractMasterSid(obj[7] != null ? Integer.valueOf(String.valueOf(obj[7])) : 0);
                dto.setTemptableSid(obj[8] != null ? String.valueOf(obj[8]) : StringUtils.EMPTY);
//                alternateHistoryDTO.getSelectedCustomerSet().add(dto.getCompanymasterSid() + StringUtils.EMPTY + dto.getContractMasterSid());
                companyList.add(dto);
            }
        }
        return companyList;
    }

//    public void deleteTempTableRecordsWithScreenName(SessionDTO session, String screenName, Boolean isRemove) {
//        String query = StringUtils.EMPTY;
//        if (isRemove) {
//            query = "DELETE FROM AH_TEMP_DETAILS WHERE AH_TEMP_DETAILS_SID IN ("+session.getSelCustomer()+")";
//        } else {
//            query = "DELETE FROM AH_TEMP_DETAILS WHERE SCREEN_NAME = '" + screenName + "' AND USER_ID = '" + session.getUserId() + "' AND SESSION_ID ='" + session.getSessionId() + "'";
//        }
//        HelperTableLocalServiceUtil.executeUpdateQuery(query);
//    }

    public int itemsSearchCount(CustomFieldGroup customerSearchBinder, AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, SessionDTO session) throws PortalException, SystemException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (isValidCriteria(altHistoryDTO.getItemNo())) {
            String itemNo = altHistoryDTO.getItemNo();
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.ITEM_NO, itemNo);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getItemName())) {
            String itemName = altHistoryDTO.getItemName();
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("itemName", itemName);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getBusinessUnitNo())) {
            String businessUnitNo = altHistoryDTO.getBusinessUnitNo();
            businessUnitNo = businessUnitNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("businessUnitNo", businessUnitNo);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getBusinessUnitName())) {
            String businessUnitName = altHistoryDTO.getBusinessUnitName();
            businessUnitName = businessUnitName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("businessUnitName", businessUnitName);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getTheraputicClass())) {
            String theraputicClass = altHistoryDTO.getTheraputicClass();
            theraputicClass = theraputicClass.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("theraputicClass", theraputicClass);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getBrand())) {
            String brand = altHistoryDTO.getBrand();
            parameters.put(Constant.BRAND, brand);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifierType())) {
            String itemIdentifierType = altHistoryDTO.getItemIdentifierType();
            parameters.put("itemIdentifierType", itemIdentifierType);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifier())) {
            String itemIdentifier = altHistoryDTO.getItemIdentifier();
            itemIdentifier = itemIdentifier.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("itemIdentifier", itemIdentifier);
        }

        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                }
            }
        }

        int count = queryUtils.itemCount(parameters, session);
        return count;

    }

    public List<AlternateHistoryDTO> searchItems(CustomFieldGroup customerSearchBinder, AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, int start, int offset, SessionDTO session, boolean isAddAll) {

        Map<String, Object> parameters = new HashMap<String, Object>();

        boolean isIdentifierSelected = false;
        if (isValidCriteria(altHistoryDTO.getItemNo())) {
            String itemNo = altHistoryDTO.getItemNo();
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.ITEM_NO, itemNo);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getItemName())) {
            String itemName = altHistoryDTO.getItemName();
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("itemName", itemName);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getBusinessUnitNo())) {
            String businessUnitNo = altHistoryDTO.getBusinessUnitNo();
            businessUnitNo = businessUnitNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("businessUnitNo", businessUnitNo);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getBusinessUnitName())) {
            String businessUnitName = altHistoryDTO.getBusinessUnitName();
            businessUnitName = businessUnitName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("businessUnitName", businessUnitName);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getTheraputicClass())) {
            String theraputicClass = altHistoryDTO.getTheraputicClass();
            theraputicClass = theraputicClass.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("theraputicClass", theraputicClass);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getBrand())) {
            String brand = altHistoryDTO.getBrand();
            parameters.put(Constant.BRAND, brand);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifierType())) {
            String itemIdentifierType = altHistoryDTO.getItemIdentifierType();
            parameters.put("itemIdentifierType", itemIdentifierType);
            isIdentifierSelected = true;
        }
        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifier())) {
            String itemIdentifier = altHistoryDTO.getItemIdentifier();
            itemIdentifier = itemIdentifier.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("itemIdentifier", itemIdentifier);
        }

        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                }
            }
        }

//        List itemsList = queryUtils.itemValues(parameters, start, offset, session, isAddAll);
        List itemsList = queryUtils.itemsSearch(parameters, start, offset, session);
        return getCustomizedItemsDTO(itemsList, isIdentifierSelected);
    }

    private List<AlternateHistoryDTO> getCustomizedItemsDTO(List list,final boolean isIdentifierSelected) {

        List<AlternateHistoryDTO> itemsList = new ArrayList<>();
        try {
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    AlternateHistoryDTO dto = new AlternateHistoryDTO();
                    Object[] obj = (Object[]) list.get(i);
                    dto.setBusinessUnitNo(convertNullToEmpty(String.valueOf(obj[0])));
                    dto.setBusinessUnitName(convertNullToEmpty(String.valueOf(obj[1])));
                    dto.setTheraputicClass(convertNullToEmpty(String.valueOf(obj[2])));
                    dto.setBrand(convertNullToEmpty(String.valueOf(obj[3])));
                    dto.setItemNo(convertNullToEmpty(String.valueOf(obj[4])));
                    dto.setItemName(convertNullToEmpty(String.valueOf(obj[5])));
                    if (obj[6] != null) {
                        dto.setItemMasterSid(Integer.valueOf(String.valueOf(obj[6])));
                    }
                    if (isIdentifierSelected) {
                        dto.setItemIdentifierType(String.valueOf(obj[8]));
                        dto.setItemIdentifier(String.valueOf(obj[7]));
                        dto.setCheck(obj[9] == null ? false : (Boolean) obj[9]);
                    } else {
                        dto.setCheck(obj[7] == null ? false : (Boolean) obj[7]);
                    }
                    itemsList.add(dto);
                }
                list.clear();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return itemsList;
    }
    public void updateItemsToTemptable(boolean checkValue, AlternateHistoryDTO dto, String screenName, SessionDTO session,boolean availableValue) {
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        String createdDate = simpleDateFormat.format(new Date());

        if (checkValue && availableValue) {
            String query = "INSERT INTO DBO.AH_TEMP_DETAILS (BUSINESS_UNIT_NO,BUSINESS_UNIT_NAME,THERAPUTIC_CLASS,BRAND_NAME,ITEM_NO,ITEM_NAME,ITEM_IDENTIFIER_TYPE,\n"
                    + "ITEM_IDENTIFIER,ITEM_MASTER_SID,CHECK_RECORD,SCREEN_NAME,USER_ID,SESSION_ID,CREATED_BY,CREATED_DATE,COMPANY_SID,COMPONENT_MASTER_SID,COMPONENT_NO)\n"
                    + "VALUES ('?BUSINESS_UNIT_NO','?BUSINESS_UNIT_NAME','?THERAPUTIC_CLASS','?BRAND_NAME','?ITEM_NO','?ITEM_NAME','?ITEM_IDENTIFIER_TYPE',\n"
                    + "'?ITEM_IDENTIFIER','?ITEM_MASTER_SID','?CHECK_RECORD','?SCREEN_NAME','?USER_ID','?SESSION_ID','?CREATED_BY','?CREATED_DATE','?COMPANY_SID','?COMPONENT_MASTER_SID','?COMPONENT_NO')";

            if (!dto.getBusinessUnitNo().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?BUSINESS_UNIT_NO", String.valueOf(dto.getBusinessUnitNo()));
            } else {
                query = query.replaceAll("\\?BUSINESS_UNIT_NO", StringUtils.EMPTY);
            }
            if (!dto.getBusinessUnitName().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?BUSINESS_UNIT_NAME", String.valueOf(dto.getBusinessUnitName()));
            } else {
                query = query.replaceAll("\\?BUSINESS_UNIT_NAME", StringUtils.EMPTY);
            }
            if (!dto.getTheraputicClass().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?THERAPUTIC_CLASS", String.valueOf(dto.getTheraputicClass()));
            } else {
                query = query.replaceAll("\\?THERAPUTIC_CLASS", StringUtils.EMPTY);
            }
            if (!dto.getBrand().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?BRAND_NAME", String.valueOf(dto.getBrand()));
            } else {
                query = query.replaceAll("\\?BRAND_NAME", StringUtils.EMPTY);
            }
            if (!dto.getItemNo().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?ITEM_NO", String.valueOf(dto.getItemNo()).contains("'") ? String.valueOf(dto.getItemNo()).replace("'", "''") : String.valueOf(dto.getItemNo()));
            } else {
                query = query.replaceAll("\\?ITEM_NO", StringUtils.EMPTY);
            }
            if (!dto.getItemName().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?ITEM_NAME", String.valueOf(dto.getItemName()).contains("'") ? String.valueOf(dto.getItemName()).replace("'", "''") : String.valueOf(dto.getItemName()));
            } else {
                query = query.replaceAll("\\?ITEM_NAME", StringUtils.EMPTY);
            }
            if (!dto.getItemIdentifierType().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?ITEM_IDENTIFIER_TYPE", String.valueOf(dto.getItemIdentifierType()));
            } else {
                query = query.replaceAll("\\?ITEM_IDENTIFIER_TYPE", StringUtils.EMPTY);
            }
            if (!dto.getItemIdentifier().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?ITEM_IDENTIFIER", String.valueOf(dto.getItemIdentifier()));
            } else {
                query = query.replaceAll("\\?ITEM_IDENTIFIER", StringUtils.EMPTY);
            }
            if (dto.getItemMasterSid() != 0) {
                query = query.replaceAll("\\?ITEM_MASTER_SID", String.valueOf(dto.getItemMasterSid()));
            }
            if (dto.getContractMasterSid() != 0) {
                query = query.replaceAll("\\?COMPONENT_MASTER_SID", String.valueOf(dto.getContractMasterSid()));
            }
            if (dto.getCompanymasterSid() != 0) {
                query = query.replaceAll("\\?COMPANY_SID", String.valueOf(dto.getCompanymasterSid()));
            }

            query = query.replaceAll("\\?COMPONENT_NO", String.valueOf(dto.getCcpDetailsId()));
            if (dto.getCheck()) {
                query = query.replaceAll("\\?CHECK_RECORD", Constant.STRING_ONE);
            } else {
                query = query.replaceAll("\\?CHECK_RECORD", DASH);
            }
            query = query.replaceAll("\\?SCREEN_NAME", screenName);
            query = query.replaceAll("\\?USER_ID", userId);
            query = query.replaceAll("\\?SESSION_ID", session.getSessionId());
            query = query.replaceAll("\\?CREATED_BY", userId);
            query = query.replaceAll("\\?CREATED_DATE", createdDate);

            HelperTableLocalServiceUtil.executeUpdateQuery(query);
        } else {
            String query = "UPDATE AH_TEMP_DETAILS SET CHECK_RECORD = '0' "
                    + "WHERE BUSINESS_UNIT_NO = '" + String.valueOf(dto.getBusinessUnitNo()) + "' AND BUSINESS_UNIT_NAME ='" + String.valueOf(dto.getBusinessUnitName()) + "'"
                    + " AND THERAPUTIC_CLASS='" + String.valueOf(dto.getTheraputicClass()) + "'"
                    + " AND BRAND_NAME='" + String.valueOf(dto.getBrand()) + "'"
                    + " AND ITEM_NO='" + String.valueOf(dto.getItemNo()) + "'"
                    + " AND ITEM_NAME='" + String.valueOf(dto.getItemName()) + "'"
                    + " AND ITEM_IDENTIFIER_TYPE='" + String.valueOf(dto.getItemIdentifierType()) + "' "
                    + " AND ITEM_IDENTIFIER='" + String.valueOf(dto.getItemIdentifier()) + "' "
                    + " AND ITEM_MASTER_SID='" + String.valueOf(dto.getItemMasterSid()) + "' "
                    + " AND COMPANY_SID='" + String.valueOf(dto.getCompanymasterSid()) + "' "
                    + " AND COMPONENT_MASTER_SID='" + String.valueOf(dto.getContractMasterSid()) + "' "
                    + " AND COMPONENT_NO='" + String.valueOf(dto.getCcpDetailsId()) + "' "
                    + "AND SCREEN_NAME = '" + screenName + "' AND USER_ID = '" + session.getUserId() + "' AND SESSION_ID = '" + session.getSessionId() + "';";
            HelperTableLocalServiceUtil.executeUpdateQuery(query);
        }

    }
    
    public int getCheckedItemsCount(CustomFieldGroup customerSearchBinder, AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, String sessionId) {
        List list;

        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                }
            }

        }

        String query = StringUtils.EMPTY;
        
        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifierType()) && !Constant.NULL.equals(altHistoryDTO.getItemIdentifier())) {
            query += SQlUtil.getQuery("selected-items-count-with-identifier");
        } else {
            query += SQlUtil.getQuery("selected-items-count");
        }
        
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        query = query.replace("[$USER_ID]", userId);
        query = query.replace("[$SESSION_ID]", sessionId);
        int count = 0;

        //For Filters
        if (parameters.containsKey("filter~businessUnitName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~businessUnitName")))) {
            query = query + " AND CM.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~businessUnitName")) + "'";
        }

        if (parameters.containsKey("filter~itemIdentifierType") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemIdentifierType")))) {
            query = query + " AND IQ.ITEM_QUALIFIER_NAME like '" + String.valueOf(parameters.get("filter~itemIdentifierType")) + "'";
        }

        if (parameters.containsKey("filter~businessUnitNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~businessUnitNo")))) {
            query = query + " AND CM.COMPANY_NO like '" + String.valueOf(parameters.get("filter~businessUnitNo")) + "'";
        }

        if (parameters.containsKey("filter~itemName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemName")))) {
            query = query + " AND IM.ITEM_NAME like '" + String.valueOf(parameters.get("filter~itemName")) + "'";
        }

        if (parameters.containsKey("filter~theraputicClass") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~theraputicClass")))) {
            query = query + " AND HT1.DESCRIPTION like '" + String.valueOf(parameters.get("filter~theraputicClass")) + "'";
        }

        if (parameters.containsKey("filter~itemNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemNo")))) {
            query = query + " AND IM.ITEM_NO like '" + String.valueOf(parameters.get("filter~itemNo")) + "'";
        }

        if (parameters.containsKey("filter~brand") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~brand")))) {
            query = query + " AND BM.BRAND_NAME like '" + String.valueOf(parameters.get("filter~brand")) + "'";
        }

        if (parameters.containsKey("filter~itemIdentifier") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemIdentifier")))) {
            query = query + " AND II.ITEM_IDENTIFIER_VALUE like '" + String.valueOf(parameters.get("filter~itemIdentifier")) + "'";
        }
        query = query + ") TMP_COUNT;";

        list = HelperTableLocalServiceUtil.executeSelectQuery(query);

        if (list != null && list.size() > 0) {
            Object obj = list.get(0);
            String countValue = String.valueOf(obj);
            count = Integer.valueOf(countValue);

        }
        return count;
    }

    public List<AlternateHistoryDTO> getCheckedItemsFromTemptable(CustomFieldGroup customerSearchBinder, AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, int start, int offset, String sessionId) {

        List list;
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);

                }
            }

        }
        boolean isIdentifierSelected;
        String query;
        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifierType()) && !Constant.NULL.equals(altHistoryDTO.getItemIdentifier())) {
            query = SQlUtil.getQuery("selected-items-search-with-identifier");
            isIdentifierSelected = true;
        } else {
            query = SQlUtil.getQuery("selected-items-search");
            isIdentifierSelected = false;
        }
        
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        query = query.replace("[$USER_ID]", userId);
        query = query.replace("[$SESSION_ID]", sessionId);

        if (parameters.containsKey("filter~businessUnitName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~businessUnitName")))) {
            query = query + " AND CM.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~businessUnitName")) + "'";
        }

        if (parameters.containsKey("filter~itemIdentifierType") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemIdentifierType")))) {
            query = query + " AND IQ.ITEM_QUALIFIER_NAME like '" + String.valueOf(parameters.get("filter~itemIdentifierType")) + "'";
        }

        if (parameters.containsKey("filter~businessUnitNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~businessUnitNo")))) {
            query = query + " AND CM.COMPANY_NO like '" + String.valueOf(parameters.get("filter~businessUnitNo")) + "'";
        }

        if (parameters.containsKey("filter~itemName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemName")))) {
            query = query + " AND IM.ITEM_NAME like '" + String.valueOf(parameters.get("filter~itemName")) + "'";
        }

        if (parameters.containsKey("filter~theraputicClass") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~theraputicClass")))) {
            query = query + " AND HT1.DESCRIPTION like '" + String.valueOf(parameters.get("filter~theraputicClass")) + "'";
        }

        if (parameters.containsKey("filter~itemNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemNo")))) {
            query = query + " AND IM.ITEM_NO like '" + String.valueOf(parameters.get("filter~itemNo")) + "'";
        }

        if (parameters.containsKey("filter~brand") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~brand")))) {
            query = query + " AND BM.BRAND_NAME like '" + String.valueOf(parameters.get("filter~brand")) + "'";
        }

        if (parameters.containsKey("filter~itemIdentifier") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemIdentifier")))) {
            query = query + " AND II.ITEM_IDENTIFIER_VALUE like '" + String.valueOf(parameters.get("filter~itemIdentifier")) + "'";
        }
        query += " ORDER BY ITEM_NAME OFFSET " + start + "  ROWS FETCH NEXT " + offset + " ROWS ONLY";

        list = HelperTableLocalServiceUtil.executeSelectQuery(query);

        //   List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return getCustomizedItemsDTOFromTemp(list,isIdentifierSelected);

    }

    private List<AlternateHistoryDTO> getCustomizedItemsDTOFromTemp(List list, final boolean isIdentifierSelected) {
        List<AlternateHistoryDTO> selectedItemsList = new ArrayList<>();
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                AlternateHistoryDTO dto = new AlternateHistoryDTO();
                Object[] obj = (Object[]) list.get(i);
                dto.setBusinessUnitNo(convertNullToEmpty(String.valueOf(obj[0])));
                dto.setBusinessUnitName(convertNullToEmpty(String.valueOf(obj[1])));
                dto.setTheraputicClass(convertNullToEmpty(String.valueOf(obj[2])));
                dto.setBrand(convertNullToEmpty(String.valueOf(obj[3])));
                dto.setItemNo(convertNullToEmpty(String.valueOf(obj[4])));
                dto.setItemName(convertNullToEmpty(String.valueOf(obj[5])));
                if (obj[6] != null) {
                    dto.setItemMasterSid(Integer.valueOf(String.valueOf(obj[6])));
                }
                if (isIdentifierSelected) {
                    dto.setItemIdentifierType(String.valueOf(obj[8]));
                    dto.setItemIdentifier(String.valueOf(obj[7]));
                    dto.setCheck(obj[9] == null ? false : (Boolean) obj[9]);
                }else{
                    dto.setCheck(obj[7] == null ? false : (Boolean) obj[7]);
                }
                selectedItemsList.add(dto);
            }
            list.clear();
        }
        return selectedItemsList;
    }

    public List<AlternateHistoryDTO> alternateSelectionList(SessionDTO session, AlternateHistoryDTO dto, Set<Container.Filter> filters,final int start,final int offset,final boolean isExcelExport,final Set checkedCCPSet) {
        List<AlternateHistoryDTO> actualsList = new ArrayList<AlternateHistoryDTO>();
        try {
        AlternateHistoryDTO dto2 = new AlternateHistoryDTO();
        dto2.setContractName("test Contract");
        dto2.setItemName("test item");
        dto2.setParent(0);
        AlternateHistoryDTO dto1 = new AlternateHistoryDTO();
        dto1.setContractName("test Contract");
        dto1.setItemName("test item");
        dto1.setParent(0);
        String freq = dto.getFrequency();

        String projection = dto.getActualsOrProjections();
        AlternateHistoryDTO altDTO = null;
        String commonColumn = StringUtils.EMPTY;
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        String companyFilter = StringUtils.EMPTY;
        String itemFilter = StringUtils.EMPTY;
        String contractFilter = StringUtils.EMPTY;
        
         if (parameters.containsKey("filter~contractNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractNo")))) {
            contractFilter = contractFilter + " AND CON.CONTRACT_NO like '" + String.valueOf(parameters.get("filter~contractNo")) + "'";
        }

        if (parameters.containsKey("filter~contractName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractName")))) {
            contractFilter = contractFilter + " AND CON.CONTRACT_NAME like '" + String.valueOf(parameters.get("filter~contractName")) + "'";
        }

        if (parameters.containsKey("filter~customerNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerNo")))) {
            companyFilter = companyFilter + " AND CM.COMPANY_NO like '" + String.valueOf(parameters.get("filter~customerNo")) + "'";
        }

        if (parameters.containsKey("filter~customerName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerName")))) {
            companyFilter = companyFilter + " AND CM.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~customerName")) + "'";
        }

        if (parameters.containsKey("filter~itemNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemNo")))) {
            itemFilter = itemFilter + " AND IM.ITEM_NO like '" + String.valueOf(parameters.get("filter~itemNo")) + "'";
        }

        if (parameters.containsKey("filter~itemName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemName")))) {
            itemFilter = itemFilter + " AND IM.ITEM_NAME like '" + String.valueOf(parameters.get("filter~itemName")) + "'";
        }
        
        String frequency;
        String filterFrequency = StringUtils.EMPTY;
        String innerFrequency = StringUtils.EMPTY;
        
         
        if (freq.equals(Constant.QUARTERLY)) {
            frequency = " U.QUARTER\n";
            filterFrequency = " ,QUARTER\n";
            innerFrequency=" QUARTER\n";
        } else if (freq.equals(Constant.SEMI_ANNUALLY)) {
            frequency = "  CASE \n"
                    + "  WHEN u.QUARTER <= 2\n"
                    + "   THEN 1\n"
                    + "    ELSE 2\n"
                    + "  END SEMI_ANNUAL\n";
            filterFrequency = " , CASE \n"
                    + "   WHEN QUARTER <= 2\n"
                    + "      THEN 1\n"
                    + "    ELSE 2\n"
                    + "   END\n";
            innerFrequency=" QUARTER\n";
        } else if (freq.equals(Constant.MONTHLY)) {
            frequency = " U.MONTH\n";
            filterFrequency = " ,MONTH\n";
            innerFrequency=" MONTH\n";
        } else {
            frequency = " 1 AS FREQUENCY";
            innerFrequency=" 1 AS FREQUENCY\n";
        }
        
        
            String sql = SQlUtil.getQuery("alternate-history-selection");

            if (isExcelExport) {
                sql = sql.replace("ORDER BY CCP.CCP_DETAILS_SID OFFSET @?START_INDEX ROWS FETCH NEXT @?END_INDEX ROWS ONLY", StringUtils.EMPTY);
            }
            sql = sql.replace("@?START_DATE DATETIME", dto.getStartDate());
            sql = sql.replace("@?END_DATE DATETIME", dto.getEndDate());
            sql = sql.replace("@?SCREEN_NAME", session.getForecastName());
            sql = sql.replace("@?USER_ID", session.getUserId());
            sql = sql.replace("@?SESSION_ID", session.getSessionId());
            sql = sql.replace("@COMPANY_FILTER", companyFilter);
            sql = sql.replace("@ITEM_FILTER", itemFilter);
            sql = sql.replace("@CONTRACT_FILTER", contractFilter);
            sql = sql.replace("@?FREQUENCY", frequency);
            sql = sql.replace("@?FILTER-FREQUENCY", filterFrequency);
            sql = sql.replace("@?INNER-FREQUENCY", innerFrequency);
            sql = sql.replace("@?START_INDEX", StringUtils.EMPTY + start);
            sql = sql.replace("@?END_INDEX", StringUtils.EMPTY + offset);            
            List list = HelperTableLocalServiceUtil.executeSelectQuery(sql);

        if (list != null && list.size() > 0) {
            int count = list.size();
            String currentccpId = StringUtils.EMPTY;
            for (int i = 0; i < count; i++) {
                Object[] obj = (Object[]) list.get(i);
                String ccpId = String.valueOf(obj[8]);
                if (ccpId.equals(currentccpId)) {
                    if ("Sales Projection".equals(session.getForecastName())) {
                        if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                            commonColumn = getCommonColumn(freq, obj[2], obj[3]) + ACTUAL_SALES;
                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[0]));
                       commonColumn = getCommonColumn(freq, obj[2], obj[3]) + ACTUAL_UNITS;
                            if(freq.equals("Annually")){
                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[10]));
                            }else{
                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[11]));
                            }
                        }
                        if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                            commonColumn = getCommonColumn(freq, obj[2], obj[3]) + PROJECTED_SALES;

                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[1]));
                              commonColumn = getCommonColumn(freq, obj[2], obj[3]) + PROJECTED_UNITS;
                            if(freq.equals("Annually")){
                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[11]));
                            }else{
//                            commonColumn = getCommonColumn(freq, obj[2], obj[3]) + PROJECTED_UNITS;
                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[12]));
                            }
                        }
                    } else if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {
                        if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                            commonColumn = getCommonColumn(freq, obj[2], obj[3]) + ACTUAL_PAYMENT;

                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[0]));
                        }
                        if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                            commonColumn = getCommonColumn(freq, obj[2], obj[3]) + PROJECTED_PAYMENT;

                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[1]));
                        }
                    }

                } else {
                    if (i != 0) {
                        actualsList.add(altDTO);
                    }

                    currentccpId = ccpId;
                    altDTO = new AlternateHistoryDTO();
                    if(freq.equals("Annually")){
                     altDTO = new AlternateHistoryDTO();
                    altDTO.setContractName(String.valueOf(obj[6]));
                    altDTO.setContractNo(String.valueOf(obj[5]));
                    altDTO.setCustomerName(String.valueOf(obj[4]));
                    altDTO.setCustomerNo(String.valueOf(obj[3]));
                    altDTO.setItemNo(String.valueOf(obj[8]));
                    altDTO.setItemName(String.valueOf(obj[9]));
                    altDTO.setCcpDetailsId(String.valueOf(obj[7]));
                    }else{
                    altDTO.setContractName(String.valueOf(obj[7]));
                    altDTO.setContractNo(String.valueOf(obj[6]));
                    altDTO.setCustomerName(String.valueOf(obj[5]));
                    altDTO.setCustomerNo(String.valueOf(obj[4]));
                    altDTO.setItemNo(String.valueOf(obj[9]));
                    altDTO.setItemName(String.valueOf(obj[10]));
                    altDTO.setCcpDetailsId(String.valueOf(obj[8]));
                    }
                    

                    altDTO.setCheck(checkedCCPSet.contains(altDTO.getCcpDetailsId()));             
                    
                    if ("Sales Projection".equals(session.getForecastName())) {
                        if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                            commonColumn = getCommonColumn(freq, obj[2], obj[3]) + ACTUAL_SALES;

                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[0]));
                            commonColumn = getCommonColumn(freq, obj[2], obj[3]) + ACTUAL_UNITS;

                            if(freq.equals("Annually")){
                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[10]));
                            }else{
                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[11]));
                            }
                        }
                        if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                            commonColumn = getCommonColumn(freq, obj[2], obj[3]) + PROJECTED_SALES;

                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[1]));
                              commonColumn = getCommonColumn(freq, obj[2], obj[3]) + PROJECTED_UNITS;
                            if(freq.equals("Annually")){
                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[11]));
                            }else{
//                            commonColumn = getCommonColumn(freq, obj[2], obj[3]) + PROJECTED_UNITS;
                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[12]));
                            }
                        }
                    } else if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {
                        if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                            commonColumn = getCommonColumn(freq, obj[2], obj[3]) + ACTUAL_PAYMENT;

                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[0]));
                        }
                        if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                            commonColumn = getCommonColumn(freq, obj[2], obj[3]) + PROJECTED_PAYMENT;

                            altDTO.addStringProperties(commonColumn, String.valueOf(obj[1]));
                        }
                    }
                }

                if (i == count - 1) {
                    actualsList.add(altDTO);
                }

            }
        }
        
        } catch (Exception e) {
           LOGGER.error(e.getMessage());
        }
        return actualsList;

    }

    /**
     *
     * @param freq
     * @param obj1
     * @param obj2
     * @return
     */
    private String getCommonColumn(String freq, Object obj1, Object obj2) {
        String column = StringUtils.EMPTY;
        if (freq.equals(Constants.QUARTERLY)) {
            column = Constant.Q_SMALL + obj1 + obj2;
        } else if (freq.equals(Constant.SEMI_ANNUALLY)) {
            column = Constant.S_SMALL + obj1 + obj2;
        } else if (freq.equals(Constant.ANNUALLY)) {
            column = StringUtils.EMPTY + obj1;
        } else if (freq.equals(Constant.MONTHLY)) {            
            column = StringUtils.lowerCase(getMonthForInt(Integer.valueOf(String.valueOf(obj1))-1) + obj2);            
        }
        return column;
    }

    public List<AlternateHistoryDTO> getAlloc(AlternateHistoryDTO altHistoryDTO, SessionDTO session, boolean addToQueue, Set<Container.Filter> filters, int start, int offset, boolean iscount) {
        List<AlternateHistoryDTO> allocationList = new ArrayList<AlternateHistoryDTO>();
        List<AlternateHistoryDTO> totalList = new ArrayList<AlternateHistoryDTO>();
        AlternateHistoryDTO altDTO = new AlternateHistoryDTO();
        AlternateHistoryDTO totDTO = new AlternateHistoryDTO();
        String commonColumn = StringUtils.EMPTY;
        String commonPerColumn = StringUtils.EMPTY;
        String projection = Constant.BOTH;
        String annualFreq = StringUtils.EMPTY;
        String freqQuery = StringUtils.EMPTY;
        String check = StringUtils.EMPTY;
        String[] fromDate = session.getStartDate().split("-");
        String[] toDate = session.getEndDate().split("-");
        String fromYear = fromDate[0];
        String fromMonth = fromDate[1];
        String toYear = toDate[0];
        String toMonth = toDate[1];
        if (addToQueue) {
            check = "ST.CHECK_RECORD = '1' AND\n";
        }
        List list;
        List totalLevelList;
        String query = StringUtils.EMPTY;
        switch (altHistoryDTO.getFrequency()) {
            case Constants.QUARTERLY:
                freqQuery = "I.\"YEAR\",I.QUARTER";
                break;
            case Constant.SEMI_ANNUALLY:
                freqQuery = "I.\"YEAR\",I.SEMI_ANNUAL";
                break;
            case Constants.MONTHLY:
                freqQuery = "I.\"YEAR\",I.MONTH";
                break;
            case Constants.ANNUALLY:
                freqQuery = " I.\"YEAR\",I.\"YEAR\" ";
                annualFreq= " I.\"YEAR\" as period1,I.\"YEAR\" as period2 ";
                break;

        }
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        String companyFilter = StringUtils.EMPTY;
        String itemFilter = StringUtils.EMPTY;
        String contractFilter = StringUtils.EMPTY;

        if (parameters.containsKey("filter~contractNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractNo")))) {
            contractFilter = contractFilter + " AND CN.CONTRACT_NO like '" + String.valueOf(parameters.get("filter~contractNo")) + "'";
        }

        if (parameters.containsKey("filter~contractName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractName")))) {
            contractFilter = contractFilter + " AND CN.CONTRACT_NAME like '" + String.valueOf(parameters.get("filter~contractName")) + "'";
        }

        if (parameters.containsKey("filter~customerNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerNo")))) {
            companyFilter = companyFilter + " AND CM.COMPANY_NO like '" + String.valueOf(parameters.get("filter~customerNo")) + "'";
        }

        if (parameters.containsKey("filter~customerName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerName")))) {
            companyFilter = companyFilter + " AND CM.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~customerName")) + "'";
        }

        if (parameters.containsKey("filter~itemNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemNo")))) {
            itemFilter = itemFilter + " AND IM.ITEM_NO like '" + String.valueOf(parameters.get("filter~itemNo")) + "'";
        }

        if (parameters.containsKey("filter~itemName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemName")))) {
            itemFilter = itemFilter + " AND IM.ITEM_NAME like '" + String.valueOf(parameters.get("filter~itemName")) + "'";
        }

//        String qu
        String totalQuery = "select " + freqQuery + ",ISNULL(SUM(TOTAL_ACTUAL_AMOUNT),0) as ACTUAL_AMOUNT,ISNULL(SUM(TOTAL_PROJECTION_AMOUNT),0) as PROJECTION_AMOUNT\n";
        if (session.getForecastName().equals("Sales Projection")) {
            totalQuery = "select " + freqQuery + ",ISNULL(SUM(TOTAL_ACTUAL_UNITS),0) as ACTUAL_UNITS,ISNULL(SUM(TOTAL_PROJECTION_UNITS),0) as PROJECTION_UNITS\n";
            totalQuery += " from dbo.ST_ALTERNATE_HIST_ALLOCATION ST JOIN\n";
        } else {
            totalQuery = "select " + freqQuery + ",ISNULL(SUM(TOTAL_ACTUAL_AMOUNT),0) as ACTUAL_AMOUNT,ISNULL(SUM(TOTAL_PROJECTION_AMOUNT),0) as PROJECTION_AMOUNT\n";
            totalQuery += " from dbo.ST_DISC_ALTERNATE_HIST_ALLOCATION ST JOIN\n";
        }
        totalQuery += " \"PERIOD\" I ON ST.PERIOD_SID = I.PERIOD_SID  where "
                + "I.PERIOD_SID BETWEEN "
                + "(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\"  = '" + fromYear + "' and \"MONTH\" = '" + fromMonth + "')"
                + "AND"
                + "(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\"  = '" + toYear + "' and \"MONTH\" = '" + toMonth + "')"
                + " AND ST.USER_ID = " + session.getUserId() + "\n"
                + " AND ST.SESSION_ID = " + session.getSessionId()
                + " group by ST.PROJECTION_DETAILS_SID," + freqQuery;

        if (session.getForecastName().equals("Sales Projection")) {
            query = "select * from ( select ST.PROJECTION_DETAILS_SID," +(Constants.ANNUALLY.equals(altHistoryDTO.getFrequency()) ? annualFreq : freqQuery)  + ",CN.CONTRACT_MASTER_SID,CN.CONTRACT_NO,CN.CONTRACT_NAME,\n"
                    + " CM.COMPANY_NO, CM.COMPANY_NAME, IM.ITEM_NO,IM.ITEM_NAME,\n"
                    + " AVG(ST.ACTUAL_ALLOCATION_PERCENT) as ACTUAL_ALLOCATION_PERCENT,\n"
                    + "SUM(ST.ACTUAL_UNITS) as ACTUAL_UNITS,\n"
                    + "AVG(ST.PROJECTION_ALLOCATION_PERCENT) as PROJECTION_ALLOCATION_PERCENT,\n"
                    + "SUM(ST.PROJECTION_UNITS) as PROJECTION_UNITS,"                    
                    + (addToQueue ? " ST.SELECTED_CHECKBOX " :" ST.AVAILABLE_CHECKBOX " )
                    + ", DENSE_RANK() OVER (\n" +
                        "   ORDER BY ST.PROJECTION_DETAILS_SID\n" +
                        "    ) AS ROW_ID  from ST_ALTERNATE_HIST_ALLOCATION ST  JOIN\n";
        } else {
            query = "select * from ( select ST.PROJECTION_DETAILS_SID," + (Constants.ANNUALLY.equals(altHistoryDTO.getFrequency()) ? annualFreq : freqQuery) + ",CN.CONTRACT_MASTER_SID,CN.CONTRACT_NO,CN.CONTRACT_NAME,\n"
                    + " CM.COMPANY_NO, CM.COMPANY_NAME, IM.ITEM_NO,IM.ITEM_NAME,\n"
                    + " AVG(ST.ACTUAL_ALLOCATION_PERCENT) as ACTUAL_ALLOCATION_PERCENT,\n"
                    + "SUM(ST.ACTUAL_AMOUNT) as ACTUAL_AMOUNT,\n"
                    + " AVG(ST.PROJECTION_ALLOCATION_PERCENT) as PROJECTION_ALLOCATION_PERCENT,\n"
                    + "SUM(ST.PROJECTION_AMOUNT) as PROJECTION_AMOUNT, "
                    + (addToQueue ? " ST.SELECTED_CHECKBOX " :" ST.AVAILABLE_CHECKBOX " )
                    + ", DENSE_RANK() OVER (\n" +
                        "   ORDER BY ST.PROJECTION_DETAILS_SID\n" +
                        "    ) AS ROW_ID from ST_DISC_ALTERNATE_HIST_ALLOCATION ST  JOIN\n";

        }

        query = query + " PROJECTION_DETAILS PD ON ST.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID "
                + " AND PD.PROJECTION_MASTER_SID = '" + session.getProjectionId() + "' JOIN\n"
                + " \"PERIOD\" I ON ST.PERIOD_SID = I.PERIOD_SID RIGHT  JOIN\n"
                + " CCP_DETAILS CCP ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID JOIN\n"
                + " CONTRACT_MASTER CN ON CCP.CONTRACT_MASTER_SID = CN.CONTRACT_MASTER_SID " + contractFilter + " JOIN\n"
                + " COMPANY_MASTER CM ON CCP.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID " + companyFilter + " JOIN\n"
                + " ITEM_MASTER IM ON CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID " + itemFilter + "  \n"
                + "where \n"
                + check
                + "I.PERIOD_SID BETWEEN "
                + "(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\"  = '" + fromYear + "' and \"MONTH\" = '" + fromMonth + "')"
                + "AND"
                + "(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\"  = '" + toYear + "' and \"MONTH\" = '" + toMonth + "')"
                + "AND ST.USER_ID = " + session.getUserId() + "\n"
                + "AND ST.SESSION_ID = " + session.getSessionId()
                + "group by ST.PROJECTION_DETAILS_SID," + freqQuery + ",CN.CONTRACT_MASTER_SID,CN.CONTRACT_NO,CN.CONTRACT_NAME,\n"
                + " CM.COMPANY_NO, CM.COMPANY_NAME, IM.ITEM_NO,IM.ITEM_NAME,"
                + (addToQueue ? " ST.SELECTED_CHECKBOX " :" ST.AVAILABLE_CHECKBOX  " ) +" ) A ";
        if (!iscount) {
            if (!addToQueue) {
                if (start > 0) {
                    start = start - 1;               
                }
                offset = offset - 1;
            }
            query = query + " WHERE ROW_ID >" + start + " AND ROW_ID <= " + (start+offset) + " ORDER BY A.PROJECTION_DETAILS_SID ";
        }


        totalLevelList = HelperTableLocalServiceUtil.executeSelectQuery(totalQuery);

        list = HelperTableLocalServiceUtil.executeSelectQuery(query);

        if (!addToQueue) {
            if (totalLevelList != null && totalLevelList.size() > 0 && start == 0) {
                int count = totalLevelList.size();
//                String currentccpId = StringUtils.EMPTY;
                for (int i = 0; i < count; i++) {
                    Object[] obj = (Object[]) totalLevelList.get(i);

                    String year = String.valueOf(obj[0]);
                    String fre = String.valueOf(obj[1]);

                    totDTO.setContractNo("Total Alternate History Baseline");
                    totDTO.setCheckRecord(null);
                    if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                        commonPerColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + Constant.ACTALLOCATION;
                        totDTO.addStringProperties(commonPerColumn, "0.00");
                        commonColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + ("Sales Projection".equalsIgnoreCase(session.getForecastName()) ? "Actual Units" : ACTUAL_PAYMENTS.getConstant());
                        totDTO.addStringProperties(commonColumn, String.valueOf(obj[2]));
                    }
                    if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                        commonPerColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + Constant.PROJALLOCATION;

                        totDTO.addStringProperties(commonPerColumn, "0.00");
                        commonColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + ("Sales Projection".equalsIgnoreCase(session.getForecastName()) ? "Projected Units" : PROJECTED_PAYMENTS.getConstant());

                        totDTO.addStringProperties(commonColumn, String.valueOf(obj[3]));
                    }
                }
                totalList.add(totDTO);
            }
        }

        allocationList.addAll(totalList);
        if (list != null && list.size() > 0) {
            int count = list.size();
            String currentProjDetailsSID = null;
            for (int i = 0; i < count; i++) {
                Object[] obj = (Object[]) list.get(i);

                if (currentProjDetailsSID == null || !currentProjDetailsSID.equals(String.valueOf(obj[0]))) {

                    altDTO = new AlternateHistoryDTO();
                    allocationList.add(altDTO);
                    currentProjDetailsSID = String.valueOf(obj[0]);
                }
                altDTO.setProjDetailSid((Integer) obj[0]);
                String year = String.valueOf(obj[1]);
                String fre = String.valueOf(obj[2]);
                altDTO.setContractName(String.valueOf(obj[5]));
                altDTO.setContractNo(String.valueOf(obj[4]));
                altDTO.setCustomerName(String.valueOf(obj[7]));
                altDTO.setCustomerNo(String.valueOf(obj[6]));
                altDTO.setItemNo(String.valueOf(obj[8]));
                altDTO.setItemName(String.valueOf(obj[9]));

                if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                    commonPerColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + Constant.ACTALLOCATION;

                    altDTO.addStringProperties(commonPerColumn, obj[10] == null ? "0.00" : String.valueOf(obj[10]));
                    commonColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + ("Sales Projection".equalsIgnoreCase(session.getForecastName()) ? "Actual Units" : ACTUAL_PAYMENTS.getConstant());

                    altDTO.addStringProperties(commonColumn, obj[11] == null ? "0.0" : String.valueOf(obj[11]));
                }
                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                    commonPerColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + Constant.PROJALLOCATION;

                    altDTO.addStringProperties(commonPerColumn, obj[12] == null ? "0.00" : String.valueOf(obj[12]));
                    commonColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + ("Sales Projection".equalsIgnoreCase(session.getForecastName()) ? "Projected Units" : PROJECTED_PAYMENTS.getConstant());

                    altDTO.addStringProperties(commonColumn, obj[13] == null ? "0.0" : String.valueOf(obj[13]));
                }

                altDTO.setCheckRecord(obj[14] == null ? false : (Boolean) (obj[14]));
            }
        }
        return allocationList;
    }

    public void check_available_allocationTab(AlternateHistoryDTO dto, SessionDTO session,
            int checkOrUncheck, java.sql.Date startDate, java.sql.Date endDate) {
        String query = SQlUtil.getQuery("Line_Level_Available_Checkbox");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace("[$TABLE_NAME]", "ST_ALTERNATE_HIST_ALLOCATION");
        } else {
            query = query.replace("[$TABLE_NAME]", "ST_DISC_ALTERNATE_HIST_ALLOCATION");
        }
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$START_DATE]", startDate.toString());
        query = query.replace("[$END_DATE]", endDate.toString());
        query = query.replace("[$PROJECTION_DETAILS_SID]", String.valueOf(dto.getProjDetailSid()));
        query = query.replace("[$AVAILABLE_CHECKBOX] ", String.valueOf(checkOrUncheck));
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }
   public void checkAll_available_allocationTab(SessionDTO session,int checkOrUncheck, java.sql.Date startDate, java.sql.Date endDate) {
        String query = SQlUtil.getQuery("Available_CheckAll");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace("[$TABLE_NAME]", "ST_ALTERNATE_HIST_ALLOCATION");
        } else {
            query = query.replace("[$TABLE_NAME]", "ST_DISC_ALTERNATE_HIST_ALLOCATION");
        }
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$START_DATE]", startDate.toString());
        query = query.replace("[$END_DATE]", endDate.toString());
        query = query.replace("[$AVAILABLE_CHECKBOX] ", String.valueOf(checkOrUncheck));
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    /**
     * To insert the alternate history table
     * @param session
     * @param projDetailsSid
     * @param ccpDetailsId
     * @return 
     */
    public boolean altHistInsert(SessionDTO session, String projDetailsSid,final String ccpDetailsId) {
        LOGGER.info("Entering altHistInsert" + session.getSessionId() + projDetailsSid);
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            } else {
                LOGGER.info("Failed to lookup datasource.");
            }
            if (connection != null) {
                
                LOGGER.info("Got Connection " + connection.toString() + ", ");
                statement = connection.prepareCall("{call PRC_ALTERNATE_HISTORY_INSERT (?,?,?,?,?,?)}");
                
                statement.setString(1, projDetailsSid);
                statement.setString(2, ccpDetailsId);
                statement.setString(3, session.getStartDate());
                statement.setString(4, session.getEndDate());
                statement.setInt(5,    Integer.parseInt(session.getUserId()));
                statement.setInt(6,    Integer.parseInt(session.getSessionId()));
                statement.execute();
            }
        } catch (Exception ex) {            
            LOGGER.error(ex.getCause());
            return false;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return true;
    }

    public void call(Object[] orderedArg, SessionDTO session, String procedureName) {
        CommonLogic.callProcedure(procedureName, orderedArg);
    }

    public String getProjDetaSid(String ccpIds, int projectionSid) {
        String projDetSid = StringUtils.EMPTY;
        List<Integer> list = new ArrayList<Integer>();
        String query = "select distinct PROJECTION_DETAILS_SID from dbo.PROJECTION_DETAILS "
                + "where CCP_DETAILS_SID in( " + ccpIds + " )"
                + "and PROJECTION_MASTER_SID=" + projectionSid;
        list = (List<Integer>) HelperTableLocalServiceUtil.executeSelectQuery(query);

        for (int pdSid : list) {
            if (projDetSid.isEmpty()) {
                projDetSid = StringUtils.EMPTY + pdSid;
            }
            projDetSid = projDetSid + "," + pdSid;
        }

        return projDetSid;

    }

    public String getDetailsSid(String projectionId) {
        String query = "select distinct PROJECTION_DETAILS_SID from PROJECTION_DETAILS where PROJECTION_MASTER_SID = '" + projectionId + "'";
        List<String> detailsList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        String detailsSid = CommonUtils.CollectionToString(detailsList, true);
        return detailsSid;
    }

    public String buildCCPCountQuery(boolean isCustomer, int projectionSid, String userId, String sessionId) {
        StringBuilder query = new StringBuilder();
        if (isCustomer) {
            query.append("SELECT Count(DISTINCT CCPD.Company_MASTER_SID) ");
        } else {
            query.append("SELECT Count(DISTINCT CCPD.ITEM_MASTER_SID )");
        }
        query.append(" FROM   dbo.CCP_DETAILS CCPD  ");
        query.append(" JOIN dbo.PROJECTION_DETAILS PD  ");
        query.append(" ON PD.CCP_DETAILS_SID = CCPD.CCP_DETAILS_SID  ");
        query.append(" JOIN dbo.PROJECTION_MASTER PM  ");
        query.append("  ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID  ");
        query.append(" JOIN dbo.ST_NM_SALES_PROJECTION_MASTER SPM  ");
        query.append("  ON SPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID  ");
        query.append(" JOIN dbo.CCP_MAP CCPM  ");
        query.append("  ON CCPM.CCP_DETAILS_SID = PD.CCP_DETAILS_SID  ");
        query.append("JOIN dbo.RELATIONSHIP_LEVEL_DEFINITION RLD  ");
        query.append("   ON RLD.RELATIONSHIP_LEVEL_SID = CCPM.RELATIONSHIP_LEVEL_SID  ");
        query.append("  JOIN dbo.RELATIONSHIP_BUILDER RB  ");
        query.append("    ON RB.RELATIONSHIP_BUILDER_SID = RLD.RELATIONSHIP_BUILDER_SID  ");
        query.append("   JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD  ");
        query.append("    ON RB.HIERARCHY_DEFINITION_SID = HLD.HIERARCHY_DEFINITION_SID  ");
        query.append(" WHERE  PM.PROJECTION_MASTER_SID = " + projectionSid + "  ");
        if (isCustomer) {
            query.append(" AND HLD.LEVEL_NAME IN ('Customer','Trading Partner')  ");
        } else {
            query.append(" AND HLD.LEVEL_NAME IN ( 'Item', 'NDC' )  ");

        }
        query.append(" AND SPM.USER_ID='" + userId + "' ");
        query.append(" AND SPM.SESSION_ID='" + sessionId + "' ");
        query.append(" AND SPM.CHECK_RECORD='1'");
        LOGGER.info("_____Count query_______________" + query.toString());
        return query.toString();
    }

    public int getCCPCount(String query) {
        int count = 0;
        List<Object> objList = new ArrayList<Object>();
        objList = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);

        if (objList != null && !objList.isEmpty()) {
            Object ob = objList.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }

        return count;

    }

    public String buildCCPListQuery(boolean isCustomer, int projectionSid, String userId, String sessionId) {
        StringBuilder query = new StringBuilder();

        query.append(" SELECT DISTINCT CCPD.CCP_DETAILS_SID ");

        query.append(" FROM   dbo.CCP_DETAILS CCPD  ");
        query.append(" JOIN dbo.PROJECTION_DETAILS PD  ");
        query.append(" ON PD.CCP_DETAILS_SID = CCPD.CCP_DETAILS_SID  ");
        query.append(" JOIN dbo.PROJECTION_MASTER PM  ");
        query.append("  ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID  ");
        query.append(" JOIN dbo.ST_NM_SALES_PROJECTION_MASTER DPM  ");
        query.append("  ON DPM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID  ");
        query.append(" JOIN dbo.CCP_MAP CCPM  ");
        query.append("  ON CCPM.CCP_DETAILS_SID = PD.CCP_DETAILS_SID  ");
        query.append(" JOIN dbo.RELATIONSHIP_LEVEL_DEFINITION RLD  ");
        query.append("   ON RLD.RELATIONSHIP_LEVEL_SID = CCPM.RELATIONSHIP_LEVEL_SID  ");
        query.append("  JOIN dbo.RELATIONSHIP_BUILDER RB  ");
        query.append("    ON RB.RELATIONSHIP_BUILDER_SID = RLD.RELATIONSHIP_BUILDER_SID  ");
        query.append("   JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD  ");
        query.append("    ON RB.HIERARCHY_DEFINITION_SID = HLD.HIERARCHY_DEFINITION_SID  ");
        query.append(" WHERE  PM.PROJECTION_MASTER_SID = ").append(projectionSid).append("  ");
        if (isCustomer) {

            query.append(" AND HLD.LEVEL_NAME IN ('Customer','Trading Partner')  ");
        } else {

            query.append(" AND HLD.LEVEL_NAME IN ( 'Item', 'NDC' )  ");

        }
        query.append(" AND DPM.USER_ID='").append(userId).append("' ");
        query.append(" AND DPM.SESSION_ID='").append(sessionId).append("' ");
        query.append(" AND DPM.CHECK_RECORD='1'");
        LOGGER.info("build ccp list query---------------->>>>" + query);
        return query.toString();
    }

    public String getCCPList(String query) {
        StringBuilder ccpList = new StringBuilder(StringUtils.EMPTY);
        List tempList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.info("--templist size---->>>>>" + tempList.size());
        //   List<Object> tempList = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        for (Object ccp : tempList) {

            if (ccpList.length() == 0) {
                ccpList.append(String.valueOf(ccp));
            } else {
                ccpList.append(",");
                ccpList.append(String.valueOf(ccp));
            }
        }

        return ccpList.toString();
    }

    public boolean checkActuals(String ccpIds, SessionDTO session) {
        boolean flag = true;
        String query = "IF Object_id('TEMPDB..#TEMP_CCP') IS NOT NULL\n"
                + "  DROP TABLE #TEMP_CCP\n"
                + " \n"
                + "CREATE TABLE #TEMP_CCP\n"
                + "  (\n"
                + "     CCP_DETAILS_SID INT\n"
                + "  )\n"
                + " \n"
                + "INSERT INTO #TEMP_CCP\n"
                + "SELECT token\n"
                + "FROM   UDF_SPLITSTRING('" + ccpIds + "', ',')\n"
                + " \n"
                + "SELECT CCP.CCP_DETAILS_SID\n"
                + "FROM   CCP_DETAILS CCP\n"
                + "JOIN   #TEMP_CCP TC ON CCP.CCP_DETAILS_SID = TC.CCP_DETAILS_SID\n"
                + "left JOIN   ACTUALS_MASTER AM ON AM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                + "                        AND AM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID\n"
                + "                        AND AM.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n"
                + "                        AND AM.ACCRUAL_ACTUAL_START_DATE BETWEEN Dateadd(yy, Datediff(yy, 0, Getdate()) - 3, 0) AND Dateadd(mm, Datediff(mm, 0, Getdate()), 0) - 1\n"
                + "GROUP  BY CCP.CCP_DETAILS_SID\n"
                + "HAVING Sum (AM.SALES_AMOUNT) = 0\n"
                + "        OR Sum(AM.SALES_AMOUNT) IS NULL";
        LOGGER.info("--no actual query---------->>>>>" + query);
        List<Integer> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.info("--list size---->>>>" + list.size());
        if (list != null && list.size() > 0) {
            StringBuilder ccps = new StringBuilder(StringUtils.EMPTY);

            flag = false;
            for (int ccpid : list) {
                if (ccps.length() == 0) {
                    ccps.append(String.valueOf(ccpid));
                } else {
                    ccps.append(",");
                    ccps.append(String.valueOf(ccpid));
                }
            }
            LOGGER.info("--actual ccps------------------>>>>>>" + ccps);
            session.setActualccp(ccps.toString());
            list.clear();
        }
        return flag;

    }

    public void executeUpdate(String query) {
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void executeDelete(final SessionDTO session, boolean isSales) {
        
        String query = StringUtils.EMPTY;
        deleteCustomersAndItemsFromTempTable(session);
        if (isSales) {
            query = " DELETE FROM ST_ALTERNATE_HIST_ALLOCATION WHERE SESSION_ID=" + session.getSessionId() + ";\n"
                    + "DELETE FROM ST_CCP_ACTUAL_DETAILS WHERE SESSION_ID=" + session.getSessionId() + " ;";
        } else {
            query = " DELETE FROM ST_DISC_ALTERNATE_HIST_ALLOCATION WHERE SESSION_ID=" + session.getSessionId() + ";\n"
                    + "DELETE FROM ST_DISC_CCP_ACTUAL_DETAILS WHERE SESSION_ID=" + session.getSessionId() + " ;";
        }        
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

    }
    
    public void removeTempTable(SessionDTO session) {
        String query = "delete  from dbo.AH_TEMP_DETAILS WHERE USER_ID='" + session.getUserId() + "' AND SESSION_ID='" + session.getSessionId() + "'";
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void insertforSalesTotal(String userId, String sessionId, String ccpIDs) {
        StringBuilder query = new StringBuilder();

        query.append(" MERGE INTO dbo.ST_ALTERNATE_HIST_ALLOCATION AS TARGET USING  ");
        query.append(" (select CCPAC.PERIOD_SID AS PERIOD_SID,ISNULL(sum(ACTUAL_UNITS),0) AS ACTUAL_UNITS,ISNULL(SUM(PROJECTION_UNITS),0)  AS PROJECTION_UNITS  ");
        query.append(" from dbo.ST_CCP_ACTUAL_DETAILS CCPAC  ");
        query.append(" join dbo.\"PERIOD\" PER ON PER.PERIOD_SID=CCPAC.PERIOD_SID  ");
        query.append(" WHERE CCPAC.USER_ID=" + userId + " and CCPAC.SESSION_ID=" + sessionId + "  ");
        query.append(" AND CCPAC.CCP_DETAILS_SID in ("+ccpIDs+") ");
        query.append(" group by CCPAC.PERIOD_SID)  AS SOURCE   ");
        query.append(" ON ( TARGET.PERIOD_SID = SOURCE.PERIOD_SID AND TARGET.USER_ID=" + userId + " AND TARGET.SESSION_ID=" + sessionId + " )  ");
        query.append(" WHEN MATCHED THEN UPDATE SET   ");
        query.append(" TARGET.TOTAL_ACTUAL_UNITS = SOURCE.ACTUAL_UNITS,   ");
        query.append("  TARGET.TOTAL_PROJECTION_UNITS = SOURCE.PROJECTION_UNITS;  ");

        CommonLogic.executeBulkUpdateQuery(query.toString(), null, null);

    }

    public void insertforDiscountTotal(String userId, String sessionId, String ccpIDs) {
        StringBuilder query = new StringBuilder();

        query.append(" MERGE INTO dbo.ST_DISC_ALTERNATE_HIST_ALLOCATION AS TARGET USING \n");
        query.append(" (select CCPAC.PERIOD_SID AS PERIOD_SID,ISNULL(sum(ACTUAL_AMOUNT),0) AS ACTUAL_AMOUNT,ISNULL(SUM(PROJECTION_AMOUNT),0)  AS PROJECTION_AMOUNT \n");
        query.append(" from dbo.ST_DISC_CCP_ACTUAL_DETAILS CCPAC \n");
        query.append(" join dbo.\"PERIOD\" PER ON PER.PERIOD_SID=CCPAC.PERIOD_SID \n");
        query.append(" WHERE CCPAC.USER_ID=" + userId + " and CCPAC.SESSION_ID=" + sessionId + " ");
        query.append(" AND CCPAC.CCP_DETAILS_SID in ("+ccpIDs+") ");
        query.append(" group by CCPAC.PERIOD_SID)  AS SOURCE \n");
        query.append(" ON ( TARGET.PERIOD_SID = SOURCE.PERIOD_SID AND TARGET.USER_ID=" + userId + " AND TARGET.SESSION_ID=" + sessionId + " )\n");
        query.append(" WHEN MATCHED THEN UPDATE SET \n");
        query.append(" TARGET.TOTAL_ACTUAL_AMOUNT = SOURCE.ACTUAL_AMOUNT, \n");
        query.append(" TARGET.TOTAL_PROJECTION_AMOUNT = SOURCE.PROJECTION_AMOUNT; ");
        CommonLogic.executeBulkUpdateQuery(query.toString(), null, null);

    }
   
    
    public void getAllSelectedItems(CustomFieldGroup customerSearchBinder, AlternateHistoryDTO altHistoryDTO, String sessionId) {
        getCheckedItemsFromTemptable(customerSearchBinder, altHistoryDTO, null, 0, 0, sessionId);
    }

    public void removeItemsFromTable(final SessionDTO session, final String screenName) {
        String query = "DELETE FROM AH_TEMP_DETAILS WHERE CHECK_RECORD= '0' AND SCREEN_NAME = '" + screenName + "' AND USER_ID = '" + session.getUserId() + "' AND SESSION_ID ='" + session.getSessionId() + "'";        
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

//    public void removeItemsFromTable(final SessionDTO session, final AlternateHistoryDTO alternateHistoryDTO) {
//        String query = "select COMPONENT_NAME,COMPONENT_NO,COMPONENT_TYPE,COMPANY_NAME,COMPANY_NO,COMPANY_SID,CONTRACT_HOLDER,COMPONENT_MASTER_SID,AH_TEMP_DETAILS_SID FROM AH_TEMP_DETAILS\n"
//                + "WHERE CHECK_RECORD = 1 AND SCREEN_NAME = 'Customer_Selection' AND USER_ID= '" + session.getUserId() + "' AND SESSION_ID= '" + session.getSessionId() + "'";
//        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
//        getCustomizedDTOFromTemp(list);
//    }

    public void updateSelectedItemsInTempTable(final boolean checkValue, final AlternateHistoryDTO dto, final String screenName, final SessionDTO session) {
        String query = "UPDATE AH_TEMP_DETAILS SET CHECK_RECORD = '" + (checkValue ? "0" : "1") + "' "
                + "WHERE BUSINESS_UNIT_NO = '" + String.valueOf(dto.getBusinessUnitNo()) + "' AND BUSINESS_UNIT_NAME ='" + String.valueOf(dto.getBusinessUnitName()) + "'"
                + " AND THERAPUTIC_CLASS='" + String.valueOf(dto.getTheraputicClass()) + "'"
                + " AND BRAND_NAME='" + String.valueOf(dto.getBrand()) + "'"
                + " AND ITEM_NO='" + (String.valueOf(dto.getItemNo()).contains("'") ? String.valueOf(dto.getItemNo()).replace("'", "''") : String.valueOf(dto.getItemNo())) + "'"
                + " AND ITEM_NAME='" + (String.valueOf(dto.getItemName()).contains("'") ? String.valueOf(dto.getItemName()).replace("'", "''") : String.valueOf(dto.getItemName())) + "'"
                + " AND ITEM_IDENTIFIER_TYPE='" + String.valueOf(dto.getItemIdentifierType()) + "' "
                + " AND ITEM_IDENTIFIER='" + String.valueOf(dto.getItemIdentifier()) + "' "
                + " AND ITEM_MASTER_SID='" + String.valueOf(dto.getItemMasterSid()) + "' "
                + " AND COMPANY_SID='" + String.valueOf(dto.getCompanymasterSid()) + "' "
                + " AND COMPONENT_MASTER_SID='" + String.valueOf(dto.getContractMasterSid()) + "' "
                + " AND COMPONENT_NO='" + String.valueOf(dto.getCcpDetailsId()) + "' "
                + "AND SCREEN_NAME = '" + screenName + "' AND USER_ID = '" + session.getUserId() + "' AND SESSION_ID = '" + session.getSessionId() + "';";
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }
    
    public void insertItemsIntoTemp(boolean checkValue, String screenName, SessionDTO session, Boolean isAvailable, Map<String, AlternateHistoryDTO> selectedMap) {
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        String createdDate = simpleDateFormat.format(new Date());

        String query = StringUtils.EMPTY;
        for (Map.Entry<String, AlternateHistoryDTO> entry : selectedMap.entrySet()) {

            AlternateHistoryDTO dto = entry.getValue();

            query = "INSERT INTO DBO.AH_TEMP_DETAILS (BUSINESS_UNIT_NO,BUSINESS_UNIT_NAME,THERAPUTIC_CLASS,BRAND_NAME,ITEM_NO,ITEM_NAME,ITEM_IDENTIFIER_TYPE,\n"
                    + "ITEM_IDENTIFIER,ITEM_MASTER_SID,CHECK_RECORD,SCREEN_NAME,USER_ID,SESSION_ID,CREATED_BY,CREATED_DATE,COMPANY_SID,COMPONENT_MASTER_SID,COMPONENT_NO)\n"
                    + "VALUES ('?BUSINESS_UNIT_NO','?BUSINESS_UNIT_NAME','?THERAPUTIC_CLASS','?BRAND_NAME','?ITEM_NO','?ITEM_NAME','?ITEM_IDENTIFIER_TYPE',\n"
                    + "'?ITEM_IDENTIFIER','?ITEM_MASTER_SID','?CHECK_RECORD','?SCREEN_NAME','?USER_ID','?SESSION_ID','?CREATED_BY','?CREATED_DATE','?COMPANY_SID','?COMPONENT_MASTER_SID','?COMPONENT_NO')";

            if (!dto.getBusinessUnitNo().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?BUSINESS_UNIT_NO", String.valueOf(dto.getBusinessUnitNo()));
            } else {
                query = query.replaceAll("\\?BUSINESS_UNIT_NO", StringUtils.EMPTY);
            }
            if (!dto.getBusinessUnitName().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?BUSINESS_UNIT_NAME", String.valueOf(dto.getBusinessUnitName()));
            } else {
                query = query.replaceAll("\\?BUSINESS_UNIT_NAME", StringUtils.EMPTY);
            }
            if (!dto.getTheraputicClass().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?THERAPUTIC_CLASS", String.valueOf(dto.getTheraputicClass()));
            } else {
                query = query.replaceAll("\\?THERAPUTIC_CLASS", StringUtils.EMPTY);
            }
            if (!dto.getBrand().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?BRAND_NAME", String.valueOf(dto.getBrand()));
            } else {
                query = query.replaceAll("\\?BRAND_NAME", StringUtils.EMPTY);
            }
            if (!dto.getItemNo().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?ITEM_NO", String.valueOf(dto.getItemNo()));
            } else {
                query = query.replaceAll("\\?ITEM_NO", StringUtils.EMPTY);
            }
            if (!dto.getItemName().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?ITEM_NAME", String.valueOf(dto.getItemName()));
            } else {
                query = query.replaceAll("\\?ITEM_NAME", StringUtils.EMPTY);
            }
            if (!dto.getItemIdentifierType().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?ITEM_IDENTIFIER_TYPE", String.valueOf(dto.getItemIdentifierType()));
            } else {
                query = query.replaceAll("\\?ITEM_IDENTIFIER_TYPE", StringUtils.EMPTY);
            }
            if (!dto.getItemIdentifier().equals(StringUtils.EMPTY)) {
                query = query.replaceAll("\\?ITEM_IDENTIFIER", String.valueOf(dto.getItemIdentifier()));
            } else {
                query = query.replaceAll("\\?ITEM_IDENTIFIER", StringUtils.EMPTY);
            }
            if (dto.getItemMasterSid() != 0) {
                query = query.replaceAll("\\?ITEM_MASTER_SID", String.valueOf(dto.getItemMasterSid()));
            }
            if (dto.getContractMasterSid() != 0) {
                query = query.replaceAll("\\?COMPONENT_MASTER_SID", String.valueOf(dto.getContractMasterSid()));
            }
            if (dto.getCompanymasterSid() != 0) {
                query = query.replaceAll("\\?COMPANY_SID", String.valueOf(dto.getCompanymasterSid()));
            }

            query = query.replaceAll("\\?COMPONENT_NO", String.valueOf(dto.getCcpDetailsId()));
            if (dto.getCheck()) {
                query = query.replaceAll("\\?CHECK_RECORD", Constant.STRING_ONE);
            } else {
                query = query.replaceAll("\\?CHECK_RECORD", DASH);
            }
            query = query.replaceAll("\\?SCREEN_NAME", screenName);
            query = query.replaceAll("\\?USER_ID", userId);
            query = query.replaceAll("\\?SESSION_ID", session.getSessionId());
            query = query.replaceAll("\\?CREATED_BY", userId);
            query = query.replaceAll("\\?CREATED_DATE", createdDate);

        }
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }
    
    public List getCompanyList(int start, int offset, CustomFieldGroup customerSearchBinder, AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, Boolean isCount, SessionDTO session) throws PortalException, SystemException {
        List list;
        String query = StringUtils.EMPTY;
        Map<String, Object> parameters = new HashMap();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString() + Constant.PERCENT;
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                }
            }
        }

        if (isCount) {
            query = "SELECT COUNT(*) FROM (";
//            query = "select COUNT(COMPONENT_MASTER_SID) FROM AH_TEMP_DETAILS \n"
//                    + "WHERE CHECK_RECORD = 1 AND SCREEN_NAME = 'Customer_Selection' AND USER_ID= '" + session.getUserId() + "' AND SESSION_ID= '" + session.getSessionId() + "'";
        } 
//        else {
             
//            query = "select COMPONENT_NAME,COMPONENT_NO,COMPONENT_TYPE,COMPANY_NAME,COMPANY_NO,COMPANY_SID,CONTRACT_HOLDER,COMPONENT_MASTER_SID,AH_TEMP_DETAILS_SID FROM AH_TEMP_DETAILS\n"
//                    + "WHERE CHECK_RECORD = 1 AND SCREEN_NAME = 'Customer_Selection' AND USER_ID= '" + session.getUserId() + "' AND SESSION_ID= '" + session.getSessionId() + "'";
//        }
         query += SQlUtil.getQuery("CUSTOMER_SELECTION_SELECTED_SEARCH");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        String whereClause=" WHERE ";
        String andClause = " AND ";
        boolean isWhereClaues = true;
        //For Filters
        if (parameters.containsKey("filter~customerNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerNo")))) {
          
            query = query + (isWhereClaues ? whereClause : andClause) + " CM1.COMPANY_NO like '" + String.valueOf(parameters.get("filter~customerNo")) + "'";
            isWhereClaues = false;
        }

        if (parameters.containsKey("filter~contractHolder") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractHolder")))) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  CM.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~contractHolder")) + "'";
            isWhereClaues = false;
        }

        if (parameters.containsKey("filter~contractNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractNo")))) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  CON_M.CONTRACT_NO like '" + String.valueOf(parameters.get("filter~contractNo")) + "'";
            isWhereClaues = false;
        }

        if (parameters.containsKey("filter~contractName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractName")))) {
            query = query + (isWhereClaues ? whereClause : andClause) + "   CON_M.CONTRACT_NAME like '" + String.valueOf(parameters.get("filter~contractName")) + "'";
            isWhereClaues = false;
        }

        if (parameters.containsKey("filter~customerName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerName")))) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  CM1.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~customerName")) + "'";
            isWhereClaues = false;
        }

        if (parameters.containsKey("filter~marketType") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~marketType")))) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  HT.DESCRIPTION like '" + String.valueOf(parameters.get("filter~marketType")) + "'";
            isWhereClaues = false;
        }
        if (parameters.containsKey("filter~check") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~check")))) {
            query = query + (isWhereClaues ? whereClause : andClause)+ "  CS.SELECTED_CHECKBOX like '" + String.valueOf(parameters.get("filter~check")) + "'";
        }
        if (isCount) {
            query += ") TEMP_DETAILS ;";
        } else {
            query += " ORDER BY CON_M.CONTRACT_NO OFFSET " + start + "  ROWS FETCH NEXT " + offset + " ROWS ONLY ";
        }
 
        list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return isCount ? list : getCustomizedDTO(list);
    }
    
    public int alternateSelectionCount(final SessionDTO sessionDTO, Set<Container.Filter> filters) {
        int count;
        
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        String companyFilter = StringUtils.EMPTY;
        String itemFilter = StringUtils.EMPTY;
        String contractFilter = StringUtils.EMPTY;
        
         if (parameters.containsKey("filter~contractNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractNo")))) {
            contractFilter = contractFilter + " AND CON.CONTRACT_NO like '" + String.valueOf(parameters.get("filter~contractNo")) + "'";
        }

        if (parameters.containsKey("filter~contractName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractName")))) {
            contractFilter = contractFilter + " AND CON.CONTRACT_NAME like '" + String.valueOf(parameters.get("filter~contractName")) + "'";
        }

        if (parameters.containsKey("filter~customerNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerNo")))) {
            companyFilter = companyFilter + " AND CM.COMPANY_NO like '" + String.valueOf(parameters.get("filter~customerNo")) + "'";
        }

        if (parameters.containsKey("filter~customerName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerName")))) {
            companyFilter = companyFilter + " AND CM.COMPANY_NAME like '" + String.valueOf(parameters.get("filter~customerName")) + "'";
        }

        if (parameters.containsKey("filter~itemNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemNo")))) {
            itemFilter = itemFilter + " AND IM.ITEM_NO like '" + String.valueOf(parameters.get("filter~itemNo")) + "'";
        }

        if (parameters.containsKey("filter~itemName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemName")))) {
            itemFilter = itemFilter + " AND IM.ITEM_NAME like '" + String.valueOf(parameters.get("filter~itemName")) + "'";
        }
        
        String query = SQlUtil.getQuery("alternate-history-selection-count");
        query = query.replace("[$USER_ID]", sessionDTO.getUserId());
        query = query.replace("[$SESSION_ID]", sessionDTO.getSessionId());
        query = query.replace("@COMPANY_FILTER", companyFilter);
        query = query.replace("@ITEM_FILTER", itemFilter);
        query = query.replace("@CONTRACT_FILTER", contractFilter);
        
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        count = list == null || list.isEmpty() ? 0 : (Integer) list.get(0);
        return count;
    }

    
      public void deleteForsalesdiscount(String sessionId) {
          StringBuilder query = new StringBuilder();

          query.append(" DELETE FROM dbo.ST_ALTERNATE_HIST_ALLOCATION where SESSION_ID=" + sessionId + ";");
          query.append(" DELETE FROM dbo.ST_DISC_ALTERNATE_HIST_ALLOCATION where SESSION_ID=" + sessionId + ";");
          CompanyMasterLocalServiceUtil.executeQuery(query.toString());

    }
    
    /**
     * METHOD will update 0 in table for all columns
     * @param session
     * @return
     * @throws Exception 
     */
    public boolean resetAllocationTab(SessionDTO session) {

        String query;
        try {
            if (session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)) {
                query = SQlUtil.getQuery("AllocationTab_reset_query_Discount");
                query = query.replace("[$USER_ID]", session.getUserId());
                query = query.replace("[$SESSION_ID]", session.getSessionId());
            } else {
                query = SQlUtil.getQuery("AllocationTab_reset_query_sales");
                query = query.replace("[$USER_ID]", session.getUserId());
                query = query.replace("[$SESSION_ID]", session.getSessionId());
            }
            
            HelperTableLocalServiceUtil.executeUpdateQuery(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * METHOD will update or insert in table
     *
     * @param session
     * @param altHistoryDTO
     * @param filters
     * @return
     * @throws Exception
     */
    public boolean checkAll_available_customerSelection(SessionDTO session, AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters,boolean value) {

        try {
            Map<String, Object> parameters = new HashMap();

            if (isValidCriteria(altHistoryDTO.getContractHolder())) {
                String contractHolder = altHistoryDTO.getContractHolder();
                contractHolder = contractHolder.replace(CommonUtils.CHAR_ASTERISK, '%');

                parameters.put("contractHolder", contractHolder);
            }

            if (isValidCriteria(altHistoryDTO.getContractNo())) {
                String contractNo = altHistoryDTO.getContractNo();
                contractNo = contractNo.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put("contractNo", contractNo);
            }

            if (isValidCriteria(altHistoryDTO.getCustomerNo())) {
                String customerNo = altHistoryDTO.getCustomerNo();
                customerNo = customerNo.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put(Constant.CUSTOMER_NO, customerNo);
            }
            if (isValidCriteria(altHistoryDTO.getMarketType())) {
                String marketType = altHistoryDTO.getMarketType();
                marketType = marketType.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put("marketType", marketType);
            }

            if (isValidCriteria(altHistoryDTO.getContractName())) {
                String contractName = altHistoryDTO.getContractName();
                contractName = contractName.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put("contractName", contractName);
            }

            if (isValidCriteria(altHistoryDTO.getCustomerName())) {
                String customerName = altHistoryDTO.getCustomerName();
                customerName = customerName.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put(Constant.CUSTOMER_NAME, customerName);

            }

            if (filters != null) {
                for (Container.Filter filter : filters) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                    }
                }
            }
            return queryUtils.insertOrUpdate_customerSelection(session, parameters,value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

  
   
    public boolean check_available_customerSelection(SessionDTO session, AlternateHistoryDTO altHistoryDTO) {
        String query;
        query = SQlUtil.getQuery("CUSTOMER_SELECTION_INSERT_OR_UPDATE_SINGLE_RECORD");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$CONTRACT_MASTER_SID]", String.valueOf(altHistoryDTO.getContractMasterSid()));
        query = query.replace("[$COMPANY_MASTER_SID]", String.valueOf(altHistoryDTO.getCompanymasterSid()));
        query = query.replace("[$AVAILABLE_CHECKBOX]", "1");
         
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        return true;
    }

    public boolean uncheck_available_customerSelection(SessionDTO session,AlternateHistoryDTO altHistoryDTO) {
        String query;

        query = SQlUtil.getQuery("CUSTOMER_SELECTION_SINGLE_UPDATE_QUERY_AVALILABLE");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$CONTRACT_MASTER_SID]", String.valueOf(altHistoryDTO.getContractMasterSid()));
        query = query.replace("[$COMPANY_MASTER_SID]", String.valueOf(altHistoryDTO.getCompanymasterSid()));
        query = query.replace("[$AVAILABLE_CHECKBOX]","0");
         
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        return true;
    }
    public boolean add_customerSelection(SessionDTO session) {
        String query;
        query = SQlUtil.getQuery("CUSTOMER_SELECTION_ADD_RECORDS");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$SELECTED_RECORDS]", "1");
         
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        return true;
    }


    public boolean check_uncheck_selected_customerSelection(SessionDTO session, AlternateHistoryDTO altHistoryDTO, boolean values) {
        String query;
        query = SQlUtil.getQuery("CUSTOMER_SELECTION_CHECK_UNCHECK_SELECTED");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$CONTRACT_MASTER_SID]", String.valueOf(altHistoryDTO.getContractMasterSid()));
        query = query.replace("[$COMPANY_MASTER_SID]", String.valueOf(altHistoryDTO.getCompanymasterSid()));
        query = query.replace("[$SELECTED_CHECKBOX]", String.valueOf(values ? 1 : 0));
         
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        return true;
    }

     public boolean remove_customerSelection(SessionDTO session) {
        String query;
        query = SQlUtil.getQuery("CUSTOMER_SELECTION_REMOVE_QUERY");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
         
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        return true;
    }


    
      public boolean delete_unChecked_customerSelection(SessionDTO session) {
        String query;

        query = SQlUtil.getQuery("CUSTOMER_SELECTION_DELETE_QUERY");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$SELECTED_RECORDS]", session.getSessionId());
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
         
        return true;
    }
      public boolean checkAll_selected_customerSelection(SessionDTO session,boolean value) {
        String query;

        query = SQlUtil.getQuery("CUSTOMER_SELECTION_SELECTED_CHECK_ALL_QUERY");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$SELECTED_CHECKBOX]", value ? "1" : "0");
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
         
        return true;
    }
      
      public boolean count_available_customerSelection(SessionDTO session) {
        String query;

        query = SQlUtil.getQuery("CUSTOMER_SELECTION_CHECK_COUNT");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$AVAILABLE_CHECKBOX]", "1");
         
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.isEmpty() || (list.get(0) == null) ? false : ((int)list.get(0) > 0);
    }

    public boolean count_selected_customerSelection(SessionDTO session) {
        String query;
        query = SQlUtil.getQuery("CUSTOMER_SELECTION_CHECK_COUNT");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("AVAILABLE_CHECKBOX", "SELECTED_CHECKBOX");
        query = query.replace("[$SELECTED_CHECKBOX]", "1");
         
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list.isEmpty() || (list.get(0) == null) ? false : (Integer.valueOf(list.get(0).toString()) > 0);
    }
    
    public void addItems(final SessionDTO session) {
        String removeQuery = SQlUtil.getQuery("update-on-add-item");
        removeQuery = removeQuery.replace("[$USER_ID]", session.getUserId());
        removeQuery = removeQuery.replace("[$SESSION_ID]", session.getSessionId());
        
        HelperTableLocalServiceUtil.executeUpdateQuery(removeQuery);
    }
    
    public void updateTableOnAddorCheckAll(CustomFieldGroup customerSearchBinder, AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, int start, int offset, SessionDTO session, boolean isAddAllOrCheckAll,final String checkValue) {

        Map<String, Object> parameters = new HashMap<>();        
        
        if (isValidCriteria(altHistoryDTO.getItemNo())) {
            String itemNo = altHistoryDTO.getItemNo();
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.ITEM_NO, itemNo);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getItemName())) {
            String itemName = altHistoryDTO.getItemName();
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("itemName", itemName);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getBusinessUnitNo())) {
            String businessUnitNo = altHistoryDTO.getBusinessUnitNo();
            businessUnitNo = businessUnitNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("businessUnitNo", businessUnitNo);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getBusinessUnitName())) {
            String businessUnitName = altHistoryDTO.getBusinessUnitName();
            businessUnitName = businessUnitName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("businessUnitName", businessUnitName);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getTheraputicClass())) {
            String theraputicClass = altHistoryDTO.getTheraputicClass();
            theraputicClass = theraputicClass.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("theraputicClass", theraputicClass);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getBrand())) {
            String brand = altHistoryDTO.getBrand();
            parameters.put(Constant.BRAND, brand);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifierType())) {
            String itemIdentifierType = altHistoryDTO.getItemIdentifierType();
            parameters.put("itemIdentifierType", itemIdentifierType);            
        }
        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifier())) {
            String itemIdentifier = altHistoryDTO.getItemIdentifier();
            itemIdentifier = itemIdentifier.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put("itemIdentifier", itemIdentifier);
        }

        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        queryUtils.mergeForAddOrCheckAll(parameters, start, offset, session, isAddAllOrCheckAll, checkValue);
    }

    public void removeAllItems(final SessionDTO session) {
        String removeQuery = SQlUtil.getQuery("remove-all-items");
        removeQuery = removeQuery.replace("[$USER_ID]", session.getUserId());
        removeQuery = removeQuery.replace("[$SESSION_ID]", session.getSessionId());
        
        HelperTableLocalServiceUtil.executeUpdateQuery(removeQuery);
    }

    public void removeItems(final SessionDTO session) {
        String removeQuery = SQlUtil.getQuery("remove-selected-items");
        removeQuery = removeQuery.replace("[$USER_ID]", session.getUserId());
        removeQuery = removeQuery.replace("[$SESSION_ID]", session.getSessionId());
        
        HelperTableLocalServiceUtil.executeUpdateQuery(removeQuery);
    }

    public void updateAvailableTableCheckUnCheck(final boolean isChecked, final SessionDTO session, String itemMasterSid) {
        String checkValue = isChecked ? "1" : "0";
        String updateQuery;
        updateQuery = isChecked ? SQlUtil.getQuery("add-item-to-temp-table-on-check") : SQlUtil.getQuery("update-temp-table-on-available-uncheck");
        updateQuery = updateQuery.replace("[$USER_ID]", session.getUserId());
        updateQuery = updateQuery.replace("[$SESSION_ID]", session.getSessionId());
        updateQuery = updateQuery.replace("[$AVAILABLE_CHECKBOX]", checkValue);
        updateQuery = updateQuery.replace("[$ITEM_MASTER_SID]", itemMasterSid);
        
        HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery);
    }

    public void updateSelectedTableCheckUnCheck(final boolean isChecked, final SessionDTO session, String itemMasterSid) {
        String checkValue = isChecked ? "1" : "0";
        String updateQuery = SQlUtil.getQuery("update-temp-table-on-selected-check-uncheck");
        updateQuery = updateQuery.replace("[$USER_ID]", session.getUserId());
        updateQuery = updateQuery.replace("[$SESSION_ID]", session.getSessionId());
        updateQuery = updateQuery.replace("[$SELECTED_CHECKBOX]", checkValue);
        updateQuery = updateQuery.replace("[$ITEM_MASTER_SID]", itemMasterSid);
        
        HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery);
    }
    
    
    public void updateSelectedTableOnCheckAll(final boolean isChecked, final SessionDTO session) {
        String updateQuery = SQlUtil.getQuery("update-on-selected-item-on-checkall");
        updateQuery = updateQuery.replace("[$USER_ID]", session.getUserId());
        updateQuery = updateQuery.replace("[$SESSION_ID]", session.getSessionId());
        updateQuery = updateQuery.replace("[$SELECTED_CHECKBOX]", isChecked ? "1" : "0");
        
        HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery);
    }
    
    public void updateAllAvailableCheckBox(final SessionDTO session) {
        String updateQuery = SQlUtil.getQuery("update-all-available-checkbox");
        updateQuery = updateQuery.replace("[$USER_ID]", session.getUserId());
        updateQuery = updateQuery.replace("[$SESSION_ID]", session.getSessionId());
        updateQuery = updateQuery.replace("[$AVAILABLE_CHECKBOX]", "0");
        
        HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery);
    }
    
    public boolean checkForAtleastOneCheckedItem(final SessionDTO session, final boolean isAvailableOrSelected) {
        boolean isAtleastOneChecked = false;
        String updateQuery = SQlUtil.getQuery("atleast-one-item-checked");
        updateQuery = updateQuery.replace("[$USER_ID]", session.getUserId());
        updateQuery = updateQuery.replace("[$SESSION_ID]", session.getSessionId());
        updateQuery = updateQuery.replace("[$CHECKBOX_COLUMN]", isAvailableOrSelected ? "AVAILABLE_CHECKBOX" : "SELECTED_CHECKBOX");
       
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(updateQuery);
        if (list != null && !list.isEmpty()) {
            isAtleastOneChecked = (1 == (int) list.get(0));
        }
        return isAtleastOneChecked;
    }
    
    public boolean checkSelectedCustomers(final SessionDTO session) {
        boolean isAtleastOneChecked = false;
        String updateQuery = SQlUtil.getQuery("selected-customers-check");
        updateQuery = updateQuery.replace("[$USER_ID]", session.getUserId());
        updateQuery = updateQuery.replace("[$SESSION_ID]", session.getSessionId());
        
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(updateQuery);
        if (list != null && !list.isEmpty()) {
            isAtleastOneChecked = (1 == (int) list.get(0));
        }
        return isAtleastOneChecked;
    }

    public boolean checkSelectedItems(final SessionDTO session) {
        boolean isAtleastOneChecked = false;
        String updateQuery = SQlUtil.getQuery("selected-items-check");
        updateQuery = updateQuery.replace("[$USER_ID]", session.getUserId());
        updateQuery = updateQuery.replace("[$SESSION_ID]", session.getSessionId());
        
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(updateQuery);
        if (list != null && !list.isEmpty()) {
            isAtleastOneChecked = (1 == (int) list.get(0));
        }
        return isAtleastOneChecked;
    }

    public List getSelectedCCPS(final SessionDTO session) {
        String removeQuery = SQlUtil.getQuery("get-selected-ccps");
        removeQuery = removeQuery.replace("[$USER_ID]", session.getUserId());
        removeQuery = removeQuery.replace("[$SESSION_ID]", session.getSessionId());
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(removeQuery);
        return list;
    }
          
    public void deleteCustomersAndItemsFromTempTable(final SessionDTO session) {
        String deleteQuery = SQlUtil.getQuery("delete-customer-items-from-temp-table");
        deleteQuery = deleteQuery.replace("[$USER_ID]", session.getUserId());
        deleteQuery = deleteQuery.replace("[$SESSION_ID]", session.getSessionId());   
        
        HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);
    }
    
    public void deleteUnselectedCustomersOrItems(final SessionDTO session, final boolean isItemOrCustomer) {
        String deleteQuery = isItemOrCustomer ? SQlUtil.getQuery("delete-unselected-items") : SQlUtil.getQuery("delete-unselected-customers");
        deleteQuery = deleteQuery.replace("[$USER_ID]", session.getUserId());
        deleteQuery = deleteQuery.replace("[$SESSION_ID]", session.getSessionId());
        
        HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);
    }
    


        
   public int count_avalibale_allocationTab(SessionDTO session,java.sql.Date startDate,java.sql.Date endDate) {

        String query = SQlUtil.getQuery("Count_Available_Table");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace("[$TABLE_NAME]", "ST_ALTERNATE_HIST_ALLOCATION");
        } else {
            query = query.replace("[$TABLE_NAME]", "ST_DISC_ALTERNATE_HIST_ALLOCATION");
        }
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$START_DATE]", startDate.toString());
        query = query.replace("[$END_DATE]", endDate.toString());
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        int count = 0;
        if (list != null && !list.isEmpty()) {
            count = (int) list.get(0);
        }
        return count;
    }

    public boolean validateAllocation(final String screenName, final SessionDTO session, final List allocatedPeriods) throws Exception{

        String fromPeriod = session.getAltFromPeriod();
        String endPeriod = session.getAltToPeriod();
        String startFrom;
        String yearFrom;
        String startTo;
        String yearTo;
        String freq;
        
        int yearStartIndex;
        int yearEndIndex;        
        int frequencyStartIndex;
        int frequncyEndIndex;
        
        boolean isMonthlyFrequency = false;
        
        if (fromPeriod.trim().length() <= 4) {
            startFrom = fromPeriod.trim();
            yearFrom = fromPeriod.trim();
            startTo = endPeriod.trim();
            yearTo = endPeriod.trim();
            freq = "A";
            
            yearStartIndex = 0;
            yearEndIndex = 4;
            frequencyStartIndex = 0;
            frequncyEndIndex = 4;
            
        } else if (fromPeriod.trim().length() > 6) {
            
            List<String> months = Arrays.asList(dateFormatSymbols.getShortMonths());
            startFrom = (months.indexOf(StringUtils.capitalize(fromPeriod.substring(0, 3))) + 1) + StringUtils.EMPTY;
            yearFrom = fromPeriod.substring(3, 7);
            startTo = (months.indexOf(StringUtils.capitalize(endPeriod.substring(0, 3))) + 1) + StringUtils.EMPTY;
            yearTo = endPeriod.substring(3, 7);
            freq = "M";
            isMonthlyFrequency = true;
            yearStartIndex = 3;
            yearEndIndex = 7;
            frequencyStartIndex = 0;
            frequncyEndIndex = 3;
            
        } else {
            startFrom = fromPeriod.substring(1, 2);
            yearFrom = fromPeriod.substring(2, 6);
            startTo = endPeriod.substring(1, 2);
            yearTo = endPeriod.substring(2, 6);
            freq = StringUtils.capitalize(fromPeriod.substring(0, 1));
            
            yearStartIndex = 2;
            yearEndIndex = 6;
            frequencyStartIndex = 1;
            frequncyEndIndex = 2;
        }

        List<String> months = Arrays.asList(dateFormatSymbols.getShortMonths());

        String selectedPeriods = StringUtils.EMPTY;
        for (Object object : allocatedPeriods) {           
            String frequency = (StringUtils.EMPTY + object).substring(frequencyStartIndex, frequncyEndIndex);
            String year = (StringUtils.EMPTY + object).substring(yearStartIndex, yearEndIndex);
            frequency = isMonthlyFrequency ? (months.indexOf(StringUtils.capitalize(frequency)) + 1) + StringUtils.EMPTY : frequency;
            selectedPeriods += "'" + frequency + " " + year + "',";
        }
        selectedPeriods = selectedPeriods.substring(0, selectedPeriods.length() - 1);
              
        boolean isNotAllocated = true;
        String query = SQlUtil.getQuery("allocation-check");
        query = query.replace("[$FREQUENCY]", freq);
        query = query.replace("[$START_PERIOD]", startFrom);
        query = query.replace("[$START_YEAR]", yearFrom);
        query = query.replace("[$END_PERIOD]", startTo);
        query = query.replace("[$END_YEAR]", yearTo);
        query = query.replace("[$TABLE_NAME]", screenName.equals(TabNameUtil.SALES_PROJECTION) ? "ST_ALTERNATE_HIST_ALLOCATION" : "ST_DISC_ALTERNATE_HIST_ALLOCATION");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$ALLOCATED_PERIODS]", selectedPeriods);
        
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        
        if (list == null || list.isEmpty()) {
            isNotAllocated = false;
        }
        return isNotAllocated;
    }

    public void addToQueue(SessionDTO session, java.sql.Date startDate, java.sql.Date endDate) {

        String query = SQlUtil.getQuery("Add_To_Queue_Update");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace("[$TABLE_NAME]", "ST_ALTERNATE_HIST_ALLOCATION");
        } else {
            query = query.replace("[$TABLE_NAME]", "ST_DISC_ALTERNATE_HIST_ALLOCATION");
        }
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$START_DATE]", startDate.toString());
        query = query.replace("[$END_DATE]", endDate.toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

public void check_selected_allocationTab(AlternateHistoryDTO dto, SessionDTO session,
            int checkOrUncheck, java.sql.Date startDate, java.sql.Date endDate) {
        String query = SQlUtil.getQuery("Selected_Line_Level_Check");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace("[$TABLE_NAME]", "ST_ALTERNATE_HIST_ALLOCATION");
        } else {
            query = query.replace("[$TABLE_NAME]", "ST_DISC_ALTERNATE_HIST_ALLOCATION");
        }
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$START_DATE]", startDate.toString());
        query = query.replace("[$END_DATE]", endDate.toString());
        query = query.replace("[$PROJECTION_DETAILS_SID]", String.valueOf(dto.getProjDetailSid()));
        query = query.replace("[$SELECTED_CHECKBOX]", String.valueOf(checkOrUncheck));
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void checkAll_selected_allocationTab(SessionDTO session, int checkOrUncheck, java.sql.Date startDate, java.sql.Date endDate) {
        String query = SQlUtil.getQuery("Selected_Check_All");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace("[$TABLE_NAME]", "ST_ALTERNATE_HIST_ALLOCATION");
        } else {
            query = query.replace("[$TABLE_NAME]", "ST_DISC_ALTERNATE_HIST_ALLOCATION");
        }
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$START_DATE]", startDate.toString());
        query = query.replace("[$END_DATE]", endDate.toString());
        query = query.replace("[$SELECTED_CHECKBOX] ", String.valueOf(checkOrUncheck));
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public int count_selected_allocationTab(SessionDTO session, java.sql.Date startDate, java.sql.Date endDate) {
        String query = SQlUtil.getQuery("Count_Selected_Table");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace("[$TABLE_NAME]", "ST_ALTERNATE_HIST_ALLOCATION");
        } else {
            query = query.replace("[$TABLE_NAME]", "ST_DISC_ALTERNATE_HIST_ALLOCATION");
        }
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$START_DATE]", startDate.toString());
        query = query.replace("[$END_DATE]", endDate.toString());
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return (int) list.get(0);
    }

    public void remove_selected_allocationTab(SessionDTO session, java.sql.Date startDate, java.sql.Date endDate) {
        String query = SQlUtil.getQuery("Selected_Remove");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace("[$TABLE_NAME]", "ST_ALTERNATE_HIST_ALLOCATION");
        } else {
            query = query.replace("[$TABLE_NAME]", "ST_DISC_ALTERNATE_HIST_ALLOCATION");
        }
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        query = query.replace("[$START_DATE]", startDate.toString());
        query = query.replace("[$END_DATE]", endDate.toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void removeAll_selected_allocationTab(SessionDTO session) {
        String query = SQlUtil.getQuery("Selected_Remove_All");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace("[$TABLE_NAME]", "ST_ALTERNATE_HIST_ALLOCATION");
        } else {
            query = query.replace("[$TABLE_NAME]", "ST_DISC_ALTERNATE_HIST_ALLOCATION");
        }
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }
  
    public void resetTablesOnAllocation(SessionDTO session) {
        String query = SQlUtil.getQuery(TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName()) ? "reset-sales-allocation-table-on-allocation" : "reset-discount-allocation-table-on-allocation");
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        System.out.println("query --" + query);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }
    
    public int getCount_allocationTab(SessionDTO session, boolean addToQueue, Set<Container.Filter> filters) {
        String query = SQlUtil.getQuery("Get_Count_Allocation_Tables");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace("[$TABLE_NAME]", "ST_ALTERNATE_HIST_ALLOCATION");
        } else {
            query = query.replace("[$TABLE_NAME]", "ST_DISC_ALTERNATE_HIST_ALLOCATION");
        }
        String filter = getFilterQuery(filters);
        if (filter.length() > 0) {
            query = query.replace("[$FILTER_JOIN]", SQlUtil.getQuery("Get_Fileter_Join_allocationTab"));
            query = query.replace("[$FILTER_CONDITION]", filter);
        } else {
            query = query.replace("[$FILTER_JOIN]", StringUtils.EMPTY);
            query = query.replace("[$FILTER_CONDITION]", StringUtils.EMPTY);
        }
        query = query.replace("[$USER_ID]", session.getUserId());
        query = query.replace("[$SESSION_ID]", session.getSessionId());
        if (addToQueue) {
            query = query.replace("[$SELECTED_TABLE_CHECK]", " AND CHECK_RECORD = 1 ");
        } else {
            query = query.replace("[$SELECTED_TABLE_CHECK]", StringUtils.EMPTY);
        }
        List result = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return (result == null || result.isEmpty()) ? 0 : (int) result.get(0);
    }


    private String getFilterQuery(Set<Container.Filter> filters) {
        Map<String, String> parameters = new HashMap();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString() + "%";
                    parameters.put("filter~" + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        StringBuilder filter = new StringBuilder();

        if (parameters.containsKey("filter~contractNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractNo")))) {
            filter.append(" AND CO.CONTRACT_NO like '").append(parameters.get("filter~contractNo")).append("'");
        }

        if (parameters.containsKey("filter~contractName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~contractName")))) {
            filter.append(" AND CO.CONTRACT_NAME like '").append(parameters.get("filter~contractName")).append("'");
        }

        if (parameters.containsKey("filter~customerNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerNo")))) {
            filter.append(" AND CM.COMPANY_NO like '").append(parameters.get("filter~customerNo")).append("'");
        }

        if (parameters.containsKey("filter~customerName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~customerName")))) {
            filter.append(" AND CM.COMPANY_NAME like '").append(parameters.get("filter~customerName")).append("'");
        }

        if (parameters.containsKey("filter~itemNo") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemNo")))) {
            filter.append(" AND IM.ITEM_NO like '").append(parameters.get("filter~itemNo")).append("'");
        }

        if (parameters.containsKey("filter~itemName") && !Constant.NULL.equals(String.valueOf(parameters.get("filter~itemName")))) {
            filter.append(" AND IM.ITEM_NAME like '").append(parameters.get("filter~itemName")).append("'");
        }
        return filter.toString();
    }
    
    
    /**
     * 
     * @param propertyid
     * @param projDetSid
     * @param incDec
     * @param oldNumber
     * @param frequency
     * @param session 
     */
    public void updateAllocationOnEdit(String propertyid, final int projDetSid, Double incDec, Double oldNumber, final String frequency, final SessionDTO session) {

        String updateQuery;
                
        if ("Sales Projection".equalsIgnoreCase(session.getForecastName().trim())) {
            if (propertyid.contains("Units")) {
                updateQuery = SQlUtil.getQuery("update-allocation-unit-amount");
                updateQuery = updateQuery.replace("[$NEW_VALUE]", StringUtils.EMPTY + incDec);
            } else {
                updateQuery = SQlUtil.getQuery("update-allocation-percent");
                updateQuery = updateQuery.replace("[$ALLOCATION_PERCENT]", StringUtils.EMPTY + incDec);
            }
            if (propertyid.contains("Proj")) {
                updateQuery = updateQuery.replace("[$TOTAL_COLUMN]", "TOTAL_PROJECTION_UNITS");
                updateQuery = updateQuery.replace("[$UPDATE_COLUMN]", "PROJECTION_UNITS");
                updateQuery = updateQuery.replace("[$ALLOCATION_PERCENT_COLUMN]", "PROJECTION_ALLOCATION_PERCENT");
            } else {
                updateQuery = updateQuery.replace("[$TOTAL_COLUMN]", "TOTAL_ACTUAL_UNITS");
                updateQuery = updateQuery.replace("[$UPDATE_COLUMN]", "ACTUAL_UNITS");
                updateQuery = updateQuery.replace("[$ALLOCATION_PERCENT_COLUMN]", "ACTUAL_ALLOCATION_PERCENT");
            }
            updateQuery = updateQuery.replace("[$TABLE_NAME]", "ST_ALTERNATE_HIST_ALLOCATION");

        } else {
            if (propertyid.contains("Payments")) {
                updateQuery = SQlUtil.getQuery("update-allocation-unit-amount");
                updateQuery = updateQuery.replace("[$NEW_VALUE]", StringUtils.EMPTY + incDec);
            } else {
                updateQuery = SQlUtil.getQuery("update-allocation-percent");
                updateQuery = updateQuery.replace("[$ALLOCATION_PERCENT]", StringUtils.EMPTY + incDec);
            }
            if (propertyid.contains("Proj")) {
                updateQuery = updateQuery.replace("[$TOTAL_COLUMN]", "TOTAL_PROJECTION_AMOUNT");
                updateQuery = updateQuery.replace("[$UPDATE_COLUMN]", "PROJECTION_AMOUNT");
                updateQuery = updateQuery.replace("[$ALLOCATION_PERCENT_COLUMN]", "PROJECTION_ALLOCATION_PERCENT");
            } else {
                updateQuery = updateQuery.replace("[$TOTAL_COLUMN]", "TOTAL_ACTUAL_AMOUNT");
                updateQuery = updateQuery.replace("[$UPDATE_COLUMN]", "ACTUAL_AMOUNT");
                updateQuery = updateQuery.replace("[$ALLOCATION_PERCENT_COLUMN]", "ACTUAL_ALLOCATION_PERCENT");
            }
            updateQuery = updateQuery.replace("[$TABLE_NAME]", "ST_DISC_ALTERNATE_HIST_ALLOCATION");

        }

        updateQuery = updateQuery.replace("[$PROJECTION_DETAILS_SID]", StringUtils.EMPTY + projDetSid);
        updateQuery = updateQuery.replace("[$SESSION_ID]", StringUtils.EMPTY + session.getSessionId());

        String temp = StringUtils.EMPTY;

        if (propertyid.contains(Constant.ACTALLOCATION)) {
            temp = propertyid.replace(Constant.ACTALLOCATION, StringUtils.EMPTY);
        } else if (propertyid.contains(Constant.PROJALLOCATION)) {
            temp = propertyid.replace(Constant.PROJALLOCATION, StringUtils.EMPTY);
        } else if (propertyid.contains(LabelConstants.PROJECTED_UNITS.getConstant())) {
            temp = propertyid.replace(LabelConstants.PROJECTED_UNITS.getConstant(), StringUtils.EMPTY);
        } else if (propertyid.contains(LabelConstants.ACTUAL_UNITS.getConstant())) {
            temp = propertyid.replace(LabelConstants.ACTUAL_UNITS.getConstant(), StringUtils.EMPTY);
        } else if (propertyid.contains(ACTUAL_PAYMENTS.getConstant())) {
            temp = propertyid.replace(ACTUAL_PAYMENTS.getConstant(), StringUtils.EMPTY);
        } else if (propertyid.contains(PROJECTED_PAYMENTS.getConstant())) {
            temp = propertyid.replace(PROJECTED_PAYMENTS.getConstant(), StringUtils.EMPTY);
        }
        
        if (Constant.MONTHLY.equals(frequency)) {
            String periodVar = String.valueOf(CommonUtils.getMonthNumber(temp.substring(0, temp.length() - 4)));
            updateQuery = updateQuery.replace("[$FREQUENCY_COLUMN]", "\"MONTH\"");
            updateQuery = updateQuery.replace("[$FREQUENCY]", periodVar);
        } else if (Constant.SEMI_ANNUALLY.equals(frequency)) {
            String periodVar = temp.substring(1, temp.length() - 4);
            updateQuery = updateQuery.replace("[$FREQUENCY_COLUMN]", "SEMI_ANNUAL");
            updateQuery = updateQuery.replace("[$FREQUENCY]", periodVar);
        } else if ("Quarterly".equals(frequency)) {
            String periodVar = temp.substring(1, temp.length() - 4);
            updateQuery = updateQuery.replace("[$FREQUENCY_COLUMN]", "QUARTER");
            updateQuery = updateQuery.replace("[$FREQUENCY]", periodVar);
        } else {
            updateQuery = updateQuery.replace("AND PER.[$FREQUENCY_COLUMN] = [$FREQUENCY]", StringUtils.EMPTY);
        }
        String year = temp.substring(temp.length() - 4, temp.length());
        updateQuery = updateQuery.replace("[$YEAR]", year);
                
        executeUpdate(updateQuery);

    }
    
}
