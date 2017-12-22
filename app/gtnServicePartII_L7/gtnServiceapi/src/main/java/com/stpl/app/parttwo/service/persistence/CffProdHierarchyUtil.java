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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.parttwo.model.CffProdHierarchy;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cff prod hierarchy service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.CffProdHierarchyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffProdHierarchyPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.CffProdHierarchyPersistenceImpl
 * @generated
 */
@ProviderType
public class CffProdHierarchyUtil {
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
	public static void clearCache(CffProdHierarchy cffProdHierarchy) {
		getPersistence().clearCache(cffProdHierarchy);
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
	public static List<CffProdHierarchy> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CffProdHierarchy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CffProdHierarchy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CffProdHierarchy> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CffProdHierarchy update(CffProdHierarchy cffProdHierarchy) {
		return getPersistence().update(cffProdHierarchy);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CffProdHierarchy update(CffProdHierarchy cffProdHierarchy,
		ServiceContext serviceContext) {
		return getPersistence().update(cffProdHierarchy, serviceContext);
	}

	/**
	* Caches the cff prod hierarchy in the entity cache if it is enabled.
	*
	* @param cffProdHierarchy the cff prod hierarchy
	*/
	public static void cacheResult(CffProdHierarchy cffProdHierarchy) {
		getPersistence().cacheResult(cffProdHierarchy);
	}

	/**
	* Caches the cff prod hierarchies in the entity cache if it is enabled.
	*
	* @param cffProdHierarchies the cff prod hierarchies
	*/
	public static void cacheResult(List<CffProdHierarchy> cffProdHierarchies) {
		getPersistence().cacheResult(cffProdHierarchies);
	}

	/**
	* Creates a new cff prod hierarchy with the primary key. Does not add the cff prod hierarchy to the database.
	*
	* @param cffProdHierarchySid the primary key for the new cff prod hierarchy
	* @return the new cff prod hierarchy
	*/
	public static CffProdHierarchy create(int cffProdHierarchySid) {
		return getPersistence().create(cffProdHierarchySid);
	}

	/**
	* Removes the cff prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffProdHierarchySid the primary key of the cff prod hierarchy
	* @return the cff prod hierarchy that was removed
	* @throws NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
	*/
	public static CffProdHierarchy remove(int cffProdHierarchySid)
		throws com.stpl.app.parttwo.exception.NoSuchCffProdHierarchyException {
		return getPersistence().remove(cffProdHierarchySid);
	}

	public static CffProdHierarchy updateImpl(CffProdHierarchy cffProdHierarchy) {
		return getPersistence().updateImpl(cffProdHierarchy);
	}

	/**
	* Returns the cff prod hierarchy with the primary key or throws a {@link NoSuchCffProdHierarchyException} if it could not be found.
	*
	* @param cffProdHierarchySid the primary key of the cff prod hierarchy
	* @return the cff prod hierarchy
	* @throws NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
	*/
	public static CffProdHierarchy findByPrimaryKey(int cffProdHierarchySid)
		throws com.stpl.app.parttwo.exception.NoSuchCffProdHierarchyException {
		return getPersistence().findByPrimaryKey(cffProdHierarchySid);
	}

	/**
	* Returns the cff prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffProdHierarchySid the primary key of the cff prod hierarchy
	* @return the cff prod hierarchy, or <code>null</code> if a cff prod hierarchy with the primary key could not be found
	*/
	public static CffProdHierarchy fetchByPrimaryKey(int cffProdHierarchySid) {
		return getPersistence().fetchByPrimaryKey(cffProdHierarchySid);
	}

	public static java.util.Map<java.io.Serializable, CffProdHierarchy> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cff prod hierarchies.
	*
	* @return the cff prod hierarchies
	*/
	public static List<CffProdHierarchy> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cff prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff prod hierarchies
	* @param end the upper bound of the range of cff prod hierarchies (not inclusive)
	* @return the range of cff prod hierarchies
	*/
	public static List<CffProdHierarchy> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cff prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff prod hierarchies
	* @param end the upper bound of the range of cff prod hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff prod hierarchies
	*/
	public static List<CffProdHierarchy> findAll(int start, int end,
		OrderByComparator<CffProdHierarchy> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cff prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff prod hierarchies
	* @param end the upper bound of the range of cff prod hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff prod hierarchies
	*/
	public static List<CffProdHierarchy> findAll(int start, int end,
		OrderByComparator<CffProdHierarchy> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cff prod hierarchies from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cff prod hierarchies.
	*
	* @return the number of cff prod hierarchies
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CffProdHierarchyPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CffProdHierarchyPersistence, CffProdHierarchyPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CffProdHierarchyPersistence.class);
}