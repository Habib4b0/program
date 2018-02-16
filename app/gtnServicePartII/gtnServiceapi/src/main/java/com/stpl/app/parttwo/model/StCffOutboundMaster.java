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
 * The extended model interface for the StCffOutboundMaster service. Represents a row in the &quot;ST_CFF_OUTBOUND_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StCffOutboundMasterModel
 * @see com.stpl.app.parttwo.model.impl.StCffOutboundMasterImpl
 * @see com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.StCffOutboundMasterImpl")
@ProviderType
public interface StCffOutboundMaster extends StCffOutboundMasterModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.StCffOutboundMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StCffOutboundMaster, Integer> CFF_DETAILS_SID_ACCESSOR =
		new Accessor<StCffOutboundMaster, Integer>() {
			@Override
			public Integer get(StCffOutboundMaster stCffOutboundMaster) {
				return stCffOutboundMaster.getCffDetailsSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StCffOutboundMaster> getTypeClass() {
				return StCffOutboundMaster.class;
			}
		};

	public static final Accessor<StCffOutboundMaster, String> SESSION_ID_ACCESSOR =
		new Accessor<StCffOutboundMaster, String>() {
			@Override
			public String get(StCffOutboundMaster stCffOutboundMaster) {
				return stCffOutboundMaster.getSessionId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StCffOutboundMaster> getTypeClass() {
				return StCffOutboundMaster.class;
			}
		};

	public static final Accessor<StCffOutboundMaster, Integer> RS_MODEL_SID_ACCESSOR =
		new Accessor<StCffOutboundMaster, Integer>() {
			@Override
			public Integer get(StCffOutboundMaster stCffOutboundMaster) {
				return stCffOutboundMaster.getRsModelSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StCffOutboundMaster> getTypeClass() {
				return StCffOutboundMaster.class;
			}
		};

	public static final Accessor<StCffOutboundMaster, String> USER_ID_ACCESSOR = new Accessor<StCffOutboundMaster, String>() {
			@Override
			public String get(StCffOutboundMaster stCffOutboundMaster) {
				return stCffOutboundMaster.getUserId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StCffOutboundMaster> getTypeClass() {
				return StCffOutboundMaster.class;
			}
		};

	public static final Accessor<StCffOutboundMaster, Integer> PERIOD_SID_ACCESSOR =
		new Accessor<StCffOutboundMaster, Integer>() {
			@Override
			public Integer get(StCffOutboundMaster stCffOutboundMaster) {
				return stCffOutboundMaster.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StCffOutboundMaster> getTypeClass() {
				return StCffOutboundMaster.class;
			}
		};
}