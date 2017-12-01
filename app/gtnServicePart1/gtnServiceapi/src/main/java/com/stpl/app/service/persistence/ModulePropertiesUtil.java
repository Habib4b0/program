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

import com.stpl.app.model.ModuleProperties;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the module properties service. This utility wraps {@link com.stpl.app.service.persistence.impl.ModulePropertiesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ModulePropertiesPersistence
 * @see com.stpl.app.service.persistence.impl.ModulePropertiesPersistenceImpl
 * @generated
 */
@ProviderType
public class ModulePropertiesUtil {
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
	public static void clearCache(ModuleProperties moduleProperties) {
		getPersistence().clearCache(moduleProperties);
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
	public static List<ModuleProperties> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ModuleProperties> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ModuleProperties> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ModuleProperties> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ModuleProperties update(ModuleProperties moduleProperties) {
		return getPersistence().update(moduleProperties);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ModuleProperties update(ModuleProperties moduleProperties,
		ServiceContext serviceContext) {
		return getPersistence().update(moduleProperties, serviceContext);
	}

	/**
	* Caches the module properties in the entity cache if it is enabled.
	*
	* @param moduleProperties the module properties
	*/
	public static void cacheResult(ModuleProperties moduleProperties) {
		getPersistence().cacheResult(moduleProperties);
	}

	/**
	* Caches the module propertieses in the entity cache if it is enabled.
	*
	* @param modulePropertieses the module propertieses
	*/
	public static void cacheResult(List<ModuleProperties> modulePropertieses) {
		getPersistence().cacheResult(modulePropertieses);
	}

	/**
	* Creates a new module properties with the primary key. Does not add the module properties to the database.
	*
	* @param modulePropertySid the primary key for the new module properties
	* @return the new module properties
	*/
	public static ModuleProperties create(int modulePropertySid) {
		return getPersistence().create(modulePropertySid);
	}

	/**
	* Removes the module properties with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param modulePropertySid the primary key of the module properties
	* @return the module properties that was removed
	* @throws NoSuchModulePropertiesException if a module properties with the primary key could not be found
	*/
	public static ModuleProperties remove(int modulePropertySid)
		throws com.stpl.app.exception.NoSuchModulePropertiesException {
		return getPersistence().remove(modulePropertySid);
	}

	public static ModuleProperties updateImpl(ModuleProperties moduleProperties) {
		return getPersistence().updateImpl(moduleProperties);
	}

	/**
	* Returns the module properties with the primary key or throws a {@link NoSuchModulePropertiesException} if it could not be found.
	*
	* @param modulePropertySid the primary key of the module properties
	* @return the module properties
	* @throws NoSuchModulePropertiesException if a module properties with the primary key could not be found
	*/
	public static ModuleProperties findByPrimaryKey(int modulePropertySid)
		throws com.stpl.app.exception.NoSuchModulePropertiesException {
		return getPersistence().findByPrimaryKey(modulePropertySid);
	}

	/**
	* Returns the module properties with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param modulePropertySid the primary key of the module properties
	* @return the module properties, or <code>null</code> if a module properties with the primary key could not be found
	*/
	public static ModuleProperties fetchByPrimaryKey(int modulePropertySid) {
		return getPersistence().fetchByPrimaryKey(modulePropertySid);
	}

	public static java.util.Map<java.io.Serializable, ModuleProperties> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the module propertieses.
	*
	* @return the module propertieses
	*/
	public static List<ModuleProperties> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the module propertieses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module propertieses
	* @param end the upper bound of the range of module propertieses (not inclusive)
	* @return the range of module propertieses
	*/
	public static List<ModuleProperties> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the module propertieses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module propertieses
	* @param end the upper bound of the range of module propertieses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of module propertieses
	*/
	public static List<ModuleProperties> findAll(int start, int end,
		OrderByComparator<ModuleProperties> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the module propertieses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of module propertieses
	* @param end the upper bound of the range of module propertieses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of module propertieses
	*/
	public static List<ModuleProperties> findAll(int start, int end,
		OrderByComparator<ModuleProperties> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the module propertieses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of module propertieses.
	*
	* @return the number of module propertieses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ModulePropertiesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ModulePropertiesPersistence, ModulePropertiesPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ModulePropertiesPersistence.class);
}