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

import com.stpl.app.parttwo.exception.NoSuchVwReturnReserveException;
import com.stpl.app.parttwo.model.VwReturnReserve;

/**
 * The persistence interface for the vw return reserve service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.VwReturnReservePersistenceImpl
 * @see VwReturnReserveUtil
 * @generated
 */
@ProviderType
public interface VwReturnReservePersistence extends BasePersistence<VwReturnReserve> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwReturnReserveUtil} to access the vw return reserve persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw return reserve in the entity cache if it is enabled.
	*
	* @param vwReturnReserve the vw return reserve
	*/
	public void cacheResult(VwReturnReserve vwReturnReserve);

	/**
	* Caches the vw return reserves in the entity cache if it is enabled.
	*
	* @param vwReturnReserves the vw return reserves
	*/
	public void cacheResult(java.util.List<VwReturnReserve> vwReturnReserves);

	/**
	* Creates a new vw return reserve with the primary key. Does not add the vw return reserve to the database.
	*
	* @param returnReserveSid the primary key for the new vw return reserve
	* @return the new vw return reserve
	*/
	public VwReturnReserve create(int returnReserveSid);

	/**
	* Removes the vw return reserve with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param returnReserveSid the primary key of the vw return reserve
	* @return the vw return reserve that was removed
	* @throws NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
	*/
	public VwReturnReserve remove(int returnReserveSid)
		throws NoSuchVwReturnReserveException;

	public VwReturnReserve updateImpl(VwReturnReserve vwReturnReserve);

	/**
	* Returns the vw return reserve with the primary key or throws a {@link NoSuchVwReturnReserveException} if it could not be found.
	*
	* @param returnReserveSid the primary key of the vw return reserve
	* @return the vw return reserve
	* @throws NoSuchVwReturnReserveException if a vw return reserve with the primary key could not be found
	*/
	public VwReturnReserve findByPrimaryKey(int returnReserveSid)
		throws NoSuchVwReturnReserveException;

	/**
	* Returns the vw return reserve with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param returnReserveSid the primary key of the vw return reserve
	* @return the vw return reserve, or <code>null</code> if a vw return reserve with the primary key could not be found
	*/
	public VwReturnReserve fetchByPrimaryKey(int returnReserveSid);

	@Override
	public java.util.Map<java.io.Serializable, VwReturnReserve> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw return reserves.
	*
	* @return the vw return reserves
	*/
	public java.util.List<VwReturnReserve> findAll();

	/**
	* Returns a range of all the vw return reserves.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw return reserves
	* @param end the upper bound of the range of vw return reserves (not inclusive)
	* @return the range of vw return reserves
	*/
	public java.util.List<VwReturnReserve> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vw return reserves.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw return reserves
	* @param end the upper bound of the range of vw return reserves (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw return reserves
	*/
	public java.util.List<VwReturnReserve> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwReturnReserve> orderByComparator);

	/**
	* Returns an ordered range of all the vw return reserves.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw return reserves
	* @param end the upper bound of the range of vw return reserves (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw return reserves
	*/
	public java.util.List<VwReturnReserve> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwReturnReserve> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw return reserves from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw return reserves.
	*
	* @return the number of vw return reserves
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}