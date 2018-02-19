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

import com.stpl.app.exception.NoSuchPsContractException;
import com.stpl.app.model.PsContract;

/**
 * The persistence interface for the ps contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.PsContractPersistenceImpl
 * @see PsContractUtil
 * @generated
 */
@ProviderType
public interface PsContractPersistence extends BasePersistence<PsContract> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PsContractUtil} to access the ps contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ps contract in the entity cache if it is enabled.
	*
	* @param psContract the ps contract
	*/
	public void cacheResult(PsContract psContract);

	/**
	* Caches the ps contracts in the entity cache if it is enabled.
	*
	* @param psContracts the ps contracts
	*/
	public void cacheResult(java.util.List<PsContract> psContracts);

	/**
	* Creates a new ps contract with the primary key. Does not add the ps contract to the database.
	*
	* @param psContractSid the primary key for the new ps contract
	* @return the new ps contract
	*/
	public PsContract create(int psContractSid);

	/**
	* Removes the ps contract with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param psContractSid the primary key of the ps contract
	* @return the ps contract that was removed
	* @throws NoSuchPsContractException if a ps contract with the primary key could not be found
	*/
	public PsContract remove(int psContractSid)
		throws NoSuchPsContractException;

	public PsContract updateImpl(PsContract psContract);

	/**
	* Returns the ps contract with the primary key or throws a {@link NoSuchPsContractException} if it could not be found.
	*
	* @param psContractSid the primary key of the ps contract
	* @return the ps contract
	* @throws NoSuchPsContractException if a ps contract with the primary key could not be found
	*/
	public PsContract findByPrimaryKey(int psContractSid)
		throws NoSuchPsContractException;

	/**
	* Returns the ps contract with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param psContractSid the primary key of the ps contract
	* @return the ps contract, or <code>null</code> if a ps contract with the primary key could not be found
	*/
	public PsContract fetchByPrimaryKey(int psContractSid);

	@Override
	public java.util.Map<java.io.Serializable, PsContract> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ps contracts.
	*
	* @return the ps contracts
	*/
	public java.util.List<PsContract> findAll();

	/**
	* Returns a range of all the ps contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps contracts
	* @param end the upper bound of the range of ps contracts (not inclusive)
	* @return the range of ps contracts
	*/
	public java.util.List<PsContract> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ps contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps contracts
	* @param end the upper bound of the range of ps contracts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ps contracts
	*/
	public java.util.List<PsContract> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsContract> orderByComparator);

	/**
	* Returns an ordered range of all the ps contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps contracts
	* @param end the upper bound of the range of ps contracts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ps contracts
	*/
	public java.util.List<PsContract> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PsContract> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ps contracts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ps contracts.
	*
	* @return the number of ps contracts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}