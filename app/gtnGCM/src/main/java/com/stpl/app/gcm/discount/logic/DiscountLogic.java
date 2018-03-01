/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.common.HelperListUtil;
import com.stpl.app.gcm.common.QueryUtils;
import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
import com.stpl.app.gcm.discount.dao.DiscountDAO;
import com.stpl.app.gcm.discount.dao.impl.DiscountDaoImpl;
import com.stpl.app.gcm.discount.dto.CFPComponentDetailsDTO;
import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.discount.dto.DiscountDTO;
import com.stpl.app.gcm.discount.dto.LookupDTO;
import com.stpl.app.gcm.discount.dto.PSComponentDetailsDTO;
import static com.stpl.app.gcm.util.Constants.DateFormatConstants.DEFOULT_JAVA_DATE_FORMAT;
import static com.stpl.app.gcm.util.Constants.DateFormatConstants.DEFOULT_SQL_DATE_FORMAT;
import static com.stpl.app.gcm.util.Constants.DateFormatConstants.MMDDYYYY;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import static com.stpl.app.gcm.itemmanagement.index.logic.ItemLogic.stringIsNull;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.CfpModel;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.IfpModel;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.PsModel;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.RsContractDetails;
import com.stpl.app.model.RsModel;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.IfpContractLocalServiceUtil;
import com.stpl.app.service.IfpModelLocalServiceUtil;
import com.stpl.app.service.PsContractLocalServiceUtil;
import com.stpl.app.service.PsModelLocalServiceUtil;
import com.stpl.app.service.RsContractDetailsLocalServiceUtil;
import com.stpl.app.service.RsContractLocalServiceUtil;
import com.stpl.app.service.RsDetailsLocalServiceUtil;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.logic.AbstractFilterLogic;
import com.stpl.app.gcm.transfercontract.util.CommonUtil;
import com.stpl.app.gcm.transfercontract.util.Constant;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.CONTRACT_MASTER_SID;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.SALES;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.UNITS;
import com.stpl.app.gcm.util.Converters;
import com.stpl.app.gcm.util.xmlparser.SQlUtil;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gcm.impl.CfpContractDetailsImpl;
import com.stpl.app.gcm.impl.IfpContractDetailsImpl;
import com.stpl.app.gcm.impl.PsContractDetailsImpl;
import com.stpl.app.gcm.impl.RsContractDetailsImpl;
import com.stpl.app.gcm.util.DataTypeConverter;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.vaadin.v7.data.Container;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.ComboBox;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vigneshkanna
 */
public class DiscountLogic {

    private static final CommonDao DAO = CommonImpl.getInstance();
    private static final DiscountDAO discountDAO = new DiscountDaoImpl();
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscountLogic.class);
    public static final SimpleDateFormat DBDate = new SimpleDateFormat(MMDDYYYY.getConstant());
    private final QueryUtils queryUtils = new QueryUtils();
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0");
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    public static final char CHAR_PERCENT = '%';
    private final DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
    private final HelperListUtil helperlist = HelperListUtil.getInstance();
    public static final String SPACE_OFFSET_SPACE = " OFFSET ";
    public static final String SET_COLUMN_NAME_QUESTION = "?SET_COLUMN_NAME?";
    public static final String ROWS_ONLY = " ROWS ONLY";
    public static final String FILTER = "@filter@";

    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';

    public List getMarketType() {
        List<String> list = discountDAO.getMarketType();
        list.add(0, Constants.SELECT_ONE);
        return list;
    }

    public int getContractSearchCount(ErrorfulFieldGroup discountChBinder, Set<Container.Filter> filters) {
        int count = 0;
        count = discountDAO.getContractsCount(discountChBinder, filters);
        return count;
    }

    public List<RemoveDiscountDto> getContractSearch(ErrorfulFieldGroup discountChBinder, int startIndex,
            int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumn) {
        LOGGER.debug("Entered contract search logic");
        List resultList;
        List<RemoveDiscountDto> searchList;
        resultList = discountDAO.getContracts(discountChBinder, startIndex, offset, filters, sortByColumn);
        searchList = setContractValues(resultList);
        LOGGER.debug("Ending contract search logic" + searchList.size());
        return searchList;
    }

    public List<RemoveDiscountDto> setContractValues(List list) {
        List<RemoveDiscountDto> returnList = new ArrayList<>();
        RemoveDiscountDto removeDiscountDto;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object[] objects = (Object[]) list.get(i);
            removeDiscountDto = new RemoveDiscountDto();
            removeDiscountDto.setContractHolder(Converters.convertNullToEmpty(objects[0]));
            removeDiscountDto.setContractNo(Converters.convertNullToEmpty(objects[1]));
            removeDiscountDto.setContractName(Converters.convertNullToEmpty(objects[NumericConstants.TWO]));
            removeDiscountDto.setMarketType(String.valueOf(objects[NumericConstants.THREE]));
            removeDiscountDto.setStartDate(setFilterFormatted(objects[NumericConstants.FOUR]));
            removeDiscountDto.setContractstartDate((Date) (objects[NumericConstants.FOUR]));
            if (queryUtils.getNull(String.valueOf(objects[NumericConstants.FIVE]))) {
                removeDiscountDto.setEndDate(setFilterFormatted(objects[NumericConstants.FIVE]));
                removeDiscountDto.setContractendDate((Date) (objects[NumericConstants.FIVE]));
            }
            removeDiscountDto.setCfpName(String.valueOf(objects[NumericConstants.SIX]));
            removeDiscountDto.setIfpName(String.valueOf(objects[NumericConstants.SEVEN]));
            removeDiscountDto.setPsName(String.valueOf(objects[NumericConstants.EIGHT]));
            removeDiscountDto.setRsName(String.valueOf(objects[NumericConstants.NINE]));
            removeDiscountDto.setContractSid(Integer.parseInt(StringUtils.EMPTY.equals(Converters.convertNullToEmpty(objects[NumericConstants.TEN])) ? Constants.ZEROSTRING : String.valueOf(objects[NumericConstants.TEN])));
            removeDiscountDto.setCfpSid(Integer.parseInt(StringUtils.EMPTY.equals(Converters.convertNullToEmpty(objects[NumericConstants.ELEVEN])) ? Constants.ZEROSTRING : String.valueOf(objects[NumericConstants.ELEVEN])));
            removeDiscountDto.setIfpSid(Integer.parseInt(StringUtils.EMPTY.equals(Converters.convertNullToEmpty(objects[NumericConstants.TWELVE])) ? Constants.ZEROSTRING : String.valueOf(objects[NumericConstants.TWELVE])));
            removeDiscountDto.setPsSid(Integer.parseInt(StringUtils.EMPTY.equals(Converters.convertNullToEmpty(objects[NumericConstants.THIRTEEN])) ? Constants.ZEROSTRING : String.valueOf(objects[NumericConstants.THIRTEEN])));
            removeDiscountDto.setRsSid(Integer.parseInt(StringUtils.EMPTY.equals(Converters.convertNullToEmpty(objects[NumericConstants.FOURTEEN])) ? Constants.ZEROSTRING : String.valueOf(objects[NumericConstants.FOURTEEN])));
            removeDiscountDto.setCompanySid(Integer.parseInt(StringUtils.EMPTY.equals(Converters.convertNullToEmpty(objects[NumericConstants.FIFTEEN])) ? Constants.ZEROSTRING : String.valueOf(objects[NumericConstants.FIFTEEN])));
            removeDiscountDto.setContractId(String.valueOf(objects[NumericConstants.SIXTEEN]));
            removeDiscountDto.setContractStatus(isNull(objects[NumericConstants.SEVENTEEN]));
            returnList.add(removeDiscountDto);
        }

        return returnList;
    }

    public List<ContractsDetailsDto> getLevelDetails(ContractsDetailsDto tableBean) {
        List<ContractsDetailsDto> levelsDetails = new ArrayList<>();
        try {
            final DynamicQuery contractQuery = RsContractDetailsLocalServiceUtil.dynamicQuery();
            contractQuery.add(RestrictionsFactoryUtil.eq("rsContractSid", tableBean.getInternalId()));
            List<RsContractDetails> contractDetails = discountDAO.getContractDetails(contractQuery);
            levelsDetails = getNewDiscountTabDto(contractDetails);
        } catch (Exception e) {
             LOGGER.error("",e);
        }
        return levelsDetails;
    }

    private List<ContractsDetailsDto> getNewDiscountTabDto(List<RsContractDetails> contractDetails) {
        List<ContractsDetailsDto> levelsDetails = new ArrayList<>();
        try {
            if (!contractDetails.isEmpty()) {
                for (RsContractDetails contractDetail : contractDetails) {
                    ContractsDetailsDto discountTabDto = new ContractsDetailsDto();
                    ItemMaster itemMaster = discountDAO.getItemDetails(contractDetail.getItemMasterSid());
                    discountTabDto.setItemNo(itemMaster.getItemNo());
                    discountTabDto.setItemName(itemMaster.getItemName());
                    discountTabDto.setTherapyClass(CommonLogic.getDescriptionFromID(itemMaster.getTherapeuticClass()));
                    discountTabDto.setBrand(String.valueOf(itemMaster.getBrandMasterSid()));
                    discountTabDto.setItemStatus(CommonLogic.getDescriptionFromID(itemMaster.getItemStatus()));
                    discountTabDto.setItemStartDate(Converters.convertNullToEmpty(itemMaster.getItemStartDate().toString()));
                    discountTabDto.setItemEndDate(Converters.convertNullToEmpty(itemMaster.getItemEndDate()));
                    levelsDetails.add(discountTabDto);
                }
            }
        } catch (Exception e) {
             LOGGER.error("",e);
        }
        return levelsDetails;
    }

    public int getItemSearchCount() {
        int count = 0;
        DynamicQuery query = ItemMasterLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.in("brandMasterSid", new Object[]{NumericConstants.FOUR, NumericConstants.SIX, NumericConstants.TEN, NumericConstants.EIGHTY_FIVE}));
        query.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        count = discountDAO.getItemsCount(query);
        return count;
    }

    public List<ContractsDetailsDto> getItemSearch(ContractsDetailsDto removeDiscountDto)  {
        List<ContractsDetailsDto> searchList;
        DynamicQuery query = ItemMasterLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.in("brandMasterSid", new Object[]{NumericConstants.FOUR, NumericConstants.SIX, NumericConstants.TEN, NumericConstants.EIGHTY_FIVE}));
        query.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        query.setLimit(removeDiscountDto.getStartIndex(), removeDiscountDto.getEndIndex());
        List<ItemMaster> resultList = discountDAO.getItems(query);
        searchList = getSearchResult(resultList);
        return searchList;
    }

    private List<ContractsDetailsDto> getSearchResult(List<ItemMaster> resultList)  {
        List<ContractsDetailsDto> searchList = new ArrayList<>();
        for (ItemMaster resultList1 : resultList) {
            ContractsDetailsDto dto = new ContractsDetailsDto();
            dto.setItemId(resultList1.getItemId());
            dto.setItemName(resultList1.getItemName());
            dto.setTherapyClass(CommonLogic.getDescriptionFromID(resultList1.getTherapeuticClass()));
            dto.setItemNo(Converters.convertNullToEmpty(resultList1.getItemNo()));
            dto.setBrand(Converters.convertNullToEmpty(String.valueOf(resultList1.getBrandMasterSid())));
            dto.setForm(CommonLogic.getDescriptionFromID(resultList1.getForm()));
            dto.setStrength(CommonLogic.getDescriptionFromID(resultList1.getStrength()));
            dto.setItemStatus(CommonLogic.getDescriptionFromID(resultList1.getItemStatus()));
            dto.setItemStartDate(Converters.convertNullToEmpty(resultList1.getItemStartDate()));
            dto.setItemEndDate(Converters.convertNullToEmpty(resultList1.getItemEndDate()));
            searchList.add(dto);
        }
        return searchList;
    }

    public List<RemoveDiscountDto> getprojectionValues(RemoveDiscountDto removeDiscountDto, List contractSid, List rsSid) {
        List removeList = new ArrayList();
        List queryList = new ArrayList();
        List<RemoveDiscountDto> searchList = new ArrayList<>();
        try {
            String query = queryUtils.getLatestCCPQuery(contractSid, rsSid);
            removeList = discountDAO.getValues(query);

            if (removeList.size() > 0) {
                Object[] objects = (Object[]) removeList.get(0);
                removeDiscountDto.setProjectionSid(Integer.parseInt(String.valueOf(objects[0])));
                removeDiscountDto.setForecastingType(String.valueOf(objects[1]));
                String dateQuery = queryUtils.getForecastDates(removeDiscountDto.getForecastingType().equalsIgnoreCase("Non Mandated") ? "Commercial" : "Government");
                queryList = discountDAO.getValues(dateQuery);
                Object[] objects1 = (Object[]) queryList.get(0);
                removeDiscountDto.setFromDate((Date) objects1[0]);
                removeDiscountDto.setToDate((Date) objects1[1]);
                LOGGER.debug("From" + removeDiscountDto.getFromDate() + "To" + removeDiscountDto.getToDate());
                searchList.add(removeDiscountDto);
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        LOGGER.debug("Ending getprojectionValues with " + searchList.size());
        return searchList;
    }

    public RemoveDiscountDto getForecastDates(String type) {
        List queryList = new ArrayList();
        RemoveDiscountDto removeDiscountDto = new RemoveDiscountDto();
        try {
            String query = queryUtils.getForecastDates(type);
            queryList = discountDAO.getValues(query);
            Object[] objects = (Object[]) queryList.get(0);
            removeDiscountDto.setFromDate((Date) objects[0]);
            removeDiscountDto.setToDate((Date) objects[1]);
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        return removeDiscountDto;
    }

    public List getSummary(RemoveDiscountDto removeDiscountDto) {
        LOGGER.debug("Entering getSummary");
        List summaryList = new ArrayList();
        try {
            String query = queryUtils.getSummaryCountQuery(removeDiscountDto);
            summaryList = discountDAO.getValues(query);
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        LOGGER.debug("Ending getSummary" + summaryList.get(0));
        return summaryList;
    }

    public List<ContractsDetailsDto> getRebateSchedule(ContractsDetailsDto newDiscountTabDto) {
        List<ContractsDetailsDto> searchList;
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.CFP_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.CFP_NAME_QUESTION, "%");
        inputMap.put(StringConstantsUtil.CFP_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.CFP_STATUS_QUESTION, "%");
        inputMap.put(StringConstantsUtil.CFP_TYPE_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_NAME_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_STATUS_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_TYPE_QUESTION, "%");
        inputMap.put(StringConstantsUtil.PS_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.PS_NAME_QUESTION, "%");
        inputMap.put(StringConstantsUtil.PS_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.PS_STATUS_QUESTION, "%");
        inputMap.put(StringConstantsUtil.PS_TYPE_QUESTION, "%");
        inputMap.put(StringConstantsUtil.RS_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.RS_NAME_QUESTION, "%");
        inputMap.put(StringConstantsUtil.RS_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.RS_STATUS_QUESTION, "%");
        inputMap.put(StringConstantsUtil.RS_TYPE_QUESTION, "%");
        List results = new ArrayList();
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.RS_VALUE.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, "ad.rsSearch");
            query += "ORDER BY RS.RS_NAME OFFSET " + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            results = discountDAO.getRebates(query);
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.CFP.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, "ad.cfpSearch");
            query += "ORDER BY CFP.CFP_NAME OFFSET " + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            results = discountDAO.getRebates(query);
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.IFP.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADIFP_SEARCH);
            query += "ORDER BY IFP.IFP_NAME OFFSET " + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            results = discountDAO.getRebates(query);
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.PS_VALUE.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, "ad.psSearch");
            query += "ORDER BY PS.PS_NAME OFFSET " + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            results = discountDAO.getRebates(query);
        }

        searchList = getCustomisedDto(results, newDiscountTabDto);
        return searchList;
    }

    private List<ContractsDetailsDto> getCustomisedDto(List results, ContractsDetailsDto newDiscountTabDto) {

        List<ContractsDetailsDto> searchList = new ArrayList<>();
        int size = results.size();
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.RS_VALUE.toString())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setRsSid(Converters.convertNullToEmpty(arr[0]));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setName(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.FOUR]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.FIVE]))) ? null : DBDate.format((Date) arr[NumericConstants.FIVE]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.SIX]))) ? null : DBDate.format((Date) arr[NumericConstants.SIX]));
                tabDto.setIfpSystemId(Converters.convertNullToEmpty(arr[NumericConstants.SEVEN]));
                tabDto.setIfpName(Converters.convertNullToEmpty(arr[NumericConstants.EIGHT]));
                tabDto.setFrequency(!String.valueOf(arr[NumericConstants.NINE]).equals(Constants.SELECT_ONE) ? String.valueOf(arr[NumericConstants.NINE]) : StringUtils.EMPTY);
                tabDto.setRarType(Converters.convertNullToEmpty(arr[NumericConstants.TEN]));
                tabDto.setBasis(Converters.convertNullToEmpty(StringUtils.EMPTY));
                tabDto.setCategory(Constants.IndicatorConstants.RS_VALUE.getConstant());
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setModelSysId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                searchList.add(tabDto);
            }
        } else if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.PS_VALUE.toString())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setPsSid(Converters.convertNullToEmpty(arr[0]));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setName(Converters.convertNullToEmpty(arr[NumericConstants.SIX]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.FOUR]))) ? null : DBDate.format((Date) arr[NumericConstants.FOUR]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.FIVE]))) ? null : DBDate.format((Date) arr[NumericConstants.FIVE]));
                tabDto.setIfpSystemId(Converters.convertNullToEmpty(arr[NumericConstants.SEVEN]));
                tabDto.setIfpName(Converters.convertNullToEmpty(arr[NumericConstants.EIGHT]));
                tabDto.setType(Converters.convertNullToEmpty(arr[NumericConstants.NINE]));
                tabDto.setPsCategory(Converters.convertNullToEmpty(arr[NumericConstants.TEN]));
                tabDto.setDesignation(Converters.convertNullToEmpty(arr[NumericConstants.ELEVEN]));
                tabDto.setCategory(Constants.IndicatorConstants.PS_VALUE.getConstant());
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setModelSysId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                searchList.add(tabDto);
            }
        } else if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.CFP.toString())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setCfpId(DataTypeConverter.convertStringToInteger(Converters.convertNullToEmpty(arr[0])));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setName(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.FIVE]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.SIX]))) ? null : DBDate.format((Date) arr[NumericConstants.SIX]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.SEVEN]))) ? null : DBDate.format((Date) arr[NumericConstants.SEVEN]));
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setModelSysId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setCategory(Constants.IndicatorConstants.CFP.getConstant());
                searchList.add(tabDto);
            }
        } else if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.IFP.toString())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setIfpId(DataTypeConverter.convertStringToInteger(Converters.convertNullToEmpty(arr[0])));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setName(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.FIVE]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.SIX]))) ? null : DBDate.format((Date) arr[NumericConstants.SIX]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.SEVEN]))) ? null : DBDate.format((Date) arr[NumericConstants.SEVEN]));
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(Converters.convertNullToEmpty(arr[0]))));
                tabDto.setModelSysId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setCategory(Constants.IndicatorConstants.IFP.getConstant());
                searchList.add(tabDto);
            }
        }

        return searchList;
    }

    public List<ContractsDetailsDto> getItemsFromRs(ContractsDetailsDto newDiscountTabDto)   {
        List<ContractsDetailsDto> searchList;
        Map<String, String> inputMap = new HashMap<>();
        List results = new ArrayList();
        inputMap.put(StringConstantsUtil.SID_QUESTION, String.valueOf(newDiscountTabDto.getInternalId()));
        if (Constants.IndicatorConstants.CFP.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.cfpFromResults");
            query += "ORDER BY CFP_CM.COMPANY_NAME OFFSET " + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            results = discountDAO.getRebates(query);
        } else if (Constants.IndicatorConstants.IFP.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADIFP_FROM_RESULTS);
            query += "ORDER BY IFP_IM.ITEM_NAME OFFSET " + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            results = discountDAO.getRebates(query);
        } else if (Constants.IndicatorConstants.PS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.psFromResults");
            query += "ORDER BY PS_IM.ITEM_NAME OFFSET " + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            results = discountDAO.getRebates(query);
        } else if (Constants.IndicatorConstants.RS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.rsFromResults");
            query += "ORDER BY RS_IM.ITEM_NAME OFFSET " + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            results = discountDAO.getRebates(query);
        }
        searchList = getDetailsFromResult(results, newDiscountTabDto);
        return searchList;
    }

    private List<ContractsDetailsDto> getDetailsFromResult(List results, ContractsDetailsDto newDiscountTabDto) {
        List<ContractsDetailsDto> searchList = new ArrayList<>();

        int size = results.size();
        if (Constants.IndicatorConstants.RS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setItemNo(Converters.convertNullToEmpty(arr[0]));
                tabDto.setItemName(Converters.convertNullToEmpty(arr[1]));
                tabDto.setBrand(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.FOUR]))) ? null : DBDate.format((Date) arr[NumericConstants.FOUR]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.FIVE]))) ? null : DBDate.format((Date) arr[NumericConstants.FIVE]));
                tabDto.setFormulaId(Converters.convertNullToEmpty(arr[NumericConstants.SIX]));
                tabDto.setRebatePlanId(Converters.convertNullToEmpty(arr[NumericConstants.SEVEN]));
                tabDto.setRebatePlanName(Converters.convertNullToEmpty(arr[NumericConstants.EIGHT]));
                tabDto.setRebateAmount(Converters.convertNullToEmpty(arr[NumericConstants.NINE]));
                tabDto.setBundleNo(Converters.convertNullToEmpty(arr[NumericConstants.TEN]));
                tabDto.setAttachedDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.ELEVEN]))) ? null : DBDate.format((Date) arr[NumericConstants.ELEVEN]));
                searchList.add(tabDto);
            }
        } else if (Constants.IndicatorConstants.PS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setItemNo(Converters.convertNullToEmpty(arr[0]));
                tabDto.setItemName(Converters.convertNullToEmpty(arr[1]));
                tabDto.setBrand(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.FOUR]))) ? null : DBDate.format((Date) arr[NumericConstants.FOUR]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.FIVE]))) ? null : DBDate.format((Date) arr[NumericConstants.FIVE]));
                tabDto.setPriceType(Converters.convertNullToEmpty(arr[NumericConstants.SIX]));
                tabDto.setPricePlanNo(Converters.convertNullToEmpty(arr[NumericConstants.SEVEN]));
                tabDto.setPricePlanName(Converters.convertNullToEmpty(arr[NumericConstants.EIGHT]));
                tabDto.setPriceProtectionStartDate(Converters.convertNullToEmpty(arr[NumericConstants.NINE]));
                tabDto.setPriceProtectionEndDate(Converters.convertNullToEmpty(arr[NumericConstants.TEN]));
                tabDto.setPriceToleranceInterval(Converters.convertNullToEmpty(arr[NumericConstants.ELEVEN]));
                tabDto.setPriceToleranceFrequency(Converters.convertNullToEmpty(arr[NumericConstants.TWELVE]));
                tabDto.setPriceToleranceType(Converters.convertNullToEmpty(arr[NumericConstants.THIRTEEN]));
                tabDto.setPriceTolerance(Converters.convertNullToEmpty(arr[NumericConstants.FOURTEEN]));
                tabDto.setAttachedDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.FIFTEEN]))) ? null : DBDate.format((Date) arr[NumericConstants.FIFTEEN]));

                searchList.add(tabDto);
            }
        } else if (Constants.IndicatorConstants.IFP.toString().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setItemNo(Converters.convertNullToEmpty(arr[0]));
                tabDto.setItemName(Converters.convertNullToEmpty(arr[1]));
                tabDto.setBrand(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.FOUR]))) ? null : DBDate.format((Date) arr[NumericConstants.FOUR]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.FIVE]))) ? null : DBDate.format((Date) arr[NumericConstants.FIVE]));
                tabDto.setAttachedDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.SIX]))) ? null : DBDate.format((Date) arr[NumericConstants.SIX]));
                searchList.add(tabDto);
            }
        } else if (Constants.IndicatorConstants.CFP.toString().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setTpNo(Converters.convertNullToEmpty(arr[0]));
                tabDto.setTpName(Converters.convertNullToEmpty(arr[1]));
                tabDto.setTpContractNo(Converters.convertNullToEmpty(arr[0]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.TWO]))) ? null : DBDate.format((Date) arr[NumericConstants.TWO]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.THREE]))) ? null : DBDate.format((Date) arr[NumericConstants.THREE]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.FOUR]));
                tabDto.setTradeClass(Converters.convertNullToEmpty(arr[NumericConstants.FIVE]));
                tabDto.setAttachedDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.SIX]))) ? null : DBDate.format((Date) arr[NumericConstants.SIX]));
                searchList.add(tabDto);
            }
        }
        return searchList;

    }

    public List<DiscountDTO> getConfigureSales(Object parentId, RemoveDiscountDto projSelDTO) {
        if (parentId instanceof DiscountDTO) {
            DiscountDTO dto = (DiscountDTO) parentId;
            projSelDTO.setLevelNo(dto.getLevelNo() + 1);
        } else {
            projSelDTO.setLevelNo(1);
        }
        List<DiscountDTO> salesList = configureLevel(projSelDTO);
        return salesList;
    }

    public List<DiscountDTO> configureLevel(RemoveDiscountDto projSelDTO) {
        List<DiscountDTO> resultList = new ArrayList<>();
        String query = queryUtils.getSummaryQuery(projSelDTO);
        try {
            List list = (List<Object[]>) discountDAO.getValues(query);
            DiscountDTO dto = new DiscountDTO();
            String levelName = StringUtils.EMPTY;
            for (Object object : list) {

                final Object[] obj = (Object[]) object;
                if (!levelName.equals(String.valueOf(obj[0]))) {
                    dto = new DiscountDTO();
                    resultList.add(dto);
                }

                dto.setLevelValue(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                levelName = dto.getLevelValue();
                dto.setParent(1);
                dto.setLevelNo(Integer.valueOf(String.valueOf(obj[NumericConstants.FIVE])));
                if (dto.getLevelNo() == NumericConstants.FOUR) {
                    dto.setParent(0);
                }
                setSalesData(dto, obj);

            }

        } catch (Exception e) {
            LOGGER.error("",e);
        }
        return resultList;
    }

    private void setSalesData(DiscountDTO dto, Object[] obj) {
        String commonColumn = "q" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
        dto.addStringProperties(commonColumn + SALES.getConstant(), getFormattedValue(AMOUNT, stringIsNull(obj[NumericConstants.THREE])));
        dto.addStringProperties(commonColumn + UNITS.getConstant(), getFormattedValue(AMOUNT_UNITS, stringIsNull(obj[NumericConstants.FOUR])));
    }


    /**
     * For Format
     *
     * @param FORMAT
     * @param value
     * @return String
     */
    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(Constants.NULL)) {
            value = Constants.ZEROSTRING;
        } else {
            value = FORMAT.format(Double.valueOf(value));
        }
        return value;
    }

    public int getConfigureSalesCount(Object parentId, RemoveDiscountDto projSelDTO) {
        if (parentId instanceof DiscountDTO) {
            projSelDTO.setLevelNo(projSelDTO.getLevelNo() + 1);
        } else {
            projSelDTO.setLevelNo(1);
        }
        int count = configureLevelCount(projSelDTO);
        return count;
    }

    public int configureLevelCount(RemoveDiscountDto projSelDTO) {
        List<Object> list = getSummary(projSelDTO);
        int count = 0;
        if (list.size() > 0) {
            count = Integer.parseInt(String.valueOf(list.get(0)));
        }
        return count;
    }

    public int getRebateScheduleCount(ContractsDetailsDto newDiscountTabDto)  {
        List results = new ArrayList();
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.CFP_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.CFP_NAME_QUESTION, "%");
        inputMap.put(StringConstantsUtil.CFP_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.CFP_STATUS_QUESTION, "%");
        inputMap.put(StringConstantsUtil.CFP_TYPE_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_NAME_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_STATUS_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_TYPE_QUESTION, "%");
        inputMap.put(StringConstantsUtil.PS_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.PS_NAME_QUESTION, "%");
        inputMap.put(StringConstantsUtil.PS_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.PS_STATUS_QUESTION, "%");
        inputMap.put(StringConstantsUtil.PS_TYPE_QUESTION, "%");
        inputMap.put(StringConstantsUtil.RS_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.RS_NAME_QUESTION, "%");
        inputMap.put(StringConstantsUtil.RS_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.RS_STATUS_QUESTION, "%");
        inputMap.put(StringConstantsUtil.RS_TYPE_QUESTION, "%");
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.RS_VALUE.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, "ad.rsSearch");
            results = discountDAO.getRebates(query);
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.CFP.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, "ad.cfpSearch");
            results = discountDAO.getRebates(query);

        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.IFP.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADIFP_SEARCH);
            results = discountDAO.getRebates(query);
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.PS_VALUE.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, "ad.psSearch");
            results = discountDAO.getRebates(query);
        }

        return results.size();
    }

    public int getItemsFromRsCount(ContractsDetailsDto newDiscountTabDto)  {
        List results = new ArrayList();
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.SID_QUESTION, String.valueOf(newDiscountTabDto.getInternalId()));
        if (Constants.IndicatorConstants.CFP.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.cfpFromResults");
            results = discountDAO.getRebates(query);
        } else if (Constants.IndicatorConstants.IFP.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADIFP_FROM_RESULTS);
            results = discountDAO.getRebates(query);
        } else if (Constants.IndicatorConstants.PS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.psFromResults");
            results = discountDAO.getRebates(query);
        } else if (Constants.IndicatorConstants.RS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.rsFromResults");
            results = discountDAO.getRebates(query);
        }
        return results.size();
    }

    public List<RemoveDiscountDto> getItems(RemoveDiscountDto discountDto, int start, int offset, boolean flag)  {
        String query = queryUtils.getItems(discountDto.getContractSid(), discountDto.getRsSid(), flag, start, offset);
        List itemList = (List<RemoveDiscountDto>) discountDAO.getRebates(query);

        return setItemValues(itemList);
    }

    public int getItemsCount(RemoveDiscountDto discountDto, int start, int offset)  {
        int count = 0;
        String query = queryUtils.getItems(discountDto.getContractSid(), discountDto.getRsSid(), true, start, offset);
        List itemList = (List<RemoveDiscountDto>) discountDAO.getRebates(query);
        count = itemList.isEmpty() ? 0 : itemList.size();
        return count;
    }

    public List<RemoveDiscountDto> getTreeItems(int contractSid, int rsId)  {
        String query = queryUtils.getItems(contractSid, rsId, true, 0, 0);
        List itemList = (List<RemoveDiscountDto>) discountDAO.getRebates(query);

        return setItemValues(itemList);
    }

    public List<RemoveDiscountDto> setItemValues(List list) {
        List<RemoveDiscountDto> itemList = new ArrayList<>();
        RemoveDiscountDto removeDiscountDto;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object[] objects = (Object[]) list.get(i);
            removeDiscountDto = new RemoveDiscountDto();
            removeDiscountDto.setItemSid(Integer.parseInt(String.valueOf(objects[0])));
            removeDiscountDto.setItemNo(isNull(objects[1]));
            removeDiscountDto.setItemName(isNull(objects[NumericConstants.TWO]));
            removeDiscountDto.setTherapyClass(isNull(objects[NumericConstants.THREE]));
            removeDiscountDto.setBrand(isNull(objects[NumericConstants.FOUR]));
            removeDiscountDto.setItemStatus(isNull(objects[NumericConstants.FIVE]));
            if (getNull(String.valueOf(objects[NumericConstants.SIX]))) {
                removeDiscountDto.setItemStartDate(DBDate.format(objects[NumericConstants.SIX]));
            }
            if (getNull(String.valueOf(objects[NumericConstants.SEVEN]))) {
                removeDiscountDto.setItemEndDate(DBDate.format(objects[NumericConstants.SEVEN]));
            }
            if (getNull(String.valueOf(objects[NumericConstants.EIGHT]))) {
                removeDiscountDto.setRebatePlan(isNull(objects[NumericConstants.EIGHT]));
            }
            if (getNull(String.valueOf(objects[NumericConstants.NINE]))) {
                removeDiscountDto.setFormulaId(isNull(objects[NumericConstants.NINE]));
            }
            removeDiscountDto.setRsSid(Integer.parseInt(String.valueOf(objects[NumericConstants.TEN])));
            removeDiscountDto.setRsId(isNull(objects[NumericConstants.ELEVEN]));
            removeDiscountDto.setRsNo(isNull(objects[NumericConstants.TWELVE]));
            removeDiscountDto.setRsName(isNull(objects[NumericConstants.THIRTEEN]));
            removeDiscountDto.setRsStatus(isNull(objects[NumericConstants.FOURTEEN]));
            removeDiscountDto.setRsStartDate(String.valueOf(objects[NumericConstants.FIFTEEN]));
            removeDiscountDto.setRsEndDate(String.valueOf(objects[NumericConstants.SIXTEEN]));
            removeDiscountDto.setRebateFrequency(isNull(objects[NumericConstants.SEVENTEEN]));
            removeDiscountDto.setProgramType(isNull(objects[NumericConstants.EIGHTEEN]));
            removeDiscountDto.setRsType(isNull(objects[NumericConstants.NINETEEN]));
            removeDiscountDto.setRsCategory(isNull(objects[NumericConstants.TWENTY]));
            removeDiscountDto.setPaymentFrequency(isNull(objects[NumericConstants.TWENTY_ONE]));
            removeDiscountDto.setRebatePlanLevel(isNull(objects[NumericConstants.TWENTY_TWO]));
            removeDiscountDto.setFormulaType(isNull(objects[NumericConstants.TWENTY_THREE]));
            removeDiscountDto.setFormulaName(isNull(objects[NumericConstants.TWENTY_FOUR]));
            removeDiscountDto.setRebateAmount(isNull(objects[NumericConstants.TWENTY_FIVE]));
            removeDiscountDto.setRebatePlanName(isNull(objects[NumericConstants.TWENTY_SIX]));
            removeDiscountDto.setRebatePlanId(isNull(objects[NumericConstants.TWENTY_SEVEN]));
            itemList.add(removeDiscountDto);
        }
        return itemList;
    }

    public boolean getNull(String value) {
        boolean check = false;
        if (!StringUtils.EMPTY.equals(value) && !Constants.NULL.equals(value) && value != null) {
            check = true;
        }
        return check;
    }

    public String isNull(Object value) {
        if (value != null && !Constants.NULL.equals(value) && !Constants.SELECT_ONE.equals(value)) {
            return String.valueOf(value);
        }
        return StringUtils.EMPTY;
    }

    public void updateRebate(String rsSid) {
        try {
            discountDAO.updateRebate(queryUtils.updateDate(rsSid));
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    public List<RemoveDiscountDto> getRebateSearch(String field, String value, int contractSid, int rsSid) {
        LOGGER.debug("Entered contract search logic");
        List resultList;
        List<RemoveDiscountDto> searchList;
        resultList = discountDAO.getRebateSearch(field, value, contractSid, rsSid);
        searchList = setContractValues(resultList);
        LOGGER.debug("Ending contract search logic" + searchList.size());
        return searchList;
    }

    public List<CFPComponentDetailsDTO> getFromCfpCD(Object parent) {
        List<CFPComponentDetailsDTO> retList = new ArrayList<>();
        Map<String, String> inputMap = new HashMap<>();
        if (parent instanceof ContractsDetailsDto) {

            inputMap.put(StringConstantsUtil.SID_QUESTION, String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
        }

        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "ad.cfpFromCD"));
        for (Object[] temp : resList) {
            CFPComponentDetailsDTO tempDto = new CFPComponentDetailsDTO();
            tempDto.setCompanyNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setCompanyName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.THREE]))) ? null : DBDate.format((Date) temp[NumericConstants.THREE]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR]))) ? null : DBDate.format((Date) temp[NumericConstants.FOUR]));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.TWO])));
            retList.add(tempDto);
        }

        return retList;
    }

    public List<PSComponentDetailsDTO> getFromIfpCD(Object parent) {
        List<PSComponentDetailsDTO> retList = new ArrayList<>();
        Map<String, String> inputMap = new HashMap<>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put(StringConstantsUtil.SID_QUESTION, String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "ad.ifpFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();

            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.THREE])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.TWO])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FIVE]))) ? null : DBDate.format((Date) temp[NumericConstants.FIVE]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.SIX]))) ? null : DBDate.format((Date) temp[NumericConstants.SIX]));
            retList.add(tempDto);
        }
        return retList;
    }

    public List<PSComponentDetailsDTO> getFromPsCD(Object parent) {
        List<PSComponentDetailsDTO> retList = new ArrayList<>();
        Map<String, String> inputMap = new HashMap<>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put(StringConstantsUtil.SID_QUESTION, String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "ad.psFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.TWO])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.THREE])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FIVE]))) ? null : DBDate.format((Date) temp[NumericConstants.FIVE]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.SIX]))) ? null : DBDate.format((Date) temp[NumericConstants.SIX]));

            retList.add(tempDto);
        }
        return retList;
    }

    public List<PSComponentDetailsDTO> getFromRsCD(Object parent) {
        List<PSComponentDetailsDTO> retList = new ArrayList<>();
        Map<String, String> inputMap = new HashMap<>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put(StringConstantsUtil.SID_QUESTION, String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "ad.rsFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.TWO])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.THREE])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FIVE]))) ? null : DBDate.format((Date) temp[NumericConstants.FIVE]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.SIX]))) ? null : DBDate.format((Date) temp[NumericConstants.SIX]));
            retList.add(tempDto);
        }
        return retList;
    }

    public static void saveRS(final int contractSystemId, final int cfpSystemId, final int ifpSystemId, final int psSystemId, final ContractsDetailsDto contractMember,boolean flag) throws  PortalException {
        LOGGER.debug("Entering saveRS method with contractSystemId=" + contractSystemId +"contractMember.getModelSysId()-----------------"+contractMember.getModelSysId());
         int rsId = flag ? Integer.valueOf(contractMember.getRsSid()) : contractMember.getModelSysId();
        final RsModel rebateMaster = discountDAO.getRebateScheduleMaster(rsId);
        final RsContract rsMasterAttached = RsContractLocalServiceUtil.createRsContract(0);
        rsMasterAttached.setContractMasterSid(contractSystemId);
        rsMasterAttached.setCfpContractSid((cfpSystemId != 0) ? String.valueOf(cfpSystemId) : null);
        rsMasterAttached.setIfpContractSid((ifpSystemId != 0) ? String.valueOf(ifpSystemId) : null);
        rsMasterAttached.setPsContractSid((psSystemId != 0) ? String.valueOf(psSystemId) : null);
        rsMasterAttached.setRsModelSid(rsId);
        rsMasterAttached.setRsName(rebateMaster.getRsName());
        rsMasterAttached.setRsId(rebateMaster.getRsId());
        rsMasterAttached.setRsNo(rebateMaster.getRsNo());
        rsMasterAttached.setRsType(rebateMaster.getRsType());
        rsMasterAttached.setRebateProgramType(rebateMaster.getRebateProgramType());
        rsMasterAttached.setRsCategory(rebateMaster.getRsCategory());
        rsMasterAttached.setRsStartDate(rebateMaster.getRsStartDate());
        rsMasterAttached.setRsEndDate(rebateMaster.getRsEndDate());
        rsMasterAttached.setRsTradeClass(rebateMaster.getRsTradeClass());
        rsMasterAttached.setRsDesignation(String.valueOf(rebateMaster.getRsDesignation()));
        rsMasterAttached.setParentRsId(rebateMaster.getParentRsId());
        rsMasterAttached.setParentRsName(rebateMaster.getParentRsName());
        rsMasterAttached.setRsStatus(rebateMaster.getRsStatus());
        rsMasterAttached.setRsTransRefId(rebateMaster.getRsTransRefId());
        rsMasterAttached.setRsTransRefNo(rebateMaster.getRsTransRefNo());
        rsMasterAttached.setRsTransRefName(rebateMaster.getRsTransRefName());
        rsMasterAttached.setPaymentMethod(rebateMaster.getPaymentMethod());
        rsMasterAttached.setPaymentFrequency(rebateMaster.getPaymentFrequency());
        rsMasterAttached.setPaymentTerms(rebateMaster.getPaymentTerms());
        rsMasterAttached.setRebateFrequency(rebateMaster.getRebateFrequency());
        rsMasterAttached.setRsCalendar(String.valueOf(rebateMaster.getRsCalendar()));
        rsMasterAttached.setRebateProcessingType(rebateMaster.getRebateProcessingType());
        rsMasterAttached.setRsValidationProfile(rebateMaster.getRsValidationProfile());
        rsMasterAttached.setRebateRuleType(rebateMaster.getRebateRuleType());
        rsMasterAttached.setRebateRuleAssociation(rebateMaster.getRebateRuleAssociation());
        rsMasterAttached.setRebatePlanLevel(rebateMaster.getRebatePlanLevel());
        rsMasterAttached.setInterestBearingIndicator(rebateMaster.getInterestBearingIndicator());
        rsMasterAttached.setInterestBearingBasis(rebateMaster.getInterestBearingBasis());
        rsMasterAttached.setPaymentGracePeriod(rebateMaster.getPaymentGracePeriod());
        rsMasterAttached.setRsContractAttachedDate(new Date());
        rsMasterAttached.setMakePayableTo(rebateMaster.getMakePayableTo());
        rsMasterAttached.setAddress1(rebateMaster.getAddress1());
        rsMasterAttached.setAddress2(rebateMaster.getAddress2());
        rsMasterAttached.setCity(rebateMaster.getCity());
        rsMasterAttached.setState(rebateMaster.getState());
        rsMasterAttached.setZipCode(rebateMaster.getZipCode());
        rsMasterAttached.setCreatedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        rsMasterAttached.setCreatedDate(new Date());
        rsMasterAttached.setModifiedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        rsMasterAttached.setModifiedDate(new Date());
        rsMasterAttached.setFormulaMethodId(rebateMaster.getFormulaMethodId());
        rsMasterAttached.setRecordLockStatus(false);
        rsMasterAttached.setInboundStatus("A");

        DynamicQuery query = RsContractLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(CONTRACT_MASTER_SID.getConstant(), contractSystemId));
        if (cfpSystemId != 0) {
            query.add(RestrictionsFactoryUtil.ilike(Constants.CFP_CONTRACT_SID, String.valueOf(cfpSystemId)));
        } else {
            query.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        }
        if (ifpSystemId != 0) {
            query.add(RestrictionsFactoryUtil.ilike(Constants.IFP_CONTRACT_SID, String.valueOf(ifpSystemId)));
        } else {
            query.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
        }
        if (psSystemId != 0) {
            query.add(RestrictionsFactoryUtil.ilike("psContractSid", String.valueOf(psSystemId)));
        } else {
            query.add(RestrictionsFactoryUtil.isNull("psContractSid"));
        }
        query.add(RestrictionsFactoryUtil.eq("rsModelSid", rsId));
        List<RsContract> list = RsContractLocalServiceUtil.dynamicQuery(query);
        RsContract rsContract = RsContractLocalServiceUtil.createRsContract(0);

        if (list.isEmpty()) {
            rsMasterAttached.setContractMasterSid(contractSystemId);
            rsMasterAttached.setCfpContractSid((cfpSystemId != 0) ? String.valueOf(cfpSystemId) : null);
            rsMasterAttached.setIfpContractSid((ifpSystemId != 0) ? String.valueOf(ifpSystemId) : null);
            rsMasterAttached.setPsContractSid((ifpSystemId != 0) ? String.valueOf(psSystemId) : null);
            rsMasterAttached.setRsModelSid(rsId);

            rsContract = discountDAO.addRsMasterAttached(rsMasterAttached);
        } else {
            rsContract.setRsContractSid(list.get(0).getRsContractSid());
        }
        try {
            List<Object> input = new ArrayList<>(NumericConstants.EIGHT);
            input.add(rsContract.getRsContractSid());
            input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            input.add(DBDate.format(new Date()));
            input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            input.add(DBDate.format(new Date()));
            input.add(rsId);
            input.add(DBDate.format(rebateMaster.getRsStartDate()));
            if (rebateMaster.getRsEndDate() != null) {
                input.add(DBDate.format(rebateMaster.getRsEndDate()));
            } else {
                input.add(null);
            }
            if (list.isEmpty()) {
                RsContractDetailsImpl.saveRsDetailsAttached(input, null);
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }

        DynamicQuery rsContractDetailsQuery = RsContractDetailsLocalServiceUtil.dynamicQuery();
        rsContractDetailsQuery.add(RestrictionsFactoryUtil.eq("rsContractSid", rsContract.getRsContractSid()));
        rsContractDetailsQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
        List<RsContractDetails> rsContDetList = RsContractDetailsLocalServiceUtil.dynamicQuery(rsContractDetailsQuery);
        for (int j = 0; j < rsContDetList.size(); j++) {
            RsContract rsCont = RsContractLocalServiceUtil.getRsContract(rsContDetList.get(j).getRsContractSid());
            DynamicQuery rsDetailsQuery = RsDetailsLocalServiceUtil.dynamicQuery();
            rsDetailsQuery.add(RestrictionsFactoryUtil.eq("rsModelSid", rsCont.getRsModelSid()));
            rsDetailsQuery.add(RestrictionsFactoryUtil.eq("itemMasterSid", rsContDetList.get(j).getItemMasterSid()));
            rsDetailsQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
            RsDetailsLocalServiceUtil.dynamicQuery(rsDetailsQuery);
        }
        LOGGER.debug("End of saveRS method");
    }

    public static void getToInput(final Map<String, String> inputMap, ContractsDetailsDto newDiscountTabDto, boolean fromNewTab) {
        String searchValue = newDiscountTabDto.getSearchFieldValue();
        String searchField;
        if (fromNewTab) {
            searchField = newDiscountTabDto.getSearchField().split("-")[1];
        } else {
            searchField = newDiscountTabDto.getSearchField();
        }

        if (Constants.getInstance().cfpSearch[0].equals(searchField)) {
            inputMap.put(StringConstantsUtil.CFP_NO_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[1].equals(searchField)) {
            inputMap.put(StringConstantsUtil.CFP_NAME_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.TWO].equals(searchField)) {
            inputMap.put(StringConstantsUtil.CFP_ID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.THREE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.CFP_STATUS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.FOUR].equals(searchField)) {
            inputMap.put(StringConstantsUtil.CFP_TYPE_QUESTION, CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.getInstance().ifpSearch[0].equals(searchField)) {
            inputMap.put(StringConstantsUtil.IFP_NO_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.getIFPSEARCH()[1].equals(searchField)) {
            inputMap.put(StringConstantsUtil.IFP_NAME_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.getIFPSEARCH()[NumericConstants.TWO].equals(searchField)) {
            inputMap.put(StringConstantsUtil.IFP_ID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.THREE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.IFP_STATUS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.FOUR].equals(searchField)) {
            inputMap.put(StringConstantsUtil.IFP_TYPE_QUESTION, CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.getInstance().psSearch[0].equals(searchField)) {
            inputMap.put(StringConstantsUtil.PS_NO_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.getPSSEARCH()[1].equals(searchField)) {
            inputMap.put(StringConstantsUtil.PS_NAME_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.getPSSEARCH()[NumericConstants.TWO].equals(searchField)) {
            inputMap.put(StringConstantsUtil.PS_ID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.THREE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.PS_STATUS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.FOUR].equals(searchField)) {
            inputMap.put(StringConstantsUtil.PS_TYPE_QUESTION, CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.getInstance().rsSearch[0].equals(searchField)) {
            inputMap.put(StringConstantsUtil.RS_NO_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.getRSSEARCH()[1].equals(searchField)) {
            inputMap.put(StringConstantsUtil.RS_NAME_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.getRSSEARCH()[NumericConstants.TWO].equals(searchField)) {
            inputMap.put(StringConstantsUtil.RS_ID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().rsSearch[NumericConstants.THREE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.RS_STATUS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().rsSearch[NumericConstants.FOUR].equals(searchField)) {
            inputMap.put(StringConstantsUtil.RS_TYPE_QUESTION, CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.getInstance().itemSearch[0].equals(searchField)) {
            inputMap.put(StringConstantsUtil.ITEM_ID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[1].equals(searchField)) {
            inputMap.put(StringConstantsUtil.ITEM_NO_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.TWO].equals(searchField)) {
            inputMap.put(StringConstantsUtil.ITEM_NAME_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.THREE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.ITEM_STATUS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.FOUR].equals(searchField)) {
            inputMap.put(StringConstantsUtil.ITEM_TYPE_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.FIVE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.BRAND_MASTER_SID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.SIX].equals(searchField)) {
            inputMap.put(StringConstantsUtil.FORM_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.SEVEN].equals(searchField)) {
            inputMap.put(StringConstantsUtil.STRENGTH_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.EIGHT].equals(searchField)) {
            inputMap.put(StringConstantsUtil.THERAPEUTIC_CLASS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.getInstance().companySearch[0].equals(searchField)) {
            inputMap.put(StringConstantsUtil.COMPANY_ID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().companySearch[1].equals(searchField)) {
            inputMap.put(StringConstantsUtil.COMPANY_NAME_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().companySearch[NumericConstants.TWO].equals(searchField)) {
            inputMap.put(StringConstantsUtil.COMPANY_NO_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().companySearch[NumericConstants.THREE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.COMPANY_STATUS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().companySearch[NumericConstants.FOUR].equals(searchField)) {
            inputMap.put(StringConstantsUtil.COMPANY_TYPE_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().companySearch[NumericConstants.FIVE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.COMPANY_CATEGORY_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().companySearch[NumericConstants.SIX].equals(searchField)) {
            inputMap.put(StringConstantsUtil.TRADE_CLASS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        }

    }

    public int getCommonCountForNewTab(ContractsDetailsDto newDiscountTabDto, SessionDTO sessionDTO)  {
        Map<String, String> inputMap = new HashMap<>();
        Map<String, String> inputMapToAppend = new HashMap<>();
        String searchField = newDiscountTabDto.getSearchField().split("-")[0];
        inputMap.put(StringConstantsUtil.IFP_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_NAME_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_STATUS_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_TYPE_QUESTION, "%");
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, sessionDTO.getUserId());
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, sessionDTO.getSearchSessionId());

        List results = new ArrayList();
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString()) || searchField.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            getToInput(inputMap, inputMapToAppend, newDiscountTabDto, true);
            String query = CommonUtil.getQuery(inputMap, "ad.ifpSearchCount");
            results = discountDAO.getRebates(query);
        }

        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            inputMap.put(StringConstantsUtil.OPERATION, "%" + Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
            getToInput(inputMap, inputMapToAppend, newDiscountTabDto, true);
            String query = CommonUtil.getQuery(inputMap, inputMapToAppend, "ad.tempItemSearchCountForIFP");
            results = discountDAO.getRebates(query);
        }
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            inputMap.put(StringConstantsUtil.COMPANY_ID_QUESTION, "%");
            inputMap.put(StringConstantsUtil.COMPANY_NO_QUESTION, "%");
            inputMap.put(StringConstantsUtil.COMPANY_NAME_QUESTION, "%");
            inputMap.put(StringConstantsUtil.COMPANY_STATUS_QUESTION, "%");
            inputMap.put(StringConstantsUtil.OPERATION, "%" + Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
            getToInput(inputMap, inputMapToAppend, newDiscountTabDto, true);
            String query = CommonUtil.getQuery(inputMap, inputMapToAppend, "ad.tempCompanySearchCountForCFP");
            results = discountDAO.getRebates(query);
        }
        Object obj = null;
        if (!results.isEmpty()) {
            obj = results.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    public static boolean insertToTempTable(ContractsDetailsDto newDiscountTabDto, SessionDTO sessionDTO) {
        String searchField = newDiscountTabDto.getCategory();
        Map<String, String> inputMap = new HashMap<>();

        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, sessionDTO.getUserId());
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, sessionDTO.getSearchSessionId());

        inputMap.put(StringConstantsUtil.COLUMN_NAME_QUESTION, StringConstantsUtil.USERS_SID_COLUMN);
        inputMap.put(StringConstantsUtil.COLUMN_NAM_E2_QUESTION, StringConstantsUtil.SESSION_ID_COLUMN);
        inputMap.put(StringConstantsUtil.COLUMN_NAM_E3_QUESTION, StringConstantsUtil.OPERATION_COLUMN);

        inputMap.put(StringConstantsUtil.VALU_E1_QUESTION, sessionDTO.getUserId());
        inputMap.put(StringConstantsUtil.VALU_E2_QUESTION, sessionDTO.getSearchSessionId());

        int count = 0;
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.toString()) || searchField.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.toString())) {
            inputMap.put(StringConstantsUtil.TABLE_NAME_QUESTION, StringConstantsUtil.IMTD_ITEM_PRICE_REBATE_DETAILS);
            String operation = StringUtils.EMPTY;
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.toString())) {
                operation = Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant();
            }
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.toString())) {
                operation = Constants.IndicatorConstants.SELECTED_PS_IFP_FOR_ADD_DISCOUNT.getConstant();
            }
            inputMap.put("?IFP_MODEL_SID?", StringUtils.EMPTY + newDiscountTabDto.getInternalId());
            inputMap.put(StringConstantsUtil.OPERATION, operation);
            inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, operation);
            String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADTEMP_DATA_DELETE) + ";" + CommonUtil.getQuery(inputMap, "ad.tempItemDataInsertFromIFP") + ";";

            try {
                count = HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
            } catch (Exception ex) {
                LoggerFactory.getLogger(DiscountLogic.class.getName()).error("", ex);
            }
        } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.IFP.toString())) {
            inputMap.put(StringConstantsUtil.TABLE_NAME_QUESTION, StringConstantsUtil.IMTD_IFP_DETAILS);
            inputMap.put(StringConstantsUtil.ITEM_ID_QUESTION, "%");
            inputMap.put(StringConstantsUtil.ITEM_NO_QUESTION, "%");
            inputMap.put(StringConstantsUtil.ITEM_NAME_QUESTION, "%");
            inputMap.put(StringConstantsUtil.ITEM_STATUS_QUESTION, "%");
            inputMap.put(StringConstantsUtil.ITEM_TYPE_QUESTION, "%");
            inputMap.put(StringConstantsUtil.BRAND_MASTER_SID_QUESTION, "%");
            inputMap.put(StringConstantsUtil.FORM_QUESTION, "%");
            inputMap.put(StringConstantsUtil.STRENGTH_QUESTION, "%");
            inputMap.put(StringConstantsUtil.THERAPEUTIC_CLASS_QUESTION, "%");
            inputMap.put(StringConstantsUtil.OPERATION, Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
            inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
            String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADTEMP_DATA_DELETE) + ";" + CommonUtil.getQuery(inputMap, "ad.tempDataInsertForIFP") + ";";
            try {
                count = HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.CFP.toString())) {
            inputMap.put(StringConstantsUtil.TABLE_NAME_QUESTION, StringConstantsUtil.IMTD_CFP_DETAILS);
            inputMap.put(StringConstantsUtil.COMPANY_ID_QUESTION, "%");
            inputMap.put(StringConstantsUtil.COMPANY_NO_QUESTION, "%");
            inputMap.put(StringConstantsUtil.COMPANY_NAME_QUESTION, "%");
            inputMap.put(StringConstantsUtil.COMPANY_STATUS_QUESTION, "%");
            inputMap.put(StringConstantsUtil.TRADE_CLASS_QUESTION, "%");
            inputMap.put(StringConstantsUtil.COMPANY_TYPE_QUESTION, "%");
            inputMap.put(StringConstantsUtil.COMPANY_CATEGORY_QUESTION, "%");
            inputMap.put(StringConstantsUtil.OPERATION, Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
            inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
            String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADTEMP_DATA_DELETE) + ";" + CommonUtil.getQuery(inputMap, "ad.tempDataInsertForCFP") + ";";
            try {
                count = HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        }
        boolean ret = count > 0;
        return ret;
    }

    public List<ContractsDetailsDto> getCommonSearchList(ContractsDetailsDto newDiscountTabDto, SessionDTO sessionDTO)  {
        List<ContractsDetailsDto> searchList;
        Map<String, String> inputMap = new HashMap<>();
        Map<String, String> inputMapToAppend = new HashMap<>();
        String searchField = newDiscountTabDto.getSearchField().split("-")[0];
        inputMap.put(StringConstantsUtil.IFP_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_NAME_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_STATUS_QUESTION, "%");
        inputMap.put(StringConstantsUtil.IFP_TYPE_QUESTION, "%");
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, sessionDTO.getUserId());
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, sessionDTO.getSearchSessionId());

        List results = new ArrayList();
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString()) || searchField.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            getToInput(inputMap, inputMapToAppend, newDiscountTabDto, true);
            String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADIFP_SEARCH);
            if (newDiscountTabDto.getEndIndex() > 0) {
                query += "ORDER BY IFP.IFP_NAME OFFSET " + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            }
            results = discountDAO.getRebates(query);
        }
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            inputMap.put(StringConstantsUtil.OPERATION, "%" + Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
            getToInput(inputMap, inputMapToAppend, newDiscountTabDto, true);
            String query = CommonUtil.getQuery(inputMap, inputMapToAppend, "ad.tempItemSearchForIFP");
            if (newDiscountTabDto.getEndIndex() > 0) {
                query += " order by ITEM_NAME OFFSET " + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            }
            results = discountDAO.getRebates(query);
        }
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            inputMap.put(StringConstantsUtil.COMPANY_ID_QUESTION, "%");
            inputMap.put(StringConstantsUtil.COMPANY_NO_QUESTION, "%");
            inputMap.put(StringConstantsUtil.COMPANY_NAME_QUESTION, "%");
            inputMap.put(StringConstantsUtil.COMPANY_STATUS_QUESTION, "%");
            inputMap.put(StringConstantsUtil.OPERATION, "%" + Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
            getToInput(inputMap, inputMapToAppend, newDiscountTabDto, true);
            String query = CommonUtil.getQuery(inputMap, inputMapToAppend, "ad.tempCompanySearchForCFP");
            if (newDiscountTabDto.getEndIndex() > 0) {
                query += " order by COMPANY_NAME OFFSET " + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            }
            results = discountDAO.getRebates(query);
        }

        searchList = getCustomisedSearchDto(results, searchField);
        return searchList;
    }

    private List<ContractsDetailsDto> getCustomisedSearchDto(List results, String searchField) {

        List<ContractsDetailsDto> searchList = new ArrayList<>();
        int size = results.size();
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString()) || searchField.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setIfpId(DataTypeConverter.convertStringToInteger(Converters.convertNullToEmpty(arr[0])));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setName(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.FIVE]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.SIX]))) ? null : DBDate.format((Date) arr[NumericConstants.SIX]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.SEVEN]))) ? null : DBDate.format((Date) arr[NumericConstants.SEVEN]));
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
                    tabDto.setCategory(Constants.IndicatorConstants.RS_VALUE.getConstant());
                } else {
                    tabDto.setCategory(Constants.IndicatorConstants.PS_VALUE.getConstant());
                }
                searchList.add(tabDto);
            }
        } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();

                tabDto.setInternalId(DataTypeConverter.convertStringToInteger(Converters.convertNullToEmpty(arr[0])));
                tabDto.setItemNo(Converters.convertNullToEmpty(arr[1]));
                tabDto.setItemName(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setBrand(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.FIVE]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.SEVEN]))) ? null : DBDate.format((Date) arr[NumericConstants.SEVEN]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.EIGHT]))) ? null : DBDate.format((Date) arr[NumericConstants.EIGHT]));
                tabDto.setCheckRecord(Boolean.valueOf(Converters.convertNullToEmpty(arr[NumericConstants.NINE])));
                tabDto.setAttachedDate(StringUtils.EMPTY);
                tabDto.setSystemId(Integer.valueOf(Converters.convertNullToEmpty(arr[NumericConstants.TEN])));
                tabDto.setCategory(Constants.IndicatorConstants.IFP.getConstant());

                searchList.add(tabDto);
            }
        } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setInternalId(DataTypeConverter.convertStringToInteger(Converters.convertNullToEmpty(arr[0])));
                tabDto.setTradingPartnerNo(Converters.convertNullToEmpty(arr[1]));
                tabDto.setTradingPartnerName(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setTpContractNo(StringUtils.EMPTY);
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.THREE]))) ? null : DBDate.format((Date) arr[NumericConstants.THREE]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[NumericConstants.FOUR]))) ? null : DBDate.format((Date) arr[NumericConstants.FOUR]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.FIVE]));
                tabDto.setTradeClass(Converters.convertNullToEmpty(arr[NumericConstants.SIX]));
                tabDto.setCheckRecord(Boolean.valueOf(Converters.convertNullToEmpty(arr[NumericConstants.SEVEN])));
                tabDto.setAttachedDate(StringUtils.EMPTY);
                tabDto.setSystemId(Integer.valueOf(Converters.convertNullToEmpty(arr[NumericConstants.EIGHT])));
                tabDto.setCategory(Constants.IndicatorConstants.CFP.getConstant());
                searchList.add(tabDto);
            }
        }
        return searchList;
    }

    public static void updateTempTableRecord(ContractsDetailsDto newDiscountTabDto, SessionDTO sessionDTO, String propertyId, boolean isSearchTable) {
        Map<String, String> inputMap = new HashMap<>();
        String searchField = newDiscountTabDto.getCategory();

        inputMap.put(StringConstantsUtil.COLUMN_NAME_QUESTION, StringConstantsUtil.USERS_SID_COLUMN);
        inputMap.put(StringConstantsUtil.COLUMN_NAM_E2_QUESTION, StringConstantsUtil.SESSION_ID_COLUMN);
        inputMap.put(StringConstantsUtil.COLUMN_NAM_E3_QUESTION, StringConstantsUtil.OPERATION_COLUMN);

        inputMap.put(StringConstantsUtil.VALU_E1_QUESTION, sessionDTO.getUserId());
        inputMap.put(StringConstantsUtil.VALU_E2_QUESTION, sessionDTO.getSearchSessionId());
        inputMap.put(StringConstantsUtil.EXTRA_COLUMN, StringUtils.EMPTY);

        if (propertyId.equals(Constants.CHECK_RECORD)) {
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.IFP.toString())) {
                inputMap.put(SET_COLUMN_NAME_QUESTION, "CHECK_BOX");
            } else {
                inputMap.put(SET_COLUMN_NAME_QUESTION, "CHECK_RECORD");
            }

            inputMap.put(StringConstantsUtil.SET_VALUE, newDiscountTabDto.getCheckRecord() ? "1" : Constants.ZEROSTRING);
        } else if (propertyId.equals("status")) {
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.IFP.toString())) {
                inputMap.put(SET_COLUMN_NAME_QUESTION, "ITEM_STATUS");
            } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.CFP.toString())) {
                inputMap.put(SET_COLUMN_NAME_QUESTION, "COMPANY_STATUS");
            } else {
                inputMap.put(SET_COLUMN_NAME_QUESTION, "RS_DETAILS_SID");
            }
            inputMap.put(StringConstantsUtil.SET_VALUE, newDiscountTabDto.getStatus().equals(Constants.ZEROSTRING) ? null : newDiscountTabDto.getStatus());
        } else if (propertyId.equals("sDate")) {
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.IFP.toString())) {
                inputMap.put(SET_COLUMN_NAME_QUESTION, "ITEM_START_DATE");
            } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.CFP.toString())) {
                inputMap.put(SET_COLUMN_NAME_QUESTION, "COMPANY_START_DATE");
            } else {
                inputMap.put(SET_COLUMN_NAME_QUESTION, "ITEM_REBATE_START_DATE");
            }
            String value = !String.valueOf(newDiscountTabDto.getsDate()).equals(Constants.NULL) ? "'" + CommonLogic.convertDateFormat(newDiscountTabDto.getsDate().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()) + "'" : null;
            inputMap.put(StringConstantsUtil.SET_VALUE, value);
        } else if (propertyId.equals("eDate")) {
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.IFP.toString())) {
                inputMap.put(SET_COLUMN_NAME_QUESTION, "ITEM_END_DATE");
            } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.CFP.toString())) {
                inputMap.put(SET_COLUMN_NAME_QUESTION, "COMPANY_END_DATE");
            } else {
                inputMap.put(SET_COLUMN_NAME_QUESTION, "ITEM_REBATE_END_DATE");
            }
            String value = !String.valueOf(newDiscountTabDto.geteDate()).equals(Constants.NULL) ? "'" + CommonLogic.convertDateFormat(newDiscountTabDto.geteDate().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()) + "'" : null;
            inputMap.put(StringConstantsUtil.SET_VALUE, value);
        } else if (propertyId.equals("rebatePlan")) {
            inputMap.put(SET_COLUMN_NAME_QUESTION, "REBATE_PLAN_SYSTEM_ID");
            String value = newDiscountTabDto.getRebatePlan().equals(Constants.ZEROSTRING) ? null : newDiscountTabDto.getRebatePlan();
            inputMap.put(StringConstantsUtil.SET_VALUE, value);
            String rebateName = newDiscountTabDto.getRebatePlanName();
            String value1 = rebateName.equals(StringUtils.EMPTY) ? null : "'" + rebateName + "'";
            inputMap.put(StringConstantsUtil.EXTRA_COLUMN, " , UDC2=" + value1 + " ");
        } else if (propertyId.equals("formulaName")) {
            inputMap.put(SET_COLUMN_NAME_QUESTION, "FORMULA_ID");
            inputMap.put(StringConstantsUtil.SET_VALUE, newDiscountTabDto.getFormulaId().equals(StringUtils.EMPTY) ? null : newDiscountTabDto.getFormulaId());
            String formulaName = newDiscountTabDto.getFormulaName();
            String value = formulaName.equals(StringUtils.EMPTY) ? null : "'" + formulaName + "'";
            inputMap.put(StringConstantsUtil.EXTRA_COLUMN, " , FORMULA_NAME=" + value + " ");
        } else if (propertyId.equals("priceType")) {
            inputMap.put(SET_COLUMN_NAME_QUESTION, "PRICE_TYPE");
            String value = newDiscountTabDto.getPriceType().equals(Constants.ZEROSTRING) ? null : newDiscountTabDto.getPriceType();
            inputMap.put(StringConstantsUtil.SET_VALUE, value);
        } else if (propertyId.equals("ppSDate")) {
            inputMap.put(SET_COLUMN_NAME_QUESTION, "PRICE_PROTECTION_START_DATE");
            String value = !String.valueOf(newDiscountTabDto.getPpSDate()).equals(Constants.NULL) ? "'" + CommonLogic.convertDateFormat(newDiscountTabDto.getPpSDate().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()) + "'" : null;
            inputMap.put(StringConstantsUtil.SET_VALUE, value);
        } else {
            return;
        }

        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.getConstant()) || searchField.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.getConstant())) {
            inputMap.put(StringConstantsUtil.TABLE_NAME_QUESTION, StringConstantsUtil.IMTD_ITEM_PRICE_REBATE_DETAILS);
            inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant());
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.toString())) {
                if (isSearchTable) {
                    inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, "%" + Constants.IndicatorConstants.PS_IFP_FOR_ADD_DISCOUNT.getConstant());
                } else {
                    inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, Constants.IndicatorConstants.SELECTED_PS_IFP_FOR_ADD_DISCOUNT.getConstant());
                }
            } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.toString())) {
                if (isSearchTable) {
                    inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, "%" + Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant());
                } else {
                    inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant());
                }
            }
            String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADTEMP_UPDATE_RECORD);
            if (newDiscountTabDto.getBulkUpdate()) {
                query += " AND CHECK_RECORD=1";
            } else if (!newDiscountTabDto.getCheckAll()) {
                query += " AND IMTD_ITEM_PRICE_REBATE_SID=" + newDiscountTabDto.getSystemId();
                query += " AND IFP_MODEL_SID=" + newDiscountTabDto.getInternalId();
            }
            try {
                HelperTableLocalServiceUtil.executeUpdateQuery(query);
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.IFP.toString())) {
            inputMap.put(StringConstantsUtil.TABLE_NAME_QUESTION, StringConstantsUtil.IMTD_IFP_DETAILS);
            if (isSearchTable) {
                inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, "%" + Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
            } else {
                inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, Constants.IndicatorConstants.SELECTED_ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
            }
            String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADTEMP_UPDATE_RECORD);
            if (newDiscountTabDto.getBulkUpdate()) {
                query += " AND CHECK_BOX=1";
            } else if (!newDiscountTabDto.getCheckAll()) {
                query += " AND IMTD_IFP_DETAILS_SID=" + newDiscountTabDto.getSystemId();
                query += " AND ITEM_MASTER_SID=" + newDiscountTabDto.getInternalId();
            }
            try {
                HelperTableLocalServiceUtil.executeUpdateQuery(query);
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.CFP.toString())) {
            inputMap.put(StringConstantsUtil.TABLE_NAME_QUESTION, StringConstantsUtil.IMTD_CFP_DETAILS);
            if (isSearchTable) {
                inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, "%" + Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
            } else {
                inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, Constants.IndicatorConstants.SELECTED_COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
            }
            String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADTEMP_UPDATE_RECORD);
            if (newDiscountTabDto.getBulkUpdate()) {
                query += " AND CHECK_RECORD=1";
            } else if (!newDiscountTabDto.getCheckAll()) {
                query += " AND IMTD_CFP_DETAILS_SID=" + newDiscountTabDto.getSystemId();
                query += " AND COMPANY_MASTER_SID=" + newDiscountTabDto.getInternalId();
            }
            try {
                HelperTableLocalServiceUtil.executeUpdateQuery(query);
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        }
    }

    public int getCommonCountForSelectedResult(ContractsDetailsDto newDiscountTabDto)  {
        List results = new ArrayList();
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.SID_QUESTION, String.valueOf(newDiscountTabDto.getInternalId()));
        if (Constants.IndicatorConstants.RS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADIFP_FROM_RESULTS);
            results = discountDAO.getRebates(query);
        }
        return results.size();
    }

    public static int getCountForNewDiscountSelectedItems(ContractsDetailsDto newDiscountTabDto, SessionDTO sessionDTO, boolean selectedItemsTable,boolean isAdd) {
        List results = new ArrayList();
        Map<String, String> inputMap = new HashMap<>();
        String operation = StringUtils.EMPTY;
        String searchField = newDiscountTabDto.getCategory();
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, sessionDTO.getUserId());
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, sessionDTO.getSearchSessionId());
        
        if (Constants.IndicatorConstants.PS_VALUE.toString().equals(searchField) || Constants.IndicatorConstants.RS_VALUE.toString().equals(searchField)) {
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.toString())) {
                if (selectedItemsTable && !isAdd) {
                    operation = Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant();
                } else {
                    operation = Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant();
                }
            }
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.toString())) {
                if (selectedItemsTable && !isAdd) {
                    operation = Constants.IndicatorConstants.SELECTED_PS_IFP_FOR_ADD_DISCOUNT.getConstant();
                } else {
                    operation = Constants.IndicatorConstants.PS_IFP_FOR_ADD_DISCOUNT.getConstant();
                }
            }
            inputMap.put(StringConstantsUtil.OPERATION, operation);
            String query = CommonUtil.getQuery(inputMap, "ad.tempItemSearchCount");
            try {
                results = discountDAO.getRebates(query);
            } catch (SystemException ex) {
                LoggerFactory.getLogger(DiscountLogic.class.getName()).error("", ex);
            } catch (Exception ex) {
                LoggerFactory.getLogger(DiscountLogic.class.getName()).error("", ex);
            }
        } else if (Constants.IndicatorConstants.CFP.getConstant().equals(newDiscountTabDto.getCategory())) {
            if (selectedItemsTable && !isAdd) {
                operation = Constants.IndicatorConstants.SELECTED_COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
            } else {
                operation = Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
            }
            inputMap.put(StringConstantsUtil.OPERATION, operation);
            String query = CommonUtil.getQuery(inputMap, "ad.selectedCompaniesFromSearchTableCount");
            try {
                results = discountDAO.getRebates(query);
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        } else if (Constants.IndicatorConstants.IFP.toString().equals(newDiscountTabDto.getCategory())) {
             if (selectedItemsTable && !isAdd) {
                operation = Constants.IndicatorConstants.SELECTED_ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
            } else {
                operation = Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
            }
            inputMap.put(StringConstantsUtil.OPERATION, operation);
            String query = CommonUtil.getQuery(inputMap, "ad.selectedItemsFromSearchTableCount");
            try {
                results = discountDAO.getRebates(query);
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        }
        Object obj = null;
        if (!results.isEmpty()) {
            obj = results.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }
    
    public static void removeSelectedItems(ContractsDetailsDto newDiscountTabDto, SessionDTO sessionDTO) {
        String query = StringUtils.EMPTY;
        if (Constants.IndicatorConstants.CFP.getConstant().equals(newDiscountTabDto.getCategory())) {
            query = "UPDATE IMTD_CFP_DETAILS SET OPERATION = '" + Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant() + Constants.WHERE_USER_SID
                    + sessionDTO.getUserId() + Constants.AND_SESSION_ID + sessionDTO.getSearchSessionId() +  Constants.AND_OPERATION + Constants.IndicatorConstants.SELECTED_COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant() + "'" + Constants.AND_CHECK_RECORD;
        } else if (Constants.IndicatorConstants.IFP.getConstant().equals(newDiscountTabDto.getCategory())) {
            query = "UPDATE IMTD_IFP_DETAILS SET OPERATION = '" + Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant() + Constants.WHERE_USER_SID
                    + sessionDTO.getUserId() + Constants.AND_SESSION_ID + sessionDTO.getSearchSessionId() + Constants.AND_OPERATION + Constants.IndicatorConstants.SELECTED_ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant() + "'" + "AND CHECK_BOX = 1";
        } else if (Constants.IndicatorConstants.PS_VALUE.getConstant().equals(newDiscountTabDto.getCategory())) {
            query = "UPDATE IMTD_ITEM_PRICE_REBATE_DETAILS SET OPERATION = '" + Constants.IndicatorConstants.PS_IFP_FOR_ADD_DISCOUNT.getConstant() + Constants.WHERE_USER_SID
                    + sessionDTO.getUserId() + Constants.AND_SESSION_ID + sessionDTO.getSearchSessionId() + Constants.AND_OPERATION + Constants.IndicatorConstants.SELECTED_PS_IFP_FOR_ADD_DISCOUNT.getConstant() + "'" +  Constants.AND_CHECK_RECORD;
        } else if (Constants.IndicatorConstants.RS_VALUE.getConstant().equals(newDiscountTabDto.getCategory())) {
            query = "UPDATE IMTD_ITEM_PRICE_REBATE_DETAILS SET OPERATION = '" + Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant() + Constants.WHERE_USER_SID
                    + sessionDTO.getUserId() + Constants.AND_SESSION_ID + sessionDTO.getSearchSessionId() + Constants.AND_OPERATION + Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant() + "'" +  Constants.AND_CHECK_RECORD;
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public static List<ContractsDetailsDto> getCommonResults(ContractsDetailsDto newDiscountTabDto, SessionDTO sessionDTO, boolean isSelectedTable)  {
        LOGGER.debug("Inside getCommonResults ");
        List<ContractsDetailsDto> searchList;
        Map<String, String> inputMap = new HashMap<>();
        List results = new ArrayList();
        String operation = StringUtils.EMPTY;
        String searchField = newDiscountTabDto.getCategory();
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, sessionDTO.getUserId());
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, sessionDTO.getSearchSessionId());

        if (Constants.IndicatorConstants.PS_VALUE.getConstant().equals(newDiscountTabDto.getCategory()) || Constants.IndicatorConstants.RS_VALUE.getConstant().equals(newDiscountTabDto.getCategory())) {
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.getConstant())) {
                if (isSelectedTable) {
                    operation = Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant();
                } else {
                    operation = Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant();
                }
            }
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.getConstant())) {
                if (isSelectedTable) {
                    operation = Constants.IndicatorConstants.SELECTED_PS_IFP_FOR_ADD_DISCOUNT.getConstant();
                } else {
                    operation = Constants.IndicatorConstants.PS_IFP_FOR_ADD_DISCOUNT.getConstant();
                }
            }
            inputMap.put(StringConstantsUtil.OPERATION, operation);
            String query = CommonUtil.getQuery(inputMap, "ad.tempItemSearch");
            query += "ORDER BY IMTD_ITEM_PRICE_REBATE_SID";
            if (newDiscountTabDto.getEndIndex() > 0) {
                query += SPACE_OFFSET_SPACE + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            }
            results = discountDAO.getRebates(query);
        }

        if (Constants.IndicatorConstants.CFP.getConstant().equals(newDiscountTabDto.getCategory())) {
            if (isSelectedTable) {
                operation = Constants.IndicatorConstants.SELECTED_COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
            } else {
                operation = Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
            }
            inputMap.put(StringConstantsUtil.OPERATION, operation);
            String query = CommonUtil.getQuery(inputMap, "ad.selectedCompaniesFromSearchTable");
            query += "ORDER BY IMTD_CFP_DETAILS_SID ";
            if (newDiscountTabDto.getEndIndex() > 0) {
                query += SPACE_OFFSET_SPACE + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            }
            results = discountDAO.getRebates(query);
        } else if (Constants.IndicatorConstants.IFP.getConstant().equals(newDiscountTabDto.getCategory())) {
            if (isSelectedTable) {
                operation = Constants.IndicatorConstants.SELECTED_ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
            } else {
                operation = Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
            }
            inputMap.put(StringConstantsUtil.OPERATION, operation);
            String query = CommonUtil.getQuery(inputMap, "ad.selectedItemsFromSearchTable");
            query += "ORDER BY IMTD_IFP_DETAILS_SID ";
            if (newDiscountTabDto.getEndIndex() > 0) {
                query += SPACE_OFFSET_SPACE + newDiscountTabDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + newDiscountTabDto.getEndIndex() + ROWS_ONLY;
            }
            results = discountDAO.getRebates(query);
        }
        searchList = getDetailsFromSelectedResult(results, newDiscountTabDto);
        LOGGER.debug("Exting getCommonResults ");
        return searchList;
    }

    private static List<ContractsDetailsDto> getDetailsFromSelectedResult(List results, ContractsDetailsDto newDiscountTabDto) {
        List<ContractsDetailsDto> searchList = new ArrayList<>();

        int size = results.size();
        if (Constants.IndicatorConstants.RS_VALUE.getConstant().equals(newDiscountTabDto.getCategory()) || Constants.IndicatorConstants.PS_VALUE.getConstant().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setSystemId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setId(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setItemId(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                tabDto.setItemNo(Converters.convertNullToEmpty(arr[NumericConstants.FOUR]));
                tabDto.setItemName(Converters.convertNullToEmpty(arr[NumericConstants.FIVE]));
                tabDto.setTherapyClass(Converters.convertNullToEmpty(arr[NumericConstants.SIX]));
                tabDto.setBrand(Converters.convertNullToEmpty(arr[NumericConstants.EIGHT]));
                tabDto.setHelperTableSid(Converters.convertNullToEmpty(arr[NumericConstants.FIVE]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.SEVEN]));
                tabDto.setTempStatus(Converters.convertNullToEmpty(arr[NumericConstants.SEVEN]));
                Date dt1 = !String.valueOf(arr[NumericConstants.NINE]).equals(Constants.NULL) ? new Date(CommonLogic.convertDateFormat(arr[NumericConstants.NINE].toString(), DEFOULT_SQL_DATE_FORMAT.getConstant(), MMDDYYYY.getConstant())) : null;

                tabDto.setsDate(dt1);
                tabDto.setTempSDate(dt1);
                Date dt2 = !String.valueOf(arr[NumericConstants.TEN]).equals(Constants.NULL) ? new Date(CommonLogic.convertDateFormat(arr[NumericConstants.TEN].toString(), DEFOULT_SQL_DATE_FORMAT.getConstant(), MMDDYYYY.getConstant())) : null;

                tabDto.seteDate(dt2);
                tabDto.setTempEDate(dt2);
                tabDto.setRebatePlan(Converters.convertNullToEmpty(arr[NumericConstants.ELEVEN]));
                tabDto.setTempRebatePlan(Converters.convertNullToEmpty(arr[NumericConstants.ELEVEN]));
                tabDto.setRebatePlanName(Converters.convertNullToEmpty(arr[NumericConstants.TWELVE]));
                tabDto.setFormulaId(Converters.convertNullToEmpty(arr[NumericConstants.THIRTEEN]));
                tabDto.setTempFormulaId(Converters.convertNullToEmpty(arr[NumericConstants.THIRTEEN]));
                tabDto.setFormulaName(Converters.convertNullToEmpty(arr[NumericConstants.FOURTEEN]));
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[1])));
                tabDto.setCategory(newDiscountTabDto.getCategory());
                tabDto.setCheckRecord(Boolean.valueOf(Converters.convertNullToEmpty(arr[NumericConstants.FIFTEEN])));
                searchList.add(tabDto);
            }
        } else if (Constants.IndicatorConstants.IFP.getConstant().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setSystemId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setItemNo(Converters.convertNullToEmpty(arr[1]));
                tabDto.setItemName(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setTherapyClass(Converters.convertNullToEmpty(arr[NumericConstants.SIX]));
                tabDto.setBrand(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));

                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.FOUR]));
                tabDto.setTempStatus(Converters.convertNullToEmpty(arr[NumericConstants.FOUR]));
                Date dt1 = !String.valueOf(arr[NumericConstants.SEVEN]).equals(Constants.NULL) ? new Date(CommonLogic.convertDateFormat(arr[NumericConstants.SEVEN].toString(), DEFOULT_SQL_DATE_FORMAT.getConstant(), MMDDYYYY.getConstant())) : null;

                tabDto.setsDate(dt1);
                tabDto.setTempSDate(dt1);
                Date dt2 = !String.valueOf(arr[NumericConstants.EIGHT]).equals(Constants.NULL) ? new Date(CommonLogic.convertDateFormat(arr[NumericConstants.EIGHT].toString(), DEFOULT_SQL_DATE_FORMAT.getConstant(), MMDDYYYY.getConstant())) : null;

                tabDto.seteDate(dt2);
                tabDto.setTempEDate(dt2);
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[NumericConstants.TEN])));
                tabDto.setCategory(newDiscountTabDto.getCategory());
                tabDto.setCheckRecord(Boolean.valueOf(Converters.convertNullToEmpty(arr[NumericConstants.NINE])));
                searchList.add(tabDto);
            }
        } else if (Constants.IndicatorConstants.CFP.toString().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object[] arr = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setSystemId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setCompanyNo(Converters.convertNullToEmpty(arr[1]));
                tabDto.setCompanyName(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                tabDto.setTempStatus(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                Date dt1 = !String.valueOf(arr[NumericConstants.FOUR]).equals(Constants.NULL) ? new Date(CommonLogic.convertDateFormat(arr[NumericConstants.FOUR].toString(), DEFOULT_SQL_DATE_FORMAT.getConstant(), MMDDYYYY.getConstant())) : null;

                tabDto.setsDate(dt1);
                tabDto.setTempSDate(dt1);
                Date dt2 = !String.valueOf(arr[NumericConstants.FIVE]).equals(Constants.NULL) ? new Date(CommonLogic.convertDateFormat(arr[NumericConstants.FIVE].toString(), DEFOULT_SQL_DATE_FORMAT.getConstant(), MMDDYYYY.getConstant())) : null;

                tabDto.seteDate(dt2);
                tabDto.setTempEDate(dt2);
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[NumericConstants.SEVEN])));
                tabDto.setCategory(newDiscountTabDto.getCategory());
                tabDto.setCheckRecord(Boolean.valueOf(Converters.convertNullToEmpty(arr[NumericConstants.SIX])));
                searchList.add(tabDto);
            }
        }

        return searchList;

    }

    public List<HelperDTO> getDropDownList(final String listType, HelperDTO dto)  {
        final List<HelperDTO> helperList = new ArrayList<>();
        final DynamicQuery helperTableQuery = HelperTableLocalServiceUtil.dynamicQuery();
        helperTableQuery.add(RestrictionsFactoryUtil.like(Constants.LIST_NAME, listType));
        helperTableQuery.addOrder(OrderFactoryUtil.asc(Constants.DESCRIPTION));
        final List<HelperTable> list = HelperTableLocalServiceUtil.dynamicQuery(helperTableQuery);
        helperList.add(dto);
        if (list != null) {
            for (HelperTable temp : list) {
                final HelperTable helperTable = (HelperTable) temp;
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(),
                        helperTable.getDescription()));
            }
        }
        return helperList;
    }

    public static int saveNewRebateToRsModel(ContractsDetailsDto saveDto, String userId, String tempSessionId) {
        RsModel rsModel = RsModelLocalServiceUtil.createRsModel(0);
        rsModel.setRsId(saveDto.getId());
        rsModel.setRsName(saveDto.getName());
        rsModel.setRsNo(saveDto.getNumber());
        rsModel.setPaymentFrequency(Integer.parseInt(saveDto.getPaymentFrequency()));
        rsModel.setRsStatus(Integer.parseInt(saveDto.getStatus()));
        rsModel.setRsStartDate(new Date(saveDto.getStartDate()));
        rsModel.setRsEndDate(new Date(saveDto.getEndDate()));
        rsModel.setPaymentMethod(Integer.parseInt(saveDto.getPaymentMethod()));
        rsModel.setRsType(Integer.parseInt(saveDto.getRebateType()));
        rsModel.setRebateProgramType(Integer.parseInt(saveDto.getRebateProgramType()));
        rsModel.setRebatePlanLevel(saveDto.getRebatePlan());
        rsModel.setCreatedDate(new Date());
        rsModel.setCreatedBy(Integer.parseInt(userId));
        rsModel.setModifiedDate(new Date());
        rsModel.setModifiedBy(Integer.parseInt(userId));
        try {
            rsModel = RsModelLocalServiceUtil.addRsModel(rsModel);
            saveIntoRsDetails(rsModel, userId, tempSessionId);
        } catch (SystemException ex) {
            LOGGER.error("",ex);
        }

        return rsModel.getRsModelSid();

    }

    public int getFormulaSearchCount(LookupDTO binderDto)  {
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.FORMULA_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.FORMULA_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.FORMULA_DESC_QUESTION, "%");
        List results;
        if (StringUtils.isNotBlank(binderDto.getFormulaId())) {
            inputMap.put(StringConstantsUtil.FORMULA_ID_QUESTION, CommonUtil.astToPerConverter(binderDto.getFormulaId()));
        }
        if (StringUtils.isNotBlank(binderDto.getFormulaNo())) {
            inputMap.put(StringConstantsUtil.FORMULA_NO_QUESTION, CommonUtil.astToPerConverter(binderDto.getFormulaNo()));
        }
        if (StringUtils.isNotBlank(binderDto.getFormulaName())) {
            inputMap.put(StringConstantsUtil.FORMULA_DESC_QUESTION, CommonUtil.astToPerConverter(binderDto.getFormulaName()));
        }
        String query = CommonUtil.getQuery(inputMap, "ad.formulaSearchCount");
        results = discountDAO.getRebates(query);
        Object obj = null;
        if (!results.isEmpty()) {
            obj = results.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    public List<LookupDTO> getFormulaSearchResults(LookupDTO binderDto)  {
        Map<String, String> inputMap = new HashMap<>();
        List<LookupDTO> resultsList = new ArrayList<>();
        inputMap.put(StringConstantsUtil.FORMULA_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.FORMULA_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.FORMULA_DESC_QUESTION, "%");
        List results;
        if (StringUtils.isNotBlank(binderDto.getFormulaId())) {
            inputMap.put(StringConstantsUtil.FORMULA_ID_QUESTION, CommonUtil.astToPerConverter(binderDto.getFormulaId()));
        }
        if (StringUtils.isNotBlank(binderDto.getFormulaNo())) {
            inputMap.put(StringConstantsUtil.FORMULA_NO_QUESTION, CommonUtil.astToPerConverter(binderDto.getFormulaNo()));
        }
        if (StringUtils.isNotBlank(binderDto.getFormulaName())) {
            inputMap.put(StringConstantsUtil.FORMULA_DESC_QUESTION, CommonUtil.astToPerConverter(binderDto.getFormulaName()));
        }
        String query = CommonUtil.getQuery(inputMap, "ad.formulaSearch");
        query += " ORDER BY FORMULA_DETAILS_MASTER_SID OFFSET " + binderDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + binderDto.getEndIndex() + " ROWS ONLY ;";
        results = discountDAO.getRebates(query);
        if (!results.isEmpty()) {
            resultsList = getLookUpDtos(results, false);
        }
        return resultsList;
    }

    private List<LookupDTO> getLookUpDtos(List results, boolean isRebate) {
        int size = results.size();
        List<LookupDTO> resultsList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Object[] arr = (Object[]) results.get(i);
            LookupDTO dTo = new LookupDTO();
            if (isRebate) {
                dTo.setRebatePlanSysId(Converters.convertNullToEmpty(arr[0]));
                dTo.setRebatePlanId(Converters.convertNullToEmpty(arr[1]));
                dTo.setRebatePlanNo(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                dTo.setRebatePlanName(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
                dTo.setRebatePlanStatus(Converters.convertNullToEmpty(arr[NumericConstants.FOUR]));
                dTo.setRebatePlanType(Converters.convertNullToEmpty(arr[NumericConstants.FIVE]));
            } else {
                dTo.setFormulaSysId(Converters.convertNullToEmpty(arr[0]));
                dTo.setFormulaId(Converters.convertNullToEmpty(arr[1]));
                dTo.setFormulaNo(Converters.convertNullToEmpty(arr[NumericConstants.TWO]));
                dTo.setFormulaName(Converters.convertNullToEmpty(arr[NumericConstants.THREE]));
            }

            resultsList.add(dTo);

        }
        return resultsList;
    }

    public int getRebateSearchCount(LookupDTO binderDto)  {
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.REBATE_PLAN_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.REBATE_PLAN_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.REBATE_PLAN_NAME_QUESTION, "%");
        inputMap.put(StringConstantsUtil.REBATE_PLAN_STATUS_QUESTION, "%");
        inputMap.put(StringConstantsUtil.REBATE_PLAN_TYPE_QUESTION, "%");
        List results;
        if (StringUtils.isNotBlank(binderDto.getRebatePlanId())) {
            inputMap.put(StringConstantsUtil.REBATE_PLAN_ID_QUESTION, CommonUtil.astToPerConverter(binderDto.getRebatePlanId()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanNo())) {
            inputMap.put(StringConstantsUtil.REBATE_PLAN_NO_QUESTION, CommonUtil.astToPerConverter(binderDto.getRebatePlanNo()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanName())) {
            inputMap.put(StringConstantsUtil.REBATE_PLAN_NAME_QUESTION, CommonUtil.astToPerConverter(binderDto.getRebatePlanName()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanStatus())) {
            inputMap.put(StringConstantsUtil.REBATE_PLAN_STATUS_QUESTION, CommonUtil.astToPerConverter(binderDto.getRebatePlanStatus()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanType())) {
            inputMap.put(StringConstantsUtil.REBATE_PLAN_TYPE_QUESTION, CommonUtil.astToPerConverter(binderDto.getRebatePlanType()));
        }
        String query = CommonUtil.getQuery(inputMap, "ad.rebateSearchCount");
        results = discountDAO.getRebates(query);
        Object obj = null;
        if (!results.isEmpty()) {
            obj = results.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    public List<LookupDTO> getRebateSearchResults(LookupDTO binderDto)  {
        Map<String, String> inputMap = new HashMap<>();
        List<LookupDTO> resultsList = new ArrayList<>();
        inputMap.put(StringConstantsUtil.REBATE_PLAN_ID_QUESTION, "%");
        inputMap.put(StringConstantsUtil.REBATE_PLAN_NO_QUESTION, "%");
        inputMap.put(StringConstantsUtil.REBATE_PLAN_NAME_QUESTION, "%");
        inputMap.put(StringConstantsUtil.REBATE_PLAN_STATUS_QUESTION, "%");
        inputMap.put(StringConstantsUtil.REBATE_PLAN_TYPE_QUESTION, "%");
        List results;
        if (StringUtils.isNotBlank(binderDto.getRebatePlanId())) {
            inputMap.put(StringConstantsUtil.REBATE_PLAN_ID_QUESTION, CommonUtil.astToPerConverter(binderDto.getRebatePlanId()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanNo())) {
            inputMap.put(StringConstantsUtil.REBATE_PLAN_NO_QUESTION, CommonUtil.astToPerConverter(binderDto.getRebatePlanNo()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanName())) {
            inputMap.put(StringConstantsUtil.REBATE_PLAN_NAME_QUESTION, CommonUtil.astToPerConverter(binderDto.getRebatePlanName()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanStatus())) {
            inputMap.put(StringConstantsUtil.REBATE_PLAN_STATUS_QUESTION, CommonUtil.astToPerConverter(binderDto.getRebatePlanStatus()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanType())) {
            inputMap.put(StringConstantsUtil.REBATE_PLAN_TYPE_QUESTION, CommonUtil.astToPerConverter(binderDto.getRebatePlanType()));
        }
        String query = CommonUtil.getQuery(inputMap, "ad.rebateSearch");
        query += " ORDER BY REBATE_PLAN_MASTER_SID OFFSET " + binderDto.getStartIndex() + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + binderDto.getEndIndex() + " ROWS ONLY ;";
        results = discountDAO.getRebates(query);
        if (!results.isEmpty()) {
            resultsList = getLookUpDtos(results, true);
        }
        return resultsList;
    }

    public boolean getActuals(RemoveDiscountDto dto) {
        boolean actual = false;
        try {
            String actualQuery = queryUtils.getActuals(dto);
            List results;
            results = discountDAO.getRebates(actualQuery);
            Object[] arr = (Object[]) results.get(0);
            if (arr[0] != null || arr[0] != null) {
                actual = true;
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }

        return actual;
    }

    public static void saveIntoRsDetails(RsModel rsModel, String userId, String sessionId) {
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, userId);
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, sessionId);
        inputMap.put("?RS_MODEL_SID?", String.valueOf(rsModel.getRsModelSid()));
        inputMap.put(StringConstantsUtil.CREATED_DATE_QUESTION, CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put(StringConstantsUtil.MODIFIED_DATE_QUESTION, CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?ITEM_RS_ATTACHED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put(StringConstantsUtil.OPERATION, Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant());
        String query = CommonUtil.getQuery(inputMap, "ad.insertintoRsDetailsFromTemp");
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        deleteTempData(userId, sessionId, Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
    }

    public static int saveNewPriceToPsModel(ContractsDetailsDto saveDto, String userId, String searchSessionId) {
        PsModel rsModel = PsModelLocalServiceUtil.createPsModel(0);
        rsModel.setPsId(saveDto.getId());
        rsModel.setPsName(saveDto.getName());
        rsModel.setPsNo(saveDto.getNumber());
        rsModel.setPsStatus(Integer.parseInt(saveDto.getStatus()));
        rsModel.setPsStartDate(new Date(saveDto.getStartDate()));
        rsModel.setInboundStatus("A");
        rsModel.setCreatedDate(new Date());
        rsModel.setCreatedBy(Integer.parseInt(userId));
        rsModel.setModifiedDate(new Date());
        rsModel.setModifiedBy(Integer.parseInt(userId));
        try {
            rsModel = PsModelLocalServiceUtil.addPsModel(rsModel);
        } catch (SystemException ex) {
            LOGGER.error("",ex);
        }
        saveIntoPsDetails(rsModel, userId, searchSessionId);
        return rsModel.getPsModelSid();

    }

    public static int saveNewIfpToIFPModel(ContractsDetailsDto saveDto, String userId, String searchSessionId) {
        IfpModel ifpmodel = IfpModelLocalServiceUtil.createIfpModel(0);
        ifpmodel.setIfpId(saveDto.getId());
        ifpmodel.setIfpName(saveDto.getName());
        ifpmodel.setIfpNo(saveDto.getNumber());
        ifpmodel.setIfpStatus(Integer.parseInt(saveDto.getStatus()));
        ifpmodel.setIfpStartDate(new Date(saveDto.getStartDate()));
        ifpmodel.setInboundStatus("A");
        ifpmodel.setCreatedDate(new Date());
        ifpmodel.setCreatedBy(Integer.parseInt(userId));
        ifpmodel.setModifiedDate(new Date());
        ifpmodel.setModifiedBy(Integer.parseInt(userId));
        try {
            ifpmodel = IfpModelLocalServiceUtil.addIfpModel(ifpmodel);
            saveIntoIfpDetails(ifpmodel, userId, searchSessionId);
        } catch (SystemException ex) {
            LOGGER.error("",ex);
        }
        return ifpmodel.getIfpModelSid();
    }

    public static void saveIntoIfpDetails(IfpModel ifpmodel, String userId, String sessionId) {
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, userId);
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, sessionId);
        inputMap.put("?IFP_MODEL_SID?", String.valueOf(ifpmodel.getIfpModelSid()));
        inputMap.put(StringConstantsUtil.CREATED_DATE_QUESTION, CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put(StringConstantsUtil.MODIFIED_DATE_QUESTION, CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?ITEM_IFP_ATTACHED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put(StringConstantsUtil.OPERATION, Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
        String query = CommonUtil.getQuery(inputMap, "ad.insertintoIFPDetailsFromTemp");
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        deleteTempData(userId, sessionId, Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
    }

    public static void saveIntoPsDetails(PsModel psModel, String userId, String sessionId) {
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, userId);
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, sessionId);
        inputMap.put("?PS_MODEL_SID?", String.valueOf(psModel.getPsModelSid()));
        inputMap.put(StringConstantsUtil.CREATED_DATE_QUESTION, CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put(StringConstantsUtil.MODIFIED_DATE_QUESTION, CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put(StringConstantsUtil.OPERATION, Constants.IndicatorConstants.PS_IFP_FOR_ADD_DISCOUNT.getConstant());

        String query = CommonUtil.getQuery(inputMap, "ad.insertintoPsDetailsFromTemp");
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        deleteTempData(userId, sessionId, Constants.IndicatorConstants.PS_IFP_FOR_ADD_DISCOUNT.getConstant());
    }

    public static int saveNewCfpToCFPModel(ContractsDetailsDto saveDto, String userId, String searchSessionId) {
        CfpModel cfpmodel = CfpModelLocalServiceUtil.createCfpModel(0);
        cfpmodel.setCfpId(saveDto.getId());
        cfpmodel.setCfpName(saveDto.getName());
        cfpmodel.setCfpNo(saveDto.getNumber());
        cfpmodel.setCfpStatus(Integer.parseInt(saveDto.getStatus()));
        cfpmodel.setCfpStartDate(new Date(saveDto.getStartDate()));
        cfpmodel.setInboundStatus("A");
        cfpmodel.setCreatedDate(new Date());
        cfpmodel.setCreatedBy(Integer.parseInt(userId));
        cfpmodel.setModifiedDate(new Date());
        cfpmodel.setModifiedBy(Integer.parseInt(userId));
        try {
            cfpmodel = CfpModelLocalServiceUtil.addCfpModel(cfpmodel);
            saveIntoCfpDetails(cfpmodel, userId, searchSessionId);
        } catch (SystemException ex) {
            LOGGER.error("",ex);
        }
        return cfpmodel.getCfpModelSid();
    }

    public static void saveIntoCfpDetails(CfpModel cfpmodel, String userId, String sessionId) {
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, userId);
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, sessionId);
        inputMap.put("?CFP_MODEL_SID?", String.valueOf(cfpmodel.getCfpModelSid()));
        inputMap.put(StringConstantsUtil.CREATED_DATE_QUESTION, CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put(StringConstantsUtil.MODIFIED_DATE_QUESTION, CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?COMPANY_CFP_ATTACHED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put(StringConstantsUtil.OPERATION, Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
        String query = CommonUtil.getQuery(inputMap, "ad.insertintoCFPDetailsFromTemp");
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        deleteTempData(userId, sessionId, Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
    }

    /**
     * Method used for savePS.
     *
     * @param contractSystemId the contract system id
     * @param cfpSystemId the cfp system id
     * @param ifpSystemId the ifp system id
     * @param contractMember the contract member
     */
    public static void savePS(final int contractSystemId, final int cfpSystemId, final int ifpSystemId, final ContractsDetailsDto contractMember,boolean flag) throws PortalException {
        LOGGER.debug("Entering getChildNodeOfPriorContractSales method with contractSystemId=" + contractSystemId +"contractMember.getModelSysId()=========="+contractMember.getModelSysId());
        try {
           int psId = flag ? Integer.valueOf(contractMember.getPsSid()) : contractMember.getModelSysId();
            final PsModel priceSchedule = discountDAO.getPriceScheduleMaster(psId);
            final PsContract psMasterAttached = PsContractLocalServiceUtil.createPsContract(0);
            psMasterAttached.setContractMasterSid(contractSystemId);
            psMasterAttached.setCfpContractSid((cfpSystemId != 0) ? String.valueOf(cfpSystemId) : null);
            psMasterAttached.setIfpContractSid((ifpSystemId != 0) ? String.valueOf(ifpSystemId) : null);
            psMasterAttached.setPsModelSid(psId);

            psMasterAttached.setPsName(priceSchedule.getPsName());
            psMasterAttached.setPsType(priceSchedule.getPsType());
            psMasterAttached.setPsCategory(priceSchedule.getPsCategory());
            psMasterAttached.setPsDesignation(priceSchedule.getPsDesignation());
            psMasterAttached.setPsStatus(priceSchedule.getPsStatus());
            psMasterAttached.setPsStartDate(priceSchedule.getPsStartDate());
            psMasterAttached.setPsEndDate(priceSchedule.getPsEndDate());
            psMasterAttached.setPsContractAttachedDate(new Date());

            psMasterAttached.setCreatedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
            psMasterAttached.setCreatedDate(new Date());
            psMasterAttached.setModifiedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
            psMasterAttached.setModifiedDate(new Date());
            psMasterAttached.setRecordLockStatus(false);
            psMasterAttached.setInboundStatus("A");

            DynamicQuery query = PsContractLocalServiceUtil.dynamicQuery();
            query.add(RestrictionsFactoryUtil.eq(CONTRACT_MASTER_SID.getConstant(), contractSystemId));
            if (cfpSystemId != 0) {
                query.add(RestrictionsFactoryUtil.ilike(Constants.CFP_CONTRACT_SID, String.valueOf(cfpSystemId)));
            } else {
                query.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
            }
            if (ifpSystemId != 0) {
                query.add(RestrictionsFactoryUtil.ilike(Constants.IFP_CONTRACT_SID, String.valueOf(ifpSystemId)));
            } else {
                query.add(RestrictionsFactoryUtil.isNull(Constants.IFP_CONTRACT_SID));
            }

            query.add(RestrictionsFactoryUtil.eq("psModelSid", psId));
            List<PsContract> list = PsContractLocalServiceUtil.dynamicQuery(query);
            PsContract psContract = PsContractLocalServiceUtil.createPsContract(0);

            if (list.isEmpty()) {
                psMasterAttached.setContractMasterSid(contractSystemId);
                psMasterAttached.setCfpContractSid((cfpSystemId != 0) ? String.valueOf(cfpSystemId) : null);
                psMasterAttached.setIfpContractSid((ifpSystemId != 0) ? String.valueOf(ifpSystemId) : null);
                psMasterAttached.setPsModelSid(psId);

                psContract = discountDAO.addPsMasterAttached(psMasterAttached);
                contractMember.setPsContractId(psContract.getPsContractSid());
            } else {
                contractMember.setPsContractId(list.get(0).getPsContractSid());
            }

            List<Object> input = new ArrayList<>(NumericConstants.EIGHT);
            input.add(psContract.getPsContractSid());
            input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            input.add(DBDate.format(new Date()));
            input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
            input.add(DBDate.format(new Date()));
            input.add(psId);
            input.add(DBDate.format(priceSchedule.getPsStartDate()));
            if (priceSchedule.getPsEndDate() != null) {
                input.add(DBDate.format(priceSchedule.getPsEndDate()));
            } else {
                input.add(null);
            }
            if (list.isEmpty()) {
                PsContractDetailsImpl.savePsDetailsAttached(input, null);
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    public static void deleteTempData(String userId, String sessionId, String operation) {
        Map<String, String> inputMap = new HashMap<>();
        if (operation.equalsIgnoreCase(Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant())) {
            inputMap.put(StringConstantsUtil.TABLE_NAME_QUESTION, StringConstantsUtil.IMTD_IFP_DETAILS);
        } else if (operation.equalsIgnoreCase(Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant())) {
            inputMap.put(StringConstantsUtil.TABLE_NAME_QUESTION, StringConstantsUtil.IMTD_CFP_DETAILS);
        } else {
            inputMap.put(StringConstantsUtil.TABLE_NAME_QUESTION, StringConstantsUtil.IMTD_ITEM_PRICE_REBATE_DETAILS);
        }
        inputMap.put(StringConstantsUtil.COLUMN_NAME_QUESTION, StringConstantsUtil.USERS_SID_COLUMN);
        inputMap.put(StringConstantsUtil.COLUMN_NAM_E2_QUESTION, StringConstantsUtil.SESSION_ID_COLUMN);
        inputMap.put(StringConstantsUtil.COLUMN_NAM_E3_QUESTION, StringConstantsUtil.OPERATION_COLUMN);
        inputMap.put(StringConstantsUtil.VALU_E1_QUESTION, userId);
        inputMap.put(StringConstantsUtil.VALU_E2_QUESTION, sessionId);
        inputMap.put(StringConstantsUtil.VALU_E3_QUESTION, "%" + operation);
        String query = CommonUtil.getQuery(inputMap, StringConstantsUtil.ADTEMP_DATA_DELETE);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public static List duplicateCheck(String operation, String name, String fieldName) {
        List list = null;
        String query = StringUtils.EMPTY;
        if (operation.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            query = "select * from dbo.PS_MODEL where " + fieldName + " = '" + name + "'" + " and INBOUND_STATUS <> 'D'";
        } else if (operation.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            query = "select * from dbo.CFP_MODEL where " + fieldName + " = '" + name + "'" + " and INBOUND_STATUS <> 'D'";
        } else if (operation.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            query = "select * from dbo.IFP_MODEL where " + fieldName + " = '" + name + "'" + "and INBOUND_STATUS <> 'D'";
        } else if (operation.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
            query = "select * from dbo.RS_MODEL where " + fieldName + " = '" + name + "'" + "and INBOUND_STATUS <> 'D'";
        }
        list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list;

    }

    public static void saveIFP(final int contractSystemId, final int cfpSystemId, final ContractsDetailsDto contractMember,boolean flag) throws PortalException {

        LOGGER.debug("Entering saveIFP method with contractSystemId=" + contractSystemId +"contractMember.getModelSysId()-------------"+contractMember.getModelSysId());
        int ifpId = flag ? contractMember.getIfpId() : contractMember.getModelSysId();

        final IfpModel itemFamily = discountDAO.getItemFamilyPlanMaster(ifpId);
        final IfpContract ifpMasterAttached = IfpContractLocalServiceUtil.createIfpContract(0);

        ifpMasterAttached.setIfpModelSid(contractMember.getModelSysId());
        ifpMasterAttached.setIfpName(contractMember.getName());
        ifpMasterAttached.setIfpType(itemFamily.getIfpType());
        ifpMasterAttached.setIfpCategory(itemFamily.getIfpCategory());
        ifpMasterAttached.setIfpDesignation(itemFamily.getIfpDesignation());
        ifpMasterAttached.setParentIfpId(itemFamily.getParentIfpId());
        ifpMasterAttached.setParentIfpName(itemFamily.getParentIfpName());
        ifpMasterAttached.setIfpStatus(itemFamily.getIfpStatus());
        ifpMasterAttached.setIfpStartDate(itemFamily.getIfpStartDate());
        ifpMasterAttached.setIfpEndDate(itemFamily.getIfpEndDate());
        ifpMasterAttached.setIfpContractAttachedDate(new Date());
        ifpMasterAttached.setCreatedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        ifpMasterAttached.setCreatedDate(new Date());
        ifpMasterAttached.setModifiedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        ifpMasterAttached.setModifiedDate(new Date());
        ifpMasterAttached.setRecordLockStatus(false);
        ifpMasterAttached.setInboundStatus("A");

        DynamicQuery query = IfpContractLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(CONTRACT_MASTER_SID.getConstant(), contractSystemId));
        if (cfpSystemId != 0) {
            query.add(RestrictionsFactoryUtil.ilike(Constants.CFP_CONTRACT_SID, String.valueOf(cfpSystemId)));
        } else {
            query.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        }
        query.add(RestrictionsFactoryUtil.eq("ifpModelSid",ifpId));
        List<IfpContract> list = IfpContractLocalServiceUtil.dynamicQuery(query);
        IfpContract ifpContract = IfpContractLocalServiceUtil.createIfpContract(0);

        if (list.isEmpty()) {
            ifpMasterAttached.setContractMasterSid(contractSystemId);

            ifpMasterAttached.setCfpContractSid((cfpSystemId != 0) ? String.valueOf(cfpSystemId) : null);

            ifpMasterAttached.setIfpModelSid(ifpId);

            ifpContract = discountDAO.addIfpMasterAttached(ifpMasterAttached);
            contractMember.setIfpContractId(ifpContract.getIfpContractSid());
        } else {
            contractMember.setIfpContractId(list.get(0).getIfpContractSid());
        }
        List<Object> input = new ArrayList<>(NumericConstants.EIGHT);
        input.add(ifpContract.getIfpContractSid());
        input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        input.add(DBDate.format(new Date()));
        input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        input.add(DBDate.format(new Date()));
        input.add(ifpId);
        input.add(DBDate.format(itemFamily.getIfpStartDate()));
        if (itemFamily.getIfpEndDate() != null) {
            input.add(DBDate.format(itemFamily.getIfpEndDate()));
        } else {
            input.add(null);
        }
        if (list.isEmpty()) {
            IfpContractDetailsImpl.saveIfpDetailsAttached(input, null);
        }

        LOGGER.debug("End of saveIFP method");
    }

    /**
     * to save CFp
     *
     * @param contractSystemId
     * @param contractMember
     * @
     * @throws PortalException
     */
    public static void saveCFp(final int contractSystemId, final ContractsDetailsDto contractMember , boolean flag) throws PortalException {
        LOGGER.debug("Entering saveCFp method with contractSystemId=" + contractSystemId + "======" + contractMember.getCfpId() + "contractMember.getModelSysId()" +contractMember.getModelSysId());
        int cpdId = flag ? contractMember.getCfpId() : contractMember.getModelSysId();
       
        final CfpModel companyFamily = discountDAO.getCompanyFamilyplanMaster(cpdId);
        final CfpContract cfpMasterAttached = CfpContractLocalServiceUtil.createCfpContract(0);
        cfpMasterAttached.setCfpName(contractMember.getName());
        cfpMasterAttached.setCfpType(companyFamily.getCfpType());
        cfpMasterAttached.setCfpCategory(companyFamily.getCfpCategory());
        cfpMasterAttached.setCfpDesignation(companyFamily.getCfpDesignation());
        cfpMasterAttached.setParentCfpId(companyFamily.getParentCfpId());
        cfpMasterAttached.setParentCfpName(companyFamily.getParentCfpName());
        cfpMasterAttached.setCfpStatus(companyFamily.getCfpStatus());
        cfpMasterAttached.setCfpTradeClass(companyFamily.getCfpTradeClass());
        cfpMasterAttached.setCfpStartDate(companyFamily.getCfpStartDate());
        cfpMasterAttached.setCfpEndDate(companyFamily.getCfpEndDate());
        cfpMasterAttached.setCreatedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        cfpMasterAttached.setCreatedDate(new Date());
        cfpMasterAttached.setModifiedBy(Integer.parseInt((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        cfpMasterAttached.setModifiedDate(new Date());
        cfpMasterAttached.setCfpContractAttachedDate(new Date());
        cfpMasterAttached.setRecordLockStatus(false);
        cfpMasterAttached.setInboundStatus("A");

        DynamicQuery query = CfpContractLocalServiceUtil.dynamicQuery();
        query.add(RestrictionsFactoryUtil.eq(CONTRACT_MASTER_SID.getConstant(), contractSystemId));
        query.add(RestrictionsFactoryUtil.eq("cfpModelSid", cpdId));
        List<CfpContract> list = CfpContractLocalServiceUtil.dynamicQuery(query);
        CfpContract cfpContract = CfpContractLocalServiceUtil.createCfpContract(0);

        if (list.isEmpty()) {
            cfpMasterAttached.setContractMasterSid(contractSystemId);
            cfpMasterAttached.setCfpModelSid(cpdId);
            cfpContract = discountDAO.addCfpMasterAttached(cfpMasterAttached);
            contractMember.setCfpContractId(cfpContract.getCfpContractSid());
        } else {
            contractMember.setCfpContractId(list.get(0).getCfpContractSid());
        }
        List<Object> input = new ArrayList<>(NumericConstants.EIGHT);
        input.add(cfpContract.getCfpContractSid());
        input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        input.add(DBDate.format(new Date()));
        input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        input.add(DBDate.format(new Date()));
        input.add(cpdId);
        input.add(DBDate.format(companyFamily.getCfpStartDate()));
        if (companyFamily.getCfpEndDate() != null) {
            input.add(DBDate.format(companyFamily.getCfpEndDate()));
        } else {
            input.add(null);
        }
        if (list.isEmpty()) {
            CfpContractDetailsImpl.saveCfpDetailsAttached(input, null);
        }
        LOGGER.debug("End of saveCFp method");

    }

    public void setIdDescription(List<String> idDesList, ComboBox ddlb) {
        ddlb.removeAllItems();
        ddlb.addItem(Constants.SHOW_ALL);
        ddlb.setNullSelectionItemId(Constants.SHOW_ALL);
        for (String dto : idDesList) {
            ddlb.addItem(dto);
            ddlb.setItemCaption(dto, dto);
        }
        ddlb.removeItem(Constants.SELECT_ONE);
        ddlb.select(Constants.SHOW_ALL);
    }    

    public int getSelectedContractCount(int userId, int sessionId, List<String> rebateList, 
            final String indicator, java.util.Set<Container.Filter> filterSet,
            ContractsDetailsDto dto,boolean summaryFlag) {
        int count = 0;
        String query = SQlUtil.getQuery("rd.selectedContractCount");
        StringBuilder filterSql = new StringBuilder();
        query = query.replace("[$USERID]", String.valueOf(userId));
        query = query.replace("[$SESSIONID]", String.valueOf(sessionId));
        if (summaryFlag) {
            query = query.replace("AND TEMP.CHECK_RECORD = 1", StringUtils.EMPTY);
        }
        if (!rebateList.isEmpty()) {
            query = query.replace("?", ItemQueries.getQuery(setRebateInput(rebateList), "rebateSub"));
        } else {
            query = query.replace('?', ' ');
        }
        if(dto != null && dto.getRemovedRsList() != null && !dto.getRemovedRsList().isEmpty()) {
            query = query.replace(StringConstantsUtil.RS_SID_CHECK, getRsContractFromRSContractList(dto));            
        } else {
            query = query.replace(StringConstantsUtil.RS_SID_CHECK, StringUtils.EMPTY);
        }
        if ("contract".equals(indicator)) {
            filterSql = AbstractFilterLogic.getInstance().contractfilterQueryGenerator(filterSet);
        } else if ("summary".equals(indicator)) {
            filterSql = AbstractFilterLogic.getInstance().summaryfilterQueryGenerator(filterSet);
        }
        if (filterSql != null) {
            query = query.replace(FILTER, filterSql.toString().replace("where", " and "));
        } else {
            query = query.replace(FILTER, " ");
        }
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        count = list != null ? Integer.parseInt(String.valueOf(list.get(0))) : 0;
        return count;
    }

    public List<RemoveDiscountDto> getSelectedContract(int start, int offset, int userId, int sessionId, List<String> rebateList,
            final String indicator,java.util.Set<Container.Filter> filterSet, ContractsDetailsDto dto, boolean summaryFlag) {
        List<RemoveDiscountDto> result;
        String query = SQlUtil.getQuery("rd.selectedContractData");
        StringBuilder filterSql = new StringBuilder();
        query = query.replace("[$USERID]", String.valueOf(userId));
        query = query.replace("[$SESSIONID]", String.valueOf(sessionId));
        query = query + SPACE_OFFSET_SPACE + start + StringConstantsUtil.SPACE_ROWS_FETCH_NEXT_SPACE + offset + ROWS_ONLY;
        if (summaryFlag) {
            query = query.replace("AND TEMP.CHECK_RECORD = 1", StringUtils.EMPTY);
        }
        if (!rebateList.isEmpty()) {
            query = query.replace("?", ItemQueries.getQuery(setRebateInput(rebateList), "rebateSub"));
        } else {
            query = query.replace('?', ' ');
        }
        if(dto.getRemovedRsList().isEmpty()){
            query = query.replace(StringConstantsUtil.RS_SID_CHECK, StringUtils.EMPTY);
        } else {
            query = query.replace(StringConstantsUtil.RS_SID_CHECK, getRsContractFromRSContractList(dto));
        }
        if (indicator.equals("contract")) {
            filterSql = AbstractFilterLogic.getInstance().contractfilterQueryGenerator(filterSet);
        } else if (indicator.equals("summary")) {
            filterSql = AbstractFilterLogic.getInstance().summaryfilterQueryGenerator(filterSet);
        }
        if (filterSql != null) {
            query = query.replace(FILTER, filterSql.toString().replace("where", " and "));
        } else {
            query = query.replace(FILTER, " ");
        }
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        result = setContractValues(list);
        return result;
    }

    public int getComponentCount(Object parent, String value) {
        int count = 0;
        String component = StringUtils.EMPTY;
        Map<String, String> inputMap = new HashMap<>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put(StringConstantsUtil.SID_QUESTION, String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
        }
        if (Constants.CFP.equalsIgnoreCase(value)) {
            component = "rd.cfpFromCDCount";
        } else if (Constants.IFP.equalsIgnoreCase(value)) {
            component = "rd.ifpFromCDCount";
        } else if (Constants.PS.equalsIgnoreCase(value)) {
            component = "rd.psFromCDCount";
        } else if (Constants.RS.equalsIgnoreCase(value)) {
            component = "rd.rsFromCDCount";
        }
        List resList = DAO.executeSelect(CommonUtil.getQuery(inputMap, component));

        count = resList != null && !resList.isEmpty() ? Integer.parseInt(String.valueOf(resList.get(0))) : 0;

        return count;
    }

    public List<CFPComponentDetailsDTO> getFromCfpCD(Object parent, int start, int offset) {
        List<CFPComponentDetailsDTO> retList = new ArrayList<>();
        Map<String, String> inputMap = new HashMap<>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put(StringConstantsUtil.SID_QUESTION, String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
            inputMap.put(StringConstantsUtil.START_QUESTION, String.valueOf(start));
            inputMap.put(StringConstantsUtil.OFFSET_QUESTION, String.valueOf(offset));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "rd.cfpFromCD"));
        for (Object[] temp : resList) {
            CFPComponentDetailsDTO tempDto = new CFPComponentDetailsDTO();
            tempDto.setCompanyNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setCompanyName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.THREE]))) ? null : DBDate.format((Date) temp[NumericConstants.THREE]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR]))) ? null : DBDate.format((Date) temp[NumericConstants.FOUR]));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.TWO])));
            retList.add(tempDto);
        }

        return retList;
    }

    public List<PSComponentDetailsDTO> getFromIfpCD(Object parent, int start, int offset) {
        List<PSComponentDetailsDTO> retList = new ArrayList<>();
        Map<String, String> inputMap = new HashMap<>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put(StringConstantsUtil.SID_QUESTION, String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
            inputMap.put(StringConstantsUtil.START_QUESTION, String.valueOf(start));
            inputMap.put(StringConstantsUtil.OFFSET_QUESTION, String.valueOf(offset));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "rd.ifpFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();

            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.THREE])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.TWO])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FIVE]))) ? null : DBDate.format((Date) temp[NumericConstants.FIVE]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.SIX]))) ? null : DBDate.format((Date) temp[NumericConstants.SIX]));
            retList.add(tempDto);
        }
        return retList;
    }

    public List<PSComponentDetailsDTO> getFromPsCD(Object parent, int start, int offset) {
        List<PSComponentDetailsDTO> retList = new ArrayList<>();
        Map<String, String> inputMap = new HashMap<>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put(StringConstantsUtil.SID_QUESTION, String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
            inputMap.put(StringConstantsUtil.START_QUESTION, String.valueOf(start));
            inputMap.put(StringConstantsUtil.OFFSET_QUESTION, String.valueOf(offset));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "rd.psFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.TWO])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.THREE])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FIVE]))) ? null : DBDate.format((Date) temp[NumericConstants.FIVE]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.SIX]))) ? null : DBDate.format((Date) temp[NumericConstants.SIX]));

            retList.add(tempDto);
        }
        return retList;
    }

    public List<PSComponentDetailsDTO> getFromRsCD(Object parent, int start, int offset) {
        List<PSComponentDetailsDTO> retList = new ArrayList<>();
        Map<String, String> inputMap = new HashMap<>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put(StringConstantsUtil.SID_QUESTION, String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
            inputMap.put(StringConstantsUtil.START_QUESTION, String.valueOf(start));
            inputMap.put(StringConstantsUtil.OFFSET_QUESTION, String.valueOf(offset));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "rd.rsFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.TWO])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.THREE])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FIVE]))) ? null : DBDate.format((Date) temp[NumericConstants.FIVE]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.SIX]))) ? null : DBDate.format((Date) temp[NumericConstants.SIX]));
            retList.add(tempDto);
        }
        return retList;
    }

    private List setRebateInput(final List<String> rebateList) {
        List input = new ArrayList();
        for (int i = 0; i < rebateList.size(); i++) {
            if (rebateList.get(i) != null && !StringUtils.EMPTY.equals(rebateList.get(i))) {
                input.add(rebateList.get(i));
            } else {
                input.add("%");
            }
        }
        return input;
    }

    public Date setFilterFormatted(final Object date) {

        Date resultDate = date != null ? (Date) date : new Date();
        try {
            String stringDate = CommonUtils.convertDateToString(resultDate);
            resultDate = df.parse(stringDate);
            return resultDate;
        } catch (ParseException ex) {
            LoggerFactory.getLogger(LookUpLogic.class.getName()).error("", ex);
        }
        return new Date();
    }
    
    public List<PSComponentDetailsDTO> getDiscountItemsForPS_RS(String UserId, String SessionId, List<String> itemsList) {
        LOGGER.debug(" Inside getDiscountItemsForPS");
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, UserId);
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, SessionId);
        inputMap.put("?ITEM_MASTER_SIDs?", CommonUtils.getListToString(itemsList));
        String query = CommonUtil.getQuery(inputMap, "ad.selectedItemsForNewPS_RS");
        List<PSComponentDetailsDTO> resultsList = new ArrayList<>();
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (int i = 0; i < list.size(); i++) {
            Object[] temp = (Object[]) list.get(i);
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.THREE])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(helperlist.getHelperDescription(Integer.parseInt(temp[NumericConstants.TWO].toString()))));
            tempDto.setStatus(CommonUtil.getPureValue(helperlist.getHelperDescription(Integer.parseInt(temp[NumericConstants.FOUR].toString()))));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FIVE]))) ? null : DBDate.format((Date) temp[NumericConstants.FIVE]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.SIX]))) ? null : DBDate.format((Date) temp[NumericConstants.SIX]));
            resultsList.add(tempDto);

        }
        LOGGER.debug(" Exiting getDiscountItemsForPS");
        return resultsList;
    }

    public List<PSComponentDetailsDTO> getDiscountItemsForIFP(String UserId, String SessionId, List<String> itemsList) {
        LOGGER.debug(" Inside getDiscountItemsForIFP");
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, UserId);
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, SessionId);
        inputMap.put("?ITEM_MASTER_SIDs?", CommonUtils.getListToString(itemsList));
        String query = CommonUtil.getQuery(inputMap, "ad.selectedItemsForNewIFP");
        List<PSComponentDetailsDTO> resultsList = new ArrayList<>();
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (int i = 0; i < list.size(); i++) {
            Object[] temp = (Object[]) list.get(i);
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.THREE])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.TWO])));
            tempDto.setStatus(CommonUtil.getPureValue(helperlist.getHelperDescription(Integer.parseInt(temp[NumericConstants.FOUR].toString()))));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FIVE]))) ? null : DBDate.format((Date) temp[NumericConstants.FIVE]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.SIX]))) ? null : DBDate.format((Date) temp[NumericConstants.SIX]));
            resultsList.add(tempDto);

        }
        LOGGER.debug(" Exiting getDiscountItemsForIFP");
        return resultsList;
    }

    public List<CFPComponentDetailsDTO> getDiscountItemsForCFP(String UserId, String SessionId, List<String> companyList) {
        LOGGER.debug(" Inside getDiscountItemsForCFP");
        List<CFPComponentDetailsDTO> resultsList = new ArrayList<>();
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, UserId);
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, SessionId);
        inputMap.put("?COMPANY_MASTER_SIDs?", CommonUtils.getListToString(companyList));
        String query = CommonUtil.getQuery(inputMap, "ad.selectedCompaniesForNewCFP");
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (int i = 0; i < list.size(); i++) {
            Object[] temp = (Object[]) list.get(i);
            CFPComponentDetailsDTO tempDto = new CFPComponentDetailsDTO();
            tempDto.setCompanyNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setCompanyName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setStatus(helperlist.getHelperDescription(CommonUtil.getIntValue(String.valueOf(temp[NumericConstants.TWO]))));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.THREE]))) ? null : DBDate.format((Date) temp[NumericConstants.THREE]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[NumericConstants.FOUR]))) ? null : DBDate.format((Date) temp[NumericConstants.FOUR]));
            resultsList.add(tempDto);

        }
        LOGGER.debug(" Exiting getDiscountItemsForCFP");
        return resultsList;
    }

    /**
     * Method used to update the operation for the data in Component Details -
     * Items search So as to fetch it in Component Details - Selected Items
     * table
     *
     * @param componentType Selected component type such as CFP, IFP, PS, RS
     * @param session sessionDto instance
     * @param isAddOperation true for add operation, false for remove operation
     */
    public void updateDataOperation(String componentType, SessionDTO sessionDTO, boolean isAddOperation) {
        LOGGER.debug("Inside updateDataOperation");
        Map<String, String> inputMap = new HashMap<>();
        String operation = "";
        String oldOperation = "";
        String tableName = "";
        String queryName = "";
        if (Constants.IndicatorConstants.CFP.getConstant().equals(componentType)) {
            if (isAddOperation) {
                oldOperation = Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
                operation = Constants.IndicatorConstants.SELECTED_COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
            } else {
                oldOperation = Constants.IndicatorConstants.SELECTED_COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
                operation = Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
            }
            tableName = StringConstantsUtil.IMTD_CFP_DETAILS;
            queryName = StringConstantsUtil.ADUPDATE_FOR_SELECTED_COMPANY_SEARCH_TABLE;
        } else if (Constants.IndicatorConstants.IFP.getConstant().equals(componentType)) {
            tableName = StringConstantsUtil.IMTD_IFP_DETAILS;
            if (isAddOperation) {
                oldOperation = Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
                operation = Constants.IndicatorConstants.SELECTED_ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
            } else {
                oldOperation = Constants.IndicatorConstants.SELECTED_ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
                operation = Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
            }
            queryName = "ad.updateForSelectedItemSearchTable";
        } else if (Constants.IndicatorConstants.PS_VALUE.getConstant().equals(componentType)) {

            if (isAddOperation) {
                oldOperation = Constants.IndicatorConstants.PS_IFP_FOR_ADD_DISCOUNT.getConstant();
                operation = Constants.IndicatorConstants.SELECTED_PS_IFP_FOR_ADD_DISCOUNT.getConstant();
            } else {
                oldOperation = Constants.IndicatorConstants.SELECTED_PS_IFP_FOR_ADD_DISCOUNT.getConstant();
                operation = Constants.IndicatorConstants.PS_IFP_FOR_ADD_DISCOUNT.getConstant();
            }
            tableName = StringConstantsUtil.IMTD_ITEM_PRICE_REBATE_DETAILS;
            queryName = StringConstantsUtil.ADUPDATE_FOR_SELECTED_COMPANY_SEARCH_TABLE;
        } else if (Constants.IndicatorConstants.RS_VALUE.getConstant().equals(componentType)) {
            if (isAddOperation) {
                oldOperation = Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant();
                operation = Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant();
            } else {
                oldOperation = Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant();
                operation = Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant();
            }
            tableName = StringConstantsUtil.IMTD_ITEM_PRICE_REBATE_DETAILS;
            queryName = StringConstantsUtil.ADUPDATE_FOR_SELECTED_COMPANY_SEARCH_TABLE;
        }
        inputMap.put(StringConstantsUtil.OPERATION, operation);
        inputMap.put("?OLD_OPERATION?", oldOperation);
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, sessionDTO.getUserId());
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, sessionDTO.getSearchSessionId());
        inputMap.put(StringConstantsUtil.TABLE_NAME_QUESTION, tableName);

        try {
            String query = CommonUtil.getQuery(inputMap, queryName);
            HelperTableLocalServiceUtil.executeUpdateQuery(query);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        LOGGER.debug("Exiting updateDataOperation");
    }

    /**
     * To get the Component Details - Selected Items table data
     *
     * @param category
     * @param session
     * @return
     */
    public List getSelectedTableData(String category, SessionDTO session) {
        LOGGER.debug("Inside getSelectedTableData");
        Map<String, String> inputMap = new HashMap<>();
        String operation = "";
        if (Constants.IndicatorConstants.CFP.getConstant().equals(category)) {
            operation = Constants.IndicatorConstants.SELECTED_COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
        } else if (Constants.IndicatorConstants.IFP.getConstant().equals(category)) {
            operation = Constants.IndicatorConstants.SELECTED_ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
        } else if (Constants.IndicatorConstants.PS_VALUE.getConstant().equals(category)) {
            operation = Constants.IndicatorConstants.SELECTED_PS_IFP_FOR_ADD_DISCOUNT.getConstant();
        } else if (Constants.IndicatorConstants.RS_VALUE.getConstant().equals(category)) {
            operation = Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant();
        }
        inputMap.put(StringConstantsUtil.OPERATION, operation);
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, session.getUserId());
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, session.getSearchSessionId());
        String query;
        if (Constants.IndicatorConstants.CFP.getConstant().equals(category)) {
            query = CommonUtil.getQuery(inputMap, "ad.selectedCompaniesFromSearchTable");
        } else if (Constants.IndicatorConstants.IFP.getConstant().equals(category)) {
            query = CommonUtil.getQuery(inputMap, "ad.selectedItemsFromSearchTable");
        } else {
            query = CommonUtil.getQuery(inputMap, "ad.tempItemSearch");
        }
        List resultList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        LOGGER.debug("Exiting getSelectedTableData");
        return getAttachedNames(resultList, category);
    }

    /**
     * To get the list of attached Companies or Items from the query result
     *
     * @param list Attached items or companies list
     * @return
     */
    public List<String> getAttachedNames(List list, String category) {
        List<String> attachedList = new ArrayList<>();
        int no = 0;
        if (Constants.IndicatorConstants.PS_VALUE.getConstant().equals(category) || Constants.IndicatorConstants.RS_VALUE.getConstant().equals(category)) {
            no = NumericConstants.TWO;
        }
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object[] objects = (Object[]) list.get(i);
            attachedList.add(Converters.convertNullToEmpty(objects[no]));
        }
        return attachedList;
    }
    
    public boolean checkSameItemInPs(String query1, String query2, String userId, String sessionId) {
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, userId);
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, sessionId);
        query1 = CommonUtil.getQuery(inputMap, query1);
        query2 = CommonUtil.getQuery(inputMap, query2);
        List queryList1 = HelperTableLocalServiceUtil.executeSelectQuery(query1);
        List queryList2 = HelperTableLocalServiceUtil.executeSelectQuery(query2);
        if (queryList1.size() == queryList2.size()) {
            Object[] listArr = queryList2.toArray();
            Object[] dataArr = queryList1.toArray();
            Arrays.sort(dataArr);
            Arrays.sort(listArr);
            return Arrays.deepEquals(dataArr, listArr);
        }
        return false;
    }

    public boolean checkSameItemInPs(String query, List<Object> dataList, String userId, String sessionId) {
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put(StringConstantsUtil.USERS_SID_QUESTION, userId);
        inputMap.put(StringConstantsUtil.SESSION_ID_QUESTION, sessionId);
        query = CommonUtil.getQuery(inputMap, query);
        List resultList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (resultList.size() == dataList.size()) {
            Object[] listArr = dataList.toArray();
            Object[] dataArr = resultList.toArray();
            Arrays.sort(dataArr);
            Arrays.sort(listArr);
            return Arrays.deepEquals(dataArr, listArr);
        }
        return false;
    }
    
    public static void getToInput(final Map<String, String> inputMap, final Map<String, String> inputMapToAppend, ContractsDetailsDto newDiscountTabDto, boolean fromNewTab) {
        String searchValue = newDiscountTabDto.getSearchFieldValue();
        String searchField;
        if (fromNewTab) {
            searchField = newDiscountTabDto.getSearchField().split("-")[1];
        } else {
            searchField = newDiscountTabDto.getSearchField();
        }

        if (Constants.getInstance().cfpSearch[0].equals(searchField)) {
            inputMap.put(StringConstantsUtil.CFP_NO_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[1].equals(searchField)) {
            inputMap.put(StringConstantsUtil.CFP_NAME_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.TWO].equals(searchField)) {
            inputMap.put(StringConstantsUtil.CFP_ID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.THREE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.CFP_STATUS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.FOUR].equals(searchField)) {
            inputMap.put(StringConstantsUtil.CFP_TYPE_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.FIVE].equals(searchField)) {
            inputMapToAppend.put("COMPANY_ID", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.SIX].equals(searchField)) {
            inputMapToAppend.put("COMPANY_NO", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.SEVEN].equals(searchField)) {
            inputMapToAppend.put("COMPANY_NAME", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.EIGHT].equals(searchField)) {
            inputMapToAppend.put("COMPANY_STATUS", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.NINE].equals(searchField)) {
            inputMapToAppend.put("COMPANY_TYPE", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.ELEVEN].equals(searchField)) {
            inputMapToAppend.put("CFP_DETAILS_TRADE_CLASS", CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.getInstance().ifpSearch[0].equals(searchField)) {
            inputMap.put(StringConstantsUtil.IFP_NO_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.getIFPSEARCH()[1].equals(searchField)) {
            inputMap.put(StringConstantsUtil.IFP_NAME_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.getIFPSEARCH()[NumericConstants.TWO].equals(searchField)) {
            inputMap.put(StringConstantsUtil.IFP_ID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().ifpSearch[NumericConstants.THREE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.IFP_STATUS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().ifpSearch[NumericConstants.FOUR].equals(searchField)) {
            inputMap.put(StringConstantsUtil.IFP_TYPE_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().ifpSearch[NumericConstants.FIVE].equals(searchField)) {
            inputMapToAppend.put("ITEM_ID", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().ifpSearch[NumericConstants.SIX].equals(searchField)) {
            inputMapToAppend.put("ITEM_NO", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().ifpSearch[NumericConstants.SEVEN].equals(searchField)) {
            inputMapToAppend.put("ITEM_NAME", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().ifpSearch[NumericConstants.EIGHT].equals(searchField)) {
            inputMapToAppend.put("ITEM_STATUS", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().ifpSearch[NumericConstants.TEN].equals(searchField)) {
            inputMapToAppend.put("ITEM_BRAND", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().ifpSearch[NumericConstants.ELEVEN].equals(searchField)) {
            inputMapToAppend.put("ITEM_FORM", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().ifpSearch[NumericConstants.TWELVE].equals(searchField)) {
            inputMapToAppend.put("ITEM_STRENGTH", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().ifpSearch[NumericConstants.THIRTEEN].equals(searchField)) {
            inputMapToAppend.put("ITEM_THERAPEUTIC_CLASS", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().ifpSearch[NumericConstants.FOURTEEN].equals(searchField)) {
            inputMapToAppend.put("ITEM_START_DATE", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().ifpSearch[NumericConstants.FIFTEEN].equals(searchField)) {
            inputMapToAppend.put("ITEM_END_DATE", CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.getInstance().psSearch[0].equals(searchField)) {
            inputMap.put(StringConstantsUtil.PS_NO_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.getPSSEARCH()[1].equals(searchField)) {
            inputMap.put(StringConstantsUtil.PS_NAME_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.getPSSEARCH()[NumericConstants.TWO].equals(searchField)) {
            inputMap.put(StringConstantsUtil.PS_ID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.THREE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.PS_STATUS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().cfpSearch[NumericConstants.FOUR].equals(searchField)) {
            inputMap.put(StringConstantsUtil.PS_TYPE_QUESTION, CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.getInstance().rsSearch[0].equals(searchField)) {
            inputMap.put(StringConstantsUtil.RS_NO_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.getRSSEARCH()[1].equals(searchField)) {
            inputMap.put(StringConstantsUtil.RS_NAME_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.getRSSEARCH()[NumericConstants.TWO].equals(searchField)) {
            inputMap.put(StringConstantsUtil.RS_ID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().rsSearch[NumericConstants.THREE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.RS_STATUS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().rsSearch[NumericConstants.FOUR].equals(searchField)) {
            inputMap.put(StringConstantsUtil.RS_TYPE_QUESTION, CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.getInstance().itemSearch[0].equals(searchField)) {
            inputMap.put(StringConstantsUtil.ITEM_ID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[1].equals(searchField)) {
            inputMap.put(StringConstantsUtil.ITEM_NO_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.TWO].equals(searchField)) {
            inputMap.put(StringConstantsUtil.ITEM_NAME_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.THREE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.ITEM_STATUS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.FOUR].equals(searchField)) {
            inputMap.put(StringConstantsUtil.ITEM_TYPE_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.FIVE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.BRAND_MASTER_SID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.SIX].equals(searchField)) {
            inputMap.put(StringConstantsUtil.FORM_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.SEVEN].equals(searchField)) {
            inputMap.put(StringConstantsUtil.STRENGTH_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().itemSearch[NumericConstants.EIGHT].equals(searchField)) {
            inputMap.put(StringConstantsUtil.THERAPEUTIC_CLASS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.getInstance().companySearch[0].equals(searchField)) {
            inputMap.put(StringConstantsUtil.COMPANY_ID_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().companySearch[1].equals(searchField)) {
            inputMap.put(StringConstantsUtil.COMPANY_NAME_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().companySearch[NumericConstants.TWO].equals(searchField)) {
            inputMap.put(StringConstantsUtil.COMPANY_NO_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().companySearch[NumericConstants.THREE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.COMPANY_STATUS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().companySearch[NumericConstants.FOUR].equals(searchField)) {
            inputMap.put(StringConstantsUtil.COMPANY_TYPE_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().companySearch[NumericConstants.FIVE].equals(searchField)) {
            inputMap.put(StringConstantsUtil.COMPANY_CATEGORY_QUESTION, CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.getInstance().companySearch[NumericConstants.SIX].equals(searchField)) {
            inputMap.put(StringConstantsUtil.TRADE_CLASS_QUESTION, CommonUtil.astToPerConverter(searchValue));
        }

    }
    public String getRsContractFromRSContractList(ContractsDetailsDto dto) {
        String rsSql = " AND RSC.RS_CONTRACT_SID IN (" + StringUtils.join(dto.getRemovedRsList(), ",") + ")"
                + " AND CN.CONTRACT_MASTER_SID = " + dto.getContractSid();
        return rsSql;
    }
    
    public List<Object> getDiscountRsList(List<Object> rsList, ContractsDetailsDto contractDto) {
        rsList.clear();
        String query = "SELECT RS_CONTRACT_SID FROM RS_CONTRACT WHERE CONTRACT_MASTER_SID = ?";
        query = query.replace("?", contractDto.getContractSid() + "");
        rsList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return rsList;
    }
}
