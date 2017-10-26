/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.sessionutils;

import com.stpl.app.accountclose.dto.GLReserveMappingDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.BalanceReportDTO;
import com.stpl.app.accountclose.gtnbalancereport.dto.DataSelectionDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class SessionDTO implements Serializable {

    private int projectionId;
    private int accClosureId;
    private int workflowId;
    private String sessionId = StringUtils.EMPTY;
    private String projectionName = StringUtils.EMPTY;
    private String action = StringUtils.EMPTY;
    private String sessionDate = StringUtils.EMPTY;
    private String vaadinSessionId = StringUtils.EMPTY;
    private Date currentDate;
    private String editOperation = StringUtils.EMPTY;
    private String userId = StringUtils.EMPTY;
    private String userName = StringUtils.EMPTY;
    private String reportName = StringUtils.EMPTY;
    private int acctCloserMasterId;
    private Date dsFromDate;
    private Date dsToDate;
    private DataSelectionDTO dataSelectionDTO;
    private String chosenPeriod = StringUtils.EMPTY;
    private List<String> selectedPeriods = new ArrayList<String>();
    private BalanceReportDTO balanceReportDTO = new BalanceReportDTO();
    private List<String> selectedVariables = new ArrayList<String>();
    private Map<String, List> FrequencyAndQuater = new HashMap<String, List>();
    private Map<String, Map<String, Integer>> historyEndDetails = new HashMap<String, Map<String, Integer>>();
    private boolean saveFlag;
    private GLReserveMappingDTO glReserveMapping;
    private List<GLReserveMappingDTO> selectedList=new ArrayList<GLReserveMappingDTO>();
    private Long processId;
    private String workflowStatus;
    private String workflowCanApprove;

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    private String workflow;
    private int noOfApproval;
    private int approvalLevel;
    private int systemId;
    private String mode;

    public GLReserveMappingDTO getGlReserveMapping() {
        return glReserveMapping;
    }

    public void setGlReserveMapping(GLReserveMappingDTO glReserveMapping) {
        this.glReserveMapping = glReserveMapping;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public int getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(int workflowId) {
        this.workflowId = workflowId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getProjectionName() {
        return projectionName;
    }

    public void setProjectionName(String projectionName) {
        this.projectionName = projectionName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getVaadinSessionId() {
        return vaadinSessionId;
    }

    public void setVaadinSessionId(String vaadinSessionId) {
        this.vaadinSessionId = vaadinSessionId;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getEditOperation() {
        return editOperation;
    }

    public void setEditOperation(String editOperation) {
        this.editOperation = editOperation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public int getAcctCloserMasterId() {
        return acctCloserMasterId;
    }

    public void setAcctCloserMasterId(int acctCloserMasterId) {
        this.acctCloserMasterId = acctCloserMasterId;
    }

    public Date getDsFromDate() {
        return dsFromDate;
    }

    public void setDsFromDate(Date dsFromDate) {
        this.dsFromDate = dsFromDate;
    }

    public Date getDsToDate() {
        return dsToDate;
    }

    public void setDsToDate(Date dsToDate) {
        this.dsToDate = dsToDate;
    }

    public List getFrequencyAndQuaterValue(String frequency) {
        return FrequencyAndQuater.get(frequency);
    }

    public Map<String, Integer> getHistoryEndDetails(String frequency) {
        return historyEndDetails.get(frequency);
    }

    public void addHistoryEndDetails(String frequency, Map<String, Integer> value) {
        this.historyEndDetails.put(frequency, value);
    }

    public void addFrequencyAndQuater(String frequency, List HistoryCount) {
        this.FrequencyAndQuater.put(frequency, HistoryCount);
    }

    public DataSelectionDTO getDataSelectionDTO() {
        return dataSelectionDTO;
    }

    public void setDataSelectionDTO(DataSelectionDTO dataSelectionDTO) {
        this.dataSelectionDTO = dataSelectionDTO;
    }

    public String getChosenPeriod() {
        return chosenPeriod;
    }

    public void setChosenPeriod(String chosenPeriod) {
        this.chosenPeriod = chosenPeriod;
    }

    public Map<String, List> getFrequencyAndQuater() {
        return FrequencyAndQuater;
    }

    public void setFrequencyAndQuater(Map<String, List> FrequencyAndQuater) {
        this.FrequencyAndQuater = FrequencyAndQuater;
    }

    public Map<String, Map<String, Integer>> getHistoryEndDetails() {
        return historyEndDetails;
    }

    public void setHistoryEndDetails(Map<String, Map<String, Integer>> historyEndDetails) {
        this.historyEndDetails = historyEndDetails;
    }

    public List<String> getSelectedPeriods() {
        return selectedPeriods;
    }

    public void setSelectedPeriods(List<String> selectedPeriods) {
        this.selectedPeriods = selectedPeriods;
    }

    public BalanceReportDTO getBalanceReportDTO() {
        return balanceReportDTO;
    }

    public void setBalanceReportDTO(BalanceReportDTO balanceReportDTO) {
        this.balanceReportDTO = balanceReportDTO;
    }

    public List<String> getSelectedVariables() {
        return selectedVariables;
    }

    public void setSelectedVariables(List<String> selectedVariables) {
        this.selectedVariables = selectedVariables;
    }

    public boolean isSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(boolean saveFlag) {
        this.saveFlag = saveFlag;
    }

    public List<GLReserveMappingDTO> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<GLReserveMappingDTO> selectedList) {
        this.selectedList = selectedList;
    }
     
    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getWorkflowCanApprove() {
        return workflowCanApprove;
    }

    public void setWorkflowCanApprove(String workflowCanApprove) {
        this.workflowCanApprove = workflowCanApprove;
    }

    public int getAccClosureId() {
        return accClosureId;
    }

    public void setAccClosureId(int accClosureId) {
        this.accClosureId = accClosureId;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public int getNoOfApproval() {
        return noOfApproval;
    }

    public void setNoOfApproval(int noOfApproval) {
        this.noOfApproval = noOfApproval;
    }

    public int getApprovalLevel() {
        return approvalLevel;
    }

    public void setApprovalLevel(int approvalLevel) {
        this.approvalLevel = approvalLevel;
    }
    
}
