/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.sessionutils;

import com.stpl.app.gcm.tp.dto.ContractResultDTO;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class SessionDTO implements Serializable, Cloneable {

    private Integer projectionId;
    private Integer workflowId;
    private String sessionId;
    private String userId;
    private String projectionName;
    private String action;
    private String moduleName;

    private String companyNo;
    private String companyName;
    private String companyType;
    private String companyCategory;
    private String tradeClass;
    private String companyMasterSid = StringUtils.EMPTY;
    private List<String> companyMasterSids = new ArrayList<>();
    private List<String> phCompanyMasterSids = new ArrayList<>();

    private Integer companyId;
    private String forecastingType = StringUtils.EMPTY;
    private String forecastTableName = StringUtils.EMPTY;
    private String companyFamilyPlanName = StringUtils.EMPTY;
    private String searchSessionId = StringUtils.EMPTY;
    private boolean checkValue;
    private transient List<ContractResultDTO> list;
    private int contMasteSid = 0;
    private String contractMasterSid = StringUtils.EMPTY;
    private String processName = StringUtils.EMPTY;
    private HelperDTO contractType = new HelperDTO(0, Constants.SELECT_ONE);
    private String newProjectionId = StringUtils.EMPTY;
    private int contractSystemId = 0;
    private boolean removeCheck;
    private int fromProjectionId = 0;
    private int toProjectionId = 0;

    public SessionDTO() {
    }

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyFamilyPlanName() {
        return companyFamilyPlanName;
    }

    public void setCompanyFamilyPlanName(String companyFamilyPlanName) {
        this.companyFamilyPlanName = companyFamilyPlanName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Integer getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(Integer projectionId) {
        this.projectionId = projectionId;
    }

    public String getForecastingType() {
        return forecastingType;
    }

    public boolean isRemoveCheck() {
        return removeCheck;
    }

    public void setRemoveCheck(boolean removeCheck) {
        this.removeCheck = removeCheck;
    }

    public void setForecastingType(String forecastingType) {
        this.forecastingType = forecastingType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getForecastTableName() {
        return forecastTableName;
    }

    public void setForecastTableName(String forecastTableName) {
        this.forecastTableName = forecastTableName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(String companyCategory) {
        this.companyCategory = companyCategory;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public List<String> getCompanyMasterSids() {
        return companyMasterSids == null ? companyMasterSids : Collections.unmodifiableList(companyMasterSids);
    }

    public void setCompanyMasterSids(List<String> companyMasterSids) {
        this.companyMasterSids = companyMasterSids == null ? companyMasterSids : Collections.unmodifiableList(companyMasterSids);
    }

    public String getSearchSessionId() {
        return searchSessionId;
    }

    public void setSearchSessionId(String searchSessionId) {
        this.searchSessionId = searchSessionId;
    }

    public String getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(String companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public boolean isCheckValue() {
        return checkValue;
    }

    public void setCheckValue(boolean checkValue) {
        this.checkValue = checkValue;
    }

    public List<ContractResultDTO> getList() {
        return list == null ? list : new ArrayList<>(list);
    }

    public void setList(List<ContractResultDTO> list) {
        this.list = list == null ? list : new ArrayList<>(list);
    }

    public List<String> getPhCompanyMasterSids() {
        return phCompanyMasterSids == null ? phCompanyMasterSids : Collections.unmodifiableList(phCompanyMasterSids);
    }

    public void setPhCompanyMasterSids(List<String> phCompanyMasterSids) {
        this.phCompanyMasterSids = phCompanyMasterSids == null ? phCompanyMasterSids : Collections.unmodifiableList(phCompanyMasterSids);
    }

    public int getContMasteSid() {
        return contMasteSid;
    }

    public void setContMasteSid(int contMasteSid) {
        this.contMasteSid = contMasteSid;
    }

    public String getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(String contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public String getNewProjectionId() {
        return newProjectionId;
    }

    public void setNewProjectionId(String newProjectionId) {
        this.newProjectionId = newProjectionId;
    }

    public HelperDTO getContractType() {
        return contractType;
    }

    public void setContractType(HelperDTO contractType) {
        this.contractType = contractType;
    }
    
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Override
    public SessionDTO clone() throws CloneNotSupportedException {
        SessionDTO selection = (SessionDTO) super.clone();
        selection.setWorkflowId(workflowId);
        selection.setAction(action);
        selection.setCheckValue(checkValue);
        selection.setCompanyCategory(companyCategory);
        selection.setCompanyFamilyPlanName(companyFamilyPlanName);
        selection.setCompanyMasterSid(companyMasterSid);
        selection.setCompanyId(companyId);
        selection.setCompanyMasterSids(companyMasterSids);
        selection.setCompanyName(companyName);
        selection.setCompanyNo(companyNo);
        selection.setCompanyType(companyType);
        selection.setContMasteSid(contMasteSid);
        selection.setForecastTableName(forecastTableName);
        selection.setForecastingType(forecastingType);
        selection.setList(list);
        selection.setModuleName(moduleName);
        selection.setPhCompanyMasterSids(phCompanyMasterSids);
        selection.setProjectionId(projectionId);
        selection.setProjectionName(projectionName);
        selection.setSearchSessionId(searchSessionId);
        selection.setSessionId(sessionId);
        selection.setTradeClass(tradeClass);
        selection.setUserId(userId);
        selection.setWorkflowId(workflowId);
        selection.setNewProjectionId(newProjectionId);
        selection.setContractSystemId(0);
        return selection;
    }

    public int getContractSystemId() {
        return contractSystemId;
    }

    public void setContractSystemId(int contractSystemId) {
        this.contractSystemId = contractSystemId;
    }

    public int getFromProjectionId() {
        return fromProjectionId;
    }

    public void setFromProjectionId(int fromProjectionId) {
        this.fromProjectionId = fromProjectionId;
    }

    public int getToProjectionId() {
        return toProjectionId;
    }

    public void setToProjectionId(int toProjectionId) {
        this.toProjectionId = toProjectionId;
    }

}
