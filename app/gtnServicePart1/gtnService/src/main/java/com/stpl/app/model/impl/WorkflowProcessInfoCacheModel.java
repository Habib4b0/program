/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.model.WorkflowProcessInfo;

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
@ProviderType
public class WorkflowProcessInfoCacheModel implements CacheModel<WorkflowProcessInfo>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkflowProcessInfoCacheModel)) {
			return false;
		}

		WorkflowProcessInfoCacheModel workflowProcessInfoCacheModel = (WorkflowProcessInfoCacheModel)obj;

		if (workflowProcessInfoSid == workflowProcessInfoCacheModel.workflowProcessInfoSid) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, workflowProcessInfoSid);
	}

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
		}
		else {
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
		}
		else {
			objectOutput.writeUTF(contractStructure);
		}
	}

	public int processInstanceId;
	public int projectionMasterSid;
	public int workflowProcessInfoSid;
	public int accClosureMasterSid;
	public int contractMasterSid;
	public String contractStructure;
}