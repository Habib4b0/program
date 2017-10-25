package com.stpl.app.model.impl;

import com.stpl.app.model.GcmItemDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GcmItemDetails in entity cache.
 *
 * @author
 * @see GcmItemDetails
 * @generated
 */
public class GcmItemDetailsCacheModel implements CacheModel<GcmItemDetails>,
    Externalizable {
    public long ifpDetailsEndDate;
    public String itemStatus;
    public boolean checkRecord;
    public long ifpDetailsStartDate;
    public int userId;
    public int itemMasterSid;
    public long itemEndDate;
    public int gcmItemDetailsSid;
    public int itemIfpDetailsSid;
    public String itemId;
    public String brandName;
    public long modifiedDate;
    public long createdDate;
    public int createdBy;
    public long itemStartDate;
    public String sessionId;
    public String itemName;
    public String operation;
    public int modifiedBy;
    public String inboundStatus;
    public int itemStatusSid;
    public String itemNo;
    public int ifpModelSid;
    public String theraputicClass;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(49);

        sb.append("{ifpDetailsEndDate=");
        sb.append(ifpDetailsEndDate);
        sb.append(", itemStatus=");
        sb.append(itemStatus);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append(", ifpDetailsStartDate=");
        sb.append(ifpDetailsStartDate);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", itemEndDate=");
        sb.append(itemEndDate);
        sb.append(", gcmItemDetailsSid=");
        sb.append(gcmItemDetailsSid);
        sb.append(", itemIfpDetailsSid=");
        sb.append(itemIfpDetailsSid);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", brandName=");
        sb.append(brandName);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", itemStartDate=");
        sb.append(itemStartDate);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", operation=");
        sb.append(operation);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append(", itemStatusSid=");
        sb.append(itemStatusSid);
        sb.append(", itemNo=");
        sb.append(itemNo);
        sb.append(", ifpModelSid=");
        sb.append(ifpModelSid);
        sb.append(", theraputicClass=");
        sb.append(theraputicClass);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public GcmItemDetails toEntityModel() {
        GcmItemDetailsImpl gcmItemDetailsImpl = new GcmItemDetailsImpl();

        if (ifpDetailsEndDate == Long.MIN_VALUE) {
            gcmItemDetailsImpl.setIfpDetailsEndDate(null);
        } else {
            gcmItemDetailsImpl.setIfpDetailsEndDate(new Date(ifpDetailsEndDate));
        }

        if (itemStatus == null) {
            gcmItemDetailsImpl.setItemStatus(StringPool.BLANK);
        } else {
            gcmItemDetailsImpl.setItemStatus(itemStatus);
        }

        gcmItemDetailsImpl.setCheckRecord(checkRecord);

        if (ifpDetailsStartDate == Long.MIN_VALUE) {
            gcmItemDetailsImpl.setIfpDetailsStartDate(null);
        } else {
            gcmItemDetailsImpl.setIfpDetailsStartDate(new Date(
                    ifpDetailsStartDate));
        }

        gcmItemDetailsImpl.setUserId(userId);
        gcmItemDetailsImpl.setItemMasterSid(itemMasterSid);

        if (itemEndDate == Long.MIN_VALUE) {
            gcmItemDetailsImpl.setItemEndDate(null);
        } else {
            gcmItemDetailsImpl.setItemEndDate(new Date(itemEndDate));
        }

        gcmItemDetailsImpl.setGcmItemDetailsSid(gcmItemDetailsSid);
        gcmItemDetailsImpl.setItemIfpDetailsSid(itemIfpDetailsSid);

        if (itemId == null) {
            gcmItemDetailsImpl.setItemId(StringPool.BLANK);
        } else {
            gcmItemDetailsImpl.setItemId(itemId);
        }

        if (brandName == null) {
            gcmItemDetailsImpl.setBrandName(StringPool.BLANK);
        } else {
            gcmItemDetailsImpl.setBrandName(brandName);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            gcmItemDetailsImpl.setModifiedDate(null);
        } else {
            gcmItemDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            gcmItemDetailsImpl.setCreatedDate(null);
        } else {
            gcmItemDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        gcmItemDetailsImpl.setCreatedBy(createdBy);

        if (itemStartDate == Long.MIN_VALUE) {
            gcmItemDetailsImpl.setItemStartDate(null);
        } else {
            gcmItemDetailsImpl.setItemStartDate(new Date(itemStartDate));
        }

        if (sessionId == null) {
            gcmItemDetailsImpl.setSessionId(StringPool.BLANK);
        } else {
            gcmItemDetailsImpl.setSessionId(sessionId);
        }

        if (itemName == null) {
            gcmItemDetailsImpl.setItemName(StringPool.BLANK);
        } else {
            gcmItemDetailsImpl.setItemName(itemName);
        }

        if (operation == null) {
            gcmItemDetailsImpl.setOperation(StringPool.BLANK);
        } else {
            gcmItemDetailsImpl.setOperation(operation);
        }

        gcmItemDetailsImpl.setModifiedBy(modifiedBy);

        if (inboundStatus == null) {
            gcmItemDetailsImpl.setInboundStatus(StringPool.BLANK);
        } else {
            gcmItemDetailsImpl.setInboundStatus(inboundStatus);
        }

        gcmItemDetailsImpl.setItemStatusSid(itemStatusSid);

        if (itemNo == null) {
            gcmItemDetailsImpl.setItemNo(StringPool.BLANK);
        } else {
            gcmItemDetailsImpl.setItemNo(itemNo);
        }

        gcmItemDetailsImpl.setIfpModelSid(ifpModelSid);

        if (theraputicClass == null) {
            gcmItemDetailsImpl.setTheraputicClass(StringPool.BLANK);
        } else {
            gcmItemDetailsImpl.setTheraputicClass(theraputicClass);
        }

        gcmItemDetailsImpl.resetOriginalValues();

        return gcmItemDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        ifpDetailsEndDate = objectInput.readLong();
        itemStatus = objectInput.readUTF();
        checkRecord = objectInput.readBoolean();
        ifpDetailsStartDate = objectInput.readLong();
        userId = objectInput.readInt();
        itemMasterSid = objectInput.readInt();
        itemEndDate = objectInput.readLong();
        gcmItemDetailsSid = objectInput.readInt();
        itemIfpDetailsSid = objectInput.readInt();
        itemId = objectInput.readUTF();
        brandName = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        itemStartDate = objectInput.readLong();
        sessionId = objectInput.readUTF();
        itemName = objectInput.readUTF();
        operation = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        inboundStatus = objectInput.readUTF();
        itemStatusSid = objectInput.readInt();
        itemNo = objectInput.readUTF();
        ifpModelSid = objectInput.readInt();
        theraputicClass = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(ifpDetailsEndDate);

        if (itemStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemStatus);
        }

        objectOutput.writeBoolean(checkRecord);
        objectOutput.writeLong(ifpDetailsStartDate);
        objectOutput.writeInt(userId);
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeLong(itemEndDate);
        objectOutput.writeInt(gcmItemDetailsSid);
        objectOutput.writeInt(itemIfpDetailsSid);

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
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeLong(itemStartDate);

        if (sessionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sessionId);
        }

        if (itemName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemName);
        }

        if (operation == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(operation);
        }

        objectOutput.writeInt(modifiedBy);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }

        objectOutput.writeInt(itemStatusSid);

        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        objectOutput.writeInt(ifpModelSid);

        if (theraputicClass == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(theraputicClass);
        }
    }
}
