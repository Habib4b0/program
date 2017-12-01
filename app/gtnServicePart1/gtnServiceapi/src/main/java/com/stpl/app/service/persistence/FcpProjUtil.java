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

import com.stpl.app.model.FcpProj;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the fcp proj service. This utility wraps {@link com.stpl.app.service.persistence.impl.FcpProjPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FcpProjPersistence
 * @see com.stpl.app.service.persistence.impl.FcpProjPersistenceImpl
 * @generated
 */
@ProviderType
public class FcpProjUtil {
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
	public static void clearCache(FcpProj fcpProj) {
		getPersistence().clearCache(fcpProj);
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
	public static List<FcpProj> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FcpProj> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FcpProj> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FcpProj> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FcpProj update(FcpProj fcpProj) {
		return getPersistence().update(fcpProj);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FcpProj update(FcpProj fcpProj, ServiceContext serviceContext) {
		return getPersistence().update(fcpProj, serviceContext);
	}

	/**
	* Caches the fcp proj in the entity cache if it is enabled.
	*
	* @param fcpProj the fcp proj
	*/
	public static void cacheResult(FcpProj fcpProj) {
		getPersistence().cacheResult(fcpProj);
	}

	/**
	* Caches the fcp projs in the entity cache if it is enabled.
	*
	* @param fcpProjs the fcp projs
	*/
	public static void cacheResult(List<FcpProj> fcpProjs) {
		getPersistence().cacheResult(fcpProjs);
	}

	/**
	* Creates a new fcp proj with the primary key. Does not add the fcp proj to the database.
	*
	* @param fcpProjPK the primary key for the new fcp proj
	* @return the new fcp proj
	*/
	public static FcpProj create(FcpProjPK fcpProjPK) {
		return getPersistence().create(fcpProjPK);
	}

	/**
	* Removes the fcp proj with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fcpProjPK the primary key of the fcp proj
	* @return the fcp proj that was removed
	* @throws NoSuchFcpProjException if a fcp proj with the primary key could not be found
	*/
	public static FcpProj remove(FcpProjPK fcpProjPK)
		throws com.stpl.app.exception.NoSuchFcpProjException {
		return getPersistence().remove(fcpProjPK);
	}

	public static FcpProj updateImpl(FcpProj fcpProj) {
		return getPersistence().updateImpl(fcpProj);
	}

	/**
	* Returns the fcp proj with the primary key or throws a {@link NoSuchFcpProjException} if it could not be found.
	*
	* @param fcpProjPK the primary key of the fcp proj
	* @return the fcp proj
	* @throws NoSuchFcpProjException if a fcp proj with the primary key could not be found
	*/
	public static FcpProj findByPrimaryKey(FcpProjPK fcpProjPK)
		throws com.stpl.app.exception.NoSuchFcpProjException {
		return getPersistence().findByPrimaryKey(fcpProjPK);
	}

	/**
	* Returns the fcp proj with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fcpProjPK the primary key of the fcp proj
	* @return the fcp proj, or <code>null</code> if a fcp proj with the primary key could not be found
	*/
	public static FcpProj fetchByPrimaryKey(FcpProjPK fcpProjPK) {
		return getPersistence().fetchByPrimaryKey(fcpProjPK);
	}

	public static java.util.Map<java.io.Serializable, FcpProj> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the fcp projs.
	*
	* @return the fcp projs
	*/
	public static List<FcpProj> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the fcp projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fcp projs
	* @param end the upper bound of the range of fcp projs (not inclusive)
	* @return the range of fcp projs
	*/
	public static List<FcpProj> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the fcp projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fcp projs
	* @param end the upper bound of the range of fcp projs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of fcp projs
	*/
	public static List<FcpProj> findAll(int start, int end,
		OrderByComparator<FcpProj> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the fcp projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fcp projs
	* @param end the upper bound of the range of fcp projs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of fcp projs
	*/
	public static List<FcpProj> findAll(int start, int end,
		OrderByComparator<FcpProj> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the fcp projs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of fcp projs.
	*
	* @return the number of fcp projs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static FcpProjPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FcpProjPersistence, FcpProjPersistence> _serviceTracker =
		ServiceTrackerFactory.open(FcpProjPersistence.class);
}