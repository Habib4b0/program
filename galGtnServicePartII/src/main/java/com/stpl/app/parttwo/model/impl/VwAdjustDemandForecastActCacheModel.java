package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.VwAdjustDemandForecastAct;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing VwAdjustDemandForecastAct in entity cache.
 *
 * @author
 * @see VwAdjustDemandForecastAct
 * @generated
 */
public class VwAdjustDemandForecastActCacheModel implements CacheModel<VwAdjustDemandForecastAct>,
    Externalizable {
    public String forecastVersion;
    public double grossUnits;
    public String businessUnitNo;
    public String year;
    public String brandName;
    public String itemId;
    public String organizationKey;
    public String source;
    public int marketShareRatio;
    public String businessUnitName;
    public double marketShareUnits;
    public String month;
    public double inventoryUnitChange;
    public String uncapturedUnitsRatio;
    public String country;
    public String forecastType;
    public double totalAdjustedDemandUnits;
    public String brandId;
    public String isForecast;
    public double totalAdjustedDemandAmount;
    public double uncapturedUnits;
    public double grossPrice;
    public double grossAmount;
    public String batchId;
    public int adjustedDemandForecastId;
    public String itemName;
    public double netSalesPrice;
    public double netSalesAmount;
    public String segment;
    public String forecastName;
    public double marketSizeUnits;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(63);

        sb.append("{forecastVersion=");
        sb.append(forecastVersion);
        sb.append(", grossUnits=");
        sb.append(grossUnits);
        sb.append(", businessUnitNo=");
        sb.append(businessUnitNo);
        sb.append(", year=");
        sb.append(year);
        sb.append(", brandName=");
        sb.append(brandName);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", source=");
        sb.append(source);
        sb.append(", marketShareRatio=");
        sb.append(marketShareRatio);
        sb.append(", businessUnitName=");
        sb.append(businessUnitName);
        sb.append(", marketShareUnits=");
        sb.append(marketShareUnits);
        sb.append(", month=");
        sb.append(month);
        sb.append(", inventoryUnitChange=");
        sb.append(inventoryUnitChange);
        sb.append(", uncapturedUnitsRatio=");
        sb.append(uncapturedUnitsRatio);
        sb.append(", country=");
        sb.append(country);
        sb.append(", forecastType=");
        sb.append(forecastType);
        sb.append(", totalAdjustedDemandUnits=");
        sb.append(totalAdjustedDemandUnits);
        sb.append(", brandId=");
        sb.append(brandId);
        sb.append(", isForecast=");
        sb.append(isForecast);
        sb.append(", totalAdjustedDemandAmount=");
        sb.append(totalAdjustedDemandAmount);
        sb.append(", uncapturedUnits=");
        sb.append(uncapturedUnits);
        sb.append(", grossPrice=");
        sb.append(grossPrice);
        sb.append(", grossAmount=");
        sb.append(grossAmount);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", adjustedDemandForecastId=");
        sb.append(adjustedDemandForecastId);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", netSalesPrice=");
        sb.append(netSalesPrice);
        sb.append(", netSalesAmount=");
        sb.append(netSalesAmount);
        sb.append(", segment=");
        sb.append(segment);
        sb.append(", forecastName=");
        sb.append(forecastName);
        sb.append(", marketSizeUnits=");
        sb.append(marketSizeUnits);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public VwAdjustDemandForecastAct toEntityModel() {
        VwAdjustDemandForecastActImpl vwAdjustDemandForecastActImpl = new VwAdjustDemandForecastActImpl();

        if (forecastVersion == null) {
            vwAdjustDemandForecastActImpl.setForecastVersion(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setForecastVersion(forecastVersion);
        }

        vwAdjustDemandForecastActImpl.setGrossUnits(grossUnits);

        if (businessUnitNo == null) {
            vwAdjustDemandForecastActImpl.setBusinessUnitNo(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setBusinessUnitNo(businessUnitNo);
        }

        if (year == null) {
            vwAdjustDemandForecastActImpl.setYear(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setYear(year);
        }

        if (brandName == null) {
            vwAdjustDemandForecastActImpl.setBrandName(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setBrandName(brandName);
        }

        if (itemId == null) {
            vwAdjustDemandForecastActImpl.setItemId(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setItemId(itemId);
        }

        if (organizationKey == null) {
            vwAdjustDemandForecastActImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setOrganizationKey(organizationKey);
        }

        if (source == null) {
            vwAdjustDemandForecastActImpl.setSource(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setSource(source);
        }

        vwAdjustDemandForecastActImpl.setMarketShareRatio(marketShareRatio);

        if (businessUnitName == null) {
            vwAdjustDemandForecastActImpl.setBusinessUnitName(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setBusinessUnitName(businessUnitName);
        }

        vwAdjustDemandForecastActImpl.setMarketShareUnits(marketShareUnits);

        if (month == null) {
            vwAdjustDemandForecastActImpl.setMonth(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setMonth(month);
        }

        vwAdjustDemandForecastActImpl.setInventoryUnitChange(inventoryUnitChange);

        if (uncapturedUnitsRatio == null) {
            vwAdjustDemandForecastActImpl.setUncapturedUnitsRatio(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setUncapturedUnitsRatio(uncapturedUnitsRatio);
        }

        if (country == null) {
            vwAdjustDemandForecastActImpl.setCountry(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setCountry(country);
        }

        if (forecastType == null) {
            vwAdjustDemandForecastActImpl.setForecastType(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setForecastType(forecastType);
        }

        vwAdjustDemandForecastActImpl.setTotalAdjustedDemandUnits(totalAdjustedDemandUnits);

        if (brandId == null) {
            vwAdjustDemandForecastActImpl.setBrandId(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setBrandId(brandId);
        }

        if (isForecast == null) {
            vwAdjustDemandForecastActImpl.setIsForecast(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setIsForecast(isForecast);
        }

        vwAdjustDemandForecastActImpl.setTotalAdjustedDemandAmount(totalAdjustedDemandAmount);
        vwAdjustDemandForecastActImpl.setUncapturedUnits(uncapturedUnits);
        vwAdjustDemandForecastActImpl.setGrossPrice(grossPrice);
        vwAdjustDemandForecastActImpl.setGrossAmount(grossAmount);

        if (batchId == null) {
            vwAdjustDemandForecastActImpl.setBatchId(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setBatchId(batchId);
        }

        vwAdjustDemandForecastActImpl.setAdjustedDemandForecastId(adjustedDemandForecastId);

        if (itemName == null) {
            vwAdjustDemandForecastActImpl.setItemName(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setItemName(itemName);
        }

        vwAdjustDemandForecastActImpl.setNetSalesPrice(netSalesPrice);
        vwAdjustDemandForecastActImpl.setNetSalesAmount(netSalesAmount);

        if (segment == null) {
            vwAdjustDemandForecastActImpl.setSegment(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setSegment(segment);
        }

        if (forecastName == null) {
            vwAdjustDemandForecastActImpl.setForecastName(StringPool.BLANK);
        } else {
            vwAdjustDemandForecastActImpl.setForecastName(forecastName);
        }

        vwAdjustDemandForecastActImpl.setMarketSizeUnits(marketSizeUnits);

        vwAdjustDemandForecastActImpl.resetOriginalValues();

        return vwAdjustDemandForecastActImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        forecastVersion = objectInput.readUTF();
        grossUnits = objectInput.readDouble();
        businessUnitNo = objectInput.readUTF();
        year = objectInput.readUTF();
        brandName = objectInput.readUTF();
        itemId = objectInput.readUTF();
        organizationKey = objectInput.readUTF();
        source = objectInput.readUTF();
        marketShareRatio = objectInput.readInt();
        businessUnitName = objectInput.readUTF();
        marketShareUnits = objectInput.readDouble();
        month = objectInput.readUTF();
        inventoryUnitChange = objectInput.readDouble();
        uncapturedUnitsRatio = objectInput.readUTF();
        country = objectInput.readUTF();
        forecastType = objectInput.readUTF();
        totalAdjustedDemandUnits = objectInput.readDouble();
        brandId = objectInput.readUTF();
        isForecast = objectInput.readUTF();
        totalAdjustedDemandAmount = objectInput.readDouble();
        uncapturedUnits = objectInput.readDouble();
        grossPrice = objectInput.readDouble();
        grossAmount = objectInput.readDouble();
        batchId = objectInput.readUTF();
        adjustedDemandForecastId = objectInput.readInt();
        itemName = objectInput.readUTF();
        netSalesPrice = objectInput.readDouble();
        netSalesAmount = objectInput.readDouble();
        segment = objectInput.readUTF();
        forecastName = objectInput.readUTF();
        marketSizeUnits = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (forecastVersion == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastVersion);
        }

        objectOutput.writeDouble(grossUnits);

        if (businessUnitNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(businessUnitNo);
        }

        if (year == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(year);
        }

        if (brandName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandName);
        }

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        if (organizationKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(organizationKey);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(marketShareRatio);

        if (businessUnitName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(businessUnitName);
        }

        objectOutput.writeDouble(marketShareUnits);

        if (month == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(month);
        }

        objectOutput.writeDouble(inventoryUnitChange);

        if (uncapturedUnitsRatio == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uncapturedUnitsRatio);
        }

        if (country == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(country);
        }

        if (forecastType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastType);
        }

        objectOutput.writeDouble(totalAdjustedDemandUnits);

        if (brandId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandId);
        }

        if (isForecast == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(isForecast);
        }

        objectOutput.writeDouble(totalAdjustedDemandAmount);
        objectOutput.writeDouble(uncapturedUnits);
        objectOutput.writeDouble(grossPrice);
        objectOutput.writeDouble(grossAmount);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeInt(adjustedDemandForecastId);

        if (itemName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemName);
        }

        objectOutput.writeDouble(netSalesPrice);
        objectOutput.writeDouble(netSalesAmount);

        if (segment == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(segment);
        }

        if (forecastName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastName);
        }

        objectOutput.writeDouble(marketSizeUnits);
    }
}
