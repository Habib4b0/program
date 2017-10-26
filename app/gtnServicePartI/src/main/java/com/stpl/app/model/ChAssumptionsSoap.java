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
public class ChAssumptionsSoap implements Serializable {
    private boolean _parent;
    private int _projectionDetailsSid;
    private String _commentary;
    private int _quarter;
    private double _totalDiscountPercentChange;
    private String _reasonCodes;
    private int _year;
    private double _totalDiscountPercentProjected;
    private double _totalDiscountPercentPrior;
    private int _chAssumptionsSid;
    private double _totalDiscountChange;
    private double _totalDiscountProjected;
    private int _camId;
    private double _grossTradeSales;
    private double _totalDiscountPrior;

    public ChAssumptionsSoap() {
    }

    public static ChAssumptionsSoap toSoapModel(ChAssumptions model) {
        ChAssumptionsSoap soapModel = new ChAssumptionsSoap();

        soapModel.setParent(model.getParent());
        soapModel.setProjectionDetailsSid(model.getProjectionDetailsSid());
        soapModel.setCommentary(model.getCommentary());
        soapModel.setQuarter(model.getQuarter());
        soapModel.setTotalDiscountPercentChange(model.getTotalDiscountPercentChange());
        soapModel.setReasonCodes(model.getReasonCodes());
        soapModel.setYear(model.getYear());
        soapModel.setTotalDiscountPercentProjected(model.getTotalDiscountPercentProjected());
        soapModel.setTotalDiscountPercentPrior(model.getTotalDiscountPercentPrior());
        soapModel.setChAssumptionsSid(model.getChAssumptionsSid());
        soapModel.setTotalDiscountChange(model.getTotalDiscountChange());
        soapModel.setTotalDiscountProjected(model.getTotalDiscountProjected());
        soapModel.setCamId(model.getCamId());
        soapModel.setGrossTradeSales(model.getGrossTradeSales());
        soapModel.setTotalDiscountPrior(model.getTotalDiscountPrior());

        return soapModel;
    }

    public static ChAssumptionsSoap[] toSoapModels(ChAssumptions[] models) {
        ChAssumptionsSoap[] soapModels = new ChAssumptionsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ChAssumptionsSoap[][] toSoapModels(ChAssumptions[][] models) {
        ChAssumptionsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ChAssumptionsSoap[models.length][models[0].length];
        } else {
            soapModels = new ChAssumptionsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ChAssumptionsSoap[] toSoapModels(List<ChAssumptions> models) {
        List<ChAssumptionsSoap> soapModels = new ArrayList<ChAssumptionsSoap>(models.size());

        for (ChAssumptions model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ChAssumptionsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _chAssumptionsSid;
    }

    public void setPrimaryKey(int pk) {
        setChAssumptionsSid(pk);
    }

    public boolean getParent() {
        return _parent;
    }

    public boolean isParent() {
        return _parent;
    }

    public void setParent(boolean parent) {
        _parent = parent;
    }

    public int getProjectionDetailsSid() {
        return _projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        _projectionDetailsSid = projectionDetailsSid;
    }

    public String getCommentary() {
        return _commentary;
    }

    public void setCommentary(String commentary) {
        _commentary = commentary;
    }

    public int getQuarter() {
        return _quarter;
    }

    public void setQuarter(int quarter) {
        _quarter = quarter;
    }

    public double getTotalDiscountPercentChange() {
        return _totalDiscountPercentChange;
    }

    public void setTotalDiscountPercentChange(double totalDiscountPercentChange) {
        _totalDiscountPercentChange = totalDiscountPercentChange;
    }

    public String getReasonCodes() {
        return _reasonCodes;
    }

    public void setReasonCodes(String reasonCodes) {
        _reasonCodes = reasonCodes;
    }

    public int getYear() {
        return _year;
    }

    public void setYear(int year) {
        _year = year;
    }

    public double getTotalDiscountPercentProjected() {
        return _totalDiscountPercentProjected;
    }

    public void setTotalDiscountPercentProjected(
        double totalDiscountPercentProjected) {
        _totalDiscountPercentProjected = totalDiscountPercentProjected;
    }

    public double getTotalDiscountPercentPrior() {
        return _totalDiscountPercentPrior;
    }

    public void setTotalDiscountPercentPrior(double totalDiscountPercentPrior) {
        _totalDiscountPercentPrior = totalDiscountPercentPrior;
    }

    public int getChAssumptionsSid() {
        return _chAssumptionsSid;
    }

    public void setChAssumptionsSid(int chAssumptionsSid) {
        _chAssumptionsSid = chAssumptionsSid;
    }

    public double getTotalDiscountChange() {
        return _totalDiscountChange;
    }

    public void setTotalDiscountChange(double totalDiscountChange) {
        _totalDiscountChange = totalDiscountChange;
    }

    public double getTotalDiscountProjected() {
        return _totalDiscountProjected;
    }

    public void setTotalDiscountProjected(double totalDiscountProjected) {
        _totalDiscountProjected = totalDiscountProjected;
    }

    public int getCamId() {
        return _camId;
    }

    public void setCamId(int camId) {
        _camId = camId;
    }

    public double getGrossTradeSales() {
        return _grossTradeSales;
    }

    public void setGrossTradeSales(double grossTradeSales) {
        _grossTradeSales = grossTradeSales;
    }

    public double getTotalDiscountPrior() {
        return _totalDiscountPrior;
    }

    public void setTotalDiscountPrior(double totalDiscountPrior) {
        _totalDiscountPrior = totalDiscountPrior;
    }
}
