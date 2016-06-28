/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.projectionvariance.logic;

import com.stpl.app.galforecasting.dao.CommonDAO;
import com.stpl.app.galforecasting.dao.ProjectionVarianceDAO;
import com.stpl.app.galforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.galforecasting.dao.impl.ProjectionVarianceDAOImpl;

import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.app.galforecasting.dto.PVSelectionDTO;
import com.stpl.app.galforecasting.dto.ProjectionVarianceDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.galforecasting.queryUtils.PVQueryUtils;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.Converters;
import com.stpl.app.galforecasting.utils.HeaderUtils;
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
import static com.stpl.app.utils.Constants.LabelConstants.TOTAL;
import static com.stpl.app.utils.Constants.LabelConstants.TOTAL_DISCOUNT;
import static com.stpl.app.utils.Constants.LabelConstants.PERCENT;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM;
import static com.stpl.app.utils.Constants.LabelConstants.PROGRAM_CATEGORY;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Mohamed.hameed
 */
public class NMProjectionVarianceLogic {

    public static final Logger LOGGER = Logger.getLogger(NMProjectionVarianceLogic.class);
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
    private static final String PRC_PROJ_RESULTS_DISCOUNT = "PRC_PROJECTION_RESULTS_DISCOUNT";
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
                    selectionDTO.setProductHierarchyNo(selectionDTO.getProductHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
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
            selectionDTO.setLevelNo((selectionDTO.getFilterLevelNo()));
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
            if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) || parentDto.getGroup().contains(CommonUtils.VAR_DIS_RATE) || parentDto.getGroup().contains("RPU")) {
                pVSelectionDTO.setGroup(parentDto.getGroup());
                isDiscountExpand = true;
            }
            if (pVSelectionDTO.getGroupParent() == 0) {
                if (!isDiscountExpand) {
                    count += getCustPeriodVarianceCount(baseVariables, pVSelectionDTO);
                } else {
                    String ccpQuery = CommonLogic.getCCPQuery(pVSelectionDTO, Boolean.TRUE);
                    String query = StringUtils.EMPTY;
                    if (Constant.TOTAL.equals(pVSelectionDTO.getLevel())) {
                        List<Object> list = null;
                        if ("Program Category".equals(pVSelectionDTO.getDiscountLevel())) {
                            count += pVSelectionDTO.getDiscountList().get(1).size();
                            //PPA Count
                            query = getPPACount(pVSelectionDTO, Boolean.FALSE, Boolean.TRUE);
                            list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                        } else if ("Program".equals(pVSelectionDTO.getDiscountLevel())) {
                            count += pVSelectionDTO.getDiscountList().get(0).size();
                            //PPA Count
                            query = getPPACount(pVSelectionDTO, Boolean.TRUE, Boolean.TRUE);
                            list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                        }
                    } else {
                        if ("Program Category".equals(pVSelectionDTO.getDiscountLevel())) {
                            query = ccpQuery + getProgramCategoryForCurrentHierarchy(pVSelectionDTO);
                            List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                            System.out.println("query ccp = = = = " + query);
                            //PPA Count
                            query = ccpQuery + getPPACount(pVSelectionDTO, Boolean.FALSE, Boolean.FALSE);
                            list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                            System.out.println("query ppa= " + query);
                        } else if ("Program".equals(pVSelectionDTO.getDiscountLevel())) {
                            query = ccpQuery + getProgramCountForCurrentHierarchy(pVSelectionDTO);
                            List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                            if (list != null && !list.isEmpty()) {
                                Object ob = list.get(0);
                                count = count + Integer.valueOf(String.valueOf(ob));
                            }
                            //PPA Count
                            query = ccpQuery + getPPACount(pVSelectionDTO, Boolean.TRUE, Boolean.FALSE);
                            list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
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
                pVSelectionDTO.setLevelNo((pVSelectionDTO.getTreeLevelNo() + 1));
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
            if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) || parentDto.getGroup().contains(CommonUtils.VAR_DIS_RATE) || parentDto.getGroup().contains("RPU")) {
                pVSelectionDTO.setGroup(parentDto.getGroup());
                isDiscountExpand = true;
                if (parentDto.getGroup().contains("RPU")) {
                    pVSelectionDTO.setRPU(true);
                }
            }
            if (pVSelectionDTO.getGroupParent()==0) {
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
                        } else if (!pVSelectionDTO.getDiscountNameList().isEmpty() || getPPACountValue(pVSelectionDTO) > 0) {
                            getTotalDiscount(pVSelectionDTO);
                            if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT)) {
                                tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, Constant.DISCOUNT_SMALL, pivotDiscountList, pVSelectionDTO, isDetail, 3, Boolean.FALSE));
                            } else if (parentDto.getGroup().contains(CommonUtils.VAR_DIS_RATE)) {
                                tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, Constant.DISCOUNT_SMALL, pivotDiscountList, pVSelectionDTO, isDetail, 4, Boolean.TRUE));
                            } else {
                                tobeAddedList.addAll(commonCustomizationForTotalDiscount(parentDto, Constant.DISCOUNT_SMALL, pivotDiscountList, pVSelectionDTO, isDetail, 5, Boolean.FALSE));
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
                    && pVSelectionDTO.getGroupParent()==0) {
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
                pVSelectionDTO.setLevelNo((pVSelectionDTO.getTreeLevelNo() + 1));
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
            Object[] orderedArg = {projectionId, frequency, discountId, "VARIANCE", projSelDTO.getSessionId(), projSelDTO.getUserId(), Constant.STRING_ONE,projSelDTO.getDiscountLevel()};
            List<Object[]> discountsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS_DISCOUNT", orderedArg);
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

            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), resultStart, offset, projSelDTO.getHierarchyIndicator(), Integer.valueOf(projSelDTO.getTreeLevelNo()), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIslevelFiler(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true);

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
                List<ProjectionVarianceDTO> dto = getDetailsPivotVariance(pvsdto, parentDto);
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
            projDTOList.addAll(resultList);
        }
        return projDTOList;
    }

    public List<ProjectionVarianceDTO> getDetailsPivotVariance(final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto) {
        List<Object> currentPivotDiscount = new ArrayList<Object>();
        pvsdto.setYear("ALL");
        pvsdto.setProjectionId(selectionDTO.getCurrentProjId());
        String ccpQuery = CommonLogic.getCCPQuery(pvsdto,Boolean.TRUE) + "\n" + CommonLogic.getTempCCPQueryForCOGS(pvsdto) /*+ "\n" + CommonLogic.getTemp_CCPD_RetrunsQuery() + "\n" + CommonLogic.getTempRetrunsQuery();*/;
        String query = ccpQuery + "\n" + queryUtils.getProjectionResultsMainPivotQuery(pvsdto);
        List<Object> currentPivotDetails = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
            String discountQuery = ccpQuery +" \n" + CommonLogic.tempRSTablePivot(pvsdto) + "\n" + queryUtils.getPVMainDiscountPivotQuery(pvsdto)+ "\n" + CommonLogic.rsTempTableSelectPivot(pvsdto);
            currentPivotDiscount = (List<Object>) CommonLogic.executeSelectQuery(discountQuery, null, null);
        }
        List<Integer> priorProjIdList = new ArrayList<Integer>();
        for (Integer projId : pvsdto.getProjIdList()) {
            priorProjIdList.add(projId);
        }
        List<ProjectionVarianceDTO> projDTOList = getCustomizedPivotDetailResults(pivotTotalList, currentPivotDetails, currentPivotDiscount, pvsdto.getDiscountNameList(), parentDto, priorProjIdList, pvsdto, baseVariables);
        return projDTOList;
    }

    public void getTotalPivotVariance(PVSelectionDTO pvsdto) {
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
            String startDate = String.format("%04d", pvsdto.getStartYear()) + "-"
                + String.format("%02d", pvsdto.getStartMonth()) + "-"
                + String.format("%02d", pvsdto.getStartDay());
//        ,startDate
        Object[] orderedArg = {projectionId, frequency, discountId, "VARIANCE", pvsdto.getSessionId(), pvsdto.getUserId(), "PIVOT"};
        List< Object[]> gtsResult = CommonLogic.callProcedure(PRC_PROJ_RESULTS, orderedArg);
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
        String ccpQuery = CommonLogic.getCCPQuery(projSelDTO,Boolean.TRUE);
        if (!projSelDTO.isIsTotal()) {
//            query = ccpQuery + queryUtils.getProjectionResultsTotalDiscountPerQuery(projSelDTO);
//            list0 = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
//            projDTOList.add(list0);
//        } else {
//            query = ccpQuery + CommonLogic.getTempCCPQueryForCOGS(projSelDTO) + "\n" + CommonLogic.getTemp_CCPD_RetrunsQuery()
//                    + " \n" + CommonLogic.getTempRetrunsQuery() + " \n" + queryUtils.getPeriodDiscountExpand(projSelDTO);
            query = ccpQuery + CommonLogic.getTempCCPQueryForCOGS(projSelDTO) + " \n" + CommonLogic.tempRSTable(projSelDTO)
                    + " \n " + queryUtils.getPeriodDiscountExpand(projSelDTO) + "\n" + CommonLogic.rsTempTableSelect(projSelDTO);
            list0 = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
            projDTOList.add(list0);
        }
        LOGGER.info("Ending getDiscountExpandQuery");
        return projDTOList;
    }

    public List<ProjectionVarianceDTO> getCustomizedPivotTotalResults(final List<Object> results, List discountList, List<Integer> priorProjGtsList, PVSelectionDTO pvsdto, PVSelectionDTO baseVariables, List totalDiscount) {
        System.out.println("getCustomizedPivotTotalResults-------------------------------------");
        List<String> periodList = new ArrayList<String>(pvsdto.getPeriodList());
        Map<String, String> periodListMap = new HashMap<String, String>(pvsdto.getPeriodListMap());
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<ProjectionVarianceDTO>();
        String[] singleColumn = new String[3];
        int frequencyDivision = pvsdto.getFrequencyDivision();
        int discountIndex = 0;
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
                if (periodList.contains(pcommonColumn)) {
                    periodList.remove(pcommonColumn);
                    List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
                    columnList.remove(Constant.GROUP);
                    ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                    projDTO.setGroup(commonHeader);
                    if (baseVariables.isVarExFacSales()) {
                        if (baseVariables.isColValue()) {
                            column = "ExFacValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[2];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarDemandSales()) {
                        if (baseVariables.isColValue()) {
                            column = "DemandSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[3];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarInvSales()) {
                        if (baseVariables.isColValue()) {
                            column = "InvWithValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[4];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarPerExFacSales()) {
                        if (baseVariables.isColValue()) {
                            column = "PerExFacValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[5];
                            String baseValue = getFormattedValue(RATE_PER, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarPerDemandSales()) {
                        if (baseVariables.isColValue()) {
                            column = "PerDemandSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[6];
                            String baseValue = getFormattedValue(RATE_PER, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarPerInvSales()) {
                        if (baseVariables.isColValue()) {
                            column = "PerInvWithValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[7];
                            String baseValue = getFormattedValue(RATE_PER, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarContractsales()) {
                        if (baseVariables.isColValue()) {
                            column = "ContractSalesWACValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[8];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarContractUnits()) {
                        if (baseVariables.isColValue()) {
                            column = "ContractUnitsValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[9];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarDisRate()) {
                        if (baseVariables.isColValue()) {
                            column = "DiscountSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[11];
                            String baseValue = getFormattedValue(RATE, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarDisAmount()) {
                        if (baseVariables.isColValue()) {
                            column = "DiscountAmountValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[10];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarRPU()) {
                        if (baseVariables.isColValue()) {
                            column = "RPUValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[16];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarNetSales()) {
                        if (baseVariables.isColValue()) {
                            column = "NetSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[17];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarCOGC()) {
                        if (baseVariables.isColValue()) {
                            column = "COGCValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[18];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarNetProfit()) {
                        if (baseVariables.isColValue()) {
                            column = "NetProfitValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[19];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    for (int j = 0; j < priorProjGtsList.size(); j++) {
                        //ExFacSales
                        if (baseVariables.isVarExFacSales()) {
                            String value1 = StringUtils.EMPTY + row[2 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "ExFacValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[2 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[2])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "ExFacPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[2 + ((j + 1) * 21)];
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
                        //Demand
                        if (baseVariables.isVarDemandSales()) {
                            String value1 = StringUtils.EMPTY + row[3 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "DemandSalesValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DemandSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[3 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[3])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "DemandSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[3 + ((j + 1) * 21)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Inventory
                        if (baseVariables.isVarInvSales()) {
                            String value1 = StringUtils.EMPTY + row[4 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "InvWithValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "InvWithVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[4 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[4])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "InvWithPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[4 + ((j + 1) * 21)];
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
                        //Percentage of Ex Fac
                        if (baseVariables.isVarPerExFacSales()) {
                            String value1 = StringUtils.EMPTY + row[5 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "PerExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[5 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[5])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE_PER, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "PerExFacPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[5 + ((j + 1) * 21)];
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
                        } //Percentage of Demand
                        if (baseVariables.isVarPerDemandSales()) {
                            String value1 = StringUtils.EMPTY + row[6 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "PerDemandSalesValue" + priorProjGtsList.get(j);

                                String baseValue = getFormattedValue(RATE_PER, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerDemandSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[6 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[6])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE_PER, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "PerDemandSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[6 + ((j + 1) * 21)];
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
                        //Percentage of Inv
                        if (baseVariables.isVarPerInvSales()) {
                            String value1 = StringUtils.EMPTY + row[7 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "PerInvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE_PER, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerInvWithVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[7 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[7])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE_PER, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "PerInvWithPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[7 + ((j + 1) * 21)];
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
                        //Contract Sales
                        if (baseVariables.isVarContractsales()) {
                            String value1 = StringUtils.EMPTY + row[8 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "ContractSalesWACValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ContractSalesWACVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[8 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[8])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                commonDoubleColumn = "ContractSalesWACVarPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[8 + ((j + 1) * 21)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[8])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Contract Units
                        if (baseVariables.isVarContractUnits()) {
                            String value1 = StringUtils.EMPTY + row[9 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "ContractUnitsValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ContractUnitsVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[9 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[9])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "ContractUnitsPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[9 + ((j + 1) * 21)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[9])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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

                        //Discount Percentage
                        if (baseVariables.isVarDisRate()) {
                            String value1 = StringUtils.EMPTY + row[11 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "DiscountSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountSalesVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[11 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[11])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "DiscountSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[11 + ((j + 1) * 21)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[11])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //Discount Dollar
                        if (baseVariables.isVarDisAmount()) {
                            String value1 = StringUtils.EMPTY + row[10 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "DiscountAmountValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountAmountVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[10 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[10])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "DiscountAmountPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[10 + ((j + 1) * 21)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[10])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                        //RPU
                        if (baseVariables.isVarRPU()) {
                            String value1 = StringUtils.EMPTY + row[14 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "RPUValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "RPUVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[14 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[14])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "RPUPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[14 + ((j + 1) * 21)];
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
                        //Netsales
                        if (baseVariables.isVarNetSales()) {
                            String value1 = StringUtils.EMPTY + row[17 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "NetSalesValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[17 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[17])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "NetSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[17 + ((j + 1) * 21)];
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
                        //COGC
                        if (baseVariables.isVarCOGC()) {
                            String value1 = StringUtils.EMPTY + row[18 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "COGCValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "COGCVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[18 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[18])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "COGCPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[18 + ((j + 1) * 21)];
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
                        //Net Profite
                        if (baseVariables.isVarNetProfit()) {
                            String value1 = StringUtils.EMPTY + row[19 + ((j + 1) * 21)];
                            if (baseVariables.isColValue()) {
                                column = "NetProfitValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetProfitVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[19 + ((j + 1) * 21)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[19])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "NetProfitPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[19 + ((j + 1) * 21)];
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
                    if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {

                        // Individual discount level
                        Set<String> noOfDiscount = new HashSet<String>();
                        for (Object discountsName : totalDiscount) {
                            final Object[] disc = (Object[]) discountsName;
                            if (disc[2] != null) {
                                noOfDiscount.add(String.valueOf(disc[2]));
                            }
                        }
                        for (int dis = 0; dis < totalDiscount.size(); dis++) {
                            discountIndex = dis;
                            Object[] discountRow = (Object[]) totalDiscount.get(dis);
                            int dyear = Integer.valueOf(String.valueOf(discountRow[0]));
                            int dperiod = Integer.valueOf(String.valueOf(discountRow[1]));
                            List<String> dcommon = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, dyear, dperiod);
                            String dcommonColumn = dcommon.get(0);
                            if (pcommonColumn.equals(dcommonColumn)) {
                                for (int disc = 0; disc < noOfDiscount.size(); disc++) {
                                    String head = String.valueOf(discountRow[2]).replace(" ", StringUtils.EMPTY) + disc;
                                    if (baseVariables.isVarDisAmount()) {
                                        if (baseVariables.isColValue()) {
                                            column = "DiscountAmountValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[3];
                                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    if (baseVariables.isVarDisRate()) {
                                        if (baseVariables.isColValue()) {
                                            column = "DiscountSalesValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[4];
                                            String baseValue = getFormattedValue(RATE, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    if (baseVariables.isVarRPU()) {
                                        if (baseVariables.isColValue()) {
                                            column = "RPUValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[5];
                                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }

                                    for (int k = 0; k < priorProjGtsList.size(); k++) {
                                        //Discount Percentage
                                        if (baseVariables.isVarDisRate()) {
                                            singleColumn[0] = "DiscountSalesValue" + head + priorProjGtsList.get(k);
                                            singleColumn[1] = "DiscountSalesVar" + head + priorProjGtsList.get(k);
                                            singleColumn[2] = "DiscountSalesPer" + head + priorProjGtsList.get(k);
                                            getPivotCommonCustomization(pvsdto, discountRow, projDTO, singleColumn, 4, k, 3, columnList, Boolean.TRUE);
                                        }

                                        //Discount Dollor
                                        if (baseVariables.isVarDisAmount()) {
                                            singleColumn[0] = "DiscountAmountValue" + head + priorProjGtsList.get(k);
                                            singleColumn[1] = "DiscountAmountVar" + head + priorProjGtsList.get(k);
                                            singleColumn[2] = "DiscountAmountPer" + head + priorProjGtsList.get(k);
                                            getPivotCommonCustomization(pvsdto, discountRow, projDTO, singleColumn, 3, k, 3, columnList, Boolean.FALSE);
                                        }
                                        //RPU
                                        if (baseVariables.isVarRPU()) {
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
                        if (baseVariables.isColValue()) {
                            column = "DiscountAmountValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[12];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // PPA Total Discount %
                        if (baseVariables.isColValue()) {
                            column = "DiscountSalesValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[13];
                            String baseValue = getFormattedValue(RATE, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                        // PPA Total Discount RPU
                        if (baseVariables.isColValue()) {
                            column = "RPUValuePPA" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[15];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Returns Total Discount $
                        if (baseVariables.isColValue()) {
                            column = "DiscountAmountValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[21];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                        // Returns Total Discount %
                        if (baseVariables.isColValue()) {
                            column = "DiscountSalesValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[22];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                        // Returns Total Discount RPU
                        if (baseVariables.isColValue()) {
                            column = "RPUValueReturns" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[20];
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
                                singleColumn[2] = "DiscountSalesPerPPA" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 13, j, 21, columnList, Boolean.TRUE);
                                //Returns
                                singleColumn[0] = "DiscountSalesValueReturns" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountSalesVarReturns" + priorProjGtsList.get(j);
                                singleColumn[2] = "DiscountSalesPerReturns" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 22, j, 21, columnList, Boolean.TRUE);
                            }
                            //Discount Dollar
                            if (baseVariables.isVarDisAmount()) {
                                //PPA
                                singleColumn[0] = "DiscountAmountValuePPA" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountAmountVarPPA" + priorProjGtsList.get(j);
                                singleColumn[2] = "DiscountAmountPerPPA" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 12, j, 21, columnList, Boolean.FALSE);
                                //Returns
                                singleColumn[0] = "DiscountAmountValueReturns" + priorProjGtsList.get(j);
                                singleColumn[1] = "DiscountAmountVarReturns" + priorProjGtsList.get(j);
                                singleColumn[2] = "DiscountAmountPerReturns" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 21, j, 21, columnList, Boolean.FALSE);
                            }
                            //RPU
                            if (baseVariables.isVarRPU()) {
                                //PPA
                                singleColumn[0] = "RPUValuePPA" + priorProjGtsList.get(j);
                                singleColumn[1] = "RPUVariancePPA" + priorProjGtsList.get(j);
                                singleColumn[2] = "RPUPerPPA" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 15, j, 21, columnList, Boolean.FALSE);
                                //Returns
                                singleColumn[0] = "RPUValueReturns" + priorProjGtsList.get(j);
                                singleColumn[1] = "RPUVarianceReturns" + priorProjGtsList.get(j);
                                singleColumn[2] = "RPUPerReturns" + priorProjGtsList.get(j);
                                getPivotCommonCustomization(pvsdto, row, projDTO, singleColumn, 20, j, 21, columnList, Boolean.FALSE);
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
        LOGGER.info("Inside getPivotCommonCustomization");
        String visibleColumn = StringUtils.EMPTY;
        String value1 = StringUtils.EMPTY + row[index + ((priorIndex + 1) * column)];
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
            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[index])) - Double.valueOf(isNull(priorVal)));
            String baseValue = getFormattedValue(AMOUNT, variance);
            if (pvsdto.hasColumn(visibleColumn)) {
                projDTO.addStringProperties(visibleColumn, isPer ? baseValue + PERCENT : baseValue);
                columnList.remove(visibleColumn);
            }

        }
        if (baseVariables.isColPercentage()) {
            visibleColumn = columns[2];
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
        LOGGER.info("Ending getPivotCommonCustomization");
    }

    public List<ProjectionVarianceDTO> getCustomizedPivotDetailResults(final List gtsList, final List results, List discountList, List<String> discountName, ProjectionVarianceDTO parentDto, List<Integer> priorProjGtsList, PVSelectionDTO pvsdto, PVSelectionDTO baseVariables) {
        List<String> periodList = new ArrayList<String>(pvsdto.getPeriodList());
        
        List<String> discountNames = new ArrayList<String>(pvsdto.getDiscountNameList());
        List list = CommonLogic.getPPADiscountNameList(pvsdto);
        if(list != null){
        discountNames.addAll(list);
        }
        Map<String, String> discountMap = new HashMap<>();
        for (int j = 0; j < discountNames.size(); j++) {
            discountMap.put(String.valueOf(discountNames.get(j)), String.valueOf(j));
        }
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
                String column = StringUtils.EMPTY;
                if (gtsList.size() >= results.size() && gtsRow.length >= row.length) {
                    isGts = true;
                }
                if (periodList.contains(pcommonColumn)) {
                    periodList.remove(pcommonColumn);
                    List<String> columnList = new ArrayList<String>(pvsdto.getColumns());
                    columnList.remove(Constant.GROUP);
                    ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                    projDTO.setGroup(commonHeader);
                    if (baseVariables.isVarExFacSales()) {
                        if (baseVariables.isColValue()) {
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
                    if (baseVariables.isVarDemandSales()) {
                        if (baseVariables.isColValue()) {
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
                    if (baseVariables.isVarInvSales()) {
                        if (baseVariables.isColValue()) {
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
                    if (baseVariables.isVarPerExFacSales()) {
                        if (baseVariables.isColValue()) {
                            column = "PerExFacValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentPob = NULL.getConstant();
                            if (isGts) {
                                currentPob = String.valueOf(gtsRow[5]);
                            }
                            String baseValue = getFormattedValue(RATE, currentPob);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarPerDemandSales()) {
                        if (baseVariables.isColValue()) {
                            column = "PerDemandSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentPob = NULL.getConstant();
                            if (isGts) {
                                currentPob = String.valueOf(gtsRow[6]);
                            }
                            String baseValue = getFormattedValue(RATE, currentPob);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue  + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarPerInvSales()) {
                        if (baseVariables.isColValue()) {
                            column = "PerInvWithValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentPob = NULL.getConstant();
                            if (isGts) {
                                currentPob = String.valueOf(gtsRow[7]);
                            }
                            String baseValue = getFormattedValue(RATE, currentPob);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue  + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarContractsales()) {
                        if (baseVariables.isColValue()) {
                            column = "ContractSalesWACValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[2];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarContractUnits()) {
                        if (baseVariables.isColValue()) {
                            column = "ContractUnitsValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[3];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarDisRate()) {
                        if (baseVariables.isColValue()) {
                            column = "DiscountSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[4];
                            String baseValue = getFormattedValue(RATE, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarDisAmount()) {
                        if (baseVariables.isColValue()) {
                            column = "DiscountAmountValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[5];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarRPU()) {
                        if (baseVariables.isColValue()) {
                            column = "RPUValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[7];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarNetSales()) {
                        if (baseVariables.isColValue()) {
                            column = "NetSalesValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[6];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarCOGC()) {
                        if (baseVariables.isColValue()) {
                            column = "COGCValue" + CURRENT + pvsdto.getCurrentProjId();
                            String currentSales = StringUtils.EMPTY + row[8];
                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                            if (pvsdto.hasColumn(column)) {
                                projDTO.addStringProperties(column, baseValue);
                                columnList.remove(column);
                            }
                        }
                    }
                    if (baseVariables.isVarNetProfit()) {
                        if (baseVariables.isColValue()) {
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
                        if (baseVariables.isVarExFacSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[2 + ((j + 1) * 21)]);
                            }
                            if (baseVariables.isColValue()) {
                                column = "ExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
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
                            if (baseVariables.isColPercentage()) {
                                
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
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        } //Demand Sales
                        if (baseVariables.isVarDemandSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[3 + ((j + 1) * 21)]);
                            }
                            if (baseVariables.isColValue()) {
                                column = "DemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
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
                            if (baseVariables.isColPercentage()) {
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
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        } //Inv Sales
                        if (baseVariables.isVarInvSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[4 + ((j + 1) * 21)]);
                            }
                            if (baseVariables.isColValue()) {
                                column = "InvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
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
                            if (baseVariables.isColPercentage()) {
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
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Percentage of Ex Factory Sales
                        if (baseVariables.isVarPerExFacSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[5 + ((j + 1) * 21)]);
                            }
                            if (baseVariables.isColValue()) {
                                column = "PerExFacValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerExFacVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[5 + ((j + 1) * 21)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[5])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
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
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Percentage of Ex Demad Sales
                        if (baseVariables.isVarPerDemandSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[6 + ((j + 1) * 21)]);
                            }
                            if (baseVariables.isColValue()) {
                                column = "PerDemandSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerDemandSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[6 + ((j + 1) * 21)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[6])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
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
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Percentage of Inv
                        if (baseVariables.isVarPerInvSales()) {
                            String value1 = NULL.getConstant();
                            if (isGts) {
                                value1 = String.valueOf(gtsRow[7 + ((j + 1) * 21)]);
                            }
                            if (baseVariables.isColValue()) {
                                column = "PerInvWithValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "PerInvWithVariance" + priorProjGtsList.get(j);
                                String priorVal = NULL.getConstant();
                                if (isGts) {
                                    priorVal = String.valueOf(gtsRow[7 + ((j + 1) * 21)]);
                                }
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + gtsRow[7])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
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
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }
                        //Contract Sales
                        if (baseVariables.isVarContractsales()) {
                            String priorValue = StringUtils.EMPTY + row[2 + ((j + 1) * 8)];
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
                                String priorVal = StringUtils.EMPTY + row[2 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[2])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "ContractSalesWACVarPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[2 + ((j + 1) * 8)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                                if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                    perChange = 0.0;
                                }
                                String change = String.valueOf(perChange);
                                String baseValue = getFormattedValue(RATE, change);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue+ PERCENT);
                                    columnList.remove(column);
                                }
                            }
                        }

                        //Contract Units
                        if (baseVariables.isVarContractUnits()) {
                            String value1 = StringUtils.EMPTY + row[3 + ((j + 1) * 8)];
                            if (baseVariables.isColValue()) {
                                column = "ContractUnitsValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "ContractUnitsVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[3 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[3])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "ContractUnitsPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[3 + ((j + 1) * 8)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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

                        //Discount Dollor
                        if (baseVariables.isVarDisRate()) {
                            String value1 = StringUtils.EMPTY + row[4 + ((j + 1) * 8)];
                            if (baseVariables.isColValue()) {
                                column = "DiscountSalesValue" + priorProjGtsList.get(j);
                                String baseValue = getFormattedValue(RATE, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountSalesVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[4 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[4])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(RATE, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
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
                        if (baseVariables.isVarDisAmount()) {
                            String value1 = StringUtils.EMPTY + row[5 + ((j + 1) * 8)];
                            if (baseVariables.isColValue()) {
                                column = "DiscountAmountValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "DiscountAmountVar" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[5 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[5])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
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
                        if (baseVariables.isVarRPU()) {
                            String value1 = StringUtils.EMPTY + row[7 + ((j + 1) * 8)];
                            if (baseVariables.isColValue()) {
                                column = "RPUValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "RPUVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[7 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[7])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "RPUPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[7 + ((j + 1) * 8)];
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
                        //Netsales
                        if (baseVariables.isVarNetSales()) {
                            String value1 = StringUtils.EMPTY + row[6 + ((j + 1) * 8)];
                            if (baseVariables.isColValue()) {
                                column = "NetSalesValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetSalesVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[6 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[6])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "NetSalesPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[6 + ((j + 1) * 8)];
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
                        } //COGC
                        if (baseVariables.isVarCOGC()) {
                            String value1 = StringUtils.EMPTY + row[8 + ((j + 1) * 8)];
                            if (baseVariables.isColValue()) {
                                column = "COGCValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "COGCVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[8 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[8])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "COGCPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[8 + ((j + 1) * 8)];
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
                        } //Net Profit
                        if (baseVariables.isVarNetProfit()) {
                            String value1 = StringUtils.EMPTY + row[9 + ((j + 1) * 8)];
                            if (baseVariables.isColValue()) {
                                column = "NetProfitValue" + priorProjGtsList.get(j);;
                                String baseValue = getFormattedValue(AMOUNT, value1);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }
                            }
                            if (baseVariables.isColVariance()) {
                                column = "NetProfitVariance" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[9 + ((j + 1) * 8)];
                                String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + row[9])) - Double.valueOf(isNull(priorVal)));
                                String baseValue = getFormattedValue(AMOUNT, variance);
                                if (pvsdto.hasColumn(column)) {
                                    projDTO.addStringProperties(column, baseValue);
                                    columnList.remove(column);
                                }

                            }
                            if (baseVariables.isColPercentage()) {
                                column = "NetProfitPer" + priorProjGtsList.get(j);
                                String priorVal = StringUtils.EMPTY + row[9 + ((j + 1) * 8)];
                                Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + row[9])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                                    String head = String.valueOf(discountRow[2]).replace(" ", StringUtils.EMPTY) + discountMap.get(discountRow[2]);
                                    if (baseVariables.isVarDisAmount()) {
                                        if (baseVariables.isColValue()) {
                                            column = "DiscountAmountValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[3];
                                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    if (baseVariables.isVarDisRate()) {
                                        if (baseVariables.isColValue()) {
                                            column = "DiscountSalesValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[6];
                                            String baseValue = getFormattedValue(RATE, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue + PERCENT);
                                                columnList.remove(column);
                                            }
                                        }
                                    }
                                    if (baseVariables.isVarRPU()) {
                                        if (baseVariables.isColValue()) {
                                            column = "RPUValue" + head + CURRENT + pvsdto.getCurrentProjId();
                                            String currentSales = StringUtils.EMPTY + discountRow[9];
                                            String baseValue = getFormattedValue(AMOUNT, currentSales);
                                            if (pvsdto.hasColumn(column)) {
                                                projDTO.addStringProperties(column, baseValue);
                                                columnList.remove(column);
                                            }
                                        }
                                    }

                                    for (int k = 0; k < priorProjGtsList.size(); k++) {
                                        //Discount Percentage
                                        if (baseVariables.isVarDisRate()) {
                                            String value1 = StringUtils.EMPTY + discountRow[6 + ((k + 1) * 9)];
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
                                                String priorVal = StringUtils.EMPTY + discountRow[7 + ((k + 1) * 9)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (baseVariables.isColPercentage()) {
                                                column = "DiscountSalesPer" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[8 + ((k + 1) * 9)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }

                                        //Discount Dollor
                                        if (baseVariables.isVarDisAmount()) {
                                            String value1 = StringUtils.EMPTY + discountRow[3 + ((k + 1) * 9)];
                                            if (baseVariables.isColValue()) {
                                                column = "DiscountAmountValue" + head + priorProjGtsList.get(k);;
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (baseVariables.isColVariance()) {
                                                column = "DiscountAmountVar" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[4 + ((k + 1) * 9)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (baseVariables.isColPercentage()) {
                                                column = "DiscountAmountPer" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[5 + ((k + 1) * 9)];
                                                String baseValue = getFormattedValue(RATE, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue + PERCENT);
                                                    columnList.remove(column);
                                                }
                                            }
                                        }
                                        //RPU
                                        if (baseVariables.isVarRPU()) {
                                            String value1 = StringUtils.EMPTY + discountRow[9 + ((k + 1) * 9)];
                                            if (baseVariables.isColValue()) {
                                                column = "RPUValue" + head + priorProjGtsList.get(k);;
                                                String baseValue = getFormattedValue(AMOUNT, value1);
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }
                                            }
                                            if (baseVariables.isColVariance()) {
                                                column = "RPUVariance" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[10 + ((k + 1) * 9)];
                                                String baseValue = getFormattedValue(AMOUNT, isNull(priorVal));
                                                if (pvsdto.hasColumn(column)) {
                                                    projDTO.addStringProperties(column, baseValue);
                                                    columnList.remove(column);
                                                }

                                            }
                                            if (baseVariables.isColPercentage()) {
                                                column = "RPUPer" + head + priorProjGtsList.get(k);
                                                String priorVal = StringUtils.EMPTY + discountRow[11 + ((k + 1) * 9)];
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
        String query = CommonLogic.getCCPQuery(pvsdto,Boolean.TRUE) + CommonLogic.getTempCCPQueryForCOGS(pvsdto) + "\n" + queryUtils.getProjectionResultsMainPivotQuery(pvsdto);
        List<Object> currentPivotDetails = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        pvsdto.setProjectionId(pvsdto.getCurrentProjId());
        return currentPivotDetails;
    }

    /**
     * get Customized period variance
     *
     * @return List
     */
    public List<ProjectionVarianceDTO> getCustPeriodVariance(final List<Object> gtsList, final List<Object> dataList, final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto, final PVSelectionDTO baseVariables) {
        try{
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
                pvsdto.setVarIndicator(VALUE.getConstant());
                exFacValue = getExFactorySales(gtsList, pvsdto);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                exFacVar = getExFactorySales(gtsList, pvsdto);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                exFacPer = getExFactorySales(gtsList, pvsdto);
            }
            //Ex fac for detail end

            //Demand sale for detail - start
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                demandValue = getDemandSales(gtsList, pvsdto);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                demandVar = getDemandSales(gtsList, pvsdto);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                demandPer = getDemandSales(gtsList, pvsdto);
            }
            //Demand sale for detail - start

            //Inv with drawal sale for detail - start
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                invWithValue = getInvWithdrawalSales(gtsList, pvsdto);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                invWithVar = getInvWithdrawalSales(gtsList, pvsdto);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                invWithPer = getInvWithdrawalSales(gtsList, pvsdto);
            }
            //Inv with drawal sale for detail - start

            //Sales for POB
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                salesValue = getContractSales(gtsList, dataList, pvsdto);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                salesVar = getContractSales(gtsList, dataList, pvsdto);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                salesPer = getContractSales(gtsList, dataList, pvsdto);
            }
        }
        // ExFac Sales
        if (baseVariables.isVarExFacSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getExFactorySales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacValue);
                }
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getExFactorySales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacVar);
                }
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getExFactorySales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(exFacPer);
                }
            }
        }
        // Demand Sales
        if (baseVariables.isVarDemandSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getDemandSales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandValue);
                }
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getDemandSales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandVar);
                }
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getDemandSales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(demandPer);
                }
            }
        }
        // Inventory Sales Withdrawal
        if (baseVariables.isVarInvSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getInvWithdrawalSales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithValue);
                }
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getInvWithdrawalSales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithVar);
                }
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO salesData = getInvWithdrawalSales(gtsList, pvsdto);
                    projectionVarianceDTO.add(salesData);
                } else {
                    projectionVarianceDTO.add(invWithPer);
                }
            }
        }
        //% Of Ex Factory
        if (baseVariables.isVarPerExFacSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getPERExFactory(gtsList, exFacValue, salesValue, pvsdto);
                projectionVarianceDTO.add(pob);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getPERExFactory(gtsList, exFacVar, salesVar, pvsdto);
                projectionVarianceDTO.add(pob);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getPERExFactory(gtsList, exFacPer, salesPer, pvsdto);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Demand Sales
        if (baseVariables.isVarPerDemandSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getPERDemand(gtsList, demandValue, salesValue, pvsdto);
                projectionVarianceDTO.add(pob);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getPERDemand(gtsList, demandVar, salesVar, pvsdto);
                projectionVarianceDTO.add(pob);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getPERDemand(gtsList, demandPer, salesPer, pvsdto);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Inv Sales
        if (baseVariables.isVarPerInvSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO pob = getPERInvWithdrawal(gtsList, invWithValue, salesValue, pvsdto);
                projectionVarianceDTO.add(pob);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO pob = getPERInvWithdrawal(gtsList, invWithVar, salesVar, pvsdto);
                projectionVarianceDTO.add(pob);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO pob = getPERInvWithdrawal(gtsList, invWithPer, salesPer, pvsdto);
                projectionVarianceDTO.add(pob);
            }
        }
        //Contract Sales
        if (baseVariables.isVarContractsales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getContractSales(gtsList, dataList, pvsdto);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesValue);
                }
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getContractSales(gtsList, dataList, pvsdto);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesVar);
                }
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                if (!isDetail) {
                    ProjectionVarianceDTO sales = getContractSales(gtsList, dataList, pvsdto);
                    projectionVarianceDTO.add(sales);
                } else {
                    projectionVarianceDTO.add(salesPer);
                }
            }
        }
        //Contract Units
        if (baseVariables.isVarContractUnits()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO units = getContractUnits(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(units);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO units = getContractUnits(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(units);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO units = getContractUnits(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(units);
            }
        }
        //Discount $ 
        if (baseVariables.isVarDisAmount()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO discountDol = getDiscountDol(gtsList, dataList, pvsdto);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO discountDol = getDiscountDol(gtsList, dataList, pvsdto);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO discountDol = getDiscountDol(gtsList, dataList, pvsdto);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
        }
        //Discount % 
        if (baseVariables.isVarDisRate()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO discountPer = getDiscountPer(gtsList, dataList, pvsdto);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO discountPer = getDiscountPer(gtsList, dataList, pvsdto);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO discountPer = getDiscountPer(gtsList, dataList, pvsdto);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
        }
        // RPU
        if (baseVariables.isVarRPU()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getRPU(gtsList, dataList, pvsdto);
                netSalesValue = setDataObjects(netSalesValue, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getRPU(gtsList, dataList, pvsdto);
                netSalesVar = setDataObjects(netSalesVar, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getRPU(gtsList, dataList, pvsdto);
                netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //NetSales 
        if (baseVariables.isVarNetSales()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getNetSales(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getNetSales(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getNetSales(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        if (baseVariables.isVarCOGC()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getCOGC(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getCOGC(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getCOGC(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        if (baseVariables.isVarNetProfit()) {
            if (baseVariables.isColValue()) {
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO netSalesValue = getNetProfit(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (baseVariables.isColVariance()) {
                pvsdto.setVarIndicator(VARIANCE.getConstant());
                ProjectionVarianceDTO netSalesVar = getNetProfit(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (baseVariables.isColPercentage()) {
                pvsdto.setVarIndicator(CHANGE.getConstant());
                ProjectionVarianceDTO netSalesPer = getNetProfit(gtsList, dataList, pvsdto);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        return projectionVarianceDTO;
        }catch(Exception e){
            e.printStackTrace();
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[3 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[3 + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[3 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[3 + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[4 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[4 + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[4 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[4 + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
        return pvDTO;
    }

    /**
     * Per
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getPERExFactory(final List<Object> dataList, ProjectionVarianceDTO gtsDto, ProjectionVarianceDTO salesDto, PVSelectionDTO pvsdto) {
        int frequencyDivision = pvsdto.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.PER_EX_FACTORY.toString().concat(Constant.VALUE));
        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
            pvDTO.setGroup(Constant.PVVariables.PER_EX_FACTORY.toString().concat(Constant.VARIANCE));
        } else {
            pvDTO.setGroup(Constant.PVVariables.PER_EX_FACTORY.toString().concat(Constant.CHANGE));
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
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = StringUtils.EMPTY + obj[5];
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[5 + ((j + 1) * 21)];
                            String val = getFormattedValue(RATE, isNull(priorVal));
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[5 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[5 + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
            columnList.remove(Constant.GROUP);
            for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
                pvDTO.addStringProperties(nullObj, DASH);
            }
            for (Object object : columnList) {
                String gts = StringUtils.EMPTY + String.valueOf(gtsDto.getPropertyValue(object)).replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", ZERO);
                String sales = StringUtils.EMPTY + String.valueOf(salesDto.getPropertyValue(object)).replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", ZERO);
                Double pob = (Double.valueOf(isNull(sales)) / Double.valueOf(isNull(gts)))*100;
                if (pob.isInfinite() || pob.isNaN()) {
                    pob = 0.0;
                }
                if (!String.valueOf(object).contains("Current")) {
                if (pvDTO.getGroup().equals(Constant.PVVariables.PER_EX_FACTORY.toString().concat(Constant.CHANGE))) {
                    String POB = getFormattedValue(RATE_PER, String.valueOf(pob));
                    pvDTO.addStringProperties(object, POB + PERCENT);
                } else {
                    String POB = getFormattedValue(RATE, String.valueOf(pob));
                    pvDTO.addStringProperties(object, POB + PERCENT);
                }
                } else {
                    if (pvDTO.getGroup().equals(Constant.PVVariables.PER_EX_FACTORY.toString().concat(Constant.VALUE))) {
                        String POB = getFormattedValue(RATE, String.valueOf(pob));
                        pvDTO.addStringProperties(object, POB + PERCENT);
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = StringUtils.EMPTY + obj[6];
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[6 + ((j + 1) * 21)];
                            String val = getFormattedValue(RATE, isNull(priorVal));
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[6 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[6 + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
            columnList.remove(Constant.GROUP);
            for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
                pvDTO.addStringProperties(nullObj, DASH);
            }
            for (Object object : columnList) {
                String gts = StringUtils.EMPTY + String.valueOf(gtsDto.getPropertyValue(object)).replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", ZERO);
                String sales = StringUtils.EMPTY + String.valueOf(salesDto.getPropertyValue(object)).replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", ZERO);
                Double pob = (Double.valueOf(isNull(sales)) / Double.valueOf(isNull(gts)))*100;
                if (pob.isInfinite() || pob.isNaN()) {
                    pob = 0.0;
                }
                if (!String.valueOf(object).contains("Current")) {
                if (pvDTO.getGroup().equals(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.CHANGE))) {
                    String POB = getFormattedValue(RATE_PER, String.valueOf(pob));
                    pvDTO.addStringProperties(object, POB + PERCENT);
                } else {
                    String POB = getFormattedValue(RATE, String.valueOf(pob));
                    pvDTO.addStringProperties(object, POB + PERCENT);
                }
                } else {
                    if (pvDTO.getGroup().equals(Constant.PVVariables.PER_DEMAND.toString().concat(Constant.VALUE))) {
                        String POB = getFormattedValue(RATE, String.valueOf(pob));
                        pvDTO.addStringProperties(object, POB + PERCENT);
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = StringUtils.EMPTY + obj[7];
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[7 + ((j + 1) * 21)];
                            String val = getFormattedValue(RATE, isNull(priorVal));
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[7 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[7 + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[7])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
            columnList.remove(Constant.GROUP);
            for (Object nullObj : pvsdto.getRightHeader().getSingleColumns()) {
                pvDTO.addStringProperties(nullObj, DASH);
            }
            for (Object object : columnList) {
                String gts = StringUtils.EMPTY + String.valueOf(gtsDto.getPropertyValue(object)).replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", ZERO);
                String sales = StringUtils.EMPTY + String.valueOf(salesDto.getPropertyValue(object)).replace("$", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("-", ZERO);
                Double pob = (Double.valueOf(isNull(sales)) / Double.valueOf(isNull(gts)))*100;
                if (pob.isInfinite() || pob.isNaN()) {
                    pob = 0.0;
                }
                if (!String.valueOf(object).contains("Current")) {
                if (pvDTO.getGroup().equals(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.CHANGE))) {
                    String POB = getFormattedValue(RATE_PER, String.valueOf(pob));
                    pvDTO.addStringProperties(object, POB + PERCENT);
                } else {
                    String POB = getFormattedValue(RATE, String.valueOf(pob));
                    pvDTO.addStringProperties(object, POB + PERCENT);
                }
                } else {
                    if (pvDTO.getGroup().equals(Constant.PVVariables.PER_INVENORY_WITHDRAW.toString().concat(Constant.VALUE))) {
                        String POB = getFormattedValue(RATE, String.valueOf(pob));
                        pvDTO.addStringProperties(object, POB + PERCENT);
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[8 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = obj[8 + ((j + 1) * 21)] != null ? StringUtils.EMPTY + obj[8 + ((j + 1) * 21)] : ZERO;
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[8])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2 + ((j + 1) * 8)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[2 + ((j + 1) * 8)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = obj[2 + ((j + 1) * 8)] != null ? StringUtils.EMPTY + obj[2 + ((j + 1) * 8)] : ZERO;
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[2])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])));
                        String baseValue = getFormattedValue(AMOUNT_UNITS, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT_UNITS, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[9 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT_UNITS, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = obj[9 + ((j + 1) * 18)] != null ? StringUtils.EMPTY + obj[9 + ((j + 1) * 21)] : ZERO;
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[9])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3])));
                    String baseValue = getFormattedValue(AMOUNT_UNITS, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3 + ((j + 1) * 8)])));
                        String val = getFormattedValue(AMOUNT_UNITS, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[3 + ((j + 1) * 8)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[3])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT_UNITS, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = obj[3 + ((j + 1) * 8)] != null ? StringUtils.EMPTY + obj[3 + ((j + 1) * 8)] : ZERO;
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[3])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[10])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[10 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[10 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[10])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = obj[10 + ((j + 1) * 21)] != null ? StringUtils.EMPTY + obj[10 + ((j + 1) * 21)] : ZERO;
                            priorVal = RATE.format(Double.valueOf(priorVal));
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[10])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
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
                if (frequencyDivision == 4) {
                    commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5 + ((j + 1) * 8)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[5 + ((j + 1) * 8)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = obj[5 + ((j + 1) * 8)] != null ? StringUtils.EMPTY + obj[5 + ((j + 1) * 8)] : ZERO;
                        priorVal = RATE.format(Double.valueOf(priorVal));
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[5])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[11])));
                        String baseValue = getFormattedValue(RATE, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[11 + ((j + 1) * 21)])));
                            String val = getFormattedValue(RATE, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[11 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[11])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(RATE, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                        } else {
                            String priorVal = obj[11 + ((j + 1) * 21)] != null ? StringUtils.EMPTY + obj[11 + ((j + 1) * 21)] : ZERO;
                            priorVal = RATE.format(Double.valueOf(priorVal));
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[11])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
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
                if (frequencyDivision == 4) {
                    commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])));
                    String baseValue = getFormattedValue(RATE, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue + PERCENT);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4 + ((j + 1) * 8)])));
                        String val = getFormattedValue(RATE, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[4 + ((j + 1) * 8)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(RATE, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val + PERCENT);

                    } else {
                        String priorVal = obj[4 + ((j + 1) * 8)] != null ? StringUtils.EMPTY + obj[4 + ((j + 1) * 8)] : ZERO;
                        priorVal = RATE.format(Double.valueOf(priorVal));
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[4])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[16])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[16 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[16 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[16])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = obj[16 + ((j + 1) * 21)] != null ? StringUtils.EMPTY + obj[16 + ((j + 1) * 21)] : ZERO;
                            priorVal = RATE.format(Double.valueOf(priorVal));
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[16])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE_PER_THREE, change);
                          
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu+PERCENT);

                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7 + ((j + 1) * 8)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[7 + ((j + 1) * 8)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[7])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = obj[7 + ((j + 1) * 8)] != null ? StringUtils.EMPTY + obj[7 + ((j + 1) * 8)] : ZERO;
                        priorVal = RATE.format(Double.valueOf(priorVal));
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[7])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER_THREE, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu+PERCENT);

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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[17])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[17 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[17 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[17])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[17 + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[17])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu+PERCENT);


                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6 + ((j + 1) * 8)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[6 + ((j + 1) * 8)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = StringUtils.EMPTY + obj[6 + ((j + 1) * 8)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[6])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu+PERCENT);

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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[18])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[18 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[18 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[18])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[18 + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[18])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu+PERCENT);

                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8 + ((j + 1) * 8)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[8 + ((j + 1) * 8)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[8])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = StringUtils.EMPTY + obj[8 + ((j + 1) * 8)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[8])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu+PERCENT);

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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[19])));
                        String baseValue = getFormattedValue(AMOUNT, value);
                        pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                    }
                    for (int j = 0; j < priorList.size(); j++) {
                        if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[19 + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[19 + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[19])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                        } else {
                            String priorVal = StringUtils.EMPTY + obj[19 + ((j + 1) * 21)];
                            Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[19])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                            if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                                perChange = 0.0;
                            }
                            String change = String.valueOf(perChange);
                            String baseValu = getFormattedValue(RATE, change);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu+PERCENT);

                        }
                    }
                }
            }
        } else if (dataList != null && !dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                final Object[] obj = (Object[]) dataList.get(i);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                    commonColumn = monthName.toLowerCase() + obj[0];
                }
                if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                    String value = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])));
                    String baseValue = getFormattedValue(AMOUNT, value);
                    pvDTO.addStringProperties(commonColumn + CURRENT + pvsdto.getCurrentProjId(), baseValue);

                }
                for (int j = 0; j < priorList.size(); j++) {
                    if (pvsdto.getVarIndicator().equals(VALUE.getConstant())) {
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9 + ((j + 1) * 8)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else if (pvsdto.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[9 + ((j + 1) * 8)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[9])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), val);

                    } else {
                        String priorVal = StringUtils.EMPTY + obj[9 + ((j + 1) * 8)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[9])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu+PERCENT);

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
                    projDTO.setGroup(String.valueOf(discountRow[2]));
                } else if (!discount.equals(discountRow[2].toString())) {
                    if (!discountName.contains(Constant.TOTAL_SMALL)) {
                        discountList.remove(discount);
                    }
                    start = false;
                }
                if (start) {
                    discount = discountRow[2] != null ? discountRow[2].toString() : StringUtils.EMPTY;
                    String column = StringUtils.EMPTY;
                    String column1 = StringUtils.EMPTY;
                    if (frequencyDivision == 4) {
                        column = Constant.Q + discountRow[1] + StringUtils.EMPTY + discountRow[0];
                    } else if (frequencyDivision == 2) {
                        column = Constant.S + discountRow[1] + StringUtils.EMPTY + discountRow[0];
                    } else if (frequencyDivision == 1) {
                        column = StringUtils.EMPTY + discountRow[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(discountRow[1])) - 1);
                        column = monthName + discountRow[0];
                    }
                    column1 = column + CURRENT + projSelDTO.getCurrentProjId();
                    if (projSelDTO.hasColumn(column1)) {
                        String value = StringUtils.EMPTY + discountRow[3];
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
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + discountRow[3 + ((j + 1) * 3)])));
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
        String resultString = new PVQueryUtils().getComparisonSearchCount(comparisonLookup, filter, screenName, parameters);
        List result = (List) commonDao.executeSelectQuery(resultString, null, null);
        if (result != null && result.size() > 0) {
            count = Integer.valueOf(String.valueOf(result.get(0)));
        } else {
            count = 0;
        }
        return count;
    }

    public List<ComparisonLookupDTO> getComparisonResults(final ComparisonLookupDTO comparisonLookup, int start, int offset, Set<Filter> filter, List<SortByColumn> sortColumns, String screenName) throws Exception {
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

        String resultString = new PVQueryUtils().getComparisonSearch(comparisonLookup, start, offset, filter, sortColumns, screenName, parameters);
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
        if(StringUtils.isNotBlank(projectionIds)){
        projectionIds = projectionIds.substring(1, projectionIds.length() - 1);
//        String query = CustomSQLUtil.get("get-projection-names-by-id");
        String query  = "SELECT PROJECTION_MASTER_SID,PROJECTION_NAME FROM PROJECTION_MASTER WHERE PROJECTION_MASTER_SID IN ("+projectionIds+");";
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    column1 = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
                    String value = StringUtils.EMPTY + obj[4];
                    if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                        value = getFormattedValue(AMOUNT, value);
                    } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                        value = getFormattedValue(RATE, value);
                    }
                    pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
                    for (int j = 0; j < priorList.size(); j++) {
                        column1 = commonColumn + priorList.get(j);
                        String pValue = StringUtils.EMPTY + obj[4 + ((j + 1) * 3)];
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
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
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 21)];
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(obj[1])) - 1);
                        commonColumn = monthName.toLowerCase() + obj[0];
                    }
                    column1 = commonColumn + CURRENT + projSelDTO.getCurrentProjId();
                    String value = StringUtils.EMPTY + obj[5];
                    if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                        value = getFormattedValue(AMOUNT, value);
                    } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                        value = getFormattedValue(RATE_PER_THREE, value);
                    }
                    pvDTO.addStringProperties(column1, isPer ? value + PERCENT : value);
                    for (int j = 0; j < priorList.size(); j++) {
                        column1 = commonColumn + priorList.get(j);
                        String pValue = StringUtils.EMPTY + obj[5 + ((j + 1) * 3)];
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
                    if (frequencyDivision == 4) {
                        commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 2) {
                        commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + obj[0];
                    } else if (frequencyDivision == 12) {
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
                            String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 21)])));
                            String val = getFormattedValue(AMOUNT, priorVal);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 21)];
                            String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                            String val = getFormattedValue(AMOUNT, variance);
                            pvDTO.addStringProperties(commonColumn + priorList.get(j), val);
                        } else {
                            String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 21)];
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
        LOGGER.info("Ending getCustomizedPPA");
        return pvDTO;
    }

    public List<ProjectionVarianceDTO> getCustomisedProjectionResultsTotalDiscount(List<Object> dataList, PVSelectionDTO projSelDTO, int index, boolean isPer) {
        LOGGER.info("Inside getCustomisedProjectionResultsTotalDiscount");
        String lastValue = StringUtils.EMPTY;
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
                if (!StringUtils.EMPTY.equals(lastValue) && !Constant.NULL.equals(lastValue) && obj[2] != null && !lastValue.equals(String.valueOf(obj[2]))) {
                    pvDTO.setGroup(lastValue);
                    resultDto.add(pvDTO);
                    pvDTO = new ProjectionVarianceDTO();
                }
                lastValue = String.valueOf(obj[2]);
                pvDTO.setGroup(lastValue);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    commonColumn = Constant.Q + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 2) {
                    commonColumn = Constant.S + obj[1] + StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[0];
                } else if (frequencyDivision == 12) {
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
                        String priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index + ((j + 1) * 3)])));
                        String val = getFormattedValue(AMOUNT, priorVal);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                    } else if (projSelDTO.getVarIndicator().equals(VARIANCE.getConstant())) {
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
                        String variance = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal)));
                        String val = getFormattedValue(AMOUNT, variance);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), isPer ? val + PERCENT : val);
                    } else {
                        String priorVal = StringUtils.EMPTY + obj[index + ((j + 1) * 3)];
                        Double perChange = (Double.valueOf(isNull(StringUtils.EMPTY + obj[index])) - Double.valueOf(isNull(priorVal))) / Double.valueOf(isNull(priorVal));
                        if (perChange.isNaN() || perChange.isInfinite() || StringUtils.EMPTY.equals(String.valueOf(perChange))) {
                            perChange = 0.0;
                        }
                        String change = String.valueOf(perChange);
                        String baseValu = getFormattedValue(RATE_PER, change);
                        pvDTO.addStringProperties(commonColumn + priorList.get(j), baseValu + PERCENT);
                    }
                }
                if (i == dataList.size() - 1) {
                    resultDto.add(pvDTO);
                }
            }
        } else {
            for (String discountNameList : projSelDTO.getDiscountNameList()) {
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
                if (StringUtils.isBlank(discountName) || !discountName.equals(String.valueOf(discountRow[2]))) {
                    projDTO = new ProjectionVarianceDTO();
                    projDtoList.add(projDTO);
                    discountName = String.valueOf(discountRow[2]);
                    projDTO.setGroup(discountName);
                    projDTO.setRelationshipLevelName(discountName);
                }
                String column = StringUtils.EMPTY;
                String column1 = StringUtils.EMPTY;
                if (frequencyDivision == 4) {
                    column = Constant.Q + discountRow[1] + StringUtils.EMPTY + discountRow[0];
                } else if (frequencyDivision == 2) {
                    column = Constant.S + discountRow[1] + StringUtils.EMPTY + discountRow[0];
                } else if (frequencyDivision == 1) {
                    column = StringUtils.EMPTY + discountRow[0];
                } else if (frequencyDivision == 12) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.valueOf(String.valueOf(discountRow[1])) - 1).toLowerCase();
                    column = monthName + discountRow[0];
                }
                column1 = column + CURRENT + projSelDTO.getCurrentProjId();
                if (projSelDTO.hasColumn(column1)) {
                    String value = null;
                    if (parentDto.getPropertyValue(column1) != null) {
                        value = StringUtils.EMPTY + discountRow[3];
//                        System.out.println("value----------------------------- >>> "+value);
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
                            priorVal = String.valueOf(Double.valueOf(isNull(StringUtils.EMPTY + discountRow[3 + ((j + 1))])));
                           if (((parentDto.getGroup().contains(Constant.PVVariables.VAR_DIS_AMOUNT.toString())) || (parentDto.getGroup().contains(Constant.PVVariables.VAR_RPU.toString()))) && parentDto.getGroup().contains(Constant.CHANGE.toString())) {
                                priorVal = getFormattedValue(RATE_PER_THREE, priorVal);
                                priorVal = priorVal + PERCENT;
                            } else if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                                priorVal = getFormattedValue(AMOUNT, priorVal);
                            } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                                priorVal = getFormattedValue(RATE_PER_THREE, priorVal);
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
     * Used to get the count of discount comes under expanded hierarchy in Total Discount
     * 
     * @return 
     */
    private String getProgramCountForCurrentHierarchy(PVSelectionDTO projSelDTO) {
        String query = "SELECT Count(DISTINCT RS_MODEL_sid)\n"
                + "FROM   ST_NM_DISCOUNT_PROJ_MASTER B\n"
                + "       JOIN PROJECTION_DETAILS pd\n"
                + "         ON pd.PROJECTION_DETAILS_SID = b.PROJECTION_DETAILS_SID\n"
                + "       JOIN @CCP CCP\n"
                + "         ON pd.ccp_details_sid = ccp.CCP_DETAILS_SID\n"
                + "WHERE  B.USER_ID = " + projSelDTO.getUserId() + " \n"
                + "       AND B.SESSION_ID = " + projSelDTO.getSessionId() + " \n";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            query += "            AND B.RS_MODEL_SID IN ( " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " );";
        }
        return query;
    }

    private String getProgramCategoryForCurrentHierarchy(PVSelectionDTO projSelDTO) {
        String discountNoList = StringUtils.EMPTY;
        String indicatorValue = StringUtils.EMPTY;
         discountNoList += "       " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
        if(PROGRAM.getConstant().equalsIgnoreCase(projSelDTO.getDiscountLevel())){
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            indicatorValue = "R";
        }} else if(PROGRAM_CATEGORY.getConstant().equalsIgnoreCase(projSelDTO.getDiscountLevel())) {
            indicatorValue = "P";
        }
        String query = "DECLARE @INDICATOR CHAR(1)='"+indicatorValue+"'\n" +
" \n" +
"SELECT Count(DISTINCT CASE\n" +
"                        WHEN @INDICATOR = 'P' THEN REBATE_PROGRAM_TYPE\n" +
"                        ELSE DISCOUNT_ID\n" +
"                      END)\n" +
"FROM   (SELECT DISTINCT RS.RS_MODEL_SID AS DISCOUNT_ID,\n" +
"                        RS.RS_NAME      AS DISCOUNT_NAME,\n" +
"                        REBATE_PROGRAM_TYPE\n" +
"        FROM   RS_CONTRACT RS\n" +
"               INNER JOIN ST_NM_DISCOUNT_PROJ_MASTER DM\n" +
"                       ON RS.RS_MODEL_SID = DM.RS_MODEL_SID\n" +
"               INNER JOIN PROJECTION_DETAILS PD\n" +
"                       ON PD.PROJECTION_DETAILS_SID = DM.PROJECTION_DETAILS_SID\n" +
"               JOIN CCP_DETAILS CCP\n" +
"                 ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n" +
"                    AND RS.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID\n" +
"               INNER JOIN HELPER_TABLE HT\n" +
"                       ON HT.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE\n" +
"                          AND HT.LIST_NAME = 'REBATE_PROGRAM_TYPE'\n" +
"                          AND CASE\n" +
"                                WHEN HT.DESCRIPTION = 'REIMB' THEN 'REIMBURSEMENT'\n" +
"                                WHEN HT.DESCRIPTION = 'OI' THEN 'OFF INVOICE'\n" +
"                                ELSE HT.DESCRIPTION\n" +
"                              END = DM.PRICE_GROUP_TYPE\n" +
"        WHERE  DM.USER_ID = " + projSelDTO.getUserId() + " \n"+
"               AND DM.SESSION_ID = " + projSelDTO.getSessionId()+ " \n"+
"               AND PD.PROJECTION_MASTER_SID =" + projSelDTO.getProjectionId()+ " \n"+
"               AND  DM.RS_MODEL_SID\n" +
"                    IN ("+discountNoList+" )\n" +
"               AND RS.INBOUND_STATUS <> 'D'\n" +
"               AND EXISTS (SELECT 1\n" +
"                           FROM   CFP_CONTRACT CFP1\n" +
"                                  JOIN CFP_CONTRACT_DETAILS CFP2\n" +
"                                    ON CFP1.CFP_CONTRACT_SID = CFP2.CFP_CONTRACT_SID\n" +
"                                       AND RS.CFP_CONTRACT_SID = CFP1.CFP_CONTRACT_SID\n" +
"                           WHERE  COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID\n" +
"                                  AND CFP1.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID))A";

        return query;
    }

    
    private String getPPACount(PVSelectionDTO projSelDTO, Boolean isprogram, Boolean isTotal) {
        String query = " SELECT ";
        if (isprogram) {
            query += " COUNT(DISTINCT RS.RS_NAME) ";
        } else {
            query += " CASE WHEN COUNT (DISTINCT RS.REBATE_PROGRAM_TYPE) >0 THEN 1 ELSE 0 END ";
        }
        query += "FROM ST_NM_PPA_PROJECTION_MASTER TEMP \n"
                + "JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = TEMP.PROJECTION_DETAILS_SID  \n";
        if (!isTotal) {
            query += " JOIN   @CCP CCP ON pd.ccp_details_sid = ccp.CCP_DETAILS_SID\n";
        }
        query += "JOIN RS_MODEL RS ON RS.RS_MODEL_SID = TEMP.RS_MODEL_SID \n"
                + "WHERE USER_ID = " + projSelDTO.getUserId() + " AND SESSION_ID = " + projSelDTO.getSessionId() + " AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();
        return query;
    }
    
    private int getPPACountValue(PVSelectionDTO projSelDTO) {
        int count = 0;
        String query = CommonLogic.getCCPQuery(projSelDTO, Boolean.TRUE)+" SELECT COUNT(DISTINCT RS.RS_NAME) "
                + "FROM ST_NM_PPA_PROJECTION_MASTER TEMP \n"
                + "JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = TEMP.PROJECTION_DETAILS_SID  \n"
                +" JOIN   @CCP CCP ON pd.ccp_details_sid = ccp.CCP_DETAILS_SID\n"
                + "JOIN RS_MODEL RS ON RS.RS_MODEL_SID = TEMP.RS_MODEL_SID \n"
                + "WHERE USER_ID = " + projSelDTO.getUserId() + " AND SESSION_ID = " + projSelDTO.getSessionId() + " AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();
        List list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            count = count + Integer.valueOf(String.valueOf(ob));
        }
        return count;
    }

}
