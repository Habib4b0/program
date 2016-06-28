package com.stpl.app.model.impl;

import com.stpl.app.model.WfMailConfig;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WfMailConfig in entity cache.
 *
 * @author
 * @see WfMailConfig
 * @generated
 */
public class WfMailConfigCacheModel implements CacheModel<WfMailConfig>,
    Externalizable {
    public String smtpFlag;
    public int createdBy;
    public String emailAddress;
    public String password;
    public int modifiedBy;
    public int wfMailConfigSid;
    public String hostName;
    public long createdDate;
    public String portNumber;
    public long modifiedDate;
    public String inboundStatus;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(23);

        sb.append("{smtpFlag=");
        sb.append(smtpFlag);
        sb.append(", createdBy=");
        sb.append(createdBy);
        sb.append(", emailAddress=");
        sb.append(emailAddress);
        sb.append(", password=");
        sb.append(password);
        sb.append(", modifiedBy=");
        sb.append(modifiedBy);
        sb.append(", wfMailConfigSid=");
        sb.append(wfMailConfigSid);
        sb.append(", hostName=");
        sb.append(hostName);
        sb.append(", createdDate=");
        sb.append(createdDate);
        sb.append(", portNumber=");
        sb.append(portNumber);
        sb.append(", modifiedDate=");
        sb.append(modifiedDate);
        sb.append(", inboundStatus=");
        sb.append(inboundStatus);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public WfMailConfig toEntityModel() {
        WfMailConfigImpl wfMailConfigImpl = new WfMailConfigImpl();

        if (smtpFlag == null) {
            wfMailConfigImpl.setSmtpFlag(StringPool.BLANK);
        } else {
            wfMailConfigImpl.setSmtpFlag(smtpFlag);
        }

        wfMailConfigImpl.setCreatedBy(createdBy);

        if (emailAddress == null) {
            wfMailConfigImpl.setEmailAddress(StringPool.BLANK);
        } else {
            wfMailConfigImpl.setEmailAddress(emailAddress);
        }

        if (password == null) {
            wfMailConfigImpl.setPassword(StringPool.BLANK);
        } else {
            wfMailConfigImpl.setPassword(password);
        }

        wfMailConfigImpl.setModifiedBy(modifiedBy);
        wfMailConfigImpl.setWfMailConfigSid(wfMailConfigSid);

        if (hostName == null) {
            wfMailConfigImpl.setHostName(StringPool.BLANK);
        } else {
            wfMailConfigImpl.setHostName(hostName);
        }

        if (createdDate == Long.MIN_VALUE) {
            wfMailConfigImpl.setCreatedDate(null);
        } else {
            wfMailConfigImpl.setCreatedDate(new Date(createdDate));
        }

        if (portNumber == null) {
            wfMailConfigImpl.setPortNumber(StringPool.BLANK);
        } else {
            wfMailConfigImpl.setPortNumber(portNumber);
        }

        if (modifiedDate == Long.MIN_VALUE) {
            wfMailConfigImpl.setModifiedDate(null);
        } else {
            wfMailConfigImpl.setModifiedDate(new Date(modifiedDate));
        }

        if (inboundStatus == null) {
            wfMailConfigImpl.setInboundStatus(StringPool.BLANK);
        } else {
            wfMailConfigImpl.setInboundStatus(inboundStatus);
        }

        wfMailConfigImpl.resetOriginalValues();

        return wfMailConfigImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        smtpFlag = objectInput.readUTF();
        createdBy = objectInput.readInt();
        emailAddress = objectInput.readUTF();
        password = objectInput.readUTF();
        modifiedBy = objectInput.readInt();
        wfMailConfigSid = objectInput.readInt();
        hostName = objectInput.readUTF();
        createdDate = objectInput.readLong();
        portNumber = objectInput.readUTF();
        modifiedDate = objectInput.readLong();
        inboundStatus = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        if (smtpFlag == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(smtpFlag);
        }

        objectOutput.writeInt(createdBy);

        if (emailAddress == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(emailAddress);
        }

        if (password == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(password);
        }

        objectOutput.writeInt(modifiedBy);
        objectOutput.writeInt(wfMailConfigSid);

        if (hostName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(hostName);
        }

        objectOutput.writeLong(createdDate);

        if (portNumber == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(portNumber);
        }

        objectOutput.writeLong(modifiedDate);

        if (inboundStatus == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(inboundStatus);
        }
    }
}
