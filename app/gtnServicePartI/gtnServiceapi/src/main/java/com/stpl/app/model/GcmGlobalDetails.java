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
 * The extended model interface for the GcmGlobalDetails service. Represents a row in the &quot;GCM_GLOBAL_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see GcmGlobalDetailsModel
 * @see com.stpl.app.model.impl.GcmGlobalDetailsImpl
 * @see com.stpl.app.model.impl.GcmGlobalDetailsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.GcmGlobalDetailsImpl")
@ProviderType
public interface GcmGlobalDetails extends GcmGlobalDetailsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.GcmGlobalDetailsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<GcmGlobalDetails, Integer> GCM_GLOBAL_DETAILS_SID_ACCESSOR =
		new Accessor<GcmGlobalDetails, Integer>() {
			@Override
			public Integer get(GcmGlobalDetails gcmGlobalDetails) {
				return gcmGlobalDetails.getGcmGlobalDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<GcmGlobalDetails> getTypeClass() {
				return GcmGlobalDetails.class;
			}
		};
}