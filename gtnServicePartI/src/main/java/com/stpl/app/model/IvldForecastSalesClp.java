package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldForecastSalesLocalServiceUtil;

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


public class IvldForecastSalesClp extends BaseModelImpl<IvldForecastSales>
    implements IvldForecastSales {
    private String _price;
    private String _forecastYear;
    private String _forecastDate;
    private Date _modifiedDate;
    private String _forecastValue;
    private String _forecastIntfid;
    private String _dollars;
    private String _ndc;
    private String _actualSalesPercentage;
    private String _source;
    private Date _createdDate;
    private String _createdBy;
    private String _addChgDelIndicator;
    private String _actualSalesPercentageMonth;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private String _percentageEstimate;
    private String _percentageEstimateYear;
    private String _units;
    private String _reasonForFailure;
    private String _forecastStartDate;
    private String _forecastValueType;
    private String _forecastedSalesPercentMonth;
    private String _country;
    private String _product;
    private String _batchId;
    private String _forecastVer;
    private String _forecastMonth;
    private int _ivldForecastSalesSid;
    private String _errorField;
    private String _segment;
    private String _brand;
    private String _forecastedSalesPercentage;
    private String _forecastName;
    private boolean _checkRecord;
    private BaseModel<?> _ivldForecastSalesRemoteModel;

    public IvldForecastSalesClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldForecastSales.class;
    }

    @Override
    public String getModelClassName() {
        return IvldForecastSales.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldForecastSalesSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldForecastSalesSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldForecastSalesSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("price", getPrice());
        attributes.put("forecastYear", getForecastYear());
        attributes.put("forecastDate", getForecastDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("forecastValue", getForecastValue());
        attributes.put("forecastIntfid", getForecastIntfid());
        attributes.put("dollars", getDollars());
        attributes.put("ndc", getNdc());
        attributes.put("actualSalesPercentage", getActualSalesPercentage());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("actualSalesPercentageMonth",
            getActualSalesPercentageMonth());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("percentageEstimate", getPercentageEstimate());
        attributes.put("percentageEstimateYear", getPercentageEstimateYear());
        attributes.put("units", getUnits());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("forecastStartDate", getForecastStartDate());
        attributes.put("forecastValueType", getForecastValueType());
        attributes.put("forecastedSalesPercentMonth",
            getForecastedSalesPercentMonth());
        attributes.put("country", getCountry());
        attributes.put("product", getProduct());
        attributes.put("batchId", getBatchId());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("forecastMonth", getForecastMonth());
        attributes.put("ivldForecastSalesSid", getIvldForecastSalesSid());
        attributes.put("errorField", getErrorField());
        attributes.put("segment", getSegment());
        attributes.put("brand", getBrand());
        attributes.put("forecastedSalesPercentage",
            getForecastedSalesPercentage());
        attributes.put("forecastName", getForecastName());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String price = (String) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        String forecastDate = (String) attributes.get("forecastDate");

        if (forecastDate != null) {
            setForecastDate(forecastDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String forecastValue = (String) attributes.get("forecastValue");

        if (forecastValue != null) {
            setForecastValue(forecastValue);
        }

        String forecastIntfid = (String) attributes.get("forecastIntfid");

        if (forecastIntfid != null) {
            setForecastIntfid(forecastIntfid);
        }

        String dollars = (String) attributes.get("dollars");

        if (dollars != null) {
            setDollars(dollars);
        }

        String ndc = (String) attributes.get("ndc");

        if (ndc != null) {
            setNdc(ndc);
        }

        String actualSalesPercentage = (String) attributes.get(
                "actualSalesPercentage");

        if (actualSalesPercentage != null) {
            setActualSalesPercentage(actualSalesPercentage);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String actualSalesPercentageMonth = (String) attributes.get(
                "actualSalesPercentageMonth");

        if (actualSalesPercentageMonth != null) {
            setActualSalesPercentageMonth(actualSalesPercentageMonth);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String percentageEstimate = (String) attributes.get(
                "percentageEstimate");

        if (percentageEstimate != null) {
            setPercentageEstimate(percentageEstimate);
        }

        String percentageEstimateYear = (String) attributes.get(
                "percentageEstimateYear");

        if (percentageEstimateYear != null) {
            setPercentageEstimateYear(percentageEstimateYear);
        }

        String units = (String) attributes.get("units");

        if (units != null) {
            setUnits(units);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String forecastStartDate = (String) attributes.get("forecastStartDate");

        if (forecastStartDate != null) {
            setForecastStartDate(forecastStartDate);
        }

        String forecastValueType = (String) attributes.get("forecastValueType");

        if (forecastValueType != null) {
            setForecastValueType(forecastValueType);
        }

        String forecastedSalesPercentMonth = (String) attributes.get(
                "forecastedSalesPercentMonth");

        if (forecastedSalesPercentMonth != null) {
            setForecastedSalesPercentMonth(forecastedSalesPercentMonth);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String product = (String) attributes.get("product");

        if (product != null) {
            setProduct(product);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String forecastMonth = (String) attributes.get("forecastMonth");

        if (forecastMonth != null) {
            setForecastMonth(forecastMonth);
        }

        Integer ivldForecastSalesSid = (Integer) attributes.get(
                "ivldForecastSalesSid");

        if (ivldForecastSalesSid != null) {
            setIvldForecastSalesSid(ivldForecastSalesSid);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }

        String brand = (String) attributes.get("brand");

        if (brand != null) {
            setBrand(brand);
        }

        String forecastedSalesPercentage = (String) attributes.get(
                "forecastedSalesPercentage");

        if (forecastedSalesPercentage != null) {
            setForecastedSalesPercentage(forecastedSalesPercentage);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getPrice() {
        return _price;
    }

    @Override
    public void setPrice(String price) {
        _price = price;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, price);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastYear() {
        return _forecastYear;
    }

    @Override
    public void setForecastYear(String forecastYear) {
        _forecastYear = forecastYear;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastYear", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, forecastYear);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastDate() {
        return _forecastDate;
    }

    @Override
    public void setForecastDate(String forecastDate) {
        _forecastDate = forecastDate;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastDate", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, forecastDate);
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

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldForecastSalesRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastValue() {
        return _forecastValue;
    }

    @Override
    public void setForecastValue(String forecastValue) {
        _forecastValue = forecastValue;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastValue", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, forecastValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastIntfid() {
        return _forecastIntfid;
    }

    @Override
    public void setForecastIntfid(String forecastIntfid) {
        _forecastIntfid = forecastIntfid;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastIntfid",
                        String.class);

                method.invoke(_ivldForecastSalesRemoteModel, forecastIntfid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDollars() {
        return _dollars;
    }

    @Override
    public void setDollars(String dollars) {
        _dollars = dollars;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setDollars", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, dollars);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNdc() {
        return _ndc;
    }

    @Override
    public void setNdc(String ndc) {
        _ndc = ndc;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, ndc);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getActualSalesPercentage() {
        return _actualSalesPercentage;
    }

    @Override
    public void setActualSalesPercentage(String actualSalesPercentage) {
        _actualSalesPercentage = actualSalesPercentage;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setActualSalesPercentage",
                        String.class);

                method.invoke(_ivldForecastSalesRemoteModel,
                    actualSalesPercentage);
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

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, source);
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

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldForecastSalesRemoteModel, createdDate);
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

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, createdBy);
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

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldForecastSalesRemoteModel, addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getActualSalesPercentageMonth() {
        return _actualSalesPercentageMonth;
    }

    @Override
    public void setActualSalesPercentageMonth(String actualSalesPercentageMonth) {
        _actualSalesPercentageMonth = actualSalesPercentageMonth;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setActualSalesPercentageMonth",
                        String.class);

                method.invoke(_ivldForecastSalesRemoteModel,
                    actualSalesPercentageMonth);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorCode() {
        return _errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    @Override
    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldForecastSalesRemoteModel, intfInsertedDate);
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

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    @Override
    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldForecastSalesRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPercentageEstimate() {
        return _percentageEstimate;
    }

    @Override
    public void setPercentageEstimate(String percentageEstimate) {
        _percentageEstimate = percentageEstimate;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setPercentageEstimate",
                        String.class);

                method.invoke(_ivldForecastSalesRemoteModel, percentageEstimate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPercentageEstimateYear() {
        return _percentageEstimateYear;
    }

    @Override
    public void setPercentageEstimateYear(String percentageEstimateYear) {
        _percentageEstimateYear = percentageEstimateYear;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setPercentageEstimateYear",
                        String.class);

                method.invoke(_ivldForecastSalesRemoteModel,
                    percentageEstimateYear);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUnits() {
        return _units;
    }

    @Override
    public void setUnits(String units) {
        _units = units;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setUnits", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, units);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldForecastSalesRemoteModel, reasonForFailure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastStartDate() {
        return _forecastStartDate;
    }

    @Override
    public void setForecastStartDate(String forecastStartDate) {
        _forecastStartDate = forecastStartDate;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastStartDate",
                        String.class);

                method.invoke(_ivldForecastSalesRemoteModel, forecastStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastValueType() {
        return _forecastValueType;
    }

    @Override
    public void setForecastValueType(String forecastValueType) {
        _forecastValueType = forecastValueType;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastValueType",
                        String.class);

                method.invoke(_ivldForecastSalesRemoteModel, forecastValueType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastedSalesPercentMonth() {
        return _forecastedSalesPercentMonth;
    }

    @Override
    public void setForecastedSalesPercentMonth(
        String forecastedSalesPercentMonth) {
        _forecastedSalesPercentMonth = forecastedSalesPercentMonth;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastedSalesPercentMonth",
                        String.class);

                method.invoke(_ivldForecastSalesRemoteModel,
                    forecastedSalesPercentMonth);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCountry() {
        return _country;
    }

    @Override
    public void setCountry(String country) {
        _country = country;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, country);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProduct() {
        return _product;
    }

    @Override
    public void setProduct(String product) {
        _product = product;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setProduct", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, product);
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

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastVer() {
        return _forecastVer;
    }

    @Override
    public void setForecastVer(String forecastVer) {
        _forecastVer = forecastVer;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, forecastVer);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastMonth() {
        return _forecastMonth;
    }

    @Override
    public void setForecastMonth(String forecastMonth) {
        _forecastMonth = forecastMonth;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastMonth", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, forecastMonth);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldForecastSalesSid() {
        return _ivldForecastSalesSid;
    }

    @Override
    public void setIvldForecastSalesSid(int ivldForecastSalesSid) {
        _ivldForecastSalesSid = ivldForecastSalesSid;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldForecastSalesSid",
                        int.class);

                method.invoke(_ivldForecastSalesRemoteModel,
                    ivldForecastSalesSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorField() {
        return _errorField;
    }

    @Override
    public void setErrorField(String errorField) {
        _errorField = errorField;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSegment() {
        return _segment;
    }

    @Override
    public void setSegment(String segment) {
        _segment = segment;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, segment);
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

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setBrand", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, brand);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastedSalesPercentage() {
        return _forecastedSalesPercentage;
    }

    @Override
    public void setForecastedSalesPercentage(String forecastedSalesPercentage) {
        _forecastedSalesPercentage = forecastedSalesPercentage;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastedSalesPercentage",
                        String.class);

                method.invoke(_ivldForecastSalesRemoteModel,
                    forecastedSalesPercentage);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastName() {
        return _forecastName;
    }

    @Override
    public void setForecastName(String forecastName) {
        _forecastName = forecastName;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_ivldForecastSalesRemoteModel, forecastName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;

        if (_ivldForecastSalesRemoteModel != null) {
            try {
                Class<?> clazz = _ivldForecastSalesRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldForecastSalesRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldForecastSalesRemoteModel() {
        return _ivldForecastSalesRemoteModel;
    }

    public void setIvldForecastSalesRemoteModel(
        BaseModel<?> ivldForecastSalesRemoteModel) {
        _ivldForecastSalesRemoteModel = ivldForecastSalesRemoteModel;
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

        Class<?> remoteModelClass = _ivldForecastSalesRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldForecastSalesRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldForecastSalesLocalServiceUtil.addIvldForecastSales(this);
        } else {
            IvldForecastSalesLocalServiceUtil.updateIvldForecastSales(this);
        }
    }

    @Override
    public IvldForecastSales toEscapedModel() {
        return (IvldForecastSales) ProxyUtil.newProxyInstance(IvldForecastSales.class.getClassLoader(),
            new Class[] { IvldForecastSales.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldForecastSalesClp clone = new IvldForecastSalesClp();

        clone.setPrice(getPrice());
        clone.setForecastYear(getForecastYear());
        clone.setForecastDate(getForecastDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setForecastValue(getForecastValue());
        clone.setForecastIntfid(getForecastIntfid());
        clone.setDollars(getDollars());
        clone.setNdc(getNdc());
        clone.setActualSalesPercentage(getActualSalesPercentage());
        clone.setSource(getSource());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setActualSalesPercentageMonth(getActualSalesPercentageMonth());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setPercentageEstimate(getPercentageEstimate());
        clone.setPercentageEstimateYear(getPercentageEstimateYear());
        clone.setUnits(getUnits());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setForecastStartDate(getForecastStartDate());
        clone.setForecastValueType(getForecastValueType());
        clone.setForecastedSalesPercentMonth(getForecastedSalesPercentMonth());
        clone.setCountry(getCountry());
        clone.setProduct(getProduct());
        clone.setBatchId(getBatchId());
        clone.setForecastVer(getForecastVer());
        clone.setForecastMonth(getForecastMonth());
        clone.setIvldForecastSalesSid(getIvldForecastSalesSid());
        clone.setErrorField(getErrorField());
        clone.setSegment(getSegment());
        clone.setBrand(getBrand());
        clone.setForecastedSalesPercentage(getForecastedSalesPercentage());
        clone.setForecastName(getForecastName());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldForecastSales ivldForecastSales) {
        int primaryKey = ivldForecastSales.getPrimaryKey();

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

        if (!(obj instanceof IvldForecastSalesClp)) {
            return false;
        }

        IvldForecastSalesClp ivldForecastSales = (IvldForecastSalesClp) obj;

        int primaryKey = ivldForecastSales.getPrimaryKey();

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
        StringBundler sb = new StringBundler(75);

        sb.append("{price=");
        sb.append(getPrice());
        sb.append(", forecastYear=");
        sb.append(getForecastYear());
        sb.append(", forecastDate=");
        sb.append(getForecastDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", forecastValue=");
        sb.append(getForecastValue());
        sb.append(", forecastIntfid=");
        sb.append(getForecastIntfid());
        sb.append(", dollars=");
        sb.append(getDollars());
        sb.append(", ndc=");
        sb.append(getNdc());
        sb.append(", actualSalesPercentage=");
        sb.append(getActualSalesPercentage());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", actualSalesPercentageMonth=");
        sb.append(getActualSalesPercentageMonth());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", percentageEstimate=");
        sb.append(getPercentageEstimate());
        sb.append(", percentageEstimateYear=");
        sb.append(getPercentageEstimateYear());
        sb.append(", units=");
        sb.append(getUnits());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", forecastStartDate=");
        sb.append(getForecastStartDate());
        sb.append(", forecastValueType=");
        sb.append(getForecastValueType());
        sb.append(", forecastedSalesPercentMonth=");
        sb.append(getForecastedSalesPercentMonth());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", product=");
        sb.append(getProduct());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", forecastVer=");
        sb.append(getForecastVer());
        sb.append(", forecastMonth=");
        sb.append(getForecastMonth());
        sb.append(", ivldForecastSalesSid=");
        sb.append(getIvldForecastSalesSid());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", segment=");
        sb.append(getSegment());
        sb.append(", brand=");
        sb.append(getBrand());
        sb.append(", forecastedSalesPercentage=");
        sb.append(getForecastedSalesPercentage());
        sb.append(", forecastName=");
        sb.append(getForecastName());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(115);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldForecastSales");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastYear</column-name><column-value><![CDATA[");
        sb.append(getForecastYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastDate</column-name><column-value><![CDATA[");
        sb.append(getForecastDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastValue</column-name><column-value><![CDATA[");
        sb.append(getForecastValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastIntfid</column-name><column-value><![CDATA[");
        sb.append(getForecastIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dollars</column-name><column-value><![CDATA[");
        sb.append(getDollars());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ndc</column-name><column-value><![CDATA[");
        sb.append(getNdc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualSalesPercentage</column-name><column-value><![CDATA[");
        sb.append(getActualSalesPercentage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
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
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualSalesPercentageMonth</column-name><column-value><![CDATA[");
        sb.append(getActualSalesPercentageMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>percentageEstimate</column-name><column-value><![CDATA[");
        sb.append(getPercentageEstimate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>percentageEstimateYear</column-name><column-value><![CDATA[");
        sb.append(getPercentageEstimateYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>units</column-name><column-value><![CDATA[");
        sb.append(getUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastStartDate</column-name><column-value><![CDATA[");
        sb.append(getForecastStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastValueType</column-name><column-value><![CDATA[");
        sb.append(getForecastValueType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastedSalesPercentMonth</column-name><column-value><![CDATA[");
        sb.append(getForecastedSalesPercentMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>product</column-name><column-value><![CDATA[");
        sb.append(getProduct());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastVer</column-name><column-value><![CDATA[");
        sb.append(getForecastVer());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastMonth</column-name><column-value><![CDATA[");
        sb.append(getForecastMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldForecastSalesSid</column-name><column-value><![CDATA[");
        sb.append(getIvldForecastSalesSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>segment</column-name><column-value><![CDATA[");
        sb.append(getSegment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brand</column-name><column-value><![CDATA[");
        sb.append(getBrand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastedSalesPercentage</column-name><column-value><![CDATA[");
        sb.append(getForecastedSalesPercentage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastName</column-name><column-value><![CDATA[");
        sb.append(getForecastName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
