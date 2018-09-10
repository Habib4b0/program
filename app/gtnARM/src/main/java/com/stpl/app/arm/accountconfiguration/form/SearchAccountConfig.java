/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.form;

import com.stpl.app.arm.accountconfiguration.dto.AccountConfigDTO;
import com.stpl.app.arm.accountconfiguration.dto.AccountConfigSelection;
import com.stpl.app.arm.accountconfiguration.dto.SearchResultsTableGenerator;
import com.stpl.app.arm.accountconfiguration.logic.AccountConfigLogic;
import com.stpl.app.arm.accountconfiguration.logic.tablelogic.AccountConfigSearchTableLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.customwindow.MinimizeTray;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar.Raju
 */
public class SearchAccountConfig extends CustomComponent {

    private SessionDTO sessionDTO;

    @UiField("excelBtn")
    public Button excelBtn;

    @UiField("resetBtn")
    public Button resetBtn;

    @UiField("searchBtn")
    public Button searchBtn;

    @UiField("addBtn")
    public Button addBtn;

    @UiField("editBtn")
    public Button editBtn;

    @UiField("viewBtn")
    public Button viewBtn;

    @UiField("copyBtn")
    public Button copyBtn;

    @UiField("deleteBtn")
    public Button deleteBtn;

    @UiField("companyDdlb")
    public ComboBox companyDdlb;

    @UiField("businessDdlb")
    public ComboBox businessDdlb;

    @UiField("brandDdlb")
    public ComboBox brandDdlb;
    @UiField("accountDdlb")
    public ComboBox accountDdlb;

    @UiField("costCentre")
    public TextField costCentre;

    @UiField("labelCompanyRes")
    public Label labelCompanyRes;
    @UiField("labelBusinessRes")
    public Label labelBusinessRes;
    @UiField("labelAccount")
    public Label labelAccount;
    @UiField("labelbrandDdlb")
    public Label labelbrandDdlb;
    @UiField("labelcostCentre")
    public Label labelcostCentre;

    /**
     * The Table Layout in Reserve Configuration
     */
    @UiField("resultsTableLayoutRes")
    private VerticalLayout resultsTableLayout;
    /**
     * The Search Results Table Logic in Reserve Configuration
     */
    private AccountConfigSearchTableLogic accountConfigSearchTableLogic = new AccountConfigSearchTableLogic();
    /**
     * The Results Table for Search Screen in Reserve Configurations
     */
    private ExtPagedTable resultsTable = new ExtPagedTable(accountConfigSearchTableLogic);

    /**
     * The Logic Class for the Adjustment and Reserve Configuration
     */
    private AccountConfigLogic logic = AccountConfigLogic.getInstance();

    /**
     * The Container For Results Table in Search Screen in Reserve Configuration
     */
    private final BeanItemContainer<AccountConfigDTO> resultsContainer = new BeanItemContainer<>(AccountConfigDTO.class);

    private AccountConfigDTO binderDto = new AccountConfigDTO();
    private final CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(binderDto));
    public static final Logger GTNLOGGER = LoggerFactory.getLogger(SearchAccountConfig.class);
    private AccountConfigSelection accSelection;
    private CommonSecurityLogic commonSecurity = new CommonSecurityLogic();

    /**
     * The Constructor for Search Screen in Adjustment Reserve Configuration.
     *
     * @param sessionDTO
     */
    public SearchAccountConfig(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/account_config/search-account-config.xml"), this));
        init();
    }

    /**
     * Inital Method invocation on Index search class load
     *
     * @throws SystemException
     * @throws PortalException
     *
     * @throws Exception
     */
    private void init() {
        configureFields();
        securityForButtons();
        securityForFields();
        securityForTables();

    }

    /**
     * Configuring all UI components and its properties
     *
     * @throws SystemException
     *
     * @throws Exception
     */
    private void configureFields() {
        getBinder();
        excelBtn.setPrimaryStyleName("link");
        excelBtn.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        loadingDropDown();
        configureTable();
        companyDdlb.focus();
    }

    /**
     * This method will configure the binder for this page.
     *
     * @return
     */
    private CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    /**
     * Configuring Result Table for Search Screen
     */
    private void configureTable() {
        resultsTableLayout.addComponent(resultsTable);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setSelectable(true);
        resultsTable.setFilterGenerator(new SearchResultsTableGenerator());
        resultsTableLayout.addComponent(getResponsiveControls(accountConfigSearchTableLogic.createControls()));
        accountConfigSearchTableLogic.setContainerDataSource(resultsContainer);
        resultsTable.setSelectable(true);
        resultsTable.setMultiSelect(false);
        accountConfigSearchTableLogic.setPageLength(NumericConstants.TEN);
        accountConfigSearchTableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(ARMUtils.getAccountConfigSearchColumns());
        resultsTable.setColumnHeaders(ARMUtils.getAccountConfigSearchHeaders());
        for (Object visibleColumns : resultsTable.getVisibleColumns()) {
            String value = visibleColumns.toString();
            if (value.endsWith("Date")) {
                resultsTable.setColumnAlignment(visibleColumns, ExtCustomTable.Align.CENTER);
            }
        }
        resultsTable.setSizeFull();
        resultsTable.setImmediate(true);
        resultsTable.setPageLength(NumericConstants.TEN);

        resultsTable.addStyleName(ARMUtils.FILTERCOMBOBOX);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.addStyleName(ARMUtils.CENTER_CHECK);

        resultsTable.addStyleName("filterbar");

        resultsTable.setFilterBarVisible(true);
    }

    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    binderDto = new AccountConfigDTO();
                    binder.setItemDataSource(new BeanItem<>(binderDto));
                    binder.commit();
                } catch (Exception ex) {
                    GTNLOGGER.error("Error in resetButtonLogic : ", ex);
                }
            }

            @Override
            public void noMethod() {
                GTNLOGGER.debug("Inside the resetButtonLogic Listener NO Method");

            }
        }.getConfirmationMessage("Confirmation", ARMMessages.getResetMessageID003());

    }

    /**
     * .
     * Loading UI Drop down values
     *
     */
    private void loadingDropDown() {
        CommonLogic.configureDropDowns(companyDdlb, "getCompanyQuery", true);
        CommonLogic.configureDropDowns(businessDdlb, "getBusinessQuery", true);
        CommonLogic.configureDropDowns(brandDdlb, "loadBrand", true);
    }

    /**
     * Value change logic of company drop down.
     *
     * @param event
     */
    @UiHandler("companyDdlb")
    public void companyDdlbChangeLogic(Property.ValueChangeEvent event) {
        loadAccountDdlb();
    }

    /**
     * Value change logic of company drop down.
     *
     * @param event
     */
    @UiHandler("businessDdlb")
    public void businessDdlbChangeLogic(Property.ValueChangeEvent event) {
        loadAccountDdlb();
    }

    /**
     * Copy Button Listener For Search Form
     *
     * @param event
     * @throws java.lang.CloneNotSupportedException
     * @throws com.vaadin.data.fieldgroup.FieldGroup.CommitException
     */
    @UiHandler("searchBtn")
    public void searchBtnLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        searchLogic();
    }

    private void searchLogic() throws FieldGroup.CommitException {
        binder.commit();
        if (binderDto.getCompanyDdlb() != 0 || binderDto.getBusinessDdlb() != 0 || binderDto.getBrandDdlb() != 0 || !binderDto.getCostCentre().isEmpty()
                || !binderDto.getAccount().isEmpty()) {
            if (!accountConfigSearchTableLogic.loadsetData(Boolean.TRUE, binderDto)) {
                CommonUtils.successNotification(ARMMessages.getNoResultsFoundMessage());
            }
            resultsTable.setValue(null);
        } else {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.NO_SEARCH_CRITERIA, ARMMessages.getSearchMsg_001());
            accountConfigSearchTableLogic.loadsetData(Boolean.FALSE, binderDto);
            accountConfigSearchTableLogic.getFilters().clear();
        }
    }

    /**
     * Add Button Listener For Sear
     *
     * @param event
     * @throws java.lang.CloneNotSupportedException
     * @throws SystemException
     * @throws PortalException
     */
    @UiHandler("addBtn")
    public void addButtonLogic(Button.ClickEvent event) {
        sessionDTO.setMode(ARMUtils.ADD);
        SessionDTO selection = createSessionId();
        accSelection = new AccountConfigSelection();
        accSelection.setSession(selection);
        selection.setScreenName("ARM_ADJ_REV");
        initializeTempTables(selection, accSelection);
        createWindow(new AddAccountConfig("Add Account Configuration", selection, accSelection));
    }

    /**
     * @throws Exception Add Button Listener For Sear
     *
     * @param event
     * @throws
     */
    @UiHandler("editBtn")
    public void editButtonLogic(Button.ClickEvent event) {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.NO_SEARCH_CRITERIA, ARMMessages.getSelect_Msg_002());
        } else {
            sessionDTO.setMode(ARMUtils.EDIT);
            SessionDTO selection = createSessionId();
            accSelection = new AccountConfigSelection();
            accSelection.setSession(selection);
            selection.setScreenName("EDIT");
            initializeTempTables(selection, accSelection);
            AccountConfigDTO searchAccConfigDto = (AccountConfigDTO) resultsTable.getValue();
            accSelection.setSearchAccConfigDTO(searchAccConfigDto);
            EditAccountConfig editAccountConfig = new EditAccountConfig(CommonConstant.ACCOUNT_CONFIGURATION, selection, accSelection);
            createWindow(editAccountConfig);
            editAccountConfig.addCloseListener(new Window.CloseListener() {

                @Override
                public void windowClose(Window.CloseEvent e) {
                    try {
                        searchLogic();
                    } catch (FieldGroup.CommitException ex) {
                        GTNLOGGER.error(ex.getMessage());
                    }
                }

            });
        }
    }

    /**
     * Add Button Listener For Sear
     *
     * @param event
     * @throws java.lang.CloneNotSupportedException
     */
    @UiHandler("viewBtn")
    public void viewButtonLogic(Button.ClickEvent event) {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.NO_SEARCH_CRITERIA, ARMMessages.getSelect_Msg_002());
        } else {
            sessionDTO.setMode(ARMUtils.VIEW);
            SessionDTO selection = createSessionId();
            accSelection = new AccountConfigSelection();
            accSelection.setSession(selection);
            selection.setScreenName("VIEW");
            AccountConfigDTO searchAccConfigDto = (AccountConfigDTO) resultsTable.getValue();
            accSelection.setSearchAccConfigDTO(searchAccConfigDto);
            createWindow(new ViewAccountConfig(CommonConstant.ACCOUNT_CONFIGURATION, selection, accSelection, ((AccountConfigDTO) resultsTable.getValue())));

        }
    }

    /**
     * Add Button Listener For Search
     *
     * @param event
     * @throws Exception
     * @throws java.lang.CloneNotSupportedException
     */
    @UiHandler("copyBtn")
    public void copyButtonLogic(Button.ClickEvent event) {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.NO_SEARCH_CRITERIA, ARMMessages.getSelect_Msg_002());
        } else {
            sessionDTO.setMode(ARMUtils.COPY);
            SessionDTO selection = createSessionId();
            accSelection = new AccountConfigSelection();
            accSelection.setSession(selection);
            selection.setScreenName("COPY");
            initializeTempTables(selection, accSelection);
            AccountConfigDTO searchAccConfigDto = (AccountConfigDTO) resultsTable.getValue();
            accSelection.setSearchAccConfigDTO(searchAccConfigDto);
            createWindow(new CopyAccountConfig(CommonConstant.ACCOUNT_CONFIGURATION, selection, accSelection));
        }
    }

    /**
     * Add Button Listener For Search
     *
     * @param event
     * @throws java.lang.CloneNotSupportedException
     */
    @UiHandler("deleteBtn")
    public void deleteButtonLogic(Button.ClickEvent event) {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.NO_SEARCH_CRITERIA, ARMMessages.getSelect_Msg_002());
        } else {

            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        AccountConfigDTO searchAccConfigDto = (AccountConfigDTO) resultsTable.getValue();
                        Boolean isDeleted = logic.deleteAccountConfigCombination(searchAccConfigDto.getMasterId());
                        accountConfigSearchTableLogic.loadsetData(Boolean.TRUE, binderDto);
                        resultsTable.setValue(null);
                        if (isDeleted) {
                            CommonUtils.successNotification("The Record is deleted");
                        }
                    } catch (Exception ex) {
                        GTNLOGGER.error("Error in deleteButtonLogic :", ex);
                    }
                }

                @Override
                public void noMethod() {
                    GTNLOGGER.debug("Inside the deleteButtonLogic Listener NO Method");
                }
            }.getConfirmationMessage("Confirmation", "Are you sure you want to delete record ? ");
        }
    }

    /**
     *
     * @return @throws CloneNotSupportedException
     */
    private SessionDTO createSessionId() {
        SessionDTO selection = sessionDTO.getSessionDTO(this.sessionDTO);
        Date sessionDate = new Date();
        selection.setSessionId(Integer.valueOf(ARMUtils.getInstance().getFmtID().format(sessionDate)));
        selection.setSessionDate(sessionDate);
        GTNLOGGER.info("UserId-->>{}", VaadinSession.getCurrent().getAttribute("userId").toString());
        selection.setUserId(ARMUtils.getIntegerValue(String.valueOf(VaadinSession.getCurrent().getAttribute("userId"))));
        return selection;
    }

    private void initializeTempTables(SessionDTO session, AccountConfigSelection accSelection) {
        session.setCurrentTableNames(QueryUtils.createTempTables("ARM_ACC_CONFIG", session.getProjectionId(), session.getUserId().toString(), session.getSessionId().toString()));
        accSelection.setTempTableName(session.getCurrentTableNames().get("ST_ARM_ACC_CONFIG"));
    }

    private void createWindow(final AbstractAccountConfig editAccountWindow) {
        editAccountWindow.setClosable(false);
        UI.getCurrent().addWindow(editAccountWindow);
        editAccountWindow.addCloseListener(new CustomWindow.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                closeEditTray(editAccountWindow);
            }
        });
        editAccountWindow.getCloseBtnRes().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                editAccountWindow.closeAccountBtnLogic();
                closeEditTray(editAccountWindow);
            }
        });
    }

    /**
     * Used to close the Edit tray
     *
     * @param editWindow
     */
    private void closeEditTray(CustomWindow editWindow) {
        MinimizeTray tray = editWindow.getMinimizeTray();
        if (tray.getWindowItems() != null && tray.getWindowItems().size() == 1) {
            tray.close();
        }
    }

    private void loadAccountDdlb() {
        int company = companyDdlb.getValue() != null ? Integer.valueOf(String.valueOf(companyDdlb.getValue())) : 0;
        int businessUnit = businessDdlb.getValue() != null ? Integer.valueOf(String.valueOf(businessDdlb.getValue())) : 0;

        boolean isLoad = company != 0 && businessUnit != 0;
        accountDdlb.removeAllItems();
        accountDdlb.setImmediate(true);
        accountDdlb.addItem(GlobalConstants.getSelectOne());
        accountDdlb.setItemCaption(GlobalConstants.getSelectOne(), GlobalConstants.getSelectOne());
        if (isLoad) {
            List inputList = new ArrayList<>();
            inputList.add(company);
            inputList.add(businessUnit);
            List<Object[]> arr = logic.loadAccountDdlb(inputList);
            for (Object[] obj : arr) {
                if (obj[NumericConstants.ONE] != null && obj[NumericConstants.TWO] != null) {
                    accountDdlb.addItem((String) obj[NumericConstants.ONE]);
                    accountDdlb.setItemCaption((String) obj[NumericConstants.ONE], obj[NumericConstants.ONE] + "");
                }
            }
        }
        accountDdlb.setNullSelectionAllowed(true);
        accountDdlb.setNullSelectionItemId(GlobalConstants.getSelectOne());
        accountDdlb.select(GlobalConstants.getSelectOne());
    }

    @UiHandler("excelBtn")
    public void excelExport(Button.ClickEvent event) {
        try {
            createWorkSheet("Account Config", resultsTable);

        } catch (Exception e) {
            GTNLOGGER.error("Error in excelExport :", e);
        }
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) {
        try {
            long recordCount = 0;
            List<String> visibleList = Arrays.asList(resultsTable.getColumnHeaders());
            if (resultTable.size() != 0) {
                binderDto.setCount(true);
                binderDto.setFilters(accountConfigSearchTableLogic.getFilters());
                recordCount = logic.getAccountConfigCount(binderDto);
            }
            ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.replace(" ", String.valueOf(ARMUtils.UNDERSCORE)).toUpperCase(Locale.ENGLISH));
        } catch (Exception ex) {
            GTNLOGGER.error("Error in createWorkSheet :", ex);
        }
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {
        binderDto.setStart(start);
        binderDto.setOffset(end);
        binderDto.setCount(false);
        List visibleList = Arrays.asList(resultsTable.getVisibleColumns());
        try {
            if (end != 0) {
                binderDto.setFilters(accountConfigSearchTableLogic.getFilters());
                final List<AccountConfigDTO> searchList = logic.getAccountConfigData(binderDto);
                ExcelExportforBB.createFileContent(visibleList.toArray(), searchList, printWriter);
            }
        } catch (Exception e) {
            GTNLOGGER.error("Error in createWorkSheetContent :", e);
        }
    }

    private void securityForButtons() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, CommonConstant.ACCOUNT_CONFIGURATION + ARMUtils.COMMA_CHAR + "Landing screen");
        if (functionHM.get("addBtn") != null && !(functionHM.get("addBtn")).isFunctionFlag()) {
            addBtn.setVisible(false);
        } else {
            addBtn.setVisible(true);
        }
        if (functionHM.get("editBtn") != null && !(functionHM.get("editBtn")).isFunctionFlag()) {
            editBtn.setVisible(false);
        } else {
            editBtn.setVisible(true);
        }
        if (functionHM.get("viewBtn") != null && !(functionHM.get("viewBtn")).isFunctionFlag()) {
            viewBtn.setVisible(false);
        } else {
            viewBtn.setVisible(true);
        }
        if (functionHM.get("copyBtn") != null && !(functionHM.get("copyBtn")).isFunctionFlag()) {
            copyBtn.setVisible(false);
        } else {
            copyBtn.setVisible(true);
        }
        if (functionHM.get("deleteBtn") != null && !(functionHM.get("deleteBtn")).isFunctionFlag()) {
            deleteBtn.setVisible(false);
        } else {
            deleteBtn.setVisible(true);
        }
        securityForButton(functionHM);
    }

    private void securityForFields() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHMforFields = stplSecurity.getBusinessFieldPermission(userId, CommonConstant.ACCOUNT_CONFIGURATION + ARMUtils.COMMA_CHAR + CommonConstant.LANDING_SCREEN);
        configureFieldPermission(functionHMforFields);
        if (functionHMforFields.get("companyDdlb") != null && !(functionHMforFields.get("companyDdlb")).isFunctionFlag()) {
            companyDdlb.setVisible(false);
            labelCompanyRes.setVisible(false);

        } else {
            companyDdlb.setVisible(true);

            labelCompanyRes.setVisible(true);

        }
        if (functionHMforFields.get("businessDdlb") != null && !(functionHMforFields.get("businessDdlb")).isFunctionFlag()) {
            businessDdlb.setVisible(false);
            labelBusinessRes.setVisible(false);
        } else {
            businessDdlb.setVisible(true);
            labelBusinessRes.setVisible(true);

        }
        if (functionHMforFields.get("accountDdlb") != null && !(functionHMforFields.get("accountDdlb")).isFunctionFlag()) {
            accountDdlb.setVisible(false);
            labelAccount.setVisible(false);

        } else {
            accountDdlb.setVisible(true);
            labelAccount.setVisible(true);

        }
        securityForField(functionHMforFields);
    }

    private void configureFieldPermission(Map<String, AppPermission> functionHMforFields) {
        GTNLOGGER.debug("Entering configurePermission");
        try {
            List<Object> resultList = logic.getFieldsForSecurity(CommonConstant.ACCOUNT_CONFIGURATION, CommonConstant.LANDING_SCREEN);
            commonSecurity.removeComponentOnPermission(resultList, resultsTableLayout, functionHMforFields, CommonSecurityLogic.ADD);
        } catch (Exception ex) {
            GTNLOGGER.error("Error in configureFieldPermission:", ex);
        }
        GTNLOGGER.debug("Ending configurePermission");

    }

    private void securityForTables() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, CommonConstant.ACCOUNT_CONFIGURATION + ARMUtils.COMMA_CHAR + CommonConstant.LANDING_SCREEN, false);
        List<Object> resultList = logic.getFieldsForSecurity(CommonConstant.ACCOUNT_CONFIGURATION, CommonConstant.LANDING_SCREEN);
        Object[] obj = ARMUtils.getAccountConfigSearchColumns();
        TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, obj, fieldIfpHM, CommonSecurityLogic.ADD);
        if (tableResultCustom.getObjResult().length > 0) {
            resultsTable.markAsDirty();
            resultsTable.setContainerDataSource(resultsContainer);
            resultsTable.setVisibleColumns(tableResultCustom.getObjResult());
            resultsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } else {
            resultsTableLayout.setVisible(false);
        }
    }

    @Override
    public boolean equals(Object searchAccObj) {
        return super.equals(searchAccObj);
    }

    @Override
    public int hashCode() {
        GTNLOGGER.debug("Enters the AccountConfig Hashcode");
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream searchAccObj) throws IOException {
        searchAccObj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream searchAccObj) throws IOException, ClassNotFoundException {
        searchAccObj.defaultReadObject();
    }

    private void securityForField(Map<String, AppPermission> functionHMforFields) {
        if (functionHMforFields.get("brandDdlb") != null && !(functionHMforFields.get("brandDdlb")).isFunctionFlag()) {
            brandDdlb.setVisible(false);
            labelbrandDdlb.setVisible(false);

        } else {
            brandDdlb.setVisible(true);
            labelbrandDdlb.setVisible(true);

        }
        if (functionHMforFields.get("costCentre") != null && !(functionHMforFields.get("costCentre")).isFunctionFlag()) {
            costCentre.setVisible(false);
            labelcostCentre.setVisible(false);
        } else {
            costCentre.setVisible(true);
            labelcostCentre.setVisible(true);

        }
    }

    private void securityForButton(Map<String, AppPermission> functionHM) {
        if (functionHM.get("excelBtn") != null && !(functionHM.get("excelBtn")).isFunctionFlag()) {
            excelBtn.setVisible(false);
        } else {
            excelBtn.setVisible(true);
        }
        if (functionHM.get("resetBtn") != null && !(functionHM.get("resetBtn")).isFunctionFlag()) {
            resetBtn.setVisible(false);
        } else {
            resetBtn.setVisible(true);
        }
        if (functionHM.get("searchBtn") != null && !(functionHM.get("searchBtn")).isFunctionFlag()) {
            searchBtn.setVisible(false);
        } else {
            searchBtn.setVisible(true);
        }
    }
}
