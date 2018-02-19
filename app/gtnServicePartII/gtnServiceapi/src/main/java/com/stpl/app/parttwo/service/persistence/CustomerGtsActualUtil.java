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

import com.stpl.app.parttwo.model.CustomerGtsActual;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the customer gts actual service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.CustomerGtsActualPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomerGtsActualPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.CustomerGtsActualPersistenceImpl
 * @generated
 */
@ProviderType
public class CustomerGtsActualUtil {
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
	public static void clearCache(CustomerGtsActual customerGtsActual) {
		getPersistence().clearCache(customerGtsActual);
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
	public static List<CustomerGtsActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CustomerGtsActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CustomerGtsActual> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CustomerGtsActual> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CustomerGtsActual update(CustomerGtsActual customerGtsActual) {
		return getPersistence().update(customerGtsActual);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CustomerGtsActual update(
		CustomerGtsActual customerGtsActual, ServiceContext serviceContext) {
		return getPersistence().update(customerGtsActual, serviceContext);
	}

	/**
	* Caches the customer gts actual in the entity cache if it is enabled.
	*
	* @param customerGtsActual the customer gts actual
	*/
	public static void cacheResult(CustomerGtsActual customerGtsActual) {
		getPersistence().cacheResult(customerGtsActual);
	}

	/**
	* Caches the customer gts actuals in the entity cache if it is enabled.
	*
	* @param customerGtsActuals the customer gts actuals
	*/
	public static void cacheResult(List<CustomerGtsActual> customerGtsActuals) {
		getPersistence().cacheResult(customerGtsActuals);
	}

	/**
	* Creates a new customer gts actual with the primary key. Does not add the customer gts actual to the database.
	*
	* @param customerGtsActualSid the primary key for the new customer gts actual
	* @return the new customer gts actual
	*/
	public static CustomerGtsActual create(int customerGtsActualSid) {
		return getPersistence().create(customerGtsActualSid);
	}

	/**
	* Removes the customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customerGtsActualSid the primary key of the customer gts actual
	* @return the customer gts actual that was removed
	* @throws NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
	*/
	public static CustomerGtsActual remove(int customerGtsActualSid)
		throws com.stpl.app.parttwo.exception.NoSuchCustomerGtsActualException {
		return getPersistence().remove(customerGtsActualSid);
	}

	public static CustomerGtsActual updateImpl(
		CustomerGtsActual customerGtsActual) {
		return getPersistence().updateImpl(customerGtsActual);
	}

	/**
	* Returns the customer gts actual with the primary key or throws a {@link NoSuchCustomerGtsActualException} if it could not be found.
	*
	* @param customerGtsActualSid the primary key of the customer gts actual
	* @return the customer gts actual
	* @throws NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
	*/
	public static CustomerGtsActual findByPrimaryKey(int customerGtsActualSid)
		throws com.stpl.app.parttwo.exception.NoSuchCustomerGtsActualException {
		return getPersistence().findByPrimaryKey(customerGtsActualSid);
	}

	/**
	* Returns the customer gts actual with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param customerGtsActualSid the primary key of the customer gts actual
	* @return the customer gts actual, or <code>null</code> if a customer gts actual with the primary key could not be found
	*/
	public static CustomerGtsActual fetchByPrimaryKey(int customerGtsActualSid) {
		return getPersistence().fetchByPrimaryKey(customerGtsActualSid);
	}

	public static java.util.Map<java.io.Serializable, CustomerGtsActual> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the customer gts actuals.
	*
	* @return the customer gts actuals
	*/
	public static List<CustomerGtsActual> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the customer gts actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of customer gts actuals
	* @param end the upper bound of the range of customer gts actuals (not inclusive)
	* @return the range of customer gts actuals
	*/
	public static List<CustomerGtsActual> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the customer gts actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of customer gts actuals
	* @param end the upper bound of the range of customer gts actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of customer gts actuals
	*/
	public static List<CustomerGtsActual> findAll(int start, int end,
		OrderByComparator<CustomerGtsActual> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the customer gts actuals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of customer gts actuals
	* @param end the upper bound of the range of customer gts actuals (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of customer gts actuals
	*/
	public static List<CustomerGtsActual> findAll(int start, int end,
		OrderByComparator<CustomerGtsActual> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the customer gts actuals from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of customer gts actuals.
	*
	* @return the number of customer gts actuals
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CustomerGtsActualPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CustomerGtsActualPersistence, CustomerGtsActualPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CustomerGtsActualPersistence.class);
}