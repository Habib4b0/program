package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CustomerGtsForecast;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CustomerGtsForecast in entity cache.
 *
 * @author
 * @see CustomerGtsForecast
 * @generated
 */
public class CustomerGtsForecastCacheModel implements CacheModel<CustomerGtsForecast>,
    Externalizable {
    public String price;
    public int itemMasterSid;
    public String forecastYear;
    public String deductionAmount;
    public String deductionId;
    public long forecastDate;
    public String itemId;
    public long modifiedDate;
    public int brandMasterSid;
    public String source;
    public long createdDate;
    public String createdBy;
    public String addChgDelIndicator;
    public String inboundStatus;
    public String modifiedBy;
    public String salesAmount;
    public String deductionType;
    public int companyMasterSid;
    public String units;
    public String deductionRate;
    public int customerGtsForecastSid;
    public String country;
    public String companyId;
    public String forecastValueType;
    public String deductionCategory;
    public String adjustmentCode;
    public String deductionProgramType;
    public boolean recordLockStatus;
    public String salesInclusion;
    public String forecastVer;
    public String batchId;
    public String priceType;
    public String forecastMonth;
    public String deductionInclusion;
    public String segment;
    public String brand;
    public String forecastName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(75);

        sb.append("{price=");
        sb.append(price);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", forecastYear=");
        sb.append(forecastYear);
        sb.append(", deductionAmount=");
        sb.append(deductionAmount);
        sb.append(", deductionId=");
        sb.append(deductionId);
        sb.append(", forecastDate=");
        sb.append(forecastDate);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", brandMasterSid=");
        sb.append(brandMasterSid);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", salesAmount=");
        sb.append(salesAmount);
        sb.append(", deductionType=");
        sb.append(deductionType);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append(", units=");
        sb.append(units);
        sb.append(", deductionRate=");
        sb.append(deductionRate);
        sb.append(", customerGtsForecastSid=");
        sb.append(customerGtsForecastSid);
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
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
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
    public CustomerGtsForecast toEntityModel() {
        CustomerGtsForecastImpl customerGtsForecastImpl = new CustomerGtsForecastImpl();

        if (price == null) {
            customerGtsForecastImpl.setPrice(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setPrice(price);
        }

        customerGtsForecastImpl.setItemMasterSid(itemMasterSid);

        if (forecastYear == null) {
            customerGtsForecastImpl.setForecastYear(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setForecastYear(forecastYear);
        }

        if (deductionAmount == null) {
            customerGtsForecastImpl.setDeductionAmount(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setDeductionAmount(deductionAmount);
        }

        if (deductionId == null) {
            customerGtsForecastImpl.setDeductionId(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setDeductionId(deductionId);
        }

        if (forecastDate == Long.MIN_VALUE) {
            customerGtsForecastImpl.setForecastDate(null);
        } else {
            customerGtsForecastImpl.setForecastDate(new Date(forecastDate));
        }

        if (itemId == null) {
            customerGtsForecastImpl.setItemId(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setItemId(itemId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            customerGtsForecastImpl.setModifiedDate(null);
        } else {
            customerGtsForecastImpl.setModifiedDate(new Date(modifiedDate));
        }

        customerGtsForecastImpl.setBrandMasterSid(brandMasterSid);

        if (source == null) {
            customerGtsForecastImpl.setSource(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setSource(source);
        }

        if (createdDate == Long.MIN_VALUE) {
            customerGtsForecastImpl.setCreatedDate(null);
        } else {
            customerGtsForecastImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            customerGtsForecastImpl.setCreatedBy(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setCreatedBy(createdBy);
        }

        if (addChgDelIndicator == null) {
            customerGtsForecastImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (inboundStatus == null) {
            customerGtsForecastImpl.setInboundStatus(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setInboundStatus(inboundStatus);
        }

        if (modifiedBy == null) {
            customerGtsForecastImpl.setModifiedBy(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setModifiedBy(modifiedBy);
        }

        if (salesAmount == null) {
            customerGtsForecastImpl.setSalesAmount(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setSalesAmount(salesAmount);
        }

        if (deductionType == null) {
            customerGtsForecastImpl.setDeductionType(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setDeductionType(deductionType);
        }

        customerGtsForecastImpl.setCompanyMasterSid(companyMasterSid);

        if (units == null) {
            customerGtsForecastImpl.setUnits(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setUnits(units);
        }

        if (deductionRate == null) {
            customerGtsForecastImpl.setDeductionRate(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setDeductionRate(deductionRate);
        }

        customerGtsForecastImpl.setCustomerGtsForecastSid(customerGtsForecastSid);

        if (country == null) {
            customerGtsForecastImpl.setCountry(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setCountry(country);
        }

        if (companyId == null) {
            customerGtsForecastImpl.setCompanyId(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setCompanyId(companyId);
        }

        if (forecastValueType == null) {
            customerGtsForecastImpl.setForecastValueType(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setForecastValueType(forecastValueType);
        }

        if (deductionCategory == null) {
            customerGtsForecastImpl.setDeductionCategory(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setDeductionCategory(deductionCategory);
        }

        if (adjustmentCode == null) {
            customerGtsForecastImpl.setAdjustmentCode(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setAdjustmentCode(adjustmentCode);
        }

        if (deductionProgramType == null) {
            customerGtsForecastImpl.setDeductionProgramType(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setDeductionProgramType(deductionProgramType);
        }

        customerGtsForecastImpl.setRecordLockStatus(recordLockStatus);

        if (salesInclusion == null) {
            customerGtsForecastImpl.setSalesInclusion(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setSalesInclusion(salesInclusion);
        }

        if (forecastVer == null) {
            customerGtsForecastImpl.setForecastVer(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setForecastVer(forecastVer);
        }

        if (batchId == null) {
            customerGtsForecastImpl.setBatchId(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setBatchId(batchId);
        }

        if (priceType == null) {
            customerGtsForecastImpl.setPriceType(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setPriceType(priceType);
        }

        if (forecastMonth == null) {
            customerGtsForecastImpl.setForecastMonth(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setForecastMonth(forecastMonth);
        }

        if (deductionInclusion == null) {
            customerGtsForecastImpl.setDeductionInclusion(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setDeductionInclusion(deductionInclusion);
        }

        if (segment == null) {
            customerGtsForecastImpl.setSegment(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setSegment(segment);
        }

        if (brand == null) {
            customerGtsForecastImpl.setBrand(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setBrand(brand);
        }

        if (forecastName == null) {
            customerGtsForecastImpl.setForecastName(StringPool.BLANK);
        } else {
            customerGtsForecastImpl.setForecastName(forecastName);
        }

        customerGtsForecastImpl.resetOriginalValues();

        return customerGtsForecastImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        price = objectInput.readUTF();
        itemMasterSid = objectInput.readInt();
        forecastYear = objectInput.readUTF();
        deductionAmount = objectInput.readUTF();
        deductionId = objectInput.readUTF();
        forecastDate = objectInput.readLong();
        itemId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        brandMasterSid = objectInput.readInt();
        source = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        salesAmount = objectInput.readUTF();
        deductionType = objectInput.readUTF();
        companyMasterSid = objectInput.readInt();
        units = objectInput.readUTF();
        deductionRate = objectInput.readUTF();
        customerGtsForecastSid = objectInput.readInt();
        country = objectInput.readUTF();
        companyId = objectInput.readUTF();
        forecastValueType = objectInput.readUTF();
        deductionCategory = objectInput.readUTF();
        adjustmentCode = objectInput.readUTF();
        deductionProgramType = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        salesInclusion = objectInput.readUTF();
        forecastVer = objectInput.readUTF();
        batchId = objectInput.readUTF();
        priceType = objectInput.readUTF();
        forecastMonth = objectInput.readUTF();
        deductionInclusion = objectInput.readUTF();
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

        objectOutput.writeInt(itemMasterSid);

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
        objectOutput.writeInt(brandMasterSid);

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

        if (salesAmount == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(salesAmount);
        }

        if (deductionType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(deductionType);
        }

        objectOutput.writeInt(companyMasterSid);

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

        objectOutput.writeInt(customerGtsForecastSid);

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

        objectOutput.writeBoolean(recordLockStatus);

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
