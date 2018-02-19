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

import com.stpl.app.model.StNewNdc;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st new ndc service. This utility wraps {@link com.stpl.app.service.persistence.impl.StNewNdcPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNewNdcPersistence
 * @see com.stpl.app.service.persistence.impl.StNewNdcPersistenceImpl
 * @generated
 */
@ProviderType
public class StNewNdcUtil {
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
	public static void clearCache(StNewNdc stNewNdc) {
		getPersistence().clearCache(stNewNdc);
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
	public static List<StNewNdc> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StNewNdc> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StNewNdc> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StNewNdc> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StNewNdc update(StNewNdc stNewNdc) {
		return getPersistence().update(stNewNdc);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StNewNdc update(StNewNdc stNewNdc,
		ServiceContext serviceContext) {
		return getPersistence().update(stNewNdc, serviceContext);
	}

	/**
	* Caches the st new ndc in the entity cache if it is enabled.
	*
	* @param stNewNdc the st new ndc
	*/
	public static void cacheResult(StNewNdc stNewNdc) {
		getPersistence().cacheResult(stNewNdc);
	}

	/**
	* Caches the st new ndcs in the entity cache if it is enabled.
	*
	* @param stNewNdcs the st new ndcs
	*/
	public static void cacheResult(List<StNewNdc> stNewNdcs) {
		getPersistence().cacheResult(stNewNdcs);
	}

	/**
	* Creates a new st new ndc with the primary key. Does not add the st new ndc to the database.
	*
	* @param stNewNdcPK the primary key for the new st new ndc
	* @return the new st new ndc
	*/
	public static StNewNdc create(StNewNdcPK stNewNdcPK) {
		return getPersistence().create(stNewNdcPK);
	}

	/**
	* Removes the st new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNewNdcPK the primary key of the st new ndc
	* @return the st new ndc that was removed
	* @throws NoSuchStNewNdcException if a st new ndc with the primary key could not be found
	*/
	public static StNewNdc remove(StNewNdcPK stNewNdcPK)
		throws com.stpl.app.exception.NoSuchStNewNdcException {
		return getPersistence().remove(stNewNdcPK);
	}

	public static StNewNdc updateImpl(StNewNdc stNewNdc) {
		return getPersistence().updateImpl(stNewNdc);
	}

	/**
	* Returns the st new ndc with the primary key or throws a {@link NoSuchStNewNdcException} if it could not be found.
	*
	* @param stNewNdcPK the primary key of the st new ndc
	* @return the st new ndc
	* @throws NoSuchStNewNdcException if a st new ndc with the primary key could not be found
	*/
	public static StNewNdc findByPrimaryKey(StNewNdcPK stNewNdcPK)
		throws com.stpl.app.exception.NoSuchStNewNdcException {
		return getPersistence().findByPrimaryKey(stNewNdcPK);
	}

	/**
	* Returns the st new ndc with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNewNdcPK the primary key of the st new ndc
	* @return the st new ndc, or <code>null</code> if a st new ndc with the primary key could not be found
	*/
	public static StNewNdc fetchByPrimaryKey(StNewNdcPK stNewNdcPK) {
		return getPersistence().fetchByPrimaryKey(stNewNdcPK);
	}

	public static java.util.Map<java.io.Serializable, StNewNdc> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st new ndcs.
	*
	* @return the st new ndcs
	*/
	public static List<StNewNdc> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st new ndcs
	* @param end the upper bound of the range of st new ndcs (not inclusive)
	* @return the range of st new ndcs
	*/
	public static List<StNewNdc> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st new ndcs
	* @param end the upper bound of the range of st new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st new ndcs
	*/
	public static List<StNewNdc> findAll(int start, int end,
		OrderByComparator<StNewNdc> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st new ndcs
	* @param end the upper bound of the range of st new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st new ndcs
	*/
	public static List<StNewNdc> findAll(int start, int end,
		OrderByComparator<StNewNdc> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st new ndcs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st new ndcs.
	*
	* @return the number of st new ndcs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StNewNdcPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StNewNdcPersistence, StNewNdcPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StNewNdcPersistence.class);
}