/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.abstractreserveform;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.arm.excecutors.SaveAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.saveaction.SaveAdjustmentSummaryAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.saveaction.SaveBalanceSummaryAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.saveaction.SaveBalanceSummaryInsertAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.saveaction.SaveConfigurationDetailsAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.saveaction.SaveConfigureDetailsToAdjustmentSummaryAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentAndBalSummaryTableGenerator;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentReserveLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentSummaryConfigLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.BalanceSummaryLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.ExcelExportLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.tablelogic.AdjustmentReserveTableLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.tablelogic.AdjustmentSummaryTableLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.tablelogic.BalSummaryConfigurationTableLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.ui.fieldfactory.AdjustmentSummaryFieldFactory;
import com.stpl.app.arm.adjustmentreserveconfiguration.ui.fieldfactory.BalanceSummaryFieldFactory;
import com.stpl.app.arm.adjustmentreserveconfiguration.ui.form.ReserveFieldFactory;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.MandatoryValidationForCreditAndDebit;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.MandatoryValidationForReserveAndGTN;
import com.stpl.app.arm.excecutors.Validation;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.ValidationAddRemoveLine;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.ValidationForAdjustmentTypeOfGTNAvailableInReserveDetails;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.ValidationForAtleastOneRecordToSave;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.ValidationForDuplicateAccounts;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.ValidationForLimitedRowInDetails;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.ValidationForNotMatchedAccountsOfGTNReserve;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.ValidationForParentRecordRemoveLine;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.ValidationForSameCreditDebitOfAdjustmentType;
import com.stpl.app.arm.adjustmentreserveconfiguration.validation.ValidationForSameReportIndicator;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.excecutors.ActionExecutor;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.HeaderUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtCustomTable;

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
     * â€œCOMPANY ID â€“ COMPANY NAMEâ€�.
     */
    @UiField("companyDdlbRes")
    protected ComboBox companyDdlbRes;
    /**
     * The Business Combo box where Options will be any Company Master record
     * where Company Type = Business Unit.The DDLB needs to show the combination
     * of â€œCOMPANY ID â€“ COMPANY NAMEâ€�.
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
     * â€œDeduction Categoryâ€� DDLB.
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
     * â€œDeduction Categoryâ€� DDLB.
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

    @UiField("methodologyDdlb")
    protected ComboBox methodologyDdlb;
    @UiField("reportTypeDdlb")
    protected ComboBox reportTypeDdlb;

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

    /* The Search Form Layout which will be disabled in Add/Edit/View Modes
     */
    @UiField("mainVerticalLayout")
    protected VerticalLayout mainVerticalLayout;

    @UiField("configurationDetailsPanel")
    protected Panel configurationDetailsPanel;

    @UiField("adjSummaryPanel")
    protected Panel adjSummaryPanel;

    @UiField("balSummaryPanel")
    protected Panel balSummaryPanel;

    /* The Search Form Layout which will be disabled in Add/Edit/View Modes
     */
    @UiField("adjustmentSummaryConfigurationLayout")
    protected VerticalLayout adjustmentSummaryConfigurationLayout;
    /* The Search Form Layout which will be disabled in Add/Edit/View Modes
     */
    @UiField("balanceSummaryConfigurationLayout")
    protected VerticalLayout balanceSummaryLayout;
    @UiField("adjSummaryTableLayout")
    protected VerticalLayout adjSummaryTableLayout;
    @UiField("balsummaryTableLayout")
    protected VerticalLayout balsummaryTableLayout;
    /* The Search Form Layout which will be disabled in Add/Edit/View Modes
     */
    @UiField("tabSheetLayout")
    protected VerticalLayout tabSheetLayout;
    private TabSheet tabSheet;
    private static final String ONE = "1";
    private static final String ZERO = "0";

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
     * The Table Logic For Adjustment Reserve Configuration in Add/Edit Mode
     */
    protected AdjustmentSummaryTableLogic adjustmentSummaryTableLogic = new AdjustmentSummaryTableLogic();
    /**
     * The Results Table
     */
    protected ExtPagedTable adjustmentSummaryTable = new ExtPagedTable(adjustmentSummaryTableLogic);
    /**
     * The Table Logic For Balance Summary Configuration in Add/Edit Mode
     */
    protected BalSummaryConfigurationTableLogic balSummaryConfigurationTableLogic = new BalSummaryConfigurationTableLogic();
    /**
     * The Results Table
     */
    protected ExtPagedTable balanceSummaryTable = new ExtPagedTable(balSummaryConfigurationTableLogic);
    protected final BeanItemContainer<AdjustmentReserveDTO> balanceSummaryContainer = new BeanItemContainer<>(AdjustmentReserveDTO.class);

    /**
     * The Available Results Container
     */
    protected final BeanItemContainer<AdjustmentReserveDTO> adjustmentSummaryContainer = new BeanItemContainer<>(AdjustmentReserveDTO.class);
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
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractReserve.class);
    /**
     * Binderdto is datasource for the binder.
     */
    protected AdjustmentReserveDTO binderDto = new AdjustmentReserveDTO();
    /**
     * binder used to bind the fields from the page
     */
    protected CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(binderDto));
    /**
     * This is used to find the value change of mass update done or not
     */
    private boolean isvValueChange;
    /**
     * This is used to find the value change of mass update done or not
     */
    private boolean isTableLoaded;
    /**
     * This is used to check wheather the values of dataselection is changed or
     * not.
     */
    protected AdjustmentSummaryConfigLogic adjustmentSummaryConfigLogic = AdjustmentSummaryConfigLogic.getInstance();

    protected BalanceSummaryLogic balanceSummaryLogic = BalanceSummaryLogic.getInstance();
    /*
    Excel Logic
     */
    private ExcelExportLogic excelExportLogic = new ExcelExportLogic();

    private int tabPosition = 0;

    private int lastPosition = 0;

    public AbstractReserve(String caption, SessionDTO sessionDTO, ReserveSelection selection) {
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
        viewOpgRes.addItem(GlobalConstants.getCurrent());
        viewOpgRes.addItem(GlobalConstants.getHistory());
        viewOpgRes.select(GlobalConstants.getCurrent());
        loadMassfield();
        massValueDdlbRes.setVisible(false);
        massValueDdlbRes.setImmediate(true);
        selection.setIsCurrent(true);
        configureDetailsTable();
        configureAdjustmentSummaryTable();
        configureBalanceSummaryTable();
        configurationTypeOpgRes.addItem(ARMConstants.getReserveDetails());
        configurationTypeOpgRes.addItem(ARMConstants.getGTNDetails());
        configurationTypeOpgRes.select(ARMConstants.getReserveDetails());
        CommonUtils.loadComboBoxWithIntegerForComboBox(methodologyDdlb, "ARM_TRX_METHDOLOGY", false);
        CommonUtils.loadComboBoxWithIntegerForComboBox(reportTypeDdlb, "ARM_REPORT_TYPE", false);
        loadComboBox();
        getBinder();
        searchBtnRes.setVisible(false);
        exportBtnRes.setPrimaryStyleName("link");
        exportBtnRes.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        configureTab();

    }

    protected void loadComboBox() {
        CommonLogic.configureDropDowns(companyDdlbRes, "getCompanyQuery", true);
        companyDdlbRes.focus();
        CommonLogic.configureDropDowns(businessDdlbRes, "getBusinessQuery", true);
        CommonLogic.configureDropDownsForDeduction(deductionCategoryDdlbRes, "getDeductionCategory");
        CommonLogic.configureDropDownsForDeduction(deductionTypeDdlbRes, "getDeductionType");
        CommonLogic.configureDropDownsForDeduction(deductionProgramDdlbRes, "getDeductionProgram");
        CommonUtils.loadComboBoxWithIntegerForComboBox(methodologyDdlb, "ARM_TRX_METHDOLOGY", false);

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
        isvValueChange = true;
        massValueDdlbRes.removeAllItems();
        massfieldDdlbRes.removeAllItems();
        massValueRes.setValue(StringUtils.EMPTY);
        massfieldDdlbRes.setImmediate(true);
        massfieldDdlbRes.addItem(GlobalConstants.getSelectOne());
        massfieldDdlbRes.setNullSelectionAllowed(true);
        massfieldDdlbRes.setNullSelectionItemId(GlobalConstants.getSelectOne());

        if (selection.isIsGTNDetails()) {
            for (int i = 0; i < ARMUtils.getAdjustmentGtnDetailsColumns().length; i++) {
                massfieldDdlbRes.addItem(ARMUtils.getAdjustmentGtnDetailsColumns()[i]);
                massfieldDdlbRes.setItemCaption(ARMUtils.getAdjustmentGtnDetailsColumns()[i], ARMUtils.getAdjustmentGtnDetailsHeader()[i]);
            }
        } else {
            for (int i = 0; i < ARMUtils.getAdjustmentReserveMassupdateColumn().length; i++) {
                massfieldDdlbRes.addItem(ARMUtils.getAdjustmentReserveMassupdateColumn()[i]);
                massfieldDdlbRes.setItemCaption(ARMUtils.getAdjustmentReserveMassupdateColumn()[i], ARMUtils.getAdjustmentReserveMassupdateHeader()[i]);
            }
        }
        isvValueChange = false;
        massValueDdlbRes.setValue(null);

    }

    /**
     * Configuring Result Table in Add/Edit Mode
     */
    private void configureDetailsTable() {
        LOGGER.debug(CommonConstant.INSIDE_CONFIGURE_TABLE);
        resultsTableLayoutRes.addComponent(resultsTable);
        resultsTableLayoutRes.addComponent(getResponsiveControls(detailsTableLogic.createControls()));
        detailsTableLogic.setContainerDataSource(detailsTableContainer);
        resultsTable.setMultiSelect(true);
        detailsTableLogic.setPageLength(NumericConstants.TEN);
        detailsTableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(ARMUtils.getAdjustmentReserveAddColumnsReserveDetails());
        resultsTable.setColumnHeaders(ARMUtils.getAdjustmentReserveAddHeadersReserveDetails());
        resultsTable.setColumnAlignment(ARMUtils.getAdjustmentReserveAddColumnsReserveDetails()[0], ExtCustomTable.Align.CENTER);
        resultsTable.setColumnWidth(ARMUtils.getAdjustmentReserveAddColumnsReserveDetails()[0], NumericConstants.FIFTY);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setSizeFull();
        resultsTable.setImmediate(true);
        resultsTable.setPageLength(NumericConstants.TEN);

        resultsTable.setEditable(true);
        setTableFieldFactory();
        loadTablefirstTime();
        resultsTable.addStyleName(ARMUtils.FILTERCOMBOBOX);
        resultsTable.addStyleName(CommonConstant.TABLEHEADERNORMAL);
        resultsTable.addStyleName(ARMUtils.CENTER_CHECK);
        resultsTable.setSelectable(!selection.isIsViewMode());
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(resultsFilters);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        if (!selection.isIsViewMode()) {
            resultsTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true);
        }
        resultsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
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
        isTableLoaded = true;
    }

    /**
     * Configuring Result Table in Add/Edit Mode
     */
    private void configureAdjustmentSummaryTable() {
        LOGGER.debug(CommonConstant.INSIDE_CONFIGURE_TABLE);
        adjSummaryTableLayout.addComponent(adjustmentSummaryTable);
        adjSummaryTableLayout.addComponent(getResponsiveControls(adjustmentSummaryTableLogic.createControls()));
        adjustmentSummaryTableLogic.setContainerDataSource(adjustmentSummaryContainer);
        adjustmentSummaryTable.setSelectable(false);
        adjustmentSummaryTable.setMultiSelect(true);
        adjustmentSummaryTableLogic.setPageLength(NumericConstants.TEN);
        adjustmentSummaryTableLogic.sinkItemPerPageWithPageLength(false);
        adjustmentSummaryTable.setVisibleColumns(ARMUtils.getAdjustmentReserveTx1Column());
        adjustmentSummaryTable.setColumnHeaders(ARMUtils.getAdjustmentReserveTx1Header());
        adjustmentSummaryTable.setFilterBarVisible(true);
        adjustmentSummaryTable.setImmediate(true);
        adjustmentSummaryTable.setPageLength(NumericConstants.TEN);
        adjustmentSummaryTable.setEditable(true);
        setSummaryFieldFactory();
        adjustmentSummaryTable.setColumnWidth(VariableConstants.CHECK_RECORD, NumericConstants.FIFTY);
        adjustmentSummaryTable.addStyleName(ARMUtils.FILTERCOMBOBOX);
        adjustmentSummaryTable.addStyleName(CommonConstant.TABLEHEADERNORMAL);
        adjustmentSummaryTable.addStyleName(ARMUtils.CENTER_CHECK);
        adjustmentSummaryTable.setFilterDecorator(new ExtDemoFilterDecorator());
        adjustmentSummaryTable.setFilterGenerator(new AdjustmentAndBalSummaryTableGenerator(selection));
        adjustmentSummaryTable.setFilterDecorator(new ExtDemoFilterDecorator());
        adjustmentSummaryTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), !selection.isIsViewMode() ? Boolean.TRUE : Boolean.FALSE);

        setWidth(adjustmentSummaryTable.getVisibleColumns());
        if (!selection.isIsViewMode()) {
            adjustmentSummaryTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true);
            adjustmentSummaryTable.setSelectable(true);
        }
        adjustmentSummaryTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = adjustmentSummaryTable.getItemIds();
                for (Object obj : itemList) {
                    AdjustmentReserveDTO dto = (AdjustmentReserveDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                    adjustmentSummaryTable.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                }
                adjustmentSummaryConfigLogic.updateAllCheckRecord(event.isChecked(), selection);
            }
        });
    }

    /**
     *
     * configureBalanceSummaryTable
     */
    private void configureBalanceSummaryTable() {
        LOGGER.debug(CommonConstant.INSIDE_CONFIGURE_TABLE);
        balsummaryTableLayout.addComponent(balanceSummaryTable);
        balsummaryTableLayout.addComponent(getResponsiveControls(balSummaryConfigurationTableLogic.createControls()));
        balSummaryConfigurationTableLogic.setContainerDataSource(balanceSummaryContainer);
        balanceSummaryTable.setSelectable(false);
        balanceSummaryTable.setMultiSelect(true);
        balSummaryConfigurationTableLogic.setPageLength(NumericConstants.TEN);
        balSummaryConfigurationTableLogic.sinkItemPerPageWithPageLength(false);

        balanceSummaryTable.setVisibleColumns(ARMUtils.getReportTypePipelineSingleVisibleColumn());
        balanceSummaryTable.setColumnHeaders(ARMUtils.getReportTypePipelineSingleHeaderColumn());
        balanceSummaryTable.setDoubleHeaderVisible(true);
        balanceSummaryTable.setDoubleHeaderVisibleColumns(ARMUtils.getReportTypePipelineDoubleVisibleColumn());
        balanceSummaryTable.setDoubleHeaderColumnHeaders(ARMUtils.getReportTypePipelineDoubleHeaderColumn());
        balanceSummaryTable.setDoubleHeaderMap(HeaderUtils.confDoubleHeaderMapPipelineReportType());

        balanceSummaryTable.setFilterBarVisible(true);
        balanceSummaryTable.setImmediate(true);
        balanceSummaryTable.setPageLength(NumericConstants.TEN);
        balanceSummaryTable.setEditable(true);
        balanceSummaryTable.setTableFieldFactory(new BalanceSummaryFieldFactory(balanceSummaryTable, selection));
        balanceSummaryTable.addStyleName(ARMUtils.FILTERCOMBOBOX);
        balanceSummaryTable.addStyleName(CommonConstant.TABLEHEADERNORMAL);
        balanceSummaryTable.addStyleName(ARMUtils.CENTER_CHECK);
        balanceSummaryTable.setColumnWidth(VariableConstants.CHECK_RECORD, NumericConstants.FIFTY);

        balanceSummaryTable.setFilterDecorator(new ExtDemoFilterDecorator());
        balanceSummaryTable.setFilterGenerator(new AdjustmentAndBalSummaryTableGenerator(selection));
        balanceSummaryTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), !selection.isIsViewMode() ? Boolean.TRUE : Boolean.FALSE);
        for (Object columnName : ARMUtils.getReportTypeSinglevisibleColumnInReserve()) {
            if (!columnName.equals(VariableConstants.CHECK_RECORD)) {
                balanceSummaryTable.setColumnWidth(columnName, NumericConstants.ONE_EIGHT_ZERO);
            }
        }
        if (!selection.isIsViewMode()) {
            balanceSummaryTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true);
            balanceSummaryTable.setSelectable(true);
        }
        balanceSummaryTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = balanceSummaryTable.getItemIds();
                for (Object obj : itemList) {
                    AdjustmentReserveDTO dto = (AdjustmentReserveDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                    balanceSummaryTable.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                }
                adjustmentSummaryConfigLogic.updateAllCheckRecord(event.isChecked(), selection);
            }
        });
    }

    @UiHandler("reportTypeDdlb")
    public void loadReportTypeDdlbValue(Property.ValueChangeEvent event) {
        selection.setReportType((Integer) (reportTypeDdlb.getValue()));
        balSummaryConfigurationTableLogic.getFilters().clear();
        if ((reportTypeDdlb.getValue() != null) && ARMUtils.getIntegerValue(String.valueOf(reportTypeDdlb.getValue())) != 0) {
            LOGGER.debug(event.toString());
            try {
                Map<String, List<Object>> tableHeaderMap;
                String reportType = reportTypeDdlb.getItemCaption(reportTypeDdlb.getValue());
                tableHeaderMap = HeaderUtils.configureBalanceSummaryTableheader(reportType);
                if (tableHeaderMap.containsKey(reportType)) {
                    List<Object> list = tableHeaderMap.get(reportType);
                    balanceSummaryTable.setVisibleColumns((Object[]) list.get(0));
                    balanceSummaryTable.setColumnHeaders((String[]) list.get(1));
                    balanceSummaryTable.setDoubleHeaderVisible(true);
                    balanceSummaryTable.setDoubleHeaderVisibleColumns((Object[]) list.get(2));
                    balanceSummaryTable.setDoubleHeaderColumnHeaders((String[]) list.get(3));
                    balanceSummaryTable.setDoubleHeaderMap((Map<Object, Object[]>) list.get(4));
                    balanceSummaryTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    balanceSummaryTable.setFilterGenerator(new AdjustmentAndBalSummaryTableGenerator(selection));
                }
                //Calling DB insertion Action Call

                ActionExecutor executor = new ActionExecutor();
                executor.callingActionExecution(new SaveBalanceSummaryInsertAction(selection));

                balSummaryConfigurationTableLogic.loadSetData(true, selection);

            } catch (Exception e) {
                LOGGER.error("Error in loadReportTypeDdlbValue :", e);
            }
        } else {
            balanceSummaryTable.setFilterDecorator(new ExtDemoFilterDecorator());
            balanceSummaryTable.setFilterGenerator(new AdjustmentAndBalSummaryTableGenerator(selection));
            balSummaryConfigurationTableLogic.loadSetData(false, selection);
        }
    }

    /**
     * Configuring Mass Update
     */
    @UiHandler("massfieldDdlbRes")
    public void loadMassValue(Property.ValueChangeEvent event) {
        LOGGER.debug(event.toString());
        if ((!isvValueChange) && (massfieldDdlbRes.getValue() != null)) {
            String massValue = massfieldDdlbRes.getValue().toString().trim();
            if (ArrayUtils.contains(ARMUtils.getAdjustmentReserveCombobox(), massValue)) {
                massValueDdlbRes.removeAllItems();
                massValueDdlbRes.setVisible(true);
                massValueRes.setVisible(false);
                if (selection.isIsGTNDetails() && massValue.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.toString())) {
                    CommonUtils.loadComboBoxWithIntegerForComboBox(massValueDdlbRes, "ARM_GTN_ADJUSTMENT_LEVEL", false);
                } else if (massValue.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_LEVEL.toString())) {
                    CommonUtils.loadComboBoxWithIntegerForComboBox(massValueDdlbRes, CommonConstant.ARM_RES_ADJUSTMENT_LEVEL, false);
                } else {
                    CommonUtils.loadComboBoxWithIntegerForComboBox(massValueDdlbRes, ARMUtils.getDropDownMap().get(massValue), false);
                }
            } else {
                massValueRes.setValue(StringUtils.EMPTY);
                massValueDdlbRes.setVisible(false);
                massValueRes.setVisible(true);
            }
        }
    }

    @UiHandler("methodologyDdlb")
    public void loadmethodologyDdlb(Property.ValueChangeEvent event) throws Exception {
        LOGGER.debug(event.toString());
        selection.setMethodology((Integer) (methodologyDdlb.getValue()));
        adjustmentSummaryTableLogic.getFilters().clear();
        if ((methodologyDdlb.getValue() != null) && ARMUtils.getIntegerValue(String.valueOf(methodologyDdlb.getValue())) != 0) {
            String transaction = methodologyDdlb.getItemCaption(methodologyDdlb.getValue());
            selection.setMethodologyDescription(transaction);
            if (transaction.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.TRANSACTION_1.toString())) {
                adjustmentSummaryTable.setVisibleColumns(ARMUtils.getAdjustmentReserveTx1Column());
                adjustmentSummaryTable.setColumnHeaders(ARMUtils.getAdjustmentReserveTx1Header());
            } else if (transaction.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.TRANSACTION_3.toString())) {
                adjustmentSummaryTable.setVisibleColumns(ARMUtils.getAdjustmentReserveTx3Column());
                adjustmentSummaryTable.setColumnHeaders(ARMUtils.getAdjustmentReserveTx3Header());

            } else if (transaction.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.TRANSACTION_2.toString()) || transaction.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.TRANSACTION_5.toString())) {
                adjustmentSummaryTable.setVisibleColumns(ARMUtils.getAdjustmentReserveTx2Column());
                adjustmentSummaryTable.setColumnHeaders(ARMUtils.getAdjustmentReserveTx2Header());

            } else if (transaction.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.TRANSACTION_4.toString())) {

                adjustmentSummaryTable.setVisibleColumns(ARMUtils.getAdjustmentReserveTx4Column());
                adjustmentSummaryTable.setColumnHeaders(ARMUtils.getAdjustmentReserveTx4Header());

            } else if (transaction.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.TRANSACTION_6.toString()) || transaction.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.TRANSACTION_7.toString()) || transaction.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.TRANSACTION_8.toString())) {

                adjustmentSummaryTable.setVisibleColumns(ARMUtils.getAdjustmentReserveTxColumn());
                adjustmentSummaryTable.setColumnHeaders(ARMUtils.getAdjustmentReserveTxHeader());

            }

            ActionExecutor executor = new ActionExecutor();
            executor.callingActionExecution(new SaveConfigureDetailsToAdjustmentSummaryAction(selection));

            adjustmentSummaryTableLogic.loadSetData(true, selection);
        } else {
            adjustmentSummaryTable.setVisibleColumns(ARMUtils.getAdjustmentReserveTx1Column());
            adjustmentSummaryTable.setColumnHeaders(ARMUtils.getAdjustmentReserveTx1Header());
            adjustmentSummaryTableLogic.loadSetData(false, selection);
        }
        setWidth(adjustmentSummaryTable.getVisibleColumns());
        adjustmentSummaryTable.setFilterGenerator(new AdjustmentAndBalSummaryTableGenerator(selection));
        adjustmentSummaryTable.setFilterDecorator(new ExtDemoFilterDecorator());

    }

    public void setWidth(Object[] visibleColumns) {
        if (visibleColumns.length < 6) {
            for (Object columnName : ARMUtils.getAdjustmentReserveColumnVar()) {
                if (!columnName.equals(VariableConstants.CHECK_RECORD)) {
                    adjustmentSummaryTable.setColumnWidth(columnName, NumericConstants.TWO_NINE_FIVE);
                }
            }
        } else {
            for (Object columnName : ARMUtils.getAdjustmentReserveColumnVar()) {
                if (!columnName.equals(VariableConstants.CHECK_RECORD)) {
                    adjustmentSummaryTable.setColumnWidth(columnName, NumericConstants.TWO_ONE_ZERO);
                }
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
    public void addLineButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        switch (tabPosition) {
            case 0:
                configureTabAddLineLogic();
                break;
            case 1:
                adjustmentSummaryAddLineLogic();
                break;
            case 2:
                balanceSummaryAddLineLogic();
                break;
            default:
                break;
        }
    }

    /**
     * Logic of Remove line from the list view.
     *
     * @param event
     */
    @UiHandler("removeLineBtnRes")
    public void removeLineButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        switch (tabPosition) {
            case 0:
                configureTabRemoveLineLogic();
                break;
            case 1:
                adjustmentSummaryRemoveLineLogic();
                break;
            case 2:
                balanceSummaryRemoveLineLogic();
                break;
            default:
                break;
        }

    }

    /**
     * This is to remove the selected line from UI and DB when the tab is
     * selected at adjustmentConfiguration tab
     *
     */
    private void configureTabRemoveLineLogic() {
        final List<AdjustmentReserveDTO> list = detailsTableContainer.getItemIds();
        final List<AdjustmentReserveDTO> finalList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCheckRecord()) {
                finalList.add(list.get(i));
            }
        }
        if (finalList.isEmpty()) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getRemoveLineMessageID001());
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
                        LOGGER.error("Error in configureTabRemoveLineLogic :", ex);
                    }
                }

                @Override
                public void noMethod() {
                    LOGGER.debug(CommonConstant.INSIDE_NO_METHOD);
                }
            }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getRemoveLineMessageID002());

        }
    }

    /**
     * This is to remove the selected line from UI and DB when the tab is
     * selected at adjustmentConfiguration tab
     *
     */
    private void adjustmentSummaryRemoveLineLogic() {
        List<Validation> removeLineValidationAdjSum = new ArrayList<>(2);
        removeLineValidationAdjSum.add(new ValidationAddRemoveLine(selection, false));
        removeLineValidationAdjSum.add(new ValidationForParentRecordRemoveLine(selection));

        for (Validation summaryValidation : removeLineValidationAdjSum) {
            if (!summaryValidation.doValidate()) {
                AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, summaryValidation.validationMessage());
                return;
            }
        }
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    adjustmentSummaryConfigLogic.deleteDataFromDB(selection);
                    adjustmentSummaryTableLogic.loadSetData(true, selection);
                } catch (Exception ex) {
                    LOGGER.error("Error in adjustmentSummaryRemoveLineLogic :", ex);
                }
            }

            @Override
            public void noMethod() {
                LOGGER.debug(CommonConstant.INSIDE_NO_METHOD);
            }
        }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getRemoveLineMessageID002());
    }

    /**
     * This is to remove the selected line from UI and DB when the tab is
     * selected at adjustmentConfiguration tab
     *
     */
    private void balanceSummaryRemoveLineLogic() {
        List<Validation> removeLineValidation = new ArrayList<>(2);
        removeLineValidation.add(new ValidationAddRemoveLine(selection, false));
        removeLineValidation.add(new ValidationForParentRecordRemoveLine(selection));

        for (Validation validation : removeLineValidation) {
            if (!validation.doValidate()) {
                AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, validation.validationMessage());
                return;
            }
        }
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    adjustmentSummaryConfigLogic.deleteDataFromDB(selection);
                    balSummaryConfigurationTableLogic.loadSetData(true, selection);
                } catch (Exception ex) {
                    LOGGER.error("Error in balanceSummaryRemoveLineLogic :", ex);
                }
            }

            @Override
            public void noMethod() {
                LOGGER.debug("Inside the balanceSummaryRemoveLineLogic Listener NO Method");
            }
        }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getRemoveLineMessageID002());
    }

    /**
     * Mass update populate button logic
     *
     * @param event
     */
    @UiHandler("populateBtn")
    public void populateBtnButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        List<Integer> ddlbValueList = new ArrayList<>();
        ddlbValueList.add((Integer) companyDdlbRes.getValue());
        ddlbValueList.add((Integer) businessDdlbRes.getValue());
        ddlbValueList.add((Integer) deductionCategoryDdlbRes.getValue());
        ddlbValueList.add((Integer) deductionTypeDdlbRes.getValue());
        ddlbValueList.add((Integer) deductionProgramDdlbRes.getValue());
        if (ddlbValueList.contains(0)) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getPropertyMessage001());

        } else if (massfieldDdlbRes.getValue() != null) {
            LOGGER.debug("massValueDdlbRes.getValue() = {}", massfieldDdlbRes.getValue());
            Object value = massValueDdlbRes.isVisible() ? massValueDdlbRes.getValue() : massValueRes.getValue();
            if (value != null) {
                populateAction(value);
            } else {
                AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getPropertyMessage002());
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getPropertyMessage002());
        }
    }

    private void populateAction(Object massValue) throws Property.ReadOnlyException {
        Object value = massValue;
        List<AdjustmentReserveDTO> list = detailsTableContainer.getItemIds();
        setTableContainerProperties(list, value);
        if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.toString().equals(massfieldDdlbRes.getValue()) || ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.toString().equals(massfieldDdlbRes.getValue())
                || ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.REPORT_INDICATOR.toString().equals(massfieldDdlbRes.getValue())) {
            Object oppositValueToBeUpdated = null;
            switch ((int) value) {
                // value = 0 for positive and value = 1 for negative
                case 0:
                    value = null;
                    break;
                case -1:
                    value = 0;
                    oppositValueToBeUpdated = 1;
                    break;
                case 1:
                    value = 1;
                    oppositValueToBeUpdated = 0;
                    break;
                default:
                    break;
            }
            if (!ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.REPORT_INDICATOR.toString().equals(massfieldDdlbRes.getValue())) {
                String property = ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.toString().equals(massfieldDdlbRes.getValue())
                        ? ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.toString() : ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.toString();
                logic.massUpdateValue(oppositValueToBeUpdated, selection, property);
            }
        }
        logic.massUpdateValue(value, selection, massfieldDdlbRes.getValue());
        detailsTableLogic.loadsetData(true, selection);
    }

    private void setTableContainerProperties(List<AdjustmentReserveDTO> list, Object value) throws Property.ReadOnlyException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCheckRecord()) {
                resultsTable.getContainerProperty(list.get(i), massfieldDdlbRes.getValue()).setValue(value);
            }
        }
    }

    /**
     * Logic copying the line.
     *
     * @param event
     * @throws CloneNotSupportedException
     */
    @UiHandler("copyLineBtnRes")
    public void copyLineBtnResButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        List list = logic.getCheckedRecords(selection);
        int checkedRecords = list.size();
        if (checkedRecords == 0) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getCopyLineMessageID001());
        } else {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    logic.copyLineInDB(selection);
                    detailsTableLogic.loadsetData(true, selection);
                }

                @Override
                public void noMethod() {
                    LOGGER.debug(CommonConstant.INSIDE_NO_METHOD);
                }

            }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getCopyLineMessageID002());
        }
    }

    /**
     * Logic of saving the reserve account details.
     *
     * @param event
     */
    @UiHandler("saveBtnRes")
    public void saveButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        try {
            saveConfigDetails();
        } catch (Exception ex) {
            LOGGER.error("Error in saveButtonLogic :", ex);
        }
    }

    /**
     * Excel export button logic.
     *
     * @param event
     */
    @UiHandler("exportBtnRes")
    public void exportButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        ExtPagedTable resultTable;
        switch (tabPosition) {
            case 0:
                resultTable = resultsTable;
                break;
            case 1:
                resultTable = adjustmentSummaryTable;
                break;
            default:
                resultTable = balanceSummaryTable;
                break;
        }
        if (resultTable.getContainerLogic().getContainerDataSource().size() > 0) {
            selection.setTabNameForExcel(tabSheet.getTab(tabPosition).getCaption());
            if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
                selection.setIsGTNDetails(false);
                selection.setMasterSID(selection.getReserveMasterSid());
            } else {
                selection.setMasterSID(selection.getGtnDetailsMasterSid());
                selection.setIsGTNDetails(true);
            }
            final ExtCustomTable excelCustomTable = new ExtCustomTable();
            balanceSummaryLayout.addComponent(excelCustomTable);
            excelExportLogic.excelLogic(excelCustomTable, resultTable, tabPosition, selection);
            balanceSummaryLayout.removeComponent(excelCustomTable);
        }
    }

    /**
     * This Method is used to do the configuration type change logic
     *
     * @param event
     * @throws SystemException
     */
    @UiHandler("configurationTypeOpgRes")
    public void configurationTypeLogic(Property.ValueChangeEvent event) {
        LOGGER.debug(event.toString());
        detailsTableLogic.clearFilters();
        if (configurationTypeOpgRes.getValue().equals(ARMConstants.getReserveDetails())) {
            resultsTable.setVisibleColumns(ARMUtils.getAdjustmentReserveAddColumnsReserveDetails());
            resultsTable.setColumnHeaders(ARMUtils.getAdjustmentReserveAddHeadersReserveDetails());
            selection.setIsGTNDetails(false);
            selection.setMasterSID(selection.getReserveMasterSid());
        } else {
            resultsTable.setVisibleColumns(ARMUtils.getAdjustmentReserveAddColumnsGtnDetails());
            resultsTable.setColumnHeaders(ARMUtils.getAdjustmentReserveAddHeadersGtnDetails());
            selection.setMasterSID(selection.getGtnDetailsMasterSid());
            selection.setIsGTNDetails(true);
        }
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(resultsFilters);
        loadMassfield();
        LOGGER.debug("selection.getMasterSID()-->>>{}", selection.getMasterSID());
        detailsTableLogic.loadsetData(true, selection);
        List<AdjustmentReserveDTO> list = detailsTableContainer.getItemIds();
        boolean check = false;
        for (AdjustmentReserveDTO dto : list) {
            check = dto.getCheckRecord();
            if (!dto.getCheckRecord()) {
                break;
            }
        }
        resultsTable.setColumnCheckBox(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant(), true, check);
    }

    /**
     * Reseting the Results list view (reseting the Table)
     *
     * @param event
     */
    @UiHandler("resetLineBtnRes")
    public void resetLineBtnResLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    selection.setResetLine(true);
                    switch (tabPosition) {
                        case 0:
                            resetConfigureTabLine();
                            break;
                        case 1:
                            resetAdjustmentSummaryLine();
                            break;
                        case 2:
                            resetBalanceSummaryLine();
                            break;
                        default:
                            break;
                    }
                } catch (Exception ex) {
                    LOGGER.error("Error in resetLineBtnResLogic :", ex);
                }
            }

            @Override
            public void noMethod() {
                LOGGER.debug(CommonConstant.INSIDE_NO_METHOD);
            }
        }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getResetMessage());
    }

    @UiHandler("viewOpgRes")
    public void viewModeChange(Property.ValueChangeEvent event) {
        LOGGER.debug(event.toString());
        if (isTableLoaded) {
            if (viewOpgRes.getValue().equals(GlobalConstants.getCurrent())) {
                selection.setIsCurrent(true);
            } else {
                selection.setIsCurrent(false);
            }
            detailsTableLogic.loadsetData(true, selection);
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
        if (dataList.isEmpty()) {
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
                LOGGER.debug(CommonConstant.INSIDE_NO_METHOD);
            }
        }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getCloseMessageID001());
    }

    private void configureTab() {
        mainVerticalLayout.removeComponent(configurationDetailsPanel);
        mainVerticalLayout.removeComponent(adjustmentSummaryConfigurationLayout);
        mainVerticalLayout.removeComponent(balanceSummaryLayout);

        tabSheet = new TabSheet();
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheetLayout.addComponent(tabSheet);
        tabSheet.addTab(configurationDetailsPanel, "Configuration Details", null, 0);
        tabSheet.addTab(adjustmentSummaryConfigurationLayout, "Adjustment Summary Configuration", null, 1);
        tabSheet.addTab(balanceSummaryLayout, "Balance Summary Configuration", null, 2);
        attachTabSheetListener();
    }

    private void saveConfigDetails() {
        if (saveToMaster()) {
            List<Validation> validationList = new ArrayList<>();
            validationList.add(new ValidationForAtleastOneRecordToSave(selection));
            validationList.add(new MandatoryValidationForReserveAndGTN(selection));
            validationList.add(new MandatoryValidationForCreditAndDebit(selection));
            validationList.add(new ValidationForLimitedRowInDetails(selection, false));//Reserve
            validationList.add(new ValidationForDuplicateAccounts(selection));
            validationList.add(new ValidationForSameCreditDebitOfAdjustmentType(selection));
            validationList.add(new ValidationForSameReportIndicator(selection));
            validationList.add(new ValidationForLimitedRowInDetails(selection, true));//GTN
            validationList.add(new ValidationForAdjustmentTypeOfGTNAvailableInReserveDetails(selection));
            validationList.add(new ValidationForNotMatchedAccountsOfGTNReserve(selection));

            // Validation Action
            ActionExecutor executor = new ActionExecutor();
            boolean isValidated = executor.executeValidation(validationList);
            if (!isValidated) {
                return;
            }

            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {

                        List<SaveAction> saveActionObjectList = new ArrayList<>(3);
                        saveActionObjectList.add(new SaveConfigurationDetailsAction(binderDto, selection));
                        saveActionObjectList.add(new SaveAdjustmentSummaryAction(selection));
                        saveActionObjectList.add(new SaveBalanceSummaryAction(selection));
                        ActionExecutor excecute = new ActionExecutor();
                        excecute.excecuteSaveAction(saveActionObjectList);

                        final Notification notif = new Notification("Adjustment & Reserve Configuration has been successfully saved", Notification.Type.HUMANIZED_MESSAGE);
                        notif.setPosition(Position.MIDDLE_CENTER);
                        notif.show(Page.getCurrent());
                    } catch (Exception ex) {
                        LOGGER.error("Error in saveConfigDetails :", ex);
                    }
                }

                @Override
                public void noMethod() {
                    LOGGER.debug(CommonConstant.INSIDE_NO_METHOD);
                }
            }.getConfirmationMessage(CommonConstant.CONFIRMATION, "Are you sure you want to save this Adjustment & Reserve Configuration?");
        }
    }

    /**
     * FieldFactory for summary tab
     *
     */
    private void setSummaryFieldFactory() {
        AdjustmentSummaryFieldFactory fieldFactory = new AdjustmentSummaryFieldFactory(adjustmentSummaryTable, selection);
        adjustmentSummaryTable.setTableFieldFactory(fieldFactory);
        fieldFactory.addFields();
        adjustmentSummaryTable.setVisibleColumns(ARMUtils.getAdjustmentReserveColumn());
        adjustmentSummaryTable.setColumnHeaders(ARMUtils.getAdjustmentReserveHeader());
    }

    /**
     * Tab change listener for all
     *
     */
    private void attachTabSheetListener() {
        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                lastPosition = tabPosition;
                tabPosition = event.getTabSheet().getTabPosition(tab);
                selection.setTabIndex(tabPosition);
                if (selection.getMethodology() != 0 && tabPosition == 1 && lastPosition == 0) {
                    try {
                        List<SaveAction> actionList = new ArrayList<>();
                        actionList.add(new SaveConfigureDetailsToAdjustmentSummaryAction(selection));
                        ActionExecutor executor = new ActionExecutor();
                        executor.excecuteSaveAction(actionList);
                        adjustmentSummaryTable.setFilterGenerator(new AdjustmentAndBalSummaryTableGenerator(selection));
                        adjustmentSummaryTable.setFilterDecorator(new ExtDemoFilterDecorator());
                        adjustmentSummaryTableLogic.loadSetData(true, selection);
                    } catch (Exception ex) {
                        LOGGER.error("Error in ", ex);
                    }
                }
                if (tabPosition == 2) {
                    balanceSummaryTable.setFilterGenerator(new AdjustmentAndBalSummaryTableGenerator(selection));
                    balanceSummaryTable.setFilterDecorator(new ExtDemoFilterDecorator());
                    balSummaryConfigurationTableLogic.loadSetData(true, selection);
                }
                disableControlButtons(tabPosition);
            }
        });

    }

    /**
     * Method to disable the control buttons based on the Tab selection
     *
     * @param tabPosition
     */
    private void disableControlButtons(int tabPosition) {
        if (tabPosition != 0 || selection.isIsViewMode()) {
            copyLineBtnRes.setEnabled(false);
        } else {
            copyLineBtnRes.setEnabled(true);
        }
    }

    protected abstract void getMasterSids();

    /**
     * This method will configure the binder for this page.
     *
     * @return
     */
    protected abstract CustomFieldGroup getBinder();

    protected abstract void loadSelection();

    /**
     * This Method is Used to Save thee data to master table.
     *
     * @return
     */
    protected abstract boolean saveToMaster();

    protected abstract void loadTablefirstTime();

    protected abstract void resetConfigureTabLine();

    protected abstract void resetAdjustmentSummaryLine();

    protected abstract void resetBalanceSummaryLine();

    protected abstract void configureTabAddLineLogic();

    protected abstract void adjustmentSummaryAddLineLogic();

    protected abstract void balanceSummaryAddLineLogic();

    private void writeObject(ObjectOutputStream resOut) throws IOException {
        resOut.defaultWriteObject();
    }

    private void readObject(ObjectInputStream resOut) throws IOException, ClassNotFoundException {
        resOut.defaultReadObject();
    }

    public String reportIndicatorMethod(String value) {
        String val;
        if ("YES".contains(value.toUpperCase(Locale.ENGLISH))) {
            val = ZERO;
        } else if ("NO".contains(value.toUpperCase(Locale.ENGLISH))) {
            val = ONE;
        } else {
            val = "-1";
        }
        return val;
    }

    public String creditAndDebitIndicator(String value) {
        String val;
        if ("POSITIVE".contains(value.toUpperCase(Locale.ENGLISH)) && "NEGATIVE".contains(value.toUpperCase(Locale.ENGLISH))) {
            val = StringUtils.EMPTY;
        } else if ("POSITIVE".contains(value.toUpperCase(Locale.ENGLISH))) {
            val = ZERO;
        } else if ("NEGATIVE".contains(value.toUpperCase(Locale.ENGLISH))) {
            val = ONE;
        } else {
            val = "-1";
        }
        return val;
    }

    private ExtFilterGenerator resultsFilters = new ExtFilterGenerator() {
        @Override
        public Container.Filter generateFilter(Object propertyId, Object value) {
            return null;
        }

        @Override
        public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {

            if (originatingField.getValue() != null && !originatingField.getValue().toString().equals(StringUtils.EMPTY)) {
                String value = String.valueOf(originatingField.getValue().toString());
                if (propertyId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.REPORT_INDICATOR.getConstant()) && !value.isEmpty()) {
                    value = reportIndicatorMethod(value);
                    return new SimpleStringFilter(propertyId, ZERO.equals(originatingField.getValue().toString()) ? StringUtils.EMPTY : value, false, false);
                }
                if (propertyId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.getConstant()) || propertyId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.getConstant()) && !value.isEmpty()) {
                    value = creditAndDebitIndicator(value);
                    return new SimpleStringFilter(propertyId, ZERO.equals(originatingField.getValue().toString()) ? StringUtils.EMPTY : value, false, false);
                }
                return new SimpleStringFilter(propertyId, ZERO.equals(originatingField.getValue().toString()) ? StringUtils.EMPTY : originatingField.getValue().toString(), false, false);
            } else {
                return null;
            }
        }

        @Override
        public void filterRemoved(Object absReservepropertyId) {
            LOGGER.debug("Inside filterRemoved Method");
        }

        @Override
        public void filterAdded(Object absReservepropertyId, Class<? extends Container.Filter> filterType, Object value) {
            LOGGER.debug("Inside filterAdded Method");
        }

        @Override
        public Container.Filter filterGeneratorFailed(Exception reason, Object absReservepropertyId, Object value) {
            return null;
        }

        @Override
        public AbstractField<?> getCustomFilterComponent(Object absReservepropertyId) {
            if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant().equals(absReservepropertyId) || ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.COMPANYNO.getConstant().equals(absReservepropertyId)
                    || ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.DIVISION.getConstant().equals(absReservepropertyId) || ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT.getConstant().equals(absReservepropertyId)) {
                CustomTextField text = new CustomTextField();
                text.setVisible(false);
                text.setImmediate(true);
                return text;
            } else {
                CustomTextField text = new CustomTextField();
                text.setImmediate(true);
                return text;
            }
        }
    };

}
