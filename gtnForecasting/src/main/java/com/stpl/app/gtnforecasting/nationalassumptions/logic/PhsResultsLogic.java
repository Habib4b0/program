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
import java.util.logging.Level;
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
    private final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";

    private final PhsQueryUtils queryUtil = new PhsQueryUtils();

    public List<TableDTO> getConfiguredPhsResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO,SessionDTO session) {
        List<TableDTO> resultList;
        if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant()) || projSelDTO.getActualsOrProjections().equals(ACTUALS.getConstant())) {
            projSelDTO.setActualsOrProjections("Actuals and Projections");
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

            int parentSid = parentDto.getItemMasterSid();
            resultList = getPhsChildren(start, offset, projSelDTO, parentSid,session);
        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);
            projSelDTO.setPageOffSet(offset);
            projSelDTO.setPageStart(start);
            resultList = getPhsResults(start, offset, projSelDTO);

        }

        return resultList;
    }

    public List<TableDTO> getPhsChildren(int start, int offset, ProjectionSelectionDTO projSelDTO, int parentSid,SessionDTO session) {
        LOGGER.debug("getPhsChildren start=" + start + "   offset=" + offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getPhsChild(projSelDTO, parentSid,session);
            for (int k = started; k < resultList.size() && neededRecord > 0; k++) {
                projDTOList.add(resultList.get(k));
                neededRecord--;
            }
            if (resultList != null) {
                resultList = null;
            }
        }
        LOGGER.debug("getPhsChildren ends");
        return projDTOList;
    }

    public List<TableDTO> getPhsChild(ProjectionSelectionDTO projSelDTO, int parentSid,SessionDTO session) {
        LOGGER.debug("getPhsChild method started ");
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
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
            if (phsList != null) {
                phsList = null;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("getPhsChild method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustPhsChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        boolean wac = false;
        boolean phs = false;
        boolean totalURA = false;
        boolean phsDiscount = false;

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
                
            }
            if (wac) {
                List<TableDTO> wacList = getCustomizedPhsChild(list, projSelDTO, WAC.getConstant());
                projDTOList.addAll(wacList);
            }
            if (phs) {
                List<TableDTO> phsList = getCustomizedPhsChild(list, projSelDTO, PHS.getConstant());
                projDTOList.addAll(phsList);
            }
            if (phsDiscount) {
                List<TableDTO> phsDiscountList = getCustomizedPhsChild(list, projSelDTO, PHS_DISCOUNT.getConstant());
                projDTOList.addAll(phsDiscountList);
            }
            if (totalURA) {
                List<TableDTO> uraList = getCustomizedPhsChild(list, projSelDTO, TOTAL_URA.getConstant());
                projDTOList.addAll(uraList);
            }
             

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return projDTOList;
    }

    public List<TableDTO> getPhsResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("getPhsResults start=" + start + "   offset=" + offset);
        List<TableDTO> projDTOList = getPhs(projSelDTO);
        LOGGER.debug("getPhsResults ends");
        return projDTOList;
    }

    public int getConfiguredPhsResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO) {
        int count = 0;

        if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant()) || projSelDTO.getActualsOrProjections().equals(ACTUALS.getConstant())) {
            projSelDTO.setActualsOrProjections("Actuals and Projections");
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
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                projSelDTO.setTreeLevelNo(0);
                projSelDTO.setGroup(StringUtils.EMPTY);
                int projMasterId = projSelDTO.getProjectionId();
                int brandSid = projSelDTO.getBrandMasterId();
                int therapeutic = projSelDTO.getTherapeuticSid().getId();
                List<Object[]> phsList;
                phsList = queryUtil.loadPhsResultsTable(projMasterId, brandSid, "getPhsParentCount", projSelDTO.getLevelNo(), 0, therapeutic);
                if (phsList != null && !phsList.isEmpty()) {
                    count += Integer.parseInt(StringUtils.isNotBlank(String.valueOf(phsList.get(0))) ? String.valueOf(phsList.get(0)) : Constant.STRING_ONE);
                }
                if (phsList != null) {
                    phsList = null;
                }
            } catch (PortalException ex) {
                java.util.logging.Logger.getLogger(PhsResultsLogic.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                java.util.logging.Logger.getLogger(PhsResultsLogic.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return count;
    }

    public int getPhsCount(ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            count = count + projSelDTO.getPriceTypeList().size();
        } else {
            count = count + projSelDTO.getPeriodList().size();
        }

        return count;
    }

    public List<TableDTO> getPhs(ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("getPhs method started ");
        int projMasterId = projSelDTO.getProjectionId();
        int brandSid = projSelDTO.getBrandMasterId();
        int therapeuticSid = projSelDTO.getTherapeuticSid().getId();
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        try {
            com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO session = new com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO();
            session.setPageFlag(true);
            session.setOffset(projSelDTO.getPageOffSet());
            session.setStart(projSelDTO.getPageStart());
            List<Object[]> phsList = queryUtil.loadPhsParent(projMasterId, brandSid, projSelDTO.getLevelNo(), session, therapeuticSid);
            if (phsList != null) {
                projDTOList = getCustomizedProjectionTotal(phsList, projSelDTO);
            }
            if (phsList != null) {
                phsList = null;
            }
        } catch (Exception e) {
           LOGGER.error(e);
        }
        LOGGER.debug("getPhs method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustomizedPhsChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO, String groupIndicator) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        TableDTO phsDTO = new TableDTO();
        phsDTO.setParent(0);

        phsDTO.setGroup(groupIndicator);

        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                String group = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                if (group.equalsIgnoreCase(groupIndicator.trim())) {

                    int year = Integer.valueOf(String.valueOf(obj[NumericConstants.FIVE]));
                    int period = Integer.valueOf(String.valueOf(obj[NumericConstants.SIX]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(0);
                    String source = StringUtils.EMPTY + obj[NumericConstants.EIGHT];
                    if (PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables())) {
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
                    } else {
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
                }
            }
        }
        for (String columns : columnList) {
            phsDTO.addStringProperties(columns, getFormattedValue(PER_TWO, Constant.NULL));
        }
        projDTOList.add(phsDTO);
        return projDTOList;
    }

    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(Constant.NULL) || StringUtils.isBlank(value)) {
            value = StringUtils.EMPTY;
        } else if (value.contains(DASH.getConstant())) {
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

    public List<TableDTO> getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

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
    public List<TableDTO> getConfiguredPhsWorkSheetResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO, int levelNo, String hierarchyNo,SessionDTO session) {
        List<TableDTO> resultList = new ArrayList<TableDTO>();
        if (levelNo == 0) {
            projSelDTO.setYear(Constant.All);
            projSelDTO.setActualsOrProjections("Actuals and Projections");
            if (parentId instanceof TableDTO) {
                TableDTO parentDto = (TableDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
                hierarchyNo = parentDto.getHierarchyNo();

                int parentSid = parentDto.getItemMasterSid();
                resultList = getPhsWorksheetChildren(start, offset, projSelDTO, hierarchyNo, parentSid,session);
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                projSelDTO.setTreeLevelNo(0);
                projSelDTO.setLevelNo(0);
                projSelDTO.setGroup(StringUtils.EMPTY);
                hierarchyNo = null;

                resultList = getPhsWorksheetResults(start, offset, projSelDTO, hierarchyNo,session);

            }

        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(levelNo);
            projSelDTO.setLevelNo(levelNo);
        }
        return resultList;
    }

    public int getConfiguredPhsWorkSheetCount(Object parentId, ProjectionSelectionDTO projSelDTO, int levelNo, String hierarchyNo, boolean isLevelsCount) {
        int count = 0;
        projSelDTO.setActualsOrProjections("Actuals and Projections");
        if (parentId instanceof TableDTO) {
            TableDTO parentDto = (TableDTO) parentId;
            projSelDTO.setLevelNo(parentDto.getLevelNo());
            projSelDTO.setLevelValue(parentDto.getLevelValue());
            projSelDTO.setParentNode(parentDto.getParentNode());
            projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
            projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
            projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
            projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
            projSelDTO.setGroup(parentDto.getGroup());
            projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
            hierarchyNo = parentDto.getHierarchyNo();
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
            hierarchyNo = null;
            count += NumericConstants.SIX;
        }
        return count;
    }

    public List<TableDTO> getPhsWorksheetResults(int start, int offset, ProjectionSelectionDTO projSelDTO, String hierarchyNo,SessionDTO session) {
        LOGGER.debug("getPhsWorksheetResults start=" + start + "   offset=" + offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getPHSWorksheet(projSelDTO,session);
            for (int k = started; k < resultList.size() && neededRecord > 0; k++) {
                projDTOList.add(resultList.get(k));
                neededRecord--;
            }
        }
        LOGGER.debug("getPhsWorksheetResults ends");
        return projDTOList;
    }

    public List<TableDTO> getPhsWorksheetChildren(int start, int offset, ProjectionSelectionDTO projSelDTO, String hierarchyNo, int parentSid,SessionDTO session) {
        LOGGER.debug("getPhsWorksheetChildren start=" + start + "   offset=" + offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getPhsWorksheetChild(projSelDTO, parentSid,session);
            for (int k = started; k < resultList.size() && neededRecord > 0; k++) {
                projDTOList.add(resultList.get(k));
                neededRecord--;
            }
            if (resultList != null) {
                resultList = null;
            }
        }
        LOGGER.debug("getPhsWorksheetChildren ends");
        return projDTOList;
    }

    public List<TableDTO> getPhsWorksheetChild(ProjectionSelectionDTO projSelDTO, int parentSid,SessionDTO session) {
        LOGGER.debug("getPhsWorksheetChild method started ");
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        try {
            int ndcSid = projSelDTO.getNdcSid().getId();
            List<Object[]> pfsWSList = queryUtil.loadPhsWorksheet(session, ndcSid, projSelDTO.isAdjust());
            projDTOList = getCustPHSWorksheetChild(projSelDTO, pfsWSList);

            if (pfsWSList != null) {
                pfsWSList = null;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("getPhsWorksheetChild method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustPHSWorksheetChild(ProjectionSelectionDTO projSelDTO, List<Object[]> pfsWSList) {
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

        boolean phsPrice = false;
        boolean totalUra = false;
        boolean isAMPExpanded = false;

        if ("PHS Price".equalsIgnoreCase(projSelDTO.getGroup())) {
            phsPrice = true;
        }
        if (TOTAL_URA.getConstant().equalsIgnoreCase(projSelDTO.getGroup())) {
            totalUra = true;
        }
        isAMPExpanded = Constant.AMP.equalsIgnoreCase(projSelDTO.getGroup());

        if (phsPrice) {

            TableDTO histFss = new TableDTO();
            histFss.setGroup("Historical PHS Price");
            histFss.setParent(0);
            projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, histFss, PHS.getConstant(), CUR_TWO));

            TableDTO forecastfssDTO = new TableDTO();
            forecastfssDTO.setGroup("Forecast PHS Price");
            forecastfssDTO.setParent(0);
            projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, forecastfssDTO, PHS.getConstant(), CUR_TWO));

            TableDTO overrideFssDTO = new TableDTO();
            overrideFssDTO.setGroup("Adjustment PHS Price");
            overrideFssDTO.setParent(0);
            projDTOList.addAll(getWorksheetOverrideData(pfsWSList, projSelDTO, overrideFssDTO, PHS.getConstant(), CUR_TWO));

        }
        if (totalUra) {
            TableDTO histNonFamp = new TableDTO();
            histNonFamp.setGroup("Historical Total URA");
            histNonFamp.setParent(0);
            projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, histNonFamp, TOTAL_URA.getConstant(), CUR_TWO));

            TableDTO forecastnonFamp = new TableDTO();
            forecastnonFamp.setGroup("Forecast Total URA");
            forecastnonFamp.setParent(0);
            projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, forecastnonFamp, TOTAL_URA.getConstant(), CUR_TWO));

        }
        if (isAMPExpanded) {
            TableDTO histAMP = new TableDTO();
            histAMP.setGroup("Historical AMP");
            histAMP.setParent(0);
            projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, histAMP, Constant.AMP, CUR_TWO));

            TableDTO forecastAMP = new TableDTO();
            forecastAMP.setGroup("Forecast AMP");
            forecastAMP.setParent(0);
            projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, forecastAMP, Constant.AMP, CUR_TWO));
        }

        return projDTOList;
    }

    public List<TableDTO> getPHSWorksheet(ProjectionSelectionDTO projSelDTO,SessionDTO session) {
        LOGGER.debug("getPHSWorksheet method starts ");
        int ndcSid = projSelDTO.getNdcSid().getId();
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        try {
            List<Object[]> phsWSList = queryUtil.loadPhsWorksheet(session, ndcSid, projSelDTO.isAdjust());
            projDTOList = getCustomizedPhsWorksheet(projSelDTO, phsWSList);
            if (phsWSList != null) {
                phsWSList = null;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("getPHSWorksheet method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustomizedPhsWorksheet(ProjectionSelectionDTO projSelDTO, List<Object[]> pfsWSList) {

        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

        TableDTO phsPriceDTO = new TableDTO();
        phsPriceDTO.setGroup("PHS Price");
        phsPriceDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, phsPriceDTO, PHS.getConstant(), CUR_TWO));

        TableDTO totalUraDTO = new TableDTO();
        totalUraDTO.setGroup(TOTAL_URA.getConstant());
        totalUraDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, totalUraDTO, TOTAL_URA.getConstant(), CUR_TWO));

        TableDTO ampDTO = new TableDTO();
        ampDTO.setGroup(Constant.AMP);
        ampDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, ampDTO, Constant.AMP, CUR_TWO));

        TableDTO wacCmsUnitPrice = new TableDTO();
        wacCmsUnitPrice.setGroup("WAC (CMS Unit Price)");
        wacCmsUnitPrice.setParent(0);
        projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, wacCmsUnitPrice, WAC.getConstant(), CUR_TWO));

        TableDTO wacIncrease = new TableDTO();
        wacIncrease.setGroup("WAC Increase %");
        wacIncrease.setParent(0);
        projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, wacIncrease, "WAC INCREASE %", PER_TWO));

        TableDTO cmsDto = new TableDTO();
        cmsDto.setGroup("CMS Units");
        cmsDto.setParent(0);
        projDTOList.addAll(getWorksheetData(pfsWSList, projSelDTO, cmsDto, "CMS Units", UNITS));

        return projDTOList;
    }

    public String getPhsCook(String priceBasis,SessionDTO session) throws SQLException, NamingException  {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
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
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        TableDTO tableDTO;
        try {

            Map<String, String> actualColumns = new HashMap<String, String>();
            actualColumns.put(WAC.getConstant(), "wacActuals");
            actualColumns.put(PHS.getConstant(), "phsActuals");
            actualColumns.put(PHS_DISCOUNT.getConstant(), "phsdiscountActuals");
            actualColumns.put(Constant.TOTAL_URA, "totaluraActuals");
            

            Map<String, String> projColumns = new HashMap<String, String>();
            projColumns.put(WAC.getConstant(), "wacProjections");
            projColumns.put(PHS.getConstant(), "phsProjections");
            projColumns.put(PHS_DISCOUNT.getConstant(), "phsdiscountProjections");
            projColumns.put(Constant.TOTAL_URA, "totaluraProjections");
           
            List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
            List<String> selectedColumn = projSelDTO.getPeriodList();
            List<String> selectedHeader = projSelDTO.getPivotList();

            columnList.remove(Constant.GROUP);
            for (int j = 0; j < selectedHeader.size(); j++) {
                tableDTO = new TableDTO();
                tableDTO.setParent(0);
                tableDTO.setGroup(selectedHeader.get(j));
                if (list != null && !list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {

                        final Object[] obj = (Object[]) list.get(i);

                        int year = Integer.valueOf(String.valueOf(obj[NumericConstants.FIVE]));
                        int period = Integer.valueOf(String.valueOf(obj[NumericConstants.SIX]));
                        List<String> periodList = getCommonColumnHeader(frequencyDivision, year, period);
                        if ((selectedColumn.get(j)).contains(periodList.get(0))) {
                            String source = StringUtils.EMPTY + obj[NumericConstants.EIGHT];
                            String column = StringUtils.EMPTY;
                            if (PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables())) {
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

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return projDTOList;
    }

    public List<TableDTO> getWorksheetData(List<Object[]> list, ProjectionSelectionDTO projSelDTO, TableDTO phsDTO, String groupIndicator, DecimalFormat format) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                String group = StringUtils.EMPTY + obj[0];
                if (group.equalsIgnoreCase(groupIndicator.trim())) {

                    int year = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
                    int period = Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(0);
                    String source = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                    if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                            column = commonColumn + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                if (phsDTO.getGroup().startsWith(Constant.FORECAST)) {
                                    phsDTO.addStringProperties(column, DASH.getConstant());
                                } else if (phsDTO.getGroup().startsWith(Constant.ADJUSTMENT)) {
                                    phsDTO.addStringProperties(column, StringUtils.EMPTY);
                                } else {
                                    String value = StringUtils.EMPTY + obj[1];
                                    value = getFormattedValue(format, value);
                                    phsDTO.addStringProperties(column, value);
                                }
                                columnList.remove(column);
                            }

                    }
                        if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
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
                }
            }

        }
        for (String columns : columnList) {
            phsDTO.addStringProperties(columns, getFormattedValue(format, DASH.getConstant()));
        }
        projDTOList.add(phsDTO);
        return projDTOList;
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
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return count;
    }

    public List<TableDTO> getWorksheetOverrideData(List<Object[]> list, ProjectionSelectionDTO projSelDTO, TableDTO phsDTO, String groupIndicator, DecimalFormat format) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        Map<String, String[]> notesMap = new HashMap<String, String[]>();

        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                String group = StringUtils.EMPTY + obj[0];
                int year = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
                int period = Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                String source = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                if (PROJ_CAPS.getConstant().equals(source)) {
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {

                        String value = StringUtils.EMPTY;
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
