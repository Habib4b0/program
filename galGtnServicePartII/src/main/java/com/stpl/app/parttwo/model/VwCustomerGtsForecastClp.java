package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.VwCustomerGtsForecastLocalServiceUtil;

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


public class VwCustomerGtsForecastClp extends BaseModelImpl<VwCustomerGtsForecast>
    implements VwCustomerGtsForecast {
    private double _price;
    private String _forecastYear;
    private double _deductionAmount;
    private String _deductionId;
    private Date _forecastDate;
    private String _itemId;
    private Date _modifiedDate;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private String _addChgDelIndicator;
    private String _modifiedBy;
    private double _salesAmount;
    private String _udc6;
    private String _udc5;
    private String _deductionType;
    private String _udc4;
    private double _units;
    private double _deductionRate;
    private String _udc1;
    private int _customerGtsForecastSid;
    private String _udc2;
    private String _udc3;
    private String _country;
    private String _companyId;
    private String _forecastValueType;
    private String _deductionCategory;
    private String _adjustmentCode;
    private String _deductionProgramType;
    private int _customerGtsForecastIntfId;
    private String _salesInclusion;
    private String _forecastVer;
    private String _batchId;
    private String _priceType;
    private String _forecastMonth;
    private String _deductionInclusion;
    private String _segment;
    private String _brand;
    private String _forecastName;
    private BaseModel<?> _vwCustomerGtsForecastRemoteModel;

    public VwCustomerGtsForecastClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwCustomerGtsForecast.class;
    }

    @Override
    public String getModelClassName() {
        return VwCustomerGtsForecast.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _customerGtsForecastSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCustomerGtsForecastSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _customerGtsForecastSid;
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
        attributes.put("deductionAmount", getDeductionAmount());
        attributes.put("deductionId", getDeductionId());
        attributes.put("forecastDate", getForecastDate());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("salesAmount", getSalesAmount());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("deductionType", getDeductionType());
        attributes.put("udc4", getUdc4());
        attributes.put("units", getUnits());
        attributes.put("deductionRate", getDeductionRate());
        attributes.put("udc1", getUdc1());
        attributes.put("customerGtsForecastSid", getCustomerGtsForecastSid());
        attributes.put("udc2", getUdc2());
        attributes.put("udc3", getUdc3());
        attributes.put("country", getCountry());
        attributes.put("companyId", getCompanyId());
        attributes.put("forecastValueType", getForecastValueType());
        attributes.put("deductionCategory", getDeductionCategory());
        attributes.put("adjustmentCode", getAdjustmentCode());
        attributes.put("deductionProgramType", getDeductionProgramType());
        attributes.put("customerGtsForecastIntfId",
            getCustomerGtsForecastIntfId());
        attributes.put("salesInclusion", getSalesInclusion());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("batchId", getBatchId());
        attributes.put("priceType", getPriceType());
        attributes.put("forecastMonth", getForecastMonth());
        attributes.put("deductionInclusion", getDeductionInclusion());
        attributes.put("segment", getSegment());
        attributes.put("brand", getBrand());
        attributes.put("forecastName", getForecastName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        Double deductionAmount = (Double) attributes.get("deductionAmount");

        if (deductionAmount != null) {
            setDeductionAmount(deductionAmount);
        }

        String deductionId = (String) attributes.get("deductionId");

        if (deductionId != null) {
            setDeductionId(deductionId);
        }

        Date forecastDate = (Date) attributes.get("forecastDate");

        if (forecastDate != null) {
            setForecastDate(forecastDate);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Double salesAmount = (Double) attributes.get("salesAmount");

        if (salesAmount != null) {
            setSalesAmount(salesAmount);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        Double units = (Double) attributes.get("units");

        if (units != null) {
            setUnits(units);
        }

        Double deductionRate = (Double) attributes.get("deductionRate");

        if (deductionRate != null) {
            setDeductionRate(deductionRate);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        Integer customerGtsForecastSid = (Integer) attributes.get(
                "customerGtsForecastSid");

        if (customerGtsForecastSid != null) {
            setCustomerGtsForecastSid(customerGtsForecastSid);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String forecastValueType = (String) attributes.get("forecastValueType");

        if (forecastValueType != null) {
            setForecastValueType(forecastValueType);
        }

        String deductionCategory = (String) attributes.get("deductionCategory");

        if (deductionCategory != null) {
            setDeductionCategory(deductionCategory);
        }

        String adjustmentCode = (String) attributes.get("adjustmentCode");

        if (adjustmentCode != null) {
            setAdjustmentCode(adjustmentCode);
        }

        String deductionProgramType = (String) attributes.get(
                "deductionProgramType");

        if (deductionProgramType != null) {
            setDeductionProgramType(deductionProgramType);
        }

        Integer customerGtsForecastIntfId = (Integer) attributes.get(
                "customerGtsForecastIntfId");

        if (customerGtsForecastIntfId != null) {
            setCustomerGtsForecastIntfId(customerGtsForecastIntfId);
        }

        String salesInclusion = (String) attributes.get("salesInclusion");

        if (salesInclusion != null) {
            setSalesInclusion(salesInclusion);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String priceType = (String) attributes.get("priceType");

        if (priceType != null) {
            setPriceType(priceType);
        }

        String forecastMonth = (String) attributes.get("forecastMonth");

        if (forecastMonth != null) {
            setForecastMonth(forecastMonth);
        }

        String deductionInclusion = (String) attributes.get(
                "deductionInclusion");

        if (deductionInclusion != null) {
            setDeductionInclusion(deductionInclusion);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }

        String brand = (String) attributes.get("brand");

        if (brand != null) {
            setBrand(brand);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }
    }

    @Override
    public double getPrice() {
        return _price;
    }

    @Override
    public void setPrice(double price) {
        _price = price;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", double.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, price);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastYear", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, forecastYear);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDeductionAmount() {
        return _deductionAmount;
    }

    @Override
    public void setDeductionAmount(double deductionAmount) {
        _deductionAmount = deductionAmount;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionAmount",
                        double.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, deductionAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionId() {
        return _deductionId;
    }

    @Override
    public void setDeductionId(String deductionId) {
        _deductionId = deductionId;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionId", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, deductionId);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastDate", Date.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, forecastDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemId() {
        return _itemId;
    }

    @Override
    public void setItemId(String itemId) {
        _itemId = itemId;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, itemId);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, modifiedDate);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, source);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, createdBy);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, createdDate);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel,
                    addChgDelIndicator);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getSalesAmount() {
        return _salesAmount;
    }

    @Override
    public void setSalesAmount(double salesAmount) {
        _salesAmount = salesAmount;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesAmount", double.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, salesAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc6() {
        return _udc6;
    }

    @Override
    public void setUdc6(String udc6) {
        _udc6 = udc6;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc6", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, udc6);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc5() {
        return _udc5;
    }

    @Override
    public void setUdc5(String udc5) {
        _udc5 = udc5;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc5", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, udc5);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionType() {
        return _deductionType;
    }

    @Override
    public void setDeductionType(String deductionType) {
        _deductionType = deductionType;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionType", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, deductionType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc4() {
        return _udc4;
    }

    @Override
    public void setUdc4(String udc4) {
        _udc4 = udc4;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc4", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, udc4);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUnits", double.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, units);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDeductionRate() {
        return _deductionRate;
    }

    @Override
    public void setDeductionRate(double deductionRate) {
        _deductionRate = deductionRate;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionRate", double.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, deductionRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc1() {
        return _udc1;
    }

    @Override
    public void setUdc1(String udc1) {
        _udc1 = udc1;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc1", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, udc1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCustomerGtsForecastSid() {
        return _customerGtsForecastSid;
    }

    @Override
    public void setCustomerGtsForecastSid(int customerGtsForecastSid) {
        _customerGtsForecastSid = customerGtsForecastSid;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerGtsForecastSid",
                        int.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel,
                    customerGtsForecastSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc2() {
        return _udc2;
    }

    @Override
    public void setUdc2(String udc2) {
        _udc2 = udc2;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc2", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, udc2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc3() {
        return _udc3;
    }

    @Override
    public void setUdc3(String udc3) {
        _udc3 = udc3;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc3", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, udc3);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, country);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyId() {
        return _companyId;
    }

    @Override
    public void setCompanyId(String companyId) {
        _companyId = companyId;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, companyId);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastValueType",
                        String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel,
                    forecastValueType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCategory() {
        return _deductionCategory;
    }

    @Override
    public void setDeductionCategory(String deductionCategory) {
        _deductionCategory = deductionCategory;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCategory",
                        String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel,
                    deductionCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentCode() {
        return _adjustmentCode;
    }

    @Override
    public void setAdjustmentCode(String adjustmentCode) {
        _adjustmentCode = adjustmentCode;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentCode",
                        String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, adjustmentCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionProgramType() {
        return _deductionProgramType;
    }

    @Override
    public void setDeductionProgramType(String deductionProgramType) {
        _deductionProgramType = deductionProgramType;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionProgramType",
                        String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel,
                    deductionProgramType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCustomerGtsForecastIntfId() {
        return _customerGtsForecastIntfId;
    }

    @Override
    public void setCustomerGtsForecastIntfId(int customerGtsForecastIntfId) {
        _customerGtsForecastIntfId = customerGtsForecastIntfId;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerGtsForecastIntfId",
                        int.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel,
                    customerGtsForecastIntfId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSalesInclusion() {
        return _salesInclusion;
    }

    @Override
    public void setSalesInclusion(String salesInclusion) {
        _salesInclusion = salesInclusion;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesInclusion",
                        String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, salesInclusion);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, forecastVer);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriceType() {
        return _priceType;
    }

    @Override
    public void setPriceType(String priceType) {
        _priceType = priceType;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceType", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, priceType);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastMonth", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, forecastMonth);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionInclusion() {
        return _deductionInclusion;
    }

    @Override
    public void setDeductionInclusion(String deductionInclusion) {
        _deductionInclusion = deductionInclusion;

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionInclusion",
                        String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel,
                    deductionInclusion);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, segment);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBrand", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, brand);
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

        if (_vwCustomerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _vwCustomerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_vwCustomerGtsForecastRemoteModel, forecastName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwCustomerGtsForecastRemoteModel() {
        return _vwCustomerGtsForecastRemoteModel;
    }

    public void setVwCustomerGtsForecastRemoteModel(
        BaseModel<?> vwCustomerGtsForecastRemoteModel) {
        _vwCustomerGtsForecastRemoteModel = vwCustomerGtsForecastRemoteModel;
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

        Class<?> remoteModelClass = _vwCustomerGtsForecastRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwCustomerGtsForecastRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwCustomerGtsForecastLocalServiceUtil.addVwCustomerGtsForecast(this);
        } else {
            VwCustomerGtsForecastLocalServiceUtil.updateVwCustomerGtsForecast(this);
        }
    }

    @Override
    public VwCustomerGtsForecast toEscapedModel() {
        return (VwCustomerGtsForecast) ProxyUtil.newProxyInstance(VwCustomerGtsForecast.class.getClassLoader(),
            new Class[] { VwCustomerGtsForecast.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwCustomerGtsForecastClp clone = new VwCustomerGtsForecastClp();

        clone.setPrice(getPrice());
        clone.setForecastYear(getForecastYear());
        clone.setDeductionAmount(getDeductionAmount());
        clone.setDeductionId(getDeductionId());
        clone.setForecastDate(getForecastDate());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setModifiedBy(getModifiedBy());
        clone.setSalesAmount(getSalesAmount());
        clone.setUdc6(getUdc6());
        clone.setUdc5(getUdc5());
        clone.setDeductionType(getDeductionType());
        clone.setUdc4(getUdc4());
        clone.setUnits(getUnits());
        clone.setDeductionRate(getDeductionRate());
        clone.setUdc1(getUdc1());
        clone.setCustomerGtsForecastSid(getCustomerGtsForecastSid());
        clone.setUdc2(getUdc2());
        clone.setUdc3(getUdc3());
        clone.setCountry(getCountry());
        clone.setCompanyId(getCompanyId());
        clone.setForecastValueType(getForecastValueType());
        clone.setDeductionCategory(getDeductionCategory());
        clone.setAdjustmentCode(getAdjustmentCode());
        clone.setDeductionProgramType(getDeductionProgramType());
        clone.setCustomerGtsForecastIntfId(getCustomerGtsForecastIntfId());
        clone.setSalesInclusion(getSalesInclusion());
        clone.setForecastVer(getForecastVer());
        clone.setBatchId(getBatchId());
        clone.setPriceType(getPriceType());
        clone.setForecastMonth(getForecastMonth());
        clone.setDeductionInclusion(getDeductionInclusion());
        clone.setSegment(getSegment());
        clone.setBrand(getBrand());
        clone.setForecastName(getForecastName());

        return clone;
    }

    @Override
    public int compareTo(VwCustomerGtsForecast vwCustomerGtsForecast) {
        int primaryKey = vwCustomerGtsForecast.getPrimaryKey();

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

        if (!(obj instanceof VwCustomerGtsForecastClp)) {
            return false;
        }

        VwCustomerGtsForecastClp vwCustomerGtsForecast = (VwCustomerGtsForecastClp) obj;

        int primaryKey = vwCustomerGtsForecast.getPrimaryKey();

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
        StringBundler sb = new StringBundler(79);

        sb.append("{price=");
        sb.append(getPrice());
        sb.append(", forecastYear=");
        sb.append(getForecastYear());
        sb.append(", deductionAmount=");
        sb.append(getDeductionAmount());
        sb.append(", deductionId=");
        sb.append(getDeductionId());
        sb.append(", forecastDate=");
        sb.append(getForecastDate());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", salesAmount=");
        sb.append(getSalesAmount());
        sb.append(", udc6=");
        sb.append(getUdc6());
        sb.append(", udc5=");
        sb.append(getUdc5());
        sb.append(", deductionType=");
        sb.append(getDeductionType());
        sb.append(", udc4=");
        sb.append(getUdc4());
        sb.append(", units=");
        sb.append(getUnits());
        sb.append(", deductionRate=");
        sb.append(getDeductionRate());
        sb.append(", udc1=");
        sb.append(getUdc1());
        sb.append(", customerGtsForecastSid=");
        sb.append(getCustomerGtsForecastSid());
        sb.append(", udc2=");
        sb.append(getUdc2());
        sb.append(", udc3=");
        sb.append(getUdc3());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", forecastValueType=");
        sb.append(getForecastValueType());
        sb.append(", deductionCategory=");
        sb.append(getDeductionCategory());
        sb.append(", adjustmentCode=");
        sb.append(getAdjustmentCode());
        sb.append(", deductionProgramType=");
        sb.append(getDeductionProgramType());
        sb.append(", customerGtsForecastIntfId=");
        sb.append(getCustomerGtsForecastIntfId());
        sb.append(", salesInclusion=");
        sb.append(getSalesInclusion());
        sb.append(", forecastVer=");
        sb.append(getForecastVer());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", priceType=");
        sb.append(getPriceType());
        sb.append(", forecastMonth=");
        sb.append(getForecastMonth());
        sb.append(", deductionInclusion=");
        sb.append(getDeductionInclusion());
        sb.append(", segment=");
        sb.append(getSegment());
        sb.append(", brand=");
        sb.append(getBrand());
        sb.append(", forecastName=");
        sb.append(getForecastName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(121);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.VwCustomerGtsForecast");
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
            "<column><column-name>deductionAmount</column-name><column-value><![CDATA[");
        sb.append(getDeductionAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionId</column-name><column-value><![CDATA[");
        sb.append(getDeductionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastDate</column-name><column-value><![CDATA[");
        sb.append(getForecastDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesAmount</column-name><column-value><![CDATA[");
        sb.append(getSalesAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc6</column-name><column-value><![CDATA[");
        sb.append(getUdc6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc5</column-name><column-value><![CDATA[");
        sb.append(getUdc5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionType</column-name><column-value><![CDATA[");
        sb.append(getDeductionType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc4</column-name><column-value><![CDATA[");
        sb.append(getUdc4());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>units</column-name><column-value><![CDATA[");
        sb.append(getUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionRate</column-name><column-value><![CDATA[");
        sb.append(getDeductionRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc1</column-name><column-value><![CDATA[");
        sb.append(getUdc1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerGtsForecastSid</column-name><column-value><![CDATA[");
        sb.append(getCustomerGtsForecastSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc2</column-name><column-value><![CDATA[");
        sb.append(getUdc2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc3</column-name><column-value><![CDATA[");
        sb.append(getUdc3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastValueType</column-name><column-value><![CDATA[");
        sb.append(getForecastValueType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCategory</column-name><column-value><![CDATA[");
        sb.append(getDeductionCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentCode</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionProgramType</column-name><column-value><![CDATA[");
        sb.append(getDeductionProgramType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerGtsForecastIntfId</column-name><column-value><![CDATA[");
        sb.append(getCustomerGtsForecastIntfId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesInclusion</column-name><column-value><![CDATA[");
        sb.append(getSalesInclusion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastVer</column-name><column-value><![CDATA[");
        sb.append(getForecastVer());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceType</column-name><column-value><![CDATA[");
        sb.append(getPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastMonth</column-name><column-value><![CDATA[");
        sb.append(getForecastMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionInclusion</column-name><column-value><![CDATA[");
        sb.append(getDeductionInclusion());
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
            "<column><column-name>forecastName</column-name><column-value><![CDATA[");
        sb.append(getForecastName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
