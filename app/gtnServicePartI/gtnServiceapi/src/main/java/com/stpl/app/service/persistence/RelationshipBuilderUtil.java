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

import com.stpl.app.model.RelationshipBuilder;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the relationship builder service. This utility wraps {@link com.stpl.app.service.persistence.impl.RelationshipBuilderPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RelationshipBuilderPersistence
 * @see com.stpl.app.service.persistence.impl.RelationshipBuilderPersistenceImpl
 * @generated
 */
@ProviderType
public class RelationshipBuilderUtil {
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
	public static void clearCache(RelationshipBuilder relationshipBuilder) {
		getPersistence().clearCache(relationshipBuilder);
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
	public static List<RelationshipBuilder> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RelationshipBuilder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RelationshipBuilder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RelationshipBuilder> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RelationshipBuilder update(
		RelationshipBuilder relationshipBuilder) {
		return getPersistence().update(relationshipBuilder);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RelationshipBuilder update(
		RelationshipBuilder relationshipBuilder, ServiceContext serviceContext) {
		return getPersistence().update(relationshipBuilder, serviceContext);
	}

	/**
	* Caches the relationship builder in the entity cache if it is enabled.
	*
	* @param relationshipBuilder the relationship builder
	*/
	public static void cacheResult(RelationshipBuilder relationshipBuilder) {
		getPersistence().cacheResult(relationshipBuilder);
	}

	/**
	* Caches the relationship builders in the entity cache if it is enabled.
	*
	* @param relationshipBuilders the relationship builders
	*/
	public static void cacheResult(
		List<RelationshipBuilder> relationshipBuilders) {
		getPersistence().cacheResult(relationshipBuilders);
	}

	/**
	* Creates a new relationship builder with the primary key. Does not add the relationship builder to the database.
	*
	* @param relationshipBuilderSid the primary key for the new relationship builder
	* @return the new relationship builder
	*/
	public static RelationshipBuilder create(int relationshipBuilderSid) {
		return getPersistence().create(relationshipBuilderSid);
	}

	/**
	* Removes the relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param relationshipBuilderSid the primary key of the relationship builder
	* @return the relationship builder that was removed
	* @throws NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
	*/
	public static RelationshipBuilder remove(int relationshipBuilderSid)
		throws com.stpl.app.exception.NoSuchRelationshipBuilderException {
		return getPersistence().remove(relationshipBuilderSid);
	}

	public static RelationshipBuilder updateImpl(
		RelationshipBuilder relationshipBuilder) {
		return getPersistence().updateImpl(relationshipBuilder);
	}

	/**
	* Returns the relationship builder with the primary key or throws a {@link NoSuchRelationshipBuilderException} if it could not be found.
	*
	* @param relationshipBuilderSid the primary key of the relationship builder
	* @return the relationship builder
	* @throws NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
	*/
	public static RelationshipBuilder findByPrimaryKey(
		int relationshipBuilderSid)
		throws com.stpl.app.exception.NoSuchRelationshipBuilderException {
		return getPersistence().findByPrimaryKey(relationshipBuilderSid);
	}

	/**
	* Returns the relationship builder with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param relationshipBuilderSid the primary key of the relationship builder
	* @return the relationship builder, or <code>null</code> if a relationship builder with the primary key could not be found
	*/
	public static RelationshipBuilder fetchByPrimaryKey(
		int relationshipBuilderSid) {
		return getPersistence().fetchByPrimaryKey(relationshipBuilderSid);
	}

	public static java.util.Map<java.io.Serializable, RelationshipBuilder> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the relationship builders.
	*
	* @return the relationship builders
	*/
	public static List<RelationshipBuilder> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the relationship builders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship builders
	* @param end the upper bound of the range of relationship builders (not inclusive)
	* @return the range of relationship builders
	*/
	public static List<RelationshipBuilder> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the relationship builders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship builders
	* @param end the upper bound of the range of relationship builders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of relationship builders
	*/
	public static List<RelationshipBuilder> findAll(int start, int end,
		OrderByComparator<RelationshipBuilder> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the relationship builders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of relationship builders
	* @param end the upper bound of the range of relationship builders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of relationship builders
	*/
	public static List<RelationshipBuilder> findAll(int start, int end,
		OrderByComparator<RelationshipBuilder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the relationship builders from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of relationship builders.
	*
	* @return the number of relationship builders
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RelationshipBuilderPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RelationshipBuilderPersistence, RelationshipBuilderPersistence> _serviceTracker =
		ServiceTrackerFactory.open(RelationshipBuilderPersistence.class);
}