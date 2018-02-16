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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.model.WfMailConfig;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the wf mail config service. This utility wraps {@link com.stpl.app.service.persistence.impl.WfMailConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WfMailConfigPersistence
 * @see com.stpl.app.service.persistence.impl.WfMailConfigPersistenceImpl
 * @generated
 */
@ProviderType
public class WfMailConfigUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(WfMailConfig wfMailConfig) {
		getPersistence().clearCache(wfMailConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WfMailConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WfMailConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WfMailConfig> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WfMailConfig> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WfMailConfig update(WfMailConfig wfMailConfig) {
		return getPersistence().update(wfMailConfig);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WfMailConfig update(WfMailConfig wfMailConfig,
		ServiceContext serviceContext) {
		return getPersistence().update(wfMailConfig, serviceContext);
	}

	/**
	* Caches the wf mail config in the entity cache if it is enabled.
	*
	* @param wfMailConfig the wf mail config
	*/
	public static void cacheResult(WfMailConfig wfMailConfig) {
		getPersistence().cacheResult(wfMailConfig);
	}

	/**
	* Caches the wf mail configs in the entity cache if it is enabled.
	*
	* @param wfMailConfigs the wf mail configs
	*/
	public static void cacheResult(List<WfMailConfig> wfMailConfigs) {
		getPersistence().cacheResult(wfMailConfigs);
	}

	/**
	* Creates a new wf mail config with the primary key. Does not add the wf mail config to the database.
	*
	* @param wfMailConfigSid the primary key for the new wf mail config
	* @return the new wf mail config
	*/
	public static WfMailConfig create(int wfMailConfigSid) {
		return getPersistence().create(wfMailConfigSid);
	}

	/**
	* Removes the wf mail config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wfMailConfigSid the primary key of the wf mail config
	* @return the wf mail config that was removed
	* @throws NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
	*/
	public static WfMailConfig remove(int wfMailConfigSid)
		throws com.stpl.app.exception.NoSuchWfMailConfigException {
		return getPersistence().remove(wfMailConfigSid);
	}

	public static WfMailConfig updateImpl(WfMailConfig wfMailConfig) {
		return getPersistence().updateImpl(wfMailConfig);
	}

	/**
	* Returns the wf mail config with the primary key or throws a {@link NoSuchWfMailConfigException} if it could not be found.
	*
	* @param wfMailConfigSid the primary key of the wf mail config
	* @return the wf mail config
	* @throws NoSuchWfMailConfigException if a wf mail config with the primary key could not be found
	*/
	public static WfMailConfig findByPrimaryKey(int wfMailConfigSid)
		throws com.stpl.app.exception.NoSuchWfMailConfigException {
		return getPersistence().findByPrimaryKey(wfMailConfigSid);
	}

	/**
	* Returns the wf mail config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wfMailConfigSid the primary key of the wf mail config
	* @return the wf mail config, or <code>null</code> if a wf mail config with the primary key could not be found
	*/
	public static WfMailConfig fetchByPrimaryKey(int wfMailConfigSid) {
		return getPersistence().fetchByPrimaryKey(wfMailConfigSid);
	}

	public static java.util.Map<java.io.Serializable, WfMailConfig> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the wf mail configs.
	*
	* @return the wf mail configs
	*/
	public static List<WfMailConfig> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<WfMailConfig> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<WfMailConfig> findAll(int start, int end,
		OrderByComparator<WfMailConfig> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<WfMailConfig> findAll(int start, int end,
		OrderByComparator<WfMailConfig> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the wf mail configs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of wf mail configs.
	*
	* @return the number of wf mail configs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static WfMailConfigPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WfMailConfigPersistence, WfMailConfigPersistence> _serviceTracker =
		ServiceTrackerFactory.open(WfMailConfigPersistence.class);
}