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

import com.stpl.app.model.VwInventoryWdActualProjMas;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw inventory wd actual proj mas service. This utility wraps {@link com.stpl.app.service.persistence.impl.VwInventoryWdActualProjMasPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwInventoryWdActualProjMasPersistence
 * @see com.stpl.app.service.persistence.impl.VwInventoryWdActualProjMasPersistenceImpl
 * @generated
 */
@ProviderType
public class VwInventoryWdActualProjMasUtil {
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
	public static void clearCache(
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		getPersistence().clearCache(vwInventoryWdActualProjMas);
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
	public static List<VwInventoryWdActualProjMas> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwInventoryWdActualProjMas> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwInventoryWdActualProjMas> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwInventoryWdActualProjMas> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwInventoryWdActualProjMas update(
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		return getPersistence().update(vwInventoryWdActualProjMas);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwInventoryWdActualProjMas update(
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(vwInventoryWdActualProjMas, serviceContext);
	}

	/**
	* Caches the vw inventory wd actual proj mas in the entity cache if it is enabled.
	*
	* @param vwInventoryWdActualProjMas the vw inventory wd actual proj mas
	*/
	public static void cacheResult(
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		getPersistence().cacheResult(vwInventoryWdActualProjMas);
	}

	/**
	* Caches the vw inventory wd actual proj mases in the entity cache if it is enabled.
	*
	* @param vwInventoryWdActualProjMases the vw inventory wd actual proj mases
	*/
	public static void cacheResult(
		List<VwInventoryWdActualProjMas> vwInventoryWdActualProjMases) {
		getPersistence().cacheResult(vwInventoryWdActualProjMases);
	}

	/**
	* Creates a new vw inventory wd actual proj mas with the primary key. Does not add the vw inventory wd actual proj mas to the database.
	*
	* @param inventoryWdActualProjMasSid the primary key for the new vw inventory wd actual proj mas
	* @return the new vw inventory wd actual proj mas
	*/
	public static VwInventoryWdActualProjMas create(
		int inventoryWdActualProjMasSid) {
		return getPersistence().create(inventoryWdActualProjMasSid);
	}

	/**
	* Removes the vw inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
	* @return the vw inventory wd actual proj mas that was removed
	* @throws NoSuchVwInventoryWdActualProjMasException if a vw inventory wd actual proj mas with the primary key could not be found
	*/
	public static VwInventoryWdActualProjMas remove(
		int inventoryWdActualProjMasSid)
		throws com.stpl.app.exception.NoSuchVwInventoryWdActualProjMasException {
		return getPersistence().remove(inventoryWdActualProjMasSid);
	}

	public static VwInventoryWdActualProjMas updateImpl(
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		return getPersistence().updateImpl(vwInventoryWdActualProjMas);
	}

	/**
	* Returns the vw inventory wd actual proj mas with the primary key or throws a {@link NoSuchVwInventoryWdActualProjMasException} if it could not be found.
	*
	* @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
	* @return the vw inventory wd actual proj mas
	* @throws NoSuchVwInventoryWdActualProjMasException if a vw inventory wd actual proj mas with the primary key could not be found
	*/
	public static VwInventoryWdActualProjMas findByPrimaryKey(
		int inventoryWdActualProjMasSid)
		throws com.stpl.app.exception.NoSuchVwInventoryWdActualProjMasException {
		return getPersistence().findByPrimaryKey(inventoryWdActualProjMasSid);
	}

	/**
	* Returns the vw inventory wd actual proj mas with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
	* @return the vw inventory wd actual proj mas, or <code>null</code> if a vw inventory wd actual proj mas with the primary key could not be found
	*/
	public static VwInventoryWdActualProjMas fetchByPrimaryKey(
		int inventoryWdActualProjMasSid) {
		return getPersistence().fetchByPrimaryKey(inventoryWdActualProjMasSid);
	}

	public static java.util.Map<java.io.Serializable, VwInventoryWdActualProjMas> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw inventory wd actual proj mases.
	*
	* @return the vw inventory wd actual proj mases
	*/
	public static List<VwInventoryWdActualProjMas> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw inventory wd actual proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw inventory wd actual proj mases
	* @param end the upper bound of the range of vw inventory wd actual proj mases (not inclusive)
	* @return the range of vw inventory wd actual proj mases
	*/
	public static List<VwInventoryWdActualProjMas> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw inventory wd actual proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw inventory wd actual proj mases
	* @param end the upper bound of the range of vw inventory wd actual proj mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw inventory wd actual proj mases
	*/
	public static List<VwInventoryWdActualProjMas> findAll(int start, int end,
		OrderByComparator<VwInventoryWdActualProjMas> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw inventory wd actual proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw inventory wd actual proj mases
	* @param end the upper bound of the range of vw inventory wd actual proj mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw inventory wd actual proj mases
	*/
	public static List<VwInventoryWdActualProjMas> findAll(int start, int end,
		OrderByComparator<VwInventoryWdActualProjMas> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw inventory wd actual proj mases from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw inventory wd actual proj mases.
	*
	* @return the number of vw inventory wd actual proj mases
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwInventoryWdActualProjMasPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwInventoryWdActualProjMasPersistence, VwInventoryWdActualProjMasPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwInventoryWdActualProjMasPersistence.class);
}