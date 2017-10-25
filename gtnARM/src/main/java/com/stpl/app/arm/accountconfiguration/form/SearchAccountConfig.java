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
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.customwindow.MinimizeTray;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar.Raju
 */
public class SearchAccountConfig extends CustomComponent {

    SessionDTO sessionDTO;

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
    AccountConfigSearchTableLogic accountConfigSearchTableLogic = new AccountConfigSearchTableLogic();
    /**
     * The Results Table for Search Screen in Reserve Configurations
     */
    ExtPagedTable resultsTable = new ExtPagedTable(accountConfigSearchTableLogic);

    Map<Integer, String> userMap = null;
    /**
     * The Logic Class for the Adjustment and Reserve Configuration
     */
    AccountConfigLogic logic = AccountConfigLogic.getInstance();

    /**
     * The Container For Results Table in Search Screen in Reserve Configuration
     */
    private final BeanItemContainer<AccountConfigDTO> resultsContainer = new BeanItemContainer<>(AccountConfigDTO.class);

    private AccountConfigDTO binderDto = new AccountConfigDTO();
    private final CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(binderDto));
    public static final Logger LOGGER = Logger.getLogger(SearchAccountConfig.class);
    private AccountConfigSelection accSelection;
    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();

    /**
     * The Constructor for Search Screen in Adjustment Reserve Configuration.
     *
     * @param sessionDTO
     * @throws java.lang.Exception
     */
    public SearchAccountConfig(SessionDTO sessionDTO) throws Exception {
        this.sessionDTO = sessionDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/account_config/search-account-config.xml"), this));
        init();
    }

    /**
     * Inital Method invocation on Index search class load
     *
     * @throws Exception
     */
    private void init() throws Exception {
        configureFields();
        securityForButtons();
        securityForFields();
        securityForTables();

    }

    /**
     * Configuring all UI components and its properties
     *
     * @throws Exception
     */
    private void configureFields() throws Exception {
        getBinder();
        userMap = AccountConfigLogic.getUserName();
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
        resultsTable.setSelectable(Boolean.TRUE);
        resultsTable.setMultiSelect(Boolean.FALSE);
        accountConfigSearchTableLogic.setPageLength(NumericConstants.TEN);
        accountConfigSearchTableLogic.sinkItemPerPageWithPageLength(Boolean.FALSE);
        resultsTable.setVisibleColumns(ARMUtils.ACCOUNT_CONFIG_SEARCH_COLUMNS);
        resultsTable.setColumnHeaders(ARMUtils.ACCOUNT_CONFIG_SEARCH_HEADERS);
        for (Object visibleColumns : resultsTable.getVisibleColumns()) {
            String value = visibleColumns.toString();
            if (value.endsWith("Date")) {
                resultsTable.setColumnAlignment(visibleColumns, ExtCustomTable.Align.CENTER);
            }
        }
        resultsTable.setSizeFull();
        resultsTable.setImmediate(Boolean.TRUE);
        resultsTable.setPageLength(NumericConstants.TEN);

        resultsTable.addStyleName(ARMUtils.FILTERCOMBOBOX);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.addStyleName(ARMUtils.CENTER_CHECK);
        resultsTable.setConverter("createdDate", new DateToStringConverter());
        resultsTable.setConverter("modifiedDate", new DateToStringConverter());

        resultsTable.addStyleName("filterbar");

        resultsTable.setFilterBarVisible(true);
    }

    @UiHandler("resetBtn")
    public void resetButtonLogic(Button.ClickEvent event) throws Exception {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    binderDto = new AccountConfigDTO();
                    binder.setItemDataSource(new BeanItem<>(binderDto));
                    binder.commit();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Confirmation", ARMMessages.getResetMessageID003());

    }

    /**
     * .
     * Loading UI Drop down values
     *
     */
    private void loadingDropDown() {
        CommonLogic.configureDropDowns(companyDdlb, "getCompanyQuery", Boolean.TRUE);
        CommonLogic.configureDropDowns(businessDdlb, "getBusinessQuery", Boolean.TRUE);
        CommonLogic.configureDropDowns(brandDdlb, "loadBrand", Boolean.TRUE);
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
    public void searchBtnLogic(Button.ClickEvent event) throws CloneNotSupportedException, FieldGroup.CommitException {
        binder.commit();
        if (binderDto.getCompanyDdlb() != 0 || binderDto.getBusinessDdlb() != 0 || binderDto.getBrandDdlb() != 0 || !binderDto.getCostCentre().isEmpty()
                || !binderDto.getAccount().isEmpty()) {
            if (!accountConfigSearchTableLogic.loadsetData(Boolean.TRUE, binderDto)) {
                CommonUtils.successNotification(ARMMessages.getNoResultsFoundMessage());
            }
            resultsTable.setValue(null);
        } else {
            AbstractNotificationUtils.getErrorNotification("No Search Criteria", ARMMessages.getSearchMsg_001());
            accountConfigSearchTableLogic.loadsetData(Boolean.FALSE, binderDto);
            accountConfigSearchTableLogic.getFilters().clear();
        }
    }

    /**
     * Add Button Listener For Sear
     *
     * @param event
     * @throws java.lang.CloneNotSupportedException
     */
    @UiHandler("addBtn")
    public void addButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException,Exception {
        sessionDTO.setMode(ARMUtils.ADD);
        SessionDTO selection = createSessionId();
        accSelection = new AccountConfigSelection();
        accSelection.setSession(selection);
        selection.setScreenName("ARM_ADJ_REV");
        initializeTempTables(selection, accSelection);
        createWindow(new AddAccountConfig("Add Account Configuration", selection, accSelection));
    }

    /**
     * Add Button Listener For Sear
     *
     * @param event
     * @throws java.lang.CloneNotSupportedException
     */
    @UiHandler("editBtn")
    public void editButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException,Exception {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No Search Criteria", ARMMessages.getSelect_Msg_002());
        } else {
            sessionDTO.setMode(ARMUtils.EDIT);
            SessionDTO selection = createSessionId();
            accSelection = new AccountConfigSelection();
            accSelection.setSession(selection);
            selection.setScreenName("EDIT");
            initializeTempTables(selection, accSelection);
            AccountConfigDTO searchAccConfigDto = (AccountConfigDTO) resultsTable.getValue();
            accSelection.setSearchAccConfigDTO(searchAccConfigDto);
            createWindow(new EditAccountConfig("Account Configuration", selection, accSelection));
        }
    }

    /**
     * Add Button Listener For Sear
     *
     * @param event
     * @throws java.lang.CloneNotSupportedException
     */
    @UiHandler("viewBtn")
    public void viewButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException,Exception {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No Search Criteria", ARMMessages.getSelect_Msg_002());
        } else {
            sessionDTO.setMode(ARMUtils.VIEW);
            SessionDTO selection = createSessionId();
            accSelection = new AccountConfigSelection();
            accSelection.setSession(selection);
            selection.setScreenName("VIEW");
            AccountConfigDTO searchAccConfigDto = (AccountConfigDTO) resultsTable.getValue();
            accSelection.setSearchAccConfigDTO(searchAccConfigDto);
            createWindow(new ViewAccountConfig("Account Configuration", selection, accSelection, ((AccountConfigDTO) resultsTable.getValue())));
        }
    }

    /**
     * Add Button Listener For Search
     *
     * @param event
     * @throws java.lang.CloneNotSupportedException
     */
    @UiHandler("copyBtn")
    public void copyButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException,Exception {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No Search Criteria", ARMMessages.getSelect_Msg_002());
        } else {
            sessionDTO.setMode(ARMUtils.COPY);
            SessionDTO selection = createSessionId();
            accSelection = new AccountConfigSelection();
            accSelection.setSession(selection);
            selection.setScreenName("COPY");
            initializeTempTables(selection, accSelection);
            AccountConfigDTO searchAccConfigDto = (AccountConfigDTO) resultsTable.getValue();
            accSelection.setSearchAccConfigDTO(searchAccConfigDto);
            createWindow(new CopyAccountConfig("Account Configuration", selection, accSelection));
        }
    }

    /**
     * Add Button Listener For Search
     *
     * @param event
     * @throws java.lang.CloneNotSupportedException
     */
    @UiHandler("deleteBtn")
    public void deleteButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("No Search Criteria", ARMMessages.getSelect_Msg_002());
        } else {
            AccountConfigDTO searchAccConfigDto = (AccountConfigDTO) resultsTable.getValue();

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
                        LOGGER.error(ex);
                    }
                }

                @Override
                public void noMethod() {
                }
            }.getConfirmationMessage("Confirmation", "Are you sure you want to delete record ? ");
        }
    }

    /**
     *
     * @return @throws CloneNotSupportedException
     */
    private SessionDTO createSessionId() throws CloneNotSupportedException {
        SessionDTO selection = this.sessionDTO.clone();
        Date sessionDate = new Date();
        selection.setSessionId(Integer.valueOf(ARMUtils.FMT_ID.format(sessionDate)));
        selection.setSessionDate(sessionDate);
        LOGGER.info("UserId-->>" + String.valueOf(VaadinSession.getCurrent().getAttribute("userId")));
        selection.setUserId(Integer.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute("userId"))));
        return selection;
    }

    private void initializeTempTables(SessionDTO session, AccountConfigSelection accSelection) {
        session.setCurrentTableNames(QueryUtils.createTempTables("ARM_ACC_CONFIG", session.getProjectionId(), session.getUserId().toString(), session.getSessionId().toString()));
        accSelection.setTempTableName(session.getCurrentTableNames().get("ST_ARM_ACC_CONFIG"));
    }

    private void createWindow(final AbstractAccountConfig editWindow) {
        editWindow.setClosable(false);
        UI.getCurrent().addWindow(editWindow);
        editWindow.addCloseListener(new CustomWindow.CloseListener() {

            @Override
            public void windowClose(Window.CloseEvent e) {
                closeEditTray(editWindow);
            }
        });
        editWindow.getCloseBtnRes().addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                editWindow.closeBtnLogic();
                closeEditTray(editWindow);
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
            if (resultsContainer.size() > 0) {
                createWorkSheet("Account Config", resultsTable);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
            ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.replace(" ", "_").toUpperCase());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
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
            LOGGER.error(e.getMessage());
        }
    }

    private void securityForButtons() throws Exception {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(sessionDTO.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Account Configuration" + "," + "Landing screen");
            if (functionHM.get("addBtn") != null && !((AppPermission) functionHM.get("addBtn")).isFunctionFlag()) {
                addBtn.setVisible(false);
            } else {
                addBtn.setVisible(true);
            }
            if (functionHM.get("editBtn") != null && !((AppPermission) functionHM.get("editBtn")).isFunctionFlag()) {
                editBtn.setVisible(false);
            } else {
                editBtn.setVisible(true);
            }
            if (functionHM.get("viewBtn") != null && !((AppPermission) functionHM.get("viewBtn")).isFunctionFlag()) {
                viewBtn.setVisible(false);
            } else {
                viewBtn.setVisible(true);
            }
            if (functionHM.get("copyBtn") != null && !((AppPermission) functionHM.get("copyBtn")).isFunctionFlag()) {
                copyBtn.setVisible(false);
            } else {
                copyBtn.setVisible(true);
            }
            if (functionHM.get("deleteBtn") != null && !((AppPermission) functionHM.get("deleteBtn")).isFunctionFlag()) {
                deleteBtn.setVisible(false);
            } else {
                deleteBtn.setVisible(true);
            }
            if (functionHM.get("excelBtn") != null && !((AppPermission) functionHM.get("excelBtn")).isFunctionFlag()) {
                excelBtn.setVisible(false);
            } else {
                excelBtn.setVisible(true);
            }
            if (functionHM.get("resetBtn") != null && !((AppPermission) functionHM.get("resetBtn")).isFunctionFlag()) {
                resetBtn.setVisible(false);
            } else {
                resetBtn.setVisible(true);
            }
            if (functionHM.get("searchBtn") != null && !((AppPermission) functionHM.get("searchBtn")).isFunctionFlag()) {
                searchBtn.setVisible(false);
            } else {
                searchBtn.setVisible(true);
            }
    }

    private void securityForFields() throws Exception {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHMforFields = stplSecurity.getBusinessFieldPermission(userId, "Account Configuration" + "," + "Landing Screen");
        configureFieldPermission(functionHMforFields);
        if (functionHMforFields.get("companyDdlb") != null && !((AppPermission) functionHMforFields.get("companyDdlb")).isFunctionFlag()) {
            companyDdlb.setVisible(false);
            labelCompanyRes.setVisible(false);

        } else {
            companyDdlb.setVisible(true);

            labelCompanyRes.setVisible(true);

        }
        if (functionHMforFields.get("businessDdlb") != null && !((AppPermission) functionHMforFields.get("businessDdlb")).isFunctionFlag()) {
            businessDdlb.setVisible(false);
            labelBusinessRes.setVisible(false);
        } else {
            businessDdlb.setVisible(true);
            labelBusinessRes.setVisible(true);

        }
        if (functionHMforFields.get("accountDdlb") != null && !((AppPermission) functionHMforFields.get("accountDdlb")).isFunctionFlag()) {
            accountDdlb.setVisible(false);
            labelAccount.setVisible(false);

        } else {
            accountDdlb.setVisible(true);
            labelAccount.setVisible(true);

        }
        if (functionHMforFields.get("brandDdlb") != null && !((AppPermission) functionHMforFields.get("brandDdlb")).isFunctionFlag()) {
            brandDdlb.setVisible(false);
            labelbrandDdlb.setVisible(false);

        } else {
            brandDdlb.setVisible(true);
            labelbrandDdlb.setVisible(true);

        }
        if (functionHMforFields.get("costCentre") != null && !((AppPermission) functionHMforFields.get("costCentre")).isFunctionFlag()) {
            costCentre.setVisible(false);
            labelcostCentre.setVisible(false);
        } else {
            costCentre.setVisible(true);
            labelcostCentre.setVisible(true);

        }
    }

    private void configureFieldPermission(Map<String, AppPermission> functionHMforFields) {
        LOGGER.debug("Entering configurePermission");
        try {
            List<Object> resultList = logic.getFieldsForSecurity("Account Configuration", "Landing Screen");
            commonSecurity.removeComponentOnPermission(resultList, resultsTableLayout, functionHMforFields, CommonSecurityLogic.ADD);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Ending configurePermission");

    }

    private void securityForTables() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, "Account Configuration" + "," + "Landing Screen", false);
        List<Object> resultList = logic.getFieldsForSecurity("Account Configuration", "Landing Screen");
        Object[] obj = ARMUtils.ACCOUNT_CONFIG_SEARCH_COLUMNS;
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

}
