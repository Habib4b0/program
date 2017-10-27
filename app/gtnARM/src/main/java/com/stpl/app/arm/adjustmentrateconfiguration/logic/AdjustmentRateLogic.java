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
import com.stpl.app.arm.dao.CommonDao;
import com.stpl.app.arm.dao.impl.CommonImpl;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.CommonUtils;
import static com.stpl.app.utils.CommonUtils.userMap;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.text.DateFormat;
import java.text.ParseException;
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
import org.jboss.logging.Logger;

/**
 *
 * @author
 */
public class AdjustmentRateLogic {

    private static AdjustmentRateLogic instance;

    private static final CommonDao DAO = CommonImpl.getInstance();
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    private static final Logger LOGGER = Logger.getLogger(AdjustmentRateLogic.class);

    public static AdjustmentRateLogic getInstance() {
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
            LOGGER.debug("--Inside deleteRateConfig--" + query);
            HelperTableLocalServiceUtil.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error("Error in deleteRateConfig :"+e);
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
        LOGGER.debug("Inside selectRateConfig -- " + query);
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
                    selection.setRateConfigMasterSid(obj[0] == null ? 0 : (int) obj[0]);
                }
                int rateConfigDetailsSid = obj[1] == null ? 0 : (int) obj[1];
                int month = obj[NumericConstants.TWO] == null ? 0 : (int) obj[NumericConstants.TWO];
                int dateType = obj[NumericConstants.THREE] == null ? 0 : (int) obj[NumericConstants.THREE];
                String value = (String.valueOf(obj[NumericConstants.FOUR]).matches("^[-+]?\\d+(\\.\\d+)?$") ? getHelpDescriptionValue(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR]))) : String.valueOf(obj[NumericConstants.FOUR]));
                String price = obj[NumericConstants.FOUR] == null || StringUtils.isBlank(String.valueOf(obj[NumericConstants.FOUR])) || "0".equals(obj[NumericConstants.FOUR]) ? GlobalConstants.getSelectOne()
                        : value;
                int rateBasis = obj[NumericConstants.SEVEN] == null ? 0 : (int) obj[NumericConstants.SEVEN];
                int rateFrequency = obj[NumericConstants.EIGHT] == null ? 0 : (int) obj[NumericConstants.EIGHT];
                int ratePeriod = obj[NumericConstants.NINE] == null ? 0 : (int) obj[NumericConstants.NINE];
                int invenDetails = obj[NumericConstants.TEN] == null ? 0 : (int) obj[NumericConstants.TEN];
                int viewMasterSid = obj[NumericConstants.ELEVEN] == null ? 0 : (int) obj[NumericConstants.ELEVEN];
                String exclusionDetails = obj[NumericConstants.TWELVE] == null ? StringUtils.EMPTY : (String) obj[NumericConstants.TWELVE];
                String inventoryCalculation = obj[NumericConstants.TWELVE] == null ? StringUtils.EMPTY : (String) obj[NumericConstants.TWELVE];
                if (ARMConstants.getPipelineAccrual().equals(selection.getAdjustmentType()) || ARMConstants.getTransaction7().equals(selection.getAdjustmentType())) {
                    rate = new AdjustmentRateDTO(rateConfigDetailsSid, ARMUtils.getMONTHS()[month - 1], dateType, price, exclusionDetails, rateBasis, rateFrequency, ratePeriod, viewMasterSid);
                } else if (ARMConstants.getPipelineInventoryTrueUp().equals(selection.getAdjustmentType())) {
                    int inventoryCustomer = obj[NumericConstants.FIVE] == null ? 0 : (int) obj[NumericConstants.FIVE];
                    int reserveDate = obj[NumericConstants.SIX] == null ? 0 : (int) obj[NumericConstants.SIX];

                    rate = new AdjustmentRateDTO(rateConfigDetailsSid, ARMUtils.getMONTHS()[month - 1], inventoryCustomer, inventoryCalculation,
                            price, reserveDate, rateBasis, rateFrequency, ratePeriod, invenDetails, viewMasterSid);
                } else {
                    String baseLinePrice = obj[NumericConstants.FOURTEEN] == null || StringUtils.isBlank(String.valueOf(obj[NumericConstants.THIRTEEN])) || "0".equals(obj[NumericConstants.FOURTEEN]) ? GlobalConstants.getSelectOne() : String.valueOf(obj[NumericConstants.FOURTEEN]);
                    String adjustedPrice = obj[NumericConstants.THIRTEEN] == null || StringUtils.isBlank(String.valueOf(obj[NumericConstants.FOURTEEN])) || "0".equals(obj[NumericConstants.THIRTEEN]) ? GlobalConstants.getSelectOne() : String.valueOf(obj[NumericConstants.THIRTEEN]);
                    rate = new AdjustmentRateDTO(rateConfigDetailsSid, ARMUtils.getMONTHS()[month - 1], 0, inventoryCalculation, price, 0, rateBasis, rateFrequency, ratePeriod, invenDetails, viewMasterSid, baseLinePrice, adjustedPrice, exclusionDetails, dateType);
                }

                months.add(rate);
            }
        } catch (Exception e) {
            LOGGER.error("Error in customizeRateConfig :"+e);
        }

        return months;
    }

    public List<AdjustmentRateDTO> customizeExcel(List<AdjustmentRateDTO> rawList, List<String> priceList, AdjustmentRateSelection selection) {
        List<AdjustmentRateDTO> list = new ArrayList<>();
        try {
            for (AdjustmentRateDTO dto : rawList) {

                String dateType = dto.getDateType() == 0 ? StringUtils.EMPTY : getHelpDescriptionValue(dto.getDateType());
                String price = checkPriceList(dto, priceList) ? StringUtils.EMPTY : dto.getPrice();
                String rateBasis = dto.getRateBasis() == 0 ? StringUtils.EMPTY : getHelpDescriptionValue(dto.getRateBasis());
                String rateFrequency = dto.getRateFrequency() == 0 ? StringUtils.EMPTY : getHelpDescriptionValue(dto.getRateFrequency());
                String ratePeriod = dto.getRatePeriod() == 0 ? StringUtils.EMPTY : getHelpDescriptionValue(dto.getRatePeriod());
                String exclusionDetails = StringUtils.EMPTY.equals(dto.getExclusionDetails()) ? StringUtils.EMPTY : String.valueOf(dto.getExclusionDetails());
                String inventoryCustomer = dto.getInventoryCustomer() == 0 ? StringUtils.EMPTY : getHelpDescriptionValue(dto.getInventoryCustomer());
                String reserveDate = dto.getReserveDate() == 0 ? StringUtils.EMPTY : getHelpDescriptionValue(dto.getReserveDate());
                String baseLinePrice = checkPriceList(dto, priceList) && !ARMConstants.getTransaction6().equals(selection.getAdjustmentType()) ? StringUtils.EMPTY : dto.getBaselinePrice();
                String adjustedPrice = checkPriceList(dto, priceList) && !ARMConstants.getTransaction6().equals(selection.getAdjustmentType()) ? StringUtils.EMPTY : dto.getAdjustedPrice();
                String invenDetails = dto.getInventoryDetails() == 0 ? StringUtils.EMPTY : getHelpDescriptionValue(dto.getInventoryDetails());
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
            LOGGER.error("Error in customizeExcel :"+e);
        }

        return list;
    }

    public String getHelpDescriptionValue(int helperId) {
        return HelperListUtil.getInstance().getIdDescMap().get(helperId);
    }

    public List<ExclusionLookupDTO> getIntialLoadValue(int rateDetailsSid) {
        List<ExclusionLookupDTO> finalList = new ArrayList<>();
        try {
            String query = "select  Distinct RCD.FIELD_NAME,AED.FILED_VALUES from ARM_EXCLUSION_DETAIL AED\n"
                    + "  Join ARM_ADJ_RATE_CONFIG_DETAIL RCD ON AED.ARM_ADJ_RATE_CONFIG_DETAIL_SID=RCD.ARM_ADJ_RATE_CONFIG_DETAIL_SID\n"
                    + "  where AED.ARM_ADJ_RATE_CONFIG_DETAIL_SID=" + rateDetailsSid + " ";
            LOGGER.debug("Inside getIntialLoadValue query--" + query);
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
            LOGGER.error("Error in getIntialLoadValue:"+e);
            return Collections.emptyList();
        }
    }

    public boolean saveOrUpdateValues(String query) {
        try {
            DAO.executeUpdate(query);
            return true;
        } catch (Exception e) {
            LOGGER.error("Error in saveOrUpdateValues:"+e);
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
                    sbQuery.append("(" + viewSid + ",'" + idValue + "'),");
                }
                sbQuery.replace(sbQuery.length() - 1, sbQuery.length(), "");
                LOGGER.debug("Inside isAdd_OR_UpdateView query--" + sbQuery.toString());
                DAO.executeUpdate(sbQuery.toString());
                return true;
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("Error in isAddORUpdateView:"+e);
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
            LOGGER.debug("Inside isSaveView query--" + query);
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (!rawList.isEmpty()) {
                return String.valueOf(rawList.get(0));
            }
            return StringUtils.EMPTY;
        } catch (Exception e) {
            LOGGER.error("Error in isSaveView :"+e);
            return StringUtils.EMPTY;
        }

    }

    public List<ViewLookupDTO> getSavedViewList(String viewName, String viewType, boolean viewStatus, int createdBy) {
        List<ViewLookupDTO> dtoList = Collections.emptyList();
        try {
            String viewValue = StringUtils.EMPTY;
            if (StringUtils.isNotBlank(viewName)) {
                viewValue = viewName.replace("*", "%");
            }
            String query = "select Distinct ARM_VIEW_MASTER_SID,VIEW_NAME,VIEW_TYPE,CREATED_BY,CREATED_DATE from ARM_VIEW_MASTER\n"
                    + "where VIEW_TYPE IN(Select DESCRIPTION from HELPER_TABLE where HELPER_TABLE_SID='@View_Type')  AND VIEW_NAME like'@VIEW_NAME'";
            query = query.replace("@VIEW_NAME", viewValue);
            query = query.replace("@View_Type", viewType);
            if (viewStatus) {
                query += "AND CREATED_BY=" + createdBy;
            }
            LOGGER.debug("Inside getSavedViewList query--" + query);
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
            LOGGER.error("Error in getSavedViewList :"+e);
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
            LOGGER.debug("Inside getSavedViewList query--" + query);
            List<Object[]> rawList = QueryUtils.executeSelect(query);
            if (!rawList.isEmpty()) {
                finalList = new ArrayList();
                for (int i = 0; i < rawList.size(); i++) {
                    Object[] obj = rawList.get(i);
                    ExclusionLookupDTO dto = new ExclusionLookupDTO();
                    dto.setExcludedField(String.valueOf(obj[0]));
                    dto.setValues(String.valueOf(obj[1]));
                    finalList.add(dto);
                }
            }
            return finalList;
        } catch (Exception e) {
            LOGGER.error("Error in getCompanySid :"+e);
            return finalList;
        }
    }

    public List searchLogicForExclusionLookUp(LookUpDTO viewLookupDTO, boolean isCount, final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet) {
        List<LookUpDTO> resultList = new ArrayList<>();
        StringBuilder sqlQuery;
        String viewName = viewLookupDTO.getViewName();
        try {
            if (isCount) {
                sqlQuery = new StringBuilder(SQlUtil.getQuery("getCountExclusionView"));
            } else {
                sqlQuery = new StringBuilder(SQlUtil.getQuery("getExclusionData"));
            }
            if (viewName.contains(ARMUtils.CHAR_ASTERISK)) {
                viewName = viewName.replace(ARMUtils.CHAR_ASTERISK, ARMUtils.CHAR_PERCENT);
            }

            String sqlQuerys = sqlQuery.toString().replace("@VIEW_NAME", viewName);
            sqlQuery = new StringBuilder(sqlQuerys);
            if (StringUtils.EMPTY.equalsIgnoreCase(viewLookupDTO.getInventCalculationValue()) || ("null".equalsIgnoreCase(String.valueOf(viewLookupDTO.getInventCalculationValue())))) {
                sqlQuery.append(" AND AVD.FIELD_VALUES is NOT Null ");
            } else {
                sqlQuery.append("CUSTOMER".equalsIgnoreCase(viewLookupDTO.getInventCalculationValue()) ? " AND AVD.COMPANY_MASTER_SID is NOT Null AND AVD.CHECK_RECORD is NOT NULL AND AVD.\"INDICATOR\" is NOT NULL" : " AND AVD.COMPANY_GROUP_SID is NOT Null AND AVD.CHECK_RECORD is NOT NULL AND AVD.\"INDICATOR\" is NOT NULL ");
            }
            CommonUtils.getUserName();
            StringBuilder filterQuery = new StringBuilder();
            HashMap<String, String> detailsColumn = new HashMap<>();
            detailsColumn.put("viewName", "AVM.VIEW_NAME");
            detailsColumn.put("createdBy", "AVM.CREATED_BY");
            detailsColumn.put("createdDate", "AVM.CREATED_DATE");

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
                        filterQuery.append("'");

                    } else if (filter instanceof Between) {
                        Between betweenFilter = (Between) filter;
                        StringBuilder dateStartstr = new StringBuilder("AND ( * >='?')");
                        StringBuilder dateEndstr = new StringBuilder("AND ( * <='?')");
                        if (!detailsColumn.get(betweenFilter.getPropertyId().toString()).isEmpty()) {
                            Date startValue = (Date) betweenFilter.getStartValue();
                            Date endValue = (Date) betweenFilter.getEndValue();
                            StringBuilder initialStart = new StringBuilder("where ( ( * >= '?' )");
                            StringBuilder initialEnd = new StringBuilder("where ( ( * <= '?' )");
                            if (!betweenFilter.getStartValue().toString().isEmpty()) {
                                StringBuilder tempStart;
                                if (sqlQuery.length() == 0) {
                                    tempStart = new StringBuilder(initialStart);
                                } else {
                                    tempStart = new StringBuilder(dateStartstr);
                                }
                                tempStart.replace(tempStart.indexOf("*"), tempStart.indexOf("*") + 1, detailsColumn.get(betweenFilter.getPropertyId().toString()));
                                tempStart.replace(tempStart.indexOf("?"), tempStart.indexOf("?") + 1, ARMUtils.getInstance().getDbDate().format(startValue));
                                sqlQuery.append(tempStart);
                            }
                            if (!betweenFilter.getEndValue().toString().isEmpty()) {
                                StringBuilder tempEnd;
                                if (sqlQuery.length() == 0) {
                                    tempEnd = new StringBuilder(initialEnd);
                                } else {
                                    tempEnd = new StringBuilder(dateEndstr);
                                }

                                tempEnd.replace(tempEnd.indexOf("*"), tempEnd.indexOf("*") + 1, detailsColumn.get(betweenFilter.getPropertyId().toString()));
                                tempEnd.replace(tempEnd.indexOf("?"), tempEnd.indexOf("?") + 1, ARMUtils.getInstance().getDbDate().format(endValue));
                                sqlQuery.append(tempEnd);
                            }
                        }
                    }
                }
            }
            StringBuilder finalQuery;
            String order = StringUtils.EMPTY;
            if (!isCount) {
                boolean sortOrder = false;
                String columnName = null;
                String orderByColumn = null;
                if (sortByColumns != null) {
                    for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                        final SortByColumn sortByColumn = iterator.next();

                        columnName = sortByColumn.getName();
                        orderByColumn = detailsColumn.get(columnName);

                        if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                            sortOrder = false;
                        } else {
                            sortOrder = true;
                        }
                    }
                }
                if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                    order = order + " ORDER BY AVM.VIEW_NAME ";
                } else {
                    order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
                }
                order = order + " " + "OFFSET ";
                order = order + startIndex;
                order = order + " ROWS FETCH NEXT " + endIndex;
                order = order + " ROWS ONLY;";
            }
            if (isCount) {
                finalQuery = new StringBuilder();
                finalQuery.append(sqlQuery).append(filterQuery);
            } else {
                finalQuery = new StringBuilder();
                finalQuery.append(sqlQuery).append(filterQuery).append(order);
            }
            if (isCount) {
                return HelperTableLocalServiceUtil.executeSelectQuery(finalQuery.toString());
            }
            LOGGER.debug("Inside getSavedViewList finalQuery--" + finalQuery);
            List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(finalQuery.toString());
            for (Object[] obj : list) {
                LookUpDTO exRateDTO = new LookUpDTO();
                exRateDTO.setViewMasterSid(Integer.valueOf(String.valueOf(obj[0])));
                exRateDTO.setViewName(String.valueOf(obj[1]));
                exRateDTO.setCreatedBy(StringUtils.isNotBlank(String.valueOf(obj[NumericConstants.TWO])) ? userMap.get(Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]))) : StringUtils.EMPTY);
                exRateDTO.setCreatedDate(df.parse(df.format((Date) obj[NumericConstants.THREE])));
                resultList.add(exRateDTO);
            }
        } catch (NumberFormatException | ParseException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            LOGGER.error("Error in searchLogicForExclusionLookUp :"+e);
        }
        return resultList;
    }

    public boolean checkPriceList(AdjustmentRateDTO dto, List<String> priceList) {
        return StringUtils.isBlank(dto.getPrice()) || "0".equals(dto.getPrice()) || GlobalConstants.getSelectOne().equals(dto.getPrice()) || !priceList.contains(dto.getPrice());
    }
}
