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
public class StAccClosureDetailsSoap implements Serializable {
    private Date _lastModifiedDate;
    private boolean _checkRecord;
    private String _contractName;
    private int _userId;
    private int _itemMasterSid;
    private String _moduleName;
    private String _companyName;
    private String _brandName;
    private String _companyCostCenter;
    private String _companyNo;
    private int _contractMasterSid;
    private int _sessionId;
    private int _ccpDetailsSid;
    private String _itemName;
    private int _accClosureMasterSid;
    private int _rsModelSid;
    private String _contractNo;
    private int _companyMasterSid;
    private String _ndc8;

    public StAccClosureDetailsSoap() {
    }

    public static StAccClosureDetailsSoap toSoapModel(StAccClosureDetails model) {
        StAccClosureDetailsSoap soapModel = new StAccClosureDetailsSoap();

        soapModel.setLastModifiedDate(model.getLastModifiedDate());
        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setContractName(model.getContractName());
        soapModel.setUserId(model.getUserId());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setModuleName(model.getModuleName());
        soapModel.setCompanyName(model.getCompanyName());
        soapModel.setBrandName(model.getBrandName());
        soapModel.setCompanyCostCenter(model.getCompanyCostCenter());
        soapModel.setCompanyNo(model.getCompanyNo());
        soapModel.setContractMasterSid(model.getContractMasterSid());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setCcpDetailsSid(model.getCcpDetailsSid());
        soapModel.setItemName(model.getItemName());
        soapModel.setAccClosureMasterSid(model.getAccClosureMasterSid());
        soapModel.setRsModelSid(model.getRsModelSid());
        soapModel.setContractNo(model.getContractNo());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
        soapModel.setNdc8(model.getNdc8());

        return soapModel;
    }

    public static StAccClosureDetailsSoap[] toSoapModels(
        StAccClosureDetails[] models) {
        StAccClosureDetailsSoap[] soapModels = new StAccClosureDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static StAccClosureDetailsSoap[][] toSoapModels(
        StAccClosureDetails[][] models) {
        StAccClosureDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new StAccClosureDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new StAccClosureDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static StAccClosureDetailsSoap[] toSoapModels(
        List<StAccClosureDetails> models) {
        List<StAccClosureDetailsSoap> soapModels = new ArrayList<StAccClosureDetailsSoap>(models.size());

        for (StAccClosureDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new StAccClosureDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _accClosureMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setAccClosureMasterSid(pk);
    }

    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;
    }

    public boolean getCheckRecord() {
        return _checkRecord;
    }

    public boolean isCheckRecord() {
        return _checkRecord;
    }

    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;
    }

    public String getContractName() {
        return _contractName;
    }

    public void setContractName(String contractName) {
        _contractName = contractName;
    }

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public String getModuleName() {
        return _moduleName;
    }

    public void setModuleName(String moduleName) {
        _moduleName = moduleName;
    }

    public String getCompanyName() {
        return _companyName;
    }

    public void setCompanyName(String companyName) {
        _companyName = companyName;
    }

    public String getBrandName() {
        return _brandName;
    }

    public void setBrandName(String brandName) {
        _brandName = brandName;
    }

    public String getCompanyCostCenter() {
        return _companyCostCenter;
    }

    public void setCompanyCostCenter(String companyCostCenter) {
        _companyCostCenter = companyCostCenter;
    }

    public String getCompanyNo() {
        return _companyNo;
    }

    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;
    }

    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;
    }

    public int getSessionId() {
        return _sessionId;
    }

    public void setSessionId(int sessionId) {
        _sessionId = sessionId;
    }

    public int getCcpDetailsSid() {
        return _ccpDetailsSid;
    }

    public void setCcpDetailsSid(int ccpDetailsSid) {
        _ccpDetailsSid = ccpDetailsSid;
    }

    public String getItemName() {
        return _itemName;
    }

    public void setItemName(String itemName) {
        _itemName = itemName;
    }

    public int getAccClosureMasterSid() {
        return _accClosureMasterSid;
    }

    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureMasterSid = accClosureMasterSid;
    }

    public int getRsModelSid() {
        return _rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;
    }

    public String getContractNo() {
        return _contractNo;
    }

    public void setContractNo(String contractNo) {
        _contractNo = contractNo;
    }

    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }

    public String getNdc8() {
        return _ndc8;
    }

    public void setNdc8(String ndc8) {
        _ndc8 = ndc8;
    }
}
