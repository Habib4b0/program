/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.salesprojection.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.abstractforecast.ForecastSalesProjection;

import com.stpl.app.galforecasting.dto.SalesRowDto;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.lookups.MPmpyCalculator;
import com.stpl.app.galforecasting.salesprojection.logic.tablelogic.MSalesProjectionTableLogic;
import com.stpl.app.galforecasting.salesprojection.utils.HeaderUtils;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastUI;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;

import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import com.stpl.app.galforecasting.utils.FunctionNameUtil;
import com.stpl.app.galforecasting.utils.UISecurityUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import static com.stpl.app.utils.Constants.CommonConstants.NULL;
import static com.stpl.app.utils.Constants.CommonConstants.SELECT_ONE;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.util.ExtCustomTableHolder;
import static com.stpl.ifs.util.constants.GlobalConstants.*;
import com.stpl.portal.kernel.exception.PortalException;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;
import org.jboss.logging.Logger;

/**
 * Mandated Sales Projection.
 *
 * @author sibi
 */
public class MSalesProjection extends ForecastSalesProjection {

    String screenName;
    private static final Logger LOGGER = Logger.getLogger(MSalesProjection.class);
    public static Map<String, Integer> rowCountMap = new HashMap<>();
    boolean generated = false;
    boolean firstGenerated = false;
    List<String> projectedPeriodList = new ArrayList();
    SalesRowDto salesPMPYDTO = new SalesRowDto();
    Set<String> tableHirarechyNos = new HashSet<String>();

    public MSalesProjection(SessionDTO session, String screenName) throws Exception {
        super(session, screenName);
        this.screenName = screenName;

        init();
    }

    public void init() throws Exception {
        projectionDTO.setSessionDTO(session);
        projectionDTO.setRowsPerLevelItem(salesLogic.getHistoryAndProjectionCount(session, projectionDTO));
        configureProjectionDTO();
        generateBtnLogic(null);
        super.configureGraph();
        setButtonSecurity();
    }

    @Override
    protected void levelFilterDdlbChangeOption() {
        if (levelFilter.getValue() != null) {
            projectionDTO.setIsFilter(true);
            projectionDTO.setLevelFilter(true);
            projectionDTO.setLevelFilterValue(String.valueOf(UiUtils.parseStringToInteger(String.valueOf(levelFilter.getValue()).split("-")[0].trim())));
            projectionDTO.setFilterLevelNo(Integer.valueOf(projectionDTO.getLevelFilterValue()));
            mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
            projectionDTO.setLevelFilter(false);
        } else {
            projectionDTO.setIsFilter(false);
            projectionDTO.setLevelFilter(false);
            projectionDTO.setFilterLevelNo(0);
            projectionDTO.setLevelFilterValue(StringUtils.EMPTY);
            mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
        }
    }

    @Override
    protected void excelExportLogic() {
        try {
            configureExcelResultTable();
            levelFilterDdlbChangeOption(true);
            excelTable.setRefresh(Boolean.TRUE);
            if (excelTable.size() > 0) {
                ForecastUI.EXCEL_CLOSE=true;
                ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(excelTable), "Sales Projection", "Sales Projection", "Sales Projection.xls", false);
                exp.export();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void enableDisableFields() {
    }

    @Override
    protected void populateBtnLogic() {
        populateButtonLogic();
    }

    @Override
    protected void adjustmentLogic() {
    }

    @Override
    protected void calculateLogic() {
        try {
            calculateButtonLogic();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    protected void generateBtnLogic(Button.ClickEvent event) {
        try {
            if (checkSelection()) {
                LOGGER.info("generate button click listener starts ");
                generated = true;
                firstGenerated = true;
                tableLayout.removeAllComponents();
                mSalesProjectionTableLogic = new MSalesProjectionTableLogic();
                resultsTable = new FreezePagedTreeTable(mSalesProjectionTableLogic);
                super.initializeResultTable();
                configureResultTable();
                projectionDTO.setRowsPerLevelItem(salesLogic.getHistoryAndProjectionCount(projectionDTO.getSessionDTO(), projectionDTO));
                addResultTable();
                generateLogic();
                generated = false;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("generate button click listener ends ");

    }

    @Override
    protected void massUpdateLogic() {
        if ((Constant.LabelConstants.DISABLE).equals(massUpdate.getValue())) {
            fieldDdlb.setValue(Constant.SELECT_ONE);
            fieldDdlb.setEnabled(false);
            valueDdlb.setEnabled(false);
            valueTxt.setEnabled(false);
            startPeriod.setEnabled(false);
            endPeriod.setEnabled(false);
            populate.setEnabled(false);

        } else {
            fieldDdlb.setEnabled(true);
            valueDdlb.setEnabled(true);
            valueTxt.setEnabled(true);
            startPeriod.setEnabled(true);
            endPeriod.setEnabled(true);
            populate.setEnabled(true);
        }
    }

    @Override
    protected void viewChangeOption() {
    }

    @Override
    protected void expandButtonLogic() {
        try {
            if (!Constant.NULL.equals(String.valueOf(level.getValue()))) {
                projectionDTO.setExpandCollapseFlag(Boolean.TRUE);
                expandCollapseLevelOption(true, level.getValue());
                projectionDTO.setExpandCollapseFlag(Boolean.FALSE);
            } else {
                projectionDTO.setExpandCollapseFlag(Boolean.FALSE);
                AbstractNotificationUtils.getErrorNotification("No Level Selected", "Please select a Level from the drop down.");
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void collapseButtonLogic() {
        mSalesProjectionTableLogic.setRefresh(false);
        expandCollapseLevelOption(false, level.getValue());
        mSalesProjectionTableLogic.setRefresh(true);
    }

    @Override
    protected void fieldDdlbLogic() {
        if (Constant.ACCOUNT_GROWTH.equals(fieldDdlb.getValue()) || Constant.PRODUCT_GROWTH.equals(fieldDdlb.getValue())) {
            valueTxt.setValue(StringUtils.EMPTY);
            startPeriod.setValue(Constant.SELECT_ONE);
            endPeriod.setValue(Constant.SELECT_ONE);
            valueTxt.setVisible(true);
        }
    }

    @Override
    protected void resetBtnLogic() {
        if (nmFrequencyDdlb.getValue().equals(MONTHLY.getConstant())
                || (nmFrequencyDdlb.getValue().equals(QUARTERLY.getConstant()))
                || (nmFrequencyDdlb.getValue().equals(ANNUAL.getConstant()))
                || (nmFrequencyDdlb.getValue().equals(SEMI_ANNUAL.getConstant()))) {
            nmFrequencyDdlb.setValue(QUARTERLY.getConstant());
            historyDdlb.setValue(4);
        }
        actualsProjections.select(Constant.ACTUALS);
        proPeriodOrd.select(Constant.ASCENDING);
        variables.removeAllItems();
        variables.addItem(Constant.SALES_SMALL);
        variables.addItem(Constant.UNITS_SMALL);
        variables.addItem(Constant.PRODUCT_GROWTH);
        variables.addItem(Constant.ACCOUNT_GROWTH);
        variables.select(Constant.SALES_SMALL);
    }

    @Override
    protected void pmpyLogic() {

        boolean contractSelected = false;
        boolean hasActuals = false;
        final String hierarchyNo;
        int tempContractLevel = 0;
        int tempCustomerLevel = 0;
        int i = 0;
        for (Object obj : rightTable.getItemIds()) {
            SalesRowDto dto = getBeanFromId(obj);
            if ((Boolean) dto.getPropertyValue(Constant.CHECK) && (("CONTRACT".equalsIgnoreCase(dto.getHierarchyLevel()) || Constant.CONTRACT.equalsIgnoreCase(dto.getHierarchyLevel()))
                    || ("CUSTOMER".equalsIgnoreCase(dto.getHierarchyLevel()) || "customer".equalsIgnoreCase(dto.getHierarchyLevel())))) {
                contractSelected = true;
                i++;
                if ("CONTRACT".equalsIgnoreCase(dto.getHierarchyLevel()) || Constant.CONTRACT.equalsIgnoreCase(dto.getHierarchyLevel())) {
                    tempContractLevel++;
                }
                if ("CUSTOMER".equalsIgnoreCase(dto.getHierarchyLevel()) || "customer".equalsIgnoreCase(dto.getHierarchyLevel())) {
                    tempCustomerLevel++;
                }
                for (Object key : dto.getProperties().keySet()) {
                    if (String.valueOf(key).contains("Actual")) {
                        String value = String.valueOf(dto.getProperties().get(key));
                        if (!value.equalsIgnoreCase("-") && !value.equalsIgnoreCase("0.00") && !value.equalsIgnoreCase("$0") && !value.equalsIgnoreCase(DASH)) {
                            hasActuals = true;
                        }
                    }
                }
                salesPMPYDTO = dto;
            }

        }
        if (contractSelected && (tempContractLevel != 0 || tempCustomerLevel != 0) && (tempCustomerLevel <= 1 || tempCustomerLevel <= 1)) {
            if (!hasActuals) {
                hierarchyNo = salesPMPYDTO.getHierarchyNo();
                tableHirarechyNos.add(mSalesProjectionTableLogic.getTreeLevelonCurrentPage(salesPMPYDTO));
                final MPmpyCalculator pmpyCalc = new MPmpyCalculator(session, projectedPeriodList);
                getUI().addWindow(pmpyCalc);
                pmpyCalc.addCloseListener(new Window.CloseListener() {
                    public void windowClose(Window.CloseEvent e) {
                        if (pmpyCalc.isImport) {
                            updateOnPMPYImport(hierarchyNo, pmpyCalc.getUpdateValue(), pmpyCalc.getUpdatePeriod(), pmpyCalc.isSalesOrUnits, salesPMPYDTO);
                        }
                    }
                });
            } else {
                AbstractNotificationUtils.getErrorNotification("Contract LookUp", "Selected contract should not have  History");
            }
        } else {
            if (contractSelected && i > 1) {
                AbstractNotificationUtils.getErrorNotification("More than one Contract Selected",
                        "There are More than one Contract selected.\n Please select only one Contract and try again");
            } else {
                AbstractNotificationUtils.getErrorNotification("No Contract Selected.", "Please select a Contract. ");
            }
        }
    }

    /**
     * Adds the layout and pagination control to the Layout.
     */
    private void addResultTable() {
        tableLayout.addComponent(resultsTable);
        HorizontalLayout controls = mSalesProjectionTableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        tableLayout.addComponent(controlLayout);
    }

    /**
     *
     */
    private void generateLogic() {
        projectionDTO.setHierarchyIndicator(Constant.CUSTOMER_SMALL.equals(view.getValue()) ? Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY : Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        loadLevelAndFilterValue();
        mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
    }

    /**
     * Configures the result table.
     */
    private void configureResultTable() {
        configureProjectionDTO();
        mSalesProjectionTableLogic.setTreeNodeMultiClick(false);
        mSalesProjectionTableLogic.setPageLength(20);
        mSalesProjectionTableLogic.sinkItemPerPageWithPageLength(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        mSalesProjectionTableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        excelHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getSalesLeftTableColumns(projectionDTO);
        rightHeader = HeaderUtils.getSalesProjectionRightTableColumns(projectionDTO, fullHeader, excelHeader);
        customContainer = new CustomTreeContainer<>(SalesRowDto.class);
        customContainer.setColumnProperties(leftHeader.getProperties());
        customContainer.setColumnProperties(rightHeader.getProperties());

        mSalesProjectionTableLogic.setContainerDataSource(customContainer);
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();

        leftTable.setEditable(true);
        rightTable.setEditable(true);
        resultsTable.getLeftFreezeAsTable().setFilterBarVisible(true);
        leftTable.setVisibleColumns(leftHeader.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeader.getSingleHeaders().toArray(new String[leftHeader.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisible(true);
        leftTable.setDoubleHeaderVisibleColumns(leftHeader.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeader.getDoubleHeaders().toArray(new String[leftHeader.getDoubleHeaders().size()]));

        rightTable.setVisibleColumns(rightHeader.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeader.getSingleHeaders().toArray(new String[rightHeader.getSingleHeaders().size()]));
        rightTable.setDoubleHeaderVisible(true);
        rightTable.setDoubleHeaderVisibleColumns(rightHeader.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeader.getDoubleHeaders().toArray(new String[rightHeader.getDoubleHeaders().size()]));

        rightTable.setDoubleHeaderMap(rightHeader.getDoubleHeaderMaps());
        leftTable.setDoubleHeaderMap(leftHeader.getDoubleHeaderMaps());
        for (Object obj : leftHeader.getSingleColumns()) {
            if (String.valueOf(obj).contains(Constant.LEVELNAME)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, 135);
            } else if (String.valueOf(obj).contains(Constant.BASELINE)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, 135);
            } else if (String.valueOf(obj).contains(Constant.METHODOLOGY)) {
                resultsTable.getLeftFreezeAsTable().setColumnWidth(obj, 135);
            }
        }

        loadPeriods();
        resultsTable.setHeight("650px");
        leftTable.setHeight("650px");
        rightTable.setHeight("650px");
        leftTable.setSortEnabled(false);
        rightTable.setSortEnabled(false);
        configureTableFieldFactory();
        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        levelFilter.addValueChangeListener(levelFilterChangeOption);
    }

    /**
     * Configures the Projection Selection DTO on entering Screen. Sets the
     * values in the Session DTO to the ProjectionSelectionDTO.
     */
    private void configureProjectionDTO() {
        boolean toHist = false;
        int historyNum = 0;
        projectionDTO.setScreenName(screenName);
        projectionDTO.setCustRelationshipBuilderSid(projectionDTO.getSessionDTO().getCustRelationshipBuilderSid());
        projectionDTO.setProdRelationshipBuilderSid(projectionDTO.getSessionDTO().getProdRelationshipBuilderSid());
        projectionDTO.setCustomerLevelNo(StringUtils.isBlank(projectionDTO.getSessionDTO().getCustomerLevelNumber()) || Constant.NULL.equals(projectionDTO.getSessionDTO().getCustomerLevelNumber())
                ? 1 : Integer.valueOf(projectionDTO.getSessionDTO().getCustomerLevelNumber()));
        projectionDTO.setProductLevelNo(StringUtils.isBlank(projectionDTO.getSessionDTO().getProductLevelNumber()) || Constant.NULL.equals(projectionDTO.getSessionDTO().getProductLevelNumber())
                ? 1 : Integer.valueOf(projectionDTO.getSessionDTO().getProductLevelNumber()));
        projectionDTO.setProjectionId(projectionDTO.getSessionDTO().getProjectionId());
        projectionDTO.setUserId(Integer.valueOf(projectionDTO.getSessionDTO().getUserId()));
        projectionDTO.setSessionId(Integer.valueOf(projectionDTO.getSessionDTO().getSessionId()));
        projectionDTO.setFrequency(String.valueOf(nmFrequencyDdlb.getValue()));
        projectionDTO.setProjectionOrder(String.valueOf(proPeriodOrd.getValue()));
        projectionDTO.setActualsOrProjections(String.valueOf(actualsProjections.getValue()));
        String history = String.valueOf(historyDdlb.getValue());
        history = history.trim();
        if (history != null && !StringUtils.isBlank(history) && !NULL.equals(history) && !SELECT_ONE.getConstant().equals(history)) {
            toHist = true;
            projectionDTO.setHistory(history);
            historyNum = Integer.valueOf(projectionDTO.getHistory());
        }
        if (toHist) {
            projectionDTO.setForecastDTO(session.getForecastDTO());
            projectionDTO.setHistoryNum(historyNum);
            projectionDTO.setProjectionNum(CommonUtils.getProjectionNumber(projectionDTO.getFrequency(), session));
        }
        if (variables.getValue() != null) {
            String tempVariables = variables.getValue().toString();
            tempVariables = tempVariables.substring(1, tempVariables.length() - 1);
            final String[] col = tempVariables.split(",");
            projectionDTO.setVariableList(Arrays.asList(col));
        }

    }

    protected void channelsViewChange() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    protected void customDdlbChangeOption() {
        LOGGER.info("customDdlbChangeOption ValueChangeEvent initiated ");
        customId = CommonLogic.customDdlbOptionChange(viewDdlb, editBtn, level);
        currentHierarchy = CommonLogic.getCustomTree(customId);
        CommonUtils.loadLevelDdlb(level, true, currentHierarchy);
        LOGGER.info(" customId  " + customId);
        LOGGER.info(" currentHierarchy " + currentHierarchy.size());
        projectionDTO.setCustomId(customId);
        generateLogic();
        if (viewDdlb.getValue() != null
                && !Constant.NULL.equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))
                && !SELECT_ONE.getConstant().equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))
                && !DASH.equalsIgnoreCase(String.valueOf(viewDdlb.getValue()))) {
            editBtn.setEnabled(true);
        } else {
            editBtn.setEnabled(false);
        }
        LOGGER.info("customDdlbChangeOption ValueChangeEvent ends ");
    }

    public void updateOnPMPYImport(String hierarchyNo, String updateValue, String updatePeriod, boolean salesOrUnits, SalesRowDto salesRow) {

        try {
            String salesOrUnitsProperty;

            if (StringUtils.isNotBlank(hierarchyNo) && StringUtils.isNotEmpty(hierarchyNo)) {
                updatePeriod = updatePeriod.replace(Constant.Q, Constant.Q_SMALL);
                updatePeriod = updatePeriod.replace(" ", "-");
                if (salesOrUnits) {
                    updatePeriod = updatePeriod + "-ProjectedSales";
                    salesOrUnitsProperty = Constant.SALES_SMALL;
                } else {
                    updatePeriod = updatePeriod + "-ProjectedUnits";
                    salesOrUnitsProperty = Constant.UNITS_SMALL;
                }

                salesLogic.saveEditedRecs(updatePeriod, updateValue, Double.NaN, salesOrUnitsProperty, salesRow, projectionDTO, false, true);
                refreshTableData(getCheckedRecordsHierarchyNo());

            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void saveSalesProjection() throws PortalException, Exception {
        String userId = String.valueOf(projectionDTO.getUserId());
        String sessionId = String.valueOf(projectionDTO.getSessionId());
        salesLogic.saveMandatedSalesProjection(userId, sessionId);
    }

    private void loadResultTable(int levelNo, String hierarchyNo) {
        projectionDTO.setFilterLevelNo(levelNo);
        projectionDTO.setFilterHierarchyNo(hierarchyNo);
        projectionDTO.setProductHierarchyNo(StringUtils.EMPTY);
        projectionDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
        mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
    }

    public boolean getSubmitFlag() {
        if (customContainer.getItemIds().size() > 0) {
            return true;
        } else {
            return false;
        }
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
        final Map<String, AppPermission> functionPsHM = stplSecurity.getBusinessFunctionPermission(userId, getGovernmentConstant() + "," + UISecurityUtil.SALES_PROJECTION);
        if (functionPsHM.get(FunctionNameUtil.GENERATEBTN) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.GENERATEBTN)).isFunctionFlag()) {
            generate.setVisible(Boolean.FALSE);
            expand.setVisible(Boolean.FALSE);
            collapse.setVisible(Boolean.FALSE);
            newBtn.setVisible(Boolean.FALSE);
            editBtn.setVisible(Boolean.FALSE);
            pmpy.setVisible(Boolean.FALSE);

        }
        if (functionPsHM.get(FunctionNameUtil.CALCULATE) != null && !((AppPermission) functionPsHM.get(FunctionNameUtil.CALCULATE)).isFunctionFlag()) {
            calculate.setVisible(Boolean.FALSE);
            populate.setVisible(Boolean.FALSE);
            adjust.setVisible(Boolean.FALSE);
        }
    }
}
