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
 * The extended model interface for the BusinessroleModule service. Represents a row in the &quot;BUSINESSROLE_MODULE&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see BusinessroleModuleModel
 * @see com.stpl.app.model.impl.BusinessroleModuleImpl
 * @see com.stpl.app.model.impl.BusinessroleModuleModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.BusinessroleModuleImpl")
@ProviderType
public interface BusinessroleModule extends BusinessroleModuleModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.BusinessroleModuleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<BusinessroleModule, Integer> BUSINESSROLE_MODULE_SID_ACCESSOR =
		new Accessor<BusinessroleModule, Integer>() {
			@Override
			public Integer get(BusinessroleModule businessroleModule) {
				return businessroleModule.getBusinessroleModuleSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<BusinessroleModule> getTypeClass() {
				return BusinessroleModule.class;
			}
		};
}