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

import com.stpl.app.exception.NoSuchImtdItemPriceRebateDetailsException;
import com.stpl.app.model.ImtdItemPriceRebateDetails;

/**
 * The persistence interface for the imtd item price rebate details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ImtdItemPriceRebateDetailsPersistenceImpl
 * @see ImtdItemPriceRebateDetailsUtil
 * @generated
 */
@ProviderType
public interface ImtdItemPriceRebateDetailsPersistence extends BasePersistence<ImtdItemPriceRebateDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImtdItemPriceRebateDetailsUtil} to access the imtd item price rebate details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the imtd item price rebate details in the entity cache if it is enabled.
	*
	* @param imtdItemPriceRebateDetails the imtd item price rebate details
	*/
	public void cacheResult(
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails);

	/**
	* Caches the imtd item price rebate detailses in the entity cache if it is enabled.
	*
	* @param imtdItemPriceRebateDetailses the imtd item price rebate detailses
	*/
	public void cacheResult(
		java.util.List<ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses);

	/**
	* Creates a new imtd item price rebate details with the primary key. Does not add the imtd item price rebate details to the database.
	*
	* @param imtdItemPriceRebateSid the primary key for the new imtd item price rebate details
	* @return the new imtd item price rebate details
	*/
	public ImtdItemPriceRebateDetails create(int imtdItemPriceRebateSid);

	/**
	* Removes the imtd item price rebate details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
	* @return the imtd item price rebate details that was removed
	* @throws NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
	*/
	public ImtdItemPriceRebateDetails remove(int imtdItemPriceRebateSid)
		throws NoSuchImtdItemPriceRebateDetailsException;

	public ImtdItemPriceRebateDetails updateImpl(
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails);

	/**
	* Returns the imtd item price rebate details with the primary key or throws a {@link NoSuchImtdItemPriceRebateDetailsException} if it could not be found.
	*
	* @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
	* @return the imtd item price rebate details
	* @throws NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
	*/
	public ImtdItemPriceRebateDetails findByPrimaryKey(
		int imtdItemPriceRebateSid)
		throws NoSuchImtdItemPriceRebateDetailsException;

	/**
	* Returns the imtd item price rebate details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
	* @return the imtd item price rebate details, or <code>null</code> if a imtd item price rebate details with the primary key could not be found
	*/
	public ImtdItemPriceRebateDetails fetchByPrimaryKey(
		int imtdItemPriceRebateSid);

	@Override
	public java.util.Map<java.io.Serializable, ImtdItemPriceRebateDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the imtd item price rebate detailses.
	*
	* @return the imtd item price rebate detailses
	*/
	public java.util.List<ImtdItemPriceRebateDetails> findAll();

	/**
	* Returns a range of all the imtd item price rebate detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd item price rebate detailses
	* @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
	* @return the range of imtd item price rebate detailses
	*/
	public java.util.List<ImtdItemPriceRebateDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the imtd item price rebate detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd item price rebate detailses
	* @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd item price rebate detailses
	*/
	public java.util.List<ImtdItemPriceRebateDetails> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdItemPriceRebateDetails> orderByComparator);

	/**
	* Returns an ordered range of all the imtd item price rebate detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd item price rebate detailses
	* @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd item price rebate detailses
	*/
	public java.util.List<ImtdItemPriceRebateDetails> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdItemPriceRebateDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the imtd item price rebate detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of imtd item price rebate detailses.
	*
	* @return the number of imtd item price rebate detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}