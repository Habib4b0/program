/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.accrualrateprojection.ui.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.accrualrateprojection.dto.AccrualRateProjectionDTO;
import com.stpl.app.gtnforecasting.accrualrateprojection.dto.AccrualRateSelectionDTO;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.AccrualRateProjectionLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.tablelogic.AccrualRateProjectionTableLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.utils.AccrualRateUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * Class used to build and configure the Rates Tab.
 *
 * @author sibi
 */
public class Rates extends CustomComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(Rates.class);

    @UiField("frequencyDdlb")
    private ComboBox frequencyDdlb;

    @UiField("historyDdlb")
    private ComboBox historyDdlb;

    @UiField("rateBasisDdlb")
    private ComboBox rateBasisDdlb;

    @UiField("variables")
    private OptionGroup variables;

    @UiField("fromDdlb")
    private ComboBox fromDdlb;

    @UiField("toDdlb")
    private ComboBox toDdlb;

    @UiField("tableVerticalLayout")
    private VerticalLayout tableVerticalLayout;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("generateBtn")
    private Button generateBtn;

    @UiField("excelBtn")
    private Button excelBtn;
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");
    private AccrualRateProjectionTableLogic tableLogic = new AccrualRateProjectionTableLogic();

    private FreezePagedTable table = new FreezePagedTable(tableLogic);

    private CustomTableHeaderDTO tableHeaderDTO;

    private ExtContainer<AccrualRateProjectionDTO> resultBeanContainer;

    public static ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");

    private final AccrualRateProjectionLogic accrualRateProjectionLogic = AccrualRateProjectionLogic.getInstance();

    private final AccrualRateSelectionDTO accrualRateSelectionDTO = new AccrualRateSelectionDTO();

    protected SessionDTO session;

    protected DSLogic dsLogic = new DSLogic();

    private boolean isFilterLoadNeeded = true;

    private final Map map;

    /**
     *
     * @param sessionDTO
     * @param map
     */
    public Rates(final SessionDTO sessionDTO, final Map map) {
        this.map = map;
        accrualRateProjectionLogic.setValuesInDTO(sessionDTO, accrualRateSelectionDTO);
        this.session = sessionDTO;
        init();
    }

    /**
     * Loads the Rates Tab and configures all the fields in the tab.
     */
    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/accrualrateprojection/rates.xml"), this));
        configurePermission();
        configureFields();
        initializeResultTable();
        if (AccrualRateUtils.ADD_CASE.equalsIgnoreCase(session.getAction())) {
            configureTable();
        }
    }

    public void configureOnLoad() {
        if (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) || AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction())) {
            configureOnEditOrView();
            configureTable();
            loadGridOnEdit();
        }
    }

    /**
     * Method configures the fields and loads the values.
     */
    private void configureFields() {
        accrualRateSelectionDTO.setSessionDto(session);
        frequencyDdlb.setImmediate(true);
        frequencyDdlb.addItem(Constant.MONTHLY);
        frequencyDdlb.setNullSelectionAllowed(false);
        frequencyDdlb.select(Constant.MONTHLY);
        frequencyDdlb.setReadOnly(true);

        historyDdlb.setImmediate(true);
        historyDdlb.addItems(AccrualRateUtils.getInstance().historyPeriods12);
        historyDdlb.setNullSelectionAllowed(true);
        historyDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        historyDdlb.select(null);

        rateBasisDdlb.setImmediate(true);
        rateBasisDdlb.addItem(Constant.SELECT_ONE);
        rateBasisDdlb.addItem(AccrualRateUtils.ADJUSTED_DEMAND);
        rateBasisDdlb.addItem(AccrualRateUtils.DEMAND);
        rateBasisDdlb.addItem(AccrualRateUtils.EX_FACTORY);
        rateBasisDdlb.addItem(AccrualRateUtils.INVENTORY_WITHDRAWALS);
        rateBasisDdlb.setNullSelectionAllowed(true);
        rateBasisDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        rateBasisDdlb.select(null);

        variables.setImmediate(true);
        variables.addItem(AccrualRateUtils.RATE);
        variables.addItem(AccrualRateUtils.AMOUNT);
        variables.select(AccrualRateUtils.RATE);
        variables.setMultiSelect(false);

        historyDdlb.focus();

        excelBtn.setIcon(excelExportImage);

    }

    /**
     * Adds the table and pagination controls to the layout.
     */
    private void addResultTable() {
        tableVerticalLayout.addComponent(table);
        tableVerticalLayout.addComponent(tableLogic.createControls());
    }

    /**
     * Sets the Initial Configuration for the table.
     */
    protected void initializeResultTable() {
        table.markAsDirty();
        table.setDoubleHeaderVisible(true);
        table.setSelectable(false);
        
        table.setSplitPosition(AccrualRateUtils.SPLIT_POSITION, Sizeable.Unit.PIXELS);
        table.setMinSplitPosition(AccrualRateUtils.MIN_SPLIT_POSITION, Sizeable.Unit.PIXELS);
        table.setMaxSplitPosition(AccrualRateUtils.MAX_SPLIT_POSITION, Sizeable.Unit.PIXELS);
        table.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        addResultTable();
    }

    /**
     * Method used to configure the table and load headers.
     */
    private void configureTable() {
        ExtPagedTable leftTable = table.getLeftFreezeAsTable();
        ExtPagedTable rightTable = table.getRightFreezeAsTable();
        tableHeaderDTO = new CustomTableHeaderDTO();
        CustomTableHeaderDTO recordHeaderDTO = new CustomTableHeaderDTO();
        AccrualRateUtils.getRightHeadersForAccrual(tableHeaderDTO, historyDdlb.getValue(), accrualRateSelectionDTO, AccrualRateUtils.RATES, null, recordHeaderDTO);
        rightTable.setSortEnabled(false);
        leftTable.setSortEnabled(false);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableLogic.setPageLength(NumericConstants.TEN);
        resultBeanContainer = new ExtContainer<>(AccrualRateProjectionDTO.class, ExtContainer.DataStructureMode.LIST);
        tableLogic.setContainerDataSource(resultBeanContainer);
        resultBeanContainer.setRecordHeader(tableHeaderDTO.getSingleColumns());
        resultBeanContainer.setColumnProperties(tableHeaderDTO.getProperties());
        leftTable.setVisibleColumns(AccrualRateUtils.PRODUCT);
        leftTable.setColumnHeader(AccrualRateUtils.PRODUCT, StringUtils.capitalize(AccrualRateUtils.PRODUCT));
        rightTable.setVisibleColumns(tableHeaderDTO.getSingleColumns().toArray());
        rightTable.setColumnHeaders(tableHeaderDTO.getSingleHeaders().toArray(new String[tableHeaderDTO.getSingleHeaders().size()]));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }
        loadFromAndToPeriods();
    }

    /**
     * Button Click Listener invokes on clicking the generating the reset
     * button. Resets the selection to the default values.
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) {

        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                return;
            }

            @Override
            public void yesMethod() {
                historyDdlb.select(null);
                boolean isViewMode = AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction());
                if (isViewMode) {
                    rateBasisDdlb.setReadOnly(false);
                }
                rateBasisDdlb.select(null);
                variables.select(AccrualRateUtils.RATE);
                fromDdlb.select(null);
                toDdlb.select(null);
                if (isViewMode) {
                    rateBasisDdlb.setReadOnly(true);
                }
            }
        }.getOkCancelMessage(AccrualRateUtils.RESET_CONFIRMATION, alertMsg.getString("ACR_MSG_ID_01"));
    }

    /**
     * Button Click Listener invokes on clicking the generating the generate
     * button. Validates the selection made in rates tab and populates the list
     * view.
     *
     * @param event
     */
    @UiHandler("generateBtn")
    public void generateBtnLogic(Button.ClickEvent event) {
        try {
            boolean isNotValidFilter = accrualRateProjectionLogic.checkInvalidFromToPeriods(fromDdlb.getValue(), toDdlb.getValue(), accrualRateSelectionDTO);
            if (isNotValidFilter) {
                AbstractNotificationUtils.getErrorNotification("Time Period conflict", alertMsg.getString("ACR_MSG_ID_03"));
            } else if (rateBasisDdlb.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification("Not all Required Fields are Populated", alertMsg.getString("ACR_MSG_ID_02"));
            } else {
                if (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) && !session.isFileNotChanged()
                        && !session.isNewFileCalculationNeeded() && !map.containsKey(Constant.IS_SALES_GENERATED)) {
                    AbstractNotificationUtils.getInfoNotification("Error", alertMsg.getString("ACR_MSG_ID_08"));
                    return;
                } else if ((AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) || AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction())) && (!session.isFileNotChanged() && !session.isNewFileCalculationNeeded())) {
                    AbstractNotificationUtils.getInfoNotification("Confirmation", alertMsg.getString("ACR_MSG_ID_07"));
                }
                accrualRateSelectionDTO.setIsFilterValid(StringUtils.isNotBlank((String) fromDdlb.getValue()));
                accrualRateSelectionDTO.setRateOrAmount(AccrualRateUtils.RATE.equals(variables.getValue()));
                accrualRateSelectionDTO.setRateBasis(rateBasisDdlb.getValue().toString());
                tableVerticalLayout.removeAllComponents();
                tableLogic = new AccrualRateProjectionTableLogic();
                table = new FreezePagedTable(tableLogic);
                tableLogic.setRequiredCount(false);
                configureTable();
                initializeResultTable();
                if (!AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction())) {
                    callRatesInsertProcedure();
                }
                generateLogic();
                loadFromAndToPeriods();
                map.put(Constant.RATE_BASIS, rateBasisDdlb.getValue());
                map.put(Constant.IS_RATES_GENERATED, true);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Method used to export the data in the list view to excel..
     *
     * @param event
     */
    @UiHandler("excelBtn")
    public void excelBtnLogic(Button.ClickEvent event) {
        try {
            final ExtCustomTable excelTable = new ExtCustomTable();
            final ExtContainer<AccrualRateProjectionDTO> excelContainer = new ExtContainer<>(AccrualRateProjectionDTO.class, ExtContainer.DataStructureMode.LIST);
            configureAndLoadDataForExcel(excelTable, excelContainer);
            if (excelTable.size() > 0) {
                ForecastUI.EXCEL_CLOSE = true;
                ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(excelTable), AccrualRateUtils.RATES, AccrualRateUtils.RATES, "Rates.xls", false);
                exp.export();
            }
            tableVerticalLayout.removeComponent(excelTable);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Method used to load the data for excel export.
     *
     * @param excelTable
     * @param excelContainer
     */
    public void configureAndLoadDataForExcel(final ExtCustomTable excelTable, final ExtContainer<AccrualRateProjectionDTO> excelContainer) {

        excelTable.setImmediate(true);
        excelTable.setVisible(false);
        tableVerticalLayout.addComponent(excelTable);

        List visibleColumns = new ArrayList(tableHeaderDTO.getSingleColumns());
        visibleColumns.add(0, AccrualRateUtils.PRODUCT);
        List<String> columnHeaders = new ArrayList(tableHeaderDTO.getSingleHeaders());
        columnHeaders.add(0, StringUtils.capitalize(AccrualRateUtils.PRODUCT));

        excelContainer.setColumnProperties(tableHeaderDTO.getProperties());
        excelContainer.setRecordHeader(tableHeaderDTO.getSingleColumns());
        excelTable.setContainerDataSource(excelContainer);
        excelTable.setVisibleColumns(visibleColumns.toArray());
        excelTable.setColumnHeaders(Arrays.copyOf(columnHeaders.toArray(), columnHeaders.size(), String[].class));
        final boolean isViewMode = AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction());
        excelContainer.addAll(accrualRateProjectionLogic.getDataForRates(accrualRateSelectionDTO, 0, 0, true, isViewMode));

    }

    /**
     * Loads the From and To Period DDLB based on the selection in rates tab.
     */
    private void loadFromAndToPeriods() {
        if (isFilterLoadNeeded) {
            fromDdlb.removeAllItems();
            fromDdlb.setImmediate(true);
            fromDdlb.addItem(Constant.SELECT_ONE);
            fromDdlb.setNullSelectionAllowed(true);
            fromDdlb.setNullSelectionItemId(Constant.SELECT_ONE);

            toDdlb.removeAllItems();
            toDdlb.setImmediate(true);
            toDdlb.addItem(Constant.SELECT_ONE);
            toDdlb.setNullSelectionAllowed(true);
            toDdlb.setNullSelectionItemId(Constant.SELECT_ONE);

            for (int i = 0; i < tableHeaderDTO.getSingleColumns().size(); i++) {
                Object object = tableHeaderDTO.getSingleColumns().get(i);
                if (accrualRateSelectionDTO.getFilterList().contains(object)) {
                    fromDdlb.addItem(object);
                    fromDdlb.setItemCaption(object, tableHeaderDTO.getSingleHeaders().get(i));

                    toDdlb.addItem(object);
                    toDdlb.setItemCaption(object, tableHeaderDTO.getSingleHeaders().get(i));
                }
            }
            isFilterLoadNeeded = false;
        }

    }

    /**
     * Method used to trigger the table logic to retrieve the data from data
     * base.
     */
    private void generateLogic() {
        LOGGER.debug("Enters Generate Logic");
        final boolean isViewMode = AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction());
        tableLogic.setRequiredData(AccrualRateUtils.RATES, accrualRateSelectionDTO, null, isViewMode);
        LOGGER.debug("Exits Generate Logic");
    }

    /**
     * Method to load the saved values on edit mode.
     */
    private void configureOnEditOrView() {

        Map<Object, Object> editviewMap = DSLogic.getProjectionSelection(session.getProjectionId(), "Rate");
        if (editviewMap != null && !editviewMap.isEmpty()) {
            Object value;
            value = editviewMap.get(Constant.HISTORY_CAPS);
            if (value != null) {
                historyDdlb.setValue(String.valueOf(value));
                historyDdlb.setImmediate(true);
            }
            value = editviewMap.get(Constant.RATE_BASIS);
            if (value != null) {
                rateBasisDdlb.setValue(String.valueOf(value));
            }
            this.map.put(Constant.RATE_BASIS, rateBasisDdlb.getValue());
            value = editviewMap.get(Constant.VARIABLES);
            if (value != null) {
                variables.setValue(String.valueOf(value));
            }
            value = editviewMap.get("FromDDLB");
            if (value != null) {
                fromDdlb.setValue(String.valueOf(value));
            }
            value = editviewMap.get("ToDDLB");
            if (value != null) {
                toDdlb.setValue(String.valueOf(value));
            }
        }

        accrualRateSelectionDTO.setRateOrAmount(AccrualRateUtils.RATE.equals(variables.getValue()));
        rateBasisDdlb.setReadOnly(AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction()));
    }

    public void saveTabSelection() {
        Map selectionMap = new HashMap();
        selectionMap.put(Constant.HISTORY_CAPS, String.valueOf(historyDdlb.getValue()));
        selectionMap.put(Constant.RATE_BASIS, String.valueOf(rateBasisDdlb.getValue()));
        selectionMap.put(Constant.VARIABLES, String.valueOf(variables.getValue()));
        selectionMap.put("FromDDLB", String.valueOf(fromDdlb.getValue()));
        selectionMap.put("ToDDLB", String.valueOf(toDdlb.getValue()));

        String actionValue = (Constant.EDIT_CAPS.equalsIgnoreCase(session.getAction()) || Constant.VIEW_CAPS.equalsIgnoreCase(session.getAction())) ? Constant.UPDATE_SMALL : "Save";

        dsLogic.saveScreenSelection(session.getProjectionId(), selectionMap, "Rate", actionValue);
    }

    private void callRatesInsertProcedure() {
        final Object[] parameters = {accrualRateSelectionDTO.getProjectionId(), accrualRateSelectionDTO.getUserId(), accrualRateSelectionDTO.getSessionId(), rateBasisDdlb.getValue(), map.get("Period Basis")};
        accrualRateProjectionLogic.callARPProcedure(parameters, "PRC_ACCRUAL_RATE");
    }

    private void loadGridOnEdit() {
        if (session.isFileNotChanged()) {
            generateLogic();
        } else if (session.isNewFileCalculationNeeded()) {
            callRatesInsertProcedure();
            generateLogic();
        } else {
            generateLogic();
        }
    }

    public void configurePermission() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(session.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Accrual Rate Projection" + "," + "Rates");
            if (functionHM.get("generateBtn") != null && !((AppPermission) functionHM.get("generateBtn")).isFunctionFlag()) {
                generateBtn.setVisible(false);
            } else {
                generateBtn.setVisible(true);
            }
            if (functionHM.get("resetBtn") != null && !((AppPermission) functionHM.get("resetBtn")).isFunctionFlag()) {
                resetBtn.setVisible(false);
            } else {
                resetBtn.setVisible(true);
            }
            if (functionHM.get("excelBtn") != null && !((AppPermission) functionHM.get("excelBtn")).isFunctionFlag()) {
                excelBtn.setVisible(false);
            } else {
                excelBtn.setVisible(true);
            }

        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

}
