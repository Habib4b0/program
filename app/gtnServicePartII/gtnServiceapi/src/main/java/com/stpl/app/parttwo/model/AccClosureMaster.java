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

package com.stpl.app.parttwo.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AccClosureMaster service. Represents a row in the &quot;Acc_Closure_Master&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see AccClosureMasterModel
 * @see com.stpl.app.parttwo.model.impl.AccClosureMasterImpl
 * @see com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.AccClosureMasterImpl")
@ProviderType
public interface AccClosureMaster extends AccClosureMasterModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.AccClosureMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AccClosureMaster, Integer> ACC_CLOSURE_MASTER_SID_ACCESSOR =
		new Accessor<AccClosureMaster, Integer>() {
			@Override
			public Integer get(AccClosureMaster accClosureMaster) {
				return accClosureMaster.getAccClosureMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<AccClosureMaster> getTypeClass() {
				return AccClosureMaster.class;
			}
		};
}