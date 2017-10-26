package com.stpl.app.parttwo.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class AccClosureMasterSoap implements Serializable {
    private boolean _saveFlag;
    private String _accountNo;
    private Date _toDate;
    private int _itemMasterSid;
    private String _description;
    private String _reportName;
    private int _rsType;
    private String _productIdentifier;
    private Date _modifiedDate;
    private int _workflowStatus;
    private String _moduleType;
    private Date _fromDate;
    private int _contractType;
    private int _glCompanyMasterSid;
    private Date _createdDate;
    private int _createdBy;
    private int _contractMasterSid;
    private String _accrualPeriod;
    private String _companyGroupSid;
    private int _accClosureMasterSid;
    private int _rsCategory;
    private int _adjustmentType;
    private int _modifiedBy;
    private String _itemGroupSid;
    private int _rebateProgramType;

    public AccClosureMasterSoap() {
    }

    public static AccClosureMasterSoap toSoapModel(AccClosureMaster model) {
        AccClosureMasterSoap soapModel = new AccClosureMasterSoap();

        soapModel.setSaveFlag(model.getSaveFlag());
        soapModel.setAccountNo(model.getAccountNo());
        soapModel.setToDate(model.getToDate());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setDescription(model.getDescription());
        soapModel.setReportName(model.getReportName());
        soapModel.setRsType(model.getRsType());
        soapModel.setProductIdentifier(model.getProductIdentifier());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setWorkflowStatus(model.getWorkflowStatus());
        soapModel.setModuleType(model.getModuleType());
        soapModel.setFromDate(model.getFromDate());
        soapModel.setContractType(model.getContractType());
        soapModel.setGlCompanyMasterSid(model.getGlCompanyMasterSid());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setAccrualPeriod(model.getAccrualPeriod());
        soapModel.setCompanyGroupSid(model.getCompanyGroupSid());
        soapModel.setAccClosureMasterSid(model.getAccClosureMasterSid());
        soapModel.setRsCategory(model.getRsCategory());
        soapModel.setAdjustmentType(model.getAdjustmentType());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setItemGroupSid(model.getItemGroupSid());
        soapModel.setRebateProgramType(model.getRebateProgramType());

        return soapModel;
    }

    public static AccClosureMasterSoap[] toSoapModels(AccClosureMaster[] models) {
        AccClosureMasterSoap[] soapModels = new AccClosureMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static AccClosureMasterSoap[][] toSoapModels(
        AccClosureMaster[][] models) {
        AccClosureMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new AccClosureMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new AccClosureMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static AccClosureMasterSoap[] toSoapModels(
        List<AccClosureMaster> models) {
        List<AccClosureMasterSoap> soapModels = new ArrayList<AccClosureMasterSoap>(models.size());

        for (AccClosureMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new AccClosureMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _accClosureMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setAccClosureMasterSid(pk);
    }

    public boolean getSaveFlag() {
        return _saveFlag;
    }

    public boolean isSaveFlag() {
        return _saveFlag;
    }

    public void setSaveFlag(boolean saveFlag) {
        _saveFlag = saveFlag;
    }

    public String getAccountNo() {
        return _accountNo;
    }

    public void setAccountNo(String accountNo) {
        _accountNo = accountNo;
    }

    public Date getToDate() {
        return _toDate;
    }

    public void setToDate(Date toDate) {
        _toDate = toDate;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getReportName() {
        return _reportName;
    }

    public void setReportName(String reportName) {
        _reportName = reportName;
    }

    public int getRsType() {
        return _rsType;
    }

    public void setRsType(int rsType) {
        _rsType = rsType;
    }

    public String getProductIdentifier() {
        return _productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        _productIdentifier = productIdentifier;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public int getWorkflowStatus() {
        return _workflowStatus;
    }

    public void setWorkflowStatus(int workflowStatus) {
        _workflowStatus = workflowStatus;
    }

    public String getModuleType() {
        return _moduleType;
    }

    public void setModuleType(String moduleType) {
        _moduleType = moduleType;
    }

    public Date getFromDate() {
        return _fromDate;
    }

    public void setFromDate(Date fromDate) {
        _fromDate = fromDate;
    }

    public int getContractType() {
        return _contractType;
    }

    public void setContractType(int contractType) {
        _contractType = contractType;
    }

    public int getGlCompanyMasterSid() {
        return _glCompanyMasterSid;
    }

    public void setGlCompanyMasterSid(int glCompanyMasterSid) {
        _glCompanyMasterSid = glCompanyMasterSid;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;
    }

    public String getAccrualPeriod() {
        return _accrualPeriod;
    }

    public void setAccrualPeriod(String accrualPeriod) {
        _accrualPeriod = accrualPeriod;
    }

    public String getCompanyGroupSid() {
        return _companyGroupSid;
    }

    public void setCompanyGroupSid(String companyGroupSid) {
        _companyGroupSid = companyGroupSid;
    }

    public int getAccClosureMasterSid() {
        return _accClosureMasterSid;
    }

    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureMasterSid = accClosureMasterSid;
    }

    public int getRsCategory() {
        return _rsCategory;
    }

    public void setRsCategory(int rsCategory) {
        _rsCategory = rsCategory;
    }

    public int getAdjustmentType() {
        return _adjustmentType;
    }

    public void setAdjustmentType(int adjustmentType) {
        _adjustmentType = adjustmentType;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getItemGroupSid() {
        return _itemGroupSid;
    }

    public void setItemGroupSid(String itemGroupSid) {
        _itemGroupSid = itemGroupSid;
    }

    public int getRebateProgramType() {
        return _rebateProgramType;
    }

    public void setRebateProgramType(int rebateProgramType) {
        _rebateProgramType = rebateProgramType;
    }
}
