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
 * The extended model interface for the CfpModel service. Represents a row in the &quot;CFP_MODEL&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see CfpModelModel
 * @see com.stpl.app.model.impl.CfpModelImpl
 * @see com.stpl.app.model.impl.CfpModelModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.CfpModelImpl")
@ProviderType
public interface CfpModel extends CfpModelModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.CfpModelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CfpModel, Integer> CFP_MODEL_SID_ACCESSOR = new Accessor<CfpModel, Integer>() {
			@Override
			public Integer get(CfpModel cfpModel) {
				return cfpModel.getCfpModelSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<CfpModel> getTypeClass() {
				return CfpModel.class;
			}
		};
}