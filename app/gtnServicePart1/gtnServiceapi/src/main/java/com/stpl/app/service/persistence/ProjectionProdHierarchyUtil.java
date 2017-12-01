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

import com.stpl.app.model.ProjectionProdHierarchy;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the projection prod hierarchy service. This utility wraps {@link com.stpl.app.service.persistence.impl.ProjectionProdHierarchyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionProdHierarchyPersistence
 * @see com.stpl.app.service.persistence.impl.ProjectionProdHierarchyPersistenceImpl
 * @generated
 */
@ProviderType
public class ProjectionProdHierarchyUtil {
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
		ProjectionProdHierarchy projectionProdHierarchy) {
		getPersistence().clearCache(projectionProdHierarchy);
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
	public static List<ProjectionProdHierarchy> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProjectionProdHierarchy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProjectionProdHierarchy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProjectionProdHierarchy> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProjectionProdHierarchy update(
		ProjectionProdHierarchy projectionProdHierarchy) {
		return getPersistence().update(projectionProdHierarchy);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProjectionProdHierarchy update(
		ProjectionProdHierarchy projectionProdHierarchy,
		ServiceContext serviceContext) {
		return getPersistence().update(projectionProdHierarchy, serviceContext);
	}

	/**
	* Caches the projection prod hierarchy in the entity cache if it is enabled.
	*
	* @param projectionProdHierarchy the projection prod hierarchy
	*/
	public static void cacheResult(
		ProjectionProdHierarchy projectionProdHierarchy) {
		getPersistence().cacheResult(projectionProdHierarchy);
	}

	/**
	* Caches the projection prod hierarchies in the entity cache if it is enabled.
	*
	* @param projectionProdHierarchies the projection prod hierarchies
	*/
	public static void cacheResult(
		List<ProjectionProdHierarchy> projectionProdHierarchies) {
		getPersistence().cacheResult(projectionProdHierarchies);
	}

	/**
	* Creates a new projection prod hierarchy with the primary key. Does not add the projection prod hierarchy to the database.
	*
	* @param projectionProdHierarchySid the primary key for the new projection prod hierarchy
	* @return the new projection prod hierarchy
	*/
	public static ProjectionProdHierarchy create(int projectionProdHierarchySid) {
		return getPersistence().create(projectionProdHierarchySid);
	}

	/**
	* Removes the projection prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionProdHierarchySid the primary key of the projection prod hierarchy
	* @return the projection prod hierarchy that was removed
	* @throws NoSuchProjectionProdHierarchyException if a projection prod hierarchy with the primary key could not be found
	*/
	public static ProjectionProdHierarchy remove(int projectionProdHierarchySid)
		throws com.stpl.app.exception.NoSuchProjectionProdHierarchyException {
		return getPersistence().remove(projectionProdHierarchySid);
	}

	public static ProjectionProdHierarchy updateImpl(
		ProjectionProdHierarchy projectionProdHierarchy) {
		return getPersistence().updateImpl(projectionProdHierarchy);
	}

	/**
	* Returns the projection prod hierarchy with the primary key or throws a {@link NoSuchProjectionProdHierarchyException} if it could not be found.
	*
	* @param projectionProdHierarchySid the primary key of the projection prod hierarchy
	* @return the projection prod hierarchy
	* @throws NoSuchProjectionProdHierarchyException if a projection prod hierarchy with the primary key could not be found
	*/
	public static ProjectionProdHierarchy findByPrimaryKey(
		int projectionProdHierarchySid)
		throws com.stpl.app.exception.NoSuchProjectionProdHierarchyException {
		return getPersistence().findByPrimaryKey(projectionProdHierarchySid);
	}

	/**
	* Returns the projection prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionProdHierarchySid the primary key of the projection prod hierarchy
	* @return the projection prod hierarchy, or <code>null</code> if a projection prod hierarchy with the primary key could not be found
	*/
	public static ProjectionProdHierarchy fetchByPrimaryKey(
		int projectionProdHierarchySid) {
		return getPersistence().fetchByPrimaryKey(projectionProdHierarchySid);
	}

	public static java.util.Map<java.io.Serializable, ProjectionProdHierarchy> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the projection prod hierarchies.
	*
	* @return the projection prod hierarchies
	*/
	public static List<ProjectionProdHierarchy> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the projection prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection prod hierarchies
	* @param end the upper bound of the range of projection prod hierarchies (not inclusive)
	* @return the range of projection prod hierarchies
	*/
	public static List<ProjectionProdHierarchy> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the projection prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection prod hierarchies
	* @param end the upper bound of the range of projection prod hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projection prod hierarchies
	*/
	public static List<ProjectionProdHierarchy> findAll(int start, int end,
		OrderByComparator<ProjectionProdHierarchy> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the projection prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection prod hierarchies
	* @param end the upper bound of the range of projection prod hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of projection prod hierarchies
	*/
	public static List<ProjectionProdHierarchy> findAll(int start, int end,
		OrderByComparator<ProjectionProdHierarchy> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the projection prod hierarchies from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of projection prod hierarchies.
	*
	* @return the number of projection prod hierarchies
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProjectionProdHierarchyPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProjectionProdHierarchyPersistence, ProjectionProdHierarchyPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ProjectionProdHierarchyPersistence.class);
}