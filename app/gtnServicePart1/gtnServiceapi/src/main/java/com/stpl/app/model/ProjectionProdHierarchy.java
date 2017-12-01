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
 * The extended model interface for the ProjectionProdHierarchy service. Represents a row in the &quot;PROJECTION_PROD_HIERARCHY&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see ProjectionProdHierarchyModel
 * @see com.stpl.app.model.impl.ProjectionProdHierarchyImpl
 * @see com.stpl.app.model.impl.ProjectionProdHierarchyModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.ProjectionProdHierarchyImpl")
@ProviderType
public interface ProjectionProdHierarchy extends ProjectionProdHierarchyModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.ProjectionProdHierarchyImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ProjectionProdHierarchy, Integer> PROJECTION_PROD_HIERARCHY_SID_ACCESSOR =
		new Accessor<ProjectionProdHierarchy, Integer>() {
			@Override
			public Integer get(ProjectionProdHierarchy projectionProdHierarchy) {
				return projectionProdHierarchy.getProjectionProdHierarchySid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<ProjectionProdHierarchy> getTypeClass() {
				return ProjectionProdHierarchy.class;
			}
		};
}