/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojectionresults.logic;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getCommonColumnHeaderSPR;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.CONTRACT_SALES_AT_WAC;
import static com.stpl.app.utils.Constants.LabelConstants.DEMAND_SALES;
import static com.stpl.app.utils.Constants.LabelConstants.EX_FACTORY_SALES;
import static com.stpl.app.utils.Constants.LabelConstants.INVENTORY_WITHDRAW;
import static com.stpl.app.utils.Constants.LabelConstants.PERIOD;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.utils.Constants.LabelConstants.UNIT_VOL;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class MSalesProjectionResultsLogic {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(MSalesProjectionResultsLogic.class);
    /**
     * The Numeric Zero Decimal Places Format.
     */
    private static final DecimalFormat NUM_ZERO = new DecimalFormat("#,##0");
    private static final DecimalFormat TWO_DECIMAL = new DecimalFormat("#,##0.00");
    private static final String CURRENCY = "$";
    /**
     * The unit volume.
     */
    private static final DecimalFormat UNITVOLUME = new DecimalFormat("#,##0");

    public List<SalesProjectionResultsDTO> getSalesProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        int started = start;
        int mayBeAdded = 0;
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        String freq = StringUtils.EMPTY;
        boolean tempCustomFlag = false;
        if (projSelDTO.getFrequencyDivision() == 1 || Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO || Constant.SEMI_ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "SEMI-ANNUALLY";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR || Constant.QUARTERLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE || Constant.MONTHLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "MONTHLY";
        }

        Object[] orderedArgs = {projSelDTO.getProjectionId(), projSelDTO.getUserId(), projSelDTO.getSessionId(), freq, "Projection Results", null, null};
        if (projSelDTO.isIsProjectionTotal()) {
            if (start < 1) {
                SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
                dto.setRelationshipLevelName(Constant.PROJECTION_TOTAL);
                dto.setParent(0);
                projDTOList.add(dto);
                neededRecord--;
            }
            mayBeAdded++;
            if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                SalesProjectionResultsDTO demandSalesDTO = null;
                SalesProjectionResultsDTO inventoryWithdrawDTO = null;
                SalesProjectionResultsDTO contractSalesDto = null;
                SalesProjectionResultsDTO unitVolDto = null;
                List<SalesProjectionResultsDTO> projectionTotalList = getProjectionTotal(orderedArgs, projSelDTO);
                SalesProjectionResultsDTO exFactorySalesDTO = projectionTotalList.get(0);
                demandSalesDTO = projectionTotalList.get(1);
                inventoryWithdrawDTO = projectionTotalList.get(NumericConstants.TWO);
                contractSalesDto = projectionTotalList.get(NumericConstants.THREE);
                unitVolDto = projectionTotalList.get(NumericConstants.FOUR);
                projectionTotalList = null;
                mayBeAdded++;
                if ((start < NumericConstants.TWO && neededRecord > 0) && (exFactorySalesDTO != null)) {
                        projDTOList.add(exFactorySalesDTO);
                        neededRecord--;

                }
                String salesUnits = projSelDTO.getSalesOrUnit();
                if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.SALES_SMALL))) {
                    if (start < NumericConstants.FOUR) {
                        if (demandSalesDTO != null) {
                            projDTOList.add(demandSalesDTO);
                            neededRecord--;
                        }
                        if (inventoryWithdrawDTO != null) {
                            projDTOList.add(inventoryWithdrawDTO);
                            neededRecord--;
                        }
                        if (contractSalesDto != null) {
                            projDTOList.add(contractSalesDto);
                            neededRecord--;
                        }
                    }
                    mayBeAdded++;
                }
                if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.UNITS_SMALL))) {
                    if (((salesUnits.equals(Constant.BOTH) && start < NumericConstants.FIVE) || (start < NumericConstants.FOUR)) && (unitVolDto != null)) {
                            projDTOList.add(unitVolDto);
                            neededRecord--;
                    }
                    mayBeAdded++;
                }

            } else {
                int mayBeAddedRecord = start - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                projSelDTO.setProjTabName("SPR");
                List<SalesProjectionResultsDTO> projectionDtoList = getProjectionPivotTotal(orderedArgs, projSelDTO);
                projSelDTO.setProjTabName(StringUtils.EMPTY);
                for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; k++) {
                    projDTOList.add(projectionDtoList.get(k));
                    neededRecord--;
                }
                mayBeAdded += projectionDtoList.size();
                projectionDtoList = null;
            }
        } else {

            if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                SalesProjectionResultsDTO contractSalesDto = null;
                SalesProjectionResultsDTO unitVolDto = null;

                String salesUnits = projSelDTO.getSalesOrUnit();
                if (neededRecord > 0 && projSelDTO.isIsTotal()) {
                    if (contractSalesDto == null || unitVolDto == null) {
                        List<SalesProjectionResultsDTO> contractSalesAndUnits = getContractSalesAndUnits(projSelDTO);
                        contractSalesDto = contractSalesAndUnits.get(0);
                        unitVolDto = contractSalesAndUnits.get(1);
                        contractSalesAndUnits = null;
                    }
                    if (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.SALES_SMALL)) {
                        boolean toadd = false;
                        if (projSelDTO.isIsProjectionTotal() && start < NumericConstants.FOUR) {
                            toadd = true;
                        } else if (!projSelDTO.isIsProjectionTotal() && start < 1) {
                            toadd = true;
                        } else {
                            toadd = false;
                        }
                        if (toadd && !projSelDTO.isIsProjectionTotal()) {
                            projDTOList.add(contractSalesDto);
                            neededRecord--;
                            started++;
                        }
                        mayBeAdded++;
                    }
                    if (neededRecord > 0 && (salesUnits.equals(Constant.BOTH) || salesUnits.equals(Constant.UNITS_SMALL))) {
                        boolean toadd = false;
                        if (projSelDTO.isIsProjectionTotal() && start < NumericConstants.FIVE) {
                            toadd = true;
                        } else if (!projSelDTO.isIsProjectionTotal() && start < NumericConstants.TWO) {
                            toadd = true;
                        } else {
                            toadd = false;
                        }
                        if (toadd && !projSelDTO.isIsProjectionTotal()) {
                            projDTOList.add(unitVolDto);
                            neededRecord--;
                            started++;
                        }
                        mayBeAdded++;
                    }
                }
            } else {
                List<SalesProjectionResultsDTO> projectionDtoList = new ArrayList<SalesProjectionResultsDTO>();

                projectionDtoList = getProjectionPivot(projSelDTO);
                for (int k = started; k < projectionDtoList.size() && neededRecord > 0; k++) {
                    projDTOList.add(projectionDtoList.get(k));
                    neededRecord--;
                    started++;
                }
                mayBeAdded += projectionDtoList.size();
                projectionDtoList = null;
            }
        }
        if (neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) {
            int mayBeAddedRecord = start - mayBeAdded;
            if (mayBeAddedRecord < 0) {
                mayBeAddedRecord = 0;
            }

            if (projSelDTO.isCustomFlag()) {
                projSelDTO.setCustomLevelNo(projSelDTO.getCustomLevelNo() + 1);
                int i = SPRCommonLogic.getIndicatorCount(projSelDTO.getProjectionId(), projSelDTO.getCustomId());

                if (i >= projSelDTO.getCustomLevelNo()) {
                    tempCustomFlag = true;

                }
            }
            if (projSelDTO.isCustomFlag() && tempCustomFlag) {
                String indicator = SPRCommonLogic.getIndicator(projSelDTO.getCustomLevelNo(), projSelDTO.getCustomId());
                projSelDTO.setHierarchyIndicator(indicator);
                projSelDTO.setLevelNo(projSelDTO.getCustomLevelNo());
                projSelDTO.setTreeLevelNo(projSelDTO.getCustomLevelNo());
            }
            if (projSelDTO.isCustomFlag()) {
                if (tempCustomFlag) {
                    List<SalesProjectionResultsDTO> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, projSelDTO);
                    projDTOList.addAll(nextLevelValueList);
                    nextLevelValueList = null;
                }
            } else {
                projSelDTO.setLevelNo(projSelDTO.getLevelNo() + 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                List<SalesProjectionResultsDTO> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, projSelDTO);
                projDTOList.addAll(nextLevelValueList);
                nextLevelValueList = null;
            }
        }

        LOGGER.debug("Ends getSalesProjectionResultsMandated");
        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;

        List<SalesProjectionResultsDTO> resultList = new ArrayList<SalesProjectionResultsDTO>();
        if (neededRecord > 0) {
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.isFilterDdlb(), projSelDTO.getLevelName());

            for (int i = 0; i < levelList.size() && neededRecord > 0; i++) {
                Leveldto levelDto = levelList.get(i);

                SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
                dto.setLevelNo(levelDto.getLevelNo());
                dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                dto.setParentNode(levelDto.getParentNode());
                dto.setGroup(levelDto.getRelationshipLevelValue());
                dto.setLevelValue(levelDto.getRelationshipLevelValue());
                dto.setHierarchyNo(levelDto.getHierarchyNo());                
                dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                }
                dto.setCustomLevelNo(projSelDTO.getCustomLevelNo());
                dto.setOnExpandTotalRow(1);
                dto.setParent(1);
                resultList.add(dto);
                neededRecord--;
            }
            levelList = null;

        }
        return resultList;
    }

    public List<SalesProjectionResultsDTO> getProjectionTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<Object[]> gtsList = CommonLogic.callProcedure("PRC_M_PROJECTION_RESULTS", orderedArgs);
        if (gtsList != null) {
            projDTOList = getCustomizedProjectionTotal(gtsList, projSelDTO);
        }
        gtsList = null;

        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> getContractSalesAndUnits(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(getSalesProjectionResultsSalesQuery(projSelDTO), null, null);
        List<SalesProjectionResultsDTO> projDTOList = getCustomizedSalesProjectionResultsSales(list, projSelDTO);
        list = null;

        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> getProjectionPivotTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<Object[]> gtsList = CommonLogic.callProcedure("PRC_M_PROJECTION_RESULTS", orderedArgs);
        List<Object[]> discountList = new ArrayList<Object[]>();
        projDTOList = getCustomizedProjectionPivotTotal(gtsList, discountList, projSelDTO);
        gtsList = null;

        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> getCustomizedProjectionPivotTotal(List<Object[]> list, List<Object[]> discountList, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        int col = NumericConstants.FOUR;
        int dcol = NumericConstants.TWO;
        if (frequencyDivision != 1) {
            col = col + 1;
            dcol = dcol + 1;
        }
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(list);
        }

        for (Object[] row : list) {

            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[col - 1]));
            int period = Integer.valueOf(String.valueOf(row[NumericConstants.THREE]));
            List<String> common = new ArrayList<>();
            if ("SPR".equals(projSelDTO.getProjTabName())) {
                common = getCommonColumnHeaderSPR(frequencyDivision, year, period);
            } else {
                common = getCommonColumnHeader(frequencyDivision, year, period);
            }

            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = StringUtils.EMPTY;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
                projDTO.setLevelValue(commonHeader);
                projDTO.setRelationshipLevelName(commonHeader);
                String value = Constant.NULL;
                commonColumn = "efs";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[1];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWO];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }

                commonColumn = "dms";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_FIVE];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_SIX];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }

                commonColumn = "iws";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_SEVEN];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWENTY_EIGHT];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }

                commonColumn = "csw";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 1];
                    value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                    projDTO.addStringProperties(column, value);
                }
                commonColumn = "uv";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWO];
                    value = getFormatValue(UNITVOLUME, value, StringUtils.EMPTY);
                    projDTO.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THREE];
                    value = getFormatValue(UNITVOLUME, value, StringUtils.EMPTY);
                    projDTO.addStringProperties(column, value);
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                projDTOList.add(projDTO);
            }

        }
        list = null;
        for (String ob : periodList) {
            SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
            projDTOList.add(projDTO);
        }

        LOGGER.debug("Ends getCustomizedProjectionPivotTotalMandated");
        return projDTOList;

    }

    public List<SalesProjectionResultsDTO> getProjectionPivot(ProjectionSelectionDTO projSelDTO) {
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();

        List<Object[]> gtsList = (List<Object[]>) CommonLogic.executeSelectQuery(getSalesProjectionResultsSalesQuery(projSelDTO), null, null);
        projDTOList = getCustomizedProjectionPivot(gtsList, projSelDTO);
        gtsList = null;
        if (projSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(projDTOList);
        }
        return projDTOList;
    }

    public List<SalesProjectionResultsDTO> getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String salesOrUnits = projSelDTO.getSalesOrUnit();
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        SalesProjectionResultsDTO exFactorySalesDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO demandSalesDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO inventoryWithdrawDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO conSaleDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO unitVolDTO = new SalesProjectionResultsDTO();
        exFactorySalesDTO.setParent(0);
        exFactorySalesDTO.setRelationshipLevelName(EX_FACTORY_SALES.getConstant());

        demandSalesDTO.setParent(0);
        demandSalesDTO.setRelationshipLevelName(DEMAND_SALES.getConstant());

        inventoryWithdrawDTO.setParent(0);
        inventoryWithdrawDTO.setRelationshipLevelName(INVENTORY_WITHDRAW.getConstant());

        conSaleDTO.setParent(0);
        conSaleDTO.setRelationshipLevelName(CONTRACT_SALES_AT_WAC.getConstant());

        unitVolDTO.setParent(0);
        unitVolDTO.setRelationshipLevelName(UNIT_VOL.getConstant());

        if (list != null && !list.isEmpty()) {
            int col = NumericConstants.FOUR;
            if (frequencyDivision != 1) {
                col = col + 1;
            }
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                int year = Integer.valueOf(String.valueOf(obj[col - 1]));
                int period = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);

                if (Constant.SALES.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String exFactoryActual = StringUtils.EMPTY + obj[1];
                    exFactoryActual = getFormatValue(TWO_DECIMAL, exFactoryActual, CURRENCY);
                    exFactorySalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, exFactoryActual);
                    String exFactoryProjection = StringUtils.EMPTY + obj[NumericConstants.TWO];
                    exFactoryProjection = getFormatValue(TWO_DECIMAL, exFactoryProjection, CURRENCY);
                    exFactorySalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, exFactoryProjection);

                    String demandActual = StringUtils.EMPTY + obj[NumericConstants.TWENTY_FIVE];
                    demandActual = getFormatValue(TWO_DECIMAL, demandActual, CURRENCY);
                    demandSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, demandActual);
                    String demandProjection = StringUtils.EMPTY + obj[NumericConstants.TWENTY_TWO];
                    demandProjection = getFormatValue(TWO_DECIMAL, demandProjection, CURRENCY);
                    demandSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, demandProjection);

                    String inventoryWithdrawActual = StringUtils.EMPTY + obj[NumericConstants.TWENTY_SEVEN];
                    inventoryWithdrawActual = getFormatValue(TWO_DECIMAL, inventoryWithdrawActual, CURRENCY);
                    inventoryWithdrawDTO.addStringProperties(commonColumn + Constant.ACTUALS, inventoryWithdrawActual);
                    String inventoryWithdrawProjection = StringUtils.EMPTY + obj[NumericConstants.TWENTY_EIGHT];
                    inventoryWithdrawProjection = getFormatValue(TWO_DECIMAL, inventoryWithdrawProjection, CURRENCY);
                    inventoryWithdrawDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, inventoryWithdrawProjection);

                    String cswActuals = StringUtils.EMPTY + obj[col];
                    cswActuals = getFormatValue(TWO_DECIMAL, cswActuals, CURRENCY);
                    conSaleDTO.addStringProperties(commonColumn + Constant.ACTUALS, cswActuals);
                    String cswProjections = StringUtils.EMPTY + obj[col + 1];
                    cswProjections = getFormatValue(TWO_DECIMAL, cswProjections, CURRENCY);
                    conSaleDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, cswProjections);
                }
                if (Constant.UNITS.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String uvActuals = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    uvActuals = getFormatValue(UNITVOLUME, uvActuals, StringUtils.EMPTY);
                    unitVolDTO.addStringProperties(commonColumn + Constant.ACTUALS, uvActuals);
                    String uvProjections = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                    uvProjections = getFormatValue(UNITVOLUME, uvProjections, StringUtils.EMPTY);
                    unitVolDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, uvProjections);
                }
            }

        }
        list = null;

        projDTOList.add(exFactorySalesDTO);
        projDTOList.add(demandSalesDTO);
        projDTOList.add(inventoryWithdrawDTO);
        projDTOList.add(conSaleDTO);
        projDTOList.add(unitVolDTO);
        return projDTOList;
    }

    public String getSalesProjectionResultsSalesQuery(ProjectionSelectionDTO projSelDTO) {

        String viewtable = CommonLogic.getViewTableName(projSelDTO.getHierarchyIndicator());

        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = ",H.LEVEL_NAME , I.\"YEAR\"";
        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = getCCPWhereConditionQuery("H", "E", "CCP");
        String historyQuery = StringUtils.EMPTY;
        String futureQuery = StringUtils.EMPTY;

        String parentNode = projSelDTO.getParentNode();
        whereClause = " and H.PARENT_NODE = '" + parentNode + "'";
        String hierarchyNo = projSelDTO.getHierarchyNo();
        whereClause += " and H.HIERARCHY_NO = '" + hierarchyNo + "'";
        groupBy = ", H.HIERARCHY_NO " + groupBy;
        selectClause += "I.\"YEAR\" , ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR || Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            selectClause += "I.QUARTER, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO || Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            selectClause += "I.SEMI_ANNUAL, ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1 || "annually".equalsIgnoreCase(projSelDTO.getFrequency())) {
            selectClause += "'0' as ANNUAL, ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE || Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            selectClause += "I.\"MONTH\", ";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }

        String customSql = "  ST_M_SALES_PROJECTION_MASTER B,"
                + " PROJECTION_DETAILS E , PROJECTION_MASTER F, " + viewtable + " G, RELATIONSHIP_LEVEL_DEFINITION H, \"PERIOD\" I, " + SPRCommonLogic.getCCPQuery(projSelDTO)
                + "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID "
                + getUserSessionQueryCondition(String.valueOf(projSelDTO.getUserId()), String.valueOf(projSelDTO.getSessionId()), "B")
                + getUserSessionQueryCondition(String.valueOf(projSelDTO.getUserId()), String.valueOf(projSelDTO.getSessionId()), "A")
                + "and E.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID and F.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + " and G.PROJECTION_MASTER_SID = F.PROJECTION_MASTER_SID "
                + "and G.RELATIONSHIP_LEVEL_SID = H.RELATIONSHIP_LEVEL_SID " + ccpWhereCond + "and A.PERIOD_SID = I.PERIOD_SID " + " and H.LEVEL_NO = " + projSelDTO.getLevelNo()
                + whereClause + " group by H.LEVEL_NO " + groupBy;

        historyQuery = selectClause + " sum(A.ACTUAL_SALES)as ACTUAL_SALES, 0 as PROJECTION_SALES, sum(A.ACTUAL_UNITS)as ACTUAL_UNITS, 0 as PROJECTION_UNITS,'ACT' AS Flag  from ST_M_ACTUAL_SALES A," + customSql;
        futureQuery = selectClause + " 0 as ACTUAL_SALES, sum(A.PROJECTION_SALES) as PROJECTION_SALES, 0 as ACTUAL_UNITS, sum(A.PROJECTION_UNITS) as PROJECTION_UNITS,'PROJ' AS Flag from ST_M_SALES_PROJECTION A," + customSql;

        customQuery = historyQuery + " Union " + futureQuery;

        return customQuery;
    }

    public List<SalesProjectionResultsDTO> getCustomizedSalesProjectionResultsSales(List<Object> list, ProjectionSelectionDTO projSelDTO) {
        List<SalesProjectionResultsDTO> projDtoList = new ArrayList<SalesProjectionResultsDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove("levelValue");
        SalesProjectionResultsDTO projSalesDTO = new SalesProjectionResultsDTO();
        SalesProjectionResultsDTO projUnitDTO = new SalesProjectionResultsDTO();
        projSalesDTO.setLevelNo(projSelDTO.getLevelNo());
        projSalesDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projSalesDTO.setRelationshipLevelName(CONTRACT_SALES_AT_WAC.getConstant());
        projUnitDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo());
        projUnitDTO.setRelationshipLevelName(UNIT_VOL.getConstant());
        projSalesDTO.setParent(0);
        projUnitDTO.setParent(0);
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        if (Constant.QUARTERLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = NumericConstants.FOUR;
        } else if (Constant.MONTHLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = NumericConstants.TWELVE;
        } else if (Constant.SEMIANNUALLY_SMALL.equalsIgnoreCase(projSelDTO.getFrequency())) {
            frequencyDivision = NumericConstants.TWO;
        } else {
            frequencyDivision = 1;
        }
        String salesOrUnits = projSelDTO.getSalesOrUnit();

        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                int year = Integer.valueOf(String.valueOf(obj[0]));
                int period = Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                int col = NumericConstants.TWO;
                if (Constant.SALES.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String actual = StringUtils.EMPTY + obj[col];
                    actual = getFormatValue(TWO_DECIMAL, actual, CURRENCY);
                    projSalesDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
                    String projection = StringUtils.EMPTY + obj[col + 1];
                    projection = getFormatValue(TWO_DECIMAL, projection, CURRENCY);
                    projSalesDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
                    columnList.remove(commonColumn + Constant.ACTUALS);
                    columnList.remove(commonColumn + Constant.PROJECTIONS);
                }
                if (Constant.UNITS.equalsIgnoreCase(salesOrUnits) || Constant.BOTH_SMALL.equalsIgnoreCase(salesOrUnits)) {
                    String actual = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    actual = getFormatValue(NUM_ZERO, actual, StringUtils.EMPTY);
                    projUnitDTO.addStringProperties(commonColumn + Constant.ACTUALS, actual);
                    String projection = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                    projection = getFormatValue(NUM_ZERO, projection, StringUtils.EMPTY);
                    projUnitDTO.addStringProperties(commonColumn + Constant.PROJECTIONS, projection);
                    columnList.remove(commonColumn + Constant.ACTUALS);
                    columnList.remove(commonColumn + Constant.PROJECTIONS);
                }
            }

        }
        for (String columns : columnList) {
            projSalesDTO.addStringProperties(columns, getFormatValue(TWO_DECIMAL, Constant.NULL, CURRENCY));
            projUnitDTO.addStringProperties(columns, getFormatValue(TWO_DECIMAL, Constant.NULL, StringUtils.EMPTY));
        }
        list = null;

        projDtoList.add(projSalesDTO);
        projDtoList.add(projUnitDTO);
        return projDtoList;
    }

    public String getFormatValue(DecimalFormat FORMAT, String value, String appendChar) {
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

    public static List<String> getCommonColumnHeader(int frequencyDivision, int year, int period) {
        List<String> common = new ArrayList<String>();
        String commonColumn = StringUtils.EMPTY;
        String commonHeader = StringUtils.EMPTY;
        if (frequencyDivision == 1) {
            commonColumn = StringUtils.EMPTY + year;
            commonHeader = StringUtils.EMPTY + year;
        } else if (frequencyDivision == NumericConstants.FOUR) {
            commonColumn = Constant.Q_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.Q + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWO) {
            commonColumn = Constant.S_SMALL + period + StringUtils.EMPTY + year;
            commonHeader = Constant.S + period + " " + year;
        } else if (frequencyDivision == NumericConstants.TWELVE) {
            String monthName = getMonthForInt(period - 1);
            commonColumn = monthName.toLowerCase() + year;
            commonHeader = monthName + " " + year;
        }
        common.add(commonColumn);
        common.add(commonHeader);
        return common;
    }

    public String getCCPWhereConditionQuery(String relationShipLevelDefination, String projectionDetails, String CCP) {
        String ccpWhereCond = " and " + relationShipLevelDefination + ".RELATIONSHIP_LEVEL_SID =" + CCP + ".RELATIONSHIP_LEVEL_SID and " + CCP + ".CCP_DETAILS_SID=" + projectionDetails + ".CCP_DETAILS_SID ";
        return ccpWhereCond;
    }

    public String getUserSessionQueryCondition(String userId, String sessionId, String table) {
        String user = " and " + table + ".USER_ID=" + userId + " and " + table + ".SESSION_ID=" + sessionId + " ";
        return user;
    }


    public List<SalesProjectionResultsDTO> getCustomizedProjectionPivot(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<SalesProjectionResultsDTO> projDTOList = new ArrayList<SalesProjectionResultsDTO>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        String oldcommonCol = "nothing";
        SalesProjectionResultsDTO projDTO = new SalesProjectionResultsDTO();
        boolean first = true;
        for (Object[] row : list) {

            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[0]));
            int period = Integer.valueOf(String.valueOf(row[1]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = StringUtils.EMPTY;

            if (periodList.contains(pcommonColumn)) {
                if (!oldcommonCol.equals(pcommonColumn) && !first) {
                    periodList.remove(oldcommonCol);
                    oldcommonCol = pcommonColumn;
                    projDTO = new SalesProjectionResultsDTO();
                    projDTOList.add(projDTO);
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(0);
                if (first) {
                    oldcommonCol = pcommonColumn;
                    projDTOList.add(projDTO);
                }
                first = false;
                projDTO.setLevelValue(commonHeader);
                projDTO.setRelationshipLevelName(commonHeader);
                String value = Constant.NULL;
                commonColumn = "gts";

                String flag = StringUtils.EMPTY + row[NumericConstants.SIX];
                if (Constant.ACT.equals(flag)) {
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = Constant.NULL;
                        value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                        projDTO.addStringProperties(column, value);
                    }
                } else {

                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = Constant.NULL;
                        value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                        projDTO.addStringProperties(column, value);
                    }
                }
                commonColumn = "csw";
                if (Constant.ACT.equals(flag)) {
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[NumericConstants.TWO];
                        value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                        projDTO.addStringProperties(column, value);
                    }
                } else {
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[NumericConstants.THREE];
                        value = getFormatValue(TWO_DECIMAL, value, CURRENCY);
                        projDTO.addStringProperties(column, value);
                    }
                }
                commonColumn = "uv";
                if (Constant.ACT.equals(flag)) {
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[NumericConstants.FOUR];
                        value = getFormatValue(NUM_ZERO, value, StringUtils.EMPTY);
                        projDTO.addStringProperties(column, value);
                    }
                } else {
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[NumericConstants.FIVE];
                        value = getFormatValue(NUM_ZERO, value, StringUtils.EMPTY);
                        projDTO.addStringProperties(column, value);
                    }
                }

            }

        }
        periodList.remove(oldcommonCol);
        list = null;
        for (String ob : periodList) {
            projDTO = new SalesProjectionResultsDTO();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(0);
            projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
            projDTO.setRelationshipLevelName(projSelDTO.getPeriodListMap().get(ob));
            projDTOList.add(projDTO);
        }
        return projDTOList;
    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num == NumericConstants.TWELVE) {
            num = 0;
        }
        if (num >= 0 && num <= NumericConstants.ELEVEN) {
            month = months[num];
        }
        return month;
    }
}
