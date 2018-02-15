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

import com.stpl.app.exception.NoSuchCfpContractException;
import com.stpl.app.model.CfpContract;

/**
 * The persistence interface for the cfp contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CfpContractPersistenceImpl
 * @see CfpContractUtil
 * @generated
 */
@ProviderType
public interface CfpContractPersistence extends BasePersistence<CfpContract> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CfpContractUtil} to access the cfp contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cfp contract in the entity cache if it is enabled.
	*
	* @param cfpContract the cfp contract
	*/
	public void cacheResult(CfpContract cfpContract);

	/**
	* Caches the cfp contracts in the entity cache if it is enabled.
	*
	* @param cfpContracts the cfp contracts
	*/
	public void cacheResult(java.util.List<CfpContract> cfpContracts);

	/**
	* Creates a new cfp contract with the primary key. Does not add the cfp contract to the database.
	*
	* @param cfpContractSid the primary key for the new cfp contract
	* @return the new cfp contract
	*/
	public CfpContract create(int cfpContractSid);

	/**
	* Removes the cfp contract with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpContractSid the primary key of the cfp contract
	* @return the cfp contract that was removed
	* @throws NoSuchCfpContractException if a cfp contract with the primary key could not be found
	*/
	public CfpContract remove(int cfpContractSid)
		throws NoSuchCfpContractException;

	public CfpContract updateImpl(CfpContract cfpContract);

	/**
	* Returns the cfp contract with the primary key or throws a {@link NoSuchCfpContractException} if it could not be found.
	*
	* @param cfpContractSid the primary key of the cfp contract
	* @return the cfp contract
	* @throws NoSuchCfpContractException if a cfp contract with the primary key could not be found
	*/
	public CfpContract findByPrimaryKey(int cfpContractSid)
		throws NoSuchCfpContractException;

	/**
	* Returns the cfp contract with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cfpContractSid the primary key of the cfp contract
	* @return the cfp contract, or <code>null</code> if a cfp contract with the primary key could not be found
	*/
	public CfpContract fetchByPrimaryKey(int cfpContractSid);

	@Override
	public java.util.Map<java.io.Serializable, CfpContract> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cfp contracts.
	*
	* @return the cfp contracts
	*/
	public java.util.List<CfpContract> findAll();

	/**
	* Returns a range of all the cfp contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp contracts
	* @param end the upper bound of the range of cfp contracts (not inclusive)
	* @return the range of cfp contracts
	*/
	public java.util.List<CfpContract> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cfp contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp contracts
	* @param end the upper bound of the range of cfp contracts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cfp contracts
	*/
	public java.util.List<CfpContract> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CfpContract> orderByComparator);

	/**
	* Returns an ordered range of all the cfp contracts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp contracts
	* @param end the upper bound of the range of cfp contracts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cfp contracts
	*/
	public java.util.List<CfpContract> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CfpContract> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cfp contracts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cfp contracts.
	*
	* @return the number of cfp contracts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}