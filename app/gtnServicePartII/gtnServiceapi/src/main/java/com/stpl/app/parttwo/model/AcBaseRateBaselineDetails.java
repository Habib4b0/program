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
 * The extended model interface for the AcBaseRateBaselineDetails service. Represents a row in the &quot;Ac_Base_Rate_Baseline_Details&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see AcBaseRateBaselineDetailsModel
 * @see com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsImpl
 * @see com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsImpl")
@ProviderType
public interface AcBaseRateBaselineDetails
	extends AcBaseRateBaselineDetailsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AcBaseRateBaselineDetails, Integer> AC_BR_METHODOLOGY_DETAILS_SID_ACCESSOR =
		new Accessor<AcBaseRateBaselineDetails, Integer>() {
			@Override
			public Integer get(
				AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
				return acBaseRateBaselineDetails.getAcBrMethodologyDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<AcBaseRateBaselineDetails> getTypeClass() {
				return AcBaseRateBaselineDetails.class;
			}
		};
}