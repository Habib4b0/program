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

import com.stpl.app.model.IvldGlBalance;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ivld gl balance service. This utility wraps {@link com.stpl.app.service.persistence.impl.IvldGlBalancePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldGlBalancePersistence
 * @see com.stpl.app.service.persistence.impl.IvldGlBalancePersistenceImpl
 * @generated
 */
@ProviderType
public class IvldGlBalanceUtil {
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
	public static void clearCache(IvldGlBalance ivldGlBalance) {
		getPersistence().clearCache(ivldGlBalance);
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
	public static List<IvldGlBalance> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IvldGlBalance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IvldGlBalance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IvldGlBalance> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IvldGlBalance update(IvldGlBalance ivldGlBalance) {
		return getPersistence().update(ivldGlBalance);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IvldGlBalance update(IvldGlBalance ivldGlBalance,
		ServiceContext serviceContext) {
		return getPersistence().update(ivldGlBalance, serviceContext);
	}

	/**
	* Caches the ivld gl balance in the entity cache if it is enabled.
	*
	* @param ivldGlBalance the ivld gl balance
	*/
	public static void cacheResult(IvldGlBalance ivldGlBalance) {
		getPersistence().cacheResult(ivldGlBalance);
	}

	/**
	* Caches the ivld gl balances in the entity cache if it is enabled.
	*
	* @param ivldGlBalances the ivld gl balances
	*/
	public static void cacheResult(List<IvldGlBalance> ivldGlBalances) {
		getPersistence().cacheResult(ivldGlBalances);
	}

	/**
	* Creates a new ivld gl balance with the primary key. Does not add the ivld gl balance to the database.
	*
	* @param ivldGlBalanceSid the primary key for the new ivld gl balance
	* @return the new ivld gl balance
	*/
	public static IvldGlBalance create(int ivldGlBalanceSid) {
		return getPersistence().create(ivldGlBalanceSid);
	}

	/**
	* Removes the ivld gl balance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldGlBalanceSid the primary key of the ivld gl balance
	* @return the ivld gl balance that was removed
	* @throws NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
	*/
	public static IvldGlBalance remove(int ivldGlBalanceSid)
		throws com.stpl.app.exception.NoSuchIvldGlBalanceException {
		return getPersistence().remove(ivldGlBalanceSid);
	}

	public static IvldGlBalance updateImpl(IvldGlBalance ivldGlBalance) {
		return getPersistence().updateImpl(ivldGlBalance);
	}

	/**
	* Returns the ivld gl balance with the primary key or throws a {@link NoSuchIvldGlBalanceException} if it could not be found.
	*
	* @param ivldGlBalanceSid the primary key of the ivld gl balance
	* @return the ivld gl balance
	* @throws NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
	*/
	public static IvldGlBalance findByPrimaryKey(int ivldGlBalanceSid)
		throws com.stpl.app.exception.NoSuchIvldGlBalanceException {
		return getPersistence().findByPrimaryKey(ivldGlBalanceSid);
	}

	/**
	* Returns the ivld gl balance with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldGlBalanceSid the primary key of the ivld gl balance
	* @return the ivld gl balance, or <code>null</code> if a ivld gl balance with the primary key could not be found
	*/
	public static IvldGlBalance fetchByPrimaryKey(int ivldGlBalanceSid) {
		return getPersistence().fetchByPrimaryKey(ivldGlBalanceSid);
	}

	public static java.util.Map<java.io.Serializable, IvldGlBalance> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ivld gl balances.
	*
	* @return the ivld gl balances
	*/
	public static List<IvldGlBalance> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ivld gl balances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld gl balances
	* @param end the upper bound of the range of ivld gl balances (not inclusive)
	* @return the range of ivld gl balances
	*/
	public static List<IvldGlBalance> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ivld gl balances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld gl balances
	* @param end the upper bound of the range of ivld gl balances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld gl balances
	*/
	public static List<IvldGlBalance> findAll(int start, int end,
		OrderByComparator<IvldGlBalance> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ivld gl balances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld gl balances
	* @param end the upper bound of the range of ivld gl balances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld gl balances
	*/
	public static List<IvldGlBalance> findAll(int start, int end,
		OrderByComparator<IvldGlBalance> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ivld gl balances from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ivld gl balances.
	*
	* @return the number of ivld gl balances
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IvldGlBalancePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IvldGlBalancePersistence, IvldGlBalancePersistence> _serviceTracker =
		ServiceTrackerFactory.open(IvldGlBalancePersistence.class);
}