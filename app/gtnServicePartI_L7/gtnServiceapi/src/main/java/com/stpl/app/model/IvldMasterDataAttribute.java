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
 * The extended model interface for the IvldMasterDataAttribute service. Represents a row in the &quot;IVLD_MASTER_DATA_ATTRIBUTE&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see IvldMasterDataAttributeModel
 * @see com.stpl.app.model.impl.IvldMasterDataAttributeImpl
 * @see com.stpl.app.model.impl.IvldMasterDataAttributeModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.IvldMasterDataAttributeImpl")
@ProviderType
public interface IvldMasterDataAttribute extends IvldMasterDataAttributeModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.IvldMasterDataAttributeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<IvldMasterDataAttribute, Integer> IVLD_MASTER_DATA_ATBTE_SID_ACCESSOR =
		new Accessor<IvldMasterDataAttribute, Integer>() {
			@Override
			public Integer get(IvldMasterDataAttribute ivldMasterDataAttribute) {
				return ivldMasterDataAttribute.getIvldMasterDataAtbteSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<IvldMasterDataAttribute> getTypeClass() {
				return IvldMasterDataAttribute.class;
			}
		};
}