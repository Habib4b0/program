/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionvariance.logic;

import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;

import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.app.gtnforecasting.dto.PVSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.gtnforecasting.queryUtils.PVQueryUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.Converters;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.jboss.logging.Logger;
import static com.stpl.app.utils.Constants.CommonConstants.NULL;
import static com.stpl.app.utils.Constants.CommonConstants.VALUE;
import static com.stpl.app.utils.Constants.CommonConstants.VARIANCE;
import static com.stpl.app.utils.Constants.ButtonConstants.ALL;
import static com.stpl.app.utils.Constants.FrequencyConstants.DEFAULT_JAVA_DATE_FORMAT;
import static com.stpl.app.utils.Constants.FrequencyConstants.DEFAULT_SQL_DATE_FORMAT;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOM;
import static com.stpl.app.utils.Constants.LabelConstants.PERCENT;
import static com.stpl.app.utils.Constants.LabelConstants.TOTAL;
import static com.stpl.app.utils.Constants.LabelConstants.TOTAL_DISCOUNT;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM_CATEGORY;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Mohamed.hameed
 */
public class NMProjectionVarianceLogic {

    public static final Logger LOGGER = Logger.getLogger(NMProjectionVarianceLogic.class);
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
    public static final String TWO_DECIMAL_FORMAT = "#,##0.00";
    /**
     * The Constant RATE.
     */
    public static final String SPACE = "       ";
    /**
     * RATE_PER_THREE
     */
    private static final DecimalFormat RATE_PER_THREE = new DecimalFormat(TWO_DECIMAL_FORMAT);
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    private static final String ZERO = "0";
    private static final String DETAIL = "Detail";
    private static final String C = "C";
    private static final String P = "P";
    private static final String D = "D";
    public static final String JOINQUERY = "@JOINQUERY";
    private static String CURRENT = "Current";
    private static final int COLUMN_COUNT_TOTAL = 75;
    private static final int COLUMN_COUNT_DISCOUNT = 12;
    private CommonLogic commonLogic = new CommonLogic();
    private static String DASH = "-";
    PVQueryUtils queryUtils = new PVQueryUtils();
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
    PVSelectionDTO baseVariables = new PVSelectionDTO();
    List<ProjectionVarianceDTO> projDTOList1 = new ArrayList<>();
    List<List> currentDiscount = new ArrayList<>();
    List<List> currentSales = new ArrayList<>();
    List<List> currentTotal = new ArrayList<>();
    List<Integer> priorIdTotal = new ArrayList<>();
    List<List> currentTotalDiscount = new ArrayList<>();
    List<Integer> priorProjIdDiscountList = new ArrayList<>();
    List<Integer> priorProjIdSalesList = new ArrayList<>();
    List<Integer> priorProjIdTotalList = new ArrayList<>();
    List<List> currentPivotGTSTotal = new ArrayList<>();
    List<Integer> pivotPriorProjIdList = new ArrayList<>();
    List<List> currentDiscountDol = new ArrayList<>();
    List<List> currentDiscountPPADol = new ArrayList<>();
    List<List> currentDiscountPer = new ArrayList<>();
    List<List> currentDiscountPPAPer = new ArrayList<>();
    List<Object> pivotTotalList = new ArrayList<>();
    List<Object> pivotDiscountList = new ArrayList<>();
    int currentProjId;
    private static final String PRC_PROJ_RESULTS = "PRC_PROJECTION_RESULTS";
    private List chartList;
    private static final CommonDAO commonDao = new CommonDAOImpl();

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

    public PVSelectionDTO getBaseVariables() {
        return baseVariables;
    }

    public void setBaseVariables(PVSelectionDTO baseVariables) {
        this.baseVariables = baseVariables;
    }

    public int getCurrentProjId() {
        return currentProjId;
    }

    public void setCurrentProjId(int currentProjId) {
        this.currentProjId = currentProjId;
    }

    /**
     * customize the results
     *
     * @param list
     * @return list
     */
    public List<ComparisonLookupDTO> getCustomizedComparisonList(final List list) throws PortalException, SystemException {
        final List<ComparisonLookupDTO> finalList = new ArrayList<>();
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

    /**
     * customize the results
     *
     * @param list
     * @return list
     */
    public List<ComparisonLookupDTO> getCustomizedPVComparisonList(final List list) throws PortalException, SystemException {
        final List<ComparisonLookupDTO> finalList = new ArrayList<>();
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

    public List<ProjectionVarianceDTO> getProjVariance(PVSelectionDTO selectionDTO, PVSelectionDTO baseVariables, Object parentId, int start, int offset) {
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
                    selectionDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    selectionDTO.setDeductionHierarchyNo(parentDto.getDeductionHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    selectionDTO.setDeductionHierarchyNo(parentDto.getDeductionHierarchyNo());
                } else {
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    selectionDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    selectionDTO.setDeductionHierarchyNo(selectionDTO.getHierarchyNo());
                }
                selectionDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                selectionDTO.setGroup(parentDto.getGroup());
                selectionDTO.setGroupParent(parentDto.getGroupParent());
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
                } else if (D.equals(selectionDTO.getHierarchyIndicator())) {
                    selectionDTO.setLevelNo(selectionDTO.getDeductionLevelNo() - 1);
                    selectionDTO.setTreeLevelNo(selectionDTO.getDeductionLevelNo() - 1);
                }
                selectionDTO.setGroup(StringUtils.EMPTY);
                selectionDTO.setHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setDeductionHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setGroupParent(0);
            }
            if (selectionDTO.getPivotView().equals(Constant.PERIOD)) {
                resultList = getPeriodResults(selectionDTO, baseVariables, start, offset, parentDto);
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

    public int getProjVarianceCount(PVSelectionDTO selectionDTO, PVSelectionDTO baseVariables, Object parentId, boolean isLevelsCount) {
        int count = 0;
        selectionDTO.setGroupCount(false);
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
                    selectionDTO.setCustomerHierarchyNo(parentDto.getHierarchyNo());
                    selectionDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    selectionDTO.setDeductionHierarchyNo(parentDto.getDeductionHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(parentDto.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    selectionDTO.setDeductionHierarchyNo(parentDto.getDeductionHierarchyNo());
                } else {
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    selectionDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    selectionDTO.setDeductionHierarchyNo(parentDto.getHierarchyNo());
                }
                selectionDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                selectionDTO.setGroup(parentDto.getGroup());
                selectionDTO.setGroupParent(parentDto.getGroupParent());

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
                selectionDTO.setDeductionHierarchyNo(StringUtils.EMPTY);
                selectionDTO.setGroupParent(0);
            }
            if (selectionDTO.getPivotView().equals(Constant.PERIOD)) {
                count += getPeriodResultsCount(selectionDTO, baseVariables, parentDto, isLevelsCount);
            } else {
                count += getPivotResultsCount(parentId, selectionDTO, isLevelsCount);
            }
        } else if (isLevelsCount) {
            selectionDTO.setLevelNo(selectionDTO.getFilterLevelNo());
            selectionDTO.setTreeLevelNo(selectionDTO.getFilterLevelNo());
            count += configureLevelsCount(selectionDTO);
        }
        return count;
    }

    public int getPeriodResultsCount(PVSelectionDTO pVSelectionDTO, PVSelectionDTO baseVariables, ProjectionVarianceDTO parentDto, boolean isLevelsCount) {
        int count = 0;
        boolean isDiscountExpand = false;
        pVSelectionDTO.setGroupCount(false);
        if (parentDto != null) {
            pVSelectionDTO.setPpa(Boolean.FALSE);
            pVSelectionDTO.setReturns(Boolean.FALSE);
            if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) || parentDto.getGroup().contains(CommonUtils.VAR_DIS_RATE) || parentDto.getGroup().contains("RPU") || parentDto.getGroup().contains(CommonUtils.VAR_DIS_PER_EXFAC)) {
                pVSelectionDTO.setGroup(parentDto.getGroup());
                isDiscountExpand = true;
            }
            if (pVSelectionDTO.getGroupParent() == 0) {
                if (!isDiscountExpand) {
                    count += getCustPeriodVarianceCount(baseVariables, pVSelectionDTO, parentDto);
                } else {
                    
                    if (pVSelectionDTO.getDeductionLevelFilter().isEmpty()) {
                        String ccpQuery = Constant.TOTAL.equals(pVSelectionDTO.getLevel()) ? StringUtils.EMPTY : insertAvailableHierarchyNo(pVSelectionDTO);
                        String query;
                        if (Constant.TOTAL.equals(pVSelectionDTO.getLevel())) {
                            List<Object> list = null;
                            if (Constant.PROGRAM_CATEGORY_LABEL.equals(pVSelectionDTO.getDiscountLevel())) {
                                count += pVSelectionDTO.getDeductionLevelCaptions().size();
                                //PPA Count
                                query = getPPACount(pVSelectionDTO, Boolean.FALSE, Boolean.TRUE);
                                list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, pVSelectionDTO.getSessionDTO().getCurrentTableNames()), null, null);
                                if (list != null && !list.isEmpty()) {
                                    Object ob = list.get(0);
                                    count = count + Integer.valueOf(String.valueOf(ob));
                                }
                            } else if (Constant.PROGRAM1.equals(pVSelectionDTO.getDiscountLevel())) {
                                count += pVSelectionDTO.getDeductionLevelCaptions().size();
                                //PPA Count
                                query = getPPACount(pVSelectionDTO, Boolean.TRUE, Boolean.TRUE);
                                list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, pVSelectionDTO.getSessionDTO().getCurrentTableNames()), null, null);
                                if (list != null && !list.isEmpty()) {
                                    Object ob = list.get(0);
                                    count = count + Integer.valueOf(String.valueOf(ob));
                                }
                            }
                        } else if (Constant.PROGRAM_CATEGORY_LABEL.equals(pVSelectionDTO.getDiscountLevel())) {
                            query = ccpQuery + getProgramCategoryForCurrentHierarchy(pVSelectionDTO);
                            query = QueryUtil.replaceTableNames(query.replace("@CCP", Constant.SELECTED_HIERARCHY_NO_HASH), pVSelectionDTO.getSessionDTO().getCurrentTableNames());
                            List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                            //PPA Count
                            query = ccpQuery + getPPACount(pVSelectionDTO, Boolean.FALSE, Boolean.FALSE);
                            query = QueryUtil.replaceTableNames(query.replace("@CCP", Constant.SELECTED_HIERARCHY_NO_HASH), pVSelectionDTO.getSessionDTO().getCurrentTableNames());
                            list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                        } else if (Constant.PROGRAM1.equals(pVSelectionDTO.getDiscountLevel())) {
                            query = ccpQuery + getProgramCountForCurrentHierarchy(pVSelectionDTO);
                            query = QueryUtil.replaceTableNames(query.replace("@CCP", Constant.SELECTED_HIERARCHY_NO_HASH), pVSelectionDTO.getSessionDTO().getCurrentTableNames());
                            List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                            //PPA Count
                            query = ccpQuery + getPPACount(pVSelectionDTO, Boolean.TRUE, Boolean.FALSE);
                            query = QueryUtil.replaceTableNames(query.replace("@CCP", Constant.SELECTED_HIERARCHY_NO_HASH), pVSelectionDTO.getSessionDTO().getCurrentTableNames());
                            list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                        }
                    } else {
                        if (Constant.TOTAL.equals(pVSelectionDTO.getLevel())) {
                            count = pVSelectionDTO.getDeductionLevelFilter().size();
                        } else {
                            String query = getQueryForRebatesAndUdcs(pVSelectionDTO);
                            query = QueryUtil.replaceTableNames(query, pVSelectionDTO.getSessionDTO().getCurrentTableNames());
                            List list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                    }
                }
            }
        }
        }
        if (!isDiscountExpand && !pVSelectionDTO.isIslevelFiler() && !pVSelectionDTO.isIsLevel() && isLevelsCount) {
            if ((pVSelectionDTO.getTreeLevelNo() + 1) == pVSelectionDTO.getTpLevel()
                    && ((pVSelectionDTO.isIsCustomHierarchy()) || (!pVSelectionDTO.getHierarchyIndicator().equals(P)))
                    && pVSelectionDTO.getGroupParent() == 0) {
                count = count + 1;
                pVSelectionDTO.setGroupCount(true);
                pVSelectionDTO.setLevelCount(1);
            } else {
                pVSelectionDTO.setGroupCount(false);
                pVSelectionDTO.setTreeLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                pVSelectionDTO.setLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                int ct = configureLevelsCount(pVSelectionDTO);
                count += ct;
                pVSelectionDTO.setLevelCount(ct);
            }
        }
        return count;
    }

    public List<ProjectionVarianceDTO> getPeriodResults(PVSelectionDTO pVSelectionDTO, PVSelectionDTO baseVariables, int start, int offset, ProjectionVarianceDTO parentDto) {
        LOGGER.info("Inside getPeriodResults" + start + ":Offset:===" + offset);
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<>();
        List<ProjectionVarianceDTO> tobeAddedList = new ArrayList<>();
        int neededRecord = offset;
        int started = start;
        int maxRecord = -1;
        boolean isDiscountExpand = false;
        pVSelectionDTO.setDiscountFlag(false);
        if (!pVSelectionDTO.getDiscountLevel().equalsIgnoreCase(TOTAL_DISCOUNT.getConstant())) {
            pVSelectionDTO.setDiscountFlag(true);
        }
        if (parentDto != null) {
            if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) || parentDto.getGroup().contains(CommonUtils.VAR_DIS_RATE) || parentDto.getGroup().contains("RPU") || parentDto.getGroup().contains(CommonUtils.VAR_DIS_PER_EXFAC)) {
                pVSelectionDTO.setGroup(parentDto.getGroup());
                isDiscountExpand = true;
                if (parentDto.getGroup().contains("RPU")) {
                    pVSelectionDTO.setRPU(true);
                }
            }
            if (pVSelectionDTO.getGroupParent() == 0) {
                if (pVSelectionDTO.getPivotView().equals(Constant.PERIOD)) {
                    maxRecord = getCustPeriodVarianceCount(baseVariables, pVSelectionDTO, parentDto);
                } else {
                    maxRecord = pVSelectionDTO.getPeriodList().size() + 1;
                }
                if (started < maxRecord) {
                    if (pivotTotalList.isEmpty() && !pVSelectionDTO.getLevel().equals(DETAIL)) {
                        getTotalPivotVariance(pVSelectionDTO);
                    }
                    if (isDiscountExpand) {
                        boolean isDetail = pVSelectionDTO.getLevel().equals(DETAIL);
                        pVSelectionDTO.setIsTotal(false);
                        pVSelectionDTO.setDiscountGroupName(parentDto.getGroup());
                        pVSelectionDTO.setDiscountIndex(0);
                        if (isDetail) {
                            getTotalDiscountResults(pVSelectionDTO);
                            if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_PER_EXFAC)) {
                                tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, pivotDiscountList, pVSelectionDTO, NumericConstants.FOURTEEN, Boolean.TRUE));
                            } else if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT)) {
                                tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, pivotDiscountList, pVSelectionDTO, NumericConstants.FIVE, Boolean.FALSE));
                            } else if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_RATE)) {
                                tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, pivotDiscountList, pVSelectionDTO, NumericConstants.EIGHT, Boolean.TRUE));
                            } else {
                                tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, pivotDiscountList, pVSelectionDTO, NumericConstants.ELEVEN, Boolean.FALSE));
                            }
                        } else {
                            List<Object> totalList = null;
                            int count = 0;
                            String query;
                            if (Constant.PROGRAM_CATEGORY_LABEL.equals(pVSelectionDTO.getDiscountLevel())) {
                                //PPA Count
                                query = getPPACount(pVSelectionDTO, Boolean.FALSE, Boolean.TRUE);
                                totalList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, pVSelectionDTO.getSessionDTO().getCurrentTableNames()), null, null);
                                if (totalList != null && !totalList.isEmpty()) {
                                    Object ob = totalList.get(0);
                                    count = count + Integer.valueOf(String.valueOf(ob));
                                }
                            } else if (Constant.PROGRAM1.equals(pVSelectionDTO.getDiscountLevel())) {
                                //PPA Count
                                query = getPPACount(pVSelectionDTO, Boolean.TRUE, Boolean.TRUE);
                                totalList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, pVSelectionDTO.getSessionDTO().getCurrentTableNames()), null, null);
                                if (totalList != null && !totalList.isEmpty()) {
                                    Object ob = totalList.get(0);
                                    count = count + Integer.valueOf(String.valueOf(ob));
                                }
                            }
                            if (!pVSelectionDTO.getDeductionLevelCaptions().isEmpty() || count > 0) {
                                getTotalDiscountResults(pVSelectionDTO);
                                if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_PER_EXFAC)) {
                                    tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, pivotDiscountList, pVSelectionDTO, NumericConstants.FOURTEEN, Boolean.TRUE));
                                } else if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT)) {
                                    tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, pivotDiscountList, pVSelectionDTO, NumericConstants.FIVE, Boolean.FALSE));
                                } else if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_RATE)) {
                                    tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, pivotDiscountList, pVSelectionDTO, NumericConstants.EIGHT, Boolean.TRUE));
                                } else {
                                    tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, pivotDiscountList, pVSelectionDTO, NumericConstants.ELEVEN, Boolean.FALSE));
                                }
                            }
                        }

                    } else {
                        if (pVSelectionDTO.getLevel().equals(DETAIL)) {
                            getTotalPivotVariance(pVSelectionDTO);
                        }
                        List<ProjectionVarianceDTO> allList = getCustPeriodVariance(pivotTotalList, pVSelectionDTO, parentDto, baseVariables);
                        tobeAddedList.addAll(allList);
                    }
                    setChartList(tobeAddedList);
                    for (int i = started; (i < tobeAddedList.size()) && (neededRecord > 0); neededRecord--, i++) {
                        projDTOList.add(tobeAddedList.get(i));
                    }
                }
            }
        }
        if (!isDiscountExpand && !pVSelectionDTO.isIslevelFiler() && !pVSelectionDTO.isIsLevel() && neededRecord > 0) {
            if ((pVSelectionDTO.getTreeLevelNo() + 1) == pVSelectionDTO.getTpLevel()
                    && ((pVSelectionDTO.isIsCustomHierarchy()) || (!pVSelectionDTO.getHierarchyIndicator().equals(P)))
                    && pVSelectionDTO.getGroupParent() == 0) {
                ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
                dto.setLevelNo(pVSelectionDTO.getLevelNo());
                dto.setTreeLevelNo(pVSelectionDTO.getTreeLevelNo());
                dto.setParentNode(pVSelectionDTO.getParentNode());
                dto.setGroup(pVSelectionDTO.getGroupFilter());
                dto.setLevelValue(pVSelectionDTO.getLevelValue());
                dto.setHierarchyIndicator(pVSelectionDTO.getHierarchyIndicator());
                dto.setHierarchyNo(pVSelectionDTO.getHierarchyNo());
                dto.setGroupParent(1);
                if (dto.getHierarchyIndicator().equals(C)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(pVSelectionDTO.getProductHierarchyNo());
                    dto.setDeductionHierarchyNo(pVSelectionDTO.getDeductionHierarchyNo());
                } else if (dto.getHierarchyIndicator().equals(P)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(pVSelectionDTO.getCustomerHierarchyNo());
                    dto.setDeductionHierarchyNo(pVSelectionDTO.getDeductionHierarchyNo());
                } else{
                    dto.setProductHierarchyNo(pVSelectionDTO.getProductHierarchyNo());
                    dto.setCustomerHierarchyNo(pVSelectionDTO.getCustomerHierarchyNo());
                    dto.setDeductionHierarchyNo(dto.getHierarchyNo());
                }
                dto.setParent(1);
                projDTOList.add(dto);
            } else {
                pVSelectionDTO.setTreeLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                pVSelectionDTO.setLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                int begin = maxRecord == -1 ? start : maxRecord - start < 0 ? Math.abs(maxRecord - start) : (maxRecord - start) - offset;
                begin = begin <= 0 ? 0 : begin;
                List<ProjectionVarianceDTO> nextLevelList = configureLevels(begin, neededRecord, pVSelectionDTO, maxRecord);
                projDTOList.addAll(nextLevelList);
            }
        }
        return projDTOList;
    }

    private List<ProjectionVarianceDTO> commonCustomizationForTotalDiscount(ProjectionVarianceDTO parentDto, List<Object> dataList, PVSelectionDTO projSelDTO, int index, boolean isPer) {
        LOGGER.info("Inside commonCustomizationForTotalDiscount");
        List<ProjectionVarianceDTO> dto = new ArrayList<>();
        if (baseVariables.isColValue() && parentDto.getGroup().contains(CommonUtils.COL_VALUE)) {
            projSelDTO.setVarIndicator(Constant.VALUE);
            dto.addAll(getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, index, isPer));
            return dto;
        }
        if (baseVariables.isColVariance() && parentDto.getGroup().contains(CommonUtils.COL_VARIANCE)) {
            projSelDTO.setVarIndicator(Constant.VARIANCE);
            dto.addAll(getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, index, isPer));
            return dto;
        }
        if (baseVariables.isColPercentage() && parentDto.getGroup().contains(Constant.CHANGE1)) {
            projSelDTO.setVarIndicator(Constant.CHANGE);
            dto.addAll(getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, index, isPer));
            return dto;
        }
        LOGGER.info("Ending commonCustomizationForTotalDiscount");
        return Collections.emptyList();
    }

    /**
     * Discount procedure
     *
     * @param projSelDTO
     */
    List<Object> getTotalDiscountResults(PVSelectionDTO projSelDTO) {
        pivotDiscountList.clear();
        List<Object[]> discountsList = null;
        if (pivotDiscountList.isEmpty()) {
            String frequency = projSelDTO.getFrequency();
            String discountId = null;
            List<String> projectionIdList = new ArrayList<>();
            pivotDiscountList = new ArrayList<>();
            if (frequency.equals(Constant.QUARTERLY)) {
                frequency = Constant.QUARTERLY1;
            } else if (frequency.equals(Constant.SEMI_ANNUALLY)) {
                frequency = Constant.SEMIANNUAL_CAPS;
            } else if (frequency.equals(Constant.MONTHLY)) {
                frequency = Constant.MONTHLY_COLUMN;
            } else {
                frequency = Constant.ANNUAL_CAPS;
            }
            projectionIdList.add(String.valueOf(selectionDTO.getCurrentProjId()));
            for (Integer projId : projSelDTO.getProjIdList()) {
                projectionIdList.add(String.valueOf(projId));
            }
            String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
            String ccps = null;
            if (projSelDTO.getLevel().equals(DETAIL)) {
                ccps = getCCPIds(projSelDTO);
                discountId = getRSIds(projSelDTO);
            }
            Object sIds=projSelDTO.getDeductionLevelFilter().isEmpty()?null:removeBracesInList(projSelDTO.getDeductionLevelFilter());
            String levelName=projSelDTO.getDeductionLevelFilter().isEmpty()?projSelDTO.getDiscountLevel():projSelDTO.getSelectedDeductionLevelName();
            Object[] orderedArg = {projectionId, frequency, discountId, Constant.VARIANCE_COLUMN, projSelDTO.getSessionDTO().getSessionId(), projSelDTO.getUserId(), Constant.STRING_ONE, levelName, ccps, projSelDTO.getSalesInclusion(), projSelDTO.getSession().getDeductionInclusion(),projSelDTO.getUomCode(),sIds};
            discountsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS_DISCOUNT", orderedArg);
            pivotDiscountList.addAll(discountsList);
        }
        return pivotDiscountList;
    }

    /**
     * get Customized period variance
     *
     * @return List
     */
    public int getCustPeriodVarianceCount(final PVSelectionDTO baseVariables, final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto) {
        int count = 0;
        if (!pvsdto.getLevel().equals(DETAIL)) {
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

        if (P.equals(parentDto.getHierarchyIndicator())) {
            /**
             * Net Sales % of Ex-Factory
             */
            if (baseVariables.isNetExFactorySales()) {
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
            /**
             * Net Ex-Factory Sales as % of Ex-Factory Sales
             */
            if (baseVariables.isNetExFactorySalesPerExFactory()) {
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
        }
        return count;
    }

    public int configureLevelsCount(PVSelectionDTO selection) {
        CommonLogic commonLogic = new CommonLogic();
        int count;
        if (selection.isIsCustomHierarchy()) {
            count = getCountForCustomView(selection);
        } else {
            count = commonLogic.getCount(selection);
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
            resultStart = (start <= maxRecord) ? start : start - maxRecord;
        }
        if (projSelDTO.isIsCustomHierarchy()) {
            if (maxRecord == -1) {
                resultStart = start;
            } else {
                start = projSelDTO.getPivotView().equals(Constant.PERIOD) ? start : NumericConstants.ZERO;
                resultStart = (start <= maxRecord) ? start : start - maxRecord;
            }

            String hierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
            Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
            List<String> hierarchyNoList = getHiearchyNoForCustomView(projSelDTO, resultStart, offset);
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
        dto.setGroup(CommonUtil.getDisplayFormattedName(hierarchyNo, hierarchyIndicator, projSelDTO.getSessionDTO().getHierarchyLevelDetails(), projSelDTO.getSessionDTO(), projSelDTO.getDisplayFormat()));
        dto.setLevelValue(detailsList.get(0).toString());
        dto.setHierarchyNo(hierarchyNo);
        dto.setHierarchyIndicator(hierarchyIndicator);
        if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
            dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
            dto.setDeductionHierarchyNo(projSelDTO.getDeductionHierarchyNo());
        } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            dto.setProductHierarchyNo(dto.getHierarchyNo());
            dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
            dto.setDeductionHierarchyNo(projSelDTO.getDeductionHierarchyNo());
        } else{
            dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
            dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
            dto.setDeductionHierarchyNo(dto.getHierarchyNo());
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
        ProjectionVarianceDTO parentDto = new ProjectionVarianceDTO();
        boolean isChild = false;
        if (parent instanceof ProjectionVarianceDTO) {
            parentDto = (ProjectionVarianceDTO) parent;
            isChild = true;
        }
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            count += pvsdto.getPeriodList().size() + 1;
        } else if (isChild && !"All Sales group".equalsIgnoreCase(parentDto.getGroup()) && !"All Discount Group".equalsIgnoreCase(parentDto.getGroup())
                && !"All PPA Group".equalsIgnoreCase(parentDto.getGroup())) {
            count += pvsdto.getPeriodList().size();
        }

        if (!pvsdto.isIsLevel() && isLevelsCount) {
            if ((pvsdto.getTreeLevelNo() + 1) == pvsdto.getTpLevel()
                    && ((pvsdto.isIsCustomHierarchy()) || (!pvsdto.getHierarchyIndicator().equals(P)))
                    && pvsdto.getGroupParent() == 0) {
                count = count + 1;
                pvsdto.setGroupCount(true);
                pvsdto.setLevelCount(1);
            } else {
                pvsdto.setTreeLevelNo(pvsdto.getTreeLevelNo() + 1);
                pvsdto.setLevelNo(pvsdto.getTreeLevelNo() + 1);
                int ct = configureLevelsCount(pvsdto);
                count += ct;
                pvsdto.setLevelCount(ct);
            }
        }
        return count;

    }

    public List<ProjectionVarianceDTO> getPivotResults(Object parent, PVSelectionDTO pvsdto, int start, int offset) {
        int neededRecord = offset;
        int started = start;
        int maxRecord = pvsdto.getPeriodList().size();

        ProjectionVarianceDTO parentDto;
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<>();
        if (started < maxRecord || pvsdto.getLevel().equals(TOTAL.getConstant())) {
            getTotalPivotVariance(pvsdto);
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                    getTotalDiscountResults(pvsdto);
                }
                List<ProjectionVarianceDTO> finalList = getCustomizedPivotTotalResults(pivotTotalList, pivotPriorProjIdList, pvsdto, baseVariables, pivotDiscountList);
                for (int i = started; (i < finalList.size()) && (neededRecord > 0); neededRecord--, i++) {
                    projDTOList.add(finalList.get(i));
                }
            } else if (parent instanceof ProjectionVarianceDTO) {
                parentDto = (ProjectionVarianceDTO) parent;
                if ("All Sales Group".equalsIgnoreCase(parentDto.getGroup()) || "All Discount Group".equalsIgnoreCase(parentDto.getGroup())
                        || "All PPA Group".equalsIgnoreCase(parentDto.getGroup())) {
                    maxRecord = -1;
                } else {
                    getTotalDiscountResults(pvsdto);
                    List<ProjectionVarianceDTO> dto = getCustomizedPivotTotalResults(pivotTotalList, pivotPriorProjIdList, pvsdto, baseVariables, pivotDiscountList);
                    for (int i = started; (i < dto.size()) && (neededRecord > 0); neededRecord--, i++) {
                        projDTOList.add(dto.get(i));
                    }
                }
            }
        }
        setChartList(projDTOList);
        if (!pvsdto.isIsLevel() && neededRecord > 0 && !pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if ((pvsdto.getTreeLevelNo() + 1) == pvsdto.getTpLevel()
                    && ((pvsdto.isIsCustomHierarchy()) || (!pvsdto.getHierarchyIndicator().equals(P)))
                    && pvsdto.getGroupParent() == 0) {
                ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
                dto.setLevelNo(pvsdto.getLevelNo());
                dto.setTreeLevelNo(pvsdto.getTreeLevelNo());
                dto.setParentNode(pvsdto.getParentNode());
                dto.setGroup(pvsdto.getGroupFilter());
                dto.setLevelValue(pvsdto.getLevelValue());
                dto.setHierarchyIndicator(pvsdto.getHierarchyIndicator());
                dto.setHierarchyNo(pvsdto.getHierarchyNo());
                dto.setGroupParent(1);
                if (dto.getHierarchyIndicator().equals(C)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(pvsdto.getProductHierarchyNo());
                    dto.setDeductionHierarchyNo(pvsdto.getDeductionHierarchyNo());
                } else if (dto.getHierarchyIndicator().equals(P)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(pvsdto.getCustomerHierarchyNo());
                    dto.setDeductionHierarchyNo(pvsdto.getDeductionHierarchyNo());
                } else{
                    dto.setCustomerHierarchyNo(pvsdto.getCustomerHierarchyNo());
                    dto.setProductHierarchyNo(pvsdto.getProductHierarchyNo());
                    dto.setDeductionHierarchyNo(dto.getHierarchyNo());
                }
                dto.setParent(1);
                projDTOList.add(dto);
            } else {
                pvsdto.setTreeLevelNo(pvsdto.getTreeLevelNo() + 1);
                pvsdto.setLevelNo(pvsdto.getTreeLevelNo() + 1);
                List<ProjectionVarianceDTO> resultList = configureLevels(start, neededRecord, pvsdto, maxRecord);
                projDTOList.addAll(resultList);
            }
        }
        return projDTOList;
    }

    public void getTotalPivotVariance(PVSelectionDTO pvsdto) {
        List< Object[]> gtsResult = null;
        String frequency = pvsdto.getFrequency();
//        CommonUtils.CollectionToString(pvsdto.getDiscountNoList(), false);
        String discountId =  CommonUtils.CollectionToString(pvsdto.getDiscountNoList(), false);
        List<String> projectionIdList = new ArrayList<>();
        pivotTotalList = new ArrayList<>();
        pivotPriorProjIdList = new ArrayList<>();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = Constant.QUARTERLY1;
        } else if (frequency.equals(Constant.SEMI_ANNUALLY)) {
            frequency = Constant.SEMIANNUAL_CAPS;
        } else if (frequency.equals(Constant.MONTHLY)) {
            frequency = Constant.MONTHLY_COLUMN;
        } else {
            frequency = Constant.ANNUAL_CAPS;
        }
        projectionIdList.add(String.valueOf(selectionDTO.getCurrentProjId()));
        for (Integer projId : pvsdto.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
            pivotPriorProjIdList.add(projId);
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
        String ccps = null;
        if (pvsdto.getLevel().equals(DETAIL)) {
            ccps = getCCPIds(pvsdto);
            discountId = getRSIds(pvsdto);
        }
       
        Object[] orderedArg = {projectionId, frequency, discountId, Constant.VARIANCE_COLUMN, pvsdto.getSessionDTO().getSessionId(), pvsdto.getUserId(), "PIVOT", ccps,pvsdto.getUomCode(), "ALL".equals(pvsdto.getSalesInclusion()) ? null : pvsdto.getSalesInclusion(), "ALL".equals(pvsdto.getSession().getDeductionInclusion()) ? null : pvsdto.getSession().getDeductionInclusion()};
        gtsResult = CommonLogic.callProcedure(PRC_PROJ_RESULTS, orderedArg);
        pivotTotalList.addAll(gtsResult);
    }

    String getCCPIds(PVSelectionDTO pvsdto) {
        String query = getCCPQueryForPV(pvsdto);
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
    
    String getRSIds(PVSelectionDTO pvsdto) {
        String rsIds = StringUtils.EMPTY;
        try{
        String rsQuery = insertAvailableHierarchyNo(pvsdto) + getRsIdForCurrentHierarchy(pvsdto);
        String query = QueryUtil.replaceTableNames(rsQuery.replace("@CCP", Constant.SELECTED_HIERARCHY_NO_HASH), pvsdto.getSessionDTO().getCurrentTableNames());
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        boolean flag = true;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj = list.get(i);
                if (flag) {
                    rsIds = String.valueOf(obj);
                    flag = false;
                } else {
                    rsIds = rsIds + "," + String.valueOf(obj);
                }
            }
        }
        }catch(Exception e){
        e.printStackTrace();
        }
        return rsIds;
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
        List<Object> visibleDoubleCol = new ArrayList<>(rightHeader.getDoubleColumns());
        List<String> visibleDoubleHeader = new ArrayList<>(rightHeader.getDoubleHeaders());
        List<Object> visibleSingleCol = new ArrayList<>();
        List<Object> visibleSingleColumn = new ArrayList<>(rightHeader.getSingleColumns());
        List<String> visibleSingleHeader = new ArrayList<>(rightHeader.getSingleHeaders());
        List<String> visibleSingleHead = new ArrayList<>();

        Map<Object, Object[]> doubleMap = rightHeader.getDoubleHeaderMaps();
        Map<Object, Object[]> doubleFinalMap = new HashMap<>();
        List<Object> finalVisList = new ArrayList<>();
        List<String> finalHeaderList = new ArrayList<>();
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
        for (int i = 0; i < visibleSingleCol.size(); i++) {
            String singleHeader = fullHeader.getSingleHeader(visibleSingleCol.get(i));
            newFullHeader.addSingleColumn(visibleSingleCol.get(i), singleHeader, String.class);
        }
        return newFullHeader;
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
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = Constant.QUARTERLY1;
        } else if (frequency.equals(Constant.SEMI_ANNUALLY)) {
            frequency = Constant.SEMI_ANNUAL;
        } else if (frequency.equals(Constant.MONTHLY)) {
            frequency = Constant.MONTHLY_COLUMN;
        } else {
            frequency = Constant.ANNUAL_CAPS;
        }
        List<Object[]> objectList = new ArrayList<>();
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            }

            if (connection != null) {
                StringBuilder statementBuilder = new StringBuilder("{call ");
                statementBuilder.append(procedureName);
                statementBuilder.append("(?,?,?,?,?)}");
                statement = connection.prepareCall(statementBuilder.toString());
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

    public List<ProjectionVarianceDTO> getConfiguredProjectionVariance(Object parentId, PVSelectionDTO projSelDTO, PVSelectionDTO baseVariables, int start, int offset) {
        try {
            LOGGER.info("Inside getConfiguredProjectionVariance");
            List<ProjectionVarianceDTO> list;
            setSelectionDTO(projSelDTO);
            setRightHeader(projSelDTO.getRightHeader());
            setBaseVariables(baseVariables);
            list = getProjVariance(projSelDTO, baseVariables, parentId, start, offset);
            LOGGER.info("Ending getConfiguredProjectionVariance");
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return Collections.emptyList();

    }

    public int getConfiguredProjectionVarianceCount(Object parentId, PVSelectionDTO projSelDTO, PVSelectionDTO baseVariables, boolean isLevelCount) {
        try {
            int count = 0;
            setSelectionDTO(projSelDTO);
            setRightHeader(projSelDTO.getRightHeader());
            setBaseVariables(baseVariables);
            count += getProjVarianceCount(projSelDTO, baseVariables, parentId, isLevelCount);
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
        List<Object[]> objList = new ArrayList<>();

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

    public List<ProjectionVarianceDTO> getCustomizedPivotTotalResults(final List<Object> results, List<Integer> priorProjGtsList, PVSelectionDTO pvsdto, PVSelectionDTO baseVariables, List totalDiscount) {
        List<String> periodList = new ArrayList<>(pvsdto.getPeriodList());
        Map<String, String> periodListMap = new HashMap<>(pvsdto.getPeriodListMap());
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<>();
        int frequencyDivision = pvsdto.getFrequencyDivision();
        if (results != null && !results.isEmpty()) {
            for (int i = 0; i < results.size(); i++) {
                final Object[] row = (Object[]) results.get(i);
                int year = Integer.valueOf(String.valueOf(row[0]));
                int period = row[1] != null ? Integer.valueOf(String.valueOf(row[1])) : 0;
                List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, year, period);
                String pcommonColumn = common.get(0);
                String commonHeader = common.get(1);
                if (periodList.contains(pcommonColumn)) {
                    periodList.remove(pcommonColumn);
                    List<String> columnList = new ArrayList<>(pvsdto.getColumns());
                    columnList.remove(Constant.GROUP);
                    ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                    projDTO.setGroup(commonHeader);

                    //Exfactory Sales
                    if ((baseVariables.isVarExFacSales())) {
                        if (baseVariables.isColValue()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.EX_FAC_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.FOUR, row,Boolean.TRUE,baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.EX_FAC_VARIANCE1, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.FOUR, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            baseVariables.setConversionNeeded(false);
                            customizePivot(Constant.EX_FAC_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.FOUR, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //Demand Sales
                    if ((baseVariables.isVarDemandSales())) {
                        if (baseVariables.isColValue()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.DEMAND_SALES_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.SEVEN, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.DEMAND_SALES_VARIANCE1, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.SEVEN, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            baseVariables.setConversionNeeded(false);
                            customizePivot(Constant.DEMAND_SALES_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.SEVEN, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //Inventory Withdrawal sales
                    if ((baseVariables.isVarInvSales())) {
                        if (baseVariables.isColValue()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.INV_WITH_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.TEN, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.INV_WITH_VARIANCE1, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.TEN, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            baseVariables.setConversionNeeded(false);
                            customizePivot(Constant.INV_WITH_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.TEN, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //Percentage of Exfactory Sales
                    if ((baseVariables.isVarPerExFacSales())) {
                        baseVariables.setConversionNeeded(false);
                        if (baseVariables.isColValue()) {
                            customizePivot(Constant.PER_EX_FAC_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, RATE, NumericConstants.THIRTEEN, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot(Constant.PER_EX_FAC_VARIANCE1, Constant.VARIANCE, pvsdto, projDTO, columnList, RATE, NumericConstants.THIRTEEN, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot(Constant.PER_EX_FAC_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.THIRTEEN, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //Percentage of Demand Sales
                    if ((baseVariables.isVarPerDemandSales())) {
                        baseVariables.setConversionNeeded(false);
                        if (baseVariables.isColValue()) {
                            customizePivot(Constant.PER_DEMAND_SALES_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, RATE, NumericConstants.SIXTEEN, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot(Constant.PER_DEMAND_SALES_VARIANCE1, Constant.VARIANCE, pvsdto, projDTO, columnList, RATE, NumericConstants.SIXTEEN, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot(Constant.PER_DEMAND_SALES_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.SIXTEEN, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //Percentage Inventory Withdrawal Value
                    if ((baseVariables.isVarPerInvSales())) {
                        baseVariables.setConversionNeeded(false);
                        if (baseVariables.isColValue()) {
                            customizePivot(Constant.PER_INV_WITH_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, RATE, NumericConstants.NINETEEN, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot(Constant.PER_INV_WITH_VARIANCE1, Constant.VARIANCE, pvsdto, projDTO, columnList, RATE, NumericConstants.NINETEEN, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot(Constant.PER_INV_WITH_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.NINETEEN, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //Contract Sales
                    if ((baseVariables.isVarContractsales())) {
                        if (baseVariables.isColValue()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.CONTRACT_SALES_WAC_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.TWENTY_TWO, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.CONTRACT_SALES_WAC_VAR1, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.TWENTY_TWO, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            baseVariables.setConversionNeeded(false);
                            customizePivot(Constant.CONTRACT_SALES_WAC_VAR_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.TWENTY_TWO, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //Contract Units
                    if ((baseVariables.isVarContractUnits())) {
                        baseVariables.setConversionNeeded(false);
                        if (baseVariables.isColValue()) {
                            customizePivot(Constant.CONTRACT_UNITS_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT_UNITS, NumericConstants.TWENTY_FIVE, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot(Constant.CONTRACT_UNITS_VAR1, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT_UNITS, NumericConstants.TWENTY_FIVE, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot(Constant.CONTRACT_UNITS_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.TWENTY_FIVE, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //Discount Amount
                    if ((baseVariables.isVarDisAmount())) {
                        if (baseVariables.isColValue()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(Constant.DISCOUNT_AMOUNT_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.TWENTY_EIGHT, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(Constant.DISCOUNT_AMOUNT_VAR1, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.TWENTY_EIGHT, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            pvsdto.setConversionNeeded(false);
                            customizePivot(Constant.DISCOUNT_AMOUNT_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.TWENTY_EIGHT, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //Discount Percentage
                    if ((baseVariables.isVarDisRate())) {
                        baseVariables.setConversionNeeded(false);
                        if (baseVariables.isColValue()) {
                            customizePivot(Constant.DISCOUNT_SALES_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, RATE, NumericConstants.THIRTY_ONE, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot(Constant.DISCOUNT_SALES_VAR1, Constant.VARIANCE, pvsdto, projDTO, columnList, RATE, NumericConstants.THIRTY_ONE, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot(Constant.DISCOUNT_SALES_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.THIRTY_ONE, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //RPU
                    if ((baseVariables.isVarRPU())) {
                        baseVariables.setConversionNeeded(false);
                        if (baseVariables.isColValue()) {
                            customizePivot(Constant.RPU_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.FORTY_SIX, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot(Constant.RPU_VAR1, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.FORTY_SIX, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot(Constant.RPU_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.FORTY_SIX, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //NetSales
                    if ((baseVariables.isVarNetSales())) {
                        if (baseVariables.isColValue()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.NET_SALES_VALUE1, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.FORTY_NINE, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.NET_SALES_VARIANCE1, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.FORTY_NINE, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            baseVariables.setConversionNeeded(false);
                            customizePivot(Constant.NET_SALES_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.FORTY_NINE, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //COGS
                    if ((baseVariables.isVarCOGC())) {
                        if (baseVariables.isColValue()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.COGC_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.FIFTY_TWO, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.COGC_VARIANCE1, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.FIFTY_TWO, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            baseVariables.setConversionNeeded(false);
                            customizePivot(Constant.COGC_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.FIFTY_TWO, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //Net Profit
                    if ((baseVariables.isVarNetProfit())) {
                        if (baseVariables.isColValue()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.NET_PROFIT_VALUE1, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.FIFTY_FIVE, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            baseVariables.setConversionNeeded(true);
                            customizePivot(Constant.NET_PROFIT_VARIANCE1, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.FIFTY_FIVE, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            baseVariables.setConversionNeeded(false);
                            customizePivot(Constant.NET_PROFIT_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.FIFTY_FIVE, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //Net Sales ExFactory Percentage
                    if ((baseVariables.isNetSalesExFactory())) {
                        baseVariables.setConversionNeeded(false);
                        if (baseVariables.isColValue()) {
                            customizePivot(Constant.NET_SALES_EX_FACTORY_VALUE1, Constant.VALUE, pvsdto, projDTO, columnList, RATE, NumericConstants.SIXTY_SEVEN, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot(Constant.NET_SALES_EX_FACTORY_VARIANCE1, Constant.VARIANCE, pvsdto, projDTO, columnList, RATE, NumericConstants.SIXTY_SEVEN, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot(Constant.NET_SALES_EX_FACTORY_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.SIXTY_SEVEN, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //DiscountPerExFactory
                    if ((baseVariables.isDiscountPerExFactory())) {
                        baseVariables.setConversionNeeded(false);
                        if (baseVariables.isColValue()) {
                            customizePivot(Constant.DISCOUNT_PER_EX_FACTORY_VALUE1, Constant.VALUE, pvsdto, projDTO, columnList, RATE, NumericConstants.SEVENTY, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot(Constant.DISCOUNT_PER_EX_FACTORY_VAR1, Constant.VARIANCE, pvsdto, projDTO, columnList, RATE, NumericConstants.SEVENTY, row,Boolean.TRUE, baseVariables);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot(Constant.DISCOUNT_PER_EX_FACTORY_PER1, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.SEVENTY, row,Boolean.TRUE, baseVariables);
                        }
                    }
                    //***net ex factory sales***//
                    if (pvsdto.getView().equals(PRODUCT.getConstant()) || pvsdto.getView().equals(CUSTOM.getConstant())) {
                        if (pvsdto.isNetExFactorySales()) {
                            if (pvsdto.isColValue()) {
                                 baseVariables.setConversionNeeded(true);
                                customizePivot(Constant.NET_EXFACT_SALES_COLUMN_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.SEVENTY_THREE, row,Boolean.TRUE, baseVariables);
                            }
                            if (pvsdto.isColVariance()) {
                                 baseVariables.setConversionNeeded(true);
                                customizePivot(Constant.NET_EXFACT_SALES_COLUMN_VARIANCE, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.SEVENTY_THREE, row,Boolean.TRUE, baseVariables);
                            }
                            if (pvsdto.isColPercentage()) {
                                 baseVariables.setConversionNeeded(false);
                                customizePivot(Constant.NET_EXFACT_SALES_COLUMN_PER_CHANGE, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.SEVENTY_THREE, row,Boolean.TRUE, baseVariables);
                            }
                        }
                         //***net ex factory sales per***//
                        if (pvsdto.isNetExFactorySalesPerExFactory()) {
                            baseVariables.setConversionNeeded(false);
                            if (pvsdto.isColValue()) {
                                customizePivot(Constant.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VALUE, Constant.VALUE, pvsdto, projDTO, columnList, RATE, NumericConstants.SEVENTY_SIX, row,Boolean.TRUE, baseVariables);
                            }
                            if (pvsdto.isColVariance()) {
                                customizePivot(Constant.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VARIANCE, Constant.VARIANCE, pvsdto, projDTO, columnList, RATE, NumericConstants.SEVENTY_SIX, row,Boolean.TRUE, baseVariables);
                            }
                            if (pvsdto.isColPercentage()) {
                                customizePivot(Constant.NET_EXFACT_SALES_PER_EXFACT_COLUMN_PER_CHANGE, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.SEVENTY_SIX, row,Boolean.TRUE, baseVariables);
                            }
                        }
                    }
                    if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {

                        // Individual discount level
                        Set<String> noOfDiscount = new HashSet<>();
                        for (Object discountsName : totalDiscount) {
                            final Object[] disc = (Object[]) discountsName;
                            if ("Program".equalsIgnoreCase(pvsdto.getDiscountLevel())) {
                                if (disc[disc.length - 1] != null) {
                                    noOfDiscount.add(String.valueOf(disc[disc.length - 1]));
                                }
                            } else if (disc[NumericConstants.TWO] != null) {
                                noOfDiscount.add(String.valueOf(disc[NumericConstants.TWO]));
                            }
                        }
                        for (int dis = 0; dis < totalDiscount.size(); dis++) {
                            Object[] discountRow = (Object[]) totalDiscount.get(dis);
                            int dyear = Integer.valueOf(String.valueOf(discountRow[0]));
                            int dperiod = Integer.valueOf(String.valueOf(discountRow[1]));
                            List<String> dcommon = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, dyear, dperiod);
                            String dcommonColumn = dcommon.get(0);
                            if (pcommonColumn.equals(dcommonColumn)) {
                                for (int disc = 0; disc < noOfDiscount.size(); disc++) {
                                    String discountName = String.valueOf(discountRow[NumericConstants.TWO]).replace(" ", StringUtils.EMPTY);

                                    String head = discountName + String.valueOf(pvsdto.getDiscountNameMap().get(discountName));
                                    //Discount Amount
                                    if ((baseVariables.isVarDisAmount())) {
                                        if (pvsdto.isColValue()) {
                                        customizePivot(Constant.DISCOUNT_AMOUNT_VALUE + head, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.FIVE, discountRow,Boolean.FALSE, baseVariables);
                                        }
                                        if (pvsdto.isColVariance()) {
                                        customizePivot(Constant.DISCOUNT_AMOUNT_VAR1 + head, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.FIVE, discountRow,Boolean.FALSE, baseVariables);
                                        }
                                        if (pvsdto.isColPercentage()) {
                                        customizePivot(Constant.DISCOUNT_AMOUNT_PER1 + head, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.FIVE, discountRow,Boolean.FALSE, baseVariables);
                                        }
                                        }
                                    //Discount Rate
                                    if ((baseVariables.isVarDisRate())) {
                                        if (pvsdto.isColValue()) {
                                        customizePivot(Constant.DISCOUNT_SALES_VALUE + head, Constant.VALUE, pvsdto, projDTO, columnList, RATE, NumericConstants.EIGHT, discountRow,Boolean.FALSE, baseVariables);
                                        }
                                        if (pvsdto.isColVariance()) {
                                        customizePivot(Constant.DISCOUNT_SALES_VAR1 + head, Constant.VARIANCE, pvsdto, projDTO, columnList, RATE, NumericConstants.EIGHT, discountRow,Boolean.FALSE, baseVariables);
                                        }
                                        if (pvsdto.isColPercentage()) {
                                        customizePivot(Constant.DISCOUNT_SALES_PER1 + head, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.EIGHT, discountRow,Boolean.FALSE, baseVariables);
                                        }
                                        }
                                    //RPU
                                    if ((baseVariables.isVarRPU())) {
                                        if (pvsdto.isColValue()) {
                                        customizePivot(Constant.RPU_VALUE + head, Constant.VALUE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.ELEVEN, discountRow,Boolean.FALSE, baseVariables);
                                        }
                                        if (pvsdto.isColVariance()) {
                                        customizePivot(Constant.RPU_VAR1 + head, Constant.VARIANCE, pvsdto, projDTO, columnList, AMOUNT, NumericConstants.ELEVEN, discountRow,Boolean.FALSE, baseVariables);
                                        }
                                        if (pvsdto.isColPercentage()) {
                                        customizePivot(Constant.RPU_PER1 + head, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.ELEVEN, discountRow,Boolean.FALSE, baseVariables);
                                        }
                                        }
                                    //Discount per of exfactory
                                    if ((baseVariables.isDiscountPerExFactory())) {
                                        if (pvsdto.isColValue()) {
                                        customizePivot(Constant.DISCOUNT_PER_EX_FACTORY_VALUE1 + head, Constant.VALUE, pvsdto, projDTO, columnList, RATE, NumericConstants.FOURTEEN, discountRow,Boolean.FALSE, baseVariables);
                                        }
                                        if (pvsdto.isColVariance()) {
                                        customizePivot(Constant.DISCOUNT_PER_EX_FACTORY_VAR1 + head, Constant.VARIANCE, pvsdto, projDTO, columnList, RATE, NumericConstants.FOURTEEN, discountRow,Boolean.FALSE, baseVariables);
                                        }
                                        if (pvsdto.isColPercentage()) {
                                        customizePivot(Constant.DISCOUNT_PER_EX_FACTORY_PER1+ head, Constant.CHANGE, pvsdto, projDTO, columnList, RATE, NumericConstants.FOURTEEN, discountRow,Boolean.FALSE, baseVariables);
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
        List<String> columnList = new ArrayList<>(pvsdto.getColumns());
        columnList.remove(Constant.GROUP);
        boolean leftFlag = false;
        for (String ob : periodList) {
            leftFlag = true;
            ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
            projDTO.setParent(0);
            projDTO.setGroup(periodListMap.get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(AMOUNT, Constant.NULL));
            }
            projDTOList.add(projDTO);
        }
        if (pvsdto.getProjectionOrder().equals(Constant.ASCENDING)) {
            if (leftFlag) {
                Collections.sort(projDTOList, new ProjectionVarianceDTO());
            }
        } else {
            if (leftFlag) {
                Collections.sort(projDTOList, new ProjectionVarianceDTO());
            }
            Collections.reverse(projDTOList);
        }
        ProjectionVarianceDTO totalDTO = new ProjectionVarianceDTO();
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            totalDTO.setGroup(Constant.PROJECTION_TOTAL);
            projDTOList.add(0, totalDTO);
        }
        return projDTOList;
    }

    /**
     * get Customized period variance
     *
     * @return List
     */
    public List<ProjectionVarianceDTO> getCustPeriodVariance(final List<Object> gtsList, final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto, final PVSelectionDTO baseVariables) {
        try {
            List<ProjectionVarianceDTO> projectionVarianceDTO = new ArrayList<>();
            if (pvsdto.getLevel().equals(DETAIL)) {
                //No action required
            } else {
                ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
                dto.setGroup(Constant.PROJECTION_TOTAL);
                projectionVarianceDTO.add(dto);
            }
            // ExFac Sales
            if (baseVariables.isVarExFacSales()) {
                if (baseVariables.isColValue()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO exFacValue = calculateDetailTotal(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VALUE, NumericConstants.FOUR, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(exFacValue);
                }
                if (baseVariables.isColVariance()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO exFacVar = calculateDetailTotal(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VARIANCE, NumericConstants.FOUR, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(exFacVar);
                }
                if (baseVariables.isColPercentage()) {
                    baseVariables.setConversionNeeded(false);
                    ProjectionVarianceDTO exFacPer = calculateDetailTotal(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.CHANGE, NumericConstants.FOUR, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(exFacPer);
                }
            }
            // Demand Sales
            if (baseVariables.isVarDemandSales()) {
                if (baseVariables.isColValue()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO demandValue = calculateDetailTotal(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VALUE, NumericConstants.SEVEN, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(demandValue);
                }
                if (baseVariables.isColVariance()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO demandVar = calculateDetailTotal(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VARIANCE, NumericConstants.SEVEN, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(demandVar);
                }
                if (baseVariables.isColPercentage()) {
                    baseVariables.setConversionNeeded(false);
                    ProjectionVarianceDTO demandPer = calculateDetailTotal(Constant.PVVariables.DEMAND_SALES.toString(), Constant.CHANGE, NumericConstants.SEVEN, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(demandPer);
                }
            }
            // Inventory Sales Withdrawal
            if (baseVariables.isVarInvSales()) {
                if (baseVariables.isColValue()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO invWithValue = calculateDetailTotal(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VALUE, NumericConstants.TEN, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(invWithValue);
                }
                if (baseVariables.isColVariance()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO invWithVar = calculateDetailTotal(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VARIANCE, NumericConstants.TEN, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(invWithVar);
                }
                if (baseVariables.isColPercentage()) {
                    baseVariables.setConversionNeeded(false);
                    ProjectionVarianceDTO invWithPer = calculateDetailTotal(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.CHANGE, NumericConstants.TEN, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(invWithPer);

                }
            }
            //% Of Ex Factory
            if (baseVariables.isVarPerExFacSales()) {
                baseVariables.setConversionNeeded(false);
                if (baseVariables.isColValue()) {
                    pvsdto.setVarIndicator(VALUE.getConstant());
                    ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.VALUE, NumericConstants.THIRTEEN, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(pob);
                }
                if (baseVariables.isColVariance()) {
                    pvsdto.setVarIndicator(VARIANCE.getConstant());
                    ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.VARIANCE, NumericConstants.THIRTEEN, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(pob);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.CHANGE, NumericConstants.THIRTEEN, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(pob);
                }
            }
            // % Of Demand Sales
            if (baseVariables.isVarPerDemandSales()) {
                baseVariables.setConversionNeeded(false);
                if (baseVariables.isColValue()) {
                    ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_DEMAND.toString(), Constant.VALUE, NumericConstants.SIXTEEN, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(pob);
                }
                if (baseVariables.isColVariance()) {
                    ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_DEMAND.toString(), Constant.VARIANCE, NumericConstants.SIXTEEN, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(pob);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_DEMAND.toString(), Constant.CHANGE, NumericConstants.SIXTEEN, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(pob);
                }

            }
            // % Of Inv Sales
            if (baseVariables.isVarPerInvSales()) {
                baseVariables.setConversionNeeded(false);
                if (baseVariables.isColValue()) {
                    pvsdto.setVarIndicator(VALUE.getConstant());
                    ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.VALUE, NumericConstants.NINETEEN, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(pob);
                }
                if (baseVariables.isColVariance()) {
                    pvsdto.setVarIndicator(VARIANCE.getConstant());
                    ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.VARIANCE, NumericConstants.NINETEEN, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(pob);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.CHANGE, NumericConstants.NINETEEN, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(pob);
                }
            }
            //Contract Sales
            if (baseVariables.isVarContractsales()) {
                if (baseVariables.isColValue()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO salesValue = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VALUE, NumericConstants.TWENTY_TWO, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(salesValue);
                }
                if (baseVariables.isColVariance()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO salesVar = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VARIANCE, NumericConstants.TWENTY_TWO, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(salesVar);
                }
                if (baseVariables.isColPercentage()) {
                    baseVariables.setConversionNeeded(false);
                    ProjectionVarianceDTO salesPer = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.CHANGE, NumericConstants.TWENTY_TWO, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(salesPer);
                }
            }
            //Contract Units
            if (baseVariables.isVarContractUnits()) {
                baseVariables.setConversionNeeded(false);
                if (baseVariables.isColValue()) {
                    ProjectionVarianceDTO units = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.VALUE, NumericConstants.TWENTY_FIVE, gtsList, pvsdto, AMOUNT_UNITS, false, baseVariables);
                    projectionVarianceDTO.add(units);
                }
                if (baseVariables.isColVariance()) {
                    ProjectionVarianceDTO units = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.VARIANCE, NumericConstants.TWENTY_FIVE, gtsList, pvsdto, AMOUNT_UNITS, false, baseVariables);
                    projectionVarianceDTO.add(units);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO units = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.CHANGE, NumericConstants.TWENTY_FIVE, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(units);
                }
            }
            //Discount $ 
            if (baseVariables.isVarDisAmount()) {
                if (baseVariables.isColValue()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO discountDol = calculateDetailTotal(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VALUE, NumericConstants.TWENTY_EIGHT, gtsList, pvsdto, AMOUNT, true, baseVariables);
                    discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountDol);
                }
                if (baseVariables.isColVariance()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO discountDol = calculateDetailTotal(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VARIANCE, NumericConstants.TWENTY_EIGHT, gtsList, pvsdto, AMOUNT, true, baseVariables);
                    discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountDol);
                }
                if (baseVariables.isColPercentage()) {
                    baseVariables.setConversionNeeded(false);
                    ProjectionVarianceDTO discountDol = calculateDetailTotal(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.CHANGE, NumericConstants.TWENTY_EIGHT, gtsList, pvsdto, RATE, true, baseVariables);
                    discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountDol);
                }
            }
            //Discount % 
            if (baseVariables.isVarDisRate()) {
                baseVariables.setConversionNeeded(false);
                if (baseVariables.isColValue()) {
                    ProjectionVarianceDTO discountPer = calculateDetailTotal(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VALUE, NumericConstants.THIRTY_ONE, gtsList, pvsdto, RATE, true, baseVariables);
                    discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountPer);
                }
                if (baseVariables.isColVariance()) {
                    ProjectionVarianceDTO discountPer = calculateDetailTotal(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VARIANCE, NumericConstants.THIRTY_ONE, gtsList, pvsdto, RATE, true, baseVariables);
                    discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountPer);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO discountPer = calculateDetailTotal(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.CHANGE, NumericConstants.THIRTY_ONE, gtsList, pvsdto, RATE, true, baseVariables);
                    discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountPer);
                }
            }
            // RPU
            if (baseVariables.isVarRPU()) {
                baseVariables.setConversionNeeded(false);
                if (baseVariables.isColValue()) {
                    ProjectionVarianceDTO netSalesValue = calculateDetailTotal(Constant.PVVariables.VAR_RPU.toString(), Constant.VALUE, NumericConstants.FORTY, gtsList, pvsdto, AMOUNT, true, baseVariables);
                    netSalesValue = setDataObjects(netSalesValue, parentDto, pvsdto);
                    projectionVarianceDTO.add(netSalesValue);
                }
                if (baseVariables.isColVariance()) {
                    ProjectionVarianceDTO netSalesVar = calculateDetailTotal(Constant.PVVariables.VAR_RPU.toString(), Constant.VARIANCE, NumericConstants.FORTY, gtsList, pvsdto, AMOUNT, true, baseVariables);
                    netSalesVar = setDataObjects(netSalesVar, parentDto, pvsdto);
                    projectionVarianceDTO.add(netSalesVar);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.VAR_RPU.toString(), Constant.CHANGE, NumericConstants.FORTY, gtsList, pvsdto, RATE, true, baseVariables);
                    netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(netSalesPer);
                }
            }
//            DiscountPerExFactory

            if (baseVariables.isDiscountPerExFactory()) {
                baseVariables.setConversionNeeded(false);
                if (baseVariables.isColValue()) {
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VALUE, NumericConstants.SEVENTY, gtsList, pvsdto, RATE, true, baseVariables);
                    netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(netSalesPer);
                }
                if (baseVariables.isColVariance()) {
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VARIANCE, NumericConstants.SEVENTY, gtsList, pvsdto, RATE, true, baseVariables);
                    netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(netSalesPer);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.CHANGE, NumericConstants.SEVENTY, gtsList, pvsdto, RATE, true, baseVariables);
                    netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(netSalesPer);
                }
            }

            //NetSales 
            if (baseVariables.isVarNetSales()) {
                if (baseVariables.isColValue()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO netSalesValue = calculateDetailTotal(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VALUE, NumericConstants.FORTY_NINE, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(netSalesValue);
                }
                if (baseVariables.isColVariance()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO netSalesVar = calculateDetailTotal(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VARIANCE, NumericConstants.FORTY_NINE, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(netSalesVar);
                }
                if (baseVariables.isColPercentage()) {
                    baseVariables.setConversionNeeded(false);
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.VAR_NETSALES.toString(), Constant.CHANGE, NumericConstants.FORTY_NINE, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(netSalesPer);
                }
            }

            //NetSalesExFactory
            if (baseVariables.isNetSalesExFactory()) {
                if (baseVariables.isColValue()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.VALUE, NumericConstants.SIXTY_SEVEN, gtsList, pvsdto, RATE, false, baseVariables);
                    netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(netSalesPer);
                }
                if (baseVariables.isColVariance()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.VARIANCE, NumericConstants.SIXTY_SEVEN, gtsList, pvsdto, RATE, false, baseVariables);
                    netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(netSalesPer);
                }
                if (baseVariables.isColPercentage()) {
                    baseVariables.setConversionNeeded(false);
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.CHANGE, NumericConstants.SIXTY_SEVEN, gtsList, pvsdto, RATE, false, baseVariables);
                    netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(netSalesPer);
                }
            }
            if (P.equals(parentDto.getHierarchyIndicator())) {
                /**
                 * Net Sales % of Ex-Factory
                 */
                if (baseVariables.isNetExFactorySales()) {
                    baseVariables.setConversionNeeded(false);
                    if (baseVariables.isColValue()) {
                        ProjectionVarianceDTO netExFactorySalesValue = calculateDetailTotal(Constant.PVVariables.NET_EX_FACTORY_SALES.toString(), Constant.VALUE, NumericConstants.SEVENTY_THREE, gtsList, pvsdto, AMOUNT, false, baseVariables);
                        projectionVarianceDTO.add(netExFactorySalesValue);
                    }
                    if (baseVariables.isColVariance()) {
                        ProjectionVarianceDTO netExFactorySalesVar = calculateDetailTotal(Constant.PVVariables.NET_EX_FACTORY_SALES.toString(), Constant.VARIANCE, NumericConstants.SEVENTY_THREE, gtsList, pvsdto, AMOUNT, false, baseVariables);
                        projectionVarianceDTO.add(netExFactorySalesVar);
                    }
                    if (baseVariables.isColPercentage()) {
                        ProjectionVarianceDTO netExFactorySalesPer = calculateDetailTotal(Constant.PVVariables.NET_EX_FACTORY_SALES.toString(), Constant.CHANGE, NumericConstants.SEVENTY_THREE, gtsList, pvsdto, RATE, false, baseVariables);
                        projectionVarianceDTO.add(netExFactorySalesPer);
                    }
                }
                /**
                 * Net Ex-Factory Sales as % of Ex-Factory Sales
                 */
                if (baseVariables.isNetExFactorySalesPerExFactory()) {
                    baseVariables.setConversionNeeded(false);
                    if (baseVariables.isColValue()) {
                        ProjectionVarianceDTO netExFactorySalesPerExFactoryValue = calculateDetailTotal(Constant.PVVariables.NET_EX_FACTORY_SALES_PER_EX_FACTORY.toString(), Constant.VALUE, NumericConstants.SEVENTY_SIX, gtsList, pvsdto, RATE, false, baseVariables);
                        projectionVarianceDTO.add(netExFactorySalesPerExFactoryValue);
                    }
                    if (baseVariables.isColVariance()) {
                        baseVariables.setConversionNeeded(true);
                        ProjectionVarianceDTO netExFactorySalesPerExFactoryVar = calculateDetailTotal(Constant.PVVariables.NET_EX_FACTORY_SALES_PER_EX_FACTORY.toString(), Constant.VARIANCE, NumericConstants.SEVENTY_SIX, gtsList, pvsdto, RATE, false, baseVariables);
                        projectionVarianceDTO.add(netExFactorySalesPerExFactoryVar);
                    }
                    if (baseVariables.isColPercentage()) {
                        ProjectionVarianceDTO netExFactorySalesPerExFactoryPer = calculateDetailTotal(Constant.PVVariables.NET_EX_FACTORY_SALES_PER_EX_FACTORY.toString(), Constant.CHANGE, NumericConstants.SEVENTY_SIX, gtsList, pvsdto, RATE, false, baseVariables);
                        projectionVarianceDTO.add(netExFactorySalesPerExFactoryPer);
                    }
                }
            }

//            COGS
            if (baseVariables.isVarCOGC()) {
                if (baseVariables.isColValue()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO netSalesValue = calculateDetailTotal(Constant.PVVariables.VAR_COGS.toString(), Constant.VALUE, NumericConstants.FIFTY_TWO, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(netSalesValue);
                }
                if (baseVariables.isColVariance()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO netSalesVar = calculateDetailTotal(Constant.PVVariables.VAR_COGS.toString(), Constant.VARIANCE, NumericConstants.FIFTY_TWO, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(netSalesVar);
                }
                if (baseVariables.isColPercentage()) {
                    baseVariables.setConversionNeeded(false);
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.VAR_COGS.toString(), Constant.CHANGE, NumericConstants.FIFTY_TWO, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(netSalesPer);
                }
            }
            //Net Profit
            if (baseVariables.isVarNetProfit()) {
                if (baseVariables.isColValue()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO netSalesValue = calculateDetailTotal(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VALUE, NumericConstants.FIFTY_FIVE, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(netSalesValue);
                }
                if (baseVariables.isColVariance()) {
                    baseVariables.setConversionNeeded(true);
                    ProjectionVarianceDTO netSalesVar = calculateDetailTotal(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VARIANCE, NumericConstants.FIFTY_FIVE, gtsList, pvsdto, AMOUNT, false, baseVariables);
                    projectionVarianceDTO.add(netSalesVar);
                }
                if (baseVariables.isColPercentage()) {
                    baseVariables.setConversionNeeded(false);
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.CHANGE, NumericConstants.FIFTY_FIVE, gtsList, pvsdto, RATE, false, baseVariables);
                    projectionVarianceDTO.add(netSalesPer);
                }
            }

            return projectionVarianceDTO;
        } catch (Exception e) {
            LOGGER.error(e);
            return Collections.emptyList();
        }
    }

    public ProjectionVarianceDTO getCustomisedProjectionResultsDiscount(List<Object> list, String discountName, PVSelectionDTO projSelDTO) {
        LOGGER.info("Inside getCustomisedProjectionResultsDiscount");
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
        projDTO.setLevelValue(projSelDTO.getLevelValue());
        projDTO.setLevelNo(projSelDTO.getLevelNo());
        projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projDTO.setGroup(null);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<String> discountList = new ArrayList<>(projSelDTO.getDiscountNameList());
        boolean start = true;
        String discount = null;
        List<Integer> priorList = new ArrayList<>(projSelDTO.getProjIdList());
        if (list != null && !list.isEmpty()) {
            for (int i = projSelDTO.getDiscountIndex(); i < list.size() && start; i++) {
                projSelDTO.setDiscountIndex(i);
                Object[] discountRow = (Object[]) list.get(i);
                if (discount == null) {
                    projDTO.setGroup(String.valueOf(discountRow[NumericConstants.TWO]));
                } else if (!discount.equals(discountRow[NumericConstants.TWO].toString())) {
                    if (!discountName.contains(Constant.TOTAL_SMALL)) {
                        discountList.remove(discount);
                    }
                    start = false;
                }
                if (start) {
                    discount = discountRow[NumericConstants.TWO] != null ? discountRow[NumericConstants.TWO].toString() : StringUtils.EMPTY;
                    String column = StringUtils.EMPTY;
                    String column1;
                    if (frequencyDivision == NumericConstants.FOUR) {
                        column = Constant.Q + discountRow[1] + StringUtils.EMPTY + discountRow[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        column = Constant.S + discountRow[1] + StringUtils.EMPTY + discountRow[0];
                    } else if (frequencyDivision == 1) {
                        column = StringUtils.EMPTY + discountRow[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(discountRow[1])) - 1);
                        column = monthName + discountRow[0];
                    }
                    column1 = column + CURRENT + projSelDTO.getCurrentProjId();
                    if (projSelDTO.hasColumn(column1)) {
                        String value = StringUtils.EMPTY + discountRow[NumericConstants.THREE];
                        if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                            value = getFormattedValue(AMOUNT, value);
                        } else if (projSelDTO.getSales().contains(Constant.RATE)) {
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
                            if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                                priorVal = getFormattedValue(AMOUNT, priorVal);
                            } else if (projSelDTO.getSales().contains(Constant.RATE)) {
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
        if (discountName.contains(Constant.TOTAL_SMALL)) {
            projDTO.setParent(1);
            projDTO.setLevelNo(projSelDTO.getLevelNo());
            projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
            projDTO.setParentNode(projSelDTO.getParentNode());
            projDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
            projDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
            projDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
            projDTO.setDeductionHierarchyNo(projSelDTO.getDeductionHierarchyNo());
            projDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
            if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                projDTO.setGroup("Total Discount $");
            } else {
                projDTO.setGroup("Total Discount %");
            }
        } else {
            if (discount != null) {
                discountList.remove(discount);
            }
            projDTO.setParent(0);
            if (projDTO.getGroup() == null || StringUtils.EMPTY.equals(projDTO.getGroup())) {
                projDTO.setGroup(discountName);
                discountList.remove(discountName);
            }
        }

        for (String columns : columnList) {
            projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
        }
        LOGGER.info("Ending getCustomisedProjectionResultsDiscount");
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
            dto.setDeductionHierarchyNo(pvsdto.getDeductionHierarchyNo());
        } else if (dto.getHierarchyIndicator().equals(P)) {
            dto.setProductHierarchyNo(dto.getHierarchyNo());
            dto.setCustomerHierarchyNo(pvsdto.getCustomerHierarchyNo());
            dto.setDeductionHierarchyNo(pvsdto.getDeductionHierarchyNo());
        } else{
            dto.setCustomerHierarchyNo(pvsdto.getCustomerHierarchyNo());
            dto.setProductHierarchyNo(pvsdto.getProductHierarchyNo());
            dto.setDeductionHierarchyNo(dto.getHierarchyNo());
        }
        return dto;
    }

    public Map<Object, Object> getNMProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<>();
        Map<Object, Object> map = new HashMap<>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(NmProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));
        ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_NAME));
        projectionListFrom.add(ProjectionFactoryUtil.property(Constant.FIELD_VALUES));
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
            value = ZERO;
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

    public boolean isNullActual(String value) {
        boolean actualValue = false;
        if (value.contains(NULL.getConstant())) {
            actualValue = true;
        }
        return actualValue;
    }

    public Integer getComparisonCount(ComparisonLookupDTO comparisonLookup, Set<Filter> filter, String screenName) {
        Integer count;
        Map<String, Object> parameters = new HashMap<>();

        if (filter != null) {
            for (Container.Filter filterSet : filter) {
                if (filterSet instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filterSet;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                } else if (filterSet instanceof Between) {
                    Between betweenFilter = (Between) filterSet;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(Constant.FILTER_BETWEEN + betweenFilter.getPropertyId() + Constant.TILT_FROM, CommonLogic.convertStringToDate(String.valueOf(startValue), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                    parameters.put(Constant.FILTER_BETWEEN + betweenFilter.getPropertyId() + "~to", CommonLogic.convertStringToDate(String.valueOf(endValue), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                } else if (filterSet instanceof Compare) {
                    Compare compare = (Compare) filterSet;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (compare.getValue() instanceof Date) {
                        if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(Constant.FILTER_GOE + compare.getPropertyId() + Constant.TILT_FROM, CommonLogic.convertStringToDate(String.valueOf(value), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                        } else {
                            parameters.put(Constant.FILTER_GOE + compare.getPropertyId() + "~to", CommonLogic.convertStringToDate(String.valueOf(value), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                        }
                    }
                }
            }
        }
        String resultString = new PVQueryUtils().getComparisionSearchResults(comparisonLookup, screenName, parameters, null, 0, 0, true);
        List result = (List) commonDao.executeSelectQuery(resultString, null, null);
        if (result != null && !result.isEmpty()) {
            count = Integer.valueOf(String.valueOf(result.get(0)));
        } else {
            count = 0;
        }
        return count;
    }

    public List<ComparisonLookupDTO> getComparisonResults(final ComparisonLookupDTO comparisonLookup, int start, int offset, Set<Filter> filter, List<SortByColumn> sortColumns, String screenName) throws PortalException, SystemException {
        Map<String, Object> parameters = new HashMap<>();

        if (filter != null) {
            for (Container.Filter filterSet : filter) {
                if (filterSet instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filterSet;
                    String filterString = Constant.PERCENT + stringFilter.getFilterString() + Constant.PERCENT;
                    parameters.put(Constant.FILTER + stringFilter.getPropertyId(), filterString);
                } else if (filterSet instanceof Between) {
                    Between betweenFilter = (Between) filterSet;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();
                    parameters.put(Constant.FILTER_BETWEEN + betweenFilter.getPropertyId() + Constant.TILT_FROM, CommonLogic.convertStringToDate(String.valueOf(startValue), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                    parameters.put(Constant.FILTER_BETWEEN + betweenFilter.getPropertyId() + "~to", CommonLogic.convertStringToDate(String.valueOf(endValue), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                } else if (filterSet instanceof Compare) {
                    Compare compare = (Compare) filterSet;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (compare.getValue() instanceof Date) {
                        if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put(Constant.FILTER_GOE + compare.getPropertyId() + Constant.TILT_FROM, CommonLogic.convertStringToDate(String.valueOf(value), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                        } else {
                            parameters.put(Constant.FILTER_GOE + compare.getPropertyId() + "~to", CommonLogic.convertStringToDate(String.valueOf(value), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                        }
                    }
                }
            }
        }

        String resultString = new PVQueryUtils().getComparisionSearchResults(comparisonLookup, screenName, parameters, sortColumns, start, offset, false);
        List result = (List) commonDao.executeSelectQuery(resultString, null, null);
        return getCustomizedComparisonList(result);
    }

    public void saveNMPVSelection(Map map, int projectionID, String screenName) {
        List<NmProjectionSelection> list = new ArrayList<>();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(NmProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionID));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, screenName));

        try {
            list = NmProjectionSelectionLocalServiceUtil.dynamicQuery(query);

            if (list.isEmpty()) {
                commonLogic.saveSelection(map, projectionID, screenName, Constant.SAVE, "NM_PROJECTION_SELECTION");
            } else {
                commonLogic.saveSelection(map, projectionID, screenName, Constant.UPDATE, "NM_PROJECTION_SELECTION");
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
        List<Object[]> resultList = null;
        if (StringUtils.isNotBlank(projectionIds)) {
            projectionIds = projectionIds.substring(1, projectionIds.length() - 1);
            String query = "SELECT PROJECTION_MASTER_SID,PROJECTION_NAME FROM PROJECTION_MASTER WHERE PROJECTION_MASTER_SID IN (" + projectionIds + ");";
            resultList = (List) commonDao.executeSelectQuery(query, null, null);
        }
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

    public List<ProjectionVarianceDTO> getCustomisedProjectionResultsTotalDiscount(List<Object> dataList, PVSelectionDTO projSelDTO, int index, boolean isPer) {
        LOGGER.info("Inside getCustomisedProjectionResultsTotalDiscount");
        String lastValue = StringUtils.EMPTY;
        String lastGroupName = StringUtils.EMPTY;
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
                if (Constant.PROGRAM_CATEGORY_LABEL.equalsIgnoreCase(projSelDTO.getDiscountLevel())) {
                    if (!StringUtils.EMPTY.equals(lastValue) && !Constant.NULL.equals(lastValue) && obj[NumericConstants.TWO] != null && !lastValue.equals(String.valueOf(obj[NumericConstants.TWO]))) {
                        pvDTO.setGroup(lastGroupName);
                        resultDto.add(pvDTO);
                        pvDTO = new ProjectionVarianceDTO();
                    }
                    lastValue = String.valueOf(obj[NumericConstants.TWO]);
                    lastGroupName = String.valueOf(obj[NumericConstants.TWO]);
                    pvDTO.setGroup(String.valueOf(obj[NumericConstants.TWO]));
                } else {
                    if (!StringUtils.EMPTY.equals(lastValue) && !Constant.NULL.equals(lastValue) && obj[obj.length - 1] != null && !lastValue.equals(String.valueOf(obj[obj.length - 1]))) {
                        pvDTO.setGroup(lastGroupName);
                        resultDto.add(pvDTO);
                        pvDTO = new ProjectionVarianceDTO();
                    }

                    lastValue = String.valueOf(obj[obj.length - 1]);
                    lastGroupName = String.valueOf(obj[NumericConstants.TWO]);
                    pvDTO.setGroup(String.valueOf(obj[NumericConstants.TWO]));
                }
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == NumericConstants.FOUR) {
                    commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == NumericConstants.TWO) {
                    commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == NumericConstants.TWELVE) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                PVCommonLogic.customizePeriod(commonColumn, projSelDTO.getVarIndicator(), projSelDTO, pvDTO, isPer ? RATE : AMOUNT, index, obj, isPer);
                for (int j = 0; j < priorList.size(); j++) {
                    PVCommonLogic.getPriorCommonCustomization(projSelDTO.getVarIndicator(), projSelDTO, obj, pvDTO, commonColumn + priorList.get(j), index, j, isPer, COLUMN_COUNT_DISCOUNT, isPer ? RATE : AMOUNT);
                }
                if (i == dataList.size() - 1) {
                    resultDto.add(pvDTO);
                }
            }
        } else {
            for (Iterator<String> it = projSelDTO.getDiscountNameList().iterator(); it.hasNext();) {
                pvDTO = new ProjectionVarianceDTO();
                List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
                columnList.remove(Constant.GROUP);
                for (String Obj : columnList) {
                    pvDTO.addStringProperties(Obj, DASH);
                }
                resultDto.add(pvDTO);
            }
        }
        LOGGER.info("Ending getCustomisedProjectionResultsTotalDiscount with list size  = = >" + resultDto.size());
        return resultDto;
    }

    /**
     * Used to get the count of discount comes under expanded hierarchy in Total
     * Discount
     *
     * @return
     */
    private String getProgramCountForCurrentHierarchy(PVSelectionDTO projSelDTO) {
        boolean viewFlag = Boolean.FALSE;
        String tableName = viewFlag ? StringUtils.EMPTY : "ST_";

        String query = "IF EXISTS (SELECT 1\n"
                + " FROM " + tableName + "NM_DISCOUNT_PROJ_MASTER B\n"
                + "        JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "          ON B.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "   AND B.DEDUCTION_HIERARCHY_NO=CCP.HIERARCHY_NO  )\n"
                + "   BEGIN \n"
                + "         SELECT COUNT(DISTINCT RS_CONTRACT_SID)\n"
                + " FROM " + tableName + "NM_DISCOUNT_PROJ_MASTER B\n"
                + "        JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "          ON B.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "AND B.DEDUCTION_HIERARCHY_NO=CCP.HIERARCHY_NO\n";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            query += "  WHERE B.RS_CONTRACT_SID IN ( " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " );";
        }
        query += "   END\n"
                + "   ELSE\n"
                + "   BEGIN\n"
                + "         SELECT COUNT(DISTINCT RS_CONTRACT_SID)\n"
                + " FROM  " + tableName + "NM_DISCOUNT_PROJ_MASTER B\n"
                + "        JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "          ON B.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "\n";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            query += "  WHERE B.RS_CONTRACT_SID IN ( " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " );";
        }
        query += "   END ";
        return query;
    }
    
    
    
    private String getRsIdForCurrentHierarchy(PVSelectionDTO projSelDTO) {
        boolean viewFlag = Boolean.FALSE;
        String tableName = viewFlag ? StringUtils.EMPTY : "ST_";

        String query = "IF EXISTS (SELECT 1\n"
                + " FROM " + tableName + "NM_DISCOUNT_PROJ_MASTER B\n"
                + "        JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "          ON B.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "   AND B.DEDUCTION_HIERARCHY_NO=CCP.HIERARCHY_NO  )\n"
                + "   BEGIN \n"
                + "         SELECT DISTINCT RS_CONTRACT_SID \n"
                + " FROM " + tableName + "NM_DISCOUNT_PROJ_MASTER B\n"
                + "        JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "          ON B.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "AND B.DEDUCTION_HIERARCHY_NO=CCP.HIERARCHY_NO\n";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            query += "  WHERE B.RS_CONTRACT_SID IN ( " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " );";
        }
        query += "   END\n"
                + "   ELSE\n"
                + "   BEGIN\n"
                + "         SELECT DISTINCT RS_CONTRACT_SID \n"
                + " FROM  " + tableName + "NM_DISCOUNT_PROJ_MASTER B\n"
                + "        JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "          ON B.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "\n";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            query += "  WHERE B.RS_CONTRACT_SID IN ( " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " );";
        }
        query += "   END ";
        return query;
    }

    private String getProgramCategoryForCurrentHierarchy(PVSelectionDTO projSelDTO) {
        boolean viewFlag = Boolean.FALSE;
        String tableName = viewFlag ? StringUtils.EMPTY : "ST_";
        String discountNoList = StringUtils.EMPTY;
        String indicatorValue = StringUtils.EMPTY;
        discountNoList += SPACE + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
        if (PROGRAM.getConstant().equalsIgnoreCase(projSelDTO.getDiscountLevel())) {
            if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
                indicatorValue = "R";
            }
        } else if (PROGRAM_CATEGORY.getConstant().equalsIgnoreCase(projSelDTO.getDiscountLevel())) {
            indicatorValue = "P";
        }
        String query = "DECLARE @INDICATOR CHAR(1)='" + indicatorValue + "'\n"
                + " \n"
                + "SELECT Count(DISTINCT CASE\n"
                + "                        WHEN @INDICATOR = 'P' THEN REBATE_PROGRAM_TYPE\n"
                + "                        ELSE DISCOUNT_ID\n"
                + "                      END)\n"
                + "FROM   (SELECT DISTINCT RS.RS_CONTRACT_SID AS DISCOUNT_ID,\n"
                + "                        RS.RS_NAME      AS DISCOUNT_NAME,\n"
                + "                        REBATE_PROGRAM_TYPE\n"
                + "        FROM   RS_CONTRACT RS\n"
                + "               INNER JOIN " + tableName + "NM_DISCOUNT_PROJ_MASTER DM\n"
                + "                       ON RS.RS_CONTRACT_SID = DM.RS_CONTRACT_SID\n";
        if (viewFlag) {
            query += " INNER JOIN PROJECTION_DETAILS PD\n"
                    + "  ON PD.PROJECTION_DETAILS_SID = DM.PROJECTION_DETAILS_SID\n";
        } else {
            query += "               INNER JOIN #SELECTED_HIERARCHY_NO PD\n"
                    + "                       ON PD.CCP_DETAILS_SID = DM.CCP_DETAILS_SID\n";
        }
        query += "               JOIN CCP_DETAILS CCP\n"
                + "                 ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                + "                    AND RS.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n"
                + "               INNER JOIN HELPER_TABLE HT\n"
                + "                       ON HT.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE\n"
                + "                          AND HT.LIST_NAME = 'REBATE_PROGRAM_TYPE'\n"
                + "                          AND CASE\n"
                + "                                WHEN HT.DESCRIPTION = 'REIMB' THEN 'REIMBURSEMENT'\n"
                + "                                WHEN HT.DESCRIPTION = 'OI' THEN 'OFF INVOICE'\n"
                + "                                ELSE HT.DESCRIPTION\n"
                + "                              END = DM.PRICE_GROUP_TYPE\n";
        if (viewFlag) {
            query += "WHERE  PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId() + " AND";
        }
        if (!discountNoList.equals(SPACE)) {
            query += "        WHERE  DM.RS_CONTRACT_SID\n"
                    + "                    IN (" + discountNoList + " )\n"
                    + "               AND RS.INBOUND_STATUS <> 'D'\n";
        } else {
            query += "               WHERE RS.INBOUND_STATUS <> 'D'\n";
        }
        
            query     +=  "               AND EXISTS (SELECT 1\n"
                + "                           FROM   CFP_CONTRACT CFP1\n"
                + "                                  JOIN CFP_CONTRACT_DETAILS CFP2\n"
                + "                                    ON CFP1.CFP_CONTRACT_SID = CFP2.CFP_CONTRACT_SID\n"
                + "                                       AND RS.CFP_CONTRACT_SID = CFP1.CFP_CONTRACT_SID\n"
                + "                           WHERE  COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n"
                + "                                  AND CFP1.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID))A";

        return query;
    }

    private String getPPACount(PVSelectionDTO projSelDTO, Boolean isprogram, Boolean isTotal) {
        boolean viewFlag = Boolean.FALSE;
        String tableName = viewFlag ? StringUtils.EMPTY : "ST_";
        String query = " SELECT ";
        if (isprogram) {
            query += " COUNT(DISTINCT TEMP.RS_CONTRACT_SID) ";
        } else {
            query += " CASE WHEN COUNT (DISTINCT TEMP.RS_CONTRACT_SID) >0 THEN 1 ELSE 0 END ";
        }
        query += "FROM " + tableName + "NM_PPA_PROJECTION_MASTER TEMP \n";

        if (!isTotal) {
            query += " JOIN   @CCP CCP ON TEMP.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n";
        }
        if (viewFlag) {
            query += "JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = TEMP.PROJECTION_DETAILS_SID  \n";
        }
        if (!isTotal) {
            query += "JOIN RS_CONTRACT RS ON RS.RS_CONTRACT_SID = TEMP.RS_CONTRACT_SID \n";
        }
        if (viewFlag) {
            query += "WHERE PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();
        }
        return query;
    }

    private int getPPACountValue(PVSelectionDTO projSelDTO) {
        projSelDTO.setTreeLevelNo(1);
        boolean viewFlag = Boolean.FALSE;
        String tableName = viewFlag ? StringUtils.EMPTY : "ST_";
        int count = 0;
        try {
            String query = CommonLogic.getCCPQuery(projSelDTO, Boolean.TRUE) + " SELECT COUNT(DISTINCT RS.RS_NAME) "
                    + "FROM " + tableName + "NM_PPA_PROJECTION_MASTER TEMP \n"
                    + "JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = TEMP.PROJECTION_DETAILS_SID  \n"
                    + " JOIN   @CCP CCP ON pd.ccp_details_sid = ccp.CCP_DETAILS_SID\n"
                    + "JOIN RS_CONTRACT RS ON RS.RS_CONTRACT_SID = TEMP.RS_CONTRACT_SID \n"
                    + "WHERE PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();
            List list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = count + Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return count;
    }

    public ProjectionVarianceDTO calculateDetailTotal(String varaibleName, String varibaleCat, int index, List<Object> gtsList, PVSelectionDTO pvsdto, DecimalFormat format, boolean parentFlag, final PVSelectionDTO selectionDto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        boolean isPer = format.equals(RATE);

        pvDTO.setGroup(varaibleName.concat(varibaleCat));

        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        if (pvsdto.isDiscountFlag() && parentFlag) {
            pvDTO.setParent(1);
        }
        List<Integer> priorList = new ArrayList<>(pvsdto.getProjIdList());
        if (gtsList != null && !gtsList.isEmpty()) {
            for (int i = 0; i < gtsList.size(); i++) {
                final Object[] obj = (Object[]) gtsList.get(i);
                String commonColumn = StringUtils.EMPTY;
                switch (frequencyDivision) {
                    case NumericConstants.FOUR:
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                        break;
                    case NumericConstants.TWO:
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                        break;
                    case 1:
                        commonColumn = StringUtils.EMPTY + obj[0];
                        break;
                    case NumericConstants.TWELVE:
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                        break;
                    default:
                        break;
                }
                PVCommonLogic.customizePeriod(commonColumn, varibaleCat, pvsdto, pvDTO, format, index, obj, isPer);
                for (int j = 0; j < priorList.size(); j++) {
                    PVCommonLogic.getPriorCommonCustomization(varibaleCat, pvsdto, obj, pvDTO, commonColumn + priorList.get(j), index, j, isPer, COLUMN_COUNT_TOTAL, format);
                }
            }
        }
        return pvDTO;
    }

    public void customizePivot(String variableValue, String variableCategory, PVSelectionDTO pvsdto, ProjectionVarianceDTO projDTO, List<String> columnList, DecimalFormat format, int index, Object[] obj,boolean isTotalLevel, final PVSelectionDTO selectionDto) {
        try {
            List<Integer> priorList = new ArrayList<>(pvsdto.getProjIdList());
            PVCommonLogic.customizePeriod(variableValue, variableCategory, pvsdto, projDTO, format, index, obj, format.equals(RATE));
            for (int j = 0; j < priorList.size(); j++) {
                PVCommonLogic.getPriorCommonCustomization(variableCategory, pvsdto, obj, projDTO, variableValue + priorList.get(j), index, j, format.equals(RATE), isTotalLevel ? COLUMN_COUNT_TOTAL : COLUMN_COUNT_DISCOUNT, format);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
    
    public String insertAvailableHierarchyNo(ProjectionSelectionDTO projSelDTO) {
        String sql;
        sql = SQlUtil.getQuery(Constant.SELECTED_HIERARCHY_NO);
        if (projSelDTO.isIsCustomHierarchy()) {
            String currentHierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
            int levelNo = commonLogic.getActualLevelNoFromCustomView(projSelDTO);
            switch (String.valueOf(currentHierarchyIndicator)) {
                case Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY:
                    sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getCustomerHierarchyNo(), currentHierarchyIndicator, levelNo));
                    break;
                case Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY:
                    sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getProductHierarchyNo(), currentHierarchyIndicator, levelNo));
                    break;
                case Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY:
                    sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getDeductionHierarchyNo(), currentHierarchyIndicator, levelNo));
                    break;
                default:
                    LOGGER.warn("Invalid Hierarchy Indicator:" + currentHierarchyIndicator);
            }
        } else {
            sql = sql.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo()));
        }
        sql = sql.replace(Constant.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(projSelDTO));

//        sql += commonLogic.getJoinBasedOnTab(projSelDTO.getTabName(), projSelDTO.getGroupFilter(), projSelDTO.getScreenName());
        LOGGER.debug("Group Filter Value :  " + projSelDTO.getGroupFilter());
        return sql;

    }
    
    public String getHierarchyJoinQuery(ProjectionSelectionDTO projSelDTO) {

        String currentHierarchyIndicator;

        if (projSelDTO.isIsCustomHierarchy()) {
            currentHierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
        } else {
            currentHierarchyIndicator = projSelDTO.getHierarchyIndicator();
        }

        String joinQuery = getHierarchyJoinQuery(projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.getProductHierarchyNo(),projSelDTO.getDeductionHierarchyNo(),currentHierarchyIndicator);
        return joinQuery;
    }
    
    public String getHierarchyJoinQuery(boolean isCustomHierarchy, String customerHierarchyNo, String productHierarchyNo, String deductionHierarchyNo, String hierarchyIndicator) {
        StringBuilder joinQuery = new StringBuilder();
        String dedJoin = StringUtils.EMPTY;

        joinQuery.append(" JOIN ST_CCP_HIERARCHY CH ON ");
        if (isCustomHierarchy) {

            switch (String.valueOf(hierarchyIndicator)) {
                case Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY:
                    joinQuery.append(" CH.CUST_HIERARCHY_NO LIKE A.HIERARCHY_NO + '%' ");
                    if (StringUtils.isNotBlank(productHierarchyNo)) {
                        joinQuery.append("AND CH.PROD_HIERARCHY_NO LIKE '");
                        joinQuery.append(productHierarchyNo);
                        joinQuery.append("%'");
                    }
                    if (StringUtils.isNotBlank(deductionHierarchyNo)) {
                        dedJoin = " AND MAS.DEDUCTION_HIERARCHY_NO LIKE '" + deductionHierarchyNo + "%'";
                    }
                    break;
                case Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY:
                    joinQuery.append(" CH.PROD_HIERARCHY_NO LIKE A.HIERARCHY_NO + '%' ");
                    if (StringUtils.isNotBlank(customerHierarchyNo)) {
                        joinQuery.append("AND CH.CUST_HIERARCHY_NO LIKE '");
                        joinQuery.append(customerHierarchyNo);
                        joinQuery.append("%'");
                    }
                    if (StringUtils.isNotBlank(deductionHierarchyNo)) {
                        dedJoin = " AND MAS.DEDUCTION_HIERARCHY_NO LIKE '" + deductionHierarchyNo + "%'";
                    }
                    break;
                case Constant.INDICATOR_LOGIC_DEDUCTION_HIERARCHY:
//                    joinQuery.append(" MAS.DEDUCTION_HIERARCHY_NO LIKE A.HIERARCHY_NO + '%' ");
                    dedJoin = " AND SPM.DEDUCTION_HIERARCHY_NO LIKE A.HIERARCHY_NO + '%' ";
                    joinQuery.append(" CH.CUST_HIERARCHY_NO LIKE '");
                    joinQuery.append(customerHierarchyNo);
                    joinQuery.append("%'");
                    joinQuery.append(" AND CH.PROD_HIERARCHY_NO LIKE '");
                    joinQuery.append(productHierarchyNo);
                    joinQuery.append("%'");
                    break;
                default:

                    LOGGER.warn("Invalid Hierarchy Indicator: " + hierarchyIndicator);
            }

        } else {
            joinQuery.append(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator) ? "CH.CUST_HIERARCHY_NO " : "CH.PROD_HIERARCHY_NO");
            joinQuery.append(" LIKE A.HIERARCHY_NO + '%' ");
        }
        joinQuery.append(getJoinForDP(dedJoin));

        return joinQuery.toString();
    }
    
    private String getJoinForDP(String deductionJoin) {
        String sql = SQlUtil.getQuery("discount-join");
        sql = sql.replace("?DEDJOIN", deductionJoin);
        sql += " JOIN RS_CONTRACT RSC ON RSC.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID\n ";
        sql += " AND PV_FILTERS=1 \n ";
        return sql;
    }
    
    public String getCCPQueryForPV(ProjectionSelectionDTO projSelDTO) {
        String ccpQuery = QueryUtil.replaceTableNames(insertAvailableHierarchyNo(projSelDTO), projSelDTO.getSessionDTO().getCurrentTableNames());
        ccpQuery += " where PV_FILTERS=1 SELECT * FROM #SELECTED_HIERARCHY_NO ";
        return ccpQuery;
    }
    
    public int getCountForCustomView(final ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        if (projSelDTO.getSessionDTO().getCustomHierarchyMap().get(projSelDTO.getCustomId()).size() < projSelDTO.getTreeLevelNo()) {
            LOGGER.info("Custom view last level");
            return 0;
        }
        String countQuery = insertAvailableHierarchyNo(projSelDTO);
        String selectedHierarchy = getHierarchyJoinQuery(projSelDTO);
        if (!selectedHierarchy.equals(StringUtils.EMPTY)) {
            countQuery += SQlUtil.getQuery("custom-view-count-query");
            countQuery = countQuery.replace(Constant.SELECTED_HIERARCHY_JOIN, selectedHierarchy);
            List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(countQuery, projSelDTO.getSessionDTO().getCurrentTableNames()));
            if (list != null && !list.isEmpty()) {
                count = Integer.valueOf(list.get(0).toString());
            }
        }
        LOGGER.debug("ending getCountForCustomView");
        return count;
    }

    public List getHiearchyNoForCustomView(final ProjectionSelectionDTO projSelDTO, int start, int end) {

        String query = insertAvailableHierarchyNo(projSelDTO);
        query += SQlUtil.getQuery("custom-view-hiearchy-no-query");
        query = query.replace(Constant.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(projSelDTO));
        query = query.replace(Constant.START_QUESTION, String.valueOf(start));
        query = query.replace(Constant.END_QUESTION, String.valueOf(end));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            return list;
        } else {
            return Collections.emptyList();
        }

    }

    private String getQueryForRebatesAndUdcs(final ProjectionSelectionDTO projSelDTO) {
        String query = insertAvailableHierarchyNo(projSelDTO);
        String joinUdcQuery=" JOIN UDCS UD ON UD.MASTER_SID=RS.RS_CONTRACT_SID AND UD.MASTER_TYPE='RS_CONTRACT' ";
        query = query.concat(SQlUtil.getQuery("total-Head"));
        query=query.replace("@SELECTCOLUMN","HT.DESCRIPTION");
        switch(projSelDTO.getSessionDTO().getSelectedDeductionLevelNoPv()){
            case 1: 
                query=query.replace(JOINQUERY," JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.RS_CATEGORY AND HT.HELPER_TABLE_SID in (").concat(removeBracesInList(projSelDTO.getDeductionLevelFilter())).concat(")");
                break;
            case 2:
               query=query.replace(JOINQUERY," JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.RS_TYPE AND HT.HELPER_TABLE_SID in (").concat(removeBracesInList(projSelDTO.getDeductionLevelFilter())).concat(")");
                break;
            case 3:
              query=query.replace(JOINQUERY," JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE AND HT.HELPER_TABLE_SID in (").concat(removeBracesInList(projSelDTO.getDeductionLevelFilter())).concat(")");
               break;
            case 4:
                joinUdcQuery = joinUdcQuery.concat(" JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UD.UDC1 AND HT.HELPER_TABLE_SID in (").concat(removeBracesInList(projSelDTO.getDeductionLevelFilter())).concat(")");
                query=query.replace(JOINQUERY,joinUdcQuery);
                 break;
            case 5:
                joinUdcQuery = joinUdcQuery.concat(" JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UD.UDC2 AND HT.HELPER_TABLE_SID in (").concat(removeBracesInList(projSelDTO.getDeductionLevelFilter())).concat(")");
                query=query.replace(JOINQUERY,joinUdcQuery);
                 break;
            case 6:
                joinUdcQuery = joinUdcQuery.concat(" JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UD.UDC3 AND HT.HELPER_TABLE_SID in (").concat(removeBracesInList(projSelDTO.getDeductionLevelFilter())).concat(")");
                query=query.replace(JOINQUERY,joinUdcQuery);
                 break;
            case 7:
                joinUdcQuery = joinUdcQuery.concat(" JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UD.UDC4 AND HT.HELPER_TABLE_SID in (").concat(removeBracesInList(projSelDTO.getDeductionLevelFilter())).concat(")");
                query=query.replace(JOINQUERY,joinUdcQuery);
                 break;
            case 8:
                joinUdcQuery = joinUdcQuery.concat(" JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UD.UDC5 AND HT.HELPER_TABLE_SID in (").concat(removeBracesInList(projSelDTO.getDeductionLevelFilter())).concat(")");
                query=query.replace(JOINQUERY,joinUdcQuery);
                 break;
            case 9:
                joinUdcQuery = joinUdcQuery.concat(" JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UD.UDC6 AND HT.HELPER_TABLE_SID in (").concat(removeBracesInList(projSelDTO.getDeductionLevelFilter())).concat(")");
                query=query.replace(JOINQUERY,joinUdcQuery);
                 break;
            case 10:
                query=query.replace("HT.DESCRIPTION","ST.RS_CONTRACT_SID");
                  query=query.replace(JOINQUERY,StringUtils.EMPTY);
                   break;
            default:
                LOGGER.info("More Than 10 Levels");
        
        }
        return query;
    }
    
    public List<List<String>> getRsIdsForDiscountAndUdcs(SessionDTO session) {
        List<List<String>> resultList = new ArrayList<>();
        String query = "SELECT RS_CONTRACT_SID FROM ST_NM_DISCOUNT_PROJ_MASTER WHERE PV_FILTERS=1";
        List<String> executedResultList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
        resultList.add(executedResultList);
        return resultList;
    }
    
    public String removeBracesInList(List<String> bracesList) {
        String removedString = StringUtils.EMPTY;
        for (String string : bracesList) {
            removedString =  removedString.concat(",").concat(string) ;
        }
        
        removedString=removedString.replaceFirst(",","");
        return removedString;
    }
    
}
