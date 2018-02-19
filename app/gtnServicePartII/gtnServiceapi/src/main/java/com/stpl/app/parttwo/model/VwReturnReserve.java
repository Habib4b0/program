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
 * The extended model interface for the VwReturnReserve service. Represents a row in the &quot;VW_RETURN_RESERVE&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see VwReturnReserveModel
 * @see com.stpl.app.parttwo.model.impl.VwReturnReserveImpl
 * @see com.stpl.app.parttwo.model.impl.VwReturnReserveModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.VwReturnReserveImpl")
@ProviderType
public interface VwReturnReserve extends VwReturnReserveModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.VwReturnReserveImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<VwReturnReserve, Integer> RETURN_RESERVE_SID_ACCESSOR =
		new Accessor<VwReturnReserve, Integer>() {
			@Override
			public Integer get(VwReturnReserve vwReturnReserve) {
				return vwReturnReserve.getReturnReserveSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<VwReturnReserve> getTypeClass() {
				return VwReturnReserve.class;
			}
		};
}