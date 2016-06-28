package com.stpl.app.model.impl;

import com.stpl.app.model.IvldItemHierarchyDefinition;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing IvldItemHierarchyDefinition in entity cache.
 *
 * @author
 * @see IvldItemHierarchyDefinition
 * @generated
 */
public class IvldItemHierarchyDefinitionCacheModel implements CacheModel<IvldItemHierarchyDefinition>,
    Externalizable {
    public String member;
    public String reasonForFailure;
    public String itemHierarchyDefnIntfid;
    public String bpiLvl;
    public long modifiedDate;
    public String alias;
    public String createdBy;
    public long createdDate;
    public String source;
    public String batchId;
    public String addChgDelIndicator;
    public int ivldItemHierarchyDefinitionSid;
    public String errorField;
    public String errorCode;
    public long intfInsertedDate;
    public String modifiedBy;
    public String reprocessedFlag;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(35);

        sb.append("{member=");
        sb.append(member);
        sb.append(", reasonForFailure=");
        sb.append(reasonForFailure);
        sb.append(", itemHierarchyDefnIntfid=");
        sb.append(itemHierarchyDefnIntfid);
        sb.append(", bpiLvl=");
        sb.append(bpiLvl);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", alias=");
        sb.append(alias);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", source=");
        sb.append(source);
        sb.append(", batchId=");
        sb.append(batchId);
        sb.append(", addChgDelIndicator=");
        sb.append(addChgDelIndicator);
        sb.append(", ivldItemHierarchyDefinitionSid=");
        sb.append(ivldItemHierarchyDefinitionSid);
        sb.append(", errorField=");
        sb.append(errorField);
        sb.append(", errorCode=");
        sb.append(errorCode);
        sb.append(", intfInsertedDate=");
        sb.append(intfInsertedDate);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", reprocessedFlag=");
        sb.append(reprocessedFlag);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public IvldItemHierarchyDefinition toEntityModel() {
        IvldItemHierarchyDefinitionImpl ivldItemHierarchyDefinitionImpl = new IvldItemHierarchyDefinitionImpl();

        if (member == null) {
            ivldItemHierarchyDefinitionImpl.setMember(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setMember(member);
        }

        if (reasonForFailure == null) {
            ivldItemHierarchyDefinitionImpl.setReasonForFailure(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setReasonForFailure(reasonForFailure);
        }

        if (itemHierarchyDefnIntfid == null) {
            ivldItemHierarchyDefinitionImpl.setItemHierarchyDefnIntfid(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setItemHierarchyDefnIntfid(itemHierarchyDefnIntfid);
        }

        if (bpiLvl == null) {
            ivldItemHierarchyDefinitionImpl.setBpiLvl(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setBpiLvl(bpiLvl);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            ivldItemHierarchyDefinitionImpl.setModifiedDate(null);
        } else {
            ivldItemHierarchyDefinitionImpl.setModifiedDate(new Date(
                    modifiedDate));
        }

        if (alias == null) {
            ivldItemHierarchyDefinitionImpl.setAlias(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setAlias(alias);
        }

        if (createdBy == null) {
            ivldItemHierarchyDefinitionImpl.setCreatedBy(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setCreatedBy(createdBy);
        }

        if (createdDate == Long.MIN_VALUE) {
            ivldItemHierarchyDefinitionImpl.setCreatedDate(null);
        } else {
            ivldItemHierarchyDefinitionImpl.setCreatedDate(new Date(createdDate));
        }

        if (source == null) {
            ivldItemHierarchyDefinitionImpl.setSource(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setSource(source);
        }

        if (batchId == null) {
            ivldItemHierarchyDefinitionImpl.setBatchId(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setBatchId(batchId);
        }

        if (addChgDelIndicator == null) {
            ivldItemHierarchyDefinitionImpl.setAddChgDelIndicator(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setAddChgDelIndicator(addChgDelIndicator);
        }

        ivldItemHierarchyDefinitionImpl.setIvldItemHierarchyDefinitionSid(ivldItemHierarchyDefinitionSid);

        if (errorField == null) {
            ivldItemHierarchyDefinitionImpl.setErrorField(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setErrorField(errorField);
        }

        if (errorCode == null) {
            ivldItemHierarchyDefinitionImpl.setErrorCode(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setErrorCode(errorCode);
        }

        if (intfInsertedDate == Long.MIN_VALUE) {
            ivldItemHierarchyDefinitionImpl.setIntfInsertedDate(null);
        } else {
            ivldItemHierarchyDefinitionImpl.setIntfInsertedDate(new Date(
                    intfInsertedDate));
        }

        if (modifiedBy == null) {
            ivldItemHierarchyDefinitionImpl.setModifiedBy(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setModifiedBy(modifiedBy);
        }

        if (reprocessedFlag == null) {
            ivldItemHierarchyDefinitionImpl.setReprocessedFlag(StringPool.BLANK);
        } else {
            ivldItemHierarchyDefinitionImpl.setReprocessedFlag(reprocessedFlag);
        }

        ivldItemHierarchyDefinitionImpl.resetOriginalValues();

        return ivldItemHierarchyDefinitionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        member = objectInput.readUTF();
        reasonForFailure = objectInput.readUTF();
        itemHierarchyDefnIntfid = objectInput.readUTF();
        bpiLvl = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        alias = objectInput.readUTF();
        createdBy = objectInput.readUTF();
        createdDate = objectInput.readLong();
        source = objectInput.readUTF();
        batchId = objectInput.readUTF();
        addChgDelIndicator = objectInput.readUTF();
        ivldItemHierarchyDefinitionSid = objectInput.readInt();
        errorField = objectInput.readUTF();
        errorCode = objectInput.readUTF();
        intfInsertedDate = objectInput.readLong();
        modifiedBy = objectInput.readUTF();
        reprocessedFlag = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (member == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(member);
        }

        if (reasonForFailure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reasonForFailure);
        }

        if (itemHierarchyDefnIntfid == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(itemHierarchyDefnIntfid);
        }

        if (bpiLvl == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(bpiLvl);
        }

        objectOutput.writeLong(modifiedDate);

        if (alias == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(alias);
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

        if (batchId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(batchId);
        }

        if (addChgDelIndicator == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(addChgDelIndicator);
        }

        objectOutput.writeInt(ivldItemHierarchyDefinitionSid);

        if (errorField == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(errorField);
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

        if (reprocessedFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(reprocessedFlag);
        }
    }
}
