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
 * The extended model interface for the IvldCustomerGtsForecast service. Represents a row in the &quot;IVLD_CUSTOMER_GTS_FORECAST&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see IvldCustomerGtsForecastModel
 * @see com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastImpl
 * @see com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastImpl")
@ProviderType
public interface IvldCustomerGtsForecast extends IvldCustomerGtsForecastModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<IvldCustomerGtsForecast, Integer> IVLD_CUSTOMER_GTS_FORECAST_SID_ACCESSOR =
		new Accessor<IvldCustomerGtsForecast, Integer>() {
			@Override
			public Integer get(IvldCustomerGtsForecast ivldCustomerGtsForecast) {
				return ivldCustomerGtsForecast.getIvldCustomerGtsForecastSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<IvldCustomerGtsForecast> getTypeClass() {
				return IvldCustomerGtsForecast.class;
			}
		};
}