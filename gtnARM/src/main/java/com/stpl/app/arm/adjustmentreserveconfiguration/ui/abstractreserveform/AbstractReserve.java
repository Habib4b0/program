/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.abstractreserveform;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentReserveLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.tablelogic.AdjustmentReserveTableLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.ui.form.ReserveFieldFactory;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.util.NumericConstants;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Srithar.Raju
 */
public abstract class AbstractReserve extends CustomWindow {

    /**
     * The Table Layout
     */
    @UiField("resultsTableLayoutRes")
    private VerticalLayout resultsTableLayoutRes;
    /**
     * The Company Combo box where Options will be all values from Company
     * Master, Company Type = GLcomp The DDLB needs to show the combination of
     * “COMPANY ID – COMPANY NAME”.
     */
    @UiField("companyDdlbRes")
    protected ComboBox companyDdlbRes;
    /**
     * The Business Combo box where Options will be any Company Master record
     * where Company Type = Business Unit.The DDLB needs to show the combination
     * of “COMPANY ID – COMPANY NAME”.
     */
    @UiField("businessDdlbRes")
    protected ComboBox businessDdlbRes;
    /**
     * The Deduction Category Combo box links to the Rebate Schedule Category
     * data attribute. Will show all distinct values of the Rebate Schedule
     * Category data attribute.
     */
    @UiField("deductionCategoryDdlbRes")
    protected ComboBox deductionCategoryDdlbRes;
    /**
     * The Deduction Type Combo box links to the Rebate Schedule Type data
     * attribute. Will show all distinct values of the Rebate Schedule Type data
     * attribute. The values listed in the DDLB will be filtered based on the
     * value selected for Deduction Category. Based on the value selected in
     * Deduction Category the DDLB will only show the distinct values that are
     * associated with rebate schedules that have value selected in the
     * “Deduction Category” DDLB.
     */
    @UiField("deductionTypeDdlbRes")
    protected ComboBox deductionTypeDdlbRes;
    /**
     * The Deduction Program Combo box links to the Rebate Program Type data
     * attribute. Will show all distinct values of the Rebate Program Type data
     * attribute. The values listed in the DDLB will be filtered based on the
     * value selected for Deduction Type. Based on the value selected in
     * Deduction Category the DDLB will only show the distinct values that are
     * associated with rebate schedules that have value selected in the
     * “Deduction Category” DDLB.
     */
    @UiField("deductionProgramDdlbRes")
    protected ComboBox deductionProgramDdlbRes;
    /**
     * In Configuration Type OptionGroup If selected 'Reserve Detail' the
     * Reserve Account(s) will be displayed in the list view. The Reserve
     * Accounts ddlb will be enabled. If selected 'GTNDetails' the Reserve
     * Account(s) will not be displayed in the list view. The Reserve Accounts
     * ddlb will be disabled.
     *
     */
    @UiField("configurationTypeOpgRes")
    protected OptionGroup configurationTypeOpgRes;
    /**
     * The View Option Group determines the List View to show the current
     * Records or the Historical Records
     */
    @UiField("viewOpgRes")
    protected OptionGroup viewOpgRes;
    /**
     * The Mass Update Combo box shows the field to be populated.Options will be
     * all of the columns available in the list view (based on Configuration
     * Type radio selected).
     */
    @UiField("massfieldDdlbRes")
    protected ComboBox massfieldDdlbRes;
    /**
     * The Mass Value Combo box is for selection of a value in mass update
     */
    @UiField("massValueDdlbRes")
    protected ComboBox massValueDdlbRes;
    /**
     * The Mass Value Text Field is for entering a value in mass update
     */
    @UiField("massValueRes")
    protected TextField massValueRes;
    /**
     * The Search Form Layout which will be disabled in Add/Edit/View Modes
     */
    @UiField("searchFormLayoutRes")
    protected HorizontalLayout searchFormLayoutRes;
    /**
     * Field is used to reset the company selection criteria.
     */
    @UiField("resetBtnRes")
    protected Button resetBtnRes;
    @UiField("searchBtnRes")
    protected Button searchBtnRes;
    @UiField("addLineBtnRes")
    protected Button addLineBtnRes;
    @UiField("resetLineBtnRes")
    protected Button resetLineBtnRes;
    @UiField("removeLineBtnRes")
    protected Button removeLineBtnRes;
    @UiField("copyLineBtnRes")
    protected Button copyLineBtnRes;
    @UiField("saveBtnRes")
    protected Button saveBtnRes;
    @UiField("closeBtnRes")
    protected Button closeBtnRes;
    @UiField("exportBtnRes")
    protected Button exportBtnRes;
    @UiField("populateBtn")
    protected Button populateBtn;
    @UiField("searchLayout")
    protected GridLayout searchLayout;
    @UiField("addPanelRes")
    protected Panel addPanelRes;

    public Button getCloseBtnRes() {
        return closeBtnRes;
    }
    /**
     * The Table Logic For Adjustment Reserve Configuration in Add/Edit Mode
     */
    protected AdjustmentReserveTableLogic detailsTableLogic = new AdjustmentReserveTableLogic();
    /**
     * The Results Table
     */
    protected ExtPagedTable resultsTable = new ExtPagedTable(detailsTableLogic);
    /**
     * The Available Results Container
     */
    protected final BeanItemContainer<AdjustmentReserveDTO> detailsTableContainer = new BeanItemContainer<>(AdjustmentReserveDTO.class);
    /**
     * The Adjustment Reserve Logic
     */
    protected AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();
    /**
     * The Session DTO
     */
    protected SessionDTO sessionDTO;
    /**
     * This field is used to save the field values.
     */
    protected ReserveSelection selection;
    /**
     * The Constant LOGGER.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractNotificationUtils.class);
    /**
     * Binderdto is datasource for the binder.
     */
    protected AdjustmentReserveDTO binderDto = new AdjustmentReserveDTO();
    /**
     * binder used to bind the fields from the page
     */
    protected CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<AdjustmentReserveDTO>(binderDto));
    /**
     * This is used to find the value change of mass update done or not
     */
    boolean isvValueChange;
    /**
     * This is used to find the value change of mass update done or not
     */
    boolean isTableLoaded = Boolean.FALSE;
    /**
     * This is used to check wheather the values of dataselection is changed or
     * not.
     */
    boolean isDataSelectionSaved;

    public AbstractReserve(String caption, SessionDTO sessionDTO,ReserveSelection selection) {
        super(caption);
        this.sessionDTO = sessionDTO;
        this.selection = selection;
        init();
    }

    /**
     * Configuring Fields For Add/Edit/View Mode In Adjustment Reserve
     * Configuration
     */
    protected void configureFields() {
        loadSelection();
        searchFormLayoutRes.setVisible(false);
        loadComboBox();
        viewOpgRes.addItem(GlobalConstants.getCurrent());
        viewOpgRes.addItem(GlobalConstants.getHistory());
        viewOpgRes.select(GlobalConstants.getCurrent());
        loadMassfield();
        getBinder();
        massValueDdlbRes.setVisible(Boolean.FALSE);
        massValueDdlbRes.setImmediate(Boolean.TRUE);
        selection.setIsCurrent(Boolean.TRUE);
        configureTable();
        configurationTypeOpgRes.addItem(ARMConstants.getReserveDetails());
        configurationTypeOpgRes.addItem(ARMConstants.getGTNDetails());
        configurationTypeOpgRes.select(ARMConstants.getReserveDetails());
        searchBtnRes.setVisible(Boolean.FALSE);
        exportBtnRes.setPrimaryStyleName("link");
        exportBtnRes.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");

    }

    protected void loadComboBox() {
        CommonLogic.configureDropDowns(companyDdlbRes, "getCompanyQuery", Boolean.TRUE);
        CommonLogic.configureDropDowns(businessDdlbRes, "getBusinessQuery", Boolean.TRUE);
        CommonUtils.loadComboBoxWithIntegerForComboBox(deductionCategoryDdlbRes, "RS_CATEGORY", Boolean.FALSE);

        deductionTypeDdlbRes.setNullSelectionAllowed(false);
        deductionProgramDdlbRes.setNullSelectionAllowed(false);
    }

    /**
     * Initialization Of the UI Components and Reading XML files and adding
     * corresponding styles to it
     */
    private void init() {
        center();
        setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
        setPositionX(0);
        setPositionY(0);
        setMinimizeToTray();
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(ARMUtils.BOOTSTRAP);
        addStyleName(ARMUtils.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setContent(Clara.create(getClass().getResourceAsStream("/adjustment_reserve_config/adjustment-reserve-form.xml"), this));
    }

    /**
     * Loading the mass update field ddlb.
     */
    private void loadMassfield() {
        isvValueChange = Boolean.TRUE;
        massValueDdlbRes.removeAllItems();
        massfieldDdlbRes.removeAllItems();
        massValueRes.setValue(StringUtils.EMPTY);
        massfieldDdlbRes.setImmediate(true);
        massfieldDdlbRes.addItem(GlobalConstants.getSelectOne());
        massfieldDdlbRes.setNullSelectionAllowed(true);
        massfieldDdlbRes.setNullSelectionItemId(GlobalConstants.getSelectOne());

        if (selection.isIsGTNDetails()) {
            for (int i = 0; i < ARMUtils.ADJUSTMENT_GTN_DETAILS_COLUMNS.length; i++) {
                massfieldDdlbRes.addItem(ARMUtils.ADJUSTMENT_GTN_DETAILS_COLUMNS[i]);
                massfieldDdlbRes.setItemCaption(ARMUtils.ADJUSTMENT_GTN_DETAILS_COLUMNS[i], ARMUtils.ADJUSTMENT_GTN_DETAILS_Header[i]);
            }
        } else {
            for (int i = 0; i < ARMUtils.ADJUSTMENT_RESERVE_MASSUPDATE_COLUMN.length; i++) {
                massfieldDdlbRes.addItem(ARMUtils.ADJUSTMENT_RESERVE_MASSUPDATE_COLUMN[i]);
                massfieldDdlbRes.setItemCaption(ARMUtils.ADJUSTMENT_RESERVE_MASSUPDATE_COLUMN[i], ARMUtils.ADJUSTMENT_RESERVE_MASSUPDATE_HEADER[i]);
            }
        }
        isvValueChange = Boolean.FALSE;
        massValueDdlbRes.setValue(null);

    }

    /**
     * Configuring Result Table in Add/Edit Mode
     */
    private void configureTable() {
        LOGGER.debug(" Inside Configure table ");
        resultsTableLayoutRes.addComponent(resultsTable);
        resultsTableLayoutRes.addComponent(getResponsiveControls(detailsTableLogic.createControls()));
        detailsTableLogic.setContainerDataSource(detailsTableContainer);
        resultsTable.setSelectable(true);
        resultsTable.setMultiSelect(true);
        detailsTableLogic.setPageLength(NumericConstants.TEN);
        detailsTableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(ARMUtils.ADJUSTMENT_RESERVE_ADD_COLUMNS_RESERVE_DETAILS);
        resultsTable.setColumnHeaders(ARMUtils.ADJUSTMENT_RESERVE_ADD_HEADERS_RESERVE_DETAILS);
        resultsTable.setColumnAlignment(ARMUtils.ADJUSTMENT_RESERVE_ADD_COLUMNS_RESERVE_DETAILS[0], ExtCustomTable.Align.CENTER);
        resultsTable.setColumnWidth(ARMUtils.ADJUSTMENT_RESERVE_ADD_COLUMNS_RESERVE_DETAILS[0], NumericConstants.FIFTY);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setSizeFull();
        resultsTable.setImmediate(true);
        resultsTable.setPageLength(NumericConstants.TEN);

        resultsTable.setEditable(true);
        setTableFieldFactory();
        loadTablefirstTime();
        resultsTable.addStyleName(ARMUtils.FILTERCOMBOBOX);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.addStyleName(ARMUtils.CENTER_CHECK);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new ExtFilterGenerator() {
            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        ComboBox combo = (ComboBox) originatingField;
                        if (propertyId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.getConstant()) || propertyId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.getConstant())) {
                            String value = String.valueOf(combo.getValue().toString());
                            switch (value) {
                                case "0":
                                    value = "";
                                    break;
                                case "-1":
                                    value = "0";
                                    break;

                            }
                            return new SimpleStringFilter(propertyId, combo.getValue().toString().equals("0") ? StringUtils.EMPTY : value, false, false);
                        }
                        return new SimpleStringFilter(propertyId, combo.getValue().toString().equals("0") ? StringUtils.EMPTY : combo.getValue().toString(), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant().equals(propertyId) || ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.COMPANYNO.getConstant().equals(propertyId)
                        || ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.DIVISION.getConstant().equals(propertyId) || ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT.getConstant().equals(propertyId)) {
                    CustomTextField text = new CustomTextField();
                    text.setVisible(false);
                    text.setImmediate(true);
                    return text;
                }
                if (ARMUtils.getDropDownMap().containsKey(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    if (ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.getConstant().equals(String.valueOf(propertyId))) {
                        CommonUtils.loadComboBoxWithIntegerForComboBox(comboBox, "ARM_RES_ADJUSTMENT_LEVEL", Boolean.TRUE);
                    } else {
                        CommonUtils.loadComboBoxWithIntegerForComboBox(comboBox, ARMUtils.dropDownMap.get(propertyId.toString()), Boolean.TRUE);
                    }
                    return comboBox;
                } else {
                    CustomTextField text = new CustomTextField();
                    text.setImmediate(true);
                    return text;
                }
            }
        });
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        if (!selection.isIsViewMode()) {
            resultsTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), Boolean.TRUE);
        }
        resultsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = resultsTable.getItemIds();
                for (Object obj : itemList) {
                    AdjustmentReserveDTO dto = (AdjustmentReserveDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                    resultsTable.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                }
                logic.updateAllCheckRecord(event.isChecked(), selection);
            }
        });
        isTableLoaded = Boolean.TRUE;
    }

    /**
     * Configuring Mass Update
     */
    @UiHandler("massfieldDdlbRes")
    public void loadMassValue(Property.ValueChangeEvent event) {
        if ((!isvValueChange) && (massfieldDdlbRes.getValue() != null)) {
                String massValue = massfieldDdlbRes.getValue().toString().trim();
                if (ArrayUtils.contains(ARMUtils.ADJUSTMENT_RESERVE_COMBOBOX, massValue)) {
                    massValueDdlbRes.removeAllItems();
                    massValueDdlbRes.setVisible(true);
                    massValueRes.setVisible(false);
                    if (selection.isIsGTNDetails() && massValue.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.toString())) {
                        CommonUtils.loadComboBoxWithIntegerForComboBox(massValueDdlbRes, "ARM_GTN_ADJUSTMENT_LEVEL", Boolean.FALSE);
                    } else if (massValue.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.toString())) {
                        CommonUtils.loadComboBoxWithIntegerForComboBox(massValueDdlbRes, "ARM_RES_ADJUSTMENT_LEVEL", Boolean.FALSE);
                    } else {
                        CommonUtils.loadComboBoxWithIntegerForComboBox(massValueDdlbRes, ARMUtils.dropDownMap.get(massValue), Boolean.FALSE);
                    }
                } else {
                    massValueRes.setValue(StringUtils.EMPTY);
                    massValueDdlbRes.setVisible(false);
                    massValueRes.setVisible(true);
                }
        }
    }

    /**
     * Logic of adding the line in result list view.
     *
     * @param event
     * @throws com.vaadin.data.fieldgroup.FieldGroup.CommitException
     */
    @UiHandler("addLineBtnRes")
    public void addLineButtonLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        addLineBtnLogic();
    }

    /**
     * Logic of Remove line from the list view.
     *
     * @param event
     */
    @UiHandler("removeLineBtnRes")
    public void removeLineButtonLogic(Button.ClickEvent event) {
        final List<AdjustmentReserveDTO> list = detailsTableContainer.getItemIds();
        final List<AdjustmentReserveDTO> finalList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCheckRecord()) {
                finalList.add(list.get(i));
            }
        }
        if (finalList.isEmpty()) {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getRemoveLineMessageID001());
        } else {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        logic.deleteDataFromDB(selection);
                        for (int i = 0; i < finalList.size(); i++) {
                            detailsTableContainer.removeItem(finalList.get(i));
                        }
                        if (list.isEmpty()) {
                            resultsTable.setColumnCheckBox("checkRecord", true, false);
                        }
                        detailsTableLogic.loadsetData(true, selection);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }

                @Override
                public void noMethod() {
                }
            }.getConfirmationMessage("Confirmation", ARMMessages.getRemoveLineMessageID002());

        }
    }

    /**
     * Mass update populate button logic
     *
     * @param event
     */
    @UiHandler("populateBtn")
    public void populateBtnButtonLogic(Button.ClickEvent event) {
        if ((((0 == (int) companyDdlbRes.getValue()
                || 0 == (int) businessDdlbRes.getValue())
                || 0 == (int) deductionCategoryDdlbRes.getValue())
                || 0 == (int) deductionTypeDdlbRes.getValue())
                || 0 == (int) deductionProgramDdlbRes.getValue()) {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getPropertyMessage001());
        } else if (massfieldDdlbRes.getValue() != null) {
            LOGGER.debug("massValueDdlbRes.getValue() = " + massfieldDdlbRes.getValue());
            Object value = massValueDdlbRes.isVisible() ? massValueDdlbRes.getValue() : massValueRes.getValue();
            if (value != null) {
                List<AdjustmentReserveDTO> list = detailsTableContainer.getItemIds();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCheckRecord()) {
                        resultsTable.getContainerProperty(list.get(i), massfieldDdlbRes.getValue()).setValue(value);
                    }
                }
                if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.toString().equals(massfieldDdlbRes.getValue()) || ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.toString().equals(massfieldDdlbRes.getValue())) {
                    switch ((int) value) {
                        // value = 0 for positive and value = 1 for negative
                        case 0:
                            value = null;
                            break;
                        case -1:
                            value = 0;
                            break;
                        case 1:
                            value = 1;
                            break;
                    }
                }
                logic.massUpdateValue(value, selection, massfieldDdlbRes.getValue());
                detailsTableLogic.loadsetData(true, selection);
            } else {
                AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getPropertyMessage002());
            }
        } else {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getPropertyMessage002());
        }
    }

    /**
     * Logic copying the line.
     *
     * @param event
     * @throws CloneNotSupportedException
     */
    @UiHandler("copyLineBtnRes")
    public void copyLineBtnResButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException {
        List list = logic.getCheckedRecords(selection);
        int checkedRecords = list.size();
        if (checkedRecords == 0) {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getCopyLineMessageID001());
        } else {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    logic.copyLineInDB(selection);
                    detailsTableLogic.loadsetData(true, selection);
                }

                @Override
                public void noMethod() {
                }

            }.getConfirmationMessage("Confirmation", ARMMessages.getCopyLineMessageID002());
        }
    }

    /**
     * Logic of saving the reserve account details.
     *
     * @param event
     */
    @UiHandler("saveBtnRes")
    public void saveButtonLogic(Button.ClickEvent event) {
        try{
        if (saveToMaster()) {
            if (selection.getReserveMasterSid() != 0 || selection.getGtnDetailsMasterSid() != 0) {
                if (logic.madatoryCheckReserveANDDetails(selection, "Mandatory check for Reserve", "Mandatory check for GTNDetails")) {
                    if (logic.madatoryCheckCreditDebit(selection, "CrediDebit_Validation")) { // GAL-8033
                        if (!(logic.equalCheckCreditDebit(selection, "CrediDebit_Same"))) { // GAL-8148
                            if (logic.duplicateCheckReserveANDDetails(selection, "Check_For_Save_validation")) {

                                new AbstractNotificationUtils() {
                                    @Override
                                    public void yesMethod() {
                                        try {
                                            if (selection.isIsSaved()) {
                                                logic.tempToMainUpdateLogic(selection);
                                                logic.masterUpdateModifiedDate(selection);
                                            } else {
                                                if (selection.getReserveMasterSid() == 0) {
                                                    int id = logic.addLineForMaster(selection, 0);
                                                    selection.setReserveMasterSid(id);
                                                } else if (selection.getGtnDetailsMasterSid() == 0) {
                                                    int id = logic.addLineForMaster(selection, 1);
                                                    selection.setGtnDetailsMasterSid(id);
                                                }

                                                logic.tempToMainSaveLogic(selection, binderDto);
                                            }
                                            selection.setIsSaved(Boolean.TRUE);
                                            final Notification notif = new Notification("Adjustment & Reserve Configuration has been successfully saved", Notification.Type.HUMANIZED_MESSAGE);
                                            notif.setPosition(Position.MIDDLE_CENTER);
                                            notif.show(Page.getCurrent());
                                        } catch (Exception ex) {
                                            LOGGER.error(ex.getMessage());
                                        }
                                    }

                                    @Override
                                    public void noMethod() {
                                    }
                                }.getConfirmationMessage("Confirmation", "Are you sure you want to save this Adjustment & Reserve Configuration?");

                            } else {
                                AbstractNotificationUtils.getErrorNotification("Error", "Please ensure that each unique combination of Business Process, Account Type, Company, Division, Deduction Category, Deduction Type, and Deduction Sub-Type is only in the list view once.");
                            }

                        } else {
                            AbstractNotificationUtils.getErrorNotification("Error", "The Credit and Debit Indicators cannot have the same value");
                        }

                    } else {
                        AbstractNotificationUtils.getErrorNotification("Error", "The Credit and Debit Indicators must be populated with a value");
                    }
                } else {
                    AbstractNotificationUtils.getErrorNotification("Error", "Please ensure that all mandatory fields are populated. ");
                }
            } else {
                AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSaveMessageID003());
            }
        }
        }catch(Exception ex){
           LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Excel export button logic.
     *
     * @param event
     */
    @UiHandler("exportBtnRes")
    public void exportButtonLogic(Button.ClickEvent event) throws PortalException, SystemException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        createWorkSheet("Adjustment Reserve", resultsTable);
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        long recordCount = 0;
        if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
            selection.setIsGTNDetails(Boolean.FALSE);
            selection.setMasterSID(selection.getReserveMasterSid());
        } else {
            selection.setMasterSID(selection.getGtnDetailsMasterSid());
            selection.setIsGTNDetails(Boolean.TRUE);
        }
        List<String> visibleList = Arrays.asList(resultsTable.getColumnHeaders()).subList(1, resultsTable.getVisibleColumns().length);
        if (resultTable.size() != 0) {
            recordCount = logic.getReserveEditCount(selection, detailsTableLogic.getFilters());
        }
        ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.toUpperCase());
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException {

        List visibleList = new ArrayList();
        if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
            visibleList = Arrays.asList(ARMUtils.ADJUSTMENT_RESERVE_ADD_COLUMNS_RESERVE_DETAILS_FOR_EXCEL);
        } else {
            visibleList = Arrays.asList(ARMUtils.ADJUSTMENT_RESERVE_ADD_COLUMNS_GTN_DETAILS_FOR_EXCEL);
        }
        try {
            if (end != 0) {
                final List<AdjustmentReserveDTO> searchList = logic.getReserveData(selection, start, end, detailsTableLogic.getFilters(), detailsTableLogic.getSortByColumns());
                ExcelExportforBB.createFileContent(visibleList.toArray(), searchList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * This Method is used to do the configuration type change logic
     *
     * @param event
     * @throws SystemException
     */
    @UiHandler("configurationTypeOpgRes")
    public void configurationTypeLogic(Property.ValueChangeEvent event) throws SystemException {
        if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
            resultsTable.setVisibleColumns(ARMUtils.ADJUSTMENT_RESERVE_ADD_COLUMNS_RESERVE_DETAILS);
            resultsTable.setColumnHeaders(ARMUtils.ADJUSTMENT_RESERVE_ADD_HEADERS_RESERVE_DETAILS);
            selection.setIsGTNDetails(Boolean.FALSE);
            selection.setMasterSID(selection.getReserveMasterSid());
        } else {
            resultsTable.setVisibleColumns(ARMUtils.ADJUSTMENT_RESERVE_ADD_COLUMNS_GTN_DETAILS);
            resultsTable.setColumnHeaders(ARMUtils.ADJUSTMENT_RESERVE_ADD_HEADERS_GTN_DETAILS);
            selection.setMasterSID(selection.getGtnDetailsMasterSid());
            selection.setIsGTNDetails(Boolean.TRUE);
        }
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(new ExtFilterGenerator() {
            @Override
            public Container.Filter generateFilter(Object propertyId, Object value) {
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                if (originatingField instanceof ComboBox) {
                    if (originatingField.getValue() != null) {
                        ComboBox combo = (ComboBox) originatingField;
                        if (propertyId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.getConstant()) || propertyId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.getConstant())) {
                            String value = String.valueOf(combo.getValue().toString());
                            switch (value) {
                                case "0":
                                    value = "";
                                    break;
                                case "-1":
                                    value = "0";
                                    break;

                            } 
                            return new SimpleStringFilter(propertyId, combo.getValue().toString().equals("0") ? StringUtils.EMPTY : value, false, false);
                        }
                        return new SimpleStringFilter(propertyId, combo.getValue().toString().equals("0") ? StringUtils.EMPTY : combo.getValue().toString(), false, false);
                    } else {
                        return null;
                    }
                }
                return null;
            }

            @Override
            public void filterRemoved(Object propertyId) {
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object propertyId) {
                 if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant().equals(propertyId)) {
                    CustomTextField text = new CustomTextField();
                    text.setVisible(false);
                    text.setImmediate(true);
                    return text;
                }
                if (ARMUtils.getDropDownMap().containsKey(propertyId)) {
                    ComboBox comboBox = new ComboBox();
                    if (ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.getConstant().equals(String.valueOf(propertyId))) {
                        if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
                            CommonUtils.loadComboBoxWithIntegerForComboBox(comboBox, "ARM_RES_ADJUSTMENT_LEVEL", Boolean.TRUE);
                        } else {
                            CommonUtils.loadComboBoxWithIntegerForComboBox(comboBox, "ARM_GTN_ADJUSTMENT_LEVEL", Boolean.TRUE);
                        }
                    } else {
                        CommonUtils.loadComboBoxWithIntegerForComboBox(comboBox, ARMUtils.dropDownMap.get(propertyId.toString()), Boolean.TRUE);
                    }
                    return comboBox;
                } else {
                    CustomTextField text = new CustomTextField();
                    text.setImmediate(true);
                    return text;
                }
            }
        });
        loadMassfield();
        LOGGER.debug("selection.getMasterSID()-->>>" + selection.getMasterSID());
        detailsTableLogic.loadsetData(Boolean.TRUE, selection);
        List<AdjustmentReserveDTO> list = detailsTableContainer.getItemIds();
        boolean check = Boolean.FALSE;
        for (AdjustmentReserveDTO dto : list) {
            check = dto.getCheckRecord();
            if (!dto.getCheckRecord()) {
                break;
            }
        }
        resultsTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), Boolean.TRUE, check);
    }

    /**
     * Reseting the Results list view (reseting the Table)
     *
     * @param event
     */
    @UiHandler("resetLineBtnRes")
    public void resetLineBtnResLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    loadResetData();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Confirmation", ARMMessages.getResetMessage());
    }

    @UiHandler("viewOpgRes")
    public void viewModeChange(Property.ValueChangeEvent event) throws SystemException {
        if (isTableLoaded) {
            if (viewOpgRes.getValue().equals(GlobalConstants.getCurrent())) {
                selection.setIsCurrent(Boolean.TRUE);
            } else {
                selection.setIsCurrent(Boolean.FALSE);
            }
            detailsTableLogic.loadsetData(Boolean.TRUE, selection);
            populateBtn.setEnabled(selection.isIsCurrent());
            massValueDdlbRes.setEnabled(selection.isIsCurrent());
            massValueRes.setEnabled(selection.isIsCurrent());
            massfieldDdlbRes.setEnabled(selection.isIsCurrent());
            resultsTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), selection.isIsCurrent());
            resetLineBtnRes.setEnabled(selection.isIsCurrent());
            if (selection.isIsViewMode()) {
                massValueDdlbRes.setReadOnly(selection.isIsCurrent());
                massValueRes.setReadOnly(selection.isIsCurrent());
                massfieldDdlbRes.setReadOnly(selection.isIsCurrent());
                populateBtn.setEnabled(false);
                resetLineBtnRes.setEnabled(false);
            }
        }

    }

    public void setTableFieldFactory() {
        ReserveFieldFactory fieldFactory = new ReserveFieldFactory(resultsTable, selection);
        resultsTable.setTableFieldFactory(fieldFactory);
    }

    /**
     * This method will return the company No based on the selected company from
     * company drop down.
     *
     * @return
     */
    protected String getCompanyNo(int sysid) {
        List input = new ArrayList<>();
        input.add(sysid);
        List dataList = QueryUtils.getItemData(input, "Load_Company_No", null);
        if (dataList.size() < 1) {
            return StringUtils.EMPTY;
        }
        return String.valueOf(dataList.get(0));
    }

    public void closeBtnLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                logic.deleteTempTableData(selection);
                close();
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Confirmation", ARMMessages.getCloseMessageID001());
    }

    protected abstract void getMasterSids();

    /**
     * This method will configure the binder for this page.
     *
     * @return
     */
    protected abstract CustomFieldGroup getBinder();

    protected abstract void loadSelection();

    protected abstract void addLineBtnLogic();

    /**
     * This Method is Used to Save thee data to master table.
     *
     * @return
     */
    protected abstract boolean saveToMaster();

    protected abstract void loadTablefirstTime();

    /**
     * This method is used to reset the Table irrespective of all the modes.
     */
    protected abstract void loadResetData();
}
