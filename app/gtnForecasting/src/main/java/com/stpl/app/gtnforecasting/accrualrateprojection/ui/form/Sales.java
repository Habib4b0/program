package com.stpl.app.gtnforecasting.accrualrateprojection.ui.form;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.gtnforecasting.accrualrateprojection.dto.AccrualRateProjectionDTO;
import com.stpl.app.gtnforecasting.accrualrateprojection.dto.AccrualRateSelectionDTO;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.AccrualRateProjectionLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.tablelogic.AccrualRateProjectionTableLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.utils.AccrualRateUtils;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastUI;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.ExtCustomTable;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.v7.ui.themes.Reindeer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.CustomMenuBar.CustomMenuItem;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Nadhiya
 */
public class Sales extends CustomComponent {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger
            .getLogger(Sales.class);

    @UiField("exclusionDetails")
    protected Button exclusionDetails;

    @UiField("horizontalLayout")
    HorizontalLayout horizontalLayout;

    @UiField("priceBasisDdlb")
    public ComboBox priceBasisDdlb;

    @UiField("historyDdlb")
    public ComboBox historyDdlb;

    @UiField("frequencyDdlb")
    public ComboBox frequencyDdlb;

    @UiField("tableVerticalLayout")
    VerticalLayout tableLayout;

    @UiField("generateBtn")
    protected Button generateBtn;

    @UiField("resetBtn")
    protected Button resetBtn;

    @UiField("fromDdlb")
    private ComboBox fromDdlb;

    @UiField("toDdlb")
    private ComboBox toDdlb;

    @UiField("excelBtn")
    private Button excelBtn;
    /**
     * The excel export image.
     */
    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");

    AccrualRateProjectionTableLogic tableLogic = new AccrualRateProjectionTableLogic();
    FreezePagedTable resultsTable = new FreezePagedTable(tableLogic);

    ExtContainer<AccrualRateProjectionDTO> resultsContainer = new ExtContainer(AccrualRateProjectionDTO.class);

    @UiField("customMenuBar")
    protected CustomMenuBar customMenuBar;

    protected CustomMenuBar.CustomMenuItem customMenuItem;

    List<String> selectedVariables = new ArrayList();

    String[] variableValues = AccrualRateUtils.AccrualSalesVariables.names();

    CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[variableValues.length];

    CommonUtils commonUtils = new CommonUtils();

    private CustomTableHeaderDTO rightHeaderDTO;

    private CustomTableHeaderDTO leftHeaderDTO;

    private ExtContainer<AccrualRateProjectionDTO> resultBeanContainer;

    public static ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");
    DSLogic dsLogic = new DSLogic();
    SessionDTO session;

    AccrualRateProjectionLogic accrualRateProjectionLogic = AccrualRateProjectionLogic.getInstance();

    final AccrualRateSelectionDTO accrualRateSelectionDTO = new AccrualRateSelectionDTO();

    final ExtContainer<AccrualRateProjectionDTO> excelContainer = new ExtContainer<>(AccrualRateProjectionDTO.class);
    private CustomTableHeaderDTO excelHeaderDTO;

    private boolean isFilterLoadNeeded = true;

    private final Map map;

    public Sales(final SessionDTO sessionDTO, final Map map) {
        this.session = sessionDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/accrualrateprojection/sales.xml"), this));
        accrualRateSelectionDTO.setUserId(session.getUserId());
        accrualRateSelectionDTO.setSessionId(session.getSessionId());
        accrualRateSelectionDTO.setProjectionId(String.valueOf(session.getProjectionId()));
        this.map = map;
        accrualRateProjectionLogic.setValuesInDTO(sessionDTO, accrualRateSelectionDTO);
        init();
    }

    private void init() {
        configurePermission();
        configureFields();
        configureTable();
        initializeResultTable();
        if (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) || AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction())) {
            configureOnEditOrView();
        }
    }

    private void configureFields() {

        LOGGER.info("Inside configureFields");
        accrualRateSelectionDTO.setSessionDto(session);
        fromDdlb.setValue(Constant.SELECT_ONE);
        toDdlb.setValue(Constant.SELECT_ONE);

        frequencyDdlb.setImmediate(true);
        frequencyDdlb.addItem(Constant.MONTHLY);
        frequencyDdlb.setValue(Constant.MONTHLY);
        frequencyDdlb.setEnabled(false);

        historyDdlb.setImmediate(true);
        historyDdlb.addItems(AccrualRateUtils.getInstance().historyPeriods12);
        historyDdlb.select(Constant.SELECT_ONE);

        exclusionDetails.addStyleName(Reindeer.BUTTON_LINK);

        priceBasisDdlb.setImmediate(true);
        priceBasisDdlb.addItem(Constant.SELECT_ONE);
        priceBasisDdlb.addItem(AccrualRateUtils.MONTH_1_3_FORWARD_QUARTER);
        priceBasisDdlb.addItem(AccrualRateUtils.MONTH_2_3_FORWARD_QUARTER);
        priceBasisDdlb.addItem(AccrualRateUtils.MONTH_3_FORWARD_QUARTER);
        priceBasisDdlb.select(Constant.SELECT_ONE);
        excelBtn.setIcon(excelExportImage);
        customMenuItem = customMenuBar.addItem(AccrualRateUtils.SELECT_VARIABLES, null);
        for (int i = 0; i < variableValues.length; i++) {
            customItem[i] = customMenuItem.addItem(variableValues[i].trim(), null);
            customItem[i].setCheckable(true);
            customItem[i].setItemClickable(true);

            customItem[i].setItemClickNotClosable(true);
            if (i == 0) {
                customItem[i].setCheckAll(true);
            }
        }
        priceBasisDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                priceBasisDdlb.setDescription(String.valueOf(priceBasisDdlb.getValue()));
            }
        });
        if (Constant.EDIT_CAPS.equalsIgnoreCase(session.getAction()) || Constant.VIEW_CAPS.equalsIgnoreCase(session.getAction())) {
            setProjectionSelection();
        }
        getCheckedValues();
        setDefaultFocus();

        LOGGER.info("configureFields ends");

    }

    @UiHandler("exclusionDetails")
    public void exclusionDetailsLookup(Button.ClickEvent event) {
        LOGGER.info("exclusionDetails starts");
        //To wait the main thread until the exclusion thread get completed
        CommonUtil.getInstance().waitFor(session.getThread(0));

        final ExclusionDetailsLookup exclusionDetailsLookup = new ExclusionDetailsLookup(session);
        UI.getCurrent().addWindow(exclusionDetailsLookup);
        exclusionDetailsLookup.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent e) {
                callSalesInsertProcedure();

            }
        });
        LOGGER.info("exclusionDetails ends");
    }

    protected void initializeResultTable() {
        resultsTable.markAsDirty();
        resultsTable.setDoubleHeaderVisible(true);
        resultsTable.setSelectable(false);
        resultsTable.setSplitPosition(AccrualRateUtils.SPLIT_POSITION, Sizeable.Unit.PIXELS);
        resultsTable.setMinSplitPosition(AccrualRateUtils.MIN_SPLIT_POSITION, Sizeable.Unit.PIXELS);
        resultsTable.setMaxSplitPosition(AccrualRateUtils.MAX_SPLIT_POSITION, Sizeable.Unit.PIXELS);
        resultsTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        addResultTable();
    }

    private void addResultTable() {
        tableLayout.addComponent(resultsTable);
        tableLayout.addComponent(tableLogic.createControls());
    }

    private void configureTable() {
        LOGGER.info("configureTable starts");
        ExtPagedTable leftTable = resultsTable.getLeftFreezeAsTable();
        ExtPagedTable rightTable = resultsTable.getRightFreezeAsTable();
        rightHeaderDTO = new CustomTableHeaderDTO();
        leftHeaderDTO = new CustomTableHeaderDTO();
        CustomTableHeaderDTO recordHeaderDTO = new CustomTableHeaderDTO();

        AccrualRateUtils.getRightHeadersForAccrual(rightHeaderDTO, historyDdlb.getValue(), accrualRateSelectionDTO, AccrualRateUtils.SALES, selectedVariables, recordHeaderDTO);
        AccrualRateUtils.getLeftHeadersForSales(leftHeaderDTO);
        rightTable.setSortEnabled(false);
        leftTable.setSortEnabled(false);
        tableLogic.sinkItemPerPageWithPageLength(false);
        tableLogic.setPageLength(NumericConstants.TEN);
        resultBeanContainer = new ExtContainer<>(AccrualRateProjectionDTO.class, ExtContainer.DataStructureMode.LIST);
        tableLogic.setContainerDataSource(resultBeanContainer);
        resultBeanContainer.setRecordHeader(rightHeaderDTO.getSingleColumns());
        resultBeanContainer.setColumnProperties(rightHeaderDTO.getProperties());

        leftTable.setVisibleColumns(leftHeaderDTO.getSingleColumns().toArray());
        leftTable.setColumnHeaders(leftHeaderDTO.getSingleHeaders().toArray(new String[leftHeaderDTO.getSingleHeaders().size()]));
        leftTable.setDoubleHeaderVisibleColumns(leftHeaderDTO.getDoubleColumns().toArray());
        leftTable.setDoubleHeaderColumnHeaders(leftHeaderDTO.getDoubleHeaders().toArray(new String[leftHeaderDTO.getDoubleHeaders().size()]));
        leftTable.setDoubleHeaderMap(leftHeaderDTO.getDoubleHeaderMaps());

        rightTable.setVisibleColumns(rightHeaderDTO.getSingleColumns().toArray());
        rightTable.setColumnHeaders(rightHeaderDTO.getSingleHeaders().toArray(new String[rightHeaderDTO.getSingleHeaders().size()]));
        rightTable.setDoubleHeaderVisibleColumns(rightHeaderDTO.getDoubleColumns().toArray());
        rightTable.setDoubleHeaderColumnHeaders(rightHeaderDTO.getDoubleHeaders().toArray(new String[rightHeaderDTO.getDoubleHeaders().size()]));
        rightTable.setDoubleHeaderMap(rightHeaderDTO.getDoubleHeaderMaps());

        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }
        resultsTable.setDoubleHeaderMap(leftHeaderDTO.getDoubleHeaderMaps(), rightHeaderDTO.getDoubleHeaderMaps());
        resultsTable.setSplitPosition(NumericConstants.THIRTY);
        resultsTable.setDoubleHeaderVisible(true);
        loadFromAndToPeriods();
        LOGGER.info("configureTable ends");
    }

    @UiHandler("generateBtn")
    public void generateBtn(Button.ClickEvent event) {
        LOGGER.info("generate button click starts");

        AccrualRateUtils.getCurrentCheckValue(selectedVariables, customMenuItem, AccrualRateUtils.SALES);
        boolean isNotValidFilter = accrualRateProjectionLogic.checkInvalidFromToPeriods(fromDdlb.getValue(), toDdlb.getValue(), accrualRateSelectionDTO);
        if (Constant.SELECT_ONE.equals(String.valueOf(priceBasisDdlb.getValue())) || selectedVariables.isEmpty()) {
            NotificationUtils.getErrorNotification("Not all Required Fields are Populated", alertMsg.getString("ACCRUAL_SALES_01"));
        } else if (accrualRateProjectionLogic.checkInvalidFromToPeriods(fromDdlb.getValue(), toDdlb.getValue(), accrualRateSelectionDTO)) {
            AbstractNotificationUtils.getErrorNotification("Time Period conflict", alertMsg.getString("ACR_MSG_ID_03"));
        } else {
            if ((AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) || AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction())) && (!session.isFileNotChanged() && !session.isNewFileCalculationNeeded())) {
                AbstractNotificationUtils.getInfoNotification("Confirmation", alertMsg.getString("ACR_MSG_ID_07"));
            }
            accrualRateSelectionDTO.setIsFilterValid(!isNotValidFilter && StringUtils.isNotBlank((String) fromDdlb.getValue()));
            accrualRateSelectionDTO.setPriceBasis(priceBasisDdlb.getValue().toString());
            tableLayout.removeAllComponents();
            tableLogic = new AccrualRateProjectionTableLogic();
            resultsTable = new FreezePagedTable(tableLogic);
            tableLogic.setRequiredCount(false);
            if (!AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction())) {
                callSalesInsertProcedure();
            }
            loadResultsTable();
            map.put(Constant.PERIOD_BASIS, priceBasisDdlb.getValue());
            map.put(Constant.IS_SALES_GENERATED, true);
        }
        LOGGER.info("generate button click end");
    }

    @UiHandler("resetBtn")
    public void resetBtn(Button.ClickEvent event) {
        LOGGER.info("Reset button click starts");
        new AbstractNotificationUtils() {
            public void noMethod() {
                return;
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                LOGGER.info("Entering resetBtn method");
                getCheckedValues();
                resetBtnLogic();
                LOGGER.info("End of resetBtn method");
            }

        }.getOkCancelMessage("Reset Confirmation", alertMsg.getString("ACCRUAL_SALES_02"));
        LOGGER.info("Reset button click end");
    }

    protected void getCheckedValues() {
        LOGGER.info("getCheckedValues starts");
        AccrualRateUtils.getCurrentCheckValue(selectedVariables, customMenuItem, AccrualRateUtils.SALES);
        if (selectedVariables.isEmpty()) {
            selectedVariables.add("Total Units");
        }
        LOGGER.info("getCheckedValues ends");
    }

    private void resetBtnLogic() {
        LOGGER.info("resetBtnLogic starts");
        for (CustomMenuItem item : customMenuItem.getChildren()) {
            item.setChecked(false);
        }
        historyDdlb.select(Constant.SELECT_ONE);
        boolean isViewMode = AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction());
        if (isViewMode) {
            priceBasisDdlb.setReadOnly(false);
        }
        priceBasisDdlb.setValue(Constant.SELECT_ONE);
        fromDdlb.select(Constant.SELECT_ONE);
        toDdlb.select(Constant.SELECT_ONE);
        if (isViewMode) {
            priceBasisDdlb.setReadOnly(true);
        }
        LOGGER.info("resetBtnLogic end");
    }

    private void generateBtnLogic() {
        LOGGER.info("generateBtnLogic starts");
        final boolean isViewMode = AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction());
        accrualRateSelectionDTO.setSessionDto(session);
        tableLogic.setRequiredData(AccrualRateUtils.SALES, accrualRateSelectionDTO, selectedVariables, isViewMode);
        map.put(Constant.PERIOD_BASIS, priceBasisDdlb.getValue().toString());
        LOGGER.info("generateBtnLogic end");
    }

    public void saveTabSelection() {
        Map selectionMap = new HashMap();
        selectionMap.put(Constant.HISTORY_CAPS, String.valueOf(historyDdlb.getValue()));
        selectionMap.put(Constant.FREQUENCY_SMALL, String.valueOf(frequencyDdlb.getValue()));
        selectionMap.put("Price Basis", String.valueOf(priceBasisDdlb.getValue()));
        String varValue = StringUtils.EMPTY;
        String actionValue = (Constant.EDIT_CAPS.equalsIgnoreCase(session.getAction()) || Constant.VIEW_CAPS.equalsIgnoreCase(session.getAction())) ? Constant.UPDATE_SMALL : "Save";
        for (String variables : selectedVariables) {
            if (StringUtils.EMPTY.equals(varValue)) {
                varValue += variables;
            } else {

                varValue += "," + variables;
            }
        }
        selectionMap.put(Constant.VARIABLES, varValue);
        dsLogic.saveScreenSelection(session.getProjectionId(), selectionMap, Constant.SALES_SMALL, actionValue);
    }

    private void setProjectionSelection() {
        Map<Object, Object> projectionSelectionmap = DSLogic.getProjectionSelection(session.getProjectionId(), Constant.SALES_SMALL);
        if (projectionSelectionmap != null && !projectionSelectionmap.isEmpty()) {
            Object value = projectionSelectionmap.get(Constant.FREQUENCY_SMALL);
            if (value != null) {
                frequencyDdlb.setValue(projectionSelectionmap.get(Constant.FREQUENCY_SMALL));
                frequencyDdlb.setImmediate(true);
            }
            value = projectionSelectionmap.get(Constant.HISTORY_CAPS);
            if (value != null) {
                historyDdlb.setValue(String.valueOf(value));
                historyDdlb.setImmediate(true);
                setDefaultFocus();
            }
            value = projectionSelectionmap.get("Price Basis");
            if (value != null) {
                priceBasisDdlb.setValue(String.valueOf(value));
            }
            this.map.put(Constant.PERIOD_BASIS, priceBasisDdlb.getValue());
            value = projectionSelectionmap.get(Constant.VARIABLES);
            if (value != null) {
                String val = value.toString();
                int customMenuSize = customMenuItem.getSize() - 1;
                final String[] col = val.split(",");
                for (int i = 0; i < col.length; i++) {
                    if (i == Constant.ZERO) {
                        for (CustomMenuBar.CustomMenuItem string : customMenuItem.getChildren()) {
                            if ((customMenuSize == col.length) && string.getText().equals("Check All")) {
                                string.setChecked(true);
                            }
                            if (string.getText().equals(String.valueOf(col[i]).trim())) {
                                string.setChecked(true);
                            }
                        }
                    } else {
                        for (CustomMenuBar.CustomMenuItem string : customMenuItem.getChildren()) {
                            if ((customMenuSize == col.length) && string.getText().equals("Check All")) {
                                string.setChecked(true);
                            }
                            if (string.getText().equals(String.valueOf(col[i]).trim())) {
                                string.setChecked(true);
                            }
                        }
                    }
                }
            }
        }
    }

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

            for (int i = 0; i < rightHeaderDTO.getDoubleColumns().size(); i++) {
                Object object = rightHeaderDTO.getDoubleColumns().get(i);
                if (accrualRateSelectionDTO.getFilterList().contains(object)) {
                    fromDdlb.addItem(object);
                    fromDdlb.setItemCaption(object, rightHeaderDTO.getDoubleHeaders().get(i));

                    toDdlb.addItem(object);
                    toDdlb.setItemCaption(object, rightHeaderDTO.getDoubleHeaders().get(i));
                }
            }
            isFilterLoadNeeded = false;
        }

    }

    /**
     * Method used to export the data in the list view to excel..
     *
     * @param event
     */
    @UiHandler("excelBtn")
    public void excelBtnLogic(Button.ClickEvent event) {
        final ExtCustomTable excelTable = new ExtCustomTable();
        final ExtContainer<AccrualRateProjectionDTO> excelBeanContainer = new ExtContainer<>(AccrualRateProjectionDTO.class, ExtContainer.DataStructureMode.LIST);
        configureAndLoadDataForExcel(excelTable, excelBeanContainer);
        if (excelTable.size() > 0) {
            ForecastUI.EXCEL_CLOSE = true;
            ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(excelTable), AccrualRateUtils.SALES, AccrualRateUtils.SALES, "Sales.xls", false);
            exp.export();
        }
        tableLayout.removeComponent(excelTable);
    }

    /**
     * Method used to load the data for excel export.
     *
     * @param excelTable
     * @param excelContainer
     */
    public void configureAndLoadDataForExcel(final ExtCustomTable excelTable, final ExtContainer<AccrualRateProjectionDTO> excelContainer) {

        excelTable.setImmediate(true);
        excelTable.setVisible(true);
        tableLayout.addComponent(excelTable);
        excelHeaderDTO = new CustomTableHeaderDTO();
        CustomTableHeaderDTO recordHeaderDTO = new CustomTableHeaderDTO();
        AccrualRateUtils.getLeftHeadersForSales(excelHeaderDTO);
        AccrualRateUtils.getRightHeadersForAccrual(excelHeaderDTO, historyDdlb.getValue(), accrualRateSelectionDTO, AccrualRateUtils.SALES, selectedVariables, recordHeaderDTO);

        List visibleColumns = new ArrayList(excelHeaderDTO.getSingleColumns());
        List<String> columnHeaders = new ArrayList(excelHeaderDTO.getSingleHeaders());
        List visibleColumns2 = new ArrayList(excelHeaderDTO.getDoubleColumns());
        List<String> columnHeaders2 = new ArrayList(excelHeaderDTO.getDoubleHeaders());

        excelContainer.setColumnProperties(excelHeaderDTO.getProperties());
        excelContainer.setRecordHeader(recordHeaderDTO.getSingleColumns());
        excelTable.setContainerDataSource(excelContainer);
        excelTable.setVisibleColumns(visibleColumns.toArray());
        excelTable.setColumnHeaders(Arrays.copyOf(columnHeaders.toArray(), columnHeaders.size(), String[].class));
        excelTable.setDoubleHeaderVisibleColumns(visibleColumns2.toArray());
        excelTable.setDoubleHeaderColumnHeaders(Arrays.copyOf(columnHeaders2.toArray(), columnHeaders2.size(), String[].class));

        excelTable.setDoubleHeaderMap(excelHeaderDTO.getDoubleHeaderMaps());

        excelTable.setDoubleHeaderVisible(true);
        final boolean isViewMode = AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction());
        excelContainer.addAll(accrualRateProjectionLogic.getDataForSales(accrualRateSelectionDTO, 0, 0, true, isViewMode));

    }

    /**
     *
     */
    private void configureOnEditOrView() {
        if (session.isFileNotChanged()) {
            generateBtnLogic();
        } else if (session.isNewFileCalculationNeeded()) {
            callSalesInsertProcedure();
            generateBtnLogic();
        } else {
            generateBtnLogic();
        }
        priceBasisDdlb.setReadOnly(AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction()));
    }

    /**
     *
     */
    private void callSalesInsertProcedure() {
        final Object[] parameters = {accrualRateSelectionDTO.getProjectionId(), accrualRateSelectionDTO.getUserId(), accrualRateSelectionDTO.getSessionId(), priceBasisDdlb.getValue()};
        accrualRateProjectionLogic.callARPProcedure(parameters, "PRC_ACCRUAL_SALES");
    }

    public void loadResultsTable() {
        configureTable();
        initializeResultTable();
        getCheckedValues();
        generateBtnLogic();
        loadFromAndToPeriods();
    }

    public void setDefaultFocus() {
        historyDdlb.focus();
    }

    public void configurePermission() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(session.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Accrual Rate Projection" + "," + "Sales");
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

        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }
}
