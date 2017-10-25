package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwIvldAdjDemandForeActual in entity cache.
 *
 * @author
 * @see VwIvldAdjDemandForeActual
 * @generated
 */
public class VwIvldAdjDemandForeActualCacheModel implements CacheModel<VwIvldAdjDemandForeActual>,
    Externalizable {
    public String forecastVersion;
    public String grossUnits;
    public String businessUnitNo;
    public String year;
    public String itemId;
    public String brandName;
    public long modifiedDate;
    public String organizationKey;
    public long createdDate;
    public String createdBy;
    public String source;
    public String marketShareRatio;
    public String businessUnitName;
    public String addChgDelIndicator;
    public String itemIdentifier;
    public String errorCode;
    public String modifiedBy;
    public String marketShareUnits;
    public String month;
    public String inventoryUnitChange;
    public String reprocessedFlag;
    public String uncapturedUnitsRatio;
    public String reasonForFailure;
    public String adjustedDemandForecastIntfId;
    public String country;
    public String forecastType;
    public String totalAdjustedDemandUnits;
    public String brandId;
    public String isForecast;
    public String totalAdjustedDemandAmount;
    public String uncapturedUnits;
    public String grossPrice;
    public String grossAmount;
    public String itemIdentifierCodeQualifier;
    public String batchId;
    public String itemName;
    public String errorField;
    public String netSalesPrice;
    public String netSalesAmount;
    public String segment;
    public String forecastName;
    public int ivldAdjustedDemandForecastSid;
    public String marketSizeUnits;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(89);

        sb.append("{forecastVersion=");
        sb.append(forecastVersion);
        sb.append(", grossUnits=");
        sb.append(grossUnits);
        sb.append(", businessUnitNo=");
        sb.append(businessUnitNo);
        sb.append(", year=");
        sb.append(year);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", brandName=");
        sb.append(brandName);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", source=");
        sb.append(source);
        sb.append(", marketShareRatio=");
        sb.append(marketShareRatio);
        sb.append(", businessUnitName=");
        sb.append(businessUnitName);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", itemIdentifier=");
        sb.append(itemIdentifier);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", marketShareUnits=");
        sb.append(marketShareUnits);
        sb.append(", month=");
        sb.append(month);
        sb.append(", inventoryUnitChange=");
        sb.append(inventoryUnitChange);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", uncapturedUnitsRatio=");
        sb.append(uncapturedUnitsRatio);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", adjustedDemandForecastIntfId=");
        sb.append(adjustedDemandForecastIntfId);
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
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(itemIdentifierCodeQualifier);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", netSalesPrice=");
        sb.append(netSalesPrice);
        sb.append(", netSalesAmount=");
        sb.append(netSalesAmount);
        sb.append(", segment=");
        sb.append(segment);
        sb.append(", forecastName=");
        sb.append(forecastName);
        sb.append(", ivldAdjustedDemandForecastSid=");
        sb.append(ivldAdjustedDemandForecastSid);
        sb.append(", marketSizeUnits=");
        sb.append(marketSizeUnits);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public VwIvldAdjDemandForeActual toEntityModel() {
        VwIvldAdjDemandForeActualImpl vwIvldAdjDemandForeActualImpl = new VwIvldAdjDemandForeActualImpl();

        if (forecastVersion == null) {
            vwIvldAdjDemandForeActualImpl.setForecastVersion(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setForecastVersion(forecastVersion);
        }

        if (grossUnits == null) {
            vwIvldAdjDemandForeActualImpl.setGrossUnits(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setGrossUnits(grossUnits);
        }

        if (businessUnitNo == null) {
            vwIvldAdjDemandForeActualImpl.setBusinessUnitNo(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setBusinessUnitNo(businessUnitNo);
        }

        if (year == null) {
            vwIvldAdjDemandForeActualImpl.setYear(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setYear(year);
        }

        if (itemId == null) {
            vwIvldAdjDemandForeActualImpl.setItemId(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setItemId(itemId);
        }

        if (brandName == null) {
            vwIvldAdjDemandForeActualImpl.setBrandName(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setBrandName(brandName);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            vwIvldAdjDemandForeActualImpl.setModifiedDate(null);
        } else {
            vwIvldAdjDemandForeActualImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (organizationKey == null) {
            vwIvldAdjDemandForeActualImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setOrganizationKey(organizationKey);
        }

        if (createdDate == Long.MIN_VALUE) {
            vwIvldAdjDemandForeActualImpl.setCreatedDate(null);
        } else {
            vwIvldAdjDemandForeActualImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            vwIvldAdjDemandForeActualImpl.setCreatedBy(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setCreatedBy(createdBy);
        }

        if (source == null) {
            vwIvldAdjDemandForeActualImpl.setSource(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setSource(source);
        }

        if (marketShareRatio == null) {
            vwIvldAdjDemandForeActualImpl.setMarketShareRatio(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setMarketShareRatio(marketShareRatio);
        }

        if (businessUnitName == null) {
            vwIvldAdjDemandForeActualImpl.setBusinessUnitName(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setBusinessUnitName(businessUnitName);
        }

        if (addChgDelIndicator == null) {
            vwIvldAdjDemandForeActualImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (itemIdentifier == null) {
            vwIvldAdjDemandForeActualImpl.setItemIdentifier(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setItemIdentifier(itemIdentifier);
        }

        if (errorCode == null) {
            vwIvldAdjDemandForeActualImpl.setErrorCode(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setErrorCode(errorCode);
        }

        if (modifiedBy == null) {
            vwIvldAdjDemandForeActualImpl.setModifiedBy(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setModifiedBy(modifiedBy);
        }

        if (marketShareUnits == null) {
            vwIvldAdjDemandForeActualImpl.setMarketShareUnits(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setMarketShareUnits(marketShareUnits);
        }

        if (month == null) {
            vwIvldAdjDemandForeActualImpl.setMonth(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setMonth(month);
        }

        if (inventoryUnitChange == null) {
            vwIvldAdjDemandForeActualImpl.setInventoryUnitChange(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setInventoryUnitChange(inventoryUnitChange);
        }

        if (reprocessedFlag == null) {
            vwIvldAdjDemandForeActualImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (uncapturedUnitsRatio == null) {
            vwIvldAdjDemandForeActualImpl.setUncapturedUnitsRatio(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setUncapturedUnitsRatio(uncapturedUnitsRatio);
        }

        if (reasonForFailure == null) {
            vwIvldAdjDemandForeActualImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setReasonForFailure(reasonForFailure);
        }

        if (adjustedDemandForecastIntfId == null) {
            vwIvldAdjDemandForeActualImpl.setAdjustedDemandForecastIntfId(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setAdjustedDemandForecastIntfId(adjustedDemandForecastIntfId);
        }

        if (country == null) {
            vwIvldAdjDemandForeActualImpl.setCountry(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setCountry(country);
        }

        if (forecastType == null) {
            vwIvldAdjDemandForeActualImpl.setForecastType(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setForecastType(forecastType);
        }

        if (totalAdjustedDemandUnits == null) {
            vwIvldAdjDemandForeActualImpl.setTotalAdjustedDemandUnits(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setTotalAdjustedDemandUnits(totalAdjustedDemandUnits);
        }

        if (brandId == null) {
            vwIvldAdjDemandForeActualImpl.setBrandId(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setBrandId(brandId);
        }

        if (isForecast == null) {
            vwIvldAdjDemandForeActualImpl.setIsForecast(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setIsForecast(isForecast);
        }

        if (totalAdjustedDemandAmount == null) {
            vwIvldAdjDemandForeActualImpl.setTotalAdjustedDemandAmount(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setTotalAdjustedDemandAmount(totalAdjustedDemandAmount);
        }

        if (uncapturedUnits == null) {
            vwIvldAdjDemandForeActualImpl.setUncapturedUnits(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setUncapturedUnits(uncapturedUnits);
        }

        if (grossPrice == null) {
            vwIvldAdjDemandForeActualImpl.setGrossPrice(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setGrossPrice(grossPrice);
        }

        if (grossAmount == null) {
            vwIvldAdjDemandForeActualImpl.setGrossAmount(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setGrossAmount(grossAmount);
        }

        if (itemIdentifierCodeQualifier == null) {
            vwIvldAdjDemandForeActualImpl.setItemIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        if (batchId == null) {
            vwIvldAdjDemandForeActualImpl.setBatchId(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setBatchId(batchId);
        }

        if (itemName == null) {
            vwIvldAdjDemandForeActualImpl.setItemName(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setItemName(itemName);
        }

        if (errorField == null) {
            vwIvldAdjDemandForeActualImpl.setErrorField(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setErrorField(errorField);
        }

        if (netSalesPrice == null) {
            vwIvldAdjDemandForeActualImpl.setNetSalesPrice(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setNetSalesPrice(netSalesPrice);
        }

        if (netSalesAmount == null) {
            vwIvldAdjDemandForeActualImpl.setNetSalesAmount(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setNetSalesAmount(netSalesAmount);
        }

        if (segment == null) {
            vwIvldAdjDemandForeActualImpl.setSegment(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setSegment(segment);
        }

        if (forecastName == null) {
            vwIvldAdjDemandForeActualImpl.setForecastName(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setForecastName(forecastName);
        }

        vwIvldAdjDemandForeActualImpl.setIvldAdjustedDemandForecastSid(ivldAdjustedDemandForecastSid);

        if (marketSizeUnits == null) {
            vwIvldAdjDemandForeActualImpl.setMarketSizeUnits(StringPool.BLANK);
        } else {
            vwIvldAdjDemandForeActualImpl.setMarketSizeUnits(marketSizeUnits);
        }

        vwIvldAdjDemandForeActualImpl.setCheckRecord(checkRecord);

        vwIvldAdjDemandForeActualImpl.resetOriginalValues();

        return vwIvldAdjDemandForeActualImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        forecastVersion = objectInput.readUTF();
        grossUnits = objectInput.readUTF();
        businessUnitNo = objectInput.readUTF();
        year = objectInput.readUTF();
        itemId = objectInput.readUTF();
        brandName = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        organizationKey = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        source = objectInput.readUTF();
        marketShareRatio = objectInput.readUTF();
        businessUnitName = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        itemIdentifier = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        marketShareUnits = objectInput.readUTF();
        month = objectInput.readUTF();
        inventoryUnitChange = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        uncapturedUnitsRatio = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        adjustedDemandForecastIntfId = objectInput.readUTF();
        country = objectInput.readUTF();
        forecastType = objectInput.readUTF();
        totalAdjustedDemandUnits = objectInput.readUTF();
        brandId = objectInput.readUTF();
        isForecast = objectInput.readUTF();
        totalAdjustedDemandAmount = objectInput.readUTF();
        uncapturedUnits = objectInput.readUTF();
        grossPrice = objectInput.readUTF();
        grossAmount = objectInput.readUTF();
        itemIdentifierCodeQualifier = objectInput.readUTF();
        batchId = objectInput.readUTF();
        itemName = objectInput.readUTF();
        errorField = objectInput.readUTF();
        netSalesPrice = objectInput.readUTF();
        netSalesAmount = objectInput.readUTF();
        segment = objectInput.readUTF();
        forecastName = objectInput.readUTF();
        ivldAdjustedDemandForecastSid = objectInput.readInt();
        marketSizeUnits = objectInput.readUTF();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (forecastVersion == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastVersion);
        }

        if (grossUnits == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(grossUnits);
        }

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

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        if (brandName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandName);
        }

        objectOutput.writeLong(modifiedDate);

        if (organizationKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(organizationKey);
        }

        objectOutput.writeLong(createdDate);

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (marketShareRatio == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(marketShareRatio);
        }

        if (businessUnitName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(businessUnitName);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        if (itemIdentifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdentifier);
        }

        if (errorCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorCode);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        if (marketShareUnits == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(marketShareUnits);
        }

        if (month == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(month);
        }

        if (inventoryUnitChange == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inventoryUnitChange);
        }

        if (reprocessedFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reprocessedFlag);
        }

        if (uncapturedUnitsRatio == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uncapturedUnitsRatio);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (adjustedDemandForecastIntfId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustedDemandForecastIntfId);
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

        if (totalAdjustedDemandUnits == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(totalAdjustedDemandUnits);
        }

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

        if (totalAdjustedDemandAmount == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(totalAdjustedDemandAmount);
        }

        if (uncapturedUnits == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(uncapturedUnits);
        }

        if (grossPrice == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(grossPrice);
        }

        if (grossAmount == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(grossAmount);
        }

        if (itemIdentifierCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdentifierCodeQualifier);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (itemName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemName);
        }

        if (errorField == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorField);
        }

        if (netSalesPrice == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(netSalesPrice);
        }

        if (netSalesAmount == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(netSalesAmount);
        }

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

        objectOutput.writeInt(ivldAdjustedDemandForecastSid);

        if (marketSizeUnits == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(marketSizeUnits);
        }

        objectOutput.writeBoolean(checkRecord);
    }
}
