package com.stpl.app.model.impl;

import com.stpl.app.model.IvldReturns;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldReturns in entity cache.
 *
 * @author
 * @see IvldReturns
 * @generated
 */
public class IvldReturnsCacheModel implements CacheModel<IvldReturns>,
    Externalizable {
    public String adjValueAtOrigAsp;
    public String firstReturn;
    public String asp;
    public String maxExpiredMonthPlusCutoff;
    public String posEstimatedReturnUnits;
    public String origSaleMonthCutOff;
    public String calcUsed;
    public long modifiedDate;
    public String lastReturn;
    public String expectedReturnUnits;
    public long createdDate;
    public String createdBy;
    public String source;
    public String version;
    public String addChgDelIndicator;
    public String weightedAvgMonths;
    public String errorCode;
    public String modifiedBy;
    public int ivldReturnsSid;
    public long intfInsertedDate;
    public String pct25th;
    public String loadDate;
    public String maxExpiredMonth;
    public String reprocessedFlag;
    public String actualReturnRate;
    public String rreserveId;
    public String returnComplete;
    public String expectedReturnRate;
    public String pct50th;
    public String within50qrtile;
    public int rreserveInterfaceId;
    public String cumReturnUnits;
    public String reasonForFailure;
    public String origSaleMonth;
    public String description;
    public String sku;
    public String upperLimit;
    public String lowerLimit;
    public String valueAtOrigAsp;
    public String adjEstimatedReturnUnits;
    public String pct75th;
    public String pastExpiration;
    public String batchId;
    public String maximum;
    public String origSaleUnits;
    public String errorField;
    public String brand;
    public String origSaleDollars;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(97);

        sb.append("{adjValueAtOrigAsp=");
        sb.append(adjValueAtOrigAsp);
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
        sb.append(", weightedAvgMonths=");
        sb.append(weightedAvgMonths);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", ivldReturnsSid=");
        sb.append(ivldReturnsSid);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", pct25th=");
        sb.append(pct25th);
        sb.append(", loadDate=");
        sb.append(loadDate);
        sb.append(", maxExpiredMonth=");
        sb.append(maxExpiredMonth);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
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
        sb.append(", rreserveInterfaceId=");
        sb.append(rreserveInterfaceId);
        sb.append(", cumReturnUnits=");
        sb.append(cumReturnUnits);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
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
        sb.append(", pastExpiration=");
        sb.append(pastExpiration);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", maximum=");
        sb.append(maximum);
        sb.append(", origSaleUnits=");
        sb.append(origSaleUnits);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", brand=");
        sb.append(brand);
        sb.append(", origSaleDollars=");
        sb.append(origSaleDollars);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldReturns toEntityModel() {
        IvldReturnsImpl ivldReturnsImpl = new IvldReturnsImpl();

        if (adjValueAtOrigAsp == null) {
            ivldReturnsImpl.setAdjValueAtOrigAsp(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setAdjValueAtOrigAsp(adjValueAtOrigAsp);
        }

        if (firstReturn == null) {
            ivldReturnsImpl.setFirstReturn(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setFirstReturn(firstReturn);
        }

        if (asp == null) {
            ivldReturnsImpl.setAsp(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setAsp(asp);
        }

        if (maxExpiredMonthPlusCutoff == null) {
            ivldReturnsImpl.setMaxExpiredMonthPlusCutoff(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setMaxExpiredMonthPlusCutoff(maxExpiredMonthPlusCutoff);
        }

        if (posEstimatedReturnUnits == null) {
            ivldReturnsImpl.setPosEstimatedReturnUnits(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setPosEstimatedReturnUnits(posEstimatedReturnUnits);
        }

        if (origSaleMonthCutOff == null) {
            ivldReturnsImpl.setOrigSaleMonthCutOff(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setOrigSaleMonthCutOff(origSaleMonthCutOff);
        }

        if (calcUsed == null) {
            ivldReturnsImpl.setCalcUsed(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setCalcUsed(calcUsed);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldReturnsImpl.setModifiedDate(null);
        } else {
            ivldReturnsImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (lastReturn == null) {
            ivldReturnsImpl.setLastReturn(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setLastReturn(lastReturn);
        }

        if (expectedReturnUnits == null) {
            ivldReturnsImpl.setExpectedReturnUnits(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setExpectedReturnUnits(expectedReturnUnits);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldReturnsImpl.setCreatedDate(null);
        } else {
            ivldReturnsImpl.setCreatedDate(new Date(createdDate));
        }

        if (createdBy == null) {
            ivldReturnsImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setCreatedBy(createdBy);
        }

        if (source == null) {
            ivldReturnsImpl.setSource(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setSource(source);
        }

        if (version == null) {
            ivldReturnsImpl.setVersion(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setVersion(version);
        }

        if (addChgDelIndicator == null) {
            ivldReturnsImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        if (weightedAvgMonths == null) {
            ivldReturnsImpl.setWeightedAvgMonths(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setWeightedAvgMonths(weightedAvgMonths);
        }

        if (errorCode == null) {
            ivldReturnsImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setErrorCode(errorCode);
        }

        if (modifiedBy == null) {
            ivldReturnsImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setModifiedBy(modifiedBy);
        }

        ivldReturnsImpl.setIvldReturnsSid(ivldReturnsSid);

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldReturnsImpl.setIntfInsertedDate(null);
        } else {
            ivldReturnsImpl.setIntfInsertedDate(new Date(intfInsertedDate));
        }

        if (pct25th == null) {
            ivldReturnsImpl.setPct25th(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setPct25th(pct25th);
        }

        if (loadDate == null) {
            ivldReturnsImpl.setLoadDate(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setLoadDate(loadDate);
        }

        if (maxExpiredMonth == null) {
            ivldReturnsImpl.setMaxExpiredMonth(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setMaxExpiredMonth(maxExpiredMonth);
        }

        if (reprocessedFlag == null) {
            ivldReturnsImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setReprocessedFlag(reprocessedFlag);
        }

        if (actualReturnRate == null) {
            ivldReturnsImpl.setActualReturnRate(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setActualReturnRate(actualReturnRate);
        }

        if (rreserveId == null) {
            ivldReturnsImpl.setRreserveId(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setRreserveId(rreserveId);
        }

        if (returnComplete == null) {
            ivldReturnsImpl.setReturnComplete(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setReturnComplete(returnComplete);
        }

        if (expectedReturnRate == null) {
            ivldReturnsImpl.setExpectedReturnRate(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setExpectedReturnRate(expectedReturnRate);
        }

        if (pct50th == null) {
            ivldReturnsImpl.setPct50th(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setPct50th(pct50th);
        }

        if (within50qrtile == null) {
            ivldReturnsImpl.setWithin50qrtile(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setWithin50qrtile(within50qrtile);
        }

        ivldReturnsImpl.setRreserveInterfaceId(rreserveInterfaceId);

        if (cumReturnUnits == null) {
            ivldReturnsImpl.setCumReturnUnits(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setCumReturnUnits(cumReturnUnits);
        }

        if (reasonForFailure == null) {
            ivldReturnsImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setReasonForFailure(reasonForFailure);
        }

        if (origSaleMonth == null) {
            ivldReturnsImpl.setOrigSaleMonth(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setOrigSaleMonth(origSaleMonth);
        }

        if (description == null) {
            ivldReturnsImpl.setDescription(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setDescription(description);
        }

        if (sku == null) {
            ivldReturnsImpl.setSku(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setSku(sku);
        }

        if (upperLimit == null) {
            ivldReturnsImpl.setUpperLimit(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setUpperLimit(upperLimit);
        }

        if (lowerLimit == null) {
            ivldReturnsImpl.setLowerLimit(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setLowerLimit(lowerLimit);
        }

        if (valueAtOrigAsp == null) {
            ivldReturnsImpl.setValueAtOrigAsp(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setValueAtOrigAsp(valueAtOrigAsp);
        }

        if (adjEstimatedReturnUnits == null) {
            ivldReturnsImpl.setAdjEstimatedReturnUnits(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setAdjEstimatedReturnUnits(adjEstimatedReturnUnits);
        }

        if (pct75th == null) {
            ivldReturnsImpl.setPct75th(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setPct75th(pct75th);
        }

        if (pastExpiration == null) {
            ivldReturnsImpl.setPastExpiration(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setPastExpiration(pastExpiration);
        }

        if (batchId == null) {
            ivldReturnsImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setBatchId(batchId);
        }

        if (maximum == null) {
            ivldReturnsImpl.setMaximum(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setMaximum(maximum);
        }

        if (origSaleUnits == null) {
            ivldReturnsImpl.setOrigSaleUnits(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setOrigSaleUnits(origSaleUnits);
        }

        if (errorField == null) {
            ivldReturnsImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setErrorField(errorField);
        }

        if (brand == null) {
            ivldReturnsImpl.setBrand(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setBrand(brand);
        }

        if (origSaleDollars == null) {
            ivldReturnsImpl.setOrigSaleDollars(StringPool.BLANK);
        } else {
            ivldReturnsImpl.setOrigSaleDollars(origSaleDollars);
        }

        ivldReturnsImpl.resetOriginalValues();

        return ivldReturnsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        adjValueAtOrigAsp = objectInput.readUTF();
        firstReturn = objectInput.readUTF();
        asp = objectInput.readUTF();
        maxExpiredMonthPlusCutoff = objectInput.readUTF();
        posEstimatedReturnUnits = objectInput.readUTF();
        origSaleMonthCutOff = objectInput.readUTF();
        calcUsed = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        lastReturn = objectInput.readUTF();
        expectedReturnUnits = objectInput.readUTF();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readUTF();
        source = objectInput.readUTF();
        version = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        weightedAvgMonths = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        modifiedBy = objectInput.readUTF();
        ivldReturnsSid = objectInput.readInt();
        intfInsertedDate = objectInput.readLong();
        pct25th = objectInput.readUTF();
        loadDate = objectInput.readUTF();
        maxExpiredMonth = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
        actualReturnRate = objectInput.readUTF();
        rreserveId = objectInput.readUTF();
        returnComplete = objectInput.readUTF();
        expectedReturnRate = objectInput.readUTF();
        pct50th = objectInput.readUTF();
        within50qrtile = objectInput.readUTF();
        rreserveInterfaceId = objectInput.readInt();
        cumReturnUnits = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        origSaleMonth = objectInput.readUTF();
        description = objectInput.readUTF();
        sku = objectInput.readUTF();
        upperLimit = objectInput.readUTF();
        lowerLimit = objectInput.readUTF();
        valueAtOrigAsp = objectInput.readUTF();
        adjEstimatedReturnUnits = objectInput.readUTF();
        pct75th = objectInput.readUTF();
        pastExpiration = objectInput.readUTF();
        batchId = objectInput.readUTF();
        maximum = objectInput.readUTF();
        origSaleUnits = objectInput.readUTF();
        errorField = objectInput.readUTF();
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

        if (firstReturn == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(firstReturn);
        }

        if (asp == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(asp);
        }

        if (maxExpiredMonthPlusCutoff == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(maxExpiredMonthPlusCutoff);
        }

        if (posEstimatedReturnUnits == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(posEstimatedReturnUnits);
        }

        if (origSaleMonthCutOff == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(origSaleMonthCutOff);
        }

        if (calcUsed == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(calcUsed);
        }

        objectOutput.writeLong(modifiedDate);

        if (lastReturn == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(lastReturn);
        }

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

        if (weightedAvgMonths == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(weightedAvgMonths);
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

        objectOutput.writeInt(ivldReturnsSid);
        objectOutput.writeLong(intfInsertedDate);

        if (pct25th == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(pct25th);
        }

        if (loadDate == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(loadDate);
        }

        if (maxExpiredMonth == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(maxExpiredMonth);
        }

        if (reprocessedFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reprocessedFlag);
        }

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

        objectOutput.writeInt(rreserveInterfaceId);

        if (cumReturnUnits == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(cumReturnUnits);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (origSaleMonth == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(origSaleMonth);
        }

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

        if (errorField == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorField);
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
