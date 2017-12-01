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
 * The extended model interface for the IvldForecastSales service. Represents a row in the &quot;IVLD_FORECAST_SALES&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see IvldForecastSalesModel
 * @see com.stpl.app.model.impl.IvldForecastSalesImpl
 * @see com.stpl.app.model.impl.IvldForecastSalesModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.IvldForecastSalesImpl")
@ProviderType
public interface IvldForecastSales extends IvldForecastSalesModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.IvldForecastSalesImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<IvldForecastSales, Integer> IVLD_FORECAST_SALES_SID_ACCESSOR =
		new Accessor<IvldForecastSales, Integer>() {
			@Override
			public Integer get(IvldForecastSales ivldForecastSales) {
				return ivldForecastSales.getIvldForecastSalesSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<IvldForecastSales> getTypeClass() {
				return IvldForecastSales.class;
			}
		};
}