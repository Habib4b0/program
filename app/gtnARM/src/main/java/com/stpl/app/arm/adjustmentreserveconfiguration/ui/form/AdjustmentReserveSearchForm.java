/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.ui.form;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentReserveLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.tablelogic.ReserveSearchTableLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.saveaction.DeleteAdjustmentReserveAction;
import com.stpl.app.arm.adjustmentreserveconfiguration.ui.abstractreserveform.AbstractReserve;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.excecutors.ActionExecutor;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.app.util.service.thread.ThreadPool;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import org.asi.ui.extfilteringtable.ExtCustomTable;

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
     * The DDLB needs to show the combination of â€œCOMPANY ID â€“
     * COMPANY NAMEâ€�.
     */
    @UiField("companyDdlbRes")
    private ComboBox companyDdlbRes;
    /**
     * The Business Drop down for Search Screen in Reserve Configuration in
     * which the Options will be any Company Master record where Company Type =
     * Business Unit.The DDLB needs to show the combination of â€œCOMPANY ID
     * â€“ COMPANY NAMEâ€�.
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
     * that have value selected in the â€œDeduction Categoryâ€� DDLB.
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
     * schedules that have value selected in the â€œDeduction
     * Categoryâ€� DDLB.
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

    @UiField("adjSummaryPanel")
    private Panel adjSummaryPanel;

    @UiField("balSummaryPanel")
    protected Panel balSummaryPanel;

    @UiField("excelBtnRes")
    public Button excelBtnRes;
    @UiField("methodologyLayout")
    public HorizontalLayout methodologyLayout;
    @UiField("reportTypeLayout")
    public HorizontalLayout reportTypeLayout;

    /**
     * The Search Results Table Logic in Reserve Configuration
     */
    private ReserveSearchTableLogic adjustReserveConfigTableLogic = new ReserveSearchTableLogic();
    /**
     * The Results Table for Search Screen in Reserve Configurations
     */
    private ExtPagedTable resultsTable = new ExtPagedTable(adjustReserveConfigTableLogic);
    /**
     * The Container For Results Table in Search Screen in Reserve Configuration
     */
    private final BeanItemContainer<AdjustmentReserveDTO> availableResultsContainer = new BeanItemContainer<>(AdjustmentReserveDTO.class);
    /**
     * The Session DTO
     */
    private SessionDTO sessionDTO;
    /**
     * The Logic Class for the Adjustment and Reserve Configuration
     */
    private AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();

    private AdjustmentReserveDTO binderDto = new AdjustmentReserveDTO();
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(binderDto));
    private static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentReserveSearchForm.class);
    private ReserveSelection resSelection;
    private final ExecutorService service = ThreadPool.getInstance().getService();
    private Boolean isValueChangeAllowed;

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
            CommonLogic.configureDropDowns(companyDdlbRes, "getCompanyQuery", true);
            CommonLogic.configureDropDowns(businessDdlbRes, "getBusinessQuery", true);
            CommonLogic.configureDropDownsForDeduction(deductionCategoryDdlbRes, "getDeductionCategory");
            CommonLogic.configureDropDownsForDeduction(deductionTypeDdlbRes, "getDeductionType");
            CommonLogic.configureDropDownsForDeduction(deductionProgramDdlbRes, "getDeductionProgram");

            configureOnSearch();
            configureTable();
            methodologyLayout.setVisible(false);
            reportTypeLayout.setVisible(false);
            excelBtnRes.setPrimaryStyleName("link");
            excelBtnRes.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        } catch (Exception ex) {
            LOGGER.error("Error in configureFields :", ex);
        }
    }

    /**
     * Configuring Result Table for Search Screen
     */
    private void configureTable() {
        resultsTableLayoutRes.addComponent(resultsTable);

        resultsTable.setSelectable(true);
        resultsTable.setFilterGenerator(new ExtFilterGenerator() {

            @Override
            public Container.Filter generateFilter(Object reserveSearchpropertyId, Field<?> originatingField) {
                String value = null;
                if ((originatingField instanceof ComboBox) && ((originatingField instanceof ComboBox) && (originatingField.getValue() != null))) {
                    return new SimpleStringFilter(reserveSearchpropertyId, String.valueOf(originatingField.getValue()), false, false);
                }
                return generateFilter(reserveSearchpropertyId, value);
            }

            @Override
            public Container.Filter generateFilter(Object reserveSearchpropertyId, Object value) {
                return null;
            }

            @Override
            public void filterRemoved(Object reserveSearchpropertyId) {
                LOGGER.debug("filterRemoved Method:");
            }

            @Override
            public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object reserveSearchpropertyId) {
                LOGGER.debug("filterAdded Method:");
            }

            @Override
            public Container.Filter filterGeneratorFailed(Exception reason, Object reserveSearchpropertyId, Object value) {
                return null;
            }

            @Override
            public AbstractField<?> getCustomFilterComponent(Object reserveSearchpropertyId) {
                return null;

            }

        });
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTableLayoutRes.addComponent(getResponsiveControls(adjustReserveConfigTableLogic.createControls()));
        adjustReserveConfigTableLogic.setContainerDataSource(availableResultsContainer);
        resultsTable.setSelectable(true);
        resultsTable.setMultiSelect(false);
        adjustReserveConfigTableLogic.setPageLength(NumericConstants.TEN);
        adjustReserveConfigTableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(ARMUtils.getAdjustmentReserveSearchColumns());
        resultsTable.setColumnHeaders(ARMUtils.getAdjustmentReserveSearchHeaders());
        for (Object objColumn1 : resultsTable.getVisibleColumns()) {
            String value = objColumn1.toString();
            if (value.endsWith("Date")) {
                resultsTable.setColumnAlignment(objColumn1, ExtCustomTable.Align.CENTER);
            }
        }
        resultsTable.setSizeFull();
        resultsTable.setImmediate(true);
        resultsTable.setPageLength(NumericConstants.TEN);

        resultsTable.addStyleName("filtertable");
        resultsTable.addStyleName("table-header-normal");

        resultsTable.addStyleName("filterbar");

        resultsTable.setFilterBarVisible(true);
    }

    /**
     * Search Button Listener For Search Form
     *
     * @param event
     */
    @UiHandler("copyBtnRes")
    public void copyButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSelect_Msg_002());
        } else {
            resSelection = new ReserveSelection();
            AdjustmentReserveDTO dto = (AdjustmentReserveDTO) resultsTable.getValue();
            sessionDTO.setMode(ARMUtils.COPY);
            SessionDTO selection = createSessionId();
            selection.setScreenName(CommonConstant.ARM_ADJ_REV);
            createWindow(new CopyAdjustmentReserve(selection, dto, resSelection));
        }
    }

    /**
     * Add Button Listener For Search Form
     *
     * @param event
     */
    @UiHandler("addBtnRes")
    public void addButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        sessionDTO.setMode(ARMUtils.ADD);
        SessionDTO selection = createSessionId();
        resSelection = new ReserveSelection();
        resSelection.setSession(selection);
        selection.setScreenName(CommonConstant.ARM_ADJ_REV);
        initializeTempTables(selection, resSelection);
        createWindow(new AddAdjustmentReserve(selection, resSelection));
    }

    /**
     * Edit Button Listener For Search Form
     *
     * @param event
     */
    @UiHandler("editBtnRes")
    public void editButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSelect_Msg_002());
        } else {
            resSelection = new ReserveSelection();
            AdjustmentReserveDTO dto = (AdjustmentReserveDTO) resultsTable.getValue();
            sessionDTO.setMode(ARMUtils.EDIT);
            SessionDTO selection = createSessionId();
            selection.setScreenName(CommonConstant.ARM_ADJ_REV);
            resSelection.setSession(selection);
            createWindow(new EditAdjustmentReserve(selection, dto, resSelection));
        }
    }

    /**
     * View Button Listener For Search Form
     *
     * @param event
     */
    @UiHandler("viewBtnRes")
    public void viewButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSelect_Msg_002());
        } else {
            resSelection = new ReserveSelection();
            AdjustmentReserveDTO dto = (AdjustmentReserveDTO) resultsTable.getValue();
            sessionDTO.setMode(ARMUtils.VIEW);
            SessionDTO selection = createSessionId();
            selection.setScreenName(CommonConstant.ARM_ADJ_REV);
            resSelection.setSession(selection);
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
    public void searchBtnResLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        LOGGER.debug(event.toString());
        binder.commit();
        if (!mandatoryCheckForSearch()) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSearchMsg_001());
        } else {
            if (!adjustReserveConfigTableLogic.loadsetData(true, binderDto)) {
                CommonUtils.successNotification(ARMMessages.getNoResultsFoundMessage());
            }
            resultsTable.setValue(null);
        }
    }

    /**
     * Delete Button Listener For Search Form
     *
     * @param event
     */
    @UiHandler("deleteBtnRes")
    public void deleteButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        if (resultsTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getSelect_Msg_002());
        } else if (logic.deleteAdjustmentDetailsCheck((AdjustmentReserveDTO) resultsTable.getValue())) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getDeleteMessageId_004());
        } else {
            final AdjustmentReserveDTO dto = (AdjustmentReserveDTO) resultsTable.getValue();
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        ActionExecutor executor = new ActionExecutor();
                        executor.callingActionExecution(new DeleteAdjustmentReserveAction(dto.getSearchMasterSid()));
                        adjustReserveConfigTableLogic.loadsetData(true, binderDto);
                        AbstractNotificationUtils.getInfoNotification("Delete Successful", ARMMessages.getSuccessful_msg_001());
                    } catch (Exception ex) {
                        LOGGER.error("Error in deleteButtonLogic :", ex);
                    }
                }

                @Override
                public void noMethod() {
                    LOGGER.debug("noMethod Method:");
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
        adjSummaryPanel.setVisible(false);
        balSummaryPanel.setVisible(false);
    }

    @UiHandler("resetBtnRes")
    public void resetButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    binderDto = new AdjustmentReserveDTO();
                    binder.setItemDataSource(new BeanItem<>(binderDto));
                    binder.commit();
                } catch (Exception ex) {
                    LOGGER.error("Error in ", ex);
                }
            }

            @Override
            public void noMethod() {
                LOGGER.debug("noMethod Method:");
            }
        }.getConfirmationMessage("Confirmation", ARMMessages.getResetMessageID003());

    }

    @UiHandler("deductionCategoryDdlbRes")
    public void valueChangeDeductionCategoryDdlbRes(final Property.ValueChangeEvent event) {
        LOGGER.debug(event.toString());
        isValueChangeAllowed = Boolean.FALSE;
        if ((int) deductionCategoryDdlbRes.getValue() != 0) {
            Map<Integer, HelperDTO> idhelper = HelperListUtil.getInstance().getIdHelperDTOMap();
            List<Object> list = logic.getTypeValuesBasedOnCategory((int) deductionCategoryDdlbRes.getValue());
            deductionTypeDdlbRes.removeAllItems();
            deductionTypeDdlbRes.addItem(0);
            deductionTypeDdlbRes.setItemCaption(0, GlobalConstants.getSelectOne());
            deductionTypeDdlbRes.setNullSelectionAllowed(false);
            for (Object obj : list) {
                if (obj != null) {
                    deductionTypeDdlbRes.addItem((int) obj);
                    deductionTypeDdlbRes.setItemCaption((int) obj, (idhelper.get((int) obj)).getDescription());
                }
            }
            deductionTypeDdlbRes.select(0);
            deductionProgramDdlbRes.removeAllItems();
            deductionProgramDdlbRes.addItem(0);
            deductionProgramDdlbRes.setItemCaption(0, GlobalConstants.getSelectOne());
            deductionProgramDdlbRes.select(0);
            isValueChangeAllowed = Boolean.TRUE;
        } else {
            CommonLogic.configureDropDownsForDeduction(deductionTypeDdlbRes, "getDeductionType");
        }
    }

    @UiHandler("deductionTypeDdlbRes")
    public void valueChangeDeductionTypeDdlbRes(final Property.ValueChangeEvent event) {
        LOGGER.debug(event.toString());
        if (isValueChangeAllowed && (int) deductionTypeDdlbRes.getValue() != 0) {
            if (deductionTypeDdlbRes.getValue() != null) {
                Map<Integer, HelperDTO> idhelper = HelperListUtil.getInstance().getIdHelperDTOMap();
                List<Object> list = logic.getTypeValuesBasedOnType((int) deductionCategoryDdlbRes.getValue(), (int) deductionTypeDdlbRes.getValue());// changed (int) companyDdlbRes.getValue() and (int) businessDdlbRes.getValue() to 0 for GAL-5535
                deductionProgramDdlbRes.removeAllItems();
                deductionProgramDdlbRes.addItem(0);
                deductionProgramDdlbRes.setItemCaption(0, GlobalConstants.getSelectOne());
                deductionProgramDdlbRes.setNullSelectionAllowed(false);
                for (Object obj : list) {
                    if (obj != null) {
                        deductionProgramDdlbRes.addItem((int) obj);
                        deductionProgramDdlbRes.setItemCaption((int) obj, (idhelper.get((int) obj)).getDescription());
                    }
                }
            }
            deductionProgramDdlbRes.select(0);
        } else {
            CommonLogic.configureDropDownsForDeduction(deductionProgramDdlbRes, "getDeductionProgram");
        }
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
    private SessionDTO createSessionId() {
        SessionDTO selection = sessionDTO.getSessionDTO(this.sessionDTO);
        Date sessionDate = new Date();
        selection.setSessionId(Integer.valueOf(ARMUtils.getInstance().getFmtID().format(sessionDate)));
        selection.setSessionDate(sessionDate);
        LOGGER.debug("UserId-->> {}", VaadinSession.getCurrent().getAttribute("userId").toString());
        selection.setUserId(Integer.valueOf(VaadinSession.getCurrent().getAttribute("userId").toString()));
        return selection;
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

//    /**
//     * Excel export button logic.
//     *
//     * @param event
//     */
    @UiHandler("excelBtnRes")
    public void exportButtonLogic(Button.ClickEvent event) {
        LOGGER.debug(event.toString());
        createWorkSheet("Adjustment Reserve", resultsTable);
    }

    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) {
        try {
            long recordCount = 0;
            List<String> visibleList = Arrays.asList(resultsTable.getColumnHeaders());
            if (resultTable.size() != 0) {
                recordCount = logic.getSearchCount(binderDto, adjustReserveConfigTableLogic.getFilters());
            }
            ExcelExportforBB.createWorkSheet(visibleList.toArray(new String[visibleList.size()]), recordCount, this, UI.getCurrent(), moduleName.toUpperCase(Locale.ENGLISH));
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            LOGGER.error("Error in ", ex);
        }
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {

        List visibleList = Arrays.asList(resultsTable.getVisibleColumns());
        try {
            if (end != 0) {
                final List<AdjustmentReserveDTO> searchList = logic.getSearchResults(binderDto, start, end, adjustReserveConfigTableLogic.getFilters(), adjustReserveConfigTableLogic.getSortByColumns());
                ExcelExportforBB.createFileContent(visibleList.toArray(), searchList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error("Error in createWorkSheetContent :", e);
        }
    }

    private void initializeTempTables(SessionDTO selection, ReserveSelection resSelection) {
        selection.setCurrentTableNames(QueryUtils.createTempTables(selection.getScreenName(), selection.getProjectionId(), selection.getUserId().toString(), selection.getSessionId().toString()));
        resSelection.setTempTableName(selection.getCurrentTableNames().get("ST_ARM_ADJ_RES_CONFIG_DETAIL"));
        resSelection.setAdjustmentSummaryTempTableName(selection.getCurrentTableNames().get("ST_ARM_ADJ_SUMMARY_CONFIG_DETAILS"));
        resSelection.setBalanceSummaryTempTableName(selection.getCurrentTableNames().get("ST_ARM_BALANCE_SUMMARY_CONFIG"));
        LOGGER.info("selection.getCurrentTableNames() ------- > {}", selection.getCurrentTableNames());
    }

    private boolean mandatoryCheckForSearch() {
        List<Integer> ddlbValues = new ArrayList<>();
        ddlbValues.add(binderDto.getCompanyDdlbRes());
        ddlbValues.add(binderDto.getBusinessDdlbRes());
        ddlbValues.add(binderDto.getDeductionCategoryDdlbRes());
        ddlbValues.add(binderDto.getDeductionTypeDdlbRes());
        ddlbValues.add(binderDto.getDeductionProgramDdlbRes());
        for (Integer ddlbValue : ddlbValues) {
            if (ddlbValue != 0) {
                return true;
            }
        }
        return false;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
