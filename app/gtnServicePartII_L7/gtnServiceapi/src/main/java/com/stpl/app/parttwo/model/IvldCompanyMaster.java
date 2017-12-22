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

package com.stpl.app.parttwo.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the IvldCompanyMaster service. Represents a row in the &quot;IVLD_COMPANY_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see IvldCompanyMasterModel
 * @see com.stpl.app.parttwo.model.impl.IvldCompanyMasterImpl
 * @see com.stpl.app.parttwo.model.impl.IvldCompanyMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.IvldCompanyMasterImpl")
@ProviderType
public interface IvldCompanyMaster extends IvldCompanyMasterModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.IvldCompanyMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<IvldCompanyMaster, Integer> IVLD_COMPANY_MASTER_SID_ACCESSOR =
		new Accessor<IvldCompanyMaster, Integer>() {
			@Override
			public Integer get(IvldCompanyMaster ivldCompanyMaster) {
				return ivldCompanyMaster.getIvldCompanyMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<IvldCompanyMaster> getTypeClass() {
				return IvldCompanyMaster.class;
			}
		};
}