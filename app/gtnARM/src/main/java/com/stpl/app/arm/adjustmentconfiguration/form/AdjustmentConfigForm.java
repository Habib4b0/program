/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentconfiguration.form;

import com.stpl.app.arm.adjustmentconfiguration.dto.AdjustmentConfigDTO;
import com.stpl.app.arm.adjustmentconfiguration.logic.AdjustmentConfigLogic;
import com.stpl.app.arm.adjustmentconfiguration.logic.AdjustmentConfigTableLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import static com.stpl.app.utils.ResponsiveUtils.getResponsiveControls;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExcelExportforBB;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtDemoFilterDecorator;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar.R
 */
public class AdjustmentConfigForm extends VerticalLayout implements View {

    /**
     * The Table Layout
     */
    @UiField("resultsTableLayoutRes")
    private VerticalLayout resultsTableLayoutRes;

    @UiField("fieldLayout")
    private HorizontalLayout fieldLayout;

    @UiField("resetBtnRes")
    protected Button resetBtnRes;
    @UiField("saveBtnRes")
    protected Button saveBtnRes;
    @UiField("deleteBtnRes")
    protected Button deleteBtnRes;
    @UiField("historyBtn")
    protected Button historyBtn;
    @UiField("excelBtnRes")
    protected Button excelBtnRes;

    @UiField("labelMode")
    protected Label labelMode;

    @UiField("labelTransName")
    protected Label labelTransName;

    @UiField("labelTransDesc")
    protected Label labelTransDesc;

    @UiField("labelMethodology")
    protected Label labelMethodology;

    @UiField("labelredemptionPeriod")
    protected Label labelredemptionPeriod;

    @UiField("modeDdlb")
    protected ComboBox modeDdlb;
    @UiField("transactionName")
    protected TextField transactionName;
    @UiField("transactionDesc")
    protected TextField transactionDesc;
    @UiField("methodology")
    protected ComboBox methodologyDDLB;
    @UiField("redemptionPeriod")
    protected ComboBox redemptionPeriod;
    /**
     * The Table Logic For Adjustment Reserve Configuration in Add/Edit Mode
     */
    protected AdjustmentConfigTableLogic configTableLogic = new AdjustmentConfigTableLogic();
    /**
     * The Results Table
     */
    protected ExtPagedTable resultsTable = new ExtPagedTable(configTableLogic);
    /**
     * The Available Results Container
     */
    protected final BeanItemContainer<AdjustmentConfigDTO> detailsTableContainer = new BeanItemContainer<>(AdjustmentConfigDTO.class);

    private static String name = StringUtils.EMPTY;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        AdjustmentConfigForm.name = name;
    }
    /**
     * Binderdto is datasource for the binder.
     */
    protected AdjustmentConfigDTO binderDto = new AdjustmentConfigDTO();
    /**
     * binder used to bind the fields from the page
     */
    protected CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(binderDto));

    private AdjustmentConfigLogic logic = AdjustmentConfigLogic.getInstance();
    private AdjustmentConfigDTO selectedDTo;
    private SessionDTO sessionDTO;
    private CommonSecurityLogic commonSecurity = new CommonSecurityLogic();

    protected static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentConfigForm.class);

    public AdjustmentConfigForm(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
        addComponent(Clara.create(getClass().getResourceAsStream("/adjustment_config/adjustment-config-form.xml"), this));
        configureFields();
        securityForButtons();
        securityForFields();
        securityForTables();

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("Inside Enter Method");

    }

    /**
     * Configuring all the fields for adjustment configuration.
     */
    private void configureFields() {
        loadModeDDLB();
        loadRedemptionPeriodDDLB();
        loadResultsTable();
        getBinder();
        CommonUtils.loadComboBoxWithIntegerForComboBox(methodologyDDLB, "ARM_TRX_METHDOLOGY", false);
        excelBtnRes.setPrimaryStyleName("link");
        excelBtnRes.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.getItemId() != null) {
                    selectedDTo = ((BeanItem<AdjustmentConfigDTO>) event.getItem()).getBean();
                    if (modeDdlb.getValue() != null && modeDdlb.getValue().equals(ARMConstants.getEditMode())) {
                        try {
                            setValueToBinder(selectedDTo);
                        } catch (FieldGroup.CommitException ex) {
                            LOGGER.error("Error in itemClick :", ex);
                        }
                    }
                } else {
                    selectedDTo = new AdjustmentConfigDTO();
                }
            }
        });
    }

    /**
     * To load mode ddlb
     */
    private void loadModeDDLB() {
        modeDdlb.addItem(GlobalConstants.getSelectOne());
        modeDdlb.addItems(ARMConstants.getAddMode(), ARMConstants.getEditMode());
        modeDdlb.setNullSelectionAllowed(true);
        modeDdlb.setNullSelectionItemId(GlobalConstants.getSelectOne());
        modeDdlb.select(GlobalConstants.getSelectOne());
        modeDdlb.focus();
        modeDdlb.select(ARMConstants.getAddMode());
    }

    /**
     * To Load redemption Period ddlb
     */
    private void loadRedemptionPeriodDDLB() {
        redemptionPeriod.addItem(GlobalConstants.getSelectOne());
        redemptionPeriod.addItems(ARMConstants.getYes(), ARMConstants.getNo());
        redemptionPeriod.setNullSelectionAllowed(true);
        redemptionPeriod.setNullSelectionItemId(GlobalConstants.getSelectOne());
        redemptionPeriod.select(GlobalConstants.getSelectOne());
    }

    /**
     * This method will configure the binder for this page.
     *
     * @return
     */
    protected CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    /**
     * Excel export button logic.
     *
     * @param event
     */
    @UiHandler("excelBtnRes")
    public void exportButtonLogic(Button.ClickEvent event) {
        createWorkSheet(CommonConstant.ADJUSTMENT_CONFIGURATION, resultsTable);
    }

    /**
     * Reset logic for selection criteria.
     *
     * @param event
     */
    @UiHandler("resetBtnRes")
    public void resetSelectionButtonLogic(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    binderDto = new AdjustmentConfigDTO();
                    binder.setItemDataSource(new BeanItem<>(binderDto));
                    binder.commit();
                    modeDdlb.select(ARMConstants.getAddMode());
                } catch (Exception ex) {
                    LOGGER.error("Error in resetSelectionButtonLogic :", ex);
                }
            }

            @Override
            public void noMethod() {
                LOGGER.debug("Inside resetSelectionButtonLogic No Method");
            }
        }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getdReset_Conform_1());
    }

    /**
     * Configure and designing the Table
     */
    private void loadResultsTable() {
        resultsTableLayoutRes.addComponent(resultsTable);
        resultsTableLayoutRes.addComponent(getResponsiveControls(configTableLogic.createControls()));
        configTableLogic.setContainerDataSource(detailsTableContainer);
        resultsTable.setSelectable(true);
        resultsTable.setMultiSelect(true);
        configTableLogic.setPageLength(NumericConstants.TEN);
        configTableLogic.sinkItemPerPageWithPageLength(false);
        resultsTable.setVisibleColumns(ARMUtils.getAdjustmentConfigColumn());
        resultsTable.setColumnHeaders(ARMUtils.getAdjustmentConfigHeader());
        resultsTable.setFilterBarVisible(true);
        resultsTable.setSizeFull();
        resultsTable.setImmediate(true);
        resultsTable.setPageLength(NumericConstants.TEN);
        resultsTable.addStyleName(ARMUtils.FILTERCOMBOBOX);
        resultsTable.addStyleName("table-header-normal");
        resultsTable.addStyleName(ARMUtils.CENTER_CHECK);
        resultsTable.setColumnAlignment("createdDate", ExtCustomTable.Align.CENTER);
        resultsTable.setColumnAlignment("modifiedDate", ExtCustomTable.Align.CENTER);
        configTableLogic.loadsetData(true);
        resultsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.getItem() == null) {
                    selectedDTo = null;
                } else {
                    selectedDTo = ((BeanItem<AdjustmentConfigDTO>) event.getItem()).getBean();
                    if (modeDdlb.getValue() != null && modeDdlb.getValue().equals(ARMConstants.getEditMode())) {
                        try {
                            setValueToBinder(selectedDTo);
                        } catch (FieldGroup.CommitException ex) {
                            LOGGER.error("Error in itemClick :", ex);
                        }
                    }
                }

            }

        });
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());
        resultsTable.setFilterGenerator(adjFilterGenerator);
        resultsTable.setFilterBarVisible(true);
        resultsTable.setFilterDecorator(new ExtDemoFilterDecorator());

    }

    /**
     * Used to set the value to binder in edit mode.
     *
     * @param selectedDTo
     * @throws com.vaadin.data.fieldgroup.FieldGroup.CommitException
     */
    private void setValueToBinder(AdjustmentConfigDTO selectedDTo) throws FieldGroup.CommitException {
        transactionName.setEnabled(true);
        binderDto = new AdjustmentConfigDTO();
        binderDto.setAdjustmentConfigSid(selectedDTo.getAdjustmentConfigSid());
        binderDto.setTransactionName(selectedDTo.getTransactionName());
        binderDto.setTransactionDesc(selectedDTo.getTransactionDesc());
        binderDto.setMethodologyDDLB(selectedDTo.getMethodologyDDLB());
        binderDto.setRedemptionPeriod(selectedDTo.getRedemptionPeriod());
        binderDto.setModeDdlb(ARMConstants.getEditMode());
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.commit();
        transactionName.setEnabled(false);
    }

    /*
     For checking the mandatory fields are selected for save.
     */
    private boolean isMandatoryFieldsAreSelected() {
        return (binderDto.getModeDdlb() != null && !binderDto.getTransactionName().isEmpty() && !binderDto.getTransactionDesc().isEmpty() && binderDto.getMethodologyDDLB() != 0 && binderDto.getRedemptionPeriod() != null);
    }

    /**
     * Configuring the header and count for excel.
     *
     * @param moduleName
     * @param resultTable
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public void createWorkSheet(String moduleName, ExtPagedTable resultTable) {
        long recordCount = 0;
        String[] visibleList = resultsTable.getColumnHeaders();
        if (resultTable.size() != 0) {
            try {
                recordCount = logic.getAdjustmentConfigCount(configTableLogic.getFilters());
            } catch (SQLException ex) {
                LOGGER.error("Error in createWorkSheet {}", ex.getMessage());
            }
        }
        try {
            ExcelExportforBB.createWorkSheet(visibleList, recordCount, this, UI.getCurrent(), moduleName.toUpperCase(Locale.ENGLISH));
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            LOGGER.error("Error in createWorkSheet {}", ex.getMessage());
        }
    }

    /**
     * For Creating the excel exort content.
     *
     * @param start
     * @param end
     * @param printWriter
     * @throws SystemException
     * @throws PortalException
     * @throws Exception
     */
    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) {

        Object[] visibleList = resultsTable.getVisibleColumns();
        try {
            if (end != 0) {
                final List<AdjustmentReserveDTO> searchList = logic.getAdjustmentConfigData(start, end, configTableLogic.getFilters(), configTableLogic.getSortByColumns());
                ExcelExportforBB.createFileContent(visibleList, searchList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error("Error in createWorkSheetContent :", e);
        }
    }

    /**
     * for deleting the adjustment config data.s
     *
     * @param event
     */
    @UiHandler("deleteBtnRes")
    public void deleteBtnResLogic(Button.ClickEvent event) {
        if (selectedDTo == null) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getdDelete_Conform_1());
            return;
        }
        if (!logic.deleteVerfication(String.valueOf(selectedDTo.getAdjustmentConfigSid()))) { // Changed for GAL-7379
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getdDelete_Conform_3());
            return;
        }
        if (logic.adjustmentConfigDelCount(String.valueOf(selectedDTo.getAdjustmentConfigSid())) != 0) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getdDelete_Conform_4());
            return;
        }
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                logic.deleteAdjustmentConfig(selectedDTo.getAdjustmentConfigSid());
                detailsTableContainer.removeItem(selectedDTo);
                configTableLogic.loadsetData(true);
            }

            @Override
            public void noMethod() {
                LOGGER.debug("Inside the deleteBtnResLogic Listener NO Method");
            }
        }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getdDelete_Conform_2());

    }

    /**
     * Saving the adjustment config data.
     *
     * @param event
     */
    @UiHandler("saveBtnRes")
    public void saveBtnResLogic(Button.ClickEvent event) throws FieldGroup.CommitException {
        binder.commit();
        if (isMandatoryFieldsAreSelected()) {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        if (modeDdlb.getValue().equals(ARMConstants.getAddMode())) {
                            addModeSaveBtn();
                        } else if (modeDdlb.getValue().equals(ARMConstants.getEditMode())) {
                            editModeSaveBtn();
                        }
                        binderDto = new AdjustmentConfigDTO();
                        binder.setItemDataSource(new BeanItem<>(binderDto));
                        binder.commit();
                        modeDdlb.select(ARMConstants.getAddMode());
                        resultsTable.setRefresh(true);

                    } catch (Exception ex) {
                        LOGGER.error("Error in saveBtnResLogic :", ex);
                    }

                }

                @Override
                public void noMethod() {
                    LOGGER.debug("Inside the saveBtnResLogic Listener NO Method");

                }
            }.getConfirmationMessage(CommonConstant.CONFIRMATION, ARMMessages.getSave_Msg1());
        } else {
            AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getAdjConfigSaveErrorMsg());
        }
    }

    /**
     * For viewing history data for adjustment config.
     *
     * @param event
     */
    @UiHandler("historyBtn")
    public void historyBtnResLogic(Button.ClickEvent event) {
        if (selectedDTo == null) {
            AbstractNotificationUtils.getErrorNotification(CommonConstant.ERROR, ARMMessages.getHistoryListViewCheck());
            return;
        } else {
            AdjustmentConfigHistory historyWQindow = new AdjustmentConfigHistory(selectedDTo);
            UI.getCurrent().addWindow(historyWQindow);
            historyWQindow.addCloseListener(new Window.CloseListener() {
                @Override
                public void windowClose(Window.CloseEvent e) {
                    configTableLogic.loadsetData(true);
                }

            });
        }

    }

    /**
     * Configuring MOde
     */
    @UiHandler("modeDdlb")
    public void modeddlbValue(Property.ValueChangeEvent event) throws FieldGroup.CommitException {
        if (modeDdlb.getValue() != null) {
            if (event.getProperty().getValue().equals(ARMConstants.getAddMode())) {
                transactionName.setEnabled(true);
                selectedDTo = null;
                binderDto = new AdjustmentConfigDTO();
                binderDto.setModeDdlb(ARMConstants.getAddMode());
                binder.setItemDataSource(new BeanItem<>(binderDto));
                binder.commit();
            } else {
                transactionName.setEnabled(false);
                configTableLogic.loadsetData(true);
            }
        } else {
            transactionName.setEnabled(true);
        }
    }

    private void securityForButtons() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, CommonConstant.ADJUSTMENT_CONFIGURATION + ARMUtils.COMMA_CHAR + CommonConstant.LANDING_SCREEN);
        if (functionHM.get("deleteBtnRes") != null && !(functionHM.get("deleteBtnRes")).isFunctionFlag()) {
            deleteBtnRes.setVisible(false);
        } else {
            deleteBtnRes.setVisible(true);
        }
        if (functionHM.get("saveBtnRes") != null && !(functionHM.get("saveBtnRes")).isFunctionFlag()) {
            saveBtnRes.setVisible(false);
        } else {
            saveBtnRes.setVisible(true);
        }
        securityForButton(functionHM);
    }

    private void securityForFields() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHMforFields = stplSecurity.getBusinessFieldPermission(userId, CommonConstant.ADJUSTMENT_CONFIGURATION + ARMUtils.COMMA_CHAR + CommonConstant.LANDING_SCREEN);
        configureFieldPermission(functionHMforFields);
        if (functionHMforFields.get("modeDdlb") != null && !(functionHMforFields.get("modeDdlb")).isFunctionFlag()) {
            modeDdlb.setVisible(false);
            labelMode.setVisible(false);

        } else {
            modeDdlb.setVisible(true);
            labelMode.setVisible(true);

        }
        if (functionHMforFields.get("transactionName") != null && !(functionHMforFields.get("transactionName")).isFunctionFlag()) {
            transactionName.setVisible(false);
            labelTransName.setVisible(false);
        } else {
            transactionName.setVisible(true);
            labelTransName.setVisible(true);

        }
        securityForField(functionHMforFields);

    }

    private void configureFieldPermission(final Map<String, AppPermission> functionHMforFields) {
        LOGGER.debug("Entering configurePermission");
        try {
            List<Object> resultList = logic.getFieldsForSecurity(CommonConstant.ADJUSTMENT_CONFIGURATION, CommonConstant.LANDING_SCREEN);
            commonSecurity.removeComponentOnPermission(resultList, fieldLayout, functionHMforFields, CommonSecurityLogic.SEARCH);
        } catch (Exception ex) {
            LOGGER.error("Error in configureFieldPermission :", ex);
        }
        LOGGER.debug("Ending configurePermission");
    }

    private void securityForTables() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, CommonConstant.ADJUSTMENT_CONFIGURATION + ARMUtils.COMMA_CHAR + CommonConstant.LANDING_SCREEN, false);
        List<Object> resultList = logic.getFieldsForSecurity(CommonConstant.ADJUSTMENT_CONFIGURATION, CommonConstant.LANDING_SCREEN);
        Object[] obj = ARMUtils.getAdjustmentConfigColumn();
        TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, obj, fieldIfpHM, CommonSecurityLogic.ADD);
        if (tableResultCustom.getObjResult().length > 0) {
            resultsTable.markAsDirty();
            resultsTable.setContainerDataSource(detailsTableContainer);
            resultsTable.setVisibleColumns(tableResultCustom.getObjResult());
            resultsTable.setColumnHeaders(tableResultCustom.getObjResultHeader());
        } else {
            resultsTableLayoutRes.setVisible(false);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void editModeSaveBtn() {
        int count = logic.checkAdjustmentTypeExitorNot(binderDto);
        if (count == 0) {
            logic.saveDataforEditMode(binderDto);
            configTableLogic.loadsetData(true);
            selectedDTo = null;
        } else {
            AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getSaveErrorMessage());

        }
    }

    public void addModeSaveBtn() {
        if (!logic.checkDuplicateTransactionName(binderDto.getTransactionName())) {
            logic.saveDataforAddMode(binderDto);
        } else {
            AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateErrorHeaderMessage(), ARMMessages.getAdjConfigSaveTranNameErrorMsg());
        }
        configTableLogic.loadsetData(true);
        selectedDTo = null;
    }

    private void writeObject(ObjectOutputStream adjConfout) throws IOException {
        adjConfout.defaultWriteObject();
    }

    private void readObject(ObjectInputStream adjConfout) throws IOException, ClassNotFoundException {
        adjConfout.defaultReadObject();
    }

    private void securityForButton(Map<String, AppPermission> functionHM) {
        if (functionHM.get("historyBtn") != null && !(functionHM.get("historyBtn")).isFunctionFlag()) {
            historyBtn.setVisible(false);
        } else {
            historyBtn.setVisible(true);
        }
        if (functionHM.get("excelBtnRes") != null && !(functionHM.get("excelBtnRes")).isFunctionFlag()) {
            excelBtnRes.setVisible(false);
        } else {
            excelBtnRes.setVisible(true);
        }
        if (functionHM.get("resetBtnRes") != null && !(functionHM.get("resetBtnRes")).isFunctionFlag()) {
            resetBtnRes.setVisible(false);
        } else {
            resetBtnRes.setVisible(true);
        }
    }

    private void securityForField(Map<String, AppPermission> functionHMforFields) {
        if (functionHMforFields.get("transactionDesc") != null && !(functionHMforFields.get("transactionDesc")).isFunctionFlag()) {
            transactionDesc.setVisible(false);
            labelTransDesc.setVisible(false);

        } else {
            transactionDesc.setVisible(true);
            labelTransDesc.setVisible(true);

        }
        if (functionHMforFields.get("methodologyDDLB") != null && !(functionHMforFields.get("methodologyDDLB")).isFunctionFlag()) {
            methodologyDDLB.setVisible(false);
            labelMethodology.setVisible(false);

        } else {
            methodologyDDLB.setVisible(true);
            labelMethodology.setVisible(true);

        }
        if (functionHMforFields.get(CommonConstant.REDEMPTION_PERIOD) != null && !(functionHMforFields.get(CommonConstant.REDEMPTION_PERIOD)).isFunctionFlag()) {
            redemptionPeriod.setVisible(false);
            labelredemptionPeriod.setVisible(false);
        } else {
            redemptionPeriod.setVisible(true);
            labelredemptionPeriod.setVisible(true);
        }
    }

    private ExtFilterGenerator adjFilterGenerator = new ExtFilterGenerator() {

        @Override
        public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
            String adjConfigFiltervalue = null;
            if ((originatingField.getValue() != null)) {
                adjConfigFiltervalue = String.valueOf(originatingField.getValue());
                if ("redemptionPeriod".equals(propertyId) && !originatingField.getValue().equals(StringUtils.EMPTY)) {
                    if ("yes".contains(originatingField.getValue().toString())) {
                        adjConfigFiltervalue = "1";
                    } else {
                        adjConfigFiltervalue = "0";
                    }
                }
                return new SimpleStringFilter(propertyId, adjConfigFiltervalue, false, false);

            }
            return generateFilter(propertyId, adjConfigFiltervalue);
        }

        @Override
        public void filterRemoved(Object propertyId) {
            LOGGER.debug("Inside Adjustment Config Form filterRemoved Method");
        }

        @Override
        public void filterAdded(Object propertyId, Class<? extends Container.Filter> ftype, Object obj) {
            LOGGER.debug("Inside Adjustment Config Form filterRemoved Method");
        }

        @Override
        public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object obj) {
            return null;
        }

        @Override
        public AbstractField<?> getCustomFilterComponent(Object props) {
            return null;
        }

        @Override
        public Container.Filter generateFilter(Object id, Object value) {
            return null;
        }
    };
}
