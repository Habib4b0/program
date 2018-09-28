/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.form;

import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

import com.stpl.app.arm.accountconfiguration.dto.AccountConfigDTO;
import com.stpl.app.arm.accountconfiguration.dto.AccountConfigSelection;
import com.stpl.app.arm.accountconfiguration.dto.SearchResultsTableGenerator;
import com.stpl.app.arm.accountconfiguration.logic.AccountConfigLogic;
import com.stpl.app.arm.accountconfiguration.logic.tablelogic.AccountConfigTableLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.asi.ui.extfilteringtable.ExtCustomTable;

/**
 *
 * @author Srithar.R
 */
public abstract class AbstractAccountConfig extends CustomWindow {

    @UiField("horizontalDetailsLayout")
    protected HorizontalLayout horizontalDetailsLayout;

    /**
     * The Table Layout
     */
    @UiField("resultsTableLayout")
    private VerticalLayout resultsTableLayout;

    /**
     * The View Option Group determines the List View to show the current
     * Records or the Historical Records
     */
    @UiField("viewOpg")
    protected OptionGroup viewOpg;
    /**
     * The Mass Update Combo box shows the field to be populated.Options will be
     * all of the columns available in the list view (based on Configuration
     * Type radio selected).
     */
    @UiField("massfieldDdlb")
    protected ComboBox massfieldDdlb;
    /**
     * The Mass Value Combo box is for selection of a value in mass update
     */
    @UiField("massValueDdlb")
    protected ComboBox massValueDdlb;

    /**
     * The Mass Value Text Field is for entering a value in mass update
     */
    @UiField("massValue")
    protected TextField massValue;
    /**
     * The Search Form Layout which will be disabled in Add/Edit/View Modes
     */
    @UiField("searchFormLayout")
    protected HorizontalLayout searchFormLayout;
    @UiField("addLineBtn")
    protected Button addLineBtn;
    @UiField("resetLineBtn")
    protected Button resetLineBtn;
    @UiField("removeLineBtn")
    protected Button removeLineBtn;
    @UiField("copyLineBtn")
    protected Button copyLineBtn;
    @UiField("saveBtn")
    protected Button saveBtn;
    @UiField("closeBtn")
    protected Button closeBtn;
    @UiField("exportBtn")
    protected Button exportBtn;
    @UiField("populateBtn")
    protected Button populateBtn;
    @UiField("addPanel")
    protected Panel addPanel;

    @UiField("labelMassUpdate")
    protected Label labelMassUpdate;
    @UiField("labelField")
    protected Label labelField;
    @UiField("labelValue")
    protected Label labelValue;
    @UiField("labelView")
    protected Label labelView;

    public Button getCloseBtnRes() {
        return closeBtn;
    }
    /**
     * The Table Logic For Adjustment Reserve Configuration in Add/Edit Mode
     */
    protected AccountConfigTableLogic detailsTableLogic = new AccountConfigTableLogic();
    /**
     * The Results Table
     */
    protected ExtPagedTable resultsTable = new ExtPagedTable(detailsTableLogic);
    /**
     * The Available Results Container
     */
    protected final BeanItemContainer<AccountConfigDTO> detailsTableContainer = new BeanItemContainer<>(AccountConfigDTO.class);
    /**
     * The Adjustment Reserve Logic
     */
    protected AccountConfigLogic logic = AccountConfigLogic.getInstance();
    /**
     * The Session DTO
     */
    protected SessionDTO sessionDTO;
    /**
     * This field is used to save the field values.
     */
    protected AccountConfigSelection selection;

    protected CommonSecurityLogic commonSecurity = new CommonSecurityLogic();
    /**
     * The Constant LOGGER.
     */
    protected static final Logger GTNLOGGER = LoggerFactory.getLogger(AbstractAccountConfig.class);

    private List<Object[]> resultsBrandFactoryDdlbList = new ArrayList<>();
    private List<Object[]> resultsCompanyFactoryDdlbList = new ArrayList<>();
    private List<Object[]> resultsBusinessUnitFactoryDdlbList = new ArrayList<>();
    private Map<String, String> updateFieldFactoryValues = new HashMap<>();
    private Map<String, String> companyAndBuMap = new HashMap<>();
    private boolean isTableLoaded = false;

    public AbstractAccountConfig(String caption, SessionDTO sessionDTO, AccountConfigSelection selection) {
        super(caption);
        this.sessionDTO = sessionDTO;
        this.selection = selection;
        init();
    }

    /**
     * Configuring Fields For Add/Edit/View Mode In Account Config Configuration
     */
    protected void configureFields() {
        loadSelection();
        searchFormLayout.setVisible(false);
        viewOpg.addItem(GlobalConstants.getCurrent());
        viewOpg.addItem(GlobalConstants.getHistory());
        viewOpg.select(GlobalConstants.getCurrent());
        loadFieldFactoryValuesMap();
        loadCompanyAndBU();
        loadMassfield();
        massValueDdlb.setVisible(false);
        massValueDdlb.setImmediate(true);
        configureTable();

        exportBtn.setPrimaryStyleName("link");
        exportBtn.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        resultsTable.addColumnCheckListener(new ExtCustomTable.ColumnCheckListener() {
            @Override
            public void columnCheck(ExtCustomTable.ColumnCheckEvent event) {
                Collection itemList = resultsTable.getItemIds();
                for (Object obj : itemList) {
                    AccountConfigDTO dto = (AccountConfigDTO) obj;
                    dto.setCheckRecord(event.isChecked());
                    resultsTable.getContainerProperty(obj, event.getPropertyId()).setValue(event.isChecked());
                    logic.checkAllUpdate(selection.getTempTableName(), event.isChecked());
                }

            }
        });
        massfieldDdlb.focus();
    }

    /**
     * Initialization Of the UI Components and Reading XML files and adding
     * corresponding styles to it
     */
    private void init() {
        center();
        setWidth(NumericConstants.HUNDRED, Sizeable.Unit.PERCENTAGE);
        setPositionX(0);
        setPositionY(0);
        setMinimizeToTray();
        addStyleName("valo-theme-customwindow");
        addStyleName("bootstrap-ui");
        addStyleName(ARMUtils.BOOTSTRAP);
        addStyleName(ARMUtils.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setContent(Clara.create(getClass().getResourceAsStream("/account_config/account-config-form.xml"), this));
    }

    /**
     * Loading the mass update field ddlb.
     */
    private void loadMassfield() {
        massValueDdlb.removeAllItems();
        massfieldDdlb.removeAllItems();
        massValue.setValue(StringUtils.EMPTY);
        massfieldDdlb.setImmediate(true);
        massfieldDdlb.addItem(GlobalConstants.getSelectOne());
        massfieldDdlb.setNullSelectionAllowed(true);
        massfieldDdlb.setNullSelectionItemId(GlobalConstants.getSelectOne());
        massValueDdlb.setValue(null);
        loadMassFieldValue();
    }

    public void loadMassFieldValue() {
        massfieldDdlb.addItems(getMassUpdateProperties());
        for (int i = 0; i < getMassUpdateProperties().length; i++) {
            massfieldDdlb.setItemCaption(getMassUpdateProperties()[i], getMassUpdateValues()[i]);
        }
    }

    /**
     * Configuring Result Table in Add/Edit Mode
     */
    private void configureTable() {
        GTNLOGGER.info("Inside Configure table");
        resultsTableLayout.addComponent(resultsTable);
        resultsTableLayout.addComponent(getResponsiveControls(detailsTableLogic.createControls()));
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        setTableFieldFactory();
        resultsTable.setFilterGenerator(new SearchResultsTableGenerator());
        detailsTableLogic.setContainerDataSource(detailsTableContainer);
        resultsTable.setSelectable(true);
        resultsTable.setMultiSelect(true);
        detailsTableLogic.setPageLength(NumericConstants.TEN);
        detailsTableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(getVisibleColumns());
        resultsTable.setColumnHeaders(getColumnHeaders());
        resultsTable.setColumnCheckBox(VariableConstants.CHECK_RECORD, true, false);
        resultsTable.setColumnAlignment(VariableConstants.CHECK_RECORD, ExtCustomTable.Align.CENTER);
        resultsTable.setColumnWidth(VariableConstants.CHECK_RECORD, NumericConstants.FIFTY);
        resultsTable.setSizeFull();
        resultsTable.setImmediate(true);
        resultsTable.setPageLength(NumericConstants.TEN);

        resultsTable.setEditable(true);

        loadTablefirstTime();
        resultsTable.addStyleName(ARMUtils.FILTERCOMBOBOX);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.addStyleName(ARMUtils.CENTER_CHECK);
        isTableLoaded = true;

    }

    /**
     * Configuring Mass Update
     *
     * @param event
     */
    @UiHandler("massfieldDdlb")
    public void loadMassValue(Property.ValueChangeEvent event) {
        String massfieldDdlbValue = massfieldDdlb.getValue() != null ? massfieldDdlb.getValue().toString().trim() : StringUtils.EMPTY;
        if (massfieldDdlbValue.isEmpty()) {
            massValueDdlb.removeAllItems();
            massValueDdlb.addItem(0);
            massValueDdlb.setItemCaption(0, GlobalConstants.getSelectOne());
            massValueDdlb.setNullSelectionAllowed(true);
            massValueDdlb.setNullSelectionItemId(0);
        } else if (ArrayUtils.contains(ARMUtils.getAccountConfigCombobox(), massfieldDdlbValue)) {
            massValueDdlb.removeAllItems();
            massValueDdlb.setVisible(true);
            this.massValue.setVisible(false);
            loadMassValueDdlb(massfieldDdlbValue);
        } else {
            this.massValue.setValue(StringUtils.EMPTY);
            massValueDdlb.setVisible(false);
            this.massValue.setVisible(true);
        }
    }

    /**
     * Logic of adding the line in result list view.
     *
     * @param event
     * @throws com.vaadin.data.fieldgroup.FieldGroup.CommitException
     */
    @UiHandler("addLineBtn")
    public void addLineButtonLogic(Button.ClickEvent event) {
        addLineBtnLogic();
    }

    /**
     * Logic of Remove line from the list view.
     *
     * @param event
     */
    @UiHandler("removeLineBtn")
    public void removeLineButtonLogic(Button.ClickEvent event) {
        final List<AccountConfigDTO> list = detailsTableContainer.getItemIds();
        final List<AccountConfigDTO> removefinalList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCheckRecord()) {
                removefinalList.add(list.get(i));
            }
        }
        if (removefinalList.isEmpty()) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getRemoveLineMessageID001());
        } else {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        logic.removeLineLogic(selection);
                        for (int i = 0; i < removefinalList.size(); i++) {
                            detailsTableContainer.removeItem(removefinalList.get(i));
                        }
                        if (list.isEmpty()) {
                            resultsTable.setColumnCheckBox(CommonConstant.CHECK_RECORD, true, false);
                        }
                        detailsTableLogic.loadsetData(true, selection);
                    } catch (Exception ex) {
                        GTNLOGGER.error("Error in removeLineButtonLogic :", ex);
                    }
                }

                @Override
                public void noMethod() {
                    GTNLOGGER.debug("Inside the removeLineButtonLogic Listener NO Method");
                }
            }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getRemoveLineMessageID002());
        }
    }

    /**
     * Mass update populate button logic
     *
     * @param event
     */
    @UiHandler("populateBtn")
    public void populateBtnButtonLogic(Button.ClickEvent event) {
        if (massfieldDdlb.getValue() != null) {
            GTNLOGGER.info("massValueDdlbRes.getValue() = {}", massfieldDdlb.getValue());
            Object value = massValueDdlb.isVisible() ? massValueDdlb.getValue() : massValue.getValue();
            if (value != null && !String.valueOf(value).isEmpty()) {
                populateBtnIterator(value);
            } else {
                AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getPropertyMessage002());
            }
        } else {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getPropertyMessage002());
        }

    }

    private void populateBtnIterator(Object value) {
        Object values;
        List<AccountConfigDTO> list = detailsTableContainer.getItemIds();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCheckRecord()) {
                populateHelperDto(value, list, i);
            }
        }
        if (value instanceof HelperDTO) {
            HelperDTO dto = (HelperDTO) value;
            values = dto.getId();
        } else {
            values = value;
        }
        if (logic.isCheckedAtleastOneItem(selection.getTempTableName())) {
            String columnName= updateFieldFactoryValues.get(massfieldDdlb.getValue().toString());
            logic.massUpdateValue(values, selection, columnName);
            if ("GL_COMPANY_MASTER_SID".equals(columnName) || "BU_COMPANY_MASTER_SID".equals(columnName) )
                logic.massUpdateValue(0, selection, "ACCOUNT");
        } else {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, "No Items are selected");
        }
    }

    public void populateHelperDto(Object value, List<AccountConfigDTO> list, int i) {
        if (value instanceof HelperDTO) {

            HelperDTO compNameDto = (HelperDTO) value;
            resultsTable.getContainerProperty(list.get(i), companyAndBuMap.get(massfieldDdlb.getValue())).setValue(compNameDto.getDescription());
            int companyNo = "companyName".equals(companyAndBuMap.get(String.valueOf(massfieldDdlb.getValue())))
                    ? compNameDto.getId() : list.get(i).getCompanyNoHelperDto().getId();
            int businessUnitNo = "businessUnitName".equals(companyAndBuMap.get(String.valueOf(massfieldDdlb.getValue())))
                    ? compNameDto.getId() : list.get(i).getBusinessNoHelperDto().getId();
            logic.loadAccount((ComboBox) list.get(i).getFieldFactoryComponent(CommonConstant.ACCOUNT), companyNo, businessUnitNo);
        }
        resultsTable.getContainerProperty(list.get(i), massfieldDdlb.getValue()).setValue(value);
    }

    /**
     * Logic copying the line.
     *
     * @param event
     * @throws CloneNotSupportedException
     */
    @UiHandler("copyLineBtn")
    public void copyLineBtnResButtonLogic(Button.ClickEvent event) {
        if (!logic.isCheckedAtleastOneItem(selection.getTempTableName())) {
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
                    GTNLOGGER.debug("Inside the copyLineBtnResButtonLogic Listener NO Method");
                }

            }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getCopyLineMessageID002());
        }
    }

    /**
     * Logic of saving the reserve account details.
     *
     * @param event
     */
    @UiHandler("saveBtn")
    public void saveButtonLogic(Button.ClickEvent event) {
        if (resultsTable.size() == 0) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, "At least one record is needed to Save.");
            return;
        }
        String[] tableNames = new String[]{selection.getTempTableName(), selection.getTempTableName(), selection.getTempTableName(), selection.getTempTableName()};
        if (logic.mandatoryAndDuplicateCheckForSave(Arrays.asList(tableNames), "Duplicatecheck for save")) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, "Please ensure that each unique combination of Company, Business Unit, Account, Brand, and Cost Center is only in the list view once.");
            return;
        }
        String[] tableName = new String[]{selection.getTempTableName()};
        if (logic.mandatoryAndDuplicateCheckForSave(Arrays.asList(tableName), "Mandatory check for save")) {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    logic.saveTempToMainTable(selection.getTempTableName());
                    CommonUtils.successNotification("Record has been has been successfully saved ");
                }

                @Override
                public void noMethod() {
                    GTNLOGGER.debug("Inside the saveButtonLogic Listener NO Method");
                }
            }.getConfirmationMessage(CommonConstant.CONFIRMATION, "Save record ?");

            return;
        }
        AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSaveMessageID006());
    }

    public void setTableFieldFactory() {
        AccountConfigFieldFactory fieldFactory = new AccountConfigFieldFactory(resultsTable, selection, resultsBrandFactoryDdlbList, resultsCompanyFactoryDdlbList, resultsBusinessUnitFactoryDdlbList, updateFieldFactoryValues);
        resultsTable.setTableFieldFactory(fieldFactory);
    }

    /**
     * Reseting the Results list view (reseting the Table)
     *
     * @param event
     */
    @UiHandler("resetLineBtn")
    public void resetLineBtnResLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    logic.resetLineLogic(selection.getTempTableName());
                    detailsTableLogic.loadsetData(true, selection);
                } catch (Exception ex) {
                    GTNLOGGER.error("Error in resetLineBtnResLogic :", ex);
                }
            }

            @Override
            public void noMethod() {
                GTNLOGGER.debug("Inside the resetLineBtnResLogic Listener NO Method");
            }
        }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getResetMessage());
    }

    @UiHandler("viewOpg")
    public void viewModeChange(Property.ValueChangeEvent event) {
        if (isTableLoaded) {
            if (viewOpg.getValue().equals(GlobalConstants.getCurrent())) {
                resultsTable.setVisibleColumns(getVisibleColumns());
                resultsTable.setColumnHeaders(getColumnHeaders());
                selection.setCurrentView(true);
            } else {
                resultsTable.setVisibleColumns(ARMUtils.getAccountConfigViewmodeColumns());
                resultsTable.setColumnHeaders(ARMUtils.getAccountConfigViewmodeHeaders());
                resultsTable.setSelectable(false);
                selection.setCurrentView(false);
            }
            resultsTable.setColumnAlignment(VariableConstants.CHECK_RECORD, ExtCustomTable.Align.CENTER);
            resultsTable.setColumnWidth(VariableConstants.CHECK_RECORD, NumericConstants.FIFTY);
            loadTablefirstTime();

            populateBtn.setEnabled(!selection.isViewMode() && selection.isCurrentView());
            massValueDdlb.setEnabled(selection.isCurrentView());
            massValue.setEnabled(selection.isCurrentView());
            massfieldDdlb.setEnabled(selection.isCurrentView());
            resultsTable.setColumnCheckBox(CommonConstant.CHECK_RECORD, selection.isCurrentView());
            resetLineBtn.setEnabled(!selection.isViewMode() && selection.isCurrentView());
            addLineBtn.setEnabled(!selection.isViewMode() && selection.isCurrentView());
            removeLineBtn.setEnabled(!selection.isViewMode() && selection.isCurrentView());

            copyLineBtn.setEnabled(!selection.isViewMode() && selection.isCurrentView());
            saveBtn.setEnabled(!selection.isViewMode() && selection.isCurrentView());
            if (selection.isViewMode()) {
                massValueDdlb.setReadOnly(selection.isCurrentView());
                massValue.setReadOnly(selection.isCurrentView());
                massfieldDdlb.setReadOnly(selection.isCurrentView());
                populateBtn.setEnabled(false);
                resetLineBtn.setEnabled(false);
            }
        }

    }

    /**
     * Excel export button logic.
     *
     * @param event
     */
    @UiHandler("exportBtn")
    public void exportButtonLogic(Button.ClickEvent event) {
        try {
            createWorkSheet("Account Configuration", resultsTable);

        } catch (Exception ex) {
            GTNLOGGER.error("Error in exportButtonLogic :", ex);
        }
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        try {

            long recordCount = 0;
            if (resultTable.size() != 0) {
                recordCount = selection.isViewMode() && selection.isCurrentView() ? 1 : logic.getAccountConfigCount(selection, detailsTableLogic.getFilters());
            }
            ExcelExportforBB.createWorkSheet(ARMUtils.getExcelAccountConfigSearchHeaders(), recordCount, this, UI.getCurrent(), moduleName.replace(ARMUtils.SPACE.toString(), String.valueOf(ARMUtils.UNDERSCORE)).toUpperCase(Locale.ENGLISH));
        } catch (Exception ex) {
            GTNLOGGER.error("Error in createWorkSheet :", ex);
        }
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {

        try {
            if (end != 0) {
                List<AccountConfigDTO> searchList = new ArrayList<>();
                if (selection.isViewMode() && selection.isCurrentView()) {
                    searchList.add(selection.getSearchAccConfigDTO());
                } else {
                    searchList = logic.getAccountConfigData(selection, start, end, detailsTableLogic.getFilters(), detailsTableLogic.getSortByColumns());
                }
                ExcelExportforBB.createFileContent(ARMUtils.getExcelAccountConfigSearchColumns(), searchList, printWriter);
            }
        } catch (Exception e) {
            GTNLOGGER.error("Error in createWorkSheetContent :", e);
        }
    }

    /**
     * This method will return the company No based on the selected company from
     * company drop down.
     *
     * @param sysid
     * @return
     */
    protected String getCompanyNo(int sysid) {
        List companyInput = new ArrayList<>();
        companyInput.add(sysid);
        List dataList = QueryUtils.getItemData(companyInput, "Load_Company_No", null);
        if (dataList.isEmpty()) {
            return StringUtils.EMPTY;
        }
        return String.valueOf(dataList.get(0));
    }

    public void closeAccountBtnLogic() {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                CommonLogic.dropDynamicTables(String.valueOf(sessionDTO.getUserId()), String.valueOf(sessionDTO.getSessionId()));
                close();
            }

            @Override
            public void noMethod() {
                GTNLOGGER.debug("Inside the closeBtnLogic Listener NO Method");
            }
        }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getCloseMessageID001());
    }

    private void loadMassValueDdlb(String massfieldDdlbValue) {
        massValueDdlb.removeAllItems();
        switch (massfieldDdlbValue) {
            case CommonConstant.ACCOUNT:
                loadMassValueDdlbForAccount();
                break;
            case "brandDdlb":
                loadMassValueDdlbForBrandDdlb();
                break;
            case CommonConstant.BUSINESS_NO_HELPER_DTO:
                logic.loadCompanyOrBusinessUnitDdlb(massValueDdlb, resultsBusinessUnitFactoryDdlbList, "getBusinessQuery", Boolean.FALSE, Boolean.FALSE);
                break;
            case CommonConstant.COMPANY_NO_HELPER_DTO:
                logic.loadCompanyOrBusinessUnitDdlb(massValueDdlb, resultsCompanyFactoryDdlbList, "getCompanyQuery", Boolean.FALSE, Boolean.FALSE);
                break;
            default:

        }

    }

    private void loadMassValueDdlbForAccount() {
        massValueDdlb.addItem(GlobalConstants.getSelectOne());
        massValueDdlb.setItemCaption(GlobalConstants.getSelectOne(), GlobalConstants.getSelectOne());
        List<Object[]> ddlbList = QueryUtils.getItemData(new ArrayList(), "loadAccountFilterDdlb", null);
        for (Object[] obj : ddlbList) {
            if (obj[NumericConstants.ONE] != null) {
                massValueDdlb.addItem(obj[NumericConstants.ONE]);
                massValueDdlb.setItemCaption(obj[NumericConstants.ONE], "" + obj[NumericConstants.ONE]);
            }
        }
        massValueDdlb.setNullSelectionAllowed(true);
        massValueDdlb.setNullSelectionItemId(GlobalConstants.getSelectOne());

    }

    private void loadMassValueDdlbForBrandDdlb() {
        massValueDdlb.addItem(0);
        massValueDdlb.setItemCaption(NumericConstants.ZERO, GlobalConstants.getSelectOne());
        List<Object[]> ddlbList = QueryUtils.getItemData(new ArrayList(), "loadBrand", null);
        for (Object[] obj : ddlbList) {
            if (obj[NumericConstants.ONE] != null) {
                massValueDdlb.addItem(obj[NumericConstants.ZERO]);
                massValueDdlb.setItemCaption(obj[NumericConstants.ZERO], "" + obj[NumericConstants.TWO]);
            }
        }
        massValueDdlb.setNullSelectionAllowed(true);
        massValueDdlb.setNullSelectionItemId(NumericConstants.ZERO);
    }

    private void loadFieldFactoryValuesMap() {
        updateFieldFactoryValues.put(CommonConstant.COMPANY_NO_HELPER_DTO, "GL_COMPANY_MASTER_SID");
        updateFieldFactoryValues.put(CommonConstant.BUSINESS_NO_HELPER_DTO, "BU_COMPANY_MASTER_SID");
        updateFieldFactoryValues.put(CommonConstant.ACCOUNT, "ACCOUNT");
        updateFieldFactoryValues.put("brandDdlb", "BRAND_MASTER_SID");
        updateFieldFactoryValues.put("costCentre", "COST_CENTER");
        updateFieldFactoryValues.put(CommonConstant.CHECK_RECORD, "CHECK_RECORD");
    }

    private void loadCompanyAndBU() {
        companyAndBuMap.put(CommonConstant.COMPANY_NO_HELPER_DTO, "companyName");
        companyAndBuMap.put(CommonConstant.BUSINESS_NO_HELPER_DTO, "businessUnitName");
    }

    protected abstract void loadSelection();

    protected abstract void addLineBtnLogic();

    /**
     * This Method is Used to Save thee data to master table.
     *
     * @return
     */
    protected abstract boolean saveToMaster();

    protected abstract void loadTablefirstTime();

    protected abstract String[] getMassUpdateValues();

    protected abstract Object[] getMassUpdateProperties();

    protected abstract Object[] getVisibleColumns();

    protected abstract String[] getColumnHeaders();

    public Button getAddLineBtn() {
        return addLineBtn;
    }

    public Button getResetLineBtn() {
        return resetLineBtn;
    }

    public Button getRemoveLineBtn() {
        return removeLineBtn;
    }

    public Button getCopyLineBtn() {
        return copyLineBtn;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public Button getCloseBtn() {
        return closeBtn;
    }

    public Button getExportBtn() {
        return exportBtn;
    }

    public ComboBox getMassfieldDdlb() {
        return massfieldDdlb;
    }

    public ComboBox getMassValueDdlb() {
        return massValueDdlb;
    }

    public TextField getMassValue() {
        return massValue;
    }

    public Button getPopulateBtn() {
        return populateBtn;
    }

    public HorizontalLayout getHorizontalDetailsLayout() {
        return horizontalDetailsLayout;
    }

    public OptionGroup getViewOpg() {
        return viewOpg;
    }

    public Label getLabelMassUpdate() {
        return labelMassUpdate;
    }

    public Label getLabelField() {
        return labelField;
    }

    public Label getLabelValue() {
        return labelValue;
    }

    public Label getLabelView() {
        return labelView;
    }

    @Override
    public boolean equals(Object absAccConfigObj) {
        return super.equals(absAccConfigObj);
    }

    @Override
    public int hashCode() {
        GTNLOGGER.debug("Abstract Account Config HashCode");
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream objectOutputStreamOut) throws IOException {
        objectOutputStreamOut.defaultWriteObject();
    }

    private void readObject(ObjectInputStream objectOutputStreamIn) throws IOException, ClassNotFoundException {
        objectOutputStreamIn.defaultReadObject();
    }

    protected void getButtonVisiblity(Map<String, AppPermission> functionHM, String buttonName, Button button) {
        if (functionHM.get(buttonName) != null && !(functionHM.get(buttonName)).isFunctionFlag()) {
            button.setVisible(false);
        } else {
            button.setVisible(true);
        }
    }

    protected void securityForButtons() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, CommonConstant.ACCOUNT_CONFIGURATION + ARMUtils.COMMA_CHAR + "Landing screen");
        getButtonVisiblity(functionHM, "addLineBtn", getAddLineBtn());
        getButtonVisiblity(functionHM, "resetLineBtn", getResetLineBtn());
        getButtonVisiblity(functionHM, "removeLineBtn", getRemoveLineBtn());
        getButtonVisiblity(functionHM, "copyLineBtn", getCopyLineBtn());
        getButtonVisiblity(functionHM, "saveBtn", getSaveBtn());
        getButtonVisiblity(functionHM, "closeBtn", getCloseBtn());
        securityForButton(functionHM);
    }

    protected void securityForFields() {

        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHMforFields = stplSecurity.getBusinessFieldPermission(userId, CommonConstant.ACCOUNT_CONFIGURATION + ARMUtils.COMMA_CHAR + "Landing Screen");
        configureFieldPermission(functionHMforFields);
        if (functionHMforFields.get("massfieldDdlb") != null && !(functionHMforFields.get("massfieldDdlb")).isFunctionFlag()) {
            getMassfieldDdlb().setVisible(false);
            getLabelField().setVisible(false);

        } else {
            getMassfieldDdlb().setVisible(true);
            getLabelField().setVisible(true);

        }
        if (functionHMforFields.get("massValue") != null && !(functionHMforFields.get("massValue")).isFunctionFlag()) {
            getMassValue().setVisible(false);
            getLabelValue().setVisible(false);
        } else {
            getMassValue().setVisible(true);
            getLabelValue().setVisible(true);

        }

    }

    protected void configureFieldPermission(Map<String, AppPermission> functionHMforFields) {
        GTNLOGGER.debug("Entering configurePermission");
        List<Object> resultList = logic.getFieldsForSecurity(CommonConstant.ACCOUNT_CONFIGURATION, "Landing Screen");
        HorizontalLayout horizontalLayout = getHorizontalDetailsLayout();
        commonSecurity.removeComponentOnPermission(resultList, horizontalLayout, functionHMforFields, CommonSecurityLogic.ADD);
        GTNLOGGER.debug("Ending configurePermission");

    }

    protected void securityForButton(Map<String, AppPermission> functionHM) {
        if (functionHM.get("exportBtn") != null && !(functionHM.get("exportBtn")).isFunctionFlag()) {
            getExportBtn().setVisible(false);
        } else {
            getExportBtn().setVisible(true);
        }
        if (functionHM.get("populateBtn") != null && !(functionHM.get("populateBtn")).isFunctionFlag()) {
            getPopulateBtn().setVisible(false);
        } else {
            getPopulateBtn().setVisible(true);
        }
        if (functionHM.get("viewOpg") != null && !(functionHM.get("viewOpg")).isFunctionFlag()) {
            getViewOpg().setVisible(false);
            getLabelView().setVisible(false);
        } else {
            getViewOpg().setVisible(true);
            getLabelView().setVisible(true);
        }
    }
}
