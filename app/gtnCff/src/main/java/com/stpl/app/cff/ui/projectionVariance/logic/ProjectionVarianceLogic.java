/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionVariance.logic;

import com.stpl.app.cff.abstractCff.AbstractFilterLogic;
import com.stpl.app.cff.dao.CommonDAO;
import com.stpl.app.cff.dao.impl.CommonDAOImpl;
import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.queryUtils.CommonQueryUtils;
import com.stpl.app.cff.ui.projectionVariance.dto.ComparisonLookupDTO;
import com.stpl.app.cff.ui.projectionVariance.dto.ProjectionVarianceDTO;
import com.stpl.app.cff.ui.projectionVariance.form.RunnableJob;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import static com.stpl.app.cff.util.Constants.ButtonConstants.ALL;
import static com.stpl.app.cff.util.Constants.CommonConstants.CHANGE;
import static com.stpl.app.cff.util.Constants.CommonConstants.NULL;
import static com.stpl.app.cff.util.Constants.CommonConstants.VALUE;
import static com.stpl.app.cff.util.Constants.CommonConstants.VARIANCE;
import static com.stpl.app.cff.util.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.cff.util.Constants.LabelConstants.PERCENT;
import static com.stpl.app.cff.util.Constants.LabelConstants.TOTAL;
import static com.stpl.app.cff.util.Constants.LabelConstants.TOTAL_DISCOUNT;
import static com.stpl.app.cff.util.Constants.LabelConstants.*;
import com.stpl.app.cff.util.Converters;
import com.stpl.app.cff.util.HeaderUtils;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.jboss.logging.Logger;

/**
 *
 * @author manikandaprabu.g
 */
public class ProjectionVarianceLogic {

    private static final CommonDAO commonDao = new CommonDAOImpl();
    public static final Logger LOGGER = Logger.getLogger(ProjectionVarianceLogic.class);
    private String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    /**
     * The Constant AMOUNT.
     */
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0");
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat RATE = new DecimalFormat("#######0.00");
    /**
     * The Constant RATE.
     */
    private static final DecimalFormat RATE_PER = new DecimalFormat("#,##0.00");
    /**
     * RATE_PER_THREE
     */
    private static final DecimalFormat RATE_PER_THREE = new DecimalFormat("#,##0.00");
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    private static final String ZERO = "0";
    private static final String DETAIL = "Detail";
    private static final String C = "C";
    private static final String P = "P";
    private static String CURRENT = "Current";
    private static String ACTUAL = "Actual";
    private CommonLogic commonLogic = new CommonLogic();
    private static String DASH = "-";
    com.stpl.app.cff.ui.projectionVariance.queryUtils.PVQueryUtils queryUtils = new com.stpl.app.cff.ui.projectionVariance.queryUtils.PVQueryUtils();
    ProjectionVarianceDTO valueGTS = new ProjectionVarianceDTO();
    ProjectionVarianceDTO variGTS = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perGTS = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valuePercBuisness = new ProjectionVarianceDTO();
    ProjectionVarianceDTO varpercBuisness = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perPercBuisness = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valueContractSales = new ProjectionVarianceDTO();
    ProjectionVarianceDTO varContractSales = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perContractSales = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valueUnitResult = new ProjectionVarianceDTO();
    ProjectionVarianceDTO varUnitResult = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perUnitResult = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valuediscountDollar = new ProjectionVarianceDTO();
    ProjectionVarianceDTO vardiscountDollar = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perdiscountDollar = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valuediscountPer = new ProjectionVarianceDTO();
    ProjectionVarianceDTO vardiscountPer = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perdiscountPer = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valueNetSales = new ProjectionVarianceDTO();
    ProjectionVarianceDTO variNetSales = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perNetSales = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valuePPARate = new ProjectionVarianceDTO();
    ProjectionVarianceDTO variPPARate = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perPPARate = new ProjectionVarianceDTO();
    ProjectionVarianceDTO valuePPAAmount = new ProjectionVarianceDTO();
    ProjectionVarianceDTO variPPAAmount = new ProjectionVarianceDTO();
    ProjectionVarianceDTO perPPAAmount = new ProjectionVarianceDTO();
    CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    PVSelectionDTO selectionDTO = new PVSelectionDTO();
    List<ProjectionVarianceDTO> projDTOList1 = new ArrayList<>();
    List<List> currentDiscount = new ArrayList<List>();
    List<List> currentSales = new ArrayList<List>();
    List<List> currentTotal = new ArrayList<List>();
    List<Integer> priorIdTotal = new ArrayList<Integer>();
    List<List> currentTotalDiscount = new ArrayList<List>();
    List<Integer> priorProjIdDiscountList = new ArrayList<Integer>();
    List<Integer> priorProjIdSalesList = new ArrayList<Integer>();
    List<Integer> priorProjIdTotalList = new ArrayList<Integer>();
    List<List> currentPivotGTSTotal = new ArrayList<List>();
    List<Integer> pivotPriorProjIdList = new ArrayList<Integer>();
    List<List> currentDiscountDol = new ArrayList<List>();
    List<List> currentDiscountPPADol = new ArrayList<List>();
    List<List> currentDiscountPer = new ArrayList<List>();
    List<List> currentDiscountPPAPer = new ArrayList<List>();
    List<Object> pivotTotalList = new ArrayList<Object>();
    List<Object> pivotDiscountList = new ArrayList<Object>();
    int currentProjId;
    private static final String PRC_CFF_RESULTS = "Prc_cff_results";
    private List chartList;
    private static Thread procedureThread;
    private static RunnableJob runnableJob;

    public List getChartList() {
        return chartList;
    }

    public void setChartList(List chartList) {
        this.chartList = chartList;
    }

    public PVSelectionDTO getSelectionDTO() {
        return selectionDTO;
    }

    public void setSelectionDTO(PVSelectionDTO selectionDTO) {
        this.selectionDTO = selectionDTO;
    }

    public CustomTableHeaderDTO getLeftHeader() {
        return leftHeader;
    }

    public void setLeftHeader(CustomTableHeaderDTO leftHeader) {
        this.leftHeader = leftHeader;
    }

    public CustomTableHeaderDTO getRightHeader() {
        return rightHeader;
    }

    public void setRightHeader(CustomTableHeaderDTO rightHeader) {
        this.rightHeader = rightHeader;
    }

    public int getCurrentProjId() {
        return currentProjId;
    }

    public void setCurrentProjId(int currentProjId) {
        this.currentProjId = currentProjId;
    }

    /**
     * Date range method to filter according to the date range selection
     *
     * @param rightTable
     * @param rightHeader
     * @param fromDate
     * @param toDate
     */
    public CustomTableHeaderDTO getDateRangeHeaders(final ExtFilterTreeTable rightTable, CustomTableHeaderDTO rightHeader, CustomTableHeaderDTO fullHeader, Object fromDate, Object toDate) {
        CustomTableHeaderDTO newFullHeader = new CustomTableHeaderDTO();
        String fromValue = String.valueOf(fromDate);
        String toValue = String.valueOf(toDate);
        String[] fromArray = fromValue.split(" ");
        String[] toArray = toValue.split(" ");
        List<Object> visibleDoubleCol = new ArrayList<Object>(rightHeader.getDoubleColumns());
        List<String> visibleDoubleHeader = new ArrayList<String>(rightHeader.getDoubleHeaders());
        List<Object> visibleSingleCol = new ArrayList<Object>();
        List<Object> visibleSingleColumn = new ArrayList<Object>(rightHeader.getSingleColumns());
        List<String> visibleSingleHeader = new ArrayList<String>(rightHeader.getSingleHeaders());
        List<String> visibleSingleHead = new ArrayList<String>();

        Map<Object, Object[]> doubleMap = rightHeader.getDoubleHeaderMaps();
        Map<Object, Object[]> doubleFinalMap = new HashMap<Object, Object[]>();
        List<Object> finalVisList = new ArrayList<Object>();
        List<String> finalHeaderList = new ArrayList<String>();
        String from = StringUtils.EMPTY;
        String to = StringUtils.EMPTY;
        for (int i = 0; i < fromArray.length; i++) {
            from = from + fromArray[i];
        }
        for (int i = 0; i < toArray.length; i++) {
            to = to + toArray[i];
        }
        if (rightHeader.getFrequencyDivision() == NumericConstants.TWELVE) {
            from = from.toLowerCase();
            to = to.toLowerCase();
        }

        int start = visibleDoubleCol.indexOf(from);
        int end = visibleDoubleCol.indexOf(to);
        if (rightHeader.getProjectionOrder() == NumericConstants.TWO) {
            int temp;
            temp = start;
            start = end;
            end = temp;
        }

        for (int i = start; i <= end; i++) {

            finalVisList.add(visibleDoubleCol.get(i));
            finalHeaderList.add(visibleDoubleHeader.get(i));
            visibleSingleCol.addAll(Arrays.asList(doubleMap.get(visibleDoubleCol.get(i))));
            doubleFinalMap.put(visibleDoubleCol.get(i), doubleMap.get(visibleDoubleCol.get(i)));

        }
        int startSingle = visibleSingleColumn.indexOf(visibleSingleCol.get(0));
        int endSingle = visibleSingleColumn.indexOf(visibleSingleCol.get(visibleSingleCol.size() - 1));

        for (int i = startSingle; i <= endSingle; i++) {
            visibleSingleHead.add(visibleSingleHeader.get(i));
        }
        rightTable.setVisibleColumns(visibleSingleCol.toArray());
        rightTable.setColumnHeaders(visibleSingleHead.toArray(new String[visibleSingleHead.size()]));
        rightTable.setDoubleHeaderVisibleColumns(finalVisList.toArray());
        rightTable.setDoubleHeaderColumnHeaders(finalHeaderList.toArray(new String[finalHeaderList.size()]));
        rightTable.setDoubleHeaderMap(doubleFinalMap);
        // For Chart and Excel Header when date range is selected
        Object singleCol = "group";
        newFullHeader.addSingleColumn(singleCol, " ", String.class);
        for (int i = 0; i < visibleSingleCol.size(); i++) {
            String singleHeader = fullHeader.getSingleHeader(visibleSingleCol.get(i));
            newFullHeader.addSingleColumn(visibleSingleCol.get(i), singleHeader, String.class);
        }
        return newFullHeader;
    }

    /**
     * customize the results
     *
     * @param list
     * @return list
     */
    public List<ComparisonLookupDTO> getCustomizedComparisonList(final List list) throws PortalException, SystemException {
        final List<ComparisonLookupDTO> finalList = new ArrayList<ComparisonLookupDTO>();
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                ComparisonLookupDTO comparisonLookupDTO = new ComparisonLookupDTO();
                Object[] obj = (Object[]) list.get(i);
                if (obj[0] == null) {
                    comparisonLookupDTO.setProjectionName(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setProjectionName(obj[0].toString());
                }
                if (obj[1] == null) {
                    comparisonLookupDTO.setProjectionDescription(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setProjectionDescription(obj[1].toString());
                }
                if (obj[NumericConstants.TWO] == null) {
                    comparisonLookupDTO.setMarketType(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setMarketType(obj[NumericConstants.TWO].toString());
                }
                if (obj[NumericConstants.FOUR] == null) {
                    comparisonLookupDTO.setContract(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setContract(obj[NumericConstants.FOUR].toString());
                }
                if (obj[NumericConstants.THREE] == null) {
                    comparisonLookupDTO.setCustomer(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setCustomer(obj[NumericConstants.THREE].toString());
                }
                if (obj[NumericConstants.FIVE] == null) {
                    comparisonLookupDTO.setBrand(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setBrand(obj[NumericConstants.FIVE].toString());
                }
                if (obj[NumericConstants.SIX] == null) {
                    comparisonLookupDTO.setNdcNo(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setNdcNo(obj[NumericConstants.SIX].toString());
                }
                if (obj[NumericConstants.SEVEN] == null) {
                    comparisonLookupDTO.setNdcName(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setNdcName(obj[NumericConstants.SEVEN].toString());
                }
                if (obj[NumericConstants.EIGHT] == null) {
                    comparisonLookupDTO.setProjectionId(0);
                } else {
                    comparisonLookupDTO.setProjectionId(Integer.valueOf(obj[NumericConstants.EIGHT].toString()));
                }
                if (obj[NumericConstants.NINE] == null) {
                    comparisonLookupDTO.setCreatedDateFrom(null);
                } else {
                    comparisonLookupDTO.setCreatedDateFrom((Date) obj[NumericConstants.NINE]);
                }
                if (obj[NumericConstants.TEN] == null) {
                    comparisonLookupDTO.setCreatedBy(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setCreatedBy(new Converters().getUserFLName(new Converters().convertNullToEmpty(obj[NumericConstants.TEN].toString())));
                }
                finalList.add(comparisonLookupDTO);

            }
        }
        return finalList;
    }

    public List<ComparisonLookupDTO> getComparisonResults(final ComparisonLookupDTO comparisonLookup) throws PortalException, SystemException {
        String ASTERIK = "*";
        String PERCENT = "%";
        String andOperator = StringUtils.EMPTY;
        List inputList = getComparisonInput(comparisonLookup);
        StringBuilder query = CommonQueryUtils.getSqlQuery(inputList, "comparisonLoadData");
        query.append(" where ");
        if (comparisonLookup.getContract() != null && !comparisonLookup.getContract().isEmpty()) {
            query.append(" CONTRACT LIKE ").append("'").append(comparisonLookup.getContract().replace(ASTERIK, PERCENT)).append("'");
            andOperator = " AND ";
        } else {
            query.append(" CONTRACT LIKE ").append("'%'");
            andOperator = " AND ";
        }
        if (comparisonLookup.getMarketType() != null && !comparisonLookup.getMarketType().isEmpty()) {
            query.append(andOperator).append("  MARKET_TYPE LIKE ").append("'").append(comparisonLookup.getMarketType().replace(ASTERIK, PERCENT)).append("'");
            andOperator = " AND ";
        } else {
            query.append(andOperator).append("  MARKET_TYPE LIKE ").append("'%'");
            andOperator = " AND ";
        }
        if (comparisonLookup.getCustomer() != null && !comparisonLookup.getCustomer().isEmpty()) {
            query.append(andOperator).append("  CONTRACT_HOLDER LIKE ").append("'").append(comparisonLookup.getCustomer().replace(ASTERIK, PERCENT)).append("'");
            andOperator = " AND ";
        } else {
            query.append(andOperator).append("  CONTRACT_HOLDER LIKE ").append("'%'");
            andOperator = " AND ";
        }
        if (comparisonLookup.getNdcName() != null && !comparisonLookup.getNdcName().isEmpty()) {
            query.append(andOperator).append("  ITEM_NAME LIKE ").append("'").append(comparisonLookup.getNdcName().replace(ASTERIK, PERCENT)).append("'");
            andOperator = " AND ";
        } else {
            query.append(andOperator).append("  ITEM_NAME LIKE ").append("'%'");
            andOperator = " AND ";
        }
        if (comparisonLookup.getNdcNo() != null && !comparisonLookup.getNdcNo().isEmpty()) {
            query.append(andOperator).append("  ITEM_NO LIKE ").append("'").append(comparisonLookup.getNdcNo().replace(ASTERIK, PERCENT)).append("'");
            andOperator = " AND ";
        } else {
            query.append(andOperator).append("  ITEM_NO LIKE ").append("'%'");
            andOperator = " AND ";
        }
        if (comparisonLookup.getBrand() != null && !comparisonLookup.getBrand().isEmpty()) {
            query.append(andOperator).append("  BRAND LIKE ").append("'").append(comparisonLookup.getBrand().replace(ASTERIK, PERCENT)).append("' ");
            andOperator = " AND ";
        } else {
            query.append(andOperator).append("  BRAND LIKE ").append("'%' ");
            andOperator = " AND ";
        }
        StringBuilder orderBy = AbstractFilterLogic.getInstance().orderByQueryGenerator(comparisonLookup.getSortColumns(), getOrderByMap());
        query.append(orderBy.toString());
        query.append(" OFFSET ").append(comparisonLookup.getStart()).append(" ROWS FETCH NEXT ").append(comparisonLookup.getOffset()).append(" ROWS ONLY");
        
        List result = (List) commonDao.executeSelectQuery(query.toString(), null, null);
        return getCustomizedComparisonList(result);
    }

    /**
     * customize the results
     *
     * @param list
     * @return list
     */
    public List<ComparisonLookupDTO> getCustomizedPVComparisonList(final List list) throws PortalException, SystemException {
        final List<ComparisonLookupDTO> finalList = new ArrayList<ComparisonLookupDTO>();
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                ComparisonLookupDTO comparisonLookupDTO = new ComparisonLookupDTO();
                Object[] obj = (Object[]) list.get(i);
                if (obj[0] == null) {
                    comparisonLookupDTO.setProjectionName(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setProjectionName(obj[0].toString());
                }
                if (obj[1] == null) {
                    comparisonLookupDTO.setProjectionDescription(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setProjectionDescription(obj[1].toString());
                }
                if (obj[NumericConstants.TWO] == null) {
                    comparisonLookupDTO.setMarketType(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setMarketType(obj[NumericConstants.TWO].toString());
                }
                if (obj[NumericConstants.THREE] == null) {
                    comparisonLookupDTO.setContract(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setContract(obj[NumericConstants.THREE].toString());
                }
                if (obj[NumericConstants.FOUR] == null) {
                    comparisonLookupDTO.setContractHolder(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setContractHolder(obj[NumericConstants.FOUR].toString());
                }
                if (obj[NumericConstants.FIVE] == null) {
                    comparisonLookupDTO.setBrand(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setBrand(obj[NumericConstants.FIVE].toString());
                }
                if (obj[NumericConstants.SIX] == null) {
                    comparisonLookupDTO.setNdcNo(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setNdcNo(obj[NumericConstants.SIX].toString());
                }
                if (obj[NumericConstants.SEVEN] == null) {
                    comparisonLookupDTO.setNdcName(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setNdcName(obj[NumericConstants.SEVEN].toString());
                }
                if (obj[NumericConstants.EIGHT] == null) {
                    comparisonLookupDTO.setProjectionId(0);
                } else {
                    comparisonLookupDTO.setProjectionId(Integer.valueOf(obj[NumericConstants.EIGHT].toString()));
                }
                if (obj[NumericConstants.NINE] == null) {
                    comparisonLookupDTO.setCreatedDateFrom(null);
                } else {
                    comparisonLookupDTO.setCreatedDateFrom((Date) obj[NumericConstants.NINE]);
                }
                if (obj[NumericConstants.TEN] == null) {
                    comparisonLookupDTO.setCreatedBy(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setCreatedBy(new Converters().getUserFLName(new Converters().convertNullToEmpty(obj[NumericConstants.TEN].toString())));
                }
                if (!isProjecionId(finalList, comparisonLookupDTO)) {
                    finalList.add(comparisonLookupDTO);
                }
            }
        }
        return finalList;
    }

    public Boolean isProjecionId(List<ComparisonLookupDTO> finalList, ComparisonLookupDTO comparisonLookupDTO) {
        for (ComparisonLookupDTO comparisonLookupDTO1 : finalList) {
            if (comparisonLookupDTO1.getProjectionId() == comparisonLookupDTO.getProjectionId()) {
                return true;
            }
        }
        return false;

    }

    public static String convertStringToDate(String stringDate, String inputFormat, String outputFormat) {
        try {
            SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
            Date date = new Date();
            date = inputDateFormatter.parse(stringDate);
            return outputDateFormatter.format(date);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CommonLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer getComparisonCount(ComparisonLookupDTO comparisonLookup) {
        String ASTERIK = "*";
        String PERCENT = "%";
        String andOperator = StringUtils.EMPTY;
        List inputList = getComparisonInput(comparisonLookup);
        StringBuilder query = CommonQueryUtils.getSqlQuery(inputList, "comparisonSearchCount");
        query.append(" where ");
        if (comparisonLookup.getContract() != null && !comparisonLookup.getContract().isEmpty()) {
            query.append(" CONTRACT LIKE ").append("'").append(comparisonLookup.getContract().replace(ASTERIK, PERCENT)).append("'");
            andOperator = " AND ";
        } else {
            query.append(" CONTRACT LIKE ").append("'%'");
            andOperator = " AND ";
        }
        if (comparisonLookup.getMarketType() != null && !comparisonLookup.getMarketType().isEmpty()) {
            query.append(andOperator).append("  MARKET_TYPE LIKE ").append("'").append(comparisonLookup.getMarketType().replace(ASTERIK, PERCENT)).append("'");
            andOperator = " AND ";
        } else {
            query.append(andOperator).append("  MARKET_TYPE LIKE ").append("'%'");
            andOperator = " AND ";

        }
        if (comparisonLookup.getCustomer() != null && !comparisonLookup.getCustomer().isEmpty()) {
            query.append(andOperator).append("  CONTRACT_HOLDER LIKE ").append("'").append(comparisonLookup.getCustomer().replace(ASTERIK, PERCENT)).append("'");
            andOperator = " AND ";
        } else {
            query.append(andOperator).append("  CONTRACT_HOLDER LIKE ").append("'%'");
            andOperator = " AND ";
        }
        if (comparisonLookup.getNdcName() != null && !comparisonLookup.getNdcName().isEmpty()) {
            query.append(andOperator).append("  ITEM_NAME LIKE ").append("'").append(comparisonLookup.getNdcName().replace(ASTERIK, PERCENT)).append("'");
            andOperator = " AND ";
        } else {
            query.append(andOperator).append("  ITEM_NAME LIKE ").append("'%'");
            andOperator = " AND ";
        }
        if (comparisonLookup.getNdcNo() != null && !comparisonLookup.getNdcNo().isEmpty()) {
            query.append(andOperator).append("  ITEM_NO LIKE ").append("'").append(comparisonLookup.getNdcNo().replace(ASTERIK, PERCENT)).append("'");
            andOperator = " AND ";
        } else {
            query.append(andOperator).append("  ITEM_NO LIKE ").append("'%'");
            andOperator = " AND ";
        }
        if (comparisonLookup.getBrand() != null && !comparisonLookup.getBrand().isEmpty()) {
            query.append(andOperator).append("  BRAND LIKE ").append("'").append(comparisonLookup.getBrand().replace(ASTERIK, PERCENT)).append("'");
            andOperator = " AND ";
        } else {
            query.append(andOperator).append("  BRAND LIKE ").append("'%'");
            andOperator = " AND ";
        }

        List result = (List) commonDao.executeSelectQuery(query.toString(), null, null);
        return CFFLogic.getCount(result);
    }

    public List<ProjectionVarianceDTO> getProjVariance(PVSelectionDTO selectionDTO, Object parentId, int start, int offset) {
        LOGGER.debug("Inside getProjVariance");
        List<ProjectionVarianceDTO> resultList;
        ProjectionVarianceDTO parentDto = null;
        selectionDTO.setIsLevel(false);
        selectionDTO.setStartPeriod(selectionDTO.getProjectionStartPeriod());
        selectionDTO.setEndPeriod(selectionDTO.getForecastEndPeriod());
        selectionDTO.setStartYear(selectionDTO.getProjectionStartYear());
        selectionDTO.setEndYear(selectionDTO.getForecastDTO().getForecastEndYear());
        if (!selectionDTO.isIslevelFiler() || (parentId instanceof ProjectionVarianceDTO)) {
            selectionDTO.setYear(ALL.getConstant());
            if (selectionDTO.getLevel().equals(TOTAL.getConstant())) {
                selectionDTO.setIsLevel(true);
                parentDto = new ProjectionVarianceDTO();
            }
            if (parentId instanceof ProjectionVarianceDTO) {
                parentDto = (ProjectionVarianceDTO) parentId;
                selectionDTO.setLevelNo(parentDto.getLevelNo());
                selectionDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                selectionDTO.setLevelValue(parentDto.getLevelValue());
                selectionDTO.setParentNode(parentDto.getParentNode());
                selectionDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (parentDto.getHierarchyIndicator().equals(C)) {
                    selectionDTO.setCustomerHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                selectionDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                selectionDTO.setGroup(parentDto.getGroup());
                selectionDTO.setCcpIds(parentDto.getCcpIds());
            } else {
                if (selectionDTO.isIsCustomHierarchy()) {
                    selectionDTO.setLevelNo(0);
                    selectionDTO.setTreeLevelNo(0);
                } else if (C.equals(selectionDTO.getHierarchyIndicator())) {
                    selectionDTO.setLevelNo(selectionDTO.getCustomerLevelNo() - 1);
                    selectionDTO.setTreeLevelNo(selectionDTO.getCustomerLevelNo() - 1);
                } else if (P.equals(selectionDTO.getHierarchyIndicator())) {
                    selectionDTO.setLevelNo(selectionDTO.getProductLevelNo() - 1);
                    selectionDTO.setTreeLevelNo(selectionDTO.getProductLevelNo() - 1);
                }
                selectionDTO.setGroup(StringUtils.EMPTY);
                selectionDTO.setHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            if (selectionDTO.getPivotView().equals("Period")) {
                resultList = getPeriodResults(selectionDTO, start, offset, parentDto);
            } else {
                resultList = getPivotResults(parentId, selectionDTO, start, offset);
            }
        } else {
            selectionDTO.setLevelNo(selectionDTO.getFilterLevelNo());
            selectionDTO.setTreeLevelNo(selectionDTO.getFilterLevelNo());
            int maxRecord = 0;
            maxRecord = - 1;
            resultList = configureLevels(start, offset, selectionDTO, maxRecord);
        }
        return resultList;

    }

    public int getProjVarianceCount(PVSelectionDTO selectionDTO, Object parentId, boolean isLevelsCount) {
        int count = 0;
        ProjectionVarianceDTO parentDto = null;
        selectionDTO.setIsLevel(false);
        if (!selectionDTO.isIslevelFiler() || (parentId instanceof ProjectionVarianceDTO)) {
            selectionDTO.setYear(ALL.getConstant());
            if (selectionDTO.getLevel().equals(TOTAL.getConstant())) {
                selectionDTO.setIsLevel(true);
                parentDto = new ProjectionVarianceDTO();
            }
            if (parentId instanceof ProjectionVarianceDTO) {
                parentDto = (ProjectionVarianceDTO) parentId;
                selectionDTO.setLevelNo(parentDto.getLevelNo());
                selectionDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                selectionDTO.setLevelValue(parentDto.getLevelValue());
                selectionDTO.setParentNode(parentDto.getParentNode());
                selectionDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (parentDto.getHierarchyIndicator().equals(C)) {
                    selectionDTO.setCustomerHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                selectionDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                selectionDTO.setGroup(parentDto.getGroup());
                selectionDTO.setCcpIds(parentDto.getCcpIds());
            } else {
                if (selectionDTO.isIsCustomHierarchy()) {
                    selectionDTO.setLevelNo(0);
                    selectionDTO.setTreeLevelNo(0);
                } else if (C.equals(selectionDTO.getHierarchyIndicator())) {
                    selectionDTO.setLevelNo(selectionDTO.getCustomerLevelNo() - 1);
                    selectionDTO.setTreeLevelNo(selectionDTO.getCustomerLevelNo() - 1);
                } else if (P.equals(selectionDTO.getHierarchyIndicator())) {
                    selectionDTO.setLevelNo(selectionDTO.getProductLevelNo() - 1);
                    selectionDTO.setTreeLevelNo(selectionDTO.getProductLevelNo() - 1);
                }
                selectionDTO.setGroup(StringUtils.EMPTY);
                selectionDTO.setHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            if (selectionDTO.getPivotView().equals("Period")) {
                count += getPeriodResultsCount(selectionDTO, parentDto, isLevelsCount);
            } else {
                count += getPivotResultsCount(parentId, selectionDTO, isLevelsCount);
            }
        } else if (isLevelsCount) {
            selectionDTO.setLevelNo(selectionDTO.getFilterLevelNo());
            selectionDTO.setTreeLevelNo(selectionDTO.getFilterLevelNo());
            System.out.println("selectionDTO.getFilterLevelNo()=======" + selectionDTO.getFilterLevelNo());
            count += configureLevelsCount(selectionDTO);
        }
        return count;
    }

    public int getPeriodResultsCount(PVSelectionDTO pVSelectionDTO, ProjectionVarianceDTO parentDto, boolean isLevelsCount) {
        int count = 0;
        boolean isDiscountExpand = false;
        pVSelectionDTO.setGroupCount(false);
        if (parentDto != null) {
            if (parentDto.getGroup().contains("Discount $") || parentDto.getGroup().contains("Discount %") || parentDto.getGroup().contains("RPU")) {
                pVSelectionDTO.setGroup(parentDto.getGroup());
                isDiscountExpand = true;
            }
            if (!isDiscountExpand) {
                count += getCustPeriodVarianceCount(pVSelectionDTO);
            } else {
                count += getDiscountCount(pVSelectionDTO);
            }
        }
        if (!isDiscountExpand && !pVSelectionDTO.isIslevelFiler() && !pVSelectionDTO.isIsLevel() && isLevelsCount) {
                    pVSelectionDTO.setTreeLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                    pVSelectionDTO.setLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                    int ct = configureLevelsCount(pVSelectionDTO);
                    count += ct;
                    pVSelectionDTO.setLevelCount(ct);
                }
        return count;
    }

    public List<ProjectionVarianceDTO> getPeriodResults(PVSelectionDTO pVSelectionDTO, int start, int offset, ProjectionVarianceDTO parentDto) {
        LOGGER.debug("Inside getPeriodResults");
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<>();
        List< ProjectionVarianceDTO> tobeAddedList = new ArrayList<>();
        int neededRecord = offset;
        int started = start;
        int maxRecord = 0;
        boolean isDiscountExpand = false;
        pVSelectionDTO.setDiscountFlag(false);
        if (!pVSelectionDTO.getDiscountLevel().equalsIgnoreCase(TOTAL_DISCOUNT.getConstant())) {
            pVSelectionDTO.setDiscountFlag(true);
        }
        if (parentDto != null) {
            if (parentDto.getGroup().contains("Discount $") || parentDto.getGroup().contains("Discount %") || parentDto.getGroup().contains("RPU") || (parentDto.getGroup().contains("Discount % of Ex-Factory"))) {
                pVSelectionDTO.setGroup(parentDto.getGroup());
                isDiscountExpand = true;
                if (parentDto.getGroup().contains("RPU")) {
                    pVSelectionDTO.setRPU(true);
                }
            }
            if (pVSelectionDTO.getPivotView().equals(Constants.PERIOD)) {
                if (isDiscountExpand) {
                    maxRecord += getDiscountCount(pVSelectionDTO);
                } else {
                    maxRecord += getCustPeriodVarianceCount(pVSelectionDTO);
                }
            } else {
                maxRecord = pVSelectionDTO.getPeriodList().size() + 1;
            }
            if (started < maxRecord) {
                if (pivotTotalList.isEmpty() && !pVSelectionDTO.getLevel().equals(DETAIL)) {
                    getResultsFromProcedure(pVSelectionDTO, Boolean.FALSE);
                }
                if (isDiscountExpand) {
                    List<Object> list = new ArrayList<>();
                    list.addAll(pivotDiscountList.isEmpty() ? geDiscountResultsFromPrc(pVSelectionDTO) : pivotDiscountList);
                    pVSelectionDTO.setIsTotal(false);
                    List<ProjectionVarianceDTO> resultsDto = new ArrayList<>();
                     if (parentDto.getGroup().contains("Discount % of Ex-Factory")) {
                        if (parentDto.getGroup().contains("Value")) {
                            pVSelectionDTO.setVarIndicator(VALUE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.TEN, NumericConstants.EIGHT, Boolean.TRUE));
                        } else if (parentDto.getGroup().contains("Variance")) {
                            pVSelectionDTO.setVarIndicator(VARIANCE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.TEN, NumericConstants.EIGHT, Boolean.TRUE));
                        } else if (parentDto.getGroup().contains("%Change")) {
                            pVSelectionDTO.setVarIndicator(CHANGE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.TEN, NumericConstants.EIGHT, Boolean.TRUE));
                        }
                    } else if (parentDto.getGroup().contains("Discount $")) {
                        if (parentDto.getGroup().contains("Value")) {
                            pVSelectionDTO.setVarIndicator(VALUE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.FOUR, NumericConstants.EIGHT, Boolean.FALSE));
                        } else if (parentDto.getGroup().contains("Variance")) {
                            pVSelectionDTO.setVarIndicator(VARIANCE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.FOUR, NumericConstants.EIGHT, Boolean.FALSE));
                        } else if (parentDto.getGroup().contains("%Change")) {
                            pVSelectionDTO.setVarIndicator(CHANGE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.FOUR, NumericConstants.EIGHT, Boolean.FALSE));
                        }
                    } else if (parentDto.getGroup().contains("Discount %")) {
                        if (parentDto.getGroup().contains("Value")) {
                            pVSelectionDTO.setVarIndicator(VALUE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.SIX, NumericConstants.EIGHT, Boolean.TRUE));
                        } else if (parentDto.getGroup().contains("Variance")) {
                            pVSelectionDTO.setVarIndicator(VARIANCE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.SIX, NumericConstants.EIGHT, Boolean.TRUE));
                        } else if (parentDto.getGroup().contains("%Change")) {
                            pVSelectionDTO.setVarIndicator(CHANGE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.SIX, NumericConstants.EIGHT, Boolean.TRUE));
                        }
                    } else if (parentDto.getGroup().contains("RPU")) {
                        if (parentDto.getGroup().contains("Value")) {
                            pVSelectionDTO.setVarIndicator(VALUE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.EIGHT, NumericConstants.EIGHT, Boolean.FALSE));
                        } else if (parentDto.getGroup().contains("Variance")) {
                            pVSelectionDTO.setVarIndicator(VARIANCE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.EIGHT, NumericConstants.EIGHT, Boolean.FALSE));
                        } else if (parentDto.getGroup().contains("%Change")) {
                            pVSelectionDTO.setVarIndicator(CHANGE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.EIGHT, NumericConstants.EIGHT, Boolean.FALSE));
                        }
                    } 
                    tobeAddedList.addAll(resultsDto);
                } else {
                    if (pVSelectionDTO.getLevel().equals(DETAIL)) {
                        getResultsFromProcedure(pVSelectionDTO, Boolean.TRUE);
                    }
                    List<ProjectionVarianceDTO> allList = getCustPeriodVariance(pivotTotalList, pivotTotalList, pVSelectionDTO, parentDto);
                    tobeAddedList.addAll(allList);
                }
                setChartList(tobeAddedList);
                for (int i = started; (i < tobeAddedList.size()) && (neededRecord > 0); i++) {
                    projDTOList.add(tobeAddedList.get(i));
                    neededRecord--;

                }
                pivotDiscountList.clear();
            }
        }
        if (!isDiscountExpand && !pVSelectionDTO.isIslevelFiler() && !pVSelectionDTO.isIsLevel() && neededRecord > 0) {
            pVSelectionDTO.setTreeLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
            pVSelectionDTO.setLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
            List<ProjectionVarianceDTO> nextLevelList = configureLevels(start, neededRecord, pVSelectionDTO, maxRecord);
            projDTOList.addAll(nextLevelList);
        }
        return projDTOList;

    }

    /**
     * Discount procedure
     *
     * @param projSelDTO
     */
    List<Object[]> geDiscountResultsFromPrc(PVSelectionDTO projSelDTO) {
        String frequency = projSelDTO.getFrequency();
        List<String> projectionIdList = new ArrayList<String>();
        pivotDiscountList = new ArrayList<Object>();
        if (frequency.equals("Quarterly")) {
            frequency = "QUARTERLY";
        } else if (frequency.equals("Semi-Annually")) {
            frequency = "SEMI-ANNUAL";
        } else if (frequency.equals("Monthly")) {
            frequency = "MONTHLY";
        } else {
            frequency = "ANNUAL";
        }
        projectionIdList.add(String.valueOf(selectionDTO.getCurrentProjId()));
        for (Integer projId : projSelDTO.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        String ccps = null;
        if (projSelDTO.getLevel().equals(DETAIL)) {
            ccps = getCCPIds(projSelDTO);
        }
        Object[] orderedArg = {projectionId, frequency, "VARIANCE", null, null, ccps, projSelDTO.getDiscountLevel(), "EXCEL"};
        List<Object[]> discountsList = CommonLogic.callProcedure("Prc_cff_projection_results_discount", orderedArg);
        pivotDiscountList.addAll(discountsList);
        return discountsList;
    }

    /**
     * get Customized period variance
     *
     * @return List
     */
    public int getCustPeriodVarianceCount(final PVSelectionDTO baseVariables) {
        try {
            int count = 0;
            if (!baseVariables.getLevel().equals(DETAIL)) {
                count++;
            }
            // Ex Factory
            if (baseVariables.isVarExFacSales()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }// Demand Sales
            if (baseVariables.isVarDemandSales()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }// Inventory
            if (baseVariables.isVarInvSales()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //Percentage Of Ex Factory
            if (baseVariables.isVarPerExFacSales()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //Percentage Of Demand Sales
            if (baseVariables.isVarPerDemandSales()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //Percentage Of Inventory
            if (baseVariables.isVarPerInvSales()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //Contract Sales
            if (baseVariables.isVarContractsales()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //Contract Units
            if (baseVariables.isVarContractUnits()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //Discount $ 
            if (baseVariables.isVarDisAmount()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //Discount % 
            if (baseVariables.isVarDisRate()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //RPU
            if (baseVariables.isVarRPU()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //Net Sales
            if (baseVariables.isVarNetSales()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //COGC
            if (baseVariables.isVarCOGC()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //Net PRofit
            if (baseVariables.isVarNetProfit()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //Ex Fac Customer
            if (baseVariables.isVarExFacCustomer()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }

            //Ex Fac Per Customer
            if (baseVariables.isVarPerExFacCustomer()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }

            //Adjusted demand
            if (baseVariables.isVarAdjDemand()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }

            //Per Adjusted demand
            if (baseVariables.isVarPerAdjDemand()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }

            //iw Details
            if (baseVariables.isVarIwDetails()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }

            //per iw Details
            if (baseVariables.isVarPerAdjDemand()) {
                if (baseVariables.isColValue()) {
                    count++;
                }
                if (baseVariables.isColVariance()) {
                    count++;
                }
                if (baseVariables.isColPercentage()) {
                    count++;
                }
            }
            //Net Sales % of Ex-Factory
        if (baseVariables.isNetSalesExFactory()) {
            if (baseVariables.isColValue()) {
                count++;
            }
            if (baseVariables.isColVariance()) {
                count++;
            }
            if (baseVariables.isColPercentage()) {
                count++;
            }
        }
        //DiscountPerExFactory
        if (baseVariables.isDiscountPerExFactory()) {
            if (baseVariables.isColValue()) {
                count++;
            }
            if (baseVariables.isColVariance()) {
                count++;
            }
            if (baseVariables.isColPercentage()) {
                count++;
            }
        }
            return count;

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return 0;

    }

    /**
     * Count for Expanding Discount Level
     *
     * @param cffMasterSid
     * @return count - integer
     */
    private int getDiscountCount(PVSelectionDTO dto) {
        List input = new ArrayList<>();
        input.add(dto.getCurrentProjId());
        input.add(dto.getCcpIds());
        if (dto.getCcpIds() == null || dto.getCcpIds().isEmpty()) {
            input.add(StringUtils.EMPTY);
            input.add(StringUtils.EMPTY);
        } else {
            String inQuery = " AND PD.CCP_DETAILS_SID IN (SELECT TOKEN FROM UDF_SPLITSTRING(@CCP_IDS,','))";
            input.add(inQuery);
            input.add(inQuery);
        }
        List<Object[]> list = CommonQueryUtils.getAppData(input, "getCffDiscountExpandCount",
                dto.getDiscountLevel().equals(PROGRAM_CATEGORY.toString())
                ? "getCffDiscountExpandCount_PROGRAM_CATTEGORY" : "getCffDiscountExpandCount_PROGRAM");
        int count = CFFLogic.getCount(list);
        return count;
    }

    public int configureLevelsCount(PVSelectionDTO selection) {
        CommonLogic commonLogic = new CommonLogic();
        int count;
        if (selection.isIsCustomHierarchy()) {
            count = commonLogic.getCountForCustomView(selection);
        } else {
            count = commonLogic.getCount(selection.getSessionDTO(), selection.getHierarchyNo(), selection.getTreeLevelNo(), selection.getHierarchyIndicator());
        }
        return count;
    }

    /**
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     */
    public List<ProjectionVarianceDTO> configureLevels(int start, int offset, PVSelectionDTO projSelDTO, int maxRecord) {

        CommonLogic commonLogic = new CommonLogic();
        List<ProjectionVarianceDTO> resultList = new ArrayList<>();
        int resultStart = start;
        if (maxRecord == -1) {
            resultStart = start;
        } else {
            resultStart = (start <= maxRecord) ? 0 : start - maxRecord;
        }
        if (projSelDTO.isIsCustomHierarchy()) {

            String hierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
            Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
            List<String> hierarchyNoList = commonLogic.getHiearchyNoForCustomView(projSelDTO, resultStart, offset);
            for (String hierarchyNo : hierarchyNoList) {
                resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, hierarchyIndicator, projSelDTO.getTreeLevelNo(), relationshipLevelDetailsMap.get(hierarchyNo)));
            }

        } else {
            Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();

            List<String> hierarchyNoList = commonLogic.getHiearchyNoAsList(projSelDTO, resultStart, offset);
            for (String hierarchyNo : hierarchyNoList) {
                resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, projSelDTO.getHierarchyIndicator(), Integer.valueOf(relationshipLevelDetailsMap.get(hierarchyNo).get(NumericConstants.TWO).toString()), relationshipLevelDetailsMap.get(hierarchyNo)));
            }
        }

        return resultList;
    }

    /**
     *
     * @param projSelDTO
     * @param hierarchyNo
     * @param detailsList
     * @return
     */
    public ProjectionVarianceDTO configureDetailsInDTO(ProjectionSelectionDTO projSelDTO, String hierarchyNo, String hierarchyIndicator, int levelNo, List detailsList) {
        ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
        dto.setLevelNo(Integer.valueOf(detailsList.get(NumericConstants.TWO).toString()));
        dto.setTreeLevelNo(levelNo);
        dto.setGroup(detailsList.get(0).toString());
        dto.setLevelValue(detailsList.get(0).toString());
        dto.setHierarchyNo(hierarchyNo);
        dto.setHierarchyIndicator(hierarchyIndicator);
        if (dto.getHierarchyIndicator().equals("C")) {
            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
            dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        } else if (dto.getHierarchyIndicator().equals("P")) {
            dto.setProductHierarchyNo(dto.getHierarchyNo());
            dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        }
        dto.setParent(1);
        return dto;
    }

    public Leveldto getNextLevel(int levelNo, List<Leveldto> hierarchy, boolean isCustomHierarchy) {
        for (Leveldto dto : hierarchy) {
            if (isCustomHierarchy && dto.getTreeLevelNo() == levelNo) {
                return dto;
            } else if (dto.getLevelNo() == levelNo) {
                return dto;
            }
        }

        return null;
    }

    public int getPivotResultsCount(Object parent, PVSelectionDTO pvsdto, boolean isLevelsCount) {
        int count = 0;
        boolean isChild = false;
        if (parent instanceof ProjectionVarianceDTO) {
            isChild = true;
        }
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            count += pvsdto.getPeriodList().size() + 1;
        } else if (isChild) {
            count += pvsdto.getPeriodList().size();
        }

        if (!pvsdto.isIsLevel() && isLevelsCount) {
            pvsdto.setTreeLevelNo(pvsdto.getTreeLevelNo() + 1);
            pvsdto.setLevelNo(pvsdto.getTreeLevelNo() + 1);
            int ct = configureLevelsCount(pvsdto);
            count += ct;
            pvsdto.setLevelCount(ct);
        }
        return count;

    }

    public List<ProjectionVarianceDTO> getPivotResults(Object parent, PVSelectionDTO pvsdto, int start, int offset) {
        int neededRecord = offset;
        int started = start;
        int maxRecord = pvsdto.getPeriodList().size();

        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        if (started < maxRecord || pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                getResultsFromProcedure(pvsdto, Boolean.FALSE);
                List<Object> currentPivotDiscount = new ArrayList<Object>();
                if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                    currentPivotDiscount.addAll(pivotDiscountList.isEmpty() ? geDiscountResultsFromPrc(pvsdto) : pivotDiscountList);
                }
                List<ProjectionVarianceDTO> finalList = getCustomizedPivotTotalResults(pivotTotalList, pivotPriorProjIdList, pvsdto, pivotDiscountList);
                for (int i = started; (i < finalList.size()) && (neededRecord > 0); i++) {
                    projDTOList.add(finalList.get(i));
                    neededRecord--;
                }
            } else if (parent instanceof ProjectionVarianceDTO) {
                getResultsFromProcedure(pvsdto, Boolean.TRUE);
                geDiscountResultsFromPrc(pvsdto);
                List<ProjectionVarianceDTO> dto = getCustomizedPivotTotalResults(pivotTotalList, pivotPriorProjIdList, pvsdto, pivotDiscountList);
                for (int i = started; (i < dto.size()) && (neededRecord > 0); i++) {
                    projDTOList.add(dto.get(i));
                    neededRecord--;
                }
            }
        }
        setChartList(projDTOList);
        if (!pvsdto.isIsLevel() && neededRecord > 0 && !pvsdto.getLevel().equals(TOTAL.getConstant())) {
            pvsdto.setTreeLevelNo(pvsdto.getTreeLevelNo() + 1);
            pvsdto.setLevelNo(pvsdto.getTreeLevelNo() + 1);
            List<ProjectionVarianceDTO> resultList = configureLevels(start, neededRecord, pvsdto, maxRecord);
            for (int i = 0; i < resultList.size(); i++) {
                projDTOList.addAll(resultList);
            }
        }
        return projDTOList;
    }

    public List<ProjectionVarianceDTO> getDetailsPivotVariance(final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto) {
        List<Object> currentPivotDiscount = new ArrayList<Object>();
        pvsdto.setYear("ALL");
        pvsdto.setProjectionId(selectionDTO.getCurrentProjId());
        String ccpQuery = CommonLogic.getCCPQuery(pvsdto) + "\n" + CommonLogic.getTempCCPQueryForCOGS(pvsdto) + "\n" + CommonLogic.getTemp_CCPD_RetrunsQuery() + "\n" + CommonLogic.getTempRetrunsQuery();
        String query = ccpQuery + "\n" + queryUtils.getProjectionResultsMainPivotQuery(pvsdto);
        List<Object> currentPivotDetails = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
            String discountQuery = ccpQuery + "\n" + queryUtils.getPVMainDiscountPivotQuery(pvsdto);
            currentPivotDiscount = (List<Object>) CommonLogic.executeSelectQuery(discountQuery, null, null);
        }
        List<Integer> priorProjIdList = new ArrayList<Integer>();
        for (Integer projId : pvsdto.getProjIdList()) {
            priorProjIdList.add(projId);
        }
        List<ProjectionVarianceDTO> projDTOList = getCustomizedPivotDetailResults(pivotTotalList, currentPivotDetails, currentPivotDiscount, pvsdto.getDiscountNameList(), parentDto, priorProjIdList, pvsdto);
        return projDTOList;
    }

    public void getResultsFromProcedure(PVSelectionDTO pvsdto, final boolean isDetail) {
        String frequency = pvsdto.getFrequency();
        List<String> projectionIdList = new ArrayList<String>();
        pivotTotalList = new ArrayList<Object>();
        pivotPriorProjIdList = new ArrayList<Integer>();
        if ("Quarterly".equals(frequency)) {
            frequency = "QUARTERLY";
        } else if ("Semi-Annually".equals(frequency)) {
            frequency = "SEMI-ANNUAL";
        } else if ("Monthly".equals(frequency)) {
            frequency = "MONTHLY";
        } else {
            frequency = "ANNUAL";
        }
        projectionIdList.add(String.valueOf(selectionDTO.getCurrentProjId()));
        for (Integer projId : pvsdto.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
            pivotPriorProjIdList.add(projId);
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        String ccps = null;
        if (isDetail) {
            ccps = getCCPIds(pvsdto);
            pvsdto.setCcpIds(ccps);
        }
        Object[] orderedArg = {projectionId, frequency, "VARIANCE", null, null, ccps};

        List< Object[]> gtsResult = CommonLogic.callProcedure(PRC_CFF_RESULTS, orderedArg);
        pivotTotalList.addAll(gtsResult);
    }

    /**
     * FFS GTS Procedure Call
     *
     * @param projectionId
     * @param procedureName
     * @return
     */
    public List<Object[]> getGrossTradeSales(int projectionId, String procedureName, String frequency, String sessionId, String userId, String discountId) {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        ResultSet rs = null;
        if (frequency.equals("Quarterly")) {
            frequency = "QUARTERLY";
        } else if (frequency.equals("Semi-Annually")) {
            frequency = "SEMI_ANNUAL";
        } else if (frequency.equals("Monthly")) {
            frequency = "MONTHLY";
        } else {
            frequency = "ANNUAL";
        }
        List<Object[]> objectList = new ArrayList<Object[]>();
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                statement = connection.prepareCall("{call " + procedureName + "(?,?,?,?,?)}");
                statement.setInt(1, projectionId);
                statement.setString(NumericConstants.TWO, frequency);
                statement.setString(NumericConstants.THREE, discountId);
                statement.setInt(NumericConstants.FOUR, Integer.valueOf(sessionId));
                statement.setInt(NumericConstants.FIVE, Integer.valueOf(userId));
                rs = statement.executeQuery();

                objectList = convertResultSetToList(rs);

            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
            try {
                System.gc();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return objectList;
    }

    public List<ProjectionVarianceDTO> getConfiguredProjectionVariance(Object parentId, PVSelectionDTO projSelDTO, int start, int offset) {
        try {
            LOGGER.debug("Inside getConfiguredProjectionVariance");
            List<ProjectionVarianceDTO> list;
            list = getProjVariance(projSelDTO, parentId, start, offset);
            LOGGER.debug("list size in getConfiguredProjectionVariance " + list.size());
            LOGGER.debug("Ending getConfiguredProjectionVariance");
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return null;

    }

    public int getConfiguredProjectionVarianceCount(Object parentId, PVSelectionDTO projSelDTO, boolean isLevelCount) {
        LOGGER.debug("In getConfiguredProjectionVarianceCount");
        int count = 0;
        setSelectionDTO(projSelDTO);
        setRightHeader(projSelDTO.getRightHeader());
        count += getProjVarianceCount(projSelDTO, parentId, isLevelCount);
        return count;

    }

    /**
     * To convert the given Result Set into List of Objects
     *
     * @param rs
     * @return
     */
    private List<Object[]> convertResultSetToList(ResultSet rs) {
        List<Object[]> objList = new ArrayList<Object[]>();

        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            Object[] header = new Object[columnCount];
            for (int i = 1; i <= columnCount; ++i) {
                Object label = rsMetaData.getColumnLabel(i);
                header[i - 1] = label;
            }
            while (rs.next()) {
                Object[] str = new Object[columnCount];
                for (int i = 1; i <= columnCount; ++i) {
                    Object obj = rs.getObject(i);
                    str[i - 1] = obj;
                }
                objList.add(str);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        }
        return objList;
    }

    public List<List> getDiscountExpandQuery(PVSelectionDTO projSelDTO) {
        LOGGER.debug("Inside getDiscountExpandQuery");
        List<List> projDTOList = new ArrayList<List>();
        List<Object> list0;
        String query = StringUtils.EMPTY;
        String ccpQuery = CommonLogic.getCCPQuery(projSelDTO);
        if (projSelDTO.isIsTotal()) {
            query = ccpQuery + queryUtils.getProjectionResultsTotalDiscountPerQuery(projSelDTO);
            list0 = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
            projDTOList.add(list0);
        } else {
            query = ccpQuery + CommonLogic.getTempCCPQueryForCOGS(projSelDTO) + "\n" + CommonLogic.getTemp_CCPD_RetrunsQuery()
                    + " \n" + CommonLogic.getTempRetrunsQuery() + " \n" + queryUtils.getPeriodDiscountExpand(projSelDTO);
            list0 = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
            projDTOList.add(list0);
        }
        LOGGER.debug("Ending getDiscountExpandQuery");
        return projDTOList;
    }

    public List<ProjectionVarianceDTO> getCustomizedPivotTotalResults(final List<Object> results, List<Integer> priorProjGtsList, PVSelectionDTO pvsdto, List totalDiscount) {
        List<String> periodList = new ArrayList<>(pvsdto.getPeriodList());
        Map<String, String> periodListMap = new HashMap<>(pvsdto.getPeriodListMap());
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<>();
        String[] singleColumn = new String[NumericConstants.THREE];
        int frequencyDivision = pvsdto.getFrequencyDivision();
        boolean actualBasis = "Actuals".equalsIgnoreCase(pvsdto.getComparisonBasis());
        int prPos = NumericConstants.SIXTY;
        if (results != null && !results.isEmpty()) {
            for (int i = 0; i < results.size(); i++) {
                final Object[] row = (Object[]) results.get(i);
                int year = Integer.valueOf(String.valueOf(row[0]));
                int period = row[1] != null ? Integer.valueOf(String.valueOf(row[1])) : 0;
                List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, year, period);
                String pcommonColumn = common.get(0);
                String commonHeader = common.get(1);
                String commonDoubleColumn = StringUtils.EMPTY;
                String column = StringUtils.EMPTY;
                String columnAct = StringUtils.EMPTY;
                String currentValue = StringUtils.EMPTY;
                String actualValue = StringUtils.EMPTY;
//                String column = "";
                pcommonColumn = getCommonColumn(frequencyDivision, pcommonColumn);
                if (periodList.contains(pcommonColumn)) {
                    periodList.remove(pcommonColumn);
                    List<String> columnList = new ArrayList<>(pvsdto.getColumns());
                    columnList.remove("group");
                    ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                    projDTO.setGroup(commonHeader);
                    String variableValue = StringUtils.EMPTY;
                    //Exfactory Sales
                    if ((pvsdto.isVarExFacSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWO];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THREE];
                        if (pvsdto.isColValue()) {
                            customizePivot("ExFacValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("ExFacVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("ExFacPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                   //CustExFacSales
                   if ((pvsdto.isVarExFacCustomer())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FORTY_EIGHT];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_NINE];
                        if (pvsdto.isColValue()) {
                            customizePivot("CustExFacValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("CustExFacVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("CustExFacPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                   //Demand Sales
                   if ((pvsdto.isVarDemandSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_EIGHT];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_NINE];
                        if (pvsdto.isColValue()) {
                            customizePivot("DemandSalesValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("DemandSalesVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("DemandSalesPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                    //AdjDemandSales
                     if ((pvsdto.isVarAdjDemand())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FORTY_SIX];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_SEVEN];
                        if (pvsdto.isColValue()) {
                            customizePivot("AdjDemandValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("AdjDemandVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("AdjDemandPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                   //InvWithSummary
                   if ((pvsdto.isVarInvSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.THIRTY];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_ONE];
                        if (pvsdto.isColValue()) {
                            customizePivot("InvWithValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("InvWithVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("InvWithPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                   //InvWithDetails
                   if ((pvsdto.isVarIwDetails())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FIFTY];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_ONE];
                        if (pvsdto.isColValue()) {
                            customizePivot("InvWithDetailsValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("InvWithDetailsVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("InvWithDetailsPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                   //PerExFac
                   if ((pvsdto.isVarPerExFacSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWELVE];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTEEN];
                        if (pvsdto.isColValue()) {
                            customizePivot("PerExFacValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("PerExFacVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("PerExFacPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                   //PerCustExFac
                   if ((pvsdto.isVarPerExFacCustomer())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_FOUR];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_FIVE];
                        if (pvsdto.isColValue()) {
                            customizePivot("PerCustExFacValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("PerCustExFacVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("PerCustExFacPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                   //PerDemandSales
                    if ((pvsdto.isVarPerDemandSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_TWO];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_THREE];
                        if (pvsdto.isColValue()) {
                            customizePivot("PerDemandSalesValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("PerDemandSalesVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("PerDemandSalesPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                   //PerAdjDemand
                   if ((pvsdto.isVarPerAdjDemand())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_TWO];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_THREE];
                        if (pvsdto.isColValue()) {
                            customizePivot("PerAdjDemandValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("PerAdjDemandSalesVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("PerAdjDemandSalesPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                    //PerInvWith
                     if ((pvsdto.isVarPerInvSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_FOUR];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_FIVE];
                        if (pvsdto.isColValue()) {
                            customizePivot("PerInvWithValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("PerInvWithVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("PerInvWithPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                   //PerInvWithDetails
                   if ((pvsdto.isVarPerIwDetails())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_SIX];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_SEVEN];
                        if (pvsdto.isColValue()) {
                            customizePivot("PerInvWithDetailsValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("PerInvWithDetailsVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("PerInvWithDetailsPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                    //ContractSalesWAC
                     if ((pvsdto.isVarContractsales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FOUR];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FIVE];
                        if (pvsdto.isColValue()) {
                            customizePivot("ContractSalesWACValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("ContractSalesWACVar", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("ContractSalesWACVarPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                  //ContractUnits
                  if ((pvsdto.isVarContractUnits())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.SIX];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.SEVEN];
                        if (pvsdto.isColValue()) {
                            customizePivot("ContractUnitsValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT_UNITS);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("ContractUnitsVar", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT_UNITS);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("ContractUnitsPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                    //DiscountSales
                     if ((pvsdto.isVarDisRate())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TEN];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.ELEVEN];
                        if (pvsdto.isColValue()) {
                            customizePivot("DiscountSalesValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("DiscountSalesVar", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("DiscountSalesPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                    //DiscountAmount
                     if ((pvsdto.isVarDisAmount())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.EIGHT];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.NINE];
                        if (pvsdto.isColValue()) {
                            customizePivot("DiscountAmountValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("DiscountAmountVar", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("DiscountAmountPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                   //RPU
                     if ((pvsdto.isVarRPU())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FORTY];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_ONE];
                        if (pvsdto.isColValue()) {
                            customizePivot("RPUValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("RPUVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("RPUPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                     //DiscountPerExFactory
                     if ((pvsdto.isDiscountPerExFactory())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.SIXTY];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.SIXTY_ONE];
                        if (pvsdto.isColValue()) {
                            customizePivot("DiscountPerExFactoryValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("DiscountPerExFactoryVar", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("DiscountPerExFactoryPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                   //NetSales
                     if ((pvsdto.isVarNetSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_SIX];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_SEVEN];
                        if (pvsdto.isColValue()) {
                            customizePivot("NetSalesValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("NetSalesVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("NetSalesPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                     //Net Sales ExFactory Percentage
                     if ((pvsdto.isNetSalesExFactory())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_EIGHT];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_NINE];
                        if (pvsdto.isColValue()) {
                            customizePivot("NetSalesExFactoryValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("NetSalesExFactoryVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("NetSalesExFactoryPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                    //COGC
                     if ((pvsdto.isVarCOGC())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FORTY_TWO];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_THREE];
                        if (pvsdto.isColValue()) {
                            customizePivot("COGCValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("COGCVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("COGCPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                    //NetProfit
                     if ((pvsdto.isVarNetProfit())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FORTY_FOUR];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_FIVE];
                        if (pvsdto.isColValue()) {
                            customizePivot("NetProfitValue", Constants.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("NetProfitVariance", Constants.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList,AMOUNT);
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("NetProfitPer", Constants.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList,RATE);
                        }
                    }
                    for (int j = 0; j < priorProjGtsList.size(); j++) {
                        String actualValueCurrent;
                        //ExFac Product
                        if (pvsdto.isVarExFacSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWO];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THREE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THREE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "ExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "ExFacVariance" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "ExFacPer" + priorProjGtsList.get(j);
                                 if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        //ExFac Customer
                        if (pvsdto.isVarExFacCustomer()) {
                             actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FORTY_EIGHT];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_NINE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FORTY_NINE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "CustExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "CustExFacVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "CustExFacPer" + priorProjGtsList.get(j);
                               if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        //Demand
                        if (pvsdto.isVarDemandSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWENTY_EIGHT];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_NINE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_NINE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "DemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "DemandSalesVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "DemandSalesPer" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        // Adjusted Demand
                        if (pvsdto.isVarAdjDemand()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FORTY_SIX];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_SEVEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FORTY_SEVEN + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "AdjDemandValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "AdjDemandVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "AdjDemandPer" + priorProjGtsList.get(j);
                                 if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        //Inventory Summary
                        if (pvsdto.isVarInvSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.THIRTY];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_ONE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_ONE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "InvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "InvWithVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "InvWithPer" + priorProjGtsList.get(j);
                                 if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        //Inventory Detials 
                        if (pvsdto.isVarIwDetails()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FIFTY];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_ONE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_ONE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "InvWithDetailsValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "InvWithDetailsVariance" + priorProjGtsList.get(j);
                               if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "InvWithDetailsPer" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        //Percentage of Ex Fac Product
                        if (pvsdto.isVarPerExFacSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWELVE];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTEEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THIRTEEN + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "PerExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerExFacVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val+ PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val+ PERCENT);
                                        columnList.remove(column);
                                    }
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerExFacPer" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        //Percentage of Ex Fac customer
                        if (pvsdto.isVarPerExFacCustomer()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FIFTY_FOUR];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_FIVE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_FIVE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "PerCustExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerCustExFacVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerCustExFacPer" + priorProjGtsList.get(j);
                               if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        //Percentage of Demand
                        if (pvsdto.isVarPerDemandSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.THIRTY_TWO];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_THREE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_THREE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "PerDemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerDemandSalesVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerDemandSalesPer" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        //Percentage of AdjDemand
                        if (pvsdto.isVarPerAdjDemand()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FIFTY_TWO];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_THREE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_THREE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "PerAdjDemandValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerAdjDemandSalesVariance" + priorProjGtsList.get(j);
                                 if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerAdjDemandSalesPer" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        //Percentage of Inv Summary
                        if (pvsdto.isVarPerInvSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.THIRTY_FOUR];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_FIVE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_FIVE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "PerInvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerInvWithVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String val = getVariance(currentValue, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerInvWithPer" + priorProjGtsList.get(j);
                                 if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        //Percentage of Inv Details
                        if (pvsdto.isVarPerIwDetails()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FIFTY_SIX];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_SEVEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_SEVEN + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "PerInvWithDetailsValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerInvWithDetailsVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String val = getVariance(currentValue, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerInvWithDetailsPer" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        //Contract Sales
                        if (pvsdto.isVarContractsales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FOUR];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FIVE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FIVE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "ContractSalesWACValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "ContractSalesWACVar" + priorProjGtsList.get(j);
                                    if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String val = getVariance(currentValue, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "ContractSalesWACVarPer" + priorProjGtsList.get(j);
                                   if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                        }
                        }
                        //Contract Units
                        if (pvsdto.isVarContractUnits()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.SIX];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.SEVEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.SEVEN + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "ContractUnitsValue" + priorProjGtsList.get(j);
                                if (pvsdto.hasColumn(column)) {
                                    String baseValue = getFormattedValue(AMOUNT_UNITS, priorValue);
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "ContractUnitsVar" + priorProjGtsList.get(j);
                                    if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT_UNITS);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String val = getVariance(currentValue, priorValue, AMOUNT_UNITS);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "ContractUnitsPer" + priorProjGtsList.get(j);
                                   if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }

                        //Discount Percentage
                        if (pvsdto.isVarDisRate()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TEN];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.ELEVEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.ELEVEN + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "DiscountSalesValue" + priorProjGtsList.get(j);
                                if (pvsdto.hasColumn(column)) {
                                    String baseValue = getFormattedValue(RATE_PER, priorValue);
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "DiscountSalesVar" + priorProjGtsList.get(j);
                              
                                    if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String val = getVariance(currentValue, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "DiscountSalesPer" + priorProjGtsList.get(j);
                              
                                     if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        //Discount Dollar
                        if (pvsdto.isVarDisAmount()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.EIGHT];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.NINE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.NINE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "DiscountAmountValue" + priorProjGtsList.get(j);
                                if (pvsdto.hasColumn(column)) {
                                    String baseValue = getFormattedValue(AMOUNT, priorValue);
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "DiscountAmountVar" + priorProjGtsList.get(j);
                             
                                    if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String val = getVariance(currentValue, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "DiscountAmountPer" + priorProjGtsList.get(j);
                              
                                    if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String baseValu = getPerChange(currentValue, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                        }
                        //RPU
                        if (pvsdto.isVarRPU()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FORTY];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_ONE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FORTY_ONE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "RPUValue" + priorProjGtsList.get(j);
                                if (pvsdto.hasColumn(column)) {
                                    String baseValue = getFormattedValue(AMOUNT, priorValue);
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "RPUVariance" + priorProjGtsList.get(j);
                              
                                    if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String val = getVariance(currentValue, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "RPUPer" + priorProjGtsList.get(j);
                            
                                    if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String baseValu = getPerChange(currentValue, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                        }
                        //Netsales
                        if (pvsdto.isVarNetSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWENTY_SIX];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_SEVEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_SEVEN + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "NetSalesValue" + priorProjGtsList.get(j);
                                if (pvsdto.hasColumn(column)) {
                                    String baseValue = getFormattedValue(AMOUNT, priorValue);
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "NetSalesVariance" + priorProjGtsList.get(j);
                             
                                    if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String val = getVariance(currentValue, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "NetSalesPer" + priorProjGtsList.get(j);
                                    if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String baseValu = getPerChange(currentValue, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                        }
                        //COGC
                        if (pvsdto.isVarCOGC()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FORTY_TWO];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_THREE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FORTY_THREE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "COGCValue" + priorProjGtsList.get(j);
                                if (pvsdto.hasColumn(column)) {
                                    String baseValue = getFormattedValue(AMOUNT, priorValue);
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "COGCVariance" + priorProjGtsList.get(j);
                                    if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String val = getVariance(currentValue, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "COGCPer" + priorProjGtsList.get(j);
                                    if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String baseValu = getPerChange(currentValue, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                        }
                        //Net Profite
                        if (pvsdto.isVarNetProfit()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FORTY_FOUR];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_FIVE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FORTY_FIVE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "NetProfitValue" + priorProjGtsList.get(j);
                                if (pvsdto.hasColumn(column)) {
                                    String baseValue = getFormattedValue(AMOUNT, priorValue);
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "NetProfitVariance" + priorProjGtsList.get(j);
                                    if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String val = getVariance(currentValue, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "NetProfitPer" + priorProjGtsList.get(j);
                               
                                    if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        
                         //NetSalesExValue
                        if (pvsdto.isNetSalesExFactory()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FIFTY_EIGHT];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_NINE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FIFTY_NINE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "NetSalesExFactoryValue" + priorProjGtsList.get(j);
                                if (pvsdto.hasColumn(column)) {
                                    String baseValue = getFormattedValue(RATE_PER, priorValue);
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "NetSalesExFactoryVariance" + priorProjGtsList.get(j);
                            
                                    if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String val = getVariance(currentValue, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "NetSalesExFactoryPer" + priorProjGtsList.get(j);
                                    if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        
                           //DiscountPerExFactory
                        if (pvsdto.isDiscountPerExFactory()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.SIXTY];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.SIXTY_ONE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.SIXTY_ONE + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "DiscountPerExFactoryValue" + priorProjGtsList.get(j);
                                if (pvsdto.hasColumn(column)) {
                                    String baseValue = getFormattedValue(RATE_PER, priorValue);
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "DiscountPerExFactoryVar" + priorProjGtsList.get(j);
                                    if (actualBasis) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    } else {
                                        String val = getVariance(currentValue, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "DiscountPerExFactoryPer" + priorProjGtsList.get(j);
                                    if (actualBasis) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
                                } else {
                                    String baseValu = getPerChange(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, baseValu + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }
                        }
                        
                    }
                    if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {

                        // Individual discount level
                        Set<String> noOfDiscount = new HashSet<String>();
                        for (Object discountsName : totalDiscount) {
                            final Object[] disc = (Object[]) discountsName;
                            if (disc[NumericConstants.TWO] != null) {
                                noOfDiscount.add(String.valueOf(disc[NumericConstants.TWO]));
                            }
                        }
                        for (int dis = 0; dis < totalDiscount.size(); dis++) {
                            Object[] discountRow = (Object[]) totalDiscount.get(dis);
                            int dyear = Integer.valueOf(String.valueOf(discountRow[0]));
                            int dperiod = Integer.valueOf(String.valueOf(discountRow[1]));
                            List<String> dcommon = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, dyear, dperiod);
                            String dcommonColumn = dcommon.get(0);
                            dcommonColumn = getCommonColumn(dcommonColumn, frequencyDivision);
                            List<String> discountNames = pvsdto.getDiscountLevel().equals("Program") ? pvsdto.getDiscountNameList() : pvsdto.getDiscountNameCFF();
                            if (pcommonColumn.equals(dcommonColumn)) {
                                for (int disc = 0; disc < noOfDiscount.size(); disc++) {
                                    String disName = String.valueOf(discountRow[NumericConstants.TWO]);

                                    String head = disName.replace(" ", StringUtils.EMPTY) + discountNames.indexOf(disName);
                                    
                                    
                                    if ((pvsdto.isVarDisAmount())) {
                                        actualValue = StringUtils.EMPTY + discountRow[NumericConstants.THREE];
                                        currentValue = StringUtils.EMPTY + discountRow[NumericConstants.FOUR];
                                        if ((pvsdto.isColValue())) {
                                            //Actual
                                            columnAct = "DiscountAmountValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                            String baseValueAct = getFormattedValue(AMOUNT, actualValue);
                                                if (pvsdto.hasColumn(columnAct)) {
                                                    projDTO.addStringProperties(columnAct, baseValueAct);
                                                    columnList.remove(columnAct);
                                                }
                                            //Current
                                            column = "DiscountAmountValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            baseValueAct = getFormattedValue(AMOUNT, currentValue);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValueAct);
                                                columnList.remove(column);
                                            }

                                        }
                                        

                                        if (actualBasis) {
                                                if ((pvsdto.isColVariance())) {
                                                    column = "DiscountAmountVar" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //  for CURRENT
                                                    String val = getVariance(actualValue, currentValue, AMOUNT);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val);
                                                        columnList.remove(column);
                                                    }

                                                }
                                                if ((pvsdto.isColPercentage())) {
                                                    column = "DiscountAmountPer" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //for CURRENT
                                                    String val = getPerChange(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }
                                        }
                                    }
                                    if ((pvsdto.isVarDisRate())) {
                                        actualValue = StringUtils.EMPTY + discountRow[NumericConstants.FIVE];
                                        currentValue = StringUtils.EMPTY + discountRow[NumericConstants.SIX];
                                        if ((pvsdto.isColValue())) {
                                            //Actual
                                            columnAct = "DiscountSalesValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                            String baseValueAct = getFormattedValue(RATE, actualValue);
                                                if (pvsdto.hasColumn(columnAct)) {
                                                    projDTO.addStringProperties(columnAct, baseValueAct + PERCENT);
                                                    columnList.remove(columnAct);
                                                }
                                            //Current
                                            column = "DiscountSalesValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            baseValueAct = getFormattedValue(RATE, currentValue);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValueAct + PERCENT);
                                                columnList.remove(column);
                                            }

                                        }

                                        if (actualBasis) {
                                                if ((pvsdto.isColVariance())) {
                                                    column = "DiscountSalesVar" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //  for CURRENT
                                                    String val = getVariance(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }

                                                }
                                                if ((pvsdto.isColPercentage())) {
                                                    column = "DiscountSalesPer" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //for CURRENT
                                                    String val = getPerChange(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }
                                        }
                                    }
                                    
                                    if ((pvsdto.isVarRPU())) {
                                        actualValue = StringUtils.EMPTY + discountRow[NumericConstants.SEVEN];
                                        currentValue = StringUtils.EMPTY + discountRow[NumericConstants.EIGHT];
                                        if ((pvsdto.isColValue())) {
                                            //Actual
                                            columnAct = "RPUValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                            String baseValueAct = getFormattedValue(AMOUNT, actualValue);
                                                if (pvsdto.hasColumn(columnAct)) {
                                                    projDTO.addStringProperties(columnAct, baseValueAct);
                                                    columnList.remove(columnAct);
                                                }
                                            //Current
                                            column = "RPUValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            baseValueAct = getFormattedValue(AMOUNT, currentValue);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValueAct);
                                                columnList.remove(column);
                                            }

                                        }

                                        if (actualBasis) {
                                                if ((pvsdto.isColVariance())) {
                                                    column = "RPUVariance" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //  for CURRENT
                                                    String val = getVariance(actualValue, currentValue, AMOUNT);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val);
                                                        columnList.remove(column);
                                                    }

                                                }
                                                if ((pvsdto.isColPercentage())) {
                                                    column = "RPUPer" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //for CURRENT
                                                    String val = getPerChange(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }
                                        }
                                    }
                                    //discount per of exfactory
                                     if ((pvsdto.isDiscountPerExFactory())) {
                                        actualValue = StringUtils.EMPTY + discountRow[NumericConstants.NINE];
                                        currentValue = StringUtils.EMPTY + discountRow[NumericConstants.TEN];
                                        if ((pvsdto.isColValue())) {
                                            //Actual
                                            columnAct = "DiscountPerExFactoryValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                            String baseValueAct = getFormattedValue(RATE, actualValue);
                                                if (pvsdto.hasColumn(columnAct)) {
                                                    projDTO.addStringProperties(columnAct, baseValueAct + PERCENT);
                                                    columnList.remove(columnAct);
                                                }
                                            //Current
                                            column = "DiscountPerExFactoryValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            baseValueAct = getFormattedValue(RATE, currentValue);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValueAct + PERCENT);
                                                columnList.remove(column);
                                            }

                                        }

                                        if (actualBasis) {
                                                if ((pvsdto.isColVariance())) {
                                                    column = "DiscountPerExFactoryVar" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //  for CURRENT
                                                    String val = getVariance(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }

                                                }
                                                if ((pvsdto.isColPercentage())) {
                                                    column = "DiscountPerExFactoryPer" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //for CURRENT
                                                    String val = getPerChange(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }
                                        }
                                    }
                                    for (int k = 0; k < priorProjGtsList.size(); k++) {
                                        //Discount Percentage
                                        if (pvsdto.isVarDisRate()) {
                                            singleColumn[0] = "DiscountSalesValue" + head + priorProjGtsList.get(k);
                                            singleColumn[1] = "DiscountSalesVar" + head + priorProjGtsList.get(k);
                                            singleColumn[NumericConstants.TWO] = "DiscountSalesPer" + head + priorProjGtsList.get(k);
                                            getPivotCommonCustomization(pvsdto, discountRow, projDTO, singleColumn, NumericConstants.SIX, k, NumericConstants.EIGHT, columnList, Boolean.TRUE);
                                        }

                                        //Discount Dollor
                                        if (pvsdto.isVarDisAmount()) {
                                            singleColumn[0] = "DiscountAmountValue" + head + priorProjGtsList.get(k);
                                            singleColumn[1] = "DiscountAmountVar" + head + priorProjGtsList.get(k);
                                            singleColumn[NumericConstants.TWO] = "DiscountAmountPer" + head + priorProjGtsList.get(k);
                                            getPivotCommonCustomization(pvsdto, discountRow, projDTO, singleColumn, NumericConstants.FOUR, k, NumericConstants.EIGHT, columnList, Boolean.FALSE);
                                        }
                                        //RPU
                                        if (pvsdto.isVarRPU()) {
                                            singleColumn[0] = "RPUValue" + head + priorProjGtsList.get(k);
                                            singleColumn[1] = "RPUVariance" + head + priorProjGtsList.get(k);
                                            singleColumn[NumericConstants.TWO] = "RPUPer" + head + priorProjGtsList.get(k);
                                            getPivotCommonCustomization(pvsdto, discountRow, projDTO, singleColumn, NumericConstants.EIGHT, k, NumericConstants.EIGHT, columnList, Boolean.FALSE);
                                        }
                                        //Discount Per Exfactory
                                        if (pvsdto.isDiscountPerExFactory()) {
                                            singleColumn[0] = "DiscountPerExFactoryValue" + head + priorProjGtsList.get(k);
                                            singleColumn[1] = "DiscountPerExFactoryVar" + head + priorProjGtsList.get(k);
                                            singleColumn[NumericConstants.TWO] = "DiscountPerExFactoryPer" + head + priorProjGtsList.get(k);
                                            getPivotCommonCustomization(pvsdto, discountRow, projDTO, singleColumn, NumericConstants.TEN, k, NumericConstants.EIGHT, columnList, Boolean.TRUE);
                                        }
                                    }
                                }
                            }
                        }

                        // PPA Total Discount $
                        if (pvsdto.isColValue()) {
                            column = "DiscountAmountValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                            String baseValue = getCellValue(row[NumericConstants.EIGHT], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // PPA Total Discount %
                        if (pvsdto.isColValue()) {
                            column = "DiscountSalesValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                            String baseValue = getCellValue(row[NumericConstants.NINE], "Rate");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Mandated Total Discount %
                        if (pvsdto.isColValue()) {
                            column = "DiscountSalesValueMANDATED" + CURRENT + pvsdto.getCurrentProjId();
                            String baseValue = getCellValue(row[NumericConstants.TWELVE], "Rate");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Supplemental Total Discount %
                        if (pvsdto.isColValue()) {
                            column = "DiscountSalesValueSUPPLEMENTAL" + CURRENT + pvsdto.getCurrentProjId();
                            String baseValue = getCellValue(row[NumericConstants.THIRTEEN], "Rate");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                            }
                        }
                        // PPA Total Discount RPU
                        if (pvsdto.isColValue()) {
                            column = "RPUValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                            String baseValue = getCellValue(row[NumericConstants.TWENTY_ONE], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Returns Total Discount $
                        if (pvsdto.isColValue()) {
                            column = "DiscountAmountValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                            String baseValue = getCellValue(row[NumericConstants.FIFTEEN], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Returns Total Discount %
                        if (pvsdto.isColValue()) {
                            column = "DiscountSalesValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                            String baseValue = getCellValue(row[NumericConstants.TWENTY_FIVE], "Rate");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Returns Total Discount RPU
                        if (pvsdto.isColValue()) {
                            column = "RPUValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                            String baseValue = getCellValue(row[NumericConstants.TWENTY_SIX], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Mandated Total Discount $
                        if (pvsdto.isColValue()) {
                            column = "DiscountAmountValueMANDATED" + CURRENT + pvsdto.getCurrentProjId();
                            String baseValue = getCellValue(row[NumericConstants.TEN], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                if (baseValue.equals(StringUtils.EMPTY)) {
                                    projDTO.addStringProperties(column, baseValue);
                                } else {
                                    projDTO.addStringProperties(column, baseValue);
                                }
                                columnList.remove(column);
                            }
                        }
                        // Supplemental Total Discount $
                        if (pvsdto.isColValue()) {
                            column = "DiscountAmountValueSUPPLEMENTAL" + CURRENT + pvsdto.getCurrentProjId();
                            String baseValue = getCellValue(row[NumericConstants.ELEVEN], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                if (baseValue.equals(StringUtils.EMPTY)) {
                                    projDTO.addStringProperties(column, baseValue);
                                } else {
                                    projDTO.addStringProperties(column, baseValue);
                                }
                                columnList.remove(column);
                            }
                        }
                        // MANDATED Total Discount $
                        if (pvsdto.isColValue()) {
                            column = "RPUValueMANDATED" + CURRENT + pvsdto.getCurrentProjId();
                            String baseValue = getCellValue(row[NumericConstants.NINE], "Rate");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Supplemental Total Discount $
                        if (pvsdto.isColValue()) {
                            column = "RPUValueSUPPLEMENTAL" + CURRENT + pvsdto.getCurrentProjId();
                            String baseValue = getCellValue(row[NumericConstants.NINE], "Rate");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        for (int j = 0; j < priorProjGtsList.size(); j++) {
                            //Discount Percentage
                            if (pvsdto.isVarDisRate()) {
                                //PPA
                                singleColumn[0] = "DiscountSalesValuePPA" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountSalesVarPPA" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "DiscountSalesPerPPA" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.EIGHT, j, prPos, columnList, Boolean.TRUE);
                                //Returns
                                singleColumn[0] = "DiscountSalesValueReturns" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountSalesVarReturns" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "DiscountSalesPerReturns" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.TWENTY_FIVE, j, prPos, columnList, Boolean.TRUE);
                                //Mandated
                                singleColumn[0] = "DiscountSalesValueMANDATED" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountSalesVarMANDATED" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "DiscountSalesPerMANDATED" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.TWELVE, j, prPos, columnList, Boolean.TRUE);
                                //Supplemental
                                singleColumn[0] = "DiscountSalesValueSUPPLEMENTAL" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountSalesVarSUPPLEMENTAL" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "DiscountSalesPerSUPPLEMENTAL" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.THIRTEEN, j, prPos, columnList, Boolean.TRUE);
                            }
                            //Discount Dollar
                            if (pvsdto.isVarDisAmount()) {
                                //PPA
                                singleColumn[0] = "DiscountAmountValuePPA" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountAmountVarPPA" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "DiscountAmountPerPPA" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.NINE, j, prPos, columnList, Boolean.FALSE);
                                //Returns
                                singleColumn[0] = "DiscountAmountValueReturns" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountAmountVarReturns" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "DiscountAmountPerReturns" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.FIFTEEN, j, prPos, columnList, Boolean.FALSE);
                                //Mandated
                                singleColumn[0] = "DiscountAmountValueMANDATED" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountAmountVarMANDATED" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "DiscountAmountPerMANDATED" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.TEN, j, prPos, columnList, Boolean.FALSE);
                                //Supplemental
                                singleColumn[0] = "DiscountAmountValueSUPPLEMENTAL" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountAmountVarSUPPLEMENTAL" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "DiscountAmountPerSUPPLEMENTAL" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.ELEVEN, j, prPos, columnList, Boolean.FALSE);
                            }
                            //RPU
                            if (pvsdto.isVarRPU()) {
                                //PPA
                                singleColumn[0] = "RPUValuePPA" + priorProjGtsList.get(j);
                                singleColumn[1] = "RPUVariancePPA" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "RPUPerPPA" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.NINETEEN, j, prPos, columnList, Boolean.FALSE);
                                //Returns
                                singleColumn[0] = "RPUValueReturns" + priorProjGtsList.get(j);
                                singleColumn[1] = "RPUVarianceReturns" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "RPUPerReturns" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.TWENTY_SIX, j, prPos, columnList, Boolean.FALSE);
                                //Mandated
                                singleColumn[0] = "RPUValueMANDATED" + priorProjGtsList.get(j);
                                singleColumn[1] = "RPUVarianceMANDATED" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "RPUPerMANDATED" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.NINETEEN, j, prPos, columnList, Boolean.FALSE);
                                //Supplemental
                                singleColumn[0] = "RPUValueSUPPLEMENTAL" + priorProjGtsList.get(j);
                                singleColumn[1] = "RPUVarianceSUPPLEMENTAL" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "RPUPerSUPPLEMENTAL" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.TWENTY_FIVE, j, prPos, columnList, Boolean.FALSE);

                            }
                        }
                    }
                    projDTOList.add(projDTO);
                }
            }
        }
        List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
        columnList.remove("group");
        boolean leftFlag = false;
        for (String ob : periodList) {
            leftFlag = true;
            ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
            projDTO.setParent(0);
            projDTO.setGroup(periodListMap.get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(AMOUNT, "null"));
            }
            projDTOList.add(projDTO);
        }
        if (pvsdto.getProjectionOrder().equals(ASCENDING.getConstant())) {
            if (leftFlag) {
                Collections.sort(projDTOList, new ProjectionVarianceDTO());
            }
        } else {
            if (leftFlag) {
                Collections.sort(projDTOList, new ProjectionVarianceDTO());
            }
            Collections.reverse(projDTOList);
        }

        if (!pvsdto.getLevel().equals(DETAIL)) {
            ProjectionVarianceDTO totalDTO = new ProjectionVarianceDTO();
            totalDTO.setGroup("CFF Total");
            projDTOList.add(0, totalDTO);

        }
        return projDTOList;
    }

    /**
     * Pivot common customization applicable only for prior projections
     *
     * @param pvsdto
     * @param row
     * @param projDTO
     * @param columns
     * @param index
     * @param priorIndex
     * @param column
     * @param columnList
     * @param isPer
     */
    private void getPivotCommonCustomization(PVSelectionDTO pvsdto, final Object[] row, ProjectionVarianceDTO projDTO, String[] columns, int index, int priorIndex, int column, List columnList, final Boolean isPer) {
        String visibleColumn = "";
        boolean actualBasis = "Actuals".equalsIgnoreCase(pvsdto.getComparisonBasis());
        if (pvsdto.isColValue()) {
            if (row[index + ((priorIndex + 1) * column)] == null) {
                visibleColumn = columns[0];
                if (pvsdto.hasColumn(visibleColumn)) {
                    String value = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                    projDTO.addStringProperties(visibleColumn, isPer ? value + PERCENT : value);
                    columnList.remove(visibleColumn);
                }
            } else {
                String priorVal = StringUtils.EMPTY + row[index + ((priorIndex + 1) * column)];
                visibleColumn = columns[0];
                String baseValue;
                if (isPer) {
                    baseValue = getFormattedValue(RATE, priorVal);
                } else {
                    baseValue = getFormattedValue(AMOUNT, priorVal);
                }
                if (pvsdto.hasColumn(visibleColumn)) {
                    projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                    columnList.remove(visibleColumn);
                }
            }
        }
        if (pvsdto.isColVariance()) {
            visibleColumn = columns[1];
            if (row[index + ((priorIndex + 1) * column)] == null && row[index] == null) {
                if (pvsdto.hasColumn(visibleColumn)) {
                    String value = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                    projDTO.addStringProperties(visibleColumn, isPer ? value + PERCENT : value);
                    columnList.remove(visibleColumn);
                }
            } else {
                String priorVal = StringUtils.EMPTY + row[index + ((priorIndex + 1) * column)];
                if (actualBasis) {
                    String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index - 1])) - Double.valueOf(isNull(priorVal)));
                    String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, variance);
                    if (pvsdto.hasColumn(visibleColumn)) {
                        projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                        columnList.remove(visibleColumn);
                    }
                } else {
                    String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index])) - Double.valueOf(isNull(priorVal)));
                    String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, variance);
                    if (pvsdto.hasColumn(visibleColumn)) {
                        projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                        columnList.remove(visibleColumn);
                    }
            }
            }
        }
        if (pvsdto.isColPercentage()) {
            visibleColumn = columns[NumericConstants.TWO];
            if (row[index + ((priorIndex + 1) * column)] == null && row[index] == null) {
                if (pvsdto.hasColumn(visibleColumn)) {
                    String value = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                    projDTO.addStringProperties(visibleColumn, isPer ? value + PERCENT : value);
                    columnList.remove(visibleColumn);
                }
            } else {
                String priorVal = StringUtils.EMPTY + row[index + ((priorIndex + 1) * column)];
                if (actualBasis) {
                    String actValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index - 1])));
                    String baseValue = getPerChange(actValue, priorVal, RATE);
                    if (pvsdto.hasColumn(visibleColumn)) {
                        projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                        columnList.remove(visibleColumn);
                    }
                } else {
                    String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index])));
                    String baseValue = getPerChange(currentValue, priorVal, RATE);
                    if (pvsdto.hasColumn(visibleColumn)) {
                        projDTO.addStringProperties(visibleColumn, baseValue + PERCENT);
                        columnList.remove(visibleColumn);
                    }
                }
            }
        }
    }

    public List<ProjectionVarianceDTO> getCustomizedPivotDetailResults(final List gtsList, final List results, List discountList, List<String> discountName, ProjectionVarianceDTO parentDto, List<Integer> priorProjGtsList, PVSelectionDTO pvsdto) {
        List<String> periodList = new ArrayList<String>(pvsdto.getPeriodList());
        Map<String, String> periodListMap = new HashMap<String, String>(pvsdto.getPeriodListMap());
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        boolean isGts = false;
        int frequencyDivision = pvsdto.getFrequencyDivision();
        if (results != null && !results.isEmpty() && !gtsList.isEmpty()) {
            for (int i = 0; i < results.size(); i++) {
                final Object[] row = (Object[]) results.get(i);
                final Object[] gtsRow = (Object[]) gtsList.get(i);
                int year = Integer.valueOf(String.valueOf(row[0]));
                int period = row[1] != null ? Integer.valueOf(String.valueOf(row[1])) : 0;
                List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, year, period);
                String pcommonColumn = common.get(0);
                String commonHeader = common.get(1);
                String column = "";
                if (gtsList.size() >= results.size() && gtsRow.length >= row.length) {
                    isGts = true;
                }
                if (periodList.contains(pcommonColumn)) {
                    periodList.remove(pcommonColumn);
                    List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
                    columnList.remove("group");
                    ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                    projDTO.setGroup(commonHeader);
                    if (pvsdto.isVarExFacSales() && pvsdto.isColValue()) {
                        column = "ExFacValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentGts = NULL.getConstant();
                        if (isGts) {
                            currentGts = String.valueOf(gtsRow[NumericConstants.TWO]);
                        }
                        String baseValue = getFormattedValue(AMOUNT, currentGts);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarDemandSales() && pvsdto.isColValue()) {
                        column = "DemandSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentGts = NULL.getConstant();
                        if (isGts) {
                            currentGts = String.valueOf(gtsRow[NumericConstants.THREE]);
                        }
                        String baseValue = getFormattedValue(AMOUNT, currentGts);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarInvSales() && pvsdto.isColValue()) {
                        column = "InvWithValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentGts = NULL.getConstant();
                        if (isGts) {
                            currentGts = String.valueOf(gtsRow[NumericConstants.FOUR]);
                        }
                        String baseValue = getFormattedValue(AMOUNT, currentGts);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarPerExFacSales() && pvsdto.isColValue()) {
                        column = "PerExFacValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentPob = NULL.getConstant();
                        if (isGts) {
                            currentPob = String.valueOf(gtsRow[NumericConstants.FIVE]);
                        }
                        String baseValue = getFormattedValue(RATE, currentPob);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarPerDemandSales() && pvsdto.isColValue()) {
                        column = "PerDemandSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentPob = NULL.getConstant();
                        if (isGts) {
                            currentPob = String.valueOf(gtsRow[NumericConstants.SIX]);
                        }
                        String baseValue = getFormattedValue(RATE, currentPob);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarPerInvSales() && pvsdto.isColValue()) {
                        column = "PerInvWithValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentPob = NULL.getConstant();
                        if (isGts) {
                            currentPob = String.valueOf(gtsRow[NumericConstants.SEVEN]);
                        }
                        String baseValue = getFormattedValue(RATE, currentPob);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarContractsales() && pvsdto.isColValue()) {
                        column = "ContractSalesWACValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentSales = StringUtils.EMPTY + row[NumericConstants.TWO];
                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarContractUnits() && pvsdto.isColValue()) {
                        column = "ContractUnitsValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentSales = StringUtils.EMPTY + row[NumericConstants.THREE];
                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarDisRate() && pvsdto.isColValue()) {
                        column = "DiscountSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentSales = StringUtils.EMPTY + row[NumericConstants.FOUR];
                        String baseValue = getFormattedValue(RATE, currentSales);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue + PERCENT);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarDisAmount() && pvsdto.isColValue()) {
                        column = "DiscountAmountValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentSales = StringUtils.EMPTY + row[NumericConstants.FIVE];
                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarRPU() && pvsdto.isColValue()) {
                        column = "RPUValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentSales = StringUtils.EMPTY + row[NumericConstants.SEVEN];
                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarNetSales() && pvsdto.isColValue()) {
                        column = "NetSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentSales = StringUtils.EMPTY + row[NumericConstants.SIX];
                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarCOGC() && pvsdto.isColValue()) {
                        column = "COGCValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentSales = StringUtils.EMPTY + row[NumericConstants.EIGHT];
                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    if (pvsdto.isVarNetProfit() && pvsdto.isColValue()) {
                        column = "NetProfitValue" + CURRENT + pvsdto.getCurrentProjId();
                        String currentSales = StringUtils.EMPTY + row[NumericConstants.NINE];
                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, baseValue);
                            columnList.remove(column);
                        }
                    }
                    for (int j = 0; j < priorProjGtsList.size(); j++) {
                        //Ex Fact
                        if (pvsdto.isVarExFacSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[NumericConstants.TWO + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                            }
                            if (pvsdto.isColValue()) {
                                column = "ExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "ExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[NumericConstants.TWO + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[NumericConstants.TWO])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "ExFacPer" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[NumericConstants.TWO + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                                }
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[NumericConstants.TWO])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                        } //Demand Sales
                        if (pvsdto.isVarDemandSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[NumericConstants.THREE + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                            }
                            if (pvsdto.isColValue()) {
                                column = "DemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "DemandSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[NumericConstants.THREE + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[NumericConstants.THREE])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "DemandSalesPer" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[NumericConstants.THREE + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                                }
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[NumericConstants.THREE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                        } //Inv Sales
                        if (pvsdto.isVarInvSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[NumericConstants.FOUR + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                            }
                            if (pvsdto.isColValue()) {
                                column = "InvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "InvWithVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[NumericConstants.FOUR + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[NumericConstants.FOUR])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "InvWithPer" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[NumericConstants.FOUR + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                                }
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[NumericConstants.FOUR])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Percentage of Ex Factory Sales
                        if (pvsdto.isVarPerExFacSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[NumericConstants.FIVE + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                            }
                            if (pvsdto.isColValue()) {
                                column = "PerExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[NumericConstants.FIVE + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[NumericConstants.FIVE])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerExFacPer" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[NumericConstants.FIVE + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                                }
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[NumericConstants.FIVE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Percentage of Ex Demad Sales
                        if (pvsdto.isVarPerDemandSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[NumericConstants.SIX + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                            }
                            if (pvsdto.isColValue()) {
                                column = "PerDemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerDemandSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[NumericConstants.SIX + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[NumericConstants.SIX])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerDemandSalesPer" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[NumericConstants.SIX + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                                }
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[NumericConstants.SIX])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Percentage of Inv
                        if (pvsdto.isVarPerInvSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[NumericConstants.SEVEN + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                            }
                            if (pvsdto.isColValue()) {
                                column = "PerInvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerInvWithVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[NumericConstants.SEVEN + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[NumericConstants.SEVEN])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerInvWithPer" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[NumericConstants.SEVEN + ((j + 1) * NumericConstants.TWENTY_ONE)]);
                                }
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[NumericConstants.SEVEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Contract Sales
                        if (pvsdto.isVarContractsales()) {
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.TWO + ((j + 1) * NumericConstants.EIGHT)];
                            if (pvsdto.isColValue()) {
                                column = "ContractSalesWACValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "ContractSalesWACVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.TWO + ((j + 1) * NumericConstants.EIGHT)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.TWO])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "ContractSalesWACVarPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.TWO + ((j + 1) * NumericConstants.EIGHT)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.TWO])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                        }

                        //Contract Units
                        if (pvsdto.isVarContractUnits()) {
                            String value1 = StringUtils.EMPTY + row[NumericConstants.THREE + ((j + 1) * NumericConstants.EIGHT)];
                            if (pvsdto.isColValue()) {
                                column = "ContractUnitsValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "ContractUnitsVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.THREE + ((j + 1) * NumericConstants.EIGHT)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.THREE])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "ContractUnitsPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.THREE + ((j + 1) * NumericConstants.EIGHT)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.THREE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                        }

                        //Discount Dollor
                        if (pvsdto.isVarDisRate()) {
                            String value1 = StringUtils.EMPTY + row[NumericConstants.FOUR + ((j + 1) * NumericConstants.EIGHT)];
                            if (pvsdto.isColValue()) {
                                column = "DiscountSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "DiscountSalesVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.FOUR + ((j + 1) * NumericConstants.EIGHT)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.FOUR])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "DiscountSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.FOUR + ((j + 1) * NumericConstants.EIGHT)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.FOUR])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Discount percentage
                        if (pvsdto.isVarDisAmount()) {
                            String value1 = StringUtils.EMPTY + row[NumericConstants.FIVE + ((j + 1) * NumericConstants.EIGHT)];
                            if (pvsdto.isColValue()) {
                                column = "DiscountAmountValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "DiscountAmountVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.FIVE + ((j + 1) * NumericConstants.EIGHT)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.FIVE])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "DiscountAmountPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.FIVE + ((j + 1) * NumericConstants.EIGHT)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.EIGHT])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        } //RPU
                        if (pvsdto.isVarRPU()) {
                            String value1 = StringUtils.EMPTY + row[NumericConstants.SEVEN + ((j + 1) * NumericConstants.EIGHT)];
                            if (pvsdto.isColValue()) {
                                column = "RPUValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "RPUVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.SEVEN + ((j + 1) * NumericConstants.EIGHT)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.SEVEN])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "RPUPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.SEVEN + ((j + 1) * NumericConstants.EIGHT)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.SEVEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Netsales
                        if (pvsdto.isVarNetSales()) {
                            String value1 = StringUtils.EMPTY + row[NumericConstants.SIX + ((j + 1) * NumericConstants.EIGHT)];
                            if (pvsdto.isColValue()) {
                                column = "NetSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "NetSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.SIX + ((j + 1) * NumericConstants.EIGHT)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.SIX])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "NetSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.SIX + ((j + 1) * NumericConstants.EIGHT)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.SIX])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                        } //COGC
                        if (pvsdto.isVarCOGC()) {
                            String value1 = StringUtils.EMPTY + row[NumericConstants.EIGHT + ((j + 1) * NumericConstants.EIGHT)];
                            if (pvsdto.isColValue()) {
                                column = "COGCValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "COGCVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.EIGHT + ((j + 1) * NumericConstants.EIGHT)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.EIGHT])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "COGCPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.EIGHT + ((j + 1) * NumericConstants.EIGHT)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.EIGHT])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                        } //Net Profit
                        if (pvsdto.isVarNetProfit()) {
                            String value1 = StringUtils.EMPTY + row[NumericConstants.NINE + ((j + 1) * NumericConstants.EIGHT)];
                            if (pvsdto.isColValue()) {
                                column = "NetProfitValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "NetProfitVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.NINE + ((j + 1) * NumericConstants.EIGHT)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.NINE])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "NetProfitPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[NumericConstants.NINE + ((j + 1) * NumericConstants.EIGHT)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[NumericConstants.NINE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                        }
                    }
                    if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        Set<String> noOfDiscount = new HashSet<String>();
                        for (Object discountsName : discountList) {
                            final Object[] disc = (Object[]) discountsName;
                            if (disc[NumericConstants.TWO] != null) {
                                noOfDiscount.add(String.valueOf(disc[NumericConstants.TWO]));
                            }
                        }
                        for (int j = 0; j < discountList.size(); j++) {
                            Object[] discountRow = (Object[]) discountList.get(j);
                            int dyear = Integer.valueOf(String.valueOf(discountRow[0]));
                            int dperiod = Integer.valueOf(String.valueOf(discountRow[1]));
                            List<String> dcommon = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, dyear, dperiod);
                            String dcommonColumn = dcommon.get(0);
                            if (pcommonColumn.equals(dcommonColumn)) {
                                for (int disc = 0; disc < noOfDiscount.size(); disc++) {
                                    String head = String.valueOf(discountRow[NumericConstants.TWO]).replace(" ", StringUtils.EMPTY) + disc;
                                    if (pvsdto.isVarDisAmount() && pvsdto.isColValue()) {
                                        column = "DiscountAmountValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                        String currentSales = StringUtils.EMPTY + discountRow[NumericConstants.THREE];
                                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue);
                                            columnList.remove(column);
                                        }
                                    }
                                    if (pvsdto.isVarDisRate() && pvsdto.isColValue()) {
                                        column = "DiscountSalesValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                        String currentSales = StringUtils.EMPTY + discountRow[NumericConstants.SIX];
                                        String baseValue = getFormattedValue(RATE, currentSales);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                    if (pvsdto.isVarRPU() && pvsdto.isColValue()) {
                                        column = "RPUValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                        String currentSales = StringUtils.EMPTY + discountRow[NumericConstants.NINE];
                                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue);
                                            columnList.remove(column);
                                        }
                                    }

                                    //  Returns Discount $
                                    if (pvsdto.isVarDisAmount() && pvsdto.isColValue()) {
                                        column = "DiscountAmountValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                                        String currentSales = StringUtils.EMPTY + discountRow[NumericConstants.TWELVE];
                                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue);
                                            columnList.remove(column);
                                        }
                                    }
                                    //  PPA Discount $
                                    if (pvsdto.isVarDisAmount() && pvsdto.isColValue()) {
                                        column = "DiscountAmountValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                                        String currentSales = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_ONE];
                                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue);
                                            columnList.remove(column);
                                        }
                                    }
                                    //  Returns Discount %
                                    if (pvsdto.isVarDisRate() && pvsdto.isColValue()) {
                                        column = "DiscountSalesValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                                        String currentSales = StringUtils.EMPTY + discountRow[NumericConstants.FIFTEEN];
                                        String baseValue = getFormattedValue(RATE, currentSales);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                    //  PPA Discount %
                                    if (pvsdto.isVarDisRate() && pvsdto.isColValue()) {
                                        column = "DiscountSalesValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                                        String currentSales = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_FOUR];
                                        String baseValue = getFormattedValue(RATE, currentSales);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                    //  Returns  RPU
                                    if (pvsdto.isVarRPU() && pvsdto.isColValue()) {
                                        column = "RPUValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                                        String currentSales = StringUtils.EMPTY + discountRow[NumericConstants.EIGHTEEN];
                                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue);
                                            columnList.remove(column);
                                        }
                                    }
                                    //  PPA  RPU
                                    if (pvsdto.isVarRPU() && pvsdto.isColValue()) {
                                        column = "RPUValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                                        String currentSales = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_SEVEN];
                                        String baseValue = getFormattedValue(AMOUNT, currentSales);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValue);
                                            columnList.remove(column);
                                        }
                                    }
                                    for (int k = 0; k < priorProjGtsList.size(); k++) {
                                        //Discount Percentage
                                        if (pvsdto.isVarDisRate()) {
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.SIX + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                            if (pvsdto.isColValue()) {
                                                column = "DiscountSalesValue" + head + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(RATE, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "DiscountSalesVar" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.SEVEN + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "DiscountSalesPer" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.EIGHT + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }

                                        // Returns Discount % 
                                        if (pvsdto.isVarDisRate()) {
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.FIFTEEN + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                            if (pvsdto.isColValue()) {
                                                column = "DiscountSalesValueReturns" + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(RATE, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "DiscountSalesVarReturns" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.SIXTEEN + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "DiscountSalesPerReturns" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.SEVENTEEN + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }

                                        // PPA Discount % 
                                        if (pvsdto.isVarDisRate()) {
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_FOUR + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                            if (pvsdto.isColValue()) {
                                                column = "DiscountSalesValuePPA" + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(RATE, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "DiscountSalesVarPPA" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_FIVE + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "DiscountSalesPerPPA" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_SIX + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }

                                        //Discount Dollor
                                        if (pvsdto.isVarDisAmount()) {
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.THREE + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                            if (pvsdto.isColValue()) {
                                                column = "DiscountAmountValue" + head + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "DiscountAmountVar" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.FOUR + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "DiscountAmountPer" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.FIVE + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                        // Returns Discount $
                                        if (pvsdto.isVarDisAmount()) {
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.TWELVE + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                            if (pvsdto.isColValue()) {
                                                column = "DiscountAmountValueReturns" + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "DiscountAmountVarReturns" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.THIRTEEN + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "DiscountAmountPerReturns" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.FOURTEEN + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                        // PPA Discount $
                                        if (pvsdto.isVarDisAmount()) {
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_ONE + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                            if (pvsdto.isColValue()) {
                                                column = "DiscountAmountValuePPA" + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "DiscountAmountVarPPA" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_TWO + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "DiscountAmountPerPPA" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_THREE + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                        //RPU
                                        if (pvsdto.isVarRPU()) {
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.NINE + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                            if (pvsdto.isColValue()) {
                                                column = "RPUValue" + head + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "RPUVariance" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TEN + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "RPUPer" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.ELEVEN + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(RATE_PER_THREE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                        // Retruns RPU
                                        if (pvsdto.isVarRPU()) {
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.EIGHTEEN + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                            if (pvsdto.isColValue()) {
                                                column = "RPUValueReturns" + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "RPUVarianceReturns" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.NINETEEN + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "RPUPerReturns" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(RATE_PER_THREE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                        // PPA RPU
                                        if (pvsdto.isVarRPU()) {
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_SEVEN + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                            if (pvsdto.isColValue()) {
                                                column = "RPUValuePPA" + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "RPUVariancePPA" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_EIGHT + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "RPUPerPPA" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_NINE + ((k + 1) * NumericConstants.TWENTY_SEVEN)];
                                                String baseValue = getFormattedValue(RATE_PER_THREE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    projDTOList.add(projDTO);
                }
            }
        }
        List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
        columnList.remove("group");
        boolean leftFlag = false;
        for (String ob : periodList) {
            leftFlag = true;
            ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
            projDTO.setParent(0);
            projDTO.setGroup(periodListMap.get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(AMOUNT, "null"));
            }
            projDTOList.add(projDTO);
        }

        if (pvsdto.getProjectionOrder().equals(Constants.CommonConstantsForChannels.ASCENDING)) {
            if (leftFlag) {
                Collections.sort(projDTOList, new ProjectionVarianceDTO());
            }
        } else {
            if (leftFlag) {
                Collections.sort(projDTOList, new ProjectionVarianceDTO());
            }
            Collections.reverse(projDTOList);
        }
        return projDTOList;
    }

    /**
     * Get ProjectionS results
     *
     * @param pvsdto
     * @param parentDto
     * @return
     */
    public List<Object> getProjVarianceResults(final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto) {
        pvsdto.setYear("ALL");
        pvsdto.setProjectionId(pvsdto.getCurrentProjId());
        String query = CommonLogic.getCCPQuery(pvsdto) + CommonLogic.getTempCCPQueryForCOGS(pvsdto) + "\n" + CommonLogic.getTemp_CCPD_RetrunsQuery()
                + " \n" + CommonLogic.getTempRetrunsQuery() + "\n" + queryUtils.getProjectionResultsMainPivotQuery(pvsdto);
        List<Object> currentPivotDetails = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        pvsdto.setProjectionId(pvsdto.getCurrentProjId());
        return currentPivotDetails;
    }

    /**
     * get Customized period variance
     *
     * @return List
     */
    public List<ProjectionVarianceDTO> getCustPeriodVariance(final List<Object> gtsList, final List<Object> dataList, final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto) {
        boolean actualBasis = ("Actuals").equals(pvsdto.getComparisonBasis());
        List<ProjectionVarianceDTO> projectionVarianceDTO = new ArrayList<ProjectionVarianceDTO>();
        ProjectionVarianceDTO exFacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO exFacPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO demandValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO demandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO demandPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO invWithValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO invWithVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO invWithPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO salesValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO salesVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO salesPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO custExFacValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO custExFacVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO custExFacPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO adjDemandValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO adjDemandVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO adjDemandPer = new ProjectionVarianceDTO();

        ProjectionVarianceDTO iwDetialsValue = new ProjectionVarianceDTO();
        ProjectionVarianceDTO iwDetialsVar = new ProjectionVarianceDTO();
        ProjectionVarianceDTO iwDetialsPer = new ProjectionVarianceDTO();

        boolean isDetail = false;
        if (pvsdto.getLevel().equals(DETAIL)) {
            isDetail = true;
        } else {
            ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
            dto.setGroup("CFF Total");
            projectionVarianceDTO.add(dto);
        }
        // GTS and Sales for POB starts
        if (isDetail) {
            //Ex fac for detail start
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                exFacValue = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, gtsList, NumericConstants.THREE, NumericConstants.SIXTY, NumericConstants.THREE, NumericConstants.SIXTY, pvsdto, AMOUNT, false ,actualBasis);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                exFacVar = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, gtsList, NumericConstants.THREE, NumericConstants.SIXTY, NumericConstants.THREE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                exFacPer = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, gtsList, NumericConstants.THREE, NumericConstants.SIXTY, NumericConstants.THREE, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
            }
            //Ex fac for detail end

            // Customer ExFac Sales for detail start 
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                custExFacValue = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, gtsList, NumericConstants.FORTY_NINE, NumericConstants.SIXTY, NumericConstants.FORTY_NINE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                custExFacVar = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, gtsList, NumericConstants.FORTY_NINE, NumericConstants.SIXTY, NumericConstants.FORTY_NINE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                custExFacPer = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, gtsList, NumericConstants.FORTY_NINE, NumericConstants.SIXTY, NumericConstants.FORTY_NINE, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
            }
            // Customer ExFac Sales for detail end 

            //Demand - start
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                demandValue = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, gtsList, NumericConstants.TWENTY_NINE, NumericConstants.SIXTY, NumericConstants.TWENTY_NINE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                demandVar = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, gtsList, NumericConstants.TWENTY_NINE, NumericConstants.SIXTY, NumericConstants.TWENTY_NINE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                demandPer = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, gtsList, NumericConstants.TWENTY_NINE, NumericConstants.SIXTY, NumericConstants.TWENTY_NINE, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
            }
            //Demand - end

            // Adjusted Demand  start 
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                adjDemandValue = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, gtsList, NumericConstants.FORTY_SEVEN, NumericConstants.SIXTY, NumericConstants.FORTY_SEVEN, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                adjDemandVar = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, gtsList, NumericConstants.FORTY_SEVEN, NumericConstants.SIXTY, NumericConstants.FORTY_SEVEN, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                adjDemandPer = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, gtsList, NumericConstants.FORTY_SEVEN, NumericConstants.SIXTY, NumericConstants.FORTY_SEVEN, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
            }
            // Adjusted Demand end 

            //Inv with drawal sale for detail - start
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                invWithValue = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, gtsList, NumericConstants.THIRTY_ONE, NumericConstants.SIXTY, NumericConstants.THIRTY_ONE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                invWithVar = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, gtsList, NumericConstants.THIRTY_ONE, NumericConstants.SIXTY, NumericConstants.THIRTY_ONE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                invWithPer = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, gtsList, NumericConstants.THIRTY_ONE, NumericConstants.SIXTY, NumericConstants.THIRTY_ONE, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
            }
            //Inv with drawal sale for detail - end

            // Inventary withdrawal Details for detail start 
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                iwDetialsValue = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, gtsList, NumericConstants.FIFTY_ONE, NumericConstants.SIXTY, NumericConstants.FIFTY_ONE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                iwDetialsVar = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, gtsList, NumericConstants.FIFTY_ONE, NumericConstants.SIXTY, NumericConstants.FIFTY_ONE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                iwDetialsPer = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, gtsList, NumericConstants.FIFTY_ONE, NumericConstants.SIXTY, NumericConstants.FIFTY_ONE, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
            }
            // Inventary withdrawal Details for detail end 

            //Sales for POB
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                salesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, NumericConstants.FIVE, NumericConstants.SIXTY, NumericConstants.FIVE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                salesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, NumericConstants.FIVE, NumericConstants.SIXTY, NumericConstants.FIVE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                salesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, NumericConstants.FIVE, NumericConstants.SIXTY, NumericConstants.FIVE, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
            }
        }
        // Total View   

        // ExFac Sales 
        if (pvsdto.isVarExFacSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, dataList, NumericConstants.THREE, NumericConstants.SIXTY, NumericConstants.THREE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, dataList, NumericConstants.THREE, NumericConstants.SIXTY, NumericConstants.THREE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, dataList, NumericConstants.THREE, NumericConstants.SIXTY, NumericConstants.THREE, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacPer);
                }
            }
        }
        // ExFac Customer
        if (pvsdto.isVarExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, NumericConstants.FORTY_NINE, NumericConstants.SIXTY, NumericConstants.FORTY_NINE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(custExFacValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, NumericConstants.FORTY_NINE, NumericConstants.SIXTY, NumericConstants.FORTY_NINE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(custExFacVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, NumericConstants.FORTY_NINE, NumericConstants.SIXTY, NumericConstants.FORTY_NINE, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(custExFacPer);
                }
            }
        }

        // Demand Sales
        if (pvsdto.isVarDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, dataList, NumericConstants.TWENTY_NINE, NumericConstants.SIXTY, NumericConstants.TWENTY_NINE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, dataList, NumericConstants.TWENTY_NINE, NumericConstants.SIXTY, NumericConstants.TWENTY_NINE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, dataList, NumericConstants.TWENTY_NINE, NumericConstants.SIXTY, NumericConstants.TWENTY_NINE, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandPer);
                }
            }
        }
        // Adjusted Demand
        if (pvsdto.isVarAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, dataList, NumericConstants.FORTY_SEVEN, NumericConstants.SIXTY, NumericConstants.FORTY_SEVEN, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(adjDemandValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, dataList, NumericConstants.FORTY_SEVEN, NumericConstants.SIXTY, NumericConstants.FORTY_SEVEN, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(adjDemandVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, dataList, NumericConstants.FORTY_SEVEN, NumericConstants.SIXTY, NumericConstants.FORTY_SEVEN, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(adjDemandPer);
                }
            }
        }
        // Inventory Sales Withdrawal
        if (pvsdto.isVarInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, dataList, NumericConstants.THIRTY_ONE, NumericConstants.SIXTY, NumericConstants.THIRTY_ONE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, dataList, NumericConstants.THIRTY_ONE, NumericConstants.SIXTY, NumericConstants.THIRTY_ONE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, dataList, NumericConstants.THIRTY_ONE, NumericConstants.SIXTY, NumericConstants.THIRTY_ONE, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithPer);
                }
            }
        }
        // iw Details
        if (pvsdto.isVarIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, dataList, NumericConstants.FIFTY_ONE, NumericConstants.SIXTY, NumericConstants.FIFTY_ONE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(iwDetialsValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, dataList, NumericConstants.FIFTY_ONE, NumericConstants.SIXTY, NumericConstants.FIFTY_ONE, NumericConstants.SIXTY, pvsdto, AMOUNT, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(iwDetialsVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, dataList, NumericConstants.FIFTY_ONE, NumericConstants.SIXTY, NumericConstants.FIFTY_ONE, NumericConstants.SIXTY, pvsdto, RATE, false,actualBasis);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(iwDetialsPer);
                }
            }
        }

        //% Of Ex Factory Product
        if (pvsdto.isVarPerExFacSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), gtsList, dataList, NumericConstants.THIRTEEN, NumericConstants.SIXTY, NumericConstants.THIRTEEN, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), gtsList, dataList, NumericConstants.THIRTEEN, NumericConstants.SIXTY, NumericConstants.THIRTEEN, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), gtsList, dataList, NumericConstants.THIRTEEN, NumericConstants.SIXTY, NumericConstants.THIRTEEN, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, NumericConstants.FIFTY_FIVE, NumericConstants.SIXTY, NumericConstants.FIFTY_FIVE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, NumericConstants.FIFTY_FIVE, NumericConstants.SIXTY, NumericConstants.FIFTY_FIVE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, NumericConstants.FIFTY_FIVE, NumericConstants.SIXTY, NumericConstants.FIFTY_FIVE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(), gtsList, dataList, NumericConstants.THIRTY_THREE, NumericConstants.SIXTY, NumericConstants.THIRTY_THREE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(), gtsList, dataList, NumericConstants.THIRTY_THREE, NumericConstants.SIXTY, NumericConstants.THIRTY_THREE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(), gtsList, dataList, NumericConstants.THIRTY_THREE, NumericConstants.SIXTY, NumericConstants.THIRTY_THREE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), gtsList, dataList, NumericConstants.FIFTY_THREE, NumericConstants.SIXTY, NumericConstants.FIFTY_THREE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), gtsList, dataList, NumericConstants.FIFTY_THREE, NumericConstants.SIXTY, NumericConstants.FIFTY_THREE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), gtsList, dataList, NumericConstants.FIFTY_THREE, NumericConstants.SIXTY, NumericConstants.FIFTY_THREE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), gtsList, dataList, NumericConstants.THIRTY_FIVE, NumericConstants.SIXTY, NumericConstants.THIRTY_FIVE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), gtsList, dataList, NumericConstants.THIRTY_FIVE, NumericConstants.SIXTY, NumericConstants.THIRTY_FIVE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), gtsList, dataList, NumericConstants.THIRTY_FIVE, NumericConstants.SIXTY, NumericConstants.THIRTY_FIVE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), gtsList, dataList, NumericConstants.FIFTY_SEVEN, NumericConstants.SIXTY, NumericConstants.FIFTY_SEVEN, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), gtsList, dataList, NumericConstants.FIFTY_SEVEN, NumericConstants.SIXTY, NumericConstants.FIFTY_SEVEN, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), gtsList, dataList, NumericConstants.FIFTY_SEVEN, NumericConstants.SIXTY, NumericConstants.FIFTY_SEVEN, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(pob);
            }
        }
        //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    System.out.println("==insdie not details============");
                    ProjectionVarianceDTO sales = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, NumericConstants.FIVE, NumericConstants.SIXTY, NumericConstants.FIVE, NumericConstants.SIXTY, pvsdto, AMOUNT, true,actualBasis);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, NumericConstants.FIVE, NumericConstants.SIXTY, NumericConstants.FIVE, NumericConstants.SIXTY, pvsdto, AMOUNT, true,actualBasis);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, NumericConstants.FIVE, NumericConstants.SIXTY, NumericConstants.FIVE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesPer);
                }
            }
        }
        //Contract Units
        if (pvsdto.isVarContractUnits()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), gtsList, dataList, NumericConstants.SEVEN, NumericConstants.SIXTY, NumericConstants.SEVEN, NumericConstants.SIXTY, pvsdto, AMOUNT_UNITS, true,actualBasis);
                projectionVarianceDTO.add(units);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), gtsList, dataList, NumericConstants.SEVEN, NumericConstants.SIXTY, NumericConstants.SEVEN, NumericConstants.SIXTY, pvsdto, AMOUNT_UNITS, true,actualBasis);
                projectionVarianceDTO.add(units);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), gtsList, dataList, NumericConstants.SEVEN, NumericConstants.SIXTY, NumericConstants.SEVEN, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(units);
            }
        }
        //Discount $ 
        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), gtsList, dataList, NumericConstants.NINE, NumericConstants.SIXTY, NumericConstants.NINE, NumericConstants.SIXTY, pvsdto, AMOUNT, true,actualBasis);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), gtsList, dataList, NumericConstants.NINE, NumericConstants.SIXTY, NumericConstants.NINE, NumericConstants.SIXTY, pvsdto, AMOUNT, true,actualBasis);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), gtsList, dataList, NumericConstants.NINE, NumericConstants.SIXTY, NumericConstants.NINE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
        }
        //Discount % 
        if (pvsdto.isVarDisRate()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(), gtsList, dataList, NumericConstants.ELEVEN, NumericConstants.SIXTY, NumericConstants.ELEVEN, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(), gtsList, dataList, NumericConstants.ELEVEN, NumericConstants.SIXTY, NumericConstants.ELEVEN, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(), gtsList, dataList, NumericConstants.ELEVEN, NumericConstants.SIXTY, NumericConstants.ELEVEN, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
        }
        // RPU
        if (pvsdto.isVarRPU()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(), gtsList, dataList, NumericConstants.FORTY_ONE, NumericConstants.SIXTY, NumericConstants.FORTY_ONE, NumericConstants.SIXTY, pvsdto, AMOUNT, true,actualBasis);
                netSalesValue = setDataObjects(netSalesValue, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(), gtsList, dataList, NumericConstants.FORTY_ONE, NumericConstants.SIXTY, NumericConstants.FORTY_ONE, NumericConstants.SIXTY, pvsdto, AMOUNT, true,actualBasis);
                netSalesVar = setDataObjects(netSalesVar, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(), gtsList, dataList, NumericConstants.FORTY_ONE, NumericConstants.SIXTY, NumericConstants.FORTY_ONE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        
        // DiscountPerExFactory
        if (pvsdto.isDiscountPerExFactory()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), gtsList, dataList, NumericConstants.SIXTY_ONE, NumericConstants.SIXTY, NumericConstants.SIXTY_ONE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                netSalesValue = setDataObjects(netSalesValue, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), gtsList, dataList, NumericConstants.SIXTY_ONE, NumericConstants.SIXTY, NumericConstants.SIXTY_ONE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                netSalesVar = setDataObjects(netSalesVar, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), gtsList, dataList, NumericConstants.SIXTY_ONE, NumericConstants.SIXTY, NumericConstants.SIXTY_ONE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        
      //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(), gtsList, dataList, NumericConstants.TWENTY_SEVEN, NumericConstants.SIXTY, NumericConstants.TWENTY_SEVEN, NumericConstants.SIXTY, pvsdto, AMOUNT, true,actualBasis);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(), gtsList, dataList, NumericConstants.TWENTY_SEVEN, NumericConstants.SIXTY, NumericConstants.TWENTY_SEVEN, NumericConstants.SIXTY, pvsdto, AMOUNT, true,actualBasis);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(), gtsList, dataList, NumericConstants.TWENTY_SEVEN, NumericConstants.SIXTY, NumericConstants.TWENTY_SEVEN, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        
           //NetSalesExFactory 
        if (pvsdto.isNetSalesExFactory()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), gtsList, dataList, NumericConstants.FIFTY_NINE, NumericConstants.SIXTY, NumericConstants.FIFTY_NINE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), gtsList, dataList, NumericConstants.FIFTY_NINE, NumericConstants.SIXTY, NumericConstants.FIFTY_NINE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), gtsList, dataList, NumericConstants.FIFTY_NINE, NumericConstants.SIXTY, NumericConstants.FIFTY_NINE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(), gtsList, dataList, NumericConstants.FORTY_THREE, NumericConstants.SIXTY, NumericConstants.FORTY_THREE, NumericConstants.SIXTY, pvsdto, AMOUNT, true,actualBasis);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(), gtsList, dataList, NumericConstants.FORTY_THREE, NumericConstants.SIXTY, NumericConstants.FORTY_THREE, NumericConstants.SIXTY, pvsdto, AMOUNT, true,actualBasis);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(), gtsList, dataList, NumericConstants.FORTY_THREE, NumericConstants.SIXTY, NumericConstants.FORTY_THREE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(), gtsList, dataList, NumericConstants.FORTY_FIVE, NumericConstants.SIXTY, NumericConstants.FORTY_FIVE, NumericConstants.SIXTY, pvsdto, AMOUNT, true,actualBasis);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(), gtsList, dataList, NumericConstants.FORTY_FIVE, NumericConstants.SIXTY, NumericConstants.FORTY_FIVE, NumericConstants.SIXTY, pvsdto, AMOUNT, true,actualBasis);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(), gtsList, dataList, NumericConstants.FORTY_FIVE, NumericConstants.SIXTY, NumericConstants.FORTY_FIVE, NumericConstants.SIXTY, pvsdto, RATE, true,actualBasis);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        return projectionVarianceDTO;
    }

    public ProjectionVarianceDTO getCustomisedProjectionResultsDiscount(List<Object> list, String discountName, PVSelectionDTO projSelDTO) {
        LOGGER.debug("Inside getCustomisedProjectionResultsDiscount");
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("group");
        ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
        projDTO.setLevelValue(projSelDTO.getLevelValue());
        projDTO.setLevelNo(projSelDTO.getLevelNo());
        projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projDTO.setGroup(null);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<String> discountList = new ArrayList<String>(projSelDTO.getDiscountNameList());
        boolean start = true;
        String discount = null;
        List<Integer> priorList = new ArrayList<Integer>(projSelDTO.getProjIdList());
        if (list != null && !list.isEmpty()) {
            for (int i = projSelDTO.getDiscountIndex(); i < list.size() && start; i++) {
                projSelDTO.setDiscountIndex(i);
                Object[] discountRow = (Object[]) list.get(i);
                if (discount == null) {
                    projDTO.setGroup(String.valueOf(discountRow[NumericConstants.TWO]));
                } else if (!discount.equals(discountRow[NumericConstants.TWO].toString())) {
                    if (!discountName.contains("total")) {
                        discountList.remove(discount);
                    }
                    start = false;
                }
                if (start) {
                    discount = discountRow[NumericConstants.TWO] != null ? discountRow[NumericConstants.TWO].toString() : StringUtils.EMPTY;
                    String column = StringUtils.EMPTY;
                    String column1 = "";
                    if (frequencyDivision == NumericConstants.FOUR) {
                        column = "Q" + discountRow[1] + StringUtils.EMPTY + discountRow[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        column = "S" + discountRow[1] + StringUtils.EMPTY + discountRow[0];
                    } else if (frequencyDivision == 1) {
                        column = StringUtils.EMPTY + discountRow[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(discountRow[1])) - 1);
                        column = monthName + discountRow[0];
                    }
                    column1 = column + CURRENT + projSelDTO.getCurrentProjId();
                    if (projSelDTO.hasColumn(column1)) {
                        String value = "" + discountRow[NumericConstants.THREE];
                        if (projSelDTO.getSales().contains("SALES")) {
                            value = getFormattedValue(AMOUNT, value);
                        } else if (projSelDTO.getSales().contains("RATE")) {
                            value = getFormattedValue(RATE_PER_THREE, value);
                            value = value + PERCENT;
                        }
                        projDTO.addStringProperties(column1, value);
                        columnList.remove(column1);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        column1 = column + priorList.get(j);
                        if (projSelDTO.hasColumn(column1)) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + discountRow[NumericConstants.THREE + ((j + 1) * NumericConstants.THREE)])));
                            if (projSelDTO.getSales().contains("SALES")) {
                                priorVal = getFormattedValue(AMOUNT, priorVal);
                            } else if (projSelDTO.getSales().contains("RATE")) {
                                priorVal = getFormattedValue(RATE_PER_THREE, priorVal);
                                priorVal = priorVal + PERCENT;
                            }
                            projDTO.addStringProperties(column1, priorVal);
                            columnList.remove(column1);
                        }
                    }
                }
            }

        }
        if (discountName.contains("total")) {
            projDTO.setParent(1);
            projDTO.setLevelNo(projSelDTO.getLevelNo());
            projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
            projDTO.setParentNode(projSelDTO.getParentNode());
            projDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
            projDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
            projDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
            projDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
            if (projSelDTO.getSales().contains("SALES")) {
                projDTO.setGroup("Total Discount $");
            } else {
                projDTO.setGroup("Total Discount %");
            }
        } else {
            if (discount != null) {
                discountList.remove(discount);
            }
            projDTO.setParent(0);
            if (projDTO.getGroup() == null || "".equals(projDTO.getGroup())) {
                projDTO.setGroup(discountName);
                discountList.remove(discountName);
            }
        }

        for (String columns : columnList) {
            projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
        }
        LOGGER.debug("Ending getCustomisedProjectionResultsDiscount");
        return projDTO;
    }

    ProjectionVarianceDTO setDataObjects(ProjectionVarianceDTO dto, ProjectionVarianceDTO parentDto, PVSelectionDTO pvsdto) {
        dto.setLevelNo(parentDto.getLevelNo());
        dto.setTreeLevelNo(parentDto.getTreeLevelNo());
        dto.setParentNode(parentDto.getParentNode());
        dto.setLevelValue(parentDto.getGroup());
        dto.setHierarchyIndicator(parentDto.getHierarchyIndicator());
        dto.setHierarchyNo(parentDto.getHierarchyNo());
        if (dto.getHierarchyIndicator().equals(C)) {
            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
            dto.setProductHierarchyNo(pvsdto.getProductHierarchyNo());
        } else if (dto.getHierarchyIndicator().equals(P)) {
            dto.setProductHierarchyNo(dto.getHierarchyNo());
            dto.setCustomerHierarchyNo(pvsdto.getCustomerHierarchyNo());
        }
        return dto;
    }

    public Map<Object, Object> getNMProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<Object[]>();
        Map<Object, Object> map = new HashMap<Object, Object>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(NmProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq("projectionMasterSid", projectionId));
        query.add(RestrictionsFactoryUtil.eq("screenName", screenName));
        ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
        projectionListFrom.add(ProjectionFactoryUtil.property("fieldName"));
        projectionListFrom.add(ProjectionFactoryUtil.property("fieldValues"));
        query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
        try {
            list = NmProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(NULL.getConstant())) {
            value = FORMAT.format(Double.valueOf(ZERO));
        } else {
            value = FORMAT.format(Double.valueOf(value));
        }
        return value;
    }

    public String isNull(String value) {
        if (value.contains(NULL.getConstant())) {
            value = ZERO;
        }
        return value;
    }

    public void saveNMPVSelection(Map map, int projectionID, String screenName) {
        List<NmProjectionSelection> list = new ArrayList<NmProjectionSelection>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(NmProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq("projectionMasterSid", projectionID));
        query.add(RestrictionsFactoryUtil.eq("screenName", screenName));

        try {
            list = NmProjectionSelectionLocalServiceUtil.dynamicQuery(query);

            if (list.isEmpty()) {
                commonLogic.saveSelection(map, projectionID, screenName, "save", "CFF_SELECTION");
            } else {
                commonLogic.saveSelection(map, projectionID, screenName, "update", "CFF_SELECTION");
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Method returns the Projection Names for the Projection Ids. Returns
     * Projection Names and Projection Ids in separate list. list.get(0) -
     * Projection Ids List. list.get(1) - Projection Names List.
     *
     * @param projectionIds
     * @return
     */
    public List<List> getComparisonProjections(String projectionIds) {
        projectionIds = projectionIds.substring(1, projectionIds.length() - 1);
        String query = SQlUtil.getQuery("get-projection-names-by-id");
        query = query.replace("@CFF_MASTER_SID", projectionIds);
        List<Object[]> resultList = (List) commonDao.executeSelectQuery(query, null, null);
        List<List> list = new ArrayList<>();
        if (resultList != null && !resultList.isEmpty()) {
            List projectionIdList = new ArrayList<>();
            List projectionNameList = new ArrayList<>();
            for (Object[] object : resultList) {
                projectionIdList.add(object[0]);
                projectionNameList.add(object[1]);
            }
            list.add(projectionIdList);
            list.add(projectionNameList);
        }
        return list;
    }

    public static void waitForPPAProcedure() {
        LOGGER.debug("Inside  waitForProcedure Method");
        if (procedureThread != null) {
            synchronized (procedureThread) {
                if (procedureThread.isAlive()) {

                    try {
                        runnableJob.wait();
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            }
        }
        LOGGER.debug("End of  waitForProcedure  Method");
    }

    /**
     * Ex Factory Sales
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getCommonCustomizedDTO(final String groupName, List<Object> gtsList, List<Object> dataList, final int totalListPostion, final int totalPrPos, final int detailsListPos, final int detailsPrPos, PVSelectionDTO pvsdto, final DecimalFormat FORMAT, boolean inclusionFlag,boolean actualBasis) {
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        try {
            LOGGER.debug("Inside getExFactorySales");
            int frequencyDivision = pvsdto.getFrequencyDivision();

            if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                pvDTO.setGroup(groupName.concat(Constants.VALUE));
            } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                pvDTO.setGroup(groupName.concat(Constants.VARIANCE));
            } else {
                pvDTO.setGroup(groupName.concat(Constants.CHANGE));
            }
            if (!pvsdto.getDiscountLevel().equals("Total Discount") && (pvDTO.getGroup().contains("Discount") || pvDTO.getGroup().contains("RPU"))) {
                pvDTO.setParent(1);
            }
            pvDTO.setCcpIds(pvsdto.getLevel().equals(TOTAL.getConstant()) ? StringUtils.EMPTY : pvsdto.getCcpIds());
            List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
            if (gtsList != null && !gtsList.isEmpty()) {
                if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                    //   System.out.println("==gts list size================>>>>" + gtsList.size());
                    for (int i = 0; i < gtsList.size(); i++) {
                        final Object[] obj = (Object[]) gtsList.get(i);
                        String commonColumn = StringUtils.EMPTY;
                        if (frequencyDivision == NumericConstants.FOUR) {
                            commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                        } else if (frequencyDivision == NumericConstants.TWO) {
                            commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                        } else if (frequencyDivision == 1) {
                            commonColumn = StringUtils.EMPTY + obj[0];
                        } else if (frequencyDivision == NumericConstants.TWELVE) {
                            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                            commonColumn = monthName.toLowerCase() + obj[0];
                        }
                        String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion - 1])));
                        String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])));
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            if (inclusionFlag) {
                                if (obj[totalListPostion] == null) {
                                    String baseValue = getFormattedValue(FORMAT, "0.00");
                                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                                } else {
//                                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])));
                                    String baseValue = getFormattedValue(FORMAT, actualValue);
                                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                                    baseValue = getFormattedValue(FORMAT, currentValue);
                                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                                }
                            } else {
//                                String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])));
                                String baseValue = getFormattedValue(FORMAT, actualValue);
                                pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                                baseValue = getFormattedValue(FORMAT, currentValue);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                            }

                        }
//                        else {
//                            pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), "");
//                        }

                        if (actualBasis) {
                            if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
//                        for CURRENT
                                String val = getVariance(actualValue, currentValue, FORMAT);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? val + PERCENT : val);
                            }
                            if (pvsdto.getVarIndicator().equals(CHANGE.getConstant())) {
//                        for CURRENT
                                String val = getPerChange(actualValue, currentValue, FORMAT);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), val + PERCENT);
                            }
                        }
                        for (int j = 0; j < priorList.size(); j++) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion + ((j + 1) * totalPrPos)])));
                            if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                                if (obj[totalListPostion + ((j + 1) * totalPrPos)] == null) {
                                    String val = getFormattedValue(FORMAT, "0.00");
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                } else {
                                    String val = getFormattedValue(FORMAT, priorVal);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                }

                            } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                                if (obj[totalListPostion + ((j + 1) * totalPrPos)] == null && obj[totalListPostion] == null) {
                                    String val = getFormattedValue(FORMAT, "0.00");
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                } else if (actualBasis) {
                                    String val = getVariance(actualValue, priorVal, FORMAT);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                } else {
                                    String val = getVariance(currentValue, priorVal, FORMAT);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                }

                            } else if (obj[totalListPostion + ((j + 1) * totalPrPos)] == null && obj[totalListPostion] == null) {
                                String val = getFormattedValue(FORMAT, "0.00");
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                            } else 
                                if (actualBasis) {
                                    String baseValu = getPerChange(actualValue, priorVal, RATE_PER);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                                } else {
                                    String baseValu = getPerChange(currentValue, priorVal, RATE_PER);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                                }
                             
                            }
                        }
                } else {
                    for (int i = 0; i < dataList.size(); i++) {
                        final Object[] obj = (Object[]) dataList.get(i);
                        String commonColumn = StringUtils.EMPTY;
                        if (frequencyDivision == NumericConstants.FOUR) {
                            commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                        } else if (frequencyDivision == NumericConstants.TWO) {
                            commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                        } else if (frequencyDivision == 1) {
                            commonColumn = StringUtils.EMPTY + obj[0];
                        } else if (frequencyDivision == NumericConstants.TWELVE) {
                            String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                            commonColumn = monthName.toLowerCase() + obj[0];
                        }
                        String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos - 1])));
                        String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos])));
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            if (obj[detailsListPos] == null) {
                                String val = getFormattedValue(FORMAT, "0.00");
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(),  groupName.contains("%") ? val + PERCENT : val);
                            } else {
                                String baseValue = getFormattedValue(FORMAT, actualValue);
                                pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                                baseValue = getFormattedValue(FORMAT, currentValue);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? baseValue + PERCENT : baseValue);
                            }

                        } 
                         if (actualBasis) {
                            if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
//                        for ACTUAL
                                String val = getVariance(actualValue, currentValue, FORMAT);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%") ? val + PERCENT : val);
                            }
                            if (pvsdto.getVarIndicator().equals(CHANGE.getConstant())) {
//                        for CURRENT
                                String val = getPerChange(actualValue, currentValue, FORMAT);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), val + PERCENT);
                            }
                        }
                        for (int j = 0; j < priorList.size(); j++) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos + ((j + 1) * detailsPrPos)])));
                            if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                                if (obj[detailsListPos + ((j + 1) * detailsPrPos)] == null) {
                                    String val = getFormattedValue(FORMAT, "0.00");
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                } else {
                                    String val = getFormattedValue(FORMAT, priorVal);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                }

                            } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                                if (obj[detailsListPos + ((j + 1) * detailsPrPos)] == null && obj[detailsListPos] == null) {
                                    String val = getFormattedValue(FORMAT, "0.00");
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                } else {
                                    if (actualBasis) {
                                        String val = getVariance(actualValue, priorVal, FORMAT);
                                        pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                    } else {
                                        String val = getVariance(currentValue, priorVal, FORMAT);
                                        pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val + PERCENT : val);
                                    }
                                }

                            } else if (obj[detailsListPos + ((j + 1) * detailsPrPos)] == null && obj[detailsListPos] == null) {
                                String val = getFormattedValue(FORMAT, "0.00");
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                            } else {
                                if (actualBasis) {
                                    String baseValu = getPerChange(actualValue, priorVal, RATE_PER);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                                } else {
                                    String baseValu = getPerChange(currentValue, priorVal, RATE_PER);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return pvDTO;
    }

    /**
     * Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getCommonCustPerDTO(final String groupName, final List<Object> dataList, final int totPos, final int totPrPos, ProjectionVarianceDTO gtsDto, ProjectionVarianceDTO salesDto, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(groupName.concat(Constants.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(groupName.concat(Constants.VARIANCE));
        } else {
            pvDTO.setGroup(groupName.concat(Constants.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = StringUtils.EMPTY + obj[totPos];
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[totPos + ((j + 1) * totPrPos)];
                            String val = getFormattedValue(RATE, isNull(priorVal));
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[totPos + ((j + 1) * totPrPos)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totPos])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[totPos + ((j + 1) * totPrPos)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[totPos])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
            columnList.remove("group");
            for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
                pvDTO.addStringProperties(nullObj, DASH);
            }
            for (Object object : columnList) {
                String gts = StringUtils.EMPTY + String.valueOf(gtsDto.getPropertyValue(object)).replace("$", "").replace("%", "").replace(",", "").replace("-", "0");
                String sales = StringUtils.EMPTY + String.valueOf(salesDto.getPropertyValue(object)).replace("$", "").replace("%", "").replace(",", "").replace("-", "0");
                Double pob = Double.valueOf(isNull(sales)) / Double.valueOf(isNull(gts));
                if (pob.isInfinite() || pob.isNaN()) {
                    pob = 0.0;
                }
                if (pvDTO.getGroup().equals(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString().concat(Constants.CHANGE))) {
                    String POB = getFormattedValue(RATE_PER, String.valueOf(pob));
                    pvDTO.addStringProperties(object, POB + PERCENT);
                } else {
                    String POB = getFormattedValue(RATE, String.valueOf(pob));
                    pvDTO.addStringProperties(object, POB + PERCENT);
                }

            }
        }
        return pvDTO;
    }

    public List<ProjectionVarianceDTO> getCustomisedDiscount(List<Object> dataList, PVSelectionDTO projSelDTO, int index, int priorIndex, boolean isPer) {
        LOGGER.debug("Inside getCustomisedProjectionResultsTotalDiscount");
        String lastValue = StringUtils.EMPTY;
        List<ProjectionVarianceDTO> resultDto = new ArrayList<>();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        boolean actualBasis = "Actuals".equalsIgnoreCase(projSelDTO.getComparisonBasis());
        String currentValue = StringUtils.EMPTY;
        String actValue = StringUtils.EMPTY;
        String column = StringUtils.EMPTY;
        List<Integer> priorList = new ArrayList<>(projSelDTO.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                if (!"".equals(lastValue) && !"null".equals(lastValue) && obj[NumericConstants.TWO] != null && !lastValue.equals(String.valueOf(obj[NumericConstants.TWO]))) {
                    pvDTO.setGroup(lastValue);
                    resultDto.add(pvDTO);
                    pvDTO = new ProjectionVarianceDTO();
                }
                lastValue = String.valueOf(obj[NumericConstants.TWO]);
                pvDTO.setGroup(lastValue);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == NumericConstants.FOUR) {
                    commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == NumericConstants.TWO) {
                    commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == NumericConstants.TWELVE) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }

                actValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                    if (obj[index] == null) {
                        String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                        pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    } else {
                        String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, actValue);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                        baseValue = getFormattedValue(isPer ? RATE : AMOUNT, currentValue);
                        pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    }
                } 
                 if (actualBasis) {
                        if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                            column = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
//                        for CURRENT
                            String val = getVariance(actValue, currentValue, isPer ? RATE : AMOUNT);
                            pvDTO.addStringProperties(column, isPer ? val + PERCENT : val);
                        }
                        if (projSelDTO.getVarIndicator().equals(CHANGE.getConstant())) {
                            column = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
//                        for CURRENT
                            String val = getPerChange(actValue, currentValue, RATE);
                            pvDTO.addStringProperties(column,val + PERCENT);
                        }
                   
                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                        if (obj[index + ((j + 1) * priorIndex)] == null) {
                            String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? baseValue + PERCENT : baseValue);
                        } else {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * priorIndex)])));
                            String val = getFormattedValue(isPer ? RATE : AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                        }
                    } else if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                        if (obj[index + ((j + 1) * priorIndex)] == null && obj[index] == null) {
                            String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? baseValue + PERCENT : baseValue);
                        } else {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * priorIndex)];
                             String val = StringUtils.EMPTY;
                           if (actualBasis) {
                                if (isPer) {
                                    val = getVariance(actValue, priorVal, RATE_PER);
                                } else {
                                    val = getVariance(actValue, priorVal, AMOUNT);
                                }
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                        } else {
                            if (isPer) {
                                val = getVariance(currentValue, priorVal, RATE_PER);
                            } else {
                                val = getVariance(currentValue, priorVal, AMOUNT);
                            }
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                        }
                        }
                    } else if (obj[index + ((j + 1) * priorIndex)] == null && obj[index] == null) {
                        String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, "0.00");
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? baseValue + PERCENT : baseValue);
                    } else {
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * priorIndex)];
                        String val = StringUtils.EMPTY;
                        if (actualBasis) {
                            String baseValu = getPerChange(actValue, priorVal, RATE_PER);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        } else {
                            String baseValu = getPerChange(currentValue, priorVal, RATE_PER);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        }
                    }
                }
                if (i == dataList.size() - 1) {
                    resultDto.add(pvDTO);
                }
            }
        }
        LOGGER.debug("Ending getCustomisedProjectionResultsTotalDiscount with list size  = = >" + resultDto.size());
        return resultDto;
    }

    /**
     * Get Custom View List
     *
     * @param projectionId
     * @return list
     */
    public static List<List<String>> loadDiscounts(int projectionId) {
        List<List<String>> discountlist = new ArrayList<>();
        List<String> discountNolist = new ArrayList<>();
        List<String> discountNamelist = new ArrayList<>();
        List input = new ArrayList<>();
        input.add(projectionId);
        List list = CommonQueryUtils.getAppData(input, "getDiscountName", null);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                discountNolist.add(String.valueOf(obj[0]));
                discountNamelist.add(String.valueOf(obj[1]));
            }
        }
        discountlist.add(discountNolist);
        discountlist.add(discountNamelist);
        return discountlist;
    }

    public static List loadProgramCategory(int projectionId) {
        List input = new ArrayList<>();
        input.add(projectionId);
        List list = CommonQueryUtils.getAppData(input, "getProgramCategoryDiscountName", null);
        return list;
    }

    String getCCPIds(PVSelectionDTO projSelDTO) {
        String query = CommonLogic.getCCPQueryForCff(projSelDTO);
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        String ccps = StringUtils.EMPTY;
        boolean flag = true;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object[] obj = (Object[]) list.get(i);
                if (flag) {
                    ccps = String.valueOf(obj[1]);
                    flag = false;
                } else {
                    ccps = ccps + "," + String.valueOf(obj[1]);
                }
            }
        }
        return ccps;
    }

    private List getComparisonInput(final ComparisonLookupDTO comparisonLookup) {
        String ASTERIK = "*";
        String PERCENT = "%";
        List inputList = new ArrayList();
        if (comparisonLookup.getWorkflowStatus() != null && !comparisonLookup.getWorkflowStatus().equals(Constants.SELECT_ONE)) { //Added for GAL-9231
            if (!comparisonLookup.getWorkflowStatus().equals("Saved")) {
                inputList.add(comparisonLookup.getWorkflowStatus());
            } else {
                inputList.add(PERCENT);
            }
        } else {
            inputList.add(PERCENT);
        } //Ends here
        inputList.add(comparisonLookup.getCurrentProjId());
        if (comparisonLookup.getProjectionName() != null && !comparisonLookup.getProjectionName().isEmpty()) {
            inputList.add(comparisonLookup.getProjectionName().replace(ASTERIK, PERCENT));
        } else {
            inputList.add(PERCENT);
        }
        if (comparisonLookup.getProjectionDescription() != null && !comparisonLookup.getProjectionDescription().isEmpty()) {
            inputList.add(comparisonLookup.getProjectionDescription().replace(ASTERIK, PERCENT));
        } else {
            inputList.add(PERCENT);
        }
        if (comparisonLookup.getCreatedDateFrom() != null) {
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            Date startValue = (Date) comparisonLookup.getCreatedDateFrom();
            String customSql = "AND PM.CREATED_DATE >= '" + format2.format(startValue) + "'";
            inputList.add(customSql);
        } else {
            inputList.add(" ");
        }
        
        if (comparisonLookup.getCreatedDateTo() != null && !"null".equals(comparisonLookup.getCreatedDateTo()) && !"".equals(comparisonLookup.getCreatedDateTo())) {
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            Date endValue = (Date) comparisonLookup.getCreatedDateTo();
            String customSql = "AND PM.CREATED_DATE <= '" + format2.format(endValue) + "'";
            inputList.add(customSql);
        } else {
            inputList.add(" ");
        }
        inputList.add(comparisonLookup.getCurrentProjId());
        StringBuilder filter = AbstractFilterLogic.getInstance().filterQueryGenerator(comparisonLookup.getFilter(), getFilerMap());

        if (filter != null && !filter.equals("")) {
            inputList.add(filter.toString());
        } else {
            inputList.add(" ");
        }

        return inputList;
    }

    private Map<String, String> getFilerMap() {
        Map<String, String> map = new HashMap<>();
        map.put("projectionName", "PM.CFF_NAME");
        map.put("projectionDescription", "PM.CFF_NAME");
        map.put("marketType", "HT.DESCRIPTION");
        map.put("customer", "CM.COMPANY_NO");
        map.put("contract", "C.CONTRACT_NAME");
        map.put("brand", "BM.BRAND_NAME");
        map.put("createdDateFrom", "PM.CREATED_DATE");
        map.put("createdBy", "PM.CREATED_BY");
        map.put("createdBy", "PM.CFF_MASTER_SID");
        return map;
    }

    private Map<String, String> getOrderByMap() {
        Map<String, String> map = new HashMap<>();
        map.put("projectionName", "CTE.CFF_NAME");
        map.put("projectionDescription", "CTE.CFF_NAME");
        map.put("marketType", "MARKET_TYPE");
        map.put("customer", "CONTRACT_HOLDER");
        map.put("contract", "CONTRACT");
        map.put("brand", "BRAND");
        map.put("createdDateFrom", "CTE.CREATED_DATE");
        map.put("createdBy", "CTE.CREATED_BY");
        map.put("createdBy", "CTE.CFF_MASTER_SID");
        return map;
    }

    private String getCommonColumn(int frequencyDivision, String column) {
        String returnString = StringUtils.EMPTY;
        if (frequencyDivision == NumericConstants.FOUR) {
            returnString = column.replace("q", "Q");
            return returnString;
        }
        if (frequencyDivision == NumericConstants.TWO) {
            returnString = column.replace("s", "S");
            return returnString;
        }
        if (frequencyDivision == NumericConstants.TWELVE) {
            returnString = column.toLowerCase();
            return returnString;
        }
        return column;
    }

    private String getCellValue(Object obj, String component) {
        String returnValue = StringUtils.EMPTY;
        if (obj == null) {
            return returnValue;
        } else {
            returnValue = StringUtils.EMPTY + obj;
            if ("Amount".equals(component)) {
                returnValue = getFormattedValue(AMOUNT, returnValue);
            } else if ("Rate".equals(component)) {
                returnValue = getFormattedValue(RATE, returnValue);
                returnValue = returnValue + PERCENT;
            } else if ("ContractUnits".equals(component)) {
                returnValue = getFormattedValue(AMOUNT_UNITS, returnValue);
            }
            return returnValue;
        }
    }

    private String getCommonColumn(String column, int frequencyDivision) {
        if (frequencyDivision == 1) {
        } else if (frequencyDivision == NumericConstants.FOUR) {
            column = column.replace("q", "Q");
        } else if (frequencyDivision == NumericConstants.TWO) {
            column = column.replace("s", "S");
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            column = column.toLowerCase();
        }
        return column;
    }
    
    public String getVariance(String actualValue, String priorVal, DecimalFormat format) {
        Double val = Double.valueOf(isNull(actualValue));
        Double val1 = Double.valueOf(isNull(priorVal));
        String value;
        String variance = StringUtils.EMPTY;
        if (format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE)) {
//            if (val1 > val) {
//                value = String.valueOf(roundToFraction(Double.valueOf(val1 - val), 10000));
//                value = roundToFraction(Double.valueOf(value), 100) + "";
//            } else {
                
                value = String.valueOf(roundToFraction((val - val1), 10000));
                value = roundToFraction(Double.valueOf(value), 100) + "";
//            }
            value = getFormattedValue(format, value);
        } else {
            variance = String.valueOf(Double.valueOf(isNull(actualValue)) - Double.valueOf(isNull(priorVal)));
            value = getFormattedValue(format, variance);
        }
        return value;
    }

    public String getPerChange(String actualValue, String priorVal, DecimalFormat format) {
        DecimalFormat formatter = new DecimalFormat("#,##0.00"); 
        Double val = Double.valueOf(isNull(actualValue));
        Double val1 = Double.valueOf(isNull(priorVal));
        String value;
        String variance = StringUtils.EMPTY;
        if (format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE)) {
//            if (val1 > val) {
//                value = String.valueOf(roundToFraction(Double.valueOf(val1 - val), 10000));
//                value = roundToFraction(Double.valueOf(value), 100) + "";
//            } else {
                
                value = String.valueOf(roundToFraction((val - val1), 10000));
                value = roundToFraction(Double.valueOf(value), 100) + "";
//            }
        } else {
            variance = String.valueOf(Double.valueOf(isNull(actualValue)) - Double.valueOf(isNull(priorVal)));
            value = getFormattedValue(formatter, variance);
        }
        String priorval = getFormattedValue(formatter, priorVal);

        value = value.replace(",", StringUtils.EMPTY);
        priorval = priorval.replace(",", StringUtils.EMPTY);
        Double perChange = Double.valueOf(value) / Double.valueOf(priorval);
        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
            perChange = 0.0;
        }
        perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
        String change = String.valueOf(perChange);
        if (change.contains("E")) {
            change = change.substring(0, 3);
        }
        change = getFormattedValue(format, change);
        return change;
    }

    public static double roundToFraction(double x, long fraction) {
        return (double) Math.round(x * fraction) / fraction;
    }
    
    
    public void customizePivot(String variableValue, String variableCategory, String currentSales, String actualSales, PVSelectionDTO pvsdto, ProjectionVarianceDTO projDTO, List<String> columnList,DecimalFormat format) {
        try {
            String column = StringUtils.EMPTY;
            String columnAct = StringUtils.EMPTY;
            boolean actualBasis = "Actuals".equalsIgnoreCase(pvsdto.getComparisonBasis());
            boolean actualCheck = "null".equalsIgnoreCase(actualSales);
            boolean isPer = format.equals(RATE);

            if (variableCategory.equals(Constants.VALUE)) {
                //for current
                columnAct = variableValue + ACTUAL + pvsdto.getCurrentProjId();
                String baseValueAct = getFormattedValue(format, actualSales);
                if (pvsdto.hasColumn(columnAct)) {
                        projDTO.addStringProperties(columnAct, isPer ? baseValueAct + PERCENT : baseValueAct);
                    
                    columnList.remove(columnAct);
                }
                //for current
                column = variableValue + CURRENT + pvsdto.getCurrentProjId();
                String baseValue = getFormattedValue(format, currentSales);
                if (pvsdto.hasColumn(column)) {
                    projDTO.addStringProperties(column, isPer ? baseValue + PERCENT : baseValue);
                    columnList.remove(column);
                }
            }

            if (actualBasis) {

                    if (variableCategory.equals(Constants.VARIANCE)) {
                        column = variableValue + CURRENT + pvsdto.getCurrentProjId();
//                        for CURRENT
                        String val = getVariance(actualSales, currentSales, format);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, isPer ? val + PERCENT : val);
                            columnList.remove(column);
                        }
                    }
                    if (variableCategory.equals(Constants.CHANGE)) {
                        column = variableValue + CURRENT + pvsdto.getCurrentProjId();
//                        for CURRENT
                        String val = getPerChange(actualSales, currentSales, format);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, val + PERCENT);
                            columnList.remove(column);
                        }
                    }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

}
