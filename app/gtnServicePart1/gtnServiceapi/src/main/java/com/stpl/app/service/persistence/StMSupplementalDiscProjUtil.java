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

import com.stpl.app.model.StMSupplementalDiscProj;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st m supplemental disc proj service. This utility wraps {@link com.stpl.app.service.persistence.impl.StMSupplementalDiscProjPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMSupplementalDiscProjPersistence
 * @see com.stpl.app.service.persistence.impl.StMSupplementalDiscProjPersistenceImpl
 * @generated
 */
@ProviderType
public class StMSupplementalDiscProjUtil {
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
		StMSupplementalDiscProj stMSupplementalDiscProj) {
		getPersistence().clearCache(stMSupplementalDiscProj);
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
	public static List<StMSupplementalDiscProj> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StMSupplementalDiscProj> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StMSupplementalDiscProj> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StMSupplementalDiscProj> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StMSupplementalDiscProj update(
		StMSupplementalDiscProj stMSupplementalDiscProj) {
		return getPersistence().update(stMSupplementalDiscProj);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StMSupplementalDiscProj update(
		StMSupplementalDiscProj stMSupplementalDiscProj,
		ServiceContext serviceContext) {
		return getPersistence().update(stMSupplementalDiscProj, serviceContext);
	}

	/**
	* Caches the st m supplemental disc proj in the entity cache if it is enabled.
	*
	* @param stMSupplementalDiscProj the st m supplemental disc proj
	*/
	public static void cacheResult(
		StMSupplementalDiscProj stMSupplementalDiscProj) {
		getPersistence().cacheResult(stMSupplementalDiscProj);
	}

	/**
	* Caches the st m supplemental disc projs in the entity cache if it is enabled.
	*
	* @param stMSupplementalDiscProjs the st m supplemental disc projs
	*/
	public static void cacheResult(
		List<StMSupplementalDiscProj> stMSupplementalDiscProjs) {
		getPersistence().cacheResult(stMSupplementalDiscProjs);
	}

	/**
	* Creates a new st m supplemental disc proj with the primary key. Does not add the st m supplemental disc proj to the database.
	*
	* @param stMSupplementalDiscProjPK the primary key for the new st m supplemental disc proj
	* @return the new st m supplemental disc proj
	*/
	public static StMSupplementalDiscProj create(
		StMSupplementalDiscProjPK stMSupplementalDiscProjPK) {
		return getPersistence().create(stMSupplementalDiscProjPK);
	}

	/**
	* Removes the st m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stMSupplementalDiscProjPK the primary key of the st m supplemental disc proj
	* @return the st m supplemental disc proj that was removed
	* @throws NoSuchStMSupplementalDiscProjException if a st m supplemental disc proj with the primary key could not be found
	*/
	public static StMSupplementalDiscProj remove(
		StMSupplementalDiscProjPK stMSupplementalDiscProjPK)
		throws com.stpl.app.exception.NoSuchStMSupplementalDiscProjException {
		return getPersistence().remove(stMSupplementalDiscProjPK);
	}

	public static StMSupplementalDiscProj updateImpl(
		StMSupplementalDiscProj stMSupplementalDiscProj) {
		return getPersistence().updateImpl(stMSupplementalDiscProj);
	}

	/**
	* Returns the st m supplemental disc proj with the primary key or throws a {@link NoSuchStMSupplementalDiscProjException} if it could not be found.
	*
	* @param stMSupplementalDiscProjPK the primary key of the st m supplemental disc proj
	* @return the st m supplemental disc proj
	* @throws NoSuchStMSupplementalDiscProjException if a st m supplemental disc proj with the primary key could not be found
	*/
	public static StMSupplementalDiscProj findByPrimaryKey(
		StMSupplementalDiscProjPK stMSupplementalDiscProjPK)
		throws com.stpl.app.exception.NoSuchStMSupplementalDiscProjException {
		return getPersistence().findByPrimaryKey(stMSupplementalDiscProjPK);
	}

	/**
	* Returns the st m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stMSupplementalDiscProjPK the primary key of the st m supplemental disc proj
	* @return the st m supplemental disc proj, or <code>null</code> if a st m supplemental disc proj with the primary key could not be found
	*/
	public static StMSupplementalDiscProj fetchByPrimaryKey(
		StMSupplementalDiscProjPK stMSupplementalDiscProjPK) {
		return getPersistence().fetchByPrimaryKey(stMSupplementalDiscProjPK);
	}

	public static java.util.Map<java.io.Serializable, StMSupplementalDiscProj> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st m supplemental disc projs.
	*
	* @return the st m supplemental disc projs
	*/
	public static List<StMSupplementalDiscProj> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st m supplemental disc projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m supplemental disc projs
	* @param end the upper bound of the range of st m supplemental disc projs (not inclusive)
	* @return the range of st m supplemental disc projs
	*/
	public static List<StMSupplementalDiscProj> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st m supplemental disc projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m supplemental disc projs
	* @param end the upper bound of the range of st m supplemental disc projs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st m supplemental disc projs
	*/
	public static List<StMSupplementalDiscProj> findAll(int start, int end,
		OrderByComparator<StMSupplementalDiscProj> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st m supplemental disc projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m supplemental disc projs
	* @param end the upper bound of the range of st m supplemental disc projs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st m supplemental disc projs
	*/
	public static List<StMSupplementalDiscProj> findAll(int start, int end,
		OrderByComparator<StMSupplementalDiscProj> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st m supplemental disc projs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st m supplemental disc projs.
	*
	* @return the number of st m supplemental disc projs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StMSupplementalDiscProjPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StMSupplementalDiscProjPersistence, StMSupplementalDiscProjPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StMSupplementalDiscProjPersistence.class);
}