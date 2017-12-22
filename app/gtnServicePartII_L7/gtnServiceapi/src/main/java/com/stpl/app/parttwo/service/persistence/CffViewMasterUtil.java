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

import com.stpl.app.parttwo.model.CffViewMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cff view master service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.CffViewMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffViewMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.CffViewMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class CffViewMasterUtil {
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
	public static void clearCache(CffViewMaster cffViewMaster) {
		getPersistence().clearCache(cffViewMaster);
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
	public static List<CffViewMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CffViewMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CffViewMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CffViewMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CffViewMaster update(CffViewMaster cffViewMaster) {
		return getPersistence().update(cffViewMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CffViewMaster update(CffViewMaster cffViewMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(cffViewMaster, serviceContext);
	}

	/**
	* Caches the cff view master in the entity cache if it is enabled.
	*
	* @param cffViewMaster the cff view master
	*/
	public static void cacheResult(CffViewMaster cffViewMaster) {
		getPersistence().cacheResult(cffViewMaster);
	}

	/**
	* Caches the cff view masters in the entity cache if it is enabled.
	*
	* @param cffViewMasters the cff view masters
	*/
	public static void cacheResult(List<CffViewMaster> cffViewMasters) {
		getPersistence().cacheResult(cffViewMasters);
	}

	/**
	* Creates a new cff view master with the primary key. Does not add the cff view master to the database.
	*
	* @param cffViewMasterSid the primary key for the new cff view master
	* @return the new cff view master
	*/
	public static CffViewMaster create(int cffViewMasterSid) {
		return getPersistence().create(cffViewMasterSid);
	}

	/**
	* Removes the cff view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffViewMasterSid the primary key of the cff view master
	* @return the cff view master that was removed
	* @throws NoSuchCffViewMasterException if a cff view master with the primary key could not be found
	*/
	public static CffViewMaster remove(int cffViewMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchCffViewMasterException {
		return getPersistence().remove(cffViewMasterSid);
	}

	public static CffViewMaster updateImpl(CffViewMaster cffViewMaster) {
		return getPersistence().updateImpl(cffViewMaster);
	}

	/**
	* Returns the cff view master with the primary key or throws a {@link NoSuchCffViewMasterException} if it could not be found.
	*
	* @param cffViewMasterSid the primary key of the cff view master
	* @return the cff view master
	* @throws NoSuchCffViewMasterException if a cff view master with the primary key could not be found
	*/
	public static CffViewMaster findByPrimaryKey(int cffViewMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchCffViewMasterException {
		return getPersistence().findByPrimaryKey(cffViewMasterSid);
	}

	/**
	* Returns the cff view master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffViewMasterSid the primary key of the cff view master
	* @return the cff view master, or <code>null</code> if a cff view master with the primary key could not be found
	*/
	public static CffViewMaster fetchByPrimaryKey(int cffViewMasterSid) {
		return getPersistence().fetchByPrimaryKey(cffViewMasterSid);
	}

	public static java.util.Map<java.io.Serializable, CffViewMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cff view masters.
	*
	* @return the cff view masters
	*/
	public static List<CffViewMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cff view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff view masters
	* @param end the upper bound of the range of cff view masters (not inclusive)
	* @return the range of cff view masters
	*/
	public static List<CffViewMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cff view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff view masters
	* @param end the upper bound of the range of cff view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff view masters
	*/
	public static List<CffViewMaster> findAll(int start, int end,
		OrderByComparator<CffViewMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cff view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff view masters
	* @param end the upper bound of the range of cff view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff view masters
	*/
	public static List<CffViewMaster> findAll(int start, int end,
		OrderByComparator<CffViewMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cff view masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cff view masters.
	*
	* @return the number of cff view masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CffViewMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CffViewMasterPersistence, CffViewMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CffViewMasterPersistence.class);
}