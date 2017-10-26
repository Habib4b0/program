package com.stpl.app.model.impl;

import com.stpl.app.model.ProjectionCustHierarchy;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProjectionCustHierarchy in entity cache.
 *
 * @author
 * @see ProjectionCustHierarchy
 * @generated
 */
public class ProjectionCustHierarchyCacheModel implements CacheModel<ProjectionCustHierarchy>,
    Externalizable {
    public int projectionMasterSid;
    public int projectionCustHierarchySid;
    public int relationshipLevelSid;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(7);

        sb.append("{projectionMasterSid=");
        sb.append(projectionMasterSid);
        sb.append(", projectionCustHierarchySid=");
        sb.append(projectionCustHierarchySid);
        sb.append(", relationshipLevelSid=");
        sb.append(relationshipLevelSid);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProjectionCustHierarchy toEntityModel() {
        ProjectionCustHierarchyImpl projectionCustHierarchyImpl = new ProjectionCustHierarchyImpl();

        projectionCustHierarchyImpl.setProjectionMasterSid(projectionMasterSid);
        projectionCustHierarchyImpl.setProjectionCustHierarchySid(projectionCustHierarchySid);
        projectionCustHierarchyImpl.setRelationshipLevelSid(relationshipLevelSid);

        projectionCustHierarchyImpl.resetOriginalValues();

        return projectionCustHierarchyImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        projectionMasterSid = objectInput.readInt();
        projectionCustHierarchySid = objectInput.readInt();
        relationshipLevelSid = objectInput.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(projectionMasterSid);
        objectOutput.writeInt(projectionCustHierarchySid);
        objectOutput.writeInt(relationshipLevelSid);
    }
}
