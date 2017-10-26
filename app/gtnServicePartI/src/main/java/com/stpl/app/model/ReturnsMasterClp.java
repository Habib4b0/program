package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ReturnsMasterLocalServiceUtil;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ReturnsMasterClp extends BaseModelImpl<ReturnsMaster>
    implements ReturnsMaster {
    private String _adjValueAtOrigAsp;
    private int _itemMasterSid;
    private Date _firstReturn;
    private String _asp;
    private Date _maxExpiredMonthPlusCutoff;
    private String _posEstimatedReturnUnits;
    private Date _origSaleMonthCutOff;
    private String _calcUsed;
    private Date _modifiedDate;
    private int _brandMasterSid;
    private Date _lastReturn;
    private String _expectedReturnUnits;
    private Date _createdDate;
    private String _createdBy;
    private String _source;
    private String _version;
    private String _addChgDelIndicator;
    private int _returnsMasterSid;
    private String _weightedAvgMonths;
    private String _modifiedBy;
    private String _inboundStatus;
    private String _pct25th;
    private Date _loadDate;
    private Date _maxExpiredMonth;
    private String _actualReturnRate;
    private String _rreserveId;
    private String _returnComplete;
    private String _expectedReturnRate;
    private String _pct50th;
    private String _within50qrtile;
    private String _cumReturnUnits;
    private Date _origSaleMonth;
    private String _description;
    private String _sku;
    private String _upperLimit;
    private String _lowerLimit;
    private String _valueAtOrigAsp;
    private String _adjEstimatedReturnUnits;
    private String _pct75th;
    private boolean _recordLockStatus;
    private String _pastExpiration;
    private String _batchId;
    private String _maximum;
    private String _origSaleUnits;
    private String _brand;
    private String _origSaleDollars;
    private BaseModel<?> _returnsMasterRemoteModel;

    public ReturnsMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ReturnsMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ReturnsMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _returnsMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setReturnsMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _returnsMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjValueAtOrigAsp", getAdjValueAtOrigAsp());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("firstReturn", getFirstReturn());
        attributes.put("asp", getAsp());
        attributes.put("maxExpiredMonthPlusCutoff",
            getMaxExpiredMonthPlusCutoff());
        attributes.put("posEstimatedReturnUnits", getPosEstimatedReturnUnits());
        attributes.put("origSaleMonthCutOff", getOrigSaleMonthCutOff());
        attributes.put("calcUsed", getCalcUsed());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("lastReturn", getLastReturn());
        attributes.put("expectedReturnUnits", getExpectedReturnUnits());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("version", getVersion());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("returnsMasterSid", getReturnsMasterSid());
        attributes.put("weightedAvgMonths", getWeightedAvgMonths());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("pct25th", getPct25th());
        attributes.put("loadDate", getLoadDate());
        attributes.put("maxExpiredMonth", getMaxExpiredMonth());
        attributes.put("actualReturnRate", getActualReturnRate());
        attributes.put("rreserveId", getRreserveId());
        attributes.put("returnComplete", getReturnComplete());
        attributes.put("expectedReturnRate", getExpectedReturnRate());
        attributes.put("pct50th", getPct50th());
        attributes.put("within50qrtile", getWithin50qrtile());
        attributes.put("cumReturnUnits", getCumReturnUnits());
        attributes.put("origSaleMonth", getOrigSaleMonth());
        attributes.put("description", getDescription());
        attributes.put("sku", getSku());
        attributes.put("upperLimit", getUpperLimit());
        attributes.put("lowerLimit", getLowerLimit());
        attributes.put("valueAtOrigAsp", getValueAtOrigAsp());
        attributes.put("adjEstimatedReturnUnits", getAdjEstimatedReturnUnits());
        attributes.put("pct75th", getPct75th());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("pastExpiration", getPastExpiration());
        attributes.put("batchId", getBatchId());
        attributes.put("maximum", getMaximum());
        attributes.put("origSaleUnits", getOrigSaleUnits());
        attributes.put("brand", getBrand());
        attributes.put("origSaleDollars", getOrigSaleDollars());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String adjValueAtOrigAsp = (String) attributes.get("adjValueAtOrigAsp");

        if (adjValueAtOrigAsp != null) {
            setAdjValueAtOrigAsp(adjValueAtOrigAsp);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date firstReturn = (Date) attributes.get("firstReturn");

        if (firstReturn != null) {
            setFirstReturn(firstReturn);
        }

        String asp = (String) attributes.get("asp");

        if (asp != null) {
            setAsp(asp);
        }

        Date maxExpiredMonthPlusCutoff = (Date) attributes.get(
                "maxExpiredMonthPlusCutoff");

        if (maxExpiredMonthPlusCutoff != null) {
            setMaxExpiredMonthPlusCutoff(maxExpiredMonthPlusCutoff);
        }

        String posEstimatedReturnUnits = (String) attributes.get(
                "posEstimatedReturnUnits");

        if (posEstimatedReturnUnits != null) {
            setPosEstimatedReturnUnits(posEstimatedReturnUnits);
        }

        Date origSaleMonthCutOff = (Date) attributes.get("origSaleMonthCutOff");

        if (origSaleMonthCutOff != null) {
            setOrigSaleMonthCutOff(origSaleMonthCutOff);
        }

        String calcUsed = (String) attributes.get("calcUsed");

        if (calcUsed != null) {
            setCalcUsed(calcUsed);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer brandMasterSid = (Integer) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        Date lastReturn = (Date) attributes.get("lastReturn");

        if (lastReturn != null) {
            setLastReturn(lastReturn);
        }

        String expectedReturnUnits = (String) attributes.get(
                "expectedReturnUnits");

        if (expectedReturnUnits != null) {
            setExpectedReturnUnits(expectedReturnUnits);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String version = (String) attributes.get("version");

        if (version != null) {
            setVersion(version);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        Integer returnsMasterSid = (Integer) attributes.get("returnsMasterSid");

        if (returnsMasterSid != null) {
            setReturnsMasterSid(returnsMasterSid);
        }

        String weightedAvgMonths = (String) attributes.get("weightedAvgMonths");

        if (weightedAvgMonths != null) {
            setWeightedAvgMonths(weightedAvgMonths);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String pct25th = (String) attributes.get("pct25th");

        if (pct25th != null) {
            setPct25th(pct25th);
        }

        Date loadDate = (Date) attributes.get("loadDate");

        if (loadDate != null) {
            setLoadDate(loadDate);
        }

        Date maxExpiredMonth = (Date) attributes.get("maxExpiredMonth");

        if (maxExpiredMonth != null) {
            setMaxExpiredMonth(maxExpiredMonth);
        }

        String actualReturnRate = (String) attributes.get("actualReturnRate");

        if (actualReturnRate != null) {
            setActualReturnRate(actualReturnRate);
        }

        String rreserveId = (String) attributes.get("rreserveId");

        if (rreserveId != null) {
            setRreserveId(rreserveId);
        }

        String returnComplete = (String) attributes.get("returnComplete");

        if (returnComplete != null) {
            setReturnComplete(returnComplete);
        }

        String expectedReturnRate = (String) attributes.get(
                "expectedReturnRate");

        if (expectedReturnRate != null) {
            setExpectedReturnRate(expectedReturnRate);
        }

        String pct50th = (String) attributes.get("pct50th");

        if (pct50th != null) {
            setPct50th(pct50th);
        }

        String within50qrtile = (String) attributes.get("within50qrtile");

        if (within50qrtile != null) {
            setWithin50qrtile(within50qrtile);
        }

        String cumReturnUnits = (String) attributes.get("cumReturnUnits");

        if (cumReturnUnits != null) {
            setCumReturnUnits(cumReturnUnits);
        }

        Date origSaleMonth = (Date) attributes.get("origSaleMonth");

        if (origSaleMonth != null) {
            setOrigSaleMonth(origSaleMonth);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        String sku = (String) attributes.get("sku");

        if (sku != null) {
            setSku(sku);
        }

        String upperLimit = (String) attributes.get("upperLimit");

        if (upperLimit != null) {
            setUpperLimit(upperLimit);
        }

        String lowerLimit = (String) attributes.get("lowerLimit");

        if (lowerLimit != null) {
            setLowerLimit(lowerLimit);
        }

        String valueAtOrigAsp = (String) attributes.get("valueAtOrigAsp");

        if (valueAtOrigAsp != null) {
            setValueAtOrigAsp(valueAtOrigAsp);
        }

        String adjEstimatedReturnUnits = (String) attributes.get(
                "adjEstimatedReturnUnits");

        if (adjEstimatedReturnUnits != null) {
            setAdjEstimatedReturnUnits(adjEstimatedReturnUnits);
        }

        String pct75th = (String) attributes.get("pct75th");

        if (pct75th != null) {
            setPct75th(pct75th);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String pastExpiration = (String) attributes.get("pastExpiration");

        if (pastExpiration != null) {
            setPastExpiration(pastExpiration);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String maximum = (String) attributes.get("maximum");

        if (maximum != null) {
            setMaximum(maximum);
        }

        String origSaleUnits = (String) attributes.get("origSaleUnits");

        if (origSaleUnits != null) {
            setOrigSaleUnits(origSaleUnits);
        }

        String brand = (String) attributes.get("brand");

        if (brand != null) {
            setBrand(brand);
        }

        String origSaleDollars = (String) attributes.get("origSaleDollars");

        if (origSaleDollars != null) {
            setOrigSaleDollars(origSaleDollars);
        }
    }

    @Override
    public String getAdjValueAtOrigAsp() {
        return _adjValueAtOrigAsp;
    }

    @Override
    public void setAdjValueAtOrigAsp(String adjValueAtOrigAsp) {
        _adjValueAtOrigAsp = adjValueAtOrigAsp;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjValueAtOrigAsp",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, adjValueAtOrigAsp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_returnsMasterRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getFirstReturn() {
        return _firstReturn;
    }

    @Override
    public void setFirstReturn(Date firstReturn) {
        _firstReturn = firstReturn;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFirstReturn", Date.class);

                method.invoke(_returnsMasterRemoteModel, firstReturn);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAsp() {
        return _asp;
    }

    @Override
    public void setAsp(String asp) {
        _asp = asp;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAsp", String.class);

                method.invoke(_returnsMasterRemoteModel, asp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getMaxExpiredMonthPlusCutoff() {
        return _maxExpiredMonthPlusCutoff;
    }

    @Override
    public void setMaxExpiredMonthPlusCutoff(Date maxExpiredMonthPlusCutoff) {
        _maxExpiredMonthPlusCutoff = maxExpiredMonthPlusCutoff;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMaxExpiredMonthPlusCutoff",
                        Date.class);

                method.invoke(_returnsMasterRemoteModel,
                    maxExpiredMonthPlusCutoff);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPosEstimatedReturnUnits() {
        return _posEstimatedReturnUnits;
    }

    @Override
    public void setPosEstimatedReturnUnits(String posEstimatedReturnUnits) {
        _posEstimatedReturnUnits = posEstimatedReturnUnits;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPosEstimatedReturnUnits",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, posEstimatedReturnUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getOrigSaleMonthCutOff() {
        return _origSaleMonthCutOff;
    }

    @Override
    public void setOrigSaleMonthCutOff(Date origSaleMonthCutOff) {
        _origSaleMonthCutOff = origSaleMonthCutOff;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrigSaleMonthCutOff",
                        Date.class);

                method.invoke(_returnsMasterRemoteModel, origSaleMonthCutOff);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCalcUsed() {
        return _calcUsed;
    }

    @Override
    public void setCalcUsed(String calcUsed) {
        _calcUsed = calcUsed;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCalcUsed", String.class);

                method.invoke(_returnsMasterRemoteModel, calcUsed);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_returnsMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBrandMasterSid() {
        return _brandMasterSid;
    }

    @Override
    public void setBrandMasterSid(int brandMasterSid) {
        _brandMasterSid = brandMasterSid;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid", int.class);

                method.invoke(_returnsMasterRemoteModel, brandMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastReturn() {
        return _lastReturn;
    }

    @Override
    public void setLastReturn(Date lastReturn) {
        _lastReturn = lastReturn;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastReturn", Date.class);

                method.invoke(_returnsMasterRemoteModel, lastReturn);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getExpectedReturnUnits() {
        return _expectedReturnUnits;
    }

    @Override
    public void setExpectedReturnUnits(String expectedReturnUnits) {
        _expectedReturnUnits = expectedReturnUnits;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setExpectedReturnUnits",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, expectedReturnUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_returnsMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_returnsMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSource() {
        return _source;
    }

    @Override
    public void setSource(String source) {
        _source = source;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_returnsMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getVersion() {
        return _version;
    }

    @Override
    public void setVersion(String version) {
        _version = version;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setVersion", String.class);

                method.invoke(_returnsMasterRemoteModel, version);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    @Override
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getReturnsMasterSid() {
        return _returnsMasterSid;
    }

    @Override
    public void setReturnsMasterSid(int returnsMasterSid) {
        _returnsMasterSid = returnsMasterSid;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReturnsMasterSid", int.class);

                method.invoke(_returnsMasterRemoteModel, returnsMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWeightedAvgMonths() {
        return _weightedAvgMonths;
    }

    @Override
    public void setWeightedAvgMonths(String weightedAvgMonths) {
        _weightedAvgMonths = weightedAvgMonths;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setWeightedAvgMonths",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, weightedAvgMonths);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_returnsMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInboundStatus() {
        return _inboundStatus;
    }

    @Override
    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_returnsMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPct25th() {
        return _pct25th;
    }

    @Override
    public void setPct25th(String pct25th) {
        _pct25th = pct25th;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPct25th", String.class);

                method.invoke(_returnsMasterRemoteModel, pct25th);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLoadDate() {
        return _loadDate;
    }

    @Override
    public void setLoadDate(Date loadDate) {
        _loadDate = loadDate;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLoadDate", Date.class);

                method.invoke(_returnsMasterRemoteModel, loadDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getMaxExpiredMonth() {
        return _maxExpiredMonth;
    }

    @Override
    public void setMaxExpiredMonth(Date maxExpiredMonth) {
        _maxExpiredMonth = maxExpiredMonth;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMaxExpiredMonth", Date.class);

                method.invoke(_returnsMasterRemoteModel, maxExpiredMonth);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getActualReturnRate() {
        return _actualReturnRate;
    }

    @Override
    public void setActualReturnRate(String actualReturnRate) {
        _actualReturnRate = actualReturnRate;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualReturnRate",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, actualReturnRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRreserveId() {
        return _rreserveId;
    }

    @Override
    public void setRreserveId(String rreserveId) {
        _rreserveId = rreserveId;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRreserveId", String.class);

                method.invoke(_returnsMasterRemoteModel, rreserveId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReturnComplete() {
        return _returnComplete;
    }

    @Override
    public void setReturnComplete(String returnComplete) {
        _returnComplete = returnComplete;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReturnComplete",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, returnComplete);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getExpectedReturnRate() {
        return _expectedReturnRate;
    }

    @Override
    public void setExpectedReturnRate(String expectedReturnRate) {
        _expectedReturnRate = expectedReturnRate;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setExpectedReturnRate",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, expectedReturnRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPct50th() {
        return _pct50th;
    }

    @Override
    public void setPct50th(String pct50th) {
        _pct50th = pct50th;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPct50th", String.class);

                method.invoke(_returnsMasterRemoteModel, pct50th);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWithin50qrtile() {
        return _within50qrtile;
    }

    @Override
    public void setWithin50qrtile(String within50qrtile) {
        _within50qrtile = within50qrtile;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setWithin50qrtile",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, within50qrtile);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCumReturnUnits() {
        return _cumReturnUnits;
    }

    @Override
    public void setCumReturnUnits(String cumReturnUnits) {
        _cumReturnUnits = cumReturnUnits;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCumReturnUnits",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, cumReturnUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getOrigSaleMonth() {
        return _origSaleMonth;
    }

    @Override
    public void setOrigSaleMonth(Date origSaleMonth) {
        _origSaleMonth = origSaleMonth;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrigSaleMonth", Date.class);

                method.invoke(_returnsMasterRemoteModel, origSaleMonth);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDescription() {
        return _description;
    }

    @Override
    public void setDescription(String description) {
        _description = description;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDescription", String.class);

                method.invoke(_returnsMasterRemoteModel, description);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSku() {
        return _sku;
    }

    @Override
    public void setSku(String sku) {
        _sku = sku;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSku", String.class);

                method.invoke(_returnsMasterRemoteModel, sku);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUpperLimit() {
        return _upperLimit;
    }

    @Override
    public void setUpperLimit(String upperLimit) {
        _upperLimit = upperLimit;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUpperLimit", String.class);

                method.invoke(_returnsMasterRemoteModel, upperLimit);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLowerLimit() {
        return _lowerLimit;
    }

    @Override
    public void setLowerLimit(String lowerLimit) {
        _lowerLimit = lowerLimit;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLowerLimit", String.class);

                method.invoke(_returnsMasterRemoteModel, lowerLimit);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getValueAtOrigAsp() {
        return _valueAtOrigAsp;
    }

    @Override
    public void setValueAtOrigAsp(String valueAtOrigAsp) {
        _valueAtOrigAsp = valueAtOrigAsp;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setValueAtOrigAsp",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, valueAtOrigAsp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjEstimatedReturnUnits() {
        return _adjEstimatedReturnUnits;
    }

    @Override
    public void setAdjEstimatedReturnUnits(String adjEstimatedReturnUnits) {
        _adjEstimatedReturnUnits = adjEstimatedReturnUnits;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjEstimatedReturnUnits",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, adjEstimatedReturnUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPct75th() {
        return _pct75th;
    }

    @Override
    public void setPct75th(String pct75th) {
        _pct75th = pct75th;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPct75th", String.class);

                method.invoke(_returnsMasterRemoteModel, pct75th);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public boolean isRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _recordLockStatus = recordLockStatus;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_returnsMasterRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPastExpiration() {
        return _pastExpiration;
    }

    @Override
    public void setPastExpiration(String pastExpiration) {
        _pastExpiration = pastExpiration;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPastExpiration",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, pastExpiration);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBatchId() {
        return _batchId;
    }

    @Override
    public void setBatchId(String batchId) {
        _batchId = batchId;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_returnsMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMaximum() {
        return _maximum;
    }

    @Override
    public void setMaximum(String maximum) {
        _maximum = maximum;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMaximum", String.class);

                method.invoke(_returnsMasterRemoteModel, maximum);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrigSaleUnits() {
        return _origSaleUnits;
    }

    @Override
    public void setOrigSaleUnits(String origSaleUnits) {
        _origSaleUnits = origSaleUnits;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrigSaleUnits", String.class);

                method.invoke(_returnsMasterRemoteModel, origSaleUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrand() {
        return _brand;
    }

    @Override
    public void setBrand(String brand) {
        _brand = brand;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrand", String.class);

                method.invoke(_returnsMasterRemoteModel, brand);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrigSaleDollars() {
        return _origSaleDollars;
    }

    @Override
    public void setOrigSaleDollars(String origSaleDollars) {
        _origSaleDollars = origSaleDollars;

        if (_returnsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _returnsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrigSaleDollars",
                        String.class);

                method.invoke(_returnsMasterRemoteModel, origSaleDollars);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getReturnsMasterRemoteModel() {
        return _returnsMasterRemoteModel;
    }

    public void setReturnsMasterRemoteModel(
        BaseModel<?> returnsMasterRemoteModel) {
        _returnsMasterRemoteModel = returnsMasterRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _returnsMasterRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_returnsMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ReturnsMasterLocalServiceUtil.addReturnsMaster(this);
        } else {
            ReturnsMasterLocalServiceUtil.updateReturnsMaster(this);
        }
    }

    @Override
    public ReturnsMaster toEscapedModel() {
        return (ReturnsMaster) ProxyUtil.newProxyInstance(ReturnsMaster.class.getClassLoader(),
            new Class[] { ReturnsMaster.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ReturnsMasterClp clone = new ReturnsMasterClp();

        clone.setAdjValueAtOrigAsp(getAdjValueAtOrigAsp());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setFirstReturn(getFirstReturn());
        clone.setAsp(getAsp());
        clone.setMaxExpiredMonthPlusCutoff(getMaxExpiredMonthPlusCutoff());
        clone.setPosEstimatedReturnUnits(getPosEstimatedReturnUnits());
        clone.setOrigSaleMonthCutOff(getOrigSaleMonthCutOff());
        clone.setCalcUsed(getCalcUsed());
        clone.setModifiedDate(getModifiedDate());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setLastReturn(getLastReturn());
        clone.setExpectedReturnUnits(getExpectedReturnUnits());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setVersion(getVersion());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setReturnsMasterSid(getReturnsMasterSid());
        clone.setWeightedAvgMonths(getWeightedAvgMonths());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setPct25th(getPct25th());
        clone.setLoadDate(getLoadDate());
        clone.setMaxExpiredMonth(getMaxExpiredMonth());
        clone.setActualReturnRate(getActualReturnRate());
        clone.setRreserveId(getRreserveId());
        clone.setReturnComplete(getReturnComplete());
        clone.setExpectedReturnRate(getExpectedReturnRate());
        clone.setPct50th(getPct50th());
        clone.setWithin50qrtile(getWithin50qrtile());
        clone.setCumReturnUnits(getCumReturnUnits());
        clone.setOrigSaleMonth(getOrigSaleMonth());
        clone.setDescription(getDescription());
        clone.setSku(getSku());
        clone.setUpperLimit(getUpperLimit());
        clone.setLowerLimit(getLowerLimit());
        clone.setValueAtOrigAsp(getValueAtOrigAsp());
        clone.setAdjEstimatedReturnUnits(getAdjEstimatedReturnUnits());
        clone.setPct75th(getPct75th());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setPastExpiration(getPastExpiration());
        clone.setBatchId(getBatchId());
        clone.setMaximum(getMaximum());
        clone.setOrigSaleUnits(getOrigSaleUnits());
        clone.setBrand(getBrand());
        clone.setOrigSaleDollars(getOrigSaleDollars());

        return clone;
    }

    @Override
    public int compareTo(ReturnsMaster returnsMaster) {
        int primaryKey = returnsMaster.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ReturnsMasterClp)) {
            return false;
        }

        ReturnsMasterClp returnsMaster = (ReturnsMasterClp) obj;

        int primaryKey = returnsMaster.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(93);

        sb.append("{adjValueAtOrigAsp=");
        sb.append(getAdjValueAtOrigAsp());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", firstReturn=");
        sb.append(getFirstReturn());
        sb.append(", asp=");
        sb.append(getAsp());
        sb.append(", maxExpiredMonthPlusCutoff=");
        sb.append(getMaxExpiredMonthPlusCutoff());
        sb.append(", posEstimatedReturnUnits=");
        sb.append(getPosEstimatedReturnUnits());
        sb.append(", origSaleMonthCutOff=");
        sb.append(getOrigSaleMonthCutOff());
        sb.append(", calcUsed=");
        sb.append(getCalcUsed());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
        sb.append(", lastReturn=");
        sb.append(getLastReturn());
        sb.append(", expectedReturnUnits=");
        sb.append(getExpectedReturnUnits());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", returnsMasterSid=");
        sb.append(getReturnsMasterSid());
        sb.append(", weightedAvgMonths=");
        sb.append(getWeightedAvgMonths());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", pct25th=");
        sb.append(getPct25th());
        sb.append(", loadDate=");
        sb.append(getLoadDate());
        sb.append(", maxExpiredMonth=");
        sb.append(getMaxExpiredMonth());
        sb.append(", actualReturnRate=");
        sb.append(getActualReturnRate());
        sb.append(", rreserveId=");
        sb.append(getRreserveId());
        sb.append(", returnComplete=");
        sb.append(getReturnComplete());
        sb.append(", expectedReturnRate=");
        sb.append(getExpectedReturnRate());
        sb.append(", pct50th=");
        sb.append(getPct50th());
        sb.append(", within50qrtile=");
        sb.append(getWithin50qrtile());
        sb.append(", cumReturnUnits=");
        sb.append(getCumReturnUnits());
        sb.append(", origSaleMonth=");
        sb.append(getOrigSaleMonth());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", sku=");
        sb.append(getSku());
        sb.append(", upperLimit=");
        sb.append(getUpperLimit());
        sb.append(", lowerLimit=");
        sb.append(getLowerLimit());
        sb.append(", valueAtOrigAsp=");
        sb.append(getValueAtOrigAsp());
        sb.append(", adjEstimatedReturnUnits=");
        sb.append(getAdjEstimatedReturnUnits());
        sb.append(", pct75th=");
        sb.append(getPct75th());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", pastExpiration=");
        sb.append(getPastExpiration());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", maximum=");
        sb.append(getMaximum());
        sb.append(", origSaleUnits=");
        sb.append(getOrigSaleUnits());
        sb.append(", brand=");
        sb.append(getBrand());
        sb.append(", origSaleDollars=");
        sb.append(getOrigSaleDollars());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(142);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ReturnsMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>adjValueAtOrigAsp</column-name><column-value><![CDATA[");
        sb.append(getAdjValueAtOrigAsp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>firstReturn</column-name><column-value><![CDATA[");
        sb.append(getFirstReturn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>asp</column-name><column-value><![CDATA[");
        sb.append(getAsp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>maxExpiredMonthPlusCutoff</column-name><column-value><![CDATA[");
        sb.append(getMaxExpiredMonthPlusCutoff());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>posEstimatedReturnUnits</column-name><column-value><![CDATA[");
        sb.append(getPosEstimatedReturnUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>origSaleMonthCutOff</column-name><column-value><![CDATA[");
        sb.append(getOrigSaleMonthCutOff());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calcUsed</column-name><column-value><![CDATA[");
        sb.append(getCalcUsed());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBrandMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastReturn</column-name><column-value><![CDATA[");
        sb.append(getLastReturn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>expectedReturnUnits</column-name><column-value><![CDATA[");
        sb.append(getExpectedReturnUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>returnsMasterSid</column-name><column-value><![CDATA[");
        sb.append(getReturnsMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>weightedAvgMonths</column-name><column-value><![CDATA[");
        sb.append(getWeightedAvgMonths());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pct25th</column-name><column-value><![CDATA[");
        sb.append(getPct25th());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>loadDate</column-name><column-value><![CDATA[");
        sb.append(getLoadDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>maxExpiredMonth</column-name><column-value><![CDATA[");
        sb.append(getMaxExpiredMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualReturnRate</column-name><column-value><![CDATA[");
        sb.append(getActualReturnRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rreserveId</column-name><column-value><![CDATA[");
        sb.append(getRreserveId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>returnComplete</column-name><column-value><![CDATA[");
        sb.append(getReturnComplete());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>expectedReturnRate</column-name><column-value><![CDATA[");
        sb.append(getExpectedReturnRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pct50th</column-name><column-value><![CDATA[");
        sb.append(getPct50th());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>within50qrtile</column-name><column-value><![CDATA[");
        sb.append(getWithin50qrtile());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cumReturnUnits</column-name><column-value><![CDATA[");
        sb.append(getCumReturnUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>origSaleMonth</column-name><column-value><![CDATA[");
        sb.append(getOrigSaleMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sku</column-name><column-value><![CDATA[");
        sb.append(getSku());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>upperLimit</column-name><column-value><![CDATA[");
        sb.append(getUpperLimit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lowerLimit</column-name><column-value><![CDATA[");
        sb.append(getLowerLimit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>valueAtOrigAsp</column-name><column-value><![CDATA[");
        sb.append(getValueAtOrigAsp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjEstimatedReturnUnits</column-name><column-value><![CDATA[");
        sb.append(getAdjEstimatedReturnUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pct75th</column-name><column-value><![CDATA[");
        sb.append(getPct75th());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pastExpiration</column-name><column-value><![CDATA[");
        sb.append(getPastExpiration());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>maximum</column-name><column-value><![CDATA[");
        sb.append(getMaximum());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>origSaleUnits</column-name><column-value><![CDATA[");
        sb.append(getOrigSaleUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brand</column-name><column-value><![CDATA[");
        sb.append(getBrand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>origSaleDollars</column-name><column-value><![CDATA[");
        sb.append(getOrigSaleDollars());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
