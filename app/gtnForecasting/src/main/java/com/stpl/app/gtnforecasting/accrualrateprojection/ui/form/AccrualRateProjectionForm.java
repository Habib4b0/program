/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.accrualrateprojection.ui.form;

import static com.stpl.app.utils.Constants.ButtonConstants.BTN_NEXT;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_PREVIOUS;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_EDIT;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_DATA_SELECTION;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.bpm.dto.WorkflowRuleDTO;
import com.stpl.app.gtnforecasting.abstractforecast.AbstractForm;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.utils.AccrualRateUtils;
import com.stpl.app.gtnforecasting.bpm.logic.VarianceCalculationLogic;
import com.stpl.app.gtnforecasting.bpm.util.MessageUtils;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.ui.ForecastMainView;
import com.stpl.app.gtnforecasting.ui.form.DataSelection;
import com.stpl.app.gtnforecasting.ui.form.DataSelectionForm;
import com.stpl.app.gtnforecasting.ui.form.lookups.WorkFlowNotesLookup;
import com.stpl.app.gtnforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.NotificationUtils;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.gtnforecasting.workflow.dto.WorkflowMasterDTO;
import com.stpl.app.gtnforecasting.workflow.logic.WorkflowLogic;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.util.BeanItem;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

/**
 *
 * @author sibi
 */
public class AccrualRateProjectionForm extends AbstractForm {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccrualRateProjectionForm.class);

    private Sales sales;

    private final Rates rates;

    private final Details details;

    private final DataSelection dataSelection;

    private final TabSheet tabSheet;

    private final CustomFieldGroup dataSelectionBinder;
    /**
     * Seesion DTO
     */
    private final SessionDTO session;
    private final String screenName;
    private final DataSelectionForm dataSelectionForm;
    private final DSLogic dsLogic = new DSLogic();
    private final Button nextBtn = new Button(BTN_NEXT.getConstant());
    private final Button prevBtn = new Button(BTN_PREVIOUS.getConstant());
    private final Button refreshBtn = new Button("REFRESH");

    private int tabPosition;
    private int lastPosition;
    private boolean isRatesLoaded;
    private boolean isDetailsLoaded;
    private boolean heirarchySaved = false;

    private TabSheet.SelectedTabChangeListener tabChangeListener = new TabSheet.SelectedTabChangeListener() {
        @Override
        public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {

            final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
            lastPosition = tabPosition;
            tabPosition = event.getTabSheet().getTabPosition(tab);
            buttonEnableLogic(tabPosition, tabSheet.getComponentCount() - 1);
            onTabChange();

        }
    };

    private final Map map = new HashMap(10);

    public AccrualRateProjectionForm(DataSelectionDTO dataSelectionDTO, SessionDTO session, String screenName, DataSelectionForm dataSelectionForm) throws ParseException {
        this.session = session;
        this.screenName = screenName;
        this.dataSelectionForm = dataSelectionForm;
        tabSheet = new TabSheet();
        if (!session.getAction().equalsIgnoreCase(Constant.VIEW)) {
            insertFileData();
        }
        this.setSales(new Sales(session, map));
        this.rates = new Rates(session, map);
        this.details = new Details(session, map);
        dataSelectionBinder = new CustomFieldGroup(new BeanItem<>(dataSelectionDTO));
        this.dataSelection = new DataSelection(dataSelectionBinder, dataSelectionDTO, session, screenName, dataSelectionForm);
        init();

    }

    private void init() {
        buildScreen();
        configurePermission();
        configureFields();
    }

    @Override
    protected void btnSaveLogic() {
        try {
            saveLogic(true);
        } catch (SystemException | PortalException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    protected void btnCloseLogic() {

        new AbstractNotificationUtils() {
            @Override
            public void noMethod() {
                return;
            }

            @Override
            /**
             * The method is triggered when Yes button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            public void yesMethod() {

                if (session.getWorkflowId() != 0) {
                    if (!Constant.VIEW.equalsIgnoreCase(session.getAction())) {
                        CommonLogic.dropDynamicTables(session.getUserId(), session.getSessionId());
                    }
                    Page.getCurrent().getJavaScript().execute("window.open('','_parent','');window.close(); ");
                } else {
                    try {
                        dataSelectionForm.closeBtn();
                        CommonLogic.dropDynamicTables(session.getUserId(), session.getSessionId());

                        getUI().getNavigator().navigateTo(ForecastMainView.NAME);

                    } catch (Exception exception) {
                        LOGGER.error(exception.getMessage());
                    }
                }
            }
        }.getConfirmationMessage("Confirmation", "Are you sure you want to close the Accrual Rate Projection?\n"
                + "YES/NO");

    }

    @Override
    protected void btnSubmitLogic() {

        if ((AccrualRateUtils.ADD.equalsIgnoreCase(session.getAction()) && map.containsKey(Constant.IS_SALES_GENERATED) && map.containsKey(Constant.IS_RATES_GENERATED))
                || (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) && !session.isNewFileCalculationNeeded()
                && map.containsKey(Constant.IS_SALES_GENERATED) && map.containsKey(Constant.IS_RATES_GENERATED))
                || (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) && !session.isNewFileCalculationNeeded()
                && !map.containsKey(Constant.IS_SALES_GENERATED) && !map.containsKey(Constant.IS_RATES_GENERATED))
                || (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) && session.isNewFileCalculationNeeded()
                && map.containsKey(Constant.IS_SALES_GENERATED) && map.containsKey(Constant.IS_RATES_GENERATED))) {
            new AbstractNotificationUtils() {
                @Override
                public void noMethod() {
                    return;
                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {
                    //TO check all threads are completed
                    for (int i = 0; i < session.getNumberOfThreads(); i++) {
                        CommonUtil.getInstance().waitFor(session.getThread(i));
                    }
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    UI.getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                if (WorkFlowNotesLookup.getSUBMIT_FLAG().equals("Success")) {
                                    boolean isSubmitted = submitProjection(popup.getNotes().getValue(), screenName, popup.getUploadedData());
                                    if (isSubmitted) {
                                        UI.getCurrent().getNavigator().navigateTo(ForecastMainView.NAME);
                                    }
                                    WorkFlowNotesLookup.setSUBMIT_FLAG("Failed");
                                    CommonLogic.dropDynamicTables(session.getUserId(), session.getSessionId());
                                }
                            } catch (SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            } catch (PortalException ex) {
                                LOGGER.error(ex.getMessage());
                            } catch (Exception ex) {
                                LOGGER.error(ex.getMessage());
                            }
                        }
                    });
                }
            }.getConfirmationMessage("Submit Confirmation", "Are you sure you want to submit this Accrual Rate Projection for approval?");
        } else {
            NotificationUtils.getErrorNotification("Not all Required Fields are Populated", "Please ensure that both Sales and Rates tabs have been generated with data before you submit.");
        }

    }

    @Override
    protected void initializeTabs() {
        return;
    }

    @Override
    protected void buildScreen() {
        this.addComponent(tabSheet);
        this.addComponent(addFooterButtons(nextBtn, prevBtn, refreshBtn));
        configureFooterButtons();
        configureTabSheet();

    }

    @Override
    protected void onTabChange(int tabPosition) {
        return;
    }

    @Override
    protected void lazyLoadTab(int tabPosition) {
        return;
    }

    private void configureTabSheet() {
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.addTab(dataSelection, TAB_DATA_SELECTION.getConstant(), null, 0);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.addSelectedTabChangeListener(tabChangeListener);
        tabSheet.addTab(this.getSales(), AccrualRateUtils.SALES, null, NumericConstants.ONE);
        tabSheet.addTab(this.rates, AccrualRateUtils.RATES, null, NumericConstants.TWO);
        tabSheet.addTab(this.details, AccrualRateUtils.DETAILS, null, NumericConstants.THREE);
        tabSheet.setSelectedTab(NumericConstants.ONE);

        onTabChange();
    }

    // Abstract Methods that are not used in this module.
    @Override
    protected void btnApproveLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Approve", "Are you sure you want to approve the projection " + " ?", new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {

                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    UI.getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {

                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                boolean callOutboundPrc = true;
                                int projectionId = session.getProjectionId();
                                String userId = session.getUserId();
                                int userIdInt = Integer.parseInt(userId);
                                int workflowId = session.getWorkflowId();
                                WorkflowLogic wfLogic = new WorkflowLogic();
                                String workflowIdUpdate;
                                WorkflowMasterDTO wfMasterDto;
                                wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getApprovedStatus(), popup.getNotes().getValue(), session.getApprovalLevel());
                                workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                if (session.getNoOfApproval() > session.getApprovalLevel()) {
                                    callOutboundPrc = false;
                                    wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getPendingStatus(), StringUtils.EMPTY, session.getApprovalLevel() + 1);
                                    workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                }
                                if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {

                                    Map<String, Object> params = new HashMap<>();
                                    params.put(Constant.APPROVE_FLAG, "approve");
                                    VarianceCalculationLogic.submitWorkflow(session.getProcessId(), session,GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
                                    callWorkflowInboxRefresh();
                                    AbstractNotificationUtils.getInfoNotification("Approved Information", Constant.WORKFLOW_ID + workflowIdUpdate + " approved successfully");
                                    // For Mail
                                    StringBuffer sb = new StringBuffer(Constant.BR_BR);
                                    sb.append(Constant.WORKFLOW_WITH_WORKFLOW_ID + workflowIdUpdate + " is Approved Succesfully.");
                                    sb.append(Constant.THANKS_BPI_TECHNICAL_TEAM);
//                                    MailWorkItemHandler.sendMail(Constant.SUPPORT_MAIL, "Workflow Approved Succesfully", sb);
                                    getBtnApprove().setEnabled(false);
                                    getBtnWithdraw().setEnabled(false);
                                    getBtnCancel().setEnabled(false);
                                    getBtnReject().setEnabled(false);
                                    if (callOutboundPrc) {
                                        dsLogic.callARPApprovalProcedure(projectionId);
                                    }

                                } else {
                                    CommonUIUtils.getMessageNotification("The projection not approved properly");
                                }
                                CommonLogic.dropDynamicTables(session.getUserId(), session.getSessionId());
                            } catch (NumberFormatException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                        }
                    });

                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @Override
    protected void btnRejectLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Reject", "Are you sure you want to reject the projection " + " ?", new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    UI.getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {

                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                int projectionId = session.getProjectionId();
                                String userId = session.getUserId();
                                int userIdInt = Integer.parseInt(userId);
                                int workflowId = session.getWorkflowId();
                                WorkflowLogic wfLogic = new WorkflowLogic();
                                WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getRejectedStatus(), popup.getNotes().getValue(), session.getApprovalLevel());
                                String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {
                                    Map<String, Object> params = new HashMap<>();
                                    params.put(Constant.APPROVE_FLAG, "reject-RWC");
                                    VarianceCalculationLogic.submitWorkflow(session.getProcessId(), session,GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
                                    // For Mail
                                    callWorkflowInboxRefresh();
                                    AbstractNotificationUtils.getInfoNotification("Rejected Information ", Constant.WORKFLOW_ID + workflowIdUpdate + " rejected successfully");
                                    StringBuffer sb = new StringBuffer(Constant.BR_BR);
                                    sb.append(Constant.WORKFLOW_WITH_WORKFLOW_ID + workflowIdUpdate + " is Rejected Succesfully.");
                                    sb.append(Constant.THANKS_BPI_TECHNICAL_TEAM);
                                    getBtnApprove().setEnabled(false);
                                    getBtnWithdraw().setEnabled(false);
                                    getBtnCancel().setEnabled(false);
                                    getBtnReject().setEnabled(false);
                                } else {
                                    CommonUIUtils.getMessageNotification("The projection not rejected properly");
                                }
                                CommonLogic.dropDynamicTables(session.getUserId(), session.getSessionId());
                            } catch (NumberFormatException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                        }
                    });
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @Override
    protected void btnWithdrawLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Withdraw", "Are you sure you want to withdraw the projection " + " ?", new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    UI.getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {

                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                int projectionId = session.getProjectionId();
                                String userId = session.getUserId();
                                int userIdInt = Integer.parseInt(userId);
                                int workflowId = session.getWorkflowId();
                                WorkflowLogic wfLogic = new WorkflowLogic();
                                WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getWithdrawnStatus(), popup.getNotes().getValue(), session.getApprovalLevel());
                                String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {
                                    Map<String, Object> params = new HashMap<>();
                                    params.put(Constant.APPROVE_FLAG, "withdraw-RWC");
                                    VarianceCalculationLogic.submitWorkflow(session.getProcessId(), session,GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
                                    callWorkflowInboxRefresh();
                                    AbstractNotificationUtils.getInfoNotification("Workflow withdrawn ", Constant.WORKFLOW_ID + workflowIdUpdate + " withdrawn successfully");
                                    // For Mail
                                    StringBuffer sb = new StringBuffer(Constant.BR_BR);
                                    sb.append(Constant.WORKFLOW_WITH_WORKFLOW_ID + workflowIdUpdate + " is Withdrawn Succesfully.");
                                    sb.append(Constant.THANKS_BPI_TECHNICAL_TEAM);
                                    getBtnApprove().setEnabled(false);
                                    getBtnWithdraw().setEnabled(false);
                                    getBtnCancel().setEnabled(false);
                                    getBtnReject().setEnabled(false);
                                } else {
                                    CommonUIUtils.getMessageNotification("The projection not withdrawn properly");
                                }
                                CommonLogic.dropDynamicTables(session.getUserId(), session.getSessionId());
                            } catch (NumberFormatException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                        }
                    });
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @Override
    protected void btnCancelLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Cancel", "Are you sure you want to cancel the projection " + " ?", new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    UI.getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {

                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                int projectionId = session.getProjectionId();
                                String userId = session.getUserId();
                                int userIdInt = Integer.parseInt(userId);
                                int workflowId = session.getWorkflowId();
                                WorkflowLogic wfLogic = new WorkflowLogic();
                                WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getCancelledStatus(), popup.getNotes().getValue(), session.getApprovalLevel());
                                String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {

                                    Map<String, Object> params = new HashMap<>();
                                    params.put(Constant.APPROVE_FLAG, "cancel-RWC");

                                    VarianceCalculationLogic.submitWorkflow(session.getProcessId(), session,GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
                                    callWorkflowInboxRefresh();
                                    AbstractNotificationUtils.getInfoNotification("Cancel Information", Constant.WORKFLOW_ID + workflowIdUpdate + " cancelled successfully");
                                    // For Mail
                                    StringBuffer sb = new StringBuffer(Constant.BR_BR);
                                    sb.append(Constant.WORKFLOW_WITH_WORKFLOW_ID + workflowIdUpdate + " is cancelled Succesfully.");
                                    sb.append(Constant.THANKS_BPI_TECHNICAL_TEAM);
                                    getBtnApprove().setEnabled(false);
                                    getBtnWithdraw().setEnabled(false);
                                    getBtnCancel().setEnabled(false);
                                    getBtnReject().setEnabled(false);
                                } else {
                                    CommonUIUtils.getMessageNotification("The projection not cancelled properly");
                                }
                            } catch (NumberFormatException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                        }
                    });
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    @Override
    protected void btnRefreshLogic() {
        return;
    }

    private void configureFooterButtons() {

        btnNext.setVisible(true);
        btnPrev.setVisible(true);
        btnSubmit.setVisible(true);
        btnClose.setVisible(true);
        btnSave.setVisible(true);

        if (session != null && (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) && session.getWorkflowId() != 0) {
            if (WorkflowConstants.getApproverConstant().equals(session.getWorkflowUserType())) {

                getBtnWithdraw().setVisible(false);
                getBtnSubmit().setVisible(false);
                boolean buttonVisiblity = WorkflowConstants.getPendingStatus().equalsIgnoreCase(session.getWorkflowStatus());
                getBtnApprove().setVisible(buttonVisiblity);
                getBtnReject().setVisible(buttonVisiblity);
                getBtnCancel().setVisible(buttonVisiblity);

            } else if (WorkflowConstants.getCreatorConstant().equals(session.getWorkflowUserType())) {

                getBtnApprove().setVisible(false);
                getBtnReject().setVisible(false);
                getBtnCancel().setVisible(false);
                if (WorkflowConstants.getPendingStatus().equalsIgnoreCase(session.getWorkflowStatus())) {
                    getBtnWithdraw().setVisible(true);
                    getBtnSubmit().setVisible(false);
                    getBtnCancel().setVisible(false);
                } else if (WorkflowConstants.getWithdrawnStatus().equalsIgnoreCase(session.getWorkflowStatus()) || WorkflowConstants.getRejectedStatus().equalsIgnoreCase(session.getWorkflowStatus())) {
                    getBtnWithdraw().setVisible(false);
                    getBtnSubmit().setVisible(true);
                    getBtnCancel().setVisible(false);
                } else if (WorkflowConstants.getApprovedStatus().equals(session.getWorkflowStatus()) || WorkflowConstants.getCancelledStatus().equals(session.getWorkflowStatus())) {
                    getBtnWithdraw().setVisible(false);
                    getBtnSubmit().setVisible(false);
                }
            }
        } else if (ACTION_VIEW.getConstant().equalsIgnoreCase(session.getAction())) {
            getBtnSubmit().setEnabled(false);
            getBtnSave().setEnabled(false);
        } else {
            getBtnApprove().setVisible(false);
            getBtnReject().setVisible(false);
            getBtnWithdraw().setVisible(false);
            getBtnCancel().setVisible(false);
        }
        configureForView();

    }
    
    
     private void configureForView() {

        if (Constant.VIEW.equalsIgnoreCase(session.getAction())) {
            AbstractForm.getBtnSave().setEnabled(false);
            AbstractForm.getBtnSubmit().setEnabled(false);
        }

    }

    public void saveLogic(boolean onSave) throws SystemException, PortalException {

        LOGGER.debug("Enters Save Logic");
        if ((AccrualRateUtils.ADD.equalsIgnoreCase(session.getAction()) && map.containsKey(Constant.IS_SALES_GENERATED) && map.containsKey(Constant.IS_RATES_GENERATED))
                || (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) && map.containsKey(Constant.IS_SALES_GENERATED) && map.containsKey(Constant.IS_RATES_GENERATED))
                || (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) && !map.containsKey(Constant.IS_SALES_GENERATED) && !map.containsKey(Constant.IS_RATES_GENERATED))) {
            LOGGER.debug("Enters valid save scenario");
            //To wait the thread to complete
            for (int i = 0; onSave && i < session.getNumberOfThreads(); i++) {
                CommonUtil.getInstance().waitFor(session.getThread(i));
            }

            // Count Down latch to check if all the seven threads are alive or dead.
            CountDownLatch latch = new CountDownLatch(NumericConstants.SEVEN);
            if (AccrualRateUtils.ADD.equalsIgnoreCase(session.getAction()) && !heirarchySaved) {
                DataSelectionLogic.accrualDetailsInsert(session.getProjectionId(), session.getCurrentTableNames());
                heirarchySaved = true;
            }
            dsLogic.saveAccrualTab(session, latch);
            try {
                latch.await();
            } catch (InterruptedException ex) {
                LOGGER.error(ex.getMessage());
            }
            getSales().saveTabSelection();
            rates.saveTabSelection();
            details.saveTabSelection();
            dsLogic.updateSaveFlag(session.getProjectionId(), session.getUserId());
            dsLogic.saveCurrentFile(session.getProjectionId());

            if (onSave) {

                Notification.show("Save Complete");
            }
        } else {
            NotificationUtils.getErrorNotification("Not all Required Fields are Populated", "Please ensure that both Sales and Rates tabs have been generated with data before you save.");
        }
        LOGGER.debug("Exits Save Logic");
    }

    protected void buttonEnableLogic(int tabPosition, int i) {
        btnPrev.setEnabled(tabPosition != 0);
        btnNext.setEnabled(tabPosition != i);
        btnSave.setVisible(tabPosition == i);
    }

    /**
     * Configure fields.
     */
    private void configureFields() {
        btnPrev.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                btnPreviousLogic();
            }
        });
        btnNext.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                btnNextLogic();
            }
        });

        btnSave.setVisible(false);
    }

    /**
     * Moves to previous tab.
     */
    protected void btnPreviousLogic() {
        if (!AccrualRateUtils.ADD_CASE.equals(session.getAction())) {
            tabLoadInitialization();
        }
        tabSheet.setSelectedTab(tabPosition - 1);
    }

    /**
     * Moves to next tab.
     */
    protected void btnNextLogic() {
        if (AccrualRateUtils.ADD_CASE.equals(session.getAction())) {
            if (!map.containsKey(Constant.IS_SALES_GENERATED) && lastPosition <= NumericConstants.THREE && tabPosition == 1) {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        tabSheet.setSelectedTab(tabPosition + 1);
                    }

                    @Override
                    public void noMethod() {
                        return;
                    }
                }.getOkCancelMessage("No Sales Basis has been populated for the Products in the list view", "No Sales values have been populated. If you proceed to the Rates tab, no Accrual Rates will be available.");

            } else if (!map.containsKey(Constant.IS_SALES_GENERATED) && lastPosition <= NumericConstants.THREE && tabPosition == NumericConstants.TWO) {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        tabSheet.setSelectedTab(tabPosition + 1);

                    }

                    @Override
                    public void noMethod() {
                        return;
                    }
                }.getOkCancelMessage("No Accrual Rates have been populated for the Products in the list view", "No Accrual Rate values have been populated. If you proceed to the Details tab, no Accrual Rates will be available.");

            } else {
                tabSheet.setSelectedTab(tabPosition + 1);
            }
        } else {
            tabLoadInitialization();
            tabSheet.setSelectedTab(tabPosition + 1);
        }
    }

    public void onTabChange() {

        if (tabPosition == 1) {

            this.getSales().setDefaultFocus();
        }

        if (AccrualRateUtils.ADD_CASE.equals(session.getAction())) {
            if (!map.containsKey(Constant.IS_SALES_GENERATED) && lastPosition <= 1 && tabPosition > 1) {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        return;
                    }

                    @Override
                    public void noMethod() {
                        TabSheet.Tab tabToReset = tabSheet.getTab(NumericConstants.TWO);
                        tabSheet.removeTab(tabToReset);
                        tabSheet.addTab(rates, AccrualRateUtils.RATES, null, NumericConstants.TWO);
                        tabSheet.removeSelectedTabChangeListener(tabChangeListener);
                        tabSheet.setSelectedTab(1);
                        lastPosition = tabPosition;
                        tabPosition = 1;
                        tabSheet.addSelectedTabChangeListener(tabChangeListener);
                    }
                }.getOkCancelMessage("No Sales Basis has been populated for the Products in the list view", "No Sales values have been populated. If you proceed to the Rates tab, no Accrual Rates will be available.");

            }
            if (!map.containsKey(Constant.IS_SALES_GENERATED) && lastPosition == NumericConstants.TWO && tabPosition > NumericConstants.TWO) {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        return;
                    }

                    @Override
                    public void noMethod() {
                        TabSheet.Tab tabToReset = tabSheet.getTab(NumericConstants.THREE);

                        tabSheet.removeTab(tabToReset);
                        tabSheet.addTab(details, AccrualRateUtils.DETAILS, null, NumericConstants.THREE);
                        tabSheet.removeSelectedTabChangeListener(tabChangeListener);
                        tabSheet.setSelectedTab(NumericConstants.TWO);
                        lastPosition = tabPosition;
                        tabPosition = NumericConstants.TWO;
                        tabSheet.addSelectedTabChangeListener(tabChangeListener);

                    }
                }.getOkCancelMessage("No Accrual Rates have been populated for the Products in the list view", "No Accrual Rate values have been populated. If you proceed to the Details tab, no Accrual Rates will be available.");

            }
        } else {
            tabLoadInitialization();
        }
    }

    /**
     * Submits the projection. Saves and calls the workflow
     */
    private boolean submitProjection(final String notes, final String screenName, final List<NotesDTO> getUploadedData) throws SystemException, PortalException {

        NonMandatedLogic logic = new NonMandatedLogic();
        Map<String, Object> params = new HashMap<>();
        params.put("projectionId", session.getProjectionId());
        boolean workflowFlag = false;
        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || "add".equalsIgnoreCase(session.getAction()) || session.getWorkflowId() != 0) {

            String workflowStatus = logic.getWorkflowStatus(session.getProjectionId(), screenName);
            if (!workflowStatus.equals("R") && !workflowStatus.equals("W")) {
//                ProcessInstance processInstance = DSCalculationLogic.startARPWorkflow();
                User userModel = UserLocalServiceUtil.getUser(Long.parseLong(session.getUserId()));
                List<String> roleList = new ArrayList<>();
//                workflowFlag = DSCalculationLogic.isValidWorkflowUser(userModel, roleList, processInstance.getId());
//                Long processInstanceId = processInstance.getId();
                if (workflowFlag) {
                    saveLogic(false);
                    logic.deleteTempBySession();
                    try {
//                        TaskSummary taskSummary = DSCalculationLogic.startAndCompleteTask(userModel, session.getProjectionId(), processInstanceId);
//                        processInstanceId = taskSummary.getProcessInstanceId();
//                        session.setProcessId(processInstanceId);
                    } catch (Exception e) {
                        LOGGER.error(e.getMessage());
                    }
                    String workflowId = submitProjToWorkflow(params, notes, screenName, getUploadedData);
                    showSubmitNotification(workflowId);
                } else {
                    StringBuffer notiMsg = new StringBuffer("You dont have permission to submit a projection.");
                    if (!roleList.isEmpty()) {
                        notiMsg.append("\n Only " + roleList + " can submit a projection.");
                    }
                    NotificationUtils.getAlertNotification("Permission Denied", notiMsg.toString());
                }
            } else {
                saveLogic(false);
                logic.deleteTempBySession();
                String workflowId = submitProjToWorkflow(params, notes, screenName, getUploadedData);
                showSubmitNotification(workflowId);
            }
        } else {
            NotificationUtils.getErrorNotification(Constant.ERROR, MessageUtils.WFP_SUBMIT_ERROR);
        }
        return workflowFlag;
    }

    private String submitProjToWorkflow(Map<String, Object> params, final String notes, final String screenName, final List<NotesDTO> getUploadedData) {
        String workflowId = "Not Saved";
        try {
            WorkflowRuleDTO dto = new WorkflowRuleDTO();
            dto.setNoOfUsers(NumericConstants.TWO);
            params.put("out_workflowDTO", dto);
            VarianceCalculationLogic.submitWorkflow( session.getProcessId(),session,GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
//            String noOfUsers = BPMProcessBean.getProcessVariableLog(session.getProcessId(), "NoOfUsers");
            String noOfUsers ="";
            if (!noOfUsers.isEmpty()) {
                workflowId = submitToWorkflow(notes, Integer.parseInt(noOfUsers), screenName, getUploadedData);
            }
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        }
        return workflowId;
    }

    private void showSubmitNotification(String workflowId) {
        if (workflowId != null && !workflowId.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {
            callWorkflowInboxRefresh();
            MessageBox.showPlain(Icon.INFO, Constant.SUBMITTED + " Successfully ", " Workflow Id: " + workflowId + "  ", new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                @Override
                public void buttonClicked(final ButtonId buttonId) {
                    if (session.getWorkflowId() != 0) {
                        getBtnSave().setEnabled(false);
                        getBtnSubmit().setEnabled(false);
                    } else {
                        UI.getCurrent().getNavigator().navigateTo(ForecastMainView.NAME);
                    }
                }
            }, ButtonId.OK);

        } else {
            NotificationUtils.getErrorNotification(Constant.ERROR, "The Data not saved properly");
        }
    }

    /**
     * Called on submitting. Starts the workflow
     */
    private String submitToWorkflow(String notes, int noOfApprovals, String screenName, List<NotesDTO> getUploadedData) {
        NonMandatedLogic logic = new NonMandatedLogic();
        return logic.submitProjection(session.getProjectionId(), session.getUserId(), notes, noOfApprovals, screenName, getUploadedData, session.getDescription());
    }

    private void callWorkflowInboxRefresh() {
        JavaScript.getCurrent().execute("localStorage.setItem('" + WorkflowConstants.getBusinessProcessNameAccurals() + "', 'true');");
    }

    /**
     * To load the tab on change and to maintain the
     */
    private void tabLoadInitialization() {
        if (tabPosition == NumericConstants.TWO && !isRatesLoaded) {
            //To check wheather the Rates merge thread is running
            CommonUtil.getInstance().waitFor(session.getThread(NumericConstants.ONE));
            CommonUtil.getInstance().waitFor(session.getThread(NumericConstants.TWO));
            isRatesLoaded = Boolean.TRUE;
            rates.configureOnLoad();
        } else if (tabPosition == NumericConstants.THREE && !isDetailsLoaded) {
            //To check the rates tab is loaded or not
            if (!isRatesLoaded) {
                CommonUtil.getInstance().waitFor(session.getThread(NumericConstants.ONE));
                CommonUtil.getInstance().waitFor(session.getThread(NumericConstants.TWO));
            }
            //To check wheather the Details merge thread is running
            CommonUtil.getInstance().waitFor(session.getThread(NumericConstants.THREE));
            isDetailsLoaded = Boolean.TRUE;
            details.configureOnLoad();
        }
    }

    private void insertFileData() {
        String sessionId = "'" + session.getSessionId() + "'";
        String query = SQlUtil.getQuery("Product_customer_files_insert")
                .replace("?PROJECTION_ID", String.valueOf(session.getProjectionId()))
                .replace("?USER_ID", String.valueOf(session.getUserId()))
                .replace("?SESSION_ID", sessionId);
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
    }

    public void configurePermission() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(session.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Accrual Rate Projection" + "," + "Details");
            if (functionHM.get("nextBtn") != null && !((AppPermission) functionHM.get("nextBtn")).isFunctionFlag()) {
                nextBtn.setVisible(false);
            } else {
                nextBtn.setVisible(true);
            }
            if (functionHM.get("prevBtn") != null && !((AppPermission) functionHM.get("prevBtn")).isFunctionFlag()) {
                prevBtn.setVisible(false);
            } else {
                prevBtn.setVisible(true);
            }
            if (functionHM.get("btnSubmit") != null && !((AppPermission) functionHM.get("btnSubmit")).isFunctionFlag()) {
                btnSubmit.setVisible(false);
            } else {
                btnSubmit.setVisible(btnSubmit.isVisible());
            }
            if (functionHM.get("btnClose") != null && !((AppPermission) functionHM.get("btnClose")).isFunctionFlag()) {
                btnClose.setVisible(false);
            } else {
                btnClose.setVisible(true);
            }
            if (functionHM.get("btnSave") != null && !((AppPermission) functionHM.get("btnSave")).isFunctionFlag()) {
                btnSave.setVisible(false);
            } else {
                btnSave.setVisible(true);
            }

        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

	public Sales getSales() {
		return sales;
	}

	public void setSales(Sales sales) {
		this.sales = sales;
	}

}
