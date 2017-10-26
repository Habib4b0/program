package com.stpl.app.model.impl;

import com.stpl.app.model.DemandForecast;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DemandForecast in entity cache.
 *
 * @author
 * @see DemandForecast
 * @generated
 */
public class DemandForecastCacheModel implements CacheModel<DemandForecast>,
    Externalizable {
    public String modifiedBy;
    public long createdDate;
    public double totalDemandUnits;
    public int brandMasterSid;
    public double marketShareUnits;
    public String batchId;
    public double grossAmount;
    public String forecastVer;
    public String brandId;
    public double grossUnits;
    public String country;
    public int demandForecastSid;
    public String forecastType;
    public int itemMasterSid;
    public double totalDemandAmount;
    public String forecastMonth;
    public String organizationKey;
    public String createdBy;
    public double marketSizeUnits;
    public String segment;
    public String forecastYear;
    public String itemId;
    public double inventoryUnitChange;
    public double grossPrice;
    public String forecastName;
    public double netSalesAmount;
    public long modifiedDate;
    public String itemIdentifier;
    public boolean recordLockStatus;
    public String uncapturedUnitsRatio;
    public String itemIdentifierCodeQualifier;
    public String marketShareRatio;
    public String source;
    public double uncapturedUnits;
    public double netSalesPrice;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(73);

        sb.append("{modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", totalDemandUnits=");
        sb.append(totalDemandUnits);
        sb.append(", brandMasterSid=");
        sb.append(brandMasterSid);
        sb.append(", marketShareUnits=");
        sb.append(marketShareUnits);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", grossAmount=");
        sb.append(grossAmount);
        sb.append(", forecastVer=");
        sb.append(forecastVer);
        sb.append(", brandId=");
        sb.append(brandId);
        sb.append(", grossUnits=");
        sb.append(grossUnits);
        sb.append(", country=");
        sb.append(country);
        sb.append(", demandForecastSid=");
        sb.append(demandForecastSid);
        sb.append(", forecastType=");
        sb.append(forecastType);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", totalDemandAmount=");
        sb.append(totalDemandAmount);
        sb.append(", forecastMonth=");
        sb.append(forecastMonth);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", marketSizeUnits=");
        sb.append(marketSizeUnits);
        sb.append(", segment=");
        sb.append(segment);
        sb.append(", forecastYear=");
        sb.append(forecastYear);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", inventoryUnitChange=");
        sb.append(inventoryUnitChange);
        sb.append(", grossPrice=");
        sb.append(grossPrice);
        sb.append(", forecastName=");
        sb.append(forecastName);
        sb.append(", netSalesAmount=");
        sb.append(netSalesAmount);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", itemIdentifier=");
        sb.append(itemIdentifier);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", uncapturedUnitsRatio=");
        sb.append(uncapturedUnitsRatio);
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(itemIdentifierCodeQualifier);
        sb.append(", marketShareRatio=");
        sb.append(marketShareRatio);
        sb.append(", source=");
        sb.append(source);
        sb.append(", uncapturedUnits=");
        sb.append(uncapturedUnits);
        sb.append(", netSalesPrice=");
        sb.append(netSalesPrice);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public DemandForecast toEntityModel() {
        DemandForecastImpl demandForecastImpl = new DemandForecastImpl();

        if (modifiedBy == null) {
            demandForecastImpl.setModifiedBy(StringPool.BLANK);
        } else {
            demandForecastImpl.setModifiedBy(modifiedBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            demandForecastImpl.setCreatedDate(null);
        } else {
            demandForecastImpl.setCreatedDate(new Date(createdDate));
        }

        demandForecastImpl.setTotalDemandUnits(totalDemandUnits);
        demandForecastImpl.setBrandMasterSid(brandMasterSid);
        demandForecastImpl.setMarketShareUnits(marketShareUnits);

        if (batchId == null) {
            demandForecastImpl.setBatchId(StringPool.BLANK);
        } else {
            demandForecastImpl.setBatchId(batchId);
        }

        demandForecastImpl.setGrossAmount(grossAmount);

        if (forecastVer == null) {
            demandForecastImpl.setForecastVer(StringPool.BLANK);
        } else {
            demandForecastImpl.setForecastVer(forecastVer);
        }

        if (brandId == null) {
            demandForecastImpl.setBrandId(StringPool.BLANK);
        } else {
            demandForecastImpl.setBrandId(brandId);
        }

        demandForecastImpl.setGrossUnits(grossUnits);

        if (country == null) {
            demandForecastImpl.setCountry(StringPool.BLANK);
        } else {
            demandForecastImpl.setCountry(country);
        }

        demandForecastImpl.setDemandForecastSid(demandForecastSid);

        if (forecastType == null) {
            demandForecastImpl.setForecastType(StringPool.BLANK);
        } else {
            demandForecastImpl.setForecastType(forecastType);
        }

        demandForecastImpl.setItemMasterSid(itemMasterSid);
        demandForecastImpl.setTotalDemandAmount(totalDemandAmount);

        if (forecastMonth == null) {
            demandForecastImpl.setForecastMonth(StringPool.BLANK);
        } else {
            demandForecastImpl.setForecastMonth(forecastMonth);
        }

        if (organizationKey == null) {
            demandForecastImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            demandForecastImpl.setOrganizationKey(organizationKey);
        }

        if (createdBy == null) {
            demandForecastImpl.setCreatedBy(StringPool.BLANK);
        } else {
            demandForecastImpl.setCreatedBy(createdBy);
        }

        demandForecastImpl.setMarketSizeUnits(marketSizeUnits);

        if (segment == null) {
            demandForecastImpl.setSegment(StringPool.BLANK);
        } else {
            demandForecastImpl.setSegment(segment);
        }

        if (forecastYear == null) {
            demandForecastImpl.setForecastYear(StringPool.BLANK);
        } else {
            demandForecastImpl.setForecastYear(forecastYear);
        }

        if (itemId == null) {
            demandForecastImpl.setItemId(StringPool.BLANK);
        } else {
            demandForecastImpl.setItemId(itemId);
        }

        demandForecastImpl.setInventoryUnitChange(inventoryUnitChange);
        demandForecastImpl.setGrossPrice(grossPrice);

        if (forecastName == null) {
            demandForecastImpl.setForecastName(StringPool.BLANK);
        } else {
            demandForecastImpl.setForecastName(forecastName);
        }

        demandForecastImpl.setNetSalesAmount(netSalesAmount);

        if (modifiedDate == Long.MIN_VALUE) {
            demandForecastImpl.setModifiedDate(null);
        } else {
            demandForecastImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (itemIdentifier == null) {
            demandForecastImpl.setItemIdentifier(StringPool.BLANK);
        } else {
            demandForecastImpl.setItemIdentifier(itemIdentifier);
        }

        demandForecastImpl.setRecordLockStatus(recordLockStatus);

        if (uncapturedUnitsRatio == null) {
            demandForecastImpl.setUncapturedUnitsRatio(StringPool.BLANK);
        } else {
            demandForecastImpl.setUncapturedUnitsRatio(uncapturedUnitsRatio);
        }

        if (itemIdentifierCodeQualifier == null) {
            demandForecastImpl.setItemIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            demandForecastImpl.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        if (marketShareRatio == null) {
            demandForecastImpl.setMarketShareRatio(StringPool.BLANK);
        } else {
            demandForecastImpl.setMarketShareRatio(marketShareRatio);
        }

        if (source == null) {
            demandForecastImpl.setSource(StringPool.BLANK);
        } else {
            demandForecastImpl.setSource(source);
        }

        demandForecastImpl.setUncapturedUnits(uncapturedUnits);
        demandForecastImpl.setNetSalesPrice(netSalesPrice);

        if (inboundStatus == null) {
            demandForecastImpl.setInboundStatus(StringPool.BLANK);
        } else {
            demandForecastImpl.setInboundStatus(inboundStatus);
        }

        demandForecastImpl.resetOriginalValues();

        return demandForecastImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        modifiedBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        totalDemandUnits = objectInput.readDouble();
        brandMasterSid = objectInput.readInt();
        marketShareUnits = objectInput.readDouble();
        batchId = objectInput.readUTF();
        grossAmount = objectInput.readDouble();
        forecastVer = objectInput.readUTF();
        brandId = objectInput.readUTF();
        grossUnits = objectInput.readDouble();
        country = objectInput.readUTF();
        demandForecastSid = objectInput.readInt();
        forecastType = objectInput.readUTF();
        itemMasterSid = objectInput.readInt();
        totalDemandAmount = objectInput.readDouble();
        forecastMonth = objectInput.readUTF();
        organizationKey = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        marketSizeUnits = objectInput.readDouble();
        segment = objectInput.readUTF();
        forecastYear = objectInput.readUTF();
        itemId = objectInput.readUTF();
        inventoryUnitChange = objectInput.readDouble();
        grossPrice = objectInput.readDouble();
        forecastName = objectInput.readUTF();
        netSalesAmount = objectInput.readDouble();
        modifiedDate = objectInput.readLong();
        itemIdentifier = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        uncapturedUnitsRatio = objectInput.readUTF();
        itemIdentifierCodeQualifier = objectInput.readUTF();
        marketShareRatio = objectInput.readUTF();
        source = objectInput.readUTF();
        uncapturedUnits = objectInput.readDouble();
        netSalesPrice = objectInput.readDouble();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        objectOutput.writeLong(createdDate);
        objectOutput.writeDouble(totalDemandUnits);
        objectOutput.writeInt(brandMasterSid);
        objectOutput.writeDouble(marketShareUnits);

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        objectOutput.writeDouble(grossAmount);

        if (forecastVer == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastVer);
        }

        if (brandId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandId);
        }

        objectOutput.writeDouble(grossUnits);

        if (country == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(country);
        }

        objectOutput.writeInt(demandForecastSid);

        if (forecastType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastType);
        }

        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeDouble(totalDemandAmount);

        if (forecastMonth == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastMonth);
        }

        if (organizationKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(organizationKey);
        }

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        objectOutput.writeDouble(marketSizeUnits);

        if (segment == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(segment);
        }

        if (forecastYear == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastYear);
        }

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        objectOutput.writeDouble(inventoryUnitChange);
        objectOutput.writeDouble(grossPrice);

        if (forecastName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastName);
        }

        objectOutput.writeDouble(netSalesAmount);
        objectOutput.writeLong(modifiedDate);

        if (itemIdentifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdentifier);
        }

        objectOutput.writeBoolean(recordLockStatus);

        if (uncapturedUnitsRatio == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uncapturedUnitsRatio);
        }

        if (itemIdentifierCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdentifierCodeQualifier);
        }

        if (marketShareRatio == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(marketShareRatio);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeDouble(uncapturedUnits);
        objectOutput.writeDouble(netSalesPrice);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
