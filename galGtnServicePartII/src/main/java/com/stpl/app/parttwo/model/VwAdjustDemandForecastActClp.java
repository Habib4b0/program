package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.VwAdjustDemandForecastActLocalServiceUtil;

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


public class VwAdjustDemandForecastActClp extends BaseModelImpl<VwAdjustDemandForecastAct>
    implements VwAdjustDemandForecastAct {
    private String _forecastVersion;
    private double _grossUnits;
    private String _businessUnitNo;
    private String _year;
    private String _brandName;
    private String _itemId;
    private String _organizationKey;
    private String _source;
    private int _marketShareRatio;
    private String _businessUnitName;
    private double _marketShareUnits;
    private String _month;
    private double _inventoryUnitChange;
    private String _uncapturedUnitsRatio;
    private String _country;
    private String _forecastType;
    private double _totalAdjustedDemandUnits;
    private String _brandId;
    private String _isForecast;
    private double _totalAdjustedDemandAmount;
    private double _uncapturedUnits;
    private double _grossPrice;
    private double _grossAmount;
    private String _batchId;
    private int _adjustedDemandForecastId;
    private String _itemName;
    private double _netSalesPrice;
    private double _netSalesAmount;
    private String _segment;
    private String _forecastName;
    private double _marketSizeUnits;
    private BaseModel<?> _vwAdjustDemandForecastActRemoteModel;

    public VwAdjustDemandForecastActClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwAdjustDemandForecastAct.class;
    }

    @Override
    public String getModelClassName() {
        return VwAdjustDemandForecastAct.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _adjustedDemandForecastId;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAdjustedDemandForecastId(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _adjustedDemandForecastId;
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
        attributes.put("brandName", getBrandName());
        attributes.put("itemId", getItemId());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("source", getSource());
        attributes.put("marketShareRatio", getMarketShareRatio());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("marketShareUnits", getMarketShareUnits());
        attributes.put("month", getMonth());
        attributes.put("inventoryUnitChange", getInventoryUnitChange());
        attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
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
        attributes.put("batchId", getBatchId());
        attributes.put("adjustedDemandForecastId", getAdjustedDemandForecastId());
        attributes.put("itemName", getItemName());
        attributes.put("netSalesPrice", getNetSalesPrice());
        attributes.put("netSalesAmount", getNetSalesAmount());
        attributes.put("segment", getSegment());
        attributes.put("forecastName", getForecastName());
        attributes.put("marketSizeUnits", getMarketSizeUnits());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String forecastVersion = (String) attributes.get("forecastVersion");

        if (forecastVersion != null) {
            setForecastVersion(forecastVersion);
        }

        Double grossUnits = (Double) attributes.get("grossUnits");

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

        Integer marketShareRatio = (Integer) attributes.get("marketShareRatio");

        if (marketShareRatio != null) {
            setMarketShareRatio(marketShareRatio);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
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

        Double totalAdjustedDemandUnits = (Double) attributes.get(
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

        Double totalAdjustedDemandAmount = (Double) attributes.get(
                "totalAdjustedDemandAmount");

        if (totalAdjustedDemandAmount != null) {
            setTotalAdjustedDemandAmount(totalAdjustedDemandAmount);
        }

        Double uncapturedUnits = (Double) attributes.get("uncapturedUnits");

        if (uncapturedUnits != null) {
            setUncapturedUnits(uncapturedUnits);
        }

        Double grossPrice = (Double) attributes.get("grossPrice");

        if (grossPrice != null) {
            setGrossPrice(grossPrice);
        }

        Double grossAmount = (Double) attributes.get("grossAmount");

        if (grossAmount != null) {
            setGrossAmount(grossAmount);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer adjustedDemandForecastId = (Integer) attributes.get(
                "adjustedDemandForecastId");

        if (adjustedDemandForecastId != null) {
            setAdjustedDemandForecastId(adjustedDemandForecastId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
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
    public String getForecastVersion() {
        return _forecastVersion;
    }

    @Override
    public void setForecastVersion(String forecastVersion) {
        _forecastVersion = forecastVersion;

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVersion",
                        String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
                    forecastVersion);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossUnits", double.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, grossUnits);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitNo",
                        String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, year);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, brandName);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, itemId);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getMarketShareRatio() {
        return _marketShareRatio;
    }

    @Override
    public void setMarketShareRatio(int marketShareRatio) {
        _marketShareRatio = marketShareRatio;

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareRatio", int.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitName",
                        String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
                    businessUnitName);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketShareUnits",
                        double.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, month);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryUnitChange",
                        double.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnitsRatio",
                        String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, country);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastType", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
                    forecastType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalAdjustedDemandUnits() {
        return _totalAdjustedDemandUnits;
    }

    @Override
    public void setTotalAdjustedDemandUnits(double totalAdjustedDemandUnits) {
        _totalAdjustedDemandUnits = totalAdjustedDemandUnits;

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalAdjustedDemandUnits",
                        double.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, brandId);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setIsForecast", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, isForecast);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTotalAdjustedDemandAmount() {
        return _totalAdjustedDemandAmount;
    }

    @Override
    public void setTotalAdjustedDemandAmount(double totalAdjustedDemandAmount) {
        _totalAdjustedDemandAmount = totalAdjustedDemandAmount;

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalAdjustedDemandAmount",
                        double.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
                    totalAdjustedDemandAmount);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setUncapturedUnits",
                        double.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossPrice", double.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, grossPrice);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setGrossAmount", double.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, grossAmount);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAdjustedDemandForecastId() {
        return _adjustedDemandForecastId;
    }

    @Override
    public void setAdjustedDemandForecastId(int adjustedDemandForecastId) {
        _adjustedDemandForecastId = adjustedDemandForecastId;

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustedDemandForecastId",
                        int.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
                    adjustedDemandForecastId);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, itemName);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesPrice", double.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
                    netSalesPrice);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesAmount",
                        double.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setSegment", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel, segment);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
                    forecastName);
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

        if (_vwAdjustDemandForecastActRemoteModel != null) {
            try {
                Class<?> clazz = _vwAdjustDemandForecastActRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketSizeUnits",
                        double.class);

                method.invoke(_vwAdjustDemandForecastActRemoteModel,
                    marketSizeUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwAdjustDemandForecastActRemoteModel() {
        return _vwAdjustDemandForecastActRemoteModel;
    }

    public void setVwAdjustDemandForecastActRemoteModel(
        BaseModel<?> vwAdjustDemandForecastActRemoteModel) {
        _vwAdjustDemandForecastActRemoteModel = vwAdjustDemandForecastActRemoteModel;
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

        Class<?> remoteModelClass = _vwAdjustDemandForecastActRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwAdjustDemandForecastActRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwAdjustDemandForecastActLocalServiceUtil.addVwAdjustDemandForecastAct(this);
        } else {
            VwAdjustDemandForecastActLocalServiceUtil.updateVwAdjustDemandForecastAct(this);
        }
    }

    @Override
    public VwAdjustDemandForecastAct toEscapedModel() {
        return (VwAdjustDemandForecastAct) ProxyUtil.newProxyInstance(VwAdjustDemandForecastAct.class.getClassLoader(),
            new Class[] { VwAdjustDemandForecastAct.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwAdjustDemandForecastActClp clone = new VwAdjustDemandForecastActClp();

        clone.setForecastVersion(getForecastVersion());
        clone.setGrossUnits(getGrossUnits());
        clone.setBusinessUnitNo(getBusinessUnitNo());
        clone.setYear(getYear());
        clone.setBrandName(getBrandName());
        clone.setItemId(getItemId());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setSource(getSource());
        clone.setMarketShareRatio(getMarketShareRatio());
        clone.setBusinessUnitName(getBusinessUnitName());
        clone.setMarketShareUnits(getMarketShareUnits());
        clone.setMonth(getMonth());
        clone.setInventoryUnitChange(getInventoryUnitChange());
        clone.setUncapturedUnitsRatio(getUncapturedUnitsRatio());
        clone.setCountry(getCountry());
        clone.setForecastType(getForecastType());
        clone.setTotalAdjustedDemandUnits(getTotalAdjustedDemandUnits());
        clone.setBrandId(getBrandId());
        clone.setIsForecast(getIsForecast());
        clone.setTotalAdjustedDemandAmount(getTotalAdjustedDemandAmount());
        clone.setUncapturedUnits(getUncapturedUnits());
        clone.setGrossPrice(getGrossPrice());
        clone.setGrossAmount(getGrossAmount());
        clone.setBatchId(getBatchId());
        clone.setAdjustedDemandForecastId(getAdjustedDemandForecastId());
        clone.setItemName(getItemName());
        clone.setNetSalesPrice(getNetSalesPrice());
        clone.setNetSalesAmount(getNetSalesAmount());
        clone.setSegment(getSegment());
        clone.setForecastName(getForecastName());
        clone.setMarketSizeUnits(getMarketSizeUnits());

        return clone;
    }

    @Override
    public int compareTo(VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
        int primaryKey = vwAdjustDemandForecastAct.getPrimaryKey();

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

        if (!(obj instanceof VwAdjustDemandForecastActClp)) {
            return false;
        }

        VwAdjustDemandForecastActClp vwAdjustDemandForecastAct = (VwAdjustDemandForecastActClp) obj;

        int primaryKey = vwAdjustDemandForecastAct.getPrimaryKey();

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

        sb.append("{forecastVersion=");
        sb.append(getForecastVersion());
        sb.append(", grossUnits=");
        sb.append(getGrossUnits());
        sb.append(", businessUnitNo=");
        sb.append(getBusinessUnitNo());
        sb.append(", year=");
        sb.append(getYear());
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
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", adjustedDemandForecastId=");
        sb.append(getAdjustedDemandForecastId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", netSalesPrice=");
        sb.append(getNetSalesPrice());
        sb.append(", netSalesAmount=");
        sb.append(getNetSalesAmount());
        sb.append(", segment=");
        sb.append(getSegment());
        sb.append(", forecastName=");
        sb.append(getForecastName());
        sb.append(", marketSizeUnits=");
        sb.append(getMarketSizeUnits());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(97);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.VwAdjustDemandForecastAct");
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
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustedDemandForecastId</column-name><column-value><![CDATA[");
        sb.append(getAdjustedDemandForecastId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
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
            "<column><column-name>marketSizeUnits</column-name><column-value><![CDATA[");
        sb.append(getMarketSizeUnits());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
