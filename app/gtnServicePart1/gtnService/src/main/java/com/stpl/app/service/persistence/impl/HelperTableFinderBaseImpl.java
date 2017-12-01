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

package com.stpl.app.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ReflectionUtil;

import com.stpl.app.model.HelperTable;
import com.stpl.app.service.persistence.HelperTablePersistence;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author
 * @generated
 */
public class HelperTableFinderBaseImpl extends BasePersistenceImpl<HelperTable> {
	public HelperTableFinderBaseImpl() {
		setModelClass(HelperTable.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("description", "DESCRIPTION");
			dbColumnNames.put("refCount", "REF_COUNT");
			dbColumnNames.put("listName", "LIST_NAME");
			dbColumnNames.put("helperTableSid", "HELPER_TABLE_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("aliasName", "ALIAS_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return getHelperTablePersistence().getBadColumnNames();
	}

	/**
	 * Returns the helper table persistence.
	 *
	 * @return the helper table persistence
	 */
	public HelperTablePersistence getHelperTablePersistence() {
		return helperTablePersistence;
	}

	/**
	 * Sets the helper table persistence.
	 *
	 * @param helperTablePersistence the helper table persistence
	 */
	public void setHelperTablePersistence(
		HelperTablePersistence helperTablePersistence) {
		this.helperTablePersistence = helperTablePersistence;
	}

	@BeanReference(type = HelperTablePersistence.class)
	protected HelperTablePersistence helperTablePersistence;
	private static final Log _log = LogFactoryUtil.getLog(HelperTableFinderBaseImpl.class);
}