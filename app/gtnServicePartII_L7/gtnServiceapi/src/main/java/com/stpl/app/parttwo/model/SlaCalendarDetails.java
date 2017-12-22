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
 * The extended model interface for the SlaCalendarDetails service. Represents a row in the &quot;SLA_CALENDAR_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see SlaCalendarDetailsModel
 * @see com.stpl.app.parttwo.model.impl.SlaCalendarDetailsImpl
 * @see com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.SlaCalendarDetailsImpl")
@ProviderType
public interface SlaCalendarDetails extends SlaCalendarDetailsModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SlaCalendarDetails, Integer> SLA_CALENDAR_DETAILS_SID_ACCESSOR =
		new Accessor<SlaCalendarDetails, Integer>() {
			@Override
			public Integer get(SlaCalendarDetails slaCalendarDetails) {
				return slaCalendarDetails.getSlaCalendarDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<SlaCalendarDetails> getTypeClass() {
				return SlaCalendarDetails.class;
			}
		};
}