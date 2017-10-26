package com.stpl.app.model;

import com.stpl.app.service.persistence.StMSupplementalDiscMasterPK;

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
public class StMSupplementalDiscMasterSoap implements Serializable {
    private double _actualDiscountRate2;
    private double _actualDiscountRate1;
    private String _marketType;
    private String _actualMethodology;
    private double _actualContractPrice;
    private int _userId;
    private Date _lastModifiedDate;
    private int _projectionDetailsSid;
    private double _actualDiscount;
    private int _sessionId;
    private int _checkRecord;
    private Date _contractEndDate;

    public StMSupplementalDiscMasterSoap() {
    }

    public static StMSupplementalDiscMasterSoap toSoapModel(
        StMSupplementalDiscMaster model) {
        StMSupplementalDiscMasterSoap soapModel = new StMSupplementalDiscMasterSoap();

        soapModel.setActualDiscountRate2(model.getActualDiscountRate2());
        soapModel.setActualDiscountRate1(model.getActualDiscountRate1());
        soapModel.setMarketType(model.getMarketType());
        soapModel.setActualMethodology(model.getActualMethodology());
        soapModel.setActualContractPrice(model.getActualContractPrice());
        soapModel.setUserId(model.getUserId());
        soapModel.setLastModifiedDate(model.getLastModifiedDate());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setActualDiscount(model.getActualDiscount());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setContractEndDate(model.getContractEndDate());

        return soapModel;
    }

    public static StMSupplementalDiscMasterSoap[] toSoapModels(
        StMSupplementalDiscMaster[] models) {
        StMSupplementalDiscMasterSoap[] soapModels = new StMSupplementalDiscMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static StMSupplementalDiscMasterSoap[][] toSoapModels(
        StMSupplementalDiscMaster[][] models) {
        StMSupplementalDiscMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new StMSupplementalDiscMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new StMSupplementalDiscMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static StMSupplementalDiscMasterSoap[] toSoapModels(
        List<StMSupplementalDiscMaster> models) {
        List<StMSupplementalDiscMasterSoap> soapModels = new ArrayList<StMSupplementalDiscMasterSoap>(models.size());

        for (StMSupplementalDiscMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new StMSupplementalDiscMasterSoap[soapModels.size()]);
    }

    public StMSupplementalDiscMasterPK getPrimaryKey() {
        return new StMSupplementalDiscMasterPK(_userId, _projectionDetailsSid,
            _sessionId);
    }

    public void setPrimaryKey(StMSupplementalDiscMasterPK pk) {
        setUserId(pk.userId);
        setProjectionDetailsSid(pk.projectionDetailsSid);
        setSessionId(pk.sessionId);
    }

    public double getActualDiscountRate2() {
        return _actualDiscountRate2;
    }

    public void setActualDiscountRate2(double actualDiscountRate2) {
        _actualDiscountRate2 = actualDiscountRate2;
    }

    public double getActualDiscountRate1() {
        return _actualDiscountRate1;
    }

    public void setActualDiscountRate1(double actualDiscountRate1) {
        _actualDiscountRate1 = actualDiscountRate1;
    }

    public String getMarketType() {
        return _marketType;
    }

    public void setMarketType(String marketType) {
        _marketType = marketType;
    }

    public String getActualMethodology() {
        return _actualMethodology;
    }

    public void setActualMethodology(String actualMethodology) {
        _actualMethodology = actualMethodology;
    }

    public double getActualContractPrice() {
        return _actualContractPrice;
    }

    public void setActualContractPrice(double actualContractPrice) {
        _actualContractPrice = actualContractPrice;
    }

    public int getUserId() {
        return _userId;
    }

    public void setUserId(int userId) {
        _userId = userId;
    }

    public Date getLastModifiedDate() {
        return _lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        _lastModifiedDate = lastModifiedDate;
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    public double getActualDiscount() {
        return _actualDiscount;
    }

    public void setActualDiscount(double actualDiscount) {
        _actualDiscount = actualDiscount;
    }

    public int getSessionId() {
        return _sessionId;
    }

    public void setSessionId(int sessionId) {
        _sessionId = sessionId;
    }

    public int getCheckRecord() {
        return _checkRecord;
    }

    public void setCheckRecord(int checkRecord) {
        _checkRecord = checkRecord;
    }

    public Date getContractEndDate() {
        return _contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        _contractEndDate = contractEndDate;
    }
}
