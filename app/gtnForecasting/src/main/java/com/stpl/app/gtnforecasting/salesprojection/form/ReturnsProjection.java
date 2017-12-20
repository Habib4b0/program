/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.abstractforecast.ForecastSalesProjection;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.logic.Utility;
import com.stpl.app.gtnforecasting.salesprojection.logic.tablelogic.MSalesProjectionTableLogic;
import com.stpl.app.gtnforecasting.salesprojection.utils.HeaderUtils;
import com.stpl.app.gtnforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.CommonConstants.SELECT_ONE;
import static com.stpl.app.gtnforecasting.utils.Constant.DASH;
import static com.stpl.app.gtnforecasting.utils.Constant.NULL;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT_HIERARCHY;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.extfilteringtable.FreezePagedTreeTable;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.HorizontalLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.jboss.logging.Logger;

/**
 *
 * @author Nadhiya
 */
public class ReturnsProjection extends ForecastSalesProjection {

    boolean generated = false;
    boolean firstGenerated = false;
    private static final Logger LOGGER = Logger.getLogger(ReturnsProjection.class);
    private final SPRCommonLogic sprCommonLogic = new SPRCommonLogic();

    public ReturnsProjection(SessionDTO session, String screenName) throws Exception  {
        super(session, screenName);
        init();
    }

    public void init()   {
        projectionDTO.setSessionDTO(session);
        projectionDTO.setRowsPerLevelItem(salesLogic.getHistoryAndProjectionCount(session, projectionDTO));
        populateLevel.setVisible(true);
        populateLevel.setEnabled(true);
        populateLabel.setEnabled(true);
        populateLabel.setVisible(true);
        configureProjectionDTO();
        super.loadLevelFilterValue(PRODUCT_HIERARCHY.getConstant());
        Utility.loadHierarchyList(session);
        generateBtnLogic(null);
        super.configureGraph();       
        configureFields();

    }

    /**
     * Configures the Projection Selection DTO on entering Screen. Sets the
     * values in the Session DTO to the ProjectionSelectionDTO.
     */
    private void configureProjectionDTO() {
        boolean toHist = false;
        int historyNum = 0;
        projectionDTO.setScreenName(screenName);
        projectionDTO.setCustRelationshipBuilderSid(DASH);
        projectionDTO.setProdRelationshipBuilderSid(projectionDTO.getSessionDTO().getProdRelationshipBuilderSid());
        projectionDTO.setCustomerLevelNo(0);
        projectionDTO.setProductLevelNo(StringUtils.isBlank(projectionDTO.getSessionDTO().getProductLevelNumber()) || Constant.NULL.equals(projectionDTO.getSessionDTO().getProductLevelNumber())
                ? 1 : Integer.valueOf(projectionDTO.getSessionDTO().getProductLevelNumber()));
        projectionDTO.setProjectionId(projectionDTO.getSessionDTO().getProjectionId());
        projectionDTO.setUserId(Integer.valueOf(projectionDTO.getSessionDTO().getUserId()));
        projectionDTO.setSessionId(Integer.valueOf(projectionDTO.getSessionDTO().getSessionId()));
        projectionDTO.setFrequency(String.valueOf(nmFrequencyDdlb.getValue()));
        projectionDTO.setProjectionOrder(String.valueOf(proPeriodOrd.getValue()));
        projectionDTO.setActualsOrProjections(String.valueOf(actualsProjections.getValue()));
        projectionDTO.setSessionDTO(session);
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

    @Override
    protected void levelFilterDdlbChangeOption() {
        LOGGER.debug("levelFilterDdlbChangeOption started ");
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
        LOGGER.debug("levelFilterDdlbChangeOption ends ");
    }

    @Override
    protected void excelExportLogic() {
        LOGGER.debug("excelExportLogic started ");
        try {
            projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
            projectionDTO.setLevelNo(Integer.valueOf(session.getProductLevelNumber()));
            configureExcelResultTable();
            levelFilterDdlbChangeOption(true);
            excelTable.setRefresh(Boolean.TRUE);
            if (excelTable.size() > 0) {
                ForecastUI.EXCEL_CLOSE=true;
                ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(excelTable), "Returns Projection", "Returns Projection", "Returns_Projection.xls", false);
                exp.export();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("excelExportLogic ends ");
    }

    @Override
    protected void enableDisableFields() {
        return;
    }

    @Override
    protected void populateBtnLogic() {
        populateButtonLogic();
    }

    @Override
    protected void adjustmentLogic() {
        return;
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

            LOGGER.debug("generate button click listener starts ");
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
            checkAllCheckBox();

        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("generate button click listener ends ");
    }

    /**
     *
     */
    private void generateLogic() {

        projectionDTO.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);
        loadLevelAndFilterValue();
        mSalesProjectionTableLogic.setProjectionResultsData(projectionDTO);
    }

    @Override
    protected void massUpdateLogic() {
        return;
    }
    
    @Override
    protected void variableChangeLogic() {
        return;

    }

    @Override
    protected void viewChangeOption() {
        return;
    }

    @Override
    protected void customDdlbChangeOption() {
        return;
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
        if (fieldDdlb.getValue() == null || Constant.GROWTH_RATE.equals(fieldDdlb.getValue())) {
            populateLabel.setVisible(false);
            populateLevel.setVisible(false);
        } else {
            populateLabel.setVisible(true);
            populateLevel.setVisible(true);
        }
        valueTxt.setValue(StringUtils.EMPTY);
        startPeriod.setValue(Constant.SELECT_ONE);
        endPeriod.setValue(Constant.SELECT_ONE);
    }

    @Override
    protected void resetBtnLogic() {
        if (nmFrequencyDdlb.getValue().equals(MONTHLY.getConstant())
                || (nmFrequencyDdlb.getValue().equals(QUARTERLY.getConstant()))
                || (nmFrequencyDdlb.getValue().equals(ANNUAL.getConstant()))
                || (nmFrequencyDdlb.getValue().equals(SEMI_ANNUAL.getConstant()))) {
            nmFrequencyDdlb.setValue(QUARTERLY.getConstant());
            historyDdlb.setValue(NumericConstants.FOUR);
        }
        actualsProjections.select(Constant.ACTUALS);
        proPeriodOrd.select(Constant.ASCENDING);
    }

    @Override
    protected void pmpyLogic() {
        return;
    }

    private void addResultTable() {
        tableLayout.addComponent(resultsTable);
        resultsTable.setSplitPosition(NumericConstants.SIX_HUNDRED);
        HorizontalLayout controls = mSalesProjectionTableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        tableLayout.addComponent(controlLayout);
    }

    private void configureResultTable() {
        configureProjectionDTO();
        mSalesProjectionTableLogic.setTreeNodeMultiClick(false);
        List<Integer> pagelength = CommonLogic.getPageNumber();
        mSalesProjectionTableLogic.getControlConfig().setPageLengthsAndCaptions(pagelength);
        excelHeader = new CustomTableHeaderDTO();
        leftHeader = HeaderUtils.getProjectionReturnsLeftTableColumns(excelHeader);
        rightHeader = HeaderUtils.getSalesProjectionRightTableColumns(projectionDTO, fullHeader, excelHeader);

        customContainer = new ExtTreeContainer<>(SalesRowDto.class,ExtContainer.DataStructureMode.MAP);
        customContainer.setColumnProperties(leftHeader.getProperties());
        customContainer.setColumnProperties(rightHeader.getProperties());

        mSalesProjectionTableLogic.setContainerDataSource(customContainer);
        mSalesProjectionTableLogic.setPageLength(NumericConstants.TWENTY);
        mSalesProjectionTableLogic.sinkItemPerPageWithPageLength(true);
        leftTable = resultsTable.getLeftFreezeAsTable();
        rightTable = resultsTable.getRightFreezeAsTable();

        leftTable.setEditable(true);
        rightTable.setEditable(true);

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
        loadPeriods();
        resultsTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setHeight(Constant.SIX_FIFTY_PX);
        rightTable.setHeight(Constant.SIX_FIFTY_PX);
        leftTable.setSortEnabled(false);
        rightTable.setSortEnabled(false);
        List headerList = rightHeader.getSingleColumns();
        List leftHeaderList = leftHeader.getSingleColumns();
        if (headerList != null) {
            for (int i = 0; i < headerList.size(); i++) {
                if ((String.valueOf(headerList.get(i))).endsWith("Amount")) {
                    rightTable.setColumnWidth(headerList.get(i), 151);
                } else if (String.valueOf(headerList.get(i)).endsWith(Constant.PERCENT)) {
                    rightTable.setColumnWidth(headerList.get(i), 113);
                }
            }
        }
        if (leftHeaderList != null) {
            for (int i = 0; i < leftHeaderList.size(); i++) {
                if ((String.valueOf(leftHeaderList.get(i))).startsWith("Closed")) {
                    leftTable.setColumnWidth(leftHeaderList.get(i), NumericConstants.ONE_THREE_FIVE);
                }
            }
        }
        configureTableFieldFactory();
        levelFilter.removeValueChangeListener(levelFilterChangeOption);
        levelFilter.addValueChangeListener(levelFilterChangeOption);
    }

    public void saveReturnsSave() {
        try {
            salesLogic.saveReturnsSalesProjection(projectionDTO.getSessionDTO());
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    public void saveSPSave() {
        LOGGER.debug("saveSPSave method starts");
        try {
            Map map = new HashMap();
            map.put(Constant.FREQUENCY_SMALL, nmFrequencyDdlb.getValue().toString());
            map.put(Constant.HISTORY_CAPS, historyDdlb.getValue().toString());
            map.put("Actuals/Projections", actualsProjections.getValue().toString());
            map.put(Constant.PERIOD_ORDER, proPeriodOrd.getValue().toString());
            sprCommonLogic.saveReturnsSPSelection(map, session.getProjectionId(), Constant.SALES_PROJECTION);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("saveSPSave method ends");
    }

    private void configureFields() {
        returnsResetBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                        // do nothing
                    }

                    @Override
                    /**
                     * The method is triggered when Yes button of the message
                     * box is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    public void yesMethod() {
                        LOGGER.debug("Entering reset method");
                        resetListViewLogic();
                        LOGGER.debug("End of reset method");
                    }

                }.getConfirmationMessage("Reset Confirm", "Are you sure you want to reset the page to default/previous values?");

            }
        });
        altHistoryBtn.setVisible(false);
    }

    private void resetListViewLogic() {
        NonMandatedLogic logic = new NonMandatedLogic();
        if (Constant.EDIT_SMALL.equalsIgnoreCase(session.getAction())) {
            logic.deleteTempData(session, "RETURNS_DELETE_TEMP");
            logic.tempTableInsertReturns(session, "RETURNS_EDIT_TEMP_INSERT_QUERY");
            refreshTableData(getReturnsCheckedRecordsHierarchyNo());
        }else if(Constant.VIEW.equalsIgnoreCase(session.getAction())){
            refreshTableData(getReturnsCheckedRecordsHierarchyNo());
        }else {
            DataSelectionLogic dsLogic = new DataSelectionLogic();
            logic.deleteTempData(session, "RETURNS_DELETE_TEMP");
            dsLogic.callInsertProcedure(session.getProjectionId(), session.getUserId(), session.getSessionId(), SalesUtils.RETURNS_SALES_INSERT_PRO_NAME);
            refreshTableData(getReturnsCheckedRecordsHierarchyNo());
        }

    }
    private void checkAllCheckBox() {
        getCheckedRecordsHierarchyNo();
        if (uncheckRecordCount == 0) {
            leftTable.setColumnCheckBox(Constant.CHECK, true, true);
        }
    }
}
