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
 * The extended model interface for the HistCompanyGroupDetails service. Represents a row in the &quot;HIST_COMPANY_GROUP_DETAILS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see HistCompanyGroupDetailsModel
 * @see com.stpl.app.model.impl.HistCompanyGroupDetailsImpl
 * @see com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.HistCompanyGroupDetailsImpl")
@ProviderType
public interface HistCompanyGroupDetails extends HistCompanyGroupDetailsModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.HistCompanyGroupDetailsImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<HistCompanyGroupDetails, String> ACTION_FLAG_ACCESSOR =
		new Accessor<HistCompanyGroupDetails, String>() {
			@Override
			public String get(HistCompanyGroupDetails histCompanyGroupDetails) {
				return histCompanyGroupDetails.getActionFlag();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<HistCompanyGroupDetails> getTypeClass() {
				return HistCompanyGroupDetails.class;
			}
		};

	public static final Accessor<HistCompanyGroupDetails, Integer> VERSION_NO_ACCESSOR =
		new Accessor<HistCompanyGroupDetails, Integer>() {
			@Override
			public Integer get(HistCompanyGroupDetails histCompanyGroupDetails) {
				return histCompanyGroupDetails.getVersionNo();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<HistCompanyGroupDetails> getTypeClass() {
				return HistCompanyGroupDetails.class;
			}
		};

	public static final Accessor<HistCompanyGroupDetails, Integer> COMPANY_GROUP_DETAILS_SID_ACCESSOR =
		new Accessor<HistCompanyGroupDetails, Integer>() {
			@Override
			public Integer get(HistCompanyGroupDetails histCompanyGroupDetails) {
				return histCompanyGroupDetails.getCompanyGroupDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<HistCompanyGroupDetails> getTypeClass() {
				return HistCompanyGroupDetails.class;
			}
		};
}