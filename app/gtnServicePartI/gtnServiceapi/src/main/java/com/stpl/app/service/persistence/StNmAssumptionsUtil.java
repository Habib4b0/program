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

import com.stpl.app.model.StNmAssumptions;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st nm assumptions service. This utility wraps {@link com.stpl.app.service.persistence.impl.StNmAssumptionsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmAssumptionsPersistence
 * @see com.stpl.app.service.persistence.impl.StNmAssumptionsPersistenceImpl
 * @generated
 */
@ProviderType
public class StNmAssumptionsUtil {
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
	public static void clearCache(StNmAssumptions stNmAssumptions) {
		getPersistence().clearCache(stNmAssumptions);
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
	public static List<StNmAssumptions> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StNmAssumptions> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StNmAssumptions> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StNmAssumptions> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StNmAssumptions update(StNmAssumptions stNmAssumptions) {
		return getPersistence().update(stNmAssumptions);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StNmAssumptions update(StNmAssumptions stNmAssumptions,
		ServiceContext serviceContext) {
		return getPersistence().update(stNmAssumptions, serviceContext);
	}

	/**
	* Caches the st nm assumptions in the entity cache if it is enabled.
	*
	* @param stNmAssumptions the st nm assumptions
	*/
	public static void cacheResult(StNmAssumptions stNmAssumptions) {
		getPersistence().cacheResult(stNmAssumptions);
	}

	/**
	* Caches the st nm assumptionses in the entity cache if it is enabled.
	*
	* @param stNmAssumptionses the st nm assumptionses
	*/
	public static void cacheResult(List<StNmAssumptions> stNmAssumptionses) {
		getPersistence().cacheResult(stNmAssumptionses);
	}

	/**
	* Creates a new st nm assumptions with the primary key. Does not add the st nm assumptions to the database.
	*
	* @param stNmAssumptionsPK the primary key for the new st nm assumptions
	* @return the new st nm assumptions
	*/
	public static StNmAssumptions create(StNmAssumptionsPK stNmAssumptionsPK) {
		return getPersistence().create(stNmAssumptionsPK);
	}

	/**
	* Removes the st nm assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmAssumptionsPK the primary key of the st nm assumptions
	* @return the st nm assumptions that was removed
	* @throws NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
	*/
	public static StNmAssumptions remove(StNmAssumptionsPK stNmAssumptionsPK)
		throws com.stpl.app.exception.NoSuchStNmAssumptionsException {
		return getPersistence().remove(stNmAssumptionsPK);
	}

	public static StNmAssumptions updateImpl(StNmAssumptions stNmAssumptions) {
		return getPersistence().updateImpl(stNmAssumptions);
	}

	/**
	* Returns the st nm assumptions with the primary key or throws a {@link NoSuchStNmAssumptionsException} if it could not be found.
	*
	* @param stNmAssumptionsPK the primary key of the st nm assumptions
	* @return the st nm assumptions
	* @throws NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
	*/
	public static StNmAssumptions findByPrimaryKey(
		StNmAssumptionsPK stNmAssumptionsPK)
		throws com.stpl.app.exception.NoSuchStNmAssumptionsException {
		return getPersistence().findByPrimaryKey(stNmAssumptionsPK);
	}

	/**
	* Returns the st nm assumptions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmAssumptionsPK the primary key of the st nm assumptions
	* @return the st nm assumptions, or <code>null</code> if a st nm assumptions with the primary key could not be found
	*/
	public static StNmAssumptions fetchByPrimaryKey(
		StNmAssumptionsPK stNmAssumptionsPK) {
		return getPersistence().fetchByPrimaryKey(stNmAssumptionsPK);
	}

	public static java.util.Map<java.io.Serializable, StNmAssumptions> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st nm assumptionses.
	*
	* @return the st nm assumptionses
	*/
	public static List<StNmAssumptions> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st nm assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm assumptionses
	* @param end the upper bound of the range of st nm assumptionses (not inclusive)
	* @return the range of st nm assumptionses
	*/
	public static List<StNmAssumptions> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st nm assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm assumptionses
	* @param end the upper bound of the range of st nm assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm assumptionses
	*/
	public static List<StNmAssumptions> findAll(int start, int end,
		OrderByComparator<StNmAssumptions> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st nm assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm assumptionses
	* @param end the upper bound of the range of st nm assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm assumptionses
	*/
	public static List<StNmAssumptions> findAll(int start, int end,
		OrderByComparator<StNmAssumptions> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st nm assumptionses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st nm assumptionses.
	*
	* @return the number of st nm assumptionses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StNmAssumptionsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StNmAssumptionsPersistence, StNmAssumptionsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StNmAssumptionsPersistence.class);
}