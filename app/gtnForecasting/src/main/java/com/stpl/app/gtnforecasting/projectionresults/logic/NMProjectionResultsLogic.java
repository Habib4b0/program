/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionresults.logic;

import com.stpl.app.gtnforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getCommonColumnHeader;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.*;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PER_TWO = new DecimalFormat("#,##0.00%");
    public static final String IMONTH_AS_PERIODS_SLASH = "I.\"MONTH\" as PERIODS, \n";
    /**
     * Percent Three Decimal Format
     */
    /**
     * The Numeric Zero Decimal Places Format.
     */
    private static final DecimalFormat NUM_ZERO = new DecimalFormat("#,##0");
    private List<ProjectionResultsDTO> prjTotalDisPerDtoList = new ArrayList<>();
    private List<ProjectionResultsDTO> prjTotalDisDolDtoList = new ArrayList<>();
    private List<ProjectionResultsDTO> prjTotalRPUDtoList = new ArrayList<>();
    private List<ProjectionResultsDTO> prjDisDolExfacDtoList = new ArrayList<>();
    private final List<ProjectionResultsDTO> projectionTotalList = new ArrayList<>();
    
    private static final String CURRENCY = "$";
    private static final String PERCENTAGE = "%";
    private static final DecimalFormat CUR_TWO = new DecimalFormat("#,##0.00");
    private int pPACount=0;
    private boolean isFirst=true;

    public List<ProjectionResultsDTO> getDiscountPer(ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("= = = Inside getDiscountPer = = =");
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setSales(Constant.RATE);
            CommonLogic commonLogic = new CommonLogic();
            String query = commonLogic.insertAvailableHierarchyNo(projSelDTO);
          query +=commonLogic.getGroupFilterJoinQuery(projSelDTO);
        query += getProjectionResultsDiscountsPerQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, false,0,false);
        projDTOList.addAll(projDTOList1);
        LOGGER.debug("= = = Ending getDiscountPer = = =");
        return projDTOList;
    }

    public List getTotalRPUDollar(ProjectionSelectionDTO projSelDTO, Boolean isVariable,int value) {
        LOGGER.debug("= = = Inside getTotalRPUDollar = = =");
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setSales(value == 1 || value == 3 ? Constant.SALES_WHOLE_CAPS : Constant.DISCOUNT_EXFAC_SALES);
        CommonLogic commonLogic = new CommonLogic();
        String query = " IF Object_id('TEMPDB..#SELECTED_REBATE') IS NOT NULL\n"
                + "  DROP TABLE #SELECTED_REBATE\n"
                + "CREATE TABLE #SELECTED_REBATE\n"
                + "  (\n"
                + "     RS_CONTRACT_SID INT\n"
                + "       ,ITEM_MASTER_SID INT\n"
                + ",CCP_DETAILS_SID INT"
                + "  )\n"
                + "\n"
                + "INSERT INTO #SELECTED_REBATE\n"
                + "            (RS_CONTRACT_SID,\n"
                + "                     ITEM_MASTER_SID, C.CCP_DETAILS_SID)\n"
                + "SELECT DISTINCT ST.RS_CONTRACT_SID,C.ITEM_MASTER_SID,c.CCP_DETAILS_SID \n"
                + "FROM ( VALUES "+getSelectedRSCond(projSelDTO.getDiscountNoList())+")A(RS_CONTRACT_SID)\n"
                + "INNER JOIN ST_NM_DISCOUNT_PROJ_MASTER ST\n"
                + "ON A.RS_CONTRACT_SID=ST.RS_CONTRACT_SID\n"
                + "INNER JOIN CCP_DETAILS C\n"
                + "ON ST.CCP_DETAILS_SID=C.CCP_DETAILS_SID\n"
                + "UNION ALL\n"
                + "SELECT DISTINCT RS_CONTRACT_SID,ITEM_MASTER_SID,c.CCP_DETAILS_SID FROM ST_NM_PPA_PROJECTION_MASTER ST\n"
                + "INNER JOIN CCP_DETAILS C\n"
                + "ON ST.CCP_DETAILS_SID=C.CCP_DETAILS_SID";

        query += commonLogic.insertAvailableHierarchyNo(projSelDTO);
        query += commonLogic.getGroupFilterJoinQuery(projSelDTO);
        List period = CommonLogic.getPeriodRestrictionPR(projSelDTO);
        query += "DECLARE @START_PERIOD_SID int \n"
                + "DECLARE @END_PERIOD_SID int "
                + " DECLARE @PROGRAM_TYPE varchar(100)='PROGRAM' \n"
                + "\n"
                + "		select @START_PERIOD_SID=PERIOD_SID from PERIOD\n"
                + "		where PERIOD_DATE=DATEADD(MM,DATEDIFF(MM,0,'" + period.get(0) + "'),0)"
                + "		select @END_PERIOD_SID=PERIOD_SID from PERIOD\n"
                + "		where PERIOD_DATE=DATEADD(MM,DATEDIFF(MM,0,'" + period.get(1) + "'),0)";

        query += "DECLARE @DISCOUNT_PPA TABLE (\n" + "     YEARS  INT,\n" + "  PERIODS  INT,\n" + "DISCOUNT   VARCHAR(50),\n" + " ACTUAL_DOLLAR NUMERIC(22, 6),\n"
                + "     PROJECTION_DOLLAR NUMERIC(22, 6),\n"
                + "     ACTUAL_RATE       NUMERIC(22, 6),\n"
                + "     PROJECTION_RATE   NUMERIC(22, 6),\n"
                + "     ACTUAL_RPU        NUMERIC(22, 6),\n"
                + "     PROJECTED_RPU     NUMERIC(22, 6),\n"
                + "     RS_CONTRACT_SID   INT"
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
                + "     ,RS_CONTRACT_SID\n"
                + "	)";
        query += getProjectionResultsDiscountsRPUQuery(projSelDTO, isVariable);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
        List<ProjectionResultsDTO> projDTOList1 = null;
        if (!isVariable) {
            projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, false, value, false);
            projDTOList.addAll(projDTOList1);
        }
        LOGGER.debug("= = = Ending getTotalRPUDollar = = =");
        return isVariable ? list : projDTOList1;
    }
    
    private String getSelectedRSCond(List<String> discountList) {
        String result = StringUtils.EMPTY;
        if (discountList != null && !discountList.isEmpty()) {
            for (int i = 0; i < discountList.size(); i++) {
                result+="("+discountList.get(i)+")";
                if(i!=discountList.size()-1){
                    result+=",";
                }
            }
        }
        return result;
    }

    public List<ProjectionResultsDTO> getDiscountDollar(ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("= = = Inside getDiscountDollar = = =");
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
          CommonLogic commonLogic = new CommonLogic();
          String query = commonLogic.insertAvailableHierarchyNo(projSelDTO);
           query +=commonLogic.getGroupFilterJoinQuery(projSelDTO);
        query += getProjectionResultsDiscountsQuery(projSelDTO, " order by DISCOUNTS");
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, false,0,false);
        projDTOList.addAll(projDTOList1);
        LOGGER.debug("= = = Ending getDiscountDollar = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getTotalDiscountLevels(ProjectionSelectionDTO projSelDTO, int value) {
        LOGGER.info("= = = Inside getTotalDiscountLevels = = =");
           CommonLogic commonLogic = new CommonLogic();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projDTOList1 = null;
        List<Object> list = null;
        String query = commonLogic.insertAvailableHierarchyNo(projSelDTO);
         query +=commonLogic.getGroupFilterJoinQuery(projSelDTO);
        query += "DECLARE @FROM_DATE DATE \n"
                + "     , @STARTFROM DATE \n"
                + "     , @PROJECTION_DATE DATE \n"
                + "     , @START_PERIOD_SID INT \n"
                + "     , @END_PERIOD_SID INT \n"
                + "SELECT TOP 1 @STARTFROM = DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, - 3, GETDATE())), 0) \n"
                + "     , @PROJECTION_DATE = DATEADD(M, DATEDIFF(M, 0, DATEADD(DAY, - 1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))), 0) \n"
                + "FROM PROJECTION_MASTER \n"
                + "WHERE PROJECTION_MASTER_SID =  " + projSelDTO.getProjectionId() + "\n";

        if (projSelDTO.isIsTotal()) {
            query += getProjectionResultsTotalDiscount(projSelDTO);
            list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
            projDTOList1 = getCustomizedProjectionTotalDiscount(list, projSelDTO);
        } else if (value == 1 || value == NumericConstants.TWO || value == NumericConstants.THREE || value == NumericConstants.FOUR) {
            projDTOList1 = getTotalRPUDollar(projSelDTO, Boolean.FALSE, value);
        }
        projDTOList.addAll(projDTOList1);
        LOGGER.info("= = = Ending getTotalDiscountLevels = = =");
        return projDTOList;
    }

    private List getCustomizedProjectionTotalDiscount(List list, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> resultList = new ArrayList<>();
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        ProjectionResultsDTO totalDiscountPerDto = new ProjectionResultsDTO();
        ProjectionResultsDTO totalRPUDto = new ProjectionResultsDTO();
        ProjectionResultsDTO totalDiscountAmtDto = new ProjectionResultsDTO();
        ProjectionResultsDTO totaldisPerExfacDto = new ProjectionResultsDTO();

        totalDiscountPerDto.setParent(1);
        totalDiscountPerDto.setGroup(TOTAL_DISCOUNT_PERC.getConstant());
        totalDiscountPerDto.setProjectionTotal(0);
        totalDiscountPerDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalDiscountPerDto.setOnExpandTotalRow(0);
        totalDiscountPerDto.setLevelValue(projSelDTO.getLevelValue());
        totalDiscountPerDto.setLevelNo(projSelDTO.getLevelNo());
        totalDiscountPerDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalDiscountPerDto.setHierarchyNo(projSelDTO.getHierarchyNo());
        if(projSelDTO.isIsCustomHierarchy()){
        totalDiscountPerDto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        }
        
        totaldisPerExfacDto.setParent(1);
        totaldisPerExfacDto.setGroup(Constant.DISCOUNT_PER_OF_EX_FACTORY_HEADER);
        totaldisPerExfacDto.setProjectionTotal(0);
        totaldisPerExfacDto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totaldisPerExfacDto.setOnExpandTotalRow(0);
        totaldisPerExfacDto.setLevelValue(projSelDTO.getLevelValue());
        totaldisPerExfacDto.setLevelNo(projSelDTO.getLevelNo());
        totaldisPerExfacDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totaldisPerExfacDto.setHierarchyNo(projSelDTO.getHierarchyNo());
        if (projSelDTO.isIsCustomHierarchy()) {
            totaldisPerExfacDto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
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
       if(projSelDTO.isIsCustomHierarchy()){
        totalDiscountAmtDto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        }

        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;
                int year = (obj[0] == null) ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = (obj[1] == null) ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                String commonColumn = common.get(0);
                int col = NumericConstants.THREE;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value;
                    String value1;
                    value = StringUtils.EMPTY + obj[col];
                    value = getFormattedValue(PER_TWO, value);
                    value1 = getFormattedValue(PER_TWO, "0.00");
                    totalDiscountPerDto.addStringProperties(column,!"null".equals(obj[col]) && obj[col]!=null? value :value1);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                    totalDiscountAmtDto.addStringProperties(column, !"null".equals(obj[col + NumericConstants.THREE]) && obj[col + NumericConstants.THREE]!=null? value :value1);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.SIX];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                    totalRPUDto.addStringProperties(column,!"null".equals(obj[col + NumericConstants.SIX]) && obj[col + NumericConstants.SIX]!=null? value :value1);
                   
                    value = StringUtils.EMPTY + obj[col + NumericConstants.NINE];
                    value = getFormattedValue(PER_TWO, value);
                    value1 = getFormattedValue(PER_TWO, "0.00");
                    totaldisPerExfacDto.addStringProperties(column,!"null".equals(obj[col + NumericConstants.NINE]) && obj[col + NumericConstants.NINE]!=null? value :value1);
                    
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + 1];
                    String value1;
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(PER_TWO, value);
                    value1 = getFormattedValue(PER_TWO, "0.00");
                    totalDiscountPerDto.addStringProperties(column, !"null".equals(obj[col + 1]) && obj[col + 1]!=null? value :value1);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FOUR];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                    totalDiscountAmtDto.addStringProperties(column, !"null".equals(obj[col + NumericConstants.FOUR]) && obj[col + NumericConstants.FOUR]!=null? value :value1);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.SEVEN];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                    totalRPUDto.addStringProperties(column, !"null".equals(obj[col + NumericConstants.SEVEN]) && obj[col + NumericConstants.SEVEN]!=null? value :value1);
                    
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TEN];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(PER_TWO, value);
                    value1 = getFormattedValue(PER_TWO, "0.00");
                    totaldisPerExfacDto.addStringProperties(column, !"null".equals(obj[col + NumericConstants.TEN]) && obj[col + NumericConstants.TEN]!=null? value :value1);
                    
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            totalDiscountPerDto.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            totalRPUDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            totalDiscountAmtDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            totaldisPerExfacDto.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
        }
        resultList.add(totalDiscountPerDto);
        resultList.add(totalRPUDto);
        resultList.add(totalDiscountAmtDto);
        resultList.add(totaldisPerExfacDto);

        return resultList;
    }

    public ProjectionResultsDTO getPPAPer(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales(Constant.RATE);
        ProjectionResultsDTO ppaDto = new ProjectionResultsDTO();
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsPPAPerQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, true,0,false);
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
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, true,1,false);
        if (projDTOList1 != null && !projDTOList1.isEmpty()) {
            ppaDto = projDTOList1.get(0);
        }
        ppaDto.setGroup(PPA_DISCOUNT.getConstant());
        ppaDto.setParent(0);
        return list;
    }

    
    
     public List<ProjectionResultsDTO> PPARPUtest(ProjectionSelectionDTO projSelDTO) {
            List<ProjectionResultsDTO> projDTOList;
        projSelDTO.setSales(Constant.RATE);
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsPPARPU(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
         projDTOList = getCustomizedProjectionResultsDiscount(list, projSelDTO, true,NumericConstants.TWO,false);
        return projDTOList;
    }
    public ProjectionResultsDTO getPPADollar(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        ProjectionResultsDTO ppaDto = new ProjectionResultsDTO();
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsPPAQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, true,NumericConstants.THREE,false);
        if (projDTOList1 != null && !projDTOList1.isEmpty()) {
            ppaDto = projDTOList1.get(0);
        }
        ppaDto.setGroup(PPA_DISCOUNT.getConstant());
        ppaDto.setParent(0);
        return ppaDto;
    }
    private List<String> discountList = new ArrayList<>();

    public List<ProjectionResultsDTO> getCustomizedProjectionResultsDiscount(List<Object> list, ProjectionSelectionDTO projSelDTO, boolean isPPA,int value,boolean isNetSales) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setDiscountIndex(0);
        if (!projSelDTO.isIsTotal() && !isPPA) {
            projDTOList = loadDiscounts(list, StringUtils.EMPTY, projSelDTO, value);
        } else {
            ProjectionResultsDTO dto = getCustomisedProjectionResultsDiscount(list, Constant.TOTAL_SMALL, projSelDTO,isNetSales);
            projDTOList.add(dto);
        }
        return projDTOList;
    }

    public ProjectionResultsDTO getCustomisedProjectionResultsDiscount(List<Object> list, String discountName, ProjectionSelectionDTO projSelDTO,boolean isNetSales ) {
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
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
                if (discountList.contains(StringUtils.EMPTY + discountRow[NumericConstants.TWO])) {
                    forword = true;
                } else if (!discountName.contains(Constant.TOTAL_SMALL)) {
                    forword = false;
                }
                if (forword) {
                    if (discount == null) {
                        projDTO.setGroup(String.valueOf(discountRow[NumericConstants.TWO]));
                    } else if (!discount.equals(discountRow[NumericConstants.TWO].toString())) {
                        if (!discountName.contains(Constant.TOTAL_SMALL)) {
                            discountList.remove(discount);
                        }
                        start = false;
                    }
                    if (start) {
                        discount = discountRow[NumericConstants.TWO].toString();
                        String column;
                        int year = Integer.valueOf(String.valueOf(discountRow[0]));
                        int period = Integer.valueOf(String.valueOf(discountRow[1]));
                        List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                        String commonColumn = common.get(0);
                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            int valueIndex = isNetSales ? NumericConstants.SIX : NumericConstants.THREE;
                            String value = StringUtils.EMPTY + discountRow[valueIndex];
                            String value1 = StringUtils.EMPTY;
                            if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                            } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                                value = getFormattedValue(PER_TWO, value);
                                value1 = getFormattedValue(PER_TWO, "0.00");
                            } else if (projSelDTO.getSales().contains("TOT")) {
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                            } else if (projSelDTO.getSales().contains(Constant.DISCOUNT_EXFAC_SALES)) {
                                value = getFormattedValue(PER_TWO, value);
                                value1 = getFormattedValue(PER_TWO, "0.00");
                            }
                          
                            projDTO.addStringProperties(column, !"null".equals(discountRow[valueIndex]) && discountRow[valueIndex]!=null? value : value1);
                            columnList.remove(column);
                        }
                        column = commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            int valueIndex = isNetSales ? NumericConstants.SEVEN : NumericConstants.FOUR;
                            String value = StringUtils.EMPTY + discountRow[valueIndex];
                            String value1 = StringUtils.EMPTY;
                            if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                            } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                                value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                                value = getFormattedValue(PER_TWO, value);
                                value1 = getFormattedValue(PER_TWO, "0.00");
                            } else if (projSelDTO.getSales().contains("TOT")) {
                                value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                            } else if (projSelDTO.getSales().contains(Constant.DISCOUNT_EXFAC_SALES)) {
                                value = getFormattedValue(PER_TWO, value);
                                value1 = getFormattedValue(PER_TWO, "0.00");
                            }

                            projDTO.addStringProperties(column, !"null".equals(discountRow[valueIndex]) && discountRow[valueIndex]!=null ? value : value1);
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
                projDTO.setGroup(Constant.TOTAL_RPU_CAPS);
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
        List<ProjectionResultsDTO> projDtoList = new ArrayList<>();
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
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
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;
                int year = Integer.valueOf(String.valueOf(obj[0]));
                int period = Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                int col = NumericConstants.TWO;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col];
                    String value1;
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                    projSalesDTO.addStringProperties(column, !"null".equals(obj[col]) && obj[col]!=null? value :value1);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    value = getFormattedValue(NUM_ZERO, value);
                    value1 = getFormattedValue(NUM_ZERO, "0.00");
                    projUnitDTO.addStringProperties(column, !"null".equals(obj[col + NumericConstants.TWO]) && obj[col + NumericConstants.TWO]!=null? value :value1);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + 1];
                    String value1;
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value1 = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    projSalesDTO.addStringProperties(column, !"null".equals(obj[col + 1]) && obj[col + 1]!=null? value :value1);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                     value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(NUM_ZERO, value);
                    value1 = getFormattedValue(NUM_ZERO, "0.00");
                    projUnitDTO.addStringProperties(column, !"null".equals(obj[col + NumericConstants.THREE]) && obj[col + NumericConstants.THREE]!=null? value :value1);
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
        CommonLogic commonLogic = new CommonLogic();
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
         String query;
         query = commonLogic.insertAvailableHierarchyNo(projSelDTO);
          query +=commonLogic.getGroupFilterJoinQuery(projSelDTO);
         query += getProjectionResultsSalesQuery(projSelDTO,false);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
        List<ProjectionResultsDTO> projDTOList = getCustomizedProjectionResultsSales(list, projSelDTO);
        LOGGER.debug("= = = Ending getContractSalesAndUnits = = =");
        return projDTOList;
    }
    
    public List<ProjectionResultsDTO> getProjectionPivot(ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDTOList;
        CommonLogic commonLogic = new CommonLogic();
        String ccpQuery = commonLogic.insertAvailableHierarchyNo(projSelDTO);
        ccpQuery += commonLogic.getGroupFilterJoinQuery(projSelDTO);
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
        String gtsListQuery = cogsSelect + " \n " + ccpQuery + " \n" + getProjectionResultsPivotQuery(projSelDTO);
        List<Object> gtsList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(gtsListQuery, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
        List discList = getTotalRPUDollar(projSelDTO, Boolean.TRUE, 0);
        projDTOList = getCustomizedProjectionPivot(gtsList, discList, projSelDTO);
        return projDTOList;
    }
    
    public List<ProjectionResultsDTO> getCustomizedProjectionPivot(List<Object> list, List<Object> discountList,  ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        int discountIndex = 0;
        int col = NumericConstants.TWO;
        int dcol = NumericConstants.TWO;
        for (Object rows : list) {
            final Object[] row = (Object[]) rows;
            String column;
            int year = Integer.valueOf(String.valueOf(row[0]));
            int period = Integer.valueOf(String.valueOf(row[1]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
                List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
                columnList.remove(Constant.GROUP);
                projDTO.setGroup(commonHeader);
                String value;
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
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWO];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THREE];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.TOT_DIS_PER;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FOUR];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FIVE];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.TOTAL_DISCOUNT_DOLLAR;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.SIX];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.SEVEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.TOTAL_RPU;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.ELEVEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netSales";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.EIGHT];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.NINE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "cogs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWELVE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netProfit";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FOURTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FIFTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.NET_SALES_PER_OF_EX_FACTORY;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_TWO];
                      value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_THREE];
                      value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
               commonColumn = Constant.DISCOUNT_PER_OF_EX_FACTORY;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_FOUR];
                      value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_FIVE];
                      value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                } 
                
                
                for (int i = discountIndex; i < discountList.size(); i++) {
                    Object[] discountRow = (Object[]) discountList.get(i);
                    int dyear = Integer.valueOf(String.valueOf(discountRow[dcol - NumericConstants.TWO]));
                    int dperiod = Integer.valueOf(String.valueOf(discountRow[1]));
                    List<String> dcommon = getCommonColumnHeader(frequencyDivision, dyear, dperiod);
                    String dcommonColumn = dcommon.get(0);
                    if (pcommonColumn.equals(dcommonColumn)) {
                            String head = String.valueOf(discountRow[NumericConstants.NINE]).replace(" ", StringUtils.EMPTY);
                            String commonColumn1 = Constant.TOTAL_DISCOUNT_DOLLAR + head;
                            String commonColumn2 = Constant.TOT_DIS_PER + head;
                            String commonColumn3 = Constant.TOTAL_RPU + head;
                            String commonColumn4 = Constant.DISCOUNT_PER_OF_EX_FACTORY + head;
                            column = commonColumn2 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.THREE];
                                value = getFormattedValue(PER_TWO, value);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn2 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.FOUR];
                                value = getFormattedValue(PER_TWO, value);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn1 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 1];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn1 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.TWO];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn3 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.FIVE];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn3 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.SIX];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn4 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.EIGHT]; 
                                value = getFormattedValue(PER_TWO, value);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn4 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.NINE]; 
                                value = getFormattedValue(PER_TWO, value);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                    }
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(0);
                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
                }
                projDTOList.add(projDTO);
            }

        }
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
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
        if (projectionTotalList.isEmpty() || projSelDTO.isIsFrequencyChanged() || projSelDTO.isIsHistroryChanged()) {
            projectionTotalList.clear();
            List<Object[]> gtsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS", orderedArgs);
            Object[] orderedArgs1 = {orderedArgs[0], orderedArgs[1], orderedArgs[NumericConstants.TWO], orderedArgs[NumericConstants.THREE], orderedArgs[NumericConstants.FOUR], orderedArgs[NumericConstants.FIVE], 0, "PROGRAM"};
            List<Object[]> discountsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS_DISCOUNT", orderedArgs1);
            getCustomizedProjectionPivotTotal(gtsList, discountsList, projSelDTO);
        }

        List<ProjectionResultsDTO> resultList = new ArrayList(projectionTotalList);
        if (!projSelDTO.getProjectionOrder().equals(ASCENDING.getConstant())) {
            Collections.reverse(resultList);
        }
        return resultList;
    }

    public void getCustomizedProjectionPivotTotal(List<Object[]> list, List<Object[]> discountList, ProjectionSelectionDTO projSelDTO) {
        projectionTotalList.clear();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        int discountIndex = 0;
        int col = NumericConstants.FIVE;
        int dcol = NumericConstants.THREE;
        if (frequencyDivision != 1) {
            col = col + 1;
        }
        for (Object[] row : list) {

            String column;
            int year = Integer.valueOf(String.valueOf(row[col - 1]));
            int period = Integer.valueOf(String.valueOf(row[NumericConstants.FOUR]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
                List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
                columnList.remove(Constant.GROUP);
                projDTO.setGroup(commonHeader);
                String value;
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
                    value = StringUtils.EMPTY + row[NumericConstants.TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "demand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.SIXTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.SEVENTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "inventory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.EIGHTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.NINETEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perExFactory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.EIGHT];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.NINE];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perDemand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_ONE];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perInventory";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_TWO];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_THREE];
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
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWO];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THREE];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.TOT_DIS_PER;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.SIX];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.SEVEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.TOTAL_RPU;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_EIGHT];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_NINE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.TOTAL_DISCOUNT_DOLLAR;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FOUR];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FIVE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netSales";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FOURTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FIFTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "cogs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY_ONE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netProfit";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY_TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY_THREE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.NET_SALES_PER_OF_EX_FACTORY;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FORTY];
                      value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FORTY_ONE];
                      value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                
                commonColumn = Constant.DISCOUNT_PER_OF_EX_FACTORY;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FORTY_TWO];
                      value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FORTY_THREE];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                } 
                for (int i = discountIndex; i < discountList.size(); i++) {
                    Object[] discountRow = discountList.get(i);
                    int dyear = Integer.valueOf(String.valueOf(discountRow[1]));
                    int dperiod = Integer.valueOf(String.valueOf(discountRow[NumericConstants.TWO]));
                    List<String> dcommon = getCommonColumnHeader(frequencyDivision, dyear, dperiod);
                    String dcommonColumn = dcommon.get(0);
                    if (pcommonColumn.equals(dcommonColumn)) {
                            String head = String.valueOf(discountRow[NumericConstants.TEN]).replace(" ", StringUtils.EMPTY);
                            String commonColumn1 = Constant.TOTAL_DISCOUNT_DOLLAR + head;
                            String commonColumn3 = Constant.TOTAL_RPU + head;
                            String commonColumn2 = Constant.TOT_DIS_PER + head;
                            String commonColumn4 = Constant.DISCOUNT_PER_OF_EX_FACTORY + head;
                            column = commonColumn1 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + 1];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn1 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.TWO];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn2 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.THREE];
                                value = getFormattedValue(PER_TWO, value);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn2 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.FOUR];
                                value = getFormattedValue(PER_TWO, value);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn3 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.FIVE];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn3 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.SIX];
                                value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            
                            column = commonColumn4 + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.SEVEN]; 
                                value = getFormattedValue(PER_TWO, value);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                            column = commonColumn4 + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.EIGHT]; 
                                value = getFormattedValue(PER_TWO, value);
                                projDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
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
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
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
        if (leftFlag) {
            Collections.sort(projectionTotalList, new ProjectionResultsDTO());
        }        
    }

    public String getFormattedValue(DecimalFormat decFormat, String value) {
        if (value.contains(Constant.NULL)) {
            value = DASH.getConstant();
        } else {
            Double newValue = Double.valueOf(value);
            if (decFormat.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / NumericConstants.HUNDRED;
            }
            value = decFormat.format(newValue);
        }
        return value;
    }

    public void getProjectionTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getProjectionTotal = = =");
         List<Object[]> gtsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS", orderedArgs);
        
        if (gtsList != null) {
            projectionTotalList.clear();
            getCustomizedProjectionTotal(gtsList, projSelDTO,false);
        }
        LOGGER.info("= = = Ending getProjectionTotal = = =");
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO,boolean isExcel) {
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
        ProjectionResultsDTO returnDiscountPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO returnRPUDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO returnDiscountDolDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO netSalesExfacSalesDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO disPerExfacDTO = new ProjectionResultsDTO();
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

        disPerExfacDTO.setParent(1);
        disPerExfacDTO.setGroup(Constant.DISCOUNT_PER_OF_EX_FACTORY_HEADER);
        disPerExfacDTO.setProjectionTotal(1);
        disPerExfacDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        disPerExfacDTO.setOnExpandTotalRow(0);

        netSaleDTO.setParent(0);
        netSaleDTO.setGroup(NET_SALES.getConstant());
            ppaPerDTO.setParent(0);
            ppaPerDTO.setGroup(PPA_DISCOUNT.getConstant());

            ppaRPUDTO.setParent(0);
            ppaRPUDTO.setGroup(PPA_DISCOUNT.getConstant());

            ppaDolDTO.setParent(0);
            ppaDolDTO.setGroup(PPA_DISCOUNT.getConstant());
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

        netSalesExfacSalesDTO.setParent(0);
        netSalesExfacSalesDTO.setGroup(Constant.NET_SALES_PER_OF_EX_FACTORY_HEADER);

        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            int col = NumericConstants.FIVE;
            if (frequencyDivision != 1) {
                col = col + 1;
            }
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;

                int year = Integer.valueOf(String.valueOf(obj[col - 1]));
                int period = Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR]));
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
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    value = getFormattedValue(NUM_ZERO, value);
                    unitVolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FOUR];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    discountDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.SIX];
                    value = getFormattedValue(PER_TWO, value);
                    discountPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.EIGHT];
                    value = getFormattedValue(PER_TWO, value);
                    perExFactoryDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.TEN];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaDolDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.TWELVE];
                        value = getFormattedValue(PER_TWO, value);
                        ppaPerDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY_SIX];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FOURTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netSaleDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.SIXTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    demandDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.EIGHTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    inventoryDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY];
                    value = getFormattedValue(PER_TWO, value);
                    perDemandDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY_TWO];
                    value = getFormattedValue(PER_TWO, value);
                    perInventoryDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY_EIGHT];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    cogsDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netprofitDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_SIX];
                    value = getFormattedValue(PER_TWO, value);
                    returnDiscountPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_EIGHT];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_FOUR];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnDiscountDolDTO.addStringProperties(column, value);
                    
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FORTY];
                    value = getFormattedValue(PER_TWO, value);
                    netSalesExfacSalesDTO.addStringProperties(column, value);
                    
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FORTY_TWO];
                    value = getFormattedValue(PER_TWO, value);
                    disPerExfacDTO.addStringProperties(column, value);
                    
                    
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[NumericConstants.TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    exFactoryDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    conSaleDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                    value = getFormattedValue(NUM_ZERO, value);
                    unitVolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FIVE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    discountDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.SEVEN];
                    value = getFormattedValue(PER_TWO, value);
                    discountPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.NINE];
                    value = getFormattedValue(PER_TWO, value);
                    perExFactoryDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.ELEVEN];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaDolDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTEEN];
                        value = getFormattedValue(PER_TWO, value);
                        ppaPerDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY_SEVEN];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        ppaRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FIFTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netSaleDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.SEVENTEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    demandDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.NINETEEN];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    inventoryDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY_ONE];
                    value = getFormattedValue(PER_TWO, value);
                    perDemandDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY_THREE];
                    value = getFormattedValue(PER_TWO, value);
                    perInventoryDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY_NINE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_ONE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    cogsDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_THREE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netprofitDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_SEVEN];
                    value = getFormattedValue(PER_TWO, value);
                    returnDiscountPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_NINE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_FIVE];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnDiscountDolDTO.addStringProperties(column, value);
                    
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FORTY_ONE];
                    value = getFormattedValue(PER_TWO, value);
                    netSalesExfacSalesDTO.addStringProperties(column, value);
                    
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FORTY_THREE];
                    value = getFormattedValue(PER_TWO, value);
                    disPerExfacDTO.addStringProperties(column, value);
                    
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
                ppaPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
                ppaRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
                ppaDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            returnDiscountPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            returnRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            returnDiscountDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            netSalesExfacSalesDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            disPerExfacDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
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
        projDTOList.add(disPerExfacDTO);
        projDTOList.add(netSaleDTO);
        projDTOList.add(netSalesExfacSalesDTO);
        projDTOList.add(cogsDTO);
        projDTOList.add(netprofitDTO);
        if (!isExcel) {
            projDTOList.add(returnDiscountPerDTO);
            projDTOList.add(returnRPUDTO);
            projDTOList.add(returnDiscountDolDTO);
            projDTOList.add(ppaPerDTO);
            projDTOList.add(ppaRPUDTO);
            projDTOList.add(ppaDolDTO);
        }
        projectionTotalList.addAll(projDTOList);
        LOGGER.debug("= = = Ending getCustomizedProjectionTotal = = =");
         return Collections.unmodifiableList(projectionTotalList);
    }

    public List<ProjectionResultsDTO> getProjectionTotalDiscounts(ProjectionSelectionDTO projSelDTO, Object[] orderedArgs) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        if (projSelDTO.isIsFrequencyChanged()|| projSelDTO.isIsHistroryChanged()||prjTotalDisDolDtoList.isEmpty() || prjTotalDisPerDtoList.isEmpty() || prjTotalRPUDtoList.isEmpty() || prjDisDolExfacDtoList.isEmpty()) {
            Object[] orderedArgs1 = {orderedArgs[0], orderedArgs[1], orderedArgs[NumericConstants.TWO], orderedArgs[NumericConstants.THREE], orderedArgs[NumericConstants.FOUR], orderedArgs[NumericConstants.FIVE], 1,"PROGRAM"};
            List<Object[]> gtsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS_DISCOUNT", orderedArgs1);
            getCustomizedDiscountsProjectionTotal(gtsList, projSelDTO);
        }
        if (projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) ) {
            projDTOList = new ArrayList<>(prjTotalDisDolDtoList);
        } else if (projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE) && !projSelDTO.getGroup().contains(Constant.DISCOUNT_PER_OF_EX_FACTORY_HEADER) ) {
            projDTOList = new ArrayList<>(prjTotalDisPerDtoList);
        } else if (projSelDTO.getGroup().contains(Constant.TOTAL_RPU_CAPS)) {
            projDTOList = new ArrayList<>(prjTotalRPUDtoList);
        } else if (projSelDTO.getGroup().contains(Constant.DISCOUNT_PER_OF_EX_FACTORY_HEADER)) {
            projDTOList = new ArrayList<>(prjDisDolExfacDtoList);
        }
        return projDTOList;
    }

    public void getCustomizedDiscountsProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDolDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projRPUDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projPerDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projDisPerExfacDTOList = new ArrayList<>();
        List<String> newList = new ArrayList<String>();
        Iterator<String> iterator = projSelDTO.getDiscountNameList().iterator();
        Iterator<String> iterator1 = projSelDTO.getDiscountNoList().iterator();
        boolean ppaFlag = Boolean.FALSE;

        while (iterator.hasNext() && iterator1.hasNext()) {
            String rsNo = iterator.next();
            String rsName = iterator1.next();
            newList.add(rsNo + "~" + rsName);
            // add FirstName and LastName to the new list here
        }
        //PPA
        List list1 = CommonLogic.getPPADiscountNameListPR(projSelDTO, Boolean.TRUE);
        if (list.get(0) != null) {
            newList.addAll(list1);
            ppaFlag = Boolean.TRUE;
        }
        String oldDiscountName;
        String newDiscountName = "oldDiscountName";
        if (list != null && !list.isEmpty()) {
            ProjectionResultsDTO projDolDTO = new ProjectionResultsDTO();
            ProjectionResultsDTO projRPUDTO = new ProjectionResultsDTO();
            ProjectionResultsDTO projPerDTO = new ProjectionResultsDTO();
            ProjectionResultsDTO projDisPerDTO = new ProjectionResultsDTO();
            int col = NumericConstants.THREE;
            boolean add = false;
            List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
            columnList.remove(Constant.GROUP);
            for (int i = 0; i < list.size(); i++) {
                final Object[] obj = (Object[]) list.get(i);
                String column;
                int year = Integer.valueOf(String.valueOf(obj[1]));
                int period = Integer.valueOf(String.valueOf(obj[NumericConstants.TWO]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                oldDiscountName = newDiscountName;
                newDiscountName = obj[NumericConstants.THREE]+"~"+obj[NumericConstants.TEN];
                if (!oldDiscountName.equals(newDiscountName)) {
                    add = false;
                    if (newList.contains(newDiscountName) || ppaFlag) {
                        add = true;
                        newList.remove(newDiscountName);
                        projDolDTO = new ProjectionResultsDTO();
                        projRPUDTO = new ProjectionResultsDTO();
                        projPerDTO = new ProjectionResultsDTO();
                        projDisPerDTO = new ProjectionResultsDTO();
                        projDolDTO.setParent(0);
                        projRPUDTO.setParent(0);
                        projPerDTO.setParent(0);
                        projDisPerDTO.setParent(0);
                        projDolDTO.setGroup(newDiscountName.split("~")[0]);
                        projRPUDTO.setGroup(newDiscountName.split("~")[0]);
                        projPerDTO.setGroup(newDiscountName.split("~")[0]);
                        projDisPerDTO.setGroup(newDiscountName.split("~")[0]);
                        projDolDTOList.add(projDolDTO);
                        projRPUDTOList.add(projRPUDTO);
                        projPerDTOList.add(projPerDTO);
                        projDisPerExfacDTOList.add(projDisPerDTO);
                        for (String columns : columnList) {
                            projDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
                            projRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
                            projPerDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                            projDisPerDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                        }
                        columnList = new ArrayList<>(projSelDTO.getColumns());
                        columnList.remove(Constant.GROUP);
                    }

                }
                if (add) {
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        String value = StringUtils.EMPTY + obj[col + 1];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        projDolDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                        value = getFormattedValue(PER_TWO, value);
                        projPerDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.FIVE];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        projRPUDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.EIGHT];
                        value = getFormattedValue(PER_TWO, value);
                        projDisPerDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        String value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        projDolDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.FOUR];
                        value = getFormattedValue(PER_TWO, value);
                        projPerDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.SIX];
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        projRPUDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.NINE];
                        value = getFormattedValue(PER_TWO, value);
                        projDisPerDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                }
            }

        }
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
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
            ProjectionResultsDTO projDTO4 = new ProjectionResultsDTO();
            projDTO4.setParent(0);
            projDTO4.setProjectionTotal(1);
            projDTO4.setGroup(ob);
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
                projDTO1.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                projDTO2.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
                projDTO4.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            }
                projDolDTOList.add(projDTO);
                projRPUDTOList.add(projDTO2);
                projPerDTOList.add(projDTO1);
                projDisPerExfacDTOList.add(projDTO4);
            }
            prjTotalDisDolDtoList = new ArrayList<>(projDolDTOList);
            prjTotalDisPerDtoList = new ArrayList<>(projPerDTOList);
            prjTotalRPUDtoList = new ArrayList<>(projRPUDTOList);
            prjDisDolExfacDtoList = new ArrayList<>(projDisPerExfacDTOList);
         
    }

    public  List<ProjectionResultsDTO> getNetSales(ProjectionSelectionDTO projSelDTO) {
        try {
            List<ProjectionResultsDTO> resultList=new ArrayList<>();
              CommonLogic commonLogic = new CommonLogic();
            ProjectionResultsDTO netSalesDto = new ProjectionResultsDTO();
            List<Object> list = null;
            List<ProjectionResultsDTO> projDTOList = null;
            projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
            String query = commonLogic.insertAvailableHierarchyNo(projSelDTO);
             query +=commonLogic.getGroupFilterJoinQuery(projSelDTO);
                query += getProjectionResultsNetSalesQuery(projSelDTO);
            list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
            projDTOList = getCustomizedProjectionResultsDiscount(list, projSelDTO, false,1,false);
            if (projDTOList != null && !projDTOList.isEmpty()) {
                netSalesDto = projDTOList.get(0);
            }
            netSalesDto.setGroup("Net Sales");
            netSalesDto.setParent(0);
            LOGGER.debug("= = = Ending getNetSales = = =");
            resultList.add(netSalesDto);
            // for Net Sales % ExFactory
            projSelDTO.setSales(Constant.DISCOUNT_EXFAC_SALES);
            projDTOList = getCustomizedProjectionResultsDiscount(list, projSelDTO, false,2,true);
            if (projDTOList != null && !projDTOList.isEmpty()) {
                netSalesDto = projDTOList.get(0);
            }
            netSalesDto.setGroup("Net Sales % ExFactory");
            netSalesDto.setParent(0);
            LOGGER.debug("= = = Net Sales % ExFactory = = =");
            resultList.add(netSalesDto);
            return resultList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        }
    }

    
     private List<ProjectionResultsDTO> getCOGSNetProfit(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("= = = Inside getCOGSandNetProfit = = = = = =");
        try {
        CommonLogic commonLogic = new CommonLogic();
        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
            
            String query;
            List<ProjectionResultsDTO> projDTOList;
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
                    + " FROM   PERIOD \n"
                    + "   WHERE  PERIOD_DATE = '"+startDate+"'\n"
                    + "\n"
                    + " SELECT @END_SID = PERIOD_SID\n"
                    + " FROM   PERIOD\n "
                    + " WHERE  PERIOD_DATE = (SELECT Dateadd(MM, Datediff(MM, 0, '"+endDate+"'), 0))\n"
                    + "\n"
                    + " SELECT @START_PERIOD_SID = PERIOD_SID\n"
                    + " FROM   PERIOD  \n"
                    + " WHERE  PERIOD_DATE = @STARTFROM\n"
                    + "\n"
                    + " SELECT @END_PERIOD_SID = PERIOD_SID\n"
                    + " FROM   PERIOD\n"
                    + " WHERE  PERIOD_DATE = @PROJECTION_DATE     \n";
            query = cogsSelect ;
            query += commonLogic.insertAvailableHierarchyNo(projSelDTO);
            query +=commonLogic.getGroupFilterJoinQuery(projSelDTO);
            query += getCOGSNetProfitValue(projSelDTO);
            List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
            projDTOList = getCustomizedCOGSandNetProfit(list, projSelDTO);
            LOGGER.info("= = = Ending getCOGSandNetProfit = = = = = =");
            return projDTOList;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        }
    }
   
    private List getCustomizedCOGSandNetProfit(List list, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> resultList = new ArrayList<>();
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
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
                String column;
                int year = (obj[0] == null) ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = (obj[1] == null) ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                String commonColumn = common.get(0);
                int col = NumericConstants.TWO;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value;
                    String value1;
                    value = StringUtils.EMPTY + obj[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                    cogsDto.addStringProperties(column, !"null".equals(obj[col]) && obj[col]!=null? value :value1);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                    netprofitDto.addStringProperties(column, !"null".equals(obj[col + NumericConstants.TWO]) && obj[col + NumericConstants.TWO]!=null? value :value1);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value;
                    String value1;
                    value = StringUtils.EMPTY + obj[col + 1];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                    cogsDto.addStringProperties(column, !"null".equals(obj[col + 1]) &&obj[col + 1]!=null? value :value1);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                    netprofitDto.addStringProperties(column, !"null".equals(obj[col + NumericConstants.THREE]) && obj[col + NumericConstants.THREE]!=null? value :value1);
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
        LOGGER.debug("= = = Inside getProjectionResults = = =   isReturn  " + projSelDTO.isReturns());
        LOGGER.debug("= = = Inside getProjectionResults = = =   isPPA  " + projSelDTO.isPpa());
        LOGGER.debug("= = = Inside getProjectionResults = = =   isPPA  " + projSelDTO.getGroup());
        int started = start;
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        String discList = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
        String freq = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 1) {
            freq = "ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            freq = Constant.SEMIANNUAL_CAPS;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            freq = "MONTHLY";
        }
        projSelDTO.setProjectionHeaderList(CommonUtils.prepareProjectionPeriodList(projSelDTO));
        Object[] orderedArgs = {projSelDTO.getProjectionId(), freq, discList, "ASSUMPTIONS", projSelDTO.getSessionDTO().getSessionId(), projSelDTO.getUserId()};
        if (!projSelDTO.getGroup().startsWith(Constant.ALL)
                && !projSelDTO.getGroup().contains(Constant.SALES_WITH_HYPHEN)
                && !projSelDTO.getGroup().contains(Constant.DISCOUNT)
                && !projSelDTO.getGroup().contains(Constant.PPA)) {
                if ((projSelDTO.isIsTotal()) && (projSelDTO.isIsProjectionTotal())) {
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
            if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                String salesUnits = projSelDTO.getSalesOrUnit();
                if (projSelDTO.isIsTotal()) {
                    if (projSelDTO.isIsProjectionTotal()) {
                        if (projectionTotalList.isEmpty() || projSelDTO.isIsFrequencyChanged() || projSelDTO.isIsHistroryChanged()) {
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
                        if (started == NumericConstants.TWO && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO demandDto = projectionTotalList.get(1);
                                projDTOList.add(demandDto);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (started == NumericConstants.THREE && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO inventoryDto = projectionTotalList.get(NumericConstants.TWO);
                                projDTOList.add(inventoryDto);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (started == NumericConstants.FOUR && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO perExFactoryDto = projectionTotalList.get(NumericConstants.THREE);
                                projDTOList.add(perExFactoryDto);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (started == NumericConstants.FIVE && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO perDemandDto = projectionTotalList.get(NumericConstants.FOUR);
                                projDTOList.add(perDemandDto);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (started == NumericConstants.SIX && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO perInventoryDto = projectionTotalList.get(NumericConstants.FIVE);
                                projDTOList.add(perInventoryDto);
                            }
                            started++;
                            neededRecord--;
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant()))) {
                            if (started == NumericConstants.SEVEN) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO contractSalesDto = projectionTotalList.get(NumericConstants.SIX);
                                    projDTOList.add(contractSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(UNITS.getConstant()))) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.EIGHT) || started == NumericConstants.SEVEN) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO unitVolDto = projectionTotalList.get(NumericConstants.SEVEN);
                                    projDTOList.add(unitVolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) && !projSelDTO.getGroup().contains(Constant.TOTAL_RPU_CAPS)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.NINE) || started == NumericConstants.EIGHT) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO discountPerDto = projectionTotalList.get(NumericConstants.EIGHT);
                                    projDTOList.add(discountPerDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE) && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.TEN) || started == NumericConstants.NINE) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO totalRPUDto = projectionTotalList.get(NumericConstants.NINE);
                                    projDTOList.add(totalRPUDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE) && !projSelDTO.getGroup().contains(Constant.TOTAL_RPU_CAPS)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.ELEVEN) || started == NumericConstants.TEN) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO discountDolDto = projectionTotalList.get(NumericConstants.TEN);
                                    projDTOList.add(discountDolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        // New Expandable row Discount % of Ex-Factory
                        if (neededRecord > 0 ) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.TWELVE) || started == NumericConstants.ELEVEN) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO disPerExfacDto = projectionTotalList.get(NumericConstants.ELEVEN); 
                                    projDTOList.add(disPerExfacDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.THIRTEEN) || started == NumericConstants.TWELVE) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO netSalesDto = projectionTotalList.get(NumericConstants.TWELVE); 
                                    projDTOList.add(netSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        
                        // New Level Net Sales % of Ex-Factory
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.FOURTEEN) || started == NumericConstants.THIRTEEN) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO netSalesExFacDto = projectionTotalList.get(NumericConstants.THIRTEEN); 
                                    projDTOList.add(netSalesExFacDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.FIFTEEN) || started == NumericConstants.FOURTEEN) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO cogsDto = projectionTotalList.get(NumericConstants.FOURTEEN); 
                                    projDTOList.add(cogsDto); 
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.SIXTEEN) || started == NumericConstants.FIFTEEN) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO netProfitDto = projectionTotalList.get(NumericConstants.FIFTEEN); 
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
                        if ((salesUnits.equals(BOTH.getConstant()) && started < NumericConstants.TWO) || started < 1) {
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
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT) && !projSelDTO.getGroup().contains(Constant.TOTAL_RPU_CAPS)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.TWO) || started == 1) {
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
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.THREE) || started == NumericConstants.TWO) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO totalRPUDtoList = list.get(1);
                                    projDTOList.add(totalRPUDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE) && !projSelDTO.getGroup().contains(Constant.TOTAL_RPU_CAPS)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.FOUR) || started == NumericConstants.THREE) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO discountDolarDtoList = list.get(NumericConstants.TWO);
                                    projDTOList.add(discountDolarDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        
//                      New Expandable row Discount % of Ex-Factory
                        if (neededRecord > 0 ) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.FIVE) || started == NumericConstants.FOUR) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO disPerExfacDtoList = list.get(3);
                                    projDTOList.add(disPerExfacDtoList);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                       
                        List<ProjectionResultsDTO> list1 = getNetSales(projSelDTO);
                        
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.SIX) || started == NumericConstants.FIVE) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO netSalesDto = list1.get(0);
                                    projDTOList.add(netSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
//                      New Level Net Sales % of Ex-Factory
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.SEVEN) || started == NumericConstants.SIX) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO netSalesExFacDto = list1.get(1); 
                                    projDTOList.add(netSalesExFacDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        List<ProjectionResultsDTO> cogsandNetProfit = getCOGSNetProfit(projSelDTO);
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.EIGHT) || started == NumericConstants.SEVEN) {
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
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.NINE) || started == NumericConstants.EIGHT) {
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
                    int ppaCount = + getPPACountValue(projSelDTO);

                    if (projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE) && !projSelDTO.getGroup().contains(Constant.DISCOUNT_PER_OF_EX_FACTORY_HEADER)) {
                        if (started < (projSelDTO.getDiscountNameList().size() + ppaCount)) {
                            List<ProjectionResultsDTO> discountPerDtoList;
                            if (projSelDTO.isIsProjectionTotal()) {
                                discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs);
                            } else {
                                discountPerDtoList = getTotalDiscountLevels(projSelDTO, NumericConstants.TWO);
                            }
                            for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                                    projDTOList.add(discountPerDtoList.get(k));
                                }
                                started++;
                            }
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size()+ ppaCount;
                    }
                    if (projSelDTO.getGroup().contains(Constant.TOTAL_RPU_CAPS)) {
                        if (started < (projSelDTO.getDiscountNameList().size()+ ppaCount)) {
                            List<ProjectionResultsDTO> discountPerDtoList;
                            if (projSelDTO.isIsProjectionTotal()) {
                                discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs);
                            } else {
                                discountPerDtoList = getTotalDiscountLevels(projSelDTO, NumericConstants.THREE);
                            }
                            for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                                    projDTOList.add(discountPerDtoList.get(k));
                                }
                                started++;
                            }
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size()+ ppaCount;
                    }
                    if (neededRecord > 0 && projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT)) {
                        if (started < (projSelDTO.getDiscountNameList().size()+ ppaCount)) {
                            List<ProjectionResultsDTO> discountPerDtoList;
                            if (projSelDTO.isIsProjectionTotal()) {
                                discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs);
                            } else {
                                discountPerDtoList = getTotalDiscountLevels(projSelDTO, 1);
                            }
                            for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                                    projDTOList.add(discountPerDtoList.get(k));
                                }
                                started++;
                            }
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size()+ ppaCount;
                    }
                     if (neededRecord > 0 && projSelDTO.getGroup().contains(Constant.DISCOUNT_PER_OF_EX_FACTORY_HEADER)) {
                        if (started < (projSelDTO.getDiscountNameList().size()+ ppaCount)) {
                            List<ProjectionResultsDTO> discountPerDtoList;
                            if (projSelDTO.isIsProjectionTotal()) {
                                discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs);
                            } else {
                                discountPerDtoList = getTotalDiscountLevels(projSelDTO, NumericConstants.FOUR);
                            }
                            for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                                    projDTOList.add(discountPerDtoList.get(k));
                                }
                                started++;
                            }
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size()+ ppaCount;
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
                        projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
                    } else {
                        projectionDtoList = getProjectionPivot(projSelDTO);
                    }
                    for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(projectionDtoList.get(k));
                        }
                        started++;
                    }
                }
                mayBeAdded += projSelDTO.getPeriodList().size();
            }
        }
        if (!(projSelDTO.isIsCustomHierarchy() && projSelDTO.getCustomId() == 0)) {
                if ((neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsFilter()) && ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                        && ((projSelDTO.isIsCustomHierarchy()) || (!projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY))))
                        && !projSelDTO.getGroup().startsWith(Constant.ALL)
                        && !projSelDTO.getGroup().contains(Constant.SALES_WITH_HYPHEN)
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
        LOGGER.info("= = = Ending getProjectionResults = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getConfiguredProjectionResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
        try {
            List<ProjectionResultsDTO> resultList;
            if (!projSelDTO.isIsFilter() || (parentId instanceof ProjectionResultsDTO)) {
                projSelDTO.setYear(Constant.ALL);

                if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
                    projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + Constant.AND_SMALL_SPACE + PROJECTIONS.getConstant());
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
                LOGGER.error(ex);

            return Collections.emptyList();
        }
    }

    public List<ProjectionResultsDTO> configureLevels(int start, int offset, int started, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        List<ProjectionResultsDTO> resultList = new ArrayList<>();
        if (neededRecord > 0) {
            resultList=getLevelListforNonmandated(start, offset, started, projSelDTO,neededRecord);
        }
        return resultList;
        }
        
    public int getConfiguredProjectionResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelsCount) {
        int count = 0;
        projSelDTO.setGroupCount(false);
        if (!projSelDTO.isIsFilter() || (parentId instanceof ProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.ALL);

            if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + Constant.AND_SMALL_SPACE + PROJECTIONS.getConstant());
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
         CommonLogic commonLogic = new CommonLogic();
         String query = StringUtils.EMPTY;
        if (!projSelDTO.getGroup().startsWith(Constant.ALL)
                && !projSelDTO.getGroup().contains(Constant.SALES_WITH_HYPHEN)
                && !projSelDTO.getGroup().contains(Constant.DISCOUNT)
                && !projSelDTO.getGroup().contains(Constant.PPA)) {
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                projSelDTO.setPpa(Boolean.FALSE);
                projSelDTO.setReturns(Boolean.FALSE);
                if (projSelDTO.isIsTotal()) {
                    count = count + NumericConstants.NINE; 
                    if (projSelDTO.isIsProjectionTotal()) {
                        count = count + NumericConstants.SEVEN;
                    }
                    if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant())) {
                        count++;
                    }
                }

                if (!projSelDTO.isIsTotal() && projSelDTO.isIsProjectionTotal()) {
                    count = count + projSelDTO.getDiscountNoList().size();
                    //PPA Count
                   count += getPPACountValue(projSelDTO);
                } else if (!projSelDTO.isIsTotal()) {
            String ccpQuery = commonLogic.insertAvailableHierarchyNo(projSelDTO);
             ccpQuery +=commonLogic.getGroupFilterJoinQuery(projSelDTO);
                     query+=ccpQuery + getDiscountCountForCurrentHierarchy(projSelDTO);
                    List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
                    if (list != null && !list.isEmpty()) {
                        Object ob = list.get(0);
                        count = count + Integer.valueOf(String.valueOf(ob));
                    }
                    query = ccpQuery + getPPACount(Boolean.FALSE);
                   list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
                    if (list != null && !list.isEmpty()) {
                        Object ob = list.get(0);
                        count = count + Integer.valueOf(String.valueOf(ob));
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
                    && !projSelDTO.getGroup().startsWith(Constant.ALL)
                    && !projSelDTO.getGroup().contains(Constant.SALES_WITH_HYPHEN)
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
        int levelCount=0;
                CommonLogic commonLogic = new CommonLogic();
                if (projSelDTO.isIsCustomHierarchy()) {
                    levelCount = commonLogic.getCountForCustomView(projSelDTO);
                } else {
                    levelCount = commonLogic.getCount(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyIndicator());
                }
        return levelCount;
    }

    public String getProjectionResultsDiscountsQuery(ProjectionSelectionDTO projSelDTO, String orderBy) {
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String whereClause = StringUtils.EMPTY;
        String groupBy = Constant.IYEAR_N;

        String customQuery;

        selectClause += " I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as  PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += Constant.IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS,  \n";
            whereClause += StringUtils.EMPTY;
            groupBy += Constant.ISEMI_ANNUAL;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += Constant.AS_PERIODS_SLASH_N;
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += IMONTH_AS_PERIODS_SLASH;
            whereClause += StringUtils.EMPTY;
            groupBy += Constant.IMONTH;
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
            whereClause += " and B.RS_CONTRACT_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ") \n";
        }
        selectClause += discountTypeColumnName + ", ";

        String customSql = "ST_NM_DISCOUNT_PROJ_MASTER B,\n"
                + "\"PERIOD\" I, \n"
                + " #SELECTED_HIERARCHY_NO CCP ";
        if (!projSelDTO.isIsTotal()) {
            customSql += ", RS_CONTRACT J \n";
        }
        customSql += "where  CCP.CCP_DETAILS_SID = B.CCP_DETAILS_SID"
                + " and A.CCP_DETAILS_SID = B.CCP_DETAILS_SID \n";
        if (!projSelDTO.isIsTotal()) {
            customSql += " and B.RS_CONTRACT_SID= J.RS_CONTRACT_SID \n";
        }
        customSql += " and A.RS_CONTRACT_SID = B.RS_CONTRACT_SID \n"
                + "and A.PERIOD_SID = I.PERIOD_SID and \n"
                + periodFilter
                + whereClause + " group by  " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as ACTUAL_SALES, \n"
                + " 0 as PROJECTION_SALES \n"
                + " from ST_NM_ACTUAL_DISCOUNT A, \n "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES , \n"
                + " sum(A.PROJECTION_SALES) as PROJECTION_SALES \n"
                + " from ST_NM_DISCOUNT_PROJECTION A, \n "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.HISTORY1, Constant.FUTURE, "on");
        String finalWhereCond = list.get(1) + " and HISTORY.DISCOUNTS=FUTURE.DISCOUNTS";
        String finalSelectClause = "select       " + list.get(0) + " Isnull(HISTORY.DISCOUNTS, FUTURE.DISCOUNTS) as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES ";
        customQuery = finalSelectClause + Constant.FROM_SLASH_N + historyQuery + "\n) HISTORY FULL OUTER JOIN  (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond + orderBy;

        return customQuery;
    }

    public String getProjectionResultsPPAQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String whereClause = StringUtils.EMPTY;
        String groupBy = Constant.IYEAR_N;
        String customQuery;
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER  as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += Constant.IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n ";
            whereClause += StringUtils.EMPTY;
            groupBy += Constant.ISEMI_ANNUAL;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += ZERO_AS_PERIODS_N;
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += IMONTH_AS_PERIODS_SLASH;
            whereClause += StringUtils.EMPTY;
            groupBy += Constant.IMONTH;
        }
        selectClause += "'PPA Discount' as DISCOUNTS,\n ";
        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql =" \"PERIOD\" I, \n"
                + " #SELECTED_HIERARCHY_NO CCP,\n"
                  + " ST_NM_ACTUAL_SALES SNAS "
                + " where CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID \n"
                + " and  A.PERIOD_SID = I.PERIOD_SID \n"
                + " AND A.CCP_DETAILS_SID = SNAS.CCP_DETAILS_SID\n"
                + "                                                    AND A.PERIOD_SID = SNAS.PERIOD_SID AND \n"
                + periodFilter
                + whereClause
                + " group by  " + groupBy;
        String customSqlFuture = " \"PERIOD\" I, \n"
                + " #SELECTED_HIERARCHY_NO CCP,\n"
                  + " ST_NM_SALES_PROJECTION SNSP "
                 + " where CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID \n"
                + " and  A.PERIOD_SID = I.PERIOD_SID \n"
                +" AND A.CCP_DETAILS_SID = SNSP.CCP_DETAILS_SID\n" +
"                                                    AND A.PERIOD_SID = SNSP.PERIOD_SID AND \n" 
                + periodFilter
                + whereClause
                + "  group by " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_DISCOUNT_DOLLAR) as ACTUAL_SALES,\n"
                + " 0 as PROJECTION_SALES "
                + ", 0 AS PPA_ACTUAL_RPU,\n"
                + "                                                    SUM(\n"
                + "                                            CASE\n"
                + "                                                            WHEN A.ACTUAL_DISCOUNT_DOLLAR <> 0 THEN SNAS.ACTUAL_SALES\n"
                + "                                                  ELSE 0\n"
                + "                                                    END\n"
                + "                                                    ) AS SALES_ACTUALS,\n"
                + "                                                    SUM(\n"
                + "                           CASE\n"
                + "                                                            WHEN A.ACTUAL_DISCOUNT_DOLLAR <> 0 THEN SNAS.ACTUAL_UNITS\n"
                + "                                                       ELSE 0\n"
                + "            END\n"
                + "                                                    ) AS UNITS_ACTUALS\n"
                + " from ST_NM_ACTUAL_PPA A,\n "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_DISCOUNT_DOLLAR) as PROJECTION_SALES , SUM(A.PROJECTION_RATE) AS PPA_PROJECTED_RPU,\n"
                + "                                              SUM(\n"
                + "                                                        CASE\n"
                + "                                                            WHEN A.PROJECTION_DISCOUNT_DOLLAR <> 0 THEN SNSP.PROJECTION_SALES\n"
                + "                                                   ELSE 0\n"
                + "                                            END\n"
                + "                                                    ) AS SALES_PROJECTED,\n"
                + "                                       SUM(\n"
                + "                                                        CASE\n"
                + "                                                            WHEN A.PROJECTION_DISCOUNT_DOLLAR <> 0 THEN SNSP.PROJECTION_UNITS\n"
                + "                                           ELSE 0\n"
                + "                       END\n"
                + "                                                    ) AS UNITS_PROJECTED \n"
                + " from ST_NM_PPA_PROJECTION A,\n "
                + customSqlFuture;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.HISTORY1, Constant.FUTURE, "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "'PPA Discount' as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES"
                + "              ,ISNULL(HISTORY.PPA_ACTUAL_RPU,0)AS PPA_ACTUAL_RPU\n"
                + "              ,ISNULL(FUTURE.PPA_PROJECTED_RPU,0)AS PPA_PROJECTED_RPU,ISNULL(\n"
                + "                                                HISTORY.SALES_ACTUALS,\n"
                + "                                              0\n"
                + "                                            ) AS SALES_ACTUALS,\n"
                + "                                        ISNULL(\n"
                + "                                                HISTORY.UNITS_ACTUALS,\n"
                + "                                   0\n"
                + "                                            ) AS UNITS_ACTUALS,\n"
                + "                                            ISNULL(\n"
                + "                                                FUTURE.SALES_PROJECTED,\n"
                + "                                               0\n"
                + "                                            ) AS SALES_PROJECTED,\n"
                + "                                            ISNULL(\n"
                + "                                                FUTURE.UNITS_PROJECTED,\n"
                + "                    0\n"
                + "                                            ) AS UNITS_PROJECTED ";

        customQuery = finalSelectClause + Constant.FROM_SLASH_N + historyQuery + "\n)  HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;
        return customQuery;
    }
    public static final String ZERO_AS_PERIODS_N = "'0' as PERIODS, \n";

    public String getProjectionResultsPPARPUQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String whereClause = StringUtils.EMPTY;
        String customQuery;
        selectClause += "I.\"YEAR\"  as  YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and  I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n  ";
            whereClause += StringUtils.EMPTY;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += ZERO_AS_PERIODS_N;
            whereClause += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += IMONTH_AS_PERIODS_SLASH;
            whereClause += StringUtils.EMPTY;
        }
        
        selectClause += "CASE WHEN @PROGRAM_TYPE = 'PROGRAM' THEN rs_name ELSE 'Price Protection' END ppa_DISCOUNTS ,\n ";
        // To filter the data according to selected period

        // To handle the scenario where any discount is not selected in program selection lookup
        String customSql = "ST_NM_PPA_PROJECTION_MASTER b\n"
                + " ON a.CCP_DETAILS_SID = b.CCP_DETAILS_SID "
                + "AND  A.RS_CONTRACT_SID=B.RS_CONTRACT_SID "
                + "INNER JOIN \"PERIOD\" I ON I.PERIOD_SID = A.PERIOD_SID \n"
                + " INNER JOIN RS_CONTRACT j"
                + " ON j.RS_CONTRACT_SID=a.RS_CONTRACT_SID \n"
                + " where \n"
                + "  I.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID "
                + " )a\n"
                + whereClause
                + " group by YEARs\n, periods\n,ppa_DISCOUNTS,RS_CONTRACT_SID";
        String customSqlFuture = "ST_NM_PPA_PROJECTION_MASTER b\n"
                + " ON a.CCP_DETAILS_SID = b.CCP_DETAILS_SID "
                + "AND  A.RS_CONTRACT_SID=B.RS_CONTRACT_SID INNER JOIN \"PERIOD\" I ON I.PERIOD_SID = A.PERIOD_SID \n"
                + " INNER JOIN RS_CONTRACT j"
                + " ON j.RS_CONTRACT_SID=a.RS_CONTRACT_SID \n"
                + "     where \n"
                + "  I.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID "
                + " )a\n"
                + whereClause
                + " group by YEARs\n, periods\n,ppa_DISCOUNTS,RS_CONTRACT_SID";

        String historyQuery = "SELECT  YEARS  , PERIODS ,  ppa_discounts,"
                + " sum(ACTUAL_DISCOUNT_DOLLAR) as ACTUAL_SALES,\n"
                + " sum(ACTUAL_PROJECTION_SALES) as PROJECTION_SALES,"
                + "SUM(ACTUAL_PROJECTION_RATE) AS PPA_ACTUAL_RPU,\n"
                + " RS_CONTRACT_SID  from (\n"
                + selectClause + " A.ACTUAL_DISCOUNT_DOLLAR , 0 as ACTUAL_PROJECTION_SALES\n,0 as ACTUAL_PROJECTION_RATE,\n"
                + " J.RS_CONTRACT_SID"
                + " from #SELECTED_HIERARCHY_NO CCP "
                + " INNER JOIN ST_NM_ACTUAL_PPA A ON\n "
                + "  A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID INNER JOIN "
                + customSql;
        String futureQuery = "SELECT  YEARS  , PERIODS , ppa_discounts,"
                + " 0 as ACTUAL_SALES,\n"
                + " sum(PROJECTION_DISCOUNT_DOLLAR) as PROJECTION_SALES,SUM(PROJECTION_RATE) AS PPA_PROJECTED_RPU,\n"
                + " RS_CONTRACT_SID  from (\n"
                + selectClause + " A.PROJECTION_DISCOUNT_DOLLAR , A.PROJECTION_SALES\n, a.PROJECTION_RATE,\n"
                + " J.RS_CONTRACT_SID"
                + "   from #SELECTED_HIERARCHY_NO CCP"
                + " INNER JOIN ST_NM_PPA_PROJECTION A ON\n "
                + " A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID INNER JOIN "
                + customSqlFuture;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.HISTORY1, Constant.FUTURE, "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "coalesce(history.ppa_DISCOUNTS ,future.ppa_DISCOUNTS) AS DISCOUNTS"
                + ",Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES"
                + ",ISNULL(HISTORY.PPA_ACTUAL_RPU,0)AS PPA_ACTUAL_RPU\n"
                + "              ,ISNULL(FUTURE.PPA_PROJECTED_RPU,0)AS PPA_PROJECTED_RPU,"
                + "ISNULL(HISTORY.RS_CONTRACT_SID,FUTURE.RS_CONTRACT_SID)AS RS_CONTRACT_SID  ";

        customQuery = finalSelectClause + Constant.FROM_SLASH_N + historyQuery + "\n) HISTORY  FULL OUTER JOIN (\n" + futureQuery + "\n)  FUTURE  \n  " + finalWhereCond +" AND HISTORY.ppa_DISCOUNTS=FUTURE.ppa_DISCOUNTS AND HISTORY.RS_CONTRACT_SID=FUTURE.RS_CONTRACT_SID )PPA ";
        return customQuery;
    }

    public String getProjectionResultsSalesQuery(ProjectionSelectionDTO projSelDTO,Boolean period) {
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String groupBy = " I.\"YEAR\"\n" ;
        String customQuery;
        selectClause += "I.\"YEAR\" as YEARS,  ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, \n";
            groupBy += Constant.IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS,    \n  ";
            groupBy += Constant.ISEMI_ANNUAL;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += ZERO_AS_PERIODS_N;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += IMONTH_AS_PERIODS_SLASH;
            groupBy += Constant.IMONTH;
        }

        // To filter the data according to selected period
        String periodFilter;
        if(period){
            periodFilter = " I.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID ";
        }else{
            periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);
        }

        String customSql = " \"PERIOD\" I ON I.PERIOD_SID = A.PERIOD_SID \n"
                + " where    \n"
                + periodFilter
                + "  group by  " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES,  \n"
                + " 0 as  SALES_PROJECTION_SALES, \n"
                + " sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, \n"
                + " 0 as PROJECTION_UNITS \n"
                + "    from #SELECTED_HIERARCHY_NO CCP"
                + " INNER JOIN ST_NM_ACTUAL_SALES A ON \n "
                + " A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID INNER JOIN "
                + customSql;
        String futureQuery = selectClause + " 0 as SALES_ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, \n"
                + " sum(A.PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + " from #SELECTED_HIERARCHY_NO CCP"
                + " INNER JOIN ST_NM_SALES_PROJECTION A ON\n"
                + " A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID INNER  JOIN "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.HISTORY1, Constant.FUTURE, "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "    select   " + list.get(0) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as SALES_ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as SALES_PROJECTION_SALES,"
                + "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,\n Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS  ";

        customQuery = finalSelectClause + Constant.FROM_SLASH_N + historyQuery + "\n) HISTORY FULL OUTER JOIN   (\n" + futureQuery + "\n)  FUTURE  \n " + finalWhereCond;

        return customQuery;
    }
    
    
    public String getProjectionResultsTotalDiscountSalesQuery(ProjectionSelectionDTO projSelDTO,Boolean period) {
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String groupBy = " I.\"YEAR\"\n" ;
        String customQuery;
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += " I.QUARTER as PERIODS, \n";
            groupBy += Constant.IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            groupBy += Constant.ISEMI_ANNUAL;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += ZERO_AS_PERIODS_N;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += IMONTH_AS_PERIODS_SLASH;
            groupBy += Constant.IMONTH;
        }

        // To filter the data according to selected period
        String periodFilter;
        if(period){
            periodFilter = " I.PERIOD_SID  BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID "
                    + " AND B.RS_CONTRACT_SID IN ("+CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false)+")";
        }else{
            periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO)+ " AND B.RS_CONTRACT_SID IN ("+CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false)+") ";
        }

        String customSql = " \"PERIOD\" I ON I.PERIOD_SID = A.PERIOD_SID \n"
                + "  where  \n"
                + periodFilter
                + " group by " + groupBy + " , B.RS_CONTRACT_SID , RS_NAME";

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                + " 0 as SALES_PROJECTION_SALES, \n"
                + " sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, \n"
                + " 0 as PROJECTION_UNITS \n"
                + ",B.RS_CONTRACT_SID \n"
                + ",RS_NAME "
                + " from #SELECTED_HIERARCHY_NO CCP"
                + " INNER JOIN ST_NM_ACTUAL_SALES A ON \n "
                + "  A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID "
                + " INNER JOIN ST_NM_DISCOUNT_PROJ_MASTER B on B.CCP_DETAILS_SID=A.CCP_DETAILS_SID "
                + " INNER JOIN RS_CONTRACT R\n"
                + " ON R.RS_CONTRACT_SID=B.RS_CONTRACT_SID INNER JOIN "
                + customSql;
        String futureQuery = selectClause + " 0 as SALES_ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, \n"
                + " sum(A.PROJECTION_UNITS)  as PROJECTION_UNITS \n"
                + ",B.RS_CONTRACT_SID \n"
                + ", RS_NAME"
                + "  from #SELECTED_HIERARCHY_NO CCP"
                + " INNER JOIN ST_NM_SALES_PROJECTION A ON\n"
                + " A.CCP_DETAILS_SID =  CCP.CCP_DETAILS_SID "
                + " INNER JOIN ST_NM_DISCOUNT_PROJ_MASTER B on B.CCP_DETAILS_SID=A.CCP_DETAILS_SID "
                + " INNER  JOIN RS_CONTRACT R\n"
                + " ON R.RS_CONTRACT_SID=B.RS_CONTRACT_SID INNER JOIN "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.HISTORY1, Constant.FUTURE, "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select  " + list.get(0) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as SALES_ACTUAL_SALES"
                + ", Isnull(FUTURE.SALES_PROJECTION_SALES"
                + ", HISTORY.SALES_PROJECTION_SALES) as SALES_PROJECTION_SALES"
                + ", Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS"
                + ", Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS"
                + ", Isnull(FUTURE.RS_CONTRACT_SID, HISTORY.RS_CONTRACT_SID) AS RS_CONTRACT_SID"
                + ", ISNULL(FUTURE.RS_NAME, HISTORY.RS_NAME) AS DISCOUNTS  ";

        customQuery = finalSelectClause + Constant.FROM_SLASH_N + historyQuery + "\n) HISTORY FULL  OUTER JOIN  (\n" + futureQuery + "\n) FUTURE \n  " + finalWhereCond +" AND history.RS_CONTRACT_SID=future.RS_CONTRACT_SID";

        return customQuery;
    }
    
    public String getProjectionResultsPPASalesQuery(ProjectionSelectionDTO projSelDTO,Boolean period) {
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String groupBy = "  I.\"YEAR\"\n" ;
        String customQuery;
        selectClause += "I.\"YEAR\"  as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as  PERIODS, \n";
            groupBy += Constant.IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            groupBy += Constant.ISEMI_ANNUAL;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += ZERO_AS_PERIODS_N;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += IMONTH_AS_PERIODS_SLASH;
            groupBy += Constant.IMONTH;
        }

        // To filter the data according to selected period
        String periodFilter;
        if(period){
            periodFilter = " I.PERIOD_SID BETWEEN  @START_PERIOD_SID AND @END_PERIOD_SID ";
        } else {
            periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);
        }

        String customSql = " \"PERIOD\" I  ON I.PERIOD_SID = A.PERIOD_SID \n"
                + " where  \n"
                + periodFilter
                + " group by " + groupBy + " , B.RS_CONTRACT_SID , RS_NAME";

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                + " 0 as SALES_PROJECTION_SALES, \n"
                + "  sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, \n"
                + " 0 as PROJECTION_UNITS \n"
                + ",B.RS_CONTRACT_SID\n"
                + ",RS_NAME"
                + " from #SELECTED_HIERARCHY_NO  CCP"
                + " INNER JOIN  ST_NM_ACTUAL_SALES A ON \n "
                + " A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID "
                + " INNER JOIN ST_NM_PPA_PROJECTION_MASTER B on B.CCP_DETAILS_SID=A.CCP_DETAILS_SID"
                + " INNER JOIN  RS_CONTRACT R\n"
                + "ON R.RS_CONTRACT_SID=B.RS_CONTRACT_SID INNER JOIN "
                + customSql;
        String futureQuery = selectClause + " 0 as  SALES_ACTUAL_SALES, \n"
                + "  sum(A.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS,  \n"
                + " sum(A.PROJECTION_UNITS) as  PROJECTION_UNITS \n"
                + ",B.RS_CONTRACT_SID\n"
                + ",RS_NAME"
                + " from  #SELECTED_HIERARCHY_NO CCP"
                + "  INNER JOIN ST_NM_SALES_PROJECTION A ON\n"
                + " A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID "
                + " INNER JOIN ST_NM_PPA_PROJECTION_MASTER B on B.CCP_DETAILS_SID=A.CCP_DETAILS_SID "
                + " INNER JOIN RS_CONTRACT R\n"
                + " ON R.RS_CONTRACT_SID=B.RS_CONTRACT_SID INNER  JOIN "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.HISTORY1, Constant.FUTURE, "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "  select " + list.get(0) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as SALES_ACTUAL_SALES"
                + ", Isnull(FUTURE.SALES_PROJECTION_SALES"
                + ", HISTORY.SALES_PROJECTION_SALES) as SALES_PROJECTION_SALES"
                + ", Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS"
                + ", Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS"
                + ", Isnull(FUTURE.RS_CONTRACT_SID, HISTORY.RS_CONTRACT_SID) AS RS_CONTRACT_SID"
                + ", ISNULL(FUTURE.RS_NAME, HISTORY.RS_NAME) AS DISCOUNTS  ";

        customQuery = finalSelectClause + Constant.FROM_SLASH_N + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE  \n" + finalWhereCond + " AND HISTORY.RS_CONTRACT_SID = FUTURE.RS_CONTRACT_SID";

        return customQuery;
    }

    public String getProjectionResultsPPAPerQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String customQuery;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.PPA_SMALL, "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'PPA Discount' as DISCOUNTS,\n";
        selectClause += " ACTUAL_RATE=Isnull(PPA.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n"
                + " PROJECTION_RATE=Isnull(PPA.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n";
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        customQuery = selectClause + Constant.FROM_SLASH_N + ppaQuery + "\n) PPA LEFT JOIN (\n" + salesQuery + Constant.CLOSE_SALE_SLASH_N + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsPPARPU(ProjectionSelectionDTO projSelDTO) {
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String customQuery;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.PPA_SMALL, "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'PPA Discount' as DISCOUNTS , rs_name\n";
        selectClause += " ,PPA_ACTUAL_RPU=(Isnull(PPA.PPA_ACTUAL_RPU / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0))\n"
                + "       ,PPA_PROJECTED_RPU=(Isnull(PPA.PPA_PROJECTED_RPU / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0))";
        String ppaQuery = getProjectionResultsPPARPUQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        customQuery = selectClause + Constant.FROM_SLASH_N + ppaQuery +"and history.ppa_DISCOUNTS=future.ppa_DISCOUNTS"+ "\n)  PPA LEFT JOIN (\n" + salesQuery + Constant.CLOSE_SALE_SLASH_N + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsTotalDiscountDolQuery(ProjectionSelectionDTO projSelDTO) {
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String customQuery;
        if (projSelDTO.isPpa()) {
            String selectClause = Constant.SELECT_SMALL_SPACE;

            List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, Constant.PPA_SMALL, "on");
            selectClause += list.get(0);
            String finalWhereCond = list.get(1);
            selectClause += "'Total Discount $' as DISCOUNTS, \n";
            selectClause += " ACTUAL_SALES=Isnull(TODIS.ACTUAL_SALES, 0)+Isnull(PPA.ACTUAL_SALES, 0),\n"
                    + "  PROJECTION_SALES=Isnull(TODIS.PROJECTION_SALES, 0)+Isnull(PPA.PROJECTION_SALES, 0) \n";

            String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
            customQuery = selectClause + Constant.FROM_SLASH_N + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN  (\n" + ppaQuery + "\n) PPA  \n" + finalWhereCond;

        } else {
            customQuery = totalDiscountQuery;
        }
        return customQuery;
    }

    public String getProjectionResultsDiscountsPerQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String customQuery;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "TODIS.DISCOUNTS, \n";
        selectClause += " ACTUAL_RATE=Isnull(TODIS.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n"
                + " PROJECTION_RATE=Isnull(TODIS.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        customQuery = selectClause + Constant.FROM_SLASH_N + totalDiscountQuery + "\n) TODIS LEFT JOIN (\n" + salesQuery + Constant.CLOSE_SALE_SLASH_N + finalWhereCond + "\n order by TODIS.DISCOUNTS";
        return customQuery;
    }

    public String getProjectionResultsTotalDiscountPerQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String customQuery;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", Constant.PPA_SMALL, "on", true);
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Total Discount %' as DISCOUNTS, \n";
        selectClause += " ACTUAL_RATE=Isnull((Isnull(TODIS.ACTUAL_SALES, 0)";

            selectClause += "+Isnull( PPA.ACTUAL_SALES, 0)";
        selectClause += ") / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n PROJECTION_RATE=Isnull((Isnull(TODIS.PROJECTION_SALES, 0)";
            selectClause += "+Isnull(PPA.PROJECTION_SALES, 0) ";

        selectClause += ") / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + Constant.FROM_SLASH_N + totalDiscountQuery + "\n)  TODIS FULL OUTER JOIN (\n" + salesQuery + Constant.CLOSE_SALE_SLASH_N + finalWhereCond;
            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL OUTER JOIN  (\n" + ppaQuery + "\n) PPA \n " + finalWhereCond1;

        return customQuery;
    }

    public String getProjectionResultsNetSalesQuery(ProjectionSelectionDTO projSelDTO) {
        
        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
        
        
        
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String customQuery;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", Constant.PPA_SMALL, "on", true);
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Net Sales' as NETSALES, \n";
        selectClause += " ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-(Isnull(TODIS.ACTUAL_SALES, 0)";

            selectClause += "+Isnull(PPA.ACTUAL_SALES , 0)";
        selectClause += ")),\n PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-(Isnull(TODIS.PROJECTION_SALES, 0)";
            selectClause += "+Isnull(PPA.PROJECTION_SALES, 0) ";

        selectClause += ")) \n";
        selectClause += " ,'Net Sales % of Ex-factory' as NETSALES_EX_FACTORY,\n"
                + " NETSALES_EX_FACTORY_ACTUALS=coalesce((Isnull(SALE.SALES_ACTUAL_SALES, 0)-(Isnull(TODIS.ACTUAL_SALES, 0)+Isnull(PPA.ACTUAL_SALES, 0)))/nullif(FILE_DATA.EXFACTORY_ACTUAL,0),0)*100,\n"
                + "NETSALES_EX_FACTORY_PROJECTED=coalesce((Isnull(SALE.SALES_PROJECTION_SALES, 0)-(Isnull(TODIS.PROJECTION_SALES, 0)+Isnull(PPA.PROJECTION_SALES, 0)))/nullif(file_data.EXFACTORY_FORECAST,0),0)*100 \n";
        
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + Constant.FROM_SLASH_N + totalDiscountQuery + "\n) TODIS  FULL OUTER JOIN (\n" + salesQuery + Constant.CLOSE_SALE_SLASH_N + finalWhereCond;

            String finalWhereCond1 = list.get(NumericConstants.TWO);
            
           String newjoin = "\n FULL OUTER JOIN \n"
                + " (select i.YEAR YEARS" + getselectedFrequencyPeriod(projSelDTO) +",sum(fd.EXFACTORY_ACTUAL_SALES) EXFACTORY_ACTUAL,sum(coalesce(fd.EXFACTORY_FORECAST_SALES,fd.EXFACTORY_ACTUAL_SALES)) EXFACTORY_FORECAST from "
                + projSelDTO.getSessionDTO().getCurrentTableNames().get(Constant.ST_PRODUCT_FILE) + " fd join period I on I.PERIOD_SID=fd.period_sid \n"
                + "where exists (select 1 from #SELECTED_HIERARCHY_NO s join ccp_details c on s.ccp_details_Sid=c.ccp_details_sid \n"
                + " where c.item_master_sid=fd.item_master_sid) \n"
                + " and  PERIOD_DATE BETWEEN ('"+startDate+"')  and  ('"+endDate+"') group by  I.\"YEAR\" \n"
                + getselectedFrequency(projSelDTO) + " ) file_data     on    file_data.YEARS = SALE.YEARS \n"
                + " AND file_data.PERIODS = SALE.PERIODS \n"; 
           
           
            customQuery += " FULL OUTER JOIN  (\n" + ppaQuery + " \n) PPA \n" + finalWhereCond1 + newjoin;
        return customQuery;
    }

    public String getCostQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = "  IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "    DROP TABLE #TEMP_CCP;\n"
                + "CREATE  TABLE #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID INT\n"
                + "     , CONTRACT_MASTER_SID INT \n"
                + "     , ITEM_MASTER_SID INT  \n"
                + "     , PROJECTION_DETAILS_SID INT \n"
                + "     , PROJECTION_MASTER_SID INT\n"
                + "     )\n"
                + "INSERT INTO #TEMP_CCP ( \n "
                + "       COMPANY_MASTER_SID \n "
                + "     , CONTRACT_MASTER_SID \n"
                + "     , ITEM_MASTER_SID \n"
                + "     , PROJECTION_DETAILS_SID\n"
                + "     , PROJECTION_MASTER_SID\n"
                + "    )\n"
                + "SELECT C.COMPANY_MASTER_SID \n"
                + "     , C.CONTRACT_MASTER_SID \n"
                + "     , C.ITEM_MASTER_SID \n"
                + "     , PD.PROJECTION_DETAILS_SID\n"
                + "     , pm.PROJECTION_MASTER_SID\n"
                + " FROM  \n"
                + "      CCP_DETAILS C\n"
                + "     , PROJECTION_DETAILS PD\n"
                + "     , PROJECTION_MASTER PM\n"
                + "WHERE PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID \n"
                + "     AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "     AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "	 and EXISTS (SELECT 1 FROM \n"
                + "	 @CCP CCP WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + "INSERT INTO @ITEMID \n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM\n"
                + "WHERE EXISTS (\n"
                + "            SELECT 1\n"
                + "            FROM #TEMP_CCP A\n"
                + "            WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                   AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "            ) ";
        selectClause += Constant.SELECT_SMALL_SPACE;
        String customQuery;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", Constant.PPA_SMALL, "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "COGS_ACTUAL = ISNULL(SALE.COGS_ACTUAL, 0)\n"
                + ", COGS_PROJECTED = ISNULL(SALE.COGS_PROJECTED, 0) \n"
                + ", NET_PROFIT_ACTUAL = ((Isnull(SALE.SALES_ACTUAL_SALES, 0) - (Isnull(TODIS.ACTUAL_SALES, 0) \n";

            selectClause += "+ Isnull(PPA.ACTUAL_SALES, 0)";
        selectClause += ")) - ISNULL(SALE.COGS_ACTUAL, 0))\n"
                + "  , NET_PROFIT_PROJECTED = ((Isnull(SALE.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.PROJECTION_SALES, 0)";
            selectClause += "+ ISNULL(PPA.PROJECTION_SALES, 0)";
         selectClause += ")) - ISNULL(SALE.COGS_PROJECTED, 0))";     
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + Constant.FROM_SLASH_N + totalDiscountQuery + "\n) TODIS FULL  OUTER JOIN (\n" + cogsQuery + Constant.CLOSE_SALE_SLASH_N + finalWhereCond;

            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n)  PPA  \n" + finalWhereCond1;
        return customQuery;
    }
   
    
    
    
    public String getCOGSNetProfitValue(ProjectionSelectionDTO projSelDTO) {
        String selectedPeriod = "";
        String groupBy = "  P.\"YEAR\" \n";
        String groupQuery = "  YEARS \n";


        selectedPeriod += "P.\"YEAR\" as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectedPeriod += "P.QUARTER as PERIODS, \n";
            groupBy += ", P.QUARTER";
            groupQuery += Constant.PERIODS_QUOTE;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectedPeriod += "P.SEMI_ANNUAL as PERIODS, \n";
            groupBy += ", P.SEMI_ANNUAL";
            groupQuery += Constant.PERIODS_QUOTE;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectedPeriod += Constant.AS_PERIODS_SLASH_N;
            groupBy += StringUtils.EMPTY;
            groupQuery += Constant.PERIODS_QUOTE;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectedPeriod += "P.\"MONTH\" as PERIODS, \n";
            groupBy += ", P.\"MONTH\"";
            groupQuery += Constant.PERIODS_QUOTE;
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
                + "     CCP_DETAILS_SID        INT\n"
                + "  )\n"
                + " \n"
                + "  INSERT INTO #TEMP_CCP\n"
                + "            (COMPANY_MASTER_SID,\n"
                + "             CONTRACT_MASTER_SID,\n"
                + "             ITEM_MASTER_SID,\n"
                + "             CCP_DETAILS_SID\n"
                + "            )\n"
                + "  SELECT C.COMPANY_MASTER_SID,\n"
                + "       C.CONTRACT_MASTER_SID,\n"
                + "       C.ITEM_MASTER_SID,\n"
                + "       C.CCP_DETAILS_SID\n"
                + "FROM   CCP_DETAILS C\n"
                + "WHERE  EXISTS (SELECT 1\n"
                + "                   FROM   #SELECTED_HIERARCHY_NO CCP\n"
                + "                   WHERE  CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)\n"
                + " \n"
                + "  DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + " \n"
                + "INSERT INTO  @ITEMID\n"
                + "SELECT DISTINCT IM.ITEM_MASTER_SID\n"
                + "FROM   ITEM_MASTER IM\n"
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
                + "                                         0 AS PROJECTION_SALES\n"
                + "                                  FROM   #TEMP_CCP T\n"
                + "                                  JOIN  ST_NM_ACTUAL_DISCOUNT A ON A.CCP_DETAILS_SID = T.CCP_DETAILS_SID\n"
                + "                              JOIN   PERIOD P ON P.PERIOD_SID = A.PERIOD_SID\n"
                + "                                  JOIN   #SELECTED_HIERARCHY_NO CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                +"                                   INNER JOIN RS_CONTRACT R\n" +
                                                     " ON A.RS_CONTRACT_SID = R.RS_CONTRACT_SID\n" +
                                                    " AND T.CONTRACT_MASTER_SID=R.CONTRACT_MASTER_SID"
                + "                                  WHERE   P.PERIOD_SID BETWEEN @START_SID AND @END_SID\n" 
                + "                                         AND A.RS_CONTRACT_SID IN (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " )\n"
                + "                                  GROUP  BY \n" + groupBy
                + "                                            ) HISTORY\n"
                + "                 FULL OUTER JOIN (SELECT \n" + selectedPeriod
                + "                                         \n"
                + "                                         'Total Discount $'      AS DISCOUNTS,\n"
                + "                                         0                       AS ACTUAL_SALES,\n"
                + "                                         Sum(A.PROJECTION_SALES) AS PROJECTION_SALES\n"
                + "                                  FROM   #TEMP_CCP T\n"
                + "                                  JOIN  ST_NM_DISCOUNT_PROJECTION A ON A.CCP_DETAILS_SID = T.CCP_DETAILS_SID\n"
                + "                                JOIN   PERIOD P ON P.PERIOD_SID = A.PERIOD_SID\n"
                + "                                  JOIN  #SELECTED_HIERARCHY_NO CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                +"                                   INNER JOIN RS_CONTRACT R\n" +
                 "                                   ON A.RS_CONTRACT_SID = R.RS_CONTRACT_SID\n" +
                 "                                   AND T.CONTRACT_MASTER_SID=R.CONTRACT_MASTER_SID   "        
                + "                                  WHERE  P.PERIOD_SID BETWEEN @START_SID AND @END_SID\n" 
                + "                                         AND A.RS_CONTRACT_SID IN ( " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " )\n"
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
                + "                                   0 AS SALES_PROJECTION_SALES,\n"
                + "                                   Sum(A.ACTUAL_UNITS)             AS ACTUAL_UNITS,\n"
                + "                                   0 AS PROJECTION_UNITS,\n"
                + "                                   Sum(COGS_ACTUAL)                AS COGS_ACTUAL\n"
                + "                            FROM   (SELECT \n" + selectedPeriod
                + "                                           \n"
                + "                                           A.ACTUAL_SALES,\n"
                + "                                           0 as HISTORY_PROJECTION_SALES,\n"
                + "                                           A.ACTUAL_UNITS,\n"
                + "                                           0 as HISTORY_PROJECTION_UNITS,\n"
                + "                                           COGS_ACTUAL = ( ISNULL(A.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) )\n"
                + "                                    FROM   #TEMP_CCP T\n"
                + "                                    JOIN  ST_NM_ACTUAL_SALES A ON A.CCP_DETAILS_SID = T.CCP_DETAILS_SID\n"
                + "                                    JOIN   PERIOD P ON P.PERIOD_SID = A.PERIOD_SID\n"
                + "                                    JOIN   #SELECTED_HIERARCHY_NO CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                    JOIN   [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U ON U.ITEM_MASTER_SID = T.ITEM_MASTER_SID\n"
                + "                                                                                                                                  AND U.PERIOD_SID = P.PERIOD_SID\n"
                + "                                    WHERE  P.PERIOD_SID BETWEEN @START_SID AND @END_SID \n"+" )A"
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
                + "                                    JOIN  ST_NM_SALES_PROJECTION A ON A.CCP_DETAILS_SID = T.CCP_DETAILS_SID\n"
                + "                                    JOIN   PERIOD P ON P.PERIOD_SID = A.PERIOD_SID\n"
                + "                                    JOIN   #SELECTED_HIERARCHY_NO CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                    JOIN   [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U ON U.ITEM_MASTER_SID = T.ITEM_MASTER_SID\n"
                + "                                                                                                                                  AND U.PERIOD_SID = P.PERIOD_SID\n"
                + "                                    WHERE  P.PERIOD_SID BETWEEN @START_SID AND @END_SID \n" +" )P"
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
                + "                                       \n"
                + "                                         'PPA Discount'                 AS DISCOUNTS,\n"
                + "                                         Sum(A.ACTUAL_DISCOUNT_DOLLAR)  AS ACTUAL_SALES,\n"
                + "                                         0 AS PROJECTION_SALES,\n"
                + "                                         0  AS PPA_ACTUAL_RPU\n"
                + "                                 FROM   #TEMP_CCP T\n"
                + "                                  JOIN  ST_NM_ACTUAL_PPA A ON A.CCP_DETAILS_SID = T.CCP_DETAILS_SID\n"
                + "                                  JOIN   PERIOD P ON P.PERIOD_SID = A.PERIOD_SID\n"
                + "                    JOIN   #SELECTED_HIERARCHY_NO CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                  WHERE P.PERIOD_SID BETWEEN @START_SID AND @END_SID\n" 
                + "             GROUP  BY \n" + groupBy
                + "                                            ) HISTORY\n"
                + "                 FULL OUTER JOIN (SELECT \n" + selectedPeriod
                + "                               \n"
                + "                                         'PPA Discount'                    AS DISCOUNTS,\n"
                + "                                         0                                 AS ACTUAL_SALES,\n"
                + "                                         Sum(A.PROJECTION_DISCOUNT_DOLLAR) AS PROJECTION_SALES,\n"
                + "                                         Sum(A.PROJECTION_RATE)            AS PPA_PROJECTED_RPU\n"
                + "                          FROM   #TEMP_CCP T\n"
                + "                                  JOIN  ST_NM_PPA_PROJECTION A ON A.CCP_DETAILS_SID = T.CCP_DETAILS_SID\n"
                + "                                  JOIN   PERIOD P ON P.PERIOD_SID = A.PERIOD_SID\n"
                + "                        JOIN   #SELECTED_HIERARCHY_NO CCP ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                  WHERE  P.PERIOD_SID BETWEEN @START_SID AND @END_SID\n" 
                + "                             GROUP  BY \n" + groupBy
                + "                                           ) FUTURE ON HISTORY.YEARS = FUTURE.YEARS\n"
                + "                                                             AND HISTORY.PERIODS = FUTURE.PERIODS) PPA ON TODIS.YEARS = PPA.YEARS\n"
                + "                                                                                                      AND TODIS.PERIODS = PPA.PERIODS";

        return query;
    }

    public String getNetProfitQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT  NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + " CREATE TABLE #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID INT\n"
                + "     , CONTRACT_MASTER_SID INT\n"
                + "     , ITEM_MASTER_SID INT\n"
                + "     , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "     , PROJECTION_MASTER_SID INT\n"
                + "   )\n"
                + "INSERT INTO #TEMP_CCP (\n"
                + " COMPANY_MASTER_SID\n"
                + "     , CONTRACT_MASTER_SID\n"
                + "     , ITEM_MASTER_SID\n"
                + "     , PROJECTION_DETAILS_SID\n"
                + "     , PROJECTION_MASTER_SID\n"
                + "    )\n"
                + " SELECT C.COMPANY_MASTER_SID\n"
                + "     , C.CONTRACT_MASTER_SID\n"
                + "     , C.ITEM_MASTER_SID\n"
                + "     , PD.PROJECTION_DETAILS_SID\n"
                + "     , pm.PROJECTION_MASTER_SID\n"
                + " FROM \n"
                + "    CCP_DETAILS C\n"
                + "     , PROJECTION_DETAILS PD\n"
                + "     , PROJECTION_MASTER PM\n"
                + "WHERE PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "     AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "     AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "	 and EXISTS (SELECT 1 FROM \n"
                + "	 @CCP CCP WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)"
                + " DECLARE @ITEMID [DBO].[UDT_ITEM] \n "
                + "INSERT INTO @ITEMID\n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM\n"
                + "WHERE EXISTS (\n"
                + "            SELECT 1\n"
                + "            FROM #TEMP_CCP A\n"
                + "            WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                   AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "            ) ";
        selectClause += Constant.SELECT_SMALL_SPACE;
        String customQuery;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", Constant.PPA_SMALL, "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Cost Of Goods Sold' as COGS, \n";

        selectClause += " COGS_ACTUAL = (ISNULL(SALE.ACTUAL_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n"
                + ", COGS_PROJECTED = (ISNULL(SALE.PROJECTION_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n";
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        customQuery = selectClause + Constant.FROM_SLASH_N + totalDiscountQuery + "\n) TODIS FULL OUTER  JOIN (\n" + salesQuery + Constant.CLOSE_SALE_SLASH_N + finalWhereCond + "\n";
        customQuery += " FULL OUTER JOIN (" + cogsQuery;

            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n " + finalWhereCond1;
        return customQuery;
    }

    public String getProjectionResultsPivotQuery(ProjectionSelectionDTO projSelDTO) {
        
        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
        
        projSelDTO.setIsTotal(true);
        String selectClause = " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U')  IS NOT NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + "CREATE TABLE #TEMP_CCP (\n"
                + "   COMPANY_MASTER_SID INT\n"
                + "     , CONTRACT_MASTER_SID INT\n"
                + "     , ITEM_MASTER_SID INT\n"
                + "     )\n"
                + "INSERT INTO #TEMP_CCP  (\n"
                + "  COMPANY_MASTER_SID\n"
                + "     , CONTRACT_MASTER_SID\n"
                + "     , ITEM_MASTER_SID\n"
                + "     ) \n"
                + "SELECT C.COMPANY_MASTER_SID\n "
                + "     , C.CONTRACT_MASTER_SID\n"
                + "     , C.ITEM_MASTER_SID\n"
                + "FROM \n"
                + "      CCP_DETAILS  C\n"
                + " WHERE EXISTS (SELECT 1 FROM #SELECTED_HIERARCHY_NO S WHERE S.CCP_DETAILS_SID=C.CCP_DETAILS_SID)\n"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM] \n "
                + "INSERT INTO   @ITEMID  \n"
                + "SELECT DISTINCT IM.ITEM_MASTER_SID\n"
                + "FROM  ITEM_MASTER IM\n";
                
        
        selectClause += "\n select ";
        String customQuery;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", Constant.PPA_SMALL, "on", true);

        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        String orderBy = list.get(NumericConstants.THREE);


        selectClause+=" SALE.SALES_ACTUAL_SALES                                  AS CONTRACT_ACTUAL_SALES,\n" +
"       SALE.SALES_PROJECTION_SALES                              AS CONTRACT_PROJECTION_SALES,\n" +
"       SALE.ACTUAL_UNITS                                        AS CONTRACT_ACTUAL_UNITS,\n" +
"       SALE.PROJECTION_UNITS                                    AS CONTRACT_PROJECTION_UNITS,\n" +
"       TOTAL_ACTUAL_RATE = Isnull(((Isnull(TODIS.ACTUAL_SALES, 0) + isnull(PPA.ACTUAL_SALES, 0)) / NULLIF(DIS_SALE.SALES_ACTUAL_SALES, 0)), 0) * 100, \n" +
"       TOTAL_PROJECTION_RATE = Isnull(((Isnull(TODIS.PROJECTION_SALES, 0) + isnull(PPA.PROJECTION_SALES, 0)) / NULLIF(DIS_SALE.SALES_PROJECTION_SALES, 0)), 0) * 100,\n" +
"       TOTAL_ACTUAL_DOLAR=( Isnull(TODIS.ACTUAL_SALES, 0) ) + Isnull(PPA.ACTUAL_SALES, 0),\n" +
"       TOTAL_PROJECTION_DOLAR=( Isnull(TODIS.PROJECTION_SALES, 0) ) + Isnull(PPA.PROJECTION_SALES, 0),\n" +
"       NET_ACTUAL_SALES=( Isnull(SALE.SALES_ACTUAL_SALES, 0) - ( Isnull(TODIS.ACTUAL_SALES, 0)\n" +
"                                                                 + Isnull( PPA.ACTUAL_SALES, 0 ) ) ),\n" +
"       NET_PROJECTION_SALES=( Isnull(SALE.SALES_PROJECTION_SALES, 0) - ( Isnull(TODIS.PROJECTION_SALES, 0)\n" +
"                                                                         + Isnull( PPA.PROJECTION_SALES, 0 ) ) ),\n" +
"       TOTAL_ACTUAL_RPU = Isnull(((Isnull(TODIS.ACTUAL_SALES, 0) + Isnull(PPA.ACTUAL_SALES, 0)) / NULLIF(DIS_SALE.ACTUAL_UNITS, 0)), 0),\n" +
"       TOTAL_PROJECTION_RPU = Isnull(((Isnull(TODIS.PROJECTION_SALES, 0) + Isnull(PPA.PROJECTION_SALES, 0)) / NULLIF(DIS_SALE.PROJECTION_UNITS, 0)), 0),\n" +
"       COGS_ACTUAL = Isnull(SALE.COGS_ACTUAL, 0),\n" +
"       COGS_PROJECTED = Isnull(SALE.COGS_PROJECTED, 0),\n" +
"       NET_PROFIT_ACTUAL = ( ( Isnull(SALE.SALES_ACTUAL_SALES, 0) - ( Isnull(TODIS.ACTUAL_SALES, 0)\n" +
"                                                                      + Isnull( PPA.ACTUAL_SALES, 0 ) ) ) - Isnull(SALE.COGS_ACTUAL, 0) ),\n" +
"       NET_PROFIT_PROJECTED = ( ( Isnull(SALE.SALES_PROJECTION_SALES, 0) - ( Isnull(TODIS.PROJECTION_SALES, 0)\n" +
"                                                                             + Isnull( PPA.PROJECTION_SALES, 0 ) ) ) - Isnull(SALE.COGS_PROJECTED, 0) ),\n" +
"       PPA.ACTUAL_SALES                                         AS PPA_ACTUAL_SALES,\n" +
"       PPA.PROJECTION_SALES                                     AS PPA_PROJECTION_SALES,\n" +
"       PPA_ACTUAL_RATE = Isnull(isnull(PPA.ACTUAL_SALES,0) / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100,\n" +
"       PPA_PROJECTION_RATE = Isnull(isnull(PPA.PROJECTION_SALES,0) / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100,\n" +
"       PPA_ACTUAL_RPU = (Isnull(isnull(PPA.ACTUAL_SALES,0) / NULLIF(SALE.ACTUAL_UNITS, 0), 0)),\n" +
"       PPA_PROJECTED_RPU = (Isnull(isnull(PPA.PROJECTION_SALES,0) / NULLIF(SALE.PROJECTION_UNITS, 0), 0)) \n " +
"       ,NET_SALES_OF_EX_FACTORY_ACTUALS=coalesce((( Isnull(SALE.SALES_ACTUAL_SALES, 0) - ( Isnull(TODIS.ACTUAL_SALES, 0) \n" +
"       + Isnull( PPA.ACTUAL_SALES, 0 ) ) ))/nullif(file_data.EXFACTORY_ACTUAL,0),0)*100 \n" +
"       ,NET_SALES_OF_EX_FACTORY_PROJECTED=coalesce(((Isnull(SALE.SALES_PROJECTION_SALES, 0) - ( Isnull(TODIS.PROJECTION_SALES, 0) \n" +
"       + Isnull( PPA.PROJECTION_SALES, 0 ) ) ))/nullif(file_data.EXFACTORY_FORECAST,0),0)*100 \n" +
"       ,DISCOUNT_OF_EX_FACTORY_ACTUALS=COALESCE(((Isnull(TODIS.ACTUAL_SALES, 0) + Isnull(PPA.ACTUAL_SALES, 0))/NULLIF(file_data.EXFACTORY_ACTUAL,0)),0)*100 \n"  + 
"       ,DISCOUNT_OF_EX_FACTORY_PROJECTED=COALESCE(((Isnull(TODIS.PROJECTION_SALES, 0) + Isnull(PPA.PROJECTION_SALES, 0))/NULLIF(file_data.EXFACTORY_FORECAST,0)),0)*100 ";  
        
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsReturnsQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        String totalDiscountLevelQuery = getProjectionResultsPivotTotalDiscountsQuery(projSelDTO);

        customQuery = selectClause + Constant.FROM_SLASH_N + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n " + salesQuery + Constant.CLOSE_SALE_SLASH_N + finalWhereCond + " \n";
        customQuery += " FULL OUTER  JOIN (\n" + totalDiscountLevelQuery + "\n ) DIS_SALE ON TODIS.YEARS = DIS_SALE.YEARS\n"
                + "       AND TODIS.PERIODS = DIS_SALE.PERIODS";
        customQuery += " FULL OUTER  JOIN (\n" + ppaQuery + "\n) PPA \n ON PPA.YEARS = SALE.YEARS\n"
                + "		AND PPA.PERIODS = SALE.PERIODS \n"
                + "		FULL OUTER JOIN \n"
                + "		(select i.YEAR YEARS" + getselectedFrequencyPeriod(projSelDTO) +",sum(fd.EXFACTORY_ACTUAL_SALES) EXFACTORY_ACTUAL,sum(fd.EXFACTORY_FORECAST_SALES) EXFACTORY_FORECAST from \n"
                + projSelDTO.getSessionDTO().getCurrentTableNames().get(Constant.ST_PRODUCT_FILE) + "  fd join period I on I.PERIOD_SID=fd.period_sid \n"
                + "   and  PERIOD_DATE BETWEEN ('"+startDate+"')  and  ('"+endDate+"') "
                + " where exists (select * from #TEMP_CCP tc where tc.item_master_sid=fd.item_master_sid )"
                + " group by  I.\"YEAR\"\n"   
                + getselectedFrequency(projSelDTO) + " ) file_data     on    file_data.YEARS = SALE.YEARS \n"  
                + "	AND file_data.PERIODS = SALE.PERIODS\n";
        customQuery += " order by  " + orderBy;

        return customQuery;
    }

    public String getProjectionResultsReturnsQuery(ProjectionSelectionDTO projSelDTO) {
        String selectPeriod = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectPeriod = Constant.IQUARTER_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectPeriod = Constant.ISEMI_ANNUAL_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = Constant.ZERO_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectPeriod = Constant.IMONTH_AS_PERIODS;
        }

        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customQuery = "SELECT Isnull(HISTORY.YEARS, FUTURE.YEARS) AS YEARS \n"
                + "              , Isnull(HISTORY.PERIODS, FUTURE.PERIODS) AS PERIODS \n"
                + "              , Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) AS SALES_ACTUAL_SALES \n"
                + "              , Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES \n"
                + "              , Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) AS ACTUAL_UNITS \n"
                + "              , Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) AS PROJECTION_UNITS \n"
                + "              , Isnull(HISTORY.COGS_ACTUAL, 0) AS COGS_ACTUAL\n"
                + "              , Isnull(FUTURE.COGS_PROJECTED, 0) AS COGS_PROJECTED\n"
                + "       FROM (\n "
                + "              SELECT YEARS\n "
                + "                     , PERIODS \n "
                + "                     , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES \n "
                + "                     , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES \n "
                + "                     , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS \n "
                + "                     , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS \n "
                + "                     , SUM(COGS_ACTUAL) AS COGS_ACTUAL\n "
                + "                     FROM\n "
                + "                     (SELECT I.\"YEAR\" AS YEARS\n "
                + "                    " + selectPeriod + "\n"
                + "                     , A.ACTUAL_SALES \n"
                + "                     , 0 as HISTORY_PROJECTION_SALES \n"
                + "                     , A.ACTUAL_UNITS \n"
                + "                     , 0 as HISTORY_PROJECTION_UNITS \n"
                + "                     , COGS_ACTUAL = (ISNULL(A.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                + "              FROM ST_NM_ACTUAL_SALES A\n"
                + "                INNER JOIN PERIOD I\n"
                + "          ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     INNER JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "                           ON  CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "              INNER JOIN CCP_DETAILS CC\n"
                + "                           ON CC.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "                                 AND A.PERIOD_SID = I.PERIOD_SID\n"

                + "                     INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U\n"
                + "                           ON U.ITEM_MASTER_SID = CC.ITEM_MASTER_SID\n"
                + "                                  AND A.PERIOD_SID = U.PERIOD_SID\n"
                + "                     WHERE A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID AND " + periodFilter + "\n"
                + "                     ) A\n"
                + "              GROUP  BY YEARS\n"
                + Constant.PERIODS_SPACE_QUERY
                + "              ) HISTORY \n"
                + "        FULL JOIN (\n"
                + "          SELECT YEARS\n"
                + Constant.PERIODS_SPACE_QUERY
                + "                     , 0 AS SALES_ACTUAL_SALES \n"
                + "                     , sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES \n"
                + "                     , 0 AS ACTUAL_UNITS \n"
                + "                     , sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS \n"
                + "                     , SUM(COGS_PROJECTED) AS COGS_PROJECTED\n"
                + "          FROM (\n"
                + "    SELECT I.\"YEAR\" AS YEARS\n"
                + "                    " + selectPeriod + "\n"
                + "                           , A.PROJECTION_SALES \n"
                + "                           , A.PROJECTION_UNITS \n"
                + "                           , COGS_PROJECTED = (ISNULL(A.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                + "                     FROM ST_NM_SALES_PROJECTION A\n"
                + "                       INNER JOIN PERIOD I\n"
                + "                           ON A.PERIOD_SID =  I.PERIOD_SID\n"
                + "                     INNER JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "                             ON  CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "                    INNER JOIN CCP_DETAILS CC\n"
                + "                           ON CC.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "             AND A.PERIOD_SID = I.PERIOD_SID\n"

                + "                     INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U\n"
                + "                           ON U.ITEM_MASTER_SID = CC.ITEM_MASTER_SID\n"
                + "                                  AND A.PERIOD_SID = U.PERIOD_SID\n"
                + "                     WHERE  A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID AND " + periodFilter + "\n"
                + "                     ) P\n"
                + "            GROUP BY YEARS\n"
                + Constant.PERIODS_SPACE_QUERY
                + "              ) FUTURE \n"
                + "              ON HISTORY.YEARS = FUTURE.YEARS \n"
                + "                     AND HISTORY.PERIODS = FUTURE.PERIODS";
        return customQuery;
    }
    
    public String getProjectionResultsPivotTotalDiscountsQuery(ProjectionSelectionDTO projSelDTO) {
        String selectPeriod = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectPeriod = Constant.IQUARTER_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectPeriod = Constant.ISEMI_ANNUAL_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = Constant.ZERO_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectPeriod = Constant.IMONTH_AS_PERIODS;
        }

        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customQuery = "SELECT Isnull(HISTORY.YEARS, FUTURE.YEARS) AS YEARS\n"
                + "              , Isnull(HISTORY.PERIODS, FUTURE.PERIODS) AS PERIODS \n"
                + "              , Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) AS SALES_ACTUAL_SALES \n"
                + "              , Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES \n"
                + "              , Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) AS ACTUAL_UNITS \n"
                + "              , Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) AS PROJECTION_UNITS \n"
                + "       FROM (\n"
                + "             SELECT YEARS\n"
                + Constant.PERIODS_SPACE_QUERY
                + "                 , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                   , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                    , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     FROM\n"
                + "                     (SELECT I.\"YEAR\" AS YEARS\n"
                + "                     " + selectPeriod + "\n"
                + "                     , A.ACTUAL_SALES \n"
                + "                     , 0 as HISTORY_PROJECTION_SALES \n"
                + "                     , A.ACTUAL_UNITS \n"
                + "                     , 0 as HISTORY_PROJECTION_UNITS \n"
                + "              FROM ST_NM_ACTUAL_SALES A\n"
                + "                     INNER JOIN PERIOD I\n"
                + "                           ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                    INNER JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "                           ON  CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "                     INNER JOIN CCP_DETAILS CC\n"
                + "                          ON CC.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "                                  AND A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     WHERE A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID AND " + periodFilter + "\n"
                + "                     AND exists (SELECT 1 from ST_NM_DISCOUNT_PROJ_MASTER b\n"
                + "                                  where CCP.CCP_DETAILS_SID=b.ccp_details_sid\n"
                + "                                  and B.RS_CONTRACT_SID IN (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + "))  ) A\n"
                + " GROUP BY YEARS\n"
                + Constant.PERIODS_SPACE_QUERY
                + "              ) HISTORY \n"
                + "       FULL JOIN  (\n"
                + "  SELECT YEARS\n"
                + Constant.PERIODS_SPACE_QUERY
                + "                     , 0 AS SALES_ACTUAL_SALES \n"
                + "                   , sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , 0 AS ACTUAL_UNITS\n"
                + "                     , sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "             FROM (\n"
                + "                     SELECT I.\"YEAR\" AS YEARS\n"
                + "        " + selectPeriod + "\n"
                + "                           , A.PROJECTION_SALES\n"
                + "                           , A.PROJECTION_UNITS\n"
                + "                      FROM ST_NM_SALES_PROJECTION A\n"
                + " INNER JOIN PERIOD I\n"
                + "                           ON A.PERIOD_SID =   I.PERIOD_SID\n"
                + "                INNER JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "                             ON  CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "                     INNER JOIN CCP_DETAILS CC\n"
                + "               ON CC.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "                                  AND A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     WHERE  A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID AND " + periodFilter + "\n"
                + "                     AND exists (SELECT 1 from ST_NM_DISCOUNT_PROJ_MASTER b\n"
                + "                                  where CCP.CCP_DETAILS_SID=b.ccp_details_sid\n"
                + "                                  and B.RS_CONTRACT_SID IN (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")) "
                + ") P\n"
                + "               GROUP BY YEARS \n"
                + Constant.PERIODS_SPACE_QUERY
                + "              ) FUTURE \n"
                + "              ON HISTORY.YEARS = FUTURE.YEARS\n"
                + "                     AND HISTORY.PERIODS = FUTURE.PERIODS";
        return customQuery;
    }

    public String getProjectionResultsCOGSQuery(ProjectionSelectionDTO projSelDTO) {
        String selectPeriod = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectPeriod = Constant.IQUARTER_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectPeriod = Constant.ISEMI_ANNUAL_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = Constant.ZERO_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectPeriod = Constant.IMONTH_AS_PERIODS;
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
                + "     FROM (\n"
                + "              SELECT  YEARS\n"
                + "                      ,  PERIODS \n"
                + "                  , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                   , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                     , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     ,SUM(COGS_ACTUAL)AS COGS_ACTUAL\n"
                + "                     FROM\n"
                + "    (SELECT I.\"YEAR\" AS YEARS\n"
                + "   " + selectPeriod + "\n"
                + "                     , A.ACTUAL_SALES\n"
                + "                     , A.HISTORY_PROJECTION_SALES\n"
                + "                     , A.ACTUAL_UNITS\n"
                + "                     , A.HISTORY_PROJECTION_UNITS\n"
                + "                     , COGS_ACTUAL = (ISNULL(A.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                + "            FROM ST_NM_ACTUAL_SALES A\n"
                + "                     , \"PERIOD\" I\n"
                + "                     , #SELECTED_HIERARCHY_NO CCP\n"
                + "                     ,ccp_details cc\n"

                + "                     ,[DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U\n"
                + "              WHERE CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "                     AND CC.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "                     AND CC.ITEM_MASTER_SID=U.ITEM_MASTER_SID\n"
                + "                     AND A.PERIOD_SID=U.PERIOD_SID\n"
                + "                     AND A.PERIOD_SID = I.PERIOD_SID AND \n" + periodFilter + ")A\n"
                + "              GROUP BY YEARS\n"
                + "                   ,  PERIODS\n"
                + "              ) HISTORY\n"
                + "       FULL JOIN(\n"
                + "              SELECT  YEARS\n"
                + "                     ,   PERIODS\n"
                + "                     ,0 AS SALES_ACTUAL_SALES\n"
                + "                     , sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     ,0 AS ACTUAL_UNITS\n"
                + "                     , sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     ,SUM(COGS_PROJECTED) AS COGS_PROJECTED\n"
                + "                       FROM\n"
                + "                     (SELECT I.\"YEAR\" AS YEARS\n"
                + "         " + selectPeriod + "\n"
                + "                     , A.PROJECTION_SALES\n"
                + "                     , A.PROJECTION_UNITS\n"
                + "                     , COGS_PROJECTED = (ISNULL(A.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                + "              FROM ST_NM_SALES_PROJECTION A\n"
                + "                     , \"PERIOD\" I\n"
                + "                     , #SELECTED_HIERARCHY_NO CCP\n"
                + "                     ,ccp_details cc\n"

                + "                     ,[DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH') U\n"
                + "              WHERE  CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "                     AND CC.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "                     AND CC.ITEM_MASTER_SID=U.ITEM_MASTER_SID\n"
                + "                     AND A.PERIOD_SID=U.PERIOD_SID\n"
                + "                     AND A.PERIOD_SID = I.PERIOD_SID AND \n" + periodFilter + ")P\n"
                + "              GROUP BY  YEARS\n"
                + "                     ,  PERIODS\n"
                + "           ) FUTURE \n"
                + "              ON HISTORY.YEARS = FUTURE.YEARS\n"
                + " AND HISTORY.PERIODS = FUTURE.PERIODS";
        return customQuery;

    }

    public String getProjectionResultsTotalDiscountsQuery(ProjectionSelectionDTO projSelDTO) {
        String selectPeriod = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectPeriod = Constant.IQUARTER_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectPeriod = Constant.ISEMI_ANNUAL_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = Constant.ZERO_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectPeriod = Constant.IMONTH_AS_PERIODS;
        }

        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customQuery = "SELECT    ISNULL(HISTORY.YEARS, FUTURE.YEARS)                                   AS YEARS,\n"
                + "                     ISNULL(HISTORY.PERIODS, FUTURE.PERIODS)                               AS PERIODS,\n"
                + "                     ISNULL(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES)         AS SALES_ACTUAL_SALES,\n"
                + "                     ISNULL(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES,\n"
                + "                     ISNULL(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS)                     AS ACTUAL_UNITS,\n"
                + "                     ISNULL(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS)             AS PROJECTION_UNITS\n"
         
                
                
                + "       FROM  (\n"
                + "              SELECT   YEARS\n"
                + "                      ,  PERIODS \n"
                + "                  , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                     , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                    , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                     , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS \n"
                
                
                
                + "                   FROM\n"
                + "                    (SELECT I.\"YEAR\" AS YEARS\n"
                + "                     " + selectPeriod + "\n"
                + "                     , A.ACTUAL_SALES\n"
                + "                     , 0 as HISTORY_PROJECTION_SALES\n"
                + "                     , A.ACTUAL_UNITS\n"
                + "                     , 0 as HISTORY_PROJECTION_UNITS\n"
               
                
              
                
                + "             FROM ST_NM_ACTUAL_SALES A\n"
                + "       INNER JOIN PERIOD I\n"
                + "              ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "       INNER JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "              ON CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
              
                
              
                + "       WHERE  " + periodFilter + "\n"
                + " AND exists (select 1 from ST_NM_DISCOUNT_PROJ_MASTER b\n"
                + "					where CCP.CCP_DETAILS_SID=b.ccp_details_sid\n"
                + "					and B.RS_CONTRACT_SID IN ("+CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false)+"))"
                
                + "       ) A\n"
                + "GROUP BY YEARS\n"
                + "       , PERIODS       "
                + "              ) HISTORY\n"
                + "   FULL JOIN (\n"
                + "              SELECT  YEARS,\n"
                + "                             PERIODS,\n"
                + "                             Sum(P.SALES_ACTUAL_SALES)AS SALES_ACTUAL_SALES,\n"
                + "                             Sum(P.PROJECTION_SALES)  AS SALES_PROJECTION_SALES,\n"
                + "                             Sum(ACTUAL_UNITS)        AS ACTUAL_UNITS,\n"
                + "                             Sum(P.PROJECTION_UNITS)  AS PROJECTION_UNITS\n"
               
                + "          FROM\n"
                + "            (SELECT I.\"YEAR\" AS YEARS\n"
                + "                " + selectPeriod + "\n"
                + "                     , 0         AS SALES_ACTUAL_SALES,\n"
                + "                                     A.PROJECTION_SALES,\n"
                + "                                     0         AS ACTUAL_UNITS,\n"
                + "                                     A.PROJECTION_UNITS \n"
               
                
                + "              FROM ST_NM_SALES_PROJECTION A\n"
                + "       INNER JOIN PERIOD I\n"
                + "              ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "       INNER JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "              ON CCP.CCP_DETAILS_SID = A.CCP_DETAILS_SID\n"
                + "       WHERE " + periodFilter + " AND exists (select 1 from ST_NM_DISCOUNT_PROJ_MASTER b\n"
                + "					where CCP.CCP_DETAILS_SID=b.ccp_details_sid\n"
                + "					and B.RS_CONTRACT_SID IN (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")) )P \n"
                + "              GROUP BY  YEARS\n"
                + Constant.PERIODS_SPACE_QUERY
                + "              ) FUTURE\n"
                + "             ON HISTORY.YEARS = FUTURE.YEARS\n"
                + " AND HISTORY.PERIODS = FUTURE.PERIODS";
        return customQuery;

    }

    public String getCOGSQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String customQuery;
        String ppaActuals;
        String ppaProj;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", Constant.PPA_SMALL, "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        String orderBy = list.get(NumericConstants.THREE);

            ppaActuals = "+Isnull( PPA.ACTUAL_SALES , 0)";
            ppaProj = "+Isnull(PPA.PROJECTION_SALES , 0)";
        selectClause += " SALE.SALES_ACTUAL_SALES as CONTRACT_ACTUAL_SALES \n"
                + ", SALE.SALES_PROJECTION_SALES as CONTRACT_PROJECTION_SALES \n"
                + ", SALE.ACTUAL_UNITS as CONTRACT_ACTUAL_UNITS \n"
                + ", SALE.PROJECTION_UNITS as CONTRACT_PROJECTION_UNITS \n"
                + ", TOTAL_ACTUAL_RATE=Isnull((Isnull(TODIS.ACTUAL_SALES, 0)" + ppaActuals + ") / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 \n"
                + ", TOTAL_PROJECTION_RATE=Isnull((Isnull(TODIS.PROJECTION_SALES, 0)" + ppaProj + ") / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n"
                + ", TOTAL_ACTUAL_DOLAR=(Isnull(TODIS.ACTUAL_SALES, 0)" + ppaActuals + ") \n"
                + ", TOTAL_PROJECTION_DOLAR=(Isnull(TODIS.PROJECTION_SALES, 0)" + ppaProj + ") \n"
                + ", NET_ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-(Isnull(TODIS.ACTUAL_SALES, 0)" + ppaActuals + "))  \n"
                + ", NET_PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-(Isnull(TODIS.PROJECTION_SALES, 0)" + ppaProj + ")) \n"
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


        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        customQuery = selectClause + Constant.FROM_SLASH_N + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + Constant.CLOSE_SALE_SLASH_N + finalWhereCond + " \n";
        customQuery += " FULL OUTER JOIN (" + cogsQuery;

            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL  OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        customQuery += " order by " + orderBy;

        return customQuery;
    }

    public String getProjectionResultsDiscountsPivotQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(false);
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String customQuery;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", "on");
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

        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,Boolean.TRUE);
        String ppaQuery = getProjectionResultsPPARPUQuery(projSelDTO);
        customQuery = selectClause + " from (" + totalDiscountQuery + ") TODIS LEFT JOIN ("+ppaQuery+" LEFT JOIN (" + salesQuery + ") SALE " + finalWhereCond + " order by " + orderBy;
        projSelDTO.setIsTotal(true);
        return customQuery;
    }

    public String getProjectionResultsTotalTotalRPU(ProjectionSelectionDTO projSelDTO) {

        String selectClause = Constant.SELECT_SMALL_SPACE;
        String customQuery;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", Constant.PPA_SMALL, "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += "'Total RPU' as DISCOUNTS, \n";
        selectClause += " ACTUAL_RPU=ISNULL((ISNULL(TODIS.ACTUAL_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.ACTUAL_SALES, 0)";
        }
        selectClause += ") / NULLIF(SALE.ACTUAL_UNITS, 0), 0) ,\n PROJECTION_RPU=Isnull((Isnull(TODIS.PROJECTION_SALES, 0)";
        if (projSelDTO.isPpa()) {
            selectClause += "+Isnull(PPA.PROJECTION_SALES , 0)";
        }
        selectClause += ") / NULLIF(SALE.PROJECTION_UNITS, 0), 0) \n";
        String totalDiscountQuery = getProjectionResultsDiscountsRPUQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO,false);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + Constant.FROM_SLASH_N + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + Constant.CLOSE_SALE_SLASH_N + finalWhereCond;
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
            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += "   FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        return customQuery;
    }

    public String getProjectionResultsDiscountsRPUQuery(ProjectionSelectionDTO projSelDTO, String orderBy) {
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String whereClause = StringUtils.EMPTY;
        String groupBy = Constant.IYEAR_N;

        String customQuery;

        selectClause += "I.\"YEAR\" as  YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += "  and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER  as  PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += Constant.IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL  as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += Constant.ISEMI_ANNUAL;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += Constant.AS_PERIODS_SLASH_N;
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += IMONTH_AS_PERIODS_SLASH;
            whereClause += StringUtils.EMPTY;
            groupBy += Constant.IMONTH;
        }


        // To handle the scenario where any discount is not selected in program selection lookup
        String discountTypeColumnName = "RS_NAME as DISCOUNTS \n";
        if ((projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) && (!projSelDTO.isIsTotal())) {
                discountTypeColumnName = " J.RS_NAME as DISCOUNTS \n";
        }
        selectClause += discountTypeColumnName + ", ";

        String customSql = "ST_NM_DISCOUNT_PROJ_MASTER B ON \n";
        customSql += " A.CCP_DETAILS_SID = B.CCP_DETAILS_SID \n"
                + " AND A.RS_CONTRACT_SID=B.RS_CONTRACT_SID"
                + " INNER JOIN   PERIOD I ON I.PERIOD_SID = A.PERIOD_SID "
                + " INNER JOIN   RS_CONTRACT J ON J.RS_CONTRACT_SID = A.RS_CONTRACT_SID"
                + " WHERE I.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID";
        
        if (projSelDTO.getDiscountNoList() != null && !projSelDTO.getDiscountNoList().isEmpty()) {
            customSql += " and B.RS_CONTRACT_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ")  \n";
        }
        customSql += whereClause + "   group by " + "RS_NAME,"+groupBy+",J.RS_CONTRACT_SID  \n";

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as ACTUAL_SALES, \n"
                + " 0 as PROJECTION_SALES,J.RS_CONTRACT_SID \n"
                +" FROM   #SELECTED_HIERARCHY_NO CCP \n"
                + " INNER JOIN ST_NM_ACTUAL_DISCOUNT A ON"
                +" A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID"
                +" INNER JOIN "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES , \n"
                + " Sum(A.PROJECTION_SALES) as PROJECTION_SALES ,J.RS_CONTRACT_SID \n"
                 +" FROM   #SELECTED_HIERARCHY_NO CCP \n"
                + " INNER JOIN ST_NM_DISCOUNT_PROJECTION A ON \n "
                 +" A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID"
                +" INNER JOIN "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.HISTORY1, Constant.FUTURE, "on");
        String finalWhereCond = list.get(1) + " and HISTORY.DISCOUNTS=FUTURE.DISCOUNTS AND HISTORY.RS_CONTRACT_SID=FUTURE.RS_CONTRACT_SID";
        String finalSelectClause = " select " + list.get(0) + " Isnull(HISTORY.DISCOUNTS, FUTURE.DISCOUNTS) as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES,ISNULL(FUTURE.RS_CONTRACT_SID,HISTORY.RS_CONTRACT_SID) AS RS_CONTRACT_SID ";
        customQuery = finalSelectClause + Constant.FROM_SLASH_N + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n)  FUTURE \n" + finalWhereCond + orderBy;

        return customQuery;
    }

    public String getProjectionResultsDiscountsRPUQuery(ProjectionSelectionDTO projSelDTO, Boolean isVariable) {
        
         String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
        
        
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String customQuery;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", "on");
        selectClause += list.get(0);
        selectClause += "TODIS.DISCOUNTS, TODIS.ACTUAL_SALES   AS DISC_ACTUAL_DOLLAR,TODIS.PROJECTION_SALES   AS DISC_PROJECTION_DOLLAR,\n";

        selectClause += " DISC_ACTUAL_RATE = Isnull(TODIS.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0)*100 ,\n"
                + " DISC_PROJECTION_RATE = Isnull(TODIS.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0)*100, \n"
                +"DISC_ACTUAL_RPU = (ISNULL(TODIS.ACTUAL_SALES / NULLIF(SALE.actual_units, 0), 0)), \n"
                +"DISC_PROJECTED_RPU = (ISNULL(TODIS.PROJECTION_SALES / NULLIF(SALE.projection_units, 0), 0))"
                + ",TODIS.RS_CONTRACT_SID";
        String totalDiscountQuery = getProjectionResultsDiscountsRPUQuery(projSelDTO, StringUtils.EMPTY);
        String ppaQuery = getProjectionResultsPPARPUQuery(projSelDTO);
        String salesQuery = getProjectionResultsTotalDiscountSalesQuery(projSelDTO, isVariable);
        String ppaSalesQuery = getProjectionResultsPPASalesQuery(projSelDTO, isVariable);
        String unionSales = " SELECT Isnull(ppa.YEARS, SALE.YEARS) AS YEARS\n"
                + "	, Isnull(ppa.PERIODS, SALE.PERIODS) AS PERIODS\n"
                + "	, ppa.DISCOUNTS\n"
                + "	, ppa.ACTUAL_SALES AS DISC_ACTUAL_DOLLAR\n"
                + "	, ppa.PROJECTION_SALES AS DISC_PROJECTION_DOLLAR\n"

                + "	, DISC_ACTUAL_RATE = Isnull(PPA.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 \n"
                + "	, DISC_PROJECTION_RATE = Isnull(PPA.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100 \n"
                + "	, DISC_ACTUAL_RPU = (ISNULL(PPA.ACTUAL_SALES / NULLIF(SALE.ACTUAL_UNITS, 0), 0)) \n"
                + "	, DISC_PROJECTED_RPU = (ISNULL(PPA.PROJECTION_SALES / NULLIF(SALE.PROJECTION_UNITS, 0), 0))"
                + "     , PPA.RS_CONTRACT_SID FROM (";
        customQuery = selectClause + Constant.FROM_SLASH_N + totalDiscountQuery + "\n) TODIS INNER JOIN (\n" + salesQuery + "\n )SALE ON TODIS.YEARS = SALE.YEARS\n"
                + "		AND TODIS.PERIODS = SALE.PERIODS "
                + " AND TODIS.RS_CONTRACT_SID=SALE.RS_CONTRACT_SID\n"
                + "       AND TODIS.DISCOUNTS=SALE.DISCOUNTS "
                + " UNION ALL " + unionSales + " \n" + ppaQuery + " INNER JOIN (\n" + ppaSalesQuery + "\n) SALE ON PPA.YEARS = SALE.YEARS\n"
                + "		AND PPA.PERIODS = SALE.PERIODS "
                + " AND PPA.RS_CONTRACT_SID=SALE.RS_CONTRACT_SID\n"
                + " AND PPA.DISCOUNTS=SALE.DISCOUNTS";

        customQuery += "\n SELECT dis.* ,DISCOUNT_OF_EXFACTORY_ACTUALS=Isnull(ACTUAL_DOLLAR / NULLIF(file_data.EXFACTORY_ACTUAL, 0), 0)*100 \n"
                + "	,DISCOUNT_OF_EXFACTORY_PROJECTED = Isnull(PROJECTION_DOLLAR / NULLIF(COALESCE(file_data.EXFACTORY_FORECAST,file_data.EXFACTORY_ACTUAL), 0), 0) * 100 \n"
                + "	FROM @DISCOUNT_PPA  dis \n"
                + "	INNER JOIN \n"
                + "		(select i.YEAR YEARS" + getselectedFrequencyPeriod(projSelDTO) + ""
                + ",sum(fd.EXFACTORY_ACTUAL_SALES) EXFACTORY_ACTUAL,sum(fd.EXFACTORY_FORECAST_SALES) EXFACTORY_FORECAST"
                + ",SHN.RS_CONTRACT_SID from \n"
                + projSelDTO.getSessionDTO().getCurrentTableNames().get(Constant.ST_PRODUCT_FILE) + "  fd "
                + " JOIN period I on I.PERIOD_SID=fd.period_sid \n"
                + "	and  PERIOD_DATE BETWEEN ('" + startDate + "') and ('" + endDate + "') "
                + " JOIN ( select distinct  RS_CONTRACT_SID,SHN.ITEM_MASTER_SID\n"
                + " from #SELECTED_HIERARCHY_NO R\n"
                + "		JOIN #SELECTED_REBATE SHN ON SHN.ccp_details_sid=r.ccp_details_sid) SHN ON SHN.ITEM_MASTER_SID=fd.ITEM_MASTER_SID "
                + " group by  I.\"YEAR\"\n"
                + getselectedFrequency(projSelDTO) + " ,RS_CONTRACT_SID ) file_data     on    file_data.YEARS = dis.YEARS \n"
                + "	AND file_data.PERIODS = dis.PERIODS AND file_data.RS_CONTRACT_SID=DIS.RS_CONTRACT_SID \n"
                + "	order by DISCOUNT,RS_CONTRACT_SID, dis.YEARS,dis.PERIODS \n"; 
        
        
        return customQuery;
    }

    public String getFormatTwoDecimalValue(DecimalFormat decFormat, String value, String appendChar) {
        if (value.contains(Constant.NULL)) {
            value = "...";
        } else if (CURRENCY.equals(appendChar)) {
            value = appendChar.concat(decFormat.format(Double.valueOf(value)));
        } else {
            value = decFormat.format(Double.valueOf(value)).concat(appendChar);
        }
        return value;
    }

    private String getProjectionResultsTotalDiscount(ProjectionSelectionDTO projSelDTO) {

        String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                + String.format("%02d", projSelDTO.getEndDay());

        String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                + String.format("%02d", projSelDTO.getStartDay());
        
        String selectClause = "IF Object_id('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCP;\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCP  (\n"
                + "        COMPANY_MASTER_SID INT\n"
                + "       , CONTRACT_MASTER_SID INT \n"
                + "       , ITEM_MASTER_SID INT \n"
                + "      , CCP_DETAILS_SID INT \n"
                + SLASH_SPACE_N
                + "\n"
                + "INSERT  INTO #TEMP_CCP (\n"
                + "       COMPANY_MASTER_SID \n"
                + "       , CONTRACT_MASTER_SID \n"
                + "       , ITEM_MASTER_SID \n"
                + "       , CCP_DETAILS_SID\n"
                + SLASH_SPACE_N
                + " SELECT C.COMPANY_MASTER_SID \n "
                + "       , C.CONTRACT_MASTER_SID\n"
                + "       , C.ITEM_MASTER_SID\n"
                + "       , C.CCP_DETAILS_SID\n"
                + "FROM #SELECTED_HIERARCHY_NO CCP\n"
                + "       , CCP_DETAILS C\n"
                + "WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "DECLARE @ITEMID  [DBO].[UDT_ITEM]\n"
                + "\n"
                + "INSERT  INTO @ITEMID\n"
                + "SELECT distinct IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM \n "
                + "\n";
               
                
        
        
        
        selectClause += " SELECT ";
        String customQuery;

        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", Constant.PPA_SMALL, "on",true);
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        
         selectClause += "'Total Discount %'                  AS DISCOUNTS \n"
                      + ",ACTUAL_RATE = ISNULL((ISNULL(TODIS.ACTUAL_SALES, 0)+Isnull(PPA.ACTUAL_SALES, 0)) / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 "
                      + ",PROJECTION_RATE = ISNULL((ISNULL(TODIS.PROJECTION_SALES, 0)+ISNULL(PPA.PROJECTION_SALES, 0)) / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100, ";
        
        
        selectClause += " 'Total Discount $'                  AS DISCOUNTS,\n"
                + "          ACTUAL_AMOUNT = ( ISNULL(TODIS.ACTUAL_SALES, 0)\n";
       
            selectClause += "+Isnull(PPA.ACTUAL_SALES, 0)";
        
        selectClause += "                            ),\n"
                + "          PROJECTION_AMOUNT =( ISNULL(TODIS.PROJECTION_SALES, 0)\n";
        
            selectClause += "+Isnull(PPA.PROJECTION_SALES, 0)";
        
        selectClause += "                               ),\n"
                + "          'RPU'                               AS DISCOUNTS,\n"
                + " ACTUAL_RPU = ((ISNULL(TODIS.ACTUAL_SALES, 0)+Isnull(PPA.ACTUAL_SALES, 0)) / NULLIF(SALE.ACTUAL_UNITS, 0))" 
                + " ,PROJECTION_RPU = ((ISNULL(TODIS.PROJECTION_SALES, 0)+Isnull(PPA.PROJECTION_SALES, 0)) / NULLIF(SALE.PROJECTION_UNITS, 0)) "   ;     
        
        selectClause += "\n ,'Discount % of Ex factory' as DISCOUNT_OF_EX_FACTORY \n"
                + ",COALESCE((ISNULL(TODIS.ACTUAL_SALES, 0)+ISNULL(PPA.ACTUAL_SALES, 0)) / NULLIF(FILE_DATA.EXFACTORY_ACTUAL, 0),0)*100 AS DISCOUNT_OF_EX_FACTORY_ACTUALS \n"
                + ",COALESCE((ISNULL(TODIS.PROJECTION_SALES, 0)+Isnull(PPA.PROJECTION_SALES, 0)) / NULLIF(file_data.EXFACTORY_FORECAST,  0),0)*100 AS DISCOUNT_OF_EX_FACTORY_PROJECTED \n "; 
        
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String cogsQuery = getProjectionResultsTotalDiscountsQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + Constant.FROM_SLASH_N + totalDiscountQuery + "\n ) TODIS FULL OUTER JOIN (\n" + cogsQuery + Constant.CLOSE_SALE_SLASH_N + finalWhereCond + "\n";
            customQuery += "  FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA ON SALE.YEARS=PPA.YEARS AND SALE.PERIODS=PPA.PERIODS \n" ;
            

            customQuery += "\n FULL OUTER JOIN  \n" 
                    + " (select i.YEAR YEARS"+ getselectedFrequencyPeriod(projSelDTO) +",sum(fd.EXFACTORY_ACTUAL_SALES) EXFACTORY_ACTUAL,sum(COALESCE(fd.EXFACTORY_FORECAST_SALES,fd.EXFACTORY_ACTUAL_SALES)) EXFACTORY_FORECAST from "
                    + projSelDTO.getSessionDTO().getCurrentTableNames().get(Constant.ST_PRODUCT_FILE) + "   fd join period I on I.PERIOD_SID=fd.period_sid  \n"
                    + "where exists (select 1 from #SELECTED_HIERARCHY_NO s join ccp_details c on s.ccp_details_Sid=c.ccp_details_sid  \n" 
                    + "where c.item_master_sid=fd.item_master_sid) \n" 
                   + "	and  PERIOD_DATE BETWEEN ('"+startDate+"') and ('"+endDate+"') group by  I.\"YEAR\"\n"
                    + getselectedFrequency(projSelDTO) + " ) file_data     on    file_data.YEARS = SALE.YEARS  \n" 
                    + "AND file_data.PERIODS = SALE.PERIODS order by YEARS , PERIODS \n"; 
        return customQuery;
    }
    public static final String SLASH_SPACE_N = "       )\n";

    public List<ProjectionResultsDTO> getReturns(ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("= = = Inside getReturns = = =");

        List<ProjectionResultsDTO> projDTOList;
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
        list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
        projDTOList = getCustomizedProjectionResultsReturns(list, projSelDTO);
        LOGGER.debug("= = = Ending getReturns = = =");
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionResultsReturns(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        List<ProjectionResultsDTO> projDTO = new ArrayList<>();
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
                String column;
                int year = (obj[0] == null) ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = (obj[1] == null) ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                String commonColumn = common.get(0);
                int col = NumericConstants.THREE;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value;
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FOUR];
                    value = getFormattedValue(PER_TWO, value);
                    returnPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    returnDolDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + NumericConstants.FIVE];
                    value = getFormattedValue(PER_TWO, value);
                    returnPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
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
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectPeriod = Constant.IQUARTER_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectPeriod = Constant.ISEMI_ANNUAL_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectPeriod = Constant.ZERO_AS_PERIODS;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectPeriod = Constant.IMONTH_AS_PERIODS;
        }

        String query = "IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCP;\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCP ( \n"
                + " COMPANY_MASTER_SID INT\n"
                + "       , CONTRACT_MASTER_SID INT\n"
                + "       , ITEM_MASTER_SID INT \n"
                + "       , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "       , PROJECTION_MASTER_SID INT\n"
                + SLASH_SPACE_N
                + "\n"
                + "INSERT INTO  #TEMP_CCP (\n"
                + "    COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID \n"
                + "       , ITEM_MASTER_SID \n"
                + "       , PROJECTION_DETAILS_SID \n"
                + "       , PROJECTION_MASTER_SID \n"
                + SLASH_SPACE_N
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
                + "          )\n"
                + "\n"
                + "DECLARE  @ITEMID [DBO].[UDT_ITEM]\n"
                + "\n"
                + " INSERT INTO @ITEMID\n"
                + "SELECT DISTINCT IM.ITEM_MASTER_SID \n"
                + "FROM ITEM_MASTER IM \n"
                + "\n"
                + "IF Object_id('TEMPDB..#TEMP_CCPD') IS NOT NULL\n"
                + "       DROP TABLE #TEMP_CCPD\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID INT \n "
                + "       , CONTRACT_MASTER_SID INT\n"
                + "       , ITEM_MASTER_SID INT\n"
                + "       , PROJECTION_DETAILS_SID INT\n"
                + "       , PROJECTION_MASTER_SID INT\n"
                + SLASH_SPACE_N
                + "\n"
                + "INSERT INTO #TEMP_CCPD (\n"
                + "       COMPANY_MASTER_SID\n"
                + "       , CONTRACT_MASTER_SID\n"
                + "       , ITEM_MASTER_SID\n"
                + "       , PROJECTION_DETAILS_SID\n"
                + "       , PROJECTION_MASTER_SID\n"
                + SLASH_SPACE_N
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
                + SLASH_SPACE_N
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
                + SLASH_SPACE_N
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
                + "      FROM (\n"
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
                + "       FULL JOIN  (\n "
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
                + "       FROM   (\n"
                + "              SELECT YEARS\n"
                + Constant.PERIODS_SPACE_QUERY
                + "                     , sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                     , sum(A.HISTORY_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                    , sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                     , sum(A.HISTORY_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                     , ACTUAL_RATE = Avg(A.ACTUAL_RATE)\n"
                + "                     , RETURNS_ACTUAL = Sum(A.ACTUAL_SALES) * Avg(A.ACTUAL_RATE)\n"
                + "   FROM (\n"
                + "                   SELECT I.\"YEAR\" AS YEARS\n"
                + "                           " + selectPeriod + "\n"
                + "                           , A.ACTUAL_SALES\n"
                + "                           , A.HISTORY_PROJECTION_SALES\n"
                + "                           , A.ACTUAL_UNITS\n"
                + "                           , A.HISTORY_PROJECTION_UNITS\n"
                + "                           , TR.ACTUAL_RATE\n"
                + "                     FROM ST_NM_ACTUAL_SALES A\n"
                + "                     INNER JOIN PROJECTION_DETAILS E\n"
                + "                           ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "                    INNER JOIN PERIOD I\n"
                + "                           ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                     INNER JOIN @CCP CCP\n"
                + "                           ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "    INNER JOIN CCP_DETAILS CC\n"
                + "                           ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                                 AND A.PERIOD_SID = I.PERIOD_SID\n"
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
                + "                     WHERE E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                           AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID AND \n" + CommonLogic.getPeriodRestrictionQuery(projSelDTO) + "\n"
                + "                     ) A\n"
                + "              GROUP BY YEARS \n "
                + Constant.PERIODS_SPACE_QUERY
                + "              ) HISTORY \n "
                + "       FULL JOIN (\n"
                + "              SELECT YEARS\n"
                + Constant.PERIODS_SPACE_QUERY
                + "                     , 0 AS SALES_ACTUAL_SALES\n"
                + "                     , sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                     , 0 AS ACTUAL_UNITS\n"
                + "                     , sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS \n"
                + "                     , PROJECTED_RATE = Avg(P.PROJECTED_RATE)\n"
                + "                     , RETURNS_PROJECTED = Sum(P.PROJECTION_SALES) * Avg(PROJECTED_RATE)\n"
                + "              FROM (\n"
                + "                 SELECT I.\"YEAR\" AS YEARS\n"
                + "                           " + selectPeriod + "\n"
                + "                           , A.PROJECTION_SALES\n"
                + "                           , A.PROJECTION_UNITS\n"
                + "                           , TR.PROJECTED_RATE\n"
                + "                     FROM ST_NM_SALES_PROJECTION A\n"
                + "                     INNER JOIN PROJECTION_DETAILS E\n"
                + "                           ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                + "           INNER JOIN PERIOD I\n"
                + "                           ON A.PERIOD_SID = I.PERIOD_SID \n"
                + "                     INNER JOIN @CCP CCP\n"
                + "                           ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                 INNER JOIN CCP_DETAILS CC\n"
                + "                           ON CC.CCP_DETAILS_SID = E.CCP_DETAILS_SID\n"
                + "                                   AND A.PERIOD_SID = I.PERIOD_SID\n"
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
                + "                     WHERE E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "                           AND CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID AND \n" + CommonLogic.getPeriodRestrictionQuery(projSelDTO) + "\n"
                + "                     ) P\n"
                + "              GROUP BY YEARS\n"
                + Constant.PERIODS_SPACE_QUERY
                + "              ) FUTURE\n"
                + "          ON HISTORY.YEARS = FUTURE.YEARS\n"
                + "                     AND HISTORY.PERIODS = FUTURE.PERIODS\n"
                + "       ) SALE\n"
                + "       ON TODIS.YEARS = SALE.YEARS\n"
                + "              AND TODIS.PERIODS = SALE.PERIODS";

        return query;
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
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        List<ProjectionResultsDTO> projDtoList = new ArrayList<>();
        ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
        projDTO.setLevelValue(projSelDTO.getLevelValue());
        projDTO.setLevelNo(projSelDTO.getLevelNo());
        projDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());

        if (list != null && !list.isEmpty()) {

            for (int i = 0; i < list.size(); i++) {
                projSelDTO.setDiscountIndex(i);
                Object[] discountRow = (Object[]) list.get(i);

                if (StringUtils.isBlank(discountName) || !discountName.equals(String.valueOf(discountRow[NumericConstants.NINE]))) {
                    projDTO = new ProjectionResultsDTO();
                    projDtoList.add(projDTO);
                    discountName = String.valueOf(discountRow[NumericConstants.NINE]);
                    projDTO.setGroup(String.valueOf(discountRow[NumericConstants.TWO]));
                    projDTO.setRelationshipLevelName(discountName);
                }

                String column;
                int year = Integer.valueOf(String.valueOf(discountRow[0]));
                int period = Integer.valueOf(String.valueOf(discountRow[1]));
                List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                String commonColumn = common.get(0);
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    int v =pos == 1 ? NumericConstants.THREE : pos == NumericConstants.TWO ? NumericConstants.FIVE  : pos == NumericConstants.FOUR ? NumericConstants.TEN : NumericConstants.SEVEN; 
                    String value = StringUtils.EMPTY + discountRow[pos == 1 ? NumericConstants.THREE : pos == NumericConstants.TWO ? NumericConstants.FIVE  : pos == NumericConstants.FOUR ? NumericConstants.TEN : NumericConstants.SEVEN]; 
                    String value1 = StringUtils.EMPTY;
                    if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        
                        value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                    } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                        value = getFormattedValue(PER_TWO, value);
                        value1 = getFormattedValue(PER_TWO, "0.00");
                    } else if (projSelDTO.getSales().contains("TOT")) {
                        value = getFormatTwoDecimalValue(CUR_TWO, value, pos == NumericConstants.TWO ?PERCENTAGE:CURRENCY);
                        value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", pos == NumericConstants.TWO ?PERCENTAGE:CURRENCY);
                    } else if (projSelDTO.getSales().contains(Constant.DISCOUNT_EXFAC_SALES)) {
                        value = getFormattedValue(PER_TWO, value);
                        value1 = getFormattedValue(PER_TWO, "0.00");
                    }
                    projDTO.addStringProperties(column, discountRow[v]!=null && !"null".equals(discountRow[v]) ? value:value1);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    int u =pos == 1 ? NumericConstants.FOUR : pos == NumericConstants.TWO ? NumericConstants.SIX :pos == NumericConstants.FOUR ? NumericConstants.ELEVEN : NumericConstants.EIGHT; 
                    String value = StringUtils.EMPTY + discountRow[pos == 1 ? NumericConstants.FOUR : pos == NumericConstants.TWO ? NumericConstants.SIX : pos == NumericConstants.FOUR ? NumericConstants.ELEVEN : NumericConstants.EIGHT]; 
                    String value1 = StringUtils.EMPTY;
                    if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                        value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                        value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                        value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", CURRENCY);
                    } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                        value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                        value = getFormattedValue(PER_TWO, value);
                        value1 = getFormattedValue(PER_TWO, "0.00");
                    } else if (projSelDTO.getSales().contains("TOT")) {
                        value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                        value = getFormatTwoDecimalValue(CUR_TWO, value, pos == NumericConstants.TWO ?PERCENTAGE:CURRENCY);
                        value1 = getFormatTwoDecimalValue(CUR_TWO, "0.00", pos == NumericConstants.TWO ?PERCENTAGE:CURRENCY);
                    } else if (projSelDTO.getSales().contains(Constant.DISCOUNT_EXFAC_SALES)) {
                        value = getFormattedValue(PER_TWO, value);
                        value1 = getFormattedValue(PER_TWO, "0.00");
                    }
                    projDTO.addStringProperties(column, discountRow[u]!=null && !"null".equals(discountRow[u])? value : value1);
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
                " B.RS_CONTRACT_SID IN ( " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " )" : StringUtils.EMPTY;
        
        return "SELECT Count(DISTINCT RS_CONTRACT_SID)\n"
                + "FROM  ST_NM_DISCOUNT_PROJ_MASTER B\n"
                + "       JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "         ON B.CCP_DETAILS_SID = ccp.CCP_DETAILS_SID\n"
                + "WHERE  "
                +  condition;

    }
    
    private String getPPACount(Boolean isTotal) {
        String query = "SELECT COUNT(DISTINCT RS.RS_NAME) "
                + "FROM ST_NM_PPA_PROJECTION_MASTER TEMP \n";
        if (!isTotal) {
            query += " JOIN #SELECTED_HIERARCHY_NO CCP ON TEMP.ccp_details_sid = ccp.CCP_DETAILS_SID \n";
        }
        query += "JOIN RS_CONTRACT RS ON RS.RS_CONTRACT_SID = TEMP.RS_CONTRACT_SID \n";
        return query;
    }
    
    private int getPPACountValue(ProjectionSelectionDTO projSelDTO) {
        if (projSelDTO.getSessionDTO().isIsPPAUpdated() || isFirst) {
            int count = 0;
            String query = "SELECT COUNT(DISTINCT RS.RS_NAME) "
                + "FROM ST_NM_PPA_PROJECTION_MASTER TEMP \n"
                    + "JOIN RS_CONTRACT RS ON RS.RS_CONTRACT_SID = TEMP.RS_CONTRACT_SID \n";
            List list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = count + Integer.valueOf(String.valueOf(ob));
            }
            pPACount = count;
            projSelDTO.getSessionDTO().setIsPPAUpdated(false);
            isFirst=false;
            return count;
        } else {
            return pPACount;
        }
    }

    public void clearProjectionTotalList() {
        projectionTotalList.clear();
    }

    private List<ProjectionResultsDTO> getLevelListforNonmandated(int start, int offset, int started, ProjectionSelectionDTO projSelDTO,int neededRecord) {
         CommonLogic commonLogic = new CommonLogic();        
        List<ProjectionResultsDTO> resultList = new ArrayList<>();

            if (projSelDTO.isIsCustomHierarchy()) {

                String hierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
                Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
                List<String> hierarchyNoList = commonLogic.getHiearchyNoForCustomView(projSelDTO, start, offset);                
               for (int i = 0; i < hierarchyNoList.size() && neededRecord > 0; neededRecord--, i++) {
                   String hierarchyNo=hierarchyNoList.get(i);
                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + (started + i))) {
                    resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, hierarchyIndicator, projSelDTO.getTreeLevelNo(), relationshipLevelDetailsMap.get(hierarchyNo), hierarchyNoList.size(),i));
                     
                    }
                started++;
                   }
                
            } else {
                Map<String, List> relationshipLevelDetailsMap =  projSelDTO.getSessionDTO().getHierarchyLevelDetails();
               
                List<String> hierarchyNoList = commonLogic.getHiearchyNoAsList(projSelDTO, start, offset);
            for (int i = 0; i < hierarchyNoList.size() && neededRecord > 0; neededRecord--, i++) {
                   String hierarchyNo=hierarchyNoList.get(i);
                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + (started + i))) {
                    resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, projSelDTO.getHierarchyIndicator(), Integer.valueOf(relationshipLevelDetailsMap.get(hierarchyNo).get(NumericConstants.TWO).toString()), relationshipLevelDetailsMap.get(hierarchyNo),hierarchyNoList.size(),i));
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
    public ProjectionResultsDTO configureDetailsInDTO(ProjectionSelectionDTO projSelDTO, String hierarchyNo, String hierarchyIndicator,int levelNo, List detailsList,int listSize,int i) {
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
            
        return dto;
    }
    
    public String getselectedFrequencyPeriod(ProjectionSelectionDTO projSelDTO) {
        String selectedFrequency = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectedFrequency = ", I.QUARTER  PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectedFrequency = ", I.SEMI_ANNUAL  PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectedFrequency = ",'0' PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectedFrequency = ", I.MONTH PERIODS";
}
        return selectedFrequency;
    }

    public String getselectedFrequency(ProjectionSelectionDTO projSelDTO) {
        String selectedGroup = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectedGroup = Constant.IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectedGroup = Constant.ISEMI_ANNUAL;
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectedGroup = StringUtils.EMPTY;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectedGroup = ", I.MONTH";
        }
        return selectedGroup;
    }
    
    }