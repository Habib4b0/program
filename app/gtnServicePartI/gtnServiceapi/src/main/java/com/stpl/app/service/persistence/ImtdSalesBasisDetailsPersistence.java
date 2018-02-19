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

import com.stpl.app.exception.NoSuchImtdSalesBasisDetailsException;
import com.stpl.app.model.ImtdSalesBasisDetails;

/**
 * The persistence interface for the imtd sales basis details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ImtdSalesBasisDetailsPersistenceImpl
 * @see ImtdSalesBasisDetailsUtil
 * @generated
 */
@ProviderType
public interface ImtdSalesBasisDetailsPersistence extends BasePersistence<ImtdSalesBasisDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImtdSalesBasisDetailsUtil} to access the imtd sales basis details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the imtd sales basis details in the entity cache if it is enabled.
	*
	* @param imtdSalesBasisDetails the imtd sales basis details
	*/
	public void cacheResult(ImtdSalesBasisDetails imtdSalesBasisDetails);

	/**
	* Caches the imtd sales basis detailses in the entity cache if it is enabled.
	*
	* @param imtdSalesBasisDetailses the imtd sales basis detailses
	*/
	public void cacheResult(
		java.util.List<ImtdSalesBasisDetails> imtdSalesBasisDetailses);

	/**
	* Creates a new imtd sales basis details with the primary key. Does not add the imtd sales basis details to the database.
	*
	* @param imtdSalesBasisDetailsSid the primary key for the new imtd sales basis details
	* @return the new imtd sales basis details
	*/
	public ImtdSalesBasisDetails create(int imtdSalesBasisDetailsSid);

	/**
	* Removes the imtd sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
	* @return the imtd sales basis details that was removed
	* @throws NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
	*/
	public ImtdSalesBasisDetails remove(int imtdSalesBasisDetailsSid)
		throws NoSuchImtdSalesBasisDetailsException;

	public ImtdSalesBasisDetails updateImpl(
		ImtdSalesBasisDetails imtdSalesBasisDetails);

	/**
	* Returns the imtd sales basis details with the primary key or throws a {@link NoSuchImtdSalesBasisDetailsException} if it could not be found.
	*
	* @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
	* @return the imtd sales basis details
	* @throws NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
	*/
	public ImtdSalesBasisDetails findByPrimaryKey(int imtdSalesBasisDetailsSid)
		throws NoSuchImtdSalesBasisDetailsException;

	/**
	* Returns the imtd sales basis details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
	* @return the imtd sales basis details, or <code>null</code> if a imtd sales basis details with the primary key could not be found
	*/
	public ImtdSalesBasisDetails fetchByPrimaryKey(int imtdSalesBasisDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, ImtdSalesBasisDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the imtd sales basis detailses.
	*
	* @return the imtd sales basis detailses
	*/
	public java.util.List<ImtdSalesBasisDetails> findAll();

	/**
	* Returns a range of all the imtd sales basis detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd sales basis detailses
	* @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
	* @return the range of imtd sales basis detailses
	*/
	public java.util.List<ImtdSalesBasisDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the imtd sales basis detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd sales basis detailses
	* @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd sales basis detailses
	*/
	public java.util.List<ImtdSalesBasisDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdSalesBasisDetails> orderByComparator);

	/**
	* Returns an ordered range of all the imtd sales basis detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd sales basis detailses
	* @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd sales basis detailses
	*/
	public java.util.List<ImtdSalesBasisDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImtdSalesBasisDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the imtd sales basis detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of imtd sales basis detailses.
	*
	* @return the number of imtd sales basis detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}