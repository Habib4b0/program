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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.parttwo.exception.NoSuchCustomerGtsActualException;
import com.stpl.app.parttwo.model.CustomerGtsActual;

/**
 * The persistence interface for the customer gts actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.CustomerGtsActualPersistenceImpl
 * @see CustomerGtsActualUtil
 * @generated
 */
@ProviderType
public interface CustomerGtsActualPersistence extends BasePersistence<CustomerGtsActual> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CustomerGtsActualUtil} to access the customer gts actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the customer gts actual in the entity cache if it is enabled.
	*
	* @param customerGtsActual the customer gts actual
	*/
	public void cacheResult(CustomerGtsActual customerGtsActual);

	/**
	* Caches the customer gts actuals in the entity cache if it is enabled.
	*
	* @param customerGtsActuals the customer gts actuals
	*/
	public void cacheResult(
		java.util.List<CustomerGtsActual> customerGtsActuals);

	/**
	* Creates a new customer gts actual with the primary key. Does not add the customer gts actual to the database.
	*
	* @param customerGtsActualSid the primary key for the new customer gts actual
	* @return the new customer gts actual
	*/
	public CustomerGtsActual create(int customerGtsActualSid);

	/**
	* Removes the customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customerGtsActualSid the primary key of the customer gts actual
	* @return the customer gts actual that was removed
	* @throws NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
	*/
	public CustomerGtsActual remove(int customerGtsActualSid)
		throws NoSuchCustomerGtsActualException;

	public CustomerGtsActual updateImpl(CustomerGtsActual customerGtsActual);

	/**
	* Returns the customer gts actual with the primary key or throws a {@link NoSuchCustomerGtsActualException} if it could not be found.
	*
	* @param customerGtsActualSid the primary key of the customer gts actual
	* @return the customer gts actual
	* @throws NoSuchCustomerGtsActualException if a customer gts actual with the primary key could not be found
	*/
	public CustomerGtsActual findByPrimaryKey(int customerGtsActualSid)
		throws NoSuchCustomerGtsActualException;

	/**
	* Returns the customer gts actual with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param customerGtsActualSid the primary key of the customer gts actual
	* @return the customer gts actual, or <code>null</code> if a customer gts actual with the primary key could not be found
	*/
	public CustomerGtsActual fetchByPrimaryKey(int customerGtsActualSid);

	@Override
	public java.util.Map<java.io.Serializable, CustomerGtsActual> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the customer gts actuals.
	*
	* @return the customer gts actuals
	*/
	public java.util.List<CustomerGtsActual> findAll();

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
	public java.util.List<CustomerGtsActual> findAll(int start, int end);

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
	public java.util.List<CustomerGtsActual> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomerGtsActual> orderByComparator);

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
	public java.util.List<CustomerGtsActual> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomerGtsActual> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the customer gts actuals from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of customer gts actuals.
	*
	* @return the number of customer gts actuals
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}