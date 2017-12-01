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
 * The extended model interface for the LotMaster service. Represents a row in the &quot;LOT_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see LotMasterModel
 * @see com.stpl.app.model.impl.LotMasterImpl
 * @see com.stpl.app.model.impl.LotMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.LotMasterImpl")
@ProviderType
public interface LotMaster extends LotMasterModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.LotMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LotMaster, Integer> LOT_MASTER_SID_ACCESSOR = new Accessor<LotMaster, Integer>() {
			@Override
			public Integer get(LotMaster lotMaster) {
				return lotMaster.getLotMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<LotMaster> getTypeClass() {
				return LotMaster.class;
			}
		};
}