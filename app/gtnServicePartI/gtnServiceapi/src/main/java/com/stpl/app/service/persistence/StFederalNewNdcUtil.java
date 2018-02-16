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

import com.stpl.app.model.StFederalNewNdc;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st federal new ndc service. This utility wraps {@link com.stpl.app.service.persistence.impl.StFederalNewNdcPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StFederalNewNdcPersistence
 * @see com.stpl.app.service.persistence.impl.StFederalNewNdcPersistenceImpl
 * @generated
 */
@ProviderType
public class StFederalNewNdcUtil {
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
	public static void clearCache(StFederalNewNdc stFederalNewNdc) {
		getPersistence().clearCache(stFederalNewNdc);
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
	public static List<StFederalNewNdc> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StFederalNewNdc> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StFederalNewNdc> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StFederalNewNdc> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StFederalNewNdc update(StFederalNewNdc stFederalNewNdc) {
		return getPersistence().update(stFederalNewNdc);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StFederalNewNdc update(StFederalNewNdc stFederalNewNdc,
		ServiceContext serviceContext) {
		return getPersistence().update(stFederalNewNdc, serviceContext);
	}

	/**
	* Caches the st federal new ndc in the entity cache if it is enabled.
	*
	* @param stFederalNewNdc the st federal new ndc
	*/
	public static void cacheResult(StFederalNewNdc stFederalNewNdc) {
		getPersistence().cacheResult(stFederalNewNdc);
	}

	/**
	* Caches the st federal new ndcs in the entity cache if it is enabled.
	*
	* @param stFederalNewNdcs the st federal new ndcs
	*/
	public static void cacheResult(List<StFederalNewNdc> stFederalNewNdcs) {
		getPersistence().cacheResult(stFederalNewNdcs);
	}

	/**
	* Creates a new st federal new ndc with the primary key. Does not add the st federal new ndc to the database.
	*
	* @param stFederalNewNdcPK the primary key for the new st federal new ndc
	* @return the new st federal new ndc
	*/
	public static StFederalNewNdc create(StFederalNewNdcPK stFederalNewNdcPK) {
		return getPersistence().create(stFederalNewNdcPK);
	}

	/**
	* Removes the st federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stFederalNewNdcPK the primary key of the st federal new ndc
	* @return the st federal new ndc that was removed
	* @throws NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
	*/
	public static StFederalNewNdc remove(StFederalNewNdcPK stFederalNewNdcPK)
		throws com.stpl.app.exception.NoSuchStFederalNewNdcException {
		return getPersistence().remove(stFederalNewNdcPK);
	}

	public static StFederalNewNdc updateImpl(StFederalNewNdc stFederalNewNdc) {
		return getPersistence().updateImpl(stFederalNewNdc);
	}

	/**
	* Returns the st federal new ndc with the primary key or throws a {@link NoSuchStFederalNewNdcException} if it could not be found.
	*
	* @param stFederalNewNdcPK the primary key of the st federal new ndc
	* @return the st federal new ndc
	* @throws NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
	*/
	public static StFederalNewNdc findByPrimaryKey(
		StFederalNewNdcPK stFederalNewNdcPK)
		throws com.stpl.app.exception.NoSuchStFederalNewNdcException {
		return getPersistence().findByPrimaryKey(stFederalNewNdcPK);
	}

	/**
	* Returns the st federal new ndc with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stFederalNewNdcPK the primary key of the st federal new ndc
	* @return the st federal new ndc, or <code>null</code> if a st federal new ndc with the primary key could not be found
	*/
	public static StFederalNewNdc fetchByPrimaryKey(
		StFederalNewNdcPK stFederalNewNdcPK) {
		return getPersistence().fetchByPrimaryKey(stFederalNewNdcPK);
	}

	public static java.util.Map<java.io.Serializable, StFederalNewNdc> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st federal new ndcs.
	*
	* @return the st federal new ndcs
	*/
	public static List<StFederalNewNdc> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st federal new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st federal new ndcs
	* @param end the upper bound of the range of st federal new ndcs (not inclusive)
	* @return the range of st federal new ndcs
	*/
	public static List<StFederalNewNdc> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st federal new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st federal new ndcs
	* @param end the upper bound of the range of st federal new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st federal new ndcs
	*/
	public static List<StFederalNewNdc> findAll(int start, int end,
		OrderByComparator<StFederalNewNdc> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st federal new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st federal new ndcs
	* @param end the upper bound of the range of st federal new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st federal new ndcs
	*/
	public static List<StFederalNewNdc> findAll(int start, int end,
		OrderByComparator<StFederalNewNdc> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st federal new ndcs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st federal new ndcs.
	*
	* @return the number of st federal new ndcs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StFederalNewNdcPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StFederalNewNdcPersistence, StFederalNewNdcPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StFederalNewNdcPersistence.class);
}