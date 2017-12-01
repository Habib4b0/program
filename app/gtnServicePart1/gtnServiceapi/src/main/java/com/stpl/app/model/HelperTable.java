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
 * The extended model interface for the HelperTable service. Represents a row in the &quot;HELPER_TABLE&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see HelperTableModel
 * @see com.stpl.app.model.impl.HelperTableImpl
 * @see com.stpl.app.model.impl.HelperTableModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.HelperTableImpl")
@ProviderType
public interface HelperTable extends HelperTableModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.HelperTableImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<HelperTable, Integer> HELPER_TABLE_SID_ACCESSOR =
		new Accessor<HelperTable, Integer>() {
			@Override
			public Integer get(HelperTable helperTable) {
				return helperTable.getHelperTableSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<HelperTable> getTypeClass() {
				return HelperTable.class;
			}
		};
}