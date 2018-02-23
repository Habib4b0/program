
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
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.CustomMenuBar.CustomMenuItem;
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
 * Class used to build and configure the Details Tab.
 *
 * @author sibi
 */
public class Details extends CustomComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(Details.class);

    @UiField("frequencyDdlb")
    private ComboBox frequencyDdlb;

    @UiField("variablesMenuBar")
    protected CustomMenuBar variablesMenuBar;

    @UiField("brandDdlb")
    private ComboBox brandDdlb;

    @UiField("historyDdlb")
    private ComboBox historyDdlb;

    @UiField("customerDdlb")
    private ComboBox customerDdlb;

    @UiField("productDdlb")
    private ComboBox productDdlb;

    @UiField("fromDdlb")
    private ComboBox fromDdlb;

    @UiField("toDdlb")
    private ComboBox toDdlb;

    @UiField("resetBtn")
    private Button resetBtn;

    @UiField("generateBtn")
    private Button generateBtn;

    @UiField("tableVerticalLayout")
    private VerticalLayout tableVerticalLayout;

    private CustomMenuBar.CustomMenuItem customMenuItem;

    private AccrualRateProjectionTableLogic tableLogic = new AccrualRateProjectionTableLogic();

    private ExtContainer<AccrualRateProjectionDTO> resultBeanContainer;

    private FreezePagedTable table = new FreezePagedTable(tableLogic);

    private CustomTableHeaderDTO rightHeaderDTO;

    private CustomTableHeaderDTO leftHeaderDTO;

    protected SessionDTO session;

    protected DSLogic dsLogic = new DSLogic();

    private final AccrualRateSelectionDTO accrualRateSelectionDTO = new AccrualRateSelectionDTO();

    private final AccrualRateProjectionLogic accrualRateProjectionLogic = AccrualRateProjectionLogic.getInstance();

    private static final ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");

    private boolean isFilterLoadNeeded = true;

    private final Map<String, String> map;

    @UiField("excelBtn")
    private Button excelBtn;

    private final Resource excelExportImage = new ThemeResource("../../icons/excel.png");

    public Details(final SessionDTO sessionDTO, final Map<String, String> map) {
        accrualRateProjectionLogic.setValuesInDTO(sessionDTO, accrualRateSelectionDTO);
        this.map = map;
        this.session = sessionDTO;
        init();
    }

    /**
     * Methods loads the UI Components from xml and configures the components.
     */
    private void init() {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/accrualrateprojection/details.xml"), this));
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
        frequencyDdlb.addItem(Constant.MONTHLY);
        frequencyDdlb.setNullSelectionAllowed(false);
        frequencyDdlb.select(Constant.MONTHLY);
        frequencyDdlb.setReadOnly(true);

        historyDdlb.addItems(AccrualRateUtils.getInstance().historyPeriods12);
        historyDdlb.setNullSelectionAllowed(true);
        historyDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        historyDdlb.select(null);

        String[] variables = AccrualRateUtils.DetailsVariables.toArray();
        customMenuItem = variablesMenuBar.addItem(AccrualRateUtils.SELECT_VARIABLES, null);
        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[variables.length];
        for (int i = 0; i < variables.length; i++) {
            customItem[i] = customMenuItem.addItem(variables[i].trim(), null);
            customItem[i].setCheckable(true);
            customItem[i].setItemClickable(true);
            customItem[i].setItemClickNotClosable(true);
            if (i == 0) {
                customItem[i].setCheckAll(true);
            }
        }

        loadDDLB(customerDdlb, AccrualRateUtils.CUSTOMER, null);
        loadDDLB(brandDdlb, AccrualRateUtils.BRAND, null);
        loadDDLB(productDdlb, AccrualRateUtils.PRODUCT, null);

        productDdlb.addItem(Constant.SELECT_ONE);
        productDdlb.addItem(AccrualRateUtils.ALL_PRODUCTS);
        productDdlb.setNullSelectionAllowed(true);
        productDdlb.setNullSelectionItemId(Constant.SELECT_ONE);
        productDdlb.select(null);

        addListenersToDDLB();
        excelBtn.setIcon(excelExportImage);

    }

    /**
     * Method used to configure the table and load headers.
     */
    private void configureTable() {

        ExtPagedTable leftTable = table.getLeftFreezeAsTable();
        ExtPagedTable rightTable = table.getRightFreezeAsTable();
        rightHeaderDTO = new CustomTableHeaderDTO();
        leftHeaderDTO = new CustomTableHeaderDTO();
        CustomTableHeaderDTO recordHeaderDTO = new CustomTableHeaderDTO();
        AccrualRateUtils.getRightHeadersForAccrual(rightHeaderDTO, historyDdlb.getValue(), accrualRateSelectionDTO, AccrualRateUtils.DETAILS, null, recordHeaderDTO);
        AccrualRateUtils.getLeftHeadersForDetails(leftHeaderDTO);

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
        table.setDoubleHeaderMap(leftHeaderDTO.getDoubleHeaderMaps(), rightHeaderDTO.getDoubleHeaderMaps());
        table.setDoubleHeaderVisible(true);
        loadFromAndToPeriods();
    }

    /**
     * Adds value change listeners to Customer and BrandDDLB.
     */
    private void addListenersToDDLB() {

        customerDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                loadDDLB(brandDdlb, AccrualRateUtils.BRAND, event.getProperty().getValue());
            }
        });

        brandDdlb.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                loadDDLB(productDdlb, AccrualRateUtils.PRODUCT, event.getProperty().getValue());
            }
        });

    }

    /**
     * Method loads the Customer,Brand and Product DDLB based on other values.
     *
     * @param comboBox
     * @param ddlbName
     * @param filterValue
     */
    private void loadDDLB(final ComboBox comboBox, final String ddlbName, final Object filterValue) {

        comboBox.removeAllItems();
        switch (ddlbName) {
            case AccrualRateUtils.CUSTOMER:
                comboBox.addItem(AccrualRateUtils.ALL_CUSTOMERS);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(AccrualRateUtils.ALL_CUSTOMERS);
                accrualRateProjectionLogic.loadCustomers(comboBox, accrualRateSelectionDTO.getProjectionId(), AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction()), session.getCurrentTableNames());
                comboBox.select(null);
                break;
            case AccrualRateUtils.BRAND:
                comboBox.addItem(AccrualRateUtils.ALL_BRANDS);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(AccrualRateUtils.ALL_BRANDS);
                accrualRateProjectionLogic.loadBrands(comboBox, filterValue, accrualRateSelectionDTO.getProjectionId(), AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction()), session.getCurrentTableNames());
                comboBox.select(null);
                break;
            case AccrualRateUtils.PRODUCT:
                comboBox.addItem(Constant.SELECT_ONE);
                comboBox.addItem(AccrualRateUtils.ALL_PRODUCTS);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(Constant.SELECT_ONE);
                accrualRateProjectionLogic.loadProducts(comboBox, customerDdlb.getValue(), filterValue, accrualRateSelectionDTO.getProjectionId(), AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction()), session.getCurrentTableNames());
                comboBox.select(null);
                break;
            default:
                LOGGER.warn("ddlbName is not valid: " + ddlbName);
                break;
        }

    }

    /**
     * Click Listener for reset button.
     *
     * @param event
     */
    @UiHandler("resetBtn")
    public void resetBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("Inside Reset Click Listener");
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                return;
            }

            @Override
            public void yesMethod() {
                historyDdlb.select(null);
                customerDdlb.select(null);
                brandDdlb.select(null);
                productDdlb.select(null);
                for (CustomMenuItem item : customMenuItem.getChildren()) {
                    item.setChecked(false);
                }
                fromDdlb.select(null);
                toDdlb.select(null);
            }
        }.getOkCancelMessage(AccrualRateUtils.RESET_CONFIRMATION, alertMsg.getString("ACR_MSG_ID_04"));
    }

    /**
     * Click Listener for generate button.
     *
     * @param event
     */
    @UiHandler("generateBtn")
    public void generateBtnLogic(Button.ClickEvent event) {
        LOGGER.debug("Inside Generate Click Listener");
        try {
            boolean isNotValidFilter = accrualRateProjectionLogic.checkInvalidFromToPeriods(fromDdlb.getValue(), toDdlb.getValue(), accrualRateSelectionDTO);
            AccrualRateUtils.getCurrentCheckValue(accrualRateSelectionDTO.getVariableList(), customMenuItem, AccrualRateUtils.SALES);
            if (isNotValidFilter) {
                AbstractNotificationUtils.getErrorNotification("Time Period conflict", alertMsg.getString("ACR_MSG_ID_06"));
            } else if (accrualRateSelectionDTO.getVariableList().isEmpty()) {
                AbstractNotificationUtils.getErrorNotification("Not all Required Fields are Populated", alertMsg.getString("ACR_MSG_ID_05"));
            } else {
                if (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) && !session.isFileNotChanged()
                        && !session.isNewFileCalculationNeeded() && !map.containsKey(Constant.IS_SALES_GENERATED) && !map.containsKey(Constant.IS_RATES_GENERATED)) {
                    AbstractNotificationUtils.getInfoNotification("Error", alertMsg.getString("ACR_MSG_ID_09"));
                    return;
                } else if ((AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) || AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction())) && (!session.isFileNotChanged() && !session.isNewFileCalculationNeeded())) {
                    AbstractNotificationUtils.getInfoNotification("Confirmation", alertMsg.getString("ACR_MSG_ID_07"));
                }
                accrualRateSelectionDTO.setPeriodBasis(map.containsKey(Constant.PERIOD_BASIS) ? map.get(Constant.PERIOD_BASIS) : StringUtils.EMPTY);
                accrualRateSelectionDTO.setRateBasis(map.containsKey("Rate Basis") ? map.get("Rate Basis") : StringUtils.EMPTY);
                accrualRateSelectionDTO.setIsFilterValid(!isNotValidFilter && StringUtils.isNotBlank((String) fromDdlb.getValue()));
                tableVerticalLayout.removeAllComponents();
                tableLogic = new AccrualRateProjectionTableLogic();
                table = new FreezePagedTable(tableLogic);
                tableLogic.setRequiredCount(false);
                configureTable();
                initializeResultTable();
                if (!AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction())) {
                    callDetailsProcedure();
                }
                generateLogic();
                loadFromAndToPeriods();
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
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
     * Adds the table and pagination controls to the layout.
     */
    private void addResultTable() {
        tableVerticalLayout.addComponent(table);
        tableVerticalLayout.addComponent(tableLogic.createControls());
    }

    /**
     * Method used to trigger the table logic to retrieve the data from data
     * base.
     */
    private void generateLogic() {

        accrualRateSelectionDTO.setCustomer(customerDdlb.getValue());
        accrualRateSelectionDTO.setBrand(brandDdlb.getValue());
        accrualRateSelectionDTO.setProduct(productDdlb.getValue());
        final boolean isViewMode = AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction());
        tableLogic.setRequiredData(AccrualRateUtils.DETAILS, accrualRateSelectionDTO, null, isViewMode);
    }

    /**
     * Loads the From and To Period DDLB based on the selection in Details tab.
     */
    private void loadFromAndToPeriods() {
        if (isFilterLoadNeeded) {
            fromDdlb.removeAllItems();
            fromDdlb.addItem(Constant.SELECT_ONE);
            fromDdlb.setNullSelectionAllowed(true);
            fromDdlb.setNullSelectionItemId(Constant.SELECT_ONE);

            toDdlb.removeAllItems();
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
     * Method used to export the data in the list view to excel.
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
                ForecastUI.setEXCEL_CLOSE(true);
                ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(excelTable), AccrualRateUtils.DETAILS, AccrualRateUtils.DETAILS, "Details.xls", false);
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

        excelTable.setVisible(false);
        tableVerticalLayout.addComponent(excelTable);

        final CustomTableHeaderDTO excelHeaderDTO = new CustomTableHeaderDTO();
        CustomTableHeaderDTO recordHeaderDTO = new CustomTableHeaderDTO();
        AccrualRateUtils.getLeftHeadersForDetails(excelHeaderDTO);
        AccrualRateUtils.getRightHeadersForAccrual(excelHeaderDTO, historyDdlb.getValue(), accrualRateSelectionDTO, AccrualRateUtils.DETAILS, null, recordHeaderDTO);
        List singleVisibleColumns = new ArrayList(excelHeaderDTO.getSingleColumns());
        List<String> singleClumnHeaders = new ArrayList(excelHeaderDTO.getSingleHeaders());
        List doubleVisibleColumns = new ArrayList(excelHeaderDTO.getDoubleColumns());
        List<String> doubleColumnHeaders = new ArrayList(excelHeaderDTO.getDoubleHeaders());
        excelContainer.setRecordHeader(recordHeaderDTO.getSingleColumns());
        excelContainer.setColumnProperties(excelHeaderDTO.getProperties());
        excelTable.setContainerDataSource(excelContainer);

        excelTable.setVisibleColumns(singleVisibleColumns.toArray());
        excelTable.setColumnHeaders(singleClumnHeaders.toArray(new String[excelHeaderDTO.getSingleHeaders().size()]));
        excelTable.setDoubleHeaderVisibleColumns(doubleVisibleColumns.toArray());
        excelTable.setDoubleHeaderColumnHeaders(doubleColumnHeaders.toArray(new String[excelHeaderDTO.getDoubleHeaders().size()]));
        excelTable.setDoubleHeaderMap(excelHeaderDTO.getDoubleHeaderMaps());
        excelTable.setDoubleHeaderVisible(true);
        final boolean isViewMode = AccrualRateUtils.VIEW.equalsIgnoreCase(session.getAction());
        excelContainer.addAll(accrualRateProjectionLogic.getDataForDetails(accrualRateSelectionDTO, 0, 0, true, isViewMode));
    }

    public void saveTabSelection() {
        Map selectionMap = new HashMap();
        String actionValue;
        selectionMap.put(Constant.FREQUENCY_SMALL, String.valueOf(frequencyDdlb.getValue()));
        selectionMap.put(Constant.HISTORY_CAPS, String.valueOf(historyDdlb.getValue()));
        selectionMap.put(Constant.CUSTOMER_SMALL, String.valueOf(customerDdlb.getValue()));
        selectionMap.put(Constant.BRAND_CAPS, String.valueOf(brandDdlb.getValue()));
        selectionMap.put(Constant.PRODUCT_LABEL, String.valueOf(productDdlb.getValue()));
        selectionMap.put("FromDDLB", String.valueOf(fromDdlb.getValue()));
        selectionMap.put("TODDLB", String.valueOf(toDdlb.getValue()));
        String varValue = StringUtils.EMPTY;
        actionValue = (Constant.EDIT_CAPS.equalsIgnoreCase(session.getAction()) || Constant.VIEW_CAPS.equalsIgnoreCase(session.getAction())) ? "Update" : "Save";

        for (String variables : accrualRateSelectionDTO.getVariableList()) {
            if (StringUtils.EMPTY.equals(varValue)) {
                varValue += variables;
            } else {
                varValue += "," + variables;
            }
        }
        selectionMap.put(Constant.VARIABLES, varValue);
        dsLogic.saveScreenSelection(session.getProjectionId(), selectionMap, Constant.DETAILS, actionValue);
    }

    private void configureOnEditOrView() {
        Map<Object, Object> editviewMap = null;

        editviewMap = DSLogic.getProjectionSelection(session.getProjectionId(), Constant.DETAILS);
        if (editviewMap != null && !editviewMap.isEmpty()) {
            Object value = editviewMap.get(Constant.FREQUENCY_SMALL);
            if (value != null) {
                frequencyDdlb.setValue(editviewMap.get(Constant.FREQUENCY_SMALL));
            }
            value = editviewMap.get(Constant.HISTORY_CAPS);
            if (value != null) {
                historyDdlb.setValue(String.valueOf(value));
            }
            value = editviewMap.get(Constant.CUSTOMER_SMALL);
            if (value != null) {
                customerDdlb.setValue(String.valueOf(value));
            }
            value = editviewMap.get(Constant.PRODUCT_LABEL);
            if (value != null) {
                productDdlb.setValue(String.valueOf(value));
            }
            value = editviewMap.get(Constant.BRAND_CAPS);
            if (value != null) {
                brandDdlb.setValue(String.valueOf(value));
            }

            value = editviewMap.get(Constant.VARIABLES);
            if (value != null) {
                String val = value.toString();

                final String[] col = val.split(",");
                for (int i = 0; i < col.length; i++) {
                    if (i == Constant.ZERO) {
                        for (CustomMenuBar.CustomMenuItem string : customMenuItem.getChildren()) {
                            if (string.getText().equals(String.valueOf(col[i]).trim())) {
                                string.setChecked(true);
                            }
                        }
                    } else {
                        for (CustomMenuBar.CustomMenuItem string : customMenuItem.getChildren()) {
                            if (string.getText().equals(col[i])) {
                                string.setChecked(true);
                            }
                        }
                    }
                }
            }
            value = editviewMap.get("FromDDLB");
            if (value != null) {
                fromDdlb.setValue(String.valueOf(value));
            }
            value = editviewMap.get("TODDLB");
            if (value != null) {
                toDdlb.setValue(String.valueOf(value));
            }

        }
        AccrualRateUtils.getCurrentCheckValue(accrualRateSelectionDTO.getVariableList(), customMenuItem, AccrualRateUtils.SALES);
    }

    public void callDetailsProcedure() {
        final Object[] parameters = {accrualRateSelectionDTO.getProjectionId(), map.get(Constant.PERIOD_BASIS), accrualRateSelectionDTO.getUserId(), accrualRateSelectionDTO.getSessionId()};
        accrualRateProjectionLogic.callARPProcedure(parameters, "PRC_ACCRUAL_DETAILS");
    }

    private void loadGridOnEdit() {
        if (session.isFileNotChanged()) {
            generateLogic();
        } else if (session.isNewFileCalculationNeeded()) {
            callDetailsProcedure();
            generateLogic();
        } else {
            generateLogic();
        }
    }

    public void configurePermission() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(session.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Accrual Rate Projection" + "," + Constant.DETAILS);
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
