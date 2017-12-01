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

import com.stpl.app.model.RelationshipLevelDefinition;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the relationship level definition service. This utility wraps {@link com.stpl.app.service.persistence.impl.RelationshipLevelDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RelationshipLevelDefinitionPersistence
 * @see com.stpl.app.service.persistence.impl.RelationshipLevelDefinitionPersistenceImpl
 * @generated
 */
@ProviderType
public class RelationshipLevelDefinitionUtil {
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
		RelationshipLevelDefinition relationshipLevelDefinition) {
		getPersistence().clearCache(relationshipLevelDefinition);
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
	public static List<RelationshipLevelDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RelationshipLevelDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RelationshipLevelDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RelationshipLevelDefinition> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RelationshipLevelDefinition update(
		RelationshipLevelDefinition relationshipLevelDefinition) {
		return getPersistence().update(relationshipLevelDefinition);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RelationshipLevelDefinition update(
		RelationshipLevelDefinition relationshipLevelDefinition,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(relationshipLevelDefinition, serviceContext);
	}

	/**
	* Caches the relationship level definition in the entity cache if it is enabled.
	*
	* @param relationshipLevelDefinition the relationship level definition
	*/
	public static void cacheResult(
		RelationshipLevelDefinition relationshipLevelDefinition) {
		getPersistence().cacheResult(relationshipLevelDefinition);
	}

	/**
	* Caches the relationship level definitions in the entity cache if it is enabled.
	*
	* @param relationshipLevelDefinitions the relationship level definitions
	*/
	public static void cacheResult(
		List<RelationshipLevelDefinition> relationshipLevelDefinitions) {
		getPersistence().cacheResult(relationshipLevelDefinitions);
	}

	/**
	* Creates a new relationship level definition with the primary key. Does not add the relationship level definition to the database.
	*
	* @param relationshipLevelSid the primary key for the new relationship level definition
	* @return the new relationship level definition
	*/
	public static RelationshipLevelDefinition create(int relationshipLevelSid) {
		return getPersistence().create(relationshipLevelSid);
	}

	/**
	* Removes the relationship level definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param relationshipLevelSid the primary key of the relationship level definition
	* @return the relationship level definition that was removed
	* @throws NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
	*/
	public static RelationshipLevelDefinition remove(int relationshipLevelSid)
		throws com.stpl.app.exception.NoSuchRelationshipLevelDefinitionException {
		return getPersistence().remove(relationshipLevelSid);
	}

	public static RelationshipLevelDefinition updateImpl(
		RelationshipLevelDefinition relationshipLevelDefinition) {
		return getPersistence().updateImpl(relationshipLevelDefinition);
	}

	/**
	* Returns the relationship level definition with the primary key or throws a {@link NoSuchRelationshipLevelDefinitionException} if it could not be found.
	*
	* @param relationshipLevelSid the primary key of the relationship level definition
	* @return the relationship level definition
	* @throws NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
	*/
	public static RelationshipLevelDefinition findByPrimaryKey(
		int relationshipLevelSid)
		throws com.stpl.app.exception.NoSuchRelationshipLevelDefinitionException {
		return getPersistence().findByPrimaryKey(relationshipLevelSid);
	}

	/**
	* Returns the relationship level definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param relationshipLevelSid the primary key of the relationship level definition
	* @return the relationship level definition, or <code>null</code> if a relationship level definition with the primary key could not be found
	*/
	public static RelationshipLevelDefinition fetchByPrimaryKey(
		int relationshipLevelSid) {
		return getPersistence().fetchByPrimaryKey(relationshipLevelSid);
	}

	public static java.util.Map<java.io.Serializable, RelationshipLevelDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the relationship level definitions.
	*
	* @return the relationship level definitions
	*/
	public static List<RelationshipLevelDefinition> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the relationship level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship level definitions
	* @param end the upper bound of the range of relationship level definitions (not inclusive)
	* @return the range of relationship level definitions
	*/
	public static List<RelationshipLevelDefinition> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the relationship level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship level definitions
	* @param end the upper bound of the range of relationship level definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of relationship level definitions
	*/
	public static List<RelationshipLevelDefinition> findAll(int start, int end,
		OrderByComparator<RelationshipLevelDefinition> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the relationship level definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship level definitions
	* @param end the upper bound of the range of relationship level definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of relationship level definitions
	*/
	public static List<RelationshipLevelDefinition> findAll(int start, int end,
		OrderByComparator<RelationshipLevelDefinition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the relationship level definitions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of relationship level definitions.
	*
	* @return the number of relationship level definitions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RelationshipLevelDefinitionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RelationshipLevelDefinitionPersistence, RelationshipLevelDefinitionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(RelationshipLevelDefinitionPersistence.class);
}