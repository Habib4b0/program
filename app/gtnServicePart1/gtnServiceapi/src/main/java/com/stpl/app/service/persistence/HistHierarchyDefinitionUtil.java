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

import com.stpl.app.model.HistHierarchyDefinition;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the hist hierarchy definition service. This utility wraps {@link com.stpl.app.service.persistence.impl.HistHierarchyDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyDefinitionPersistence
 * @see com.stpl.app.service.persistence.impl.HistHierarchyDefinitionPersistenceImpl
 * @generated
 */
@ProviderType
public class HistHierarchyDefinitionUtil {
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
		HistHierarchyDefinition histHierarchyDefinition) {
		getPersistence().clearCache(histHierarchyDefinition);
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
	public static List<HistHierarchyDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HistHierarchyDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HistHierarchyDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HistHierarchyDefinition> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HistHierarchyDefinition update(
		HistHierarchyDefinition histHierarchyDefinition) {
		return getPersistence().update(histHierarchyDefinition);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HistHierarchyDefinition update(
		HistHierarchyDefinition histHierarchyDefinition,
		ServiceContext serviceContext) {
		return getPersistence().update(histHierarchyDefinition, serviceContext);
	}

	/**
	* Caches the hist hierarchy definition in the entity cache if it is enabled.
	*
	* @param histHierarchyDefinition the hist hierarchy definition
	*/
	public static void cacheResult(
		HistHierarchyDefinition histHierarchyDefinition) {
		getPersistence().cacheResult(histHierarchyDefinition);
	}

	/**
	* Caches the hist hierarchy definitions in the entity cache if it is enabled.
	*
	* @param histHierarchyDefinitions the hist hierarchy definitions
	*/
	public static void cacheResult(
		List<HistHierarchyDefinition> histHierarchyDefinitions) {
		getPersistence().cacheResult(histHierarchyDefinitions);
	}

	/**
	* Creates a new hist hierarchy definition with the primary key. Does not add the hist hierarchy definition to the database.
	*
	* @param histHierarchyDefinitionPK the primary key for the new hist hierarchy definition
	* @return the new hist hierarchy definition
	*/
	public static HistHierarchyDefinition create(
		HistHierarchyDefinitionPK histHierarchyDefinitionPK) {
		return getPersistence().create(histHierarchyDefinitionPK);
	}

	/**
	* Removes the hist hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
	* @return the hist hierarchy definition that was removed
	* @throws NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
	*/
	public static HistHierarchyDefinition remove(
		HistHierarchyDefinitionPK histHierarchyDefinitionPK)
		throws com.stpl.app.exception.NoSuchHistHierarchyDefinitionException {
		return getPersistence().remove(histHierarchyDefinitionPK);
	}

	public static HistHierarchyDefinition updateImpl(
		HistHierarchyDefinition histHierarchyDefinition) {
		return getPersistence().updateImpl(histHierarchyDefinition);
	}

	/**
	* Returns the hist hierarchy definition with the primary key or throws a {@link NoSuchHistHierarchyDefinitionException} if it could not be found.
	*
	* @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
	* @return the hist hierarchy definition
	* @throws NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
	*/
	public static HistHierarchyDefinition findByPrimaryKey(
		HistHierarchyDefinitionPK histHierarchyDefinitionPK)
		throws com.stpl.app.exception.NoSuchHistHierarchyDefinitionException {
		return getPersistence().findByPrimaryKey(histHierarchyDefinitionPK);
	}

	/**
	* Returns the hist hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
	* @return the hist hierarchy definition, or <code>null</code> if a hist hierarchy definition with the primary key could not be found
	*/
	public static HistHierarchyDefinition fetchByPrimaryKey(
		HistHierarchyDefinitionPK histHierarchyDefinitionPK) {
		return getPersistence().fetchByPrimaryKey(histHierarchyDefinitionPK);
	}

	public static java.util.Map<java.io.Serializable, HistHierarchyDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the hist hierarchy definitions.
	*
	* @return the hist hierarchy definitions
	*/
	public static List<HistHierarchyDefinition> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the hist hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy definitions
	* @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
	* @return the range of hist hierarchy definitions
	*/
	public static List<HistHierarchyDefinition> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the hist hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy definitions
	* @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist hierarchy definitions
	*/
	public static List<HistHierarchyDefinition> findAll(int start, int end,
		OrderByComparator<HistHierarchyDefinition> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the hist hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist hierarchy definitions
	* @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist hierarchy definitions
	*/
	public static List<HistHierarchyDefinition> findAll(int start, int end,
		OrderByComparator<HistHierarchyDefinition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the hist hierarchy definitions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of hist hierarchy definitions.
	*
	* @return the number of hist hierarchy definitions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HistHierarchyDefinitionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistHierarchyDefinitionPersistence, HistHierarchyDefinitionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(HistHierarchyDefinitionPersistence.class);
}