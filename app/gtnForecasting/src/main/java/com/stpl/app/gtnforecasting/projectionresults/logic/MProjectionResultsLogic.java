/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionresults.logic;

import com.liferay.portal.kernel.exception.PortalException;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.*;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getCommonColumnHeader;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.SALES;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sibi
 */
public class MProjectionResultsLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(MProjectionResultsLogic.class);

    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PER_TWO = new DecimalFormat("#,##0.00%");
    /**
     * The Numeric Zero Decimal Places Format.
     */
    private static final DecimalFormat NUM_ZERO = new DecimalFormat("#,##0");

    /**
     * The Currency Two Decimal Places Format.
     */
    private static final DecimalFormat CUR_TWO = new DecimalFormat("$#,##0.00");

    private ProjectionResultsDTO supDisPerDto = null;
    private ProjectionResultsDTO supDisDolDto = null;
    private ProjectionResultsDTO manDisPerDto = null;
    private ProjectionResultsDTO manDisDolDto = null;
    private ProjectionResultsDTO manRPUDto = null;
    private ProjectionResultsDTO supRPUDto = null;
    private List<ProjectionResultsDTO> prjTotalDisPerDtoList = new ArrayList<>();
    private List<ProjectionResultsDTO> prjTotalDisDolDtoList = new ArrayList<>();

    private final List<ProjectionResultsDTO> prjTotalDisDtoList = new ArrayList<>();
    private final List<ProjectionResultsDTO> prjTotalDisChildDtoList = new ArrayList<>();
    private final List<List<ProjectionResultsDTO>> programCodeDtoList = new ArrayList<>();

    public static final String TOTAL_DISCOUNT_DOLLAR = "Total Discount $";
    private final List<ProjectionResultsDTO> netSalesDtoList = new ArrayList<>();
    private List<Object[]> prcMProjResultsContract = new ArrayList<>();
     //To Persist the Total Procedure Result List 
    private List<Object[]> prcMProcedureResults = new ArrayList<>();
    private List<Object> childDiscountList = new ArrayList<>();
    private List<Object[]> programCodeList = new ArrayList<>();
    private List<Object> prMainQuery = new ArrayList<>();
    private List<ProjectionResultsDTO> projectionTotalList = new ArrayList<>();
    // To Persist the Total Procedure Input
    private Object[] totalPRCInput;

    public MProjectionResultsLogic() {
        super();
    }
    
    public List<ProjectionResultsDTO> getProjectionTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        if ( projSelDTO.getSessionDTO().isPrRefreshReqd() || !CommonLogic.checkProcedureInputIsSame(orderedArgs, totalPRCInput)) {
            prcMProcedureResults = CommonLogic.callProcedure("PRC_M_PROJECTION_RESULTS", orderedArgs);
            projSelDTO.getSessionDTO().setPrRefreshReqd(false);
        }
        List<Object[]> gtsList = prcMProcedureResults;
        if (gtsList != null) {
            projDTOList = getCustomizedProjectionTotal(gtsList, projSelDTO);
        }

        return projDTOList;
    }

    public List<ProjectionResultsDTO> getContractSalesAndUnits(ProjectionSelectionDTO projSelDTO) {
             CommonLogic commonLogic = new CommonLogic();
        String sql = commonLogic.insertAvailableHierarchyNo(projSelDTO);
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);

        String query = "DECLARE @FROM_DATE DATE \n"
                + "     , @STARTFROM DATE\n"
                + "     , @PROJECTION_DATE DATE\n"
                + "     , @START_PERIOD_SID INT\n"
                + "     , @END_PERIOD_SID INT\n"
                + "\n"
                + "SELECT TOP 1 @STARTFROM = DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, - 3, GETDATE())), 0)\n"
                + "     , @PROJECTION_DATE = DATEADD(M, DATEDIFF(M, 0, DATEADD(DAY, - 1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))), 0)\n"
                + "FROM PROJECTION_MASTER\n"
                + "WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "\n"
                + "SELECT @START_PERIOD_SID = PERIOD_SID\n"
                + "FROM PERIOD \n"
                + "WHERE PERIOD_DATE = @STARTFROM\n"
                + "\n"
                + "SELECT @END_PERIOD_SID = PERIOD_SID\n"
                + "FROM PERIOD \n"
                + "WHERE PERIOD_DATE = @PROJECTION_DATE \n";
        query += sql + " \n" + getProjectionResultsNetSalesQueryForPR(projSelDTO);
        prMainQuery = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        List<ProjectionResultsDTO> projDTOList = getCustomizedProjectionResultsSales(prMainQuery, projSelDTO);
        return projDTOList;
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
        projSalesDTO.setRelationshipLevelName(CONTRACT_SALES_AT_WAC.getConstant());
        projUnitDTO.setLevelNo(projSelDTO.getLevelNo());
        projUnitDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projUnitDTO.setLevelValue(projSelDTO.getLevelValue());
        projUnitDTO.setGroup(UNIT_VOL.getConstant());
        projUnitDTO.setRelationshipLevelName(UNIT_VOL.getConstant());
        projSalesDTO.setParent(NumericConstants.ZERO);
        projUnitDTO.setParent(NumericConstants.ZERO);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;
                int year = obj[NumericConstants.ZERO] == null ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(obj[NumericConstants.ZERO]));
                int period = obj[NumericConstants.ONE] == null ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(obj[NumericConstants.ONE]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(NumericConstants.ZERO);
                int col = NumericConstants.EIGHT;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    value = getFormattedValue(CUR_TWO, value);
                    projSalesDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FOUR];
                    value = getFormattedValue(NUM_ZERO, value);
                    projUnitDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(CUR_TWO, value);
                    projSalesDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FIVE];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(NUM_ZERO, value);
                    projUnitDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        
        for (String columns : columnList) {
            projSalesDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
            projUnitDTO.addStringProperties(columns, getFormattedValue(NUM_ZERO, Constant.ZERO_STRING));
        }
        projDtoList.add(projSalesDTO);
        projDtoList.add(projUnitDTO);
        return projDtoList;
    }

    public void getCustomizedTotalDiscount(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        ProjectionResultsDTO totalDisPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO totalRPUDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO totalDisDolDTO = new ProjectionResultsDTO();
        totalDisDolDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        totalDisPerDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        totalRPUDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        totalDisDolDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        totalDisPerDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        totalRPUDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        totalDisDolDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        totalDisPerDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        totalRPUDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        totalDisPerDTO.setLevelNo(projSelDTO.getLevelNo());
        totalDisPerDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalDisPerDTO.setLevelValue(projSelDTO.getLevelValue());
        totalDisPerDTO.setGroup(TOTAL_DISCOUNT_PERC.getConstant());
        totalDisPerDTO.setRelationshipLevelName(TOTAL_DISCOUNT_PERC.getConstant());
        totalDisPerDTO.setLevelIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        totalDisPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalRPUDTO.setLevelNo(projSelDTO.getLevelNo());
        totalRPUDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalRPUDTO.setLevelValue(projSelDTO.getLevelValue());
        totalRPUDTO.setGroup(TOTAL_RPU.getConstant());
        totalRPUDTO.setRelationshipLevelName(TOTAL_RPU.getConstant());
        totalRPUDTO.setLevelIndicator(Constant.R);
        totalRPUDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalDisDolDTO.setLevelNo(projSelDTO.getLevelNo());
        totalDisDolDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        totalDisDolDTO.setLevelValue(projSelDTO.getLevelValue());
        totalDisDolDTO.setGroup(TOTAL_DISCOUNT_AMOUNT.getConstant());
        totalDisDolDTO.setRelationshipLevelName(TOTAL_DISCOUNT_AMOUNT.getConstant());
        totalDisDolDTO.setLevelIndicator("D");
        totalDisDolDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        totalDisDolDTO.setParent(NumericConstants.ONE);
        totalDisPerDTO.setParent(NumericConstants.ONE);
        totalDisPerDTO.setChildTotal(NumericConstants.ONE);
        totalRPUDTO.setParent(NumericConstants.ONE);
        totalRPUDTO.setChildTotal(NumericConstants.ONE);
        totalDisDolDTO.setChildTotal(NumericConstants.ONE);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;
                int year = obj[NumericConstants.ZERO] == null ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(obj[NumericConstants.ZERO]));
                int period = obj[NumericConstants.ONE] == null ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(obj[NumericConstants.ONE]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(NumericConstants.ZERO);
                int col = NumericConstants.FOUR;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col];
                    value = getFormattedValue(CUR_TWO, value);
                    totalDisDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    value = getFormattedValue(PER_TWO, value);
                    totalDisPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FOUR];
                    value = getFormattedValue(CUR_TWO, value);
                    totalRPUDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(PER_TWO, value);
                    totalDisPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.ONE];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(CUR_TWO, value);
                    totalDisDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FIVE];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(CUR_TWO, value);
                    totalRPUDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
       
        for (String columns : columnList) {
            totalDisPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
            totalRPUDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            totalDisDolDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
        }
        prjTotalDisDtoList.clear();
        prjTotalDisDtoList.add(totalDisPerDTO);
        prjTotalDisDtoList.add(totalRPUDTO);
        prjTotalDisDtoList.add(totalDisDolDTO);
    }

    public void getCustomizedChildTotalDiscount(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        ProjectionResultsDTO manPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO manRPUDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO manDolDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO suppPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO suppRPUDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO suppDolDTO = new ProjectionResultsDTO();

        manPerDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        manRPUDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        manDolDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        suppPerDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        suppRPUDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        suppDolDTO.setHierarchyNo(projSelDTO.getHierarchyNo());
        manPerDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        manPerDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        manRPUDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        manRPUDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        manDolDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        manDolDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        suppPerDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        suppPerDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        suppRPUDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        suppRPUDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
        suppDolDTO.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
        suppDolDTO.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());

        manPerDTO.setLevelNo(projSelDTO.getLevelNo());
        manPerDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        manPerDTO.setLevelValue(projSelDTO.getLevelValue());
        manPerDTO.setGroup(MANDATED_DISCOUNT.getConstant());
        manPerDTO.setDiscountValue(Constant.TOTAL_DISCOUNT_PERCEN);
        manPerDTO.setRelationshipLevelName(MANDATED_DISCOUNT.getConstant());
        manPerDTO.setLevelIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        manPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        manRPUDTO.setLevelNo(projSelDTO.getLevelNo());
        manRPUDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        manRPUDTO.setLevelValue(projSelDTO.getLevelValue());
        manRPUDTO.setGroup(MANDATED_DISCOUNT.getConstant());
        manRPUDTO.setDiscountValue(Constant.TOTAL_RPU_CAPS);
        manRPUDTO.setRelationshipLevelName(MANDATED_DISCOUNT.getConstant());
        manRPUDTO.setLevelIndicator(Constant.R);
        manRPUDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        manDolDTO.setLevelNo(projSelDTO.getLevelNo());
        manDolDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        manDolDTO.setLevelValue(projSelDTO.getLevelValue());
        manDolDTO.setGroup(MANDATED_DISCOUNT.getConstant());
        manDolDTO.setDiscountValue(TOTAL_DISCOUNT_DOLLAR);
        manDolDTO.setRelationshipLevelName(MANDATED_DISCOUNT.getConstant());
        manDolDTO.setLevelIndicator("D");
        manDolDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        suppPerDTO.setLevelNo(projSelDTO.getLevelNo());
        suppPerDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        suppPerDTO.setLevelValue(projSelDTO.getLevelValue());
        suppPerDTO.setGroup(SUPPLEMENTAL_DISCOUNT.getConstant());
        suppPerDTO.setDiscountValue(Constant.TOTAL_DISCOUNT_PERCEN);
        suppPerDTO.setRelationshipLevelName(SUPPLEMENTAL_DISCOUNT.getConstant());
        suppPerDTO.setLevelIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        suppPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        suppRPUDTO.setLevelNo(projSelDTO.getLevelNo());
        suppRPUDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        suppRPUDTO.setLevelValue(projSelDTO.getLevelValue());
        suppRPUDTO.setGroup(SUPPLEMENTAL_DISCOUNT.getConstant());
        suppRPUDTO.setDiscountValue(Constant.TOTAL_RPU_CAPS);
        suppRPUDTO.setRelationshipLevelName(SUPPLEMENTAL_DISCOUNT.getConstant());
        suppRPUDTO.setLevelIndicator(Constant.R);
        suppRPUDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        suppDolDTO.setLevelNo(projSelDTO.getLevelNo());
        suppDolDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        suppDolDTO.setLevelValue(projSelDTO.getLevelValue());
        suppDolDTO.setGroup(SUPPLEMENTAL_DISCOUNT.getConstant());
        suppDolDTO.setDiscountValue(TOTAL_DISCOUNT_DOLLAR);
        suppDolDTO.setRelationshipLevelName(SUPPLEMENTAL_DISCOUNT.getConstant());
        suppDolDTO.setLevelIndicator("D");
        suppDolDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        manPerDTO.setParent(NumericConstants.ONE);
        manRPUDTO.setParent(NumericConstants.ONE);
        manDolDTO.setParent(NumericConstants.ONE);
        suppPerDTO.setParent(NumericConstants.ONE);
        suppRPUDTO.setParent(NumericConstants.ONE);
        suppDolDTO.setParent(NumericConstants.ONE);

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;
                int year = obj[NumericConstants.ZERO] == null ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(obj[NumericConstants.ZERO]));
                int period = obj[NumericConstants.ONE] == null ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(obj[NumericConstants.ONE]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(NumericConstants.ZERO);
                int col = NumericConstants.TWO;

                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col];
                    value = getFormattedValue(CUR_TWO, value);
                    manDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.ONE];
                    value = getFormattedValue(PER_TWO, value);
                    manPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    value = getFormattedValue(CUR_TWO, value);
                    manRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                    value = getFormattedValue(CUR_TWO, value);
                    suppDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FOUR];
                    value = getFormattedValue(PER_TWO, value);
                    suppPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FIVE];
                    value = getFormattedValue(CUR_TWO, value);
                    suppRPUDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + NumericConstants.SIX];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(CUR_TWO, value);
                    manDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 7];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(PER_TWO, value);
                    manPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.EIGHT];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(CUR_TWO, value);
                    manRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.NINE];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(CUR_TWO, value);
                    suppDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TEN];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(PER_TWO, value);
                    suppPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.ELEVEN];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(CUR_TWO, value);
                    suppRPUDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        
        for (String columns : columnList) {
            manPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
            manRPUDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            manDolDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));

            suppPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
            suppRPUDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            suppDolDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
        }
        prjTotalDisChildDtoList.clear();
        prjTotalDisChildDtoList.add(manPerDTO);
        prjTotalDisChildDtoList.add(suppPerDTO);
        prjTotalDisChildDtoList.add(manRPUDTO);
        prjTotalDisChildDtoList.add(suppRPUDTO);
        prjTotalDisChildDtoList.add(manDolDTO);
        prjTotalDisChildDtoList.add(suppDolDTO);
    }

    public List<ProjectionResultsDTO> getCustomizedNetSales(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        ProjectionResultsDTO netSalesDto = new ProjectionResultsDTO();
        ProjectionResultsDTO cogsDto = new ProjectionResultsDTO();
        ProjectionResultsDTO netprofitDto = new ProjectionResultsDTO();

        netSalesDto.setLevelNo(projSelDTO.getLevelNo());
        netSalesDto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        netSalesDto.setLevelValue(projSelDTO.getLevelValue());
        netSalesDto.setGroup(NET_SALES.getConstant());
        netSalesDto.setRelationshipLevelName(NET_SALES.getConstant());

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

        netSalesDto.setParent(NumericConstants.ZERO);
        cogsDto.setParent(NumericConstants.ZERO);
        netprofitDto.setParent(NumericConstants.ZERO);

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;
                int year = (obj[NumericConstants.ZERO] == null) ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(obj[NumericConstants.ZERO]));
                int period = projSelDTO.getFrequencyDivision() == NumericConstants.ONE ? NumericConstants.ZERO : (obj[NumericConstants.ONE] == null) ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(obj[NumericConstants.ONE]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(NumericConstants.ZERO);
                int col = projSelDTO.getFrequencyDivision() == NumericConstants.ONE ? NumericConstants.ONE : NumericConstants.TWO;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col];
                    value = getFormattedValue(CUR_TWO, value);
                    netSalesDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    value = getFormattedValue(CUR_TWO, value);
                    cogsDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FOUR];
                    value = getFormattedValue(CUR_TWO, value);
                    netprofitDto.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + NumericConstants.ONE];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(CUR_TWO, value);
                    netSalesDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(CUR_TWO, value);
                    cogsDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FIVE];
                    value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                    value = getFormattedValue(CUR_TWO, value);
                    netprofitDto.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        
        for (String columns : columnList) {
            netSalesDto.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            cogsDto.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            netprofitDto.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
        }
        netSalesDtoList.clear();
        netSalesDtoList.add(netSalesDto);
        netSalesDtoList.add(cogsDto);
        netSalesDtoList.add(netprofitDto);

        return Collections.unmodifiableList(netSalesDtoList);
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        ProjectionResultsDTO exFacSalesDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO demandSalesDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO invWithdrawDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO perExfacDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO perDemandDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO perinvWithdrawDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO conSaleDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO unitVolDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO discountPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO discountDolDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO discountRPUDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO netSaleDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO cogsDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO netProfitDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO supDisDolDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO manDisDolDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO supDisPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO manDisPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO supRPUDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO manRPUDTO = new ProjectionResultsDTO();
        exFacSalesDTO.setParent(NumericConstants.ZERO);
        exFacSalesDTO.setGroup(EX_FACTORY_SALES.getConstant());
        exFacSalesDTO.setRelationshipLevelName(EX_FACTORY_SALES.getConstant());

        demandSalesDTO.setParent(NumericConstants.ZERO);
        demandSalesDTO.setGroup(DEMAND_SALES.getConstant());
        demandSalesDTO.setRelationshipLevelName(DEMAND_SALES.getConstant());

        invWithdrawDTO.setParent(NumericConstants.ZERO);
        invWithdrawDTO.setGroup(INVENTORY_WITHDRAW.getConstant());
        invWithdrawDTO.setRelationshipLevelName(INVENTORY_WITHDRAW.getConstant());

        perExfacDTO.setParent(NumericConstants.ZERO);
        perExfacDTO.setGroup(PERC_OF_EX_FACTORY.getConstant());
        perExfacDTO.setRelationshipLevelName(PERC_OF_EX_FACTORY.getConstant());

        perDemandDTO.setParent(NumericConstants.ZERO);
        perDemandDTO.setGroup(PERC_OF_DEMAND.getConstant());
        perDemandDTO.setRelationshipLevelName(PERC_OF_DEMAND.getConstant());

        perinvWithdrawDTO.setParent(NumericConstants.ZERO);
        perinvWithdrawDTO.setGroup(PERC_OF_INVENTORY_WITHDRAW.getConstant());
        perinvWithdrawDTO.setRelationshipLevelName(PERC_OF_INVENTORY_WITHDRAW.getConstant());

        conSaleDTO.setParent(NumericConstants.ZERO);
        conSaleDTO.setGroup(CONTRACT_SALES_AT_WAC.getConstant());
        conSaleDTO.setRelationshipLevelName(CONTRACT_SALES_AT_WAC.getConstant());

        unitVolDTO.setParent(NumericConstants.ZERO);
        unitVolDTO.setGroup(UNIT_VOL.getConstant());
        unitVolDTO.setRelationshipLevelName(UNIT_VOL.getConstant());

        discountPerDTO.setParent(NumericConstants.ONE);
        discountPerDTO.setGroup(TOTAL_DISCOUNT_PERC.getConstant());
        discountPerDTO.setRelationshipLevelName(TOTAL_DISCOUNT_PERC.getConstant());
        discountPerDTO.setProjectionTotal(NumericConstants.ONE);
        discountPerDTO.setChildTotal(NumericConstants.ONE);
        discountPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        discountPerDTO.setLevelIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);

        discountDolDTO.setParent(NumericConstants.ONE);
        discountDolDTO.setGroup(TOTAL_DISCOUNT_AMOUNT.getConstant());
        discountDolDTO.setRelationshipLevelName(TOTAL_DISCOUNT_AMOUNT.getConstant());
        discountDolDTO.setProjectionTotal(NumericConstants.ONE);
        discountDolDTO.setChildTotal(NumericConstants.ONE);
        discountDolDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        discountDolDTO.setLevelIndicator("D");

        discountRPUDTO.setParent(NumericConstants.ONE);
        discountRPUDTO.setGroup(TOTAL_RPU.getConstant());
        discountRPUDTO.setRelationshipLevelName(TOTAL_RPU.getConstant());
        discountRPUDTO.setProjectionTotal(NumericConstants.ONE);
        discountRPUDTO.setChildTotal(NumericConstants.ONE);
        discountRPUDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        discountRPUDTO.setLevelIndicator(Constant.R);

        netSaleDTO.setParent(NumericConstants.ZERO);
        netSaleDTO.setGroup(NET_SALES.getConstant());
        netSaleDTO.setRelationshipLevelName(NET_SALES.getConstant());

        cogsDTO.setParent(NumericConstants.ZERO);
        cogsDTO.setGroup(COGS.getConstant());
        cogsDTO.setRelationshipLevelName(COGS.getConstant());

        netProfitDTO.setParent(NumericConstants.ZERO);
        netProfitDTO.setGroup(NET_PROFIT.getConstant());
        netProfitDTO.setRelationshipLevelName(NET_PROFIT.getConstant());

        supDisDolDTO.setParent(NumericConstants.ONE);
        supDisDolDTO.setGroup(Constant.SUPPLEMENTAL_DISCOUNT_LABEL);
        supDisDolDTO.setRelationshipLevelName(Constant.SUPPLEMENTAL_DISCOUNT_LABEL);
        supDisDolDTO.setProjectionTotal(NumericConstants.ONE);
        supDisDolDTO.setLevelIndicator("D");
        supDisDolDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());

        manDisDolDTO.setParent(NumericConstants.ONE);
        manDisDolDTO.setGroup(Constant.MANDATED_DISCOUNT);
        manDisDolDTO.setProjectionTotal(NumericConstants.ONE);
        manDisDolDTO.setRelationshipLevelName(Constant.MANDATED_DISCOUNT);
        manDisDolDTO.setLevelIndicator("D");
        manDisDolDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());

        supDisPerDTO.setParent(NumericConstants.ONE);
        supDisPerDTO.setGroup(Constant.SUPPLEMENTAL_DISCOUNT_LABEL);
        supDisPerDTO.setProjectionTotal(NumericConstants.ONE);
        supDisPerDTO.setRelationshipLevelName(Constant.SUPPLEMENTAL_DISCOUNT_LABEL);
        supDisPerDTO.setLevelIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        supDisPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());

        manDisPerDTO.setParent(NumericConstants.ONE);
        manDisPerDTO.setGroup(Constant.MANDATED_DISCOUNT);
        manDisPerDTO.setProjectionTotal(NumericConstants.ONE);
        manDisPerDTO.setRelationshipLevelName(Constant.MANDATED_DISCOUNT);
        manDisPerDTO.setLevelIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        manDisPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());

        supRPUDTO.setParent(NumericConstants.ONE);
        supRPUDTO.setGroup(Constant.SUPPLEMENTAL_DISCOUNT_LABEL);
        supRPUDTO.setProjectionTotal(NumericConstants.ONE);
        supRPUDTO.setRelationshipLevelName(Constant.SUPPLEMENTAL_DISCOUNT_LABEL);
        supRPUDTO.setLevelIndicator("R");
        supRPUDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        manRPUDTO.setParent(NumericConstants.ONE);
        manRPUDTO.setGroup(Constant.MANDATED_DISCOUNT);
        manRPUDTO.setProjectionTotal(NumericConstants.ONE);
        manRPUDTO.setRelationshipLevelName(Constant.MANDATED_DISCOUNT);
        manRPUDTO.setLevelIndicator("R");
        manRPUDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());

        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            int col = NumericConstants.THREE;
            if (frequencyDivision != NumericConstants.ONE) {
                col = col + NumericConstants.ONE;
            }
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;

                int year = Integer.parseInt(String.valueOf(obj[col]));
                int period = Integer.parseInt(String.valueOf(frequencyDivision != NumericConstants.ONE ? obj[col - NumericConstants.ONE] : NumericConstants.ZERO));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(NumericConstants.ZERO);

                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[NumericConstants.ONE];
                    value = getFormattedValue(CUR_TWO, value);
                    exFacSalesDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.ONE];
                    value = getFormattedValue(CUR_TWO, value);
                    conSaleDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                    value = getFormattedValue(NUM_ZERO, value);
                    unitVolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.FIVE];

                    value = getFormattedValue(CUR_TWO, value);
                    manDisDolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.SEVEN];
                    value = getFormattedValue(CUR_TWO, value);
                    supDisDolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.NINE];
                    value = getFormattedValue(PER_TWO, value);
                    manDisPerDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.ELEVEN];
                    value = getFormattedValue(PER_TWO, value);
                    supDisPerDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTEEN];
                    value = getFormattedValue(CUR_TWO, value);
                    discountDolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.FIFTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    discountPerDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.SEVENTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    perExfacDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.NINETEEN];
                    value = getFormattedValue(CUR_TWO, value);
                    netSaleDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY_ONE];
                    value = getFormattedValue(CUR_TWO, value);
                    demandSalesDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY_THREE];
                    value = getFormattedValue(CUR_TWO, value);
                    invWithdrawDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY_FIVE];
                    value = getFormattedValue(PER_TWO, value);
                    perDemandDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY_SEVEN];
                    value = getFormattedValue(PER_TWO, value);
                    perinvWithdrawDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_THREE];
                    value = getFormattedValue(CUR_TWO, value);
                    discountRPUDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_FIVE];
                    value = getFormattedValue(CUR_TWO, value);
                    cogsDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_SEVEN];
                    value = getFormattedValue(CUR_TWO, value);
                    netProfitDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWENTY_NINE];
                    value = getFormattedValue(CUR_TWO, value);
                    manRPUDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_ONE];
                    value = getFormattedValue(CUR_TWO, value);
                    supRPUDTO.addStringProperties(column, value);

                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[NumericConstants.TWO];
                    value = getFormattedValue(CUR_TWO, value);
                    exFacSalesDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    value = getFormattedValue(CUR_TWO, value);
                    conSaleDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.FOUR];
                    value = getFormattedValue(NUM_ZERO, value);
                    unitVolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.SIX];
                    value = getFormattedValue(CUR_TWO, value);
                    manDisDolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.EIGHT];
                    value = getFormattedValue(CUR_TWO, value);
                    supDisDolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.TEN];
                    value = getFormattedValue(PER_TWO, value);
                    manDisPerDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWELVE];
                    value = getFormattedValue(PER_TWO, value);
                    supDisPerDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.FOURTEEN];
                    value = getFormattedValue(CUR_TWO, value);
                    discountDolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.SIXTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    discountPerDTO.addStringProperties(column, value);

                    value = "" + obj[col + NumericConstants.EIGHTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    perExfacDTO.addStringProperties(column, value);

                    value = "" + obj[col + NumericConstants.TWENTY];
                    value = getFormattedValue(CUR_TWO, value);
                    netSaleDTO.addStringProperties(column, value);

                    value = "" + obj[col + NumericConstants.TWENTY_TWO];
                    value = getFormattedValue(CUR_TWO, value);
                    demandSalesDTO.addStringProperties(column, value);

                    value = "" + obj[col + NumericConstants.TWENTY_FOUR];
                    value = getFormattedValue(CUR_TWO, value);
                    invWithdrawDTO.addStringProperties(column, value);

                    value = "" + obj[col + NumericConstants.TWENTY_SIX];
                    value = getFormattedValue(PER_TWO, value);
                    perDemandDTO.addStringProperties(column, value);
                    value = "" + obj[col + NumericConstants.TWENTY_EIGHT];
                    value = getFormattedValue(PER_TWO, value);
                    perinvWithdrawDTO.addStringProperties(column, value);

                    value = "" + obj[col + NumericConstants.THIRTY_FOUR];
                    value = getFormattedValue(CUR_TWO, value);
                    discountRPUDTO.addStringProperties(column, value);

                    value = "" + obj[col + NumericConstants.THIRTY_SIX];
                    value = getFormattedValue(CUR_TWO, value);
                    cogsDTO.addStringProperties(column, value);
                    value = "" + obj[col + NumericConstants.THIRTY_EIGHT];
                    value = getFormattedValue(CUR_TWO, value);
                    netProfitDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY];
                    value = getFormattedValue(CUR_TWO, value);
                    manRPUDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTY_TWO];
                    value = getFormattedValue(CUR_TWO, value);
                    supRPUDTO.addStringProperties(column, value);

                    columnList.remove(column);
                }
            }

        }
        
        for (String columns : columnList) {
            exFacSalesDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            demandSalesDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            invWithdrawDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            perExfacDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
            perDemandDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
            perinvWithdrawDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
            conSaleDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            unitVolDTO.addStringProperties(columns, getFormattedValue(NUM_ZERO, Constant.ZERO_STRING));
            discountPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
            discountDolDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            discountRPUDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            netSaleDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            cogsDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            netProfitDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            manDisDolDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            supDisDolDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            supDisPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
            manDisPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
            manRPUDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            supRPUDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
        }
        projDTOList.add(exFacSalesDTO);
        projDTOList.add(demandSalesDTO);
        projDTOList.add(invWithdrawDTO);
        projDTOList.add(perExfacDTO);
        projDTOList.add(perDemandDTO);
        projDTOList.add(perinvWithdrawDTO);
        projDTOList.add(conSaleDTO);
        projDTOList.add(unitVolDTO);
        projDTOList.add(discountPerDTO);
        projDTOList.add(discountDolDTO);
        projDTOList.add(discountRPUDTO);
        projDTOList.add(netSaleDTO);
        projDTOList.add(cogsDTO);
        projDTOList.add(netProfitDTO);
        projDTOList.add(manDisDolDTO);
        projDTOList.add(supDisDolDTO);
        projDTOList.add(supDisPerDTO);
        projDTOList.add(manDisPerDTO);
        projDTOList.add(manRPUDTO);
        projDTOList.add(supRPUDTO);
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getNetSales1(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        String query = "";
        query = getNetSalesQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        List<ProjectionResultsDTO> projDTOList = getCustomizedNetSales(list, projSelDTO);

        return projDTOList;
    }

    public List<ProjectionResultsDTO> getNetSales(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        List<ProjectionResultsDTO> projDTOList = getCustomizedNetSales(prMainQuery, projSelDTO);

        return projDTOList;
    }

    public List<ProjectionResultsDTO> getDiscountPer(ProjectionSelectionDTO projSelDTO) throws PortalException  {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setSales(Constant.RATE);
        String query = "";
        if (projSelDTO.isIsTotal()) {

            getCustomizedTotalDiscount(prMainQuery, projSelDTO);
            projDTOList.add(prjTotalDisDtoList.get(NumericConstants.ZERO));
        } else if (projSelDTO.isIsChildTotal()) {

            query = getChildTotalDiscountMainQuery(projSelDTO);
            childDiscountList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));

            getCustomizedChildTotalDiscount(childDiscountList, projSelDTO);
            projDTOList.add(prjTotalDisChildDtoList.get(NumericConstants.ZERO));
            projDTOList.add(prjTotalDisChildDtoList.get(NumericConstants.ONE));
        } else {

            programCodeList = getProgramCodeDiscount(projSelDTO);

            getCustomizedProgramCode(programCodeList, projSelDTO);
            if (projSelDTO.getGroup().equals(MANDATED_DISCOUNT.getConstant())) {
                projDTOList.addAll(programCodeDtoList.get(NumericConstants.TWO));
            } else if (projSelDTO.getGroup().equals(SUPPLEMENTAL_DISCOUNT.getConstant())) {
                projDTOList.addAll(programCodeDtoList.get(NumericConstants.FIVE));
            }
        }
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getDiscountDollar(ProjectionSelectionDTO projSelDTO) throws PortalException  {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        String query = "";
        if (projSelDTO.isIsTotal()) {
            getCustomizedTotalDiscount(prMainQuery, projSelDTO);
            projDTOList.add(prjTotalDisDtoList.get(NumericConstants.TWO));
        } else if (projSelDTO.isIsChildTotal()) {
            query = getChildTotalDiscountMainQuery(projSelDTO);
            childDiscountList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query,projSelDTO.getSessionDTO().getCurrentTableNames()));

            getCustomizedChildTotalDiscount(childDiscountList, projSelDTO);
            projDTOList.add(prjTotalDisChildDtoList.get(NumericConstants.FOUR));
            projDTOList.add(prjTotalDisChildDtoList.get(NumericConstants.FIVE));
        } else {

            programCodeList = getProgramCodeDiscount(projSelDTO);

            getCustomizedProgramCode(programCodeList, projSelDTO);
            if (projSelDTO.getGroup().equals(MANDATED_DISCOUNT.getConstant())) {
                projDTOList.addAll(programCodeDtoList.get(NumericConstants.ZERO));
            } else if (projSelDTO.getGroup().equals(SUPPLEMENTAL_DISCOUNT.getConstant())) {
                projDTOList.addAll(programCodeDtoList.get(NumericConstants.THREE));
            }
        }
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getDiscountRPU(ProjectionSelectionDTO projSelDTO) throws PortalException {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        projSelDTO.setSales("RPU");
        String query = "";
        if (projSelDTO.isIsTotal()) {
            getCustomizedTotalDiscount(prMainQuery, projSelDTO);
            projDTOList.add(prjTotalDisDtoList.get(NumericConstants.ONE));
        } else if (projSelDTO.isIsChildTotal()) {

            query = getChildTotalDiscountMainQuery(projSelDTO);
            childDiscountList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query,projSelDTO.getSessionDTO().getCurrentTableNames()));

            getCustomizedChildTotalDiscount(childDiscountList, projSelDTO);
            projDTOList.add(prjTotalDisChildDtoList.get(NumericConstants.TWO));
            projDTOList.add(prjTotalDisChildDtoList.get(NumericConstants.THREE));
        } else {

            programCodeList = getProgramCodeDiscount(projSelDTO);

            getCustomizedProgramCode(programCodeList, projSelDTO);
            if (projSelDTO.getGroup().equals(MANDATED_DISCOUNT.getConstant())) {
                projDTOList.addAll(programCodeDtoList.get(NumericConstants.ONE));
            } else if (projSelDTO.getGroup().equals(SUPPLEMENTAL_DISCOUNT.getConstant())) {
                projDTOList.addAll(programCodeDtoList.get(NumericConstants.FOUR));
            }
        }
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getProjectionTotalDiscounts(ProjectionSelectionDTO projSelDTO, Object[] orderedArgs) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        if (prjTotalDisDolDtoList.isEmpty() || prjTotalDisPerDtoList.isEmpty()) {
            Object[] orderedArgs1 = {orderedArgs[NumericConstants.ZERO], orderedArgs[NumericConstants.ONE], orderedArgs[NumericConstants.TWO], orderedArgs[NumericConstants.THREE], orderedArgs[NumericConstants.FOUR], orderedArgs[NumericConstants.FIVE], orderedArgs[NumericConstants.SIX]};
            if (prcMProjResultsContract.isEmpty()) {
                prcMProjResultsContract = CommonLogic.callProcedure("PRC_M_PROJ_RESULTS_CONTRACT", orderedArgs1);
            }
            List<Object[]> gtsList = prcMProjResultsContract;
            getCustomizedDiscountsProjectionTotal(gtsList, projSelDTO);
        }
        if (projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT)) {
            if (manDisDolDto != null) {
                projDTOList.add(manDisDolDto);
            }
            if (supDisDolDto != null) {
                projDTOList.add(supDisDolDto);
            }

        } else if (projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE)) {
            if (manDisPerDto != null) {
                projDTOList.add(manDisPerDto);
            }
            if (supDisPerDto != null) {
                projDTOList.add(supDisPerDto);
            }
        }

        return projDTOList;
    }

    public void getCustomizedDiscountsProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDolDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projPerDTOList = new ArrayList<>();
        List<String> discountList = new ArrayList<>(projSelDTO.getDiscountNameList());
        String oldDiscountName;
        String newDiscountName = "oldDiscountName";
        if (list != null && !list.isEmpty()) {
            ProjectionResultsDTO projDolDTO = new ProjectionResultsDTO();
            ProjectionResultsDTO projPerDTO = new ProjectionResultsDTO();
            int col = NumericConstants.TWO;
            if (frequencyDivision != NumericConstants.ONE) {
                col = col + NumericConstants.ONE;
            }
            boolean add = false;
            List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
            columnList.remove(Constant.GROUP);
            for (int i = NumericConstants.ZERO; i < list.size(); i++) {
                final Object[] obj = list.get(i);
                String column = "";
                int year = (obj[col - NumericConstants.ONE] == null) ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(obj[col - NumericConstants.ONE]));
                int period = obj[NumericConstants.ONE] == null ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(obj[NumericConstants.ONE]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(NumericConstants.ZERO);
                oldDiscountName = newDiscountName;
                newDiscountName = "" + obj[NumericConstants.THREE];
                if (!oldDiscountName.equals(newDiscountName)) {
                    add = false;
                    if (discountList.contains(newDiscountName)) {
                        add = true;
                        discountList.remove(newDiscountName);
                        projDolDTO = new ProjectionResultsDTO();
                        projPerDTO = new ProjectionResultsDTO();
                        projDolDTO.setParent(NumericConstants.ZERO);
                        projPerDTO.setParent(NumericConstants.ZERO);
                        projDolDTO.setGroup(newDiscountName);
                        projDolDTO.setRelationshipLevelName(newDiscountName);
                        projPerDTO.setGroup(newDiscountName);
                        projPerDTO.setRelationshipLevelName(newDiscountName);
                        projDolDTOList.add(projDolDTO);
                        projPerDTOList.add(projPerDTO);
                        for (String columns : columnList) {
                            projDolDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
                            projPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
                        }
                        columnList = new ArrayList<>(projSelDTO.getColumns());
                        columnList.remove(Constant.GROUP);
                    }

                }
                if (add) {
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        String value = "" + obj[col + NumericConstants.ONE];
                        value = getFormattedValue(CUR_TWO, value);
                        projDolDTO.addStringProperties(column, value);
                        value = "" + obj[col + NumericConstants.THREE];
                        value = getFormattedValue(PER_TWO, value);
                        projPerDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        String value = "" + obj[col + NumericConstants.TWO];
                        value = getFormattedValue(CUR_TWO, value);
                        projDolDTO.addStringProperties(column, value);
                        value = "" + obj[col + NumericConstants.FOUR];
                        value = getFormattedValue(PER_TWO, value);
                        projPerDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                }
            }

        }
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        for (String ob : discountList) {
            ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
            projDTO.setParent(NumericConstants.ZERO);
            projDTO.setProjectionTotal(NumericConstants.ONE);
            projDTO.setGroup(ob);
            projDTO.setRelationshipLevelName(ob);
            ProjectionResultsDTO projDTO1 = new ProjectionResultsDTO();
            projDTO1.setParent(NumericConstants.ZERO);
            projDTO1.setProjectionTotal(NumericConstants.ONE);
            projDTO1.setGroup(ob);
            projDTO1.setRelationshipLevelName(ob);
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
                projDTO1.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
            }
            projDolDTOList.add(projDTO);
            projPerDTOList.add(projDTO1);
        }
        prjTotalDisDolDtoList = new ArrayList<>(projDolDTOList);
        prjTotalDisPerDtoList = new ArrayList<>(projPerDTOList);
    }


    public String getProjectionResultsSalesQueryForPR(ProjectionSelectionDTO projSelDTO) {

        String selectClause = Constant.SELECT_SMALL_SPACE;
        String whereClause = "";
        String groupBy = " I.\"YEAR\"\n";
        String customQuery = "";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += "";
            groupBy += Constant.IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.ONE) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += "";
            groupBy += "";

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += "";
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customSql = " JOIN #SELECTED_HIERARCHY_NO CCP\n"
                + "      ON A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "      JOIN \"PERIOD\" I\n"
                + "      ON A.PERIOD_SID = I.PERIOD_SID "
                + periodFilter
                + whereClause + "\n"
                + " group by " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                + " sum(A.ACTUAL_PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, \n"
                + " sum(A.ACTUAL_PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + (" from ST_M_ACTUAL_SALES A \n ")
                + customSql;
        String futureQuery = selectClause + " 0 as SALES_ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, \n"
                + " sum(A.PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + (" from ST_M_SALES_PROJECTION A \n")
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.HISTORY1, Constant.FUTURE, "on");
        String finalWhereCond = list.get(NumericConstants.ONE);
        String finalSelectClause = " select " + list.get(NumericConstants.ZERO) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as SALES_ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as SALES_PROJECTION_SALES,"
                + "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,\n Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS  ";

        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;

        return customQuery;
    }

    public List<ProjectionResultsDTO> getProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO) throws PortalException{
        int started = start;
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        String freq = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == NumericConstants.ONE) {
            freq = "ANNUALLY";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            freq = "SEMI-ANNUALLY";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            freq = Constant.MONTHLY_COLUMN;
        }
        projSelDTO.setProjectionHeaderList(CommonUtils.prepareProjectionPeriodList(projSelDTO));
        Object[] orderedArgs = {projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionDTO().getSessionId(), freq, Constant.PROJECTION_RESULTS, projSelDTO.getStartPeriod(), projSelDTO.getStartYear()};
        if (projSelDTO.isIsProjectionTotal()) {
            if (started == NumericConstants.ZERO && projSelDTO.isIsTotal()) {
                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                    ProjectionResultsDTO dto = new ProjectionResultsDTO();
                    dto.setGroup(Constant.PROJECTION_TOTAL);
                    dto.setRelationshipLevelName(Constant.PROJECTION_TOTAL);
                    dto.setParent(NumericConstants.ZERO);
                    projDTOList.add(dto);
                }
                neededRecord--;
                started++;
            }
            mayBeAdded++;

            if (neededRecord > NumericConstants.ZERO && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {

                 projectionTotalList = getProjectionTotal(orderedArgs, projSelDTO);
                ProjectionResultsDTO exFacSalesDTO = projectionTotalList.get(NumericConstants.ZERO);
                ProjectionResultsDTO demandSalesDTO = projectionTotalList.get(NumericConstants.ONE);
                ProjectionResultsDTO invWithdrawDTO = projectionTotalList.get(NumericConstants.TWO);
                ProjectionResultsDTO perExfacDTO = projectionTotalList.get(NumericConstants.THREE);
                ProjectionResultsDTO perDemandDTO = projectionTotalList.get(NumericConstants.FOUR);
                ProjectionResultsDTO perinvWithdrawDTO = projectionTotalList.get(NumericConstants.FIVE);
                ProjectionResultsDTO contractSalesDto = projectionTotalList.get(NumericConstants.SIX);
                ProjectionResultsDTO unitVolDto = projectionTotalList.get(NumericConstants.SEVEN);
                ProjectionResultsDTO discountPerDto = projectionTotalList.get(NumericConstants.EIGHT);
                if (discountPerDto != null) {
                    discountPerDto.setOnExpandTotalRow(NumericConstants.ZERO);
                }
                ProjectionResultsDTO discountDolDto = projectionTotalList.get(NumericConstants.NINE);
                if (discountDolDto != null) {
                    discountDolDto.setOnExpandTotalRow(NumericConstants.ZERO);
                }
                ProjectionResultsDTO discountRPUDto = projectionTotalList.get(NumericConstants.TEN);
                if (discountRPUDto != null) {
                    discountRPUDto.setOnExpandTotalRow(NumericConstants.ZERO);
                }
                ProjectionResultsDTO netSalesDto = projectionTotalList.get(NumericConstants.ELEVEN);
                ProjectionResultsDTO cogsDTO = projectionTotalList.get(NumericConstants.TWELVE);
                ProjectionResultsDTO netProfitDTO = projectionTotalList.get(NumericConstants.THIRTEEN);
                manDisDolDto = projectionTotalList.get(NumericConstants.FOURTEEN);
                supDisDolDto = projectionTotalList.get(NumericConstants.FIFTEEN);
                supDisPerDto = projectionTotalList.get(NumericConstants.SIXTEEN);
                manDisPerDto = projectionTotalList.get(NumericConstants.SEVENTEEN);
                supRPUDto = projectionTotalList.get(NumericConstants.NINETEEN);
                manRPUDto = projectionTotalList.get(NumericConstants.EIGHTEEN);

                if (projSelDTO.isIsChildTotal() && projSelDTO.getGroup().equals(TOTAL_DISCOUNT_PERC.getConstant())) {

                    if (started == NumericConstants.ZERO) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(manDisPerDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                    if (started == NumericConstants.ONE) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(supDisPerDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;

                } else if (projSelDTO.isIsChildTotal() && projSelDTO.getGroup().equals(TOTAL_DISCOUNT_AMOUNT.getConstant())) {

                    if (started == NumericConstants.ZERO) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(manDisDolDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                    if (started == NumericConstants.ONE) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(supDisDolDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                } else if (projSelDTO.isIsChildTotal() && projSelDTO.getGroup().equals(TOTAL_RPU.getConstant())) {

                    if (started == NumericConstants.ZERO) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(manRPUDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                    if (started == NumericConstants.ONE) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(supRPUDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                } else if (projSelDTO.getGroup().equals(Constant.MANDATED_DISCOUNT) && projSelDTO.getLevelIndicator().equals("D")) {
                    List<ProjectionResultsDTO> discountDolarDtoList = new ArrayList<>();

                    projSelDTO.setTreeLevelNo(NumericConstants.ONE);
                    programCodeList = getProgramCodeDiscount(projSelDTO);
                    getCustomizedProgramCode(programCodeList, projSelDTO);
                    if (programCodeDtoList.isEmpty()) {
                        List<String> discountList = new ArrayList<>(projSelDTO.getDiscountProgramsList());
                        for (String discountList1 : discountList) {
                            ProjectionResultsDTO tempDto = new ProjectionResultsDTO();
                            tempDto.setGroup(discountList1);
                            tempDto.setRelationshipLevelName(discountList1);
                            discountDolarDtoList.add(tempDto);
                        }
                    } else {
                        discountDolarDtoList.addAll(programCodeDtoList.get(NumericConstants.ZERO));
                    }
                    for (int k = started; k < discountDolarDtoList.size() && neededRecord > NumericConstants.ZERO; neededRecord--, k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(discountDolarDtoList.get(k));
                        }
                        started++;
                    }
                    mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();

                } else if (projSelDTO.getGroup().equals(Constant.SUPPLEMENTAL_DISCOUNT_LABEL) && projSelDTO.getLevelIndicator().equals("D")) {
                    List<ProjectionResultsDTO> discountDolarDtoList = new ArrayList<>();

                    projSelDTO.setTreeLevelNo(NumericConstants.ONE);
                    programCodeList = getProgramCodeDiscount(projSelDTO);
                    getCustomizedProgramCode(programCodeList, projSelDTO);

                    if (programCodeDtoList.isEmpty()) {
                        List<String> discountList = new ArrayList<>(projSelDTO.getDiscountProgramsList());
                        for (String discountList1 : discountList) {
                            ProjectionResultsDTO tempDto = new ProjectionResultsDTO();
                            tempDto.setGroup(discountList1);
                            tempDto.setRelationshipLevelName(discountList1);
                            discountDolarDtoList.add(tempDto);
                        }
                    } else {
                        discountDolarDtoList.addAll(programCodeDtoList.get(NumericConstants.THREE));
                    }
                    for (int k = started; k < discountDolarDtoList.size() && neededRecord > NumericConstants.ZERO; neededRecord--, k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(discountDolarDtoList.get(k));
                        }
                        started++;
                    }
                    mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                } else if (projSelDTO.getGroup().equals(Constant.MANDATED_DISCOUNT) && projSelDTO.getLevelIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    List<ProjectionResultsDTO> discountPerDtoList = new ArrayList<>();

                    projSelDTO.setTreeLevelNo(NumericConstants.ONE);
                    programCodeList = getProgramCodeDiscount(projSelDTO);
                    getCustomizedProgramCode(programCodeList, projSelDTO);

                    if (programCodeDtoList.isEmpty()) {
                        List<String> discountList = new ArrayList<>(projSelDTO.getDiscountProgramsList());
                        for (String discountList1 : discountList) {
                            ProjectionResultsDTO tempDto = new ProjectionResultsDTO();
                            tempDto.setRelationshipLevelName(discountList1);
                            discountPerDtoList.add(tempDto);
                        }
                    } else {
                        discountPerDtoList.addAll(programCodeDtoList.get(NumericConstants.TWO));
                    }
                    for (int k = started; k < discountPerDtoList.size() && neededRecord > NumericConstants.ZERO; neededRecord--, k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(discountPerDtoList.get(k));
                        }
                        started++;
                    }
                    mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                } else if (projSelDTO.getGroup().equals(Constant.SUPPLEMENTAL_DISCOUNT_LABEL) && projSelDTO.getLevelIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

                    projSelDTO.setTreeLevelNo(NumericConstants.ONE);
                    programCodeList = getProgramCodeDiscount(projSelDTO);
                    getCustomizedProgramCode(programCodeList, projSelDTO);
                    List<ProjectionResultsDTO> discountPerDtoList = new ArrayList<>();

                    if (programCodeDtoList.isEmpty()) {
                        List<String> discountList = new ArrayList<>(projSelDTO.getDiscountProgramsList());
                        for (String discountList1 : discountList) {
                            ProjectionResultsDTO tempDto = new ProjectionResultsDTO();
                            tempDto.setRelationshipLevelName(discountList1);
                            discountPerDtoList.add(tempDto);
                        }
                    } else {
                        discountPerDtoList.addAll(programCodeDtoList.get(NumericConstants.FIVE));
                    }

                    for (int k = started; k < discountPerDtoList.size() && neededRecord > NumericConstants.ZERO; neededRecord--, k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(discountPerDtoList.get(k));
                        }
                        started++;
                    }
                    mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                } else if (projSelDTO.getGroup().equals(Constant.MANDATED_DISCOUNT) && projSelDTO.getLevelIndicator().equals("R")) {
                    List<ProjectionResultsDTO> discountRPUDtoList = new ArrayList<>();

                    projSelDTO.setTreeLevelNo(NumericConstants.ONE);
                    programCodeList = getProgramCodeDiscount(projSelDTO);
                    getCustomizedProgramCode(programCodeList, projSelDTO);

                    if (programCodeDtoList.isEmpty()) {
                        List<String> discountList = new ArrayList<>(projSelDTO.getDiscountProgramsList());
                        for (String discountList1 : discountList) {
                            ProjectionResultsDTO tempDto = new ProjectionResultsDTO();
                            tempDto.setRelationshipLevelName(discountList1);
                            discountRPUDtoList.add(tempDto);
                        }
                    } else {
                        discountRPUDtoList.addAll(programCodeDtoList.get(NumericConstants.ONE));
                    }
                    for (int k = started; k < discountRPUDtoList.size() && neededRecord > NumericConstants.ZERO; neededRecord--, k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(discountRPUDtoList.get(k));
                        }
                        started++;
                    }
                    mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                } else if (projSelDTO.getGroup().equals(Constant.SUPPLEMENTAL_DISCOUNT_LABEL) && projSelDTO.getLevelIndicator().equals("R")) {

                    projSelDTO.setTreeLevelNo(NumericConstants.ONE);
                    programCodeList = getProgramCodeDiscount(projSelDTO);
                    getCustomizedProgramCode(programCodeList, projSelDTO);
                    List<ProjectionResultsDTO> discountRPUDtoList = new ArrayList<>();

                    if (programCodeDtoList.isEmpty()) {
                        List<String> discountList = new ArrayList<>(projSelDTO.getDiscountProgramsList());
                        for (String discountList1 : discountList) {
                            ProjectionResultsDTO tempDto = new ProjectionResultsDTO();
                            tempDto.setRelationshipLevelName(discountList1);
                            discountRPUDtoList.add(tempDto);
                        }
                    } else {
                        discountRPUDtoList.addAll(programCodeDtoList.get(NumericConstants.FOUR));
                    }

                    for (int k = started; k < discountRPUDtoList.size() && neededRecord > NumericConstants.ZERO; neededRecord--, k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(discountRPUDtoList.get(k));
                        }
                        started++;
                    }
                    mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                }
                if ((started == NumericConstants.ONE && neededRecord > NumericConstants.ZERO) && (exFacSalesDTO != null)) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(exFacSalesDTO);
                        }
                        started++;
                        neededRecord--;
                }

                String salesUnits = projSelDTO.getSalesOrUnit();
                if (neededRecord > NumericConstants.ZERO && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant()))) {
                    if (started == NumericConstants.TWO && neededRecord > NumericConstants.ZERO) {
                        if (demandSalesDTO != null) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                projDTOList.add(demandSalesDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        mayBeAdded++;
                    }
                    if (started == NumericConstants.THREE && neededRecord > NumericConstants.ZERO) {
                        if (invWithdrawDTO != null) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                projDTOList.add(invWithdrawDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        mayBeAdded++;
                    }
                }

                if (started == NumericConstants.FOUR || started == NumericConstants.TWO && neededRecord > NumericConstants.ZERO) {
                    if (perExfacDTO != null) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(perExfacDTO);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > NumericConstants.ZERO && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant()))) {
                    if (started == NumericConstants.FIVE && neededRecord > NumericConstants.ZERO) {
                        if (perDemandDTO != null) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                projDTOList.add(perDemandDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        mayBeAdded++;
                    }
                    if (started == NumericConstants.SIX && neededRecord > NumericConstants.ZERO) {
                        if (perinvWithdrawDTO != null) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                projDTOList.add(perinvWithdrawDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        mayBeAdded++;
                    }
                    if (started == NumericConstants.SEVEN) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(contractSalesDto);
                        }
                        started++;
                        neededRecord--;
                        mayBeAdded++;
                    }
                }
                if (neededRecord > NumericConstants.ZERO && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(UNITS.getConstant()))) {
                    if (started == NumericConstants.EIGHT || started == NumericConstants.THREE) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(unitVolDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > NumericConstants.ZERO && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE)) {
                    if ((started == NumericConstants.NINE || started == NumericConstants.FOUR) || (started == NumericConstants.EIGHT && !salesUnits.equals(UNITS.getConstant()))) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(discountPerDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > NumericConstants.ZERO && !projSelDTO.getGroup().contains("RPU")) {
                    if ((started == NumericConstants.TEN || started == NumericConstants.FIVE) || (started == NumericConstants.NINE && !salesUnits.equals(UNITS.getConstant()))) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(discountRPUDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > NumericConstants.ZERO && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT)) {
                    if ((started == NumericConstants.ELEVEN || started == NumericConstants.SIX) || (started == NumericConstants.TEN && !salesUnits.equals(UNITS.getConstant()))) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(discountDolDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > NumericConstants.ZERO) {
                    if ((started == NumericConstants.TWELVE || started == NumericConstants.SEVEN) || (started == NumericConstants.ELEVEN && !salesUnits.equals(UNITS.getConstant()))) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(netSalesDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > NumericConstants.ZERO) {
                    if ((started == NumericConstants.THIRTEEN || started == NumericConstants.EIGHT) || (started == NumericConstants.TWELVE && !salesUnits.equals(UNITS.getConstant()))) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(cogsDTO);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > NumericConstants.ZERO) {
                    if ((started == NumericConstants.FOURTEEN || started == NumericConstants.NINE) || (started == NumericConstants.THIRTEEN && !salesUnits.equals(UNITS.getConstant()))) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(netProfitDTO);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
            } else if (neededRecord > NumericConstants.ZERO) {

                int mayBeAddedRecord = started - mayBeAdded;
                if (mayBeAddedRecord < NumericConstants.ZERO) {
                    mayBeAddedRecord = NumericConstants.ZERO;
                }
                if (mayBeAddedRecord < projSelDTO.getPeriodList().size()) {
                    List<ProjectionResultsDTO> projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
                    for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > NumericConstants.ZERO; neededRecord--, k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(projectionDtoList.get(k));
                        }
                        started++;
                    }
                }
                mayBeAdded += projSelDTO.getPeriodList().size();
            }
        }
//.........

        //Next To PRoj Total
        if (neededRecord > NumericConstants.ZERO && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            String salesUnits = projSelDTO.getSalesOrUnit();
            if (neededRecord > NumericConstants.ZERO && projSelDTO.isIsTotal() && !projSelDTO.isIsProjectionTotal()) {
                List<ProjectionResultsDTO> contractSalesAndUnits = getContractSalesAndUnits(projSelDTO);
                ProjectionResultsDTO contractSalesDto = contractSalesAndUnits.get(NumericConstants.ZERO);
                ProjectionResultsDTO unitVolDto = contractSalesAndUnits.get(NumericConstants.ONE);
                if (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant())) {
                    if (started == NumericConstants.ZERO) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(contractSalesDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }

                if (neededRecord > NumericConstants.ZERO && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(UNITS.getConstant()))) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.ONE) || started == NumericConstants.ZERO) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(unitVolDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
            }
            if (neededRecord > NumericConstants.ZERO && !projSelDTO.isIsProjectionTotal()) {

                List<ProjectionResultsDTO> discountPerDtoList = Collections.emptyList();
                if (projSelDTO.isIsTotal() || projSelDTO.getGroup().equalsIgnoreCase(Constant.TOTAL_DISCOUNT_PERCEN) || projSelDTO.getDiscountValue().equalsIgnoreCase(Constant.TOTAL_DISCOUNT_PERCEN)) {
                    discountPerDtoList = getDiscountPer(projSelDTO);
                }
                if (projSelDTO.isIsTotal()) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.TWO) || started == NumericConstants.ONE) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.addAll(discountPerDtoList);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                } else {
                    for (int i = start; i < discountPerDtoList.size() && neededRecord > NumericConstants.ZERO; i++) {
                        projDTOList.add(discountPerDtoList.get(i));
                    }
                    mayBeAdded += discountPerDtoList.size();
                }

                List<ProjectionResultsDTO> discountRPUDtoList = Collections.emptyList();
                if (projSelDTO.isIsTotal() || projSelDTO.getGroup().equalsIgnoreCase(Constant.TOTAL_RPU_CAPS) || projSelDTO.getDiscountValue().equalsIgnoreCase(Constant.TOTAL_RPU_CAPS)) {
                    discountRPUDtoList = getDiscountRPU(projSelDTO);
                }
                if (projSelDTO.isIsTotal()) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.THREE) || started == NumericConstants.TWO) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.addAll(discountRPUDtoList);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                } else {
                    for (int i = start; i < discountRPUDtoList.size() && neededRecord > NumericConstants.ZERO; i++) {
                        projDTOList.add(discountRPUDtoList.get(i));
                    }
                    mayBeAdded += discountRPUDtoList.size();
                }

                List<ProjectionResultsDTO> discountDolarDtoList = Collections.emptyList();
                if (projSelDTO.isIsTotal() || projSelDTO.getGroup().equalsIgnoreCase(TOTAL_DISCOUNT_DOLLAR) || projSelDTO.getDiscountValue().equalsIgnoreCase(TOTAL_DISCOUNT_DOLLAR)) {
                    discountDolarDtoList = getDiscountDollar(projSelDTO);
                }

                if (projSelDTO.isIsTotal()) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.FOUR) || started == NumericConstants.THREE) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.addAll(discountDolarDtoList);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                } else {
                    for (int i = start; i < discountDolarDtoList.size() && neededRecord > NumericConstants.ZERO; i++) {
                        projDTOList.add(discountDolarDtoList.get(i));
                    }
                    mayBeAdded += discountDolarDtoList.size();
                }

            }

            if (projSelDTO.isIsTotal() && !projSelDTO.isIsProjectionTotal()) {
                List<ProjectionResultsDTO> netDTO = getNetSales1(projSelDTO);
                ProjectionResultsDTO netSalesDto = netDTO.get(NumericConstants.ZERO);
                ProjectionResultsDTO cogsDTO = netDTO.get(NumericConstants.ONE);
                ProjectionResultsDTO netProfitDto = netDTO.get(NumericConstants.TWO);
                if (neededRecord > NumericConstants.ZERO) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.FIVE) || started == NumericConstants.FOUR) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(netSalesDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > NumericConstants.ZERO) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.SIX) || started == NumericConstants.FIVE) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(cogsDTO);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > NumericConstants.ZERO) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.SEVEN) || started == NumericConstants.SIX) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(netProfitDto);
                        }
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
            }
        } else if (neededRecord > NumericConstants.ZERO && !projSelDTO.isIsProjectionTotal()) {
            List<ProjectionResultsDTO> projectionDtoList = getProjectionPivot(projSelDTO);
            int mayBeAddedRecord = start - mayBeAdded;
            if (mayBeAddedRecord < NumericConstants.ZERO) {
                mayBeAddedRecord = NumericConstants.ZERO;
            }
            for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > NumericConstants.ZERO; k++,neededRecord--) {
                projDTOList.add(projectionDtoList.get(k));
            }
            mayBeAdded += projectionDtoList.size();
        }
        if (neededRecord > NumericConstants.ZERO && projSelDTO.isIsTotal() && !projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) {
            int mayBeAddedRecord = start - mayBeAdded;
            if (mayBeAddedRecord < NumericConstants.ZERO) {
                mayBeAddedRecord = NumericConstants.ZERO;
            }
            projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + NumericConstants.ONE);
            List<ProjectionResultsDTO> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, projSelDTO);
            projDTOList.addAll(nextLevelValueList);
        }

        return projDTOList;
    }

    public List<ProjectionResultsDTO> getProjectionResultsTotal(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        int mayBeAdded = NumericConstants.ZERO;
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        String freq = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == NumericConstants.ONE) {
            freq = "ANNUALLY";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            freq = "SEMI-ANNUALLY";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            freq = Constant.MONTHLY_COLUMN;
        }

        Object[] orderedArgs = {projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionDTO().getSessionId(), freq, Constant.PROJECTION_RESULTS, projSelDTO.getStartPeriod(), projSelDTO.getStartYear()};
        if (neededRecord > NumericConstants.ZERO) {
            if (start < NumericConstants.ONE) {
                ProjectionResultsDTO dto = new ProjectionResultsDTO();
                dto.setGroup(Constant.PROJECTION_TOTAL);
                dto.setRelationshipLevelName(Constant.PROJECTION_TOTAL);
                dto.setParent(NumericConstants.ZERO);
                projDTOList.add(dto);
                neededRecord--;
            }
            mayBeAdded++;
            if (neededRecord > NumericConstants.ZERO && projSelDTO.getPivotView().contains(PERIOD.getConstant()) && projSelDTO.isIsTotal()) {
                List<ProjectionResultsDTO> projectionList = getProjectionTotal(orderedArgs, projSelDTO);
                ProjectionResultsDTO gtsDto = projectionList.get(NumericConstants.ZERO);
                ProjectionResultsDTO percentDto = projectionList.get(NumericConstants.ONE);
                ProjectionResultsDTO contractSalesDto = projectionList.get(NumericConstants.TWO);
                ProjectionResultsDTO unitVolDto = projectionList.get(NumericConstants.THREE);
                ProjectionResultsDTO discountPerDto = projectionList.get(NumericConstants.FOUR);
                if (discountPerDto != null) {
                    discountPerDto.setOnExpandTotalRow(NumericConstants.ZERO);
                }
                ProjectionResultsDTO discountDolDto = projectionList.get(NumericConstants.FIVE);
                if (discountDolDto != null) {
                    discountDolDto.setOnExpandTotalRow(NumericConstants.ZERO);
                }
                ProjectionResultsDTO netSalesDto = projectionList.get(NumericConstants.SIX);
                manDisDolDto = projectionList.get(NumericConstants.SEVEN);
                supDisDolDto = projectionTotalList.get(NumericConstants.EIGHT);

                supDisPerDto = projectionList.get(NumericConstants.NINE);
                manDisPerDto = projectionList.get(NumericConstants.TEN);
                if ((start < NumericConstants.TWO && neededRecord > NumericConstants.ZERO) && (gtsDto != null)) {
                        projDTOList.add(gtsDto);
                        neededRecord--;
                }
                if ((start < NumericConstants.THREE && neededRecord > NumericConstants.ZERO) && (percentDto != null)) {
                        projDTOList.add(percentDto);
                        neededRecord--;
                }
                String salesUnits = projSelDTO.getSalesOrUnit();
                if ((start < NumericConstants.FOUR) && (neededRecord > NumericConstants.ZERO && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant())))) {
                        projDTOList.add(contractSalesDto);
                        neededRecord--;
                }
                if ((neededRecord > NumericConstants.ZERO && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(UNITS.getConstant())))
                        && ((salesUnits.equals(BOTH.getConstant()) && start < NumericConstants.FIVE) || start < NumericConstants.FOUR)) {
                        projDTOList.add(unitVolDto);
                        neededRecord--;
                }
                if ((neededRecord > NumericConstants.ZERO && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT))
                        && ((salesUnits.equals(BOTH.getConstant()) && start < NumericConstants.SIX) || start < NumericConstants.FIVE)) {
                        projDTOList.add(discountPerDto);
                        neededRecord--;
                }
                if ((neededRecord > NumericConstants.ZERO && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE))
                       && ((salesUnits.equals(BOTH.getConstant()) && start < NumericConstants.SEVEN) || start < NumericConstants.SIX)){
                        projDTOList.add(discountDolDto);
                        neededRecord--;
                }
                if ((neededRecord > NumericConstants.ZERO)
                        && ((salesUnits.equals(BOTH.getConstant()) && start < NumericConstants.EIGHT) || start < NumericConstants.SEVEN)) {
                        projDTOList.add(netSalesDto);
                }
            } else if (neededRecord > NumericConstants.ZERO) {
                List<ProjectionResultsDTO> projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);

                int mayBeAddedRecord = start - mayBeAdded;
                if (mayBeAddedRecord < NumericConstants.ZERO) {
                    mayBeAddedRecord = NumericConstants.ZERO;
                }
                for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > NumericConstants.ZERO; neededRecord--, k++) {
                    projDTOList.add(projectionDtoList.get(k));
                }
            }
        }
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getConfiguredProjectionResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) throws PortalException {
        List<ProjectionResultsDTO> resultList;
        if ((!projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) || parentId instanceof ProjectionResultsDTO) {
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
                projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == NumericConstants.ONE);
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == NumericConstants.ONE);
                projSelDTO.setIsChildTotal(parentDto.getChildTotal() == NumericConstants.ONE);
                projSelDTO.setLevelIndicator(parentDto.getLevelIndicator());
                projSelDTO.setDiscountValue(parentDto.getDiscountValue());
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(NumericConstants.ZERO);
                    projSelDTO.setTreeLevelNo(NumericConstants.ZERO);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - NumericConstants.ONE);
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - NumericConstants.ONE);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - NumericConstants.ONE);
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - NumericConstants.ONE);
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
            resultList = configureLevels(start, offset, projSelDTO);
        }
        return resultList;
    }

    public List<ProjectionResultsDTO> getConfiguredProjectionResultsTotal(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> resultList = new ArrayList<>();
        projSelDTO.setIsProjectionTotal(true);
        projSelDTO.setIsTotal(true);
        if (!projSelDTO.isIsFilter()) {
            projSelDTO.setYear(Constant.ALL);

            if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + Constant.AND_SMALL_SPACE + PROJECTIONS.getConstant());
            }
            if (projSelDTO.isIsCustomHierarchy()) {
                projSelDTO.setLevelNo(NumericConstants.ZERO);
                projSelDTO.setTreeLevelNo(NumericConstants.ZERO);
            } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - NumericConstants.ONE);
                projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - NumericConstants.ONE);
            } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - NumericConstants.ONE);
                projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - NumericConstants.ONE);
            }

            projSelDTO.setGroup(StringUtils.EMPTY);
            projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            resultList = getProjectionResultsTotal(start, offset, projSelDTO);
        }
        return resultList;
    }

    public List<ProjectionResultsDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        List<ProjectionResultsDTO> resultList = new ArrayList<>();
        if (neededRecord > NumericConstants.ZERO) {
              resultList=getLevelListforMandated(start, offset, projSelDTO,neededRecord);
        }
        return resultList;
    }

    public int getConfiguredProjectionResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelsCount, ProjectionSelectionDTO initialProjSelDTO) throws  PortalException  {
        int count = NumericConstants.ZERO;
        if ((!projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) || parentId instanceof ProjectionResultsDTO) {
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
                projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == NumericConstants.ONE);
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == NumericConstants.ONE);
                projSelDTO.setIsChildTotal(parentDto.getChildTotal() == NumericConstants.ONE);
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                projSelDTO.setHierarchyIndicator(initialProjSelDTO.getHierarchyIndicator());
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(NumericConstants.ZERO);
                    projSelDTO.setTreeLevelNo(NumericConstants.ZERO);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(initialProjSelDTO.getCustomerLevelNo() - NumericConstants.ONE);
                    projSelDTO.setTreeLevelNo(initialProjSelDTO.getCustomerLevelNo() - NumericConstants.ONE);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(initialProjSelDTO.getProductLevelNo() - NumericConstants.ONE);
                    projSelDTO.setTreeLevelNo(initialProjSelDTO.getProductLevelNo() - NumericConstants.ONE);
                }
                projSelDTO.setGroup(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            count += getProjectionResultsCount(projSelDTO, isLevelsCount);
        } else if (isLevelsCount || projSelDTO.isFilterDdlb()) {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            count += configureLevelsCount(projSelDTO);
        }
        return count;
    }

    public int getConfiguredProjectionResultsTotalCount(ProjectionSelectionDTO projSelDTO) {
        int count = NumericConstants.ZERO;
        projSelDTO.setIsProjectionTotal(true);
        projSelDTO.setIsTotal(true);
        if (!projSelDTO.isIsFilter()) {
            projSelDTO.setYear(Constant.ALL);

            if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + Constant.AND_SMALL_SPACE + PROJECTIONS.getConstant());
            }
            if (projSelDTO.isIsCustomHierarchy()) {
                projSelDTO.setLevelNo(NumericConstants.ZERO);
                projSelDTO.setTreeLevelNo(NumericConstants.ZERO);
            } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - NumericConstants.ONE);
                projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - NumericConstants.ONE);
            } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - NumericConstants.ONE);
                projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - NumericConstants.ONE);
            }
            projSelDTO.setGroup(StringUtils.EMPTY);
            projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            count += getProjectionResultsTotalCount(projSelDTO);
        }
        return count;
    }

    public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelsCount) throws PortalException {
        int count = NumericConstants.ZERO;
        if (!projSelDTO.getGroup().startsWith(Constant.ALL)
                && !projSelDTO.getGroup().contains(Constant.SALES_WITH_HYPHEN)
                && !projSelDTO.getGroup().contains(Constant.DISCOUNT)) {
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                String salesUnits = projSelDTO.getSalesOrUnit();
                if (projSelDTO.isIsTotal()) {
                    count = count + NumericConstants.SEVEN;
                    if (projSelDTO.isIsProjectionTotal()) {
                        count = count + NumericConstants.SEVEN;
                    }
                    if (salesUnits.equals(BOTH.getConstant())) {
                        count++;
                    }
                }
                if (!projSelDTO.isIsTotal()) {
                    if (projSelDTO.isIsChildTotal()) {
                        count = count + NumericConstants.TWO;
                    } else {
                        count = count + (projSelDTO.isIsProjectionTotal() ? projSelDTO.getDiscountProgramsList().size() : getProgramCodeCount(projSelDTO));
                    }
                }
            } else if (!projSelDTO.isIsProjectionTotal()) {
                count = count + projSelDTO.getPeriodList().size();
            }
        }
        if (!projSelDTO.isFilterDdlb() && projSelDTO.isIsTotal() && isLevelsCount && !projSelDTO.isIsFilter()) {
            projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + NumericConstants.ONE);
            if (projSelDTO.isIsProjectionTotal()) {
                count = count + configureLevelsCount(projSelDTO);
            } else {
                int levelCount;
                 CommonLogic commonLogic = new CommonLogic();
                if (projSelDTO.isIsCustomHierarchy()) {
                    levelCount = commonLogic.getCountForCustomView(projSelDTO);
                } else {
                    levelCount = commonLogic.getCount(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyIndicator());
                }
                count = count + levelCount;
                projSelDTO.setLevelCount(levelCount);
            }
        }
        LOGGER.debug("Ends getProjectionResultsCount= {} " , count);
        return count;
    }

    public int getProjectionResultsTotalCount(ProjectionSelectionDTO projSelDTO) {
        int count = NumericConstants.ZERO;
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            String salesUnits = projSelDTO.getSalesOrUnit();
            count = count + NumericConstants.SEVEN;
            if (salesUnits.equals(BOTH.getConstant())) {
                count++;
            }

        } else {
            count = count + NumericConstants.ONE + projSelDTO.getPeriodList().size();
        }
        return count;
    }

    public int configureLevelsCount(ProjectionSelectionDTO projSelDTO) {
        int count = NumericConstants.ZERO;
            if (!projSelDTO.isIsFilter() && !projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                count = count + NumericConstants.ONE + projSelDTO.getPeriodList().size();
            }
        int levelCount;
                CommonLogic commonLogic = new CommonLogic();
                if (projSelDTO.isIsCustomHierarchy()) {
                    levelCount = commonLogic.getCountForCustomView(projSelDTO);
                } else {
                    levelCount = commonLogic.getCount(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyIndicator());
                }
        count = count + levelCount;
        projSelDTO.setLevelCount(levelCount);
        LOGGER.debug("Ends configureLevelsCount= {} " , count);
        return count;
    }

    public List<Object[]> getProgramCodeDiscount(ProjectionSelectionDTO projSelDTO) {

        Map<Integer, String> freq = new HashMap<>();
        freq.put(NumericConstants.ONE, Constant.YEAR_SPACE);
        freq.put(NumericConstants.TWO, Constant.SEMI_ANNUAL);
        freq.put(NumericConstants.FOUR, Constant.QUARTER);
        freq.put(NumericConstants.TWELVE, "\"MONTH\"");
        if (projSelDTO.getTreeLevelNo() == NumericConstants.ZERO) { //need to change
            projSelDTO.setTreeLevelNo(NumericConstants.FIVE);
        }
        String query=getProgramCodeQuery(freq.get(projSelDTO.getFrequencyDivision()), projSelDTO);
        programCodeList = (List<Object[]>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        return Collections.unmodifiableList(programCodeList);
    }

    public void getCustomizedProgramCode(List<Object[]> list, ProjectionSelectionDTO projSelDTO) throws PortalException{

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDolManDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projRPUManDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projPerManDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projDolSupDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projRPUSupDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projPerSupDTOList = new ArrayList<>();
        List<String> discountList = new ArrayList<>(projSelDTO.getDiscountProgramsList());

        if (list != null && !list.isEmpty() && !discountList.isEmpty()) {
            int col = NumericConstants.THREE;
            List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
            columnList.remove(Constant.GROUP);

            discountList = projSelDTO.isIsProjectionTotal() ? discountList : getProgramCodeList(projSelDTO);
            for (int z = NumericConstants.ZERO; z < discountList.size(); z++) {
                ProjectionResultsDTO projDolManDTO = new ProjectionResultsDTO();
                ProjectionResultsDTO projRPUManDTO = new ProjectionResultsDTO();
                ProjectionResultsDTO projPerManDTO = new ProjectionResultsDTO();
                ProjectionResultsDTO projDolSupDTO = new ProjectionResultsDTO();
                ProjectionResultsDTO projRPUSupDTO = new ProjectionResultsDTO();
                ProjectionResultsDTO projPerSupDTO = new ProjectionResultsDTO();
                for (int i = NumericConstants.ZERO; i < list.size(); i++) {
                    final Object[] obj = list.get(i);
                    String column = "";
                    int year = obj[col - NumericConstants.ONE] == null ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(obj[col - NumericConstants.ONE]));
                    int period = obj[NumericConstants.FIFTEEN] == null ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(obj[NumericConstants.FIFTEEN]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(NumericConstants.ZERO);
                    if (obj[NumericConstants.ZERO] != null && discountList.get(z).equals(obj[NumericConstants.ZERO])) {
                        projDolManDTO.setParent(NumericConstants.ZERO);
                        projRPUManDTO.setParent(NumericConstants.ZERO);
                        projPerManDTO.setParent(NumericConstants.ZERO);
                        projDolSupDTO.setParent(NumericConstants.ZERO);
                        projRPUSupDTO.setParent(NumericConstants.ZERO);
                        projPerSupDTO.setParent(NumericConstants.ZERO);

                        projDolManDTO.setGroup(obj[NumericConstants.ZERO].toString());
                        projRPUManDTO.setGroup(obj[NumericConstants.ZERO].toString());
                        projPerManDTO.setGroup(obj[NumericConstants.ZERO].toString());
                        projDolSupDTO.setGroup(obj[NumericConstants.ZERO].toString());
                        projRPUSupDTO.setGroup(obj[NumericConstants.ZERO].toString());
                        projPerSupDTO.setGroup(obj[NumericConstants.ZERO].toString());

                        projDolManDTO.setLevelValue(obj[NumericConstants.ZERO].toString());
                        projRPUManDTO.setLevelValue(obj[NumericConstants.ZERO].toString());
                        projPerManDTO.setLevelValue(obj[NumericConstants.ZERO].toString());
                        projDolSupDTO.setLevelValue(obj[NumericConstants.ZERO].toString());
                        projRPUSupDTO.setLevelValue(obj[NumericConstants.ZERO].toString());
                        projPerSupDTO.setLevelValue(obj[NumericConstants.ZERO].toString());

                        projDolManDTO.setRelationshipLevelName(obj[NumericConstants.ZERO].toString());
                        projRPUManDTO.setRelationshipLevelName(obj[NumericConstants.ZERO].toString());
                        projPerManDTO.setRelationshipLevelName(obj[NumericConstants.ZERO].toString());
                        projDolSupDTO.setRelationshipLevelName(obj[NumericConstants.ZERO].toString());
                        projRPUSupDTO.setRelationshipLevelName(obj[NumericConstants.ZERO].toString());
                        projPerSupDTO.setRelationshipLevelName(obj[NumericConstants.ZERO].toString());

                        column = commonColumn + ACTUALS.getConstant();

                        if (projSelDTO.hasColumn(column)) {
                            String value = "" + obj[NumericConstants.THREE];
                            value = getFormattedValue(CUR_TWO, value);
                            projDolManDTO.addStringProperties(column, value);
                            value = "" + obj[NumericConstants.FOUR];
                            value = getFormattedValue(CUR_TWO, value);
                            projRPUManDTO.addStringProperties(column, value);
                            value = "" + obj[NumericConstants.FIVE];
                            value = getFormattedValue(PER_TWO, value);
                            projPerManDTO.addStringProperties(column, value);
                            value = "" + obj[NumericConstants.SIX];
                            value = getFormattedValue(CUR_TWO, value);
                            projDolSupDTO.addStringProperties(column, value);
                            value = "" + obj[NumericConstants.SEVEN];
                            value = getFormattedValue(CUR_TWO, value);
                            projRPUSupDTO.addStringProperties(column, value);
                            value = "" + obj[NumericConstants.EIGHT];
                            value = getFormattedValue(PER_TWO, value);
                            projPerSupDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            String value = "" + obj[NumericConstants.NINE];
                            value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                            value = getFormattedValue(CUR_TWO, value);
                            projDolManDTO.addStringProperties(column, value);
                            value = "" + obj[NumericConstants.TEN];
                            value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                            value = getFormattedValue(CUR_TWO, value);
                            projRPUManDTO.addStringProperties(column, value);
                            value = "" + obj[NumericConstants.ELEVEN];
                            value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                            value = getFormattedValue(PER_TWO, value);
                            projPerManDTO.addStringProperties(column, value);
                            value = "" + obj[NumericConstants.TWELVE];
                            value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                            value = getFormattedValue(CUR_TWO, value);
                            projDolSupDTO.addStringProperties(column, value);
                            value = "" + obj[NumericConstants.THIRTEEN];
                            value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                            value = getFormattedValue(CUR_TWO, value);
                            projRPUSupDTO.addStringProperties(column, value);
                            value = "" + obj[NumericConstants.FOURTEEN];
                            value = !CommonUtils.setProjectionZero(projSelDTO, commonColumn) ? value : Constant.ZERO_STRING;
                            value = getFormattedValue(PER_TWO, value);
                            projPerSupDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                    }
                }
                for (String columns : columnList) {
                    projDolManDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
                    projRPUManDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
                    projPerManDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
                    projDolSupDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
                    projRPUSupDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
                    projPerSupDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.ZERO_STRING));
                }

                projDolManDTOList.add(projDolManDTO);
                projRPUManDTOList.add(projRPUManDTO);
                projPerManDTOList.add(projPerManDTO);
                projDolSupDTOList.add(projDolSupDTO);
                projRPUSupDTOList.add(projRPUSupDTO);
                projPerSupDTOList.add(projPerSupDTO);
            }
            programCodeDtoList.clear();
            programCodeDtoList.add(projDolManDTOList);
            programCodeDtoList.add(projRPUManDTOList);
            programCodeDtoList.add(projPerManDTOList);
            programCodeDtoList.add(projDolSupDTOList);
            programCodeDtoList.add(projRPUSupDTOList);
            programCodeDtoList.add(projPerSupDTOList);
        }
        LOGGER.debug("Ends getCustomizedProgramCode");
    }

    public List<ProjectionResultsDTO> getProjectionPivot(ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDTOList;
        String query = " DECLARE @FROM_DATE DATE\n"
                + "     , @STARTFROM DATE\n"
                + "     , @PROJECTION_DATE DATE\n"
                + "     , @START_PERIOD_SID INT\n"
                + "     , @END_PERIOD_SID INT\n"
                + "\n"
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
                + "WHERE PERIOD_DATE = @PROJECTION_DATE \n";
        String gtsListQuery = getProjectionResultsPivotQuery(projSelDTO);
        query += gtsListQuery;
        List<Object> gtsList = (List<Object>) CommonLogic.executeSelectQuery(query);

        List<Object[]> pcList = addProgramCodeDiscounts(projSelDTO);

        projDTOList = getCustomizedProjectionPivot(gtsList, pcList, projSelDTO);

        return projDTOList;
    }

    private List<Object[]> addProgramCodeDiscounts(ProjectionSelectionDTO projSelDTO) {
        List<Object[]> list;
        Map<Integer, String> freq = new HashMap<>();
        freq.put(NumericConstants.ONE, Constant.YEAR_SPACE);
        freq.put(NumericConstants.TWO, Constant.SEMI_ANNUAL);
        freq.put(NumericConstants.FOUR, Constant.QUARTER);
        freq.put(NumericConstants.TWELVE, "\"MONTH\"");
        String query=getProgramCodeQuery(freq.get(projSelDTO.getFrequencyDivision()), projSelDTO);
        list = (List<Object[]>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        return list;
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionPivot(List<Object> list, List<Object[]> pcList, ProjectionSelectionDTO projSelDTO) {

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(list);
        }
        for (Object rows : list) {
            final Object[] row = (Object[]) rows;
            String column;
            int year = (row[NumericConstants.ZERO] == null) ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(row[NumericConstants.ZERO]));
            int period = projSelDTO.getFrequencyDivision() == NumericConstants.ONE ? NumericConstants.ZERO : (row[NumericConstants.ONE] == null) ? NumericConstants.ZERO : Integer.parseInt(String.valueOf(row[NumericConstants.ONE]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(NumericConstants.ZERO);
            String commonHeader = common.get(NumericConstants.ONE);

            String commonColumn;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
                projDTO.setGroup(commonHeader);
                projDTO.setRelationshipLevelName(commonHeader);
                String value;
                commonColumn = "conSalesWac";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWO];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.THREE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }

                commonColumn = "unitVol";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.FOUR];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.FIVE];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = Constant.TOT_DIS_PER;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.SIX];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.SEVEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = Constant.TOT_RPU;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.EIGHT];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.NINE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = Constant.TOTAL_DISCOUNT_DOLLAR;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.ELEVEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "totDisDolMandatedDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.FOURTEEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }

                commonColumn = "totRPUMandatedDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.FIFTEEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_ONE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "totDisPerMandatedDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.SIXTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_TWO];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "totDisDolSupplementalDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.SEVENTEEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_THREE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "totRPUSupplementalDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.EIGHTEEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_FOUR];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "totDisPerSupplementalDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.NINETEEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_FIVE];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "netSales";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWELVE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.THIRTEEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "cogs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_SIX];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_SEVEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "netProfit";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_EIGHT];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_NINE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                }

                for (int i = NumericConstants.ZERO; i < pcList.size(); i++) {
                    Object[] discountRow = pcList.get(i);
                    int dyear = Integer.parseInt(String.valueOf(discountRow[NumericConstants.TWO]));
                    int dperiod = Integer.parseInt(String.valueOf(discountRow[NumericConstants.FIFTEEN]));
                    List<String> dcommon = getCommonColumnHeader(frequencyDivision, dyear, dperiod);
                    String dcommonColumn = dcommon.get(NumericConstants.ZERO);
                    if (pcommonColumn.contains(dcommonColumn)) {
                        commonColumn = discountRow[NumericConstants.ZERO].toString().replace(" ", StringUtils.EMPTY);
                        column = Constant.TOT_DIS_PER + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();

                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[NumericConstants.FIVE];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                        }
                        column = Constant.TOT_DIS_PER + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[NumericConstants.ELEVEN];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                        }
                        column = Constant.TOT_DIS_PER + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[NumericConstants.EIGHT];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                        }
                        column = Constant.TOT_DIS_PER + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[NumericConstants.FOURTEEN];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                        }
                        column = Constant.TOT_RPU + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[NumericConstants.FOUR];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                        }
                        column = Constant.TOT_RPU + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[NumericConstants.TEN];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                        }
                        column = Constant.TOT_RPU + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[NumericConstants.SEVEN];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                        }
                        column = Constant.TOT_RPU + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[NumericConstants.THIRTEEN];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                        }

                        column = Constant.TOTAL_DISCOUNT_DOLLAR + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[NumericConstants.THREE];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                        }
                        column = Constant.TOTAL_DISCOUNT_DOLLAR + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[NumericConstants.NINE];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                        }
                        column = Constant.TOTAL_DISCOUNT_DOLLAR + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[NumericConstants.SIX];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                        }
                        column = Constant.TOTAL_DISCOUNT_DOLLAR + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[NumericConstants.TWELVE];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                        }
                    }

                }

                projDTO.setParent(NumericConstants.ZERO);
                projDTO.setProjectionTotal(NumericConstants.ONE);
                projDTOList.add(projDTO);
            }

        }
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        for (String ob : periodList) {
            ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
            projDTO.setParent(NumericConstants.ZERO);
            projDTO.setProjectionTotal(NumericConstants.ONE);
            projDTO.setGroup(projSelDTO.getPeriodListMap().get(ob));
            projDTO.setRelationshipLevelName(projSelDTO.getPeriodListMap().get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(CUR_TWO, Constant.ZERO_STRING));
            }
            projDTOList.add(projDTO);
        }

        return projDTOList;
    }

    public List<ProjectionResultsDTO> getProjectionPivotTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDTOList;
        if (projSelDTO.getSessionDTO().isPrRefreshReqd() || !CommonLogic.checkProcedureInputIsSame(orderedArgs, totalPRCInput)) {
            List<Object[]> gtsList =CommonLogic.callProcedure("PRC_M_PROJECTION_RESULTS", orderedArgs);
            totalPRCInput = orderedArgs;
            projSelDTO.getSessionDTO().setPrRefreshReqd(false);
            prcMProcedureResults=gtsList;
        }
        List<Object[]> gtsList = prcMProcedureResults;
        Object[] orderedArgs1 = {orderedArgs[NumericConstants.ZERO], orderedArgs[NumericConstants.ONE], orderedArgs[NumericConstants.TWO], orderedArgs[NumericConstants.THREE], orderedArgs[NumericConstants.FOUR], orderedArgs[NumericConstants.FIVE], orderedArgs[NumericConstants.SIX]};
        List<Object[]> discountList = CommonLogic.callProcedure("PRC_M_PROJ_RESULTS_CONTRACT", orderedArgs1);
        projDTOList = getCustomizedProjectionPivotTotal(gtsList, discountList, projSelDTO);
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionPivotTotal(List<Object[]> list, List<Object[]> discountList, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        int col = NumericConstants.THREE;
        int dcol = NumericConstants.ONE;
        if (frequencyDivision != NumericConstants.ONE) {
            col = col + NumericConstants.ONE;
            dcol = dcol + NumericConstants.ONE;
        }
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(list);
        }
        for (Object[] row : list) {

            String column;
            int year = Integer.parseInt(String.valueOf(row[col]));
            int period = Integer.parseInt(String.valueOf(frequencyDivision != NumericConstants.ONE ? row[col - NumericConstants.ONE] : NumericConstants.ZERO));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(NumericConstants.ZERO);
            String commonHeader = common.get(NumericConstants.ONE);
            String commonColumn;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
                List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
                columnList.remove(Constant.GROUP);
                projDTO.setGroup(commonHeader);
                projDTO.setRelationshipLevelName(commonHeader);
                String value;
                commonColumn = "efs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.ONE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWO];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "dms";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_ONE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_TWO];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "iws";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_THREE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_FOUR];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perOfExfac";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.SEVENTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.EIGHTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perOfDemand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_FIVE];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_SIX];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perOfInvwithdraw";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_SEVEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_EIGHT];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "conSalesWac";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.ONE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWO];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "unitVol";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THREE];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FOUR];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.TOT_DIS_PER;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FIFTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.SIXTEEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.TOT_RPU;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY_THREE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY_FOUR];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.TOTAL_DISCOUNT_DOLLAR;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTEEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FOURTEEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisDolMandatedDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FIVE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.SIX];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisDolSupplementalDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.SEVEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.EIGHT];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisPerMandatedDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.NINE];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisPerSupplementalDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.ELEVEN];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWELVE];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totRPUMandatedDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY_NINE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totRPUSupplementalDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY_ONE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY_TWO];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netSales";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.NINETEEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWENTY];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "cogs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY_FIVE];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY_SIX];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netProfit";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY_SEVEN];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THIRTY_EIGHT];
                    value = getFormattedValue(CUR_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                for (int i = NumericConstants.ZERO; i < discountList.size(); i++) {

                    Object[] discountRow = discountList.get(i);
                    int dyear = Integer.parseInt(String.valueOf(discountRow[dcol]));
                    int dperiod = Integer.parseInt(String.valueOf(frequencyDivision != NumericConstants.ONE ? discountRow[dcol - NumericConstants.ONE] : NumericConstants.ZERO));
                    List<String> dcommon = getCommonColumnHeader(frequencyDivision, dyear, dperiod);
                    String dcommonColumn = dcommon.get(NumericConstants.ZERO);
                    if (pcommonColumn.contains(dcommonColumn)) {
                        int programCodeIndex = projSelDTO.getFrequency().equals(ANNUALLY.getConstant()) ? NumericConstants.FOUR : NumericConstants.FIVE;
                        commonColumn = discountRow[programCodeIndex].toString().replace(" ", StringUtils.EMPTY);
                        column = Constant.TOT_DIS_PER + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.TWELVE];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.TOT_DIS_PER + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.THIRTEEN];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.TOT_DIS_PER + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.FOURTEEN];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.TOT_DIS_PER + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.FIFTEEN];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.TOT_RPU + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.SIXTEEN];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.TOT_RPU + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol +NumericConstants.SEVENTEEN];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.TOT_RPU + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.EIGHTEEN];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.TOT_RPU + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.NINETEEN];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.TOTAL_DISCOUNT_DOLLAR + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.EIGHT];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.TOTAL_DISCOUNT_DOLLAR + MANDATED_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.NINE];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.TOTAL_DISCOUNT_DOLLAR + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.TEN];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.TOTAL_DISCOUNT_DOLLAR + SUPPLEMENTAL_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.ELEVEN];
                            value = getFormattedValue(CUR_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                    }
                }

                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormattedValue(NUM_ZERO, Constant.NULL));
                }
                projDTO.setParent(NumericConstants.ZERO);
                projDTO.setProjectionTotal(NumericConstants.ONE);
                projDTOList.add(projDTO);
            }

        }
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        for (String ob : periodList) {
            ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
            projDTO.setParent(NumericConstants.ZERO);
            projDTO.setProjectionTotal(NumericConstants.ONE);
            projDTO.setGroup(projSelDTO.getPeriodListMap().get(ob));
            projDTO.setRelationshipLevelName(projSelDTO.getPeriodListMap().get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(NUM_ZERO, Constant.NULL));
            }
            projDTOList.add(projDTO);
        }

        return projDTOList;
    }

    public String getFormattedValue(DecimalFormat decFormat, String value) {
        String valueMan = value;
        if (valueMan.contains(Constant.NULL)) {
            valueMan = "0";
        } 
        if (decFormat.toPattern().contains(Constant.PERCENT)) {
            Double newValue = Double.valueOf(valueMan);
            newValue = newValue / NumericConstants.HUNDRED;
            valueMan = decFormat.format(newValue);
        }else{
            valueMan = decFormat.format(Double.valueOf(valueMan));
        }
        return valueMan;
    }


    public String getChildTotalDiscountQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " select  ";
        selectClause += "p.\"YEAR\" as YEARS, ";
        String groupBy = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "p.QUARTER as PERIODS, \n";
            groupBy += " p.QUARTER,";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "p.SEMI_ANNUAL as PERIODS, \n";
            groupBy += " p.SEMI_ANNUAL,";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.ONE) {
            selectClause += "'0' as PERIODS, \n";
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "p.\"MONTH\" as PERIODS, \n";
            groupBy += " p.\"MONTH\",";
        }
        if (projSelDTO.isFuture()) {
            selectClause += " Sum(Isnull(MAD.PROJECTION_SALES,0))   AS  MAD_PROJECTION_SALES,\n"
                    + "       (Sum(Isnull(MAD.PROJECTION_SALES, 0)) / NULLIF(Sum(m_ac.PROJECTION_SALES), 0)) * 100    AS  MAD_PROJECTION_RATE,\n"
                    + "       (SUM(ISNULL(MAD.PROJECTION_SALES, 0)) / NULLIF(SUM(M_AC.PROJECTION_UNITS), 0)) AS MAD_PROJECTION_RPU, \n"
                    + "       Sum(Isnull(SPMA.PROJECTION_SALES,0))   AS  SUP_PROJECTION_SALES,\n"
                    + "       ( Sum(Isnull(SPMA.PROJECTION_SALES, 0)) / NULLIF(Sum(m_ac.PROJECTION_SALES), 0) ) * 100    AS  SUP_PROJECTION_RATE,\n"
                    + "       (SUM(ISNULL(SPMA.PROJECTION_SALES, 0)) / NULLIF(SUM(M_AC.PROJECTION_UNITS), 0)) AS SUP_PROJECTION_RPU  \n";
        } else {

            selectClause += " Sum(Isnull(MAD.ACTUAL_SALES,0))        AS  MAD_ACTUAL_SALES,\n"
                    + "       ( Sum(Isnull(MAD.ACTUAL_SALES, 0)) / NULLIF(Sum(m_ac.ACTUAL_SALES), 0) ) * 100    AS  MAD_ACTUAL_RATE,\n"
                    + "       (SUM(ISNULL(MAD.ACTUAL_SALES, 0)) / NULLIF(SUM(M_AC.ACTUAL_UNITS), 0)) AS MAD_ACTUAL_RPU ,\n"
                    + "       Sum(Isnull(SPMA.ACTUAL_SALES,0))        AS  SUP_ACTUAL_SALES,\n"
                    + "       ( Sum(Isnull(SPMA.ACTUAL_SALES, 0)) / NULLIF(Sum(m_ac.ACTUAL_SALES), 0) ) * 100        AS  SUP_ACTUAL_RATE,\n"
                    + "         (SUM(ISNULL(SPMA.ACTUAL_SALES, 0)) / NULLIF(SUM(M_AC.ACTUAL_UNITS), 0)) AS SUP_ACTUAL_RPU ";
        }

        String query = selectClause + " FROM   #SELECTED_HIERARCHY_NO CCP "
                + " JOIN  ST_M_SALES_PROJECTION_MASTER "
                + " m_mas ON CCP.CCP_DETAILS_SID = m_mas.CCP_DETAILS_SID\n"
                + " JOIN   ";
        if (projSelDTO.isFuture()) {
            query += (" ST_M_SALES_PROJECTION");
        } else {
            query += (" ST_M_ACTUAL_SALES");
        }
        query += " m_ac ON m_mas.CCP_DETAILS_SID = m_ac.CCP_DETAILS_SID\n"
                + "JOIN ";
        if (projSelDTO.isFuture()) {
            query += "ST_M_DISCOUNT_PROJECTION";
        } else {
            query += "ST_M_ACTUAL_DISCOUNT";
        }
        query += " MAD ON MAD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + " LEFT JOIN  ST_M_SUPPLEMENTAL_DISC_MASTER "
                + " SPM ON SPM.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + " LEFT JOIN   ";
        if (projSelDTO.isFuture()) {
            query += (" ST_M_SUPPLEMENTAL_DISC_PROJ");
        } else {
            query += (" ST_M_SUPPLEMENTAL_DISC_ACTUALS");
        }
        query += " SPMA ON SPMA.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                                                        AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + " JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                + " GROUP  BY ";
        if (projSelDTO.getFrequencyDivision() != NumericConstants.ONE) {
            query += "" + groupBy + " \n";
        }

        query += "                           CCP.HIERARCHY_NO,\n"
                + "                          p.\"YEAR\"";
        return query;
    }

    public String getChildTotalDiscountMainQuery(ProjectionSelectionDTO projSelDTO) {
        CommonLogic commonLogic = new CommonLogic();
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.HISTORY1, Constant.FUTURE, "on");
        String finalWhereCond = list.get(NumericConstants.ONE);
        String finalSelectClause = "select   " + list.get(NumericConstants.ZERO) + "\n "
                + "HISTORY.MAD_ACTUAL_SALES as MAD_ACTUAL_SALES,\n"
                + "HISTORY.MAD_ACTUAL_RATE as MAD_ACTUAL_RATE,\n"
                + "HISTORY.MAD_ACTUAL_RPU as MAD_ACTUAL_RPU,\n"
                + "HISTORY.SUP_ACTUAL_SALES as SUP_ACTUAL_SALES,\n"
                + "HISTORY.SUP_ACTUAL_RATE as SUP_ACTUAL_RATE,\n"
                + "HISTORY.SUP_ACTUAL_RPU as SUP_ACTUAL_RPU,\n"
                + "FUTURE.MAD_PROJECTION_SALES as MAD_PROJECTION_SALES,\n"
                + "FUTURE.MAD_PROJECTION_RATE as MAD_PROJECTION_RATE,\n"
                + "FUTURE.MAD_PROJECTION_RPU as MAD_PROJECTION_RPU,\n"
                + "FUTURE.SUP_PROJECTION_SALES as SUP_PROJECTION_SALES,\n"
                + "FUTURE.SUP_PROJECTION_RATE as SUP_PROJECTION_RATE,\n"
                + "FUTURE.SUP_PROJECTION_RPU as SUP_PROJECTION_RPU\n";
        projSelDTO.setFuture(false);
        String historyQuery = getChildTotalDiscountQuery(projSelDTO);
        projSelDTO.setFuture(true);
        String futureQuery = getChildTotalDiscountQuery(projSelDTO);
         String ccpQuery = commonLogic.insertAvailableHierarchyNo(projSelDTO);
        String customQuery = ccpQuery + " \n" + finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;
        return customQuery;
    }

    public String getTotalDiscountMainQuery(ProjectionSelectionDTO projSelDTO) {
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.HISTORY1, Constant.FUTURE, "on");
        String finalWhereCond = list.get(NumericConstants.ONE);
        String finalSelectClause = "select " + list.get(NumericConstants.ZERO) + "\n "
                + "(Isnull(HISTORY.MAD_ACTUAL_SALES,0)+Isnull(HISTORY.SUP_ACTUAL_SALES,0)) as ACTUAL_SALES,\n"
                + "((Isnull(HISTORY.MAD_ACTUAL_RATE,0)+Isnull(HISTORY.SUP_ACTUAL_RATE,0))/2) as ACTUAL_RATE,\n"
                + "(ISNULL(HISTORY.MAD_ACTUAL_RPU, 0) + ISNULL(HISTORY.SUP_ACTUAL_RPU, 0)) AS TOTAL_ACTUAL_RPU,\n"
                + "(Isnull(FUTURE.MAD_PROJECTION_SALES,0)+Isnull(FUTURE.SUP_PROJECTION_SALES,0)) as PROJECTION_SALES,\n"
                + "((Isnull(FUTURE.MAD_PROJECTION_RATE,0)+Isnull(FUTURE.SUP_PROJECTION_RATE,0))/2) as PROJECTION_RATE,\n"
                + "(Isnull(FUTURE.MAD_PROJECTION_RPU, 0) + Isnull(FUTURE.SUP_PROJECTION_RPU, 0)) AS TOTAL_PROJ_RPU";
        projSelDTO.setFuture(false);
        String historyQuery = getChildTotalDiscountQuery(projSelDTO);
        projSelDTO.setFuture(true);
        String futureQuery = getChildTotalDiscountQuery(projSelDTO);

        String customQuery = finalSelectClause + "  from  (\n " + historyQuery + "\n) HISTORY FULL  OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n " + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsNetSalesQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL DROP TABLE #TEMP_CCP;\n"
                + "CREATE TABLE #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID INT\n"
                + "     , CONTRACT_MASTER_SID INT\n"
                + "     , ITEM_MASTER_SID INT\n"
                + "     , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "     , PROJECTION_MASTER_SID INT\n"
                + "     )\n"
                + "\n"
                + "INSERT INTO #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID\n"
                + "     , CONTRACT_MASTER_SID\n"
                + "     , ITEM_MASTER_SID\n"
                + "     , PROJECTION_DETAILS_SID\n"
                + "     , PROJECTION_MASTER_SID\n"
                + "     ) \n"
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
                + "\n"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + "\n"
                + "INSERT INTO @ITEMID\n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM\n"
                + "WHERE EXISTS (\n"
                + "            SELECT 1\n"
                + "            FROM #TEMP_CCP A\n"
                + "            WHERE PROJECTION_MASTER_SID =  " + projSelDTO.getProjectionId() + "\n"
                + "                   AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "            )";

        selectClause += Constant.SELECT_SMALL_SPACE;
        String customQuery;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", "on");
        selectClause += list.get(NumericConstants.ZERO);
        String finalWhereCond = list.get(NumericConstants.ONE);
        selectClause += " ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-Isnull(TODIS.ACTUAL_SALES, 0)),\n"
                + " PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-Isnull(TODIS.PROJECTION_SALES, 0)),COGS_ACTUAL = (ISNULL(SALE.ACTUAL_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n"
                + "       , COGS_PROJECTED = (ISNULL(SALE.PROJECTION_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n"
                + "       , NET_PROFIT_ACTUAL = ((ISNULL(SALE.SALES_ACTUAL_SALES, 0) * (ISNULL(SALE.ACTUAL_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))))\n"
                + "       , NET_PROFIT_PROJECTED = ((ISNULL(SALE.SALES_PROJECTION_SALES, 0) * (ISNULL(SALE.PROJECTION_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0)))) \n";
        String totalDiscountQuery = getTotalDiscountMainQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQueryForPR(projSelDTO);
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        customQuery = selectClause + " from (\n " + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        customQuery += " FULL OUTER JOIN (" + cogsQuery + "\n";
        customQuery += " ORDER BY YEARS\n"
                + "       , PERIODS";
        return customQuery;
    }

    public String getProjectionResultsNetSalesQueryForPR(ProjectionSelectionDTO projSelDTO) {
        String selectClause = Constant.SELECT_SMALL_SPACE;
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.TODIS_LABEL, "SALE", "on");
        selectClause += list.get(NumericConstants.ZERO);
        String finalWhereCond = list.get(NumericConstants.ONE);
        selectClause += " NET_ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-Isnull(TODIS.ACTUAL_SALES, 0)),\n"
                + " NET_PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-Isnull(TODIS.PROJECTION_SALES, 0)), \n"
                + "        Isnull(TODIS.ACTUAL_SALES, 0)    AS total_actual_disc_dollar,\n"
                + "       Isnull(TODIS.PROJECTION_SALES, 0)  AS total_proj_disc_dollar,\n"
                + "       ( Isnull(TODIS.ACTUAL_SALES, 0) / ( NULLIF(SALE.SALES_ACTUAL_SALES, 0) ) ) * 100  AS TOTAL_ACTUAL_DISC_RATE,\n"
                + "       ( Isnull(TODIS.PROJECTION_SALES, 0) / ( NULLIF(SALE.SALES_PROJECTION_SALES, 0) ) ) * 100 AS TOTAL_PROJ_DISC_RATE, \n"
                + "       (Isnull (TOTAL_ACTUAL_RPU,0)) AS TOTAL_ACTUAL_RPU, \n"
                + "       (Isnull (TOTAL_PROJ_RPU,0)) AS TOTAL_PROJ_RPU, \n"
                + "       Isnull(SALE.SALES_ACTUAL_SALES, 0)  AS ACTUAL_SALES,\n"
                + "       Isnull(SALE.SALES_PROJECTION_SALES, 0)  AS PROJECTION_SALES,\n"
                + "       Isnull(SALE.ACTUAL_UNITS, 0)  AS ACTUAL_UNITS,\n"
                + "       Isnull(SALE.PROJECTION_UNITS, 0) AS PROJECTION_UNITS,"
                + "       COGS_ACTUAL=0.000000,\n"
                + "       COGS_PROJECTED=0.000000,\n"
                + "       NET_PROFIT_ACTUAL=0.000000,\n"
                + "       NET_PROFIT_PROJECTED=0.000000";
        String totalDiscountQuery = getTotalDiscountMainQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQueryForPR(projSelDTO);
        customQuery = selectClause + "from  (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsCOGSQuery(ProjectionSelectionDTO projSelDTO) {
        String groupBy = StringUtils.EMPTY;
        String value = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            value = Constant.IQUARTER;
            groupBy = "AND SALE.PERIODS = COGS.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            value = ", I.SEMI_ANNUAL";
            groupBy = "AND SALE.PERIODS = COGS.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.ONE) {
            value = StringUtils.EMPTY;
            groupBy = StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            value = ", I.MONTH";
            groupBy += "AND SALE.PERIODS = COGS.MONTH";
        }
        String query = " SELECT ITEM_PRICE = COALESCE(AVG(ITEM_PRICE), 0)\n"
                + "            , I.YEAR\n"
                + "            " + value + "\n"
                + "     FROM [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U\n"
                + "            , PERIOD I\n"
                + "     WHERE I.PERIOD_SID = U.PERIOD_SID"
                + CommonLogic.getPeriodRestrictionQuery(projSelDTO)
                + "       GROUP BY I.\"YEAR\"\n"
                + "              " + value + "\n"
                + "       ) COGS ON SALE.YEARS = COGS.YEAR\n"
                + "              " + groupBy;

        return query;
    }

    private String getProgramCodeQuery(String frequency, ProjectionSelectionDTO projSelDTO) {
        String columnName = StringUtils.EMPTY;
        List list;
        CommonLogic commonLogic = new CommonLogic();
        String hierSQL = "SELECT FIELD_NAME FROM dbo.HIERARCHY_LEVEL_DEFINITION WHERE HIERARCHY_DEFINITION_SID=" + projSelDTO.getCustHierarchySid() + " and LEVEL_NAME='Contract'";
        list = (List<Object>) CommonLogic.executeSelectQuery(hierSQL);

        if (list != null && !list.isEmpty()) {
            final Object obj = list.get(NumericConstants.ZERO);
            columnName = String.valueOf(obj);
        }

        if (StringUtils.isBlank(columnName)) {
            columnName = "contract_name";
        }
        String customSQL = "SELECT \n"
                + "       cm." + columnName + " as PROGRAM_CODE,\n"
                + "CCP.HIERARCHY_NO AS HIERARCHY_NO,\n"
                + "p.\"YEAR\" AS YEARS,\n"
                + "       Isnull(Sum(MAD.ACTUAL_SALES),0)       AS  MAN_ACT_DOL,\n"
                + "       (Isnull(Sum(MAD.ACTUAL_SALES), 0)/NULLIF(Sum(m_ac.ACTUAL_SALES),0))*100   AS MAN_ACT_RATE,\n"
                + "       ( SUM(ISNULL(MAD.ACTUAL_SALES, 0)) / NULLIF(SUM(M_AC.ACTUAL_UNITS), 0) ) AS MAN_ACT_RPU,\n"
                + "       Isnull(Sum(SPMA.ACTUAL_SALES),0)        AS  SUP_ACT_DOL,\n"
                + "       (Isnull(Sum(SPMA.ACTUAL_SALES), 0)/NULLIF(Sum(m_ac.ACTUAL_SALES),0))*100  AS SUP_ACT_RATE,\n"
                + "       (SUM(ISNULL(SPMA.ACTUAL_SALES, 0)) / NULLIF(SUM(M_AC.ACTUAL_UNITS), 0))  AS SUP_ACT_RPU,"
                + "       0                            As ActualProj\n";
        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL += "         , p." + frequency + " as PERIODS \n";
        } else {
            customSQL += ",0 as PERIODS \n";
        }
        customSQL += "FROM   #SELECTED_HIERARCHY_NO CCP ";
        customSQL += "JOIN ST_M_SALES_PROJECTION_MASTER "
                + "m_mas ON CCP.CCP_DETAILS_SID = m_mas.CCP_DETAILS_SID\n"
                + "JOIN  ST_M_ACTUAL_SALES "
                + " m_ac ON m_mas.CCP_DETAILS_SID = m_ac.CCP_DETAILS_SID\n "
                + "JOIN   ST_M_ACTUAL_DISCOUNT MAD ON MAD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + " JOIN ccp_details ccd ON ccd.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                + " JOIN contract_master cm ON cm.contract_master_sid = ccd.contract_master_sid \n"
                + "LEFT JOIN  ST_M_SUPPLEMENTAL_DISC_MASTER "
                + " SPM ON SPM.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "\n"
                + "LEFT JOIN  ST_M_SUPPLEMENTAL_DISC_ACTUALS "
                + " SPMA  ON SPMA.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + " AND SPMA.PERIOD_SID=m_ac.PERIOD_SID JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                + "GROUP  BY cm." + columnName + ",\n";

        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL += "          p." + frequency + ",\n";
        }
        customSQL += "    CCP.HIERARCHY_NO,\n"
                + "          p.\"YEAR\" \n";

        String customSQL1 = "SELECT\n"
                + "       cm." + columnName + " as PROGRAM_CODE,\n"
                + "CCP.HIERARCHY_NO,\n"
                + "p.\"YEAR\" as YEARS,\n"
                + "       Sum(MAD.PROJECTION_SALES)        AS  MAN_PROJ_DOL,\n"
                + "       (Isnull(Sum(MAD.PROJECTION_SALES),0)/NULLIF(Sum(m_ac.PROJECTION_SALES),0))*100   AS MAN_PROJ_RATE,\n"
                + "       ( SUM(ISNULL(MAD.PROJECTION_SALES, 0)) / NULLIF(SUM(M_AC.PROJECTION_UNITS), 0) )        AS MAN_PROJ_RPU,\n"
                + "       Sum(SPMA.PROJECTION_SALES)        AS  SUP_PROJ_DOL,\n"
                + "       (Isnull(Sum(SPMA.PROJECTION_SALES),0)/NULLIF(Sum(m_ac.PROJECTION_SALES),0))*100  AS SUP_PROJ_RATE,\n"
                + "       (SUM(ISNULL(SPMA.PROJECTION_SALES, 0)) / NULLIF(SUM(M_AC.PROJECTION_UNITS), 0)) AS SUP_PROJ_RPU, \n"
                + "       1                            As ActualProj\n";
        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL1 += "         , p." + frequency + " as PERIODS \n";
        } else {
            customSQL1 += ",0 as PERIODS \n";
        }
        customSQL1 += "FROM  #SELECTED_HIERARCHY_NO CCP ";

        customSQL1 += "JOIN  ST_M_SALES_PROJECTION_MASTER "
                + " m_mas ON CCP.CCP_DETAILS_SID = m_mas.CCP_DETAILS_SID\n"
                + "JOIN  ST_M_SALES_PROJECTION "
                + " m_ac ON m_mas.CCP_DETAILS_SID = m_ac.CCP_DETAILS_SID \n"
                + "JOIN  ST_M_DISCOUNT_PROJECTION MAD ON MAD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID \n"
                + " JOIN ccp_details ccd ON ccd.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                + " JOIN contract_master cm ON cm.contract_master_sid = ccd.contract_master_sid \n"
                + "LEFT JOIN ST_M_SUPPLEMENTAL_DISC_MASTER "
                + " SPM ON SPM.CCP_DETAILS_SID =  CCP.CCP_DETAILS_SID\n"
                + "\n"
                + " LEFT JOIN ST_M_SUPPLEMENTAL_DISC_PROJ "
                + " SPMA ON  SPMA.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "                                                                        AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                + "GROUP  BY cm." + columnName + ",\n";
        if (!Constant.YEAR_SPACE.equalsIgnoreCase(frequency)) {
            customSQL1 += "          p." + frequency + ",\n";
        }
        customSQL1 += " CCP.HIERARCHY_NO,\n"
                + "          p.\"YEAR\"\n";
          
          String ccpQuery = commonLogic.insertAvailableHierarchyNo(projSelDTO);
        String sql = ccpQuery + " \n" + "   select "
                + " Isnull(HISTORY.PROGRAM_CODE,FUTURE.PROGRAM_CODE) as PROGRAM_CODE \n"
                + ",Isnull(HISTORY.HIERARCHY_NO,FUTURE.HIERARCHY_NO) \n"
                + ",Isnull(HISTORY.YEARS,FUTURE.YEARS) as YEARS \n"
                + ",Isnull(HISTORY.MAN_ACT_DOL,0) as MAN_ACT_DOL \n"
                + ",Isnull(HISTORY.MAN_ACT_RPU,0) as MAN_ACT_RPU \n"
                + ",Isnull(HISTORY.MAN_ACT_RATE,0) as MAN_ACT_RATE \n"
                + ",Isnull(HISTORY.SUP_ACT_DOL,0) as SUP_ACT_DOL \n"
                + ",Isnull(HISTORY.SUP_ACT_RPU,0) as SUP_ACT_RPU \n"
                + ",Isnull(HISTORY.SUP_ACT_RATE,0) as SUP_ACT_RATE \n"
                + ",Isnull(FUTURE.MAN_PROJ_DOL,0) as MAN_PROJ_DOL \n"
                + ",Isnull(FUTURE.MAN_PROJ_RPU,0) as MAN_PROJ_RPU \n"
                + ",Isnull(FUTURE.MAN_PROJ_RATE,0) as MAN_PROJ_RATE \n"
                + ",Isnull(FUTURE.SUP_PROJ_DOL,0) as SUP_PROJ_DOL \n"
                + ",Isnull(FUTURE.SUP_PROJ_RPU,0) as SUP_PROJ_RPU \n"
                + ",Isnull(FUTURE.SUP_PROJ_RATE,0) as SUP_PROJ_RATE  \n"
                + ",Isnull(HISTORY.PERIODS,FUTURE.PERIODS) as PERIODS \n"
                + " from (" + customSQL + ") HISTORY FULL OUTER JOIN (" + customSQL1 + ") FUTURE \n "
                + " on HISTORY.YEARS=FUTURE.YEARS  \n"
                + " and HISTORY.PERIODS=FUTURE.PERIODS  \n"
                + " and HISTORY.PROGRAM_CODE=FUTURE.PROGRAM_CODE \n"
                + " Order By YEARS\n"
                + "  ,PERIODS \n"
                + "  ,PROGRAM_CODE \n";
        
        return sql;
    }

    public String getProjectionResultsPivotQuery(ProjectionSelectionDTO projSelDTO) {

        projSelDTO.setIsTotal(true);
        String selectClause = " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + "\n"
                + "CREATE TABLE #TEMP_CCP (\n"
                + "     COMPANY_MASTER_SID INT\n"
                + "     , CONTRACT_MASTER_SID INT\n"
                + "     , ITEM_MASTER_SID INT\n"
                + "     , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "     , PROJECTION_MASTER_SID INT\n"
                + "      )\n"
                + "\n"
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
                + "\n"
                + "DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + "\n"
                + "INSERT INTO @ITEMID\n"
                + "SELECT IM.ITEM_MASTER_SID\n"
                + "FROM ITEM_MASTER IM\n"
                + "WHERE EXISTS (\n"
                + "            SELECT 1\n"
                + "            FROM #TEMP_CCP A\n"
                + "            WHERE PROJECTION_MASTER_SID =  " + projSelDTO.getProjectionId() + "\n"
                + "                   AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "            )";

        selectClause += Constant.SELECT_SMALL_SPACE;
        String customQuery;

        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByNetSalesClause("DISCOUNT", "SALE", projSelDTO.getFrequencyDivision());
        selectClause += list.get(NumericConstants.ZERO);
        String orderBy = list.get(NumericConstants.TWO);

        selectClause += " SALE.SALES_ACTUAL_SALES as CONTRACT_ACTUAL_SALES, \n"
                + " SALE.SALES_PROJECTION_SALES as CONTRACT_PROJECTION_SALES, \n"
                + " SALE.ACTUAL_UNITS as CONTRACT_ACTUAL_UNITS, \n"
                + " SALE.PROJECTION_UNITS as CONTRACT_PROJECTION_UNITS, \n"
                + " TOTAL_ACTUAL_RATE = ((Isnull(DISCOUNT.MAD_ACTUAL_SALES, 0) + Isnull(DISCOUNT.SUP_ACTUAL_SALES, 0)) / nullif(SALE.SALES_ACTUAL_SALES,0)) * 100  ,\n"
                + "TOTAL_PROJECTION_RATE = ((Isnull(DISCOUNT.MAD_PROJECTION_SALES, 0) + Isnull(DISCOUNT.SUP_PROJECTION_SALES, 0)) / nullif(SALE.SALES_PROJECTION_SALES, 0)) * 100, \n"
                + "(ISNULL(DISCOUNT.MAD_ACTUAL_RPU, 0)\n"
                + "  + ISNULL(DISCOUNT.SUP_ACTUAL_RPU, 0)) AS TOTAL_ACTUAL_RPU,\n"
                + "  (ISNULL(DISCOUNT.MAD_PROJECTION_RPU, 0)\n"
                + "  + ISNULL(DISCOUNT.SUP_PROJECTION_RPU, 0)) AS TOTAL_PROJ_RPU,"
                + " (Isnull(DISCOUNT.MAD_ACTUAL_SALES,0)+Isnull(DISCOUNT.SUP_ACTUAL_SALES,0)) as TOTAL_ACTUAL_DOLAR,\n"
                + " (Isnull(DISCOUNT.MAD_PROJECTION_SALES,0)+Isnull(DISCOUNT.SUP_PROJECTION_SALES,0)) as TOTAL_PROJECTION_DOLAR, \n"
                + " NET_ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-(Isnull(DISCOUNT.MAD_ACTUAL_SALES,0)+Isnull(DISCOUNT.SUP_ACTUAL_SALES,0))),  \n"
                + " NET_PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-(Isnull(DISCOUNT.MAD_PROJECTION_SALES,0)+Isnull(DISCOUNT.SUP_PROJECTION_SALES,0))), \n"
                + " ISNULL(DISCOUNT.MAD_ACTUAL_SALES ,0)as MAD_ACTUAL_SALES,\n"
                + " ISNULL(DISCOUNT.MAD_ACTUAL_RPU,0) as MAD_ACTUAL_RPU,\n"
                + " ISNULL(DISCOUNT.MAD_ACTUAL_RATE,0) as MAD_ACTUAL_RATE,\n"
                + " ISNULL(DISCOUNT.SUP_ACTUAL_SALES,0) as SUP_ACTUAL_SALES,\n"
                + " ISNULL(DISCOUNT.SUP_ACTUAL_RPU,0) as SUP_ACTUAL_RPU,\n"
                + " ISNULL(DISCOUNT.SUP_ACTUAL_RATE,0) as SUP_ACTUAL_RATE,\n"
                + " ISNULL(DISCOUNT.MAD_PROJECTION_SALES,0) as MAD_PROJECTION_SALES,\n"
                + " ISNULL(DISCOUNT.MAD_PROJECTION_RPU,0) as MAD_PROJECTION_RPU,\n"
                + " ISNULL(DISCOUNT.MAD_PROJECTION_RATE,0) as MAD_PROJECTION_RATE,\n"
                + " ISNULL(DISCOUNT.SUP_PROJECTION_SALES,0) as SUP_PROJECTION_SALES,\n"
                + " ISNULL(DISCOUNT.SUP_PROJECTION_RPU,0) as SUP_PROJECTION_RPU,\n"
                + " ISNULL(DISCOUNT.SUP_PROJECTION_RATE,0) as SUP_PROJECTION_RATE,\n"
                + "COGS_ACTUAL = ISNULL(SALE.COGS_ACTUAL, 0),\n"
                + "  COGS_PROJECTED = ISNULL(SALE.COGS_PROJECTED, 0),\n"
                + "  NET_PROFIT_ACTUAL = ((Isnull(SALE.SALES_ACTUAL_SALES, 0) - (Isnull(DISCOUNT.MAD_ACTUAL_SALES, 0) + Isnull(DISCOUNT.SUP_ACTUAL_SALES, 0))) - ISNULL(SALE.COGS_ACTUAL, 0)),\n"
                + "  NET_PROFIT_PROJECTED = ((Isnull(SALE.SALES_PROJECTION_SALES, 0) - (Isnull(DISCOUNT.MAD_PROJECTION_SALES, 0) + Isnull(DISCOUNT.SUP_PROJECTION_SALES, 0))) - ISNULL(SALE.COGS_PROJECTED, 0))";
        String discountQuery = getDiscountTotalQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQueryPivotForPR(projSelDTO);
        CommonLogic commonLogic = new CommonLogic();
          String ccpQuery = commonLogic.insertAvailableHierarchyNo(projSelDTO);
        customQuery = ccpQuery + " \n" + selectClause + " from  (" + discountQuery + ") DISCOUNT  FULL  JOIN (" + salesQuery + ") SALE " + " ON DISCOUNT.YEARS = SALE.YEARS\n"
                + "  AND DISCOUNT.PERIODS = SALE.PERIODS" + "\n order by " + orderBy;
        return customQuery;
    }

    public String getDiscountTotalQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setFuture(false);
        String historyQuery = getChildTotalDiscountQuery(projSelDTO);
        projSelDTO.setFuture(true);
        String futureQuery = getChildTotalDiscountQuery(projSelDTO);
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.HISTORY1, Constant.FUTURE, "on");
        String finalWhereCond = list.get(NumericConstants.ONE);
        String finalSelectClause = "select " + list.get(NumericConstants.ZERO)
                + "HISTORY.MAD_ACTUAL_SALES as MAD_ACTUAL_SALES,\n"
                + "HISTORY.MAD_ACTUAL_RATE as MAD_ACTUAL_RATE,\n"
                + "HISTORY.MAD_ACTUAL_RPU as MAD_ACTUAL_RPU,\n"
                + "HISTORY.SUP_ACTUAL_SALES as SUP_ACTUAL_SALES,\n"
                + "HISTORY.SUP_ACTUAL_RATE as SUP_ACTUAL_RATE,\n"
                + "HISTORY.SUP_ACTUAL_RPU as SUP_ACTUAL_RPU,\n"
                + "FUTURE.MAD_PROJECTION_SALES as MAD_PROJECTION_SALES,\n"
                + "FUTURE.MAD_PROJECTION_RATE as MAD_PROJECTION_RATE,\n"
                + "FUTURE.MAD_PROJECTION_RPU as MAD_PROJECTION_RPU,\n"
                + "FUTURE.SUP_PROJECTION_SALES as SUP_PROJECTION_SALES,\n"
                + "FUTURE.SUP_PROJECTION_RATE as SUP_PROJECTION_RATE,\n"
                + "FUTURE.SUP_PROJECTION_RPU as SUP_PROJECTION_RPU\n";

        String customQuery = finalSelectClause + "from (\n" + historyQuery + "\n) HISTORY  FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE  \n" + finalWhereCond;
        return customQuery;
    }


    public static List<String> getProgramCodeName(int projectionId) {
        List<String> strList = new ArrayList<>();
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(getProgramCodeNameQuery(projectionId));
        if (list != null && !list.isEmpty()) {
            strList = CommonUtils.objectListToStringList(list);
        }
        return strList;
    }

    private static String getProgramCodeNameQuery(int projectionId) {
        String customSQL = "SELECT\n"
                + "    CM.CONTRACT_NAME\n"
                + "FROM\n"
                + "    dbo.PROJECTION_MASTER pm\n"
                + "    JOIN dbo.RELATIONSHIP_LEVEL_DEFINITION rl ON rl.RELATIONSHIP_BUILDER_SID = pm.CUST_RELATIONSHIP_BUILDER_SID\n"
                + "    JOIN dbo.CONTRACT_MASTER CM ON CM.CONTRACT_MASTER_SID = rl.RELATIONSHIP_LEVEL_VALUES\n"
                + "    Join PROJECTION_CUST_HIERARCHY PCM ON  pm.PROJECTION_MASTER_SID=PCM.PROJECTION_MASTER_SID AND PCM.RELATIONSHIP_LEVEL_SID=rl.RELATIONSHIP_LEVEL_SID\n"
                + "WHERE\n"
                + "    pm.PROJECTION_MASTER_SID = " + projectionId + "\n"
                + "    AND rl.LEVEL_NAME = 'Contract'";
        return customSQL;
    }

    private int getProgramCodeCount(final ProjectionSelectionDTO projectionSelectionDTO) throws PortalException{
        CommonLogic commonLogic = new CommonLogic();
        String sql = commonLogic.insertAvailableHierarchyNo(projectionSelectionDTO);
        sql += SQlUtil.getQuery(getClass(),"m.program-code-count-query");
        SalesProjectionDAO dao = new SalesProjectionDAOImpl();
        List list = (List) dao.executeSelectQuery(QueryUtil.replaceTableNames(sql, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
        LOGGER.debug("getProgramCodeCount = = = = = {}" , (Integer) list.get(NumericConstants.ZERO));
        return (Integer) list.get(NumericConstants.ZERO);
    }

    private List getProgramCodeList(final ProjectionSelectionDTO projectionSelectionDTO) throws PortalException {
           CommonLogic commonLogic = new CommonLogic();
        String sql = commonLogic.insertAvailableHierarchyNo(projectionSelectionDTO);
        sql += SQlUtil.getQuery(getClass(),"m.program-code-query");
        SalesProjectionDAO dao = new SalesProjectionDAOImpl();
        List<Object> list = (List) dao.executeSelectQuery(QueryUtil.replaceTableNames(sql, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
        List finalList = new ArrayList();
        for (Object obj : list) {
            finalList.add(obj);
        }
        LOGGER.debug("getProgramCodeList query custom sql = = = = {}" , sql);
        LOGGER.debug("getProgramCodeList = = = = = {}" , finalList.size());
        return finalList;
    }

    public String getNetSalesQuery(ProjectionSelectionDTO projSelDTO) {
         CommonLogic commonLogic = new CommonLogic();
        String customerProductQuery = commonLogic.insertAvailableHierarchyNo(projSelDTO);

        StringBuilder netSalesQuery = new StringBuilder();
        List<String> list = getCommonSelectPeriodNetSalesClause(Constant.TODIS_LABEL, "SALE", projSelDTO.getFrequencyDivision());
        List<String> list2 = getCommonSelectPeriodNetSalesClause(Constant.HISTORY1, Constant.FUTURE, projSelDTO.getFrequencyDivision());
        String value = StringUtils.EMPTY;
        String periodValue = StringUtils.EMPTY;
        String groupByValue = StringUtils.EMPTY;
        String period = StringUtils.EMPTY;
        String periodHisValue = StringUtils.EMPTY;
        String periodGroup = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            value = ", I.QUARTER AS PERIODS \n ";
            periodValue = ",PERIODS   \n";
            groupByValue = APERIODS;
            period = "and HISTORY.PERIODS=FUTURE.PERIODS\n";
            periodHisValue = ", p.QUARTER as PERIODS";
            periodGroup = " p.QUARTER,";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            value = ", I.SEMI_ANNUAL AS PERIODS \n";
            periodValue = ",PERIODS  \n";
            groupByValue = APERIODS;
            period = "and HISTORY.PERIODS=FUTURE.PERIODS \n";
            periodHisValue = ", p.SEMI_ANNUAL as PERIODS";
            periodGroup = " p.SEMI_ANNUAL,";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.ONE) {
            value = StringUtils.EMPTY;
            periodValue = StringUtils.EMPTY;
            groupByValue = StringUtils.EMPTY;
            periodHisValue = StringUtils.EMPTY;
            periodGroup = StringUtils.EMPTY;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            value = ", I.MONTH AS PERIODS \n";
            periodValue = ",PERIODS  \n";
            groupByValue = APERIODS;
            period = "and HISTORY.PERIODS=FUTURE.PERIODS\n";
            periodHisValue = ", p.MONTH as PERIODS";
            periodGroup = " p.MONTH,";
        }
        netSalesQuery.append("DECLARE @FROM_DATE DATE\n"
                ).append( " , @STARTFROM DATE\n"
                ).append( " , @PROJECTION_DATE DATE\n"
                ).append( " , @START_PERIOD_SID INT\n"
                ).append( " , @END_PERIOD_SID INT\n"
                ).append( '\n'
                ).append( " SELECT TOP 1 @STARTFROM = DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, - 3, GETDATE())), 0)\n"
                ).append( " , @PROJECTION_DATE = DATEADD(M, DATEDIFF(M, 0, DATEADD(DAY, - 1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) ).append( 1, 0))), 0)\n"
                ).append( " FROM PROJECTION_MASTER\n"
                ).append( " WHERE PROJECTION_MASTER_SID = " ).append( projSelDTO.getProjectionId() ).append( '\n'
                ).append( '\n'
                ).append( " SELECT @START_PERIOD_SID = PERIOD_SID\n"
                ).append( " FROM PERIOD\n"
                ).append( " WHERE PERIOD_DATE = @STARTFROM\n"
                ).append( '\n'
                ).append( " SELECT @END_PERIOD_SID = PERIOD_SID\n"
                ).append( " FROM PERIOD\n"
                ).append( " WHERE PERIOD_DATE = @PROJECTION_DATE\n"
                ).append( customerProductQuery 
                ).append( " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                ).append( " DROP TABLE #TEMP_CCP;\n"
                ).append( '\n'
                ).append( " CREATE TABLE #TEMP_CCP (\n"
                ).append( " COMPANY_MASTER_SID INT\n"
                ).append( " , CONTRACT_MASTER_SID INT\n"
                ).append( " , ITEM_MASTER_SID INT\n"
                ).append( " , PROJECTION_MASTER_SID INT\n"
                ).append( " )\n"
                ).append( '\n'
                ).append( " INSERT INTO #TEMP_CCP (\n"
                ).append( " COMPANY_MASTER_SID\n"
                ).append( " , CONTRACT_MASTER_SID\n"
                ).append( " , ITEM_MASTER_SID\n"
                ).append( " , PROJECTION_MASTER_SID\n"
                ).append( " )\n"
                ).append( " SELECT C.COMPANY_MASTER_SID\n"
                ).append( " , C.CONTRACT_MASTER_SID\n"
                ).append( " , C.ITEM_MASTER_SID\n"
                ).append( " , pm.PROJECTION_MASTER_SID\n"
                ).append( " FROM\n"
                ).append( " CCP_DETAILS C\n"
                ).append( " , PROJECTION_MASTER PM\n"
                ).append( " WHERE  PM.PROJECTION_MASTER_SID = " ).append( projSelDTO.getProjectionId() ).append( '\n'
                ).append( " and EXISTS (SELECT 1 FROM\n"
                ).append( " #SELECTED_HIERARCHY_NO CCP WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)\n"
                ).append( " DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                ).append( '\n'
                ).append( " INSERT INTO @ITEMID\n"
                ).append( " SELECT IM.ITEM_MASTER_SID\n"
                ).append( " FROM ITEM_MASTER IM\n"
                ).append( " WHERE EXISTS (\n"
                ).append( " SELECT 1\n"
                ).append( " FROM #TEMP_CCP A\n"
                ).append( " WHERE PROJECTION_MASTER_SID =  " ).append( projSelDTO.getProjectionId() ).append( '\n'
                ).append( " AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                ).append( " ) select " ).append( list.get(0) ).append( '\n'
                ).append( " ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-Isnull(TODIS.ACTUAL_SALES, 0)),\n"
                ).append( " PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-Isnull(TODIS.PROJECTION_SALES, 0)),COGS_ACTUAL = ISNULL(SALE.COGS_ACTUAL, 0)\n"
                ).append( " ,COGS_PROJECTED = ISNULL(SALE.COGS_PROJECTED, 0)\n"
                ).append( " ,NET_PROFIT_ACTUAL = ((Isnull(SALE.SALES_ACTUAL_SALES, 0) - (Isnull(TODIS.MADD_ACTUAL_SALES, 0) + Isnull(TODIS.SUPP_ACTUAL_SALES, 0))) - ISNULL(SALE.COGS_ACTUAL, 0))\n"
                ).append( " ,NET_PROFIT_PROJECTED = ((Isnull(SALE.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.MADD_ACTUAL_SALES, 0) + Isnull(TODIS.SUPP_ACTUAL_SALES, 0))) - ISNULL(SALE.COGS_PROJECTED, 0)) from (\n"
                ).append( Constant.SELECT_SMALL_SPACE ).append( list2.get(0) ).append( '\n'
                ).append( '\n'
                ).append( " (Isnull(HISTORY.MAD_ACTUAL_SALES,0)+Isnull(HISTORY.SUP_ACTUAL_SALES,0)) as ACTUAL_SALES,\n"
                ).append( " ((Isnull(HISTORY.MAD_ACTUAL_RATE,0)+Isnull(HISTORY.SUP_ACTUAL_RATE,0))/2) as ACTUAL_RATE,\n"
                ).append( " (ISNULL(HISTORY.MAD_ACTUAL_RPU, 0) + ISNULL(HISTORY.SUP_ACTUAL_RPU, 0) ) AS ACTUAL_RPU,\n"
                ).append( " (Isnull(FUTURE.MAD_PROJECTION_SALES,0)+Isnull(FUTURE.SUP_PROJECTION_SALES,0)) as PROJECTION_SALES,\n"
                ).append( " ((Isnull(FUTURE.MAD_PROJECTION_RATE,0)+Isnull(FUTURE.SUP_PROJECTION_RATE,0))/2) as PROJECTION_RATE,\n"
                ).append( " (Isnull(FUTURE.MAD_PROJECTION_RPU,0)+Isnull(FUTURE.SUP_PROJECTION_RPU,0)) as PROJECTION_RPU "
                ).append( " ,(isnull(history.MAD_ACTUAL_SALES,0))MADD_ACTUAL_SALES\n"
                ).append( " ,(isnull(history.SUP_ACTUAL_SALES,0))SUPP_ACTUAL_SALES\n"
                ).append( " ,(isnull(FUTURE.MAD_PROJECTION_SALES,0))MADD_PROJECTION_SALES\n"
                ).append( " ,(isnull(FUTURE.SUP_PROJECTION_SALES,0))SUPP_PROJECTION_SALES from (\n"
                ).append( " select p.\"YEAR\" as YEARS" ).append( periodHisValue ).append( ",\n"
                ).append( " Sum(Isnull(MAD.ACTUAL_SALES,0))        AS  MAD_ACTUAL_SALES,\n"
                ).append( " ( Sum(Isnull(MAD.ACTUAL_SALES, 0)) / NULLIF(Sum(m_ac.ACTUAL_SALES), 0) ) * 100    AS  MAD_ACTUAL_RATE,\n"
                ).append( " ( SUM(ISNULL(MAD.ACTUAL_SALES, 0)) / NULLIF(SUM(M_AC.ACTUAL_UNITS), 0) )        AS MAD_ACTUAL_RPU,\n"
                ).append( " Sum(Isnull(SPMA.ACTUAL_SALES,0))        AS  SUP_ACTUAL_SALES,\n"
                ).append( " ( Sum(Isnull(SPMA.ACTUAL_SALES, 0)) / NULLIF(Sum(m_ac.ACTUAL_SALES), 0) ) * 100        AS  SUP_ACTUAL_RATE,\n"
                ).append( " ( SUM(ISNULL(SPMA.ACTUAL_SALES, 0)) / NULLIF(SUM(M_AC.ACTUAL_UNITS), 0) ) * 100 AS SUP_ACTUAL_RPU FROM   #SELECTED_HIERARCHY_NO CCP INNER  JOIN  ST_M_SALES_PROJECTION_MASTER "
                ).append( " m_mas ON CCP.CCP_DETAILS_SID =  m_mas.CCP_DETAILS_SID\n"
                ).append( " INNER  JOIN ST_M_ACTUAL_SALES "
                ).append( " m_ac  ON m_mas.CCP_DETAILS_SID = m_ac.CCP_DETAILS_SID\n"
                ).append( " INNER JOIN   ST_M_ACTUAL_DISCOUNT MAD ON MAD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                ).append( " AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                ).append( "  LEFT JOIN ST_M_SUPPLEMENTAL_DISC_MASTER "
                ).append( " SPM ON  SPM.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                ).append( "  LEFT JOIN  ST_M_SUPPLEMENTAL_DISC_ACTUALS "
                ).append( " SPMA ON SPMA.CCP_DETAILS_SID =  CCP.CCP_DETAILS_SID\n"
                ).append( " AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                ).append( " INNER  JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                ).append( " GROUP  BY  " ).append( periodGroup ).append( '\n'
                ).append( " CCP.HIERARCHY_NO,\n"
                ).append( " p.\"YEAR\"\n"
                ).append( " ) HISTORY FULL  JOIN (\n"
                ).append( " select p.\"YEAR\" as YEARS" ).append( periodHisValue ).append( ",\n"
                ).append( " Sum(Isnull(MAD.PROJECTION_SALES,0))   AS  MAD_PROJECTION_SALES,\n"
                ).append( " (Sum(Isnull(MAD.PROJECTION_SALES, 0)) / NULLIF(Sum(m_ac.PROJECTION_SALES), 0)) * 100    AS  MAD_PROJECTION_RATE,\n"
                ).append( " ( SUM(ISNULL(MAD.PROJECTION_SALES, 0)) / NULLIF(SUM(M_AC.PROJECTION_UNITS), 0) )        AS MAD_PROJECTION_RPU,\n"
                ).append( " Sum(Isnull(SPMA.PROJECTION_SALES,0))   AS  SUP_PROJECTION_SALES,\n"
                ).append( " ( Sum(Isnull(SPMA.PROJECTION_SALES, 0)) / NULLIF(Sum(m_ac.PROJECTION_SALES), 0) ) * 100    AS  SUP_PROJECTION_RATE,\n"
                ).append( " Sum(Isnull(SPMA.PROJECTION_RPU,0))   AS  SUP_PROJECTION_RPU\n"
                ).append( " FROM   #SELECTED_HIERARCHY_NO CCP INNER  JOIN  ST_M_SALES_PROJECTION_MASTER "
                ).append( " m_mas ON  CCP.CCP_DETAILS_SID = m_mas.CCP_DETAILS_SID\n"
                ).append( " INNER  JOIN  ST_M_SALES_PROJECTION "
                ).append( " m_ac ON m_mas.CCP_DETAILS_SID = m_ac.CCP_DETAILS_SID\n"
                ).append( " INNER JOIN   ST_M_DISCOUNT_PROJECTION MAD ON MAD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                ).append( " AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                ).append( " LEFT JOIN  ST_M_SUPPLEMENTAL_DISC_MASTER "
                ).append( " SPM ON SPM.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                ).append( " LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_PROJ "
                ).append( " SPMA ON SPMA.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                ).append( " AND SPMA.PERIOD_SID=m_ac.PERIOD_SID INNER  JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                ).append( " GROUP  BY  " ).append( periodGroup ).append( '\n'
                ).append( " CCP.HIERARCHY_NO, p.\"YEAR\"\n"
                ).append( " ) FUTURE\n" + "ON HISTORY.YEARS = FUTURE.YEARS  "
                ).append( period
                ).append( " ) TODIS FULL  JOIN (\n"
                ).append( " SELECT " ).append( list2.get(0) ).append( ""
                ).append( " Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                ).append( " ,Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                ).append( " ,Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                ).append( " ,Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                ).append( " ,Isnull(HISTORY.COGS_ACTUAL, 0) AS COGS_ACTUAL\n"
                ).append( " ,Isnull(FUTURE.COGS_PROJECTED, 0) AS COGS_PROJECTED\n"
                ).append( " FROM ( \n"
                ).append( " SELECT YEARS\n"
                ).append( periodValue
                ).append( " ,sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                ).append( " ,sum(A.ACTUAL_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                ).append( " ,sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                ).append( " ,sum(A.ACTUAL_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                ).append( " ,SUM(A.COGS_ACTUAL) AS COGS_ACTUAL\n"
                ).append( "  FROM (\n "
                ).append( " SELECT I.\"YEAR\" AS YEARS\n"
                ).append( value
                ).append( " ,A.ACTUAL_SALES\n"
                ).append( " ,A.ACTUAL_PROJECTION_SALES\n"
                ).append( " ,A.ACTUAL_UNITS\n"
                ).append( " ,A.ACTUAL_PROJECTION_UNITS\n"
                ).append( " ,COGS_ACTUAL = (ISNULL(A.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                ).append( " FROM  ST_M_ACTUAL_SALES A\n"
                ).append( " INNER JOIN #SELECTED_HIERARCHY_NO CCP ON A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                ).append( " INNER JOIN \"PERIOD\" I ON A.PERIOD_SID = I.PERIOD_SID\n"
                ).append( " INNER JOIN CCP_DETAILS CC ON CCP.CCP_DETAILS_sID = CC.CCP_DETAILS_sID\n"
                ).append( " INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U ON CC.ITEM_MASTER_SID = U.ITEM_MASTER_SID\n"
                ).append( " AND A.PERIOD_SID = U.PERIOD_SID\n"
                ).append( CommonLogic.getPeriodRestrictionQuery(projSelDTO)
                ).append( " ) A\n"
                ).append( " GROUP BY A.YEARS\n"
                ).append( groupByValue
                ).append( " ) HISTORY\n"
                ).append( " FULL JOIN (\n"
                ).append( " SELECT YEARS\n"
                ).append( periodValue
                ).append( " ,0 AS SALES_ACTUAL_SALES\n"
                ).append( " ,sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                ).append( " ,0 AS ACTUAL_UNITS\n"
                ).append( " ,sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                ).append( " ,SUM(P.COGS_PROJECTED) AS COGS_PROJECTED\n"
                ).append( "   FROM (  \n"
                ).append( " SELECT I.\"YEAR\" AS YEARS\n"
                ).append( value
                ).append( " ,A.PROJECTION_SALES\n"
                ).append( " ,A.PROJECTION_UNITS\n"
                ).append( " ,COGS_PROJECTED = (ISNULL(A.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                ).append( " FROM  ST_M_SALES_PROJECTION A\n"
                ).append( " INNER JOIN #SELECTED_HIERARCHY_NO CCP ON A.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                ).append( " INNER JOIN \"PERIOD\" I ON A.PERIOD_SID = I.PERIOD_SID\n"
                ).append( " INNER JOIN CCP_DETAILS CC ON CCP.CCP_DETAILS_sID = CC.CCP_DETAILS_sID\n"
                ).append( " INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U ON CC.ITEM_MASTER_SID = U.ITEM_MASTER_SID\n"
                ).append( " AND A.PERIOD_SID = U.PERIOD_SID\n"
                ).append( CommonLogic.getPeriodRestrictionQuery(projSelDTO)
                ).append( " ) P\n"
                ).append( " GROUP BY YEARS\n"
                ).append( periodValue
                ).append( " ) FUTURE ON HISTORY.YEARS = FUTURE.YEARS\n");
        if (projSelDTO.getFrequencyDivision() != NumericConstants.ONE) {
            netSalesQuery.append("  AND HISTORY.PERIODS = FUTURE.PERIODS\n");
        }
        netSalesQuery.append(" ) SALE\n"
                + "  on TODIS.YEARS=SALE.YEARS");
        if (projSelDTO.getFrequencyDivision() != NumericConstants.ONE) {
            netSalesQuery.append(" and TODIS.PERIODS=SALE.PERIODS ");
        }
        netSalesQuery.append("   ORDER BY YEARS\n" ).append( periodValue );

        return netSalesQuery.toString();
    }
    public static final String APERIODS = ",A.PERIODS\n";

    public String getCustomQuery(ProjectionSelectionDTO projSelDTO) {
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo() + Constant.PERCENT;
        String productHierarchyNo = projSelDTO.getProductHierarchyNo() + Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;

        if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {

            customerLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        } else if (projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

            productLevelNo = StringUtils.EMPTY + projSelDTO.getTreeLevelNo();
        }
        StringBuilder customQuery = new StringBuilder();
        customQuery.append("INSERT INTO @CCP (RELATIONSHIP_LEVEL_SID\n"
                ).append( ", PROJECTION_DETAILS_SID\n"
                ).append( ", CCP_DETAILS_SID\n"
                ).append( ", HIERARCHY_NO)\n"
                ).append( "  SELECT DISTINCT\n"
                ).append( "    HLDC.RELATIONSHIP_LEVEL_SID,\n"
                ).append( "    PD.PROJECTION_DETAILS_SID,\n"
                ).append( "    CCPMAPC.CCP_DETAILS_SID,\n"
                ).append( "    HLDC.HIERARCHY_NO\n"
                ).append( "  FROM (SELECT\n"
                ).append( "    RLD.RELATIONSHIP_LEVEL_VALUES,\n"
                ).append( "    RLD.HIERARCHY_NO,\n"
                ).append( "    CCP.CCP_DETAILS_SID\n"
                ).append( "  FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                ).append( "  INNER JOIN CCP_MAP CCP\n"
                ).append( "    ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                ).append( "    AND RLD.RELATIONSHIP_BUILDER_SID = " ).append( projSelDTO.getCustRelationshipBuilderSid() ).append( '\n'
                ).append( "  INNER JOIN PROJECTION_DETAILS PD\n"
                ).append( "    ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                ).append( "    AND PD.PROJECTION_MASTER_SID =  " ).append( projSelDTO.getProjectionId() ).append( ") CCPMAPC\n"
                ).append( "  INNER JOIN (SELECT\n"
                ).append( "    RLD.RELATIONSHIP_LEVEL_VALUES,\n"
                ).append( "    RLD.HIERARCHY_NO,\n"
                ).append( "    CCP.CCP_DETAILS_SID\n"
                ).append( "  FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                ).append( "  INNER JOIN CCP_MAP CCP\n"
                ).append( "    ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                ).append( "    AND RLD.RELATIONSHIP_BUILDER_SID = " ).append( projSelDTO.getProdRelationshipBuilderSid() ).append( '\n'
                ).append( "  INNER JOIN PROJECTION_DETAILS PD\n"
                ).append( "    ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                ).append( "    AND PD.PROJECTION_MASTER_SID =   " ).append( projSelDTO.getProjectionId() ).append( ") CCPMAPP\n"
                ).append( "    ON CCPMAPC.CCP_DETAILS_SID = CCPMAPP.CCP_DETAILS_SID\n"
                ).append( "  INNER JOIN (SELECT\n"
                ).append( "    RLD2.HIERARCHY_NO,\n"
                ).append( "    RLD2.RELATIONSHIP_LEVEL_SID,\n"
                ).append( "    CVD.LEVEL_NO\n"
                ).append( "  FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                ).append( "  INNER JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD\n"
                ).append( "    ON CVD.HIERARCHY_ID = HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                ).append( "    AND CVD.CUSTOM_VIEW_MASTER_SID =" ).append( projSelDTO.getCustomId() ).append( '\n'
                ).append( "    AND CVD.LEVEL_NO LIKE '" ).append( customerLevelNo ).append( "'\n"
                ).append( "  INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2\n"
                ).append( "    ON HLD.HIERARCHY_LEVEL_DEFINITION_SID = RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                ).append( "  INNER JOIN PROJECTION_CUST_HIERARCHY PCH2\n"
                ).append( "    ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID\n"
                ).append( "    AND PCH2.PROJECTION_MASTER_SID =  " ).append( projSelDTO.getProjectionId() ).append( '\n'
                ).append( "  WHERE RLD2.HIERARCHY_NO LIKE '" ).append( customerHierarchyNo ).append( "') HLDC\n"
                ).append( "    ON CCPMAPC.HIERARCHY_NO LIKE HLDC.HIERARCHY_NO + '%'\n"
                ).append( "  INNER JOIN  (SELECT\n"
                ).append( "    RLD2.HIERARCHY_NO,\n"
                ).append( "    RLD2.RELATIONSHIP_LEVEL_SID,\n"
                ).append( "    CVD.LEVEL_NO\n"
                ).append( "  FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                ).append( "  INNER JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD\n"
                ).append( "    ON CVD.HIERARCHY_ID = HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                ).append( "    AND CVD.CUSTOM_VIEW_MASTER_SID = " ).append( projSelDTO.getCustomId() ).append( '\n'
                ).append( "    AND CVD.LEVEL_NO LIKE '" ).append( productLevelNo ).append( "'\n"
                ).append( "  INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2\n"
                ).append( "    ON HLD.HIERARCHY_LEVEL_DEFINITION_SID = RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                ).append( "  INNER JOIN PROJECTION_PROD_HIERARCHY PCH2\n"
                ).append( "    ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID\n"
                ).append( "    AND PCH2.PROJECTION_MASTER_SID =  " ).append( projSelDTO.getProjectionId() ).append( '\n'
                ).append( "  WHERE RLD2.HIERARCHY_NO LIKE '" ).append( productHierarchyNo ).append( "') HLDP\n"
                ).append( "    ON CCPMAPP.HIERARCHY_NO LIKE HLDP.HIERARCHY_NO + '%'\n"
                ).append( "  INNER JOIN PROJECTION_DETAILS PD \n"
                ).append( "    ON PD.CCP_DETAILS_SID = CCPMAPC.CCP_DETAILS_SID\n"
                ).append( "    AND PD.PROJECTION_MASTER_SID =  " ).append( projSelDTO.getProjectionId() ).append( StringUtils.EMPTY);

        return customQuery.toString();
    }

    public String getCusProdQuery(ProjectionSelectionDTO projSelDTO, String relationshipBuilderSid) {
        StringBuilder cusProdQuery = new StringBuilder();
        cusProdQuery.append("  INSERT INTO @CCP\n"
                ).append( "             (RELATIONSHIP_LEVEL_SID,PROJECTION_DETAILS_SID,CCP_DETAILS_SID,HIERARCHY_NO)\n"
                ).append( "  SELECT LCCP.RELATIONSHIP_LEVEL_SID,LCCP.PROJECTION_DETAILS_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from\n"
                ).append( " (SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID,CCPMAP.PROJECTION_DETAILS_SID from\n"
                ).append( " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID,PD.PROJECTION_DETAILS_SID\n"
                ).append( " FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                ).append( " INNER  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                ).append( " AND RLD.RELATIONSHIP_BUILDER_SID=" ).append( relationshipBuilderSid ).append( '\n'
                ).append( " INNER  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID\n"
                ).append( " AND PD.PROJECTION_MASTER_SID=" ).append( projSelDTO.getProjectionId() ).append( " ) CCPMAP,\n"
                ).append( "  (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID\n"
                ).append( "  FROM RELATIONSHIP_LEVEL_DEFINITION RLD1\n"
                ).append( " INNER  JOIN " ).append( CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator()) ).append( " PCH\n"
                ).append( "  ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID\n"
                ).append( "  AND PCH.PROJECTION_MASTER_SID=" ).append( projSelDTO.getProjectionId() ).append( '\n'
                ).append( "  WHERE RLD1.HIERARCHY_NO like '" ).append( projSelDTO.getHierarchyNo() ).append( "%') HLD\n"
                ).append( "  WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP\n"
                ).append( "  WHERE LCCP.HIERARCHY_NO in\n"
                ).append( "  (SELECT RLD2.HIERARCHY_NO\n"
                ).append( "  FROM RELATIONSHIP_LEVEL_DEFINITION RLD2\n"
                ).append( " INNER  JOIN " ).append( CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator()) ).append( " PCH2\n"
                ).append( "  ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID\n"
                ).append( "  AND PCH2.PROJECTION_MASTER_SID=" ).append( projSelDTO.getProjectionId() ).append( '\n'
                ).append( "  WHERE RLD2.LEVEL_NO=" ).append( projSelDTO.getTreeLevelNo() ).append( ")\n");

        return cusProdQuery.toString();
    }

    public String getProjectionResultsSalesQueryPivotForPR(ProjectionSelectionDTO projSelDTO) {
        String value = StringUtils.EMPTY;
        String periodValue = StringUtils.EMPTY;
        String period = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            value = ", I.QUARTER AS PERIODS \n ";
            periodValue = Constant.PERIODS_COMMA;
            period = Constant.PERIODS_SPACE;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            value = ", I.SEMI_ANNUAL AS PERIODS \n";
            periodValue = Constant.PERIODS_COMMA;
            period = Constant.PERIODS_SPACE;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.ONE) {
            value = ",0 AS PERIODS";
            periodValue = Constant.PERIODS_COMMA;
            period = Constant.PERIODS_SPACE;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            value = ", I.MONTH AS PERIODS \n";
            periodValue = Constant.PERIODS_COMMA;
            period = Constant.PERIODS_SPACE;
        }
        String whereClause = StringUtils.EMPTY;
        String customQuery;
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if ((projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) || (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) 
                || (projSelDTO.getFrequencyDivision() == NumericConstants.ONE) || (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE))  {
            whereClause += StringUtils.EMPTY;
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customSql = " JOIN @CCP CCP\n"
                + "      ON A.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "      JOIN \"PERIOD\" I\n"
                + "      ON A.PERIOD_SID = I.PERIOD_SID "
                + "INNER JOIN CCP_DETAILS CC\n"
                + "  ON CCP.CCP_DETAILS_sID = CC.CCP_DETAILS_sID\n"
                + "INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U\n"
                + "  ON CC.ITEM_MASTER_SID = U.ITEM_MASTER_SID\n"
                + "  AND A.PERIOD_SID = U.PERIOD_SID  "
                + "where  "
                + CommonLogic.getUserSessionQueryConditionForPR(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + periodFilter + "\n"
                + whereClause + "\n";

        String historyQuery = "SELECT  YEARS,\n" + periodValue
                + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                + " sum(A.ACTUAL_PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, \n"
                + " SUM(A.ACTUAL_PROJECTION_UNITS) AS PROJECTION_UNITS,\n"
                + "  SUM(A.COGS_ACTUAL) AS COGS_ACTUAL\n"
                + "FROM (SELECT\n"
                + "  I.\"YEAR\" AS YEARS\n"
                + value
                + "  ,A.ACTUAL_SALES,\n"
                + "  A.ACTUAL_PROJECTION_SALES,\n"
                + "  A.ACTUAL_UNITS,\n"
                + "  A.ACTUAL_PROJECTION_UNITS,\n"
                + "  COGS_ACTUAL = (ISNULL(A.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))"
                + " from ST_M_ACTUAL_SALES A \n "
                + customSql + ")A" + " group by " + "YEARS\n" + period;
        String futureQuery = "SELECT  YEARS,\n" + periodValue + " 0 as SALES_ACTUAL_SALES, \n"
                + " sum(P.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, \n"
                + " SUM(P.PROJECTION_UNITS) AS PROJECTION_UNITS,\n"
                + "  SUM(P.COGS_PROJECTED) AS COGS_PROJECTED\n"
                + "FROM (SELECT\n"
                + "  I.\"YEAR\" AS YEARS\n"
                + value
                + "  ,A.PROJECTION_SALES,\n"
                + "  A.PROJECTION_UNITS,\n"
                + "  COGS_PROJECTED = (ISNULL(A.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))"
                + " from ST_M_SALES_PROJECTION A \n"
                + customSql + ")P" + "  group by " + "YEARS\n" + period;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByNetSalesClause(Constant.HISTORY1, Constant.FUTURE, projSelDTO.getFrequencyDivision());
        String finalWhereCond = list.get(NumericConstants.ONE);
        String finalSelectClause = "     select " + list.get(NumericConstants.ZERO) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as SALES_ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as SALES_PROJECTION_SALES,"
                + "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,"
                + " ISNULL(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) AS PROJECTION_UNITS,\n"
                + "  ISNULL(HISTORY.COGS_ACTUAL, 0) AS COGS_ACTUAL,\n"
                + "  ISNULL(FUTURE.COGS_PROJECTED, 0) AS COGS_PROJECTED \n";

        customQuery = finalSelectClause + "  from (\n" + historyQuery + "\n) HISTORY FULL  JOIN (\n" + futureQuery + "\n)  FUTURE \n" + finalWhereCond;

        return customQuery;
    }

    public static List<String> getCommonSelectPeriodNetSalesClause(String table1, String table2, int freq) {
        List<String> list = new ArrayList<>();
        String orderBy = " YEARS";
        String groupBy = " " + table1 + ".YEARS";
        String selectClause = "Isnull(" + table1 + ".YEARS, " + table2 + ".YEARS) as YEARS, ";
        String finalWhereCond = " " + "on" + " " + table1 + ".YEARS=" + table2 + ".YEARS";
        if (freq != NumericConstants.ONE) {
            selectClause += "Isnull(" + table1 + Constant.DOT_PERIODS_QUOTE + table2 + ".PERIODS)";
            selectClause += " as PERIODS, \n";
        }
        if (freq != NumericConstants.ONE) {
            finalWhereCond += Constant.AND_SMALL_SPACE + table1 + Constant.PERIODS_EQUAL + table2 + Constant.DOT_PERIODS_QUERY;
            groupBy += ", " + table1 + Constant.DOT_PERIODS_QUERY;

        }
        orderBy += ", PERIODS";
        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

    private List<ProjectionResultsDTO> getLevelListforMandated(int start, int offset, ProjectionSelectionDTO projSelDTO, int neededRecord) {
     CommonLogic commonLogic = new CommonLogic();      
     int neededRecordMandated = neededRecord;
        List<ProjectionResultsDTO> resultList = new ArrayList<>();

            if (projSelDTO.isIsCustomHierarchy()) {

                String hierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
                Map<String, List> relationshipLevelDetailsMap = projSelDTO.getSessionDTO().getHierarchyLevelDetails();
                List<String> hierarchyNoList = commonLogic.getHiearchyNoForCustomView(projSelDTO, start, offset);                
               for (int i = NumericConstants.ZERO; i < hierarchyNoList.size() && neededRecordMandated > NumericConstants.ZERO; neededRecordMandated--, i++) {
                   String hierarchyNo=hierarchyNoList.get(i);
                    resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, hierarchyIndicator, projSelDTO.getTreeLevelNo(), relationshipLevelDetailsMap.get(hierarchyNo), hierarchyNoList.size(),i));
                     
              
                   }
                
            } else {
                Map<String, List> relationshipLevelDetailsMap =  projSelDTO.getSessionDTO().getHierarchyLevelDetails();
               
                List<String> hierarchyNoList = commonLogic.getHiearchyNoAsList(projSelDTO, start, offset);
            for (int i = NumericConstants.ZERO; i < hierarchyNoList.size() && neededRecordMandated > NumericConstants.ZERO; neededRecordMandated--, i++) {
                   String hierarchyNo=hierarchyNoList.get(i);
                    resultList.add(configureDetailsInDTO(projSelDTO, hierarchyNo, projSelDTO.getHierarchyIndicator(), Integer.parseInt(relationshipLevelDetailsMap.get(hierarchyNo).get(NumericConstants.TWO).toString()), relationshipLevelDetailsMap.get(hierarchyNo),hierarchyNoList.size(),i));
                   
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
                    dto.setGroup(detailsList.get(NumericConstants.ONE).toString());
                   dto.setLevelValue(detailsList.get(NumericConstants.THREE).toString());
                         dto.setRelationshipLevelName(projSelDTO.getSessionDTO().getLevelValueDiscription(hierarchyNo,hierarchyIndicator));
                    if (i == (listSize - NumericConstants.ONE)) {
                        dto.setGroup(detailsList.get(NumericConstants.ZERO).toString());
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
                    dto.setOnExpandTotalRow(NumericConstants.ONE);
                    dto.setChildTotal(NumericConstants.ZERO);
                    dto.setParent(NumericConstants.ONE);
            
        return dto;
    
 }
}

          
              
