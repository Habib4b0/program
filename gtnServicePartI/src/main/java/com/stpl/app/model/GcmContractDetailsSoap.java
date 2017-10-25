package com.stpl.app.model;

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
public class GcmContractDetailsSoap implements Serializable {
    private String _paymentMethod;
    private int _userId;
    private Date _endDate;
    private String _paymentFrequency;
    private int _gcmContractDetailsSid;
    private String _componentId;
    private Date _modifiedDate;
    private String _componentName;
    private String _rsCalendar;
    private String _fileName;
    private Date _startDate;
    private String _planLevel;
    private Date _createdDate;
    private int _createdBy;
    private String _componentNo;
    private String _programType;
    private String _sessionId;
    private int _modifiedBy;
    private String _componentStatus;
    private String _componentType;

    public GcmContractDetailsSoap() {
    }

    public static GcmContractDetailsSoap toSoapModel(GcmContractDetails model) {
        GcmContractDetailsSoap soapModel = new GcmContractDetailsSoap();

        soapModel.setPaymentMethod(model.getPaymentMethod());
        soapModel.setUserId(model.getUserId());
        soapModel.setEndDate(model.getEndDate());
        soapModel.setPaymentFrequency(model.getPaymentFrequency());
        soapModel.setGcmContractDetailsSid(model.getGcmContractDetailsSid());
        soapModel.setComponentId(model.getComponentId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setComponentName(model.getComponentName());
        soapModel.setRsCalendar(model.getRsCalendar());
        soapModel.setFileName(model.getFileName());
        soapModel.setStartDate(model.getStartDate());
        soapModel.setPlanLevel(model.getPlanLevel());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setComponentNo(model.getComponentNo());
        soapModel.setProgramType(model.getProgramType());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setComponentStatus(model.getComponentStatus());
        soapModel.setComponentType(model.getComponentType());

        return soapModel;
    }

    public static GcmContractDetailsSoap[] toSoapModels(
        GcmContractDetails[] models) {
        GcmContractDetailsSoap[] soapModels = new GcmContractDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static GcmContractDetailsSoap[][] toSoapModels(
        GcmContractDetails[][] models) {
        GcmContractDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new GcmContractDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new GcmContractDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static GcmContractDetailsSoap[] toSoapModels(
        List<GcmContractDetails> models) {
        List<GcmContractDetailsSoap> soapModels = new ArrayList<GcmContractDetailsSoap>(models.size());

        for (GcmContractDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new GcmContractDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _gcmContractDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setGcmContractDetailsSid(pk);
    }

    public String getPaymentMethod() {
        return _paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        _paymentMethod = paymentMethod;
    }

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public Date getEndDate() {
        return _endDate;
    }

    public void setEndDate(Date endDate) {
        _endDate = endDate;
    }

    public String getPaymentFrequency() {
        return _paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        _paymentFrequency = paymentFrequency;
    }

    public int getGcmContractDetailsSid() {
        return _gcmContractDetailsSid;
    }

    public void setGcmContractDetailsSid(int gcmContractDetailsSid) {
        _gcmContractDetailsSid = gcmContractDetailsSid;
    }

    public String getComponentId() {
        return _componentId;
    }

    public void setComponentId(String componentId) {
        _componentId = componentId;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getComponentName() {
        return _componentName;
    }

    public void setComponentName(String componentName) {
        _componentName = componentName;
    }

    public String getRsCalendar() {
        return _rsCalendar;
    }

    public void setRsCalendar(String rsCalendar) {
        _rsCalendar = rsCalendar;
    }

    public String getFileName() {
        return _fileName;
    }

    public void setFileName(String fileName) {
        _fileName = fileName;
    }

    public Date getStartDate() {
        return _startDate;
    }

    public void setStartDate(Date startDate) {
        _startDate = startDate;
    }

    public String getPlanLevel() {
        return _planLevel;
    }

    public void setPlanLevel(String planLevel) {
        _planLevel = planLevel;
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

    public String getComponentNo() {
        return _componentNo;
    }

    public void setComponentNo(String componentNo) {
        _componentNo = componentNo;
    }

    public String getProgramType() {
        return _programType;
    }

    public void setProgramType(String programType) {
        _programType = programType;
    }

    public String getSessionId() {
        return _sessionId;
    }

    public void setSessionId(String sessionId) {
        _sessionId = sessionId;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getComponentStatus() {
        return _componentStatus;
    }

    public void setComponentStatus(String componentStatus) {
        _componentStatus = componentStatus;
    }

    public String getComponentType() {
        return _componentType;
    }

    public void setComponentType(String componentType) {
        _componentType = componentType;
    }
}
