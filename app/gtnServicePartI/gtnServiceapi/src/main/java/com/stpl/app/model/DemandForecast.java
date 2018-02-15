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
 * The extended model interface for the DemandForecast service. Represents a row in the &quot;DEMAND_FORECAST&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see DemandForecastModel
 * @see com.stpl.app.model.impl.DemandForecastImpl
 * @see com.stpl.app.model.impl.DemandForecastModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.DemandForecastImpl")
@ProviderType
public interface DemandForecast extends DemandForecastModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.DemandForecastImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DemandForecast, Integer> DEMAND_FORECAST_SID_ACCESSOR =
		new Accessor<DemandForecast, Integer>() {
			@Override
			public Integer get(DemandForecast demandForecast) {
				return demandForecast.getDemandForecastSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<DemandForecast> getTypeClass() {
				return DemandForecast.class;
			}
		};
}