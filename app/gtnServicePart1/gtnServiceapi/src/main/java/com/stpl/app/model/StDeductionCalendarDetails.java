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
 * The extended model interface for the StDeductionCalendarDetails service. Represents a row in the &quot;ST_DEDUCTION_CALENDAR_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StDeductionCalendarDetailsModel
 * @see com.stpl.app.model.impl.StDeductionCalendarDetailsImpl
 * @see com.stpl.app.model.impl.StDeductionCalendarDetailsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StDeductionCalendarDetailsImpl")
@ProviderType
public interface StDeductionCalendarDetails
	extends StDeductionCalendarDetailsModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StDeductionCalendarDetailsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StDeductionCalendarDetails, Integer> PERIOD_SID_ACCESSOR =
		new Accessor<StDeductionCalendarDetails, Integer>() {
			@Override
			public Integer get(
				StDeductionCalendarDetails stDeductionCalendarDetails) {
				return stDeductionCalendarDetails.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StDeductionCalendarDetails> getTypeClass() {
				return StDeductionCalendarDetails.class;
			}
		};

	public static final Accessor<StDeductionCalendarDetails, Integer> COMPANY_MASTER_SID_ACCESSOR =
		new Accessor<StDeductionCalendarDetails, Integer>() {
			@Override
			public Integer get(
				StDeductionCalendarDetails stDeductionCalendarDetails) {
				return stDeductionCalendarDetails.getCompanyMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StDeductionCalendarDetails> getTypeClass() {
				return StDeductionCalendarDetails.class;
			}
		};

	public static final Accessor<StDeductionCalendarDetails, Integer> USER_ID_ACCESSOR =
		new Accessor<StDeductionCalendarDetails, Integer>() {
			@Override
			public Integer get(
				StDeductionCalendarDetails stDeductionCalendarDetails) {
				return stDeductionCalendarDetails.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StDeductionCalendarDetails> getTypeClass() {
				return StDeductionCalendarDetails.class;
			}
		};

	public static final Accessor<StDeductionCalendarDetails, Integer> ITEM_MASTER_SID_ACCESSOR =
		new Accessor<StDeductionCalendarDetails, Integer>() {
			@Override
			public Integer get(
				StDeductionCalendarDetails stDeductionCalendarDetails) {
				return stDeductionCalendarDetails.getItemMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StDeductionCalendarDetails> getTypeClass() {
				return StDeductionCalendarDetails.class;
			}
		};

	public static final Accessor<StDeductionCalendarDetails, String> SESSION_ID_ACCESSOR =
		new Accessor<StDeductionCalendarDetails, String>() {
			@Override
			public String get(
				StDeductionCalendarDetails stDeductionCalendarDetails) {
				return stDeductionCalendarDetails.getSessionId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StDeductionCalendarDetails> getTypeClass() {
				return StDeductionCalendarDetails.class;
			}
		};
}