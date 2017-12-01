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

import com.stpl.app.model.InventoryWdProjMas;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the inventory wd proj mas service. This utility wraps {@link com.stpl.app.service.persistence.impl.InventoryWdProjMasPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see InventoryWdProjMasPersistence
 * @see com.stpl.app.service.persistence.impl.InventoryWdProjMasPersistenceImpl
 * @generated
 */
@ProviderType
public class InventoryWdProjMasUtil {
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
	public static void clearCache(InventoryWdProjMas inventoryWdProjMas) {
		getPersistence().clearCache(inventoryWdProjMas);
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
	public static List<InventoryWdProjMas> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<InventoryWdProjMas> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<InventoryWdProjMas> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<InventoryWdProjMas> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static InventoryWdProjMas update(
		InventoryWdProjMas inventoryWdProjMas) {
		return getPersistence().update(inventoryWdProjMas);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static InventoryWdProjMas update(
		InventoryWdProjMas inventoryWdProjMas, ServiceContext serviceContext) {
		return getPersistence().update(inventoryWdProjMas, serviceContext);
	}

	/**
	* Caches the inventory wd proj mas in the entity cache if it is enabled.
	*
	* @param inventoryWdProjMas the inventory wd proj mas
	*/
	public static void cacheResult(InventoryWdProjMas inventoryWdProjMas) {
		getPersistence().cacheResult(inventoryWdProjMas);
	}

	/**
	* Caches the inventory wd proj mases in the entity cache if it is enabled.
	*
	* @param inventoryWdProjMases the inventory wd proj mases
	*/
	public static void cacheResult(
		List<InventoryWdProjMas> inventoryWdProjMases) {
		getPersistence().cacheResult(inventoryWdProjMases);
	}

	/**
	* Creates a new inventory wd proj mas with the primary key. Does not add the inventory wd proj mas to the database.
	*
	* @param inventoryWdProjMasSid the primary key for the new inventory wd proj mas
	* @return the new inventory wd proj mas
	*/
	public static InventoryWdProjMas create(int inventoryWdProjMasSid) {
		return getPersistence().create(inventoryWdProjMasSid);
	}

	/**
	* Removes the inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
	* @return the inventory wd proj mas that was removed
	* @throws NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
	*/
	public static InventoryWdProjMas remove(int inventoryWdProjMasSid)
		throws com.stpl.app.exception.NoSuchInventoryWdProjMasException {
		return getPersistence().remove(inventoryWdProjMasSid);
	}

	public static InventoryWdProjMas updateImpl(
		InventoryWdProjMas inventoryWdProjMas) {
		return getPersistence().updateImpl(inventoryWdProjMas);
	}

	/**
	* Returns the inventory wd proj mas with the primary key or throws a {@link NoSuchInventoryWdProjMasException} if it could not be found.
	*
	* @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
	* @return the inventory wd proj mas
	* @throws NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
	*/
	public static InventoryWdProjMas findByPrimaryKey(int inventoryWdProjMasSid)
		throws com.stpl.app.exception.NoSuchInventoryWdProjMasException {
		return getPersistence().findByPrimaryKey(inventoryWdProjMasSid);
	}

	/**
	* Returns the inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
	* @return the inventory wd proj mas, or <code>null</code> if a inventory wd proj mas with the primary key could not be found
	*/
	public static InventoryWdProjMas fetchByPrimaryKey(
		int inventoryWdProjMasSid) {
		return getPersistence().fetchByPrimaryKey(inventoryWdProjMasSid);
	}

	public static java.util.Map<java.io.Serializable, InventoryWdProjMas> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the inventory wd proj mases.
	*
	* @return the inventory wd proj mases
	*/
	public static List<InventoryWdProjMas> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the inventory wd proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd proj mases
	* @param end the upper bound of the range of inventory wd proj mases (not inclusive)
	* @return the range of inventory wd proj mases
	*/
	public static List<InventoryWdProjMas> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the inventory wd proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd proj mases
	* @param end the upper bound of the range of inventory wd proj mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of inventory wd proj mases
	*/
	public static List<InventoryWdProjMas> findAll(int start, int end,
		OrderByComparator<InventoryWdProjMas> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the inventory wd proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd proj mases
	* @param end the upper bound of the range of inventory wd proj mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of inventory wd proj mases
	*/
	public static List<InventoryWdProjMas> findAll(int start, int end,
		OrderByComparator<InventoryWdProjMas> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the inventory wd proj mases from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of inventory wd proj mases.
	*
	* @return the number of inventory wd proj mases
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static InventoryWdProjMasPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<InventoryWdProjMasPersistence, InventoryWdProjMasPersistence> _serviceTracker =
		ServiceTrackerFactory.open(InventoryWdProjMasPersistence.class);
}