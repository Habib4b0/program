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
 * The extended model interface for the StFederalNewNdc service. Represents a row in the &quot;ST_FEDERAL_NEW_NDC&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StFederalNewNdcModel
 * @see com.stpl.app.model.impl.StFederalNewNdcImpl
 * @see com.stpl.app.model.impl.StFederalNewNdcModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StFederalNewNdcImpl")
@ProviderType
public interface StFederalNewNdc extends StFederalNewNdcModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StFederalNewNdcImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StFederalNewNdc, Integer> USER_ID_ACCESSOR = new Accessor<StFederalNewNdc, Integer>() {
			@Override
			public Integer get(StFederalNewNdc stFederalNewNdc) {
				return stFederalNewNdc.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StFederalNewNdc> getTypeClass() {
				return StFederalNewNdc.class;
			}
		};

	public static final Accessor<StFederalNewNdc, Integer> ITEM_MASTER_SID_ACCESSOR =
		new Accessor<StFederalNewNdc, Integer>() {
			@Override
			public Integer get(StFederalNewNdc stFederalNewNdc) {
				return stFederalNewNdc.getItemMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StFederalNewNdc> getTypeClass() {
				return StFederalNewNdc.class;
			}
		};

	public static final Accessor<StFederalNewNdc, Integer> SESSION_ID_ACCESSOR = new Accessor<StFederalNewNdc, Integer>() {
			@Override
			public Integer get(StFederalNewNdc stFederalNewNdc) {
				return stFederalNewNdc.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StFederalNewNdc> getTypeClass() {
				return StFederalNewNdc.class;
			}
		};
}