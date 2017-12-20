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
 * The extended model interface for the StSelectionTable service. Represents a row in the &quot;ST_SELECTION_TABLE&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StSelectionTableModel
 * @see com.stpl.app.model.impl.StSelectionTableImpl
 * @see com.stpl.app.model.impl.StSelectionTableModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.StSelectionTableImpl")
@ProviderType
public interface StSelectionTable extends StSelectionTableModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.StSelectionTableImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StSelectionTable, String> SELECTION_TYPE_ACCESSOR =
		new Accessor<StSelectionTable, String>() {
			@Override
			public String get(StSelectionTable stSelectionTable) {
				return stSelectionTable.getSelectionType();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StSelectionTable> getTypeClass() {
				return StSelectionTable.class;
			}
		};

	public static final Accessor<StSelectionTable, Integer> USER_ID_ACCESSOR = new Accessor<StSelectionTable, Integer>() {
			@Override
			public Integer get(StSelectionTable stSelectionTable) {
				return stSelectionTable.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StSelectionTable> getTypeClass() {
				return StSelectionTable.class;
			}
		};

	public static final Accessor<StSelectionTable, String> SESSION_ID_ACCESSOR = new Accessor<StSelectionTable, String>() {
			@Override
			public String get(StSelectionTable stSelectionTable) {
				return stSelectionTable.getSessionId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StSelectionTable> getTypeClass() {
				return StSelectionTable.class;
			}
		};

	public static final Accessor<StSelectionTable, Integer> COMPANY_ITEM_SID_ACCESSOR =
		new Accessor<StSelectionTable, Integer>() {
			@Override
			public Integer get(StSelectionTable stSelectionTable) {
				return stSelectionTable.getCompanyItemSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StSelectionTable> getTypeClass() {
				return StSelectionTable.class;
			}
		};
}