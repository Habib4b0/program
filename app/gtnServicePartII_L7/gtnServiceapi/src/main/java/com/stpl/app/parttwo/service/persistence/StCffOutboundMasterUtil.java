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

import com.stpl.app.parttwo.model.StCffOutboundMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st cff outbound master service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.StCffOutboundMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StCffOutboundMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.StCffOutboundMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class StCffOutboundMasterUtil {
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
	public static void clearCache(StCffOutboundMaster stCffOutboundMaster) {
		getPersistence().clearCache(stCffOutboundMaster);
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
	public static List<StCffOutboundMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StCffOutboundMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StCffOutboundMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StCffOutboundMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StCffOutboundMaster update(
		StCffOutboundMaster stCffOutboundMaster) {
		return getPersistence().update(stCffOutboundMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StCffOutboundMaster update(
		StCffOutboundMaster stCffOutboundMaster, ServiceContext serviceContext) {
		return getPersistence().update(stCffOutboundMaster, serviceContext);
	}

	/**
	* Caches the st cff outbound master in the entity cache if it is enabled.
	*
	* @param stCffOutboundMaster the st cff outbound master
	*/
	public static void cacheResult(StCffOutboundMaster stCffOutboundMaster) {
		getPersistence().cacheResult(stCffOutboundMaster);
	}

	/**
	* Caches the st cff outbound masters in the entity cache if it is enabled.
	*
	* @param stCffOutboundMasters the st cff outbound masters
	*/
	public static void cacheResult(
		List<StCffOutboundMaster> stCffOutboundMasters) {
		getPersistence().cacheResult(stCffOutboundMasters);
	}

	/**
	* Creates a new st cff outbound master with the primary key. Does not add the st cff outbound master to the database.
	*
	* @param stCffOutboundMasterPK the primary key for the new st cff outbound master
	* @return the new st cff outbound master
	*/
	public static StCffOutboundMaster create(
		StCffOutboundMasterPK stCffOutboundMasterPK) {
		return getPersistence().create(stCffOutboundMasterPK);
	}

	/**
	* Removes the st cff outbound master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stCffOutboundMasterPK the primary key of the st cff outbound master
	* @return the st cff outbound master that was removed
	* @throws NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
	*/
	public static StCffOutboundMaster remove(
		StCffOutboundMasterPK stCffOutboundMasterPK)
		throws com.stpl.app.parttwo.exception.NoSuchStCffOutboundMasterException {
		return getPersistence().remove(stCffOutboundMasterPK);
	}

	public static StCffOutboundMaster updateImpl(
		StCffOutboundMaster stCffOutboundMaster) {
		return getPersistence().updateImpl(stCffOutboundMaster);
	}

	/**
	* Returns the st cff outbound master with the primary key or throws a {@link NoSuchStCffOutboundMasterException} if it could not be found.
	*
	* @param stCffOutboundMasterPK the primary key of the st cff outbound master
	* @return the st cff outbound master
	* @throws NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
	*/
	public static StCffOutboundMaster findByPrimaryKey(
		StCffOutboundMasterPK stCffOutboundMasterPK)
		throws com.stpl.app.parttwo.exception.NoSuchStCffOutboundMasterException {
		return getPersistence().findByPrimaryKey(stCffOutboundMasterPK);
	}

	/**
	* Returns the st cff outbound master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stCffOutboundMasterPK the primary key of the st cff outbound master
	* @return the st cff outbound master, or <code>null</code> if a st cff outbound master with the primary key could not be found
	*/
	public static StCffOutboundMaster fetchByPrimaryKey(
		StCffOutboundMasterPK stCffOutboundMasterPK) {
		return getPersistence().fetchByPrimaryKey(stCffOutboundMasterPK);
	}

	public static java.util.Map<java.io.Serializable, StCffOutboundMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st cff outbound masters.
	*
	* @return the st cff outbound masters
	*/
	public static List<StCffOutboundMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st cff outbound masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st cff outbound masters
	* @param end the upper bound of the range of st cff outbound masters (not inclusive)
	* @return the range of st cff outbound masters
	*/
	public static List<StCffOutboundMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st cff outbound masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st cff outbound masters
	* @param end the upper bound of the range of st cff outbound masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st cff outbound masters
	*/
	public static List<StCffOutboundMaster> findAll(int start, int end,
		OrderByComparator<StCffOutboundMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st cff outbound masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st cff outbound masters
	* @param end the upper bound of the range of st cff outbound masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st cff outbound masters
	*/
	public static List<StCffOutboundMaster> findAll(int start, int end,
		OrderByComparator<StCffOutboundMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st cff outbound masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st cff outbound masters.
	*
	* @return the number of st cff outbound masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StCffOutboundMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StCffOutboundMasterPersistence, StCffOutboundMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StCffOutboundMasterPersistence.class);
}