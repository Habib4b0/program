/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic;

import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.PhsQueryUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.SUCCESS;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.IndicatorConstants.ACTUALS_CAPS;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.IndicatorConstants.PROJ_CAPS;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils.getCommonColumnHeader;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Nadhiya
 */
public class PhsResultsLogic {

    public static final Logger LOGGER = Logger.getLogger(PhsResultsLogic.class);
    /**
     * The Currency Zero Decimal Places Format.
     */
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat CUR_TWO = new DecimalFormat("$#,##0.00");
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PER_TWO = new DecimalFormat("#0.00%");
    /**
     * The Numeric Unit Decimal Places Format.
     */
    private static final DecimalFormat UNITS = new DecimalFormat("#0.00");
    private static final String DATASOURCECONTEXT = "java:jboss/datasources/jdbc/appDataPool";

    public static final String ADJUSTMENT = "Adjustment ";
    public static final String OVERRIDE = "Override ";
    public static final String HISTORICAL = "Historical ";
    public static final String FORECAST = "Forecast ";
    public static final String PHS_PRICE = "PHS_PRICE";

    private final PhsQueryUtils queryUtil = new PhsQueryUtils();

    public List<TableDTO> getConfiguredPhsResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        List<TableDTO> resultList;
        if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant()) || projSelDTO.getActualsOrProjections().equals(ACTUALS.getConstant())) {
            projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
        }
        if (parentId instanceof TableDTO) {
            TableDTO parentDto = (TableDTO) parentId;
            projSelDTO.setLevelValue(parentDto.getLevelValue());
            projSelDTO.setParentNode(parentDto.getParentNode());
            projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
            projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
            projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
            projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
            projSelDTO.setGroup(parentDto.getPriceType());
            projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);

            int parentSid = parentDto.getItemMasterSid();
            resultList = getPhsChildren(start, offset, projSelDTO, parentSid, session);
        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);
            projSelDTO.setPageOffSet(offset);
            projSelDTO.setPageStart(start);
            resultList = getPhsResults(projSelDTO);

        }

        return resultList;
    }

    public List<TableDTO> getPhsChildren(int start, int offset, ProjectionSelectionDTO projSelDTO, int parentSid, SessionDTO session) {
        LOGGER.debug("getPhsChildren started");
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getPhsChild(projSelDTO, parentSid, session);
            for (int k = started; k < resultList.size() && neededRecord > 0; k++, neededRecord--) {
                projDTOList.add(resultList.get(k));
            }
        }
        LOGGER.debug("getPhsChildren ends");
        return projDTOList;
    }

    public List<TableDTO> getPhsChild(ProjectionSelectionDTO projSelDTO, int parentSid, SessionDTO session) {
        LOGGER.debug("getPhsChild method started ");
        List<TableDTO> projDTOList = new ArrayList<>();
        try {
            List<Object[]> phsList;
            if (projSelDTO.getVariables().contains(PERCENTAGE.getConstant())) {
                phsList = queryUtil.loadPhsResultsChild(session, parentSid, projSelDTO.getPriceTypeList(), true);
            } else {
                phsList = queryUtil.loadPhsResultsChild(session, parentSid, projSelDTO.getPriceTypeList(), false);
            }
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                projDTOList = getCustPhsChild(phsList, projSelDTO);
            } else {
                projDTOList = getCustomizedPriceTypeChild(phsList, projSelDTO);
            }
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
        }
        LOGGER.debug("getPhsChild method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustPhsChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        List<TableDTO> projDTOList = new ArrayList<>();
        boolean wac = false;
        boolean phs = false;
        boolean totalURA = false;
        boolean phsDiscount = false;
        boolean amp = false;

        List<String> priceList = projSelDTO.getPriceTypeList();
        try {
            for (int i = 0; i < priceList.size(); i++) {
                if (WAC.getConstant().equalsIgnoreCase(priceList.get(i))) {
                    wac = true;
                }
                if (PHS.getConstant().equalsIgnoreCase(priceList.get(i))) {
                    phs = true;
                }
                if (PHS_DISCOUNT.getConstant().equalsIgnoreCase(priceList.get(i))) {
                    phsDiscount = true;
                }
                if (TOTAL_URA.getConstant().equalsIgnoreCase(priceList.get(i))) {
                    totalURA = true;
                }
                if (AMP.getConstant().equalsIgnoreCase(priceList.get(i))) {
                    amp = true;
                }

            }
            getCustPhsChildCustomization(wac, list, projSelDTO, projDTOList, phs, phsDiscount, totalURA, amp);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return projDTOList;
    }

    public void getCustPhsChildCustomization(boolean wac, List<Object[]> list, ProjectionSelectionDTO projSelDTO, List<TableDTO> projDTOList, boolean phs, boolean phsDiscount, boolean totalURA, boolean amp) {
        Map<String, String> priceTypeList = projSelDTO.getLoadPhsPriceMap();
        if (wac) {
            TableDTO phsDTO = new TableDTO();
            phsDTO.setGroup(WAC.getConstant());
            List<TableDTO> wacList = getCustomizedPhsChild(list, projSelDTO, WAC.getConstant(), phsDTO);
            projDTOList.addAll(wacList);
        }
        if (phs) {
            TableDTO phsDTO = new TableDTO();
            phsDTO.setGroup(PHS.getConstant());
            List<TableDTO> phsList = getCustomizedPhsChild(list, projSelDTO, PHS.getConstant(), phsDTO);
            projDTOList.addAll(phsList);
        }
        if (phsDiscount) {
            TableDTO phsDTO = new TableDTO();
            phsDTO.setGroup(PHS_DISCOUNT.getConstant());
            List<TableDTO> phsDiscountList = getCustomizedPhsChild(list, projSelDTO, PHS_DISCOUNT.getConstant(), phsDTO);
            projDTOList.addAll(phsDiscountList);
        }
        if (totalURA) {
            TableDTO phsDTO = new TableDTO();
            phsDTO.setGroup(CommonUtils.getGroupName(priceTypeList.get(Constant.PHS_TOTAL_URA)));
            List<TableDTO> uraList = getCustomizedPhsChild(list, projSelDTO, TOTAL_URA.getConstant(), phsDTO);
            projDTOList.addAll(uraList);
        }
        if (amp) {
            TableDTO phsDTO = new TableDTO();
            phsDTO.setGroup(CommonUtils.getGroupName(priceTypeList.get(Constant.PHS_AMP)));
            List<TableDTO> ampList = getCustomizedPhsChild(list, projSelDTO, AMP.getConstant(), phsDTO);
            projDTOList.addAll(ampList);
        }
    }

    public List<TableDTO> getPhsResults(ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("getPhsResults started");
        List<TableDTO> projDTOList = getPhs(projSelDTO);
        LOGGER.debug("getPhsResults ends");
        return projDTOList;
    }

    public int getConfiguredPhsResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO) {
        int count = 0;

        if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant()) || projSelDTO.getActualsOrProjections().equals(ACTUALS.getConstant())) {
            projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
        }
        if (parentId instanceof TableDTO) {
            TableDTO parentDto = (TableDTO) parentId;
            projSelDTO.setLevelValue(parentDto.getLevelValue());
            projSelDTO.setParentNode(parentDto.getParentNode());
            projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
            projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
            projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
            projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
            projSelDTO.setGroup(parentDto.getGroup());
            projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
            count += getPhsCount(projSelDTO);
        } else {
            try {
                count = getPhsParentCount(projSelDTO);
            } catch (PortalException | SystemException ex) {
                LOGGER.error(ex);
            }

        }
        return count;
    }

    public int getPhsParentCount(ProjectionSelectionDTO projSelDTO) throws SystemException, PortalException {
        projSelDTO.setIsProjectionTotal(true);
        projSelDTO.setIsTotal(true);
        projSelDTO.setTreeLevelNo(0);
        projSelDTO.setGroup(StringUtils.EMPTY);
        int projMasterId = projSelDTO.getProjectionId();
        int brandSid = projSelDTO.getBrandMasterId();
        int therapeutic = projSelDTO.getTherapeuticSid().getId();
        List<Object[]> phsList;
        int phsParentCount = 0;
        phsList = queryUtil.loadPhsResultsTable(projMasterId, brandSid, "getPhsParentCount", projSelDTO.getLevelNo(), 0, therapeutic);
        if (phsList != null && !phsList.isEmpty()) {
            phsParentCount += Integer.parseInt(StringUtils.isNotBlank(String.valueOf(phsList.get(0))) ? String.valueOf(phsList.get(0)) : Constant.STRING_ONE);
        }
        return phsParentCount;
    }

    public int getPhsCount(ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        int phsCount = 0;
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            phsCount = count + projSelDTO.getPriceTypeList().size();
        } else {
            phsCount = count + projSelDTO.getPeriodList().size();
        }

        return phsCount;
    }

    public List<TableDTO> getPhs(ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("getPhs method started ");
        int projMasterId = projSelDTO.getProjectionId();
        int brandSid = projSelDTO.getBrandMasterId();
        int therapeuticSid = projSelDTO.getTherapeuticSid().getId();
        List<TableDTO> projDTOList = new ArrayList<>();
        try {
            com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO session = new com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO();
            session.setPageFlag(true);
            session.setOffset(projSelDTO.getPageOffSet());
            session.setStart(projSelDTO.getPageStart());
            List<Object[]> phsList = queryUtil.loadPhsParent(projMasterId, brandSid, projSelDTO.getLevelNo(), session, therapeuticSid);
            if (phsList != null) {
                projDTOList = getCustomizedProjectionTotal(phsList);
            }
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
        }
        LOGGER.debug("getPhs method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustomizedPhsChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO, String groupIndicator, TableDTO phsDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<TableDTO> projDTOList = new ArrayList<>();
        phsDTO.setParent(0);
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String group = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                if (group.equalsIgnoreCase(groupIndicator.trim())) {
                    int year = Integer.parseInt(String.valueOf(obj[NumericConstants.FIVE]));
                    int period = Integer.parseInt(String.valueOf(obj[NumericConstants.SIX]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(0);
                    String source = StringUtils.EMPTY + obj[NumericConstants.EIGHT];
                    if (PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables())) {
                        getCustomizedPhsChildPercentage(source, projections, commonColumn, projSelDTO, obj, phsDTO, columnList);
                    } else {
                        getCustomizedPhsChildAmount(source, projections, commonColumn, projSelDTO, obj, phsDTO, columnList);
                    }
                }
            }
        }
        for (String columns : columnList) {
            phsDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
        }
        projDTOList.add(phsDTO);
        return projDTOList;
    }

    public void getCustomizedPhsChildAmount(String source, String projections, String commonColumn, ProjectionSelectionDTO projSelDTO, final Object[] obj, TableDTO phsDTO, List<String> columnList) {
        String column;
        if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
            column = commonColumn + ACTUALS.getConstant();
            if (projSelDTO.hasColumn(column)) {
                String value = StringUtils.EMPTY + obj[NumericConstants.THREE];
                value = getFormattedValue(CUR_ZERO, value);
                phsDTO.addStringProperties(column, value);
                columnList.remove(column);
            }
        }
        if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
            column = commonColumn + PROJECTIONS.getConstant();
            if (projSelDTO.hasColumn(column)) {
                String value = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                value = getFormattedValue(CUR_ZERO, value);
                phsDTO.addStringProperties(column, value);
                columnList.remove(column);
            }
        }
    }

    public void getCustomizedPhsChildPercentage(String source, String projections, String commonColumn, ProjectionSelectionDTO projSelDTO, final Object[] obj, TableDTO phsDTO, List<String> columnList) {
        String column;
        if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
            column = commonColumn + ACTUALS.getConstant();
            if (projSelDTO.hasColumn(column)) {
                String value = StringUtils.EMPTY + obj[NumericConstants.THREE];
                value = getFormattedValue(PER_TWO, value);
                phsDTO.addStringProperties(column, value);
                columnList.remove(column);
            }
        }
        if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
            column = commonColumn + PROJECTIONS.getConstant();
            if (projSelDTO.hasColumn(column)) {
                String value = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                value = getFormattedValue(PER_TWO, value);
                phsDTO.addStringProperties(column, value);
                columnList.remove(column);
            }
        }

    }

    public String getFormattedValue(DecimalFormat format, String value) {
        String formatValue;
        if (value.contains(Constant.NULL) || StringUtils.isBlank(value)) {
            formatValue = StringUtils.EMPTY;
        } else if (value.contains(DASH.getConstant())) {
            formatValue = DASH.getConstant();
        } else {
            Double newValue = Double.valueOf(value);
            if (format.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / NumericConstants.HUNDRED;
            }
            formatValue = format.format(newValue);
        }
        return formatValue;
    }

    public List<TableDTO> getCustomizedProjectionTotal(List<Object[]> list) {
        List<TableDTO> projDTOList = new ArrayList<>();

        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                TableDTO fcpDTO = new TableDTO();
                final Object[] obj = (Object[]) list1;
                String itemDesc = obj[NumericConstants.TWO] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.TWO];
                String value = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(itemDesc)) {
                    value += itemDesc + ", ";
                }
                value += obj[0];

                int itemId = Integer.parseInt(String.valueOf(obj[1]));
                fcpDTO.setGroup(value);
                fcpDTO.setParent(1);
                fcpDTO.setItemMasterSid(itemId);
                projDTOList.add(fcpDTO);
            }

        }
        return projDTOList;
    }

// Master Phs Worksheet starts
    public List<TableDTO> getConfiguredPhsWorkSheetResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO, int levelNo, SessionDTO session) {
        List<TableDTO> resultList = new ArrayList<>();
        if (levelNo == 0) {
            projSelDTO.setYear(Constant.ALL);
            projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
            if (parentId instanceof TableDTO) {
                TableDTO parentDto = (TableDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setGroup(parentDto.getPriceType());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
                resultList = getPhsWorksheetChildren(start, offset, projSelDTO, session);
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                projSelDTO.setTreeLevelNo(0);
                projSelDTO.setLevelNo(0);
                projSelDTO.setGroup(StringUtils.EMPTY);

                resultList = getPhsWorksheetResults(start, offset, projSelDTO, session);

            }

        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(levelNo);
            projSelDTO.setLevelNo(levelNo);
        }
        return resultList;
    }

    public int getConfiguredPhsWorkSheetCount(Object parentId, ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
        if (parentId instanceof TableDTO) {
            TableDTO parentDto = (TableDTO) parentId;
            projSelDTO.setLevelNo(parentDto.getLevelNo());
            projSelDTO.setLevelValue(parentDto.getLevelValue());
            projSelDTO.setParentNode(parentDto.getParentNode());
            projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
            projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
            projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
            projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
            projSelDTO.setGroup(parentDto.getPriceType());
            projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
            if (parentDto.getGroup().startsWith("PHS")) {
                count += NumericConstants.THREE;
            } else if (parentDto.getGroup().equalsIgnoreCase(Constant.AMP)) {
                count += NumericConstants.TWO;
            } else {
                count += NumericConstants.TWO;
            }
        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);
            count += NumericConstants.SIX;
        }
        return count;
    }

    public List<TableDTO> getPhsWorksheetResults(int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        LOGGER.debug("getPhsWorksheetResults started");
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getPHSWorksheet(projSelDTO, session);
            for (int k = started; k < resultList.size() && neededRecord > 0; k++, neededRecord--) {
                projDTOList.add(resultList.get(k));
            }
        }
        LOGGER.debug("getPhsWorksheetResults ends");
        return projDTOList;
    }

    public List<TableDTO> getPhsWorksheetChildren(int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        LOGGER.debug("getPhsWorksheetChildren started");
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getPhsWorksheetChild(projSelDTO, session);
            for (int k = started; k < resultList.size() && neededRecord > 0; k++, neededRecord--) {
                projDTOList.add(resultList.get(k));
            }
        }
        LOGGER.debug("getPhsWorksheetChildren ends");
        return projDTOList;
    }

    public List<TableDTO> getPhsWorksheetChild(ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        LOGGER.debug("getPhsWorksheetChild method started ");
        List<TableDTO> projDTOList = new ArrayList<>();
        try {
            int ndcSid = projSelDTO.getNdcSid().getId();
            List<Object[]> pfsWSList = queryUtil.loadPhsWorksheet(session, ndcSid, projSelDTO.isAdjust());
            Map<String, String> priceTypeList = projSelDTO.getLoadPhsPriceMap();
            projDTOList = getCustPHSWorksheetChild(projSelDTO, pfsWSList, priceTypeList);
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
        }
        LOGGER.debug("getPhsWorksheetChild method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustPHSWorksheetChild(ProjectionSelectionDTO projSelDTO, List<Object[]> pfsWSList, Map<String, String> priceTypeList) {
        List<TableDTO> projDTOList = new ArrayList<>();
        
        boolean phsPrice = false;
        boolean totalUra = false;
        boolean isAMPExpanded = false;
        List<String> priceList = projSelDTO.getPriceTypeList();
        
        if ("PHS Price".equalsIgnoreCase(projSelDTO.getGroup())) {
            phsPrice = true;
        } else if (priceList.contains(TOTAL_URA.getConstant()) && (projSelDTO.getGroup().equals(TOTAL_URA.getConstant()) || projSelDTO.getGroup().equals(priceTypeList.get(Constant.PHS_TOTAL_URA)))) {
            totalUra = true;
        } else if (Constant.AMP.equalsIgnoreCase(projSelDTO.getGroup())) {
            isAMPExpanded = true;
        }
        
        if (phsPrice) {

            TableDTO histFss = new TableDTO();
            histFss.setGroup(HISTORICAL + priceTypeList.get(PHS_PRICE));
            histFss.setParent(0);
            projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, histFss, PHS.getConstant(), CUR_TWO));

            TableDTO forecastfssDTO = new TableDTO();
            forecastfssDTO.setGroup(FORECAST + priceTypeList.get(PHS_PRICE));
            forecastfssDTO.setParent(0);
            projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, forecastfssDTO, PHS.getConstant(), CUR_TWO));

            TableDTO overrideFssDTO = new TableDTO();
            overrideFssDTO.setGroup(OVERRIDE + priceTypeList.get(PHS_PRICE));
            overrideFssDTO.setParent(0);
            projDTOList.addAll(getWorksheetOverrideData(pfsWSList, projSelDTO, overrideFssDTO, PHS.getConstant(), CUR_TWO));

        }
        if (totalUra) {
            TableDTO histNonFamp = new TableDTO();
            histNonFamp.setGroup(HISTORICAL + priceTypeList.get(Constant.PHS_TOTAL_URA));
            histNonFamp.setParent(0);
            projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, histNonFamp, TOTAL_URA.getConstant(), CUR_TWO));

            TableDTO forecastnonFamp = new TableDTO();
            forecastnonFamp.setGroup(FORECAST + priceTypeList.get(Constant.PHS_TOTAL_URA));
            forecastnonFamp.setParent(0);
            projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, forecastnonFamp, TOTAL_URA.getConstant(), CUR_TWO));

        }
        if (isAMPExpanded) {
            TableDTO histAMP = new TableDTO();
            histAMP.setGroup(HISTORICAL + priceTypeList.get(Constant.PHS_AMP));
            histAMP.setParent(0);
            projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, histAMP, Constant.AMP, CUR_TWO));

            TableDTO forecastAMP = new TableDTO();
            forecastAMP.setGroup(FORECAST + priceTypeList.get(Constant.PHS_AMP));
            forecastAMP.setParent(0);
            projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, forecastAMP, Constant.AMP, CUR_TWO));
        }

        return projDTOList;
    }

    public List<TableDTO> getPHSWorksheet(ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        LOGGER.debug("getPHSWorksheet method starts ");
        int ndcSid = projSelDTO.getNdcSid().getId();
        List<TableDTO> projDTOList = new ArrayList<>();
        try {
            List<Object[]> phsWSList = queryUtil.loadPhsWorksheet(session, ndcSid, projSelDTO.isAdjust());
            Map<String, String> priceTypeList = projSelDTO.getLoadPhsPriceMap();
            projDTOList = getCustomizedPhsWorksheet(projSelDTO, phsWSList, priceTypeList);
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
        }
        LOGGER.debug("getPHSWorksheet method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustomizedPhsWorksheet(ProjectionSelectionDTO projSelDTO, List<Object[]> pfsWSList, Map<String, String> priceTypeList) {

        List<TableDTO> projDTOList = new ArrayList<>();

        TableDTO phsPriceDTO = new TableDTO();
        phsPriceDTO.setGroup(priceTypeList.get(PHS_PRICE));
        phsPriceDTO.setPriceType("PHS Price");
        phsPriceDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, phsPriceDTO, PHS.getConstant(), CUR_TWO));

        TableDTO totalUraDTO = new TableDTO();
        totalUraDTO.setGroup(priceTypeList.get(Constant.PHS_TOTAL_URA));
        totalUraDTO.setPriceType(TOTAL_URA.getConstant());
        totalUraDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, totalUraDTO, TOTAL_URA.getConstant(), CUR_TWO));

        TableDTO ampDTO = new TableDTO();
        ampDTO.setGroup(priceTypeList.get(Constant.PHS_AMP));
        ampDTO.setPriceType(Constant.AMP);
        ampDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, ampDTO, Constant.AMP, CUR_TWO));

        TableDTO wacCmsUnitPrice = new TableDTO();
        wacCmsUnitPrice.setGroup(priceTypeList.get("PHS_WAC_CMS_UNIT_PRICE"));
        wacCmsUnitPrice.setPriceType("WAC (CMS Unit Price)");
        wacCmsUnitPrice.setParent(0);
        projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, wacCmsUnitPrice, WAC.getConstant(), CUR_TWO));

        TableDTO wacIncrease = new TableDTO();
        wacIncrease.setGroup(priceTypeList.get("PHS_WAC_INCREASE"));
        wacIncrease.setPriceType("WAC Increase %");
        wacIncrease.setParent(0);
        projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, wacIncrease, "WAC INCREASE %", PER_TWO));

        TableDTO cmsDto = new TableDTO();
        cmsDto.setGroup(priceTypeList.get("PHS_CMS_UNITS"));
        cmsDto.setPriceType("CMS Units");
        cmsDto.setParent(0);
        projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, cmsDto, "CMS Units", UNITS));

        return projDTOList;
    }

    public String getPhsCook(String priceBasis, SessionDTO session) throws SQLException, NamingException {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCECONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                statement = connection.prepareCall("{call " + "PRC_MASTER_PHS_WORKSHEET " + "(?,?,?,?)}");
                statement.setInt(1, session.getProjectionId());
                statement.setObject(NumericConstants.TWO, priceBasis);
                statement.setInt(NumericConstants.THREE, Integer.valueOf(session.getUserId()));
                statement.setObject(NumericConstants.FOUR, session.getSessionId());
                statement.execute();
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return SUCCESS.getConstant();
    }

    /**
     *
     * @param list
     * @param projSelDTO
     * @return
     */
    public List<TableDTO> getCustomizedPriceTypeChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<TableDTO> projDTOList = new ArrayList<>();
        TableDTO tableDTO;
        try {

            Map<String, String> actualColumns = new HashMap<>();
            actualColumns.put(WAC.getConstant(), "wacActuals");
            actualColumns.put(PHS.getConstant(), "phsActuals");
            actualColumns.put(PHS_DISCOUNT.getConstant(), "phsdiscountActuals");
            actualColumns.put(Constant.TOTAL_URA, "totaluraActuals");
            actualColumns.put(Constant.AMP, "ampActuals");

            Map<String, String> projColumns = new HashMap<>();
            projColumns.put(WAC.getConstant(), "wacProjections");
            projColumns.put(PHS.getConstant(), "phsProjections");
            projColumns.put(PHS_DISCOUNT.getConstant(), "phsdiscountProjections");
            projColumns.put(Constant.TOTAL_URA, "totaluraProjections");
            projColumns.put(Constant.AMP, "ampProjections");

            List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
            List<String> selectedColumn = projSelDTO.getPeriodList();
            List<String> selectedHeader = projSelDTO.getPivotList();

            columnList.remove(Constant.GROUP);
            for (int j = 0; j < selectedHeader.size(); j++) {
                tableDTO = new TableDTO();
                tableDTO.setParent(0);
                tableDTO.setGroup(selectedHeader.get(j));
                if (list != null && !list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        final Object[] obj = list.get(i);
                        int year = Integer.parseInt(String.valueOf(obj[NumericConstants.FIVE]));
                        int period = Integer.parseInt(String.valueOf(obj[NumericConstants.SIX]));
                        List<String> periodList = getCommonColumnHeader(frequencyDivision, year, period);
                        if ((selectedColumn.get(j)).contains(periodList.get(0))) {
                            String source = StringUtils.EMPTY + obj[NumericConstants.EIGHT];
                            if (PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables())) {
                                String column;
                                if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                                    column = actualColumns.get(String.valueOf(obj[NumericConstants.SEVEN]));
                                    if (projSelDTO.hasColumn(column)) {
                                        String value = StringUtils.EMPTY + obj[NumericConstants.THREE];
                                        value = getFormattedValue(PER_TWO, value);
                                        tableDTO.addStringProperties(column, value);
                                        columnList.remove(column);
                                    }
                                }
                                if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                                    column = projColumns.get(String.valueOf(obj[NumericConstants.SEVEN]));
                                    if (projSelDTO.hasColumn(column)) {
                                        String value = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                                        value = getFormattedValue(PER_TWO, value);
                                        tableDTO.addStringProperties(column, value);
                                        columnList.remove(column);
                                    }
                                }
                            } else {
                                String column;
                                if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                                    column = actualColumns.get(String.valueOf(obj[NumericConstants.SEVEN]));
                                    if (projSelDTO.hasColumn(column)) {
                                        String value = StringUtils.EMPTY + obj[NumericConstants.THREE];
                                        value = getFormattedValue(CUR_ZERO, value);
                                        tableDTO.addStringProperties(column, value);
                                        columnList.remove(column);
                                    }
                                }
                                if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                                    column = projColumns.get(String.valueOf(obj[NumericConstants.SEVEN]));
                                    if (projSelDTO.hasColumn(column)) {
                                        String value = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                                        value = getFormattedValue(CUR_ZERO, value);
                                        tableDTO.addStringProperties(column, value);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            for (String columns : columnList) {
                                tableDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
                            }
                        }
                    }
                }
                projDTOList.add(tableDTO);
            }

        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }
        return projDTOList;
    }

    public List<TableDTO> getWorksheetData(List<Object[]> list, ProjectionSelectionDTO projSelDTO, TableDTO phsDTO, String groupIndicator, DecimalFormat format) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<TableDTO> projDTOList = new ArrayList<>();
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String group = StringUtils.EMPTY + obj[0];
                if (group.equalsIgnoreCase(groupIndicator.trim())) {
                    getWorksheetDataCondition(obj, frequencyDivision, projections, projSelDTO, phsDTO, format, columnList);
                }
            }
        }
        for (String columns : columnList) {
            phsDTO.addStringProperties(columns, getFormattedValue(format, DASH.getConstant()));
        }
        projDTOList.add(phsDTO);
        return projDTOList;
    }

    public void getWorksheetDataCondition(final Object[] obj, int frequencyDivision, String projections, ProjectionSelectionDTO projSelDTO, TableDTO phsDTO, DecimalFormat format, List<String> columnList) {
        int year = Integer.parseInt(String.valueOf(obj[NumericConstants.THREE]));
        int period = Integer.parseInt(String.valueOf(obj[NumericConstants.FOUR]));
        List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
        String commonColumn = common.get(0);
        String source = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
        if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
            getWorksheetDataActuals(commonColumn, projSelDTO, phsDTO, obj, format, columnList);

        }
        if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
            getWorksheetDataProjection(commonColumn, projSelDTO, phsDTO, obj, format, columnList);
        }
    }

    public void getWorksheetDataProjection(String commonColumn, ProjectionSelectionDTO projSelDTO, TableDTO phsDTO, final Object[] obj, DecimalFormat format, List<String> columnList) {
        String column;
        column = commonColumn + PROJECTIONS.getConstant();
        if (projSelDTO.hasColumn(column)) {
            if (phsDTO.getGroup().startsWith("Historical")) {
                phsDTO.addStringProperties(column, DASH.getConstant());
            } else {
                String value = StringUtils.EMPTY + obj[NumericConstants.TWO];
                value = getFormattedValue(format, value);
                phsDTO.addStringProperties(column, value);
            }
            columnList.remove(column);
        }
    }

    public void getWorksheetDataActuals(String commonColumn, ProjectionSelectionDTO projSelDTO, TableDTO phsDTO, final Object[] obj, DecimalFormat format, List<String> columnList) {
        String column;
        column = commonColumn + ACTUALS.getConstant();
        if (projSelDTO.hasColumn(column)) {
            if (phsDTO.getGroup().startsWith(Constant.FORECAST)) {
                phsDTO.addStringProperties(column, DASH.getConstant());
            } else if (phsDTO.getGroup().startsWith(Constant.OVERRIDE)) {
                phsDTO.addStringProperties(column, StringUtils.EMPTY);
            } else {
                String value = StringUtils.EMPTY + obj[1];
                value = getFormattedValue(format, value);
                phsDTO.addStringProperties(column, value);
            }
            columnList.remove(column);
        }
    }

    public int getPhsRowIndex(ProjectionSelectionDTO projSelDTO) {

        int projMasterId = projSelDTO.getProjectionId();
        int brandSid = projSelDTO.getBrandMasterId();
        int itemMasterSid = projSelDTO.getNdcLevelNo();
        int therapeutic = projSelDTO.getTherapeuticSid().getId();
        int count = 0;
        try {
            List<Object[]> medicaidIndex = queryUtil.loadPhsResultsTable(projMasterId, brandSid, "getPhsRowIndex", 0, itemMasterSid, therapeutic);
            if (!medicaidIndex.isEmpty()) {
                count = Integer.parseInt(StringUtils.isNotBlank(String.valueOf(medicaidIndex.get(0))) ? String.valueOf(medicaidIndex.get(0)) : Constant.DASH);
            }
        } catch (PortalException | SystemException | NumberFormatException e) {
            LOGGER.error(e);
        }
        return count;
    }

    public List<TableDTO> getWorksheetOverrideData(List<Object[]> list, ProjectionSelectionDTO projSelDTO, TableDTO phsDTO, String groupIndicator, DecimalFormat format) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<TableDTO> projDTOList = new ArrayList<>();
        Map<String, String[]> notesMap = new HashMap<>();

        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;
                String group = StringUtils.EMPTY + obj[0];
                int year = Integer.parseInt(String.valueOf(obj[NumericConstants.THREE]));
                int period = Integer.parseInt(String.valueOf(obj[NumericConstants.FOUR]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                String source = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                if (PROJ_CAPS.getConstant().equals(source)) {
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        String value;
                        String[] notesArray = new String[NumericConstants.TWO];
                        if (obj[NumericConstants.SIX] != null) {
                            notesArray[0] = Double.valueOf(String.valueOf(obj[NumericConstants.SIX])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[NumericConstants.SIX]);
                        } else {
                            notesArray[0] = StringUtils.EMPTY;
                        }
                        notesArray[1] = obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.FIVE];
                        if (projSelDTO.isExcel()) {
                            if (group.equalsIgnoreCase(groupIndicator.trim())) {
                                notesMap.put(column, notesArray);
                                value = notesArray[0];
                                phsDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                        } else {
                            if (group.equalsIgnoreCase(PHS.getConstant())) {
                                notesMap.put(column, notesArray);
                            }
                            value = notesArray[0];
                            phsDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                    }
                }
            }
        }
        for (String columns : columnList) {
            phsDTO.addStringProperties(columns, getFormattedValue(format, DASH.getConstant()));
        }
        projSelDTO.setNotesMap(notesMap);
        projDTOList.add(phsDTO);
        return projDTOList;
    }

}
