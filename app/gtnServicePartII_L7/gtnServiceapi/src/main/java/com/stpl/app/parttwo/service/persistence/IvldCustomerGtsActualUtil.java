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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.parttwo.model.IvldCustomerGtsActual;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld customer gts actual service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.IvldCustomerGtsActualPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCustomerGtsActualPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldCustomerGtsActualPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldCustomerGtsActualUtil {
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
	public static void clearCache(IvldCustomerGtsActual ivldCustomerGtsActual) {
		getPersistence().clearCache(ivldCustomerGtsActual);
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
	public static List<IvldCustomerGtsActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldCustomerGtsActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldCustomerGtsActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldCustomerGtsActual> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldCustomerGtsActual update(
		IvldCustomerGtsActual ivldCustomerGtsActual) {
		return getPersistence().update(ivldCustomerGtsActual);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldCustomerGtsActual update(
		IvldCustomerGtsActual ivldCustomerGtsActual,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldCustomerGtsActual, serviceContext);
	}

	/**
	* Caches the ivld customer gts actual in the entity cache if it is enabled.
	*
	* @param ivldCustomerGtsActual the ivld customer gts actual
	*/
	public static void cacheResult(IvldCustomerGtsActual ivldCustomerGtsActual) {
		getPersistence().cacheResult(ivldCustomerGtsActual);
	}

	/**
	* Caches the ivld customer gts actuals in the entity cache if it is enabled.
	*
	* @param ivldCustomerGtsActuals the ivld customer gts actuals
	*/
	public static void cacheResult(
		List<IvldCustomerGtsActual> ivldCustomerGtsActuals) {
		getPersistence().cacheResult(ivldCustomerGtsActuals);
	}

	/**
	* Creates a new ivld customer gts actual with the primary key. Does not add the ivld customer gts actual to the database.
	*
	* @param ivldCustomerGtsActualSid the primary key for the new ivld customer gts actual
	* @return the new ivld customer gts actual
	*/
	public static IvldCustomerGtsActual create(int ivldCustomerGtsActualSid) {
		return getPersistence().create(ivldCustomerGtsActualSid);
	}

	/**
	* Removes the ivld customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
	* @return the ivld customer gts actual that was removed
	* @throws NoSuchIvldCustomerGtsActualException if a ivld customer gts actual with the primary key could not be found
	*/
	public static IvldCustomerGtsActual remove(int ivldCustomerGtsActualSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldCustomerGtsActualException {
		return getPersistence().remove(ivldCustomerGtsActualSid);
	}

	public static IvldCustomerGtsActual updateImpl(
		IvldCustomerGtsActual ivldCustomerGtsActual) {
		return getPersistence().updateImpl(ivldCustomerGtsActual);
	}

	/**
	* Returns the ivld customer gts actual with the primary key or throws a {@link NoSuchIvldCustomerGtsActualException} if it could not be found.
	*
	* @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
	* @return the ivld customer gts actual
	* @throws NoSuchIvldCustomerGtsActualException if a ivld customer gts actual with the primary key could not be found
	*/
	public static IvldCustomerGtsActual findByPrimaryKey(
		int ivldCustomerGtsActualSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldCustomerGtsActualException {
		return getPersistence().findByPrimaryKey(ivldCustomerGtsActualSid);
	}

	/**
	* Returns the ivld customer gts actual with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
	* @return the ivld customer gts actual, or <code>null</code> if a ivld customer gts actual with the primary key could not be found
	*/
	public static IvldCustomerGtsActual fetchByPrimaryKey(
		int ivldCustomerGtsActualSid) {
		return getPersistence().fetchByPrimaryKey(ivldCustomerGtsActualSid);
	}

	public static java.util.Map<java.io.Serializable, IvldCustomerGtsActual> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld customer gts actuals.
	*
	* @return the ivld customer gts actuals
	*/
	public static List<IvldCustomerGtsActual> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld customer gts actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld customer gts actuals
	* @param end the upper bound of the range of ivld customer gts actuals (not inclusive)
	* @return the range of ivld customer gts actuals
	*/
	public static List<IvldCustomerGtsActual> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld customer gts actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld customer gts actuals
	* @param end the upper bound of the range of ivld customer gts actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld customer gts actuals
	*/
	public static List<IvldCustomerGtsActual> findAll(int start, int end,
		OrderByComparator<IvldCustomerGtsActual> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld customer gts actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld customer gts actuals
	* @param end the upper bound of the range of ivld customer gts actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld customer gts actuals
	*/
	public static List<IvldCustomerGtsActual> findAll(int start, int end,
		OrderByComparator<IvldCustomerGtsActual> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld customer gts actuals from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld customer gts actuals.
	*
	* @return the number of ivld customer gts actuals
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldCustomerGtsActualPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldCustomerGtsActualPersistence, IvldCustomerGtsActualPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldCustomerGtsActualPersistence.class);
}