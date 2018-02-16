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

import com.stpl.app.model.IvldItemHierarchyDefinition;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld item hierarchy definition service. This utility wraps {@link com.stpl.app.service.persistence.impl.IvldItemHierarchyDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemHierarchyDefinitionPersistence
 * @see com.stpl.app.service.persistence.impl.IvldItemHierarchyDefinitionPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldItemHierarchyDefinitionUtil {
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
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
		getPersistence().clearCache(ivldItemHierarchyDefinition);
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
	public static List<IvldItemHierarchyDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldItemHierarchyDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldItemHierarchyDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldItemHierarchyDefinition> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldItemHierarchyDefinition update(
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
		return getPersistence().update(ivldItemHierarchyDefinition);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldItemHierarchyDefinition update(
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(ivldItemHierarchyDefinition, serviceContext);
	}

	/**
	* Caches the ivld item hierarchy definition in the entity cache if it is enabled.
	*
	* @param ivldItemHierarchyDefinition the ivld item hierarchy definition
	*/
	public static void cacheResult(
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
		getPersistence().cacheResult(ivldItemHierarchyDefinition);
	}

	/**
	* Caches the ivld item hierarchy definitions in the entity cache if it is enabled.
	*
	* @param ivldItemHierarchyDefinitions the ivld item hierarchy definitions
	*/
	public static void cacheResult(
		List<IvldItemHierarchyDefinition> ivldItemHierarchyDefinitions) {
		getPersistence().cacheResult(ivldItemHierarchyDefinitions);
	}

	/**
	* Creates a new ivld item hierarchy definition with the primary key. Does not add the ivld item hierarchy definition to the database.
	*
	* @param ivldItemHierarchyDefinitionSid the primary key for the new ivld item hierarchy definition
	* @return the new ivld item hierarchy definition
	*/
	public static IvldItemHierarchyDefinition create(
		int ivldItemHierarchyDefinitionSid) {
		return getPersistence().create(ivldItemHierarchyDefinitionSid);
	}

	/**
	* Removes the ivld item hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
	* @return the ivld item hierarchy definition that was removed
	* @throws NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
	*/
	public static IvldItemHierarchyDefinition remove(
		int ivldItemHierarchyDefinitionSid)
		throws com.stpl.app.exception.NoSuchIvldItemHierarchyDefinitionException {
		return getPersistence().remove(ivldItemHierarchyDefinitionSid);
	}

	public static IvldItemHierarchyDefinition updateImpl(
		IvldItemHierarchyDefinition ivldItemHierarchyDefinition) {
		return getPersistence().updateImpl(ivldItemHierarchyDefinition);
	}

	/**
	* Returns the ivld item hierarchy definition with the primary key or throws a {@link NoSuchIvldItemHierarchyDefinitionException} if it could not be found.
	*
	* @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
	* @return the ivld item hierarchy definition
	* @throws NoSuchIvldItemHierarchyDefinitionException if a ivld item hierarchy definition with the primary key could not be found
	*/
	public static IvldItemHierarchyDefinition findByPrimaryKey(
		int ivldItemHierarchyDefinitionSid)
		throws com.stpl.app.exception.NoSuchIvldItemHierarchyDefinitionException {
		return getPersistence().findByPrimaryKey(ivldItemHierarchyDefinitionSid);
	}

	/**
	* Returns the ivld item hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldItemHierarchyDefinitionSid the primary key of the ivld item hierarchy definition
	* @return the ivld item hierarchy definition, or <code>null</code> if a ivld item hierarchy definition with the primary key could not be found
	*/
	public static IvldItemHierarchyDefinition fetchByPrimaryKey(
		int ivldItemHierarchyDefinitionSid) {
		return getPersistence().fetchByPrimaryKey(ivldItemHierarchyDefinitionSid);
	}

	public static java.util.Map<java.io.Serializable, IvldItemHierarchyDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld item hierarchy definitions.
	*
	* @return the ivld item hierarchy definitions
	*/
	public static List<IvldItemHierarchyDefinition> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld item hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item hierarchy definitions
	* @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
	* @return the range of ivld item hierarchy definitions
	*/
	public static List<IvldItemHierarchyDefinition> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld item hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item hierarchy definitions
	* @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld item hierarchy definitions
	*/
	public static List<IvldItemHierarchyDefinition> findAll(int start, int end,
		OrderByComparator<IvldItemHierarchyDefinition> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld item hierarchy definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item hierarchy definitions
	* @param end the upper bound of the range of ivld item hierarchy definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld item hierarchy definitions
	*/
	public static List<IvldItemHierarchyDefinition> findAll(int start, int end,
		OrderByComparator<IvldItemHierarchyDefinition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld item hierarchy definitions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld item hierarchy definitions.
	*
	* @return the number of ivld item hierarchy definitions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldItemHierarchyDefinitionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldItemHierarchyDefinitionPersistence, IvldItemHierarchyDefinitionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldItemHierarchyDefinitionPersistence.class);
}