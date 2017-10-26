package com.stpl.app.model.impl;

import com.stpl.app.model.ItemHierarchyMaster;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ItemHierarchyMaster in entity cache.
 *
 * @author
 * @see ItemHierarchyMaster
 * @generated
 */
public class ItemHierarchyMasterCacheModel implements CacheModel<ItemHierarchyMaster>,
    Externalizable {
    public String level1Alias;
    public String level11Alias;
    public long createdDate;
    public String level8Alias;
    public String level14Alias;
    public String level5Alias;
    public int createdBy;
    public String level10Alias;
    public int itemHierarchyMasterSid;
    public String level17Alias;
    public String level9Alias;
    public String level0Alias;
    public long modifiedDate;
    public String level13Alias;
    public String source;
    public String level6Alias;
    public String gtnBrand;
    public int modifiedBy;
    public String level3Alias;
    public String level16Alias;
    public String batchId;
    public String level19Alias;
    public String level20;
    public String level2Alias;
    public String level20Alias;
    public String gtnTherapeuticClass;
    public String level7Alias;
    public String level0;
    public String level1;
    public String level2;
    public String level3;
    public String level12Alias;
    public String level8;
    public String level11;
    public String level4Alias;
    public String level9;
    public String level12;
    public String level13;
    public String level14;
    public boolean recordLockStatus;
    public String level0Tag;
    public String level4;
    public String level5;
    public String level6;
    public String level15Alias;
    public String level7;
    public String level10;
    public String level19;
    public String level15;
    public String level16;
    public String gtnCompany;
    public String level17;
    public String level18Alias;
    public String level18;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(111);

        sb.append("{level1Alias=");
        sb.append(level1Alias);
        sb.append(", level11Alias=");
        sb.append(level11Alias);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", level8Alias=");
        sb.append(level8Alias);
        sb.append(", level14Alias=");
        sb.append(level14Alias);
        sb.append(", level5Alias=");
        sb.append(level5Alias);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", level10Alias=");
        sb.append(level10Alias);
        sb.append(", itemHierarchyMasterSid=");
        sb.append(itemHierarchyMasterSid);
        sb.append(", level17Alias=");
        sb.append(level17Alias);
        sb.append(", level9Alias=");
        sb.append(level9Alias);
        sb.append(", level0Alias=");
        sb.append(level0Alias);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", level13Alias=");
        sb.append(level13Alias);
        sb.append(", source=");
        sb.append(source);
        sb.append(", level6Alias=");
        sb.append(level6Alias);
        sb.append(", gtnBrand=");
        sb.append(gtnBrand);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", level3Alias=");
        sb.append(level3Alias);
        sb.append(", level16Alias=");
        sb.append(level16Alias);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", level19Alias=");
        sb.append(level19Alias);
        sb.append(", level20=");
        sb.append(level20);
        sb.append(", level2Alias=");
        sb.append(level2Alias);
        sb.append(", level20Alias=");
        sb.append(level20Alias);
        sb.append(", gtnTherapeuticClass=");
        sb.append(gtnTherapeuticClass);
        sb.append(", level7Alias=");
        sb.append(level7Alias);
        sb.append(", level0=");
        sb.append(level0);
        sb.append(", level1=");
        sb.append(level1);
        sb.append(", level2=");
        sb.append(level2);
        sb.append(", level3=");
        sb.append(level3);
        sb.append(", level12Alias=");
        sb.append(level12Alias);
        sb.append(", level8=");
        sb.append(level8);
        sb.append(", level11=");
        sb.append(level11);
        sb.append(", level4Alias=");
        sb.append(level4Alias);
        sb.append(", level9=");
        sb.append(level9);
        sb.append(", level12=");
        sb.append(level12);
        sb.append(", level13=");
        sb.append(level13);
        sb.append(", level14=");
        sb.append(level14);
        sb.append(", recordLockStatus=");
        sb.append(recordLockStatus);
        sb.append(", level0Tag=");
        sb.append(level0Tag);
        sb.append(", level4=");
        sb.append(level4);
        sb.append(", level5=");
        sb.append(level5);
        sb.append(", level6=");
        sb.append(level6);
        sb.append(", level15Alias=");
        sb.append(level15Alias);
        sb.append(", level7=");
        sb.append(level7);
        sb.append(", level10=");
        sb.append(level10);
        sb.append(", level19=");
        sb.append(level19);
        sb.append(", level15=");
        sb.append(level15);
        sb.append(", level16=");
        sb.append(level16);
        sb.append(", gtnCompany=");
        sb.append(gtnCompany);
        sb.append(", level17=");
        sb.append(level17);
        sb.append(", level18Alias=");
        sb.append(level18Alias);
        sb.append(", level18=");
        sb.append(level18);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ItemHierarchyMaster toEntityModel() {
        ItemHierarchyMasterImpl itemHierarchyMasterImpl = new ItemHierarchyMasterImpl();

        if (level1Alias == null) {
            itemHierarchyMasterImpl.setLevel1Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel1Alias(level1Alias);
        }

        if (level11Alias == null) {
            itemHierarchyMasterImpl.setLevel11Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel11Alias(level11Alias);
        }

        if (createdDate == Long.MIN_VALUE) {
            itemHierarchyMasterImpl.setCreatedDate(null);
        } else {
            itemHierarchyMasterImpl.setCreatedDate(new Date(createdDate));
        }

        if (level8Alias == null) {
            itemHierarchyMasterImpl.setLevel8Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel8Alias(level8Alias);
        }

        if (level14Alias == null) {
            itemHierarchyMasterImpl.setLevel14Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel14Alias(level14Alias);
        }

        if (level5Alias == null) {
            itemHierarchyMasterImpl.setLevel5Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel5Alias(level5Alias);
        }

        itemHierarchyMasterImpl.setCreatedBy(createdBy);

        if (level10Alias == null) {
            itemHierarchyMasterImpl.setLevel10Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel10Alias(level10Alias);
        }

        itemHierarchyMasterImpl.setItemHierarchyMasterSid(itemHierarchyMasterSid);

        if (level17Alias == null) {
            itemHierarchyMasterImpl.setLevel17Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel17Alias(level17Alias);
        }

        if (level9Alias == null) {
            itemHierarchyMasterImpl.setLevel9Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel9Alias(level9Alias);
        }

        if (level0Alias == null) {
            itemHierarchyMasterImpl.setLevel0Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel0Alias(level0Alias);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            itemHierarchyMasterImpl.setModifiedDate(null);
        } else {
            itemHierarchyMasterImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (level13Alias == null) {
            itemHierarchyMasterImpl.setLevel13Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel13Alias(level13Alias);
        }

        if (source == null) {
            itemHierarchyMasterImpl.setSource(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setSource(source);
        }

        if (level6Alias == null) {
            itemHierarchyMasterImpl.setLevel6Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel6Alias(level6Alias);
        }

        if (gtnBrand == null) {
            itemHierarchyMasterImpl.setGtnBrand(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setGtnBrand(gtnBrand);
        }

        itemHierarchyMasterImpl.setModifiedBy(modifiedBy);

        if (level3Alias == null) {
            itemHierarchyMasterImpl.setLevel3Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel3Alias(level3Alias);
        }

        if (level16Alias == null) {
            itemHierarchyMasterImpl.setLevel16Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel16Alias(level16Alias);
        }

        if (batchId == null) {
            itemHierarchyMasterImpl.setBatchId(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setBatchId(batchId);
        }

        if (level19Alias == null) {
            itemHierarchyMasterImpl.setLevel19Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel19Alias(level19Alias);
        }

        if (level20 == null) {
            itemHierarchyMasterImpl.setLevel20(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel20(level20);
        }

        if (level2Alias == null) {
            itemHierarchyMasterImpl.setLevel2Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel2Alias(level2Alias);
        }

        if (level20Alias == null) {
            itemHierarchyMasterImpl.setLevel20Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel20Alias(level20Alias);
        }

        if (gtnTherapeuticClass == null) {
            itemHierarchyMasterImpl.setGtnTherapeuticClass(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setGtnTherapeuticClass(gtnTherapeuticClass);
        }

        if (level7Alias == null) {
            itemHierarchyMasterImpl.setLevel7Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel7Alias(level7Alias);
        }

        if (level0 == null) {
            itemHierarchyMasterImpl.setLevel0(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel0(level0);
        }

        if (level1 == null) {
            itemHierarchyMasterImpl.setLevel1(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel1(level1);
        }

        if (level2 == null) {
            itemHierarchyMasterImpl.setLevel2(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel2(level2);
        }

        if (level3 == null) {
            itemHierarchyMasterImpl.setLevel3(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel3(level3);
        }

        if (level12Alias == null) {
            itemHierarchyMasterImpl.setLevel12Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel12Alias(level12Alias);
        }

        if (level8 == null) {
            itemHierarchyMasterImpl.setLevel8(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel8(level8);
        }

        if (level11 == null) {
            itemHierarchyMasterImpl.setLevel11(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel11(level11);
        }

        if (level4Alias == null) {
            itemHierarchyMasterImpl.setLevel4Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel4Alias(level4Alias);
        }

        if (level9 == null) {
            itemHierarchyMasterImpl.setLevel9(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel9(level9);
        }

        if (level12 == null) {
            itemHierarchyMasterImpl.setLevel12(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel12(level12);
        }

        if (level13 == null) {
            itemHierarchyMasterImpl.setLevel13(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel13(level13);
        }

        if (level14 == null) {
            itemHierarchyMasterImpl.setLevel14(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel14(level14);
        }

        itemHierarchyMasterImpl.setRecordLockStatus(recordLockStatus);

        if (level0Tag == null) {
            itemHierarchyMasterImpl.setLevel0Tag(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel0Tag(level0Tag);
        }

        if (level4 == null) {
            itemHierarchyMasterImpl.setLevel4(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel4(level4);
        }

        if (level5 == null) {
            itemHierarchyMasterImpl.setLevel5(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel5(level5);
        }

        if (level6 == null) {
            itemHierarchyMasterImpl.setLevel6(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel6(level6);
        }

        if (level15Alias == null) {
            itemHierarchyMasterImpl.setLevel15Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel15Alias(level15Alias);
        }

        if (level7 == null) {
            itemHierarchyMasterImpl.setLevel7(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel7(level7);
        }

        if (level10 == null) {
            itemHierarchyMasterImpl.setLevel10(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel10(level10);
        }

        if (level19 == null) {
            itemHierarchyMasterImpl.setLevel19(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel19(level19);
        }

        if (level15 == null) {
            itemHierarchyMasterImpl.setLevel15(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel15(level15);
        }

        if (level16 == null) {
            itemHierarchyMasterImpl.setLevel16(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel16(level16);
        }

        if (gtnCompany == null) {
            itemHierarchyMasterImpl.setGtnCompany(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setGtnCompany(gtnCompany);
        }

        if (level17 == null) {
            itemHierarchyMasterImpl.setLevel17(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel17(level17);
        }

        if (level18Alias == null) {
            itemHierarchyMasterImpl.setLevel18Alias(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel18Alias(level18Alias);
        }

        if (level18 == null) {
            itemHierarchyMasterImpl.setLevel18(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setLevel18(level18);
        }

        if (inboundStatus == null) {
            itemHierarchyMasterImpl.setInboundStatus(StringPool.BLANK);
        } else {
            itemHierarchyMasterImpl.setInboundStatus(inboundStatus);
        }

        itemHierarchyMasterImpl.resetOriginalValues();

        return itemHierarchyMasterImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        level1Alias = objectInput.readUTF();
        level11Alias = objectInput.readUTF();
        createdDate = objectInput.readLong();
        level8Alias = objectInput.readUTF();
        level14Alias = objectInput.readUTF();
        level5Alias = objectInput.readUTF();
        createdBy = objectInput.readInt();
        level10Alias = objectInput.readUTF();
        itemHierarchyMasterSid = objectInput.readInt();
        level17Alias = objectInput.readUTF();
        level9Alias = objectInput.readUTF();
        level0Alias = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        level13Alias = objectInput.readUTF();
        source = objectInput.readUTF();
        level6Alias = objectInput.readUTF();
        gtnBrand = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        level3Alias = objectInput.readUTF();
        level16Alias = objectInput.readUTF();
        batchId = objectInput.readUTF();
        level19Alias = objectInput.readUTF();
        level20 = objectInput.readUTF();
        level2Alias = objectInput.readUTF();
        level20Alias = objectInput.readUTF();
        gtnTherapeuticClass = objectInput.readUTF();
        level7Alias = objectInput.readUTF();
        level0 = objectInput.readUTF();
        level1 = objectInput.readUTF();
        level2 = objectInput.readUTF();
        level3 = objectInput.readUTF();
        level12Alias = objectInput.readUTF();
        level8 = objectInput.readUTF();
        level11 = objectInput.readUTF();
        level4Alias = objectInput.readUTF();
        level9 = objectInput.readUTF();
        level12 = objectInput.readUTF();
        level13 = objectInput.readUTF();
        level14 = objectInput.readUTF();
        recordLockStatus = objectInput.readBoolean();
        level0Tag = objectInput.readUTF();
        level4 = objectInput.readUTF();
        level5 = objectInput.readUTF();
        level6 = objectInput.readUTF();
        level15Alias = objectInput.readUTF();
        level7 = objectInput.readUTF();
        level10 = objectInput.readUTF();
        level19 = objectInput.readUTF();
        level15 = objectInput.readUTF();
        level16 = objectInput.readUTF();
        gtnCompany = objectInput.readUTF();
        level17 = objectInput.readUTF();
        level18Alias = objectInput.readUTF();
        level18 = objectInput.readUTF();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (level1Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level1Alias);
        }

        if (level11Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level11Alias);
        }

        objectOutput.writeLong(createdDate);

        if (level8Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level8Alias);
        }

        if (level14Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level14Alias);
        }

        if (level5Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level5Alias);
        }

        objectOutput.writeInt(createdBy);

        if (level10Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level10Alias);
        }

        objectOutput.writeInt(itemHierarchyMasterSid);

        if (level17Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level17Alias);
        }

        if (level9Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level9Alias);
        }

        if (level0Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level0Alias);
        }

        objectOutput.writeLong(modifiedDate);

        if (level13Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level13Alias);
        }

        if (source == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(source);
        }

        if (level6Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level6Alias);
        }

        if (gtnBrand == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(gtnBrand);
        }

        objectOutput.writeInt(modifiedBy);

        if (level3Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level3Alias);
        }

        if (level16Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level16Alias);
        }

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (level19Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level19Alias);
        }

        if (level20 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level20);
        }

        if (level2Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level2Alias);
        }

        if (level20Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level20Alias);
        }

        if (gtnTherapeuticClass == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(gtnTherapeuticClass);
        }

        if (level7Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level7Alias);
        }

        if (level0 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level0);
        }

        if (level1 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level1);
        }

        if (level2 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level2);
        }

        if (level3 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level3);
        }

        if (level12Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level12Alias);
        }

        if (level8 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level8);
        }

        if (level11 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level11);
        }

        if (level4Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level4Alias);
        }

        if (level9 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level9);
        }

        if (level12 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level12);
        }

        if (level13 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level13);
        }

        if (level14 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level14);
        }

        objectOutput.writeBoolean(recordLockStatus);

        if (level0Tag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level0Tag);
        }

        if (level4 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level4);
        }

        if (level5 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level5);
        }

        if (level6 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level6);
        }

        if (level15Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level15Alias);
        }

        if (level7 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level7);
        }

        if (level10 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level10);
        }

        if (level19 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level19);
        }

        if (level15 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level15);
        }

        if (level16 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level16);
        }

        if (gtnCompany == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(gtnCompany);
        }

        if (level17 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level17);
        }

        if (level18Alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level18Alias);
        }

        if (level18 == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(level18);
        }

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
