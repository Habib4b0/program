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

import com.stpl.app.parttwo.model.IvldAccrualInbound;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld accrual inbound service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.IvldAccrualInboundPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldAccrualInboundPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldAccrualInboundPersistenceImpl
 * @generated
 */
@ProviderType
public class IvldAccrualInboundUtil {
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
	public static void clearCache(IvldAccrualInbound ivldAccrualInbound) {
		getPersistence().clearCache(ivldAccrualInbound);
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
	public static List<IvldAccrualInbound> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldAccrualInbound> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldAccrualInbound> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldAccrualInbound> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldAccrualInbound update(
		IvldAccrualInbound ivldAccrualInbound) {
		return getPersistence().update(ivldAccrualInbound);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldAccrualInbound update(
		IvldAccrualInbound ivldAccrualInbound, ServiceContext serviceContext) {
		return getPersistence().update(ivldAccrualInbound, serviceContext);
	}

	/**
	* Caches the ivld accrual inbound in the entity cache if it is enabled.
	*
	* @param ivldAccrualInbound the ivld accrual inbound
	*/
	public static void cacheResult(IvldAccrualInbound ivldAccrualInbound) {
		getPersistence().cacheResult(ivldAccrualInbound);
	}

	/**
	* Caches the ivld accrual inbounds in the entity cache if it is enabled.
	*
	* @param ivldAccrualInbounds the ivld accrual inbounds
	*/
	public static void cacheResult(List<IvldAccrualInbound> ivldAccrualInbounds) {
		getPersistence().cacheResult(ivldAccrualInbounds);
	}

	/**
	* Creates a new ivld accrual inbound with the primary key. Does not add the ivld accrual inbound to the database.
	*
	* @param ivldAccrualInboundSid the primary key for the new ivld accrual inbound
	* @return the new ivld accrual inbound
	*/
	public static IvldAccrualInbound create(int ivldAccrualInboundSid) {
		return getPersistence().create(ivldAccrualInboundSid);
	}

	/**
	* Removes the ivld accrual inbound with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
	* @return the ivld accrual inbound that was removed
	* @throws NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
	*/
	public static IvldAccrualInbound remove(int ivldAccrualInboundSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldAccrualInboundException {
		return getPersistence().remove(ivldAccrualInboundSid);
	}

	public static IvldAccrualInbound updateImpl(
		IvldAccrualInbound ivldAccrualInbound) {
		return getPersistence().updateImpl(ivldAccrualInbound);
	}

	/**
	* Returns the ivld accrual inbound with the primary key or throws a {@link NoSuchIvldAccrualInboundException} if it could not be found.
	*
	* @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
	* @return the ivld accrual inbound
	* @throws NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
	*/
	public static IvldAccrualInbound findByPrimaryKey(int ivldAccrualInboundSid)
		throws com.stpl.app.parttwo.exception.NoSuchIvldAccrualInboundException {
		return getPersistence().findByPrimaryKey(ivldAccrualInboundSid);
	}

	/**
	* Returns the ivld accrual inbound with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
	* @return the ivld accrual inbound, or <code>null</code> if a ivld accrual inbound with the primary key could not be found
	*/
	public static IvldAccrualInbound fetchByPrimaryKey(
		int ivldAccrualInboundSid) {
		return getPersistence().fetchByPrimaryKey(ivldAccrualInboundSid);
	}

	public static java.util.Map<java.io.Serializable, IvldAccrualInbound> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld accrual inbounds.
	*
	* @return the ivld accrual inbounds
	*/
	public static List<IvldAccrualInbound> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld accrual inbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld accrual inbounds
	* @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
	* @return the range of ivld accrual inbounds
	*/
	public static List<IvldAccrualInbound> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld accrual inbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld accrual inbounds
	* @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld accrual inbounds
	*/
	public static List<IvldAccrualInbound> findAll(int start, int end,
		OrderByComparator<IvldAccrualInbound> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld accrual inbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld accrual inbounds
	* @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld accrual inbounds
	*/
	public static List<IvldAccrualInbound> findAll(int start, int end,
		OrderByComparator<IvldAccrualInbound> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld accrual inbounds from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld accrual inbounds.
	*
	* @return the number of ivld accrual inbounds
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldAccrualInboundPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldAccrualInboundPersistence, IvldAccrualInboundPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldAccrualInboundPersistence.class);
}