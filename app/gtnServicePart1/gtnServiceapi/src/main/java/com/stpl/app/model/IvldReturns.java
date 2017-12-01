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
 * The extended model interface for the IvldReturns service. Represents a row in the &quot;IVLD_RETURNS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see IvldReturnsModel
 * @see com.stpl.app.model.impl.IvldReturnsImpl
 * @see com.stpl.app.model.impl.IvldReturnsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.IvldReturnsImpl")
@ProviderType
public interface IvldReturns extends IvldReturnsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.IvldReturnsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<IvldReturns, Integer> IVLD_RETURNS_SID_ACCESSOR =
		new Accessor<IvldReturns, Integer>() {
			@Override
			public Integer get(IvldReturns ivldReturns) {
				return ivldReturns.getIvldReturnsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<IvldReturns> getTypeClass() {
				return IvldReturns.class;
			}
		};
}