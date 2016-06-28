package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.AdjustedDemandForecast;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AdjustedDemandForecast in entity cache.
 *
 * @author
 * @see AdjustedDemandForecast
 * @generated
 */
public class AdjustedDemandForecastCacheModel implements CacheModel<AdjustedDemandForecast>,
    Externalizable {
    public int itemMasterSid;
    public int adjustedDemandForecastSid;
    public double grossUnits;
    public double totalDemandUnits;
    public String year;
    public String itemId;
    public long modifiedDate;
    public int brandMasterSid;
    public String organizationKey;
    public String source;
    public long createdDate;
    public String createdBy;
    public String marketShareRatio;
    public String itemIdentifier;
    public String inboundStatus;
    public String modifiedBy;
    public double marketShareUnits;
    public String month;
    public double inventoryUnitChange;
    public String uncapturedUnitsRatio;
    public String country;
    public String forecastType;
    public String brandId;
    public double uncapturedUnits;
    public double grossPrice;
    public boolean recordLockStatus;
    public double grossAmount;
    public String itemIdentifierCodeQualifier;
    public String forecastVer;
    public String batchId;
    public double netSalesPrice;
    public double netSalesAmount;
    public String segment;
    public double totalDemandAmount;
    public String forecastName;
    public double marketSizeUnits;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(73);

        sb.append("{itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", adjustedDemandForecastSid=");
        sb.append(adjustedDemandForecastSid);
        sb.append(", grossUnits=");
        sb.append(grossUnits);
        sb.append(", totalDemandUnits=");
        sb.append(totalDemandUnits);
        sb.append(", year=");
        sb.append(year);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", brandMasterSid=");
        sb.append(brandMasterSid);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", marketShareRatio=");
        sb.append(marketShareRatio);
        sb.append(", itemIdentifier=");
        sb.append(itemIdentifier);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
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
        sb.append(", brandId=");
        sb.append(brandId);
        sb.append(", uncapturedUnits=");
        sb.append(uncapturedUnits);
        sb.append(", grossPrice=");
        sb.append(grossPrice);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", grossAmount=");
        sb.append(grossAmount);
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(itemIdentifierCodeQualifier);
        sb.append(", forecastVer=");
        sb.append(forecastVer);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", netSalesPrice=");
        sb.append(netSalesPrice);
        sb.append(", netSalesAmount=");
        sb.append(netSalesAmount);
        sb.append(", segment=");
        sb.append(segment);
        sb.append(", totalDemandAmount=");
        sb.append(totalDemandAmount);
        sb.append(", forecastName=");
        sb.append(forecastName);
        sb.append(", marketSizeUnits=");
        sb.append(marketSizeUnits);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public AdjustedDemandForecast toEntityModel() {
        AdjustedDemandForecastImpl adjustedDemandForecastImpl = new AdjustedDemandForecastImpl();

        adjustedDemandForecastImpl.setItemMasterSid(itemMasterSid);
        adjustedDemandForecastImpl.setAdjustedDemandForecastSid(adjustedDemandForecastSid);
        adjustedDemandForecastImpl.setGrossUnits(grossUnits);
        adjustedDemandForecastImpl.setTotalDemandUnits(totalDemandUnits);

        if (year == null) {
            adjustedDemandForecastImpl.setYear(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setYear(year);
        }

        if (itemId == null) {
            adjustedDemandForecastImpl.setItemId(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setItemId(itemId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            adjustedDemandForecastImpl.setModifiedDate(null);
        } else {
            adjustedDemandForecastImpl.setModifiedDate(new Date(modifiedDate));
        }

        adjustedDemandForecastImpl.setBrandMasterSid(brandMasterSid);

        if (organizationKey == null) {
            adjustedDemandForecastImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setOrganizationKey(organizationKey);
        }

        if (source == null) {
            adjustedDemandForecastImpl.setSource(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setSource(source);
        }

        if (createdDate == Long.MIN_VALUE) {
            adjustedDemandForecastImpl.setCreatedDate(null);
        } else {
            adjustedDemandForecastImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            adjustedDemandForecastImpl.setCreatedBy(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setCreatedBy(createdBy);
        }

        if (marketShareRatio == null) {
            adjustedDemandForecastImpl.setMarketShareRatio(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setMarketShareRatio(marketShareRatio);
        }

        if (itemIdentifier == null) {
            adjustedDemandForecastImpl.setItemIdentifier(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setItemIdentifier(itemIdentifier);
        }

        if (inboundStatus == null) {
            adjustedDemandForecastImpl.setInboundStatus(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setInboundStatus(inboundStatus);
        }

        if (modifiedBy == null) {
            adjustedDemandForecastImpl.setModifiedBy(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setModifiedBy(modifiedBy);
        }

        adjustedDemandForecastImpl.setMarketShareUnits(marketShareUnits);

        if (month == null) {
            adjustedDemandForecastImpl.setMonth(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setMonth(month);
        }

        adjustedDemandForecastImpl.setInventoryUnitChange(inventoryUnitChange);

        if (uncapturedUnitsRatio == null) {
            adjustedDemandForecastImpl.setUncapturedUnitsRatio(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setUncapturedUnitsRatio(uncapturedUnitsRatio);
        }

        if (country == null) {
            adjustedDemandForecastImpl.setCountry(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setCountry(country);
        }

        if (forecastType == null) {
            adjustedDemandForecastImpl.setForecastType(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setForecastType(forecastType);
        }

        if (brandId == null) {
            adjustedDemandForecastImpl.setBrandId(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setBrandId(brandId);
        }

        adjustedDemandForecastImpl.setUncapturedUnits(uncapturedUnits);
        adjustedDemandForecastImpl.setGrossPrice(grossPrice);
        adjustedDemandForecastImpl.setRecordLockStatus(recordLockStatus);
        adjustedDemandForecastImpl.setGrossAmount(grossAmount);

        if (itemIdentifierCodeQualifier == null) {
            adjustedDemandForecastImpl.setItemIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        if (forecastVer == null) {
            adjustedDemandForecastImpl.setForecastVer(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setForecastVer(forecastVer);
        }

        if (batchId == null) {
            adjustedDemandForecastImpl.setBatchId(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setBatchId(batchId);
        }

        adjustedDemandForecastImpl.setNetSalesPrice(netSalesPrice);
        adjustedDemandForecastImpl.setNetSalesAmount(netSalesAmount);

        if (segment == null) {
            adjustedDemandForecastImpl.setSegment(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setSegment(segment);
        }

        adjustedDemandForecastImpl.setTotalDemandAmount(totalDemandAmount);

        if (forecastName == null) {
            adjustedDemandForecastImpl.setForecastName(StringPool.BLANK);
        } else {
            adjustedDemandForecastImpl.setForecastName(forecastName);
        }

        adjustedDemandForecastImpl.setMarketSizeUnits(marketSizeUnits);

        adjustedDemandForecastImpl.resetOriginalValues();

        return adjustedDemandForecastImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemMasterSid = objectInput.readInt();
        adjustedDemandForecastSid = objectInput.readInt();
        grossUnits = objectInput.readDouble();
        totalDemandUnits = objectInput.readDouble();
        year = objectInput.readUTF();
        itemId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        brandMasterSid = objectInput.readInt();
        organizationKey = objectInput.readUTF();
        source = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        marketShareRatio = objectInput.readUTF();
        itemIdentifier = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        marketShareUnits = objectInput.readDouble();
        month = objectInput.readUTF();
        inventoryUnitChange = objectInput.readDouble();
        uncapturedUnitsRatio = objectInput.readUTF();
        country = objectInput.readUTF();
        forecastType = objectInput.readUTF();
        brandId = objectInput.readUTF();
        uncapturedUnits = objectInput.readDouble();
        grossPrice = objectInput.readDouble();
        recordLockStatus = objectInput.readBoolean();
        grossAmount = objectInput.readDouble();
        itemIdentifierCodeQualifier = objectInput.readUTF();
        forecastVer = objectInput.readUTF();
        batchId = objectInput.readUTF();
        netSalesPrice = objectInput.readDouble();
        netSalesAmount = objectInput.readDouble();
        segment = objectInput.readUTF();
        totalDemandAmount = objectInput.readDouble();
        forecastName = objectInput.readUTF();
        marketSizeUnits = objectInput.readDouble();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeInt(adjustedDemandForecastSid);
        objectOutput.writeDouble(grossUnits);
        objectOutput.writeDouble(totalDemandUnits);

        if (year == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(year);
        }

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(brandMasterSid);

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

        objectOutput.writeLong(createdDate);

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        if (marketShareRatio == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(marketShareRatio);
        }

        if (itemIdentifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdentifier);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
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

        if (brandId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandId);
        }

        objectOutput.writeDouble(uncapturedUnits);
        objectOutput.writeDouble(grossPrice);
        objectOutput.writeBoolean(recordLockStatus);
        objectOutput.writeDouble(grossAmount);

        if (itemIdentifierCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdentifierCodeQualifier);
        }

        if (forecastVer == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastVer);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeDouble(netSalesPrice);
        objectOutput.writeDouble(netSalesAmount);

        if (segment == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(segment);
        }

        objectOutput.writeDouble(totalDemandAmount);

        if (forecastName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastName);
        }

        objectOutput.writeDouble(marketSizeUnits);
    }
}
