/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic;

import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.queryutils.MedicaidQueryUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUiUtils.getCommonColumnHeader;
import com.stpl.app.gtnforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.IndicatorConstants.ACTUALS_CAPS;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.IndicatorConstants.BASEYEAR;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.IndicatorConstants.PROJ_CAPS;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.AMP;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.BEST_PRICE_CAPS;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.BOTH;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.DASH;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.PERCENTAGE;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.PERIOD;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.PRICE_TYPE;
import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.LabelConstants.PROJECTIONS;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
import org.jboss.logging.Logger;

/**
 *
 * @author Manasa
 */
public class MedicaidURAResultsLogic {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(MedicaidURAResultsLogic.class);
    /**
     * The Currency Zero Decimal Places Format.
     */
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0.00");
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PER_TWO = new DecimalFormat("0.00%");
    /**
     * The Numeric Zero Decimal Places Format.
     */
    private static final DecimalFormat NUM_TWO = new DecimalFormat("0.00");

    public static final String MEDICAID_URA_AMP = "MEDICAID_URA_AMP";
    public static final String MEDICAID_URA_BEST_PRICE = "MEDICAID_URA_BEST_PRICE";
    public static final String FORECAST = "Forecast ";
    public static final String HISTORICAL = "Historical ";
    public static final String MEDICAID_URA_BASIC_URA = "MEDICAID_URA_BASIC_URA";
    public static final String MEDICAID_URA_CPI_URA = "MEDICAID_URA_CPI_URA";
    public static final String MEDICAID_URA_CPI_U = "MEDICAID_URA_CPI_U";
    public static final String MEDICAID_URA_TOTAL_URA = "MEDICAID_URA_TOTAL_URA";

    private final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    public static final String ADJUSTMENT_CPI = "Adjustment CPI";
    public static final String OVERRIDE_CPI_URA = "Override CPI URA";
    public static final String CPI_U = "CPI-U";

    MedicaidQueryUtils queryUtil = new MedicaidQueryUtils();
    private static final DecimalFormat CUR_FOUR = new DecimalFormat("$0.0000");

    public List<TableDTO> getConfiguredMedicaidResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO session) {
        List<TableDTO> resultList;
        if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant()) || projSelDTO.getActualsOrProjections().equals(ACTUALS.getConstant())) {
            projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
        }
        if (parentId instanceof TableDTO) {
            TableDTO parentDto = (TableDTO) parentId;
            projSelDTO.setParentNode(parentDto.getParentNode());
            projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
            projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
            projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
            projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
            projSelDTO.setGroup(parentDto.getPriceType());
            projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);

            String parentSid = parentDto.getNdc9();
            resultList = getMedicaidChildren(start, offset, projSelDTO, parentSid, session);
        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);
            projSelDTO.setPageOffSet(offset);
            projSelDTO.setPageStart(start);
            resultList = getMedicaidResults(start, offset, projSelDTO);

        }

        return resultList;
    }

    public int getConfiguredMedicaidResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant()) || projSelDTO.getActualsOrProjections().equals(ACTUALS.getConstant())) {
            projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
        }

        if (parentId instanceof TableDTO) {
            TableDTO parentDto = (TableDTO) parentId;
            projSelDTO.setParentNode(parentDto.getParentNode());
            projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
            projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
            projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
            projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
            projSelDTO.setGroup(parentDto.getGroup());
            projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);

            count += getMedicaidCount(projSelDTO);
        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);
            if (StringUtils.isNotBlank(projSelDTO.getLevelValue())) {
                count = 1;
            } else {
                int projMasterId = projSelDTO.getProjectionId();
                int brandSid = projSelDTO.getBrandMasterId();
                String ndc9Level = projSelDTO.getNdc9LevelNo();
                int therapeuticSid = projSelDTO.getTherapeuticSid().getId();
                List<Object[]> medicaidList = queryUtil.loadMedicaidResultsTable(projMasterId, brandSid, "getMedicaidParentCount", ndc9Level, therapeuticSid);
                if (!medicaidList.isEmpty()) {
                    count += Integer.parseInt(StringUtils.isNotBlank(String.valueOf(medicaidList.get(0))) ? String.valueOf(medicaidList.get(0)) : Constant.STRING_ONE);
                }
            }
        }

        return count;
    }

    public List<TableDTO> getMedicaidResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("getMedicaidResults start=" + start + "   offset=" + offset);
        List<TableDTO> projDTOList = getMedicaid(projSelDTO);
        LOGGER.debug("getMedicaidResults ends");
        return projDTOList;
    }

    public int getMedicaidCount(ProjectionSelectionDTO projSelDTO) {
        int count = 0;
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {

            count = count + projSelDTO.getPriceTypeList().size();

        } else {
            count = count + projSelDTO.getPeriodList().size();
        }

        return count;
    }

    public List<TableDTO> getMedicaid(ProjectionSelectionDTO projSelDTO) {
        LOGGER.debug("getMedicaid method started ");
        int projMasterId = projSelDTO.getProjectionId();
        int brandSid = projSelDTO.getBrandMasterId();
        int therapeuticSid = projSelDTO.getTherapeuticSid().getId();

        List<TableDTO> projDTOList = new ArrayList<>();
        try {
            if (StringUtils.isNotBlank(projSelDTO.getLevelValue())) {
                TableDTO medicaidDTO = new TableDTO();
                medicaidDTO.setGroup(projSelDTO.getMedicaidSelectedNdc());
                medicaidDTO.setParent(1);
                medicaidDTO.setNdc9(projSelDTO.getLevelValue());
                projDTOList.add(medicaidDTO);
            } else {
                com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO session = new com.stpl.app.gtnforecasting.nationalassumptions.dto.SessionDTO();
                session.setPageFlag(true);
                session.setOffset(projSelDTO.getPageOffSet());
                session.setStart(projSelDTO.getPageStart());
                List<Object[]> medicaidList = queryUtil.loadMedicaidParent(projMasterId, brandSid, projSelDTO.getLevelValue(), session, therapeuticSid);
                if (medicaidList != null) {
                    projDTOList = getCustomizedProjectionTotal(medicaidList);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("getMedicaid method ends ");
        return projDTOList;
    }

    public List<TableDTO> getMedicaidChild(ProjectionSelectionDTO projSelDTO, String parentSid, SessionDTO session) {
        LOGGER.debug("getMedicaidChild method started ");
        List<TableDTO> projDTOList = new ArrayList<>(); 
        try {
            List<Object[]> medicaidList;
            if (projSelDTO.getVariables().contains(PERCENTAGE.getConstant())) {
                medicaidList = queryUtil.loadMedicaidResultsChild(session, parentSid, projSelDTO.getPriceTypeList(), true);
            } else {
                medicaidList = queryUtil.loadMedicaidResultsChild(session, parentSid, projSelDTO.getPriceTypeList(), false);
            }

            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                projDTOList = getCustMedicaidChild(medicaidList, projSelDTO);
            }
            if (projSelDTO.getPivotView().contains(PRICE_TYPE.getConstant())) {
                projDTOList = getPriceTypeChild(projSelDTO, parentSid, session);

            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("getMedicaidChild method ends ");
        return projDTOList;
    }

    public List<TableDTO> getPriceTypeChild(ProjectionSelectionDTO projSelDTO, String parentSid, SessionDTO session) {
        LOGGER.debug("getPriceTypeChild method started ");
        List<TableDTO> projDTOList = new ArrayList<>();
        List<Object[]> medicaidList = null;
        try {
            if (projSelDTO.getVariables().contains(PERCENTAGE.getConstant())) {
                medicaidList = queryUtil.loadMedicaidResultsChild(session, parentSid, projSelDTO.getPriceTypeList(), true);
            } else {
                medicaidList = queryUtil.loadMedicaidResultsChild(session, parentSid, projSelDTO.getPriceTypeList(), false);
            }
            projDTOList = getCustPriceTypeChild(medicaidList, projSelDTO);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("getPriceTypeChild method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustomizedProjectionTotal(List<Object[]> list) {

        List<TableDTO> projDTOList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {

            for (Object list1 : list) {
                TableDTO medicaidDTO = new TableDTO();
                final Object[] obj = (Object[]) list1;
                String itemDesc = obj[1] == null ? "" : "" + obj[1];
                String value = "";
                if (StringUtils.isNotBlank(itemDesc)) {
                    value += itemDesc + Constant.COMMA;
                }
                value += obj[0];

                String ndc9 = StringUtils.EMPTY + obj[2];
                medicaidDTO.setGroup(value);
                medicaidDTO.setParent(1);
                medicaidDTO.setNdc9(ndc9);
                medicaidDTO.setItemMasterSid(Integer.valueOf(String.valueOf(obj[2])));
                medicaidDTO.setNewFormulation(String.valueOf(obj[5]));
                medicaidDTO.setNewFormulationItemId(String.valueOf(obj[6]));
                medicaidDTO.setBaeselineAmp(String.valueOf(obj[7]));
                medicaidDTO.setBaeselineCpi(String.valueOf(obj[8]));
                projDTOList.add(medicaidDTO);
            }

        }
        return projDTOList;
    }

    public List<TableDTO> getCustMedicaidChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        List<TableDTO> projDTOList = new ArrayList<>();
        Map<String,String> priceTypeList = projSelDTO.getLoadUraPriceMap();
        boolean wac = false;
        boolean amp = false;
        boolean bestPrice = false;
        boolean lowestNmPrice = false;
        boolean basicUra = false;
        boolean cpiUra = false;
        boolean totalUra = false;
        List<String> priceList = projSelDTO.getPriceTypeList();

        for (int i = 0; i < priceList.size(); i++) {
            if (Constant.WAC.equalsIgnoreCase(priceList.get(i))) {
                wac = true;
            }
            if (Constant.AMP.equalsIgnoreCase(priceList.get(i))) {
                amp = true;
            }
            if (Constant.BEST_PRICE.equalsIgnoreCase(priceList.get(i))) {
                bestPrice = true;
            }

            if (Constant.LOWEST_COMMERCIAL_NET_PRICE.equalsIgnoreCase(priceList.get(i))) {
                lowestNmPrice = true;
            }
            if (Constant.BASIC_URA.equalsIgnoreCase(priceList.get(i))) {
                basicUra = true;
            }
            if (Constant.CPIURA.equalsIgnoreCase(priceList.get(i))) {
                cpiUra = true;
            }
            if (Constant.TOTAL_URA_LABEL.equalsIgnoreCase(priceList.get(i))) {
                totalUra = true;
            }
        }
        if (wac) {
            TableDTO gtsDTO = new TableDTO();
            gtsDTO.setGroup(Constant.WAC);
            List<TableDTO> wacList = getCustomizedMedicaidChild(list, projSelDTO, Constant.WAC, gtsDTO);
            projDTOList.addAll(wacList);
        }
        if (amp) {
            TableDTO gtsDTO = new TableDTO();
            gtsDTO.setGroup(Constant.AMP);
            List<TableDTO> ampList = getCustomizedMedicaidChild(list, projSelDTO, Constant.AMP, gtsDTO);
            projDTOList.addAll(ampList);
        }
        if (bestPrice) {
            TableDTO gtsDTO = new TableDTO();
            gtsDTO.setGroup(getGroupName(priceTypeList.get(MEDICAID_URA_BEST_PRICE)));
            List<TableDTO> bpList = getCustomizedMedicaidChild(list, projSelDTO, Constant.BEST_PRICE_LOWERCASE, gtsDTO);
            projDTOList.addAll(bpList);
        }
        if (lowestNmPrice) {
            TableDTO gtsDTO = new TableDTO();
            gtsDTO.setGroup("Lowest Commercial Net Price");
            List<TableDTO> lowestNpList = getCustomizedMedicaidChild(list, projSelDTO, "Lowest Commercial Net Price", gtsDTO);
            projDTOList.addAll(lowestNpList);
        }
        if (basicUra) {
            TableDTO gtsDTO = new TableDTO();
            gtsDTO.setGroup(getGroupName(priceTypeList.get(MEDICAID_URA_BASIC_URA)));
            List<TableDTO> uraList = getCustomizedMedicaidChild(list, projSelDTO, Constant.BASIC_URA1, gtsDTO);
            projDTOList.addAll(uraList);
        }
        if (cpiUra) {
            TableDTO gtsDTO = new TableDTO();
            gtsDTO.setGroup(getGroupName(priceTypeList.get(MEDICAID_URA_CPI_URA)));
            List<TableDTO> cpiList = getCustomizedMedicaidChild(list, projSelDTO, Constant.CPIURA, gtsDTO);
            projDTOList.addAll(cpiList);
        }
        if (totalUra) {
            TableDTO gtsDTO = new TableDTO();
            gtsDTO.setGroup(getGroupName(priceTypeList.get(MEDICAID_URA_TOTAL_URA)));
            List<TableDTO> totalUraList = getCustomizedMedicaidChild(list, projSelDTO, Constant.TOTAL_URA_LABEL, gtsDTO);
            projDTOList.addAll(totalUraList);
        }

        return projDTOList;
    }

    public List<TableDTO> getCustPriceTypeChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        List<TableDTO> projDTOList = new ArrayList<>();

        List<TableDTO> wacList = getCustomizedPriceTypeChild(list, projSelDTO);
        projDTOList.addAll(wacList);
        return projDTOList;
    }

    public List<TableDTO> getCustomizedMedicaidChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO, String groupIndicator, TableDTO gtsDTO) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<TableDTO> projDTOList = new ArrayList<>();
        gtsDTO.setParent(0);
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;
                String group = StringUtils.EMPTY + obj[NumericConstants.SIX];
                if (group.equalsIgnoreCase(groupIndicator.trim())) {
                    int year = Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR]));
                    int period = Integer.valueOf(String.valueOf(obj[NumericConstants.FIVE]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(0);
                    String source = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                    if (PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables())) {
                        if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                            column = commonColumn + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                String value = StringUtils.EMPTY + obj[NumericConstants.TWO];
                                value = CommonUtils.getFormattedValue(PER_TWO, value);
                                gtsDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                        }
                        if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                            column = commonColumn + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                String value = StringUtils.EMPTY + obj[NumericConstants.THREE];
                                value = CommonUtils.getFormattedValue(PER_TWO, value);
                                gtsDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                        }
                    } else {
                        if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                            column = commonColumn + ACTUALS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                String value = StringUtils.EMPTY + obj[NumericConstants.TWO];
                                value = CommonUtils.getFormattedValue(CUR_ZERO, value);
                                gtsDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                        }
                        if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                            column = commonColumn + PROJECTIONS.getConstant();
                            if (projSelDTO.hasColumn(column)) {
                                String value = StringUtils.EMPTY + obj[NumericConstants.THREE];
                                value = CommonUtils.getFormattedValue(CUR_ZERO, value);
                                gtsDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }
                        }
                    }
                }
            }

        }
        for (String columns : columnList) {
            gtsDTO.addStringProperties(columns, CommonUtils.getFormattedValue(PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables()) ? PER_TWO : CUR_ZERO, Constant.NULL));
        }
        projDTOList.add(gtsDTO);
        return projDTOList;
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
            actualColumns.put(Constant.WAC, "wacActuals");
            actualColumns.put(Constant.AMP, "ampActuals");
            actualColumns.put(Constant.BEST_PRICE, "bestpriceActuals");
            actualColumns.put(Constant.LOWEST_COMMERCIAL_NET_PRICE, "lowestcommercialnetpriceActuals");
            actualColumns.put(Constant.BASIC_URA, "basicuraActuals");
            actualColumns.put(Constant.CPIURA, "cpiuraActuals");
            actualColumns.put(Constant.TOTAL_URA, "totaluraActuals");

            Map<String, String> projColumns = new HashMap<>();
            projColumns.put(Constant.WAC, "wacProjections");
            projColumns.put(Constant.AMP, "ampProjections");
            projColumns.put(Constant.BEST_PRICE, "bestpriceProjections");
            projColumns.put(Constant.LOWEST_COMMERCIAL_NET_PRICE, "lowestcommercialnetpriceProjections");
            projColumns.put(Constant.BASIC_URA, "basicuraProjections");
            projColumns.put(Constant.CPIURA, "cpiuraProjections");
            projColumns.put(Constant.TOTAL_URA, "totaluraProjections");

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

                        int year = Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR]));
                        int period = Integer.valueOf(String.valueOf(obj[NumericConstants.FIVE]));
                        List<String> periodList = getCommonColumnHeader(frequencyDivision, year, period);
                        if ((selectedColumn.get(j)).contains(periodList.get(0))) {
                            String source = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                            String column;
                            if (PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables())) {

                                if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {

                                    column = actualColumns.get(String.valueOf(obj[NumericConstants.SIX]));
                                    if (projSelDTO.hasColumn(column)) {
                                        String value = StringUtils.EMPTY + obj[NumericConstants.TWO];
                                        value = CommonUtils.getFormattedValue(PER_TWO, value);
                                        tableDTO.addStringProperties(column, value);
                                        columnList.remove(column);
                                    }
                                }

                                if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                                    column = projColumns.get(String.valueOf(obj[NumericConstants.SIX]));
                                    if (projSelDTO.hasColumn(column)) {
                                        String value = StringUtils.EMPTY + obj[NumericConstants.THREE];
                                        value = CommonUtils.getFormattedValue(PER_TWO, value);
                                        tableDTO.addStringProperties(column, value);
                                        columnList.remove(column);
                                    }
                                }
                            } else {
                                if ((ACTUALS_CAPS.getConstant().equals(source)) && (projections.contains(ACTUALS.getConstant()))) {
                                    column = actualColumns.get(String.valueOf(obj[NumericConstants.SIX]));
                                    if (projSelDTO.hasColumn(column)) {
                                        String value = StringUtils.EMPTY + obj[NumericConstants.TWO];
                                        value = CommonUtils.getFormattedValue(CUR_ZERO, value);
                                        tableDTO.addStringProperties(column, value);
                                        columnList.remove(column);
                                    }
                                }
                                if ((PROJ_CAPS.getConstant().equals(source)) && (projections.contains(PROJECTIONS.getConstant()))) {
                                    column = projColumns.get(String.valueOf(obj[NumericConstants.SIX]));
                                    if (projSelDTO.hasColumn(column)) {
                                        String value = StringUtils.EMPTY + obj[NumericConstants.THREE];
                                        value = CommonUtils.getFormattedValue(CUR_ZERO, value);
                                        tableDTO.addStringProperties(column, value);
                                        columnList.remove(column);
                                    }
                                }
                            }
                            for (String columns : columnList) {
                                tableDTO.addStringProperties(columns, CommonUtils.getFormattedValue(PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables()) ? PER_TWO : CUR_ZERO, Constant.NULL));
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

    public List<TableDTO> getMedicaidChildren(int start, int offset, ProjectionSelectionDTO projSelDTO, String parentSid, SessionDTO session) {
        LOGGER.debug("getMedicaidChildren start=" + start + "   offset=" + offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<>();

        if ((projSelDTO.getPivotView().contains(PERIOD.getConstant())) && neededRecord > 0) {
            List<TableDTO> resultList = getMedicaidChild(projSelDTO, parentSid, session);
            for (int k = started; k < resultList.size() && neededRecord > 0; neededRecord--, k++) {
                projDTOList.add(resultList.get(k));
            }
        } else if ((projSelDTO.getPivotView().contains(PRICE_TYPE.getConstant())) && neededRecord > 0) {
            List<TableDTO> resultList = getPriceTypeChild(projSelDTO, parentSid, session);
            for (int k = started; k < resultList.size() && neededRecord > 0; neededRecord--, k++) {
                projDTOList.add(resultList.get(k));
            }
        }
        LOGGER.debug("getMedicaidChildren ends");
        return projDTOList;
    }

    public List<TableDTO> getConfiguredMedicaidWorkSheetResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO sessionDTO) {
        List<TableDTO> resultList;

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

            resultList = getMedicaidWorksheetChildren(start, offset, projSelDTO, sessionDTO);
        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);

            resultList = getMedicaidWorksheetResults(start, offset, projSelDTO, sessionDTO);

        }

        return resultList;
    }

    public int getConfiguredMedicaidWorkSheetCount(Object parentId, ProjectionSelectionDTO projSelDTO) {
        int count = 0;

        projSelDTO.setActualsOrProjections(Constant.ACTUALS_AND_PROJECTIONS);
        Map<String,String> priceTypeList = projSelDTO.getLoadUraPriceMap();
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

            if (Constant.BEST_PRICE_LOWERCASE.equalsIgnoreCase(parentDto.getGroup()) || Constant.CPIURA.equalsIgnoreCase(parentDto.getGroup())) {
                count += NumericConstants.FOUR;
            }
            if (Constant.AMP.equalsIgnoreCase(parentDto.getGroup())) {
                count += NumericConstants.THREE;
            }
            if (Constant.BASIC_URA1.equalsIgnoreCase(projSelDTO.getGroup()) || CPI_U.equalsIgnoreCase(projSelDTO.getGroup()) || projSelDTO.getGroup().equals(getGroupName(priceTypeList.get(MEDICAID_URA_TOTAL_URA)))) {
                count += NumericConstants.TWO;
            }

        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);

            count += NumericConstants.NINE;
        }
        return count;
    }

    public List<TableDTO> getMedicaidWorksheetResults(int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO sessionDTO) {
        LOGGER.debug("getMedicaidResults start=" + start + "    offset=" + offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getMedicaidWorksheet(projSelDTO, sessionDTO);
            for (int k = started; k < resultList.size() && neededRecord > 0; neededRecord--, k++) {
                projDTOList.add(resultList.get(k));
            }
        }
        LOGGER.debug("getMedicaidResults ends");
        return projDTOList;
    }

    public List<TableDTO> getMedicaidWorksheetChildren(int start, int offset, ProjectionSelectionDTO projSelDTO, SessionDTO sessionDTO) {
        LOGGER.debug("getMedicaidChildren start=" + start + "  offset=" + offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getMedicaidWorksheetChild(projSelDTO, sessionDTO);
            for (int k = started; k < resultList.size() && neededRecord > 0; neededRecord--, k++) {
                projDTOList.add(resultList.get(k));
            }
        }
        LOGGER.debug("getMedicaidChildren ends");
        return projDTOList;
    }

    public List<TableDTO> getMedicaidWorksheetChild(ProjectionSelectionDTO projSelDTO, SessionDTO sessionDTO) {
        LOGGER.debug("getMedicaidChild method started ");

        List<TableDTO> projDTOList = new ArrayList<>();
        Map<String,String> priceTypeList = projSelDTO.getLoadUraPriceMap();
        try {
            List<Object[]> medicaidList = queryUtil.loadMedicaidWorksheet(sessionDTO, projSelDTO.getNdc9(), projSelDTO.isAdjust());
            projDTOList = getCustMedicaidWorksheetChild(projSelDTO, medicaidList, priceTypeList);
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
        }
        LOGGER.debug("getMedicaidChild method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustMedicaidWorksheetChild(ProjectionSelectionDTO projSelDTO, List<Object[]> medicaidList, Map<String, String> priceTypeList) {
        List<TableDTO> projDTOList = new ArrayList<>();

        boolean amp = false;
        boolean bestPrice = false;
        boolean ura = false;
        boolean cpi = false;
        boolean cpiUra = false;
        boolean totalUra = false;
        List<String> priceList = projSelDTO.getPriceTypeList();

        if (Constant.AMP.equalsIgnoreCase(projSelDTO.getGroup())) {
            amp = true;
        }
        if (Constant.BEST_PRICE_LOWERCASE.equalsIgnoreCase(projSelDTO.getGroup())) {
            bestPrice = true;
        }
        if (Constant.BASIC_URA1.equalsIgnoreCase(projSelDTO.getGroup())) {
            ura = true;
        }
        if (CPI_U.equalsIgnoreCase(projSelDTO.getGroup())) {
            cpi = true;
        }
        if (Constant.CPIURA.equalsIgnoreCase(projSelDTO.getGroup())) {
            cpiUra = true;
        }
        if (priceList.contains(Constant.TOTAL_URA_LABEL) && projSelDTO.getGroup().equals(getGroupName(priceTypeList.get(MEDICAID_URA_TOTAL_URA)))) {
            totalUra = true;
        }

        if (amp) {

            TableDTO histAmp = new TableDTO();
            histAmp.setGroup(HISTORICAL + getGroupName(priceTypeList.get(MEDICAID_URA_AMP)));
            histAmp.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, histAmp, AMP.getConstant(), CUR_FOUR));

            TableDTO forecastAmpDTO = new TableDTO();
            forecastAmpDTO.setGroup(FORECAST + getGroupName(priceTypeList.get(MEDICAID_URA_AMP)));
            forecastAmpDTO.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, forecastAmpDTO, AMP.getConstant(), CUR_FOUR));

            TableDTO adjustmentAmpDTO = new TableDTO();
            adjustmentAmpDTO.setGroup("Override " + getGroupName(priceTypeList.get(MEDICAID_URA_AMP)));
            adjustmentAmpDTO.setParent(0);
            projDTOList.addAll(getWorksheetOverrideData(medicaidList, projSelDTO, adjustmentAmpDTO, AMP.getConstant(), CUR_FOUR));

        }
        if (bestPrice) {
            TableDTO histBestPrice = new TableDTO();
            histBestPrice.setGroup(HISTORICAL + getGroupName(priceTypeList.get(MEDICAID_URA_BEST_PRICE)));
            histBestPrice.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, histBestPrice, BEST_PRICE_CAPS.getConstant(), CUR_FOUR));

            TableDTO forecastBestPrice = new TableDTO();
            forecastBestPrice.setGroup(FORECAST + getGroupName(priceTypeList.get(MEDICAID_URA_BEST_PRICE)));
            forecastBestPrice.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, forecastBestPrice, BEST_PRICE_CAPS.getConstant(), CUR_FOUR));

            TableDTO lowNMBestPrice = new TableDTO();
            lowNMBestPrice.setGroup("Lowest Commercial " + getGroupName(priceTypeList.get(MEDICAID_URA_BEST_PRICE)));
            lowNMBestPrice.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, lowNMBestPrice, "Lowest Commercial Best Price", CUR_FOUR));

            TableDTO adjBestPrice = new TableDTO();
            adjBestPrice.setGroup("Override " + getGroupName(priceTypeList.get(MEDICAID_URA_BEST_PRICE)));
            adjBestPrice.setParent(0);
            projDTOList.addAll(getWorksheetOverrideData(medicaidList, projSelDTO, adjBestPrice, BEST_PRICE_CAPS.getConstant(), CUR_FOUR));
        }
        if (ura) {
            TableDTO histUra = new TableDTO();
            histUra.setGroup(HISTORICAL + getGroupName(priceTypeList.get(MEDICAID_URA_BASIC_URA)));
            histUra.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, histUra, Constant.BASIC_URA, CUR_FOUR));

            TableDTO forecastUra = new TableDTO();
            forecastUra.setGroup(FORECAST + getGroupName(priceTypeList.get(MEDICAID_URA_BASIC_URA)));
            forecastUra.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, forecastUra, Constant.BASIC_URA, CUR_FOUR));

        }
        if (cpi) {
            TableDTO histCpi = new TableDTO();
            histCpi.setGroup(HISTORICAL + getGroupName(priceTypeList.get(MEDICAID_URA_CPI_U)));
            histCpi.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, histCpi, CPI_U, CUR_FOUR));

            TableDTO forecastCpi = new TableDTO();
            forecastCpi.setGroup(FORECAST + getGroupName(priceTypeList.get(MEDICAID_URA_CPI_U)));
            forecastCpi.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, forecastCpi, CPI_U, CUR_FOUR));

        }
        if (cpiUra) {
            TableDTO histCpiUra = new TableDTO();
            histCpiUra.setGroup(HISTORICAL + getGroupName(priceTypeList.get(MEDICAID_URA_CPI_URA)));
            histCpiUra.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, histCpiUra, Constant.CPIURA, CUR_FOUR));

            TableDTO forecastCpiUra = new TableDTO();
            forecastCpiUra.setGroup(FORECAST + getGroupName(priceTypeList.get(MEDICAID_URA_CPI_URA)));
            forecastCpiUra.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, forecastCpiUra, Constant.CPIURA, CUR_FOUR));

            TableDTO adjCpiAlt = new TableDTO();
            adjCpiAlt.setGroup("Adjustment CPI (Alt)");
            adjCpiAlt.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, adjCpiAlt, "Adjustment CPI (Alt)", CUR_FOUR));

            TableDTO adjCpi = new TableDTO();
            adjCpi.setGroup(OVERRIDE_CPI_URA);
            adjCpi.setParent(0);
            projDTOList.addAll(getWorksheetOverrideData(medicaidList, projSelDTO, adjCpi, CPI_U, CUR_FOUR));
        }
        if (totalUra) {
            TableDTO histTotalUra = new TableDTO();
            histTotalUra.setGroup(HISTORICAL + getGroupName(priceTypeList.get(MEDICAID_URA_TOTAL_URA)));
            histTotalUra.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, histTotalUra, Constant.TOTAL_URA, CUR_FOUR));

            TableDTO forecastTotalUra = new TableDTO();
            forecastTotalUra.setGroup(FORECAST + getGroupName(priceTypeList.get(MEDICAID_URA_TOTAL_URA)));
            forecastTotalUra.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, forecastTotalUra, Constant.TOTAL_URA, CUR_FOUR));

        }
        return projDTOList;
    }

    public List<TableDTO> getMedicaidWorksheet(ProjectionSelectionDTO projSelDTO, SessionDTO sessionDTO) {
        LOGGER.debug("getMedicaidWorksheet method started ");
        List<TableDTO> projDTOList = new ArrayList<>();
        Map<String,String> priceTypeList = projSelDTO.getLoadUraPriceMap();
        try {
            List<Object[]> medicaidList = queryUtil.loadMedicaidWorksheet(sessionDTO, projSelDTO.getNdc9(), projSelDTO.isAdjust());
            projDTOList = getCustomizedMedicaidWorksheet(projSelDTO, medicaidList, priceTypeList);
        } catch (PortalException | SystemException e) {
            LOGGER.error(e);
        }
        LOGGER.debug("getMedicaidWorksheet method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustomizedMedicaidWorksheet(ProjectionSelectionDTO projSelDTO, List<Object[]> medicaidList, Map<String, String> priceTypeList) {

        List<TableDTO> projDTOList = new ArrayList<>();

        TableDTO ampDTO = new TableDTO();
        ampDTO.setGroup(getGroupName(priceTypeList.get(MEDICAID_URA_AMP)));
        ampDTO.setPriceType(getGroupName(priceTypeList.get(MEDICAID_URA_AMP)));
        ampDTO.setParent(1);
        projDTOList.addAll(getBpWorksheetData(medicaidList, projSelDTO, ampDTO, AMP.getConstant(), CUR_FOUR));

        TableDTO bestPriceDTO = new TableDTO();
        bestPriceDTO.setGroup(getGroupName(priceTypeList.get(MEDICAID_URA_BEST_PRICE)));
        bestPriceDTO.setPriceType(getGroupName(priceTypeList.get(MEDICAID_URA_BEST_PRICE)));
        bestPriceDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, bestPriceDTO, BEST_PRICE_CAPS.getConstant(), CUR_FOUR));

        TableDTO baseUraDTO = new TableDTO();
        baseUraDTO.setGroup(getGroupName(priceTypeList.get(MEDICAID_URA_BASIC_URA)));
        baseUraDTO.setPriceType(getGroupName(priceTypeList.get(MEDICAID_URA_BASIC_URA)));
        baseUraDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, baseUraDTO, Constant.BASIC_URA, CUR_FOUR));

        TableDTO cpiUDTO = new TableDTO();
        cpiUDTO.setGroup(getGroupName(priceTypeList.get(MEDICAID_URA_CPI_U)));
        cpiUDTO.setPriceType(getGroupName(priceTypeList.get(MEDICAID_URA_CPI_U)));
        cpiUDTO.setParent(1);
        projDTOList.addAll(getBpWorksheetData(medicaidList, projSelDTO, cpiUDTO, CPI_U, CUR_FOUR));

        TableDTO cpiDTO = new TableDTO();
        cpiDTO.setGroup(getGroupName(priceTypeList.get(MEDICAID_URA_CPI_URA)));
        cpiDTO.setPriceType(getGroupName(priceTypeList.get(MEDICAID_URA_CPI_URA)));
        cpiDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, cpiDTO, Constant.CPIURA, CUR_FOUR));

        TableDTO totalUraDTO = new TableDTO();
        totalUraDTO.setGroup(getGroupName(priceTypeList.get(MEDICAID_URA_TOTAL_URA)));
        totalUraDTO.setPriceType(getGroupName(priceTypeList.get(MEDICAID_URA_TOTAL_URA)));
        totalUraDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, totalUraDTO, Constant.TOTAL_URA, CUR_FOUR));

        TableDTO wacDTO = new TableDTO();
        wacDTO.setGroup(getGroupName(priceTypeList.get("MEDICAID_URA_WAC_CMS_UNIT")));
        wacDTO.setPriceType(getGroupName(priceTypeList.get("MEDICAID_URA_WAC_CMS_UNIT")));
        wacDTO.setParent(0);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, wacDTO, Constant.WAC, CUR_FOUR));

        TableDTO wacIncreaseDTO = new TableDTO();
        wacIncreaseDTO.setGroup(getGroupName(priceTypeList.get("MEDICAID_URA_WAC_INCREASE")));
        wacIncreaseDTO.setPriceType(getGroupName(priceTypeList.get("MEDICAID_URA_WAC_INCREASE")));
        wacIncreaseDTO.setParent(0);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, wacIncreaseDTO, "WAC INCREASE %", PER_TWO));

        TableDTO cmsUnitsDTO = new TableDTO();
        cmsUnitsDTO.setGroup(getGroupName(priceTypeList.get("MEDICAID_URA_CMS_UNIT")));
        cmsUnitsDTO.setPriceType(getGroupName(priceTypeList.get("MEDICAID_URA_CMS_UNIT")));
        cmsUnitsDTO.setParent(0);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, cmsUnitsDTO, "CMS UNITS", NUM_TWO));

        return projDTOList;
    }

    public String medicaidProcSetupDataCook(SessionDTO session, String priceBasis) throws NamingException, SQLException {
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
                statement = connection.prepareCall("{call PRC_MEDICAID_URA_WORKSHEET(?,?,?,?)}");
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
        return "Success";
    }

    public List<TableDTO> getWorksheetData(List<Object[]> list, ProjectionSelectionDTO projSelDTO, TableDTO medicaidDTO, String groupIndicator, DecimalFormat format) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<TableDTO> projDTOList = new ArrayList<>();
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;
                String group = StringUtils.EMPTY + obj[0];
                if (group.equalsIgnoreCase(groupIndicator.trim())) {

                    int year = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
                    int period = Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(0);
                    String source = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                    if (ACTUALS_CAPS.getConstant().equals(source)) {

                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            if (medicaidDTO.getGroup().startsWith(Constant.FORECAST)) {
                                medicaidDTO.addStringProperties(column, DASH.getConstant());
                            } else {
                                String value = StringUtils.EMPTY + obj[1];
                                if (medicaidDTO.getGroup().startsWith(Constant.ADJUSTMENT_AMP) || medicaidDTO.getGroup().startsWith(Constant.ADJUSTMENT_BEST_PRICE) || medicaidDTO.getGroup().equals(ADJUSTMENT_CPI) 
                                        || medicaidDTO.getGroup().startsWith(Constant.OVERRIDE_AMP) || medicaidDTO.getGroup().startsWith(Constant.OVERRIDE_BEST_PRICE) || medicaidDTO.getGroup().startsWith(Constant.OVERRIDE_CPI_URA)) {
                                    value = StringUtils.EMPTY;
                                }
                                value = CommonUtils.getFormattedValue(format, value);
                                String[] notesArray = new String[NumericConstants.TWO];
                                if (medicaidDTO.getGroup().startsWith("Historical AMP")) {
                                    if (obj[NumericConstants.SIX] != null) {
                                        notesArray[0] = Double.valueOf(String.valueOf(obj[NumericConstants.SIX])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[NumericConstants.SIX]);
                                    } else {
                                        notesArray[0] = StringUtils.EMPTY;
                                    }
                                    medicaidDTO.addStringProperties(BASEYEAR.getConstant(), notesArray[0]);
                                    columnList.remove(BASEYEAR.getConstant());
                                    notesArray[1] = obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.FIVE];
                                    medicaidDTO.setBaseYearAmp(notesArray[0]);
                                    medicaidDTO.setBaseYearAmpNotes(notesArray[1]);
                                }
                                if (medicaidDTO.getGroup().startsWith("Historical CPI-U")) {
                                    if (obj[NumericConstants.SIX] != null) {
                                        notesArray[0] = Double.valueOf(String.valueOf(obj[NumericConstants.SIX])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[NumericConstants.SIX]);
                                    } else {
                                        notesArray[0] = StringUtils.EMPTY;
                                    }
                                    medicaidDTO.addStringProperties(BASEYEAR.getConstant(), notesArray[0]);
                                    columnList.remove(BASEYEAR.getConstant());
                                    notesArray[1] = obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.FIVE];
                                    medicaidDTO.setBaseYearCpi(notesArray[0]);
                                    medicaidDTO.setBaseYearCpiNotes(notesArray[1]);
                                }
                                medicaidDTO.addStringProperties(column, value);
                            }
                            columnList.remove(column);
                        }

                    }
                    if (PROJ_CAPS.getConstant().equals(source)) {
                        column = commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            if (medicaidDTO.getGroup().startsWith("Historical")) {
                                medicaidDTO.addStringProperties(column, DASH.getConstant());
                            } else {
                                String value = StringUtils.EMPTY + obj[NumericConstants.TWO];
                                value = CommonUtils.getFormattedValue(format, value);
                                medicaidDTO.addStringProperties(column, value);
                            }
                            columnList.remove(column);
                        }
                    }
                }
            }
        }
        for (String columns : columnList) {
            medicaidDTO.addStringProperties(columns, CommonUtils.getFormattedValue(format, DASH.getConstant()));
        }

        projDTOList.add(medicaidDTO);
        return projDTOList;
    }

    public int getMedicaidRowIndex(ProjectionSelectionDTO projSelDTO) {

        int projMasterId = projSelDTO.getProjectionId();
        int brandSid = projSelDTO.getBrandMasterId();
        int therapeutic = projSelDTO.getTherapeuticSid().getId();
        String ndc9Level = projSelDTO.getNdc9LevelNo();
        int count = 0;
        if (StringUtils.isNotBlank(ndc9Level)) {
            try {
                List<Object[]> medicaidIndex = queryUtil.loadMedicaidResultsTable(projMasterId, brandSid, "getMedicaidRowIndex", ndc9Level, therapeutic);
                if (!medicaidIndex.isEmpty()) {
                    count = Integer.parseInt(StringUtils.isNotBlank(String.valueOf(medicaidIndex.get(0))) ? String.valueOf(medicaidIndex.get(0)) : Constant.DASH);
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return count;
    }

    public String workSheetSetupCook(int itemMasterSid, String priceType, String workSheet, String ndc9, SessionDTO session) throws NamingException, SQLException {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        boolean status;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            } else {
                LOGGER.debug("Failed in datasource.");
            }
            if (connection != null) {
                LOGGER.debug("Got Connection " + connection.toString()
                        + ", itemMasterSid==========>" + itemMasterSid);

                statement = connection.prepareCall("{call PRC_NA_ADJUSTMENT(?,?,?,?,?,?)}");
                statement.setObject(1, itemMasterSid);
                statement.setObject(NumericConstants.TWO, priceType);
                statement.setObject(NumericConstants.THREE, workSheet);
                statement.setObject(NumericConstants.FOUR, session.getUserId());
                statement.setObject(NumericConstants.FIVE, session.getSessionId());
                statement.setObject(NumericConstants.SIX, ndc9);

                status = statement.execute();
                LOGGER.debug("procedure call ended  status--------->" + status);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return "SUCCESS";

    }

    public List<TableDTO> getWorksheetOverrideData(List<Object[]> list, ProjectionSelectionDTO projSelDTO, TableDTO medicaidDTO, String groupIndicator, DecimalFormat format) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<TableDTO> projDTOList = new ArrayList<>();
        Map<String, String[]> ampNotesMap = new HashMap<>();
        Map<String, String[]> bpNotesMap = new HashMap<>();
        Map<String, String[]> cpiNotesMap = new HashMap<>();
        Map<String, String[]> cpiUraNotesMap = new HashMap<>();
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;
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
                        if (projSelDTO.isExcel()) {
                            if (group.equalsIgnoreCase(groupIndicator.trim())) {

                                if (obj[NumericConstants.SIX] != null) {
                                    value = Double.valueOf(String.valueOf(obj[NumericConstants.SIX])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[NumericConstants.SIX]);
                                }
                                medicaidDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }

                        } else {
                            if (group.equalsIgnoreCase(AMP.getConstant())) {

                                if (obj[NumericConstants.SIX] != null) {
                                    notesArray[0] = Double.valueOf(String.valueOf(obj[NumericConstants.SIX])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[NumericConstants.SIX]);
                                } else {
                                    notesArray[0] = StringUtils.EMPTY;
                                }
                                value = notesArray[0];
                                notesArray[1] = obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.FIVE];
                                ampNotesMap.put(column, notesArray);
                            }
                            if (group.equalsIgnoreCase(BEST_PRICE_CAPS.getConstant())) {
                                if (obj[NumericConstants.SIX] != null) {
                                    notesArray[0] = Double.valueOf(String.valueOf(obj[NumericConstants.SIX])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[NumericConstants.SIX]);
                                } else {
                                    notesArray[0] = StringUtils.EMPTY;
                                }
                                value = notesArray[0];
                                notesArray[1] = obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.FIVE];
                                bpNotesMap.put(column, notesArray);
                            }
                            if (group.equalsIgnoreCase(CPI_U)) {
                                if (obj[NumericConstants.SIX] != null) {
                                    notesArray[0] = Double.valueOf(String.valueOf(obj[NumericConstants.SIX])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[NumericConstants.SIX]);
                                } else {
                                    notesArray[0] = StringUtils.EMPTY;
                                }
                                value = notesArray[0];
                                notesArray[1] = obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.FIVE];
                                cpiNotesMap.put(column, notesArray);
                            }
                            if (group.equalsIgnoreCase("CPI URA")) {
                                if (obj[NumericConstants.SIX] != null) {
                                    notesArray[0] = Double.valueOf(String.valueOf(obj[NumericConstants.SIX])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[NumericConstants.SIX]);
                                } else {
                                    notesArray[0] = StringUtils.EMPTY;
                                }
                                value = notesArray[0];
                                notesArray[1] = obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.FIVE];
                                cpiUraNotesMap.put(column, notesArray);
                            }
                            medicaidDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                    }
                }
            }
        }
        for (String columns : columnList) {
            medicaidDTO.addStringProperties(columns, CommonUtils.getFormattedValue(format, DASH.getConstant()));
        }
        projSelDTO.setNotesMap(ampNotesMap);
        projSelDTO.setSecondRowNotesMap(bpNotesMap);
        projSelDTO.setThirdRowNotesMap(cpiNotesMap);
        projSelDTO.setFourthRowNotesMap(cpiUraNotesMap);
        projDTOList.add(medicaidDTO);
        return projDTOList;
    }

    public List<TableDTO> getBpWorksheetData(List<Object[]> list, ProjectionSelectionDTO projSelDTO, TableDTO medicaidDTO, String groupIndicator, DecimalFormat format) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<TableDTO> projDTOList = new ArrayList<>();
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column;
                String group = StringUtils.EMPTY + obj[0];
                if (group.equalsIgnoreCase(groupIndicator.trim())) {

                    int year = Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]));
                    int period = Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(0);
                    String source = StringUtils.EMPTY + obj[NumericConstants.SEVEN];
                    if (ACTUALS_CAPS.getConstant().equals(source)) {

                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            if (medicaidDTO.getGroup().startsWith(Constant.FORECAST)) {
                                medicaidDTO.addStringProperties(column, DASH.getConstant());
                            } else {
                                String value = StringUtils.EMPTY + obj[1];
                                if (medicaidDTO.getGroup().startsWith(Constant.ADJUSTMENT_AMP) || medicaidDTO.getGroup().startsWith(Constant.ADJUSTMENT_BEST_PRICE) || medicaidDTO.getGroup().equals(ADJUSTMENT_CPI)
                                        || medicaidDTO.getGroup().startsWith(Constant.OVERRIDE_AMP) || medicaidDTO.getGroup().startsWith(Constant.OVERRIDE_BEST_PRICE) || medicaidDTO.getGroup().startsWith(Constant.OVERRIDE_CPI_URA)) {
                                    value = StringUtils.EMPTY;
                                }
                                value = CommonUtils.getFormattedValue(format, value);
                                String[] notesArray = new String[NumericConstants.TWO];
                                if (medicaidDTO.getGroup().startsWith(Constant.AMP)) {
                                    if (obj[NumericConstants.EIGHT] != null) {
                                        notesArray[0] = Double.valueOf(String.valueOf(obj[NumericConstants.EIGHT])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[NumericConstants.EIGHT]);
                                    } else {
                                        notesArray[0] = StringUtils.EMPTY;
                                    }
                                    medicaidDTO.addStringProperties(BASEYEAR.getConstant(), notesArray[0]);
                                    columnList.remove(BASEYEAR.getConstant());
                                    notesArray[1] = obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.FIVE];
                                    medicaidDTO.setBaseYearAmp(notesArray[0]);
                                    medicaidDTO.setBaseYearAmpNotes(notesArray[1]);
                                }
                                if (medicaidDTO.getGroup().startsWith(CPI_U)) {
                                    if (obj[NumericConstants.EIGHT] != null) {
                                        notesArray[0] = Double.valueOf(String.valueOf(obj[NumericConstants.EIGHT])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[NumericConstants.EIGHT]);
                                    } else {
                                        notesArray[0] = StringUtils.EMPTY;
                                    }
                                    medicaidDTO.addStringProperties(BASEYEAR.getConstant(), notesArray[0]);
                                    columnList.remove(BASEYEAR.getConstant());
                                    notesArray[1] = obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[NumericConstants.FIVE];
                                    medicaidDTO.setBaseYearCpi(notesArray[0]);
                                    medicaidDTO.setBaseYearCpiNotes(notesArray[1]);
                                }
                                medicaidDTO.addStringProperties(column, value);
                            }
                            columnList.remove(column);
                        }

                    }
                    if (PROJ_CAPS.getConstant().equals(source)) {
                        column = commonColumn + PROJECTIONS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            if (medicaidDTO.getGroup().startsWith("Historical")) {
                                medicaidDTO.addStringProperties(column, DASH.getConstant());
                            } else {
                                String value = StringUtils.EMPTY + obj[NumericConstants.TWO];
                                value = CommonUtils.getFormattedValue(format, value);
                                medicaidDTO.addStringProperties(column, value);
                            }
                            columnList.remove(column);
                        }
                    }
                }
            }
        }
        for (String columns : columnList) {
            medicaidDTO.addStringProperties(columns, CommonUtils.getFormattedValue(format, DASH.getConstant()));
        }

        projDTOList.add(medicaidDTO);
        return projDTOList;
    }
    
    public String getGroupName(String groupName) {
        return StringUtils.isBlank(groupName) || "null".equals(groupName) ? StringUtils.EMPTY : groupName;
    }

}