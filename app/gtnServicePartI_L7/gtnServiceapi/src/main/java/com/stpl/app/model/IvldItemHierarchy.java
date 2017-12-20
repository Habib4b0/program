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
 * The extended model interface for the IvldItemHierarchy service. Represents a row in the &quot;IVLD_ITEM_HIERARCHY&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see IvldItemHierarchyModel
 * @see com.stpl.app.model.impl.IvldItemHierarchyImpl
 * @see com.stpl.app.model.impl.IvldItemHierarchyModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.IvldItemHierarchyImpl")
@ProviderType
public interface IvldItemHierarchy extends IvldItemHierarchyModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.IvldItemHierarchyImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<IvldItemHierarchy, Integer> IVLD_ITEM_HIERARCHY_SID_ACCESSOR =
		new Accessor<IvldItemHierarchy, Integer>() {
			@Override
			public Integer get(IvldItemHierarchy ivldItemHierarchy) {
				return ivldItemHierarchy.getIvldItemHierarchySid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<IvldItemHierarchy> getTypeClass() {
				return IvldItemHierarchy.class;
			}
		};
}