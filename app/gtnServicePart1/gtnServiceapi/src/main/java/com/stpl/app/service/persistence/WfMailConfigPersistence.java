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

package com.stpl.app.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.exception.NoSuchWfMailConfigException;
import com.stpl.app.model.WfMailConfig;

/**
 * The persistence interface for the wf mail config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.WfMailConfigPersistenceImpl
 * @see WfMailConfigUtil
 * @generated
 */
@ProviderType
public interface WfMailConfigPersistence extends BasePersistence<WfMailConfig> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WfMailConfigUtil} to access the wf mail config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the wf mail config in the entity cache if it is enabled.
	*
	* @param wfMailConfig the wf mail config
	*/
	public void cacheResult(WfMailConfig wfMailConfig);

	/**
	* Caches the wf mail configs in the entity cache if it is enabled.
	*
	* @param wfMailConfigs the wf mail configs
	*/
	public void cacheResult(java.util.List<WfMailConfig> wfMailConfigs);

	/**
	* Creates a new wf mail config with the primary key. Does not add the wf mail config to the database.
	*
	* @param wfMailConfigSid the primary key for the new wf mail config
	* @return the new wf mail config
	*/
	public WfMailConfig create(int wfMailConfigSid);

	/**
	* Removes the wf mail config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wfMailConfigSid the primary key of the wf mail config
	* @return the wf mail config that was removed
	* @throws NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
	*/
	public WfMailConfig remove(int wfMailConfigSid)
		throws NoSuchWfMailConfigException;

	public WfMailConfig updateImpl(WfMailConfig wfMailConfig);

	/**
	* Returns the wf mail config with the primary key or throws a {@link NoSuchWfMailConfigException} if it could not be found.
	*
	* @param wfMailConfigSid the primary key of the wf mail config
	* @return the wf mail config
	* @throws NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
	*/
	public WfMailConfig findByPrimaryKey(int wfMailConfigSid)
		throws NoSuchWfMailConfigException;

	/**
	* Returns the wf mail config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wfMailConfigSid the primary key of the wf mail config
	* @return the wf mail config, or <code>null</code> if a wf mail config with the primary key could not be found
	*/
	public WfMailConfig fetchByPrimaryKey(int wfMailConfigSid);

	@Override
	public java.util.Map<java.io.Serializable, WfMailConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the wf mail configs.
	*
	* @return the wf mail configs
	*/
	public java.util.List<WfMailConfig> findAll();

	/**
	* Returns a range of all the wf mail configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of wf mail configs
	* @param end the upper bound of the range of wf mail configs (not inclusive)
	* @return the range of wf mail configs
	*/
	public java.util.List<WfMailConfig> findAll(int start, int end);

	/**
	* Returns an ordered range of all the wf mail configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of wf mail configs
	* @param end the upper bound of the range of wf mail configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of wf mail configs
	*/
	public java.util.List<WfMailConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WfMailConfig> orderByComparator);

	/**
	* Returns an ordered range of all the wf mail configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WfMailConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of wf mail configs
	* @param end the upper bound of the range of wf mail configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of wf mail configs
	*/
	public java.util.List<WfMailConfig> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WfMailConfig> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the wf mail configs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of wf mail configs.
	*
	* @return the number of wf mail configs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}