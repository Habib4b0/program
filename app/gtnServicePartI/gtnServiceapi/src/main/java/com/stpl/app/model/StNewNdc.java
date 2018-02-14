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
 * The extended model interface for the StNewNdc service. Represents a row in the &quot;ST_NEW_NDC&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StNewNdcModel
 * @see com.stpl.app.model.impl.StNewNdcImpl
 * @see com.stpl.app.model.impl.StNewNdcModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StNewNdcImpl")
@ProviderType
public interface StNewNdc extends StNewNdcModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StNewNdcImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StNewNdc, Integer> NA_PROJ_DETAILS_SID_ACCESSOR =
		new Accessor<StNewNdc, Integer>() {
			@Override
			public Integer get(StNewNdc stNewNdc) {
				return stNewNdc.getNaProjDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNewNdc> getTypeClass() {
				return StNewNdc.class;
			}
		};

	public static final Accessor<StNewNdc, Integer> USER_ID_ACCESSOR = new Accessor<StNewNdc, Integer>() {
			@Override
			public Integer get(StNewNdc stNewNdc) {
				return stNewNdc.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNewNdc> getTypeClass() {
				return StNewNdc.class;
			}
		};

	public static final Accessor<StNewNdc, Integer> ITEM_MASTER_SID_ACCESSOR = new Accessor<StNewNdc, Integer>() {
			@Override
			public Integer get(StNewNdc stNewNdc) {
				return stNewNdc.getItemMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNewNdc> getTypeClass() {
				return StNewNdc.class;
			}
		};

	public static final Accessor<StNewNdc, Integer> SESSION_ID_ACCESSOR = new Accessor<StNewNdc, Integer>() {
			@Override
			public Integer get(StNewNdc stNewNdc) {
				return stNewNdc.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StNewNdc> getTypeClass() {
				return StNewNdc.class;
			}
		};
}