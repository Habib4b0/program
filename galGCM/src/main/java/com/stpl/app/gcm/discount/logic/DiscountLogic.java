/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

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
import static com.stpl.app.gcm.util.Constants.DateFormatConstants.MMddyyyy;
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
import com.stpl.app.model.RsDetails;
import com.stpl.app.model.RsModel;
import com.stpl.app.service.CfpContractLocalServiceUtil;
import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
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
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.SALES;
import static com.stpl.app.gcm.util.Constants.IndicatorConstants.UNITS;
import com.stpl.app.gcm.util.Converters;
import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;
import com.stpl.app.service.PsContractDetailsLocalServiceUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author vigneshkanna
 */
public class DiscountLogic {

    static CommonDao DAO = CommonImpl.getInstance();
    static DiscountDAO discountDAO = new DiscountDaoImpl();
    private static final Logger LOGGER = Logger.getLogger(DiscountLogic.class);
    public static final SimpleDateFormat DBDate = new SimpleDateFormat(MMddyyyy.getConstant());
    QueryUtils queryUtils = new QueryUtils();
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0");
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    public static final char CHAR_PERCENT = '%';
    private static final DecimalFormat ACTUAL_FORMAT = new DecimalFormat("0.00");
    DateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
    HelperListUtil helperlist = HelperListUtil.getInstance();

    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';

    public List getMarketType() {
        List<String> list = discountDAO.getMarketType();
        list.add(0, Constants.SELECT_ONE);
        return list;
    }

    public int getContractSearchCount(CustomFieldGroup discountChBinder, Set<Container.Filter> filters) {
        int count = 0;
        count = discountDAO.getContractsCount(discountChBinder, filters);
        return count;
    }

    public List<RemoveDiscountDto> getContractSearch(CustomFieldGroup discountChBinder, int startIndex,
            int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumn) {
        LOGGER.info("Entered contract search logic");
        List resultList = new ArrayList();
        String columnName = StringUtils.EMPTY;
        String dbColumnName = StringUtils.EMPTY;
        boolean asc = false;
        List<RemoveDiscountDto> searchList = new ArrayList<RemoveDiscountDto>();
        resultList = discountDAO.getContracts(discountChBinder, startIndex, offset, filters, sortByColumn);
        searchList = setContractValues(resultList);
        LOGGER.info("Ending contract search logic" + searchList.size());
        return searchList;
    }

    public List<RemoveDiscountDto> setContractValues(List list) {
        List<RemoveDiscountDto> returnList = new ArrayList<RemoveDiscountDto>();
        RemoveDiscountDto removeDiscountDto;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object objects[] = (Object[]) list.get(i);
            removeDiscountDto = new RemoveDiscountDto();
            removeDiscountDto.setContractHolder(Converters.convertNullToEmpty(objects[0]));
            removeDiscountDto.setContractNo(Converters.convertNullToEmpty(objects[1]));
            removeDiscountDto.setContractName(Converters.convertNullToEmpty(objects[2]));
            removeDiscountDto.setMarketType(String.valueOf(objects[3]));
            removeDiscountDto.setStartDate(setFilterFormatted(objects[4]));
            removeDiscountDto.setContractstartDate((Date) (objects[4]));
            if (queryUtils.getNull(String.valueOf(objects[5]))) {
                removeDiscountDto.setEndDate(setFilterFormatted(objects[5]));
                removeDiscountDto.setContractendDate((Date) (objects[5]));
            }
            removeDiscountDto.setCfpName(String.valueOf(objects[6]));
            removeDiscountDto.setIfpName(String.valueOf(objects[7]));
            removeDiscountDto.setPsName(String.valueOf(objects[8]));
            removeDiscountDto.setRsName(String.valueOf(objects[9]));
            removeDiscountDto.setContractSid(Integer.parseInt(StringUtils.EMPTY.equals(Converters.convertNullToEmpty(objects[10])) ? Constants.ZEROSTRING : String.valueOf(objects[10])));
            removeDiscountDto.setCfpSid(Integer.parseInt(StringUtils.EMPTY.equals(Converters.convertNullToEmpty(objects[11])) ? Constants.ZEROSTRING : String.valueOf(objects[11])));
            removeDiscountDto.setIfpSid(Integer.parseInt(StringUtils.EMPTY.equals(Converters.convertNullToEmpty(objects[12])) ? Constants.ZEROSTRING : String.valueOf(objects[12])));
            removeDiscountDto.setPsSid(Integer.parseInt(StringUtils.EMPTY.equals(Converters.convertNullToEmpty(objects[13])) ? Constants.ZEROSTRING : String.valueOf(objects[13])));
            removeDiscountDto.setRsSid(Integer.parseInt(StringUtils.EMPTY.equals(Converters.convertNullToEmpty(objects[14])) ? Constants.ZEROSTRING : String.valueOf(objects[14])));
            removeDiscountDto.setCompanySid(Integer.parseInt(StringUtils.EMPTY.equals(Converters.convertNullToEmpty(objects[15])) ? Constants.ZEROSTRING : String.valueOf(objects[15])));
            removeDiscountDto.setContractId(String.valueOf(objects[16]));
            removeDiscountDto.setContractStatus(isNull(objects[17]));
            returnList.add(removeDiscountDto);
        }

        return returnList;
    }

    public List<ContractsDetailsDto> getLevelDetails(ContractsDetailsDto tableBean) {
        List<ContractsDetailsDto> levelsDetails = new ArrayList<ContractsDetailsDto>();
        try {
            final DynamicQuery contractQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class);
            contractQuery.add(RestrictionsFactoryUtil.eq("rsContractSid", tableBean.getInternalId()));
            List<RsContractDetails> contractDetails = discountDAO.getContractDetails(contractQuery);
            levelsDetails = getNewDiscountTabDto(contractDetails);
        } catch (Exception e) {
        }
        return levelsDetails;
    }

    private List<ContractsDetailsDto> getNewDiscountTabDto(List<RsContractDetails> contractDetails) {
        List<ContractsDetailsDto> levelsDetails = new ArrayList<ContractsDetailsDto>();
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
        }
        return levelsDetails;
    }

    public int getItemSearchCount(ContractsDetailsDto newDiscountTabDto) throws Exception {
        int count = 0;
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        query.add(RestrictionsFactoryUtil.in("brandMasterSid", new Object[]{4, 6, 10, 85}));
        query.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        count = discountDAO.getItemsCount(query);
        return count;
    }

    public List<ContractsDetailsDto> getItemSearch(ContractsDetailsDto removeDiscountDto) throws Exception {
        List<ContractsDetailsDto> searchList = new ArrayList<ContractsDetailsDto>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        query.add(RestrictionsFactoryUtil.in("brandMasterSid", new Object[]{4, 6, 10, 85}));
        query.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil.like(Constants.IndicatorConstants.INBOUND_STATUS.getConstant(), "D")));
        query.setLimit(removeDiscountDto.getStartIndex(), removeDiscountDto.getEndIndex());
        List<ItemMaster> resultList = discountDAO.getItems(query);
        searchList = getSearchResult(resultList);
        return searchList;
    }

    private List<ContractsDetailsDto> getSearchResult(List<ItemMaster> resultList) throws Exception {
        List<ContractsDetailsDto> searchList = new ArrayList<ContractsDetailsDto>();
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
        List<RemoveDiscountDto> searchList = new ArrayList<RemoveDiscountDto>();
        try {
            String query = queryUtils.getLatestCCPQuery(contractSid, rsSid);
            removeList = discountDAO.getValues(query);

            if (removeList.size() > 0) {
                Object objects[] = (Object[]) removeList.get(0);
                removeDiscountDto.setProjectionSid(Integer.parseInt(String.valueOf(objects[0])));
                removeDiscountDto.setForecastingType(String.valueOf(objects[1]));
                String dateQuery = queryUtils.getForecastDates(removeDiscountDto.getForecastingType().equalsIgnoreCase("Non Mandated") ? "Commercial" : "Government");
                queryList = discountDAO.getValues(dateQuery);
                Object objects1[] = (Object[]) queryList.get(0);
                removeDiscountDto.setFromDate((Date) objects1[0]);
                removeDiscountDto.setToDate((Date) objects1[1]);
                LOGGER.info("From" + removeDiscountDto.getFromDate() + "To" + removeDiscountDto.getToDate());
                searchList.add(removeDiscountDto);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("Ending getprojectionValues with " + searchList.size());
        return searchList;
    }

    public RemoveDiscountDto getForecastDates(String type) {
        List queryList = new ArrayList();
        RemoveDiscountDto removeDiscountDto = new RemoveDiscountDto();
        try {
            String query = queryUtils.getForecastDates(type);
            queryList = discountDAO.getValues(query);
            Object objects[] = (Object[]) queryList.get(0);
            removeDiscountDto.setFromDate((Date) objects[0]);
            removeDiscountDto.setToDate((Date) objects[1]);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return removeDiscountDto;
    }

    public List getSummary(RemoveDiscountDto removeDiscountDto) {
        LOGGER.info("Entering getSummary");
        List summaryList = new ArrayList();
        try {
            String query = queryUtils.getSummaryCountQuery(removeDiscountDto);
            summaryList = discountDAO.getValues(query);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("Ending getSummary" + summaryList.get(0));
        return summaryList;
    }

    public List<ContractsDetailsDto> getRebateSchedule(ContractsDetailsDto newDiscountTabDto) throws Exception {
        List<ContractsDetailsDto> searchList = new ArrayList<ContractsDetailsDto>();
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?CFP_NO?", "%");
        inputMap.put("?CFP_NAME?", "%");
        inputMap.put("?CFP_ID?", "%");
        inputMap.put("?CFP_STATUS?", "%");
        inputMap.put("?CFP_TYPE?", "%");
        inputMap.put("?IFP_NO?", "%");
        inputMap.put("?IFP_NAME?", "%");
        inputMap.put("?IFP_ID?", "%");
        inputMap.put("?IFP_STATUS?", "%");
        inputMap.put("?IFP_TYPE?", "%");
        inputMap.put("?PS_NO?", "%");
        inputMap.put("?PS_NAME?", "%");
        inputMap.put("?PS_ID?", "%");
        inputMap.put("?PS_STATUS?", "%");
        inputMap.put("?PS_TYPE?", "%");
        inputMap.put("?RS_NO?", "%");
        inputMap.put("?RS_NAME?", "%");
        inputMap.put("?RS_ID?", "%");
        inputMap.put("?RS_STATUS?", "%");
        inputMap.put("?RS_TYPE?", "%");
        String searchValue = Converters.convertNullToEmpty(newDiscountTabDto.getSearchFieldValue());
        if (searchValue.trim().equals(StringUtils.EMPTY)) {

            searchValue = Constants.IndicatorConstants.CHAR_PERCENT.getConstant();
        } else {
            searchValue = searchValue.replace(Constants.IndicatorConstants.CHAR_ASTERISK.getConstant(), Constants.IndicatorConstants.CHAR_PERCENT.getConstant());
        }
        List results = new ArrayList();
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.RS_VALUE.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, "ad.rsSearch");
            query += "ORDER BY RS.RS_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            results = discountDAO.getRebates(query);
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.CFP.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, "ad.cfpSearch");
            query += "ORDER BY CFP.CFP_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            results = discountDAO.getRebates(query);
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.IFP.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, "ad.ifpSearch");
            query += "ORDER BY IFP.IFP_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            results = discountDAO.getRebates(query);
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.PS_VALUE.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, "ad.psSearch");
            query += "ORDER BY PS.PS_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            results = discountDAO.getRebates(query);
        }

        searchList = getCustomisedDto(results, newDiscountTabDto);
        return searchList;
    }

    private List<ContractsDetailsDto> getCustomisedDto(List results, ContractsDetailsDto newDiscountTabDto) throws Exception {

        List<ContractsDetailsDto> searchList = new ArrayList<ContractsDetailsDto>();
        int size = results.size();
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.RS_VALUE.toString())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setRsSid(Converters.convertNullToEmpty(arr[0]));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[2]));
                tabDto.setName(Converters.convertNullToEmpty(arr[3]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[4]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[5]))) ? null : DBDate.format((Date) arr[5]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[6]))) ? null : DBDate.format((Date) arr[6]));
                tabDto.setIfpSystemId(Converters.convertNullToEmpty(arr[7]));
                tabDto.setIfpName(Converters.convertNullToEmpty(arr[8]));
                tabDto.setFrequency(Converters.convertNullToEmpty(arr[9]));
                tabDto.setRarType(Converters.convertNullToEmpty(arr[10]));
                tabDto.setBasis(Converters.convertNullToEmpty(StringUtils.EMPTY));
                tabDto.setCategory(Constants.IndicatorConstants.RS_VALUE.getConstant());
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setModelSysId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                searchList.add(tabDto);
            }
        } else if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.PS_VALUE.toString())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setPsSid(Converters.convertNullToEmpty(arr[0]));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[2]));
                tabDto.setName(Converters.convertNullToEmpty(arr[6]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[3]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[4]))) ? null : DBDate.format((Date) arr[4]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[5]))) ? null : DBDate.format((Date) arr[5]));
                tabDto.setIfpSystemId(Converters.convertNullToEmpty(arr[7]));
                tabDto.setIfpName(Converters.convertNullToEmpty(arr[8]));
                tabDto.setType(Converters.convertNullToEmpty(arr[9]));
                tabDto.setPsCategory(Converters.convertNullToEmpty(arr[10]));
                tabDto.setDesignation(Converters.convertNullToEmpty(arr[11]));
                tabDto.setCategory(Constants.IndicatorConstants.PS_VALUE.getConstant());
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                searchList.add(tabDto);
            }
        } else if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.CFP.toString())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setCfpId(Integer.parseInt(Converters.convertNullToEmpty(arr[0])));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[2]));
                tabDto.setName(Converters.convertNullToEmpty(arr[3]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[5]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[6]))) ? null : DBDate.format((Date) arr[6]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[7]))) ? null : DBDate.format((Date) arr[7]));
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setCategory(Constants.IndicatorConstants.CFP.getConstant());
                searchList.add(tabDto);
            }
        } else if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.IFP.toString())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setIfpId(Integer.parseInt(Converters.convertNullToEmpty(arr[0])));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[2]));
                tabDto.setName(Converters.convertNullToEmpty(arr[3]));
                tabDto.setStatus(Converters.convertNullToEmpty((arr[5])));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[6]))) ? null : DBDate.format((Date) arr[6]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[7]))) ? null : DBDate.format((Date) arr[7]));
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(Converters.convertNullToEmpty(arr[0]))));
                tabDto.setCategory(Constants.IndicatorConstants.IFP.getConstant());
                searchList.add(tabDto);
            }
        }

        return searchList;
    }

    public List<ContractsDetailsDto> getItemsFromRs(ContractsDetailsDto newDiscountTabDto) throws Exception {
        List<ContractsDetailsDto> searchList = new ArrayList<ContractsDetailsDto>();
        Map<String, String> inputMap = new HashMap<String, String>();
        List results = new ArrayList();
        inputMap.put("?SID?", String.valueOf(newDiscountTabDto.getInternalId()));
        if (Constants.IndicatorConstants.CFP.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.cfpFromResults");
            query += "ORDER BY CFP_CM.COMPANY_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            results = discountDAO.getRebates(query);
        } else if (Constants.IndicatorConstants.IFP.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.ifpFromResults");
            query += "ORDER BY IFP_IM.ITEM_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            results = discountDAO.getRebates(query);
        } else if (Constants.IndicatorConstants.PS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.psFromResults");
            query += "ORDER BY PS_IM.ITEM_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            results = discountDAO.getRebates(query);
        } else if (Constants.IndicatorConstants.RS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.rsFromResults");
            query += "ORDER BY RS_IM.ITEM_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            results = discountDAO.getRebates(query);
        }
        searchList = getDetailsFromResult(results, newDiscountTabDto);
        return searchList;
    }

    private List<ContractsDetailsDto> getDetailsFromResult(List results, ContractsDetailsDto newDiscountTabDto) {
        List<ContractsDetailsDto> searchList = new ArrayList<ContractsDetailsDto>();

        int size = results.size();
        if (Constants.IndicatorConstants.RS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setItemNo(Converters.convertNullToEmpty(arr[0]));
                tabDto.setItemName(Converters.convertNullToEmpty(arr[1]));
                tabDto.setBrand(Converters.convertNullToEmpty(arr[2]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[3]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[4]))) ? null : DBDate.format((Date) arr[4]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[5]))) ? null : DBDate.format((Date) arr[5]));
                tabDto.setFormulaId(Converters.convertNullToEmpty(arr[6]));
                tabDto.setRebatePlanId(Converters.convertNullToEmpty(arr[7]));
                tabDto.setRebatePlanName(Converters.convertNullToEmpty(arr[8]));
                tabDto.setRebateAmount(Converters.convertNullToEmpty(arr[9]));
                tabDto.setBundleNo(Converters.convertNullToEmpty(arr[10]));
                tabDto.setAttachedDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[11]))) ? null : DBDate.format((Date) arr[11]));
                searchList.add(tabDto);
            }
        } else if (Constants.IndicatorConstants.PS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                int j = -1;
                tabDto.setItemNo(Converters.convertNullToEmpty(arr[0]));
                tabDto.setItemName(Converters.convertNullToEmpty(arr[1]));
                tabDto.setBrand(Converters.convertNullToEmpty(arr[2]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[3]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[4]))) ? null : DBDate.format((Date) arr[4]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[5]))) ? null : DBDate.format((Date) arr[5]));
                tabDto.setPriceType(Converters.convertNullToEmpty(arr[6]));
                tabDto.setPricePlanNo(Converters.convertNullToEmpty(arr[7]));
                tabDto.setPricePlanName(Converters.convertNullToEmpty(arr[8]));
                tabDto.setPriceProtectionStartDate(Converters.convertNullToEmpty(arr[9]));
                tabDto.setPriceProtectionEndDate(Converters.convertNullToEmpty(arr[10]));
                tabDto.setPriceToleranceInterval(Converters.convertNullToEmpty(arr[11]));
                tabDto.setPriceToleranceFrequency(Converters.convertNullToEmpty(arr[12]));
                tabDto.setPriceToleranceType(Converters.convertNullToEmpty(arr[13]));
                tabDto.setPriceTolerance(Converters.convertNullToEmpty(arr[14]));
                tabDto.setAttachedDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[15]))) ? null : DBDate.format((Date) arr[15]));

                searchList.add(tabDto);
            }
        } else if (Constants.IndicatorConstants.IFP.toString().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setItemNo(Converters.convertNullToEmpty(arr[0]));
                tabDto.setItemName(Converters.convertNullToEmpty(arr[1]));
                tabDto.setBrand(Converters.convertNullToEmpty(arr[2]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[3]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[4]))) ? null : DBDate.format((Date) arr[4]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[5]))) ? null : DBDate.format((Date) arr[5]));
                tabDto.setAttachedDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[6]))) ? null : DBDate.format((Date) arr[6]));
                searchList.add(tabDto);
            }
        } else if (Constants.IndicatorConstants.CFP.toString().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setTpNo(Converters.convertNullToEmpty(arr[0]));
                tabDto.setTpName(Converters.convertNullToEmpty(arr[1]));
                tabDto.setTpContractNo(Converters.convertNullToEmpty(arr[0]));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[2]))) ? null : DBDate.format((Date) arr[2]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[3]))) ? null : DBDate.format((Date) arr[3]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[4]));
                tabDto.setTradeClass(Converters.convertNullToEmpty(arr[5]));
                tabDto.setAttachedDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[6]))) ? null : DBDate.format((Date) arr[6]));
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
        List<DiscountDTO> resultList = new ArrayList<DiscountDTO>();
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
                dto.setLevelNo(Integer.valueOf(String.valueOf(obj[5])));
                if (dto.getLevelNo() == 4) {
                    dto.setParent(0);
                }
                setSalesData(dto, obj);

            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage() + "- in configureLevel");
        }
        return resultList;
    }

    private void setSalesData(DiscountDTO dto, Object[] obj) {
        String commonColumn = "q" + obj[2] + StringUtils.EMPTY + obj[1];
        int mon = Integer.parseInt(String.valueOf(obj[2]));
        int yr = Integer.parseInt(String.valueOf(obj[1]));
        int month = getQuarter(mon);
        dto.addStringProperties(commonColumn + SALES.getConstant(), getFormattedValue(AMOUNT, stringIsNull(obj[3])));
        dto.addStringProperties(commonColumn + UNITS.getConstant(), getFormattedValue(AMOUNT_UNITS, stringIsNull(obj[4])));
    }

    // This method is to compare the dates
    private boolean getDiffDate(int mon, int yr) {
        boolean result = true;
        String date = DBDate.format(new Date());
        String[] dateStr = date.split("/");
        int month = getQuarter(Integer.parseInt(dateStr[0]));
        if (yr > Integer.parseInt(dateStr[2])) {
            result = false;

        }
        if (yr == Integer.parseInt(dateStr[2]) && mon >= month) {
            result = false;
        }
        return result;
    }

    //This method is to return which quarter the month refers
    private int getQuarter(int mon) {
        int result = 1;
        if (mon <= 3) {
            result = 1;
        }
        if (mon <= 6) {
            result = 2;
        }
        if (mon <= 9) {
            result = 3;
        }
        if (mon <= 12) {
            result = 4;
        }
        return result;
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
        return Integer.parseInt(String.valueOf(list.get(0)));
    }

    public int getRebateScheduleCount(ContractsDetailsDto newDiscountTabDto) throws Exception {
        List results = new ArrayList();
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?CFP_NO?", "%");
        inputMap.put("?CFP_NAME?", "%");
        inputMap.put("?CFP_ID?", "%");
        inputMap.put("?CFP_STATUS?", "%");
        inputMap.put("?CFP_TYPE?", "%");
        inputMap.put("?IFP_NO?", "%");
        inputMap.put("?IFP_NAME?", "%");
        inputMap.put("?IFP_ID?", "%");
        inputMap.put("?IFP_STATUS?", "%");
        inputMap.put("?IFP_TYPE?", "%");
        inputMap.put("?PS_NO?", "%");
        inputMap.put("?PS_NAME?", "%");
        inputMap.put("?PS_ID?", "%");
        inputMap.put("?PS_STATUS?", "%");
        inputMap.put("?PS_TYPE?", "%");
        inputMap.put("?RS_NO?", "%");
        inputMap.put("?RS_NAME?", "%");
        inputMap.put("?RS_ID?", "%");
        inputMap.put("?RS_STATUS?", "%");
        inputMap.put("?RS_TYPE?", "%");
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
            String query = CommonUtil.getQuery(inputMap, "ad.ifpSearch");
            results = discountDAO.getRebates(query);
        }
        if (newDiscountTabDto.getSearchField().contains(Constants.IndicatorConstants.PS_VALUE.toString())) {
            getToInput(inputMap, newDiscountTabDto, false);
            String query = CommonUtil.getQuery(inputMap, "ad.psSearch");
            results = discountDAO.getRebates(query);
        }

        return results.size();
    }

    public int getItemsFromRsCount(ContractsDetailsDto newDiscountTabDto) throws Exception {
        List results = new ArrayList();
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?SID?", String.valueOf(newDiscountTabDto.getInternalId()));
        if (Constants.IndicatorConstants.CFP.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.cfpFromResults");
            results = discountDAO.getRebates(query);
        } else if (Constants.IndicatorConstants.IFP.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.ifpFromResults");
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

    public List<RemoveDiscountDto> getItems(RemoveDiscountDto discountDto, int start, int offset, boolean flag) throws Exception {
        String query = queryUtils.getItems(discountDto.getContractSid(), discountDto.getRsSid(), flag, start, offset);
        List itemList = (List<RemoveDiscountDto>) discountDAO.getRebates(query);

        return setItemValues(itemList);
    }

    public int getItemsCount(RemoveDiscountDto discountDto, int start, int offset) throws Exception {
        int count = 0;
        String query = queryUtils.getItems(discountDto.getContractSid(), discountDto.getRsSid(), true, start, offset);
        List itemList = (List<RemoveDiscountDto>) discountDAO.getRebates(query);
        count = itemList.isEmpty() ? 0 : itemList.size();
        return count;
    }

    public List<RemoveDiscountDto> getTreeItems(int contractSid, int rsId) throws Exception {
        String query = queryUtils.getItems(contractSid, rsId, true, 0, 0);
        List itemList = (List<RemoveDiscountDto>) discountDAO.getRebates(query);

        return setItemValues(itemList);
    }

    public List<RemoveDiscountDto> setItemValues(List list) {
        List<RemoveDiscountDto> itemList = new ArrayList<RemoveDiscountDto>();
        RemoveDiscountDto removeDiscountDto;
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object objects[] = (Object[]) list.get(i);
            removeDiscountDto = new RemoveDiscountDto();
            removeDiscountDto.setItemSid(Integer.parseInt(String.valueOf(objects[0])));
            removeDiscountDto.setItemNo(isNull(objects[1]));
            removeDiscountDto.setItemName(isNull(objects[2]));
            removeDiscountDto.setTherapyClass(isNull(objects[3]));
            removeDiscountDto.setBrand(isNull(objects[4]));
            removeDiscountDto.setItemStatus(isNull(objects[5]));
            if (getNull(String.valueOf(objects[6]))) {
                removeDiscountDto.setItemStartDate(DBDate.format(objects[6]));
            }
            if (getNull(String.valueOf(objects[7]))) {
                removeDiscountDto.setItemEndDate(DBDate.format(objects[7]));
            }
            if (getNull(String.valueOf(objects[8]))) {
                removeDiscountDto.setRebatePlan(isNull(objects[8]));
            }
            if (getNull(String.valueOf(objects[9]))) {
                removeDiscountDto.setFormulaId(isNull(objects[9]));
            }
            removeDiscountDto.setRsSid(Integer.parseInt(String.valueOf(objects[10])));
            removeDiscountDto.setRsId(isNull(objects[11]));
            removeDiscountDto.setRsNo(isNull(objects[12]));
            removeDiscountDto.setRsName(isNull(objects[13]));
            removeDiscountDto.setRsStatus(isNull(objects[14]));
            removeDiscountDto.setRsStartDate(String.valueOf(objects[15]));
            removeDiscountDto.setRsEndDate(String.valueOf(objects[16]));
            removeDiscountDto.setRebateFrequency(isNull(objects[17]));
            removeDiscountDto.setProgramType(isNull(objects[18]));
            removeDiscountDto.setRsType(isNull(objects[19]));
            removeDiscountDto.setRsCategory(isNull(objects[20]));
            removeDiscountDto.setPaymentFrequency(isNull(objects[21]));
            removeDiscountDto.setRebatePlanLevel(isNull(objects[22]));
            removeDiscountDto.setFormulaType(isNull(objects[23]));
            removeDiscountDto.setFormulaName(isNull(objects[24]));
            removeDiscountDto.setRebateAmount(isNull(objects[25]));
            removeDiscountDto.setRebatePlanName(isNull(objects[26]));
            removeDiscountDto.setRebatePlanId(isNull(objects[27]));
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
            LOGGER.error(e.getMessage());
        }
    }

    public List<RemoveDiscountDto> getRebateSearch(String field, String value, int contractSid, int rsSid) {
        LOGGER.info("Entered contract search logic");
        List resultList = new ArrayList();
        List<RemoveDiscountDto> searchList = new ArrayList<RemoveDiscountDto>();
        resultList = discountDAO.getRebateSearch(field, value, contractSid, rsSid);
        searchList = setContractValues(resultList);
        LOGGER.info("Ending contract search logic" + searchList.size());
        return searchList;
    }

    public List<CFPComponentDetailsDTO> getFromCfpCD(Object parent) {
        List<CFPComponentDetailsDTO> retList = new ArrayList<CFPComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof ContractsDetailsDto) {

            inputMap.put("?SID?", String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
        }

        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "ad.cfpFromCD"));
        for (Object[] temp : resList) {
            CFPComponentDetailsDTO tempDto = new CFPComponentDetailsDTO();
            tempDto.setCompanyNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setCompanyName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[3]))) ? null : DBDate.format((Date) temp[3]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[4]))) ? null : DBDate.format((Date) temp[4]));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[2])));
            retList.add(tempDto);
        }

        return retList;
    }

    public List<PSComponentDetailsDTO> getFromIfpCD(Object parent) {
        List<PSComponentDetailsDTO> retList = new ArrayList<PSComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put("?SID?", String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "ad.ifpFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();

            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[3])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[2])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[4])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[5]))) ? null : DBDate.format((Date) temp[5]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[6]))) ? null : DBDate.format((Date) temp[6]));
            retList.add(tempDto);
        }
        return retList;
    }

    public List<PSComponentDetailsDTO> getFromPsCD(Object parent) {
        List<PSComponentDetailsDTO> retList = new ArrayList<PSComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put("?SID?", String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "ad.psFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            int j = -1;
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[2])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[3])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[4])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[5]))) ? null : DBDate.format((Date) temp[5]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[6]))) ? null : DBDate.format((Date) temp[6]));

            retList.add(tempDto);
        }
        return retList;
    }

    public List<PSComponentDetailsDTO> getFromRsCD(Object parent) {
        List<PSComponentDetailsDTO> retList = new ArrayList<PSComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put("?SID?", String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "ad.rsFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[2])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[3])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[4])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[5]))) ? null : DBDate.format((Date) temp[5]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[6]))) ? null : DBDate.format((Date) temp[6]));
            retList.add(tempDto);
        }
        return retList;
    }

    public void saveRS(final int contractSystemId, final int cfpSystemId, final int ifpSystemId, final int psSystemId, final ContractsDetailsDto contractMember) throws SystemException, PortalException {
        LOGGER.info("Entering saveRS method with contractSystemId=" + contractSystemId);
        int rsId = 0;
        if (!contractMember.getRsSid().equals("0")) {
            rsId = Integer.valueOf(contractMember.getRsSid());
        }
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
        rsMasterAttached.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        rsMasterAttached.setCreatedDate(new Date());
        rsMasterAttached.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        rsMasterAttached.setModifiedDate(new Date());
        rsMasterAttached.setFormulaMethodId(rebateMaster.getFormulaMethodId());
        rsMasterAttached.setRecordLockStatus(false);
        rsMasterAttached.setInboundStatus("A");

        DynamicQuery query = DynamicQueryFactoryUtil.forClass(RsContract.class);
        query.add(RestrictionsFactoryUtil.eq("contractMasterSid", contractSystemId));
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
//            rsMasterAttached.setRsContractSid(list.get(0).getRsContractSid());
//           
//            rsMasterAttached.setContractMasterSid(contractSystemId);
//            rsMasterAttached.setCfpContractSid((cfpSystemId != 0) ? String.valueOf(cfpSystemId) : null);
//            rsMasterAttached.setIfpContractSid((ifpSystemId != 0) ? String.valueOf(ifpSystemId) : null);
//            rsMasterAttached.setPsContractSid((ifpSystemId != 0) ? String.valueOf(psSystemId) : null);
//            rsMasterAttached.setRsModelSid(contractMember.getModelSysId());
//
//            rsContract = discountDAO.updateRsMasterAttached(rsMasterAttached);
//
//        } else {

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
            List<Object> input = new ArrayList<Object>(8);
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
                RsContractDetailsLocalServiceUtil.saveRsDetailsAttached(input, null);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        DynamicQuery rsContractDetailsQuery = DynamicQueryFactoryUtil.forClass(RsContractDetails.class);
        rsContractDetailsQuery.add(RestrictionsFactoryUtil.eq("rsContractSid", rsContract.getRsContractSid()));
        rsContractDetailsQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
        List<RsContractDetails> rsContDetList = RsContractDetailsLocalServiceUtil.dynamicQuery(rsContractDetailsQuery);
        for (int j = 0; j < rsContDetList.size(); j++) {
            RsContract rsCont = RsContractLocalServiceUtil.getRsContract(rsContDetList.get(j).getRsContractSid());
            DynamicQuery rsDetailsQuery = DynamicQueryFactoryUtil.forClass(RsDetails.class);
            rsDetailsQuery.add(RestrictionsFactoryUtil.eq("rsModelSid", rsCont.getRsModelSid()));
            rsDetailsQuery.add(RestrictionsFactoryUtil.eq("itemMasterSid", rsContDetList.get(j).getItemMasterSid()));
            rsDetailsQuery.add(RestrictionsFactoryUtil.ne("inboundStatus", "D"));
            List<RsDetails> rsdList = RsDetailsLocalServiceUtil.dynamicQuery(rsDetailsQuery);
        }
        LOGGER.info("End of saveRS method");
    }

    public static void getToInput(final Map<String, String> inputMap, ContractsDetailsDto newDiscountTabDto, boolean fromNewTab) {
        String searchValue = newDiscountTabDto.getSearchFieldValue();
        String searchField = StringUtils.EMPTY;
        if (fromNewTab) {
            searchField = newDiscountTabDto.getSearchField().split("-")[1];
        } else {
            searchField = newDiscountTabDto.getSearchField();
        }

        if (Constants.CFP_SEARCH[0].equals(searchField)) {
            inputMap.put("?CFP_NO?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.CFP_SEARCH[1].equals(searchField)) {
            inputMap.put("?CFP_NAME?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.CFP_SEARCH[2].equals(searchField)) {
            inputMap.put("?CFP_ID?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.CFP_SEARCH[3].equals(searchField)) {
            inputMap.put("?CFP_STATUS?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.CFP_SEARCH[4].equals(searchField)) {
            inputMap.put("?CFP_TYPE?", CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.IFP_SEARCH[0].equals(searchField)) {
            inputMap.put("?IFP_NO?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.IFP_SEARCH[1].equals(searchField)) {
            inputMap.put("?IFP_NAME?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.IFP_SEARCH[2].equals(searchField)) {
            inputMap.put("?IFP_ID?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.CFP_SEARCH[3].equals(searchField)) {
            inputMap.put("?IFP_STATUS?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.CFP_SEARCH[4].equals(searchField)) {
            inputMap.put("?IFP_TYPE?", CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.PS_SEARCH[0].equals(searchField)) {
            inputMap.put("?PS_NO?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.PS_SEARCH[1].equals(searchField)) {
            inputMap.put("?PS_NAME?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.PS_SEARCH[2].equals(searchField)) {
            inputMap.put("?PS_ID?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.CFP_SEARCH[3].equals(searchField)) {
            inputMap.put("?PS_STATUS?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.CFP_SEARCH[4].equals(searchField)) {
            inputMap.put("?PS_TYPE?", CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.RS_SEARCH[0].equals(searchField)) {
            inputMap.put("?RS_NO?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.RS_SEARCH[1].equals(searchField)) {
            inputMap.put("?RS_NAME?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constant.RS_SEARCH[2].equals(searchField)) {
            inputMap.put("?RS_ID?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.RS_SEARCH[3].equals(searchField)) {
            inputMap.put("?RS_STATUS?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.RS_SEARCH[4].equals(searchField)) {
            inputMap.put("?RS_TYPE?", CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.ITEM_SEARCH[0].equals(searchField)) {
            inputMap.put("?ITEM_ID?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.ITEM_SEARCH[1].equals(searchField)) {
            inputMap.put("?ITEM_NO?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.ITEM_SEARCH[2].equals(searchField)) {
            inputMap.put("?ITEM_NAME?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.ITEM_SEARCH[3].equals(searchField)) {
            inputMap.put("?ITEM_STATUS?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.ITEM_SEARCH[4].equals(searchField)) {
            inputMap.put("?ITEM_TYPE?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.ITEM_SEARCH[5].equals(searchField)) {
            inputMap.put("?BRAND_MASTER_SID?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.ITEM_SEARCH[6].equals(searchField)) {
            inputMap.put("?FORM?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.ITEM_SEARCH[7].equals(searchField)) {
            inputMap.put("?STRENGTH?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.ITEM_SEARCH[8].equals(searchField)) {
            inputMap.put("?THERAPEUTIC_CLASS?", CommonUtil.astToPerConverter(searchValue));
        }

        if (Constants.COMPANY_SEARCH[0].equals(searchField)) {
            inputMap.put("?COMPANY_ID?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.COMPANY_SEARCH[1].equals(searchField)) {
            inputMap.put("?COMPANY_NAME?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.COMPANY_SEARCH[2].equals(searchField)) {
            inputMap.put("?COMPANY_NO?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.COMPANY_SEARCH[3].equals(searchField)) {
            inputMap.put("?COMPANY_STATUS?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.COMPANY_SEARCH[4].equals(searchField)) {
            inputMap.put("?COMPANY_TYPE?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.COMPANY_SEARCH[5].equals(searchField)) {
            inputMap.put("?COMPANY_CATEGORY?", CommonUtil.astToPerConverter(searchValue));
        } else if (Constants.COMPANY_SEARCH[6].equals(searchField)) {
            inputMap.put("?TRADE_CLASS?", CommonUtil.astToPerConverter(searchValue));
        }

    }

    public int getCommonCountForNewTab(ContractsDetailsDto newDiscountTabDto, SessionDTO sessionDTO) throws Exception {
        Map<String, String> inputMap = new HashMap<String, String>();
        String searchField = newDiscountTabDto.getSearchField().split("-")[0];
        inputMap.put("?IFP_NO?", "%");
        inputMap.put("?IFP_NAME?", "%");
        inputMap.put("?IFP_ID?", "%");
        inputMap.put("?IFP_STATUS?", "%");
        inputMap.put("?IFP_TYPE?", "%");
        inputMap.put("?USERS_SID?", sessionDTO.getUserId());
        inputMap.put("?SESSION_ID?", sessionDTO.getSearchSessionId());

        List results = new ArrayList();
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString()) || searchField.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            String query = CommonUtil.getQuery(inputMap, "ad.ifpSearchCount");
            results = discountDAO.getRebates(query);
        }

        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            inputMap.put("?OPERATION?", "%" + Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
            getToInput(inputMap, newDiscountTabDto, true);
            String query = CommonUtil.getQuery(inputMap, "ad.tempItemSearchCountForIFP");
            results = discountDAO.getRebates(query);
        }
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            inputMap.put("?COMPANY_ID?", "%");
            inputMap.put("?COMPANY_NO?", "%");
            inputMap.put("?COMPANY_NAME?", "%");
            inputMap.put("?COMPANY_STATUS?", "%");
            inputMap.put("?OPERATION?", "%" + Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
            getToInput(inputMap, newDiscountTabDto, true);
            String query = CommonUtil.getQuery(inputMap, "ad.tempCompanySearchCountForCFP");
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
        Map<String, String> inputMap = new HashMap<String, String>();

        inputMap.put("?USERS_SID?", sessionDTO.getUserId());
        inputMap.put("?SESSION_ID?", sessionDTO.getSearchSessionId());

        inputMap.put("?COLUMN_NAME1?", "USERS_SID");
        inputMap.put("?COLUMN_NAME2?", "SESSION_ID");
        inputMap.put("?COLUMN_NAME3?", "OPERATION");

        inputMap.put("?VALUE1?", sessionDTO.getUserId());
        inputMap.put("?VALUE2?", sessionDTO.getSearchSessionId());

        int count = 0;
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.toString()) || searchField.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.toString())) {
            inputMap.put("?TABLE_NAME?", "IMTD_ITEM_PRICE_REBATE_DETAILS");
            String operation = StringUtils.EMPTY;
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.toString())) {
                operation = Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant();
            }
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.toString())) {
                operation = Constants.IndicatorConstants.SELECTED_PS_IFP_FOR_ADD_DISCOUNT.getConstant();
            }
            inputMap.put("?IFP_MODEL_SID?", StringUtils.EMPTY + newDiscountTabDto.getInternalId());
            inputMap.put("?OPERATION?", operation);
            inputMap.put("?VALUE3?", operation);
            String query = CommonUtil.getQuery(inputMap, "ad.tempItemDataInsertFromIFP") + ";";

            try {
                count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(DiscountLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.IFP.toString())) {
            inputMap.put("?TABLE_NAME?", "IMTD_IFP_DETAILS");
            inputMap.put("?ITEM_ID?", "%");
            inputMap.put("?ITEM_NO?", "%");
            inputMap.put("?ITEM_NAME?", "%");
            inputMap.put("?ITEM_STATUS?", "%");
            inputMap.put("?ITEM_TYPE?", "%");
            inputMap.put("?BRAND_MASTER_SID?", "%");
            inputMap.put("?FORM?", "%");
            inputMap.put("?STRENGTH?", "%");
            inputMap.put("?THERAPEUTIC_CLASS?", "%");
            inputMap.put("?OPERATION?", Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
            getToInput(inputMap, newDiscountTabDto, true);
            inputMap.put("?VALUE3?", Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
            String query = CommonUtil.getQuery(inputMap, "ad.tempDataDelete") + ";" + CommonUtil.getQuery(inputMap, "ad.tempDataInsertForIFP") + ";";
            try {
                count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.CFP.toString())) {
            inputMap.put("?TABLE_NAME?", "IMTD_CFP_DETAILS");
            inputMap.put("?COMPANY_ID?", "%");
            inputMap.put("?COMPANY_NO?", "%");
            inputMap.put("?COMPANY_NAME?", "%");
            inputMap.put("?COMPANY_STATUS?", "%");
            inputMap.put("?TRADE_CLASS?", "%");
            inputMap.put("?COMPANY_TYPE?", "%");
            inputMap.put("?COMPANY_CATEGORY?", "%");
            inputMap.put("?OPERATION?", Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
            getToInput(inputMap, newDiscountTabDto, true);
            inputMap.put("?VALUE3?", Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
            String query = CommonUtil.getQuery(inputMap, "ad.tempDataDelete") + ";" + CommonUtil.getQuery(inputMap, "ad.tempDataInsertForCFP") + ";";
            try {
                count = CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        boolean ret = count > 0;
        return ret;
    }

    public List<ContractsDetailsDto> getCommonSearchList(ContractsDetailsDto newDiscountTabDto, SessionDTO sessionDTO) throws Exception {
        List<ContractsDetailsDto> searchList = new ArrayList<ContractsDetailsDto>();
        Map<String, String> inputMap = new HashMap<String, String>();
        String searchField = newDiscountTabDto.getSearchField().split("-")[0];
        inputMap.put("?IFP_NO?", "%");
        inputMap.put("?IFP_NAME?", "%");
        inputMap.put("?IFP_ID?", "%");
        inputMap.put("?IFP_STATUS?", "%");
        inputMap.put("?IFP_TYPE?", "%");
        inputMap.put("?USERS_SID?", sessionDTO.getUserId());
        inputMap.put("?SESSION_ID?", sessionDTO.getSearchSessionId());

        List results = new ArrayList();
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString()) || searchField.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            getToInput(inputMap, newDiscountTabDto, true);
            String query = CommonUtil.getQuery(inputMap, "ad.ifpSearch");
            if (newDiscountTabDto.getEndIndex() > 0) {
                query += "ORDER BY IFP.IFP_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            }
            results = discountDAO.getRebates(query);
        }
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            inputMap.put("?OPERATION?", "%" + Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
            getToInput(inputMap, newDiscountTabDto, true);
            String query = CommonUtil.getQuery(inputMap, "ad.tempItemSearchForIFP");
            if (newDiscountTabDto.getEndIndex() > 0) {
                query += " order by ITEM_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            }
            results = discountDAO.getRebates(query);
        }
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            inputMap.put("?COMPANY_ID?", "%");
            inputMap.put("?COMPANY_NO?", "%");
            inputMap.put("?COMPANY_NAME?", "%");
            inputMap.put("?COMPANY_STATUS?", "%");
            inputMap.put("?OPERATION?", "%" + Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
            getToInput(inputMap, newDiscountTabDto, true);
            String query = CommonUtil.getQuery(inputMap, "ad.tempCompanySearchForCFP");
            if (newDiscountTabDto.getEndIndex() > 0) {
                query += " order by COMPANY_NAME OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            }
            results = discountDAO.getRebates(query);
        }

        searchList = getCustomisedSearchDto(results, searchField);
        return searchList;
    }

    private List<ContractsDetailsDto> getCustomisedSearchDto(List results, String searchField) throws Exception {

        List<ContractsDetailsDto> searchList = new ArrayList<ContractsDetailsDto>();
        int size = results.size();
        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString()) || searchField.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setIfpId(Integer.parseInt(Converters.convertNullToEmpty(arr[0])));
                tabDto.setId(Converters.convertNullToEmpty(arr[1]));
                tabDto.setNumber(Converters.convertNullToEmpty(arr[2]));
                tabDto.setName(Converters.convertNullToEmpty(arr[3]));
                tabDto.setStatus(Converters.convertNullToEmpty((arr[5])));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[6]))) ? null : DBDate.format((Date) arr[6]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[7]))) ? null : DBDate.format((Date) arr[7]));
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
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();

                tabDto.setInternalId(Integer.parseInt(Converters.convertNullToEmpty(arr[0])));
                tabDto.setItemNo(Converters.convertNullToEmpty(arr[1]));
                tabDto.setItemName(Converters.convertNullToEmpty(arr[2]));
                tabDto.setBrand(Converters.convertNullToEmpty(arr[3]));
                tabDto.setStatus(Converters.convertNullToEmpty((arr[5])));
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[7]))) ? null : DBDate.format((Date) arr[7]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[8]))) ? null : DBDate.format((Date) arr[8]));
                tabDto.setCheckRecord(Boolean.valueOf(Converters.convertNullToEmpty(arr[9])));
                tabDto.setAttachedDate(StringUtils.EMPTY);
                tabDto.setSystemId(Integer.valueOf(Converters.convertNullToEmpty(arr[10])));
                tabDto.setCategory(Constants.IndicatorConstants.IFP.getConstant());

                searchList.add(tabDto);
            }
        } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setInternalId(Integer.parseInt(Converters.convertNullToEmpty(arr[0])));
                tabDto.setTradingPartnerNo(Converters.convertNullToEmpty(arr[1]));
                tabDto.setTradingPartnerName(Converters.convertNullToEmpty(arr[2]));
                tabDto.setTpContractNo(StringUtils.EMPTY);
                tabDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[3]))) ? null : DBDate.format((Date) arr[3]));
                tabDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(arr[4]))) ? null : DBDate.format((Date) arr[4]));
                tabDto.setStatus(Converters.convertNullToEmpty((arr[5])));
                tabDto.setTradeClass(Converters.convertNullToEmpty((arr[6])));
                tabDto.setCheckRecord(Boolean.valueOf(Converters.convertNullToEmpty(arr[7])));
                tabDto.setAttachedDate(StringUtils.EMPTY);
                tabDto.setSystemId(Integer.valueOf(Converters.convertNullToEmpty(arr[8])));
                tabDto.setCategory(Constants.IndicatorConstants.CFP.getConstant());
                searchList.add(tabDto);
            }
        }
        return searchList;
    }

    public static void updateTempTableRecord(ContractsDetailsDto newDiscountTabDto, SessionDTO sessionDTO, String propertyId, boolean isSearchTable) {
        Map<String, String> inputMap = new HashMap<String, String>();
        String searchField = newDiscountTabDto.getCategory();

        inputMap.put("?COLUMN_NAME1?", "USERS_SID");
        inputMap.put("?COLUMN_NAME2?", "SESSION_ID");
        inputMap.put("?COLUMN_NAME3?", "OPERATION");

        inputMap.put("?VALUE1?", sessionDTO.getUserId());
        inputMap.put("?VALUE2?", sessionDTO.getSearchSessionId());
        inputMap.put("?EXTRA_COLUMN?", StringUtils.EMPTY);

        if (propertyId.equals(Constants.CHECK_RECORD)) {
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.IFP.toString())) {
                inputMap.put("?SET_COLUMN_NAME?", "CHECK_BOX");
            } else {
                inputMap.put("?SET_COLUMN_NAME?", "CHECK_RECORD");
            }

            inputMap.put("?SET_VALUE?", newDiscountTabDto.getCheckRecord() ? "1" : Constants.ZEROSTRING);
        } else if (propertyId.equals("status")) {
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.IFP.toString())) {
                inputMap.put("?SET_COLUMN_NAME?", "ITEM_STATUS");
            } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.CFP.toString())) {
                inputMap.put("?SET_COLUMN_NAME?", "COMPANY_STATUS");
            } else {
                inputMap.put("?SET_COLUMN_NAME?", "RS_DETAILS_SID");
            }
            inputMap.put("?SET_VALUE?", newDiscountTabDto.getStatus().equals(Constants.ZEROSTRING) ? null : newDiscountTabDto.getStatus());
        } else if (propertyId.equals("sDate")) {
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.IFP.toString())) {
                inputMap.put("?SET_COLUMN_NAME?", "ITEM_START_DATE");
            } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.CFP.toString())) {
                inputMap.put("?SET_COLUMN_NAME?", "COMPANY_START_DATE");
            } else {
                inputMap.put("?SET_COLUMN_NAME?", "ITEM_REBATE_START_DATE");
            }
            String value = !String.valueOf(newDiscountTabDto.getsDate()).equals(Constants.NULL) ? "'" + CommonLogic.convertDateFormat(newDiscountTabDto.getsDate().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()) + "'" : null;
            inputMap.put("?SET_VALUE?", value);
        } else if (propertyId.equals("eDate")) {
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.IFP.toString())) {
                inputMap.put("?SET_COLUMN_NAME?", "ITEM_END_DATE");
            } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.CFP.toString())) {
                inputMap.put("?SET_COLUMN_NAME?", "COMPANY_END_DATE");
            } else {
                inputMap.put("?SET_COLUMN_NAME?", "ITEM_REBATE_END_DATE");
            }
            String value = !String.valueOf(newDiscountTabDto.geteDate()).equals(Constants.NULL) ? "'" + CommonLogic.convertDateFormat(newDiscountTabDto.geteDate().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()) + "'" : null;
            inputMap.put("?SET_VALUE?", value);
        } else if (propertyId.equals("rebatePlan")) {
            inputMap.put("?SET_COLUMN_NAME?", "REBATE_PLAN_SYSTEM_ID");
            String value = (newDiscountTabDto.getRebatePlan().equals(Constants.ZEROSTRING) ? null : (newDiscountTabDto.getRebatePlan()));
            inputMap.put("?SET_VALUE?", value);
            String rebateName = newDiscountTabDto.getRebatePlanName();
            String value1 = (rebateName.equals(StringUtils.EMPTY) ? null : ("'" + rebateName + "'"));
            inputMap.put("?EXTRA_COLUMN?", " , UDC2=" + value1 + " ");
        } else if (propertyId.equals("formulaName")) {
            inputMap.put("?SET_COLUMN_NAME?", "FORMULA_ID");
            inputMap.put("?SET_VALUE?", newDiscountTabDto.getFormulaId().equals(StringUtils.EMPTY) ? null : newDiscountTabDto.getFormulaId());
            String formulaName = newDiscountTabDto.getFormulaName();
            String value = (formulaName.equals(StringUtils.EMPTY) ? null : ("'" + formulaName + "'"));
            inputMap.put("?EXTRA_COLUMN?", " , FORMULA_NAME=" + value + " ");
        } else if (propertyId.equals("priceType")) {
            inputMap.put("?SET_COLUMN_NAME?", "PRICE_TYPE");
            String value = (newDiscountTabDto.getPriceType().equals(Constants.ZEROSTRING) ? null : (newDiscountTabDto.getPriceType()));
            inputMap.put("?SET_VALUE?", value);
        } else if (propertyId.equals("ppSDate")) {
            inputMap.put("?SET_COLUMN_NAME?", "PRICE_PROTECTION_START_DATE");
            String value = !String.valueOf(newDiscountTabDto.getPpSDate()).equals(Constants.NULL) ? "'" + CommonLogic.convertDateFormat(newDiscountTabDto.getPpSDate().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()) + "'" : null;
            inputMap.put("?SET_VALUE?", value);
        } else {
            return;
        }

        if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.getConstant()) || searchField.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.getConstant())) {
            inputMap.put("?TABLE_NAME?", "IMTD_ITEM_PRICE_REBATE_DETAILS");
            inputMap.put("?VALUE3?", Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant());
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.toString())) {
                if (isSearchTable) {
                    inputMap.put("?VALUE3?", "%" + Constants.IndicatorConstants.PS_IFP_FOR_ADD_DISCOUNT.getConstant());
                } else {
                    inputMap.put("?VALUE3?", Constants.IndicatorConstants.SELECTED_PS_IFP_FOR_ADD_DISCOUNT.getConstant());
                }
            } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.toString())) {
                if (isSearchTable) {
                    inputMap.put("?VALUE3?", "%" + Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant());
                } else {
                    inputMap.put("?VALUE3?", Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant());
                }
            }
            String query = CommonUtil.getQuery(inputMap, "ad.tempUpdateRecord");
            if (newDiscountTabDto.isBulkUpdate()) {
                query += " AND CHECK_RECORD=1";
            } else if (!newDiscountTabDto.isCheckAll()) {
                query += " AND IMTD_ITEM_PRICE_REBATE_SID=" + newDiscountTabDto.getSystemId();
                query += " AND IFP_MODEL_SID=" + newDiscountTabDto.getInternalId();
            }
            try {
                CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.IFP.toString())) {
            inputMap.put("?TABLE_NAME?", "IMTD_IFP_DETAILS");
            if (isSearchTable) {
                inputMap.put("?VALUE3?", "%" + Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
            } else {
                inputMap.put("?VALUE3?", Constants.IndicatorConstants.SELECTED_ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
            }
            String query = CommonUtil.getQuery(inputMap, "ad.tempUpdateRecord");
            if (newDiscountTabDto.isBulkUpdate()) {
                query += " AND CHECK_BOX=1";
            } else if (!newDiscountTabDto.isCheckAll()) {
                query += " AND IMTD_IFP_DETAILS_SID=" + newDiscountTabDto.getSystemId();
                query += " AND ITEM_MASTER_SID=" + newDiscountTabDto.getInternalId();
            }
            try {
                CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        } else if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.CFP.toString())) {
            inputMap.put("?TABLE_NAME?", "IMTD_CFP_DETAILS");
            if (isSearchTable) {
                inputMap.put("?VALUE3?", "%" + Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
            } else {
                inputMap.put("?VALUE3?", Constants.IndicatorConstants.SELECTED_COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
            }
            String query = CommonUtil.getQuery(inputMap, "ad.tempUpdateRecord");
            if (newDiscountTabDto.isBulkUpdate()) {
                query += " AND CHECK_RECORD=1";
            } else if (!newDiscountTabDto.isCheckAll()) {
                query += " AND IMTD_CFP_DETAILS_SID=" + newDiscountTabDto.getSystemId();
                query += " AND COMPANY_MASTER_SID=" + newDiscountTabDto.getInternalId();
            }
            try {
                CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }

    public int getCommonCountForSelectedResult(ContractsDetailsDto newDiscountTabDto) throws Exception {
        List results = new ArrayList();
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?SID?", String.valueOf(newDiscountTabDto.getInternalId()));
        if (Constants.IndicatorConstants.RS_VALUE.toString().equals(newDiscountTabDto.getCategory())) {
            String query = CommonUtil.getQuery(inputMap, "ad.ifpFromResults");
            results = discountDAO.getRebates(query);
        }
        return results.size();
    }

    public static int getCountForNewDiscountSelectedItems(ContractsDetailsDto newDiscountTabDto, SessionDTO sessionDTO, boolean selectedItemsTable, boolean addCheckCondition) {
        List results = new ArrayList();
        Map<String, String> inputMap = new HashMap<String, String>();
        String operation = StringUtils.EMPTY;
        String searchField = newDiscountTabDto.getCategory();
        inputMap.put("?USERS_SID?", sessionDTO.getUserId());
        inputMap.put("?SESSION_ID?", sessionDTO.getSearchSessionId());

        if (Constants.IndicatorConstants.PS_VALUE.toString().equals(searchField) || Constants.IndicatorConstants.RS_VALUE.toString().equals(searchField)) {
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.RS_VALUE.toString())) {
                if (selectedItemsTable) {
                    operation = Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant();
                } else {
                    operation = Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant();
                }
            }
            if (searchField.equalsIgnoreCase(Constants.IndicatorConstants.PS_VALUE.toString())) {
                if (selectedItemsTable) {
                    operation = Constants.IndicatorConstants.SELECTED_PS_IFP_FOR_ADD_DISCOUNT.getConstant();
                } else {
                    operation = Constants.IndicatorConstants.PS_IFP_FOR_ADD_DISCOUNT.getConstant();
                }
            }
            inputMap.put("?OPERATION?", operation);
            String query = CommonUtil.getQuery(inputMap, "ad.tempItemSearchCount");
            if (addCheckCondition) {
                query += " AND CHECK_RECORD=1";
            }
            try {
                results = discountDAO.getRebates(query);
            } catch (SystemException ex) {
                java.util.logging.Logger.getLogger(DiscountLogic.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(DiscountLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (Constants.IndicatorConstants.CFP.getConstant().equals(newDiscountTabDto.getCategory())) {
            if (selectedItemsTable) {
                operation = Constants.IndicatorConstants.SELECTED_COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
            } else {
                operation = Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
            }
            inputMap.put("?OPERATION?", operation);
            String query = CommonUtil.getQuery(inputMap, "ad.selectedCompaniesFromSearchTableCount");
            try {
                results = discountDAO.getRebates(query);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        } else if (Constants.IndicatorConstants.IFP.toString().equals(newDiscountTabDto.getCategory())) {
            if (selectedItemsTable) {
                operation = Constants.IndicatorConstants.SELECTED_ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
            } else {
                operation = Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
            }
            inputMap.put("?OPERATION?", operation);
            String query = CommonUtil.getQuery(inputMap, "ad.selectedItemsFromSearchTableCount");
            try {
                results = discountDAO.getRebates(query);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        Object obj = null;
        if (!results.isEmpty()) {
            obj = results.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    public static List<ContractsDetailsDto> getCommonResults(ContractsDetailsDto newDiscountTabDto, SessionDTO sessionDTO, boolean isSelectedTable) throws Exception {
        LOGGER.info("Inside getCommonResults ");
        List<ContractsDetailsDto> searchList = new ArrayList<ContractsDetailsDto>();
        Map<String, String> inputMap = new HashMap<String, String>();
        List results = new ArrayList();
        String operation = StringUtils.EMPTY;
        String searchField = newDiscountTabDto.getCategory();
        inputMap.put("?USERS_SID?", sessionDTO.getUserId());
        inputMap.put("?SESSION_ID?", sessionDTO.getSearchSessionId());

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
            inputMap.put("?OPERATION?", operation);
            String query = CommonUtil.getQuery(inputMap, "ad.tempItemSearch");
            query += "ORDER BY IMTD_ITEM_PRICE_REBATE_SID";
            if (newDiscountTabDto.getEndIndex() > 0) {
                query += " OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            }
            results = discountDAO.getRebates(query);
        }

        if (Constants.IndicatorConstants.CFP.getConstant().equals(newDiscountTabDto.getCategory())) {
            if (isSelectedTable) {
                operation = Constants.IndicatorConstants.SELECTED_COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
            } else {
                operation = Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant();
            }
            inputMap.put("?OPERATION?", operation);
            String query = CommonUtil.getQuery(inputMap, "ad.selectedCompaniesFromSearchTable");
            query += "ORDER BY IMTD_CFP_DETAILS_SID ";
            if (newDiscountTabDto.getEndIndex() > 0) {
                query += " OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            }
            results = discountDAO.getRebates(query);
        } else if (Constants.IndicatorConstants.IFP.getConstant().equals(newDiscountTabDto.getCategory())) {
            if (isSelectedTable) {
                operation = Constants.IndicatorConstants.SELECTED_ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
            } else {
                operation = Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant();
            }
            inputMap.put("?OPERATION?", operation);
            String query = CommonUtil.getQuery(inputMap, "ad.selectedItemsFromSearchTable");
            query += "ORDER BY IMTD_IFP_DETAILS_SID ";
            if (newDiscountTabDto.getEndIndex() > 0) {
                query += " OFFSET " + newDiscountTabDto.getStartIndex() + "  ROWS FETCH NEXT " + newDiscountTabDto.getEndIndex() + " ROWS ONLY";
            }
            results = discountDAO.getRebates(query);
        }
        searchList = getDetailsFromSelectedResult(results, newDiscountTabDto);
        LOGGER.info("Exting getCommonResults ");
        return searchList;
    }

    private static List<ContractsDetailsDto> getDetailsFromSelectedResult(List results, ContractsDetailsDto newDiscountTabDto) {
        List<ContractsDetailsDto> searchList = new ArrayList<ContractsDetailsDto>();

        int size = results.size();
        if (Constants.IndicatorConstants.RS_VALUE.getConstant().equals(newDiscountTabDto.getCategory()) || Constants.IndicatorConstants.PS_VALUE.getConstant().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setSystemId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setId(Converters.convertNullToEmpty(arr[2]));
                tabDto.setItemId(Converters.convertNullToEmpty(arr[3]));
                tabDto.setItemNo(Converters.convertNullToEmpty(arr[4]));
                tabDto.setItemName(Converters.convertNullToEmpty(arr[5]));
                tabDto.setTherapyClass(Converters.convertNullToEmpty(arr[6]));
                tabDto.setBrand(Converters.convertNullToEmpty(arr[8]));
                tabDto.setHelperTableSid(Converters.convertNullToEmpty(arr[5]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[7]));
                tabDto.setTempStatus(Converters.convertNullToEmpty(arr[7]));
                Date dt1 = !String.valueOf(arr[9]).equals(Constants.NULL) ? new Date(CommonLogic.convertDateFormat(arr[9].toString(), DEFOULT_SQL_DATE_FORMAT.getConstant(), MMddyyyy.getConstant())) : null;

                tabDto.setsDate(dt1);
                tabDto.setTempSDate(dt1);
                Date dt2 = !String.valueOf(arr[10]).equals(Constants.NULL) ? new Date(CommonLogic.convertDateFormat(arr[10].toString(), DEFOULT_SQL_DATE_FORMAT.getConstant(), MMddyyyy.getConstant())) : null;

                tabDto.seteDate(dt2);
                tabDto.setTempEDate(dt2);
                tabDto.setRebatePlan(Converters.convertNullToEmpty(arr[11]));
                tabDto.setTempRebatePlan(Converters.convertNullToEmpty(arr[11]));
                tabDto.setRebatePlanName(Converters.convertNullToEmpty(arr[12]));
                tabDto.setFormulaId(Converters.convertNullToEmpty(arr[13]));
                tabDto.setTempFormulaId(Converters.convertNullToEmpty(arr[13]));
                tabDto.setFormulaName(Converters.convertNullToEmpty(arr[14]));
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[1])));
                tabDto.setCategory(newDiscountTabDto.getCategory());
                tabDto.setCheckRecord(Boolean.valueOf(Converters.convertNullToEmpty(arr[15])));
                searchList.add(tabDto);
            }
        } else if (Constants.IndicatorConstants.IFP.getConstant().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setSystemId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setItemNo(Converters.convertNullToEmpty(arr[1]));
                tabDto.setItemName(Converters.convertNullToEmpty(arr[2]));
                tabDto.setTherapyClass(Converters.convertNullToEmpty(arr[6]));
                tabDto.setBrand(Converters.convertNullToEmpty(arr[3]));

                tabDto.setStatus(Converters.convertNullToEmpty(arr[4]));
                tabDto.setTempStatus(Converters.convertNullToEmpty(arr[4]));
                Date dt1 = !String.valueOf(arr[7]).equals(Constants.NULL) ? new Date(CommonLogic.convertDateFormat(arr[7].toString(), DEFOULT_SQL_DATE_FORMAT.getConstant(), MMddyyyy.getConstant())) : null;

                tabDto.setsDate(dt1);
                tabDto.setTempSDate(dt1);
                Date dt2 = !String.valueOf(arr[8]).equals(Constants.NULL) ? new Date(CommonLogic.convertDateFormat(arr[8].toString(), DEFOULT_SQL_DATE_FORMAT.getConstant(), MMddyyyy.getConstant())) : null;

                tabDto.seteDate(dt2);
                tabDto.setTempEDate(dt2);
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[10])));
                tabDto.setCategory(newDiscountTabDto.getCategory());
                tabDto.setCheckRecord(Boolean.valueOf(Converters.convertNullToEmpty(arr[9])));
                searchList.add(tabDto);
            }
        } else if (Constants.IndicatorConstants.CFP.toString().equals(newDiscountTabDto.getCategory())) {
            for (int i = 0; i < size; i++) {
                Object arr[] = (Object[]) results.get(i);
                ContractsDetailsDto tabDto = new ContractsDetailsDto();
                tabDto.setSystemId(Integer.valueOf(Converters.convertNullToEmpty(arr[0])));
                tabDto.setCompanyNo(Converters.convertNullToEmpty(arr[1]));
                tabDto.setCompanyName(Converters.convertNullToEmpty(arr[2]));
                tabDto.setStatus(Converters.convertNullToEmpty(arr[3]));
                tabDto.setTempStatus(Converters.convertNullToEmpty(arr[3]));
                Date dt1 = !String.valueOf(arr[4]).equals(Constants.NULL) ? new Date(CommonLogic.convertDateFormat(arr[4].toString(), DEFOULT_SQL_DATE_FORMAT.getConstant(), MMddyyyy.getConstant())) : null;

                tabDto.setsDate(dt1);
                tabDto.setTempSDate(dt1);
                Date dt2 = !String.valueOf(arr[5]).equals(Constants.NULL) ? new Date(CommonLogic.convertDateFormat(arr[5].toString(), DEFOULT_SQL_DATE_FORMAT.getConstant(), MMddyyyy.getConstant())) : null;

                tabDto.seteDate(dt2);
                tabDto.setTempEDate(dt2);
                tabDto.setInternalId(Integer.valueOf(Converters.convertNullToEmpty(arr[7])));
                tabDto.setCategory(newDiscountTabDto.getCategory());
                tabDto.setCheckRecord(Boolean.valueOf(Converters.convertNullToEmpty(arr[6])));
                searchList.add(tabDto);
            }
        }

        return searchList;

    }

    public List<HelperDTO> getDropDownList(final String listType, HelperDTO dto) throws SystemException {
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final DynamicQuery helperTableQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
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
        rsModel.setPaymentFrequency(Integer.valueOf(saveDto.getPaymentFrequency()));
        rsModel.setRsStatus(Integer.valueOf(saveDto.getStatus()));
        rsModel.setRsStartDate(new Date(saveDto.getStartDate()));
        rsModel.setRsEndDate(new Date(saveDto.getEndDate()));
        rsModel.setPaymentMethod(Integer.valueOf(saveDto.getPaymentMethod()));
        rsModel.setRsType(Integer.valueOf(saveDto.getRebateType()));
        rsModel.setRebateProgramType(Integer.valueOf(saveDto.getRebateProgramType()));
        rsModel.setRebatePlanLevel(saveDto.getRebatePlan());
        rsModel.setCreatedDate(new Date());
        rsModel.setCreatedBy(Integer.valueOf(userId));
        rsModel.setModifiedDate(new Date());
        rsModel.setModifiedBy(Integer.valueOf(userId));
        try {
            rsModel = RsModelLocalServiceUtil.addRsModel(rsModel);
            saveIntoRsDetails(rsModel, userId, tempSessionId);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }

        return rsModel.getRsModelSid();

    }

    public int getFormulaSearchCount(LookupDTO binderDto) throws Exception {
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?FORMULA_ID?", "%");
        inputMap.put("?FORMULA_NO?", "%");
        inputMap.put("?FORMULA_DESC?", "%");
        List results = new ArrayList();
        if (StringUtils.isNotBlank(binderDto.getFormulaId())) {
            inputMap.put("?FORMULA_ID?", CommonUtil.astToPerConverter(binderDto.getFormulaId()));
        }
        if (StringUtils.isNotBlank(binderDto.getFormulaNo())) {
            inputMap.put("?FORMULA_NO?", CommonUtil.astToPerConverter(binderDto.getFormulaNo()));
        }
        if (StringUtils.isNotBlank(binderDto.getFormulaName())) {
            inputMap.put("?FORMULA_DESC?", CommonUtil.astToPerConverter(binderDto.getFormulaName()));
        }
        String query = CommonUtil.getQuery(inputMap, "ad.formulaSearchCount");
        results = discountDAO.getRebates(query);
        Object obj = null;
        if (!results.isEmpty()) {
            obj = results.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    public List<LookupDTO> getFormulaSearchResults(LookupDTO binderDto) throws Exception {
        Map<String, String> inputMap = new HashMap<String, String>();
        List<LookupDTO> resultsList = new ArrayList<LookupDTO>();
        inputMap.put("?FORMULA_ID?", "%");
        inputMap.put("?FORMULA_NO?", "%");
        inputMap.put("?FORMULA_DESC?", "%");
        List results = new ArrayList();
        if (StringUtils.isNotBlank(binderDto.getFormulaId())) {
            inputMap.put("?FORMULA_ID?", CommonUtil.astToPerConverter(binderDto.getFormulaId()));
        }
        if (StringUtils.isNotBlank(binderDto.getFormulaNo())) {
            inputMap.put("?FORMULA_NO?", CommonUtil.astToPerConverter(binderDto.getFormulaNo()));
        }
        if (StringUtils.isNotBlank(binderDto.getFormulaName())) {
            inputMap.put("?FORMULA_DESC?", CommonUtil.astToPerConverter(binderDto.getFormulaName()));
        }
        String query = CommonUtil.getQuery(inputMap, "ad.formulaSearch");
        query += " ORDER BY FORMULA_DETAILS_MASTER_SID OFFSET " + binderDto.getStartIndex() + "  ROWS FETCH NEXT " + binderDto.getEndIndex() + " ROWS ONLY ;";
        results = discountDAO.getRebates(query);
        if (!results.isEmpty()) {
            resultsList = getLookUpDtos(results, false);
        }
        return resultsList;
    }

    private List<LookupDTO> getLookUpDtos(List results, boolean isRebate) {
        int size = results.size();
        List<LookupDTO> resultsList = new ArrayList<LookupDTO>();
        for (int i = 0; i < size; i++) {
            Object arr[] = (Object[]) results.get(i);
            LookupDTO dTo = new LookupDTO();
            if (isRebate) {
                dTo.setRebatePlanSysId(Converters.convertNullToEmpty(arr[0]));
                dTo.setRebatePlanId(Converters.convertNullToEmpty(arr[1]));
                dTo.setRebatePlanNo(Converters.convertNullToEmpty(arr[2]));
                dTo.setRebatePlanName(Converters.convertNullToEmpty(arr[3]));
                dTo.setRebatePlanStatus(Converters.convertNullToEmpty(arr[4]));
                dTo.setRebatePlanType(Converters.convertNullToEmpty(arr[5]));
            } else {
                dTo.setFormulaSysId(Converters.convertNullToEmpty(arr[0]));
                dTo.setFormulaId(Converters.convertNullToEmpty(arr[1]));
                dTo.setFormulaNo(Converters.convertNullToEmpty(arr[2]));
                dTo.setFormulaName(Converters.convertNullToEmpty(arr[3]));
            }

            resultsList.add(dTo);

        }
        return resultsList;
    }

    public int getRebateSearchCount(LookupDTO binderDto) throws Exception {
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?REBATE_PLAN_ID?", "%");
        inputMap.put("?REBATE_PLAN_NO?", "%");
        inputMap.put("?REBATE_PLAN_NAME?", "%");
        inputMap.put("?REBATE_PLAN_STATUS?", "%");
        inputMap.put("?REBATE_PLAN_TYPE?", "%");
        List results = new ArrayList();
        if (StringUtils.isNotBlank(binderDto.getRebatePlanId())) {
            inputMap.put("?REBATE_PLAN_ID?", CommonUtil.astToPerConverter(binderDto.getRebatePlanId()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanNo())) {
            inputMap.put("?REBATE_PLAN_NO?", CommonUtil.astToPerConverter(binderDto.getRebatePlanNo()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanName())) {
            inputMap.put("?REBATE_PLAN_NAME?", CommonUtil.astToPerConverter(binderDto.getRebatePlanName()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanStatus())) {
            inputMap.put("?REBATE_PLAN_STATUS?", CommonUtil.astToPerConverter(binderDto.getRebatePlanStatus()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanType())) {
            inputMap.put("?REBATE_PLAN_TYPE?", CommonUtil.astToPerConverter(binderDto.getRebatePlanType()));
        }
        String query = CommonUtil.getQuery(inputMap, "ad.rebateSearchCount");
        results = discountDAO.getRebates(query);
        Object obj = null;
        if (!results.isEmpty()) {
            obj = results.get(0);
        }
        return obj == null ? 0 : (Integer) obj;
    }

    public List<LookupDTO> getRebateSearchResults(LookupDTO binderDto) throws Exception {
        Map<String, String> inputMap = new HashMap<String, String>();
        List<LookupDTO> resultsList = new ArrayList<LookupDTO>();
        inputMap.put("?REBATE_PLAN_ID?", "%");
        inputMap.put("?REBATE_PLAN_NO?", "%");
        inputMap.put("?REBATE_PLAN_NAME?", "%");
        inputMap.put("?REBATE_PLAN_STATUS?", "%");
        inputMap.put("?REBATE_PLAN_TYPE?", "%");
        List results = new ArrayList();
        if (StringUtils.isNotBlank(binderDto.getRebatePlanId())) {
            inputMap.put("?REBATE_PLAN_ID?", CommonUtil.astToPerConverter(binderDto.getRebatePlanId()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanNo())) {
            inputMap.put("?REBATE_PLAN_NO?", CommonUtil.astToPerConverter(binderDto.getRebatePlanNo()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanName())) {
            inputMap.put("?REBATE_PLAN_NAME?", CommonUtil.astToPerConverter(binderDto.getRebatePlanName()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanStatus())) {
            inputMap.put("?REBATE_PLAN_STATUS?", CommonUtil.astToPerConverter(binderDto.getRebatePlanStatus()));
        }
        if (StringUtils.isNotBlank(binderDto.getRebatePlanType())) {
            inputMap.put("?REBATE_PLAN_TYPE?", CommonUtil.astToPerConverter(binderDto.getRebatePlanType()));
        }
        String query = CommonUtil.getQuery(inputMap, "ad.rebateSearch");
        query += " ORDER BY REBATE_PLAN_MASTER_SID OFFSET " + binderDto.getStartIndex() + "  ROWS FETCH NEXT " + binderDto.getEndIndex() + " ROWS ONLY ;";
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
            List results = new ArrayList();
            results = discountDAO.getRebates(actualQuery);
            int count = results.size();
            if (count != 0) {

                Object arr[] = (Object[]) results.get(0);
                Double sales = Double.valueOf(String.valueOf(arr[0]));
                Double units = Double.valueOf(String.valueOf(arr[1]));

                if (ACTUAL_FORMAT.format(sales) != "0.00" || ACTUAL_FORMAT.format(units) != "0.00") {
                    actual = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        return actual;
    }

    public static void saveIntoRsDetails(RsModel rsModel, String userId, String sessionId) {
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?USERS_SID?", userId);
        inputMap.put("?SESSION_ID?", sessionId);
        inputMap.put("?RS_MODEL_SID?", String.valueOf(rsModel.getRsModelSid()));
        inputMap.put("?CREATED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?MODIFIED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?ITEM_RS_ATTACHED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?OPERATION?", Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant());
        String query = CommonUtil.getQuery(inputMap, "ad.insertintoRsDetailsFromTemp");
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
        deleteTempData(userId, sessionId, Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
    }

    public static int saveNewPriceToPsModel(ContractsDetailsDto saveDto, String userId, String searchSessionId) {
        PsModel rsModel = PsModelLocalServiceUtil.createPsModel(0);
        rsModel.setPsId(saveDto.getId());
        rsModel.setPsName(saveDto.getName());
        rsModel.setPsNo(saveDto.getNumber());
        rsModel.setPsStatus(Integer.valueOf(saveDto.getStatus()));
        rsModel.setPsStartDate(new Date(saveDto.getStartDate()));
        rsModel.setInboundStatus("A");
        rsModel.setCreatedDate(new Date());
        rsModel.setCreatedBy(Integer.valueOf(userId));
        rsModel.setModifiedDate(new Date());
        rsModel.setModifiedBy(Integer.valueOf(userId));
        try {
            rsModel = PsModelLocalServiceUtil.addPsModel(rsModel);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        saveIntoPsDetails(rsModel, userId, searchSessionId);
        return rsModel.getPsModelSid();

    }

    public static int saveNewIfpToIFPModel(ContractsDetailsDto saveDto, String userId, String searchSessionId) {
        IfpModel ifpmodel = IfpModelLocalServiceUtil.createIfpModel(0);
        ifpmodel.setIfpId(saveDto.getId());
        ifpmodel.setIfpName(saveDto.getName());
        ifpmodel.setIfpNo(saveDto.getNumber());
        ifpmodel.setIfpStatus(Integer.valueOf(saveDto.getStatus()));
        ifpmodel.setIfpStartDate(new Date(saveDto.getStartDate()));
        ifpmodel.setInboundStatus("A");
        ifpmodel.setCreatedDate(new Date());
        ifpmodel.setCreatedBy(Integer.valueOf(userId));
        ifpmodel.setModifiedDate(new Date());
        ifpmodel.setModifiedBy(Integer.valueOf(userId));
        try {
            ifpmodel = IfpModelLocalServiceUtil.addIfpModel(ifpmodel);
            saveIntoIfpDetails(ifpmodel, userId, searchSessionId);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return ifpmodel.getIfpModelSid();
    }

    public static void saveIntoIfpDetails(IfpModel ifpmodel, String userId, String sessionId) {
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?USERS_SID?", userId);
        inputMap.put("?SESSION_ID?", sessionId);
        inputMap.put("?IFP_MODEL_SID?", String.valueOf(ifpmodel.getIfpModelSid()));
        inputMap.put("?CREATED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?MODIFIED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?ITEM_IFP_ATTACHED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?OPERATION?", Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
        String query = CommonUtil.getQuery(inputMap, "ad.insertintoIFPDetailsFromTemp");
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
        deleteTempData(userId, sessionId, Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant());
    }

    public static void saveIntoPsDetails(PsModel psModel, String userId, String sessionId) {
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?USERS_SID?", userId);
        inputMap.put("?SESSION_ID?", sessionId);
        inputMap.put("?PS_MODEL_SID?", String.valueOf(psModel.getPsModelSid()));
        inputMap.put("?CREATED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?MODIFIED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?OPERATION?", Constants.IndicatorConstants.PS_IFP_FOR_ADD_DISCOUNT.getConstant());

        String query = CommonUtil.getQuery(inputMap, "ad.insertintoPsDetailsFromTemp");
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
        deleteTempData(userId, sessionId, Constants.IndicatorConstants.PS_IFP_FOR_ADD_DISCOUNT.getConstant());
    }

    public static int saveNewCfpToCFPModel(ContractsDetailsDto saveDto, String userId, String searchSessionId) {
        CfpModel cfpmodel = CfpModelLocalServiceUtil.createCfpModel(0);
        cfpmodel.setCfpId(saveDto.getId());
        cfpmodel.setCfpName(saveDto.getName());
        cfpmodel.setCfpNo(saveDto.getNumber());
        cfpmodel.setCfpStatus(Integer.valueOf(saveDto.getStatus()));
        cfpmodel.setCfpStartDate(new Date(saveDto.getStartDate()));
        cfpmodel.setInboundStatus("A");
        cfpmodel.setCreatedDate(new Date());
        cfpmodel.setCreatedBy(Integer.valueOf(userId));
        cfpmodel.setModifiedDate(new Date());
        cfpmodel.setModifiedBy(Integer.valueOf(userId));
        try {
            cfpmodel = CfpModelLocalServiceUtil.addCfpModel(cfpmodel);
            saveIntoCfpDetails(cfpmodel, userId, searchSessionId);
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return cfpmodel.getCfpModelSid();
    }

    public static void saveIntoCfpDetails(CfpModel cfpmodel, String userId, String sessionId) {
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?USERS_SID?", userId);
        inputMap.put("?SESSION_ID?", sessionId);
        inputMap.put("?CFP_MODEL_SID?", String.valueOf(cfpmodel.getCfpModelSid()));
        inputMap.put("?CREATED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?MODIFIED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?COMPANY_CFP_ATTACHED_DATE?", CommonLogic.convertDateFormat(new Date().toString(), DEFOULT_JAVA_DATE_FORMAT.getConstant(), DEFOULT_SQL_DATE_FORMAT.getConstant()));
        inputMap.put("?OPERATION?", Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant());
        String query = CommonUtil.getQuery(inputMap, "ad.insertintoCFPDetailsFromTemp");
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
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
    public static void savePS(final int contractSystemId, final int cfpSystemId, final int ifpSystemId, final ContractsDetailsDto contractMember) throws SystemException, PortalException {
        LOGGER.info("Entering getChildNodeOfPriorContractSales method with contractSystemId=" + contractSystemId);
        try {
            int psId = 0;
            if (!"0".equals(contractMember.getPsSid())) {
                psId = Integer.valueOf(contractMember.getPsSid());
            }
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

            psMasterAttached.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
            psMasterAttached.setCreatedDate(new Date());
            psMasterAttached.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
            psMasterAttached.setModifiedDate(new Date());
            psMasterAttached.setRecordLockStatus(false);
            psMasterAttached.setInboundStatus("A");

            DynamicQuery query = DynamicQueryFactoryUtil.forClass(PsContract.class);
            query.add(RestrictionsFactoryUtil.eq("contractMasterSid", contractSystemId));
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
//                psMasterAttached.setPsContractSid(list.get(0).getPsContractSid());
//
//                psMasterAttached.setContractMasterSid(contractSystemId);
//                psMasterAttached.setCfpContractSid((cfpSystemId != 0) ? String.valueOf(cfpSystemId) : null);
//                psMasterAttached.setIfpContractSid((ifpSystemId != 0) ? String.valueOf(ifpSystemId) : null);
//                psMasterAttached.setPsModelSid(psId);

//                psContract = discountDAO.updatePsMasterAttached(psMasterAttached);
//
//            } else {
                psMasterAttached.setContractMasterSid(contractSystemId);
                psMasterAttached.setCfpContractSid((cfpSystemId != 0) ? String.valueOf(cfpSystemId) : null);
                psMasterAttached.setIfpContractSid((ifpSystemId != 0) ? String.valueOf(ifpSystemId) : null);
                psMasterAttached.setPsModelSid(psId);

                psContract = discountDAO.addPsMasterAttached(psMasterAttached);
                contractMember.setPsContractId(psContract.getPsContractSid());
            } else {
                contractMember.setPsContractId(list.get(0).getPsContractSid());
            }

            List<Object> input = new ArrayList<Object>(8);
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
                PsContractDetailsLocalServiceUtil.savePsDetailsAttached(input, null);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void deleteTempData(String userId, String sessionId, String operation) {
        Map<String, String> inputMap = new HashMap<String, String>();
        if (operation.equalsIgnoreCase(Constants.IndicatorConstants.ITEMS_FOR_IFP_IN_ADD_DISCOUNT.getConstant())) {
            inputMap.put("?TABLE_NAME?", "IMTD_IFP_DETAILS");
        } else if (operation.equalsIgnoreCase(Constants.IndicatorConstants.COMPANYS_FOR_CFP_IN_ADD_DISCOUNT.getConstant())) {
            inputMap.put("?TABLE_NAME?", "IMTD_CFP_DETAILS");
        } else {
            inputMap.put("?TABLE_NAME?", "IMTD_ITEM_PRICE_REBATE_DETAILS");
        }
        inputMap.put("?COLUMN_NAME1?", "USERS_SID");
        inputMap.put("?COLUMN_NAME2?", "SESSION_ID");
        inputMap.put("?COLUMN_NAME3?", "OPERATION");
        inputMap.put("?VALUE1?", userId);
        inputMap.put("?VALUE2?", sessionId);
        inputMap.put("?VALUE3?", "%" + operation);
        String query = CommonUtil.getQuery(inputMap, "ad.tempDataDelete");
        CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
    }

    public static List duplicateCheck(String operation, String name, String fieldName) {
        List list = null;
        String query = StringUtils.EMPTY;
        if (operation.equalsIgnoreCase(Constants.IndicatorConstants.PRICE_SCHEDULE.toString())) {
            query = "select * from dbo.PS_MODEL where " + fieldName + " = '" + name + "'" + "and INBOUND_STATUS <> 'D'";
        } else if (operation.equalsIgnoreCase(Constants.IndicatorConstants.COMPANY_FAMILY_PLAN.toString())) {
            query = "select * from dbo.CFP_MODEL where " + fieldName + " = '" + name + "'" + "and INBOUND_STATUS <> 'D'";
        } else if (operation.equalsIgnoreCase(Constants.IndicatorConstants.ITEM_FAMILY_PLAN.toString())) {
            query = "select * from dbo.IFP_MODEL where " + fieldName + " = '" + name + "'" + "and INBOUND_STATUS <> 'D'";
        } else if (operation.equalsIgnoreCase(Constants.IndicatorConstants.REBATE_SCHEDULE.toString())) {
            query = "select * from dbo.RS_MODEL where " + fieldName + " = '" + name + "'" + "and INBOUND_STATUS <> 'D'";
        }
        list = CompanyMasterLocalServiceUtil.executeQuery(query);
        return list;

    }

    public static void saveIFP(final int contractSystemId, final int cfpSystemId, final ContractsDetailsDto contractMember) throws SystemException, PortalException {

        LOGGER.info("Entering saveIFP method with contractSystemId=" + contractSystemId);

        final IfpModel itemFamily = discountDAO.getItemFamilyPlanMaster(contractMember.getIfpId());
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
        ifpMasterAttached.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        ifpMasterAttached.setCreatedDate(new Date());
        ifpMasterAttached.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        ifpMasterAttached.setModifiedDate(new Date());
        ifpMasterAttached.setRecordLockStatus(false);
        ifpMasterAttached.setInboundStatus("A");

        DynamicQuery query = DynamicQueryFactoryUtil.forClass(IfpContract.class);
        query.add(RestrictionsFactoryUtil.eq("contractMasterSid", contractSystemId));
        if (cfpSystemId != 0) {
            query.add(RestrictionsFactoryUtil.ilike(Constants.CFP_CONTRACT_SID, String.valueOf(cfpSystemId)));
        } else {
            query.add(RestrictionsFactoryUtil.isNull(Constants.CFP_CONTRACT_SID));
        }
        query.add(RestrictionsFactoryUtil.eq("ifpModelSid", contractMember.getIfpId()));
        List<IfpContract> list = IfpContractLocalServiceUtil.dynamicQuery(query);
        IfpContract ifpContract = IfpContractLocalServiceUtil.createIfpContract(0);

        if (list.isEmpty()) {
//            ifpMasterAttached.setIfpContractSid(list.get(0).getIfpContractSid());
//
//            ifpMasterAttached.setContractMasterSid(contractSystemId);
//
//            ifpMasterAttached.setCfpContractSid((cfpSystemId != 0) ? String.valueOf(cfpSystemId) : null);
//
//            ifpMasterAttached.setIfpModelSid(contractMember.getIfpId());
//
//            ifpContract = discountDAO.updateIfpMasterAttached(ifpMasterAttached);
//
//        } else {

            ifpMasterAttached.setContractMasterSid(contractSystemId);

            ifpMasterAttached.setCfpContractSid((cfpSystemId != 0) ? String.valueOf(cfpSystemId) : null);

            ifpMasterAttached.setIfpModelSid(contractMember.getIfpId());

            ifpContract = discountDAO.addIfpMasterAttached(ifpMasterAttached);
            contractMember.setIfpContractId(ifpContract.getIfpContractSid());
        } else {
            contractMember.setIfpContractId(list.get(0).getIfpContractSid());
        }
        List<Object> input = new ArrayList<Object>(8);
        input.add(ifpContract.getIfpContractSid());
        input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        input.add(DBDate.format(new Date()));
        input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        input.add(DBDate.format(new Date()));
        input.add(contractMember.getIfpId());
        input.add(DBDate.format(itemFamily.getIfpStartDate()));
        if (itemFamily.getIfpEndDate() != null) {
            input.add(DBDate.format(itemFamily.getIfpEndDate()));
        } else {
            input.add(null);
        }
        if (list.isEmpty()) {
            IfpContractDetailsLocalServiceUtil.saveIfpDetailsAttached(input, null);
        }

        LOGGER.info("End of saveIFP method");
    }

    /**
     * to save CFp
     *
     * @param contractSystemId
     * @param contractMember
     * @throws SystemException
     * @throws PortalException
     */
    public static void saveCFp(final int contractSystemId, final ContractsDetailsDto contractMember) throws SystemException, PortalException {
        LOGGER.info("Entering saveCFp method with contractSystemId=" + contractSystemId + "======" + contractMember.getCfpId());

        final CfpModel companyFamily = discountDAO.getCompanyFamilyplanMaster(contractMember.getCfpId());
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
        cfpMasterAttached.setCreatedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        cfpMasterAttached.setCreatedDate(new Date());
        cfpMasterAttached.setModifiedBy(Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(Constants.USER_ID)));
        cfpMasterAttached.setModifiedDate(new Date());
        cfpMasterAttached.setCfpContractAttachedDate(new Date());
        cfpMasterAttached.setRecordLockStatus(false);
        cfpMasterAttached.setInboundStatus("A");

        DynamicQuery query = DynamicQueryFactoryUtil.forClass(CfpContract.class);
        query.add(RestrictionsFactoryUtil.eq("contractMasterSid", contractSystemId));
        query.add(RestrictionsFactoryUtil.eq("cfpModelSid", contractMember.getCfpId()));
        List<CfpContract> list = CfpContractLocalServiceUtil.dynamicQuery(query);
        CfpContract cfpContract = CfpContractLocalServiceUtil.createCfpContract(0);

        if (list.isEmpty()) {
//            cfpMasterAttached.setCfpContractSid(list.get(0).getCfpContractSid());
//
//            cfpMasterAttached.setContractMasterSid(contractSystemId);
//            cfpMasterAttached.setCfpModelSid(contractMember.getCfpId());
//
//            cfpContract = discountDAO.updateCfpMasterAttached(cfpMasterAttached);
////
//        } else {

            cfpMasterAttached.setContractMasterSid(contractSystemId);
            cfpMasterAttached.setCfpModelSid(contractMember.getCfpId());
            cfpContract = discountDAO.addCfpMasterAttached(cfpMasterAttached);
            contractMember.setCfpContractId(cfpContract.getCfpContractSid());
        } else {
            contractMember.setCfpContractId(cfpContract.getCfpContractSid());
        }
        List<Object> input = new ArrayList<Object>(8);
        input.add(cfpContract.getCfpContractSid());
        input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        input.add(DBDate.format(new Date()));
        input.add(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
        input.add(DBDate.format(new Date()));
        input.add(contractMember.getCfpId());
        input.add(DBDate.format(companyFamily.getCfpStartDate()));
        if (companyFamily.getCfpEndDate() != null) {
            input.add(DBDate.format(companyFamily.getCfpEndDate()));
        } else {
            input.add(null);
        }
        if (list.isEmpty()) {
            CfpContractDetailsLocalServiceUtil.saveCfpDetailsAttached(input, null);
        }
        LOGGER.info("End of saveCFp method");

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

    public static void createSessionID(RemoveDiscountDto dto) {
        final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
        Date sessionDate = new Date();
        dto.setSessionId(Integer.valueOf(fmtID.format(sessionDate)));
        dto.setUserId(Integer.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute("userId"))));
    }

    public int getSelectedContractCount(int userId, int sessionId, List<String> rebateList, final String indicator, java.util.Set<Container.Filter> filterSet) {
        int count = 0;
        String query = CustomSQLUtil.get("rd.selectedContractCount");
        StringBuilder filterSql = new StringBuilder();
        query = query.replace("[$USERID]", String.valueOf(userId));
        query = query.replace("[$SESSIONID]", String.valueOf(sessionId));
        if (!rebateList.isEmpty()) {
            query = query.replace("?", ItemQueries.getQuery(setRebateInput(rebateList), "rebateSub"));
        } else {
            query = query.replace("?", " ");
        }
        if ("contract".equals(indicator)) {
            filterSql = AbstractFilterLogic.getInstance().contractfilterQueryGenerator(filterSet);
        } else if ("summary".equals(indicator)) {
            filterSql = AbstractFilterLogic.getInstance().summaryfilterQueryGenerator(filterSet);
        }
        LOGGER.info("filterSql ->" + filterSql);
        if (filterSql != null) {
            query = query.replace("@filter@", filterSql.toString().replace("where", " and "));
        } else {
            query = query.replace("@filter@", " ");
        }
        LOGGER.info("Count fetch query ->" + query);
        List list = CompanyMasterLocalServiceUtil.executeQuery(query);
        count = list != null ? Integer.parseInt(String.valueOf(list.get(0))) : 0;
        return count;
    }

    public List<RemoveDiscountDto> getSelectedContract(int start, int offset, int userId, int sessionId, List<String> rebateList, final String indicator, java.util.Set<Container.Filter> filterSet) {
        List<RemoveDiscountDto> result = new ArrayList<RemoveDiscountDto>();
        String query = CustomSQLUtil.get("rd.getSelectedContract");
        StringBuilder filterSql = new StringBuilder();
        query = query.replace("[$USERID]", String.valueOf(userId));
        query = query.replace("[$SESSIONID]", String.valueOf(sessionId));
        query = query + " OFFSET " + start + "  ROWS FETCH NEXT " + offset + " ROWS ONLY";

        if (!rebateList.isEmpty()) {
            query = query.replace("?", ItemQueries.getQuery(setRebateInput(rebateList), "rebateSub"));
        } else {
            query = query.replace("?", " ");
        }
        if (indicator.equals("contract")) {
            filterSql = AbstractFilterLogic.getInstance().contractfilterQueryGenerator(filterSet);
        } else if (indicator.equals("summary")) {
            filterSql = AbstractFilterLogic.getInstance().summaryfilterQueryGenerator(filterSet);
        }
        LOGGER.info("filterSql ->" + filterSql);
        if (filterSql != null) {
            query = query.replace("@filter@", filterSql.toString().replace("where", " and "));
        } else {
            query = query.replace("@filter@", " ");
        }
        LOGGER.info("fetch query ->" + query);
        List list = CompanyMasterLocalServiceUtil.executeQuery(query);
        result = setContractValues(list);
        return result;
    }

    public int getComponentCount(Object parent, String value) {
        List<CFPComponentDetailsDTO> retList = new ArrayList<CFPComponentDetailsDTO>();
        int count = 0;
        String component = StringUtils.EMPTY;
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put("?SID?", String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
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
        List<CFPComponentDetailsDTO> retList = new ArrayList<CFPComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put("?SID?", String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
            inputMap.put("?start?", String.valueOf(start));
            inputMap.put("?offset?", String.valueOf(offset));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "rd.cfpFromCD"));
        for (Object[] temp : resList) {
            CFPComponentDetailsDTO tempDto = new CFPComponentDetailsDTO();
            tempDto.setCompanyNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setCompanyName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[3]))) ? null : DBDate.format((Date) temp[3]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[4]))) ? null : DBDate.format((Date) temp[4]));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[2])));
            retList.add(tempDto);
        }

        return retList;
    }

    public List<PSComponentDetailsDTO> getFromIfpCD(Object parent, int start, int offset) {
        List<PSComponentDetailsDTO> retList = new ArrayList<PSComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put("?SID?", String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
            inputMap.put("?start?", String.valueOf(start));
            inputMap.put("?offset?", String.valueOf(offset));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "rd.ifpFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();

            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[3])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[2])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[4])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[5]))) ? null : DBDate.format((Date) temp[5]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[6]))) ? null : DBDate.format((Date) temp[6]));
            retList.add(tempDto);
        }
        return retList;
    }

    public List<PSComponentDetailsDTO> getFromPsCD(Object parent, int start, int offset) {
        List<PSComponentDetailsDTO> retList = new ArrayList<PSComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put("?SID?", String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
            inputMap.put("?start?", String.valueOf(start));
            inputMap.put("?offset?", String.valueOf(offset));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "rd.psFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            int j = -1;
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[2])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[3])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[4])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[5]))) ? null : DBDate.format((Date) temp[5]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[6]))) ? null : DBDate.format((Date) temp[6]));

            retList.add(tempDto);
        }
        return retList;
    }

    public List<PSComponentDetailsDTO> getFromRsCD(Object parent, int start, int offset) {
        List<PSComponentDetailsDTO> retList = new ArrayList<PSComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        if (parent instanceof ContractsDetailsDto) {
            inputMap.put("?SID?", String.valueOf(((ContractsDetailsDto) parent).getInternalId()));
            inputMap.put("?start?", String.valueOf(start));
            inputMap.put("?offset?", String.valueOf(offset));
        }
        List<Object[]> resList = (List<Object[]>) DAO.executeSelect(CommonUtil.getQuery(inputMap, "rd.rsFromCD"));
        for (Object[] temp : resList) {
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[2])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[3])));
            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[4])));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[5]))) ? null : DBDate.format((Date) temp[5]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[6]))) ? null : DBDate.format((Date) temp[6]));
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
            java.util.logging.Logger.getLogger(LookUpLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date();
    }

//    public List<PSComponentDetailsDTO> getDiscountItemsForRS(String UserId, String SessionId) {
//        String query = "Select distinct RS_IM.ITEM_NO,RS_IM.ITEM_NAME,IM.THERAPEUTIC_CLASS,BM.BRAND_NAME,ht.DESCRIPTION STATUS,RS_IM.START_DATE,RS_IM.END_DATE from dbo.IMTD_ITEM_PRICE_REBATE_DETAILS RS_IM \n"
//                + "JOIN ITEM_MASTER IM \n"
//                + "ON IM.ITEM_MASTER_SID=RS_IM.ITEM_MASTER_SID AND RS_IM.ITEM_MASTER_SID=1404\n"
//                + "JOIN BRAND_MASTER BM ON \n"
//                + "BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID\n"
//                + "join helper_table ht on\n"
//                + "ht.helper_table_sid = IM.ITEM_STATUS\n";
//        List<PSComponentDetailsDTO> resultsList = new ArrayList<PSComponentDetailsDTO>();
//        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
//        for (int i = 0; i < list.size(); i++) {
//            Object[] temp = (Object[]) list.get(i);
//            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
//            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
//            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
//            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[3])));
//            tempDto.setTherapyClass(CommonUtil.getPureValue(helperlist.getHelperDTObyID(Integer.valueOf(temp[2].toString())).getDescription()));
//            tempDto.setStatus(CommonUtil.getPureValue(String.valueOf(temp[4])));
//            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[5]))) ? null : DBDate.format((Date) temp[5]));
//            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[6]))) ? null : DBDate.format((Date) temp[6]));
//            resultsList.add(tempDto);
//
//        }
//
//        return resultsList;
//    }
    public List<PSComponentDetailsDTO> getDiscountItemsForPS_RS(String UserId, String SessionId, List<String> itemsList) {
        LOGGER.info(" Inside getDiscountItemsForPS");
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?USERS_SID?", UserId);
        inputMap.put("?SESSION_ID?", SessionId);
        inputMap.put("?ITEM_MASTER_SIDs?", CommonUtils.getListToString(itemsList));
        String query = CommonUtil.getQuery(inputMap, "ad.selectedItemsForNewPS_RS");
        List<PSComponentDetailsDTO> resultsList = new ArrayList<PSComponentDetailsDTO>();
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (int i = 0; i < list.size(); i++) {
            Object[] temp = (Object[]) list.get(i);
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[3])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(helperlist.getHelperDescription(Integer.valueOf(temp[2].toString()))));
            tempDto.setStatus(CommonUtil.getPureValue(helperlist.getHelperDescription(Integer.valueOf(temp[4].toString()))));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[5]))) ? null : DBDate.format((Date) temp[5]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[6]))) ? null : DBDate.format((Date) temp[6]));
            resultsList.add(tempDto);

        }
        LOGGER.info(" Exiting getDiscountItemsForPS");
        return resultsList;
    }

    public List<PSComponentDetailsDTO> getDiscountItemsForIFP(String UserId, String SessionId, List<String> itemsList) {
        LOGGER.info(" Inside getDiscountItemsForIFP");
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?USERS_SID?", UserId);
        inputMap.put("?SESSION_ID?", SessionId);
        inputMap.put("?ITEM_MASTER_SIDs?", CommonUtils.getListToString(itemsList));
        String query = CommonUtil.getQuery(inputMap, "ad.selectedItemsForNewIFP");
        List<PSComponentDetailsDTO> resultsList = new ArrayList<PSComponentDetailsDTO>();
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (int i = 0; i < list.size(); i++) {
            Object[] temp = (Object[]) list.get(i);
            PSComponentDetailsDTO tempDto = new PSComponentDetailsDTO();
            tempDto.setItemNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setItemName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setBrand(CommonUtil.getPureValue(String.valueOf(temp[3])));
            tempDto.setTherapyClass(CommonUtil.getPureValue(String.valueOf(temp[2])));
            tempDto.setStatus(CommonUtil.getPureValue(helperlist.getHelperDescription(Integer.valueOf(temp[4].toString()))));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[5]))) ? null : DBDate.format((Date) temp[5]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[6]))) ? null : DBDate.format((Date) temp[6]));
            resultsList.add(tempDto);

        }
        LOGGER.info(" Exiting getDiscountItemsForIFP");
        return resultsList;
    }

    public List<CFPComponentDetailsDTO> getDiscountItemsForCFP(String UserId, String SessionId, List<String> companyList) {
        LOGGER.info(" Inside getDiscountItemsForCFP");
        List<CFPComponentDetailsDTO> resultsList = new ArrayList<CFPComponentDetailsDTO>();
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("?USERS_SID?", UserId);
        inputMap.put("?SESSION_ID?", SessionId);
        inputMap.put("?COMPANY_MASTER_SIDs?", CommonUtils.getListToString(companyList));
        String query = CommonUtil.getQuery(inputMap, "ad.selectedCompaniesForNewCFP");
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        for (int i = 0; i < list.size(); i++) {
            Object[] temp = (Object[]) list.get(i);
            CFPComponentDetailsDTO tempDto = new CFPComponentDetailsDTO();
            tempDto.setCompanyNo(CommonUtil.getPureValue(String.valueOf(temp[0])));
            tempDto.setCompanyName(CommonUtil.getPureValue(String.valueOf(temp[1])));
            tempDto.setStatus(helperlist.getHelperDescription(CommonUtil.getIntValue(String.valueOf(temp[2]))));
            tempDto.setStartDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[3]))) ? null : DBDate.format((Date) temp[3]));
            tempDto.setEndDate(StringUtils.EMPTY.equals(CommonUtil.getPureValue(String.valueOf(temp[4]))) ? null : DBDate.format((Date) temp[4]));
            resultsList.add(tempDto);

        }
        LOGGER.info(" Exiting getDiscountItemsForCFP");
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
        LOGGER.info("Inside updateDataOperation");
        Map<String, String> inputMap = new HashMap<String, String>();
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
            tableName = "IMTD_CFP_DETAILS";
            queryName = "ad.updateForSelectedCompanySearchTable";
        } else if (Constants.IndicatorConstants.IFP.getConstant().equals(componentType)) {
            tableName = "IMTD_IFP_DETAILS";
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
            tableName = "IMTD_ITEM_PRICE_REBATE_DETAILS";
            queryName = "ad.updateForSelectedCompanySearchTable";
        } else if (Constants.IndicatorConstants.RS_VALUE.getConstant().equals(componentType)) {
            if (isAddOperation) {
                oldOperation = Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant();
                operation = Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant();
            } else {
                oldOperation = Constants.IndicatorConstants.SELECTED_RS_IFP_FOR_ADD_DISCOUNT.getConstant();
                operation = Constants.IndicatorConstants.RS_IFP_FOR_ADD_DISCOUNT.getConstant();
            }
            tableName = "IMTD_ITEM_PRICE_REBATE_DETAILS";
            queryName = "ad.updateForSelectedCompanySearchTable";
        }
        inputMap.put("?OPERATION?", operation);
        inputMap.put("?OLD_OPERATION?", oldOperation);
        inputMap.put("?USERS_SID?", sessionDTO.getUserId());
        inputMap.put("?SESSION_ID?", sessionDTO.getSearchSessionId());
        inputMap.put("?TABLE_NAME?", tableName);

        try {
            String query = CommonUtil.getQuery(inputMap, queryName);
            CompanyMasterLocalServiceUtil.executeUpdateQuery(query);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.info("Exiting updateDataOperation");
    }

    /**
     * To get the Component Details - Selected Items table data
     *
     * @param category
     * @param session
     * @return
     */
    public List getSelectedTableData(String category, SessionDTO session) {
        LOGGER.info("Inside getSelectedTableData");
        Map<String, String> inputMap = new HashMap<String, String>();
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
        inputMap.put("?OPERATION?", operation);
        inputMap.put("?USERS_SID?", session.getUserId());
        inputMap.put("?SESSION_ID?", session.getSearchSessionId());
        String query = StringUtils.EMPTY;
        if (Constants.IndicatorConstants.CFP.getConstant().equals(category)) {
            query = CommonUtil.getQuery(inputMap, "ad.selectedCompaniesFromSearchTable");
        } else if (Constants.IndicatorConstants.IFP.getConstant().equals(category)) {
            query = CommonUtil.getQuery(inputMap, "ad.selectedItemsFromSearchTable");
        } else {
            query = CommonUtil.getQuery(inputMap, "ad.tempItemSearch");
        }
        List resultList = CompanyMasterLocalServiceUtil.executeQuery(query);
        LOGGER.info("Exiting getSelectedTableData");
        return getAttachedNames(resultList, category);
    }

    /**
     * To get the list of attached Companies or Items from the query result
     *
     * @param list Attached items or companies list
     * @return
     */
    public List<String> getAttachedNames(List list, String category) {
        List<String> attachedList = new ArrayList<String>();
        int no = 0;
        if (Constants.IndicatorConstants.PS_VALUE.getConstant().equals(category) || Constants.IndicatorConstants.RS_VALUE.getConstant().equals(category)) {
            no = 2;
        }
        int count = list.size();
        for (int i = 0; i < count; i++) {
            Object objects[] = (Object[]) list.get(i);
            attachedList.add(Converters.convertNullToEmpty(objects[no]));
        }
        return attachedList;
    }
}
