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

package com.stpl.app.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the WorkflowMaster service. Represents a row in the &quot;WORKFLOW_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see WorkflowMasterModel
 * @see com.stpl.app.model.impl.WorkflowMasterImpl
 * @see com.stpl.app.model.impl.WorkflowMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.WorkflowMasterImpl")
@ProviderType
public interface WorkflowMaster extends WorkflowMasterModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.WorkflowMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WorkflowMaster, Integer> WORKFLOW_MASTER_SID_ACCESSOR =
		new Accessor<WorkflowMaster, Integer>() {
			@Override
			public Integer get(WorkflowMaster workflowMaster) {
				return workflowMaster.getWorkflowMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<WorkflowMaster> getTypeClass() {
				return WorkflowMaster.class;
			}
		};
}