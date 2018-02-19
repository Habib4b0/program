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

import com.stpl.app.model.IvldInventoryWdActualMas;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld inventory wd actual mas service. This utility wraps {@link com.stpl.app.service.persistence.impl.IvldInventoryWdActualMasPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldInventoryWdActualMasPersistence
 * @see com.stpl.app.service.persistence.impl.IvldInventoryWdActualMasPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldInventoryWdActualMasUtil {
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
		IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		getPersistence().clearCache(ivldInventoryWdActualMas);
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
	public static List<IvldInventoryWdActualMas> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldInventoryWdActualMas> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldInventoryWdActualMas> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldInventoryWdActualMas> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldInventoryWdActualMas update(
		IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		return getPersistence().update(ivldInventoryWdActualMas);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldInventoryWdActualMas update(
		IvldInventoryWdActualMas ivldInventoryWdActualMas,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldInventoryWdActualMas, serviceContext);
	}

	/**
	* Caches the ivld inventory wd actual mas in the entity cache if it is enabled.
	*
	* @param ivldInventoryWdActualMas the ivld inventory wd actual mas
	*/
	public static void cacheResult(
		IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		getPersistence().cacheResult(ivldInventoryWdActualMas);
	}

	/**
	* Caches the ivld inventory wd actual mases in the entity cache if it is enabled.
	*
	* @param ivldInventoryWdActualMases the ivld inventory wd actual mases
	*/
	public static void cacheResult(
		List<IvldInventoryWdActualMas> ivldInventoryWdActualMases) {
		getPersistence().cacheResult(ivldInventoryWdActualMases);
	}

	/**
	* Creates a new ivld inventory wd actual mas with the primary key. Does not add the ivld inventory wd actual mas to the database.
	*
	* @param ivldInventoryWdActualMasSid the primary key for the new ivld inventory wd actual mas
	* @return the new ivld inventory wd actual mas
	*/
	public static IvldInventoryWdActualMas create(
		int ivldInventoryWdActualMasSid) {
		return getPersistence().create(ivldInventoryWdActualMasSid);
	}

	/**
	* Removes the ivld inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldInventoryWdActualMasSid the primary key of the ivld inventory wd actual mas
	* @return the ivld inventory wd actual mas that was removed
	* @throws NoSuchIvldInventoryWdActualMasException if a ivld inventory wd actual mas with the primary key could not be found
	*/
	public static IvldInventoryWdActualMas remove(
		int ivldInventoryWdActualMasSid)
		throws com.stpl.app.exception.NoSuchIvldInventoryWdActualMasException {
		return getPersistence().remove(ivldInventoryWdActualMasSid);
	}

	public static IvldInventoryWdActualMas updateImpl(
		IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		return getPersistence().updateImpl(ivldInventoryWdActualMas);
	}

	/**
	* Returns the ivld inventory wd actual mas with the primary key or throws a {@link NoSuchIvldInventoryWdActualMasException} if it could not be found.
	*
	* @param ivldInventoryWdActualMasSid the primary key of the ivld inventory wd actual mas
	* @return the ivld inventory wd actual mas
	* @throws NoSuchIvldInventoryWdActualMasException if a ivld inventory wd actual mas with the primary key could not be found
	*/
	public static IvldInventoryWdActualMas findByPrimaryKey(
		int ivldInventoryWdActualMasSid)
		throws com.stpl.app.exception.NoSuchIvldInventoryWdActualMasException {
		return getPersistence().findByPrimaryKey(ivldInventoryWdActualMasSid);
	}

	/**
	* Returns the ivld inventory wd actual mas with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldInventoryWdActualMasSid the primary key of the ivld inventory wd actual mas
	* @return the ivld inventory wd actual mas, or <code>null</code> if a ivld inventory wd actual mas with the primary key could not be found
	*/
	public static IvldInventoryWdActualMas fetchByPrimaryKey(
		int ivldInventoryWdActualMasSid) {
		return getPersistence().fetchByPrimaryKey(ivldInventoryWdActualMasSid);
	}

	public static java.util.Map<java.io.Serializable, IvldInventoryWdActualMas> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld inventory wd actual mases.
	*
	* @return the ivld inventory wd actual mases
	*/
	public static List<IvldInventoryWdActualMas> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld inventory wd actual mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldInventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld inventory wd actual mases
	* @param end the upper bound of the range of ivld inventory wd actual mases (not inclusive)
	* @return the range of ivld inventory wd actual mases
	*/
	public static List<IvldInventoryWdActualMas> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld inventory wd actual mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldInventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld inventory wd actual mases
	* @param end the upper bound of the range of ivld inventory wd actual mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld inventory wd actual mases
	*/
	public static List<IvldInventoryWdActualMas> findAll(int start, int end,
		OrderByComparator<IvldInventoryWdActualMas> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld inventory wd actual mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldInventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld inventory wd actual mases
	* @param end the upper bound of the range of ivld inventory wd actual mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld inventory wd actual mases
	*/
	public static List<IvldInventoryWdActualMas> findAll(int start, int end,
		OrderByComparator<IvldInventoryWdActualMas> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld inventory wd actual mases from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld inventory wd actual mases.
	*
	* @return the number of ivld inventory wd actual mases
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldInventoryWdActualMasPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldInventoryWdActualMasPersistence, IvldInventoryWdActualMasPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldInventoryWdActualMasPersistence.class);
}