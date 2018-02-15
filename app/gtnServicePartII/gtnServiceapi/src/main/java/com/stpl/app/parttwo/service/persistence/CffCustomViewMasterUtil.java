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

import com.stpl.app.parttwo.model.CffCustomViewMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cff custom view master service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.CffCustomViewMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffCustomViewMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.CffCustomViewMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class CffCustomViewMasterUtil {
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
	public static void clearCache(CffCustomViewMaster cffCustomViewMaster) {
		getPersistence().clearCache(cffCustomViewMaster);
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
	public static List<CffCustomViewMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CffCustomViewMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CffCustomViewMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CffCustomViewMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CffCustomViewMaster update(
		CffCustomViewMaster cffCustomViewMaster) {
		return getPersistence().update(cffCustomViewMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CffCustomViewMaster update(
		CffCustomViewMaster cffCustomViewMaster, ServiceContext serviceContext) {
		return getPersistence().update(cffCustomViewMaster, serviceContext);
	}

	/**
	* Caches the cff custom view master in the entity cache if it is enabled.
	*
	* @param cffCustomViewMaster the cff custom view master
	*/
	public static void cacheResult(CffCustomViewMaster cffCustomViewMaster) {
		getPersistence().cacheResult(cffCustomViewMaster);
	}

	/**
	* Caches the cff custom view masters in the entity cache if it is enabled.
	*
	* @param cffCustomViewMasters the cff custom view masters
	*/
	public static void cacheResult(
		List<CffCustomViewMaster> cffCustomViewMasters) {
		getPersistence().cacheResult(cffCustomViewMasters);
	}

	/**
	* Creates a new cff custom view master with the primary key. Does not add the cff custom view master to the database.
	*
	* @param cffCustomViewMasterSid the primary key for the new cff custom view master
	* @return the new cff custom view master
	*/
	public static CffCustomViewMaster create(int cffCustomViewMasterSid) {
		return getPersistence().create(cffCustomViewMasterSid);
	}

	/**
	* Removes the cff custom view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustomViewMasterSid the primary key of the cff custom view master
	* @return the cff custom view master that was removed
	* @throws NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
	*/
	public static CffCustomViewMaster remove(int cffCustomViewMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchCffCustomViewMasterException {
		return getPersistence().remove(cffCustomViewMasterSid);
	}

	public static CffCustomViewMaster updateImpl(
		CffCustomViewMaster cffCustomViewMaster) {
		return getPersistence().updateImpl(cffCustomViewMaster);
	}

	/**
	* Returns the cff custom view master with the primary key or throws a {@link NoSuchCffCustomViewMasterException} if it could not be found.
	*
	* @param cffCustomViewMasterSid the primary key of the cff custom view master
	* @return the cff custom view master
	* @throws NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
	*/
	public static CffCustomViewMaster findByPrimaryKey(
		int cffCustomViewMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchCffCustomViewMasterException {
		return getPersistence().findByPrimaryKey(cffCustomViewMasterSid);
	}

	/**
	* Returns the cff custom view master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffCustomViewMasterSid the primary key of the cff custom view master
	* @return the cff custom view master, or <code>null</code> if a cff custom view master with the primary key could not be found
	*/
	public static CffCustomViewMaster fetchByPrimaryKey(
		int cffCustomViewMasterSid) {
		return getPersistence().fetchByPrimaryKey(cffCustomViewMasterSid);
	}

	public static java.util.Map<java.io.Serializable, CffCustomViewMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cff custom view masters.
	*
	* @return the cff custom view masters
	*/
	public static List<CffCustomViewMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cff custom view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff custom view masters
	* @param end the upper bound of the range of cff custom view masters (not inclusive)
	* @return the range of cff custom view masters
	*/
	public static List<CffCustomViewMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cff custom view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff custom view masters
	* @param end the upper bound of the range of cff custom view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff custom view masters
	*/
	public static List<CffCustomViewMaster> findAll(int start, int end,
		OrderByComparator<CffCustomViewMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cff custom view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff custom view masters
	* @param end the upper bound of the range of cff custom view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff custom view masters
	*/
	public static List<CffCustomViewMaster> findAll(int start, int end,
		OrderByComparator<CffCustomViewMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cff custom view masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cff custom view masters.
	*
	* @return the number of cff custom view masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CffCustomViewMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CffCustomViewMasterPersistence, CffCustomViewMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CffCustomViewMasterPersistence.class);
}