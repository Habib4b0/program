/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.projectionresults.logic;

import com.stpl.app.galforecasting.dao.SalesProjectionDAO;
import com.stpl.app.galforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.galforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import static com.stpl.app.galforecasting.logic.CommonLogic.executeSelectQuery;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.CommonConstants.*;
import static com.stpl.app.galforecasting.utils.Constant.LabelConstants.*;
import static com.stpl.app.galforecasting.utils.HeaderUtils.getCommonColumnHeader;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.SALES;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.ui.ComboBox;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sibi
 */
public class MProjectionResultsLogic {

    private static final Logger LOGGER = Logger.getLogger(MProjectionResultsLogic.class);

    /**
     * The Currency Zero Decimal Places Format.
     */
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0");
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PER_TWO = new DecimalFormat("#,##0.00%");
    private static final DecimalFormat PER_THREE = new DecimalFormat("#,##0.000%");
    /**
     * The Numeric Zero Decimal Places Format.
     */
    private static final DecimalFormat NUM_ZERO = new DecimalFormat("#,##0");

    private static final DecimalFormat CUR_TWO = new DecimalFormat("#,##0.00");

    private static final String CURRENCY = "$";
    private static final String PERCENTAGE = "%";

    ProjectionResultsDTO supDisPerDto = null;
    ProjectionResultsDTO supDisDolDto = null;
    ProjectionResultsDTO manDisPerDto = null;
    ProjectionResultsDTO manDisDolDto = null;
    List<ProjectionResultsDTO> prjTotalDisPerDtoList = new ArrayList<ProjectionResultsDTO>();
    List<ProjectionResultsDTO> prjTotalDisDolDtoList = new ArrayList<ProjectionResultsDTO>();

    List<ProjectionResultsDTO> prjTotalDisDtoList = new ArrayList<ProjectionResultsDTO>();
    List<ProjectionResultsDTO> prjTotalDisChildDtoList = new ArrayList<ProjectionResultsDTO>();
    List<List<ProjectionResultsDTO>> programCodeDtoList = new ArrayList<List<ProjectionResultsDTO>>();

    List<Object> totalDiscountDollarList = new ArrayList<Object>();
    List<Object> childDiscountDollarList = new ArrayList<Object>();
    List<Object[]> programCodeDollarList = new ArrayList<Object[]>();

    List<Object> totalDiscountPerList = new ArrayList<Object>();
    List<Object> childDiscountPerList = new ArrayList<Object>();
    List<Object[]> programCodePerList = new ArrayList<Object[]>();

    List<ProjectionResultsDTO> netSalesDtoList = new ArrayList<ProjectionResultsDTO>();
    List<Object[]> prcMProjResultsContract = new ArrayList<Object[]>();
    List<Object[]> prcMProcedureResults = new ArrayList<Object[]>();
    List<Object> totalDiscount = new ArrayList<Object>();
    List<Object> childDiscountList = new ArrayList<Object>();
    List<Object[]> programCodeList = new ArrayList<Object[]>();
    List<Object> prMainQuery = new ArrayList<Object>();
    List<ProjectionResultsDTO> projectionTotalList = new ArrayList<ProjectionResultsDTO>();

    public List<ProjectionResultsDTO> getProjectionTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        if (prcMProcedureResults.isEmpty()) {
            prcMProcedureResults = CommonLogic.callProcedure("PRC_M_PROJECTION_RESULTS", orderedArgs);
        }
        List<Object[]> gtsList = prcMProcedureResults;
        if (gtsList != null) {
            projDTOList = getCustomizedProjectionTotal(gtsList, projSelDTO);
        }
        gtsList = null;

        return projDTOList;
    }

    public List<ProjectionResultsDTO> getContractSalesAndUnits(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);

        String query = "DECLARE @FROM_DATE DATE\n"
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
        query += CommonLogic.getCCPQueryForPR(projSelDTO) + " \n" + getProjectionResultsNetSalesQueryForPR(projSelDTO);
        prMainQuery = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList = getCustomizedProjectionResultsSales(prMainQuery, projSelDTO);
        return projDTOList;
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
        projSalesDTO.setRelationshipLevelName(CONTRACT_SALES_AT_WAC.getConstant());
        projUnitDTO.setLevelNo(projSelDTO.getLevelNo());
        projUnitDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projUnitDTO.setLevelValue(projSelDTO.getLevelValue());
        projUnitDTO.setGroup(UNIT_VOL.getConstant());
        projUnitDTO.setRelationshipLevelName(UNIT_VOL.getConstant());
        projSalesDTO.setParent(0);
        projUnitDTO.setParent(0);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                int year = obj[0] == null ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = obj[1] == null ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                int col = 8;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + 2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projSalesDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 4];
                    value = getFormattedValue(NUM_ZERO, value);
                    projUnitDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + 3];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projSalesDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 5];
                    value = getFormattedValue(NUM_ZERO, value);
                    projUnitDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            projSalesDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            projUnitDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
        }
        projDtoList.add(projSalesDTO);
        projDtoList.add(projUnitDTO);
        return projDtoList;
    }

    public void getCustomizedTotalDiscount(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
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
        totalDisDolDTO.setParent(1);
        totalDisPerDTO.setParent(1);
        totalDisPerDTO.setChildTotal(1);
        totalRPUDTO.setParent(1);
        totalRPUDTO.setChildTotal(1);
        totalDisDolDTO.setChildTotal(1);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                int year = obj[0] == null ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = obj[1] == null ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                int col = 4;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalDisDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 2];
                    value = getFormattedValue(PER_TWO, value);
                    totalDisPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 4];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalRPUDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + 3];
                    value = getFormattedValue(PER_TWO, value);
                    totalDisPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalDisDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 5];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    totalRPUDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            totalDisPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            totalRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            totalDisDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
        }
        prjTotalDisDtoList.clear();
        prjTotalDisDtoList.add(totalDisPerDTO);
        prjTotalDisDtoList.add(totalRPUDTO);
        prjTotalDisDtoList.add(totalDisDolDTO);
    }

    public void getCustomizedChildTotalDiscount(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
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
        manPerDTO.setGroup(Mandated_Discount.getConstant());
        manPerDTO.setRelationshipLevelName(Mandated_Discount.getConstant());
        manPerDTO.setLevelIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        manPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        manRPUDTO.setLevelNo(projSelDTO.getLevelNo());
        manRPUDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        manRPUDTO.setLevelValue(projSelDTO.getLevelValue());
        manRPUDTO.setGroup(Mandated_Discount.getConstant());
        manRPUDTO.setRelationshipLevelName(Mandated_Discount.getConstant());
        manRPUDTO.setLevelIndicator(Constant.R);
        manRPUDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        manDolDTO.setLevelNo(projSelDTO.getLevelNo());
        manDolDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        manDolDTO.setLevelValue(projSelDTO.getLevelValue());
        manDolDTO.setGroup(Mandated_Discount.getConstant());
        manDolDTO.setRelationshipLevelName(Mandated_Discount.getConstant());
        manDolDTO.setLevelIndicator("D");
        manDolDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        suppPerDTO.setLevelNo(projSelDTO.getLevelNo());
        suppPerDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        suppPerDTO.setLevelValue(projSelDTO.getLevelValue());
        suppPerDTO.setGroup(Supplemental_Discount.getConstant());
        suppPerDTO.setRelationshipLevelName(Supplemental_Discount.getConstant());
        suppPerDTO.setLevelIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        suppPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        suppRPUDTO.setLevelNo(projSelDTO.getLevelNo());
        suppRPUDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        suppRPUDTO.setLevelValue(projSelDTO.getLevelValue());
        suppRPUDTO.setGroup(Supplemental_Discount.getConstant());
        suppRPUDTO.setRelationshipLevelName(Supplemental_Discount.getConstant());
        suppRPUDTO.setLevelIndicator(Constant.R);
        suppRPUDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        suppDolDTO.setLevelNo(projSelDTO.getLevelNo());
        suppDolDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        suppDolDTO.setLevelValue(projSelDTO.getLevelValue());
        suppDolDTO.setGroup(Supplemental_Discount.getConstant());
        suppDolDTO.setRelationshipLevelName(Supplemental_Discount.getConstant());
        suppDolDTO.setLevelIndicator("D");
        suppDolDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        manPerDTO.setParent(1);
        manRPUDTO.setParent(1);
        manDolDTO.setParent(1);
        suppPerDTO.setParent(1);
        suppRPUDTO.setParent(1);
        suppDolDTO.setParent(1);

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                int year = obj[0] == null ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = obj[1] == null ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                int col = 2;

                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY);
                    manDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 1];
                    value = getFormattedValue(PER_THREE, value);
                    manPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 2];
                    value = getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY);
                    manRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 3];
                    value = getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY);
                    suppDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 4];
                    value = getFormattedValue(PER_THREE, value);
                    suppPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 5];
                    value = getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY);
                    suppRPUDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + 6];
                    value = getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY);
                    manDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 7];
                    value = getFormattedValue(PER_THREE, value);
                    manPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 8];
                    value = getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY);
                    manRPUDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 9];
                    value = getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY);
                    suppDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 10];
                    value = getFormattedValue(CUR_ZERO, value);
                    suppPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 11];
                    value = getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY);
                    suppRPUDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            manPerDTO.addStringProperties(columns, getFormattedValue(PER_THREE, Constant.NULL));
            manRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            manDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));

            suppPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            suppRPUDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            suppDolDTO.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
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
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
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

        netSalesDto.setParent(0);
        cogsDto.setParent(0);
        netprofitDto.setParent(0);

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                int year = (obj[0] == null) ? 0 : Integer.valueOf(String.valueOf(obj[0]));
                int period = projSelDTO.getFrequencyDivision() == 1 ? 0 : (obj[1] == null) ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                int col = projSelDTO.getFrequencyDivision() == 1 ? 1 : 2;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netSalesDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    cogsDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 4];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netprofitDto.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netSalesDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 3];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    cogsDto.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 5];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netprofitDto.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            netSalesDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            cogsDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
            netprofitDto.addStringProperties(columns, getFormatTwoDecimalValue(CUR_TWO, Constant.NULL, CURRENCY));
        }
        netSalesDtoList.clear();
        netSalesDtoList.add(netSalesDto);
        netSalesDtoList.add(cogsDto);
        netSalesDtoList.add(netprofitDto);

        return netSalesDtoList;
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

    public List<ProjectionResultsDTO> getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
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
        exFacSalesDTO.setParent(0);
        exFacSalesDTO.setGroup(EX_FACTORY_SALES.getConstant());
        exFacSalesDTO.setRelationshipLevelName(EX_FACTORY_SALES.getConstant());

        demandSalesDTO.setParent(0);
        demandSalesDTO.setGroup(DEMAND_SALES.getConstant());
        demandSalesDTO.setRelationshipLevelName(DEMAND_SALES.getConstant());

        invWithdrawDTO.setParent(0);
        invWithdrawDTO.setGroup(INVENTORY_WITHDRAW.getConstant());
        invWithdrawDTO.setRelationshipLevelName(INVENTORY_WITHDRAW.getConstant());

        perExfacDTO.setParent(0);
        perExfacDTO.setGroup(PERC_OF_EX_FACTORY.getConstant());
        perExfacDTO.setRelationshipLevelName(PERC_OF_EX_FACTORY.getConstant());

        perDemandDTO.setParent(0);
        perDemandDTO.setGroup(PERC_OF_DEMAND.getConstant());
        perDemandDTO.setRelationshipLevelName(PERC_OF_DEMAND.getConstant());

        perinvWithdrawDTO.setParent(0);
        perinvWithdrawDTO.setGroup(PERC_OF_INVENTORY_WITHDRAW.getConstant());
        perinvWithdrawDTO.setRelationshipLevelName(PERC_OF_INVENTORY_WITHDRAW.getConstant());

        conSaleDTO.setParent(0);
        conSaleDTO.setGroup(CONTRACT_SALES_AT_WAC.getConstant());
        conSaleDTO.setRelationshipLevelName(CONTRACT_SALES_AT_WAC.getConstant());

        unitVolDTO.setParent(0);
        unitVolDTO.setGroup(UNIT_VOL.getConstant());
        unitVolDTO.setRelationshipLevelName(UNIT_VOL.getConstant());

        discountPerDTO.setParent(1);
        discountPerDTO.setGroup(TOTAL_DISCOUNT_PERC.getConstant());
        discountPerDTO.setRelationshipLevelName(TOTAL_DISCOUNT_PERC.getConstant());
        discountPerDTO.setProjectionTotal(1);
        discountPerDTO.setChildTotal(1);
        discountPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        discountPerDTO.setLevelIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);

        discountDolDTO.setParent(1);
        discountDolDTO.setGroup(TOTAL_DISCOUNT_AMOUNT.getConstant());
        discountDolDTO.setRelationshipLevelName(TOTAL_DISCOUNT_AMOUNT.getConstant());
        discountDolDTO.setProjectionTotal(1);
        discountDolDTO.setChildTotal(1);
        discountDolDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        discountDolDTO.setLevelIndicator("D");

        discountRPUDTO.setParent(1);
        discountRPUDTO.setGroup(TOTAL_RPU.getConstant());
        discountRPUDTO.setRelationshipLevelName(TOTAL_RPU.getConstant());
        discountRPUDTO.setProjectionTotal(1);
        discountRPUDTO.setChildTotal(1);
        discountRPUDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        discountRPUDTO.setLevelIndicator(Constant.R);

        netSaleDTO.setParent(0);
        netSaleDTO.setGroup(NET_SALES.getConstant());
        netSaleDTO.setRelationshipLevelName(NET_SALES.getConstant());

        cogsDTO.setParent(0);
        cogsDTO.setGroup(COGS.getConstant());
        cogsDTO.setRelationshipLevelName(COGS.getConstant());

        netProfitDTO.setParent(0);
        netProfitDTO.setGroup(NET_PROFIT.getConstant());
        netProfitDTO.setRelationshipLevelName(NET_PROFIT.getConstant());

        supDisDolDTO.setParent(1);
        supDisDolDTO.setGroup("Supplemental Discount");
        supDisDolDTO.setRelationshipLevelName("Supplemental Discount");
        supDisDolDTO.setProjectionTotal(1);
        supDisDolDTO.setLevelIndicator("D");
        supDisDolDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());

        manDisDolDTO.setParent(1);
        manDisDolDTO.setGroup(Constant.MANDATED_DISCOUNT);
        manDisDolDTO.setProjectionTotal(1);
        manDisDolDTO.setRelationshipLevelName(Constant.MANDATED_DISCOUNT);
        manDisDolDTO.setLevelIndicator("D");
        manDisDolDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());

        supDisPerDTO.setParent(1);
        supDisPerDTO.setGroup("Supplemental Discount");
        supDisPerDTO.setProjectionTotal(1);
        supDisPerDTO.setRelationshipLevelName("Supplemental Discount");
        supDisPerDTO.setLevelIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        supDisPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());

        manDisPerDTO.setParent(1);
        manDisPerDTO.setGroup(Constant.MANDATED_DISCOUNT);
        manDisPerDTO.setProjectionTotal(1);
        manDisPerDTO.setRelationshipLevelName(Constant.MANDATED_DISCOUNT);
        manDisPerDTO.setLevelIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        manDisPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());

        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            int col = 3;
            if (frequencyDivision != 1) {
                col = col + 1;
            }
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;

                int year = Integer.valueOf(String.valueOf(obj[col]));
                int period = Integer.valueOf(String.valueOf(frequencyDivision != 1 ? obj[col - 1] : 0));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);

                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    exFacSalesDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    conSaleDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 3];
                    value = getFormattedValue(NUM_ZERO, value);
                    unitVolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 5];

                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    manDisDolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 7];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    supDisDolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 9];
                    value = getFormattedValue(PER_TWO, value);
                    manDisPerDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 11];
                    value = getFormattedValue(PER_TWO, value);
                    supDisPerDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 13];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    discountDolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 15];
                    value = getFormattedValue(PER_TWO, value);
                    discountPerDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 17];
                    value = getFormattedValue(PER_TWO, value);
                    perExfacDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 19];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netSaleDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 21];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    demandSalesDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 23];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    invWithdrawDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 25];
                    value = getFormattedValue(PER_TWO, value);
                    perDemandDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 27];
                    value = getFormattedValue(PER_TWO, value);
                    perinvWithdrawDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 33];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    discountRPUDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 35];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    cogsDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 37];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netProfitDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    exFacSalesDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    conSaleDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 4];
                    value = getFormattedValue(NUM_ZERO, value);
                    unitVolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 6];
                    value = getFormattedValue(CUR_ZERO, value);
                    manDisDolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 8];
                    value = getFormattedValue(CUR_ZERO, value);
                    supDisDolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 10];
                    value = getFormattedValue(PER_TWO, value);
                    manDisPerDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 12];
                    value = getFormattedValue(PER_TWO, value);
                    supDisPerDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 14];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    discountDolDTO.addStringProperties(column, value);

                    value = StringUtils.EMPTY + obj[col + 16];
                    value = getFormattedValue(PER_TWO, value);
                    discountPerDTO.addStringProperties(column, value);

                    value = "" + obj[col + 18];
                    value = getFormattedValue(PER_TWO, value);
                    perExfacDTO.addStringProperties(column, value);

                    value = "" + obj[col + 20];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netSaleDTO.addStringProperties(column, value);

                    value = "" + obj[col + 22];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    demandSalesDTO.addStringProperties(column, value);

                    value = "" + obj[col + 24];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    invWithdrawDTO.addStringProperties(column, value);

                    value = "" + obj[col + 26];
                    value = getFormattedValue(PER_TWO, value);
                    perDemandDTO.addStringProperties(column, value);
                    value = "" + obj[col + 28];
                    value = getFormattedValue(PER_TWO, value);
                    perinvWithdrawDTO.addStringProperties(column, value);

                    value = "" + obj[col + 34];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    discountRPUDTO.addStringProperties(column, value);

                    value = "" + obj[col + 36];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    cogsDTO.addStringProperties(column, value);
                    value = "" + obj[col + 38];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    netProfitDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            exFacSalesDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            demandSalesDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            invWithdrawDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            perExfacDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            perDemandDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            perinvWithdrawDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            conSaleDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            unitVolDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            discountPerDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            discountDolDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            discountRPUDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            netSaleDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            cogsDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            netProfitDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
            manDisDolDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            supDisDolDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            supDisPerDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            manDisPerDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
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
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getNetSales1(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        String query = "";
        query = getNetSalesQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList = getCustomizedNetSales(list, projSelDTO);

        return projDTOList;
    }

    public List<ProjectionResultsDTO> getNetSales(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        List<ProjectionResultsDTO> projDTOList = getCustomizedNetSales(prMainQuery, projSelDTO);

        return projDTOList;
    }

    public List<ProjectionResultsDTO> getDiscountPer(ProjectionSelectionDTO projSelDTO) throws Exception {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        projSelDTO.setSales(Constant.RATE);
        String query = "";
        if (projSelDTO.isIsTotal()) {

            getCustomizedTotalDiscount(prMainQuery, projSelDTO);
            projDTOList.add(prjTotalDisDtoList.get(0));
        } else if (projSelDTO.isIsChildTotal()) {

            query = getChildTotalDiscountMainQuery(projSelDTO);
            childDiscountList = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);

            getCustomizedChildTotalDiscount(childDiscountList, projSelDTO);
            projDTOList.add(prjTotalDisChildDtoList.get(0));
            projDTOList.add(prjTotalDisChildDtoList.get(1));
        } else {

            programCodeList = getProgramCodeDiscount(projSelDTO);

            getCustomizedProgramCode(programCodeList, projSelDTO);
            if (projSelDTO.getGroup().equals(Mandated_Discount.getConstant())) {
                projDTOList.addAll(programCodeDtoList.get(2));
            } else if (projSelDTO.getGroup().equals(Supplemental_Discount.getConstant())) {
                projDTOList.addAll(programCodeDtoList.get(5));
            }
        }
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getDiscountDollar(ProjectionSelectionDTO projSelDTO) throws Exception {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        String query = "";
        if (projSelDTO.isIsTotal()) {
            if (totalDiscount.isEmpty()) {
            }
            getCustomizedTotalDiscount(prMainQuery, projSelDTO);
            projDTOList.add(prjTotalDisDtoList.get(2));
        } else if (projSelDTO.isIsChildTotal()) {

            query = getChildTotalDiscountMainQuery(projSelDTO);
            childDiscountList = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);

            getCustomizedChildTotalDiscount(childDiscountList, projSelDTO);
            projDTOList.add(prjTotalDisChildDtoList.get(4));
            projDTOList.add(prjTotalDisChildDtoList.get(5));
        } else {

            programCodeList = getProgramCodeDiscount(projSelDTO);

            getCustomizedProgramCode(programCodeList, projSelDTO);
            if (projSelDTO.getGroup().equals(Mandated_Discount.getConstant())) {
                projDTOList.addAll(programCodeDtoList.get(0));
            } else if (projSelDTO.getGroup().equals(Supplemental_Discount.getConstant())) {
                projDTOList.addAll(programCodeDtoList.get(3));
            }
        }
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getDiscountRPU(ProjectionSelectionDTO projSelDTO) throws Exception {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        projSelDTO.setSales("RPU");
        String query = "";
        if (projSelDTO.isIsTotal()) {
            if (totalDiscount.isEmpty()) {
            }
            getCustomizedTotalDiscount(prMainQuery, projSelDTO);
            projDTOList.add(prjTotalDisDtoList.get(1));
        } else if (projSelDTO.isIsChildTotal()) {

            query = getChildTotalDiscountMainQuery(projSelDTO);
            childDiscountList = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);

            getCustomizedChildTotalDiscount(childDiscountList, projSelDTO);
            projDTOList.add(prjTotalDisChildDtoList.get(2));
            projDTOList.add(prjTotalDisChildDtoList.get(3));
        } else {

            programCodeList = getProgramCodeDiscount(projSelDTO);

            getCustomizedProgramCode(programCodeList, projSelDTO);
            if (projSelDTO.getGroup().equals(Mandated_Discount.getConstant())) {
                projDTOList.addAll(programCodeDtoList.get(1));
            } else if (projSelDTO.getGroup().equals(Supplemental_Discount.getConstant())) {
                projDTOList.addAll(programCodeDtoList.get(4));
            }
        }
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getProjectionTotalDiscounts(ProjectionSelectionDTO projSelDTO, Object[] orderedArgs) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        if (prjTotalDisDolDtoList.isEmpty() || prjTotalDisPerDtoList.isEmpty()) {
            Object[] orderedArgs1 = {orderedArgs[0], orderedArgs[1], orderedArgs[2], orderedArgs[3], orderedArgs[4], orderedArgs[5], orderedArgs[6]};
            if (prcMProjResultsContract.isEmpty()) {
                prcMProjResultsContract = CommonLogic.callProcedure("PRC_M_PROJ_RESULTS_CONTRACT", orderedArgs1);
            }
            List<Object[]> gtsList = prcMProjResultsContract;
            getCustomizedDiscountsProjectionTotal(gtsList, projSelDTO);
            gtsList = null;
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
        String projections = projSelDTO.getActualsOrProjections();
        List<ProjectionResultsDTO> projDolDTOList = new ArrayList<ProjectionResultsDTO>();
        List<ProjectionResultsDTO> projPerDTOList = new ArrayList<ProjectionResultsDTO>();
        List<String> discountList = new ArrayList<String>(projSelDTO.getDiscountNameList());
        String oldDiscountName = "old";
        String newDiscountName = "oldDiscountName";
        if (list != null && !list.isEmpty()) {
            ProjectionResultsDTO projDolDTO = new ProjectionResultsDTO();
            ProjectionResultsDTO projPerDTO = new ProjectionResultsDTO();
            int col = 2;
            if (frequencyDivision != 1) {
                col = col + 1;
            }
            boolean add = false;
            List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
            columnList.remove(Constant.GROUP);
            for (int i = 0; i < list.size(); i++) {
                final Object[] obj = (Object[]) list.get(i);
                String column = "";
                int year = (obj[col - 1] == null) ? 0 : Integer.valueOf(String.valueOf(obj[col - 1]));
                int period = obj[1] == null ? 0 : Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                oldDiscountName = newDiscountName;
                newDiscountName = "" + obj[3];
                if (!oldDiscountName.equals(newDiscountName)) {
                    add = false;
                    if (discountList.contains(newDiscountName)) {
                        add = true;
                        discountList.remove(newDiscountName);
                        projDolDTO = new ProjectionResultsDTO();
                        projPerDTO = new ProjectionResultsDTO();
                        projDolDTO.setParent(0);
                        projPerDTO.setParent(0);
                        projDolDTO.setGroup(newDiscountName);
                        projDolDTO.setRelationshipLevelName(newDiscountName);
                        projPerDTO.setGroup(newDiscountName);
                        projPerDTO.setRelationshipLevelName(newDiscountName);
                        projDolDTOList.add(projDolDTO);
                        projPerDTOList.add(projPerDTO);
                        for (String columns : columnList) {
                            projDolDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                            projPerDTO.addStringProperties(columns, getFormattedValue(PER_THREE, Constant.NULL));
                        }
                        columnList = new ArrayList<String>(projSelDTO.getColumns());
                        columnList.remove(Constant.GROUP);
                    }

                }
                if (add) {
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        String value = "" + obj[col + 1];
                        value = getFormattedValue(CUR_ZERO, value);
                        projDolDTO.addStringProperties(column, value);
                        value = "" + obj[col + 3];
                        value = getFormattedValue(PER_THREE, value);
                        projPerDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        String value = "" + obj[col + 2];
                        value = getFormattedValue(CUR_ZERO, value);
                        projDolDTO.addStringProperties(column, value);
                        value = "" + obj[col + 4];
                        value = getFormattedValue(PER_THREE, value);
                        projPerDTO.addStringProperties(column, value);
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
            projDTO.setRelationshipLevelName(ob);
            ProjectionResultsDTO projDTO1 = new ProjectionResultsDTO();
            projDTO1.setParent(0);
            projDTO1.setProjectionTotal(1);
            projDTO1.setGroup(ob);
            projDTO1.setRelationshipLevelName(ob);
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                projDTO1.addStringProperties(columns, getFormattedValue(PER_THREE, Constant.NULL));
            }
            projDolDTOList.add(projDTO);
            projPerDTOList.add(projDTO1);
        }
        prjTotalDisDolDtoList = new ArrayList<ProjectionResultsDTO>(projDolDTOList);
        prjTotalDisPerDtoList = new ArrayList<ProjectionResultsDTO>(projPerDTOList);
    }

    public String getProjectionResultsSalesQuery(ProjectionSelectionDTO projSelDTO) {
        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());

        String selectClause = " select ";
        String whereClause = "";
        String groupBy = ",H.LEVEL_NAME , I.\"YEAR\"\n";
        String customQuery = "";
        String ccpWhereCond = CommonLogic.getCCPWhereConditionQuery("H", "E", "CCP");

        String parentNode = projSelDTO.getParentNode();
        whereClause = " and H.PARENT_NODE = '" + parentNode + "'\n";
        String levelValue = projSelDTO.getLevelValue();
        whereClause += " and H.RELATIONSHIP_LEVEL_VALUES = '" + levelValue + "'\n";
        groupBy = ", H.RELATIONSHIP_LEVEL_VALUES " + groupBy;
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
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);

        String customSql = "  ST_M_SALES_PROJECTION_MASTER B,\n"
                + " PROJECTION_DETAILS E , PROJECTION_MASTER F, \n" + viewtable + " G,\n"
                + " RELATIONSHIP_LEVEL_DEFINITION H,\n"
                + " \"PERIOD\" I, \n"
                + CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE)
                + "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n"
                + " and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + CommonLogic.getUserSessionQueryCondition((Integer.valueOf(projSelDTO.getUserId())), (Integer.valueOf(projSelDTO.getSessionId())), "B")
                + CommonLogic.getUserSessionQueryCondition((Integer.valueOf(projSelDTO.getUserId())), (Integer.valueOf(projSelDTO.getSessionId())), "A")
                + " and E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID \n"
                + " and F.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + " and G.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID \n"
                + " and G.RELATIONSHIP_LEVEL_SID = H.RELATIONSHIP_LEVEL_SID \n"
                + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID \n"
                + periodFilter + " and H.LEVEL_NO = " + projSelDTO.getLevelNo()
                + whereClause + "\n"
                + " group by H.LEVEL_NO " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                + " sum(A.ACTUAL_PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, \n"
                + " sum(A.ACTUAL_PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + " from ST_M_ACTUAL_SALES A,\n "
                + customSql;
        String futureQuery = selectClause + " 0 as SALES_ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, \n"
                + " sum(A.PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + " from ST_M_SALES_PROJECTION A,\n"
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as SALES_ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as SALES_PROJECTION_SALES,"
                + "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,\n Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS  ";

        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsSalesQueryForPR(ProjectionSelectionDTO projSelDTO) {

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

        String customSql = " JOIN @CCP CCP\n"
                + "      ON A.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "      JOIN \"PERIOD\" I\n"
                + "      ON A.PERIOD_SID = I.PERIOD_SID "
                + "where "
                + CommonLogic.getUserSessionQueryConditionForPR((Integer.valueOf(projSelDTO.getUserId())), (Integer.valueOf(projSelDTO.getSessionId())), "A")
                + periodFilter
                + whereClause + "\n"
                + " group by " + groupBy;

        String historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                + " sum(A.ACTUAL_PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, \n"
                + " sum(A.ACTUAL_PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + " from ST_M_ACTUAL_SALES A \n "
                + customSql;
        String futureQuery = selectClause + " 0 as SALES_ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, \n"
                + " sum(A.PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + " from ST_M_SALES_PROJECTION A \n"
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as SALES_ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as SALES_PROJECTION_SALES,"
                + "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,\n Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS  ";

        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;

        return customQuery;
    }

    public List<ProjectionResultsDTO> getProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO) throws Exception {
        int started = start;
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        String freq = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 1) {
            freq = "ANNUALLY";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            freq = "SEMI-ANNUALLY";
        } else if (projSelDTO.getFrequencyDivision() == 4) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == 12) {
            freq = "MONTHLY";
        }
        Object[] orderedArgs = {projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionId(), freq, "Projection Results", projSelDTO.getStartPeriod(), projSelDTO.getStartYear()};
        if (projSelDTO.isIsProjectionTotal()) {
            if (started == 0 && projSelDTO.isIsTotal()) {
                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                    ProjectionResultsDTO dto = new ProjectionResultsDTO();
                    dto.setGroup(Constant.PROJECTION_TOTAL);
                    dto.setRelationshipLevelName(Constant.PROJECTION_TOTAL);
                    dto.setParent(0);
                    projDTOList.add(dto);
                }
                neededRecord--;
                started++;
            }
            mayBeAdded++;

            if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {

                if (projectionTotalList.isEmpty()) {
                    projectionTotalList = getProjectionTotal(orderedArgs, projSelDTO);
                }
                ProjectionResultsDTO exFacSalesDTO = projectionTotalList.get(0);
                ProjectionResultsDTO demandSalesDTO = projectionTotalList.get(1);
                ProjectionResultsDTO invWithdrawDTO = projectionTotalList.get(2);
                ProjectionResultsDTO perExfacDTO = projectionTotalList.get(3);
                ProjectionResultsDTO perDemandDTO = projectionTotalList.get(4);
                ProjectionResultsDTO perinvWithdrawDTO = projectionTotalList.get(5);
                ProjectionResultsDTO contractSalesDto = projectionTotalList.get(6);
                ProjectionResultsDTO unitVolDto = projectionTotalList.get(7);
                ProjectionResultsDTO discountPerDto = projectionTotalList.get(8);
                if (discountPerDto != null) {
                    discountPerDto.setOnExpandTotalRow(0);
                }
                ProjectionResultsDTO discountDolDto = projectionTotalList.get(9);
                if (discountDolDto != null) {
                    discountDolDto.setOnExpandTotalRow(0);
                }
                ProjectionResultsDTO discountRPUDto = projectionTotalList.get(10);
                if (discountRPUDto != null) {
                    discountRPUDto.setOnExpandTotalRow(0);
                }
                ProjectionResultsDTO netSalesDto = projectionTotalList.get(11);
                ProjectionResultsDTO cogsDTO = projectionTotalList.get(12);
                ProjectionResultsDTO netProfitDTO = projectionTotalList.get(13);
                manDisDolDto = projectionTotalList.get(14);
                supDisDolDto = projectionTotalList.get(15);
                supDisPerDto = projectionTotalList.get(16);
                manDisPerDto = projectionTotalList.get(17);

                if (projSelDTO.isIsChildTotal() && projSelDTO.getGroup().equals(TOTAL_DISCOUNT_PERC.getConstant())) {

                    if (started == 0) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(manDisPerDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                    if (started == 1) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(supDisPerDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;

                } else if (projSelDTO.isIsChildTotal() && projSelDTO.getGroup().equals(TOTAL_DISCOUNT_AMOUNT.getConstant())) {

                    if (started == 0) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(manDisDolDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                    if (started == 1) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(supDisDolDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                } else if (projSelDTO.isIsChildTotal() && projSelDTO.getGroup().equals(TOTAL_RPU.getConstant())) {

                    if (started == 0) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(manDisDolDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                    if (started == 1) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(supDisDolDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                } else if (projSelDTO.getGroup().equals(Constant.MANDATED_DISCOUNT) && projSelDTO.getLevelIndicator().equals("D")) {
                    List<ProjectionResultsDTO> discountDolarDtoList = new ArrayList<ProjectionResultsDTO>();

                    projSelDTO.setTreeLevelNo(1);
                    programCodeList = getProgramCodeDiscount(projSelDTO);
                    getCustomizedProgramCode(programCodeList, projSelDTO);
                    if (programCodeDtoList.isEmpty()) {
                        List<String> discountList = new ArrayList<String>(projSelDTO.getDiscountProgramsList());
                        for (String discountList1 : discountList) {
                            ProjectionResultsDTO tempDto = new ProjectionResultsDTO();
                            tempDto.setGroup(discountList1);
                            tempDto.setRelationshipLevelName(discountList1);
                            discountDolarDtoList.add(tempDto);
                        }
                    } else {
                        discountDolarDtoList.addAll(programCodeDtoList.get(0));
                    }
                    for (int k = started; k < discountDolarDtoList.size() && neededRecord > 0; k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(discountDolarDtoList.get(k));
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();

                } else if (projSelDTO.getGroup().equals("Supplemental Discount") && projSelDTO.getLevelIndicator().equals("D")) {
                    List<ProjectionResultsDTO> discountDolarDtoList = new ArrayList<ProjectionResultsDTO>();

                    projSelDTO.setTreeLevelNo(1);
                    programCodeList = getProgramCodeDiscount(projSelDTO);
                    getCustomizedProgramCode(programCodeList, projSelDTO);

                    if (programCodeDtoList.isEmpty()) {
                        List<String> discountList = new ArrayList<String>(projSelDTO.getDiscountProgramsList());
                        for (String discountList1 : discountList) {
                            ProjectionResultsDTO tempDto = new ProjectionResultsDTO();
                            tempDto.setGroup(discountList1);
                            tempDto.setRelationshipLevelName(discountList1);
                            discountDolarDtoList.add(tempDto);
                        }
                    } else {
                        discountDolarDtoList.addAll(programCodeDtoList.get(2));
                    }
                    for (int k = started; k < discountDolarDtoList.size() && neededRecord > 0; k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(discountDolarDtoList.get(k));
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                } else if (projSelDTO.getGroup().equals(Constant.MANDATED_DISCOUNT) && projSelDTO.getLevelIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    List<ProjectionResultsDTO> discountPerDtoList = new ArrayList<ProjectionResultsDTO>();

                    projSelDTO.setTreeLevelNo(1);
                    programCodeList = getProgramCodeDiscount(projSelDTO);
                    getCustomizedProgramCode(programCodeList, projSelDTO);

                    if (programCodeDtoList.isEmpty()) {
                        List<String> discountList = new ArrayList<String>(projSelDTO.getDiscountProgramsList());
                        for (String discountList1 : discountList) {
                            ProjectionResultsDTO tempDto = new ProjectionResultsDTO();
                            tempDto.setRelationshipLevelName(discountList1);
                            discountPerDtoList.add(tempDto);
                        }
                    } else {
                        discountPerDtoList.addAll(programCodeDtoList.get(1));
                    }
                    for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(discountPerDtoList.get(k));
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                } else if (projSelDTO.getGroup().equals("Supplemental Discount") && projSelDTO.getLevelIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {

                    projSelDTO.setTreeLevelNo(1);
                    programCodeList = getProgramCodeDiscount(projSelDTO);
                    getCustomizedProgramCode(programCodeList, projSelDTO);
                    List<ProjectionResultsDTO> discountPerDtoList = new ArrayList<ProjectionResultsDTO>();

                    if (programCodeDtoList.isEmpty()) {
                        List<String> discountList = new ArrayList<String>(projSelDTO.getDiscountProgramsList());
                        for (String discountList1 : discountList) {
                            ProjectionResultsDTO tempDto = new ProjectionResultsDTO();
                            tempDto.setRelationshipLevelName(discountList1);
                            discountPerDtoList.add(tempDto);
                        }
                    } else {
                        discountPerDtoList.addAll(programCodeDtoList.get(3));
                    }

                    for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(discountPerDtoList.get(k));
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                }
                if (started == 1 && neededRecord > 0) {
                    if (exFacSalesDTO != null) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(exFacSalesDTO);
                        }
                        started++;
                        neededRecord--;
                    }
                }

                String salesUnits = projSelDTO.getSalesOrUnit();
                if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant()))) {
                    if (started == 2 && neededRecord > 0) {
                        if (demandSalesDTO != null) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                projDTOList.add(demandSalesDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        mayBeAdded++;
                    }
                    if (started == 3 && neededRecord > 0) {
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

                if (started == 4 || started == 2 && neededRecord > 0) {
                    if (perExfacDTO != null) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(perExfacDTO);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant()))) {
                    if (started == 5 && neededRecord > 0) {
                        if (perDemandDTO != null) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                projDTOList.add(perDemandDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        mayBeAdded++;
                    }
                    if (started == 6 && neededRecord > 0) {
                        if (perinvWithdrawDTO != null) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                projDTOList.add(perinvWithdrawDTO);
                            }
                            started++;
                            neededRecord--;
                        }
                        mayBeAdded++;
                    }
                    if (started == 7) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(contractSalesDto);
                        }
                        started++;
                        neededRecord--;
                        mayBeAdded++;
                    }
                }
                if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(UNITS.getConstant()))) {
                    if (started == 8 || started == 3) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(unitVolDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE)) {
                    if ((started == 9 || started == 4) || (started == 8 && !salesUnits.equals(UNITS.getConstant()))) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(discountPerDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0 && !projSelDTO.getGroup().contains("RPU")) {
                    if ((started == 10 || started == 5) || (started == 9 && !salesUnits.equals(UNITS.getConstant()))) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(discountRPUDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT)) {
                    if ((started == 11 || started == 6) || (started == 10 && !salesUnits.equals(UNITS.getConstant()))) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(discountDolDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0) {
                    if ((started == 12 || started == 7) || (started == 11 && !salesUnits.equals(UNITS.getConstant()))) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(netSalesDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0) {
                    if ((started == 13 || started == 8) || (started == 12 && !salesUnits.equals(UNITS.getConstant()))) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(cogsDTO);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0) {
                    if ((started == 14 || started == 9) || (started == 13 && !salesUnits.equals(UNITS.getConstant()))) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(netProfitDTO);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
            } else if (neededRecord > 0) {

                int mayBeAddedRecord = started - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                if (mayBeAddedRecord < projSelDTO.getPeriodList().size()) {
                    List<ProjectionResultsDTO> projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
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

        //Next To PRoj Total
        if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            String salesUnits = projSelDTO.getSalesOrUnit();
            if (neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsProjectionTotal()) {
                List<ProjectionResultsDTO> contractSalesAndUnits = getContractSalesAndUnits(projSelDTO);
                ProjectionResultsDTO contractSalesDto = contractSalesAndUnits.get(0);
                ProjectionResultsDTO unitVolDto = contractSalesAndUnits.get(1);
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
                contractSalesDto = null;

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
                unitVolDto = null;
            }
            if (neededRecord > 0 && !projSelDTO.isIsProjectionTotal()) {

                List<ProjectionResultsDTO> discountPerDtoList = getDiscountPer(projSelDTO);
                if (projSelDTO.isIsTotal()) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == 2) || started == 1) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.addAll(discountPerDtoList);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                } else {
                    for (int i = start; i < discountPerDtoList.size() && neededRecord > 0; i++) {
                        projDTOList.add(discountPerDtoList.get(i));
                    }
                    mayBeAdded += discountPerDtoList.size();
                }
                discountPerDtoList = null;

                List<ProjectionResultsDTO> discountRPUDtoList = getDiscountRPU(projSelDTO);
                if (projSelDTO.isIsTotal()) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == 3) || started == 2) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.addAll(discountRPUDtoList);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                } else {
                    for (int i = start; i < discountRPUDtoList.size() && neededRecord > 0; i++) {
                        projDTOList.add(discountRPUDtoList.get(i));
                    }
                    mayBeAdded += discountRPUDtoList.size();
                }
                discountRPUDtoList = null;

                List<ProjectionResultsDTO> discountDolarDtoList = getDiscountDollar(projSelDTO);
                if (projSelDTO.isIsTotal()) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == 4) || started == 3) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.addAll(discountDolarDtoList);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                } else {
                    for (int i = start; i < discountDolarDtoList.size() && neededRecord > 0; i++) {
                        projDTOList.add(discountDolarDtoList.get(i));
                    }
                    mayBeAdded += discountDolarDtoList.size();
                }
                discountDolarDtoList = null;

            }

            if (projSelDTO.isIsTotal() && !projSelDTO.isIsProjectionTotal()) {
                List<ProjectionResultsDTO> netDTO = getNetSales1(projSelDTO);
                ProjectionResultsDTO netSalesDto = netDTO.get(0);
                ProjectionResultsDTO cogsDTO = netDTO.get(1);
                ProjectionResultsDTO netProfitDto = netDTO.get(2);
                if (neededRecord > 0) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == 5) || started == 4) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(netSalesDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == 6) || started == 5) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(cogsDTO);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0) {
                    if ((salesUnits.equals(BOTH.getConstant()) && started == 7) || started == 6) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                            projDTOList.add(netProfitDto);
                        }
                        started++;
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
            }
        } else if (neededRecord > 0 && !projSelDTO.isIsProjectionTotal()) {
            List<ProjectionResultsDTO> projectionDtoList = getProjectionPivot(projSelDTO);
            int mayBeAddedRecord = start - mayBeAdded;
            if (mayBeAddedRecord < 0) {
                mayBeAddedRecord = 0;
            }
            for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; k++) {
                projDTOList.add(projectionDtoList.get(k));
                neededRecord--;
            }
            mayBeAdded += projectionDtoList.size();
            projectionDtoList = null;
        }
        if (neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) {
            int mayBeAddedRecord = start - mayBeAdded;
            if (mayBeAddedRecord < 0) {
                mayBeAddedRecord = 0;
            }
            projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
            List<ProjectionResultsDTO> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, projSelDTO);
            projDTOList.addAll(nextLevelValueList);
            nextLevelValueList = null;
        }

        return projDTOList;
    }

    public List<ProjectionResultsDTO> getProjectionResultsTotal(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        String discList = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
        String freq = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 1) {
            freq = "ANNUALLY";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            freq = "SEMI-ANNUALLY";
        } else if (projSelDTO.getFrequencyDivision() == 4) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == 12) {
            freq = "MONTHLY";
        }

        Object[] orderedArgs = {projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionId(), freq, "Projection Results", projSelDTO.getStartPeriod(), projSelDTO.getStartYear()};
        if (neededRecord > 0) {
            if (start < 1) {
                ProjectionResultsDTO dto = new ProjectionResultsDTO();
                dto.setGroup(Constant.PROJECTION_TOTAL);
                dto.setRelationshipLevelName(Constant.PROJECTION_TOTAL);
                dto.setParent(0);
                projDTOList.add(dto);
                neededRecord--;
            }
            mayBeAdded++;
            if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant()) && projSelDTO.isIsTotal()) {

                mayBeAdded += 2;
                List<ProjectionResultsDTO> projectionTotalList = getProjectionTotal(orderedArgs, projSelDTO);
                ProjectionResultsDTO gtsDto = projectionTotalList.get(0);
                ProjectionResultsDTO percentDto = projectionTotalList.get(1);
                ProjectionResultsDTO contractSalesDto = projectionTotalList.get(2);
                ProjectionResultsDTO unitVolDto = projectionTotalList.get(3);
                ProjectionResultsDTO discountPerDto = projectionTotalList.get(4);
                if (discountPerDto != null) {
                    discountPerDto.setOnExpandTotalRow(0);
                }
                ProjectionResultsDTO discountDolDto = projectionTotalList.get(5);
                if (discountDolDto != null) {
                    discountDolDto.setOnExpandTotalRow(0);
                }
                ProjectionResultsDTO netSalesDto = projectionTotalList.get(6);
                manDisDolDto = projectionTotalList.get(7);
                supDisDolDto = projectionTotalList.get(8);

                supDisPerDto = projectionTotalList.get(9);
                manDisPerDto = projectionTotalList.get(10);
                if (start < 2 && neededRecord > 0) {
                    if (gtsDto != null) {
                        projDTOList.add(gtsDto);
                        neededRecord--;
                    }
                }
                if (start < 3 && neededRecord > 0) {
                    if (percentDto != null) {
                        projDTOList.add(percentDto);
                        neededRecord--;
                    }
                }
                String salesUnits = projSelDTO.getSalesOrUnit();
                if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant()))) {
                    if (start < 4) {
                        projDTOList.add(contractSalesDto);
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(UNITS.getConstant()))) {
                    if ((salesUnits.equals(BOTH.getConstant()) && start < 5) || start < 4) {
                        projDTOList.add(unitVolDto);
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_AMOUNT)) {
                    if ((salesUnits.equals(BOTH.getConstant()) && start < 6) || start < 5) {
                        projDTOList.add(discountPerDto);
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0 && !projSelDTO.getGroup().contains(CommonUtils.VAR_DIS_RATE)) {
                    if ((salesUnits.equals(BOTH.getConstant()) && start < 7) || start < 6) {
                        projDTOList.add(discountDolDto);
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0) {
                    if ((salesUnits.equals(BOTH.getConstant()) && start < 8) || start < 7) {
                        projDTOList.add(netSalesDto);
                        neededRecord--;
                    }
                    mayBeAdded++;
                }
            } else if (neededRecord > 0) {
                List<ProjectionResultsDTO> projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);

                int mayBeAddedRecord = start - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; k++) {
                    projDTOList.add(projectionDtoList.get(k));
                    neededRecord--;
                }
                mayBeAdded += projectionDtoList.size();
            }
        }
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getConfiguredProjectionResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) throws Exception {
        List<ProjectionResultsDTO> resultList = new ArrayList<>();
        if ((!projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) || parentId instanceof ProjectionResultsDTO) {
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
                projSelDTO.setIsChildTotal(parentDto.getChildTotal() == 1);
                projSelDTO.setLevelIndicator(parentDto.getLevelIndicator());
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
            resultList = configureLevels(start, offset, projSelDTO);
        }
        return resultList;
    }

    public List<ProjectionResultsDTO> getConfiguredProjectionResultsTotal(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> resultList = new ArrayList<ProjectionResultsDTO>();
        projSelDTO.setIsProjectionTotal(true);
        projSelDTO.setIsTotal(true);
        if (!projSelDTO.isIsFilter()) {
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + " and " + PROJECTIONS.getConstant());
            }
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
            projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            resultList = getProjectionResultsTotal(start, offset, projSelDTO);
        }
        return resultList;
    }

    public List<ProjectionResultsDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        List<ProjectionResultsDTO> resultList = new ArrayList<>();
        if (neededRecord > 0) {
            String userGroup = StringUtils.EMPTY;
            if (projSelDTO.getTreeLevelNo() == projSelDTO.getTpLevel()) {
                userGroup = projSelDTO.getGroupFilter();
            }
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), start, offset, projSelDTO.getHierarchyIndicator(),
                    projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(),
                    projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), userGroup, projSelDTO.getUserId(),
                    projSelDTO.getSessionId(), projSelDTO.isFilterDdlb(), projSelDTO.getLevelName());
            for (int i = 0; i < levelList.size() && neededRecord > 0; i++) {
                Leveldto levelDto = levelList.get(i);

                ProjectionResultsDTO dto = new ProjectionResultsDTO();
                dto.setLevelNo(levelDto.getLevelNo());
                dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                dto.setParentNode(levelDto.getParentNode());
                dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                dto.setLevelValue(levelDto.getRelationshipLevelValue());
                dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                dto.setHierarchyNo(levelDto.getHierarchyNo());
                dto.setRelationshipLevelName(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                }
                dto.setOnExpandTotalRow(1);
                dto.setChildTotal(0);
                dto.setParent(1);
                resultList.add(dto);
                neededRecord--;
            }
        }
        return resultList;
    }

    public int getConfiguredProjectionResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelsCount, ProjectionSelectionDTO initialProjSelDTO) throws Exception {
        int count = 0;
        if ((!projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) || parentId instanceof ProjectionResultsDTO) {
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
                projSelDTO.setIsChildTotal(parentDto.getChildTotal() == 1);
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                projSelDTO.setHierarchyIndicator(initialProjSelDTO.getHierarchyIndicator());
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(initialProjSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(initialProjSelDTO.getCustomerLevelNo() - 1);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(initialProjSelDTO.getProductLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(initialProjSelDTO.getProductLevelNo() - 1);
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
        int count = 0;
        projSelDTO.setIsProjectionTotal(true);
        projSelDTO.setIsTotal(true);
        if (!projSelDTO.isIsFilter()) {
            projSelDTO.setYear(Constant.All);

            if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + " and " + PROJECTIONS.getConstant());
            }
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
            projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            count += getProjectionResultsTotalCount(projSelDTO);
        }
        return count;
    }

    public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelsCount) throws Exception {
        int count = 0;
        if (!projSelDTO.getGroup().startsWith(Constant.All)
                && !projSelDTO.getGroup().contains(Constant.Sales)
                && !projSelDTO.getGroup().contains(Constant.DISCOUNT)) {
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                String salesUnits = projSelDTO.getSalesOrUnit();
                if (projSelDTO.isIsTotal()) {
                    count = count + 7;
                    if (projSelDTO.isIsProjectionTotal()) {
                        count = count + 7;
                    }
                    if (salesUnits.equals(BOTH.getConstant())) {
                        count++;
                    }
                }
                if (!projSelDTO.isIsTotal()) {
                    if (projSelDTO.isIsChildTotal()) {
                        count = count + 2;
                    } else {
                        count = count + (projSelDTO.isIsProjectionTotal() ? projSelDTO.getDiscountProgramsList().size() : getProgramCodeCount(projSelDTO));
                    }
                }
            } else {
                if (!projSelDTO.isIsProjectionTotal()) {
                    count = count + projSelDTO.getPeriodList().size();
                }
            }
        }
        if (!projSelDTO.isFilterDdlb() && projSelDTO.isIsTotal() && isLevelsCount && !projSelDTO.isIsFilter()) {
            projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
            if (projSelDTO.isIsProjectionTotal()) {
                count = count + configureLevelsCount(projSelDTO);
            } else {
                int levelCount = 0;
                if (projSelDTO.isIsCustomHierarchy()) {
                    String hierarchyIndicatorQuery = "select HIERARCHY_INDICATOR from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID=" + projSelDTO.getCustomId() + " and LEVEL_NO=" + projSelDTO.getTreeLevelNo();
                    List<Object> list = (List<Object>) executeSelectQuery(hierarchyIndicatorQuery, null, null);
                    if (list != null && !list.isEmpty()) {
                        levelCount = CommonLogic.getLevelListCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(),
                                projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(),
                                projSelDTO.getCustomId(), StringUtils.EMPTY, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), projSelDTO.isFilterDdlb(), projSelDTO.getLevelName());
                    }
                } else {
                    levelCount = CommonLogic.getLevelListCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(),
                            projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(),
                            projSelDTO.getCustomId(), StringUtils.EMPTY, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), projSelDTO.isFilterDdlb(), projSelDTO.getLevelName());
                }
                count = count + levelCount;
                projSelDTO.setLevelCount(levelCount);
            }
        }
        LOGGER.info("Ends getProjectionResultsCount " + count);
        return count;
    }

    public int getProjectionResultsTotalCount(ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            String salesUnits = projSelDTO.getSalesOrUnit();
            count = count + 7;
            if (salesUnits.equals(BOTH.getConstant())) {
                count++;
            }

        } else {
            count = count + 1 + projSelDTO.getPeriodList().size();
        }
        return count;
    }

    public int configureLevelsCount(ProjectionSelectionDTO projSelDTO) {
        String userGroup = StringUtils.EMPTY;
        if (projSelDTO.getTreeLevelNo() == projSelDTO.getTpLevel()) {
            userGroup = projSelDTO.getGroupFilter();
        }
        int count = 0;
        if (!projSelDTO.isIsFilter()) {
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {

            } else {
                count = count + 1 + projSelDTO.getPeriodList().size();
            }
        }
        int levelCount = CommonLogic.getLevelListCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(),
                projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(),
                projSelDTO.getCustomId(), userGroup, Integer.valueOf(projSelDTO.getUserId()), Integer.valueOf(projSelDTO.getSessionId()), projSelDTO.isFilterDdlb(), projSelDTO.getLevelName());
        count = count + levelCount;
        projSelDTO.setLevelCount(levelCount);
        LOGGER.info("Ends configureLevelsCount " + count);
        return count;
    }

    public List<Object[]> getProgramCodeDiscount(ProjectionSelectionDTO projSelDTO) {

        Map<Integer, String> freq = new HashMap<Integer, String>();
        freq.put(1, "\"YEAR\"");
        freq.put(2, "SEMI_ANNUAL");
        freq.put(4, "QUARTER");
        freq.put(12, "\"MONTH\"");
        if (projSelDTO.getTreeLevelNo() == 0) { //need to change
            projSelDTO.setTreeLevelNo(5);
        }
        programCodeList = (List<Object[]>) CommonLogic.executeSelectQuery(getProgramCodeQuery(projSelDTO.getProjectionId(), Integer.valueOf(projSelDTO.getUserId()),
                Integer.valueOf(projSelDTO.getSessionId()), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(),
                freq.get(projSelDTO.getFrequencyDivision()), projSelDTO.getView(), projSelDTO), null, null);
        return programCodeList;
    }

    public void getCustomizedProgramCode(List<Object[]> list, ProjectionSelectionDTO projSelDTO) throws Exception {
        LOGGER.info("Enters getCustomizedProgramCode with size = " + list.size());

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDolManDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projRPUManDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projPerManDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projDolSupDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projRPUSupDTOList = new ArrayList<>();
        List<ProjectionResultsDTO> projPerSupDTOList = new ArrayList<>();
        List<String> discountList = new ArrayList<>(projSelDTO.getDiscountProgramsList());

        if (list != null && !list.isEmpty() && !discountList.isEmpty()) {
            int col = 3;
            boolean add = false;
            List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
            columnList.remove(Constant.GROUP);

            discountList = projSelDTO.isIsProjectionTotal() ? discountList : getProgramCodeList(projSelDTO);
            for (int z = 0; z < discountList.size(); z++) {
                ProjectionResultsDTO projDolManDTO = new ProjectionResultsDTO();
                ProjectionResultsDTO projRPUManDTO = new ProjectionResultsDTO();
                ProjectionResultsDTO projPerManDTO = new ProjectionResultsDTO();
                ProjectionResultsDTO projDolSupDTO = new ProjectionResultsDTO();
                ProjectionResultsDTO projRPUSupDTO = new ProjectionResultsDTO();
                ProjectionResultsDTO projPerSupDTO = new ProjectionResultsDTO();
                for (int i = 0; i < list.size(); i++) {
                    final Object[] obj = (Object[]) list.get(i);
                    String column = "";
                    int year = obj[col - 1] == null ? 0 : Integer.valueOf(String.valueOf(obj[col - 1]));
                    int period = obj[15] == null ? 0 : Integer.valueOf(String.valueOf(obj[15]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(0);
                    if (obj[0] != null && discountList.get(z).equals(obj[0])) {
                        projDolManDTO.setParent(0);
                        projRPUManDTO.setParent(0);
                        projPerManDTO.setParent(0);
                        projDolSupDTO.setParent(0);
                        projRPUSupDTO.setParent(0);
                        projPerSupDTO.setParent(0);

                        projDolManDTO.setGroup(obj[0].toString());
                        projRPUManDTO.setGroup(obj[0].toString());
                        projPerManDTO.setGroup(obj[0].toString());
                        projDolSupDTO.setGroup(obj[0].toString());
                        projRPUSupDTO.setGroup(obj[0].toString());
                        projPerSupDTO.setGroup(obj[0].toString());

                        projDolManDTO.setLevelValue(obj[0].toString());
                        projRPUManDTO.setLevelValue(obj[0].toString());
                        projPerManDTO.setLevelValue(obj[0].toString());
                        projDolSupDTO.setLevelValue(obj[0].toString());
                        projRPUSupDTO.setLevelValue(obj[0].toString());
                        projPerSupDTO.setLevelValue(obj[0].toString());

                        projDolManDTO.setRelationshipLevelName(obj[0].toString());
                        projRPUManDTO.setRelationshipLevelName(obj[0].toString());
                        projPerManDTO.setRelationshipLevelName(obj[0].toString());
                        projDolSupDTO.setRelationshipLevelName(obj[0].toString());
                        projRPUSupDTO.setRelationshipLevelName(obj[0].toString());
                        projPerSupDTO.setRelationshipLevelName(obj[0].toString());

                        column = commonColumn + ACTUALS.getConstant();

                        if (projSelDTO.hasColumn(column)) {
                            String value = "" + obj[3];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDolManDTO.addStringProperties(column, value);
                            value = "" + obj[4];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projRPUManDTO.addStringProperties(column, value);
                            value = "" + obj[5];
                            value = getFormattedValue(PER_TWO, value);
                            projPerManDTO.addStringProperties(column, value);
                            value = "" + obj[6];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDolSupDTO.addStringProperties(column, value);
                            value = "" + obj[7];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projRPUSupDTO.addStringProperties(column, value);
                            value = "" + obj[8];
                            value = getFormattedValue(PER_THREE, value);
                            projPerSupDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            String value = "" + obj[9];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDolManDTO.addStringProperties(column, value);
                            value = "" + obj[10];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projRPUManDTO.addStringProperties(column, value);
                            value = "" + obj[11];
                            value = getFormattedValue(PER_TWO, value);
                            projPerManDTO.addStringProperties(column, value);
                            value = "" + obj[12];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDolSupDTO.addStringProperties(column, value);
                            value = "" + obj[13];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projRPUSupDTO.addStringProperties(column, value);
                            value = "" + obj[14];
                            value = getFormattedValue(PER_THREE, value);
                            projPerSupDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                    }
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
            projDolManDTOList = null;
            projRPUManDTOList = null;
            projPerManDTOList = null;
            projDolSupDTOList = null;
            projRPUSupDTOList = null;
            projPerSupDTOList = null;
        }
        LOGGER.info("Ends getCustomizedProgramCode");
    }

    public List<ProjectionResultsDTO> getProjectionPivot(ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        String query = "DECLARE @FROM_DATE DATE\n"
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
        List<Object> gtsList = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);

        List<Object[]> pcList = addProgramCodeDiscounts(projSelDTO);
        
        projDTOList = getCustomizedProjectionPivot(gtsList, pcList, projSelDTO);
        gtsList = null;
        pcList = null;

        return projDTOList;
    }

    private List<Object[]> addProgramCodeDiscounts(ProjectionSelectionDTO projSelDTO) {
        List<Object[]> list = new ArrayList<Object[]>();
        Map<Integer, String> freq = new HashMap<Integer, String>();
        freq.put(1, "\"YEAR\"");
        freq.put(2, "SEMI_ANNUAL");
        freq.put(4, "QUARTER");
        freq.put(12, "\"MONTH\"");
        list = (List<Object[]>) CommonLogic.executeSelectQuery(getProgramCodeQuery(projSelDTO.getProjectionId(), Integer.valueOf(projSelDTO.getUserId()),
                Integer.valueOf(projSelDTO.getSessionId()), projSelDTO.getLevelNo(), projSelDTO.getHierarchyNo(),
                freq.get(projSelDTO.getFrequencyDivision()), projSelDTO.getView(), projSelDTO), null, null);
        return list;
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionPivot(List<Object> list, List<Object[]> pcList, ProjectionSelectionDTO projSelDTO) {

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        List<String> discountList = new ArrayList<String>(projSelDTO.getDiscountProgramsList());
        int discountIndex = 0;
        int col = 2;
        int dcol = 2;
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(list);
        }
        for (Object rows : list) {
            final Object[] row = (Object[]) rows;
            String column = StringUtils.EMPTY;
            int year = (row[0] == null) ? 0 : Integer.valueOf(String.valueOf(row[0]));
            int period = projSelDTO.getFrequencyDivision() == 1 ? 0 : (row[1] == null) ? 0 : Integer.valueOf(String.valueOf(row[1]));
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
                projDTO.setRelationshipLevelName(commonHeader);
                String value = Constant.NULL;
                commonColumn = "conSalesWac";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[3];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                commonColumn = "unitVol";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[4];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[5];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.totDisPer;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[6];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[7];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totRPU";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[8];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[9];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.totDisDol;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[10];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[11];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisDolMandatedDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[14];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[20];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                commonColumn = "totRPUMandatedDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[15];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[21];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisPerMandatedDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[16];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[22];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisDolSupplementalDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[17];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[23];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totRPUSupplementalDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[18];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[24];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisPerSupplementalDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[19];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[25];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netSales";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[12];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[13];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "cogs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[26];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[27];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netProfit";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[28];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[29];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                boolean start = false;
                int pcCol = 0;
                for (int i = 0; i < pcList.size(); i++) {
                    Object[] discountRow = (Object[]) pcList.get(i);
                    int dyear = Integer.valueOf(String.valueOf(discountRow[2]));
                    int dperiod = Integer.valueOf(String.valueOf(discountRow[15]));
                    List<String> dcommon = getCommonColumnHeader(frequencyDivision, dyear, dperiod);
                    String dcommonColumn = dcommon.get(0);
                    if (pcommonColumn.contains(dcommonColumn)) {
                        start = true;
                        commonColumn = discountRow[0].toString().replace(" ", StringUtils.EMPTY);
                        column = Constant.totDisPer + Mandated_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();

                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[5];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisPer + Mandated_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[11];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisPer + Supplemental_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[8];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisPer + Supplemental_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[14];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = "totRPU" + Mandated_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[4];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = "totRPU" + Mandated_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[10];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = "totRPU" + Supplemental_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[7];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = "totRPU" + Supplemental_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[13];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }

                        column = Constant.totDisDol + Mandated_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[3];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisDol + Mandated_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[9];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisDol + Supplemental_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[6];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisDol + Supplemental_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[12];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                    }

                }

                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                projDTOList.add(projDTO);
            }

        }
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        for (String ob : periodList) {
            ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setGroup(projSelDTO.getPeriodListMap().get(ob));
            projDTO.setRelationshipLevelName(projSelDTO.getPeriodListMap().get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            }
            projDTOList.add(projDTO);
        }

        return projDTOList;
    }

    public List<ProjectionResultsDTO> getProjectionPivotTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        List<Object[]> gtsList = CommonLogic.callProcedure("PRC_M_PROJECTION_RESULTS", orderedArgs);
        Object[] orderedArgs1 = {orderedArgs[0], orderedArgs[1], orderedArgs[2], orderedArgs[3], orderedArgs[4], orderedArgs[5], orderedArgs[6]};
        List<Object[]> discountList = CommonLogic.callProcedure("PRC_M_PROJ_RESULTS_CONTRACT", orderedArgs1);
        projDTOList = getCustomizedProjectionPivotTotal(gtsList, discountList, projSelDTO);
        gtsList = null;
        discountList = null;
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionPivotTotal(List<Object[]> list, List<Object[]> discountList, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        int col = 3;
        int dcol = 1;
        if (frequencyDivision != 1) {
            col = col + 1;
            dcol = dcol + 1;
        }
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(list);
        }
        for (Object[] row : list) {

            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[col]));
            int period = Integer.valueOf(String.valueOf(frequencyDivision != 1 ? row[col - 1] : 0));
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
                projDTO.setRelationshipLevelName(commonHeader);
                String value = Constant.NULL;
                commonColumn = "efs";
                int discountIndex = 0;
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
                commonColumn = "dms";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 21];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 22];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "iws";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 23];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 24];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perOfExfac";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 17];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 18];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perOfDemand";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 25];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 26];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perOfInvwithdraw";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 27];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 28];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "conSalesWac";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 1];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 2];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "unitVol";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 3];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 4];
                    value = getFormattedValue(NUM_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.totDisPer;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 15];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 16];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totRPU";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 33];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 34];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = Constant.totDisDol;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 13];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 14];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisDolMandatedDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 5];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 6];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisDolSupplementalDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 7];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 8];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisPerMandatedDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 9];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 10];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totDisPerSupplementalDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 11];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 12];
                    value = getFormattedValue(PER_TWO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totRPUMandatedDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 29];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 30];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "totRPUSupplementalDiscount";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 31];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 32];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netSales";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 19];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 20];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "cogs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 35];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 36];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netProfit";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 37];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 38];
                    value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }

                for (int i = 0; i < discountList.size(); i++) {

                    Object[] discountRow = discountList.get(i);
                    int dyear = Integer.valueOf(String.valueOf(discountRow[dcol]));
                    int dperiod = Integer.valueOf(String.valueOf(frequencyDivision != 1 ? discountRow[dcol - 1] : 0));
                    List<String> dcommon = getCommonColumnHeader(frequencyDivision, dyear, dperiod);
                    String dcommonColumn = dcommon.get(0);
                    if (pcommonColumn.contains(dcommonColumn)) {                       
                       int programCodeIndex = projSelDTO.getFrequency().equals(ANNUALLY.getConstant()) ? 4 : 5;
                        commonColumn = discountRow[programCodeIndex].toString().replace(" ", StringUtils.EMPTY);
                        column = Constant.totDisPer + Mandated_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 12];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisPer + Mandated_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 13];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisPer + Supplemental_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 14];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisPer + Supplemental_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 15];
                            value = getFormattedValue(PER_TWO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = "totRPU" + Mandated_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 16];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = "totRPU" + Mandated_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 17];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = "totRPU" + Supplemental_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 18];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = "totRPU" + Supplemental_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 19];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisDol + Mandated_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 8];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisDol + Mandated_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 9];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisDol + Supplemental_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 10];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = Constant.totDisDol + Supplemental_Discount.getConstant().replace(" ", StringUtils.EMPTY).trim() + commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 11];
                            value = getFormatTwoDecimalValue(CUR_TWO, value, CURRENCY);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                    }
                }

                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                projDTOList.add(projDTO);
            }

        }
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        for (String ob : periodList) {
            ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setGroup(projSelDTO.getPeriodListMap().get(ob));
            projDTO.setRelationshipLevelName(projSelDTO.getPeriodListMap().get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            }
            projDTOList.add(projDTO);
        }

        return projDTOList;
    }

    public String getFormatValue(int numberOfDecimal, String value, String appendChar) {
        if (value.contains(Constant.NULL)) {
            value = "...";
        } else {
            if (CURRENCY.equals(appendChar)) {
                value = appendChar.concat(String.valueOf(new BigDecimal(String.valueOf(value)).setScale(numberOfDecimal, BigDecimal.ROUND_DOWN)));
            } else {
                value = String.valueOf(new BigDecimal(String.valueOf(value)).setScale(numberOfDecimal, BigDecimal.ROUND_DOWN)).concat(appendChar);
            }
        }
        return value;
    }

    public String getFormatTwoDecimalValue(DecimalFormat FORMAT, String value, String appendChar) {
        if (value.contains(Constant.NULL)) {
            value = "...";
        } else {
            if (CURRENCY.equals(appendChar)) {
                value = appendChar.concat(FORMAT.format(Double.valueOf(value)));
            } else {
                value = FORMAT.format(Double.valueOf(value)).concat(appendChar);
            }
        }
        return value;
    }

    public static ComboBox loadCustomerDdlb(ProjectionSelectionDTO projSelDTO, ComboBox contractType) {
        SalesProjectionDAO dao = new SalesProjectionDAOImpl();
        StringBuilder customSql = new StringBuilder(StringUtils.EMPTY);
        String[] tempTableName = new String[2];
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            tempTableName[0] = "CUST_RELATIONSHIP_BUILDER_SID";
            tempTableName[1] = "CUSTOMER_HIERARCHY_LEVEL";
        } else {
            tempTableName[0] = "PROD_RELATIONSHIP_BUILDER_SID";
            tempTableName[1] = "PRODUCT_HIERARCHY_LEVEL";
        }
        customSql.append("select RLD.RELATIONSHIP_LEVEL_VALUES,RLD.HIERARCHY_NO from PROJECTION_MASTER PM,RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "WHERE RLD.RELATIONSHIP_BUILDER_SID = PM." + tempTableName[0] + " \n"
                + "AND RLD.LEVEL_NAME in (" + projSelDTO.getLevelName() + ") \n"
                + "AND RLD.LEVEL_NO >= PM." + tempTableName[1] + " \n"
                + "AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId());
        try {
            List list = (List) dao.executeSelectQuery(customSql.toString());
            contractType.addItem(0);
            contractType.setItemCaption(0, SELECT_ONE.getConstant());
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    contractType.addItem(obj[1] == null ? StringUtils.EMPTY : obj[1].toString());
                    contractType.setItemCaption(obj[1] == null ? StringUtils.EMPTY : obj[1].toString(), obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return contractType;
    }

    public String getChildTotalDiscountQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = "select ";
        selectClause += "p.\"YEAR\" as YEARS, ";
        String groupBy = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "p.QUARTER as PERIODS, \n";
            groupBy += " p.QUARTER,";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "p.SEMI_ANNUAL as PERIODS, \n";
            groupBy += " p.SEMI_ANNUAL,";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
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

        String query = selectClause + " FROM   @CCP CCP "
                + " JOIN   ST_M_SALES_PROJECTION_MASTER  m_mas ON CCP.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                + " JOIN   ";
        if (projSelDTO.isFuture()) {
            query += "ST_M_SALES_PROJECTION";
        } else {
            query += "ST_M_ACTUAL_SALES";
        }
        query += " m_ac ON m_mas.PROJECTION_DETAILS_SID = m_ac.PROJECTION_DETAILS_SID\n"
                + "                             AND m_ac.user_id = m_mas.user_id\n"
                + "                             AND m_ac.session_id = m_mas.session_id\n"
                + "JOIN   ";
        if (projSelDTO.isFuture()) {
            query += "M_DISCOUNT_PROJECTION";
        } else {
            query += "M_ACTUAL_DISCOUNT";
        }
        query += " MAD ON MAD.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                            AND MAD.user_id = m_mas.user_id\n"
                + "                            AND MAD.session_id = m_mas.session_id\n"
                + " LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_MASTER SPM ON SPM.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "                                        AND SPM.user_id = m_mas.user_id\n"
                + "                                        AND SPM.session_id = m_mas.session_id\n"
                + " LEFT JOIN   ";
        if (projSelDTO.isFuture()) {
            query += " ST_M_SUPPLEMENTAL_DISC_PROJ";
        } else {
            query += " ST_M_SUPPLEMENTAL_DISC_ACTUALS";
        }
        query += " SPMA ON SPMA.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "                                                                        AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                                          AND SPMA.user_id = m_mas.user_id\n"
                + "                                          AND SPMA.session_id = m_mas.session_id\n"
                + " JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                + " WHERE  "
                + "   m_mas.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "   AND m_mas.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + " GROUP  BY ";
        if (projSelDTO.getFrequencyDivision() != 1) {
            query += "" + groupBy + " \n";
        }

        query += "                          CCP.RELATIONSHIP_LEVEL_SID,\n"
                + "                          CCP.HIERARCHY_NO,\n"
                + "                          p.\"YEAR\"";
        return query;
    }

    public String getChildTotalDiscountMainQuery(ProjectionSelectionDTO projSelDTO) {
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "\n "
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
        String customQuery = CommonLogic.getCCPQueryForPR(projSelDTO) + " \n" + finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;
        return customQuery;
    }

    public String getTotalDiscountMainQuery(ProjectionSelectionDTO projSelDTO) {
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "\n "
                + "(Isnull(HISTORY.MAD_ACTUAL_SALES,0)+Isnull(HISTORY.SUP_ACTUAL_SALES,0)) as ACTUAL_SALES,\n"
                + "((Isnull(HISTORY.MAD_ACTUAL_RATE,0)+Isnull(HISTORY.SUP_ACTUAL_RATE,0))/2) as ACTUAL_RATE,\n"
                + "(ISNULL(HISTORY.MAD_ACTUAL_RPU, 0) + ISNULL(HISTORY.SUP_ACTUAL_RPU, 0) ) AS ACTUAL_RPU,\n"
                + "(Isnull(FUTURE.MAD_PROJECTION_SALES,0)+Isnull(FUTURE.SUP_PROJECTION_SALES,0)) as PROJECTION_SALES,\n"
                + "((Isnull(FUTURE.MAD_PROJECTION_RATE,0)+Isnull(FUTURE.SUP_PROJECTION_RATE,0))/2) as PROJECTION_RATE,\n"
                + "(Isnull(FUTURE.MAD_PROJECTION_RPU,0)+Isnull(FUTURE.SUP_PROJECTION_RPU,0)) as PROJECTION_RPU";
        projSelDTO.setFuture(false);
        String historyQuery = getChildTotalDiscountQuery(projSelDTO);
        projSelDTO.setFuture(true);
        String futureQuery = getChildTotalDiscountQuery(projSelDTO);

        String customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsNetSalesQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "     DROP TABLE #TEMP_CCP;\n"
                + "\n"
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

        selectClause += " select ";
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += " ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-Isnull(TODIS.ACTUAL_SALES, 0)),\n"
                + " PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-Isnull(TODIS.PROJECTION_SALES, 0)),COGS_ACTUAL = (ISNULL(SALE.ACTUAL_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n"
                + "       , COGS_PROJECTED = (ISNULL(SALE.PROJECTION_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))\n"
                + "       , NET_PROFIT_ACTUAL = ((ISNULL(SALE.SALES_ACTUAL_SALES, 0) * (ISNULL(SALE.ACTUAL_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0))))\n"
                + "       , NET_PROFIT_PROJECTED = ((ISNULL(SALE.SALES_PROJECTION_SALES, 0) * (ISNULL(SALE.PROJECTION_UNITS, 0) * ISNULL(COGS.ITEM_PRICE, 0)))) \n";
        String totalDiscountQuery = getTotalDiscountMainQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQueryForPR(projSelDTO);
        String cogsQuery = getProjectionResultsCOGSQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        customQuery += " FULL OUTER JOIN (" + cogsQuery + "\n";
        customQuery += " ORDER BY YEARS\n"
                + "       , PERIODS";
        return customQuery;
    }

    public String getProjectionResultsNetSalesQueryForPR(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = "";
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", "on");
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        selectClause += " NET_ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-Isnull(TODIS.ACTUAL_SALES, 0)),\n"
                + " NET_PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-Isnull(TODIS.PROJECTION_SALES, 0)), \n"
                + "        Isnull(TODIS.ACTUAL_SALES, 0)    AS total_actual_disc_dollar,\n"
                + "       Isnull(TODIS.PROJECTION_SALES, 0)  AS total_proj_disc_dollar,\n"
                + "       ( Isnull(TODIS.ACTUAL_SALES, 0) / ( NULLIF(SALE.SALES_ACTUAL_SALES, 0) ) ) * 100  AS TOTAL_ACTUAL_DISC_RATE,\n"
                + "       ( Isnull(TODIS.PROJECTION_SALES, 0) / ( NULLIF(SALE.SALES_PROJECTION_SALES, 0) ) ) * 100 AS TOTAL_PROJ_DISC_RATE, \n"
                + "        (Isnull(TODIS.ACTUAL_RPU, 0) / (NULLIF(SALE.ACTUAL_UNITS, 0))) * 100 AS TOTAL_ACTUAL_RPU, \n"
                + "        (Isnull(TODIS.PROJECTION_RPU, 0) / (NULLIF(SALE.PROJECTION_UNITS, 0))) * 100 AS TOTAL_PROJ_RPU, \n"
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
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        return customQuery;
    }

    public String getProjectionResultsCOGSQuery(ProjectionSelectionDTO projSelDTO) {
        String groupBy = StringUtils.EMPTY;
        String value = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 4) {
            value = ", I.QUARTER";
            groupBy = "AND SALE.PERIODS = COGS.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            value = ", I.SEMI_ANNUAL";
            groupBy = "AND SALE.PERIODS = COGS.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            value = StringUtils.EMPTY;
            groupBy = StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
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

    private String getProgramCodeQuery(int projectionId, int userID, int sessionID, int levelNo, String hierarchyNo, String frequency, String View, ProjectionSelectionDTO projSelDTO) {
        String hierarchy = StringUtils.EMPTY;
        String columnName = StringUtils.EMPTY;
        String customerLevelNo = StringUtils.EMPTY;
        String prodLevelNo = StringUtils.EMPTY;
        String customerHierNo = StringUtils.isBlank(String.valueOf(projSelDTO.getCustomerHierarchyNo())) ? "" : String.valueOf(projSelDTO.getCustomerHierarchyNo());
        String prodHierNo = StringUtils.isBlank(String.valueOf(projSelDTO.getProductHierarchyNo())) ? "" : String.valueOf(projSelDTO.getProductHierarchyNo());
        List list;
        int customID = projSelDTO.getCustomId();
        int customLevelNo = projSelDTO.getCustomLevelNo();
        String hierarchyIndicator = projSelDTO.getHierarchyIndicator().trim();
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(hierarchyIndicator)) {
            customerLevelNo = StringUtils.isBlank(String.valueOf(customLevelNo)) ? Constant.PERCENT : String.valueOf(customLevelNo);
            prodLevelNo = Constant.PERCENT;
        } else {
            customerLevelNo = Constant.PERCENT;
            prodLevelNo = StringUtils.isBlank(String.valueOf(customLevelNo)) ? Constant.PERCENT : String.valueOf(customLevelNo);
        }

        if (Constant.CUSTOMER_SMALL.equalsIgnoreCase(View)) {
            hierarchy = "PROJECTION_CUST_HIERARCHY";
        } else {
            hierarchy = "PROJECTION_PROD_HIERARCHY";
        }
        String hierSQL = "SELECT FIELD_NAME FROM dbo.HIERARCHY_LEVEL_DEFINITION WHERE HIERARCHY_DEFINITION_SID=" + projSelDTO.getCustHierarchySid() + " and LEVEL_NAME='Contract'";
        list = (List<Object>) CommonLogic.executeSelectQuery(hierSQL, null, null);

        if (list != null && !list.isEmpty()) {
            final Object obj = list.get(0);
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
                + "       ( SUM(ISNULL(SPMA.ACTUAL_SALES, 0)) / NULLIF(SUM(M_AC.ACTUAL_UNITS), 0) ) * 100 AS SUP_ACT_RPU,"
                + "       0                            As ActualProj\n";
        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL += "         , p." + frequency + " as PERIODS \n";
        } else {
            customSQL += ",0 as PERIODS \n";
        }
        customSQL += "FROM  @CCP CCP ";
        customSQL += "JOIN   ST_M_SALES_PROJECTION_MASTER m_mas ON CCP.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                + "JOIN   ST_M_ACTUAL_SALES m_ac ON m_mas.PROJECTION_DETAILS_SID = m_ac.PROJECTION_DETAILS_SID\n"
                + "                             AND m_ac.user_id = m_mas.user_id\n"
                + "                             AND m_ac.session_id = m_mas.session_id\n"
                + "JOIN   M_ACTUAL_DISCOUNT MAD ON MAD.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                            AND MAD.user_id = m_mas.user_id\n"
                + "                            AND MAD.session_id = m_mas.session_id\n"
                + " JOIN ccp_details ccd ON ccd.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                + " JOIN contract_master cm ON cm.contract_master_sid = ccd.contract_master_sid \n"
                + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_MASTER SPM ON SPM.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "\n"
                + "                                        AND SPM.user_id = m_mas.user_id\n"
                + "                                        AND SPM.session_id = m_mas.session_id\n"
                + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_ACTUALS SPMA ON SPMA.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "										   AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                                          AND SPMA.user_id = m_mas.user_id\n"
                + "                                          AND SPMA.session_id = m_mas.session_id\n"
                + "JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                + "WHERE  "
                + "   m_mas.USER_ID = " + userID + "\n"
                + "   AND m_mas.SESSION_ID = " + sessionID + "\n"
                + "GROUP  BY cm." + columnName + ",\n";

        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL += "          p." + frequency + ",\n";
        }
        customSQL += "          CCP.RELATIONSHIP_LEVEL_SID,\n"
                + "CCP.HIERARCHY_NO,\n"
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
                + "       Sum(Isnull(SPMA.PROJECTION_RPU,0))   AS  SUP_PROJ_RPU, \n"
                + "       1                            As ActualProj\n";
        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL1 += "         , p." + frequency + " as PERIODS \n";
        } else {
            customSQL1 += ",0 as PERIODS \n";
        }
        customSQL1 += "FROM  @CCP CCP ";

        customSQL1 += "JOIN   ST_M_SALES_PROJECTION_MASTER m_mas ON CCP.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                + "JOIN   ST_M_SALES_PROJECTION m_ac ON m_mas.PROJECTION_DETAILS_SID = m_ac.PROJECTION_DETAILS_SID\n"
                + "                             AND m_ac.user_id = m_mas.user_id\n"
                + "                             AND m_ac.session_id = m_mas.session_id\n"
                + "JOIN   M_DISCOUNT_PROJECTION MAD ON MAD.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                            AND MAD.user_id = m_mas.user_id\n"
                + "                            AND MAD.session_id = m_mas.session_id\n"
                + " JOIN ccp_details ccd ON ccd.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID \n"
                + " JOIN contract_master cm ON cm.contract_master_sid = ccd.contract_master_sid \n"
                + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_MASTER SPM ON SPM.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "\n"
                + "                                        AND SPM.user_id = m_mas.user_id\n"
                + "                                        AND SPM.session_id = m_mas.session_id\n"
                + "LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_PROJ SPMA ON SPMA.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "                                                                        AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                                          AND SPMA.user_id = m_mas.user_id\n"
                + "                                          AND SPMA.session_id = m_mas.session_id\n"
                + "JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                + "WHERE "
                + "   m_mas.USER_ID = " + userID + "\n"
                + "   AND m_mas.SESSION_ID = " + sessionID + "\n"
                + "GROUP  BY cm." + columnName + ",\n";
        if (!"\"YEAR\"".equalsIgnoreCase(frequency)) {
            customSQL1 += "          p." + frequency + ",\n";
        }
        customSQL1 += "          "
                + "CCP.RELATIONSHIP_LEVEL_SID,\n"
                + "CCP.HIERARCHY_NO,\n"
                + "          p.\"YEAR\"\n";

        String sql = CommonLogic.getCCPQueryForPR(projSelDTO) + " \n" + "select "
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
        String finalCond = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() != 1) {
            finalCond = "  AND DISCOUNT.PERIODS = SALE.PERIODS";
        }

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
                + "     )\n"
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

        selectClause += " select ";
        String customQuery = StringUtils.EMPTY;

        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByNetSalesClause("DISCOUNT", "SALE", projSelDTO.getFrequencyDivision());
        selectClause += list.get(0);
        String finalWhereCond = list.get(2);
        String orderBy = list.get(2);

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

        customQuery = CommonLogic.getCCPQueryForPR(projSelDTO) + " \n" + selectClause + " from  (" + discountQuery + ") DISCOUNT  FULL  JOIN (" + salesQuery + ") SALE " + " ON DISCOUNT.YEARS = SALE.YEARS\n"
                + "  AND DISCOUNT.PERIODS = SALE.PERIODS" + "\n order by " + orderBy;

        return customQuery;
    }

    public String getDiscountTotalQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setFuture(false);
        String historyQuery = getChildTotalDiscountQuery(projSelDTO);
        projSelDTO.setFuture(true);
        String futureQuery = getChildTotalDiscountQuery(projSelDTO);
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0)
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

        String customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;
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
        selectClause += " TODIS.ACTUAL_SALES as DOL_ACTUAL_SALES \n"
                + ", TODIS.PROJECTION_SALES as DOL_PROJECTION_SALES \n"
                + ", PER_ACTUAL_SALES=Isnull(Isnull(TODIS.ACTUAL_SALES, 0) / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 \n"
                + ", PER_PROJECTION_SALES=Isnull(Isnull(TODIS.PROJECTION_SALES, 0) / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100  \n";

        String totalDiscountQuery = getTotalDiscountMainQuery(projSelDTO);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (" + totalDiscountQuery + ") TODIS LEFT JOIN (" + salesQuery + ") SALE " + finalWhereCond + " order by " + orderBy;
        projSelDTO.setIsTotal(true);
        return customQuery;
    }

    public static List<String> getProgramCodeName(int projectionId) {
        List<String> strList = new ArrayList<String>();
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(getProgramCodeNameQuery(projectionId), null, null);
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

    private int getProgramCodeCount(final ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, Exception {
        String sql = CommonLogic.getCCPQueryForPR(projectionSelectionDTO);
        sql += CustomSQLUtil.get("m.program-code-count-query");
        SalesProjectionDAO dao = new SalesProjectionDAOImpl();
        List list = (List) dao.executeSelectQuery(sql);
        LOGGER.info("getProgramCodeCount = = = = =" + (Integer) list.get(0));
        return (Integer) list.get(0);
    }

    private List getProgramCodeList(final ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, Exception {
        String sql = CommonLogic.getCCPQueryForPR(projectionSelectionDTO);
        sql += CustomSQLUtil.get("m.program-code-query");
        SalesProjectionDAO dao = new SalesProjectionDAOImpl();
        List<Object> list = (List) dao.executeSelectQuery(sql);
        List finalList = new ArrayList();
        for (Object obj : list) {
            finalList.add(obj);
        }
        LOGGER.info("getProgramCodeList query custom sql = = = =" + sql);
        LOGGER.info("getProgramCodeList = = = = =" + finalList.size());
        return finalList;
    }

    public String getNetSalesQuery(ProjectionSelectionDTO projSelDTO) {

        String customerProductQuery = StringUtils.EMPTY;

        StringBuffer netSalesQuery = new StringBuffer();
        String relationshipBuilderSid = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            relationshipBuilderSid = projSelDTO.getCustRelationshipBuilderSid();
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
            relationshipBuilderSid = projSelDTO.getProdRelationshipBuilderSid();
        }
        if (projSelDTO.isIsCustomHierarchy()) {
            customerProductQuery = getCustomQuery(projSelDTO);
        } else {
            customerProductQuery = getCusProdQuery(projSelDTO, relationshipBuilderSid);
        }
        List<String> list = getCommonSelectPeriodNetSalesClause("TODIS", "SALE", projSelDTO.getFrequencyDivision());
        List<String> list2 = getCommonSelectPeriodNetSalesClause("HISTORY", "FUTURE", projSelDTO.getFrequencyDivision());
        String value = StringUtils.EMPTY;
        String periodValue = StringUtils.EMPTY;
        String groupByValue = StringUtils.EMPTY;
        String period = StringUtils.EMPTY;
        String periodHisValue = StringUtils.EMPTY;
        String periodGroup = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 4) {
            value = ", I.QUARTER AS PERIODS \n ";
            periodValue = ",PERIODS \n";
            groupByValue = ",A.PERIODS\n";
            period = "and HISTORY.PERIODS=FUTURE.PERIODS\n";
            periodHisValue = ", p.QUARTER as PERIODS";
            periodGroup = " p.QUARTER,";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            value = ", I.SEMI_ANNUAL AS PERIODS \n";
            periodValue = ",PERIODS \n";
            groupByValue = ",A.PERIODS\n";
            period = "and HISTORY.PERIODS=FUTURE.PERIODS\n";
            periodHisValue = ", p.SEMI_ANNUAL as PERIODS";
            periodGroup = " p.SEMI_ANNUAL,";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            value = StringUtils.EMPTY;
            periodValue = StringUtils.EMPTY;
            groupByValue = StringUtils.EMPTY;
            periodHisValue = StringUtils.EMPTY;
            periodGroup = StringUtils.EMPTY;
        } else if (projSelDTO.getFrequencyDivision() == 12) {
            value = ", I.MONTH AS PERIODS \n";
            periodValue = ",PERIODS \n";
            groupByValue = ",A.PERIODS\n";
            period = "and HISTORY.PERIODS=FUTURE.PERIODS\n";
            periodHisValue = ", p.MONTH as PERIODS";
            periodGroup = " p.MONTH,";
        }
        netSalesQuery.append("DECLARE @FROM_DATE DATE\n"
                + "      , @STARTFROM DATE\n"
                + "      , @PROJECTION_DATE DATE\n"
                + "      , @START_PERIOD_SID INT\n"
                + "      , @END_PERIOD_SID INT\n"
                + "\n"
                + " SELECT TOP 1 @STARTFROM = DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, - 3, GETDATE())), 0)\n"
                + "      , @PROJECTION_DATE = DATEADD(M, DATEDIFF(M, 0, DATEADD(DAY, - 1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))), 0)\n"
                + " FROM PROJECTION_MASTER\n"
                + " WHERE PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "\n"
                + " SELECT @START_PERIOD_SID = PERIOD_SID\n"
                + " FROM PERIOD\n"
                + " WHERE PERIOD_DATE = @STARTFROM\n"
                + "\n"
                + " SELECT @END_PERIOD_SID = PERIOD_SID\n"
                + " FROM PERIOD\n"
                + " WHERE PERIOD_DATE = @PROJECTION_DATE\n"
                + " DECLARE @CCP TABLE\n"
                + "   (\n"
                + "      RELATIONSHIP_LEVEL_SID INT,\n"
                + "      PROJECTION_DETAILS_SID INT,\n"
                + "      CCP_DETAILS_SID        INT,\n"
                + "      HIERARCHY_NO           VARCHAR(50)\n"
                + "   )\n" + customerProductQuery
                + "  IF OBJECT_ID('TEMPDB.DBO.#TEMP_CCP', 'U') IS NOT NULL\n"
                + "      DROP TABLE #TEMP_CCP;\n"
                + "\n"
                + " CREATE TABLE #TEMP_CCP (\n"
                + "      COMPANY_MASTER_SID INT\n"
                + "      , CONTRACT_MASTER_SID INT\n"
                + "      , ITEM_MASTER_SID INT\n"
                + "      , PROJECTION_DETAILS_SID INT PRIMARY KEY\n"
                + "      , PROJECTION_MASTER_SID INT\n"
                + "      )\n"
                + "\n"
                + " INSERT INTO #TEMP_CCP (\n"
                + "      COMPANY_MASTER_SID\n"
                + "      , CONTRACT_MASTER_SID\n"
                + "      , ITEM_MASTER_SID\n"
                + "      , PROJECTION_DETAILS_SID\n"
                + "      , PROJECTION_MASTER_SID\n"
                + "      )\n"
                + " SELECT C.COMPANY_MASTER_SID\n"
                + "      , C.CONTRACT_MASTER_SID\n"
                + "      , C.ITEM_MASTER_SID\n"
                + "      , PD.PROJECTION_DETAILS_SID\n"
                + "      , pm.PROJECTION_MASTER_SID\n"
                + " FROM\n"
                + "       CCP_DETAILS C\n"
                + "      , PROJECTION_DETAILS PD\n"
                + "      , PROJECTION_MASTER PM\n"
                + " WHERE PD.CCP_DETAILS_SID = C.CCP_DETAILS_SID\n"
                + "      AND PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID\n"
                + "      AND PM.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + "       and EXISTS (SELECT 1 FROM\n"
                + "       @CCP CCP WHERE CCP.CCP_DETAILS_SID = C.CCP_DETAILS_SID)\n"
                + " DECLARE @ITEMID [DBO].[UDT_ITEM]\n"
                + "\n"
                + " INSERT INTO @ITEMID\n"
                + " SELECT IM.ITEM_MASTER_SID\n"
                + " FROM ITEM_MASTER IM\n"
                + " WHERE EXISTS (\n"
                + "             SELECT 1\n"
                + "             FROM #TEMP_CCP A\n"
                + "             WHERE PROJECTION_MASTER_SID =  " + projSelDTO.getProjectionId() + "\n"
                + "                    AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID\n"
                + "             ) select " + list.get(0) + "\n"
                + "  ACTUAL_SALES=(Isnull(SALE.SALES_ACTUAL_SALES, 0)-Isnull(TODIS.ACTUAL_SALES, 0)),\n"
                + "  PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-Isnull(TODIS.PROJECTION_SALES, 0)),COGS_ACTUAL = ISNULL(SALE.COGS_ACTUAL, 0)\n"
                + "      ,COGS_PROJECTED = ISNULL(SALE.COGS_PROJECTED, 0)\n"
                + "      ,NET_PROFIT_ACTUAL = ((Isnull(SALE.SALES_ACTUAL_SALES, 0) - (Isnull(TODIS.MADD_ACTUAL_SALES, 0) + Isnull(TODIS.SUPP_ACTUAL_SALES, 0))) - ISNULL(SALE.COGS_ACTUAL, 0))\n"
                + "      ,NET_PROFIT_PROJECTED = ((Isnull(SALE.SALES_PROJECTION_SALES, 0) - (Isnull(TODIS.MADD_ACTUAL_SALES, 0) + Isnull(TODIS.SUPP_ACTUAL_SALES, 0))) - ISNULL(SALE.COGS_PROJECTED, 0)) from (\n"
                + " select " + list2.get(0) + "\n"
                + "\n"
                + "  (Isnull(HISTORY.MAD_ACTUAL_SALES,0)+Isnull(HISTORY.SUP_ACTUAL_SALES,0)) as ACTUAL_SALES,\n"
                + " ((Isnull(HISTORY.MAD_ACTUAL_RATE,0)+Isnull(HISTORY.SUP_ACTUAL_RATE,0))/2) as ACTUAL_RATE,\n"
                + " (ISNULL(HISTORY.MAD_ACTUAL_RPU, 0) + ISNULL(HISTORY.SUP_ACTUAL_RPU, 0) ) AS ACTUAL_RPU,\n"
                + " (Isnull(FUTURE.MAD_PROJECTION_SALES,0)+Isnull(FUTURE.SUP_PROJECTION_SALES,0)) as PROJECTION_SALES,\n"
                + " ((Isnull(FUTURE.MAD_PROJECTION_RATE,0)+Isnull(FUTURE.SUP_PROJECTION_RATE,0))/2) as PROJECTION_RATE,\n"
                + " (Isnull(FUTURE.MAD_PROJECTION_RPU,0)+Isnull(FUTURE.SUP_PROJECTION_RPU,0)) as PROJECTION_RPU "
                + "  ,(isnull(history.MAD_ACTUAL_SALES,0))MADD_ACTUAL_SALES\n"
                + "            ,(isnull(history.SUP_ACTUAL_SALES,0))SUPP_ACTUAL_SALES\n"
                + "             ,(isnull(FUTURE.MAD_PROJECTION_SALES,0))MADD_PROJECTION_SALES\n"
                + "              ,(isnull(FUTURE.SUP_PROJECTION_SALES,0))SUPP_PROJECTION_SALES from (\n"
                + " select p.\"YEAR\" as YEARS" + periodHisValue + ",\n"
                + "  Sum(Isnull(MAD.ACTUAL_SALES,0))        AS  MAD_ACTUAL_SALES,\n"
                + "        ( Sum(Isnull(MAD.ACTUAL_SALES, 0)) / NULLIF(Sum(m_ac.ACTUAL_SALES), 0) ) * 100    AS  MAD_ACTUAL_RATE,\n"
                + "        ( SUM(ISNULL(MAD.ACTUAL_SALES, 0)) / NULLIF(SUM(M_AC.ACTUAL_UNITS), 0) )        AS MAD_ACTUAL_RPU,\n"
                + "        Sum(Isnull(SPMA.ACTUAL_SALES,0))        AS  SUP_ACTUAL_SALES,\n"
                + "        ( Sum(Isnull(SPMA.ACTUAL_SALES, 0)) / NULLIF(Sum(m_ac.ACTUAL_SALES), 0) ) * 100        AS  SUP_ACTUAL_RATE,\n"
                + "        ( SUM(ISNULL(SPMA.ACTUAL_SALES, 0)) / NULLIF(SUM(M_AC.ACTUAL_UNITS), 0) ) * 100 AS SUP_ACTUAL_RPU FROM   @CCP CCP INNER  JOIN   ST_M_SALES_PROJECTION_MASTER  m_mas ON CCP.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                + " INNER  JOIN   ST_M_ACTUAL_SALES m_ac ON m_mas.PROJECTION_DETAILS_SID = m_ac.PROJECTION_DETAILS_SID\n"
                + "                              AND m_ac.user_id = m_mas.user_id\n"
                + "                              AND m_ac.session_id = m_mas.session_id\n"
                + " INNER JOIN   M_ACTUAL_DISCOUNT MAD ON MAD.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + " AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                             AND MAD.user_id = m_mas.user_id\n"
                + "                             AND MAD.session_id = m_mas.session_id\n"
                + "  LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_MASTER SPM ON SPM.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "                                         AND SPM.user_id = m_mas.user_id\n"
                + "                                         AND SPM.session_id = m_mas.session_id\n"
                + "  LEFT JOIN    ST_M_SUPPLEMENTAL_DISC_ACTUALS SPMA ON SPMA.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "                                                                         AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                                           AND SPMA.user_id = m_mas.user_id\n"
                + "                                           AND SPMA.session_id = m_mas.session_id\n"
                + " INNER  JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                + "  WHERE     m_mas.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "    AND m_mas.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + "  GROUP  BY  " + periodGroup + "\n"
                + "                           CCP.RELATIONSHIP_LEVEL_SID,\n"
                + "                           CCP.HIERARCHY_NO,\n"
                + "                           p.\"YEAR\"\n"
                + " ) HISTORY FULL  JOIN (\n"
                + " select p.\"YEAR\" as YEARS" + periodHisValue + ",\n"
                + "  Sum(Isnull(MAD.PROJECTION_SALES,0))   AS  MAD_PROJECTION_SALES,\n"
                + "        (Sum(Isnull(MAD.PROJECTION_SALES, 0)) / NULLIF(Sum(m_ac.PROJECTION_SALES), 0)) * 100    AS  MAD_PROJECTION_RATE,\n"
                + "        ( SUM(ISNULL(MAD.PROJECTION_SALES, 0)) / NULLIF(SUM(M_AC.PROJECTION_UNITS), 0) )        AS MAD_PROJECTION_RPU,\n"
                + "        Sum(Isnull(SPMA.PROJECTION_SALES,0))   AS  SUP_PROJECTION_SALES,\n"
                + "        ( Sum(Isnull(SPMA.PROJECTION_SALES, 0)) / NULLIF(Sum(m_ac.PROJECTION_SALES), 0) ) * 100    AS  SUP_PROJECTION_RATE,\n"
                + "        Sum(Isnull(SPMA.PROJECTION_RPU,0))   AS  SUP_PROJECTION_RPU\n"
                + "  FROM   @CCP CCP INNER  JOIN   ST_M_SALES_PROJECTION_MASTER  m_mas ON CCP.PROJECTION_DETAILS_SID = m_mas.PROJECTION_DETAILS_SID\n"
                + " INNER  JOIN   ST_M_SALES_PROJECTION m_ac ON m_mas.PROJECTION_DETAILS_SID = m_ac.PROJECTION_DETAILS_SID\n"
                + "                              AND m_ac.user_id = m_mas.user_id\n"
                + "                              AND m_ac.session_id = m_mas.session_id\n"
                + " INNER JOIN   M_DISCOUNT_PROJECTION MAD ON MAD.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + " AND MAD.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                             AND MAD.user_id = m_mas.user_id\n"
                + "                             AND MAD.session_id = m_mas.session_id\n"
                + "  LEFT JOIN   ST_M_SUPPLEMENTAL_DISC_MASTER SPM ON SPM.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "                                         AND SPM.user_id = m_mas.user_id\n"
                + "                                         AND SPM.session_id = m_mas.session_id\n"
                + "  LEFT JOIN    ST_M_SUPPLEMENTAL_DISC_PROJ SPMA ON SPMA.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "                                                                         AND SPMA.PERIOD_SID=m_ac.PERIOD_SID\n"
                + "                                           AND SPMA.user_id = m_mas.user_id\n"
                + "                                           AND SPMA.session_id = m_mas.session_id\n"
                + " INNER  JOIN   PERIOD p ON p.period_sid = m_ac.PERIOD_SID\n"
                + "  WHERE     m_mas.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "    AND m_mas.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + "  GROUP  BY  " + periodGroup + "\n"
                + "                           CCP.RELATIONSHIP_LEVEL_SID,\n"
                + "                           CCP.HIERARCHY_NO,\n"
                + "                           p.\"YEAR\"\n"
                + " ) FUTURE\n" + "ON HISTORY.YEARS = FUTURE.YEARS  "
                + period
                + " ) TODIS FULL  JOIN (\n"
                + " SELECT " + list2.get(0) + ""
                + "              Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "              ,Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "              ,Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "              ,Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "              ,Isnull(HISTORY.COGS_ACTUAL, 0) AS COGS_ACTUAL\n"
                + "              ,Isnull(FUTURE.COGS_PROJECTED, 0) AS COGS_PROJECTED\n"
                + "      FROM (\n"
                + "              SELECT YEARS\n"
                + periodValue
                + "                      ,sum(A.ACTUAL_SALES) AS SALES_ACTUAL_SALES\n"
                + "                      ,sum(A.ACTUAL_PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                      ,sum(A.ACTUAL_UNITS) AS ACTUAL_UNITS\n"
                + "                      ,sum(A.ACTUAL_PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                      ,SUM(A.COGS_ACTUAL) AS COGS_ACTUAL\n"
                + "              FROM (\n"
                + "                      SELECT I.\"YEAR\" AS YEARS\n"
                + value
                + "                              ,A.ACTUAL_SALES\n"
                + "                              ,A.ACTUAL_PROJECTION_SALES\n"
                + "                              ,A.ACTUAL_UNITS\n"
                + "                              ,A.ACTUAL_PROJECTION_UNITS\n"
                + "                              ,COGS_ACTUAL = (ISNULL(A.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                + "                      FROM ST_M_ACTUAL_SALES A\n"
                + "                      INNER JOIN @CCP CCP ON A.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "                      INNER JOIN \"PERIOD\" I ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                      INNER JOIN CCP_DETAILS CC ON CCP.CCP_DETAILS_sID = CC.CCP_DETAILS_sID\n"
                + "                      INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U ON CC.ITEM_MASTER_SID = U.ITEM_MASTER_SID\n"
                + "                              AND A.PERIOD_SID = U.PERIOD_SID\n"
                + "                      WHERE A.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "                              AND A.SESSION_ID = " + projSelDTO.getSessionId() + "\n"
                + CommonLogic.getPeriodRestrictionQuery(projSelDTO)
                + "                      ) A\n"
                + "              GROUP BY A.YEARS\n"
                + groupByValue
                + "              ) HISTORY\n"
                + "      FULL JOIN (\n"
                + "              SELECT YEARS\n"
                + periodValue
                + "                      ,0 AS SALES_ACTUAL_SALES\n"
                + "                      ,sum(P.PROJECTION_SALES) AS SALES_PROJECTION_SALES\n"
                + "                      ,0 AS ACTUAL_UNITS\n"
                + "                      ,sum(P.PROJECTION_UNITS) AS PROJECTION_UNITS\n"
                + "                      ,SUM(P.COGS_PROJECTED) AS COGS_PROJECTED\n"
                + "              FROM (\n"
                + "                      SELECT I.\"YEAR\" AS YEARS\n"
                + value
                + "                              ,A.PROJECTION_SALES\n"
                + "                              ,A.PROJECTION_UNITS\n"
                + "                              ,COGS_PROJECTED = (ISNULL(A.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0))\n"
                + "                      FROM ST_M_SALES_PROJECTION A\n"
                + "                      INNER JOIN @CCP CCP ON A.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID\n"
                + "                      INNER JOIN \"PERIOD\" I ON A.PERIOD_SID = I.PERIOD_SID\n"
                + "                      INNER JOIN CCP_DETAILS CC ON CCP.CCP_DETAILS_sID = CC.CCP_DETAILS_sID\n"
                + "                      INNER JOIN [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'UN') U ON CC.ITEM_MASTER_SID = U.ITEM_MASTER_SID\n"
                + "                              AND A.PERIOD_SID = U.PERIOD_SID\n"
                + "                      WHERE A.USER_ID = " + projSelDTO.getUserId() + "\n"
                + "                              AND A.SESSION_ID =" + projSelDTO.getSessionId() + "\n"
                + CommonLogic.getPeriodRestrictionQuery(projSelDTO)
                + "                      ) P\n"
                + "              GROUP BY YEARS\n"
                + periodValue
                + "              ) FUTURE ON HISTORY.YEARS = FUTURE.YEARS\n");
        if (projSelDTO.getFrequencyDivision() != 1) {
            netSalesQuery.append("  AND HISTORY.PERIODS = FUTURE.PERIODS\n");
        }
        netSalesQuery.append(" ) SALE\n"
                + "  on TODIS.YEARS=SALE.YEARS");
        if (projSelDTO.getFrequencyDivision() != 1) {
            netSalesQuery.append(" and TODIS.PERIODS=SALE.PERIODS ");
        }
        netSalesQuery.append("   ORDER BY YEARS\n" + periodValue + "");

        return netSalesQuery.toString();
    }

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
        StringBuffer customQuery = new StringBuffer(StringUtils.EMPTY);
        customQuery.append("INSERT INTO @CCP (RELATIONSHIP_LEVEL_SID\n"
                + ", PROJECTION_DETAILS_SID\n"
                + ", CCP_DETAILS_SID\n"
                + ", HIERARCHY_NO)\n"
                + "  SELECT DISTINCT\n"
                + "    HLDC.RELATIONSHIP_LEVEL_SID,\n"
                + "    PD.PROJECTION_DETAILS_SID,\n"
                + "    CCPMAPC.CCP_DETAILS_SID,\n"
                + "    HLDC.HIERARCHY_NO\n"
                + "  FROM (SELECT\n"
                + "    RLD.RELATIONSHIP_LEVEL_VALUES,\n"
                + "    RLD.HIERARCHY_NO,\n"
                + "    CCP.CCP_DETAILS_SID\n"
                + "  FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "  INNER JOIN CCP_MAP CCP\n"
                + "    ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                + "    AND RLD.RELATIONSHIP_BUILDER_SID = " + projSelDTO.getCustRelationshipBuilderSid() + "\n"
                + "  INNER JOIN PROJECTION_DETAILS PD\n"
                + "    ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "    AND PD.PROJECTION_MASTER_SID =  " + projSelDTO.getProjectionId() + ") CCPMAPC\n"
                + "  INNER JOIN (SELECT\n"
                + "    RLD.RELATIONSHIP_LEVEL_VALUES,\n"
                + "    RLD.HIERARCHY_NO,\n"
                + "    CCP.CCP_DETAILS_SID\n"
                + "  FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "  INNER JOIN CCP_MAP CCP\n"
                + "    ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID\n"
                + "    AND RLD.RELATIONSHIP_BUILDER_SID = " + projSelDTO.getProdRelationshipBuilderSid() + "\n"
                + "  INNER JOIN PROJECTION_DETAILS PD\n"
                + "    ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID\n"
                + "    AND PD.PROJECTION_MASTER_SID =  " + projSelDTO.getProjectionId() + ") CCPMAPP\n"
                + "    ON CCPMAPC.CCP_DETAILS_SID = CCPMAPP.CCP_DETAILS_SID\n"
                + "  INNER JOIN (SELECT\n"
                + "    RLD2.HIERARCHY_NO,\n"
                + "    RLD2.RELATIONSHIP_LEVEL_SID,\n"
                + "    CVD.LEVEL_NO\n"
                + "  FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                + "  INNER JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD\n"
                + "    ON CVD.HIERARCHY_ID = HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "    AND CVD.CUSTOM_VIEW_MASTER_SID =" + projSelDTO.getCustomId() + "\n"
                + "    AND CVD.LEVEL_NO LIKE '" + customerLevelNo + "'\n"
                + "  INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2\n"
                + "    ON HLD.HIERARCHY_LEVEL_DEFINITION_SID = RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "  INNER JOIN PROJECTION_CUST_HIERARCHY PCH2\n"
                + "    ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID\n"
                + "    AND PCH2.PROJECTION_MASTER_SID =  " + projSelDTO.getProjectionId() + "\n"
                + "  WHERE RLD2.HIERARCHY_NO LIKE '" + customerHierarchyNo + "') HLDC\n"
                + "    ON CCPMAPC.HIERARCHY_NO LIKE HLDC.HIERARCHY_NO + '%'\n"
                + "  INNER JOIN (SELECT\n"
                + "    RLD2.HIERARCHY_NO,\n"
                + "    RLD2.RELATIONSHIP_LEVEL_SID,\n"
                + "    CVD.LEVEL_NO\n"
                + "  FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                + "  INNER JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD\n"
                + "    ON CVD.HIERARCHY_ID = HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "    AND CVD.CUSTOM_VIEW_MASTER_SID = " + projSelDTO.getCustomId() + "\n"
                + "    AND CVD.LEVEL_NO LIKE '" + productLevelNo + "'\n"
                + "  INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2\n"
                + "    ON HLD.HIERARCHY_LEVEL_DEFINITION_SID = RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "  INNER JOIN PROJECTION_PROD_HIERARCHY PCH2\n"
                + "    ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID\n"
                + "    AND PCH2.PROJECTION_MASTER_SID =  " + projSelDTO.getProjectionId() + "\n"
                + "  WHERE RLD2.HIERARCHY_NO LIKE '" + productHierarchyNo + "') HLDP\n"
                + "    ON CCPMAPP.HIERARCHY_NO LIKE HLDP.HIERARCHY_NO + '%'\n"
                + "  INNER JOIN PROJECTION_DETAILS PD\n"
                + "    ON PD.CCP_DETAILS_SID = CCPMAPC.CCP_DETAILS_SID\n"
                + "    AND PD.PROJECTION_MASTER_SID =  " + projSelDTO.getProjectionId() + StringUtils.EMPTY);

        return customQuery.toString();
    }

    public String getCusProdQuery(ProjectionSelectionDTO projSelDTO, String relationshipBuilderSid) {
        StringBuffer cusProdQuery = new StringBuffer();
        cusProdQuery.append("  INSERT INTO @CCP\n"
                + "             (RELATIONSHIP_LEVEL_SID,PROJECTION_DETAILS_SID,CCP_DETAILS_SID,HIERARCHY_NO)\n"
                + "  SELECT LCCP.RELATIONSHIP_LEVEL_SID,LCCP.PROJECTION_DETAILS_SID, LCCP.CCP_DETAILS_SID, LCCP.HIERARCHY_NO from\n"
                + " (SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID,CCPMAP.PROJECTION_DETAILS_SID from\n"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID,PD.PROJECTION_DETAILS_SID\n"
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + " INNER  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                + " AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + "\n"
                + " INNER  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID\n"
                + " AND PD.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + " ) CCPMAP,\n"
                + "  (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID\n"
                + "  FROM RELATIONSHIP_LEVEL_DEFINITION RLD1\n"
                + " INNER  JOIN " + CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator()) + " PCH\n"
                + "  ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID\n"
                + "  AND PCH.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + "  WHERE RLD1.HIERARCHY_NO like '" + projSelDTO.getHierarchyNo() + "%') HLD\n"
                + "  WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO+'%' ) LCCP\n"
                + "  WHERE LCCP.HIERARCHY_NO in\n"
                + "  (SELECT RLD2.HIERARCHY_NO\n"
                + "  FROM RELATIONSHIP_LEVEL_DEFINITION RLD2\n"
                + " INNER  JOIN " + CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator()) + " PCH2\n"
                + "  ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID\n"
                + "  AND PCH2.PROJECTION_MASTER_SID=" + projSelDTO.getProjectionId() + "\n"
                + "  WHERE RLD2.LEVEL_NO=" + projSelDTO.getTreeLevelNo() + ")\n");

        return cusProdQuery.toString();
    }

    public String getProjectionResultsSalesQueryPivotForPR(ProjectionSelectionDTO projSelDTO) {
        String value = StringUtils.EMPTY;
        String periodValue = StringUtils.EMPTY;
        String period = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 4) {
            value = ", I.QUARTER AS PERIODS \n ";
            periodValue = "  PERIODS,";
            period = " ,PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            value = ", I.SEMI_ANNUAL AS PERIODS \n";
            periodValue = "  PERIODS,";
            period = " ,PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            value = ",0 AS PERIODS";
            periodValue = "  PERIODS,";
            period = " ,PERIODS";
        } else if (projSelDTO.getFrequencyDivision() == 12) {
            value = ", I.MONTH AS PERIODS \n";
            periodValue = "  PERIODS,";
            period = " ,PERIODS";
        }
        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\"\n";
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
                + CommonLogic.getUserSessionQueryConditionForPR((Integer.valueOf(projSelDTO.getUserId())), (Integer.valueOf(projSelDTO.getSessionId())), "A")
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
                + customSql + ")P" + " group by " + "YEARS\n" + period;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByNetSalesClause("HISTORY", "FUTURE", projSelDTO.getFrequencyDivision());
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as SALES_ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as SALES_PROJECTION_SALES,"
                + "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,"
                + " ISNULL(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) AS PROJECTION_UNITS,\n"
                + "  ISNULL(HISTORY.COGS_ACTUAL, 0) AS COGS_ACTUAL,\n"
                + "  ISNULL(FUTURE.COGS_PROJECTED, 0) AS COGS_PROJECTED \n";

        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL  JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;

        return customQuery;
    }

    public static List<String> getCommonSelectPeriodNetSalesClause(String table1, String table2, int freq) {
        List<String> list = new ArrayList<String>();
        String orderBy = " YEARS";
        String groupBy = " " + table1 + ".YEARS";
        String selectClause = "Isnull(" + table1 + ".YEARS, " + table2 + ".YEARS) as YEARS, ";
        String finalWhereCond = " " + "on" + " " + table1 + ".YEARS=" + table2 + ".YEARS";
        if (freq != 1) {
            selectClause += "Isnull(" + table1 + ".PERIODS, " + table2 + ".PERIODS)";
            selectClause += " as PERIODS, \n";
        }
        if (freq != 1) {
            finalWhereCond += " and " + table1 + ".PERIODS=" + table2 + ".PERIODS";
            groupBy += ", " + table1 + ".PERIODS";

        }
        orderBy += ", PERIODS";
        list.add(selectClause);
        list.add(finalWhereCond);
        list.add(orderBy);
        list.add(groupBy);
        return list;
    }

}
