package com.stpl.app.model;

import com.stpl.app.service.persistence.NmDiscountProjectionPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class NmDiscountProjectionSoap implements Serializable {
    private String _adjustmentMethodology;
    private String _adjustmentBasis;
    private int _periodSid;
    private double _projectionRate;
    private int _projectionDetailsSid;
    private boolean _adjustmentVariable;
    private double _adjustmentValue;
    private String _adjustmentType;
    private double _projectionSales;
    private double _discountRate;

    public NmDiscountProjectionSoap() {
    }

    public static NmDiscountProjectionSoap toSoapModel(
        NmDiscountProjection model) {
        NmDiscountProjectionSoap soapModel = new NmDiscountProjectionSoap();

        soapModel.setAdjustmentMethodology(model.getAdjustmentMethodology());
        soapModel.setAdjustmentBasis(model.getAdjustmentBasis());
        soapModel.setPeriodSid(model.getPeriodSid());
        soapModel.setProjectionRate(model.getProjectionRate());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setAdjustmentVariable(model.getAdjustmentVariable());
        soapModel.setAdjustmentValue(model.getAdjustmentValue());
        soapModel.setAdjustmentType(model.getAdjustmentType());
        soapModel.setProjectionSales(model.getProjectionSales());
        soapModel.setDiscountRate(model.getDiscountRate());

        return soapModel;
    }

    public static NmDiscountProjectionSoap[] toSoapModels(
        NmDiscountProjection[] models) {
        NmDiscountProjectionSoap[] soapModels = new NmDiscountProjectionSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static NmDiscountProjectionSoap[][] toSoapModels(
        NmDiscountProjection[][] models) {
        NmDiscountProjectionSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new NmDiscountProjectionSoap[models.length][models[0].length];
        } else {
            soapModels = new NmDiscountProjectionSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static NmDiscountProjectionSoap[] toSoapModels(
        List<NmDiscountProjection> models) {
        List<NmDiscountProjectionSoap> soapModels = new ArrayList<NmDiscountProjectionSoap>(models.size());

        for (NmDiscountProjection model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new NmDiscountProjectionSoap[soapModels.size()]);
    }

    public NmDiscountProjectionPK getPrimaryKey() {
        return new NmDiscountProjectionPK(_periodSid, _projectionDetailsSid);
    }

    public void setPrimaryKey(NmDiscountProjectionPK pk) {
        setPeriodSid(pk.periodSid);
        setProjectionDetailsSid(pk.projectionDetailsSid);
    }

    public String getAdjustmentMethodology() {
        return _adjustmentMethodology;
    }

    public void setAdjustmentMethodology(String adjustmentMethodology) {
        _adjustmentMethodology = adjustmentMethodology;
    }

    public String getAdjustmentBasis() {
        return _adjustmentBasis;
    }

    public void setAdjustmentBasis(String adjustmentBasis) {
        _adjustmentBasis = adjustmentBasis;
    }

    public int getPeriodSid() {
        return _periodSid;
    }

    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;
    }

    public double getProjectionRate() {
        return _projectionRate;
    }

    public void setProjectionRate(double projectionRate) {
        _projectionRate = projectionRate;
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    public boolean getAdjustmentVariable() {
        return _adjustmentVariable;
    }

    public boolean isAdjustmentVariable() {
        return _adjustmentVariable;
    }

    public void setAdjustmentVariable(boolean adjustmentVariable) {
        _adjustmentVariable = adjustmentVariable;
    }

    public double getAdjustmentValue() {
        return _adjustmentValue;
    }

    public void setAdjustmentValue(double adjustmentValue) {
        _adjustmentValue = adjustmentValue;
    }

    public String getAdjustmentType() {
        return _adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        _adjustmentType = adjustmentType;
    }

    public double getProjectionSales() {
        return _projectionSales;
    }

    public void setProjectionSales(double projectionSales) {
        _projectionSales = projectionSales;
    }

    public double getDiscountRate() {
        return _discountRate;
    }

    public void setDiscountRate(double discountRate) {
        _discountRate = discountRate;
    }
}
