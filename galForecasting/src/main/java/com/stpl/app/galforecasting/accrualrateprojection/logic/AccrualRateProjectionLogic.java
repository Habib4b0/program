/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.accrualrateprojection.logic;

import com.stpl.app.galforecasting.accrualrateprojection.dto.AccrualRateProjectionDTO;
import com.stpl.app.galforecasting.accrualrateprojection.dto.AccrualRateSelectionDTO;
import com.stpl.app.galforecasting.accrualrateprojection.utils.AccrualRateUtils;
import com.stpl.app.galforecasting.logic.DataSourceConnection;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.ui.ComboBox;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.Constant;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.util.Iterator;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author sibi
 */
public final class AccrualRateProjectionLogic {

    private static final Logger LOGGER = Logger.getLogger(AccrualRateProjectionLogic.class);

    private static AccrualRateProjectionLogic accrualRateProjectionLogic;
    public static List<String> selectedCompanyList = new ArrayList<>();

    /**
     * Private Constructor to restrict instantiation outside this class.
     */
    public AccrualRateProjectionLogic() {

    }

    /**
     * Returns the same instance whenever this method is called.(Singleton
     * Implementation)
     *
     * @return
     */
    public static AccrualRateProjectionLogic getInstance() {

        if (accrualRateProjectionLogic == null) {
            accrualRateProjectionLogic = new AccrualRateProjectionLogic();
        }
        return accrualRateProjectionLogic;
    }

    /**
     * Checks the From and To Period while generating the list view to filter
     * periods. If the filter selection is valid, sets the filter details to the
     * AccrualRateSelectionDTO and returns value as false.
     *
     * @param fromValue
     * @param toValue
     * @param accrualRateSelectionDTO
     * @return
     */
    public boolean checkInvalidFromToPeriods(final Object fromValue, final Object toValue, final AccrualRateSelectionDTO accrualRateSelectionDTO) {
        String fromPeriod = (String) fromValue;
        String toPeriod = (String) toValue;
        boolean isNotValid = false;
        if (StringUtils.isNotBlank(fromPeriod) || StringUtils.isNotBlank(toPeriod)) {
            if (StringUtils.isBlank(fromPeriod) && StringUtils.isNotBlank(toPeriod)) {
                isNotValid = true;
            } else {
                String[] from = fromPeriod.split(AccrualRateUtils.DASH);
                int startYear = Integer.valueOf(from[1].substring(0, 4));
                int startMonth = Integer.valueOf(from[0].trim().substring(1, from[0].length()));
                if (StringUtils.isNotBlank(toPeriod)) {
                    String[] to = toPeriod.split(AccrualRateUtils.DASH);
                    int endYear = Integer.valueOf(to[1].substring(0, 4));
                    int endMonth = Integer.valueOf(to[0].trim().substring(1, to[0].length()));
                    if (startYear > endYear) {
                        isNotValid = true;
                    } else if (startYear == endYear) {
                        if (startMonth > endMonth) {
                            isNotValid = true;
                        }
                    }
                    if (!isNotValid) {
                        setFilterPeriods(accrualRateSelectionDTO, startYear, endYear, startMonth, endMonth);
                    }
                }
                if (StringUtils.isBlank(toPeriod)) {
                    setFilterPeriods(accrualRateSelectionDTO, startYear, 0, startMonth, 0);
                }
            }
        }
        return isNotValid;
    }

    /**
     * Methods sets the filter start and end periods to AccrualRateSelectionDTO.
     *
     * @param accrualRateSelectionDTO
     * @param startYear
     * @param endYear
     * @param startMonth
     * @param endMonth
     */
    private void setFilterPeriods(final AccrualRateSelectionDTO accrualRateSelectionDTO, int startYear, int endYear, int startMonth, int endMonth) {
        accrualRateSelectionDTO.setFilterStartYear(startYear);
        accrualRateSelectionDTO.setFilterStartMonth(startMonth);
        accrualRateSelectionDTO.setFilterEndYear(endYear);
        accrualRateSelectionDTO.setFilterEndMonth(endMonth);
    }

    /**
     * Methods returns the number of products available in the projection.
     *
     * @param projectionId
     * @return
     */
    public int getProductsCount(String projectionId) {
        String query = CustomSQLUtil.get("products-count");
        query = query.replace("@PROJECTION_MASTER_SID", projectionId);
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list == null || list.get(0) == null ? 0 : Integer.valueOf(list.get(0).toString());
    }

    /**
     * Retrieves the data for Rates Tab.
     *
     * @param accrualRateSelectionDTO
     * @param start
     * @param offset
     * @param isExcelExport
     * @return
     */
    public List<AccrualRateProjectionDTO> getDataForRates(final AccrualRateSelectionDTO accrualRateSelectionDTO, final int start, final int offset, final boolean isExcelExport) {
        final Timestamp startTimestamp = new Timestamp(accrualRateSelectionDTO.getStartDate().getTime());
        final Timestamp endTimestamp = new Timestamp(accrualRateSelectionDTO.getEndDate().getTime());
        String query = CustomSQLUtil.get("get-data-for-rates");
        query = query.replace("@PROJECTION_MASTER_SID", accrualRateSelectionDTO.getProjectionId());
        query = query.replace("@USER_ID", accrualRateSelectionDTO.getUserId());
        query = query.replace("@SESSION_ID", accrualRateSelectionDTO.getSessionId());
        query = query.replace("@START_TIME_STAMP", startTimestamp.toString());
        query = query.replace("@END_TIME_STAMP", endTimestamp.toString());
        if (isExcelExport) {
            query = query.replace("ORDER  BY ITEM_MASTER_SID OFFSET @START ROWS FETCH NEXT @OFFSET ROWS ONLY", StringUtils.EMPTY);
        }
        query = query.replace("@START", StringUtils.EMPTY + start);
        query = query.replace("@OFFSET", StringUtils.EMPTY + offset);
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return customizeRatesToDTO(list, accrualRateSelectionDTO);
    }

    /**
     * Retrieves the data for details tab.
     *
     * @param accrualRateSelectionDTO
     * @param start
     * @param offset
     * @param isExcelExport
     * @return
     */
    public List<AccrualRateProjectionDTO> getDataForDetails(final AccrualRateSelectionDTO accrualRateSelectionDTO, final int start, final int offset, final boolean isExcelExport) {
        String query = CustomSQLUtil.get("get-data-for-details");
        try
        {
        if (accrualRateSelectionDTO.getBrand() == null || AccrualRateUtils.ALL_BRANDS.equals(accrualRateSelectionDTO.getBrand())) {
            query = query.replace("JOIN   ITEM_MASTER IM ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID AND IM.BRAND_MASTER_SID = @BRAND_MASTER_SID", StringUtils.EMPTY);
            query = query.replace("JOIN   ITEM_MASTER IM ON IM.ITEM_MASTER_SID = STA.ITEM_MASTER_SID AND IM.BRAND_MASTER_SID = @BRAND_MASTER_SID", StringUtils.EMPTY);
        } else {
            query = query.replace("@BRAND_MASTER_SID", accrualRateSelectionDTO.getBrand().toString());
        }
        if (accrualRateSelectionDTO.getCustomer() == null || AccrualRateUtils.ALL_CUSTOMERS.equals(accrualRateSelectionDTO.getCustomer())) {
            query = query.replace("AND CCP.COMPANY_MASTER_SID = @COMPANY_MASTER_SID", StringUtils.EMPTY);
            query = query.replace("AND COMPANY_MASTER_SID = @COMPANY_MASTER_SID", StringUtils.EMPTY);
        } else {
            query = query.replace("@COMPANY_MASTER_SID", accrualRateSelectionDTO.getCustomer().toString());
        }
        if (accrualRateSelectionDTO.getProduct() == null || AccrualRateUtils.ALL_PRODUCTS.equals(accrualRateSelectionDTO.getProduct())) {
            query = query.replace("AND CCP.ITEM_MASTER_SID = @ITEM_MASTER_SID", StringUtils.EMPTY);
            query = query.replace("AND STA.ITEM_MASTER_SID = @ITEM_MASTER_SID", StringUtils.EMPTY);
        } else {
            query = query.replace("@ITEM_MASTER_SID", accrualRateSelectionDTO.getProduct().toString());
        }
        query = query.replace("@PROJECTION_MASTER_SID", accrualRateSelectionDTO.getProjectionId());
        query = query.replace("@USER_ID", accrualRateSelectionDTO.getUserId());
        query = query.replace("@SESSION_ID", accrualRateSelectionDTO.getSessionId());
        String rateBasis;
            if (accrualRateSelectionDTO.getRateBasis() != null) {
                switch (accrualRateSelectionDTO.getRateBasis()) {
                    case AccrualRateUtils.ADJUSTED_DEMAND:
                        rateBasis = "ADJUSTED_DEMAND";
                        break;
                    case AccrualRateUtils.DEMAND:
                        rateBasis = "DEMAND";
                        break;
                    case AccrualRateUtils.EX_FACTORY:
                        rateBasis = "GROSS_TRADE_SALES";
                        break;
                    case AccrualRateUtils.INVENTORY_WITHDRAWALS:
                        rateBasis = "INVENTORY_WITHDRAWALS";
                        break;
                    default:
                        rateBasis = "GROSS_TRADE_SALES";
                        LOGGER.warn("Accrual Rate Details: Rate Basis is Empty." + rateBasis);
                }
            } else {
                rateBasis="GROSS_TRADE_SALES";
            }
        query = query.replace("@SELECTED_RATE_BASIS", rateBasis);
         }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        return customizeDetailsToDTO(accrualRateSelectionDTO, list, start, offset, isExcelExport);
        }

    /**
     * Customizes the result set to AccrualRateProjectionDTO and returns a list
     * of AccrualRateProjectionDTO.
     *
     * @param list
     * @param accrualRateSelectionDTO
     * @return
     */
    public List<AccrualRateProjectionDTO> customizeRatesToDTO(final List list, final AccrualRateSelectionDTO accrualRateSelectionDTO) {
        List<AccrualRateProjectionDTO> resultList = new ArrayList<>();
        AccrualRateProjectionDTO accrualRateProjectionDTO = new AccrualRateProjectionDTO();
        int listSize = list.size();
        String lastProduct = StringUtils.EMPTY;
        int j = 0;
        int variable = accrualRateSelectionDTO.isRateOrAmount() ? 3 : 4;
        List availableVisibleColumns = accrualRateSelectionDTO.getAvailableVisibleColumns();
        for (int i = 0; i < listSize; i++) {
            Object[] object = (Object[]) list.get(i);
            if (StringUtils.isBlank(lastProduct) || lastProduct.equals(object[0])) {
                lastProduct = String.valueOf(object[0]);
                accrualRateProjectionDTO.setProduct(lastProduct);
            } else {
                resultList.add(accrualRateProjectionDTO);
                accrualRateProjectionDTO = new AccrualRateProjectionDTO();
                j = 0;
                lastProduct = String.valueOf(object[0]);
                accrualRateProjectionDTO.setProduct(lastProduct);
            }
            String column = AccrualRateUtils.M + object[1] + AccrualRateUtils.DASH + object[2] + AccrualRateUtils.DOT + j;
            if (availableVisibleColumns.contains(column)) {
                accrualRateProjectionDTO.addStringProperties(column,
                        checkAndFormatValues(object[variable], accrualRateSelectionDTO.isRateOrAmount() ? AccrualRateUtils.RATE_FORMAT : AccrualRateUtils.DOLLAR_FORMAT, accrualRateSelectionDTO.isRateOrAmount()));
                j++;
            }
            if (i == list.size() - 1) {
                resultList.add(accrualRateProjectionDTO);
            }
        }
        return resultList;
    }

    public List<AccrualRateProjectionDTO> customizeSalesToDTO(final List list, final AccrualRateSelectionDTO accrualRateSelectionDTO, final List<String> selectedVariables) {
        List<AccrualRateProjectionDTO> resultList = new ArrayList<>();
        AccrualRateProjectionDTO accrualRateProjectionDTO = new AccrualRateProjectionDTO();
        List availableVisibleColumns = accrualRateSelectionDTO.getAvailableVisibleColumns();
        int listSize = list.size();

        String lastProduct = StringUtils.EMPTY;
        int j = 0;
        for (int i = 0; i < listSize; i++) {
            Object[] object = (Object[]) list.get(i);
            if (StringUtils.isBlank(lastProduct) || lastProduct.equals(object[1])) {
                lastProduct = String.valueOf(object[1]);
                accrualRateProjectionDTO.setGroup(lastProduct);
            } else {
                resultList.add(accrualRateProjectionDTO);
                accrualRateProjectionDTO = new AccrualRateProjectionDTO();
                j = 0;
                lastProduct = String.valueOf(object[1]);
                accrualRateProjectionDTO.setGroup(lastProduct);
            }

            String key = AccrualRateUtils.M + object[2] + AccrualRateUtils.DASH + object[3];

            if (Constant.TRUE.equals(String.valueOf(object[14]))) {

                if (availableVisibleColumns.contains(key + "TotalUnits" + AccrualRateUtils.DOT + (j))) {
                    accrualRateProjectionDTO.addStringProperties(key + "TotalUnits" + AccrualRateUtils.DOT + (j++), String.valueOf((object[4] == null ? 0 : AccrualRateUtils.NO_DECIMAL_FORMAT.format(object[4]))));

                }

                if (availableVisibleColumns.contains(key + "ExcludedUnits" + AccrualRateUtils.DOT + (j))) {
                    accrualRateProjectionDTO.addStringProperties(key + "ExcludedUnits" + AccrualRateUtils.DOT + (j++), String.valueOf((object[5] == null ? 0 : AccrualRateUtils.NO_DECIMAL_FORMAT.format(object[5]))));

                }

                if (availableVisibleColumns.contains(key + "NetUnits" + AccrualRateUtils.DOT + (j))) {
                    accrualRateProjectionDTO.addStringProperties(key + "NetUnits" + AccrualRateUtils.DOT + (j++), String.valueOf((object[6] == null ? 0 : AccrualRateUtils.NO_DECIMAL_FORMAT.format(object[6]))));

                }

                if (availableVisibleColumns.contains(key + "Price" + AccrualRateUtils.DOT + (j))) {
                    accrualRateProjectionDTO.addStringProperties(key + "Price" + AccrualRateUtils.DOT + (j++), String.valueOf((object[7] == null ? 0.00 : AccrualRateUtils.DOLLAR_FORMAT.format(object[7]))));

                }

                if (availableVisibleColumns.contains(key + Constant.SALES_SMALL + AccrualRateUtils.DOT + (j))) {
                    accrualRateProjectionDTO.addStringProperties(key + Constant.SALES_SMALL + AccrualRateUtils.DOT + (j++), String.valueOf((object[8] == null ? 0.00 : AccrualRateUtils.DOLLAR_FORMAT.format(object[8]))));

                }
            } else {

                if (availableVisibleColumns.contains(key + "TotalUnits" + AccrualRateUtils.DOT + (j))) {
                    accrualRateProjectionDTO.addStringProperties(key + "TotalUnits" + AccrualRateUtils.DOT + (j++), String.valueOf((object[9] == null ? 0 : AccrualRateUtils.NO_DECIMAL_FORMAT.format(object[9]))));

                }

                if (availableVisibleColumns.contains(key + "ExcludedUnits" + AccrualRateUtils.DOT + (j))) {
                    accrualRateProjectionDTO.addStringProperties(key + "ExcludedUnits" + AccrualRateUtils.DOT + (j++), String.valueOf((object[10] == null ? 0 : AccrualRateUtils.NO_DECIMAL_FORMAT.format(object[10]))));

                }

                if (availableVisibleColumns.contains(key + "NetUnits" + AccrualRateUtils.DOT + (j))) {
                    accrualRateProjectionDTO.addStringProperties(key + "NetUnits" + AccrualRateUtils.DOT + (j++), String.valueOf((object[11] == null ? 0 : AccrualRateUtils.NO_DECIMAL_FORMAT.format(object[11]))));

                }

                if (availableVisibleColumns.contains(key + "Price" + AccrualRateUtils.DOT + (j))) {
                    accrualRateProjectionDTO.addStringProperties(key + "Price" + AccrualRateUtils.DOT + (j++), String.valueOf((object[12] == null ? 0.00 : AccrualRateUtils.DOLLAR_FORMAT.format(object[12]))));

                }

                if (availableVisibleColumns.contains(key + Constant.SALES_SMALL + AccrualRateUtils.DOT + (j))) {
                    accrualRateProjectionDTO.addStringProperties(key + Constant.SALES_SMALL + AccrualRateUtils.DOT + (j++), String.valueOf((object[13] == null ? 0.00 : AccrualRateUtils.DOLLAR_FORMAT.format(object[13]))));

                }

            }
            if (i == list.size() - 1) {
                resultList.add(accrualRateProjectionDTO);
            }
        }
        return resultList;
    }

    /**
     * Customizes the result set to AccrualRateProjectionDTO and returns a list
     * of AccrualRateProjectionDTO.
     *
     * @param accrualRateSelectionDTO - Selection DTO to be used in the
     * customization.
     * @param list - List of object array (result set) from database.
     * @param start
     * @param offset
     * @param isExcelExport
     * @return
     */
    public List<AccrualRateProjectionDTO> customizeDetailsToDTO(final AccrualRateSelectionDTO accrualRateSelectionDTO, final List list, final int start, final int offset, final boolean isExcelExport) {
        final List<AccrualRateProjectionDTO> resultList = new ArrayList();
        final Map<String, AccrualRateProjectionDTO> resultMap = new HashMap(); // Map Contains the variable and the corresponding DTO.
        final Map<String, Integer> indexMap = new HashMap(); // Map contains the variable and corresponding result set column index.
        final Map<String, String> formatMap = new HashMap(); // Map contains the variable and corresponding format (Rate or Amount).
        final String[] variables = AccrualRateUtils.DetailsVariables.toArray(); // Array contains all the available variables in Details Tab.
        final List<String> variableList = accrualRateSelectionDTO.getVariableList(); // List contains the selected variable in the Details Tab.
        final boolean checkAll = variableList.contains(variables[0]);
        int j = 2;
        for (int i = 1; i < variables.length; i++) {
            if (checkAll || variableList.contains(variables[i])) {
                AccrualRateProjectionDTO accrualRateProjectionDTO = new AccrualRateProjectionDTO();
                resultMap.put(variables[i], accrualRateProjectionDTO);
                indexMap.put(variables[i], j);

                formatMap.put(variables[i], i == 4 || i == 10 || i == 12 ? AccrualRateUtils.RATE : AccrualRateUtils.AMOUNT);

                accrualRateProjectionDTO.setGroup(variables[i]);
                resultList.add(accrualRateProjectionDTO);
            }
            j++;
        }
        int k = 0;
        for (int i = 0; i < list.size(); i++) {
            Object[] object = (Object[]) list.get(i);
            String commonColumn = AccrualRateUtils.M + object[0] + AccrualRateUtils.DASH + object[1] + AccrualRateUtils.DOT + k;
            if (accrualRateSelectionDTO.getAvailableVisibleColumns().contains(commonColumn)) {
                for (Map.Entry<String, Integer> entry : indexMap.entrySet()) {
                    resultMap.get(entry.getKey()).addStringProperties(commonColumn, checkAndFormatValues(object[entry.getValue()], formatMap.get(entry.getKey())));
                }
                k++;
            }
        }
        return resultList.isEmpty() || isExcelExport ? resultList : resultList.subList(start, start + offset);
    }

    /**
     * Checks for null object and returns '-', if the value is null.Otherwise
     * returns the formatted value in the required format.
     *
     * @param object
     * @param decimalFormat
     * @param isRateOrAmount
     * @return
     */
    public String checkAndFormatValues(final Object object, final DecimalFormat decimalFormat, boolean isRateOrAmount) {
        String value;
        if (object == null) {
            value = AccrualRateUtils.DASH;
        } else {
            value = decimalFormat.format(object) + (isRateOrAmount ? AccrualRateUtils.PERCENT : StringUtils.EMPTY);
        }
        return value;
    }

    /**
     * Checks for null object and returns '-', if the value is null.Otherwise
     * returns the formatted value in the required format.
     *
     * @param object
     * @param format
     * @return
     */
    public String checkAndFormatValues(final Object object, final String format) {
        String value;
        boolean isRateOrAmount = AccrualRateUtils.RATE.equals(format);
        final DecimalFormat decimalFormat = isRateOrAmount ? AccrualRateUtils.RATE_FORMAT : AccrualRateUtils.DOLLAR_FORMAT_WITH_COMMA;
        if (object == null) {
            value = AccrualRateUtils.DASH;
        } else {
            value = decimalFormat.format(object) + (isRateOrAmount ? AccrualRateUtils.PERCENT : StringUtils.EMPTY);
        }
        return value;
    }

    public List<AccrualRateProjectionDTO> getDataForSales(final AccrualRateSelectionDTO accrualRateSelectionDTO, final List<String> selectedVariables, int start, int offset, boolean isExcel) {
        final Timestamp startTimestamp = new Timestamp(accrualRateSelectionDTO.getStartDate().getTime());
        final Timestamp endTimestamp = new Timestamp(accrualRateSelectionDTO.getEndDate().getTime());

        StringBuilder query = new StringBuilder();
        if (!isExcel) {
            query.append(";WITH ITEM\n"
                    + "                AS (SELECT DISTINCT ITEM_MASTER_SID\n"
                    + "            FROM   ACCRUAL_PROJ_DETAILS APD\n"
                    + "            JOIN   CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID = APD.CCP_DETAILS_SID\n"
                    + "                                  AND APD.PROJECTION_MASTER_SID ='" + accrualRateSelectionDTO.getProjectionId() + "' \n"
                    + "            ORDER  BY ITEM_MASTER_SID\n"
                    + "                    OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY) \n");
        }
        query.append("select SA.ITEM_MASTER_SID,IM.ITEM_NO,P.MONTH,P.YEAR,SA.TOTAL_UNITS as actTotalUnits,\n"
                + "SA.EXCLUDED_UNITS as actExcludedUnits,\n"
                + "SA.NET_UNITS as actNetUnits,\n"
                + "SA.PRICE as actPrice,\n"
                + "SA.SALES as actSales,\n"
                + "NULL as prjTotalUnits,\n"
                + "NULL as projExcludedUnits,\n"
                + "NULL as projNetUnits,\n"
                + "NULL as projPrice,\n"
                + "NULL as projSales,\n"
                + "'true' as isactuals \n"
                + "from dbo.ST_ACCRUAL_SALES_ACTUALS SA JOIN dbo.ITEM_MASTER IM ON IM.ITEM_MASTER_SID=SA.ITEM_MASTER_SID\n"
                + "JOIN PERIOD P ON P.PERIOD_SID = SA.PERIOD_SID AND P.PERIOD_DATE >= '" + startTimestamp + "'\n");
        if (!isExcel) {
            query.append(" JOIN ITEM I ON I.ITEM_MASTER_SID = SA.ITEM_MASTER_SID \n");
        }
        query.append(" WHERE SA.PROJECTION_MASTER_SID=" + accrualRateSelectionDTO.getProjectionId() + "\n"
                + " AND SESSION_ID =" + accrualRateSelectionDTO.getSessionId() + "\n"
                + " AND USER_ID =" + accrualRateSelectionDTO.getUserId() + "\n"
                + "UNION \n"
                + "select SD.ITEM_MASTER_SID,IM.ITEM_NO,P.MONTH,P.YEAR,\n"
                + "NULL as actTotalUnits,\n"
                + "NULL as actExcludedUnits,\n"
                + "NULL as actNetUnits,\n"
                + "NULL as actPrice,\n"
                + "NULL as actSales,\n"
                + "SD.TOTAL_UNITS as prjTotalUnits,\n"
                + "SD.EXCLUDED_UNITS as projExcludedUnits,\n"
                + "SD.NET_UNITS as projNetUnits,\n"
                + "SD.PRICE as projPrice,\n"
                + "SD.SALES as projSales,\n"
                + "'false' as isactuals              \n"
                + "from dbo.ST_ACCRUAL_SALES_DETAILS SD JOIN dbo.ITEM_MASTER IM ON IM.ITEM_MASTER_SID=SD.ITEM_MASTER_SID\n"
                + "JOIN PERIOD P ON P.PERIOD_SID = SD.PERIOD_SID AND P.PERIOD_DATE <= '" + endTimestamp + "'\n");
        if (!isExcel) {
            query.append(" JOIN ITEM I ON I.ITEM_MASTER_SID = SD.ITEM_MASTER_SID \n");
        }
        query.append("WHERE SD.PROJECTION_MASTER_SID=" + accrualRateSelectionDTO.getProjectionId() + "\n"
                + " AND SESSION_ID =" + accrualRateSelectionDTO.getSessionId() + "\n"
                + " AND USER_ID =" + accrualRateSelectionDTO.getUserId() + "\n"
                + " ORDER BY SA.ITEM_MASTER_SID,IM.ITEM_NO,P.YEAR,P.MONTH");

        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
        return customizeSalesToDTO(list, accrualRateSelectionDTO, selectedVariables);
    }

    /**
     *
     * @param comboBox
     * @param projectionId
     */
    public void loadCustomers(final ComboBox comboBox, final String projectionId) {
        String query = CustomSQLUtil.get("load-customers");
        query = query.replace("@PROJECTION_MASTER_SID", projectionId);
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        customizeDataForDDLB(list, comboBox);
    }

    /**
     *
     * @param comboBox
     * @param companyMasterId
     * @param projectionId
     */
    public void loadBrands(final ComboBox comboBox, final Object companyMasterId, final String projectionId) {
        String query = CustomSQLUtil.get("load-brands");
        if (companyMasterId == null) {
            query = query.replace("AND CM.COMPANY_MASTER_SID = @COMPANY_MASTER_SID", StringUtils.EMPTY);
        } else {
            query = query.replace("@COMPANY_MASTER_SID", companyMasterId.toString());
        }
        query = query.replace("@PROJECTION_MASTER_SID", projectionId);
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        customizeDataForDDLB(list, comboBox);
    }

    /**
     *
     * @param comboBox
     * @param companyMasterId
     * @param brandMasterId
     * @param projectionId
     */
    public void loadProducts(final ComboBox comboBox, final Object companyMasterId, final Object brandMasterId, final String projectionId) {
        String query = CustomSQLUtil.get("load-products");
        if (companyMasterId == null) {
            query = query.replace("AND CM.COMPANY_MASTER_SID = @COMPANY_MASTER_SID", StringUtils.EMPTY);
        } else {
            query = query.replace("@COMPANY_MASTER_SID", companyMasterId.toString());
        }
        if (brandMasterId == null) {
            query = query.replace("AND IM.BRAND_MASTER_SID = @BRAND_MASTER_SID", StringUtils.EMPTY);
        } else {
            query = query.replace("@BRAND_MASTER_SID", brandMasterId.toString());
        }
        query = query.replace("@PROJECTION_MASTER_SID", projectionId);
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        customizeDataForDDLB(list, comboBox);
    }

    /**
     * Sets the values form Result set to Combo Box.
     *
     * @param list
     * @param comboBox
     */
    public void customizeDataForDDLB(final List list, final ComboBox comboBox) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Object[] object = (Object[]) list.get(i);
                comboBox.addItem(object[0]);
                comboBox.setItemCaption(object[0], object[1].toString());
            }
        }
    }

    /**
     * Calls the procedure with the given procedure name and parameters.
     *
     * @param parameters - parameters required for procedure.
     * @param procedureName - procedure name.
     */
    public void callARPProcedure(final Object[] parameters, final String procedureName) {

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();
            if (connection != null) {
                StringBuilder arguments = new StringBuilder("(");
                for (int i = 0; i < parameters.length; i++) {
                    arguments.append("?,");
                }
                String args = arguments.substring(0, arguments.length() - 1) + ")}";
                statement = connection.prepareCall("{CALL " + procedureName + args);
                LOGGER.info("Procedure Name: " + procedureName);
                for (int i = 0; i < parameters.length; i++) {
                    LOGGER.info("Parameter: " + (i + 1) + " ---- " + parameters[i]);
                    statement.setObject(i + 1, parameters[i]);
                }
                statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }

    public int getCompanysCount(final List<SortByColumn> columns, final Set<Container.Filter> filterSet) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT( DISTINCT A.COMPANY_MASTER_SID)\n"
                + "FROM   CUSTOMER_GTS_FORECAST A\n"
                + "JOIN   (SELECT     TOP 1 FT.FORECAST_NAME,\n"
                + "                         FT.[VERSION]\n"
                + "        FROM       FILE_MANAGEMENT FT\n"
                + "        INNER JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = FT.FILE_TYPE\n"
                + "        WHERE      ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())\n"
                + "                     AND FT.FROM_PERIOD IS NOT NULL )\n"
                + "                   AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())\n"
                + "                          OR FT.TO_PERIOD IS NULL )\n"
                + "                   AND HT.LIST_NAME = 'FILE_TYPE'\n"
                + "                   AND HT.[DESCRIPTION] IN ( 'CUSTOMER SALES' )\n"
                + "        ORDER      BY FT.FROM_PERIOD DESC) B ON A.FORECAST_NAME = B.FORECAST_NAME\n"
                + "                                            AND A.FORECAST_VER = B.VERSION\n"
                + "JOIN   COMPANY_MASTER C ON A.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID");

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    if (AccrualRateUtils.COMPANY_ID.equals(stringFilter.getPropertyId())) {
                        filterString = filterString.replace(Constant.PERCENT, StringUtils.EMPTY);
                        if (StringUtils.isNotBlank(filterString)) {
                            query.append(" Where A.COMPANY_ID LIKE '%").append(filterString).append("%'");
                        }
                    }
                }
            }
        }

        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
        return list == null || list.get(0) == null ? 0 : Integer.valueOf(list.get(0).toString());
    }

    public List<AccrualRateSelectionDTO> getAvalableValues(final List<SortByColumn> columns, final Set<Container.Filter> filterSet, int start, int end) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT A.COMPANY_MASTER_SID,\n"
                + "                A.COMPANY_ID,\n"
                + "                C.COMPANY_NAME\n"
                + "FROM   CUSTOMER_GTS_FORECAST A\n"
                + "JOIN   (SELECT     TOP 1 FT.FORECAST_NAME,\n"
                + "                         FT.[VERSION]\n"
                + "        FROM       FILE_MANAGEMENT FT\n"
                + "        INNER JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = FT.FILE_TYPE\n"
                + "        WHERE      ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())\n"
                + "                     AND FT.FROM_PERIOD IS NOT NULL )\n"
                + "                   AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())\n"
                + "                          OR FT.TO_PERIOD IS NULL )\n"
                + "                   AND HT.LIST_NAME = 'FILE_TYPE'\n"
                + "                   AND HT.[DESCRIPTION] IN ( 'CUSTOMER SALES' )\n"
                + "        ORDER      BY FT.FROM_PERIOD DESC) B ON A.FORECAST_NAME = B.FORECAST_NAME\n"
                + "                                            AND A.FORECAST_VER = B.VERSION\n"
                + "JOIN   COMPANY_MASTER C ON A.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID");
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    if (AccrualRateUtils.COMPANY_ID.equals(stringFilter.getPropertyId())) {
                        filterString = filterString.replace(Constant.PERCENT, StringUtils.EMPTY);
                        if (StringUtils.isNotBlank(filterString)) {
                            query.append(" Where A.COMPANY_ID LIKE '%").append(filterString).append("%'");
                        }
                    }
                }
            }
        }

        String orderByColumn = null;
        boolean sortOrder = false;
        if (columns != null) {
            for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
                orderByColumn = sortByColumn.getName();
                if (null != orderByColumn) {
                    switch (orderByColumn) {
                        case AccrualRateUtils.COMPANY_ID:
                            query.append(" ORDER BY A.COMPANY_ID ").append((!sortOrder) ? " ASC " : " DESC ");
                            break;
                        default:
                            query.append(" ORDER BY A.COMPANY_ID  ASC ");
                            break;
                    }

                }
            }
        }
        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            query.append(" ORDER BY A.COMPANY_ID  ASC ");
        }
        query.append(" OFFSET ").append(start);
        query.append(" ROWS FETCH NEXT ").append(end).append(" ROWS ONLY;");

        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
        return customizeAvalableValuesToDTO(list);

    }

    public List<AccrualRateSelectionDTO> customizeAvalableValuesToDTO(final List list) {
        List<AccrualRateSelectionDTO> resultList = new ArrayList<>();
        int listSize = list.size();
        try {
            if (!list.isEmpty()) {
                for (int i = 0; i < listSize; i++) {
                    Object[] object = (Object[]) list.get(i);

                    AccrualRateSelectionDTO accrualRateSelectionDTO = new AccrualRateSelectionDTO();
                    if (object[0] != null) {
                        accrualRateSelectionDTO.setCompanyId(object[1].toString());
                        accrualRateSelectionDTO.setCompanyMasterSid(object[0].toString());
                        accrualRateSelectionDTO.setCompanyName(object[2].toString());
                    }
                    resultList.add(accrualRateSelectionDTO);
                }
            }
        } catch (Exception e) {

        }
        return resultList;
    }

    public void addToTempTable(String companySid, AccrualRateSelectionDTO dto) {
        StringBuffer query = new StringBuffer();
        query.append("Insert into ST_EXCLUSION_DETAILS (PROJECTION_MASTER_SID,COMPANY_MASTER_SID,USER_ID,SESSION_ID) values ('" + dto.getProjectionId() + "','"
                + companySid + "','"
                + dto.getUserId() + "','"
                + dto.getSessionId() + "')");
              HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());

    }

    public void removeFromTempTable(String CompanySid, AccrualRateSelectionDTO dto, boolean flag) {

        StringBuffer deleteQuery = new StringBuffer();
        String str = null;
        deleteQuery.append("DELETE FROM ST_EXCLUSION_DETAILS WHERE");
        if (flag) {
            str = " COMPANY_MASTER_SID ='" + CompanySid;
        } else {
            str = " PROJECTION_MASTER_SID = '" + dto.getProjectionId();
        }

        deleteQuery.append(str
                + "' AND SESSION_ID='"
                + dto.getSessionId() + "' AND USER_ID ='"
                + dto.getUserId() + "'");
                HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery.toString());

    }

    public int getExcludedCompanysCount(AccrualRateSelectionDTO dto, final List<SortByColumn> columns, final Set<Container.Filter> filterSet) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT COUNT (ED.COMPANY_MASTER_SID) FROM ST_EXCLUSION_DETAILS ED \n"
                + "INNER JOIN COMPANY_MASTER CM ON ED.COMPANY_MASTER_SID=CM.COMPANY_MASTER_SID\n"
                + "WHERE ED.PROJECTION_MASTER_SID='"
                + dto.getProjectionId() + "' AND ED.SESSION_ID='"
                + dto.getSessionId() + "' AND ED.USER_ID ='"
                + dto.getUserId() + "'");
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    if (AccrualRateUtils.EXCLUDED_FIELD.equals(stringFilter.getPropertyId())) {
                        filterString = filterString.replace(Constant.PERCENT, StringUtils.EMPTY);
                        if (StringUtils.isNotBlank(filterString)) {

                            query.append(" AND CM.COMPANY_ID LIKE '%").append(filterString).append("%'");

                        }

                    }
                    if (AccrualRateUtils.COMPANY_NAME.equals(stringFilter.getPropertyId())) {
                        filterString = filterString.replace(Constant.PERCENT, StringUtils.EMPTY);
                        if (StringUtils.isNotBlank(filterString)) {

                            query.append(" AND CM.COMPANY_NAME LIKE '%").append(filterString).append("%'");
                        }
                    }
                }
            }
        }

        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
        return list == null || list.get(0) == null ? 0 : Integer.valueOf(list.get(0).toString());
    }

    public List getExcluededCompanys(AccrualRateSelectionDTO accrualRateSelectionDTO, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, int start, int end) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ED.COMPANY_MASTER_SID,CM.COMPANY_ID,CM.COMPANY_NAME FROM ST_EXCLUSION_DETAILS ED \n"
                + "INNER JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=ED.COMPANY_MASTER_SID WHERE PROJECTION_MASTER_SID='"
                + accrualRateSelectionDTO.getProjectionId() + "' AND SESSION_ID='"
                + accrualRateSelectionDTO.getSessionId() + "' AND USER_ID ='"
                + accrualRateSelectionDTO.getUserId() + "'");
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    if (AccrualRateUtils.EXCLUDED_FIELD.equals(stringFilter.getPropertyId())) {
                        filterString = filterString.replace(Constant.PERCENT, StringUtils.EMPTY);
                        if (StringUtils.isNotBlank(filterString)) {

                            query.append(" AND CM.COMPANY_ID LIKE '%").append(filterString).append("%'");

                        }

                    }
                    if (AccrualRateUtils.COMPANY_NAME.equals(stringFilter.getPropertyId())) {
                        filterString = filterString.replace(Constant.PERCENT, StringUtils.EMPTY);
                        if (StringUtils.isNotBlank(filterString)) {

                            query.append(" AND CM.COMPANY_NAME LIKE '%").append(filterString).append("%'");
                        }
                    }
                }
            }
        }
        String orderByColumn = null;
        boolean sortOrder = false;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
                orderByColumn = sortByColumn.getName();
                if (null != orderByColumn) {
                    switch (orderByColumn) {
                        case AccrualRateUtils.EXCLUDED_FIELD:
                            query.append(" ORDER BY CM.COMPANY_ID ").append((!sortOrder) ? " ASC " : " DESC ");
                            break;
                        case AccrualRateUtils.COMPANY_NAME:
                            query.append(" ORDER BY CM.COMPANY_NAME ").append((!sortOrder) ? " ASC " : " DESC ");
                            break;
                        default:
                            query.append(" ORDER BY CM.COMPANY_NAME  ASC ");
                            break;
                    }
                }
            }
        }


        if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
            query.append(" ORDER BY CM.COMPANY_NAME  ASC ");
        }

        query.append(" OFFSET ").append(start);
        query.append(" ROWS FETCH NEXT ").append(end).append(" ROWS ONLY;");
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
        return customizeExludedValuesToDTO(list);
    }

    private List customizeExludedValuesToDTO(List list) {

        List<AccrualRateSelectionDTO> resultList = new ArrayList<>();
        List<String> listComSid = new ArrayList<>();
        int listSize = list.size();
        try {
            if (!list.isEmpty()) {
                for (int i = 0; i < listSize; i++) {
                    Object[] object = (Object[]) list.get(i);
                    AccrualRateSelectionDTO accrualRateSelectionDTO = new AccrualRateSelectionDTO();
                    if (object[0] != null) {
                        accrualRateSelectionDTO.setCompanyMasterSid(object[0].toString());
                        listComSid.add(object[0].toString());
                        accrualRateSelectionDTO.setExcludedField(object[1].toString());
                        accrualRateSelectionDTO.setCompanyName(object[2].toString());

                    }
                    resultList.add(accrualRateSelectionDTO);
                }
                setSelectedCompanyList(listComSid);
            }
        } catch (Exception e) {

        }
        return resultList;
    }

    public static List<String> getSelectedCompanyList() {
        return selectedCompanyList;
    }

    public static void setSelectedCompanyList(List<String> selectedCompanyList) {
        AccrualRateProjectionLogic.selectedCompanyList = selectedCompanyList;
    }

    public void moveAllCompanys(AccrualRateSelectionDTO dto) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ST_EXCLUSION_DETAILS (PROJECTION_MASTER_SID, COMPANY_MASTER_SID, USER_ID, SESSION_ID) \n"
                + " SELECT DISTINCT '" + dto.getProjectionId() + "',A.COMPANY_MASTER_SID,'" + dto.getUserId() + "','" + dto.getSessionId() + "'\n"
                + " FROM   CUSTOMER_GTS_FORECAST A\n"
                + "JOIN   (SELECT     TOP 1 FT.FORECAST_NAME,\n"
                + "                         FT.[VERSION]\n"
                + "        FROM       FILE_MANAGEMENT FT\n"
                + "        INNER JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = FT.FILE_TYPE\n"
                + "        WHERE      ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())\n"
                + "                     AND FT.FROM_PERIOD IS NOT NULL )\n"
                + "                   AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())\n"
                + "                          OR FT.TO_PERIOD IS NULL )\n"
                + "                   AND HT.LIST_NAME = 'FILE_TYPE'\n"
                + "                   AND HT.[DESCRIPTION] IN ( 'CUSTOMER SALES' )\n"
                + "        ORDER      BY FT.FROM_PERIOD DESC) B ON A.FORECAST_NAME = B.FORECAST_NAME\n"
                + "                                            AND A.FORECAST_VER = B.VERSION\n"
                + "JOIN   COMPANY_MASTER C ON A.COMPANY_MASTER_SID = C.COMPANY_MASTER_SID");

               HelperTableLocalServiceUtil.executeUpdateQuery(query.toString());
    }

    /**
     * Sets the values for session DTO to AccrualRateSelectionDTO.
     *
     * @param sessionDTO
     * @param accrualRateSelectionDTO
     */
    public void setValuesInDTO(final SessionDTO sessionDTO, final AccrualRateSelectionDTO accrualRateSelectionDTO) {
        accrualRateSelectionDTO.setProjectionId(StringUtils.EMPTY + sessionDTO.getProjectionId());
        accrualRateSelectionDTO.setUserId(StringUtils.EMPTY + sessionDTO.getUserId());
        accrualRateSelectionDTO.setSessionId(StringUtils.EMPTY + sessionDTO.getSessionId());
    }

}
