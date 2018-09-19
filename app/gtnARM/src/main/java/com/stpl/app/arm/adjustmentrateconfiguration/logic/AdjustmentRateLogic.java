/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.logic;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.AdjustmentRateDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.AdjustmentRateSelection;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.ExclusionLookupDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.LookUpDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.SaveViewLookUpDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.dto.ViewLookupDTO;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.dao.CommonDao;
import com.stpl.app.arm.dao.impl.CommonImpl;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.SysDataSourceConnection;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author
 */
public class AdjustmentRateLogic {

    private static AdjustmentRateLogic instance;

    private static final CommonDao DAO = CommonImpl.getInstance();
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    private static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentRateLogic.class);

    public static synchronized AdjustmentRateLogic getInstance() {
        if (instance == null) {
            instance = new AdjustmentRateLogic();
        }
        return instance;
    }

    public AdjustmentRateLogic() {
        LOGGER.debug("Inside Empty Constructor of AdjustmentRateLogic Class");
    }

    public boolean saveRateConfig(AdjustmentRateSelection selection, List<AdjustmentRateDTO> months) {
        String query = QueryUtils.buildRateConfigsaveQuery(selection, months);
        DAO.executeUpdate(query);
        return true;
    }

    public boolean deleteRateConfig(AdjustmentRateSelection selection) {
        List input = new ArrayList();
        try {
            input.add(String.valueOf(selection.getGlcompanyMasterSid()));
            input.add(String.valueOf(selection.getBucompanyMasterSid()));
            input.add(String.valueOf(selection.getAdjustmentId()));

            String query = QueryUtils.getQuery(input, "DELETE_MASTER_DETAILS");
            LOGGER.debug("--Inside deleteRateConfig--{}", query);
            HelperTableLocalServiceUtil.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error("Error in deleteRateConfig :", e);
        }
        return true;
    }

    public boolean updateRateConfig(List<AdjustmentRateDTO> months) {
        String query = QueryUtils.buildRateConfigupdateQuery(months);
        DAO.executeUpdate(query);
        return true;
    }

    public List<AdjustmentRateDTO> selectRateConfig(AdjustmentRateSelection selection) {
        String query = QueryUtils.buildRateConfigselectQuery(selection);
        LOGGER.debug("Inside selectRateConfig -- {}", query);
        List<Object[]> rawList = QueryUtils.executeSelect(query);
        if (rawList == null || rawList.isEmpty() || rawList.size() == 1) {
            return Collections.emptyList();
        }
        return customizeRateConfig(selection, rawList);
    }

    private List<AdjustmentRateDTO> customizeRateConfig(AdjustmentRateSelection selection, List<Object[]> rawList) {
        List<AdjustmentRateDTO> months = new ArrayList<>();
        try {
            AdjustmentRateDTO rate;
            for (Object[] obj : rawList) {
                if (selection.getRateConfigMasterSid() == 0) {
                    selection.setRateConfigMasterSid(CommonLogic.getIntegerValue(0, obj));
                }
                int rateConfigDetailsSid = CommonLogic.getIntegerValue(0, obj);
                int month = CommonLogic.getIntegerValue(NumericConstants.TWO, obj);
                int dateType = CommonLogic.getIntegerValue(NumericConstants.THREE, obj);
                String value = (String.valueOf(obj[NumericConstants.FOUR]).matches("^[-+]?\\d+(\\.\\d+)?$") ? getHelpDescriptionValue(ARMUtils.getIntegerValue(obj[NumericConstants.FOUR].toString())) : String.valueOf(obj[NumericConstants.FOUR]));
                String price = obj[NumericConstants.FOUR] == null || StringUtils.isBlank(String.valueOf(obj[NumericConstants.FOUR])) || "0".equals(obj[NumericConstants.FOUR]) ? GlobalConstants.getSelectOne()
                        : value;
                int rateBasis = CommonLogic.getIntegerValue(NumericConstants.SEVEN, obj);
                int rateFrequency = CommonLogic.getIntegerValue(NumericConstants.EIGHT, obj);
                int ratePeriod = CommonLogic.getIntegerValue(NumericConstants.NINE, obj);
                int invenDetails = CommonLogic.getIntegerValue(NumericConstants.TEN, obj);
                int viewMasterSid = CommonLogic.getIntegerValue(NumericConstants.ELEVEN, obj);
                List<Integer> parameterSet = new ArrayList<>();
                int inventoryCustomer = CommonLogic.getIntegerValue(NumericConstants.FIVE, obj);
                int reserveDate = CommonLogic.getIntegerValue(NumericConstants.SIX, obj);
                parameterSet.add(0, rateConfigDetailsSid);
                parameterSet.add(1, dateType);
                parameterSet.add(2, rateBasis);
                parameterSet.add(3, rateFrequency);
                parameterSet.add(4, viewMasterSid);
                parameterSet.add(5, inventoryCustomer);
                parameterSet.add(6, reserveDate);
                parameterSet.add(7, ratePeriod);
                parameterSet.add(8, invenDetails);

                rate = getRate(selection, parameterSet, month, price, ratePeriod, obj);

                months.add(rate);
            }
        } catch (Exception e) {
            LOGGER.error("Error in customizeRateConfig :", e);
        }

        return months;
    }

    private AdjustmentRateDTO getRate(AdjustmentRateSelection selection, List<Integer> parameterSet, int month, String price, int ratePeriod, Object[] obj) {
        AdjustmentRateDTO rate;
        String exclusionDetails = CommonLogic.getStringValue(NumericConstants.TWELVE, obj);
        String inventoryCalculation = CommonLogic.getStringValue(NumericConstants.TWELVE, obj);
        if (ARMConstants.getPipelineAccrual().equals(selection.getAdjustmentType()) || ARMConstants.getTransaction7().equals(selection.getAdjustmentType())) {
            rate = new AdjustmentRateDTO(parameterSet, ARMUtils.getMONTHS()[month - 1], price, exclusionDetails, ratePeriod);
        } else if (ARMConstants.getPipelineInventoryTrueUp().equals(selection.getAdjustmentType())) {
            rate = new AdjustmentRateDTO(parameterSet, ARMUtils.getMONTHS()[month - 1], inventoryCalculation,
                    price);
        } else {
            String baseLinePrice = obj[NumericConstants.FOURTEEN] == null || StringUtils.isBlank(String.valueOf(obj[NumericConstants.THIRTEEN])) || "0".equals(obj[NumericConstants.FOURTEEN]) ? GlobalConstants.getSelectOne() : String.valueOf(obj[NumericConstants.FOURTEEN]);
            String adjustedPrice = obj[NumericConstants.THIRTEEN] == null || StringUtils.isBlank(String.valueOf(obj[NumericConstants.FOURTEEN])) || "0".equals(obj[NumericConstants.THIRTEEN]) ? GlobalConstants.getSelectOne() : String.valueOf(obj[NumericConstants.THIRTEEN]);
            rate = new AdjustmentRateDTO(parameterSet, ARMUtils.getMONTHS()[month - 1], inventoryCalculation, price, baseLinePrice, adjustedPrice, exclusionDetails);
        }
        return rate;
    }

    public List<AdjustmentRateDTO> customizeExcel(List<AdjustmentRateDTO> rawList, List<String> priceList, AdjustmentRateSelection selection) {
        List<AdjustmentRateDTO> list = new ArrayList<>();
        try {
            for (AdjustmentRateDTO dto : rawList) {

                String dateType = getStringValue(dto.getDateType());
                String price = getStringValue(dto, priceList, selection, dto.getPrice());
                String rateBasis = getStringValue(dto.getRateBasis());
                String rateFrequency = getStringValue(dto.getRateFrequency());
                String ratePeriod = dto.getRatePeriod() == 0 ? StringUtils.EMPTY : getHelpDescriptionValue(dto.getRatePeriod());
                String exclusionDetails = StringUtils.EMPTY.equals(dto.getExclusionDetails()) ? StringUtils.EMPTY : String.valueOf(dto.getExclusionDetails());
                String inventoryCustomer = getStringValue(dto.getInventoryCustomer());
                String reserveDate = getStringValue(dto.getReserveDate());
                String baseLinePrice = getStringValue(dto, priceList, selection, dto.getBaselinePrice());
                String adjustedPrice = getStringValue(dto, priceList, selection, dto.getAdjustedPrice());
                String invenDetails = getStringValue(dto.getInventoryDetails());
                String invenCalculation = StringUtils.isBlank(dto.getInventoryCalculation()) || "0".equals(dto.getInventoryCalculation()) ? StringUtils.EMPTY : dto.getInventoryCalculation();
                dto.setexcelmonth(dto.getMonth());
                dto.setexceldateType(dateType);
                dto.setexcelprice(price);
                dto.setexcelrateBasis(rateBasis);
                dto.setexcelrateFrequency(rateFrequency);
                dto.setexcelratePeriod(ratePeriod);
                dto.setexcelinventoryCustomer(inventoryCustomer);
                dto.setexcelreserveDate(reserveDate);
                dto.setexcelbaseLinePrice(baseLinePrice);
                dto.setexceladjustmentPrice(adjustedPrice);
                dto.setexcelexclusionDetails(exclusionDetails);
                dto.setexcelinventoryDetails(invenDetails);
                dto.setexcelinventoryCalculation(invenCalculation);
                list.add(dto);
            }
        } catch (Exception e) {
            LOGGER.error("Error in customizeExcel :", e);
        }

        return list;
    }

    private String getStringValue(AdjustmentRateDTO dto, List<String> priceList, AdjustmentRateSelection selection, String value) {
        return checkPriceList(dto, priceList) && !ARMConstants.getTransaction6().equals(selection.getAdjustmentType()) ? StringUtils.EMPTY : value;
    }

    private String getStringValue(int value) {
        return value == 0 ? StringUtils.EMPTY : getHelpDescriptionValue(value);
    }

    public String getHelpDescriptionValue(int helperId) {
        return HelperListUtil.getInstance().getIdDescMap().get(helperId);
    }

    public List<ExclusionLookupDTO> getIntialLoadValue(int rateDetailsSid) {
        List<ExclusionLookupDTO> finalList = new ArrayList<>();
        try {
            String query = "select  Distinct RCD.FIELD_NAME,AED.FILED_VALUES from ARM_EXCLUSION_DETAIL AED\n"
                    + "  Join ARM_ADJ_RATE_CONFIG_DETAIL RCD ON AED.ARM_ADJ_RATE_CONFIG_DETAIL_SID=RCD.ARM_ADJ_RATE_CONFIG_DETAIL_SID\n"
                    + "  where AED.ARM_ADJ_RATE_CONFIG_DETAIL_SID=" + rateDetailsSid + ARMUtils.SPACE;
            LOGGER.debug("Inside getIntialLoadValue query--{}", query);
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (rawList == null || rawList.isEmpty()) {
                return Collections.emptyList();
            }
            if (!rawList.isEmpty()) {
                for (int i = 0; i < rawList.size(); i++) {
                    Object[] obj = rawList.get(i);
                    ExclusionLookupDTO dtoValue = new ExclusionLookupDTO();
                    dtoValue.setExcludedField(String.valueOf(obj[0]));
                    dtoValue.setValues(String.valueOf(obj[1]));
                    finalList.add(dtoValue);
                }
            }
            return finalList;
        } catch (Exception e) {
            LOGGER.error("Error in getIntialLoadValue:", e);
            return Collections.emptyList();
        }
    }

    public boolean saveOrUpdateValues(String query) {
        try {
            DAO.executeUpdate(query);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error in saveOrUpdateValues:", e);
            return false;
        }
    }

    public boolean isAddORUpdateView(SaveViewLookUpDTO saveViewDTO) {
        StringBuilder sbQuery = new StringBuilder(StringUtils.EMPTY);
        try {
            String viewSid;
            if (saveViewDTO.isViewStatus()) {
                sbQuery.append("Delete ARM_VIEW_DETAILS where ARM_VIEW_MASTER_SID=" + saveViewDTO.getViewMasterSid() + ";");
                sbQuery.append("Update  ARM_VIEW_MASTER set FIELD_NAME='" + saveViewDTO.getFieldName() + "' where ARM_VIEW_MASTER_SID=" + saveViewDTO.getViewMasterSid() + ";");
                viewSid = saveViewDTO.getViewMasterSid();
            } else {
                viewSid = isSaveView(saveViewDTO);
            }
            if (!StringUtils.EMPTY.equals(viewSid)) {
                sbQuery.append("Insert into ARM_VIEW_DETAILS (ARM_View_Master_Sid,Field_Values) Values ");
                for (String idValue : saveViewDTO.getCompanyIDList()) {
                    sbQuery.append(ARMUtils.OPEN_PARANTHESIS + viewSid + ",'" + idValue + "'),");
                }
                sbQuery.replace(sbQuery.length() - 1, sbQuery.length(), "");
                LOGGER.debug("Inside isAdd_OR_UpdateView query--{}", sbQuery.toString());
                DAO.executeUpdate(sbQuery.toString());
                return true;
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("Error in isAddORUpdateView:", e);
            return false;
        }
    }

    public String isSaveView(SaveViewLookUpDTO saveViewDTO) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String query = "Insert into ARM_VIEW_MASTER Values('@View_Name','@View_Type','@Field_Name','@Created_By',@Created_Date,null,null);";
            query = query.replace("@View_Name", saveViewDTO.getViewName());
            query = query.replace("@View_Type", saveViewDTO.getViewType());
            query = query.replace("@Field_Name", saveViewDTO.getFieldName());
            query = query.replace("@Created_By", saveViewDTO.getCreatedBy());
            query = query.replace("@Created_Date", dateFormat.format(new Date()));
            query += "   SELECT SCOPE_IDENTITY();";
            LOGGER.debug("Inside isSaveView query--{}", query);
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (!rawList.isEmpty()) {
                return String.valueOf(rawList.get(0));
            }
            return StringUtils.EMPTY;
        } catch (Exception e) {
            LOGGER.error("Error in isSaveView :", e);
            return StringUtils.EMPTY;
        }

    }

    public List<ViewLookupDTO> getSavedViewList(String viewName, String viewType, boolean viewStatus, int createdBy) {
        List<ViewLookupDTO> dtoList = Collections.emptyList();
        try {
            String viewValue = StringUtils.EMPTY;
            if (StringUtils.isNotBlank(viewName)) {
                viewValue = viewName.replace(ARMUtils.CHAR_ASTERISK, "%");
            }
            String query = "select Distinct ARM_VIEW_MASTER_SID,VIEW_NAME,VIEW_TYPE,CREATED_BY,CREATED_DATE from ARM_VIEW_MASTER\n"
                    + "where VIEW_TYPE IN(Select DESCRIPTION from HELPER_TABLE where HELPER_TABLE_SID='@View_Type')  AND VIEW_NAME like'@VIEW_NAME'";
            query = query.replace("@VIEW_NAME", viewValue);
            query = query.replace("@View_Type", viewType);
            if (viewStatus) {
                query += "AND CREATED_BY=" + createdBy;
            }
            LOGGER.debug("Inside getSavedViewList query--{}", query);
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (!rawList.isEmpty()) {

                dtoList = new ArrayList();
                for (int i = 0; i < rawList.size(); i++) {
                    Object[] obj = rawList.get(i);
                    ViewLookupDTO dto = new ViewLookupDTO();
                    dto.setViewSid(String.valueOf(obj[0]));
                    dto.setViewName(String.valueOf(obj[1]));
                    dto.setViewType("Private".equalsIgnoreCase(String.valueOf(obj[NumericConstants.TWO])) ? "privateView" : "publicView");
                    dto.setCreatedBy(String.valueOf(obj[NumericConstants.THREE]));
                    dto.setCreatedDate(df.parse(df.format((Date) obj[NumericConstants.FOUR])));
                    dtoList.add(dto);
                }
            }
            return dtoList;
        } catch (Exception e) {
            LOGGER.error("Error in getSavedViewList :", e);
            return dtoList;
        }

    }

    public List<ExclusionLookupDTO> getCompanySid(String viewSid) {
        List<ExclusionLookupDTO> finalList = Collections.emptyList();
        try {
            String query = "Select Distinct AVM.FIELD_NAME,AVD.Field_Values from ARM_VIEW_MASTER AVM\n"
                    + "Join ARM_VIEW_DETAILS AVD ON AVM.ARM_VIEW_MASTER_SID=AVD.ARM_VIEW_MASTER_SID\n"
                    + "where AVM.ARM_VIEW_MASTER_SID='@ARM_VIEW_MASTER_SID' ;";
            query = query.replace("@ARM_VIEW_MASTER_SID", viewSid);
            LOGGER.debug("Inside getSavedViewList query--{}", query);
            List<Object[]> resultsList = QueryUtils.executeSelect(query);
            if (!resultsList.isEmpty()) {
                finalList = new ArrayList();
                for (int i = 0; i < resultsList.size(); i++) {
                    Object[] obj = resultsList.get(i);
                    ExclusionLookupDTO ratesdto = new ExclusionLookupDTO();
                    ratesdto.setExcludedField(String.valueOf(obj[0]));
                    ratesdto.setValues(String.valueOf(obj[1]));
                    finalList.add(ratesdto);
                }
            }
            return finalList;
        } catch (Exception e) {
            LOGGER.error("Error in getCompanySid :", e);
            return finalList;
        }
    }

    public List searchLogicForExclusionLookUp(LookUpDTO viewLookupDTO, boolean isCount, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) {
        List<LookUpDTO> resultList = new ArrayList<>();
        try {
            Connection con = SysDataSourceConnection.getConnection();

            StringBuilder sqlQuery;
            String viewName = viewLookupDTO.getViewName();

            sqlQuery = new StringBuilder(SQlUtil.getQuery("getExclusionData"));
            if (viewName.contains(ARMUtils.CHAR_ASTERISK)) {
                viewName = viewName.replace(ARMUtils.CHAR_ASTERISK, ARMUtils.CHAR_PERCENT);
            }
            String sqlQueryRp = sqlQuery.toString().replace("@CONNECTION", con.getCatalog());
            String sqlQuerys = sqlQueryRp.replace("@VIEW_NAME", viewName);
            sqlQuery = new StringBuilder(sqlQuerys);

            if (StringUtils.EMPTY.equalsIgnoreCase(viewLookupDTO.getInventCalculationValue()) || ("null".equalsIgnoreCase(String.valueOf(viewLookupDTO.getInventCalculationValue())))) {
                sqlQuery.append(" AND AVD.FIELD_VALUES is NOT Null ");
            } else {
                sqlQuery.append("CUSTOMER".equalsIgnoreCase(viewLookupDTO.getInventCalculationValue()) ? " AND AVD.COMPANY_MASTER_SID is NOT Null AND AVD.CHECK_RECORD is NOT NULL " : " AND AVD.COMPANY_GROUP_SID is NOT Null AND AVD.CHECK_RECORD is NOT NULL ");
            }
            CommonUtils.getUserName();
            StringBuilder filterQuery = new StringBuilder();
            HashMap<String, String> detailsColumn = new HashMap<>();
            detailsColumn.put("viewName", "AVM.VIEW_NAME");
            detailsColumn.put("createdBy", "(cr.lastName + ',' + cr.firstName)");
            detailsColumn.put("createdDate", "CONVERT(VARCHAR(10), AVM.CREATED_DATE, 101)");

            if (filterSet != null) {
                for (Container.Filter filter : filterSet) {
                    if (filter instanceof SimpleStringFilter) {
                        SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        filterQuery.append(filterQuery);
                        filterQuery.append(" AND ");
                        filterQuery.append(detailsColumn.get(String.valueOf(stringFilter.getPropertyId())));
                        filterQuery.append(" like '");
                        filterQuery.append(filterString);
                        filterQuery.append(ARMUtils.SINGLE_QUOTES);

                    } else if (filter instanceof Between) {
                        Between betweenFilter = (Between) filter;
                        StringBuilder ratesDateStartstr = new StringBuilder("AND ( * >='?')");
                        StringBuilder ratesDateEndstr = new StringBuilder("AND ( * <='?')");
                        if (!detailsColumn.get(betweenFilter.getPropertyId().toString()).isEmpty()) {
                            Date startVal = (Date) betweenFilter.getStartValue();
                            Date endVal = (Date) betweenFilter.getEndValue();
                            StringBuilder initStart = new StringBuilder("where ( ( * >= '?' )");
                            StringBuilder initEnd = new StringBuilder("where ( ( * <= '?' )");
                            if (!betweenFilter.getStartValue().toString().isEmpty()) {
                                StringBuilder tmpStart;
                                if (sqlQuery.length() == 0) {
                                    tmpStart = new StringBuilder(initStart);
                                } else {
                                    tmpStart = new StringBuilder(ratesDateStartstr);
                                }
                                tmpStart.replace(tmpStart.indexOf(ARMUtils.CHAR_ASTERISK), tmpStart.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(betweenFilter.getPropertyId().toString()));
                                tmpStart.replace(tmpStart.indexOf(ARMUtils.CHAR_QUS), tmpStart.indexOf(ARMUtils.CHAR_QUS) + 1, ARMUtils.getInstance().getDbDate().format(startVal));
                                sqlQuery.append(tmpStart);
                            }
                            if (!betweenFilter.getEndValue().toString().isEmpty()) {
                                StringBuilder tempEnd;
                                if (sqlQuery.length() == 0) {
                                    tempEnd = new StringBuilder(initEnd);
                                } else {
                                    tempEnd = new StringBuilder(ratesDateEndstr);
                                }

                                tempEnd.replace(tempEnd.indexOf(ARMUtils.CHAR_ASTERISK), tempEnd.indexOf(ARMUtils.CHAR_ASTERISK) + 1, detailsColumn.get(betweenFilter.getPropertyId().toString()));
                                tempEnd.replace(tempEnd.indexOf(ARMUtils.CHAR_QUS), tempEnd.indexOf(ARMUtils.CHAR_QUS) + 1, ARMUtils.getInstance().getDbDate().format(endVal));
                                sqlQuery.append(tempEnd);
                            }
                        }
                    }
                }
            }
            StringBuilder finalQuery;
            String orderBy = StringUtils.EMPTY;
            if (!isCount) {
                boolean sortOrdering = false;
                String colName = null;
                String orderByCol = null;
                if (sortByColumns != null) {
                    for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                        final SortByColumn sortByColumn = iterator.next();

                        colName = sortByColumn.getName();
                        orderByCol = detailsColumn.get(colName);

                        if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                            sortOrdering = false;
                        } else {
                            sortOrdering = true;
                        }
                    }
                }
                if (orderByCol == null || StringUtils.EMPTY.equals(orderByCol)) {
                    orderBy = orderBy + " ORDER BY AVM.VIEW_NAME ";
                } else {
                    orderBy = orderBy + " ORDER BY " + orderByCol + ((!sortOrdering) ? " ASC " : " DESC ");
                }
                orderBy = orderBy + ARMUtils.SPACE + "OFFSET ";
                orderBy = orderBy + startIndex;
                orderBy = orderBy + " ROWS FETCH NEXT " + endIndex;
                orderBy = orderBy + " ROWS ONLY;";
            }

            finalQuery = new StringBuilder();
            finalQuery.append(sqlQuery).append(filterQuery).append(orderBy);
            if (isCount) {
                return HelperTableLocalServiceUtil.executeSelectQuery(finalQuery.toString());
            }
            LOGGER.debug("Inside getSavedViewList finalQuery--{}", finalQuery);
            List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery.toString());
            for (Object[] obj : list) {
                LookUpDTO exRateDTO = new LookUpDTO();
                exRateDTO.setViewMasterSid((Integer)(obj[0]));
                exRateDTO.setViewName(String.valueOf(obj[1]));
                exRateDTO.setCreatedBy(StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.TWO])) ? CommonUtils.getUserMap().get((Integer)(obj[NumericConstants.TWO])) : StringUtils.EMPTY);
                exRateDTO.setCreatedDate(CommonUtils.convertStringToDate(String.valueOf(obj[NumericConstants.THREE])));
                resultList.add(exRateDTO);
            }
            return resultList;
        } catch (SQLException | NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException ex) {
            LOGGER.error("Error in searchLogicForExclusionLookUp", ex);
        }
        return Collections.emptyList();
    }

    public boolean checkPriceList(AdjustmentRateDTO dto, List<String> priceList) {
        return StringUtils.isBlank(dto.getPrice()) || "0".equals(dto.getPrice()) || GlobalConstants.getSelectOne().equals(dto.getPrice()) || !priceList.contains(dto.getPrice());
    }
}
