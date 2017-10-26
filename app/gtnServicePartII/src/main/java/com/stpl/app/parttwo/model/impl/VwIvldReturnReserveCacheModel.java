package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.VwIvldReturnReserve;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwIvldReturnReserve in entity cache.
 *
 * @author
 * @see VwIvldReturnReserve
 * @generated
 */
public class VwIvldReturnReserveCacheModel implements CacheModel<VwIvldReturnReserve>,
    Externalizable {
    public int ivldReturnReserveSid;
    public String companyName;
    public String year;
    public String project;
    public String itemId;
    public String brandName;
    public long modifiedDate;
    public String account;
    public String returnReserveIntfId;
    public String source;
    public long createdDate;
    public String createdBy;
    public String businessUnit;
    public String businessUnitName;
    public String addChgDelIndicator;
    public String errorCode;
    public long intfInsertedDate;
    public String modifiedBy;
    public String itemNo;
    public String month;
    public String reprocessedFlag;
    public String udc6;
    public String udc5;
    public String udc4;
    public String udc1;
    public String units;
    public String udc2;
    public String udc3;
    public String reasonForFailure;
    public String country;
    public String companyId;
    public String costCenter;
    public String glCompany;
    public String brandId;
    public String future1;
    public String future2;
    public String amount;
    public String division;
    public String companyNo;
    public String batchId;
    public String itemName;
    public String errorField;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(87);

        sb.append("{ivldReturnReserveSid=");
        sb.append(ivldReturnReserveSid);
        sb.append(", companyName=");
        sb.append(companyName);
        sb.append(", year=");
        sb.append(year);
        sb.append(", project=");
        sb.append(project);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", brandName=");
        sb.append(brandName);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", account=");
        sb.append(account);
        sb.append(", returnReserveIntfId=");
        sb.append(returnReserveIntfId);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", businessUnit=");
        sb.append(businessUnit);
        sb.append(", businessUnitName=");
        sb.append(businessUnitName);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", itemNo=");
        sb.append(itemNo);
        sb.append(", month=");
        sb.append(month);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", udc6=");
        sb.append(udc6);
        sb.append(", udc5=");
        sb.append(udc5);
        sb.append(", udc4=");
        sb.append(udc4);
        sb.append(", udc1=");
        sb.append(udc1);
        sb.append(", units=");
        sb.append(units);
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
        sb.append(", costCenter=");
        sb.append(costCenter);
        sb.append(", glCompany=");
        sb.append(glCompany);
        sb.append(", brandId=");
        sb.append(brandId);
        sb.append(", future1=");
        sb.append(future1);
        sb.append(", future2=");
        sb.append(future2);
        sb.append(", amount=");
        sb.append(amount);
        sb.append(", division=");
        sb.append(division);
        sb.append(", companyNo=");
        sb.append(companyNo);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public VwIvldReturnReserve toEntityModel() {
        VwIvldReturnReserveImpl vwIvldReturnReserveImpl = new VwIvldReturnReserveImpl();

        vwIvldReturnReserveImpl.setIvldReturnReserveSid(ivldReturnReserveSid);

        if (companyName == null) {
            vwIvldReturnReserveImpl.setCompanyName(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setCompanyName(companyName);
        }

        if (year == null) {
            vwIvldReturnReserveImpl.setYear(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setYear(year);
        }

        if (project == null) {
            vwIvldReturnReserveImpl.setProject(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setProject(project);
        }

        if (itemId == null) {
            vwIvldReturnReserveImpl.setItemId(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setItemId(itemId);
        }

        if (brandName == null) {
            vwIvldReturnReserveImpl.setBrandName(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setBrandName(brandName);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            vwIvldReturnReserveImpl.setModifiedDate(null);
        } else {
            vwIvldReturnReserveImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (account == null) {
            vwIvldReturnReserveImpl.setAccount(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setAccount(account);
        }

        if (returnReserveIntfId == null) {
            vwIvldReturnReserveImpl.setReturnReserveIntfId(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setReturnReserveIntfId(returnReserveIntfId);
        }

        if (source == null) {
            vwIvldReturnReserveImpl.setSource(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setSource(source);
        }

        if (createdDate == Long.MIN_VALUE) {
            vwIvldReturnReserveImpl.setCreatedDate(null);
        } else {
            vwIvldReturnReserveImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            vwIvldReturnReserveImpl.setCreatedBy(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setCreatedBy(createdBy);
        }

        if (businessUnit == null) {
            vwIvldReturnReserveImpl.setBusinessUnit(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setBusinessUnit(businessUnit);
        }

        if (businessUnitName == null) {
            vwIvldReturnReserveImpl.setBusinessUnitName(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setBusinessUnitName(businessUnitName);
        }

        if (addChgDelIndicator == null) {
            vwIvldReturnReserveImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (errorCode == null) {
            vwIvldReturnReserveImpl.setErrorCode(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setErrorCode(errorCode);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            vwIvldReturnReserveImpl.setIntfInsertedDate(null);
        } else {
            vwIvldReturnReserveImpl.setIntfInsertedDate(new Date(
                    intfInsertedDate));
        }

        if (modifiedBy == null) {
            vwIvldReturnReserveImpl.setModifiedBy(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setModifiedBy(modifiedBy);
        }

        if (itemNo == null) {
            vwIvldReturnReserveImpl.setItemNo(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setItemNo(itemNo);
        }

        if (month == null) {
            vwIvldReturnReserveImpl.setMonth(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setMonth(month);
        }

        if (reprocessedFlag == null) {
            vwIvldReturnReserveImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (udc6 == null) {
            vwIvldReturnReserveImpl.setUdc6(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setUdc6(udc6);
        }

        if (udc5 == null) {
            vwIvldReturnReserveImpl.setUdc5(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setUdc5(udc5);
        }

        if (udc4 == null) {
            vwIvldReturnReserveImpl.setUdc4(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setUdc4(udc4);
        }

        if (udc1 == null) {
            vwIvldReturnReserveImpl.setUdc1(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setUdc1(udc1);
        }

        if (units == null) {
            vwIvldReturnReserveImpl.setUnits(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setUnits(units);
        }

        if (udc2 == null) {
            vwIvldReturnReserveImpl.setUdc2(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setUdc2(udc2);
        }

        if (udc3 == null) {
            vwIvldReturnReserveImpl.setUdc3(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setUdc3(udc3);
        }

        if (reasonForFailure == null) {
            vwIvldReturnReserveImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setReasonForFailure(reasonForFailure);
        }

        if (country == null) {
            vwIvldReturnReserveImpl.setCountry(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setCountry(country);
        }

        if (companyId == null) {
            vwIvldReturnReserveImpl.setCompanyId(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setCompanyId(companyId);
        }

        if (costCenter == null) {
            vwIvldReturnReserveImpl.setCostCenter(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setCostCenter(costCenter);
        }

        if (glCompany == null) {
            vwIvldReturnReserveImpl.setGlCompany(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setGlCompany(glCompany);
        }

        if (brandId == null) {
            vwIvldReturnReserveImpl.setBrandId(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setBrandId(brandId);
        }

        if (future1 == null) {
            vwIvldReturnReserveImpl.setFuture1(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setFuture1(future1);
        }

        if (future2 == null) {
            vwIvldReturnReserveImpl.setFuture2(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setFuture2(future2);
        }

        if (amount == null) {
            vwIvldReturnReserveImpl.setAmount(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setAmount(amount);
        }

        if (division == null) {
            vwIvldReturnReserveImpl.setDivision(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setDivision(division);
        }

        if (companyNo == null) {
            vwIvldReturnReserveImpl.setCompanyNo(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setCompanyNo(companyNo);
        }

        if (batchId == null) {
            vwIvldReturnReserveImpl.setBatchId(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setBatchId(batchId);
        }

        if (itemName == null) {
            vwIvldReturnReserveImpl.setItemName(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setItemName(itemName);
        }

        if (errorField == null) {
            vwIvldReturnReserveImpl.setErrorField(StringPool.BLANK);
        } else {
            vwIvldReturnReserveImpl.setErrorField(errorField);
        }

        vwIvldReturnReserveImpl.setCheckRecord(checkRecord);

        vwIvldReturnReserveImpl.resetOriginalValues();

        return vwIvldReturnReserveImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        ivldReturnReserveSid = objectInput.readInt();
        companyName = objectInput.readUTF();
        year = objectInput.readUTF();
        project = objectInput.readUTF();
        itemId = objectInput.readUTF();
        brandName = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        account = objectInput.readUTF();
        returnReserveIntfId = objectInput.readUTF();
        source = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        businessUnit = objectInput.readUTF();
        businessUnitName = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        itemNo = objectInput.readUTF();
        month = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        udc6 = objectInput.readUTF();
        udc5 = objectInput.readUTF();
        udc4 = objectInput.readUTF();
        udc1 = objectInput.readUTF();
        units = objectInput.readUTF();
        udc2 = objectInput.readUTF();
        udc3 = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        country = objectInput.readUTF();
        companyId = objectInput.readUTF();
        costCenter = objectInput.readUTF();
        glCompany = objectInput.readUTF();
        brandId = objectInput.readUTF();
        future1 = objectInput.readUTF();
        future2 = objectInput.readUTF();
        amount = objectInput.readUTF();
        division = objectInput.readUTF();
        companyNo = objectInput.readUTF();
        batchId = objectInput.readUTF();
        itemName = objectInput.readUTF();
        errorField = objectInput.readUTF();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(ivldReturnReserveSid);

        if (companyName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyName);
        }

        if (year == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(year);
        }

        if (project == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(project);
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

        if (account == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(account);
        }

        if (returnReserveIntfId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(returnReserveIntfId);
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

        if (businessUnit == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(businessUnit);
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

        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        if (month == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(month);
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

        if (costCenter == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(costCenter);
        }

        if (glCompany == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(glCompany);
        }

        if (brandId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brandId);
        }

        if (future1 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(future1);
        }

        if (future2 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(future2);
        }

        if (amount == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(amount);
        }

        if (division == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(division);
        }

        if (companyNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyNo);
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

        objectOutput.writeBoolean(checkRecord);
    }
}
