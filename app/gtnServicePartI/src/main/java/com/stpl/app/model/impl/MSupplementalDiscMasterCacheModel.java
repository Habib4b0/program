package com.stpl.app.model.impl;

import com.stpl.app.model.MSupplementalDiscMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MSupplementalDiscMaster in entity cache.
 *
 * @author
 * @see MSupplementalDiscMaster
 * @generated
 */
public class MSupplementalDiscMasterCacheModel implements CacheModel<MSupplementalDiscMaster>,
    Externalizable {
    public double actualDiscountRate2;
    public double actualDiscountRate1;
    public String marketType;
    public String actualMethodology;
    public double actualContractPrice;
    public int projectionDetailsSid;
    public double actualDiscount;
    public int checkRecord;
    public long contractEndDate;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{actualDiscountRate2=");
        sb.append(actualDiscountRate2);
        sb.append(", actualDiscountRate1=");
        sb.append(actualDiscountRate1);
        sb.append(", marketType=");
        sb.append(marketType);
        sb.append(", actualMethodology=");
        sb.append(actualMethodology);
        sb.append(", actualContractPrice=");
        sb.append(actualContractPrice);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", actualDiscount=");
        sb.append(actualDiscount);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append(", contractEndDate=");
        sb.append(contractEndDate);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public MSupplementalDiscMaster toEntityModel() {
        MSupplementalDiscMasterImpl mSupplementalDiscMasterImpl = new MSupplementalDiscMasterImpl();

        mSupplementalDiscMasterImpl.setActualDiscountRate2(actualDiscountRate2);
        mSupplementalDiscMasterImpl.setActualDiscountRate1(actualDiscountRate1);

        if (marketType == null) {
            mSupplementalDiscMasterImpl.setMarketType(StringPool.BLANK);
        } else {
            mSupplementalDiscMasterImpl.setMarketType(marketType);
        }

        if (actualMethodology == null) {
            mSupplementalDiscMasterImpl.setActualMethodology(StringPool.BLANK);
        } else {
            mSupplementalDiscMasterImpl.setActualMethodology(actualMethodology);
        }

        mSupplementalDiscMasterImpl.setActualContractPrice(actualContractPrice);
        mSupplementalDiscMasterImpl.setProjectionDetailsSid(projectionDetailsSid);
        mSupplementalDiscMasterImpl.setActualDiscount(actualDiscount);
        mSupplementalDiscMasterImpl.setCheckRecord(checkRecord);

        if (contractEndDate == Long.MIN_VALUE) {
            mSupplementalDiscMasterImpl.setContractEndDate(null);
        } else {
            mSupplementalDiscMasterImpl.setContractEndDate(new Date(
                    contractEndDate));
        }

        mSupplementalDiscMasterImpl.resetOriginalValues();

        return mSupplementalDiscMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        actualDiscountRate2 = objectInput.readDouble();
        actualDiscountRate1 = objectInput.readDouble();
        marketType = objectInput.readUTF();
        actualMethodology = objectInput.readUTF();
        actualContractPrice = objectInput.readDouble();
        projectionDetailsSid = objectInput.readInt();
        actualDiscount = objectInput.readDouble();
        checkRecord = objectInput.readInt();
        contractEndDate = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeDouble(actualDiscountRate2);
        objectOutput.writeDouble(actualDiscountRate1);

        if (marketType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(marketType);
        }

        if (actualMethodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(actualMethodology);
        }

        objectOutput.writeDouble(actualContractPrice);
        objectOutput.writeInt(projectionDetailsSid);
        objectOutput.writeDouble(actualDiscount);
        objectOutput.writeInt(checkRecord);
        objectOutput.writeLong(contractEndDate);
    }
}
