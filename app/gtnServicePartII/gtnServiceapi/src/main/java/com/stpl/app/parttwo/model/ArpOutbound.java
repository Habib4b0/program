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
 * The extended model interface for the ArpOutbound service. Represents a row in the &quot;ARP_OUTBOUND&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see ArpOutboundModel
 * @see com.stpl.app.parttwo.model.impl.ArpOutboundImpl
 * @see com.stpl.app.parttwo.model.impl.ArpOutboundModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.parttwo.model.impl.ArpOutboundImpl")
@ProviderType
public interface ArpOutbound extends ArpOutboundModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.parttwo.model.impl.ArpOutboundImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ArpOutbound, Integer> COMPANY_MASTER_SID_ACCESSOR =
		new Accessor<ArpOutbound, Integer>() {
			@Override
			public Integer get(ArpOutbound arpOutbound) {
				return arpOutbound.getCompanyMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<ArpOutbound> getTypeClass() {
				return ArpOutbound.class;
			}
		};

	public static final Accessor<ArpOutbound, Integer> ARP_MASTER_SID_ACCESSOR = new Accessor<ArpOutbound, Integer>() {
			@Override
			public Integer get(ArpOutbound arpOutbound) {
				return arpOutbound.getArpMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<ArpOutbound> getTypeClass() {
				return ArpOutbound.class;
			}
		};

	public static final Accessor<ArpOutbound, Integer> ARP_ID_ACCESSOR = new Accessor<ArpOutbound, Integer>() {
			@Override
			public Integer get(ArpOutbound arpOutbound) {
				return arpOutbound.getArpId();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<ArpOutbound> getTypeClass() {
				return ArpOutbound.class;
			}
		};

	public static final Accessor<ArpOutbound, Integer> PERIOD_SID_ACCESSOR = new Accessor<ArpOutbound, Integer>() {
			@Override
			public Integer get(ArpOutbound arpOutbound) {
				return arpOutbound.getPeriodSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<ArpOutbound> getTypeClass() {
				return ArpOutbound.class;
			}
		};

	public static final Accessor<ArpOutbound, Integer> ITEM_MASTER_SID_ACCESSOR = new Accessor<ArpOutbound, Integer>() {
			@Override
			public Integer get(ArpOutbound arpOutbound) {
				return arpOutbound.getItemMasterSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<ArpOutbound> getTypeClass() {
				return ArpOutbound.class;
			}
		};

	public static final Accessor<ArpOutbound, Integer> RS_MODEL_SID_ACCESSOR = new Accessor<ArpOutbound, Integer>() {
			@Override
			public Integer get(ArpOutbound arpOutbound) {
				return arpOutbound.getRsModelSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<ArpOutbound> getTypeClass() {
				return ArpOutbound.class;
			}
		};
}