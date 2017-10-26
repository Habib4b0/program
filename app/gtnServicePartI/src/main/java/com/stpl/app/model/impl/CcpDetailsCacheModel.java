package com.stpl.app.model.impl;

import com.stpl.app.model.CcpDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CcpDetails in entity cache.
 *
 * @author
 * @see CcpDetails
 * @generated
 */
public class CcpDetailsCacheModel implements CacheModel<CcpDetails>,
    Externalizable {
    public int itemMasterSid;
    public int contractMasterSid;
    public int ccpDetailsSid;
    public int companyMasterSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{itemMasterSid=");
        sb.append(itemMasterSid);
        sb.append(", contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", ccpDetailsSid=");
        sb.append(ccpDetailsSid);
        sb.append(", companyMasterSid=");
        sb.append(companyMasterSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CcpDetails toEntityModel() {
        CcpDetailsImpl ccpDetailsImpl = new CcpDetailsImpl();

        ccpDetailsImpl.setItemMasterSid(itemMasterSid);
        ccpDetailsImpl.setContractMasterSid(contractMasterSid);
        ccpDetailsImpl.setCcpDetailsSid(ccpDetailsSid);
        ccpDetailsImpl.setCompanyMasterSid(companyMasterSid);

        ccpDetailsImpl.resetOriginalValues();

        return ccpDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        itemMasterSid = objectInput.readInt();
        contractMasterSid = objectInput.readInt();
        ccpDetailsSid = objectInput.readInt();
        companyMasterSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(itemMasterSid);
        objectOutput.writeInt(contractMasterSid);
        objectOutput.writeInt(ccpDetailsSid);
        objectOutput.writeInt(companyMasterSid);
    }
}
