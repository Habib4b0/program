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
 * The extended model interface for the MailNotificationMaster service. Represents a row in the &quot;MAIL_NOTIFICATION_MASTER&quot; database table, with each column mapped to a property of this class.
 *
 * @author
 * @see MailNotificationMasterModel
 * @see com.stpl.app.model.impl.MailNotificationMasterImpl
 * @see com.stpl.app.model.impl.MailNotificationMasterModelImpl
 * @generated
 */
@ImplementationClassName("com.stpl.app.model.impl.MailNotificationMasterImpl")
@ProviderType
public interface MailNotificationMaster extends MailNotificationMasterModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.stpl.app.model.impl.MailNotificationMasterImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MailNotificationMaster, Integer> MAIL_NOTIFICATION_SID_ACCESSOR =
		new Accessor<MailNotificationMaster, Integer>() {
			@Override
			public Integer get(MailNotificationMaster mailNotificationMaster) {
				return mailNotificationMaster.getMailNotificationSid();
			}

			@Override
			public Class<Integer> getAttributeClass() {
				return Integer.class;
			}

			@Override
			public Class<MailNotificationMaster> getTypeClass() {
				return MailNotificationMaster.class;
			}
		};
}