/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.projectionVariance.logic;


import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.abstractCff.AbstractFilterLogic;
import com.stpl.app.cff.dto.PVSelectionDTO;
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.queryUtils.CommonQueryUtils;
import com.stpl.app.cff.ui.projectionVariance.dto.ComparisonLookupDTO;
import com.stpl.app.cff.ui.projectionVariance.dto.ProjectionVarianceDTO;
import com.stpl.app.cff.ui.projectionVariance.form.RunnableJob;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import static com.stpl.app.cff.util.Constants.ButtonConstants.ALL;
import static com.stpl.app.cff.util.Constants.CommonConstants.NULL;
import static com.stpl.app.cff.util.Constants.CommonConstants.VALUE;
import static com.stpl.app.cff.util.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.cff.util.Constants.LabelConstants.PERCENT;
import static com.stpl.app.cff.util.Constants.LabelConstants.PROGRAM_CATEGORY;
import static com.stpl.app.cff.util.Constants.LabelConstants.TOTAL;
import static com.stpl.app.cff.util.Constants.LabelConstants.TOTAL_DISCOUNT;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.Converters;
import com.stpl.app.cff.util.HeaderUtils;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.NmProjectionSelectionLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterTreeTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author manikandaprabu.g
 */
public class ProjectionVarianceLogic {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProjectionVarianceLogic.class);
    
    /**
     * The Constant AMOUNT.
     */
    private static final DecimalFormat AMOUNT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0");
    public static final String DISCOUNT_PERCENT = "Discount %";
    public static final String DISCOUNT_DOLLAR = "Discount $";
    private static final String DF_LEVEL_NUMBER = "dfLevelNumber";
    private static final String DF_LEVEL_NAME = "dfLevelName";
    private static final String CFF_TOTAL = "CFF Total";
    public static final String D_INDICATOR = "D";
    
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat RATE = new DecimalFormat("#######0.00");
    public static final String TWO_DECIMAL = "#,##0.00";
    /**
     * The Constant RATE.
     */
    private static final DecimalFormat RATE_PER = new DecimalFormat(TWO_DECIMAL);
    /**
     * RATE_PER_THREE
     */
    private static final DecimalFormat RATE_PER_THREE = new DecimalFormat(TWO_DECIMAL);
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    private static final String ZERO = "0";
    private static final String DETAIL = "Detail";
    private static final String C = "C";
    private static final String P = "P";
    private static final String CURRENT = "Current";
    private final CommonLogic commonLogic = new CommonLogic();
    private CustomTableHeaderDTO leftHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    private PVSelectionDTO selectionDTO = new PVSelectionDTO();
    private List<Object> pivotTotalList = new ArrayList<>();
    private List<Object> pivotDiscountList = new ArrayList<>();
    private int currentProjId;
    private static final String PRC_CFF_RESULTS = "Prc_cff_results";
    private List chartList;
    private static Thread procedureThread;
    private static RunnableJob runnableJob;
    public static final String CROSS_APPLY_SELECT_TOKEN_FROM_UDF_SPLITST = "CROSS APPLY (SELECT TOKEN FROM UDF_SPLITSTRING('";
    public static final String CONCAT_CONDITION = "', ',') C WHERE CH.PROD_HIERARCHY_NO LIKE concat(C.TOKEN , '%')) FN";
    public static final String WHERE_FILTER_CCPD = " WHERE FILTER_CCPD = 1 ";

    public ProjectionVarianceLogic() {
        super();
    }

    public List getChartList() {
        return chartList == null ? chartList : new ArrayList<>(chartList);
    }

    public void setChartList(List chartList) {
        this.chartList = chartList == null ? chartList : new ArrayList<>(chartList);
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
        String from;
        String to;
        StringBuilder fromBuilder = new StringBuilder();
        StringBuilder toBuilder = new StringBuilder();
        for (int i = 0; i < fromArray.length; i++) {
            fromBuilder.append(fromArray[i]);
        }
        for (int i = 0; i < toArray.length; i++) {
            toBuilder.append(toArray[i]);
        }
        from = fromBuilder.toString();
        to = toBuilder.toString();
        if (rightHeader.getFrequencyDivision() == NumericConstants.TWELVE) {
            from = from.toLowerCase(Locale.ENGLISH);
            to = to.toLowerCase(Locale.ENGLISH);
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
        Object singleCol = StringConstantsUtil.GROUP_PROPERTY;
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
    public List<ComparisonLookupDTO> getCustomizedComparisonList(final List list) throws PortalException {
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
                    comparisonLookupDTO.setProjectionId(Integer.parseInt(obj[NumericConstants.EIGHT].toString()));
                }
                if (obj[NumericConstants.NINE] == null) {
                    comparisonLookupDTO.setCreatedDateFrom(null);
                } else {
                    comparisonLookupDTO.setCreatedDateFrom((Date) obj[NumericConstants.NINE]);
                }
                if (obj[NumericConstants.TEN] == null) {
                    comparisonLookupDTO.setCreatedBy(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setCreatedBy(Converters.getUserFLName(Converters.convertNullToEmpty(obj[NumericConstants.TEN].toString())));
                }
                finalList.add(comparisonLookupDTO);

            }
        }
        return finalList;
    }

    public List<ComparisonLookupDTO> getComparisonResults(final ComparisonLookupDTO comparisonLookup,boolean isDataSelection,SessionDTO session) throws Exception{
        char asterik = '*';
        char percent = '%';
        String andOperator;
        List inputList = getComparisonInput(comparisonLookup,isDataSelection,session);
        StringBuilder query = CommonQueryUtils.getSqlQuery(inputList, isDataSelection?"comparisonLoadDataDataSelection":"comparisonLoadData");
        query.append(" where ");
        if (comparisonLookup.getContract() != null && !comparisonLookup.getContract().isEmpty()) {
            query.append(" CONTRACT LIKE ").append('\'').append(comparisonLookup.getContract().replace(asterik, percent)).append('\'');
            andOperator = StringConstantsUtil.AND;
        } else {
            query.append(" CONTRACT LIKE ").append("'%'");
            andOperator = StringConstantsUtil.AND;
        }
        if (comparisonLookup.getMarketType() != null && !comparisonLookup.getMarketType().isEmpty()) {
            query.append(andOperator).append("  MARKET_TYPE LIKE  ").append('\'').append(comparisonLookup.getMarketType().replace(asterik, percent)).append('\'');
            andOperator = StringConstantsUtil.AND;
        } else {
            query.append(andOperator).append("  MARKET_TYPE LIKE  ").append("'%'");
            andOperator = StringConstantsUtil.AND;
        }
        if (comparisonLookup.getCustomer() != null && !comparisonLookup.getCustomer().isEmpty()) {
            query.append(andOperator).append("  CONTRACT_HOLDER LIKE ").append('\'').append(comparisonLookup.getCustomer().replace(asterik, percent)).append('\'');
            andOperator = StringConstantsUtil.AND;
        } else {
            query.append(andOperator).append("  CONTRACT_HOLDER LIKE ").append("'%'");
            andOperator = StringConstantsUtil.AND;
        }
        if (comparisonLookup.getNdcName() != null && !comparisonLookup.getNdcName().isEmpty()) {
            query.append(andOperator).append("  ITEM_NAME LIKE   ").append('\'').append(comparisonLookup.getNdcName().replace(asterik, percent)).append('\'');
            andOperator = StringConstantsUtil.AND;
        } else {
            query.append(andOperator).append("  ITEM_NAME LIKE   ").append("'%'");
            andOperator = StringConstantsUtil.AND;
        }
        if (comparisonLookup.getNdcNo() != null && !comparisonLookup.getNdcNo().isEmpty()) {
            query.append(andOperator).append("  ITEM_NO LIKE ").append('\'').append(comparisonLookup.getNdcNo().replace(asterik, percent)).append('\'');
            andOperator = StringConstantsUtil.AND;
        } else {
            query.append(andOperator).append("  ITEM_NO LIKE ").append("'%'");
            andOperator = StringConstantsUtil.AND;
        }
        if (comparisonLookup.getBrand() != null && !comparisonLookup.getBrand().isEmpty()) {
            query.append(andOperator).append("   BRAND LIKE ").append('\'').append(comparisonLookup.getBrand().replace(asterik, percent)).append("' ");
        } else {
            query.append(andOperator).append("   BRAND LIKE ").append("'%' ");
        }
        StringBuilder orderBy = AbstractFilterLogic.getInstance().orderByQueryGenerator(comparisonLookup.getSortColumns(), getOrderByMap());
        query.append(orderBy.toString());
        query.append(" OFFSET ").append(comparisonLookup.getStart()).append(" ROWS FETCH NEXT ").append(comparisonLookup.getOffset()).append(" ROWS ONLY");
        
        List result = HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
        return getCustomizedComparisonList(result);
    }

    /**
     * customize the results
     *
     * @param list
     * @return list
     */
    public List<ComparisonLookupDTO> getCustomizedPVComparisonList(final List list) throws PortalException{
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
                    comparisonLookupDTO.setProjectionId(Integer.parseInt(obj[NumericConstants.EIGHT].toString()));
                }
                if (obj[NumericConstants.NINE] == null) {
                    comparisonLookupDTO.setCreatedDateFrom(null);
                } else {
                    comparisonLookupDTO.setCreatedDateFrom((Date) obj[NumericConstants.NINE]);
                }
                if (obj[NumericConstants.TEN] == null) {
                    comparisonLookupDTO.setCreatedBy(StringUtils.EMPTY);
                } else {
                    comparisonLookupDTO.setCreatedBy(Converters.getUserFLName(Converters.convertNullToEmpty(obj[NumericConstants.TEN].toString())));
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
                return BooleanConstant.getTrueFlag();
            }
        }
        return BooleanConstant.getFalseFlag();

    }

    public static String convertStringToDate(String stringDate, String inputFormat, String outputFormat) {
        try {
            SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
            Date date;
            date = inputDateFormatter.parse(stringDate);
            return outputDateFormatter.format(date);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CommonLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer getComparisonCount(ComparisonLookupDTO comparisonLookup,boolean isDataSelection,SessionDTO session) {
        char asterik = '*';
        char percent = '%';
        String andOperator;
        List inputList = getComparisonInput(comparisonLookup,isDataSelection,session);
        StringBuilder query = CommonQueryUtils.getSqlQuery(inputList, isDataSelection?"comparisonSearchCountDataSelection":"comparisonSearchCount");
        query.append(" where ");
        if (comparisonLookup.getContract() != null && !comparisonLookup.getContract().isEmpty()) {
            query.append(" CONTRACT LIKE  ").append('\'').append(comparisonLookup.getContract().replace(asterik, percent)).append('\'');
            andOperator = StringConstantsUtil.AND;
        } else {
            query.append(" CONTRACT LIKE  ").append("'%'");
            andOperator = StringConstantsUtil.AND;
        }
        if (comparisonLookup.getMarketType() != null && !comparisonLookup.getMarketType().isEmpty()) {
            query.append(andOperator).append("  MARKET_TYPE LIKE ").append('\'').append(comparisonLookup.getMarketType().replace(asterik, percent)).append('\'');
            andOperator = StringConstantsUtil.AND;
        } else {
            query.append(andOperator).append("  MARKET_TYPE LIKE ").append("'%'");
            andOperator = StringConstantsUtil.AND;

        }
        if (comparisonLookup.getCustomer() != null && !comparisonLookup.getCustomer().isEmpty()) {
            query.append(andOperator).append("  CONTRACT_HOLDER LIKE  ").append('\'').append(comparisonLookup.getCustomer().replace(asterik, percent)).append('\'');
            andOperator = StringConstantsUtil.AND;
        } else {
            query.append(andOperator).append("  CONTRACT_HOLDER LIKE  ").append("'%'");
            andOperator = StringConstantsUtil.AND;
        }
        if (comparisonLookup.getNdcName() != null && !comparisonLookup.getNdcName().isEmpty()) {
            query.append(andOperator).append("  ITEM_NAME LIKE ").append('\'').append(comparisonLookup.getNdcName().replace(asterik, percent)).append('\'');
            andOperator = StringConstantsUtil.AND;
        } else {
            query.append(andOperator).append("  ITEM_NAME LIKE ").append("'%'");
            andOperator = StringConstantsUtil.AND;
        }
        if (comparisonLookup.getNdcNo() != null && !comparisonLookup.getNdcNo().isEmpty()) {
            query.append(andOperator).append("  ITEM_NO LIKE  ").append('\'').append(comparisonLookup.getNdcNo().replace(asterik, percent)).append('\'');
            andOperator = StringConstantsUtil.AND;
        } else {
            query.append(andOperator).append("  ITEM_NO LIKE  ").append("'%'");
            andOperator = StringConstantsUtil.AND;
        }
        if (comparisonLookup.getBrand() != null && !comparisonLookup.getBrand().isEmpty()) {
            query.append(andOperator).append("  BRAND LIKE ").append('\'').append(comparisonLookup.getBrand().replace(asterik, percent)).append('\'');
        } else {
            query.append(andOperator).append("  BRAND LIKE ").append("'%'");
        }

        List result = HelperTableLocalServiceUtil.executeSelectQuery(query.toString());
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
                    selectionDTO.setDeductionHierarchyNo(parentDto.getDeductionHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    selectionDTO.setDeductionHierarchyNo(parentDto.getDeductionHierarchyNo());
                } else{
                    selectionDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    selectionDTO.setDeductionHierarchyNo(selectionDTO.getHierarchyNo());
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
                selectionDTO.setDeductionHierarchyNo(StringUtils.EMPTY);
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
                    selectionDTO.setDeductionHierarchyNo(parentDto.getDeductionHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(P)) {
                    selectionDTO.setProductHierarchyNo(selectionDTO.getHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    selectionDTO.setDeductionHierarchyNo(parentDto.getDeductionHierarchyNo());
                } else{
                    selectionDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    selectionDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    selectionDTO.setDeductionHierarchyNo(selectionDTO.getHierarchyNo());
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
                selectionDTO.setDeductionHierarchyNo(StringUtils.EMPTY);
            }
            if (selectionDTO.getPivotView().equals("Period")) {
                count += getPeriodResultsCount(selectionDTO, parentDto, isLevelsCount);
            } else {
                count += getPivotResultsCount(parentId, selectionDTO, isLevelsCount);
            }
        } else if (isLevelsCount) {
            selectionDTO.setLevelNo(selectionDTO.getFilterLevelNo());
            selectionDTO.setTreeLevelNo(selectionDTO.getFilterLevelNo());
            selectionDTO.setHierarchyNo(StringUtils.EMPTY);
            count += configureLevelsCount(selectionDTO);
        }
        return count;
    }

    public int getPeriodResultsCount(PVSelectionDTO pVSelectionDTO, ProjectionVarianceDTO parentDto, boolean isLevelsCount) {
        int count = 0;
        boolean isDiscountExpand = false;
        pVSelectionDTO.setGroupCount(false);
        if (parentDto != null) {
            if (parentDto.getGroup().contains(DISCOUNT_DOLLAR) || parentDto.getGroup().contains(DISCOUNT_PERCENT) || parentDto.getGroup().contains("RPU")) {
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
            if (parentDto.getGroup().contains(DISCOUNT_DOLLAR) || parentDto.getGroup().contains(DISCOUNT_PERCENT) || parentDto.getGroup().contains("RPU") || (parentDto.getGroup().contains("Discount % of Ex-Factory"))) {
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
                    getResultsFromProcedure(pVSelectionDTO, BooleanConstant.getFalseFlag());
                }
                if (isDiscountExpand) {
                    List<Object> list = new ArrayList<>();
                    list.addAll(pivotDiscountList.isEmpty() ? geDiscountResultsFromPrc(pVSelectionDTO) : pivotDiscountList);
                    pVSelectionDTO.setIsTotal(false);
                    List<ProjectionVarianceDTO> resultsDto = new ArrayList<>();
                     if (parentDto.getGroup().contains("Discount % of Ex-Factory")) {
                         pVSelectionDTO.setConversionNeeded(false);
                        if (parentDto.getGroup().contains(StringConstantsUtil.VALUE_LABEL)) {
                            pVSelectionDTO.setVarIndicator(Constants.VALUE);
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.ELEVEN, BooleanConstant.getTrueFlag()));
                        } else if (parentDto.getGroup().contains(StringConstantsUtil.VARIANCE_LABEL)) {
                            pVSelectionDTO.setVarIndicator(Constants.VARIANCE);
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.ELEVEN, BooleanConstant.getTrueFlag()));
                        } else if (parentDto.getGroup().contains(CHANGE1)) {
                            pVSelectionDTO.setVarIndicator(Constants.CHANGE);
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.ELEVEN, BooleanConstant.getTrueFlag()));
                        }
                    } else if (parentDto.getGroup().contains(DISCOUNT_DOLLAR)) {
                        if (parentDto.getGroup().contains(StringConstantsUtil.VALUE_LABEL)) {
                            pVSelectionDTO.setConversionNeeded(true);
                            pVSelectionDTO.setVarIndicator(Constants.VALUE);
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.THREE, BooleanConstant.getFalseFlag()));
                        } else if (parentDto.getGroup().contains(StringConstantsUtil.VARIANCE_LABEL)) {
                            pVSelectionDTO.setConversionNeeded(true);
                            pVSelectionDTO.setVarIndicator(Constants.VARIANCE);
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.THREE, BooleanConstant.getFalseFlag()));
                        } else if (parentDto.getGroup().contains(CHANGE1)) {
                            pVSelectionDTO.setConversionNeeded(false);
                            pVSelectionDTO.setVarIndicator(Constants.CHANGE);
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.THREE, BooleanConstant.getTrueFlag()));
                        }
                    } else if (parentDto.getGroup().contains(DISCOUNT_PERCENT)) {
                            pVSelectionDTO.setConversionNeeded(false);
                        if (parentDto.getGroup().contains(StringConstantsUtil.VALUE_LABEL)) {
                            pVSelectionDTO.setVarIndicator(Constants.VALUE);
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.FOUR, BooleanConstant.getTrueFlag()));
                        } else if (parentDto.getGroup().contains(StringConstantsUtil.VARIANCE_LABEL)) {
                            pVSelectionDTO.setVarIndicator(Constants.VARIANCE);
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.FOUR, BooleanConstant.getTrueFlag()));
                        } else if (parentDto.getGroup().contains(CHANGE1)) {
                            pVSelectionDTO.setVarIndicator(Constants.CHANGE);
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.FOUR, BooleanConstant.getTrueFlag()));
                        }
                    } else if (parentDto.getGroup().contains("RPU")) {
                            pVSelectionDTO.setConversionNeeded(false);
                        if (parentDto.getGroup().contains(StringConstantsUtil.VALUE_LABEL)) {
                            pVSelectionDTO.setVarIndicator(Constants.VALUE);
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.TEN, BooleanConstant.getFalseFlag()));
                        } else if (parentDto.getGroup().contains(StringConstantsUtil.VARIANCE_LABEL)) {
                            pVSelectionDTO.setVarIndicator(Constants.VARIANCE);
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.TEN, BooleanConstant.getFalseFlag()));
                        } else if (parentDto.getGroup().contains(CHANGE1)) {
                            pVSelectionDTO.setVarIndicator(Constants.CHANGE);
                            resultsDto.addAll(getCustomisedDiscount(list, pVSelectionDTO, NumericConstants.TEN, BooleanConstant.getFalseFlag()));
                        }
                    } 
                    tobeAddedList.addAll(resultsDto);
                } else {
                    if (pVSelectionDTO.getLevel().equals(DETAIL)) {
                        getResultsFromProcedure(pVSelectionDTO, BooleanConstant.getTrueFlag());
                    }
                    List<ProjectionVarianceDTO> allList = getCustPeriodVariance(pivotTotalList, pVSelectionDTO, parentDto);
                    tobeAddedList.addAll(allList);
                }
                setChartList(tobeAddedList);
                 for (int i = started; (i < tobeAddedList.size()) && (neededRecord > 0); neededRecord--, i++) {
                    projDTOList.add(tobeAddedList.get(i));

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
    public static final String CHANGE1 = "%Change";

    /**
     * Discount procedure
     *
     * @param projSelDTO
     */
    List<Object[]> geDiscountResultsFromPrc(PVSelectionDTO projSelDTO) {
        String frequency = projSelDTO.getFrequency();
        List<String> projectionIdList = new ArrayList<>();
        pivotDiscountList = new ArrayList<>();
        if (frequency.equals(StringConstantsUtil.QUARTERLY_FREQ)) {
            frequency = StringConstantsUtil.QUARTERLY_LABEL;
        } else if (frequency.equals(StringConstantsUtil.SEMI_ANNUALLY_FREQ)) {
            frequency = "SEMI-ANNUAL";
        } else if (frequency.equals(StringConstantsUtil.MONTHLY_FREQ)) {
            frequency = StringConstantsUtil.MONTHLY_LABEL;
        } else {
            frequency = StringConstantsUtil.ANNUAL_LABEL;
        }
        projectionIdList.add(String.valueOf(selectionDTO.getCurrentProjId()));
        for (Integer projId : projSelDTO.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
        }
        String projectionId = CommonUtils.collectionToString(projectionIdList, false);
        String salesInclusion = projSelDTO.getSessionDTO().getSalesInclusion();
        String deductionInclusion = projSelDTO.getSessionDTO().getDeductionInclusion();
        if (projSelDTO.getLevel().equals(DETAIL)) {
            getCCPIds(projSelDTO);
        }
        String discountLevelName = projSelDTO.getSessionDTO().getDeductionName();
        String viewName=projSelDTO.getView().equalsIgnoreCase("Custom")?"D":projSelDTO.getView();
        Object[] orderedArg = {projectionId, frequency, null, null,discountLevelName,salesInclusion, deductionInclusion, null , 
                      projSelDTO.getSessionDTO().getCustomViewMasterSid()
                      ,projSelDTO.getSessionDTO().getSessionId(),projSelDTO.getSessionDTO().getUserId(),viewName,projSelDTO.getSessionDTO().getLevelHierarchyNo() };
        List<Object[]> discountsList = CommonLogic.callProcedure("Prc_cff_projection_results_discount", orderedArg);
        pivotDiscountList.addAll(discountsList);
        return discountsList;
    }

    /**
     * get Customized period variance
     *
     * @param baseVariables
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

            if (P.equals(baseVariables.getHierarchyIndicator())) {
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

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
        List list;
        List input = new ArrayList<>();
        List dedInput = new ArrayList<>();
        input.add(dto.getCurrentProjId());
        input.add(dto.getCcpIds());
        input.add(getRSIds(dto));
        if (dto.getCcpIds() == null || dto.getCcpIds().isEmpty()) {
            input.add(StringUtils.EMPTY);
            input.add(StringUtils.EMPTY);
        } else {
            String inQuery = " AND PD.CCP_DETAILS_SID IN (SELECT TOKEN FROM UDF_SPLITSTRING(@CCP_IDS,','))";
            input.add(inQuery);
            input.add(inQuery);
        }
        if (!dto.getDeductionLevelFilter().isEmpty()) {
            dedInput.add(dto.getCurrentProjId());
            dedInput.add(dto.getCcpIds());
            dedInput.add(getRSIds(dto));
            String commonQuery = helperJoinQuery(dto, dedInput);
            list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(commonQuery, dto.getSessionDTO().getCurrentTableNames()));
        } else {
            String rsQuery = CommonQueryUtils.getAppDataQuery(input, "getCffDiscountExpandCount",
                    dto.getDiscountLevel().equals(PROGRAM_CATEGORY.toString())
                    ? "getCffDiscountExpandCount_PROGRAM_CATTEGORY" : "getCffDiscountExpandCount_PROGRAM");
            if (!dto.getLevel().equals(DETAIL)) {
                rsQuery = rsQuery.replace("@RSQUERY", " ");
            } else {
                rsQuery = rsQuery.replace("@RSQUERY", " AND NMDP.RS_CONTRACT_SID IN (SELECT TOKEN FROM UDF_SPLITSTRING(@RS_IDS,',')) ");
            }
            list = HelperTableLocalServiceUtil.executeSelectQuery(rsQuery);
        }
        return CFFLogic.getCount(list);
    }
    
    public String helperJoinQuery(PVSelectionDTO dto,List dedInput){
        String commonQuery = CommonQueryUtils.getAppQuery(dedInput, "getCffDiscountExpandDeductionCount");
        commonQuery = commonQuery.replace("@RS_SELECTION", " HT.DESCRIPTION ");
            
        switch (dto.getSessionDTO().getSelectedDeductionLevelNo()) {
            case 1:
                commonQuery = commonQuery.replace(StringConstantsUtil.UDCJOIN, "   ");
                commonQuery = commonQuery.replace(StringConstantsUtil.HELPERTABLEJOIN, " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.RS_CATEGORY AND HT.HELPER_TABLE_SID in ("+ CommonUtils.collectionToString(dto.getDeductionLevelFilter(), false) +")");
                break;
            case 2:
                commonQuery = commonQuery.replace(StringConstantsUtil.UDCJOIN, "  ");
                commonQuery = commonQuery.replace(StringConstantsUtil.HELPERTABLEJOIN, " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.RS_TYPE  AND HT.HELPER_TABLE_SID in ("+ CommonUtils.collectionToString(dto.getDeductionLevelFilter(), false) +")");
                break;
            case 3:
                commonQuery = commonQuery.replace(StringConstantsUtil.UDCJOIN, "  ");
                commonQuery = commonQuery.replace(StringConstantsUtil.HELPERTABLEJOIN, " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE  AND HT.HELPER_TABLE_SID in ("+ CommonUtils.collectionToString(dto.getDeductionLevelFilter(), false) +")");
                break;
            case 4:
                commonQuery = commonQuery.replace(StringConstantsUtil.UDCJOIN, StringConstantsUtil.UDC_MASTER_SID);
                commonQuery = commonQuery.replace(StringConstantsUtil.HELPERTABLEJOIN, " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UD.UDC1 AND HT.HELPER_TABLE_SID in ("+ CommonUtils.collectionToString(dto.getDeductionLevelFilter(), false) +")");
                break;

            case 5:
                commonQuery = commonQuery.replace(StringConstantsUtil.UDCJOIN, StringConstantsUtil.UDC_MASTER_SID);
                commonQuery = commonQuery.replace(StringConstantsUtil.HELPERTABLEJOIN, " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UD.UDC2 AND HT.HELPER_TABLE_SID in ("+ CommonUtils.collectionToString(dto.getDeductionLevelFilter(), false) +")");
                break;
            case 6:
                commonQuery = commonQuery.replace(StringConstantsUtil.UDCJOIN, StringConstantsUtil.UDC_MASTER_SID);
                commonQuery = commonQuery.replace(StringConstantsUtil.HELPERTABLEJOIN, " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UD.UDC3 AND HT.HELPER_TABLE_SID in ("+ CommonUtils.collectionToString(dto.getDeductionLevelFilter(), false) +")");
                break;
            case 7:
                commonQuery = commonQuery.replace(StringConstantsUtil.UDCJOIN, StringConstantsUtil.UDC_MASTER_SID);
                commonQuery = commonQuery.replace(StringConstantsUtil.HELPERTABLEJOIN, " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UD.UDC4 AND HT.HELPER_TABLE_SID in (" + CommonUtils.collectionToString(dto.getDeductionLevelFilter(), false) + ")");
                break;
            case 8:
                commonQuery = commonQuery.replace(StringConstantsUtil.UDCJOIN, StringConstantsUtil.UDC_MASTER_SID);
                commonQuery = commonQuery.replace(StringConstantsUtil.HELPERTABLEJOIN, " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UD.UDC5 AND HT.HELPER_TABLE_SID in (" + CommonUtils.collectionToString(dto.getDeductionLevelFilter(), false) + ")");
                break;
            case 9:
                commonQuery = commonQuery.replace(StringConstantsUtil.UDCJOIN, StringConstantsUtil.UDC_MASTER_SID);
                commonQuery = commonQuery.replace(StringConstantsUtil.HELPERTABLEJOIN, " JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = UD.UDC6 AND HT.HELPER_TABLE_SID in (" + CommonUtils.collectionToString(dto.getDeductionLevelFilter(), false) + ")");
                break;
            case 10:
                commonQuery = commonQuery.replace(StringConstantsUtil.UDCJOIN, " ");
                commonQuery = commonQuery.replace(StringConstantsUtil.HELPERTABLEJOIN, " ");
                commonQuery = commonQuery.replace(" HT.DESCRIPTION ", "ST.RS_CONTRACT_SID");
                break;
            default:
                break;
        }
        return commonQuery;

    }

    public int configureLevelsCount(PVSelectionDTO selection) {
        int count;
        if (selection.isIsCustomHierarchy()) {
            count = getCountForCustomView(selection);
        } else {
            count = getCount(selection.getSessionDTO(), selection.getHierarchyIndicator(),selection);
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

        CommonLogic vCommonLogic = new CommonLogic();
        List<ProjectionVarianceDTO> resultList = new ArrayList<>();
        int resultStart;
        if (maxRecord == -1) {
            resultStart = start;
        } else {
            resultStart = (start <= maxRecord) ? 0 : start - maxRecord;
        }
        if (projSelDTO.isIsCustomHierarchy()) {

            String hierarchyIndicator = vCommonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
            Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getCustomDescription();
            List<String> hierarchyNoList = getHiearchyNoForCustomView(projSelDTO, resultStart, offset);
            for (String hierarchyNo : hierarchyNoList) {
                String hierarchy = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0] : hierarchyNo;
                resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, hierarchyIndicator, projSelDTO.getTreeLevelNo(), relationshipLevelDetailsMap.get(hierarchy)));
            }

        } else {
            Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();

            List<String> hierarchyNoList = getHiearchyNoAsList(projSelDTO, resultStart, offset);
            for (String hierarchyNo : hierarchyNoList) {
                String hierarchy = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0] : hierarchyNo;
                resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, projSelDTO.getHierarchyIndicator(), Integer.parseInt(relationshipLevelDetailsMap.get(hierarchy).get(NumericConstants.TWO).toString()), relationshipLevelDetailsMap.get(hierarchy)));
            }
        }

        return resultList;
    }

    /**
     *
     * @param projSelDTO
     * @param hierarchyNo
     * @param hierarchyIndicator
     * @param levelNo
     * @param detailsList
     * @return
     */
    public ProjectionVarianceDTO configureDetailsInDTO(ProjectionSelectionDTO projSelDTO, String hierarchyNo, String hierarchyIndicator, int levelNo, List detailsList) {
        Map<Object,Object> dataMap=new HashMap<>();
        ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
        dto.setLevelNo(Integer.valueOf(detailsList.get(NumericConstants.TWO).toString()));
        dto.setTreeLevelNo(levelNo);
        String hierarchy = hierarchyNo.contains(",") ? hierarchyNo.split(",")[0] : hierarchyNo;
        dataMap.put("format", projSelDTO.getDisplayFormat());
        dataMap.put("isExcel", Boolean.FALSE);
        Map<String, List> levelNameList = projSelDTO.isIsCustomHierarchy() ? projSelDTO.getSessionDTO().getCustomDescription() : projSelDTO.getSessionDTO().getHierarchyLevelDetails();
        dto.setGroup(CommonUtils.getDisplayFormattedName(hierarchy, hierarchyIndicator, levelNameList, projSelDTO.getSessionDTO(),dataMap ));
        dto.setLevelValue(detailsList.get(0).toString());
        dto.setHierarchyNo(hierarchyNo);
        dto.setHierarchyIndicator(hierarchyIndicator);
        if (dto.getHierarchyIndicator().equals("C")) {
            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
            dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
            dto.setDeductionHierarchyNo(projSelDTO.getDeductionHierarchyNo());
        } else if (dto.getHierarchyIndicator().equals("P")) {
            dto.setProductHierarchyNo(dto.getHierarchyNo());
            dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
            dto.setDeductionHierarchyNo(projSelDTO.getDeductionHierarchyNo());
        } else{
            dto.setDeductionHierarchyNo(dto.getHierarchyNo());
            dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
            dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        }
            dto.setParent(1);
        return dto;
    }

    public Leveldto getNextLevel(int levelNo, List<Leveldto> hierarchy, boolean isCustomHierarchy) {
        for (Leveldto dto : hierarchy) {
            if ((isCustomHierarchy && dto.getTreeLevelNo() == levelNo) || (dto.getLevelNo() == levelNo)) {
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

        List<ProjectionVarianceDTO> projDTOList = new ArrayList<>();
            if (started < maxRecord || pvsdto.getLevel().equals(TOTAL.getConstant())) {
                if (pvsdto.getLevel().equals(TOTAL.getConstant())) {
                    getResultsFromProcedure(pvsdto, BooleanConstant.getFalseFlag());
                    List<Object> currentPivotDiscount = new ArrayList<>();
                    if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {
                        currentPivotDiscount.addAll(pivotDiscountList.isEmpty() ? geDiscountResultsFromPrc(pvsdto) : pivotDiscountList);
                    }
                    List<ProjectionVarianceDTO> finalList = getCustomizedPivotTotalResults(pivotTotalList, pvsdto, pivotDiscountList);

                    for (int i = started; (i < finalList.size()) && (neededRecord > 0); neededRecord--, i++) {
                        projDTOList.add(finalList.get(i));
                    }
                } else if (parent instanceof ProjectionVarianceDTO) {
                    getResultsFromProcedure(pvsdto, BooleanConstant.getTrueFlag());
                    geDiscountResultsFromPrc(pvsdto);
                    List<ProjectionVarianceDTO> dto = getCustomizedPivotTotalResults(pivotTotalList, pvsdto, pivotDiscountList);

                    for (int i = started; (i < dto.size()) && (neededRecord > 0); neededRecord--, i++) {
                        projDTOList.add(dto.get(i));
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


    public void getResultsFromProcedure(PVSelectionDTO pvsdto, final boolean isDetail) {
        String frequency = pvsdto.getFrequency();
        List<String> projectionIdList = new ArrayList<>();
        pivotTotalList = new ArrayList<>();
        if (StringConstantsUtil.QUARTERLY_FREQ.equals(frequency)) {
            frequency = StringConstantsUtil.QUARTERLY_LABEL;
        } else if (StringConstantsUtil.SEMI_ANNUALLY_FREQ.equals(frequency)) {
            frequency = "SEMI-ANNUAL";
        } else if (StringConstantsUtil.MONTHLY_FREQ.equals(frequency)) {
            frequency = StringConstantsUtil.MONTHLY_LABEL;
        } else {
            frequency = StringConstantsUtil.ANNUAL_LABEL;
        }
        projectionIdList.add(String.valueOf(selectionDTO.getCurrentProjId()));
        for (Integer projId : pvsdto.getProjIdList()) {
            projectionIdList.add(String.valueOf(projId));
        }
        String projectionId = CommonUtils.collectionToString(projectionIdList, false);
        String salesInclusion = pvsdto.getSessionDTO().getSalesInclusion();
        String deductionInclusion = pvsdto.getSessionDTO().getDeductionInclusion();
        String ccps = null;
        if (isDetail) {
             ccps =getCCPIds(pvsdto);
             pvsdto.setCcpIds(ccps);
            pvsdto.getSession().getSalesInclusion();
        }
          String viewName=pvsdto.getView().equalsIgnoreCase("Custom")?D_INDICATOR:pvsdto.getView();
        Object[] orderedArg = {projectionId,pvsdto.getSessionDTO().getLevelHierarchyNo(), frequency,null,null,
           pvsdto.getSessionDTO().getUserId() ,pvsdto.getSessionDTO().getSessionId() ,viewName, 
           pvsdto.getSessionDTO().getCustomViewMasterSid(),salesInclusion,deductionInclusion};

        List< Object[]> gtsResult = CommonLogic.callProcedure(PRC_CFF_RESULTS, orderedArg);
        pivotTotalList.addAll(gtsResult);
    }

	public List<ProjectionVarianceDTO> getConfiguredProjectionVariance(Object parentId, PVSelectionDTO projSelDTO,
			int start, int offset) {
		try {
            LOGGER.debug("Inside getConfiguredProjectionVariance");
            List<ProjectionVarianceDTO> list;
            list = getProjVariance(projSelDTO, parentId, start, offset);
            LOGGER.debug("list size in getConfiguredProjectionVariance= {}", list.size());
            LOGGER.debug("Ending getConfiguredProjectionVariance");
            return list;
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return Collections.emptyList();

    }

    public int getConfiguredProjectionVarianceCount(Object parentId, PVSelectionDTO projSelDTO, boolean isLevelCount) {
        LOGGER.debug("In getConfiguredProjectionVarianceCount");
        int count = 0;
        setSelectionDTO(projSelDTO);
        setRightHeader(projSelDTO.getRightHeader());
        count += getProjVarianceCount(projSelDTO, parentId, isLevelCount);
        return count;

    }


    public List<ProjectionVarianceDTO> getCustomizedPivotTotalResults(final List<Object> results, PVSelectionDTO pvsdto,  List<Object> totalDiscount) {
        List<String> periodList = new ArrayList<>(pvsdto.getPeriodList());
        Map<String, String> periodListMap = new HashMap<>(pvsdto.getPeriodListMap());
        List<ProjectionVarianceDTO> projDTOList = new ArrayList<>();
        int frequencyDivision = pvsdto.getFrequencyDivision();
        Set<String> noOfDiscount = new HashSet<>();
        
        Map<Object, List<Object[]>> groupedResult = results.stream().map(obj -> (Object[]) obj)
                .collect(Collectors.groupingBy(x -> {
                    return new ArrayList<>(Arrays.asList(x[2], x[3]));
                }));
         Comparator c=new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (Integer.parseInt(String.valueOf(((List) o1).get(0))) > Integer.parseInt(String.valueOf(((List) o2).get(0)))) {
                    return 1;
                } else if (Integer.parseInt(String.valueOf(((List) o1).get(0))) < Integer.parseInt(String.valueOf(((List) o2).get(0)))) {
                    return -1;
                } else if ((Integer.parseInt(String.valueOf(((List) o1).get(1))) > Integer.parseInt(String.valueOf(((List) o2).get(1))))) {
                    return 1;
                } else if ((Integer.parseInt(String.valueOf(((List) o1).get(1))) < Integer.parseInt(String.valueOf(((List) o2).get(1))))) {
                    return -1;
                }
                return 0;
            }
        };
         List<Object> sortedKeys=new ArrayList<>(groupedResult.keySet());
         Collections.sort(sortedKeys, c);
        if (!results.isEmpty()) {
          for (Object keys : sortedKeys) {
                 List<Object[]> row = groupedResult.get(keys);
                 final Object[] obj = row.get(0);
           
                int year = Integer.parseInt(String.valueOf(obj[2]));
                int period = obj[3] != null ? Integer.parseInt(String.valueOf(obj[3])) : 0;
                List<String> common = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, year, period);
                String pcommonColumn = common.get(0);
                String commonHeader = common.get(1);
                pcommonColumn = getCommonColumn(frequencyDivision, pcommonColumn);
                if (periodList.contains(pcommonColumn)) {
                    periodList.remove(pcommonColumn);
                    ProjectionVarianceDTO projDTO = new ProjectionVarianceDTO();
                    projDTO.setGroup(commonHeader);
                    projDTO.setDfLevelNumber(commonHeader);
                    projDTO.setDfLevelName(commonHeader);
                    //Exfactory Sales
                    if ((pvsdto.isVarExFacSales())) {
                        if (pvsdto.isColValue()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.EX_FAC_VALUE, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.ONE, row);
                        }
                        if (pvsdto.isColVariance()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.EX_FAC_VARIANCE, Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.ONE, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            pvsdto.setConversionNeeded(false);
                            customizePivot(StringConstantsUtil.EX_FAC_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.ONE, row );
                        }
                    }
                    //CustExFacSales
                    if ((pvsdto.isVarExFacCustomer())) {
                       
                        if (pvsdto.isColValue()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot("CustExFacValue", Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.TWENTY_SEVEN, row );
                        }
                        if (pvsdto.isColVariance()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot("CustExFacVariance", Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.TWENTY_SEVEN, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            pvsdto.setConversionNeeded(false);
                            customizePivot("CustExFacPer", Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.TWENTY_SEVEN, row );
                        }
                    }
                    //Demand Sales
                    if ((pvsdto.isVarDemandSales())) {
                        if (pvsdto.isColValue()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.DEMAND_SALES_VALUE, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.SEVENTEEN, row );
                        }
                        if (pvsdto.isColVariance()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.DEMAND_SALES_VARIANCE, Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.SEVENTEEN, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            pvsdto.setConversionNeeded(false);
                            customizePivot(StringConstantsUtil.DEMAND_SALES_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.SEVENTEEN, row );
                        }
                    }
                    //AdjDemandSales
                    if ((pvsdto.isVarAdjDemand())) {
                        pvsdto.setConversionNeeded(false);
                        if (pvsdto.isColValue()) {
                            customizePivot("AdjDemandValue", Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.TWENTY_SIX, row );
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("AdjDemandVariance", Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.TWENTY_SIX, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("AdjDemandPer", Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.TWENTY_SIX, row );
                        }
                    }
                    //InvWithSummary
                    if ((pvsdto.isVarInvSales())) {
                       
                        if (pvsdto.isColValue()) {
                             pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.INV_WITH_VALUE, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.EIGHTEEN, row );
                        }
                        if (pvsdto.isColVariance()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.INV_WITH_VARIANCE, Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.EIGHTEEN, row );
                        }
                        if (pvsdto.isColPercentage()) {
                             pvsdto.setConversionNeeded(false);
                            customizePivot(StringConstantsUtil.INV_WITH_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.EIGHTEEN, row );
                        }
                    }
                    //InvWithDetails
                    if ((pvsdto.isVarIwDetails())) {
                        if (pvsdto.isColValue()) {
                             pvsdto.setConversionNeeded(true);
                            customizePivot("InvWithDetailsValue", Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.TWENTY_EIGHT, row );
                        }
                        if (pvsdto.isColVariance()) {
                             pvsdto.setConversionNeeded(true);
                            customizePivot("InvWithDetailsVariance", Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.TWENTY_EIGHT, row );
                        }
                        if (pvsdto.isColPercentage()) {
                             pvsdto.setConversionNeeded(false);
                            customizePivot("InvWithDetailsPer", Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.TWENTY_EIGHT, row );
                        }
                    }
                    //PerExFac
                    if ((pvsdto.isVarPerExFacSales())) {
                        pvsdto.setConversionNeeded(false);
                        if (pvsdto.isColValue()) {
                            customizePivot(StringConstantsUtil.PER_EX_FAC_VALUE, Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.EIGHT, row );
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot(StringConstantsUtil.PER_EX_FAC_VARIANCE, Constants.VARIANCE, pvsdto, projDTO,  RATE, NumericConstants.EIGHT, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot(StringConstantsUtil.PER_EX_FAC_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.EIGHT, row );
                        }
                    }
                    //PerCustExFac
                    if ((pvsdto.isVarPerExFacCustomer())) {
                        pvsdto.setConversionNeeded(false);
                        if (pvsdto.isColValue()) {
                            customizePivot("PerCustExFacValue", Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY, row );
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("PerCustExFacVariance", Constants.VARIANCE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("PerCustExFacPer", Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY, row );
                        }
                    }
                    //PerDemandSales
                    if ((pvsdto.isVarPerDemandSales())) {
                        pvsdto.setConversionNeeded(false);
                        if (pvsdto.isColValue()) {
                            customizePivot(StringConstantsUtil.PER_DEMAND_SALES_VALUE, Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.NINETEEN, row );
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot(StringConstantsUtil.PER_DEMAND_SALES_VARIANCE, Constants.VARIANCE, pvsdto, projDTO,  RATE, NumericConstants.NINETEEN, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot(StringConstantsUtil.PER_DEMAND_SALES_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.NINETEEN, row );
                        }
                    }
                    //PerAdjDemand
                    if ((pvsdto.isVarPerAdjDemand())) {
                        pvsdto.setConversionNeeded(false);
                        if (pvsdto.isColValue()) {
                            customizePivot("PerAdjDemandValue", Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.TWENTY_SIX, row );
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("PerAdjDemandSalesVariance", Constants.VARIANCE, pvsdto, projDTO,  RATE, NumericConstants.TWENTY_SIX, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("PerAdjDemandSalesPer", Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.TWENTY_SIX, row );
                        }
                    }
                    //PerInvWith
                    if ((pvsdto.isVarPerInvSales())) {
                        pvsdto.setConversionNeeded(false);
                        if (pvsdto.isColValue()) {
                            customizePivot(StringConstantsUtil.PER_INV_WITH_VALUE, Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.TWENTY, row );
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot(StringConstantsUtil.PER_INV_WITH_VARIANCE, Constants.VARIANCE, pvsdto, projDTO,  RATE, NumericConstants.TWENTY, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot(StringConstantsUtil.PER_INV_WITH_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.TWENTY, row );
                        }
                    }
                    //PerInvWithDetails
                    if ((pvsdto.isVarPerIwDetails())) {
                        pvsdto.setConversionNeeded(false);
                        if (pvsdto.isColValue()) {
                            customizePivot("PerInvWithDetailsValue", Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_ONE, row );
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("PerInvWithDetailsVariance", Constants.VARIANCE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_ONE, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("PerInvWithDetailsPer", Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_ONE, row );
                        }
                    }
                    //ContractSalesWAC
                    if ((pvsdto.isVarContractsales())) {
                        pvsdto.setConversionNeeded(true);
                        if (pvsdto.isColValue()) {
                            customizePivot(StringConstantsUtil.CONTRACT_SALES_WAC_VALUE, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.FOUR, row );
                        }
                        if (pvsdto.isColVariance()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.CONTRACT_SALES_WAC_VAR, Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.FOUR, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            pvsdto.setConversionNeeded(false);
                            customizePivot(StringConstantsUtil.CONTRACT_SALES_WAC_VAR_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.FOUR, row );
                        }
                    }
                    //ContractUnits
                    if ((pvsdto.isVarContractUnits())) {
                        pvsdto.setConversionNeeded(false);
                        if (pvsdto.isColValue()) {
                            customizePivot(StringConstantsUtil.CONTRACT_UNITS_VALUE, Constants.VALUE, pvsdto, projDTO,  AMOUNT_UNITS, NumericConstants.FIVE, row );
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot(StringConstantsUtil.CONTRACT_UNITS_VAR, Constants.VARIANCE, pvsdto, projDTO,  AMOUNT_UNITS, NumericConstants.FIVE, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot(StringConstantsUtil.CONTRACT_UNITS_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.FIVE, row );
                        }
                    }
                    //DiscountSales
                    if ((pvsdto.isVarDisRate())) {
                        pvsdto.setConversionNeeded(true);
                        if (pvsdto.isColValue()) {
                            customizePivot(StringConstantsUtil.DISCOUNT_SALES_VALUE, Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.SIX, row );
                        }
                        if (pvsdto.isColVariance()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.DISCOUNT_SALES_VAR, Constants.VARIANCE, pvsdto, projDTO,  RATE, NumericConstants.SIX, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            pvsdto.setConversionNeeded(false);
                            customizePivot(StringConstantsUtil.DISCOUNT_SALES_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.SIX, row );
                        }
                    }
                    //DiscountAmount
                    if ((pvsdto.isVarDisAmount())) {
                       
                        if (pvsdto.isColValue()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.DISCOUNT_AMOUNT_VALUE, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.SEVEN, row );
                        }
                        if (pvsdto.isColVariance()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.DISCOUNT_AMOUNT_VAR, Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.SEVEN, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            pvsdto.setConversionNeeded(false);
                            customizePivot(StringConstantsUtil.DISCOUNT_AMOUNT_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.SEVEN, row );
                        }
                    }
                    //RPU
                    if ((pvsdto.isVarRPU())) {
                        pvsdto.setConversionNeeded(false);
                        if (pvsdto.isColValue()) {
                            customizePivot(StringConstantsUtil.RPU_VALUE, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.TWENTY_THREE, row );
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot(StringConstantsUtil.RPU_VARIANCE, Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.TWENTY_THREE, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot(StringConstantsUtil.RPU_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.TWENTY_THREE, row );
                        }
                    }
                    //DiscountPerExFactory
                    if ((pvsdto.isDiscountPerExFactory())) {
                        pvsdto.setConversionNeeded(false);
                        if (pvsdto.isColValue()) {
                            customizePivot(StringConstantsUtil.DISCOUNT_PER_EX_FACTORY_VALUE, Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_THREE, row );
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot(StringConstantsUtil.DISCOUNT_PER_EX_FACTORY_VAR, Constants.VARIANCE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_THREE, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot(StringConstantsUtil.DISCOUNT_PER_EX_FACTORY_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_THREE, row );
                        }
                    }
                    //NetSales
                    if ((pvsdto.isVarNetSales())) {
                        if (pvsdto.isColValue()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.NET_SALES_VALUE, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.SIXTEEN, row );
                        }
                        if (pvsdto.isColVariance()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.NET_SALES_VARIANCE, Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.SIXTEEN, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            pvsdto.setConversionNeeded(false);
                            customizePivot(StringConstantsUtil.NET_SALES_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.SIXTEEN, row );
                        }
                    }
                    //Net Sales ExFactory Percentage
                    if ((pvsdto.isNetSalesExFactory())) {
                        pvsdto.setConversionNeeded(false);
                        if (pvsdto.isColValue()) {
                            customizePivot("NetSalesExFactoryValue", Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_TWO, row );
                        }
                        if (pvsdto.isColVariance()) {
                            customizePivot("NetSalesExFactoryVariance", Constants.VARIANCE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_TWO, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            customizePivot("NetSalesExFactoryPer", Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_TWO, row );
                        }
                    }

                    if (ConstantsUtil.DETAIL.equals(pvsdto.getLevel()) && (Constants.PRODUCT_LABEL.equals(pvsdto.getView()) || Constants.LabelConstants.CUSTOM.toString().equals(pvsdto.getView()))
                            && P.equals(pvsdto.getHierarchyIndicator())) {
                        /**
                         * Net Ex-Factory Sales
                         */
                        if ((pvsdto.isNetExFactorySales())) {
                            if (pvsdto.isColValue()) {
                                pvsdto.setConversionNeeded(true);
                                customizePivot(ConstantsUtil.NET_EXFACT_SALES_COLUMN_VALUE, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.THIRTY_FOUR, row );
                            }
                            if (pvsdto.isColVariance()) {
                                pvsdto.setConversionNeeded(true);
                                customizePivot(ConstantsUtil.NET_EXFACT_SALES_COLUMN_VARIANCE, Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.THIRTY_FOUR, row );
                            }
                            if (pvsdto.isColPercentage()) {
                                pvsdto.setConversionNeeded(false);
                                customizePivot(ConstantsUtil.NET_EXFACT_SALES_COLUMN_PER_CHANGE, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_FOUR, row );
                            }
                        }
                        /**
                         * Net Ex-Factory Sales as % of Ex-Factory Sales
                         */
                        if ((pvsdto.isNetExFactorySalesPerExFactory())) {
                            pvsdto.setConversionNeeded(false);
                            if (pvsdto.isColValue()) {
                                customizePivot(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VALUE, Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_FIVE, row );
                            }
                            if (pvsdto.isColVariance()) {
                                customizePivot(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_VARIANCE, Constants.VARIANCE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_FIVE, row );
                            }
                            if (pvsdto.isColPercentage()) {
                                customizePivot(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT_COLUMN_PER_CHANGE, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.THIRTY_FIVE, row );
                            }
                        }
                    }

                    //COGC
                    if ((pvsdto.isVarCOGC())) {
                        if (pvsdto.isColValue()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.COGC_VALUE, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.TWENTY_FOUR, row );
                        }
                        if (pvsdto.isColVariance()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.COGC_VARIANCE, Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.TWENTY_FOUR, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            pvsdto.setConversionNeeded(false);
                            customizePivot(StringConstantsUtil.COGC_PER, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.TWENTY_FOUR, row );
                        }
                    }
                    //NetProfit
                    if ((pvsdto.isVarNetProfit())) {
                        if (pvsdto.isColValue()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.NET_PROFIT_VALUE, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.TWENTY_FIVE, row );
                        }
                        if (pvsdto.isColVariance()) {
                            pvsdto.setConversionNeeded(true);
                            customizePivot(StringConstantsUtil.NET_PROFIT_VARIANCE, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.TWENTY_FIVE, row );
                        }
                        if (pvsdto.isColPercentage()) {
                            pvsdto.setConversionNeeded(false);
                            customizePivot(StringConstantsUtil.NET_PROFIT_PER, Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.TWENTY_FIVE, row );
                        }
                    }
                    if (!pvsdto.getDiscountLevel().equals(TOTAL_DISCOUNT.getConstant())) {

                        // Individual discount level
                        
                        for (Object discountsName : totalDiscount) {
                            final Object[] disc = (Object[]) discountsName;
                            if (disc[NumericConstants.THIRTEEN] != null) {
                                noOfDiscount.add(String.valueOf(disc[NumericConstants.THIRTEEN]));
                            }
                        }
                        @SuppressWarnings("unchecked")
                        Map<Object, List<Object[]>> groupedDiscResult = totalDiscount.stream().map(o -> (Object[]) o)
                                .collect(Collectors.groupingBy(x -> {
                                    return new ArrayList<>(Arrays.asList(x[1], x[2]));
                                }));
                         for (Map.Entry<Object, List<Object[]>> discount : groupedDiscResult.entrySet()) {
                             
                             List<Object[]> discountRow = discount.getValue();
                             final Object[] disobj = discountRow.get(0);
                            int dyear = Integer.parseInt(String.valueOf(disobj[1]));
                            int dperiod = Integer.parseInt(String.valueOf(disobj[2]));
                            List<String> dcommon = HeaderUtils.getCommonColumnHeaderForPV(frequencyDivision, dyear, dperiod);
                            String dcommonColumn = dcommon.get(0);
                            dcommonColumn = getCommonColumn(dcommonColumn, frequencyDivision);
                            List<String> discountNames = pvsdto.getDiscountLevel().equals("Program") ? pvsdto.getDiscountNameList() : pvsdto.getDiscountNameCFF();
                            List<String> dedNames = !pvsdto.getDeductionLevelFilter().isEmpty() ? pvsdto.getDeductionLevelCaptions() : discountNames;
                            if (pcommonColumn.equals(dcommonColumn)) {
                                for (int disc = 0; disc < noOfDiscount.size(); disc++) {
                                    String disName = String.valueOf(disobj[disobj.length - 2]);

                                    String head = disName.replace(" ", StringUtils.EMPTY) + dedNames.indexOf(disName);

                                    if ((pvsdto.isVarDisAmount())) {
                                        if (pvsdto.isColValue()) {

                                            pvsdto.setConversionNeeded(true);
                                            customizePivot(StringConstantsUtil.DISCOUNT_AMOUNT_VALUE + head, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.THREE, discountRow );
                                        }
                                        if (pvsdto.isColVariance()) {
                                            pvsdto.setConversionNeeded(true);
                                            customizePivot(StringConstantsUtil.DISCOUNT_AMOUNT_VAR + head, Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.THREE, discountRow );
                                        }
                                        if (pvsdto.isColPercentage()) {
                                            pvsdto.setConversionNeeded(false);
                                            customizePivot(StringConstantsUtil.DISCOUNT_AMOUNT_PER + head, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.THREE, discountRow );
                                        }
                                    }
                                    if ((pvsdto.isVarDisRate())) {
                                        pvsdto.setConversionNeeded(false);
                                        if (pvsdto.isColValue()) {
                                            customizePivot(StringConstantsUtil.DISCOUNT_SALES_VALUE + head, Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.FOUR, discountRow );
                                        }
                                        if (pvsdto.isColVariance()) {
                                            customizePivot(StringConstantsUtil.DISCOUNT_SALES_VAR + head, Constants.VARIANCE, pvsdto, projDTO,  RATE, NumericConstants.FOUR, discountRow );
                                        }
                                        if (pvsdto.isColPercentage()) {
                                            customizePivot(StringConstantsUtil.DISCOUNT_SALES_PER + head, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.FOUR, discountRow );
                                        }
                                    }

                                    if ((pvsdto.isVarRPU())) {
                                        pvsdto.setConversionNeeded(false);
                                        if (pvsdto.isColValue()) {
                                            customizePivot(StringConstantsUtil.RPU_VALUE + head, Constants.VALUE, pvsdto, projDTO,  AMOUNT, NumericConstants.TEN, discountRow );
                                        }
                                        if (pvsdto.isColVariance()) {
                                            customizePivot(StringConstantsUtil.RPU_VARIANCE + head, Constants.VARIANCE, pvsdto, projDTO,  AMOUNT, NumericConstants.TEN, discountRow );
                                        }
                                        if (pvsdto.isColPercentage()) {
                                            customizePivot(StringConstantsUtil.RPU_PER + head, Constants.CHANGE, pvsdto, projDTO,  AMOUNT, NumericConstants.TEN, discountRow );
                                        }
                                    }
                                    //discount per of exfactory
                                    if ((pvsdto.isDiscountPerExFactory())) {
                                        pvsdto.setConversionNeeded(false);
                                        if (pvsdto.isColValue()) {
                                            customizePivot(StringConstantsUtil.DISCOUNT_PER_EX_FACTORY_VALUE + head, Constants.VALUE, pvsdto, projDTO,  RATE, NumericConstants.ELEVEN, discountRow );
                                        }
                                        if (pvsdto.isColVariance()) {
                                            customizePivot(StringConstantsUtil.DISCOUNT_PER_EX_FACTORY_VAR + head, Constants.VARIANCE, pvsdto, projDTO,  RATE, NumericConstants.ELEVEN, discountRow );
                                        }
                                        if (pvsdto.isColPercentage()) {
                                            customizePivot(StringConstantsUtil.DISCOUNT_PER_EX_FACTORY_PER + head, Constants.CHANGE, pvsdto, projDTO,  RATE, NumericConstants.ELEVEN, discountRow );
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
        columnList.remove(StringConstantsUtil.GROUP_PROPERTY);
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
            totalDTO.setGroup(CFF_TOTAL);
            projDTOList.add(0, totalDTO);

        }
        return projDTOList;
    }

    /**
     * get Customized period variance
     *
     * @return List
     */
    public List<ProjectionVarianceDTO> getCustPeriodVariance(final List<Object> gtsList, final PVSelectionDTO pvsdto, final ProjectionVarianceDTO parentDto) {
        List<ProjectionVarianceDTO> projectionVarianceDTO = new ArrayList<>(NumericConstants.FIFTEEN);
        boolean isDetail = false;
        if (pvsdto.getLevel().equals(DETAIL)) {
            isDetail = true;
        } else {
            ProjectionVarianceDTO dto = new ProjectionVarianceDTO();
            dto.setGroup(CFF_TOTAL);
            dto.addStringProperties(DF_LEVEL_NUMBER, CFF_TOTAL);
            dto.addStringProperties(DF_LEVEL_NAME, CFF_TOTAL);
            projectionVarianceDTO.add(dto);
        }
        // ExFac Sales 
        if (pvsdto.isVarExFacSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO exFacValue = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(),Constants.VALUE, gtsList, NumericConstants.ONE, pvsdto, AMOUNT);
                projectionVarianceDTO.add(exFacValue);
            }
            if (pvsdto.isColVariance()) {
                 pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO exFacVar = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(),Constants.VARIANCE, gtsList, NumericConstants.ONE, pvsdto, AMOUNT);
                projectionVarianceDTO.add(exFacVar);
            }
            if (pvsdto.isColPercentage()) {
                 pvsdto.setConversionNeeded(false);
                ProjectionVarianceDTO exFacPer = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_PRODUCT.toString(),Constants.CHANGE, gtsList, NumericConstants.ONE, pvsdto, RATE);
                projectionVarianceDTO.add(exFacPer);
            }
        }
        // ExFac Customer
        if (pvsdto.isVarExFacCustomer()) {
           
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO custExFacValue = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(),Constants.VALUE, gtsList, NumericConstants.TWENTY_SEVEN, pvsdto, AMOUNT);
                projectionVarianceDTO.add(custExFacValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO custExFacVar = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(),Constants.VARIANCE, gtsList, NumericConstants.TWENTY_SEVEN, pvsdto, AMOUNT);
                projectionVarianceDTO.add(custExFacVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                ProjectionVarianceDTO custExFacPer = getCommonCustomizedDTO(Constants.PVVariables.EX_FACTORY_CUSTOMER.toString(),Constants.CHANGE, gtsList, NumericConstants.TWENTY_SEVEN, pvsdto, RATE);
                projectionVarianceDTO.add(custExFacPer);
            }
        }

        // Demand Sales
        if (pvsdto.isVarDemandSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO demandValue = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(),Constants.VALUE, gtsList, NumericConstants.SEVENTEEN, pvsdto, AMOUNT);
                projectionVarianceDTO.add(demandValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO demandVar = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(),Constants.VARIANCE, gtsList, NumericConstants.SEVENTEEN, pvsdto, AMOUNT);
                projectionVarianceDTO.add(demandVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                ProjectionVarianceDTO demandPer = getCommonCustomizedDTO(Constants.PVVariables.DEMAND.toString(),Constants.CHANGE, gtsList, NumericConstants.SEVENTEEN, pvsdto, RATE);
                projectionVarianceDTO.add(demandPer);
            }
        }
        // Adjusted Demand
        if (pvsdto.isVarAdjDemand()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                ProjectionVarianceDTO adjDemandValue = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(),Constants.VALUE, gtsList, NumericConstants.TWENTY_SIX, pvsdto, AMOUNT);
                projectionVarianceDTO.add(adjDemandValue);
            }
            if (pvsdto.isColVariance()) {
                ProjectionVarianceDTO adjDemandVar = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(), Constants.VARIANCE,gtsList, NumericConstants.TWENTY_SIX, pvsdto, AMOUNT);
                projectionVarianceDTO.add(adjDemandVar);
            }
            if (pvsdto.isColPercentage()) {
                ProjectionVarianceDTO adjDemandPer = getCommonCustomizedDTO(Constants.PVVariables.ADJUSTED_DEMAND.toString(),Constants.CHANGE, gtsList, NumericConstants.TWENTY_SIX, pvsdto, RATE);
                projectionVarianceDTO.add(adjDemandPer);
            }
        }
        // Inventory Sales Withdrawal
        if (pvsdto.isVarInvSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                pvsdto.setVarIndicator(VALUE.getConstant());
                ProjectionVarianceDTO invWithValue = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(),Constants.VALUE, gtsList, NumericConstants.EIGHTEEN, pvsdto, AMOUNT);
                projectionVarianceDTO.add(invWithValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO invWithVar = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(),Constants.VARIANCE, gtsList, NumericConstants.EIGHTEEN, pvsdto, AMOUNT);
                projectionVarianceDTO.add(invWithVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                ProjectionVarianceDTO invWithPer = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_SUMMARY.toString(),Constants.CHANGE, gtsList, NumericConstants.EIGHTEEN, pvsdto, RATE);
                projectionVarianceDTO.add(invWithPer);
            }
        }
        // iw Details
        if (pvsdto.isVarIwDetails()) {
           
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO iwDetialsValue = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(),Constants.VALUE, gtsList, NumericConstants.TWENTY_EIGHT, pvsdto, AMOUNT);
                projectionVarianceDTO.add(iwDetialsValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO iwDetialsVar = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(),Constants.VARIANCE, gtsList, NumericConstants.TWENTY_EIGHT, pvsdto, AMOUNT);
                projectionVarianceDTO.add(iwDetialsVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                ProjectionVarianceDTO iwDetialsPer = getCommonCustomizedDTO(Constants.PVVariables.INVENTORY_DETAILS.toString(),Constants.CHANGE, gtsList, NumericConstants.TWENTY_EIGHT, pvsdto, RATE);
                projectionVarianceDTO.add(iwDetialsPer);
            }
        }

        //% Of Ex Factory Product
        if (pvsdto.isVarPerExFacSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(),Constants.VALUE, gtsList, NumericConstants.EIGHT, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(),Constants.VARIANCE, gtsList, NumericConstants.EIGHT, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_PRODUCT.toString(),Constants.CHANGE, gtsList, NumericConstants.EIGHT, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Ex factory customer
        if (pvsdto.isVarPerExFacCustomer()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(),Constants.VALUE, gtsList, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(),Constants.VARIANCE, gtsList, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_EX_FACTORY_CUSTOMER.toString(),Constants.CHANGE, gtsList, NumericConstants.THIRTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Demand Sales
        if (pvsdto.isVarPerDemandSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(),Constants.VALUE, gtsList, NumericConstants.NINETEEN, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(),Constants.VARIANCE, gtsList, NumericConstants.NINETEEN, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_DEMAND.toString(),Constants.CHANGE, gtsList, NumericConstants.NINETEEN, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Adjusted Demand 
        if (pvsdto.isVarPerAdjDemand()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(),Constants.VALUE, gtsList, NumericConstants.TWENTY_NINE, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(),Constants.VARIANCE, gtsList, NumericConstants.TWENTY_NINE, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_ADJUSTED_DEMAND.toString(),Constants.CHANGE, gtsList, NumericConstants.TWENTY_NINE, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Inv Summary
        if (pvsdto.isVarPerInvSales()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(),Constants.VALUE, gtsList, NumericConstants.TWENTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(),Constants.VARIANCE, gtsList, NumericConstants.TWENTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_SUMMARY.toString(),Constants.CHANGE, gtsList, NumericConstants.TWENTY, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        // % Of Inventry withdrawal Detials 
        if (pvsdto.isVarPerIwDetails()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(),Constants.VALUE, gtsList, NumericConstants.THIRTY_ONE, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColVariance()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(),Constants.VARIANCE, gtsList, NumericConstants.THIRTY_ONE, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
            if (pvsdto.isColPercentage()) {
                ProjectionVarianceDTO pob = getCommonCustomizedDTO(Constants.PVVariables.PER_INVENORY_WITHDRAW_DETAILS.toString(),Constants.CHANGE, gtsList, NumericConstants.THIRTY_ONE, pvsdto, RATE);
                projectionVarianceDTO.add(pob);
            }
        }
        //Contract Sales
        if (pvsdto.isVarContractsales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO salesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(),Constants.VALUE, gtsList, NumericConstants.FOUR, pvsdto, AMOUNT);
                projectionVarianceDTO.add(salesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO salesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(),Constants.VARIANCE, gtsList, NumericConstants.FOUR, pvsdto, AMOUNT);
                projectionVarianceDTO.add(salesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                ProjectionVarianceDTO salesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_SALES.toString(),Constants.CHANGE, gtsList, NumericConstants.FOUR, pvsdto, RATE);
                projectionVarianceDTO.add(salesPer);
            }
        }
        //Contract Units
        if (pvsdto.isVarContractUnits()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(),Constants.VALUE, gtsList, NumericConstants.FIVE, pvsdto, AMOUNT_UNITS);
                projectionVarianceDTO.add(units);
            }
            if (pvsdto.isColVariance()) {
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(),Constants.VARIANCE, gtsList, NumericConstants.FIVE, pvsdto, AMOUNT_UNITS);
                projectionVarianceDTO.add(units);
            }
            if (pvsdto.isColPercentage()) {
                ProjectionVarianceDTO units = getCommonCustomizedDTO(Constants.PVVariables.VAR_CONTRACT_UNITS.toString(),Constants.CHANGE, gtsList, NumericConstants.FIVE, pvsdto, RATE);
                projectionVarianceDTO.add(units);
            }
        }
        //Discount $ 
        if (pvsdto.isVarDisAmount()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(),Constants.VALUE, gtsList, NumericConstants.SIX, pvsdto, AMOUNT);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(),Constants.VARIANCE, gtsList, NumericConstants.SIX, pvsdto, AMOUNT);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                ProjectionVarianceDTO discountDol = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_AMOUNT.toString(),Constants.CHANGE, gtsList, NumericConstants.SIX, pvsdto, RATE);
                discountDol = setDataObjects(discountDol, parentDto, pvsdto);
                projectionVarianceDTO.add(discountDol);
            }
        }
        //Discount % 
        if (pvsdto.isVarDisRate()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(),Constants.VALUE, gtsList, NumericConstants.SEVEN, pvsdto, RATE);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
            if (pvsdto.isColVariance()) {
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(),Constants.VARIANCE, gtsList, NumericConstants.SEVEN, pvsdto, RATE);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
            if (pvsdto.isColPercentage()) {
                ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_DIS_RATE.toString(),Constants.CHANGE, gtsList, NumericConstants.SEVEN, pvsdto, RATE);
                discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                projectionVarianceDTO.add(discountPer);
            }
        }
        // RPU
        if (pvsdto.isVarRPU()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(),Constants.VALUE, gtsList, NumericConstants.TWENTY_THREE, pvsdto, AMOUNT);
                netSalesValue = setDataObjects(netSalesValue, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(),Constants.VARIANCE, gtsList, NumericConstants.TWENTY_THREE, pvsdto, AMOUNT);
                netSalesVar = setDataObjects(netSalesVar, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_RPU.toString(),Constants.CHANGE, gtsList, NumericConstants.TWENTY_THREE, pvsdto, RATE);
                netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesPer);
            }
        }

        // DiscountPerExFactory
        if (pvsdto.isDiscountPerExFactory()) {
            pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(),Constants.VALUE, gtsList, NumericConstants.THIRTY_THREE, pvsdto, RATE);
                netSalesValue = setDataObjects(netSalesValue, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(),Constants.VARIANCE, gtsList, NumericConstants.THIRTY_THREE, pvsdto, RATE);
                netSalesVar = setDataObjects(netSalesVar, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.DISCOUNT_PER_EX_FACTORY.toString(),Constants.CHANGE, gtsList, NumericConstants.THIRTY_THREE, pvsdto, RATE);
                netSalesPer = setDataObjects(netSalesPer, parentDto, pvsdto);
                projectionVarianceDTO.add(netSalesPer);
            }
        }

        //NetSales 
        if (pvsdto.isVarNetSales()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(),Constants.VALUE, gtsList, NumericConstants.SIXTEEN, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(),Constants.VARIANCE, gtsList, NumericConstants.SIXTEEN, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_NETSALES.toString(),Constants.CHANGE, gtsList, NumericConstants.SIXTEEN, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesPer);
            }
        }

        //NetSalesExFactory 
        if (pvsdto.isNetSalesExFactory()) {
             pvsdto.setConversionNeeded(false);
            if (pvsdto.isColValue()) {
              ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(),Constants.VALUE, gtsList, NumericConstants.THIRTY_ONE, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
              ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(),Constants.VARIANCE, gtsList, NumericConstants.THIRTY_ONE, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
               
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.NET_SALES_PER_EX_FACTORY.toString(),Constants.CHANGE, gtsList, NumericConstants.THIRTY_ONE, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesPer);
            }
        }

        if (isDetail && (Constants.PRODUCT_LABEL.equals(pvsdto.getView()) || Constants.LabelConstants.CUSTOM.toString().equals(pvsdto.getView())) && P.equals(pvsdto.getHierarchyIndicator())) {
            /**
             * Net Ex-Factory Sales
             */
            
            if (pvsdto.isNetExFactorySales()) {
                if (pvsdto.isColValue()) {
                    pvsdto.setConversionNeeded(true);
                    ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(ConstantsUtil.NET_EXFACT_SALES,Constants.VALUE, gtsList, NumericConstants.THIRTY_FOUR, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(netSalesValue);
                }
                if (pvsdto.isColVariance()) {
                    pvsdto.setConversionNeeded(true);
                    ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(ConstantsUtil.NET_EXFACT_SALES,Constants.VARIANCE, gtsList, NumericConstants.THIRTY_FOUR, pvsdto, AMOUNT);
                    projectionVarianceDTO.add(netSalesVar);
                }
                if (pvsdto.isColPercentage()) {
                    pvsdto.setConversionNeeded(false);
                    ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(ConstantsUtil.NET_EXFACT_SALES,Constants.CHANGE, gtsList, NumericConstants.THIRTY_FOUR, pvsdto, RATE);
                    projectionVarianceDTO.add(netSalesPer);
                }
            }
            /**
             * Net Ex-Factory Sales as % of Ex-Factory Sales
             */
            if (pvsdto.isNetExFactorySalesPerExFactory()) {
                pvsdto.setConversionNeeded(false);
                if (pvsdto.isColValue()) {
                    ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT,Constants.VALUE, gtsList, NumericConstants.THIRTY_FIVE, pvsdto, RATE);
                    discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountPer);
                }
                if (pvsdto.isColVariance()) {
                    ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT,Constants.VARIANCE, gtsList, NumericConstants.THIRTY_FIVE, pvsdto, RATE);
                    discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountPer);
                }
                if (pvsdto.isColPercentage()) {
                    ProjectionVarianceDTO discountPer = getCommonCustomizedDTO(ConstantsUtil.NET_EXFACT_SALES_PER_EXFACT,Constants.CHANGE, gtsList, NumericConstants.THIRTY_FIVE, pvsdto, RATE);
                    discountPer = setDataObjects(discountPer, parentDto, pvsdto);
                    projectionVarianceDTO.add(discountPer);
                }
            }
        }

        //COGS
        if (pvsdto.isVarCOGC()) {
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(),Constants.VALUE, gtsList, NumericConstants.TWENTY_FOUR, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(),Constants.VARIANCE, gtsList, NumericConstants.TWENTY_FOUR, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_COGS.toString(),Constants.CHANGE, gtsList, NumericConstants.TWENTY_FOUR, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        //Net Profit
        if (pvsdto.isVarNetProfit()) {
            
            if (pvsdto.isColValue()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO netSalesValue = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(),Constants.VALUE, gtsList, NumericConstants.TWENTY_FIVE, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesValue);
            }
            if (pvsdto.isColVariance()) {
                pvsdto.setConversionNeeded(true);
                ProjectionVarianceDTO netSalesVar = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(),Constants.VARIANCE, gtsList, NumericConstants.TWENTY_FIVE, pvsdto, AMOUNT);
                projectionVarianceDTO.add(netSalesVar);
            }
            if (pvsdto.isColPercentage()) {
                pvsdto.setConversionNeeded(false);
                ProjectionVarianceDTO netSalesPer = getCommonCustomizedDTO(Constants.PVVariables.VAR_NET_PROFITE.toString(),Constants.CHANGE, gtsList, NumericConstants.TWENTY_FIVE, pvsdto, RATE);
                projectionVarianceDTO.add(netSalesPer);
            }
        }
        return projectionVarianceDTO;
    }

    public ProjectionVarianceDTO getCustomisedProjectionResultsDiscount(List<Object> list, String discountName, PVSelectionDTO projSelDTO) {
        LOGGER.debug("Inside getCustomisedProjectionResultsDiscount");

        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(StringConstantsUtil.GROUP_PROPERTY);
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
                        String monthName = HeaderUtils.getMonthForInt(Integer.parseInt(String.valueOf(discountRow[1])) - 1);
                        column = monthName + discountRow[0];
                    }
                    column1 = column + CURRENT + projSelDTO.getCurrentProjId();
                    if (projSelDTO.hasColumn(column1)) {
                        String value = "" + discountRow[NumericConstants.THREE];
                        if (projSelDTO.getSales().contains(StringConstantsUtil.SALES1)) {
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
                            if (projSelDTO.getSales().contains(StringConstantsUtil.SALES1)) {
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
            if (projSelDTO.getSales().contains(StringConstantsUtil.SALES1)) {
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
            dto.setDeductionHierarchyNo(pvsdto.getDeductionHierarchyNo());
        } else if (dto.getHierarchyIndicator().equals(P)) {
            dto.setProductHierarchyNo(dto.getHierarchyNo());
            dto.setCustomerHierarchyNo(pvsdto.getCustomerHierarchyNo());
            dto.setDeductionHierarchyNo(pvsdto.getDeductionHierarchyNo());
        } else{
            dto.setProductHierarchyNo(pvsdto.getProductHierarchyNo());
            dto.setCustomerHierarchyNo(pvsdto.getCustomerHierarchyNo());
            dto.setDeductionHierarchyNo(dto.getHierarchyNo());
        }
        return dto;
    }

    public Map<Object, Object> getNMProjectionSelection(final int projectionId, final String screenName) {
        List<Object[]> list = new ArrayList<>();
        Map<Object, Object> map = new HashMap<>();
        DynamicQuery query = NmProjectionSelectionLocalServiceUtil.dynamicQuery();
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
                    Object[] obj = list.get(i);
                    map.put(obj[0], obj[1]);
                }
            }
            return map;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return null;
    }

    public String getFormattedValue(DecimalFormat format, String value) {
        if (value.contains(NULL.getConstant())) {
            value = format.format(Double.valueOf(ZERO));
        } else {
            value = format.format(Double.valueOf(value));
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
        List<NmProjectionSelection> list = new ArrayList<>();
        DynamicQuery query = NmProjectionSelectionLocalServiceUtil.dynamicQuery();
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
            LOGGER.error(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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
        List<Object[]> resultList = HelperTableLocalServiceUtil.executeSelectQuery(query);
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
                    } catch (InterruptedException e) {
                        LOGGER.error(e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        LOGGER.debug("End of  waitForProcedure  Method");
    }

    /**
     * Common Customization
     *
     * @param pvsdto
     * @return
     */
    public ProjectionVarianceDTO getCommonCustomizedDTO(final String groupName, String varibaleCat, List<Object> gtsList,
            final int totalListPostion, PVSelectionDTO pvsdto, final DecimalFormat format) {
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        try {
            LOGGER.debug("Inside getExFactorySales");
            int frequencyDivision = pvsdto.getFrequencyDivision();
            boolean isPer = format.equals(RATE);
            pvDTO.setGroup(groupName.concat(varibaleCat));
            pvDTO.setDfLevelNumber(groupName.concat(varibaleCat));
            pvDTO.setDfLevelName(groupName.concat(varibaleCat));
            if (!pvsdto.getDiscountLevel().equals("Total Discount") && (pvDTO.getGroup().contains("Discount") || pvDTO.getGroup().contains("RPU"))) {
                pvDTO.setParent(1);
            }
            pvDTO.setCcpIds(pvsdto.getLevel().equals(TOTAL.getConstant()) ? StringUtils.EMPTY : pvsdto.getCcpIds());
            List<Integer> priorList = new ArrayList<>(pvsdto.getProjIdList());

                Map<Object, List<Object[]>> groupedResult = gtsList.stream().map(obj -> (Object[]) obj)
                     .collect(Collectors.groupingBy(x -> {
                            return new ArrayList<>(Arrays.asList( x[2], x[3]));
                        }));
                for (Map.Entry<Object, List<Object[]>> entry : groupedResult.entrySet()) {
                    Object period ;
                    Object year ;
                    List<Object[]> list = entry.getValue();

                    final Object[] obj = list.get(0);
                     Object[] proj=null;
                     Object[] actual=null;
                    if(list.size()>1){
                    if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                        actual = list.get(0);
                        proj = list.get(1);
                        period=actual[3];
                        year=actual[2];
                    } else {
                        actual = list.get(1);
                        proj = list.get(0);
                        period=proj[3];
                        year=proj[2];
                    }
                    }else{
                      Object[] empty= IntStream.rangeClosed(0, obj.length).boxed().map(e -> 0).toArray();
                      if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                          
                        actual = list.get(0);
                        period=actual[3];
                        year=actual[2];
                        proj = empty;
                    } else {
                        actual = empty;
                        proj = list.get(0);
                        period=proj[3];
                        year=proj[2];
                    }  
                    }
                    String commonColumn = StringUtils.EMPTY;
                   
                    if (frequencyDivision == NumericConstants.FOUR) {
                        commonColumn = "Q" + period + StringUtils.EMPTY + year;
                    } else if (frequencyDivision == NumericConstants.TWO) {
                        commonColumn = "S" + period + StringUtils.EMPTY + year;
                    } else if (frequencyDivision == 1) {
                        commonColumn = StringUtils.EMPTY + year;
                    } else if (frequencyDivision == NumericConstants.TWELVE) {
                        String monthName = HeaderUtils.getMonthForInt(Integer.parseInt(String.valueOf(period)) - 1);
                        commonColumn = monthName.toLowerCase(Locale.ENGLISH) + year;
                    }
                    PVCommonLogic.customizePeriodV2(commonColumn, varibaleCat, pvsdto, pvDTO, format, totalListPostion, actual, proj, isPer,false);
                    for (int j = 0; j < priorList.size(); j++) {
                        PVCommonLogic.getPriorCommonCustomizationV2(varibaleCat, pvsdto, list, pvDTO, commonColumn, totalListPostion, j, isPer, format,false);
                    }
                }

        } catch (NumberFormatException e) {
            LOGGER.error("{}",e);
        }
        return pvDTO;
    }

    public List<ProjectionVarianceDTO> getCustomisedDiscount(List<Object> dataList, PVSelectionDTO projSelDTO, int index, boolean isPer) {
        LOGGER.debug("Inside getCustomisedProjectionResultsTotalDiscount");
        String lastValue = StringUtils.EMPTY;
        List<ProjectionVarianceDTO> resultDto = new ArrayList<>();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        ProjectionVarianceDTO pvDTO = new ProjectionVarianceDTO();
        pvDTO.setLevelValue(projSelDTO.getLevelValue());
        pvDTO.setLevelNo(projSelDTO.getLevelNo());
        pvDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        pvDTO.setParent(0);
        List<Integer> priorList = new ArrayList<>(projSelDTO.getProjIdList());

        Map<Object, List<Object[]>> groupedResult = dataList.stream().map(obj -> (Object[]) obj)
                .collect(Collectors.groupingBy(x -> {
                    return new ArrayList<>(Arrays.asList(x[1], x[2]));
                }));
        int i=0;
        for (Map.Entry<Object, List<Object[]>> entry : groupedResult.entrySet()) {
            List<Object[]> list = entry.getValue();

            final Object[] obj = list.get(0);
            final Object[] proj;
            final Object[] actual;
            if(list.size()>1){
            if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                actual = list.get(0);
                proj = list.get(1);
            } else {
                actual = list.get(1);
                proj = list.get(0);
            }
            }else{
                Object[] emptyArray = Collections.nCopies(obj.length, 0).toArray(new Object[0]);
             if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                actual = list.get(0);
                proj = emptyArray;
            } else {
                actual = emptyArray;
                proj = list.get(0);
            }
            }
                if (obj[obj.length - 2] != null && !"".equals(lastValue) && !"null".equals(lastValue) 
                        && !lastValue.equals(String.valueOf(obj[obj.length - 2]))) {
                    pvDTO.setGroup(lastValue);
                    resultDto.add(pvDTO);
                    pvDTO = new ProjectionVarianceDTO();
                }
                lastValue = String.valueOf(obj[obj.length - 2]);
                pvDTO.setGroup(lastValue);
                String commonColumn = StringUtils.EMPTY;
                if (frequencyDivision == NumericConstants.FOUR) {
                    commonColumn = "Q" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == NumericConstants.TWO) {
                    commonColumn = "S" + obj[NumericConstants.TWO] + StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == 1) {
                    commonColumn = StringUtils.EMPTY + obj[1];
                } else if (frequencyDivision == NumericConstants.TWELVE) {
                    String monthName = HeaderUtils.getMonthForInt(Integer.parseInt(String.valueOf(obj[NumericConstants.TWO])) - 1);
                    commonColumn = monthName.toLowerCase(Locale.ENGLISH) + obj[1];
                }
                /*Only for Discount Customization*/
                PVCommonLogic.customizePeriodDiscountV2(commonColumn, projSelDTO.getVarIndicator(), projSelDTO, pvDTO, isPer ? RATE : AMOUNT, index, actual,proj, isPer);
                for (int j = 0; j < priorList.size(); j++) {
                    PVCommonLogic.getPriorCommonCustomizationV2(projSelDTO.getVarIndicator(), projSelDTO, list, pvDTO, commonColumn, index, j, isPer, isPer ? RATE : AMOUNT,false);
                }
                if (i++ == groupedResult.entrySet().size() - 1) {
                    resultDto.add(pvDTO);
                }
        }
       
        LOGGER.debug("Ending getCustomisedProjectionResultsTotalDiscount with list size  = = > {}", resultDto.size());
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
        return CommonQueryUtils.getAppData(input, "getProgramCategoryDiscountName", null);
    }

    String getCCPIds(PVSelectionDTO projSelDTO) {
        String query = getCCPQueryForCff(projSelDTO);
        List list = projSelDTO.isIsCustomHierarchy() ? Collections.emptyList() : HelperTableLocalServiceUtil.executeSelectQuery(query);
        String ccps = StringUtils.EMPTY;
        boolean flag = true;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object[] obj = (Object[]) list.get(i);
                if (flag) {
                    ccps = String.valueOf(obj[0]);
                    flag = false;
                } else {
                    ccps = ccps + "," + String.valueOf(obj[0]);
            }
        }
    }
        return ccps;
    }
    
    public String getCCPQueryForCff(ProjectionSelectionDTO projSelDTO) {
        String ccpQuery = SQlUtil.getQuery(Constants.PARENTVALIDATE);
        ccpQuery = ccpQuery.replace(Constants.RELVALUE, projSelDTO.getSessionDTO().getDedRelationshipBuilderSid());
        ccpQuery = ccpQuery.replace(Constants.RELVERSION, String.valueOf(projSelDTO.getSessionDTO().getDeductionRelationVersion()));
        ccpQuery += insertAvailableHierarchyNo(projSelDTO);
        ccpQuery += CommonLogic.getRelJoinGenerate(projSelDTO.getHierarchyIndicator(),projSelDTO.getSessionDTO());
        ccpQuery += SQlUtil.getQuery("get-ccp-query");
        ccpQuery = ccpQuery.replace(Constants.RELJOIN, CommonLogic.getRelJoinGenerate(projSelDTO.getHierarchyIndicator(),projSelDTO.getSessionDTO()));
        ccpQuery += " SELECT * FROM #SELECTED_HIERARCHY_NO_TEMP SH  JOIN ST_CCP_DEDUCTION_HIERARCHY SND ON SND.CCP_DETAILS_SID=SH.CCP_DETAILS_SID WHERE FILTER_CCPD=1 ";
        return QueryUtil.replaceTableNames(ccpQuery, projSelDTO.getSessionDTO().getCurrentTableNames());
    }

    private List getComparisonInput(final ComparisonLookupDTO comparisonLookup,boolean isDataSelection,SessionDTO session) {
        char asterik = '*';
        char percent = '%';
        List inputList = new ArrayList();
        if (isDataSelection) {
            return getInputsForQuery(comparisonLookup, percent,session);
        }
        if (comparisonLookup.getWorkflowStatus() != null && !comparisonLookup.getWorkflowStatus().equals(Constants.SELECT_ONE_LABEL)) { //Added for GAL-9231
            if (!comparisonLookup.getWorkflowStatus().equals("Saved")) {
                inputList.add(comparisonLookup.getWorkflowStatus());
            } else {
                inputList.add(percent);
            }
        } else {
            inputList.add(percent);
        } //Ends here
        inputList.add(comparisonLookup.getCurrentProjId());
        if (comparisonLookup.getProjectionName() != null && !comparisonLookup.getProjectionName().isEmpty()) {
            inputList.add(comparisonLookup.getProjectionName().replace(asterik, percent));
        } else {
            inputList.add(percent);
        }
        if (comparisonLookup.getProjectionDescription() != null && !comparisonLookup.getProjectionDescription().isEmpty()) {
            inputList.add(comparisonLookup.getProjectionDescription().replace(asterik, percent));
        } else {
            inputList.add(percent);
        }
        getFromAndTo(comparisonLookup, inputList);
        
        inputList.add(comparisonLookup.getCurrentProjId());
        
        getFilterWhereCondition(comparisonLookup, inputList);

        return inputList;
    }

    private void getFilterWhereCondition(final ComparisonLookupDTO comparisonLookup, List inputList) {
        StringBuilder filter = AbstractFilterLogic.getInstance().filterQueryGenerator(comparisonLookup.getFilter(), getFilerMap());
        
        if (filter != null) {
            inputList.add(filter.toString());
        } else {
            inputList.add(" ");
        }
    }

    private void getFromAndTo(final ComparisonLookupDTO comparisonLookup, List inputList) {
        if (comparisonLookup.getCreatedDateFrom() != null) {
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            Date startValue = comparisonLookup.getCreatedDateFrom();
            String customSql = "AND PM.CREATED_DATE >= '" + format2.format(startValue) + "'";
            inputList.add(customSql);
        } else {
            inputList.add(" ");
        }
        
        if (comparisonLookup.getCreatedDateTo() != null) {
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            Date endValue =  comparisonLookup.getCreatedDateTo();
            String customSql = "AND PM.CREATED_DATE <= '" + format2.format(endValue) + "'";
            inputList.add(customSql);
        } else {
            inputList.add(" ");
        }
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
        map.put(StringConstantsUtil.CREATED_BY1, "PM.CREATED_BY");
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
        map.put(StringConstantsUtil.CREATED_BY1, "CTE.CREATED_BY");
        map.put(StringConstantsUtil.CREATED_BY1, "CTE.CFF_MASTER_SID");
        return map;
    }

    private String getCommonColumn(int frequencyDivision, String column) {
        String returnString;
        if (frequencyDivision == NumericConstants.FOUR) {
            returnString = column.replace('q', 'Q');
            return returnString;
        }
        if (frequencyDivision == NumericConstants.TWO) {
            returnString = column.replace('s', 'S');
            return returnString;
        }
        if (frequencyDivision == NumericConstants.TWELVE) {
            returnString = column.toLowerCase(Locale.ENGLISH);
            return returnString;
        }
        return column;
    }

    private String getCommonColumn(String column, int frequencyDivision) {
         if (frequencyDivision == NumericConstants.FOUR) {
            column = column.replace('q', 'Q');
        } else if (frequencyDivision == NumericConstants.TWO) {
            column = column.replace('s', 'S');
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            column = column.toLowerCase(Locale.ENGLISH);
        }
        return column;
    }
    
    public String getVariance(String actualValue, String priorVal, DecimalFormat format) {
        Double val = Double.valueOf(isNull(actualValue));
        Double val1 = Double.valueOf(isNull(priorVal));
        String value;
        String variance;
        if (format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE)) {
                
                value = String.valueOf(roundToFraction((val - val1), 10000));
                value = roundToFraction(Double.parseDouble(value), 100) + "";
            value = getFormattedValue(format, value);
        } else {
            variance = String.valueOf(Double.parseDouble(isNull(actualValue)) - Double.parseDouble(isNull(priorVal)));
            value = getFormattedValue(format, variance);
        }
        return value;
    }

    public String getPerChange(String actualValue, String priorVal, DecimalFormat format) {
        DecimalFormat formatter = new DecimalFormat(TWO_DECIMAL); 
        Double val = Double.valueOf(isNull(actualValue));
        Double val1 = Double.valueOf(isNull(priorVal));
        String value;
        String variance;
        if (format.equals(RATE) || format.equals(RATE_PER) || format.equals(RATE_PER_THREE)) {
                
                value = String.valueOf(roundToFraction((val - val1), 10000));
                value = roundToFraction(Double.parseDouble(value), 100) + "";
        } else {
            variance = String.valueOf(Double.parseDouble(isNull(actualValue)) - Double.parseDouble(isNull(priorVal)));
            value = getFormattedValue(formatter, variance);
        }
        String priorval = getFormattedValue(formatter, priorVal);

        value = value.replace(",", StringUtils.EMPTY);
        priorval = priorval.replace(",", StringUtils.EMPTY);
        Double perChange = Double.parseDouble(value) / Double.parseDouble(priorval);
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
    
    
    public void customizePivot(String variableValue, String variableCategory, PVSelectionDTO pvsdto, ProjectionVarianceDTO projDTO, 
            DecimalFormat format, int index, List<Object[]> list) {
        try {
            Object[] obj=list.get(0);
            final Object[] proj;
            final Object[] actual;
            if (list.size() > 1) {
                if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                    actual = list.get(0);
                    proj = list.get(1);
                } else {
                    actual = list.get(1);
                    proj = list.get(0);
                }
            } else {
                Object[] arr=Collections.nCopies(obj.length, 0).toArray(new Integer[0]);
                if (Integer.parseInt(String.valueOf(obj[obj.length - 1])) == 0) {
                    actual = list.get(0);
                    proj = arr;
                } else {
                    actual = arr;
                    proj = list.get(0);
                }
            }
            List<Integer> priorList = new ArrayList<>(pvsdto.getProjIdList());
            if (actual.length == 15) {
                PVCommonLogic.customizePeriodDiscountV2(variableValue, variableCategory, pvsdto, projDTO, format, index, actual, proj, format.equals(RATE));
            } else {
                PVCommonLogic.customizePeriodV2(variableValue, variableCategory, pvsdto, projDTO, format, index, actual, proj, format.equals(RATE), false);
            }
            for (int j = 0; j < priorList.size(); j++) {
                PVCommonLogic.getPriorCommonCustomizationV2(variableCategory, pvsdto, list, projDTO, variableValue, index, j,
                        format.equals(RATE), format,false);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
 public int getCountForCustomView(final ProjectionSelectionDTO projSelDTO) {
		int count = 0;
		if (projSelDTO.getSessionDTO().getCustomHierarchyMap().get(projSelDTO.getCustomId()).size() < projSelDTO
				.getTreeLevelNo()) {
			LOGGER.info("Custom view last level");
			return 0;
		}
		String countQuery = SQlUtil.getQuery("customViewDeclaration");
		countQuery = countQuery.replace("[$CUSTOM_VIEW_MASTER_SID]", String.valueOf(projSelDTO.getCustomId()));
		countQuery += insertAvailableHierarchyNoForCount(projSelDTO);
	       countQuery += SQlUtil.getQuery("custom-view-count-condition-query-forPV");
        List list = HelperTableLocalServiceUtil.executeSelectQuery(
                QueryUtil.replaceTableNames(countQuery, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            count = Integer.parseInt(list.get(0).toString());
        }
        LOGGER.debug("ending getCountForCustomView");
        return count;
    }
    
    public int getCount(final SessionDTO sessionDTO, final String hierarchyIndicator,ProjectionSelectionDTO projSelDTO) {

        int count = 0;
        if (("C".equals(hierarchyIndicator) && sessionDTO.getCustomerLastLevelNo() < projSelDTO.getTreeLevelNo())
                || ("P".equals(hierarchyIndicator) && sessionDTO.getProductLastLevelNo() < projSelDTO.getTreeLevelNo())) {
            return count;
        }

        if ("C".equals(hierarchyIndicator) || "P".equals(hierarchyIndicator)) {
            String query = SQlUtil.getQuery("hiearchy-no-count-query");
            query = query.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(),projSelDTO));
            query = query.replace("[?HIERARCHY_COLUMN]", commonLogic.getColumnName(projSelDTO.getHierarchyIndicator()));
            query = query.replace("[?TAB_BASED_JOIN]", SQlUtil.getQuery("discount-join-filter"));
            query += CommonLogic.getRelJoinGenerate(projSelDTO.getHierarchyIndicator(),projSelDTO.getSessionDTO());
            query += WHERE_FILTER_CCPD;
            query += SQlUtil.getQuery("custom-view-count-condition-query");
            query = query.replace(Constants.RELJOIN, CommonLogic.getRelJoinGenerate(projSelDTO.getHierarchyIndicator(),projSelDTO.getSessionDTO()));
            List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
            if (list != null && !list.isEmpty()) {
                count = Integer.parseInt(list.get(0).toString());
        }
        } else {
            throw new IllegalArgumentException("Invalid Hierarchy Indicator :" + hierarchyIndicator);
        }
        
        LOGGER.info("Count is= {}", count);
        return count;

    }
    
    
    public String insertAvailableHierarchyNo(ProjectionSelectionDTO projSelDTO) {
        String sql;
        sql = projSelDTO.isIsCustomHierarchy() ? SQlUtil.getQuery("selected-hierarchy-no-for-customer-pv"):SQlUtil.getQuery("selected-hierarchy-no-for-custom");
        if (projSelDTO.isIsCustomHierarchy()) {
            String currentHierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
            int levelNo = commonLogic.getActualLevelNoFromCustomView(projSelDTO);
            switch (String.valueOf(currentHierarchyIndicator)) {
                case "C":
                    sql = sql.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getCustomerHierarchyNo(), currentHierarchyIndicator, levelNo,projSelDTO));
                    break;
                case "P":
                    sql = sql.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getProductHierarchyNo(), currentHierarchyIndicator, levelNo,projSelDTO));
                    break;
                case D_INDICATOR:
                        sql = sql.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION,
                                commonLogic.getSelectedHierarchyDeduction(projSelDTO.getSessionDTO(), projSelDTO.getDeductionHierarchyNo(), 
                                        currentHierarchyIndicator, levelNo,projSelDTO));
                    break;
                default:
                    LOGGER.warn("Invalid Hierarchy Indicator: {}", currentHierarchyIndicator);
            }
        } else {
            sql = sql.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(),projSelDTO));
        }
        sql = sql.replace(StringConstantsUtil.SELECTED_HIERARCHY_JOIN, getHierarchyJoinQuery(projSelDTO));
        LOGGER.debug("Group Filter Value: {}", projSelDTO.getGroupFilter());
        return sql;

    }
    public String insertAvailableHierarchyNoForCount(ProjectionSelectionDTO projSelDTO) {
        String sql;
		sql = !projSelDTO.isIsCustomHierarchy() ? SQlUtil.getQuery("selected-hierarchy-no-for-custom")
				: SQlUtil.getQuery("selected-hierarchy-no-for-customer-pv");
		if (projSelDTO.isIsCustomHierarchy()) {
			sql = sql.replace("[?HIERARCHY_NO_VALUES]",
					getSelectedHierarchyCustom(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(),
							commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO), projSelDTO.getTreeLevelNo(),
							false));
		} else {
			sql = sql.replace("[?HIERARCHY_NO_VALUES]", getSelectedHierarchy(projSelDTO.getSessionDTO(),
					projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo()));
		}
		sql = sql.replace("[?SELECTED_HIERARCHY_JOIN]", getHierarchyJoinQuery(projSelDTO));

		LOGGER.debug("Group Filter Value = {} ", projSelDTO.getGroupFilter());
		return sql;

    }
    
    public String getHierarchyJoinQuery(ProjectionSelectionDTO projSelDTO) {

        String currentHierarchyIndicator;

        if (projSelDTO.isIsCustomHierarchy()) {
            currentHierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
        } else {
            currentHierarchyIndicator = projSelDTO.getHierarchyIndicator();
        }

        return getHierarchyJoinQuery(projSelDTO.isIsCustomHierarchy(),currentHierarchyIndicator,projSelDTO);
    }    
    /**
     * Method is used to build the join query. This join determines that hierarchy no
     * associated with CCP that is used to manipulate the data.
     *
     * @param isCustomHierarchy
     * @param customerHierarchyNo
     * @param productHierarchyNo
     * @param hierarchyIndicator
     * @return
     */
    public String getHierarchyJoinQuery(boolean isCustomHierarchy, String hierarchyIndicator,
			ProjectionSelectionDTO projSelDTO) {
		StringBuilder joinQuery = new StringBuilder();
		String dedJoin = StringUtils.EMPTY;

		if (isCustomHierarchy) {
			joinQuery.append(
					"INNER JOIN CUSTOM_CCP_SALES_").append(projSelDTO.getCustomId()).append(" RLD1 ON RLD1.HIERARCHY_NO LIKE A.HIERARCHY_NO + '%'  ")
					.append(" AND RLD1.CUST_VIEW_MASTER_SID = ").append(projSelDTO.getCustomId());
		}

		joinQuery.append(" JOIN ST_CCP_HIERARCHY CH ON ");
		if (isCustomHierarchy) {
			joinQuery.append("CH.CCP_DETAILS_SID = RLD1.CCP_DETAILS_SID ");
			dedJoin = "AND ( RLD1.RS_CONTRACT_SID = SPM.RS_CONTRACT_SID\n"
					+ "                   OR RLD1.RS_CONTRACT_SID=0 ) ";

		} else {
			joinQuery.append(
					"C".equals(hierarchyIndicator) ? "CH.CUST_HIERARCHY_NO "
							: "CH.PROD_HIERARCHY_NO");
			joinQuery.append(" LIKE A.HIERARCHY_NO + '%' ");
		}
		joinQuery.append(getJoinForDP(dedJoin));

		return joinQuery.toString();
	}

	private String getJoinForDP(String deductionJoin) {
		String sql = SQlUtil.getQuery("discount-join");
		sql = sql.replace("?DEDJOIN", deductionJoin);
        sql += "LEFT JOIN RS_CONTRACT RSC ON RSC.RS_CONTRACT_SID=SPM.RS_CONTRACT_SID\n ";
		return sql;
	}
    
      public List<String> getHiearchyNoForCustomView(final ProjectionSelectionDTO projSelDTO, int start, int end) {

        List<String> resultSet = new ArrayList();
        String query = SQlUtil.getQuery("customViewDeclaration");
        query = query.replace("[$CUSTOM_VIEW_MASTER_SID]", String.valueOf(projSelDTO.getCustomId()));
        query += insertAvailableHierarchyNo(projSelDTO);
        query += SQlUtil.getQuery("custom-view-count-condition-query-forPVLoad");
        query = query.replace("[?START]", String.valueOf(start));
        query = query.replace("[?END]", String.valueOf(end));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(
                QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
            Object[] object = (Object[]) list.get(i);
            resultSet.add(String.valueOf(object[0]));
            }
        } else {
            return Collections.emptyList();
        }
        return resultSet;
    }
    
    private String getRsIdForCurrentHierarchy(PVSelectionDTO pvsdto) {
        String tableName = pvsdto.getSessionDTO().getAction().equals("view") ? StringUtils.EMPTY : "ST_";
        String query = SQlUtil.getQuery("get-RsId-For-Current-Hierarchy");
        query = query.replace("?tableName", tableName);
        return query;
    }
     
	public String getRSIds(PVSelectionDTO pvsdto) {
        String relJoin = CommonLogic.getRelJoinGenerate(pvsdto.getHierarchyIndicator(),pvsdto.getSessionDTO());
        relJoin += SQlUtil.getQuery("get-ccp-query");
        relJoin = relJoin.replace(Constants.RELJOIN, CommonLogic.getRelJoinGenerate(pvsdto.getHierarchyIndicator(),pvsdto.getSessionDTO()));
            
		StringBuilder rsIds = new StringBuilder();
		try {
                    String ccpQuery = SQlUtil.getQuery(Constants.PARENTVALIDATE);
                    ccpQuery = ccpQuery.replace(Constants.RELVALUE, pvsdto.getSessionDTO().getDedRelationshipBuilderSid());
                    ccpQuery = ccpQuery.replace(Constants.RELVERSION, String.valueOf(pvsdto.getSessionDTO().getDeductionRelationVersion()));
                    String rsQuery = insertAvailableHierarchyNo(pvsdto) + relJoin + getRsIdForCurrentHierarchy(pvsdto);
                    String query = QueryUtil.replaceTableNames(rsQuery.replace("@CCP", "#SELECTED_HIERARCHY_NO"),
                            pvsdto.getSessionDTO().getCurrentTableNames());
                    List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                    boolean flag = true;
                    if (list != null) {
				int size = list.size();
				for (int i = 0; i < size; i++) {
					Object obj = list.get(i);
					if (flag) {
						rsIds.append(String.valueOf(obj));
						flag = false;
					} else {
						rsIds.append(',').append(String.valueOf(obj));
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return rsIds.toString();
	}
  
        public List getHiearchyNoAsList(final ProjectionSelectionDTO projSelDTO, int start, int end) {

        String query = SQlUtil.getQuery("hiearchy-no-query");
        query = query.replace(StringConstantsUtil.HIERARCHY_NO_VALUES_QUESTION, getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(),projSelDTO));
        query = query.replace("[?HIERARCHY_COLUMN]", commonLogic.getColumnName(projSelDTO.getHierarchyIndicator()));
        query = query.replace("[?TAB_BASED_JOIN]", SQlUtil.getQuery("discount-join-filter"));
        query += CommonLogic.getRelJoinGenerate(projSelDTO.getHierarchyIndicator(),projSelDTO.getSessionDTO());
        query += WHERE_FILTER_CCPD;
        query += SQlUtil.getQuery("custom-view-condition-query");
        query = query.replace("[?START]", String.valueOf(start));
        query = query.replace("[?END]", String.valueOf(end));
        query = query.replace(Constants.RELJOIN, CommonLogic.getRelJoinGenerate(projSelDTO.getHierarchyIndicator(),projSelDTO.getSessionDTO()));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            return list;
        }
        
        return Collections.emptyList();
    }

  
   public String getString(String key, List<String> hierarchyNo) {
        StringBuilder stringBuilder = new StringBuilder();
        int i=1;
        for (String str : hierarchyNo) {
            if (key.startsWith(str.trim())) {
                stringBuilder.append("('");
                stringBuilder.append(key);
                stringBuilder.append("',").append(i++).append(')');
                return stringBuilder.toString();
            }
        }
        return "";
    }
    
    public String getSelectedHierarchy(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator, int levelNo,ProjectionSelectionDTO projSelDTO) {

        if (levelNo == 0) {
            throw new IllegalArgumentException("Invalid Level No:" + levelNo);
        }
        String hierarchyForLevel;
        Map<String, List> relationshipLevelDetailsMap = projSelDTO.isIsCustomHierarchy() ?sessionDTO.getCustomDescription() :sessionDTO.getHierarchyLevelDetails()  ;
        StringBuilder stringBuilder = new StringBuilder();
        hierarchyForLevel=hierachyQueryIndicator(hierarchyNo, relationshipLevelDetailsMap, levelNo, hierarchyIndicator, stringBuilder);
        if (sessionDTO.getHierarchyLevelDetails().isEmpty()) {
            stringBuilder.append("('");
            stringBuilder.append("')");
        }
        hierarchyForLevel=hierarchyForLevel.substring(0, hierarchyForLevel.lastIndexOf(Constants.COMMA_CHAR));
        sessionDTO.setLevelHierarchyNo(hierarchyForLevel);
        projSelDTO.setSessionDTO(sessionDTO);
        return stringBuilder.toString();
    }

    private String hierachyQueryIndicator(String hierarchyNo, Map<String, List> relationshipLevelDetailsMap, int levelNo, String hierarchyIndicator, StringBuilder stringBuilder)  {
        boolean isNotFirstElement = false;
        boolean isNotFirstHierarchy = false;
        String hierarchyForLevel=StringUtils.EMPTY;
        boolean isHierarchyNoNotAvailable = checkHierarchyAvailability(hierarchyNo);
        int i = 1;
        for (Map.Entry<String, List> entry : relationshipLevelDetailsMap.entrySet()) {
            if (checkHierarchyNo(hierarchyNo)) {
                if (hierarchyValidation(entry, levelNo, hierarchyIndicator, isHierarchyNoNotAvailable, hierarchyNo)) {
                    if (isNotFirstElement) {
                        stringBuilder.append(",\n");
                    }
                    hierarchyForLevel= queryGenerate(stringBuilder, entry, i);
                    isNotFirstElement = true;
                }
            } else if ((Integer.parseInt(entry.getValue().get(2).toString()) == levelNo && hierarchyIndicator.equals(entry.getValue().get(4).toString()))) {
                if (isNotFirstHierarchy) {
                    stringBuilder.append(",\n");
                }
                hierarchyForLevel=hierarchyForLevel.concat(entry.getKey()).concat(",");
                stringBuilder.append(getString(entry.getKey(), Arrays.asList((String.valueOf(hierarchyNo)).split("\\,"))));
                isNotFirstHierarchy = true;
            }
        }
        return hierarchyForLevel;
    }

    private static boolean checkHierarchyNo(String hierarchyNo) {
        return hierarchyNo != null && !hierarchyNo.contains(",");
    }

    private boolean checkHierarchyAvailability(String hierarchyNo) {
        return StringUtils.isEmpty(hierarchyNo) || "%".equals(hierarchyNo);
    }

    private String queryGenerate(StringBuilder stringBuilder, Map.Entry<String, List> entry, int i) {
        String hierarchyForLevel=StringUtils.EMPTY;
        stringBuilder.append("('");
        hierarchyForLevel = hierarchyForLevel.concat(entry.getKey()).concat(",");
        stringBuilder.append(entry.getKey());
        stringBuilder.append("',").append(i++).append(')');
        return hierarchyForLevel;
    }

    private static boolean hierarchyValidation(Map.Entry<String, List> entry, int levelNo, String hierarchyIndicator, boolean isHierarchyNoNotAvailable, String hierarchyNo) {
        return (Integer.parseInt(entry.getValue().get(2).toString()) == levelNo && hierarchyIndicator.equals(entry.getValue().get(4).toString())) && (isHierarchyNoNotAvailable || entry.getKey().startsWith(hierarchyNo));
    }

    private List getInputsForQuery(ComparisonLookupDTO comparisonLookup,char percent,SessionDTO session) {
         List inputList = new ArrayList();
         if (comparisonLookup.getWorkflowStatus() != null && !comparisonLookup.getWorkflowStatus().equals(Constants.SELECT_ONE_LABEL)) { //Added for GAL-9231
            if (!comparisonLookup.getWorkflowStatus().equals("Saved")) {
                inputList.add(comparisonLookup.getWorkflowStatus());
            } else {
                inputList.add(percent);
            }
        } else {
            inputList.add(percent);
        } 
             getFromAndTo(comparisonLookup, inputList);
             inputList.add(session.getCustomViewMasterSid());
             getFilterWhereCondition(comparisonLookup, inputList);
         return inputList;
    }
    
    
    public String getSelectedHierarchyCustom(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator,
			int levelNo, boolean isCount) {

		if (levelNo == 0) {
			throw new IllegalArgumentException("Invalid Level No:" + levelNo);
		}

		Map<String, List> relationshipLevelDetailsMap = sessionDTO.getCustomDescription();
		StringBuilder stringBuilder = new StringBuilder();

		boolean isNotFirstElement = false;
		boolean isHierarchyNoNotAvailable = StringUtils.isEmpty(hierarchyNo) || "%".equals(hierarchyNo);
		int i = 1;
		for (Map.Entry<String, List> entry : relationshipLevelDetailsMap.entrySet()) {
			if ((Integer.parseInt(entry.getValue().get(2).toString()) == levelNo
					&& hierarchyIndicator.equals(entry.getValue().get(4).toString()))
					&& (isHierarchyNoNotAvailable || entry.getKey().startsWith(hierarchyNo))) {

				if (isNotFirstElement) {
					stringBuilder.append(",\n");
				}
				stringBuilder.append("('");
				stringBuilder.append(entry.getKey());
				stringBuilder.append(isCount ? "')" : "'," + i++ + ")");

				isNotFirstElement = true;
			}
		}
		if (sessionDTO.getHierarchyLevelDetails().isEmpty()) {
			stringBuilder.append("('");
			stringBuilder.append("')");
		}
		return stringBuilder.toString();
	}
    
    public String getSelectedHierarchy(SessionDTO sessionDTO, String hierarchyNo, String hierarchyIndicator,
			int levelNo) {

		if (levelNo == 0) {
			throw new IllegalArgumentException("INVALID_LEVEL_NO" + levelNo);
		}
		Map<String, List> relationshipLevelDetailsMap = sessionDTO.getHierarchyLevelDetails();
		StringBuilder stringBuilder = new StringBuilder();
		boolean isNotFirstElement = false;
		boolean isNotFirstHierarchy = false;
		boolean isHierarchyNoNotAvailable = StringUtils.isEmpty(hierarchyNo) || "%".equals(hierarchyNo);
		int i = 1;
		for (Map.Entry<String, List> entry : relationshipLevelDetailsMap.entrySet()) {
			if (!hierarchyNo.contains(",")) {
				if ((Integer.parseInt(entry.getValue().get(2).toString()) == levelNo
						&& hierarchyIndicator.equals(entry.getValue().get(4).toString()))
						&& (isHierarchyNoNotAvailable || entry.getKey().startsWith(hierarchyNo))) {
					if (isNotFirstElement) {
						stringBuilder.append(",\n");
					}
					stringBuilder.append("('");
					stringBuilder.append(entry.getKey());
					stringBuilder.append("',").append(i++).append(')');
					isNotFirstElement = true;
				}
			} else if ((Integer.parseInt(entry.getValue().get(2).toString()) == levelNo
					&& hierarchyIndicator.equals(entry.getValue().get(4).toString()))) {
				if (isNotFirstHierarchy) {
					stringBuilder.append(",\n");
				}
				stringBuilder
						.append(getString(entry.getKey(), Arrays.asList((String.valueOf(hierarchyNo)).split("\\,"))));
				isNotFirstHierarchy = true;
			}
		}
		if (sessionDTO.getHierarchyLevelDetails().isEmpty()) {
			stringBuilder.append("('");
			stringBuilder.append("')");
		}
		return stringBuilder.toString();
	}
}