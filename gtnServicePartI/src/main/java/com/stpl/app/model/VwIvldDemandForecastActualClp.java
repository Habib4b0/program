package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.VwIvldDemandForecastActualLocalServiceUtil;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class VwIvldDemandForecastActualClp extends BaseModelImpl<VwIvldDemandForecastActual>
    implements VwIvldDemandForecastActual {
    private String _demandIntSid;
    private String _forecastYear;
    private String _grossUnits;
    private String _businessUnitNo;
    private String _totalDemandUnits;
    private String _brandName;
    private String _itemId;
    private String _organizationKey;
    private String _source;
    private String _marketShareRatio;
    private int _ivldDemandActualForecastSid;
    private String _businessUnitName;
    private String _addChgDelIndicator;
    private String _errorCode;
    private String _marketShareUnits;
    private String _inventoryUnitChange;
    private String _reprocessedFlag;
    private String _uncapturedUnitsRatio;
    private String _reasonForFailure;
    private String _country;
    private String _forecastType;
    private String _brandId;
    private String _isForecast;
    private String _uncapturedUnits;
    private String _grossPrice;
    private String _isActive;
    private String _grossAmount;
    private String _batchId;
    private String _forecastVer;
    private String _itemName;
    private String _forecastMonth;
    private String _errorField;
    private String _netSalesPrice;
    private String _netSalesAmount;
    private String _segment;
    private String _totalDemandAmount;
    private String _forecastName;
    private String _marketSizeUnits;
    private boolean _checkRecord;
    private BaseModel<?> _vwIvldDemandForecastActualRemoteModel;

    public VwIvldDemandForecastActualClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwIvldDemandForecastActual.class;
    }

    @Override
    public String getModelClassName() {
        return VwIvldDemandForecastActual.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldDemandActualForecastSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldDemandActualForecastSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldDemandActualForecastSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("demandIntSid", getDemandIntSid());
        attributes.put("forecastYear", getForecastYear());
        attributes.put("grossUnits", getGrossUnits());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("totalDemandUnits", getTotalDemandUnits());
        attributes.put("brandName", getBrandName());
        attributes.put("itemId", getItemId());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("source", getSource());
        attributes.put("marketShareRatio", getMarketShareRatio());
        attributes.put("ivldDemandActualForecastSid",
            getIvldDemandActualForecastSid());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("errorCode", getErrorCode());
        attributes.put("marketShareUnits", getMarketShareUnits());
        attributes.put("inventoryUnitChange", getInventoryUnitChange());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("country", getCountry());
        attributes.put("forecastType", getForecastType());
        attributes.put("brandId", getBrandId());
        attributes.put("isForecast", getIsForecast());
        attributes.put("uncapturedUnits", getUncapturedUnits());
        attributes.put("grossPrice", getGrossPrice());
        attributes.put("isActive", getIsActive());
        attributes.put("grossAmount", getGrossAmount());
        attributes.put("batchId", getBatchId());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("itemName", getItemName());
        attributes.put("forecastMonth", getForecastMonth());
        attributes.put("errorField", getErrorField());
        attributes.put("netSalesPrice", getNetSalesPrice());
        attributes.put("netSalesAmount", getNetSalesAmount());
        attributes.put("segment", getSegment());
        attributes.put("totalDemandAmount", getTotalDemandAmount());
        attributes.put("forecastName", getForecastName());
        attributes.put("marketSizeUnits", getMarketSizeUnits());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String demandIntSid = (String) attributes.get("demandIntSid");

        if (demandIntSid != null) {
            setDemandIntSid(demandIntSid);
        }

        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        String grossUnits = (String) attributes.get("grossUnits");

        if (grossUnits != null) {
            setGrossUnits(grossUnits);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        String totalDemandUnits = (String) attributes.get("totalDemandUnits");

        if (totalDemandUnits != null) {
            setTotalDemandUnits(totalDemandUnits);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String marketShareRatio = (String) attributes.get("marketShareRatio");

        if (marketShareRatio != null) {
            setMarketShareRatio(marketShareRatio);
        }

        Integer ivldDemandActualForecastSid = (Integer) attributes.get(
                "ivldDemandActualForecastSid");

        if (ivldDemandActualForecastSid != null) {
            setIvldDemandActualForecastSid(ivldDemandActualForecastSid);
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

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String marketShareUnits = (String) attributes.get("marketShareUnits");

        if (marketShareUnits != null) {
            setMarketShareUnits(marketShareUnits);
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

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        String forecastType = (String) attributes.get("forecastType");

        if (forecastType != null) {
            setForecastType(forecastType);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String isForecast = (String) attributes.get("isForecast");

        if (isForecast != null) {
            setIsForecast(isForecast);
        }

        String uncapturedUnits = (String) attributes.get("uncapturedUnits");

        if (uncapturedUnits != null) {
            setUncapturedUnits(uncapturedUnits);
        }

        String grossPrice = (String) attributes.get("grossPrice");

        if (grossPrice != null) {
            setGrossPrice(grossPrice);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String grossAmount = (String) attributes.get("grossAmount");

        if (grossAmount != null) {
            setGrossAmount(grossAmount);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String forecastMonth = (String) attributes.get("forecastMonth");

        if (forecastMonth != null) {
            setForecastMonth(forecastMonth);
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

        String totalDemandAmount = (String) attributes.get("totalDemandAmount");

        if (totalDemandAmount != null) {
            setTotalDemandAmount(totalDemandAmount);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
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
    public String getDemandIntSid() {
        return _demandIntSid;
    }

    @Override
    public void setDemandIntSid(String demandIntSid) {
        _demandIntSid = demandIntSid;

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setDemandIntSid", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    demandIntSid);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastYear", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    forecastYear);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossUnits", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, grossUnits);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitNo",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    businessUnitNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTotalDemandUnits() {
        return _totalDemandUnits;
    }

    @Override
    public void setTotalDemandUnits(String totalDemandUnits) {
        _totalDemandUnits = totalDemandUnits;

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDemandUnits",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    totalDemandUnits);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, brandName);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, itemId);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    organizationKey);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, source);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareRatio",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    marketShareRatio);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldDemandActualForecastSid() {
        return _ivldDemandActualForecastSid;
    }

    @Override
    public void setIvldDemandActualForecastSid(int ivldDemandActualForecastSid) {
        _ivldDemandActualForecastSid = ivldDemandActualForecastSid;

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldDemandActualForecastSid",
                        int.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    ivldDemandActualForecastSid);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitName",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    addChgDelIndicator);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, errorCode);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareUnits",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    marketShareUnits);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryUnitChange",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnitsRatio",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    reasonForFailure);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, country);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastType", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    forecastType);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, brandId);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setIsForecast", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, isForecast);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnits",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossPrice", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, grossPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIsActive() {
        return _isActive;
    }

    @Override
    public void setIsActive(String isActive) {
        _isActive = isActive;

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, isActive);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossAmount", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    grossAmount);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, batchId);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    forecastVer);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, itemName);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastMonth", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    forecastMonth);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, errorField);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPrice", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesAmount",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel, segment);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTotalDemandAmount() {
        return _totalDemandAmount;
    }

    @Override
    public void setTotalDemandAmount(String totalDemandAmount) {
        _totalDemandAmount = totalDemandAmount;

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDemandAmount",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    totalDemandAmount);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    forecastName);
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketSizeUnits",
                        String.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
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

        if (_vwIvldDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_vwIvldDemandForecastActualRemoteModel,
                    checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwIvldDemandForecastActualRemoteModel() {
        return _vwIvldDemandForecastActualRemoteModel;
    }

    public void setVwIvldDemandForecastActualRemoteModel(
        BaseModel<?> vwIvldDemandForecastActualRemoteModel) {
        _vwIvldDemandForecastActualRemoteModel = vwIvldDemandForecastActualRemoteModel;
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

        Class<?> remoteModelClass = _vwIvldDemandForecastActualRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwIvldDemandForecastActualRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwIvldDemandForecastActualLocalServiceUtil.addVwIvldDemandForecastActual(this);
        } else {
            VwIvldDemandForecastActualLocalServiceUtil.updateVwIvldDemandForecastActual(this);
        }
    }

    @Override
    public VwIvldDemandForecastActual toEscapedModel() {
        return (VwIvldDemandForecastActual) ProxyUtil.newProxyInstance(VwIvldDemandForecastActual.class.getClassLoader(),
            new Class[] { VwIvldDemandForecastActual.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwIvldDemandForecastActualClp clone = new VwIvldDemandForecastActualClp();

        clone.setDemandIntSid(getDemandIntSid());
        clone.setForecastYear(getForecastYear());
        clone.setGrossUnits(getGrossUnits());
        clone.setBusinessUnitNo(getBusinessUnitNo());
        clone.setTotalDemandUnits(getTotalDemandUnits());
        clone.setBrandName(getBrandName());
        clone.setItemId(getItemId());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setSource(getSource());
        clone.setMarketShareRatio(getMarketShareRatio());
        clone.setIvldDemandActualForecastSid(getIvldDemandActualForecastSid());
        clone.setBusinessUnitName(getBusinessUnitName());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setErrorCode(getErrorCode());
        clone.setMarketShareUnits(getMarketShareUnits());
        clone.setInventoryUnitChange(getInventoryUnitChange());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setUncapturedUnitsRatio(getUncapturedUnitsRatio());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setCountry(getCountry());
        clone.setForecastType(getForecastType());
        clone.setBrandId(getBrandId());
        clone.setIsForecast(getIsForecast());
        clone.setUncapturedUnits(getUncapturedUnits());
        clone.setGrossPrice(getGrossPrice());
        clone.setIsActive(getIsActive());
        clone.setGrossAmount(getGrossAmount());
        clone.setBatchId(getBatchId());
        clone.setForecastVer(getForecastVer());
        clone.setItemName(getItemName());
        clone.setForecastMonth(getForecastMonth());
        clone.setErrorField(getErrorField());
        clone.setNetSalesPrice(getNetSalesPrice());
        clone.setNetSalesAmount(getNetSalesAmount());
        clone.setSegment(getSegment());
        clone.setTotalDemandAmount(getTotalDemandAmount());
        clone.setForecastName(getForecastName());
        clone.setMarketSizeUnits(getMarketSizeUnits());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(VwIvldDemandForecastActual vwIvldDemandForecastActual) {
        int primaryKey = vwIvldDemandForecastActual.getPrimaryKey();

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

        if (!(obj instanceof VwIvldDemandForecastActualClp)) {
            return false;
        }

        VwIvldDemandForecastActualClp vwIvldDemandForecastActual = (VwIvldDemandForecastActualClp) obj;

        int primaryKey = vwIvldDemandForecastActual.getPrimaryKey();

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

        sb.append("{demandIntSid=");
        sb.append(getDemandIntSid());
        sb.append(", forecastYear=");
        sb.append(getForecastYear());
        sb.append(", grossUnits=");
        sb.append(getGrossUnits());
        sb.append(", businessUnitNo=");
        sb.append(getBusinessUnitNo());
        sb.append(", totalDemandUnits=");
        sb.append(getTotalDemandUnits());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", marketShareRatio=");
        sb.append(getMarketShareRatio());
        sb.append(", ivldDemandActualForecastSid=");
        sb.append(getIvldDemandActualForecastSid());
        sb.append(", businessUnitName=");
        sb.append(getBusinessUnitName());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", marketShareUnits=");
        sb.append(getMarketShareUnits());
        sb.append(", inventoryUnitChange=");
        sb.append(getInventoryUnitChange());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", uncapturedUnitsRatio=");
        sb.append(getUncapturedUnitsRatio());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", forecastType=");
        sb.append(getForecastType());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", isForecast=");
        sb.append(getIsForecast());
        sb.append(", uncapturedUnits=");
        sb.append(getUncapturedUnits());
        sb.append(", grossPrice=");
        sb.append(getGrossPrice());
        sb.append(", isActive=");
        sb.append(getIsActive());
        sb.append(", grossAmount=");
        sb.append(getGrossAmount());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", forecastVer=");
        sb.append(getForecastVer());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", forecastMonth=");
        sb.append(getForecastMonth());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", netSalesPrice=");
        sb.append(getNetSalesPrice());
        sb.append(", netSalesAmount=");
        sb.append(getNetSalesAmount());
        sb.append(", segment=");
        sb.append(getSegment());
        sb.append(", totalDemandAmount=");
        sb.append(getTotalDemandAmount());
        sb.append(", forecastName=");
        sb.append(getForecastName());
        sb.append(", marketSizeUnits=");
        sb.append(getMarketSizeUnits());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(121);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.VwIvldDemandForecastActual");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>demandIntSid</column-name><column-value><![CDATA[");
        sb.append(getDemandIntSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastYear</column-name><column-value><![CDATA[");
        sb.append(getForecastYear());
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
            "<column><column-name>totalDemandUnits</column-name><column-value><![CDATA[");
        sb.append(getTotalDemandUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandName</column-name><column-value><![CDATA[");
        sb.append(getBrandName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
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
            "<column><column-name>ivldDemandActualForecastSid</column-name><column-value><![CDATA[");
        sb.append(getIvldDemandActualForecastSid());
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
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketShareUnits</column-name><column-value><![CDATA[");
        sb.append(getMarketShareUnits());
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
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastType</column-name><column-value><![CDATA[");
        sb.append(getForecastType());
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
            "<column><column-name>uncapturedUnits</column-name><column-value><![CDATA[");
        sb.append(getUncapturedUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossPrice</column-name><column-value><![CDATA[");
        sb.append(getGrossPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isActive</column-name><column-value><![CDATA[");
        sb.append(getIsActive());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossAmount</column-name><column-value><![CDATA[");
        sb.append(getGrossAmount());
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
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastMonth</column-name><column-value><![CDATA[");
        sb.append(getForecastMonth());
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
            "<column><column-name>totalDemandAmount</column-name><column-value><![CDATA[");
        sb.append(getTotalDemandAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastName</column-name><column-value><![CDATA[");
        sb.append(getForecastName());
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
