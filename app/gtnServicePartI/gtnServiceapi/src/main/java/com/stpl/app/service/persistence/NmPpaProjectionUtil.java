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

import com.stpl.app.model.NmPpaProjection;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the nm ppa projection service. This utility wraps {@link com.stpl.app.service.persistence.impl.NmPpaProjectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmPpaProjectionPersistence
 * @see com.stpl.app.service.persistence.impl.NmPpaProjectionPersistenceImpl
 * @generated
 */
@ProviderType
public class NmPpaProjectionUtil {
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
	public static void clearCache(NmPpaProjection nmPpaProjection) {
		getPersistence().clearCache(nmPpaProjection);
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
	public static List<NmPpaProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NmPpaProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NmPpaProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NmPpaProjection> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NmPpaProjection update(NmPpaProjection nmPpaProjection) {
		return getPersistence().update(nmPpaProjection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NmPpaProjection update(NmPpaProjection nmPpaProjection,
		ServiceContext serviceContext) {
		return getPersistence().update(nmPpaProjection, serviceContext);
	}

	/**
	* Caches the nm ppa projection in the entity cache if it is enabled.
	*
	* @param nmPpaProjection the nm ppa projection
	*/
	public static void cacheResult(NmPpaProjection nmPpaProjection) {
		getPersistence().cacheResult(nmPpaProjection);
	}

	/**
	* Caches the nm ppa projections in the entity cache if it is enabled.
	*
	* @param nmPpaProjections the nm ppa projections
	*/
	public static void cacheResult(List<NmPpaProjection> nmPpaProjections) {
		getPersistence().cacheResult(nmPpaProjections);
	}

	/**
	* Creates a new nm ppa projection with the primary key. Does not add the nm ppa projection to the database.
	*
	* @param nmPpaProjectionPK the primary key for the new nm ppa projection
	* @return the new nm ppa projection
	*/
	public static NmPpaProjection create(NmPpaProjectionPK nmPpaProjectionPK) {
		return getPersistence().create(nmPpaProjectionPK);
	}

	/**
	* Removes the nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmPpaProjectionPK the primary key of the nm ppa projection
	* @return the nm ppa projection that was removed
	* @throws NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
	*/
	public static NmPpaProjection remove(NmPpaProjectionPK nmPpaProjectionPK)
		throws com.stpl.app.exception.NoSuchNmPpaProjectionException {
		return getPersistence().remove(nmPpaProjectionPK);
	}

	public static NmPpaProjection updateImpl(NmPpaProjection nmPpaProjection) {
		return getPersistence().updateImpl(nmPpaProjection);
	}

	/**
	* Returns the nm ppa projection with the primary key or throws a {@link NoSuchNmPpaProjectionException} if it could not be found.
	*
	* @param nmPpaProjectionPK the primary key of the nm ppa projection
	* @return the nm ppa projection
	* @throws NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
	*/
	public static NmPpaProjection findByPrimaryKey(
		NmPpaProjectionPK nmPpaProjectionPK)
		throws com.stpl.app.exception.NoSuchNmPpaProjectionException {
		return getPersistence().findByPrimaryKey(nmPpaProjectionPK);
	}

	/**
	* Returns the nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nmPpaProjectionPK the primary key of the nm ppa projection
	* @return the nm ppa projection, or <code>null</code> if a nm ppa projection with the primary key could not be found
	*/
	public static NmPpaProjection fetchByPrimaryKey(
		NmPpaProjectionPK nmPpaProjectionPK) {
		return getPersistence().fetchByPrimaryKey(nmPpaProjectionPK);
	}

	public static java.util.Map<java.io.Serializable, NmPpaProjection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the nm ppa projections.
	*
	* @return the nm ppa projections
	*/
	public static List<NmPpaProjection> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the nm ppa projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projections
	* @param end the upper bound of the range of nm ppa projections (not inclusive)
	* @return the range of nm ppa projections
	*/
	public static List<NmPpaProjection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the nm ppa projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projections
	* @param end the upper bound of the range of nm ppa projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm ppa projections
	*/
	public static List<NmPpaProjection> findAll(int start, int end,
		OrderByComparator<NmPpaProjection> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the nm ppa projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projections
	* @param end the upper bound of the range of nm ppa projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm ppa projections
	*/
	public static List<NmPpaProjection> findAll(int start, int end,
		OrderByComparator<NmPpaProjection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the nm ppa projections from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of nm ppa projections.
	*
	* @return the number of nm ppa projections
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NmPpaProjectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NmPpaProjectionPersistence, NmPpaProjectionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NmPpaProjectionPersistence.class);
}