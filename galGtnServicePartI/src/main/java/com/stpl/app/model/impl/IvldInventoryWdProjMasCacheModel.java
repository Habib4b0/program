package com.stpl.app.model.impl;

import com.stpl.app.model.IvldInventoryWdProjMas;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldInventoryWdProjMas in entity cache.
 *
 * @author
 * @see IvldInventoryWdProjMas
 * @generated
 */
public class IvldInventoryWdProjMasCacheModel implements CacheModel<IvldInventoryWdProjMas>,
    Externalizable {
    public int inventoryWdProjMasIntfId;
    public String week;
    public String unitsWithdrawn;
    public String reasonForFailure;
    public String country;
    public String year;
    public String itemId;
    public long modifiedDate;
    public String organizationKey;
    public String itemIdentifierCodeQualifier;
    public String source;
    public long createdDate;
    public String createdBy;
    public String day;
    public String forecastVer;
    public String batchId;
    public String addChgDelIndicator;
    public String itemIdentifier;
    public String errorField;
    public String errorCode;
    public long intfInsertedDate;
    public String modifiedBy;
    public int ivldInventoryWdProjMasSid;
    public String month;
    public String reprocessedFlag;
    public String forecastName;
    public String amountWithdrawn;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(55);

        sb.append("{inventoryWdProjMasIntfId=");
        sb.append(inventoryWdProjMasIntfId);
        sb.append(", week=");
        sb.append(week);
        sb.append(", unitsWithdrawn=");
        sb.append(unitsWithdrawn);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", country=");
        sb.append(country);
        sb.append(", year=");
        sb.append(year);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(itemIdentifierCodeQualifier);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", day=");
        sb.append(day);
        sb.append(", forecastVer=");
        sb.append(forecastVer);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", itemIdentifier=");
        sb.append(itemIdentifier);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", ivldInventoryWdProjMasSid=");
        sb.append(ivldInventoryWdProjMasSid);
        sb.append(", month=");
        sb.append(month);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", forecastName=");
        sb.append(forecastName);
        sb.append(", amountWithdrawn=");
        sb.append(amountWithdrawn);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldInventoryWdProjMas toEntityModel() {
        IvldInventoryWdProjMasImpl ivldInventoryWdProjMasImpl = new IvldInventoryWdProjMasImpl();

        ivldInventoryWdProjMasImpl.setInventoryWdProjMasIntfId(inventoryWdProjMasIntfId);

        if (week == null) {
            ivldInventoryWdProjMasImpl.setWeek(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setWeek(week);
        }

        if (unitsWithdrawn == null) {
            ivldInventoryWdProjMasImpl.setUnitsWithdrawn(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setUnitsWithdrawn(unitsWithdrawn);
        }

        if (reasonForFailure == null) {
            ivldInventoryWdProjMasImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setReasonForFailure(reasonForFailure);
        }

        if (country == null) {
            ivldInventoryWdProjMasImpl.setCountry(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setCountry(country);
        }

        if (year == null) {
            ivldInventoryWdProjMasImpl.setYear(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setYear(year);
        }

        if (itemId == null) {
            ivldInventoryWdProjMasImpl.setItemId(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setItemId(itemId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldInventoryWdProjMasImpl.setModifiedDate(null);
        } else {
            ivldInventoryWdProjMasImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (organizationKey == null) {
            ivldInventoryWdProjMasImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setOrganizationKey(organizationKey);
        }

        if (itemIdentifierCodeQualifier == null) {
            ivldInventoryWdProjMasImpl.setItemIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        if (source == null) {
            ivldInventoryWdProjMasImpl.setSource(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setSource(source);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldInventoryWdProjMasImpl.setCreatedDate(null);
        } else {
            ivldInventoryWdProjMasImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            ivldInventoryWdProjMasImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setCreatedBy(createdBy);
        }

        if (day == null) {
            ivldInventoryWdProjMasImpl.setDay(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setDay(day);
        }

        if (forecastVer == null) {
            ivldInventoryWdProjMasImpl.setForecastVer(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setForecastVer(forecastVer);
        }

        if (batchId == null) {
            ivldInventoryWdProjMasImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setBatchId(batchId);
        }

        if (addChgDelIndicator == null) {
            ivldInventoryWdProjMasImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (itemIdentifier == null) {
            ivldInventoryWdProjMasImpl.setItemIdentifier(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setItemIdentifier(itemIdentifier);
        }

        if (errorField == null) {
            ivldInventoryWdProjMasImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setErrorField(errorField);
        }

        if (errorCode == null) {
            ivldInventoryWdProjMasImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setErrorCode(errorCode);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldInventoryWdProjMasImpl.setIntfInsertedDate(null);
        } else {
            ivldInventoryWdProjMasImpl.setIntfInsertedDate(new Date(
                    intfInsertedDate));
        }

        if (modifiedBy == null) {
            ivldInventoryWdProjMasImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setModifiedBy(modifiedBy);
        }

        ivldInventoryWdProjMasImpl.setIvldInventoryWdProjMasSid(ivldInventoryWdProjMasSid);

        if (month == null) {
            ivldInventoryWdProjMasImpl.setMonth(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setMonth(month);
        }

        if (reprocessedFlag == null) {
            ivldInventoryWdProjMasImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (forecastName == null) {
            ivldInventoryWdProjMasImpl.setForecastName(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setForecastName(forecastName);
        }

        if (amountWithdrawn == null) {
            ivldInventoryWdProjMasImpl.setAmountWithdrawn(StringPool.BLANK);
        } else {
            ivldInventoryWdProjMasImpl.setAmountWithdrawn(amountWithdrawn);
        }

        ivldInventoryWdProjMasImpl.resetOriginalValues();

        return ivldInventoryWdProjMasImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        inventoryWdProjMasIntfId = objectInput.readInt();
        week = objectInput.readUTF();
        unitsWithdrawn = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        country = objectInput.readUTF();
        year = objectInput.readUTF();
        itemId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        organizationKey = objectInput.readUTF();
        itemIdentifierCodeQualifier = objectInput.readUTF();
        source = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        day = objectInput.readUTF();
        forecastVer = objectInput.readUTF();
        batchId = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        itemIdentifier = objectInput.readUTF();
        errorField = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        ivldInventoryWdProjMasSid = objectInput.readInt();
        month = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        forecastName = objectInput.readUTF();
        amountWithdrawn = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(inventoryWdProjMasIntfId);

        if (week == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(week);
        }

        if (unitsWithdrawn == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(unitsWithdrawn);
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

        if (organizationKey == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(organizationKey);
        }

        if (itemIdentifierCodeQualifier == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemIdentifierCodeQualifier);
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

        if (day == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(day);
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

        if (errorField == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorField);
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

        objectOutput.writeInt(ivldInventoryWdProjMasSid);

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

        if (forecastName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastName);
        }

        if (amountWithdrawn == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(amountWithdrawn);
        }
    }
}
