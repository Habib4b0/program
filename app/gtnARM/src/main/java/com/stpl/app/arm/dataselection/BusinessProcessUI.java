/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.arm.bpm.persistance.WorkflowPersistance;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.SessionUtil;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.ui.form.BussinessProcessForm;
import com.stpl.app.arm.dataselection.view.ArmWorkflowView;
import com.stpl.app.arm.dataselection.view.DataSelectionView;
import com.stpl.app.arm.supercode.CommonUI;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.WorkflowConstants;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

/**
 *
 * @author
 */
@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet")
@Component(service = UI.class, property = {
    "com.liferay.portlet.display-category=ARM",
    "javax.portlet.name=Fixed Dollar Adjusmtment",
    "javax.portlet.display-name=Fixed Dollar Adjustment",
    "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class BusinessProcessUI extends CommonUI {

    private DataSelectionDTO dataSelectionDto = new DataSelectionDTO();
    public static final org.slf4j.Logger LOGGERVALUE = org.slf4j.LoggerFactory.getLogger(BusinessProcessUI.class);

    public BusinessProcessUI() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    protected void init(VaadinRequest request) {
        addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
        final String userId = request.getRemoteUser();
        if (userId == null) {
            return;
        }
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
        String sessionId = new SimpleDateFormat("hhmmssms").format(new Date());
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setUserId(Integer.valueOf(userId));
        sessionDTO.setSessionId(Integer.valueOf(sessionId));
        HelperListUtil.getInstance().loadValuesWithListName("DATA_SELECTION");
        CommonLogic.loadTransactionName();
        CommonUtils.beforeUnloadCloseUi(this);
        CommonUtils.getUserName();
        String pageParameters = Page.getCurrent().getLocation().getQuery();
        String projectionId = null;
        String workflowId = null;
        String workflowStatus = null;
        String userType = null;
        String noOfApprovals = null;
        String approvalLevels = null;
        String adjType = null;
        String selectedAdjType = null;
        String configType = null;
        BussinessProcessForm editWindow = null;
        LOGGERVALUE.info("Inside BusinessProcessUI :");
        LOGGERVALUE.info("USER_ID :{}", userId);
        LOGGERVALUE.info("SESSION_ID :{}", sessionId);
        if (pageParameters != null) {
            pageParameters = pageParameters.contains("///&") ? pageParameters.replace("///&", "///") : pageParameters;
            String[] parameters = pageParameters.split("&");
            String[] parametersFromProjection = null;
            String[] parametersFromWorkflow = null;
            String[] parametersWorkflowStatus = null;
            String[] parametersWorkflowApprove = null;
            String[] parametersCanApprove = null;
            String[] noOfApproval = null;
            String[] approvalLevel = null;
            String[] portletName = null;
            String[] adjustmentType = null;
            String[] selectedAdjustmentType = null;
            String[] configurationType = null;
            parametersFromProjection = parameters[0].split(ARMUtils.EQUAL);
            parametersFromWorkflow = parameters[1].split(ARMUtils.EQUAL);
            parametersWorkflowStatus = parameters[NumericConstants.TWO].split(ARMUtils.EQUAL);
            parametersWorkflowApprove = parameters[NumericConstants.THREE].split(ARMUtils.EQUAL);
            parametersCanApprove = parameters[NumericConstants.FOUR].split(ARMUtils.EQUAL);
            noOfApproval = parameters[NumericConstants.FIVE].split(ARMUtils.EQUAL);
            approvalLevel = parameters[NumericConstants.SIX].split(ARMUtils.EQUAL);
            portletName = parameters[NumericConstants.SEVEN].split(ARMUtils.EQUAL);
            adjustmentType = parameters[NumericConstants.EIGHT].split(ARMUtils.EQUAL);
            selectedAdjustmentType = parameters[NumericConstants.NINE].split(ARMUtils.EQUAL);
            configurationType = parameters[NumericConstants.TEN].split(ARMUtils.EQUAL);

            HashMap<String, String> hm = new HashMap<>();
            hm.put(parametersFromProjection[0], parametersFromProjection[1]);
            hm.put(parametersWorkflowStatus[0], parametersWorkflowStatus[1]);
            hm.put(parametersFromWorkflow[0], parametersFromWorkflow[1]);
            hm.put(parametersWorkflowApprove[0], parametersWorkflowApprove[1]);
            hm.put(parametersCanApprove[0], parametersCanApprove[1]);
            hm.put(noOfApproval[0], noOfApproval[1].contains("///") ? noOfApproval[1].replace("///", "&") : noOfApproval[1]);
            hm.put(approvalLevel[0], approvalLevel[1]);
            hm.put(portletName[0], portletName[1]);
            hm.put(adjustmentType[0], adjustmentType[1]);
            hm.put(selectedAdjustmentType[0], selectedAdjustmentType[1]);
            hm.put(configurationType[0], configurationType[1]);

            projectionId = hm.get("armMasterSid");
            workflowId = hm.get("workflowId");
            workflowStatus = hm.get("workflowStatus");
            userType = hm.get("userType");
            noOfApprovals = hm.get("noOfApprovals");
            approvalLevels = hm.get("approvalLevel");
            adjType = hm.get("adjustmentType");
            selectedAdjType = hm.get("selectedAdjustmentType");
            configType = hm.get("configurationType");
            try {
                sessionDTO = SessionUtil.createSession();
            } catch (ParseException ex) {
                LOGGER.error(ex.getMessage());
            }
            List list = WorkflowPersistance.selectWFInstanceInfo(Integer.parseInt(projectionId));
            Long processId = 0L;
            if (list != null && !list.isEmpty()) {
                processId = Long.valueOf(list.get(0).toString());
            }

            sessionDTO.setProcessId(processId);
            dataSelectionDto.setProjectionId(Integer.parseInt(projectionId));
            dataSelectionDto.setAdjustmentType(adjType);
            dataSelectionDto.setSelectedAdjType(selectedAdjType.replaceAll("~", ARMUtils.SPACE.toString()));
            sessionDTO.setProjectionId(Integer.parseInt(projectionId));
            try {
                CommonLogic.getDataSelectionForWorkFlow(dataSelectionDto);
            } catch (Exception ex) {
                LOGGERVALUE.error("Error in getDataSelectionForWorkFlow :", ex);
            }
            sessionDTO.setWorkflowId(Integer.parseInt(workflowId));
            sessionDTO.setWorkflowUserType(userType);
            sessionDTO.setUserId(Integer.valueOf(userId));
            sessionDTO.setNoOfApproval(Integer.parseInt(noOfApprovals));
            sessionDTO.setApprovalLevel(Integer.parseInt(approvalLevels));
            sessionDTO.setWorkflowStatus(workflowStatus);
            sessionDTO.setAdjustmentType(adjType);
            sessionDTO.setWorkFlow(true);
            sessionDTO.setConfigType(configType.replace("~", ARMUtils.SPACE.toString()));
            try {
                if (!WorkflowConstants.getRejectedStatus().equals(workflowStatus) && !WorkflowConstants.getWithdrawnStatus().equals(workflowStatus)) {
                    sessionDTO.setAction(ARMUtils.VIEW_SMALL);
                    Map<String, String> currentTempTables = QueryUtils.createTempTables("view_tables", sessionDTO.getProjectionId(), sessionDTO.getUserId().toString(), sessionDTO.getSessionId().toString());
                    sessionDTO.setCurrentTableNames(currentTempTables);
                } else {
                    sessionDTO.setAction(ARMUtils.EDIT_SMALL);
                    sessionDTO.setScreenName(getTransactionName(adjType));
                    Map<String, String> currentTempTables = QueryUtils.createTempTables(sessionDTO.getScreenName(), sessionDTO.getProjectionId(), sessionDTO.getUserId().toString(), sessionDTO.getSessionId().toString());
                    sessionDTO.addTransactionTables(getTransactionName(adjType), currentTempTables);
                    sessionDTO.setCurrentTableNames(currentTempTables);
                    CommonLogic.saveToTemp(sessionDTO, adjType);
                }
                String query = QueryUtils.getQuery(Arrays.asList(new String[]{String.valueOf(Integer.valueOf(projectionId))}), "getloadworkflowAdjustmentType");
                List id = HelperTableLocalServiceUtil.executeSelectQuery(query);

                dataSelectionDto.setAdjustmentId(Integer.parseInt(String.valueOf(id.get(0))));
                editWindow = new BussinessProcessForm(adjType.replace("~", ARMUtils.SPACE.toString()), dataSelectionDto, sessionDTO);
            } catch (Exception ex) {
                LOGGERVALUE.error("Error in editWindow :", ex);
            }
        }
        Navigator navigator = new Navigator(this, this);
        if (editWindow != null) {
            try {
                navigator.addView(ArmWorkflowView.NAME, new ArmWorkflowView(editWindow));
            } catch (Exception ex) {
                LOGGERVALUE.error("Error in navigator addview :", ex);
            }
        }
        HelperListUtil.getInstance().loadValuesWithListName("DATA_SELECTION");
        // This method is to load transaction name of adjustment config value in helper list map
        CommonLogic.loadTransactionName();
        if (projectionId != null) {
            getUI().getNavigator().navigateTo(ArmWorkflowView.NAME + "/" + pageParameters);
        } else {
            navigator.addView(DataSelectionView.NAME, new DataSelectionView(sessionDTO, StringUtils.EMPTY));
        }
        uiErrorHandler();
    }

    /**
     * Transaction names
     *
     * @param adjType
     * @return String
     */
    private String getTransactionName(String adjType) {
        String adjustType = adjType.replace("~", ARMUtils.SPACE.toString());
        if (adjustType.equals(ARMConstants.getPipelineAccrual())) {
            return "ARM_Txt_1";
        } else if (adjustType.equals(ARMConstants.getDemandAccrual())) {
            return "ARM_Txt_2";
        } else if (adjustType.equals(ARMConstants.getPipelineInventoryTrueUp())) {
            return "ARM_Txt_3";
        } else if (adjustType.equals(ARMConstants.getDemandPaymentsRecon())) {
            return "ARM_Txt_4";
        } else if (adjustType.equals(ARMConstants.getDemandReforecastTrueUp())) {
            return "ARM_Txt_5";
        } else if (adjustType.equals(ARMConstants.getTransaction6())) {
            return "ARM_Txt_6";
        } else if (adjustType.equals(ARMConstants.getTransaction7())) {
            return "ARM_Txt_7";
        }
        return StringUtils.EMPTY;
    }

    static class BPMJob implements Runnable {

        @Override
        public void run() {
            LOGGERVALUE.info("Intailizing BPM Engine:");
        }

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.dataSelectionDto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BusinessProcessUI other = (BusinessProcessUI) obj;
        return Objects.equals(this.dataSelectionDto, other.dataSelectionDto);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
