package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.CustomerGtsForecastLocalServiceUtil;

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


public class CustomerGtsForecastClp extends BaseModelImpl<CustomerGtsForecast>
    implements CustomerGtsForecast {
    private String _price;
    private int _itemMasterSid;
    private String _forecastYear;
    private String _deductionAmount;
    private String _deductionId;
    private Date _forecastDate;
    private String _itemId;
    private Date _modifiedDate;
    private int _brandMasterSid;
    private String _source;
    private Date _createdDate;
    private String _createdBy;
    private String _addChgDelIndicator;
    private String _inboundStatus;
    private String _modifiedBy;
    private String _salesAmount;
    private String _deductionType;
    private int _companyMasterSid;
    private String _units;
    private String _deductionRate;
    private int _customerGtsForecastSid;
    private String _country;
    private String _companyId;
    private String _forecastValueType;
    private String _deductionCategory;
    private String _adjustmentCode;
    private String _deductionProgramType;
    private boolean _recordLockStatus;
    private String _salesInclusion;
    private String _forecastVer;
    private String _batchId;
    private String _priceType;
    private String _forecastMonth;
    private String _deductionInclusion;
    private String _segment;
    private String _brand;
    private String _forecastName;
    private BaseModel<?> _customerGtsForecastRemoteModel;

    public CustomerGtsForecastClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CustomerGtsForecast.class;
    }

    @Override
    public String getModelClassName() {
        return CustomerGtsForecast.class.getName();
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
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("forecastYear", getForecastYear());
        attributes.put("deductionAmount", getDeductionAmount());
        attributes.put("deductionId", getDeductionId());
        attributes.put("forecastDate", getForecastDate());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("salesAmount", getSalesAmount());
        attributes.put("deductionType", getDeductionType());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("units", getUnits());
        attributes.put("deductionRate", getDeductionRate());
        attributes.put("customerGtsForecastSid", getCustomerGtsForecastSid());
        attributes.put("country", getCountry());
        attributes.put("companyId", getCompanyId());
        attributes.put("forecastValueType", getForecastValueType());
        attributes.put("deductionCategory", getDeductionCategory());
        attributes.put("adjustmentCode", getAdjustmentCode());
        attributes.put("deductionProgramType", getDeductionProgramType());
        attributes.put("recordLockStatus", getRecordLockStatus());
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
        String price = (String) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        String deductionAmount = (String) attributes.get("deductionAmount");

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

        Integer brandMasterSid = (Integer) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
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

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String salesAmount = (String) attributes.get("salesAmount");

        if (salesAmount != null) {
            setSalesAmount(salesAmount);
        }

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        String units = (String) attributes.get("units");

        if (units != null) {
            setUnits(units);
        }

        String deductionRate = (String) attributes.get("deductionRate");

        if (deductionRate != null) {
            setDeductionRate(deductionRate);
        }

        Integer customerGtsForecastSid = (Integer) attributes.get(
                "customerGtsForecastSid");

        if (customerGtsForecastSid != null) {
            setCustomerGtsForecastSid(customerGtsForecastSid);
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

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
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
    public String getPrice() {
        return _price;
    }

    @Override
    public void setPrice(String price) {
        _price = price;

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", String.class);

                method.invoke(_customerGtsForecastRemoteModel, price);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_customerGtsForecastRemoteModel, itemMasterSid);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastYear", String.class);

                method.invoke(_customerGtsForecastRemoteModel, forecastYear);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionAmount() {
        return _deductionAmount;
    }

    @Override
    public void setDeductionAmount(String deductionAmount) {
        _deductionAmount = deductionAmount;

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionAmount",
                        String.class);

                method.invoke(_customerGtsForecastRemoteModel, deductionAmount);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionId", String.class);

                method.invoke(_customerGtsForecastRemoteModel, deductionId);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastDate", Date.class);

                method.invoke(_customerGtsForecastRemoteModel, forecastDate);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_customerGtsForecastRemoteModel, itemId);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_customerGtsForecastRemoteModel, modifiedDate);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid", int.class);

                method.invoke(_customerGtsForecastRemoteModel, brandMasterSid);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_customerGtsForecastRemoteModel, source);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_customerGtsForecastRemoteModel, createdDate);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_customerGtsForecastRemoteModel, createdBy);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_customerGtsForecastRemoteModel,
                    addChgDelIndicator);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_customerGtsForecastRemoteModel, inboundStatus);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_customerGtsForecastRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSalesAmount() {
        return _salesAmount;
    }

    @Override
    public void setSalesAmount(String salesAmount) {
        _salesAmount = salesAmount;

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesAmount", String.class);

                method.invoke(_customerGtsForecastRemoteModel, salesAmount);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionType", String.class);

                method.invoke(_customerGtsForecastRemoteModel, deductionType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_customerGtsForecastRemoteModel, companyMasterSid);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUnits", String.class);

                method.invoke(_customerGtsForecastRemoteModel, units);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionRate() {
        return _deductionRate;
    }

    @Override
    public void setDeductionRate(String deductionRate) {
        _deductionRate = deductionRate;

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionRate", String.class);

                method.invoke(_customerGtsForecastRemoteModel, deductionRate);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerGtsForecastSid",
                        int.class);

                method.invoke(_customerGtsForecastRemoteModel,
                    customerGtsForecastSid);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_customerGtsForecastRemoteModel, country);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_customerGtsForecastRemoteModel, companyId);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastValueType",
                        String.class);

                method.invoke(_customerGtsForecastRemoteModel, forecastValueType);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCategory",
                        String.class);

                method.invoke(_customerGtsForecastRemoteModel, deductionCategory);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentCode",
                        String.class);

                method.invoke(_customerGtsForecastRemoteModel, adjustmentCode);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionProgramType",
                        String.class);

                method.invoke(_customerGtsForecastRemoteModel,
                    deductionProgramType);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_customerGtsForecastRemoteModel, recordLockStatus);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesInclusion",
                        String.class);

                method.invoke(_customerGtsForecastRemoteModel, salesInclusion);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_customerGtsForecastRemoteModel, forecastVer);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_customerGtsForecastRemoteModel, batchId);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceType", String.class);

                method.invoke(_customerGtsForecastRemoteModel, priceType);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastMonth", String.class);

                method.invoke(_customerGtsForecastRemoteModel, forecastMonth);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionInclusion",
                        String.class);

                method.invoke(_customerGtsForecastRemoteModel,
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_customerGtsForecastRemoteModel, segment);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBrand", String.class);

                method.invoke(_customerGtsForecastRemoteModel, brand);
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

        if (_customerGtsForecastRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_customerGtsForecastRemoteModel, forecastName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCustomerGtsForecastRemoteModel() {
        return _customerGtsForecastRemoteModel;
    }

    public void setCustomerGtsForecastRemoteModel(
        BaseModel<?> customerGtsForecastRemoteModel) {
        _customerGtsForecastRemoteModel = customerGtsForecastRemoteModel;
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

        Class<?> remoteModelClass = _customerGtsForecastRemoteModel.getClass();

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

        Object returnValue = method.invoke(_customerGtsForecastRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CustomerGtsForecastLocalServiceUtil.addCustomerGtsForecast(this);
        } else {
            CustomerGtsForecastLocalServiceUtil.updateCustomerGtsForecast(this);
        }
    }

    @Override
    public CustomerGtsForecast toEscapedModel() {
        return (CustomerGtsForecast) ProxyUtil.newProxyInstance(CustomerGtsForecast.class.getClassLoader(),
            new Class[] { CustomerGtsForecast.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CustomerGtsForecastClp clone = new CustomerGtsForecastClp();

        clone.setPrice(getPrice());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setForecastYear(getForecastYear());
        clone.setDeductionAmount(getDeductionAmount());
        clone.setDeductionId(getDeductionId());
        clone.setForecastDate(getForecastDate());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setSource(getSource());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setInboundStatus(getInboundStatus());
        clone.setModifiedBy(getModifiedBy());
        clone.setSalesAmount(getSalesAmount());
        clone.setDeductionType(getDeductionType());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setUnits(getUnits());
        clone.setDeductionRate(getDeductionRate());
        clone.setCustomerGtsForecastSid(getCustomerGtsForecastSid());
        clone.setCountry(getCountry());
        clone.setCompanyId(getCompanyId());
        clone.setForecastValueType(getForecastValueType());
        clone.setDeductionCategory(getDeductionCategory());
        clone.setAdjustmentCode(getAdjustmentCode());
        clone.setDeductionProgramType(getDeductionProgramType());
        clone.setRecordLockStatus(getRecordLockStatus());
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
    public int compareTo(CustomerGtsForecast customerGtsForecast) {
        int primaryKey = customerGtsForecast.getPrimaryKey();

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

        if (!(obj instanceof CustomerGtsForecastClp)) {
            return false;
        }

        CustomerGtsForecastClp customerGtsForecast = (CustomerGtsForecastClp) obj;

        int primaryKey = customerGtsForecast.getPrimaryKey();

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
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
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
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", salesAmount=");
        sb.append(getSalesAmount());
        sb.append(", deductionType=");
        sb.append(getDeductionType());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", units=");
        sb.append(getUnits());
        sb.append(", deductionRate=");
        sb.append(getDeductionRate());
        sb.append(", customerGtsForecastSid=");
        sb.append(getCustomerGtsForecastSid());
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
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
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
        StringBundler sb = new StringBundler(115);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CustomerGtsForecast");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
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
            "<column><column-name>brandMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBrandMasterSid());
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
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
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
            "<column><column-name>deductionType</column-name><column-value><![CDATA[");
        sb.append(getDeductionType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
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
            "<column><column-name>customerGtsForecastSid</column-name><column-value><![CDATA[");
        sb.append(getCustomerGtsForecastSid());
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
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
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
