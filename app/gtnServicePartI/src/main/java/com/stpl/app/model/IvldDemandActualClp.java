package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldDemandActualLocalServiceUtil;

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


public class IvldDemandActualClp extends BaseModelImpl<IvldDemandActual>
    implements IvldDemandActual {
    private String _forecastYear;
    private String _grossUnits;
    private String _totalDemandUnits;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private String _source;
    private String _marketShareRatio;
    private String _createdBy;
    private Date _createdDate;
    private String _demandActualInterfaceId;
    private String _addChgDelIndicator;
    private String _itemIdentifier;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _marketShareUnits;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private String _reasonForFailure;
    private String _country;
    private String _forecastType;
    private String _brandId;
    private String _grossPrice;
    private int _ivldDemandActualSid;
    private String _grossAmount;
    private String _itemIdentifierCodeQualifier;
    private String _batchId;
    private String _forecastMonth;
    private String _errorField;
    private String _netSalesPrice;
    private String _netSalesAmount;
    private String _segment;
    private String _totalDemandAmount;
    private String _marketSizeUnits;
    private boolean _checkRecord;
    private BaseModel<?> _ivldDemandActualRemoteModel;

    public IvldDemandActualClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldDemandActual.class;
    }

    @Override
    public String getModelClassName() {
        return IvldDemandActual.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldDemandActualSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldDemandActualSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldDemandActualSid;
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
        attributes.put("source", getSource());
        attributes.put("marketShareRatio", getMarketShareRatio());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("demandActualInterfaceId", getDemandActualInterfaceId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("marketShareUnits", getMarketShareUnits());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("country", getCountry());
        attributes.put("forecastType", getForecastType());
        attributes.put("brandId", getBrandId());
        attributes.put("grossPrice", getGrossPrice());
        attributes.put("ivldDemandActualSid", getIvldDemandActualSid());
        attributes.put("grossAmount", getGrossAmount());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("batchId", getBatchId());
        attributes.put("forecastMonth", getForecastMonth());
        attributes.put("errorField", getErrorField());
        attributes.put("netSalesPrice", getNetSalesPrice());
        attributes.put("netSalesAmount", getNetSalesAmount());
        attributes.put("segment", getSegment());
        attributes.put("totalDemandAmount", getTotalDemandAmount());
        attributes.put("marketSizeUnits", getMarketSizeUnits());
        attributes.put("checkRecord", getCheckRecord());

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

        String demandActualInterfaceId = (String) attributes.get(
                "demandActualInterfaceId");

        if (demandActualInterfaceId != null) {
            setDemandActualInterfaceId(demandActualInterfaceId);
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

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String marketShareUnits = (String) attributes.get("marketShareUnits");

        if (marketShareUnits != null) {
            setMarketShareUnits(marketShareUnits);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
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

        String grossPrice = (String) attributes.get("grossPrice");

        if (grossPrice != null) {
            setGrossPrice(grossPrice);
        }

        Integer ivldDemandActualSid = (Integer) attributes.get(
                "ivldDemandActualSid");

        if (ivldDemandActualSid != null) {
            setIvldDemandActualSid(ivldDemandActualSid);
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
    public String getForecastYear() {
        return _forecastYear;
    }

    @Override
    public void setForecastYear(String forecastYear) {
        _forecastYear = forecastYear;

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastYear", String.class);

                method.invoke(_ivldDemandActualRemoteModel, forecastYear);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossUnits", String.class);

                method.invoke(_ivldDemandActualRemoteModel, grossUnits);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDemandUnits",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel, totalDemandUnits);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldDemandActualRemoteModel, itemId);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldDemandActualRemoteModel, modifiedDate);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel, organizationKey);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldDemandActualRemoteModel, source);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareRatio",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel, marketShareRatio);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldDemandActualRemoteModel, createdBy);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldDemandActualRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDemandActualInterfaceId() {
        return _demandActualInterfaceId;
    }

    @Override
    public void setDemandActualInterfaceId(String demandActualInterfaceId) {
        _demandActualInterfaceId = demandActualInterfaceId;

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setDemandActualInterfaceId",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel,
                    demandActualInterfaceId);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel, addChgDelIndicator);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifier",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel, itemIdentifier);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldDemandActualRemoteModel, errorCode);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldDemandActualRemoteModel, intfInsertedDate);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareUnits",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel, marketShareUnits);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldDemandActualRemoteModel, modifiedBy);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel, reprocessedFlag);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel, reasonForFailure);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_ivldDemandActualRemoteModel, country);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastType", String.class);

                method.invoke(_ivldDemandActualRemoteModel, forecastType);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_ivldDemandActualRemoteModel, brandId);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossPrice", String.class);

                method.invoke(_ivldDemandActualRemoteModel, grossPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldDemandActualSid() {
        return _ivldDemandActualSid;
    }

    @Override
    public void setIvldDemandActualSid(int ivldDemandActualSid) {
        _ivldDemandActualSid = ivldDemandActualSid;

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldDemandActualSid",
                        int.class);

                method.invoke(_ivldDemandActualRemoteModel, ivldDemandActualSid);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossAmount", String.class);

                method.invoke(_ivldDemandActualRemoteModel, grossAmount);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierCodeQualifier",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel,
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldDemandActualRemoteModel, batchId);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastMonth", String.class);

                method.invoke(_ivldDemandActualRemoteModel, forecastMonth);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldDemandActualRemoteModel, errorField);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPrice", String.class);

                method.invoke(_ivldDemandActualRemoteModel, netSalesPrice);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesAmount",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel, netSalesAmount);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_ivldDemandActualRemoteModel, segment);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDemandAmount",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel, totalDemandAmount);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketSizeUnits",
                        String.class);

                method.invoke(_ivldDemandActualRemoteModel, marketSizeUnits);
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

        if (_ivldDemandActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldDemandActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldDemandActualRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldDemandActualRemoteModel() {
        return _ivldDemandActualRemoteModel;
    }

    public void setIvldDemandActualRemoteModel(
        BaseModel<?> ivldDemandActualRemoteModel) {
        _ivldDemandActualRemoteModel = ivldDemandActualRemoteModel;
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

        Class<?> remoteModelClass = _ivldDemandActualRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldDemandActualRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldDemandActualLocalServiceUtil.addIvldDemandActual(this);
        } else {
            IvldDemandActualLocalServiceUtil.updateIvldDemandActual(this);
        }
    }

    @Override
    public IvldDemandActual toEscapedModel() {
        return (IvldDemandActual) ProxyUtil.newProxyInstance(IvldDemandActual.class.getClassLoader(),
            new Class[] { IvldDemandActual.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldDemandActualClp clone = new IvldDemandActualClp();

        clone.setForecastYear(getForecastYear());
        clone.setGrossUnits(getGrossUnits());
        clone.setTotalDemandUnits(getTotalDemandUnits());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setSource(getSource());
        clone.setMarketShareRatio(getMarketShareRatio());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setDemandActualInterfaceId(getDemandActualInterfaceId());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setItemIdentifier(getItemIdentifier());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setMarketShareUnits(getMarketShareUnits());
        clone.setModifiedBy(getModifiedBy());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setCountry(getCountry());
        clone.setForecastType(getForecastType());
        clone.setBrandId(getBrandId());
        clone.setGrossPrice(getGrossPrice());
        clone.setIvldDemandActualSid(getIvldDemandActualSid());
        clone.setGrossAmount(getGrossAmount());
        clone.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        clone.setBatchId(getBatchId());
        clone.setForecastMonth(getForecastMonth());
        clone.setErrorField(getErrorField());
        clone.setNetSalesPrice(getNetSalesPrice());
        clone.setNetSalesAmount(getNetSalesAmount());
        clone.setSegment(getSegment());
        clone.setTotalDemandAmount(getTotalDemandAmount());
        clone.setMarketSizeUnits(getMarketSizeUnits());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldDemandActual ivldDemandActual) {
        int primaryKey = ivldDemandActual.getPrimaryKey();

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

        if (!(obj instanceof IvldDemandActualClp)) {
            return false;
        }

        IvldDemandActualClp ivldDemandActual = (IvldDemandActualClp) obj;

        int primaryKey = ivldDemandActual.getPrimaryKey();

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
        StringBundler sb = new StringBundler(71);

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
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", marketShareRatio=");
        sb.append(getMarketShareRatio());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", demandActualInterfaceId=");
        sb.append(getDemandActualInterfaceId());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", itemIdentifier=");
        sb.append(getItemIdentifier());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", marketShareUnits=");
        sb.append(getMarketShareUnits());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", forecastType=");
        sb.append(getForecastType());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", grossPrice=");
        sb.append(getGrossPrice());
        sb.append(", ivldDemandActualSid=");
        sb.append(getIvldDemandActualSid());
        sb.append(", grossAmount=");
        sb.append(getGrossAmount());
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
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
        sb.append(", marketSizeUnits=");
        sb.append(getMarketSizeUnits());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(109);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldDemandActual");
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
            "<column><column-name>demandActualInterfaceId</column-name><column-value><![CDATA[");
        sb.append(getDemandActualInterfaceId());
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
            "<column><column-name>marketShareUnits</column-name><column-value><![CDATA[");
        sb.append(getMarketShareUnits());
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
            "<column><column-name>grossPrice</column-name><column-value><![CDATA[");
        sb.append(getGrossPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldDemandActualSid</column-name><column-value><![CDATA[");
        sb.append(getIvldDemandActualSid());
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
