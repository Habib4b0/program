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

import com.stpl.app.model.InventoryWdActualMas;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the inventory wd actual mas service. This utility wraps {@link com.stpl.app.service.persistence.impl.InventoryWdActualMasPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see InventoryWdActualMasPersistence
 * @see com.stpl.app.service.persistence.impl.InventoryWdActualMasPersistenceImpl
 * @generated
 */
@ProviderType
public class InventoryWdActualMasUtil {
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
	public static void clearCache(InventoryWdActualMas inventoryWdActualMas) {
		getPersistence().clearCache(inventoryWdActualMas);
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
	public static List<InventoryWdActualMas> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<InventoryWdActualMas> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<InventoryWdActualMas> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<InventoryWdActualMas> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static InventoryWdActualMas update(
		InventoryWdActualMas inventoryWdActualMas) {
		return getPersistence().update(inventoryWdActualMas);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static InventoryWdActualMas update(
		InventoryWdActualMas inventoryWdActualMas, ServiceContext serviceContext) {
		return getPersistence().update(inventoryWdActualMas, serviceContext);
	}

	/**
	* Caches the inventory wd actual mas in the entity cache if it is enabled.
	*
	* @param inventoryWdActualMas the inventory wd actual mas
	*/
	public static void cacheResult(InventoryWdActualMas inventoryWdActualMas) {
		getPersistence().cacheResult(inventoryWdActualMas);
	}

	/**
	* Caches the inventory wd actual mases in the entity cache if it is enabled.
	*
	* @param inventoryWdActualMases the inventory wd actual mases
	*/
	public static void cacheResult(
		List<InventoryWdActualMas> inventoryWdActualMases) {
		getPersistence().cacheResult(inventoryWdActualMases);
	}

	/**
	* Creates a new inventory wd actual mas with the primary key. Does not add the inventory wd actual mas to the database.
	*
	* @param inventoryWdActualMasSid the primary key for the new inventory wd actual mas
	* @return the new inventory wd actual mas
	*/
	public static InventoryWdActualMas create(int inventoryWdActualMasSid) {
		return getPersistence().create(inventoryWdActualMasSid);
	}

	/**
	* Removes the inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
	* @return the inventory wd actual mas that was removed
	* @throws NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
	*/
	public static InventoryWdActualMas remove(int inventoryWdActualMasSid)
		throws com.stpl.app.exception.NoSuchInventoryWdActualMasException {
		return getPersistence().remove(inventoryWdActualMasSid);
	}

	public static InventoryWdActualMas updateImpl(
		InventoryWdActualMas inventoryWdActualMas) {
		return getPersistence().updateImpl(inventoryWdActualMas);
	}

	/**
	* Returns the inventory wd actual mas with the primary key or throws a {@link NoSuchInventoryWdActualMasException} if it could not be found.
	*
	* @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
	* @return the inventory wd actual mas
	* @throws NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
	*/
	public static InventoryWdActualMas findByPrimaryKey(
		int inventoryWdActualMasSid)
		throws com.stpl.app.exception.NoSuchInventoryWdActualMasException {
		return getPersistence().findByPrimaryKey(inventoryWdActualMasSid);
	}

	/**
	* Returns the inventory wd actual mas with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
	* @return the inventory wd actual mas, or <code>null</code> if a inventory wd actual mas with the primary key could not be found
	*/
	public static InventoryWdActualMas fetchByPrimaryKey(
		int inventoryWdActualMasSid) {
		return getPersistence().fetchByPrimaryKey(inventoryWdActualMasSid);
	}

	public static java.util.Map<java.io.Serializable, InventoryWdActualMas> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the inventory wd actual mases.
	*
	* @return the inventory wd actual mases
	*/
	public static List<InventoryWdActualMas> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the inventory wd actual mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd actual mases
	* @param end the upper bound of the range of inventory wd actual mases (not inclusive)
	* @return the range of inventory wd actual mases
	*/
	public static List<InventoryWdActualMas> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the inventory wd actual mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd actual mases
	* @param end the upper bound of the range of inventory wd actual mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of inventory wd actual mases
	*/
	public static List<InventoryWdActualMas> findAll(int start, int end,
		OrderByComparator<InventoryWdActualMas> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the inventory wd actual mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd actual mases
	* @param end the upper bound of the range of inventory wd actual mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of inventory wd actual mases
	*/
	public static List<InventoryWdActualMas> findAll(int start, int end,
		OrderByComparator<InventoryWdActualMas> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the inventory wd actual mases from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of inventory wd actual mases.
	*
	* @return the number of inventory wd actual mases
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static InventoryWdActualMasPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<InventoryWdActualMasPersistence, InventoryWdActualMasPersistence> _serviceTracker =
		ServiceTrackerFactory.open(InventoryWdActualMasPersistence.class);
}