package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.DemandForecastLocalServiceUtil;

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


public class DemandForecastClp extends BaseModelImpl<DemandForecast>
    implements DemandForecast {
    private String _modifiedBy;
    private Date _createdDate;
    private double _totalDemandUnits;
    private int _brandMasterSid;
    private double _marketShareUnits;
    private String _batchId;
    private double _grossAmount;
    private String _forecastVer;
    private String _brandId;
    private double _grossUnits;
    private String _country;
    private int _demandForecastSid;
    private String _forecastType;
    private int _itemMasterSid;
    private double _totalDemandAmount;
    private String _forecastMonth;
    private String _organizationKey;
    private String _createdBy;
    private double _marketSizeUnits;
    private String _segment;
    private String _forecastYear;
    private String _itemId;
    private double _inventoryUnitChange;
    private double _grossPrice;
    private String _forecastName;
    private double _netSalesAmount;
    private Date _modifiedDate;
    private String _itemIdentifier;
    private boolean _recordLockStatus;
    private String _uncapturedUnitsRatio;
    private String _itemIdentifierCodeQualifier;
    private String _marketShareRatio;
    private String _source;
    private double _uncapturedUnits;
    private double _netSalesPrice;
    private String _inboundStatus;
    private BaseModel<?> _demandForecastRemoteModel;

    public DemandForecastClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return DemandForecast.class;
    }

    @Override
    public String getModelClassName() {
        return DemandForecast.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _demandForecastSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setDemandForecastSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _demandForecastSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("totalDemandUnits", getTotalDemandUnits());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("marketShareUnits", getMarketShareUnits());
        attributes.put("batchId", getBatchId());
        attributes.put("grossAmount", getGrossAmount());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("brandId", getBrandId());
        attributes.put("grossUnits", getGrossUnits());
        attributes.put("country", getCountry());
        attributes.put("demandForecastSid", getDemandForecastSid());
        attributes.put("forecastType", getForecastType());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("totalDemandAmount", getTotalDemandAmount());
        attributes.put("forecastMonth", getForecastMonth());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("marketSizeUnits", getMarketSizeUnits());
        attributes.put("segment", getSegment());
        attributes.put("forecastYear", getForecastYear());
        attributes.put("itemId", getItemId());
        attributes.put("inventoryUnitChange", getInventoryUnitChange());
        attributes.put("grossPrice", getGrossPrice());
        attributes.put("forecastName", getForecastName());
        attributes.put("netSalesAmount", getNetSalesAmount());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("marketShareRatio", getMarketShareRatio());
        attributes.put("source", getSource());
        attributes.put("uncapturedUnits", getUncapturedUnits());
        attributes.put("netSalesPrice", getNetSalesPrice());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Double totalDemandUnits = (Double) attributes.get("totalDemandUnits");

        if (totalDemandUnits != null) {
            setTotalDemandUnits(totalDemandUnits);
        }

        Integer brandMasterSid = (Integer) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        Double marketShareUnits = (Double) attributes.get("marketShareUnits");

        if (marketShareUnits != null) {
            setMarketShareUnits(marketShareUnits);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Double grossAmount = (Double) attributes.get("grossAmount");

        if (grossAmount != null) {
            setGrossAmount(grossAmount);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        Double grossUnits = (Double) attributes.get("grossUnits");

        if (grossUnits != null) {
            setGrossUnits(grossUnits);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        Integer demandForecastSid = (Integer) attributes.get(
                "demandForecastSid");

        if (demandForecastSid != null) {
            setDemandForecastSid(demandForecastSid);
        }

        String forecastType = (String) attributes.get("forecastType");

        if (forecastType != null) {
            setForecastType(forecastType);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Double totalDemandAmount = (Double) attributes.get("totalDemandAmount");

        if (totalDemandAmount != null) {
            setTotalDemandAmount(totalDemandAmount);
        }

        String forecastMonth = (String) attributes.get("forecastMonth");

        if (forecastMonth != null) {
            setForecastMonth(forecastMonth);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Double marketSizeUnits = (Double) attributes.get("marketSizeUnits");

        if (marketSizeUnits != null) {
            setMarketSizeUnits(marketSizeUnits);
        }

        String segment = (String) attributes.get("segment");

        if (segment != null) {
            setSegment(segment);
        }

        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Double inventoryUnitChange = (Double) attributes.get(
                "inventoryUnitChange");

        if (inventoryUnitChange != null) {
            setInventoryUnitChange(inventoryUnitChange);
        }

        Double grossPrice = (Double) attributes.get("grossPrice");

        if (grossPrice != null) {
            setGrossPrice(grossPrice);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Double netSalesAmount = (Double) attributes.get("netSalesAmount");

        if (netSalesAmount != null) {
            setNetSalesAmount(netSalesAmount);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String uncapturedUnitsRatio = (String) attributes.get(
                "uncapturedUnitsRatio");

        if (uncapturedUnitsRatio != null) {
            setUncapturedUnitsRatio(uncapturedUnitsRatio);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String marketShareRatio = (String) attributes.get("marketShareRatio");

        if (marketShareRatio != null) {
            setMarketShareRatio(marketShareRatio);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Double uncapturedUnits = (Double) attributes.get("uncapturedUnits");

        if (uncapturedUnits != null) {
            setUncapturedUnits(uncapturedUnits);
        }

        Double netSalesPrice = (Double) attributes.get("netSalesPrice");

        if (netSalesPrice != null) {
            setNetSalesPrice(netSalesPrice);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public String getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_demandForecastRemoteModel, modifiedBy);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_demandForecastRemoteModel, createdDate);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDemandUnits",
                        double.class);

                method.invoke(_demandForecastRemoteModel, totalDemandUnits);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid", int.class);

                method.invoke(_demandForecastRemoteModel, brandMasterSid);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareUnits",
                        double.class);

                method.invoke(_demandForecastRemoteModel, marketShareUnits);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_demandForecastRemoteModel, batchId);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossAmount", double.class);

                method.invoke(_demandForecastRemoteModel, grossAmount);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_demandForecastRemoteModel, forecastVer);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_demandForecastRemoteModel, brandId);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossUnits", double.class);

                method.invoke(_demandForecastRemoteModel, grossUnits);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_demandForecastRemoteModel, country);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDemandForecastSid() {
        return _demandForecastSid;
    }

    @Override
    public void setDemandForecastSid(int demandForecastSid) {
        _demandForecastSid = demandForecastSid;

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setDemandForecastSid",
                        int.class);

                method.invoke(_demandForecastRemoteModel, demandForecastSid);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastType", String.class);

                method.invoke(_demandForecastRemoteModel, forecastType);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_demandForecastRemoteModel, itemMasterSid);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDemandAmount",
                        double.class);

                method.invoke(_demandForecastRemoteModel, totalDemandAmount);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastMonth", String.class);

                method.invoke(_demandForecastRemoteModel, forecastMonth);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_demandForecastRemoteModel, organizationKey);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_demandForecastRemoteModel, createdBy);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketSizeUnits",
                        double.class);

                method.invoke(_demandForecastRemoteModel, marketSizeUnits);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_demandForecastRemoteModel, segment);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastYear", String.class);

                method.invoke(_demandForecastRemoteModel, forecastYear);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_demandForecastRemoteModel, itemId);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryUnitChange",
                        double.class);

                method.invoke(_demandForecastRemoteModel, inventoryUnitChange);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossPrice", double.class);

                method.invoke(_demandForecastRemoteModel, grossPrice);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_demandForecastRemoteModel, forecastName);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesAmount",
                        double.class);

                method.invoke(_demandForecastRemoteModel, netSalesAmount);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_demandForecastRemoteModel, modifiedDate);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifier",
                        String.class);

                method.invoke(_demandForecastRemoteModel, itemIdentifier);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_demandForecastRemoteModel, recordLockStatus);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnitsRatio",
                        String.class);

                method.invoke(_demandForecastRemoteModel, uncapturedUnitsRatio);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierCodeQualifier",
                        String.class);

                method.invoke(_demandForecastRemoteModel,
                    itemIdentifierCodeQualifier);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareRatio",
                        String.class);

                method.invoke(_demandForecastRemoteModel, marketShareRatio);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_demandForecastRemoteModel, source);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnits",
                        double.class);

                method.invoke(_demandForecastRemoteModel, uncapturedUnits);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPrice", double.class);

                method.invoke(_demandForecastRemoteModel, netSalesPrice);
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

        if (_demandForecastRemoteModel != null) {
            try {
                Class<?> clazz = _demandForecastRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_demandForecastRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getDemandForecastRemoteModel() {
        return _demandForecastRemoteModel;
    }

    public void setDemandForecastRemoteModel(
        BaseModel<?> demandForecastRemoteModel) {
        _demandForecastRemoteModel = demandForecastRemoteModel;
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

        Class<?> remoteModelClass = _demandForecastRemoteModel.getClass();

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

        Object returnValue = method.invoke(_demandForecastRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DemandForecastLocalServiceUtil.addDemandForecast(this);
        } else {
            DemandForecastLocalServiceUtil.updateDemandForecast(this);
        }
    }

    @Override
    public DemandForecast toEscapedModel() {
        return (DemandForecast) ProxyUtil.newProxyInstance(DemandForecast.class.getClassLoader(),
            new Class[] { DemandForecast.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        DemandForecastClp clone = new DemandForecastClp();

        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setTotalDemandUnits(getTotalDemandUnits());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setMarketShareUnits(getMarketShareUnits());
        clone.setBatchId(getBatchId());
        clone.setGrossAmount(getGrossAmount());
        clone.setForecastVer(getForecastVer());
        clone.setBrandId(getBrandId());
        clone.setGrossUnits(getGrossUnits());
        clone.setCountry(getCountry());
        clone.setDemandForecastSid(getDemandForecastSid());
        clone.setForecastType(getForecastType());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setTotalDemandAmount(getTotalDemandAmount());
        clone.setForecastMonth(getForecastMonth());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setCreatedBy(getCreatedBy());
        clone.setMarketSizeUnits(getMarketSizeUnits());
        clone.setSegment(getSegment());
        clone.setForecastYear(getForecastYear());
        clone.setItemId(getItemId());
        clone.setInventoryUnitChange(getInventoryUnitChange());
        clone.setGrossPrice(getGrossPrice());
        clone.setForecastName(getForecastName());
        clone.setNetSalesAmount(getNetSalesAmount());
        clone.setModifiedDate(getModifiedDate());
        clone.setItemIdentifier(getItemIdentifier());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setUncapturedUnitsRatio(getUncapturedUnitsRatio());
        clone.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        clone.setMarketShareRatio(getMarketShareRatio());
        clone.setSource(getSource());
        clone.setUncapturedUnits(getUncapturedUnits());
        clone.setNetSalesPrice(getNetSalesPrice());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(DemandForecast demandForecast) {
        int primaryKey = demandForecast.getPrimaryKey();

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

        if (!(obj instanceof DemandForecastClp)) {
            return false;
        }

        DemandForecastClp demandForecast = (DemandForecastClp) obj;

        int primaryKey = demandForecast.getPrimaryKey();

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

        sb.append("{modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", totalDemandUnits=");
        sb.append(getTotalDemandUnits());
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
        sb.append(", marketShareUnits=");
        sb.append(getMarketShareUnits());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", grossAmount=");
        sb.append(getGrossAmount());
        sb.append(", forecastVer=");
        sb.append(getForecastVer());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", grossUnits=");
        sb.append(getGrossUnits());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", demandForecastSid=");
        sb.append(getDemandForecastSid());
        sb.append(", forecastType=");
        sb.append(getForecastType());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", totalDemandAmount=");
        sb.append(getTotalDemandAmount());
        sb.append(", forecastMonth=");
        sb.append(getForecastMonth());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", marketSizeUnits=");
        sb.append(getMarketSizeUnits());
        sb.append(", segment=");
        sb.append(getSegment());
        sb.append(", forecastYear=");
        sb.append(getForecastYear());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", inventoryUnitChange=");
        sb.append(getInventoryUnitChange());
        sb.append(", grossPrice=");
        sb.append(getGrossPrice());
        sb.append(", forecastName=");
        sb.append(getForecastName());
        sb.append(", netSalesAmount=");
        sb.append(getNetSalesAmount());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", itemIdentifier=");
        sb.append(getItemIdentifier());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", uncapturedUnitsRatio=");
        sb.append(getUncapturedUnitsRatio());
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append(", marketShareRatio=");
        sb.append(getMarketShareRatio());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", uncapturedUnits=");
        sb.append(getUncapturedUnits());
        sb.append(", netSalesPrice=");
        sb.append(getNetSalesPrice());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(112);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.DemandForecast");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDemandUnits</column-name><column-value><![CDATA[");
        sb.append(getTotalDemandUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBrandMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketShareUnits</column-name><column-value><![CDATA[");
        sb.append(getMarketShareUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossAmount</column-name><column-value><![CDATA[");
        sb.append(getGrossAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastVer</column-name><column-value><![CDATA[");
        sb.append(getForecastVer());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossUnits</column-name><column-value><![CDATA[");
        sb.append(getGrossUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>demandForecastSid</column-name><column-value><![CDATA[");
        sb.append(getDemandForecastSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastType</column-name><column-value><![CDATA[");
        sb.append(getForecastType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDemandAmount</column-name><column-value><![CDATA[");
        sb.append(getTotalDemandAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastMonth</column-name><column-value><![CDATA[");
        sb.append(getForecastMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketSizeUnits</column-name><column-value><![CDATA[");
        sb.append(getMarketSizeUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>segment</column-name><column-value><![CDATA[");
        sb.append(getSegment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastYear</column-name><column-value><![CDATA[");
        sb.append(getForecastYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inventoryUnitChange</column-name><column-value><![CDATA[");
        sb.append(getInventoryUnitChange());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>grossPrice</column-name><column-value><![CDATA[");
        sb.append(getGrossPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastName</column-name><column-value><![CDATA[");
        sb.append(getForecastName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesAmount</column-name><column-value><![CDATA[");
        sb.append(getNetSalesAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uncapturedUnitsRatio</column-name><column-value><![CDATA[");
        sb.append(getUncapturedUnitsRatio());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketShareRatio</column-name><column-value><![CDATA[");
        sb.append(getMarketShareRatio());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uncapturedUnits</column-name><column-value><![CDATA[");
        sb.append(getUncapturedUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesPrice</column-name><column-value><![CDATA[");
        sb.append(getNetSalesPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
