package com.stpl.app.model.impl;

import com.stpl.app.model.VwIvldInventoryWdActualProjMas;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwIvldInventoryWdActualProjMas in entity cache.
 *
 * @author
 * @see VwIvldInventoryWdActualProjMas
 * @generated
 */
public class VwIvldInventoryWdActualProjMasCacheModel implements CacheModel<VwIvldInventoryWdActualProjMas>,
    Externalizable {
    public int ivldInventoryWdActualProjMasSid;
    public String quantityOnOrder;
    public String week;
    public double price;
    public String amountOnHand;
    public String isMaster;
    public String companyName;
    public String year;
    public String itemId;
    public long modifiedDate;
    public String organizationKey;
    public String source;
    public String createdBy;
    public long createdDate;
    public String day;
    public String addChgDelIndicator;
    public String unitsOnHand;
    public String amountReceived;
    public String errorCode;
    public long intfInsertedDate;
    public String modifiedBy;
    public String month;
    public String reprocessedFlag;
    public String amountWithdrawn;
    public String quantityReceived;
    public String unitsWithdrawn;
    public String reasonForFailure;
    public String country;
    public String companyId;
    public String isForecast;
    public String inventoryWdActualProjMasIntfId;
    public String forecastVer;
    public String batchId;
    public String itemName;
    public String errorField;
    public String amountOnOrder;
    public String forecastName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(75);

        sb.append("{ivldInventoryWdActualProjMasSid=");
        sb.append(ivldInventoryWdActualProjMasSid);
        sb.append(", quantityOnOrder=");
        sb.append(quantityOnOrder);
        sb.append(", week=");
        sb.append(week);
        sb.append(", price=");
        sb.append(price);
        sb.append(", amountOnHand=");
        sb.append(amountOnHand);
        sb.append(", isMaster=");
        sb.append(isMaster);
        sb.append(", companyName=");
        sb.append(companyName);
        sb.append(", year=");
        sb.append(year);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", organizationKey=");
        sb.append(organizationKey);
        sb.append(", source=");
        sb.append(source);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", day=");
        sb.append(day);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", unitsOnHand=");
        sb.append(unitsOnHand);
        sb.append(", amountReceived=");
        sb.append(amountReceived);
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
        sb.append(", quantityReceived=");
        sb.append(quantityReceived);
        sb.append(", unitsWithdrawn=");
        sb.append(unitsWithdrawn);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", country=");
        sb.append(country);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", isForecast=");
        sb.append(isForecast);
        sb.append(", inventoryWdActualProjMasIntfId=");
        sb.append(inventoryWdActualProjMasIntfId);
        sb.append(", forecastVer=");
        sb.append(forecastVer);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", amountOnOrder=");
        sb.append(amountOnOrder);
        sb.append(", forecastName=");
        sb.append(forecastName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public VwIvldInventoryWdActualProjMas toEntityModel() {
        VwIvldInventoryWdActualProjMasImpl vwIvldInventoryWdActualProjMasImpl = new VwIvldInventoryWdActualProjMasImpl();

        vwIvldInventoryWdActualProjMasImpl.setIvldInventoryWdActualProjMasSid(ivldInventoryWdActualProjMasSid);

        if (quantityOnOrder == null) {
            vwIvldInventoryWdActualProjMasImpl.setQuantityOnOrder(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setQuantityOnOrder(quantityOnOrder);
        }

        if (week == null) {
            vwIvldInventoryWdActualProjMasImpl.setWeek(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setWeek(week);
        }

        vwIvldInventoryWdActualProjMasImpl.setPrice(price);

        if (amountOnHand == null) {
            vwIvldInventoryWdActualProjMasImpl.setAmountOnHand(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setAmountOnHand(amountOnHand);
        }

        if (isMaster == null) {
            vwIvldInventoryWdActualProjMasImpl.setIsMaster(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setIsMaster(isMaster);
        }

        if (companyName == null) {
            vwIvldInventoryWdActualProjMasImpl.setCompanyName(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setCompanyName(companyName);
        }

        if (year == null) {
            vwIvldInventoryWdActualProjMasImpl.setYear(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setYear(year);
        }

        if (itemId == null) {
            vwIvldInventoryWdActualProjMasImpl.setItemId(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setItemId(itemId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            vwIvldInventoryWdActualProjMasImpl.setModifiedDate(null);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setModifiedDate(new Date(
                    modifiedDate));
        }

        if (organizationKey == null) {
            vwIvldInventoryWdActualProjMasImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setOrganizationKey(organizationKey);
        }

        if (source == null) {
            vwIvldInventoryWdActualProjMasImpl.setSource(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setSource(source);
        }

        if (createdBy == null) {
            vwIvldInventoryWdActualProjMasImpl.setCreatedBy(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            vwIvldInventoryWdActualProjMasImpl.setCreatedDate(null);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setCreatedDate(new Date(
                    createdDate));
        }

        if (day == null) {
            vwIvldInventoryWdActualProjMasImpl.setDay(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setDay(day);
        }

        if (addChgDelIndicator == null) {
            vwIvldInventoryWdActualProjMasImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (unitsOnHand == null) {
            vwIvldInventoryWdActualProjMasImpl.setUnitsOnHand(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setUnitsOnHand(unitsOnHand);
        }

        if (amountReceived == null) {
            vwIvldInventoryWdActualProjMasImpl.setAmountReceived(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setAmountReceived(amountReceived);
        }

        if (errorCode == null) {
            vwIvldInventoryWdActualProjMasImpl.setErrorCode(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setErrorCode(errorCode);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            vwIvldInventoryWdActualProjMasImpl.setIntfInsertedDate(null);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setIntfInsertedDate(new Date(
                    intfInsertedDate));
        }

        if (modifiedBy == null) {
            vwIvldInventoryWdActualProjMasImpl.setModifiedBy(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setModifiedBy(modifiedBy);
        }

        if (month == null) {
            vwIvldInventoryWdActualProjMasImpl.setMonth(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setMonth(month);
        }

        if (reprocessedFlag == null) {
            vwIvldInventoryWdActualProjMasImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (amountWithdrawn == null) {
            vwIvldInventoryWdActualProjMasImpl.setAmountWithdrawn(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setAmountWithdrawn(amountWithdrawn);
        }

        if (quantityReceived == null) {
            vwIvldInventoryWdActualProjMasImpl.setQuantityReceived(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setQuantityReceived(quantityReceived);
        }

        if (unitsWithdrawn == null) {
            vwIvldInventoryWdActualProjMasImpl.setUnitsWithdrawn(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setUnitsWithdrawn(unitsWithdrawn);
        }

        if (reasonForFailure == null) {
            vwIvldInventoryWdActualProjMasImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setReasonForFailure(reasonForFailure);
        }

        if (country == null) {
            vwIvldInventoryWdActualProjMasImpl.setCountry(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setCountry(country);
        }

        if (companyId == null) {
            vwIvldInventoryWdActualProjMasImpl.setCompanyId(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setCompanyId(companyId);
        }

        if (isForecast == null) {
            vwIvldInventoryWdActualProjMasImpl.setIsForecast(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setIsForecast(isForecast);
        }

        if (inventoryWdActualProjMasIntfId == null) {
            vwIvldInventoryWdActualProjMasImpl.setInventoryWdActualProjMasIntfId(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setInventoryWdActualProjMasIntfId(inventoryWdActualProjMasIntfId);
        }

        if (forecastVer == null) {
            vwIvldInventoryWdActualProjMasImpl.setForecastVer(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setForecastVer(forecastVer);
        }

        if (batchId == null) {
            vwIvldInventoryWdActualProjMasImpl.setBatchId(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setBatchId(batchId);
        }

        if (itemName == null) {
            vwIvldInventoryWdActualProjMasImpl.setItemName(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setItemName(itemName);
        }

        if (errorField == null) {
            vwIvldInventoryWdActualProjMasImpl.setErrorField(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setErrorField(errorField);
        }

        if (amountOnOrder == null) {
            vwIvldInventoryWdActualProjMasImpl.setAmountOnOrder(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setAmountOnOrder(amountOnOrder);
        }

        if (forecastName == null) {
            vwIvldInventoryWdActualProjMasImpl.setForecastName(StringPool.BLANK);
        } else {
            vwIvldInventoryWdActualProjMasImpl.setForecastName(forecastName);
        }

        vwIvldInventoryWdActualProjMasImpl.resetOriginalValues();

        return vwIvldInventoryWdActualProjMasImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        ivldInventoryWdActualProjMasSid = objectInput.readInt();
        quantityOnOrder = objectInput.readUTF();
        week = objectInput.readUTF();
        price = objectInput.readDouble();
        amountOnHand = objectInput.readUTF();
        isMaster = objectInput.readUTF();
        companyName = objectInput.readUTF();
        year = objectInput.readUTF();
        itemId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        organizationKey = objectInput.readUTF();
        source = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        day = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        unitsOnHand = objectInput.readUTF();
        amountReceived = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        month = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        amountWithdrawn = objectInput.readUTF();
        quantityReceived = objectInput.readUTF();
        unitsWithdrawn = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        country = objectInput.readUTF();
        companyId = objectInput.readUTF();
        isForecast = objectInput.readUTF();
        inventoryWdActualProjMasIntfId = objectInput.readUTF();
        forecastVer = objectInput.readUTF();
        batchId = objectInput.readUTF();
        itemName = objectInput.readUTF();
        errorField = objectInput.readUTF();
        amountOnOrder = objectInput.readUTF();
        forecastName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(ivldInventoryWdActualProjMasSid);

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

        objectOutput.writeDouble(price);

        if (amountOnHand == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(amountOnHand);
        }

        if (isMaster == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(isMaster);
        }

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

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (createdBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(createdBy);
        }

        objectOutput.writeLong(createdDate);

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

        if (companyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyId);
        }

        if (isForecast == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(isForecast);
        }

        if (inventoryWdActualProjMasIntfId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inventoryWdActualProjMasIntfId);
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

        if (amountOnOrder == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(amountOnOrder);
        }

        if (forecastName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastName);
        }
    }
}
