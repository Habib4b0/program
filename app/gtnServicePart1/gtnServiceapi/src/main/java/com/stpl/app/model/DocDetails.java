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
 * The extended model interface for the DocDetails service. Represents a row in the &quot;DOC_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see DocDetailsModel
 * @see com.stpl.app.model.impl.DocDetailsImpl
 * @see com.stpl.app.model.impl.DocDetailsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.DocDetailsImpl")
@ProviderType
public interface DocDetails extends DocDetailsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.DocDetailsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DocDetails, Integer> DOC_DETAILS_ID_ACCESSOR = new Accessor<DocDetails, Integer>() {
			@Override
			public Integer get(DocDetails docDetails) {
				return docDetails.getDocDetailsId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<DocDetails> getTypeClass() {
				return DocDetails.class;
			}
		};
}