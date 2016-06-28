/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.projectionresults.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;

import com.stpl.app.galforecasting.abstractforecast.ForecastProjectionResults;
import com.stpl.app.galforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.projectionresults.logic.MProjectionResultsLogic;
import com.stpl.app.galforecasting.projectionresults.logic.tablelogic.ProjectionResultsTableLogic;
import com.stpl.app.galforecasting.projectionresults.utils.HeaderUtils;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.CommonConstants.SELECT_ONE;
import static com.stpl.app.galforecasting.utils.Constant.LabelConstants.CUSTOM;
import static com.stpl.app.galforecasting.utils.Constant.LabelConstants.CUSTOMER;
import static com.stpl.app.galforecasting.utils.Constant.LabelConstants.PRODUCT;
import com.stpl.app.galforecasting.utils.FunctionNameUtil;
import com.stpl.app.galforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.vaadin.server.VaadinSession;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.vaadin.ui.ExtCustomTable;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTreeTable;
import org.jboss.logging.Logger;

/**
 *
 * @author sathyaseelan.v
 */
public final class MProjectionResults extends ForecastProjectionResults {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = Logger.getLogger(MProjectionResults.class);
    /**
     * discountlist
     */
    List<List<String>> discountlist = new ArrayList<>();
    private final MProjectionResultsLogic mProjectionResultsLogic = new MProjectionResultsLogic();
    ExtPagedTreeTable leftTable;
    ExtPagedTreeTable rightTable;
    int ctot = 0;//1
    int tot = 0;//4
    int perIndex = 0;
    int rpuIndex = 0;//13
    int dolIndex = 0;

    public MProjectionResults(final SessionDTO session, final String screenName) throws Exception {
        super(session, screenName);
        configureOnMandated();
        setButtonSecurity();
        configureTabOrder();
    }

    public void configureOnMandated() {
        loadFrequencyDdlb();
        frequencyDdlb.focus();
        historyDdlb.setValue("4 Quarters");
        groupDdlb.setVisible(false);
        groupDdlbLabel.setVisible(false);
        initializeResultTable();
        if (loadProjectionSelection()) {
            configureResultTable();
        }
        addResultTable();
    }

    @Override
    protected void generateButtonLogic() {
        LOGGER.info("generate button click listener starts ");
        generated = true;
        firstGenerated = true;
        int pageNo = tableLogic.getItemsPerPage();
        if (loadProjectionSelection()) {
            tableVerticalLayout.removeAllComponents();
            tableLogic = new ProjectionResultsTableLogic();
            periodTableId = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            configureResultTable();
            addResultTable();
            generateLogic();
        } else {
            MessageBox.showPlain(Icon.ERROR, "Generate", "Please select a frequency and/or history and try again.", ButtonId.OK);
        }
        generated = false;
        LOGGER.info("generate button click listener ends ");
    }

    public boolean loadProjectionSelection() {
        boolean toRet = false;
        Object freq = frequencyDdlb.getValue();
        boolean toFreq = false;
        int historyNum = 0;
        if (freq != null) {
            if (!SELECT_ONE.getConstant().equals(freq.toString())) {
                toFreq = true;
                projectionSelectionDTO.setFrequency(freq.toString());
            }
        }
        Object hist = historyDdlb.getValue();
        boolean toHist = false;
        if (hist != null) {
            if (!SELECT_ONE.getConstant().equals(hist.toString())) {
                toHist = true;
                projectionSelectionDTO.setHistory(hist.toString());
                String[] his = historyDdlb.getValue().toString().split(" ");
                historyNum = Integer.valueOf(his[0]);
            }
        }
        if (toFreq && toHist) {
            toRet = true;
            projectionId = sessionDTO.getProjectionId();
            projectionSelectionDTO.setForecastDTO(sessionDTO.getForecastDTO());
            projectionSelectionDTO.setHistoryNum(historyNum);
            projectionSelectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(projectionSelectionDTO.getFrequency(), sessionDTO));
            projectionSelectionDTO.setProjection(StringUtils.EMPTY + projectionSelectionDTO.getProjectionNum());
            projectionSelectionDTO.setUserId(Integer.valueOf(sessionDTO.getUserId()));
            projectionSelectionDTO.setSessionId(Integer.valueOf(sessionDTO.getSessionId()));
            projectionSelectionDTO.setActualsOrProjections(actualOrProjectionsOpg.getValue().toString());

            projectionSelectionDTO.setProjectionId(sessionDTO.getProjectionId());
            projectionSelectionDTO.setSalesOrUnit(salesOrUnitsOpg.getValue().toString());
            projectionSelectionDTO.setProjectionOrder(periodOrderOpg.getValue().toString());
            projectionSelectionDTO.setPivotView(pivotViewOpg.getValue().toString());
            tablePanel.setCaption(pivotViewOpg.getValue().toString() + " chart View");
            projectionSelectionDTO.setCustRelationshipBuilderSid(sessionDTO.getCustRelationshipBuilderSid());
            projectionSelectionDTO.setProdRelationshipBuilderSid(sessionDTO.getProdRelationshipBuilderSid());
            projectionSelectionDTO.setCustomId(customId);
            projectionSelectionDTO.setView(viewOpg.getValue().toString());
            projectionSelectionDTO.setDiscountList(new ArrayList<>(discountlist));
            projectionSelectionDTO.setCustomerLevelNo(Integer.valueOf(sessionDTO.getCustomerLevelNumber()));
            projectionSelectionDTO.setProductLevelNo(Integer.valueOf(sessionDTO.getProductLevelNumber()));
            List<String> pcNameList = MProjectionResultsLogic.getProgramCodeName(projectionSelectionDTO.getProjectionId());
            projectionSelectionDTO.setDiscountProgramsList(pcNameList);
            viewChange(false);
        }
        return toRet;
    }

    @Override
    public void levelFilterDdlbChangeOption(boolean excelExport) {
        LOGGER.info("levelFilterDdlbChangeOption inititated");
        List<Object> levelHierarchy = CommonLogic.getLevelNoAndHierarchyNo(levelFilterDdlb.getValue());

        int levelNo = Integer.valueOf(String.valueOf(levelHierarchy.get(0)));
        if (levelNo < 0) {
            levelNo = 0;
        }
        String hierarchyNo = String.valueOf(levelHierarchy.get(1));
        projectionSelectionDTO.setIsFilter(true);
        if (levelNo == 0) {
            projectionSelectionDTO.setIsFilter(false);
        }
        if (excelExport) {
            loadExcelResultTable(levelNo, hierarchyNo);
        } else {
            tableLogic.clearAll();
            tableLogic.setRefresh(false);
            loadResultTable(levelNo, hierarchyNo);
            tableLogic.setRefresh(true);
        }
        LOGGER.info("levelFilterDdlbChangeOption ends");
    }

    @Override
    public void loadExcelResultTable(int levelNo, String hierarchyNo) {
        try {
            excelResultBean.removeAllItems();
            projectionSelectionDTO.setFilterLevelNo(levelNo);
            projectionSelectionDTO.setFilterHierarchyNo(hierarchyNo);
            projectionSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projectionSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);

            int count = mProjectionResultsLogic.getConfiguredProjectionResultsCount(new Object(), projectionSelectionDTO, true, projectionSelectionDTO);
            List<ProjectionResultsDTO> resultList = mProjectionResultsLogic.getConfiguredProjectionResults(new Object(), 0, count, projectionSelectionDTO);
            loadDataToContainer(resultList, null);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Generate Logic for Government Projection Results.
     */
    @Override
    public void generateLogic() {
        levelFilterDdlb.removeValueChangeListener(levelFilterChangeOption);
        loadLevelAndFilterValue();
        projectionSelectionDTO.setIsFilter(false);
        loadResultTable(0, StringUtils.EMPTY);
        levelFilterDdlb.addValueChangeListener(levelFilterChangeOption);
    }

    @SuppressWarnings("serial")
    private void loadResultTable(int levelNo, String hierarchyNo) {
        projectionSelectionDTO.setFilterLevelNo(levelNo);
        projectionSelectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        projectionSelectionDTO.clearNonFetchableIndex();
        tableLogic.setProjectionResultsData(projectionSelectionDTO);
    }

    private void doCollapseForChildTotal(int index, int totalIndex, boolean collapse) {
        for (int i = index; i < index + totalIndex; i++) {
            rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(i), collapse);
        }
    }

    @SuppressWarnings("serial")
    private void configureResultTable() {
        tableLogic.setTreeNodeMultiClick(false);
        tableLogic.setPageLength(100);
        tableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        fullHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getProjectionResultsLeftTableColumns(projectionSelectionDTO, fullHeader);
        rightHeader = HeaderUtils.getProjectionResultsRightTableColumns(projectionSelectionDTO, fullHeader);
        resultBeanContainer = new CustomTreeContainer<>(ProjectionResultsDTO.class);
        resultBeanContainer.setColumnProperties(fullHeader.getProperties());
        tableLogic.setContainerDataSource(resultBeanContainer);
        tableLogic.setTreeNodeMultiClick(false);
        leftTable = periodTableId
                .getLeftFreezeAsTable();
        rightTable = periodTableId
                .getRightFreezeAsTable();
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);
        periodTableId.setHeight("650px");
        leftTable.setHeight("650px");
        rightTable.setHeight("650px");
        leftTable.setColumnWidth("relationshipLevelName", 300);
        leftTable.setDoubleHeaderColumnWidth("relationshipLevelName", 300);

        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }

        periodTableId.setDoubleHeaderVisible(true);
        leftTable
                .setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable
                .setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));
        if (Constant.CUSTOM.equalsIgnoreCase(projectionSelectionDTO.getView())) {
            leftTable.setDoubleHeaderColumnHeader("group1", projectionSelectionDTO.getView());
        } else {
            leftTable.setDoubleHeaderColumnHeader("group1", projectionSelectionDTO.getView() + Constant.S_SMALL);
        }
        leftTable.setDoubleHeaderColumnAlignment("group1", ExtCustomTable.Align.CENTER);
        rightTable
                .setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable
                .setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

        periodTableId.getRightFreezeAsTable().setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        periodTableId.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps(), rightHeader.getDoubleHeaderMaps());
        if (projectionSelectionDTO.getPivotView().equals("Variable")) {
            rightTable.setColumnCollapsingAllowed(true);

            ctot = projectionSelectionDTO.getDiscountProgramsList().size();
            tot = 2 + (ctot * 2);
            perIndex = 8;
            rpuIndex = perIndex + tot + 1;
            dolIndex = rpuIndex + tot + 1;

            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(perIndex), false);//per
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(perIndex + 1), false);
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(perIndex + 2 + ctot), false);

            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(rpuIndex), false);//rpu
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(rpuIndex + 1), false);
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(rpuIndex + 2 + ctot), false);

            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(dolIndex), false);//dol
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(dolIndex + 1), false);
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(dolIndex + 2 + ctot), false);

            for (int i = 1; i <= tot; i++) {
                rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(i + perIndex), true);
                rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(i + rpuIndex), true);
                rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(i + dolIndex), true);
            }

            rightTable.addDoubleHeaderColumnExpandIconListener(new ExtCustomTable.DoubleHeaderColumnExpandIconListener() {

                private static final long serialVersionUID = -4215343675341144627L;

                public void doubleHeaderColumnExpandIcon(ExtCustomTable.DoubleHeaderColumnExpandIconEvent event) {
                    LOGGER.info("ProjectionResults addColumnExpandIconListener initiated ");

                    if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(perIndex))) {
                        if (event.isExpanded()) {
                            doCollapseForChildTotal(perIndex + 1, 1, !event.isExpanded());
                            doCollapseForChildTotal(perIndex + ctot + 2, 1, !event.isExpanded());
                        } else {
                            doCollapseForChildTotal(perIndex + 1, tot, !event.isExpanded());
                        }

                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(rpuIndex))) {
                        if (event.isExpanded()) {
                            doCollapseForChildTotal(rpuIndex + 1, 1, !event.isExpanded());
                            doCollapseForChildTotal(rpuIndex + ctot + 2, 1, !event.isExpanded());
                        } else {
                            doCollapseForChildTotal(rpuIndex + 1, tot, !event.isExpanded());
                        }

                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(dolIndex))) {
                        if (event.isExpanded()) {
                            doCollapseForChildTotal(dolIndex + 1, 1, !event.isExpanded());
                            doCollapseForChildTotal(dolIndex + ctot + 2, 1, !event.isExpanded());
                        } else {
                            doCollapseForChildTotal(dolIndex + 1, tot, !event.isExpanded());
                        }

                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(perIndex + 1))) {
                        doCollapseForChildTotal(perIndex + 2, ctot, !event.isExpanded());
                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(rpuIndex + 1))) {
                        doCollapseForChildTotal(rpuIndex + 2, ctot, !event.isExpanded());
                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(dolIndex + 1))) {
                        doCollapseForChildTotal(dolIndex + 2, ctot, !event.isExpanded());
                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(perIndex + ctot + 2))) {
                        doCollapseForChildTotal(perIndex + ctot + 3, ctot, !event.isExpanded());
                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(rpuIndex + ctot + 2))) {
                        doCollapseForChildTotal(rpuIndex + ctot + 3, ctot, !event.isExpanded());
                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(dolIndex + ctot + 2))) {
                        doCollapseForChildTotal(dolIndex + ctot + 3, ctot, !event.isExpanded());
                    }

                    LOGGER.info("ProjectionResults addColumnExpandIconListener ends ");
                }
            });
        }
    }

    protected void viewChange(boolean viewChange) {
        projectionSelectionDTO.setIsCustomHierarchy(false);
        customDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        if (viewOpg.getValue() != null) {
            if (CUSTOM.getConstant().equals(String.valueOf(viewOpg.getValue()))) {
                if (leftTable != null) {
                    leftTable.setDoubleHeaderColumnHeader("group1", String.valueOf(viewOpg.getValue()));
                    leftTable.setDoubleHeaderColumnAlignment("group1", ExtCustomTable.Align.CENTER);
                }
                levelDdlb.setEnabled(customId != 0);
                levelFilterDdlb.setEnabled(false);
                projectionSelectionDTO.setHierarchyIndicator(StringUtils.EMPTY);
                projectionSelectionDTO.setIsCustomHierarchy(true);
                if (firstGenerated && !generated) {
                    tableLogic.clearAll();
                    tableLogic.getControlTable().getContainerDataSource().removeAllItems();
                }
                loadCustomDDLB();
            } else {
                if (leftTable != null) {
                    leftTable.setDoubleHeaderColumnHeader("group1", String.valueOf(viewOpg.getValue()) + Constant.S_SMALL);
                    leftTable.setDoubleHeaderColumnAlignment("group1", ExtCustomTable.Align.CENTER);
                }
                levelDdlb.setEnabled(true);
                levelFilterDdlb.setEnabled(true);
                customIdToSelect = customId;

                projectionSelectionDTO.setIsCustomHierarchy(false);
                if (CUSTOMER.getConstant().equals(String.valueOf(viewOpg.getValue()))) {
                    projectionSelectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY);
                    projectionSelectionDTO.setView(String.valueOf(viewOpg.getValue()));
                    projectionSelectionDTO.setRelationshipBuilderSid(projectionSelectionDTO.getCustRelationshipBuilderSid());
                    if (viewChange) {
                        callGenerateLogic();
                    }
                } else if (PRODUCT.getConstant().equals(String.valueOf(viewOpg.getValue()))) {
                    projectionSelectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
                    projectionSelectionDTO.setView(String.valueOf(viewOpg.getValue()));
                    projectionSelectionDTO.setRelationshipBuilderSid(projectionSelectionDTO.getProdRelationshipBuilderSid());
                    if (viewChange) {
                        callGenerateLogic();
                    }
                }
            }
        }
    }

    public void loadDataToContainer(List<ProjectionResultsDTO> resultList, Object parentId) throws Exception {
        for (ProjectionResultsDTO dto : resultList) {
            excelResultBean.addBean(dto);
            if (parentId != null) {
                excelResultBean.setParent(dto, parentId);
            }
            if (dto.getParent() == 1) {
                excelResultBean.setChildrenAllowed(dto, true);
                addLowerLevelsForExport(dto);
            } else {
                excelResultBean.setChildrenAllowed(dto, false);
            }
        }
    }

    public void addLowerLevelsForExport(Object id) throws Exception {
        projectionSelectionDTO.setFilterLevelNo(0);
        projectionSelectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
        projectionSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        int count = mProjectionResultsLogic.getConfiguredProjectionResultsCount(id, projectionSelectionDTO, true, projectionSelectionDTO);
        List<ProjectionResultsDTO> resultList = mProjectionResultsLogic.getConfiguredProjectionResults(id, 0, count, projectionSelectionDTO);
        loadDataToContainer(resultList, id);
    }

    @Override
    protected void excelExportLogic() {
        configureExcelResultTable();
        levelFilterDdlbChangeOption(true);
        ForecastUI.EXCEL_CLOSE=true;
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), "Projection Results", "Projection Results", "Projection Results.xls", false);
        exp.export();
        tableVerticalLayout.removeComponent(exceltable);
    }

    /**
     *
     *
     * @param moduleAndTabName
     * @throws Exception
     */
    public void setButtonSecurity() throws Exception {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID));
        final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getGovernmentConstant() + "," + UISecurityUtil.PROJECTION_RESULTS);
        if (functionPsHM.get(FunctionNameUtil.GENERATE) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.GENERATE)).isFunctionFlag()) {
            generateBtn.setVisible(Boolean.FALSE);
            expandBtn.setVisible(Boolean.FALSE);
            collapseBtn.setVisible(Boolean.FALSE);
            newBtn.setVisible(Boolean.FALSE);
            editBtn.setVisible(Boolean.FALSE);
        }
    }

    public void configureTabOrder() {
        frequencyDdlb.focus();
        frequencyDdlb.setTabIndex(1);
        historyDdlb.setTabIndex(2);
        salesOrUnitsOpg.setTabIndex(3);
        actualOrProjectionsOpg.setTabIndex(4);
        periodOrderOpg.setTabIndex(5);
        pivotViewOpg.setTabIndex(6);
        generateBtn.setTabIndex(7);
        resetBtn.setTabIndex(8);
        levelDdlb.setTabIndex(9);
        expandBtn.setTabIndex(10);
        collapseBtn.setTabIndex(11);
        levelFilterDdlb.setTabIndex(12);
        viewOpg.setTabIndex(13);
        newBtn.setTabIndex(14);
        editBtn.setTabIndex(15);
        excelBtn.setTabIndex(16);
        graphBtn.setTabIndex(17);
    }
}
