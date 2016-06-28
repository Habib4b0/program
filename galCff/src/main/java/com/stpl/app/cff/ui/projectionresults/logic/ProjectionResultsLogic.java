package com.stpl.app.cff.ui.projectionresults.logic;

import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.projectionresults.dto.ProjectionResultsDTO;
import com.stpl.app.cff.util.CommonUtils;
import static com.stpl.app.cff.util.CommonUtils.BOTH;
import static com.stpl.app.cff.util.Constants.LabelConstants.*;
import static com.stpl.app.cff.util.HeaderUtils.getCommonColumnHeader;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
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
     * The Currency Two Decimal Places Format.
     */
    private static final DecimalFormat CUR_TWO_DOL = new DecimalFormat("$#,##0.00");
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PER_TWO = new DecimalFormat("#,##0.00%");
    /**
     * Percent Three Decimal Format
     */
    private static final DecimalFormat PER_THREE = new DecimalFormat("#,##0.000%");
    /**
     * The Numeric Zero Decimal Places Format.
     */
    private static final DecimalFormat NUM_ZERO = new DecimalFormat("#,##0");
    List<ProjectionResultsDTO> prjTotalDisPerDtoList = new ArrayList<>();
    List<ProjectionResultsDTO> prjTotalDisDolDtoList = new ArrayList<>();
    List<ProjectionResultsDTO> prjTotalRPUDtoList = new ArrayList<>();
    List<ProjectionResultsDTO> projectionTotalList = new ArrayList<>();

    private static final String CURRENCY = "$";
    private static final DecimalFormat CUR_TWO = new DecimalFormat("#,##0.00");
    
    Map<String,String> monthMap=new HashMap<String, String>();

    public List<ProjectionResultsDTO> getDiscountPer(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getDiscountPer = = =");
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setSales("RATE");
        String query = CommonLogic.getCCPQuery(projSelDTO) + " \n";
        query += getProjectionResultsDiscountsPerQuery(projSelDTO);

        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, false);
        projDTOList.addAll(projDTOList1);

        LOGGER.info("= = = Ending getDiscountPer = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getTotalRPUDollar(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getTotalRPUDollar = = =");
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setSales("TOT");
        String query = CommonLogic.getCCPQuery(projSelDTO) + " \n";
        query += getProjectionResultsDiscountsRPUQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, false);
        projDTOList.addAll(projDTOList1);
        LOGGER.info("= = = Ending getTotalRPUDollar = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getDiscountDollar(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getDiscountDollar = = =");
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        projSelDTO.setSales("SALES");
        String query = CommonLogic.getCCPQuery(projSelDTO) + " \n";
        query += getProjectionResultsDiscountsQuery(projSelDTO, " order by DISCOUNTS");
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, false);
        projDTOList.addAll(projDTOList1);

        LOGGER.info("= = = Ending getDiscountDollar = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getTotalDiscountLevels(ProjectionSelectionDTO projSelDTO,String group) {
        LOGGER.info("= = = Inside getTotalDiscountLevels = = =");
         List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
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
        Object[] orderedArgs2 = {projSelDTO.getProjectionId(), projSelDTO.getFrequency(), "ASSUMPTIONS","PERIOD", null, ccps, null, "excel"};
          List<Object[]> gtsList = CommonLogic.callProcedure("Prc_cff_projection_results_discount", orderedArgs2);
            projDTOList =getCustomizedDiscountsProjectionTotal(gtsList, projSelDTO,group);

     return projDTOList;
    }

    private List getCustomizedProjectionTotalDiscount(List list, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> resultList = new ArrayList<ProjectionResultsDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("group");
        ProjectionResultsDTO totalDiscountPerDto = new ProjectionResultsDTO();
        ProjectionResultsDTO totalRPUDto = new ProjectionResultsDTO();
        ProjectionResultsDTO totalDiscountAmtDto = new ProjectionResultsDTO();

        totalDiscountPerDto.setParent(1);
        totalDiscountPerDto.setGroup(TOTAL_DISCOUNT_PERC.getConstant());
        totalDiscountPerDto.setProjectionTotal(0);
        totalDiscountPerDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalDiscountPerDto.setOnExpandTotalRow(0);
        totalDiscountPerDto.setLevelValue(projSelDTO.getLevelValue());
        totalDiscountPerDto.setLevelNo(projSelDTO.getLevelNo());
        totalDiscountPerDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalDiscountPerDto.setHierarchyNo(projSelDTO.getHierarchyNo());

        totalRPUDto.setParent(1);
        totalRPUDto.setGroup(TOTAL_RPU.getConstant());
        totalRPUDto.setProjectionTotal(0);
        totalRPUDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalRPUDto.setOnExpandTotalRow(0);
        totalRPUDto.setLevelValue(projSelDTO.getLevelValue());
        totalRPUDto.setLevelNo(projSelDTO.getLevelNo());
        totalRPUDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalRPUDto.setHierarchyNo(projSelDTO.getHierarchyNo());

        totalDiscountAmtDto.setParent(1);
        totalDiscountAmtDto.setGroup(TOTAL_DISCOUNT_AMOUNT.getConstant());
        totalDiscountAmtDto.setProjectionTotal(0);
        totalDiscountAmtDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalDiscountAmtDto.setOnExpandTotalRow(0);
        totalDiscountAmtDto.setLevelValue(projSelDTO.getLevelValue());
        totalDiscountAmtDto.setLevelNo(projSelDTO.getLevelNo());
        totalDiscountAmtDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalDiscountAmtDto.setHierarchyNo(projSelDTO.getHierarchyNo());

        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = "";
                int year = (obj[0] == null) ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = (obj[1] == null) ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                String commonColumn = common.get(0);
                int col = 3;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = "";
                    value = "" + obj[col];
                    value = getFormattedValue(PER_TWO, value);
                    totalDiscountPerDto.addStringProperties(column, value);
                    value = "" + obj[col + 3];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalDiscountAmtDto.addStringProperties(column, value);
                    value = "" + obj[col + 6];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalRPUDto.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = "" + obj[col + 1];
                    value = getFormattedValue(PER_TWO, value);
                    totalDiscountPerDto.addStringProperties(column, value);
                    value = "" + obj[col + 4];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalDiscountAmtDto.addStringProperties(column, value);
                    value = "" + obj[col + 7];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalRPUDto.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            totalDiscountPerDto.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
            totalRPUDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            totalDiscountAmtDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
        }
        resultList.add(totalDiscountPerDto);
        resultList.add(totalRPUDto);
        resultList.add(totalDiscountAmtDto);

        return resultList;
    }

    public ProjectionResultsDTO getPPAPer(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales("RATE");
        ProjectionResultsDTO ppaDto = new ProjectionResultsDTO();
        String query = CommonLogic.getCCPQuery(projSelDTO) + " \n" + getProjectionResultsPPAPerQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, true);
        if (projDTOList1 != null && !projDTOList1.isEmpty()) {
            ppaDto = projDTOList1.get(0);
        }
        ppaDto.setGroup(PPA_DISCOUNT.getConstant());
        ppaDto.setParent(0);
        return ppaDto;
    }

    public ProjectionResultsDTO getPPARPU(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales("RATE");
        ProjectionResultsDTO ppaDto = new ProjectionResultsDTO();
        String query = CommonLogic.getCCPQuery(projSelDTO) + " \n" + getProjectionResultsPPARPU(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, true);
        if (projDTOList1 != null && !projDTOList1.isEmpty()) {
            ppaDto = projDTOList1.get(0);
        }
        ppaDto.setGroup(PPA_DISCOUNT.getConstant());
        ppaDto.setParent(0);
        return ppaDto;
    }

    public ProjectionResultsDTO getPPADollar(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales("SALES");
        ProjectionResultsDTO ppaDto = new ProjectionResultsDTO();
        String query = CommonLogic.getCCPQuery(projSelDTO) + " \n" + getProjectionResultsPPAQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, true);
        if (projDTOList1 != null && !projDTOList1.isEmpty()) {
            ppaDto = projDTOList1.get(0);
        }
        ppaDto.setGroup(PPA_DISCOUNT.getConstant());
        ppaDto.setParent(0);
        return ppaDto;
    }
    List<String> discountList = new ArrayList<String>();

    public List<ProjectionResultsDTO> getCustomizedProjectionResultsDiscount(List<Object> list, ProjectionSelectionDTO projSelDTO, boolean isPPA) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        projSelDTO.setDiscountIndex(0);
        if (!projSelDTO.isIsTotal() && !isPPA) {
            discountList = new ArrayList<String>(projSelDTO.getDiscountNameList());
            for (String name : projSelDTO.getDiscountNameList()) {
                ProjectionResultsDTO dto = getCustomisedProjectionResultsDiscount(list, name, projSelDTO);
                projDTOList.add(dto);
            }
            List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
            columnList.remove("group");
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
            ProjectionResultsDTO dto = getCustomisedProjectionResultsDiscount(list, "total", projSelDTO);
            projDTOList.add(dto);
        }
        return projDTOList;
    }

    public ProjectionResultsDTO getCustomisedProjectionResultsDiscount(List<Object> list, String discountName, ProjectionSelectionDTO projSelDTO) {
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("group");
        ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
        projDTO.setLevelValue(projSelDTO.getLevelValue());
        projDTO.setLevelNo(projSelDTO.getLevelNo());
        projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projDTO.setGroup(null);
        boolean forword = false;
        if (discountName.contains("total")) {
            forword = true;
        }
        boolean start = true;
        String discount = null;
        if (list != null && !list.isEmpty()) {
            for (int i = projSelDTO.getDiscountIndex(); i < list.size() && start; i++) {
                projSelDTO.setDiscountIndex(i);
                Object[] discountRow = (Object[]) list.get(i);
                if (discountList.contains("" + discountRow[2])) {
                    forword = true;
                } else {
                    if (!discountName.contains("total")) {
                        forword = false;
                    }
                }
                if (forword) {
                    if (discount == null) {
                        projDTO.setGroup(String.valueOf(discountRow[2]));
                    } else if (!discount.equals(discountRow[2].toString())) {
                        if (!discountName.contains("total")) {
                            discountList.remove(discount);
                        }
                        start = false;
                    }
                    if (start) {
                        discount = discountRow[2].toString();
                        String column = "";
                        int year = Integer.valueOf(String.valueOf(discountRow[0]));
                        int period = Integer.valueOf(String.valueOf(discountRow[1]));
                        List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                        String commonColumn = common.get(0);
                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            String value = "" + discountRow[3];
                            if (projSelDTO.getSales().contains("SALES")) {
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
                            String value = "" + discountRow[4];
                            if (projSelDTO.getSales().contains("SALES")) {
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
        if (discountName.contains("total")) {
            projDTO.setParent(1);
            projDTO.setLevelNo(projSelDTO.getLevelNo());
            projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
            projDTO.setParentNode(projSelDTO.getParentNode());
            projDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
            projDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
            projDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
            projDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
            projDTO.setOnExpandTotalRow(0);
            if (projSelDTO.getSales().contains("SALES")) {
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
        List<ProjectionResultsDTO> projDtoList = new ArrayList<ProjectionResultsDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("group");
        ProjectionResultsDTO projSalesDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO projUnitDTO = new ProjectionResultsDTO();
         ProjectionResultsDTO totalDiscountPerDto = new ProjectionResultsDTO();
        ProjectionResultsDTO totalRPUDto = new ProjectionResultsDTO();
        ProjectionResultsDTO totalDiscountAmtDto = new ProjectionResultsDTO();
        ProjectionResultsDTO netSalesDto= new ProjectionResultsDTO();
        ProjectionResultsDTO cogsDto = new ProjectionResultsDTO();
        ProjectionResultsDTO netprofitDto = new ProjectionResultsDTO();
        
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

        totalRPUDto.setParent(1);
        totalRPUDto.setGroup(TOTAL_RPU.getConstant());
        totalRPUDto.setProjectionTotal(0);
        totalRPUDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalRPUDto.setOnExpandTotalRow(0);
        totalRPUDto.setLevelValue(projSelDTO.getLevelValue());
        totalRPUDto.setLevelNo(projSelDTO.getLevelNo());
        totalRPUDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalRPUDto.setHierarchyNo(projSelDTO.getHierarchyNo());

        totalDiscountAmtDto.setParent(1);
        totalDiscountAmtDto.setGroup(TOTAL_DISCOUNT_AMOUNT.getConstant());
        totalDiscountAmtDto.setProjectionTotal(0);
        totalDiscountAmtDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalDiscountAmtDto.setOnExpandTotalRow(0);
        totalDiscountAmtDto.setLevelValue(projSelDTO.getLevelValue());
        totalDiscountAmtDto.setLevelNo(projSelDTO.getLevelNo());
        totalDiscountAmtDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalDiscountAmtDto.setHierarchyNo(projSelDTO.getHierarchyNo());
        
        
        
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
        
        
       
        
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = "";
                int year = Integer.valueOf(String.valueOf(obj[3]));
                int period = Integer.valueOf(String.valueOf(obj[4]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
             //   int col = 2;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   // String value = "" + obj[5];
                    //value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    String value=getCellValue(obj[5], "Sales");
                    projSalesDTO.addStringProperties(column, value);
                    
                   // value = "" + obj[7];
                  //  value = getFormattedValue(NUM_ZERO, value);
                    value=getCellValue(obj[7], "Units");
                    projUnitDTO.addStringProperties(column, value);
                   
                  //  value = "" + obj[11];
                   // value = getFormattedValue(PER_TWO, value);
                   
                    value=getCellValue(obj[11], "Percent");
                    totalDiscountPerDto.addStringProperties(column, value);
                   
                   // value = "" + obj[9];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(obj[9], "Sales");
                    totalDiscountAmtDto.addStringProperties(column, value);
                    
                    
                   // value = "" + obj[41];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value=getCellValue(obj[41], "Sales");
                    totalRPUDto.addStringProperties(column, value);

                   // value = "" + obj[27];
                   //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(obj[27], "Sales");
                    netSalesDto.addStringProperties(column, value);
                    
                   // value = "" + obj[43];
                 //   value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                  value=getCellValue(obj[43], "Sales");
                    cogsDto.addStringProperties(column, value);
                    
                  //  value = "" + obj[45];
                  // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                  value=getCellValue(obj[45], "Sales");
                    netprofitDto.addStringProperties(column, value);
                    
                    
                    
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   // String value = "" + obj[6];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                  String value =getCellValue(obj[6], "Sales");
                    projSalesDTO.addStringProperties(column, value);
                    
                   // value = "" + obj[8];
                    //value = getFormattedValue(NUM_ZERO, value);
                     value =getCellValue(obj[8], "Units");
                    projUnitDTO.addStringProperties(column, value);
                    
                   // value = "" + obj[12];
                   // value = getFormattedValue(PER_TWO, value);
                   value =getCellValue(obj[12], "Percent");
                    totalDiscountPerDto.addStringProperties(column, value);
                    
                   // value = "" + obj[10];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value =getCellValue(obj[10], "Sales");
                    totalDiscountAmtDto.addStringProperties(column, value);
                    
                    //value = "" + obj[42];
                    //value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value =getCellValue(obj[42], "Sales");
                    totalRPUDto.addStringProperties(column, value);
                    
                   //  value = "" + obj[28];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                  value=getCellValue(obj[28], "Sales");
                    netSalesDto.addStringProperties(column, value);
                    
                   // value = "" + obj[44];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(obj[44], "Sales");
                    cogsDto.addStringProperties(column, value);
                    
                   // value = "" + obj[46];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(obj[46], "Sales");
                    netprofitDto.addStringProperties(column, value);
                    
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
        }
        projDtoList.add(projSalesDTO);
        projDtoList.add(projUnitDTO);
        projDtoList.add(totalDiscountPerDto);
        projDtoList.add(totalRPUDto);
        projDtoList.add(totalDiscountAmtDto);
        projDtoList.add(netSalesDto);
         projDtoList.add(cogsDto);
        projDtoList.add(netprofitDto); 
        
        return projDtoList;
    }

  public List<ProjectionResultsDTO> getQueryForHierarchyExpand(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getContractSalesAndUnits = = =");
        projSelDTO.setSales("SALES");
        String query = CommonLogic.getCCPQueryForCff(projSelDTO);
          System.out.println("==expand ccp query==============>>>>"+query);
//                + " \n" + getQueryForExpand(projSelDTO);
        String ccp=getCustomizedCcp(query);
      
      //  List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
       String freq = projSelDTO.getFrequency();
          Object[] orderedArgs1 = {projSelDTO.getProjectionId(), freq, "ASSUMPTIONS", "PERIOD", null, ccp};
        List<Object[]> list = CommonLogic.callProcedure("PRC_CFF_RESULTS", orderedArgs1);
        if(list==null){
        System.out.println("list size is null=====================");
        }else{
            System.out.println("===list size======================>>>"+list.size());
        }
        List<ProjectionResultsDTO> projDTOList = getCustomizedProjectionResultsSales(list, projSelDTO);
        LOGGER.info("= = = Ending getContractSalesAndUnits = = =");
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
        Object[] orderedArgs1 = {projSelDTO.getProjectionId(), freq, "ASSUMPTIONS", "PIVOT", fromDate, ccps};
        List<Object[]> gtsList = CommonLogic.callProcedure("PRC_CFF_RESULTS", orderedArgs1);
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        List<ProjectionResultsDTO> resultsList = setPivotValue(gtsList, projSelDTO.getFrequencyDivision(), periodList, projSelDTO, 3, discountsList);
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
            Object[] orderedArgs2 = {projSelDTO.getProjectionId(),freq, "ASSUMPTIONS","PIVOT",fromDate};
            List<Object[]> gtsList = CommonLogic.callProcedure("PRC_CFF_RESULTS", orderedArgs2);
            getCustomizedProjectionPivotTotal(gtsList, discountsList, projSelDTO);
        }
        return projectionTotalList;
    }

    public void getCustomizedProjectionPivotTotal(List<Object[]> list, List<Object[]> discountList, ProjectionSelectionDTO projSelDTO) {
        projectionTotalList.clear();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        
        List<ProjectionResultsDTO> resultsList=setPivotValue(list,frequencyDivision,periodList,projSelDTO, 3,discountList);
        projectionTotalList.addAll(resultsList);
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("group");
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
                newValue = newValue / 100;
            }
            value = FORMAT.format(newValue);
                return value;
    }

    public void getProjectionTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getProjectionTotal = = =");
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        List<Object[]> gtsList = CommonLogic.callProcedure("Prc_cff_results", orderedArgs);
        if (gtsList != null) {
            getCustomizedProjectionTotal(gtsList, projSelDTO);
        }
        LOGGER.info("= = = Ending getProjectionTotal = = =");
    }

     public void getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getCustomizedProjectionTotal = = =");
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
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
        ProjectionResultsDTO returnDiscountPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO returnRPUDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO returnDiscountDolDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO exFactoryCustDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO adjDemandDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO inventoryDetailsDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO perExFactoryCustDTO  = new ProjectionResultsDTO();
        ProjectionResultsDTO perAdjDemandDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO perInventoryDetailsDTO = new ProjectionResultsDTO();
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

        returnDiscountPerDTO.setParent(0);
        returnDiscountPerDTO.setGroup(RETURNS.getConstant());

        returnRPUDTO.setParent(0);
        returnRPUDTO.setGroup(RETURNS.getConstant());

        returnDiscountDolDTO.setParent(0);
        returnDiscountDolDTO.setGroup(RETURNS.getConstant());

        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("group");
        if (list != null && !list.isEmpty()) {
            int col = 5;
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = "";
                  int year=0;
                  if(obj[3]!=null){
                 year = Integer.valueOf(String.valueOf(obj[3]));
                  }
               int period =0;
               if(obj[4]!=null){
                  period = Integer.valueOf(String.valueOf(obj[4]));
               }
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);

                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = "" + obj[1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);                    
                    exFactoryDTO.addStringProperties(column, value);
                    
                    value = "" + obj[55]; 
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    exFactoryCustDTO.addStringProperties(column, value);
                    
                    value = "" + obj[53]; 
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    adjDemandDTO.addStringProperties(column, value);
                    
                    value = "" + obj[57]; 
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    inventoryDetailsDTO.addStringProperties(column, value);
                    
                    value = "" + obj[61];
                    value = getFormattedValue(PER_TWO, value);
                    perExFactoryCustDTO.addStringProperties(column, value);
                    
                    value = "" + obj[59]; 
                    value = getFormattedValue(PER_TWO, value);
                    perAdjDemandDTO.addStringProperties(column, value);
                    
                    value = "" + obj[63];                     
                    value = getFormattedValue(PER_TWO, value);
                    perInventoryDetailsDTO.addStringProperties(column, value);
                    
                  
                     value=getCellValue(obj[5],"Sales");
                     conSaleDTO.addStringProperties(column, value);
//                    if(obj[5]==null){
//                        System.out.println("==Actual contrscat sales null=============");
//                       conSaleDTO.addStringProperties(column, StringUtils.EMPTY);  
//                   }else{
//                    value = "" + obj[5]; //---- col ----- 6
//                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
//                    conSaleDTO.addStringProperties(column, value);
//                   }
                    
                   // value = "" + obj[7];
                  //  value = getFormattedValue(NUM_ZERO, value);
                      value=getCellValue(obj[7],"Units");
                    unitVolDTO.addStringProperties(column, value);
                    
//                    value = "" + obj[9];
//                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                     value=getCellValue(obj[9],"Sales");
                    discountDolDTO.addStringProperties(column, value);
                    
                   // value = "" + obj[11];
                   // value = getFormattedValue(PER_TWO, value);
                    value=getCellValue(obj[11],"Percent");
                    discountPerDTO.addStringProperties(column, value);
                    value = "" + obj[13];
                    value = getFormattedValue(PER_TWO, value);
                    perExFactoryDTO.addStringProperties(column, value);
                    if (projSelDTO.isPpa()) {
                        value = "" + obj[14];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaDolDTO.addStringProperties(column, value);
                        value = "" + obj[col + 12];
                        value = getFormattedValue(PER_TWO, value);
                        ppaPerDTO.addStringProperties(column, value);
                        value = "" + obj[col + 26];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaRPUDTO.addStringProperties(column, value);
                    }
                   // value = "" + obj[27];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value=getCellValue(obj[27],"Sales");
                    netSaleDTO.addStringProperties(column, value);
                  
                    value = "" + obj[29];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    demandDTO.addStringProperties(column, value);
                    value = "" + obj[31];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    inventoryDTO.addStringProperties(column, value);
                    value = "" + obj[33];
                    value = getFormattedValue(PER_TWO, value);
                    perDemandDTO.addStringProperties(column, value);
                    value = "" + obj[35];
                    value = getFormattedValue(PER_TWO, value);
                    perInventoryDTO.addStringProperties(column, value);
                   // value = "" + obj[41];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value=getCellValue(obj[41], "Sales");
                    totalRPUDTO.addStringProperties(column, value);
                    
                    
                   // value = "" + obj[43];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value=getCellValue(obj[43],"Sales");
                    cogsDTO.addStringProperties(column, value);
                   // value = "" + obj[45];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value=getCellValue(obj[45],"Sales");
                    netprofitDTO.addStringProperties(column, value);
                    value = "" + obj[48];
                    value = getFormattedValue(PER_TWO, value);
                    returnDiscountPerDTO.addStringProperties(column, value);
                    value = "" + obj[51];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnRPUDTO.addStringProperties(column, value);
                    value = "" + obj[47];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnDiscountDolDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                
                if (projSelDTO.hasColumn(column)) {
                    String value = "" + obj[2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    exFactoryDTO.addStringProperties(column, value);
                    
                    value = "" + obj[56];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    exFactoryCustDTO.addStringProperties(column, value);
                    
                    value = "" + obj[54];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    adjDemandDTO.addStringProperties(column, value);
                    
                    value = "" + obj[58];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    inventoryDetailsDTO.addStringProperties(column, value);
                    
                    value = "" + obj[62];
                    value = getFormattedValue(PER_TWO, value);
                    perExFactoryCustDTO.addStringProperties(column, value);
                    
                    value = "" + obj[60];
                    value = getFormattedValue(PER_TWO, value);
                    perAdjDemandDTO.addStringProperties(column, value);
                    
                    value = "" + obj[64];
                    value = getFormattedValue(PER_TWO, value);
                    perInventoryDetailsDTO.addStringProperties(column, value);
                    
                    value=getCellValue(obj[6],"Sales");
                    conSaleDTO.addStringProperties(column, value);
                    
//                    if(obj[6]==null){
//                        System.out.println("==projection contrscat sales null=============");
//                      conSaleDTO.addStringProperties(column, StringUtils.EMPTY);   
//                    }else{
//                    value = "" + obj[6];
//                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
//                    conSaleDTO.addStringProperties(column, value);
//                    }
                    
                     value=getCellValue(obj[8],"Units");
                  //  value = "" + obj[8];
                   // value = getFormattedValue(NUM_ZERO, value);
                    unitVolDTO.addStringProperties(column, value);
//                    value = "" + obj[10];
//                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value=getCellValue(obj[10],"Sales");
                    discountDolDTO.addStringProperties(column, value);
                    
                   // value = "" + obj[12];
                   // value = getFormattedValue(PER_TWO, value);
                     value=getCellValue(obj[12],"Percent");
                    discountPerDTO.addStringProperties(column, value);
                    value = "" + obj[14];
                    value = getFormattedValue(PER_TWO, value);
                    perExFactoryDTO.addStringProperties(column, value);
                    if (projSelDTO.isPpa()) {
                        value = "" + obj[15];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaDolDTO.addStringProperties(column, value);
                        value = "" + obj[col + 13];
                        value = getFormattedValue(PER_TWO, value);
                        ppaPerDTO.addStringProperties(column, value);
                        value = "" + obj[col + 27];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaRPUDTO.addStringProperties(column, value);
                    }
                    //value = "" + obj[28];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(obj[28],"Sales");
                    netSaleDTO.addStringProperties(column, value);
                    value = "" + obj[30];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    demandDTO.addStringProperties(column, value);
                    value = "" + obj[32];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    inventoryDTO.addStringProperties(column, value);
                    value = "" + obj[34];
                    value = getFormattedValue(PER_TWO, value);
                    perDemandDTO.addStringProperties(column, value);
                    value = "" + obj[36];
                    value = getFormattedValue(PER_TWO, value);
                    perInventoryDTO.addStringProperties(column, value);
                   // value = "" + obj[42];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value=getCellValue(obj[42], "Sales");
                    totalRPUDTO.addStringProperties(column, value);
                    
                  //  value = "" + obj[44];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(obj[44],"Sales");
                    cogsDTO.addStringProperties(column, value);
                  //  value = "" + obj[46];
                 //   value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                  value=getCellValue(obj[46],"Sales");
                    netprofitDTO.addStringProperties(column, value);
                    value = "" + obj[50];
                    value = getFormattedValue(PER_TWO, value);
                    returnDiscountPerDTO.addStringProperties(column, value);
                    value = "" + obj[52];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnRPUDTO.addStringProperties(column, value);
                    value = "" + obj[49];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnDiscountDolDTO.addStringProperties(column, value);
                    columnList.remove(column);
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
            netSaleDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            cogsDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            netprofitDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            if (projSelDTO.isPpa()) {
                ppaPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
                ppaRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
                ppaDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            }
            returnDiscountPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, "null"));
            returnRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            returnDiscountDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
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
        projDTOList.add(netSaleDTO);
        projDTOList.add(cogsDTO);
        projDTOList.add(netprofitDTO);
        projDTOList.add(returnDiscountPerDTO);
        projDTOList.add(returnRPUDTO);
        projDTOList.add(returnDiscountDolDTO);
        if (projSelDTO.isPpa()) {
            projDTOList.add(ppaPerDTO);
            projDTOList.add(ppaRPUDTO);
            projDTOList.add(ppaDolDTO);
        }
        projectionTotalList.addAll(projDTOList);
        LOGGER.info("= = = Ending getCustomizedProjectionTotal = = =");
    }

    public List<ProjectionResultsDTO> getProjectionTotalDiscounts(ProjectionSelectionDTO projSelDTO, Object[] orderedArgs, String group) {
      
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        if (prjTotalDisDolDtoList.isEmpty() || prjTotalDisPerDtoList.isEmpty() || prjTotalRPUDtoList.isEmpty()) {
            Object[] orderedArgs2 = {orderedArgs[0], orderedArgs[1], orderedArgs[2], null, null ,null, null,"excel"};
            List<Object[]> gtsList = CommonLogic.callProcedure("Prc_cff_projection_results_discount", orderedArgs2);
            projDTOList =getCustomizedDiscountsProjectionTotal(gtsList, projSelDTO,group);
        }
        return projDTOList;
    }

   public List<ProjectionResultsDTO> getCustomizedDiscountsProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO,String group) {
      
       
        ProjectionResultsDTO discountNameDTO=new ProjectionResultsDTO();
        List<ProjectionResultsDTO> discountNameList = new ArrayList<ProjectionResultsDTO>();
        String oldDiscountName = "";
        String newDiscountName = "";
         String column=StringUtils.EMPTY;
         loadMap();
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            newDiscountName = String.valueOf(obj[3]);
            if (oldDiscountName.equals(newDiscountName)) {
                column = StringUtils.EMPTY;
                column=getCommonColumn(projSelDTO,obj);
                String property = column + ACTUALS.getConstant();
               
                if(group.equals("Discount %")){
                 String value=getCellValue(obj[6], "Percent");
                  //  String value = "" + obj[6];
             //   value = getFormattedValue(PER_TWO, value);
                discountNameDTO.addStringProperties(property, value);
                String projProperty = column + PROJECTIONS.getConstant();
              //  value = "" + obj[7];
               // value = getFormattedValue(PER_TWO, value);
                value=getCellValue(obj[7], "Percent");
                discountNameDTO.addStringProperties(projProperty, value);
                 }else if(group.equals("Total RPU")){
               //  String value = "" + obj[8];
                 String value=getCellValue(obj[8], "Sales");
                //value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                discountNameDTO.addStringProperties(property, value);
                String projProperty = column + PROJECTIONS.getConstant();
               // value = "" + obj[9];
               // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                value=getCellValue(obj[9], "Sales");
                discountNameDTO.addStringProperties(projProperty, value);
                 }else if(group.equals("Discount $")){
                //String value = "" + obj[4];
              //  System.out.println("==value====================>>>>"+value);
               // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                 String value=getCellValue(obj[4], "Sales");
                discountNameDTO.addStringProperties(property, value);
                String projProperty = column + PROJECTIONS.getConstant();
               // value = "" + obj[5];
               // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
               value=getCellValue(obj[5], "Sales");
                discountNameDTO.addStringProperties(projProperty, value);
                 }
            } else {
                if (i != 0) {
                    discountNameList.add(discountNameDTO);
                }
                oldDiscountName = String.valueOf(obj[3]);
                discountNameDTO = new ProjectionResultsDTO();
                discountNameDTO.addStringProperties("group", oldDiscountName);
                column = StringUtils.EMPTY;
                column=getCommonColumn(projSelDTO,obj);
                 if(group.equals("Discount %")){
                String property = column + ACTUALS.getConstant();
               // String value = "" + obj[6];
                //value = getFormattedValue(PER_TWO, value);
               String value=getCellValue(obj[6], "Percent");
                discountNameDTO.addStringProperties(property, value);
                String projProperty = column + PROJECTIONS.getConstant();
                //value = "" + obj[7];
               // value = getFormattedValue(PER_TWO, value);
                value=getCellValue(obj[7], "Percent");
                discountNameDTO.addStringProperties(projProperty, value);
                 }            
                  else if(group.equals("Total RPU")){
                String property = column + ACTUALS.getConstant();
               // String value = "" + obj[8];
               // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
               String value=getCellValue(obj[8], "Sales");
                discountNameDTO.addStringProperties(property, value);
                String projProperty = column + PROJECTIONS.getConstant();
               // value = "" + obj[9];
               // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                value=getCellValue(obj[9], "Sales");
                discountNameDTO.addStringProperties(projProperty, value);
                 }else if(group.equals("Discount $")){
                  String property = column + ACTUALS.getConstant();
              //   String value = "" + obj[4];
              String value=getCellValue(obj[4], "Sales");
               // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                discountNameDTO.addStringProperties(property, value);
                String projProperty = column + PROJECTIONS.getConstant();
                //value = "" + obj[5];
               // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
               value=getCellValue(obj[5], "Sales");
                discountNameDTO.addStringProperties(projProperty, value);
                 }
            }
            if (i == list.size() - 1) {
                discountNameList.add(discountNameDTO);
            }
        } 
          return discountNameList;      
    }

    public ProjectionResultsDTO getNetSales(ProjectionSelectionDTO projSelDTO) {
        try {
            LOGGER.info("= = = Inside getNetSales = = = = = =");
            ProjectionResultsDTO netSalesDto = new ProjectionResultsDTO();
            String query = "";
            List<Object> list = null;
            List<ProjectionResultsDTO> projDTOList = null;
            projSelDTO.setSales("SALES");
            query = CommonLogic.getCCPQuery(projSelDTO) + " \n" + getProjectionResultsNetSalesQuery(projSelDTO);
            list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
            projDTOList = getCustomizedProjectionResultsDiscount(list, projSelDTO, false);
            if (projDTOList != null && !projDTOList.isEmpty()) {
                netSalesDto = projDTOList.get(0);
            }
            netSalesDto.setGroup("Net Sales");
            netSalesDto.setParent(0);
            LOGGER.info("= = = Ending getNetSales = = =");
            return netSalesDto;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }



    private List getCustomizedCOGSandNetProfit(List list, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> resultList = new ArrayList<ProjectionResultsDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("group");
        ProjectionResultsDTO cogsDto = new ProjectionResultsDTO();
        ProjectionResultsDTO netprofitDto = new ProjectionResultsDTO();

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

        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = "";
                int year = (obj[0] == null) ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = (obj[1] == null) ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                String commonColumn = common.get(0);
                int col = 2;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = "";
                    value = "" + obj[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    cogsDto.addStringProperties(column, value);
                    value = "" + obj[col + 2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netprofitDto.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = "" + obj[col + 1];
                    value = "" + obj[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    cogsDto.addStringProperties(column, value);
                    value = "" + obj[col + 3];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netprofitDto.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            cogsDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
            netprofitDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
        }
        resultList.add(cogsDto);
        resultList.add(netprofitDto);

        return resultList;
    }

    public List<ProjectionResultsDTO> getProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getProjectionResults = = = start"+start+" offset - "+offset);
        int started = start;
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        String discList = CollectionToString(projSelDTO.getDiscountNoList(), false);
        String freq = "";
        if (projSelDTO.getFrequencyDivision() == 1) {
            freq = "ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            freq = "SEMI-ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 4) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == 12) {
            freq = "MONTHLY";
        }

      
      
       Object[] orderedArgs = {projSelDTO.getProjectionId(), freq, "ASSUMPTIONS"};
            if (projSelDTO.isIsTotal()) {
                if (projSelDTO.isIsProjectionTotal()) {
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
            }
            if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                String salesUnits = projSelDTO.getSalesOrUnit();
                if (projSelDTO.isIsTotal()) {
                    LOGGER.info("= = = Inside Period isTotal method = = =");
                    if (projSelDTO.isIsProjectionTotal()) {
                        LOGGER.info("= = = Inside Period isTotal = = = isIsProjectionTotal method = = =");
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
                        if (started == 2 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO exFactoryCustomerDto = projectionTotalList.get(1);
                                projDTOList.add(exFactoryCustomerDto);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == 3 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO demandDTO = projectionTotalList.get(2);
                                projDTOList.add(demandDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == 4 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO adjustedDemandDTO = projectionTotalList.get(3);
                                projDTOList.add(adjustedDemandDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == 5 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO inventoryWDSummaryDTO = projectionTotalList.get(4);
                                projDTOList.add(inventoryWDSummaryDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == 6 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO inventoryWDDetailsDTO = projectionTotalList.get(5);
                                projDTOList.add(inventoryWDDetailsDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == 7 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO perExfactoryProdDTO = projectionTotalList.get(6);
                                projDTOList.add(perExfactoryProdDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == 8 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO perExfactoryProdDTO = projectionTotalList.get(7);
                                projDTOList.add(perExfactoryProdDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == 9 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO perDemandDTO = projectionTotalList.get(8);
                                projDTOList.add(perDemandDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == 10 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO perAdjDemandDTO = projectionTotalList.get(9);
                                projDTOList.add(perAdjDemandDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == 11 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO perInvWDSumDTO = projectionTotalList.get(10);
                                projDTOList.add(perInvWDSumDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == 12 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                ProjectionResultsDTO perInvWDDetDTO = projectionTotalList.get(11);
                                projDTOList.add(perInvWDDetDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        mayBeAdded+=12;
                        if (neededRecord > 0 && (salesUnits.equals(BOTH) || salesUnits.equals(SALES.getConstant()))) {
                            if (started == 13) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO contractSalesDto = projectionTotalList.get(12);
                                    projDTOList.add(contractSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && (salesUnits.equals(BOTH) || salesUnits.equals(UNITS.getConstant()))) {
                            if ((salesUnits.equals(BOTH) && started == 14) || started == 13) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO unitVolDto = projectionTotalList.get(13);
                                    projDTOList.add(unitVolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains("Discount $") && !projSelDTO.getGroup().contains("Total RPU")) {
                            if ((salesUnits.equals(BOTH) && started == 15) || started == 14) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountPerDto = projectionTotalList.get(14);
                                    projDTOList.add(discountPerDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains("Discount %") && !projSelDTO.getGroup().contains("Discount $")) {
                            if ((salesUnits.equals(BOTH) && started == 16) || started == 15) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO totalRPUDto = projectionTotalList.get(15);
                                    projDTOList.add(totalRPUDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains("Discount %") && !projSelDTO.getGroup().contains("Total RPU")) {
                            if ((salesUnits.equals(BOTH) && started == 17) || started == 16) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountDolDto = projectionTotalList.get(16);
                                    projDTOList.add(discountDolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH) && started == 18) || started == 17) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO netSalesDto = projectionTotalList.get(17);
                                    projDTOList.add(netSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH) && started == 19) || started == 18) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO cogsDto = projectionTotalList.get(18);
                                    projDTOList.add(cogsDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH) && started == 20) || started == 19) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                   
                                    ProjectionResultsDTO netProfitDto = projectionTotalList.get(19);
                                    projDTOList.add(netProfitDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                    } else if (neededRecord > 0) {
                   List<ProjectionResultsDTO> resultList = getQueryForHierarchyExpand(projSelDTO);
                        ProjectionResultsDTO contractSalesDto = null;
                        ProjectionResultsDTO unitVolDto = null;
                        if ((salesUnits.equals(BOTH) && started < 2) || started < 1) {
                            contractSalesDto = resultList.get(0);
                            unitVolDto = resultList.get(1);
                        }
                        if (salesUnits.equals(BOTH) || salesUnits.equals(SALES.getConstant())) {
                            if (started == 0) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    projDTOList.add(contractSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && (salesUnits.equals(BOTH) || salesUnits.equals(UNITS.getConstant()))) {
                            if ((salesUnits.equals(BOTH) && started == 1) || started == 0) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    projDTOList.add(unitVolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains("Discount $") && !projSelDTO.getGroup().contains("Total RPU")) {
                            if ((salesUnits.equals(BOTH) && started == 2) || started == 1) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountPerDtoList =resultList.get(2);
                                    projDTOList.add(discountPerDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains("Discount $") && !projSelDTO.getGroup().contains("Discount %")) {
                            if ((salesUnits.equals(BOTH) && started == 3) || started == 2) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO totalRPUDtoList = resultList.get(3);
                                    projDTOList.add(totalRPUDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains("Discount %") && !projSelDTO.getGroup().contains("Total RPU")) {
                            if ((salesUnits.equals(BOTH) && started == 4) || started == 3) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountDolarDtoList =resultList.get(4);
                                    projDTOList.add(discountDolarDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH) && started == 5) || started == 4) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO netSalesDto = resultList.get(5);
                                    projDTOList.add(netSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH) && started == 6) || started == 5) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO cogsDto = resultList.get(6);
                                    projDTOList.add(cogsDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH) && started == 7) || started == 6) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO netProfitDto = resultList.get(7);
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
                    List<ProjectionResultsDTO> returnsList = getReturns(projSelDTO);
                    if (!projSelDTO.getGroup().contains("Discount $") && !projSelDTO.getGroup().contains("Total RPU")) {
                        List<ProjectionResultsDTO> discountPerDtoList = new ArrayList<ProjectionResultsDTO>();
                            if (projSelDTO.isIsProjectionTotal()) {
                              
                                discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs,"Discount %");
                            } else {
                                discountPerDtoList = getTotalDiscountLevels(projSelDTO, "Discount %");
                            }
                            for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; k++) {
                                if (!projSelDTO.hasNonFetchableIndex("" + k)) {
                                    projDTOList.add(discountPerDtoList.get(k));
                                }
                                started++;
                                neededRecord--;
                            }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                        if (neededRecord > 0) {
                            if (projSelDTO.isPpa()) {
                                if (projSelDTO.isIsProjectionTotal()) {
                                    if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                        if (projectionTotalList.isEmpty()) {
                                            getProjectionTotal(orderedArgs, projSelDTO);
                                        }
                                        projDTOList.add(projectionTotalList.get(17));
                                    }

                                } else {
                                    if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                        ProjectionResultsDTO ppaDTO = getPPAPer(projSelDTO);
                                        projDTOList.add(ppaDTO);
                                    }

                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if (projSelDTO.isIsProjectionTotal()) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO netSalesDto = projectionTotalList.get(14);
                                    projDTOList.add(netSalesDto);
                                }
                            } else {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountPerReturnDTO = returnsList.get(0);
                                    projDTOList.add(discountPerReturnDTO);
                                }
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                    }
                    if (!projSelDTO.getGroup().contains("Discount $") && !projSelDTO.getGroup().contains("Discount %")) {
                        List<ProjectionResultsDTO> discountPerDtoList = new ArrayList<ProjectionResultsDTO>();
                            if (projSelDTO.isIsProjectionTotal()) {
                                discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs,"Total RPU");
                            } else {
                                discountPerDtoList = getTotalDiscountLevels(projSelDTO, "Total RPU");
                            }
                            for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; k++) {
                                if (!projSelDTO.hasNonFetchableIndex("" + k)) {
                                    projDTOList.add(discountPerDtoList.get(k));
                                }
                                started++;
                                neededRecord--;
                            }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                        if (neededRecord > 0) {
                            if (projSelDTO.isPpa()) {
                                if (projSelDTO.isIsProjectionTotal()) {
                                    if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                        if (projectionTotalList.isEmpty()) {
                                            getProjectionTotal(orderedArgs, projSelDTO);
                                        }
                                        projDTOList.add(projectionTotalList.get(18));
                                    }

                                } else {
                                    if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                        ProjectionResultsDTO ppaDTO = getPPARPU(projSelDTO);
                                        projDTOList.add(ppaDTO);
                                    }

                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if (projSelDTO.isIsProjectionTotal()) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO netSalesDto = projectionTotalList.get(15);
                                    projDTOList.add(netSalesDto);
                                }
                            } else {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountPerReturnDTO = returnsList.get(1);
                                    projDTOList.add(discountPerReturnDTO);
                                }
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                    }
                    if (neededRecord > 0 && !projSelDTO.getGroup().contains("Discount %") && !projSelDTO.getGroup().contains("Total RPU")) {
                        List<ProjectionResultsDTO> discountPerDtoList = new ArrayList<ProjectionResultsDTO>();
                            if (projSelDTO.isIsProjectionTotal()) {
                                discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs,"Discount $");
                            } else {
                                discountPerDtoList = getTotalDiscountLevels(projSelDTO,"Discount $");
                            }
                            for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; k++) {
                                if (!projSelDTO.hasNonFetchableIndex("" + k)) {
                                    projDTOList.add(discountPerDtoList.get(k));
                                }
                                started++;
                                neededRecord--;
                            }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                        if (neededRecord > 0) {
                            if (projSelDTO.isPpa()) {
                                if (projSelDTO.isIsProjectionTotal()) {
                                    if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                        if (projectionTotalList.isEmpty()) {
                                            getProjectionTotal(orderedArgs, projSelDTO);
                                        }
                                        projDTOList.add(projectionTotalList.get(19));
                                    }

                                } else {
                                    if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                        ProjectionResultsDTO ppaDTO = getPPADollar(projSelDTO);
                                        projDTOList.add(ppaDTO);
                                    }

                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if (projSelDTO.isIsProjectionTotal()) {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO netSalesDto = projectionTotalList.get(16);
                                    projDTOList.add(netSalesDto);
                                }
                            } else {
                                if (!projSelDTO.hasNonFetchableIndex("" + started)) {
                                    ProjectionResultsDTO discountPerReturnDTO = returnsList.get(2);
                                    projDTOList.add(discountPerReturnDTO);
                                }
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                    }
                    LOGGER.info("= = = Ending Period = = = neededRecord > 0 =started =" + started + "= neededRecord =" + neededRecord + "= maybeadded= " + mayBeAdded);
                }

            } else if (neededRecord > 0) {
               
                int mayBeAddedRecord = started - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                if (mayBeAddedRecord < projSelDTO.getPeriodList().size()) {
                    List<ProjectionResultsDTO> projectionDtoList = new ArrayList<ProjectionResultsDTO>();
                   
                    if (projSelDTO.isIsProjectionTotal()) {
                        // Object[] orderedArgs1 = {projSelDTO.getProjectionId(), freq, "ASSUMPTIONS"};
                         Object[] orderedArgs1 = {projSelDTO.getProjectionId(), projSelDTO.getFrequency(), "ASSUMPTIONS", "PIVOT", null, null, null, "excel"};
                          List<Object[]> discountsList = CommonLogic.callProcedure("Prc_cff_projection_results_discount", orderedArgs1);
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
                        Object[] orderedArgs2 = {projSelDTO.getProjectionId(), projSelDTO.getFrequency(), "ASSUMPTIONS", "PIVOT", null, ccps, null, "excel"};
                        List<Object[]> discountsList = CommonLogic.callProcedure("Prc_cff_projection_results_discount", orderedArgs2);
                        projectionDtoList = getProjectionPivot(projSelDTO, discountsList);
                        
                        
                        
                    }
                    for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; k++) {
                        if (!projSelDTO.hasNonFetchableIndex("" + k)) {
                            projDTOList.add(projectionDtoList.get(k));
                        }
                        started++;
                        neededRecord--;
                    }
                }
                mayBeAdded += projSelDTO.getPeriodList().size();
            }
        if (!(projSelDTO.isIsCustomHierarchy() && projSelDTO.getCustomId() == 0)) {
            if (neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsFilter()) {
                    int mayBeAddedRecord = started - mayBeAdded;
                    if (mayBeAddedRecord < 0) {
                        mayBeAddedRecord = 0;
                    }
                    projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                    List<ProjectionResultsDTO> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, started, projSelDTO);
                    projDTOList.addAll(nextLevelValueList);
                }
            }
        LOGGER.info("= = = Ending getProjectionResults = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getConfiguredProjectionResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
        try {
            List<ProjectionResultsDTO> resultList = new ArrayList<>();
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
                resultList = configureLevels(start, offset, start, projSelDTO);
            }
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    public List<ProjectionResultsDTO> configureLevels(int start, int offset, int started, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        List<ProjectionResultsDTO> resultList = new ArrayList<ProjectionResultsDTO>();
        if (neededRecord > 0) {
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true);
            for (int i = 0; i < levelList.size() && neededRecord > 0; i++) {
                if (!projSelDTO.hasNonFetchableIndex("" + (started + i))) {
                    Leveldto levelDto = levelList.get(i);
                    ProjectionResultsDTO dto = new ProjectionResultsDTO();
                    dto.setLevelNo(levelDto.getLevelNo());
                    dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                    dto.setParentNode(levelDto.getParentNode());
                    dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setLevelValue(levelDto.getRelationshipLevelValue());
                    if (i == (levelList.size() - 1)) {
                        dto.setLevelValue(levelDto.getRelationshipLevelValue());
                    }
                    dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                    dto.setHierarchyNo(levelDto.getHierarchyNo());
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
                    resultList.add(dto);
                }
                started++;
                neededRecord--;
            }
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
            count += configureLevelsCount(projSelDTO);
        }
        return count;
    }

    public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelsCount) {
        int count = 0;
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                if (projSelDTO.isIsTotal()) {
                    count = count + 7;
                    if (projSelDTO.isIsProjectionTotal()) {
                        count = count + 13;
                    }
                    if (projSelDTO.getSalesOrUnit().equals(BOTH)) {
                        count++;
                    }                    
                }else{
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
        } else {
            if (projSelDTO.isIsTotal() && isLevelsCount && !projSelDTO.isIsFilter()) {
                    projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                    int levelCount = configureLevelsCount(projSelDTO);
                    count = count + levelCount;
                    projSelDTO.setLevelCount(levelCount);
            }
        }
        return count;
    }

    public int configureLevelsCount(ProjectionSelectionDTO projSelDTO) {
        return CommonLogic.getLevelListCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid());
    }

    public String getProjectionResultsDiscountsQuery(ProjectionSelectionDTO projSelDTO, String orderBy) {

        String selectClause = " select ";
        String whereClause = "";
        String groupBy = " I.\"YEAR\" \n";

        String customQuery = "";
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";

        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS,\n ";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == 12) {
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
                + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID \n"
                + periodFilter
                + whereClause + " group by " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as ACTUAL_SALES, \n"
                + " sum(A.ACTUAL_PROJECTION_SALES) as PROJECTION_SALES \n"
                + " from ST_NM_ACTUAL_DISCOUNT A, \n "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES , \n"
                + " sum(A.PROJECTION_SALES) as PROJECTION_SALES \n"
                + " from ST_NM_DISCOUNT_PROJECTION A, \n "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1) + " and HISTORY.DISCOUNTS=FUTURE.DISCOUNTS";
        String finalSelectClause = "select " + list.get(0) + " Isnull(HISTORY.DISCOUNTS, FUTURE.DISCOUNTS) as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES ";
        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond + orderBy;

        return customQuery;
    }

    public String getProjectionResultsPPAQuery(ProjectionSelectionDTO projSelDTO) {

        String selectClause = " select ";
        String whereClause = "";
        String groupBy = " I.\"YEAR\" \n";
        String customQuery = "";
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.\"MONTH\"";
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
                + " group by " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_DISCOUNT_DOLLAR) as ACTUAL_SALES,\n"
                + " sum(A.ACTUAL_PROJECTION_SALES) as PROJECTION_SALES "
                + ",sum(A.ACTUAL_PROJECTION_RATE)AS PPA_ACTUAL_RPU\n"
                + " from ST_NM_ACTUAL_PPA A,\n "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_DISCOUNT_DOLLAR) as PROJECTION_SALES ,sum(A.PROJECTION_RATE)AS PPA_PROJECTED_RPU \n"
                + " from ST_NM_PPA_PROJECTION A,\n "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "'PPA Discount' as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES"
                + "              ,ISNULL(HISTORY.PPA_ACTUAL_RPU,0)AS PPA_ACTUAL_RPU\n"
                + "              ,ISNULL(FUTURE.PPA_PROJECTED_RPU,0)AS PPA_PROJECTED_RPU ";

        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsPPARPUQuery(ProjectionSelectionDTO projSelDTO) {

        String selectClause = " select ";
        String whereClause = "";
        String groupBy = " I.\"YEAR\" \n";
        String customQuery = "";
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.\"MONTH\"";
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
                + " group by " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_DISCOUNT_DOLLAR) as ACTUAL_SALES,\n"
                + " sum(A.ACTUAL_PROJECTION_SALES) as PROJECTION_SALES, sum(A.ACTUAL_PROJECTION_RATE)AS PPA_ACTUAL_RPU \n"
                + " from ST_NM_ACTUAL_PPA A,\n "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_DISCOUNT_DOLLAR) as PROJECTION_SALES,sum(A.PROJECTION_RATE)AS PPA_PROJECTED_RPU \n"
                + " from ST_NM_PPA_PROJECTION A,\n "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "'PPA Discount' as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES"
                + ",ISNULL(HISTORY.PPA_ACTUAL_RPU,0)AS PPA_ACTUAL_RPU\n"
                + "              ,ISNULL(FUTURE.PPA_PROJECTED_RPU,0)AS PPA_PROJECTED_RPU ";

        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsSalesQuery(ProjectionSelectionDTO projSelDTO) {

        String selectClause = " select ";
        String whereClause = "";
        String groupBy = " I.\"YEAR\"\n";
        String customQuery = "";
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customSql = " PROJECTION_DETAILS E , \n"
                + " \"PERIOD\" I, \n"
                + " @CCP CCP "
                + "where A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
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
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as SALES_ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as SALES_PROJECTION_SALES,"
                + "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,\n Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS  ";

        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;

        return customQuery;
    }

    public String getProjectionResultsPPAPerQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("PPA", "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'PPA Discount' as DISCOUNTS,\n";
        selectClause += " ACTUAL_RATE=Isnull(PPA.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n"
                + " PROJECTION_RATE=Isnull(PPA.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n";
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + ppaQuery + "\n) PPA LEFT JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsPPARPU(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("PPA", "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'PPA Discount' as DISCOUNTS\n";
        selectClause += " ,PPA_ACTUAL_RPU=(Isnull(PPA.PPA_ACTUAL_RPU / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0))\n"
                + "       ,PPA_PROJECTED_RPU=(Isnull(PPA.PPA_PROJECTED_RPU / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0))";
        String ppaQuery = getProjectionResultsPPARPUQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + ppaQuery + "\n) PPA LEFT JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsTotalDiscountDolQuery(ProjectionSelectionDTO projSelDTO) {
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String customQuery = "";
        if (projSelDTO.isPpa()) {
            String selectClause = " select ";

            List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "PPA", "on");
            selectClause += list.get(0);
            String finalWhereCond = list.get(1);
            selectClause += "'Total Discount $' as DISCOUNTS, \n";
            selectClause += " ACTUAL_SALES=Isnull(TODIS.ACTUAL_SALES, 0)+Isnull(PPA.ACTUAL_SALES, 0),\n"
                    + "  PROJECTION_SALES=Isnull(TODIS.PROJECTION_SALES, 0)+Isnull(PPA.PROJECTION_SALES, 0) \n";

            String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
            customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond;

        } else {
            customQuery = totalDiscountQuery;
        }
        return customQuery;
    }

    public String getProjectionResultsDiscountsPerQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "TODIS.DISCOUNTS, \n";
        selectClause += " ACTUAL_RATE=Isnull(TODIS.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n"
                + " PROJECTION_RATE=Isnull(TODIS.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS LEFT JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + "\n order by TODIS.DISCOUNTS";
        return customQuery;
    }

    public String getProjectionResultsTotalDiscountPerQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Total Discount %' as DISCOUNTS, \n";
        selectClause += " ACTUAL_RATE=Isnull((Isnull(TODIS.ACTUAL_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.ACTUAL_SALES, 0)";
        }
        selectClause += ") / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n PROJECTION_RATE=Isnull((Isnull(TODIS.PROJECTION_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.PROJECTION_SALES, 0)";
        }
        selectClause += ") / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        return customQuery;
    }

    public String getCostQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + "CREATE TABLE #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID INT\n"
                + "     , CONTRACT_MASTER_SID INT\n"
                + "     , ITEM_MASTER_SID INT\n"
                + "     , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "     , PROJECTION_MASTER_SID INT\n"
                + "     )\n"
                + "INSERT INTO #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID\n"
                + "     , CONTRACT_MASTER_SID\n"
                + "     , ITEM_MASTER_SID\n"
                + "     , PROJECTION_DETAILS_SID\n"
                + "     , PROJECTION_MASTER_SID\n"
                + "     )\n"
                + "SELECT C.COMPANY_MASTER_SID\n"
                + "     , C.CONTRACT_MASTER_SID\n"
                + "     , C.ITEM_MASTER_SID\n"
                + "     , PD.PROJECTION_DETAILS_SID\n"
                + "     , pm.PROJECTION_MASTER_SID\n"
                + "FROM \n"
                + "      CCP_DETAILS C\n"
                + "     , PROJECTION_DETAILS PD\n"
                + "     , PROJECTION_MASTER PM\n"
                + "WHERE PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "     AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "     AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "	 and EXISTS (SELECT 1 FROM \n"
                + "	 @CCP CCP WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + "INSERT INTO @ITEMID\n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM\n"
                + "WHERE EXISTS (\n"
                + "            SELECT 1\n"
                + "            FROM #TEMP_CCP A\n"
                + "            WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                   AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "            ) ";
        selectClause += " select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "COGS_ACTUAL = ISNULL(SALE.COGS_ACTUAL, 0)\n"
                + ", COGS_PROJECTED = ISNULL(SALE.COGS_PROJECTED, 0) \n"
                + ", NET_PROFIT_ACTUAL = ((Isnull(SALE.SALES_ACTUAL_SALES, 0) - (Isnull(TODIS.ACTUAL_SALES, 0)+ ISNULL(PPA.ACTUAL_SALES, 0))) - ISNULL(SALE.COGS_ACTUAL, 0))\n"
                + "  , NET_PROFIT_PROJECTED = ((Isnull(SALE.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0)+ ISNULL(PPA.PROJECTION_SALES, 0))) - ISNULL(SALE.COGS_PROJECTED, 0))";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + cogsQuery + "\n) SALE \n" + finalWhereCond;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        return customQuery;
    }

    public String getNetProfitQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + "CREATE TABLE #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID INT\n"
                + "     , CONTRACT_MASTER_SID INT\n"
                + "     , ITEM_MASTER_SID INT\n"
                + "     , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "     , PROJECTION_MASTER_SID INT\n"
                + "     )\n"
                + "INSERT INTO #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID\n"
                + "     , CONTRACT_MASTER_SID\n"
                + "     , ITEM_MASTER_SID\n"
                + "     , PROJECTION_DETAILS_SID\n"
                + "     , PROJECTION_MASTER_SID\n"
                + "     )\n"
                + "SELECT C.COMPANY_MASTER_SID\n"
                + "     , C.CONTRACT_MASTER_SID\n"
                + "     , C.ITEM_MASTER_SID\n"
                + "     , PD.PROJECTION_DETAILS_SID\n"
                + "     , pm.PROJECTION_MASTER_SID\n"
                + "FROM \n"
                + "      CCP_DETAILS C\n"
                + "     , PROJECTION_DETAILS PD\n"
                + "     , PROJECTION_MASTER PM\n"
                + "WHERE PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "     AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "     AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "	 and EXISTS (SELECT 1 FROM \n"
                + "	 @CCP CCP WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + "INSERT INTO @ITEMID\n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM\n"
                + "WHERE EXISTS (\n"
                + "            SELECT 1\n"
                + "            FROM #TEMP_CCP A\n"
                + "            WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                   AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "            ) ";
        selectClause += " select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Cost Of Goods Sold' as COGS, \n";
        selectClause += " COGS_ACTUAL = (ISNULL(SALE.ACTUAL_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n"
                + ", COGS_PROJECTED = (ISNULL(SALE.PROJECTION_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + "\n";
        customQuery += " FULL OUTER JOIN (" + cogsQuery;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        return customQuery;
    }

    public String getProjectionResultsPivotQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + "CREATE TABLE #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID INT\n"
                + "     , CONTRACT_MASTER_SID INT\n"
                + "     , ITEM_MASTER_SID INT\n"
                + "     , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "     , PROJECTION_MASTER_SID INT\n"
                + "     )\n"
                + "INSERT INTO #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID\n"
                + "     , CONTRACT_MASTER_SID\n"
                + "     , ITEM_MASTER_SID\n"
                + "     , PROJECTION_DETAILS_SID\n"
                + "     , PROJECTION_MASTER_SID\n"
                + "     )\n"
                + "SELECT C.COMPANY_MASTER_SID\n"
                + "     , C.CONTRACT_MASTER_SID\n"
                + "     , C.ITEM_MASTER_SID\n"
                + "     , PD.PROJECTION_DETAILS_SID\n"
                + "     , pm.PROJECTION_MASTER_SID\n"
                + "FROM \n"
                + "      CCP_DETAILS C\n"
                + "     , PROJECTION_DETAILS PD\n"
                + "     , PROJECTION_MASTER PM\n"
                + "WHERE PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "     AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "     AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "	 and EXISTS (SELECT 1 FROM \n"
                + "	 @CCP CCP WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + "INSERT INTO @ITEMID\n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM\n"
                + "WHERE EXISTS (\n"
                + "            SELECT 1\n"
                + "            FROM #TEMP_CCP A\n"
                + "            WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                   AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "            ) ";
        selectClause += " IF Object_id('TEMPDB..#TEMP_CCPD') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCPD\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID INT\n"
                + "       , CONTRACT_MASTER_SID INT\n"
                + "       , ITEM_MASTER_SID INT\n"
                + "       , PROJECTION_DETAILS_SID INT\n"
                + "       , PROJECTION_MASTER_SID INT\n"
                + "       )\n"
                + "\n"
                + "INSERT INTO #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID\n"
                + "       , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + "       )\n"
                + "SELECT DISTINCT COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID\n"
                + "       , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + "FROM (\n"
                + "       SELECT COMPANY_MASTER_SID\n"
                + "              , T_CCP.CONTRACT_MASTER_SID\n"
                + "              , T_CCP.ITEM_MASTER_SID\n"
                + "              , PROJECTION_DETAILS_SID\n"
                + "              , PROJECTION_MASTER_SID\n"
                + "              , RS_ID\n"
                + "              , RS_TYPE\n"
                + "       FROM #TEMP_CCP T_CCP\n"
                + "       INNER JOIN RS_CONTRACT RS\n"
                + "              ON T_CCP.CONTRACT_MASTER_SID = RS.CONTRACT_MASTER_SID\n"
                + "       INNER JOIN RS_CONTRACT_DETAILS RSC\n"
                + "              ON RS.RS_CONTRACT_SID = RSC.RS_CONTRACT_SID\n"
                + "                     AND T_CCP.ITEM_MASTER_SID = RSC.ITEM_MASTER_SID\n"
                + "       WHERE EXISTS (\n"
                + "                     SELECT 1\n"
                + "                     FROM CFP_CONTRACT CF\n"
                + "                     INNER JOIN CFP_CONTRACT_DETAILS CFD\n"
                + "                           ON CF.CFP_CONTRACT_SID = CFD.CFP_CONTRACT_SID\n"
                + "                                  AND T_CCP.COMPANY_MASTER_SID = CFD.COMPANY_MASTER_SID\n"
                + "                                  AND RS.CFP_CONTRACT_SID = CF.CFP_CONTRACT_SID\n"
                + "                                  AND CF.CONTRACT_MASTER_SID = T_CCP.CONTRACT_MASTER_SID\n"
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
                + "       )\n"
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
                + "CREATE TABLE #TEMP_RETURNS (\n"
                + "       ITEM_MASTER_SID INT\n"
                + "       , RETURNS_DETAILS_SID INT\n"
                + "       , PERIOD_SID INT\n"
                + "       , ACTUAL_RATE NUMERIC(22, 6)\n"
                + "       , PROJECTED_RATE NUMERIC(22, 6)\n"
                + "       )\n"
                + "\n"
                + "WHILE (@I <= @COUNT)\n"
                + "BEGIN\n"
                + "       SET @ITEM = (\n"
                + "                     SELECT ITEM_MASTER_SID\n"
                + "                     FROM #ITEM\n"
                + "                     WHERE id = @I\n"
                + "                     );\n"
                + "\n"
                + "       WITH ITEM_PROJ_DETAILS\n"
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
                + "                           , (CREATED_DATE)\n"
                + "                     ) CS(LASTEST_DATE)\n"
                + "              WHERE SAVE_FLAG = 1\n"
                + "                     AND ITEM_MASTER_SID = @ITEM\n"
                + "              )\n"
                + "       INSERT INTO #TEMP_RETURNS (\n"
                + "              ITEM_MASTER_SID\n"
                + "              , RETURNS_DETAILS_SID\n"
                + "              , PERIOD_SID\n"
                + "              , ACTUAL_RATE\n"
                + "              , PROJECTED_RATE\n"
                + "              )\n"
                + "       SELECT @ITEM AS ITEM_MASTER_SID\n"
                + "              , COALESCE(R_ACTUALS.RETURNS_DETAILS_SID, R_PROJ.RETURNS_DETAILS_SID) AS RETURNS_DETAILS_SID\n"
                + "              , COALESCE(R_ACTUALS.PERIOD_SID, R_PROJ.PERIOD_SID) AS PERIOD_SID\n"
                + "              , R_ACTUALS.ACTUAL_RETURN_PERCENT\n"
                + "              , R_PROJ.PROJECTED_RETURN_PERCENT\n"
                + "       FROM (\n"
                + "              SELECT RETURNS_DETAILS_SID\n"
                + "                     , PERIOD_SID\n"
                + "                     , ACTUAL_RETURN_PERCENT\n"
                + "              FROM RETURNS_ACTUALS NAP\n"
                + "              WHERE EXISTS (\n"
                + "                           SELECT 1\n"
                + "                           FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                         AND RD.RETURNS_DETAILS_SID = NAP.RETURNS_DETAILS_SID\n"
                + "                           WHERE IMPD.RN = 1\n"
                + "                           )\n"
                + "              ) R_ACTUALS\n"
                + "       FULL JOIN (\n"
                + "              SELECT RETURNS_DETAILS_SID\n"
                + "                     , PERIOD_SID\n"
                + "                     , PROJECTED_RETURN_PERCENT\n"
                + "              FROM RETURNS_PROJ_DETAILS NPP\n"
                + "              WHERE EXISTS (\n"
                + "                           SELECT 1\n"
                + "                           FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                         AND RD.RETURNS_DETAILS_SID = NPP.RETURNS_DETAILS_SID\n"
                + "                           WHERE IMPD.RN = 1\n"
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
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        String orderBy = list.get(3);
        if (projSelDTO.isPpa()) {
            ppa_actuals = "+Isnull(PPA.ACTUAL_SALES, 0)";
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
                + ", NET_ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-(Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + "))  \n"
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
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + " \n";
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        customQuery += " order by " + orderBy;
        return customQuery;
    }

    public String getProjectionResultsReturnsQuery(ProjectionSelectionDTO projSelDTO) {
        String selectPeriod = "";
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectPeriod = ", I.QUARTER AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectPeriod = ", I.SEMI_ANNUAL AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = ",'0' AS  PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectPeriod = ", I.MONTH AS PERIODS";
        }

        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customQuery = "SELECT Isnull(HISTORY.YEARS, FUTURE.YEARS) AS YEARS\n"
                + "              , Isnull(HISTORY.PERIODS, FUTURE.PERIODS) AS PERIODS\n"
                + "              , Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "              , Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "              , Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "              , Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "              , Isnull(HISTORY.COGS_ACTUAL, 0) AS COGS_ACTUAL\n"
                + "              , Isnull(FUTURE.COGS_PROJECTED, 0) AS COGS_PROJECTED\n"
                + "              , Isnull(HISTORY.RETURNS_ACTUAL, 0) AS RETURNS_ACTUAL\n"
                + "              , Isnull(FUTURE.RETURNS_PROJECTED, 0) AS RETURNS_PROJECTED\n"
                + ", RETURNS_ACTUAL_PERCENT = (Isnull(HISTORY.ACTUAL_RATE, 0))\n"
                + "       , RETURNS_PROJECTED_PERCENT = (Isnull(FUTURE.PROJECTED_RATE, 0))"
                + "       FROM (\n"
                + "              SELECT YEARS\n"
                + "                     , PERIODS\n"
                + "                     , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                     , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                     , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     , SUM(COGS_ACTUAL) AS COGS_ACTUAL\n"
                + "                     , ACTUAL_RATE = Avg(A.ACTUAL_RATE)\n"
                + "                     , RETURNS_ACTUAL = Sum(A.ACTUAL_SALES) * Avg(A.ACTUAL_RATE)\n"
                + "                     FROM\n"
                + "                     (SELECT I.\"YEAR\" AS YEARS\n"
                + "                     " + selectPeriod + "\n"
                + "                     , A.ACTUAL_SALES\n"
                + "                     , A.HISTORY_PROJECTION_SALES\n"
                + "                     , A.ACTUAL_UNITS\n"
                + "                     , A.HISTORY_PROJECTION_UNITS\n"
                + "                     , COGS_ACTUAL = (ISNULL(A.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                + "                           , TR.ACTUAL_RATE\n"
                + "              FROM ST_NM_ACTUAL_SALES A\n"
                + "                     INNER JOIN PROJECTION_DETAILS E\n"
                + "                           ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "                     INNER JOIN PERIOD I\n"
                + "                           ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     INNER JOIN @CCP CCP\n"
                + "                           ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                     INNER JOIN CCP_DETAILS CC\n"
                + "                           ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                                  AND A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U\n"
                + "                           ON U.ITEM_MASTER_SID = CC.ITEM_MASTER_SID\n"
                + "                                  AND A.PERIOD_SID = U.PERIOD_SID\n"
                + "                     LEFT JOIN (\n"
                + "                           SELECT A.ITEM_MASTER_sID\n"
                + "                                  , A.PERIOD_sID\n"
                + "                                  , A.ACTUAL_RATE\n"
                + "                                  , A.PROJECTED_RATE\n"
                + "                                  , CCPD.PROJECTION_DETAILS_SID\n"
                + "                           FROM #TEMP_RETURNS A\n"
                + "                           INNER JOIN #TEMP_CCPD CCPD\n"
                + "                                  ON A.ITEM_MASTER_SID = CCPD.ITEM_MASTER_SID\n"
                + "                           ) TR\n"
                + "                           ON TR.ITEM_MASTER_sID = CC.ITEM_MASTER_sID\n"
                + "                                  AND TR.PERIOD_SID = A.PERIOD_SID\n"
                + "                                  AND A.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID\n"
                + "                     WHERE A.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "                           AND A.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + "                           AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                           AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID " + periodFilter + "\n"
                + "                     ) A\n"
                + "              GROUP BY YEARS\n"
                + "                     , PERIODS\n"
                + "              ) HISTORY\n"
                + "       FULL JOIN (\n"
                + "              SELECT YEARS\n"
                + "                     , PERIODS\n"
                + "                     , 0 AS SALES_ACTUAL_SALES\n"
                + "                     , sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , 0 AS ACTUAL_UNITS\n"
                + "                     , sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     , SUM(COGS_PROJECTED) AS COGS_PROJECTED\n"
                + "                     , PROJECTED_RATE = Avg(P.PROJECTED_RATE)\n"
                + "                     , RETURNS_PROJECTED = Sum(P.PROJECTION_SALES) * Avg(PROJECTED_RATE)\n"
                + "              FROM (\n"
                + "                     SELECT I.\"YEAR\" AS YEARS\n"
                + "                           " + selectPeriod + "\n"
                + "                           , A.PROJECTION_SALES\n"
                + "                           , A.PROJECTION_UNITS\n"
                + "                           , COGS_PROJECTED = (ISNULL(A.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                + "                           , TR.PROJECTED_RATE\n"
                + "                     FROM ST_NM_SALES_PROJECTION A\n"
                + "                     INNER JOIN PROJECTION_DETAILS E\n"
                + "                           ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "                     INNER JOIN PERIOD I\n"
                + "                           ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     INNER JOIN @CCP CCP\n"
                + "                           ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                     INNER JOIN CCP_DETAILS CC\n"
                + "                           ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                                  AND A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U\n"
                + "                           ON U.ITEM_MASTER_SID = CC.ITEM_MASTER_SID\n"
                + "                                  AND A.PERIOD_SID = U.PERIOD_SID\n"
                + "                     LEFT JOIN (\n"
                + "                           SELECT A.ITEM_MASTER_sID\n"
                + "                                  , A.PERIOD_sID\n"
                + "                                  , A.ACTUAL_RATE\n"
                + "                                  , A.PROJECTED_RATE\n"
                + "                                  , CCPD.PROJECTION_DETAILS_SID\n"
                + "                           FROM #TEMP_RETURNS A\n"
                + "                           INNER JOIN #TEMP_CCPD CCPD\n"
                + "                                  ON A.ITEM_MASTER_SID = CCPD.ITEM_MASTER_SID\n"
                + "                           ) TR\n"
                + "                           ON TR.ITEM_MASTER_sID = CC.ITEM_MASTER_sID\n"
                + "                                  AND TR.PERIOD_SID = A.PERIOD_SID\n"
                + "                                  AND A.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID\n"
                + "                     WHERE A.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "                           AND A.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + "                           AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                           AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID " + periodFilter + "\n"
                + "                     ) P\n"
                + "              GROUP BY YEARS\n"
                + "                     , PERIODS\n"
                + "              ) FUTURE\n"
                + "              ON HISTORY.YEARS = FUTURE.YEARS\n"
                + "                     AND HISTORY.PERIODS = FUTURE.PERIODS";
        return customQuery;
    }

    public String getProjectionResultsCOGSQuery(ProjectionSelectionDTO projSelDTO) {
        String selectPeriod = "";
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectPeriod = ", I.QUARTER AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectPeriod = ", I.SEMI_ANNUAL AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = ",'0' AS  PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectPeriod = ", I.MONTH AS PERIODS";
        }

        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customQuery = "SELECT Isnull(HISTORY.YEARS, FUTURE.YEARS) AS YEARS\n"
                + "              , Isnull(HISTORY.PERIODS, FUTURE.PERIODS) AS PERIODS\n"
                + "              , Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "              , Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "              , Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "              , Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "              ,Isnull(HISTORY.COGS_ACTUAL, 0) AS COGS_ACTUAL\n"
                + "              ,Isnull(FUTURE.COGS_PROJECTED, 0) AS COGS_PROJECTED\n"
                + "       FROM (\n"
                + "              SELECT  YEARS\n"
                + "                      ,  PERIODS \n"
                + "                     , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                     , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                     , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     ,SUM(COGS_ACTUAL)AS COGS_ACTUAL\n"
                + "                     FROM\n"
                + "                     (SELECT I.\"YEAR\" AS YEARS\n"
                + "                     " + selectPeriod + "\n"
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
                + "                     ,  PERIODS\n"
                + "              ) HISTORY\n"
                + "       FULL JOIN (\n"
                + "              SELECT  YEARS\n"
                + "                     ,  PERIODS\n"
                + "                     ,0 AS SALES_ACTUAL_SALES\n"
                + "                     , sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     ,0 AS ACTUAL_UNITS\n"
                + "                     , sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     ,SUM(COGS_PROJECTED) AS COGS_PROJECTED\n"
                + "                     FROM\n"
                + "                     (SELECT I.\"YEAR\" AS YEARS\n"
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
                + "                     AND A.PERIOD_SID = I.PERIOD_SID\n" + periodFilter + ")P\n"
                + "              GROUP BY  YEARS\n"
                + "                     ,  PERIODS\n"
                + "              ) FUTURE\n"
                + "              ON HISTORY.YEARS = FUTURE.YEARS\n"
                + " AND HISTORY.PERIODS = FUTURE.PERIODS";
        return customQuery;

    }

    public String getProjectionResultsTotalDiscountsQuery(ProjectionSelectionDTO projSelDTO) {
        String selectPeriod = "";
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectPeriod = ", I.QUARTER AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectPeriod = ", I.SEMI_ANNUAL AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = ",'0' AS  PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 12) {
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
                + "       FROM (\n"
                + "              SELECT  YEARS\n"
                + "                      ,  PERIODS \n"
                + "                     , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                     , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                     , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     ,ACTUAL_RATE = Avg(A.ACTUAL_RATE),\n"
                + "                      RETURNS_ACTUAL = Sum(A.ACTUAL_SALES) * Avg(ACTUAL_RATE)\n"
                + "                     FROM\n"
                + "                     (SELECT I.\"YEAR\" AS YEARS\n"
                + "                     " + selectPeriod + "\n"
                + "                     , A.ACTUAL_SALES\n"
                + "                     , A.HISTORY_PROJECTION_SALES\n"
                + "                     , A.ACTUAL_UNITS\n"
                + "                     , A.HISTORY_PROJECTION_UNITS\n"
                + "                     , TR.ACTUAL_RATE\n"
                + "              FROM ST_NM_ACTUAL_SALES A\n"
                + "              INNER JOIN PROJECTION_DETAILS E\n"
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
                + "              AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n" + periodFilter + "\n"
                + "       ) A\n"
                + "GROUP BY YEARS\n"
                + "       , PERIODS       "
                + "              ) HISTORY\n"
                + "       FULL JOIN (\n"
                + "              SELECT  YEARS,\n"
                + "                             PERIODS,\n"
                + "                             Sum(P.SALES_ACTUAL_SALES)AS SALES_ACTUAL_SALES,\n"
                + "                             Sum(P.PROJECTION_SALES)  AS SALES_PROJECTION_SALES,\n"
                + "                             Sum(ACTUAL_UNITS)        AS ACTUAL_UNITS,\n"
                + "                             Sum(P.PROJECTION_UNITS)  AS PROJECTION_UNITS,\n"
                + "                             PROJECTED_RATE = Avg(P.PROJECTED_RATE),\n"
                + "                             RETURNS_PROJECTED = Sum(P.PROJECTION_SALES) * Avg(PROJECTED_RATE)"
                + "                     FROM\n"
                + "                     (SELECT I.\"YEAR\" AS YEARS\n"
                + "                     " + selectPeriod + "\n"
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
                + "              ) FUTURE\n"
                + "              ON HISTORY.YEARS = FUTURE.YEARS\n"
                + " AND HISTORY.PERIODS = FUTURE.PERIODS";
        return customQuery;

    }

    public String getCOGSQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = " select ";
        String customQuery = "";
        String ppa_actuals = "";
        String ppa_projection = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        String orderBy = list.get(3);
        if (projSelDTO.isPpa()) {
            ppa_actuals = "+Isnull(PPA.ACTUAL_SALES, 0)";
            ppa_projection = "+Isnull(PPA.PROJECTION_SALES, 0)";
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
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + " \n";
        customQuery += " FULL OUTER JOIN (" + cogsQuery;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        customQuery += " order by " + orderBy;
        return customQuery;
    }

    public String getProjectionResultsDiscountsPivotQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        String orderBy = list.get(2);
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

        String selectClause = " select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Total RPU' as DISCOUNTS, \n";
        selectClause += " ACTUAL_RPU=ISNULL((ISNULL(TODIS.ACTUAL_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.ACTUAL_SALES, 0)";
        }
        selectClause += ") / NULLIF(SALE.ACTUAL_UNITS, 0), 0) ,\n PROJECTION_RPU=Isnull((Isnull(TODIS.PROJECTION_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.PROJECTION_SALES, 0)";
        }
        selectClause += ") / NULLIF(SALE.PROJECTION_UNITS, 0), 0) \n";
        String totalDiscountQuery = getProjectionResultsDiscountsRPUQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        return customQuery;
    }

    public String getProjectionResultsDiscountsRPUQuery(ProjectionSelectionDTO projSelDTO, String orderBy) {

        String selectClause = " select ";
        String whereClause = "";
        String groupBy = " I.\"YEAR\" \n";

        String customQuery = "";
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";

        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS,\n ";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.\"MONTH\"";
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
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1) + " and HISTORY.DISCOUNTS=FUTURE.DISCOUNTS";
        String finalSelectClause = "select " + list.get(0) + " Isnull(HISTORY.DISCOUNTS, FUTURE.DISCOUNTS) as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES ";
        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond + orderBy;

        return customQuery;
    }

    public String getProjectionResultsDiscountsRPUQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "TODIS.DISCOUNTS, \n";
        selectClause += " ACTUAL_RATE=Isnull(TODIS.ACTUAL_SALES / NULLIF(SALE.ACTUAL_UNITS, 0), 0) ,\n"
                + " PROJECTION_RATE=Isnull(TODIS.PROJECTION_SALES / NULLIF(SALE.PROJECTION_UNITS, 0), 0) \n";
        String totalDiscountQuery = getProjectionResultsDiscountsRPUQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS LEFT JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + "\n order by TODIS.DISCOUNTS";
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

    private String getProjectionResultsTotalDiscount(ProjectionSelectionDTO projSelDTO) {

        String selectClause = "IF Object_id('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCP;\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCP (\n"
                + "       COMPANY_MASTER_SID INT\n"
                + "       , CONTRACT_MASTER_SID INT\n"
                + "       , ITEM_MASTER_SID INT\n"
                + "       , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "       , PROJECTION_MASTER_SID INT\n"
                + "       )\n"
                + "\n"
                + "INSERT INTO #TEMP_CCP (\n"
                + "       COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID\n"
                + "       , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + "       )\n"
                + "SELECT C.COMPANY_MASTER_SID\n"
                + "       , C.CONTRACT_MASTER_SID\n"
                + "       , C.ITEM_MASTER_SID\n"
                + "       , PD.PROJECTION_DETAILS_SID\n"
                + "       , pm.PROJECTION_MASTER_SID\n"
                + "FROM @CCP CCP\n"
                + "       , CCP_DETAILS C\n"
                + "       , PROJECTION_DETAILS PD\n"
                + "       , PROJECTION_MASTER PM\n"
                + "WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "       AND PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "       AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "       AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "\n"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + "\n"
                + "INSERT INTO @ITEMID\n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM\n"
                + "WHERE EXISTS (\n"
                + "              SELECT 1\n"
                + "              FROM #TEMP_CCP A\n"
                + "              WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                     AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "              )\n"
                + "\n"
                + "IF Object_id('TEMPDB..#TEMP_CCPD') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCPD\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID INT\n"
                + "       , CONTRACT_MASTER_SID INT\n"
                + "       , ITEM_MASTER_SID INT\n"
                + "       , PROJECTION_DETAILS_SID INT\n"
                + "       , PROJECTION_MASTER_SID INT\n"
                + "       )\n"
                + "\n"
                + "INSERT INTO #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID\n"
                + "       , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + "       )\n"
                + "SELECT DISTINCT COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID\n"
                + "       , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + "FROM (\n"
                + "       SELECT COMPANY_MASTER_SID\n"
                + "              , T_CCP.CONTRACT_MASTER_SID\n"
                + "              , T_CCP.ITEM_MASTER_SID\n"
                + "              , PROJECTION_DETAILS_SID\n"
                + "              , PROJECTION_MASTER_SID\n"
                + "              , RS_ID\n"
                + "              , RS_TYPE\n"
                + "       FROM #TEMP_CCP T_CCP\n"
                + "       INNER JOIN RS_CONTRACT RS\n"
                + "              ON T_CCP.CONTRACT_MASTER_SID = RS.CONTRACT_MASTER_SID\n"
                + "       INNER JOIN RS_CONTRACT_DETAILS RSC\n"
                + "              ON RS.RS_CONTRACT_SID = RSC.RS_CONTRACT_SID\n"
                + "                     AND T_CCP.ITEM_MASTER_SID = RSC.ITEM_MASTER_SID\n"
                + "       WHERE EXISTS (\n"
                + "                     SELECT 1\n"
                + "                     FROM CFP_CONTRACT CF\n"
                + "                     INNER JOIN CFP_CONTRACT_DETAILS CFD\n"
                + "                           ON CF.CFP_CONTRACT_SID = CFD.CFP_CONTRACT_SID\n"
                + "                                  AND T_CCP.COMPANY_MASTER_SID = CFD.COMPANY_MASTER_SID\n"
                + "                                  AND RS.CFP_CONTRACT_SID = CF.CFP_CONTRACT_SID\n"
                + "                                  AND CF.CONTRACT_MASTER_SID = T_CCP.CONTRACT_MASTER_SID\n"
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
                + "       )\n"
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
                + "CREATE TABLE #TEMP_RETURNS (\n"
                + "       ITEM_MASTER_SID INT\n"
                + "       , RETURNS_DETAILS_SID INT\n"
                + "       , PERIOD_SID INT\n"
                + "       , ACTUAL_RATE NUMERIC(22, 6)\n"
                + "       , PROJECTED_RATE NUMERIC(22, 6)\n"
                + "       )\n"
                + "\n"
                + "WHILE (@I <= @COUNT)\n"
                + "BEGIN\n"
                + "       SET @ITEM = (\n"
                + "                     SELECT ITEM_MASTER_SID\n"
                + "                     FROM #ITEM\n"
                + "                     WHERE id = @I\n"
                + "                     );\n"
                + "\n"
                + "       WITH ITEM_PROJ_DETAILS\n"
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
                + "                           , (CREATED_DATE)\n"
                + "                     ) CS(LASTEST_DATE)\n"
                + "              WHERE SAVE_FLAG = 1\n"
                + "                     AND ITEM_MASTER_SID = @ITEM\n"
                + "              )\n"
                + "       INSERT INTO #TEMP_RETURNS (\n"
                + "              ITEM_MASTER_SID\n"
                + "              , RETURNS_DETAILS_SID\n"
                + "              , PERIOD_SID\n"
                + "              , ACTUAL_RATE\n"
                + "              , PROJECTED_RATE\n"
                + "              )\n"
                + "       SELECT @ITEM AS ITEM_MASTER_SID\n"
                + "              , COALESCE(R_ACTUALS.RETURNS_DETAILS_SID, R_PROJ.RETURNS_DETAILS_SID) AS RETURNS_DETAILS_SID\n"
                + "              , COALESCE(R_ACTUALS.PERIOD_SID, R_PROJ.PERIOD_SID) AS PERIOD_SID\n"
                + "              , R_ACTUALS.ACTUAL_RETURN_PERCENT\n"
                + "              , R_PROJ.PROJECTED_RETURN_PERCENT\n"
                + "       FROM (\n"
                + "              SELECT RETURNS_DETAILS_SID\n"
                + "                     , PERIOD_SID\n"
                + "                     , ACTUAL_RETURN_PERCENT\n"
                + "              FROM RETURNS_ACTUALS NAP\n"
                + "              WHERE EXISTS (\n"
                + "                           SELECT 1\n"
                + "                           FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "AND RD.RETURNS_DETAILS_SID = NAP.RETURNS_DETAILS_SID \n"
                + "                           WHERE IMPD.RN = 1\n"
                + "                           )\n"
                + "              ) R_ACTUALS\n"
                + "       FULL JOIN (\n"
                + "              SELECT RETURNS_DETAILS_SID\n"
                + "                     , PERIOD_SID\n"
                + "                     , PROJECTED_RETURN_PERCENT\n"
                + "              FROM RETURNS_PROJ_DETAILS NPP\n"
                + "              WHERE EXISTS (\n"
                + "                           SELECT 1\n"
                + "                           FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "AND RD.RETURNS_DETAILS_SID = NPP.RETURNS_DETAILS_SID \n"
                + "                           WHERE IMPD.RN = 1\n"
                + "                           )\n"
                + "              ) R_PROJ\n"
                + "              ON R_ACTUALS.RETURNS_DETAILS_SID = R_PROJ.RETURNS_DETAILS_SID\n"
                + "                     AND R_ACTUALS.PERIOD_SID = R_PROJ.PERIOD_SID\n"
                + "\n"
                + "       SET @I = @I + 1\n"
                + "END";
        selectClause += " SELECT ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Total Discount %'                  AS DISCOUNTS,\n"
                + "          ACTUAL_RATE = ISNULL(( ISNULL(TODIS.ACTUAL_SALES, 0) ";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.ACTUAL_SALES, 0)";
        }
        selectClause += "                                 + ISNULL(SALE.RETURNS_ACTUAL_PERCENT, 0) ) / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n"
                + "          PROJECTION_RATE = ISNULL(( ISNULL(TODIS.PROJECTION_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.PROJECTION_SALES, 0)";
        }
        selectClause += "                                     + ISNULL(SALE.RETURNS_PROJECTED_PERCENT, 0) ) / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100,\n"
                + "          'Total Discount $'                  AS DISCOUNTS,\n"
                + "          ACTUAL_AMOUNT = ( ISNULL(TODIS.ACTUAL_SALES, 0)\n";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.ACTUAL_SALES, 0)";
        }
        selectClause += "                            + ISNULL(SALE.RETURNS_ACTUAL_PERCENT, 0) ),\n"
                + "          PROJECTION_AMOUNT =( ISNULL(TODIS.PROJECTION_SALES, 0)\n";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.ACTUAL_SALES, 0)";
        }
        selectClause += "                               + ISNULL(SALE.RETURNS_PROJECTED_PERCENT, 0)),\n"
                + "          'RPU'                               AS DISCOUNTS,\n"
                + "          ACTUAL_RPU = ( ISNULL(SALE.RETURNS_ACTUAL_AMOUNT";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.PPA_ACTUAL_RPU, 0)";
        }
        selectClause += " +ISNULL(TODIS.ACTUAL_SALES, 0) / NULLIF(SALE.ACTUAL_UNITS, 0), 0) ),\n"
                + "          PROJECTION_RPU =( ISNULL(SALE.RETURNS_PROJECTED_AMOUNT ";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.PPA_PROJECTED_RPU, 0)";
        }
        selectClause += " +ISNULL(TODIS.PROJECTION_SALES, 0) / NULLIF(SALE.PROJECTION_UNITS, 0), 0) )";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String cogsQuery = getProjectionResultsTotalDiscountsQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + cogsQuery + "\n) SALE \n" + finalWhereCond + "\n";
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        return customQuery;
    }

    public List<ProjectionResultsDTO> getReturns(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getReturns = = =");

        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<Object> list = null;
        String query = "DECLARE @FROM_DATE DATE\n"
                + "     , @STARTFROM DATE\n"
                + "     , @PROJECTION_DATE DATE\n"
                + "     , @START_PERIOD_SID INT\n"
                + "     , @END_PERIOD_SID INT\n"
                + "SELECT TOP 1 @STARTFROM = DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, - 3, GETDATE())), 0)\n"
                + "     , @PROJECTION_DATE = DATEADD(M, DATEDIFF(M, 0, DATEADD(DAY, - 1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))), 0)\n"
                + "FROM PROJECTION_MASTER\n"
                + "WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n";

        query += "\n" + CommonLogic.getCCPQuery(projSelDTO) + " \n";
        query += getReturnsQuery(projSelDTO);
        list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        projDTOList = getCustomizedProjectionResultsReturns(list, projSelDTO, false);
        LOGGER.info("= = = Ending getReturns = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionResultsReturns(List<Object> list, ProjectionSelectionDTO projSelDTO, boolean isPPA) {
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        List<ProjectionResultsDTO> projDTO = new ArrayList<ProjectionResultsDTO>();
        columnList.remove("group");
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
                int col = 3;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = "";
                    value = "" + obj[col + 4];
                    value = getFormattedValue(PER_TWO, value);
                    returnPerDTO.addStringProperties(column, value);
                    value = "" + obj[col + 2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnRPUDTO.addStringProperties(column, value);
                    value = "" + obj[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnDolDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = "" + obj[col + 5];
                    value = getFormattedValue(PER_TWO, value);
                    returnPerDTO.addStringProperties(column, value);
                    value = "" + obj[col + 3];
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
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectPeriod = ", I.QUARTER AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectPeriod = ", I.SEMI_ANNUAL AS PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = ",'0' AS  PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectPeriod = ", I.MONTH AS PERIODS";
        }

        String query = "IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCP;\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCP (\n"
                + "       COMPANY_MASTER_SID INT\n"
                + "       , CONTRACT_MASTER_SID INT\n"
                + "       , ITEM_MASTER_SID INT\n"
                + "       , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "       , PROJECTION_MASTER_SID INT\n"
                + "       )\n"
                + "\n"
                + "INSERT INTO #TEMP_CCP (\n"
                + "       COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID\n"
                + "       , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + "       )\n"
                + "SELECT C.COMPANY_MASTER_SID\n"
                + "       , C.CONTRACT_MASTER_SID\n"
                + "       , C.ITEM_MASTER_SID\n"
                + "       , PD.PROJECTION_DETAILS_SID\n"
                + "       , pm.PROJECTION_MASTER_SID\n"
                + "FROM CCP_DETAILS C\n"
                + "       , PROJECTION_DETAILS PD\n"
                + "       , PROJECTION_MASTER PM\n"
                + "WHERE PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "       AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "       AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "       AND EXISTS (\n"
                + "              SELECT 1\n"
                + "              FROM @CCP CCP\n"
                + "              WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "              )\n"
                + "\n"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + "\n"
                + "INSERT INTO @ITEMID\n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM\n"
                + "WHERE EXISTS (\n"
                + "              SELECT 1\n"
                + "              FROM #TEMP_CCP A\n"
                + "              WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                     AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "              )\n"
                + "\n"
                + "IF Object_id('TEMPDB..#TEMP_CCPD') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCPD\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID INT\n"
                + "       , CONTRACT_MASTER_SID INT\n"
                + "       , ITEM_MASTER_SID INT\n"
                + "       , PROJECTION_DETAILS_SID INT\n"
                + "       , PROJECTION_MASTER_SID INT\n"
                + "       )\n"
                + "\n"
                + "INSERT INTO #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID\n"
                + "       , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + "       )\n"
                + "SELECT DISTINCT COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID\n"
                + "       , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + "FROM (\n"
                + "       SELECT COMPANY_MASTER_SID\n"
                + "              , T_CCP.CONTRACT_MASTER_SID\n"
                + "              , T_CCP.ITEM_MASTER_SID\n"
                + "              , PROJECTION_DETAILS_SID\n"
                + "              , PROJECTION_MASTER_SID\n"
                + "              , RS_ID\n"
                + "              , RS_TYPE\n"
                + "       FROM #TEMP_CCP T_CCP\n"
                + "       INNER JOIN RS_CONTRACT RS\n"
                + "              ON T_CCP.CONTRACT_MASTER_SID = RS.CONTRACT_MASTER_SID\n"
                + "       INNER JOIN RS_CONTRACT_DETAILS RSC\n"
                + "              ON RS.RS_CONTRACT_SID = RSC.RS_CONTRACT_SID\n"
                + "                     AND T_CCP.ITEM_MASTER_SID = RSC.ITEM_MASTER_SID\n"
                + "       WHERE EXISTS (\n"
                + "                     SELECT 1\n"
                + "                     FROM CFP_CONTRACT CF\n"
                + "                     INNER JOIN CFP_CONTRACT_DETAILS CFD\n"
                + "                           ON CF.CFP_CONTRACT_SID = CFD.CFP_CONTRACT_SID\n"
                + "                                  AND T_CCP.COMPANY_MASTER_SID = CFD.COMPANY_MASTER_SID\n"
                + "                                  AND RS.CFP_CONTRACT_SID = CF.CFP_CONTRACT_SID\n"
                + "                                  AND CF.CONTRACT_MASTER_SID = T_CCP.CONTRACT_MASTER_SID\n"
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
                + "       )\n"
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
                + "CREATE TABLE #TEMP_RETURNS (\n"
                + "       ITEM_MASTER_SID INT\n"
                + "       , RETURNS_DETAILS_SID INT\n"
                + "       , PERIOD_SID INT\n"
                + "       , ACTUAL_RATE NUMERIC(22, 6)\n"
                + "       , PROJECTED_RATE NUMERIC(22, 6)\n"
                + "       )\n"
                + "\n"
                + "WHILE (@I <= @COUNT)\n"
                + "BEGIN\n"
                + "       SET @ITEM = (\n"
                + "                     SELECT ITEM_MASTER_SID\n"
                + "                     FROM #ITEM\n"
                + "                     WHERE id = @I\n"
                + "                     );\n"
                + "\n"
                + "       WITH ITEM_PROJ_DETAILS\n"
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
                + "                           , (CREATED_DATE)\n"
                + "                     ) CS(LASTEST_DATE)\n"
                + "              WHERE SAVE_FLAG = 1\n"
                + "                     AND ITEM_MASTER_SID = @ITEM\n"
                + "              )\n"
                + "       INSERT INTO #TEMP_RETURNS (\n"
                + "              ITEM_MASTER_SID\n"
                + "              , RETURNS_DETAILS_SID\n"
                + "              , PERIOD_SID\n"
                + "              , ACTUAL_RATE\n"
                + "              , PROJECTED_RATE\n"
                + "              )\n"
                + "       SELECT @ITEM AS ITEM_MASTER_SID\n"
                + "              , COALESCE(R_ACTUALS.RETURNS_DETAILS_SID, R_PROJ.RETURNS_DETAILS_SID) AS RETURNS_DETAILS_SID\n"
                + "              , COALESCE(R_ACTUALS.PERIOD_SID, R_PROJ.PERIOD_SID) AS PERIOD_SID\n"
                + "              , R_ACTUALS.ACTUAL_RETURN_PERCENT\n"
                + "              , R_PROJ.PROJECTED_RETURN_PERCENT\n"
                + "       FROM (\n"
                + "              SELECT RETURNS_DETAILS_SID\n"
                + "                     , PERIOD_SID\n"
                + "                     , ACTUAL_RETURN_PERCENT\n"
                + "              FROM RETURNS_ACTUALS NAP\n"
                + "              WHERE EXISTS (\n"
                + "                           SELECT 1\n"
                + "                           FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                         AND RD.RETURNS_DETAILS_SID = NAP.RETURNS_DETAILS_SID\n"
                + "                           WHERE IMPD.RN = 1\n"
                + "                           )\n"
                + "              ) R_ACTUALS\n"
                + "       FULL JOIN (\n"
                + "              SELECT RETURNS_DETAILS_SID\n"
                + "                     , PERIOD_SID\n"
                + "                     , PROJECTED_RETURN_PERCENT\n"
                + "              FROM RETURNS_PROJ_DETAILS NPP\n"
                + "              WHERE EXISTS (\n"
                + "                           SELECT 1\n"
                + "                           FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                         AND RD.RETURNS_DETAILS_SID = NPP.RETURNS_DETAILS_SID\n"
                + "                           WHERE IMPD.RN = 1\n"
                + "                           )\n"
                + "              ) R_PROJ\n"
                + "              ON R_ACTUALS.RETURNS_DETAILS_SID = R_PROJ.RETURNS_DETAILS_SID\n"
                + "                     AND R_ACTUALS.PERIOD_SID = R_PROJ.PERIOD_SID\n"
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
                + "              , Isnull(HISTORY.PERIODS, FUTURE.PERIODS) AS PERIODS\n"
                + "              , Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "              , Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "              , Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "              , Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "              , Isnull(HISTORY.RETURNS_ACTUAL, 0) AS RETURNS_ACTUAL\n"
                + "              , Isnull(FUTURE.RETURNS_PROJECTED, 0) AS RETURNS_PROJECTED\n"
                + "              , Isnull(HISTORY.ACTUAL_RATE, 0) AS RETURNS_ACTUAL_RATE\n"
                + "              , Isnull(FUTURE.PROJECTED_RATE, 0) AS RETURNS_PROJECTED_RATE\n"
                + "       FROM (\n"
                + "              SELECT YEARS\n"
                + "                     , PERIODS\n"
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
                + "                     INNER JOIN PROJECTION_DETAILS E\n"
                + "                           ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "                     INNER JOIN PERIOD I\n"
                + "                           ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     INNER JOIN @CCP CCP\n"
                + "                           ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                     INNER JOIN CCP_DETAILS CC\n"
                + "                           ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                                  AND A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     LEFT JOIN (\n"
                + "                           SELECT A.ITEM_MASTER_sID\n"
                + "                                  , A.PERIOD_sID\n"
                + "                                  , A.ACTUAL_RATE\n"
                + "                                  , A.PROJECTED_RATE\n"
                + "                                  , CCPD.PROJECTION_DETAILS_SID\n"
                + "                           FROM #TEMP_RETURNS A\n"
                + "                           INNER JOIN #TEMP_CCPD CCPD\n"
                + "                                  ON A.ITEM_MASTER_SID = CCPD.ITEM_MASTER_SID\n"
                + "                           ) TR\n"
                + "                           ON TR.ITEM_MASTER_sID = CC.ITEM_MASTER_sID\n"
                + "                                  AND TR.PERIOD_SID = A.PERIOD_SID\n"
                + "                                  AND A.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID\n"
                + "                     WHERE A.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "                           AND A.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + "                           AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                           AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n" + CommonLogic.getPeriodRestrictionQuery(projSelDTO) + "\n"
                + "                     ) A\n"
                + "              GROUP BY YEARS\n"
                + "                     , PERIODS\n"
                + "              ) HISTORY\n"
                + "       FULL JOIN (\n"
                + "              SELECT YEARS\n"
                + "                     , PERIODS\n"
                + "                     , 0 AS SALES_ACTUAL_SALES\n"
                + "                     , sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , 0 AS ACTUAL_UNITS\n"
                + "                     , sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     , PROJECTED_RATE = Avg(P.PROJECTED_RATE)\n"
                + "                     , RETURNS_PROJECTED = Sum(P.PROJECTION_SALES) * Avg(PROJECTED_RATE)\n"
                + "              FROM (\n"
                + "                     SELECT I.\"YEAR\" AS YEARS\n"
                + "                           " + selectPeriod + "\n"
                + "                           , A.PROJECTION_SALES\n"
                + "                           , A.PROJECTION_UNITS\n"
                + "                           , TR.PROJECTED_RATE\n"
                + "                     FROM ST_NM_SALES_PROJECTION A\n"
                + "                     INNER JOIN PROJECTION_DETAILS E\n"
                + "                           ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "                     INNER JOIN PERIOD I\n"
                + "                           ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     INNER JOIN @CCP CCP\n"
                + "                           ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                     INNER JOIN CCP_DETAILS CC\n"
                + "                           ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                                  AND A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     LEFT JOIN (\n"
                + "                           SELECT A.ITEM_MASTER_sID\n"
                + "                                  , A.PERIOD_sID\n"
                + "                                  , A.ACTUAL_RATE\n"
                + "                                  , A.PROJECTED_RATE\n"
                + "                                  , CCPD.PROJECTION_DETAILS_SID\n"
                + "                           FROM #TEMP_RETURNS A\n"
                + "                           INNER JOIN #TEMP_CCPD CCPD\n"
                + "                                  ON A.ITEM_MASTER_SID = CCPD.ITEM_MASTER_SID\n"
                + "                           ) TR\n"
                + "                           ON TR.ITEM_MASTER_sID = CC.ITEM_MASTER_sID\n"
                + "                                  AND TR.PERIOD_SID = A.PERIOD_SID\n"
                + "                                  AND A.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID\n"
                + "                     WHERE A.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "                           AND A.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + "                           AND E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                           AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n" + CommonLogic.getPeriodRestrictionQuery(projSelDTO) + "\n"
                + "                     ) P\n"
                + "              GROUP BY YEARS\n"
                + "                     , PERIODS\n"
                + "              ) FUTURE\n"
                + "              ON HISTORY.YEARS = FUTURE.YEARS\n"
                + "                     AND HISTORY.PERIODS = FUTURE.PERIODS\n"
                + "       ) SALE\n"
                + "       ON TODIS.YEARS = SALE.YEARS\n"
                + "              AND TODIS.PERIODS = SALE.PERIODS";

        return query;
    }

    public String getProjectionResultsPivotReturnQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + "CREATE TABLE #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID INT\n"
                + "     , CONTRACT_MASTER_SID INT\n"
                + "     , ITEM_MASTER_SID INT\n"
                + "     , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "     , PROJECTION_MASTER_SID INT\n"
                + "     )\n"
                + "INSERT INTO #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID\n"
                + "     , CONTRACT_MASTER_SID\n"
                + "     , ITEM_MASTER_SID\n"
                + "     , PROJECTION_DETAILS_SID\n"
                + "     , PROJECTION_MASTER_SID\n"
                + "     )\n"
                + "SELECT C.COMPANY_MASTER_SID\n"
                + "     , C.CONTRACT_MASTER_SID\n"
                + "     , C.ITEM_MASTER_SID\n"
                + "     , PD.PROJECTION_DETAILS_SID\n"
                + "     , pm.PROJECTION_MASTER_SID\n"
                + "FROM \n"
                + "      CCP_DETAILS C\n"
                + "     , PROJECTION_DETAILS PD\n"
                + "     , PROJECTION_MASTER PM\n"
                + "WHERE PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "     AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "     AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "	 and EXISTS (SELECT 1 FROM \n"
                + "	 @CCP CCP WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + "INSERT INTO @ITEMID\n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM\n"
                + "WHERE EXISTS (\n"
                + "            SELECT 1\n"
                + "            FROM #TEMP_CCP A\n"
                + "            WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                   AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "            ) ";
        selectClause += " IF Object_id('TEMPDB..#TEMP_CCPD') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCPD\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID INT\n"
                + "       , CONTRACT_MASTER_SID INT\n"
                + "       , ITEM_MASTER_SID INT\n"
                + "       , PROJECTION_DETAILS_SID INT\n"
                + "       , PROJECTION_MASTER_SID INT\n"
                + "       )\n"
                + "\n"
                + "INSERT INTO #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID\n"
                + "       , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + "       )\n"
                + "SELECT DISTINCT COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID\n"
                + "       , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + "FROM (\n"
                + "       SELECT COMPANY_MASTER_SID\n"
                + "              , T_CCP.CONTRACT_MASTER_SID\n"
                + "              , T_CCP.ITEM_MASTER_SID\n"
                + "              , PROJECTION_DETAILS_SID\n"
                + "              , PROJECTION_MASTER_SID\n"
                + "              , RS_ID\n"
                + "              , RS_TYPE\n"
                + "       FROM #TEMP_CCP T_CCP\n"
                + "       INNER JOIN RS_CONTRACT RS\n"
                + "              ON T_CCP.CONTRACT_MASTER_SID = RS.CONTRACT_MASTER_SID\n"
                + "       INNER JOIN RS_CONTRACT_DETAILS RSC\n"
                + "              ON RS.RS_CONTRACT_SID = RSC.RS_CONTRACT_SID\n"
                + "                     AND T_CCP.ITEM_MASTER_SID = RSC.ITEM_MASTER_SID\n"
                + "       WHERE EXISTS (\n"
                + "                     SELECT 1\n"
                + "                     FROM CFP_CONTRACT CF\n"
                + "                     INNER JOIN CFP_CONTRACT_DETAILS CFD\n"
                + "                           ON CF.CFP_CONTRACT_SID = CFD.CFP_CONTRACT_SID\n"
                + "                                  AND T_CCP.COMPANY_MASTER_SID = CFD.COMPANY_MASTER_SID\n"
                + "                                  AND RS.CFP_CONTRACT_SID = CF.CFP_CONTRACT_SID\n"
                + "                                  AND CF.CONTRACT_MASTER_SID = T_CCP.CONTRACT_MASTER_SID\n"
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
                + "       )\n"
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
                + "CREATE TABLE #TEMP_RETURNS (\n"
                + "       ITEM_MASTER_SID INT\n"
                + "       , RETURNS_DETAILS_SID INT\n"
                + "       , PERIOD_SID INT\n"
                + "       , ACTUAL_RATE NUMERIC(22, 6)\n"
                + "       , PROJECTED_RATE NUMERIC(22, 6)\n"
                + "       )\n"
                + "\n"
                + "WHILE (@I <= @COUNT)\n"
                + "BEGIN\n"
                + "       SET @ITEM = (\n"
                + "                     SELECT ITEM_MASTER_SID\n"
                + "                     FROM #ITEM\n"
                + "                     WHERE id = @I\n"
                + "                     );\n"
                + "\n"
                + "       WITH ITEM_PROJ_DETAILS\n"
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
                + "                           , (CREATED_DATE)\n"
                + "                     ) CS(LASTEST_DATE)\n"
                + "              WHERE SAVE_FLAG = 1\n"
                + "                     AND ITEM_MASTER_SID = @ITEM\n"
                + "              )\n"
                + "       INSERT INTO #TEMP_RETURNS (\n"
                + "              ITEM_MASTER_SID\n"
                + "              , RETURNS_DETAILS_SID\n"
                + "              , PERIOD_SID\n"
                + "              , ACTUAL_RATE\n"
                + "              , PROJECTED_RATE\n"
                + "              )\n"
                + "       SELECT @ITEM AS ITEM_MASTER_SID\n"
                + "              , COALESCE(R_ACTUALS.RETURNS_DETAILS_SID, R_PROJ.RETURNS_DETAILS_SID) AS RETURNS_DETAILS_SID\n"
                + "              , COALESCE(R_ACTUALS.PERIOD_SID, R_PROJ.PERIOD_SID) AS PERIOD_SID\n"
                + "              , R_ACTUALS.ACTUAL_RETURN_PERCENT\n"
                + "              , R_PROJ.PROJECTED_RETURN_PERCENT\n"
                + "       FROM (\n"
                + "              SELECT RETURNS_DETAILS_SID\n"
                + "                     , PERIOD_SID\n"
                + "                     , ACTUAL_RETURN_PERCENT\n"
                + "              FROM RETURNS_ACTUALS NAP\n"
                + "              WHERE EXISTS (\n"
                + "                           SELECT 1\n"
                + "                           FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                         AND RD.RETURNS_DETAILS_SID = NAP.RETURNS_DETAILS_SID\n"
                + "                           WHERE IMPD.RN = 1\n"
                + "                           )\n"
                + "              ) R_ACTUALS\n"
                + "       FULL JOIN (\n"
                + "              SELECT RETURNS_DETAILS_SID\n"
                + "                     , PERIOD_SID\n"
                + "                     , PROJECTED_RETURN_PERCENT\n"
                + "              FROM RETURNS_PROJ_DETAILS NPP\n"
                + "              WHERE EXISTS (\n"
                + "                           SELECT 1\n"
                + "                           FROM RETURNS_DETAILS RD\n"
                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
                + "                                         AND RD.RETURNS_DETAILS_SID = NPP.RETURNS_DETAILS_SID\n"
                + "                           WHERE IMPD.RN = 1\n"
                + "                           )\n"
                + "              ) R_PROJ\n"
                + "              ON R_ACTUALS.RETURNS_DETAILS_SID = R_PROJ.RETURNS_DETAILS_SID\n"
                + "                     AND R_ACTUALS.PERIOD_SID = R_PROJ.PERIOD_SID\n"
                + "\n"
                + "       SET @I = @I + 1\n"
                + "END";

        selectClause += "\n select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "on");
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
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + " \n";
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
        String selectClause = " select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "PPA", "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Net Sales' as NETSALES, \n";
        selectClause += " ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-(Isnull(TODIS.ACTUAL_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.ACTUAL_SALES, 0)";
        }
        selectClause += ")),\n PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-(Isnull(TODIS.PROJECTION_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.PROJECTION_SALES, 0)";
        }
        selectClause += ")) \n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, "");
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        return customQuery;
    }
   
    
   
       private String getQueryForExpand(ProjectionSelectionDTO projSelDTO)
   {
      
       String freq = projSelDTO.getFrequency();
       if("Quarterly".equals(freq)){
           freq="QUARTER";
       }else if("Semi-Annually".equals(freq)){
           freq="SEMI_ANNUAL";
       }else if("Annually".equals(freq)){
           freq="YEAR";
       }else if("Monthly".equals(freq)){
           freq="MONTH";
       }
       
       
       
       String query = "DECLARE @ACT_END_PERIOD_SID    INT,\n"
               + "        @ACT_START_PERIOD_SID  INT,\n"
               + "        @CFF_MASTER_SID        INT = " + projSelDTO.getProjectionId() + ",\n"
               + "        @PROJ_END_PERIOD_SID   INT,\n"
               + "        @PROJ_START_PERIOD_SID INT\n"
               + "\n"
               + "SELECT @ACT_START_PERIOD_SID = MAX(IIF(F.ACTUAL_START_DATE = PERIOD_DATE, PERIOD_SID, NULL)),\n"
               + "       @ACT_END_PERIOD_SID = MAX(IIF(F.ACTUAL_END_DATE = PERIOD_DATE, PERIOD_SID, NULL)),\n"
               + "       @PROJ_START_PERIOD_SID = MAX(IIF(CAST(F.PROJECTION_START_DATE AS DATE) = PERIOD_DATE, PERIOD_SID, NULL)),\n"
               + "       @PROJ_END_PERIOD_SID = MAX(IIF(DATEADD(DD, 1, EOMONTH(F.PROJECTION_END_DATE, -1)) = PERIOD_DATE, PERIOD_SID, NULL))\n"
               + "FROM   [DBO].[UDF_NA_PROJ_DATES]('COMMERCIAL') F\n"
               + "       INNER JOIN PERIOD P\n"
               + "               ON P.PERIOD_DATE IN ( F.ACTUAL_END_DATE, F.ACTUAL_START_DATE, DATEADD(DD, 1, EOMONTH(F.PROJECTION_END_DATE, -1)), CAST(F.PROJECTION_START_DATE AS DATE) );\n"
               + "\n"
               + "DECLARE @RETURNS_ITEM [DBO].[UDT_ITEM]\n"
               + "\n"
               + "INSERT INTO @RETURNS_ITEM\n"
               + "SELECT DISTINCT ITEM_MASTER_SID\n"
               + "FROM   CCP_DETAILS CCP\n"
               + "WHERE  EXISTS (SELECT 1\n"
               + "               FROM   CFF_DETAILS CD\n"
               + "                      JOIN PROJECTION_DETAILS PD\n"
               + "                        ON CD.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID\n"
               + "                           AND CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
               + "                      JOIN PROJECTION_MASTER PM\n"
               + "                        ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID\n"
               + "               WHERE  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
               + "                      AND CFF_MASTER_SID = @CFF_MASTER_SID\n"
               + "                      AND PM.FORECASTING_TYPE = 'NON MANDATED'\n"
               + "                      AND EXISTS (SELECT 1\n"
               + "                                  FROM   @CCP C\n"
               + "                                  WHERE  C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID))\n"
               + "\n"
               + "DECLARE @COUNT INT\n"
               + "\n"
               + "IF OBJECT_ID('TEMPDB..#ITEM') IS NOT NULL\n"
               + "  DROP TABLE #ITEM\n"
               + "\n"
               + "CREATE TABLE #ITEM\n"
               + "  (\n"
               + "     ID              INT IDENTITY(1, 1),\n"
               + "     ITEM_MASTER_SID INT\n"
               + "  )\n"
               + "\n"
               + "INSERT INTO #ITEM\n"
               + "            (ITEM_MASTER_SID)\n"
               + "SELECT ITEM_MASTER_SID\n"
               + "FROM   @RETURNS_ITEM\n"
               + "\n"
               + "SET @COUNT = @@ROWCOUNT\n"
               + "\n"
               + "DECLARE @I INT = 1\n"
               + "DECLARE @ITEM INT\n"
               + "\n"
               + "IF OBJECT_ID('TEMPDB..#TEMP_RETURNS') IS NOT NULL\n"
               + "  DROP TABLE #TEMP_RETURNS\n"
               + "\n"
               + "CREATE TABLE #TEMP_RETURNS\n"
               + "  (\n"
               + "     ITEM_MASTER_SID     INT,\n"
               + "     RETURNS_DETAILS_SID INT,\n"
               + "     PERIOD_SID          INT,\n"
               + "     ACTUAL_RATE         NUMERIC(22, 6),\n"
               + "     PROJECTED_RATE      NUMERIC(22, 6)\n"
               + "  )\n"
               + "\n"
               + "WHILE ( @I <= @COUNT )\n"
               + "  BEGIN\n"
               + "      SET @ITEM = (SELECT ITEM_MASTER_SID\n"
               + "                   FROM   #ITEM\n"
               + "                   WHERE  ID = @I);\n"
               + "\n"
               + "      WITH ITEM_PROJ_DETAILS\n"
               + "           AS (SELECT ROW_NUMBER()\n"
               + "                        OVER (\n"
               + "                          PARTITION BY ITEM_MASTER_SID\n"
               + "                          ORDER BY LASTEST_DATE DESC ) RN,\n"
               + "                      ITEM_MASTER_SID,\n"
               + "                      PM.PROJECTION_MASTER_SID,\n"
               + "                      RETURNS_DETAILS_SID\n"
               + "               FROM   RETURNS_DETAILS RD\n"
               + "                      INNER JOIN PROJECTION_MASTER PM\n"
               + "                              ON RD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
               + "                      CROSS APPLY ( VALUES (MODIFIED_DATE),\n"
               + "                                           (CREATED_DATE) ) CS(LASTEST_DATE)\n"
               + "               WHERE  SAVE_FLAG = 1\n"
               + "                      AND ITEM_MASTER_SID = @ITEM)\n"
               + "      INSERT INTO #TEMP_RETURNS\n"
               + "                  (ITEM_MASTER_SID,\n"
               + "                   RETURNS_DETAILS_SID,\n"
               + "                   PERIOD_SID,\n"
               + "                   ACTUAL_RATE,\n"
               + "                   PROJECTED_RATE)\n"
               + "      SELECT @ITEM                                                               AS ITEM_MASTER_SID,\n"
               + "             COALESCE(R_ACTUALS.RETURNS_DETAILS_SID, R_PROJ.RETURNS_DETAILS_SID) AS RETURNS_DETAILS_SID,\n"
               + "             COALESCE(R_ACTUALS.PERIOD_SID, R_PROJ.PERIOD_SID)                   AS PERIOD_SID,\n"
               + "             R_ACTUALS.ACTUAL_RETURN_PERCENT,\n"
               + "             R_PROJ.PROJECTED_RETURN_PERCENT\n"
               + "      FROM   (SELECT RETURNS_DETAILS_SID,\n"
               + "                     PERIOD_SID,\n"
               + "                     ACTUAL_RETURN_PERCENT\n"
               + "              FROM   RETURNS_ACTUALS NAP\n"
               + "              WHERE  EXISTS (SELECT 1\n"
               + "                             FROM   RETURNS_DETAILS RD\n"
               + "                                    INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
               + "                                            ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
               + "                                               AND RD.RETURNS_DETAILS_SID = NAP.RETURNS_DETAILS_SID\n"
               + "                             WHERE  IMPD.RN = 1)) R_ACTUALS\n"
               + "             FULL JOIN (SELECT RETURNS_DETAILS_SID,\n"
               + "                               PERIOD_SID,\n"
               + "                               PROJECTED_RETURN_PERCENT\n"
               + "                        FROM   RETURNS_PROJ_DETAILS NPP\n"
               + "                        WHERE  EXISTS (SELECT 1\n"
               + "                                       FROM   RETURNS_DETAILS RD\n"
               + "                                              INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
               + "                                                      ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
               + "                                                         AND RD.RETURNS_DETAILS_SID = NPP.RETURNS_DETAILS_SID\n"
               + "                                       WHERE  IMPD.RN = 1)) R_PROJ\n"
               + "                    ON R_ACTUALS.RETURNS_DETAILS_SID = R_PROJ.RETURNS_DETAILS_SID\n"
               + "                       AND R_ACTUALS.PERIOD_SID = R_PROJ.PERIOD_SID\n"
               + "\n"
               + "      SET @I = @I + 1\n"
               + "  END\n"
               + "\n"
               + "DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
               + "\n"
               + "INSERT INTO @ITEMID\n"
               + "SELECT DISTINCT ITEM_MASTER_SID\n"
               + "FROM   CCP_DETAILS CCP\n"
               + "WHERE  EXISTS (SELECT 1\n"
               + "               FROM   CFF_DETAILS CD\n"
               + "                      JOIN PROJECTION_DETAILS PD\n"
               + "                        ON CD.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID\n"
               + "                           AND CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
               + "               WHERE  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
               + "                      AND CFF_MASTER_SID = @CFF_MASTER_SID\n"
               + "                      AND EXISTS (SELECT 1\n"
               + "                                  FROM   @CCP C\n"
               + "                                  WHERE  C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID));\n"
               + "\n"
               + "WITH FORECAST_DETAILS\n"
               + "     AS (SELECT CD.CFF_MASTER_SID,\n"
               + "                PD.PROJECTION_MASTER_SID,\n"
               + "                PROJECTION_DETAILS_SID,\n"
               + "                PD.CCP_DETAILS_SID\n"
               + "         FROM   CFF_DETAILS CD\n"
               + "                JOIN PROJECTION_DETAILS PD\n"
               + "                  ON CD.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID\n"
               + "                     AND CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
               + "         WHERE  CFF_MASTER_SID = @CFF_MASTER_SID\n"
               + "                AND EXISTS (SELECT 1\n"
               + "                            FROM   @CCP C\n"
               + "                            WHERE  C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID)),\n"
               + "     DATA\n"
               + "     AS (SELECT *\n"
               + "         FROM   FORECAST_DETAILS FD\n"
               + "                CROSS JOIN PERIOD P\n"
               + "         WHERE  P.PERIOD_SID BETWEEN @ACT_START_PERIOD_SID AND @PROJ_END_PERIOD_SID),\n"
               + "     SALES\n"
               + "     AS (SELECT YEAR,\n"
               + "                PERIODS= @PERIOD,\n"
               + "                PROJECTION_SALES = COALESCE(SUM(NSP.PROJECTION_SALES), 0)\n"
               + "                                   + COALESCE(SUM(MSP.PROJECTION_SALES), 0),\n"
               + "                PROJECTION_UNITS = COALESCE(SUM(NSP.PROJECTION_UNITS), 0)\n"
               + "                                   + COALESCE(SUM(MSP.PROJECTION_UNITS), 0),\n"
               + "                ACTUAL_SALES = COALESCE(SUM(NAS.ACTUAL_SALES), 0)\n"
               + "                               + COALESCE(SUM(MAS.ACTUAL_SALES), 0),\n"
               + "                ACTUAL_UNITS = COALESCE(SUM(NAS.ACTUAL_UNITS), 0)\n"
               + "                               + COALESCE(SUM(MAS.ACTUAL_UNITS), 0),\n"
               + "                COGS_PROJECTED = ( COALESCE(SUM(NSP.PROJECTION_SALES * ISNULL(U.ITEM_PRICE, 0)), 0)\n"
               + "                                   + COALESCE(SUM(MSP.PROJECTION_SALES * ISNULL(U.ITEM_PRICE, 0)), 0) ),\n"
               + "                COGS_ACTUAL = ( COALESCE(SUM(NAS.ACTUAL_SALES * ISNULL(U.ITEM_PRICE, 0)), 0)\n"
               + "                                + COALESCE(SUM(MAS.ACTUAL_SALES * ISNULL(U.ITEM_PRICE, 0)), 0) ),\n"
               + "                RETURNS_ACTUALS = ( COALESCE(SUM(NAS.ACTUAL_SALES), 0)\n"
               + "                                    + COALESCE(SUM(MAS.ACTUAL_SALES), 0) ) * AVG(TR.ACTUAL_RATE),\n"
               + "                RETURNS_PROJECTED = ( COALESCE(SUM(NSP.PROJECTION_SALES), 0)\n"
               + "                                      + COALESCE(SUM(MSP.PROJECTION_SALES), 0) ) * AVG(TR.PROJECTED_RATE)\n"
               + "         FROM   DATA FD\n"
               + "                JOIN CCP_DETAILS CD\n"
               + "                  ON FD.CCP_DETAILS_SID = CD.CCP_DETAILS_SID\n"
               + "                LEFT OUTER JOIN NM_SALES_PROJECTION NSP\n"
               + "                             ON NSP.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID\n"
               + "                                AND FD.PERIOD_SID = NSP.PERIOD_SID\n"
               + "                                AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID\n"
               + "                LEFT OUTER JOIN M_SALES_PROJECTION MSP\n"
               + "                             ON MSP.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID\n"
               + "                                AND FD.PERIOD_SID = MSP.PERIOD_SID\n"
               + "                                AND MSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID\n"
               + "                LEFT OUTER JOIN NM_ACTUAL_SALES NAS\n"
               + "                             ON NAS.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID\n"
               + "                                AND FD.PERIOD_SID = NAS.PERIOD_SID\n"
               + "                                AND NAS.PERIOD_SID BETWEEN @ACT_START_PERIOD_SID AND @ACT_END_PERIOD_SID\n"
               + "                LEFT OUTER JOIN M_ACTUAL_SALES MAS\n"
               + "                             ON MAS.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID\n"
               + "                                AND FD.PERIOD_SID = MAS.PERIOD_SID\n"
               + "                                AND MAS.PERIOD_SID BETWEEN @ACT_START_PERIOD_SID AND @ACT_END_PERIOD_SID\n"
               + "                LEFT OUTER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, 'UN') U\n"
               + "                             ON CD.ITEM_MASTER_SID = U.ITEM_MASTER_SID\n"
               + "                                AND FD.PERIOD_SID = U.PERIOD_SID\n"
               + "                LEFT OUTER JOIN #TEMP_RETURNS TR\n"
               + "                             ON TR.ITEM_MASTER_SID = CD.ITEM_MASTER_SID\n"
               + "                                AND TR.PERIOD_SID = FD.PERIOD_SID\n"
               + "         GROUP  BY FD.CFF_MASTER_SID,\n"
               + "                   YEAR,\n"
               + "                   @PERIOD),\n"
               + "     NM_DISCOUNT\n"
               + "     AS (SELECT YEAR,\n"
               + "                PERIODS= @PERIOD,\n"
               + "                PROJECTION_SALES = COALESCE(SUM(NSP.PROJECTION_SALES), 0),\n"
               + "                ACTUAL_SALES = COALESCE(SUM(NAD.ACTUAL_SALES), 0)\n"
               + "         FROM   DATA FD\n"
               + "                LEFT OUTER JOIN(SELECT SUM(NSP.PROJECTION_SALES) AS PROJECTION_SALES,\n"
               + "                                       PERIOD_SID,\n"
               + "                                       PROJECTION_DETAILS_SID\n"
               + "                                FROM   NM_DISCOUNT_PROJECTION NSP\n"
               + "                                GROUP  BY PROJECTION_DETAILS_SID,\n"
               + "                                          PERIOD_SID) NSP\n"
               + "                             ON NSP.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID\n"
               + "                                AND FD.PERIOD_SID = NSP.PERIOD_SID\n"
               + "                                AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID\n"
               + "                LEFT OUTER JOIN(SELECT SUM(ACTUAL_SALES) AS ACTUAL_SALES,\n"
               + "                                       PERIOD_SID,\n"
               + "                                       PROJECTION_DETAILS_SID\n"
               + "                                FROM   NM_ACTUAL_DISCOUNT NAD\n"
               + "                                GROUP  BY PROJECTION_DETAILS_SID,\n"
               + "                                          PERIOD_SID) NAD\n"
               + "                             ON NAD.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID\n"
               + "                                AND FD.PERIOD_SID = NAD.PERIOD_SID\n"
               + "                                AND NAD.PERIOD_SID BETWEEN @ACT_START_PERIOD_SID AND @ACT_END_PERIOD_SID\n"
               + "         GROUP  BY YEAR,\n"
               + "                   @PERIOD),\n"
               + "     PPA_SUPP_MANDATED\n"
               + "     AS (SELECT YEAR,\n"
               + "                PERIODS= @PERIOD,\n"
               + "                PPA_DISCOUNT_PROJECTED = COALESCE(SUM(NSP.PROJECTION_DISCOUNT_DOLLAR), 0),\n"
               + "                SUPPLEMENTAL_DISCOUNT_PROJECTED = COALESCE(SUM(SDP.PROJECTION_SALES), 0),\n"
               + "                MANDATED_DISCOUNT_PROJECTED = COALESCE(SUM(MDP.PROJECTION_SALES), 0),\n"
               + "                PPA_ACTUAL_SALES = COALESCE(SUM(NAS.ACTUAL_DISCOUNT_DOLLAR), 0),\n"
               + "                SUPPLEMENTAL_DISCOUNT_ACTUALS = COALESCE(SUM(SDA.ACTUAL_SALES), 0),\n"
               + "                MANDATED_DISCOUNT_ACTUALS = COALESCE(SUM(MAD.ACTUAL_SALES), 0)\n"
               + "         FROM   DATA FD\n"
               + "                LEFT OUTER JOIN NM_PPA_PROJECTION NSP\n"
               + "                             ON NSP.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID\n"
               + "                                AND FD.PERIOD_SID = NSP.PERIOD_SID\n"
               + "                                AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @PROJ_END_PERIOD_SID\n"
               + "                LEFT OUTER JOIN NM_ACTUAL_PPA NAS\n"
               + "                             ON NAS.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID\n"
               + "                                AND FD.PERIOD_SID = NAS.PERIOD_SID\n"
               + "                                AND NAS.PERIOD_SID BETWEEN @ACT_START_PERIOD_SID AND @ACT_END_PERIOD_SID\n"
               + "                LEFT OUTER JOIN M_SUPPLEMENTAL_DISC_PROJ SDP\n"
               + "                             ON SDP.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID\n"
               + "                                AND FD.PERIOD_SID = SDP.PERIOD_SID\n"
               + "                LEFT OUTER JOIN M_SUPPLEMENTAL_DISC_ACTUALS SDA\n"
               + "                             ON SDA.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID\n"
               + "                                AND FD.PERIOD_SID = SDA.PERIOD_SID\n"
               + "                LEFT OUTER JOIN M_DISCOUNT_PROJECTION MDP\n"
               + "                             ON MDP.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID\n"
               + "                                AND MDP.PERIOD_SID = FD.PERIOD_SID\n"
               + "                                AND MDP.SAVE_FLAG = 1\n"
               + "                LEFT OUTER JOIN M_ACTUAL_DISCOUNT MAD\n"
               + "                             ON MAD.PROJECTION_DETAILS_SID = FD.PROJECTION_DETAILS_SID\n"
               + "                                AND MAD.PERIOD_SID = FD.PERIOD_SID\n"
               + "                                AND MAD.SAVE_FLAG = 1\n"
               + "         GROUP  BY YEAR,\n"
               + "                   @PERIOD)\n"
               + " SELECT S.YEAR,\n" +
"       S.PERIODS,\n" +
"       S.PROJECTION_SALES,\n" +
"       S.ACTUAL_SALES,\n" +
"       S.PROJECTION_UNITS,\n" +
"       S.ACTUAL_UNITS,\n" +
"       TOTAL_DISCOUNT_PROJECTED = ISNULL(ND.PROJECTION_SALES,0)\n" +
"                                  + ISNULL(PPA_DISCOUNT_PROJECTED,0)\n" +
"                                  + ISNULL(SUPPLEMENTAL_DISCOUNT_PROJECTED,0)\n" +
"                                  + ISNULL(MANDATED_DISCOUNT_PROJECTED,0)\n" +
"                                  + ISNULL(RETURNS_PROJECTED,0),\n" +
"       TOTAL_DISCOUNT_ACTUALS = ISNULL(ND.ACTUAL_SALES,0) + ISNULL(PPA_ACTUAL_SALES,0)\n" +
"                                + ISNULL(SUPPLEMENTAL_DISCOUNT_ACTUALS,0)\n" +
"                                + ISNULL(MANDATED_DISCOUNT_ACTUALS,0) + ISNULL(RETURNS_ACTUALS,0),\n" +
"       NET_SALES_PROJECTED = ISNULL(S.PROJECTION_SALES,0) - ( ISNULL(ND.PROJECTION_SALES,0)\n" +
"                                                    + ISNULL(PPA_DISCOUNT_PROJECTED,0)\n" +
"                                                    + ISNULL(SUPPLEMENTAL_DISCOUNT_PROJECTED,0)\n" +
"                                                    + ISNULL(MANDATED_DISCOUNT_PROJECTED,0)\n" +
"                                                    + ISNULL(RETURNS_PROJECTED,0) ),\n" +
"       NET_SALES_ACTUALS = isnull(S.ACTUAL_SALES - ( ISNULL(ND.ACTUAL_SALES,0) + ISNULL(PPA_ACTUAL_SALES,0)\n" +
"                                              + ISNULL(SUPPLEMENTAL_DISCOUNT_ACTUALS,0)\n" +
"                                              + ISNULL(MANDATED_DISCOUNT_ACTUALS,0) + ISNULL(RETURNS_ACTUALS,0) ),0),\n" +
"       COGS_PROJECTED,\n" +
"       COGS_ACTUAL,\n" +
"       NET_PROFIT_ACTUAL = (S.ACTUAL_SALES - ( ND.ACTUAL_SALES + PPA_ACTUAL_SALES\n" +
"                                            + SUPPLEMENTAL_DISCOUNT_ACTUALS\n" +
"                                            + MANDATED_DISCOUNT_ACTUALS + RETURNS_ACTUALS )  - ( ISNULL(s.COGS_ACTUAL, 0) ) ),\n" +
"       NET_PROFIT_PROJECTED = (S.PROJECTION_SALES - ( ND.PROJECTION_SALES\n" +
"                                                          + PPA_DISCOUNT_PROJECTED\n" +
"                                                          + SUPPLEMENTAL_DISCOUNT_PROJECTED\n" +
"                                                          + MANDATED_DISCOUNT_PROJECTED\n" +
"                                                         + RETURNS_PROJECTED )  - ( ISNULL(S.COGS_PROJECTED, 0) ) ),\n" +
"       TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( ISNULL(ND.PROJECTION_SALES,0)\n" +
"                                               + ISNULL(PPA_DISCOUNT_PROJECTED,0)\n" +
"                                               + ISNULL(SUPPLEMENTAL_DISCOUNT_PROJECTED,0)\n" +
"                                               + ISNULL(MANDATED_DISCOUNT_PROJECTED,0)\n" +
"                                               + ISNULL(RETURNS_PROJECTED,0) ) / NULLIF(S.PROJECTION_SALES, 0),\n" +
"       TOTAL_DISCOUNT_ACTUALS_PERCENTAGE = ( ISNULL(ND.ACTUAL_SALES,0) + ISNULL(PPA_ACTUAL_SALES,0)\n" +
"                                             + ISNULL(SUPPLEMENTAL_DISCOUNT_ACTUALS,0)\n" +
"                                             + ISNULL(MANDATED_DISCOUNT_ACTUALS,0) + ISNULL(RETURNS_ACTUALS,0) ) / NULLIF(S.ACTUAL_SALES, 0),\n" +
"       TOTAL_PROJECTED_RPU = ( ISNULL(ND.PROJECTION_SALES,0)\n" +
"                               + ISNULL(PPA_DISCOUNT_PROJECTED,0)\n" +
"                               + ISNULL(SUPPLEMENTAL_DISCOUNT_PROJECTED,0)\n" +
"                               + ISNULL(MANDATED_DISCOUNT_PROJECTED,0)\n" +
"                               + ISNULL(RETURNS_PROJECTED,0) ) / NULLIF(S.PROJECTION_UNITS,0),\n" +
"       TOTAL_ACTUAL_RPU = ( ISNULL(ND.ACTUAL_SALES,0) + ISNULL(PPA_ACTUAL_SALES,0)\n" +
"                            + ISNULL(SUPPLEMENTAL_DISCOUNT_ACTUALS,0)\n" +
"                            + ISNULL(MANDATED_DISCOUNT_ACTUALS,0) + ISNULL(RETURNS_ACTUALS,0) ) / NULLIF(S.ACTUAL_SALES,0)\n" +
"FROM   SALES S\n" +
"       INNER JOIN PPA_SUPP_MANDATED PSM\n" +
"               ON S.YEAR = PSM.YEAR\n" +
"                  AND S.PERIODS = PSM.PERIODS\n" +
"       INNER JOIN NM_DISCOUNT ND\n" +
"               ON S.YEAR = ND.YEAR\n" +
"                  AND S.PERIODS = ND.PERIODS\n" +
"ORDER  BY S.YEAR,\n" +
"          S.PERIODS";

       
       query=query.replaceAll("@PERIOD", freq);
       return query;

   }
    
    private List<ProjectionResultsDTO> setPivotValue(List<Object[]> list, int frequencyDivision, List<String> periodList, ProjectionSelectionDTO projSelDTO, int dcol, List<Object[]> discountList) {
        List<ProjectionResultsDTO> returnList = new ArrayList<ProjectionResultsDTO>();
        int discountIndex = 0;
        for (Object[] row : list) {

            String column = "";
            int year = 0;
            if (row[3] != null) {
                year = Integer.valueOf(String.valueOf(row[3]));
            }
            int period = 0;
            if (row[4] != null) {
                period = Integer.valueOf(String.valueOf(row[4]));
            }
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = "";
           if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
                List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
                columnList.remove("group");
                projDTO.setGroup(commonHeader);
                String value = "null";
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
                    value = "" + row[2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                commonColumn = "custExFactory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[55];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[56];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                commonColumn = "demand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[29];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[30];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "adjDemand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[53];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[54];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "inventory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[31];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[32];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "inventoryDetails";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[57];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[58];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perExFactory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[13];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[14];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perCustExFactory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[61];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[62];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perDemand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[33];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[34];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perAdjDemand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[59];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[60];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perInventory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[35];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[36];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perInventoryDetails";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[63];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[64];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "conSalesWac";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   
                    //value = "" + row[5];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(row[5], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   // value = "" + row[6];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                  value=getCellValue(row[6], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "unitVol";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                  //  value = "" + row[7];
                   // value = getFormattedValue(NUM_ZERO, value);
                    value=getCellValue(row[7], "Units");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                 //   value = "" + row[8];
                   // value = getFormattedValue(NUM_ZERO, value);
                    value=getCellValue(row[8], "Units");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisPer";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   // value = "" + row[9];
                  //  value = getFormattedValue(PER_TWO, value);
                   value=getCellValue(row[11], "Percent");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   // value = "" + row[10];
                  //  value = getFormattedValue(PER_TWO, value);
                   value=getCellValue(row[12], "Percent");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totalRPU";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                  //  value = "" + row[41];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value=getCellValue(row[41], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                  //  value = "" + row[42];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(row[42], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisDol";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                  //  value = "" + row[11];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(row[9], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                  //  value = "" + row[12];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(row[10], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netSales";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   // value = "" + row[27];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(row[27], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   // value = "" + row[28];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                  value=getCellValue(row[28], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "cogs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   // value = "" + row[43];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                  value=getCellValue(row[43], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    //value = "" + row[44];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(row[44], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netProfit";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   // value = "" + row[45];
                   // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                   value=getCellValue(row[45], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    //value = "" + row[46];
                    //value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value=getCellValue(row[46], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                //Returns
                commonColumn = "totDisDol" + RETURNS.getConstant().replace(" ", "");
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                   // value = "" + row[47];
                  //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                  value=getCellValue(row[47], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[48];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                  // value=getCellValue(row[48], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totalRPU" + RETURNS.getConstant().replace(" ", "");
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[51];
                    value = getFormattedValue(PER_THREE, value);
                //  value=getCellValue(row[51], "Sales");
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[52];
                    value = getFormattedValue(PER_THREE, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisPer" + RETURNS.getConstant().replace(" ", "");
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[49];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[50];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

//                if (projSelDTO.isPpa()) {
                //PPA
                commonColumn = "totDisDol" + PPA_DISCOUNT.getConstant().replace(" ", "");
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[15];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[16];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totalRPU" + PPA_DISCOUNT.getConstant().replace(" ", "");
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[39];
                    value = getFormattedValue(PER_THREE, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[40];
                    value = getFormattedValue(PER_THREE, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisPer" + PPA_DISCOUNT.getConstant().replace(" ", "");
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[17];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[18];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
//                }

                //MANDATED
                commonColumn = "totDisDol" + MANDATED.getConstant().replace(" ", "");
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[19];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[20];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totalRPU" + MANDATED.getConstant().replace(" ", "");
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
//                        value = "" + row[39];
//                        value = getFormattedValue(PER_THREE, value);
                    projDTO.addStringProperties(column, SPRDASH.getConstant());
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
//                        value = "" + row[40];
//                        value = getFormattedValue(PER_THREE, value);
                    projDTO.addStringProperties(column, SPRDASH.getConstant());
                    columnList.remove(column);
                }
                commonColumn = "totDisPer" + MANDATED.getConstant().replace(" ", "");
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[23];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[24];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                //SUPPLEMENTAL
                commonColumn = "totDisDol" + SUPPLEMENTAL.getConstant().replace(" ", "");
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[21];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[22];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totalRPU" + SUPPLEMENTAL.getConstant().replace(" ", "");
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
//                        value = "" + row[39];
//                        value = getFormattedValue(PER_THREE, value);
                    projDTO.addStringProperties(column, SPRDASH.getConstant());
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
//                        value = "" + row[40];
//                        value = getFormattedValue(PER_THREE, value);
                    projDTO.addStringProperties(column, SPRDASH.getConstant());
                    columnList.remove(column);
                }
                commonColumn = "totDisPer" + SUPPLEMENTAL.getConstant().replace(" ", "");
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[25];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = "" + row[26];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                boolean start = false;
                if (discountList != null) {
                    for (int i = discountIndex; i < discountList.size(); i++) {
                        Object[] discountRow = discountList.get(i);
                        int dyear = Integer.valueOf(String.valueOf(discountRow[dcol - 2]));
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
                            column = commonColumn1 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                             //   value = "" + discountRow[dcol + 1];
                               // value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                               value=getCellValue(discountRow[dcol + 1], "Sales");
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn1 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                               // value = "" + discountRow[dcol + 2];
                                //value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                value=getCellValue(discountRow[dcol + 2], "Sales");
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn2 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                               // value = "" + discountRow[dcol + 3];
                              //  value = getFormattedValue(PER_TWO, value);
                              value=getCellValue(discountRow[dcol + 3], "Percent");
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn2 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                               // value = "" + discountRow[dcol + 4];
                              //  value = getFormattedValue(PER_TWO, value);
                               value=getCellValue(discountRow[dcol + 4], "Percent");
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn3 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                               // value = "" + discountRow[dcol + 5];
                              //  value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                               value=getCellValue(discountRow[dcol + 5], "Sales");
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn3 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                //value = "" + discountRow[dcol + 6];
                                //value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                value=getCellValue(discountRow[dcol + 6], "Sales");
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }

                        } else if (start) {
                            break;
                        }
                    }
                }

                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, "null", CURRENCY));
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                returnList.add(projDTO);
            }

        }
        if (!periodList.isEmpty()&&!projSelDTO.isIsProjectionTotal()) {
           List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
           columnList.remove("group");
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
   
   public int getDiscoutCount(ProjectionSelectionDTO projDTO)
   {
     int count=0;
       String query = "SELECT Count(distinct NMDP.RS_MODEL_SID)+4\n"
               + "FROM   CFF_DETAILS CFF\n"
               + "       JOIN PROJECTION_DETAILS PD\n"
               + "         ON CFF.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID\n"
               + "            AND CFF.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
               + "       JOIN NM_DISCOUNT_PROJ_MASTER NMDP\n"
               + "         ON NMDP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
               + "       JOIN RS_MODEL RM\n"
               + "         ON RM.RS_MODEL_SID = NMDP.RS_MODEL_SID\n"
               + "WHERE  CFF.CFF_MASTER_SID ="+projDTO.getProjectionId();
       List list=HelperTableLocalServiceUtil.executeSelectQuery(query);
       if(list!=null && list.size()>0){
           Object obj=list.get(0);
           String discountCount=String.valueOf(obj);
           count=Integer.valueOf(discountCount);
       }
     return count;
   }

    public static List getRSName(int cffSid) {
        String query = "SELECT DISTINCT RM.RS_NAME\n"
                + "FROM   CFF_DETAILS CFF\n"
                + "       JOIN PROJECTION_DETAILS PD\n"
                + "         ON CFF.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID\n"
                + "            AND CFF.CCP_DETAILS_SID = PD.CCP_DETAILS_SID\n"
                + "       JOIN NM_DISCOUNT_PROJ_MASTER NMDP\n"
                + "         ON NMDP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                + "       JOIN RS_MODEL RM\n"
                + "         ON RM.RS_MODEL_SID = NMDP.RS_MODEL_SID\n"
                + "WHERE  CFF.CFF_MASTER_SID = "+cffSid+" \n"
                + "UNION ALL\n"
                + "SELECT DISCOUNTS FROM (VALUES \n"
                + "('SUPPLEMENTAL'),\n"
                + "('MANDATED'),\n"
                + "('PPA'),\n"
                + "('RETURNS')) TC (DISCOUNTS)";
        List object = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return object;
    }
   
    private String getCommonColumn(ProjectionSelectionDTO projSelDTO,Object[] obj)
 {
     String column=StringUtils.EMPTY;        
     if(projSelDTO.getFrequencyDivision() == 4) {
                    column = "q" + String.valueOf(obj[2]) + String.valueOf(obj[1]);
                } else if (projSelDTO.getFrequencyDivision() == 2) {
                    column = "s" + String.valueOf(obj[2]) + String.valueOf(obj[1]);
                } else if (projSelDTO.getFrequencyDivision() == 1) {
                    column = String.valueOf(obj[1]);
                } else if (projSelDTO.getFrequencyDivision() == 12) {
                    String month = monthMap.get(String.valueOf(obj[2]));
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
         if(component.equals("Sales")){
         returnValue = getFormatTwoDecimalValue(CUR_TWO, returnValue, CURRENCY);
         }else if(component.equals("Units")){
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
        System.out.println("==ccp string=======================>>>"+ccps);
        return ccps;
 } 
    
    
}
