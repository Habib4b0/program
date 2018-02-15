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

import com.stpl.app.model.NationalAssumptions;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the national assumptions service. This utility wraps {@link com.stpl.app.service.persistence.impl.NationalAssumptionsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsPersistence
 * @see com.stpl.app.service.persistence.impl.NationalAssumptionsPersistenceImpl
 * @generated
 */
@ProviderType
public class NationalAssumptionsUtil {
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
	public static void clearCache(NationalAssumptions nationalAssumptions) {
		getPersistence().clearCache(nationalAssumptions);
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
	public static List<NationalAssumptions> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NationalAssumptions> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NationalAssumptions> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NationalAssumptions> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NationalAssumptions update(
		NationalAssumptions nationalAssumptions) {
		return getPersistence().update(nationalAssumptions);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NationalAssumptions update(
		NationalAssumptions nationalAssumptions, ServiceContext serviceContext) {
		return getPersistence().update(nationalAssumptions, serviceContext);
	}

	/**
	* Caches the national assumptions in the entity cache if it is enabled.
	*
	* @param nationalAssumptions the national assumptions
	*/
	public static void cacheResult(NationalAssumptions nationalAssumptions) {
		getPersistence().cacheResult(nationalAssumptions);
	}

	/**
	* Caches the national assumptionses in the entity cache if it is enabled.
	*
	* @param nationalAssumptionses the national assumptionses
	*/
	public static void cacheResult(
		List<NationalAssumptions> nationalAssumptionses) {
		getPersistence().cacheResult(nationalAssumptionses);
	}

	/**
	* Creates a new national assumptions with the primary key. Does not add the national assumptions to the database.
	*
	* @param nationalAssumptionsPK the primary key for the new national assumptions
	* @return the new national assumptions
	*/
	public static NationalAssumptions create(
		NationalAssumptionsPK nationalAssumptionsPK) {
		return getPersistence().create(nationalAssumptionsPK);
	}

	/**
	* Removes the national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsPK the primary key of the national assumptions
	* @return the national assumptions that was removed
	* @throws NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
	*/
	public static NationalAssumptions remove(
		NationalAssumptionsPK nationalAssumptionsPK)
		throws com.stpl.app.exception.NoSuchNationalAssumptionsException {
		return getPersistence().remove(nationalAssumptionsPK);
	}

	public static NationalAssumptions updateImpl(
		NationalAssumptions nationalAssumptions) {
		return getPersistence().updateImpl(nationalAssumptions);
	}

	/**
	* Returns the national assumptions with the primary key or throws a {@link NoSuchNationalAssumptionsException} if it could not be found.
	*
	* @param nationalAssumptionsPK the primary key of the national assumptions
	* @return the national assumptions
	* @throws NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
	*/
	public static NationalAssumptions findByPrimaryKey(
		NationalAssumptionsPK nationalAssumptionsPK)
		throws com.stpl.app.exception.NoSuchNationalAssumptionsException {
		return getPersistence().findByPrimaryKey(nationalAssumptionsPK);
	}

	/**
	* Returns the national assumptions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nationalAssumptionsPK the primary key of the national assumptions
	* @return the national assumptions, or <code>null</code> if a national assumptions with the primary key could not be found
	*/
	public static NationalAssumptions fetchByPrimaryKey(
		NationalAssumptionsPK nationalAssumptionsPK) {
		return getPersistence().fetchByPrimaryKey(nationalAssumptionsPK);
	}

	public static java.util.Map<java.io.Serializable, NationalAssumptions> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the national assumptionses.
	*
	* @return the national assumptionses
	*/
	public static List<NationalAssumptions> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the national assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptionses
	* @param end the upper bound of the range of national assumptionses (not inclusive)
	* @return the range of national assumptionses
	*/
	public static List<NationalAssumptions> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the national assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptionses
	* @param end the upper bound of the range of national assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of national assumptionses
	*/
	public static List<NationalAssumptions> findAll(int start, int end,
		OrderByComparator<NationalAssumptions> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the national assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptionses
	* @param end the upper bound of the range of national assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of national assumptionses
	*/
	public static List<NationalAssumptions> findAll(int start, int end,
		OrderByComparator<NationalAssumptions> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the national assumptionses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of national assumptionses.
	*
	* @return the number of national assumptionses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NationalAssumptionsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NationalAssumptionsPersistence, NationalAssumptionsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NationalAssumptionsPersistence.class);
}