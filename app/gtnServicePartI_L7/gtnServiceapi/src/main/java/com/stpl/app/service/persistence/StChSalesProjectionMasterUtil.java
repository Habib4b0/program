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

import com.stpl.app.model.StChSalesProjectionMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st ch sales projection master service. This utility wraps {@link com.stpl.app.service.persistence.impl.StChSalesProjectionMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChSalesProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.impl.StChSalesProjectionMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class StChSalesProjectionMasterUtil {
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
		StChSalesProjectionMaster stChSalesProjectionMaster) {
		getPersistence().clearCache(stChSalesProjectionMaster);
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
	public static List<StChSalesProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StChSalesProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StChSalesProjectionMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StChSalesProjectionMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StChSalesProjectionMaster update(
		StChSalesProjectionMaster stChSalesProjectionMaster) {
		return getPersistence().update(stChSalesProjectionMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StChSalesProjectionMaster update(
		StChSalesProjectionMaster stChSalesProjectionMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(stChSalesProjectionMaster, serviceContext);
	}

	/**
	* Caches the st ch sales projection master in the entity cache if it is enabled.
	*
	* @param stChSalesProjectionMaster the st ch sales projection master
	*/
	public static void cacheResult(
		StChSalesProjectionMaster stChSalesProjectionMaster) {
		getPersistence().cacheResult(stChSalesProjectionMaster);
	}

	/**
	* Caches the st ch sales projection masters in the entity cache if it is enabled.
	*
	* @param stChSalesProjectionMasters the st ch sales projection masters
	*/
	public static void cacheResult(
		List<StChSalesProjectionMaster> stChSalesProjectionMasters) {
		getPersistence().cacheResult(stChSalesProjectionMasters);
	}

	/**
	* Creates a new st ch sales projection master with the primary key. Does not add the st ch sales projection master to the database.
	*
	* @param stChSalesProjectionMasterPK the primary key for the new st ch sales projection master
	* @return the new st ch sales projection master
	*/
	public static StChSalesProjectionMaster create(
		StChSalesProjectionMasterPK stChSalesProjectionMasterPK) {
		return getPersistence().create(stChSalesProjectionMasterPK);
	}

	/**
	* Removes the st ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
	* @return the st ch sales projection master that was removed
	* @throws NoSuchStChSalesProjectionMasterException if a st ch sales projection master with the primary key could not be found
	*/
	public static StChSalesProjectionMaster remove(
		StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
		throws com.stpl.app.exception.NoSuchStChSalesProjectionMasterException {
		return getPersistence().remove(stChSalesProjectionMasterPK);
	}

	public static StChSalesProjectionMaster updateImpl(
		StChSalesProjectionMaster stChSalesProjectionMaster) {
		return getPersistence().updateImpl(stChSalesProjectionMaster);
	}

	/**
	* Returns the st ch sales projection master with the primary key or throws a {@link NoSuchStChSalesProjectionMasterException} if it could not be found.
	*
	* @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
	* @return the st ch sales projection master
	* @throws NoSuchStChSalesProjectionMasterException if a st ch sales projection master with the primary key could not be found
	*/
	public static StChSalesProjectionMaster findByPrimaryKey(
		StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
		throws com.stpl.app.exception.NoSuchStChSalesProjectionMasterException {
		return getPersistence().findByPrimaryKey(stChSalesProjectionMasterPK);
	}

	/**
	* Returns the st ch sales projection master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
	* @return the st ch sales projection master, or <code>null</code> if a st ch sales projection master with the primary key could not be found
	*/
	public static StChSalesProjectionMaster fetchByPrimaryKey(
		StChSalesProjectionMasterPK stChSalesProjectionMasterPK) {
		return getPersistence().fetchByPrimaryKey(stChSalesProjectionMasterPK);
	}

	public static java.util.Map<java.io.Serializable, StChSalesProjectionMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st ch sales projection masters.
	*
	* @return the st ch sales projection masters
	*/
	public static List<StChSalesProjectionMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st ch sales projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch sales projection masters
	* @param end the upper bound of the range of st ch sales projection masters (not inclusive)
	* @return the range of st ch sales projection masters
	*/
	public static List<StChSalesProjectionMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st ch sales projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch sales projection masters
	* @param end the upper bound of the range of st ch sales projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st ch sales projection masters
	*/
	public static List<StChSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<StChSalesProjectionMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st ch sales projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch sales projection masters
	* @param end the upper bound of the range of st ch sales projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st ch sales projection masters
	*/
	public static List<StChSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<StChSalesProjectionMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st ch sales projection masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st ch sales projection masters.
	*
	* @return the number of st ch sales projection masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StChSalesProjectionMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StChSalesProjectionMasterPersistence, StChSalesProjectionMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StChSalesProjectionMasterPersistence.class);
}