package com.stpl.app.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class ProjectionProdDetailsSoap implements Serializable {
    private String _productName;
    private String _costCenter;
    private String _productNo;
    private String _subLedgerCode;
    private int _productDetailsId;
    private String _brandName;
    private int _projectionId;

    public ProjectionProdDetailsSoap() {
    }

    public static ProjectionProdDetailsSoap toSoapModel(
        ProjectionProdDetails model) {
        ProjectionProdDetailsSoap soapModel = new ProjectionProdDetailsSoap();

        soapModel.setProductName(model.getProductName());
        soapModel.setCostCenter(model.getCostCenter());
        soapModel.setProductNo(model.getProductNo());
        soapModel.setSubLedgerCode(model.getSubLedgerCode());
        soapModel.setProductDetailsId(model.getProductDetailsId());
        soapModel.setBrandName(model.getBrandName());
        soapModel.setProjectionId(model.getProjectionId());

        return soapModel;
    }

    public static ProjectionProdDetailsSoap[] toSoapModels(
        ProjectionProdDetails[] models) {
        ProjectionProdDetailsSoap[] soapModels = new ProjectionProdDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ProjectionProdDetailsSoap[][] toSoapModels(
        ProjectionProdDetails[][] models) {
        ProjectionProdDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ProjectionProdDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new ProjectionProdDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ProjectionProdDetailsSoap[] toSoapModels(
        List<ProjectionProdDetails> models) {
        List<ProjectionProdDetailsSoap> soapModels = new ArrayList<ProjectionProdDetailsSoap>(models.size());

        for (ProjectionProdDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ProjectionProdDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _productDetailsId;
    }

    public void setPrimaryKey(int pk) {
        setProductDetailsId(pk);
    }

    public String getProductName() {
        return _productName;
    }

    public void setProductName(String productName) {
        _productName = productName;
    }

    public String getCostCenter() {
        return _costCenter;
    }

    public void setCostCenter(String costCenter) {
        _costCenter = costCenter;
    }

    public String getProductNo() {
        return _productNo;
    }

    public void setProductNo(String productNo) {
        _productNo = productNo;
    }

    public String getSubLedgerCode() {
        return _subLedgerCode;
    }

    public void setSubLedgerCode(String subLedgerCode) {
        _subLedgerCode = subLedgerCode;
    }

    public int getProductDetailsId() {
        return _productDetailsId;
    }

    public void setProductDetailsId(int productDetailsId) {
        _productDetailsId = productDetailsId;
    }

    public String getBrandName() {
        return _brandName;
    }

    public void setBrandName(String brandName) {
        _brandName = brandName;
    }

    public int getProjectionId() {
        return _projectionId;
    }

    public void setProjectionId(int projectionId) {
        _projectionId = projectionId;
    }
}
