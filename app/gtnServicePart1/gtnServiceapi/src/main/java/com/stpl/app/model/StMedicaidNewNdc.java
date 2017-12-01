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
 * The extended model interface for the StMedicaidNewNdc service. Represents a row in the &quot;ST_MEDICAID_NEW_NDC&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StMedicaidNewNdcModel
 * @see com.stpl.app.model.impl.StMedicaidNewNdcImpl
 * @see com.stpl.app.model.impl.StMedicaidNewNdcModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StMedicaidNewNdcImpl")
@ProviderType
public interface StMedicaidNewNdc extends StMedicaidNewNdcModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StMedicaidNewNdcImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StMedicaidNewNdc, String> NDC9_ACCESSOR = new Accessor<StMedicaidNewNdc, String>() {
			@Override
			public String get(StMedicaidNewNdc stMedicaidNewNdc) {
				return stMedicaidNewNdc.getNdc9();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StMedicaidNewNdc> getTypeClass() {
				return StMedicaidNewNdc.class;
			}
		};

	public static final Accessor<StMedicaidNewNdc, Integer> USER_ID_ACCESSOR = new Accessor<StMedicaidNewNdc, Integer>() {
			@Override
			public Integer get(StMedicaidNewNdc stMedicaidNewNdc) {
				return stMedicaidNewNdc.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StMedicaidNewNdc> getTypeClass() {
				return StMedicaidNewNdc.class;
			}
		};

	public static final Accessor<StMedicaidNewNdc, Integer> SESSION_ID_ACCESSOR = new Accessor<StMedicaidNewNdc, Integer>() {
			@Override
			public Integer get(StMedicaidNewNdc stMedicaidNewNdc) {
				return stMedicaidNewNdc.getSessionId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StMedicaidNewNdc> getTypeClass() {
				return StMedicaidNewNdc.class;
			}
		};
}