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
 * The extended model interface for the RebateTierFormula service. Represents a row in the &quot;REBATE_TIER_FORMULA&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see RebateTierFormulaModel
 * @see com.stpl.app.model.impl.RebateTierFormulaImpl
 * @see com.stpl.app.model.impl.RebateTierFormulaModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.RebateTierFormulaImpl")
@ProviderType
public interface RebateTierFormula extends RebateTierFormulaModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.RebateTierFormulaImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<RebateTierFormula, Integer> REBATE_TIER_FORMULA_SID_ACCESSOR =
		new Accessor<RebateTierFormula, Integer>() {
			@Override
			public Integer get(RebateTierFormula rebateTierFormula) {
				return rebateTierFormula.getRebateTierFormulaSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<RebateTierFormula> getTypeClass() {
				return RebateTierFormula.class;
			}
		};
}