package com.stpl.app.parttwo.model.impl;

import com.stpl.app.parttwo.model.CffProdHierarchy;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CffProdHierarchy in entity cache.
 *
 * @author
 * @see CffProdHierarchy
 * @generated
 */
public class CffProdHierarchyCacheModel implements CacheModel<CffProdHierarchy>,
    Externalizable {
    public int cffMasterSid;
    public int relationshipLevelSid;
    public int cffProdHierarchySid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{cffMasterSid=");
        sb.append(cffMasterSid);
        sb.append(", relationshipLevelSid=");
        sb.append(relationshipLevelSid);
        sb.append(", cffProdHierarchySid=");
        sb.append(cffProdHierarchySid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public CffProdHierarchy toEntityModel() {
        CffProdHierarchyImpl cffProdHierarchyImpl = new CffProdHierarchyImpl();

        cffProdHierarchyImpl.setCffMasterSid(cffMasterSid);
        cffProdHierarchyImpl.setRelationshipLevelSid(relationshipLevelSid);
        cffProdHierarchyImpl.setCffProdHierarchySid(cffProdHierarchySid);

        cffProdHierarchyImpl.resetOriginalValues();

        return cffProdHierarchyImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        cffMasterSid = objectInput.readInt();
        relationshipLevelSid = objectInput.readInt();
        cffProdHierarchySid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(cffMasterSid);
        objectOutput.writeInt(relationshipLevelSid);
        objectOutput.writeInt(cffProdHierarchySid);
    }
}
