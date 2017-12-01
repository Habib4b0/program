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
 * The extended model interface for the CfpContract service. Represents a row in the &quot;CFP_CONTRACT&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see CfpContractModel
 * @see com.stpl.app.model.impl.CfpContractImpl
 * @see com.stpl.app.model.impl.CfpContractModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.CfpContractImpl")
@ProviderType
public interface CfpContract extends CfpContractModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.CfpContractImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CfpContract, Integer> CFP_CONTRACT_SID_ACCESSOR =
		new Accessor<CfpContract, Integer>() {
			@Override
			public Integer get(CfpContract cfpContract) {
				return cfpContract.getCfpContractSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<CfpContract> getTypeClass() {
				return CfpContract.class;
			}
		};
}