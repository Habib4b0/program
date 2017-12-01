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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.exception.NoSuchChActualSalesException;
import com.stpl.app.model.ChActualSales;

/**
 * The persistence interface for the ch actual sales service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ChActualSalesPersistenceImpl
 * @see ChActualSalesUtil
 * @generated
 */
@ProviderType
public interface ChActualSalesPersistence extends BasePersistence<ChActualSales> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChActualSalesUtil} to access the ch actual sales persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ch actual sales in the entity cache if it is enabled.
	*
	* @param chActualSales the ch actual sales
	*/
	public void cacheResult(ChActualSales chActualSales);

	/**
	* Caches the ch actual saleses in the entity cache if it is enabled.
	*
	* @param chActualSaleses the ch actual saleses
	*/
	public void cacheResult(java.util.List<ChActualSales> chActualSaleses);

	/**
	* Creates a new ch actual sales with the primary key. Does not add the ch actual sales to the database.
	*
	* @param chActualSalesPK the primary key for the new ch actual sales
	* @return the new ch actual sales
	*/
	public ChActualSales create(ChActualSalesPK chActualSalesPK);

	/**
	* Removes the ch actual sales with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chActualSalesPK the primary key of the ch actual sales
	* @return the ch actual sales that was removed
	* @throws NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
	*/
	public ChActualSales remove(ChActualSalesPK chActualSalesPK)
		throws NoSuchChActualSalesException;

	public ChActualSales updateImpl(ChActualSales chActualSales);

	/**
	* Returns the ch actual sales with the primary key or throws a {@link NoSuchChActualSalesException} if it could not be found.
	*
	* @param chActualSalesPK the primary key of the ch actual sales
	* @return the ch actual sales
	* @throws NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
	*/
	public ChActualSales findByPrimaryKey(ChActualSalesPK chActualSalesPK)
		throws NoSuchChActualSalesException;

	/**
	* Returns the ch actual sales with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chActualSalesPK the primary key of the ch actual sales
	* @return the ch actual sales, or <code>null</code> if a ch actual sales with the primary key could not be found
	*/
	public ChActualSales fetchByPrimaryKey(ChActualSalesPK chActualSalesPK);

	@Override
	public java.util.Map<java.io.Serializable, ChActualSales> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ch actual saleses.
	*
	* @return the ch actual saleses
	*/
	public java.util.List<ChActualSales> findAll();

	/**
	* Returns a range of all the ch actual saleses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch actual saleses
	* @param end the upper bound of the range of ch actual saleses (not inclusive)
	* @return the range of ch actual saleses
	*/
	public java.util.List<ChActualSales> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ch actual saleses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch actual saleses
	* @param end the upper bound of the range of ch actual saleses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ch actual saleses
	*/
	public java.util.List<ChActualSales> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChActualSales> orderByComparator);

	/**
	* Returns an ordered range of all the ch actual saleses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch actual saleses
	* @param end the upper bound of the range of ch actual saleses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ch actual saleses
	*/
	public java.util.List<ChActualSales> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChActualSales> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ch actual saleses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ch actual saleses.
	*
	* @return the number of ch actual saleses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}