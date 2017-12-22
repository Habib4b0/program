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

import com.stpl.app.parttwo.model.CffCustHierarchy;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cff cust hierarchy service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.CffCustHierarchyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffCustHierarchyPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.CffCustHierarchyPersistenceImpl
 * @generated
 */
@ProviderType
public class CffCustHierarchyUtil {
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
	public static void clearCache(CffCustHierarchy cffCustHierarchy) {
		getPersistence().clearCache(cffCustHierarchy);
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
	public static List<CffCustHierarchy> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CffCustHierarchy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CffCustHierarchy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CffCustHierarchy> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CffCustHierarchy update(CffCustHierarchy cffCustHierarchy) {
		return getPersistence().update(cffCustHierarchy);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CffCustHierarchy update(CffCustHierarchy cffCustHierarchy,
		ServiceContext serviceContext) {
		return getPersistence().update(cffCustHierarchy, serviceContext);
	}

	/**
	* Caches the cff cust hierarchy in the entity cache if it is enabled.
	*
	* @param cffCustHierarchy the cff cust hierarchy
	*/
	public static void cacheResult(CffCustHierarchy cffCustHierarchy) {
		getPersistence().cacheResult(cffCustHierarchy);
	}

	/**
	* Caches the cff cust hierarchies in the entity cache if it is enabled.
	*
	* @param cffCustHierarchies the cff cust hierarchies
	*/
	public static void cacheResult(List<CffCustHierarchy> cffCustHierarchies) {
		getPersistence().cacheResult(cffCustHierarchies);
	}

	/**
	* Creates a new cff cust hierarchy with the primary key. Does not add the cff cust hierarchy to the database.
	*
	* @param cffCustHierarchySid the primary key for the new cff cust hierarchy
	* @return the new cff cust hierarchy
	*/
	public static CffCustHierarchy create(int cffCustHierarchySid) {
		return getPersistence().create(cffCustHierarchySid);
	}

	/**
	* Removes the cff cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustHierarchySid the primary key of the cff cust hierarchy
	* @return the cff cust hierarchy that was removed
	* @throws NoSuchCffCustHierarchyException if a cff cust hierarchy with the primary key could not be found
	*/
	public static CffCustHierarchy remove(int cffCustHierarchySid)
		throws com.stpl.app.parttwo.exception.NoSuchCffCustHierarchyException {
		return getPersistence().remove(cffCustHierarchySid);
	}

	public static CffCustHierarchy updateImpl(CffCustHierarchy cffCustHierarchy) {
		return getPersistence().updateImpl(cffCustHierarchy);
	}

	/**
	* Returns the cff cust hierarchy with the primary key or throws a {@link NoSuchCffCustHierarchyException} if it could not be found.
	*
	* @param cffCustHierarchySid the primary key of the cff cust hierarchy
	* @return the cff cust hierarchy
	* @throws NoSuchCffCustHierarchyException if a cff cust hierarchy with the primary key could not be found
	*/
	public static CffCustHierarchy findByPrimaryKey(int cffCustHierarchySid)
		throws com.stpl.app.parttwo.exception.NoSuchCffCustHierarchyException {
		return getPersistence().findByPrimaryKey(cffCustHierarchySid);
	}

	/**
	* Returns the cff cust hierarchy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffCustHierarchySid the primary key of the cff cust hierarchy
	* @return the cff cust hierarchy, or <code>null</code> if a cff cust hierarchy with the primary key could not be found
	*/
	public static CffCustHierarchy fetchByPrimaryKey(int cffCustHierarchySid) {
		return getPersistence().fetchByPrimaryKey(cffCustHierarchySid);
	}

	public static java.util.Map<java.io.Serializable, CffCustHierarchy> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cff cust hierarchies.
	*
	* @return the cff cust hierarchies
	*/
	public static List<CffCustHierarchy> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cff cust hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff cust hierarchies
	* @param end the upper bound of the range of cff cust hierarchies (not inclusive)
	* @return the range of cff cust hierarchies
	*/
	public static List<CffCustHierarchy> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cff cust hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff cust hierarchies
	* @param end the upper bound of the range of cff cust hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff cust hierarchies
	*/
	public static List<CffCustHierarchy> findAll(int start, int end,
		OrderByComparator<CffCustHierarchy> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cff cust hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff cust hierarchies
	* @param end the upper bound of the range of cff cust hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff cust hierarchies
	*/
	public static List<CffCustHierarchy> findAll(int start, int end,
		OrderByComparator<CffCustHierarchy> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cff cust hierarchies from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cff cust hierarchies.
	*
	* @return the number of cff cust hierarchies
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CffCustHierarchyPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CffCustHierarchyPersistence, CffCustHierarchyPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CffCustHierarchyPersistence.class);
}