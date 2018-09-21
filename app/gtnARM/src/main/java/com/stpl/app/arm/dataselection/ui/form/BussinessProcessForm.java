/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.ui.form;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.bpm.logic.DSCalculationLogic;
import com.stpl.app.arm.bpm.logic.VarianceCalculationLogic;
import com.stpl.app.arm.bpm.persistance.WorkflowPersistance;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.supercode.AbstractTransaction;
import com.stpl.app.arm.businessprocess.Transaction1;
import com.stpl.app.arm.businessprocess.Transaction2;
import com.stpl.app.arm.businessprocess.Transaction3;
import com.stpl.app.arm.businessprocess.Transaction4;
import com.stpl.app.arm.businessprocess.Transaction5;
import com.stpl.app.arm.businessprocess.Transaction6;
import com.stpl.app.arm.businessprocess.Transaction7;
import com.stpl.app.arm.businessprocess.Transaction8;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.stpl.app.arm.dataselection.view.DataSelectionView;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.arm.workflow.dto.WorkflowMasterDTO;
import com.stpl.app.arm.workflow.logic.WorkflowLogic;
import com.stpl.app.arm.workflow.lookup.WorkFlowNotesLookup;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.utils.ConstantsUtils;

import com.stpl.app.utils.VariableConstants;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.gtn.gtn2o.ws.constants.workflow.GtnWsBpmCommonConstants;
import com.stpl.gtn.gtn2o.ws.response.workflow.GtnWsCommonWorkflowResponse;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author
 */
public class BussinessProcessForm extends Window {

    /**
     * The Constant LOGGER.
     *
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(BussinessProcessForm.class);

    @UiField("winHeader")
    private VerticalLayout winHeader;

    @UiField("winHeaderLabel")
    private Label winHeaderLabel;

    @UiField("abstractTabSheet")
    private TabSheet tabSheet;

    @UiField("previousBtn")
    private Button previousBtn;

    private int tabPosition = 0;
    private AdjustmentReserveDTO binderDto = new AdjustmentReserveDTO();
    /**
     * binder used to bind the fields from the page
     */
    private final CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(binderDto));
    private String adjustmentType;
    private DataSelectionDTO dataselectionDTO;
    private SessionDTO sessionDTO;
    @UiField("submitBtn")
    private Button submitBtn;
    /**
     * The data selection.
     */
    private final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
    @UiField("btnApprove")
    private Button btnApprove;
    @UiField("btnCancel")
    private Button btnCancel;
    @UiField("btnReject")
    private Button btnReject;
    @UiField("btnWithdraw")
    private Button btnWithdraw;
    @UiField("nextBtn")
    private Button nextBtn;
    @UiField("closeButton")
    private Button closeButton;

    private AbstractTransaction transaction;
    public static final WorkflowLogic WORKFLOW_LOGIC = new WorkflowLogic();
    public static final String GTN_DETAIL = "GTN Detail";
    private DataSelectionLogic dataSelectionLogic = new DataSelectionLogic();

    public BussinessProcessForm(String adjustmentType, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) {
        setContent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/bottomButtonLayout.xml"), this));
        this.adjustmentType = adjustmentType;
        this.sessionDTO = sessionDTO;
        this.dataselectionDTO = dataselectionDTO;
        sessionDTO.setProjectionId(dataselectionDTO.getProjectionId());
        configureWindowHeader(sessionDTO);
        getBinder();
        nextBtn.focus();
        configureWindow();
        configureWorkflowButtons();
        initializeTabs();
        configurePermission();
        configureWorkflow();
        if (!sessionDTO.isWorkFlow()) {
            insertToReserveCCP();
            insertToSummaryCCP();
            callInsertCcpProcedure();
        }

    }

    private void initializeTabs() {
        try {
            tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
            tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
            if (ARMConstants.getPipelineAccrual().equals(adjustmentType)) {
                transaction = new Transaction1(tabSheet, binder, adjustmentType, dataselectionDTO, sessionDTO);
            } else if (ARMConstants.getDemandAccrual().equals(adjustmentType)) {
                transaction = new Transaction2(tabSheet, binder, adjustmentType, dataselectionDTO, sessionDTO);
            } else if (ARMConstants.getPipelineInventoryTrueUp().equals(adjustmentType)) {
                transaction = new Transaction3(tabSheet, binder, adjustmentType, dataselectionDTO, sessionDTO);
            } else if (ARMConstants.getDemandPaymentsRecon().equals(adjustmentType)) {
                transaction = new Transaction4(tabSheet, binder, adjustmentType, dataselectionDTO, sessionDTO);
            } else if (ARMConstants.getDemandReforecastTrueUp().equals(adjustmentType)) {
                transaction = new Transaction5(tabSheet, binder, adjustmentType, dataselectionDTO, sessionDTO);
            } else if (ARMConstants.getTransaction6().equals(adjustmentType)) {
                transaction = new Transaction6(tabSheet, binder, adjustmentType, dataselectionDTO, sessionDTO);
            } else if (ARMConstants.getTransaction7().equals(adjustmentType)) {
                transaction = new Transaction7(tabSheet, binder, adjustmentType, dataselectionDTO, sessionDTO);
            } else if (ARMConstants.getTransaction8().equals(adjustmentType)) {
                transaction = new Transaction8(tabSheet, binder, adjustmentType, dataselectionDTO, sessionDTO);
            }
            transaction.initializeTabs();
            transaction.configurePermission();
            if (tabSheet.getTab(0).getCaption().equals(ARMConstants.getDataSelection())) {
                previousBtn.setVisible(false);
                if (ARMConstants.getAdjustmentSummary().equals(dataselectionDTO.getAdjustmentType())) {
                    submitBtn.setVisible(false);
                }
            }
            tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
                @Override
                public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                    final TabSheet.Tab tab = event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                    tabPosition = event.getTabSheet().getTabPosition(tab);
                    transaction.setTabPosition(tabPosition);
                    if (tabPosition == 0) {
                        previousBtn.setVisible(false);
                        if (ARMConstants.getAdjustmentSummary().equals(dataselectionDTO.getAdjustmentType())) {
                            submitBtn.setVisible(false);
                        }
                    } else if (WorkflowConstants.getApproverConstant().equals(sessionDTO.getWorkflowUserType())) {
                        previousBtn.setVisible(true);
                        submitBtn.setVisible(false);
                    } else if (WorkflowConstants.getCreatorConstant().equals(sessionDTO.getWorkflowUserType())) {
                        if (WorkflowConstants.getPendingStatus().equalsIgnoreCase(sessionDTO.getWorkflowStatus()) || WorkflowConstants.getApprovedStatus().equals(sessionDTO.getWorkflowStatus()) || WorkflowConstants.getCancelledStatus().equals(sessionDTO.getWorkflowStatus())) {
                            previousBtn.setVisible(true);
                            submitBtn.setVisible(false);
                        }

                    } else {
                        previousBtn.setVisible(true);
                        submitBtn.setVisible(true);
                    }

                    if ("Additional Information".equals(tab.getCaption())) {
                        nextBtn.setVisible(false);
                    } else {
                        nextBtn.setVisible(true);
                    }
                }
            });
        } catch (Exception ex) {
            LOGGER.error("Error in tabSheet addSelectedTabChangeListener", ex);
        }

    }

    private void configureWindow() {

        center();
        setWidth(NumericConstants.FLOAT_HUNDRED, Sizeable.Unit.PERCENTAGE);
        setHeight(NumericConstants.FLOAT_EIGHTY, Sizeable.Unit.PERCENTAGE);
        setPositionX(ARMUtils.ZERO);
        setPositionY(ARMUtils.ZERO);
        addStyleName(ARMUtils.BOOTSTRAP_UI);
        addStyleName(ARMUtils.BOOTSTRAP);
        addStyleName(ARMUtils.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        setResizable(false);
        setClosable(false);
        btnApprove.setVisible(false);
        btnCancel.setVisible(false);
        btnReject.setVisible(false);
        btnWithdraw.setVisible(false);
    }

    private CustomFieldGroup getBinder() {
        binder.bindMemberFields(this);
        binder.setItemDataSource(new BeanItem<>(binderDto));
        binder.setBuffered(true);
        return binder;
    }

    private final CustomNotificationBPForm notifier = new CustomNotificationBPForm();

    private void insertToSummaryCCP() {
        List input = new ArrayList();
        input.add(dataselectionDTO.getProjectionId());
        QueryUtils.itemUpdate(input, "armSummaryInsertCcp");
    }

    private void callInsertCcpProcedure() {
        Object[] orderedArgs = {dataselectionDTO.getProjectionId(), sessionDTO.getUserId(), sessionDTO.getSessionId()};
        QueryUtil.callProcedure("PRC_ARM_CURRENT_BALANCE", orderedArgs);
    }

    class CustomNotificationBPForm extends AbstractNotificationUtils {

        private String buttonName;
        private int tabNo;

        public CustomNotificationBPForm() {
            /*
        THE DEFAULT CONSTRUCTOR
             */
        }

        @Override
        public void noMethod() {
            LOGGER.debug("inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case "close":
                        CommonLogic.dropDynamicTables(String.valueOf(sessionDTO.getUserId()), String.valueOf(sessionDTO.getSessionId()));
                        close();
                        break;
                    case "submit":
                        break;
                    case "next":
                        tabSheet.setSelectedTab(tabNo + 1);
                        break;
                    default:
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

        public void setCurrentTabNo(int tabNo) {
            this.tabNo = tabNo;
        }

    }

    public void closeButton() {
        closeButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    if (sessionDTO.isWorkFlow()) {
                        Page.getCurrent().getJavaScript().execute("window.close()");
                    } else {
                        // Changed for GAL-8113
                        notifier.setButtonName("close");
                        notifier.getOkCancelMessage(ARMMessages.getCloseMessageName_001(), "Are you sure you want to close the Fixed Dollar Adjustment -'" + dataselectionDTO.getAdjustmentCaption() + "' ? Nothing will be submitted.");
                    }
                } catch (Exception e) {
                    LOGGER.error("Error in closeButton", e);
                }
            }
        });
    }

    public void submitBtn() {
        submitBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    if (submitButtonValidation()) {

                        //GAL-9159 alert changes
                        if (dataSelectionLogic.dateCheckforGLCompAndBu(dataselectionDTO, true)) {
                            new AbstractNotificationUtils() {
                                @Override
                                public void yesMethod() {
                                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                                    getUI().getCurrent().addWindow(popup);
                                    popup.addCloseListener(new Window.CloseListener() {
                                        @Override
                                        public void windowClose(Window.CloseEvent e) {
                                            try {
                                                if (WorkFlowNotesLookup.getSubmitFlag().equals(CommonConstant.SUCCESS)) {
                                                    CommonLogic.saveTempToMain(dataselectionDTO.getProjectionId(), sessionDTO.getCurrentTableNames(), adjustmentType);
                                                    transaction.saveAssets();
                                                    saveProjection();
                                                    submitProjection(popup.getNotes().getValue(), adjustmentType, popup.getUploadedData());
                                                    CommonLogic.dropDynamicTables(String.valueOf(sessionDTO.getUserId()), String.valueOf(sessionDTO.getSessionId()));
                                                    close();
                                                    WorkFlowNotesLookup.setWorkFlowLookupFlag(CommonConstant.FAILED);
                                                }

                                            } catch (Exception ex) {
                                                LOGGER.error(VariableConstants.ERROR_IN_SUBMIT_BTNADD_CLICK_LISTENER, ex);
                                            }
                                        }

                                    });
                                }

                                @Override
                                public void noMethod() {
                                    LOGGER.debug("inside submitBtn NO Method");
                                }
                                //Changed for GAL-8113
                            }.getConfirmationMessage("Submit Confirmation", "Are you sure you want to submit this Fixed Dollar Adjustment - " + dataselectionDTO.getAdjustmentCaption() + " for approval? ");
                        } else {
                            //GAL-9159 alert changes
                            AbstractNotificationUtils.getErrorNotification("ERROR", dataselectionDTO.getProjectionDescription() + " can not be submitted because the GL Date is associated with a closed financial period.");
                        }
                    } else {
                        AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateMessageName_001(), transaction.submitErrorMessage());
                    }
                } catch (Exception ex) {

                    LOGGER.error(VariableConstants.ERROR_IN_SUBMIT_BTNADD_CLICK_LISTENER, ex);
                }
            }
        });
    }

    /**
     * Submits the projection. Saves and calls the workflow
     */
    private void submitProjection(final String notesVal, final String screenName, final List<NotesDTO> getUploadedData) {

        Map<String, Object> params = new HashMap<>();
        try {
            params.put(ARMUtils.PROJECTION_ID, dataselectionDTO.getProjectionId());
            String workflowStatus = getWorkflowStatus(dataselectionDTO.getProjectionId());
            if (!workflowStatus.equals("R") && !workflowStatus.equals("W")) {
                GtnWsCommonWorkflowResponse response = DSCalculationLogic.startWorkflow(sessionDTO, userId);
                List<String> roleList = new ArrayList<>();
                Long processInstanceId = Long.parseLong(String.valueOf(response.getProcessInstanceId()));
                sessionDTO.setProcessId(processInstanceId);

                if (response.isHasPermission()) {
                    DSCalculationLogic.startAndCompleteTask(sessionDTO, userId);
                    submitProjToWorkflow(params, notesVal, screenName, getUploadedData);

                } else {
                    StringBuilder notiMsg = new StringBuilder("You dont have permission to submit a projection.");
                    if (!roleList.isEmpty()) {
                        notiMsg.append("\n Only ").append(roleList).append(" can submit a projection.");
                    }
                    AbstractNotificationUtils.getWarningNotification("Permission Denied", notiMsg.toString());

                }
            } else {
                submitProjToWorkflow(params, notesVal, screenName, getUploadedData);
            }
        } catch (NumberFormatException e) {
            LOGGER.error("Error in submitProjection", e);
        }
    }

    private void submitProjToWorkflow(Map<String, Object> params, final String notes, final String screenName, final List<NotesDTO> getUploadedData) {

        try {

            Long processId = 0L;
            List processList = WorkflowPersistance.selectWFInstanceInfo(dataselectionDTO.getProjectionId());
            if (processList != null && !(processList.isEmpty())) {
                processId = Long.valueOf(processList.get(0).toString());
            }
            VarianceCalculationLogic.submitWorkflow(processId, sessionDTO, GtnWsBpmCommonConstants.ARM);
            String noOfUsers = DSCalculationLogic.getProcessVariableLog(processId, "NoOfUsers");
            LOGGER.debug("no of users : {}", noOfUsers);
            if (!noOfUsers.isEmpty()) {
                LOGGER.debug("no of users : {}", noOfUsers);
                String workflowId = submitToWorkflow(notes, Integer.parseInt(noOfUsers), screenName, getUploadedData);
                String approvedFlag;
                approvedFlag = ARMUtils.SUBMITTED;
                if (workflowId != null && !workflowId.trim().equals(ARMUtils.WORKFLOW_NOT_SAVED)) {
                    callWorkflowInboxRefresh();
                    MessageBox.showPlain(Icon.INFO, approvedFlag + " Successfully ", " Workflow Id: " + workflowId + "  ", new MessageBoxListener() {
                        /**
                         * The method is triggered when a button of the message
                         * box is pressed .
                         *
                         * @param buttonId The buttonId of the pressed button.
                         */
                        @SuppressWarnings("PMD")
                        @Override
                        public void buttonClicked(final ButtonId buttonId) {
                            if (sessionDTO.getWorkflowId() != 0) {
                                submitBtn.setEnabled(false);
                            } else {
                                UI.getCurrent().getNavigator().navigateTo(DataSelectionView.NAME);
                            }
                        }
                    }, ButtonId.OK);

                } else {
                    AbstractNotificationUtils.getErrorNotification("Error", "The Data not saved properly");
                }

            }
        } catch (NumberFormatException ex) {
            LOGGER.error("Error in submitProjToWorkflow", ex);
        }
    }

    /**
     * Called on submitting. Starts the workflow
     */
    private String submitToWorkflow(String notes, int noOfApprovals, String screenName, List<NotesDTO> getUploadedData) {
        return submitProjectionLogic(dataselectionDTO.getProjectionId(), userId, notes, noOfApprovals, screenName, getUploadedData, dataselectionDTO.getProjectionDescription());
    }

    public String getWorkflowStatus(int projectionId) {
        String workflowStatus = StringUtils.EMPTY;
        try {
            String query = SQlUtil.getQuery("checkWorkFlowStatus");
            query = query.replace("@PROJECTION_MASTER_SID", String.valueOf(projectionId));
            List<Object> result = HelperTableLocalServiceUtil.executeSelectQuery(query);
            workflowStatus = result != null && !ARMUtils.NULL.equals(String.valueOf(result.get(0))) ? String.valueOf(result.get(0)) : StringUtils.EMPTY;
        } catch (Exception ex) {
            LOGGER.error("Error in getWorkflowStatus", ex);
        }
        return workflowStatus;
    }

    private void callWorkflowInboxRefresh() {
        JavaScript.getCurrent().execute("localStorage.setItem('" + "ARM" + "', 'true');");
    }

    /**
     * Submit the projection.
     *
     * @param projectionId the projection id
     * @param userId
     * @param notes
     * @param noOfApprovals
     * @param screenName
     * @param getUploadedData
     * @param description
     * @return
     */
    public String submitProjectionLogic(int projectionId, String userId, String notes, int noOfApprovals, String screenName, List<NotesDTO> getUploadedData, String description) {
        LOGGER.debug("Entering submitProjection method");
        String workflowStatus = StringUtils.EMPTY;
        try {
            ProjectionMaster projMaster = ProjectionMasterLocalServiceUtil.getProjectionMaster(projectionId);
            if (projMaster != null) {
                if (projMaster.getIsApproved() != null && (projMaster.getIsApproved().equals(ARMUtils.R) || projMaster.getIsApproved().equals(ARMUtils.INDICATOR_LOGIC_CUSTOMER_HIERARCHY) || projMaster.getIsApproved().equals("W"))) {
                    workflowStatus = WORKFLOW_LOGIC.updateWorkflowFromForecast(projectionId, notes, userId);
                } else {
                    workflowStatus = WORKFLOW_LOGIC.saveWorkflow(projectionId, userId, notes, noOfApprovals, screenName, getUploadedData, description);
                }
                QueryUtils.itemUpdate(Arrays.asList(new String[]{userId, String.valueOf(projectionId)}), QueryUtils.QueryName.UPDATE_PROJECTION_MASTER_IN_SUBMIT);
            }

        } catch (Exception e) {
            LOGGER.error("Error in submitProjectionLogic", e);
            return "Not Saved";
        }
        LOGGER.debug("Ending submitProjection ");
        return workflowStatus;
    }

    public void nextBtn() {
        nextBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                int position = tabSheet.getTabPosition(tabSheet.getTab(tabSheet.getSelectedTab()));
                tabChangeLogic(position, position + 1);
            }
        });
    }

    private void tabChangeLogic(int oldPosition, int newPosition) {
        transaction.setTabPosition(oldPosition);
        boolean leave = transaction.checkLeave();
        if (leave) {
            tabSheet.setSelectedTab(newPosition);
        } else if (transaction.isRestrict()) {
            AbstractNotificationUtils.getWarningNotification(transaction.leaveRestrictionHeader(), transaction.leaveRestrictionMessage());
            tabSheet.setSelectedTab(oldPosition);
        } else if (oldPosition < newPosition) {
            notifier.getOkCancelMessage(transaction.leaveConfirmationHeader(), transaction.leaveConfirmationMessage());
            notifier.setCurrentTabNo(oldPosition);
            notifier.setButtonName("next");
        } else {
            tabSheet.setSelectedTab(newPosition);
        }
    }

    public void previousBtn() {
        previousBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                int position = tabSheet.getTabPosition(tabSheet.getTab(tabSheet.getSelectedTab()));
                tabChangeLogic(position, position - 1);
            }
        });
    }

    public void saveProjection() {
        transaction.getNotes().saveNotesInformation(dataselectionDTO.getProjectionId(), dataselectionDTO.getForecastingType());
    }

    private void configureWorkflowButtons() {
        if (sessionDTO.isWorkFlow()) {
            btnApprove.setVisible(true);
            btnCancel.setVisible(true);
            btnReject.setVisible(true);
            btnWithdraw.setVisible(true);
        }
        if ((ARMUtils.EDIT_SMALL.equalsIgnoreCase(sessionDTO.getAction()) || ARMUtils.VIEW_SMALL.equalsIgnoreCase(sessionDTO.getAction())) && sessionDTO.getWorkflowId() != 0) {
            if (WorkflowConstants.getApproverConstant().equals(sessionDTO.getWorkflowUserType())) {

                btnWithdraw.setVisible(false);
                submitBtn.setVisible(false);

                boolean buttonVisiblity = WorkflowConstants.getPendingStatus().equalsIgnoreCase(sessionDTO.getWorkflowStatus());
                btnApprove.setVisible(buttonVisiblity);
                btnReject.setVisible(buttonVisiblity);
                btnCancel.setVisible(buttonVisiblity);

            } else if (WorkflowConstants.getCreatorConstant().equals(sessionDTO.getWorkflowUserType())) {

                btnApprove.setVisible(false);
                btnReject.setVisible(false);
                btnCancel.setVisible(false);

                if (WorkflowConstants.getPendingStatus().equalsIgnoreCase(sessionDTO.getWorkflowStatus())) {
                    btnWithdraw.setVisible(true);
                    submitBtn.setVisible(false);
                    submitBtn.setVisible(false);
                    btnCancel.setVisible(true);
                } else if (WorkflowConstants.getWithdrawnStatus().equalsIgnoreCase(sessionDTO.getWorkflowStatus()) || WorkflowConstants.getRejectedStatus().equalsIgnoreCase(sessionDTO.getWorkflowStatus())) {
                    btnWithdraw.setVisible(false);
                    submitBtn.setVisible(true);
                    btnCancel.setVisible(false);
                } else if (WorkflowConstants.getApprovedStatus().equals(sessionDTO.getWorkflowStatus()) || WorkflowConstants.getCancelledStatus().equals(sessionDTO.getWorkflowStatus())) {
                    btnWithdraw.setVisible(false);
                    submitBtn.setVisible(false);
                }
            }
        } else {
            btnApprove.setVisible(false);
            btnReject.setVisible(false);
            btnWithdraw.setVisible(false);
            btnCancel.setVisible(false);
        }
    }

    private void configureWorkflow() {

        btnApprove.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                btnApproveLogic();
            }

        });
        btnReject.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                btnRejectLogic();
            }
        });
        btnWithdraw.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                btnWithdrawLogic();
            }
        });
        btnCancel.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                btnCancelLogic();
            }
        });

    }

    protected void btnApproveLogic() {
        if (dataSelectionLogic.dateCheckforGLCompAndBu(dataselectionDTO, true)) {
            MessageBox.showPlain(Icon.QUESTION, "Confirm Approve", "Are you sure you want to approve the projection " + " ?", new MessageBoxListener() {
                @Override
                public void buttonClicked(ButtonId approveButtonId) {
                    if (approveButtonId.name().equals(ARMUtils.YES)) {

                        final WorkFlowNotesLookup approvePopup = new WorkFlowNotesLookup();
                        getUI().getCurrent().addWindow(approvePopup);
                        approvePopup.addCloseListener(new Window.CloseListener() {

                            @Override
                            public void windowClose(Window.CloseEvent approveEvent) {
                                try {
                                    if (WorkFlowNotesLookup.getSubmitFlag().equals(CommonConstant.SUCCESS)) {
                                        int projectionId = sessionDTO.getProjectionId();
                                        String userIds = String.valueOf(sessionDTO.getUserId());
                                        int userIdInt = Integer.parseInt(userIds);
                                        int workflowId = sessionDTO.getWorkflowId();
                                        WorkflowLogic wfLogic = new WorkflowLogic();
                                        String workflowIdUpdate;
                                        WorkflowMasterDTO wfMasterDto;
                                        wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getApprovedStatus(), approvePopup.getNotes().getValue(), sessionDTO.getApprovalLevel());
                                        workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                        if (sessionDTO.getNoOfApproval() > sessionDTO.getApprovalLevel()) {
                                            wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getPendingStatus(), StringUtils.EMPTY, sessionDTO.getApprovalLevel() + 1);
                                            workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                        } else {
                                            List input = new ArrayList();
                                            input.add(sessionDTO.getProjectionId());
                                            if (GTN_DETAIL.equals(sessionDTO.getConfigType())) {
                                                input.add(transaction.ratesInput());
                                            }
                                            if (!GTN_DETAIL.equals(sessionDTO.getConfigType())) {
                                                input.add(transaction.getTableName());
                                            }
                                            input.add(transaction.getTableName());
                                            input.add(dataselectionDTO.getAdjustmentId());
                                            if (!GTN_DETAIL.equals(sessionDTO.getConfigType())) {
                                                input.add(dataselectionDTO.getAdjustmentId());
                                            }

                                            QueryUtils.itemUpdate(input,
                                                    GTN_DETAIL.equals(sessionDTO.getConfigType()) ? transaction.getGtnQuery() : transaction.getReserveQuery());
                                        }
                                        if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(ARMUtils.WORKFLOW_NOT_SAVED)) {

                                            VarianceCalculationLogic.submitWorkflow(sessionDTO.getProcessId(), sessionDTO, GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
                                            callWorkflowInboxRefresh();
                                            AbstractNotificationUtils.getInfoNotification("Approved Information", CommonConstant.WORKFLOW_ID + workflowIdUpdate + " approved successfully");
                                            // For Mail
                                            boolean approveFlag = getApproveFlag();
                                            btnApprove.setEnabled(approveFlag);
                                            btnWithdraw.setEnabled(approveFlag);
                                            btnCancel.setEnabled(approveFlag);
                                            btnReject.setEnabled(approveFlag);
                                        } else {
                                            CommonUIUtils.getMessageNotification("The projection not approved properly");
                                        }
                                        String approveMessage = getApproveMessage();
                                        WorkFlowNotesLookup.setWorkFlowLookupFlag(approveMessage);
                                    }
                                } catch (Exception ex) {
                                    LOGGER.error("Error in btnApprove Logic", ex);
                                }
                            }

                            private boolean getApproveFlag() {
                                return false;
                            }

                            private String getApproveMessage() {
                                return CommonConstant.FAILED;
                            }
                        });

                    }
                }
            }, ButtonId.YES, ButtonId.NO);
        } else {
            AbstractNotificationUtils.getErrorNotification("ERROR", getWorkflowId(sessionDTO) + " - " + dataselectionDTO.getProjectionDescription() + " can not be approved because the GL Date is associated with a closed financial period.");
        }
    }

    protected void btnRejectLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Reject", "Are you sure you want to reject the projection " + " ?", new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId rejectButtonId) {
                if (rejectButtonId.name().equals(ARMUtils.YES)) {
                    final WorkFlowNotesLookup rejectPopup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(rejectPopup);
                    rejectPopup.addCloseListener(new Window.CloseListener() {

                        @Override
                        public void windowClose(Window.CloseEvent rejectEvent) {
                            try {
                                if (WorkFlowNotesLookup.getSubmitFlag().equals(CommonConstant.SUCCESS)) {
                                    int projectionId = sessionDTO.getProjectionId();
                                    String sessionUserId = String.valueOf(sessionDTO.getUserId());
                                    int userIdInt = Integer.parseInt(sessionUserId);
                                    int workflowId = sessionDTO.getWorkflowId();
                                    WorkflowLogic wfLogic = new WorkflowLogic();
                                    WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getRejectedStatus(), rejectPopup.getNotes().getValue(), sessionDTO.getApprovalLevel());
                                    String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                    if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(ARMUtils.WORKFLOW_NOT_SAVED)) {

                                        Map<String, Object> rejectParams = new HashMap<>();
                                        rejectParams.put(CommonConstant.APPROVE_FLAG, "reject-RWC");
                                        VarianceCalculationLogic.submitWorkflow(sessionDTO.getProcessId(), sessionDTO, GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
                                        // For Mail
                                        callWorkflowInboxRefresh();
                                        AbstractNotificationUtils.getInfoNotification("Rejected Information ", CommonConstant.WORKFLOW_ID + workflowIdUpdate + " rejected successfully");
                                        boolean rejectFlag = getRejectFlag();
                                        btnApprove.setEnabled(rejectFlag);
                                        btnWithdraw.setEnabled(rejectFlag);
                                        btnCancel.setEnabled(rejectFlag);
                                        btnReject.setEnabled(rejectFlag);
                                    } else {
                                        CommonUIUtils.getMessageNotification("The projection not rejected properly");
                                    }
                                    WorkFlowNotesLookup.setWorkFlowLookupFlag(CommonConstant.FAILED);
                                }
                            } catch (Exception ex) {
                                LOGGER.error("Error in btnRejectLogic", ex);
                            }
                        }

                        private boolean getRejectFlag() {
                            return false;
                        }
                    });
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    protected void btnWithdrawLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Withdraw", "Are you sure you want to withdraw the projection " + " ?", new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId withdrawButtonId) {
                if (withdrawButtonId.name().equals(ARMUtils.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {

                        @Override
                        public void windowClose(Window.CloseEvent withdrawEvent) {
                            try {
                                if (WorkFlowNotesLookup.getSubmitFlag().equals(CommonConstant.SUCCESS)) {
                                    int projectionId = sessionDTO.getProjectionId();
                                    String sessionDtoUserId = String.valueOf(sessionDTO.getUserId());
                                    int userIdInt = Integer.parseInt(sessionDtoUserId);
                                    int workflowId = sessionDTO.getWorkflowId();
                                    WorkflowLogic wfLogic = new WorkflowLogic();
                                    WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getWithdrawnStatus(), popup.getNotes().getValue(), sessionDTO.getApprovalLevel());
                                    String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                    if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(ARMUtils.WORKFLOW_NOT_SAVED)) {

                                        Map<String, Object> withdrawParams = new HashMap<>();
                                        withdrawParams.put(CommonConstant.APPROVE_FLAG, "withdraw-RWC");
                                        VarianceCalculationLogic.submitWorkflow(sessionDTO.getProcessId(), sessionDTO, GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
                                        callWorkflowInboxRefresh();
                                        AbstractNotificationUtils.getInfoNotification("Workflow withdrawn ", CommonConstant.WORKFLOW_ID + workflowIdUpdate + " withdrawn successfully");
                                        // For Mail
                                        boolean withdrawFlag = getWithdrawFlag();
                                        btnApprove.setEnabled(withdrawFlag);
                                        btnWithdraw.setEnabled(withdrawFlag);
                                        btnCancel.setEnabled(withdrawFlag);
                                        btnReject.setEnabled(withdrawFlag);
                                    } else {
                                        CommonUIUtils.getMessageNotification("The projection not withdrawn properly");
                                    }
                                    WorkFlowNotesLookup.setWorkFlowLookupFlag(CommonConstant.FAILED);
                                }
                            } catch (Exception ex) {
                                LOGGER.error("Error in btnWithdrawLogic", ex);
                            }
                        }

                        private boolean getWithdrawFlag() {
                            return false;
                        }
                    });
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    protected void btnCancelLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Cancel", "Are you sure you want to cancel the projection " + " ?", new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(ARMUtils.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {

                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                if (WorkFlowNotesLookup.getSubmitFlag().equals(CommonConstant.SUCCESS)) {
                                    int projectionId = sessionDTO.getProjectionId();
                                    String CancelUserId = String.valueOf(sessionDTO.getUserId());
                                    int userIdInt = Integer.parseInt(CancelUserId);
                                    int workflowId = sessionDTO.getWorkflowId();
                                    WorkflowLogic wfLogic = new WorkflowLogic();
                                    WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getCancelledStatus(), popup.getNotes().getValue(), sessionDTO.getApprovalLevel());
                                    String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                    if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(ARMUtils.WORKFLOW_NOT_SAVED)) {

                                        Map<String, Object> cancelParams = new HashMap<>();
                                        cancelParams.put(CommonConstant.APPROVE_FLAG, "cancel-RWC");

                                        VarianceCalculationLogic.submitWorkflow(sessionDTO.getProcessId(), sessionDTO, GtnWsBpmCommonConstants.FORECAST_COMMERCIAL);
                                        callWorkflowInboxRefresh();
                                        AbstractNotificationUtils.getInfoNotification("Cancel Information", CommonConstant.WORKFLOW_ID + workflowIdUpdate + " cancelled successfully");
                                        // For Mail
                                        StringBuilder sb = new StringBuilder("Hi,<br /><br />");
                                        sb.append("The workflow with workflow Id ").append(workflowIdUpdate).append(" is cancelled Succesfully.");
                                        sb.append("<br /><br />Thanks,<br />BPI Technical Team");
                                        btnApprove.setEnabled(false);
                                        btnWithdraw.setEnabled(false);
                                        btnCancel.setEnabled(false);
                                        btnReject.setEnabled(false);
                                    } else {
                                        CommonUIUtils.getMessageNotification("The projection not cancelled properly");
                                    }
                                    WorkFlowNotesLookup.setWorkFlowLookupFlag(CommonConstant.FAILED);
                                }
                            } catch (Exception ex) {
                                LOGGER.error("Error in btnCancelLogic", ex);
                            }
                        }
                    });
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    private boolean submitButtonValidation() {
        return transaction.isGenerated();
    }

    private void insertToReserveCCP() {
        List input = new ArrayList();
        input.add(dataselectionDTO.getProjectionId());
        QueryUtils.itemUpdate(input, "Insert To Reserve CCP");
    }

    private void configureWindowHeader(SessionDTO sessionDTO) {
        if (sessionDTO.isWorkFlow()) {
            String result = getWorkflowId(sessionDTO);
            winHeaderLabel.setCaption(dataselectionDTO.getSelectedAdjType() + ARMUtils.SPACE + ">" + ARMUtils.SPACE
                    + result + ARMUtils.SPACE + ">" + ARMUtils.SPACE
                    + dataselectionDTO.getProjectionDescription() + ARMUtils.SPACE + ">" + ARMUtils.SPACE
                    + sessionDTO.getWorkflowStatus());
        } else {
            winHeaderLabel.setCaption(dataselectionDTO.getAdjustmentCaption() + ARMUtils.SPACE + ">" + ARMUtils.SPACE
                    + dataselectionDTO.getProjectionDescription());
        }
    }

    public String getWorkflowId(SessionDTO sessionDTO) {
        List input = new ArrayList();
        input.add(sessionDTO.getWorkflowId());
        String query = QueryUtils.getQuery(input, "GetWorkflowId");
        List<Object> result = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return result == null ? StringUtils.EMPTY : result.get(0).toString();

    }

    public void configurePermission() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String PermissionUserId = String.valueOf(VaadinSession.getCurrent()
                .getAttribute(com.stpl.app.utils.ConstantsUtils.USER_ID));
        final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(PermissionUserId, ARMUtils.FIXED_DOLLAR_ADJUSTMENT + ConstantsUtils.COMMA + com.stpl.app.utils.ConstantsUtils.ADJUSTMENT_DETAILS);
        if (functionCfpHM.get("submitBtn") != null && (functionCfpHM.get("submitBtn")).isFunctionFlag()) {
            submitBtn();
        } else {
            submitBtn.setVisible(false);
        }
        if (functionCfpHM.get("nextBtn") != null && (functionCfpHM.get("nextBtn")).isFunctionFlag()) {
            nextBtn();
        } else {
            nextBtn.setVisible(false);
        }
        if (functionCfpHM.get("previousBtn") != null && (functionCfpHM.get("previousBtn")).isFunctionFlag()) {
            previousBtn();
        } else {
            previousBtn.setVisible(false);
        }
        if (functionCfpHM.get("closeButton") != null && (functionCfpHM.get("closeButton")).isFunctionFlag()) {
            closeButton();
        } else {
            closeButton.setVisible(false);
        }
    }

    @Override
    public boolean equals(Object bpObj) {
        return super.equals(bpObj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream bpOut) throws IOException {
        bpOut.defaultWriteObject();
    }

    private void readObject(ObjectInputStream bpIn) throws IOException, ClassNotFoundException {
        bpIn.defaultReadObject();
    }
}
