package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;

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


public class ForecastingMasterClp extends BaseModelImpl<ForecastingMaster>
    implements ForecastingMaster {
    private String _forecastValueType;
    private int _modifiedBy;
    private Date _createdDate;
    private String _percentageEstimate;
    private int _actualSalesPercentageMonth;
    private String _ndc;
    private String _batchId;
    private String _forecastVer;
    private String _country;
    private String _product;
    private Date _forecastStartDate;
    private String _forecastedSalesPercentage;
    private double _units;
    private double _dollars;
    private String _forecastMonth;
    private int _createdBy;
    private String _segment;
    private double _price;
    private String _actualSalesPercentage;
    private String _forecastYear;
    private String _forecastName;
    private Date _forecastDate;
    private Date _modifiedDate;
    private String _brand;
    private String _forecastValue;
    private int _percentageEstimateYear;
    private boolean _recordLockStatus;
    private String _source;
    private int _forecastMasterSid;
    private int _forecastedSalesPercentMonth;
    private String _inboundStatus;
    private BaseModel<?> _forecastingMasterRemoteModel;

    public ForecastingMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ForecastingMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ForecastingMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _forecastMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setForecastMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _forecastMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("forecastValueType", getForecastValueType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("percentageEstimate", getPercentageEstimate());
        attributes.put("actualSalesPercentageMonth",
            getActualSalesPercentageMonth());
        attributes.put("ndc", getNdc());
        attributes.put("batchId", getBatchId());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("country", getCountry());
        attributes.put("product", getProduct());
        attributes.put("forecastStartDate", getForecastStartDate());
        attributes.put("forecastedSalesPercentage",
            getForecastedSalesPercentage());
        attributes.put("units", getUnits());
        attributes.put("dollars", getDollars());
        attributes.put("forecastMonth", getForecastMonth());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("segment", getSegment());
        attributes.put("price", getPrice());
        attributes.put("actualSalesPercentage", getActualSalesPercentage());
        attributes.put("forecastYear", getForecastYear());
        attributes.put("forecastName", getForecastName());
        attributes.put("forecastDate", getForecastDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("brand", getBrand());
        attributes.put("forecastValue", getForecastValue());
        attributes.put("percentageEstimateYear", getPercentageEstimateYear());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("forecastMasterSid", getForecastMasterSid());
        attributes.put("forecastedSalesPercentMonth",
            getForecastedSalesPercentMonth());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String forecastValueType = (String) attributes.get("forecastValueType");

        if (forecastValueType != null) {
            setForecastValueType(forecastValueType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String percentageEstimate = (String) attributes.get(
                "percentageEstimate");

        if (percentageEstimate != null) {
            setPercentageEstimate(percentageEstimate);
        }

        Integer actualSalesPercentageMonth = (Integer) attributes.get(
                "actualSalesPercentageMonth");

        if (actualSalesPercentageMonth != null) {
            setActualSalesPercentageMonth(actualSalesPercentageMonth);
        }

        String ndc = (String) attributes.get("ndc");

        if (ndc != null) {
            setNdc(ndc);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String product = (String) attributes.get("product");

        if (product != null) {
            setProduct(product);
        }

        Date forecastStartDate = (Date) attributes.get("forecastStartDate");

        if (forecastStartDate != null) {
            setForecastStartDate(forecastStartDate);
        }

        String forecastedSalesPercentage = (String) attributes.get(
                "forecastedSalesPercentage");

        if (forecastedSalesPercentage != null) {
            setForecastedSalesPercentage(forecastedSalesPercentage);
        }

        Double units = (Double) attributes.get("units");

        if (units != null) {
            setUnits(units);
        }

        Double dollars = (Double) attributes.get("dollars");

        if (dollars != null) {
            setDollars(dollars);
        }

        String forecastMonth = (String) attributes.get("forecastMonth");

        if (forecastMonth != null) {
            setForecastMonth(forecastMonth);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }

        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        String actualSalesPercentage = (String) attributes.get(
                "actualSalesPercentage");

        if (actualSalesPercentage != null) {
            setActualSalesPercentage(actualSalesPercentage);
        }

        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Date forecastDate = (Date) attributes.get("forecastDate");

        if (forecastDate != null) {
            setForecastDate(forecastDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String brand = (String) attributes.get("brand");

        if (brand != null) {
            setBrand(brand);
        }

        String forecastValue = (String) attributes.get("forecastValue");

        if (forecastValue != null) {
            setForecastValue(forecastValue);
        }

        Integer percentageEstimateYear = (Integer) attributes.get(
                "percentageEstimateYear");

        if (percentageEstimateYear != null) {
            setPercentageEstimateYear(percentageEstimateYear);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer forecastMasterSid = (Integer) attributes.get(
                "forecastMasterSid");

        if (forecastMasterSid != null) {
            setForecastMasterSid(forecastMasterSid);
        }

        Integer forecastedSalesPercentMonth = (Integer) attributes.get(
                "forecastedSalesPercentMonth");

        if (forecastedSalesPercentMonth != null) {
            setForecastedSalesPercentMonth(forecastedSalesPercentMonth);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public String getForecastValueType() {
        return _forecastValueType;
    }

    @Override
    public void setForecastValueType(String forecastValueType) {
        _forecastValueType = forecastValueType;

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastValueType",
                        String.class);

                method.invoke(_forecastingMasterRemoteModel, forecastValueType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_forecastingMasterRemoteModel, modifiedBy);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_forecastingMasterRemoteModel, createdDate);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPercentageEstimate",
                        String.class);

                method.invoke(_forecastingMasterRemoteModel, percentageEstimate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getActualSalesPercentageMonth() {
        return _actualSalesPercentageMonth;
    }

    @Override
    public void setActualSalesPercentageMonth(int actualSalesPercentageMonth) {
        _actualSalesPercentageMonth = actualSalesPercentageMonth;

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualSalesPercentageMonth",
                        int.class);

                method.invoke(_forecastingMasterRemoteModel,
                    actualSalesPercentageMonth);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc", String.class);

                method.invoke(_forecastingMasterRemoteModel, ndc);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_forecastingMasterRemoteModel, batchId);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_forecastingMasterRemoteModel, forecastVer);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_forecastingMasterRemoteModel, country);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProduct", String.class);

                method.invoke(_forecastingMasterRemoteModel, product);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getForecastStartDate() {
        return _forecastStartDate;
    }

    @Override
    public void setForecastStartDate(Date forecastStartDate) {
        _forecastStartDate = forecastStartDate;

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastStartDate",
                        Date.class);

                method.invoke(_forecastingMasterRemoteModel, forecastStartDate);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastedSalesPercentage",
                        String.class);

                method.invoke(_forecastingMasterRemoteModel,
                    forecastedSalesPercentage);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getUnits() {
        return _units;
    }

    @Override
    public void setUnits(double units) {
        _units = units;

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUnits", double.class);

                method.invoke(_forecastingMasterRemoteModel, units);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDollars() {
        return _dollars;
    }

    @Override
    public void setDollars(double dollars) {
        _dollars = dollars;

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDollars", double.class);

                method.invoke(_forecastingMasterRemoteModel, dollars);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastMonth", String.class);

                method.invoke(_forecastingMasterRemoteModel, forecastMonth);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_forecastingMasterRemoteModel, createdBy);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_forecastingMasterRemoteModel, segment);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPrice() {
        return _price;
    }

    @Override
    public void setPrice(double price) {
        _price = price;

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", double.class);

                method.invoke(_forecastingMasterRemoteModel, price);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualSalesPercentage",
                        String.class);

                method.invoke(_forecastingMasterRemoteModel,
                    actualSalesPercentage);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastYear", String.class);

                method.invoke(_forecastingMasterRemoteModel, forecastYear);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_forecastingMasterRemoteModel, forecastName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getForecastDate() {
        return _forecastDate;
    }

    @Override
    public void setForecastDate(Date forecastDate) {
        _forecastDate = forecastDate;

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastDate", Date.class);

                method.invoke(_forecastingMasterRemoteModel, forecastDate);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_forecastingMasterRemoteModel, modifiedDate);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrand", String.class);

                method.invoke(_forecastingMasterRemoteModel, brand);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastValue", String.class);

                method.invoke(_forecastingMasterRemoteModel, forecastValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPercentageEstimateYear() {
        return _percentageEstimateYear;
    }

    @Override
    public void setPercentageEstimateYear(int percentageEstimateYear) {
        _percentageEstimateYear = percentageEstimateYear;

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPercentageEstimateYear",
                        int.class);

                method.invoke(_forecastingMasterRemoteModel,
                    percentageEstimateYear);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_forecastingMasterRemoteModel, recordLockStatus);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_forecastingMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getForecastMasterSid() {
        return _forecastMasterSid;
    }

    @Override
    public void setForecastMasterSid(int forecastMasterSid) {
        _forecastMasterSid = forecastMasterSid;

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastMasterSid",
                        int.class);

                method.invoke(_forecastingMasterRemoteModel, forecastMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getForecastedSalesPercentMonth() {
        return _forecastedSalesPercentMonth;
    }

    @Override
    public void setForecastedSalesPercentMonth(int forecastedSalesPercentMonth) {
        _forecastedSalesPercentMonth = forecastedSalesPercentMonth;

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastedSalesPercentMonth",
                        int.class);

                method.invoke(_forecastingMasterRemoteModel,
                    forecastedSalesPercentMonth);
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

        if (_forecastingMasterRemoteModel != null) {
            try {
                Class<?> clazz = _forecastingMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_forecastingMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getForecastingMasterRemoteModel() {
        return _forecastingMasterRemoteModel;
    }

    public void setForecastingMasterRemoteModel(
        BaseModel<?> forecastingMasterRemoteModel) {
        _forecastingMasterRemoteModel = forecastingMasterRemoteModel;
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

        Class<?> remoteModelClass = _forecastingMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_forecastingMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ForecastingMasterLocalServiceUtil.addForecastingMaster(this);
        } else {
            ForecastingMasterLocalServiceUtil.updateForecastingMaster(this);
        }
    }

    @Override
    public ForecastingMaster toEscapedModel() {
        return (ForecastingMaster) ProxyUtil.newProxyInstance(ForecastingMaster.class.getClassLoader(),
            new Class[] { ForecastingMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ForecastingMasterClp clone = new ForecastingMasterClp();

        clone.setForecastValueType(getForecastValueType());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setPercentageEstimate(getPercentageEstimate());
        clone.setActualSalesPercentageMonth(getActualSalesPercentageMonth());
        clone.setNdc(getNdc());
        clone.setBatchId(getBatchId());
        clone.setForecastVer(getForecastVer());
        clone.setCountry(getCountry());
        clone.setProduct(getProduct());
        clone.setForecastStartDate(getForecastStartDate());
        clone.setForecastedSalesPercentage(getForecastedSalesPercentage());
        clone.setUnits(getUnits());
        clone.setDollars(getDollars());
        clone.setForecastMonth(getForecastMonth());
        clone.setCreatedBy(getCreatedBy());
        clone.setSegment(getSegment());
        clone.setPrice(getPrice());
        clone.setActualSalesPercentage(getActualSalesPercentage());
        clone.setForecastYear(getForecastYear());
        clone.setForecastName(getForecastName());
        clone.setForecastDate(getForecastDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setBrand(getBrand());
        clone.setForecastValue(getForecastValue());
        clone.setPercentageEstimateYear(getPercentageEstimateYear());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setSource(getSource());
        clone.setForecastMasterSid(getForecastMasterSid());
        clone.setForecastedSalesPercentMonth(getForecastedSalesPercentMonth());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(ForecastingMaster forecastingMaster) {
        int primaryKey = forecastingMaster.getPrimaryKey();

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

        if (!(obj instanceof ForecastingMasterClp)) {
            return false;
        }

        ForecastingMasterClp forecastingMaster = (ForecastingMasterClp) obj;

        int primaryKey = forecastingMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(63);

        sb.append("{forecastValueType=");
        sb.append(getForecastValueType());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", percentageEstimate=");
        sb.append(getPercentageEstimate());
        sb.append(", actualSalesPercentageMonth=");
        sb.append(getActualSalesPercentageMonth());
        sb.append(", ndc=");
        sb.append(getNdc());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", forecastVer=");
        sb.append(getForecastVer());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", product=");
        sb.append(getProduct());
        sb.append(", forecastStartDate=");
        sb.append(getForecastStartDate());
        sb.append(", forecastedSalesPercentage=");
        sb.append(getForecastedSalesPercentage());
        sb.append(", units=");
        sb.append(getUnits());
        sb.append(", dollars=");
        sb.append(getDollars());
        sb.append(", forecastMonth=");
        sb.append(getForecastMonth());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", segment=");
        sb.append(getSegment());
        sb.append(", price=");
        sb.append(getPrice());
        sb.append(", actualSalesPercentage=");
        sb.append(getActualSalesPercentage());
        sb.append(", forecastYear=");
        sb.append(getForecastYear());
        sb.append(", forecastName=");
        sb.append(getForecastName());
        sb.append(", forecastDate=");
        sb.append(getForecastDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", brand=");
        sb.append(getBrand());
        sb.append(", forecastValue=");
        sb.append(getForecastValue());
        sb.append(", percentageEstimateYear=");
        sb.append(getPercentageEstimateYear());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", forecastMasterSid=");
        sb.append(getForecastMasterSid());
        sb.append(", forecastedSalesPercentMonth=");
        sb.append(getForecastedSalesPercentMonth());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(97);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ForecastingMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>forecastValueType</column-name><column-value><![CDATA[");
        sb.append(getForecastValueType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>percentageEstimate</column-name><column-value><![CDATA[");
        sb.append(getPercentageEstimate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualSalesPercentageMonth</column-name><column-value><![CDATA[");
        sb.append(getActualSalesPercentageMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ndc</column-name><column-value><![CDATA[");
        sb.append(getNdc());
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
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>product</column-name><column-value><![CDATA[");
        sb.append(getProduct());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastStartDate</column-name><column-value><![CDATA[");
        sb.append(getForecastStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastedSalesPercentage</column-name><column-value><![CDATA[");
        sb.append(getForecastedSalesPercentage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>units</column-name><column-value><![CDATA[");
        sb.append(getUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dollars</column-name><column-value><![CDATA[");
        sb.append(getDollars());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastMonth</column-name><column-value><![CDATA[");
        sb.append(getForecastMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>segment</column-name><column-value><![CDATA[");
        sb.append(getSegment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualSalesPercentage</column-name><column-value><![CDATA[");
        sb.append(getActualSalesPercentage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastYear</column-name><column-value><![CDATA[");
        sb.append(getForecastYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastName</column-name><column-value><![CDATA[");
        sb.append(getForecastName());
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
            "<column><column-name>brand</column-name><column-value><![CDATA[");
        sb.append(getBrand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastValue</column-name><column-value><![CDATA[");
        sb.append(getForecastValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>percentageEstimateYear</column-name><column-value><![CDATA[");
        sb.append(getPercentageEstimateYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastMasterSid</column-name><column-value><![CDATA[");
        sb.append(getForecastMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastedSalesPercentMonth</column-name><column-value><![CDATA[");
        sb.append(getForecastedSalesPercentMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
