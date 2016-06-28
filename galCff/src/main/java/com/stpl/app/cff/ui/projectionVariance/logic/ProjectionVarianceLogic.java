/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionVariance.logic;

import com.stpl.app.cff.abstractCff.AbstractFilterLogic;
import com.stpl.app.cff.dao.CommonDAO;
import com.stpl.app.cff.dao.ProjectionVarianceDAO;
import com.stpl.app.cff.dao.impl.CommonDAOImpl;
import com.stpl.app.cff.dao.impl.ProjectionVarianceDAOImpl;
import com.stpl.app.cff.dto.PVSelectionDTO;
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
import static com.stpl.app.cff.util.HeaderUtils.getMonthForInt;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
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
    private ProjectionVarianceDAO projectionVarianceDAO = new ProjectionVarianceDAOImpl();
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
    List<ProjectionVarianceDTO> projDTOList1 = new ArrayList<ProjectionVarianceDTO>();
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
    private static final String PRC_PROJ_RESULTS_DISCOUNT = "PRC_PROJECTION_RESULTS_DISCOUNT";
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
        if (rightHeader.getFrequencyDivision() == 12) {
            from = from.toLowerCase();
            to = to.toLowerCase();
        }

        int start = visibleDoubleCol.indexOf(from);
        int end = visibleDoubleCol.indexOf(to);
        if (rightHeader.getProjectionOrder() == 2) {
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
    public List<ComparisonLookupDTO> getCustomizedComparisonList(final List list) throws Exception {
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
                if (obj[2] == null) {
                    comparisonLookupDTO.setMarketType(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setMarketType(obj[2].toString());
                }
                if (obj[4] == null) {
                    comparisonLookupDTO.setContract(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setContract(obj[4].toString());
                }
                if (obj[3] == null) {
                    comparisonLookupDTO.setCustomer(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setCustomer(obj[3].toString());
                }
                if (obj[5] == null) {
                    comparisonLookupDTO.setBrand(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setBrand(obj[5].toString());
                }
                if (obj[6] == null) {
                    comparisonLookupDTO.setNdcNo(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setNdcNo(obj[6].toString());
                }
                if (obj[7] == null) {
                    comparisonLookupDTO.setNdcName(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setNdcName(obj[7].toString());
                }
                if (obj[8] == null) {
                    comparisonLookupDTO.setProjectionId(0);
                } else {
                    comparisonLookupDTO.setProjectionId(Integer.valueOf(obj[8].toString()));
                }
                if (obj[9] == null) {
                    comparisonLookupDTO.setCreatedDateFrom(null);
                } else {
                    comparisonLookupDTO.setCreatedDateFrom((Date) obj[9]);
                }
                if (obj[10] == null) {
                    comparisonLookupDTO.setCreatedBy(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setCreatedBy(new Converters().getUserFLName(new Converters().convertNullToEmpty(obj[10].toString())));
                }
                finalList.add(comparisonLookupDTO);

            }
        }
        return finalList;
    }

    public List<ComparisonLookupDTO> getComparisonResults(final ComparisonLookupDTO comparisonLookup) throws Exception {
        List inputList = getComparisonInput(comparisonLookup);
        String sql = CommonQueryUtils.getAppQuery(inputList, "comparisonLoadData");
        List result = (List) commonDao.executeSelectQuery(sql, null, null);
        return getCustomizedComparisonList(result);
    }

    /**
     * customize the results
     *
     * @param list
     * @return list
     */
    public List<ComparisonLookupDTO> getCustomizedPVComparisonList(final List list) throws Exception {
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
                if (obj[2] == null) {
                    comparisonLookupDTO.setMarketType(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setMarketType(obj[2].toString());
                }
                if (obj[3] == null) {
                    comparisonLookupDTO.setContract(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setContract(obj[3].toString());
                }
                if (obj[4] == null) {
                    comparisonLookupDTO.setContractHolder(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setContractHolder(obj[4].toString());
                }
                if (obj[5] == null) {
                    comparisonLookupDTO.setBrand(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setBrand(obj[5].toString());
                }
                if (obj[6] == null) {
                    comparisonLookupDTO.setNdcNo(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setNdcNo(obj[6].toString());
                }
                if (obj[7] == null) {
                    comparisonLookupDTO.setNdcName(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setNdcName(obj[7].toString());
                }
                if (obj[8] == null) {
                    comparisonLookupDTO.setProjectionId(0);
                } else {
                    comparisonLookupDTO.setProjectionId(Integer.valueOf(obj[8].toString()));
                }
                if (obj[9] == null) {
                    comparisonLookupDTO.setCreatedDateFrom(null);
                } else {
                    comparisonLookupDTO.setCreatedDateFrom((Date) obj[9]);
                }
                if (obj[10] == null) {
                    comparisonLookupDTO.setCreatedBy(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setCreatedBy(new Converters().getUserFLName(new Converters().convertNullToEmpty(obj[10].toString())));
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

    public Integer getComparisonCount(ComparisonLookupDTO comparisonLookup) throws Exception {
        List list = getComparisonInput(comparisonLookup);
        String query = CommonQueryUtils.getAppQuery(list, "comparisonSearchCount");
        List result = (List) commonDao.executeSelectQuery(query, null, null);
        return CFFLogic.getCount(result);
    }

    public List<ProjectionVarianceDTO> getProjVariance(PVSelectionDTO selectionDTO, Object parentId, int start, int offset) {
        LOGGER.info("Inside getProjVariance");
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
                    selectionDTO.setProductHierarchyNo(selectionDTO.getProductHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                selectionDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                selectionDTO.setGroup(parentDto.getGroup());
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
                    selectionDTO.setProductHierarchyNo(selectionDTO.getProductHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                selectionDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                selectionDTO.setGroup(parentDto.getGroup());
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
                count += getPeriodResultsCount(selectionDTO, selectionDTO.getDiscountNameList(), selectionDTO.getParentNode(), selectionDTO.getLevelNo(), parentDto, isLevelsCount);
            } else {
                count += getPivotResultsCount(parentId, selectionDTO, isLevelsCount);
            }
        } else if (isLevelsCount) {
            selectionDTO.setLevelNo((selectionDTO.getFilterLevelNo()));
            selectionDTO.setTreeLevelNo(selectionDTO.getFilterLevelNo());
            count += configureLevelsCount(selectionDTO);
        }
        return count;
    }

    public int getPeriodResultsCount(PVSelectionDTO pVSelectionDTO, List<String> discountList, String parentName, int levelNo, ProjectionVarianceDTO parentDto, boolean isLevelsCount) {
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
                pVSelectionDTO.setLevelNo((pVSelectionDTO.getTreeLevelNo() + 1));
                int ct = configureLevelsCount(pVSelectionDTO);
                count += ct;
                pVSelectionDTO.setLevelCount(ct);
            }
        return count;
    }

    public List<ProjectionVarianceDTO> getPeriodResults(PVSelectionDTO pVSelectionDTO, int start, int offset, ProjectionVarianceDTO parentDto) {
        LOGGER.info("Inside getPeriodResults");
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
            if (parentDto.getGroup().contains("Discount $") || parentDto.getGroup().contains("Discount %") || parentDto.getGroup().contains("RPU")) {
                pVSelectionDTO.setGroup(parentDto.getGroup());
                isDiscountExpand = true;
                if (parentDto.getGroup().contains("RPU")) {
                    pVSelectionDTO.setRPU(true);
                }
            }
            if (pVSelectionDTO.getPivotView().equals(Constants.PERIOD)) {
                maxRecord += getCustPeriodVarianceCount(pVSelectionDTO);
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
                    if (parentDto.getGroup().contains("Discount $")) {
                        if (parentDto.getGroup().contains("Value")) {
                            pVSelectionDTO.setVarIndicator(VALUE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, 3, 3, Boolean.FALSE));
                        } else if (parentDto.getGroup().contains("Variance")) {
                            pVSelectionDTO.setVarIndicator(VARIANCE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, 3, 3, Boolean.FALSE));
                        } else if (parentDto.getGroup().contains("%Change")) {
                            pVSelectionDTO.setVarIndicator(PERCENT.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, 3, 3, Boolean.FALSE));
                        }
                    } else if (parentDto.getGroup().contains("Discount %")) {
                        if (parentDto.getGroup().contains("Value")) {
                            pVSelectionDTO.setVarIndicator(VALUE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, 4, 3, Boolean.TRUE));
                        } else if (parentDto.getGroup().contains("Variance")) {
                            pVSelectionDTO.setVarIndicator(VARIANCE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, 4, 3, Boolean.TRUE));
                        } else if (parentDto.getGroup().contains("%Change")) {
                            pVSelectionDTO.setVarIndicator(PERCENT.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, 4, 3, Boolean.TRUE));
                        }
                    } else if (parentDto.getGroup().contains("RPU")) {
                        if (parentDto.getGroup().contains("Value")) {
                            pVSelectionDTO.setVarIndicator(VALUE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, 5, 3, Boolean.FALSE));
                        } else if (parentDto.getGroup().contains("Variance")) {
                            pVSelectionDTO.setVarIndicator(VARIANCE.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, 5, 3, Boolean.FALSE));
                        } else if (parentDto.getGroup().contains("%Change")) {
                            pVSelectionDTO.setVarIndicator(PERCENT.getConstant());
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, 5, 3, Boolean.FALSE));
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
            }
        }
        if (!isDiscountExpand && !pVSelectionDTO.isIslevelFiler() && !pVSelectionDTO.isIsLevel() && neededRecord > 0) {
                pVSelectionDTO.setTreeLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                pVSelectionDTO.setLevelNo((pVSelectionDTO.getTreeLevelNo() + 1));
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
        String discountId = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
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
        Object[] orderedArg = {projectionId, frequency, "VARIANCE", null, null, ccps, projSelDTO.getDiscountLevel(),"EXCEL"};
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
        try{
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
         return count;
        
        }catch(Exception e){
        e.printStackTrace();
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
        List<Object[]> list = CommonQueryUtils.getAppData(input, dto.getDiscountLevel().equals(PROGRAM_CATEGORY.toString()) ? "getCffProgramCategoryCount" : "getCffProgramCount", null);
        int count = CFFLogic.getCount(list);
        return count;
    }

    public int configureLevelsCount(PVSelectionDTO projSelDTO) {
        return CommonLogic.getLevelListCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIslevelFiler(), projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid());
    }

    public List<ProjectionVarianceDTO> configureLevels(int start, int offset, PVSelectionDTO projSelDTO, int maxRecord) {
        int resultStart = start;
        List<ProjectionVarianceDTO> resultList = new ArrayList<ProjectionVarianceDTO>();
        if (offset > 0) {
            if (maxRecord == -1) {
                resultStart = start;
            } else {
                resultStart = (start <= maxRecord) ? 0 : start - maxRecord;
            }
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), resultStart, offset, projSelDTO.getHierarchyIndicator(), Integer.valueOf(projSelDTO.getTreeLevelNo()), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIslevelFiler(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true);//            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), resultStart, offset, projSelDTO.getHierarchyIndicator(), Integer.valueOf(projSelDTO.getTreeLevelNo()), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIslevelFiler(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId());
            for (int i = 0; i < levelList.size(); i++) {
                Leveldto levelDto = levelList.get(i);

                ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
                dto.setLevelNo((levelDto.getLevelNo()));
                dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                dto.setParentNode(levelDto.getParentNode());
                dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                dto.setLevelValue(levelDto.getRelationshipLevelValue());
                dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                dto.setHierarchyNo(levelDto.getHierarchyNo());
                if (dto.getHierarchyIndicator().equals(C)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                } else if (dto.getHierarchyIndicator().equals(P)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                }
                dto.setParent(1);
                resultList.add(dto);
            }
        }
        return resultList;
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
        ProjectionVarianceDTO parentDto = new ProjectionVarianceDTO();
        boolean isChild = false;
        if (parent instanceof ProjectionVarianceDTO) {
            parentDto = (ProjectionVarianceDTO) parent;
            isChild = true;
        }
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            count += pvsdto.getPeriodList().size() + 1;
        } else if (isChild) {
            count += pvsdto.getPeriodList().size();
        }

        if (!pvsdto.isIsLevel() && isLevelsCount) {
            pvsdto.setTreeLevelNo(pvsdto.getTreeLevelNo() + 1);
            pvsdto.setLevelNo((pvsdto.getTreeLevelNo() + 1));
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

        ProjectionVarianceDTO parentDto = new ProjectionVarianceDTO();
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        if (started < maxRecord || pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                System.out.println("==inside pivot toatl=====================");
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
                System.out.println("==inside expand==========================");
                parentDto = (ProjectionVarianceDTO) parent;
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
            pvsdto.setLevelNo((pvsdto.getTreeLevelNo() + 1));
            List<ProjectionVarianceDTO> resultList = configureLevels(start, neededRecord, pvsdto, maxRecord);
            for(int i=0; i<resultList.size();i++)
            projDTOList.addAll(resultList);
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
        String discountId = CommonUtils.CollectionToString(pvsdto.getDiscountNoList(), false);
        List<String> projectionIdList = new ArrayList<String>();
        pivotTotalList = new ArrayList<Object>();
        pivotPriorProjIdList = new ArrayList<Integer>();
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
        for (Integer projId : pvsdto.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
            pivotPriorProjIdList.add(projId);
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        String ccps = null;
        if (isDetail) {
            ccps = getCCPIds(pvsdto);
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
                statement.setString(2, frequency);
                statement.setString(3, discountId);
                statement.setInt(4, Integer.valueOf(sessionId));
                statement.setInt(5, Integer.valueOf(userId));
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
            LOGGER.info("Inside getConfiguredProjectionVariance");
            List<ProjectionVarianceDTO> list;
            list = getProjVariance(projSelDTO, parentId, start, offset);
            LOGGER.info("list size in getConfiguredProjectionVariance " + list.size());
            LOGGER.info("Ending getConfiguredProjectionVariance");
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return null;

    }

    public int getConfiguredProjectionVarianceCount(Object parentId, PVSelectionDTO projSelDTO, boolean isLevelCount) {
        try {
            LOGGER.info("In getConfiguredProjectionVarianceCount");
            int count = 0;
            setSelectionDTO(projSelDTO);
            setRightHeader(projSelDTO.getRightHeader());
            count += getProjVarianceCount(projSelDTO, parentId, isLevelCount);
            return count;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return 0;
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
            List<Object[]> result = new ArrayList<Object[]>();
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
        LOGGER.info("Inside getDiscountExpandQuery");
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
        LOGGER.info("Ending getDiscountExpandQuery");
        return projDTOList;
    }

 public List<ProjectionVarianceDTO> getCustomizedPivotTotalResults(final List<Object> results, List<Integer> priorProjGtsList, PVSelectionDTO pvsdto, List totalDiscount) {
        System.out.println("==inside getCustomizedPivotTotalResults====================");
     List<String> periodList = new ArrayList<>(pvsdto.getPeriodList());
        Map<String, String> periodListMap = new HashMap<>(pvsdto.getPeriodListMap());
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<>();
        String[] singleColumn = new String[3];
        int frequencyDivision = pvsdto.getFrequencyDivision();
        int discountIndex = 0;
        int prPos = 31;
        if (results != null && !results.isEmpty()) {
            for (int i = 0; i < results.size(); i++) {
                final Object[] row = (Object[]) results.get(i);
                int year = Integer.valueOf(String.valueOf(row[0]));
                int period = row[1] != null ? Integer.valueOf(String.valueOf(row[1])) : 0;
                List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, year, period);
                String pcommonColumn = common.get(0);
                String commonHeader = common.get(1);
                String commonDoubleColumn = StringUtils.EMPTY;
                String column = "";
                pcommonColumn=getCommonColumn(frequencyDivision,pcommonColumn);
                if (periodList.contains(pcommonColumn)) {
                    periodList.remove(pcommonColumn);
                    List<String> columnList = new ArrayList<>(pvsdto.getColumns());
                    columnList.remove("group");
                    ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                    projDTO.setGroup(commonHeader);
                    if (pvsdto.isVarExFacSales()) {
                        if (pvsdto.isColValue()) {
                            column = "ExFacValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[2];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarExFacCustomer()) {
                        if (pvsdto.isColValue()) {
                            column = "CustExFacValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[28];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarDemandSales()) {
                        if (pvsdto.isColValue()) {
                            column = "DemandSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[16];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarAdjDemand()) {
                        if (pvsdto.isColValue()) {
                            column = "AdjDemandValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[27];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarInvSales()) {
                        if (pvsdto.isColValue()) {
                            column = "InvWithValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[17];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarIwDetails()) {
                        if (pvsdto.isColValue()) {
                            column = "InvWithDetailsValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[29];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarPerExFacSales()) {
                        if (pvsdto.isColValue()) {
                            column = "PerExFacValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[7];
                            String baseValue = getFormattedValue(RATE_PER, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarPerExFacCustomer()) {
                        if (pvsdto.isColValue()) {
                            column = "PerCustExFacValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[31];
                            String baseValue = getFormattedValue(RATE_PER, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarPerDemandSales()) {
                        if (pvsdto.isColValue()) {
                            column = "PerDemandSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[18];
                            String baseValue = getFormattedValue(RATE_PER, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarPerAdjDemand()) {
                        if (pvsdto.isColValue()) {
                            column = "PerAdjDemandValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[30];
                            String baseValue = getFormattedValue(RATE_PER, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarPerInvSales()) {
                        if (pvsdto.isColValue()) {
                            column = "PerInvWithValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[19];
                            String baseValue = getFormattedValue(RATE_PER, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarPerIwDetails()) {
                        if (pvsdto.isColValue()) {
                            column = "PerInvWithDetailsValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[32];
                            String baseValue = getFormattedValue(RATE_PER, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarContractsales()) {
                        if (pvsdto.isColValue()) {
                            column = "ContractSalesWACValue" + CURRENT + pvsdto.getCurrentProjId();
                           // String currentSales = StringUtils.EMPTY + row[3];
                          //  String baseValue = getFormattedValue(AMOUNT, currentSales);
                          String baseValue=getCellValue(row[3], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarContractUnits()) {
                        if (pvsdto.isColValue()) {
                            column = "ContractUnitsValue" + CURRENT + pvsdto.getCurrentProjId();
                          //  String currentSales = StringUtils.EMPTY + row[4];
                           // String baseValue = getFormattedValue(AMOUNT, currentSales);
                            String baseValue=getCellValue(row[4], "ContractUnits");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarDisRate()) {
                        if (pvsdto.isColValue()) {
                            column = "DiscountSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                           // String currentSales = StringUtils.EMPTY + row[6];
                            //String baseValue = getFormattedValue(RATE, currentSales);
                           String baseValue =getCellValue(row[6], "Rate");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarDisAmount()) {
                        if (pvsdto.isColValue()) {
                            column = "DiscountAmountValue" + CURRENT + pvsdto.getCurrentProjId();
                           // String currentSales = StringUtils.EMPTY + row[5];
                           // String baseValue = getFormattedValue(AMOUNT, currentSales);
                           String baseValue =getCellValue(row[5], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarRPU()) {
                        if (pvsdto.isColValue()) {
                            column = "RPUValue" + CURRENT + pvsdto.getCurrentProjId();
                          //  String currentSales = StringUtils.EMPTY + row[20];
                         //   String baseValue = getFormattedValue(AMOUNT, currentSales);
                              String baseValue =getCellValue(row[20], "Amount"); 
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarNetSales()) {
                        if (pvsdto.isColValue()) {
                            column = "NetSalesValue" + CURRENT + pvsdto.getCurrentProjId();
//                            String currentSales = StringUtils.EMPTY + row[14];
                           // String baseValue = getFormattedValue(AMOUNT, currentSales);
                           String baseValue=getCellValue(row[14], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarCOGC()) {
                        if (pvsdto.isColValue()) {
                            column = "COGCValue" + CURRENT + pvsdto.getCurrentProjId();
                            //String currentSales = StringUtils.EMPTY + row[23];
                          //  String baseValue = getFormattedValue(AMOUNT, currentSales);
                          String baseValue=getCellValue(row[23], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarNetProfit()) {
                        if (pvsdto.isColValue()) {
                            column = "NetProfitValue" + CURRENT + pvsdto.getCurrentProjId();
                           // String currentSales = StringUtils.EMPTY + row[24];
                           // String baseValue = getFormattedValue(AMOUNT, currentSales);
                            String baseValue=getCellValue(row[24], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    for (int j = 0; j < priorProjGtsList.size(); j++) {
                        //ExFac Product
                        if (pvsdto.isVarExFacSales()) {
                            String value1 = StringUtils.EMPTY + row[2 + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "ExFacValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "ExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[2 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[2])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "ExFacPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[2 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //ExFac Customer
                        if (pvsdto.isVarExFacCustomer()) {
                            String value1 = StringUtils.EMPTY + row[28 + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "CustExFacValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "CustExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[28 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[27])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "CustExFacPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[28 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[27])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Demand
                        if (pvsdto.isVarDemandSales()) {
                            String value1 = StringUtils.EMPTY + row[16 + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "DemandSalesValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "DemandSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[16 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[15])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "DemandSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[16 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[15])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        // Adjusted Demand
                        if (pvsdto.isVarAdjDemand()) {
                            String value1 = StringUtils.EMPTY + row[27 + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "AdjDemandValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "AdjDemandVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[27 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[26])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "AdjDemandPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[27 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[26])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Inventory Summary
                        if (pvsdto.isVarInvSales()) {
                            String value1 = StringUtils.EMPTY + row[17 + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "InvWithValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "InvWithVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[17 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[16])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "InvWithPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[17 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[16])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Inventory Detials 
                        if (pvsdto.isVarIwDetails()) {
                            String value1 = StringUtils.EMPTY + row[29 + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "InvWithDetailsValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "InvWithDetailsVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[29 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[28])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "InvWithDetailsPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[29 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[28])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Percentage of Ex Fac Product
                        if (pvsdto.isVarPerExFacSales()) {
                            String value1 = StringUtils.EMPTY + row[7 + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "PerExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[7 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[7])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE_PER, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerExFacPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[7 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[7])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Percentage of Ex Fac customer
                        if (pvsdto.isVarPerExFacCustomer()) {
                            String value1 = StringUtils.EMPTY + row[31 + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "PerCustExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerCustExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[31 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[30])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE_PER, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerCustExFacPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[31 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[30])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Percentage of Demand
                        if (pvsdto.isVarPerDemandSales()) {
                            String value1 = StringUtils.EMPTY + row[18 + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "PerDemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerDemandSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[18 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[17])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE_PER, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerDemandSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[18 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[17])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Percentage of Demand
                        if (pvsdto.isVarPerAdjDemand()) {
                            String value1 = StringUtils.EMPTY + row[30 + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "PerAdjDemandValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerAdjDemandSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[30 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[29])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE_PER, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerAdjDemandSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[30 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[29])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Percentage of Inv Summary
                        if (pvsdto.isVarPerInvSales()) {
                            String value1 = StringUtils.EMPTY + row[19 + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "PerInvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerInvWithVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[19 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[18])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE_PER, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerInvWithPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[19 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[18])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Percentage of Inv Details
                        if (pvsdto.isVarPerIwDetails()) {
                            String value1 = StringUtils.EMPTY + row[32 + ((j + 1) * prPos)];
                            if (pvsdto.isColValue()) {
                                column = "PerInvWithDetailsValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "PerInvWithDetailsVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[32 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[31])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE_PER, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "PerInvWithDetailsPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[32 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[31])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Contract Sales
                        if (pvsdto.isVarContractsales()) {
                           // String value1 = StringUtils.EMPTY + row[3 + ((j + 1) * prPos)];
                            String baseValue1=getCellValue(row[3 + ((j + 1) * prPos)], "Amount");
                            if (pvsdto.isColValue()) {
                                column = "ContractSalesWACValue" + priorProjGtsList.get(j);
                               // String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue1);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "ContractSalesWACVar" + priorProjGtsList.get(j);
                               if(row[3 + ((j + 1) * prPos)]==null && row[3]==null){
                                    if (pvsdto.hasColumn(column)) {
                                   projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                    }
                               }else{
                                String priorVal = StringUtils.EMPTY + row[3 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[3])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                               }
                            }
                            if (pvsdto.isColPercentage()) {
                                commonDoubleColumn = "ContractSalesWACVarPer" + priorProjGtsList.get(j);
                                if(row[3 + ((j + 1) * prPos)]==null && row[3]==null){
                                   if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(commonDoubleColumn, StringUtils.EMPTY);
                                    columnList.remove(commonDoubleColumn);
                                   }
                                }else{
                                String priorVal = StringUtils.EMPTY + row[3 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(commonDoubleColumn)) {
                                    projDTO.addStringProperties(commonDoubleColumn, baseValue + PERCENT);
                                    columnList.remove(commonDoubleColumn);
                                }
                            }
                            }
                        }
                        //Contract Units
                        if (pvsdto.isVarContractUnits()) {
                           // String value1 = StringUtils.EMPTY + row[4 + ((j + 1) * prPos)];
                           String baseValue1=getCellValue(row[4 + ((j + 1) * prPos)], "ContractUnits");
                            if (pvsdto.isColValue()) {
                                column = "ContractUnitsValue" + priorProjGtsList.get(j);
                             //   String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue1);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "ContractUnitsVar" + priorProjGtsList.get(j);
                               if(row[4 + ((j + 1) * prPos)]==null && row[4]==null){
                                  if (pvsdto.hasColumn(column)) { 
                                   projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                  }
                               }else{
                                String priorVal = StringUtils.EMPTY + row[4 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[4])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT_UNITS, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                               }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "ContractUnitsPer" + priorProjGtsList.get(j);
                                if(row[4 + ((j + 1) * prPos)]==null && row[4]==null){
                                   if (pvsdto.hasColumn(column)) { 
                                    projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                   }
                                }else{
                                String priorVal = StringUtils.EMPTY + row[4 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[4])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        }

                        //Discount Percentage
                        if (pvsdto.isVarDisRate()) {
                          //  String value1 = StringUtils.EMPTY + row[6 + ((j + 1) * prPos)];
                          String baseValue1 =getCellValue(row[6 + ((j + 1) * prPos)], "Rate");
                            if (pvsdto.isColValue()) {
                                column = "DiscountSalesValue" + priorProjGtsList.get(j);
                              //  String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue1);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "DiscountSalesVar" + priorProjGtsList.get(j);
                                if(row[6 + ((j + 1) * prPos)]==null && row[6]==null){
                                   if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                   }
                                }else{
                                String priorVal = StringUtils.EMPTY + row[6 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[6])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                                }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "DiscountSalesPer" + priorProjGtsList.get(j);
                              if(row[6 + ((j + 1) * prPos)]==null &&row[6]==null ){
                                  if (pvsdto.hasColumn(column)) {
                                  projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                  }
                              }else{
                                String priorVal = StringUtils.EMPTY + row[6 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[6])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        }
                        //Discount Dollar
                        if (pvsdto.isVarDisAmount()) {
                         //   String value1 = StringUtils.EMPTY + row[5 + ((j + 1) * prPos)];
                         String baseValue1 =getCellValue(row[5 + ((j + 1) * prPos)], "Amount");
                            if (pvsdto.isColValue()) {
                                column = "DiscountAmountValue" + priorProjGtsList.get(j);;
                             //   String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue1);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "DiscountAmountVar" + priorProjGtsList.get(j);
                                if(row[5 + ((j + 1) * prPos)]==null&& row[5]==null){
                                    if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                    }
                                }else{
                                String priorVal = StringUtils.EMPTY + row[5 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[5])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "DiscountAmountPer" + priorProjGtsList.get(j);
                               if(row[5 + ((j + 1) * prPos)]==null && row[5]==null){
                                   if (pvsdto.hasColumn(column)) {
                                   projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                   }
                               }else{
                                String priorVal = StringUtils.EMPTY + row[5 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[5])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        }
                        //RPU
                        if (pvsdto.isVarRPU()) {
                       //     String value1 = StringUtils.EMPTY + row[20 + ((j + 1) * prPos)];
                            String baseValue1 =getCellValue(row[20 + ((j + 1) * prPos)], "Amount");
                            if (pvsdto.isColValue()) {
                                column = "RPUValue" + priorProjGtsList.get(j);;
                              //  String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue1);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "RPUVariance" + priorProjGtsList.get(j);
                               if(row[20 + ((j + 1) * prPos)]==null && row[19]==null){
                                   if (pvsdto.hasColumn(column)) {
                                   projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                   }
                               }else{
                                String priorVal = StringUtils.EMPTY + row[20 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[19])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                               }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "RPUPer" + priorProjGtsList.get(j);
                               if(row[20 + ((j + 1) * prPos)]==null && row[19]==null){
                                   if (pvsdto.hasColumn(column)) {
                                   projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                   }
                               }else{
                                String priorVal = StringUtils.EMPTY + row[20 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[19])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        }
                        //Netsales
                        if (pvsdto.isVarNetSales()) {
                           // String value1 = StringUtils.EMPTY + row[14 + ((j + 1) * prPos)];
                           String baseValue1 =getCellValue(row[14 + ((j + 1) * prPos)], "Amount");
                            if (pvsdto.isColValue()) {
                                column = "NetSalesValue" + priorProjGtsList.get(j);;
                               // String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue1);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "NetSalesVariance" + priorProjGtsList.get(j);
                              if(row[14 + ((j + 1) * prPos)]==null && row[14]==null){
                                  if (pvsdto.hasColumn(column)) {
                                  projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column); 
                                  }
                              }else{
                                String priorVal = StringUtils.EMPTY + row[14 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[14])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                              }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "NetSalesPer" + priorProjGtsList.get(j);
                              if(row[14 + ((j + 1) * prPos)]==null && row[14]==null){
                                   if (pvsdto.hasColumn(column)) { 
                                  projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                   }
                              }else{
                                String priorVal = StringUtils.EMPTY + row[14 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[14])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        }
                        //COGC
                        if (pvsdto.isVarCOGC()) {
                           // String value1 = StringUtils.EMPTY + row[23 + ((j + 1) * prPos)];
                            String baseValue1 =getCellValue(row[23 + ((j + 1) * prPos)], "Amount");
                            if (pvsdto.isColValue()) {
                                column = "COGCValue" + priorProjGtsList.get(j);;
                             //   String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue1);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "COGCVariance" + priorProjGtsList.get(j);
                              if(row[23 + ((j + 1) * prPos)]==null && row[22]==null){
                                   if (pvsdto.hasColumn(column)) {
                                  projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                   }
                              }else{
                                String priorVal = StringUtils.EMPTY + row[23 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[22])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                              }
                            }
                            if (pvsdto.isColPercentage()) {
                                column = "COGCPer" + priorProjGtsList.get(j);
                              if(row[23 + ((j + 1) * prPos)]==null && row[22]==null){
                                   if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                   }
                              }else{
                                String priorVal = StringUtils.EMPTY + row[23 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[22])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        }
                        //Net Profite
                        if (pvsdto.isVarNetProfit()) {
                          //  String value1 = StringUtils.EMPTY + row[24 + ((j + 1) * prPos)];
                           String baseValue1=getCellValue(row[24 + ((j + 1) * prPos)], "Amount");
                            if (pvsdto.isColValue()) {
                                column = "NetProfitValue" + priorProjGtsList.get(j);;
                              //  String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue1);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "NetProfitVariance" + priorProjGtsList.get(j);
                                if(row[24 + ((j + 1) * prPos)]==null && row[23]==null){
                                    if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                }
                                }else{
                                String priorVal = StringUtils.EMPTY + row[24 + ((j + 1) * prPos)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[23])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "NetProfitPer" + priorProjGtsList.get(j);
                               if(row[24 + ((j + 1) * prPos)]==null && row[23]==null){
                                   if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, StringUtils.EMPTY);
                                    columnList.remove(column);
                                }
                               }else{
                                String priorVal = StringUtils.EMPTY + row[24 + ((j + 1) * prPos)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[23])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        }
                    }
                    if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {

                        // Individual discount level
                        Set<String> noOfDiscount = new HashSet<String>();
                        for (Object discountsName : totalDiscount) {
                            final Object[] disc = (Object[]) discountsName;
                            if (disc[2] != null) {
                                noOfDiscount.add(String.valueOf(disc[2]));
                            }
                        }
                      //  System.out.println("==total doiscount size================"+totalDiscount.size());
                        for (int dis = 0; dis < totalDiscount.size(); dis++) {
                            discountIndex = dis;
                            Object[] discountRow = (Object[]) totalDiscount.get(dis);
                            int dyear = Integer.valueOf(String.valueOf(discountRow[0]));
                            int dperiod = Integer.valueOf(String.valueOf(discountRow[1]));
                            List<String> dcommon = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, dyear, dperiod);
                            String dcommonColumn = dcommon.get(0);
                          //  System.out.println("===============dcommonColumn============="+dcommonColumn);
                           // System.out.println("===================P comm column========="+pcommonColumn);
                           dcommonColumn= getCommonColumn(dcommonColumn,frequencyDivision);
                         //  System.out.println("===============dcommonColumn============="+dcommonColumn);
                         //   System.out.println("===================P comm column========="+pcommonColumn);
                            if (pcommonColumn.equals(dcommonColumn)) {
                                for (int disc = 0; disc < noOfDiscount.size(); disc++) {
                                    String head = String.valueOf(discountRow[2]).replace(" ", StringUtils.EMPTY) + disc;
                                    if (pvsdto.isVarDisAmount()) {
                                        if (pvsdto.isColValue()) {
                                            column = "DiscountAmountValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                           
                                           // String currentSales = StringUtils.EMPTY + discountRow[3];
                                          //  String baseValue = getFormattedValue(AMOUNT, currentSales);
                                          //  System.out.println("==discount Vlaue=========="+""+discountRow[3]);
                                          String baseValue =getCellValue(discountRow[3], "Amount");
                                          //  System.out.println("==bae value==========="+baseValue);
                                          //  System.out.println("==column============="+column);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    if (pvsdto.isVarDisRate()) {
                                        if (pvsdto.isColValue()) {
                                            column = "DiscountSalesValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                           // String currentSales = StringUtils.EMPTY + discountRow[4];
                                         //   String baseValue = getFormattedValue(RATE, currentSales);
                                         String baseValue =getCellValue(discountRow[4], "Rate");
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    if (pvsdto.isVarRPU()) {
                                        if (pvsdto.isColValue()) {
                                            column = "RPUValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            //String currentSales = StringUtils.EMPTY + discountRow[5];
                                          //  String baseValue = getFormattedValue(AMOUNT, currentSales);
                                          String baseValue =getCellValue(discountRow[5], "Amount");
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }

                                    for (int k = 0; k < priorProjGtsList.size(); k++) {
                                        //Discount Percentage
                                        if (pvsdto.isVarDisRate()) {
                                            singleColumn[0] = "DiscountSalesValue" + head + priorProjGtsList.get(k);
                                            singleColumn[1] = "DiscountSalesVar" + head + priorProjGtsList.get(k);
                                            singleColumn[2] = "DiscountSalesPer" + head + priorProjGtsList.get(k);
                                            getPivotCommonCustomization(pvsdto, discountRow, projDTO, singleColumn, 4, k, 3, columnList, Boolean.TRUE);
                                        }

                                        //Discount Dollor
                                        if (pvsdto.isVarDisAmount()) {
                                            singleColumn[0] = "DiscountAmountValue" + head + priorProjGtsList.get(k);
                                            singleColumn[1] = "DiscountAmountVar" + head + priorProjGtsList.get(k);
                                            singleColumn[2] = "DiscountAmountPer" + head + priorProjGtsList.get(k);
                                            getPivotCommonCustomization(pvsdto, discountRow, projDTO, singleColumn, 3, k, 3, columnList, Boolean.FALSE);
                                        }
                                        //RPU
                                        if (pvsdto.isVarRPU()) {
                                            singleColumn[0] = "RPUValue" + head + priorProjGtsList.get(k);
                                            singleColumn[1] = "RPUVariance" + head + priorProjGtsList.get(k);
                                            singleColumn[2] = "RPUPer" + head + priorProjGtsList.get(k);
                                            getPivotCommonCustomization(pvsdto, discountRow, projDTO, singleColumn, 5, k, 3, columnList, Boolean.FALSE);
                                        }
                                    }
                                }
                            }
                        }

                        // PPA Total Discount $
                        if (pvsdto.isColValue()) {
                            column = "DiscountAmountValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                           // String currentSales = StringUtils.EMPTY + row[8];
                           // String baseValue = getFormattedValue(AMOUNT, currentSales);
                           String baseValue = getCellValue(row[8], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // PPA Total Discount %
                        if (pvsdto.isColValue()) {
                            column = "DiscountSalesValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                         //   String currentSales = StringUtils.EMPTY + row[9];
                          //  String baseValue = getFormattedValue(RATE, currentSales);
                          String baseValue =getCellValue(row[9], "Rate");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Mandated Total Discount %
                        if (pvsdto.isColValue()) {
                            column = "DiscountSalesValueMANDATED" + CURRENT + pvsdto.getCurrentProjId();
                          //  String currentSales = StringUtils.EMPTY + row[12];
                         //   String baseValue = getFormattedValue(RATE, currentSales);
                         String baseValue =getCellValue(row[12], "Rate");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue );
                                columnList.remove(column);
                            }
                        }
                        // Supplemental Total Discount %
                        if (pvsdto.isColValue()) {
                            column = "DiscountSalesValueSUPPLEMENTAL" + CURRENT + pvsdto.getCurrentProjId();
                            //String currentSales = StringUtils.EMPTY + row[13];
                           // String baseValue = getFormattedValue(RATE, currentSales);
                           String baseValue =getCellValue(row[13], "Rate");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                        // PPA Total Discount RPU
                        if (pvsdto.isColValue()) {
                            column = "RPUValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                           // String currentSales = StringUtils.EMPTY + row[21];
                           // String baseValue = getFormattedValue(AMOUNT, currentSales);
                           String baseValue =getCellValue(row[21], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Returns Total Discount $
                        if (pvsdto.isColValue()) {
                            column = "DiscountAmountValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                          //  String currentSales = StringUtils.EMPTY + row[15];
                           // String baseValue = getFormattedValue(AMOUNT, currentSales);
                           String baseValue =getCellValue(row[15], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Returns Total Discount %
                        if (pvsdto.isColValue()) {
                            column = "DiscountSalesValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                       //     String currentSales = StringUtils.EMPTY + row[25];
                       //     String baseValue = getFormattedValue(AMOUNT, currentSales);
                                 String baseValue =getCellValue(row[25], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                        // Returns Total Discount RPU
                        if (pvsdto.isColValue()) {
                            column = "RPUValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                           // String currentSales = StringUtils.EMPTY + row[26];
                           // String baseValue = getFormattedValue(AMOUNT, currentSales);
                            String baseValue =getCellValue(row[26], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Mandated Total Discount $
                        if (pvsdto.isColValue()) {
                            column = "DiscountAmountValueMANDATED" + CURRENT + pvsdto.getCurrentProjId();
                            //String currentSales = StringUtils.EMPTY + row[10];
                           // String baseValue = getFormattedValue(AMOUNT, currentSales);
                           String baseValue =getCellValue(row[10], "Amount");
                            if (pvsdto.hasColumn(column)) {
                                if(baseValue.equals(StringUtils.EMPTY)){
                                     projDTO.addStringProperties(column, baseValue);
                                }else{
                                     projDTO.addStringProperties(column, baseValue + PERCENT);
                                }
                                columnList.remove(column);
                            }
                        }
                        // Supplemental Total Discount $
                        if (pvsdto.isColValue()) {
                            column = "DiscountAmountValueSUPPLEMENTAL" + CURRENT + pvsdto.getCurrentProjId();
                           // String currentSales = StringUtils.EMPTY + row[11];
                          //  String baseValue = getFormattedValue(AMOUNT, currentSales);
                          String baseValue =getCellValue(row[11], "Amount");
                            if (pvsdto.hasColumn(column)) {
                               if(baseValue.equals(StringUtils.EMPTY)){
                                   projDTO.addStringProperties(column, baseValue);
                               }else{
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                               }
                                columnList.remove(column);
                            }
                        }
                        // MANDATED Total Discount $
                        if (pvsdto.isColValue()) {
                            column = "RPUValueMANDATED" + CURRENT + pvsdto.getCurrentProjId();
//                            String currentSales = StringUtils.EMPTY + row[9];
                            String currentSales = "null";
                          //  String baseValue = getFormattedValue(RATE, currentSales);
                          String baseValue =getCellValue(row[9], "Rate");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Supplemental Total Discount $
                        if (pvsdto.isColValue()) {
                            column = "RPUValueSUPPLEMENTAL" + CURRENT + pvsdto.getCurrentProjId();
//                            String currentSales = StringUtils.EMPTY + row[9];
                            String currentSales = "null";
                        //    String baseValue = getFormattedValue(RATE, currentSales);
                             String baseValue =getCellValue(row[9], "Rate");
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                        System.out.println("====================proir gts list size===========>>>"+priorProjGtsList.size());
                        for (int j = 0; j < priorProjGtsList.size(); j++) {
                            //Discount Percentage
                            if (pvsdto.isVarDisRate()) {
                                //PPA
                                singleColumn[0] = "DiscountSalesValuePPA" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountSalesVarPPA" + priorProjGtsList.get(j);
                                singleColumn[2] = "DiscountSalesPerPPA" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 8, j, prPos, columnList, Boolean.TRUE);
                                //Returns
                                singleColumn[0] = "DiscountSalesValueReturns" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountSalesVarReturns" + priorProjGtsList.get(j);
                                singleColumn[2] = "DiscountSalesPerReturns" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 25, j, prPos, columnList, Boolean.TRUE);
                                //Mandated
                                singleColumn[0] = "DiscountSalesValueMANDATED" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountSalesVarMANDATED" + priorProjGtsList.get(j);
                                singleColumn[2] = "DiscountSalesPerMANDATED" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 12, j, prPos, columnList, Boolean.TRUE);
                                //Supplemental
                                singleColumn[0] = "DiscountSalesValueSUPPLEMENTAL" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountSalesVarSUPPLEMENTAL" + priorProjGtsList.get(j);
                                singleColumn[2] = "DiscountSalesPerSUPPLEMENTAL" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 13, j, prPos, columnList, Boolean.TRUE);
                            }
                            //Discount Dollar
                            if (pvsdto.isVarDisAmount()) {
                                //PPA
                                singleColumn[0] = "DiscountAmountValuePPA" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountAmountVarPPA" + priorProjGtsList.get(j);
                                singleColumn[2] = "DiscountAmountPerPPA" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 9, j, prPos, columnList, Boolean.FALSE);
                                //Returns
                                singleColumn[0] = "DiscountAmountValueReturns" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountAmountVarReturns" + priorProjGtsList.get(j);
                                singleColumn[2] = "DiscountAmountPerReturns" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 15, j, prPos, columnList, Boolean.FALSE);
                                //Mandated
                                singleColumn[0] = "DiscountAmountValueMANDATED" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountAmountVarMANDATED" + priorProjGtsList.get(j);
                                singleColumn[2] = "DiscountAmountPerMANDATED" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 10, j, prPos, columnList, Boolean.FALSE);
                                //Supplemental
                                singleColumn[0] = "DiscountAmountValueSUPPLEMENTAL" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountAmountVarSUPPLEMENTAL" + priorProjGtsList.get(j);
                                singleColumn[2] = "DiscountAmountPerSUPPLEMENTAL" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 11, j, prPos, columnList, Boolean.FALSE);
                            }
                            //RPU
                            if (pvsdto.isVarRPU()) {
                                //PPA
                                singleColumn[0] = "RPUValuePPA" + priorProjGtsList.get(j);
                                singleColumn[1] = "RPUVariancePPA" + priorProjGtsList.get(j);
                                singleColumn[2] = "RPUPerPPA" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 19, j, prPos, columnList, Boolean.FALSE);
                                //Returns
                                singleColumn[0] = "RPUValueReturns" + priorProjGtsList.get(j);
                                singleColumn[1] = "RPUVarianceReturns" + priorProjGtsList.get(j);
                                singleColumn[2] = "RPUPerReturns" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 26, j, prPos, columnList, Boolean.FALSE);
                                //Mandated
                                singleColumn[0] = "RPUValueMANDATED" + priorProjGtsList.get(j);
                                singleColumn[1] = "RPUVarianceMANDATED" + priorProjGtsList.get(j);
                                singleColumn[2] = "RPUPerMANDATED" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 19, j, prPos, columnList, Boolean.FALSE);
                                //Supplemental
                                singleColumn[0] = "RPUValueSUPPLEMENTAL" + priorProjGtsList.get(j);
                                singleColumn[1] = "RPUVarianceSUPPLEMENTAL" + priorProjGtsList.get(j);
                                singleColumn[2] = "RPUPerSUPPLEMENTAL" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 25, j, prPos, columnList, Boolean.FALSE);

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
       // String value1 = StringUtils.EMPTY + row[index + ((priorIndex + 1) * column)];
             if (pvsdto.isColValue()) {
                 System.out.println("=inside one=================");
            if(row[index + ((priorIndex + 1) * column)]==null){
                System.out.println("==inside null===================");
                visibleColumn = columns[0];
            if (pvsdto.hasColumn(visibleColumn)) {
                projDTO.addStringProperties(visibleColumn, StringUtils.EMPTY);
                columnList.remove(visibleColumn);
            }
        }else{
                System.out.println("==inside else value=================");
                String value1 = StringUtils.EMPTY + row[index + ((priorIndex + 1) * column)];
            visibleColumn = columns[0];
            String baseValue = getFormattedValue(AMOUNT, value1);
            if (pvsdto.hasColumn(visibleColumn)) {
                projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                columnList.remove(visibleColumn);
            }
            }
        }
        if (pvsdto.isColVariance()) {
            System.out.println("===============inside variance==============");
            visibleColumn = columns[1];
           if(row[index + ((priorIndex + 1) * column)]==null && row[index]==null){
               System.out.println("==variance nulll================");
               if (pvsdto.hasColumn(visibleColumn)) {
                projDTO.addStringProperties(visibleColumn, StringUtils.EMPTY);
                columnList.remove(visibleColumn);
            } 
           }else{
               System.out.println("==inside veariance else======================");
               String priorVal = StringUtils.EMPTY + row[index + ((priorIndex + 1) * column)];
            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index])) - Double.valueOf(isNull(priorVal)));
            String baseValue = getFormattedValue(AMOUNT, variance);
            if (pvsdto.hasColumn(visibleColumn)) {
                projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                columnList.remove(visibleColumn);
            }
           }
        }
        if (pvsdto.isColPercentage()) {
            visibleColumn = columns[2];
            if(row[index + ((priorIndex + 1) * column)]==null && row[index]==null){
                 if (pvsdto.hasColumn(visibleColumn)) {
                projDTO.addStringProperties(visibleColumn, StringUtils.EMPTY);
                columnList.remove(visibleColumn);
            }
            }else{
            String priorVal = StringUtils.EMPTY + row[index + ((priorIndex + 1) * column)];
            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                perChange = 0.0;
            }
            String change = String.valueOf(perChange);
            String baseValue = getFormattedValue(RATE, change);
            if (pvsdto.hasColumn(visibleColumn)) {
                projDTO.addStringProperties(visibleColumn, baseValue + PERCENT);
                columnList.remove(visibleColumn);
            }
            }
        }
    }

    public List<ProjectionVarianceDTO> getCustomizedPivotDetailResults(final List gtsList, final List results, List discountList, List<String> discountName, ProjectionVarianceDTO parentDto, List<Integer> priorProjGtsList, PVSelectionDTO pvsdto) {
        List<String> periodList = new ArrayList<String>(pvsdto.getPeriodList());
        Map<String, String> periodListMap = new HashMap<String, String>(pvsdto.getPeriodListMap());
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        boolean isGts = false;
        int discountIndex = 0;
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
                    if (pvsdto.isVarExFacSales()) {
                        if (pvsdto.isColValue()) {
                            column = "ExFacValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentGts = NULL.getConstant();
                            if (isGts) {
                                currentGts = String.valueOf(gtsRow[2]);
                            }
                            String baseValue = getFormattedValue(AMOUNT, currentGts);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarDemandSales()) {
                        if (pvsdto.isColValue()) {
                            column = "DemandSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentGts = NULL.getConstant();
                            if (isGts) {
                                currentGts = String.valueOf(gtsRow[3]);
                            }
                            String baseValue = getFormattedValue(AMOUNT, currentGts);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarInvSales()) {
                        if (pvsdto.isColValue()) {
                            column = "InvWithValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentGts = NULL.getConstant();
                            if (isGts) {
                                currentGts = String.valueOf(gtsRow[4]);
                            }
                            String baseValue = getFormattedValue(AMOUNT, currentGts);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarPerExFacSales()) {
                        if (pvsdto.isColValue()) {
                            column = "PerExFacValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentPob = NULL.getConstant();
                            if (isGts) {
                                currentPob = String.valueOf(gtsRow[5]);
                            }
                            String baseValue = getFormattedValue(RATE, currentPob);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarPerDemandSales()) {
                        if (pvsdto.isColValue()) {
                            column = "PerDemandSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentPob = NULL.getConstant();
                            if (isGts) {
                                currentPob = String.valueOf(gtsRow[6]);
                            }
                            String baseValue = getFormattedValue(RATE, currentPob);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarPerInvSales()) {
                        if (pvsdto.isColValue()) {
                            column = "PerInvWithValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentPob = NULL.getConstant();
                            if (isGts) {
                                currentPob = String.valueOf(gtsRow[7]);
                            }
                            String baseValue = getFormattedValue(RATE, currentPob);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarContractsales()) {
                        if (pvsdto.isColValue()) {
                            column = "ContractSalesWACValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[2];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarContractUnits()) {
                        if (pvsdto.isColValue()) {
                            column = "ContractUnitsValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[3];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarDisRate()) {
                        if (pvsdto.isColValue()) {
                            column = "DiscountSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[4];
                            String baseValue = getFormattedValue(RATE, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarDisAmount()) {
                        if (pvsdto.isColValue()) {
                            column = "DiscountAmountValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[5];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarRPU()) {
                        if (pvsdto.isColValue()) {
                            column = "RPUValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[7];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarNetSales()) {
                        if (pvsdto.isColValue()) {
                            column = "NetSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[6];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarCOGC()) {
                        if (pvsdto.isColValue()) {
                            column = "COGCValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[8];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (pvsdto.isVarNetProfit()) {
                        if (pvsdto.isColValue()) {
                            column = "NetProfitValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[9];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    for (int j = 0; j < priorProjGtsList.size(); j++) {
                        //Ex Fact
                        if (pvsdto.isVarExFacSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[2 + ((j + 1) * 21)]);
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
                                    priorVal = String.valueOf(gtsRow[2 + ((j + 1) * 21)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[2])) - Double.valueOf(isNull(priorVal)));
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
                                    priorVal = String.valueOf(gtsRow[2 + ((j + 1) * 21)]);
                                }
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                                value1 = String.valueOf(gtsRow[3 + ((j + 1) * 21)]);
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
                                    priorVal = String.valueOf(gtsRow[3 + ((j + 1) * 21)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[3])) - Double.valueOf(isNull(priorVal)));
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
                                    priorVal = String.valueOf(gtsRow[3 + ((j + 1) * 21)]);
                                }
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                                value1 = String.valueOf(gtsRow[4 + ((j + 1) * 21)]);
                            }
                            if (pvsdto.isColValue()) {
                                column = "InvWithValue" + priorProjGtsList.get(j);
//                                value = StringUtils.EMPTY + row[2];
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
                                    priorVal = String.valueOf(gtsRow[4 + ((j + 1) * 21)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[4])) - Double.valueOf(isNull(priorVal)));
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
                                    priorVal = String.valueOf(gtsRow[4 + ((j + 1) * 21)]);
                                }
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[4])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                                value1 = String.valueOf(gtsRow[5 + ((j + 1) * 21)]);
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
                                    priorVal = String.valueOf(gtsRow[5 + ((j + 1) * 21)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[5])) - Double.valueOf(isNull(priorVal)));
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
                                    priorVal = String.valueOf(gtsRow[5 + ((j + 1) * 21)]);
                                }
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[5])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                                value1 = String.valueOf(gtsRow[6 + ((j + 1) * 21)]);
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
                                    priorVal = String.valueOf(gtsRow[6 + ((j + 1) * 21)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[6])) - Double.valueOf(isNull(priorVal)));
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
                                    priorVal = String.valueOf(gtsRow[6 + ((j + 1) * 21)]);
                                }
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[6])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                                value1 = String.valueOf(gtsRow[7 + ((j + 1) * 21)]);
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
                                    priorVal = String.valueOf(gtsRow[7 + ((j + 1) * 21)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[7])) - Double.valueOf(isNull(priorVal)));
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
                                    priorVal = String.valueOf(gtsRow[7 + ((j + 1) * 21)]);
                                }
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[7])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                            String priorValue = StringUtils.EMPTY + row[2 + ((j + 1) * 8)];
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
                                String priorVal = StringUtils.EMPTY + row[2 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[2])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "ContractSalesWACVarPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[2 + ((j + 1) * 8)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                            String value1 = StringUtils.EMPTY + row[3 + ((j + 1) * 8)];
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
                                String priorVal = StringUtils.EMPTY + row[3 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[3])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "ContractUnitsPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[3 + ((j + 1) * 8)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                            String value1 = StringUtils.EMPTY + row[4 + ((j + 1) * 8)];
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
                                String priorVal = StringUtils.EMPTY + row[4 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[4])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "DiscountSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[4 + ((j + 1) * 8)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[4])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                            String value1 = StringUtils.EMPTY + row[5 + ((j + 1) * 8)];
                            if (pvsdto.isColValue()) {
                                column = "DiscountAmountValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "DiscountAmountVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[5 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[5])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "DiscountAmountPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[5 + ((j + 1) * 8)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[8])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                            String value1 = StringUtils.EMPTY + row[7 + ((j + 1) * 8)];
                            if (pvsdto.isColValue()) {
                                column = "RPUValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "RPUVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[7 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[7])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "RPUPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[7 + ((j + 1) * 8)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[7])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                            String value1 = StringUtils.EMPTY + row[6 + ((j + 1) * 8)];
                            if (pvsdto.isColValue()) {
                                column = "NetSalesValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "NetSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[6 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[6])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "NetSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[6 + ((j + 1) * 8)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[6])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                            String value1 = StringUtils.EMPTY + row[8 + ((j + 1) * 8)];
                            if (pvsdto.isColValue()) {
                                column = "COGCValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "COGCVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[8 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[8])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "COGCPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[8 + ((j + 1) * 8)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[8])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                            String value1 = StringUtils.EMPTY + row[9 + ((j + 1) * 8)];
                            if (pvsdto.isColValue()) {
                                column = "NetProfitValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (pvsdto.isColVariance()) {
                                column = "NetProfitVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[9 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[9])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (pvsdto.isColPercentage()) {
                                column = "NetProfitPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[9 + ((j + 1) * 8)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[9])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                            if (disc[2] != null) {
                                noOfDiscount.add(String.valueOf(disc[2]));
                            }
                        }
                        boolean start = false;
                        for (int j = 0; j < discountList.size(); j++) {
                            discountIndex = j;
                            Object[] discountRow = (Object[]) discountList.get(j);
                            int dyear = Integer.valueOf(String.valueOf(discountRow[0]));
                            int dperiod = Integer.valueOf(String.valueOf(discountRow[1]));
                            List<String> dcommon = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, dyear, dperiod);
                            String dcommonColumn = dcommon.get(0);
                            if (pcommonColumn.equals(dcommonColumn)) {
                                for (int disc = 0; disc < noOfDiscount.size(); disc++) {
                                    String head = String.valueOf(discountRow[2]).replace(" ", StringUtils.EMPTY) + disc;
                                    if (pvsdto.isVarDisAmount()) {
                                        if (pvsdto.isColValue()) {
                                            column = "DiscountAmountValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[3];
                                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    if (pvsdto.isVarDisRate()) {
                                        if (pvsdto.isColValue()) {
                                            column = "DiscountSalesValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[6];
                                            String baseValue = getFormattedValue(RATE, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    if (pvsdto.isVarRPU()) {
                                        if (pvsdto.isColValue()) {
                                            column = "RPUValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[9];
                                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }

                                    //  Returns Discount $
                                    if (pvsdto.isVarDisAmount()) {
                                        if (pvsdto.isColValue()) {
                                            column = "DiscountAmountValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[12];
                                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    //  PPA Discount $
                                    if (pvsdto.isVarDisAmount()) {
                                        if (pvsdto.isColValue()) {
                                            column = "DiscountAmountValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[21];
                                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    //  Returns Discount %
                                    if (pvsdto.isVarDisRate()) {
                                        if (pvsdto.isColValue()) {
                                            column = "DiscountSalesValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[15];
                                            String baseValue = getFormattedValue(RATE, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    //  PPA Discount %
                                    if (pvsdto.isVarDisRate()) {
                                        if (pvsdto.isColValue()) {
                                            column = "DiscountSalesValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[24];
                                            String baseValue = getFormattedValue(RATE, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    //  Returns  RPU
                                    if (pvsdto.isVarRPU()) {
                                        if (pvsdto.isColValue()) {
                                            column = "RPUValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[18];
                                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    //  PPA  RPU
                                    if (pvsdto.isVarRPU()) {
                                        if (pvsdto.isColValue()) {
                                            column = "RPUValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[27];
                                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    for (int k = 0; k < priorProjGtsList.size(); k++) {
                                        //Discount Percentage
                                        if (pvsdto.isVarDisRate()) {
                                            String value1 = StringUtils.EMPTY + discountRow[6 + ((k + 1) * 27)];
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
                                                String priorVal = StringUtils.EMPTY + discountRow[7 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "DiscountSalesPer" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[8 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }

                                        // Returns Discount % 
                                        if (pvsdto.isVarDisRate()) {
                                            String value1 = StringUtils.EMPTY + discountRow[15 + ((k + 1) * 27)];
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
                                                String priorVal = StringUtils.EMPTY + discountRow[16 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "DiscountSalesPerReturns" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[17 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }

                                        // PPA Discount % 
                                        if (pvsdto.isVarDisRate()) {
                                            String value1 = StringUtils.EMPTY + discountRow[24 + ((k + 1) * 27)];
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
                                                String priorVal = StringUtils.EMPTY + discountRow[25 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "DiscountSalesPerPPA" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[26 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }

                                        //Discount Dollor
                                        if (pvsdto.isVarDisAmount()) {
                                            String value1 = StringUtils.EMPTY + discountRow[3 + ((k + 1) * 27)];
                                            if (pvsdto.isColValue()) {
                                                column = "DiscountAmountValue" + head + priorProjGtsList.get(k);;
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "DiscountAmountVar" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[4 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "DiscountAmountPer" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[5 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                        // Returns Discount $
                                        if (pvsdto.isVarDisAmount()) {
                                            String value1 = StringUtils.EMPTY + discountRow[12 + ((k + 1) * 27)];
                                            if (pvsdto.isColValue()) {
                                                column = "DiscountAmountValueReturns" + priorProjGtsList.get(k);;
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "DiscountAmountVarReturns" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[13 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "DiscountAmountPerReturns" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[14 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                        // PPA Discount $
                                        if (pvsdto.isVarDisAmount()) {
                                            String value1 = StringUtils.EMPTY + discountRow[21 + ((k + 1) * 27)];
                                            if (pvsdto.isColValue()) {
                                                column = "DiscountAmountValuePPA" + priorProjGtsList.get(k);;
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "DiscountAmountVarPPA" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[22 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "DiscountAmountPerPPA" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[23 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                        //RPU
                                        if (pvsdto.isVarRPU()) {
                                            String value1 = StringUtils.EMPTY + discountRow[9 + ((k + 1) * 27)];
                                            if (pvsdto.isColValue()) {
                                                column = "RPUValue" + head + priorProjGtsList.get(k);;
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "RPUVariance" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[10 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "RPUPer" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[11 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(RATE_PER_THREE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                        // Retruns RPU
                                        if (pvsdto.isVarRPU()) {
                                            String value1 = StringUtils.EMPTY + discountRow[18 + ((k + 1) * 27)];
                                            if (pvsdto.isColValue()) {
                                                column = "RPUValueReturns" + priorProjGtsList.get(k);;
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "RPUVarianceReturns" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[19 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "RPUPerReturns" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[20 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(RATE_PER_THREE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                        // PPA RPU
                                        if (pvsdto.isVarRPU()) {
                                            String value1 = StringUtils.EMPTY + discountRow[27 + ((k + 1) * 27)];
                                            if (pvsdto.isColValue()) {
                                                column = "RPUValuePPA" + priorProjGtsList.get(k);;
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (pvsdto.isColVariance()) {
                                                column = "RPUVariancePPA" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[28 + ((k + 1) * 27)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (pvsdto.isColPercentage()) {
                                                column = "RPUPerPPA" + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[29 + ((k + 1) * 27)];
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
                exFacValue = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, gtsList, 2, 31, 2, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                exFacVar = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, gtsList, 2, 31, 2, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                exFacPer = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, gtsList, 2, 31, 2, 31, pvsdto, RATE,false);
            }
            //Ex fac for detail end

            // Customer ExFac Sales for detail start 
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                custExFacValue = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, gtsList, 28, 31, 28, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                custExFacVar = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, gtsList, 28, 31, 28, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                custExFacPer = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, gtsList, 28, 31, 28, 31, pvsdto, RATE,false);
            }
            // Customer ExFac Sales for detail end 

            //Demand - start
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                demandValue = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, gtsList, 16, 31, 16, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                demandVar = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, gtsList, 16, 31, 16, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                demandPer = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, gtsList, 16, 31, 16, 31, pvsdto, RATE,false);
            }
            //Demand - end

            // Adjusted Demand  start 
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                adjDemandValue = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, gtsList, 27, 31, 27, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                adjDemandVar = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, gtsList, 27, 31, 27, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                adjDemandPer = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, gtsList, 27, 31, 27, 31, pvsdto, RATE,false);
            }
            // Adjusted Demand end 

            //Inv with drawal sale for detail - start
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                invWithValue = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, gtsList, 17, 31, 17, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                invWithVar = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, gtsList, 17, 31, 17, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                invWithPer = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, gtsList, 17, 31, 17, 31, pvsdto, RATE,false);
            }
            //Inv with drawal sale for detail - end

            // Inventary withdrawal Details for detail start 
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                iwDetialsValue = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, gtsList, 29, 31, 29, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                iwDetialsVar = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, gtsList, 29, 31, 29, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                iwDetialsPer = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, gtsList, 29, 31, 29, 31, pvsdto, RATE,false);
            }
            // Inventary withdrawal Details for detail end 

            //Sales for POB
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                salesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, 3, 31, 3, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                salesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, 3, 31, 3, 31, pvsdto, AMOUNT,false);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                salesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, 3, 31, 3, 31, pvsdto, RATE,false);
            }
        }
        ///////////////////////////////////////////////// Total View   

        // ExFac Sales 
        if (pvsdto.isVarExFacSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, dataList, 2, 31, 2, 31, pvsdto, AMOUNT,false);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, dataList, 2, 31, 2, 31, pvsdto, AMOUNT,false);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(), gtsList, dataList, 2, 31, 2, 31, pvsdto, RATE,false);
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
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, 28, 31, 28, 31, pvsdto, AMOUNT,false);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(custExFacValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, 28, 31, 28, 31, pvsdto, AMOUNT,false);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(custExFacVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, 28, 31, 28, 31, pvsdto, RATE,false);
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
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, dataList, 16, 31, 16, 31, pvsdto, AMOUNT,false);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, dataList, 16, 31, 16, 31, pvsdto, AMOUNT,false);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(), gtsList, dataList, 16, 31, 16, 31, pvsdto, RATE,false);
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
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, dataList, 27, 31, 27, 31, pvsdto, AMOUNT,false);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(adjDemandValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, dataList, 27, 31, 27, 31, pvsdto, AMOUNT,false);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(adjDemandVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), gtsList, dataList, 27, 31, 27, 31, pvsdto, RATE,false);
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
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, dataList, 17, 31, 17, 31, pvsdto, AMOUNT,false);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, dataList, 17, 31, 17, 31, pvsdto, AMOUNT,false);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(), gtsList, dataList, 17, 31, 17, 31, pvsdto, RATE,false);
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
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, dataList, 29, 31, 29, 31, pvsdto, AMOUNT,false);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(iwDetialsValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, dataList, 29, 31, 29, 31, pvsdto, AMOUNT,false);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(iwDetialsVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(), gtsList, dataList, 29, 31, 29, 31, pvsdto, RATE,false);
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
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), gtsList, dataList, 7, 31, 7, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), gtsList, dataList, 7, 31, 7, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(), gtsList, dataList, 7, 31, 7, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, 31, 31, 31, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, 31, 31, 31, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(), gtsList, dataList, 31, 31, 31, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(), gtsList, dataList, 18, 31, 18, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(), gtsList, dataList, 18, 31, 18, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(), gtsList, dataList, 18, 31, 18, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), gtsList, dataList, 30, 31, 30, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), gtsList, dataList, 30, 31, 30, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(), gtsList, dataList, 30, 31, 30, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), gtsList, dataList, 19, 31, 19, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), gtsList, dataList, 19, 31, 19, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(), gtsList, dataList, 19, 31, 19, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), gtsList, dataList, 32, 31, 32, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), gtsList, dataList, 32, 31, 32, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(), gtsList, dataList, 32, 31, 32, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(pob);
            }
        }
        //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    System.out.println("==insdie not details============");
                    ProjectionVarianceDTO sales = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, 3, 31, 3, 31, pvsdto, AMOUNT,true);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesValue);
                }
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, 3, 31, 3, 31, pvsdto, AMOUNT,true);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesVar);
                }
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(), gtsList, dataList, 3, 31, 3, 31, pvsdto, RATE,true);
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
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), gtsList, dataList, 4, 31, 4, 31, pvsdto, AMOUNT_UNITS,true);
                projectionVarianceDTO.add(units);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), gtsList, dataList, 4, 31, 4, 31, pvsdto, AMOUNT_UNITS,true);
                projectionVarianceDTO.add(units);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(), gtsList, dataList, 4, 31, 4, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(units);
            }
        }
        //Discount $ 
        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), gtsList, dataList, 5, 31, 5, 31, pvsdto, AMOUNT,true);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), gtsList, dataList, 5, 31, 5, 31, pvsdto, AMOUNT,true);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(), gtsList, dataList, 5, 31, 5, 31, pvsdto, RATE,true);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
        }
        //Discount % 
        if (pvsdto.isVarDisRate()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(), gtsList, dataList, 6, 31, 6, 31, pvsdto, RATE,true);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(), gtsList, dataList, 6, 31, 6, 31, pvsdto, RATE,true);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(), gtsList, dataList, 6, 31, 6, 31, pvsdto, RATE,true);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
        }
        // RPU
        if (pvsdto.isVarRPU()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(), gtsList, dataList, 22, 31, 22, 31, pvsdto, AMOUNT,true);
                netSalesValue = setDataObjects(netSalesValue, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(), gtsList, dataList, 22, 31, 22, 31, pvsdto, AMOUNT,true);
                netSalesVar = setDataObjects(netSalesVar, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(), gtsList, dataList, 22, 31, 22, 31, pvsdto, RATE,true);
                netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(), gtsList, dataList, 14, 31, 14, 31, pvsdto, AMOUNT,true);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(), gtsList, dataList, 14, 31, 14, 31, pvsdto, AMOUNT,true);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(), gtsList, dataList, 14, 31, 14, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(), gtsList, dataList, 23, 31, 23, 31, pvsdto, AMOUNT,true);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(), gtsList, dataList, 23, 31, 23, 31, pvsdto, AMOUNT,true);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(), gtsList, dataList, 23, 31, 23, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            if (pvsdto.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(), gtsList, dataList, 24, 31, 24, 31, pvsdto, AMOUNT,true);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(), gtsList, dataList, 24, 31, 24, 31, pvsdto, AMOUNT,true);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(), gtsList, dataList, 24, 31, 24, 31, pvsdto, RATE,true);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        return projectionVarianceDTO;
    }

    public ProjectionVarianceDTO getCustomisedProjectionResultsDiscount(List<Object> list, String discountName, PVSelectionDTO projSelDTO) {
        LOGGER.info("Inside getCustomisedProjectionResultsDiscount");
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
                    projDTO.setGroup(String.valueOf(discountRow[2]));
                } else if (!discount.equals(discountRow[2].toString())) {
                    if (!discountName.contains("total")) {
                        discountList.remove(discount);
                    }
                    start = false;
                }
                if (start) {
                    discount = discountRow[2] != null ? discountRow[2].toString() : StringUtils.EMPTY;
                    String column = StringUtils.EMPTY;
                    String column1 = "";
                    if (frequencyDivision == 4) {
                        column = "Q" + discountRow[1] + StringUtils.EMPTY + discountRow[0];
                    } else if (frequencyDivision == 2) {
                        column = "S" + discountRow[1] + StringUtils.EMPTY + discountRow[0];
                    } else if (frequencyDivision == 1) {
                        column = StringUtils.EMPTY + discountRow[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(discountRow[1])) - 1);
                        column = monthName + discountRow[0];
                    }
                    column1 = column + CURRENT + projSelDTO.getCurrentProjId();
                    if (projSelDTO.hasColumn(column1)) {
                        String value = "" + discountRow[3];
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
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + discountRow[3 + ((j + 1) * 3)])));
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
        LOGGER.info("Ending getCustomisedProjectionResultsDiscount");
        return projDTO;
    }

    ProjectionVarianceDTO setDataObjects(ProjectionVarianceDTO dto, ProjectionVarianceDTO parentDto, PVSelectionDTO pvsdto) {
        dto.setLevelNo((parentDto.getLevelNo()));
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
        String query = CustomSQLUtil.get("get-projection-names-by-id");
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
        LOGGER.info("Inside  waitForProcedure Method");
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
        LOGGER.info("End of  waitForProcedure  Method");
    }

    /**
     * Ex Factory Sales
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getCommonCustomizedDTO(final String groupName, List<Object> gtsList, List<Object> dataList, final int totalListPostion, final int totalPrPos, final int detailsListPos, final int detailsPrPos, PVSelectionDTO pvsdto, final DecimalFormat FORMAT,boolean inclusionFlag) {
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        try{
        LOGGER.info("Inside getExFactorySales");
        String dollarFormat;
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
//         if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
//             dollarFormat="$0.00";
//        }else{
//             dollarFormat="0.00%";
//        }
//        for (Object nullObj : pvsdto.getRightHeaderPeriod().getSingleColumns()) {
//            pvDTO.addStringProperties(nullObj, "");
//        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (gtsList != null && !gtsList.isEmpty()) {
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                System.out.println("==gts list size================>>>>"+gtsList.size());
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        if (inclusionFlag) {
                            if (obj[totalListPostion] == null) {
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), StringUtils.EMPTY);
                            } else {
                                String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])));
                                String baseValue = getFormattedValue(FORMAT, value);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%")? baseValue+PERCENT : baseValue);
                            }
                        } else {
                            String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])));
                            String baseValue = getFormattedValue(FORMAT, value);
                            pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), groupName.contains("%")? baseValue+PERCENT : baseValue);
                        }

                    }
                    else{
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), "");  
                        }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                           if(obj[totalListPostion + ((j + 1) * totalPrPos)]==null){
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                           }else{
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion + ((j + 1) * totalPrPos)])));
                            String val = getFormattedValue(FORMAT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val+PERCENT : val);
                           }

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            if(obj[totalListPostion + ((j + 1) * totalPrPos)]==null && obj[totalListPostion]==null){
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                            }else{
                            String priorVal = StringUtils.EMPTY + obj[totalListPostion + ((j + 1) * totalPrPos)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(FORMAT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val+PERCENT : val);
                            }

                        } else {
                            if(obj[totalListPostion + ((j + 1) * totalPrPos)]==null && obj[totalListPostion]==null){
                                 pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                            }else{
                            String priorVal = StringUtils.EMPTY + obj[totalListPostion + ((j + 1) * totalPrPos)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[totalListPostion])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(FORMAT, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                            }

                        }
                    }
                }
            } else {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                       if(obj[detailsListPos]==null){
                           pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), StringUtils.EMPTY); 
                       }else{
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos])));
                        String baseValue = getFormattedValue(FORMAT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(),groupName.contains("%") ? baseValue+PERCENT : baseValue);
                       }

                    }else{
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), "");  
                        }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                           if(obj[detailsListPos + ((j + 1) * detailsPrPos)]==null){
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                           }else{
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos + ((j + 1) * detailsPrPos)])));
                            String val = getFormattedValue(FORMAT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), groupName.contains("%") ? val+PERCENT : val);
                           }

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                             if(obj[detailsListPos + ((j + 1) * detailsPrPos)]==null && obj[detailsListPos]==null){
                                  pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                             }else{
                            String priorVal = StringUtils.EMPTY + obj[detailsListPos + ((j + 1) * detailsPrPos)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(FORMAT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j),groupName.contains("%") ? val+PERCENT : val);
                             }

                        } else {
                          if(obj[detailsListPos + ((j + 1) * detailsPrPos)]==null && obj[detailsListPos]==null){
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                          }else{
                            String priorVal = StringUtils.EMPTY + obj[detailsListPos + ((j + 1) * detailsPrPos)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[detailsListPos])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
            }
        }
       
        }catch(Exception e){
        e.printStackTrace();
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
                    if (frequencyDivision == 4) {
                        commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
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
                Double pob = (Double.valueOf(isNull(sales)) / Double.valueOf(isNull(gts)));
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
        LOGGER.info("Inside getCustomisedProjectionResultsTotalDiscount");
        String lastValue = StringUtils.EMPTY;
        List<ProjectionVarianceDTO> resultDto = new ArrayList<>();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        List<Integer> priorList = new ArrayList<>(projSelDTO.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                if (!"".equals(lastValue) && !"null".equals(lastValue) && obj[2] != null && !lastValue.equals(String.valueOf(obj[2]))) {
                    pvDTO.setGroup(lastValue);
                    resultDto.add(pvDTO);
                    pvDTO = new ProjectionVarianceDTO();
                }
                lastValue = String.valueOf(obj[2]);
                pvDTO.setGroup(lastValue);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = "Q" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = "S" + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
               
                if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                    if(obj[index]==null){
                         pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), StringUtils.EMPTY);
                    }else{
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                    String baseValue = getFormattedValue(isPer ? RATE : AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    }
                }
                else{
                    pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? ZERO + PERCENT : ZERO); 
                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                        if(obj[index + ((j + 1) * priorIndex)]==null){
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                        }else{
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * priorIndex)])));
                        String val = getFormattedValue(isPer ? RATE : AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                        }
                    } else if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                       if(obj[index + ((j + 1) * priorIndex)]==null && obj[index]==null){
                           pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                       }else{
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * priorIndex)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(isPer ? RATE : AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                       }
                    } else {
                     if(obj[index + ((j + 1) * priorIndex)]==null &&  obj[index]==null){
                          pvDTO.addStringProperties(commonColumn + priorList.get(j), StringUtils.EMPTY);
                     }else{
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * priorIndex)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                     }
                    }
                }
                if (i == dataList.size() - 1) {
                    resultDto.add(pvDTO);
                }
            }
        }
        LOGGER.info("Ending getCustomisedProjectionResultsTotalDiscount with list size  = = >" + resultDto.size());
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
                discountNamelist.add(String.valueOf(obj[2]));
            }
        }
        discountlist.add(discountNolist);
        discountlist.add(discountNamelist);
        return discountlist;
    }
    
    public static List loadProgramCategory(int projectionId){
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

    private List getComparisonInput(final ComparisonLookupDTO comparisonLookup) throws Exception {
        String ASTERIK = "*";
        String PERCENT = "%";
        List inputList = new ArrayList();
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
        if ((comparisonLookup.getCreatedDateFrom() != null)) {
            SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
            String customSql = "AND PM.ACTIVE_FROM_DATE >= '" + format2.format(comparisonLookup.getCreatedDateFrom()) + "'";
            inputList.add(customSql);
        } else {
            inputList.add(" ");
        }

        if (comparisonLookup.getCreatedDateTo() != null && !"null".equals(comparisonLookup.getCreatedDateTo()) && !"".equals(comparisonLookup.getCreatedDateTo())) {
            SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
            String customSql = "AND PM.ACTIVE_TO_DATE <= '" + format2.format(comparisonLookup.getCreatedDateTo()) + "'";
            inputList.add(customSql);
        } else {
            inputList.add(" ");
        }
        //Fil
        if (comparisonLookup.getWorkflowStatus() != null && !comparisonLookup.getWorkflowStatus().equals(Constants.SELECT_ONE)) {
            if (!comparisonLookup.getWorkflowStatus().equals("Saved")) {
                inputList.add(comparisonLookup.getWorkflowStatus());
            } else {
                inputList.add(PERCENT);
            }
        } else {
            inputList.add(PERCENT);
        }
        if (comparisonLookup.getContract() != null && !comparisonLookup.getContract().isEmpty()) {
            inputList.add(comparisonLookup.getContract().replace(ASTERIK, PERCENT));
        } else {
            inputList.add(PERCENT);
        }
        if (comparisonLookup.getMarketType() != null && !comparisonLookup.getMarketType().isEmpty()) {
            inputList.add(comparisonLookup.getMarketType().replace(ASTERIK, PERCENT));
        } else {
            inputList.add(PERCENT);
        }
        if (comparisonLookup.getCustomer() != null && !comparisonLookup.getCustomer().isEmpty()) {
            inputList.add(comparisonLookup.getCustomer().replace(ASTERIK, PERCENT));
        } else {
            inputList.add(PERCENT);
        }
        if (comparisonLookup.getNdcName() != null && !comparisonLookup.getNdcName().isEmpty()) {
            inputList.add(comparisonLookup.getNdcName().replace(ASTERIK, PERCENT));
        } else {
            inputList.add(PERCENT);
        }
        if (comparisonLookup.getNdcNo() != null && !comparisonLookup.getNdcNo().isEmpty()) {
            inputList.add(comparisonLookup.getNdcNo().replace(ASTERIK, PERCENT));
        } else {
            inputList.add(PERCENT);
        }
        if (comparisonLookup.getBrand() != null && !comparisonLookup.getBrand().isEmpty()) {
            inputList.add(comparisonLookup.getBrand().replace(ASTERIK, PERCENT));
        } else {
            inputList.add(PERCENT);
        }
        StringBuilder filter = AbstractFilterLogic.getInstance().filterQueryGenerator(comparisonLookup.getFilter(), getFilerMap());
       
        if (filter != null && !filter.equals("")) {
            inputList.add(filter.toString());
        } else {
            inputList.add(" ");
        }

        if (!comparisonLookup.getCount()) {
            StringBuilder orderBy = AbstractFilterLogic.getInstance().orderByQueryGenerator(comparisonLookup.getSortColumns(), getOrderByMap());
            inputList.add(orderBy.toString());
            inputList.add(comparisonLookup.getStart());
            inputList.add(comparisonLookup.getOffset());
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
        map.put("projectionName", "PM.CFF_NAME");
        map.put("projectionDescription", "PM.CFF_NAME");
        map.put("marketType", "MARKET_TYPE");
        map.put("customer", "CONTRACT_HOLDER");
        map.put("contract", "CONTRACT");
        map.put("brand", "BRAND");
        map.put("createdDateFrom", "PM.CREATED_DATE");
        map.put("createdBy", "PM.CREATED_BY");
        map.put("createdBy", "PM.CFF_MASTER_SID");
        return map;
    }
    
   private String getCommonColumn(int frequencyDivision,String column)
   {
      String returnString=StringUtils.EMPTY;
       if(frequencyDivision==4){
           returnString=column.replace("q", "Q");
           return returnString;
       }   if(frequencyDivision==2){
           returnString=column.replace("s", "S");
           return returnString;
       } if(frequencyDivision==12){
           returnString=column.toLowerCase();
           return returnString;
       }
      return column;
   }
   
    private String getCellValue(Object obj,String component)
   {
       String returnValue=StringUtils.EMPTY;
       if(obj==null){
           return returnValue;
       }else{
           returnValue = StringUtils.EMPTY + obj;
           if("Amount".equals(component)){
              returnValue= getFormattedValue(AMOUNT, returnValue);  
           }else if("Rate".equals(component)){
               returnValue=getFormattedValue(RATE, returnValue);
               returnValue=returnValue+PERCENT;
           }else if("ContractUnits".equals(component)){
               returnValue=getFormattedValue(AMOUNT_UNITS, returnValue);
           }
          return returnValue;
       }
   }
    
    private  String getCommonColumn(String column,int frequencyDivision){
             if (frequencyDivision == 1) {
          // column=column.replace("q", "Q");
        } else if (frequencyDivision == 4) {
             column=column.replace("q", "Q");
        } else if (frequencyDivision == 2) {
            column=column.replace("s", "S");
        } else if (frequencyDivision == 12) {
           column=column.toLowerCase();
        }
             return column;
    }
    
}
