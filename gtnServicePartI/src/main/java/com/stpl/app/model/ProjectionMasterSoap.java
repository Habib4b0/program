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
public class ProjectionMasterSoap implements Serializable {
    private int _productHierarchyLevel;
    private boolean _saveFlag;
    private String _projectionName;
    private Date _toDate;
    private int _projectionMasterSid;
    private String _forecastingType;
    private int _productHierVersionNo;
    private int _customerHierVersionNo;
    private Date _modifiedDate;
    private int _customerHierarchyLevel;
    private Date _fromDate;
    private String _productHierarchySid;
    private Date _createdDate;
    private int _createdBy;
    private String _customerHierarchySid;
    private String _companyGroupSid;
    private boolean _brandType;
    private int _modifiedBy;
    private String _projectionDescription;
    private String _isApproved;
    private String _itemGroupSid;
    private String _companyMasterSid;
    private int _customerHierarchyInnerLevel;
    private int _productHierarchyInnerLevel;
    private String _custRelationshipBuilderSid;
    private String _prodRelationshipBuilderSid;
    private int _discountType;
    private int _businessUnit;

    public ProjectionMasterSoap() {
    }

    public static ProjectionMasterSoap toSoapModel(ProjectionMaster model) {
        ProjectionMasterSoap soapModel = new ProjectionMasterSoap();

        soapModel.setProductHierarchyLevel(model.getProductHierarchyLevel());
        soapModel.setSaveFlag(model.getSaveFlag());
        soapModel.setProjectionName(model.getProjectionName());
        soapModel.setToDate(model.getToDate());
        soapModel.setProjectionMasterSid(model.getProjectionMasterSid());
        soapModel.setForecastingType(model.getForecastingType());
        soapModel.setProductHierVersionNo(model.getProductHierVersionNo());
        soapModel.setCustomerHierVersionNo(model.getCustomerHierVersionNo());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCustomerHierarchyLevel(model.getCustomerHierarchyLevel());
        soapModel.setFromDate(model.getFromDate());
        soapModel.setProductHierarchySid(model.getProductHierarchySid());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCustomerHierarchySid(model.getCustomerHierarchySid());
        soapModel.setCompanyGroupSid(model.getCompanyGroupSid());
        soapModel.setBrandType(model.getBrandType());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setProjectionDescription(model.getProjectionDescription());
        soapModel.setIsApproved(model.getIsApproved());
        soapModel.setItemGroupSid(model.getItemGroupSid());
        soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
        soapModel.setCustomerHierarchyInnerLevel(model.getCustomerHierarchyInnerLevel());
        soapModel.setProductHierarchyInnerLevel(model.getProductHierarchyInnerLevel());
        soapModel.setCustRelationshipBuilderSid(model.getCustRelationshipBuilderSid());
        soapModel.setProdRelationshipBuilderSid(model.getProdRelationshipBuilderSid());
        soapModel.setDiscountType(model.getDiscountType());
        soapModel.setBusinessUnit(model.getBusinessUnit());

        return soapModel;
    }

    public static ProjectionMasterSoap[] toSoapModels(ProjectionMaster[] models) {
        ProjectionMasterSoap[] soapModels = new ProjectionMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProjectionMasterSoap[][] toSoapModels(
        ProjectionMaster[][] models) {
        ProjectionMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProjectionMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new ProjectionMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProjectionMasterSoap[] toSoapModels(
        List<ProjectionMaster> models) {
        List<ProjectionMasterSoap> soapModels = new ArrayList<ProjectionMasterSoap>(models.size());

        for (ProjectionMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProjectionMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _projectionMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setProjectionMasterSid(pk);
    }

    public int getProductHierarchyLevel() {
        return _productHierarchyLevel;
    }

    public void setProductHierarchyLevel(int productHierarchyLevel) {
        _productHierarchyLevel = productHierarchyLevel;
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

    public String getProjectionName() {
        return _projectionName;
    }

    public void setProjectionName(String projectionName) {
        _projectionName = projectionName;
    }

    public Date getToDate() {
        return _toDate;
    }

    public void setToDate(Date toDate) {
        _toDate = toDate;
    }

    public int getProjectionMasterSid() {
        return _projectionMasterSid;
    }

    public void setProjectionMasterSid(int projectionMasterSid) {
        _projectionMasterSid = projectionMasterSid;
    }

    public String getForecastingType() {
        return _forecastingType;
    }

    public void setForecastingType(String forecastingType) {
        _forecastingType = forecastingType;
    }

    public int getProductHierVersionNo() {
        return _productHierVersionNo;
    }

    public void setProductHierVersionNo(int productHierVersionNo) {
        _productHierVersionNo = productHierVersionNo;
    }

    public int getCustomerHierVersionNo() {
        return _customerHierVersionNo;
    }

    public void setCustomerHierVersionNo(int customerHierVersionNo) {
        _customerHierVersionNo = customerHierVersionNo;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public int getCustomerHierarchyLevel() {
        return _customerHierarchyLevel;
    }

    public void setCustomerHierarchyLevel(int customerHierarchyLevel) {
        _customerHierarchyLevel = customerHierarchyLevel;
    }

    public Date getFromDate() {
        return _fromDate;
    }

    public void setFromDate(Date fromDate) {
        _fromDate = fromDate;
    }

    public String getProductHierarchySid() {
        return _productHierarchySid;
    }

    public void setProductHierarchySid(String productHierarchySid) {
        _productHierarchySid = productHierarchySid;
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

    public String getCustomerHierarchySid() {
        return _customerHierarchySid;
    }

    public void setCustomerHierarchySid(String customerHierarchySid) {
        _customerHierarchySid = customerHierarchySid;
    }

    public String getCompanyGroupSid() {
        return _companyGroupSid;
    }

    public void setCompanyGroupSid(String companyGroupSid) {
        _companyGroupSid = companyGroupSid;
    }

    public boolean getBrandType() {
        return _brandType;
    }

    public boolean isBrandType() {
        return _brandType;
    }

    public void setBrandType(boolean brandType) {
        _brandType = brandType;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getProjectionDescription() {
        return _projectionDescription;
    }

    public void setProjectionDescription(String projectionDescription) {
        _projectionDescription = projectionDescription;
    }

    public String getIsApproved() {
        return _isApproved;
    }

    public void setIsApproved(String isApproved) {
        _isApproved = isApproved;
    }

    public String getItemGroupSid() {
        return _itemGroupSid;
    }

    public void setItemGroupSid(String itemGroupSid) {
        _itemGroupSid = itemGroupSid;
    }

    public String getCompanyMasterSid() {
        return _companyMasterSid;
    }

    public void setCompanyMasterSid(String companyMasterSid) {
        _companyMasterSid = companyMasterSid;
    }

    public int getCustomerHierarchyInnerLevel() {
        return _customerHierarchyInnerLevel;
    }

    public void setCustomerHierarchyInnerLevel(int customerHierarchyInnerLevel) {
        _customerHierarchyInnerLevel = customerHierarchyInnerLevel;
    }

    public int getProductHierarchyInnerLevel() {
        return _productHierarchyInnerLevel;
    }

    public void setProductHierarchyInnerLevel(int productHierarchyInnerLevel) {
        _productHierarchyInnerLevel = productHierarchyInnerLevel;
    }

    public String getCustRelationshipBuilderSid() {
        return _custRelationshipBuilderSid;
    }

    public void setCustRelationshipBuilderSid(String custRelationshipBuilderSid) {
        _custRelationshipBuilderSid = custRelationshipBuilderSid;
    }

    public String getProdRelationshipBuilderSid() {
        return _prodRelationshipBuilderSid;
    }

    public void setProdRelationshipBuilderSid(String prodRelationshipBuilderSid) {
        _prodRelationshipBuilderSid = prodRelationshipBuilderSid;
    }

    public int getDiscountType() {
        return _discountType;
    }

    public void setDiscountType(int discountType) {
        _discountType = discountType;
    }

    public int getBusinessUnit() {
        return _businessUnit;
    }

    public void setBusinessUnit(int businessUnit) {
        _businessUnit = businessUnit;
    }
}
