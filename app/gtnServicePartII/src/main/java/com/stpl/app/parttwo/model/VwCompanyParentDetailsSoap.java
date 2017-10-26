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
public class VwCompanyParentDetailsSoap implements Serializable {
    private String _parentCompanyId;
    private String _priorParentCompanyId;
    private String _companyId;
    private Date _lastUpdatedDate;
    private Date _parentEndDate;
    private Date _modifiedDate;
    private Date _priorParentStartDate;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private int _companyParentDetailsSid;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _modifiedBy;
    private Date _parentStartDate;

    public VwCompanyParentDetailsSoap() {
    }

    public static VwCompanyParentDetailsSoap toSoapModel(
        VwCompanyParentDetails model) {
        VwCompanyParentDetailsSoap soapModel = new VwCompanyParentDetailsSoap();

        soapModel.setParentCompanyId(model.getParentCompanyId());
        soapModel.setPriorParentCompanyId(model.getPriorParentCompanyId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setLastUpdatedDate(model.getLastUpdatedDate());
        soapModel.setParentEndDate(model.getParentEndDate());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setPriorParentStartDate(model.getPriorParentStartDate());
        soapModel.setSource(model.getSource());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCompanyParentDetailsSid(model.getCompanyParentDetailsSid());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setParentStartDate(model.getParentStartDate());

        return soapModel;
    }

    public static VwCompanyParentDetailsSoap[] toSoapModels(
        VwCompanyParentDetails[] models) {
        VwCompanyParentDetailsSoap[] soapModels = new VwCompanyParentDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static VwCompanyParentDetailsSoap[][] toSoapModels(
        VwCompanyParentDetails[][] models) {
        VwCompanyParentDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new VwCompanyParentDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new VwCompanyParentDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static VwCompanyParentDetailsSoap[] toSoapModels(
        List<VwCompanyParentDetails> models) {
        List<VwCompanyParentDetailsSoap> soapModels = new ArrayList<VwCompanyParentDetailsSoap>(models.size());

        for (VwCompanyParentDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new VwCompanyParentDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _companyParentDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setCompanyParentDetailsSid(pk);
    }

    public String getParentCompanyId() {
        return _parentCompanyId;
    }

    public void setParentCompanyId(String parentCompanyId) {
        _parentCompanyId = parentCompanyId;
    }

    public String getPriorParentCompanyId() {
        return _priorParentCompanyId;
    }

    public void setPriorParentCompanyId(String priorParentCompanyId) {
        _priorParentCompanyId = priorParentCompanyId;
    }

    public String getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(String companyId) {
        _companyId = companyId;
    }

    public Date getLastUpdatedDate() {
        return _lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        _lastUpdatedDate = lastUpdatedDate;
    }

    public Date getParentEndDate() {
        return _parentEndDate;
    }

    public void setParentEndDate(Date parentEndDate) {
        _parentEndDate = parentEndDate;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public Date getPriorParentStartDate() {
        return _priorParentStartDate;
    }

    public void setPriorParentStartDate(Date priorParentStartDate) {
        _priorParentStartDate = priorParentStartDate;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public int getCompanyParentDetailsSid() {
        return _companyParentDetailsSid;
    }

    public void setCompanyParentDetailsSid(int companyParentDetailsSid) {
        _companyParentDetailsSid = companyParentDetailsSid;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public Date getParentStartDate() {
        return _parentStartDate;
    }

    public void setParentStartDate(Date parentStartDate) {
        _parentStartDate = parentStartDate;
    }
}
