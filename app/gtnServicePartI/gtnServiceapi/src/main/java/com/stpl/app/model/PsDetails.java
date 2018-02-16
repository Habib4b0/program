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
 * The extended model interface for the PsDetails service. Represents a row in the &quot;PS_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see PsDetailsModel
 * @see com.stpl.app.model.impl.PsDetailsImpl
 * @see com.stpl.app.model.impl.PsDetailsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.PsDetailsImpl")
@ProviderType
public interface PsDetails extends PsDetailsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.PsDetailsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PsDetails, Integer> PS_DETAILS_SID_ACCESSOR = new Accessor<PsDetails, Integer>() {
			@Override
			public Integer get(PsDetails psDetails) {
				return psDetails.getPsDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<PsDetails> getTypeClass() {
				return PsDetails.class;
			}
		};
}