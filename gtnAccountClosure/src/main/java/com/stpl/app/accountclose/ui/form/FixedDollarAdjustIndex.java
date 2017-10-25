/* * To 
 change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dto.FixedDollarDTO;
import com.stpl.app.accountclose.logic.FixedDollarCalculationLogic;
import com.stpl.app.accountclose.lookups.NotesPopup;
import com.stpl.app.accountclose.security.StplSecurity;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class FixedDollarAdjustIndex extends CustomComponent implements View {

    /**
     * The tab sheet.
     */
    @UiField("tabSheet")
    TabSheet tabSheet;
    /**
     * The tab lazy load map.
     */
    Map<Integer, Boolean> tabLazyLoadMap = new HashMap<>();
    /**
     * The previous btn.
     */
    @UiField("approveBtn")
    Button approveBtn;
    /**
     * The reset btn.
     */
    @UiField("rejectBtn")
    Button rejectBtn;
    /**
     * The previous btn.
     */
    @UiField("cancelBtn")
    Button cancelBtn;
    /**
     * The next btn.
     */
    @UiField("saveBtn")
    Button saveBtn;
    /**
     * The close.
     */
    @UiField("nextTabBtn")
    Button nextTabBtn;
    /**
     * The refresh btn.
     */
    @UiField("submitBtn")
    Button submitBtn;
    /**
     * The submit.
     */
    @UiField("closeBtn")
    Button closeBtn;
    @UiField("previousBtn")
    Button previousBtn;
    int tabPosition = 0;
    private FixedDollarAdjustmentDetails fixedDollarAdjustmentDetails;
    NotesTabForm notesTabForm;
    SessionDTO session;
    private FixedDollarDTO fixedDollarDTO;
    final ErrorLabel errorMsg = new ErrorLabel();
    private final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(FixedDollarAdjustIndex.class);
    /**
     * The data selection binder.
     */
    public CustomFieldGroup fixedDollarBinder;
    boolean tabFlag = false;
    CommonLogic logic = new CommonLogic();
    String WorkFlowid = StringUtils.EMPTY;
    private String wfId = StringUtils.EMPTY;
    public FixedDollarCalculationLogic fixedDollarCalculationLogic = new FixedDollarCalculationLogic();
    CommonUtils utils = new CommonUtils();
    @UiField("stopBtn")
    Button stopBtn;
    @UiField("withdrawBtn")
    Button withdrawBtn;
    CommonUtil commonutil=CommonUtil.getInstance();

    /**
     *
     * @param screenIndicator
     * @param fixedDollarDTO
     * @param custom
     * @throws Exception
     */
    public FixedDollarAdjustIndex(String screenIndicator, FixedDollarDTO fixedDollarDTO, CustomFieldGroup custom, SessionDTO sessionDTO) throws Exception {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/fixedDollarAdjustForm.xml"), this));
        this.fixedDollarDTO = fixedDollarDTO;
        this.fixedDollarBinder = custom;
        this.session = sessionDTO;
        configureFields();
        init();
        addTab();
        tabSheet.setSelectedTab(0);
    }

    private void configureFields() throws PortalException, Exception {
        previousBtn.setVisible(false);

    }

    private void init() throws Exception {
        addSecurityForButtons();
        fixedDollarAdjustmentDetails = new FixedDollarAdjustmentDetails(session, fixedDollarDTO, false);
          notesTabForm = new NotesTabForm(fixedDollarBinder, "Fixed Dollar Adjustment");
        if (session.getAcctCloserMasterId() == 0) {
            approveBtn.setEnabled(false);
            rejectBtn.setEnabled(false);
            withdrawBtn.setEnabled(false);
            stopBtn.setEnabled(false);
            cancelBtn.setEnabled(false);

        } else {
            if ("Pending".equalsIgnoreCase(session.getWorkflowStatus())) {

                approveBtn.setEnabled(true);
                rejectBtn.setEnabled(true);
                stopBtn.setEnabled(false);
                withdrawBtn.setEnabled(true);
                cancelBtn.setEnabled(true);
                saveBtn.setEnabled(false);
                nextTabBtn.setEnabled(false);
                submitBtn.setEnabled(false);

            }
            if ("Withdrawn".equalsIgnoreCase(session.getWorkflowStatus())) {
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                withdrawBtn.setEnabled(false);
                stopBtn.setEnabled(false);
                submitBtn.setEnabled(true);
                cancelBtn.setEnabled(true);
                nextTabBtn.setEnabled(true);
                saveBtn.setEnabled(false);

            }
            if ("Rejected".equalsIgnoreCase(session.getWorkflowStatus())) {
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                withdrawBtn.setEnabled(false);
                stopBtn.setEnabled(false);
                submitBtn.setEnabled(true);
                cancelBtn.setEnabled(true);
                nextTabBtn.setEnabled(true);
                saveBtn.setEnabled(false);
            }
            if ("Approved".equalsIgnoreCase(session.getWorkflowStatus())) {
                stopBtn.setEnabled(true);
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                withdrawBtn.setEnabled(false);
                submitBtn.setEnabled(false);
                cancelBtn.setEnabled(false);
                nextTabBtn.setEnabled(false);
                saveBtn.setEnabled(false);

            }
            if ("Cancelled".equalsIgnoreCase(session.getWorkflowStatus())) {
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                stopBtn.setEnabled(false);
                withdrawBtn.setEnabled(false);
                cancelBtn.setEnabled(false);
                saveBtn.setEnabled(false);
                nextTabBtn.setEnabled(false);
                submitBtn.setEnabled(false);
                saveBtn.setEnabled(false);
            }

        }

    }
     public void addSecurityForButtons() throws SystemException, PortalException {
        LOGGER.info("Entering addToContent");
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId,Constants.FIXED_DOLLAR_ADJUSTMENT +","+"Adjustment Detail");
         if ((functionCfpHM.get(Constants.APPROVE))!= null && ((AppPermission) functionCfpHM.get(Constants.APPROVE)).isFunctionFlag()) {
            approveBtn.setVisible(true);
        }else{
            approveBtn.setVisible(false);
        }
        if ((functionCfpHM.get(Constants.REJECT))!= null && ((AppPermission) functionCfpHM.get(Constants.REJECT)).isFunctionFlag()) {
            rejectBtn.setVisible(true);
        }else{
            rejectBtn.setVisible(false);
        }
         if ((functionCfpHM.get(Constants.STOP))!= null && ((AppPermission) functionCfpHM.get(Constants.STOP)).isFunctionFlag()) {
            stopBtn.setVisible(true);
        }else{
            stopBtn.setVisible(false);
        }
        if ((functionCfpHM.get(Constants.WITHDRAW))!= null && ((AppPermission) functionCfpHM.get(Constants.WITHDRAW)).isFunctionFlag()) {
            withdrawBtn.setVisible(true);
        }else{
            withdrawBtn.setVisible(false);
        }
        if ((functionCfpHM.get(Constants.CANCELWORKFLOW))!= null && ((AppPermission) functionCfpHM.get(Constants.CANCELWORKFLOW)).isFunctionFlag()) {
            cancelBtn.setVisible(true);
        }else{
            cancelBtn.setVisible(false);
        }
         if ((functionCfpHM.get(Constants.SAVE))!= null && ((AppPermission) functionCfpHM.get(Constants.SAVE)).isFunctionFlag()) {
            saveBtn.setVisible(true);
        }else{
            saveBtn.setVisible(false);
        }  
           if ((functionCfpHM.get(Constants.SUBMIT))!= null && ((AppPermission) functionCfpHM.get(Constants.SUBMIT)).isFunctionFlag()) {
            submitBtn.setVisible(true);
        }else{
            submitBtn.setVisible(false);
        } 
        LOGGER.info("Ending addToContent");
    }

    private void addTab() {
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);
        tabSheet.markAsDirty();
        tabSheet.markAsDirtyRecursive();
        tabSheet.addTab(fixedDollarAdjustmentDetails, "Adjustment Detail", null, 0);
        tabSheet.addTab(notesTabForm, "Additional Information", null, 1);
        attachTabChangeListener();
    }

    private void attachTabChangeListener() {
        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                tabPosition = event.getTabSheet().getTabPosition(tab);
                buttonEnableLogic(tabPosition, tabSheet.getComponentCount() - 1);
                try {
                    onTabChange(tabPosition);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }

    protected void buttonEnableLogic(int tabPosition, int i) {
        if (tabPosition == 0) {
            previousBtn.setVisible(false);
        } else {
            previousBtn.setVisible(true);
        }
        if (tabPosition == i) {
            nextTabBtn.setVisible(false);
            previousBtn.setVisible(true);
        } else {
            nextTabBtn.setVisible(true);
        }
    }

    /**
     * On tab change.
     *
     * @param tabPosition the tab position
     */
    protected void onTabChange(int tabPosition) {
        tabSheet.setSelectedTab(tabPosition);
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {

        tabSheet.setSelectedTab(0);
    }

    /**
     * Next Button Click Logic
     *
     */
    protected void btnNextLogic() {
        tabSheet.setSelectedTab(tabPosition + 1);

        UI.getCurrent().setFocusedComponent(UI.getCurrent());
    }

    /**
     * Next Button Click Logic
     *
     * @param event
     */
    @UiHandler("nextTabBtn")
    public void nextTab(Button.ClickEvent event) {
        try {
            btnNextLogic();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Previous Button Click logic
     *
     * @param event
     */
    @UiHandler("previousBtn")
    public void previousTab(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, commonutil.getNavigationMessage(), commonutil.getBackBtnMessage(), new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("OK")) {
                    try {
                        btnPreviousLogic();
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }
        }, ButtonId.OK, ButtonId.CANCEL);
    }

    /**
     * Previous Button Logic
     *
     */
    protected void btnPreviousLogic() {
        tabSheet.setSelectedTab(tabPosition - 1);
        UI.getCurrent().setFocusedComponent(UI.getCurrent());

    }

    /**
     * Save Button Logic
     *
     * @param event
     */
    @UiHandler("saveBtn")
    public void btnSaveLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, commonutil.getSaveBtnHeader(), commonutil.getSaveBtnMessage(), new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("OK")) {
                    try {

                        final NotesPopup popup = new NotesPopup();
                        getUI().getCurrent().addWindow(popup);
                        popup.addCloseListener(new Window.CloseListener() {

                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                String notes = popup.getNotes().getValue();
                                try {
                                    Integer sid = fixedDollarAdjustmentDetails.getAccountMasterId();
                                    fixedDollarCalculationLogic.updateAccMaster(sid);
                                    wfId = setWorkflow("Open", true, StringUtils.EMPTY, notes);
                                    fixedDollarCalculationLogic.workflowAdjustmentselection(sid);
                                    Notification notif = new Notification("Workflow " + wfId + " has been saved successfully.",
                                            Notification.Type.HUMANIZED_MESSAGE);
                                    notif.setPosition(Position.BOTTOM_CENTER);
                                    notif.setDelayMsec(3000);
                                    notif.show(Page.getCurrent());
                                } catch (Exception ex) {
                                    LOGGER.error(ex);
                                }
                            }
                        });
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }
        }, ButtonId.OK, ButtonId.CANCEL
        );
    }

    /**
     * Base Rate Approve Workflow Logic
     *
     * @param event
     */
    @UiHandler("approveBtn")
    public void approveWorkflowLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, commonutil.getapproveBtnHeader(), commonutil.getapproveBtnMessage(), new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            final NotesPopup popup = new NotesPopup();
                            getUI().getCurrent().addWindow(popup);
                            popup.addCloseListener(new Window.CloseListener() {

                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    String notes = popup.getNotes().getValue();
                                    try {
                                        setWorkflow("Approved", false, String.valueOf(session.getWorkflow()), notes);
                                        Notification notif = new Notification("Workflow" + session.getWorkflow() + " has been approved successfully.",
                                                Notification.Type.HUMANIZED_MESSAGE);
                                        notif.setPosition(Position.BOTTOM_CENTER);
                                        notif.setDelayMsec(3000);
                                        notif.show(Page.getCurrent());
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            });

                        }
                    }
                }, ButtonId.YES, ButtonId.NO);

    }

    /**
     * Base Rate Cancel Workflow Logic
     *
     * @param event
     */
    @UiHandler("cancelBtn")
    public void cancelWorkflowLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Cancel Confirmation", " You are about to cancel Fixed Dollar Adjustment workflow " + session.getWorkflow()
                + "  Please click OK if you would like to continue.?", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            try {
                                final NotesPopup popup = new NotesPopup();
                                getUI().getCurrent().addWindow(popup);
                                popup.addCloseListener(new Window.CloseListener() {

                                    @Override
                                    public void windowClose(Window.CloseEvent e) {
                                        String notes = popup.getNotes().getValue();
                                        setWorkflow("Cancelled", false, String.valueOf(session.getWorkflow()), notes);
                                        Notification notif = new Notification("Workflow" + session.getWorkflow() + " has been cancelled successfully.",
                                                Notification.Type.HUMANIZED_MESSAGE);
                                        notif.setPosition(Position.BOTTOM_CENTER);
                                        notif.setDelayMsec(3000);
                                        notif.show(Page.getCurrent());
                                    }
                                });

                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
    }

    /**
     * Base Rate Submit Workflow Logic
     *
     * @param event
     */
    @UiHandler("submitBtn")
    public void submitWorkflowLogic(Button.ClickEvent event) {
        if (StringUtils.isNotBlank(notesTabForm.getInternalNotes().getValue())
                && notesTabForm.getReasonCode().getValue() != null
                && !"-Select One-".equalsIgnoreCase(String.valueOf(notesTabForm.getReasonCode().getValue()))) {
            if (session.getAcctCloserMasterId() != 0) {
                wfId = session.getWorkflow();
            }
            MessageBox.showPlain(Icon.QUESTION, "Submit workflow?", " You are about to submit " + wfId + ". Are you sure you wish to continue?", new MessageBoxListener() {
                public void buttonClicked(ButtonId buttonId) {
                    if (buttonId.name().equals("OK")) {
                        try {
                            final NotesPopup popup = new NotesPopup();
                            getUI().getCurrent().addWindow(popup);
                            popup.addCloseListener(new Window.CloseListener() {

                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    String notes = popup.getNotes().getValue();
                                    setWorkflow("Pending", false, wfId, notes);
                                    Notification notif = new Notification("Workflow " + wfId + " has been submitted successfully.",
                                            Notification.Type.HUMANIZED_MESSAGE);
                                    notif.setPosition(Position.BOTTOM_CENTER);
                                    notif.setDelayMsec(3000);
                                    notif.show(Page.getCurrent());
                                }
                            });

                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                }
            }, ButtonId.OK, ButtonId.CANCEL);
        } else {
            AbstractNotificationUtils.getErrorNotification(commonutil.getMessage("FDA_MSG_1015"), commonutil.getMessage("FDA_MSG_1016"));
        }
    }

    /**
     * Base Rate Workflow Reject Logic
     *
     * @param event
     */
    @UiHandler("rejectBtn")
    public void workflowRejectLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Confirmation", " Fixed Dollar Adjustment workflow  " + session.getWorkflow()
                + "  is about to be rejected.  Please click OK if you would like to continue.", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            try {
                                final NotesPopup popup = new NotesPopup();
                                getUI().getCurrent().addWindow(popup);
                                popup.addCloseListener(new Window.CloseListener() {

                                    @Override
                                    public void windowClose(Window.CloseEvent e) {
                                        String notes = popup.getNotes().getValue();
                                        setWorkflow("Rejected", false, String.valueOf(session.getWorkflow()), notes);
                                        Notification notif = new Notification("Workflow" + session.getWorkflow() + " has been rejected successfully.",
                                                Notification.Type.HUMANIZED_MESSAGE);
                                        notif.setPosition(Position.BOTTOM_CENTER);
                                        notif.setDelayMsec(3000);
                                        notif.show(Page.getCurrent());
                                    }
                                });

                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
    }

    /**
     * Close Button Logic
     *
     * @param event
     */
    @UiHandler("closeBtn")
    public void closeButtonLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, commonutil.getcloseBtnHeader(), commonutil.getcloseBtnMessage(), new MessageBoxListener() {
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals("YES")) {
                    try {
                        if (session.getAcctCloserMasterId() != 0) {
                            Page.getCurrent().getJavaScript().execute("window.open('','_parent','');window.close(); ");

                        } else {
                            btnPreviousLogic();
                        }

                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }
   
    public String getWorkFlowid() {
        return WorkFlowid;
    }

    public void setWorkFlowid(String WorkFlowid) {
        this.WorkFlowid = WorkFlowid;
    }

    public String setWorkflow(String status, boolean flag, String wfSId, String notes) {
        String wfId = StringUtils.EMPTY;
        if (flag) {
            wfId = fixedDollarCalculationLogic.setWorkflow(fixedDollarAdjustmentDetails.getAccountMasterId(), status, flag, StringUtils.EMPTY, notes);
        } else {
            wfId = fixedDollarCalculationLogic.setWorkflow(fixedDollarAdjustmentDetails.getAccountMasterId(), status, flag, wfSId, notes);
        }
        return wfId;

    }

    @UiHandler("stopBtn")
    public void workflowStopLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Stop Confirmation", "  Fixed Dollar Adjustment workflow  is about to be stopped.\n"
                + " Please click OK if you would like to continue.?", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            try {
                                if (fixedDollarCalculationLogic.stopDate(session.getAcctCloserMasterId())) {
                                    final NotesPopup popup = new NotesPopup();
                                    getUI().getCurrent().addWindow(popup);
                                    popup.addCloseListener(new Window.CloseListener() {

                                        @Override
                                        public void windowClose(Window.CloseEvent e) {
                                            String notes = popup.getNotes().getValue();
                                            setWorkflow("Stop", false, String.valueOf(session.getWorkflow()), notes);
                                            Notification notif = new Notification("Workflow" + session.getWorkflow() + " has been stopped successfully.",
                                                    Notification.Type.HUMANIZED_MESSAGE);
                                            notif.setPosition(Position.BOTTOM_CENTER);
                                            notif.setDelayMsec(3000);
                                            notif.show(Page.getCurrent());
                                        }
                                    });
                                } else {
                                    AbstractNotificationUtils.getErrorNotification(commonutil.getMessage("FDA_MSG_1017"), commonutil.getMessage("FDA_MSG_1018"));

                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
    }

    @UiHandler("withdrawBtn")
    public void withdrawWorkflowLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Withdraw Confirmation", "  You are about to Withdraw Fixed Dollar Adjustment workflow  "
                + " \n Please click OK if you would like to continue.?", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("OK")) {
                            try {

                                if (!"Pending".equalsIgnoreCase(session.getWorkflowStatus())) {
                                    AbstractNotificationUtils.getErrorNotification(commonutil.getMessage("FDA_MSG_1019"), commonutil.getMessage("FDA_MSG_1020"));
                                } else {
                                    final NotesPopup popup = new NotesPopup();
                                    getUI().getCurrent().addWindow(popup);
                                    popup.addCloseListener(new Window.CloseListener() {

                                        @Override
                                        public void windowClose(Window.CloseEvent e) {
                                            String notes = popup.getNotes().getValue();
                                            setWorkflow("Withdrawn", false, String.valueOf(session.getWorkflow()), notes);

                                            Notification notif = new Notification("Workflow" + session.getWorkflow() + " has been withdrawn successfully.",
                                                    Notification.Type.HUMANIZED_MESSAGE);
                                            notif.setPosition(Position.BOTTOM_CENTER);
                                            notif.setDelayMsec(3000);
                                            notif.show(Page.getCurrent());
                                        }
                                    });
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                }, ButtonId.OK, ButtonId.CANCEL);
    }

}
