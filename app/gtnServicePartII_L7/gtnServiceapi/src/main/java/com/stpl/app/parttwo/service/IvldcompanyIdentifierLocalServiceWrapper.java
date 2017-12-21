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

package com.stpl.app.parttwo.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldcompanyIdentifierLocalService}.
 *
 * @author
 * @see IvldcompanyIdentifierLocalService
 * @generated
 */
@ProviderType
public class IvldcompanyIdentifierLocalServiceWrapper
	implements IvldcompanyIdentifierLocalService,
		ServiceWrapper<IvldcompanyIdentifierLocalService> {
	public IvldcompanyIdentifierLocalServiceWrapper(
		IvldcompanyIdentifierLocalService ivldcompanyIdentifierLocalService) {
		_ivldcompanyIdentifierLocalService = ivldcompanyIdentifierLocalService;
	}

	@Override
	public IvldcompanyIdentifierLocalService getWrappedService() {
		return _ivldcompanyIdentifierLocalService;
	}

	@Override
	public void setWrappedService(
		IvldcompanyIdentifierLocalService ivldcompanyIdentifierLocalService) {
		_ivldcompanyIdentifierLocalService = ivldcompanyIdentifierLocalService;
	}

	private IvldcompanyIdentifierLocalService _ivldcompanyIdentifierLocalService;
}