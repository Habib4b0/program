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

import com.stpl.app.exception.NoSuchRsContractException;
import com.stpl.app.model.RsContract;

/**
 * The persistence interface for the rs contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.RsContractPersistenceImpl
 * @see RsContractUtil
 * @generated
 */
@ProviderType
public interface RsContractPersistence extends BasePersistence<RsContract> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RsContractUtil} to access the rs contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the rs contract in the entity cache if it is enabled.
	*
	* @param rsContract the rs contract
	*/
	public void cacheResult(RsContract rsContract);

	/**
	* Caches the rs contracts in the entity cache if it is enabled.
	*
	* @param rsContracts the rs contracts
	*/
	public void cacheResult(java.util.List<RsContract> rsContracts);

	/**
	* Creates a new rs contract with the primary key. Does not add the rs contract to the database.
	*
	* @param rsContractSid the primary key for the new rs contract
	* @return the new rs contract
	*/
	public RsContract create(int rsContractSid);

	/**
	* Removes the rs contract with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsContractSid the primary key of the rs contract
	* @return the rs contract that was removed
	* @throws NoSuchRsContractException if a rs contract with the primary key could not be found
	*/
	public RsContract remove(int rsContractSid)
		throws NoSuchRsContractException;

	public RsContract updateImpl(RsContract rsContract);

	/**
	* Returns the rs contract with the primary key or throws a {@link NoSuchRsContractException} if it could not be found.
	*
	* @param rsContractSid the primary key of the rs contract
	* @return the rs contract
	* @throws NoSuchRsContractException if a rs contract with the primary key could not be found
	*/
	public RsContract findByPrimaryKey(int rsContractSid)
		throws NoSuchRsContractException;

	/**
	* Returns the rs contract with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rsContractSid the primary key of the rs contract
	* @return the rs contract, or <code>null</code> if a rs contract with the primary key could not be found
	*/
	public RsContract fetchByPrimaryKey(int rsContractSid);

	@Override
	public java.util.Map<java.io.Serializable, RsContract> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the rs contracts.
	*
	* @return the rs contracts
	*/
	public java.util.List<RsContract> findAll();

	/**
	* Returns a range of all the rs contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contracts
	* @param end the upper bound of the range of rs contracts (not inclusive)
	* @return the range of rs contracts
	*/
	public java.util.List<RsContract> findAll(int start, int end);

	/**
	* Returns an ordered range of all the rs contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contracts
	* @param end the upper bound of the range of rs contracts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rs contracts
	*/
	public java.util.List<RsContract> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsContract> orderByComparator);

	/**
	* Returns an ordered range of all the rs contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contracts
	* @param end the upper bound of the range of rs contracts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rs contracts
	*/
	public java.util.List<RsContract> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsContract> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the rs contracts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of rs contracts.
	*
	* @return the number of rs contracts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}