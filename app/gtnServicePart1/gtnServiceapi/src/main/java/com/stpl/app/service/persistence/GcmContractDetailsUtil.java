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

import com.stpl.app.model.GcmContractDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the gcm contract details service. This utility wraps {@link com.stpl.app.service.persistence.impl.GcmContractDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmContractDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.GcmContractDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class GcmContractDetailsUtil {
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
	public static void clearCache(GcmContractDetails gcmContractDetails) {
		getPersistence().clearCache(gcmContractDetails);
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
	public static List<GcmContractDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GcmContractDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GcmContractDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<GcmContractDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GcmContractDetails update(
		GcmContractDetails gcmContractDetails) {
		return getPersistence().update(gcmContractDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GcmContractDetails update(
		GcmContractDetails gcmContractDetails, ServiceContext serviceContext) {
		return getPersistence().update(gcmContractDetails, serviceContext);
	}

	/**
	* Caches the gcm contract details in the entity cache if it is enabled.
	*
	* @param gcmContractDetails the gcm contract details
	*/
	public static void cacheResult(GcmContractDetails gcmContractDetails) {
		getPersistence().cacheResult(gcmContractDetails);
	}

	/**
	* Caches the gcm contract detailses in the entity cache if it is enabled.
	*
	* @param gcmContractDetailses the gcm contract detailses
	*/
	public static void cacheResult(
		List<GcmContractDetails> gcmContractDetailses) {
		getPersistence().cacheResult(gcmContractDetailses);
	}

	/**
	* Creates a new gcm contract details with the primary key. Does not add the gcm contract details to the database.
	*
	* @param gcmContractDetailsSid the primary key for the new gcm contract details
	* @return the new gcm contract details
	*/
	public static GcmContractDetails create(int gcmContractDetailsSid) {
		return getPersistence().create(gcmContractDetailsSid);
	}

	/**
	* Removes the gcm contract details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmContractDetailsSid the primary key of the gcm contract details
	* @return the gcm contract details that was removed
	* @throws NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
	*/
	public static GcmContractDetails remove(int gcmContractDetailsSid)
		throws com.stpl.app.exception.NoSuchGcmContractDetailsException {
		return getPersistence().remove(gcmContractDetailsSid);
	}

	public static GcmContractDetails updateImpl(
		GcmContractDetails gcmContractDetails) {
		return getPersistence().updateImpl(gcmContractDetails);
	}

	/**
	* Returns the gcm contract details with the primary key or throws a {@link NoSuchGcmContractDetailsException} if it could not be found.
	*
	* @param gcmContractDetailsSid the primary key of the gcm contract details
	* @return the gcm contract details
	* @throws NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
	*/
	public static GcmContractDetails findByPrimaryKey(int gcmContractDetailsSid)
		throws com.stpl.app.exception.NoSuchGcmContractDetailsException {
		return getPersistence().findByPrimaryKey(gcmContractDetailsSid);
	}

	/**
	* Returns the gcm contract details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gcmContractDetailsSid the primary key of the gcm contract details
	* @return the gcm contract details, or <code>null</code> if a gcm contract details with the primary key could not be found
	*/
	public static GcmContractDetails fetchByPrimaryKey(
		int gcmContractDetailsSid) {
		return getPersistence().fetchByPrimaryKey(gcmContractDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, GcmContractDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the gcm contract detailses.
	*
	* @return the gcm contract detailses
	*/
	public static List<GcmContractDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the gcm contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm contract detailses
	* @param end the upper bound of the range of gcm contract detailses (not inclusive)
	* @return the range of gcm contract detailses
	*/
	public static List<GcmContractDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the gcm contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm contract detailses
	* @param end the upper bound of the range of gcm contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gcm contract detailses
	*/
	public static List<GcmContractDetails> findAll(int start, int end,
		OrderByComparator<GcmContractDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gcm contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm contract detailses
	* @param end the upper bound of the range of gcm contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of gcm contract detailses
	*/
	public static List<GcmContractDetails> findAll(int start, int end,
		OrderByComparator<GcmContractDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the gcm contract detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of gcm contract detailses.
	*
	* @return the number of gcm contract detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static GcmContractDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<GcmContractDetailsPersistence, GcmContractDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(GcmContractDetailsPersistence.class);
}