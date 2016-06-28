package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldDemandForecastLocalServiceUtil;

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


public class IvldDemandForecastClp extends BaseModelImpl<IvldDemandForecast>
    implements IvldDemandForecast {
    private String _forecastYear;
    private String _grossUnits;
    private String _totalDemandUnits;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private int _ivldDemandForecastSid;
    private String _source;
    private String _marketShareRatio;
    private String _createdBy;
    private Date _createdDate;
    private String _addChgDelIndicator;
    private String _itemIdentifier;
    private String _errorCode;
    private String _intfInsertedDate;
    private String _modifiedBy;
    private String _marketShareUnits;
    private String _inventoryUnitChange;
    private String _reprocessedFlag;
    private String _uncapturedUnitsRatio;
    private String _reasonForFailure;
    private String _country;
    private String _forecastType;
    private String _brandId;
    private String _demandForecastInterfaceId;
    private String _uncapturedUnits;
    private String _grossPrice;
    private String _grossAmount;
    private String _itemIdentifierCodeQualifier;
    private String _forecastVer;
    private String _batchId;
    private String _forecastMonth;
    private String _errorField;
    private String _netSalesPrice;
    private String _netSalesAmount;
    private String _segment;
    private String _totalDemandAmount;
    private String _forecastName;
    private String _marketSizeUnits;
    private BaseModel<?> _ivldDemandForecastRemoteModel;

    public IvldDemandForecastClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldDemandForecast.class;
    }

    @Override
    public String getModelClassName() {
        return IvldDemandForecast.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldDemandForecastSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldDemandForecastSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldDemandForecastSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("forecastYear", getForecastYear());
        attributes.put("grossUnits", getGrossUnits());
        attributes.put("totalDemandUnits", getTotalDemandUnits());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("ivldDemandForecastSid", getIvldDemandForecastSid());
        attributes.put("source", getSource());
        attributes.put("marketShareRatio", getMarketShareRatio());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("marketShareUnits", getMarketShareUnits());
        attributes.put("inventoryUnitChange", getInventoryUnitChange());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("country", getCountry());
        attributes.put("forecastType", getForecastType());
        attributes.put("brandId", getBrandId());
        attributes.put("demandForecastInterfaceId",
            getDemandForecastInterfaceId());
        attributes.put("uncapturedUnits", getUncapturedUnits());
        attributes.put("grossPrice", getGrossPrice());
        attributes.put("grossAmount", getGrossAmount());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("batchId", getBatchId());
        attributes.put("forecastMonth", getForecastMonth());
        attributes.put("errorField", getErrorField());
        attributes.put("netSalesPrice", getNetSalesPrice());
        attributes.put("netSalesAmount", getNetSalesAmount());
        attributes.put("segment", getSegment());
        attributes.put("totalDemandAmount", getTotalDemandAmount());
        attributes.put("forecastName", getForecastName());
        attributes.put("marketSizeUnits", getMarketSizeUnits());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        String grossUnits = (String) attributes.get("grossUnits");

        if (grossUnits != null) {
            setGrossUnits(grossUnits);
        }

        String totalDemandUnits = (String) attributes.get("totalDemandUnits");

        if (totalDemandUnits != null) {
            setTotalDemandUnits(totalDemandUnits);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        Integer ivldDemandForecastSid = (Integer) attributes.get(
                "ivldDemandForecastSid");

        if (ivldDemandForecastSid != null) {
            setIvldDemandForecastSid(ivldDemandForecastSid);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String marketShareRatio = (String) attributes.get("marketShareRatio");

        if (marketShareRatio != null) {
            setMarketShareRatio(marketShareRatio);
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

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String intfInsertedDate = (String) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
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

        String demandForecastInterfaceId = (String) attributes.get(
                "demandForecastInterfaceId");

        if (demandForecastInterfaceId != null) {
            setDemandForecastInterfaceId(demandForecastInterfaceId);
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

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
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
    }

    @Override
    public String getForecastYear() {
        return _forecastYear;
    }

    @Override
    public void setForecastYear(String forecastYear) {
        _forecastYear = forecastYear;

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastYear", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, forecastYear);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossUnits", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, grossUnits);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDemandUnits",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, totalDemandUnits);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, itemId);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldDemandForecastRemoteModel, modifiedDate);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, organizationKey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldDemandForecastSid() {
        return _ivldDemandForecastSid;
    }

    @Override
    public void setIvldDemandForecastSid(int ivldDemandForecastSid) {
        _ivldDemandForecastSid = ivldDemandForecastSid;

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldDemandForecastSid",
                        int.class);

                method.invoke(_ivldDemandForecastRemoteModel,
                    ivldDemandForecastSid);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, source);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareRatio",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, marketShareRatio);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, createdBy);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldDemandForecastRemoteModel, createdDate);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, addChgDelIndicator);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifier",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, itemIdentifier);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    @Override
    public void setIntfInsertedDate(String intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, intfInsertedDate);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, modifiedBy);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareUnits",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, marketShareUnits);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryUnitChange",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel,
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, reprocessedFlag);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnitsRatio",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel,
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, reasonForFailure);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, country);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastType", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, forecastType);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, brandId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDemandForecastInterfaceId() {
        return _demandForecastInterfaceId;
    }

    @Override
    public void setDemandForecastInterfaceId(String demandForecastInterfaceId) {
        _demandForecastInterfaceId = demandForecastInterfaceId;

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDemandForecastInterfaceId",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel,
                    demandForecastInterfaceId);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnits",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, uncapturedUnits);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossPrice", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, grossPrice);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossAmount", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, grossAmount);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierCodeQualifier",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel,
                    itemIdentifierCodeQualifier);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, forecastVer);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, batchId);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastMonth", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, forecastMonth);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, errorField);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPrice", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, netSalesPrice);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesAmount",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, netSalesAmount);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, segment);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDemandAmount",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, totalDemandAmount);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_ivldDemandForecastRemoteModel, forecastName);
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

        if (_ivldDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketSizeUnits",
                        String.class);

                method.invoke(_ivldDemandForecastRemoteModel, marketSizeUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldDemandForecastRemoteModel() {
        return _ivldDemandForecastRemoteModel;
    }

    public void setIvldDemandForecastRemoteModel(
        BaseModel<?> ivldDemandForecastRemoteModel) {
        _ivldDemandForecastRemoteModel = ivldDemandForecastRemoteModel;
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

        Class<?> remoteModelClass = _ivldDemandForecastRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldDemandForecastRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldDemandForecastLocalServiceUtil.addIvldDemandForecast(this);
        } else {
            IvldDemandForecastLocalServiceUtil.updateIvldDemandForecast(this);
        }
    }

    @Override
    public IvldDemandForecast toEscapedModel() {
        return (IvldDemandForecast) ProxyUtil.newProxyInstance(IvldDemandForecast.class.getClassLoader(),
            new Class[] { IvldDemandForecast.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldDemandForecastClp clone = new IvldDemandForecastClp();

        clone.setForecastYear(getForecastYear());
        clone.setGrossUnits(getGrossUnits());
        clone.setTotalDemandUnits(getTotalDemandUnits());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setIvldDemandForecastSid(getIvldDemandForecastSid());
        clone.setSource(getSource());
        clone.setMarketShareRatio(getMarketShareRatio());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setItemIdentifier(getItemIdentifier());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setMarketShareUnits(getMarketShareUnits());
        clone.setInventoryUnitChange(getInventoryUnitChange());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setUncapturedUnitsRatio(getUncapturedUnitsRatio());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setCountry(getCountry());
        clone.setForecastType(getForecastType());
        clone.setBrandId(getBrandId());
        clone.setDemandForecastInterfaceId(getDemandForecastInterfaceId());
        clone.setUncapturedUnits(getUncapturedUnits());
        clone.setGrossPrice(getGrossPrice());
        clone.setGrossAmount(getGrossAmount());
        clone.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        clone.setForecastVer(getForecastVer());
        clone.setBatchId(getBatchId());
        clone.setForecastMonth(getForecastMonth());
        clone.setErrorField(getErrorField());
        clone.setNetSalesPrice(getNetSalesPrice());
        clone.setNetSalesAmount(getNetSalesAmount());
        clone.setSegment(getSegment());
        clone.setTotalDemandAmount(getTotalDemandAmount());
        clone.setForecastName(getForecastName());
        clone.setMarketSizeUnits(getMarketSizeUnits());

        return clone;
    }

    @Override
    public int compareTo(IvldDemandForecast ivldDemandForecast) {
        int primaryKey = ivldDemandForecast.getPrimaryKey();

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

        if (!(obj instanceof IvldDemandForecastClp)) {
            return false;
        }

        IvldDemandForecastClp ivldDemandForecast = (IvldDemandForecastClp) obj;

        int primaryKey = ivldDemandForecast.getPrimaryKey();

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

        sb.append("{forecastYear=");
        sb.append(getForecastYear());
        sb.append(", grossUnits=");
        sb.append(getGrossUnits());
        sb.append(", totalDemandUnits=");
        sb.append(getTotalDemandUnits());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", ivldDemandForecastSid=");
        sb.append(getIvldDemandForecastSid());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", marketShareRatio=");
        sb.append(getMarketShareRatio());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", itemIdentifier=");
        sb.append(getItemIdentifier());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
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
        sb.append(", demandForecastInterfaceId=");
        sb.append(getDemandForecastInterfaceId());
        sb.append(", uncapturedUnits=");
        sb.append(getUncapturedUnits());
        sb.append(", grossPrice=");
        sb.append(getGrossPrice());
        sb.append(", grossAmount=");
        sb.append(getGrossAmount());
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append(", forecastVer=");
        sb.append(getForecastVer());
        sb.append(", batchId=");
        sb.append(getBatchId());
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
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(121);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldDemandForecast");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>forecastYear</column-name><column-value><![CDATA[");
        sb.append(getForecastYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossUnits</column-name><column-value><![CDATA[");
        sb.append(getGrossUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDemandUnits</column-name><column-value><![CDATA[");
        sb.append(getTotalDemandUnits());
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
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldDemandForecastSid</column-name><column-value><![CDATA[");
        sb.append(getIvldDemandForecastSid());
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
            "<column><column-name>itemIdentifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifier());
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
            "<column><column-name>demandForecastInterfaceId</column-name><column-value><![CDATA[");
        sb.append(getDemandForecastInterfaceId());
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
            "<column><column-name>forecastVer</column-name><column-value><![CDATA[");
        sb.append(getForecastVer());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
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

        sb.append("</model>");

        return sb.toString();
    }
}
