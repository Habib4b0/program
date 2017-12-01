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
 * The extended model interface for the GlCostCenterMaster service. Represents a row in the &quot;GL_COST_CENTER_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see GlCostCenterMasterModel
 * @see com.stpl.app.model.impl.GlCostCenterMasterImpl
 * @see com.stpl.app.model.impl.GlCostCenterMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.GlCostCenterMasterImpl")
@ProviderType
public interface GlCostCenterMaster extends GlCostCenterMasterModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.GlCostCenterMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<GlCostCenterMaster, Integer> GL_COST_CENTER_MASTER_SID_ACCESSOR =
		new Accessor<GlCostCenterMaster, Integer>() {
			@Override
			public Integer get(GlCostCenterMaster glCostCenterMaster) {
				return glCostCenterMaster.getGlCostCenterMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<GlCostCenterMaster> getTypeClass() {
				return GlCostCenterMaster.class;
			}
		};
}