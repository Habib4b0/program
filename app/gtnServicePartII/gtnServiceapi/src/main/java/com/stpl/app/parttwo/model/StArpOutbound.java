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
 * The extended model interface for the StArpOutbound service. Represents a row in the &quot;ST_ARP_OUTBOUND&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see StArpOutboundModel
 * @see com.stpl.app.parttwo.model.impl.StArpOutboundImpl
 * @see com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.StArpOutboundImpl")
@ProviderType
public interface StArpOutbound extends StArpOutboundModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.StArpOutboundImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StArpOutbound, Integer> COMPANY_MASTER_SID_ACCESSOR =
		new Accessor<StArpOutbound, Integer>() {
			@Override
			public Integer get(StArpOutbound stArpOutbound) {
				return stArpOutbound.getCompanyMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StArpOutbound> getTypeClass() {
				return StArpOutbound.class;
			}
		};

	public static final Accessor<StArpOutbound, Integer> USER_ID_ACCESSOR = new Accessor<StArpOutbound, Integer>() {
			@Override
			public Integer get(StArpOutbound stArpOutbound) {
				return stArpOutbound.getUserId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StArpOutbound> getTypeClass() {
				return StArpOutbound.class;
			}
		};

	public static final Accessor<StArpOutbound, Integer> ARP_MASTER_SID_ACCESSOR =
		new Accessor<StArpOutbound, Integer>() {
			@Override
			public Integer get(StArpOutbound stArpOutbound) {
				return stArpOutbound.getArpMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StArpOutbound> getTypeClass() {
				return StArpOutbound.class;
			}
		};

	public static final Accessor<StArpOutbound, Integer> ARP_ID_ACCESSOR = new Accessor<StArpOutbound, Integer>() {
			@Override
			public Integer get(StArpOutbound stArpOutbound) {
				return stArpOutbound.getArpId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StArpOutbound> getTypeClass() {
				return StArpOutbound.class;
			}
		};

	public static final Accessor<StArpOutbound, Integer> PERIOD_SID_ACCESSOR = new Accessor<StArpOutbound, Integer>() {
			@Override
			public Integer get(StArpOutbound stArpOutbound) {
				return stArpOutbound.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StArpOutbound> getTypeClass() {
				return StArpOutbound.class;
			}
		};

	public static final Accessor<StArpOutbound, Integer> ITEM_MASTER_SID_ACCESSOR =
		new Accessor<StArpOutbound, Integer>() {
			@Override
			public Integer get(StArpOutbound stArpOutbound) {
				return stArpOutbound.getItemMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StArpOutbound> getTypeClass() {
				return StArpOutbound.class;
			}
		};

	public static final Accessor<StArpOutbound, Integer> RS_MODEL_SID_ACCESSOR = new Accessor<StArpOutbound, Integer>() {
			@Override
			public Integer get(StArpOutbound stArpOutbound) {
				return stArpOutbound.getRsModelSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<StArpOutbound> getTypeClass() {
				return StArpOutbound.class;
			}
		};

	public static final Accessor<StArpOutbound, String> SESSION_ID_ACCESSOR = new Accessor<StArpOutbound, String>() {
			@Override
			public String get(StArpOutbound stArpOutbound) {
				return stArpOutbound.getSessionId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<StArpOutbound> getTypeClass() {
				return StArpOutbound.class;
			}
		};
}