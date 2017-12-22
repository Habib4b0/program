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

package com.stpl.app.parttwo.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CffDetails service. Represents a row in the &quot;CFF_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see CffDetailsModel
 * @see com.stpl.app.parttwo.model.impl.CffDetailsImpl
 * @see com.stpl.app.parttwo.model.impl.CffDetailsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.CffDetailsImpl")
@ProviderType
public interface CffDetails extends CffDetailsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.CffDetailsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CffDetails, Integer> CFF_DETAILS_SID_ACCESSOR = new Accessor<CffDetails, Integer>() {
			@Override
			public Integer get(CffDetails cffDetails) {
				return cffDetails.getCffDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<CffDetails> getTypeClass() {
				return CffDetails.class;
			}
		};
}