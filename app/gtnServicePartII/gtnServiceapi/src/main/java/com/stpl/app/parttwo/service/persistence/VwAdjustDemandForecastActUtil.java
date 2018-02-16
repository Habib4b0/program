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

import com.stpl.app.parttwo.model.VwAdjustDemandForecastAct;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vw adjust demand forecast act service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.VwAdjustDemandForecastActPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwAdjustDemandForecastActPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.VwAdjustDemandForecastActPersistenceImpl
 * @generated
 */
@ProviderType
public class VwAdjustDemandForecastActUtil {
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
		VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
		getPersistence().clearCache(vwAdjustDemandForecastAct);
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
	public static List<VwAdjustDemandForecastAct> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<VwAdjustDemandForecastAct> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<VwAdjustDemandForecastAct> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<VwAdjustDemandForecastAct> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static VwAdjustDemandForecastAct update(
		VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
		return getPersistence().update(vwAdjustDemandForecastAct);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static VwAdjustDemandForecastAct update(
		VwAdjustDemandForecastAct vwAdjustDemandForecastAct,
		ServiceContext serviceContext) {
		return getPersistence().update(vwAdjustDemandForecastAct, serviceContext);
	}

	/**
	* Caches the vw adjust demand forecast act in the entity cache if it is enabled.
	*
	* @param vwAdjustDemandForecastAct the vw adjust demand forecast act
	*/
	public static void cacheResult(
		VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
		getPersistence().cacheResult(vwAdjustDemandForecastAct);
	}

	/**
	* Caches the vw adjust demand forecast acts in the entity cache if it is enabled.
	*
	* @param vwAdjustDemandForecastActs the vw adjust demand forecast acts
	*/
	public static void cacheResult(
		List<VwAdjustDemandForecastAct> vwAdjustDemandForecastActs) {
		getPersistence().cacheResult(vwAdjustDemandForecastActs);
	}

	/**
	* Creates a new vw adjust demand forecast act with the primary key. Does not add the vw adjust demand forecast act to the database.
	*
	* @param adjustedDemandForecastId the primary key for the new vw adjust demand forecast act
	* @return the new vw adjust demand forecast act
	*/
	public static VwAdjustDemandForecastAct create(int adjustedDemandForecastId) {
		return getPersistence().create(adjustedDemandForecastId);
	}

	/**
	* Removes the vw adjust demand forecast act with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
	* @return the vw adjust demand forecast act that was removed
	* @throws NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
	*/
	public static VwAdjustDemandForecastAct remove(int adjustedDemandForecastId)
		throws com.stpl.app.parttwo.exception.NoSuchVwAdjustDemandForecastActException {
		return getPersistence().remove(adjustedDemandForecastId);
	}

	public static VwAdjustDemandForecastAct updateImpl(
		VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
		return getPersistence().updateImpl(vwAdjustDemandForecastAct);
	}

	/**
	* Returns the vw adjust demand forecast act with the primary key or throws a {@link NoSuchVwAdjustDemandForecastActException} if it could not be found.
	*
	* @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
	* @return the vw adjust demand forecast act
	* @throws NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
	*/
	public static VwAdjustDemandForecastAct findByPrimaryKey(
		int adjustedDemandForecastId)
		throws com.stpl.app.parttwo.exception.NoSuchVwAdjustDemandForecastActException {
		return getPersistence().findByPrimaryKey(adjustedDemandForecastId);
	}

	/**
	* Returns the vw adjust demand forecast act with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
	* @return the vw adjust demand forecast act, or <code>null</code> if a vw adjust demand forecast act with the primary key could not be found
	*/
	public static VwAdjustDemandForecastAct fetchByPrimaryKey(
		int adjustedDemandForecastId) {
		return getPersistence().fetchByPrimaryKey(adjustedDemandForecastId);
	}

	public static java.util.Map<java.io.Serializable, VwAdjustDemandForecastAct> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the vw adjust demand forecast acts.
	*
	* @return the vw adjust demand forecast acts
	*/
	public static List<VwAdjustDemandForecastAct> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the vw adjust demand forecast acts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw adjust demand forecast acts
	* @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
	* @return the range of vw adjust demand forecast acts
	*/
	public static List<VwAdjustDemandForecastAct> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the vw adjust demand forecast acts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw adjust demand forecast acts
	* @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw adjust demand forecast acts
	*/
	public static List<VwAdjustDemandForecastAct> findAll(int start, int end,
		OrderByComparator<VwAdjustDemandForecastAct> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the vw adjust demand forecast acts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw adjust demand forecast acts
	* @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw adjust demand forecast acts
	*/
	public static List<VwAdjustDemandForecastAct> findAll(int start, int end,
		OrderByComparator<VwAdjustDemandForecastAct> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the vw adjust demand forecast acts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of vw adjust demand forecast acts.
	*
	* @return the number of vw adjust demand forecast acts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VwAdjustDemandForecastActPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VwAdjustDemandForecastActPersistence, VwAdjustDemandForecastActPersistence> _serviceTracker =
		ServiceTrackerFactory.open(VwAdjustDemandForecastActPersistence.class);
}