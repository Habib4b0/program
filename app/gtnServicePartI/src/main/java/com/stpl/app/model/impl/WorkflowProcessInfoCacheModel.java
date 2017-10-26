package com.stpl.app.model.impl;

import com.stpl.app.model.WorkflowProcessInfo;

import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.kernel.util.StringPool;
import com.stpl.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing WorkflowProcessInfo in entity cache.
 *
 * @author
 * @see WorkflowProcessInfo
 * @generated
 */
public class WorkflowProcessInfoCacheModel implements CacheModel<WorkflowProcessInfo>,
    Externalizable {
    public int processInstanceId;
    public int projectionMasterSid;
    public int workflowProcessInfoSid;
    public int accClosureMasterSid;
    public int contractMasterSid;
    public String contractStructure;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{processInstanceId=");
        sb.append(processInstanceId);
        sb.append(", projectionMasterSid=");
        sb.append(projectionMasterSid);
        sb.append(", workflowProcessInfoSid=");
        sb.append(workflowProcessInfoSid);
        sb.append(", accClosureMasterSid=");
        sb.append(accClosureMasterSid);
        sb.append(", contractMasterSid=");
        sb.append(contractMasterSid);
        sb.append(", contractStructure=");
        sb.append(contractStructure);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public WorkflowProcessInfo toEntityModel() {
        WorkflowProcessInfoImpl workflowProcessInfoImpl = new WorkflowProcessInfoImpl();

        workflowProcessInfoImpl.setProcessInstanceId(processInstanceId);
        workflowProcessInfoImpl.setProjectionMasterSid(projectionMasterSid);
        workflowProcessInfoImpl.setWorkflowProcessInfoSid(workflowProcessInfoSid);
        workflowProcessInfoImpl.setAccClosureMasterSid(accClosureMasterSid);
        workflowProcessInfoImpl.setContractMasterSid(contractMasterSid);

        if (contractStructure == null) {
            workflowProcessInfoImpl.setContractStructure(StringPool.BLANK);
        } else {
            workflowProcessInfoImpl.setContractStructure(contractStructure);
        }

        workflowProcessInfoImpl.resetOriginalValues();

        return workflowProcessInfoImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        processInstanceId = objectInput.readInt();
        projectionMasterSid = objectInput.readInt();
        workflowProcessInfoSid = objectInput.readInt();
        accClosureMasterSid = objectInput.readInt();
        contractMasterSid = objectInput.readInt();
        contractStructure = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeInt(processInstanceId);
        objectOutput.writeInt(projectionMasterSid);
        objectOutput.writeInt(workflowProcessInfoSid);
        objectOutput.writeInt(accClosureMasterSid);
        objectOutput.writeInt(contractMasterSid);

        if (contractStructure == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contractStructure);
        }
    }
}
