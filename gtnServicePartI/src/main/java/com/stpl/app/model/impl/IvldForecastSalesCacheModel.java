package com.stpl.app.model.impl;

import com.stpl.app.model.IvldForecastSales;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldForecastSales in entity cache.
 *
 * @author
 * @see IvldForecastSales
 * @generated
 */
public class IvldForecastSalesCacheModel implements CacheModel<IvldForecastSales>,
    Externalizable {
    public String price;
    public String forecastYear;
    public String forecastDate;
    public long modifiedDate;
    public String forecastValue;
    public String forecastIntfid;
    public String dollars;
    public String ndc;
    public String actualSalesPercentage;
    public String source;
    public long createdDate;
    public String createdBy;
    public String addChgDelIndicator;
    public String actualSalesPercentageMonth;
    public String errorCode;
    public long intfInsertedDate;
    public String modifiedBy;
    public String reprocessedFlag;
    public String percentageEstimate;
    public String percentageEstimateYear;
    public String units;
    public String reasonForFailure;
    public String forecastStartDate;
    public String forecastValueType;
    public String forecastedSalesPercentMonth;
    public String country;
    public String product;
    public String batchId;
    public String forecastVer;
    public String forecastMonth;
    public int ivldForecastSalesSid;
    public String errorField;
    public String segment;
    public String brand;
    public String forecastedSalesPercentage;
    public String forecastName;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(75);

        sb.append("{price=");
        sb.append(price);
        sb.append(", forecastYear=");
        sb.append(forecastYear);
        sb.append(", forecastDate=");
        sb.append(forecastDate);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", forecastValue=");
        sb.append(forecastValue);
        sb.append(", forecastIntfid=");
        sb.append(forecastIntfid);
        sb.append(", dollars=");
        sb.append(dollars);
        sb.append(", ndc=");
        sb.append(ndc);
        sb.append(", actualSalesPercentage=");
        sb.append(actualSalesPercentage);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", actualSalesPercentageMonth=");
        sb.append(actualSalesPercentageMonth);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", percentageEstimate=");
        sb.append(percentageEstimate);
        sb.append(", percentageEstimateYear=");
        sb.append(percentageEstimateYear);
        sb.append(", units=");
        sb.append(units);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", forecastStartDate=");
        sb.append(forecastStartDate);
        sb.append(", forecastValueType=");
        sb.append(forecastValueType);
        sb.append(", forecastedSalesPercentMonth=");
        sb.append(forecastedSalesPercentMonth);
        sb.append(", country=");
        sb.append(country);
        sb.append(", product=");
        sb.append(product);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", forecastVer=");
        sb.append(forecastVer);
        sb.append(", forecastMonth=");
        sb.append(forecastMonth);
        sb.append(", ivldForecastSalesSid=");
        sb.append(ivldForecastSalesSid);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", segment=");
        sb.append(segment);
        sb.append(", brand=");
        sb.append(brand);
        sb.append(", forecastedSalesPercentage=");
        sb.append(forecastedSalesPercentage);
        sb.append(", forecastName=");
        sb.append(forecastName);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldForecastSales toEntityModel() {
        IvldForecastSalesImpl ivldForecastSalesImpl = new IvldForecastSalesImpl();

        if (price == null) {
            ivldForecastSalesImpl.setPrice(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setPrice(price);
        }

        if (forecastYear == null) {
            ivldForecastSalesImpl.setForecastYear(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setForecastYear(forecastYear);
        }

        if (forecastDate == null) {
            ivldForecastSalesImpl.setForecastDate(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setForecastDate(forecastDate);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldForecastSalesImpl.setModifiedDate(null);
        } else {
            ivldForecastSalesImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (forecastValue == null) {
            ivldForecastSalesImpl.setForecastValue(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setForecastValue(forecastValue);
        }

        if (forecastIntfid == null) {
            ivldForecastSalesImpl.setForecastIntfid(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setForecastIntfid(forecastIntfid);
        }

        if (dollars == null) {
            ivldForecastSalesImpl.setDollars(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setDollars(dollars);
        }

        if (ndc == null) {
            ivldForecastSalesImpl.setNdc(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setNdc(ndc);
        }

        if (actualSalesPercentage == null) {
            ivldForecastSalesImpl.setActualSalesPercentage(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setActualSalesPercentage(actualSalesPercentage);
        }

        if (source == null) {
            ivldForecastSalesImpl.setSource(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setSource(source);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldForecastSalesImpl.setCreatedDate(null);
        } else {
            ivldForecastSalesImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            ivldForecastSalesImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setCreatedBy(createdBy);
        }

        if (addChgDelIndicator == null) {
            ivldForecastSalesImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (actualSalesPercentageMonth == null) {
            ivldForecastSalesImpl.setActualSalesPercentageMonth(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setActualSalesPercentageMonth(actualSalesPercentageMonth);
        }

        if (errorCode == null) {
            ivldForecastSalesImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setErrorCode(errorCode);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldForecastSalesImpl.setIntfInsertedDate(null);
        } else {
            ivldForecastSalesImpl.setIntfInsertedDate(new Date(intfInsertedDate));
        }

        if (modifiedBy == null) {
            ivldForecastSalesImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setModifiedBy(modifiedBy);
        }

        if (reprocessedFlag == null) {
            ivldForecastSalesImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (percentageEstimate == null) {
            ivldForecastSalesImpl.setPercentageEstimate(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setPercentageEstimate(percentageEstimate);
        }

        if (percentageEstimateYear == null) {
            ivldForecastSalesImpl.setPercentageEstimateYear(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setPercentageEstimateYear(percentageEstimateYear);
        }

        if (units == null) {
            ivldForecastSalesImpl.setUnits(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setUnits(units);
        }

        if (reasonForFailure == null) {
            ivldForecastSalesImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setReasonForFailure(reasonForFailure);
        }

        if (forecastStartDate == null) {
            ivldForecastSalesImpl.setForecastStartDate(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setForecastStartDate(forecastStartDate);
        }

        if (forecastValueType == null) {
            ivldForecastSalesImpl.setForecastValueType(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setForecastValueType(forecastValueType);
        }

        if (forecastedSalesPercentMonth == null) {
            ivldForecastSalesImpl.setForecastedSalesPercentMonth(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setForecastedSalesPercentMonth(forecastedSalesPercentMonth);
        }

        if (country == null) {
            ivldForecastSalesImpl.setCountry(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setCountry(country);
        }

        if (product == null) {
            ivldForecastSalesImpl.setProduct(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setProduct(product);
        }

        if (batchId == null) {
            ivldForecastSalesImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setBatchId(batchId);
        }

        if (forecastVer == null) {
            ivldForecastSalesImpl.setForecastVer(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setForecastVer(forecastVer);
        }

        if (forecastMonth == null) {
            ivldForecastSalesImpl.setForecastMonth(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setForecastMonth(forecastMonth);
        }

        ivldForecastSalesImpl.setIvldForecastSalesSid(ivldForecastSalesSid);

        if (errorField == null) {
            ivldForecastSalesImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setErrorField(errorField);
        }

        if (segment == null) {
            ivldForecastSalesImpl.setSegment(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setSegment(segment);
        }

        if (brand == null) {
            ivldForecastSalesImpl.setBrand(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setBrand(brand);
        }

        if (forecastedSalesPercentage == null) {
            ivldForecastSalesImpl.setForecastedSalesPercentage(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setForecastedSalesPercentage(forecastedSalesPercentage);
        }

        if (forecastName == null) {
            ivldForecastSalesImpl.setForecastName(StringPool.BLANK);
        } else {
            ivldForecastSalesImpl.setForecastName(forecastName);
        }

        ivldForecastSalesImpl.setCheckRecord(checkRecord);

        ivldForecastSalesImpl.resetOriginalValues();

        return ivldForecastSalesImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        price = objectInput.readUTF();
        forecastYear = objectInput.readUTF();
        forecastDate = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        forecastValue = objectInput.readUTF();
        forecastIntfid = objectInput.readUTF();
        dollars = objectInput.readUTF();
        ndc = objectInput.readUTF();
        actualSalesPercentage = objectInput.readUTF();
        source = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        actualSalesPercentageMonth = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        percentageEstimate = objectInput.readUTF();
        percentageEstimateYear = objectInput.readUTF();
        units = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        forecastStartDate = objectInput.readUTF();
        forecastValueType = objectInput.readUTF();
        forecastedSalesPercentMonth = objectInput.readUTF();
        country = objectInput.readUTF();
        product = objectInput.readUTF();
        batchId = objectInput.readUTF();
        forecastVer = objectInput.readUTF();
        forecastMonth = objectInput.readUTF();
        ivldForecastSalesSid = objectInput.readInt();
        errorField = objectInput.readUTF();
        segment = objectInput.readUTF();
        brand = objectInput.readUTF();
        forecastedSalesPercentage = objectInput.readUTF();
        forecastName = objectInput.readUTF();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (price == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(price);
        }

        if (forecastYear == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastYear);
        }

        if (forecastDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastDate);
        }

        objectOutput.writeLong(modifiedDate);

        if (forecastValue == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastValue);
        }

        if (forecastIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastIntfid);
        }

        if (dollars == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(dollars);
        }

        if (ndc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ndc);
        }

        if (actualSalesPercentage == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actualSalesPercentage);
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

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        if (actualSalesPercentageMonth == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actualSalesPercentageMonth);
        }

        if (errorCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorCode);
        }

        objectOutput.writeLong(intfInsertedDate);

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        if (reprocessedFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reprocessedFlag);
        }

        if (percentageEstimate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(percentageEstimate);
        }

        if (percentageEstimateYear == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(percentageEstimateYear);
        }

        if (units == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(units);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (forecastStartDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastStartDate);
        }

        if (forecastValueType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastValueType);
        }

        if (forecastedSalesPercentMonth == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastedSalesPercentMonth);
        }

        if (country == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(country);
        }

        if (product == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(product);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (forecastVer == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastVer);
        }

        if (forecastMonth == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastMonth);
        }

        objectOutput.writeInt(ivldForecastSalesSid);

        if (errorField == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorField);
        }

        if (segment == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(segment);
        }

        if (brand == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brand);
        }

        if (forecastedSalesPercentage == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastedSalesPercentage);
        }

        if (forecastName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastName);
        }

        objectOutput.writeBoolean(checkRecord);
    }
}
