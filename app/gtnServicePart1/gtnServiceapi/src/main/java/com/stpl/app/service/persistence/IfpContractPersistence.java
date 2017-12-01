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

import com.stpl.app.exception.NoSuchIfpContractException;
import com.stpl.app.model.IfpContract;

/**
 * The persistence interface for the ifp contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IfpContractPersistenceImpl
 * @see IfpContractUtil
 * @generated
 */
@ProviderType
public interface IfpContractPersistence extends BasePersistence<IfpContract> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IfpContractUtil} to access the ifp contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ifp contract in the entity cache if it is enabled.
	*
	* @param ifpContract the ifp contract
	*/
	public void cacheResult(IfpContract ifpContract);

	/**
	* Caches the ifp contracts in the entity cache if it is enabled.
	*
	* @param ifpContracts the ifp contracts
	*/
	public void cacheResult(java.util.List<IfpContract> ifpContracts);

	/**
	* Creates a new ifp contract with the primary key. Does not add the ifp contract to the database.
	*
	* @param ifpContractSid the primary key for the new ifp contract
	* @return the new ifp contract
	*/
	public IfpContract create(int ifpContractSid);

	/**
	* Removes the ifp contract with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ifpContractSid the primary key of the ifp contract
	* @return the ifp contract that was removed
	* @throws NoSuchIfpContractException if a ifp contract with the primary key could not be found
	*/
	public IfpContract remove(int ifpContractSid)
		throws NoSuchIfpContractException;

	public IfpContract updateImpl(IfpContract ifpContract);

	/**
	* Returns the ifp contract with the primary key or throws a {@link NoSuchIfpContractException} if it could not be found.
	*
	* @param ifpContractSid the primary key of the ifp contract
	* @return the ifp contract
	* @throws NoSuchIfpContractException if a ifp contract with the primary key could not be found
	*/
	public IfpContract findByPrimaryKey(int ifpContractSid)
		throws NoSuchIfpContractException;

	/**
	* Returns the ifp contract with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ifpContractSid the primary key of the ifp contract
	* @return the ifp contract, or <code>null</code> if a ifp contract with the primary key could not be found
	*/
	public IfpContract fetchByPrimaryKey(int ifpContractSid);

	@Override
	public java.util.Map<java.io.Serializable, IfpContract> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ifp contracts.
	*
	* @return the ifp contracts
	*/
	public java.util.List<IfpContract> findAll();

	/**
	* Returns a range of all the ifp contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp contracts
	* @param end the upper bound of the range of ifp contracts (not inclusive)
	* @return the range of ifp contracts
	*/
	public java.util.List<IfpContract> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ifp contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp contracts
	* @param end the upper bound of the range of ifp contracts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ifp contracts
	*/
	public java.util.List<IfpContract> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpContract> orderByComparator);

	/**
	* Returns an ordered range of all the ifp contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp contracts
	* @param end the upper bound of the range of ifp contracts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ifp contracts
	*/
	public java.util.List<IfpContract> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IfpContract> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ifp contracts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ifp contracts.
	*
	* @return the number of ifp contracts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}