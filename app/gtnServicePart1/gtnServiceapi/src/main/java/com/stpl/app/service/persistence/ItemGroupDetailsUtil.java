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

import com.stpl.app.model.ItemGroupDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the item group details service. This utility wraps {@link com.stpl.app.service.persistence.impl.ItemGroupDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemGroupDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.ItemGroupDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class ItemGroupDetailsUtil {
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
	public static void clearCache(ItemGroupDetails itemGroupDetails) {
		getPersistence().clearCache(itemGroupDetails);
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
	public static List<ItemGroupDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ItemGroupDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ItemGroupDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ItemGroupDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ItemGroupDetails update(ItemGroupDetails itemGroupDetails) {
		return getPersistence().update(itemGroupDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ItemGroupDetails update(ItemGroupDetails itemGroupDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(itemGroupDetails, serviceContext);
	}

	/**
	* Caches the item group details in the entity cache if it is enabled.
	*
	* @param itemGroupDetails the item group details
	*/
	public static void cacheResult(ItemGroupDetails itemGroupDetails) {
		getPersistence().cacheResult(itemGroupDetails);
	}

	/**
	* Caches the item group detailses in the entity cache if it is enabled.
	*
	* @param itemGroupDetailses the item group detailses
	*/
	public static void cacheResult(List<ItemGroupDetails> itemGroupDetailses) {
		getPersistence().cacheResult(itemGroupDetailses);
	}

	/**
	* Creates a new item group details with the primary key. Does not add the item group details to the database.
	*
	* @param itemGroupDetailsSid the primary key for the new item group details
	* @return the new item group details
	*/
	public static ItemGroupDetails create(int itemGroupDetailsSid) {
		return getPersistence().create(itemGroupDetailsSid);
	}

	/**
	* Removes the item group details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemGroupDetailsSid the primary key of the item group details
	* @return the item group details that was removed
	* @throws NoSuchItemGroupDetailsException if a item group details with the primary key could not be found
	*/
	public static ItemGroupDetails remove(int itemGroupDetailsSid)
		throws com.stpl.app.exception.NoSuchItemGroupDetailsException {
		return getPersistence().remove(itemGroupDetailsSid);
	}

	public static ItemGroupDetails updateImpl(ItemGroupDetails itemGroupDetails) {
		return getPersistence().updateImpl(itemGroupDetails);
	}

	/**
	* Returns the item group details with the primary key or throws a {@link NoSuchItemGroupDetailsException} if it could not be found.
	*
	* @param itemGroupDetailsSid the primary key of the item group details
	* @return the item group details
	* @throws NoSuchItemGroupDetailsException if a item group details with the primary key could not be found
	*/
	public static ItemGroupDetails findByPrimaryKey(int itemGroupDetailsSid)
		throws com.stpl.app.exception.NoSuchItemGroupDetailsException {
		return getPersistence().findByPrimaryKey(itemGroupDetailsSid);
	}

	/**
	* Returns the item group details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemGroupDetailsSid the primary key of the item group details
	* @return the item group details, or <code>null</code> if a item group details with the primary key could not be found
	*/
	public static ItemGroupDetails fetchByPrimaryKey(int itemGroupDetailsSid) {
		return getPersistence().fetchByPrimaryKey(itemGroupDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, ItemGroupDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the item group detailses.
	*
	* @return the item group detailses
	*/
	public static List<ItemGroupDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the item group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item group detailses
	* @param end the upper bound of the range of item group detailses (not inclusive)
	* @return the range of item group detailses
	*/
	public static List<ItemGroupDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the item group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item group detailses
	* @param end the upper bound of the range of item group detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of item group detailses
	*/
	public static List<ItemGroupDetails> findAll(int start, int end,
		OrderByComparator<ItemGroupDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the item group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of item group detailses
	* @param end the upper bound of the range of item group detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of item group detailses
	*/
	public static List<ItemGroupDetails> findAll(int start, int end,
		OrderByComparator<ItemGroupDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the item group detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of item group detailses.
	*
	* @return the number of item group detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ItemGroupDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ItemGroupDetailsPersistence, ItemGroupDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ItemGroupDetailsPersistence.class);
}