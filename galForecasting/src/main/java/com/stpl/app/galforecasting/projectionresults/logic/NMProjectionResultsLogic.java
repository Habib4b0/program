/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.projectionresults.logic;

import com.stpl.app.galforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.HeaderUtils.getCommonColumnHeader;
import static com.stpl.app.galforecasting.utils.Constant.LabelConstants.*;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sibi
 */
public class NMProjectionResultsLogic {

    private static final Logger LOGGER = Logger.getLogger(NMProjectionResultsLogic.class);
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
     private static final String Percentage = "%";
    private static final DecimalFormat CUR_TWO = new DecimalFormat("#,##0.00");

    public List<ProjectionResultsDTO> getDiscountPer(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getDiscountPer = = =");
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setSales(Constant.RATE);
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n";
        query += getProjectionResultsDiscountsPerQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, false,0);
        projDTOList.addAll(projDTOList1);
        LOGGER.info("= = = Ending getDiscountPer = = =");
        return projDTOList;
    }

    public List getTotalRPUDollar(ProjectionSelectionDTO projSelDTO, Boolean isVariable,int value) {
        LOGGER.info("= = = Inside getTotalRPUDollar = = =");
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setSales("TOT");
        String query = CommonLogic.getCCPQueryPR(projSelDTO) + " \n";
        List period = CommonLogic.getPeriodRestrictionPR(projSelDTO);
        query += "DECLARE @START_PERIOD_SID int \n"
                + "DECLARE @END_PERIOD_SID int "
                + " DECLARE @PROGRAM_TYPE varchar(100)='PROGRAM' \n"
                + "\n"
                + "		select @START_PERIOD_SID=PERIOD_SID from PERIOD\n"
                + "		where PERIOD_DATE='"+period.get(0)+"'\n"
                + "		select @END_PERIOD_SID=PERIOD_SID from PERIOD\n"
                + "		where PERIOD_DATE=DATEADD(MM,DATEDIFF(MM,0,'"+period.get(1)+"'),0)";
        
        query += "DECLARE @DISCOUNT_PPA TABLE (\n" + "     YEARS  INT,\n" + "  PERIODS  INT,\n" + "DISCOUNT   VARCHAR(50),\n" + " ACTUAL_DOLLAR NUMERIC(22, 6),\n"
                + "     PROJECTION_DOLLAR NUMERIC(22, 6),\n"
                + "     ACTUAL_RATE       NUMERIC(22, 6),\n"
                + "     PROJECTION_RATE   NUMERIC(22, 6),\n"
                + "     ACTUAL_RPU        NUMERIC(22, 6),\n"
                + "     PROJECTED_RPU     NUMERIC(22, 6)\n"
                + "  )";
        query += "INSERT INTO @DISCOUNT_PPA (\n"
                + "	YEARS\n"
                + "	, PERIODS\n"
                + "	, DISCOUNT\n"
                + "	, ACTUAL_DOLLAR\n"
                + "	, PROJECTION_DOLLAR\n"
                + "	, ACTUAL_RATE\n"
                + "	, PROJECTION_RATE\n"
                + "	, ACTUAL_RPU\n"
                + "	, PROJECTED_RPU\n"
                + "	)";
        query += getProjectionResultsDiscountsRPUQuery(projSelDTO, isVariable);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = null;
        if (!isVariable) {
            projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, false, value);
            projDTOList.addAll(projDTOList1);
        }
//        System.out.println("For discount---------------"+query);
        LOGGER.info("= = = Ending getTotalRPUDollar = = =");
        return isVariable ? list : projDTOList1;
    }

    public List<ProjectionResultsDTO> getDiscountDollar(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getDiscountDollar = = =");
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n";

        query += getProjectionResultsDiscountsQuery(projSelDTO, " order by DISCOUNTS");
//         System.out.println("Query:========DiscountDollar===================>"+query);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, false,0);
        projDTOList.addAll(projDTOList1);
        LOGGER.info("= = = Ending getDiscountDollar = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getTotalDiscountLevels(ProjectionSelectionDTO projSelDTO, int value) {
        LOGGER.info("= = = Inside getTotalDiscountLevels = = =");

        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projDTOList1 = null;
        List<Object> list = null;
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n";
        query += "DECLARE @FROM_DATE DATE\n"
                + "     , @STARTFROM DATE\n"
                + "     , @PROJECTION_DATE DATE\n"
                + "     , @START_PERIOD_SID INT\n"
                + "     , @END_PERIOD_SID INT\n"
                + "SELECT TOP 1 @STARTFROM = DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, - 3, GETDATE())), 0)\n"
                + "     , @PROJECTION_DATE = DATEADD(M, DATEDIFF(M, 0, DATEADD(DAY, - 1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))), 0)\n"
                + "FROM PROJECTION_MASTER\n"
                + "WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsTotal()) {
            query += getProjectionResultsTotalDiscount(projSelDTO);
            list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
            projDTOList1 = getCustomizedProjectionTotalDiscount(list, projSelDTO);
        } else if (value == 1 || value == 2 || value == 3) {
            projDTOList1 = getTotalRPUDollar(projSelDTO,Boolean.FALSE,value);
        }
//            projDTOList1 = PPARPUtest(projSelDTO);
//            projDTOList1 = getDiscountPer(projSelDTO);
//        } else if (value == 2) {
//            System.out.println("Inside rpu---------------");
//            projDTOList1 = PPARPUtest(projSelDTO);
//            getTotalRPUDollar(projSelDTO);
//        } else if (value == 3) {
//            projDTOList1 = PPARPUtest(projSelDTO);
////            projDTOList1 = getDiscountDollar(projSelDTO);
//        }
        projDTOList.addAll(projDTOList1);
        LOGGER.info("= = = Ending getTotalDiscountLevels = = =");
        return projDTOList;
    }

    private List getCustomizedProjectionTotalDiscount(List list, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> resultList = new ArrayList<ProjectionResultsDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
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
//        System.out.println("projSelDTO.isIsCustomHierarchy():=============>"+projSelDTO.isIsCustomHierarchy());
        if(projSelDTO.isIsCustomHierarchy()){
        totalDiscountPerDto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        }
        
        totalRPUDto.setParent(1);
        totalRPUDto.setGroup(TOTAL_RPU.getConstant());
        totalRPUDto.setProjectionTotal(0);
        totalRPUDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalRPUDto.setOnExpandTotalRow(0);
        totalRPUDto.setLevelValue(projSelDTO.getLevelValue());
        totalRPUDto.setLevelNo(projSelDTO.getLevelNo());
        totalRPUDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalRPUDto.setHierarchyNo(projSelDTO.getHierarchyNo());
//         System.out.println("projSelDTO.isIsCustomHierarchy():=============>"+projSelDTO.isIsCustomHierarchy());
        if(projSelDTO.isIsCustomHierarchy()){
        totalRPUDto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        }

        totalDiscountAmtDto.setParent(1);
        totalDiscountAmtDto.setGroup(TOTAL_DISCOUNT_AMOUNT.getConstant());
        totalDiscountAmtDto.setProjectionTotal(0);
        totalDiscountAmtDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalDiscountAmtDto.setOnExpandTotalRow(0);
        totalDiscountAmtDto.setLevelValue(projSelDTO.getLevelValue());
        totalDiscountAmtDto.setLevelNo(projSelDTO.getLevelNo());
        totalDiscountAmtDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalDiscountAmtDto.setHierarchyNo(projSelDTO.getHierarchyNo());
//         System.out.println("projSelDTO.isIsCustomHierarchy():=============>"+projSelDTO.isIsCustomHierarchy());
       if(projSelDTO.isIsCustomHierarchy()){
        totalDiscountAmtDto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        }

        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                int year = (obj[0] == null) ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = (obj[1] == null) ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                String commonColumn = common.get(0);
                int col = 3;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY;
                    value = StringUtils.EMPTY + obj[col];
                    value = getFormattedValue(PER_TWO, value);
                    totalDiscountPerDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 3];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalDiscountAmtDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 6];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalRPUDto.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + 1];
                    value = getFormattedValue(PER_TWO, value);
                    totalDiscountPerDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 4];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalDiscountAmtDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 7];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalRPUDto.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            totalDiscountPerDto.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            totalRPUDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            totalDiscountAmtDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
        }
        resultList.add(totalDiscountPerDto);
        resultList.add(totalRPUDto);
        resultList.add(totalDiscountAmtDto);

        return resultList;
    }

    public ProjectionResultsDTO getPPAPer(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales(Constant.RATE);
        ProjectionResultsDTO ppaDto = new ProjectionResultsDTO();
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsPPAPerQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, true,0);
        if (projDTOList1 != null && !projDTOList1.isEmpty()) {
            ppaDto = projDTOList1.get(0);
        }
        ppaDto.setGroup(PPA_DISCOUNT.getConstant());
        ppaDto.setParent(0);
        return ppaDto;
    }

    public List<Object> getPPARPU(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales(Constant.RATE);
        ProjectionResultsDTO ppaDto = new ProjectionResultsDTO();
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsPPARPU(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, true,1);
        if (projDTOList1 != null && !projDTOList1.isEmpty()) {
            ppaDto = projDTOList1.get(0);
        }
        ppaDto.setGroup(PPA_DISCOUNT.getConstant());
        ppaDto.setParent(0);
        return list;
    }

    
    
     public List<ProjectionResultsDTO> PPARPUtest(ProjectionSelectionDTO projSelDTO) {
            List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setSales(Constant.RATE);
        ProjectionResultsDTO ppaDto = new ProjectionResultsDTO();
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsPPARPU(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
         projDTOList = getCustomizedProjectionResultsDiscount(list, projSelDTO, true,2);
//        if (projDTOList1 != null && !projDTOList1.isEmpty()) {
//            ppaDto = projDTOList1.get(0);
//        }
//        ppaDto.setGroup(PPA_DISCOUNT.getConstant());
//        ppaDto.setParent(0);
        return projDTOList;
    }
    public ProjectionResultsDTO getPPADollar(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        ProjectionResultsDTO ppaDto = new ProjectionResultsDTO();
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsPPAQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, true,3);
        if (projDTOList1 != null && !projDTOList1.isEmpty()) {
            ppaDto = projDTOList1.get(0);
        }
        ppaDto.setGroup(PPA_DISCOUNT.getConstant());
        ppaDto.setParent(0);
        return ppaDto;
    }
    List<String> discountList = new ArrayList<String>();

    public List<ProjectionResultsDTO> getCustomizedProjectionResultsDiscount(List<Object> list, ProjectionSelectionDTO projSelDTO, boolean isPPA,int value) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        projSelDTO.setDiscountIndex(0);
        if (!projSelDTO.isIsTotal() && !isPPA) {
            System.out.println("==========going inside discount================");
            projDTOList = loadDiscounts(list, StringUtils.EMPTY, projSelDTO, value);
        } else {
            System.out.println("==========going inside else discount================");
            ProjectionResultsDTO dto = getCustomisedProjectionResultsDiscount(list, Constant.TOTAL_SMALL, projSelDTO);
            projDTOList.add(dto);
        }
        return projDTOList;
    }

    public ProjectionResultsDTO getCustomisedProjectionResultsDiscount(List<Object> list, String discountName, ProjectionSelectionDTO projSelDTO) {
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
        projDTO.setLevelValue(projSelDTO.getLevelValue());
        projDTO.setLevelNo(projSelDTO.getLevelNo());
        projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projDTO.setGroup(null);
        boolean forword = false;
        if (discountName.contains(Constant.TOTAL_SMALL)) {
            forword = true;
        }
        boolean start = true;
        String discount = null;
        if (list != null && !list.isEmpty()) {
            for (int i = projSelDTO.getDiscountIndex(); i < list.size() && start; i++) {
                projSelDTO.setDiscountIndex(i);
                Object[] discountRow = (Object[]) list.get(i);
                if (discountList.contains(StringUtils.EMPTY + discountRow[2])) {
                    forword = true;
                } else if (!discountName.contains(Constant.TOTAL_SMALL)) {
                    forword = false;
                }
                if (forword) {
                    if (discount == null) {
                        projDTO.setGroup(String.valueOf(discountRow[2]));
                    } else if (!discount.equals(discountRow[2].toString())) {
                        if (!discountName.contains(Constant.TOTAL_SMALL)) {
                            discountList.remove(discount);
                        }
                        start = false;
                    }
                    if (start) {
                        discount = discountRow[2].toString();
                        String column = StringUtils.EMPTY;
                        int year = Integer.valueOf(String.valueOf(discountRow[0]));
                        int period = Integer.valueOf(String.valueOf(discountRow[1]));
                        List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                        String commonColumn = common.get(0);
                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            String value = StringUtils.EMPTY + discountRow[3];
                            if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                                value = getFormattedValue(PER_TWO, value);
                            } else if (projSelDTO.getSales().contains("TOT")) {
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            }
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            String value = StringUtils.EMPTY + discountRow[4];
                            if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            } else if (projSelDTO.getSales().contains(Constant.RATE)) {
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
        if (discountName.contains(Constant.TOTAL_SMALL)) {
            projDTO.setParent(1);
            projDTO.setLevelNo(projSelDTO.getLevelNo());
            projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
            projDTO.setParentNode(projSelDTO.getParentNode());
            projDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
            projDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
            projDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
            projDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
            projDTO.setOnExpandTotalRow(0);
            if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                projDTO.setGroup("Total Discount $");
            } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                projDTO.setGroup("Total Discount %");
            } else {
                projDTO.setGroup(Constant.Total_RPU);
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
            projDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
        }
        return projDTO;
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionResultsSales(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDtoList = new ArrayList<ProjectionResultsDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        ProjectionResultsDTO projSalesDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO projUnitDTO = new ProjectionResultsDTO();
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
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                int year = Integer.valueOf(String.valueOf(obj[0]));
                int period = Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                int col = 2;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projSalesDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 2];
                    value = getFormattedValue(NUM_ZERO, value);
                    projUnitDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projSalesDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 3];
                    value = getFormattedValue(NUM_ZERO, value);
                    projUnitDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            projSalesDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            projUnitDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
        }
        projDtoList.add(projSalesDTO);
        projDtoList.add(projUnitDTO);
        return projDtoList;
    }

    public List<ProjectionResultsDTO> getContractSalesAndUnits(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getContractSalesAndUnits = = =");
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        String query = CommonLogic.getCCPQueryPR(projSelDTO) + " \n" + getProjectionResultsSalesQuery(projSelDTO,false);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList = getCustomizedProjectionResultsSales(list, projSelDTO);
        LOGGER.info("= = = Ending getContractSalesAndUnits = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getProjectionPivot(ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        String cogsSelect = "DECLARE @FROM_DATE DATE\n"
                + "     , @STARTFROM DATE\n"
                + "     , @PROJECTION_DATE DATE\n"
                + "     , @START_PERIOD_SID INT\n"
                + "     , @END_PERIOD_SID INT\n"
                + "SELECT TOP 1 @STARTFROM = DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, - 3, GETDATE())), 0)\n"
                + "     , @PROJECTION_DATE = DATEADD(M, DATEDIFF(M, 0, DATEADD(DAY, - 1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))), 0)\n"
                + "FROM PROJECTION_MASTER\n"
                + "WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "\n"
                + "SELECT @START_PERIOD_SID = PERIOD_SID\n"
                + "FROM PERIOD\n"
                + "WHERE PERIOD_DATE = @STARTFROM\n"
                + "\n"
                + "SELECT @END_PERIOD_SID = PERIOD_SID\n"
                + "FROM PERIOD\n"
                + "WHERE PERIOD_DATE = @PROJECTION_DATE\n"
                + "\n";
        String gtsListQuery = cogsSelect + " \n " + CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsPivotQuery(projSelDTO);
        List<Object> gtsList = (List<Object>) CommonLogic.executeSelectQuery(gtsListQuery, null, null);
//        String discountListQuery = CommonLogic.getCCPQuery(projSelDTO) + " \n" + getProjectionResultsDiscountsPivotQuery(projSelDTO);
//        String discountListQuery = CommonLogic.getCCPQueryPR(projSelDTO) + " \n" + getTotalRPUDollar(projSelDTO,Boolean.TRUE);
//        System.out.println("discountListQuery in variance= --- -- " + discountListQuery);
//        List<Object> discountList = (List<Object>) CommonLogic.executeSelectQuery(discountListQuery, null, null);
        List discountList = getTotalRPUDollar(projSelDTO,Boolean.TRUE,0);
//        String returnQuery = cogsSelect + "\n" + CommonLogic.getCCPQuery(projSelDTO) + "\n" + getProjectionResultsPivotReturnQuery(projSelDTO);
//        List<Object> returnQueryList = (List<Object>) CommonLogic.executeSelectQuery(returnQuery, null, null);
        projDTOList = getCustomizedProjectionPivot(gtsList, discountList, projSelDTO);
        return projDTOList;
    }
    
    public List<ProjectionResultsDTO> getCustomizedProjectionPivot(List<Object> list, List<Object> discountList,  ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        String oldDiscount = "old";
        String newDiscount = "new";
        int returnIndex = 0;
        int discountIndex = 0;
        int col = 2;
        int dcol = 2;
        List<String> columnList1 = new ArrayList<String>(projSelDTO.getColumns());
        for (Object rows : list) {
            final Object[] row = (Object[]) rows;
            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[0]));
            int period = Integer.valueOf(String.valueOf(row[1]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = StringUtils.EMPTY;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
                List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
                columnList.remove(Constant.GROUP);
                projDTO.setGroup(commonHeader);
                String value = Constant.NULL;
                commonColumn = "exFactory";
                value = "...";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "demand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "inventory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perExFactory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perDemand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perInventory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "conSalesWac";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "unitVol";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 2];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 3];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.totDisPer;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 4];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 5];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.totDisDol;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 6];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 7];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.totalRPU;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 10];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 11];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netSales";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 8];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 9];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "cogs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 12];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 13];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netProfit";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 14];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 15];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
//                boolean start = false;
//                int discountFlag = 0;
                for (int i = discountIndex; i < discountList.size(); i++) {
//                    discountIndex = i;
                    Object[] discountRow = (Object[]) discountList.get(i);
                    int dyear = Integer.valueOf(String.valueOf(discountRow[dcol - 2]));
                    int dperiod = Integer.valueOf(String.valueOf(discountRow[1]));
                    List<String> dcommon = getCommonColumnHeader(frequencyDivision, dyear, dperiod);
                    String dcommonColumn = dcommon.get(0);
                    if (pcommonColumn.equals(dcommonColumn)) {
//                        start = true;
                        newDiscount = String.valueOf(discountRow[dcol]);
                        if (!oldDiscount.equals(newDiscount)) {
                            String head = String.valueOf(discountRow[dcol]).replace(" ", StringUtils.EMPTY);
                            String commonColumn1 = Constant.totDisDol + head;
                            String commonColumn2 = Constant.totDisPer + head;
                            String commonColumn3 = Constant.totalRPU + head;
                            column = commonColumn2 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 3];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn2 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 4];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn1 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 1];
                                value = getFormattedValue(PER_TWO, value);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn1 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 2];
                                value = getFormattedValue(PER_TWO, value);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn3 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 5];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn3 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 6];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
//                        discountFlag = 1;
                        }
                        oldDiscount = newDiscount;
                    }
//                    else {
//                        discountFlag = discountFlag == 1 ? 2 : 0;
//                    }

//                    if (discountFlag == 2) {
//                        discountIndex = discountIndex - 1;
//                        break;
//                    }
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(0);
                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, value = getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
                }
                projDTOList.add(projDTO);
            }

        }
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        boolean leftFlag = false;
        for (String ob : periodList) {
            leftFlag = true;
            ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(0);
            projDTO.setGroup(projSelDTO.getPeriodListMap().get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            }
            projDTOList.add(projDTO);
        }
        if (projSelDTO.getProjectionOrder().equals(ASCENDING.getConstant())) {
            if (leftFlag) {
                Collections.sort(projDTOList, new ProjectionResultsDTO());
            }
        } else {
            if (leftFlag) {
                Collections.sort(projDTOList, new ProjectionResultsDTO());
            }
            Collections.reverse(projDTOList);
        }
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getProjectionPivotTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        if (projectionTotalList.isEmpty()) {
            List<Object[]> gtsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS", orderedArgs);
            Object[] orderedArgs1 = {orderedArgs[0], orderedArgs[1], orderedArgs[2], orderedArgs[3], orderedArgs[4], orderedArgs[5], 0, "PROGRAM"};
            List<Object[]> discountsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS_DISCOUNT", orderedArgs1);
            getCustomizedProjectionPivotTotal(gtsList, discountsList, projSelDTO);
        }
        return projectionTotalList;
    }

    public void getCustomizedProjectionPivotTotal(List<Object[]> list, List<Object[]> discountList, ProjectionSelectionDTO projSelDTO) {
        projectionTotalList.clear();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        String oldDiscount = "old";
        String newDiscount = "new";
        int discountIndex = 0;
        int col = 5;
        int dcol = 3;
        if (frequencyDivision != 1) {
            col = col + 1;
//            dcol = dcol + 1;
        }
        for (Object[] row : list) {

            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[col - 1]));
            int period = Integer.valueOf(String.valueOf(row[4]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = StringUtils.EMPTY;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
                List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
                columnList.remove(Constant.GROUP);
                projDTO.setGroup(commonHeader);
                String value = Constant.NULL;
                commonColumn = "exFactory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "demand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 16];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 17];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "inventory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 18];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 19];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perExFactory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 8];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 9];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perDemand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 20];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 21];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perInventory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 22];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 23];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "conSalesWac";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "unitVol";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 2];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 3];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.totDisPer;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 6];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 7];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.totalRPU;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 28];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 29];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.totDisDol;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 4];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 5];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netSales";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 14];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 15];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "cogs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 30];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 31];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netProfit";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 32];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 33];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                for (int i = discountIndex; i < discountList.size(); i++) {
//                    discountIndex = i;
                    Object[] discountRow = discountList.get(i);
                    int dyear = Integer.valueOf(String.valueOf(discountRow[1]));
                    int dperiod = Integer.valueOf(String.valueOf(discountRow[2]));
                    List<String> dcommon = getCommonColumnHeader(frequencyDivision, dyear, dperiod);
                    String dcommonColumn = dcommon.get(0);
                    if (pcommonColumn.equals(dcommonColumn)) {
                        newDiscount = String.valueOf(discountRow[dcol]);
                        if (!oldDiscount.equals(newDiscount)) {
                            String head = String.valueOf(discountRow[dcol]).replace(" ", StringUtils.EMPTY);
                            String commonColumn1 = Constant.totDisDol + head;
                            String commonColumn3 = Constant.totalRPU + head;
                            String commonColumn2 = Constant.totDisPer + head;
                            column = commonColumn1 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 1];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn1 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 2];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn2 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 3];
                                value = getFormattedValue(PER_TWO, value);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn2 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 4];
                                value = getFormattedValue(PER_TWO, value);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn3 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 5];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn3 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 6];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                        }
                        oldDiscount = newDiscount;
                    }
                }
                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                projectionTotalList.add(projDTO);
            }

        }
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        boolean leftFlag = false;
        for (String ob : periodList) {
            leftFlag = true;
            ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setGroup(projSelDTO.getPeriodListMap().get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
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
        if (value.contains(Constant.NULL)) {
            value = DASH.getConstant();
        } else {
            Double newValue = Double.valueOf(value);
            if (FORMAT.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / 100;
            }
            value = FORMAT.format(newValue);
        }
        return value;
    }

    public void getProjectionTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getProjectionTotal = = =");
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        List<Object[]> gtsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS", orderedArgs);
        if (gtsList != null) {
            getCustomizedProjectionTotal(gtsList, projSelDTO,false);
        }
        LOGGER.info("= = = Ending getProjectionTotal = = =");
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO,boolean isExcel) {
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
//        if (projSelDTO.isPpa()) {
            ppaPerDTO.setParent(0);
            ppaPerDTO.setGroup(PPA_DISCOUNT.getConstant());

            ppaRPUDTO.setParent(0);
            ppaRPUDTO.setGroup(PPA_DISCOUNT.getConstant());

            ppaDolDTO.setParent(0);
            ppaDolDTO.setGroup(PPA_DISCOUNT.getConstant());
//        }
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
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            int col = 5;
            if (frequencyDivision != 1) {
                col = col + 1;
            }
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;

                int year = Integer.valueOf(String.valueOf(obj[col - 1]));
                int period = Integer.valueOf(String.valueOf(obj[4]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);

                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    exFactoryDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    conSaleDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 2];
                    value = getFormattedValue(NUM_ZERO, value);
                    unitVolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 4];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    discountDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 6];
                    value = getFormattedValue(PER_TWO, value);
                    discountPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 8];
                    value = getFormattedValue(PER_TWO, value);
                    perExFactoryDTO.addStringProperties(column, value);
//                    if (projSelDTO.isPpa()) {
                        value = StringUtils.EMPTY + obj[col + 10];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaDolDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + 12];
                        value = getFormattedValue(PER_TWO, value);
                        ppaPerDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + 26];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaRPUDTO.addStringProperties(column, value);
//                    }
                    value = StringUtils.EMPTY + obj[col + 14];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netSaleDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 16];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    demandDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 18];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    inventoryDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 20];
                    value = getFormattedValue(PER_TWO, value);
                    perDemandDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 22];
                    value = getFormattedValue(PER_TWO, value);
                    perInventoryDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 28];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 30];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    cogsDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 32];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netprofitDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 36];
                    value = getFormattedValue(PER_TWO, value);
                    returnDiscountPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 38];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 34];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnDiscountDolDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    exFactoryDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    conSaleDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 3];
                    value = getFormattedValue(NUM_ZERO, value);
                    unitVolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 5];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    discountDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 7];
                    value = getFormattedValue(PER_TWO, value);
                    discountPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 9];
                    value = getFormattedValue(PER_TWO, value);
                    perExFactoryDTO.addStringProperties(column, value);
//                    if (projSelDTO.isPpa()) {
                        value = StringUtils.EMPTY + obj[col + 11];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaDolDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + 13];
                        value = getFormattedValue(PER_TWO, value);
                        ppaPerDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + 27];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaRPUDTO.addStringProperties(column, value);
//                    }
                    value = StringUtils.EMPTY + obj[col + 15];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netSaleDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 17];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    demandDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 19];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    inventoryDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 21];
                    value = getFormattedValue(PER_TWO, value);
                    perDemandDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 23];
                    value = getFormattedValue(PER_TWO, value);
                    perInventoryDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 29];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 31];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    cogsDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 33];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netprofitDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 37];
                    value = getFormattedValue(PER_TWO, value);
                    returnDiscountPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 39];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 35];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnDiscountDolDTO.addStringProperties(column, value);
                    columnList.remove(column);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            exFactoryDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            demandDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            inventoryDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            perExFactoryDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            perDemandDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            perInventoryDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            conSaleDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            unitVolDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            discountPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            totalRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            discountDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            netSaleDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            cogsDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            netprofitDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
//            if (projSelDTO.isPpa()) {
                ppaPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
                ppaRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
                ppaDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
//            }
            returnDiscountPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            returnRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            returnDiscountDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
        }
        projDTOList.add(exFactoryDTO);
        projDTOList.add(demandDTO);
        projDTOList.add(inventoryDTO);
        projDTOList.add(perExFactoryDTO);
        projDTOList.add(perDemandDTO);
        projDTOList.add(perInventoryDTO);
        projDTOList.add(conSaleDTO);
        projDTOList.add(unitVolDTO);
        projDTOList.add(discountPerDTO);
        projDTOList.add(totalRPUDTO);
        projDTOList.add(discountDolDTO);
        projDTOList.add(netSaleDTO);
        projDTOList.add(cogsDTO);
        projDTOList.add(netprofitDTO);
        if (!isExcel) {
//        if (projSelDTO.isReturns()) {
            projDTOList.add(returnDiscountPerDTO);
            projDTOList.add(returnRPUDTO);
            projDTOList.add(returnDiscountDolDTO);
//        }
//        if (projSelDTO.isPpa()) {
            projDTOList.add(ppaPerDTO);
            projDTOList.add(ppaRPUDTO);
            projDTOList.add(ppaDolDTO);
        }
//        }
        projectionTotalList.addAll(projDTOList);
        LOGGER.info("= = = Ending getCustomizedProjectionTotal = = =");
         return projectionTotalList;
    }

    public List<ProjectionResultsDTO> getProjectionTotalDiscounts(ProjectionSelectionDTO projSelDTO, Object[] orderedArgs) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        if (prjTotalDisDolDtoList.isEmpty() || prjTotalDisPerDtoList.isEmpty() || prjTotalRPUDtoList.isEmpty()) {
            Object[] orderedArgs1 = {orderedArgs[0], orderedArgs[1], orderedArgs[2], orderedArgs[3], orderedArgs[4], orderedArgs[5], 1,"PROGRAM"};
            List<Object[]> gtsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS_DISCOUNT", orderedArgs1);
            getCustomizedDiscountsProjectionTotal(gtsList, projSelDTO);
        }
        if (projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT)) {
            projDTOList = new ArrayList<ProjectionResultsDTO>(prjTotalDisDolDtoList);
        } else if (projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE)) {
            projDTOList = new ArrayList<ProjectionResultsDTO>(prjTotalDisPerDtoList);
        } else if (projSelDTO.getGroup().contains(Constant.Total_RPU)) {
            projDTOList = new ArrayList<ProjectionResultsDTO>(prjTotalRPUDtoList);
        }
        return projDTOList;
    }

    public void getCustomizedDiscountsProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        System.out.println("==========Inisde total custom============");
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<ProjectionResultsDTO> projDolDTOList = new ArrayList<ProjectionResultsDTO>();
        List<ProjectionResultsDTO> projRPUDTOList = new ArrayList<ProjectionResultsDTO>();
        List<ProjectionResultsDTO> projPerDTOList = new ArrayList<ProjectionResultsDTO>();
        List<String> discountList = new ArrayList<String>(projSelDTO.getDiscountNameList());
        //PPA
        List list1 = CommonLogic.getPPADiscountNameListPR(projSelDTO, Boolean.TRUE);
        if (list.get(0) != null) {
            discountList.addAll(list1);
        }
        String oldDiscountName = "old";
        String newDiscountName = "oldDiscountName";
        if (list != null && !list.isEmpty()) {
            ProjectionResultsDTO projDolDTO = new ProjectionResultsDTO();
            ProjectionResultsDTO projRPUDTO = new ProjectionResultsDTO();
            ProjectionResultsDTO projPerDTO = new ProjectionResultsDTO();
            int col = 3;
//            if (frequencyDivision != 1) {
//                col = col + 1;
//            }
            boolean add = false;
            List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
            columnList.remove(Constant.GROUP);
            for (int i = 0; i < list.size(); i++) {
                final Object[] obj = (Object[]) list.get(i);
                String column = StringUtils.EMPTY;
                int year = Integer.valueOf(String.valueOf(obj[1]));
                int period = Integer.valueOf(String.valueOf(obj[2]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                oldDiscountName = newDiscountName;
                newDiscountName = StringUtils.EMPTY + obj[3];
                if (!oldDiscountName.equals(newDiscountName)) {
                    add = false;
                    if (discountList.contains(newDiscountName)) {
                        add = true;
                        discountList.remove(newDiscountName);
                        projDolDTO = new ProjectionResultsDTO();
                        projRPUDTO = new ProjectionResultsDTO();
                        projPerDTO = new ProjectionResultsDTO();
                        projDolDTO.setParent(0);
                        projRPUDTO.setParent(0);
                        projPerDTO.setParent(0);
                        projDolDTO.setGroup(newDiscountName);
                        projRPUDTO.setGroup(newDiscountName);
                        projPerDTO.setGroup(newDiscountName);
                        projDolDTOList.add(projDolDTO);
                        projRPUDTOList.add(projRPUDTO);
                        projPerDTOList.add(projPerDTO);
                        for (String columns : columnList) {
                            projDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
                            projRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
                            projPerDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                        }
                        columnList = new ArrayList<String>(projSelDTO.getColumns());
                        columnList.remove(Constant.GROUP);
                    }

                }
                if (add) {
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        String value = StringUtils.EMPTY + obj[col + 1];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        projDolDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + 3];
                        value = getFormattedValue(PER_TWO, value);
                        projPerDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + 5];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        projRPUDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        String value = StringUtils.EMPTY + obj[col + 2];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        projDolDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + 4];
                        value = getFormattedValue(PER_THREE, value);
                        projPerDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + 6];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        projRPUDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                }
            }

        }
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        for (String ob : discountList) {
            ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setGroup(ob);
            ProjectionResultsDTO projDTO2 = new ProjectionResultsDTO();
            projDTO2.setParent(0);
            projDTO2.setProjectionTotal(1);
            projDTO2.setGroup(ob);
            ProjectionResultsDTO projDTO1 = new ProjectionResultsDTO();
            projDTO1.setParent(0);
            projDTO1.setProjectionTotal(1);
            projDTO1.setGroup(ob);
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
                projDTO1.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                projDTO2.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            }
            projDolDTOList.add(projDTO);
            projRPUDTOList.add(projDTO2);
            projPerDTOList.add(projDTO1);
        }
        prjTotalDisDolDtoList = new ArrayList<ProjectionResultsDTO>(projDolDTOList);
        prjTotalDisPerDtoList = new ArrayList<ProjectionResultsDTO>(projPerDTOList);
        prjTotalRPUDtoList = new ArrayList<ProjectionResultsDTO>(projRPUDTOList);

        }

    public ProjectionResultsDTO getNetSales(ProjectionSelectionDTO projSelDTO) {
        try {
            LOGGER.info("= = = Inside getNetSales = = = = = =");
            ProjectionResultsDTO netSalesDto = new ProjectionResultsDTO();
            String query = StringUtils.EMPTY;
            List<Object> list = null;
            List<ProjectionResultsDTO> projDTOList = null;
            projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
            query = CommonLogic.getCCPQueryPR(projSelDTO) + " \n" + getProjectionResultsNetSalesQuery(projSelDTO);
            list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
            projDTOList = getCustomizedProjectionResultsDiscount(list, projSelDTO, false,1);
            if (projDTOList != null && !projDTOList.isEmpty()) {
                netSalesDto = projDTOList.get(0);
            }
            netSalesDto.setGroup("Net Sales");
            netSalesDto.setParent(0);
            LOGGER.info("= = = Ending getNetSales = = =");
            return netSalesDto;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    private List<ProjectionResultsDTO> getCOGSandNetProfit(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getCOGSandNetProfit = = = = = =");
        try {
            String query = StringUtils.EMPTY;
            List<ProjectionResultsDTO> projDTOList = null;
            String cogsSelect = "DECLARE @FROM_DATE DATE\n"
                    + "     , @STARTFROM DATE\n"
                    + "     , @PROJECTION_DATE DATE\n"
                    + "     , @START_PERIOD_SID INT\n"
                    + "     , @END_PERIOD_SID INT\n"
                    + "SELECT TOP 1 @STARTFROM = DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, - 3, GETDATE())), 0)\n"
                    + "     , @PROJECTION_DATE = DATEADD(M, DATEDIFF(M, 0, DATEADD(DAY, - 1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))), 0)\n"
                    + "FROM PROJECTION_MASTER\n"
                    + "WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                    + "\n"
                    + "SELECT @START_PERIOD_SID = PERIOD_SID\n"
                    + "FROM PERIOD\n"
                    + "WHERE PERIOD_DATE = @STARTFROM\n"
                    + "\n"
                    + "SELECT @END_PERIOD_SID = PERIOD_SID\n"
                    + "FROM PERIOD\n"
                    + "WHERE PERIOD_DATE = @PROJECTION_DATE\n"
                    + "\n";
            query = cogsSelect + CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getCostQuery(projSelDTO);
            List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
            projDTOList = getCustomizedCOGSandNetProfit(list, projSelDTO);
            LOGGER.info("= = = Ending getCOGSandNetProfit = = = = = =");
            return projDTOList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }
    
     private List<ProjectionResultsDTO> getCOGSNetProfit(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getCOGSandNetProfit = = = = = =");
        try {
        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
            
            String query = StringUtils.EMPTY;
            List<ProjectionResultsDTO> projDTOList = null;
            String cogsSelect = " DECLARE @FROM_DATE        DATE,\n"
                    + "        @STARTFROM        DATE,\n"
                    + "        @PROJECTION_DATE  DATE,\n"
                    + "        @START_PERIOD_SID INT,\n"
                    + "        @END_PERIOD_SID   INT\n"
                    + "\n"
                    + " SELECT @STARTFROM = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0),-- Change (removed top 1)\n"
                    + "       @PROJECTION_DATE = Dateadd(M, Datediff(M, 0, Dateadd(DAY, -1, Dateadd(QQ, Datediff(QQ, 0, TO_DATE) + 1, 0))), 0)\n"
                    + " FROM   PROJECTION_MASTER\n"
                    + " WHERE  PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                    + "\n"
                    + " DECLARE @START_SID INT,\n"
                    + "        @END_SID   INT\n"
                    + "\n"
                    + " SELECT @START_SID = PERIOD_SID\n"
                    + " FROM   PERIOD\n"
                    + "   WHERE  PERIOD_DATE = '"+startDate+"'\n"
                    + "\n"
                    + " SELECT @END_SID = PERIOD_SID\n"
                    + " FROM   PERIOD\n"
                    + " WHERE  PERIOD_DATE = (SELECT Dateadd(MM, Datediff(MM, 0, '"+endDate+"'), 0))\n"
                    + "\n"
                    + " SELECT @START_PERIOD_SID = PERIOD_SID\n"
                    + " FROM   PERIOD\n"
                    + " WHERE  PERIOD_DATE = @STARTFROM\n"
                    + "\n"
                    + " SELECT @END_PERIOD_SID = PERIOD_SID\n"
                    + " FROM   PERIOD\n"
                    + " WHERE  PERIOD_DATE = @PROJECTION_DATE     \n";
                    
            query = cogsSelect + CommonLogic.getCCPQuery(projSelDTO, Boolean.FALSE) + " \n" + getCOGSNetProfitValue(projSelDTO);
//            System.out.println("Query:==================================================>"+query);
            List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
            projDTOList = getCustomizedCOGSandNetProfit(list, projSelDTO);
            LOGGER.info("= = = Ending getCOGSandNetProfit = = = = = =");
            return projDTOList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }
   
    private List getCustomizedCOGSandNetProfit(List list, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> resultList = new ArrayList<ProjectionResultsDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
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
                String column = StringUtils.EMPTY;
                int year = (obj[0] == null) ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = (obj[1] == null) ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                String commonColumn = common.get(0);
                int col = 2;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY;
                    value = StringUtils.EMPTY + obj[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    cogsDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netprofitDto.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + 1];
                    value = StringUtils.EMPTY + obj[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    cogsDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 3];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netprofitDto.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            cogsDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            netprofitDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
        }
        resultList.add(cogsDto);
        resultList.add(netprofitDto);

        return resultList;
    }

    public List<ProjectionResultsDTO> getProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getProjectionResults = = =   isReturn  " + projSelDTO.isReturns());
        LOGGER.info("= = = Inside getProjectionResults = = =   isPPA  " + projSelDTO.isPpa());
        int started = start;
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        String discList = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
        String freq = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 1) {
            freq = "ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            freq = "SEMI-ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 4) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == 12) {
            freq = "MONTHLY";
        }

        Object[] orderedArgs = {projSelDTO.getProjectionId(), freq, discList, "ASSUMPTIONS", projSelDTO.getSessionId(), projSelDTO.getUserId()};
        if (!projSelDTO.getGroup().startsWith(Constant.All)
                && !projSelDTO.getGroup().contains(Constant.Sales)
                && !projSelDTO.getGroup().contains(Constant.DISCOUNT)
                && !projSelDTO.getGroup().contains(Constant.PPA)) {
            if (projSelDTO.isIsTotal()) {
                if (projSelDTO.isIsProjectionTotal()) {
                    if (started == 0) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            ProjectionResultsDTO dto = new ProjectionResultsDTO();
                            dto.setGroup(Constant.PROJECTION_TOTAL);
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
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO exFactoryDto = projectionTotalList.get(0);
                                projDTOList.add(exFactoryDto);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (started == 2 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO demandDto = projectionTotalList.get(1);
                                projDTOList.add(demandDto);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (started == 3 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO inventoryDto = projectionTotalList.get(2);
                                projDTOList.add(inventoryDto);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (started == 4 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO perExFactoryDto = projectionTotalList.get(3);
                                projDTOList.add(perExFactoryDto);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (started == 5 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO perDemandDto = projectionTotalList.get(4);
                                projDTOList.add(perDemandDto);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (started == 6 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO perInventoryDto = projectionTotalList.get(5);
                                projDTOList.add(perInventoryDto);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant()))) {
                            if (started == 7) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO contractSalesDto = projectionTotalList.get(6);
                                    projDTOList.add(contractSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(UNITS.getConstant()))) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 8) || started == 7) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO unitVolDto = projectionTotalList.get(7);
                                    projDTOList.add(unitVolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) && !projSelDTO.getGroup().contains(Constant.Total_RPU)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 9) || started == 8) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO discountPerDto = projectionTotalList.get(8);
                                    projDTOList.add(discountPerDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE) && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 10) || started == 9) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO totalRPUDto = projectionTotalList.get(9);
                                    projDTOList.add(totalRPUDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE) && !projSelDTO.getGroup().contains(Constant.Total_RPU)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 11) || started == 10) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO discountDolDto = projectionTotalList.get(10);
                                    projDTOList.add(discountDolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 12) || started == 11) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO netSalesDto = projectionTotalList.get(11);
                                    projDTOList.add(netSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 13) || started == 12) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO cogsDto = projectionTotalList.get(12);
                                    projDTOList.add(cogsDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 14) || started == 13) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO netProfitDto = projectionTotalList.get(13);
                                    projDTOList.add(netProfitDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }

                    } else if (neededRecord > 0) {

                        ProjectionResultsDTO contractSalesDto = null;
                        ProjectionResultsDTO unitVolDto = null;
                        if ((salesUnits.equals(BOTH.getConstant()) && started < 2) || started < 1) {
                            List<ProjectionResultsDTO> contractSalesAndUnits = getContractSalesAndUnits(projSelDTO);
                            contractSalesDto = contractSalesAndUnits.get(0);
                            unitVolDto = contractSalesAndUnits.get(1);

                        }
                        if (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant())) {
                            if (started == 0) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    projDTOList.add(contractSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(UNITS.getConstant()))) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 1) || started == 0) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    projDTOList.add(unitVolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        List<ProjectionResultsDTO> list = getTotalDiscountLevels(projSelDTO, 0);
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) && !projSelDTO.getGroup().contains(Constant.Total_RPU)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 2) || started == 1) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO discountPerDtoList = list.get(0);
                                    projDTOList.add(discountPerDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 3) || started == 2) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO totalRPUDtoList = list.get(1);
                                    projDTOList.add(totalRPUDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE) && !projSelDTO.getGroup().contains(Constant.Total_RPU)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 4) || started == 3) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO discountDolarDtoList = list.get(2);
                                    projDTOList.add(discountDolarDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 5) || started == 4) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO netSalesDto = getNetSales(projSelDTO);
                                    projDTOList.add(netSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        List<ProjectionResultsDTO> cogsandNetProfit = getCOGSNetProfit(projSelDTO);
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 6) || started == 5) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO cogsDto = cogsandNetProfit.get(0);
                                    projDTOList.add(cogsDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == 7) || started == 6) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO netProfitDto = cogsandNetProfit.get(1);
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
//                    List<ProjectionResultsDTO> returnsList = getReturns(projSelDTO);
                    int ppaCount = + getPPACountValue(projSelDTO);

                    if (!projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) && !projSelDTO.getGroup().contains(Constant.Total_RPU)) {
                        if (started < (projSelDTO.getDiscountNameList().size() + ppaCount)) {
                            List<ProjectionResultsDTO> discountPerDtoList = new ArrayList<ProjectionResultsDTO>();
                            if (projSelDTO.isIsProjectionTotal()) {
                                discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs);
                            } else {
                                discountPerDtoList = getTotalDiscountLevels(projSelDTO, 2);
                            }
                            for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; k++) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                                    projDTOList.add(discountPerDtoList.get(k));
                                }
                                started++;
                                neededRecord--;
                            }
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size()+ ppaCount;
////                        if (neededRecord > 0) {
////                            if (projSelDTO.isPpa()) {
////                                if (projSelDTO.isIsProjectionTotal()) {
////                                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
////                                        if (projectionTotalList.isEmpty()) {
////                                            getProjectionTotal(orderedArgs, projSelDTO);
////                                        }
////                                        projDTOList.add(projectionTotalList.get(17));
////                                    }
////
////                                } else if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
////                                    ProjectionResultsDTO ppaDTO = getPPAPer(projSelDTO);
////                                    projDTOList.add(ppaDTO);
////                                }
////                                started++;
////                                neededRecord--;
////                            }
////                            mayBeAdded++;
////                        }
////                        if (neededRecord > 0) {
////                            if (projSelDTO.isReturns()) {
////                                if (projSelDTO.isIsProjectionTotal()) {
////                                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
////                                        if (projectionTotalList.isEmpty()) {
////                                            getProjectionTotal(orderedArgs, projSelDTO);
////                                        }
////                                        ProjectionResultsDTO netSalesDto = projectionTotalList.get(14);
////                                        projDTOList.add(netSalesDto);
////                                    }
////                                } else if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
////                                    ProjectionResultsDTO discountPerReturnDTO = returnsList.get(0);
////                                    projDTOList.add(discountPerReturnDTO);
////                                }
////                                started++;
////                                neededRecord--;
////                            }
////                            mayBeAdded++;
//                        }
                    }
                    if (!projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE)) {
                        if (started < (projSelDTO.getDiscountNameList().size()+ ppaCount)) {
                            List<ProjectionResultsDTO> discountPerDtoList = new ArrayList<ProjectionResultsDTO>();
                            if (projSelDTO.isIsProjectionTotal()) {
                                discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs);
                            } else {
                                discountPerDtoList = getTotalDiscountLevels(projSelDTO, 3);
                            }
                            for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; k++) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                                    projDTOList.add(discountPerDtoList.get(k));
                                }
                                started++;
                                neededRecord--;
                            }
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size()+ ppaCount;
//                        if (neededRecord > 0) {
//                            if (projSelDTO.isPpa()) {
//                                if (projSelDTO.isIsProjectionTotal()) {
//                                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
//                                        if (projectionTotalList.isEmpty()) {
//                                            getProjectionTotal(orderedArgs, projSelDTO);
//                                        }
//                                        projDTOList.add(projectionTotalList.get(18));
//                                    }
//
//                                } else if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
//                        getPPARPU(projSelDTO);
//                                    ProjectionResultsDTO ppaDTO = (ProjectionResultsDTO) getPPARPU(projSelDTO);
//                                    projDTOList.add(ppaDTO);
//                                }
//                                started++;
//                                neededRecord--;
//                            }
//                            mayBeAdded++;
//                        }
//                        if (neededRecord > 0) {
//                            if (projSelDTO.isReturns()) {
//                                if (projSelDTO.isIsProjectionTotal()) {
//                                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
//                                        if (projectionTotalList.isEmpty()) {
//                                            getProjectionTotal(orderedArgs, projSelDTO);
//                                        }
//                                        ProjectionResultsDTO netSalesDto = projectionTotalList.get(15);
//                                        projDTOList.add(netSalesDto);
//                                    }
//                                } else if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
//                                    ProjectionResultsDTO discountPerReturnDTO = returnsList.get(1);
//                                    projDTOList.add(discountPerReturnDTO);
//                                }
//                                started++;
//                                neededRecord--;
//                            }
//                            mayBeAdded++;
//                        }
                    }
                    if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE) && !projSelDTO.getGroup().contains(Constant.Total_RPU)) {
                        if (started < (projSelDTO.getDiscountNameList().size()+ ppaCount)) {
                            List<ProjectionResultsDTO> discountPerDtoList = new ArrayList<ProjectionResultsDTO>();
                            if (projSelDTO.isIsProjectionTotal()) {
                                discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs);
                            } else {
                                discountPerDtoList = getTotalDiscountLevels(projSelDTO, 1);
                            }
                            for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; k++) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                                    projDTOList.add(discountPerDtoList.get(k));
                                }
                                started++;
                                neededRecord--;
                            }
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size()+ ppaCount;
//                        if (neededRecord > 0) {
//                            if (projSelDTO.isPpa()) {
//                                if (projSelDTO.isIsProjectionTotal()) {
//                                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
//                                        if (projectionTotalList.isEmpty()) {
//                                            getProjectionTotal(orderedArgs, projSelDTO);
//                                        }
//                                        projDTOList.add(projectionTotalList.get(19));
//                                    }
//
//                                } else if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
//                                    ProjectionResultsDTO ppaDTO = getPPADollar(projSelDTO);
//                                    projDTOList.add(ppaDTO);
//                                }
//                                started++;
//                                neededRecord--;
//                            }
//                            mayBeAdded++;
//                        }
//                        if (neededRecord > 0) {
//                            if (projSelDTO.isReturns()) {
//                                if (projSelDTO.isIsProjectionTotal()) {
//                                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
//                                        if (projectionTotalList.isEmpty()) {
//                                            getProjectionTotal(orderedArgs, projSelDTO);
//                                        }
//                                        ProjectionResultsDTO netSalesDto = projectionTotalList.get(16);
//                                        projDTOList.add(netSalesDto);
//                                    }
//                                } else if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
//                                    ProjectionResultsDTO discountPerReturnDTO = returnsList.get(2);
//                                    projDTOList.add(discountPerReturnDTO);
//                                }
//                                started++;
//                                neededRecord--;
//                            }
//                            mayBeAdded++;
//                        }
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
                        projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
                    } else {
                        projectionDtoList = getProjectionPivot(projSelDTO);
                    }
                    for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(projectionDtoList.get(k));
                        }
                        started++;
                        neededRecord--;
                    }
                }
                mayBeAdded += projSelDTO.getPeriodList().size();
            }
        }
        if (!(projSelDTO.isIsCustomHierarchy() && projSelDTO.getCustomId() == 0)) {
            if (neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsFilter()) {
                if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                        && ((projSelDTO.isIsCustomHierarchy()) || (!projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
                        && !projSelDTO.getGroup().startsWith(Constant.All)
                        && !projSelDTO.getGroup().contains(Constant.Sales)
                        && !projSelDTO.getGroup().contains(Constant.DISCOUNT)
                        && !projSelDTO.getGroup().contains(Constant.PPA)) {

                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                        ProjectionResultsDTO dto = new ProjectionResultsDTO();
                        dto.setLevelNo(projSelDTO.getLevelNo());
                        dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
                        dto.setParentNode(projSelDTO.getParentNode());
                        dto.setGroup(projSelDTO.getGroupFilter());
                        dto.setLevelValue(projSelDTO.getLevelValue());
                        dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                        dto.setHierarchyNo(projSelDTO.getHierarchyNo());
                        if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                            dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                        } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                            dto.setProductHierarchyNo(dto.getHierarchyNo());
                            dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                        }
                        dto.setOnExpandTotalRow(1);
                        dto.setParent(1);
                        projDTOList.add(dto);
                    }
                    started++;

                    neededRecord--;
                } else {
                    int mayBeAddedRecord = started - mayBeAdded;
                    if (mayBeAddedRecord < 0) {
                        mayBeAddedRecord = 0;
                    }
                    projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                    List<ProjectionResultsDTO> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, started, projSelDTO);
                    projDTOList.addAll(nextLevelValueList);
                }
            }
        }
        LOGGER.info("= = = Ending getProjectionResults = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getConfiguredProjectionResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
        try {
            List<ProjectionResultsDTO> resultList = new ArrayList<>();
            if (!projSelDTO.isIsFilter() || (parentId instanceof ProjectionResultsDTO)) {
                projSelDTO.setYear(Constant.All);

                if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
                    projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + " and " + PROJECTIONS.getConstant());
                }
                if (parentId instanceof ProjectionResultsDTO) {
                    ProjectionResultsDTO parentDto = (ProjectionResultsDTO) parentId;
                    projSelDTO.setLevelNo(parentDto.getLevelNo());
                    projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                    projSelDTO.setLevelValue(parentDto.getLevelValue());
                    projSelDTO.setParentNode(parentDto.getParentNode());
                    projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                    if (parentDto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                        projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    } else if (parentDto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                        projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                        projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    }
                    else{
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
                    } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
                    }

                    projSelDTO.setGroup(StringUtils.EMPTY);
                    projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                    projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                    projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
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

            return null;
        }
    }

    public List<ProjectionResultsDTO> configureLevels(int start, int offset, int started, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        List<ProjectionResultsDTO> resultList = new ArrayList<ProjectionResultsDTO>();
        if (neededRecord > 0) {
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true);
            for (int i = 0; i < levelList.size() && neededRecord > 0; i++) {
                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + (started + i))) {

                    Leveldto levelDto = levelList.get(i);
                    ProjectionResultsDTO dto = new ProjectionResultsDTO();
                    dto.setLevelNo(levelDto.getLevelNo());
                    dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                    dto.setParentNode(levelDto.getParentNode());
                    dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setLevelValue(levelDto.getRelationshipLevelValue());
                    if (i == (levelList.size() - 1)) {
                        dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                        dto.setLevelValue(levelDto.getRelationshipLevelValue());
                    }
                    dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                    dto.setHierarchyNo(levelDto.getHierarchyNo());
                    if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                    } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
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
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + " and " + PROJECTIONS.getConstant());
            }
            if (parentId instanceof ProjectionResultsDTO) {
                ProjectionResultsDTO parentDto = (ProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (parentDto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
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
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
                }
                projSelDTO.setGroup(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
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
        if (!projSelDTO.getGroup().startsWith(Constant.All)
                && !projSelDTO.getGroup().contains(Constant.Sales)
                && !projSelDTO.getGroup().contains(Constant.DISCOUNT)
                && !projSelDTO.getGroup().contains(Constant.PPA)) {
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                projSelDTO.setPpa(Boolean.FALSE);
                projSelDTO.setReturns(Boolean.FALSE);
                if (projSelDTO.isIsTotal()) {
                    count = count + 7;
                    if (projSelDTO.isIsProjectionTotal()) {
                        count = count + 7;
                    }
                    if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant())) {
                        count++;
                    }
                }

                if (!projSelDTO.isIsTotal() && projSelDTO.isIsProjectionTotal()) {
                    System.out.println("======Inside !projSelDTO.isIsTotal() && projSelDTO.isIsProjectionTotal()==============");
                    count = count + projSelDTO.getDiscountNoList().size();
                    //PPA Count
                    String query = getPPACount(projSelDTO, Boolean.TRUE);
                    List list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                    if (list != null && !list.isEmpty()) {
                        Object ob = list.get(0);
                        count = count + Integer.valueOf(String.valueOf(ob));
                    }
                } else if (!projSelDTO.isIsTotal()) {
                    System.out.println("=============Inside !projSelDTO.isIsTotal()========== ");
                    String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + getDiscountCountForCurrentHierarchy(projSelDTO);
                    List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                    if (list != null && !list.isEmpty()) {
                        Object ob = list.get(0);
                        count = count + Integer.valueOf(String.valueOf(ob));;
                    }
                    //PPA Count
                    query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + getPPACount(projSelDTO, Boolean.FALSE);
                    list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                    if (list != null && !list.isEmpty()) {
                        Object ob = list.get(0);
                        count = count + Integer.valueOf(String.valueOf(ob));
                        System.out.println("count =============== " + count);
                    }
                }
            } else {
                count = count + projSelDTO.getPeriodList().size();
                if (projSelDTO.isIsProjectionTotal()) {
                    count++;
                }
            }
        }
        if (projSelDTO.isIsCustomHierarchy() && projSelDTO.getCustomId() == 0) {
            projSelDTO.setLevelCount(0);
        } else if (projSelDTO.isIsTotal() && isLevelsCount && !projSelDTO.isIsFilter()) {
            if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                    && ((projSelDTO.isIsCustomHierarchy()) || (!projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
                    && !projSelDTO.getGroup().startsWith(Constant.All)
                    && !projSelDTO.getGroup().contains(Constant.Sales)
                    && !projSelDTO.getGroup().contains(Constant.DISCOUNT)
                    && !projSelDTO.getGroup().contains(Constant.PPA)) {
                count = count + 1;
                projSelDTO.setGroupCount(true);
                projSelDTO.setLevelCount(1);
            } else {
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
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\" \n";

        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";

        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS,\n ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
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
            whereClause += " and B.RS_MODEL_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ") \n";
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
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\" \n";
        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }
        selectClause += "'PPA Discount' as DISCOUNTS,\n ";
        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = " PROJECTION_DETAILS E ,\n"
                + " \"PERIOD\" I, \n"
                + " @CCP CCP,\n"
                + " ST_NM_ACTUAL_SALES SNAS "
                + " where A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + "and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + ccpWhereCond
                + " and A.PERIOD_SID = I.PERIOD_SID \n"
                + " AND A.PROJECTION_DETAILS_SID = SNAS.PROJECTION_DETAILS_SID\n"
                + "                                                    AND A.PERIOD_SID = SNAS.PERIOD_SID\n"
                + "                                                    AND A.USER_ID = SNAS.USER_ID\n"
                + "                                                    AND A.SESSION_ID = SNAS.SESSION_ID"
                + periodFilter
                + whereClause
                + " group by " + groupBy;
        String customSqlFuture = " PROJECTION_DETAILS E ,\n"
                + " \"PERIOD\" I, \n"
                + " @CCP CCP,\n"
                + " ST_NM_SALES_PROJECTION SNSP "
                + " where A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + "and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + ccpWhereCond
                + " and A.PERIOD_SID = I.PERIOD_SID \n"
                +" AND A.PROJECTION_DETAILS_SID = SNSP.PROJECTION_DETAILS_SID\n" +
"                                                    AND A.PERIOD_SID = SNSP.PERIOD_SID\n" +
"                                                    AND A.USER_ID = SNSP.USER_ID\n" +
"                                                    AND A.SESSION_ID = SNSP.SESSION_ID"
                + periodFilter
                + whereClause
                + " group by " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_DISCOUNT_DOLLAR) as ACTUAL_SALES,\n"
                + " sum(A.ACTUAL_PROJECTION_SALES) as PROJECTION_SALES "
                + ", SUM(A.ACTUAL_PROJECTION_RATE) AS PPA_ACTUAL_RPU,\n"
                + "                                                    SUM(\n"
                + "                                                        CASE\n"
                + "                                                            WHEN A.ACTUAL_DISCOUNT_DOLLAR <> 0 THEN SNAS.ACTUAL_SALES\n"
                + "                                                            ELSE 0\n"
                + "                                                        END\n"
                + "                                                    ) AS SALES_ACTUALS,\n"
                + "                                                    SUM(\n"
                + "                                                        CASE\n"
                + "                                                            WHEN A.ACTUAL_DISCOUNT_DOLLAR <> 0 THEN SNAS.ACTUAL_UNITS\n"
                + "                                                            ELSE 0\n"
                + "                                                        END\n"
                + "                                                    ) AS UNITS_ACTUALS\n"
                + " from ST_NM_ACTUAL_PPA A,\n "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_DISCOUNT_DOLLAR) as PROJECTION_SALES , SUM(A.PROJECTION_RATE) AS PPA_PROJECTED_RPU,\n"
                + "                                                    SUM(\n"
                + "                                                        CASE\n"
                + "                                                            WHEN A.PROJECTION_DISCOUNT_DOLLAR <> 0 THEN SNSP.PROJECTION_SALES\n"
                + "                                                            ELSE 0\n"
                + "                                                        END\n"
                + "                                                    ) AS SALES_PROJECTED,\n"
                + "                                                    SUM(\n"
                + "                                                        CASE\n"
                + "                                                            WHEN A.PROJECTION_DISCOUNT_DOLLAR <> 0 THEN SNSP.PROJECTION_UNITS\n"
                + "                                                            ELSE 0\n"
                + "                                                        END\n"
                + "                                                    ) AS UNITS_PROJECTED \n"
                + " from ST_NM_PPA_PROJECTION A,\n "
                + customSqlFuture;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "'PPA Discount' as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES"
                + "              ,ISNULL(HISTORY.PPA_ACTUAL_RPU,0)AS PPA_ACTUAL_RPU\n"
                + "              ,ISNULL(FUTURE.PPA_PROJECTED_RPU,0)AS PPA_PROJECTED_RPU,ISNULL(\n"
                + "                                                HISTORY.SALES_ACTUALS,\n"
                + "                                                0\n"
                + "                                            ) AS SALES_ACTUALS,\n"
                + "                                            ISNULL(\n"
                + "                                                HISTORY.UNITS_ACTUALS,\n"
                + "                                                0\n"
                + "                                            ) AS UNITS_ACTUALS,\n"
                + "                                            ISNULL(\n"
                + "                                                FUTURE.SALES_PROJECTED,\n"
                + "                                                0\n"
                + "                                            ) AS SALES_PROJECTED,\n"
                + "                                            ISNULL(\n"
                + "                                                FUTURE.UNITS_PROJECTED,\n"
                + "                                                0\n"
                + "                                            ) AS UNITS_PROJECTED ";

        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsPPARPUQuery(ProjectionSelectionDTO projSelDTO) {

        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\" \n";
        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }
        
        selectClause += "CASE WHEN @PROGRAM_TYPE = 'PROGRAM' THEN rs_name ELSE 'Price Protection' END ppa_DISCOUNTS ,\n ";
        // To filter the data according to selected period
//        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = " ST_NM_PPA_PROJECTION_MASTER b\n"
                + " ON a.PROJECTION_DETAILS_SID = b.PROJECTION_DETAILS_SID INNER JOIN ST_NM_ACTUAL_SALES SNAS\n"
                + "                                                                    ON A.PROJECTION_DETAILS_SID = SNAS.PROJECTION_DETAILS_SID\n"
                + "                                                                AND A.PERIOD_SID = SNAS.PERIOD_SID\n"
                + "                                                                AND A.USER_ID = SNAS.USER_ID\n"
                + "                                                                AND A.SESSION_ID = SNAS.SESSION_ID\n"
                + "AND A.USER_ID=B.USER_ID\n"
                + "AND A.SESSION_ID=B.SESSION_ID\n"
                + "AND A.RS_MODEL_SID=B.RS_MODEL_SID INNER JOIN \"PERIOD\" I ON I.PERIOD_SID = A.PERIOD_SID \n"
                + " INNER JOIN RS_MODEL j"
                + " ON j.RS_MODEL_SID=a.rs_model_sid \n"
                + " where \n"
                + CommonLogic.getUserSessionQueryConditionPR(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + " AND I.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID "
                + " )a\n"
                + whereClause
                + " group by YEARs\n, periods\n,ppa_DISCOUNTS";
        String customSqlFuture = " ST_NM_PPA_PROJECTION_MASTER b\n"
                + " ON a.PROJECTION_DETAILS_SID = b.PROJECTION_DETAILS_SID INNER JOIN ST_NM_SALES_PROJECTION SNSP\n"
                + "ON A.PROJECTION_DETAILS_SID = SNSP.PROJECTION_DETAILS_SID\n"
                + "                                                                AND A.PERIOD_SID = SNSP.PERIOD_SID\n"
                + "                                                                AND A.USER_ID = SNSP.USER_ID\n"
                + "                                                                AND A.SESSION_ID = SNSP.SESSION_ID\n"
                + "AND A.USER_ID=B.USER_ID\n"
                + "AND A.SESSION_ID=B.SESSION_ID\n"
                + "AND A.RS_MODEL_SID=B.RS_MODEL_SID INNER JOIN \"PERIOD\" I ON I.PERIOD_SID = A.PERIOD_SID \n"
                + " INNER JOIN RS_MODEL j"
                + " ON j.RS_MODEL_SID=a.rs_model_sid \n"
                + " where \n"
                + CommonLogic.getUserSessionQueryConditionPR(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + " AND I.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID "
                + " )a\n"
                + whereClause
                + " group by YEARs\n, periods\n,ppa_DISCOUNTS";

        String historyQuery = "SELECT  YEARS  , PERIODS ,  ppa_discounts,"
                + " sum(ACTUAL_DISCOUNT_DOLLAR) as ACTUAL_SALES,\n"
                + " sum(ACTUAL_PROJECTION_SALES) as PROJECTION_SALES,SUM(ACTUAL_PROJECTION_RATE) AS PPA_ACTUAL_RPU,\n"
                + "                                                        SUM(SALES_ACTUALS) AS SALES_ACTUALS,\n"
                + "                                                        SUM(UNITS_ACTUALS) AS UNITS_ACTUALS  from (\n"
                + selectClause + " A.ACTUAL_DISCOUNT_DOLLAR , A.ACTUAL_PROJECTION_SALES\n, a.ACTUAL_PROJECTION_RATE,\n"
                + "                                                                CASE\n"
                + "                                                                    WHEN A.ACTUAL_DISCOUNT_DOLLAR <> 0 THEN SNAS.ACTUAL_SALES\n"
                + "                                                                    ELSE 0\n"
                + "                                                                END AS SALES_ACTUALS,\n"
                + "                                                                CASE\n"
                + "                                                                    WHEN A.ACTUAL_DISCOUNT_DOLLAR <> 0 THEN SNAS.ACTUAL_UNITS\n"
                + "                                                                    ELSE 0\n"
                + "                                                                END AS UNITS_ACTUALS"
                + " from @CCP CCP"
                + " INNER JOIN ST_NM_ACTUAL_PPA A ON\n "
                + "  A.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID INNER JOIN "
                + customSql;
        String futureQuery = "SELECT  YEARS  , PERIODS , ppa_discounts,"
                + " 0 as ACTUAL_SALES,\n"
                + " sum(PROJECTION_DISCOUNT_DOLLAR) as PROJECTION_SALES,SUM(PROJECTION_RATE) AS PPA_PROJECTED_RPU,\n"
                + "                                                        SUM(SALES_PROJECTED) AS SALES_PROJECTED,\n"
                + "                                                        SUM(UNITS_PROJECTED) AS UNITS_PROJECTED  from (\n"
                + selectClause + " A.PROJECTION_DISCOUNT_DOLLAR , A.PROJECTION_SALES\n, a.PROJECTION_RATE,\n"
                + "                                                                CASE\n"
                + "                                                                    WHEN A.PROJECTION_DISCOUNT_DOLLAR <> 0 THEN SNSP.PROJECTION_SALES\n"
                + "                                                                    ELSE 0\n"
                + "                                                                END AS SALES_PROJECTED,\n"
                + "                                                                CASE\n"
                + "                                                                    WHEN A.PROJECTION_DISCOUNT_DOLLAR <> 0 THEN SNSP.PROJECTION_UNITS\n"
                + "                                                                    ELSE 0\n"
                + "                                                                END AS UNITS_PROJECTED"
                + "   from @CCP CCP"
                + " INNER JOIN ST_NM_PPA_PROJECTION A ON\n "
                + " A.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID INNER JOIN "
                + customSqlFuture;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "coalesce(history.ppa_DISCOUNTS ,future.ppa_DISCOUNTS) AS DISCOUNTS"
                + ",Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES"
                + ",ISNULL(HISTORY.PPA_ACTUAL_RPU,0)AS PPA_ACTUAL_RPU\n"
                + "              ,ISNULL(FUTURE.PPA_PROJECTED_RPU,0)AS PPA_PROJECTED_RPU,\n"
                + "                                                ISNULL(\n"
                + "                                                    HISTORY.SALES_ACTUALS,\n"
                + "                                                    0\n"
                + "                                                ) AS SALES_ACTUALS,\n"
                + "                                                ISNULL(\n"
                + "                                                    HISTORY.UNITS_ACTUALS,\n"
                + "                                                    0\n"
                + "                                                ) AS UNITS_ACTUALS,\n"
                + "                                                ISNULL(\n"
                + "                                                    FUTURE.SALES_PROJECTED,\n"
                + "                                                    0\n"
                + "                                                ) AS SALES_PROJECTED,\n"
                + "                                                ISNULL(\n"
                + "                                                    FUTURE.UNITS_PROJECTED,\n"
                + "                                                    0\n"
                + "                                                ) AS UNITS_PROJECTED ";

        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond +" AND HISTORY.ppa_DISCOUNTS=FUTURE.ppa_DISCOUNTS)PPA ";
//          customQuery+="   \n" ;
        return customQuery;
    }

    public String getProjectionResultsSalesQuery(ProjectionSelectionDTO projSelDTO,Boolean period) {

        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\"\n" ;
        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = StringUtils.EMPTY;
        if(period){
            periodFilter = " AND I.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID ";
        }else{
            periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);
        }
//        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customSql = " \"PERIOD\" I ON I.PERIOD_SID = A.PERIOD_SID \n"
                + " where \n"
                + CommonLogic.getUserSessionQueryConditionPR(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + periodFilter
                + " group by " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                + " sum(A.HISTORY_PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, \n"
                + " sum(A.HISTORY_PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + "    from @CCP CCP"
                + " INNER JOIN  ST_NM_ACTUAL_SALES A ON \n "
                + " A.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID INNER JOIN "
                + customSql;
        String futureQuery = selectClause + " 0 as SALES_ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, \n"
                + " sum(A.PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + " from @CCP CCP"
                + " INNER JOIN ST_NM_SALES_PROJECTION A ON\n"
                + " A.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID INNER JOIN "
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
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.PPA_SMALL, "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'PPA Discount' as DISCOUNTS,\n";
        selectClause += " ACTUAL_RATE=Isnull(PPA.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n"
                + " PROJECTION_RATE=Isnull(PPA.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n";
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        customQuery = selectClause + " from (\n" + ppaQuery + "\n) PPA LEFT JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsPPARPU(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.PPA_SMALL, "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'PPA Discount' as DISCOUNTS , rs_name\n";
        selectClause += " ,PPA_ACTUAL_RPU=(Isnull(PPA.PPA_ACTUAL_RPU / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0))\n"
                + "       ,PPA_PROJECTED_RPU=(Isnull(PPA.PPA_PROJECTED_RPU / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0))";
        String ppaQuery = getProjectionResultsPPARPUQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        customQuery = selectClause + " from (\n" + ppaQuery +"and history.ppa_DISCOUNTS=future.ppa_DISCOUNTS"+ "\n)  PPA LEFT JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsTotalDiscountDolQuery(ProjectionSelectionDTO projSelDTO) {
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String customQuery = StringUtils.EMPTY;
        if (projSelDTO.isPpa()) {
            String selectClause = " select ";

            List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", Constant.PPA_SMALL, "on");
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
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "TODIS.DISCOUNTS, \n";
        selectClause += " ACTUAL_RATE=Isnull(TODIS.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n"
                + " PROJECTION_RATE=Isnull(TODIS.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS LEFT JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + "\n order by TODIS.DISCOUNTS";
        return customQuery;
    }

    public String getProjectionResultsTotalDiscountPerQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", Constant.PPA_SMALL, "on", true);
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Total Discount %' as DISCOUNTS, \n";
        selectClause += " ACTUAL_RATE=Isnull((Isnull(TODIS.ACTUAL_SALES, 0)";

//        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.ACTUAL_SALES, 0)";
//        }
        selectClause += ") / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n PROJECTION_RATE=Isnull((Isnull(TODIS.PROJECTION_SALES, 0)";
//        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.PROJECTION_SALES, 0)";
//        }

        selectClause += ") / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
//        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
//        }

        return customQuery;
    }

    public String getProjectionResultsNetSalesQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", Constant.PPA_SMALL, "on", true);
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Net Sales' as NETSALES, \n";
        selectClause += " ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-(Isnull(TODIS.ACTUAL_SALES, 0)";

//        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.ACTUAL_SALES, 0)";
//        }
        selectClause += ")),\n PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-(Isnull(TODIS.PROJECTION_SALES, 0)";
//        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.PROJECTION_SALES, 0)";
//        }

        selectClause += ")) \n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;

//        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
//        }
        return customQuery;
    }

    public String getCostQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + "CREATE TABLE #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID INT\n"
                + "     , CONTRACT_MASTER_SID INT\n"
                + "     , ITEM_MASTER_SID INT\n"
                + "     , PROJECTION_DETAILS_SID INT \n"
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
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", Constant.PPA_SMALL, "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "COGS_ACTUAL = ISNULL(SALE.COGS_ACTUAL, 0)\n"
                + ", COGS_PROJECTED = ISNULL(SALE.COGS_PROJECTED, 0) \n"
                + ", NET_PROFIT_ACTUAL = ((Isnull(SALE.SALES_ACTUAL_SALES, 0) - (Isnull(TODIS.ACTUAL_SALES, 0) \n";

//        if (projSelDTO.isPpa()) {
            selectClause += "+ Isnull(PPA.ACTUAL_SALES, 0)";
//        }
        selectClause += ")) - ISNULL(SALE.COGS_ACTUAL, 0))\n"
                + "  , NET_PROFIT_PROJECTED = ((Isnull(SALE.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0)";
//        if (projSelDTO.isPpa()) {
            selectClause += "+ ISNULL(PPA.PROJECTION_SALES, 0)";
//        }
         selectClause += ")) - ISNULL(SALE.COGS_PROJECTED, 0))";     
//                + "ISNULL(PPA.ACTUAL_SALES, 0))) - ISNULL(SALE.COGS_ACTUAL, 0))\n"
//                + "  , NET_PROFIT_PROJECTED = ((Isnull(SALE.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0)+ ISNULL(PPA.PROJECTION_SALES, 0))) - ISNULL(SALE.COGS_PROJECTED, 0))";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + cogsQuery + "\n) SALE \n" + finalWhereCond;

//        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
//        }
        return customQuery;
    }
   
    
    
    
    public String getCOGSNetProfitValue(ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDTOList = null;
        String selectedPeriod = "";
        String whereClause = StringUtils.EMPTY;
        String groupBy = "  P.\"YEAR\" \n";
        String groupQuery = "  YEARS \n";

        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";

        selectedPeriod += "P.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and P.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectedPeriod += "P.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", P.QUARTER";
            groupQuery += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectedPeriod += "P.SEMI_ANNUAL as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", P.SEMI_ANNUAL";
            groupQuery += ", PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectedPeriod += "'0' as PERIODS,\n ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;
            groupQuery += ", PERIODS";

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectedPeriod += "P.\"MONTH\" as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", P.\"MONTH\"";
            groupQuery += ", PERIODS";
        }

        String query
                = "   IF Object_id('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "  DROP TABLE #TEMP_CCP;\n"
                + " \n"
                + "   CREATE TABLE #TEMP_CCP\n"
                + "  (\n"
                + "     COMPANY_MASTER_SID     INT,\n"
                + "     CONTRACT_MASTER_SID    INT,\n"
                + "     ITEM_MASTER_SID        INT,\n"
                + "     CCP_DETAILS_SID        INT,\n"
                + "     PROJECTION_DETAILS_SID INT PRIMARY KEY,\n"
                + "     PROJECTION_MASTER_SID  INT\n"
                + "  )\n"
                + " \n"
                + "  INSERT INTO #TEMP_CCP\n"
                + "            (COMPANY_MASTER_SID,\n"
                + "             CONTRACT_MASTER_SID,\n"
                + "             ITEM_MASTER_SID,\n"
                + "             CCP_DETAILS_SID,\n"
                + "             PROJECTION_DETAILS_SID,\n"
                + "             PROJECTION_MASTER_SID)\n"
                + "  SELECT C.COMPANY_MASTER_SID,\n"
                + "       C.CONTRACT_MASTER_SID,\n"
                + "       C.ITEM_MASTER_SID,\n"
                + "       PD.CCP_DETAILS_SID,\n"
                + "       PD.PROJECTION_DETAILS_SID,\n"
                + "       PD.PROJECTION_MASTER_SID\n"
                + "FROM   CCP_DETAILS C\n"
                + "JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "WHERE  PD.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "       AND EXISTS (SELECT 1\n"
                + "                   FROM   @CCP CCP\n"
                + "                   WHERE  CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)\n"
                + " \n"
                + "  DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + " \n"
                + "INSERT INTO @ITEMID\n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM   ITEM_MASTER IM\n"
                + "WHERE  EXISTS (SELECT 1\n"
                + "               FROM   #TEMP_CCP A\n"
                + "               WHERE  PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                      AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID)\n"
                + " \n"
                + "SELECT          ISNULL(TODIS.YEARS, SALE.YEARS)     AS YEARS,\n"
                + "                ISNULL(TODIS.PERIODS, SALE.PERIODS) AS PERIODS,\n"
                + "                COGS_ACTUAL = ISNULL(SALE.COGS_ACTUAL, 0),\n"
                + "                COGS_PROJECTED = ISNULL(SALE.COGS_PROJECTED, 0),\n"
                + "                NET_PROFIT_ACTUAL = ( ( ISNULL(SALE.SALES_ACTUAL_SALES, 0) - ( ISNULL(TODIS.ACTUAL_SALES, 0)\n"
                + "                                                                               + ISNULL(PPA.ACTUAL_SALES, 0) ) ) - ISNULL(SALE.COGS_ACTUAL, 0) ),\n"
                + "                NET_PROFIT_PROJECTED = ( ( ISNULL(SALE.SALES_PROJECTION_SALES, 0) - ( ISNULL(TODIS.PROJECTION_SALES, 0)\n"
                + "                                                                                      + ISNULL(PPA.PROJECTION_SALES, 0) ) ) - ISNULL(SALE.COGS_PROJECTED, 0) )\n"
                + "FROM            (SELECT          ISNULL(HISTORY.YEARS, FUTURE.YEARS)                       AS YEARS,\n"
                + "                                 ISNULL(HISTORY.PERIODS, FUTURE.PERIODS)                   AS PERIODS,\n"
                + "                                 ISNULL(HISTORY.DISCOUNTS, FUTURE.DISCOUNTS)               AS DISCOUNTS,\n"
                + "                                 ISNULL(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES)         AS ACTUAL_SALES,\n"
                + "                                 ISNULL(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) AS PROJECTION_SALES\n"
                + "                 FROM            (SELECT \n"
                + "                                         \n" + selectedPeriod
                + "                                         'Total Discount $'             AS DISCOUNTS,\n"
                + "                                         Sum(A.ACTUAL_SALES)            AS ACTUAL_SALES,\n"
                + "                                         Sum(A.ACTUAL_PROJECTION_SALES) AS PROJECTION_SALES\n"
                + "                                  FROM   #TEMP_CCP T\n"
                + "                                  JOIN   ST_NM_ACTUAL_DISCOUNT A ON A.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID\n"
                + "                                  JOIN   PERIOD P ON P.PERIOD_SID = A.PERIOD_SID\n"
                + "                                  JOIN   @CCP CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                  WHERE    \n"
                + "                                         \n"+ CommonLogic.getUserSessionQueryConditionPR(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + "                                         AND   P.PERIOD_SID BETWEEN @START_SID AND @END_SID\n" 
                + "                                         AND A.RS_MODEL_SID IN (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " )\n"
                + "                                  GROUP  BY \n" + groupBy
                + "                                            ) HISTORY\n"
                + "                 FULL OUTER JOIN (SELECT \n" + selectedPeriod
                + "                                         \n"
                + "                                         'Total Discount $'      AS DISCOUNTS,\n"
                + "                                         0                       AS ACTUAL_SALES,\n"
                + "                                         Sum(A.PROJECTION_SALES) AS PROJECTION_SALES\n"
                + "                                  FROM   #TEMP_CCP T\n"
                + "                                  JOIN   ST_NM_DISCOUNT_PROJECTION A ON A.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID\n"
                + "                                  JOIN   PERIOD P ON P.PERIOD_SID = A.PERIOD_SID\n"
                + "                                  JOIN   @CCP CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                  WHERE  \n"
                + "                                         \n"+ CommonLogic.getUserSessionQueryConditionPR(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + "                                         AND  P.PERIOD_SID BETWEEN @START_SID AND @END_SID\n" 
                + "                                         AND A.RS_MODEL_SID IN ( " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " )\n"
                + "                                  GROUP  BY \n" + groupBy
                + "                                            ) FUTURE ON HISTORY.YEARS = FUTURE.YEARS\n"
                + "                                                             AND HISTORY.PERIODS = FUTURE.PERIODS\n"
                + "                                                             AND HISTORY.DISCOUNTS = FUTURE.DISCOUNTS) TODIS\n"
                + "FULL OUTER JOIN (SELECT    ISNULL(HISTORY.YEARS, FUTURE.YEARS)                                   AS YEARS,\n"
                + "                           ISNULL(HISTORY.PERIODS, FUTURE.PERIODS)                               AS PERIODS,\n"
                + "                           ISNULL(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES)         AS SALES_ACTUAL_SALES,\n"
                + "                           ISNULL(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES,\n"
                + "                           ISNULL(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS)                     AS ACTUAL_UNITS,\n"
                + "                           ISNULL(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS)             AS PROJECTION_UNITS,\n"
                + "                           ISNULL(HISTORY.COGS_ACTUAL, 0)                                        AS COGS_ACTUAL,\n"
                + "                           ISNULL(FUTURE.COGS_PROJECTED, 0)                                      AS COGS_PROJECTED\n"
                + "                 FROM      (SELECT YEARS,\n"
                + "                                   PERIODS,\n"
                + "                                   Sum(A.ACTUAL_SALES)             AS SALES_ACTUAL_SALES,\n"
                + "                                   Sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES,\n"
                + "                                   Sum(A.ACTUAL_UNITS)             AS ACTUAL_UNITS,\n"
                + "                                   Sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS,\n"
                + "                                   Sum(COGS_ACTUAL)                AS COGS_ACTUAL\n"
                + "                            FROM   (SELECT \n" + selectedPeriod
                + "                                           \n"
                + "                                           A.ACTUAL_SALES,\n"
                + "                                           A.HISTORY_PROJECTION_SALES,\n"
                + "                                           A.ACTUAL_UNITS,\n"
                + "                                           A.HISTORY_PROJECTION_UNITS,\n"
                + "                                           COGS_ACTUAL = ( ISNULL(A.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) )\n"
                + "                                    FROM   #TEMP_CCP T\n"
                + "                                    JOIN   ST_NM_ACTUAL_SALES A ON A.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID\n"
                + "                                    JOIN   PERIOD P ON P.PERIOD_SID = A.PERIOD_SID\n"
                + "                                    JOIN   @CCP CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                    JOIN   [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U ON U.ITEM_MASTER_SID = T.ITEM_MASTER_SID\n"
                + "                                                                                                                                  AND U.PERIOD_SID = P.PERIOD_SID\n"
                + "                                    WHERE  \n"
                + "                                           \n" + CommonLogic.getUserSessionQueryConditionPR(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + "                                           AND  P.PERIOD_SID BETWEEN @START_SID AND @END_SID \n"+" )A"
                + "                            GROUP BY \n" + groupQuery
                + "                                     ) HISTORY\n"
                + "                 FULL JOIN (SELECT YEARS,\n"
                + "                                   PERIODS,\n"
                + "                                   0                       AS SALES_ACTUAL_SALES,\n"
                + "                                   Sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES,\n"
                + "                                   0                       AS ACTUAL_UNITS,\n"
                + "                                   Sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS,\n"
                + "                                   Sum(COGS_PROJECTED)     AS COGS_PROJECTED\n"
                + "                            FROM   (SELECT \n" + selectedPeriod
                + "                                           \n"
                + "                                           A.PROJECTION_SALES,\n"
                + "                                           A.PROJECTION_UNITS,\n"
                + "                                           COGS_PROJECTED = ( ISNULL(A.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) )\n"
                + "                                    FROM   #TEMP_CCP T\n"
                + "                                    JOIN   ST_NM_SALES_PROJECTION A ON A.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID\n"
                + "                                    JOIN   PERIOD P ON P.PERIOD_SID = A.PERIOD_SID\n"
                + "                                    JOIN   @CCP CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                    JOIN   [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U ON U.ITEM_MASTER_SID = T.ITEM_MASTER_SID\n"
                + "                                                                                                                                  AND U.PERIOD_SID = P.PERIOD_SID\n"
                + "                                    WHERE  \n"
                + "                                           \n"+ CommonLogic.getUserSessionQueryConditionPR(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + "                                            AND P.PERIOD_SID BETWEEN @START_SID AND @END_SID \n" +" )P"
                + "                           GROUP  BY \n" + groupQuery
                + "                                      ) FUTURE ON HISTORY.YEARS = FUTURE.YEARS\n"
                + "                                                     AND HISTORY.PERIODS = FUTURE.PERIODS) SALE ON TODIS.YEARS = SALE.YEARS\n"
                + "                                                                                               AND TODIS.PERIODS = SALE.PERIODS\n"
                + "FULL OUTER JOIN (SELECT          ISNULL(HISTORY.YEARS, FUTURE.YEARS)                       AS YEARS,\n"
                + "                                 ISNULL(HISTORY.PERIODS, FUTURE.PERIODS)                   AS PERIODS,\n"
                + "                                 'PPA Discount'                                            AS DISCOUNTS,\n"
                + "                                 ISNULL(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES)         AS ACTUAL_SALES,\n"
                + "                                 ISNULL(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) AS PROJECTION_SALES,\n"
                + "                                 ISNULL(HISTORY.PPA_ACTUAL_RPU, 0)                         AS PPA_ACTUAL_RPU,\n"
                + "                                 ISNULL(FUTURE.PPA_PROJECTED_RPU, 0)                       AS PPA_PROJECTED_RPU\n"
                + "                 FROM            (SELECT \n" + selectedPeriod
                + "                                         \n"
                + "                                         'PPA Discount'                 AS DISCOUNTS,\n"
                + "                                         Sum(A.ACTUAL_DISCOUNT_DOLLAR)  AS ACTUAL_SALES,\n"
                + "                                         Sum(A.ACTUAL_PROJECTION_SALES) AS PROJECTION_SALES,\n"
                + "                                         Sum(A.ACTUAL_PROJECTION_RATE)  AS PPA_ACTUAL_RPU\n"
                + "                                  FROM   #TEMP_CCP T\n"
                + "                                  JOIN   ST_NM_ACTUAL_PPA A ON A.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID\n"
                + "                                  JOIN   PERIOD P ON P.PERIOD_SID = A.PERIOD_SID\n"
                + "                                  JOIN   @CCP CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                  WHERE \n"
                + "                                         \n"+ CommonLogic.getUserSessionQueryConditionPR(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + "                                         AND P.PERIOD_SID BETWEEN @START_SID AND @END_SID\n" 
                + "                                  GROUP  BY \n" + groupBy
                + "                                            ) HISTORY\n"
                + "                 FULL OUTER JOIN (SELECT \n" + selectedPeriod
                + "                                         \n"
                + "                                         'PPA Discount'                    AS DISCOUNTS,\n"
                + "                                         0                                 AS ACTUAL_SALES,\n"
                + "                                         Sum(A.PROJECTION_DISCOUNT_DOLLAR) AS PROJECTION_SALES,\n"
                + "                                         Sum(A.PROJECTION_RATE)            AS PPA_PROJECTED_RPU\n"
                + "                                  FROM   #TEMP_CCP T\n"
                + "                                  JOIN   ST_NM_PPA_PROJECTION A ON A.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID\n"
                + "                                  JOIN   PERIOD P ON P.PERIOD_SID = A.PERIOD_SID\n"
                + "                                  JOIN   @CCP CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                  WHERE  \n"
                + "                                         \n"+ CommonLogic.getUserSessionQueryConditionPR(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + "                                         AND  P.PERIOD_SID BETWEEN @START_SID AND @END_SID\n" 
                + "                                  GROUP  BY \n" + groupBy
                + "                                           ) FUTURE ON HISTORY.YEARS = FUTURE.YEARS\n"
                + "                                                             AND HISTORY.PERIODS = FUTURE.PERIODS) PPA ON TODIS.YEARS = PPA.YEARS\n"
                + "                                                                                                      AND TODIS.PERIODS = PPA.PERIODS";

        return query;
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
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", Constant.PPA_SMALL, "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Cost Of Goods Sold' as COGS, \n";

        selectClause += " COGS_ACTUAL = (ISNULL(SALE.ACTUAL_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n"
                + ", COGS_PROJECTED = (ISNULL(SALE.PROJECTION_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + "\n";
        customQuery += " FULL OUTER JOIN (" + cogsQuery;

//        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
//        }
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
        
        selectClause += "\n select ";
        String customQuery = StringUtils.EMPTY;
        String ppa_actuals = StringUtils.EMPTY;
        String ppa_projection = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", Constant.PPA_SMALL, "on", true);

        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        String orderBy = list.get(3);

//        if (projSelDTO.isPpa()) {
//            ppa_actuals = "+Isnull(PPA.ACTUAL_SALES, 0)";
//            ppa_projection = "+Isnull(PPA.PROJECTION_SALES, 0)";
//        }
//        selectClause += " SALE.SALES_ACTUAL_SALES as CONTRACT_ACTUAL_SALES \n"
//                + ", SALE.SALES_PROJECTION_SALES as CONTRACT_PROJECTION_SALES \n"
//                + ", SALE.ACTUAL_UNITS as CONTRACT_ACTUAL_UNITS \n"
//                + ", SALE.PROJECTION_UNITS as CONTRACT_PROJECTION_UNITS \n"
////                + ", TOTAL_ACTUAL_RATE=Isnull((Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + "+Isnull(SALE.RETURNS_ACTUAL, 0))/ NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 \n"
//                + ", TOTAL_ACTUAL_RATE=Isnull((Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + "/ NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0)+ ISNULL(\n"
//                + "                                            PPA.ACTUAL_SALES / NULLIF(\n"
//                + "                                                ppa.SALES_ACTUALS,\n"
//                + "                                                0\n"
//                + "                                            ),\n"
//                + "                                            0\n"
//                + "                                        ),0) * 100 \n"
////                + ", TOTAL_PROJECTION_RATE=Isnull(Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + "+Isnull(SALE.RETURNS_PROJECTED, 0) / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n"
//                + ", TOTAL_PROJECTION_RATE=Isnull((Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + " / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) + ISNULL(\n"
//                + "                                            PPA.PROJECTION_SALES / NULLIF(\n"
//                + "                                                ppa.SALES_PROJECTED,\n"
//                + "                                                0\n"
//                + "                                            ),\n"
//                + "                                            0\n"
//                + "                                        ) , 0) * 100 \n"
////                + ", TOTAL_ACTUAL_DOLAR=(Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + "+Isnull(SALE.RETURNS_ACTUAL, 0)) \n"
//                + ", TOTAL_ACTUAL_DOLAR=(Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + ")+ ISNULL(\n"
//                + "                                            PPA.ACTUAL_SALES,\n"
//                + "                                            0\n"
//                + "                                        ) \n"
////                + ", TOTAL_PROJECTION_DOLAR=(Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + "+Isnull(SALE.RETURNS_PROJECTED, 0)) \n"
//                + ", TOTAL_PROJECTION_DOLAR=(Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + ")+ ISNULL(\n"
//                + "                                            PPA.PROJECTION_SALES,\n"
//                + "                                            0\n"
//                + "                                        ) \n"
//                + ", NET_ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-(Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + " + ISNULL(\n"
//                + "                                                PPA.ACTUAL_SALES,\n"
//                + "                                                0\n"
//                + "                                            )" + "))  \n"
//                + ", NET_PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-(Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + " + ISNULL(\n"
//                + "                                                PPA.PROJECTION_SALES,\n"
//                + "                                                0\n"
//                + "                                            )" + ")) \n"
////                + ", TOTAL_ACTUAL_RPU = Isnull(( Isnull(TODIS.ACTUAL_SALES, 0) " + ppa_actuals + " +Isnull(SALE.RETURNS_ACTUAL, 0)) / NULLIF(SALE.ACTUAL_UNITS, 0), 0),\n"
//                + ", TOTAL_ACTUAL_RPU = Isnull(( Isnull(TODIS.ACTUAL_SALES, 0) " + ppa_actuals + "  / NULLIF(SALE.ACTUAL_UNITS, 0), 0)+ ISNULL(\n"
//                + "                                            PPA.ACTUAL_SALES / NULLIF(\n"
//                + "                                                ppa.UNITS_ACTUALS,\n"
//                + "                                                0\n"
//                + "                                            ),\n"
//                + "                                            0\n"
//                + "                                        ),0),\n"
////                + "       TOTAL_PROJECTION_RPU = Isnull(( Isnull(TODIS.PROJECTION_SALES, 0) " + ppa_projection + " +Isnull(SALE.RETURNS_PROJECTED, 0) ) / NULLIF(SALE.PROJECTION_UNITS, 0), 0)\n"
//                + "       TOTAL_PROJECTION_RPU = Isnull(( Isnull(TODIS.PROJECTION_SALES, 0) " + ppa_projection + "  / NULLIF(SALE.PROJECTION_UNITS, 0), 0)+ ISNULL(\n"
//                + "                                            PPA.PROJECTION_SALES / NULLIF(\n"
//                + "                                                ppa.UNITS_PROJECTED,\n"
//                + "                                                0\n"
//                + "                                            ),\n"
//                + "                                            0\n"
//                + "                                        ),0)\n"
//                + ", COGS_ACTUAL = ISNULL(SALE.COGS_ACTUAL, 0) \n"
//                + "       , COGS_PROJECTED = ISNULL(SALE.COGS_PROJECTED, 0) \n"
//                + "       , NET_PROFIT_ACTUAL = ((Isnull(SALE.SALES_ACTUAL_SALES, 0) - (Isnull(TODIS.ACTUAL_SALES, 0)" + ppa_actuals + "+ ISNULL(\n"
//                + "                                                    PPA.ACTUAL_SALES,\n"
//                + "                                                    0\n"
//                + "                                                )" + ")) - ISNULL(SALE.COGS_ACTUAL, 0))\n"
//                + "       , NET_PROFIT_PROJECTED = ((Isnull(SALE.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + " + ISNULL(\n"
//                + "                                                    PPA.PROJECTION_SALES,\n"
//                + "                                                    0\n"
//                + "                                                )" + ")) - ISNULL(SALE.COGS_PROJECTED, 0))\n"
//                + "";
//
//
////        if (projSelDTO.isPpa()) {
//            selectClause += ", PPA.ACTUAL_SALES AS PPA_ACTUAL_SALES \n"
//                    + ", PPA.PROJECTION_SALES AS PPA_PROJECTION_SALES \n"
//                    + ", PPA_ACTUAL_RATE=Isnull(PPA.ACTUAL_SALES / NULLIF( ppa.SALES_ACTUALS, 0), 0) * 100 \n"
//                    + ", PPA_PROJECTION_RATE=Isnull(PPA.PROJECTION_SALES / NULLIF(ppa.SALES_PROJECTED, 0), 0) * 100 "
//                    + ",PPA_ACTUAL_RPU=(Isnull(PPA.PPA_ACTUAL_RPU / NULLIF(ppa.UNITS_ACTUALS, 0), 0))\n"
//                    + "       ,PPA_PROJECTED_RPU=(Isnull(PPA.PPA_PROJECTED_RPU / NULLIF(ppa.UNITS_PROJECTED, 0), 0)) \n";

        selectClause+=" SALE.SALES_ACTUAL_SALES                                  AS CONTRACT_ACTUAL_SALES,\n" +
"       SALE.SALES_PROJECTION_SALES                              AS CONTRACT_PROJECTION_SALES,\n" +
"       SALE.ACTUAL_UNITS                                        AS CONTRACT_ACTUAL_UNITS,\n" +
"       SALE.PROJECTION_UNITS                                    AS CONTRACT_PROJECTION_UNITS,\n" +
"       TOTAL_ACTUAL_RATE=Isnull(( Isnull(TODIS.ACTUAL_SALES, 0) / NULLIF(SALE.SALES_ACTUAL_SALES, 0) ) + Isnull(PPA.ACTUAL_SALES / NULLIF(ppa.SALES_ACTUALS, 0), 0), 0) * 100,\n" +
"       TOTAL_PROJECTION_RATE=Isnull(( Isnull(TODIS.PROJECTION_SALES, 0) / NULLIF(SALE.SALES_PROJECTION_SALES, 0) ) + Isnull(PPA.PROJECTION_SALES / NULLIF(ppa.SALES_PROJECTED, 0), 0), 0) * 100,\n" +
"       TOTAL_ACTUAL_DOLAR=( Isnull(TODIS.ACTUAL_SALES, 0) ) + Isnull(PPA.ACTUAL_SALES, 0),\n" +
"       TOTAL_PROJECTION_DOLAR=( Isnull(TODIS.PROJECTION_SALES, 0) ) + Isnull(PPA.PROJECTION_SALES, 0),\n" +
"       NET_ACTUAL_SALES=( Isnull(SALE.SALES_ACTUAL_SALES, 0) - ( Isnull(TODIS.ACTUAL_SALES, 0)\n" +
"                                                                 + Isnull( PPA.ACTUAL_SALES, 0 ) ) ),\n" +
"       NET_PROJECTION_SALES=( Isnull(SALE.SALES_PROJECTION_SALES, 0) - ( Isnull(TODIS.PROJECTION_SALES, 0)\n" +
"                                                                         + Isnull( PPA.PROJECTION_SALES, 0 ) ) ),\n" +
"       TOTAL_ACTUAL_RPU = Isnull(( Isnull(TODIS.ACTUAL_SALES, 0) / NULLIF(SALE.ACTUAL_UNITS, 0) ) + Isnull(PPA.ACTUAL_SALES / NULLIF(ppa.UNITS_ACTUALS, 0), 0), 0),\n" +
"       TOTAL_PROJECTION_RPU = Isnull(( Isnull(TODIS.PROJECTION_SALES, 0) / NULLIF(SALE.PROJECTION_UNITS, 0) ) + Isnull(PPA.PROJECTION_SALES / NULLIF(ppa.UNITS_PROJECTED, 0), 0), 0),\n" +
"       COGS_ACTUAL = Isnull(SALE.COGS_ACTUAL, 0),\n" +
"       COGS_PROJECTED = Isnull(SALE.COGS_PROJECTED, 0),\n" +
"       NET_PROFIT_ACTUAL = ( ( Isnull(SALE.SALES_ACTUAL_SALES, 0) - ( Isnull(TODIS.ACTUAL_SALES, 0)\n" +
"                                                                      + Isnull( PPA.ACTUAL_SALES, 0 ) ) ) - Isnull(SALE.COGS_ACTUAL, 0) ),\n" +
"       NET_PROFIT_PROJECTED = ( ( Isnull(SALE.SALES_PROJECTION_SALES, 0) - ( Isnull(TODIS.PROJECTION_SALES, 0)\n" +
"                                                                             + Isnull( PPA.PROJECTION_SALES, 0 ) ) ) - Isnull(SALE.COGS_PROJECTED, 0) ),\n" +
"       PPA.ACTUAL_SALES                                         AS PPA_ACTUAL_SALES,\n" +
"       PPA.PROJECTION_SALES                                     AS PPA_PROJECTION_SALES,\n" +
"       PPA_ACTUAL_RATE=Isnull(PPA.ACTUAL_SALES / NULLIF(ppa.SALES_ACTUALS, 0), 0) * 100,\n" +
"       PPA_PROJECTION_RATE=Isnull(PPA.PROJECTION_SALES / NULLIF(ppa.SALES_PROJECTED, 0), 0) * 100,\n" +
"       PPA_ACTUAL_RPU=( Isnull(PPA.PPA_ACTUAL_RPU / NULLIF(ppa.UNITS_ACTUALS, 0), 0) ),\n" +
"       PPA_PROJECTED_RPU=( Isnull(PPA.PPA_PROJECTED_RPU / NULLIF(ppa.UNITS_PROJECTED, 0), 0) )";
        
//        }
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsReturnsQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);

        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + " \n";

//        if (projSelDTO.isPpa()) {
//        String finalWhereCond1 = list.get(2);
        customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n ON PPA.YEARS = SALE.YEARS\n"
                + "		AND PPA.PERIODS = SALE.PERIODS";
//        }
        customQuery += " order by " + orderBy;

        return customQuery;
    }

    public String getProjectionResultsReturnsQuery(ProjectionSelectionDTO projSelDTO) {
        String selectPeriod = StringUtils.EMPTY;
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
                + "       FROM (\n"
                + "              SELECT YEARS\n"
                + "                     , PERIODS\n"
                + "                     , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                     , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                     , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     , SUM(COGS_ACTUAL) AS COGS_ACTUAL\n"
                + "                     FROM\n"
                + "                     (SELECT I.\"YEAR\" AS YEARS\n"
                + "                     " + selectPeriod + "\n"
                + "                     , A.ACTUAL_SALES\n"
                + "                     , A.HISTORY_PROJECTION_SALES\n"
                + "                     , A.ACTUAL_UNITS\n"
                + "                     , A.HISTORY_PROJECTION_UNITS\n"
                + "                     , COGS_ACTUAL = (ISNULL(A.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
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

                + "                     INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U\n"
                + "                           ON U.ITEM_MASTER_SID = CC.ITEM_MASTER_SID\n"
                + "                                  AND A.PERIOD_SID = U.PERIOD_SID\n"
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
                + "              FROM (\n"
                + "                     SELECT I.\"YEAR\" AS YEARS\n"
                + "                           " + selectPeriod + "\n"
                + "                           , A.PROJECTION_SALES\n"
                + "                           , A.PROJECTION_UNITS\n"
                + "                           , COGS_PROJECTED = (ISNULL(A.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
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

                + "                     INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U\n"
                + "                           ON U.ITEM_MASTER_SID = CC.ITEM_MASTER_SID\n"
                + "                                  AND A.PERIOD_SID = U.PERIOD_SID\n"
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
        String selectPeriod = StringUtils.EMPTY;
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

                + "                     ,[DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U\n"
                + "              WHERE A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + StringUtils.EMPTY + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A") + "\n"
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

                + "                     ,[DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U\n"
                + "              WHERE A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + StringUtils.EMPTY + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A") + "\n"
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
        String selectPeriod = StringUtils.EMPTY;
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
                + "                     ISNULL(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS)             AS PROJECTION_UNITS\n"
         
                
//                + "                     ISNULL(HISTORY.ACTUAL_RATE, 0)                                        AS RETURNS_ACTUAL_PERCENT,\n"
//                + "                     ISNULL(FUTURE.PROJECTED_RATE, 0)                                      AS RETURNS_PROJECTED_PERCENT,\n"
//                + "                     ISNULL(HISTORY.RETURNS_ACTUAL, 0)                                     AS RETURNS_ACTUAL_AMOUNT,\n"
//                + "                     ISNULL(FUTURE.RETURNS_PROJECTED, 0)                                   AS RETURNS_PROJECTED_AMOUNT\n"
//              
                
                + "       FROM (\n"
                + "              SELECT  YEARS\n"
                + "                      ,  PERIODS \n"
                + "                     , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                     , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                     , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS \n"
                
                
//                + "                     ,ACTUAL_RATE = Avg(A.ACTUAL_RATE),\n"
//                + "                      RETURNS_ACTUAL = Sum(A.ACTUAL_SALES) * Avg(ACTUAL_RATE)\n"
                
                + "                     FROM\n"
                + "                     (SELECT I.\"YEAR\" AS YEARS\n"
                + "                     " + selectPeriod + "\n"
                + "                     , A.ACTUAL_SALES\n"
                + "                     , A.HISTORY_PROJECTION_SALES\n"
                + "                     , A.ACTUAL_UNITS\n"
                + "                     , A.HISTORY_PROJECTION_UNITS\n"
               
                
//                + "                     , TR.ACTUAL_RATE\n"
              
                
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
              
                
//                + "       LEFT JOIN (\n"
//                + "              SELECT A.ITEM_MASTER_sID\n"
//                + "                     , A.PERIOD_sID\n"
//                + "                     , A.ACTUAL_RATE\n"
//                + "                     , A.PROJECTED_RATE\n"
//                + "                     , CCPD.PROJECTION_DETAILS_SID\n"
//                + "              FROM #TEMP_RETURNS A\n"
//                + "              INNER JOIN #TEMP_CCPD CCPD\n"
//                + "                     ON A.ITEM_MASTER_SID = CCPD.ITEM_MASTER_SID\n"
//                + "              ) TR\n"
//                + "              ON TR.ITEM_MASTER_sID = CC.ITEM_MASTER_sID\n"
//                + "                     AND TR.PERIOD_SID = A.PERIOD_SID\n"
//                + "                     AND A.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID\n"
              
                
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
                + "                             Sum(P.PROJECTION_UNITS)  AS PROJECTION_UNITS\n"
               
                
//                + "                             PROJECTED_RATE = Avg(P.PROJECTED_RATE),\n"
//                + "                             RETURNS_PROJECTED = Sum(P.PROJECTION_SALES) * Avg(PROJECTED_RATE)"
               
                
                + "                     FROM\n"
                + "                     (SELECT I.\"YEAR\" AS YEARS\n"
                + "                     " + selectPeriod + "\n"
                + "                     , 0         AS SALES_ACTUAL_SALES,\n"
                + "                                     A.PROJECTION_SALES,\n"
                + "                                     0         AS ACTUAL_UNITS,\n"
                + "                                     A.PROJECTION_UNITS \n"
               
//                + "                                     TR.PROJECTED_RATE\n"
                
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
               
                
//                + "       LEFT JOIN (\n"
//                + "              SELECT A.ITEM_MASTER_sID\n"
//                + "                     , A.PERIOD_sID\n"
//                + "                     , A.ACTUAL_RATE\n"
//                + "                     , A.PROJECTED_RATE\n"
//                + "                     , CCPD.PROJECTION_DETAILS_SID\n"
//                + "              FROM #TEMP_RETURNS A\n"
//                + "              INNER JOIN #TEMP_CCPD CCPD\n"
//                + "                     ON A.ITEM_MASTER_SID = CCPD.ITEM_MASTER_SID\n"
//                + "              ) TR\n"
//                + "              ON TR.ITEM_MASTER_sID = CC.ITEM_MASTER_sID\n"
//                + "                     AND TR.PERIOD_SID = A.PERIOD_SID\n"
//                + "                     AND A.PROJECTION_DETAILS_SID = TR.PROJECTION_DETAILS_SID\n"
              
                
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
        String customQuery = StringUtils.EMPTY;
        String ppa_actuals = StringUtils.EMPTY;
        String ppa_projection = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", Constant.PPA_SMALL, "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        String orderBy = list.get(3);

//        if (projSelDTO.isPpa()) {
            ppa_actuals = "+Isnull(PPA.ACTUAL_SALES, 0)";
            ppa_projection = "+Isnull(PPA.PROJECTION_SALES, 0)";
//        }
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
            selectClause += ", PPA.ACTUAL_SALES AS PPA_ACTUAL_SALES \n"
                    + ", PPA.PROJECTION_SALES AS PPA_PROJECTION_SALES \n"
                    + ", PPA_ACTUAL_RATE=Isnull(PPA.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 \n"
                    + ", PPA_PROJECTION_RATE=Isnull(PPA.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100  \n";

//        }

        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + " \n";
        customQuery += " FULL OUTER JOIN (" + cogsQuery;

//        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
//        }
        customQuery += " order by " + orderBy;

        return customQuery;
    }

    public String getProjectionResultsDiscountsPivotQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
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

        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,Boolean.TRUE);
        String ppaQuery = getProjectionResultsPPARPUQuery(projSelDTO);
        customQuery = selectClause + " from (" + totalDiscountQuery + ") TODIS LEFT JOIN ("+ppaQuery+" LEFT JOIN (" + salesQuery + ") SALE " + finalWhereCond + " order by " + orderBy;
        System.out.println("customQuery for variable"+customQuery);
        projSelDTO.setIsTotal(true);
        return customQuery;
    }

    public String getProjectionResultsTotalTotalRPU(ProjectionSelectionDTO projSelDTO) {

        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", Constant.PPA_SMALL, "on", projSelDTO.isPpa());
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
        String totalDiscountQuery = getProjectionResultsDiscountsRPUQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        customQuery +="SELECT  DISTINCT YEARS, PERIODS,RS_NAME,ACTUAL_DOLLAR, PROJECTION_DOLLAR,ACTUAL_RATE,PROJECTION_RATE,ACTUAL_RPU, PROJECTION_RPU FROM  @DISCOUNT_PPA";
        customQuery +="CROSS APPLY ( VALUES(DISCOUNT,\n" +
"            DISC_ACTUAL_DOLLAR, DISC_PROJECTION_DOLLAR,DISC_ACTUAL_RATE,DISC_PROJECTION_RATE,\n DISC_ACTUAL_RPU,DISC_PROJECTED_RPU),\n(PPA,\n" +
"            PPA_ACTUAL_DOLLAR,\n" +
"            PPA_PROJECTION_DOLLAR,\n" +
"            PPA_ACTUAL_RATE,\n" +
"            PPA_PROJECTION_RATE,\n" +
"            PPA_ACTUAL_RPU,\n" +
"            PPA_PROJECTED_RPU)) CS (RS_NAME, ACTUAL_DOLLAR, PROJECTION_DOLLAR, ACTUAL_RATE, PROJECTION_RATE, ACTUAL_RPU, PROJECTION_RPU)";
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        return customQuery;
    }

    public String getProjectionResultsDiscountsRPUQuery(ProjectionSelectionDTO projSelDTO, String orderBy) {

        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\" \n";

        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";

        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS,\n ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
//        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "RS_NAME as DISCOUNTS \n";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            if (!projSelDTO.isIsTotal()) {
                discountTypeColumnName = " J.RS_NAME as DISCOUNTS \n";
//                groupBy += ", J.RS_NAME";
            }
//            whereClause += " and B.RS_MODEL_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ") \n";
        }
        selectClause += discountTypeColumnName + ", ";

        String customSql = "  ST_NM_DISCOUNT_PROJ_MASTER B ON \n";
//                + " PROJECTION_DETAILS E , \n"
//                + "\"PERIOD\" I, \n"
//                + " @CCP CCP ";
//        if (!projSelDTO.isIsTotal()) {
//            customSql += ", RS_MODEL J \n";
//        }
        customSql += " A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n"
                + "AND A.USER_ID=B.USER_ID\n"
                + "AND A.SESSION_ID=B.SESSION_ID AND A.RS_MODEL_SID=B.RS_MODEL_SID"
                //                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B")
                //                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + " INNER JOIN   PERIOD I ON I.PERIOD_SID = A.PERIOD_SID "
                + " INNER JOIN   RS_MODEL J ON J.RS_MODEL_SID = A.RS_MODEL_SID "
                + " WHERE  A.USER_ID=" + projSelDTO.getUserId() + "\n"
                + " AND A.SESSION_ID=" + projSelDTO.getSessionId() + "\n";
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            customSql += " and B.RS_MODEL_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")  \n";
        }
//        if (!projSelDTO.isIsTotal()) {
//            customSql += "and B.RS_MODEL_SID= J.RS_MODEL_SID \n";
//        }
        customSql +=  " AND I.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID "
                + whereClause + " group by " + "RS_NAME,"+groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as ACTUAL_SALES, \n"
                + " 0 as PROJECTION_SALES \n"
                +" FROM   @CCP CCP \n"
                + " INNER JOIN  ST_NM_ACTUAL_DISCOUNT A ON"
                +" A.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID"
                +" INNER JOIN "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES , \n"
                + " Sum(A.PROJECTION_SALES) as PROJECTION_SALES \n"
                 +" FROM   @CCP CCP \n"
                + " INNER JOIN ST_NM_DISCOUNT_PROJECTION A ON \n "
                 +" A.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID"
                +" INNER JOIN "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1) + " and HISTORY.DISCOUNTS=FUTURE.DISCOUNTS";
        String finalSelectClause = "select " + list.get(0) + " Isnull(HISTORY.DISCOUNTS, FUTURE.DISCOUNTS) as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES ";
        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond + orderBy;

        return customQuery;
    }

    public String getProjectionResultsDiscountsRPUQuery(ProjectionSelectionDTO projSelDTO, Boolean isVariable) {
        System.out.println("getProjectionResultsDiscountsRPUQuery==============");
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "TODIS.DISCOUNTS, TODIS.ACTUAL_SALES   AS DISC_ACTUAL_DOLLAR,TODIS.PROJECTION_SALES   AS DISC_PROJECTION_DOLLAR,\n";

        selectClause += " DISC_ACTUAL_RATE = Isnull(TODIS.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0)*100 ,\n"
                + " DISC_PROJECTION_RATE = Isnull(TODIS.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0)*100, \n"
                +"DISC_ACTUAL_RPU = (ISNULL(TODIS.ACTUAL_SALES / NULLIF(SALE.actual_units, 0), 0)), \n"
                +"DISC_PROJECTED_RPU = (ISNULL(TODIS.PROJECTION_SALES / NULLIF(SALE.projection_units, 0), 0))";
//        selectClause +=",RS_NAME    AS PPA,PPA.ACTUAL_SALES  AS PPA_ACTUAL_DOLLAR,PPA.PROJECTION_SALES   AS PPA_PROJECTION_DOLLAR,";
//        selectClause +="PPA_ACTUAL_RATE = ISNULL(PPA.ACTUAL_SALES / NULLIF(SALE.ACTUAL_UNITS, 0), 0), \n"
//                +"PPA_PROJECTION_RATE = ISNULL(PPA.PROJECTION_SALES / NULLIF(SALE.PROJECTION_UNITS, 0), 0),"
//                +"PPA_ACTUAL_RPU=( ISNULL(PPA.PPA_ACTUAL_RPU / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) ),"
//                +"PPA_PROJECTED_RPU=( ISNULL(PPA.PPA_PROJECTED_RPU / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) )";
        String totalDiscountQuery = getProjectionResultsDiscountsRPUQuery(projSelDTO, StringUtils.EMPTY);
//        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPARPUQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO, isVariable);
//        \n order by TODIS.DISCOUNTS";
        String unionSales = " SELECT Isnull(ppa.YEARS, SALE.YEARS) AS YEARS\n"
                + "	, Isnull(ppa.PERIODS, SALE.PERIODS) AS PERIODS\n"
                + "	, ppa.DISCOUNTS\n"
                + "	, ppa.ACTUAL_SALES AS DISC_ACTUAL_DOLLAR\n"
                + "	, ppa.PROJECTION_SALES AS DISC_PROJECTION_DOLLAR\n"

                + "	, DISC_ACTUAL_RATE = Isnull(PPA.ACTUAL_SALES / NULLIF(PPA.SALES_ACTUALS, 0), 0)*100 \n"
                + "	, DISC_PROJECTION_RATE = Isnull(PPA.PROJECTION_SALES / NULLIF(PPA.SALES_PROJECTED, 0), 0)*100 \n"
                + "	, DISC_ACTUAL_RPU = (ISNULL(PPA.ACTUAL_SALES / NULLIF(PPA.UNITS_ACTUALS, 0), 0))\n"
                + "	, DISC_PROJECTED_RPU = (ISNULL(PPA.PROJECTION_SALES / NULLIF(PPA.UNITS_PROJECTED, 0), 0)) from(";
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS INNER JOIN (\n" + salesQuery + "\n )SALE ON TODIS.YEARS = SALE.YEARS\n"
                + "		AND TODIS.PERIODS = SALE.PERIODS UNION ALL " + unionSales + " \n" + ppaQuery + " INNER JOIN (\n" + salesQuery + "\n) SALE ON PPA.YEARS = SALE.YEARS\n" +
"		AND PPA.PERIODS = SALE.PERIODS";
//       customQuery +=" SELECT  DISTINCT YEARS, PERIODS,RS_NAME,ACTUAL_DOLLAR, PROJECTION_DOLLAR,ACTUAL_RATE,PROJECTION_RATE,ACTUAL_RPU, PROJECTION_RPU FROM  @DISCOUNT_PPA";
//       customQuery +=" CROSS APPLY ( VALUES(DISCOUNT,\n" +
//"            DISC_ACTUAL_DOLLAR, DISC_PROJECTION_DOLLAR,DISC_ACTUAL_RATE,DISC_PROJECTION_RATE,\n DISC_ACTUAL_RPU,DISC_PROJECTED_RPU),\n(PPA,\n" +
//"            PPA_ACTUAL_DOLLAR,\n" +
//"            PPA_PROJECTION_DOLLAR,\n" +
//"            PPA_ACTUAL_RATE,\n" +
//"            PPA_PROJECTION_RATE,\n" +
//"            PPA_ACTUAL_RPU,\n" +
//"            PPA_PROJECTED_RPU)) CS (RS_NAME, ACTUAL_DOLLAR, PROJECTION_DOLLAR, ACTUAL_RATE, PROJECTION_RATE, ACTUAL_RPU, PROJECTION_RPU) ORDER BY RS_NAME, YEARS ";
        customQuery+=" SELECT * FROM @DISCOUNT_PPA order by DISCOUNT, YEARS,PERIODS";
        System.out.println("getProjectionResultsDiscountsRPUQuery --");
        return customQuery;
    }

    public String getFormatTwoDecimalValue(DecimalFormat FORMAT, String value, String appendChar) {
        if (value.contains(Constant.NULL)) {
            value = "...";
        } else if (CURRENCY.equals(appendChar)) {
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
                + "       , PROJECTION_DETAILS_SID INT \n"
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
                + "\n";
               
                
//                + "IF Object_id('TEMPDB..#TEMP_CCPD') IS NOT NULL\n"
//                + "       DROP TABLE #TEMP_CCPD\n"
//                + "\n"
//                + "CREATE TABLE #TEMP_CCPD (\n"
//                + "       COMPANY_MASTER_SID INT\n"
//                + "       , CONTRACT_MASTER_SID INT\n"
//                + "       , ITEM_MASTER_SID INT\n"
//                + "       , PROJECTION_DETAILS_SID INT\n"
//                + "       , PROJECTION_MASTER_SID INT\n"
//                + "       )\n"
//                + "\n"
//                + "INSERT INTO #TEMP_CCPD (\n"
//                + "       COMPANY_MASTER_SID\n"
//                + "       , CONTRACT_MASTER_SID\n"
//                + "       , ITEM_MASTER_SID\n"
//                + "       , PROJECTION_DETAILS_SID\n"
//                + "       , PROJECTION_MASTER_SID\n"
//                + "       )\n"
//                + "SELECT DISTINCT COMPANY_MASTER_SID\n"
//                + "       , CONTRACT_MASTER_SID\n"
//                + "       , ITEM_MASTER_SID\n"
//                + "       , PROJECTION_DETAILS_SID\n"
//                + "       , PROJECTION_MASTER_SID\n"
//                + "FROM (\n"
//                + "       SELECT COMPANY_MASTER_SID\n"
//                + "              , T_CCP.CONTRACT_MASTER_SID\n"
//                + "              , T_CCP.ITEM_MASTER_SID\n"
//                + "              , PROJECTION_DETAILS_SID\n"
//                + "              , PROJECTION_MASTER_SID\n"
//                + "              , RS_ID\n"
//                + "              , RS_TYPE\n"
//                + "       FROM #TEMP_CCP T_CCP\n"
//                + "       INNER JOIN RS_CONTRACT RS\n"
//                + "              ON T_CCP.CONTRACT_MASTER_SID = RS.CONTRACT_MASTER_SID\n"
//                + "       INNER JOIN RS_CONTRACT_DETAILS RSC\n"
//                + "              ON RS.RS_CONTRACT_SID = RSC.RS_CONTRACT_SID\n"
//                + "                     AND T_CCP.ITEM_MASTER_SID = RSC.ITEM_MASTER_SID\n"
//                + "       WHERE EXISTS (\n"
//                + "                     SELECT 1\n"
//                + "                     FROM CFP_CONTRACT CF\n"
//                + "                     INNER JOIN CFP_CONTRACT_DETAILS CFD\n"
//                + "                           ON CF.CFP_CONTRACT_SID = CFD.CFP_CONTRACT_SID\n"
//                + "                                  AND T_CCP.COMPANY_MASTER_SID = CFD.COMPANY_MASTER_SID\n"
//                + "                                  AND RS.CFP_CONTRACT_SID = CF.CFP_CONTRACT_SID\n"
//                + "                                  AND CF.CONTRACT_MASTER_SID = T_CCP.CONTRACT_MASTER_SID\n"
//                + "                     )\n"
//                + "       ) R\n"
//                + "INNER JOIN HELPER_TABLE HT\n"
//                + "       ON R.RS_TYPE = HT.HELPER_TABLE_SID\n"
//                + "WHERE HT.DESCRIPTION = 'Returns'\n"
//                + "\n"
//                + "DECLARE @ITEM_ID [DBO].[UDT_ITEM]\n"
//                + "\n"
//                + "INSERT INTO @ITEM_ID\n"
//                + "SELECT DISTINCT ITEM_MASTER_SID\n"
//                + "FROM #TEMP_CCPD A\n"
//                + "\n"
//                + "DECLARE @COUNT INT\n"
//                + "\n"
//                + "IF Object_id('TEMPDB..#ITEM') IS NOT NULL\n"
//                + "       DROP TABLE #ITEM\n"
//                + "\n"
//                + "CREATE TABLE #ITEM (\n"
//                + "       ID INT IDENTITY(1, 1)\n"
//                + "       , ITEM_MASTER_SID INT\n"
//                + "       )\n"
//                + "\n"
//                + "INSERT INTO #ITEM (ITEM_MASTER_SID)\n"
//                + "SELECT ITEM_MASTER_SID\n"
//                + "FROM @ITEMID\n"
//                + "\n"
//                + "SET @COUNT = @@ROWCOUNT\n"
//                + "\n"
//                + "DECLARE @I INT = 1\n"
//                + "DECLARE @ITEM INT\n"
//                + "\n"
//                + "IF Object_id('TEMPDB..#TEMP_RETURNS') IS NOT NULL\n"
//                + "       DROP TABLE #TEMP_RETURNS\n"
//                + "\n"
//                + "CREATE TABLE #TEMP_RETURNS (\n"
//                + "       ITEM_MASTER_SID INT\n"
//                + "       , RETURNS_DETAILS_SID INT\n"
//                + "       , PERIOD_SID INT\n"
//                + "       , ACTUAL_RATE NUMERIC(22, 6)\n"
//                + "       , PROJECTED_RATE NUMERIC(22, 6)\n"
//                + "       )\n"
//                + "\n"
//                + "WHILE (@I <= @COUNT)\n"
//                + "BEGIN\n"
//                + "       SET @ITEM = (\n"
//                + "                     SELECT ITEM_MASTER_SID\n"
//                + "                     FROM #ITEM\n"
//                + "                     WHERE id = @I\n"
//                + "                     );\n"
//                + "\n"
//                + "       WITH ITEM_PROJ_DETAILS\n"
//                + "       AS (\n"
//                + "              SELECT ROW_NUMBER() OVER (\n"
//                + "                           PARTITION BY ITEM_MASTER_SID ORDER BY LASTEST_DATE DESC\n"
//                + "                           ) RN\n"
//                + "                     , ITEM_MASTER_SID\n"
//                + "                     , PM.PROJECTION_MASTER_SID\n"
//                + "                     , RETURNS_DETAILS_SID\n"
//                + "              FROM RETURNS_DETAILS RD\n"
//                + "              INNER JOIN PROJECTION_MASTER PM\n"
//                + "                     ON RD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
//                + "              CROSS APPLY (\n"
//                + "                     VALUES (MODIFIED_DATE)\n"
//                + "                           , (CREATED_DATE)\n"
//                + "                     ) CS(LASTEST_DATE)\n"
//                + "              WHERE SAVE_FLAG = 1\n"
//                + "                     AND ITEM_MASTER_SID = @ITEM\n"
//                + "              )\n"
//                + "       INSERT INTO #TEMP_RETURNS (\n"
//                + "              ITEM_MASTER_SID\n"
//                + "              , RETURNS_DETAILS_SID\n"
//                + "              , PERIOD_SID\n"
//                + "              , ACTUAL_RATE\n"
//                + "              , PROJECTED_RATE\n"
//                + "              )\n"
//                + "       SELECT @ITEM AS ITEM_MASTER_SID\n"
//                + "              , COALESCE(R_ACTUALS.RETURNS_DETAILS_SID, R_PROJ.RETURNS_DETAILS_SID) AS RETURNS_DETAILS_SID\n"
//                + "              , COALESCE(R_ACTUALS.PERIOD_SID, R_PROJ.PERIOD_SID) AS PERIOD_SID\n"
//                + "              , R_ACTUALS.ACTUAL_RETURN_PERCENT\n"
//                + "              , R_PROJ.PROJECTED_RETURN_PERCENT\n"
//                + "       FROM (\n"
//                + "              SELECT RETURNS_DETAILS_SID\n"
//                + "                     , PERIOD_SID\n"
//                + "                     , ACTUAL_RETURN_PERCENT\n"
//                + "              FROM RETURNS_ACTUALS NAP\n"
//                + "              WHERE EXISTS (\n"
//                + "                           SELECT 1\n"
//                + "                           FROM RETURNS_DETAILS RD\n"
//                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
//                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
//                + "AND RD.RETURNS_DETAILS_SID = NAP.RETURNS_DETAILS_SID \n"
//                + "                           WHERE IMPD.RN = 1\n"
//                + "                           )\n"
//                + "              ) R_ACTUALS\n"
//                + "       FULL JOIN (\n"
//                + "              SELECT RETURNS_DETAILS_SID\n"
//                + "                     , PERIOD_SID\n"
//                + "                     , PROJECTED_RETURN_PERCENT\n"
//                + "              FROM RETURNS_PROJ_DETAILS NPP\n"
//                + "              WHERE EXISTS (\n"
//                + "                           SELECT 1\n"
//                + "                           FROM RETURNS_DETAILS RD\n"
//                + "                           INNER JOIN ITEM_PROJ_DETAILS IMPD\n"
//                + "                                  ON IMPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID\n"
//                + "AND RD.RETURNS_DETAILS_SID = NPP.RETURNS_DETAILS_SID \n"
//                + "                           WHERE IMPD.RN = 1\n"
//                + "                           )\n"
//                + "              ) R_PROJ\n"
//                + "              ON R_ACTUALS.RETURNS_DETAILS_SID = R_PROJ.RETURNS_DETAILS_SID\n"
//                + "                     AND R_ACTUALS.PERIOD_SID = R_PROJ.PERIOD_SID\n"
//                + "\n"
//                + "       SET @I = @I + 1\n"
//                + "END";
        
        
        
        selectClause += " SELECT ";
        String customQuery = StringUtils.EMPTY;

        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", Constant.PPA_SMALL, "on",true);
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Total Discount %'                  AS DISCOUNTS,\n"
                + "          ACTUAL_RATE = ISNULL(( ISNULL(TODIS.ACTUAL_SALES, 0) ";
        
        
//        if (projSelDTO.isPpa()) {
//            selectClause += "+Isnull(PPA.ACTUAL_SALES, 0)";
//        }
//         + ISNULL(SALE.RETURNS_ACTUAL_PERCENT, 0)
        selectClause += "                                 ) / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0)*100+ ISNULL(\n"
                + "                                        Isnull(\n"
                + "                                            PPA.ACTUAL_SALES,\n"
                + "                                            0\n"
                + "                                        ) / NULLIF(\n"
                + "                                            PPA.SALES_ACTUALS,\n"
                + "                                            0\n"
                + "                                        ),\n"
                + "                                        0\n"
                + "                                    ) * 100,\n"
                + "          PROJECTION_RATE = ISNULL(( ISNULL(TODIS.PROJECTION_SALES, 0)";
       
        
//        if (projSelDTO.isPpa()) {
//            selectClause += "+Isnull(PPA.PROJECTION_SALES, 0)";
//        }
//          + ISNULL(SALE.RETURNS_PROJECTED_PERCENT, 0)
        
        selectClause += "                                     ) / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0)*100 + ISNULL(\n"
                + "                                        ISNULL(\n"
                + "                                            PPA.PROJECTION_SALES,\n"
                + "                                            0\n"
                + "                                        ) / NULLIF(\n"
                + "                                            PPA.SALES_PROJECTED,\n"
                + "                                            0\n"
                + "                                        ),\n"
                + "                                        0\n"
                + "                                    ) * 100,\n"
                + "          'Total Discount $'                  AS DISCOUNTS,\n"
                + "          ACTUAL_AMOUNT = ( ISNULL(TODIS.ACTUAL_SALES, 0)\n";
       
        
//        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.ACTUAL_SALES, 0)";
//        }
//          + ISNULL(SALE.RETURNS_ACTUAL_PERCENT, 0)
        
        selectClause += "                            ),\n"
                + "          PROJECTION_AMOUNT =( ISNULL(TODIS.PROJECTION_SALES, 0)\n";
        
        
//        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.PROJECTION_SALES, 0)";
//        }
//       + ISNULL(SALE.RETURNS_PROJECTED_PERCENT, 0)
        
        selectClause += "                               ),\n"
                + "          'RPU'                               AS DISCOUNTS,\n";
//                + "          ACTUAL_RPU = ( ISNULL(SALE.RETURNS_ACTUAL_AMOUNT";
        
        
//        if (projSelDTO.isPpa()) {
//            selectClause += "+Isnull(PPA.PPA_ACTUAL_RPU, 0)";
//        }
       
        
        selectClause += " ACTUAL_RPU = ((ISNULL(TODIS.ACTUAL_SALES, 0) ) / NULLIF(SALE.ACTUAL_UNITS, 0) )+ ISNULL(\n"
                + "                                        Isnull(\n"
                + "                                            PPA.ACTUAL_SALES,\n"
                + "                                            0\n"
                + "                                        ) / NULLIF(\n"
                + "                                            PPA.UNITS_ACTUALS,\n"
                + "                                            0\n"
                + "                                        ),\n"
                + "                                        0\n"
                + "                                    ),\n";
//                + "          PROJECTION_RPU =( ISNULL(SALE.RETURNS_PROJECTED_AMOUNT ";
       
        
//        if (projSelDTO.isPpa()) {
//            selectClause += "+Isnull(PPA.PPA_PROJECTED_RPU, 0)";
//        }
       
                selectClause += "PROJECTION_RPU = ((ISNULL(TODIS.PROJECTION_SALES, 0))/ NULLIF(SALE.PROJECTION_UNITS, 0))+ ISNULL(\n"
                + "                                        Isnull(\n"
                + "                                            PPA.PROJECTION_SALES,\n"
                + "                                            0\n"
                + "                                        ) / NULLIF(\n"
                + "                                            PPA.UNITS_PROJECTED,\n"
                + "                                            0\n"
                + "                                        ),\n"
                + "                                        0\n"
                + "                                    )";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String cogsQuery = getProjectionResultsTotalDiscountsQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + cogsQuery + "\n) SALE \n" + finalWhereCond + "\n";
//        if (projSelDTO.isPpa()) {
//            String finalWhereCond1 = list.get(2);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA ON SALE.YEARS=PPA.YEARS AND SALE.PERIODS=PPA.PERIODS \n" ;
//        }
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

        query += "\n" + CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n";
        query += getReturnsQuery(projSelDTO);
        list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        projDTOList = getCustomizedProjectionResultsReturns(list, projSelDTO, false);
        LOGGER.info("= = = Ending getReturns = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionResultsReturns(List<Object> list, ProjectionSelectionDTO projSelDTO, boolean isPPA) {
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        List<ProjectionResultsDTO> projDTO = new ArrayList<ProjectionResultsDTO>();
        columnList.remove(Constant.GROUP);
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
                String column = StringUtils.EMPTY;
                int year = (obj[0] == null) ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = (obj[1] == null) ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                String commonColumn = common.get(0);
                int col = 3;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY;
                    value = StringUtils.EMPTY + obj[col + 4];
                    value = getFormattedValue(PER_TWO, value);
                    returnPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnDolDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + 5];
                    value = getFormattedValue(PER_TWO, value);
                    returnPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 3];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnDolDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            returnPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            returnRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            returnDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
        }
        projDTO.add(returnPerDTO);
        projDTO.add(returnRPUDTO);
        projDTO.add(returnDolDTO);

        return projDTO;
    }

    public String getReturnsQuery(ProjectionSelectionDTO projSelDTO) {

        String selectPeriod = StringUtils.EMPTY;
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
                + "FROM ( \n" + getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY) + ") TODIS\n"
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
        String customQuery = StringUtils.EMPTY;
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

        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsReturnsQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + " \n";
        return customQuery;
    }

    /**
     * Used to load PPA static row and discount under Total Discount
     *
     * @param list
     * @param discountName
     * @param projSelDTO
     * @return List<ProjectionResultsDTO>
     */
    public List<ProjectionResultsDTO> loadDiscounts(List<Object> list, String discountName, ProjectionSelectionDTO projSelDTO, int pos) {
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        List<ProjectionResultsDTO> projDtoList = new ArrayList<ProjectionResultsDTO>();
        ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
        projDTO.setLevelValue(projSelDTO.getLevelValue());
        projDTO.setLevelNo(projSelDTO.getLevelNo());
        projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());

        if (list != null && !list.isEmpty()) {

            for (int i = 0; i < list.size(); i++) {
                projSelDTO.setDiscountIndex(i);
                Object[] discountRow = (Object[]) list.get(i);

                if (StringUtils.isBlank(discountName) || !discountName.equals(String.valueOf(discountRow[2]))) {
                    projDTO = new ProjectionResultsDTO();
                    projDtoList.add(projDTO);
                    discountName = String.valueOf(discountRow[2]);
                    projDTO.setGroup(discountName);
                    projDTO.setRelationshipLevelName(discountName);
                }

                String column = StringUtils.EMPTY;
                int year = Integer.valueOf(String.valueOf(discountRow[0]));
                int period = Integer.valueOf(String.valueOf(discountRow[1]));
                List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                String commonColumn = common.get(0);
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    int v =pos == 1 ? 3 : pos == 2 ? 5 : 7;
                    String value = StringUtils.EMPTY + discountRow[pos == 1 ? 3 : pos == 2 ? 5 : 7];
                    if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                        value = getFormattedValue(PER_TWO, value);
                    } else if (projSelDTO.getSales().contains("TOT")) {
                        value = getFormatTwoDecimalValue(CUR_TWO, value, pos == 2 ?Percentage:CURRENCY);
                    }
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + discountRow[pos == 1 ? 4 : pos == 2 ? 6 : 8];
                    if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                        value = getFormattedValue(PER_TWO, value);
                    } else if (projSelDTO.getSales().contains("TOT")) {

                        value = getFormatTwoDecimalValue(CUR_TWO, value, pos == 2 ?Percentage:CURRENCY);
                    }
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }

        for (String columns : columnList) {
            projDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
        }
        return projDtoList;
    }

    /**
     * Used to get the count of discount comes under expanded hierarchy in Total
     * Discount
     *
     * @return
     */
    private String getDiscountCountForCurrentHierarchy(ProjectionSelectionDTO projSelDTO) {
        String condition = projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty() ?  
                "AND B.RS_MODEL_SID IN ( " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " )" : StringUtils.EMPTY;
        
        return "SELECT Count(DISTINCT RS_MODEL_sid)\n"
                + "FROM   ST_NM_DISCOUNT_PROJ_MASTER B\n"
                + "       JOIN PROJECTION_DETAILS pd\n"
                + "         ON pd.PROJECTION_DETAILS_SID = b.PROJECTION_DETAILS_SID\n"
                + "       JOIN @CCP CCP\n"
                + "         ON pd.ccp_details_sid = ccp.CCP_DETAILS_SID\n"
                + "WHERE  B.USER_ID = " + projSelDTO.getUserId() + " \n"
                +  condition;

    }
    
    private String getPPACount(ProjectionSelectionDTO projSelDTO, Boolean isTotal) {
        String query = "SELECT COUNT(DISTINCT RS.RS_NAME) "
                + "FROM ST_NM_PPA_PROJECTION_MASTER TEMP \n"
                + "JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = TEMP.PROJECTION_DETAILS_SID  \n";
        if (!isTotal) {
            query += " JOIN @CCP CCP ON pd.ccp_details_sid = ccp.CCP_DETAILS_SID \n";
        }
        query += "JOIN RS_MODEL RS ON RS.RS_MODEL_SID = TEMP.RS_MODEL_SID \n"
                + "WHERE USER_ID = " + projSelDTO.getUserId() + " AND SESSION_ID = " + projSelDTO.getSessionId() + " AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId();
        return query;
    }
    
    private int getPPACountValue(ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        String query = "SELECT COUNT(DISTINCT RS.RS_NAME) "
                + "FROM ST_NM_PPA_PROJECTION_MASTER TEMP \n"
                + "JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = TEMP.PROJECTION_DETAILS_SID  \n"
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
