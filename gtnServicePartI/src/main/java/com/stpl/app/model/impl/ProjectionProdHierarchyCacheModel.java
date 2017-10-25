package com.stpl.app.model.impl;

import com.stpl.app.model.ProjectionProdHierarchy;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProjectionProdHierarchy in entity cache.
 *
 * @author
 * @see ProjectionProdHierarchy
 * @generated
 */
public class ProjectionProdHierarchyCacheModel implements CacheModel<ProjectionProdHierarchy>,
    Externalizable {
    public int projectionMasterSid;
    public int projectionProdHierarchySid;
    public int relationshipLevelSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{projectionMasterSid=");
        sb.append(projectionMasterSid);
        sb.append(", projectionProdHierarchySid=");
        sb.append(projectionProdHierarchySid);
        sb.append(", relationshipLevelSid=");
        sb.append(relationshipLevelSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProjectionProdHierarchy toEntityModel() {
        ProjectionProdHierarchyImpl projectionProdHierarchyImpl = new ProjectionProdHierarchyImpl();

        projectionProdHierarchyImpl.setProjectionMasterSid(projectionMasterSid);
        projectionProdHierarchyImpl.setProjectionProdHierarchySid(projectionProdHierarchySid);
        projectionProdHierarchyImpl.setRelationshipLevelSid(relationshipLevelSid);

        projectionProdHierarchyImpl.resetOriginalValues();

        return projectionProdHierarchyImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        projectionMasterSid = objectInput.readInt();
        projectionProdHierarchySid = objectInput.readInt();
        relationshipLevelSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(projectionMasterSid);
        objectOutput.writeInt(projectionProdHierarchySid);
        objectOutput.writeInt(relationshipLevelSid);
    }
}
