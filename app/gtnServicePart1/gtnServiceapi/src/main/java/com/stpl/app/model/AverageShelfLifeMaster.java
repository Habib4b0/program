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
 * The extended model interface for the AverageShelfLifeMaster service. Represents a row in the &quot;AVERAGE_SHELF_LIFE_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see AverageShelfLifeMasterModel
 * @see com.stpl.app.model.impl.AverageShelfLifeMasterImpl
 * @see com.stpl.app.model.impl.AverageShelfLifeMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.AverageShelfLifeMasterImpl")
@ProviderType
public interface AverageShelfLifeMaster extends AverageShelfLifeMasterModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.AverageShelfLifeMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AverageShelfLifeMaster, Integer> AVERAGE_SHELF_LIFE_MASTER_SID_ACCESSOR =
		new Accessor<AverageShelfLifeMaster, Integer>() {
			@Override
			public Integer get(AverageShelfLifeMaster averageShelfLifeMaster) {
				return averageShelfLifeMaster.getAverageShelfLifeMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<AverageShelfLifeMaster> getTypeClass() {
				return AverageShelfLifeMaster.class;
			}
		};
}