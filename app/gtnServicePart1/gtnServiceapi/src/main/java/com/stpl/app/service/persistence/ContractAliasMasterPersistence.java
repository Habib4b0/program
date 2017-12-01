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

import com.stpl.app.exception.NoSuchContractAliasMasterException;
import com.stpl.app.model.ContractAliasMaster;

/**
 * The persistence interface for the contract alias master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ContractAliasMasterPersistenceImpl
 * @see ContractAliasMasterUtil
 * @generated
 */
@ProviderType
public interface ContractAliasMasterPersistence extends BasePersistence<ContractAliasMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContractAliasMasterUtil} to access the contract alias master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the contract alias masters where contractMasterSid = &#63;.
	*
	* @param contractMasterSid the contract master sid
	* @return the matching contract alias masters
	*/
	public java.util.List<ContractAliasMaster> findByContractSystemId(
		int contractMasterSid);

	/**
	* Returns a range of all the contract alias masters where contractMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractMasterSid the contract master sid
	* @param start the lower bound of the range of contract alias masters
	* @param end the upper bound of the range of contract alias masters (not inclusive)
	* @return the range of matching contract alias masters
	*/
	public java.util.List<ContractAliasMaster> findByContractSystemId(
		int contractMasterSid, int start, int end);

	/**
	* Returns an ordered range of all the contract alias masters where contractMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractMasterSid the contract master sid
	* @param start the lower bound of the range of contract alias masters
	* @param end the upper bound of the range of contract alias masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contract alias masters
	*/
	public java.util.List<ContractAliasMaster> findByContractSystemId(
		int contractMasterSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAliasMaster> orderByComparator);

	/**
	* Returns an ordered range of all the contract alias masters where contractMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractMasterSid the contract master sid
	* @param start the lower bound of the range of contract alias masters
	* @param end the upper bound of the range of contract alias masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contract alias masters
	*/
	public java.util.List<ContractAliasMaster> findByContractSystemId(
		int contractMasterSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAliasMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first contract alias master in the ordered set where contractMasterSid = &#63;.
	*
	* @param contractMasterSid the contract master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract alias master
	* @throws NoSuchContractAliasMasterException if a matching contract alias master could not be found
	*/
	public ContractAliasMaster findByContractSystemId_First(
		int contractMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAliasMaster> orderByComparator)
		throws NoSuchContractAliasMasterException;

	/**
	* Returns the first contract alias master in the ordered set where contractMasterSid = &#63;.
	*
	* @param contractMasterSid the contract master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract alias master, or <code>null</code> if a matching contract alias master could not be found
	*/
	public ContractAliasMaster fetchByContractSystemId_First(
		int contractMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAliasMaster> orderByComparator);

	/**
	* Returns the last contract alias master in the ordered set where contractMasterSid = &#63;.
	*
	* @param contractMasterSid the contract master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract alias master
	* @throws NoSuchContractAliasMasterException if a matching contract alias master could not be found
	*/
	public ContractAliasMaster findByContractSystemId_Last(
		int contractMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAliasMaster> orderByComparator)
		throws NoSuchContractAliasMasterException;

	/**
	* Returns the last contract alias master in the ordered set where contractMasterSid = &#63;.
	*
	* @param contractMasterSid the contract master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract alias master, or <code>null</code> if a matching contract alias master could not be found
	*/
	public ContractAliasMaster fetchByContractSystemId_Last(
		int contractMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAliasMaster> orderByComparator);

	/**
	* Returns the contract alias masters before and after the current contract alias master in the ordered set where contractMasterSid = &#63;.
	*
	* @param contractAliasMasterSid the primary key of the current contract alias master
	* @param contractMasterSid the contract master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contract alias master
	* @throws NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
	*/
	public ContractAliasMaster[] findByContractSystemId_PrevAndNext(
		int contractAliasMasterSid, int contractMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAliasMaster> orderByComparator)
		throws NoSuchContractAliasMasterException;

	/**
	* Removes all the contract alias masters where contractMasterSid = &#63; from the database.
	*
	* @param contractMasterSid the contract master sid
	*/
	public void removeByContractSystemId(int contractMasterSid);

	/**
	* Returns the number of contract alias masters where contractMasterSid = &#63;.
	*
	* @param contractMasterSid the contract master sid
	* @return the number of matching contract alias masters
	*/
	public int countByContractSystemId(int contractMasterSid);

	/**
	* Caches the contract alias master in the entity cache if it is enabled.
	*
	* @param contractAliasMaster the contract alias master
	*/
	public void cacheResult(ContractAliasMaster contractAliasMaster);

	/**
	* Caches the contract alias masters in the entity cache if it is enabled.
	*
	* @param contractAliasMasters the contract alias masters
	*/
	public void cacheResult(
		java.util.List<ContractAliasMaster> contractAliasMasters);

	/**
	* Creates a new contract alias master with the primary key. Does not add the contract alias master to the database.
	*
	* @param contractAliasMasterSid the primary key for the new contract alias master
	* @return the new contract alias master
	*/
	public ContractAliasMaster create(int contractAliasMasterSid);

	/**
	* Removes the contract alias master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAliasMasterSid the primary key of the contract alias master
	* @return the contract alias master that was removed
	* @throws NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
	*/
	public ContractAliasMaster remove(int contractAliasMasterSid)
		throws NoSuchContractAliasMasterException;

	public ContractAliasMaster updateImpl(
		ContractAliasMaster contractAliasMaster);

	/**
	* Returns the contract alias master with the primary key or throws a {@link NoSuchContractAliasMasterException} if it could not be found.
	*
	* @param contractAliasMasterSid the primary key of the contract alias master
	* @return the contract alias master
	* @throws NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
	*/
	public ContractAliasMaster findByPrimaryKey(int contractAliasMasterSid)
		throws NoSuchContractAliasMasterException;

	/**
	* Returns the contract alias master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contractAliasMasterSid the primary key of the contract alias master
	* @return the contract alias master, or <code>null</code> if a contract alias master with the primary key could not be found
	*/
	public ContractAliasMaster fetchByPrimaryKey(int contractAliasMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, ContractAliasMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the contract alias masters.
	*
	* @return the contract alias masters
	*/
	public java.util.List<ContractAliasMaster> findAll();

	/**
	* Returns a range of all the contract alias masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract alias masters
	* @param end the upper bound of the range of contract alias masters (not inclusive)
	* @return the range of contract alias masters
	*/
	public java.util.List<ContractAliasMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the contract alias masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract alias masters
	* @param end the upper bound of the range of contract alias masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of contract alias masters
	*/
	public java.util.List<ContractAliasMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAliasMaster> orderByComparator);

	/**
	* Returns an ordered range of all the contract alias masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract alias masters
	* @param end the upper bound of the range of contract alias masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of contract alias masters
	*/
	public java.util.List<ContractAliasMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAliasMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the contract alias masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of contract alias masters.
	*
	* @return the number of contract alias masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}