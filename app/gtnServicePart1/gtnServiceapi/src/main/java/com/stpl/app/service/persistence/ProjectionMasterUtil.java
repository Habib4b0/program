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

import com.stpl.app.model.ProjectionMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the projection master service. This utility wraps {@link com.stpl.app.service.persistence.impl.ProjectionMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.impl.ProjectionMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class ProjectionMasterUtil {
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
	public static void clearCache(ProjectionMaster projectionMaster) {
		getPersistence().clearCache(projectionMaster);
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
	public static List<ProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProjectionMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProjectionMaster update(ProjectionMaster projectionMaster) {
		return getPersistence().update(projectionMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProjectionMaster update(ProjectionMaster projectionMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(projectionMaster, serviceContext);
	}

	/**
	* Caches the projection master in the entity cache if it is enabled.
	*
	* @param projectionMaster the projection master
	*/
	public static void cacheResult(ProjectionMaster projectionMaster) {
		getPersistence().cacheResult(projectionMaster);
	}

	/**
	* Caches the projection masters in the entity cache if it is enabled.
	*
	* @param projectionMasters the projection masters
	*/
	public static void cacheResult(List<ProjectionMaster> projectionMasters) {
		getPersistence().cacheResult(projectionMasters);
	}

	/**
	* Creates a new projection master with the primary key. Does not add the projection master to the database.
	*
	* @param projectionMasterSid the primary key for the new projection master
	* @return the new projection master
	*/
	public static ProjectionMaster create(int projectionMasterSid) {
		return getPersistence().create(projectionMasterSid);
	}

	/**
	* Removes the projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionMasterSid the primary key of the projection master
	* @return the projection master that was removed
	* @throws NoSuchProjectionMasterException if a projection master with the primary key could not be found
	*/
	public static ProjectionMaster remove(int projectionMasterSid)
		throws com.stpl.app.exception.NoSuchProjectionMasterException {
		return getPersistence().remove(projectionMasterSid);
	}

	public static ProjectionMaster updateImpl(ProjectionMaster projectionMaster) {
		return getPersistence().updateImpl(projectionMaster);
	}

	/**
	* Returns the projection master with the primary key or throws a {@link NoSuchProjectionMasterException} if it could not be found.
	*
	* @param projectionMasterSid the primary key of the projection master
	* @return the projection master
	* @throws NoSuchProjectionMasterException if a projection master with the primary key could not be found
	*/
	public static ProjectionMaster findByPrimaryKey(int projectionMasterSid)
		throws com.stpl.app.exception.NoSuchProjectionMasterException {
		return getPersistence().findByPrimaryKey(projectionMasterSid);
	}

	/**
	* Returns the projection master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionMasterSid the primary key of the projection master
	* @return the projection master, or <code>null</code> if a projection master with the primary key could not be found
	*/
	public static ProjectionMaster fetchByPrimaryKey(int projectionMasterSid) {
		return getPersistence().fetchByPrimaryKey(projectionMasterSid);
	}

	public static java.util.Map<java.io.Serializable, ProjectionMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the projection masters.
	*
	* @return the projection masters
	*/
	public static List<ProjectionMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection masters
	* @param end the upper bound of the range of projection masters (not inclusive)
	* @return the range of projection masters
	*/
	public static List<ProjectionMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection masters
	* @param end the upper bound of the range of projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projection masters
	*/
	public static List<ProjectionMaster> findAll(int start, int end,
		OrderByComparator<ProjectionMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection masters
	* @param end the upper bound of the range of projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of projection masters
	*/
	public static List<ProjectionMaster> findAll(int start, int end,
		OrderByComparator<ProjectionMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the projection masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of projection masters.
	*
	* @return the number of projection masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProjectionMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProjectionMasterPersistence, ProjectionMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ProjectionMasterPersistence.class);
}