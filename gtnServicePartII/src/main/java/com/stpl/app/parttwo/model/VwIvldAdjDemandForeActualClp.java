package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.VwIvldAdjDemandForeActualLocalServiceUtil;

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


public class VwIvldAdjDemandForeActualClp extends BaseModelImpl<VwIvldAdjDemandForeActual>
    implements VwIvldAdjDemandForeActual {
    private String _forecastVersion;
    private String _grossUnits;
    private String _businessUnitNo;
    private String _year;
    private String _itemId;
    private String _brandName;
    private Date _modifiedDate;
    private String _organizationKey;
    private Date _createdDate;
    private String _createdBy;
    private String _source;
    private String _marketShareRatio;
    private String _businessUnitName;
    private String _addChgDelIndicator;
    private String _itemIdentifier;
    private String _errorCode;
    private String _modifiedBy;
    private String _marketShareUnits;
    private String _month;
    private String _inventoryUnitChange;
    private String _reprocessedFlag;
    private String _uncapturedUnitsRatio;
    private String _reasonForFailure;
    private String _adjustedDemandForecastIntfId;
    private String _country;
    private String _forecastType;
    private String _totalAdjustedDemandUnits;
    private String _brandId;
    private String _isForecast;
    private String _totalAdjustedDemandAmount;
    private String _uncapturedUnits;
    private String _grossPrice;
    private String _grossAmount;
    private String _itemIdentifierCodeQualifier;
    private String _batchId;
    private String _itemName;
    private String _errorField;
    private String _netSalesPrice;
    private String _netSalesAmount;
    private String _segment;
    private String _forecastName;
    private int _ivldAdjustedDemandForecastSid;
    private String _marketSizeUnits;
    private boolean _checkRecord;
    private BaseModel<?> _vwIvldAdjDemandForeActualRemoteModel;

    public VwIvldAdjDemandForeActualClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwIvldAdjDemandForeActual.class;
    }

    @Override
    public String getModelClassName() {
        return VwIvldAdjDemandForeActual.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldAdjustedDemandForecastSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldAdjustedDemandForecastSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldAdjustedDemandForecastSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("forecastVersion", getForecastVersion());
        attributes.put("grossUnits", getGrossUnits());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("brandName", getBrandName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("marketShareRatio", getMarketShareRatio());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("errorCode", getErrorCode());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("marketShareUnits", getMarketShareUnits());
        attributes.put("month", getMonth());
        attributes.put("inventoryUnitChange", getInventoryUnitChange());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("adjustedDemandForecastIntfId",
            getAdjustedDemandForecastIntfId());
        attributes.put("country", getCountry());
        attributes.put("forecastType", getForecastType());
        attributes.put("totalAdjustedDemandUnits", getTotalAdjustedDemandUnits());
        attributes.put("brandId", getBrandId());
        attributes.put("isForecast", getIsForecast());
        attributes.put("totalAdjustedDemandAmount",
            getTotalAdjustedDemandAmount());
        attributes.put("uncapturedUnits", getUncapturedUnits());
        attributes.put("grossPrice", getGrossPrice());
        attributes.put("grossAmount", getGrossAmount());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("batchId", getBatchId());
        attributes.put("itemName", getItemName());
        attributes.put("errorField", getErrorField());
        attributes.put("netSalesPrice", getNetSalesPrice());
        attributes.put("netSalesAmount", getNetSalesAmount());
        attributes.put("segment", getSegment());
        attributes.put("forecastName", getForecastName());
        attributes.put("ivldAdjustedDemandForecastSid",
            getIvldAdjustedDemandForecastSid());
        attributes.put("marketSizeUnits", getMarketSizeUnits());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String forecastVersion = (String) attributes.get("forecastVersion");

        if (forecastVersion != null) {
            setForecastVersion(forecastVersion);
        }

        String grossUnits = (String) attributes.get("grossUnits");

        if (grossUnits != null) {
            setGrossUnits(grossUnits);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
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

        String marketShareRatio = (String) attributes.get("marketShareRatio");

        if (marketShareRatio != null) {
            setMarketShareRatio(marketShareRatio);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String marketShareUnits = (String) attributes.get("marketShareUnits");

        if (marketShareUnits != null) {
            setMarketShareUnits(marketShareUnits);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        String inventoryUnitChange = (String) attributes.get(
                "inventoryUnitChange");

        if (inventoryUnitChange != null) {
            setInventoryUnitChange(inventoryUnitChange);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String uncapturedUnitsRatio = (String) attributes.get(
                "uncapturedUnitsRatio");

        if (uncapturedUnitsRatio != null) {
            setUncapturedUnitsRatio(uncapturedUnitsRatio);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String adjustedDemandForecastIntfId = (String) attributes.get(
                "adjustedDemandForecastIntfId");

        if (adjustedDemandForecastIntfId != null) {
            setAdjustedDemandForecastIntfId(adjustedDemandForecastIntfId);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String forecastType = (String) attributes.get("forecastType");

        if (forecastType != null) {
            setForecastType(forecastType);
        }

        String totalAdjustedDemandUnits = (String) attributes.get(
                "totalAdjustedDemandUnits");

        if (totalAdjustedDemandUnits != null) {
            setTotalAdjustedDemandUnits(totalAdjustedDemandUnits);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String isForecast = (String) attributes.get("isForecast");

        if (isForecast != null) {
            setIsForecast(isForecast);
        }

        String totalAdjustedDemandAmount = (String) attributes.get(
                "totalAdjustedDemandAmount");

        if (totalAdjustedDemandAmount != null) {
            setTotalAdjustedDemandAmount(totalAdjustedDemandAmount);
        }

        String uncapturedUnits = (String) attributes.get("uncapturedUnits");

        if (uncapturedUnits != null) {
            setUncapturedUnits(uncapturedUnits);
        }

        String grossPrice = (String) attributes.get("grossPrice");

        if (grossPrice != null) {
            setGrossPrice(grossPrice);
        }

        String grossAmount = (String) attributes.get("grossAmount");

        if (grossAmount != null) {
            setGrossAmount(grossAmount);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String netSalesPrice = (String) attributes.get("netSalesPrice");

        if (netSalesPrice != null) {
            setNetSalesPrice(netSalesPrice);
        }

        String netSalesAmount = (String) attributes.get("netSalesAmount");

        if (netSalesAmount != null) {
            setNetSalesAmount(netSalesAmount);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Integer ivldAdjustedDemandForecastSid = (Integer) attributes.get(
                "ivldAdjustedDemandForecastSid");

        if (ivldAdjustedDemandForecastSid != null) {
            setIvldAdjustedDemandForecastSid(ivldAdjustedDemandForecastSid);
        }

        String marketSizeUnits = (String) attributes.get("marketSizeUnits");

        if (marketSizeUnits != null) {
            setMarketSizeUnits(marketSizeUnits);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getForecastVersion() {
        return _forecastVersion;
    }

    @Override
    public void setForecastVersion(String forecastVersion) {
        _forecastVersion = forecastVersion;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVersion",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    forecastVersion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGrossUnits() {
        return _grossUnits;
    }

    @Override
    public void setGrossUnits(String grossUnits) {
        _grossUnits = grossUnits;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossUnits", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, grossUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessUnitNo() {
        return _businessUnitNo;
    }

    @Override
    public void setBusinessUnitNo(String businessUnitNo) {
        _businessUnitNo = businessUnitNo;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitNo",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    businessUnitNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getYear() {
        return _year;
    }

    @Override
    public void setYear(String year) {
        _year = year;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, year);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandName() {
        return _brandName;
    }

    @Override
    public void setBrandName(String brandName) {
        _brandName = brandName;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, brandName);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrganizationKey() {
        return _organizationKey;
    }

    @Override
    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    organizationKey);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, createdDate);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, createdBy);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMarketShareRatio() {
        return _marketShareRatio;
    }

    @Override
    public void setMarketShareRatio(String marketShareRatio) {
        _marketShareRatio = marketShareRatio;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareRatio",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    marketShareRatio);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessUnitName() {
        return _businessUnitName;
    }

    @Override
    public void setBusinessUnitName(String businessUnitName) {
        _businessUnitName = businessUnitName;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitName",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    businessUnitName);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdentifier() {
        return _itemIdentifier;
    }

    @Override
    public void setItemIdentifier(String itemIdentifier) {
        _itemIdentifier = itemIdentifier;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifier",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    itemIdentifier);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, errorCode);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMarketShareUnits() {
        return _marketShareUnits;
    }

    @Override
    public void setMarketShareUnits(String marketShareUnits) {
        _marketShareUnits = marketShareUnits;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareUnits",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    marketShareUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMonth() {
        return _month;
    }

    @Override
    public void setMonth(String month) {
        _month = month;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, month);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInventoryUnitChange() {
        return _inventoryUnitChange;
    }

    @Override
    public void setInventoryUnitChange(String inventoryUnitChange) {
        _inventoryUnitChange = inventoryUnitChange;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryUnitChange",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    inventoryUnitChange);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUncapturedUnitsRatio() {
        return _uncapturedUnitsRatio;
    }

    @Override
    public void setUncapturedUnitsRatio(String uncapturedUnitsRatio) {
        _uncapturedUnitsRatio = uncapturedUnitsRatio;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnitsRatio",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    uncapturedUnitsRatio);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    reasonForFailure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustedDemandForecastIntfId() {
        return _adjustedDemandForecastIntfId;
    }

    @Override
    public void setAdjustedDemandForecastIntfId(
        String adjustedDemandForecastIntfId) {
        _adjustedDemandForecastIntfId = adjustedDemandForecastIntfId;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustedDemandForecastIntfId",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    adjustedDemandForecastIntfId);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, country);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastType() {
        return _forecastType;
    }

    @Override
    public void setForecastType(String forecastType) {
        _forecastType = forecastType;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastType", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    forecastType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTotalAdjustedDemandUnits() {
        return _totalAdjustedDemandUnits;
    }

    @Override
    public void setTotalAdjustedDemandUnits(String totalAdjustedDemandUnits) {
        _totalAdjustedDemandUnits = totalAdjustedDemandUnits;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalAdjustedDemandUnits",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    totalAdjustedDemandUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandId() {
        return _brandId;
    }

    @Override
    public void setBrandId(String brandId) {
        _brandId = brandId;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, brandId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIsForecast() {
        return _isForecast;
    }

    @Override
    public void setIsForecast(String isForecast) {
        _isForecast = isForecast;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setIsForecast", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, isForecast);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTotalAdjustedDemandAmount() {
        return _totalAdjustedDemandAmount;
    }

    @Override
    public void setTotalAdjustedDemandAmount(String totalAdjustedDemandAmount) {
        _totalAdjustedDemandAmount = totalAdjustedDemandAmount;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalAdjustedDemandAmount",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    totalAdjustedDemandAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUncapturedUnits() {
        return _uncapturedUnits;
    }

    @Override
    public void setUncapturedUnits(String uncapturedUnits) {
        _uncapturedUnits = uncapturedUnits;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnits",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    uncapturedUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGrossPrice() {
        return _grossPrice;
    }

    @Override
    public void setGrossPrice(String grossPrice) {
        _grossPrice = grossPrice;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossPrice", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, grossPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGrossAmount() {
        return _grossAmount;
    }

    @Override
    public void setGrossAmount(String grossAmount) {
        _grossAmount = grossAmount;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossAmount", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, grossAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdentifierCodeQualifier() {
        return _itemIdentifierCodeQualifier;
    }

    @Override
    public void setItemIdentifierCodeQualifier(
        String itemIdentifierCodeQualifier) {
        _itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierCodeQualifier",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    itemIdentifierCodeQualifier);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemName() {
        return _itemName;
    }

    @Override
    public void setItemName(String itemName) {
        _itemName = itemName;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, itemName);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetSalesPrice() {
        return _netSalesPrice;
    }

    @Override
    public void setNetSalesPrice(String netSalesPrice) {
        _netSalesPrice = netSalesPrice;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPrice", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    netSalesPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetSalesAmount() {
        return _netSalesAmount;
    }

    @Override
    public void setNetSalesAmount(String netSalesAmount) {
        _netSalesAmount = netSalesAmount;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesAmount",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    netSalesAmount);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, segment);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    forecastName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldAdjustedDemandForecastSid() {
        return _ivldAdjustedDemandForecastSid;
    }

    @Override
    public void setIvldAdjustedDemandForecastSid(
        int ivldAdjustedDemandForecastSid) {
        _ivldAdjustedDemandForecastSid = ivldAdjustedDemandForecastSid;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldAdjustedDemandForecastSid",
                        int.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    ivldAdjustedDemandForecastSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMarketSizeUnits() {
        return _marketSizeUnits;
    }

    @Override
    public void setMarketSizeUnits(String marketSizeUnits) {
        _marketSizeUnits = marketSizeUnits;

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketSizeUnits",
                        String.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                    marketSizeUnits);
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

        if (_vwIvldAdjDemandForeActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldAdjDemandForeActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_vwIvldAdjDemandForeActualRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwIvldAdjDemandForeActualRemoteModel() {
        return _vwIvldAdjDemandForeActualRemoteModel;
    }

    public void setVwIvldAdjDemandForeActualRemoteModel(
        BaseModel<?> vwIvldAdjDemandForeActualRemoteModel) {
        _vwIvldAdjDemandForeActualRemoteModel = vwIvldAdjDemandForeActualRemoteModel;
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

        Class<?> remoteModelClass = _vwIvldAdjDemandForeActualRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwIvldAdjDemandForeActualRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwIvldAdjDemandForeActualLocalServiceUtil.addVwIvldAdjDemandForeActual(this);
        } else {
            VwIvldAdjDemandForeActualLocalServiceUtil.updateVwIvldAdjDemandForeActual(this);
        }
    }

    @Override
    public VwIvldAdjDemandForeActual toEscapedModel() {
        return (VwIvldAdjDemandForeActual) ProxyUtil.newProxyInstance(VwIvldAdjDemandForeActual.class.getClassLoader(),
            new Class[] { VwIvldAdjDemandForeActual.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwIvldAdjDemandForeActualClp clone = new VwIvldAdjDemandForeActualClp();

        clone.setForecastVersion(getForecastVersion());
        clone.setGrossUnits(getGrossUnits());
        clone.setBusinessUnitNo(getBusinessUnitNo());
        clone.setYear(getYear());
        clone.setItemId(getItemId());
        clone.setBrandName(getBrandName());
        clone.setModifiedDate(getModifiedDate());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setMarketShareRatio(getMarketShareRatio());
        clone.setBusinessUnitName(getBusinessUnitName());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setItemIdentifier(getItemIdentifier());
        clone.setErrorCode(getErrorCode());
        clone.setModifiedBy(getModifiedBy());
        clone.setMarketShareUnits(getMarketShareUnits());
        clone.setMonth(getMonth());
        clone.setInventoryUnitChange(getInventoryUnitChange());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setUncapturedUnitsRatio(getUncapturedUnitsRatio());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setAdjustedDemandForecastIntfId(getAdjustedDemandForecastIntfId());
        clone.setCountry(getCountry());
        clone.setForecastType(getForecastType());
        clone.setTotalAdjustedDemandUnits(getTotalAdjustedDemandUnits());
        clone.setBrandId(getBrandId());
        clone.setIsForecast(getIsForecast());
        clone.setTotalAdjustedDemandAmount(getTotalAdjustedDemandAmount());
        clone.setUncapturedUnits(getUncapturedUnits());
        clone.setGrossPrice(getGrossPrice());
        clone.setGrossAmount(getGrossAmount());
        clone.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        clone.setBatchId(getBatchId());
        clone.setItemName(getItemName());
        clone.setErrorField(getErrorField());
        clone.setNetSalesPrice(getNetSalesPrice());
        clone.setNetSalesAmount(getNetSalesAmount());
        clone.setSegment(getSegment());
        clone.setForecastName(getForecastName());
        clone.setIvldAdjustedDemandForecastSid(getIvldAdjustedDemandForecastSid());
        clone.setMarketSizeUnits(getMarketSizeUnits());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
        int primaryKey = vwIvldAdjDemandForeActual.getPrimaryKey();

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

        if (!(obj instanceof VwIvldAdjDemandForeActualClp)) {
            return false;
        }

        VwIvldAdjDemandForeActualClp vwIvldAdjDemandForeActual = (VwIvldAdjDemandForeActualClp) obj;

        int primaryKey = vwIvldAdjDemandForeActual.getPrimaryKey();

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
        StringBundler sb = new StringBundler(89);

        sb.append("{forecastVersion=");
        sb.append(getForecastVersion());
        sb.append(", grossUnits=");
        sb.append(getGrossUnits());
        sb.append(", businessUnitNo=");
        sb.append(getBusinessUnitNo());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", marketShareRatio=");
        sb.append(getMarketShareRatio());
        sb.append(", businessUnitName=");
        sb.append(getBusinessUnitName());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", itemIdentifier=");
        sb.append(getItemIdentifier());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", marketShareUnits=");
        sb.append(getMarketShareUnits());
        sb.append(", month=");
        sb.append(getMonth());
        sb.append(", inventoryUnitChange=");
        sb.append(getInventoryUnitChange());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", uncapturedUnitsRatio=");
        sb.append(getUncapturedUnitsRatio());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", adjustedDemandForecastIntfId=");
        sb.append(getAdjustedDemandForecastIntfId());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", forecastType=");
        sb.append(getForecastType());
        sb.append(", totalAdjustedDemandUnits=");
        sb.append(getTotalAdjustedDemandUnits());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", isForecast=");
        sb.append(getIsForecast());
        sb.append(", totalAdjustedDemandAmount=");
        sb.append(getTotalAdjustedDemandAmount());
        sb.append(", uncapturedUnits=");
        sb.append(getUncapturedUnits());
        sb.append(", grossPrice=");
        sb.append(getGrossPrice());
        sb.append(", grossAmount=");
        sb.append(getGrossAmount());
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", netSalesPrice=");
        sb.append(getNetSalesPrice());
        sb.append(", netSalesAmount=");
        sb.append(getNetSalesAmount());
        sb.append(", segment=");
        sb.append(getSegment());
        sb.append(", forecastName=");
        sb.append(getForecastName());
        sb.append(", ivldAdjustedDemandForecastSid=");
        sb.append(getIvldAdjustedDemandForecastSid());
        sb.append(", marketSizeUnits=");
        sb.append(getMarketSizeUnits());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(136);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>forecastVersion</column-name><column-value><![CDATA[");
        sb.append(getForecastVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossUnits</column-name><column-value><![CDATA[");
        sb.append(getGrossUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitNo</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandName</column-name><column-value><![CDATA[");
        sb.append(getBrandName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
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
            "<column><column-name>marketShareRatio</column-name><column-value><![CDATA[");
        sb.append(getMarketShareRatio());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitName</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketShareUnits</column-name><column-value><![CDATA[");
        sb.append(getMarketShareUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>month</column-name><column-value><![CDATA[");
        sb.append(getMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inventoryUnitChange</column-name><column-value><![CDATA[");
        sb.append(getInventoryUnitChange());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uncapturedUnitsRatio</column-name><column-value><![CDATA[");
        sb.append(getUncapturedUnitsRatio());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustedDemandForecastIntfId</column-name><column-value><![CDATA[");
        sb.append(getAdjustedDemandForecastIntfId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastType</column-name><column-value><![CDATA[");
        sb.append(getForecastType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalAdjustedDemandUnits</column-name><column-value><![CDATA[");
        sb.append(getTotalAdjustedDemandUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isForecast</column-name><column-value><![CDATA[");
        sb.append(getIsForecast());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalAdjustedDemandAmount</column-name><column-value><![CDATA[");
        sb.append(getTotalAdjustedDemandAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uncapturedUnits</column-name><column-value><![CDATA[");
        sb.append(getUncapturedUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossPrice</column-name><column-value><![CDATA[");
        sb.append(getGrossPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossAmount</column-name><column-value><![CDATA[");
        sb.append(getGrossAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesPrice</column-name><column-value><![CDATA[");
        sb.append(getNetSalesPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesAmount</column-name><column-value><![CDATA[");
        sb.append(getNetSalesAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>segment</column-name><column-value><![CDATA[");
        sb.append(getSegment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastName</column-name><column-value><![CDATA[");
        sb.append(getForecastName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldAdjustedDemandForecastSid</column-name><column-value><![CDATA[");
        sb.append(getIvldAdjustedDemandForecastSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketSizeUnits</column-name><column-value><![CDATA[");
        sb.append(getMarketSizeUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
