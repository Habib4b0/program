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

import com.stpl.app.model.NmDiscountProjection;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the nm discount projection service. This utility wraps {@link com.stpl.app.service.persistence.impl.NmDiscountProjectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmDiscountProjectionPersistence
 * @see com.stpl.app.service.persistence.impl.NmDiscountProjectionPersistenceImpl
 * @generated
 */
@ProviderType
public class NmDiscountProjectionUtil {
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
	public static void clearCache(NmDiscountProjection nmDiscountProjection) {
		getPersistence().clearCache(nmDiscountProjection);
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
	public static List<NmDiscountProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NmDiscountProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NmDiscountProjection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NmDiscountProjection> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NmDiscountProjection update(
		NmDiscountProjection nmDiscountProjection) {
		return getPersistence().update(nmDiscountProjection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NmDiscountProjection update(
		NmDiscountProjection nmDiscountProjection, ServiceContext serviceContext) {
		return getPersistence().update(nmDiscountProjection, serviceContext);
	}

	/**
	* Caches the nm discount projection in the entity cache if it is enabled.
	*
	* @param nmDiscountProjection the nm discount projection
	*/
	public static void cacheResult(NmDiscountProjection nmDiscountProjection) {
		getPersistence().cacheResult(nmDiscountProjection);
	}

	/**
	* Caches the nm discount projections in the entity cache if it is enabled.
	*
	* @param nmDiscountProjections the nm discount projections
	*/
	public static void cacheResult(
		List<NmDiscountProjection> nmDiscountProjections) {
		getPersistence().cacheResult(nmDiscountProjections);
	}

	/**
	* Creates a new nm discount projection with the primary key. Does not add the nm discount projection to the database.
	*
	* @param nmDiscountProjectionPK the primary key for the new nm discount projection
	* @return the new nm discount projection
	*/
	public static NmDiscountProjection create(
		NmDiscountProjectionPK nmDiscountProjectionPK) {
		return getPersistence().create(nmDiscountProjectionPK);
	}

	/**
	* Removes the nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmDiscountProjectionPK the primary key of the nm discount projection
	* @return the nm discount projection that was removed
	* @throws NoSuchNmDiscountProjectionException if a nm discount projection with the primary key could not be found
	*/
	public static NmDiscountProjection remove(
		NmDiscountProjectionPK nmDiscountProjectionPK)
		throws com.stpl.app.exception.NoSuchNmDiscountProjectionException {
		return getPersistence().remove(nmDiscountProjectionPK);
	}

	public static NmDiscountProjection updateImpl(
		NmDiscountProjection nmDiscountProjection) {
		return getPersistence().updateImpl(nmDiscountProjection);
	}

	/**
	* Returns the nm discount projection with the primary key or throws a {@link NoSuchNmDiscountProjectionException} if it could not be found.
	*
	* @param nmDiscountProjectionPK the primary key of the nm discount projection
	* @return the nm discount projection
	* @throws NoSuchNmDiscountProjectionException if a nm discount projection with the primary key could not be found
	*/
	public static NmDiscountProjection findByPrimaryKey(
		NmDiscountProjectionPK nmDiscountProjectionPK)
		throws com.stpl.app.exception.NoSuchNmDiscountProjectionException {
		return getPersistence().findByPrimaryKey(nmDiscountProjectionPK);
	}

	/**
	* Returns the nm discount projection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nmDiscountProjectionPK the primary key of the nm discount projection
	* @return the nm discount projection, or <code>null</code> if a nm discount projection with the primary key could not be found
	*/
	public static NmDiscountProjection fetchByPrimaryKey(
		NmDiscountProjectionPK nmDiscountProjectionPK) {
		return getPersistence().fetchByPrimaryKey(nmDiscountProjectionPK);
	}

	public static java.util.Map<java.io.Serializable, NmDiscountProjection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the nm discount projections.
	*
	* @return the nm discount projections
	*/
	public static List<NmDiscountProjection> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the nm discount projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm discount projections
	* @param end the upper bound of the range of nm discount projections (not inclusive)
	* @return the range of nm discount projections
	*/
	public static List<NmDiscountProjection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the nm discount projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm discount projections
	* @param end the upper bound of the range of nm discount projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm discount projections
	*/
	public static List<NmDiscountProjection> findAll(int start, int end,
		OrderByComparator<NmDiscountProjection> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the nm discount projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm discount projections
	* @param end the upper bound of the range of nm discount projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm discount projections
	*/
	public static List<NmDiscountProjection> findAll(int start, int end,
		OrderByComparator<NmDiscountProjection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the nm discount projections from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of nm discount projections.
	*
	* @return the number of nm discount projections
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NmDiscountProjectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NmDiscountProjectionPersistence, NmDiscountProjectionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NmDiscountProjectionPersistence.class);
}