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

import com.stpl.app.model.NmSalesProjection;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the nm sales projection service. This utility wraps {@link com.stpl.app.service.persistence.impl.NmSalesProjectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmSalesProjectionPersistence
 * @see com.stpl.app.service.persistence.impl.NmSalesProjectionPersistenceImpl
 * @generated
 */
@ProviderType
public class NmSalesProjectionUtil {
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
	public static void clearCache(NmSalesProjection nmSalesProjection) {
		getPersistence().clearCache(nmSalesProjection);
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
	public static List<NmSalesProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NmSalesProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NmSalesProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NmSalesProjection> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NmSalesProjection update(NmSalesProjection nmSalesProjection) {
		return getPersistence().update(nmSalesProjection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NmSalesProjection update(
		NmSalesProjection nmSalesProjection, ServiceContext serviceContext) {
		return getPersistence().update(nmSalesProjection, serviceContext);
	}

	/**
	* Caches the nm sales projection in the entity cache if it is enabled.
	*
	* @param nmSalesProjection the nm sales projection
	*/
	public static void cacheResult(NmSalesProjection nmSalesProjection) {
		getPersistence().cacheResult(nmSalesProjection);
	}

	/**
	* Caches the nm sales projections in the entity cache if it is enabled.
	*
	* @param nmSalesProjections the nm sales projections
	*/
	public static void cacheResult(List<NmSalesProjection> nmSalesProjections) {
		getPersistence().cacheResult(nmSalesProjections);
	}

	/**
	* Creates a new nm sales projection with the primary key. Does not add the nm sales projection to the database.
	*
	* @param nmSalesProjectionPK the primary key for the new nm sales projection
	* @return the new nm sales projection
	*/
	public static NmSalesProjection create(
		NmSalesProjectionPK nmSalesProjectionPK) {
		return getPersistence().create(nmSalesProjectionPK);
	}

	/**
	* Removes the nm sales projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmSalesProjectionPK the primary key of the nm sales projection
	* @return the nm sales projection that was removed
	* @throws NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
	*/
	public static NmSalesProjection remove(
		NmSalesProjectionPK nmSalesProjectionPK)
		throws com.stpl.app.exception.NoSuchNmSalesProjectionException {
		return getPersistence().remove(nmSalesProjectionPK);
	}

	public static NmSalesProjection updateImpl(
		NmSalesProjection nmSalesProjection) {
		return getPersistence().updateImpl(nmSalesProjection);
	}

	/**
	* Returns the nm sales projection with the primary key or throws a {@link NoSuchNmSalesProjectionException} if it could not be found.
	*
	* @param nmSalesProjectionPK the primary key of the nm sales projection
	* @return the nm sales projection
	* @throws NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
	*/
	public static NmSalesProjection findByPrimaryKey(
		NmSalesProjectionPK nmSalesProjectionPK)
		throws com.stpl.app.exception.NoSuchNmSalesProjectionException {
		return getPersistence().findByPrimaryKey(nmSalesProjectionPK);
	}

	/**
	* Returns the nm sales projection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nmSalesProjectionPK the primary key of the nm sales projection
	* @return the nm sales projection, or <code>null</code> if a nm sales projection with the primary key could not be found
	*/
	public static NmSalesProjection fetchByPrimaryKey(
		NmSalesProjectionPK nmSalesProjectionPK) {
		return getPersistence().fetchByPrimaryKey(nmSalesProjectionPK);
	}

	public static java.util.Map<java.io.Serializable, NmSalesProjection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the nm sales projections.
	*
	* @return the nm sales projections
	*/
	public static List<NmSalesProjection> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the nm sales projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm sales projections
	* @param end the upper bound of the range of nm sales projections (not inclusive)
	* @return the range of nm sales projections
	*/
	public static List<NmSalesProjection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the nm sales projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm sales projections
	* @param end the upper bound of the range of nm sales projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm sales projections
	*/
	public static List<NmSalesProjection> findAll(int start, int end,
		OrderByComparator<NmSalesProjection> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the nm sales projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm sales projections
	* @param end the upper bound of the range of nm sales projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm sales projections
	*/
	public static List<NmSalesProjection> findAll(int start, int end,
		OrderByComparator<NmSalesProjection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the nm sales projections from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of nm sales projections.
	*
	* @return the number of nm sales projections
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NmSalesProjectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NmSalesProjectionPersistence, NmSalesProjectionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NmSalesProjectionPersistence.class);
}