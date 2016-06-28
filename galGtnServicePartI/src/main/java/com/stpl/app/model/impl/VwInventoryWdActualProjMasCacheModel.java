package com.stpl.app.model.impl;

import com.stpl.app.model.VwInventoryWdActualProjMas;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing VwInventoryWdActualProjMas in entity cache.
 *
 * @author
 * @see VwInventoryWdActualProjMas
 * @generated
 */
public class VwInventoryWdActualProjMasCacheModel implements CacheModel<VwInventoryWdActualProjMas>,
    Externalizable {
    public double quantityOnOrder;
    public String week;
    public double price;
    public double amountOnHand;
    public String isMaster;
    public String companyName;
    public String year;
    public String itemId;
    public long modifiedDate;
    public String organizationKey;
    public int inventoryWdActualProjMasSid;
    public String source;
    public String createdBy;
    public long createdDate;
    public String day;
    public String addChgDelIndicator;
    public double unitsOnHand;
    public double amountReceived;
    public String modifiedBy;
    public String month;
    public double amountWithdrawn;
    public double quantityReceived;
    public double unitsWithdrawn;
    public String country;
    public String companyId;
    public String isForecast;
    public String forecastVer;
    public String batchId;
    public String itemName;
    public double amountOnOrder;
    public String forecastName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(63);

        sb.append("{quantityOnOrder=");
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
        sb.append(", inventoryWdActualProjMasSid=");
        sb.append(inventoryWdActualProjMasSid);
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
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", month=");
        sb.append(month);
        sb.append(", amountWithdrawn=");
        sb.append(amountWithdrawn);
        sb.append(", quantityReceived=");
        sb.append(quantityReceived);
        sb.append(", unitsWithdrawn=");
        sb.append(unitsWithdrawn);
        sb.append(", country=");
        sb.append(country);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", isForecast=");
        sb.append(isForecast);
        sb.append(", forecastVer=");
        sb.append(forecastVer);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", amountOnOrder=");
        sb.append(amountOnOrder);
        sb.append(", forecastName=");
        sb.append(forecastName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public VwInventoryWdActualProjMas toEntityModel() {
        VwInventoryWdActualProjMasImpl vwInventoryWdActualProjMasImpl = new VwInventoryWdActualProjMasImpl();

        vwInventoryWdActualProjMasImpl.setQuantityOnOrder(quantityOnOrder);

        if (week == null) {
            vwInventoryWdActualProjMasImpl.setWeek(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setWeek(week);
        }

        vwInventoryWdActualProjMasImpl.setPrice(price);
        vwInventoryWdActualProjMasImpl.setAmountOnHand(amountOnHand);

        if (isMaster == null) {
            vwInventoryWdActualProjMasImpl.setIsMaster(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setIsMaster(isMaster);
        }

        if (companyName == null) {
            vwInventoryWdActualProjMasImpl.setCompanyName(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setCompanyName(companyName);
        }

        if (year == null) {
            vwInventoryWdActualProjMasImpl.setYear(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setYear(year);
        }

        if (itemId == null) {
            vwInventoryWdActualProjMasImpl.setItemId(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setItemId(itemId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            vwInventoryWdActualProjMasImpl.setModifiedDate(null);
        } else {
            vwInventoryWdActualProjMasImpl.setModifiedDate(new Date(
                    modifiedDate));
        }

        if (organizationKey == null) {
            vwInventoryWdActualProjMasImpl.setOrganizationKey(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setOrganizationKey(organizationKey);
        }

        vwInventoryWdActualProjMasImpl.setInventoryWdActualProjMasSid(inventoryWdActualProjMasSid);

        if (source == null) {
            vwInventoryWdActualProjMasImpl.setSource(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setSource(source);
        }

        if (createdBy == null) {
            vwInventoryWdActualProjMasImpl.setCreatedBy(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            vwInventoryWdActualProjMasImpl.setCreatedDate(null);
        } else {
            vwInventoryWdActualProjMasImpl.setCreatedDate(new Date(createdDate));
        }

        if (day == null) {
            vwInventoryWdActualProjMasImpl.setDay(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setDay(day);
        }

        if (addChgDelIndicator == null) {
            vwInventoryWdActualProjMasImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        vwInventoryWdActualProjMasImpl.setUnitsOnHand(unitsOnHand);
        vwInventoryWdActualProjMasImpl.setAmountReceived(amountReceived);

        if (modifiedBy == null) {
            vwInventoryWdActualProjMasImpl.setModifiedBy(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setModifiedBy(modifiedBy);
        }

        if (month == null) {
            vwInventoryWdActualProjMasImpl.setMonth(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setMonth(month);
        }

        vwInventoryWdActualProjMasImpl.setAmountWithdrawn(amountWithdrawn);
        vwInventoryWdActualProjMasImpl.setQuantityReceived(quantityReceived);
        vwInventoryWdActualProjMasImpl.setUnitsWithdrawn(unitsWithdrawn);

        if (country == null) {
            vwInventoryWdActualProjMasImpl.setCountry(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setCountry(country);
        }

        if (companyId == null) {
            vwInventoryWdActualProjMasImpl.setCompanyId(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setCompanyId(companyId);
        }

        if (isForecast == null) {
            vwInventoryWdActualProjMasImpl.setIsForecast(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setIsForecast(isForecast);
        }

        if (forecastVer == null) {
            vwInventoryWdActualProjMasImpl.setForecastVer(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setForecastVer(forecastVer);
        }

        if (batchId == null) {
            vwInventoryWdActualProjMasImpl.setBatchId(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setBatchId(batchId);
        }

        if (itemName == null) {
            vwInventoryWdActualProjMasImpl.setItemName(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setItemName(itemName);
        }

        vwInventoryWdActualProjMasImpl.setAmountOnOrder(amountOnOrder);

        if (forecastName == null) {
            vwInventoryWdActualProjMasImpl.setForecastName(StringPool.BLANK);
        } else {
            vwInventoryWdActualProjMasImpl.setForecastName(forecastName);
        }

        vwInventoryWdActualProjMasImpl.resetOriginalValues();

        return vwInventoryWdActualProjMasImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        quantityOnOrder = objectInput.readDouble();
        week = objectInput.readUTF();
        price = objectInput.readDouble();
        amountOnHand = objectInput.readDouble();
        isMaster = objectInput.readUTF();
        companyName = objectInput.readUTF();
        year = objectInput.readUTF();
        itemId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        organizationKey = objectInput.readUTF();
        inventoryWdActualProjMasSid = objectInput.readInt();
        source = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        day = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        unitsOnHand = objectInput.readDouble();
        amountReceived = objectInput.readDouble();
        modifiedBy = objectInput.readUTF();
        month = objectInput.readUTF();
        amountWithdrawn = objectInput.readDouble();
        quantityReceived = objectInput.readDouble();
        unitsWithdrawn = objectInput.readDouble();
        country = objectInput.readUTF();
        companyId = objectInput.readUTF();
        isForecast = objectInput.readUTF();
        forecastVer = objectInput.readUTF();
        batchId = objectInput.readUTF();
        itemName = objectInput.readUTF();
        amountOnOrder = objectInput.readDouble();
        forecastName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(quantityOnOrder);

        if (week == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(week);
        }

        objectOutput.writeDouble(price);
        objectOutput.writeDouble(amountOnHand);

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

        objectOutput.writeInt(inventoryWdActualProjMasSid);

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

        objectOutput.writeDouble(unitsOnHand);
        objectOutput.writeDouble(amountReceived);

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

        objectOutput.writeDouble(amountWithdrawn);
        objectOutput.writeDouble(quantityReceived);
        objectOutput.writeDouble(unitsWithdrawn);

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

        objectOutput.writeDouble(amountOnOrder);

        if (forecastName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(forecastName);
        }
    }
}
