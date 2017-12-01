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
 * The extended model interface for the HistHierarchyLevelDefn service. Represents a row in the &quot;HIST_HIERARCHY_LEVEL_DEFN&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see HistHierarchyLevelDefnModel
 * @see com.stpl.app.model.impl.HistHierarchyLevelDefnImpl
 * @see com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.HistHierarchyLevelDefnImpl")
@ProviderType
public interface HistHierarchyLevelDefn extends HistHierarchyLevelDefnModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.HistHierarchyLevelDefnImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<HistHierarchyLevelDefn, Integer> HIERARCHY_LEVEL_DEFINITION_SID_ACCESSOR =
		new Accessor<HistHierarchyLevelDefn, Integer>() {
			@Override
			public Integer get(HistHierarchyLevelDefn histHierarchyLevelDefn) {
				return histHierarchyLevelDefn.getHierarchyLevelDefinitionSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<HistHierarchyLevelDefn> getTypeClass() {
				return HistHierarchyLevelDefn.class;
			}
		};

	public static final Accessor<HistHierarchyLevelDefn, Integer> VERSION_NO_ACCESSOR =
		new Accessor<HistHierarchyLevelDefn, Integer>() {
			@Override
			public Integer get(HistHierarchyLevelDefn histHierarchyLevelDefn) {
				return histHierarchyLevelDefn.getVersionNo();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<HistHierarchyLevelDefn> getTypeClass() {
				return HistHierarchyLevelDefn.class;
			}
		};

	public static final Accessor<HistHierarchyLevelDefn, String> ACTION_FLAG_ACCESSOR =
		new Accessor<HistHierarchyLevelDefn, String>() {
			@Override
			public String get(HistHierarchyLevelDefn histHierarchyLevelDefn) {
				return histHierarchyLevelDefn.getActionFlag();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<HistHierarchyLevelDefn> getTypeClass() {
				return HistHierarchyLevelDefn.class;
			}
		};
}