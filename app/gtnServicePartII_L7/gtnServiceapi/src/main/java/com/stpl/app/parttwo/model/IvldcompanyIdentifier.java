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
 * The extended model interface for the IvldcompanyIdentifier service. Represents a row in the &quot;IVLD_COMPANY_IDENTIFIER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see IvldcompanyIdentifierModel
 * @see com.stpl.app.parttwo.model.impl.IvldcompanyIdentifierImpl
 * @see com.stpl.app.parttwo.model.impl.IvldcompanyIdentifierModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.IvldcompanyIdentifierImpl")
@ProviderType
public interface IvldcompanyIdentifier extends IvldcompanyIdentifierModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.IvldcompanyIdentifierImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<IvldcompanyIdentifier, Integer> IVLDCOMPANY_IDENTIFIER_SID_ACCESSOR =
		new Accessor<IvldcompanyIdentifier, Integer>() {
			@Override
			public Integer get(IvldcompanyIdentifier ivldcompanyIdentifier) {
				return ivldcompanyIdentifier.getIvldcompanyIdentifierSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<IvldcompanyIdentifier> getTypeClass() {
				return IvldcompanyIdentifier.class;
			}
		};
}