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

import com.stpl.app.exception.NoSuchImtdPsDetailsException;
import com.stpl.app.model.ImtdPsDetails;

/**
 * The persistence interface for the imtd ps details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ImtdPsDetailsPersistenceImpl
 * @see ImtdPsDetailsUtil
 * @generated
 */
@ProviderType
public interface ImtdPsDetailsPersistence extends BasePersistence<ImtdPsDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImtdPsDetailsUtil} to access the imtd ps details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the imtd ps details in the entity cache if it is enabled.
	*
	* @param imtdPsDetails the imtd ps details
	*/
	public void cacheResult(ImtdPsDetails imtdPsDetails);

	/**
	* Caches the imtd ps detailses in the entity cache if it is enabled.
	*
	* @param imtdPsDetailses the imtd ps detailses
	*/
	public void cacheResult(java.util.List<ImtdPsDetails> imtdPsDetailses);

	/**
	* Creates a new imtd ps details with the primary key. Does not add the imtd ps details to the database.
	*
	* @param imtdPsDetailsSid the primary key for the new imtd ps details
	* @return the new imtd ps details
	*/
	public ImtdPsDetails create(int imtdPsDetailsSid);

	/**
	* Removes the imtd ps details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdPsDetailsSid the primary key of the imtd ps details
	* @return the imtd ps details that was removed
	* @throws NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
	*/
	public ImtdPsDetails remove(int imtdPsDetailsSid)
		throws NoSuchImtdPsDetailsException;

	public ImtdPsDetails updateImpl(ImtdPsDetails imtdPsDetails);

	/**
	* Returns the imtd ps details with the primary key or throws a {@link NoSuchImtdPsDetailsException} if it could not be found.
	*
	* @param imtdPsDetailsSid the primary key of the imtd ps details
	* @return the imtd ps details
	* @throws NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
	*/
	public ImtdPsDetails findByPrimaryKey(int imtdPsDetailsSid)
		throws NoSuchImtdPsDetailsException;

	/**
	* Returns the imtd ps details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdPsDetailsSid the primary key of the imtd ps details
	* @return the imtd ps details, or <code>null</code> if a imtd ps details with the primary key could not be found
	*/
	public ImtdPsDetails fetchByPrimaryKey(int imtdPsDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, ImtdPsDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the imtd ps detailses.
	*
	* @return the imtd ps detailses
	*/
	public java.util.List<ImtdPsDetails> findAll();

	/**
	* Returns a range of all the imtd ps detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ps detailses
	* @param end the upper bound of the range of imtd ps detailses (not inclusive)
	* @return the range of imtd ps detailses
	*/
	public java.util.List<ImtdPsDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the imtd ps detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ps detailses
	* @param end the upper bound of the range of imtd ps detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd ps detailses
	*/
	public java.util.List<ImtdPsDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdPsDetails> orderByComparator);

	/**
	* Returns an ordered range of all the imtd ps detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ps detailses
	* @param end the upper bound of the range of imtd ps detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd ps detailses
	*/
	public java.util.List<ImtdPsDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdPsDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the imtd ps detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of imtd ps detailses.
	*
	* @return the number of imtd ps detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}