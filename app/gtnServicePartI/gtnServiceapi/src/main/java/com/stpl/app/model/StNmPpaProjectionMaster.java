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
 * The extended model interface for the StNmPpaProjectionMaster service. Represents a row in the &quot;ST_NM_PPA_PROJECTION_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StNmPpaProjectionMasterModel
 * @see com.stpl.app.model.impl.StNmPpaProjectionMasterImpl
 * @see com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StNmPpaProjectionMasterImpl")
@ProviderType
public interface StNmPpaProjectionMaster extends StNmPpaProjectionMasterModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StNmPpaProjectionMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StNmPpaProjectionMaster, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<StNmPpaProjectionMaster, Integer>() {
			@Override
			public Integer get(StNmPpaProjectionMaster stNmPpaProjectionMaster) {
				return stNmPpaProjectionMaster.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmPpaProjectionMaster> getTypeClass() {
				return StNmPpaProjectionMaster.class;
			}
		};

	public static final Accessor<StNmPpaProjectionMaster, Integer> USER_ID_ACCESSOR =
		new Accessor<StNmPpaProjectionMaster, Integer>() {
			@Override
			public Integer get(StNmPpaProjectionMaster stNmPpaProjectionMaster) {
				return stNmPpaProjectionMaster.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmPpaProjectionMaster> getTypeClass() {
				return StNmPpaProjectionMaster.class;
			}
		};

	public static final Accessor<StNmPpaProjectionMaster, Integer> SESSION_ID_ACCESSOR =
		new Accessor<StNmPpaProjectionMaster, Integer>() {
			@Override
			public Integer get(StNmPpaProjectionMaster stNmPpaProjectionMaster) {
				return stNmPpaProjectionMaster.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNmPpaProjectionMaster> getTypeClass() {
				return StNmPpaProjectionMaster.class;
			}
		};
}