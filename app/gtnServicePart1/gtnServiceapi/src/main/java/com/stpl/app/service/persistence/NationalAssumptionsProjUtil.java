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

import com.stpl.app.model.NationalAssumptionsProj;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the national assumptions proj service. This utility wraps {@link com.stpl.app.service.persistence.impl.NationalAssumptionsProjPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsProjPersistence
 * @see com.stpl.app.service.persistence.impl.NationalAssumptionsProjPersistenceImpl
 * @generated
 */
@ProviderType
public class NationalAssumptionsProjUtil {
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
		NationalAssumptionsProj nationalAssumptionsProj) {
		getPersistence().clearCache(nationalAssumptionsProj);
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
	public static List<NationalAssumptionsProj> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NationalAssumptionsProj> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NationalAssumptionsProj> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NationalAssumptionsProj> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NationalAssumptionsProj update(
		NationalAssumptionsProj nationalAssumptionsProj) {
		return getPersistence().update(nationalAssumptionsProj);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NationalAssumptionsProj update(
		NationalAssumptionsProj nationalAssumptionsProj,
		ServiceContext serviceContext) {
		return getPersistence().update(nationalAssumptionsProj, serviceContext);
	}

	/**
	* Caches the national assumptions proj in the entity cache if it is enabled.
	*
	* @param nationalAssumptionsProj the national assumptions proj
	*/
	public static void cacheResult(
		NationalAssumptionsProj nationalAssumptionsProj) {
		getPersistence().cacheResult(nationalAssumptionsProj);
	}

	/**
	* Caches the national assumptions projs in the entity cache if it is enabled.
	*
	* @param nationalAssumptionsProjs the national assumptions projs
	*/
	public static void cacheResult(
		List<NationalAssumptionsProj> nationalAssumptionsProjs) {
		getPersistence().cacheResult(nationalAssumptionsProjs);
	}

	/**
	* Creates a new national assumptions proj with the primary key. Does not add the national assumptions proj to the database.
	*
	* @param nationalAssumptionsProjPK the primary key for the new national assumptions proj
	* @return the new national assumptions proj
	*/
	public static NationalAssumptionsProj create(
		NationalAssumptionsProjPK nationalAssumptionsProjPK) {
		return getPersistence().create(nationalAssumptionsProjPK);
	}

	/**
	* Removes the national assumptions proj with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nationalAssumptionsProjPK the primary key of the national assumptions proj
	* @return the national assumptions proj that was removed
	* @throws NoSuchNationalAssumptionsProjException if a national assumptions proj with the primary key could not be found
	*/
	public static NationalAssumptionsProj remove(
		NationalAssumptionsProjPK nationalAssumptionsProjPK)
		throws com.stpl.app.exception.NoSuchNationalAssumptionsProjException {
		return getPersistence().remove(nationalAssumptionsProjPK);
	}

	public static NationalAssumptionsProj updateImpl(
		NationalAssumptionsProj nationalAssumptionsProj) {
		return getPersistence().updateImpl(nationalAssumptionsProj);
	}

	/**
	* Returns the national assumptions proj with the primary key or throws a {@link NoSuchNationalAssumptionsProjException} if it could not be found.
	*
	* @param nationalAssumptionsProjPK the primary key of the national assumptions proj
	* @return the national assumptions proj
	* @throws NoSuchNationalAssumptionsProjException if a national assumptions proj with the primary key could not be found
	*/
	public static NationalAssumptionsProj findByPrimaryKey(
		NationalAssumptionsProjPK nationalAssumptionsProjPK)
		throws com.stpl.app.exception.NoSuchNationalAssumptionsProjException {
		return getPersistence().findByPrimaryKey(nationalAssumptionsProjPK);
	}

	/**
	* Returns the national assumptions proj with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nationalAssumptionsProjPK the primary key of the national assumptions proj
	* @return the national assumptions proj, or <code>null</code> if a national assumptions proj with the primary key could not be found
	*/
	public static NationalAssumptionsProj fetchByPrimaryKey(
		NationalAssumptionsProjPK nationalAssumptionsProjPK) {
		return getPersistence().fetchByPrimaryKey(nationalAssumptionsProjPK);
	}

	public static java.util.Map<java.io.Serializable, NationalAssumptionsProj> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the national assumptions projs.
	*
	* @return the national assumptions projs
	*/
	public static List<NationalAssumptionsProj> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the national assumptions projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptions projs
	* @param end the upper bound of the range of national assumptions projs (not inclusive)
	* @return the range of national assumptions projs
	*/
	public static List<NationalAssumptionsProj> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the national assumptions projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptions projs
	* @param end the upper bound of the range of national assumptions projs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of national assumptions projs
	*/
	public static List<NationalAssumptionsProj> findAll(int start, int end,
		OrderByComparator<NationalAssumptionsProj> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the national assumptions projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of national assumptions projs
	* @param end the upper bound of the range of national assumptions projs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of national assumptions projs
	*/
	public static List<NationalAssumptionsProj> findAll(int start, int end,
		OrderByComparator<NationalAssumptionsProj> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the national assumptions projs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of national assumptions projs.
	*
	* @return the number of national assumptions projs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NationalAssumptionsProjPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NationalAssumptionsProjPersistence, NationalAssumptionsProjPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NationalAssumptionsProjPersistence.class);
}