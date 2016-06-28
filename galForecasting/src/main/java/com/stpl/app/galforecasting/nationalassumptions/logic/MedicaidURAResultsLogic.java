/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.logic;

import com.stpl.app.galforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.SessionDTO;
import com.stpl.app.galforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.galforecasting.nationalassumptions.queryutils.MedicaidQueryUtils;
import static com.stpl.app.galforecasting.nationalassumptions.util.CommonUiUtils.getCommonColumnHeader;
import com.stpl.app.galforecasting.nationalassumptions.util.CommonUtils;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.IndicatorConstants.ACTUALS_CAPS;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.IndicatorConstants.BASEYEAR;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.IndicatorConstants.PROJ_CAPS;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.AMP;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.BEST_PRICE_CAPS;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.BOTH;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.DASH;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.PERCENTAGE;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.PERIOD;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.PRICE_TYPE;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.galforecasting.nationalassumptions.util.Constants.CommonConstants.*;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
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

    private String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    MedicaidQueryUtils queryUtil = new MedicaidQueryUtils();
    private static final DecimalFormat CUR_FOUR = new DecimalFormat("$0.0000");

    public List<TableDTO> getConfiguredMedicaidResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO, final Set<Container.Filter> filterSet) {
        List<TableDTO> resultList = new ArrayList<TableDTO>();
        if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant()) || projSelDTO.getActualsOrProjections().equals(ACTUALS.getConstant())) {
            projSelDTO.setActualsOrProjections("Actuals and Projections");
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

            String parentSid = parentDto.getNdc9();
            resultList = getMedicaidChildren(start, offset, projSelDTO, parentSid);
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

    public int getConfiguredMedicaidResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, final Set<Container.Filter> filterSet) {
        int count = 0;
        if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant()) || projSelDTO.getActualsOrProjections().equals(ACTUALS.getConstant())) {
            projSelDTO.setActualsOrProjections("Actuals and Projections");
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
                    count += Integer.parseInt((StringUtils.isNotBlank(String.valueOf(medicaidList.get(0))) ? String.valueOf(medicaidList.get(0)) : Constant.STRING_ONE));
                    medicaidList = null;
                }
            }
        }

        return count;
    }

    public List<TableDTO> getMedicaidResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("getMedicaidResults start=" + start + "   offset=" + offset);
        List<TableDTO> projDTOList = getMedicaid(projSelDTO, projSelDTO.getLevelNo());
        LOGGER.info("getMedicaidResults ends");
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

    public List<TableDTO> getMedicaid(ProjectionSelectionDTO projSelDTO, int parentLevelId) {
        LOGGER.info("getMedicaid method started ");
        int projMasterId = projSelDTO.getProjectionId();
        int brandSid = projSelDTO.getBrandMasterId();
        int therapeuticSid = projSelDTO.getTherapeuticSid().getId();

        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        try {
            if (StringUtils.isNotBlank(projSelDTO.getLevelValue())) {
                TableDTO medicaidDTO = new TableDTO();
                medicaidDTO.setGroup(projSelDTO.getMedicaidSelectedNdc());
                medicaidDTO.setParent(1);
                medicaidDTO.setNdc9(projSelDTO.getLevelValue());
                projDTOList.add(medicaidDTO);
            } else {
                SessionDTO session = new SessionDTO();
                session.setPageFlag(true);
                session.setOffset(projSelDTO.getPageOffSet());
                session.setStart(projSelDTO.getPageStart());
                List<Object[]> medicaidList = queryUtil.loadMedicaidParent(projMasterId, brandSid, projSelDTO.getLevelValue(), session, therapeuticSid);
                if (medicaidList != null) {
                    projDTOList = getCustomizedProjectionTotal(medicaidList, projSelDTO);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("getMedicaid method ends ");
        return projDTOList;
    }

    public List<TableDTO> getMedicaidChild(ProjectionSelectionDTO projSelDTO, String parentSid) {
        LOGGER.info("getMedicaidChild method started ");
        int projMasterId = projSelDTO.getProjectionId();
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        try {
            List<Object[]> medicaidList;
            if (projSelDTO.getVariables().contains(PERCENTAGE.getConstant())) {
                medicaidList = queryUtil.loadMedicaidResultsChild(projMasterId, parentSid, projSelDTO.getPriceTypeList(), true);
            } else {
                medicaidList = queryUtil.loadMedicaidResultsChild(projMasterId, parentSid, projSelDTO.getPriceTypeList(), false);
            }

            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                projDTOList = getCustMedicaidChild(medicaidList, projSelDTO);
            }
            if (!medicaidList.isEmpty()) {
                medicaidList = null;
            }
            if (projSelDTO.getPivotView().contains(PRICE_TYPE.getConstant())) {
                projDTOList = getPriceTypeChild(projSelDTO, parentSid);

            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("getMedicaidChild method ends ");
        return projDTOList;
    }

    public List<TableDTO> getPriceTypeChild(ProjectionSelectionDTO projSelDTO, String parentSid) {
        LOGGER.info("getPriceTypeChild method started ");
        int projMasterId = projSelDTO.getProjectionId();
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        List<Object[]> medicaidList = null;
        try {
            if (projSelDTO.getVariables().contains(PERCENTAGE.getConstant())) {
                medicaidList = queryUtil.loadMedicaidResultsChild(projMasterId, parentSid, projSelDTO.getPriceTypeList(), true);
            } else {
                medicaidList = queryUtil.loadMedicaidResultsChild(projMasterId, parentSid, projSelDTO.getPriceTypeList(), false);
            }
            projDTOList = getCustPriceTypeChild(medicaidList, projSelDTO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("getPriceTypeChild method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustomizedProjectionTotal(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {

        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        if (list != null && !list.isEmpty()) {

            for (Object list1 : list) {
                TableDTO medicaidDTO = new TableDTO();
                final Object[] obj = (Object[]) list1;
                String itemDesc=obj[1]==null?"":""+obj[1];
//                String itemNo=obj[2]==null?"":""+String.valueOf(obj[2]);//remove
                String value = "" ;
                if(StringUtils.isNotBlank(itemDesc)){
                    value +=itemDesc+", ";
                }
                value += obj[0];

                String ndc9 = StringUtils.EMPTY + obj[0];
                medicaidDTO.setGroup(value);
                medicaidDTO.setParent(1);
//                medicaidDTO.setItemMasterSid(Integer.valueOf(itemNo));//remove
                medicaidDTO.setNdc9(ndc9);
                projDTOList.add(medicaidDTO);
            }

        }
        return projDTOList;
    }

    public List<TableDTO> getCustMedicaidChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
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

            if ("LOWEST COMMERCIAL NET PRICE".equalsIgnoreCase(priceList.get(i))) {
                lowestNmPrice = true;
            }
            if ("BASIC URA".equalsIgnoreCase(priceList.get(i))) {
                basicUra = true;
            }
            if (Constant.CPIURA.equalsIgnoreCase(priceList.get(i))) {
                cpiUra = true;
            }
            if ("Total URA".equalsIgnoreCase(priceList.get(i))) {
                totalUra = true;
            }
        }
        if (!priceList.isEmpty()) {
            priceList = null;
        }
        if (wac) {
            List<TableDTO> wacList = getCustomizedMedicaidChild(list, projSelDTO, Constant.WAC);
            projDTOList.addAll(wacList);
            wacList = null;
        }
        if (amp) {
            List<TableDTO> ampList = getCustomizedMedicaidChild(list, projSelDTO, Constant.AMP);
            projDTOList.addAll(ampList);
            ampList = null;
        }
        if (bestPrice) {
            List<TableDTO> bpList = getCustomizedMedicaidChild(list, projSelDTO, Constant.Best_Price);
            projDTOList.addAll(bpList);
            bpList = null;
        }
        if (lowestNmPrice) {
            List<TableDTO> lowestNpList = getCustomizedMedicaidChild(list, projSelDTO, "Lowest Commercial Net Price");
            projDTOList.addAll(lowestNpList);
            lowestNpList = null;
        }
        if (basicUra) {
            List<TableDTO> uraList = getCustomizedMedicaidChild(list, projSelDTO, "Basic URA");
            projDTOList.addAll(uraList);
            uraList = null;
        }
        if (cpiUra) {
            List<TableDTO> cpiList = getCustomizedMedicaidChild(list, projSelDTO, Constant.CPIURA);
            projDTOList.addAll(cpiList);
            cpiList = null;
        }
        if (totalUra) {
            List<TableDTO> totalUraList = getCustomizedMedicaidChild(list, projSelDTO, "Total URA");
            projDTOList.addAll(totalUraList);
            totalUraList = null;
        }

        return projDTOList;
    }

    public List<TableDTO> getCustPriceTypeChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO) {
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

        List<TableDTO> wacList = getCustomizedPriceTypeChild(list, projSelDTO);
        projDTOList.addAll(wacList);
        return projDTOList;
    }

    public List<TableDTO> getCustomizedMedicaidChild(List<Object[]> list, ProjectionSelectionDTO projSelDTO, String groupIndicator) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        String projections = projSelDTO.getActualsOrProjections();
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        TableDTO gtsDTO = new TableDTO();
        gtsDTO.setParent(0);

        gtsDTO.setGroup(groupIndicator);

        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                String group = StringUtils.EMPTY + obj[6];
                if (group.equalsIgnoreCase(groupIndicator.trim())) {
                    int year = Integer.valueOf(String.valueOf(obj[4]));
                    int period = Integer.valueOf(String.valueOf(obj[5]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(0);
                    String source = StringUtils.EMPTY + obj[7];
                    if (PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables())) {
                        if (ACTUALS_CAPS.getConstant().equals(source)) {
                            if (projections.contains(ACTUALS.getConstant())) {
                                column = commonColumn + ACTUALS.getConstant();
                                if (projSelDTO.hasColumn(column)) {
                                    String value = StringUtils.EMPTY + obj[2];
                                    value = CommonUtils.getFormattedValue(PER_TWO, value);
                                    gtsDTO.addStringProperties(column, value);
                                    columnList.remove(column);
                                }
                            }
                        }
                        if (PROJ_CAPS.getConstant().equals(source)) {
                            if (projections.contains(PROJECTIONS.getConstant())) {
                                column = commonColumn + PROJECTIONS.getConstant();
                                if (projSelDTO.hasColumn(column)) {
                                    String value = StringUtils.EMPTY + obj[3];
                                    value = CommonUtils.getFormattedValue(PER_TWO, value);
                                    gtsDTO.addStringProperties(column, value);
                                    columnList.remove(column);
                                }
                            }
                        }
                    } else {
                        if (ACTUALS_CAPS.getConstant().equals(source)) {
                            if (projections.contains(ACTUALS.getConstant())) {
                                column = commonColumn + ACTUALS.getConstant();
                                if (projSelDTO.hasColumn(column)) {
                                    String value = StringUtils.EMPTY + obj[2];
                                    value = CommonUtils.getFormattedValue(CUR_ZERO, value);
                                    gtsDTO.addStringProperties(column, value);
                                    columnList.remove(column);
                                }
                            }
                        }
                        if (PROJ_CAPS.getConstant().equals(source)) {
                            if (projections.contains(PROJECTIONS.getConstant())) {
                                column = commonColumn + PROJECTIONS.getConstant();
                                if (projSelDTO.hasColumn(column)) {
                                    String value = StringUtils.EMPTY + obj[3];
                                    value = CommonUtils.getFormattedValue(CUR_ZERO, value);
                                    gtsDTO.addStringProperties(column, value);
                                    columnList.remove(column);
                                }
                            }
                        }
                    }
                }
            }

        }
        for (String columns : columnList) {
            gtsDTO.addStringProperties(columns, CommonUtils.getFormattedValue(PER_TWO, Constant.NULL));
        }
        if (!columnList.isEmpty()) {
            columnList = null;
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
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        TableDTO tableDTO;
        try {

            Map<String, String> actualColumns = new HashMap<String, String>();
            actualColumns.put(Constant.WAC, "wacActuals");
            actualColumns.put(Constant.AMP, "ampActuals");
            actualColumns.put(Constant.BEST_PRICE, "bestpriceActuals");
            actualColumns.put("LOWEST COMMERCIAL NET PRICE", "lowestcommercialnetpriceActuals");
            actualColumns.put("BASIC URA", "basicuraActuals");
            actualColumns.put(Constant.CPIURA, "cpiuraActuals");
            actualColumns.put(Constant.TOTAL_URA, "totaluraActuals");

            Map<String, String> projColumns = new HashMap<String, String>();
            projColumns.put(Constant.WAC, "wacProjections");
            projColumns.put(Constant.AMP, "ampProjections");
            projColumns.put(Constant.BEST_PRICE, "bestpriceProjections");
            projColumns.put("LOWEST COMMERCIAL NET PRICE", "lowestcommercialnetpriceProjections");
            projColumns.put("BASIC URA", "basicuraProjections");
            projColumns.put(Constant.CPIURA, "cpiuraProjections");
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

                        int year = Integer.valueOf(String.valueOf(obj[4]));
                        int period = Integer.valueOf(String.valueOf(obj[5]));
                        List<String> periodList = getCommonColumnHeader(frequencyDivision, year, period);
                        if ((selectedColumn.get(j)).contains(periodList.get(0))) {
                            String source = StringUtils.EMPTY + obj[7];
                            String column = StringUtils.EMPTY;
                            if (PERCENTAGE.getConstant().equalsIgnoreCase(projSelDTO.getVariables())) {

                                if (ACTUALS_CAPS.getConstant().equals(source)) {

                                    if (projections.contains(ACTUALS.getConstant())) {
                                        column = actualColumns.get(String.valueOf(obj[6]));
                                        if (projSelDTO.hasColumn(column)) {
                                            String value = StringUtils.EMPTY + obj[2];
                                            value = CommonUtils.getFormattedValue(PER_TWO, value);
                                            tableDTO.addStringProperties(column, value);
                                            columnList.remove(column);
                                        }
                                    }
                                }

                                if (PROJ_CAPS.getConstant().equals(source)) {

                                    if (projections.contains(PROJECTIONS.getConstant())) {
                                        column = projColumns.get(String.valueOf(obj[6]));
                                        if (projSelDTO.hasColumn(column)) {
                                            String value = StringUtils.EMPTY + obj[3];
                                            value = CommonUtils.getFormattedValue(PER_TWO, value);
                                            tableDTO.addStringProperties(column, value);
                                            columnList.remove(column);
                                        }
                                    }
                                }
                            } else {
                                if (ACTUALS_CAPS.getConstant().equals(source)) {
                                    if (projections.contains(ACTUALS.getConstant())) {
                                        column = actualColumns.get(String.valueOf(obj[6]));
                                        if (projSelDTO.hasColumn(column)) {
                                            String value = StringUtils.EMPTY + obj[2];
                                            value = CommonUtils.getFormattedValue(CUR_ZERO, value);
                                            tableDTO.addStringProperties(column, value);
                                            columnList.remove(column);
                                        }
                                    }
                                }
                                if (PROJ_CAPS.getConstant().equals(source)) {
                                    if (projections.contains(PROJECTIONS.getConstant())) {
                                        column = projColumns.get(String.valueOf(obj[6]));
                                        if (projSelDTO.hasColumn(column)) {
                                            String value = StringUtils.EMPTY + obj[3];
                                            value = CommonUtils.getFormattedValue(CUR_ZERO, value);
                                            tableDTO.addStringProperties(column, value);
                                            columnList.remove(column);
                                        }
                                    }
                                }
                            }
                            for (String columns : columnList) {
                                tableDTO.addStringProperties(columns, CommonUtils.getFormattedValue(PER_TWO, Constant.NULL));
                            }
                        }
                    }
                }
                projDTOList.add(tableDTO);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return projDTOList;
    }

    public List<TableDTO> getMedicaidChildren(int start, int offset, ProjectionSelectionDTO projSelDTO, String parentSid) {
        LOGGER.info("getMedicaidChildren start=" + start + "   offset=" + offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

        if ((projSelDTO.getPivotView().contains(PERIOD.getConstant())) && neededRecord > 0) {
            List<TableDTO> resultList = getMedicaidChild(projSelDTO, parentSid);
            for (int k = started; k < resultList.size() && neededRecord > 0; k++) {
                projDTOList.add(resultList.get(k));
                neededRecord--;
            }
            if (!resultList.isEmpty()) {
                resultList = null;
            }

        } else if ((projSelDTO.getPivotView().contains(PRICE_TYPE.getConstant())) && neededRecord > 0) {
            List<TableDTO> resultList = getPriceTypeChild(projSelDTO, parentSid);
            for (int k = started; k < resultList.size() && neededRecord > 0; k++) {
                projDTOList.add(resultList.get(k));
                neededRecord--;
            }
            if (!resultList.isEmpty()) {
                resultList = null;
            }
        }
        LOGGER.info("getMedicaidChildren ends");
        return projDTOList;
    }

    public List<TableDTO> getConfiguredMedicaidWorkSheetResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
        List<TableDTO> resultList = new ArrayList<TableDTO>();

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

            int parentSid = parentDto.getItemMasterSid();
            resultList = getMedicaidWorksheetChildren(start, offset, projSelDTO, parentSid);
        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);

            resultList = getMedicaidWorksheetResults(start, offset, projSelDTO);

        }

        return resultList;
    }

    public int getConfiguredMedicaidWorkSheetCount(Object parentId, ProjectionSelectionDTO projSelDTO) {
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

            if (Constant.Best_Price.equalsIgnoreCase(parentDto.getGroup()) || Constant.CPIURA.equalsIgnoreCase(parentDto.getGroup())) {
                count += 4;
            }
            if (Constant.AMP.equalsIgnoreCase(parentDto.getGroup())) {
                count += 3;
            }
            if ("Basic URA".equalsIgnoreCase(projSelDTO.getGroup()) || "CPI-U".equalsIgnoreCase(projSelDTO.getGroup()) || "Total URA".equalsIgnoreCase(projSelDTO.getGroup())) {
                count += 2;
            }

        } else {
            projSelDTO.setIsProjectionTotal(true);
            projSelDTO.setIsTotal(true);
            projSelDTO.setTreeLevelNo(0);
            projSelDTO.setLevelNo(0);
            projSelDTO.setGroup(StringUtils.EMPTY);

            count += 9;
        }
        return count;
    }

    public List<TableDTO> getMedicaidWorksheetResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("getMedicaidResults start=" + start + "   offset=" + offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getMedicaidWorksheet(projSelDTO);
            for (int k = started; k < resultList.size() && neededRecord > 0; k++) {
                projDTOList.add(resultList.get(k));
                neededRecord--;
            }
            if (!resultList.isEmpty()) {
                resultList = null;
            }
        }
        LOGGER.info("getMedicaidResults ends");
        return projDTOList;
    }

    public List<TableDTO> getMedicaidWorksheetChildren(int start, int offset, ProjectionSelectionDTO projSelDTO, int parentSid) {
        LOGGER.info("getMedicaidChildren start=" + start + "   offset=" + offset);
        int neededRecord = offset;
        int started = start;
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

        if (neededRecord > 0) {
            List<TableDTO> resultList = getMedicaidWorksheetChild(projSelDTO, parentSid);
            for (int k = started; k < resultList.size() && neededRecord > 0; k++) {
                projDTOList.add(resultList.get(k));
                neededRecord--;
            }
            if (!resultList.isEmpty()) {
                resultList = null;
            }
        }
        LOGGER.info("getMedicaidChildren ends");
        return projDTOList;
    }

    public List<TableDTO> getMedicaidWorksheetChild(ProjectionSelectionDTO projSelDTO, int parentSid) {
        LOGGER.info("getMedicaidChild method started ");
        int projMasterId = projSelDTO.getProjectionId();

        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        try {

            List<Object[]> medicaidList = queryUtil.loadMedicaidWorksheet(projMasterId, projSelDTO.getNdc9(), projSelDTO.isAdjust());
            projDTOList = getCustMedicaidWorksheetChild(projSelDTO, medicaidList);
            if (!medicaidList.isEmpty()) {
                medicaidList = null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("getMedicaidChild method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustMedicaidWorksheetChild(ProjectionSelectionDTO projSelDTO, List<Object[]> medicaidList) {
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

        boolean amp = false;
        boolean bestPrice = false;
        boolean ura = false;
        boolean cpi = false;
        boolean cpiUra = false;
        boolean totalUra = false;

        if (Constant.AMP.equalsIgnoreCase(projSelDTO.getGroup())) {
            amp = true;
        }
        if (Constant.Best_Price.equalsIgnoreCase(projSelDTO.getGroup())) {
            bestPrice = true;
        }
        if ("Basic URA".equalsIgnoreCase(projSelDTO.getGroup())) {
            ura = true;
        }
        if ("CPI-U".equalsIgnoreCase(projSelDTO.getGroup())) {
            cpi = true;
        }
        if (Constant.CPIURA.equalsIgnoreCase(projSelDTO.getGroup())) {
            cpiUra = true;
        }
        if ("Total URA".equalsIgnoreCase(projSelDTO.getGroup())) {
            totalUra = true;
        }

        if (amp) {

            TableDTO histAmp = new TableDTO();
            histAmp.setGroup("Historical AMP");
            histAmp.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, histAmp, AMP.getConstant(), CUR_FOUR));

            TableDTO forecastAmpDTO = new TableDTO();
            forecastAmpDTO.setGroup("Forecast AMP");
            forecastAmpDTO.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, forecastAmpDTO, AMP.getConstant(), CUR_FOUR));

            TableDTO adjustmentAmpDTO = new TableDTO();
            adjustmentAmpDTO.setGroup(Constant.ADJUSTMENT_AMP);
            adjustmentAmpDTO.setParent(0);
            projDTOList.addAll(getWorksheetOverrideData(medicaidList, projSelDTO, adjustmentAmpDTO, AMP.getConstant(), CUR_FOUR));

        }
        if (bestPrice) {
            TableDTO histBestPrice = new TableDTO();
            histBestPrice.setGroup("Historical Best Price");
            histBestPrice.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, histBestPrice, BEST_PRICE_CAPS.getConstant(), CUR_FOUR));

            TableDTO forecastBestPrice = new TableDTO();
            forecastBestPrice.setGroup("Forecast Best Price");
            forecastBestPrice.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, forecastBestPrice, BEST_PRICE_CAPS.getConstant(), CUR_FOUR));

            TableDTO lowNMBestPrice = new TableDTO();
            lowNMBestPrice.setGroup("Lowest Commercial Best Price");
            lowNMBestPrice.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, lowNMBestPrice, "Lowest Commercial Best Price", CUR_FOUR));

            TableDTO adjBestPrice = new TableDTO();
            adjBestPrice.setGroup(Constant.ADJUSTMENT_BEST_PRICE);
            adjBestPrice.setParent(0);
            projDTOList.addAll(getWorksheetOverrideData(medicaidList, projSelDTO, adjBestPrice, BEST_PRICE_CAPS.getConstant(), CUR_FOUR));
        }
        if (ura) {
            TableDTO histUra = new TableDTO();
            histUra.setGroup("Historical Basic URA");
            histUra.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, histUra, "BASIC URA", CUR_FOUR));

            TableDTO forecastUra = new TableDTO();
            forecastUra.setGroup("Forecast Basic URA");
            forecastUra.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, forecastUra, "BASIC URA", CUR_FOUR));

        }
        if (cpi) {
            TableDTO histCpi = new TableDTO();
            histCpi.setGroup("Historical CPI-U");
            histCpi.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, histCpi, "CPI-U", CUR_FOUR));

            TableDTO forecastCpi = new TableDTO();
            forecastCpi.setGroup("Forecast CPI-U");
            forecastCpi.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, forecastCpi, "CPI-U", CUR_FOUR));

        }
        if (cpiUra) {
            TableDTO histCpiUra = new TableDTO();
            histCpiUra.setGroup("Historical CPI URA");
            histCpiUra.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, histCpiUra, Constant.CPIURA, CUR_FOUR));

            TableDTO forecastCpiUra = new TableDTO();
            forecastCpiUra.setGroup("Forecast CPI URA");
            forecastCpiUra.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, forecastCpiUra, Constant.CPIURA, CUR_FOUR));

            TableDTO adjCpiAlt = new TableDTO();
            adjCpiAlt.setGroup("Adjustment CPI (Alt)");
            adjCpiAlt.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, adjCpiAlt, "Adjustment CPI (Alt)", CUR_FOUR));

            TableDTO adjCpi = new TableDTO();
            adjCpi.setGroup("Adjustment CPI");
            adjCpi.setParent(0);
            projDTOList.addAll(getWorksheetOverrideData(medicaidList, projSelDTO, adjCpi, "CPI-U", CUR_FOUR));
        }
        if (totalUra) {
            TableDTO histTotalUra = new TableDTO();
            histTotalUra.setGroup("Historical Total URA");
            histTotalUra.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, histTotalUra, Constant.TOTAL_URA, CUR_FOUR));

            TableDTO forecastTotalUra = new TableDTO();
            forecastTotalUra.setGroup("Forecast Total URA");
            forecastTotalUra.setParent(0);
            projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, forecastTotalUra, Constant.TOTAL_URA, CUR_FOUR));

        }
        return projDTOList;
    }

    public List<TableDTO> getMedicaidWorksheet(ProjectionSelectionDTO projSelDTO) {
        LOGGER.info("getMedicaidWorksheet method started ");

        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        int projMasterId = projSelDTO.getProjectionId();

        try {
            List<Object[]> medicaidList = queryUtil.loadMedicaidWorksheet(projMasterId, projSelDTO.getNdc9(), projSelDTO.isAdjust());
            projDTOList = getCustomizedMedicaidWorksheet(projSelDTO, medicaidList);
            if (!medicaidList.isEmpty()) {
                medicaidList = null;
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("getMedicaidWorksheet method ends ");
        return projDTOList;
    }

    public List<TableDTO> getCustomizedMedicaidWorksheet(ProjectionSelectionDTO projSelDTO, List<Object[]> medicaidList) {

        List<TableDTO> projDTOList = new ArrayList<TableDTO>();

        TableDTO ampDTO = new TableDTO();
        ampDTO.setGroup(Constant.AMP);
        ampDTO.setParent(1);
        projDTOList.addAll(getBpWorksheetData(medicaidList, projSelDTO, ampDTO, AMP.getConstant(), CUR_FOUR));

        TableDTO bestPriceDTO = new TableDTO();
        bestPriceDTO.setGroup(Constant.Best_Price);
        bestPriceDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, bestPriceDTO, BEST_PRICE_CAPS.getConstant(), CUR_FOUR));

        TableDTO baseUraDTO = new TableDTO();
        baseUraDTO.setGroup("Basic URA");
        baseUraDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, baseUraDTO, "BASIC URA", CUR_FOUR));

        TableDTO cpiUDTO = new TableDTO();
        cpiUDTO.setGroup("CPI-U");
        cpiUDTO.setParent(1);
        projDTOList.addAll(getBpWorksheetData(medicaidList, projSelDTO, cpiUDTO, "CPI-U", CUR_FOUR));

        TableDTO cpiDTO = new TableDTO();
        cpiDTO.setGroup(Constant.CPIURA);
        cpiDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, cpiDTO, Constant.CPIURA, CUR_FOUR));

        TableDTO totalUraDTO = new TableDTO();
        totalUraDTO.setGroup("Total URA");
        totalUraDTO.setParent(1);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, totalUraDTO, Constant.TOTAL_URA, CUR_FOUR));

        TableDTO wacDTO = new TableDTO();
        wacDTO.setGroup("WAC (CMS Unit Price)");
        wacDTO.setParent(0);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, wacDTO, Constant.WAC, CUR_FOUR));

        TableDTO wacIncreaseDTO = new TableDTO();
        wacIncreaseDTO.setGroup("WAC Increase % ");
        wacIncreaseDTO.setParent(0);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, wacIncreaseDTO, "WAC INCREASE %", PER_TWO));

        TableDTO cmsUnitsDTO = new TableDTO();
        cmsUnitsDTO.setGroup("CMS Units");
        cmsUnitsDTO.setParent(0);
        projDTOList.addAll(getWorksheetData(medicaidList, projSelDTO, cmsUnitsDTO, "CMS UNITS", NUM_TWO));

        return projDTOList;
    }

    public String medicaidProcSetupDataCook(int projectionId, int userId, int sessionId, String priceBasis) throws Exception {
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
                statement.setInt(1, projectionId);
                statement.setObject(2, priceBasis);
                statement.setInt(3, userId);
                statement.setInt(4, sessionId);
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
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                String group = StringUtils.EMPTY + obj[0];
                if (group.equalsIgnoreCase(groupIndicator.trim())) {

                    int year = Integer.valueOf(String.valueOf(obj[3]));
                    int period = Integer.valueOf(String.valueOf(obj[4]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(0);
                    String source = StringUtils.EMPTY + obj[7];
                    if (ACTUALS_CAPS.getConstant().equals(source)) {

                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            if (medicaidDTO.getGroup().startsWith(Constant.FORECAST)) {
                                medicaidDTO.addStringProperties(column, DASH.getConstant());
                            } else {
                                String value = StringUtils.EMPTY + obj[1];
                                if (medicaidDTO.getGroup().startsWith(Constant.ADJUSTMENT_AMP) || medicaidDTO.getGroup().startsWith(Constant.ADJUSTMENT_BEST_PRICE) || medicaidDTO.getGroup().equals("Adjustment CPI")) {
                                    value = StringUtils.EMPTY;
                                }
                                value = CommonUtils.getFormattedValue(format, value);
                                String[] notesArray = new String[2];
                                if (medicaidDTO.getGroup().startsWith("Historical AMP")) {
                                    if (obj[6] != null) {
                                        notesArray[0] = Double.valueOf(String.valueOf(obj[6])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[6]);
                                    } else {
                                        notesArray[0] = StringUtils.EMPTY;
                                    }
                                    medicaidDTO.addStringProperties(BASEYEAR.getConstant(), notesArray[0]);
                                    columnList.remove(BASEYEAR.getConstant());
                                    notesArray[1] = obj[5] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[5];
                                    medicaidDTO.setBaseYearAmp(notesArray[0]);
                                    medicaidDTO.setBaseYearAmpNotes(notesArray[1]);
                                }
                                if (medicaidDTO.getGroup().startsWith("Historical CPI-U")) {
                                    if (obj[6] != null) {
                                        notesArray[0] = Double.valueOf(String.valueOf(obj[6])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[6]);
                                    } else {
                                        notesArray[0] = StringUtils.EMPTY;
                                    }
                                    medicaidDTO.addStringProperties(BASEYEAR.getConstant(), notesArray[0]);
                                    columnList.remove(BASEYEAR.getConstant());
                                    notesArray[1] = obj[5] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[5];
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
                                String value = StringUtils.EMPTY + obj[2];
                                value = CommonUtils.getFormattedValue(format, value);
                                medicaidDTO.addStringProperties(column, value);
                            }
                            columnList.remove(column);
                        }
                    }
                }
            }
            list = null;
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
                    count = Integer.parseInt((StringUtils.isNotBlank(String.valueOf(medicaidIndex.get(0))) ? String.valueOf(medicaidIndex.get(0)) : Constant.DASH));
                    medicaidIndex = null;
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
        return count;
    }

    public String workSheetSetupCook(int itemMasterSid, String priceType, String workSheet, String ndc9) throws Exception {
        int userId = Long.valueOf((String) VaadinSession.getCurrent()
                .getAttribute(Constant.USER_ID)).intValue();
        Integer sessionId = (Integer) VaadinSession.getCurrent().getAttribute(SESSION_ID.getConstant());
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
                LOGGER.info("Failed in datasource.");
            }
            if (connection != null) {
                LOGGER.info("Got Connection " + connection.toString()
                        + ", itemMasterSid==========>" + itemMasterSid);

                statement = connection.prepareCall("{call PRC_NA_ADJUSTMENT(?,?,?,?,?,?)}");
                statement.setObject(1, itemMasterSid);
                statement.setObject(2, priceType);
                statement.setObject(3, workSheet);
                statement.setObject(4, userId);
                statement.setObject(5, sessionId);
                statement.setObject(6, ndc9);

                status = statement.execute();
                LOGGER.info("procedure call ended  status--------->" + status);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.gc();
        }
        return "SUCCESS";

    }

    public List<TableDTO> getWorksheetOverrideData(List<Object[]> list, ProjectionSelectionDTO projSelDTO, TableDTO medicaidDTO, String groupIndicator, DecimalFormat format) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        Map<String, String[]> ampNotesMap = new HashMap<String, String[]>();
        Map<String, String[]> bpNotesMap = new HashMap<String, String[]>();
        Map<String, String[]> cpiNotesMap = new HashMap<String, String[]>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                String group = StringUtils.EMPTY + obj[0];
                int year = Integer.valueOf(String.valueOf(obj[3]));
                int period = Integer.valueOf(String.valueOf(obj[4]));
                List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                String commonColumn = common.get(0);
                String source = StringUtils.EMPTY + obj[7];
                if (PROJ_CAPS.getConstant().equals(source)) {
                    column = commonColumn + PROJECTIONS.getConstant();
                    if (projSelDTO.hasColumn(column)) {
                        String value = StringUtils.EMPTY;
                        String[] notesArray = new String[2];
                        if (projSelDTO.isExcel()) {
                            if (group.equalsIgnoreCase(groupIndicator.trim())) {

                                if (obj[6] != null) {
                                    value = Double.valueOf(String.valueOf(obj[6])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[6]);
                                }
                                medicaidDTO.addStringProperties(column, value);
                                columnList.remove(column);
                            }

                        } else {
                            if (group.equalsIgnoreCase(AMP.getConstant())) {

                                if (obj[6] != null) {
                                    notesArray[0] = Double.valueOf(String.valueOf(obj[6])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[6]);
                                } else {
                                    notesArray[0] = StringUtils.EMPTY;
                                }
                                value = notesArray[0];
                                notesArray[1] = obj[5] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[5];
                                ampNotesMap.put(column, notesArray);
                            }
                            if (group.equalsIgnoreCase(BEST_PRICE_CAPS.getConstant())) {
                                if (obj[6] != null) {
                                    notesArray[0] = Double.valueOf(String.valueOf(obj[6])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[6]);
                                } else {
                                    notesArray[0] = StringUtils.EMPTY;
                                }
                                value = notesArray[0];
                                notesArray[1] = obj[5] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[5];
                                bpNotesMap.put(column, notesArray);
                            }
                            if (group.equalsIgnoreCase("CPI-U")) {
                                if (obj[6] != null) {
                                    notesArray[0] = Double.valueOf(String.valueOf(obj[6])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[6]);
                                } else {
                                    notesArray[0] = StringUtils.EMPTY;
                                }
                                value = notesArray[0];
                                notesArray[1] = obj[5] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[5];
                                cpiNotesMap.put(column, notesArray);
                            }
                            medicaidDTO.addStringProperties(column, value);
                            columnList.remove(column);
                        }
                    }
                }
            }
            list = null;
        }
        for (String columns : columnList) {
            medicaidDTO.addStringProperties(columns, CommonUtils.getFormattedValue(format, DASH.getConstant()));
        }
        projSelDTO.setNotesMap(ampNotesMap);
        projSelDTO.setSecondRowNotesMap(bpNotesMap);
        projSelDTO.setThirdRowNotesMap(cpiNotesMap);
        projDTOList.add(medicaidDTO);
        return projDTOList;
    }

    public List<TableDTO> getBpWorksheetData(List<Object[]> list, ProjectionSelectionDTO projSelDTO, TableDTO medicaidDTO, String groupIndicator, DecimalFormat format) {
        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<TableDTO> projDTOList = new ArrayList<TableDTO>();
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
        columnList.remove(Constant.GROUP);
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                final Object[] obj = (Object[]) list1;
                String column = StringUtils.EMPTY;
                String group = StringUtils.EMPTY + obj[0];
                if (group.equalsIgnoreCase(groupIndicator.trim())) {

                    int year = Integer.valueOf(String.valueOf(obj[3]));
                    int period = Integer.valueOf(String.valueOf(obj[4]));
                    List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
                    String commonColumn = common.get(0);
                    String source = StringUtils.EMPTY + obj[7];
                    if (ACTUALS_CAPS.getConstant().equals(source)) {

                        column = commonColumn + ACTUALS.getConstant();
                        if (projSelDTO.hasColumn(column)) {
                            if (medicaidDTO.getGroup().startsWith(Constant.FORECAST)) {
                                medicaidDTO.addStringProperties(column, DASH.getConstant());
                            } else {
                                String value = StringUtils.EMPTY + obj[1];
                                if (medicaidDTO.getGroup().startsWith(Constant.ADJUSTMENT_AMP) || medicaidDTO.getGroup().startsWith(Constant.ADJUSTMENT_BEST_PRICE) || medicaidDTO.getGroup().equals("Adjustment CPI")) {
                                    value = StringUtils.EMPTY;
                                }
                                value = CommonUtils.getFormattedValue(format, value);
                                String[] notesArray = new String[2];
                                if (medicaidDTO.getGroup().startsWith(Constant.AMP)) {
                                    if (obj[8] != null) {
                                        notesArray[0] = Double.valueOf(String.valueOf(obj[8])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[8]);
                                    } else {
                                        notesArray[0] = StringUtils.EMPTY;
                                    }
                                    medicaidDTO.addStringProperties(BASEYEAR.getConstant(), notesArray[0]);
                                    columnList.remove(BASEYEAR.getConstant());
                                    notesArray[1] = obj[5] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[5];
                                    medicaidDTO.setBaseYearAmp(notesArray[0]);
                                    medicaidDTO.setBaseYearAmpNotes(notesArray[1]);
                                }
                                if (medicaidDTO.getGroup().startsWith("CPI-U")) {
                                    if (obj[8] != null) {
                                        notesArray[0] = Double.valueOf(String.valueOf(obj[8])) == 0 ? StringUtils.EMPTY : CommonUtils.getFormattedValue(CommonUtils.CUR_FOUR, StringUtils.EMPTY + obj[8]);
                                    } else {
                                        notesArray[0] = StringUtils.EMPTY;
                                    }
                                    medicaidDTO.addStringProperties(BASEYEAR.getConstant(), notesArray[0]);
                                    columnList.remove(BASEYEAR.getConstant());
                                    notesArray[1] = obj[5] == null ? StringUtils.EMPTY : StringUtils.EMPTY + obj[5];
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
                                String value = StringUtils.EMPTY + obj[2];
                                value = CommonUtils.getFormattedValue(format, value);
                                medicaidDTO.addStringProperties(column, value);
                            }
                            columnList.remove(column);
                        }
                    }
                }
            }
            list = null;
        }
        for (String columns : columnList) {
            medicaidDTO.addStringProperties(columns, CommonUtils.getFormattedValue(format, DASH.getConstant()));
        }

        projDTOList.add(medicaidDTO);
        return projDTOList;
    }

}
