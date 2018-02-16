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
 * The extended model interface for the StChDiscountProjMaster service. Represents a row in the &quot;ST_CH_DISCOUNT_PROJ_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StChDiscountProjMasterModel
 * @see com.stpl.app.model.impl.StChDiscountProjMasterImpl
 * @see com.stpl.app.model.impl.StChDiscountProjMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StChDiscountProjMasterImpl")
@ProviderType
public interface StChDiscountProjMaster extends StChDiscountProjMasterModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StChDiscountProjMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StChDiscountProjMaster, Integer> PROJECTION_DETAILS_SID_ACCESSOR =
		new Accessor<StChDiscountProjMaster, Integer>() {
			@Override
			public Integer get(StChDiscountProjMaster stChDiscountProjMaster) {
				return stChDiscountProjMaster.getProjectionDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChDiscountProjMaster> getTypeClass() {
				return StChDiscountProjMaster.class;
			}
		};

	public static final Accessor<StChDiscountProjMaster, Integer> USER_ID_ACCESSOR =
		new Accessor<StChDiscountProjMaster, Integer>() {
			@Override
			public Integer get(StChDiscountProjMaster stChDiscountProjMaster) {
				return stChDiscountProjMaster.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChDiscountProjMaster> getTypeClass() {
				return StChDiscountProjMaster.class;
			}
		};

	public static final Accessor<StChDiscountProjMaster, Integer> SESSION_ID_ACCESSOR =
		new Accessor<StChDiscountProjMaster, Integer>() {
			@Override
			public Integer get(StChDiscountProjMaster stChDiscountProjMaster) {
				return stChDiscountProjMaster.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChDiscountProjMaster> getTypeClass() {
				return StChDiscountProjMaster.class;
			}
		};

	public static final Accessor<StChDiscountProjMaster, Integer> RS_MODEL_SID_ACCESSOR =
		new Accessor<StChDiscountProjMaster, Integer>() {
			@Override
			public Integer get(StChDiscountProjMaster stChDiscountProjMaster) {
				return stChDiscountProjMaster.getRsModelSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StChDiscountProjMaster> getTypeClass() {
				return StChDiscountProjMaster.class;
			}
		};
}