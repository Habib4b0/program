package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.AccClosureDetails;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AccClosureDetails in entity cache.
 *
 * @author
 * @see AccClosureDetails
 * @generated
 */
public class AccClosureDetailsCacheModel implements CacheModel<AccClosureDetails>,
    Externalizable {
    public int accClosureDetailsSid;
    public int ccpDetailsSid;
    public int accClosureMasterSid;
    public int rsModelSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{accClosureDetailsSid=");
        sb.append(accClosureDetailsSid);
        sb.append(", ccpDetailsSid=");
        sb.append(ccpDetailsSid);
        sb.append(", accClosureMasterSid=");
        sb.append(accClosureMasterSid);
        sb.append(", rsModelSid=");
        sb.append(rsModelSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public AccClosureDetails toEntityModel() {
        AccClosureDetailsImpl accClosureDetailsImpl = new AccClosureDetailsImpl();

        accClosureDetailsImpl.setAccClosureDetailsSid(accClosureDetailsSid);
        accClosureDetailsImpl.setCcpDetailsSid(ccpDetailsSid);
        accClosureDetailsImpl.setAccClosureMasterSid(accClosureMasterSid);
        accClosureDetailsImpl.setRsModelSid(rsModelSid);

        accClosureDetailsImpl.resetOriginalValues();

        return accClosureDetailsImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        accClosureDetailsSid = objectInput.readInt();
        ccpDetailsSid = objectInput.readInt();
        accClosureMasterSid = objectInput.readInt();
        rsModelSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(accClosureDetailsSid);
        objectOutput.writeInt(ccpDetailsSid);
        objectOutput.writeInt(accClosureMasterSid);
        objectOutput.writeInt(rsModelSid);
    }
}
