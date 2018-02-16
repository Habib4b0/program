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

import com.stpl.app.model.ChAssumptions;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ch assumptions service. This utility wraps {@link com.stpl.app.service.persistence.impl.ChAssumptionsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChAssumptionsPersistence
 * @see com.stpl.app.service.persistence.impl.ChAssumptionsPersistenceImpl
 * @generated
 */
@ProviderType
public class ChAssumptionsUtil {
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
	public static void clearCache(ChAssumptions chAssumptions) {
		getPersistence().clearCache(chAssumptions);
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
	public static List<ChAssumptions> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ChAssumptions> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ChAssumptions> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ChAssumptions> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ChAssumptions update(ChAssumptions chAssumptions) {
		return getPersistence().update(chAssumptions);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ChAssumptions update(ChAssumptions chAssumptions,
		ServiceContext serviceContext) {
		return getPersistence().update(chAssumptions, serviceContext);
	}

	/**
	* Caches the ch assumptions in the entity cache if it is enabled.
	*
	* @param chAssumptions the ch assumptions
	*/
	public static void cacheResult(ChAssumptions chAssumptions) {
		getPersistence().cacheResult(chAssumptions);
	}

	/**
	* Caches the ch assumptionses in the entity cache if it is enabled.
	*
	* @param chAssumptionses the ch assumptionses
	*/
	public static void cacheResult(List<ChAssumptions> chAssumptionses) {
		getPersistence().cacheResult(chAssumptionses);
	}

	/**
	* Creates a new ch assumptions with the primary key. Does not add the ch assumptions to the database.
	*
	* @param chAssumptionsSid the primary key for the new ch assumptions
	* @return the new ch assumptions
	*/
	public static ChAssumptions create(int chAssumptionsSid) {
		return getPersistence().create(chAssumptionsSid);
	}

	/**
	* Removes the ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chAssumptionsSid the primary key of the ch assumptions
	* @return the ch assumptions that was removed
	* @throws NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
	*/
	public static ChAssumptions remove(int chAssumptionsSid)
		throws com.stpl.app.exception.NoSuchChAssumptionsException {
		return getPersistence().remove(chAssumptionsSid);
	}

	public static ChAssumptions updateImpl(ChAssumptions chAssumptions) {
		return getPersistence().updateImpl(chAssumptions);
	}

	/**
	* Returns the ch assumptions with the primary key or throws a {@link NoSuchChAssumptionsException} if it could not be found.
	*
	* @param chAssumptionsSid the primary key of the ch assumptions
	* @return the ch assumptions
	* @throws NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
	*/
	public static ChAssumptions findByPrimaryKey(int chAssumptionsSid)
		throws com.stpl.app.exception.NoSuchChAssumptionsException {
		return getPersistence().findByPrimaryKey(chAssumptionsSid);
	}

	/**
	* Returns the ch assumptions with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chAssumptionsSid the primary key of the ch assumptions
	* @return the ch assumptions, or <code>null</code> if a ch assumptions with the primary key could not be found
	*/
	public static ChAssumptions fetchByPrimaryKey(int chAssumptionsSid) {
		return getPersistence().fetchByPrimaryKey(chAssumptionsSid);
	}

	public static java.util.Map<java.io.Serializable, ChAssumptions> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ch assumptionses.
	*
	* @return the ch assumptionses
	*/
	public static List<ChAssumptions> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch assumptionses
	* @param end the upper bound of the range of ch assumptionses (not inclusive)
	* @return the range of ch assumptionses
	*/
	public static List<ChAssumptions> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch assumptionses
	* @param end the upper bound of the range of ch assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ch assumptionses
	*/
	public static List<ChAssumptions> findAll(int start, int end,
		OrderByComparator<ChAssumptions> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ch assumptionses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch assumptionses
	* @param end the upper bound of the range of ch assumptionses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ch assumptionses
	*/
	public static List<ChAssumptions> findAll(int start, int end,
		OrderByComparator<ChAssumptions> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ch assumptionses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ch assumptionses.
	*
	* @return the number of ch assumptionses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ChAssumptionsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ChAssumptionsPersistence, ChAssumptionsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ChAssumptionsPersistence.class);
}