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

import com.stpl.app.parttwo.exception.NoSuchVwIvldReturnReserveException;
import com.stpl.app.parttwo.model.VwIvldReturnReserve;

/**
 * The persistence interface for the vw ivld return reserve service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.VwIvldReturnReservePersistenceImpl
 * @see VwIvldReturnReserveUtil
 * @generated
 */
@ProviderType
public interface VwIvldReturnReservePersistence extends BasePersistence<VwIvldReturnReserve> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwIvldReturnReserveUtil} to access the vw ivld return reserve persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw ivld return reserve in the entity cache if it is enabled.
	*
	* @param vwIvldReturnReserve the vw ivld return reserve
	*/
	public void cacheResult(VwIvldReturnReserve vwIvldReturnReserve);

	/**
	* Caches the vw ivld return reserves in the entity cache if it is enabled.
	*
	* @param vwIvldReturnReserves the vw ivld return reserves
	*/
	public void cacheResult(
		java.util.List<VwIvldReturnReserve> vwIvldReturnReserves);

	/**
	* Creates a new vw ivld return reserve with the primary key. Does not add the vw ivld return reserve to the database.
	*
	* @param ivldReturnReserveSid the primary key for the new vw ivld return reserve
	* @return the new vw ivld return reserve
	*/
	public VwIvldReturnReserve create(int ivldReturnReserveSid);

	/**
	* Removes the vw ivld return reserve with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldReturnReserveSid the primary key of the vw ivld return reserve
	* @return the vw ivld return reserve that was removed
	* @throws NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
	*/
	public VwIvldReturnReserve remove(int ivldReturnReserveSid)
		throws NoSuchVwIvldReturnReserveException;

	public VwIvldReturnReserve updateImpl(
		VwIvldReturnReserve vwIvldReturnReserve);

	/**
	* Returns the vw ivld return reserve with the primary key or throws a {@link NoSuchVwIvldReturnReserveException} if it could not be found.
	*
	* @param ivldReturnReserveSid the primary key of the vw ivld return reserve
	* @return the vw ivld return reserve
	* @throws NoSuchVwIvldReturnReserveException if a vw ivld return reserve with the primary key could not be found
	*/
	public VwIvldReturnReserve findByPrimaryKey(int ivldReturnReserveSid)
		throws NoSuchVwIvldReturnReserveException;

	/**
	* Returns the vw ivld return reserve with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldReturnReserveSid the primary key of the vw ivld return reserve
	* @return the vw ivld return reserve, or <code>null</code> if a vw ivld return reserve with the primary key could not be found
	*/
	public VwIvldReturnReserve fetchByPrimaryKey(int ivldReturnReserveSid);

	@Override
	public java.util.Map<java.io.Serializable, VwIvldReturnReserve> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw ivld return reserves.
	*
	* @return the vw ivld return reserves
	*/
	public java.util.List<VwIvldReturnReserve> findAll();

	/**
	* Returns a range of all the vw ivld return reserves.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw ivld return reserves
	* @param end the upper bound of the range of vw ivld return reserves (not inclusive)
	* @return the range of vw ivld return reserves
	*/
	public java.util.List<VwIvldReturnReserve> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vw ivld return reserves.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw ivld return reserves
	* @param end the upper bound of the range of vw ivld return reserves (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw ivld return reserves
	*/
	public java.util.List<VwIvldReturnReserve> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwIvldReturnReserve> orderByComparator);

	/**
	* Returns an ordered range of all the vw ivld return reserves.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw ivld return reserves
	* @param end the upper bound of the range of vw ivld return reserves (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw ivld return reserves
	*/
	public java.util.List<VwIvldReturnReserve> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwIvldReturnReserve> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw ivld return reserves from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw ivld return reserves.
	*
	* @return the number of vw ivld return reserves
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}