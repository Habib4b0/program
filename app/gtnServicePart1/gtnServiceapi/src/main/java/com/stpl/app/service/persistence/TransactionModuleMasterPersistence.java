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

import com.stpl.app.exception.NoSuchTransactionModuleMasterException;
import com.stpl.app.model.TransactionModuleMaster;

/**
 * The persistence interface for the transaction module master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.TransactionModuleMasterPersistenceImpl
 * @see TransactionModuleMasterUtil
 * @generated
 */
@ProviderType
public interface TransactionModuleMasterPersistence extends BasePersistence<TransactionModuleMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TransactionModuleMasterUtil} to access the transaction module master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the transaction module master in the entity cache if it is enabled.
	*
	* @param transactionModuleMaster the transaction module master
	*/
	public void cacheResult(TransactionModuleMaster transactionModuleMaster);

	/**
	* Caches the transaction module masters in the entity cache if it is enabled.
	*
	* @param transactionModuleMasters the transaction module masters
	*/
	public void cacheResult(
		java.util.List<TransactionModuleMaster> transactionModuleMasters);

	/**
	* Creates a new transaction module master with the primary key. Does not add the transaction module master to the database.
	*
	* @param transactionModuleMasterSid the primary key for the new transaction module master
	* @return the new transaction module master
	*/
	public TransactionModuleMaster create(int transactionModuleMasterSid);

	/**
	* Removes the transaction module master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleMasterSid the primary key of the transaction module master
	* @return the transaction module master that was removed
	* @throws NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
	*/
	public TransactionModuleMaster remove(int transactionModuleMasterSid)
		throws NoSuchTransactionModuleMasterException;

	public TransactionModuleMaster updateImpl(
		TransactionModuleMaster transactionModuleMaster);

	/**
	* Returns the transaction module master with the primary key or throws a {@link NoSuchTransactionModuleMasterException} if it could not be found.
	*
	* @param transactionModuleMasterSid the primary key of the transaction module master
	* @return the transaction module master
	* @throws NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
	*/
	public TransactionModuleMaster findByPrimaryKey(
		int transactionModuleMasterSid)
		throws NoSuchTransactionModuleMasterException;

	/**
	* Returns the transaction module master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param transactionModuleMasterSid the primary key of the transaction module master
	* @return the transaction module master, or <code>null</code> if a transaction module master with the primary key could not be found
	*/
	public TransactionModuleMaster fetchByPrimaryKey(
		int transactionModuleMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, TransactionModuleMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the transaction module masters.
	*
	* @return the transaction module masters
	*/
	public java.util.List<TransactionModuleMaster> findAll();

	/**
	* Returns a range of all the transaction module masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of transaction module masters
	* @param end the upper bound of the range of transaction module masters (not inclusive)
	* @return the range of transaction module masters
	*/
	public java.util.List<TransactionModuleMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the transaction module masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of transaction module masters
	* @param end the upper bound of the range of transaction module masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of transaction module masters
	*/
	public java.util.List<TransactionModuleMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TransactionModuleMaster> orderByComparator);

	/**
	* Returns an ordered range of all the transaction module masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of transaction module masters
	* @param end the upper bound of the range of transaction module masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of transaction module masters
	*/
	public java.util.List<TransactionModuleMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TransactionModuleMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the transaction module masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of transaction module masters.
	*
	* @return the number of transaction module masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}