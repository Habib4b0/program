package com.stpl.app.model.impl;

import com.stpl.app.model.GcmCompanyLink;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing GcmCompanyLink in entity cache.
 *
 * @author
 * @see GcmCompanyLink
 * @generated
 */
public class GcmCompanyLinkCacheModel implements CacheModel<GcmCompanyLink>,
    Externalizable {
    public boolean checkRecord;
    public int userId;
    public String companyNo;
    public String companyId;
    public int gcmCompanyLinkSid;
    public String sessionId;
    public String companyName;
    public String linkId;
    public int companyMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{checkRecord=");
        sb.append(checkRecord);
        sb.append(", userId=");
        sb.append(userId);
        sb.append(", companyNo=");
        sb.append(companyNo);
        sb.append(", companyId=");
        sb.append(companyId);
        sb.append(", gcmCompanyLinkSid=");
        sb.append(gcmCompanyLinkSid);
        sb.append(", sessionId=");
        sb.append(sessionId);
        sb.append(", companyName=");
        sb.append(companyName);
        sb.append(", linkId=");
        sb.append(linkId);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public GcmCompanyLink toEntityModel() {
        GcmCompanyLinkImpl gcmCompanyLinkImpl = new GcmCompanyLinkImpl();

        gcmCompanyLinkImpl.setCheckRecord(checkRecord);
        gcmCompanyLinkImpl.setUserId(userId);

        if (companyNo == null) {
            gcmCompanyLinkImpl.setCompanyNo(StringPool.BLANK);
        } else {
            gcmCompanyLinkImpl.setCompanyNo(companyNo);
        }

        if (companyId == null) {
            gcmCompanyLinkImpl.setCompanyId(StringPool.BLANK);
        } else {
            gcmCompanyLinkImpl.setCompanyId(companyId);
        }

        gcmCompanyLinkImpl.setGcmCompanyLinkSid(gcmCompanyLinkSid);

        if (sessionId == null) {
            gcmCompanyLinkImpl.setSessionId(StringPool.BLANK);
        } else {
            gcmCompanyLinkImpl.setSessionId(sessionId);
        }

        if (companyName == null) {
            gcmCompanyLinkImpl.setCompanyName(StringPool.BLANK);
        } else {
            gcmCompanyLinkImpl.setCompanyName(companyName);
        }

        if (linkId == null) {
            gcmCompanyLinkImpl.setLinkId(StringPool.BLANK);
        } else {
            gcmCompanyLinkImpl.setLinkId(linkId);
        }

        gcmCompanyLinkImpl.setCompanyMasterSid(companyMasterSid);

        gcmCompanyLinkImpl.resetOriginalValues();

        return gcmCompanyLinkImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        checkRecord = objectInput.readBoolean();
        userId = objectInput.readInt();
        companyNo = objectInput.readUTF();
        companyId = objectInput.readUTF();
        gcmCompanyLinkSid = objectInput.readInt();
        sessionId = objectInput.readUTF();
        companyName = objectInput.readUTF();
        linkId = objectInput.readUTF();
        companyMasterSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeBoolean(checkRecord);
        objectOutput.writeInt(userId);

        if (companyNo == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyNo);
        }

        if (companyId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyId);
        }

        objectOutput.writeInt(gcmCompanyLinkSid);

        if (sessionId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(sessionId);
        }

        if (companyName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(companyName);
        }

        if (linkId == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(linkId);
        }

        objectOutput.writeInt(companyMasterSid);
    }
}
