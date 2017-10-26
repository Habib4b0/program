package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.VwDemandForecastActualLocalServiceUtil;

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


public class VwDemandForecastActualClp extends BaseModelImpl<VwDemandForecastActual>
    implements VwDemandForecastActual {
    private String _forecastYear;
    private double _grossUnits;
    private String _businessUnitNo;
    private double _totalDemandUnits;
    private String _brandName;
    private String _itemId;
    private String _organizationKey;
    private String _source;
    private String _marketShareRatio;
    private String _businessUnitName;
    private int _demandForecastActualSid;
    private double _marketShareUnits;
    private double _inventoryUnitChange;
    private String _uncapturedUnitsRatio;
    private String _country;
    private String _forecastType;
    private String _brandId;
    private String _isForecast;
    private double _uncapturedUnits;
    private double _grossPrice;
    private String _isActive;
    private double _grossAmount;
    private String _batchId;
    private String _forecastVer;
    private String _itemName;
    private String _forecastMonth;
    private double _netSalesPrice;
    private double _netSalesAmount;
    private String _segment;
    private double _totalDemandAmount;
    private String _forecastName;
    private double _marketSizeUnits;
    private int _demandId;
    private BaseModel<?> _vwDemandForecastActualRemoteModel;

    public VwDemandForecastActualClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwDemandForecastActual.class;
    }

    @Override
    public String getModelClassName() {
        return VwDemandForecastActual.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _demandForecastActualSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setDemandForecastActualSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _demandForecastActualSid;
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
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("totalDemandUnits", getTotalDemandUnits());
        attributes.put("brandName", getBrandName());
        attributes.put("itemId", getItemId());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("source", getSource());
        attributes.put("marketShareRatio", getMarketShareRatio());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("demandForecastActualSid", getDemandForecastActualSid());
        attributes.put("marketShareUnits", getMarketShareUnits());
        attributes.put("inventoryUnitChange", getInventoryUnitChange());
        attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
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
        attributes.put("netSalesPrice", getNetSalesPrice());
        attributes.put("netSalesAmount", getNetSalesAmount());
        attributes.put("segment", getSegment());
        attributes.put("totalDemandAmount", getTotalDemandAmount());
        attributes.put("forecastName", getForecastName());
        attributes.put("marketSizeUnits", getMarketSizeUnits());
        attributes.put("demandId", getDemandId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String forecastYear = (String) attributes.get("forecastYear");

        if (forecastYear != null) {
            setForecastYear(forecastYear);
        }

        Double grossUnits = (Double) attributes.get("grossUnits");

        if (grossUnits != null) {
            setGrossUnits(grossUnits);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        Double totalDemandUnits = (Double) attributes.get("totalDemandUnits");

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

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        Integer demandForecastActualSid = (Integer) attributes.get(
                "demandForecastActualSid");

        if (demandForecastActualSid != null) {
            setDemandForecastActualSid(demandForecastActualSid);
        }

        Double marketShareUnits = (Double) attributes.get("marketShareUnits");

        if (marketShareUnits != null) {
            setMarketShareUnits(marketShareUnits);
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

        String isForecast = (String) attributes.get("isForecast");

        if (isForecast != null) {
            setIsForecast(isForecast);
        }

        Double uncapturedUnits = (Double) attributes.get("uncapturedUnits");

        if (uncapturedUnits != null) {
            setUncapturedUnits(uncapturedUnits);
        }

        Double grossPrice = (Double) attributes.get("grossPrice");

        if (grossPrice != null) {
            setGrossPrice(grossPrice);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        Double grossAmount = (Double) attributes.get("grossAmount");

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

        Integer demandId = (Integer) attributes.get("demandId");

        if (demandId != null) {
            setDemandId(demandId);
        }
    }

    @Override
    public String getForecastYear() {
        return _forecastYear;
    }

    @Override
    public void setForecastYear(String forecastYear) {
        _forecastYear = forecastYear;

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastYear", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, forecastYear);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossUnits", double.class);

                method.invoke(_vwDemandForecastActualRemoteModel, grossUnits);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitNo",
                        String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, businessUnitNo);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDemandUnits",
                        double.class);

                method.invoke(_vwDemandForecastActualRemoteModel,
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, brandName);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, itemId);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_vwDemandForecastActualRemoteModel,
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, source);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareRatio",
                        String.class);

                method.invoke(_vwDemandForecastActualRemoteModel,
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitName",
                        String.class);

                method.invoke(_vwDemandForecastActualRemoteModel,
                    businessUnitName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDemandForecastActualSid() {
        return _demandForecastActualSid;
    }

    @Override
    public void setDemandForecastActualSid(int demandForecastActualSid) {
        _demandForecastActualSid = demandForecastActualSid;

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setDemandForecastActualSid",
                        int.class);

                method.invoke(_vwDemandForecastActualRemoteModel,
                    demandForecastActualSid);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareUnits",
                        double.class);

                method.invoke(_vwDemandForecastActualRemoteModel,
                    marketShareUnits);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryUnitChange",
                        double.class);

                method.invoke(_vwDemandForecastActualRemoteModel,
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnitsRatio",
                        String.class);

                method.invoke(_vwDemandForecastActualRemoteModel,
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, country);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastType", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, forecastType);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, brandId);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setIsForecast", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, isForecast);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnits",
                        double.class);

                method.invoke(_vwDemandForecastActualRemoteModel,
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossPrice", double.class);

                method.invoke(_vwDemandForecastActualRemoteModel, grossPrice);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, isActive);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossAmount", double.class);

                method.invoke(_vwDemandForecastActualRemoteModel, grossAmount);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, batchId);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, forecastVer);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, itemName);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastMonth", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, forecastMonth);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPrice", double.class);

                method.invoke(_vwDemandForecastActualRemoteModel, netSalesPrice);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesAmount",
                        double.class);

                method.invoke(_vwDemandForecastActualRemoteModel, netSalesAmount);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, segment);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDemandAmount",
                        double.class);

                method.invoke(_vwDemandForecastActualRemoteModel,
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_vwDemandForecastActualRemoteModel, forecastName);
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

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketSizeUnits",
                        double.class);

                method.invoke(_vwDemandForecastActualRemoteModel,
                    marketSizeUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDemandId() {
        return _demandId;
    }

    @Override
    public void setDemandId(int demandId) {
        _demandId = demandId;

        if (_vwDemandForecastActualRemoteModel != null) {
            try {
                Class<?> clazz = _vwDemandForecastActualRemoteModel.getClass();

                Method method = clazz.getMethod("setDemandId", int.class);

                method.invoke(_vwDemandForecastActualRemoteModel, demandId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwDemandForecastActualRemoteModel() {
        return _vwDemandForecastActualRemoteModel;
    }

    public void setVwDemandForecastActualRemoteModel(
        BaseModel<?> vwDemandForecastActualRemoteModel) {
        _vwDemandForecastActualRemoteModel = vwDemandForecastActualRemoteModel;
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

        Class<?> remoteModelClass = _vwDemandForecastActualRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwDemandForecastActualRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwDemandForecastActualLocalServiceUtil.addVwDemandForecastActual(this);
        } else {
            VwDemandForecastActualLocalServiceUtil.updateVwDemandForecastActual(this);
        }
    }

    @Override
    public VwDemandForecastActual toEscapedModel() {
        return (VwDemandForecastActual) ProxyUtil.newProxyInstance(VwDemandForecastActual.class.getClassLoader(),
            new Class[] { VwDemandForecastActual.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwDemandForecastActualClp clone = new VwDemandForecastActualClp();

        clone.setForecastYear(getForecastYear());
        clone.setGrossUnits(getGrossUnits());
        clone.setBusinessUnitNo(getBusinessUnitNo());
        clone.setTotalDemandUnits(getTotalDemandUnits());
        clone.setBrandName(getBrandName());
        clone.setItemId(getItemId());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setSource(getSource());
        clone.setMarketShareRatio(getMarketShareRatio());
        clone.setBusinessUnitName(getBusinessUnitName());
        clone.setDemandForecastActualSid(getDemandForecastActualSid());
        clone.setMarketShareUnits(getMarketShareUnits());
        clone.setInventoryUnitChange(getInventoryUnitChange());
        clone.setUncapturedUnitsRatio(getUncapturedUnitsRatio());
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
        clone.setNetSalesPrice(getNetSalesPrice());
        clone.setNetSalesAmount(getNetSalesAmount());
        clone.setSegment(getSegment());
        clone.setTotalDemandAmount(getTotalDemandAmount());
        clone.setForecastName(getForecastName());
        clone.setMarketSizeUnits(getMarketSizeUnits());
        clone.setDemandId(getDemandId());

        return clone;
    }

    @Override
    public int compareTo(VwDemandForecastActual vwDemandForecastActual) {
        int primaryKey = vwDemandForecastActual.getPrimaryKey();

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

        if (!(obj instanceof VwDemandForecastActualClp)) {
            return false;
        }

        VwDemandForecastActualClp vwDemandForecastActual = (VwDemandForecastActualClp) obj;

        int primaryKey = vwDemandForecastActual.getPrimaryKey();

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
        StringBundler sb = new StringBundler(67);

        sb.append("{forecastYear=");
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
        sb.append(", businessUnitName=");
        sb.append(getBusinessUnitName());
        sb.append(", demandForecastActualSid=");
        sb.append(getDemandForecastActualSid());
        sb.append(", marketShareUnits=");
        sb.append(getMarketShareUnits());
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
        sb.append(", demandId=");
        sb.append(getDemandId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(103);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.VwDemandForecastActual");
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
            "<column><column-name>businessUnitName</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>demandForecastActualSid</column-name><column-value><![CDATA[");
        sb.append(getDemandForecastActualSid());
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
            "<column><column-name>demandId</column-name><column-value><![CDATA[");
        sb.append(getDemandId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
