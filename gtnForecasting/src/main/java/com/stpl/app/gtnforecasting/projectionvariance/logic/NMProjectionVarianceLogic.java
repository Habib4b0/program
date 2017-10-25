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
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.Converters;
import com.stpl.app.gtnforecasting.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
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
import static com.stpl.app.utils.Constants.CommonConstants.CHANGE;
import static com.stpl.app.utils.Constants.ButtonConstants.ALL;
import static com.stpl.app.utils.Constants.FrequencyConstants.DEFAULT_JAVA_DATE_FORMAT;
import static com.stpl.app.utils.Constants.FrequencyConstants.DEFAULT_SQL_DATE_FORMAT;
import static com.stpl.app.utils.Constants.LabelConstants.PERCENT;
import static com.stpl.app.utils.Constants.LabelConstants.TOTAL;
import static com.stpl.app.utils.Constants.LabelConstants.TOTAL_DISCOUNT;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM_CATEGORY;
import static com.stpl.app.utils.Constants.LabelConstants.DASH;
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
    private static final int COLUMN_COUNT_TOTAL = 46;
    private static final int COLUMN_COUNT_DISCOUNT = 32;
    private static final int COLUMN_TOTAL_DISCOUNT = 9;
    private CommonLogic commonLogic = new CommonLogic();
    private static String DASH = "-";
    private static String actualDASH = "-";
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
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                } else {
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    selectionDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
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
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(parentDto.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                } else {
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    selectionDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
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
                selectionDTO.setGroupParent(0);
            }
            if (selectionDTO.getPivotView().equals(Constant.PERIOD)) {
                count += getPeriodResultsCount(selectionDTO, baseVariables, selectionDTO.getDiscountNameList(), selectionDTO.getParentNode(), selectionDTO.getLevelNo(), parentDto, isLevelsCount);
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

    public int getPeriodResultsCount(PVSelectionDTO pVSelectionDTO, PVSelectionDTO baseVariables, List<String> discountList, String parentName, int levelNo, ProjectionVarianceDTO parentDto, boolean isLevelsCount) {
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
                        count += getCustPeriodVarianceCount(baseVariables, pVSelectionDTO);
                    } else {
                        String ccpQuery = Constant.TOTAL.equals(pVSelectionDTO.getLevel()) ? StringUtils.EMPTY : commonLogic.insertAvailableHierarchyNo(pVSelectionDTO);
                        String query = StringUtils.EMPTY;
                        if (Constant.TOTAL.equals(pVSelectionDTO.getLevel())) {
                            List<Object> list = null;
                            if ("Program Category".equals(pVSelectionDTO.getDiscountLevel())) {
                                count += pVSelectionDTO.getDiscountList().get(1).size();
                                //PPA Count
                                query = getPPACount(pVSelectionDTO, Boolean.FALSE, Boolean.TRUE);
                                list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, pVSelectionDTO.getSessionDTO().getCurrentTableNames()), null, null);
                                if (list != null && !list.isEmpty()) {
                                    Object ob = list.get(0);
                                    count = count + Integer.valueOf(String.valueOf(ob));
                                }
                            } else if ("Program".equals(pVSelectionDTO.getDiscountLevel())) {
                                count += pVSelectionDTO.getDiscountList().get(0).size();
                                //PPA Count
                                query = getPPACount(pVSelectionDTO, Boolean.TRUE, Boolean.TRUE);
                                list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, pVSelectionDTO.getSessionDTO().getCurrentTableNames()), null, null);
                                if (list != null && !list.isEmpty()) {
                                    Object ob = list.get(0);
                                    count = count + Integer.valueOf(String.valueOf(ob));
                                }
                            }
                        } else if ("Program Category".equals(pVSelectionDTO.getDiscountLevel())) {
                            query = ccpQuery + getProgramCategoryForCurrentHierarchy(pVSelectionDTO);
                            query = QueryUtil.replaceTableNames(query.replace("@CCP", "#SELECTED_HIERARCHY_NO"), pVSelectionDTO.getSessionDTO().getCurrentTableNames());
                            List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                            //PPA Count
                            query = ccpQuery + getPPACount(pVSelectionDTO, Boolean.FALSE, Boolean.FALSE);
                            query = QueryUtil.replaceTableNames(query.replace("@CCP", "#SELECTED_HIERARCHY_NO"), pVSelectionDTO.getSessionDTO().getCurrentTableNames());
                            list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                        } else if ("Program".equals(pVSelectionDTO.getDiscountLevel())) {
                            query = ccpQuery + getProgramCountForCurrentHierarchy(pVSelectionDTO);
                            query = QueryUtil.replaceTableNames(query.replace("@CCP", "#SELECTED_HIERARCHY_NO"), pVSelectionDTO.getSessionDTO().getCurrentTableNames());
                            List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                            //PPA Count
                            query = ccpQuery + getPPACount(pVSelectionDTO, Boolean.TRUE, Boolean.FALSE);
                            query = QueryUtil.replaceTableNames(query.replace("@CCP", "#SELECTED_HIERARCHY_NO"), pVSelectionDTO.getSessionDTO().getCurrentTableNames());
                            list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
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
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        List<Object> dataList = new ArrayList<Object>();
        List< ProjectionVarianceDTO> tobeAddedList = new ArrayList<ProjectionVarianceDTO>();
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
                        maxRecord = getCustPeriodVarianceCount(baseVariables, pVSelectionDTO);
                    } else {
                        maxRecord = pVSelectionDTO.getPeriodList().size() + 1;
                    }
                    if (started < maxRecord) {
                        if (pivotTotalList.isEmpty()) {
                            getTotalPivotVariance(pVSelectionDTO);
                        }
                        if (isDiscountExpand) {
                            List<Object> list = null;
                            boolean isDetail = pVSelectionDTO.getLevel().equals(DETAIL);
                            pVSelectionDTO.setIsTotal(false);
                            pVSelectionDTO.setDiscountGroupName(parentDto.getGroup());
                            pVSelectionDTO.setDiscountIndex(0);
                            if (isDetail) {
                                list = getDiscountExpandQuery(pVSelectionDTO).get(0);
                                tobeAddedList = loadDiscounts(list, StringUtils.EMPTY, pVSelectionDTO, parentDto);
                            } else {
                                List<Object> totalList = null;
                                int count = 0;
                                String query = StringUtils.EMPTY;
                                if ("Program Category".equals(pVSelectionDTO.getDiscountLevel())) {
//                                count += pVSelectionDTO.getDiscountList().get(1).size();
                                    //PPA Count
                                    query = getPPACount(pVSelectionDTO, Boolean.FALSE, Boolean.TRUE);
                                    totalList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, pVSelectionDTO.getSessionDTO().getCurrentTableNames()), null, null);
                                    if (totalList != null && !totalList.isEmpty()) {
                                        Object ob = totalList.get(0);
                                        count = count + Integer.valueOf(String.valueOf(ob));
                                    }
                                } else if ("Program".equals(pVSelectionDTO.getDiscountLevel())) {
//                                count += pVSelectionDTO.getDiscountList().get(0).size();
                                    //PPA Count
                                    query = getPPACount(pVSelectionDTO, Boolean.TRUE, Boolean.TRUE);
                                    totalList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, pVSelectionDTO.getSessionDTO().getCurrentTableNames()), null, null);
                                    if (totalList != null && !totalList.isEmpty()) {
                                        Object ob = totalList.get(0);
                                        count = count + Integer.valueOf(String.valueOf(ob));
                                    }
                                }
//                            getPPACountValue(pVSelectionDTO);
                                if (!pVSelectionDTO.getDiscountNameList().isEmpty() || count > 0) {
                                    getTotalDiscount(pVSelectionDTO);
                                    if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_PER_EXFAC)) {
                                        tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, Constant.DISCOUNT_SMALL, pivotDiscountList, pVSelectionDTO, isDetail, NumericConstants.TEN, Boolean.TRUE));
                                    } else if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT)) {
                                        tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, Constant.DISCOUNT_SMALL, pivotDiscountList, pVSelectionDTO, isDetail, NumericConstants.FOUR, Boolean.FALSE));
                                    } else if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_RATE)) {
                                        tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, Constant.DISCOUNT_SMALL, pivotDiscountList, pVSelectionDTO, isDetail, NumericConstants.SIX, Boolean.TRUE));
                                    } else {
                                        tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, Constant.DISCOUNT_SMALL, pivotDiscountList, pVSelectionDTO, isDetail, NumericConstants.EIGHT, Boolean.FALSE));
                                    }
                                }
                            }

                        } else {
                            if (pVSelectionDTO.getLevel().equals(DETAIL)) {
                                dataList = getProjVarianceResults(pVSelectionDTO, parentDto);
                            }
                            List<ProjectionVarianceDTO> allList = getCustPeriodVariance(pivotTotalList, dataList, pVSelectionDTO, parentDto, baseVariables);
                            tobeAddedList.addAll(allList);
                        }
                        setChartList(tobeAddedList);
                        for (int i = started; (i < tobeAddedList.size()) && (neededRecord > 0); i++) {
                            projDTOList.add(tobeAddedList.get(i));
                            neededRecord--;
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
                    } else if (dto.getHierarchyIndicator().equals(P)) {
                        dto.setProductHierarchyNo(dto.getHierarchyNo());
                        dto.setCustomerHierarchyNo(pVSelectionDTO.getCustomerHierarchyNo());
                    }
                    dto.setParent(1);
                    projDTOList.add(dto);
                    neededRecord--;
                } else {
                    pVSelectionDTO.setTreeLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                    pVSelectionDTO.setLevelNo(pVSelectionDTO.getTreeLevelNo() + 1);
                    List<ProjectionVarianceDTO> nextLevelList = configureLevels(start, neededRecord, pVSelectionDTO, maxRecord);
                    projDTOList.addAll(nextLevelList);
                }
            }
        return projDTOList;
    }

    /**
     * Discount procedure
     *
     * @param projSelDTO
     */
    void getTotalDiscount(PVSelectionDTO projSelDTO) {
        if (pivotDiscountList.isEmpty()) {
            String frequency = projSelDTO.getFrequency();
            String discountId = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
            List<String> projectionIdList = new ArrayList<String>();
            pivotDiscountList = new ArrayList<Object>();
            if (frequency.equals(Constant.QUARTERLY)) {
                frequency = "QUARTERLY";
            } else if (frequency.equals(Constant.SEMI_ANNUALLY)) {
                frequency = "SEMI-ANNUAL";
            } else if (frequency.equals(Constant.MONTHLY)) {
                frequency = "MONTHLY";
            } else {
                frequency = "ANNUAL";
            }
            projectionIdList.add(String.valueOf(selectionDTO.getCurrentProjId()));
            for (Integer projId : projSelDTO.getProjIdList()) {
                projectionIdList.add(String.valueOf(projId));
            }
            String projectionId = CommonUtils.CollectionToString(projectionIdList, false);
            List<Object[]> discountsList = null;
                Object[] orderedArg = {projectionId, frequency, discountId, "VARIANCE", projSelDTO.getSessionDTO().getSessionId(), projSelDTO.getUserId(), Constant.STRING_ONE, projSelDTO.getDiscountLevel()};
                discountsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS_DISCOUNT", orderedArg);
          
            pivotDiscountList.addAll(discountsList);
        }
    }

    /**
     * Common Customization For All the Discount levels
     *
     * @param discountType
     * @param dataList
     * @param projSelDTO
     * @param isDetail
     * @param index
     * @return
     */
    private List<ProjectionVarianceDTO> commonCustomizationForTotalDiscount(ProjectionVarianceDTO parentDto, String discountType, List<Object> dataList, PVSelectionDTO projSelDTO, boolean isDetail, int index, boolean isPer) {
        LOGGER.info("Inside commonCustomizationForTotalDiscount");
        List<ProjectionVarianceDTO> dto = new ArrayList<ProjectionVarianceDTO>();
        if (discountType.equals(Constant.PPA_SMALL)) {
            if (!isDetail) {
                if (baseVariables.isColValue() && parentDto.getGroup().contains(CommonUtils.COL_VALUE)) {
                    projSelDTO.setVarIndicator(VALUE.getConstant());
                    dto.add(getCustomizedPPA(dataList, projSelDTO, isDetail, index, isPer));
                    return dto;
                }
                if (baseVariables.isColVariance() && parentDto.getGroup().contains(CommonUtils.COL_VARIANCE)) {
                    projSelDTO.setVarIndicator(VARIANCE.getConstant());
                    dto.add(getCustomizedPPA(dataList, projSelDTO, isDetail, index, isPer));
                    return dto;
                }
                if (baseVariables.isColPercentage() && parentDto.getGroup().contains("Change")) {
                    projSelDTO.setVarIndicator(CHANGE.getConstant());
                    dto.add(getCustomizedPPA(dataList, projSelDTO, isDetail, index, isPer));
                    return dto;
                }
            } else {
                dto.add(getCustomizedPPA(dataList, projSelDTO, isDetail, 0, isPer));
                return dto;
            }
        } else if (discountType.equals("Returns")) {
            if (!isDetail) {
                if (baseVariables.isColValue() && parentDto.getGroup().contains(CommonUtils.COL_VALUE)) {
                    projSelDTO.setVarIndicator(VALUE.getConstant());
                    dto.add(getCustomizedReturns(dataList, projSelDTO, isDetail, index, isPer));
                    return dto;
                }
                if (baseVariables.isColVariance() && parentDto.getGroup().contains(CommonUtils.COL_VARIANCE)) {
                    projSelDTO.setVarIndicator(VARIANCE.getConstant());
                    dto.add(getCustomizedReturns(dataList, projSelDTO, isDetail, index, isPer));
                    return dto;
                }
                if (baseVariables.isColPercentage() && parentDto.getGroup().contains("Change")) {
                    projSelDTO.setVarIndicator(CHANGE.getConstant());
                    dto.add(getCustomizedReturns(dataList, projSelDTO, isDetail, index, isPer));
                    return dto;
                }
            } else {
                dto.add(getCustomizedReturns(dataList, projSelDTO, isDetail, 0, isPer));
                return dto;
            }
        } else if (!isDetail) {
            if (baseVariables.isColValue() && parentDto.getGroup().contains(CommonUtils.COL_VALUE)) {
                projSelDTO.setVarIndicator(VALUE.getConstant());
                dto.addAll(getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, index, isPer));
                return dto;
            }
            if (baseVariables.isColVariance() && parentDto.getGroup().contains(CommonUtils.COL_VARIANCE)) {
                projSelDTO.setVarIndicator(VARIANCE.getConstant());
                dto.addAll(getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, index, isPer));
                return dto;
            }
            if (baseVariables.isColPercentage() && parentDto.getGroup().contains("Change")) {
                projSelDTO.setVarIndicator(CHANGE.getConstant());
                dto.addAll(getCustomisedProjectionResultsTotalDiscount(dataList, projSelDTO, index, isPer));
                return dto;
            }
        }
        LOGGER.info("Ending commonCustomizationForTotalDiscount");
        return null;
    }

    /**
     * get Customized period variance
     *
     * @return List
     */
    public int getCustPeriodVarianceCount(final PVSelectionDTO baseVariables, final PVSelectionDTO pvsdto) {
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
        return count;
    }

    public int configureLevelsCount(PVSelectionDTO selection) {
        CommonLogic commonLogic = new CommonLogic();
        int count;
        if (selection.isIsCustomHierarchy()) {
            count = commonLogic.getCountForCustomView(selection);
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
                resultStart = (start <= maxRecord) ? 0 : start - maxRecord;
            }

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
        if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
            dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
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

        ProjectionVarianceDTO parentDto = new ProjectionVarianceDTO();
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        if (started < maxRecord || pvsdto.getLevel().equals(TOTAL.getConstant())) {
            getTotalPivotVariance(pvsdto);
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                List<Object> currentPivotDiscount = new ArrayList<Object>();
                if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {

                    getTotalDiscount(pvsdto);
                }
                List<ProjectionVarianceDTO> finalList = getCustomizedPivotTotalResults(pivotTotalList, currentPivotDiscount, pivotPriorProjIdList, pvsdto, baseVariables, pivotDiscountList);
                for (int i = started; (i < finalList.size()) && (neededRecord > 0); i++) {
                    projDTOList.add(finalList.get(i));
                    neededRecord--;
                }
            } else if (parent instanceof ProjectionVarianceDTO) {
                parentDto = (ProjectionVarianceDTO) parent;
                if ("All Sales Group".equalsIgnoreCase(parentDto.getGroup()) || "All Discount Group".equalsIgnoreCase(parentDto.getGroup())
                        || "All PPA Group".equalsIgnoreCase(parentDto.getGroup())) {
                    maxRecord = -1;
                } else {
                    List<ProjectionVarianceDTO> dto = getDetailsPivotVariance(pvsdto, parentDto);
                    for (int i = started; (i < dto.size()) && (neededRecord > 0); i++) {
                        projDTOList.add(dto.get(i));
                        neededRecord--;
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
                } else if (dto.getHierarchyIndicator().equals(P)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(pvsdto.getCustomerHierarchyNo());
                }
                dto.setParent(1);
                projDTOList.add(dto);
                neededRecord--;
            } else {
                pvsdto.setTreeLevelNo(pvsdto.getTreeLevelNo() + 1);
                pvsdto.setLevelNo(pvsdto.getTreeLevelNo() + 1);
                List<ProjectionVarianceDTO> resultList = configureLevels(start, neededRecord, pvsdto, maxRecord);
                projDTOList.addAll(resultList);
            }
        }
        return projDTOList;
    }

    public List<ProjectionVarianceDTO> getDetailsPivotVariance(final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto) {
        List<Object> currentPivotDiscount = new ArrayList<Object>();
        pvsdto.setYear("ALL");
        pvsdto.setProjectionId(selectionDTO.getCurrentProjId());
        String priorQuery = priorlistQuery(pvsdto);
        String ccpQuery = commonLogic.insertAvailableHierarchyNo(pvsdto)
                + "\n" + CommonLogic.getTempCCPQueryForCOGS(pvsdto);
        String query = priorQuery + ccpQuery + "\n" + queryUtils.getProjectionResultsMainPivotQuery(pvsdto);
        List<Object> currentPivotDetails = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query.replace("@CCP", "#SELECTED_HIERARCHY_NO"), pvsdto.getSessionDTO().getCurrentTableNames()), null, null);
        if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {

            String discountQuery = priorQuery + ccpQuery + " \n" + CommonLogic.tempRSTablePrevious(pvsdto) + " \n" + CommonLogic.tempRSTablePivot(pvsdto) + "\n" + queryUtils.getPVMainDiscountPivotQuery(pvsdto) + "\n" + CommonLogic.rsTempTableSelectPivot(pvsdto);
            currentPivotDiscount = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(discountQuery.replace("@CCP", "#SELECTED_HIERARCHY_NO"), pvsdto.getSessionDTO().getCurrentTableNames()), null, null);
        }
        List<Integer> priorProjIdList = new ArrayList<Integer>();
        for (Integer projId : pvsdto.getProjIdList()) {
            priorProjIdList.add(projId);
        }
        List<ProjectionVarianceDTO> projDTOList = getCustomizedPivotDetailResults(pivotTotalList, currentPivotDetails, currentPivotDiscount, pvsdto.getDiscountNameList(), parentDto, priorProjIdList, pvsdto, baseVariables);
        return projDTOList;
    }

    public void getTotalPivotVariance(PVSelectionDTO pvsdto) {
        List< Object[]> gtsResult = null;
        String frequency = pvsdto.getFrequency();
        String discountId = CommonUtils.CollectionToString(pvsdto.getDiscountNoList(), false);
        List<String> projectionIdList = new ArrayList<String>();
        pivotTotalList = new ArrayList<Object>();
        pivotPriorProjIdList = new ArrayList<Integer>();
        if (frequency.equals(Constant.QUARTERLY)) {
            frequency = "QUARTERLY";
        } else if (frequency.equals(Constant.SEMI_ANNUALLY)) {
            frequency = "SEMI-ANNUAL";
        } else if (frequency.equals(Constant.MONTHLY)) {
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
            Object[] orderedArg = {projectionId, frequency, discountId, "VARIANCE", pvsdto.getSessionDTO().getSessionId(), pvsdto.getUserId(), "PIVOT"};
            gtsResult = CommonLogic.callProcedure(PRC_PROJ_RESULTS, orderedArg);
        pivotTotalList.addAll(gtsResult);
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
            frequency = "QUARTERLY";
        } else if (frequency.equals(Constant.SEMI_ANNUALLY)) {
            frequency = "SEMI_ANNUAL";
        } else if (frequency.equals(Constant.MONTHLY)) {
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
        return null;

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
        LOGGER.info("Inside getDiscountExpandQuery");
        List<List> projDTOList = new ArrayList<List>();
        List<Object> list0;
        String priorQuery = priorlistQuery(projSelDTO);
        String query = StringUtils.EMPTY;
        String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(projSelDTO);
        if (!projSelDTO.isIsTotal()) {
            query = priorQuery + ccpQuery + CommonLogic.getTempCCPQueryForCOGS(projSelDTO) + " \n" + CommonLogic.tempRSTablePrevious(projSelDTO) + " \n" + CommonLogic.tempRSTable(projSelDTO)
                    + " \n " + queryUtils.getPeriodDiscountExpand(projSelDTO) + "\n" + CommonLogic.rsTempTableSelect(projSelDTO);
            query = query.replace("@CCP", "#SELECTED_HIERARCHY_NO");
            String querr = QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames());
            list0 = (List<Object>) CommonLogic.executeSelectQuery(querr, null, null);
            projDTOList.add(list0);
        }
        LOGGER.info("Ending getDiscountExpandQuery");
        return projDTOList;
    }

    public List<ProjectionVarianceDTO> getCustomizedPivotTotalResults(final List<Object> results, List discountList, List<Integer> priorProjGtsList, PVSelectionDTO pvsdto, PVSelectionDTO baseVariables, List totalDiscount) {
        List<String> periodList = new ArrayList<String>(pvsdto.getPeriodList());
        Map<String, String> periodListMap = new HashMap<String, String>(pvsdto.getPeriodListMap());
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        String[] singleColumn = new String[NumericConstants.THREE];
        int frequencyDivision = pvsdto.getFrequencyDivision();
        boolean actualBasis = "Actuals".equalsIgnoreCase(pvsdto.getComparisonBasis());
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
                if (periodList.contains(pcommonColumn)) {
                    periodList.remove(pcommonColumn);
                    List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
                    columnList.remove(Constant.GROUP);
                    ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                    projDTO.setGroup(commonHeader);

                    String variableValue = StringUtils.EMPTY;
                    //Exfactory Sales
                    if ((baseVariables.isVarExFacSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWO];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THREE];
                        if (baseVariables.isColValue()) {
                            customizePivot("ExFacValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("ExFacVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("ExFacPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Demand Sales
                    if ((baseVariables.isVarDemandSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FOUR];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FIVE];
                        if (baseVariables.isColValue()) {
                            customizePivot("DemandSalesValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("DemandSalesVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("DemandSalesPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Inventory Withdrawal sales
                    if ((baseVariables.isVarInvSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.SIX];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.SEVEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("InvWithValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("InvWithVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("InvWithPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Percentage of Exfactory Sales
                    if ((baseVariables.isVarPerExFacSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.EIGHT];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.NINE];
                        if (baseVariables.isColValue()) {
                            customizePivot("PerExFacValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("PerExFacVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("PerExFacPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Percentage of Demand Sales
                    if ((baseVariables.isVarPerDemandSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TEN];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.ELEVEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("PerDemandSalesValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("PerDemandSalesVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("PerDemandSalesPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Percentage Inventory Withdrawal Value
                    if ((baseVariables.isVarPerInvSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWELVE];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTEEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("PerInvWithValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("PerInvWithVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("PerInvWithPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Contract Sales
                    if ((baseVariables.isVarContractsales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FOURTEEN];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTEEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("ContractSalesWACValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("ContractSalesWACVar", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("ContractSalesWACVarPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Contract Units
                    if ((baseVariables.isVarContractUnits())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.SIXTEEN];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.SEVENTEEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("ContractUnitsValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT_UNITS);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("ContractUnitsVar", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT_UNITS);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("ContractUnitsPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Discount Amount
                    if ((baseVariables.isVarDisAmount())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.EIGHTEEN];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.NINETEEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("DiscountAmountValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("DiscountAmountVar", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("DiscountAmountPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Discount Percentage
                    if ((baseVariables.isVarDisRate())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWENTY];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_ONE];
                        if (baseVariables.isColValue()) {
                            customizePivot("DiscountSalesValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("DiscountSalesVar", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("DiscountSalesPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //RPU
                    if ((baseVariables.isVarRPU())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_SIX];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_SEVEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("RPUValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("RPUVar", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("RPUPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //NetSales
                    if ((baseVariables.isVarNetSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_TWO];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_THREE];
                        if (baseVariables.isColValue()) {
                            customizePivot("NetSalesValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("NetSalesVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("NetSalesPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //COGS
                    if ((baseVariables.isVarCOGC())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_FOUR];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_FIVE];
                        if (baseVariables.isColValue()) {
                            customizePivot("COGCValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("COGCVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("COGCPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Net Profit
                    if ((baseVariables.isVarNetProfit())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_SIX];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_SEVEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("NetProfitValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("NetProfitVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("NetProfitPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Net Sales ExFactory Percentage
                    if ((baseVariables.isNetSalesExFactory())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FORTY_FOUR];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_FIVE];
                        if (baseVariables.isColValue()) {
                            customizePivot("NetSalesExFactoryValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("NetSalesExFactoryVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("NetSalesExFactoryPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //DiscountPerExFactory
                    if ((baseVariables.isDiscountPerExFactory())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FORTY_SIX];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_SEVEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("DiscountPerExFactoryValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("DiscountPerExFactoryVar", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("DiscountPerExFactoryPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    for (int j = 0; j < priorProjGtsList.size(); j++) {
                        String actualValueCurrent;
                        //ExFacSales
                        if (baseVariables.isVarExFacSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWO];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THREE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THREE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "ExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ExFacVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "ExFacPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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

                        if (baseVariables.isVarDemandSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FOUR];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FIVE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "DemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DemandSalesVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "DemandSalesPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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

                        //Inventory 
                        if (baseVariables.isVarInvSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.SIX];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.SEVEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.SEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "InvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "InvWithVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "InvWithPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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

                        //Percentage of Ex Fac
                        if (baseVariables.isVarPerExFacSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.EIGHT];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.NINE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.NINE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "PerExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerExFacVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "PerExFacPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarPerDemandSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TEN];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.ELEVEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.ELEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "PerDemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerDemandSalesVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "PerDemandSalesPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        //Percentage of Inv
                        if (baseVariables.isVarPerInvSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWELVE];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTEEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THIRTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "PerInvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerInvWithVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "PerInvWithPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarContractsales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FOURTEEN];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTEEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FIFTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "ContractSalesWACValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ContractSalesWACVar" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "ContractSalesWACVarPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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

                        if (baseVariables.isVarContractUnits()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.SIXTEEN];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.SEVENTEEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.SEVENTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "ContractUnitsValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT_UNITS, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ContractUnitsVar" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT_UNITS);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT_UNITS);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "ContractUnitsPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarDisRate()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWENTY];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_ONE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_ONE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "DiscountSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountSalesVar" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "DiscountSalesPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarDisAmount()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.EIGHTEEN];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.NINETEEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.NINETEEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "DiscountAmountValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountAmountVar" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "DiscountAmountPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarRPU()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.THIRTY];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_ONE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_ONE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "RPUValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "RPUVar" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "RPUPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarNetSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.THIRTY_TWO];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_THREE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_THREE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "NetSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetSalesVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "NetSalesPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarCOGC()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.THIRTY_FOUR];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_FIVE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "COGCValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "COGCVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "COGCPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarNetProfit()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.THIRTY_SIX];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_SEVEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_SEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "NetProfitValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetProfitVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "NetProfitPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isNetSalesExFactory()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FORTY_FOUR];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_FIVE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FORTY_FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "NetSalesExFactoryValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetSalesExFactoryVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "NetSalesExFactoryPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isDiscountPerExFactory()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FORTY_SIX];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FORTY_SEVEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FORTY_SEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "DiscountPerExFactoryValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountPerExFactoryVar" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "DiscountPerExFactoryPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                            if (pcommonColumn.equals(dcommonColumn)) {
                                for (int disc = 0; disc < noOfDiscount.size(); disc++) {
                                    String head = String.valueOf(discountRow[NumericConstants.TWO]).replace(" ", StringUtils.EMPTY) + disc;

                                    if ((baseVariables.isVarDisAmount())) {
                                        actualValue = StringUtils.EMPTY + discountRow[NumericConstants.THREE];
                                        currentValue = StringUtils.EMPTY + discountRow[NumericConstants.FOUR];
                                        boolean actualCheck = "null".equalsIgnoreCase(actualValue);
                                        if ((baseVariables.isColValue())) {
                                            //Actual
                                            columnAct = "DiscountAmountValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                            String baseValueAct = getFormattedValue(AMOUNT, actualValue);
                                            if (!actualCheck) {
                                                if (pvsdto.hasColumn(columnAct)) {
                                                    projDTO.addStringProperties(columnAct, baseValueAct);
                                                    columnList.remove(columnAct);
                                                }
                                            } else if (pvsdto.hasColumn(columnAct)) {
                                                projDTO.addStringProperties(columnAct, actualDASH);
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
                                            if (!actualCheck) {
                                                if ((baseVariables.isColVariance())) {
                                                    column = "DiscountAmountVar" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //  for CURRENT
                                                    String val = getVariance(actualValue, currentValue, AMOUNT);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val);
                                                        columnList.remove(column);
                                                    }

                                                }
                                                if ((baseVariables.isColPercentage())) {
                                                    column = "DiscountAmountPer" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //for CURRENT
                                                    String val = getPerChange(actualValue, currentValue, AMOUNT);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }
                                            } else if ((baseVariables.isColValue())) {
                                                column = "DiscountAmountValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, actualDASH);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                    }

                                    if ((baseVariables.isVarDisRate())) {
                                        actualValue = StringUtils.EMPTY + discountRow[NumericConstants.FIVE];
                                        currentValue = StringUtils.EMPTY + discountRow[NumericConstants.SIX];
                                        boolean actualCheck = "null".equalsIgnoreCase(actualValue);
                                        if ((baseVariables.isColValue())) {
                                            //Actual
                                            columnAct = "DiscountSalesValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                            String baseValueAct = getFormattedValue(RATE, actualValue);
                                            if (!actualCheck) {
                                                if (pvsdto.hasColumn(columnAct)) {
                                                    projDTO.addStringProperties(columnAct, baseValueAct);
                                                    columnList.remove(columnAct);
                                                }
                                            } else if (pvsdto.hasColumn(columnAct)) {
                                                projDTO.addStringProperties(columnAct, actualDASH);
                                                columnList.remove(columnAct);
                                            }
                                            //Current
                                            column = "DiscountSalesValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            baseValueAct = getFormattedValue(RATE, currentValue);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValueAct);
                                                columnList.remove(column);
                                            }
                                        }

                                        if (actualBasis) {
                                            if (!actualCheck) {
                                                if ((baseVariables.isColVariance())) {
                                                    column = "DiscountSalesVar" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //  for CURRENT
                                                    String val = getVariance(actualValue, currentValue, AMOUNT);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val);
                                                        columnList.remove(column);
                                                    }

                                                }
                                                if ((baseVariables.isColPercentage())) {
                                                    column = "DiscountSalesPer" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //for CURRENT
                                                    String val = getPerChange(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }
                                            } else if ((baseVariables.isColValue())) {
                                                column = "DiscountSalesValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, actualDASH);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                    }

                                    if ((baseVariables.isVarRPU())) {
                                        actualValue = StringUtils.EMPTY + discountRow[NumericConstants.SEVEN];
                                        currentValue = StringUtils.EMPTY + discountRow[NumericConstants.EIGHT];
                                        boolean actualCheck = "null".equalsIgnoreCase(actualValue);
                                        if ((baseVariables.isColValue())) {
                                            //Actual
                                            columnAct = "RPUValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                            String baseValueAct = getFormattedValue(RATE, actualValue);
                                            if (!actualCheck) {
                                                if (pvsdto.hasColumn(columnAct)) {
                                                    projDTO.addStringProperties(columnAct, baseValueAct);
                                                    columnList.remove(columnAct);
                                                }
                                            } else if (pvsdto.hasColumn(columnAct)) {
                                                projDTO.addStringProperties(columnAct, actualDASH);
                                                columnList.remove(columnAct);
                                            }
                                            //Current
                                            column = "RPUValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            baseValueAct = getFormattedValue(RATE, currentValue);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValueAct);
                                                columnList.remove(column);
                                            }
                                        }
                                        if (actualBasis) {
                                            if (!actualCheck) {
                                                if ((baseVariables.isColVariance())) {
                                                    column = "RPUVar" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //  for CURRENT
                                                    String val = getVariance(actualValue, currentValue, AMOUNT);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val);
                                                        columnList.remove(column);
                                                    }

                                                }
                                                if ((baseVariables.isColPercentage())) {
                                                    column = "RPUPer" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //for CURRENT
                                                    String val = getPerChange(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }
                                            } else if ((baseVariables.isColValue())) {
                                                column = "RPUValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, actualDASH);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                    }
                                    //discount per of exfactory
                                    if ((baseVariables.isDiscountPerExFactory())) {
                                        actualValue = StringUtils.EMPTY + discountRow[NumericConstants.NINE];
                                        currentValue = StringUtils.EMPTY + discountRow[NumericConstants.TEN];
                                        boolean actualCheck = "null".equalsIgnoreCase(actualValue);
                                        if ((baseVariables.isColValue())) {
                                            //Actual
                                            columnAct = "DiscountPerExFactoryValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                            String baseValueAct = getFormattedValue(RATE, actualValue);
                                            if (!actualCheck) {
                                                if (pvsdto.hasColumn(columnAct)) {
                                                    projDTO.addStringProperties(columnAct, baseValueAct);
                                                    columnList.remove(columnAct);
                                                }
                                            } else if (pvsdto.hasColumn(columnAct)) {
                                                projDTO.addStringProperties(columnAct, actualDASH);
                                                columnList.remove(columnAct);
                                            }
                                            //Current
                                            column = "DiscountPerExFactoryValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            baseValueAct = getFormattedValue(RATE, currentValue);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValueAct);
                                                columnList.remove(column);
                                            }
                                        }
                                        if (actualBasis) {
                                            if (!actualCheck) {
                                                if ((baseVariables.isColVariance())) {
                                                    column = "DiscountPerExFactoryVar" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //  for CURRENT
                                                    String val = getVariance(actualValue, currentValue, AMOUNT);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val);
                                                        columnList.remove(column);
                                                    }

                                                }
                                                if ((baseVariables.isColPercentage())) {
                                                    column = "DiscountPerExFactoryPer" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //for CURRENT
                                                    String val = getPerChange(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }
                                            } else if ((baseVariables.isColValue())) {
                                                column = "DiscountPerExFactoryValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, actualDASH);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                    }

                                    for (int k = 0; k < priorProjGtsList.size(); k++) {
                                        //Discount Percentage
                                        if (baseVariables.isVarDisRate()) {
                                            singleColumn[0] = "DiscountSalesValue" + head + priorProjGtsList.get(k);
                                            singleColumn[1] = "DiscountSalesVar" + head + priorProjGtsList.get(k);
                                            singleColumn[NumericConstants.TWO] = "DiscountSalesPer" + head + priorProjGtsList.get(k);
                                            getPivotCommonCustomization(pvsdto, discountRow, projDTO, singleColumn, NumericConstants.SIX, k, NumericConstants.EIGHT, columnList, Boolean.TRUE);
                                        }

                                        //Discount Dollor
                                        if (baseVariables.isVarDisAmount()) {
                                            singleColumn[0] = "DiscountAmountValue" + head + priorProjGtsList.get(k);
                                            singleColumn[1] = "DiscountAmountVar" + head + priorProjGtsList.get(k);
                                            singleColumn[NumericConstants.TWO] = "DiscountAmountPer" + head + priorProjGtsList.get(k);
                                            getPivotCommonCustomization(pvsdto, discountRow, projDTO, singleColumn, NumericConstants.FOUR, k, NumericConstants.EIGHT, columnList, Boolean.FALSE);
                                        }
                                        //RPU
                                        if (baseVariables.isVarRPU()) {
                                            singleColumn[0] = "RPUValue" + head + priorProjGtsList.get(k);
                                            singleColumn[1] = "RPUVar" + head + priorProjGtsList.get(k);
                                            singleColumn[NumericConstants.TWO] = "RPUPer" + head + priorProjGtsList.get(k);
                                            getPivotCommonCustomization(pvsdto, discountRow, projDTO, singleColumn, NumericConstants.EIGHT, k, NumericConstants.EIGHT, columnList, Boolean.FALSE);
                                        }
                                        //Discount Per Exfactory
                                        if (baseVariables.isDiscountPerExFactory()) {
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
                        if (baseVariables.isColValue()) {
                            column = "DiscountAmountValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[NumericConstants.TWELVE];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // PPA Total Discount %
                        if (baseVariables.isColValue()) {
                            column = "DiscountSalesValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[NumericConstants.THIRTEEN];
                            String baseValue = getFormattedValue(RATE, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                        // PPA Total Discount RPU
                        if (baseVariables.isColValue()) {
                            column = "RPUValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[NumericConstants.FIFTEEN];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Returns Total Discount $
                        if (baseVariables.isColValue()) {
                            column = "DiscountAmountValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[NumericConstants.TWENTY_ONE];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Returns Total Discount %
                        if (baseVariables.isColValue()) {
                            column = "DiscountSalesValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[NumericConstants.TWENTY_TWO];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                        // Returns Total Discount RPU
                        if (baseVariables.isColValue()) {
                            column = "RPUValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[NumericConstants.TWENTY];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        for (int j = 0; j < priorProjGtsList.size(); j++) {
                            //Discount Percentage
                            if (baseVariables.isVarDisRate()) {
                                //PPA
                                singleColumn[0] = "DiscountSalesValuePPA" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountSalesVarPPA" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "DiscountSalesPerPPA" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.THIRTEEN, j, NumericConstants.TWENTY_ONE, columnList, Boolean.TRUE);
                                //Returns
                                singleColumn[0] = "DiscountSalesValueReturns" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountSalesVarReturns" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "DiscountSalesPerReturns" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.TWENTY_TWO, j, NumericConstants.TWENTY_ONE, columnList, Boolean.TRUE);
                            }
                            //Discount Dollar
                            if (baseVariables.isVarDisAmount()) {
                                //PPA
                                singleColumn[0] = "DiscountAmountValuePPA" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountAmountVarPPA" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "DiscountAmountPerPPA" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.TWELVE, j, NumericConstants.TWENTY_ONE, columnList, Boolean.FALSE);
                                //Returns
                                singleColumn[0] = "DiscountAmountValueReturns" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountAmountVarReturns" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "DiscountAmountPerReturns" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.TWENTY_ONE, j, NumericConstants.TWENTY_ONE, columnList, Boolean.FALSE);
                            }
                            //RPU
                            if (baseVariables.isVarRPU()) {
                                //PPA
                                singleColumn[0] = "RPUValuePPA" + priorProjGtsList.get(j);
                                singleColumn[1] = "RPUVarPPA" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "RPUPerPPA" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.FIFTEEN, j, NumericConstants.TWENTY_ONE, columnList, Boolean.FALSE);
                                //Returns
                                singleColumn[0] = "RPUValueReturns" + priorProjGtsList.get(j);
                                singleColumn[1] = "RPUVarReturns" + priorProjGtsList.get(j);
                                singleColumn[NumericConstants.TWO] = "RPUPerReturns" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, NumericConstants.TWENTY, j, NumericConstants.TWENTY_ONE, columnList, Boolean.FALSE);
                            }
                        }
                    }
                    projDTOList.add(projDTO);
                }
            }
        }
        List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
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
        totalDTO.setGroup(Constant.PROJECTION_TOTAL);
        projDTOList.add(0, totalDTO);
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
        LOGGER.debug("Inside getPivotCommonCustomization");
        String visibleColumn = StringUtils.EMPTY;
        String value1 = StringUtils.EMPTY + row[index + ((priorIndex + 1) * column)];
        boolean actualBasis = "Actuals".equalsIgnoreCase(pvsdto.getComparisonBasis());
        boolean actualCheck = "null".equalsIgnoreCase(StringUtils.EMPTY + row[index - 1]);
        if (baseVariables.isColValue()) {
            visibleColumn = columns[0];
            String baseValue = getFormattedValue(AMOUNT, value1);
            if (pvsdto.hasColumn(visibleColumn)) {
                projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                columnList.remove(visibleColumn);
            }
        }
        if (baseVariables.isColVariance()) {
            visibleColumn = columns[1];
            String priorVal = StringUtils.EMPTY + row[index + ((priorIndex + 1) * column)];
            if (actualBasis) {
                if (!actualCheck) {
                    String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index - 1])) - Double.valueOf(isNull(priorVal)));
                    String baseValue = getFormattedValue(AMOUNT, variance);
                    if (pvsdto.hasColumn(visibleColumn)) {
                        projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                        columnList.remove(visibleColumn);
                    }
                }
            } else {
                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index])) - Double.valueOf(isNull(priorVal)));
                String baseValue = getFormattedValue(AMOUNT, variance);
                if (pvsdto.hasColumn(visibleColumn)) {
                    projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                    columnList.remove(visibleColumn);
                }
            }

        }
        if (baseVariables.isColPercentage()) {
            visibleColumn = columns[NumericConstants.TWO];
            String priorVal = StringUtils.EMPTY + row[index + ((priorIndex + 1) * column)];
            if (actualBasis) {
                if (!actualCheck) {
                    String actValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index - 1])));
                    String baseValue = getPerChange(actValue, priorVal, RATE);
                    if (pvsdto.hasColumn(visibleColumn)) {
                        projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                        columnList.remove(visibleColumn);
                    }
                }
            } else {
                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                    perChange = 0.0;
                }
                perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                String change = String.valueOf(perChange);
                String baseValue = getFormattedValue(RATE, change);
                if (pvsdto.hasColumn(visibleColumn)) {
                    projDTO.addStringProperties(visibleColumn, baseValue + PERCENT);
                    columnList.remove(visibleColumn);
                }
            }
        }
        LOGGER.debug("Ending getPivotCommonCustomization");
    }

    public List<ProjectionVarianceDTO> getCustomizedPivotDetailResults(final List gtsList, final List results, List discountList, List<String> discountName, ProjectionVarianceDTO parentDto, List<Integer> priorProjGtsList, PVSelectionDTO pvsdto, PVSelectionDTO baseVariables) {
        List<String> periodList = new ArrayList<String>(pvsdto.getPeriodList());

        List<String> discountNames = new ArrayList<String>(pvsdto.getDiscountNameList());
        if (!"Total Discount".equalsIgnoreCase(pvsdto.getDiscountLevel())) {
            List list = CommonLogic.getPPADiscountNameList(pvsdto);
            if (list != null) {
                discountNames.addAll(list);
            }
        }
        Map<String, String> discountMap = new HashMap<>();
        for (int j = 0; j < discountNames.size(); j++) {
            discountMap.put(String.valueOf(discountNames.get(j)), String.valueOf(j));
        }
        Map<String, String> periodListMap = new HashMap<String, String>(pvsdto.getPeriodListMap());
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        boolean isGts = false;
        int frequencyDivision = pvsdto.getFrequencyDivision();
        if (results != null && !results.isEmpty() && !gtsList.isEmpty()) {
            List<Object[]> customizedGtsList = new ArrayList<>();
            for (Object resultObj : results) {
                for (Object gtslistObj : gtsList) {
                    Object[] resObj = (Object[]) resultObj;
                    Object[] gtsObj = (Object[]) gtslistObj;
                    if (((int) resObj[0]) == ((int) gtsObj[0]) && ((int) resObj[1]) == (gtsObj[1] != null ? Integer.valueOf(String.valueOf(gtsObj[1])) : 0)) {
                        customizedGtsList.add(gtsObj);
                    }
                }
            }
            for (int i = 0; i < results.size(); i++) {
                final Object[] row = (Object[]) results.get(i);
                final Object[] gtsRow = (Object[]) customizedGtsList.get(i);
                int year = Integer.valueOf(String.valueOf(gtsRow[0]));
                int period = gtsRow[1] != null ? Integer.valueOf(String.valueOf(gtsRow[1])) : 0;
                List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, year, period);
                String pcommonColumn = common.get(0);
                String commonHeader = common.get(1);
                String column = StringUtils.EMPTY;
                String currentValue = StringUtils.EMPTY;
                String actualValue = StringUtils.EMPTY;
                if (gtsList.size() >= results.size() && gtsRow.length >= row.length) {
                    isGts = true;
                }
                if (periodList.contains(pcommonColumn)) {
                    periodList.remove(pcommonColumn);
                    List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
                    columnList.remove(Constant.GROUP);
                    ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                    projDTO.setGroup(commonHeader);
                    String variableValue = StringUtils.EMPTY;
                    //Exfactory Sales
                    if ((baseVariables.isVarExFacSales()) && (isGts)) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWENTY];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_ONE];
                        if (baseVariables.isColValue()) {
                            customizePivot("ExFacValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("ExFacVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("ExFacPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }

                    //Demand Sales
                    if ((baseVariables.isVarDemandSales()) && (isGts)) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_SIX];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_SEVEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("DemandSalesValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("DemandSalesVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("DemandSalesPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Inventory Withdrawal sales
                    if ((baseVariables.isVarInvSales()) && (isGts)) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.THIRTY];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_ONE];
                        if (baseVariables.isColValue()) {
                            customizePivot("InvWithValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("InvWithVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("InvWithPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Percentage of Exfactory Sales
                    if ((baseVariables.isVarPerExFacSales()) && (isGts)) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_TWO];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_THREE];
                        if (baseVariables.isColValue()) {
                            customizePivot("PerExFacValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("PerExFacVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("PerExFacPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Percentage of Demand Sales
                    if ((baseVariables.isVarPerDemandSales()) && (isGts)) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_EIGHT];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_NINE];
                        if (baseVariables.isColValue()) {
                            customizePivot("PerDemandSalesValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("PerDemandSalesVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("PerDemandSalesPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Percentage Inventory Withdrawal Value
                    if ((baseVariables.isVarPerInvSales()) && (isGts)) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_TWO];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_THREE];
                        if (baseVariables.isColValue()) {
                            customizePivot("PerInvWithValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("PerInvWithVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("PerInvWithPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }

                    //Contract Sales
                    if ((baseVariables.isVarContractsales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWO];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THREE];
                        if (baseVariables.isColValue()) {
                            customizePivot("ContractSalesWACValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("ContractSalesWACVar", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("ContractSalesWACVarPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Contract Units
                    if ((baseVariables.isVarContractUnits())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FOUR];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FIVE];
                        if (baseVariables.isColValue()) {
                            customizePivot("ContractUnitsValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT_UNITS);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("ContractUnitsVar", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT_UNITS);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("ContractUnitsPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Discount Amount
                    if ((baseVariables.isVarDisAmount())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.EIGHT];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.NINE];
                        if (baseVariables.isColValue()) {
                            customizePivot("DiscountAmountValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("DiscountAmountVar", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("DiscountAmountPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Discount Percentage
                    if ((baseVariables.isVarDisRate())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.SIX];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.SEVEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("DiscountSalesValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("DiscountSalesVar", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("DiscountSalesPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //RPU
                    if ((baseVariables.isVarRPU())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWELVE];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTEEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("RPUValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("RPUVar", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("RPUPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //NetSales
                    if ((baseVariables.isVarNetSales())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TEN];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.ELEVEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("NetSalesValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("NetSalesVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("NetSalesPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //COGS
                    if ((baseVariables.isVarCOGC())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.FOURTEEN];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTEEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("COGCValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("COGCVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("COGCPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Net Profit
                    if ((baseVariables.isVarNetProfit())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.SIXTEEN];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.SEVENTEEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("NetProfitValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("NetProfitVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, AMOUNT);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("NetProfitPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //Net Sales ExFactory Percentage
                    if ((baseVariables.isNetSalesExFactory())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.EIGHTEEN];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.NINETEEN];
                        if (baseVariables.isColValue()) {
                            customizePivot("NetSalesExFactoryValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("NetSalesExFactoryVariance", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("NetSalesExFactoryPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }
                    //DiscountPerExFactory
                    if ((baseVariables.isDiscountPerExFactory())) {
                        actualValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_FOUR];
                        currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_FIVE];
                        if (baseVariables.isColValue()) {
                            customizePivot("DiscountPerExFactoryValue", Constant.VALUE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColVariance()) {
                            customizePivot("DiscountPerExFactoryVar", Constant.VARIANCE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                        if (baseVariables.isColPercentage()) {
                            customizePivot("DiscountPerExFactoryPer", Constant.CHANGE, currentValue, actualValue, pvsdto, projDTO, columnList, baseVariables, RATE);
                        }
                    }

                    for (int j = 0; j < priorProjGtsList.size(); j++) {
                        boolean actualBasis = "Actuals".equalsIgnoreCase(pvsdto.getComparisonBasis());
                        String actualValueCurrent;
                        //ExFacSales
                        if (baseVariables.isVarExFacSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWENTY];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_ONE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_ONE + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "ExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ExFacVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "ExFacPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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

                        if (baseVariables.isVarDemandSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWENTY_SIX];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_SEVEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_SEVEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "DemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DemandSalesVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "DemandSalesPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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

                        //Inventory 
                        if (baseVariables.isVarInvSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.THIRTY];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_ONE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_ONE + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "InvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "InvWithVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "InvWithPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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

                        //Percentage of Ex Fac
                        if (baseVariables.isVarPerExFacSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWENTY_TWO];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_THREE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_THREE + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "PerExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerExFacVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "PerExFacPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarPerDemandSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWENTY_EIGHT];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_NINE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_NINE + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "PerDemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerDemandSalesVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "PerDemandSalesPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        //Percentage of Inv
                        if (baseVariables.isVarPerInvSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.THIRTY_TWO];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_THREE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THIRTY_THREE + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "PerInvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerInvWithVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "PerInvWithPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        // Contract Sales
                        if (baseVariables.isVarContractsales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWO];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THREE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THREE + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "ContractSalesWACValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ContractSalesWACVar" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "ContractSalesWACVarPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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

                        if (baseVariables.isVarContractUnits()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FOUR];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FIVE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FIVE + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "ContractUnitsValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT_UNITS, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ContractUnitsVar" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT_UNITS);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT_UNITS);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "ContractUnitsPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarDisRate()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.SIX];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.SEVEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.SEVEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "DiscountSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountSalesVar" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE_PER);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE_PER);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "DiscountSalesPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarDisAmount()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.EIGHT];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.NINE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.NINE + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "DiscountAmountValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountAmountVar" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "DiscountAmountPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarRPU()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWELVE];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.THIRTEEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.THIRTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "RPUValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "RPUVar" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "RPUPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarNetSales()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TEN];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.ELEVEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.ELEVEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "NetSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetSalesVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "NetSalesPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarCOGC()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.FOURTEEN];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.FIFTEEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.FIFTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "COGCValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "COGCVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "COGCPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isVarNetProfit()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.SIXTEEN];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.SEVENTEEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.SEVENTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "NetProfitValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetProfitVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, AMOUNT);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, AMOUNT);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "NetProfitPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isNetSalesExFactory()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.EIGHTEEN];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.NINETEEN];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.NINETEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "NetSalesExFactoryValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetSalesExFactoryVariance" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "NetSalesExFactoryPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        if (baseVariables.isDiscountPerExFactory()) {
                            actualValueCurrent = StringUtils.EMPTY + row[NumericConstants.TWENTY_FOUR];
                            currentValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_FIVE];
                            String priorValue = StringUtils.EMPTY + row[NumericConstants.TWENTY_FIVE + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                            boolean actualCheck = "null".equalsIgnoreCase(actualValueCurrent);
                            if (baseVariables.isColValue()) {
                                column = "DiscountPerExFactoryValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, priorValue);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountPerExFactoryVar" + priorProjGtsList.get(j);
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, val + PERCENT);
                                            columnList.remove(column);
                                        }
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorValue, RATE);
                                    if (pvsdto.hasColumn(column)) {
                                        projDTO.addStringProperties(column, val + PERCENT);
                                        columnList.remove(column);
                                    }
                                }
                            }

                            if (baseVariables.isColPercentage()) {
                                column = "DiscountPerExFactoryPer" + priorProjGtsList.get(j);

                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String baseValu = getPerChange(actualValueCurrent, priorValue, RATE);
                                        if (pvsdto.hasColumn(column)) {
                                            projDTO.addStringProperties(column, baseValu + PERCENT);
                                            columnList.remove(column);
                                        }
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
                        Set<String> noOfDiscount = new HashSet<String>();
                        for (Object discountsName : discountList) {
                            final Object[] disc = (Object[]) discountsName;
                            if (disc[NumericConstants.TWO] != null) {
                                noOfDiscount.add(String.valueOf(disc[NumericConstants.TWO]));
                            }
                        }
                        for (int j = 0; j < discountList.size(); j++) {
                            String columnAct = StringUtils.EMPTY;
                            boolean actualBasis = "Actuals".equalsIgnoreCase(pvsdto.getComparisonBasis());
                            Object[] discountRow = (Object[]) discountList.get(j);
                            int dyear = Integer.valueOf(String.valueOf(discountRow[0]));
                            int dperiod = Integer.valueOf(String.valueOf(discountRow[1]));
                            List<String> dcommon = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, dyear, dperiod);
                            String dcommonColumn = dcommon.get(0);
                            if (pcommonColumn.equals(dcommonColumn)) {
                                for (int disc = 0; disc < noOfDiscount.size(); disc++) {
                                    String head = String.valueOf(discountRow[NumericConstants.TWO]).replace(" ", StringUtils.EMPTY) + discountMap.get(discountRow[NumericConstants.TWO]);

                                    if ((baseVariables.isVarDisAmount())) {
                                        actualValue = StringUtils.EMPTY + discountRow[NumericConstants.FOURTEEN];
                                        currentValue = StringUtils.EMPTY + discountRow[NumericConstants.THREE];
                                        boolean actualCheck = "null".equalsIgnoreCase(actualValue);
                                        if ((baseVariables.isColValue())) {
                                            //Actual
                                            columnAct = "DiscountAmountValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                            String baseValueAct = getFormattedValue(RATE, actualValue);
                                            if (!actualCheck) {
                                                if (pvsdto.hasColumn(columnAct)) {
                                                    projDTO.addStringProperties(columnAct, baseValueAct);
                                                    columnList.remove(columnAct);
                                                }
                                            } else if (pvsdto.hasColumn(columnAct)) {
                                                projDTO.addStringProperties(columnAct, actualDASH);
                                                columnList.remove(columnAct);
                                            }
                                            //Current
                                            column = "DiscountAmountValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            baseValueAct = getFormattedValue(RATE, currentValue);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValueAct);
                                                columnList.remove(column);
                                            }
                                        }
                                        if (actualBasis) {
                                            if (!actualCheck) {
                                                if ((baseVariables.isColVariance())) {
                                                    column = "DiscountAmountVar" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //  for CURRENT
                                                    String val = getVariance(actualValue, currentValue, AMOUNT);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val);
                                                        columnList.remove(column);
                                                    }

                                                }
                                                if ((baseVariables.isColPercentage())) {
                                                    column = "DiscountAmountPer" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //for CURRENT
                                                    String val = getPerChange(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }
                                            } else if ((baseVariables.isColValue())) {
                                                column = "DiscountAmountPer" + head + ACTUAL + pvsdto.getCurrentProjId();
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, actualDASH);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                    }

                                    if ((baseVariables.isVarDisRate())) {
                                        actualValue = StringUtils.EMPTY + discountRow[NumericConstants.FIFTEEN];
                                        currentValue = StringUtils.EMPTY + discountRow[NumericConstants.SIX];
                                        boolean actualCheck = "null".equalsIgnoreCase(actualValue);
                                        if ((baseVariables.isColValue())) {
                                            //Actual
                                            columnAct = "DiscountSalesValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                            String baseValueAct = getFormattedValue(RATE, actualValue);
                                            if (!actualCheck) {
                                                if (pvsdto.hasColumn(columnAct)) {
                                                    projDTO.addStringProperties(columnAct, baseValueAct);
                                                    columnList.remove(columnAct);
                                                }
                                            } else if (pvsdto.hasColumn(columnAct)) {
                                                projDTO.addStringProperties(columnAct, actualDASH);
                                                columnList.remove(columnAct);
                                            }
                                            //Current
                                            column = "DiscountSalesValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            baseValueAct = getFormattedValue(RATE, currentValue);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValueAct);
                                                columnList.remove(column);
                                            }
                                        }
                                        if (actualBasis) {
                                            if (!actualCheck) {
                                                if ((baseVariables.isColVariance())) {
                                                    column = "DiscountSalesVar" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //  for CURRENT
                                                    String val = getVariance(actualValue, currentValue, AMOUNT);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val);
                                                        columnList.remove(column);
                                                    }

                                                }
                                                if ((baseVariables.isColPercentage())) {
                                                    column = "DiscountSalesPer" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //for CURRENT
                                                    String val = getPerChange(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }
                                            } else if ((baseVariables.isColValue())) {
                                                column = "DiscountSalesPer" + head + ACTUAL + pvsdto.getCurrentProjId();
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, actualDASH);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                    }

                                    if ((baseVariables.isVarRPU())) {
                                        actualValue = StringUtils.EMPTY + discountRow[NumericConstants.SIXTEEN];
                                        currentValue = StringUtils.EMPTY + discountRow[NumericConstants.NINE];
                                        boolean actualCheck = "null".equalsIgnoreCase(actualValue);
                                        if ((baseVariables.isColValue())) {
                                            //Actual
                                            columnAct = "RPUValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                            String baseValueAct = getFormattedValue(RATE, actualValue);
                                            if (!actualCheck) {
                                                if (pvsdto.hasColumn(columnAct)) {
                                                    projDTO.addStringProperties(columnAct, baseValueAct);
                                                    columnList.remove(columnAct);
                                                }
                                            } else if (pvsdto.hasColumn(columnAct)) {
                                                projDTO.addStringProperties(columnAct, actualDASH);
                                                columnList.remove(columnAct);
                                            }
                                            //Current
                                            column = "RPUValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            baseValueAct = getFormattedValue(RATE, currentValue);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValueAct);
                                                columnList.remove(column);
                                            }
                                        }
                                        if (actualBasis) {
                                            if (!actualCheck) {
                                                if ((baseVariables.isColVariance())) {
                                                    column = "RPUVar" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //  for CURRENT
                                                    String val = getVariance(actualValue, currentValue, AMOUNT);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val);
                                                        columnList.remove(column);
                                                    }

                                                }
                                                if ((baseVariables.isColPercentage())) {
                                                    column = "RPUPer" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //for CURRENT
                                                    String val = getPerChange(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }
                                            } else if ((baseVariables.isColValue())) {
                                                column = "RPUPer" + head + ACTUAL + pvsdto.getCurrentProjId();
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, actualDASH);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                    }

                                    //DiscountPerExFactory
                                    if ((baseVariables.isDiscountPerExFactory())) {
                                        actualValue = StringUtils.EMPTY + discountRow[NumericConstants.SEVENTEEN];
                                        currentValue = StringUtils.EMPTY + discountRow[NumericConstants.EIGHTEEN];
                                        boolean actualCheck = "null".equalsIgnoreCase(actualValue);
                                        if ((baseVariables.isColValue())) {
                                            //Actual
                                            columnAct = "DiscountPerExFactoryValue" + head + ACTUAL + pvsdto.getCurrentProjId();
                                            String baseValueAct = getFormattedValue(RATE, actualValue);
                                            if (!actualCheck) {
                                                if (pvsdto.hasColumn(columnAct)) {
                                                    projDTO.addStringProperties(columnAct, baseValueAct);
                                                    columnList.remove(columnAct);
                                                }
                                            } else if (pvsdto.hasColumn(columnAct)) {
                                                projDTO.addStringProperties(columnAct, actualDASH);
                                                columnList.remove(columnAct);
                                            }
                                            //Current
                                            column = "DiscountPerExFactoryValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            baseValueAct = getFormattedValue(RATE, currentValue);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValueAct);
                                                columnList.remove(column);
                                            }
                                        }
                                        if (actualBasis) {
                                            if (!actualCheck) {
                                                if ((baseVariables.isColVariance())) {
                                                    column = "DiscountPerExFactoryVar" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //  for CURRENT
                                                    String val = getVariance(actualValue, currentValue, AMOUNT);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val);
                                                        columnList.remove(column);
                                                    }

                                                }
                                                if ((baseVariables.isColPercentage())) {
                                                    column = "DiscountPerExFactoryPer" + head + CURRENT + pvsdto.getCurrentProjId();
                                                    //for CURRENT
                                                    String val = getPerChange(actualValue, currentValue, RATE);
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, val + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }
                                            } else if ((baseVariables.isColValue())) {
                                                column = "DiscountPerExFactoryPer" + head + ACTUAL + pvsdto.getCurrentProjId();
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, actualDASH);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                    }

                                    for (int k = 0; k < priorProjGtsList.size(); k++) {

                                        //Discount Percentage
                                        if (baseVariables.isVarDisRate()) {
                                            String priorVal = StringUtils.EMPTY;
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.SIX + ((k + 1) * NumericConstants.EIGHTEEN)];
                                            if (baseVariables.isColValue()) {
                                                column = "DiscountSalesValue" + head + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(RATE, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (baseVariables.isColVariance()) {
                                                column = "DiscountSalesVar" + head + priorProjGtsList.get(k);

                                                if (actualBasis) {
                                                    boolean actuals = "null".equalsIgnoreCase(StringUtils.EMPTY + discountRow[NumericConstants.SEVENTEEN]);
                                                    if (!actuals) {
                                                        priorVal = StringUtils.EMPTY + discountRow[NumericConstants.SEVENTEEN + ((k + 1) * NumericConstants.EIGHTEEN)];
                                                        String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                        if (pvsdto.hasColumn(column)) {
                                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                                            columnList.remove(column);
                                                        }
                                                    }
                                                } else {
                                                    priorVal = StringUtils.EMPTY + discountRow[NumericConstants.SEVEN + ((k + 1) * NumericConstants.EIGHTEEN)];
                                                    String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, baseValue + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }

                                            }
                                            if (baseVariables.isColPercentage()) {
                                                column = "DiscountSalesPer" + head + priorProjGtsList.get(k);
                                                if (actualBasis) {
                                                    boolean actuals = "null".equalsIgnoreCase(StringUtils.EMPTY + discountRow[NumericConstants.EIGHTEEN]);
                                                    if (!actuals) {
                                                        priorVal = StringUtils.EMPTY + discountRow[NumericConstants.EIGHTEEN + ((k + 1) * NumericConstants.EIGHTEEN)];
                                                        String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                        if (pvsdto.hasColumn(column)) {
                                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                                            columnList.remove(column);
                                                        }
                                                    }
                                                } else {
                                                    priorVal = StringUtils.EMPTY + discountRow[NumericConstants.EIGHT + ((k + 1) * NumericConstants.EIGHTEEN)];
                                                    String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, baseValue + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }

                                            }
                                        }

                                        //Discount Dollor
                                        if (baseVariables.isVarDisAmount()) {
                                            String priorVal = StringUtils.EMPTY;
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.THREE + ((k + 1) * NumericConstants.EIGHTEEN)];
                                            if (baseVariables.isColValue()) {
                                                column = "DiscountAmountValue" + head + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (baseVariables.isColVariance()) {
                                                column = "DiscountAmountVar" + head + priorProjGtsList.get(k);
                                                if (actualBasis) {
                                                    boolean actuals = "null".equalsIgnoreCase(StringUtils.EMPTY + discountRow[NumericConstants.FIFTEEN]);
                                                    if (!actuals) {
                                                        priorVal = StringUtils.EMPTY + discountRow[NumericConstants.FIFTEEN + ((k + 1) * NumericConstants.EIGHTEEN)];
                                                        String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                        if (pvsdto.hasColumn(column)) {
                                                            projDTO.addStringProperties(column, baseValue);
                                                            columnList.remove(column);
                                                        }
                                                    }
                                                } else {
                                                    priorVal = StringUtils.EMPTY + discountRow[NumericConstants.FOUR + ((k + 1) * NumericConstants.EIGHTEEN)];
                                                    String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, baseValue);
                                                        columnList.remove(column);
                                                    }
                                                }

                                            }
                                            if (baseVariables.isColPercentage()) {
                                                column = "DiscountAmountPer" + head + priorProjGtsList.get(k);
                                                if (actualBasis) {
                                                    boolean actuals = "null".equalsIgnoreCase(StringUtils.EMPTY + discountRow[NumericConstants.SIXTEEN]);
                                                    if (!actuals) {
                                                        priorVal = StringUtils.EMPTY + discountRow[NumericConstants.SIXTEEN + ((k + 1) * NumericConstants.EIGHTEEN)];
                                                        String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                        if (pvsdto.hasColumn(column)) {
                                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                                            columnList.remove(column);
                                                        }
                                                    }
                                                } else {
                                                    priorVal = StringUtils.EMPTY + discountRow[NumericConstants.FIVE + ((k + 1) * NumericConstants.EIGHTEEN)];
                                                    String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, baseValue + PERCENT);
                                                        columnList.remove(column);
                                                    }

                                                }
                                            }
                                        }
                                        //RPU
                                        if (baseVariables.isVarRPU()) {
                                            String priorVal = StringUtils.EMPTY;
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.NINE + ((k + 1) * NumericConstants.EIGHTEEN)];
                                            if (baseVariables.isColValue()) {
                                                column = "RPUValue" + head + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (baseVariables.isColVariance()) {
                                                column = "RPUVar" + head + priorProjGtsList.get(k);
                                                if (actualBasis) {
                                                    boolean actuals = "null".equalsIgnoreCase(StringUtils.EMPTY + discountRow[NumericConstants.NINETEEN]);
                                                    if (!actuals) {
                                                        priorVal = StringUtils.EMPTY + discountRow[NumericConstants.NINETEEN + ((k + 1) * NumericConstants.EIGHTEEN)];
                                                        String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                        if (pvsdto.hasColumn(column)) {
                                                            projDTO.addStringProperties(column, baseValue);
                                                            columnList.remove(column);
                                                        }
                                                    }
                                                } else {
                                                    priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TEN + ((k + 1) * NumericConstants.EIGHTEEN)];
                                                    String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, baseValue);
                                                        columnList.remove(column);
                                                    }

                                                }

                                            }
                                            if (baseVariables.isColPercentage()) {
                                                column = "RPUPer" + head + priorProjGtsList.get(k);
                                                if (actualBasis) {
                                                    boolean actuals = "null".equalsIgnoreCase(StringUtils.EMPTY + discountRow[NumericConstants.TWENTY]);
                                                    if (!actuals) {
                                                        priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY + ((k + 1) * NumericConstants.EIGHTEEN)];
                                                        String baseValue = getFormattedValue(RATE_PER_THREE, isNull(priorVal));
                                                        if (pvsdto.hasColumn(column)) {
                                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                                            columnList.remove(column);
                                                        }
                                                    }
                                                } else {
                                                    priorVal = StringUtils.EMPTY + discountRow[NumericConstants.ELEVEN + ((k + 1) * NumericConstants.EIGHTEEN)];
                                                    String baseValue = getFormattedValue(RATE_PER_THREE, isNull(priorVal));
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, baseValue + PERCENT);
                                                        columnList.remove(column);
                                                    }
                                                }

                                            }
                                        }

                                        //Discount of Exfactory Percentage
                                        if (baseVariables.isDiscountPerExFactory()) {
                                            String priorVal = StringUtils.EMPTY;
                                            String value1 = StringUtils.EMPTY + discountRow[NumericConstants.EIGHTEEN + ((k + 1) * NumericConstants.TWENTY_FOUR)];
                                            if (baseVariables.isColValue()) {
                                                column = "DiscountPerExFactoryValue" + head + priorProjGtsList.get(k);
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (baseVariables.isColVariance()) {
                                                column = "DiscountPerExFactoryVar" + head + priorProjGtsList.get(k);
                                                if (actualBasis) {
                                                    boolean actuals = "null".equalsIgnoreCase(StringUtils.EMPTY + discountRow[NumericConstants.SEVENTEEN]);
                                                    if (!actuals) {
                                                        priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_FIVE + ((k + 1) * NumericConstants.TWENTY_FOUR)];
                                                        String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                        if (pvsdto.hasColumn(column)) {
                                                            projDTO.addStringProperties(column, baseValue);
                                                            columnList.remove(column);
                                                        }
                                                    }
                                                } else {
                                                    priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TWELVE + ((k + 1) * NumericConstants.TWENTY_FOUR)];
                                                    String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                    if (pvsdto.hasColumn(column)) {
                                                        projDTO.addStringProperties(column, baseValue);
                                                        columnList.remove(column);
                                                    }

                                                }

                                            }
                                            if (baseVariables.isColPercentage()) {
                                                column = "DiscountPerExFactoryPer" + head + priorProjGtsList.get(k);
                                                if (actualBasis) {
                                                    boolean actuals = "null".equalsIgnoreCase(StringUtils.EMPTY + discountRow[NumericConstants.TWENTY]);
                                                    if (!actuals) {
                                                        priorVal = StringUtils.EMPTY + discountRow[NumericConstants.TWENTY_SIX + ((k + 1) * NumericConstants.TWENTY_FOUR)];
                                                        String baseValue = getFormattedValue(RATE_PER_THREE, isNull(priorVal));
                                                        if (pvsdto.hasColumn(column)) {
                                                            projDTO.addStringProperties(column, baseValue + PERCENT);
                                                            columnList.remove(column);
                                                        }
                                                    }
                                                } else {
                                                    priorVal = StringUtils.EMPTY + discountRow[NumericConstants.THIRTEEN + ((k + 1) * NumericConstants.TWENTY_FOUR)];
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
                    }
                    projDTOList.add(projDTO);
                }
            }
        }
        List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
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
        String priorQuery = priorlistQuery(pvsdto);
        String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(pvsdto);
        String query = priorQuery + ccpQuery + CommonLogic.getTempCCPQueryForCOGS(pvsdto) + "\n" + queryUtils.getProjectionResultsMainPeriodQuery(pvsdto);
        query = query.replace("@CCP", "#SELECTED_HIERARCHY_NO");
        String replaceQuery = QueryUtil.replaceTableNames(query, pvsdto.getSessionDTO().getCurrentTableNames());
        List<Object> currentPivotDetails = (List<Object>) CommonLogic.executeSelectQuery(replaceQuery, null, null);
        pvsdto.setProjectionId(pvsdto.getCurrentProjId());
        return currentPivotDetails;
    }

    /**
     * get Customized period variance
     *
     * @return List
     */
    public List<ProjectionVarianceDTO> getCustPeriodVariance(final List<Object> gtsList, final List<Object> dataList, final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto, final PVSelectionDTO baseVariables) {
        try {
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
            boolean isDetail = false;
            if (pvsdto.getLevel().equals(DETAIL)) {
                isDetail = true;
            } else {
                ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
                dto.setGroup(Constant.PROJECTION_TOTAL);
                projectionVarianceDTO.add(dto);
            }
            // GTS and Sales for POB starts
            if (isDetail) {
                //Ex fac for detail start
                if (baseVariables.isColValue()) {
//                     getExFactorySales(gtsList, pvsdto);
//                    exFacValue = calculateCommon(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VALUE, NumericConstants.THREE, gtsList, pvsdto, AMOUNT, actualBasis);
                    exFacValue = calculateDetailTotal(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VALUE, NumericConstants.THREE, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.TWENTY_ONE, false);
                }
                if (baseVariables.isColVariance()) {
                    exFacVar = calculateDetailTotal(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VARIANCE, NumericConstants.THREE, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.TWENTY_ONE, false);
                }
                if (baseVariables.isColPercentage()) {
                    exFacPer = calculateDetailTotal(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.CHANGE, NumericConstants.THREE, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_ONE, false);
                }
                //Ex fac for detail end

                //Demand sale for detail - start
                if (baseVariables.isColValue()) {
                    demandValue = calculateDetailTotal(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VALUE, NumericConstants.FIVE, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.TWENTY_SEVEN, false);
//                    demandValue = calculateCommon(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VALUE, NumericConstants.FIVE, gtsList, pvsdto, AMOUNT, actualBasis);
                }
                if (baseVariables.isColVariance()) {
                    demandVar = calculateDetailTotal(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VARIANCE, NumericConstants.FIVE, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.TWENTY_SEVEN, false);
                }
                if (baseVariables.isColPercentage()) {
                    demandPer = calculateDetailTotal(Constant.PVVariables.DEMAND_SALES.toString(), Constant.CHANGE, NumericConstants.FIVE, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_SEVEN, false);
                }
                //Demand sale for detail - start

                //Inv with drawal sale for detail - start
                if (baseVariables.isColValue()) {
                    invWithValue = calculateDetailTotal(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VALUE, NumericConstants.SEVEN, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.THIRTY_ONE, false);
//                    invWithValue = calculateCommon(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VALUE, NumericConstants.SEVEN, gtsList, pvsdto, AMOUNT, actualBasis);
                }
                if (baseVariables.isColVariance()) {
                    invWithVar = calculateDetailTotal(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VARIANCE, NumericConstants.SEVEN, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.THIRTY_ONE, false);
                }
                if (baseVariables.isColPercentage()) {
                    invWithPer = calculateDetailTotal(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.CHANGE, NumericConstants.SEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.THIRTY_ONE, false);
                }
                //Inv with drawal sale for detail - start

                //Sales for POB
                if (baseVariables.isColValue()) {
                    salesValue = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VALUE, NumericConstants.FIFTEEN, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.THREE, false);
                }
                if (baseVariables.isColVariance()) {
                    salesVar = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VARIANCE, NumericConstants.FIFTEEN, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.THREE, false);
                }
                if (baseVariables.isColPercentage()) {
                    salesPer = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.CHANGE, NumericConstants.FIFTEEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.THREE, false);
                }
            }
            // ExFac Sales
            if (baseVariables.isVarExFacSales()) {
                if (baseVariables.isColValue()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateCommon(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VALUE, NumericConstants.THREE, gtsList, pvsdto, AMOUNT, actualBasis);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        projectionVarianceDTO.add(exFacValue);
                    }
                }
                if (baseVariables.isColVariance()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateCommon(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.VARIANCE, NumericConstants.THREE, gtsList, pvsdto, AMOUNT, actualBasis);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        projectionVarianceDTO.add(exFacVar);
                    }
                }
                if (baseVariables.isColPercentage()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateCommon(Constant.PVVariables.EX_FACTORY_SALES.toString(), Constant.CHANGE, NumericConstants.THREE, gtsList, pvsdto, RATE, actualBasis);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        projectionVarianceDTO.add(exFacPer);
                    }
                }
            }
            // Demand Sales
            if (baseVariables.isVarDemandSales()) {
                if (baseVariables.isColValue()) {
                    if (!isDetail) {
//                        getDemandSales(gtsList, pvsdto);
                        ProjectionVarianceDTO salesData = calculateCommon(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VALUE, NumericConstants.FIVE, gtsList, pvsdto, AMOUNT, actualBasis);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        projectionVarianceDTO.add(demandValue);
                    }
                }
                if (baseVariables.isColVariance()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateCommon(Constant.PVVariables.DEMAND_SALES.toString(), Constant.VARIANCE, NumericConstants.FIVE, gtsList, pvsdto, AMOUNT, actualBasis);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        projectionVarianceDTO.add(demandVar);
                    }
                }
                if (baseVariables.isColPercentage()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateCommon(Constant.PVVariables.DEMAND_SALES.toString(), Constant.CHANGE, NumericConstants.FIVE, gtsList, pvsdto, RATE, actualBasis);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        projectionVarianceDTO.add(demandPer);
                    }
                }
            }
            // Inventory Sales Withdrawal
            if (baseVariables.isVarInvSales()) {
                if (baseVariables.isColValue()) {
                    if (!isDetail) {
//                        getInvWithdrawalSales(gtsList, pvsdto);
                        ProjectionVarianceDTO salesData = calculateCommon(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VALUE, NumericConstants.SEVEN, gtsList, pvsdto, AMOUNT, actualBasis);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        projectionVarianceDTO.add(invWithValue);
                    }
                }
                if (baseVariables.isColVariance()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateCommon(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.VARIANCE, NumericConstants.SEVEN, gtsList, pvsdto, AMOUNT, actualBasis);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        projectionVarianceDTO.add(invWithVar);
                    }
                }
                if (baseVariables.isColPercentage()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateCommon(Constant.PVVariables.INVENTORY_SALES.toString(), Constant.CHANGE, NumericConstants.SEVEN, gtsList, pvsdto, RATE, actualBasis);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        projectionVarianceDTO.add(invWithPer);
                    }
                }
            }
            //% Of Ex Factory
            if (baseVariables.isVarPerExFacSales()) {
                if (baseVariables.isColValue()) {
                    if (!isDetail) {
                        pvsdto.setVarIndicator(VALUE.getConstant());
                        ProjectionVarianceDTO exfacsalesData = getCommonPERCustomization(Constant.PVVariables.PER_EX_FACTORY.toString(), gtsList, exFacValue, salesValue, pvsdto, RATE, actualBasis, NumericConstants.NINE);
                        projectionVarianceDTO.add(exfacsalesData);
                    } else {
                        pvsdto.setVarIndicator(VALUE.getConstant());
                        ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.VALUE, NumericConstants.NINE, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_THREE, false);
                        projectionVarianceDTO.add(pob);
                    }

                }
                if (baseVariables.isColVariance()) {
                    if (!isDetail) {
                        pvsdto.setVarIndicator(VARIANCE.getConstant());
                        ProjectionVarianceDTO exfacsalesData = getCommonPERCustomization(Constant.PVVariables.PER_EX_FACTORY.toString(), gtsList, exFacVar, salesVar, pvsdto, RATE, actualBasis, NumericConstants.NINE);
                        projectionVarianceDTO.add(exfacsalesData);
                    } else {
                        pvsdto.setVarIndicator(VARIANCE.getConstant());
                        ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.VARIANCE, NumericConstants.NINE, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_THREE, false);
                        projectionVarianceDTO.add(pob);
                    }

                }
                if (baseVariables.isColPercentage()) {
                    if (!isDetail) {
                        pvsdto.setVarIndicator(CHANGE.getConstant());
                        ProjectionVarianceDTO exfacsalesData = getCommonPERCustomization(Constant.PVVariables.PER_EX_FACTORY.toString(), gtsList, exFacPer, salesPer, pvsdto, RATE, actualBasis, NumericConstants.NINE);
                        projectionVarianceDTO.add(exfacsalesData);
                    } else {
                        ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_EX_FACTORY.toString(), Constant.CHANGE, NumericConstants.NINE, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_THREE, false);
                        projectionVarianceDTO.add(pob);
                    }
                }
            }
            // % Of Demand Sales
            if (baseVariables.isVarPerDemandSales()) {
                if (baseVariables.isColValue()) {
                    if (!isDetail) {
                        pvsdto.setVarIndicator(VALUE.getConstant());
                        ProjectionVarianceDTO exfacsalesData = getCommonPERCustomization(Constant.PVVariables.PER_DEMAND.toString(), gtsList, exFacValue, salesValue, pvsdto, RATE, actualBasis, NumericConstants.ELEVEN);
                        projectionVarianceDTO.add(exfacsalesData);
                    } else {
                        ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_DEMAND.toString(), Constant.VALUE, NumericConstants.ELEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_NINE, false);
                        projectionVarianceDTO.add(pob);
                    }

                }
                if (baseVariables.isColVariance()) {
                    if (!isDetail) {
                        pvsdto.setVarIndicator(VARIANCE.getConstant());
                        ProjectionVarianceDTO exfacsalesData = getCommonPERCustomization(Constant.PVVariables.PER_DEMAND.toString(), gtsList, exFacValue, salesValue, pvsdto, RATE, actualBasis, NumericConstants.ELEVEN);
                        projectionVarianceDTO.add(exfacsalesData);
                    } else {
                        ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_DEMAND.toString(), Constant.VARIANCE, NumericConstants.ELEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_NINE, false);
                        projectionVarianceDTO.add(pob);
                    }

                }
                if (baseVariables.isColPercentage()) {
                    if (!isDetail) {
                        pvsdto.setVarIndicator(CHANGE.getConstant());
                        ProjectionVarianceDTO exfacsalesData = getCommonPERCustomization(Constant.PVVariables.PER_DEMAND.toString(), gtsList, exFacValue, salesValue, pvsdto, RATE, actualBasis, NumericConstants.ELEVEN);
                        projectionVarianceDTO.add(exfacsalesData);
                    } else {
                        ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_DEMAND.toString(), Constant.CHANGE, NumericConstants.ELEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_NINE, false);
                        projectionVarianceDTO.add(pob);
                    }
                }

            }
            // % Of Inv Sales
            if (baseVariables.isVarPerInvSales()) {
                if (baseVariables.isColValue()) {
                    if (!isDetail) {
                        pvsdto.setVarIndicator(VALUE.getConstant());
                        ProjectionVarianceDTO exfacsalesData = getCommonPERCustomization(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), gtsList, exFacValue, salesValue, pvsdto, RATE, actualBasis, NumericConstants.THIRTEEN);
                        projectionVarianceDTO.add(exfacsalesData);
                    } else {
                        pvsdto.setVarIndicator(VALUE.getConstant());
                        ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.VALUE, NumericConstants.THIRTEEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.THIRTY_THREE, false);
                        projectionVarianceDTO.add(pob);
                    }

                }
                if (baseVariables.isColVariance()) {
                    if (!isDetail) {
                        pvsdto.setVarIndicator(VARIANCE.getConstant());
                        ProjectionVarianceDTO exfacsalesData = getCommonPERCustomization(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), gtsList, exFacVar, salesVar, pvsdto, RATE, actualBasis, NumericConstants.THIRTEEN);
                        projectionVarianceDTO.add(exfacsalesData);
                    } else {
                        pvsdto.setVarIndicator(VARIANCE.getConstant());
                        ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.VARIANCE, NumericConstants.THIRTEEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.THIRTY_THREE, false);
                        projectionVarianceDTO.add(pob);
                    }

                }
                if (baseVariables.isColPercentage()) {
                    if (!isDetail) {
                        pvsdto.setVarIndicator(CHANGE.getConstant());
                        ProjectionVarianceDTO exfacsalesData = getCommonPERCustomization(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), gtsList, exFacPer, salesPer, pvsdto, RATE, actualBasis, NumericConstants.THIRTEEN);
                        projectionVarianceDTO.add(exfacsalesData);
                    } else {
                        ProjectionVarianceDTO pob = calculateDetailTotal(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString(), Constant.CHANGE, NumericConstants.THIRTEEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.THIRTY_THREE, false);
                        projectionVarianceDTO.add(pob);
                    }
                }
            }
            //Contract Sales
            if (baseVariables.isVarContractsales()) {
                if (baseVariables.isColValue()) {
                    if (!isDetail) {
//                        ProjectionVarianceDTO sales = getContractSales(gtsList, dataList, pvsdto);
                        ProjectionVarianceDTO sales = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VALUE, NumericConstants.FIFTEEN, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.THREE, false);
                        projectionVarianceDTO.add(sales);
                    } else {
                        projectionVarianceDTO.add(salesValue);
                    }
                }
                if (baseVariables.isColVariance()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO sales = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.VARIANCE, NumericConstants.FIFTEEN, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.THREE, false);
                        projectionVarianceDTO.add(sales);
                    } else {
                        projectionVarianceDTO.add(salesVar);
                    }
                }
                if (baseVariables.isColPercentage()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO sales = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_SALES.toString(), Constant.CHANGE, NumericConstants.FIFTEEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.THREE, false);
                        projectionVarianceDTO.add(sales);
                    } else {
                        projectionVarianceDTO.add(salesPer);
                    }
                }
            }
            //Contract Units
            if (baseVariables.isVarContractUnits()) {
                if (baseVariables.isColValue()) {
//                    ProjectionVarianceDTO units = getContractUnits(gtsList, dataList, pvsdto);
                    ProjectionVarianceDTO units = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.VALUE, NumericConstants.SEVENTEEN, gtsList, dataList, pvsdto, AMOUNT_UNITS, actualBasis, NumericConstants.FIVE, false);
                    projectionVarianceDTO.add(units);
                }
                if (baseVariables.isColVariance()) {
                    ProjectionVarianceDTO units = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.VARIANCE, NumericConstants.SEVENTEEN, gtsList, dataList, pvsdto, AMOUNT_UNITS, actualBasis, NumericConstants.FIVE, false);
                    projectionVarianceDTO.add(units);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO units = calculateDetailTotal(Constant.PVVariables.VAR_CONTRACT_UNITS.toString(), Constant.CHANGE, NumericConstants.SEVENTEEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.FIVE, false);
                    projectionVarianceDTO.add(units);
                }
            }
            //Discount $ 
            if (baseVariables.isVarDisAmount()) {
                if (baseVariables.isColValue()) {
//                    ProjectionVarianceDTO discountDol = getDiscountDol(gtsList, dataList, pvsdto);
                    ProjectionVarianceDTO discountDol = calculateDetailTotal(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VALUE, NumericConstants.NINETEEN, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.NINE, true);
                    discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountDol);
                }
                if (baseVariables.isColVariance()) {
                    ProjectionVarianceDTO discountDol = calculateDetailTotal(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.VARIANCE, NumericConstants.NINETEEN, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.NINE, true);
                    discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountDol);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO discountDol = calculateDetailTotal(Constant.PVVariables.VAR_DIS_AMOUNT.toString(), Constant.CHANGE, NumericConstants.NINETEEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.NINE, true);
                    discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountDol);
                }
            }
            //Discount % 
            if (baseVariables.isVarDisRate()) {
                if (baseVariables.isColValue()) {
//                    ProjectionVarianceDTO discountPer = getDiscountPer(gtsList, dataList, pvsdto);
                    ProjectionVarianceDTO discountPer = calculateDetailTotal(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VALUE, NumericConstants.TWENTY_ONE, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.SEVEN, true);
                    discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountPer);
                }
                if (baseVariables.isColVariance()) {
                    ProjectionVarianceDTO discountPer = calculateDetailTotal(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.VARIANCE, NumericConstants.TWENTY_ONE, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.SEVEN, true);
                    discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountPer);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO discountPer = calculateDetailTotal(Constant.PVVariables.VAR_DIS_RATE.toString(), Constant.CHANGE, NumericConstants.TWENTY_ONE, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.SEVEN, true);
                    discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountPer);
                }
            }
            // RPU
            if (baseVariables.isVarRPU()) {
                if (baseVariables.isColValue()) {
//                    ProjectionVarianceDTO netSalesValue = getRPU(gtsList, dataList, pvsdto);
                    ProjectionVarianceDTO netSalesValue = calculateDetailTotal(Constant.PVVariables.VAR_RPU.toString(), Constant.VALUE, NumericConstants.THIRTY_ONE, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.THIRTEEN, true);
                    netSalesValue = setDataObjects(netSalesValue, parentDto, pvsdto);
                    projectionVarianceDTO.add(netSalesValue);
                }
                if (baseVariables.isColVariance()) {
                    ProjectionVarianceDTO netSalesVar = calculateDetailTotal(Constant.PVVariables.VAR_RPU.toString(), Constant.VARIANCE, NumericConstants.THIRTY_ONE, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.THIRTEEN, true);
                    netSalesVar = setDataObjects(netSalesVar, parentDto, pvsdto);
                    projectionVarianceDTO.add(netSalesVar);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.VAR_RPU.toString(), Constant.CHANGE, NumericConstants.THIRTY_ONE, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.THIRTEEN, true);
                    netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(netSalesPer);
                }
            }
//            DiscountPerExFactory

            if (baseVariables.isDiscountPerExFactory()) {
                if (baseVariables.isColValue()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateDetailTotal(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VALUE, NumericConstants.FORTY_SEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_FIVE, true);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VALUE, NumericConstants.FORTY_SEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_FIVE, true);
                        netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                        projectionVarianceDTO.add(netSalesPer);
                    }

//                        getDemandSales(gtsList, pvsdto);
                }
                if (baseVariables.isColVariance()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateDetailTotal(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VARIANCE, NumericConstants.FORTY_SEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_FIVE, true);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.VARIANCE, NumericConstants.FORTY_SEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_FIVE, true);
                        netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                        projectionVarianceDTO.add(netSalesPer);
                    }
                }
                if (baseVariables.isColPercentage()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateDetailTotal(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.CHANGE, NumericConstants.FORTY_SEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_FIVE, true);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(), Constant.CHANGE, NumericConstants.FORTY_SEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.TWENTY_FIVE, true);
                        netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                        projectionVarianceDTO.add(netSalesPer);
                    }
                }
            }

            //NetSales 
            if (baseVariables.isVarNetSales()) {
                if (baseVariables.isColValue()) {
//                    ProjectionVarianceDTO netSalesValue = getNetSales(gtsList, dataList, pvsdto);
                    ProjectionVarianceDTO netSalesValue = calculateDetailTotal(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VALUE, NumericConstants.THIRTY_THREE, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.ELEVEN, false);
                    projectionVarianceDTO.add(netSalesValue);
                }
                if (baseVariables.isColVariance()) {
                    ProjectionVarianceDTO netSalesVar = calculateDetailTotal(Constant.PVVariables.VAR_NETSALES.toString(), Constant.VARIANCE, NumericConstants.THIRTY_THREE, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.ELEVEN, false);
                    projectionVarianceDTO.add(netSalesVar);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.VAR_NETSALES.toString(), Constant.CHANGE, NumericConstants.THIRTY_THREE, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.ELEVEN, false);
                    projectionVarianceDTO.add(netSalesPer);
                }
            }

            //NetSalesExFactory
            if (baseVariables.isNetSalesExFactory()) {
                if (baseVariables.isColValue()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateCommon(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.VALUE, NumericConstants.FORTY_FIVE, gtsList, pvsdto, RATE, actualBasis);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.VALUE, NumericConstants.FORTY_SEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.NINETEEN, false);
                        netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                        projectionVarianceDTO.add(netSalesPer);
                    }

//                        getDemandSales(gtsList, pvsdto);
                }
                if (baseVariables.isColVariance()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateCommon(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.VARIANCE, NumericConstants.FORTY_FIVE, gtsList, pvsdto, RATE, actualBasis);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.VARIANCE, NumericConstants.FORTY_SEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.NINETEEN, false);
                        netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                        projectionVarianceDTO.add(netSalesPer);
                    }
                }
                if (baseVariables.isColPercentage()) {
                    if (!isDetail) {
                        ProjectionVarianceDTO salesData = calculateCommon(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.CHANGE, NumericConstants.FORTY_FIVE, gtsList, pvsdto, RATE, actualBasis);
                        projectionVarianceDTO.add(salesData);
                    } else {
                        ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString(), Constant.CHANGE, NumericConstants.FORTY_SEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.NINETEEN, false);
                        netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                        projectionVarianceDTO.add(netSalesPer);
                    }
                }
            }

//            COGS
            if (baseVariables.isVarCOGC()) {
                if (baseVariables.isColValue()) {
//                    ProjectionVarianceDTO netSalesValue = getCOGC(gtsList, dataList, pvsdto);
                    ProjectionVarianceDTO netSalesValue = calculateDetailTotal(Constant.PVVariables.VAR_COGS.toString(), Constant.VALUE, NumericConstants.THIRTY_FIVE, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.FIFTEEN, false);
                    projectionVarianceDTO.add(netSalesValue);
                }
                if (baseVariables.isColVariance()) {
                    ProjectionVarianceDTO netSalesVar = calculateDetailTotal(Constant.PVVariables.VAR_COGS.toString(), Constant.VARIANCE, NumericConstants.THIRTY_FIVE, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.FIFTEEN, false);
                    projectionVarianceDTO.add(netSalesVar);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.VAR_COGS.toString(), Constant.CHANGE, NumericConstants.THIRTY_FIVE, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.FIFTEEN, false);
                    projectionVarianceDTO.add(netSalesPer);
                }
            }
            //Net Profit
            if (baseVariables.isVarNetProfit()) {
                if (baseVariables.isColValue()) {
//                    ProjectionVarianceDTO netSalesValue = getNetProfit(gtsList, dataList, pvsdto);
                    ProjectionVarianceDTO netSalesValue = calculateDetailTotal(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VALUE, NumericConstants.THIRTY_SEVEN, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.SEVENTEEN, false);
                    projectionVarianceDTO.add(netSalesValue);
                }
                if (baseVariables.isColVariance()) {
                    ProjectionVarianceDTO netSalesVar = calculateDetailTotal(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.VARIANCE, NumericConstants.THIRTY_SEVEN, gtsList, dataList, pvsdto, AMOUNT, actualBasis, NumericConstants.SEVENTEEN, false);
                    projectionVarianceDTO.add(netSalesVar);
                }
                if (baseVariables.isColPercentage()) {
                    ProjectionVarianceDTO netSalesPer = calculateDetailTotal(Constant.PVVariables.VAR_NET_PROFITE.toString(), Constant.CHANGE, NumericConstants.THIRTY_SEVEN, gtsList, dataList, pvsdto, RATE, actualBasis, NumericConstants.SEVENTEEN, false);
                    projectionVarianceDTO.add(netSalesPer);
                }
            }

            return projectionVarianceDTO;
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    /**
     * Ex Factory Sales
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getExFactorySales(List<Object> dataList, PVSelectionDTO pvsdto) {
        LOGGER.info("Inside getExFactorySales");
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.EX_FACTORY_SALES.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.EX_FACTORY_SALES.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.EX_FACTORY_SALES.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
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
//                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
//                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
//                        String baseValue = getFormattedValue(AMOUNT, value);
//                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);
//
//                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
//                        for ACTUAL
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWO])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);
//                        for CURRENT
                        value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THREE])));
                        baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THREE + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THREE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THREE])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THREE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THREE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
//                **************************************************
            } else {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
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
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        //Actual
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWO])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);
                        //Current
                        value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THREE])));
                        baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THREE + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THREE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THREE])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THREE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THREE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        }
        return pvDTO;
    }

    /**
     * Ex Factory Sales
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getDemandSales(List<Object> dataList, PVSelectionDTO pvsdto) {
        LOGGER.info("Inside getDemandSales");
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.DEMAND_SALES.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.DEMAND_SALES.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.DEMAND_SALES.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
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

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FOUR])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            } else {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
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
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FOUR])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        }
        return pvDTO;
    }

    /**
     * Ex Factory Sales
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getInvWithdrawalSales(List<Object> dataList, PVSelectionDTO pvsdto) {
        LOGGER.info("Inside getGTS");
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.INVENTORY_SALES.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.INVENTORY_SALES.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.INVENTORY_SALES.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
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

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.SEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.SEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            } else {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
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
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.SEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.SEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        }
        return pvDTO;
    }

    /**
     * Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getCommonPERCustomization(String Variable, final List<Object> dataList, ProjectionVarianceDTO gtsDto, ProjectionVarianceDTO salesDto, PVSelectionDTO pvsdto, DecimalFormat format, boolean actualBasis, int index) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Variable.concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Variable.concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Variable.concat(Constant.CHANGE));
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
                    boolean actualCheck = "null".equalsIgnoreCase(StringUtils.EMPTY + obj[index - 1]);
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
                    String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                    String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
//                        for ACTUAL
                        String baseValue = getFormattedValue(format, actualValue);
                        if (!actualCheck) {
                            pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue + PERCENT);
                        } else {
                            pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), actualDASH);
                        }
//                        for CURRENT
                        baseValue = getFormattedValue(format, currentValue);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                    }

                    if (actualBasis) {
                        if (!actualCheck) {
                            if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
//                        for CURRENT
                                String val = getVariance(actualValue, currentValue, format);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), val + PERCENT);

                            }
                            if (pvsdto.getVarIndicator().equals(CHANGE.getConstant())) {
//                        for CURRENT
                                String val = getPerChange(actualValue, currentValue, format);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), val + PERCENT);

                            }
                        } else if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), actualDASH);
                        }
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {

                            String val = getFormattedValue(format, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            if (actualBasis) {
                                if (!actualCheck) {
                                    String val = getVariance(actualValue, priorVal, format);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                                }
                            } else {
                                String val = getVariance(currentValue, priorVal, format);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                            }

                        } else if (actualBasis) {
                            if (!actualCheck) {
                                String baseValu = getPerChange(actualValue, priorVal, format);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                            }
                        } else {
                            String baseValu = getPerChange(currentValue, priorVal, format);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        }
                    }
                }
            }
        } else {
            List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
            columnList.remove(Constant.GROUP);
            for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
                pvDTO.addStringProperties(nullObj, DASH);
            }
            for (Object object : columnList) {
                String gts = StringUtils.EMPTY + String.valueOf(gtsDto.getPropertyValue(object)).replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", ZERO);
                String sales = StringUtils.EMPTY + String.valueOf(salesDto.getPropertyValue(object)).replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", ZERO);
                Double pob = (Double.valueOf(isNull(sales)) / Double.valueOf(isNull(gts))) * NumericConstants.HUNDRED;
                if (pob.isInfinite() || pob.isNaN()) {
                    pob = 0.0;
                }
                if (!String.valueOf(object).contains("Current")) {
                    if (pvDTO.getGroup().equals(Variable.concat(Constant.CHANGE))) {
                    } else if (pvDTO.getGroup().equals(Variable.toString().concat(Constant.VALUE))) {
                        String POB = getFormattedValue(RATE, String.valueOf(pob));
                        pvDTO.addStringProperties(object, POB + PERCENT);
                    }
                } else if (pvDTO.getGroup().equals(Variable.concat(Constant.VALUE))) {
                    String POB = getFormattedValue(RATE, String.valueOf(pob));
                    pvDTO.addStringProperties(object, POB + PERCENT);
                }

                if (dataList != null && !dataList.isEmpty()) {

                    for (int i = 0; i < dataList.size(); i++) {
                        final Object[] obj = (Object[]) dataList.get(i);
                        String commonColumn = StringUtils.EMPTY;
                        boolean actualCheck = "null".equalsIgnoreCase(StringUtils.EMPTY + obj[index - 1]);
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
                        String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                        String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));

                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
//                        for ACTUAL
                            String baseValue = getFormattedValue(format, actualValue);
                            if (!actualCheck) {
                                pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue + PERCENT);
                            } else {
                                pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), actualDASH);
                            }
//                        for CURRENT
                            baseValue = getFormattedValue(format, currentValue);
                            pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                        }

                        if (actualBasis) {
                            if (!actualCheck) {

                                if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
//                        for CURRENT
                                    String val = getVariance(actualValue, currentValue, format);
                                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), val + PERCENT);

                                }
                                if (pvsdto.getVarIndicator().equals(CHANGE.getConstant())) {
//                        for CURRENT
                                    String val = getPerChange(actualValue, currentValue, format);
                                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), val + PERCENT);

                                }
                            } else if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                                pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), actualDASH);
                            }
                        }
                        for (int j = 0; j < priorList.size(); j++) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {

                                String val = getFormattedValue(format, priorVal);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                            } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                                if (actualBasis) {
                                    if (!actualCheck) {
                                        String val = getVariance(actualValue, priorVal, format);
                                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                                    }
                                } else {
                                    String val = getVariance(currentValue, priorVal, format);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);
                                }

                            } else if (actualBasis) {
                                if (!actualCheck) {
                                    String baseValu = getPerChange(actualValue, priorVal, RATE_PER);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                                }
                            } else {
                                String baseValu = getPerChange(currentValue, priorVal, RATE_PER);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                            }
                        }
                    }

                }
            }

        }
        return pvDTO;
    }

    /**
     * Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getPERDemand(final List<Object> dataList, ProjectionVarianceDTO gtsDto, ProjectionVarianceDTO salesDto, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.CHANGE));
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
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN])));
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = StringUtils.EMPTY + obj[NumericConstants.ELEVEN];
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.ELEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String val = getFormattedValue(RATE, isNull(priorVal));
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.ELEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.ELEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
            columnList.remove(Constant.GROUP);
            for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
                pvDTO.addStringProperties(nullObj, DASH);
            }
            for (Object object : columnList) {
                String gts = StringUtils.EMPTY + String.valueOf(gtsDto.getPropertyValue(object)).replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", ZERO);
                String sales = StringUtils.EMPTY + String.valueOf(salesDto.getPropertyValue(object)).replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", ZERO);
                Double pob = (Double.valueOf(isNull(sales)) / Double.valueOf(isNull(gts))) * NumericConstants.HUNDRED;
                if (pob.isInfinite() || pob.isNaN()) {
                    pob = 0.0;
                }
                if (!String.valueOf(object).contains("Current")) {
                    if (pvDTO.getGroup().equals(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.VALUE))) {
                        String POB = getFormattedValue(RATE, String.valueOf(pob));
                        pvDTO.addStringProperties(object, POB + PERCENT);
                    }
                } else if (pvDTO.getGroup().equals(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.VALUE))) {
                    String POB = getFormattedValue(RATE, String.valueOf(pob));
                    pvDTO.addStringProperties(object, POB + PERCENT);
                }

            }

            if (dataList != null && !dataList.isEmpty()) {
                for (int i = 0; i < dataList.size(); i++) {
                    final Object[] obj = (Object[]) dataList.get(i);
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
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.ELEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(CHANGE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.ELEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }

        }
        return pvDTO;
    }

    /**
     * Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getPERInvWithdrawal(final List<Object> dataList, ProjectionVarianceDTO gtsDto, ProjectionVarianceDTO salesDto, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.CHANGE));
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
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWELVE])));
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = StringUtils.EMPTY + obj[NumericConstants.THIRTEEN];
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String val = getFormattedValue(RATE, isNull(priorVal));
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTEEN])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTEEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else {
            List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
            columnList.remove(Constant.GROUP);
            for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
                pvDTO.addStringProperties(nullObj, DASH);
            }
            for (Object object : columnList) {
                String gts = StringUtils.EMPTY + String.valueOf(gtsDto.getPropertyValue(object)).replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", ZERO);
                String sales = StringUtils.EMPTY + String.valueOf(salesDto.getPropertyValue(object)).replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", ZERO);
                Double pob = (Double.valueOf(isNull(sales)) / Double.valueOf(isNull(gts))) * NumericConstants.HUNDRED;
                if (pob.isInfinite() || pob.isNaN()) {
                    pob = 0.0;
                }
                if (!String.valueOf(object).contains("Current")) {
                    if (pvDTO.getGroup().equals(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.VALUE))) {
                        String POB = getFormattedValue(RATE, String.valueOf(pob));
                        pvDTO.addStringProperties(object, POB + PERCENT);
                    }
                } else if (pvDTO.getGroup().equals(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.VALUE))) {
                    String POB = getFormattedValue(RATE, String.valueOf(pob));
                    pvDTO.addStringProperties(object, POB + PERCENT);
                }

                if (dataList != null && !dataList.isEmpty()) {
                    for (int i = 0; i < dataList.size(); i++) {
                        final Object[] obj = (Object[]) dataList.get(i);
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
                        for (int j = 0; j < priorList.size(); j++) {
                            if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                                String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTEEN])) - Double.valueOf(isNull(priorVal)));
                                String val = getFormattedValue(RATE, variance);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                            } else if (pvsdto.getVarIndicator().equals(CHANGE.getConstant())) {
                                String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTEEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                                String change = String.valueOf(perChange);
                                String baseValu = getFormattedValue(RATE_PER, change);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                            }
                        }
                    }
                }

            }
        }
        return pvDTO;
    }

    /**
     * ContractSales
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getContractSales(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_CONTRACT_SALES.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_CONTRACT_SALES.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_CONTRACT_SALES.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
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

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FOURTEEN])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIFTEEN])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIFTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.FIFTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIFTEEN])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = obj[NumericConstants.FIFTEEN + ((j + 1) * NumericConstants.FORTY_TWO)] != null ? StringUtils.EMPTY + obj[NumericConstants.FIFTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)] : ZERO;
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIFTEEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
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
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FOUR])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWO])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWO + ((j + 1) * COLUMN_COUNT_DISCOUNT)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.TWO + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWO])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = obj[NumericConstants.TWO + ((j + 1) * NumericConstants.EIGHT)] != null ? StringUtils.EMPTY + obj[NumericConstants.TWO + ((j + 1) * COLUMN_COUNT_DISCOUNT)] : ZERO;
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWO])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                    }
                }
            }
        }

        return pvDTO;
    }

    /**
     * Units
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getContractUnits(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_CONTRACT_UNITS.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_CONTRACT_UNITS.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_CONTRACT_UNITS.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
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

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIXTEEN])));
                        String baseValue = getFormattedValue(AMOUNT_UNITS, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVENTEEN])));
                        String baseValue = getFormattedValue(AMOUNT_UNITS, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVENTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT_UNITS, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.SEVENTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVENTEEN])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT_UNITS, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = obj[NumericConstants.SEVENTEEN + ((j + 1) * NumericConstants.EIGHTEEN)] != null ? StringUtils.EMPTY + obj[NumericConstants.SEVENTEEN + ((j + 1) * COLUMN_COUNT_TOTAL)] : ZERO;
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVENTEEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
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

                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIVE])));
                    String baseValue = getFormattedValue(AMOUNT_UNITS, value);
                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THREE])));
                    String baseValue = getFormattedValue(AMOUNT_UNITS, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THREE + ((j + 1) * COLUMN_COUNT_DISCOUNT)])));
                        String val = getFormattedValue(AMOUNT_UNITS, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.THREE + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THREE])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT_UNITS, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = obj[NumericConstants.THREE + ((j + 1) * NumericConstants.EIGHT)] != null ? StringUtils.EMPTY + obj[NumericConstants.THREE + ((j + 1) * COLUMN_COUNT_DISCOUNT)] : ZERO;
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THREE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                    }
                }
            }
        }

        return pvDTO;
    }

    /**
     * Discount DOl
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getDiscountDol(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_DIS_AMOUNT.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_DIS_AMOUNT.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_DIS_AMOUNT.toString().concat(Constant.CHANGE));
        }
        if (pvsdto.isDiscountFlag()) {
            pvDTO.setParent(1);
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());

        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
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

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHTEEN])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINETEEN])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINETEEN + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.NINETEEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINETEEN])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = obj[NumericConstants.NINETEEN + ((j + 1) * NumericConstants.FORTY_TWO)] != null ? StringUtils.EMPTY + obj[NumericConstants.NINETEEN + ((j + 1) * COLUMN_COUNT_TOTAL)] : ZERO;
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINETEEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER_THREE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
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
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINE])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT + ((j + 1) * COLUMN_COUNT_DISCOUNT)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.EIGHT + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = obj[NumericConstants.EIGHT + ((j + 1) * NumericConstants.EIGHT)] != null ? StringUtils.EMPTY + obj[NumericConstants.EIGHT + ((j + 1) * COLUMN_COUNT_DISCOUNT)] : ZERO;
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHT])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER_THREE, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                    }
                }
            }
        }

        return pvDTO;
    }

    /**
     * Discount Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getDiscountPer(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_DIS_RATE.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_DIS_RATE.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_DIS_RATE.toString().concat(Constant.CHANGE));
        }
        if (pvsdto.isDiscountFlag()) {
            pvDTO.setParent(1);
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
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

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWENTY])));
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWENTY_ONE])));
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWENTY_ONE + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(RATE, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.TWENTY_ONE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWENTY_ONE])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = obj[NumericConstants.TWENTY_ONE + ((j + 1) * NumericConstants.TWENTY_ONE)] != null ? StringUtils.EMPTY + obj[NumericConstants.TWENTY_ONE + ((j + 1) * COLUMN_COUNT_TOTAL)] : ZERO;
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWENTY_ONE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER_THREE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
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
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVEN])));
                    String baseValue = getFormattedValue(RATE, value);
                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])));
                    String baseValue = getFormattedValue(RATE, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX + ((j + 1) * COLUMN_COUNT_DISCOUNT)])));
                        String val = getFormattedValue(RATE, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.SIX + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(RATE, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                    } else {
                        String priorVal = obj[NumericConstants.SIX + ((j + 1) * NumericConstants.EIGHT)] != null ? StringUtils.EMPTY + obj[NumericConstants.SIX + ((j + 1) * COLUMN_COUNT_DISCOUNT)] : ZERO;
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIX])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                    }
                }
            }
        }
        return pvDTO;
    }

    /**
     * Discount Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getRPU(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_RPU.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_RPU.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_RPU.toString().concat(Constant.CHANGE));
        }
        if (pvsdto.isDiscountFlag()) {
            pvDTO.setParent(1);
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
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

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_ONE])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_ONE + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTY_ONE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_ONE])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = obj[NumericConstants.THIRTY_ONE + ((j + 1) * COLUMN_COUNT_TOTAL)] != null ? StringUtils.EMPTY + obj[NumericConstants.THIRTY_ONE + ((j + 1) * COLUMN_COUNT_TOTAL)] : ZERO;
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_ONE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER_THREE, change);

                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
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
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TWELVE])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTEEN])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTEEN])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = obj[NumericConstants.THIRTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)] != null ? StringUtils.EMPTY + obj[NumericConstants.THIRTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)] : ZERO;
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTEEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER_THREE, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                    }
                }
            }
        }
        return pvDTO;
    }

    /**
     * Discount Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getNetSales(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_NETSALES.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_NETSALES.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_NETSALES.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
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

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_TWO])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_THREE])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_THREE + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTY_THREE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_THREE])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTY_THREE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_THREE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
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
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.ELEVEN])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.TEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.TEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.TEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                    }
                }
            }
        }
        return pvDTO;
    }

    /**
     * Discount Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getCOGC(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_COGS.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_COGS.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_COGS.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
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

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_FOUR])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                    }

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_FIVE])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTY_FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_FIVE])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTY_FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_FIVE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
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
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FIFTEEN])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FOURTEEN])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FOURTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.FOURTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FOURTEEN])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.FOURTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FOURTEEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                    }
                }
            }
        }
        return pvDTO;
    }

    /**
     * Discount Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getNetProfit(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_NET_PROFITE.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.VAR_NET_PROFITE.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.VAR_NET_PROFITE.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
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

                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_SIX])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_SEVEN])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_SEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTY_SEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_SEVEN])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.THIRTY_SEVEN + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.THIRTY_SEVEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
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

                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SEVENTEEN])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIXTEEN])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIXTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.SIXTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIXTEEN])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.SIXTEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.SIXTEEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                    }
                }
            }
        }
        return pvDTO;
    }

    public ProjectionVarianceDTO getCustomisedProjectionResultsDiscount(List<Object> list, String discountName, PVSelectionDTO projSelDTO) {
        LOGGER.info("Inside getCustomisedProjectionResultsDiscount");
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
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
                    if (!discountName.contains(Constant.TOTAL_SMALL)) {
                        discountList.remove(discount);
                    }
                    start = false;
                }
                if (start) {
                    discount = discountRow[NumericConstants.TWO] != null ? discountRow[NumericConstants.TWO].toString() : StringUtils.EMPTY;
                    String column = StringUtils.EMPTY;
                    String column1 = StringUtils.EMPTY;
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

    public static double roundToFraction(double x, long fraction) {
        return (double) Math.round(x * fraction) / fraction;
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
        Map<String, Object> parameters = new HashMap<String, Object>();

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
                    parameters.put("filter~Between~" + betweenFilter.getPropertyId() + "~from", CommonLogic.convertStringToDate(String.valueOf(startValue), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                    parameters.put("filter~Between~" + betweenFilter.getPropertyId() + "~to", CommonLogic.convertStringToDate(String.valueOf(endValue), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                } else if (filterSet instanceof Compare) {
                    Compare compare = (Compare) filterSet;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (compare.getValue() instanceof Date) {
                        if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put("filter~GOE~" + compare.getPropertyId() + "~from", CommonLogic.convertStringToDate(String.valueOf(value), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                        } else {
                            parameters.put("filter~GOE~" + compare.getPropertyId() + "~to", CommonLogic.convertStringToDate(String.valueOf(value), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                        }
                    }
                }
            }
        }
        String resultString = new PVQueryUtils().getComparisionSearchResults(comparisonLookup, screenName, parameters, null, 0, 0, true);
        List result = (List) commonDao.executeSelectQuery(resultString, null, null);
        if (result != null && result.size() > 0) {
            count = Integer.valueOf(String.valueOf(result.get(0)));
        } else {
            count = 0;
        }
        return count;
    }

    public List<ComparisonLookupDTO> getComparisonResults(final ComparisonLookupDTO comparisonLookup, int start, int offset, Set<Filter> filter, List<SortByColumn> sortColumns, String screenName) throws PortalException, SystemException {
        Map<String, Object> parameters = new HashMap<String, Object>();

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
                    parameters.put("filter~Between~" + betweenFilter.getPropertyId() + "~from", CommonLogic.convertStringToDate(String.valueOf(startValue), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                    parameters.put("filter~Between~" + betweenFilter.getPropertyId() + "~to", CommonLogic.convertStringToDate(String.valueOf(endValue), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                } else if (filterSet instanceof Compare) {
                    Compare compare = (Compare) filterSet;
                    Compare.Operation operation = compare.getOperation();
                    Date value = (Date) compare.getValue();
                    if (compare.getValue() instanceof Date) {
                        if (operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            parameters.put("filter~GOE~" + compare.getPropertyId() + "~from", CommonLogic.convertStringToDate(String.valueOf(value), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
                        } else {
                            parameters.put("filter~GOE~" + compare.getPropertyId() + "~to", CommonLogic.convertStringToDate(String.valueOf(value), DEFAULT_JAVA_DATE_FORMAT.getConstant(), DEFAULT_SQL_DATE_FORMAT.getConstant()));
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
        List<NmProjectionSelection> list = new ArrayList<NmProjectionSelection>();
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

    public ProjectionVarianceDTO getCustomizedReturns(List<Object> dataList, PVSelectionDTO projSelDTO, boolean isDetail, int index, boolean isPer) {
        LOGGER.info("Inside getCustomizedReturns");
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        List<Integer> priorList = new ArrayList<Integer>(projSelDTO.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {

            for (int i = 0; i < dataList.size(); i++) {
                if (isDetail) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    String column1 = StringUtils.EMPTY;
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
                    column1 = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
                    String value = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                    if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                        value = getFormattedValue(AMOUNT, value);
                    } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                        value = getFormattedValue(RATE, value);
                    }
                    pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
                    for (int j = 0; j < priorList.size(); j++) {
                        column1 = commonColumn + priorList.get(j);
                        String pValue = StringUtils.EMPTY + obj[NumericConstants.FOUR + ((j + 1) * NumericConstants.THREE)];
                        if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                            pValue = getFormattedValue(AMOUNT, pValue);
                        } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                            pValue = getFormattedValue(RATE, pValue);
                        }
                        pvDTO.addStringProperties(column1, isPer ? pValue + PERCENT : pValue);
                    }
                } else {
                    final Object[] obj = (Object[]) dataList.get(i);
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
                    if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.TWENTY_ONE)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.TWENTY_ONE)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.TWENTY_ONE)];
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
            }
        }
        LOGGER.info("Ending getCustomizedReturns");
        return pvDTO;
    }

    /**
     * Customization for PPA discounts level
     *
     * @param dataList
     * @param projSelDTO
     * @param isDetail
     * @param index
     * @return
     */
    public ProjectionVarianceDTO getCustomizedPPA(List<Object> dataList, PVSelectionDTO projSelDTO, boolean isDetail, int index, boolean isPer) {
        LOGGER.info("Inside getCustomizedPPA");
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        List<Integer> priorList = new ArrayList<Integer>(projSelDTO.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {

            for (int i = 0; i < dataList.size(); i++) {
                if (isDetail) {
                    final Object[] obj = (Object[]) dataList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    String column1 = StringUtils.EMPTY;
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
                    column1 = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
                    String value = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                    if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                        value = getFormattedValue(AMOUNT, value);
                    } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                        value = getFormattedValue(RATE_PER_THREE, value);
                    }
                    pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
                    for (int j = 0; j < priorList.size(); j++) {
                        column1 = commonColumn + priorList.get(j);
                        String pValue = StringUtils.EMPTY + obj[NumericConstants.FIVE + ((j + 1) * NumericConstants.THREE)];
                        if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                            pValue = getFormattedValue(AMOUNT, pValue);
                        } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                            pValue = getFormattedValue(RATE_PER_THREE, pValue);
                        }
                        pvDTO.addStringProperties(column1, isPer ? pValue + PERCENT : pValue);
                    }
                } else {
                    final Object[] obj = (Object[]) dataList.get(i);
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
                    if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.TWENTY_ONE)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.TWENTY_ONE)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.TWENTY_ONE)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        }
                    }
                }
            }
        }
        LOGGER.info("Ending getCustomizedPPA");
        return pvDTO;
    }

    public List<ProjectionVarianceDTO> getCustomisedProjectionResultsTotalDiscount(List<Object> dataList, PVSelectionDTO projSelDTO, int index, boolean isPer) {
        LOGGER.info("Inside getCustomisedProjectionResultsTotalDiscount");
        String lastValue = StringUtils.EMPTY;
        String lastGroupName = StringUtils.EMPTY;
        String column = StringUtils.EMPTY;
        String value = StringUtils.EMPTY;
        String actValue = StringUtils.EMPTY;
        boolean actualBasis = "Actuals".equalsIgnoreCase(projSelDTO.getComparisonBasis());
        List<ProjectionVarianceDTO> resultDto = new ArrayList<ProjectionVarianceDTO>();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        List<Integer> priorList = new ArrayList<Integer>(projSelDTO.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                if ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())) {
                    if (!StringUtils.EMPTY.equals(lastValue) && !Constant.NULL.equals(lastValue) && obj[NumericConstants.TWO] != null && !lastValue.equals(String.valueOf(obj[NumericConstants.TWO]))) {
                        pvDTO.setGroup(lastValue);
                        resultDto.add(pvDTO);
                        pvDTO = new ProjectionVarianceDTO();
                    }
                    lastValue = String.valueOf(obj[NumericConstants.TWO]);
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
                boolean actualCheck = "null".equalsIgnoreCase(StringUtils.EMPTY + obj[index - 1]);
                value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));
                actValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));

                if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                    String baseValue = StringUtils.EMPTY;
                    String baseValueAct = StringUtils.EMPTY;
                    if (isPer) {
                        baseValue = getFormattedValue(RATE_PER, value);
                        baseValueAct = getFormattedValue(RATE_PER, actValue);
                    } else {
                        baseValue = getFormattedValue(AMOUNT, value);
                        baseValueAct = getFormattedValue(AMOUNT, actValue);
                    }
                    if (!actualCheck) {
                        pvDTO.addStringProperties(commonColumn + ACTUAL + projSelDTO.getCurrentProjId(), isPer ? baseValueAct + PERCENT : baseValueAct);
                    } else {
                        pvDTO.addStringProperties(commonColumn + ACTUAL + projSelDTO.getCurrentProjId(), actualDASH);
                    }
                    pvDTO.addStringProperties(commonColumn + CURRENT + projSelDTO.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                }

                if (actualBasis) {
                    if (!actualCheck) {

                        if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                            column = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
//                        for CURRENT
                            String val = getVariance(actValue, value, AMOUNT);
                            pvDTO.addStringProperties(column, val);
                        }
                        if (projSelDTO.getVarIndicator().equals(CHANGE.getConstant())) {
                            column = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
//                        for CURRENT
                            String val = getPerChange(actValue, value, RATE);
                            pvDTO.addStringProperties(column, val);
                        }
                    } else if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                        pvDTO.addStringProperties(commonColumn + ACTUAL + projSelDTO.getCurrentProjId(), actualDASH);
                    }
                }

                for (int j = 0; j < priorList.size(); j++) {

                    if (projSelDTO.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.EIGHT)])));
                        String val = StringUtils.EMPTY;
                        if (isPer) {
                            val = getFormattedValue(RATE_PER, priorVal);
                        } else {
                            val = getFormattedValue(AMOUNT, priorVal);
                        }
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                    } else if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.EIGHT)];
                        String val = StringUtils.EMPTY;
                        if (actualBasis) {
                            if (!actualCheck) {
                                if (isPer) {
                                    val = getVariance(actValue, priorVal, RATE_PER);
                                } else {
                                    val = getVariance(actValue, priorVal, AMOUNT);
                                }
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                            }
                        } else {
                            if (isPer) {
                                val = getVariance(value, priorVal, RATE_PER);
                            } else {
                                val = getVariance(value, priorVal, AMOUNT);
                            }
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                        }
                    } else {
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * NumericConstants.EIGHT)];
                        String val = StringUtils.EMPTY;
                        if (actualBasis) {
                            if (!actualCheck) {
                                String baseValu = getPerChange(actValue, priorVal, RATE_PER);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                            }
                        } else {
                            String baseValu = getPerChange(value, priorVal, RATE_PER);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        }
                    }
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
     * Used to load PPA static row and discount under Total Discount
     *
     * @param list
     * @param discountName
     * @param projSelDTO
     * @return List<ProjectionResultsDTO>
     */
    public List<ProjectionVarianceDTO> loadDiscounts(List<Object> list, String discountName, PVSelectionDTO projSelDTO, ProjectionVarianceDTO parentDto) {
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        List<ProjectionVarianceDTO> projDtoList = new ArrayList<ProjectionVarianceDTO>();

        ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
        projDTO.setLevelValue(projSelDTO.getLevelValue());
        projDTO.setLevelNo(projSelDTO.getLevelNo());
        projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projDTO.setGroup(null);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<Integer> priorList = new ArrayList<Integer>(projSelDTO.getProjIdList());
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Object[] discountRow = (Object[]) list.get(i);
                if ("Program Category".equalsIgnoreCase(projSelDTO.getDiscountLevel())) {
                    if (StringUtils.isBlank(discountName) || !discountName.equals(String.valueOf(discountRow[NumericConstants.TWO]))) {
                        projDTO = new ProjectionVarianceDTO();
                        projDtoList.add(projDTO);
                        discountName = String.valueOf(discountRow[NumericConstants.TWO]);
                        projDTO.setGroup(discountName);
                        projDTO.setRelationshipLevelName(discountName);
                    }
                } else if (StringUtils.isBlank(discountName) || !discountName.equals(String.valueOf(discountRow[NumericConstants.TWO]))) {
                    projDTO = new ProjectionVarianceDTO();
                    projDtoList.add(projDTO);
                    discountName = String.valueOf(discountRow[NumericConstants.TWO]);
                    projDTO.setGroup(String.valueOf(discountRow[NumericConstants.TWO]));
                    projDTO.setRelationshipLevelName(String.valueOf(discountRow[NumericConstants.TWO]));
                }
                String column = StringUtils.EMPTY;
                String column1 = StringUtils.EMPTY;
                String columnActual = StringUtils.EMPTY;
                if (frequencyDivision == NumericConstants.FOUR) {
                    column = Constant.Q + discountRow[1] + StringUtils.EMPTY + discountRow[0];
                } else if (frequencyDivision == NumericConstants.TWO) {
                    column = Constant.S + discountRow[1] + StringUtils.EMPTY + discountRow[0];
                } else if (frequencyDivision == 1) {
                    column = StringUtils.EMPTY + discountRow[0];
                } else if (frequencyDivision == NumericConstants.TWELVE) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(discountRow[1])) - 1).toLowerCase();
                    column = monthName + discountRow[0];
                }
                column1 = column + CURRENT + projSelDTO.getCurrentProjId();
                columnActual = column + ACTUAL + projSelDTO.getCurrentProjId();
                boolean actualCheck = "null".equalsIgnoreCase(StringUtils.EMPTY + discountRow[NumericConstants.THREE]);

                if (projSelDTO.hasColumn(columnActual)) {
                    String value = null;
                    if (parentDto.getPropertyValue(columnActual) != null) {
                        value = StringUtils.EMPTY + discountRow[NumericConstants.THREE];
                        if (parentDto.getGroup().contains(Constant.PVVariables.VAR_DIS_AMOUNT.toString()) && parentDto.getGroup().contains(Constant.CHANGE.toString())) {
                            value = getFormattedValue(RATE_PER_THREE, value);
                            value = value + PERCENT;
                        } else if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                            value = getFormattedValue(AMOUNT, value);
                        } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                            value = getFormattedValue(RATE_PER_THREE, value);
                            value = value + PERCENT;
                        }
                    }
                    projDTO.addStringProperties(columnActual, value);
                    columnList.remove(columnActual);
                }

                if (projSelDTO.hasColumn(column1)) {
                    String value = null;
                    if (parentDto.getPropertyValue(column1) != null) {
                        value = StringUtils.EMPTY + discountRow[NumericConstants.FOUR];
                        if (parentDto.getGroup().contains(Constant.PVVariables.VAR_DIS_AMOUNT.toString()) && parentDto.getGroup().contains(Constant.CHANGE.toString())) {
                            value = getFormattedValue(RATE_PER_THREE, value);
                            value = value + PERCENT;
                        } else if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                            value = getFormattedValue(AMOUNT, value);
                        } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                            value = getFormattedValue(RATE_PER_THREE, value);
                            value = value + PERCENT;
                        }
                    }
                    projDTO.addStringProperties(column1, value);
                    columnList.remove(column1);
                }
                for (int j = 0; j < priorList.size(); j++) {
                    column1 = column + priorList.get(j);
                    if (projSelDTO.hasColumn(column1)) {
                        String priorVal = null;
                        if (parentDto.getPropertyValue(column1) != null) {

                            if (((parentDto.getGroup().contains(Constant.PVVariables.VAR_DIS_AMOUNT.toString())) || (parentDto.getGroup().contains(Constant.PVVariables.VAR_RPU.toString())) || projSelDTO.getSales().contains(Constant.RATE)) && parentDto.getGroup().contains(Constant.CHANGE.toString())) {
                                priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + discountRow[NumericConstants.FOUR + ((j + 1) * NumericConstants.TWO)])));
                                priorVal = getFormattedValue(RATE_PER_THREE, priorVal);
                                priorVal = priorVal + PERCENT;
                            } else if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                                priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + discountRow[NumericConstants.FOUR + ((j + 1) * NumericConstants.TWO)])));
                                priorVal = getFormattedValue(AMOUNT, priorVal);
                            } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                                priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + discountRow[NumericConstants.FOUR + ((j + 1) * NumericConstants.TWO)])));
                                priorVal = getFormattedValue(RATE, priorVal);
                                priorVal = priorVal + PERCENT;
                            }
                        }
                        projDTO.addStringProperties(column1, priorVal);
                        columnList.remove(column1);
                    }
                }
            }

        }

        for (String columns : columnList) {
            projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
        }
        LOGGER.info("Ending getCustomisedProjectionResultsDiscount");
        return projDtoList;
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
        String query = "SELECT Count(DISTINCT RS_CONTRACT_SID)\n"
                + "FROM  " + tableName + "NM_DISCOUNT_PROJ_MASTER B\n";
        if (viewFlag) {
            query += "       JOIN PROJECTION_DETAILS pd\n"
                    + "         ON pd.PROJECTION_DETAILS_SID = b.PROJECTION_DETAILS_SID\n";
        }
        query += "       JOIN @CCP CCP\n"
                + "         ON B.ccp_details_sid = ccp.CCP_DETAILS_SID\n";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            query += "  WHERE B.RS_CONTRACT_SID IN ( " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " );";
        }
        return query;
    }

    private String getProgramCategoryForCurrentHierarchy(PVSelectionDTO projSelDTO) {
        boolean viewFlag = Boolean.FALSE;
        String tableName = viewFlag ? StringUtils.EMPTY : "ST_";
        String discountNoList = StringUtils.EMPTY;
        String indicatorValue = StringUtils.EMPTY;
        discountNoList += "       " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
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
        query += "        WHERE  DM.RS_CONTRACT_SID\n"
                + "                    IN (" + discountNoList + " )\n"
                + "               AND RS.INBOUND_STATUS <> 'D'\n"
                + "               AND EXISTS (SELECT 1\n"
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
        String ccpQuery = new CommonLogic().insertAvailableHierarchyNo(projSelDTO);
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
            e.printStackTrace();
        }
        return count;
    }

    /**
     * Net Sales % of Ex-Factory
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getNetSalesExFactory(final List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto) {

        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.NET_SALES_PER_EX_FACTORY.toString().concat(Constant.CHANGE));
        }
        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<Integer>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
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
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FORTY_FOUR])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FORTY_FIVE])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FORTY_FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.FORTY_FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FORTY_FIVE])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[NumericConstants.FORTY_FIVE + ((j + 1) * COLUMN_COUNT_TOTAL)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.FORTY_FIVE])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
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
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.EIGHTEEN])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), baseValue);

                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINETEEN])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINETEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.NINETEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINETEEN])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = StringUtils.EMPTY + obj[NumericConstants.NINETEEN + ((j + 1) * COLUMN_COUNT_DISCOUNT)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[NumericConstants.NINETEEN])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        perChange = perChange * NumericConstants.DOUBLE_HUNDRED;
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);

                    }
                }
            }
        }
        return pvDTO;
    }

    /**
     *
     * @param varaibleName
     * @param varibaleCat
     * @param index
     * @param dataList
     * @param pvsdto
     * @param format
     * @param actualBasis
     * @return
     */
    public ProjectionVarianceDTO calculateCommon(String varaibleName, String varibaleCat, int index, List<Object> dataList, PVSelectionDTO pvsdto, DecimalFormat format, boolean actualBasis) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        boolean isPer = varaibleName.contains("%");
        pvDTO.setGroup(varaibleName.concat(varibaleCat));

        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        List<Integer> priorList = new ArrayList<>(pvsdto.getProjIdList());
        if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                String commonColumn = StringUtils.EMPTY;
                boolean actualCheck = "null".equalsIgnoreCase(StringUtils.EMPTY + obj[index - 1]);
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
                String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));

                if (varibaleCat.equals(Constant.VALUE)) {
                    //  for ACTUAL
                    String baseValue = getFormattedValue(format, actualValue);
                    if (!actualCheck) {
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    } else {
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), actualDASH);
                    }
                    // for CURRENT
                    String baseValueAct = getFormattedValue(format, currentValue);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), isPer ? baseValueAct + PERCENT : baseValueAct);

                }

                if (actualBasis) {
                    if (!actualCheck) {

                        if (varibaleCat.equals(Constant.VARIANCE)) {
                            //  for CURRENT
                            String val = getVariance(actualValue, currentValue, format);
                            pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), isPer ? val + PERCENT : val);

                        }
                        if (varibaleCat.equals(Constant.CHANGE)) {
                            //  for CURRENT
                            String val = getPerChange(actualValue, currentValue, format);
                            pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), val + PERCENT);

                        }
                    } else if (varibaleCat.equals(Constant.VALUE)) {
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), actualDASH);
                    }
                }
                for (int j = 0; j < priorList.size(); j++) {
                    String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                    if (varibaleCat.equals(Constant.VALUE)) {

                        String val = getFormattedValue(format, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);

                    } else if (varibaleCat.equals(Constant.VARIANCE)) {
                        if (actualBasis) {
                            if (!actualCheck) {
                                String val = getVariance(actualValue, priorVal, format);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                            }
                        } else {
                            String val = getVariance(currentValue, priorVal, format);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                        }

                    } else if (actualBasis) {
                        if (!actualCheck) {
                            String baseValu = getPerChange(actualValue, priorVal, RATE_PER);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        }
                    } else {
                        String baseValu = getPerChange(currentValue, priorVal, RATE_PER);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                    }
                }
            }
        }

        return pvDTO;
    }

    public ProjectionVarianceDTO calculateDetailTotal(String varaibleName, String varibaleCat, int index, List<Object> gtsList, final List<Object> dataList, PVSelectionDTO pvsdto, DecimalFormat format, boolean actualBasis, int discountIndex, boolean parentFlag) {

        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        boolean isPer = varaibleName.contains("%");

        pvDTO.setGroup(varaibleName.concat(varibaleCat));

        for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
            pvDTO.addStringProperties(nullObj, DASH);
        }
        if (pvsdto.isDiscountFlag() && parentFlag) {
            pvDTO.setParent(1);
        }
        List<Integer> priorList = new ArrayList<>(pvsdto.getProjIdList());
        if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
            if (gtsList != null && !gtsList.isEmpty()) {
                for (int i = 0; i < gtsList.size(); i++) {
                    final Object[] obj = (Object[]) gtsList.get(i);
                    String commonColumn = StringUtils.EMPTY;
                    boolean actualCheck = "null".equalsIgnoreCase(StringUtils.EMPTY + obj[index - 1]);
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
                    String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index - 1])));
                    String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])));

                    if (varibaleCat.equals(Constant.VALUE)) {
//                        for ACTUAL
                        String baseValue = getFormattedValue(format, actualValue);
                        if (!actualCheck) {
                            pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                        } else {
                            pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), actualDASH);
                        }
//                        for CURRENT
                        baseValue = getFormattedValue(format, currentValue);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);

                    }

                    if (actualBasis) {
                        if (!actualCheck) {
                            if (varibaleCat.equals(Constant.VARIANCE)) {
//                        for CURRENT
                                String val = getVariance(actualValue, currentValue, format);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), isPer ? val + PERCENT : val);

                            }
                            if (varibaleCat.equals(Constant.CHANGE)) {
//                        for CURRENT
                                String val = getPerChange(actualValue, currentValue, format);
                                pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), val + PERCENT);

                            }
                        } else if (varibaleCat.equals(Constant.VALUE)) {
                            pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), actualDASH);
                        }
                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * COLUMN_COUNT_TOTAL)])));
                        if (varibaleCat.equals(Constant.VALUE)) {

                            String val = getFormattedValue(format, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);

                        } else if (varibaleCat.equals(Constant.VARIANCE)) {
                            if (actualBasis) {
                                if (!actualCheck) {
                                    String val = getVariance(actualValue, priorVal, format);
                                    pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                                }
                            } else {
                                String val = getVariance(currentValue, priorVal, format);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                            }

                        } else if (actualBasis) {
                            if (!actualCheck) {
                                String baseValu = getPerChange(actualValue, priorVal, RATE_PER);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                            }
                        } else {
                            String baseValu = getPerChange(currentValue, priorVal, RATE_PER);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                String commonColumn = StringUtils.EMPTY;
                boolean actualCheck = "null".equalsIgnoreCase(StringUtils.EMPTY + obj[discountIndex - 1]);
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
                String actualValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[discountIndex - 1])));
                String currentValue = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[discountIndex])));

                if (varibaleCat.equals(Constant.VALUE)) {
//                        for ACTUAL
                    String baseValue = getFormattedValue(format, actualValue);
                    if (!actualCheck) {
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);
                    } else {
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), actualDASH);
                    }
//                        for CURRENT
                    baseValue = getFormattedValue(format, currentValue);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), isPer ? baseValue + PERCENT : baseValue);

                }
                if (actualBasis) {
                    if (!actualCheck) {
                        if (varibaleCat.equals(Constant.VARIANCE)) {
//                        for CURRENT
                            String val = getVariance(actualValue, currentValue, format);
                            pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), isPer ? val + PERCENT : val);

                        }
                        if (varibaleCat.equals(Constant.CHANGE)) {
//                        for CURRENT
                            String val = getPerChange(actualValue, currentValue, format);
                            pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), val + PERCENT);

                        }
                    } else if (varibaleCat.equals(Constant.VALUE)) {
                        pvDTO.addStringProperties(commonColumn + ACTUAL + pvsdto.getCurrentProjId(), actualDASH);
                    }
                }
                for (int j = 0; j < priorList.size(); j++) {
                    String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[discountIndex + ((j + 1) * COLUMN_COUNT_DISCOUNT)])));
                    if (varibaleCat.equals(Constant.VALUE)) {

                        String val = getFormattedValue(format, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);

                    } else if (varibaleCat.equals(Constant.VARIANCE)) {
                        if (actualBasis) {
                            if (!actualCheck) {
                                String val = getVariance(actualValue, priorVal, format);
                                pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                            }
                        } else {
                            String val = getVariance(currentValue, priorVal, format);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                        }

                    } else if (actualBasis) {
                        if (!actualCheck) {
                            String baseValu = getPerChange(actualValue, priorVal, RATE_PER);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                        }
                    } else {
                        String baseValu = getPerChange(currentValue, priorVal, RATE_PER);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                    }
                }
            }
        }
        return pvDTO;
    }

    public String getVariance(String actualValue, String priorVal, DecimalFormat format) {
        Double val = Double.valueOf(isNull(actualValue));
        Double val1 = Double.valueOf(isNull(priorVal));
        String value;
        String variance = StringUtils.EMPTY;
        if (format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE)) {
            value = String.valueOf(roundToFraction((val - val1), 10000));
            value = roundToFraction(Double.valueOf(value), 100) + "";
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
        String variance;
        if (format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE)) {
            value = String.valueOf(roundToFraction((val - val1), 10000));
            value = roundToFraction(Double.valueOf(value), 100) + "";
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

    public void customizePivot(String variableValue, String variableCategory, String currentSales, String actualSales, PVSelectionDTO pvsdto, ProjectionVarianceDTO projDTO, List<String> columnList, PVSelectionDTO baseVariables, DecimalFormat format) {
        try {
            String column = StringUtils.EMPTY;
            String columnAct = StringUtils.EMPTY;
            boolean actualBasis = "Actuals".equalsIgnoreCase(pvsdto.getComparisonBasis());
            boolean actualCheck = "null".equalsIgnoreCase(actualSales);
            boolean isPer = format.equals(RATE);

            if (variableCategory.equals(Constant.VALUE)) {
                //for current
                columnAct = variableValue + ACTUAL + pvsdto.getCurrentProjId();
                String baseValueAct = getFormattedValue(format, actualSales);
                if (pvsdto.hasColumn(columnAct)) {
                    if (!actualCheck) {
                        projDTO.addStringProperties(columnAct, isPer ? baseValueAct + PERCENT : baseValueAct);
                    } else {
                        projDTO.addStringProperties(columnAct, actualDASH);
                    }
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
                if (!actualCheck) {

                    if (variableCategory.equals(Constant.VARIANCE)) {
                        column = variableValue + CURRENT + pvsdto.getCurrentProjId();
//                        for CURRENT
//                String variance = getVariance(actualSales, currentSales,AMOUNT);
                        String val = getVariance(actualSales, currentSales, format);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, isPer ? val + PERCENT : val);
                            columnList.remove(column);
                        }
//                projDTO.addStringProperties(column, val);

                    }
                    if (variableCategory.equals(Constant.CHANGE)) {
                        column = variableValue + CURRENT + pvsdto.getCurrentProjId();
//                        for CURRENT
//                        String change = getPerChange(actualSales, currentSales,RATE);
                        String val = getPerChange(actualSales, currentSales, format);
                        if (pvsdto.hasColumn(column)) {
                            projDTO.addStringProperties(column, val + PERCENT);
                            columnList.remove(column);
                        }
//                projDTO.addStringProperties(column, val + PERCENT);
                    }
                } else if (variableCategory.equals(Constant.VALUE)) {
                    column = variableValue + ACTUAL + pvsdto.getCurrentProjId();
                    if (pvsdto.hasColumn(column)) {
                        projDTO.addStringProperties(column, actualDASH);
                        columnList.remove(column);
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public String priorlistQuery(PVSelectionDTO pvsdto) {
        String sql;
        List<String> projectionIdList = new ArrayList<String>();
        projectionIdList.add(String.valueOf(selectionDTO.getCurrentProjId()));
        for (Integer projId : pvsdto.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
//            pivotPriorProjIdList.add(projId);
        }
        String projectionId = CommonUtils.CollectionToString(projectionIdList, false);

        sql = SQlUtil.getQuery("projectionVariancePeriodQuery");
        sql = sql.replace("[?SELECTED_PROJECTION_IDS]", projectionId);
        return sql;
    }

}
