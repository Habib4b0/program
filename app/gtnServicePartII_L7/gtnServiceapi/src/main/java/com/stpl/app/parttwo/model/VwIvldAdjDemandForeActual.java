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
 * The extended model interface for the VwIvldAdjDemandForeActual service. Represents a row in the &quot;VW_IVLD_ADJ_DEMAND_FORE_ACTUAL&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see VwIvldAdjDemandForeActualModel
 * @see com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualImpl
 * @see com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualImpl")
@ProviderType
public interface VwIvldAdjDemandForeActual
	extends VwIvldAdjDemandForeActualModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<VwIvldAdjDemandForeActual, Integer> IVLD_ADJUSTED_DEMAND_FORECAST_SID_ACCESSOR =
		new Accessor<VwIvldAdjDemandForeActual, Integer>() {
			@Override
			public Integer get(
				VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
				return vwIvldAdjDemandForeActual.getIvldAdjustedDemandForecastSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<VwIvldAdjDemandForeActual> getTypeClass() {
				return VwIvldAdjDemandForeActual.class;
			}
		};
}