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

import com.stpl.app.model.StChAssumptions;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st ch assumptions service. This utility wraps {@link com.stpl.app.service.persistence.impl.StChAssumptionsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChAssumptionsPersistence
 * @see com.stpl.app.service.persistence.impl.StChAssumptionsPersistenceImpl
 * @generated
 */
@ProviderType
public class StChAssumptionsUtil {
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
	public static void clearCache(StChAssumptions stChAssumptions) {
		getPersistence().clearCache(stChAssumptions);
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
	public static List<StChAssumptions> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StChAssumptions> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StChAssumptions> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StChAssumptions> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StChAssumptions update(StChAssumptions stChAssumptions) {
		return getPersistence().update(stChAssumptions);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StChAssumptions update(StChAssumptions stChAssumptions,
		ServiceContext serviceContext) {
		return getPersistence().update(stChAssumptions, serviceContext);
	}

	/**
	* Caches the st ch assumptions in the entity cache if it is enabled.
	*
	* @param stChAssumptions the st ch assumptions
	*/
	public static void cacheResult(StChAssumptions stChAssumptions) {
		getPersistence().cacheResult(stChAssumptions);
	}

	/**
	* Caches the st ch assumptionses in the entity cache if it is enabled.
	*
	* @param stChAssumptionses the st ch assumptionses
	*/
	public static void cacheResult(List<StChAssumptions> stChAssumptionses) {
		getPersistence().cacheResult(stChAssumptionses);
	}

	/**
	* Creates a new st ch assumptions with the primary key. Does not add the st ch assumptions to the database.
	*
	* @param stChAssumptionsPK the primary key for the new st ch assumptions
	* @return the new st ch assumptions
	*/
	public static StChAssumptions create(StChAssumptionsPK stChAssumptionsPK) {
		return getPersistence().create(stChAssumptionsPK);
	}

	/**
	* Removes the st ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stChAssumptionsPK the primary key of the st ch assumptions
	* @return the st ch assumptions that was removed
	* @throws NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
	*/
	public static StChAssumptions remove(StChAssumptionsPK stChAssumptionsPK)
		throws com.stpl.app.exception.NoSuchStChAssumptionsException {
		return getPersistence().remove(stChAssumptionsPK);
	}

	public static StChAssumptions updateImpl(StChAssumptions stChAssumptions) {
		return getPersistence().updateImpl(stChAssumptions);
	}

	/**
	* Returns the st ch assumptions with the primary key or throws a {@link NoSuchStChAssumptionsException} if it could not be found.
	*
	* @param stChAssumptionsPK the primary key of the st ch assumptions
	* @return the st ch assumptions
	* @throws NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
	*/
	public static StChAssumptions findByPrimaryKey(
		StChAssumptionsPK stChAssumptionsPK)
		throws com.stpl.app.exception.NoSuchStChAssumptionsException {
		return getPersistence().findByPrimaryKey(stChAssumptionsPK);
	}

	/**
	* Returns the st ch assumptions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stChAssumptionsPK the primary key of the st ch assumptions
	* @return the st ch assumptions, or <code>null</code> if a st ch assumptions with the primary key could not be found
	*/
	public static StChAssumptions fetchByPrimaryKey(
		StChAssumptionsPK stChAssumptionsPK) {
		return getPersistence().fetchByPrimaryKey(stChAssumptionsPK);
	}

	public static java.util.Map<java.io.Serializable, StChAssumptions> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st ch assumptionses.
	*
	* @return the st ch assumptionses
	*/
	public static List<StChAssumptions> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch assumptionses
	* @param end the upper bound of the range of st ch assumptionses (not inclusive)
	* @return the range of st ch assumptionses
	*/
	public static List<StChAssumptions> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch assumptionses
	* @param end the upper bound of the range of st ch assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st ch assumptionses
	*/
	public static List<StChAssumptions> findAll(int start, int end,
		OrderByComparator<StChAssumptions> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch assumptionses
	* @param end the upper bound of the range of st ch assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st ch assumptionses
	*/
	public static List<StChAssumptions> findAll(int start, int end,
		OrderByComparator<StChAssumptions> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st ch assumptionses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st ch assumptionses.
	*
	* @return the number of st ch assumptionses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StChAssumptionsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StChAssumptionsPersistence, StChAssumptionsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StChAssumptionsPersistence.class);
}