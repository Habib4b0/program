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

import com.stpl.app.model.HierarchyLevelDefinition;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the hierarchy level definition service. This utility wraps {@link com.stpl.app.service.persistence.impl.HierarchyLevelDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyLevelDefinitionPersistence
 * @see com.stpl.app.service.persistence.impl.HierarchyLevelDefinitionPersistenceImpl
 * @generated
 */
@ProviderType
public class HierarchyLevelDefinitionUtil {
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
	public static void clearCache(
		HierarchyLevelDefinition hierarchyLevelDefinition) {
		getPersistence().clearCache(hierarchyLevelDefinition);
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
	public static List<HierarchyLevelDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HierarchyLevelDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HierarchyLevelDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HierarchyLevelDefinition> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HierarchyLevelDefinition update(
		HierarchyLevelDefinition hierarchyLevelDefinition) {
		return getPersistence().update(hierarchyLevelDefinition);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HierarchyLevelDefinition update(
		HierarchyLevelDefinition hierarchyLevelDefinition,
		ServiceContext serviceContext) {
		return getPersistence().update(hierarchyLevelDefinition, serviceContext);
	}

	/**
	* Caches the hierarchy level definition in the entity cache if it is enabled.
	*
	* @param hierarchyLevelDefinition the hierarchy level definition
	*/
	public static void cacheResult(
		HierarchyLevelDefinition hierarchyLevelDefinition) {
		getPersistence().cacheResult(hierarchyLevelDefinition);
	}

	/**
	* Caches the hierarchy level definitions in the entity cache if it is enabled.
	*
	* @param hierarchyLevelDefinitions the hierarchy level definitions
	*/
	public static void cacheResult(
		List<HierarchyLevelDefinition> hierarchyLevelDefinitions) {
		getPersistence().cacheResult(hierarchyLevelDefinitions);
	}

	/**
	* Creates a new hierarchy level definition with the primary key. Does not add the hierarchy level definition to the database.
	*
	* @param hierarchyLevelDefinitionSid the primary key for the new hierarchy level definition
	* @return the new hierarchy level definition
	*/
	public static HierarchyLevelDefinition create(
		int hierarchyLevelDefinitionSid) {
		return getPersistence().create(hierarchyLevelDefinitionSid);
	}

	/**
	* Removes the hierarchy level definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	* @return the hierarchy level definition that was removed
	* @throws NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
	*/
	public static HierarchyLevelDefinition remove(
		int hierarchyLevelDefinitionSid)
		throws com.stpl.app.exception.NoSuchHierarchyLevelDefinitionException {
		return getPersistence().remove(hierarchyLevelDefinitionSid);
	}

	public static HierarchyLevelDefinition updateImpl(
		HierarchyLevelDefinition hierarchyLevelDefinition) {
		return getPersistence().updateImpl(hierarchyLevelDefinition);
	}

	/**
	* Returns the hierarchy level definition with the primary key or throws a {@link NoSuchHierarchyLevelDefinitionException} if it could not be found.
	*
	* @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	* @return the hierarchy level definition
	* @throws NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
	*/
	public static HierarchyLevelDefinition findByPrimaryKey(
		int hierarchyLevelDefinitionSid)
		throws com.stpl.app.exception.NoSuchHierarchyLevelDefinitionException {
		return getPersistence().findByPrimaryKey(hierarchyLevelDefinitionSid);
	}

	/**
	* Returns the hierarchy level definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	* @return the hierarchy level definition, or <code>null</code> if a hierarchy level definition with the primary key could not be found
	*/
	public static HierarchyLevelDefinition fetchByPrimaryKey(
		int hierarchyLevelDefinitionSid) {
		return getPersistence().fetchByPrimaryKey(hierarchyLevelDefinitionSid);
	}

	public static java.util.Map<java.io.Serializable, HierarchyLevelDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the hierarchy level definitions.
	*
	* @return the hierarchy level definitions
	*/
	public static List<HierarchyLevelDefinition> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the hierarchy level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level definitions
	* @param end the upper bound of the range of hierarchy level definitions (not inclusive)
	* @return the range of hierarchy level definitions
	*/
	public static List<HierarchyLevelDefinition> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the hierarchy level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level definitions
	* @param end the upper bound of the range of hierarchy level definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hierarchy level definitions
	*/
	public static List<HierarchyLevelDefinition> findAll(int start, int end,
		OrderByComparator<HierarchyLevelDefinition> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the hierarchy level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hierarchy level definitions
	* @param end the upper bound of the range of hierarchy level definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hierarchy level definitions
	*/
	public static List<HierarchyLevelDefinition> findAll(int start, int end,
		OrderByComparator<HierarchyLevelDefinition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the hierarchy level definitions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of hierarchy level definitions.
	*
	* @return the number of hierarchy level definitions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HierarchyLevelDefinitionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HierarchyLevelDefinitionPersistence, HierarchyLevelDefinitionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(HierarchyLevelDefinitionPersistence.class);
}