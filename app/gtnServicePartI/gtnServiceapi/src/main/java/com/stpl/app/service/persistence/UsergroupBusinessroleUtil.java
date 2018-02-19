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

import com.stpl.app.model.UsergroupBusinessrole;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the usergroup businessrole service. This utility wraps {@link com.stpl.app.service.persistence.impl.UsergroupBusinessrolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see UsergroupBusinessrolePersistence
 * @see com.stpl.app.service.persistence.impl.UsergroupBusinessrolePersistenceImpl
 * @generated
 */
@ProviderType
public class UsergroupBusinessroleUtil {
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
	public static void clearCache(UsergroupBusinessrole usergroupBusinessrole) {
		getPersistence().clearCache(usergroupBusinessrole);
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
	public static List<UsergroupBusinessrole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UsergroupBusinessrole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UsergroupBusinessrole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UsergroupBusinessrole> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UsergroupBusinessrole update(
		UsergroupBusinessrole usergroupBusinessrole) {
		return getPersistence().update(usergroupBusinessrole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UsergroupBusinessrole update(
		UsergroupBusinessrole usergroupBusinessrole,
		ServiceContext serviceContext) {
		return getPersistence().update(usergroupBusinessrole, serviceContext);
	}

	/**
	* Caches the usergroup businessrole in the entity cache if it is enabled.
	*
	* @param usergroupBusinessrole the usergroup businessrole
	*/
	public static void cacheResult(UsergroupBusinessrole usergroupBusinessrole) {
		getPersistence().cacheResult(usergroupBusinessrole);
	}

	/**
	* Caches the usergroup businessroles in the entity cache if it is enabled.
	*
	* @param usergroupBusinessroles the usergroup businessroles
	*/
	public static void cacheResult(
		List<UsergroupBusinessrole> usergroupBusinessroles) {
		getPersistence().cacheResult(usergroupBusinessroles);
	}

	/**
	* Creates a new usergroup businessrole with the primary key. Does not add the usergroup businessrole to the database.
	*
	* @param usergroupBusinessroleSid the primary key for the new usergroup businessrole
	* @return the new usergroup businessrole
	*/
	public static UsergroupBusinessrole create(int usergroupBusinessroleSid) {
		return getPersistence().create(usergroupBusinessroleSid);
	}

	/**
	* Removes the usergroup businessrole with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param usergroupBusinessroleSid the primary key of the usergroup businessrole
	* @return the usergroup businessrole that was removed
	* @throws NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
	*/
	public static UsergroupBusinessrole remove(int usergroupBusinessroleSid)
		throws com.stpl.app.exception.NoSuchUsergroupBusinessroleException {
		return getPersistence().remove(usergroupBusinessroleSid);
	}

	public static UsergroupBusinessrole updateImpl(
		UsergroupBusinessrole usergroupBusinessrole) {
		return getPersistence().updateImpl(usergroupBusinessrole);
	}

	/**
	* Returns the usergroup businessrole with the primary key or throws a {@link NoSuchUsergroupBusinessroleException} if it could not be found.
	*
	* @param usergroupBusinessroleSid the primary key of the usergroup businessrole
	* @return the usergroup businessrole
	* @throws NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
	*/
	public static UsergroupBusinessrole findByPrimaryKey(
		int usergroupBusinessroleSid)
		throws com.stpl.app.exception.NoSuchUsergroupBusinessroleException {
		return getPersistence().findByPrimaryKey(usergroupBusinessroleSid);
	}

	/**
	* Returns the usergroup businessrole with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param usergroupBusinessroleSid the primary key of the usergroup businessrole
	* @return the usergroup businessrole, or <code>null</code> if a usergroup businessrole with the primary key could not be found
	*/
	public static UsergroupBusinessrole fetchByPrimaryKey(
		int usergroupBusinessroleSid) {
		return getPersistence().fetchByPrimaryKey(usergroupBusinessroleSid);
	}

	public static java.util.Map<java.io.Serializable, UsergroupBusinessrole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the usergroup businessroles.
	*
	* @return the usergroup businessroles
	*/
	public static List<UsergroupBusinessrole> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the usergroup businessroles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of usergroup businessroles
	* @param end the upper bound of the range of usergroup businessroles (not inclusive)
	* @return the range of usergroup businessroles
	*/
	public static List<UsergroupBusinessrole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the usergroup businessroles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of usergroup businessroles
	* @param end the upper bound of the range of usergroup businessroles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of usergroup businessroles
	*/
	public static List<UsergroupBusinessrole> findAll(int start, int end,
		OrderByComparator<UsergroupBusinessrole> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the usergroup businessroles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of usergroup businessroles
	* @param end the upper bound of the range of usergroup businessroles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of usergroup businessroles
	*/
	public static List<UsergroupBusinessrole> findAll(int start, int end,
		OrderByComparator<UsergroupBusinessrole> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the usergroup businessroles from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of usergroup businessroles.
	*
	* @return the number of usergroup businessroles
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static UsergroupBusinessrolePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UsergroupBusinessrolePersistence, UsergroupBusinessrolePersistence> _serviceTracker =
		ServiceTrackerFactory.open(UsergroupBusinessrolePersistence.class);
}