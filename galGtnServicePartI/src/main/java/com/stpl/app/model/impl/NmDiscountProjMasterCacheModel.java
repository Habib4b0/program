package com.stpl.app.model.impl;

import com.stpl.app.model.NmDiscountProjMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing NmDiscountProjMaster in entity cache.
 *
 * @author
 * @see NmDiscountProjMaster
 * @generated
 */
public class NmDiscountProjMasterCacheModel implements CacheModel<NmDiscountProjMaster>,
    Externalizable {
    public boolean checkRecord;
    public String discountId;
    public String userGroup;
    public String priceGroupType;
    public int projectionDetailsSid;
    public String netFlag;
    public String methodology;
    public String discountName;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(17);

        sb.append("{checkRecord=");
        sb.append(checkRecord);
        sb.append(", discountId=");
        sb.append(discountId);
        sb.append(", userGroup=");
        sb.append(userGroup);
        sb.append(", priceGroupType=");
        sb.append(priceGroupType);
        sb.append(", projectionDetailsSid=");
        sb.append(projectionDetailsSid);
        sb.append(", netFlag=");
        sb.append(netFlag);
        sb.append(", methodology=");
        sb.append(methodology);
        sb.append(", discountName=");
        sb.append(discountName);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public NmDiscountProjMaster toEntityModel() {
        NmDiscountProjMasterImpl nmDiscountProjMasterImpl = new NmDiscountProjMasterImpl();

        nmDiscountProjMasterImpl.setCheckRecord(checkRecord);

        if (discountId == null) {
            nmDiscountProjMasterImpl.setDiscountId(StringPool.BLANK);
        } else {
            nmDiscountProjMasterImpl.setDiscountId(discountId);
        }

        if (userGroup == null) {
            nmDiscountProjMasterImpl.setUserGroup(StringPool.BLANK);
        } else {
            nmDiscountProjMasterImpl.setUserGroup(userGroup);
        }

        if (priceGroupType == null) {
            nmDiscountProjMasterImpl.setPriceGroupType(StringPool.BLANK);
        } else {
            nmDiscountProjMasterImpl.setPriceGroupType(priceGroupType);
        }

        nmDiscountProjMasterImpl.setProjectionDetailsSid(projectionDetailsSid);

        if (netFlag == null) {
            nmDiscountProjMasterImpl.setNetFlag(StringPool.BLANK);
        } else {
            nmDiscountProjMasterImpl.setNetFlag(netFlag);
        }

        if (methodology == null) {
            nmDiscountProjMasterImpl.setMethodology(StringPool.BLANK);
        } else {
            nmDiscountProjMasterImpl.setMethodology(methodology);
        }

        if (discountName == null) {
            nmDiscountProjMasterImpl.setDiscountName(StringPool.BLANK);
        } else {
            nmDiscountProjMasterImpl.setDiscountName(discountName);
        }

        nmDiscountProjMasterImpl.resetOriginalValues();

        return nmDiscountProjMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        checkRecord = objectInput.readBoolean();
        discountId = objectInput.readUTF();
        userGroup = objectInput.readUTF();
        priceGroupType = objectInput.readUTF();
        projectionDetailsSid = objectInput.readInt();
        netFlag = objectInput.readUTF();
        methodology = objectInput.readUTF();
        discountName = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeBoolean(checkRecord);

        if (discountId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(discountId);
        }

        if (userGroup == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(userGroup);
        }

        if (priceGroupType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(priceGroupType);
        }

        objectOutput.writeInt(projectionDetailsSid);

        if (netFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(netFlag);
        }

        if (methodology == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(methodology);
        }

        if (discountName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(discountName);
        }
    }
}
