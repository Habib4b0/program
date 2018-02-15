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
 * The extended model interface for the DeductionGroup service. Represents a row in the &quot;DEDUCTION_GROUP&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see DeductionGroupModel
 * @see com.stpl.app.model.impl.DeductionGroupImpl
 * @see com.stpl.app.model.impl.DeductionGroupModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.DeductionGroupImpl")
@ProviderType
public interface DeductionGroup extends DeductionGroupModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.DeductionGroupImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DeductionGroup, Integer> DEDUCTION_GROUP_SID_ACCESSOR =
		new Accessor<DeductionGroup, Integer>() {
			@Override
			public Integer get(DeductionGroup deductionGroup) {
				return deductionGroup.getDeductionGroupSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<DeductionGroup> getTypeClass() {
				return DeductionGroup.class;
			}
		};
}