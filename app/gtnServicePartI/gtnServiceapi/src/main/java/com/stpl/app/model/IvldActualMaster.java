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
 * The extended model interface for the IvldActualMaster service. Represents a row in the &quot;IVLD_ACTUAL_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see IvldActualMasterModel
 * @see com.stpl.app.model.impl.IvldActualMasterImpl
 * @see com.stpl.app.model.impl.IvldActualMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.IvldActualMasterImpl")
@ProviderType
public interface IvldActualMaster extends IvldActualMasterModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.IvldActualMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<IvldActualMaster, Integer> IVLD_ACTUAL_MASTER_SID_ACCESSOR =
		new Accessor<IvldActualMaster, Integer>() {
			@Override
			public Integer get(IvldActualMaster ivldActualMaster) {
				return ivldActualMaster.getIvldActualMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<IvldActualMaster> getTypeClass() {
				return IvldActualMaster.class;
			}
		};
}