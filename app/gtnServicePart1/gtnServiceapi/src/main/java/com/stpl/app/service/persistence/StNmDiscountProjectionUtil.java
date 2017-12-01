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

import com.stpl.app.model.StNmDiscountProjection;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st nm discount projection service. This utility wraps {@link com.stpl.app.service.persistence.impl.StNmDiscountProjectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmDiscountProjectionPersistence
 * @see com.stpl.app.service.persistence.impl.StNmDiscountProjectionPersistenceImpl
 * @generated
 */
@ProviderType
public class StNmDiscountProjectionUtil {
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
	public static void clearCache(StNmDiscountProjection stNmDiscountProjection) {
		getPersistence().clearCache(stNmDiscountProjection);
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
	public static List<StNmDiscountProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StNmDiscountProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StNmDiscountProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StNmDiscountProjection> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StNmDiscountProjection update(
		StNmDiscountProjection stNmDiscountProjection) {
		return getPersistence().update(stNmDiscountProjection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StNmDiscountProjection update(
		StNmDiscountProjection stNmDiscountProjection,
		ServiceContext serviceContext) {
		return getPersistence().update(stNmDiscountProjection, serviceContext);
	}

	/**
	* Caches the st nm discount projection in the entity cache if it is enabled.
	*
	* @param stNmDiscountProjection the st nm discount projection
	*/
	public static void cacheResult(
		StNmDiscountProjection stNmDiscountProjection) {
		getPersistence().cacheResult(stNmDiscountProjection);
	}

	/**
	* Caches the st nm discount projections in the entity cache if it is enabled.
	*
	* @param stNmDiscountProjections the st nm discount projections
	*/
	public static void cacheResult(
		List<StNmDiscountProjection> stNmDiscountProjections) {
		getPersistence().cacheResult(stNmDiscountProjections);
	}

	/**
	* Creates a new st nm discount projection with the primary key. Does not add the st nm discount projection to the database.
	*
	* @param stNmDiscountProjectionPK the primary key for the new st nm discount projection
	* @return the new st nm discount projection
	*/
	public static StNmDiscountProjection create(
		StNmDiscountProjectionPK stNmDiscountProjectionPK) {
		return getPersistence().create(stNmDiscountProjectionPK);
	}

	/**
	* Removes the st nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmDiscountProjectionPK the primary key of the st nm discount projection
	* @return the st nm discount projection that was removed
	* @throws NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
	*/
	public static StNmDiscountProjection remove(
		StNmDiscountProjectionPK stNmDiscountProjectionPK)
		throws com.stpl.app.exception.NoSuchStNmDiscountProjectionException {
		return getPersistence().remove(stNmDiscountProjectionPK);
	}

	public static StNmDiscountProjection updateImpl(
		StNmDiscountProjection stNmDiscountProjection) {
		return getPersistence().updateImpl(stNmDiscountProjection);
	}

	/**
	* Returns the st nm discount projection with the primary key or throws a {@link NoSuchStNmDiscountProjectionException} if it could not be found.
	*
	* @param stNmDiscountProjectionPK the primary key of the st nm discount projection
	* @return the st nm discount projection
	* @throws NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
	*/
	public static StNmDiscountProjection findByPrimaryKey(
		StNmDiscountProjectionPK stNmDiscountProjectionPK)
		throws com.stpl.app.exception.NoSuchStNmDiscountProjectionException {
		return getPersistence().findByPrimaryKey(stNmDiscountProjectionPK);
	}

	/**
	* Returns the st nm discount projection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmDiscountProjectionPK the primary key of the st nm discount projection
	* @return the st nm discount projection, or <code>null</code> if a st nm discount projection with the primary key could not be found
	*/
	public static StNmDiscountProjection fetchByPrimaryKey(
		StNmDiscountProjectionPK stNmDiscountProjectionPK) {
		return getPersistence().fetchByPrimaryKey(stNmDiscountProjectionPK);
	}

	public static java.util.Map<java.io.Serializable, StNmDiscountProjection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st nm discount projections.
	*
	* @return the st nm discount projections
	*/
	public static List<StNmDiscountProjection> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st nm discount projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount projections
	* @param end the upper bound of the range of st nm discount projections (not inclusive)
	* @return the range of st nm discount projections
	*/
	public static List<StNmDiscountProjection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st nm discount projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount projections
	* @param end the upper bound of the range of st nm discount projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm discount projections
	*/
	public static List<StNmDiscountProjection> findAll(int start, int end,
		OrderByComparator<StNmDiscountProjection> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st nm discount projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount projections
	* @param end the upper bound of the range of st nm discount projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm discount projections
	*/
	public static List<StNmDiscountProjection> findAll(int start, int end,
		OrderByComparator<StNmDiscountProjection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st nm discount projections from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st nm discount projections.
	*
	* @return the number of st nm discount projections
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StNmDiscountProjectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StNmDiscountProjectionPersistence, StNmDiscountProjectionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StNmDiscountProjectionPersistence.class);
}