package com.stpl.app.cff.ui.projectionresults.logic;

import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.queryUtils.CommonQueryUtils;
import com.stpl.app.cff.ui.projectionresults.dto.ProjectionResultsDTO;
import com.stpl.app.cff.util.CommonUtils;
import static com.stpl.app.cff.util.CommonUtils.BOTH;
import static com.stpl.app.cff.util.Constants.LabelConstants.*;
import static com.stpl.app.cff.util.HeaderUtils.getCommonColumnHeader;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author porchelvi.g
 */
public class ProjectionResultsLogic {

    private static final Logger LOGGER = Logger.getLogger(ProjectionResultsLogic.class);
    /**
     * The Currency Zero Decimal Places Format.
     */
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,###,##0");
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PER_TWO = new DecimalFormat("#,##0.00%");
    /**
     * The Numeric Zero Decimal Places Format.
     */
    private static final DecimalFormat NUM_ZERO = new DecimalFormat("#,##0");
    private List<ProjectionResultsDTO> prjTotalDisPerDtoList = new ArrayList<>();
    private List<ProjectionResultsDTO> prjTotalDisDolDtoList = new ArrayList<>();
    private List<ProjectionResultsDTO> prjTotalRPUDtoList = new ArrayList<>();
    private List<ProjectionResultsDTO> projectionTotalList = new ArrayList<>();
    
    public static final String NULL_PPAACTUAL_SALES = "+Isnull(PPA.ACTUAL_SALES, 0)";
    private static final String CURRENCY = "$";
    private static final DecimalFormat CUR_TWO = new DecimalFormat("#,##0.00");
    public static final String CLOSE_SLASH_N = "       )\n";
    public static final String SELECT_SPACE = "select ";
    public static final String PRC_CFF_PROJECTION_RESULTS_DISCOUNT = "Prc_cff_projection_results_discount";
    public static final String PRC_CFF_RESULTS = "PRC_CFF_RESULTS";
    
    private Map<String,String> monthMap=new HashMap<>();

    public List<ProjectionResultsDTO> getTotalDiscountLevels(ProjectionSelectionDTO projSelDTO,String group) {
        LOGGER.debug("= = = Inside getTotalDiscountLevels = = =");
         List<ProjectionResultsDTO> projDTOList;
        Object[] orderedArgs2 = {projSelDTO.getProjectionId(), projSelDTO.getFrequency(), StringConstantsUtil.ASSUMPTIONS,"PERIOD", null, projSelDTO.getCcpIds(), null, StringConstantsUtil.EXCEL};
          List<Object[]> gtsList = CommonLogic.callProcedure(PRC_CFF_PROJECTION_RESULTS_DISCOUNT, orderedArgs2);
            projDTOList =getCustomizedDiscountsProjectionTotal(gtsList, projSelDTO,group);

     return projDTOList;
    }


    private List<String> discountList = new ArrayList<>();

    public List<ProjectionResultsDTO> getCustomizedProjectionResultsDiscount(List<Object> list, ProjectionSelectionDTO projSelDTO, boolean isPPA) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setDiscountIndex(0);
        if (!projSelDTO.isIsTotal() && !isPPA) {
            discountList = new ArrayList<>(projSelDTO.getDiscountNameList());
            for (String name : projSelDTO.getDiscountNameList()) {
                ProjectionResultsDTO dto = getCustomisedProjectionResultsDiscount(list, name, projSelDTO);
                projDTOList.add(dto);
            }
            List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
            columnList.remove(StringConstantsUtil.GROUP_PROPERTY);
            for (String ob : discountList) {
                ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
                projDTO.setParent(0);
                projDTO.setProjectionTotal(0);
                projDTO.setGroup(ob);
                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
                }
                projDTOList.add(projDTO);
            }
        } else {
            ProjectionResultsDTO dto = getCustomisedProjectionResultsDiscount(list, StringConstantsUtil.TOTAL1, projSelDTO);
            projDTOList.add(dto);
        }
        return projDTOList;
    }

    public ProjectionResultsDTO getCustomisedProjectionResultsDiscount(List<Object> list, String discountName, ProjectionSelectionDTO projSelDTO) {
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(StringConstantsUtil.GROUP_PROPERTY);
        ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
        projDTO.setLevelValue(projSelDTO.getLevelValue());
        projDTO.setLevelNo(projSelDTO.getLevelNo());
        projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projDTO.setGroup(null);
        boolean forword = false;
        if (discountName.contains(StringConstantsUtil.TOTAL1)) {
            forword = true;
        }
        boolean start = true;
        String discount = null;
        if (list != null && !list.isEmpty()) {
            for (int i = projSelDTO.getDiscountIndex(); i < list.size() && start; i++) {
                projSelDTO.setDiscountIndex(i);
                Object[] discountRow = (Object[]) list.get(i);
                if (discountList.contains("" + discountRow[NumericConstants.TWO])) {
                    forword = true;
                } else {
                    if (!discountName.contains(StringConstantsUtil.TOTAL1)) {
                        forword = false;
                    }
                }
                if (forword) {
                    if (discount == null) {
                        projDTO.setGroup(String.valueOf(discountRow[NumericConstants.TWO]));
                    } else if (!discount.equals(discountRow[NumericConstants.TWO].toString())) {
                        if (!discountName.contains(StringConstantsUtil.TOTAL1)) {
                            discountList.remove(discount);
                        }
                        start = false;
                    }
                    if (start) {
                        discount = discountRow[NumericConstants.TWO].toString();
                        String column = "";
                        int year = Integer.valueOf(String.valueOf(discountRow[0]));
                        int period = Integer.valueOf(String.valueOf(discountRow[1]));
                        List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                        String commonColumn = common.get(0);
                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            String value = "" + discountRow[NumericConstants.THREE];
                            if (projSelDTO.getSales().contains(StringConstantsUtil.SALES1)) {
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            } else if (projSelDTO.getSales().contains("RATE")) {
                                value = getFormattedValue(PER_TWO, value);
                            } else if (projSelDTO.getSales().contains("TOT")) {
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            }
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            String value = "" + discountRow[NumericConstants.FOUR];
                            if (projSelDTO.getSales().contains(StringConstantsUtil.SALES1)) {
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            } else if (projSelDTO.getSales().contains("RATE")) {
                                value = getFormattedValue(PER_TWO, value);
                            } else if (projSelDTO.getSales().contains("TOT")) {
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            }
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                    }
                }
            }

        }
        if (discountName.contains(StringConstantsUtil.TOTAL1)) {
            projDTO.setParent(1);
            projDTO.setLevelNo(projSelDTO.getLevelNo());
            projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
            projDTO.setParentNode(projSelDTO.getParentNode());
            projDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
            projDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
            projDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
            projDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
            projDTO.setOnExpandTotalRow(0);
            if (projSelDTO.getSales().contains(StringConstantsUtil.SALES1)) {
                projDTO.setGroup("Total Discount $");
            } else if (projSelDTO.getSales().contains("RATE")) {
                projDTO.setGroup("Total Discount %");
            } else {
                projDTO.setGroup("Total RPU");
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
            projDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
        }
        return projDTO;
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionResultsSales(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDtoList = new ArrayList<>();
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(StringConstantsUtil.GROUP_PROPERTY);
        ProjectionResultsDTO projSalesDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO projUnitDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO totalDiscountPerDto = new ProjectionResultsDTO();
        ProjectionResultsDTO totalRPUDto = new ProjectionResultsDTO();
        ProjectionResultsDTO totalDiscountAmtDto = new ProjectionResultsDTO();
        ProjectionResultsDTO netSalesDto = new ProjectionResultsDTO();
        ProjectionResultsDTO cogsDto = new ProjectionResultsDTO();
        ProjectionResultsDTO netprofitDto = new ProjectionResultsDTO();
        ProjectionResultsDTO percentageExFactoryProductDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO netSalesPercentageExFactoryDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO discountPercentExFactoryDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO productViewExFactoryDTO = new ProjectionResultsDTO();

        setProductExFactoryDto(productViewExFactoryDTO, projSelDTO);
        
        projSalesDTO.setLevelNo(projSelDTO.getLevelNo());
        projSalesDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projSalesDTO.setLevelValue(projSelDTO.getLevelValue());
        projSalesDTO.setGroup(CONTRACT_SALES_AT_WAC.getConstant());
        projUnitDTO.setLevelNo(projSelDTO.getLevelNo());
        projUnitDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projUnitDTO.setLevelValue(projSelDTO.getLevelValue());
        projUnitDTO.setGroup(UNIT_VOL.getConstant());
        projSalesDTO.setParent(0);
        projUnitDTO.setParent(0);

        totalDiscountPerDto.setParent(1);
        totalDiscountPerDto.setGroup(TOTAL_DISCOUNT_PERC.getConstant());
        totalDiscountPerDto.setProjectionTotal(0);
        totalDiscountPerDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalDiscountPerDto.setOnExpandTotalRow(0);
        totalDiscountPerDto.setLevelValue(projSelDTO.getLevelValue());
        totalDiscountPerDto.setLevelNo(projSelDTO.getLevelNo());
        totalDiscountPerDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalDiscountPerDto.setHierarchyNo(projSelDTO.getHierarchyNo());
        totalDiscountPerDto.setCcpIds(projSelDTO.getCcpIds());

        totalRPUDto.setParent(1);
        totalRPUDto.setGroup(TOTAL_RPU.getConstant());
        totalRPUDto.setProjectionTotal(0);
        totalRPUDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalRPUDto.setOnExpandTotalRow(0);
        totalRPUDto.setLevelValue(projSelDTO.getLevelValue());
        totalRPUDto.setLevelNo(projSelDTO.getLevelNo());
        totalRPUDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalRPUDto.setHierarchyNo(projSelDTO.getHierarchyNo());
        totalRPUDto.setCcpIds(projSelDTO.getCcpIds());

        totalDiscountAmtDto.setParent(1);
        totalDiscountAmtDto.setGroup(TOTAL_DISCOUNT_AMOUNT.getConstant());
        totalDiscountAmtDto.setProjectionTotal(0);
        totalDiscountAmtDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalDiscountAmtDto.setOnExpandTotalRow(0);
        totalDiscountAmtDto.setLevelValue(projSelDTO.getLevelValue());
        totalDiscountAmtDto.setLevelNo(projSelDTO.getLevelNo());
        totalDiscountAmtDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalDiscountAmtDto.setHierarchyNo(projSelDTO.getHierarchyNo());
        totalDiscountAmtDto.setCcpIds(projSelDTO.getCcpIds());

        netSalesDto.setParent(0);
        netSalesDto.setGroup("Net Sales");
        netSalesDto.setProjectionTotal(0);
        netSalesDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        netSalesDto.setOnExpandTotalRow(0);
        netSalesDto.setLevelValue(projSelDTO.getLevelValue());
        netSalesDto.setLevelNo(projSelDTO.getLevelNo());
        netSalesDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        netSalesDto.setHierarchyNo(projSelDTO.getHierarchyNo());

        cogsDto.setLevelNo(projSelDTO.getLevelNo());
        cogsDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        cogsDto.setLevelValue(projSelDTO.getLevelValue());
        cogsDto.setGroup(COGS.getConstant());
        cogsDto.setRelationshipLevelName(COGS.getConstant());

        netprofitDto.setLevelNo(projSelDTO.getLevelNo());
        netprofitDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        netprofitDto.setLevelValue(projSelDTO.getLevelValue());
        netprofitDto.setGroup(NET_PROFIT.getConstant());
        netprofitDto.setRelationshipLevelName(NET_PROFIT.getConstant());

        cogsDto.setParent(0);
        netprofitDto.setParent(0);
        //% of ExFactory Product
        percentageExFactoryProductDTO.setParent(0);
        percentageExFactoryProductDTO.setLevelNo(projSelDTO.getLevelNo());
        percentageExFactoryProductDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        percentageExFactoryProductDTO.setLevelValue(projSelDTO.getLevelValue());
        percentageExFactoryProductDTO.setGroup(PERC_OF_EX_FACTORY.getConstant());
        //Net Sales % of Ex-Factory
        netSalesPercentageExFactoryDTO.setParent(0);
        netSalesPercentageExFactoryDTO.setLevelNo(projSelDTO.getLevelNo());
        netSalesPercentageExFactoryDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        netSalesPercentageExFactoryDTO.setLevelValue(projSelDTO.getLevelValue());
        netSalesPercentageExFactoryDTO.setGroup(NET_SALES_PERCENTAGE_EXFACTORY.getConstant());
        //Discount % of Ex-Factory
        discountPercentExFactoryDTO.setParent(1);
        discountPercentExFactoryDTO.setGroup(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant());
        discountPercentExFactoryDTO.setProjectionTotal(0);
        discountPercentExFactoryDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        discountPercentExFactoryDTO.setOnExpandTotalRow(0);
        discountPercentExFactoryDTO.setLevelValue(projSelDTO.getLevelValue());
        discountPercentExFactoryDTO.setLevelNo(projSelDTO.getLevelNo());
        discountPercentExFactoryDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        discountPercentExFactoryDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        discountPercentExFactoryDTO.setCcpIds(projSelDTO.getCcpIds());

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = "";
                int year = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
                int period = Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = getCellValue(obj[NumericConstants.FIVE], StringConstantsUtil.SALES);
                    projSalesDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.SEVEN], StringConstantsUtil.UNITS1);
                    projUnitDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.ELEVEN], StringConstantsUtil.PERCENT1);
                    totalDiscountPerDto.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.NINE], StringConstantsUtil.SALES);
                    totalDiscountAmtDto.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.FORTY_ONE], StringConstantsUtil.SALES);
                    totalRPUDto.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.TWENTY_SEVEN], StringConstantsUtil.SALES);
                    netSalesDto.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.FORTY_THREE], StringConstantsUtil.SALES);
                    cogsDto.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.FORTY_FIVE], StringConstantsUtil.SALES);
                    netprofitDto.addStringProperties(column, value);
                    //% of ExFactory Product - Actuals
                    value = getFormattedValue(PER_TWO, String.valueOf(obj[NumericConstants.THIRTEEN]));
                    percentageExFactoryProductDTO.addStringProperties(column, value);
                    //Discount % of Ex-Factory - Actuals
                    value = getFormattedValue(PER_TWO, String.valueOf(obj[NumericConstants.SIXTY_ONE]));
                    discountPercentExFactoryDTO.addStringProperties(column, value);
                    //Net Sales % of Ex-Factory - Actuals
                    value = getFormattedValue(PER_TWO, String.valueOf(obj[NumericConstants.FIFTY_NINE]));
                    netSalesPercentageExFactoryDTO.addStringProperties(column, value);
                    //Product or Custom view based Ex-Factory CEL-376
                    value = getCellValue(obj[NumericConstants.ONE], StringConstantsUtil.SALES);
                    productViewExFactoryDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = getCellValue(obj[NumericConstants.SIX], StringConstantsUtil.SALES);
                    projSalesDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.EIGHT], StringConstantsUtil.UNITS1);
                    projUnitDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.TWELVE], StringConstantsUtil.PERCENT1);
                    totalDiscountPerDto.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.TEN], StringConstantsUtil.SALES);
                    totalDiscountAmtDto.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.FORTY_TWO], StringConstantsUtil.SALES);
                    totalRPUDto.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.TWENTY_EIGHT], StringConstantsUtil.SALES);
                    netSalesDto.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.FORTY_FOUR], StringConstantsUtil.SALES);
                    cogsDto.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.FORTY_SIX], StringConstantsUtil.SALES);
                    netprofitDto.addStringProperties(column, value);
                    //% of ExFactory Product - Projected
                    value = getFormattedValue(PER_TWO, String.valueOf(obj[NumericConstants.FOURTEEN]));
                    percentageExFactoryProductDTO.addStringProperties(column, value);
                    //Discount % of Ex-Factory - Projected
                    value = getFormattedValue(PER_TWO, String.valueOf(obj[NumericConstants.SIXTY_TWO]));
                    discountPercentExFactoryDTO.addStringProperties(column, value);
                    //Net Sales % of Ex-Factory - Projected
                    value = getFormattedValue(PER_TWO, String.valueOf(obj[NumericConstants.SIXTY]));
                    netSalesPercentageExFactoryDTO.addStringProperties(column, value);
                    //Product or Custom view based Ex-Factory CEL-376
                    value = getCellValue(obj[NumericConstants.TWO], StringConstantsUtil.SALES);
                    productViewExFactoryDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            projSalesDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
            projUnitDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
            totalDiscountPerDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
            totalRPUDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
            totalDiscountAmtDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
            netSalesDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
            cogsDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
            netprofitDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
            percentageExFactoryProductDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
            discountPercentExFactoryDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
            netSalesPercentageExFactoryDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
            productViewExFactoryDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
        }
        projDtoList.add(projSalesDTO);
        projDtoList.add(projUnitDTO);
        projDtoList.add(totalDiscountPerDto);
        projDtoList.add(totalRPUDto);
        projDtoList.add(totalDiscountAmtDto);
        projDtoList.add(netSalesDto);
        projDtoList.add(cogsDto);
        projDtoList.add(netprofitDto);
        projDtoList.add(percentageExFactoryProductDTO);
        projDtoList.add(discountPercentExFactoryDTO);
        projDtoList.add(netSalesPercentageExFactoryDTO);
        projDtoList.add(productViewExFactoryDTO);

        return projDtoList;
    }

    private void setProductExFactoryDto(final ProjectionResultsDTO productViewExFactoryDTO, final ProjectionSelectionDTO projSelDTO) {

        productViewExFactoryDTO.setParent(0);
        productViewExFactoryDTO.setLevelNo(projSelDTO.getLevelNo());
        productViewExFactoryDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        productViewExFactoryDTO.setLevelValue(projSelDTO.getLevelValue());
        productViewExFactoryDTO.setGroup(projSelDTO.getLevelDdlbMap().get(projSelDTO.getTreeLevelNo()) + " " + PRODUCT_LEVEL_EX_FACTORY.getConstant());

    }

    public List<ProjectionResultsDTO> getQueryForHierarchyExpand(ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("= = = Inside getContractSalesAndUnits = = =");
        projSelDTO.setSales(StringConstantsUtil.SALES1);
        String query = CommonLogic.getCCPQueryForCff(projSelDTO);
        String ccp = getCustomizedCcp(query);
        projSelDTO.setCcpIds(ccp);
        String freq = projSelDTO.getFrequency();
        Object[] orderedArgs1 = {projSelDTO.getProjectionId(), freq, StringConstantsUtil.ASSUMPTIONS, "PERIOD", null, ccp};
        List<Object[]> list = CommonLogic.callProcedure(PRC_CFF_RESULTS, orderedArgs1);
        List<ProjectionResultsDTO> projDTOList = getCustomizedProjectionResultsSales(list, projSelDTO);
        LOGGER.debug("= = = Ending getContractSalesAndUnits = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getProjectionPivot(ProjectionSelectionDTO projSelDTO, List<Object[]> discountsList) {
        String freq = projSelDTO.getFrequency();
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

        int histNum = projSelDTO.getHistoryNum();
        String dateQuery = CommonLogic.getHistoryPeriodDate(freq, histNum);
        List dateList = HelperTableLocalServiceUtil.executeSelectQuery(dateQuery);
        String fromDate = StringUtils.EMPTY;
        if (dateList != null && dateList.size() > 0) {
            Object obj = dateList.get(0);
            fromDate = String.valueOf(obj);
        }
        Object[] orderedArgs1 = {projSelDTO.getProjectionId(), freq, StringConstantsUtil.ASSUMPTIONS, StringConstantsUtil.PIVOT_LABEL, fromDate, ccps};
        List<Object[]> gtsList = CommonLogic.callProcedure(PRC_CFF_RESULTS, orderedArgs1);
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        List<ProjectionResultsDTO> resultsList = setPivotValue(gtsList, projSelDTO.getFrequencyDivision(), periodList, projSelDTO, NumericConstants.THREE, discountsList);
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(DESCENDING.getConstant())) {
            Collections.reverse(resultsList);
        }
      
        return resultsList;
    }

    public List<ProjectionResultsDTO> getProjectionPivotTotal(ProjectionSelectionDTO projSelDTO, List<Object[]> discountsList) {
        if (projectionTotalList.isEmpty()) {
            
            String freq=projSelDTO.getFrequency();
            int histNum=projSelDTO.getHistoryNum();
            String dateQuery=CommonLogic.getHistoryPeriodDate(freq,histNum);
            List dateList=HelperTableLocalServiceUtil.executeSelectQuery(dateQuery);
             String fromDate=StringUtils.EMPTY;
            if (dateList != null) {
                Object obj = dateList.get(0);
                fromDate = String.valueOf(obj);
            }
            Object[] orderedArgs2 = {projSelDTO.getProjectionId(),freq, StringConstantsUtil.ASSUMPTIONS,StringConstantsUtil.PIVOT_LABEL,fromDate};
            List<Object[]> gtsList = CommonLogic.callProcedure(PRC_CFF_RESULTS, orderedArgs2);
            getCustomizedProjectionPivotTotal(gtsList, discountsList, projSelDTO);
        }
        return projectionTotalList;
    }

    public void getCustomizedProjectionPivotTotal(List<Object[]> list, List<Object[]> discountList, ProjectionSelectionDTO projSelDTO) {
        projectionTotalList.clear();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        
        List<ProjectionResultsDTO> resultsList=setPivotValue(list,frequencyDivision,periodList,projSelDTO, NumericConstants.THREE,discountList);
        projectionTotalList.addAll(resultsList);
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(StringConstantsUtil.GROUP_PROPERTY);
        boolean leftFlag = false;
        for (String ob : periodList) {
            leftFlag = true;
            ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setGroup(projSelDTO.getPeriodListMap().get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            }
            projectionTotalList.add(projDTO);
        }
        if (projSelDTO.getProjectionOrder().equals(ASCENDING.getConstant())) {
            if (leftFlag) {
                Collections.sort(projectionTotalList, new ProjectionResultsDTO());
            }
        } else {
            if (leftFlag) {
                Collections.sort(projectionTotalList, new ProjectionResultsDTO());
            }
            Collections.reverse(projectionTotalList);
        }
    }

    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains("null")) {
            value = "0";
        } 
            Double newValue = Double.valueOf(value);
            if (FORMAT.toPattern().contains("%")) {
                newValue = newValue / NumericConstants.HUNDRED;
            }
            value = FORMAT.format(newValue);
                return value;
    }

    public void getProjectionTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("= = = Inside getProjectionTotal = = =");
        List<Object[]> gtsList = CommonLogic.callProcedure("Prc_cff_results", orderedArgs);
        if (gtsList != null) {
            getCustomizedProjectionTotal(gtsList, projSelDTO);
        }
        LOGGER.debug("= = = Ending getProjectionTotal = = =");
    }

    public void getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("= = = Inside getCustomizedProjectionTotal = = =");
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        ProjectionResultsDTO exFactoryDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO demandDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO inventoryDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO perExFactoryDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO perDemandDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO perInventoryDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO conSaleDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO unitVolDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO discountPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO totalRPUDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO discountDolDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO netSaleDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO cogsDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO netprofitDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO ppaPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO ppaRPUDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO ppaDolDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO exFactoryCustDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO adjDemandDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO inventoryDetailsDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO perExFactoryCustDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO perAdjDemandDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO perInventoryDetailsDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO discountPercentExFactoryDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO netSalesPercentageExFactoryDTO = new ProjectionResultsDTO();
        exFactoryDTO.setParent(0);
        exFactoryDTO.setGroup(EX_FACTORY_SALES.getConstant());

        demandDTO.setParent(0);
        demandDTO.setGroup(DEMAND_SALES.getConstant());

        inventoryDTO.setParent(0);
        inventoryDTO.setGroup(INVENTORY_WITHDRAW.getConstant());

        perExFactoryDTO.setParent(0);
        perExFactoryDTO.setGroup(PERC_OF_EX_FACTORY.getConstant());

        perDemandDTO.setParent(0);
        perDemandDTO.setGroup(PERC_OF_DEMAND.getConstant());

        perInventoryDTO.setParent(0);
        perInventoryDTO.setGroup(PERC_OF_INVENTORY_WITHDRAW.getConstant());

        conSaleDTO.setParent(0);
        conSaleDTO.setGroup(CONTRACT_SALES_AT_WAC.getConstant());

        unitVolDTO.setParent(0);
        unitVolDTO.setGroup(UNIT_VOL.getConstant());

        exFactoryCustDTO.setParent(0);
        exFactoryCustDTO.setGroup(EX_FACTORY_CUSTOMER.getConstant());

        adjDemandDTO.setParent(0);
        adjDemandDTO.setGroup(ADJUSTED_DEMAND.getConstant());

        inventoryDetailsDTO.setParent(0);
        inventoryDetailsDTO.setGroup(INVENTORY_WITHDRAW_DETAILS.getConstant());

        perExFactoryCustDTO.setParent(0);
        perExFactoryCustDTO.setGroup(PERC_OF_EX_FACTORY_CUST.getConstant());

        perAdjDemandDTO.setParent(0);
        perAdjDemandDTO.setGroup(PERC_OF_ADJ_DEMAND.getConstant());

        perInventoryDetailsDTO.setParent(0);
        perInventoryDetailsDTO.setGroup(PERC_OF_INVENTORY_WITHDRAW_DETIALS.getConstant());

        discountPerDTO.setParent(1);
        discountPerDTO.setGroup(TOTAL_DISCOUNT_PERC.getConstant());
        discountPerDTO.setProjectionTotal(1);
        discountPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        discountPerDTO.setOnExpandTotalRow(0);

        totalRPUDTO.setParent(1);
        totalRPUDTO.setGroup(TOTAL_RPU.getConstant());
        totalRPUDTO.setProjectionTotal(1);
        totalRPUDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalRPUDTO.setOnExpandTotalRow(0);

        discountDolDTO.setParent(1);
        discountDolDTO.setGroup(TOTAL_DISCOUNT_AMOUNT.getConstant());
        discountDolDTO.setProjectionTotal(1);
        discountDolDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        discountDolDTO.setOnExpandTotalRow(0);

        discountPercentExFactoryDTO.setParent(1);
        discountPercentExFactoryDTO.setGroup(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant());
        discountPercentExFactoryDTO.setProjectionTotal(1);
        discountPercentExFactoryDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        discountPercentExFactoryDTO.setOnExpandTotalRow(0);

        netSaleDTO.setParent(0);
        netSaleDTO.setGroup(NET_SALES.getConstant());
        if (projSelDTO.isPpa()) {
            ppaPerDTO.setParent(0);
            ppaPerDTO.setGroup(PPA_DISCOUNT.getConstant());

            ppaRPUDTO.setParent(0);
            ppaRPUDTO.setGroup(PPA_DISCOUNT.getConstant());

            ppaDolDTO.setParent(0);
            ppaDolDTO.setGroup(PPA_DISCOUNT.getConstant());
        }
        cogsDTO.setParent(0);
        cogsDTO.setGroup(COGS.getConstant());

        netprofitDTO.setParent(0);
        netprofitDTO.setGroup(NET_PROFIT.getConstant());

        netSalesPercentageExFactoryDTO.setParent(0);
        netSalesPercentageExFactoryDTO.setGroup(NET_SALES_PERCENTAGE_EXFACTORY.getConstant());

        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(StringConstantsUtil.GROUP_PROPERTY);
        if (list != null && !list.isEmpty()) {
            int col = NumericConstants.FIVE;
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = "";
                int year = 0;
                if (obj[NumericConstants.THREE] != null) {
                    year = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
                }
                int period = 0;
                if (obj[NumericConstants.FOUR] != null) {
                    period = Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR]));
                }
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);

                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = "" + obj[1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    exFactoryDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.FORTY_NINE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    exFactoryCustDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.FORTY_SEVEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    adjDemandDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.FIFTY_ONE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    inventoryDetailsDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.FIFTY_FIVE];
                    value = getFormattedValue(PER_TWO, value);
                    perExFactoryCustDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.FIFTY_THREE];
                    value = getFormattedValue(PER_TWO, value);
                    perAdjDemandDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.FIFTY_SEVEN];
                    value = getFormattedValue(PER_TWO, value);
                    perInventoryDetailsDTO.addStringProperties(column, value);

                    value = getCellValue(obj[NumericConstants.FIVE], StringConstantsUtil.SALES);
                    conSaleDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.SEVEN], StringConstantsUtil.UNITS1);
                    unitVolDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.NINE], StringConstantsUtil.SALES);
                    discountDolDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.ELEVEN], StringConstantsUtil.PERCENT1);
                    discountPerDTO.addStringProperties(column, value);
                    value = "" + obj[NumericConstants.THIRTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    perExFactoryDTO.addStringProperties(column, value);
                    if (projSelDTO.isPpa()) {
                        value = "" + obj[NumericConstants.FOURTEEN];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaDolDTO.addStringProperties(column, value);
                        value = "" + obj[col + NumericConstants.TWELVE];
                        value = getFormattedValue(PER_TWO, value);
                        ppaPerDTO.addStringProperties(column, value);
                        value = "" + obj[col + NumericConstants.TWENTY_SIX];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaRPUDTO.addStringProperties(column, value);
                    }
                    value = getCellValue(obj[NumericConstants.TWENTY_SEVEN], StringConstantsUtil.SALES);
                    netSaleDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.TWENTY_NINE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    demandDTO.addStringProperties(column, value);
                    value = "" + obj[NumericConstants.THIRTY_ONE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    inventoryDTO.addStringProperties(column, value);
                    value = "" + obj[NumericConstants.THIRTY_THREE];
                    value = getFormattedValue(PER_TWO, value);
                    perDemandDTO.addStringProperties(column, value);
                    value = "" + obj[NumericConstants.THIRTY_FIVE];
                    value = getFormattedValue(PER_TWO, value);
                    perInventoryDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.FORTY_ONE], StringConstantsUtil.SALES);
                    totalRPUDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.FORTY_THREE], StringConstantsUtil.SALES);
                    cogsDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.FORTY_FIVE], StringConstantsUtil.SALES);
                    netprofitDTO.addStringProperties(column, value);
                    //Discount % of Ex-Factory - Actuals
                    value = getFormattedValue(PER_TWO, String.valueOf(obj[NumericConstants.SIXTY_ONE]));
                    discountPercentExFactoryDTO.addStringProperties(column, value);
                    //Net Sales % of Ex-Factory - Actuals
                    value = getFormattedValue(PER_TWO, String.valueOf(obj[NumericConstants.FIFTY_NINE]));
                    netSalesPercentageExFactoryDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();

                if (projSelDTO.hasColumn(column)) {
                    String value = "" + obj[NumericConstants.TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    exFactoryDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.FIFTY];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    exFactoryCustDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.FORTY_EIGHT];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    adjDemandDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.FIFTY_TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    inventoryDetailsDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.FIFTY_SIX];
                    value = getFormattedValue(PER_TWO, value);
                    perExFactoryCustDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.FIFTY_FOUR];
                    value = getFormattedValue(PER_TWO, value);
                    perAdjDemandDTO.addStringProperties(column, value);

                    value = "" + obj[NumericConstants.FIFTY_EIGHT];
                    value = getFormattedValue(PER_TWO, value);
                    perInventoryDetailsDTO.addStringProperties(column, value);

                    value = getCellValue(obj[NumericConstants.SIX], StringConstantsUtil.SALES);
                    conSaleDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.EIGHT], StringConstantsUtil.UNITS1);
                    unitVolDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.TEN], StringConstantsUtil.SALES);
                    discountDolDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.TWELVE], StringConstantsUtil.PERCENT1);
                    discountPerDTO.addStringProperties(column, value);
                    value = "" + obj[NumericConstants.FOURTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    perExFactoryDTO.addStringProperties(column, value);
                    if (projSelDTO.isPpa()) {
                        value = "" + obj[NumericConstants.FIFTEEN];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaDolDTO.addStringProperties(column, value);
                        value = "" + obj[col + NumericConstants.THIRTEEN];
                        value = getFormattedValue(PER_TWO, value);
                        ppaPerDTO.addStringProperties(column, value);
                        value = "" + obj[col + NumericConstants.TWENTY_SEVEN];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaRPUDTO.addStringProperties(column, value);
                    }
                    value = getCellValue(obj[NumericConstants.TWENTY_EIGHT], StringConstantsUtil.SALES);
                    netSaleDTO.addStringProperties(column, value);
                    value = "" + obj[NumericConstants.THIRTY];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    demandDTO.addStringProperties(column, value);
                    value = "" + obj[NumericConstants.THIRTY_TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    inventoryDTO.addStringProperties(column, value);
                    value = "" + obj[NumericConstants.THIRTY_FOUR];
                    value = getFormattedValue(PER_TWO, value);
                    perDemandDTO.addStringProperties(column, value);
                    value = "" + obj[NumericConstants.THIRTY_SIX];
                    value = getFormattedValue(PER_TWO, value);
                    perInventoryDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.FORTY_TWO], StringConstantsUtil.SALES);
                    totalRPUDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.FORTY_FOUR], StringConstantsUtil.SALES);
                    cogsDTO.addStringProperties(column, value);
                    value = getCellValue(obj[NumericConstants.FORTY_SIX], StringConstantsUtil.SALES);
                    netprofitDTO.addStringProperties(column, value);
                    //Discount % of Ex-Factory - Actuals
                    value = getFormattedValue(PER_TWO, String.valueOf(obj[NumericConstants.SIXTY_TWO]));
                    discountPercentExFactoryDTO.addStringProperties(column, value);
                    //Net Sales % of Ex-Factory - Actuals
                    value = getFormattedValue(PER_TWO, String.valueOf(obj[NumericConstants.SIXTY]));
                    netSalesPercentageExFactoryDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            exFactoryDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            exFactoryCustDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            adjDemandDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            inventoryDetailsDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            perExFactoryCustDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            perAdjDemandDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            perInventoryDetailsDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));

            demandDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            inventoryDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            perExFactoryDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
            perDemandDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
            perInventoryDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
            conSaleDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            unitVolDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, "null"));
            discountPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
            totalRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            discountDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            discountPercentExFactoryDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
            netSaleDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            netSalesPercentageExFactoryDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
            cogsDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            netprofitDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            if (projSelDTO.isPpa()) {
                ppaPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
                ppaRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
                ppaDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            }
        }
        projDTOList.add(exFactoryDTO);
        projDTOList.add(exFactoryCustDTO);
        projDTOList.add(demandDTO);
        projDTOList.add(adjDemandDTO);
        projDTOList.add(inventoryDTO);
        projDTOList.add(inventoryDetailsDTO);
        projDTOList.add(perExFactoryDTO);
        projDTOList.add(perExFactoryCustDTO);
        projDTOList.add(perDemandDTO);
        projDTOList.add(perAdjDemandDTO);
        projDTOList.add(perInventoryDTO);
        projDTOList.add(perInventoryDetailsDTO);
        projDTOList.add(conSaleDTO);
        projDTOList.add(unitVolDTO);
        projDTOList.add(discountPerDTO);
        projDTOList.add(totalRPUDTO);
        projDTOList.add(discountDolDTO);
        projDTOList.add(discountPercentExFactoryDTO);
        projDTOList.add(netSaleDTO);
        projDTOList.add(netSalesPercentageExFactoryDTO);
        projDTOList.add(cogsDTO);
        projDTOList.add(netprofitDTO);
        if (projSelDTO.isPpa()) {
            projDTOList.add(ppaPerDTO);
            projDTOList.add(ppaRPUDTO);
            projDTOList.add(ppaDolDTO);
        }
        projectionTotalList.addAll(projDTOList);
        LOGGER.debug("= = = Ending getCustomizedProjectionTotal = = =");
    }

    public List<ProjectionResultsDTO> getProjectionTotalDiscounts(ProjectionSelectionDTO projSelDTO, Object[] orderedArgs, String group) {
      
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        if (prjTotalDisDolDtoList.isEmpty() || prjTotalDisPerDtoList.isEmpty() || prjTotalRPUDtoList.isEmpty()) {
            Object[] orderedArgs2 = {orderedArgs[0], orderedArgs[1], orderedArgs[NumericConstants.TWO], null, null ,null, null, StringConstantsUtil.EXCEL};
            List<Object[]> gtsList = CommonLogic.callProcedure(PRC_CFF_PROJECTION_RESULTS_DISCOUNT, orderedArgs2);
            projDTOList =getCustomizedDiscountsProjectionTotal(gtsList, projSelDTO,group);
        }
        return projDTOList;
    }

   public List<ProjectionResultsDTO> getCustomizedDiscountsProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO,String group) {

        ProjectionResultsDTO discountNameDTO = new ProjectionResultsDTO();
        List<ProjectionResultsDTO> discountNameList = new ArrayList<>();
        String oldDiscountName = "";
        String newDiscountName = "";
        String column;
        loadMap();
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            newDiscountName = String.valueOf(obj[NumericConstants.THREE]);
            if (oldDiscountName.equals(newDiscountName)) {
                column = getCommonColumn(projSelDTO, obj);
                String property = column + ACTUALS.getConstant();

                if (group.equals(TOTAL_DISCOUNT_PERC.getConstant())) {
                    String value = getCellValue(obj[NumericConstants.SIX], StringConstantsUtil.PERCENT1);
                    discountNameDTO.addStringProperties(property, value);
                    String projProperty = column + PROJECTIONS.getConstant();
                    value = getCellValue(obj[NumericConstants.SEVEN], StringConstantsUtil.PERCENT1);
                    discountNameDTO.addStringProperties(projProperty, value);
                } else if (group.equals(TOTAL_RPU.getConstant())) {
                    String value = getCellValue(obj[NumericConstants.EIGHT], StringConstantsUtil.SALES);
                    discountNameDTO.addStringProperties(property, value);
                    String projProperty = column + PROJECTIONS.getConstant();
                    value = getCellValue(obj[NumericConstants.NINE], StringConstantsUtil.SALES);
                    discountNameDTO.addStringProperties(projProperty, value);
                } else if (group.equals(TOTAL_DISCOUNT_AMOUNT.getConstant())) {
                    String value = getCellValue(obj[NumericConstants.FOUR], StringConstantsUtil.SALES);
                    discountNameDTO.addStringProperties(property, value);
                    String projProperty = column + PROJECTIONS.getConstant();
                    value = getCellValue(obj[NumericConstants.FIVE], StringConstantsUtil.SALES);
                    discountNameDTO.addStringProperties(projProperty, value);
                } else if (group.equals(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                    String value = getCellValue(obj[NumericConstants.TEN], StringConstantsUtil.PERCENT1);
                    discountNameDTO.addStringProperties(property, value);
                    String projProperty = column + PROJECTIONS.getConstant();
                    value = getCellValue(obj[NumericConstants.ELEVEN], StringConstantsUtil.PERCENT1);
                    discountNameDTO.addStringProperties(projProperty, value);
                }
            } else {
                if (i != 0) {
                    discountNameList.add(discountNameDTO);
                }
                oldDiscountName = String.valueOf(obj[NumericConstants.THREE]);
                discountNameDTO = new ProjectionResultsDTO();
                discountNameDTO.addStringProperties(StringConstantsUtil.GROUP_PROPERTY, oldDiscountName);
                column = getCommonColumn(projSelDTO, obj);
                if (group.equals(TOTAL_DISCOUNT_PERC.getConstant())) {
                    String property = column + ACTUALS.getConstant();
                    String value = getCellValue(obj[NumericConstants.SIX], StringConstantsUtil.PERCENT1);
                    discountNameDTO.addStringProperties(property, value);
                    String projProperty = column + PROJECTIONS.getConstant();
                    value = getCellValue(obj[NumericConstants.SEVEN], StringConstantsUtil.PERCENT1);
                    discountNameDTO.addStringProperties(projProperty, value);
                } else if (group.equals(TOTAL_RPU.getConstant())) {
                    String property = column + ACTUALS.getConstant();
                    String value = getCellValue(obj[NumericConstants.EIGHT], StringConstantsUtil.SALES);
                    discountNameDTO.addStringProperties(property, value);
                    String projProperty = column + PROJECTIONS.getConstant();
                    value = getCellValue(obj[NumericConstants.NINE], StringConstantsUtil.SALES);
                    discountNameDTO.addStringProperties(projProperty, value);
                } else if (group.equals(TOTAL_DISCOUNT_AMOUNT.getConstant())) {
                    String property = column + ACTUALS.getConstant();
                    String value = getCellValue(obj[NumericConstants.FOUR], StringConstantsUtil.SALES);
                    discountNameDTO.addStringProperties(property, value);
                    String projProperty = column + PROJECTIONS.getConstant();
                    value = getCellValue(obj[NumericConstants.FIVE], StringConstantsUtil.SALES);
                    discountNameDTO.addStringProperties(projProperty, value);
                } else if (group.equals(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                    String property = column + ACTUALS.getConstant();
                    String value = getCellValue(obj[NumericConstants.TEN], StringConstantsUtil.PERCENT1);
                    discountNameDTO.addStringProperties(property, value);
                    String projProperty = column + PROJECTIONS.getConstant();
                    value = getCellValue(obj[NumericConstants.ELEVEN], StringConstantsUtil.PERCENT1);
                    discountNameDTO.addStringProperties(projProperty, value);
                }
            }
            if (i == list.size() - 1) {
                discountNameList.add(discountNameDTO);
            }
        }
        return discountNameList;
    }





    public List<ProjectionResultsDTO> getProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("= = = Inside getProjectionResults = = = start"+start+" offset - "+offset);
        int started = start;
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        String freq = "";
        if (projSelDTO.getFrequencyDivision() == 1) {
            freq = "ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            freq = "SEMI-ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            freq = "MONTHLY";
        }

      
      
       Object[] orderedArgs = {projSelDTO.getProjectionId(), freq, StringConstantsUtil.ASSUMPTIONS};
            if (projSelDTO.isIsTotal() && projSelDTO.isIsProjectionTotal()) {
                    if (started == 0) {
                        if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                            ProjectionResultsDTO dto = new ProjectionResultsDTO();
                            dto.setGroup("CFF Total");
                            dto.setParent(0);
                            projDTOList.add(dto);
                        }
                        neededRecord--;
                        started++;
                    }
                    mayBeAdded++;
            }
            if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                String salesUnits = projSelDTO.getSalesOrUnit();
                if (projSelDTO.isIsTotal()) {
                    LOGGER.debug("= = = Inside Period isTotal method = = =");
                    if (projSelDTO.isIsProjectionTotal()) {
                        LOGGER.debug("= = = Inside Period isTotal = = = isIsProjectionTotal method = = =");
                        if (projectionTotalList.isEmpty()) {
                            getProjectionTotal(orderedArgs, projSelDTO);
                        }
                        if (started == 1 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO exFactoryProductDto = projectionTotalList.get(0);
                                projDTOList.add(exFactoryProductDto);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == NumericConstants.TWO && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO exFactoryCustomerDto = projectionTotalList.get(1);
                                projDTOList.add(exFactoryCustomerDto);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == NumericConstants.THREE && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO demandDTO = projectionTotalList.get(NumericConstants.TWO);
                                projDTOList.add(demandDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == NumericConstants.FOUR && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO adjustedDemandDTO = projectionTotalList.get(NumericConstants.THREE);
                                projDTOList.add(adjustedDemandDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == NumericConstants.FIVE && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO inventoryWDSummaryDTO = projectionTotalList.get(NumericConstants.FOUR);
                                projDTOList.add(inventoryWDSummaryDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == NumericConstants.SIX && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO inventoryWDDetailsDTO = projectionTotalList.get(NumericConstants.FIVE);
                                projDTOList.add(inventoryWDDetailsDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == NumericConstants.SEVEN && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO perExfactoryProdDTO = projectionTotalList.get(NumericConstants.SIX);
                                projDTOList.add(perExfactoryProdDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == NumericConstants.EIGHT && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO perExfactoryProdDTO = projectionTotalList.get(NumericConstants.SEVEN);
                                projDTOList.add(perExfactoryProdDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == NumericConstants.NINE && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO perDemandDTO = projectionTotalList.get(NumericConstants.EIGHT);
                                projDTOList.add(perDemandDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == NumericConstants.TEN && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO perAdjDemandDTO = projectionTotalList.get(NumericConstants.NINE);
                                projDTOList.add(perAdjDemandDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == NumericConstants.ELEVEN && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO perInvWDSumDTO = projectionTotalList.get(NumericConstants.TEN);
                                projDTOList.add(perInvWDSumDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == NumericConstants.TWELVE && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO perInvWDDetDTO = projectionTotalList.get(NumericConstants.ELEVEN);
                                projDTOList.add(perInvWDDetDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        mayBeAdded+=NumericConstants.TWELVE;
                        if (neededRecord > 0 && (salesUnits.equals(BOTH) || salesUnits.equals(SALES.getConstant()))) {
                            if (started == NumericConstants.THIRTEEN) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO contractSalesDto = projectionTotalList.get(NumericConstants.TWELVE);
                                    projDTOList.add(contractSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && (salesUnits.equals(BOTH) || salesUnits.equals(UNITS.getConstant()))) {
                            if ((salesUnits.equals(BOTH) && started == NumericConstants.FOURTEEN) || started == NumericConstants.THIRTEEN) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO unitVolDto = projectionTotalList.get(NumericConstants.THIRTEEN);
                                    projDTOList.add(unitVolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_AMOUNT.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_RPU.getConstant()) && !projSelDTO.getGroup().equals(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                            if ((salesUnits.equals(BOTH) && started == NumericConstants.FIFTEEN) || started == NumericConstants.FOURTEEN) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountPerDto = projectionTotalList.get(NumericConstants.FOURTEEN);
                                    projDTOList.add(discountPerDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_PERC.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_AMOUNT.getConstant()) && !projSelDTO.getGroup().equals(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                            if ((salesUnits.equals(BOTH) && started == NumericConstants.SIXTEEN) || started == NumericConstants.FIFTEEN) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO totalRPUDto = projectionTotalList.get(NumericConstants.FIFTEEN);
                                    projDTOList.add(totalRPUDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_PERC.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_RPU.getConstant()) && !projSelDTO.getGroup().equals(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                            if ((salesUnits.equals(BOTH) && started == NumericConstants.SEVENTEEN) || started == NumericConstants.SIXTEEN) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountDolDto = projectionTotalList.get(NumericConstants.SIXTEEN);
                                    projDTOList.add(discountDolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_PERC.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_RPU.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_AMOUNT.getConstant())) {
                            if ((salesUnits.equals(BOTH) && started == NumericConstants.EIGHTEEN) || started == NumericConstants.SEVENTEEN) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountDolDto = projectionTotalList.get(NumericConstants.SEVENTEEN);
                                    projDTOList.add(discountDolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH) && started == NumericConstants.NINETEEN) || started == NumericConstants.EIGHTEEN) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO netSalesDto = projectionTotalList.get(NumericConstants.EIGHTEEN);
                                    projDTOList.add(netSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH) && started == NumericConstants.TWENTY) || started == NumericConstants.NINETEEN) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO netSalesDto = projectionTotalList.get(NumericConstants.NINETEEN);
                                    projDTOList.add(netSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH) && started == NumericConstants.TWENTY_ONE) || started == NumericConstants.TWENTY) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO cogsDto = projectionTotalList.get(NumericConstants.TWENTY);
                                    projDTOList.add(cogsDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH) && started == NumericConstants.TWENTY_TWO) || started == NumericConstants.TWENTY_ONE) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                   
                                    ProjectionResultsDTO netProfitDto = projectionTotalList.get(NumericConstants.TWENTY_ONE);
                                    projDTOList.add(netProfitDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                    } else if (neededRecord > 0) {
                        //Added for CR CEL-376
                        boolean isExFactoryNeededInProductLevel = "P".equals(projSelDTO.getHierarchyIndicator());
                        List<ProjectionResultsDTO> resultList = getQueryForHierarchyExpand(projSelDTO);
                        ProjectionResultsDTO contractSalesDto = null;
                        ProjectionResultsDTO unitVolDto = null;
                        //Added for CR CEL-376
                        if (neededRecord > 0) {
                            if (isExFactoryNeededInProductLevel && started == NumericConstants.ZERO) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO productViewExFactoryDTO = resultList.get(NumericConstants.ELEVEN);
                                    projDTOList.add(productViewExFactoryDTO);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if ((salesUnits.equals(BOTH) && started < NumericConstants.FOUR) || started < NumericConstants.FOUR) {
                            contractSalesDto = resultList.get(0);
                            unitVolDto = resultList.get(1);
                        }
                        if (salesUnits.equals(BOTH) || salesUnits.equals(SALES.getConstant())) {
                            if ((isExFactoryNeededInProductLevel && started == NumericConstants.ONE) || started == NumericConstants.ZERO) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    projDTOList.add(contractSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((isExFactoryNeededInProductLevel && (started == NumericConstants.TWO || started == NumericConstants.ONE))
                                    || (started == NumericConstants.ONE || started == NumericConstants.ZERO)) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO percentageExfactoryProduct = resultList.get(NumericConstants.EIGHT);
                                    projDTOList.add(percentageExfactoryProduct);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && (salesUnits.equals(BOTH) || salesUnits.equals(UNITS.getConstant()))) {
                            /**
                             * The following if condition
                             * ((salesUnits.equals(BOTH) && (isExFactoryNeededInProductLevel && started == NumericConstants.THREE)) || (isExFactoryNeededInProductLevel && started == NumericConstants.TWO))
                             * will be used only in the product level or product view.
                             * else the below condition will be checked for non product levels and customer view
                             * ((salesUnits.equals(BOTH) && started == NumericConstants.TWO) || started == NumericConstants.ONE)
                             * 
                             * All the below if will work as explained above
                             * 
                             */
                            if (((salesUnits.equals(BOTH) && (isExFactoryNeededInProductLevel && started == NumericConstants.THREE)) || (isExFactoryNeededInProductLevel && started == NumericConstants.TWO))
                                    || ((salesUnits.equals(BOTH) && started == NumericConstants.TWO) || started == NumericConstants.ONE)) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    projDTOList.add(unitVolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_AMOUNT.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_RPU.getConstant()) && !projSelDTO.getGroup().equals(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                            if (((salesUnits.equals(BOTH) && (isExFactoryNeededInProductLevel && started == NumericConstants.FOUR)) || (isExFactoryNeededInProductLevel && started == NumericConstants.THREE))
                                    || ((salesUnits.equals(BOTH) && started == NumericConstants.THREE) || started == NumericConstants.TWO)) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountPerDtoList = resultList.get(NumericConstants.TWO);
                                    projDTOList.add(discountPerDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_AMOUNT.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_PERC.getConstant()) && !projSelDTO.getGroup().equals(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                            if (((salesUnits.equals(BOTH) && (isExFactoryNeededInProductLevel && started == NumericConstants.FIVE)) || (isExFactoryNeededInProductLevel && started == NumericConstants.FOUR))
                                    || ((salesUnits.equals(BOTH) && started == NumericConstants.FOUR) || started == NumericConstants.THREE)) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO totalRPUDtoList = resultList.get(NumericConstants.THREE);
                                    projDTOList.add(totalRPUDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_PERC.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_RPU.getConstant()) && !projSelDTO.getGroup().equals(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                            if (((salesUnits.equals(BOTH) && (isExFactoryNeededInProductLevel && started == NumericConstants.SIX)) || (isExFactoryNeededInProductLevel && started == NumericConstants.FIVE))
                                    || ((salesUnits.equals(BOTH) && started == NumericConstants.FIVE) || started == NumericConstants.FOUR)) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountDolarDtoList = resultList.get(NumericConstants.FOUR);
                                    projDTOList.add(discountDolarDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_AMOUNT.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_RPU.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_PERC.getConstant())) {
                            if (((salesUnits.equals(BOTH) && (isExFactoryNeededInProductLevel && started == NumericConstants.SEVEN)) || (isExFactoryNeededInProductLevel && started == NumericConstants.SIX))
                                    || ((salesUnits.equals(BOTH) && started == NumericConstants.SIX) || started == NumericConstants.FIVE)) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountDolarDtoList = resultList.get(NumericConstants.NINE);
                                    projDTOList.add(discountDolarDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if (((salesUnits.equals(BOTH) && (isExFactoryNeededInProductLevel && started == NumericConstants.EIGHT)) || (isExFactoryNeededInProductLevel && started == NumericConstants.SEVEN))
                                    || ((salesUnits.equals(BOTH) && started == NumericConstants.SEVEN) || started == NumericConstants.SIX)) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO netSalesDto = resultList.get(NumericConstants.FIVE);
                                    projDTOList.add(netSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if (((salesUnits.equals(BOTH) && (isExFactoryNeededInProductLevel && started == NumericConstants.NINE)) || (isExFactoryNeededInProductLevel && started == NumericConstants.EIGHT))
                                    || ((salesUnits.equals(BOTH) && started == NumericConstants.EIGHT) || started == NumericConstants.SEVEN)) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO netSalesPercentageExFactoryDto = resultList.get(NumericConstants.TEN);
                                    projDTOList.add(netSalesPercentageExFactoryDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if (((salesUnits.equals(BOTH) && (isExFactoryNeededInProductLevel && started == NumericConstants.TEN)) || (isExFactoryNeededInProductLevel && started == NumericConstants.NINE))
                                    || ((salesUnits.equals(BOTH) && started == NumericConstants.NINE) || started == NumericConstants.EIGHT)) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO cogsDto = resultList.get(NumericConstants.SIX);
                                    projDTOList.add(cogsDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if (((salesUnits.equals(BOTH) && (isExFactoryNeededInProductLevel && started == NumericConstants.ELEVEN)) || (isExFactoryNeededInProductLevel && started == NumericConstants.TEN))
                                    || ((salesUnits.equals(BOTH) && started == NumericConstants.TEN) || started == NumericConstants.NINE)) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO netProfitDto = resultList.get(NumericConstants.SEVEN);
                                    projDTOList.add(netProfitDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                    }
                } else if (neededRecord > 0) {
                    //For returns
                    if (!projSelDTO.getGroup().equals(TOTAL_DISCOUNT_AMOUNT.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_RPU.getConstant()) && !projSelDTO.getGroup().equals(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                        List<ProjectionResultsDTO> discountPerDtoList;
                        if (projSelDTO.isIsProjectionTotal()) {

                            discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs, TOTAL_DISCOUNT_PERC.getConstant());
                        } else {
                            discountPerDtoList = getTotalDiscountLevels(projSelDTO, TOTAL_DISCOUNT_PERC.getConstant());
                        }
                        for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                            if (!projSelDTO.hasNonFetchableIndex("" + k)) {
                                projDTOList.add(discountPerDtoList.get(k));
                            }
                            started++;
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                    }
                    if (!projSelDTO.getGroup().equals(TOTAL_DISCOUNT_AMOUNT.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_PERC.getConstant()) && !projSelDTO.getGroup().equals(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                        List<ProjectionResultsDTO> discountPerDtoList;
                        if (projSelDTO.isIsProjectionTotal()) {
                            discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs, TOTAL_RPU.getConstant());
                        } else {
                            discountPerDtoList = getTotalDiscountLevels(projSelDTO, TOTAL_RPU.getConstant());
                        }
                        for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                            if (!projSelDTO.hasNonFetchableIndex("" + k)) {
                                projDTOList.add(discountPerDtoList.get(k));
                            }
                            started++;
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                    }
                    if (neededRecord > 0 && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_PERC.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_RPU.getConstant()) && !projSelDTO.getGroup().equals(DISCOUNT_PERCENTAGE_EXFACTORY.getConstant())) {
                        List<ProjectionResultsDTO> discountPerDtoList;
                        if (projSelDTO.isIsProjectionTotal()) {
                            discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs, TOTAL_DISCOUNT_AMOUNT.getConstant());
                        } else {
                            discountPerDtoList = getTotalDiscountLevels(projSelDTO, TOTAL_DISCOUNT_AMOUNT.getConstant());
                        }
                        for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                            if (!projSelDTO.hasNonFetchableIndex("" + k)) {
                                projDTOList.add(discountPerDtoList.get(k));
                            }
                            started++;
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                    }
                    if (neededRecord > 0 && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_PERC.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_RPU.getConstant()) && !projSelDTO.getGroup().equals(TOTAL_DISCOUNT_AMOUNT.getConstant())) {
                        List<ProjectionResultsDTO> discountPerDtoList;
                        if (projSelDTO.isIsProjectionTotal()) {
                            discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs, DISCOUNT_PERCENTAGE_EXFACTORY.getConstant());
                        } else {
                            discountPerDtoList = getTotalDiscountLevels(projSelDTO, DISCOUNT_PERCENTAGE_EXFACTORY.getConstant());
                        }
                        for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                            if (!projSelDTO.hasNonFetchableIndex("" + k)) {
                                projDTOList.add(discountPerDtoList.get(k));
                            }
                            started++;
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                    }
                    LOGGER.debug("= = = Ending Period = = = neededRecord > 0 =started =" + started + "= neededRecord =" + neededRecord + "= maybeadded= " + mayBeAdded);
                }

            } else if (neededRecord > 0) {
               
                int mayBeAddedRecord = started - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                if (mayBeAddedRecord < projSelDTO.getPeriodList().size()) {
                    List<ProjectionResultsDTO> projectionDtoList;
                   
                    if (projSelDTO.isIsProjectionTotal()) {
                         Object[] orderedArgs1 = {projSelDTO.getProjectionId(), projSelDTO.getFrequency(), StringConstantsUtil.ASSUMPTIONS, StringConstantsUtil.PIVOT_LABEL, null, null, null, StringConstantsUtil.EXCEL};
                          List<Object[]> discountsList = CommonLogic.callProcedure(PRC_CFF_PROJECTION_RESULTS_DISCOUNT, orderedArgs1);
                        projectionDtoList = getProjectionPivotTotal(projSelDTO, discountsList);
                    } else {
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
                        Object[] orderedArgs2 = {projSelDTO.getProjectionId(), projSelDTO.getFrequency(), StringConstantsUtil.ASSUMPTIONS, StringConstantsUtil.PIVOT_LABEL, null, ccps, null, StringConstantsUtil.EXCEL};
                        List<Object[]> discountsList = CommonLogic.callProcedure(PRC_CFF_PROJECTION_RESULTS_DISCOUNT, orderedArgs2);
                        projectionDtoList = getProjectionPivot(projSelDTO, discountsList);
                        
                        
                        
                    }
                    for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; k++,neededRecord--) {
                        int l=k;
                        if (projSelDTO.isIsProjectionTotal()) {
                            l++;
                        }
                        
                        if (!projSelDTO.hasNonFetchableIndex("" + l)) {
                            
                            projDTOList.add(projectionDtoList.get(k));
                        }
                        started++;
                    }
                }
                mayBeAdded += projSelDTO.getPeriodList().size();
        }
        if (!(projSelDTO.isIsCustomHierarchy() && projSelDTO.getCustomId() == 0) && neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsFilter()) {
            int mayBeAddedRecord = started - mayBeAdded;
            if (mayBeAddedRecord < 0) {
                mayBeAddedRecord = 0;
            }
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
            List<ProjectionResultsDTO> nextLevelValueList = configureLevel(mayBeAddedRecord, neededRecord, started, projSelDTO);
            projDTOList.addAll(nextLevelValueList);
        }
        LOGGER.debug("= = = Ending getProjectionResults = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getConfiguredProjectionResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
        try {
            List<ProjectionResultsDTO> resultList;
            if (!projSelDTO.isIsFilter() || (parentId instanceof ProjectionResultsDTO)) {
                projSelDTO.setYear("All");

                if (projSelDTO.getActualsOrProjections().equals(BOTH)) {
                    projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + " and " + PROJECTIONS.getConstant());
                }
                if (parentId instanceof ProjectionResultsDTO) {
                    ProjectionResultsDTO parentDto = (ProjectionResultsDTO) parentId;
                    projSelDTO.setLevelNo(parentDto.getLevelNo());
                    projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                    projSelDTO.setLevelValue(parentDto.getLevelValue());
                    projSelDTO.setParentNode(parentDto.getParentNode());
                    projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                    if (parentDto.getHierarchyIndicator().equals("C")) {
                        projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                        projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    } else if (parentDto.getHierarchyIndicator().equals("P")) {
                        projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                        projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    }
                    projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                    projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
                    projSelDTO.setGroup(parentDto.getGroup());
                    projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
                    projSelDTO.setCcpIds(parentDto.getCcpIds());
                } else {
                    projSelDTO.setIsProjectionTotal(true);
                    projSelDTO.setIsTotal(true);
                    if (projSelDTO.isIsCustomHierarchy()) {
                        projSelDTO.setLevelNo(0);
                        projSelDTO.setTreeLevelNo(0);
                    } else if ("C".equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    } else if ("P".equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
                    }

                    projSelDTO.setGroup("");
                    projSelDTO.setHierarchyNo("");
                    projSelDTO.setProductHierarchyNo("");
                    projSelDTO.setCustomerHierarchyNo("");
                }
                resultList = getProjectionResults(start, offset, projSelDTO);
            } else {
                projSelDTO.setIsProjectionTotal(false);
                projSelDTO.setIsTotal(true);
                projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
                projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
                resultList = configureLevel(start, offset, start, projSelDTO);
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        }
    }

    
    public List<ProjectionResultsDTO> configureLevel(int start, int offset, int started, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        List<ProjectionResultsDTO> resultList = new ArrayList<>();
        if (neededRecord > 0) {
            resultList = getLevelListforNonmandated(start, offset, started, projSelDTO, neededRecord);
        }
        return resultList;
    }
    
    public int getConfiguredProjectionResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelsCount) {
        int count = 0;
        projSelDTO.setGroupCount(false);
        if (!projSelDTO.isIsFilter() || (parentId instanceof ProjectionResultsDTO)) {
            projSelDTO.setYear("All");

            if (projSelDTO.getActualsOrProjections().equals(BOTH)) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + " and " + PROJECTIONS.getConstant());
            }
            if (parentId instanceof ProjectionResultsDTO) {
                ProjectionResultsDTO parentDto = (ProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (parentDto.getHierarchyIndicator().equals("C")) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals("P")) {
                    projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
                projSelDTO.setCcpIds(parentDto.getCcpIds());
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                } else if ("C".equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                } else if ("P".equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
                }
                projSelDTO.setGroup("");
                projSelDTO.setHierarchyNo("");
                projSelDTO.setProductHierarchyNo("");
                projSelDTO.setCustomerHierarchyNo("");
            }
            count += getProjectionResultsCount(projSelDTO, isLevelsCount);
        } else if (isLevelsCount) {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            count += configureLevelCount(projSelDTO);
        }
        return count;
    }

    public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelsCount) {
        int count = 0;
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            if (projSelDTO.isIsTotal()) {
                count = count + NumericConstants.TEN;
                if (projSelDTO.isIsProjectionTotal()) {
                    count = count + NumericConstants.TWELVE;
                } else if ("P".equals(projSelDTO.getHierarchyIndicator())) { //Added else for CEL-376
                    count++;
                }
                if (projSelDTO.getSalesOrUnit().equals(BOTH)) {
                    count++;
                }
            } else {
                int discountCount = 0;
                discountCount = getDiscoutCount(projSelDTO);
                count = count + discountCount;
            }
        } else {
            count = count + projSelDTO.getPeriodList().size();
            if (projSelDTO.isIsProjectionTotal()) {
                count++;
            }
        }
        if (projSelDTO.isIsCustomHierarchy() && projSelDTO.getCustomId() == 0) {
            projSelDTO.setLevelCount(0);
        } else if (projSelDTO.isIsTotal() && isLevelsCount && !projSelDTO.isIsFilter()) {
            projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
            int levelCount = configureLevelCount(projSelDTO);
            count = count + levelCount;
            projSelDTO.setLevelCount(levelCount);
        }
        return count;
    }
    public int configureLevelCount(ProjectionSelectionDTO projSelDTO) {
        int levelCount = 0;
        CommonLogic commonLogic = new CommonLogic();
        if (projSelDTO.isIsCustomHierarchy()) {
            levelCount = commonLogic.getCountForCustomView(projSelDTO);
        } else {
            levelCount = commonLogic.getCount(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyIndicator());
        }
        return levelCount;
    }

    public String getProjectionResultsDiscountsQuery(ProjectionSelectionDTO projSelDTO, String orderBy) {

        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String whereClause = "";
        String groupBy = " I.\"YEAR\" \n";

        String customQuery = "";
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";

        selectClause += "I.\"YEAR\"  as  YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER  as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS,\n ";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "'Total Discount $' as DISCOUNTS \n";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            if (!projSelDTO.isIsTotal()) {
                discountTypeColumnName = " J.RS_NAME as DISCOUNTS \n";
                groupBy += ", J.RS_NAME";
            }
            whereClause += " and B.RS_MODEL_SID in (" + CollectionToString(projSelDTO.getDiscountNoList(), false) + ") \n";
        }
        selectClause += discountTypeColumnName + ", ";

        String customSql = "  ST_NM_DISCOUNT_PROJ_MASTER B,\n"
                + " PROJECTION_DETAILS E , \n"
                + "\"PERIOD\" I, \n"
                + " @CCP CCP ";
        if (!projSelDTO.isIsTotal()) {
            customSql += ", RS_MODEL J \n";
        }
        customSql += "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n"
                + " and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B")
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A");
        if (!projSelDTO.isIsTotal()) {
            customSql += "and B.RS_MODEL_SID= J.RS_MODEL_SID \n";
        }
        customSql += "and A.RS_MODEL_SID = B.RS_MODEL_SID \n"
                + " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + " \n"
                + ccpWhereCond + "and A.PERIOD_SID =  I.PERIOD_SID \n"
                + periodFilter
                + whereClause + " group by  " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as ACTUAL_SALES, \n"
                + " sum(A.ACTUAL_PROJECTION_SALES) as PROJECTION_SALES \n"
                + " from ST_NM_ACTUAL_DISCOUNT A, \n "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES , \n"
                + " sum(A.PROJECTION_SALES) as PROJECTION_SALES \n"
                + " from ST_NM_DISCOUNT_PROJECTION A, \n "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.HISTORY1, StringConstantsUtil.FUTURE, "on");
        String finalWhereCond = list.get(1) + " and HISTORY.DISCOUNTS=FUTURE.DISCOUNTS";
        String finalSelectClause = SELECT_SPACE + list.get(0) + " Isnull(HISTORY.DISCOUNTS, FUTURE.DISCOUNTS) as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES ";
        customQuery = finalSelectClause + "  from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond + orderBy;

        return customQuery;
    }

    public String getProjectionResultsPPAQuery(ProjectionSelectionDTO projSelDTO) {

        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String whereClause = "";
        String groupBy = " I.\"YEAR\" \n";
        String customQuery = "";
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER  as  PERIODS, \n";
            whereClause += "";
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += "";
            groupBy += ",  I.\"MONTH\" ";
        }
        selectClause += "'PPA Discount' as DISCOUNTS,\n ";
        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = " PROJECTION_DETAILS E ,\n"
                + " \"PERIOD\" I, \n"
                + " @CCP CCP "
                + " where A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + "and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + ccpWhereCond
                + " and A.PERIOD_SID = I.PERIOD_SID \n"
                + periodFilter
                + whereClause
                + "  group by " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_DISCOUNT_DOLLAR) as ACTUAL_SALES,\n"
                + " sum(A.ACTUAL_PROJECTION_SALES) as PROJECTION_SALES "
                + ",sum(A.ACTUAL_PROJECTION_RATE)AS PPA_ACTUAL_RPU\n"
                + " from ST_NM_ACTUAL_PPA A,\n "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_DISCOUNT_DOLLAR) as PROJECTION_SALES ,sum(A.PROJECTION_RATE)AS PPA_PROJECTED_RPU \n"
                + " from ST_NM_PPA_PROJECTION A,\n "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.HISTORY1, StringConstantsUtil.FUTURE, "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = SELECT_SPACE + list.get(0) + "'PPA Discount' as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES"
                + "              ,ISNULL(HISTORY.PPA_ACTUAL_RPU,0)AS PPA_ACTUAL_RPU\n"
                + "              ,ISNULL(FUTURE.PPA_PROJECTED_RPU,0)AS PPA_PROJECTED_RPU ";

        customQuery = finalSelectClause + "  from (\n" + historyQuery + "\n) HISTORY  FULL  OUTER JOIN (\n" + futureQuery + "\n)  FUTURE  \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsPPARPUQuery(ProjectionSelectionDTO projSelDTO) {

        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String whereClause = "";
        String groupBy = "  I.\"YEAR\"  \n";
        String customQuery = "";
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID= E.CCP_DETAILS_SID \n";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += "  and  I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as  PERIODS, \n ";
            whereClause += "";
            groupBy += ",  I.QUARTER ";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL  as PERIODS, \n";
            whereClause += "";
            groupBy += ",  I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as  PERIODS, \n";
            whereClause += "";
            groupBy += ",  I.\"MONTH\"";
        }
        selectClause += "'PPA Discount' as DISCOUNTS,\n ";
        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = " PROJECTION_DETAILS E ,\n"
                + " \"PERIOD\" I, \n"
                + " @CCP   CCP  "
                + " where A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + "and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + ccpWhereCond
                + " and A.PERIOD_SID = I.PERIOD_SID \n"
                + periodFilter
                + whereClause
                + "  group by  " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_DISCOUNT_DOLLAR) as ACTUAL_SALES,\n"
                + " sum(A.ACTUAL_PROJECTION_SALES) as PROJECTION_SALES, sum(A.ACTUAL_PROJECTION_RATE)AS PPA_ACTUAL_RPU \n"
                + " from ST_NM_ACTUAL_PPA A,\n "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_DISCOUNT_DOLLAR) as PROJECTION_SALES,sum(A.PROJECTION_RATE)AS PPA_PROJECTED_RPU \n"
                + " from ST_NM_PPA_PROJECTION A,\n "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.HISTORY1, StringConstantsUtil.FUTURE, "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = SELECT_SPACE + list.get(0) + "'PPA Discount' as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES"
                + ",ISNULL(HISTORY.PPA_ACTUAL_RPU,0)AS PPA_ACTUAL_RPU\n"
                + "              ,ISNULL(FUTURE.PPA_PROJECTED_RPU,0)AS PPA_PROJECTED_RPU ";

        customQuery = finalSelectClause + " from  (\n" + historyQuery + "\n) HISTORY FULL OUTER  JOIN  (\n" + futureQuery + "\n) FUTURE \n " + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsSalesQuery(ProjectionSelectionDTO projSelDTO) {

        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String whereClause = "";
        String groupBy = " I.\"YEAR\"\n";
        String customQuery = "";
        String ccpWhereCond = " and  CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";
        selectClause += "I.\"YEAR\" as  YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += "  and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.QUARTER ";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as  PERIODS, \n";
            whereClause += "";
            groupBy += ",  I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as  PERIODS,  \n";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\"  as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.\"MONTH\" ";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customSql = " PROJECTION_DETAILS E , \n"
                + " \"PERIOD\" I,  \n"
                + " @CCP CCP  "
                + "where A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + "  and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID \n"
                + periodFilter
                + whereClause + "\n"
                + " group by " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                + " sum(A.HISTORY_PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, \n"
                + " sum(A.HISTORY_PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + " from ST_NM_ACTUAL_SALES A,\n "
                + customSql;
        String futureQuery = selectClause + " 0 as SALES_ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, \n"
                + " sum(A.PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + " from ST_NM_SALES_PROJECTION A,\n"
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.HISTORY1, StringConstantsUtil.FUTURE, "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = SELECT_SPACE + list.get(0) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as SALES_ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as SALES_PROJECTION_SALES,"
                + "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,\n Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS  ";

        customQuery = finalSelectClause + " from  (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN ( \n " + futureQuery + "\n ) FUTURE \n" + finalWhereCond;

        return customQuery;
    }

    public String getProjectionResultsPPAPerQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("PPA", "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'PPA Discount' as DISCOUNTS,\n";
        selectClause += " ACTUAL_RATE=Isnull(PPA.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n"
                + " PROJECTION_RATE=Isnull(PPA.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n";
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + "  from  (\n" + ppaQuery + "\n) PPA LEFT JOIN (\n" + salesQuery + "\n)  SALE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsPPARPU(ProjectionSelectionDTO projSelDTO) {
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("PPA", "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'PPA Discount' as DISCOUNTS\n";
        selectClause += " ,PPA_ACTUAL_RPU=(Isnull(PPA.PPA_ACTUAL_RPU / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0))\n"
                + "       ,PPA_PROJECTED_RPU=(Isnull(PPA.PPA_PROJECTED_RPU / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0))";
        String ppaQuery = getProjectionResultsPPARPUQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + "  from  (\n" + ppaQuery + "\n) PPA LEFT JOIN (\n" + salesQuery + "\n)  SALE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsTotalDiscountDolQuery(ProjectionSelectionDTO projSelDTO) {
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String customQuery = "";
        if (projSelDTO.isPpa()) {
            String selectClause = StringConstantsUtil.SPACE_SELECT;

            List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, "PPA", "on");
            selectClause += list.get(0);
            String finalWhereCond = list.get(1);
            selectClause += "'Total Discount $' as DISCOUNTS, \n";
            selectClause += " ACTUAL_SALES=Isnull(TODIS.ACTUAL_SALES, 0)+Isnull(PPA.ACTUAL_SALES, 0),\n"
                    + "  PROJECTION_SALES=Isnull(TODIS.PROJECTION_SALES, 0)+Isnull(PPA.PROJECTION_SALES, 0) \n";

            String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
            customQuery = selectClause + " from (\n " + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond;

        } else {
            customQuery = totalDiscountQuery;
        }
        return customQuery;
    }

    public String getProjectionResultsDiscountsPerQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "TODIS.DISCOUNTS, \n";
        selectClause += " ACTUAL_RATE=Isnull(TODIS.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n"
                + " PROJECTION_RATE=Isnull(TODIS.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (\n " + totalDiscountQuery + "\n) TODIS LEFT JOIN (\n" + salesQuery + "\n) SALE  \n" + finalWhereCond + "\n order by TODIS.DISCOUNTS";
        return customQuery;
    }

    public String getProjectionResultsTotalDiscountPerQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Total Discount %' as DISCOUNTS, \n";
        selectClause += " ACTUAL_RATE=Isnull((Isnull(TODIS.ACTUAL_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += NULL_PPAACTUAL_SALES;
        }
        selectClause += ") / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n PROJECTION_RATE=Isnull((Isnull(TODIS.PROJECTION_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.PROJECTION_SALES, 0)";
        }
        selectClause += ") / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from  (\n " + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE  \n" + finalWhereCond;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL OUTER  JOIN (\n" + ppaQuery + "\n)  PPA  \n " + finalWhereCond1;
        }
        return customQuery;
    }

    public String getCostQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = "  IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + "CREATE TABLE #TEMP_CCP  (\n"
                + "     COMPANY_MASTER_SID INT\n"
                + "     , CONTRACT_MASTER_SID INT\n"
                + "      , ITEM_MASTER_SID INT\n"
                + "     , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "     , PROJECTION_MASTER_SID INT\n"
                + "      )\n"
                + "INSERT INTO #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID\n"
                + "    , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID  \n"
                + "    , PROJECTION_DETAILS_SID\n"
                + "     , PROJECTION_MASTER_SID\n"
                + "      )\n"
                + "SELECT C.COMPANY_MASTER_SID \n "
                + "      , C.CONTRACT_MASTER_SID\n"
                + "       , C.ITEM_MASTER_SID\n"
                + "      , PD.PROJECTION_DETAILS_SID\n"
                + "     , pm.PROJECTION_MASTER_SID\n"
                + "FROM \n"
                + "      CCP_DETAILS C\n"
                + "     , PROJECTION_DETAILS PD\n"
                + "    , PROJECTION_MASTER PM\n"
                + "WHERE PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "     AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "     AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "	 and EXISTS (SELECT 1 FROM \n"
                + "	 @CCP CCP WHERE CCP.CCP_DETAILS_SID =  C.CCP_DETAILS_SID)"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM] \n"
                + "INSERT INTO @ITEMID \n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM\n"
                + "WHERE EXISTS  (\n"
                + "              SELECT 1\n"
                + "           FROM #TEMP_CCP A\n"
                + "            WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                   AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "             ) ";
        selectClause += StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "COGS_ACTUAL = ISNULL(SALE.COGS_ACTUAL, 0)\n"
                + ", COGS_PROJECTED = ISNULL(SALE.COGS_PROJECTED, 0) \n"
                + ", NET_PROFIT_ACTUAL = ((Isnull(SALE.SALES_ACTUAL_SALES, 0) - (Isnull(TODIS.ACTUAL_SALES, 0)+ ISNULL(PPA.ACTUAL_SALES, 0))) - ISNULL(SALE.COGS_ACTUAL, 0))\n"
                + "  , NET_PROFIT_PROJECTED = ((Isnull(SALE.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0)+ ISNULL(PPA.PROJECTION_SALES, 0))) - ISNULL(SALE.COGS_PROJECTED, 0))";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from  ( \n" + totalDiscountQuery + "\n) TODIS FULL OUTER  JOIN (\n" + cogsQuery + "\n)  SALE  \n" + finalWhereCond;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL OUTER  JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        return customQuery;
    }

    public String getNetProfitQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = "  IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + "CREATE TABLE  #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID INT\n"
                + "     , CONTRACT_MASTER_SID INT\n"
                + "      , ITEM_MASTER_SID INT\n"
                + "      , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "     , PROJECTION_MASTER_SID INT\n"
                + "      ) \n"
                + "INSERT INTO  #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID\n"
                + "      , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID  \n"
                + "      , PROJECTION_DETAILS_SID\n"
                + "      , PROJECTION_MASTER_SID\n"
                + "      ) \n"
                + "SELECT C.COMPANY_MASTER_SID\n"
                + "     ,  C.CONTRACT_MASTER_SID\n"
                + "     , C.ITEM_MASTER_SID\n"
                + "      , PD.PROJECTION_DETAILS_SID\n"
                + "     , pm.PROJECTION_MASTER_SID\n"
                + "FROM  \n"
                + "      CCP_DETAILS C\n"
                + "        , PROJECTION_DETAILS PD\n"
                + "      , PROJECTION_MASTER PM\n"
                + "WHERE PD.CCP_DETAILS_SID =  C.CCP_DETAILS_SID\n"
                + "     AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "     AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "	  and EXISTS (SELECT 1 FROM \n"
                + "	 @CCP CCP WHERE CCP.CCP_DETAILS_SID =   C.CCP_DETAILS_SID)"
                + "DECLARE @ITEMID  [DBO].[UDT_ITEM]\n"
                + "INSERT INTO @ITEMID\n "
                + "SELECT IM.ITEM_MASTER_SID \n"
                + "FROM ITEM_MASTER  IM\n"
                + "WHERE  EXISTS (\n"
                + "             SELECT 1\n"
                + "           FROM #TEMP_CCP A\n"
                + "            WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                   AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "         ) ";
        selectClause += StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Cost Of Goods Sold' as COGS, \n";
        selectClause += " COGS_ACTUAL = (ISNULL(SALE.ACTUAL_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n"
                + ", COGS_PROJECTED = (ISNULL(SALE.PROJECTION_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        customQuery = selectClause + " from ( \n" + totalDiscountQuery + "\n) TODIS FULL  OUTER JOIN (\n" + salesQuery + "\n)  SALE  \n" + finalWhereCond + "\n";
        customQuery += " FULL OUTER JOIN (" + cogsQuery;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL  OUTER JOIN (\n" + ppaQuery + "\n) PPA  \n " + finalWhereCond1;
        }
        return customQuery;
    }

    public String getProjectionResultsPivotQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "     DROP TABLE  #TEMP_CCP;\n"
                + "CREATE  TABLE  #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID  INT\n"
                + "      ,  CONTRACT_MASTER_SID INT\n"
                + "     , ITEM_MASTER_SID INT\n"
                + "       , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "  , PROJECTION_MASTER_SID INT\n"
                + "     ) \n"
                + "INSERT INTO #TEMP_CCP   (\n"
                + "      COMPANY_MASTER_SID\n"
                + "     , CONTRACT_MASTER_SID\n"
                + "     , ITEM_MASTER_SID\n"
                + "     , PROJECTION_DETAILS_SID\n"
                + "     , PROJECTION_MASTER_SID\n"
                + "     ) \n"
                + "SELECT C.COMPANY_MASTER_SID\n"
                + "     , C.CONTRACT_MASTER_SID\n"
                + "     , C.ITEM_MASTER_SID\n"
                + "     , PD.PROJECTION_DETAILS_SID\n"
                + "  , pm.PROJECTION_MASTER_SID\n"
                + "FROM  \n "
                + "      CCP_DETAILS C \n"
                + "         , PROJECTION_DETAILS PD\n"
                + "     , PROJECTION_MASTER PM\n"
                + "WHERE PD.CCP_DETAILS_SID =  C.CCP_DETAILS_SID\n"
                + "     AND PD.PROJECTION_MASTER_SID  = PM.PROJECTION_MASTER_SID\n"
                + "     AND PM.PROJECTION_MASTER_SID  = " + projSelDTO.getProjectionId() + "\n"
                + "	 and EXISTS (SELECT 1 FROM \n"
                + "	 @CCP CCP WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM] \n"
                + "INSERT INTO  @ITEMID \n "
                + "SELECT  IM.ITEM_MASTER_SID\n"
                + "FROM  ITEM_MASTER IM\n"
                + " WHERE EXISTS (\n"
                + "            SELECT 1\n"
                + "            FROM #TEMP_CCP A\n"
                + "            WHERE PROJECTION_MASTER_SID  = " + projSelDTO.getProjectionId() + "\n"
                + "          AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "            ) ";
        selectClause += " IF Object_id('TEMPDB..#TEMP_CCPD') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCPD \n"
                + "\n"
                + "CREATE TABLE #TEMP_CCPD (\n"
                + "      COMPANY_MASTER_SID INT\n"
                + "       , CONTRACT_MASTER_SID INT\n"
                + "         , ITEM_MASTER_SID INT\n"
                + "       , PROJECTION_DETAILS_SID INT\n"
                + "       , PROJECTION_MASTER_SID INT\n"
                + CLOSE_SLASH_N
                + "\n"
                + "INSERT INTO #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID\n"
                + "        , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID \n "
                + "       , PROJECTION_DETAILS_SID \n"
                + "      , PROJECTION_MASTER_SID\n"
                + CLOSE_SLASH_N
                + "SELECT DISTINCT COMPANY_MASTER_SID\n"
                + "        , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID \n "
                + "       , PROJECTION_DETAILS_SID \n"
                + "      , PROJECTION_MASTER_SID \n  "
                + "FROM (\n"
                + "       SELECT COMPANY_MASTER_SID\n"
                + "            , T_CCP.CONTRACT_MASTER_SID\n"
                + "              , T_CCP.ITEM_MASTER_SID\n"
                + "              , PROJECTION_DETAILS_SID\n"
                + "              , PROJECTION_MASTER_SID\n"
                + "              , RS_ID\n"
                + "              , RS_TYPE\n"
                + "       FROM #TEMP_CCP T_CCP\n"
                + "        INNER JOIN RS_CONTRACT RS\n"
                + "              ON T_CCP.CONTRACT_MASTER_SID = RS.CONTRACT_MASTER_SID\n"
                + "       INNER JOIN RS_CONTRACT_DETAILS RSC\n"
                + "              ON RS.RS_CONTRACT_SID = RSC.RS_CONTRACT_SID\n"
                + "                    AND T_CCP.ITEM_MASTER_SID = RSC.ITEM_MASTER_SID\n"
                + "       WHERE EXISTS  (\n "
                + "                 SELECT 1\n"
                + "               FROM CFP_CONTRACT CF\n"
                + "                   INNER JOIN CFP_CONTRACT_DETAILS CFD\n"
                + "                           ON CF.CFP_CONTRACT_SID = CFD.CFP_CONTRACT_SID\n"
                + "                                  AND T_CCP.COMPANY_MASTER_SID = CFD.COMPANY_MASTER_SID\n"
                + "                                  AND RS.CFP_CONTRACT_SID = CF.CFP_CONTRACT_SID\n"
                + "                         AND CF.CONTRACT_MASTER_SID = T_CCP.CONTRACT_MASTER_SID\n"
                + "                     )\n"
                + "       ) R\n"
                + "INNER JOIN HELPER_TABLE HT\n"
                + "       ON R.RS_TYPE = HT.HELPER_TABLE_SID\n"
                + "WHERE HT.DESCRIPTION = 'Returns'\n"
                + "\n"
                + "DECLARE @ITEM_ID [DBO].[UDT_ITEM]\n"
                + "\n"
                + "INSERT INTO @ITEM_ID\n"
                + "SELECT DISTINCT ITEM_MASTER_SID\n"
                + "FROM #TEMP_CCPD A\n"
                + "\n"
                + "DECLARE @COUNT INT\n"
                + "\n"
                + "IF Object_id('TEMPDB..#ITEM') IS NOT NULL\n"
                + "       DROP TABLE #ITEM\n"
                + "\n"
                + "CREATE TABLE #ITEM (\n"
                + "       ID INT IDENTITY(1, 1)\n"
                + "       , ITEM_MASTER_SID INT\n"
                + CLOSE_SLASH_N
                + "\n"
                + "INSERT INTO #ITEM (ITEM_MASTER_SID)\n"
                + "SELECT ITEM_MASTER_SID\n"
                + "FROM @ITEMID\n"
                + "\n"
                + "SET @COUNT = @@ROWCOUNT \n"
                + "\n"
                + "DECLARE @I INT = 1\n"
                + " DECLARE @ITEM INT\n"
                + "\n"
                + "IF Object_id('TEMPDB..#TEMP_RETURNS') IS NOT NULL\n"
                + "    DROP TABLE #TEMP_RETURNS\n"
                + "\n"
                + "CREATE TABLE #TEMP_RETURNS (\n"
                + "        ITEM_MASTER_SID INT\n"
                + "       , RETURNS_DETAILS_SID INT\n"
                + "       , PERIOD_SID INT\n"
                + "       , ACTUAL_RATE NUMERIC(22, 6)\n"
                + "       , PROJECTED_RATE NUMERIC(22, 6)\n"
                + CLOSE_SLASH_N
                + "\n"
                + " WHILE (@I <= @COUNT)\n"
                + "BEGIN\n"
                + "       SET @ITEM = (\n"
                + "                     SELECT ITEM_MASTER_SID\n"
                + "                    FROM #ITEM\n"
                + "                   WHERE id = @I\n"
                + "                     );\n"
                + "\n"
                + "        WITH ITEM_PROJ_DETAILS\n"
                + "       AS (\n"
                + "              SELECT ROW_NUMBER() OVER (\n"
                + "                           PARTITION BY ITEM_MASTER_SID ORDER BY LASTEST_DATE DESC\n"
                + "                           ) RN\n"
                + "                     , ITEM_MASTER_SID\n"
                + "                     , PM.PROJECTION_MASTER_SID\n"
                + "                     , RETURNS_DETAILS_SID\n"
                + "              FROM RETURNS_DETAILS RD\n"
                + "              INNER JOIN PROJECTION_MASTER PM\n"
                + "                     ON RD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "              CROSS APPLY (\n"
                + "                     VALUES (MODIFIED_DATE)\n"
                + "                          , (CREATED_DATE)\n"
                + "                     ) CS(LASTEST_DATE)\n"
                + "             WHERE SAVE_FLAG = 1\n"
                + "                 AND ITEM_MASTER_SID = @ITEM\n"
                + "             )\n"
                + "       INSERT INTO #TEMP_RETURNS (\n"
                + "              ITEM_MASTER_SID\n"
                + "              , RETURNS_DETAILS_SID\n"
                + "              , PERIOD_SID\n"
                + "              , ACTUAL_RATE\n"
                + "          , PROJECTED_RATE\n"
                + "             )\n"
                + "       SELECT @ITEM AS ITEM_MASTER_SID\n"
                + "              , COALESCE(R_ACTUALS.RETURNS_DETAILS_SID, R_PROJ.RETURNS_DETAILS_SID) AS RETURNS_DETAILS_SID\n"
                + "              , COALESCE(R_ACTUALS.PERIOD_SID, R_PROJ.PERIOD_SID) AS PERIOD_SID\n"
                + "               , R_ACTUALS.ACTUAL_RETURN_PERCENT\n"
                + "             , R_PROJ.PROJECTED_RETURN_PERCENT \n"
                + "     FROM (\n"
                + "              SELECT RETURNS_DETAILS_SID\n"
                + "                     , PERIOD_SID\n"
                + "                     , ACTUAL_RETURN_PERCENT\n"
                + "              FROM RETURNS_ACTUALS NAP\n"
                + "            WHERE EXISTS (\n"
                + "                        SELECT 1\n"
                + "                       FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                         AND RD.RETURNS_DETAILS_SID = NAP.RETURNS_DETAILS_SID\n"
                + "                      WHERE IMPD.RN = 1\n"
                + "                           )\n"
                + "           ) R_ACTUALS\n"
                + "       FULL JOIN  ( \n "
                + "              SELECT  RETURNS_DETAILS_SID\n"
                + "                     , PERIOD_SID\n"
                + "                 , PROJECTED_RETURN_PERCENT\n"
                + "              FROM RETURNS_PROJ_DETAILS NPP\n"
                + "             WHERE EXISTS (\n"
                + "                          SELECT 1\n"
                + "                          FROM RETURNS_DETAILS RD\n"
                + "                  INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                    ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                    AND RD.RETURNS_DETAILS_SID = NPP.RETURNS_DETAILS_SID\n"
                + "                          WHERE IMPD.RN = 1\n"
                + "                           )\n"
                + "              ) R_PROJ\n"
                + "              ON R_ACTUALS.RETURNS_DETAILS_SID = R_PROJ.RETURNS_DETAILS_SID\n"
                + "                     AND R_ACTUALS.PERIOD_SID = R_PROJ.PERIOD_SID\n"
                + "\n"
                + "       SET @I = @I + 1\n"
                + "END";

        selectClause += "\n select ";
        String customQuery = "";
        String ppa_actuals = "";
        String ppa_projection = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        String orderBy = list.get(NumericConstants.THREE);
        if (projSelDTO.isPpa()) {
            ppa_actuals = NULL_PPAACTUAL_SALES;
            ppa_projection = "+Isnull(PPA.PROJECTION_SALES, 0)";
        }
        selectClause += " SALE.SALES_ACTUAL_SALES as CONTRACT_ACTUAL_SALES \n"
                + ", SALE.SALES_PROJECTION_SALES as CONTRACT_PROJECTION_SALES \n"
                + ", SALE.ACTUAL_UNITS as CONTRACT_ACTUAL_UNITS \n"
                + ", SALE.PROJECTION_UNITS as CONTRACT_PROJECTION_UNITS \n"
                + ", TOTAL_ACTUAL_RATE=Isnull((Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + "+Isnull(SALE.RETURNS_ACTUAL, 0))/ NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 \n"
                + ", TOTAL_PROJECTION_RATE=Isnull(Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + "+Isnull(SALE.RETURNS_PROJECTED, 0) / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n"
                + ", TOTAL_ACTUAL_DOLAR=(Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + "+Isnull(SALE.RETURNS_ACTUAL, 0)) \n"
                + ", TOTAL_PROJECTION_DOLAR=(Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + "+Isnull(SALE.RETURNS_PROJECTED, 0)) \n"
                + ", NET_ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-(Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + "))  \n "
                + ", NET_PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-(Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + ")) \n"
                + ", TOTAL_ACTUAL_RPU = Isnull(( Isnull(TODIS.ACTUAL_SALES, 0) " + ppa_actuals + " +Isnull(SALE.RETURNS_ACTUAL, 0)) / NULLIF(SALE.ACTUAL_UNITS, 0), 0),\n"
                + "       TOTAL_PROJECTION_RPU = Isnull(( Isnull(TODIS.PROJECTION_SALES, 0) " + ppa_projection + " +Isnull(SALE.RETURNS_PROJECTED, 0) ) / NULLIF(SALE.PROJECTION_UNITS, 0), 0)\n"
                + ", COGS_ACTUAL = ISNULL(SALE.COGS_ACTUAL, 0) \n"
                + "       , COGS_PROJECTED = ISNULL(SALE.COGS_PROJECTED, 0) \n"
                + "       , NET_PROFIT_ACTUAL = ((Isnull(SALE.SALES_ACTUAL_SALES, 0) - (Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + ")) - ISNULL(SALE.COGS_ACTUAL, 0))\n"
                + "       , NET_PROFIT_PROJECTED = ((Isnull(SALE.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + ")) - ISNULL(SALE.COGS_PROJECTED, 0))\n"
                + ", RETURNS_ACTUAL_AMOUNT = (Isnull(SALE.RETURNS_ACTUAL, 0))\n"
                + "       , RETURNS_PROJECTED_AMOUNT = (Isnull(SALE.RETURNS_PROJECTED, 0))\n"
                + "       , RETURNS_ACTUAL_PERCENT = (Isnull(SALE.RETURNS_ACTUAL_PERCENT, 0))\n"
                + "       , RETURNS_PROJECTED_PERCENT = (Isnull(SALE.RETURNS_PROJECTED_PERCENT, 0))\n"
                + "       , RETURNS_ACTUAL_RPU = (Isnull(SALE.RETURNS_ACTUAL / NULLIF(SALE.ACTUAL_UNITS, 0), 0))\n"
                + "       , RETURNS_PROJECTED_RPU = (Isnull(SALE.RETURNS_PROJECTED / NULLIF(SALE.PROJECTION_UNITS, 0), 0))";

        if (projSelDTO.isPpa()) {
            selectClause += ", PPA.ACTUAL_SALES AS PPA_ACTUAL_SALES \n"
                    + ", PPA.PROJECTION_SALES AS PPA_PROJECTION_SALES \n"
                    + ", PPA_ACTUAL_RATE=Isnull(PPA.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 \n"
                    + ", PPA_PROJECTION_RATE=Isnull(PPA.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 "
                    + ",PPA_ACTUAL_RPU=(Isnull(PPA.PPA_ACTUAL_RPU / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0))\n"
                    + "       ,PPA_PROJECTED_RPU=(Isnull(PPA.PPA_PROJECTED_RPU / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0)) \n";
        }

        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsReturnsQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from ( \n" + totalDiscountQuery + "\n)  TODIS  FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n " + finalWhereCond + " \n";
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n " + finalWhereCond1;
        }
        customQuery += " order by  " + orderBy;
        return customQuery;
    }

    public String getProjectionResultsReturnsQuery(ProjectionSelectionDTO projSelDTO) {
        String selectPeriod = "";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectPeriod = ",  I.QUARTER AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectPeriod = ", I.SEMI_ANNUAL AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = ",'0' AS  PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectPeriod = ", I.MONTH AS  PERIODS";
        }

        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customQuery = "SELECT Isnull(HISTORY.YEARS, FUTURE.YEARS) AS YEARS\n"
                + "              , Isnull(HISTORY.PERIODS, FUTURE.PERIODS) AS PERIODS\n"
                + "          , Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "              , Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "              , Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "              , Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "              , Isnull(HISTORY.COGS_ACTUAL, 0) AS COGS_ACTUAL\n"
                + "              , Isnull(FUTURE.COGS_PROJECTED, 0) AS COGS_PROJECTED\n"
                + "              , Isnull(HISTORY.RETURNS_ACTUAL, 0) AS RETURNS_ACTUAL\n"
                + "              , Isnull(FUTURE.RETURNS_PROJECTED, 0) AS RETURNS_PROJECTED\n"
                + ", RETURNS_ACTUAL_PERCENT = (Isnull(HISTORY.ACTUAL_RATE, 0))\n"
                + "       , RETURNS_PROJECTED_PERCENT = (Isnull(FUTURE.PROJECTED_RATE, 0))"
                + "      FROM (\n"
                + "              SELECT YEARS\n"
                + "                    , PERIODS\n"
                + "      , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                  , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "               , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                     , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     , SUM(COGS_ACTUAL) AS COGS_ACTUAL\n"
                + "                     , ACTUAL_RATE = Avg(A.ACTUAL_RATE)\n"
                + "                     , RETURNS_ACTUAL = Sum(A.ACTUAL_SALES) * Avg(A.ACTUAL_RATE)\n"
                + "                 FROM\n"
                + "                     (SELECT I.\"YEAR\" AS YEARS\n"
                + "                     " + selectPeriod + "\n"
                + "                     , A.ACTUAL_SALES\n"
                + "                       , A.HISTORY_PROJECTION_SALES\n"
                + "                       , A.ACTUAL_UNITS\n"
                + "                     , A.HISTORY_PROJECTION_UNITS\n"
                + "                     , COGS_ACTUAL = (ISNULL(A.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                + "                           , TR.ACTUAL_RATE\n"
                + "              FROM ST_NM_ACTUAL_SALES A\n"
                + "                     INNER JOIN PROJECTION_DETAILS E\n"
                + "                          ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "                     INNER JOIN PERIOD I\n"
                + "                        ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                      INNER JOIN @CCP CCP\n"
                + "                         ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                     INNER JOIN CCP_DETAILS CC\n"
                + "                       ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                              AND A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U\n"
                + "                           ON U.ITEM_MASTER_SID = CC.ITEM_MASTER_SID\n"
                + "                                  AND A.PERIOD_SID = U.PERIOD_SID\n"
                + "                     LEFT JOIN (\n"
                + "                           SELECT A.ITEM_MASTER_sID\n"
                + "                                  , A.PERIOD_sID\n"
                + "                                  , A.ACTUAL_RATE\n"
                + "                                  , A.PROJECTED_RATE\n"
                + "                                 , CCPD.PROJECTION_DETAILS_SID\n"
                + "                           FROM #TEMP_RETURNS A\n"
                + "                           INNER JOIN #TEMP_CCPD CCPD\n"
                + "                                  ON A.ITEM_MASTER_SID = CCPD.ITEM_MASTER_SID\n"
                + "                        ) TR\n"
                + "                           ON TR.ITEM_MASTER_sID = CC.ITEM_MASTER_sID\n"
                + "                                  AND TR.PERIOD_SID = A.PERIOD_SID\n"
                + "                               AND A.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID\n"
                + "                     WHERE A.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "                           AND A.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + "                          AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                           AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID " + periodFilter + "\n"
                + "                     ) A\n"
                + "               GROUP BY YEARS\n"
                + "                   , PERIODS\n"
                + "              ) HISTORY\n"
                + "       FULL JOIN (\n"
                + "   SELECT YEARS\n"
                + "                , PERIODS\n"
                + "                     , 0 AS SALES_ACTUAL_SALES\n"
                + "        , sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , 0 AS ACTUAL_UNITS\n"
                + "                 , sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     , SUM(COGS_PROJECTED) AS COGS_PROJECTED\n"
                + "                     , PROJECTED_RATE = Avg(P.PROJECTED_RATE)\n"
                + "                     , RETURNS_PROJECTED = Sum(P.PROJECTION_SALES) * Avg(PROJECTED_RATE)\n"
                + "              FROM (\n"
                + "                     SELECT I.\"YEAR\" AS YEARS\n"
                + "                            " + selectPeriod + "\n"
                + "                           , A.PROJECTION_SALES\n"
                + "                           , A.PROJECTION_UNITS\n"
                + "                           , COGS_PROJECTED = (ISNULL(A.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                + "                           , TR.PROJECTED_RATE\n"
                + "                     FROM ST_NM_SALES_PROJECTION A\n"
                + "                     INNER JOIN PROJECTION_DETAILS E\n"
                + "                           ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "                     INNER JOIN PERIOD I\n"
                + "                ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                    INNER JOIN @CCP CCP\n"
                + "                          ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                     INNER JOIN CCP_DETAILS CC\n"
                + "                          ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                               AND A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U\n"
                + "                           ON U.ITEM_MASTER_SID = CC.ITEM_MASTER_SID\n"
                + "                                  AND A.PERIOD_SID = U.PERIOD_SID\n"
                + "                    LEFT JOIN (\n"
                + "                       SELECT A.ITEM_MASTER_sID\n"
                + "                                  , A.PERIOD_sID\n"
                + "                                  , A.ACTUAL_RATE\n"
                + "                                  , A.PROJECTED_RATE\n"
                + "                          , CCPD.PROJECTION_DETAILS_SID\n"
                + "                        FROM #TEMP_RETURNS A\n"
                + "                               INNER JOIN #TEMP_CCPD CCPD\n"
                + "                                   ON A.ITEM_MASTER_SID = CCPD.ITEM_MASTER_SID\n"
                + "                       ) TR\n"
                + "                           ON TR.ITEM_MASTER_sID = CC.ITEM_MASTER_sID\n"
                + "                                  AND TR.PERIOD_SID = A.PERIOD_SID\n"
                + "                                 AND A.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID\n"
                + "                  WHERE A.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "                           AND A.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + "                       AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                           AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID " + periodFilter + "\n"
                + "                     ) P\n"
                + "           GROUP BY YEARS\n"
                + "          , PERIODS\n"
                + "              ) FUTURE\n"
                + "              ON HISTORY.YEARS = FUTURE.YEARS\n"
                + "                     AND HISTORY.PERIODS = FUTURE.PERIODS";
        return customQuery;
    }

    public String getProjectionResultsCOGSQuery(ProjectionSelectionDTO projSelDTO) {
        String selectPeriod = "";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectPeriod = ", I.QUARTER AS  PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectPeriod = ", I.SEMI_ANNUAL AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = ",'0'  AS   PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectPeriod = ", I.MONTH AS  PERIODS";
        }

        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customQuery = "SELECT Isnull(HISTORY.YEARS, FUTURE.YEARS) AS YEARS\n"
                + "              , Isnull(HISTORY.PERIODS, FUTURE.PERIODS) AS PERIODS\n"
                + "              , Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "             , Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "             , Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "           , Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "              ,Isnull(HISTORY.COGS_ACTUAL, 0) AS COGS_ACTUAL\n"
                + "              ,Isnull(FUTURE.COGS_PROJECTED, 0) AS COGS_PROJECTED\n"
                + "       FROM  (\n"
                + "               SELECT   YEARS\n"
                + "                      ,  PERIODS \n"
                + "                , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                  , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "     , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                  , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     ,SUM(COGS_ACTUAL)AS COGS_ACTUAL\n"
                + "                    FROM \n "
                + "                     (SELECT I.\"YEAR\" AS YEARS\n"
                + "                      " + selectPeriod + "\n"
                + "                     , A.ACTUAL_SALES\n"
                + "                     , A.HISTORY_PROJECTION_SALES\n"
                + "                     , A.ACTUAL_UNITS\n"
                + "                     , A.HISTORY_PROJECTION_UNITS\n"
                + "                     , COGS_ACTUAL = (ISNULL(A.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                + "              FROM ST_NM_ACTUAL_SALES A\n"
                + "                     , PROJECTION_DETAILS E\n"
                + "                     , \"PERIOD\" I\n"
                + "                     , @CCP CCP\n"
                + "                     ,ccp_details cc\n"
                + "                     ,[DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U\n"
                + "              WHERE A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "" + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A") + "\n"
                + "                     AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                     AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                     AND CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                     AND CC.ITEM_MASTER_SID=U.ITEM_MASTER_SID\n"
                + "                     AND A.PERIOD_SID=U.PERIOD_SID\n"
                + "                     AND A.PERIOD_SID = I.PERIOD_SID\n" + periodFilter + ")A\n"
                + "              GROUP BY YEARS\n"
                + "                     , PERIODS  \n "
                + "              ) HISTORY\n"
                + "       FULL JOIN (\n"
                + "              SELECT  YEARS\n"
                + "                    ,  PERIODS\n"
                + "                     ,0 AS SALES_ACTUAL_SALES\n"
                + "                     , sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     ,0 AS ACTUAL_UNITS\n"
                + "                     , sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     ,SUM(COGS_PROJECTED) AS COGS_PROJECTED\n"
                + "                    FROM \n"
                + "                   (SELECT I.\"YEAR\" AS YEARS\n"
                + "                     " + selectPeriod + "\n"
                + "                     , A.PROJECTION_SALES\n"
                + "                     , A.PROJECTION_UNITS\n"
                + "                     , COGS_PROJECTED = (ISNULL(A.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                + "              FROM ST_NM_SALES_PROJECTION A\n"
                + "                     , PROJECTION_DETAILS E\n"
                + "                     , \"PERIOD\" I\n"
                + "                     , @CCP CCP\n"
                + "                     ,ccp_details cc\n"
                + "                     ,[DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U\n"
                + "              WHERE A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "" + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A") + "\n"
                + "                     AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                     AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                     AND CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                     AND CC.ITEM_MASTER_SID=U.ITEM_MASTER_SID\n"
                + "                     AND A.PERIOD_SID=U.PERIOD_SID\n"
                + "                 AND A.PERIOD_SID = I.PERIOD_SID\n" + periodFilter + ")P\n"
                + "              GROUP BY  YEARS\n"
                + "                     ,  PERIODS\n"
                + "              ) FUTURE\n"
                + "           ON HISTORY.YEARS = FUTURE.YEARS\n"
                + " AND HISTORY.PERIODS = FUTURE.PERIODS";
        return customQuery;

    }

    public String getProjectionResultsTotalDiscountsQuery(ProjectionSelectionDTO projSelDTO) {
        String selectPeriod = "";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectPeriod = ", I.QUARTER AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectPeriod = ", I.SEMI_ANNUAL AS  PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = ",'0'  AS  PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectPeriod = ", I.MONTH AS PERIODS";
        }

        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customQuery = "SELECT    ISNULL(HISTORY.YEARS, FUTURE.YEARS)                                   AS YEARS,\n"
                + "                     ISNULL(HISTORY.PERIODS, FUTURE.PERIODS)                               AS PERIODS,\n"
                + "                     ISNULL(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES)         AS SALES_ACTUAL_SALES,\n"
                + "                     ISNULL(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES,\n"
                + "                     ISNULL(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS)                     AS ACTUAL_UNITS,\n"
                + "                     ISNULL(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS)             AS PROJECTION_UNITS,\n"
                + "                     ISNULL(HISTORY.ACTUAL_RATE, 0)                                        AS RETURNS_ACTUAL_PERCENT,\n"
                + "                     ISNULL(FUTURE.PROJECTED_RATE, 0)                                      AS RETURNS_PROJECTED_PERCENT,\n"
                + "                     ISNULL(HISTORY.RETURNS_ACTUAL, 0)                                     AS RETURNS_ACTUAL_AMOUNT,\n"
                + "                     ISNULL(FUTURE.RETURNS_PROJECTED, 0)                                   AS RETURNS_PROJECTED_AMOUNT\n"
                + "       FROM  (\n"
                + "              SELECT  YEARS\n"
                + "                      ,  PERIODS \n"
                + "                     , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                     , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "          , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     ,ACTUAL_RATE = Avg(A.ACTUAL_RATE),\n"
                + "                      RETURNS_ACTUAL = Sum(A.ACTUAL_SALES) * Avg(ACTUAL_RATE)\n"
                + "                     FROM\n"
                + "                    (SELECT I.\"YEAR\" AS YEARS\n"
                + "                  " + selectPeriod + "\n"
                + "                , A.ACTUAL_SALES\n"
                + "                     , A.HISTORY_PROJECTION_SALES\n"
                + "                     , A.ACTUAL_UNITS\n"
                + "                      , A.HISTORY_PROJECTION_UNITS\n"
                + "                     , TR.ACTUAL_RATE\n"
                + "          FROM ST_NM_ACTUAL_SALES A\n"
                + "              INNER JOIN PROJECTION_DETAILS E\n"
                + "              ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "       INNER JOIN PERIOD I\n"
                + "              ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "       INNER JOIN @CCP CCP\n"
                + "              ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "       INNER JOIN CCP_DETAILS CC\n"
                + "              ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                  AND A.PERIOD_SID = I.PERIOD_SID\n"
                + "       LEFT JOIN (\n"
                + "              SELECT A.ITEM_MASTER_sID\n"
                + "                     , A.PERIOD_sID\n"
                + "                     , A.ACTUAL_RATE\n"
                + "                  , A.PROJECTED_RATE\n"
                + "                     , CCPD.PROJECTION_DETAILS_SID\n"
                + "              FROM #TEMP_RETURNS A\n"
                + "              INNER JOIN #TEMP_CCPD CCPD\n"
                + "                     ON A.ITEM_MASTER_SID = CCPD.ITEM_MASTER_SID\n"
                + "              ) TR\n"
                + "              ON TR.ITEM_MASTER_sID = CC.ITEM_MASTER_sID\n"
                + "                     AND TR.PERIOD_SID = A.PERIOD_SID\n"
                + "                     AND A.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID\n"
                + "       WHERE A.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "              AND A.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + "              AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "              AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n" + periodFilter + "\n"
                + "       ) A\n"
                + "GROUP BY YEARS\n"
                + "       , PERIODS       "
                + "               ) HISTORY\n"
                + "         FULL JOIN (\n"
                + "              SELECT  YEARS,\n"
                + "                             PERIODS,\n"
                + "                             Sum(P.SALES_ACTUAL_SALES)AS SALES_ACTUAL_SALES,\n"
                + "                             Sum(P.PROJECTION_SALES)  AS SALES_PROJECTION_SALES,\n"
                + "                             Sum(ACTUAL_UNITS)        AS ACTUAL_UNITS,\n"
                + "                             Sum(P.PROJECTION_UNITS)  AS PROJECTION_UNITS,\n"
                + "                             PROJECTED_RATE = Avg(P.PROJECTED_RATE),\n"
                + "                             RETURNS_PROJECTED = Sum(P.PROJECTION_SALES) * Avg(PROJECTED_RATE)"
                + "                     FROM\n"
                + "     (SELECT I.\"YEAR\" AS YEARS\n"
                + "                    " + selectPeriod + "\n"
                + "                     , 0         AS SALES_ACTUAL_SALES,\n"
                + "                                     A.PROJECTION_SALES,\n"
                + "                                     0         AS ACTUAL_UNITS,\n"
                + "                                     A.PROJECTION_UNITS,\n"
                + "                                     TR.PROJECTED_RATE\n"
                + "              FROM ST_NM_SALES_PROJECTION A\n"
                + "INNER JOIN PROJECTION_DETAILS E\n"
                + "              ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "       INNER JOIN PERIOD I\n"
                + "              ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "       INNER JOIN @CCP CCP\n"
                + "              ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "       INNER JOIN CCP_DETAILS CC\n"
                + "              ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                     AND A.PERIOD_SID = I.PERIOD_SID\n"
                + "       LEFT JOIN (\n"
                + "              SELECT A.ITEM_MASTER_sID\n"
                + "                     , A.PERIOD_sID\n"
                + "                     , A.ACTUAL_RATE\n"
                + "                     , A.PROJECTED_RATE\n"
                + "                     , CCPD.PROJECTION_DETAILS_SID\n"
                + "              FROM #TEMP_RETURNS A\n"
                + "              INNER JOIN #TEMP_CCPD CCPD\n"
                + "                     ON A.ITEM_MASTER_SID = CCPD.ITEM_MASTER_SID\n"
                + "              ) TR\n"
                + "              ON TR.ITEM_MASTER_sID = CC.ITEM_MASTER_sID\n"
                + "                     AND TR.PERIOD_SID = A.PERIOD_SID\n"
                + "                     AND A.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID\n"
                + "       WHERE A.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "              AND A.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + "              AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "              AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID" + periodFilter + ")P\n"
                + "              GROUP BY  YEARS\n"
                + "                     ,  PERIODS\n"
                + "             ) FUTURE\n"
                + "              ON HISTORY.YEARS = FUTURE.YEARS\n"
                + " AND HISTORY.PERIODS = FUTURE.PERIODS";
        return customQuery;

    }

    public String getCOGSQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        String ppa_actuals = "";
        String ppa_projection = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        String orderBy = list.get(NumericConstants.THREE);
        if (projSelDTO.isPpa()) {
            ppa_actuals = NULL_PPAACTUAL_SALES;
            ppa_projection = "+Isnull(PPA.PROJECTION_SALES,  0)";
        }
        selectClause += " SALE.SALES_ACTUAL_SALES as CONTRACT_ACTUAL_SALES \n"
                + ", SALE.SALES_PROJECTION_SALES as CONTRACT_PROJECTION_SALES \n"
                + ", SALE.ACTUAL_UNITS as CONTRACT_ACTUAL_UNITS \n"
                + ", SALE.PROJECTION_UNITS as CONTRACT_PROJECTION_UNITS \n"
                + ", TOTAL_ACTUAL_RATE=Isnull((Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + ") / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 \n"
                + ", TOTAL_PROJECTION_RATE=Isnull((Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + ") / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n"
                + ", TOTAL_ACTUAL_DOLAR=(Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + ") \n"
                + ", TOTAL_PROJECTION_DOLAR=(Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + ") \n"
                + ", NET_ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-(Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + "))  \n"
                + ", NET_PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-(Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + ")) \n"
                + ", TOTAL_ACTUAL_RPU = Isnull(( Isnull(TODIS.ACTUAL_SALES, 0) ) / NULLIF(SALE.ACTUAL_UNITS, 0), 0),\n"
                + "       TOTAL_PROJECTION_RPU = Isnull(( Isnull(TODIS.PROJECTION_SALES, 0) ) / NULLIF(SALE.PROJECTION_UNITS, 0), 0),\n"
                + "          COGS_ACTUAL = (ISNULL(SALE.ACTUAL_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n"
                + "       , COGS_PROJECTED = (ISNULL(SALE.PROJECTION_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n"
                + "       , NET_PROFIT_ACTUAL = ((ISNULL(SALE.SALES_ACTUAL_SALES, 0) * (ISNULL(SALE.ACTUAL_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))))\n"
                + "       , NET_PROFIT_PROJECTED = ((ISNULL(SALE.SALES_PROJECTION_SALES, 0) * (ISNULL(SALE.ACTUAL_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))))";
        if (projSelDTO.isPpa()) {
            selectClause += ", PPA.ACTUAL_SALES AS PPA_ACTUAL_SALES \n"
                    + ", PPA.PROJECTION_SALES AS PPA_PROJECTION_SALES \n"
                    + ", PPA_ACTUAL_RATE=Isnull(PPA.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 \n"
                    + ", PPA_PROJECTION_RATE=Isnull(PPA.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100  \n";
        }

        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        customQuery = selectClause + " from ( \n " + totalDiscountQuery + "\n) TODIS  FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n " + finalWhereCond + " \n";
        customQuery += " FULL OUTER JOIN (" + cogsQuery;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL  OUTER  JOIN (\n" + ppaQuery + "\n)  PPA  \n" + finalWhereCond1;
        }
        customQuery += " order by " + orderBy;
        return customQuery;
    }

    public String getProjectionResultsDiscountsPivotQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        String orderBy = list.get(NumericConstants.TWO);
        selectClause += " TODIS.DISCOUNTS \n"
                + ", TODIS.ACTUAL_SALES as DOL_ACTUAL_SALES \n"
                + ", TODIS.PROJECTION_SALES as DOL_PROJECTION_SALES \n"
                + ", PER_ACTUAL_SALES=Isnull(Isnull(TODIS.ACTUAL_SALES, 0) / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 \n"
                + ", PER_PROJECTION_SALES=Isnull(Isnull(TODIS.PROJECTION_SALES, 0) / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100  \n"
                + ",  PER_ACTUAL_RPU = Isnull(( Isnull(TODIS.ACTUAL_SALES, 0) ) / NULLIF(SALE.ACTUAL_UNITS, 0), 0),\n"
                + "  PER_PROJECTION_RPU = Isnull(( Isnull(TODIS.PROJECTION_SALES, 0) ) / NULLIF(SALE.PROJECTION_UNITS, 0), 0)";

        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (" + totalDiscountQuery + ") TODIS LEFT JOIN (" + salesQuery + ") SALE " + finalWhereCond + " order by " + orderBy;
        projSelDTO.setIsTotal(true);
        return customQuery;
    }

    public String getProjectionResultsTotalTotalRPU(ProjectionSelectionDTO projSelDTO) {

        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Total RPU' as DISCOUNTS, \n";
        selectClause += " ACTUAL_RPU=ISNULL((ISNULL(TODIS.ACTUAL_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += NULL_PPAACTUAL_SALES;
        }
        selectClause += ") / NULLIF(SALE.ACTUAL_UNITS, 0), 0) ,\n PROJECTION_RPU=Isnull((Isnull(TODIS.PROJECTION_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.PROJECTION_SALES , 0)";
        }
        selectClause += ") / NULLIF(SALE.PROJECTION_UNITS, 0), 0) \n";
        String totalDiscountQuery = getProjectionResultsDiscountsRPUQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from   ( \n " + totalDiscountQuery + "\n) TODIS  FULL OUTER JOIN (\n" + salesQuery + "\n ) SALE \n" + finalWhereCond;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += "  FULL  OUTER  JOIN  (\n" + ppaQuery + "\n) PPA  \n" + finalWhereCond1;
        }
        return customQuery;
    }

    public String getProjectionResultsDiscountsRPUQuery(ProjectionSelectionDTO projSelDTO, String orderBy) {

        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String whereClause = "";
        String groupBy = "  I.\"YEAR\" \n";

        String customQuery = "";
        String ccpWhereCond = " and  CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";

        selectClause += "I.\"YEAR\"  as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and  I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.QUARTER ";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as  PERIODS, \n";
            whereClause += "";
            groupBy += ",   I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS,\n ";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS,  \n";
            whereClause += "";
            groupBy += ", I.\"MONTH\" ";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "'Total RPU' as DISCOUNTS \n";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            if (!projSelDTO.isIsTotal()) {
                discountTypeColumnName = " J.RS_NAME as DISCOUNTS \n";
                groupBy += ", J.RS_NAME";
            }
            whereClause += " and B.RS_MODEL_SID in (" + CollectionToString(projSelDTO.getDiscountNoList(), false) + ") \n";
        }
        selectClause += discountTypeColumnName + ", ";

        String customSql = "  ST_NM_DISCOUNT_PROJ_MASTER B,\n"
                + " PROJECTION_DETAILS  E , \n"
                + "\"PERIOD\" I, \n"
                + " @CCP  CCP ";
        if (!projSelDTO.isIsTotal()) {
            customSql += ", RS_MODEL J \n";
        }
        customSql += "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n"
                + " and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B")
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A");
        if (!projSelDTO.isIsTotal()) {
            customSql += "and B.RS_MODEL_SID= J.RS_MODEL_SID \n";
        }
        customSql += "and A.RS_MODEL_SID = B.RS_MODEL_SID \n"
                + " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + " \n"
                + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID \n"
                + periodFilter
                + whereClause + " group by " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as ACTUAL_SALES, \n"
                + " 0 as PROJECTION_SALES \n"
                + " from ST_NM_ACTUAL_DISCOUNT A, \n "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES , \n"
                + " Sum(A.PROJECTION_SALES) as PROJECTION_SALES \n"
                + " from ST_NM_DISCOUNT_PROJECTION A, \n "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.HISTORY1, StringConstantsUtil.FUTURE, "on");
        String finalWhereCond = list.get(1) + " and HISTORY.DISCOUNTS=FUTURE.DISCOUNTS";
        String finalSelectClause = SELECT_SPACE + list.get(0) + " Isnull(HISTORY.DISCOUNTS, FUTURE.DISCOUNTS) as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES ";
        customQuery = finalSelectClause + "   from   (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond + orderBy;

        return customQuery;
    }

    public String getProjectionResultsDiscountsRPUQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "TODIS.DISCOUNTS, \n";
        selectClause += " ACTUAL_RATE=Isnull(TODIS.ACTUAL_SALES / NULLIF(SALE.ACTUAL_UNITS, 0), 0) ,\n"
                + " PROJECTION_RATE=Isnull(TODIS.PROJECTION_SALES / NULLIF(SALE.PROJECTION_UNITS, 0), 0) \n";
        String totalDiscountQuery = getProjectionResultsDiscountsRPUQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + "   from   (\n" + totalDiscountQuery + "\n) TODIS LEFT JOIN (\n" + salesQuery + "\n ) SALE \n" + finalWhereCond + "\n order by TODIS.DISCOUNTS";
        return customQuery;
    }

    public String getFormatTwoDecimalValue(DecimalFormat FORMAT, String value, String appendChar) {
        if (value.contains("null")) {
            value = "0";
        }
        if (CURRENCY.equals(appendChar)) {
            value = appendChar.concat(FORMAT.format(Double.valueOf(value)));
        } else {
            value = FORMAT.format(Double.valueOf(value)).concat(appendChar);
        }

        return value;
    }




    public List<ProjectionResultsDTO> getCustomizedProjectionResultsReturns(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        List<ProjectionResultsDTO> projDTO = new ArrayList<>();
        columnList.remove(StringConstantsUtil.GROUP_PROPERTY);
        ProjectionResultsDTO returnPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO returnRPUDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO returnDolDTO = new ProjectionResultsDTO();
        returnPerDTO.setLevelValue(projSelDTO.getLevelValue());
        returnPerDTO.setLevelNo(projSelDTO.getLevelNo());
        returnPerDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        returnPerDTO.setGroup(RETURNS.getConstant());
        returnRPUDTO.setLevelValue(projSelDTO.getLevelValue());
        returnRPUDTO.setLevelNo(projSelDTO.getLevelNo());
        returnRPUDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        returnRPUDTO.setGroup(RETURNS.getConstant());
        returnDolDTO.setLevelValue(projSelDTO.getLevelValue());
        returnDolDTO.setLevelNo(projSelDTO.getLevelNo());
        returnDolDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        returnDolDTO.setGroup(RETURNS.getConstant());
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = "";
                int year = (obj[0] == null) ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = (obj[1] == null) ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                String commonColumn = common.get(0);
                int col = NumericConstants.THREE;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = "";
                    value = "" + obj[col + NumericConstants.FOUR];
                    value = getFormattedValue(PER_TWO, value);
                    returnPerDTO.addStringProperties(column, value);
                    value = "" + obj[col + NumericConstants.TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnRPUDTO.addStringProperties(column, value);
                    value = "" + obj[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnDolDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = "" + obj[col + NumericConstants.FIVE];
                    value = getFormattedValue(PER_TWO, value);
                    returnPerDTO.addStringProperties(column, value);
                    value = "" + obj[col + NumericConstants.THREE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnRPUDTO.addStringProperties(column, value);
                    value = "" + obj[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnDolDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            returnPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
            returnRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            returnDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
        }
        projDTO.add(returnPerDTO);
        projDTO.add(returnRPUDTO);
        projDTO.add(returnDolDTO);

        return projDTO;
    }

    public String getReturnsQuery(ProjectionSelectionDTO projSelDTO) {

        String selectPeriod = "";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectPeriod = ", I.QUARTER AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectPeriod = ", I.SEMI_ANNUAL  AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = ",'0' AS  PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectPeriod = ", I.MONTH AS PERIODS";
        }

        String query = "IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCP;\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCP (\n"
                + "        COMPANY_MASTER_SID INT\n"
                + "    , CONTRACT_MASTER_SID INT\n"
                + "       , ITEM_MASTER_SID INT\n"
                + "       , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "       , PROJECTION_MASTER_SID INT\n"
                + CLOSE_SLASH_N
                + "\n"
                + "INSERT  INTO #TEMP_CCP  (\n"
                + "       COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID \n"
                + "       , ITEM_MASTER_SID \n  "
                + "       , PROJECTION_DETAILS_SID\n"
                + "     , PROJECTION_MASTER_SID \n "
                + CLOSE_SLASH_N
                + "SELECT  C.COMPANY_MASTER_SID\n"
                + "       , C.CONTRACT_MASTER_SID\n"
                + "       , C.ITEM_MASTER_SID\n"
                + "       , PD.PROJECTION_DETAILS_SID\n"
                + "       , pm.PROJECTION_MASTER_SID\n"
                + "FROM CCP_DETAILS C\n"
                + "       , PROJECTION_DETAILS PD\n"
                + "       , PROJECTION_MASTER PM\n"
                + "WHERE  PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "       AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "       AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "       AND EXISTS (\n"
                + "              SELECT 1\n"
                + "              FROM @CCP CCP\n"
                + "              WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "         )\n"
                + "\n"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + "\n"
                + "INSERT INTO @ITEMID\n"
                + "SELECT  IM.ITEM_MASTER_SID\n"
                + "FROM  ITEM_MASTER IM\n"
                + " WHERE EXISTS (\n"
                + "           SELECT 1\n"
                + "              FROM #TEMP_CCP A\n"
                + "              WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                     AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "          )\n"
                + "\n"
                + "IF Object_id('TEMPDB..#TEMP_CCPD') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCPD\n"
                + "\n"
                + "CREATE  TABLE #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID INT\n"
                + "      , CONTRACT_MASTER_SID INT\n"
                + "    , ITEM_MASTER_SID INT\n"
                + "      , PROJECTION_DETAILS_SID INT\n"
                + "        , PROJECTION_MASTER_SID INT\n"
                + CLOSE_SLASH_N
                + "\n"
                + "INSERT INTO #TEMP_CCPD (\n"
                + "            COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID \n"
                + "       , ITEM_MASTER_SID\n   "
                + "       , PROJECTION_DETAILS_SID\n "
                + "     , PROJECTION_MASTER_SID \n "
                + CLOSE_SLASH_N
                + "SELECT DISTINCT COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID\n    "
                + "       , PROJECTION_DETAILS_SID\n "
                + "       , PROJECTION_MASTER_SID \n"
                + "FROM  (\n"
                + "       SELECT COMPANY_MASTER_SID\n"
                + "              , T_CCP.CONTRACT_MASTER_SID\n"
                + "           , T_CCP.ITEM_MASTER_SID\n"
                + "              , PROJECTION_DETAILS_SID\n"
                + "             , PROJECTION_MASTER_SID\n"
                + "             , RS_ID\n"
                + "              , RS_TYPE\n"
                + "       FROM #TEMP_CCP T_CCP\n"
                + "       INNER JOIN  RS_CONTRACT RS\n"
                + "              ON T_CCP.CONTRACT_MASTER_SID = RS.CONTRACT_MASTER_SID\n"
                + "        INNER JOIN RS_CONTRACT_DETAILS RSC\n"
                + "              ON RS.RS_CONTRACT_SID = RSC.RS_CONTRACT_SID\n"
                + "                 AND T_CCP.ITEM_MASTER_SID = RSC.ITEM_MASTER_SID\n"
                + "       WHERE  EXISTS  (\n"
                + "                     SELECT 1\n"
                + "                     FROM CFP_CONTRACT CF\n"
                + "                     INNER JOIN CFP_CONTRACT_DETAILS CFD\n"
                + "                          ON CF.CFP_CONTRACT_SID = CFD.CFP_CONTRACT_SID\n"
                + "                AND T_CCP.COMPANY_MASTER_SID = CFD.COMPANY_MASTER_SID\n"
                + "                                  AND RS.CFP_CONTRACT_SID = CF.CFP_CONTRACT_SID\n"
                + "                                  AND CF.CONTRACT_MASTER_SID = T_CCP.CONTRACT_MASTER_SID\n"
                + "                )\n"
                + "        ) R\n"
                + "INNER  JOIN HELPER_TABLE HT\n"
                + "       ON R.RS_TYPE = HT.HELPER_TABLE_SID\n"
                + "WHERE HT.DESCRIPTION = 'Returns'\n"
                + "\n"
                + "DECLARE @ITEM_ID [DBO].[UDT_ITEM]\n"
                + "\n"
                + "INSERT INTO @ITEM_ID\n"
                + "SELECT DISTINCT ITEM_MASTER_SID\n"
                + "FROM #TEMP_CCPD A\n"
                + "\n"
                + "DECLARE @COUNT INT\n"
                + "\n"
                + "IF Object_id('TEMPDB..#ITEM')  IS NOT NULL\n"
                + "       DROP TABLE #ITEM\n"
                + "\n"
                + "CREATE  TABLE #ITEM (\n"
                + "        ID INT IDENTITY(1, 1)\n"
                + "        , ITEM_MASTER_SID INT\n"
                + CLOSE_SLASH_N
                + "\n"
                + "INSERT INTO #ITEM (ITEM_MASTER_SID)\n"
                + "SELECT ITEM_MASTER_SID\n"
                + "FROM @ITEMID\n"
                + "\n"
                + "SET @COUNT = @@ROWCOUNT\n"
                + "\n"
                + "DECLARE @I INT = 1\n"
                + "DECLARE @ITEM INT\n"
                + "\n"
                + "IF Object_id('TEMPDB..#TEMP_RETURNS') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_RETURNS\n"
                + "\n"
                + "CREATE  TABLE #TEMP_RETURNS (\n"
                + "       ITEM_MASTER_SID INT\n"
                + "       ,  RETURNS_DETAILS_SID INT\n"
                + "      , PERIOD_SID INT\n"
                + "        , ACTUAL_RATE NUMERIC(22, 6)\n"
                + "       , PROJECTED_RATE NUMERIC(22, 6)\n"
                + CLOSE_SLASH_N
                + "\n"
                + "WHILE (@I <= @COUNT)\n"
                + "BEGIN\n"
                + "        SET @ITEM = (\n"
                + "                     SELECT ITEM_MASTER_SID\n"
                + "                     FROM #ITEM\n"
                + "                     WHERE id = @I\n"
                + "                   );\n"
                + "\n"
                + "       WITH ITEM_PROJ_DETAILS\n"
                + "       AS (\n"
                + "               SELECT ROW_NUMBER() OVER (\n"
                + "                           PARTITION BY ITEM_MASTER_SID ORDER BY LASTEST_DATE DESC\n"
                + "                           ) RN\n"
                + "                     , ITEM_MASTER_SID\n"
                + "                    , PM.PROJECTION_MASTER_SID\n"
                + "                     , RETURNS_DETAILS_SID\n"
                + "              FROM RETURNS_DETAILS RD\n"
                + "              INNER JOIN PROJECTION_MASTER PM\n"
                + "                     ON RD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "              CROSS APPLY (\n"
                + "                     VALUES (MODIFIED_DATE)\n"
                + "                           , (CREATED_DATE)\n"
                + "                     ) CS(LASTEST_DATE)\n"
                + "              WHERE SAVE_FLAG = 1\n"
                + "                     AND ITEM_MASTER_SID = @ITEM\n"
                + "   )\n"
                + "       INSERT INTO #TEMP_RETURNS (\n"
                + "              ITEM_MASTER_SID\n"
                + "              , RETURNS_DETAILS_SID\n"
                + "               , PERIOD_SID\n"
                + "               , ACTUAL_RATE\n"
                + "              , PROJECTED_RATE\n"
                + "  )\n"
                + "       SELECT @ITEM AS ITEM_MASTER_SID\n"
                + "              , COALESCE(R_ACTUALS.RETURNS_DETAILS_SID, R_PROJ.RETURNS_DETAILS_SID) AS RETURNS_DETAILS_SID\n"
                + "           , COALESCE(R_ACTUALS.PERIOD_SID, R_PROJ.PERIOD_SID) AS PERIOD_SID\n"
                + "              , R_ACTUALS.ACTUAL_RETURN_PERCENT\n"
                + "              , R_PROJ.PROJECTED_RETURN_PERCENT\n"
                + "       FROM (\n"
                + "               SELECT RETURNS_DETAILS_SID\n"
                + "                    , PERIOD_SID\n"
                + "                    , ACTUAL_RETURN_PERCENT\n"
                + "              FROM RETURNS_ACTUALS NAP\n"
                + "              WHERE EXISTS (\n"
                + "             SELECT 1\n"
                + "                         FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD \n"
                + "                                 ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                     AND RD.RETURNS_DETAILS_SID = NAP.RETURNS_DETAILS_SID\n"
                + "                           WHERE IMPD.RN =  1\n"
                + "     )\n   "
                + "              ) R_ACTUALS\n"
                + "        FULL JOIN (\n"
                + "              SELECT RETURNS_DETAILS_SID \n"
                + "                    , PERIOD_SID\n"
                + "                     , PROJECTED_RETURN_PERCENT\n"
                + "             FROM RETURNS_PROJ_DETAILS NPP\n"
                + "              WHERE EXISTS (\n"
                + "                           SELECT 1\n"
                + "             FROM RETURNS_DETAILS RD\n"
                + "                          INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID \n"
                + "                                         AND RD.RETURNS_DETAILS_SID = NPP.RETURNS_DETAILS_SID\n"
                + "                            WHERE IMPD.RN = 1\n"
                + "                          )\n"
                + "              ) R_PROJ\n"
                + "               ON R_ACTUALS.RETURNS_DETAILS_SID = R_PROJ.RETURNS_DETAILS_SID\n"
                + "                      AND R_ACTUALS.PERIOD_SID = R_PROJ.PERIOD_SID\n"
                + "\n"
                + "       SET @I = @I + 1\n"
                + "END\n"
                + "\n"
                + "SELECT Isnull(TODIS.YEARS, SALE.YEARS) AS YEARS\n"
                + "       , Isnull(TODIS.PERIODS, SALE.PERIODS) AS PERIODS\n"
                + "       , 'Returns' AS DISCOUNTS\n"
                + "       , RETURNS_ACTUAL_AMOUNT = ISNULL(SALE.RETURNS_ACTUAL, 0)\n"
                + "       , RETURNS_ACTUAL_AMOUNT = ISNULL(SALE.RETURNS_PROJECTED, 0)\n"
                + "       , RETURNS_ACTUAL_RPU = Isnull((Isnull(SALE.RETURNS_ACTUAL, 0)) / NULLIF(SALE.ACTUAL_UNITS, 0), 0)\n"
                + "       , RETURNS_PROJECTED_RPU = Isnull((Isnull(SALE.RETURNS_PROJECTED, 0)) / NULLIF(SALE.ACTUAL_UNITS, 0), 0)\n"
                + "       , RETURNS_ACTUAL_RATE = Isnull(SALE.RETURNS_ACTUAL_RATE, 0)\n"
                + "       , RETURNS_PROJECTED_RATE = Isnull(SALE.RETURNS_PROJECTED_RATE, 0)\n"
                + "FROM ( \n" + getProjectionResultsDiscountsQuery(projSelDTO, "") + ") TODIS\n"
                + "LEFT JOIN (\n"
                + "       SELECT Isnull(HISTORY.YEARS, FUTURE.YEARS) AS YEARS\n"
                + "            , Isnull(HISTORY.PERIODS, FUTURE.PERIODS) AS PERIODS\n"
                + "              , Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "              , Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "              , Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "              , Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "              , Isnull(HISTORY.RETURNS_ACTUAL, 0) AS RETURNS_ACTUAL\n"
                + "              , Isnull(FUTURE.RETURNS_PROJECTED, 0) AS RETURNS_PROJECTED\n"
                + "              , Isnull(HISTORY.ACTUAL_RATE, 0) AS RETURNS_ACTUAL_RATE\n"
                + "              , Isnull(FUTURE.PROJECTED_RATE, 0) AS RETURNS_PROJECTED_RATE\n"
                + "        FROM  (\n"
                + "           SELECT YEARS\n"
                + "                    ,  PERIODS\n"
                + "                     , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                     , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                     , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     , ACTUAL_RATE = Avg(A.ACTUAL_RATE)\n"
                + "                     , RETURNS_ACTUAL = Sum(A.ACTUAL_SALES) * Avg(A.ACTUAL_RATE)\n"
                + "              FROM (\n"
                + "                     SELECT I.\"YEAR\" AS YEARS\n"
                + "                           " + selectPeriod + "\n"
                + "                           , A.ACTUAL_SALES\n"
                + "                           , A.HISTORY_PROJECTION_SALES\n"
                + "                           , A.ACTUAL_UNITS\n"
                + "                           , A.HISTORY_PROJECTION_UNITS\n"
                + "                           , TR.ACTUAL_RATE\n"
                + "                     FROM ST_NM_ACTUAL_SALES A\n"
                + "                    INNER JOIN PROJECTION_DETAILS E\n"
                + "                           ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "                    INNER JOIN PERIOD I\n"
                + "                           ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     INNER JOIN @CCP CCP\n"
                + "                           ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                INNER JOIN CCP_DETAILS CC\n"
                + "                           ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                                  AND A.PERIOD_SID = I.PERIOD_SID\n"
                + "                  LEFT JOIN (\n"
                + "                          SELECT A.ITEM_MASTER_sID\n"
                + "                   , A.PERIOD_sID\n"
                + "          , A.ACTUAL_RATE\n"
                + "                     , A.PROJECTED_RATE\n"
                + "                                  , CCPD.PROJECTION_DETAILS_SID\n"
                + "                          FROM #TEMP_RETURNS A\n"
                + "                           INNER JOIN #TEMP_CCPD CCPD\n"
                + "                                  ON A.ITEM_MASTER_SID = CCPD.ITEM_MASTER_SID\n"
                + "                           ) TR\n"
                + "                      ON TR.ITEM_MASTER_sID = CC.ITEM_MASTER_sID\n"
                + "                  AND TR.PERIOD_SID = A.PERIOD_SID\n"
                + "                                  AND A.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID\n"
                + "                WHERE A.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "                        AND A.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + "                           AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                           AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n" + CommonLogic.getPeriodRestrictionQuery(projSelDTO) + "\n"
                + "                     ) A\n"
                + "              GROUP BY YEARS\n"
                + "                     , PERIODS\n"
                + "               ) HISTORY\n"
                + "       FULL  JOIN (\n"
                + "              SELECT YEARS\n"
                + "                     , PERIODS\n"
                + "                     , 0 AS SALES_ACTUAL_SALES\n"
                + "                     , sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , 0 AS ACTUAL_UNITS\n"
                + "                     , sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     , PROJECTED_RATE = Avg(P.PROJECTED_RATE)\n"
                + "                     , RETURNS_PROJECTED = Sum(P.PROJECTION_SALES) * Avg(PROJECTED_RATE)\n"
                + "                FROM (\n"
                + "                   SELECT I.\"YEAR\" AS YEARS\n"
                + "                           " + selectPeriod + "\n"
                + "                           , A.PROJECTION_SALES\n"
                + "                           , A.PROJECTION_UNITS\n"
                + "                           , TR.PROJECTED_RATE\n"
                + "                     FROM ST_NM_SALES_PROJECTION A\n"
                + "                  INNER JOIN PROJECTION_DETAILS E\n"
                + "                        ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "                INNER JOIN PERIOD I\n"
                + "                           ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     INNER JOIN @CCP CCP\n"
                + "                           ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                INNER JOIN CCP_DETAILS CC\n"
                + "                           ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                                  AND A.PERIOD_SID = I.PERIOD_SID\n"
                + "                LEFT JOIN (\n"
                + "                           SELECT A.ITEM_MASTER_sID\n"
                + "                              , A.PERIOD_sID\n"
                + "                                , A.ACTUAL_RATE\n"
                + "                              , A.PROJECTED_RATE\n"
                + "                                  , CCPD.PROJECTION_DETAILS_SID\n"
                + "                           FROM #TEMP_RETURNS A\n"
                + "                    INNER JOIN #TEMP_CCPD CCPD\n"
                + "                             ON A.ITEM_MASTER_SID = CCPD.ITEM_MASTER_SID\n"
                + "                           ) TR\n"
                + "                        ON TR.ITEM_MASTER_sID = CC.ITEM_MASTER_sID\n"
                + "                             AND TR.PERIOD_SID = A.PERIOD_SID\n"
                + "                                  AND A.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID\n"
                + "                     WHERE A.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "                      AND A.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + "                           AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                           AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n" + CommonLogic.getPeriodRestrictionQuery(projSelDTO) + "\n"
                + "                     ) P\n"
                + "                 GROUP BY YEARS\n"
                + "                     ,  PERIODS \n"
                + "            ) FUTURE\n"
                + "          ON HISTORY.YEARS = FUTURE.YEARS\n"
                + "                     AND HISTORY.PERIODS = FUTURE.PERIODS\n"
                + "       ) SALE\n"
                + "       ON TODIS.YEARS = SALE.YEARS\n"
                + "              AND TODIS.PERIODS = SALE.PERIODS";

        return query;
    }

    public String getProjectionResultsPivotReturnQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "      DROP TABLE #TEMP_CCP;\n"
                + "CREATE TABLE #TEMP_CCP (\n"
                + "      COMPANY_MASTER_SID INT\n"
                + "      , CONTRACT_MASTER_SID INT\n"
                + "     , ITEM_MASTER_SID INT\n"
                + "     , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "      , PROJECTION_MASTER_SID INT\n"
                + "     )\n"
                + "INSERT INTO #TEMP_CCP (\n"
                + "      COMPANY_MASTER_SID\n"
                + "     , CONTRACT_MASTER_SID\n"
                + "     , ITEM_MASTER_SID\n"
                + "     , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID \n "
                + "     )\n"
                + "SELECT  C.COMPANY_MASTER_SID\n"
                + "     , C.CONTRACT_MASTER_SID\n"
                + "     ,  C.ITEM_MASTER_SID \n "
                + "     , PD.PROJECTION_DETAILS_SID\n"
                + "         , pm.PROJECTION_MASTER_SID\n"
                + "FROM \n"
                + "      CCP_DETAILS  C\n"
                + "     , PROJECTION_DETAILS PD\n"
                + "     , PROJECTION_MASTER PM\n"
                + "WHERE  PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "     AND PD.PROJECTION_MASTER_SID =  PM.PROJECTION_MASTER_SID\n"
                + "     AND PM.PROJECTION_MASTER_SID  = " + projSelDTO.getProjectionId() + "\n"
                + "	 and EXISTS (SELECT 1 FROM  \n"
                + "	 @CCP CCP WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + "INSERT INTO @ITEMID\n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM\n"
                + "WHERE EXISTS (\n"
                + "            SELECT 1\n"
                + "            FROM #TEMP_CCP A\n"
                + "            WHERE PROJECTION_MASTER_SID  = " + projSelDTO.getProjectionId() + "\n"
                + "                 AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "            ) ";
        selectClause += " IF Object_id('TEMPDB..#TEMP_CCPD') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCPD\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID INT\n"
                + "       , CONTRACT_MASTER_SID INT\n"
                + "       ,   ITEM_MASTER_SID INT\n"
                + "       , PROJECTION_DETAILS_SID INT\n"
                + "         , PROJECTION_MASTER_SID INT\n"
                + CLOSE_SLASH_N
                + "\n"
                + "INSERT  INTO #TEMP_CCPD (\n"
                + "          COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       ,   ITEM_MASTER_SID \n"
                + "        , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + CLOSE_SLASH_N
                + "SELECT  DISTINCT COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID \n "
                + "       ,   ITEM_MASTER_SID \n"
                + "       , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + "FROM (\n"
                + "       SELECT  COMPANY_MASTER_SID\n"
                + "              , T_CCP.CONTRACT_MASTER_SID\n"
                + "              , T_CCP.ITEM_MASTER_SID\n"
                + "             , PROJECTION_DETAILS_SID\n"
                + "              , PROJECTION_MASTER_SID\n"
                + "              , RS_ID\n"
                + "           , RS_TYPE\n"
                + "     FROM #TEMP_CCP T_CCP\n"
                + "       INNER JOIN RS_CONTRACT RS\n"
                + "               ON T_CCP.CONTRACT_MASTER_SID = RS.CONTRACT_MASTER_SID\n"
                + "       INNER JOIN RS_CONTRACT_DETAILS RSC\n"
                + "            ON RS.RS_CONTRACT_SID = RSC.RS_CONTRACT_SID\n"
                + "                     AND T_CCP.ITEM_MASTER_SID = RSC.ITEM_MASTER_SID\n"
                + "       WHERE EXISTS (\n"
                + "                     SELECT 1\n"
                + "                     FROM CFP_CONTRACT CF\n"
                + "                     INNER JOIN CFP_CONTRACT_DETAILS CFD\n"
                + "                           ON CF.CFP_CONTRACT_SID = CFD.CFP_CONTRACT_SID\n"
                + "                                  AND T_CCP.COMPANY_MASTER_SID = CFD.COMPANY_MASTER_SID\n"
                + "                               AND RS.CFP_CONTRACT_SID = CF.CFP_CONTRACT_SID\n"
                + "                                  AND CF.CONTRACT_MASTER_SID = T_CCP.CONTRACT_MASTER_SID\n"
                + "                     )\n"
                + "       ) R\n"
                + "INNER JOIN HELPER_TABLE HT\n"
                + "    ON R.RS_TYPE = HT.HELPER_TABLE_SID\n"
                + " WHERE HT.DESCRIPTION = 'Returns'\n"
                + "\n"
                + "DECLARE @ITEM_ID [DBO].[UDT_ITEM] \n"
                + "\n"
                + "INSERT INTO  @ITEM_ID\n"
                + "SELECT  DISTINCT  ITEM_MASTER_SID \n"
                + "FROM  #TEMP_CCPD A\n"
                + "\n"
                + "DECLARE @COUNT  INT\n"
                + "\n"
                + "IF Object_id('TEMPDB..#ITEM') IS NOT NULL\n"
                + "     DROP TABLE #ITEM\n"
                + "\n"
                + "CREATE TABLE #ITEM (\n"
                + "        ID INT IDENTITY(1, 1)\n"
                + "       ,  ITEM_MASTER_SID INT\n"
                + CLOSE_SLASH_N
                + "\n"
                + "INSERT INTO #ITEM  (ITEM_MASTER_SID)\n"
                + "SELECT  ITEM_MASTER_SID\n"
                + "FROM  @ITEMID\n"
                + "\n"
                + "SET @COUNT = @@ROWCOUNT\n"
                + "\n"
                + "DECLARE  @I INT = 1\n"
                + "DECLARE @ITEM INT\n"
                + "\n"
                + "IF  Object_id('TEMPDB..#TEMP_RETURNS') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_RETURNS\n"
                + "\n"
                + "CREATE TABLE #TEMP_RETURNS (\n"
                + "       ITEM_MASTER_SID INT\n"
                + "       , RETURNS_DETAILS_SID INT\n"
                + "       , PERIOD_SID INT\n"
                + "       , ACTUAL_RATE NUMERIC(22, 6)\n"
                + "      , PROJECTED_RATE NUMERIC(22, 6)\n"
                + CLOSE_SLASH_N
                + "\n"
                + "WHILE (@I <= @COUNT)\n"
                + "BEGIN \n"
                + "       SET @ITEM = (\n"
                + "                  SELECT ITEM_MASTER_SID\n"
                + "                     FROM #ITEM\n"
                + "                     WHERE id = @I\n"
                + "                 );\n"
                + "\n"
                + "       WITH ITEM_PROJ_DETAILS\n"
                + "       AS   (\n"
                + "              SELECT ROW_NUMBER() OVER (\n"
                + "               PARTITION BY ITEM_MASTER_SID ORDER BY LASTEST_DATE DESC\n"
                + "                        ) RN\n"
                + "                      , ITEM_MASTER_SID\n"
                + "                     , PM.PROJECTION_MASTER_SID\n"
                + "                 , RETURNS_DETAILS_SID\n"
                + "     FROM RETURNS_DETAILS RD\n"
                + "           INNER JOIN PROJECTION_MASTER PM\n"
                + "           ON RD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "         CROSS APPLY (\n"
                + "                     VALUES  (MODIFIED_DATE)\n"
                + "                           , (CREATED_DATE)\n"
                + "                     )  CS(LASTEST_DATE)\n"
                + "              WHERE SAVE_FLAG = 1\n"
                + "                     AND ITEM_MASTER_SID = @ITEM\n"
                + "              )\n"
                + "       INSERT INTO #TEMP_RETURNS  (\n"
                + "          ITEM_MASTER_SID\n"
                + "                , RETURNS_DETAILS_SID\n"
                + "              , PERIOD_SID\n"
                + "              , ACTUAL_RATE\n"
                + "              , PROJECTED_RATE\n"
                + "              )\n"
                + "       SELECT @ITEM AS  ITEM_MASTER_SID\n"
                + "               , COALESCE(R_ACTUALS.RETURNS_DETAILS_SID, R_PROJ.RETURNS_DETAILS_SID) AS RETURNS_DETAILS_SID\n"
                + "              , COALESCE(R_ACTUALS.PERIOD_SID, R_PROJ.PERIOD_SID) AS PERIOD_SID\n"
                + "              , R_ACTUALS.ACTUAL_RETURN_PERCENT\n"
                + "              , R_PROJ.PROJECTED_RETURN_PERCENT\n"
                + "       FROM (\n"
                + "  SELECT RETURNS_DETAILS_SID\n"
                + "                 , PERIOD_SID\n"
                + "                     , ACTUAL_RETURN_PERCENT\n"
                + "           FROM RETURNS_ACTUALS NAP\n"
                + "              WHERE EXISTS  (\n"
                + "                            SELECT 1\n"
                + "                           FROM RETURNS_DETAILS RD\n"
                + "  INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                         AND RD.RETURNS_DETAILS_SID = NAP.RETURNS_DETAILS_SID\n"
                + "                           WHERE IMPD.RN = 1\n"
                + "               )\n"
                + "              ) R_ACTUALS\n"
                + "       FULL JOIN  (\n"
                + "              SELECT RETURNS_DETAILS_SID\n"
                + "                  , PERIOD_SID\n"
                + "                     , PROJECTED_RETURN_PERCENT\n"
                + "              FROM RETURNS_PROJ_DETAILS NPP\n"
                + "              WHERE EXISTS  (\n"
                + "                           SELECT 1\n"
                + "                           FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "              ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                         AND RD.RETURNS_DETAILS_SID = NPP.RETURNS_DETAILS_SID\n"
                + "                           WHERE IMPD.RN = 1\n"
                + "                       )\n"
                + "            ) R_PROJ\n"
                + "              ON R_ACTUALS.RETURNS_DETAILS_SID = R_PROJ.RETURNS_DETAILS_SID\n"
                + "                     AND R_ACTUALS.PERIOD_SID = R_PROJ.PERIOD_SID\n"
                + "\n"
                + "       SET  @I = @I + 1\n"
                + "END";

        selectClause += "\n select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);

        selectClause += "  'Returns' AS DISCOUNTS\n"
                + "       , RETURNS_ACTUAL_AMOUNT = ISNULL(SALE.RETURNS_ACTUAL, 0)\n"
                + "       , RETURNS_ACTUAL_AMOUNT = ISNULL(SALE.RETURNS_PROJECTED, 0)\n"
                + "       , RETURNS_ACTUAL_RPU = Isnull((Isnull(SALE.RETURNS_ACTUAL, 0)) / NULLIF(SALE.ACTUAL_UNITS, 0), 0)\n"
                + "       , RETURNS_PROJECTED_RPU = Isnull((Isnull(SALE.RETURNS_PROJECTED, 0)) / NULLIF(SALE.ACTUAL_UNITS, 0), 0)\n"
                + "       , RETURNS_ACTUAL_RATE = Isnull(SALE.RETURNS_ACTUAL_PERCENT, 0)\n"
                + "       , RETURNS_PROJECTED_RATE = Isnull(SALE.RETURNS_PROJECTED_PERCENT, 0)";

        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsReturnsQuery(projSelDTO);
        customQuery = selectClause + "   from   ( \n  " + totalDiscountQuery + "\n)  TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + " \n";
        return customQuery;
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
        return CollectionToString(collectionOfString, toAddQuote, false);
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @param toRemoveSpace
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean toRemoveSpace) {

        String framedString = "";
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "'").replace("]", "'").replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "").replace("]", "");
            }

            if (toRemoveSpace) {
                framedString.replace(", ", "");
            }
        }
        return framedString;
    }

       public String getProjectionResultsNetSalesQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = StringConstantsUtil.SPACE_SELECT;
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(StringConstantsUtil.TODIS, "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Net Sales' as NETSALES, \n";
        selectClause += " ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-(Isnull(TODIS.ACTUAL_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += NULL_PPAACTUAL_SALES;
        }
        selectClause += ")),\n PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-(Isnull(TODIS.PROJECTION_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull( PPA.PROJECTION_SALES, 0)";
        }
        selectClause += "))  \n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n)  TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n)  PPA \n" + finalWhereCond1;
        }
        return customQuery;
    }
   
    
   
    private List<ProjectionResultsDTO> setPivotValue(List<Object[]> list, int frequencyDivision, List<String> periodList, ProjectionSelectionDTO projSelDTO, int dcol, List<Object[]> discountList) {
        List<ProjectionResultsDTO> returnList = new ArrayList<>();
        int discountIndex = 0;
        for (Object[] row : list) {

            String column = "";
            int year = 0;
            if (row[NumericConstants.THREE] != null) {
                year = Integer.valueOf(String.valueOf(row[NumericConstants.THREE]));
            }
            int period = 0;
            if (row[NumericConstants.FOUR] != null) {
                period = Integer.valueOf(String.valueOf(row[NumericConstants.FOUR]));
            }
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = "";
           if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
                List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
                columnList.remove(StringConstantsUtil.GROUP_PROPERTY);
                projDTO.setGroup(commonHeader);
                String value;
                commonColumn = "exFactory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                commonColumn = "custExFactory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FORTY_NINE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FIFTY];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                commonColumn = "demand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.TWENTY_NINE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.THIRTY];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "adjDemand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FORTY_SEVEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FORTY_EIGHT];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "inventory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.THIRTY_ONE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.THIRTY_TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "inventoryDetails";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FIFTY_ONE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FIFTY_TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perExFactory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.THIRTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FOURTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perCustExFactory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FIFTY_FIVE];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FIFTY_SIX];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perDemand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.THIRTY_THREE];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.THIRTY_FOUR];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perAdjDemand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FIFTY_THREE];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FIFTY_FOUR];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perInventory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.THIRTY_FIVE];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.THIRTY_SIX];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perInventoryDetails";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FIFTY_SEVEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[NumericConstants.FIFTY_EIGHT];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "conSalesWac";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   value=getCellValue(row[NumericConstants.FIVE], StringConstantsUtil.SALES);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                  value=getCellValue(row[NumericConstants.SIX], StringConstantsUtil.SALES);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "unitVol";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value=getCellValue(row[NumericConstants.SEVEN], StringConstantsUtil.UNITS1);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value=getCellValue(row[NumericConstants.EIGHT], StringConstantsUtil.UNITS1);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisPer";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   value=getCellValue(row[NumericConstants.ELEVEN], StringConstantsUtil.PERCENT1);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   value=getCellValue(row[NumericConstants.TWELVE], StringConstantsUtil.PERCENT1);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totalRPU";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value=getCellValue(row[NumericConstants.FORTY_ONE], StringConstantsUtil.SALES);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   value=getCellValue(row[NumericConstants.FORTY_TWO], StringConstantsUtil.SALES);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisDol";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   value=getCellValue(row[NumericConstants.NINE], StringConstantsUtil.SALES);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   value=getCellValue(row[NumericConstants.TEN], StringConstantsUtil.SALES);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netSales";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   value=getCellValue(row[NumericConstants.TWENTY_SEVEN], StringConstantsUtil.SALES);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                  value=getCellValue(row[NumericConstants.TWENTY_EIGHT], StringConstantsUtil.SALES);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "cogs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                  value=getCellValue(row[NumericConstants.FORTY_THREE], StringConstantsUtil.SALES);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   value=getCellValue(row[NumericConstants.FORTY_FOUR], StringConstantsUtil.SALES);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netProfit";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   value=getCellValue(row[NumericConstants.FORTY_FIVE], StringConstantsUtil.SALES);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value=getCellValue(row[NumericConstants.FORTY_SIX], StringConstantsUtil.SALES);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                //Discount % of Ex-Factory
               commonColumn = "disPerExFactory";
               column = commonColumn + ACTUALS.getConstant();
               if (projSelDTO.hasColumn(column)) {
                   value = getFormattedValue(PER_TWO, String.valueOf(row[NumericConstants.SIXTY_ONE]));
                   projDTO.addStringProperties(column, value);
                   columnList.remove(column);
               }
               column = commonColumn + PROJECTIONS.getConstant();
               if (projSelDTO.hasColumn(column)) {
                   value = getFormattedValue(PER_TWO, String.valueOf(row[NumericConstants.SIXTY_TWO]));
                   projDTO.addStringProperties(column, value);
                   columnList.remove(column);
               }
               //Net Sales % of Ex-Factory
               commonColumn = "netSalesPerExFactory";
               column = commonColumn + ACTUALS.getConstant();
               if (projSelDTO.hasColumn(column)) {
                   value = getFormattedValue(PER_TWO, String.valueOf(row[NumericConstants.FIFTY_NINE]));
                   projDTO.addStringProperties(column, value);
                   columnList.remove(column);
               }
               column = commonColumn + PROJECTIONS.getConstant();
               if (projSelDTO.hasColumn(column)) {
                   value = getFormattedValue(PER_TWO, String.valueOf(row[NumericConstants.SIXTY]));
                   projDTO.addStringProperties(column, value);
                   columnList.remove(column);
               }
                boolean start = false;
                if (discountList != null) {
                    for (int i = discountIndex; i < discountList.size(); i++) {
                        Object[] discountRow = discountList.get(i);
                        int dyear = Integer.valueOf(String.valueOf(discountRow[dcol - NumericConstants.TWO]));
                        int dperiod = Integer.valueOf(String.valueOf(discountRow[dcol-1]));
                        List<String> dcommon = getCommonColumnHeader(frequencyDivision, dyear, dperiod);
                        String dcommonColumn = dcommon.get(0);
                        if (pcommonColumn.equals(dcommonColumn)) {
                            start = true;
                            discountIndex = i;
                            String head = String.valueOf(discountRow[dcol]).replace(" ", "");
                            String commonColumn1 = "totDisDol" + head;
                            String commonColumn3 = "totalRPU" + head;
                            String commonColumn2 = "totDisPer" + head;
                            String commonColumn4 = "disPerExFactory" + head;
                            column = commonColumn1 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                               value=getCellValue(discountRow[dcol + 1], StringConstantsUtil.SALES);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn1 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value=getCellValue(discountRow[dcol + NumericConstants.TWO], StringConstantsUtil.SALES);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn2 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                              value=getCellValue(discountRow[dcol + NumericConstants.THREE], StringConstantsUtil.PERCENT1);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn2 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                               value=getCellValue(discountRow[dcol + NumericConstants.FOUR], StringConstantsUtil.PERCENT1);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn3 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                               value=getCellValue(discountRow[dcol + NumericConstants.FIVE], StringConstantsUtil.SALES);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn3 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = getCellValue(discountRow[dcol + NumericConstants.SIX], StringConstantsUtil.SALES);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            //Discount % of Ex-Factory
                            column = commonColumn4 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = getFormattedValue(PER_TWO, String.valueOf(discountRow[dcol + NumericConstants.SEVEN]));
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn4 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = getFormattedValue(PER_TWO, String.valueOf(discountRow[dcol + NumericConstants.EIGHT]));
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }

                        } else if (start) {
                            break;
                        }
                    }
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                returnList.add(projDTO);
            }

        }
        if (!periodList.isEmpty()&&!projSelDTO.isIsProjectionTotal()) {
           List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
           columnList.remove(StringConstantsUtil.GROUP_PROPERTY);
            for (String period : periodList) {
                ProjectionResultsDTO dto = new ProjectionResultsDTO();
                for (Object obj : columnList) {
                    dto.addStringProperties(obj, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
                }
                dto.setGroup(projSelDTO.getPeriodListMap().get(period));
                returnList.add(dto);
            }
        }
        return returnList;
    }
    
    
   
   private void loadMap()
   {
     monthMap.put("1","jan");
     monthMap.put("2","feb");
     monthMap.put("3","mar");
     monthMap.put("4","apr");
     monthMap.put("5","may");
     monthMap.put("6","jun");
     monthMap.put("7","jul");
     monthMap.put("8","aug");
     monthMap.put("9","sep");
     monthMap.put("10","oct");
     monthMap.put("11","nov");
     monthMap.put("12","dec");
   }
   
    public int getDiscoutCount(ProjectionSelectionDTO projDTO) {
        List input = new ArrayList<>(1);
        input.add(projDTO.getProjectionId());
        input.add(projDTO.getCcpIds());
        if (projDTO.getCcpIds() == null || projDTO.getCcpIds().isEmpty()) {
            input.add(StringUtils.EMPTY);
            input.add(StringUtils.EMPTY);
        } else {
            String inQuery = " AND PD.CCP_DETAILS_SID IN (SELECT TOKEN FROM UDF_SPLITSTRING(@CCP_IDS,','))";
            input.add(inQuery);
            input.add(inQuery);
        }
        List<Object[]> list = CommonQueryUtils.getAppData(input, "getCffDiscountExpandCount", "getCffDiscountExpandCount_PROGRAM");
        int count = CFFLogic.getCount(list);
        return count;
    }

    public static List<String> getRSName(int cffSid) {
        List list = CommonQueryUtils.getAppData(Arrays.asList(new Integer[]{cffSid}), "getDiscountName", null);
        List<String> discountNamelist = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                discountNamelist.add(String.valueOf(obj[1]));
            }
        }
        list.clear();
        return discountNamelist;
    }
   
    private String getCommonColumn(ProjectionSelectionDTO projSelDTO,Object[] obj)
 {
     String column=StringUtils.EMPTY;        
     if(projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
                    column = "q" + String.valueOf(obj[NumericConstants.TWO]) + String.valueOf(obj[1]);
                } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
                    column = "s" + String.valueOf(obj[NumericConstants.TWO]) + String.valueOf(obj[1]);
                } else if (projSelDTO.getFrequencyDivision() == 1) {
                    column = String.valueOf(obj[1]);
                } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
                    String month = monthMap.get(String.valueOf(obj[NumericConstants.TWO]));
                    column = month + String.valueOf(obj[1]);
                }
     return column;
 }
   
   private String getCellValue(Object obj,String component)
 {
         String returnValue = StringUtils.EMPTY;
     if (obj == null) {
         return returnValue;
     } else {
         returnValue = "" + obj; //---- col ----- 6
         if(component.equals(StringConstantsUtil.SALES)){
         returnValue = getFormatTwoDecimalValue(CUR_TWO, returnValue, CURRENCY);
         }else if(component.equals(StringConstantsUtil.UNITS1)){
            returnValue = getFormattedValue(NUM_ZERO, returnValue); 
         }else{
            returnValue= getFormattedValue(PER_TWO, returnValue);
         }
         return returnValue;
     }
 }
 
 private String getCustomizedCcp(String query)
 {
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
    
    private List<ProjectionResultsDTO> getLevelListforNonmandated(int start, int offset, int started, ProjectionSelectionDTO projSelDTO, int neededRecord) {
        CommonLogic commonLogic = new CommonLogic();
        List<ProjectionResultsDTO> resultList = new ArrayList<>();
    
        if (projSelDTO.isIsCustomHierarchy()) {

            String hierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
            Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
            List<String> hierarchyNoList = commonLogic.getHiearchyNoForCustomView(projSelDTO, start, offset);
            for (int i = 0; i < hierarchyNoList.size() && neededRecord > 0; neededRecord--, i++) {
                String hierarchyNo = hierarchyNoList.get(i);
                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + (started + i))) {
                    resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, hierarchyIndicator, projSelDTO.getTreeLevelNo(), relationshipLevelDetailsMap.get(hierarchyNo), hierarchyNoList.size(), i));

}
                started++;
            }

        } else {
            Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();

            List<String> hierarchyNoList = commonLogic.getHiearchyNoAsList(projSelDTO, start, offset);
            for (int i = 0; i < hierarchyNoList.size() && neededRecord > 0; neededRecord--, i++) {
                String hierarchyNo = hierarchyNoList.get(i);
                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + (started + i))) {
                    resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, projSelDTO.getHierarchyIndicator(), Integer.valueOf(relationshipLevelDetailsMap.get(hierarchyNo).get(NumericConstants.TWO).toString()), relationshipLevelDetailsMap.get(hierarchyNo), hierarchyNoList.size(), i));
                }
                started++;
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
    public ProjectionResultsDTO configureDetailsInDTO(ProjectionSelectionDTO projSelDTO, String hierarchyNo, String hierarchyIndicator, int levelNo, List detailsList, int listSize, int i) {
        ProjectionResultsDTO dto = new ProjectionResultsDTO();
        dto.setLevelNo(Integer.valueOf(detailsList.get(NumericConstants.TWO).toString()));
        dto.setTreeLevelNo(levelNo);
        dto.setGroup(detailsList.get(0).toString());
        dto.setLevelValue(detailsList.get(NumericConstants.THREE).toString());
        if (i == (listSize - 1)) {
            dto.setGroup(detailsList.get(0).toString());
            dto.setLevelValue(detailsList.get(NumericConstants.THREE).toString());
        }
        dto.setHierarchyNo(hierarchyNo);
        dto.setHierarchyIndicator(hierarchyIndicator);
        if (dto.getHierarchyIndicator().equals("C")) {
            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
            dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        } else if (dto.getHierarchyIndicator().equals("P")) {
            dto.setProductHierarchyNo(dto.getHierarchyNo());
            dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        }
        dto.setOnExpandTotalRow(1);
        dto.setParent(1);
        dto.setProjectionTotal(0);

        return dto;
    }
}