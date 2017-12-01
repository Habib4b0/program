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

import com.stpl.app.exception.NoSuchTransactionModuleDetailsException;
import com.stpl.app.model.TransactionModuleDetails;

/**
 * The persistence interface for the transaction module details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.TransactionModuleDetailsPersistenceImpl
 * @see TransactionModuleDetailsUtil
 * @generated
 */
@ProviderType
public interface TransactionModuleDetailsPersistence extends BasePersistence<TransactionModuleDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TransactionModuleDetailsUtil} to access the transaction module details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the transaction module details in the entity cache if it is enabled.
	*
	* @param transactionModuleDetails the transaction module details
	*/
	public void cacheResult(TransactionModuleDetails transactionModuleDetails);

	/**
	* Caches the transaction module detailses in the entity cache if it is enabled.
	*
	* @param transactionModuleDetailses the transaction module detailses
	*/
	public void cacheResult(
		java.util.List<TransactionModuleDetails> transactionModuleDetailses);

	/**
	* Creates a new transaction module details with the primary key. Does not add the transaction module details to the database.
	*
	* @param transactionModuleDetailsSid the primary key for the new transaction module details
	* @return the new transaction module details
	*/
	public TransactionModuleDetails create(int transactionModuleDetailsSid);

	/**
	* Removes the transaction module details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleDetailsSid the primary key of the transaction module details
	* @return the transaction module details that was removed
	* @throws NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
	*/
	public TransactionModuleDetails remove(int transactionModuleDetailsSid)
		throws NoSuchTransactionModuleDetailsException;

	public TransactionModuleDetails updateImpl(
		TransactionModuleDetails transactionModuleDetails);

	/**
	* Returns the transaction module details with the primary key or throws a {@link NoSuchTransactionModuleDetailsException} if it could not be found.
	*
	* @param transactionModuleDetailsSid the primary key of the transaction module details
	* @return the transaction module details
	* @throws NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
	*/
	public TransactionModuleDetails findByPrimaryKey(
		int transactionModuleDetailsSid)
		throws NoSuchTransactionModuleDetailsException;

	/**
	* Returns the transaction module details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param transactionModuleDetailsSid the primary key of the transaction module details
	* @return the transaction module details, or <code>null</code> if a transaction module details with the primary key could not be found
	*/
	public TransactionModuleDetails fetchByPrimaryKey(
		int transactionModuleDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, TransactionModuleDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the transaction module detailses.
	*
	* @return the transaction module detailses
	*/
	public java.util.List<TransactionModuleDetails> findAll();

	/**
	* Returns a range of all the transaction module detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of transaction module detailses
	* @param end the upper bound of the range of transaction module detailses (not inclusive)
	* @return the range of transaction module detailses
	*/
	public java.util.List<TransactionModuleDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the transaction module detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of transaction module detailses
	* @param end the upper bound of the range of transaction module detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of transaction module detailses
	*/
	public java.util.List<TransactionModuleDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TransactionModuleDetails> orderByComparator);

	/**
	* Returns an ordered range of all the transaction module detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of transaction module detailses
	* @param end the upper bound of the range of transaction module detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of transaction module detailses
	*/
	public java.util.List<TransactionModuleDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TransactionModuleDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the transaction module detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of transaction module detailses.
	*
	* @return the number of transaction module detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}