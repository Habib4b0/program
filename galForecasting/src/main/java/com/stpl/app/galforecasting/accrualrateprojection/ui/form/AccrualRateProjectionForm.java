
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.accrualrateprojection.ui.form;

import com.stpl.app.bpm.dto.ForecastingRulesDTO;
import com.stpl.app.bpm.dto.WorkflowRuleDTO;
import com.stpl.app.galforecasting.abstractforecast.AbstractForm;
import com.stpl.app.galforecasting.accrualrateprojection.logic.DSLogic;
import com.stpl.app.galforecasting.accrualrateprojection.utils.AccrualRateUtils;
import com.stpl.app.galforecasting.bpm.logic.DSCalculationLogic;
import com.stpl.app.galforecasting.bpm.logic.VarianceCalculationLogic;
import com.stpl.app.galforecasting.bpm.service.BPMProcessBean;
import com.stpl.app.galforecasting.bpm.service.MailWorkItemHandler;
import com.stpl.app.galforecasting.bpm.util.MessageUtils;
import com.stpl.app.galforecasting.logic.NonMandatedLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.ForecastMainView;
import com.stpl.app.galforecasting.ui.form.DataSelection;
import com.stpl.app.galforecasting.ui.form.DataSelectionForm;
import com.stpl.app.galforecasting.ui.form.lookups.WorkFlowNotesLookup;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.workflow.dto.WorkflowMasterDTO;
import com.stpl.app.galforecasting.workflow.logic.WorkflowLogic;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.galforecasting.utils.AbstractNotificationUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.galforecasting.utils.NotificationUtils;
import static com.stpl.app.utils.Constants.LabelConstants.TAB_DATA_SELECTION;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.vaadin.data.util.BeanItem;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_NEXT;
import static com.stpl.app.utils.Constants.ButtonConstants.BTN_PREVIOUS;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_EDIT;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import org.jboss.logging.Logger;

/**
 *
 * @author sibi
 */
public class AccrualRateProjectionForm extends AbstractForm {

    private static final Logger LOGGER = Logger.getLogger(AccrualRateProjectionForm.class);

    public final Sales sales;

    public final Rates rates;

    private final Details details;

    private final DataSelection dataSelection;

    private final TabSheet tabSheet;

    private final CustomFieldGroup dataSelectionBinder;
    SessionDTO session;
    String screenName;
    DataSelectionForm dataSelectionForm;
    DSLogic dsLogic = new DSLogic();
    private final Button nextBtn = new Button(BTN_NEXT.getConstant());
    private final Button prevBtn = new Button(BTN_PREVIOUS.getConstant());
    private final Button refreshBtn = new Button("REFRESH");

    private int tabPosition;
    private int lastPosition;
    public static ResourceBundle alertMsg = ResourceBundle.getBundle("properties.alertmessage");

    TabSheet.SelectedTabChangeListener tabChangeListener = new TabSheet.SelectedTabChangeListener() {
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

    public AccrualRateProjectionForm(DataSelectionDTO dataSelectionDTO, SessionDTO session, String screenName, DataSelectionForm dataSelectionForm) throws Exception {
        this.session = session;
        this.screenName = screenName;
        this.dataSelectionForm = dataSelectionForm;
        tabSheet = new TabSheet();
        this.sales = new Sales(session, map);
        this.rates = new Rates(session, map);
        this.details = new Details(session, map);
        dataSelectionBinder = new CustomFieldGroup(new BeanItem<>(dataSelectionDTO));
        this.dataSelection = new DataSelection(dataSelectionBinder, dataSelectionDTO, session, screenName, dataSelectionForm);
        init();

    }

    private void init() {
        buildScreen();
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
            public void noMethod() {

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
                    Page.getCurrent().getJavaScript().execute("window.open('','_parent','');window.close(); ");
                } else {
                    try {
                        dataSelectionForm.closeBtn();

                        getUI().getNavigator().navigateTo(ForecastMainView.NAME);

                    } catch (Exception exception) {
                        LOGGER.error(exception);
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
                && !map.containsKey(Constant.IS_SALES_GENERATED) && !map.containsKey(Constant.IS_RATES_GENERATED))) {
            new AbstractNotificationUtils() {
                public void noMethod() {

                }

                @Override
                /**
                 * The method is triggered when Yes button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                public void yesMethod() {

                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {

                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                submitProjection(popup.getNotes().getValue(), screenName, popup.getUploadedData());
                                getUI().getNavigator().navigateTo(ForecastMainView.NAME);
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
            }.getConfirmationMessage("Submit Confirmation", "Are you sure you want to submit this Accrual Rate Projection for approval?"
                    + "YES/NO");
        } else {
            NotificationUtils.getErrorNotification("Not all Required Fields are Populated", "Please ensure that both Sales and Rates tabs have been generated with data before you submit.");
        }

    }

    @Override
    protected void initializeTabs() {

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

    }

    @Override
    protected void lazyLoadTab(int tabPosition) {

    }

    private void configureTabSheet() {
        tabSheet.setImmediate(true);
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.addTab(dataSelection, TAB_DATA_SELECTION.getConstant(), null, 0);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.addSelectedTabChangeListener(tabChangeListener);
        tabSheet.addTab(this.sales, AccrualRateUtils.SALES, null, 1);
        tabSheet.addTab(this.rates, AccrualRateUtils.RATES, null, 2);
        tabSheet.addTab(this.details, AccrualRateUtils.DETAILS, null, 3);
        tabSheet.setSelectedTab(1);

       
       // tabPosition = 1;
        onTabChange();
      //  sales.setDefaultFocus();
    }

    // Abstract Methods that are not used in this module.
    @Override
    protected void btnApproveLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Approve", "Are you sure you want to approve the projection " + " ?", new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {

                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {

                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                int projectionId = session.getProjectionId();
                                String userId = session.getUserId();
                                int userIdInt = Integer.parseInt(userId);
                                int workflowId = session.getWorkflowId();
                                WorkflowLogic wfLogic = new WorkflowLogic();
                                String workflowIdUpdate = StringUtils.EMPTY;
                                WorkflowMasterDTO wfMasterDto = new WorkflowMasterDTO();
                                wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getApprovedStatus(), popup.getNotes().getValue(), session.getApprovalLevel());
                                workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                if (session.getNoOfApproval() > session.getApprovalLevel()) {
                                    wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getPendingStatus(), StringUtils.EMPTY, session.getApprovalLevel() + 1);
                                    workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                }
                                if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {

                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("approveFlag", "approve");
                                    VarianceCalculationLogic.submitWorkflow(session.getUserId(), session.getProcessId(), params, "approve");
                                    callWorkflowInboxRefresh();
                                    AbstractNotificationUtils.getInfoNotification("Approved Information", "Workflow Id " + workflowIdUpdate + " approved successfully");
                                    // For Mail
                                    StringBuffer sb = new StringBuffer("Hi,<br /><br />");
                                    sb.append("The workflow with workflow Id " + workflowIdUpdate + " is Approved Succesfully.");
                                    sb.append("<br /><br />Thanks,<br />BPI Technical Team");
                                    MailWorkItemHandler.sendMail("support@bpitechnologies.com", "Workflow Approved Succesfully", sb);
                                    getBtnApprove().setEnabled(false);
                                    getBtnWithdraw().setEnabled(false);
                                    getBtnCancel().setEnabled(false);
                                    getBtnReject().setEnabled(false);
                                } else {
                                    CommonUIUtils.getMessageNotification("The projection not approved properly");
                                }
                            } catch (Exception ex) {
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
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
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
                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("approveFlag", "reject-RWC");
                                    VarianceCalculationLogic.submitWorkflow(session.getUserId(), session.getProcessId(), params, "reject");
                                    // For Mail
                                    callWorkflowInboxRefresh();
                                    AbstractNotificationUtils.getInfoNotification("Rejected Information ", "Workflow Id " + workflowIdUpdate + " rejected successfully");
                                    StringBuffer sb = new StringBuffer("Hi,<br /><br />");
                                    sb.append("The workflow with workflow Id " + workflowIdUpdate + " is Rejected Succesfully.");
                                    sb.append("<br /><br />Thanks,<br />BPI Technical Team");
                                    MailWorkItemHandler.sendMail("support@bpitechnologies.com", "Workflow Rejected Succesfully", sb);
                                    getBtnApprove().setEnabled(false);
                                    getBtnWithdraw().setEnabled(false);
                                    getBtnCancel().setEnabled(false);
                                    getBtnReject().setEnabled(false);
                                } else {
                                    CommonUIUtils.getMessageNotification("The projection not rejected properly");
                                }
                            } catch (Exception ex) {
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
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
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
                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("approveFlag", "withdraw-RWC");
                                    VarianceCalculationLogic.submitWorkflow(session.getUserId(), session.getProcessId(), params, "withdraw");
                                    callWorkflowInboxRefresh();
                                    AbstractNotificationUtils.getInfoNotification("Workflow withdrawn ", "Workflow Id " + workflowIdUpdate + " withdrawn successfully");
                                    // For Mail
                                    StringBuffer sb = new StringBuffer("Hi,<br /><br />");
                                    sb.append("The workflow with workflow Id " + workflowIdUpdate + " is Withdrawn Succesfully.");
                                    sb.append("<br /><br />Thanks,<br />BPI Technical Team");
                                    MailWorkItemHandler.sendMail("support@bpitechnologies.com", "Workflow Withdrawn Succesfully", sb);
                                    getBtnApprove().setEnabled(false);
                                    getBtnWithdraw().setEnabled(false);
                                    getBtnCancel().setEnabled(false);
                                    getBtnReject().setEnabled(false);
                                } else {
                                    CommonUIUtils.getMessageNotification("The projection not withdrawn properly");
                                }
                            } catch (Exception ex) {
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
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(Constant.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
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

                                    Map<String, Object> params = new HashMap<String, Object>();
                                    params.put("approveFlag", "cancel-RWC");

                                    VarianceCalculationLogic.submitWorkflow(session.getUserId(), session.getProcessId(), params, "cancel");
                                    callWorkflowInboxRefresh();
                                    AbstractNotificationUtils.getInfoNotification("Cancel Information", "Workflow Id " + workflowIdUpdate + " cancelled successfully");
                                    // For Mail
                                    StringBuffer sb = new StringBuffer("Hi,<br /><br />");
                                    sb.append("The workflow with workflow Id " + workflowIdUpdate + " is cancelled Succesfully.");
                                    sb.append("<br /><br />Thanks,<br />BPI Technical Team");
                                    MailWorkItemHandler.sendMail("support@bpitechnologies.com", "Workflow Cancelled Succesfully", sb);
                                    getBtnApprove().setEnabled(false);
                                    getBtnWithdraw().setEnabled(false);
                                    getBtnCancel().setEnabled(false);
                                    getBtnReject().setEnabled(false);
                                } else {
                                    CommonUIUtils.getMessageNotification("The projection not cancelled properly");
                                }
                            } catch (Exception ex) {
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

    }

    private void configureFooterButtons() {

        btnNext.setImmediate(true);
        btnNext.setVisible(true);

        btnPrev.setImmediate(true);
        btnPrev.setVisible(true);

        btnSubmit.setImmediate(true);
        btnSubmit.setVisible(true);

        btnClose.setImmediate(true);
        btnClose.setVisible(true);

        btnSave.setImmediate(true);
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
        } else {
            getBtnApprove().setVisible(false);
            getBtnReject().setVisible(false);
            getBtnWithdraw().setVisible(false);
            getBtnCancel().setVisible(false);
        }

    }


    public void saveLogic(boolean onSave) throws SystemException, PortalException {

        LOGGER.info("Enters Save Logic");
        if ((AccrualRateUtils.ADD.equalsIgnoreCase(session.getAction()) && map.containsKey("isSalesGenerated") && map.containsKey("isRatesGenerated"))
                || (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) && map.containsKey("isSalesGenerated") && map.containsKey("isRatesGenerated"))
                || (AccrualRateUtils.EDIT.equalsIgnoreCase(session.getAction()) && !map.containsKey("isSalesGenerated") && !map.containsKey("isRatesGenerated"))) {
            LOGGER.info("Enters valid save scenario");
            dsLogic.saveAccrualTab(session.getUserId(), session.getSessionId(), "AccrualMainSave");
            sales.saveTabSelection();
            rates.saveTabSelection();
            details.saveTabSelection();
            dsLogic.updateSaveFlag(session.getProjectionId(), session.getUserId());
            dsLogic.saveCurrentFile(session.getProjectionId());


            if(onSave) {

                Notification.show("Save Complete");
            }
        } else {
            NotificationUtils.getErrorNotification("Not all Required Fields are Populated", "Please ensure that both Sales and Rates tabs have been generated with data before you save.");
        }
        LOGGER.info("Exits Save Logic");
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
      //  tabSheet.setSelectedTab(1);
        btnPrev.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                btnPreviousLogic();
            }
        });
        btnNext.addClickListener(new Button.ClickListener() {
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
        tabSheet.setSelectedTab(tabPosition - 1);
    }

    /**
     * Moves to next tab.
     */
    protected void btnNextLogic() {
        if (AccrualRateUtils.add.equals(session.getAction())) {
            if (!map.containsKey("isSalesGenerated") && lastPosition <= 3 && tabPosition == 1) {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        tabSheet.setSelectedTab(tabPosition + 1);
                    }

                    @Override
                    public void noMethod() {
                    }
                }.getOkCancelMessage("No Sales Basis has been populated for the Products in the list view", "No Sales values have been populated. If you proceed to the Rates tab, no Accrual Rates will be available.");

            } else if (!map.containsKey("isRatesGenerated") && lastPosition <= 3 && tabPosition == 2) {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        tabSheet.setSelectedTab(tabPosition + 1);

                    }

                    @Override
                    public void noMethod() {

                    }
                }.getOkCancelMessage("No Accrual Rates have been populated for the Products in the list view", "No Accrual Rate values have been populated. If you proceed to the Details tab, no Accrual Rates will be available.");

            } else {
                tabSheet.setSelectedTab(tabPosition + 1);
            }
        } else {
            tabSheet.setSelectedTab(tabPosition + 1);
        }
    }

    public void onTabChange() {
       
       
        if (tabPosition == 1) {
           
            this.sales.setDefaultFocus();
        }

        if (AccrualRateUtils.add.equals(session.getAction())) {
            if (!map.containsKey("isSalesGenerated") && lastPosition <= 1 && tabPosition > 1) {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                    }

                    @Override
                    public void noMethod() {
                        TabSheet.Tab tabToReset = tabSheet.getTab(2);
                        tabSheet.removeTab(tabToReset);
                        tabSheet.addTab(rates, AccrualRateUtils.RATES, null, 2);
                        tabSheet.removeSelectedTabChangeListener(tabChangeListener);
                        tabSheet.setSelectedTab(1);
                        lastPosition = tabPosition;
                        tabPosition = 1;
                        tabSheet.addSelectedTabChangeListener(tabChangeListener);
                    }
                }.getOkCancelMessage("No Sales Basis has been populated for the Products in the list view", "No Sales values have been populated. If you proceed to the Rates tab, no Accrual Rates will be available.");

            }
            if (!map.containsKey("isRatesGenerated") && lastPosition == 2 && tabPosition > 2) {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                    }

                        
                    @Override
                    public void noMethod() {
                         TabSheet.Tab tabToReset = tabSheet.getTab(3);

                        tabSheet.removeTab(tabToReset);
                        tabSheet.addTab(details, AccrualRateUtils.DETAILS, null, 3);
                        tabSheet.removeSelectedTabChangeListener(tabChangeListener);
                        tabSheet.setSelectedTab(2);
                        lastPosition = tabPosition;
                        tabPosition = 2;
                        tabSheet.addSelectedTabChangeListener(tabChangeListener);

                    }
                }.getOkCancelMessage("No Accrual Rates have been populated for the Products in the list view", "No Accrual Rate values have been populated. If you proceed to the Details tab, no Accrual Rates will be available.");

            }
        }

    }

    /**
     * Submits the projection. Saves and calls the workflow
     */


    private void submitProjection(final String notes, final String screenName, final List<NotesDTO> getUploadedData) throws SystemException, Exception {
        

        if (ACTION_EDIT.getConstant().equalsIgnoreCase(session.getAction()) || "add".equalsIgnoreCase(session.getAction()) || session.getWorkflowId() != 0) {
            NonMandatedLogic logic = new NonMandatedLogic();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("projectionId", session.getProjectionId());
            List<ForecastingRulesDTO> list = DSCalculationLogic.getProjectionValuesForAccrual(session.getProjectionId(), session.getUserId(), session.getSessionId());
            for (ForecastingRulesDTO forecastingRulesDTO : list) {
                params.put("out_" + forecastingRulesDTO.getVariableName(), forecastingRulesDTO);
            }
            WorkflowRuleDTO dto = new WorkflowRuleDTO();

            params.put("out_workflowDTO", dto);            

            saveLogic(false);
            logic.deleteTempBySession(session, screenName);
            VarianceCalculationLogic.submitWorkflow(session.getUserId(), session.getProcessId(), params, "submit");
            String autoApproval = BPMProcessBean.getProcessVariableLog(session.getProcessId(), "Auto_Approval");
            String noOfUsers = BPMProcessBean.getProcessVariableLog(session.getProcessId(), "NoOfUsers");

             if(!autoApproval.isEmpty() && !noOfUsers.isEmpty()){
            LOGGER.info("autoApproval  : " + autoApproval);
            String workflowId = submitToWorkflow(notes, Integer.parseInt(noOfUsers), screenName, getUploadedData);
            String approvedFlag = StringUtils.EMPTY;
            if ("true".equals(autoApproval)) {
                WorkflowLogic wfLogic = new WorkflowLogic();
                WorkflowMaster wm = wfLogic.getWorkflowMasterByProjectionId(session.getProjectionId());
                WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(session.getProjectionId(), Integer.valueOf(wm.getWorkflowMasterSid()), Integer.valueOf(session.getUserId()), WorkflowConstants.getApprovedStatus(), notes, session.getApprovalLevel());
                workflowId = wfLogic.updateWorkflow(wfMasterDto);
                approvedFlag = "Submitted and Approved";
            } else { 
                approvedFlag = Constant.SUBMITTED;
            }

            if (workflowId != null && !workflowId.trim().equals(CommonUtils.WORKFLOW_NOT_SAVED)) {
                callWorkflowInboxRefresh();
                MessageBox.showPlain(Icon.INFO, approvedFlag + " Successfully ", " Workflow Id: " + workflowId + "  ", new MessageBoxListener() {
                    /**
                     * The method is triggered when a button of the message box
                     * is pressed .
                     *
                     * @param buttonId The buttonId of the pressed button.
                     */
                    @SuppressWarnings("PMD")
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
                NotificationUtils.getErrorNotification("Error", "The Data not saved properly");
            }
        } else {
                new AbstractNotificationUtils() {                

                    @Override
                    public void noMethod() {

                    }

                    @Override

                    public void yesMethod() { 

                        try {
                            submitProjection(notes, screenName, getUploadedData);
                        } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(AccrualRateProjectionForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }.getConfirmationMessage("Error", "Something went wrong while submitting projection. Are you sure you want to proceed with submit?");
            }
        } else {
            NotificationUtils.getErrorNotification("Error", MessageUtils.WFP_SUBMIT_ERROR);
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
            JavaScript.getCurrent().execute("localStorage.setItem('"+WorkflowConstants.getBusinessProcessNameAccurals()+"', 'true');");
    }
}

