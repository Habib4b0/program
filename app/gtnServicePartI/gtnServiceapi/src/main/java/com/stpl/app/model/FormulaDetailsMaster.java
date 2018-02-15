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
 * The extended model interface for the FormulaDetailsMaster service. Represents a row in the &quot;FORMULA_DETAILS_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see FormulaDetailsMasterModel
 * @see com.stpl.app.model.impl.FormulaDetailsMasterImpl
 * @see com.stpl.app.model.impl.FormulaDetailsMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.FormulaDetailsMasterImpl")
@ProviderType
public interface FormulaDetailsMaster extends FormulaDetailsMasterModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.FormulaDetailsMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<FormulaDetailsMaster, Integer> FORMULA_DETAILS_MASTER_SID_ACCESSOR =
		new Accessor<FormulaDetailsMaster, Integer>() {
			@Override
			public Integer get(FormulaDetailsMaster formulaDetailsMaster) {
				return formulaDetailsMaster.getFormulaDetailsMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<FormulaDetailsMaster> getTypeClass() {
				return FormulaDetailsMaster.class;
			}
		};
}