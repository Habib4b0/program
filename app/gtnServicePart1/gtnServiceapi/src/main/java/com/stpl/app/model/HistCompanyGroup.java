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
 * The extended model interface for the HistCompanyGroup service. Represents a row in the &quot;HIST_COMPANY_GROUP&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see HistCompanyGroupModel
 * @see com.stpl.app.model.impl.HistCompanyGroupImpl
 * @see com.stpl.app.model.impl.HistCompanyGroupModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.HistCompanyGroupImpl")
@ProviderType
public interface HistCompanyGroup extends HistCompanyGroupModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.HistCompanyGroupImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<HistCompanyGroup, String> ACTION_FLAG_ACCESSOR = new Accessor<HistCompanyGroup, String>() {
			@Override
			public String get(HistCompanyGroup histCompanyGroup) {
				return histCompanyGroup.getActionFlag();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<HistCompanyGroup> getTypeClass() {
				return HistCompanyGroup.class;
			}
		};

	public static final Accessor<HistCompanyGroup, Integer> COMPANY_GROUP_SID_ACCESSOR =
		new Accessor<HistCompanyGroup, Integer>() {
			@Override
			public Integer get(HistCompanyGroup histCompanyGroup) {
				return histCompanyGroup.getCompanyGroupSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<HistCompanyGroup> getTypeClass() {
				return HistCompanyGroup.class;
			}
		};

	public static final Accessor<HistCompanyGroup, Integer> VERSION_NO_ACCESSOR = new Accessor<HistCompanyGroup, Integer>() {
			@Override
			public Integer get(HistCompanyGroup histCompanyGroup) {
				return histCompanyGroup.getVersionNo();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<HistCompanyGroup> getTypeClass() {
				return HistCompanyGroup.class;
			}
		};
}