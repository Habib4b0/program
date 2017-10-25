package com.stpl.app.model;

import com.stpl.app.service.persistence.StMSupplementalDiscProjPK;

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
public class StMSupplementalDiscProjSoap implements Serializable {
    private double _projectionRate;
    private int _userId;
    private Date _lastModifiedDate;
    private String _parityReference;
    private double _projectionSales;
    private double _contractPrice;
    private String _methodology;
    private boolean _parity;
    private int _periodSid;
    private double _discountRate1;
    private int _projectionDetailsSid;
    private double _discountRate2;
    private double _parityDiscount;
    private int _sessionId;
    private String _access;

    public StMSupplementalDiscProjSoap() {
    }

    public static StMSupplementalDiscProjSoap toSoapModel(
        StMSupplementalDiscProj model) {
        StMSupplementalDiscProjSoap soapModel = new StMSupplementalDiscProjSoap();

        soapModel.setProjectionRate(model.getProjectionRate());
        soapModel.setUserId(model.getUserId());
        soapModel.setLastModifiedDate(model.getLastModifiedDate());
        soapModel.setParityReference(model.getParityReference());
        soapModel.setProjectionSales(model.getProjectionSales());
        soapModel.setContractPrice(model.getContractPrice());
        soapModel.setMethodology(model.getMethodology());
        soapModel.setParity(model.getParity());
        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setDiscountRate1(model.getDiscountRate1());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setDiscountRate2(model.getDiscountRate2());
        soapModel.setParityDiscount(model.getParityDiscount());
        soapModel.setSessionId(model.getSessionId());
        soapModel.setAccess(model.getAccess());

        return soapModel;
    }

    public static StMSupplementalDiscProjSoap[] toSoapModels(
        StMSupplementalDiscProj[] models) {
        StMSupplementalDiscProjSoap[] soapModels = new StMSupplementalDiscProjSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static StMSupplementalDiscProjSoap[][] toSoapModels(
        StMSupplementalDiscProj[][] models) {
        StMSupplementalDiscProjSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new StMSupplementalDiscProjSoap[models.length][models[0].length];
        } else {
            soapModels = new StMSupplementalDiscProjSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static StMSupplementalDiscProjSoap[] toSoapModels(
        List<StMSupplementalDiscProj> models) {
        List<StMSupplementalDiscProjSoap> soapModels = new ArrayList<StMSupplementalDiscProjSoap>(models.size());

        for (StMSupplementalDiscProj model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new StMSupplementalDiscProjSoap[soapModels.size()]);
    }

    public StMSupplementalDiscProjPK getPrimaryKey() {
        return new StMSupplementalDiscProjPK(_userId, _projectionDetailsSid,
            _sessionId);
    }

    public void setPrimaryKey(StMSupplementalDiscProjPK pk) {
        setUserId(pk.userId);
        setProjectionDetailsSid(pk.projectionDetailsSid);
        setSessionId(pk.sessionId);
    }

    public double getProjectionRate() {
        return _projectionRate;
    }

    public void setProjectionRate(double projectionRate) {
        _projectionRate = projectionRate;
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

    public String getParityReference() {
        return _parityReference;
    }

    public void setParityReference(String parityReference) {
        _parityReference = parityReference;
    }

    public double getProjectionSales() {
        return _projectionSales;
    }

    public void setProjectionSales(double projectionSales) {
        _projectionSales = projectionSales;
    }

    public double getContractPrice() {
        return _contractPrice;
    }

    public void setContractPrice(double contractPrice) {
        _contractPrice = contractPrice;
    }

    public String getMethodology() {
        return _methodology;
    }

    public void setMethodology(String methodology) {
        _methodology = methodology;
    }

    public boolean getParity() {
        return _parity;
    }

    public boolean isParity() {
        return _parity;
    }

    public void setParity(boolean parity) {
        _parity = parity;
    }

    public int getPeriodSid() {
        return _periodSid;
    }

    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    public double getDiscountRate1() {
        return _discountRate1;
    }

    public void setDiscountRate1(double discountRate1) {
        _discountRate1 = discountRate1;
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    public double getDiscountRate2() {
        return _discountRate2;
    }

    public void setDiscountRate2(double discountRate2) {
        _discountRate2 = discountRate2;
    }

    public double getParityDiscount() {
        return _parityDiscount;
    }

    public void setParityDiscount(double parityDiscount) {
        _parityDiscount = parityDiscount;
    }

    public int getSessionId() {
        return _sessionId;
    }

    public void setSessionId(int sessionId) {
        _sessionId = sessionId;
    }

    public String getAccess() {
        return _access;
    }

    public void setAccess(String access) {
        _access = access;
    }
}
