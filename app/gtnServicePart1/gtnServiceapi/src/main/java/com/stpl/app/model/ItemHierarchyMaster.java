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
 * The extended model interface for the ItemHierarchyMaster service. Represents a row in the &quot;ITEM_HIERARCHY_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see ItemHierarchyMasterModel
 * @see com.stpl.app.model.impl.ItemHierarchyMasterImpl
 * @see com.stpl.app.model.impl.ItemHierarchyMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.ItemHierarchyMasterImpl")
@ProviderType
public interface ItemHierarchyMaster extends ItemHierarchyMasterModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.ItemHierarchyMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ItemHierarchyMaster, Integer> ITEM_HIERARCHY_MASTER_SID_ACCESSOR =
		new Accessor<ItemHierarchyMaster, Integer>() {
			@Override
			public Integer get(ItemHierarchyMaster itemHierarchyMaster) {
				return itemHierarchyMaster.getItemHierarchyMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<ItemHierarchyMaster> getTypeClass() {
				return ItemHierarchyMaster.class;
			}
		};
}