/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.form;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentReserveLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.tablelogic.ReserveSearchTableLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.ui.abstractreserveform.AbstractReserve;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.DateToStringConverter;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.customwindow.MinimizeTray;
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
import java.util.concurrent.ExecutorService;

/**
 * The Adjustment Reserve Configuration shortens close cycle time for
 * forecasting, increases profitability, optimizes contracting strategies,
 * improves forecasting accuracy, enhances insights to price changes, reduces
 * risk & compliance issues while improving overall operational efficiency and
 * mitigating against large reserve adjustments.This Form Class used For Search
 * Screen in Adjustment Reserve Configuration. The Constants and Window Messages
 * are from Property files in GALGTNProperties.'Res'is appended in each of the
 * fields to represent unique combination of property Id.
 *
 * @author sathyaseelan.v
 */
public class AdjustmentReserveSearchForm extends CustomComponent {

    /**
     * The Table Layout in Reserve Configuration
     */
    @UiField("resultsTableLayoutRes")
    private VerticalLayout resultsTableLayoutRes;
    /**
     * The Company Drop down for Search Screen in Reserve Configuration in which
     * the Options will be all values from Company Master, Company Type = GLcomp
     * The DDLB needs to show the combination of “COMPANY ID – COMPANY NAME”.
     */
    @UiField("companyDdlbRes")
    private ComboBox companyDdlbRes;
    /**
     * The Business Drop down for Search Screen in Reserve Configuration in
     * which the Options will be any Company Master record where Company Type =
     * Business Unit.The DDLB needs to show the combination of “COMPANY ID –
     * COMPANY NAME”.
     */
    @UiField("businessDdlbRes")
    private ComboBox businessDdlbRes;
    /**
     * The DeductionCategory Drop down for Search Screen in Reserve
     * Configuration links to the Rebate Schedule Category data attribute. Will
     * show all distinct values of the Rebate Schedule Category data attribute.
     */
    @UiField("deductionCategoryDdlbRes")
    private ComboBox deductionCategoryDdlbRes;
    /**
     * The Deduction Type Drop down for Search Screen in Reserve Configuration
     * links to the Rebate Schedule Type data attribute. Will show all distinct
     * values of the Rebate Schedule Type data attribute. The values listed in
     * the DDLB will be filtered based on the value selected for Deduction
     * Category. Based on the value selected in Deduction Category the DDLB will
     * only show the distinct values that are associated with rebate schedules
     * that have value selected in the “Deduction Category” DDLB.
     */
    @UiField("deductionTypeDdlbRes")
    private ComboBox deductionTypeDdlbRes;
    /**
     * The Deduction Program Drop down for Search Screen in Reserve
     * Configuration links to the Rebate Program Type data attribute. Will show
     * all distinct values of the Rebate Program Type data attribute. The values
     * listed in the DDLB will be filtered based on the value selected for
     * Deduction Type. Based on the value selected in Deduction Category the
     * DDLB will only show the distinct values that are associated with rebate
     * schedules that have value selected in the “Deduction Category” DDLB.
     */
    @UiField("deductionProgramDdlbRes")
    private ComboBox deductionProgramDdlbRes;
    /**
     * The Layouts Hidden for Search Screen in Reserve Configuration
     */
    @UiField("addFormLayoutRes")
    private HorizontalLayout addFormLayoutRes;
    /**
     * The Panels Hidden for Search Screen in Reserve Configuration
     */
    @UiField("addPanelRes")
    private Panel addPanelRes;

    @UiField("excelBtnRes")
    public Button excelBtnRes;
    /**
     * The Search Results Table Logic in Reserve Configuration
     */
    ReserveSearchTableLogic adjustReserveConfigTableLogic = new ReserveSearchTableLogic();
    /**
     * The Results Table for Search Screen in Reserve Configurations
     */
    ExtPagedTable resultsTable = new ExtPagedTable(adjustReserveConfigTableLogic);
    /**
     * The Container For Results Table in Search Screen in Reserve Configuration
     */
    private final BeanItemContainer<AdjustmentReserveDTO> availableResultsContainer = new BeanItemContainer<>(AdjustmentReserveDTO.class);
    /**
     * The Session DTO
     */
    SessionDTO sessionDTO;
    /**
     * The Logic Class for the Adjustment and Reserve Configuration
     */
    AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();

    AdjustmentReserveDTO binderDto = new AdjustmentReserveDTO();
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<AdjustmentReserveDTO>(binderDto));
    public static final Logger LOGGER = Logger.getLogger(AdjustmentReserveSearchForm.class);
    Map<Integer, String> userMap = null;
    ReserveSelection resSelection;
    ExecutorService service = ThreadPool.getInstance().getService();

    /**
     * The Constructor for Search Screen in Adjustment Reserve Configuration.
     *
     * @param sessionDTO
     */
    public AdjustmentReserveSearchForm(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/adjustment_reserve_config/adjustment-reserve-form.xml"), this));
        companyDdlbRes.focus();
        configureFields();
    }

    /**
     * Configuring Fields for Search Form
     */
    private void configureFields() {
        try {
            CommonLogic.configureDropDowns(companyDdlbRes, "getCompanyQuery", Boolean.TRUE);
            CommonLogic.configureDropDowns(businessDdlbRes, "getBusinessQuery", Boolean.TRUE);
            CommonUtils.loadComboBoxWithIntegerForComboBox(deductionCategoryDdlbRes, "RS_CATEGORY", false);
            CommonUtils.loadComboBoxWithIntegerForComboBox(deductionTypeDdlbRes, "RS_TYPE", false);
            CommonUtils.loadComboBoxWithIntegerForComboBox(deductionProgramDdlbRes, "REBATE_PROGRAM_TYPE", false);
            configureOnSearch();
            userMap = logic.getUserName();
            configureTable();
            excelBtnRes.setPrimaryStyleName("link");
            excelBtnRes.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * Configuring Result Table for Search Screen
     */
    private void configureTable() {
        resultsTableLayoutRes.addComponent(resultsTable);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setSelectable(true);
        resultsTable.setFilterGenerator(new ExtFilterGenerator() {

            public Container.Filter generateFilter(Object propertyId, Object value) {
                if ((propertyId.toString().equals("createdBy")) && (value != null)) {
                    return new SimpleStringFilter(propertyId, String.valueOf(value), false, false);
                }
                return null;
            }

            @Override
            public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
                String value = null;
                if ((originatingField instanceof ComboBox) && ((originatingField instanceof ComboBox) && (originatingField.getValue() != null))) {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                }
                return generateFilter(propertyId, value);
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
                try {
                    final ComboBox comboBox = new ComboBox();
                    comboBox.setImmediate(true);
                    switch (propertyId.toString()) {
                        case "createdBy":
                            comboBox.addItem(0);
                            comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                            for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                                comboBox.addItem(entry.getKey());
                                comboBox.setItemCaption(entry.getKey(), entry.getValue());
                            }
                            comboBox.setNullSelectionAllowed(true);
                            comboBox.setNullSelectionItemId(0);
                            return comboBox;
                        default:
                            return null;
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
                return null;
            }
        });
        resultsTableLayoutRes.addComponent(getResponsiveControls(adjustReserveConfigTableLogic.createControls()));
        adjustReserveConfigTableLogic.setContainerDataSource(availableResultsContainer);
        resultsTable.setSelectable(Boolean.TRUE);
        resultsTable.setMultiSelect(Boolean.FALSE);
        adjustReserveConfigTableLogic.setPageLength(NumericConstants.TEN);
        adjustReserveConfigTableLogic.sinkItemPerPageWithPageLength(Boolean.FALSE);
        resultsTable.setVisibleColumns(ARMUtils.ADJUSTMENT_RESERVE_SEARCH_COLUMNS);
        resultsTable.setColumnHeaders(ARMUtils.ADJUSTMENT_RESERVE_SEARCH_HEADERS);
        for (Object objColumn1 : resultsTable.getVisibleColumns()) {
            String value = objColumn1.toString();
            if (value.endsWith("Date")) {
                resultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
            }
        }
        resultsTable.setSizeFull();
        resultsTable.setImmediate(Boolean.TRUE);
        resultsTable.setPageLength(NumericConstants.TEN);

        resultsTable.addStyleName("filtertable");
        resultsTable.addStyleName("table-header-normal");
        resultsTable.setConverter("createdDate", new DateToStringConverter());
        resultsTable.setConverter("modifiedDate", new DateToStringConverter());

        resultsTable.addStyleName("filterbar");

        resultsTable.setFilterBarVisible(true);
    }

    /**
     * Search Button Listener For Search Form
     *
     * @param event
     */
    @UiHandler("copyBtnRes")
    public void copyButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSelect_Msg_002());
        } else {
            resSelection = new ReserveSelection();
            AdjustmentReserveDTO dto = (AdjustmentReserveDTO) resultsTable.getValue();
            sessionDTO.setMode(ARMUtils.COPY);
            SessionDTO selection = createSessionId();
            selection.setScreenName("ARM_ADJ_REV");
            createWindow(new CopyAdjustmentReserve(selection, dto, resSelection));
        }
    }

    /**
     * Add Button Listener For Search Form
     *
     * @param event
     */
    @UiHandler("addBtnRes")
    public void addButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException {
        sessionDTO.setMode(ARMUtils.ADD);
        SessionDTO selection = createSessionId();
        resSelection = new ReserveSelection();
        resSelection.setSession(selection);
        selection.setScreenName("ARM_ADJ_REV");
        initializeTempTables(selection, resSelection);
        createWindow(new AddAdjustmentReserve(selection, resSelection));
    }

    /**
     * Edit Button Listener For Search Form
     *
     * @param event
     */
    @UiHandler("editBtnRes")
    public void editButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSelect_Msg_002());
        } else {
            resSelection = new ReserveSelection();
            AdjustmentReserveDTO dto = (AdjustmentReserveDTO) resultsTable.getValue();
            sessionDTO.setMode(ARMUtils.EDIT);
            SessionDTO selection = createSessionId();
            selection.setScreenName("ARM_ADJ_REV");
            createWindow(new EditAdjustmentReserve(selection, dto, resSelection));
        }
    }

    /**
     * View Button Listener For Search Form
     *
     * @param event
     */
    @UiHandler("viewBtnRes")
    public void viewButtonLogic(Button.ClickEvent event) throws CloneNotSupportedException {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSelect_Msg_002());
        } else {
            resSelection = new ReserveSelection();
            AdjustmentReserveDTO dto = (AdjustmentReserveDTO) resultsTable.getValue();
            sessionDTO.setMode(ARMUtils.VIEW);
            SessionDTO selection = createSessionId();
            createWindow(new ViewAdjustmentReserve(selection, dto, resSelection));
        }
    }

    /**
     * Copy Button Listener For Search Form
     *
     * @param event
     * @throws java.lang.CloneNotSupportedException
     * @throws com.vaadin.data.fieldgroup.FieldGroup.CommitException
     */
    @UiHandler("searchBtnRes")
    public void searchBtnResLogic(Button.ClickEvent event) throws CloneNotSupportedException, FieldGroup.CommitException {
        binder.commit();
        if (binderDto.getCompanyDdlbRes() != 0 || binderDto.getBusinessDdlbRes() != 0 || binderDto.getDeductionCategoryDdlbRes() != 0 || binderDto.getDeductionTypeDdlbRes() != 0
                || binderDto.getDeductionProgramDdlbRes() != 0) {
            if (!adjustReserveConfigTableLogic.loadsetData(Boolean.TRUE, binderDto)) {
                CommonUtils.successNotification(ARMMessages.getNoResultsFoundMessage());
            }
            resultsTable.setValue(null);

        } else {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSearchMsg_001());
        }
    }

    /**
     * Delete Button Listener For Search Form
     *
     * @param event
     */
    @UiHandler("deleteBtnRes")
    public void deleteButtonLogic(Button.ClickEvent event) {
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getSelect_Msg_002());
        } else if (logic.deleteAdjustmentDetailsCheck((AdjustmentReserveDTO) resultsTable.getValue())) {
            AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getDeleteMessageId_004());
        } else {
            final AdjustmentReserveDTO dto = (AdjustmentReserveDTO) resultsTable.getValue();
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        logic.deleteReserveMaster(dto.getSearchMasterSid());
                        adjustReserveConfigTableLogic.loadsetData(Boolean.TRUE, binderDto);
                        AbstractNotificationUtils.getInfoNotification("Delete Successful", ARMMessages.getSuccessful_msg_001());
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }

                @Override
                public void noMethod() {
                }

            }.getConfirmationMessage("Confirmation", "Are you sure you want to delete record <" + dto.getCompanyName() + "> <" + dto.getBusinessUnitNo() + "> <" + dto.getDeductionCategory() + "> "
                    + "<" + dto.getDeductionType() + "> <" + dto.getDeductionProgram() + "> ? ");

        }

    }

    /**
     * Removing Layout Components For Search Screen
     */
    private void configureOnSearch() {
        getBinder();
        addFormLayoutRes.setVisible(false);
        addPanelRes.setVisible(false);
    }

    @UiHandler("resetBtnRes")
    public void resetButtonLogic(Button.ClickEvent event) throws SystemException, PortalException {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    binderDto = new AdjustmentReserveDTO();
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

    private void createWindow(final AbstractReserve editWindow) {
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
     *
     * @return @throws CloneNotSupportedException
     */
    private SessionDTO createSessionId() throws CloneNotSupportedException {
        SessionDTO selection = this.sessionDTO.clone();
        Date sessionDate = new Date();
        selection.setSessionId(Integer.valueOf(ARMUtils.FMT_ID.format(sessionDate)));
        selection.setSessionDate(sessionDate);
        LOGGER.debug("UserId-->>" + String.valueOf(VaadinSession.getCurrent().getAttribute("userId")));
        selection.setUserId(Integer.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute("userId"))));
        return selection;
    }

    /**
     * This method will configure the binder for this page.
     *
     * @return
     */
    private CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<AdjustmentReserveDTO>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

//    /**
//     * Excel export button logic.
//     *
//     * @param event
//     */
    @UiHandler("excelBtnRes")
    public void exportButtonLogic(Button.ClickEvent event) throws PortalException, SystemException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        createWorkSheet("Adjustment Reserve", resultsTable);
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        long recordCount = 0;
        List<String> visibleList = Arrays.asList(resultsTable.getColumnHeaders());
        if (resultTable.size() != 0) {
            recordCount = logic.getSearchCount(binderDto, adjustReserveConfigTableLogic.getFilters());
        }
        ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.toUpperCase());
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException {

        List visibleList = Arrays.asList(resultsTable.getVisibleColumns());
        try {
            if (end != 0) {
                final List<AdjustmentReserveDTO> searchList = logic.getSearchResults(binderDto, start, end, adjustReserveConfigTableLogic.getFilters(), adjustReserveConfigTableLogic.getSortByColumns());
                ExcelExportforBB.createFileContent(visibleList.toArray(), searchList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void initializeTempTables(SessionDTO selection, ReserveSelection resSelection) {
        selection.setCurrentTableNames(QueryUtils.createTempTables(selection.getScreenName(), selection.getProjectionId(), selection.getUserId().toString(), selection.getSessionId().toString()));
        resSelection.setTempTableName(selection.getCurrentTableNames().get("ST_ARM_ADJ_RES_CONFIG_DETAIL"));
    }
    }
