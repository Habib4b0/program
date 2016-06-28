package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.IvldCustomerGtsForecast;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldCustomerGtsForecast in entity cache.
 *
 * @author
 * @see IvldCustomerGtsForecast
 * @generated
 */
public class IvldCustomerGtsForecastCacheModel implements CacheModel<IvldCustomerGtsForecast>,
    Externalizable {
    public String price;
    public String forecastYear;
    public String deductionAmount;
    public int ivldCustomerGtsForecastSid;
    public String deductionId;
    public long forecastDate;
    public String itemId;
    public long modifiedDate;
    public String source;
    public long createdDate;
    public String createdBy;
    public String addChgDelIndicator;
    public String errorCode;
    public long intfInsertedDate;
    public String modifiedBy;
    public String salesAmount;
    public String reprocessedFlag;
    public String udc6;
    public String udc5;
    public String deductionType;
    public String udc4;
    public String udc1;
    public String units;
    public String deductionRate;
    public String udc2;
    public String udc3;
    public String reasonForFailure;
    public String country;
    public String companyId;
    public String forecastValueType;
    public String deductionCategory;
    public String adjustmentCode;
    public String deductionProgramType;
    public int customerGtsForecastIntfId;
    public String salesInclusion;
    public String forecastVer;
    public String batchId;
    public String priceType;
    public String forecastMonth;
    public String deductionInclusion;
    public String errorField;
    public String segment;
    public String brand;
    public String forecastName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(89);

        sb.append("{price=");
        sb.append(price);
        sb.append(", forecastYear=");
        sb.append(forecastYear);
        sb.append(", deductionAmount=");
        sb.append(deductionAmount);
        sb.append(", ivldCustomerGtsForecastSid=");
        sb.append(ivldCustomerGtsForecastSid);
        sb.append(", deductionId=");
        sb.append(deductionId);
        sb.append(", forecastDate=");
        sb.append(forecastDate);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", salesAmount=");
        sb.append(salesAmount);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", udc6=");
        sb.append(udc6);
        sb.append(", udc5=");
        sb.append(udc5);
        sb.append(", deductionType=");
        sb.append(deductionType);
        sb.append(", udc4=");
        sb.append(udc4);
        sb.append(", udc1=");
        sb.append(udc1);
        sb.append(", units=");
        sb.append(units);
        sb.append(", deductionRate=");
        sb.append(deductionRate);
        sb.append(", udc2=");
        sb.append(udc2);
        sb.append(", udc3=");
        sb.append(udc3);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", country=");
        sb.append(country);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", forecastValueType=");
        sb.append(forecastValueType);
        sb.append(", deductionCategory=");
        sb.append(deductionCategory);
        sb.append(", adjustmentCode=");
        sb.append(adjustmentCode);
        sb.append(", deductionProgramType=");
        sb.append(deductionProgramType);
        sb.append(", customerGtsForecastIntfId=");
        sb.append(customerGtsForecastIntfId);
        sb.append(", salesInclusion=");
        sb.append(salesInclusion);
        sb.append(", forecastVer=");
        sb.append(forecastVer);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", priceType=");
        sb.append(priceType);
        sb.append(", forecastMonth=");
        sb.append(forecastMonth);
        sb.append(", deductionInclusion=");
        sb.append(deductionInclusion);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", segment=");
        sb.append(segment);
        sb.append(", brand=");
        sb.append(brand);
        sb.append(", forecastName=");
        sb.append(forecastName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldCustomerGtsForecast toEntityModel() {
        IvldCustomerGtsForecastImpl ivldCustomerGtsForecastImpl = new IvldCustomerGtsForecastImpl();

        if (price == null) {
            ivldCustomerGtsForecastImpl.setPrice(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setPrice(price);
        }

        if (forecastYear == null) {
            ivldCustomerGtsForecastImpl.setForecastYear(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setForecastYear(forecastYear);
        }

        if (deductionAmount == null) {
            ivldCustomerGtsForecastImpl.setDeductionAmount(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setDeductionAmount(deductionAmount);
        }

        ivldCustomerGtsForecastImpl.setIvldCustomerGtsForecastSid(ivldCustomerGtsForecastSid);

        if (deductionId == null) {
            ivldCustomerGtsForecastImpl.setDeductionId(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setDeductionId(deductionId);
        }

        if (forecastDate == Long.MIN_VALUE) {
            ivldCustomerGtsForecastImpl.setForecastDate(null);
        } else {
            ivldCustomerGtsForecastImpl.setForecastDate(new Date(forecastDate));
        }

        if (itemId == null) {
            ivldCustomerGtsForecastImpl.setItemId(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setItemId(itemId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldCustomerGtsForecastImpl.setModifiedDate(null);
        } else {
            ivldCustomerGtsForecastImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (source == null) {
            ivldCustomerGtsForecastImpl.setSource(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setSource(source);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldCustomerGtsForecastImpl.setCreatedDate(null);
        } else {
            ivldCustomerGtsForecastImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            ivldCustomerGtsForecastImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setCreatedBy(createdBy);
        }

        if (addChgDelIndicator == null) {
            ivldCustomerGtsForecastImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (errorCode == null) {
            ivldCustomerGtsForecastImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setErrorCode(errorCode);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldCustomerGtsForecastImpl.setIntfInsertedDate(null);
        } else {
            ivldCustomerGtsForecastImpl.setIntfInsertedDate(new Date(
                    intfInsertedDate));
        }

        if (modifiedBy == null) {
            ivldCustomerGtsForecastImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setModifiedBy(modifiedBy);
        }

        if (salesAmount == null) {
            ivldCustomerGtsForecastImpl.setSalesAmount(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setSalesAmount(salesAmount);
        }

        if (reprocessedFlag == null) {
            ivldCustomerGtsForecastImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (udc6 == null) {
            ivldCustomerGtsForecastImpl.setUdc6(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setUdc6(udc6);
        }

        if (udc5 == null) {
            ivldCustomerGtsForecastImpl.setUdc5(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setUdc5(udc5);
        }

        if (deductionType == null) {
            ivldCustomerGtsForecastImpl.setDeductionType(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setDeductionType(deductionType);
        }

        if (udc4 == null) {
            ivldCustomerGtsForecastImpl.setUdc4(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setUdc4(udc4);
        }

        if (udc1 == null) {
            ivldCustomerGtsForecastImpl.setUdc1(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setUdc1(udc1);
        }

        if (units == null) {
            ivldCustomerGtsForecastImpl.setUnits(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setUnits(units);
        }

        if (deductionRate == null) {
            ivldCustomerGtsForecastImpl.setDeductionRate(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setDeductionRate(deductionRate);
        }

        if (udc2 == null) {
            ivldCustomerGtsForecastImpl.setUdc2(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setUdc2(udc2);
        }

        if (udc3 == null) {
            ivldCustomerGtsForecastImpl.setUdc3(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setUdc3(udc3);
        }

        if (reasonForFailure == null) {
            ivldCustomerGtsForecastImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setReasonForFailure(reasonForFailure);
        }

        if (country == null) {
            ivldCustomerGtsForecastImpl.setCountry(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setCountry(country);
        }

        if (companyId == null) {
            ivldCustomerGtsForecastImpl.setCompanyId(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setCompanyId(companyId);
        }

        if (forecastValueType == null) {
            ivldCustomerGtsForecastImpl.setForecastValueType(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setForecastValueType(forecastValueType);
        }

        if (deductionCategory == null) {
            ivldCustomerGtsForecastImpl.setDeductionCategory(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setDeductionCategory(deductionCategory);
        }

        if (adjustmentCode == null) {
            ivldCustomerGtsForecastImpl.setAdjustmentCode(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setAdjustmentCode(adjustmentCode);
        }

        if (deductionProgramType == null) {
            ivldCustomerGtsForecastImpl.setDeductionProgramType(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setDeductionProgramType(deductionProgramType);
        }

        ivldCustomerGtsForecastImpl.setCustomerGtsForecastIntfId(customerGtsForecastIntfId);

        if (salesInclusion == null) {
            ivldCustomerGtsForecastImpl.setSalesInclusion(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setSalesInclusion(salesInclusion);
        }

        if (forecastVer == null) {
            ivldCustomerGtsForecastImpl.setForecastVer(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setForecastVer(forecastVer);
        }

        if (batchId == null) {
            ivldCustomerGtsForecastImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setBatchId(batchId);
        }

        if (priceType == null) {
            ivldCustomerGtsForecastImpl.setPriceType(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setPriceType(priceType);
        }

        if (forecastMonth == null) {
            ivldCustomerGtsForecastImpl.setForecastMonth(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setForecastMonth(forecastMonth);
        }

        if (deductionInclusion == null) {
            ivldCustomerGtsForecastImpl.setDeductionInclusion(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setDeductionInclusion(deductionInclusion);
        }

        if (errorField == null) {
            ivldCustomerGtsForecastImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setErrorField(errorField);
        }

        if (segment == null) {
            ivldCustomerGtsForecastImpl.setSegment(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setSegment(segment);
        }

        if (brand == null) {
            ivldCustomerGtsForecastImpl.setBrand(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setBrand(brand);
        }

        if (forecastName == null) {
            ivldCustomerGtsForecastImpl.setForecastName(StringPool.BLANK);
        } else {
            ivldCustomerGtsForecastImpl.setForecastName(forecastName);
        }

        ivldCustomerGtsForecastImpl.resetOriginalValues();

        return ivldCustomerGtsForecastImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        price = objectInput.readUTF();
        forecastYear = objectInput.readUTF();
        deductionAmount = objectInput.readUTF();
        ivldCustomerGtsForecastSid = objectInput.readInt();
        deductionId = objectInput.readUTF();
        forecastDate = objectInput.readLong();
        itemId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        source = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        salesAmount = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        udc6 = objectInput.readUTF();
        udc5 = objectInput.readUTF();
        deductionType = objectInput.readUTF();
        udc4 = objectInput.readUTF();
        udc1 = objectInput.readUTF();
        units = objectInput.readUTF();
        deductionRate = objectInput.readUTF();
        udc2 = objectInput.readUTF();
        udc3 = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        country = objectInput.readUTF();
        companyId = objectInput.readUTF();
        forecastValueType = objectInput.readUTF();
        deductionCategory = objectInput.readUTF();
        adjustmentCode = objectInput.readUTF();
        deductionProgramType = objectInput.readUTF();
        customerGtsForecastIntfId = objectInput.readInt();
        salesInclusion = objectInput.readUTF();
        forecastVer = objectInput.readUTF();
        batchId = objectInput.readUTF();
        priceType = objectInput.readUTF();
        forecastMonth = objectInput.readUTF();
        deductionInclusion = objectInput.readUTF();
        errorField = objectInput.readUTF();
        segment = objectInput.readUTF();
        brand = objectInput.readUTF();
        forecastName = objectInput.readUTF();
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

        if (deductionAmount == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionAmount);
        }

        objectOutput.writeInt(ivldCustomerGtsForecastSid);

        if (deductionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionId);
        }

        objectOutput.writeLong(forecastDate);

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        objectOutput.writeLong(modifiedDate);

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

        if (salesAmount == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(salesAmount);
        }

        if (reprocessedFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reprocessedFlag);
        }

        if (udc6 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc6);
        }

        if (udc5 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc5);
        }

        if (deductionType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionType);
        }

        if (udc4 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc4);
        }

        if (udc1 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc1);
        }

        if (units == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(units);
        }

        if (deductionRate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionRate);
        }

        if (udc2 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc2);
        }

        if (udc3 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(udc3);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (country == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(country);
        }

        if (companyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyId);
        }

        if (forecastValueType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastValueType);
        }

        if (deductionCategory == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionCategory);
        }

        if (adjustmentCode == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjustmentCode);
        }

        if (deductionProgramType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionProgramType);
        }

        objectOutput.writeInt(customerGtsForecastIntfId);

        if (salesInclusion == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(salesInclusion);
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

        if (priceType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceType);
        }

        if (forecastMonth == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastMonth);
        }

        if (deductionInclusion == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionInclusion);
        }

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

        if (forecastName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastName);
        }
    }
}
