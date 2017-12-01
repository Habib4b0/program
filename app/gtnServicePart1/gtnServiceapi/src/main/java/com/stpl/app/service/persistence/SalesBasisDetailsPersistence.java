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

import com.stpl.app.exception.NoSuchSalesBasisDetailsException;
import com.stpl.app.model.SalesBasisDetails;

/**
 * The persistence interface for the sales basis details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.SalesBasisDetailsPersistenceImpl
 * @see SalesBasisDetailsUtil
 * @generated
 */
@ProviderType
public interface SalesBasisDetailsPersistence extends BasePersistence<SalesBasisDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SalesBasisDetailsUtil} to access the sales basis details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the sales basis details in the entity cache if it is enabled.
	*
	* @param salesBasisDetails the sales basis details
	*/
	public void cacheResult(SalesBasisDetails salesBasisDetails);

	/**
	* Caches the sales basis detailses in the entity cache if it is enabled.
	*
	* @param salesBasisDetailses the sales basis detailses
	*/
	public void cacheResult(
		java.util.List<SalesBasisDetails> salesBasisDetailses);

	/**
	* Creates a new sales basis details with the primary key. Does not add the sales basis details to the database.
	*
	* @param salesBasisDetailsSid the primary key for the new sales basis details
	* @return the new sales basis details
	*/
	public SalesBasisDetails create(int salesBasisDetailsSid);

	/**
	* Removes the sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param salesBasisDetailsSid the primary key of the sales basis details
	* @return the sales basis details that was removed
	* @throws NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
	*/
	public SalesBasisDetails remove(int salesBasisDetailsSid)
		throws NoSuchSalesBasisDetailsException;

	public SalesBasisDetails updateImpl(SalesBasisDetails salesBasisDetails);

	/**
	* Returns the sales basis details with the primary key or throws a {@link NoSuchSalesBasisDetailsException} if it could not be found.
	*
	* @param salesBasisDetailsSid the primary key of the sales basis details
	* @return the sales basis details
	* @throws NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
	*/
	public SalesBasisDetails findByPrimaryKey(int salesBasisDetailsSid)
		throws NoSuchSalesBasisDetailsException;

	/**
	* Returns the sales basis details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param salesBasisDetailsSid the primary key of the sales basis details
	* @return the sales basis details, or <code>null</code> if a sales basis details with the primary key could not be found
	*/
	public SalesBasisDetails fetchByPrimaryKey(int salesBasisDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, SalesBasisDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sales basis detailses.
	*
	* @return the sales basis detailses
	*/
	public java.util.List<SalesBasisDetails> findAll();

	/**
	* Returns a range of all the sales basis detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales basis detailses
	* @param end the upper bound of the range of sales basis detailses (not inclusive)
	* @return the range of sales basis detailses
	*/
	public java.util.List<SalesBasisDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the sales basis detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales basis detailses
	* @param end the upper bound of the range of sales basis detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sales basis detailses
	*/
	public java.util.List<SalesBasisDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesBasisDetails> orderByComparator);

	/**
	* Returns an ordered range of all the sales basis detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales basis detailses
	* @param end the upper bound of the range of sales basis detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sales basis detailses
	*/
	public java.util.List<SalesBasisDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SalesBasisDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the sales basis detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sales basis detailses.
	*
	* @return the number of sales basis detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}