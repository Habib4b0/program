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

import com.stpl.app.model.HierarchyDefinition;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the hierarchy definition service. This utility wraps {@link com.stpl.app.service.persistence.impl.HierarchyDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyDefinitionPersistence
 * @see com.stpl.app.service.persistence.impl.HierarchyDefinitionPersistenceImpl
 * @generated
 */
@ProviderType
public class HierarchyDefinitionUtil {
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
	public static void clearCache(HierarchyDefinition hierarchyDefinition) {
		getPersistence().clearCache(hierarchyDefinition);
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
	public static List<HierarchyDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HierarchyDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HierarchyDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HierarchyDefinition> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HierarchyDefinition update(
		HierarchyDefinition hierarchyDefinition) {
		return getPersistence().update(hierarchyDefinition);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HierarchyDefinition update(
		HierarchyDefinition hierarchyDefinition, ServiceContext serviceContext) {
		return getPersistence().update(hierarchyDefinition, serviceContext);
	}

	/**
	* Caches the hierarchy definition in the entity cache if it is enabled.
	*
	* @param hierarchyDefinition the hierarchy definition
	*/
	public static void cacheResult(HierarchyDefinition hierarchyDefinition) {
		getPersistence().cacheResult(hierarchyDefinition);
	}

	/**
	* Caches the hierarchy definitions in the entity cache if it is enabled.
	*
	* @param hierarchyDefinitions the hierarchy definitions
	*/
	public static void cacheResult(
		List<HierarchyDefinition> hierarchyDefinitions) {
		getPersistence().cacheResult(hierarchyDefinitions);
	}

	/**
	* Creates a new hierarchy definition with the primary key. Does not add the hierarchy definition to the database.
	*
	* @param hierarchyDefinitionSid the primary key for the new hierarchy definition
	* @return the new hierarchy definition
	*/
	public static HierarchyDefinition create(int hierarchyDefinitionSid) {
		return getPersistence().create(hierarchyDefinitionSid);
	}

	/**
	* Removes the hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyDefinitionSid the primary key of the hierarchy definition
	* @return the hierarchy definition that was removed
	* @throws NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
	*/
	public static HierarchyDefinition remove(int hierarchyDefinitionSid)
		throws com.stpl.app.exception.NoSuchHierarchyDefinitionException {
		return getPersistence().remove(hierarchyDefinitionSid);
	}

	public static HierarchyDefinition updateImpl(
		HierarchyDefinition hierarchyDefinition) {
		return getPersistence().updateImpl(hierarchyDefinition);
	}

	/**
	* Returns the hierarchy definition with the primary key or throws a {@link NoSuchHierarchyDefinitionException} if it could not be found.
	*
	* @param hierarchyDefinitionSid the primary key of the hierarchy definition
	* @return the hierarchy definition
	* @throws NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
	*/
	public static HierarchyDefinition findByPrimaryKey(
		int hierarchyDefinitionSid)
		throws com.stpl.app.exception.NoSuchHierarchyDefinitionException {
		return getPersistence().findByPrimaryKey(hierarchyDefinitionSid);
	}

	/**
	* Returns the hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hierarchyDefinitionSid the primary key of the hierarchy definition
	* @return the hierarchy definition, or <code>null</code> if a hierarchy definition with the primary key could not be found
	*/
	public static HierarchyDefinition fetchByPrimaryKey(
		int hierarchyDefinitionSid) {
		return getPersistence().fetchByPrimaryKey(hierarchyDefinitionSid);
	}

	public static java.util.Map<java.io.Serializable, HierarchyDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the hierarchy definitions.
	*
	* @return the hierarchy definitions
	*/
	public static List<HierarchyDefinition> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy definitions
	* @param end the upper bound of the range of hierarchy definitions (not inclusive)
	* @return the range of hierarchy definitions
	*/
	public static List<HierarchyDefinition> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy definitions
	* @param end the upper bound of the range of hierarchy definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hierarchy definitions
	*/
	public static List<HierarchyDefinition> findAll(int start, int end,
		OrderByComparator<HierarchyDefinition> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy definitions
	* @param end the upper bound of the range of hierarchy definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hierarchy definitions
	*/
	public static List<HierarchyDefinition> findAll(int start, int end,
		OrderByComparator<HierarchyDefinition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the hierarchy definitions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of hierarchy definitions.
	*
	* @return the number of hierarchy definitions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HierarchyDefinitionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HierarchyDefinitionPersistence, HierarchyDefinitionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(HierarchyDefinitionPersistence.class);
}