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
 * The extended model interface for the HistRelationshipBuilder service. Represents a row in the &quot;HIST_RELATIONSHIP_BUILDER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see HistRelationshipBuilderModel
 * @see com.stpl.app.model.impl.HistRelationshipBuilderImpl
 * @see com.stpl.app.model.impl.HistRelationshipBuilderModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.HistRelationshipBuilderImpl")
@ProviderType
public interface HistRelationshipBuilder extends HistRelationshipBuilderModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.HistRelationshipBuilderImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<HistRelationshipBuilder, String> ACTION_FLAG_ACCESSOR =
		new Accessor<HistRelationshipBuilder, String>() {
			@Override
			public String get(HistRelationshipBuilder histRelationshipBuilder) {
				return histRelationshipBuilder.getActionFlag();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<HistRelationshipBuilder> getTypeClass() {
				return HistRelationshipBuilder.class;
			}
		};

	public static final Accessor<HistRelationshipBuilder, Integer> VERSION_NO_ACCESSOR =
		new Accessor<HistRelationshipBuilder, Integer>() {
			@Override
			public Integer get(HistRelationshipBuilder histRelationshipBuilder) {
				return histRelationshipBuilder.getVersionNo();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<HistRelationshipBuilder> getTypeClass() {
				return HistRelationshipBuilder.class;
			}
		};

	public static final Accessor<HistRelationshipBuilder, Integer> RELATIONSHIP_BUILDER_SID_ACCESSOR =
		new Accessor<HistRelationshipBuilder, Integer>() {
			@Override
			public Integer get(HistRelationshipBuilder histRelationshipBuilder) {
				return histRelationshipBuilder.getRelationshipBuilderSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<HistRelationshipBuilder> getTypeClass() {
				return HistRelationshipBuilder.class;
			}
		};
}