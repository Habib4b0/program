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
 * The extended model interface for the ForecastingViewMaster service. Represents a row in the &quot;FORECASTING_VIEW_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see ForecastingViewMasterModel
 * @see com.stpl.app.model.impl.ForecastingViewMasterImpl
 * @see com.stpl.app.model.impl.ForecastingViewMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.ForecastingViewMasterImpl")
@ProviderType
public interface ForecastingViewMaster extends ForecastingViewMasterModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.ForecastingViewMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ForecastingViewMaster, Integer> VIEW_ID_ACCESSOR =
		new Accessor<ForecastingViewMaster, Integer>() {
			@Override
			public Integer get(ForecastingViewMaster forecastingViewMaster) {
				return forecastingViewMaster.getViewId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<ForecastingViewMaster> getTypeClass() {
				return ForecastingViewMaster.class;
			}
		};
}