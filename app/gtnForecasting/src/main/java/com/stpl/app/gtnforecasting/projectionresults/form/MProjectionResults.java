/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionresults.form;

import com.stpl.addons.tableexport.ExcelExport;

import com.stpl.app.gtnforecasting.abstractforecast.ForecastProjectionResults;
import com.stpl.app.gtnforecasting.dto.ProjectionResultsDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.projectionresults.logic.MProjectionResultsLogic;
import com.stpl.app.gtnforecasting.projectionresults.logic.tablelogic.ProjectionResultsTableLogic;
import com.stpl.app.gtnforecasting.projectionresults.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.CommonConstants.SELECT_ONE;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.CUSTOM;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.CUSTOMER;
import static com.stpl.app.gtnforecasting.utils.Constant.LabelConstants.PRODUCT;
import com.stpl.app.gtnforecasting.utils.FunctionNameUtil;
import com.stpl.app.gtnforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.vaadin.server.VaadinSession;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.ExtCustomTable;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.extfilteringtable.ExtPagedTreeTable;
import com.stpl.ifs.ui.util.NumericConstants;
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
    protected List<List<String>> discountlist = new ArrayList<>();
     /**
     * left table
     */
    protected ExtPagedTreeTable leftTable;
     /**
     * right table
     */
    protected ExtPagedTreeTable rightTable;
    protected int ctot = 0;//1
    protected int tot = 0;//4
    protected int perIndex = 0;
    protected int rpuIndex = 0;//13
    protected int dolIndex = 0;
    protected boolean canLoad = true;

    public MProjectionResults(final SessionDTO session, final String screenName) throws SystemException, PortalException  {
        super(session, screenName);
    }

    public void configureOnMandated() {
        List<String> pcNameList = MProjectionResultsLogic.getProgramCodeName(sessionDTO.getProjectionId());
        projectionSelectionDTO.setDiscountProgramsList(pcNameList);
        loadFrequencyDdlb();
        frequencyDdlb.focus();
        historyDdlb.setValue(Constant.FOUR_QUARTERS);
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
        LOGGER.debug("generate button click listener starts ");
        generated = true;
        firstGenerated = true;
        if (loadProjectionSelection()) {
            tableVerticalLayout.removeAllComponents();
            MProjectionResultsLogic logic=tableLogic.getmProjectionResultsLogic();
            tableLogic = new ProjectionResultsTableLogic();
            tableLogic.setmProjectionResultsLogic(logic);
            periodTableId = new FreezePagedTreeTable(tableLogic);
            initializeResultTable();
            configureResultTable();
            addResultTable();
            generateLogic();
        } else {
            MessageBox.showPlain(Icon.ERROR, "Generate", "Please select a frequency and/or history and try again.", ButtonId.OK);
        }
        generated = false;
        LOGGER.debug("generate button click listener ends ");
    }

    public boolean loadProjectionSelection() {
        boolean toRet = false;
        Object freq = frequencyDdlb.getValue();
        boolean toFreq = false;
        int historyNum = 0;
            if ((freq != null) && (!SELECT_ONE.getConstant().equals(freq.toString()))) {
                toFreq = true;
                projectionSelectionDTO.setFrequency(freq.toString());
            }
        Object hist = historyDdlb.getValue();
        boolean toHist = false;
            if ((hist != null) && (!SELECT_ONE.getConstant().equals(hist.toString()))) {
                toHist = true;
                projectionSelectionDTO.setHistory(hist.toString());
                String[] his = historyDdlb.getValue().toString().split(" ");
                historyNum = Integer.valueOf(his[0]);
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
            viewChange(false);
        }
        return toRet;
    }

    @Override
    public void levelFilterDdlbChangeOption(boolean excelExport) {
        LOGGER.debug("levelFilterDdlbChangeOption inititated");
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
        LOGGER.debug("levelFilterDdlbChangeOption ends");
    }

    @Override
    public void loadExcelResultTable(int levelNo, String hierarchyNo) {
        try {
            excelResultBean.removeAllItems();
            projectionSelectionDTO.setFilterLevelNo(levelNo);
            projectionSelectionDTO.setFilterHierarchyNo(hierarchyNo);
            projectionSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projectionSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);

            int count = tableLogic.getmProjectionResultsLogic().getConfiguredProjectionResultsCount(new Object(), projectionSelectionDTO, true, projectionSelectionDTO);
            List<ProjectionResultsDTO> resultList = tableLogic.getmProjectionResultsLogic().getConfiguredProjectionResults(new Object(), 0, count, projectionSelectionDTO);
            loadDataToContainer(resultList, null);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex);
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
        tableLogic.setPageLength(NumericConstants.HUNDRED);
        tableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        tableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        fullHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getProjectionResultsLeftTableColumns( fullHeader);
        rightHeader = HeaderUtils.getProjectionResultsRightTableColumns(projectionSelectionDTO, fullHeader);
        resultBeanContainer = new ExtTreeContainer<>(ProjectionResultsDTO.class,ExtContainer.DataStructureMode.MAP);
        resultBeanContainer.setColumnProperties(fullHeader.getProperties());
        tableLogic.setContainerDataSource(resultBeanContainer);
        tableLogic.setTreeNodeMultiClick(false);
        leftTable = periodTableId
                .getLeftFreezeAsTable();
        rightTable = periodTableId
                .getRightFreezeAsTable();
        leftTable.setImmediate(true);
        rightTable.setImmediate(true);
        periodTableId.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setHeight(Constant.SIX_FIFTY_PX);
        rightTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setColumnWidth("relationshipLevelName", NumericConstants.THREE_HUNDRED);
        leftTable.setDoubleHeaderColumnWidth("relationshipLevelName", NumericConstants.THREE_HUNDRED);

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
        if (Constant.CUSTOM_LABEL.equalsIgnoreCase(projectionSelectionDTO.getView())) {
            leftTable.setDoubleHeaderColumnHeader(Constant.GROUP1_SMALL, projectionSelectionDTO.getView());
        } else {
            leftTable.setDoubleHeaderColumnHeader(Constant.GROUP1_SMALL, projectionSelectionDTO.getView() + Constant.S_SMALL);
        }
        leftTable.setDoubleHeaderColumnAlignment(Constant.GROUP1_SMALL, ExtCustomTable.Align.CENTER);
        rightTable
                .setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable
                .setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

        periodTableId.getRightFreezeAsTable().setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        periodTableId.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps(), rightHeader.getDoubleHeaderMaps());
        if (projectionSelectionDTO.getPivotView().equals("Variable")) {
            rightTable.setColumnCollapsingAllowed(true);

            ctot = projectionSelectionDTO.getDiscountProgramsList().size();
            tot = NumericConstants.TWO + (ctot * NumericConstants.TWO);
            perIndex = NumericConstants.EIGHT;
            rpuIndex = perIndex + tot + 1;
            dolIndex = rpuIndex + tot + 1;

            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(perIndex), false);//per
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(perIndex + 1), false);
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(perIndex + NumericConstants.TWO + ctot), false);

            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(rpuIndex), false);//rpu
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(rpuIndex + 1), false);
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(rpuIndex + NumericConstants.TWO + ctot), false);

            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(dolIndex), false);//dol
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(dolIndex + 1), false);
            rightTable.setDoubleHeaderColumnExpandIcon(rightHeader.getDoubleColumns().get(dolIndex + NumericConstants.TWO + ctot), false);

            for (int i = 1; i <= tot; i++) {
                rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(i + perIndex), true);
                rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(i + rpuIndex), true);
                rightTable.setDoubleHeaderColumnCollapsed(rightHeader.getDoubleColumns().get(i + dolIndex), true);
            }

            rightTable.addDoubleHeaderColumnExpandIconListener(new ExtCustomTable.DoubleHeaderColumnExpandIconListener() {

                private static final long serialVersionUID = -4215343675341144627L;

                @Override
                public void doubleHeaderColumnExpandIcon(ExtCustomTable.DoubleHeaderColumnExpandIconEvent event) {
                    LOGGER.debug("ProjectionResults addColumnExpandIconListener initiated ");

                    if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(perIndex))) {
                        if (event.isExpanded()) {
                            doCollapseForChildTotal(perIndex + 1, 1, !event.isExpanded());
                            doCollapseForChildTotal(perIndex + ctot + NumericConstants.TWO, 1, !event.isExpanded());
                        } else {
                            doCollapseForChildTotal(perIndex + 1, tot, !event.isExpanded());
                        }

                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(rpuIndex))) {
                        if (event.isExpanded()) {
                            doCollapseForChildTotal(rpuIndex + 1, 1, !event.isExpanded());
                            doCollapseForChildTotal(rpuIndex + ctot + NumericConstants.TWO, 1, !event.isExpanded());
                        } else {
                            doCollapseForChildTotal(rpuIndex + 1, tot, !event.isExpanded());
                        }

                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(dolIndex))) {
                        if (event.isExpanded()) {
                            doCollapseForChildTotal(dolIndex + 1, 1, !event.isExpanded());
                            doCollapseForChildTotal(dolIndex + ctot + NumericConstants.TWO, 1, !event.isExpanded());
                        } else {
                            doCollapseForChildTotal(dolIndex + 1, tot, !event.isExpanded());
                        }

                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(perIndex + 1))) {
                        doCollapseForChildTotal(perIndex + NumericConstants.TWO, ctot, !event.isExpanded());
                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(rpuIndex + 1))) {
                        doCollapseForChildTotal(rpuIndex + NumericConstants.TWO, ctot, !event.isExpanded());
                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(dolIndex + 1))) {
                        doCollapseForChildTotal(dolIndex + NumericConstants.TWO, ctot, !event.isExpanded());
                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(perIndex + ctot + NumericConstants.TWO))) {
                        doCollapseForChildTotal(perIndex + ctot + NumericConstants.THREE, ctot, !event.isExpanded());
                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(rpuIndex + ctot + NumericConstants.TWO))) {
                        doCollapseForChildTotal(rpuIndex + ctot + NumericConstants.THREE, ctot, !event.isExpanded());
                    } else if (event.getPropertyId().equals(rightHeader.getDoubleColumns().get(dolIndex + ctot + NumericConstants.TWO))) {
                        doCollapseForChildTotal(dolIndex + ctot + NumericConstants.THREE, ctot, !event.isExpanded());
                    }

                    LOGGER.debug("ProjectionResults addColumnExpandIconListener ends ");
                }
            });
        }
    }

    @Override
    protected void viewChange(boolean viewChange) {
        projectionSelectionDTO.setIsCustomHierarchy(false);
        customDdlb.setEnabled(false);
        editBtn.setEnabled(false);
        if (viewOpg.getValue() != null) {
            if (CUSTOM.getConstant().equals(String.valueOf(viewOpg.getValue()))) {
                if (leftTable != null) {
                    leftTable.setDoubleHeaderColumnHeader(Constant.GROUP1_SMALL, String.valueOf(viewOpg.getValue()));
                    leftTable.setDoubleHeaderColumnAlignment(Constant.GROUP1_SMALL, ExtCustomTable.Align.CENTER);
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
                    leftTable.setDoubleHeaderColumnHeader(Constant.GROUP1_SMALL, String.valueOf(viewOpg.getValue()) + Constant.S_SMALL);
                    leftTable.setDoubleHeaderColumnAlignment(Constant.GROUP1_SMALL, ExtCustomTable.Align.CENTER);
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

    public void loadDataToContainer(List<ProjectionResultsDTO> resultList, Object parentId) throws PortalException, SystemException  {
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

    public void addLowerLevelsForExport(Object id) throws PortalException, SystemException  {
        projectionSelectionDTO.setFilterLevelNo(0);
        projectionSelectionDTO.setFilterHierarchyNo(StringUtils.EMPTY);
        projectionSelectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionSelectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        int count =tableLogic.getmProjectionResultsLogic().getConfiguredProjectionResultsCount(id, projectionSelectionDTO, true, projectionSelectionDTO);
        List<ProjectionResultsDTO> resultList = tableLogic.getmProjectionResultsLogic().getConfiguredProjectionResults(id, 0, count, projectionSelectionDTO);
        loadDataToContainer(resultList, id);
    }

    @Override
    protected void excelExportLogic() {
        configureExcelResultTable();
        levelFilterDdlbChangeOption(true);
        ForecastUI.setEXCEL_CLOSE(true);
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(exceltable), Constant.PROJECTION_RESULTS, Constant.PROJECTION_RESULTS, "Projection_Results.xls", false);
        exp.export();
        tableVerticalLayout.removeComponent(exceltable);
    }

    /**
     *
     *
     * @param moduleAndTabName
     * @throws Exception
     */
    public void setButtonSecurity() {
        try {
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
        } catch (PortalException | SystemException ex) {
            java.util.logging.Logger.getLogger(MProjectionResults.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void configureTabOrder() {
        frequencyDdlb.focus();
        frequencyDdlb.setTabIndex(1);
        historyDdlb.setTabIndex(NumericConstants.TWO);
        salesOrUnitsOpg.setTabIndex(NumericConstants.THREE);
        actualOrProjectionsOpg.setTabIndex(NumericConstants.FOUR);
        periodOrderOpg.setTabIndex(NumericConstants.FIVE);
        pivotViewOpg.setTabIndex(NumericConstants.SIX);
        generateBtn.setTabIndex(NumericConstants.SEVEN);
        resetBtn.setTabIndex(NumericConstants.EIGHT);
        levelDdlb.setTabIndex(NumericConstants.NINE);
        expandBtn.setTabIndex(NumericConstants.TEN);
        collapseBtn.setTabIndex(NumericConstants.ELEVEN);
        levelFilterDdlb.setTabIndex(NumericConstants.TWELVE);
        viewOpg.setTabIndex(NumericConstants.THIRTEEN);
        newBtn.setTabIndex(NumericConstants.FOURTEEN);
        editBtn.setTabIndex(NumericConstants.FIFTEEN);
        excelBtn.setTabIndex(NumericConstants.SIXTEEN);
        graphBtn.setTabIndex(NumericConstants.SEVENTEEN);
    }

    public void configureScreen() {
        if (canLoad) {
            configureOnMandated();
            setButtonSecurity();
            configureTabOrder();
            canLoad = false;
        }
    }
}
