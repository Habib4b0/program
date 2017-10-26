/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.app.gtnforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getCommonColumnHeader;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.CONTRACT_SALES_AT_WAC;
import static com.stpl.app.utils.Constants.LabelConstants.DASH;
import static com.stpl.app.utils.Constants.LabelConstants.GROSS_TRADE_SALES;
import static com.stpl.app.utils.Constants.LabelConstants.NET_SALES;
import static com.stpl.app.utils.Constants.LabelConstants.PERC_OF_BUSSINESS;
import static com.stpl.app.utils.Constants.LabelConstants.PERIOD;
import static com.stpl.app.utils.Constants.LabelConstants.PPA_DISCOUNT;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.SALES;
import static com.stpl.app.utils.Constants.LabelConstants.TOTAL_DISCOUNT_AMOUNT;
import static com.stpl.app.utils.Constants.LabelConstants.TOTAL_DISCOUNT_PERC;
import static com.stpl.app.utils.Constants.LabelConstants.UNITS;
import static com.stpl.app.utils.Constants.LabelConstants.UNIT_VOL;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Abhiram
 */
public class ProjectionResultsLogic {

    /**
     * The Currency Zero Decimal Places Format.
     */
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0");
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PER_TWO = new DecimalFormat("#,##0.00%");
    /**
     * The Numeric Zero Decimal Places Format.
     */
    private static final DecimalFormat NUM_ZERO = new DecimalFormat("#,##0");
    List<ProjectionResultsDTO> prjTotalDisPerDtoList = new ArrayList<ProjectionResultsDTO>();
    List<ProjectionResultsDTO> prjTotalDisDolDtoList = new ArrayList<ProjectionResultsDTO>();
    List<ProjectionResultsDTO> projectionTotalList = new ArrayList<ProjectionResultsDTO>();

    public List<ProjectionResultsDTO> getDiscountPer(ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        projSelDTO.setSales(Constant.RATE);
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n";
        if (projSelDTO.isIsTotal()) {
            query += getProjectionResultsTotalDiscountPerQuery(projSelDTO);
        } else {
            query += getProjectionResultsDiscountsPerQuery(projSelDTO);
        }
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, false);
        projDTOList.addAll(projDTOList1);

        return projDTOList;
    }

    public List<ProjectionResultsDTO> getDiscountDollar(ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n";
        if (projSelDTO.isIsTotal()) {
            query += getProjectionResultsTotalDiscountDolQuery(projSelDTO);
        } else {
            query += getProjectionResultsDiscountsQuery(projSelDTO, " order by DISCOUNTS");
        }
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList1 = getCustomizedProjectionResultsDiscount(list, projSelDTO, false);
        projDTOList.addAll(projDTOList1);

        return projDTOList;
    }

    public ProjectionResultsDTO getPPAPer(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setSales(Constant.RATE);
        ProjectionResultsDTO ppaDto = new ProjectionResultsDTO();
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsPPAPerQuery(projSelDTO);
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
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        ProjectionResultsDTO ppaDto = new ProjectionResultsDTO();
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsPPAQuery(projSelDTO);
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
            columnList.remove(Constant.GROUP);
            for (String ob : discountList) {
                ProjectionResultsDTO projDTO = new ProjectionResultsDTO();
                projDTO.setParent(0);
                projDTO.setProjectionTotal(0);
                projDTO.setGroup(ob);
                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                }
                projDTOList.add(projDTO);
            }
        } else {
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
                if (discountList.contains(StringUtils.EMPTY + discountRow[NumericConstants.TWO])) {
                    forword = true;
                } else {
                    if (!discountName.contains(Constant.TOTAL_SMALL)) {
                        forword = false;
                    }
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
                        String column = StringUtils.EMPTY;
                        int year = Integer.valueOf(String.valueOf(discountRow[0]));
                        int period = Integer.valueOf(String.valueOf(discountRow[1]));
                        List<String> common = getCommonColumnHeader(projSelDTO.getFrequencyDivision(), year, period);
                        String commonColumn = common.get(0);
                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            String value = StringUtils.EMPTY + discountRow[NumericConstants.THREE];
                            if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                                value = getFormattedValue(CUR_ZERO, value);
                            } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                                value = getFormattedValue(PER_TWO, value);
                            }
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            String value = StringUtils.EMPTY + discountRow[NumericConstants.FOUR];
                            if (projSelDTO.getSales().contains(Constant.SALES_WHOLE_CAPS)) {
                                value = getFormattedValue(CUR_ZERO, value);
                            } else if (projSelDTO.getSales().contains(Constant.RATE)) {
                                value = getFormattedValue(PER_TWO, value);
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
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                int year = Integer.valueOf(String.valueOf(obj[0]));
                int period = Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                int col = NumericConstants.TWO;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col];
                    value = getFormattedValue(CUR_ZERO, value);
                    projSalesDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    value = getFormattedValue(NUM_ZERO, value);
                    projUnitDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[col + 1];
                    value = getFormattedValue(CUR_ZERO, value);
                    projSalesDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
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
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsSalesQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList = getCustomizedProjectionResultsSales(list, projSelDTO);
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getProjectionPivot(ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        String gtsListQuery = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsPivotQuery(projSelDTO);
        List<Object> gtsList = (List<Object>) CommonLogic.executeSelectQuery(gtsListQuery, null, null);
        String discountListQuery = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsDiscountsPivotQuery(projSelDTO);
        List<Object> discountList = (List<Object>) CommonLogic.executeSelectQuery(discountListQuery, null, null);
        projDTOList = getCustomizedProjectionPivot(gtsList, discountList, projSelDTO);
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getCustomizedProjectionPivot(List<Object> list, List<Object> discountList, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        int discountIndex = 0;
        int col = NumericConstants.TWO;
        int dcol = NumericConstants.TWO;
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
                commonColumn = "gts";
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
                commonColumn = "perOfBus";
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
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 1];
                    value = getFormattedValue(CUR_ZERO, value);
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
                commonColumn = Constant.totDisPer;
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
                commonColumn = Constant.totDisDol;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.SIX];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.SEVEN];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netSales";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.EIGHT];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.NINE];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                if (projSelDTO.isPpa()) {
                    commonColumn = Constant.totDisDol + PPA_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY);
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[col + NumericConstants.TEN];
                        value = getFormattedValue(CUR_ZERO, value);
                        projDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[col + NumericConstants.ELEVEN];
                        value = getFormattedValue(CUR_ZERO, value);
                        projDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                    commonColumn = Constant.totDisPer + PPA_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY);
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[col + NumericConstants.TWELVE];
                        value = getFormattedValue(PER_TWO, value);
                        projDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[col + NumericConstants.THIRTEEN];
                        value = getFormattedValue(PER_TWO, value);
                        projDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                }
                boolean start = false;
                for (int i = discountIndex; i < discountList.size(); i++) {
                    discountIndex = i;
                    Object[] discountRow = (Object[]) discountList.get(i);
                    int dyear = Integer.valueOf(String.valueOf(discountRow[dcol - 1]));
                    int dperiod = Integer.valueOf(String.valueOf(discountRow[1]));
                    List<String> dcommon = getCommonColumnHeader(frequencyDivision, dyear, dperiod);
                    String dcommonColumn = dcommon.get(0);
                    if (pcommonColumn.equals(dcommonColumn)) {
                        start = true;
                        String head = String.valueOf(discountRow[dcol]).replace(" ", StringUtils.EMPTY);
                        String commonColumn1 = Constant.totDisDol + head;
                        String commonColumn2 = Constant.totDisPer + head;
                        column = commonColumn1 + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 1];
                            value = getFormattedValue(CUR_ZERO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = commonColumn1 + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.TWO];
                            value = getFormattedValue(CUR_ZERO, value);
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

                    } else if (start) {
                        break;
                    }
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(0);
                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
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
                projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
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
            Object[] orderedArgs1 = {orderedArgs[0], orderedArgs[1], orderedArgs[NumericConstants.TWO], orderedArgs[NumericConstants.THREE], orderedArgs[NumericConstants.FOUR], orderedArgs[NumericConstants.FIVE], 0};
             List<Object[]> discountsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS_DISCOUNT", orderedArgs1);
            getCustomizedProjectionPivotTotal(gtsList, discountsList, projSelDTO);
        }
        return projectionTotalList;
    }

    public void getCustomizedProjectionPivotTotal(List<Object[]> list, List<Object[]> discountList, ProjectionSelectionDTO projSelDTO) {
        projectionTotalList.clear();
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        int discountIndex = 0;
        int col = NumericConstants.FIVE;
        int dcol = NumericConstants.TWO;
        if (frequencyDivision != 1) {
            col = col + 1;
            dcol = dcol + 1;
        }
        for (Object[] row : list) {

            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[col - 1]));
            int period = Integer.valueOf(String.valueOf(row[NumericConstants.FOUR]));
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
                commonColumn = "gts";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[1];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[NumericConstants.TWO];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "perOfBus";
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
                commonColumn = "conSalesWac";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 1];
                    value = getFormattedValue(CUR_ZERO, value);
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
                commonColumn = Constant.totDisPer;
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
                commonColumn = Constant.totDisDol;
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FOUR];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FIVE];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                commonColumn = "netSales";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FOURTEEN];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.FIFTEEN];
                    value = getFormattedValue(CUR_ZERO, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                if (projSelDTO.isPpa()) {
                    commonColumn = Constant.totDisDol + PPA_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY);
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[col + NumericConstants.TEN];
                        value = getFormattedValue(CUR_ZERO, value);
                        projDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[col + NumericConstants.ELEVEN];
                        value = getFormattedValue(CUR_ZERO, value);
                        projDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                    commonColumn = Constant.totDisPer + PPA_DISCOUNT.getConstant().replace(" ", StringUtils.EMPTY);
                    column = commonColumn + ACTUALS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[col + NumericConstants.TWELVE];
                        value = getFormattedValue(PER_TWO, value);
                        projDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        value = StringUtils.EMPTY + row[col + NumericConstants.THIRTEEN];
                        value = getFormattedValue(PER_TWO, value);
                        projDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                }
                boolean start = false;
                for (int i = discountIndex; i < discountList.size(); i++) {
                    discountIndex = i;
                    Object[] discountRow = discountList.get(i);
                    int dyear = Integer.valueOf(String.valueOf(discountRow[dcol - 1]));
                    int dperiod = Integer.valueOf(String.valueOf(discountRow[1]));
                    List<String> dcommon = getCommonColumnHeader(frequencyDivision, dyear, dperiod);
                    String dcommonColumn = dcommon.get(0);
                    if (pcommonColumn.equals(dcommonColumn)) {
                        start = true;
                        String head = String.valueOf(discountRow[dcol]).replace(" ", StringUtils.EMPTY);
                        String commonColumn1 = Constant.totDisDol + head;
                        String commonColumn2 = Constant.totDisPer + head;
                        column = commonColumn1 + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + 1];
                            value = getFormattedValue(CUR_ZERO, value);
                            projDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                        column = commonColumn1 + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            value = StringUtils.EMPTY + discountRow[dcol + NumericConstants.TWO];
                            value = getFormattedValue(CUR_ZERO, value);
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

                    } else if (start) {
                        break;
                    }
                }
                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
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
                projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
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
                newValue = newValue / NumericConstants.HUNDRED;
            }
            value = FORMAT.format(newValue);
        }
        return value;
    }

    public void getProjectionTotal(Object[] orderedArgs, ProjectionSelectionDTO projSelDTO) {
        List<Object[]> gtsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS", orderedArgs);
        if (gtsList != null) {
            getCustomizedProjectionTotal(gtsList, projSelDTO);
        }
    }

    public void getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        ProjectionResultsDTO gtsDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO perDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO conSaleDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO unitVolDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO discountPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO discountDolDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO netSaleDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO ppaPerDTO = new ProjectionResultsDTO();
        ProjectionResultsDTO ppaDolDTO = new ProjectionResultsDTO();
        gtsDTO.setParent(0);
        gtsDTO.setGroup(GROSS_TRADE_SALES.getConstant());

        perDTO.setParent(0);
        perDTO.setGroup(PERC_OF_BUSSINESS.getConstant());

        conSaleDTO.setParent(0);
        conSaleDTO.setGroup(CONTRACT_SALES_AT_WAC.getConstant());

        unitVolDTO.setParent(0);
        unitVolDTO.setGroup(UNIT_VOL.getConstant());

        discountPerDTO.setParent(1);
        discountPerDTO.setGroup(TOTAL_DISCOUNT_PERC.getConstant());
        discountPerDTO.setProjectionTotal(1);
        discountPerDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
        discountPerDTO.setOnExpandTotalRow(0);

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

            ppaDolDTO.setParent(0);
            ppaDolDTO.setGroup(PPA_DISCOUNT.getConstant());
        }
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            int col = NumericConstants.FIVE;
            if (frequencyDivision != 1) {
                col = col + 1;
            }
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;

                int year = Integer.valueOf(String.valueOf(obj[col - 1]));
                int period = Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);

                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[1];
                    value = getFormattedValue(CUR_ZERO, value);
                    gtsDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col];
                    value = getFormattedValue(CUR_ZERO, value);
                    conSaleDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                    value = getFormattedValue(NUM_ZERO, value);
                    unitVolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FOUR];
                    value = getFormattedValue(CUR_ZERO, value);
                    discountDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.SIX];
                    value = getFormattedValue(PER_TWO, value);
                    discountPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.EIGHT];
                    value = getFormattedValue(PER_TWO, value);
                    perDTO.addStringProperties(column, value);
                    if (projSelDTO.isPpa()) {
                        value = StringUtils.EMPTY + obj[col + NumericConstants.TEN];
                        value = getFormattedValue(CUR_ZERO, value);
                        ppaDolDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.TWELVE];
                        value = getFormattedValue(PER_TWO, value);
                        ppaPerDTO.addStringProperties(column, value);
                    }
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FOURTEEN];
                    value = getFormattedValue(CUR_ZERO, value);
                    netSaleDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    String value = StringUtils.EMPTY + obj[NumericConstants.TWO];
                    value = getFormattedValue(CUR_ZERO, value);
                    gtsDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + 1];
                    value = getFormattedValue(CUR_ZERO, value);
                    conSaleDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                    value = getFormattedValue(NUM_ZERO, value);
                    unitVolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FIVE];
                    value = getFormattedValue(CUR_ZERO, value);
                    discountDolDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.SEVEN];
                    value = getFormattedValue(PER_TWO, value);
                    discountPerDTO.addStringProperties(column, value);
                    value = StringUtils.EMPTY + obj[col + NumericConstants.NINE];
                    value = getFormattedValue(PER_TWO, value);
                    perDTO.addStringProperties(column, value);
                    if (projSelDTO.isPpa()) {
                        value = StringUtils.EMPTY + obj[col + NumericConstants.ELEVEN];
                        value = getFormattedValue(CUR_ZERO, value);
                        ppaDolDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.THIRTEEN];
                        value = getFormattedValue(PER_TWO, value);
                        ppaPerDTO.addStringProperties(column, value);
                    }
                    value = StringUtils.EMPTY + obj[col + NumericConstants.FIFTEEN];
                    value = getFormattedValue(CUR_ZERO, value);
                    netSaleDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
            }

        }
        for (String columns : columnList) {
            gtsDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            perDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            conSaleDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            unitVolDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            discountPerDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            discountDolDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            netSaleDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            if (projSelDTO.isPpa()) {
                ppaPerDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                ppaDolDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            }
        }
        projDTOList.add(gtsDTO);
        projDTOList.add(perDTO);
        projDTOList.add(conSaleDTO);
        projDTOList.add(unitVolDTO);
        projDTOList.add(discountPerDTO);
        projDTOList.add(discountDolDTO);
        projDTOList.add(netSaleDTO);
        if (projSelDTO.isPpa()) {
            projDTOList.add(ppaPerDTO);
            projDTOList.add(ppaDolDTO);
        }
        projectionTotalList.addAll(projDTOList);
    }

    public List<ProjectionResultsDTO> getProjectionTotalDiscounts(ProjectionSelectionDTO projSelDTO, Object[] orderedArgs) {
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        if (prjTotalDisDolDtoList.isEmpty() || prjTotalDisPerDtoList.isEmpty()) {
            Object[] orderedArgs1 =  {orderedArgs[0], orderedArgs[1], orderedArgs[NumericConstants.TWO], orderedArgs[NumericConstants.THREE], orderedArgs[NumericConstants.FOUR], orderedArgs[NumericConstants.FIVE], 1};
           List<Object[]> gtsList = CommonLogic.callProcedure("PRC_PROJECTION_RESULTS_DISCOUNT", orderedArgs1);
           
            getCustomizedDiscountsProjectionTotal(gtsList, projSelDTO);
        }
        if (projSelDTO.getGroup().contains("$")) {
            projDTOList = new ArrayList<ProjectionResultsDTO>(prjTotalDisDolDtoList);
        } else if (projSelDTO.getGroup().contains(Constant.PERCENT)) {
            projDTOList = new ArrayList<ProjectionResultsDTO>(prjTotalDisPerDtoList);
        }
        return projDTOList;
    }

    public void getCustomizedDiscountsProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<ProjectionResultsDTO> projDolDTOList = new ArrayList<ProjectionResultsDTO>();
        List<ProjectionResultsDTO> projPerDTOList = new ArrayList<ProjectionResultsDTO>();
        List<String> discountList = new ArrayList<String>(projSelDTO.getDiscountNameList());
        String oldDiscountName = "old";
        String newDiscountName = "oldDiscountName";
        if (list != null && !list.isEmpty()) {
            ProjectionResultsDTO projDolDTO = new ProjectionResultsDTO();
            ProjectionResultsDTO projPerDTO = new ProjectionResultsDTO();
            int col = NumericConstants.TWO;
            if (frequencyDivision != 1) {
                col = col + 1;
            }
            boolean add = false;
            List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
            columnList.remove(Constant.GROUP);
            for (int i = 0; i < list.size(); i++) {
                final Object[] obj = (Object[]) list.get(i);
                String column = StringUtils.EMPTY;
                int year = Integer.valueOf(String.valueOf(obj[col - 1]));
                int period = Integer.valueOf(String.valueOf(obj[1]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                oldDiscountName = newDiscountName;
                newDiscountName = StringUtils.EMPTY + obj[col];
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
                        projPerDTO.setGroup(newDiscountName);
                        projDolDTOList.add(projDolDTO);
                        projPerDTOList.add(projPerDTO);
                        for (String columns : columnList) {
                            projDolDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
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
                        value = getFormattedValue(CUR_ZERO, value);
                        projDolDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.THREE];
                        value = getFormattedValue(PER_TWO, value);
                        projPerDTO.addStringProperties(column, value);
                        columnList.remove(column);
                    }
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        String value = StringUtils.EMPTY + obj[col + NumericConstants.TWO];
                        value = getFormattedValue(CUR_ZERO, value);
                        projDolDTO.addStringProperties(column, value);
                        value = StringUtils.EMPTY + obj[col + NumericConstants.FOUR];
                        value = getFormattedValue(PER_TWO, value);
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
            ProjectionResultsDTO projDTO1 = new ProjectionResultsDTO();
            projDTO1.setParent(0);
            projDTO1.setProjectionTotal(1);
            projDTO1.setGroup(ob);
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                projDTO1.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            }
            projDolDTOList.add(projDTO);
            projPerDTOList.add(projDTO1);
        }
        prjTotalDisDolDtoList = new ArrayList<ProjectionResultsDTO>(projDolDTOList);
        prjTotalDisPerDtoList = new ArrayList<ProjectionResultsDTO>(projPerDTOList);
    }

    public ProjectionResultsDTO getNetSales(ProjectionSelectionDTO projSelDTO) {
        ProjectionResultsDTO netSalesDto = new ProjectionResultsDTO();
        projSelDTO.setSales(Constant.SALES_WHOLE_CAPS);
        String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getProjectionResultsNetSalesQuery(projSelDTO);
        List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
        List<ProjectionResultsDTO> projDTOList = getCustomizedProjectionResultsDiscount(list, projSelDTO, false);
        if (projDTOList != null && !projDTOList.isEmpty()) {
            netSalesDto = projDTOList.get(0);
        }
        netSalesDto.setGroup("Net Sales");
        netSalesDto.setParent(0);
        return netSalesDto;
    }

    public List<ProjectionResultsDTO> getProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int started = start;
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<ProjectionResultsDTO> projDTOList = new ArrayList<ProjectionResultsDTO>();
        String discList = CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false);
        String freq = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 1) {
            freq = "ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            freq = "SEMI-ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            freq = "MONTHLY";
        }

        Object[] orderedArgs = {projSelDTO.getProjectionId(), freq, discList, "ASSUMPTIONS", projSelDTO.getSessionDTO().getSessionId(), projSelDTO.getUserId()};
        if (!projSelDTO.getGroup().startsWith(Constant.All)
                && !projSelDTO.getGroup().contains(Constant.Sales)
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
                        mayBeAdded += NumericConstants.TWO;
                        if (projectionTotalList.isEmpty()) {
                            getProjectionTotal(orderedArgs, projSelDTO);
                        }
                        if (started == 1 && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO gtsDto = projectionTotalList.get(0);
                                projDTOList.add(gtsDto);
                            }
                            started++;
                            neededRecord--;
                        }
                        if (started == NumericConstants.TWO && neededRecord > 0) {
                            if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                ProjectionResultsDTO percentDto = projectionTotalList.get(1);
                                projDTOList.add(percentDto);
                            }
                            started++;
                            neededRecord--;
                        }

                        if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(SALES.getConstant()))) {
                            if (started == NumericConstants.THREE) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO contractSalesDto = projectionTotalList.get(NumericConstants.TWO);
                                    projDTOList.add(contractSalesDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && (salesUnits.equals(BOTH.getConstant()) || salesUnits.equals(UNITS.getConstant()))) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.FOUR) || started == NumericConstants.THREE) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO unitVolDto = projectionTotalList.get(NumericConstants.THREE);
                                    projDTOList.add(unitVolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains("$")) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.FIVE) || started == NumericConstants.FOUR) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO discountPerDto = projectionTotalList.get(NumericConstants.FOUR);
                                    projDTOList.add(discountPerDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(Constant.PERCENT)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.SIX) || started == NumericConstants.FIVE) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO discountDolDto = projectionTotalList.get(NumericConstants.FIVE);
                                    projDTOList.add(discountDolDto);
                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.SEVEN) || started == NumericConstants.SIX) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO netSalesDto = projectionTotalList.get(NumericConstants.SIX);
                                    projDTOList.add(netSalesDto);
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
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains("$")) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.TWO) || started == 1) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    List<ProjectionResultsDTO> discountPerDtoList = getDiscountPer(projSelDTO);
                                    projDTOList.addAll(discountPerDtoList);

                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0 && !projSelDTO.getGroup().contains(Constant.PERCENT)) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.THREE) || started == NumericConstants.TWO) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    List<ProjectionResultsDTO> discountDolarDtoList = getDiscountDollar(projSelDTO);
                                    projDTOList.addAll(discountDolarDtoList);

                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                        if (neededRecord > 0) {
                            if ((salesUnits.equals(BOTH.getConstant()) && started == NumericConstants.FOUR) || started == NumericConstants.THREE) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                    ProjectionResultsDTO netSalesDto = getNetSales(projSelDTO);
                                    projDTOList.add(netSalesDto);

                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                    }
                } else if (neededRecord > 0) {
                    if (!projSelDTO.getGroup().contains("$")) {
                        if (started < projSelDTO.getDiscountNameList().size()) {
                            List<ProjectionResultsDTO> discountPerDtoList = new ArrayList<ProjectionResultsDTO>();
                            if (projSelDTO.isIsProjectionTotal()) {
                                discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs);
                            } else {
                                discountPerDtoList = getDiscountPer(projSelDTO);
                            }
                            for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; k++) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                                    projDTOList.add(discountPerDtoList.get(k));
                                }
                                started++;
                                neededRecord--;
                            }
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                        if (neededRecord > 0) {
                            if (projSelDTO.isPpa()) {
                                if (projSelDTO.isIsProjectionTotal()) {
                                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                        if (projectionTotalList.isEmpty()) {
                                            getProjectionTotal(orderedArgs, projSelDTO);
                                        }
                                        projDTOList.add(projectionTotalList.get(NumericConstants.SEVEN));
                                    }

                                } else {
                                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                        ProjectionResultsDTO ppaDTO = getPPAPer(projSelDTO);
                                        projDTOList.add(ppaDTO);
                                    }

                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                    }
                    if (neededRecord > 0 && !projSelDTO.getGroup().contains(Constant.PERCENT)) {
                        if (started < projSelDTO.getDiscountNameList().size()) {
                            List<ProjectionResultsDTO> discountPerDtoList = new ArrayList<ProjectionResultsDTO>();
                            if (projSelDTO.isIsProjectionTotal()) {
                                discountPerDtoList = getProjectionTotalDiscounts(projSelDTO, orderedArgs);
                            } else {
                                discountPerDtoList = getDiscountDollar(projSelDTO);
                            }
                            for (int k = started; k < discountPerDtoList.size() && neededRecord > 0; k++) {
                                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                                    projDTOList.add(discountPerDtoList.get(k));
                                }
                                started++;
                                neededRecord--;
                            }
                        }
                        mayBeAdded = mayBeAdded + projSelDTO.getDiscountNameList().size();
                        if (neededRecord > 0) {
                            if (projSelDTO.isPpa()) {
                                if (projSelDTO.isIsProjectionTotal()) {
                                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                        if (projectionTotalList.isEmpty()) {
                                            getProjectionTotal(orderedArgs, projSelDTO);
                                        }
                                        projDTOList.add(projectionTotalList.get(NumericConstants.EIGHT));
                                    }

                                } else {
                                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                                        ProjectionResultsDTO ppaDTO = getPPADollar(projSelDTO);
                                        projDTOList.add(ppaDTO);
                                    }

                                }
                                started++;
                                neededRecord--;
                            }
                            mayBeAdded++;
                        }
                    }

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
                if ((!(projSelDTO.isIsCustomHierarchy() && projSelDTO.getCustomId() == 0)) && ((neededRecord > 0 && projSelDTO.isIsTotal() && !projSelDTO.isIsFilter()) && ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                        && ((projSelDTO.isIsCustomHierarchy()) || (!projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
                        && !projSelDTO.getGroup().startsWith(Constant.All)
                        && !projSelDTO.getGroup().contains(Constant.Sales)
                        && !projSelDTO.getGroup().contains(Constant.DISCOUNT)
                        && !projSelDTO.getGroup().contains(Constant.PPA)))) {

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
        return projDTOList;
    }

    public List<ProjectionResultsDTO> getConfiguredProjectionResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
        List<ProjectionResultsDTO> resultList = new ArrayList<ProjectionResultsDTO>();
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
            resultList = getProjectionResults(start, offset, projSelDTO);
        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            resultList = configureLevels(start, offset, start, projSelDTO);
        }
        return resultList;
    }

    public List<ProjectionResultsDTO> configureLevels(int start, int offset, int started, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        List<ProjectionResultsDTO> resultList = new ArrayList<ProjectionResultsDTO>();
        if (neededRecord > 0) {
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), StringUtils.EMPTY, start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true, projSelDTO.getDiscountNoList(),projSelDTO);
            for (int i = 0; i < levelList.size() && neededRecord > 0; i++) {
                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + (started + i))) {

                    Leveldto levelDto = levelList.get(i);
                    ProjectionResultsDTO dto = new ProjectionResultsDTO();
                    dto.setLevelNo(levelDto.getLevelNo());
                    dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                    dto.setParentNode(levelDto.getParentNode());
                    dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setLevelValue(levelDto.getRelationshipLevelValue());
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
                if (projSelDTO.isIsTotal()) {
                    count = count + NumericConstants.FOUR;
                    if (projSelDTO.isIsProjectionTotal()) {
                        count = count + NumericConstants.THREE;
                    }
                    if (projSelDTO.getSalesOrUnit().equals(BOTH.getConstant())) {
                        count++;
                    }
                }
                if (!projSelDTO.isIsTotal()) {
                    count = count + projSelDTO.getDiscountNoList().size();
                    if (projSelDTO.isPpa()) {
                        count++;
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
        } else {
            if (projSelDTO.isIsTotal() && isLevelsCount && !projSelDTO.isIsFilter()) {
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
        }
        return count;
    }

    public int configureLevelsCount(ProjectionSelectionDTO projSelDTO) {
        return CommonLogic.getLevelListCount(projSelDTO.getProjectionId(), StringUtils.EMPTY, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), projSelDTO.getDiscountNoList(),projSelDTO);
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
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS,\n ";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
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
            whereClause += " and B.RS_CONTRACT_SID in (" + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + ") \n";
        }
        selectClause += discountTypeColumnName + ", ";

        String customSql = "  ST_NM_DISCOUNT_PROJ_MASTER B,\n"
                + " PROJECTION_DETAILS E , \n"
                + "\"PERIOD\" I, \n"
                + " @CCP CCP ";
        if (!projSelDTO.isIsTotal()) {
            customSql += ", RS_CONTRACT J \n";
        }
        customSql += "where A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID \n"
                + " and B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B")
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A");
        if (!projSelDTO.isIsTotal()) {
            customSql += "and B.RS_CONTRACT_SID= J.RS_CONTRACT_SID \n";
        }
        customSql += "and A.RS_CONTRACT_SID = B.RS_CONTRACT_SID \n"
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
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
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
                + " sum(A.ACTUAL_PROJECTION_SALES) as PROJECTION_SALES \n"
                + " from ST_NM_ACTUAL_PPA A,\n "
                + customSql;
        String futureQuery = selectClause + " 0 as ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_DISCOUNT_DOLLAR) as PROJECTION_SALES \n"
                + " from ST_NM_PPA_PROJECTION A,\n "
                + customSql;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String finalSelectClause = "select " + list.get(0) + "'PPA Discount' as DISCOUNTS,\n Isnull(HISTORY.ACTUAL_SALES, FUTURE.ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.PROJECTION_SALES, HISTORY.PROJECTION_SALES) as PROJECTION_SALES ";

        customQuery = finalSelectClause + " from (\n" + historyQuery + "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond;

        return customQuery;
    }

    public String getProjectionResultsSalesQuery(ProjectionSelectionDTO projSelDTO) {

        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = " I.\"YEAR\"\n";
        String customQuery = StringUtils.EMPTY;
        String ccpWhereCond = " and CCP.CCP_DETAILS_SID=E.CCP_DETAILS_SID \n";
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
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
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause(Constant.PPA_SMALL, "SALE", "on");
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
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS LEFT JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond + "\n order by TODIS.DISCOUNTS";
        return customQuery;
    }

    public String getProjectionResultsTotalDiscountPerQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", Constant.PPA_SMALL, "on", projSelDTO.isPpa());
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
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        return customQuery;
    }

    public String getProjectionResultsNetSalesQuery(ProjectionSelectionDTO projSelDTO) {
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", Constant.PPA_SMALL, "on", projSelDTO.isPpa());
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
        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
        return customQuery;
    }

    public String getProjectionResultsPivotQuery(ProjectionSelectionDTO projSelDTO) {
        projSelDTO.setIsTotal(true);
        String selectClause = " select ";
        String customQuery = StringUtils.EMPTY;
        String ppa_actuals = StringUtils.EMPTY;
        String ppa_projection = StringUtils.EMPTY;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("TODIS", "SALE", Constant.PPA_SMALL, "on", projSelDTO.isPpa());
        selectClause += list.get(0);
        String finalWhereCond = list.get(1);
        String orderBy = list.get(NumericConstants.THREE);
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
                + ", NET_PROJECTION_SALES=(Isnull(SALE.SALES_PROJECTION_SALES, 0)-(Isnull(TODIS.PROJECTION_SALES, 0)" + ppa_projection + ")) \n";
        if (projSelDTO.isPpa()) {
            selectClause += ", PPA.ACTUAL_SALES AS PPA_ACTUAL_SALES \n"
                    + ", PPA.PROJECTION_SALES AS PPA_PROJECTION_SALES \n"
                    + ", PPA_ACTUAL_RATE=Isnull(PPA.ACTUAL_SALES / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 \n"
                    + ", PPA_PROJECTION_RATE=Isnull(PPA.PROJECTION_SALES / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100  \n";
        }

        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        String ppaQuery = getProjectionResultsPPAQuery(projSelDTO);
        customQuery = selectClause + " from (\n" + totalDiscountQuery + "\n) TODIS FULL OUTER JOIN (\n" + salesQuery + "\n) SALE \n" + finalWhereCond;
        if (projSelDTO.isPpa()) {
            String finalWhereCond1 = list.get(NumericConstants.TWO);
            customQuery += " FULL OUTER JOIN (\n" + ppaQuery + "\n) PPA \n" + finalWhereCond1;
        }
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
        String orderBy = list.get(NumericConstants.TWO);
        selectClause += " TODIS.DISCOUNTS \n"
                + ", TODIS.ACTUAL_SALES as DOL_ACTUAL_SALES \n"
                + ", TODIS.PROJECTION_SALES as DOL_PROJECTION_SALES \n"
                + ", PER_ACTUAL_SALES=Isnull(Isnull(TODIS.ACTUAL_SALES, 0) / NULLIF(SALE.SALES_ACTUAL_SALES, 0), 0) * 100 \n"
                + ", PER_PROJECTION_SALES=Isnull(Isnull(TODIS.PROJECTION_SALES, 0) / NULLIF(SALE.SALES_PROJECTION_SALES, 0), 0) * 100  \n";

        String totalDiscountQuery = getProjectionResultsDiscountsQuery(projSelDTO, StringUtils.EMPTY);
        String salesQuery = getProjectionResultsSalesQuery(projSelDTO);
        customQuery = selectClause + " from (" + totalDiscountQuery + ") TODIS LEFT JOIN (" + salesQuery + ") SALE " + finalWhereCond + " order by " + orderBy;
        projSelDTO.setIsTotal(true);
        return customQuery;
    }
}
