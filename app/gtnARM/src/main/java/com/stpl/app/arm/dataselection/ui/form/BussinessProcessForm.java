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
import com.stpl.app.arm.bpm.service.BPMProcessBean;
import com.stpl.app.arm.bpm.service.MailWorkItemHandler;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dao.DataSelectionDAO;
import com.stpl.app.arm.dao.impl.DataSelectionDAOImpl;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.view.DataSelectionView;
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
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.arm.workflow.dto.WorkflowMasterDTO;
import com.stpl.app.arm.workflow.logic.WorkflowLogic;
import com.stpl.app.arm.workflow.lookup.WorkFlowNotesLookup;
import com.stpl.app.bpm.dto.WorkflowRuleDTO;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import com.stpl.ifs.ui.NotesDTO;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.TaskSummary;

/**
 *
 * @author
 */
public class BussinessProcessForm extends Window {

    /**
     * The Constant LOGGER.
     *
     */
    public static final Logger LOGGER = Logger.getLogger(BussinessProcessForm.class);

    @UiField("winHeader")
    private VerticalLayout winHeader;

    @UiField("winHeaderLabel")
    private Label winHeaderLabel;

    @UiField("abstractTabSheet")
    private TabSheet tabSheet;

    @UiField("previousBtn")
    private Button previousBtn;

    int tabPosition = 0;
    AdjustmentReserveDTO binderDto = new AdjustmentReserveDTO();
    /**
     * binder used to bind the fields from the page
     */
    private final CustomFieldGroup binder = new CustomFieldGroup(new BeanItem<>(binderDto));
    String adjustmentType;
    DataSelectionDTO dataselectionDTO;
    private SessionDTO sessionDTO;
    @UiField("submitBtn")
    private Button submitBtn;
    /**
     * The data selection.
     */
    public static DataSelectionDAO dataSelection = new DataSelectionDAOImpl();
    final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
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

    AbstractTransaction transaction;
    public static final WorkflowLogic WORKFLOW_LOGIC = new WorkflowLogic();
    DataSelectionLogic dataSelectionLogic = new DataSelectionLogic();

    public BussinessProcessForm(String adjustmentType, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) {
        setContent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/bottomButtonLayout.xml"), this));
        this.adjustmentType = adjustmentType;
        this.sessionDTO = sessionDTO;
        this.dataselectionDTO = dataselectionDTO;
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
            tabSheet.setImmediate(true);
            try {
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
            } catch (Exception e) {
                LOGGER.error("Error in initializeTabs"+e);
            }
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
                        if (WorkflowConstants.getPendingStatus().equalsIgnoreCase(sessionDTO.getWorkflowStatus())) {
                            previousBtn.setVisible(true);
                            submitBtn.setVisible(false);
                        } else if (WorkflowConstants.getApprovedStatus().equals(sessionDTO.getWorkflowStatus()) || WorkflowConstants.getCancelledStatus().equals(sessionDTO.getWorkflowStatus())) {
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
            LOGGER.error("Error in tabSheet addSelectedTabChangeListener"+ex);
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

    private final CustomNotification notifier = new CustomNotification();

    private void insertToSummaryCCP() {
        List input = new ArrayList();
        input.add(dataselectionDTO.getProjectionId());
        QueryUtils.itemUpdate(input, "armSummaryInsertCcp");
    }

    private void callInsertCcpProcedure() {
        Object[] orderedArgs = {dataselectionDTO.getProjectionId(), sessionDTO.getUserId(), sessionDTO.getSessionId()};
        CommonUtils.callInsertProcedure("PRC_ARM_CURRENT_BALANCE", orderedArgs);
    }

    class CustomNotification extends AbstractNotificationUtils {

        String buttonName;
        int tabNo;

        @Override
        public void noMethod() {
            LOGGER.debug("inside CustomNotification NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :" + buttonName);
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
                    LOGGER.error("Error in closeButton"+e);
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
                                                if (WorkFlowNotesLookup.submitFlag.equals(CommonConstant.SUCCESS)) {
                                                    CommonLogic.saveTempToMain(dataselectionDTO.getProjectionId(), sessionDTO.getCurrentTableNames(), adjustmentType);
                                                    transaction.saveAssets();
                                                    saveProjection();
                                                    submitProjection(popup.getNotes().getValue(), adjustmentType, popup.getUploadedData());
                                                    CommonLogic.dropDynamicTables(String.valueOf(sessionDTO.getUserId()), String.valueOf(sessionDTO.getSessionId()));
                                                    close();
                                                    WorkFlowNotesLookup.submitFlag = CommonConstant.FAILED;
                                                }

                                            } catch (SystemException | PortalException ex) {
                                                LOGGER.error(VariableConstants.ERROR_IN_SUBMIT_BTNADD_CLICK_LISTENER+ex);
                                            } catch (Exception ex) {
                                                LOGGER.error(VariableConstants.ERROR_IN_SUBMIT_BTNADD_CLICK_LISTENER+ex);
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
                    
                    LOGGER.error(VariableConstants.ERROR_IN_SUBMIT_BTNADD_CLICK_LISTENER + ex);
                }
            }
        });
    }

    

    /**
     * Submits the projection. Saves and calls the workflow
     */
    private void submitProjection(final String notesVal, final String screenName, final List<NotesDTO> getUploadedData) throws SystemException, PortalException {

        Map<String, Object> params = new HashMap<>();
        try {
            params.put(ARMUtils.PROJECTION_ID, dataselectionDTO.getProjectionId());
            String workflowStatus = getWorkflowStatus(dataselectionDTO.getProjectionId());
            if (!workflowStatus.equals("R") && !workflowStatus.equals("W")) {
                ProcessInstance processInstance = DSCalculationLogic.startWorkflow();
                User userModel = UserLocalServiceUtil.getUser(Long.parseLong(userId));
                List<String> roleList = new ArrayList<>();
                boolean workflowFlag = DSCalculationLogic.isValidWorkflowUser(userModel, roleList, processInstance.getId());
                Long processInstanceId = processInstance.getId();

                TaskSummary taskSummary = DSCalculationLogic.startAndCompleteTask(userModel, dataselectionDTO.getProjectionId(), processInstanceId);
                processInstanceId = taskSummary.getProcessInstanceId();
                sessionDTO.setProcessId(processInstanceId);

                if (workflowFlag) {
                    submitProjToWorkflow(params, notesVal, screenName, getUploadedData);

                } else {
                    StringBuffer notiMsg = new StringBuffer("You dont have permission to submit a projection.");
                    if (roleList != null && !roleList.isEmpty()) {
                        notiMsg.append("\n Only " + roleList + " can submit a projection.");
                    }
                    AbstractNotificationUtils.getWarningNotification("Permission Denied", notiMsg.toString());

                }
            } else {
                submitProjToWorkflow(params, notesVal, screenName, getUploadedData);
            }
        } catch (Exception e) {
            LOGGER.error("Error in submitProjection"+e);
        }
    }

    private void submitProjToWorkflow(Map<String, Object> params, final String notes, final String screenName, final List<NotesDTO> getUploadedData) {

        try {

            WorkflowRuleDTO dto = new WorkflowRuleDTO();
            params.put("out_workflowDTO", dto);

            Long processId = 0L;
            List processList = WorkflowPersistance.selectWFInstanceInfo(dataselectionDTO.getProjectionId());
            if (processList != null && !(processList.isEmpty())) {
                processId = Long.valueOf(processList.get(0).toString());
            }
            VarianceCalculationLogic.submitWorkflow(userId, processId, params);
            String noOfUsers = BPMProcessBean.getProcessVariableLog(processId, "NoOfUsers");
            LOGGER.debug("no of users : " + noOfUsers);
            if (!noOfUsers.isEmpty()) {
                LOGGER.debug("no of users : " + noOfUsers);
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
        } catch (Exception ex) {
            LOGGER.error("Error in submitProjToWorkflow"+ex);
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
            DynamicQuery projectionDynamicQuery = DynamicQueryFactoryUtil
                    .forClass(ProjectionMaster.class);
            projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(
                    "forecastingType", VariableConstants.ARM));
            projectionDynamicQuery.add(RestrictionsFactoryUtil.eq(ARMUtils.PROJECTION_MASTER_SID,
                    projectionId));

            List<ProjectionMaster> resultList;

            resultList = dataSelection.getProjectionMaster(projectionDynamicQuery);

            for (ProjectionMaster pm : resultList) {
                workflowStatus = pm.getIsApproved();
            }
        } catch (PortalException | SystemException ex) {
            LOGGER.error("Error in getWorkflowStatus"+ex);
        }
        return workflowStatus;
    }

    public Object deleteTempBySession(final SessionDTO inputDto) throws SystemException {
        Map<String, Object> input = new HashMap<>();
        input.put("?UID", inputDto.getUserId());
        input.put("?SID", inputDto.getSessionId());
        return dataSelection.tempOperation(input, "bp.dleteTemp");
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
            LOGGER.error("Error in submitProjectionLogic"+e);
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

    public void saveProjection() throws SystemException {
        transaction.getNotes().saveNotesInformation(dataselectionDTO.getProjectionId(), dataselectionDTO.getForecastingType());
    }

    private void configureWorkflowButtons() {
        if (sessionDTO.isWorkFlow()) {
            btnApprove.setVisible(true);
            btnCancel.setVisible(true);
            btnReject.setVisible(true);
            btnWithdraw.setVisible(true);
        }
        if (sessionDTO != null && (ARMUtils.EDIT_SMALL.equalsIgnoreCase(sessionDTO.getAction()) || ARMUtils.VIEW_SMALL.equalsIgnoreCase(sessionDTO.getAction())) && sessionDTO.getWorkflowId() != 0) {
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
                public void buttonClicked(ButtonId buttonId) {
                    if (buttonId.name().equals(ARMUtils.YES)) {

                        final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                        getUI().getCurrent().addWindow(popup);
                        popup.addCloseListener(new Window.CloseListener() {

                            @Override
                            public void windowClose(Window.CloseEvent e) {
                                try {
                                    if (WorkFlowNotesLookup.submitFlag.equals(CommonConstant.SUCCESS)) {
                                        int projectionId = sessionDTO.getProjectionId();
                                        String userIds = String.valueOf(sessionDTO.getUserId());
                                        int userIdInt = Integer.parseInt(userIds);
                                        int workflowId = sessionDTO.getWorkflowId();
                                        WorkflowLogic wfLogic = new WorkflowLogic();
                                        String workflowIdUpdate;
                                        WorkflowMasterDTO wfMasterDto;
                                        wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getApprovedStatus(), popup.getNotes().getValue(), sessionDTO.getApprovalLevel());
                                        workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                        if (sessionDTO.getNoOfApproval() > sessionDTO.getApprovalLevel()) {
                                            wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getPendingStatus(), StringUtils.EMPTY, sessionDTO.getApprovalLevel() + 1);
                                            workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                        } else {
                                            List input = new ArrayList();
                                            input.add(sessionDTO.getProjectionId());
                                            if (!"GTN Detail".equals(sessionDTO.getConfigType())) {
                                                input.add(transaction.getTableName());
                                            }
                                            input.add(transaction.getTableName());
                                            input.add(dataselectionDTO.getAdjustmentId());
                                            QueryUtils.itemUpdate(input,
                                                    "GTN Detail".equals(sessionDTO.getConfigType()) ? transaction.getGtnQuery() : transaction.getReserveQuery());
                                        }
                                        if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(ARMUtils.WORKFLOW_NOT_SAVED)) {

                                            Map<String, Object> params = new HashMap<>();
                                            params.put(CommonConstant.APPROVE_FLAG, "approve");
                                            VarianceCalculationLogic.submitWorkflow(String.valueOf(sessionDTO.getUserId()), sessionDTO.getProcessId(), params);
                                            callWorkflowInboxRefresh();
                                            AbstractNotificationUtils.getInfoNotification("Approved Information", CommonConstant.WORKFLOW_ID + workflowIdUpdate + " approved successfully");
                                            // For Mail

                                            btnApprove.setEnabled(false);
                                            btnWithdraw.setEnabled(false);
                                            btnCancel.setEnabled(false);
                                            btnReject.setEnabled(false);
                                        } else {
                                            CommonUIUtils.getMessageNotification("The projection not approved properly");
                                        }
                                        WorkFlowNotesLookup.submitFlag = CommonConstant.FAILED;
                                    }
                                } catch (Exception ex) {
                                    LOGGER.error("Error in btnApproveLogic"+ex);
                                }
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
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(ARMUtils.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {

                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                if (WorkFlowNotesLookup.submitFlag.equals(CommonConstant.SUCCESS)) {
                                    int projectionId = sessionDTO.getProjectionId();
                                    String userId = String.valueOf(sessionDTO.getUserId());
                                    int userIdInt = Integer.parseInt(userId);
                                    int workflowId = sessionDTO.getWorkflowId();
                                    WorkflowLogic wfLogic = new WorkflowLogic();
                                    WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getRejectedStatus(), popup.getNotes().getValue(), sessionDTO.getApprovalLevel());
                                    String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                    if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(ARMUtils.WORKFLOW_NOT_SAVED)) {

                                        Map<String, Object> params = new HashMap<>();
                                        params.put(CommonConstant.APPROVE_FLAG, "reject-RWC");
                                        VarianceCalculationLogic.submitWorkflow(String.valueOf(sessionDTO.getUserId()), sessionDTO.getProcessId(), params);
                                        // For Mail
                                        callWorkflowInboxRefresh();
                                        AbstractNotificationUtils.getInfoNotification("Rejected Information ", CommonConstant.WORKFLOW_ID + workflowIdUpdate + " rejected successfully");

                                        btnApprove.setEnabled(false);
                                        btnWithdraw.setEnabled(false);
                                        btnCancel.setEnabled(false);
                                        btnReject.setEnabled(false);
                                    } else {
                                        CommonUIUtils.getMessageNotification("The projection not rejected properly");
                                    }
                                    WorkFlowNotesLookup.submitFlag = CommonConstant.FAILED;
                                }
                            } catch (Exception ex) {
                                LOGGER.error("Error in btnRejectLogic"+ex);
                            }
                        }
                    });
                }
            }
        }, ButtonId.YES, ButtonId.NO);
    }

    protected void btnWithdrawLogic() {
        MessageBox.showPlain(Icon.QUESTION, "Confirm Withdraw", "Are you sure you want to withdraw the projection " + " ?", new MessageBoxListener() {
            @Override
            public void buttonClicked(ButtonId buttonId) {
                if (buttonId.name().equals(ARMUtils.YES)) {
                    final WorkFlowNotesLookup popup = new WorkFlowNotesLookup();
                    getUI().getCurrent().addWindow(popup);
                    popup.addCloseListener(new Window.CloseListener() {

                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            try {
                                if (WorkFlowNotesLookup.submitFlag.equals(CommonConstant.SUCCESS)) {
                                    int projectionId = sessionDTO.getProjectionId();
                                    String userId = String.valueOf(sessionDTO.getUserId());
                                    int userIdInt = Integer.parseInt(userId);
                                    int workflowId = sessionDTO.getWorkflowId();
                                    WorkflowLogic wfLogic = new WorkflowLogic();
                                    WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getWithdrawnStatus(), popup.getNotes().getValue(), sessionDTO.getApprovalLevel());
                                    String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                    if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(ARMUtils.WORKFLOW_NOT_SAVED)) {

                                        Map<String, Object> params = new HashMap<>();
                                        params.put(CommonConstant.APPROVE_FLAG, "withdraw-RWC");
                                        VarianceCalculationLogic.submitWorkflow(String.valueOf(sessionDTO.getUserId()), sessionDTO.getProcessId(), params);
                                        callWorkflowInboxRefresh();
                                        AbstractNotificationUtils.getInfoNotification("Workflow withdrawn ", CommonConstant.WORKFLOW_ID + workflowIdUpdate + " withdrawn successfully");
                                        // For Mail

                                        btnApprove.setEnabled(false);
                                        btnWithdraw.setEnabled(false);
                                        btnCancel.setEnabled(false);
                                        btnReject.setEnabled(false);
                                    } else {
                                        CommonUIUtils.getMessageNotification("The projection not withdrawn properly");
                                    }
                                    WorkFlowNotesLookup.submitFlag = CommonConstant.FAILED;
                                }
                            } catch (Exception ex) {
                                LOGGER.error("Error in btnWithdrawLogic"+ex);
                            }
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
                                if (WorkFlowNotesLookup.submitFlag.equals(CommonConstant.SUCCESS)) {
                                    int projectionId = sessionDTO.getProjectionId();
                                    String userId = String.valueOf(sessionDTO.getUserId());
                                    int userIdInt = Integer.parseInt(userId);
                                    int workflowId = sessionDTO.getWorkflowId();
                                    WorkflowLogic wfLogic = new WorkflowLogic();
                                    WorkflowMasterDTO wfMasterDto = wfLogic.setWorkflowMasterDTO(projectionId, workflowId, userIdInt, WorkflowConstants.getCancelledStatus(), popup.getNotes().getValue(), sessionDTO.getApprovalLevel());
                                    String workflowIdUpdate = wfLogic.updateWorkflow(wfMasterDto);
                                    if (workflowIdUpdate != null && !workflowIdUpdate.trim().equals(ARMUtils.WORKFLOW_NOT_SAVED)) {

                                        Map<String, Object> params = new HashMap<>();
                                        params.put(CommonConstant.APPROVE_FLAG, "cancel-RWC");

                                        VarianceCalculationLogic.submitWorkflow(String.valueOf(sessionDTO.getUserId()), sessionDTO.getProcessId(), params);
                                        callWorkflowInboxRefresh();
                                        AbstractNotificationUtils.getInfoNotification("Cancel Information", CommonConstant.WORKFLOW_ID + workflowIdUpdate + " cancelled successfully");
                                        // For Mail
                                        StringBuilder sb = new StringBuilder("Hi,<br /><br />");
                                        sb.append("The workflow with workflow Id " + workflowIdUpdate + " is cancelled Succesfully.");
                                        sb.append("<br /><br />Thanks,<br />BPI Technical Team");
                                        MailWorkItemHandler.sendMail("support@bpitechnologies.com", "Workflow Cancelled Succesfully", sb);
                                        btnApprove.setEnabled(false);
                                        btnWithdraw.setEnabled(false);
                                        btnCancel.setEnabled(false);
                                        btnReject.setEnabled(false);
                                    } else {
                                        CommonUIUtils.getMessageNotification("The projection not cancelled properly");
                                    }
                                    WorkFlowNotesLookup.submitFlag = CommonConstant.FAILED;
                                }
                            } catch (Exception ex) {
                                LOGGER.error("Error in btnCancelLogic"+ex);
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

    public void configureWindowHeader(SessionDTO sessionDTO) {
        if (sessionDTO.isWorkFlow()) {
            String result = getWorkflowId(sessionDTO);
            winHeaderLabel.setCaption(dataselectionDTO.getSelectedAdjType() + " " + ">" + " "
                    + result + " " + ">" + " "
                    + dataselectionDTO.getProjectionDescription() + " " + ">" + " "
                    + sessionDTO.getWorkflowStatus());
        } else {
            winHeaderLabel.setCaption(dataselectionDTO.getAdjustmentCaption() + " " + ">" + " "
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
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent()
                    .getAttribute(com.stpl.app.utils.ConstantsUtils.USER_ID));

            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, ARMUtils.FIXED_DOLLAR_ADJUSTMENT + ConstantUtil.COMMA + com.stpl.app.utils.ConstantsUtils.ADJUSTMENT_DETAILS);
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

        } catch (PortalException | SystemException ex) {
            LOGGER.error("Error in configurePermission"+ex);
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
}
