package com.stpl.app.model.impl;

import com.stpl.app.model.ImtdIfpDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ImtdIfpDetails in entity cache.
 *
 * @author
 * @see ImtdIfpDetails
 * @generated
 */
public class ImtdIfpDetailsCacheModel implements CacheModel<ImtdIfpDetails>,
    Externalizable {
    public int itemStatus;
    public long ifpDetailsEndDate;
    public int itemMasterSid;
    public long imtdCreateddate;
    public String itemPackageSize;
    public long ifpDetailsCreatedDate;
    public String totalDollarCommitment;
    public String ifpDetailsCreatedBy;
    public String itemId;
    public long modifiedDate;
    public String ifpDetailsModifiedBy;
    public long ifpDetailsModifiedDate;
    public long createdDate;
    public int createdBy;
    public int usersSid;
    public String itemDesc;
    public long itemStartDate;
    public String itemStrength;
    public int contractMasterSid;
    public int modifiedBy;
    public String commitmentPeriod;
    public String itemNo;
    public int ifpDetailsSid;
    public int ifpModelSid;
    public String itemTherapeuticClass;
    public long ifpDetailsStartDate;
    public String itemForm;
    public String totalVolumeCommitment;
    public long itemEndDate;
    public boolean checkBox;
    public int ifpDetailsAttachedStatus;
    public String totalMarketshareCommitment;
    public long ifpDetailsAttachedDate;
    public int imtdIfpDetailsSid;
    public String sessionId;
    public String itemName;
    public String itemPrimaryUom;
    public String operation;
    public String itemBrand;
    public int cfpModelSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(81);

        sb.append("{itemStatus=");
        sb.append(itemStatus);
        sb.append(", ifpDetailsEndDate=");
        sb.append(ifpDetailsEndDate);
        sb.append(", itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", imtdCreateddate=");
        sb.append(imtdCreateddate);
        sb.append(", itemPackageSize=");
        sb.append(itemPackageSize);
        sb.append(", ifpDetailsCreatedDate=");
        sb.append(ifpDetailsCreatedDate);
        sb.append(", totalDollarCommitment=");
        sb.append(totalDollarCommitment);
        sb.append(", ifpDetailsCreatedBy=");
        sb.append(ifpDetailsCreatedBy);
        sb.append(", itemId=");
        sb.append(itemId);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", ifpDetailsModifiedBy=");
        sb.append(ifpDetailsModifiedBy);
        sb.append(", ifpDetailsModifiedDate=");
        sb.append(ifpDetailsModifiedDate);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", usersSid=");
        sb.append(usersSid);
        sb.append(", itemDesc=");
        sb.append(itemDesc);
        sb.append(", itemStartDate=");
        sb.append(itemStartDate);
        sb.append(", itemStrength=");
        sb.append(itemStrength);
        sb.append(", contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", commitmentPeriod=");
        sb.append(commitmentPeriod);
        sb.append(", itemNo=");
        sb.append(itemNo);
        sb.append(", ifpDetailsSid=");
        sb.append(ifpDetailsSid);
        sb.append(", ifpModelSid=");
        sb.append(ifpModelSid);
        sb.append(", itemTherapeuticClass=");
        sb.append(itemTherapeuticClass);
        sb.append(", ifpDetailsStartDate=");
        sb.append(ifpDetailsStartDate);
        sb.append(", itemForm=");
        sb.append(itemForm);
        sb.append(", totalVolumeCommitment=");
        sb.append(totalVolumeCommitment);
        sb.append(", itemEndDate=");
        sb.append(itemEndDate);
        sb.append(", checkBox=");
        sb.append(checkBox);
        sb.append(", ifpDetailsAttachedStatus=");
        sb.append(ifpDetailsAttachedStatus);
        sb.append(", totalMarketshareCommitment=");
        sb.append(totalMarketshareCommitment);
        sb.append(", ifpDetailsAttachedDate=");
        sb.append(ifpDetailsAttachedDate);
        sb.append(", imtdIfpDetailsSid=");
        sb.append(imtdIfpDetailsSid);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", itemName=");
        sb.append(itemName);
        sb.append(", itemPrimaryUom=");
        sb.append(itemPrimaryUom);
        sb.append(", operation=");
        sb.append(operation);
        sb.append(", itemBrand=");
        sb.append(itemBrand);
        sb.append(", cfpModelSid=");
        sb.append(cfpModelSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ImtdIfpDetails toEntityModel() {
        ImtdIfpDetailsImpl imtdIfpDetailsImpl = new ImtdIfpDetailsImpl();

        imtdIfpDetailsImpl.setItemStatus(itemStatus);

        if (ifpDetailsEndDate == Long.MIN_VALUE) {
            imtdIfpDetailsImpl.setIfpDetailsEndDate(null);
        } else {
            imtdIfpDetailsImpl.setIfpDetailsEndDate(new Date(ifpDetailsEndDate));
        }

        imtdIfpDetailsImpl.setItemMasterSid(itemMasterSid);

        if (imtdCreateddate == Long.MIN_VALUE) {
            imtdIfpDetailsImpl.setImtdCreateddate(null);
        } else {
            imtdIfpDetailsImpl.setImtdCreateddate(new Date(imtdCreateddate));
        }

        if (itemPackageSize == null) {
            imtdIfpDetailsImpl.setItemPackageSize(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setItemPackageSize(itemPackageSize);
        }

        if (ifpDetailsCreatedDate == Long.MIN_VALUE) {
            imtdIfpDetailsImpl.setIfpDetailsCreatedDate(null);
        } else {
            imtdIfpDetailsImpl.setIfpDetailsCreatedDate(new Date(
                    ifpDetailsCreatedDate));
        }

        if (totalDollarCommitment == null) {
            imtdIfpDetailsImpl.setTotalDollarCommitment(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setTotalDollarCommitment(totalDollarCommitment);
        }

        if (ifpDetailsCreatedBy == null) {
            imtdIfpDetailsImpl.setIfpDetailsCreatedBy(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setIfpDetailsCreatedBy(ifpDetailsCreatedBy);
        }

        if (itemId == null) {
            imtdIfpDetailsImpl.setItemId(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setItemId(itemId);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            imtdIfpDetailsImpl.setModifiedDate(null);
        } else {
            imtdIfpDetailsImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (ifpDetailsModifiedBy == null) {
            imtdIfpDetailsImpl.setIfpDetailsModifiedBy(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setIfpDetailsModifiedBy(ifpDetailsModifiedBy);
        }

        if (ifpDetailsModifiedDate == Long.MIN_VALUE) {
            imtdIfpDetailsImpl.setIfpDetailsModifiedDate(null);
        } else {
            imtdIfpDetailsImpl.setIfpDetailsModifiedDate(new Date(
                    ifpDetailsModifiedDate));
        }

        if (createdDate == Long.MIN_VALUE) {
            imtdIfpDetailsImpl.setCreatedDate(null);
        } else {
            imtdIfpDetailsImpl.setCreatedDate(new Date(createdDate));
        }

        imtdIfpDetailsImpl.setCreatedBy(createdBy);
        imtdIfpDetailsImpl.setUsersSid(usersSid);

        if (itemDesc == null) {
            imtdIfpDetailsImpl.setItemDesc(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setItemDesc(itemDesc);
        }

        if (itemStartDate == Long.MIN_VALUE) {
            imtdIfpDetailsImpl.setItemStartDate(null);
        } else {
            imtdIfpDetailsImpl.setItemStartDate(new Date(itemStartDate));
        }

        if (itemStrength == null) {
            imtdIfpDetailsImpl.setItemStrength(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setItemStrength(itemStrength);
        }

        imtdIfpDetailsImpl.setContractMasterSid(contractMasterSid);
        imtdIfpDetailsImpl.setModifiedBy(modifiedBy);

        if (commitmentPeriod == null) {
            imtdIfpDetailsImpl.setCommitmentPeriod(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setCommitmentPeriod(commitmentPeriod);
        }

        if (itemNo == null) {
            imtdIfpDetailsImpl.setItemNo(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setItemNo(itemNo);
        }

        imtdIfpDetailsImpl.setIfpDetailsSid(ifpDetailsSid);
        imtdIfpDetailsImpl.setIfpModelSid(ifpModelSid);

        if (itemTherapeuticClass == null) {
            imtdIfpDetailsImpl.setItemTherapeuticClass(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setItemTherapeuticClass(itemTherapeuticClass);
        }

        if (ifpDetailsStartDate == Long.MIN_VALUE) {
            imtdIfpDetailsImpl.setIfpDetailsStartDate(null);
        } else {
            imtdIfpDetailsImpl.setIfpDetailsStartDate(new Date(
                    ifpDetailsStartDate));
        }

        if (itemForm == null) {
            imtdIfpDetailsImpl.setItemForm(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setItemForm(itemForm);
        }

        if (totalVolumeCommitment == null) {
            imtdIfpDetailsImpl.setTotalVolumeCommitment(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setTotalVolumeCommitment(totalVolumeCommitment);
        }

        if (itemEndDate == Long.MIN_VALUE) {
            imtdIfpDetailsImpl.setItemEndDate(null);
        } else {
            imtdIfpDetailsImpl.setItemEndDate(new Date(itemEndDate));
        }

        imtdIfpDetailsImpl.setCheckBox(checkBox);
        imtdIfpDetailsImpl.setIfpDetailsAttachedStatus(ifpDetailsAttachedStatus);

        if (totalMarketshareCommitment == null) {
            imtdIfpDetailsImpl.setTotalMarketshareCommitment(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setTotalMarketshareCommitment(totalMarketshareCommitment);
        }

        if (ifpDetailsAttachedDate == Long.MIN_VALUE) {
            imtdIfpDetailsImpl.setIfpDetailsAttachedDate(null);
        } else {
            imtdIfpDetailsImpl.setIfpDetailsAttachedDate(new Date(
                    ifpDetailsAttachedDate));
        }

        imtdIfpDetailsImpl.setImtdIfpDetailsSid(imtdIfpDetailsSid);

        if (sessionId == null) {
            imtdIfpDetailsImpl.setSessionId(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setSessionId(sessionId);
        }

        if (itemName == null) {
            imtdIfpDetailsImpl.setItemName(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setItemName(itemName);
        }

        if (itemPrimaryUom == null) {
            imtdIfpDetailsImpl.setItemPrimaryUom(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setItemPrimaryUom(itemPrimaryUom);
        }

        if (operation == null) {
            imtdIfpDetailsImpl.setOperation(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setOperation(operation);
        }

        if (itemBrand == null) {
            imtdIfpDetailsImpl.setItemBrand(StringPool.BLANK);
        } else {
            imtdIfpDetailsImpl.setItemBrand(itemBrand);
        }

        imtdIfpDetailsImpl.setCfpModelSid(cfpModelSid);

        imtdIfpDetailsImpl.resetOriginalValues();

        return imtdIfpDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemStatus = objectInput.readInt();
        ifpDetailsEndDate = objectInput.readLong();
        itemMasterSid = objectInput.readInt();
        imtdCreateddate = objectInput.readLong();
        itemPackageSize = objectInput.readUTF();
        ifpDetailsCreatedDate = objectInput.readLong();
        totalDollarCommitment = objectInput.readUTF();
        ifpDetailsCreatedBy = objectInput.readUTF();
        itemId = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        ifpDetailsModifiedBy = objectInput.readUTF();
        ifpDetailsModifiedDate = objectInput.readLong();
        createdDate = objectInput.readLong();
        createdBy = objectInput.readInt();
        usersSid = objectInput.readInt();
        itemDesc = objectInput.readUTF();
        itemStartDate = objectInput.readLong();
        itemStrength = objectInput.readUTF();
        contractMasterSid = objectInput.readInt();
        modifiedBy = objectInput.readInt();
        commitmentPeriod = objectInput.readUTF();
        itemNo = objectInput.readUTF();
        ifpDetailsSid = objectInput.readInt();
        ifpModelSid = objectInput.readInt();
        itemTherapeuticClass = objectInput.readUTF();
        ifpDetailsStartDate = objectInput.readLong();
        itemForm = objectInput.readUTF();
        totalVolumeCommitment = objectInput.readUTF();
        itemEndDate = objectInput.readLong();
        checkBox = objectInput.readBoolean();
        ifpDetailsAttachedStatus = objectInput.readInt();
        totalMarketshareCommitment = objectInput.readUTF();
        ifpDetailsAttachedDate = objectInput.readLong();
        imtdIfpDetailsSid = objectInput.readInt();
        sessionId = objectInput.readUTF();
        itemName = objectInput.readUTF();
        itemPrimaryUom = objectInput.readUTF();
        operation = objectInput.readUTF();
        itemBrand = objectInput.readUTF();
        cfpModelSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(itemStatus);
        objectOutput.writeLong(ifpDetailsEndDate);
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeLong(imtdCreateddate);

        if (itemPackageSize == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemPackageSize);
        }

        objectOutput.writeLong(ifpDetailsCreatedDate);

        if (totalDollarCommitment == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(totalDollarCommitment);
        }

        if (ifpDetailsCreatedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ifpDetailsCreatedBy);
        }

        if (itemId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemId);
        }

        objectOutput.writeLong(modifiedDate);

        if (ifpDetailsModifiedBy == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(ifpDetailsModifiedBy);
        }

        objectOutput.writeLong(ifpDetailsModifiedDate);
        objectOutput.writeLong(createdDate);
        objectOutput.writeInt(createdBy);
        objectOutput.writeInt(usersSid);

        if (itemDesc == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemDesc);
        }

        objectOutput.writeLong(itemStartDate);

        if (itemStrength == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemStrength);
        }

        objectOutput.writeInt(contractMasterSid);
        objectOutput.writeInt(modifiedBy);

        if (commitmentPeriod == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(commitmentPeriod);
        }

        if (itemNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemNo);
        }

        objectOutput.writeInt(ifpDetailsSid);
        objectOutput.writeInt(ifpModelSid);

        if (itemTherapeuticClass == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemTherapeuticClass);
        }

        objectOutput.writeLong(ifpDetailsStartDate);

        if (itemForm == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemForm);
        }

        if (totalVolumeCommitment == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(totalVolumeCommitment);
        }

        objectOutput.writeLong(itemEndDate);
        objectOutput.writeBoolean(checkBox);
        objectOutput.writeInt(ifpDetailsAttachedStatus);

        if (totalMarketshareCommitment == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(totalMarketshareCommitment);
        }

        objectOutput.writeLong(ifpDetailsAttachedDate);
        objectOutput.writeInt(imtdIfpDetailsSid);

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

        if (itemPrimaryUom == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemPrimaryUom);
        }

        if (operation == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(operation);
        }

        if (itemBrand == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemBrand);
        }

        objectOutput.writeInt(cfpModelSid);
    }
}
