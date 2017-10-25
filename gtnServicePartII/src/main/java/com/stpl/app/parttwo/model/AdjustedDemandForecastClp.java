package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.AdjustedDemandForecastLocalServiceUtil;
import com.stpl.app.parttwo.service.ClpSerializer;

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


public class AdjustedDemandForecastClp extends BaseModelImpl<AdjustedDemandForecast>
    implements AdjustedDemandForecast {
    private int _itemMasterSid;
    private int _adjustedDemandForecastSid;
    private double _grossUnits;
    private double _totalDemandUnits;
    private String _year;
    private String _itemId;
    private Date _modifiedDate;
    private int _brandMasterSid;
    private String _organizationKey;
    private String _source;
    private Date _createdDate;
    private String _createdBy;
    private String _marketShareRatio;
    private String _itemIdentifier;
    private String _inboundStatus;
    private String _modifiedBy;
    private double _marketShareUnits;
    private String _month;
    private double _inventoryUnitChange;
    private String _uncapturedUnitsRatio;
    private String _country;
    private String _forecastType;
    private String _brandId;
    private double _uncapturedUnits;
    private double _grossPrice;
    private boolean _recordLockStatus;
    private double _grossAmount;
    private String _itemIdentifierCodeQualifier;
    private String _forecastVer;
    private String _batchId;
    private double _netSalesPrice;
    private double _netSalesAmount;
    private String _segment;
    private double _totalDemandAmount;
    private String _forecastName;
    private double _marketSizeUnits;
    private BaseModel<?> _adjustedDemandForecastRemoteModel;

    public AdjustedDemandForecastClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AdjustedDemandForecast.class;
    }

    @Override
    public String getModelClassName() {
        return AdjustedDemandForecast.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _adjustedDemandForecastSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAdjustedDemandForecastSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _adjustedDemandForecastSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("adjustedDemandForecastSid",
            getAdjustedDemandForecastSid());
        attributes.put("grossUnits", getGrossUnits());
        attributes.put("totalDemandUnits", getTotalDemandUnits());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("marketShareRatio", getMarketShareRatio());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("marketShareUnits", getMarketShareUnits());
        attributes.put("month", getMonth());
        attributes.put("inventoryUnitChange", getInventoryUnitChange());
        attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
        attributes.put("country", getCountry());
        attributes.put("forecastType", getForecastType());
        attributes.put("brandId", getBrandId());
        attributes.put("uncapturedUnits", getUncapturedUnits());
        attributes.put("grossPrice", getGrossPrice());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("grossAmount", getGrossAmount());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("batchId", getBatchId());
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
        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer adjustedDemandForecastSid = (Integer) attributes.get(
                "adjustedDemandForecastSid");

        if (adjustedDemandForecastSid != null) {
            setAdjustedDemandForecastSid(adjustedDemandForecastSid);
        }

        Double grossUnits = (Double) attributes.get("grossUnits");

        if (grossUnits != null) {
            setGrossUnits(grossUnits);
        }

        Double totalDemandUnits = (Double) attributes.get("totalDemandUnits");

        if (totalDemandUnits != null) {
            setTotalDemandUnits(totalDemandUnits);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
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

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
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

        String marketShareRatio = (String) attributes.get("marketShareRatio");

        if (marketShareRatio != null) {
            setMarketShareRatio(marketShareRatio);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Double marketShareUnits = (Double) attributes.get("marketShareUnits");

        if (marketShareUnits != null) {
            setMarketShareUnits(marketShareUnits);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        Double inventoryUnitChange = (Double) attributes.get(
                "inventoryUnitChange");

        if (inventoryUnitChange != null) {
            setInventoryUnitChange(inventoryUnitChange);
        }

        String uncapturedUnitsRatio = (String) attributes.get(
                "uncapturedUnitsRatio");

        if (uncapturedUnitsRatio != null) {
            setUncapturedUnitsRatio(uncapturedUnitsRatio);
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

        Double uncapturedUnits = (Double) attributes.get("uncapturedUnits");

        if (uncapturedUnits != null) {
            setUncapturedUnits(uncapturedUnits);
        }

        Double grossPrice = (Double) attributes.get("grossPrice");

        if (grossPrice != null) {
            setGrossPrice(grossPrice);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Double grossAmount = (Double) attributes.get("grossAmount");

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

        Double netSalesPrice = (Double) attributes.get("netSalesPrice");

        if (netSalesPrice != null) {
            setNetSalesPrice(netSalesPrice);
        }

        Double netSalesAmount = (Double) attributes.get("netSalesAmount");

        if (netSalesAmount != null) {
            setNetSalesAmount(netSalesAmount);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }

        Double totalDemandAmount = (Double) attributes.get("totalDemandAmount");

        if (totalDemandAmount != null) {
            setTotalDemandAmount(totalDemandAmount);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Double marketSizeUnits = (Double) attributes.get("marketSizeUnits");

        if (marketSizeUnits != null) {
            setMarketSizeUnits(marketSizeUnits);
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_adjustedDemandForecastRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAdjustedDemandForecastSid() {
        return _adjustedDemandForecastSid;
    }

    @Override
    public void setAdjustedDemandForecastSid(int adjustedDemandForecastSid) {
        _adjustedDemandForecastSid = adjustedDemandForecastSid;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustedDemandForecastSid",
                        int.class);

                method.invoke(_adjustedDemandForecastRemoteModel,
                    adjustedDemandForecastSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getGrossUnits() {
        return _grossUnits;
    }

    @Override
    public void setGrossUnits(double grossUnits) {
        _grossUnits = grossUnits;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossUnits", double.class);

                method.invoke(_adjustedDemandForecastRemoteModel, grossUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalDemandUnits() {
        return _totalDemandUnits;
    }

    @Override
    public void setTotalDemandUnits(double totalDemandUnits) {
        _totalDemandUnits = totalDemandUnits;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDemandUnits",
                        double.class);

                method.invoke(_adjustedDemandForecastRemoteModel,
                    totalDemandUnits);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, year);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, itemId);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_adjustedDemandForecastRemoteModel, modifiedDate);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid", int.class);

                method.invoke(_adjustedDemandForecastRemoteModel, brandMasterSid);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_adjustedDemandForecastRemoteModel,
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, source);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_adjustedDemandForecastRemoteModel, createdDate);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, createdBy);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareRatio",
                        String.class);

                method.invoke(_adjustedDemandForecastRemoteModel,
                    marketShareRatio);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifier",
                        String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, itemIdentifier);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, inboundStatus);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getMarketShareUnits() {
        return _marketShareUnits;
    }

    @Override
    public void setMarketShareUnits(double marketShareUnits) {
        _marketShareUnits = marketShareUnits;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareUnits",
                        double.class);

                method.invoke(_adjustedDemandForecastRemoteModel,
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, month);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getInventoryUnitChange() {
        return _inventoryUnitChange;
    }

    @Override
    public void setInventoryUnitChange(double inventoryUnitChange) {
        _inventoryUnitChange = inventoryUnitChange;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryUnitChange",
                        double.class);

                method.invoke(_adjustedDemandForecastRemoteModel,
                    inventoryUnitChange);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnitsRatio",
                        String.class);

                method.invoke(_adjustedDemandForecastRemoteModel,
                    uncapturedUnitsRatio);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, country);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastType", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, forecastType);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, brandId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getUncapturedUnits() {
        return _uncapturedUnits;
    }

    @Override
    public void setUncapturedUnits(double uncapturedUnits) {
        _uncapturedUnits = uncapturedUnits;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnits",
                        double.class);

                method.invoke(_adjustedDemandForecastRemoteModel,
                    uncapturedUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getGrossPrice() {
        return _grossPrice;
    }

    @Override
    public void setGrossPrice(double grossPrice) {
        _grossPrice = grossPrice;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossPrice", double.class);

                method.invoke(_adjustedDemandForecastRemoteModel, grossPrice);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_adjustedDemandForecastRemoteModel,
                    recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getGrossAmount() {
        return _grossAmount;
    }

    @Override
    public void setGrossAmount(double grossAmount) {
        _grossAmount = grossAmount;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossAmount", double.class);

                method.invoke(_adjustedDemandForecastRemoteModel, grossAmount);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierCodeQualifier",
                        String.class);

                method.invoke(_adjustedDemandForecastRemoteModel,
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, forecastVer);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNetSalesPrice() {
        return _netSalesPrice;
    }

    @Override
    public void setNetSalesPrice(double netSalesPrice) {
        _netSalesPrice = netSalesPrice;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPrice", double.class);

                method.invoke(_adjustedDemandForecastRemoteModel, netSalesPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNetSalesAmount() {
        return _netSalesAmount;
    }

    @Override
    public void setNetSalesAmount(double netSalesAmount) {
        _netSalesAmount = netSalesAmount;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesAmount",
                        double.class);

                method.invoke(_adjustedDemandForecastRemoteModel, netSalesAmount);
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, segment);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalDemandAmount() {
        return _totalDemandAmount;
    }

    @Override
    public void setTotalDemandAmount(double totalDemandAmount) {
        _totalDemandAmount = totalDemandAmount;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDemandAmount",
                        double.class);

                method.invoke(_adjustedDemandForecastRemoteModel,
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

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_adjustedDemandForecastRemoteModel, forecastName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getMarketSizeUnits() {
        return _marketSizeUnits;
    }

    @Override
    public void setMarketSizeUnits(double marketSizeUnits) {
        _marketSizeUnits = marketSizeUnits;

        if (_adjustedDemandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _adjustedDemandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketSizeUnits",
                        double.class);

                method.invoke(_adjustedDemandForecastRemoteModel,
                    marketSizeUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAdjustedDemandForecastRemoteModel() {
        return _adjustedDemandForecastRemoteModel;
    }

    public void setAdjustedDemandForecastRemoteModel(
        BaseModel<?> adjustedDemandForecastRemoteModel) {
        _adjustedDemandForecastRemoteModel = adjustedDemandForecastRemoteModel;
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

        Class<?> remoteModelClass = _adjustedDemandForecastRemoteModel.getClass();

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

        Object returnValue = method.invoke(_adjustedDemandForecastRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AdjustedDemandForecastLocalServiceUtil.addAdjustedDemandForecast(this);
        } else {
            AdjustedDemandForecastLocalServiceUtil.updateAdjustedDemandForecast(this);
        }
    }

    @Override
    public AdjustedDemandForecast toEscapedModel() {
        return (AdjustedDemandForecast) ProxyUtil.newProxyInstance(AdjustedDemandForecast.class.getClassLoader(),
            new Class[] { AdjustedDemandForecast.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AdjustedDemandForecastClp clone = new AdjustedDemandForecastClp();

        clone.setItemMasterSid(getItemMasterSid());
        clone.setAdjustedDemandForecastSid(getAdjustedDemandForecastSid());
        clone.setGrossUnits(getGrossUnits());
        clone.setTotalDemandUnits(getTotalDemandUnits());
        clone.setYear(getYear());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setSource(getSource());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setMarketShareRatio(getMarketShareRatio());
        clone.setItemIdentifier(getItemIdentifier());
        clone.setInboundStatus(getInboundStatus());
        clone.setModifiedBy(getModifiedBy());
        clone.setMarketShareUnits(getMarketShareUnits());
        clone.setMonth(getMonth());
        clone.setInventoryUnitChange(getInventoryUnitChange());
        clone.setUncapturedUnitsRatio(getUncapturedUnitsRatio());
        clone.setCountry(getCountry());
        clone.setForecastType(getForecastType());
        clone.setBrandId(getBrandId());
        clone.setUncapturedUnits(getUncapturedUnits());
        clone.setGrossPrice(getGrossPrice());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setGrossAmount(getGrossAmount());
        clone.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        clone.setForecastVer(getForecastVer());
        clone.setBatchId(getBatchId());
        clone.setNetSalesPrice(getNetSalesPrice());
        clone.setNetSalesAmount(getNetSalesAmount());
        clone.setSegment(getSegment());
        clone.setTotalDemandAmount(getTotalDemandAmount());
        clone.setForecastName(getForecastName());
        clone.setMarketSizeUnits(getMarketSizeUnits());

        return clone;
    }

    @Override
    public int compareTo(AdjustedDemandForecast adjustedDemandForecast) {
        int primaryKey = adjustedDemandForecast.getPrimaryKey();

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

        if (!(obj instanceof AdjustedDemandForecastClp)) {
            return false;
        }

        AdjustedDemandForecastClp adjustedDemandForecast = (AdjustedDemandForecastClp) obj;

        int primaryKey = adjustedDemandForecast.getPrimaryKey();

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
        StringBundler sb = new StringBundler(73);

        sb.append("{itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", adjustedDemandForecastSid=");
        sb.append(getAdjustedDemandForecastSid());
        sb.append(", grossUnits=");
        sb.append(getGrossUnits());
        sb.append(", totalDemandUnits=");
        sb.append(getTotalDemandUnits());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", marketShareRatio=");
        sb.append(getMarketShareRatio());
        sb.append(", itemIdentifier=");
        sb.append(getItemIdentifier());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", marketShareUnits=");
        sb.append(getMarketShareUnits());
        sb.append(", month=");
        sb.append(getMonth());
        sb.append(", inventoryUnitChange=");
        sb.append(getInventoryUnitChange());
        sb.append(", uncapturedUnitsRatio=");
        sb.append(getUncapturedUnitsRatio());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", forecastType=");
        sb.append(getForecastType());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", uncapturedUnits=");
        sb.append(getUncapturedUnits());
        sb.append(", grossPrice=");
        sb.append(getGrossPrice());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", grossAmount=");
        sb.append(getGrossAmount());
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append(", forecastVer=");
        sb.append(getForecastVer());
        sb.append(", batchId=");
        sb.append(getBatchId());
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
        StringBundler sb = new StringBundler(112);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.AdjustedDemandForecast");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustedDemandForecastSid</column-name><column-value><![CDATA[");
        sb.append(getAdjustedDemandForecastSid());
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
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
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
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
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
            "<column><column-name>marketShareRatio</column-name><column-value><![CDATA[");
        sb.append(getMarketShareRatio());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifier());
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
            "<column><column-name>uncapturedUnitsRatio</column-name><column-value><![CDATA[");
        sb.append(getUncapturedUnitsRatio());
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
            "<column><column-name>uncapturedUnits</column-name><column-value><![CDATA[");
        sb.append(getUncapturedUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossPrice</column-name><column-value><![CDATA[");
        sb.append(getGrossPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
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
