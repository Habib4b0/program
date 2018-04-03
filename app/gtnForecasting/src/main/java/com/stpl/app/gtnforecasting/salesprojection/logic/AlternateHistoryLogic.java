/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.logic;

import com.stpl.app.gtnforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import static com.stpl.app.gtnforecasting.nationalassumptions.logic.DataSelectionLogic.convertNullToEmpty;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.FrequencyConstants.ANNUALLY;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getMonthForInt;
import com.stpl.app.gtnforecasting.utils.TabNameUtil;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.app.utils.Constants.LabelConstants;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import com.stpl.app.utils.QueryUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nandhakumar
 */
public class AlternateHistoryLogic {

    protected QueryUtils queryUtils = new QueryUtils();
    protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
    protected final DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
    protected String ACTUAL_SALES = "actualSales";
    protected String ACTUAL_UNITS = "actualUnits";
    protected String PROJECTED_SALES = "projectionSales";
    protected String PROJECTED_UNITS = "projectionUnits";
    protected String ACTUAL_PAYMENT = "actualPayments";

    public static final String PROJECTION_DETAILS_SID = "[$PROJECTION_DETAILS_SID]";
    public static final String END_DATE = "[$END_DATE]";
    public static final String START_DATE = "[$START_DATE]";
    public static final String TABLE_NAME = "[$TABLE_NAME]";

    protected String PROJECTED_PAYMENT = "projectionPayments";
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(AlternateHistoryLogic.class);

    public int companySearchCount(AlternateHistoryDTO altHistoryDTO,
            Set<Container.Filter> filters, SessionDTO session) {

        Map<String, Object> parameters = new HashMap<>();
        LOGGER.debug("--customer No-----= {}" , altHistoryDTO.getCustomerNo());
        if (isValidCriteria(altHistoryDTO.getContractHolder())) {
            String contractHolder = altHistoryDTO.getContractHolder();
            contractHolder = contractHolder.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.CONTRACT_HOLDER, contractHolder);
        }

        if (isValidCriteria(altHistoryDTO.getContractNo())) {
            String contractNo = altHistoryDTO.getContractNo();
            contractNo = contractNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.CONTRACT_NO1, contractNo);
        }

        if (isValidCriteria(altHistoryDTO.getCustomerNo())) {
            String customerNo = altHistoryDTO.getCustomerNo();
            customerNo = customerNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.CUSTOMER_NO, customerNo);
        }
        if (isValidCriteria(altHistoryDTO.getMarketType())) {
            String marketType = altHistoryDTO.getMarketType();
            marketType = marketType.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.MARKET_TYPE, marketType);
        }

        if (isValidCriteria(altHistoryDTO.getContractName())) {
            String contractName = altHistoryDTO.getContractName();
            contractName = contractName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.CONTRACT_NAME, contractName);
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
                    String filterString = stringFilter.getFilterString() + Constant.PERCENT;
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        int count = queryUtils.companyCount(parameters, session);
        return count;
    }

    public List<AlternateHistoryDTO> searchCompany(AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, int start, int offset, SessionDTO session) {

        Map<String, Object> parameters = new HashMap<>();
        List list = Collections.EMPTY_LIST;
        try {
            if (isValidCriteria(altHistoryDTO.getContractHolder())) {
                String contractHolder = altHistoryDTO.getContractHolder();
                contractHolder = contractHolder.replace(CommonUtils.CHAR_ASTERISK, '%');

                parameters.put(Constant.CONTRACT_HOLDER, contractHolder);
            }

            if (isValidCriteria(altHistoryDTO.getContractNo())) {
                String contractNo = altHistoryDTO.getContractNo();
                contractNo = contractNo.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put(Constant.CONTRACT_NO1, contractNo);
            }

            if (isValidCriteria(altHistoryDTO.getCustomerNo())) {
                String customerNo = altHistoryDTO.getCustomerNo();
                customerNo = customerNo.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put(Constant.CUSTOMER_NO, customerNo);
            }
            if (isValidCriteria(altHistoryDTO.getMarketType())) {
                String marketType = altHistoryDTO.getMarketType();
                marketType = marketType.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put(Constant.MARKET_TYPE, marketType);
            }

            if (isValidCriteria(altHistoryDTO.getContractName())) {
                String contractName = altHistoryDTO.getContractName();
                contractName = contractName.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put(Constant.CONTRACT_NAME, contractName);
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
                        String filterString = stringFilter.getFilterString() + Constant.PERCENT;
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                    }
                }
            }

            String query = queryUtils.companyValues(parameters, start, offset, session);
            list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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

        List<AlternateHistoryDTO> companyList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                AlternateHistoryDTO dto = new AlternateHistoryDTO();
                Object[] obj = (Object[]) list.get(i);
                dto.setContractHolder(convertNullToEmpty(String.valueOf(obj[0])));
                dto.setContractNo(convertNullToEmpty(String.valueOf(obj[1])));
                dto.setContractName(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWO])));
                dto.setMarketType(convertNullToEmpty(String.valueOf(obj[NumericConstants.THREE])));
                dto.setCustomerNo(convertNullToEmpty(String.valueOf(obj[NumericConstants.FOUR])));
                dto.setCustomerName(convertNullToEmpty(String.valueOf(obj[NumericConstants.FIVE])));
                dto.setContractMasterSid(Integer.valueOf(String.valueOf(obj[NumericConstants.SIX])));
                dto.setCompanymasterSid(Integer.valueOf(String.valueOf(obj[NumericConstants.SEVEN])));
                dto.setCheck(obj[NumericConstants.EIGHT] == null ? false : (boolean) obj[NumericConstants.EIGHT]);
                companyList.add(dto);
            }
        }

        return companyList;
    }

    public int itemsSearchCount(AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, SessionDTO session) {
        Map<String, Object> parameters = new HashMap<>();
        if (isValidCriteria(altHistoryDTO.getItemNo())) {
            String itemNo = altHistoryDTO.getItemNo();
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.ITEM_NO, itemNo);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getItemName())) {
            String itemName = altHistoryDTO.getItemName();
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.ITEM_NAME_SMALL_PROPERY, itemName);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getBusinessUnitNo())) {
            String businessUnitNo = altHistoryDTO.getBusinessUnitNo();
            businessUnitNo = businessUnitNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.BUSINESS_UNIT_NO_PROPERTY, businessUnitNo);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getBusinessUnitName())) {
            String businessUnitName = altHistoryDTO.getBusinessUnitName();
            businessUnitName = businessUnitName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.BUSINESS_UNIT_NAME_PROPERTY, businessUnitName);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getTheraputicClass())) {
            String theraputicClass = altHistoryDTO.getTheraputicClass();
            theraputicClass = theraputicClass.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.THERAPUTIC_CLASS_PROPERTY, theraputicClass);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getBrand())) {
            String brand = altHistoryDTO.getBrand();
            parameters.put(Constant.BRAND, brand);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifierType())) {
            String itemIdentifierType = altHistoryDTO.getItemIdentifierType();
            parameters.put(Constant.ITEM_IDENTIFIER_TYPE, itemIdentifierType);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifier())) {
            String itemIdentifier = altHistoryDTO.getItemIdentifier();
            itemIdentifier = itemIdentifier.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.ITEM_IDENTIFIER, itemIdentifier);
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

    public List<AlternateHistoryDTO> searchItems(AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, int start, int offset, SessionDTO session) {

        Map<String, Object> parameters = new HashMap<>();

        boolean isIdentifierSelected = false;
        if (isValidCriteria(altHistoryDTO.getItemNo())) {
            String itemNo = altHistoryDTO.getItemNo();
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.ITEM_NO, itemNo);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getItemName())) {
            String itemName = altHistoryDTO.getItemName();
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.ITEM_NAME_SMALL_PROPERY, itemName);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getBusinessUnitNo())) {
            String businessUnitNo = altHistoryDTO.getBusinessUnitNo();
            businessUnitNo = businessUnitNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.BUSINESS_UNIT_NO_PROPERTY, businessUnitNo);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getBusinessUnitName())) {
            String businessUnitName = altHistoryDTO.getBusinessUnitName();
            businessUnitName = businessUnitName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.BUSINESS_UNIT_NAME_PROPERTY, businessUnitName);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getTheraputicClass())) {
            String theraputicClass = altHistoryDTO.getTheraputicClass();
            theraputicClass = theraputicClass.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.THERAPUTIC_CLASS_PROPERTY, theraputicClass);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getBrand())) {
            String brand = altHistoryDTO.getBrand();
            parameters.put(Constant.BRAND, brand);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifierType())) {
            String itemIdentifierType = altHistoryDTO.getItemIdentifierType();
            parameters.put(Constant.ITEM_IDENTIFIER_TYPE, itemIdentifierType);
            isIdentifierSelected = true;
        }
        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifier())) {
            String itemIdentifier = altHistoryDTO.getItemIdentifier();
            itemIdentifier = itemIdentifier.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.ITEM_IDENTIFIER, itemIdentifier);
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

        List itemsList = queryUtils.itemsSearch(parameters, start, offset, session);
        return getCustomizedItemsDTO(itemsList, isIdentifierSelected);
    }

    private List<AlternateHistoryDTO> getCustomizedItemsDTO(List list, final boolean isIdentifierSelected) {

        List<AlternateHistoryDTO> itemsList = new ArrayList<>();
        try {
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    AlternateHistoryDTO dto = new AlternateHistoryDTO();
                    Object[] obj = (Object[]) list.get(i);
                    dto.setBusinessUnitNo(convertNullToEmpty(String.valueOf(obj[0])));
                    dto.setBusinessUnitName(convertNullToEmpty(String.valueOf(obj[1])));
                    dto.setTheraputicClass(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWO])));
                    dto.setBrand(convertNullToEmpty(String.valueOf(obj[NumericConstants.THREE])));
                    dto.setItemNo(convertNullToEmpty(String.valueOf(obj[NumericConstants.FOUR])));
                    dto.setItemName(convertNullToEmpty(String.valueOf(obj[NumericConstants.FIVE])));
                    if (obj[NumericConstants.SIX] != null) {
                        dto.setItemMasterSid(Integer.valueOf(String.valueOf(obj[NumericConstants.SIX])));
                    }
                    if (isIdentifierSelected) {
                        dto.setItemIdentifierType(String.valueOf(obj[NumericConstants.EIGHT]));
                        dto.setItemIdentifier(String.valueOf(obj[NumericConstants.SEVEN]));
                        dto.setCheck(obj[NumericConstants.NINE] == null ? false : (Boolean) obj[NumericConstants.NINE]);
                    } else {
                        dto.setCheck(obj[NumericConstants.SEVEN] == null ? false : (Boolean) obj[NumericConstants.SEVEN]);
                    }
                    itemsList.add(dto);
                }
                list.clear();
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }

        return itemsList;
    }

    public int getCheckedItemsCount(AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, SessionDTO sessionDto) {
        List list;

        Map<String, Object> parameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                }
            }

        }

        String query = StringUtils.EMPTY;

        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifierType()) && !Constant.NULL.equals(altHistoryDTO.getItemIdentifier())) {
            query += SQlUtil.getQuery("selected-items-count-with-identifier");
        } else {
            query += SQlUtil.getQuery("selected-items-count");
        }

        int count = 0;

        //For Filters
        if (parameters.containsKey(Constant.FILTERBUSINESS_UNIT_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERBUSINESS_UNIT_NAME)))) {
            query = query + " AND CM.COMPANY_NAME  like '" + String.valueOf(parameters.get(Constant.FILTERBUSINESS_UNIT_NAME)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERITEM_IDENTIFIER_TYPE) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_IDENTIFIER_TYPE)))) {
            query = query + " AND IQ.ITEM_QUALIFIER_NAME like '" + String.valueOf(parameters.get(Constant.FILTERITEM_IDENTIFIER_TYPE)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERBUSINESS_UNIT_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERBUSINESS_UNIT_NO)))) {
            query = query + "  AND CM.COMPANY_NO  like '" + String.valueOf(parameters.get(Constant.FILTERBUSINESS_UNIT_NO)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERITEM_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_NAME)))) {
            query = query + " AND IM.ITEM_NAME like '" + String.valueOf(parameters.get(Constant.FILTERITEM_NAME)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERTHERAPUTIC_CLASS) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERTHERAPUTIC_CLASS)))) {
            query = query + " AND HT1.DESCRIPTION like '" + String.valueOf(parameters.get(Constant.FILTERTHERAPUTIC_CLASS)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERITEM_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_NO)))) {
            query = query + " AND IM.ITEM_NO like '" + String.valueOf(parameters.get(Constant.FILTERITEM_NO)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERBRAND) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERBRAND)))) {
            query = query + " AND BM.BRAND_NAME like '" + String.valueOf(parameters.get(Constant.FILTERBRAND)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERITEM_IDENTIFIER) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_IDENTIFIER)))) {
            query = query + " AND II.ITEM_IDENTIFIER_VALUE like '" + String.valueOf(parameters.get(Constant.FILTERITEM_IDENTIFIER)) + "'";
        }
        query = query + ") TMP_COUNT;";

        list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDto.getCurrentTableNames()));

        if (list != null && !list.isEmpty()) {
            Object obj = list.get(0);
            String countValue = String.valueOf(obj);
            count = Integer.parseInt(countValue);

        }
        return count;
    }

    public List<AlternateHistoryDTO> getCheckedItemsFromTemptable(AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, int start, int offset, SessionDTO sessionDto) {

        List list;
        Map<String, Object> parameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);

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

        if (parameters.containsKey(Constant.FILTERBUSINESS_UNIT_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERBUSINESS_UNIT_NAME)))) {
            query = query + " AND CM.COMPANY_NAME like '" + String.valueOf(parameters.get(Constant.FILTERBUSINESS_UNIT_NAME)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERITEM_IDENTIFIER_TYPE) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_IDENTIFIER_TYPE)))) {
            query = query + " AND IQ.ITEM_QUALIFIER_NAME like '" + String.valueOf(parameters.get(Constant.FILTERITEM_IDENTIFIER_TYPE)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERBUSINESS_UNIT_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERBUSINESS_UNIT_NO)))) {
            query = query + "  AND CM.COMPANY_NO like '" + String.valueOf(parameters.get(Constant.FILTERBUSINESS_UNIT_NO)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERITEM_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_NAME)))) {
            query = query + " AND IM.ITEM_NAME like '" + String.valueOf(parameters.get(Constant.FILTERITEM_NAME)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERTHERAPUTIC_CLASS) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERTHERAPUTIC_CLASS)))) {
            query = query + " AND HT1.DESCRIPTION like '" + String.valueOf(parameters.get(Constant.FILTERTHERAPUTIC_CLASS)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERITEM_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_NO)))) {
            query = query + "  AND IM.ITEM_NO like '" + String.valueOf(parameters.get(Constant.FILTERITEM_NO)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERBRAND) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERBRAND)))) {
            query = query + " AND BM.BRAND_NAME like '" + String.valueOf(parameters.get(Constant.FILTERBRAND)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERITEM_IDENTIFIER) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_IDENTIFIER)))) {
            query = query + " AND II.ITEM_IDENTIFIER_VALUE like '" + String.valueOf(parameters.get(Constant.FILTERITEM_IDENTIFIER)) + "'";
        }
        query += " ORDER BY ITEM_NAME OFFSET " + start + "  ROWS FETCH NEXT " + offset + Constant.ROWS_ONLY_SPACE;

        list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDto.getCurrentTableNames()));

        return getCustomizedItemsDTOFromTemp(list, isIdentifierSelected);

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
                dto.setTheraputicClass(convertNullToEmpty(String.valueOf(obj[NumericConstants.TWO])));
                dto.setBrand(convertNullToEmpty(String.valueOf(obj[NumericConstants.THREE])));
                dto.setItemNo(convertNullToEmpty(String.valueOf(obj[NumericConstants.FOUR])));
                dto.setItemName(convertNullToEmpty(String.valueOf(obj[NumericConstants.FIVE])));
                if (obj[NumericConstants.SIX] != null) {
                    dto.setItemMasterSid(Integer.valueOf(String.valueOf(obj[NumericConstants.SIX])));
                }
                if (isIdentifierSelected) {
                    dto.setItemIdentifierType(String.valueOf(obj[NumericConstants.EIGHT]));
                    dto.setItemIdentifier(String.valueOf(obj[NumericConstants.SEVEN]));
                    dto.setCheck(obj[NumericConstants.NINE] == null ? false : (Boolean) obj[NumericConstants.NINE]);
                } else {
                    dto.setCheck(obj[NumericConstants.SEVEN] == null ? false : (Boolean) obj[NumericConstants.SEVEN]);
                }
                selectedItemsList.add(dto);
            }
            list.clear();
        }
        return selectedItemsList;
    }

    public List<AlternateHistoryDTO> alternateSelectionList(SessionDTO session, AlternateHistoryDTO dto, Set<Container.Filter> filters, final int start, final int offset, final boolean isExcelExport, final Set checkedCCPSet) {
        List<AlternateHistoryDTO> actualsList = new ArrayList<>();
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
            AlternateHistoryDTO altDTO = new AlternateHistoryDTO();
            String commonColumn;
            Map<String, Object> parameters = new HashMap<>();
            if (filters != null) {
                for (Container.Filter filter : filters) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                    }
                }
            }
            String companyFilter = StringUtils.EMPTY;
            String itemFilter = StringUtils.EMPTY;
            String contractFilter = StringUtils.EMPTY;

            if (parameters.containsKey(Constant.FILTERCONTRACT_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCONTRACT_NO)))) {
                contractFilter = contractFilter + " AND CON.CONTRACT_NO like '" + String.valueOf(parameters.get(Constant.FILTERCONTRACT_NO)) + "'";
            }

            if (parameters.containsKey(Constant.FILTERCONTRACT_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCONTRACT_NAME)))) {
                contractFilter = contractFilter + " AND CON.CONTRACT_NAME like '" + String.valueOf(parameters.get(Constant.FILTERCONTRACT_NAME)) + "'";
            }

            if (parameters.containsKey(Constant.FILTERCUSTOMER_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NO)))) {
                companyFilter = companyFilter + " AND CM.COMPANY_NO  like '" + String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NO)) + "'";
            }

            if (parameters.containsKey(Constant.FILTERCUSTOMER_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NAME)))) {
                companyFilter = companyFilter + " AND CM.COMPANY_NAME like  '" + String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NAME)) + "'";
            }

            if (parameters.containsKey(Constant.FILTERITEM_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_NO)))) {
                itemFilter = itemFilter + " AND IM.ITEM_NO like  '" + String.valueOf(parameters.get(Constant.FILTERITEM_NO)) + "'";
            }

            if (parameters.containsKey(Constant.FILTERITEM_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_NAME)))) {
                itemFilter = itemFilter + "  AND  IM.ITEM_NAME like '" + String.valueOf(parameters.get(Constant.FILTERITEM_NAME)) + "'";
            }

            String frequency;
            String filterFrequency = StringUtils.EMPTY;
            String innerFrequency;

            if (freq.equals(Constant.QUARTERLY)) {
                frequency = " U.QUARTER\n";
                filterFrequency = " ,QUARTER\n";
                innerFrequency = " QUARTER\n";
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
                innerFrequency = " QUARTER\n";
            } else if (freq.equals(Constant.MONTHLY)) {
                frequency = " U.MONTH\n";
                filterFrequency = " ,MONTH\n";
                innerFrequency = " MONTH\n";
            } else {
                frequency = " 1 AS FREQUENCY";
                innerFrequency = " 1 AS FREQUENCY\n";
            }

            String sql = SQlUtil.getQuery("alternate-history-selection");

            if (isExcelExport) {
                sql = sql.replace("ORDER BY CCP.CCP_DETAILS_SID OFFSET @?START_INDEX ROWS FETCH NEXT @?END_INDEX ROWS ONLY", StringUtils.EMPTY);
            }
            sql = sql.replace("@?START_DATE DATETIME", dto.getStartDate());
            sql = sql.replace("@?END_DATE DATETIME", dto.getEndDate());
            sql = sql.replace("@?SCREEN_NAME", session.getForecastName());
            sql = sql.replace("@COMPANY_FILTER", companyFilter);
            sql = sql.replace("@ITEM_FILTER", itemFilter);
            sql = sql.replace("@CONTRACT_FILTER", contractFilter);
            sql = sql.replace("@?FREQUENCY", frequency);
            sql = sql.replace("@?FILTER-FREQUENCY", filterFrequency);
            sql = sql.replace("@?INNER-FREQUENCY", innerFrequency);
            sql = sql.replace("@?START_INDEX", StringUtils.EMPTY + start);
            sql = sql.replace("@?END_INDEX", StringUtils.EMPTY + offset);
            List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(sql, session.getCurrentTableNames()));

            if (list != null && !list.isEmpty()) {
                int count = list.size();
                String currentccpId = StringUtils.EMPTY;
                for (int i = 0; i < count; i++) {
                    Object[] obj = (Object[]) list.get(i);
                    String ccpId = String.valueOf(obj[NumericConstants.EIGHT]);
                    if (ccpId.equals(currentccpId)) {
                        if (Constant.SALES_PROJECTION.equals(session.getForecastName())) {
                            if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                                commonColumn = getCommonColumn(freq, obj[NumericConstants.TWO], obj[NumericConstants.THREE]) + ACTUAL_SALES;
                                altDTO.addStringProperties(commonColumn, String.valueOf(obj[0]));
                                commonColumn = getCommonColumn(freq, obj[NumericConstants.TWO], obj[NumericConstants.THREE]) + ACTUAL_UNITS;
                                if (freq.equals(ANNUALLY.getConstant())) {
                                    altDTO.addStringProperties(commonColumn, String.valueOf(obj[NumericConstants.TEN]));
                                } else {
                                    altDTO.addStringProperties(commonColumn, String.valueOf(obj[NumericConstants.ELEVEN]));
                                }
                            }
                            if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                                commonColumn = getCommonColumn(freq, obj[NumericConstants.TWO], obj[NumericConstants.THREE]) + PROJECTED_SALES;

                                altDTO.addStringProperties(commonColumn, String.valueOf(obj[1]));
                                commonColumn = getCommonColumn(freq, obj[NumericConstants.TWO], obj[NumericConstants.THREE]) + PROJECTED_UNITS;
                                if (freq.equals(ANNUALLY.getConstant())) {
                                    altDTO.addStringProperties(commonColumn, String.valueOf(obj[NumericConstants.ELEVEN]));
                                } else {
                                    altDTO.addStringProperties(commonColumn, String.valueOf(obj[NumericConstants.TWELVE]));
                                }
                            }
                        } else if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {
                            if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                                commonColumn = getCommonColumn(freq, obj[NumericConstants.TWO], obj[NumericConstants.THREE]) + ACTUAL_PAYMENT;

                                altDTO.addStringProperties(commonColumn, String.valueOf(obj[0]));
                            }
                            if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                                commonColumn = getCommonColumn(freq, obj[NumericConstants.TWO], obj[NumericConstants.THREE]) + PROJECTED_PAYMENT;

                                altDTO.addStringProperties(commonColumn, String.valueOf(obj[1]));
                            }
                        }

                    } else {
                        if (i != 0) {
                            actualsList.add(altDTO);
                        }

                        currentccpId = ccpId;
                        altDTO = new AlternateHistoryDTO();
                        if (freq.equals(ANNUALLY.getConstant())) {
                            altDTO = new AlternateHistoryDTO();
                            altDTO.setContractName(String.valueOf(obj[NumericConstants.SIX]));
                            altDTO.setContractNo(String.valueOf(obj[NumericConstants.FIVE]));
                            altDTO.setCustomerName(String.valueOf(obj[NumericConstants.FOUR]));
                            altDTO.setCustomerNo(String.valueOf(obj[NumericConstants.THREE]));
                            altDTO.setItemNo(String.valueOf(obj[NumericConstants.EIGHT]));
                            altDTO.setItemName(String.valueOf(obj[NumericConstants.NINE]));
                            altDTO.setCcpDetailsId(String.valueOf(obj[NumericConstants.SEVEN]));
                        } else {
                            altDTO.setContractName(String.valueOf(obj[NumericConstants.SEVEN]));
                            altDTO.setContractNo(String.valueOf(obj[NumericConstants.SIX]));
                            altDTO.setCustomerName(String.valueOf(obj[NumericConstants.FIVE]));
                            altDTO.setCustomerNo(String.valueOf(obj[NumericConstants.FOUR]));
                            altDTO.setItemNo(String.valueOf(obj[NumericConstants.NINE]));
                            altDTO.setItemName(String.valueOf(obj[NumericConstants.TEN]));
                            altDTO.setCcpDetailsId(String.valueOf(obj[NumericConstants.EIGHT]));
                        }

                        altDTO.setCheck(checkedCCPSet.contains(altDTO.getCcpDetailsId()));

                        if (Constant.SALES_PROJECTION.equals(session.getForecastName())) {
                            if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                                commonColumn = getCommonColumn(freq, obj[NumericConstants.TWO], obj[NumericConstants.THREE]) + ACTUAL_SALES;

                                altDTO.addStringProperties(commonColumn, String.valueOf(obj[0]));
                                commonColumn = getCommonColumn(freq, obj[NumericConstants.TWO], obj[NumericConstants.THREE]) + ACTUAL_UNITS;

                                if (freq.equals(ANNUALLY.getConstant())) {
                                    altDTO.addStringProperties(commonColumn, String.valueOf(obj[NumericConstants.TEN]));
                                } else {
                                    altDTO.addStringProperties(commonColumn, String.valueOf(obj[NumericConstants.ELEVEN]));
                                }
                            }
                            if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                                commonColumn = getCommonColumn(freq, obj[NumericConstants.TWO], obj[NumericConstants.THREE]) + PROJECTED_SALES;

                                altDTO.addStringProperties(commonColumn, String.valueOf(obj[1]));
                                commonColumn = getCommonColumn(freq, obj[NumericConstants.TWO], obj[NumericConstants.THREE]) + PROJECTED_UNITS;
                                if (freq.equals(ANNUALLY.getConstant())) {
                                    altDTO.addStringProperties(commonColumn, String.valueOf(obj[NumericConstants.ELEVEN]));
                                } else {
                                    altDTO.addStringProperties(commonColumn, String.valueOf(obj[NumericConstants.TWELVE]));
                                }
                            }
                        } else if (TabNameUtil.DISCOUNT_PROJECTION.equals(session.getForecastName())) {
                            if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                                commonColumn = getCommonColumn(freq, obj[NumericConstants.TWO], obj[NumericConstants.THREE]) + ACTUAL_PAYMENT;

                                altDTO.addStringProperties(commonColumn, String.valueOf(obj[0]));
                            }
                            if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                                commonColumn = getCommonColumn(freq, obj[NumericConstants.TWO], obj[NumericConstants.THREE]) + PROJECTED_PAYMENT;

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
            column = StringUtils.lowerCase(getMonthForInt(Integer.parseInt(String.valueOf(obj1)) - 1) + obj2);
        }
        return column;
    }

    public List<AlternateHistoryDTO> getAlloc(AlternateHistoryDTO altHistoryDTO, SessionDTO session, boolean addToQueue, Set<Container.Filter> filters, int start, int offset, boolean iscount) {
        List<AlternateHistoryDTO> allocationList = new ArrayList<>();
        List<AlternateHistoryDTO> totalList = new ArrayList<>();
        AlternateHistoryDTO altDTO = new AlternateHistoryDTO();
        AlternateHistoryDTO totDTO = new AlternateHistoryDTO();
        String commonColumn;
        String commonPerColumn;
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
        String query;
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
                annualFreq = " I.\"YEAR\" as period1,I.\"YEAR\" as period2 ";
                break;
            default:
                break;

        }
        Map<String, Object> parameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        String companyFilter = StringUtils.EMPTY;
        String itemFilter = StringUtils.EMPTY;
        String contractFilter = StringUtils.EMPTY;

        if (parameters.containsKey(Constant.FILTERCONTRACT_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCONTRACT_NO)))) {
            contractFilter = contractFilter + " AND CN.CONTRACT_NO like '" + String.valueOf(parameters.get(Constant.FILTERCONTRACT_NO)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERCONTRACT_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCONTRACT_NAME)))) {
            contractFilter = contractFilter + " AND CN.CONTRACT_NAME like '" + String.valueOf(parameters.get(Constant.FILTERCONTRACT_NAME)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERCUSTOMER_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NO)))) {
            companyFilter = companyFilter + " AND  CM.COMPANY_NO like '" + String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NO)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERCUSTOMER_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NAME)))) {
            companyFilter = companyFilter + " AND CM.COMPANY_NAME like '" + String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NAME)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERITEM_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_NO)))) {
            itemFilter = itemFilter + " AND IM.ITEM_NO  like '" + String.valueOf(parameters.get(Constant.FILTERITEM_NO)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERITEM_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_NAME)))) {
            itemFilter = itemFilter + " AND IM.ITEM_NAME  like  '" + String.valueOf(parameters.get(Constant.FILTERITEM_NAME)) + "'";
        }

        String totalQuery;
        if (session.getForecastName().equals(Constant.SALES_PROJECTION)) {
            totalQuery = "select " + freqQuery + ",ISNULL(SUM(TOTAL_ACTUAL_UNITS),0) as ACTUAL_UNITS,ISNULL(SUM(TOTAL_PROJECTION_UNITS),0) as PROJECTION_UNITS\n";
            totalQuery += " from dbo.ST_ALTERNATE_HIST_ALLOCATION ST JOIN\n";
        } else {
            totalQuery = "select " + freqQuery + ",ISNULL(SUM(TOTAL_ACTUAL_AMOUNT),0) as ACTUAL_AMOUNT,ISNULL(SUM(TOTAL_PROJECTION_AMOUNT),0) as PROJECTION_AMOUNT\n";
            totalQuery += " from dbo.ST_DISC_ALTERNATE_HIST_ALLOCATION ST JOIN\n";
        }
        totalQuery += " \"PERIOD\" I ON ST.PERIOD_SID = I.PERIOD_SID  where "
                + "I.PERIOD_SID BETWEEN "
                + "(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\"  =  '" + fromYear + Constant.AND_MONTH_EQUAL + fromMonth + "')"
                + "AND"
                + "(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\"  = '" + toYear + Constant.AND_MONTH_EQUAL + toMonth + "')"
                + " group by ST.CCP_DETAILS_SID," + freqQuery;

        if (session.getForecastName().equals(Constant.SALES_PROJECTION)) {
            query = "select * from ( select ST.CCP_DETAILS_SID," + (Constants.ANNUALLY.equals(altHistoryDTO.getFrequency()) ? annualFreq : freqQuery) + ", CN.CONTRACT_MASTER_SID,CN.CONTRACT_NO,CN.CONTRACT_NAME,\n"
                    + " CM.COMPANY_NO, CM.COMPANY_NAME, IM.ITEM_NO,IM.ITEM_NAME,\n"
                    + " AVG(ST.ACTUAL_ALLOCATION_PERCENT) as ACTUAL_ALLOCATION_PERCENT,\n"
                    + "SUM(ST.ACTUAL_UNITS) as ACTUAL_UNITS,\n"
                    + "AVG(ST.PROJECTION_ALLOCATION_PERCENT) as PROJECTION_ALLOCATION_PERCENT,\n"
                    + "SUM(ST.PROJECTION_UNITS) as PROJECTION_UNITS,"
                    + (addToQueue ? " ST.SELECTED_CHECKBOX  " : " ST.AVAILABLE_CHECKBOX ")
                    + ", DENSE_RANK() OVER (\n"
                    + "   ORDER BY ST.CCP_DETAILS_SID\n"
                    + "    ) AS ROW_ID  from ST_ALTERNATE_HIST_ALLOCATION ST  \n";
        } else {
            query = "select * from ( select ST.CCP_DETAILS_SID," + (Constants.ANNUALLY.equals(altHistoryDTO.getFrequency()) ? annualFreq : freqQuery) + ",CN.CONTRACT_MASTER_SID,CN.CONTRACT_NO,CN.CONTRACT_NAME,\n"
                    + " CM.COMPANY_NO, CM.COMPANY_NAME, IM.ITEM_NO,IM.ITEM_NAME,\n"
                    + " AVG(ST.ACTUAL_ALLOCATION_PERCENT) as ACTUAL_ALLOCATION_PERCENT,\n"
                    + "SUM(ST.ACTUAL_AMOUNT) as ACTUAL_AMOUNT,\n"
                    + " AVG(ST.PROJECTION_ALLOCATION_PERCENT) as PROJECTION_ALLOCATION_PERCENT,\n"
                    + "SUM(ST.PROJECTION_AMOUNT) as PROJECTION_AMOUNT, "
                    + (addToQueue ? " ST.SELECTED_CHECKBOX " : " ST.AVAILABLE_CHECKBOX ")
                    + ", DENSE_RANK() OVER (\n"
                    + "   ORDER BY ST.CCP_DETAILS_SID\n"
                    + "    ) AS ROW_ID from ST_DISC_ALTERNATE_HIST_ALLOCATION ST  \n";

        }

        query = query + " JOIN \n"
                + " \"PERIOD\" I ON ST.PERIOD_SID = I.PERIOD_SID RIGHT  JOIN\n"
                + " CCP_DETAILS CCP ON ST.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID JOIN\n"
                + " CONTRACT_MASTER CN ON CCP.CONTRACT_MASTER_SID = CN.CONTRACT_MASTER_SID " + contractFilter + " JOIN\n"
                + " COMPANY_MASTER CM ON CCP.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID " + companyFilter + " JOIN\n"
                + " ITEM_MASTER IM ON CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID " + itemFilter + "  \n"
                + "where \n"
                + check
                + "I.PERIOD_SID BETWEEN "
                + "(SELECT PERIOD_SID FROM \"PERIOD\" where  \"YEAR\"  = '" + fromYear + Constant.AND_MONTH_EQUAL + fromMonth + "')"
                + "AND"
                + "(SELECT PERIOD_SID FROM \"PERIOD\" where \"YEAR\"  = '" + toYear + Constant.AND_MONTH_EQUAL + toMonth + "')"
                + "group by ST.CCP_DETAILS_SID," + freqQuery + ",CN.CONTRACT_MASTER_SID,CN.CONTRACT_NO,CN.CONTRACT_NAME,\n"
                + " CM.COMPANY_NO, CM.COMPANY_NAME, IM.ITEM_NO,IM.ITEM_NAME,"
                + (addToQueue ? " ST.SELECTED_CHECKBOX " : " ST.AVAILABLE_CHECKBOX  ") + " ) A ";
        if (!iscount) {
            if (!addToQueue) {
                if (start > 0) {
                    start = start - 1;
                }
                offset = offset - 1;
            }
            query = query + " WHERE ROW_ID >" + start + " AND ROW_ID <= " + (start + offset) + " ORDER BY A.CCP_DETAILS_SID ";
        }

        totalLevelList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(totalQuery, session.getCurrentTableNames()));
        list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        if ((!addToQueue) && (totalLevelList != null && !totalLevelList.isEmpty() && start == 0)) {
            int count = totalLevelList.size();
            for (int i = 0; i < count; i++) {
                Object[] obj = (Object[]) totalLevelList.get(i);

                String year = String.valueOf(obj[0]);
                String fre = String.valueOf(obj[1]);

                totDTO.setContractNo("Total Alternate History Baseline");
                totDTO.setCheckRecord(null);
                if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                    commonPerColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + Constant.ACTALLOCATION;
                    totDTO.addStringProperties(commonPerColumn, "0.00");
                    commonColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + (Constant.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName()) ? "Actual Units" : ACTUAL_PAYMENTS.getConstant());
                    totDTO.addStringProperties(commonColumn, String.valueOf(obj[NumericConstants.TWO]));
                }
                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                    commonPerColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + Constant.PROJALLOCATION;

                    totDTO.addStringProperties(commonPerColumn, "0.00");
                    commonColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + (Constant.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName()) ? "Projected Units" : PROJECTED_PAYMENTS.getConstant());

                    totDTO.addStringProperties(commonColumn, String.valueOf(obj[NumericConstants.THREE]));
                }
            }
            totalList.add(totDTO);
        }

        allocationList.addAll(totalList);
        if (list != null && !list.isEmpty()) {
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
                String fre = String.valueOf(obj[NumericConstants.TWO]);
                altDTO.setContractName(String.valueOf(obj[NumericConstants.FIVE]));
                altDTO.setContractNo(String.valueOf(obj[NumericConstants.FOUR]));
                altDTO.setCustomerName(String.valueOf(obj[NumericConstants.SEVEN]));
                altDTO.setCustomerNo(String.valueOf(obj[NumericConstants.SIX]));
                altDTO.setItemNo(String.valueOf(obj[NumericConstants.EIGHT]));
                altDTO.setItemName(String.valueOf(obj[NumericConstants.NINE]));

                if (projection.contains(Constant.BOTH) || projection.contains(ACTUALS.getConstant())) {
                    commonPerColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + Constant.ACTALLOCATION;

                    altDTO.addStringProperties(commonPerColumn, obj[NumericConstants.TEN] == null ? "0.00" : String.valueOf(obj[NumericConstants.TEN]));
                    commonColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + (Constant.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName()) ? "Actual Units" : ACTUAL_PAYMENTS.getConstant());

                    altDTO.addStringProperties(commonColumn, obj[NumericConstants.ELEVEN] == null ? "0.0" : String.valueOf(obj[NumericConstants.ELEVEN]));
                }
                if (projection.contains(Constant.BOTH) || projection.contains(PROJECTIONS.getConstant())) {
                    commonPerColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + Constant.PROJALLOCATION;

                    altDTO.addStringProperties(commonPerColumn, obj[NumericConstants.TWELVE] == null ? "0.00" : String.valueOf(obj[NumericConstants.TWELVE]));
                    commonColumn = getCommonColumn(altHistoryDTO.getFrequency(), fre, year) + (Constant.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName()) ? "Projected Units" : PROJECTED_PAYMENTS.getConstant());

                    altDTO.addStringProperties(commonColumn, obj[NumericConstants.THIRTEEN] == null ? "0.0" : String.valueOf(obj[NumericConstants.THIRTEEN]));
                }

                altDTO.setCheckRecord(obj[NumericConstants.FOURTEEN] == null ? false : (Boolean) (obj[NumericConstants.FOURTEEN]));
            }
        }
        return allocationList;
    }

    public void check_available_allocationTab(AlternateHistoryDTO dto, SessionDTO session,
            int checkOrUncheck, java.sql.Date startDate, java.sql.Date endDate) {
        String query = SQlUtil.getQuery("Line_Level_Available_Checkbox");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace(TABLE_NAME, Constant.ST_ALTERNATE_HIST_ALLOCATION);
        } else {
            query = query.replace(TABLE_NAME, Constant.ST_DISC_ALTERNATE_HIST_ALLOCATION);
        }
        query = query.replace(START_DATE, startDate.toString());
        query = query.replace(END_DATE, endDate.toString());
        query = query.replace(PROJECTION_DETAILS_SID, String.valueOf(dto.getProjDetailSid()));
        query = query.replace("[$AVAILABLE_CHECKBOX] ", String.valueOf(checkOrUncheck));
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }

    public void checkAll_available_allocationTab(SessionDTO session, int checkOrUncheck, java.sql.Date startDate, java.sql.Date endDate) {
        String query = SQlUtil.getQuery("Available_CheckAll");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace(TABLE_NAME, Constant.ST_ALTERNATE_HIST_ALLOCATION);
        } else {
            query = query.replace(TABLE_NAME, Constant.ST_DISC_ALTERNATE_HIST_ALLOCATION);
        }
        query = query.replace(START_DATE, startDate.toString());
        query = query.replace(END_DATE, endDate.toString());
        query = query.replace("[$AVAILABLE_CHECKBOX] ", String.valueOf(checkOrUncheck));
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }

    /**
     * To insert the alternate history table
     *
     * @param session
     * @param projDetailsSid
     * @param ccpDetailsId
     * @return
     */
    public boolean altHistInsert(SessionDTO session, String projDetailsSid, final String ccpDetailsId) {
        LOGGER.debug("Entering altHistInsert= {}, {}" , session.getSessionId(), projDetailsSid);
        DataSource datasource = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
        } catch (NamingException ex) {
            LOGGER.debug(ex.getMessage());
        }
        if (datasource != null) {
            try (Connection connection = datasource.getConnection();
                    CallableStatement statement = connection.prepareCall("{call PRC_ALTERNATE_HISTORY_INSERT (?,?,?,?,?,?)}")) {
                statement.setString(1, projDetailsSid);
                statement.setString(NumericConstants.TWO, ccpDetailsId);
                statement.setString(NumericConstants.THREE, session.getStartDate());
                statement.setString(NumericConstants.FOUR, session.getEndDate());
                statement.setInt(NumericConstants.FIVE, Integer.parseInt(session.getUserId()));
                statement.setString(NumericConstants.SIX, session.getSessionId());
                statement.execute();

            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
                return false;
            }
        }
        return true;
    }

    public void call(Object[] orderedArg, String procedureName) {
        CommonLogic.callProcedure(procedureName, orderedArg);
    }

    public String getProjDetaSid(String ccpIds, int projectionSid) {
        String projDetSid = StringUtils.EMPTY;
        List<Integer> list;
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

    public String buildCCPCountQuery(boolean isCustomer) {
        StringBuilder query = new StringBuilder();
        if (isCustomer) {
            query.append("SELECT Count(DISTINCT CCPD.Company_MASTER_SID) ");
        } else {
            query.append("SELECT Count(DISTINCT CCPD.ITEM_MASTER_SID )");
        }
        query.append(" FROM CCP_DETAILS CCPD JOIN ST_NM_SALES_PROJECTION_MASTER MAS ON MAS.CCP_DETAILS_SID = CCPD.CCP_DETAILS_SID WHERE CHECK_RECORD=1");
        LOGGER.debug("_____Count query_______________= {}" , query.toString());
        return query.toString();
    }

    public int getCCPCount(String query, SessionDTO sessionDto) {
        int count = 0;
        List<Object> objList;
        objList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDto.getCurrentTableNames()), null, null);

        if (objList != null && !objList.isEmpty()) {
            Object ob = objList.get(0);
            count += Integer.parseInt(String.valueOf(ob));
        }

        return count;

    }

    public boolean checkActuals(SessionDTO session) {
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
                + " SELECT CCP.CCP_DETAILS_SID FROM CCP_DETAILS CCP \n"
                + " JOIN ST_NM_SALES_PROJECTION_MASTER MAS ON MAS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                + " WHERE CHECK_RECORD=1 \n"
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
        List<Integer> list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));

        if (list != null && list.isEmpty()) {
            LOGGER.debug("--list size---->>>>= {}" , list.size());
            StringBuilder ccps = new StringBuilder(StringUtils.EMPTY);

            flag = false;
            for (int ccpid : list) {
                if (ccps.length() == 0) {
                    ccps.append(String.valueOf(ccpid));
                } else {
                    ccps.append(',');
                    ccps.append(String.valueOf(ccpid));
                }
            }
            LOGGER.debug("--actual ccps------------------>>>>>>= {}" , ccps);
            session.setActualccp(ccps.toString());
            list.clear();
        }
        return flag;
    }

    public void executeUpdate(String query) {
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void executeDelete(final SessionDTO session, boolean isSales) {

        String query;
        deleteCustomersAndItemsFromTempTable(session);
        if (isSales) {
            query = " DELETE FROM ST_ALTERNATE_HIST_ALLOCATION "
                    + "DELETE FROM ST_CCP_ACTUAL_DETAILS ";
        } else {
            query = " DELETE FROM ST_DISC_ALTERNATE_HIST_ALLOCATION "
                    + "DELETE FROM ST_DISC_CCP_ACTUAL_DETAILS ";
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));

    }

    public void removeTempTable(SessionDTO session) {
        String query = "delete  from dbo.AH_TEMP_DETAILS ";
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }

    public void insertforSalesTotal(SessionDTO session, String ccpIDs) {
        StringBuilder query = new StringBuilder();

        query.append(" MERGE INTO dbo.ST_ALTERNATE_HIST_ALLOCATION AS TARGET USING  ");
        query.append(" (select CCPAC.PERIOD_SID AS PERIOD_SID,ISNULL(sum(ACTUAL_UNITS),0) AS ACTUAL_UNITS,ISNULL(SUM(PROJECTION_UNITS),0)  AS PROJECTION_UNITS  ");
        query.append(" from dbo.ST_CCP_ACTUAL_DETAILS CCPAC  ");
        query.append(" join dbo.\"PERIOD\" PER ON PER.PERIOD_SID=CCPAC.PERIOD_SID  ");
        query.append(" WHERE CCPAC.CCP_DETAILS_SID in (" ).append( ccpIDs ).append( ") ");
        query.append(" group by CCPAC.PERIOD_SID)  AS SOURCE   ");
        query.append(" ON ( TARGET.PERIOD_SID = SOURCE.PERIOD_SID )  ");
        query.append(" WHEN MATCHED THEN UPDATE SET   ");
        query.append(" TARGET.TOTAL_ACTUAL_UNITS = SOURCE.ACTUAL_UNITS,   ");
        query.append("  TARGET.TOTAL_PROJECTION_UNITS = SOURCE.PROJECTION_UNITS;  ");

        CommonLogic.executeBulkUpdateQuery(QueryUtil.replaceTableNames(query.toString(), session.getCurrentTableNames()), null, null);

    }

    public void insertforDiscountTotal(SessionDTO session, String ccpIDs) {
        StringBuilder query = new StringBuilder();

        query.append(" MERGE INTO dbo.ST_DISC_ALTERNATE_HIST_ALLOCATION AS TARGET USING \n");
        query.append(" (select CCPAC.PERIOD_SID AS PERIOD_SID,ISNULL(sum(ACTUAL_AMOUNT),0) AS ACTUAL_AMOUNT,ISNULL(SUM(PROJECTION_AMOUNT),0)  AS PROJECTION_AMOUNT \n");
        query.append(" from dbo.ST_DISC_CCP_ACTUAL_DETAILS CCPAC \n");
        query.append(" join dbo.\"PERIOD\" PER ON PER.PERIOD_SID=CCPAC.PERIOD_SID \n");
        query.append(" WHERE CCPAC.CCP_DETAILS_SID in (" ).append( ccpIDs ).append( ") ");
        query.append(" group by CCPAC.PERIOD_SID)  AS SOURCE \n");
        query.append(" ON ( TARGET.PERIOD_SID = SOURCE.PERIOD_SID  )\n");
        query.append(" WHEN MATCHED THEN UPDATE SET \n");
        query.append(" TARGET.TOTAL_ACTUAL_AMOUNT = SOURCE.ACTUAL_AMOUNT, \n");
        query.append(" TARGET.TOTAL_PROJECTION_AMOUNT = SOURCE.PROJECTION_AMOUNT; ");
        CommonLogic.executeBulkUpdateQuery(QueryUtil.replaceTableNames(query.toString(), session.getCurrentTableNames()), null, null);

    }

    public List getCompanyList(int start, int offset, Set<Container.Filter> filters, Boolean isCount, SessionDTO session) {
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
        }

        query += SQlUtil.getQuery("CUSTOMER_SELECTION_SELECTED_SEARCH");
        String whereClause = " WHERE ";
        String andClause = " AND ";
        boolean isWhereClaues = true;
        //For Filters
        if (parameters.containsKey(Constant.FILTERCUSTOMER_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NO)))) {

            query = query + (isWhereClaues ? whereClause : andClause) + " CM1.COMPANY_NO like '" + String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NO)) + '\'';
            isWhereClaues = false;
        }

        if (parameters.containsKey(Constant.FILTERCONTRACT_HOLDER) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCONTRACT_HOLDER)))) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  CM.COMPANY_NAME like '" + String.valueOf(parameters.get(Constant.FILTERCONTRACT_HOLDER)) + '\'';
            isWhereClaues = false;
        }

        if (parameters.containsKey(Constant.FILTERCONTRACT_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCONTRACT_NO)))) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  CON_M.CONTRACT_NO like '" + String.valueOf(parameters.get(Constant.FILTERCONTRACT_NO)) + '\'';
            isWhereClaues = false;
        }

        if (parameters.containsKey(Constant.FILTERCONTRACT_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCONTRACT_NAME)))) {
            query = query + (isWhereClaues ? whereClause : andClause) + "   CON_M.CONTRACT_NAME like '" + String.valueOf(parameters.get(Constant.FILTERCONTRACT_NAME)) + '\'';
            isWhereClaues = false;
        }

        if (parameters.containsKey(Constant.FILTERCUSTOMER_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NAME)))) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  CM1.COMPANY_NAME like '" + String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NAME)) + '\'';
            isWhereClaues = false;
        }

        if (parameters.containsKey(Constant.FILTERMARKET_TYPE) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERMARKET_TYPE)))) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  HT.DESCRIPTION like '" + String.valueOf(parameters.get(Constant.FILTERMARKET_TYPE)) + '\'';
            isWhereClaues = false;
        }
        if (parameters.containsKey(Constant.FILTERCHECK) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCHECK)))) {
            query = query + (isWhereClaues ? whereClause : andClause) + "  CS.SELECTED_CHECKBOX like '" + String.valueOf(parameters.get(Constant.FILTERCHECK)) + '\'';
        }
        if (isCount) {
            query += ") TEMP_DETAILS ;";
        } else {
            query += " ORDER BY CON_M.CONTRACT_NO OFFSET " + start + "  ROWS FETCH NEXT " + offset + " ROWS ONLY ";
        }

        list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        return isCount ? list : getCustomizedDTO(list);
    }

    public int alternateSelectionCount(final SessionDTO sessionDTO, Set<Container.Filter> filters) {
        int count;

        Map<String, Object> parameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        String companyFilter = StringUtils.EMPTY;
        String itemFilter = StringUtils.EMPTY;
        String contractFilter = StringUtils.EMPTY;

        if (parameters.containsKey(Constant.FILTERCONTRACT_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCONTRACT_NO)))) {
            contractFilter = contractFilter + " AND CON.CONTRACT_NO like '" + String.valueOf(parameters.get(Constant.FILTERCONTRACT_NO)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERCONTRACT_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCONTRACT_NAME)))) {
            contractFilter = contractFilter + " AND CON.CONTRACT_NAME like '" + String.valueOf(parameters.get(Constant.FILTERCONTRACT_NAME)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERCUSTOMER_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NO)))) {
            companyFilter = companyFilter + " AND CM.COMPANY_NO like '" + String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NO)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERCUSTOMER_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NAME)))) {
            companyFilter = companyFilter + " AND  CM.COMPANY_NAME like '" + String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NAME)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERITEM_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_NO)))) {
            itemFilter = itemFilter + " AND  IM.ITEM_NO like '" + String.valueOf(parameters.get(Constant.FILTERITEM_NO)) + "'";
        }

        if (parameters.containsKey(Constant.FILTERITEM_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_NAME)))) {
            itemFilter = itemFilter + "  AND IM.ITEM_NAME like '" + String.valueOf(parameters.get(Constant.FILTERITEM_NAME)) + "'";
        }

        String query = SQlUtil.getQuery("alternate-history-selection-count");
        query = query.replace("@COMPANY_FILTER", companyFilter);
        query = query.replace("@ITEM_FILTER", itemFilter);
        query = query.replace("@CONTRACT_FILTER", contractFilter);

        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames()));
        count = list == null || list.isEmpty() ? 0 : (Integer) list.get(0);
        return count;
    }

    /**
     * METHOD will update 0 in table for all columns
     *
     * @param session
     * @return
     * @throws Exception
     */
    public boolean resetAllocationTab(SessionDTO session) {

        String query;
        try {
            if (session.getForecastName().equals(TabNameUtil.DISCOUNT_PROJECTION)) {
                query = SQlUtil.getQuery("AllocationTab_reset_query_Discount");
            } else {
                query = SQlUtil.getQuery("AllocationTab_reset_query_sales");
            }

            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
    public boolean checkAll_available_customerSelection(SessionDTO session, AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, boolean value) {

        try {
            Map<String, Object> parameters = new HashMap();

            if (isValidCriteria(altHistoryDTO.getContractHolder())) {
                String contractHolder = altHistoryDTO.getContractHolder();
                contractHolder = contractHolder.replace(CommonUtils.CHAR_ASTERISK, '%');

                parameters.put(Constant.CONTRACT_HOLDER, contractHolder);
            }

            if (isValidCriteria(altHistoryDTO.getContractNo())) {
                String contractNo = altHistoryDTO.getContractNo();
                contractNo = contractNo.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put(Constant.CONTRACT_NO1, contractNo);
            }

            if (isValidCriteria(altHistoryDTO.getCustomerNo())) {
                String customerNo = altHistoryDTO.getCustomerNo();
                customerNo = customerNo.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put(Constant.CUSTOMER_NO, customerNo);
            }
            if (isValidCriteria(altHistoryDTO.getMarketType())) {
                String marketType = altHistoryDTO.getMarketType();
                marketType = marketType.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put(Constant.MARKET_TYPE, marketType);
            }

            if (isValidCriteria(altHistoryDTO.getContractName())) {
                String contractName = altHistoryDTO.getContractName();
                contractName = contractName.replace(CommonUtils.CHAR_ASTERISK, '%');
                parameters.put(Constant.CONTRACT_NAME, contractName);
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
            return queryUtils.insertOrUpdate_customerSelection(session, parameters, value);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }

    }

    public boolean check_available_customerSelection(SessionDTO session, AlternateHistoryDTO altHistoryDTO) {
        String query;
        query = SQlUtil.getQuery("CUSTOMER_SELECTION_INSERT_OR_UPDATE_SINGLE_RECORD");
        query = query.replace(Constant.CONTRACT_MASTER_SID_DOLLAR, String.valueOf(altHistoryDTO.getContractMasterSid()));
        query = query.replace(Constant.COMPANY_MASTER_SID_DOLLAR, String.valueOf(altHistoryDTO.getCompanymasterSid()));
        query = query.replace(Constant.AVAILABLE_CHECKBOX, "1");

        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        return true;
    }

    public boolean uncheck_available_customerSelection(SessionDTO session, AlternateHistoryDTO altHistoryDTO) {
        String query;

        query = SQlUtil.getQuery("CUSTOMER_SELECTION_SINGLE_UPDATE_QUERY_AVALILABLE");
        query = query.replace(Constant.CONTRACT_MASTER_SID_DOLLAR, String.valueOf(altHistoryDTO.getContractMasterSid()));
        query = query.replace(Constant.COMPANY_MASTER_SID_DOLLAR, String.valueOf(altHistoryDTO.getCompanymasterSid()));
        query = query.replace(Constant.AVAILABLE_CHECKBOX, "0");

        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        return true;
    }

    public boolean add_customerSelection(SessionDTO session) {
        String query;
        query = SQlUtil.getQuery("CUSTOMER_SELECTION_ADD_RECORDS");
        query = query.replace("[$SELECTED_RECORDS]", "1");

        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        return true;
    }

    public boolean check_uncheck_selected_customerSelection(SessionDTO session, AlternateHistoryDTO altHistoryDTO, boolean values) {
        String query;
        query = SQlUtil.getQuery("CUSTOMER_SELECTION_CHECK_UNCHECK_SELECTED");
        query = query.replace(Constant.CONTRACT_MASTER_SID_DOLLAR, String.valueOf(altHistoryDTO.getContractMasterSid()));
        query = query.replace(Constant.COMPANY_MASTER_SID_DOLLAR, String.valueOf(altHistoryDTO.getCompanymasterSid()));
        query = query.replace(Constant.SELECTED_CHECKBOX, String.valueOf(values ? 1 : 0));

        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        return true;
    }

    public boolean remove_customerSelection(SessionDTO session) {
        String query;
        query = SQlUtil.getQuery("CUSTOMER_SELECTION_REMOVE_QUERY");
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        return true;
    }

    public boolean delete_unChecked_customerSelection(SessionDTO session) {
        String query;

        query = SQlUtil.getQuery("CUSTOMER_SELECTION_DELETE_QUERY");
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));

        return true;
    }

    public boolean checkAll_selected_customerSelection(SessionDTO session, boolean value) {
        String query;

        query = SQlUtil.getQuery("CUSTOMER_SELECTION_SELECTED_CHECK_ALL_QUERY");
        query = query.replace(Constant.SELECTED_CHECKBOX, value ? "1" : "0");
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));

        return true;
    }

    public boolean count_available_customerSelection(SessionDTO session) {
        String query;

        query = SQlUtil.getQuery("CUSTOMER_SELECTION_CHECK_COUNT");
        query = query.replace(Constant.AVAILABLE_CHECKBOX, "1");

        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        return list.isEmpty() || (list.get(0) == null) ? false : ((int) list.get(0) > 0);
    }

    public boolean count_selected_customerSelection(SessionDTO session) {
        String query;
        query = SQlUtil.getQuery("CUSTOMER_SELECTION_CHECK_COUNT");
        query = query.replace("AVAILABLE_CHECKBOX", "SELECTED_CHECKBOX");
        query = query.replace(Constant.SELECTED_CHECKBOX, "1");

        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        return list.isEmpty() || (list.get(0) == null) ? false : (Integer.parseInt(list.get(0).toString()) > 0);
    }

    public void addItems(final SessionDTO session) {
        String removeQuery = SQlUtil.getQuery("update-on-add-item");

        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(removeQuery, session.getCurrentTableNames()));
    }

    public void updateTableOnAddorCheckAll(AlternateHistoryDTO altHistoryDTO, Set<Container.Filter> filters, SessionDTO session, boolean isAddAllOrCheckAll, final String checkValue) {

        Map<String, Object> parameters = new HashMap<>();

        if (isValidCriteria(altHistoryDTO.getItemNo())) {
            String itemNo = altHistoryDTO.getItemNo();
            itemNo = itemNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.ITEM_NO, itemNo);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getItemName())) {
            String itemName = altHistoryDTO.getItemName();
            itemName = itemName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.ITEM_NAME_SMALL_PROPERY, itemName);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getBusinessUnitNo())) {
            String businessUnitNo = altHistoryDTO.getBusinessUnitNo();
            businessUnitNo = businessUnitNo.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.BUSINESS_UNIT_NO_PROPERTY, businessUnitNo);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getBusinessUnitName())) {
            String businessUnitName = altHistoryDTO.getBusinessUnitName();
            businessUnitName = businessUnitName.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.BUSINESS_UNIT_NAME_PROPERTY, businessUnitName);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getTheraputicClass())) {
            String theraputicClass = altHistoryDTO.getTheraputicClass();
            theraputicClass = theraputicClass.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.THERAPUTIC_CLASS_PROPERTY, theraputicClass);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getBrand())) {
            String brand = altHistoryDTO.getBrand();
            parameters.put(Constant.BRAND, brand);
        }

        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifierType())) {
            String itemIdentifierType = altHistoryDTO.getItemIdentifierType();
            parameters.put(Constant.ITEM_IDENTIFIER_TYPE, itemIdentifierType);
        }
        if (!Constant.NULL.equals(altHistoryDTO.getItemIdentifier())) {
            String itemIdentifier = altHistoryDTO.getItemIdentifier();
            itemIdentifier = itemIdentifier.replace(CommonUtils.CHAR_ASTERISK, '%');
            parameters.put(Constant.ITEM_IDENTIFIER, itemIdentifier);
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
        queryUtils.mergeForAddOrCheckAll(parameters, session, isAddAllOrCheckAll, checkValue);
    }

    public void removeAllItems(final SessionDTO session) {
        String removeQuery = SQlUtil.getQuery("remove-all-items");
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(removeQuery, session.getCurrentTableNames()));
    }

    public void removeItems(final SessionDTO session) {
        String removeQuery = SQlUtil.getQuery("remove-selected-items");
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(removeQuery, session.getCurrentTableNames()));
    }

    public void updateAvailableTableCheckUnCheck(final boolean isChecked, final SessionDTO session, String itemMasterSid) {
        String checkValue = isChecked ? "1" : "0";
        String updateQuery;
        updateQuery = isChecked ? SQlUtil.getQuery("add-item-to-temp-table-on-check") : SQlUtil.getQuery("update-temp-table-on-available-uncheck");
        updateQuery = updateQuery.replace(Constant.AVAILABLE_CHECKBOX, checkValue);
        updateQuery = updateQuery.replace("[$ITEM_MASTER_SID]", itemMasterSid);

        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(updateQuery, session.getCurrentTableNames()));
    }

    public void updateSelectedTableCheckUnCheck(final boolean isChecked, final SessionDTO session, String itemMasterSid) {
        String checkValue = isChecked ? "1" : "0";
        String updateQuery = SQlUtil.getQuery("update-temp-table-on-selected-check-uncheck");
        updateQuery = updateQuery.replace(Constant.SELECTED_CHECKBOX, checkValue);
        updateQuery = updateQuery.replace("[$ITEM_MASTER_SID]", itemMasterSid);

        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(updateQuery, session.getCurrentTableNames()));
    }

    public void updateSelectedTableOnCheckAll(final boolean isChecked, final SessionDTO session) {
        String updateQuery = SQlUtil.getQuery("update-on-selected-item-on-checkall");
        updateQuery = updateQuery.replace(Constant.SELECTED_CHECKBOX, isChecked ? "1" : "0");

        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(updateQuery, session.getCurrentTableNames()));
    }

    public void updateAllAvailableCheckBox(final SessionDTO session) {
        String updateQuery = SQlUtil.getQuery("update-all-available-checkbox");
        updateQuery = updateQuery.replace(Constant.AVAILABLE_CHECKBOX, "0");

        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(updateQuery, session.getCurrentTableNames()));
    }

    public boolean checkForAtleastOneCheckedItem(final SessionDTO session, final boolean isAvailableOrSelected) {
        boolean isAtleastOneChecked = false;
        String updateQuery = SQlUtil.getQuery("atleast-one-item-checked");
        updateQuery = updateQuery.replace("[$CHECKBOX_COLUMN]", isAvailableOrSelected ? "AVAILABLE_CHECKBOX" : "SELECTED_CHECKBOX");

        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(updateQuery, session.getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            isAtleastOneChecked = 1 == (int) list.get(0);
        }
        return isAtleastOneChecked;
    }

    public boolean checkSelectedCustomers(final SessionDTO session) {
        boolean isAtleastOneChecked = false;
        String updateQuery = SQlUtil.getQuery("selected-customers-check");

        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(updateQuery, session.getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            isAtleastOneChecked = 1 == (int) list.get(0);
        }
        return isAtleastOneChecked;
    }

    public boolean checkSelectedItems(final SessionDTO session) {
        boolean isAtleastOneChecked = false;
        String updateQuery = SQlUtil.getQuery("selected-items-check");
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(updateQuery, session.getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            isAtleastOneChecked = 1 == (int) list.get(0);
        }
        return isAtleastOneChecked;
    }

    public List getSelectedCCPS(final SessionDTO session) {
        String removeQuery = SQlUtil.getQuery("get-selected-ccps");
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(removeQuery, session.getCurrentTableNames()));
        return list;
    }

    public void deleteCustomersAndItemsFromTempTable(final SessionDTO session) {
        String deleteQuery = SQlUtil.getQuery("delete-customer-items-from-temp-table");
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(deleteQuery, session.getCurrentTableNames()));
    }

    public void deleteUnselectedCustomersOrItems(final SessionDTO session, final boolean isItemOrCustomer) {
        String deleteQuery = isItemOrCustomer ? SQlUtil.getQuery("delete-unselected-items") : SQlUtil.getQuery("delete-unselected-customers");
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(deleteQuery, session.getCurrentTableNames()));
    }

    public int count_avalibale_allocationTab(SessionDTO session, java.sql.Date startDate, java.sql.Date endDate) {

        String query = SQlUtil.getQuery("Count_Available_Table");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace(TABLE_NAME, Constant.ST_ALTERNATE_HIST_ALLOCATION);
        } else {
            query = query.replace(TABLE_NAME, Constant.ST_DISC_ALTERNATE_HIST_ALLOCATION);
        }
        query = query.replace(START_DATE, startDate.toString());
        query = query.replace(END_DATE, endDate.toString());
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        int count = 0;
        if (list != null && !list.isEmpty()) {
            count = (int) list.get(0);
        }
        return count;
    }

    public boolean validateAllocation(final String screenName, final SessionDTO session, final List allocatedPeriods) {

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

        if (fromPeriod.trim().length() <= NumericConstants.FOUR) {
            startFrom = fromPeriod.trim();
            yearFrom = fromPeriod.trim();
            startTo = endPeriod.trim();
            yearTo = endPeriod.trim();
            freq = "A";

            yearStartIndex = 0;
            yearEndIndex = NumericConstants.FOUR;
            frequencyStartIndex = 0;
            frequncyEndIndex = NumericConstants.FOUR;

        } else if (fromPeriod.trim().length() > NumericConstants.SIX) {

            List<String> months = Arrays.asList(dateFormatSymbols.getShortMonths());
            startFrom = (months.indexOf(StringUtils.capitalize(fromPeriod.substring(0, NumericConstants.THREE))) + 1) + StringUtils.EMPTY;
            yearFrom = fromPeriod.substring(NumericConstants.THREE, NumericConstants.SEVEN);
            startTo = (months.indexOf(StringUtils.capitalize(endPeriod.substring(0, NumericConstants.THREE))) + 1) + StringUtils.EMPTY;
            yearTo = endPeriod.substring(NumericConstants.THREE, NumericConstants.SEVEN);
            freq = "M";
            isMonthlyFrequency = true;
            yearStartIndex = NumericConstants.THREE;
            yearEndIndex = NumericConstants.SEVEN;
            frequencyStartIndex = 0;
            frequncyEndIndex = NumericConstants.THREE;

        } else {
            startFrom = fromPeriod.substring(1, NumericConstants.TWO);
            yearFrom = fromPeriod.substring(NumericConstants.TWO, NumericConstants.SIX);
            startTo = endPeriod.substring(1, NumericConstants.TWO);
            yearTo = endPeriod.substring(NumericConstants.TWO, NumericConstants.SIX);
            freq = StringUtils.capitalize(fromPeriod.substring(0, 1));

            yearStartIndex = NumericConstants.TWO;
            yearEndIndex = NumericConstants.SIX;
            frequencyStartIndex = 1;
            frequncyEndIndex = NumericConstants.TWO;
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
        query = query.replace(Constant.FREQUENCY_DOLLAR, freq);
        query = query.replace("[$START_PERIOD]", startFrom);
        query = query.replace("[$START_YEAR]", yearFrom);
        query = query.replace("[$END_PERIOD]", startTo);
        query = query.replace("[$END_YEAR]", yearTo);
        query = query.replace(TABLE_NAME, screenName.equals(TabNameUtil.SALES_PROJECTION) ? Constant.ST_ALTERNATE_HIST_ALLOCATION : Constant.ST_DISC_ALTERNATE_HIST_ALLOCATION);
        query = query.replace("[$ALLOCATED_PERIODS]", selectedPeriods);

        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));

        if (list == null || list.isEmpty()) {
            isNotAllocated = false;
        }
        return isNotAllocated;
    }

    public void addToQueue(SessionDTO session, java.sql.Date startDate, java.sql.Date endDate) {

        String query = SQlUtil.getQuery("Add_To_Queue_Update");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace(TABLE_NAME, Constant.ST_ALTERNATE_HIST_ALLOCATION);
        } else {
            query = query.replace(TABLE_NAME, Constant.ST_DISC_ALTERNATE_HIST_ALLOCATION);
        }
        query = query.replace(START_DATE, startDate.toString());
        query = query.replace(END_DATE, endDate.toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }

    public void check_selected_allocationTab(AlternateHistoryDTO dto, SessionDTO session,
            int checkOrUncheck, java.sql.Date startDate, java.sql.Date endDate) {
        String query = SQlUtil.getQuery("Selected_Line_Level_Check");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace(TABLE_NAME, Constant.ST_ALTERNATE_HIST_ALLOCATION);
        } else {
            query = query.replace(TABLE_NAME, Constant.ST_DISC_ALTERNATE_HIST_ALLOCATION);
        }
        query = query.replace(START_DATE, startDate.toString());
        query = query.replace(END_DATE, endDate.toString());
        query = query.replace(PROJECTION_DETAILS_SID, String.valueOf(dto.getProjDetailSid()));
        query = query.replace(Constant.SELECTED_CHECKBOX, String.valueOf(checkOrUncheck));
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }

    public void checkAll_selected_allocationTab(SessionDTO session, int checkOrUncheck, java.sql.Date startDate, java.sql.Date endDate) {
        String query = SQlUtil.getQuery("Selected_Check_All");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace(TABLE_NAME, Constant.ST_ALTERNATE_HIST_ALLOCATION);
        } else {
            query = query.replace(TABLE_NAME, Constant.ST_DISC_ALTERNATE_HIST_ALLOCATION);
        }
        query = query.replace(START_DATE, startDate.toString());
        query = query.replace(END_DATE, endDate.toString());
        query = query.replace("[$SELECTED_CHECKBOX] ", String.valueOf(checkOrUncheck));
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }

    public int count_selected_allocationTab(SessionDTO session, java.sql.Date startDate, java.sql.Date endDate) {
        String query = SQlUtil.getQuery("Count_Selected_Table");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace(TABLE_NAME, Constant.ST_ALTERNATE_HIST_ALLOCATION);
        } else {
            query = query.replace(TABLE_NAME, Constant.ST_DISC_ALTERNATE_HIST_ALLOCATION);
        }
        query = query.replace(START_DATE, startDate.toString());
        query = query.replace(END_DATE, endDate.toString());
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        return (int) list.get(0);
    }

    public void remove_selected_allocationTab(SessionDTO session, java.sql.Date startDate, java.sql.Date endDate) {
        String query = SQlUtil.getQuery("Selected_Remove");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace(TABLE_NAME, Constant.ST_ALTERNATE_HIST_ALLOCATION);
        } else {
            query = query.replace(TABLE_NAME, Constant.ST_DISC_ALTERNATE_HIST_ALLOCATION);
        }
        query = query.replace(START_DATE, startDate.toString());
        query = query.replace(END_DATE, endDate.toString());
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }

    public void removeAll_selected_allocationTab(SessionDTO session) {
        String query = SQlUtil.getQuery("Selected_Remove_All");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace(TABLE_NAME, Constant.ST_ALTERNATE_HIST_ALLOCATION);
        } else {
            query = query.replace(TABLE_NAME, Constant.ST_DISC_ALTERNATE_HIST_ALLOCATION);
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }

    public void resetTablesOnAllocation(SessionDTO session) {
        String query = SQlUtil.getQuery(TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName()) ? "reset-sales-allocation-table-on-allocation" : "reset-discount-allocation-table-on-allocation");
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }

    public int getCount_allocationTab(SessionDTO session, boolean addToQueue, Set<Container.Filter> filters) {
        String query = SQlUtil.getQuery("Get_Count_Allocation_Tables");
        if (TabNameUtil.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName())) {
            query = query.replace(TABLE_NAME, Constant.ST_ALTERNATE_HIST_ALLOCATION);
        } else {
            query = query.replace(TABLE_NAME, Constant.ST_DISC_ALTERNATE_HIST_ALLOCATION);
        }
        String filter = getFilterQuery(filters);
        if (filter.length() > 0) {
            query = query.replace("[$FILTER_JOIN]", SQlUtil.getQuery("Get_Fileter_Join_allocationTab"));
            query = query.replace("[$FILTER_CONDITION]", filter);
        } else {
            query = query.replace("[$FILTER_JOIN]", StringUtils.EMPTY);
            query = query.replace("[$FILTER_CONDITION]", StringUtils.EMPTY);
        }
        if (addToQueue) {
            query = query.replace("[$SELECTED_TABLE_CHECK]", " AND CHECK_RECORD = 1 ");
        } else {
            query = query.replace("[$SELECTED_TABLE_CHECK]", StringUtils.EMPTY);
        }

        List result = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        return (result == null || result.isEmpty()) ? 0 : (int) result.get(0);
    }

    private String getFilterQuery(Set<Container.Filter> filters) {
        Map<String, String> parameters = new HashMap();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString() + "%";
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                }
            }
        }
        StringBuilder filter = new StringBuilder();

        if (parameters.containsKey(Constant.FILTERCONTRACT_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCONTRACT_NO)))) {
            filter.append(" AND CO.CONTRACT_NO like '").append(parameters.get(Constant.FILTERCONTRACT_NO)).append('\'');
        }

        if (parameters.containsKey(Constant.FILTERCONTRACT_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCONTRACT_NAME)))) {
            filter.append(" AND CO.CONTRACT_NAME like '").append(parameters.get(Constant.FILTERCONTRACT_NAME)).append('\'');
        }

        if (parameters.containsKey(Constant.FILTERCUSTOMER_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NO)))) {
            filter.append(" AND CM.COMPANY_NO like '").append(parameters.get(Constant.FILTERCUSTOMER_NO)).append('\'');
        }

        if (parameters.containsKey(Constant.FILTERCUSTOMER_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERCUSTOMER_NAME)))) {
            filter.append(" AND  CM.COMPANY_NAME like '").append(parameters.get(Constant.FILTERCUSTOMER_NAME)).append('\'');
        }

        if (parameters.containsKey(Constant.FILTERITEM_NO) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_NO)))) {
            filter.append(" AND IM.ITEM_NO like '").append(parameters.get(Constant.FILTERITEM_NO)).append('\'');
        }

        if (parameters.containsKey(Constant.FILTERITEM_NAME) && !Constant.NULL.equals(String.valueOf(parameters.get(Constant.FILTERITEM_NAME)))) {
            filter.append(" AND  IM.ITEM_NAME like '").append(parameters.get(Constant.FILTERITEM_NAME)).append('\'');
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
    public void updateAllocationOnEdit(String propertyid, final int projDetSid, Double incDec, final String frequency, final SessionDTO session) {

        String updateQuery;

        if (Constant.SALES_PROJECTION.equalsIgnoreCase(session.getForecastName().trim())) {
            if (propertyid.contains("Units")) {
                updateQuery = SQlUtil.getQuery("update-allocation-unit-amount");
                updateQuery = updateQuery.replace("[$NEW_VALUE]", StringUtils.EMPTY + incDec);
            } else {
                updateQuery = SQlUtil.getQuery("update-allocation-percent");
                updateQuery = updateQuery.replace("[$ALLOCATION_PERCENT]", StringUtils.EMPTY + incDec);
            }
            if (propertyid.contains("Proj")) {
                updateQuery = updateQuery.replace(Constant.TOTAL_COLUMN, "TOTAL_PROJECTION_UNITS");
                updateQuery = updateQuery.replace(Constant.UPDATE_COLUMN, "PROJECTION_UNITS");
                updateQuery = updateQuery.replace(Constant.ALLOCATION_PERCENT_COLUMN, "PROJECTION_ALLOCATION_PERCENT");
            } else {
                updateQuery = updateQuery.replace(Constant.TOTAL_COLUMN, "TOTAL_ACTUAL_UNITS");
                updateQuery = updateQuery.replace(Constant.UPDATE_COLUMN, "ACTUAL_UNITS");
                updateQuery = updateQuery.replace(Constant.ALLOCATION_PERCENT_COLUMN, "ACTUAL_ALLOCATION_PERCENT");
            }
            updateQuery = updateQuery.replace(TABLE_NAME, Constant.ST_ALTERNATE_HIST_ALLOCATION);

        } else {
            if (propertyid.contains("Payments")) {
                updateQuery = SQlUtil.getQuery("update-allocation-unit-amount");
                updateQuery = updateQuery.replace("[$NEW_VALUE]", StringUtils.EMPTY + incDec);
            } else {
                updateQuery = SQlUtil.getQuery("update-allocation-percent");
                updateQuery = updateQuery.replace("[$ALLOCATION_PERCENT]", StringUtils.EMPTY + incDec);
            }
            if (propertyid.contains("Proj")) {
                updateQuery = updateQuery.replace(Constant.TOTAL_COLUMN, "TOTAL_PROJECTION_AMOUNT");
                updateQuery = updateQuery.replace(Constant.UPDATE_COLUMN, "PROJECTION_AMOUNT");
                updateQuery = updateQuery.replace(Constant.ALLOCATION_PERCENT_COLUMN, "PROJECTION_ALLOCATION_PERCENT");
            } else {
                updateQuery = updateQuery.replace(Constant.TOTAL_COLUMN, "TOTAL_ACTUAL_AMOUNT");
                updateQuery = updateQuery.replace(Constant.UPDATE_COLUMN, "ACTUAL_AMOUNT");
                updateQuery = updateQuery.replace(Constant.ALLOCATION_PERCENT_COLUMN, "ACTUAL_ALLOCATION_PERCENT");
            }
            updateQuery = updateQuery.replace(TABLE_NAME, Constant.ST_DISC_ALTERNATE_HIST_ALLOCATION);

        }

        updateQuery = updateQuery.replace(PROJECTION_DETAILS_SID, StringUtils.EMPTY + projDetSid);

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
            String periodVar = String.valueOf(CommonUtils.getMonthNumber(temp.substring(0, temp.length() - NumericConstants.FOUR)));
            updateQuery = updateQuery.replace(Constant.FREQUENCY_COLUMN, "\"MONTH\"");
            updateQuery = updateQuery.replace(Constant.FREQUENCY_DOLLAR, periodVar);
        } else if (Constant.SEMI_ANNUALLY.equals(frequency)) {
            String periodVar = temp.substring(1, temp.length() - NumericConstants.FOUR);
            updateQuery = updateQuery.replace(Constant.FREQUENCY_COLUMN, Constant.SEMI_ANNUAL);
            updateQuery = updateQuery.replace(Constant.FREQUENCY_DOLLAR, periodVar);
        } else if ("Quarterly".equals(frequency)) {
            String periodVar = temp.substring(1, temp.length() - NumericConstants.FOUR);
            updateQuery = updateQuery.replace(Constant.FREQUENCY_COLUMN, Constant.QUARTER);
            updateQuery = updateQuery.replace(Constant.FREQUENCY_DOLLAR, periodVar);
        } else {
            updateQuery = updateQuery.replace("AND PER.[$FREQUENCY_COLUMN] = [$FREQUENCY]", StringUtils.EMPTY);
        }
        String year = temp.substring(temp.length() - NumericConstants.FOUR, temp.length());
        updateQuery = updateQuery.replace("[$YEAR]", year);

        executeUpdate(QueryUtil.replaceTableNames(updateQuery, session.getCurrentTableNames()));
    }

}
