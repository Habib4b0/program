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

import com.stpl.app.model.ChSalesProjection;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ch sales projection service. This utility wraps {@link com.stpl.app.service.persistence.impl.ChSalesProjectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChSalesProjectionPersistence
 * @see com.stpl.app.service.persistence.impl.ChSalesProjectionPersistenceImpl
 * @generated
 */
@ProviderType
public class ChSalesProjectionUtil {
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
	public static void clearCache(ChSalesProjection chSalesProjection) {
		getPersistence().clearCache(chSalesProjection);
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
	public static List<ChSalesProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChSalesProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChSalesProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ChSalesProjection> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ChSalesProjection update(ChSalesProjection chSalesProjection) {
		return getPersistence().update(chSalesProjection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ChSalesProjection update(
		ChSalesProjection chSalesProjection, ServiceContext serviceContext) {
		return getPersistence().update(chSalesProjection, serviceContext);
	}

	/**
	* Caches the ch sales projection in the entity cache if it is enabled.
	*
	* @param chSalesProjection the ch sales projection
	*/
	public static void cacheResult(ChSalesProjection chSalesProjection) {
		getPersistence().cacheResult(chSalesProjection);
	}

	/**
	* Caches the ch sales projections in the entity cache if it is enabled.
	*
	* @param chSalesProjections the ch sales projections
	*/
	public static void cacheResult(List<ChSalesProjection> chSalesProjections) {
		getPersistence().cacheResult(chSalesProjections);
	}

	/**
	* Creates a new ch sales projection with the primary key. Does not add the ch sales projection to the database.
	*
	* @param chSalesProjectionPK the primary key for the new ch sales projection
	* @return the new ch sales projection
	*/
	public static ChSalesProjection create(
		ChSalesProjectionPK chSalesProjectionPK) {
		return getPersistence().create(chSalesProjectionPK);
	}

	/**
	* Removes the ch sales projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chSalesProjectionPK the primary key of the ch sales projection
	* @return the ch sales projection that was removed
	* @throws NoSuchChSalesProjectionException if a ch sales projection with the primary key could not be found
	*/
	public static ChSalesProjection remove(
		ChSalesProjectionPK chSalesProjectionPK)
		throws com.stpl.app.exception.NoSuchChSalesProjectionException {
		return getPersistence().remove(chSalesProjectionPK);
	}

	public static ChSalesProjection updateImpl(
		ChSalesProjection chSalesProjection) {
		return getPersistence().updateImpl(chSalesProjection);
	}

	/**
	* Returns the ch sales projection with the primary key or throws a {@link NoSuchChSalesProjectionException} if it could not be found.
	*
	* @param chSalesProjectionPK the primary key of the ch sales projection
	* @return the ch sales projection
	* @throws NoSuchChSalesProjectionException if a ch sales projection with the primary key could not be found
	*/
	public static ChSalesProjection findByPrimaryKey(
		ChSalesProjectionPK chSalesProjectionPK)
		throws com.stpl.app.exception.NoSuchChSalesProjectionException {
		return getPersistence().findByPrimaryKey(chSalesProjectionPK);
	}

	/**
	* Returns the ch sales projection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chSalesProjectionPK the primary key of the ch sales projection
	* @return the ch sales projection, or <code>null</code> if a ch sales projection with the primary key could not be found
	*/
	public static ChSalesProjection fetchByPrimaryKey(
		ChSalesProjectionPK chSalesProjectionPK) {
		return getPersistence().fetchByPrimaryKey(chSalesProjectionPK);
	}

	public static java.util.Map<java.io.Serializable, ChSalesProjection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ch sales projections.
	*
	* @return the ch sales projections
	*/
	public static List<ChSalesProjection> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ch sales projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch sales projections
	* @param end the upper bound of the range of ch sales projections (not inclusive)
	* @return the range of ch sales projections
	*/
	public static List<ChSalesProjection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ch sales projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch sales projections
	* @param end the upper bound of the range of ch sales projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ch sales projections
	*/
	public static List<ChSalesProjection> findAll(int start, int end,
		OrderByComparator<ChSalesProjection> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ch sales projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch sales projections
	* @param end the upper bound of the range of ch sales projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ch sales projections
	*/
	public static List<ChSalesProjection> findAll(int start, int end,
		OrderByComparator<ChSalesProjection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ch sales projections from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ch sales projections.
	*
	* @return the number of ch sales projections
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ChSalesProjectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ChSalesProjectionPersistence, ChSalesProjectionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ChSalesProjectionPersistence.class);
}