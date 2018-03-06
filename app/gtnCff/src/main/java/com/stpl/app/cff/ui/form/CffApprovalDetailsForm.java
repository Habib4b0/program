 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.form;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.bpm.logic.VarianceCalculationLogic;
import com.stpl.app.cff.dto.ApprovalDetailsDTO;
import com.stpl.app.cff.dto.CFFResultsDTO;
import com.stpl.app.cff.dto.CFFSearchDTO;
import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.security.StplSecurity;
import com.stpl.app.cff.ui.dataSelection.form.DataSelection;
import com.stpl.app.cff.ui.fileSelection.form.FileSelection;
import com.stpl.app.cff.ui.projectionVariance.form.ProjectionVariance;
import com.stpl.app.cff.ui.projectionresults.form.ProjectionResults;
import com.stpl.app.cff.util.AbstractNotificationUtils;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.cff.util.Constants;
import com.stpl.app.cff.util.ConstantsUtil;
import com.stpl.app.cff.util.StringConstantsUtil;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.HorizontalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customwindow.CustomWindow;
import org.asi.ui.customwindow.CustomWindowConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 * CFF Approval screen
 *
 * @author shrihariharan
 */
public class CffApprovalDetailsForm extends CustomWindow {

    /**
     * Logger implementation for CffApprovalDetailsForm
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CffApprovalDetailsForm.class);
    
    private static final BooleanConstant BOOLEAN_CONSTANT = new BooleanConstant();
    
    /**
     * Approval UI Tab
     */
    @UiField("detailsTabSheet")
    private TabSheet tabSheet;
    
    @UiField("BottomBtnLayout")
    private HorizontalLayout BottomBtnLayout;
    private DataSelection dataSelection;
        
    @UiField("closeButton")
    private Button closeBtn;
    
    @UiField("submitBtn")
    private Button submitBtn;
    
    @UiField("approveBtn")
    private Button approveBtn;
    
    @UiField("rejectBtn")
    private Button rejectBtn;
    
    @UiField("deleteBtn")
    private Button deleteBtn;
    
    @UiField("cancelBtn")
    private Button cancelBtn;
    private int tabPosition = 0;
    /**
     * Approval Tab
     */
    private ApprovalTab approvalTab;
    /**
     * Additional Information
     */
    private NotesTabForm notestab;
    private CFFSearchDTO dto;
    /**
     * The approval details bean.
     */
    private  BeanItemContainer<ApprovalDetailsDTO> approvalDetailsBean;
    /**
     * The results bean.
     */
    private  BeanItemContainer<CFFResultsDTO> resultsBean;
    private SessionDTO sessionDTO;
    /**
     * scenarioModeling form constructor
     */
    private DataSelectionDTO dataSelectionDTO = new DataSelectionDTO();
    /**
     * Binder for Data Selection
     */
    private CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(dataSelectionDTO));
    /**
     * File Selection
     */
    private FileSelection fileSelection;
    /**
     * PR
     */
    private ProjectionResults projectionResults;
    /**
     * PV
     */
    private ProjectionVariance projectionVariance;
    private CustomFieldGroup cffSearchBinder;
    private CFFLogic cffLogic = new CFFLogic();
    private boolean isApproved = false;
    public static final String CFF = "CFF";
    private boolean filterOptionLoaded = false;

    /**
     * scenarioModeling form constructor
     *
     * @param dto
     * @param approvalDetailsBean
     * @param sessionDTO
     * @param resultsBean
     */
    public CffApprovalDetailsForm(CustomFieldGroup cffSearchBinder, CFFSearchDTO dto, BeanItemContainer<ApprovalDetailsDTO> approvalDetailsBean, BeanItemContainer<CFFResultsDTO> resultsBean, final SessionDTO sessionDTO, final DataSelectionDTO dataSelectiondto) {

        super(StringConstantsUtil.CONSOLIDATED_FINANCIAL_FORECAST);
        try {
            LOGGER.debug("Enters CffApprovalDetailsForm Constructor");
            addStyleName(Constants.BOOTSTRAP);
            addStyleName(Constants.BOOTSTRAP_UI);
            addStyleName("valo-theme-customwindow");
            addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
            this.approvalDetailsBean = approvalDetailsBean;
            this.resultsBean = resultsBean;
            this.sessionDTO = sessionDTO;
            this.dto = dto;
            if (!(Constants.ADD.equals(sessionDTO.getAction()))) {
                this.dataSelectionDTO = dataSelectiondto;
            }
            this.cffSearchBinder = cffSearchBinder;
            center();
            setWidth(NumericConstants.HUNDRED, Unit.PERCENTAGE);
            setPositionX(0);
            setPositionY(0);
            setMinimizeToTray();
            setHeight("800px");
            addStyleName(CustomWindowConstant.VALO_THEME_CUSTOMWINDOW);
            addStyleName(Constants.BOOTSTRAP_UI);
            addStyleName(Constants.BOOTSTRAP);
            addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
            addStyleName("body-fixed");
            setContent(Clara.create(getClass().getResourceAsStream("/cff/CffApprovalDetailsForm.xml"), this));
            configureFields();
            if (sessionDTO.getAction().equals("view")) {
                disableFieldsOnview();
            }

            LOGGER.debug("Exits CffApprovalDetailsForm Constructor");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    /**
     * To set additional properties for the UI components
     */
    private void configureFields() {
        try {
            LOGGER.debug("Enters CffApprovalDetailsForm Configure Field method");
            approveBtnLogic();
            tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
            tabSheet.markAsDirty();
            tabSheet.markAsDirtyRecursive();
            dataSelection = new DataSelection(binder, dataSelectionDTO, tabSheet, sessionDTO);
            fileSelection = new FileSelection(sessionDTO, dataSelection.getBusinessUnit());
            projectionResults = new ProjectionResults(sessionDTO, CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED, dataSelectionDTO);
            projectionVariance = new ProjectionVariance(sessionDTO, dataSelectionDTO);
            notestab = new NotesTabForm(sessionDTO, cffSearchBinder, StringConstantsUtil.CONSOLIDATED_FINANCIAL_FORECAST, this);
            approvalTab = new ApprovalTab(dto, approvalDetailsBean, resultsBean, notestab, sessionDTO);
            configurePermission();
            dataSelection.setCaption(StringConstantsUtil.DATA_SELECTION_LABEL);
            tabSheet.addTab(dataSelection, StringConstantsUtil.DATA_SELECTION_LABEL, null, NumericConstants.ZERO);
            fileSelection.setCaption("File Selection");
            tabSheet.addTab(fileSelection, "File Selection", null, NumericConstants.ONE);
            approvalTab.setCaption("Approval Details");
            tabSheet.addTab(approvalTab, "Approval Details", null, NumericConstants.TWO);
            projectionResults.setCaption("Results");
            tabSheet.addTab(projectionResults, "Results", null, NumericConstants.THREE);
            projectionVariance.setCaption("Variance");
            tabSheet.addTab(projectionVariance, "Variance", null, NumericConstants.FOUR);
            notestab.setCaption("Additional Information");
            tabSheet.addTab(notestab, "Additional Information", null, NumericConstants.FIVE);
            if (tabSheet.getTab(0).getCaption().contentEquals(StringConstantsUtil.DATA_SELECTION_LABEL)) {
                BottomBtnLayout.setVisible(false);
            }
            setTabSecurity();
            tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
                @Override
                public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                    int previousTabPostion = tabPosition;
                    final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                    tabPosition = event.getTabSheet().getTabPosition(tab);
                    String mode = sessionDTO.getAction();
                    submitBtn.setEnabled(false);
                    if (tabPosition == NumericConstants.TWO) {
                        if (!Constants.PENDING.equals(dto.getStatusDesc()) && (!"view".equals(mode))) {
                            if (Constants.REJECTED.equals(dto.getStatusDesc())) {
                                if (!Integer.valueOf(sessionDTO.getUserId()).equals(dto.getCreatedUser())) {
                                    submitBtn.setEnabled(false);
                                } else {
                                    submitBtn.setEnabled(true);
                                }
                            }
                            if (isApproved) {
                                submitBtn.setEnabled(false);
                            }
                        }

                        if ("add".equals(mode)) {
                            approvalTab.loadResultTable();
                            submitBtn.setEnabled(true);
                        }
                    }
                    if (tabPosition == 1) {
                        fileSelection.refreshTable();
                    }
                    if (previousTabPostion == 1) {
                        if ("add".equals(mode)) {
                            fileSelection.getSelectedFile();
                        }
                        String projId = String.valueOf(sessionDTO.getProjectionId());
                        Object[] obj = {projId};
                        CommonLogic.callProcedureUpdate("PRC_CFF_FILES_DATA_INSERT", obj);
                    }

                    if (tabPosition == NumericConstants.THREE || (!tabSheet.getTab(NumericConstants.THREE).isVisible() && tabPosition == NumericConstants.FOUR)) {
                        String projId = String.valueOf(sessionDTO.getProjectionId());
                        Object[] obj = {projId};
                        CommonLogic.callProcedureUpdate("PRC_CFF_FILES_DATA_INSERT", obj);
                        projectionVariance.uomLoadingTabChange();
                    }

                    if (tabPosition == NumericConstants.FOUR && Constants.ADD.equals(sessionDTO.getAction()) && !filterOptionLoaded) {
                        projectionVariance.loadAllDdbls();
                        filterOptionLoaded=true;
                    }

                    if (event.getTabSheet().getSelectedTab().getCaption().contentEquals(StringConstantsUtil.DATA_SELECTION_LABEL)) {
                        BottomBtnLayout.setVisible(false);
                    } else {
                        BottomBtnLayout.setVisible(true);
                    }
                }
            });
            closeBtn.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    AbstractNotificationUtils notification = new AbstractNotificationUtils() {
                        @Override
                        public void noMethod() {
                            // To change body of generated methods, choose Tools
                            // | Templates.
                        }

                        @Override
                        public void yesMethod() {

                            close();
                            CommonLogic.dropDynamicTables(sessionDTO.getUserId(), sessionDTO.getSessionId());
                        }
                    };
                    notification.getConfirmationMessage("Close Confirmation", "Are you sure you want to close the Consolidated Financial Forecast Approval");

                }
            });
            submitBtn.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    if (sessionDTO.isIsGenerated()) {
                        Boolean submitCkeck = approvalTab.submitCheck();
                        if (submitCkeck) {
                            new AbstractNotificationUtils() {
                                @Override
                                public void noMethod() {
                                    // To change body of generated methods, choose Tools
                                    // | Templates.
                                }

                                @Override
                                public void yesMethod() {
                                    try {
                                        if (Constants.REJECTED.equals(dto.getStatusDesc())) {
                                            dataSelection.insertCffDetailsUsingExecutorService(sessionDTO.getProjectionId(), sessionDTO.getCurrentTableNames());
                                        }
                                        checkIsCffDetailsInsertJobRunning();
                                        isApproved = approvalTab.submitLogic();
                                        if (isApproved) {
                                            submitBtn.setEnabled(false);
                                        }
                                        projectionResults.saveProjectionResultsSelection(sessionDTO);
                                        projectionVariance.savePvSelections(sessionDTO);

                                    } catch (PortalException | SystemException | InterruptedException | ExecutionException ex) {
                                        java.util.logging.Logger.getLogger(CffApprovalDetailsForm.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }.getConfirmationMessage("Submit Confirmation",
                                    "Are you sure you want to Submit the CFF?");
                        }
                    } else {
                        MessageBox.showPlain(Icon.INFO, ConstantsUtil.ERROR, "Dataselection needs to be generated", ButtonId.OK);
                    }
                }
            });

            if (dto.getStatusDesc() != null && !StringUtils.EMPTY.equals(dto.getStatusDesc())) {
                if (Constants.APPROVED.equals(dto.getStatusDesc())) {

                    approveBtn.setEnabled(false);
                    rejectBtn.setEnabled(false);
                    deleteBtn.setEnabled(false);
                    cancelBtn.setEnabled(false);
                    submitBtn.setEnabled(false);
                } else if ("Pending".equals(dto.getStatusDesc())) {
                    if (Integer.valueOf(sessionDTO.getUserId()).equals(dto.getCreatedUser())) {
                        approveBtn.setEnabled(false);
                        rejectBtn.setEnabled(false);
                        deleteBtn.setEnabled(false);
                        cancelBtn.setEnabled(true);
                        submitBtn.setEnabled(false);
                    } else {
                        approveBtn.setEnabled(true);
                        rejectBtn.setEnabled(true);
                        deleteBtn.setEnabled(true);
                        cancelBtn.setEnabled(true);
                        submitBtn.setEnabled(false);
                    }
                } else if (Constants.REJECTED.equals(dto.getStatusDesc())) {

                    approveBtn.setEnabled(false);
                    rejectBtn.setEnabled(false);
                    deleteBtn.setEnabled(true);
                    cancelBtn.setEnabled(true);
                    submitBtn.setEnabled(true);
                } else if ("Cancelled".equals(dto.getStatusDesc()) || "Canceled".equals(dto.getStatusDesc())) {

                    approveBtn.setEnabled(false);
                    rejectBtn.setEnabled(false);
                    deleteBtn.setEnabled(false);
                    cancelBtn.setEnabled(false);
                    submitBtn.setEnabled(false);
                }

            } else {
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                deleteBtn.setEnabled(false);
                cancelBtn.setEnabled(false);
                submitBtn.setEnabled(true);

            }
        } catch (PortalException | SystemException | NumberFormatException e) {
            LOGGER.error(e.getMessage());

        }
    }

    @UiHandler("rejectBtn")
    public void rejectBtnLogic(final Button.ClickEvent event) {
        LOGGER.debug("Inside Rejected Button click event method");
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                String result = "";
                final int cffMasterSystemId = dto.getCffMasterSid();
                final String userId = sessionDTO.getUserId();

                cffLogic.approveCffInformation(cffMasterSystemId, userId);

                result = cffLogic.approveCffApproveDetails(userId, cffMasterSystemId, CommonUtils.WORKFLOW_STATUS_REJECTED).get(0).toString();
                
                VarianceCalculationLogic.rejectWorkFlow(sessionDTO.getProcessId(), sessionDTO, CFF);
                if (!result.equals(CommonUtils.FAIL)) {
                    notestab.getApprovalWindow().close();
                    CommonUIUtils.getMessageNotification("Rejected Successfully");
                }
            }
        }.getConfirmationMessage("Reject Confirmation",
                "Are you sure you want to Reject the Forecast Workflow?");

        LOGGER.debug("Inside Rejected Button click event method");
    }

    /**
     * Reject button click method event handler
     *
     * @param event
     */
    @UiHandler("cancelBtn")
    public void cancelBtnLogic(final Button.ClickEvent event) {
        LOGGER.debug("Inside Cancelled Button click event method");
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                String result = "";
                final int cffMasterSystemId = dto.getCffMasterSid();
                final String userId = sessionDTO.getUserId();

                cffLogic.approveCffInformation(cffMasterSystemId, userId);

                result = cffLogic.approveCffApproveDetails(userId, cffMasterSystemId, CommonUtils.WORKFLOW_STATUS_CANCELLED).get(0).toString();
                
                VarianceCalculationLogic.cancelWorkFlow(sessionDTO.getProcessId(), sessionDTO, CFF);
                if (!result.equals(CommonUtils.FAIL)) {
                    notestab.getApprovalWindow().close();
                    CommonUIUtils.getMessageNotification("Cancelled Successfully");
                }
            }
        }.getConfirmationMessage("Cancel Confirmation",
                " Are you sure you want to Cancel the Forecast Workflow?");

        LOGGER.debug("Inside Cancelled Button click event method");
    }

    @UiHandler("deleteBtn")
    public void deleteBtnLogic(final Button.ClickEvent event) {
        LOGGER.debug("Inside delete Button click event method");
        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                // do nothing
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {
                String result;
                final int cffMasterSystemId = sessionDTO.getProjectionId();
                cffLogic.deleteCff(cffMasterSystemId, ConstantsUtil.CFF_MASTER);
                cffLogic.deleteCff(cffMasterSystemId, ConstantsUtil.CFF_APPROVE_MASTER);
                cffLogic.deleteCff(cffMasterSystemId, ConstantsUtil.CFF_DETAILS);
                result = cffLogic.deleteCffAdditionalInfo(cffMasterSystemId);

                if (!result.equals(CommonUtils.FAIL)) {
                    notestab.getApprovalWindow().close();
                    CommonUIUtils.getMessageNotification("Deleted Successfully");
                }
            }
        }.getConfirmationMessage("Delete Confirmation",
                "Are you sure you want to Delete the Forecast Workflow?");

        LOGGER.debug("Inside delete Button click event method");
    }

    public void approveBtnLogic() {
        LOGGER.debug("Inside Approval Button click event method");

        approveBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                AbstractNotificationUtils notification = new AbstractNotificationUtils() {
                    @Override
                    public void noMethod() {
                        // To change body of generated methods, choose Tools
                        // | Templates.
                    }

                    @Override
                    public void yesMethod() {

                        String result = "";
                        final int cffMasterSystemId = dto.getCffMasterSid();
                        final String userId = sessionDTO.getUserId();

                        cffLogic.approveCffInformation(cffMasterSystemId, userId);

                        List<Object> resultList = cffLogic.approveCffApproveDetails(userId, cffMasterSystemId, CommonUtils.WORKFLOW_STATUS_APPROVED);
                        result = resultList.get(0).toString();
                        
                        VarianceCalculationLogic.approveWorkflow(sessionDTO.getProcessId(),sessionDTO, CFF);
                        if (!result.equals(CommonUtils.FAIL)) {
                            if ((Boolean) resultList.get(1)) {
                                Object[] obj = {dto.getCffMasterSid()};
                                CommonLogic.callProcedureUpdate("PRC_CFF_OUTBOUND", obj);
                            }
                            notestab.getApprovalWindow().close();
                            CommonUIUtils.getMessageNotification("Approved Successfully");
                        }
                    }
                };
                notification.getConfirmationMessage("Confirmation", "Are you sure you want to Approve the Forecast Workflow?");

            }
        });

        LOGGER.debug("Exits Approval Button click even method");
    }

    public void disableFieldsOnview() {
        approveBtn.setEnabled(false);
        rejectBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        cancelBtn.setEnabled(false);
        submitBtn.setEnabled(false);

    }

    private void checkIsCffDetailsInsertJobRunning() throws ExecutionException, InterruptedException {
        sessionDTO.getFuture().get();
    }

    public void configurePermission() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtil.USER_ID));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, StringConstantsUtil.CONSOLIDATED_FINANCIAL_FORECAST + "," + "Variance Tab");
            if (functionHM.get("approveBtn") != null && !((AppPermission) functionHM.get("approveBtn")).isFunctionFlag()) {
                approveBtn.setVisible(false);
            } else {
                approveBtn.setVisible(true);
            }
            if (functionHM.get("rejectBtn") != null && !((AppPermission) functionHM.get("rejectBtn")).isFunctionFlag()) {
                rejectBtn.setVisible(false);
            } else {
                rejectBtn.setVisible(true);
            }
            if (functionHM.get("deleteBtn") != null && !((AppPermission) functionHM.get("deleteBtn")).isFunctionFlag()) {
                deleteBtn.setVisible(false);
            } else {
                deleteBtn.setVisible(true);
            }
            if (functionHM.get("cancelBtn") != null && !((AppPermission) functionHM.get("cancelBtn")).isFunctionFlag()) {
                cancelBtn.setVisible(false);
            } else {
                cancelBtn.setVisible(true);
            }
            if (functionHM.get("submitBtn") != null && !((AppPermission) functionHM.get("submitBtn")).isFunctionFlag()) {
                submitBtn.setVisible(false);
            } else {
                submitBtn.setVisible(true);
            }
            if (functionHM.get("closeBtn") != null && !((AppPermission) functionHM.get("closeBtn")).isFunctionFlag()) {
                closeBtn.setVisible(false);
            } else {
                closeBtn.setVisible(true);
            }
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    private void setTabSecurity() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtil.USER_ID));
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessTabPermission(userId, "Consolidated Financial Forecast");
            if (functionHM.get("resultsTab") != null && !((AppPermission) functionHM.get("resultsTab")).isTabFlag()) {
                tabSheet.getTab(NumericConstants.THREE).setVisible(BOOLEAN_CONSTANT.getFalseFlag());
            }
            if (functionHM.get("varianceTab") != null && !((AppPermission) functionHM.get("varianceTab")).isTabFlag()) {
                tabSheet.getTab(NumericConstants.FOUR).setVisible(BOOLEAN_CONSTANT.getFalseFlag());
            }
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
