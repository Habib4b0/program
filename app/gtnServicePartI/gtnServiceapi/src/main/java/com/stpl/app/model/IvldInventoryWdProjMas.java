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
 * The extended model interface for the IvldInventoryWdProjMas service. Represents a row in the &quot;IVLD_INVENTORY_WD_PROJ_MAS&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see IvldInventoryWdProjMasModel
 * @see com.stpl.app.model.impl.IvldInventoryWdProjMasImpl
 * @see com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.IvldInventoryWdProjMasImpl")
@ProviderType
public interface IvldInventoryWdProjMas extends IvldInventoryWdProjMasModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.IvldInventoryWdProjMasImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<IvldInventoryWdProjMas, Integer> IVLD_INVENTORY_WD_PROJ_MAS_SID_ACCESSOR =
		new Accessor<IvldInventoryWdProjMas, Integer>() {
			@Override
			public Integer get(IvldInventoryWdProjMas ivldInventoryWdProjMas) {
				return ivldInventoryWdProjMas.getIvldInventoryWdProjMasSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<IvldInventoryWdProjMas> getTypeClass() {
				return IvldInventoryWdProjMas.class;
			}
		};
}