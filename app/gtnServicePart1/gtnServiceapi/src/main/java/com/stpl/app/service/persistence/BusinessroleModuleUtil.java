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

import com.stpl.app.model.BusinessroleModule;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the businessrole module service. This utility wraps {@link com.stpl.app.service.persistence.impl.BusinessroleModulePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BusinessroleModulePersistence
 * @see com.stpl.app.service.persistence.impl.BusinessroleModulePersistenceImpl
 * @generated
 */
@ProviderType
public class BusinessroleModuleUtil {
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
	public static void clearCache(BusinessroleModule businessroleModule) {
		getPersistence().clearCache(businessroleModule);
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
	public static List<BusinessroleModule> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BusinessroleModule> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BusinessroleModule> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BusinessroleModule> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BusinessroleModule update(
		BusinessroleModule businessroleModule) {
		return getPersistence().update(businessroleModule);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BusinessroleModule update(
		BusinessroleModule businessroleModule, ServiceContext serviceContext) {
		return getPersistence().update(businessroleModule, serviceContext);
	}

	/**
	* Caches the businessrole module in the entity cache if it is enabled.
	*
	* @param businessroleModule the businessrole module
	*/
	public static void cacheResult(BusinessroleModule businessroleModule) {
		getPersistence().cacheResult(businessroleModule);
	}

	/**
	* Caches the businessrole modules in the entity cache if it is enabled.
	*
	* @param businessroleModules the businessrole modules
	*/
	public static void cacheResult(List<BusinessroleModule> businessroleModules) {
		getPersistence().cacheResult(businessroleModules);
	}

	/**
	* Creates a new businessrole module with the primary key. Does not add the businessrole module to the database.
	*
	* @param businessroleModuleSid the primary key for the new businessrole module
	* @return the new businessrole module
	*/
	public static BusinessroleModule create(int businessroleModuleSid) {
		return getPersistence().create(businessroleModuleSid);
	}

	/**
	* Removes the businessrole module with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param businessroleModuleSid the primary key of the businessrole module
	* @return the businessrole module that was removed
	* @throws NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
	*/
	public static BusinessroleModule remove(int businessroleModuleSid)
		throws com.stpl.app.exception.NoSuchBusinessroleModuleException {
		return getPersistence().remove(businessroleModuleSid);
	}

	public static BusinessroleModule updateImpl(
		BusinessroleModule businessroleModule) {
		return getPersistence().updateImpl(businessroleModule);
	}

	/**
	* Returns the businessrole module with the primary key or throws a {@link NoSuchBusinessroleModuleException} if it could not be found.
	*
	* @param businessroleModuleSid the primary key of the businessrole module
	* @return the businessrole module
	* @throws NoSuchBusinessroleModuleException if a businessrole module with the primary key could not be found
	*/
	public static BusinessroleModule findByPrimaryKey(int businessroleModuleSid)
		throws com.stpl.app.exception.NoSuchBusinessroleModuleException {
		return getPersistence().findByPrimaryKey(businessroleModuleSid);
	}

	/**
	* Returns the businessrole module with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param businessroleModuleSid the primary key of the businessrole module
	* @return the businessrole module, or <code>null</code> if a businessrole module with the primary key could not be found
	*/
	public static BusinessroleModule fetchByPrimaryKey(
		int businessroleModuleSid) {
		return getPersistence().fetchByPrimaryKey(businessroleModuleSid);
	}

	public static java.util.Map<java.io.Serializable, BusinessroleModule> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the businessrole modules.
	*
	* @return the businessrole modules
	*/
	public static List<BusinessroleModule> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the businessrole modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole modules
	* @param end the upper bound of the range of businessrole modules (not inclusive)
	* @return the range of businessrole modules
	*/
	public static List<BusinessroleModule> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the businessrole modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole modules
	* @param end the upper bound of the range of businessrole modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of businessrole modules
	*/
	public static List<BusinessroleModule> findAll(int start, int end,
		OrderByComparator<BusinessroleModule> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the businessrole modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BusinessroleModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of businessrole modules
	* @param end the upper bound of the range of businessrole modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of businessrole modules
	*/
	public static List<BusinessroleModule> findAll(int start, int end,
		OrderByComparator<BusinessroleModule> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the businessrole modules from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of businessrole modules.
	*
	* @return the number of businessrole modules
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static BusinessroleModulePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BusinessroleModulePersistence, BusinessroleModulePersistence> _serviceTracker =
		ServiceTrackerFactory.open(BusinessroleModulePersistence.class);
}