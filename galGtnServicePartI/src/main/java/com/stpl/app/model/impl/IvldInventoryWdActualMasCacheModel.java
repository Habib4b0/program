package com.stpl.app.model.impl;

import com.stpl.app.model.IvldInventoryWdActualMas;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldInventoryWdActualMas in entity cache.
 *
 * @author
 * @see IvldInventoryWdActualMas
 * @generated
 */
public class IvldInventoryWdActualMasCacheModel implements CacheModel<IvldInventoryWdActualMas>,
    Externalizable {
    public String quantityOnOrder;
    public String week;
    public String amountOnHand;
    public String year;
    public String itemId;
    public long modifiedDate;
    public String organizationKey;
    public String createdBy;
    public long createdDate;
    public String source;
    public int ivldInventoryWdActualMasSid;
    public String day;
    public String addChgDelIndicator;
    public String unitsOnHand;
    public String amountReceived;
    public String itemIdentifier;
    public String errorCode;
    public long intfInsertedDate;
    public String modifiedBy;
    public String month;
    public String reprocessedFlag;
    public String amountWithdrawn;
    public int inventoryWdActualMasIntfId;
    public String quantityReceived;
    public String unitsWithdrawn;
    public String reasonForFailure;
    public String country;
    public String itemIdentifierCodeQualifier;
    public String batchId;
    public String errorField;
    public String amountOnOrder;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(63);

        sb.append("{quantityOnOrder=");
        sb.append(quantityOnOrder);
        sb.append(", week=");
        sb.append(week);
        sb.append(", amountOnHand=");
        sb.append(amountOnHand);
        sb.append(", year=");
        sb.append(year);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", ivldInventoryWdActualMasSid=");
        sb.append(ivldInventoryWdActualMasSid);
        sb.append(", day=");
        sb.append(day);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", unitsOnHand=");
        sb.append(unitsOnHand);
        sb.append(", amountReceived=");
        sb.append(amountReceived);
        sb.append(", itemIdentifier=");
        sb.append(itemIdentifier);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", month=");
        sb.append(month);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append(", amountWithdrawn=");
        sb.append(amountWithdrawn);
        sb.append(", inventoryWdActualMasIntfId=");
        sb.append(inventoryWdActualMasIntfId);
        sb.append(", quantityReceived=");
        sb.append(quantityReceived);
        sb.append(", unitsWithdrawn=");
        sb.append(unitsWithdrawn);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", country=");
        sb.append(country);
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(itemIdentifierCodeQualifier);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", amountOnOrder=");
        sb.append(amountOnOrder);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldInventoryWdActualMas toEntityModel() {
        IvldInventoryWdActualMasImpl ivldInventoryWdActualMasImpl = new IvldInventoryWdActualMasImpl();

        if (quantityOnOrder == null) {
            ivldInventoryWdActualMasImpl.setQuantityOnOrder(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setQuantityOnOrder(quantityOnOrder);
        }

        if (week == null) {
            ivldInventoryWdActualMasImpl.setWeek(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setWeek(week);
        }

        if (amountOnHand == null) {
            ivldInventoryWdActualMasImpl.setAmountOnHand(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setAmountOnHand(amountOnHand);
        }

        if (year == null) {
            ivldInventoryWdActualMasImpl.setYear(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setYear(year);
        }

        if (itemId == null) {
            ivldInventoryWdActualMasImpl.setItemId(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setItemId(itemId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldInventoryWdActualMasImpl.setModifiedDate(null);
        } else {
            ivldInventoryWdActualMasImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (organizationKey == null) {
            ivldInventoryWdActualMasImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setOrganizationKey(organizationKey);
        }

        if (createdBy == null) {
            ivldInventoryWdActualMasImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldInventoryWdActualMasImpl.setCreatedDate(null);
        } else {
            ivldInventoryWdActualMasImpl.setCreatedDate(new Date(createdDate));
        }

        if (source == null) {
            ivldInventoryWdActualMasImpl.setSource(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setSource(source);
        }

        ivldInventoryWdActualMasImpl.setIvldInventoryWdActualMasSid(ivldInventoryWdActualMasSid);

        if (day == null) {
            ivldInventoryWdActualMasImpl.setDay(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setDay(day);
        }

        if (addChgDelIndicator == null) {
            ivldInventoryWdActualMasImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (unitsOnHand == null) {
            ivldInventoryWdActualMasImpl.setUnitsOnHand(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setUnitsOnHand(unitsOnHand);
        }

        if (amountReceived == null) {
            ivldInventoryWdActualMasImpl.setAmountReceived(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setAmountReceived(amountReceived);
        }

        if (itemIdentifier == null) {
            ivldInventoryWdActualMasImpl.setItemIdentifier(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setItemIdentifier(itemIdentifier);
        }

        if (errorCode == null) {
            ivldInventoryWdActualMasImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setErrorCode(errorCode);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldInventoryWdActualMasImpl.setIntfInsertedDate(null);
        } else {
            ivldInventoryWdActualMasImpl.setIntfInsertedDate(new Date(
                    intfInsertedDate));
        }

        if (modifiedBy == null) {
            ivldInventoryWdActualMasImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setModifiedBy(modifiedBy);
        }

        if (month == null) {
            ivldInventoryWdActualMasImpl.setMonth(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setMonth(month);
        }

        if (reprocessedFlag == null) {
            ivldInventoryWdActualMasImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (amountWithdrawn == null) {
            ivldInventoryWdActualMasImpl.setAmountWithdrawn(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setAmountWithdrawn(amountWithdrawn);
        }

        ivldInventoryWdActualMasImpl.setInventoryWdActualMasIntfId(inventoryWdActualMasIntfId);

        if (quantityReceived == null) {
            ivldInventoryWdActualMasImpl.setQuantityReceived(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setQuantityReceived(quantityReceived);
        }

        if (unitsWithdrawn == null) {
            ivldInventoryWdActualMasImpl.setUnitsWithdrawn(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setUnitsWithdrawn(unitsWithdrawn);
        }

        if (reasonForFailure == null) {
            ivldInventoryWdActualMasImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setReasonForFailure(reasonForFailure);
        }

        if (country == null) {
            ivldInventoryWdActualMasImpl.setCountry(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setCountry(country);
        }

        if (itemIdentifierCodeQualifier == null) {
            ivldInventoryWdActualMasImpl.setItemIdentifierCodeQualifier(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        if (batchId == null) {
            ivldInventoryWdActualMasImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setBatchId(batchId);
        }

        if (errorField == null) {
            ivldInventoryWdActualMasImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setErrorField(errorField);
        }

        if (amountOnOrder == null) {
            ivldInventoryWdActualMasImpl.setAmountOnOrder(StringPool.BLANK);
        } else {
            ivldInventoryWdActualMasImpl.setAmountOnOrder(amountOnOrder);
        }

        ivldInventoryWdActualMasImpl.resetOriginalValues();

        return ivldInventoryWdActualMasImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        quantityOnOrder = objectInput.readUTF();
        week = objectInput.readUTF();
        amountOnHand = objectInput.readUTF();
        year = objectInput.readUTF();
        itemId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        organizationKey = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        source = objectInput.readUTF();
        ivldInventoryWdActualMasSid = objectInput.readInt();
        day = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        unitsOnHand = objectInput.readUTF();
        amountReceived = objectInput.readUTF();
        itemIdentifier = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        month = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        amountWithdrawn = objectInput.readUTF();
        inventoryWdActualMasIntfId = objectInput.readInt();
        quantityReceived = objectInput.readUTF();
        unitsWithdrawn = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        country = objectInput.readUTF();
        itemIdentifierCodeQualifier = objectInput.readUTF();
        batchId = objectInput.readUTF();
        errorField = objectInput.readUTF();
        amountOnOrder = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (quantityOnOrder == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(quantityOnOrder);
        }

        if (week == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(week);
        }

        if (amountOnHand == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(amountOnHand);
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

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        objectOutput.writeLong(createdDate);

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        objectOutput.writeInt(ivldInventoryWdActualMasSid);

        if (day == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(day);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        if (unitsOnHand == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(unitsOnHand);
        }

        if (amountReceived == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(amountReceived);
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

        objectOutput.writeLong(intfInsertedDate);

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
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

        if (amountWithdrawn == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(amountWithdrawn);
        }

        objectOutput.writeInt(inventoryWdActualMasIntfId);

        if (quantityReceived == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(quantityReceived);
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

        if (errorField == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorField);
        }

        if (amountOnOrder == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(amountOnOrder);
        }
    }
}
