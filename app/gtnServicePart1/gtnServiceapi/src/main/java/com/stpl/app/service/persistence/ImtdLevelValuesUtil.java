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

import com.stpl.app.model.ImtdLevelValues;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the imtd level values service. This utility wraps {@link com.stpl.app.service.persistence.impl.ImtdLevelValuesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdLevelValuesPersistence
 * @see com.stpl.app.service.persistence.impl.ImtdLevelValuesPersistenceImpl
 * @generated
 */
@ProviderType
public class ImtdLevelValuesUtil {
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
	public static void clearCache(ImtdLevelValues imtdLevelValues) {
		getPersistence().clearCache(imtdLevelValues);
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
	public static List<ImtdLevelValues> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ImtdLevelValues> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ImtdLevelValues> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ImtdLevelValues> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ImtdLevelValues update(ImtdLevelValues imtdLevelValues) {
		return getPersistence().update(imtdLevelValues);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ImtdLevelValues update(ImtdLevelValues imtdLevelValues,
		ServiceContext serviceContext) {
		return getPersistence().update(imtdLevelValues, serviceContext);
	}

	/**
	* Caches the imtd level values in the entity cache if it is enabled.
	*
	* @param imtdLevelValues the imtd level values
	*/
	public static void cacheResult(ImtdLevelValues imtdLevelValues) {
		getPersistence().cacheResult(imtdLevelValues);
	}

	/**
	* Caches the imtd level valueses in the entity cache if it is enabled.
	*
	* @param imtdLevelValueses the imtd level valueses
	*/
	public static void cacheResult(List<ImtdLevelValues> imtdLevelValueses) {
		getPersistence().cacheResult(imtdLevelValueses);
	}

	/**
	* Creates a new imtd level values with the primary key. Does not add the imtd level values to the database.
	*
	* @param imtdLevelValuesSid the primary key for the new imtd level values
	* @return the new imtd level values
	*/
	public static ImtdLevelValues create(int imtdLevelValuesSid) {
		return getPersistence().create(imtdLevelValuesSid);
	}

	/**
	* Removes the imtd level values with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdLevelValuesSid the primary key of the imtd level values
	* @return the imtd level values that was removed
	* @throws NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
	*/
	public static ImtdLevelValues remove(int imtdLevelValuesSid)
		throws com.stpl.app.exception.NoSuchImtdLevelValuesException {
		return getPersistence().remove(imtdLevelValuesSid);
	}

	public static ImtdLevelValues updateImpl(ImtdLevelValues imtdLevelValues) {
		return getPersistence().updateImpl(imtdLevelValues);
	}

	/**
	* Returns the imtd level values with the primary key or throws a {@link NoSuchImtdLevelValuesException} if it could not be found.
	*
	* @param imtdLevelValuesSid the primary key of the imtd level values
	* @return the imtd level values
	* @throws NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
	*/
	public static ImtdLevelValues findByPrimaryKey(int imtdLevelValuesSid)
		throws com.stpl.app.exception.NoSuchImtdLevelValuesException {
		return getPersistence().findByPrimaryKey(imtdLevelValuesSid);
	}

	/**
	* Returns the imtd level values with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdLevelValuesSid the primary key of the imtd level values
	* @return the imtd level values, or <code>null</code> if a imtd level values with the primary key could not be found
	*/
	public static ImtdLevelValues fetchByPrimaryKey(int imtdLevelValuesSid) {
		return getPersistence().fetchByPrimaryKey(imtdLevelValuesSid);
	}

	public static java.util.Map<java.io.Serializable, ImtdLevelValues> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the imtd level valueses.
	*
	* @return the imtd level valueses
	*/
	public static List<ImtdLevelValues> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the imtd level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd level valueses
	* @param end the upper bound of the range of imtd level valueses (not inclusive)
	* @return the range of imtd level valueses
	*/
	public static List<ImtdLevelValues> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the imtd level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd level valueses
	* @param end the upper bound of the range of imtd level valueses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd level valueses
	*/
	public static List<ImtdLevelValues> findAll(int start, int end,
		OrderByComparator<ImtdLevelValues> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the imtd level valueses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd level valueses
	* @param end the upper bound of the range of imtd level valueses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd level valueses
	*/
	public static List<ImtdLevelValues> findAll(int start, int end,
		OrderByComparator<ImtdLevelValues> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the imtd level valueses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of imtd level valueses.
	*
	* @return the number of imtd level valueses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ImtdLevelValuesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImtdLevelValuesPersistence, ImtdLevelValuesPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ImtdLevelValuesPersistence.class);
}