/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.FcpQueryUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils.getCommonColumnHeader;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.IndicatorConstants.*;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.*;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vinodhini
 */
public class FcpResultsLogic {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(FcpResultsLogic.class);
    /**
     * The Currency Zero Decimal Places Format.
     */

    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat UNITS = new DecimalFormat("#0.00");
    private static final DecimalFormat PER_TWO = new DecimalFormat("0.00%");
    private static final DecimalFormat CUR_FOUR = new DecimalFormat("$#,##0.00");
    public static final String ACTUALS_AND_PROJECTIONS = "Actuals and Projections";
    public static final String FCP_CPI_U = "FCP_CPI_U";
    public static final String FCP_NON_FAMP = "FCP_NON_FAMP";
    public static final String ADJUSTMENT = "Adjustment ";
    public static final String OVERRIDE = "Override ";
    public static final String FORECAST = "Forecast ";
    public static final String HISTORICAL = "Historical ";
    public static final String FCP_MAXIMUM_FSS_PRICE_OGA = "FCP_MAXIMUM_FSS_PRICE_OGA";
    public static final String FCP_OVERRIDE_FSS = "FCP_OVERRIDE_FSS";
    public static final String FCP_OVERRIDE_CPI_U = "FCP_OVERRIDE_CPI_U";

    private final FcpQueryUtils queryUtil = new FcpQueryUtils();

    public List<TableDTO> getConfiguredFcpResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        List<TableDTO> resultList;

        if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant()) || projSelDTO.getActualsOrProjections().equals(ACTUALS.getConstant())) {
            projSelDTO.setActualsOrProjections(ACTUALS_AND_PROJECTIONS);
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
            resultList = getFcpChildren(start, offset, projSelDTO, parentSid, session);
        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);
            projSelDTO.setPageOffSet(offset);
            projSelDTO.setPageStart(start);
            resultList = getFcpResults(start, offset, projSelDTO);

        }

        return resultList;
    }

    public int getConfiguredFcpResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO) {
        int count = 0;

        if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant()) || projSelDTO.getActualsOrProjections().equals(ACTUALS.getConstant())) {
            projSelDTO.setActualsOrProjections(ACTUALS_AND_PROJECTIONS);
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
            count += getFcpResultsCount(projSelDTO);
        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);
            int projMasterId = projSelDTO.getProjectionId();
            int brandSid = projSelDTO.getBrandMasterId();
            int therapeuticSid = projSelDTO.getTherapeuticSid().getId();
            List<Object[]> fcpList = queryUtil.loadFcpResultsTable(projMasterId, brandSid, "getFcpParentCount", projSelDTO.getLevelNo(), 0, therapeuticSid);
            if (fcpList != null && !fcpList.isEmpty()) {
                count += Integer.parseInt(StringUtils.isNotBlank(String.valueOf(fcpList.get(0))) ? String.valueOf(fcpList.get(0)) : Constant.STRING_ONE);
            }

        }
        return count;
    }

    public List<TableDTO> getFcpResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("getFcpResults start= {}, offset= {}" , start, offset);
        List<TableDTO> projDTOList = getFcp(projSelDTO);
        LOGGER.debug("getFcpResults ends");
        return projDTOList;
    }

    public int getFcpResultsCount(ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            count = count + projSelDTO.getPriceTypeList().size();
        } else {
            count = count + projSelDTO.getPeriodList().size();
        }

        return count;
    }

    public List<TableDTO> getFcp(ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("getFcp method started ");
        int projMasterId = projSelDTO.getProjectionId();
        int brandSid = projSelDTO.getBrandMasterId();
        int therapeuticSid = projSelDTO.getTherapeuticSid().getId();
        List<TableDTO> projDTOList = new ArrayList<>();
        try {
            com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO session = new com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO();
            session.setPageFlag(true);
            session.setOffset(projSelDTO.getPageOffSet());
            session.setStart(projSelDTO.getPageStart());
            List<Object[]> fcpList = queryUtil.loadFcpParent(projMasterId, brandSid, projSelDTO.getLevelNo(), session, therapeuticSid);
            if (fcpList != null) {
                projDTOList = getCustomizedFcp(fcpList);
            }
        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("getFcp method ends ");
        return projDTOList;
    }

    public List<TableDTO> getFcpChild(ProjectionSelectionDTO projSelDTO, int parentSid, SessionDTO session) {
        LOGGER.debug("getFcpChild method started ");
        List<TableDTO> projDTOList = new ArrayList<>();
        try {
            List<Object[]> fcpList;
            if (projSelDTO.getVariables().contains(PERCENTAGE.getConstant())) {
                fcpList = queryUtil.loadFcpResultsChild(session, parentSid, projSelDTO.getPriceTypeList(), true);
            } else {
                fcpList = queryUtil.loadFcpResultsChild(session, parentSid, projSelDTO.getPriceTypeList(), false);
            }
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                projDTOList = getCustFcpChild(fcpList, projSelDTO);
            } else {
                projDTOList = getCustPivotFcpChild(fcpList, projSelDTO);
            }
        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("getFcpChild method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustomizedFcp(List<Object[]> list) {
        List<TableDTO> projDTOList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                TableDTO fcpDTO = new TableDTO();
                final Object[] obj = (Object[]) list1;
                String ndc = obj[NumericConstants.TWO] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.TWO];
                String ndcDescription = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(ndc)) {
                    ndcDescription += ndc + ", ";
                }
                ndcDescription += obj[0];

                int itemId = Integer.parseInt(String.valueOf(obj[1]));
                fcpDTO.setGroup(ndcDescription);
                fcpDTO.setParent(1);
                fcpDTO.setItemMasterSid(itemId);
                projDTOList.add(fcpDTO);
            }

        }
        return projDTOList;
    }

    public List<TableDTO> getCustFcpChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        List<TableDTO> projDTOList = new ArrayList<>();
        boolean wac = false;
        boolean ffs = false;
        boolean nonFamp = false;
        boolean cpi = false;
        boolean fcp = false;
        boolean fcpDis = false;
        boolean fcpOgaDis = false;
        List<String> priceList = projSelDTO.getPriceTypeList();

        for (int i = 0; i < priceList.size(); i++) {
            if (Constant.WAC.equalsIgnoreCase(priceList.get(i))) {
                wac = true;
            }
            if (Constant.FSS.equalsIgnoreCase(priceList.get(i))) {
                ffs = true;
            }
            if (Constant.FSS_OGA_DISCOUNT.equalsIgnoreCase(priceList.get(i))) {
                fcpOgaDis = true;
            }
            if (NONFAMP1.equalsIgnoreCase(priceList.get(i))) {
                nonFamp = true;
            }
            if (Constant.CPI_URA.equalsIgnoreCase(priceList.get(i))) {
                cpi = true;
            }
            if (Constant.FCP.equalsIgnoreCase(priceList.get(i))) {
                fcp = true;
            }
            if (Constant.FCP_DISCOUNT.equalsIgnoreCase(priceList.get(i))) {
                fcpDis = true;
            }

        }
        if (wac) {
            List<TableDTO> wacList = getCustomizedFcpChild(list, projSelDTO, Constant.WAC);
            projDTOList.addAll(wacList);
        }
        if (ffs) {
            List<TableDTO> wacList = getCustomizedFcpChild(list, projSelDTO, Constant.FSS);
            projDTOList.addAll(wacList);
        }
        if (fcpOgaDis) {
            List<TableDTO> wacList = getCustomizedFcpChild(list, projSelDTO, Constant.FSS_OGA_DISCOUNT);
            projDTOList.addAll(wacList);
        }
        if (nonFamp) {
            List<TableDTO> wacList = getCustomizedFcpChild(list, projSelDTO, NONFAMP1);
            projDTOList.addAll(wacList);
        }
        if (cpi) {
            List<TableDTO> wacList = getCustomizedFcpChild(list, projSelDTO, Constant.CPI_URA);
            projDTOList.addAll(wacList);
        }
        if (fcp) {
            List<TableDTO> wacList = getCustomizedFcpChild(list, projSelDTO, Constant.FCP);
            projDTOList.addAll(wacList);
        }
        if (fcpDis) {
            List<TableDTO> wacList = getCustomizedFcpChild(list, projSelDTO, Constant.FCP_DISCOUNT);
            projDTOList.addAll(wacList);
        }

        return projDTOList;
    }
    public static final String NONFAMP1 = "NON-FAMP";

    public List<TableDTO> getCustomizedFcpChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO, String groupIndicator) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<TableDTO> projDTOList = new ArrayList<>();
        TableDTO fcpDTO = new TableDTO();
        fcpDTO.setParent(0);

        fcpDTO.setGroup(groupIndicator);

        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        try {
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    String column;
                    String group = StringUtils.EMPTY + obj[NumericConstants.SIX];
                    if (group.trim().equalsIgnoreCase(groupIndicator.trim())) {
                        String source = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                        int year = Integer.parseInt(String.valueOf(obj[NumericConstants.FIVE]));
                        int period = 0;
                        List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                        String commonColumn = common.get(0);
                        if (PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables())) {
                            if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                                column = commonColumn + ACTUALS.getConstant();
                                if (projSelDTO.hasColumn(column)) {
                                    String value = StringUtils.EMPTY + obj[NumericConstants.THREE];
                                    value = CommonUtils.getFormattedValue(PER_TWO, value);
                                    fcpDTO.addStringProperties(column, value);
                                    columnList.remove(column);
                                }
                            }
                            if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                                column = commonColumn + PROJECTIONS.getConstant();
                                if (projSelDTO.hasColumn(column)) {
                                    String value = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                                    value = CommonUtils.getFormattedValue(PER_TWO, value);
                                    fcpDTO.addStringProperties(column, value);
                                    columnList.remove(column);
                                }
                            }
                        } else {
                            if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                                column = commonColumn + ACTUALS.getConstant();
                                if (projSelDTO.hasColumn(column)) {
                                    String value = StringUtils.EMPTY + obj[NumericConstants.THREE];
                                    value = CommonUtils.getFormattedValue(CUR_FOUR, value);
                                    fcpDTO.addStringProperties(column, value);
                                    columnList.remove(column);
                                }
                            }
                            if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                                column = commonColumn + PROJECTIONS.getConstant();
                                if (projSelDTO.hasColumn(column)) {
                                    String value = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                                    value = CommonUtils.getFormattedValue(CUR_FOUR, value);
                                    fcpDTO.addStringProperties(column, value);
                                    columnList.remove(column);
                                }
                            }
                        }

                    }
                }

            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
        for (String columns : columnList) {
            fcpDTO.addStringProperties(columns, CommonUtils.getFormattedValue(PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables()) ? PER_TWO : CUR_FOUR, Constant.NULL));
        }
        projDTOList.add(fcpDTO);
        return projDTOList;
    }

    public List<TableDTO> getFcpChildren(int start, int offset, ProjectionSelectionDTO projSelDTO, int parentSid, SessionDTO session) {
        LOGGER.debug("getFcpChildren start= {}, offset= {}" , start, offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getFcpChild(projSelDTO, parentSid, session);
            for (int k = started; k < resultList.size() && neededRecord > 0; neededRecord--, k++) {
                projDTOList.add(resultList.get(k));
            }
        }
        LOGGER.debug("getFcpChildren ends");
        return projDTOList;
    }

    public List<TableDTO> getCustPivotFcpChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<TableDTO> projDTOList = new ArrayList<>();
        TableDTO tableDTO;
        try {

            Map<String, String> actualColumns = new HashMap<>();
            actualColumns.put(Constant.WAC, "wacActuals");
            actualColumns.put(Constant.FSS, "fssActuals");
            actualColumns.put(Constant.FSS_OGA_DISCOUNT, "fss(oga)discountActuals");
            actualColumns.put(NONFAMP1, "nonfampActuals");
            actualColumns.put(Constant.CPI_URA, "cpiuraActuals");
            actualColumns.put(Constant.FCP, "fcpActuals");
            actualColumns.put(Constant.FCP_DISCOUNT, "fcpdiscountActuals");

            Map<String, String> projColumns = new HashMap<>();
            projColumns.put(Constant.WAC, "wacProjections");
            projColumns.put(Constant.FSS, "fssProjections");
            projColumns.put(Constant.FSS_OGA_DISCOUNT, "fss(oga)discountProjections");
            projColumns.put(NONFAMP1, "nonfampProjections");
            projColumns.put(Constant.CPI_URA, "cpiuraProjections");
            projColumns.put(Constant.FCP, "fcpProjections");
            projColumns.put(Constant.FCP_DISCOUNT, "fcpdiscountProjections");

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

                        final Object[] obj = (Object[]) list.get(i);

                        int year = Integer.parseInt(String.valueOf(obj[NumericConstants.FIVE]));
                        int period = 0;
                        List<String> periodList = getCommonColumnHeader(frequencyDivision, year, period);

                        if ((selectedColumn.get(j)).contains(periodList.get(0))) {
                            String source = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                            String column;
                            if (PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables())) {
                                if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                                    column = actualColumns.get(String.valueOf(obj[NumericConstants.SIX]));

                                    if (projSelDTO.hasColumn(column)) {
                                        String value = StringUtils.EMPTY + obj[NumericConstants.THREE];
                                        value = CommonUtils.getFormattedValue(PER_TWO, value);
                                        tableDTO.addStringProperties(column, value);
                                        columnList.remove(column);
                                    }
                                }
                                if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                                    column = projColumns.get(String.valueOf(obj[NumericConstants.SIX]));

                                    if (projSelDTO.hasColumn(column)) {
                                        String value = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                                        value = CommonUtils.getFormattedValue(PER_TWO, value);
                                        tableDTO.addStringProperties(column, value);
                                        columnList.remove(column);
                                    }
                                }
                            } else {
                                if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                                    column = actualColumns.get(String.valueOf(obj[NumericConstants.SIX]));
                                    if (projSelDTO.hasColumn(column)) {
                                        String value = StringUtils.EMPTY + obj[NumericConstants.THREE];
                                        value = CommonUtils.getFormattedValue(CUR_FOUR, value);
                                        tableDTO.addStringProperties(column, value);
                                        columnList.remove(column);
                                    }
                                }
                                if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                                    column = projColumns.get(String.valueOf(obj[NumericConstants.SIX]));
                                    if (projSelDTO.hasColumn(column)) {
                                        String value = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                                        value = CommonUtils.getFormattedValue(CUR_FOUR, value);
                                        tableDTO.addStringProperties(column, value);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            for (String columns : columnList) {
                                tableDTO.addStringProperties(columns, CommonUtils.getFormattedValue(PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables()) ? PER_TWO : CUR_FOUR, Constant.NULL));
                            }
                        }
                    }
                }
                projDTOList.add(tableDTO);
            }

        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
        return projDTOList;
    }

    // Non Famp Starts 
    public List<TableDTO> getConfiguredNonFamp(int start, int offset, ProjectionSelectionDTO projSelDTO, String hierarchyNo, SessionDTO session) {
        List<TableDTO> resultList;
        if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant()) || projSelDTO.getActualsOrProjections().equals(ACTUALS.getConstant())) {
            projSelDTO.setActualsOrProjections(ACTUALS_AND_PROJECTIONS);
        }
        projSelDTO.setIsProjectionTotal(true);
        projSelDTO.setIsTotal(true);
        projSelDTO.setTreeLevelNo(0);
        projSelDTO.setGroup(StringUtils.EMPTY);
        hierarchyNo = null;
        resultList = getNonFampResults(start, offset, projSelDTO, session);

        return resultList;
    }

    public int getConfiguredNonFampCount(Object parentId, ProjectionSelectionDTO projSelDTO, String hierarchyNo) {
        int count = 0;
        if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant()) || projSelDTO.getActualsOrProjections().equals(ACTUALS.getConstant())) {
            projSelDTO.setActualsOrProjections(ACTUALS_AND_PROJECTIONS);
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
            hierarchyNo = parentDto.getHierarchyNo();

        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);
            hierarchyNo = null;
        }
        count += getNonFampCount(projSelDTO);
        return count;
    }

    public List<TableDTO> getNonFampResults(int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        LOGGER.debug("getNonFampResults start= {}, offset= {}" , start, offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getNonFamp(projSelDTO, session);
            for (int k = started; k < resultList.size() && neededRecord > 0; neededRecord--, k++) {
                projDTOList.add(resultList.get(k));
            }
        }
        LOGGER.debug("getNonFampResults ends");
        return projDTOList;
    }

    public List<TableDTO> getNonFamp(ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        LOGGER.debug("getNonFamp method started ");
        int brandMasterSid = projSelDTO.getBrandMasterId();
        int therapeuticSid = projSelDTO.getTherapeuticSid().getId();

        List<TableDTO> projDTOList = new ArrayList<>();
        try {
            List<Object[]> nonFampList;
            if (projSelDTO.getVariables().contains(PERCENTAGE.getConstant())) {
                nonFampList = queryUtil.getNonFamp(session, brandMasterSid, therapeuticSid, projSelDTO.getLevelNo(), true);
            } else {
                nonFampList = queryUtil.getNonFamp(session, brandMasterSid, therapeuticSid, projSelDTO.getLevelNo(), false);
            }
            if (nonFampList != null) {
                projDTOList = getCustomizedNonFamp(nonFampList, projSelDTO);
            }
        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("getNonFamp method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustomizedNonFamp(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        List<TableDTO> projDTOList = new ArrayList<>();
        Map<Integer, String> nonFampMap = new HashMap<>();
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String ndc = obj[NumericConstants.TWO] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.TWO];
                String ndcDescription = StringUtils.EMPTY;
                if (StringUtils.isNotBlank(ndc)) {
                    ndcDescription += ndc + ", ";
                }
                ndcDescription += obj[NumericConstants.THREE];

                int itemId = Integer.parseInt(String.valueOf(obj[1]));
                nonFampMap.put(itemId, ndcDescription);
            }
        }

        for (int itemSid : nonFampMap.keySet()) {
            projDTOList.addAll(getNonFampCust(list, projSelDTO, itemSid, nonFampMap.get(itemSid)));
        }

        return projDTOList;
    }

    public List<TableDTO> getNonFampCust(List<Object[]> list, ProjectionSelectionDTO projSelDTO, int itemSid, String ndc11) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<TableDTO> projDTOList = new ArrayList<>();
        TableDTO gtsDTO = new TableDTO();
        gtsDTO.setParent(0);

        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());

        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                int itemId = Integer.parseInt(String.valueOf(obj[1]));
                if (itemId == itemSid) {
                    String column;
                    gtsDTO.setGroup(ndc11);
                    gtsDTO.setParent(0);
                    gtsDTO.setItemMasterSid(itemId);
                    columnList.remove(Constant.GROUP);
                    int year = Integer.parseInt(String.valueOf(obj[NumericConstants.SIX]));
                    int period = Integer.parseInt(String.valueOf(obj[NumericConstants.SEVEN]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(0);
                    String source = StringUtils.EMPTY + obj[NumericConstants.EIGHT];
                    if (PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables())) {
                        if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                            column = commonColumn + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                String value = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                                value = CommonUtils.getFormattedValue(PER_TWO, value);
                                gtsDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                        }
                        if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                            column = commonColumn + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                String value = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                                value = CommonUtils.getFormattedValue(PER_TWO, value);
                                gtsDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                        }

                    } else {
                        if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                            column = commonColumn + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                String value = StringUtils.EMPTY + obj[NumericConstants.FOUR];
                                value = CommonUtils.getFormattedValue(CUR_FOUR, value);
                                gtsDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                        }
                        if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                            column = commonColumn + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                String value = StringUtils.EMPTY + obj[NumericConstants.FIVE];
                                value = CommonUtils.getFormattedValue(CUR_FOUR, value);
                                gtsDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                        }
                    }
                }
            }
        }
        for (String columns : columnList) {
            gtsDTO.addStringProperties(columns, CommonUtils.getFormattedValue(PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables()) ? PER_TWO : CUR_FOUR, Constant.NULL));
        }
        projDTOList.add(gtsDTO);
        return projDTOList;
    }

    public int getNonFampCount(ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        int projMasterId = projSelDTO.getProjectionId();
        int brandMasterSid = projSelDTO.getBrandMasterId();
        int therapeuticSid = projSelDTO.getTherapeuticSid().getId();
        List<Object[]> fcpList = queryUtil.loadFcpResultsTable(projMasterId, brandMasterSid, "getFcpParentCount", projSelDTO.getLevelNo(), 0, therapeuticSid);
        count += Integer.parseInt(StringUtils.isNotBlank(String.valueOf(fcpList.get(0))) ? String.valueOf(fcpList.get(0)) : Constant.STRING_ONE);
        return count;
    }

    // Master Worksheet Fcp starts
    public List<TableDTO> getConfiguredFcpWorkSheetResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        List<TableDTO> resultList;
        projSelDTO.setActualsOrProjections(ACTUALS_AND_PROJECTIONS);
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

            resultList = getFcpWorksheetChildren(start, offset, projSelDTO, session);
        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);

            resultList = getFcpWorksheetResults(start, offset, projSelDTO, session);

        }

        return resultList;
    }

    public int getConfiguredFcpWorkSheetCount(Object parentId, ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        projSelDTO.setActualsOrProjections(ACTUALS_AND_PROJECTIONS);
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
            if (parentDto.getGroup().startsWith("CPI-U")) {
                count += NumericConstants.TWO;
            } else {
                count += NumericConstants.THREE;
            }
        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);
            count += NumericConstants.TWELVE;
        }
        return count;
    }

    public List<TableDTO> getFcpWorksheetResults(int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        LOGGER.debug("getFcpResults start= {}, offset= {}" , start, offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getFcpWorksheet(projSelDTO, session);
            for (int k = started; k < resultList.size() && neededRecord > 0; neededRecord--, k++) {
                projDTOList.add(resultList.get(k));
            }
        }
        LOGGER.debug("getFcpResults ends");
        return projDTOList;
    }

    public List<TableDTO> getFcpWorksheetChildren(int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        LOGGER.debug("getFcpWorksheetChildren start= {}, offset= {}" , start, offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getFcpWorksheetChild(projSelDTO, session);
            for (int k = started; k < resultList.size() && neededRecord > 0; neededRecord--, k++) {
                projDTOList.add(resultList.get(k));
            }
        }
        LOGGER.debug("getFcpWorksheetChildren ends");
        return projDTOList;
    }

    public List<TableDTO> getFcpWorksheetChild(ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        LOGGER.debug("getFcpWorksheetChild method started ");
        List<TableDTO> projDTOList = new ArrayList<>();
        int ndcSid = projSelDTO.getNdcSid().getId();
        try {
            List<Object[]> fcpQtrList = queryUtil.loadFCPWorksheet(session, ndcSid, false, projSelDTO.isAdjust());
            List<Object[]> fcpYearlist = queryUtil.loadFCPWorksheet(session, ndcSid, true, projSelDTO.isAdjust());
            Map<String, String> priceTypeList = queryUtil.getFcpPriceTypeNameDynamic("FCP");
            projDTOList = getCustFcpWorksheetChild(projSelDTO, fcpQtrList, fcpYearlist, priceTypeList);

        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("getFcpWorksheetChild method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustFcpWorksheetChild(ProjectionSelectionDTO projSelDTO, List<Object[]> fcpList, List<Object[]> fcpYearlist, Map<String, String> priceTypeList) {
        List<TableDTO> projDTOList = new ArrayList<>();

        boolean fss = false;
        boolean nonFamp = false;
        boolean cpi = false;

        if ("Maximum FSS Price (OGA)".equalsIgnoreCase(projSelDTO.getGroup())) {
            fss = true;
        }
        if ("Non-FAMP".equalsIgnoreCase(projSelDTO.getGroup())) {
            nonFamp = true;
        }
        if ("CPI-U Additional Discount".equalsIgnoreCase(projSelDTO.getGroup())) {
            cpi = true;
        }

        if (fss) {

            TableDTO histFss = new TableDTO();
            histFss.setGroup(HISTORICAL + priceTypeList.get(FCP_MAXIMUM_FSS_PRICE_OGA));
            histFss.setParent(0);
            projDTOList.addAll(getWorksheetData(fcpList, projSelDTO, histFss, "HISTORICAL FSS (OGA) PRICING", CUR_FOUR));

            TableDTO forecastfssDTO = new TableDTO();
            forecastfssDTO.setGroup(FORECAST + priceTypeList.get(FCP_MAXIMUM_FSS_PRICE_OGA));
            forecastfssDTO.setParent(0);
            projDTOList.addAll(getWorksheetData(fcpList, projSelDTO, forecastfssDTO, "Forecast FSS (OGA) Pricing", CUR_FOUR));

            TableDTO overrideFssDTO = new TableDTO();
            overrideFssDTO.setGroup(OVERRIDE + priceTypeList.get(FCP_OVERRIDE_FSS));
            overrideFssDTO.setParent(0);
            projDTOList.addAll(getWorksheetOverrideData(fcpList, projSelDTO, overrideFssDTO, "FORECAST FSS (OGA) PRICING", CUR_FOUR));

        }
        if (nonFamp) {
            TableDTO histNonFamp = new TableDTO();
            histNonFamp.setGroup(HISTORICAL + priceTypeList.get(FCP_NON_FAMP));
            histNonFamp.setParent(0);
            projDTOList.addAll(getWorksheetData(fcpList, projSelDTO, histNonFamp, "HISTORICAL NON-FAMP", CUR_FOUR));

            TableDTO forecastnonFamp = new TableDTO();
            forecastnonFamp.setGroup(FORECAST + priceTypeList.get(FCP_NON_FAMP));
            forecastnonFamp.setParent(0);
            projDTOList.addAll(getWorksheetData(fcpList, projSelDTO, forecastnonFamp, Constant.FORECAST_NONFAMP, CUR_FOUR));

            TableDTO overrideDto = new TableDTO();
            overrideDto.setGroup(OVERRIDE + priceTypeList.get(FCP_NON_FAMP));
            overrideDto.setParent(0);
            projDTOList.addAll(getWorksheetOverrideData(fcpList, projSelDTO, overrideDto, Constant.FORECAST_NONFAMP, CUR_FOUR));
        }
        if (cpi) {
            TableDTO forecastCpiDTO = new TableDTO();
            forecastCpiDTO.setGroup(FORECAST + priceTypeList.get(FCP_CPI_U));
            forecastCpiDTO.setParent(0);
            projDTOList.addAll(getWorksheetDataYear(projSelDTO, forecastCpiDTO, fcpYearlist, "FORECAST CPI-U", PER_TWO));

            TableDTO cpiDTO = new TableDTO();
            cpiDTO.setGroup(priceTypeList.get(FCP_OVERRIDE_CPI_U) + " "+OVERRIDE);
            cpiDTO.setParent(0);
            projDTOList.addAll(getWorksheetDataYear(projSelDTO, cpiDTO, fcpYearlist, "CPI-U ADJUSTMENT", CUR_FOUR));

        }

        return projDTOList;
    }

    public List<TableDTO> getFcpWorksheet(ProjectionSelectionDTO projSelDTO, SessionDTO session) {

        List<TableDTO> projDTOList = new ArrayList<>();
        int ndcSid = projSelDTO.getNdcSid().getId();
        try {
            List<Object[]> fcpQtrList = queryUtil.loadFCPWorksheet(session, ndcSid, false, projSelDTO.isAdjust());
            List<Object[]> fcpYearlist = queryUtil.loadFCPWorksheet(session, ndcSid, true, projSelDTO.isAdjust());
            Map<String, String> priceTypeList = queryUtil.getFcpPriceTypeNameDynamic("FCP");
            projDTOList = getCustomizedFcpWorksheet(projSelDTO, fcpQtrList, fcpYearlist, priceTypeList);
        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("getFcpWorksheet method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustomizedFcpWorksheet(ProjectionSelectionDTO projSelDTO, List<Object[]> fcpQtrList, List<Object[]> fcpYearlist, Map<String, String> priceTypeList) {

        List<TableDTO> projDTOList = new ArrayList<>();

        TableDTO reportfcpDTO = new TableDTO();
        reportfcpDTO.setGroup(priceTypeList.get("FCP_REPORTED_FCP_CALENDAR_YEAR"));
        reportfcpDTO.setPriceType("Reported FCP- (Calendar Year)");
        reportfcpDTO.setParent(0);
        projDTOList.addAll(getWorksheetDataYear(projSelDTO, reportfcpDTO, fcpYearlist, "REPORTED FCP-(CALENDER YEAR)", CUR_FOUR));

        TableDTO forecastfcpDTO = new TableDTO();
        forecastfcpDTO.setGroup(priceTypeList.get("FCP_FORECAST_FCP_CALCULATED"));
        forecastfcpDTO.setPriceType("Forecast FCP Calculated");
        forecastfcpDTO.setParent(0);
        projDTOList.addAll(getWorksheetDataYear(projSelDTO, forecastfcpDTO, fcpYearlist, "FORECAST FCP CALCULATED", CUR_FOUR));

        TableDTO maxFssfcpDTO = new TableDTO();
        maxFssfcpDTO.setGroup(priceTypeList.get(FCP_MAXIMUM_FSS_PRICE_OGA));
        maxFssfcpDTO.setPriceType("Maximum FSS Price (OGA)");
        maxFssfcpDTO.setParent(1);
        projDTOList.addAll(getWorksheetDataYear(projSelDTO, maxFssfcpDTO, fcpYearlist, "MAXIMUM FSS PRICE (OGA)", CUR_FOUR));

        TableDTO nonFampDTO = new TableDTO();
        nonFampDTO.setGroup(priceTypeList.get(FCP_NON_FAMP));
        nonFampDTO.setPriceType("Non-FAMP");
        nonFampDTO.setParent(1);
        projDTOList.addAll(getWorksheetDataYear(projSelDTO, nonFampDTO, fcpYearlist, NONFAMP1, CUR_FOUR));

        TableDTO cpiDTO = new TableDTO();
        cpiDTO.setGroup(priceTypeList.get(FCP_CPI_U));
        cpiDTO.setPriceType("CPI-U Additional Discount");
        cpiDTO.setParent(1);
        projDTOList.addAll(getWorksheetDataYear(projSelDTO, cpiDTO, fcpYearlist, "CPI-U", CUR_FOUR));

        TableDTO rebateDTO = new TableDTO();
        rebateDTO.setGroup(priceTypeList.get("FCP_RETAIL_REBATE"));
        rebateDTO.setPriceType("Retail (Rebate)");
        rebateDTO.setParent(0);
        projDTOList.add(rebateDTO);

        TableDTO discountDTO = new TableDTO();
        discountDTO.setGroup(priceTypeList.get("FCP_TRICARE"));
        discountDTO.setPriceType("TriCare");
        discountDTO.setParent(0);
        projDTOList.addAll(getWorksheetDataYear(projSelDTO, discountDTO, fcpYearlist, "MANDATED DISCOUNT ($)", CUR_FOUR));

        TableDTO wacDTO = new TableDTO();
        wacDTO.setGroup(priceTypeList.get("FCP_WAC"));
        wacDTO.setPriceType(Constant.WAC);
        wacDTO.setParent(0);
        projDTOList.addAll(getWorksheetData(fcpQtrList, projSelDTO, wacDTO, Constant.WAC, CUR_FOUR));

        TableDTO wacIncreaseDTO = new TableDTO();
        wacIncreaseDTO.setGroup(priceTypeList.get("FCP_WAC_INCREASE"));
        wacIncreaseDTO.setPriceType("WAC Increase %");
        wacIncreaseDTO.setParent(0);
        projDTOList.addAll(getWorksheetData(fcpQtrList, projSelDTO, wacIncreaseDTO, "WAC INCREASE %", PER_TWO));

        TableDTO wacFccDTO = new TableDTO();
        wacFccDTO.setGroup(priceTypeList.get("FCP_WAC_FCP"));
        wacFccDTO.setPriceType("WAC-FCP");
        wacFccDTO.setParent(0);
        projDTOList.addAll(getWorksheetData(fcpQtrList, projSelDTO, wacFccDTO, Constant.WAC_FCP, CUR_FOUR));

        TableDTO wacFssDTO = new TableDTO();
        wacFssDTO.setGroup(priceTypeList.get("FCP_WAC_FSS"));
        wacFssDTO.setPriceType("WAC-FSS");
        wacFssDTO.setParent(0);
        projDTOList.addAll(getWorksheetData(fcpQtrList, projSelDTO, wacFssDTO, Constant.WAC_FSS, CUR_FOUR));

        TableDTO cmsUnitsDTO = new TableDTO();
        cmsUnitsDTO.setGroup(priceTypeList.get("FCP_CMS_UNITS"));
        cmsUnitsDTO.setPriceType("CMS Units");
        cmsUnitsDTO.setParent(0);
        projDTOList.addAll(getWorksheetData(fcpQtrList, projSelDTO, cmsUnitsDTO, "CMS UNITS", UNITS));

        return projDTOList;
    }

    public String fcpSetupCook(com.stpl.app.gtnforecasting.sessionutils.SessionDTO session, String priceBasis) throws NamingException, SQLException {
        DataSource datasource = null;
        try {
            Context initialContext = new InitialContext();
            String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
        } catch (NamingException ex)
        {
            LOGGER.debug(ex.getMessage());
        }
            if (datasource != null) {
                try (Connection connection = datasource.getConnection();
                        CallableStatement statement = connection.prepareCall("{call PRC_MASTER_FCP_WORKSHEET(?,?,?,?)}"))
                {
                LOGGER.debug("Failed in FCP datasource.");
                LOGGER.debug("Got Connection= {} " , connection.toString());
                statement.setInt(1, session.getProjectionId());
                statement.setObject(NumericConstants.TWO, priceBasis);
                statement.setInt(NumericConstants.THREE, Integer.parseInt(session.getUserId()));
                statement.setObject(NumericConstants.FOUR, session.getSessionId());
                statement.execute();
                LOGGER.debug("procedure call ended  ");
            } catch (SQLException ex)
            {
              LOGGER.debug(ex.getMessage());
            }
        }
        return "SUCCESS";
    }

    public int getFcpRowIndex(ProjectionSelectionDTO projSelDTO) {

        int projMasterId = projSelDTO.getProjectionId();
        int brandSid = projSelDTO.getBrandMasterId();
        int itemMasterSid = projSelDTO.getNdcLevelNo();
        int therapeuticSid = projSelDTO.getTherapeuticSid().getId();
        int count = 0;
        try {
            List<Object[]> medicaidIndex = queryUtil.loadFcpResultsTable(projMasterId, brandSid, "getFcpRowIndex", 0, itemMasterSid, therapeuticSid);
            if (!medicaidIndex.isEmpty()) {
                count = Integer.parseInt(StringUtils.isNotBlank(String.valueOf(medicaidIndex.get(0))) ? String.valueOf(medicaidIndex.get(0)) : Constant.DASH);
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
        return count;
    }

    public List<TableDTO> getWorksheetData(List<Object[]> list, ProjectionSelectionDTO projSelDTO, TableDTO fcpDTO, String groupIndicator, DecimalFormat format) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<TableDTO> projDTOList = new ArrayList<>();
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);

        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;
                String group = StringUtils.EMPTY + obj[0];
                if (group.equalsIgnoreCase(groupIndicator.trim())) {

                    int year = Integer.parseInt(String.valueOf(obj[NumericConstants.THREE]));
                    int period = Integer.parseInt(String.valueOf(obj[NumericConstants.FOUR]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(0);
                    String source = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                    if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            if (fcpDTO.getGroup().startsWith(Constant.FORECAST)) {
                                fcpDTO.addStringProperties(column, DASH.getConstant());
                            } else {
                                String value = StringUtils.EMPTY + obj[1];
                                value = CommonUtils.getFormattedValue(format, value);
                                fcpDTO.addStringProperties(column, value);
                            }
                            columnList.remove(column);
                        }
                    }
                    if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                        column = commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            if (fcpDTO.getGroup().startsWith("Historical")) {
                                fcpDTO.addStringProperties(column, DASH.getConstant());
                            } else {
                                String value = StringUtils.EMPTY + obj[NumericConstants.TWO];
                                value = CommonUtils.getFormattedValue(format, value);
                                fcpDTO.addStringProperties(column, value);
                            }
                            columnList.remove(column);
                        }
                    }
                }
            }
        }
        for (String columns : columnList) {
            fcpDTO.addStringProperties(columns, CommonUtils.getFormattedValue(format, DASH.getConstant()));
        }
        projDTOList.add(fcpDTO);

        return projDTOList;
    }

    public List<TableDTO> getWorksheetDataYear(ProjectionSelectionDTO projSelDTO, TableDTO fcpDTO, List<Object[]> fcpYearlist, String groupIndicator, DecimalFormat format) {

        List<TableDTO> projDTOList = new ArrayList<>();

        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);

        if (fcpYearlist != null && !fcpYearlist.isEmpty()) {
            for (Object list1 : fcpYearlist) {
                final Object[] obj = (Object[]) list1;
                String group = StringUtils.EMPTY + obj[0];
                int year = Integer.parseInt(String.valueOf(obj[NumericConstants.THREE]));
                if (group.equalsIgnoreCase(groupIndicator.trim())) {
                    String source = StringUtils.EMPTY + obj[NumericConstants.SIX];
                    if (ACTUALS_CAPS.getConstant().equals(source)) {

                        String reportedColumn = year + "reportedFcp";
                        if (projSelDTO.hasColumn(reportedColumn)) {
                            String value = StringUtils.EMPTY + obj[1];
                            value = CommonUtils.getFormattedValue(format, value);
                            fcpDTO.addStringProperties(reportedColumn, value);
                            columnList.remove(reportedColumn);
                        }
                    }
                    if (PROJ_CAPS.getConstant().equals(source)) {
                        String forecastColumn = year + "forecastFcp";
                        if (projSelDTO.hasColumn(forecastColumn)) {
                            String value = StringUtils.EMPTY + obj[NumericConstants.TWO];
                            value = CommonUtils.getFormattedValue(format, value);
                            fcpDTO.addStringProperties(forecastColumn, value);
                            columnList.remove(forecastColumn);
                        }
                    }
                }
            }

        }
        for (String columns : columnList) {
            fcpDTO.addStringProperties(columns, StringUtils.EMPTY);
        }

        projDTOList.add(fcpDTO);

        return projDTOList;
    }

    public List<TableDTO> getWorksheetOverrideData(List<Object[]> list, ProjectionSelectionDTO projSelDTO, TableDTO fcpDTO, String groupIndicator, DecimalFormat format) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<TableDTO> projDTOList = new ArrayList<>();
        Map<String, String[]> fssNotesMap = new HashMap<>();
        Map<String, String[]> fampNotesMap = new HashMap<>();
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

                if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        String value;
                        String[] notesArray = new String[NumericConstants.TWO];
                        if (obj[NumericConstants.SIX] != null) {
                            notesArray[0] = Double.parseDouble(String.valueOf(obj[NumericConstants.SIX])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CUR_FOUR, StringUtils.EMPTY + obj[NumericConstants.SIX]);
                        } else {
                            notesArray[0] = StringUtils.EMPTY;
                        }
                        notesArray[1] = obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.FIVE];
                        if (projSelDTO.isExcel()) {
                            if (group.equalsIgnoreCase(groupIndicator.trim())) {
                                value = notesArray[0];
                                fcpDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                        } else {
                            if (group.startsWith(Constant.FORECAST_NONFAMP)) {
                                fampNotesMap.put(column, notesArray);
                            }
                            if (group.startsWith("FORECAST FSS (OGA) PRICING")) {
                                fssNotesMap.put(column, notesArray);
                            }
                            value = notesArray[0];
                            fcpDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                    }
                }
            }
        }
        for (String columns : columnList) {
            fcpDTO.addStringProperties(columns, CommonUtils.getFormattedValue(format, DASH.getConstant()));
        }
        projSelDTO.setNotesMap(fssNotesMap);
        projSelDTO.setSecondRowNotesMap(fampNotesMap);
        projDTOList.add(fcpDTO);

        return projDTOList;
    }
}
