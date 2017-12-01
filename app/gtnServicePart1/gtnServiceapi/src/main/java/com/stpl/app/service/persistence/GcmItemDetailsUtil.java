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

import com.stpl.app.model.GcmItemDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the gcm item details service. This utility wraps {@link com.stpl.app.service.persistence.impl.GcmItemDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmItemDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.GcmItemDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class GcmItemDetailsUtil {
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
	public static void clearCache(GcmItemDetails gcmItemDetails) {
		getPersistence().clearCache(gcmItemDetails);
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
	public static List<GcmItemDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GcmItemDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GcmItemDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<GcmItemDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GcmItemDetails update(GcmItemDetails gcmItemDetails) {
		return getPersistence().update(gcmItemDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GcmItemDetails update(GcmItemDetails gcmItemDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(gcmItemDetails, serviceContext);
	}

	/**
	* Caches the gcm item details in the entity cache if it is enabled.
	*
	* @param gcmItemDetails the gcm item details
	*/
	public static void cacheResult(GcmItemDetails gcmItemDetails) {
		getPersistence().cacheResult(gcmItemDetails);
	}

	/**
	* Caches the gcm item detailses in the entity cache if it is enabled.
	*
	* @param gcmItemDetailses the gcm item detailses
	*/
	public static void cacheResult(List<GcmItemDetails> gcmItemDetailses) {
		getPersistence().cacheResult(gcmItemDetailses);
	}

	/**
	* Creates a new gcm item details with the primary key. Does not add the gcm item details to the database.
	*
	* @param gcmItemDetailsSid the primary key for the new gcm item details
	* @return the new gcm item details
	*/
	public static GcmItemDetails create(int gcmItemDetailsSid) {
		return getPersistence().create(gcmItemDetailsSid);
	}

	/**
	* Removes the gcm item details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmItemDetailsSid the primary key of the gcm item details
	* @return the gcm item details that was removed
	* @throws NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
	*/
	public static GcmItemDetails remove(int gcmItemDetailsSid)
		throws com.stpl.app.exception.NoSuchGcmItemDetailsException {
		return getPersistence().remove(gcmItemDetailsSid);
	}

	public static GcmItemDetails updateImpl(GcmItemDetails gcmItemDetails) {
		return getPersistence().updateImpl(gcmItemDetails);
	}

	/**
	* Returns the gcm item details with the primary key or throws a {@link NoSuchGcmItemDetailsException} if it could not be found.
	*
	* @param gcmItemDetailsSid the primary key of the gcm item details
	* @return the gcm item details
	* @throws NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
	*/
	public static GcmItemDetails findByPrimaryKey(int gcmItemDetailsSid)
		throws com.stpl.app.exception.NoSuchGcmItemDetailsException {
		return getPersistence().findByPrimaryKey(gcmItemDetailsSid);
	}

	/**
	* Returns the gcm item details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gcmItemDetailsSid the primary key of the gcm item details
	* @return the gcm item details, or <code>null</code> if a gcm item details with the primary key could not be found
	*/
	public static GcmItemDetails fetchByPrimaryKey(int gcmItemDetailsSid) {
		return getPersistence().fetchByPrimaryKey(gcmItemDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, GcmItemDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the gcm item detailses.
	*
	* @return the gcm item detailses
	*/
	public static List<GcmItemDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the gcm item detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm item detailses
	* @param end the upper bound of the range of gcm item detailses (not inclusive)
	* @return the range of gcm item detailses
	*/
	public static List<GcmItemDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the gcm item detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm item detailses
	* @param end the upper bound of the range of gcm item detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gcm item detailses
	*/
	public static List<GcmItemDetails> findAll(int start, int end,
		OrderByComparator<GcmItemDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gcm item detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm item detailses
	* @param end the upper bound of the range of gcm item detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of gcm item detailses
	*/
	public static List<GcmItemDetails> findAll(int start, int end,
		OrderByComparator<GcmItemDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the gcm item detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of gcm item detailses.
	*
	* @return the number of gcm item detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static GcmItemDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<GcmItemDetailsPersistence, GcmItemDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(GcmItemDetailsPersistence.class);
}