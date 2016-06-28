package com.stpl.app.model.impl;

import com.stpl.app.model.StSelectionTable;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing StSelectionTable in entity cache.
 *
 * @author
 * @see StSelectionTable
 * @generated
 */
public class StSelectionTableCacheModel implements CacheModel<StSelectionTable>,
    Externalizable {
    public String selectionType;
    public int userId;
    public String sessionId;
    public int companyItemSid;
    public boolean checkRecord;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{selectionType=");
        sb.append(selectionType);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", companyItemSid=");
        sb.append(companyItemSid);
        sb.append(", checkRecord=");
        sb.append(checkRecord);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public StSelectionTable toEntityModel() {
        StSelectionTableImpl stSelectionTableImpl = new StSelectionTableImpl();

        if (selectionType == null) {
            stSelectionTableImpl.setSelectionType(StringPool.BLANK);
        } else {
            stSelectionTableImpl.setSelectionType(selectionType);
        }

        stSelectionTableImpl.setUserId(userId);

        if (sessionId == null) {
            stSelectionTableImpl.setSessionId(StringPool.BLANK);
        } else {
            stSelectionTableImpl.setSessionId(sessionId);
        }

        stSelectionTableImpl.setCompanyItemSid(companyItemSid);
        stSelectionTableImpl.setCheckRecord(checkRecord);

        stSelectionTableImpl.resetOriginalValues();

        return stSelectionTableImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        selectionType = objectInput.readUTF();
        userId = objectInput.readInt();
        sessionId = objectInput.readUTF();
        companyItemSid = objectInput.readInt();
        checkRecord = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (selectionType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(selectionType);
        }

        objectOutput.writeInt(userId);

        if (sessionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sessionId);
        }

        objectOutput.writeInt(companyItemSid);
        objectOutput.writeBoolean(checkRecord);
    }
}
