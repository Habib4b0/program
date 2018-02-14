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

import com.stpl.app.model.IvldDemandActual;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld demand actual service. This utility wraps {@link com.stpl.app.service.persistence.impl.IvldDemandActualPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldDemandActualPersistence
 * @see com.stpl.app.service.persistence.impl.IvldDemandActualPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldDemandActualUtil {
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
	public static void clearCache(IvldDemandActual ivldDemandActual) {
		getPersistence().clearCache(ivldDemandActual);
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
	public static List<IvldDemandActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldDemandActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldDemandActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldDemandActual> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldDemandActual update(IvldDemandActual ivldDemandActual) {
		return getPersistence().update(ivldDemandActual);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldDemandActual update(IvldDemandActual ivldDemandActual,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldDemandActual, serviceContext);
	}

	/**
	* Caches the ivld demand actual in the entity cache if it is enabled.
	*
	* @param ivldDemandActual the ivld demand actual
	*/
	public static void cacheResult(IvldDemandActual ivldDemandActual) {
		getPersistence().cacheResult(ivldDemandActual);
	}

	/**
	* Caches the ivld demand actuals in the entity cache if it is enabled.
	*
	* @param ivldDemandActuals the ivld demand actuals
	*/
	public static void cacheResult(List<IvldDemandActual> ivldDemandActuals) {
		getPersistence().cacheResult(ivldDemandActuals);
	}

	/**
	* Creates a new ivld demand actual with the primary key. Does not add the ivld demand actual to the database.
	*
	* @param ivldDemandActualSid the primary key for the new ivld demand actual
	* @return the new ivld demand actual
	*/
	public static IvldDemandActual create(int ivldDemandActualSid) {
		return getPersistence().create(ivldDemandActualSid);
	}

	/**
	* Removes the ivld demand actual with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldDemandActualSid the primary key of the ivld demand actual
	* @return the ivld demand actual that was removed
	* @throws NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
	*/
	public static IvldDemandActual remove(int ivldDemandActualSid)
		throws com.stpl.app.exception.NoSuchIvldDemandActualException {
		return getPersistence().remove(ivldDemandActualSid);
	}

	public static IvldDemandActual updateImpl(IvldDemandActual ivldDemandActual) {
		return getPersistence().updateImpl(ivldDemandActual);
	}

	/**
	* Returns the ivld demand actual with the primary key or throws a {@link NoSuchIvldDemandActualException} if it could not be found.
	*
	* @param ivldDemandActualSid the primary key of the ivld demand actual
	* @return the ivld demand actual
	* @throws NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
	*/
	public static IvldDemandActual findByPrimaryKey(int ivldDemandActualSid)
		throws com.stpl.app.exception.NoSuchIvldDemandActualException {
		return getPersistence().findByPrimaryKey(ivldDemandActualSid);
	}

	/**
	* Returns the ivld demand actual with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldDemandActualSid the primary key of the ivld demand actual
	* @return the ivld demand actual, or <code>null</code> if a ivld demand actual with the primary key could not be found
	*/
	public static IvldDemandActual fetchByPrimaryKey(int ivldDemandActualSid) {
		return getPersistence().fetchByPrimaryKey(ivldDemandActualSid);
	}

	public static java.util.Map<java.io.Serializable, IvldDemandActual> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld demand actuals.
	*
	* @return the ivld demand actuals
	*/
	public static List<IvldDemandActual> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld demand actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld demand actuals
	* @param end the upper bound of the range of ivld demand actuals (not inclusive)
	* @return the range of ivld demand actuals
	*/
	public static List<IvldDemandActual> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld demand actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld demand actuals
	* @param end the upper bound of the range of ivld demand actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld demand actuals
	*/
	public static List<IvldDemandActual> findAll(int start, int end,
		OrderByComparator<IvldDemandActual> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld demand actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld demand actuals
	* @param end the upper bound of the range of ivld demand actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld demand actuals
	*/
	public static List<IvldDemandActual> findAll(int start, int end,
		OrderByComparator<IvldDemandActual> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld demand actuals from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld demand actuals.
	*
	* @return the number of ivld demand actuals
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldDemandActualPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldDemandActualPersistence, IvldDemandActualPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldDemandActualPersistence.class);
}