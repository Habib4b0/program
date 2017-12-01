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
 * The extended model interface for the HistHierarchyDefinition service. Represents a row in the &quot;HIST_HIERARCHY_DEFINITION&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see HistHierarchyDefinitionModel
 * @see com.stpl.app.model.impl.HistHierarchyDefinitionImpl
 * @see com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.HistHierarchyDefinitionImpl")
@ProviderType
public interface HistHierarchyDefinition extends HistHierarchyDefinitionModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.HistHierarchyDefinitionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<HistHierarchyDefinition, String> ACTION_FLAG_ACCESSOR =
		new Accessor<HistHierarchyDefinition, String>() {
			@Override
			public String get(HistHierarchyDefinition histHierarchyDefinition) {
				return histHierarchyDefinition.getActionFlag();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<HistHierarchyDefinition> getTypeClass() {
				return HistHierarchyDefinition.class;
			}
		};

	public static final Accessor<HistHierarchyDefinition, Integer> HIERARCHY_DEFINITION_SID_ACCESSOR =
		new Accessor<HistHierarchyDefinition, Integer>() {
			@Override
			public Integer get(HistHierarchyDefinition histHierarchyDefinition) {
				return histHierarchyDefinition.getHierarchyDefinitionSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<HistHierarchyDefinition> getTypeClass() {
				return HistHierarchyDefinition.class;
			}
		};

	public static final Accessor<HistHierarchyDefinition, Integer> VERSION_NO_ACCESSOR =
		new Accessor<HistHierarchyDefinition, Integer>() {
			@Override
			public Integer get(HistHierarchyDefinition histHierarchyDefinition) {
				return histHierarchyDefinition.getVersionNo();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<HistHierarchyDefinition> getTypeClass() {
				return HistHierarchyDefinition.class;
			}
		};
}