package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CffCustHierarchy;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CffCustHierarchy in entity cache.
 *
 * @author
 * @see CffCustHierarchy
 * @generated
 */
public class CffCustHierarchyCacheModel implements CacheModel<CffCustHierarchy>,
    Externalizable {
    public int cffCustHierarchySid;
    public int cffMasterSid;
    public int relationshipLevelSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{cffCustHierarchySid=");
        sb.append(cffCustHierarchySid);
        sb.append(", cffMasterSid=");
        sb.append(cffMasterSid);
        sb.append(", relationshipLevelSid=");
        sb.append(relationshipLevelSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CffCustHierarchy toEntityModel() {
        CffCustHierarchyImpl cffCustHierarchyImpl = new CffCustHierarchyImpl();

        cffCustHierarchyImpl.setCffCustHierarchySid(cffCustHierarchySid);
        cffCustHierarchyImpl.setCffMasterSid(cffMasterSid);
        cffCustHierarchyImpl.setRelationshipLevelSid(relationshipLevelSid);

        cffCustHierarchyImpl.resetOriginalValues();

        return cffCustHierarchyImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        cffCustHierarchySid = objectInput.readInt();
        cffMasterSid = objectInput.readInt();
        relationshipLevelSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(cffCustHierarchySid);
        objectOutput.writeInt(cffMasterSid);
        objectOutput.writeInt(relationshipLevelSid);
    }
}
