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
 * The extended model interface for the MedicaidNewNdc service. Represents a row in the &quot;MEDICAID_NEW_NDC&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see MedicaidNewNdcModel
 * @see com.stpl.app.model.impl.MedicaidNewNdcImpl
 * @see com.stpl.app.model.impl.MedicaidNewNdcModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.MedicaidNewNdcImpl")
@ProviderType
public interface MedicaidNewNdc extends MedicaidNewNdcModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.MedicaidNewNdcImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MedicaidNewNdc, String> NDC9_ACCESSOR = new Accessor<MedicaidNewNdc, String>() {
			@Override
			public String get(MedicaidNewNdc medicaidNewNdc) {
				return medicaidNewNdc.getNdc9();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<MedicaidNewNdc> getTypeClass() {
				return MedicaidNewNdc.class;
			}
		};
}