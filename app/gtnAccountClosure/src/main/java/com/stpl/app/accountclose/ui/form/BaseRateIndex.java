
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.dto.WorkflowMasterDTO;
import com.stpl.app.accountclose.logic.BaseRateCalculationLogic;
import com.stpl.app.accountclose.lookups.NotesPopup;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.errorhandling.ErrorLabel;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
public class BaseRateIndex extends CustomComponent implements View {

    private BaseRateDTO baseRateDTO;
    final ErrorLabel errorMsg = new ErrorLabel();
    CommonLogic logic = new CommonLogic();
    BaseRateCalculationLogic brlogic = new BaseRateCalculationLogic();
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(BaseRateIndex.class);
    /**
     * The data selection binder.
     */
    public CustomFieldGroup baseRateBinder;
    boolean tabFlag = false;
    /**
     * The tab sheet.
     */
    @UiField("tabSheet")
    TabSheet tabSheet;
    /**
     * The tab lazy load map.
     */
    Map<Integer, Boolean> tabLazyLoadMap = new HashMap<Integer, Boolean>();
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

    @UiField("stopBtn")
    Button stopBtn;
    @UiField("withdrawBtn")
    Button withdrawBtn;
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
    private Button previousBtn;
    /**
     * Position of the tab.s
     */
    int tabPosition = 0;

    List<Integer> tabList = new ArrayList<Integer>();
    SessionDTO session;
    private BaseRateCalculation baseRateCalc;
     private BaseRateSummary baseRateSummary;
    private BaseRateHistory baseRateHistory;
    private AdditionalInformation additionalInformation;
    private WorkflowMasterDTO wfId = new WorkflowMasterDTO();
    CommonUtils utils = new CommonUtils();
    int userId = Integer.parseInt(VaadinSession.getCurrent().getAttribute(Constants.USER_ID).toString());

    /**
     *
     * @param screenIndicator
     * @param baseRateDTO
     * @param custom
     * @throws Exception
     */
    public BaseRateIndex(String screenIndicator, BaseRateDTO baseRateDTO, CustomFieldGroup custom, SessionDTO session) throws Exception {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/baseRateForm.xml"), this));
        this.baseRateDTO = baseRateDTO;
        this.session = session;
        this.baseRateBinder = custom;
        configureFields();
        init();
        addTab();
        tabSheet.setSelectedTab(0);

        baseRateCalc.company.focus();
    }

    private void init() throws Exception {
        baseRateCalc = new BaseRateCalculation(session, baseRateDTO, false, new ArrayList<String>(), "0", null, null);
        baseRateSummary = new BaseRateSummary(session, baseRateDTO);
        baseRateHistory = new BaseRateHistory(session, baseRateDTO);
        additionalInformation = new AdditionalInformation(session);
    }

    private void addTab() {
        tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
        tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        tabSheet.setImmediate(true);
        tabSheet.markAsDirty();
        tabSheet.markAsDirtyRecursive();
        tabSheet.addTab(baseRateCalc, "Base Rate Calculation", null, 0);
        tabSheet.addTab(baseRateSummary, "Base Rate Summary", null, 1);
        tabSheet.addTab(baseRateHistory, "Base Rate History", null, 2);
        tabSheet.addTab(additionalInformation, "Additional Information", null, 3);
        attachTabChangeListener();

        if (!"yes".equals(session.getWorkflowCanApprove())) {
            approveBtn.setEnabled(false);
            rejectBtn.setEnabled(false);
            submitBtn.setEnabled(true);
            if ("open".equalsIgnoreCase(session.getWorkflowStatus())) {
                submitBtn.setEnabled(true);
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                cancelBtn.setEnabled(false);
                withdrawBtn.setEnabled(false);
                closeBtn.setEnabled(true);
                saveBtn.setEnabled(false);
                stopBtn.setEnabled(false);
            } else if ("Pending".equalsIgnoreCase(session.getWorkflowStatus())) {
                submitBtn.setEnabled(false);
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                cancelBtn.setEnabled(true);
                withdrawBtn.setEnabled(true);
                closeBtn.setEnabled(true);
                saveBtn.setEnabled(false);
                stopBtn.setEnabled(false);
            } else if ("withdrawn".equalsIgnoreCase(session.getWorkflowStatus()) || "rejected".equalsIgnoreCase(session.getWorkflowStatus())) {
                submitBtn.setEnabled(true);
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                cancelBtn.setEnabled(true);
                withdrawBtn.setEnabled(false);
                closeBtn.setEnabled(true);
                saveBtn.setEnabled(false);
                stopBtn.setEnabled(false);
            } else if ("approved".equalsIgnoreCase(session.getWorkflowStatus()) || "rejected".equalsIgnoreCase(session.getWorkflowStatus())
                    || "cancelled".equalsIgnoreCase(session.getWorkflowStatus())) {
                submitBtn.setEnabled(false);
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                cancelBtn.setEnabled(false);
                withdrawBtn.setEnabled(false);
                closeBtn.setEnabled(true);
                saveBtn.setEnabled(false);
                if ("approved".equalsIgnoreCase(session.getWorkflowStatus())) {
                    stopBtn.setEnabled(true);
                }
            }
        } else {
            if ("Pending".equalsIgnoreCase(session.getWorkflowStatus())) {
                submitBtn.setEnabled(false);
                approveBtn.setEnabled(true);
                rejectBtn.setEnabled(true);
                cancelBtn.setEnabled(true);
                withdrawBtn.setEnabled(true);
                closeBtn.setEnabled(true);
                saveBtn.setEnabled(false);
                stopBtn.setEnabled(false);
            } else if ("open".equalsIgnoreCase(session.getWorkflowStatus())) {
                submitBtn.setEnabled(true);
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                cancelBtn.setEnabled(false);
                withdrawBtn.setEnabled(false);
                closeBtn.setEnabled(false);
                saveBtn.setEnabled(false);
                stopBtn.setEnabled(false);
            } else if ("withdrawn".equalsIgnoreCase(session.getWorkflowStatus()) || "rejected".equalsIgnoreCase(session.getWorkflowStatus())) {
                submitBtn.setEnabled(true);
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                cancelBtn.setEnabled(true);
                withdrawBtn.setEnabled(false);
                closeBtn.setEnabled(true);
                saveBtn.setEnabled(false);
                stopBtn.setEnabled(false);
            } else if ("approved".equalsIgnoreCase(session.getWorkflowStatus()) || "rejected".equalsIgnoreCase(session.getWorkflowStatus())
                    || "cancelled".equalsIgnoreCase(session.getWorkflowStatus())) {
                submitBtn.setEnabled(false);
                approveBtn.setEnabled(false);
                rejectBtn.setEnabled(false);
                cancelBtn.setEnabled(false);
                withdrawBtn.setEnabled(false);
                closeBtn.setEnabled(true);
                saveBtn.setEnabled(false);
                if ("approved".equalsIgnoreCase(session.getWorkflowStatus())) {
                    stopBtn.setEnabled(true);
                }
            }

        }

    }

    private void attachTabChangeListener() {
        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(final TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                try {
                    tabPosition = event.getTabSheet().getTabPosition(tab);
                    buttonEnableLogic(tabPosition, tabSheet.getComponentCount() - 1);

                    onTabChange(tabPosition);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        });
    }

    /**
     * Button Enable and Disable Logic
     *
     * @param tabPosition
     * @param i
     */
    protected void buttonEnableLogic(int tabPosition, int i) {
        LOGGER.info("tabPosition-------->" + tabPosition);
        if (tabPosition == 0) {
            previousBtn.setVisible(false);
        } else {
            previousBtn.setVisible(true);
        }
        if (tabPosition != 0) {
            approveBtn.setEnabled(true);
            rejectBtn.setEnabled(true);
        } else {
            approveBtn.setEnabled(true);
            rejectBtn.setEnabled(true);
        }
        if (tabPosition == i) {
            nextTabBtn.setVisible(false);
            previousBtn.setVisible(true);
            cancelBtn.setVisible(false);
        } else {
            nextTabBtn.setVisible(true);
            cancelBtn.setVisible(true);
        }
    }

    /**
     * On tab change.
     *
     * @param tabPosition the tab position
     */
    public void onTabChange(int tabPosition) {
        LOGGER.info("tabPosition-->" + tabPosition);
        if (tabPosition != 1) {
            detachListeners(tabSheet);
            tabLazyLoadMap.put(tabPosition, Boolean.TRUE);
            lazyLoadTab(tabPosition);
            attachTabChangeListener();
        }
        if (tabPosition == 1) {
            baseRateSummary.setSummaryValues(baseRateCalc);
        }
        if (tabPosition == 2) {
            baseRateHistory.setSummaryValues(baseRateCalc);
        }
    }

    /**
     * Lazy load tab.
     *
     * @param tabPosition the tab position
     */
    protected void lazyLoadTab(int tabPosition) {
        if (tabPosition == 0) {
            tabSheet.replaceComponent(baseRateCalc, baseRateCalc);

        } else if (tabPosition == 1) {
            tabSheet.replaceComponent(baseRateSummary, baseRateSummary);

        } else if (tabPosition == 2) {
            tabSheet.replaceComponent(baseRateHistory, baseRateHistory);

        } else if (tabPosition == 3 && !tabList.contains(3)) {
            tabSheet.replaceComponent(additionalInformation, additionalInformation.getContent());
            tabList.add(tabPosition);
        }
    }

    /**
     *
     * @return
     */
    
    /**
     * Configures the components.
     */
    protected void configureFields() {
        try {
            previousBtn.setVisible(false);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     *
     * @param event
     */
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        tabSheet.setSelectedTab(0);
    }

    public Component getContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(Clara.create(getClass().getResourceAsStream("/baseRateForm.xml"), this));
        configureFields();
        Panel panel = new Panel();
        panel.setContent(layout);
        return panel;
    }

    /**
     * Next Button Click Logic
     *
     */
    protected void btnNextLogic() {
        if (tabPosition == 0) {
        }
        tabSheet.setSelectedTab(tabPosition + 1);

    }

    /**
     * Next Button Click Logic
     *
     * @param event
     */
    @UiHandler("nextTabBtn")
    public void nextTab(Button.ClickEvent event) {
        LOGGER.info("Next tab button clicked---->");
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    btnNextLogic();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Update confirmation", "Base Rate values have changed. All other tabs will be updated \n and unsaved data will be lost. Continue?");
    }

    /**
     * Previous Button Click logic
     *
     * @param event
     */
    @UiHandler("previousBtn")
    public void previousTab(Button.ClickEvent event) {
        LOGGER.info("previousBtn clicked---->");
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    btnPreviousLogic();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage("Update confirmation", "Base Rate values have changed. All other tabs will be updated \n and unsaved data will be lost. Continue?");
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
        MessageBox.showPlain(Icon.QUESTION, "Workflow Saved Confirmation", "You are about to save workflow."
                + "  This workflow can be submitted at a later date. Please click OK if you would like to continue.", new MessageBoxListener() {

                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("OK")) {
                            final NotesPopup popup = new NotesPopup();
                            getUI().getCurrent().addWindow(popup);
                            popup.addCloseListener(new Window.CloseListener() {

                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    try {
                                            int accClosureId = wfId.getAacSid();
                                            baseRateCalc.saveLogic();
                                            baseRateCalc.saveAccruals();
                                            baseRateSummary.saveBRSummary();
                                            wfId.setNotes(popup.getNotes().getValue());
                                            wfId = baseRateCalc.setWorkflow("Open", true, wfId);
                                            logic.clearTemp();
                                            LOGGER.info("save logic, id: " + wfId.getAacSid());

                                            Notification notif = new Notification("Workflow" + wfId.getWorkflowId() + " has been saved successfully.",
                                                    Notification.Type.HUMANIZED_MESSAGE);
                                            notif.setPosition(Position.BOTTOM_CENTER);
                                            notif.setDelayMsec(3000);
                                            notif.show(Page.getCurrent());
                                            saveBtn.setEnabled(false);
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            });
                        }
                    }
                }, ButtonId.OK, ButtonId.CANCEL);
    }

    /**
     * Base Rate Approve Workflow Logic
     *
     * @param event
     */
    @UiHandler("approveBtn")
    public void approveWorkflowLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Approval Confirmation", " Base Accrual Rate workflow  is about to be approved.\n"
                + " Please click OK if you would like to continue.?", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("OK")) {
                            final NotesPopup popup = new NotesPopup();
                            getUI().getCurrent().addWindow(popup);
                            popup.addCloseListener(new Window.CloseListener() {

                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    try {
                                        brlogic.getWorkFlowDetails(session.getWorkflowId(), wfId);
                                        if (wfId.getCreatedBy() == userId) {
                                            AbstractNotificationUtils.getErrorNotification("Cannot Approved", "Same user cannot approve the workflow");

                                        } else {
                                            wfId.setWorkflowMasterSystemId(session.getWorkflowId());
                                            wfId.setWorkflowId(session.getWorkflow());
                                            wfId.setNotes(popup.getNotes().getValue());
                                            wfId = baseRateCalc.setWorkflow("Approved", false, wfId);
                                            if (session.getNoOfApproval() > session.getApprovalLevel()) {
                                                wfId.setNotes(StringUtils.EMPTY);
                                                wfId = baseRateCalc.setWorkflow("Pending", false, wfId);
                                            }
                                            Notification notif = new Notification("Workflow" + session.getWorkflow() + " has been approved successfully.",
                                                    Notification.Type.HUMANIZED_MESSAGE);
                                            notif.setPosition(Position.BOTTOM_CENTER);
                                            notif.setDelayMsec(3000);
                                            notif.show(Page.getCurrent());
                                        }
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            });
                        }
                    }
                }, ButtonId.OK, ButtonId.CANCEL);
    }

    /**
     * Base Rate Cancel Workflow Logic
     *
     * @param event
     */
    @UiHandler("cancelBtn")
    public void cancelWorkflowLogic(Button.ClickEvent event) {

        MessageBox.showPlain(Icon.QUESTION, "Cancel Confirmation", " You are about to cancel Base Accrual Rate workflow "
                + " \n Please click OK if you would like to continue.?", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("OK")) {
                            final NotesPopup popup = new NotesPopup();
                            getUI().getCurrent().addWindow(popup);
                            popup.addCloseListener(new Window.CloseListener() {

                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    try {

                                        if (session.getWorkflow() != null && !"null".equals(session.getWorkflow())) {
                                            wfId.setWorkflowMasterSystemId(session.getWorkflowId());
                                            wfId.setWorkflowId(session.getWorkflow());
                                            wfId.setNotes(popup.getNotes().getValue());
                                            wfId = baseRateCalc.setWorkflow("Cancelled", false, wfId);
                                            Notification notif = new Notification("Workflow" + session.getWorkflow() + " has been cancelled successfully.",
                                                    Notification.Type.HUMANIZED_MESSAGE);
                                            notif.setPosition(Position.BOTTOM_CENTER);
                                            notif.setDelayMsec(3000);
                                            notif.show(Page.getCurrent());
                                        } else {
                                            wfId = baseRateCalc.setWorkflow("Cancelled", false, wfId);
                                            Notification notif = new Notification("Workflow" + wfId.getWorkflowId() + " has been cancelled successfully.",
                                                    Notification.Type.HUMANIZED_MESSAGE);
                                            notif.setPosition(Position.BOTTOM_CENTER);
                                            notif.setDelayMsec(3000);
                                            notif.show(Page.getCurrent());
                                            cancelBtn.setEnabled(false);
                                            stopBtn.setEnabled(false);
                                            withdrawBtn.setEnabled(false);
                                        }
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            });
                        }
                    }
                }, ButtonId.OK, ButtonId.CANCEL);
    }

    /**
     * Base Rate Cancel Workflow Logic
     *
     * @param event
     */
    @UiHandler("withdrawBtn")
    public void withdrawWorkflowLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Withdraw Confirmation", " You are about to withdraw Base Accrual Rate workflow "
                + " \n Please click OK if you would like to continue.?", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("OK")) {
                            final NotesPopup popup = new NotesPopup();
                            getUI().getCurrent().addWindow(popup);
                            popup.addCloseListener(new Window.CloseListener() {

                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    try {
                                        if (!"Pending".equalsIgnoreCase(session.getWorkflowStatus())) {
                                            AbstractNotificationUtils.getErrorNotification("Cannot Withdraw", "The workflow selected for withdrawn should be in pending status");
                                        } else {
                                            wfId.setWorkflowMasterSystemId(session.getWorkflowId());
                                            wfId.setWorkflowId(session.getWorkflow());
                                            wfId.setProjectionId(session.getAcctCloserMasterId());
                                            wfId.setNotes(popup.getNotes().getValue());
                                            wfId = baseRateCalc.setWorkflow("Withdrawn", false, wfId);
                                            Notification notif = new Notification("Workflow" + session.getWorkflow() + " has been withdrawn successfully.",
                                                    Notification.Type.HUMANIZED_MESSAGE);
                                            notif.setPosition(Position.BOTTOM_CENTER);
                                            notif.setDelayMsec(3000);
                                            notif.show(Page.getCurrent());
                                            withdrawBtn.setEnabled(false);
                                        }
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            });
                        }
                    }
                }, ButtonId.OK, ButtonId.CANCEL
        );
    }

    /**
     * Base Rate Submit Workflow Logic
     *
     * @param event
     */
    @UiHandler("submitBtn")
    public void submitWorkflowLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Submit Workflow", " You are about to submit Base Accrual Rate workflow "
                + " \n Please click OK if you would like to continue.?", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("OK")) {
                            final NotesPopup popup = new NotesPopup();
                            getUI().getCurrent().addWindow(popup);
                            popup.addCloseListener(new Window.CloseListener() {

                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    try {
                                        if (session.getWorkflowId() != 0) {
                                            wfId.setWorkflowId(session.getWorkflow());
                                            wfId.setAacSid(session.getAcctCloserMasterId());
                                        }
                                        if (!utils.getNull(wfId.getWorkflowId())) {

                                            Notification notif = new Notification("Workflow" + wfId.getWorkflowId() + " has been submitted successfully.",
                                                    Notification.Type.HUMANIZED_MESSAGE);
                                            notif.setPosition(Position.BOTTOM_CENTER);
                                            notif.setDelayMsec(3000);
                                            notif.show(Page.getCurrent());
                                            submitBtn.setEnabled(false);
                                        } else {
                                            AbstractNotificationUtils.getErrorNotification("Not Saved", "Please save the workflow before Approving");
                                        }
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            });
                        }
                    }
                }, ButtonId.OK, ButtonId.CANCEL);
    }

    /**
     * Base Rate Workflow Reject Logic
     *
     * @param event
     */
    @UiHandler("rejectBtn")
    public void workflowRejectLogic(Button.ClickEvent event) {

        MessageBox.showPlain(Icon.QUESTION, "Rejection Confirmation", " Base Accrual Rate workflow  is about to be rejected.\n"
                + " Please click OK if you would like to continue.?", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            final NotesPopup popup = new NotesPopup();
                            getUI().getCurrent().addWindow(popup);
                            popup.addCloseListener(new Window.CloseListener() {

                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    try {
                                        wfId.setWorkflowMasterSystemId(session.getWorkflowId());
                                        wfId.setWorkflowId(session.getWorkflow());
                                        wfId.setNotes(popup.getNotes().getValue());
                                        wfId = baseRateCalc.setWorkflow("Rejected", false, wfId);
                                        Notification notif = new Notification("Workflow" + session.getWorkflow() + " has been rejected successfully.",
                                                Notification.Type.HUMANIZED_MESSAGE);
                                        notif.setPosition(Position.BOTTOM_CENTER);
                                        notif.setDelayMsec(3000);
                                        notif.show(Page.getCurrent());
                                        rejectBtn.setEnabled(false);
                                    } catch (Exception ex) {
                                        LOGGER.error(ex);
                                    }
                                }
                            });
                        }
                    }
                }, ButtonId.YES, ButtonId.NO
        );
    }

    /**
     * Base Rate Workflow Reject Logic
     *
     * @param event
     */
    @UiHandler("stopBtn")
    public void workflowStopLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Stop Confirmation", " Base Accrual Rate workflow  is about to be stopped.\n"
                + " Please click OK if you would like to continue.?", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            final NotesPopup popup = new NotesPopup();
                            getUI().getCurrent().addWindow(popup);
                            popup.addCloseListener(new Window.CloseListener() {

                                @Override
                                public void windowClose(Window.CloseEvent e) {
                                    try {
                                        if (brlogic.stopDate(session.getWorkflowId())) {
                                            wfId.setWorkflowMasterSystemId(session.getWorkflowId());
                                            wfId.setWorkflowId(session.getWorkflow());
                                            wfId.setNotes(popup.getNotes().getValue());
                                            wfId = baseRateCalc.setWorkflow("Rejected", false, wfId);
                                            Notification notif = new Notification("Workflow" + session.getWorkflow() + " has been stopped successfully.",
                                                    Notification.Type.HUMANIZED_MESSAGE);
                                            notif.setPosition(Position.BOTTOM_CENTER);
                                            notif.setDelayMsec(3000);
                                            notif.show(Page.getCurrent());
                                            stopBtn.setEnabled(false);
                                        } else {
                                            AbstractNotificationUtils.getErrorNotification("Cannot Stop", "The Base Rate Projection has already started and therefore cannot be stopped.");
                                        }
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
     * Close Button Logic
     *
     * @param event
     */
    @UiHandler("closeBtn")
    public void closeButtonLogic(Button.ClickEvent event) {
        MessageBox.showPlain(Icon.QUESTION, "Save?", " The workflow is about to be closed."
                + " If you would like to save the workflow, click OK. \n If you would like to close without saving, click CANCEL.", new MessageBoxListener() {
                    public void buttonClicked(ButtonId buttonId) {
                        if (buttonId.name().equals("YES")) {
                            try {
                                if (session.getWorkflowId() != 0) {
                                    Page.getCurrent().getJavaScript().execute("window.open('','_parent','');window.close(); ");
                                }
                            } catch (Exception ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                }, ButtonId.YES, ButtonId.NO);
    }

    public void detachListeners(TabSheet tabsheet) {
        List<TabSheet.SelectedTabChangeListener> listeners = new ArrayList<TabSheet.SelectedTabChangeListener>();

        listeners = (List<TabSheet.SelectedTabChangeListener>) tabsheet
                .getListeners(TabSheet.SelectedTabChangeEvent.class);

        for (TabSheet.SelectedTabChangeListener object : listeners) {
            tabsheet.removeSelectedTabChangeListener(object);

        }

    }

}
