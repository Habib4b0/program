package com.stpl.app.model.impl;

import com.stpl.app.model.ReturnsMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ReturnsMaster in entity cache.
 *
 * @author
 * @see ReturnsMaster
 * @generated
 */
public class ReturnsMasterCacheModel implements CacheModel<ReturnsMaster>,
    Externalizable {
    public String adjValueAtOrigAsp;
    public int itemMasterSid;
    public long firstReturn;
    public String asp;
    public long maxExpiredMonthPlusCutoff;
    public String posEstimatedReturnUnits;
    public long origSaleMonthCutOff;
    public String calcUsed;
    public long modifiedDate;
    public int brandMasterSid;
    public long lastReturn;
    public String expectedReturnUnits;
    public long createdDate;
    public String createdBy;
    public String source;
    public String version;
    public String addChgDelIndicator;
    public int returnsMasterSid;
    public String weightedAvgMonths;
    public String modifiedBy;
    public String inboundStatus;
    public String pct25th;
    public long loadDate;
    public long maxExpiredMonth;
    public String actualReturnRate;
    public String rreserveId;
    public String returnComplete;
    public String expectedReturnRate;
    public String pct50th;
    public String within50qrtile;
    public String cumReturnUnits;
    public long origSaleMonth;
    public String description;
    public String sku;
    public String upperLimit;
    public String lowerLimit;
    public String valueAtOrigAsp;
    public String adjEstimatedReturnUnits;
    public String pct75th;
    public boolean recordLockStatus;
    public String pastExpiration;
    public String batchId;
    public String maximum;
    public String origSaleUnits;
    public String brand;
    public String origSaleDollars;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(93);

        sb.append("{adjValueAtOrigAsp=");
        sb.append(adjValueAtOrigAsp);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", firstReturn=");
        sb.append(firstReturn);
        sb.append(", asp=");
        sb.append(asp);
        sb.append(", maxExpiredMonthPlusCutoff=");
        sb.append(maxExpiredMonthPlusCutoff);
        sb.append(", posEstimatedReturnUnits=");
        sb.append(posEstimatedReturnUnits);
        sb.append(", origSaleMonthCutOff=");
        sb.append(origSaleMonthCutOff);
        sb.append(", calcUsed=");
        sb.append(calcUsed);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", brandMasterSid=");
        sb.append(brandMasterSid);
        sb.append(", lastReturn=");
        sb.append(lastReturn);
        sb.append(", expectedReturnUnits=");
        sb.append(expectedReturnUnits);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", source=");
        sb.append(source);
        sb.append(", version=");
        sb.append(version);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", returnsMasterSid=");
        sb.append(returnsMasterSid);
        sb.append(", weightedAvgMonths=");
        sb.append(weightedAvgMonths);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", pct25th=");
        sb.append(pct25th);
        sb.append(", loadDate=");
        sb.append(loadDate);
        sb.append(", maxExpiredMonth=");
        sb.append(maxExpiredMonth);
        sb.append(", actualReturnRate=");
        sb.append(actualReturnRate);
        sb.append(", rreserveId=");
        sb.append(rreserveId);
        sb.append(", returnComplete=");
        sb.append(returnComplete);
        sb.append(", expectedReturnRate=");
        sb.append(expectedReturnRate);
        sb.append(", pct50th=");
        sb.append(pct50th);
        sb.append(", within50qrtile=");
        sb.append(within50qrtile);
        sb.append(", cumReturnUnits=");
        sb.append(cumReturnUnits);
        sb.append(", origSaleMonth=");
        sb.append(origSaleMonth);
        sb.append(", description=");
        sb.append(description);
        sb.append(", sku=");
        sb.append(sku);
        sb.append(", upperLimit=");
        sb.append(upperLimit);
        sb.append(", lowerLimit=");
        sb.append(lowerLimit);
        sb.append(", valueAtOrigAsp=");
        sb.append(valueAtOrigAsp);
        sb.append(", adjEstimatedReturnUnits=");
        sb.append(adjEstimatedReturnUnits);
        sb.append(", pct75th=");
        sb.append(pct75th);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", pastExpiration=");
        sb.append(pastExpiration);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", maximum=");
        sb.append(maximum);
        sb.append(", origSaleUnits=");
        sb.append(origSaleUnits);
        sb.append(", brand=");
        sb.append(brand);
        sb.append(", origSaleDollars=");
        sb.append(origSaleDollars);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ReturnsMaster toEntityModel() {
        ReturnsMasterImpl returnsMasterImpl = new ReturnsMasterImpl();

        if (adjValueAtOrigAsp == null) {
            returnsMasterImpl.setAdjValueAtOrigAsp(StringPool.BLANK);
        } else {
            returnsMasterImpl.setAdjValueAtOrigAsp(adjValueAtOrigAsp);
        }

        returnsMasterImpl.setItemMasterSid(itemMasterSid);

        if (firstReturn == Long.MIN_VALUE) {
            returnsMasterImpl.setFirstReturn(null);
        } else {
            returnsMasterImpl.setFirstReturn(new Date(firstReturn));
        }

        if (asp == null) {
            returnsMasterImpl.setAsp(StringPool.BLANK);
        } else {
            returnsMasterImpl.setAsp(asp);
        }

        if (maxExpiredMonthPlusCutoff == Long.MIN_VALUE) {
            returnsMasterImpl.setMaxExpiredMonthPlusCutoff(null);
        } else {
            returnsMasterImpl.setMaxExpiredMonthPlusCutoff(new Date(
                    maxExpiredMonthPlusCutoff));
        }

        if (posEstimatedReturnUnits == null) {
            returnsMasterImpl.setPosEstimatedReturnUnits(StringPool.BLANK);
        } else {
            returnsMasterImpl.setPosEstimatedReturnUnits(posEstimatedReturnUnits);
        }

        if (origSaleMonthCutOff == Long.MIN_VALUE) {
            returnsMasterImpl.setOrigSaleMonthCutOff(null);
        } else {
            returnsMasterImpl.setOrigSaleMonthCutOff(new Date(
                    origSaleMonthCutOff));
        }

        if (calcUsed == null) {
            returnsMasterImpl.setCalcUsed(StringPool.BLANK);
        } else {
            returnsMasterImpl.setCalcUsed(calcUsed);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            returnsMasterImpl.setModifiedDate(null);
        } else {
            returnsMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        returnsMasterImpl.setBrandMasterSid(brandMasterSid);

        if (lastReturn == Long.MIN_VALUE) {
            returnsMasterImpl.setLastReturn(null);
        } else {
            returnsMasterImpl.setLastReturn(new Date(lastReturn));
        }

        if (expectedReturnUnits == null) {
            returnsMasterImpl.setExpectedReturnUnits(StringPool.BLANK);
        } else {
            returnsMasterImpl.setExpectedReturnUnits(expectedReturnUnits);
        }

        if (createdDate == Long.MIN_VALUE) {
            returnsMasterImpl.setCreatedDate(null);
        } else {
            returnsMasterImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            returnsMasterImpl.setCreatedBy(StringPool.BLANK);
        } else {
            returnsMasterImpl.setCreatedBy(createdBy);
        }

        if (source == null) {
            returnsMasterImpl.setSource(StringPool.BLANK);
        } else {
            returnsMasterImpl.setSource(source);
        }

        if (version == null) {
            returnsMasterImpl.setVersion(StringPool.BLANK);
        } else {
            returnsMasterImpl.setVersion(version);
        }

        if (addChgDelIndicator == null) {
            returnsMasterImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            returnsMasterImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        returnsMasterImpl.setReturnsMasterSid(returnsMasterSid);

        if (weightedAvgMonths == null) {
            returnsMasterImpl.setWeightedAvgMonths(StringPool.BLANK);
        } else {
            returnsMasterImpl.setWeightedAvgMonths(weightedAvgMonths);
        }

        if (modifiedBy == null) {
            returnsMasterImpl.setModifiedBy(StringPool.BLANK);
        } else {
            returnsMasterImpl.setModifiedBy(modifiedBy);
        }

        if (inboundStatus == null) {
            returnsMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            returnsMasterImpl.setInboundStatus(inboundStatus);
        }

        if (pct25th == null) {
            returnsMasterImpl.setPct25th(StringPool.BLANK);
        } else {
            returnsMasterImpl.setPct25th(pct25th);
        }

        if (loadDate == Long.MIN_VALUE) {
            returnsMasterImpl.setLoadDate(null);
        } else {
            returnsMasterImpl.setLoadDate(new Date(loadDate));
        }

        if (maxExpiredMonth == Long.MIN_VALUE) {
            returnsMasterImpl.setMaxExpiredMonth(null);
        } else {
            returnsMasterImpl.setMaxExpiredMonth(new Date(maxExpiredMonth));
        }

        if (actualReturnRate == null) {
            returnsMasterImpl.setActualReturnRate(StringPool.BLANK);
        } else {
            returnsMasterImpl.setActualReturnRate(actualReturnRate);
        }

        if (rreserveId == null) {
            returnsMasterImpl.setRreserveId(StringPool.BLANK);
        } else {
            returnsMasterImpl.setRreserveId(rreserveId);
        }

        if (returnComplete == null) {
            returnsMasterImpl.setReturnComplete(StringPool.BLANK);
        } else {
            returnsMasterImpl.setReturnComplete(returnComplete);
        }

        if (expectedReturnRate == null) {
            returnsMasterImpl.setExpectedReturnRate(StringPool.BLANK);
        } else {
            returnsMasterImpl.setExpectedReturnRate(expectedReturnRate);
        }

        if (pct50th == null) {
            returnsMasterImpl.setPct50th(StringPool.BLANK);
        } else {
            returnsMasterImpl.setPct50th(pct50th);
        }

        if (within50qrtile == null) {
            returnsMasterImpl.setWithin50qrtile(StringPool.BLANK);
        } else {
            returnsMasterImpl.setWithin50qrtile(within50qrtile);
        }

        if (cumReturnUnits == null) {
            returnsMasterImpl.setCumReturnUnits(StringPool.BLANK);
        } else {
            returnsMasterImpl.setCumReturnUnits(cumReturnUnits);
        }

        if (origSaleMonth == Long.MIN_VALUE) {
            returnsMasterImpl.setOrigSaleMonth(null);
        } else {
            returnsMasterImpl.setOrigSaleMonth(new Date(origSaleMonth));
        }

        if (description == null) {
            returnsMasterImpl.setDescription(StringPool.BLANK);
        } else {
            returnsMasterImpl.setDescription(description);
        }

        if (sku == null) {
            returnsMasterImpl.setSku(StringPool.BLANK);
        } else {
            returnsMasterImpl.setSku(sku);
        }

        if (upperLimit == null) {
            returnsMasterImpl.setUpperLimit(StringPool.BLANK);
        } else {
            returnsMasterImpl.setUpperLimit(upperLimit);
        }

        if (lowerLimit == null) {
            returnsMasterImpl.setLowerLimit(StringPool.BLANK);
        } else {
            returnsMasterImpl.setLowerLimit(lowerLimit);
        }

        if (valueAtOrigAsp == null) {
            returnsMasterImpl.setValueAtOrigAsp(StringPool.BLANK);
        } else {
            returnsMasterImpl.setValueAtOrigAsp(valueAtOrigAsp);
        }

        if (adjEstimatedReturnUnits == null) {
            returnsMasterImpl.setAdjEstimatedReturnUnits(StringPool.BLANK);
        } else {
            returnsMasterImpl.setAdjEstimatedReturnUnits(adjEstimatedReturnUnits);
        }

        if (pct75th == null) {
            returnsMasterImpl.setPct75th(StringPool.BLANK);
        } else {
            returnsMasterImpl.setPct75th(pct75th);
        }

        returnsMasterImpl.setRecordLockStatus(recordLockStatus);

        if (pastExpiration == null) {
            returnsMasterImpl.setPastExpiration(StringPool.BLANK);
        } else {
            returnsMasterImpl.setPastExpiration(pastExpiration);
        }

        if (batchId == null) {
            returnsMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            returnsMasterImpl.setBatchId(batchId);
        }

        if (maximum == null) {
            returnsMasterImpl.setMaximum(StringPool.BLANK);
        } else {
            returnsMasterImpl.setMaximum(maximum);
        }

        if (origSaleUnits == null) {
            returnsMasterImpl.setOrigSaleUnits(StringPool.BLANK);
        } else {
            returnsMasterImpl.setOrigSaleUnits(origSaleUnits);
        }

        if (brand == null) {
            returnsMasterImpl.setBrand(StringPool.BLANK);
        } else {
            returnsMasterImpl.setBrand(brand);
        }

        if (origSaleDollars == null) {
            returnsMasterImpl.setOrigSaleDollars(StringPool.BLANK);
        } else {
            returnsMasterImpl.setOrigSaleDollars(origSaleDollars);
        }

        returnsMasterImpl.resetOriginalValues();

        return returnsMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        adjValueAtOrigAsp = objectInput.readUTF();
        itemMasterSid = objectInput.readInt();
        firstReturn = objectInput.readLong();
        asp = objectInput.readUTF();
        maxExpiredMonthPlusCutoff = objectInput.readLong();
        posEstimatedReturnUnits = objectInput.readUTF();
        origSaleMonthCutOff = objectInput.readLong();
        calcUsed = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        brandMasterSid = objectInput.readInt();
        lastReturn = objectInput.readLong();
        expectedReturnUnits = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        source = objectInput.readUTF();
        version = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        returnsMasterSid = objectInput.readInt();
        weightedAvgMonths = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
        pct25th = objectInput.readUTF();
        loadDate = objectInput.readLong();
        maxExpiredMonth = objectInput.readLong();
        actualReturnRate = objectInput.readUTF();
        rreserveId = objectInput.readUTF();
        returnComplete = objectInput.readUTF();
        expectedReturnRate = objectInput.readUTF();
        pct50th = objectInput.readUTF();
        within50qrtile = objectInput.readUTF();
        cumReturnUnits = objectInput.readUTF();
        origSaleMonth = objectInput.readLong();
        description = objectInput.readUTF();
        sku = objectInput.readUTF();
        upperLimit = objectInput.readUTF();
        lowerLimit = objectInput.readUTF();
        valueAtOrigAsp = objectInput.readUTF();
        adjEstimatedReturnUnits = objectInput.readUTF();
        pct75th = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        pastExpiration = objectInput.readUTF();
        batchId = objectInput.readUTF();
        maximum = objectInput.readUTF();
        origSaleUnits = objectInput.readUTF();
        brand = objectInput.readUTF();
        origSaleDollars = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (adjValueAtOrigAsp == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjValueAtOrigAsp);
        }

        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeLong(firstReturn);

        if (asp == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(asp);
        }

        objectOutput.writeLong(maxExpiredMonthPlusCutoff);

        if (posEstimatedReturnUnits == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(posEstimatedReturnUnits);
        }

        objectOutput.writeLong(origSaleMonthCutOff);

        if (calcUsed == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(calcUsed);
        }

        objectOutput.writeLong(modifiedDate);
        objectOutput.writeInt(brandMasterSid);
        objectOutput.writeLong(lastReturn);

        if (expectedReturnUnits == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(expectedReturnUnits);
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

        if (version == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(version);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        objectOutput.writeInt(returnsMasterSid);

        if (weightedAvgMonths == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(weightedAvgMonths);
        }

        if (modifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(modifiedBy);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        if (pct25th == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pct25th);
        }

        objectOutput.writeLong(loadDate);
        objectOutput.writeLong(maxExpiredMonth);

        if (actualReturnRate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actualReturnRate);
        }

        if (rreserveId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(rreserveId);
        }

        if (returnComplete == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(returnComplete);
        }

        if (expectedReturnRate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(expectedReturnRate);
        }

        if (pct50th == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pct50th);
        }

        if (within50qrtile == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(within50qrtile);
        }

        if (cumReturnUnits == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(cumReturnUnits);
        }

        objectOutput.writeLong(origSaleMonth);

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }

        if (sku == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sku);
        }

        if (upperLimit == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(upperLimit);
        }

        if (lowerLimit == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(lowerLimit);
        }

        if (valueAtOrigAsp == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(valueAtOrigAsp);
        }

        if (adjEstimatedReturnUnits == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(adjEstimatedReturnUnits);
        }

        if (pct75th == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pct75th);
        }

        objectOutput.writeBoolean(recordLockStatus);

        if (pastExpiration == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pastExpiration);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (maximum == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(maximum);
        }

        if (origSaleUnits == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(origSaleUnits);
        }

        if (brand == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(brand);
        }

        if (origSaleDollars == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(origSaleDollars);
        }
    }
}
