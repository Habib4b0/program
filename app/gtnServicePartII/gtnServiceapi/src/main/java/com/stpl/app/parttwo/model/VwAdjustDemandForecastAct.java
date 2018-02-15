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
 * The extended model interface for the VwAdjustDemandForecastAct service. Represents a row in the &quot;VW_ADJUST_DEMAND_FORECAST_ACT&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see VwAdjustDemandForecastActModel
 * @see com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActImpl
 * @see com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActImpl")
@ProviderType
public interface VwAdjustDemandForecastAct
	extends VwAdjustDemandForecastActModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<VwAdjustDemandForecastAct, Integer> ADJUSTED_DEMAND_FORECAST_ID_ACCESSOR =
		new Accessor<VwAdjustDemandForecastAct, Integer>() {
			@Override
			public Integer get(
				VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
				return vwAdjustDemandForecastAct.getAdjustedDemandForecastId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<VwAdjustDemandForecastAct> getTypeClass() {
				return VwAdjustDemandForecastAct.class;
			}
		};
}