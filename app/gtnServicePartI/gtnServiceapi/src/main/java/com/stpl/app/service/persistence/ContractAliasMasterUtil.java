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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.model.ContractAliasMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the contract alias master service. This utility wraps {@link com.stpl.app.service.persistence.impl.ContractAliasMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ContractAliasMasterPersistence
 * @see com.stpl.app.service.persistence.impl.ContractAliasMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class ContractAliasMasterUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ContractAliasMaster contractAliasMaster) {
		getPersistence().clearCache(contractAliasMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ContractAliasMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContractAliasMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContractAliasMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContractAliasMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContractAliasMaster update(
		ContractAliasMaster contractAliasMaster) {
		return getPersistence().update(contractAliasMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContractAliasMaster update(
		ContractAliasMaster contractAliasMaster, ServiceContext serviceContext) {
		return getPersistence().update(contractAliasMaster, serviceContext);
	}

	/**
	* Returns all the contract alias masters where contractMasterSid = &#63;.
	*
	* @param contractMasterSid the contract master sid
	* @return the matching contract alias masters
	*/
	public static List<ContractAliasMaster> findByContractSystemId(
		int contractMasterSid) {
		return getPersistence().findByContractSystemId(contractMasterSid);
	}

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
	public static List<ContractAliasMaster> findByContractSystemId(
		int contractMasterSid, int start, int end) {
		return getPersistence()
				   .findByContractSystemId(contractMasterSid, start, end);
	}

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
	public static List<ContractAliasMaster> findByContractSystemId(
		int contractMasterSid, int start, int end,
		OrderByComparator<ContractAliasMaster> orderByComparator) {
		return getPersistence()
				   .findByContractSystemId(contractMasterSid, start, end,
			orderByComparator);
	}

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
	public static List<ContractAliasMaster> findByContractSystemId(
		int contractMasterSid, int start, int end,
		OrderByComparator<ContractAliasMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByContractSystemId(contractMasterSid, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first contract alias master in the ordered set where contractMasterSid = &#63;.
	*
	* @param contractMasterSid the contract master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract alias master
	* @throws NoSuchContractAliasMasterException if a matching contract alias master could not be found
	*/
	public static ContractAliasMaster findByContractSystemId_First(
		int contractMasterSid,
		OrderByComparator<ContractAliasMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchContractAliasMasterException {
		return getPersistence()
				   .findByContractSystemId_First(contractMasterSid,
			orderByComparator);
	}

	/**
	* Returns the first contract alias master in the ordered set where contractMasterSid = &#63;.
	*
	* @param contractMasterSid the contract master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract alias master, or <code>null</code> if a matching contract alias master could not be found
	*/
	public static ContractAliasMaster fetchByContractSystemId_First(
		int contractMasterSid,
		OrderByComparator<ContractAliasMaster> orderByComparator) {
		return getPersistence()
				   .fetchByContractSystemId_First(contractMasterSid,
			orderByComparator);
	}

	/**
	* Returns the last contract alias master in the ordered set where contractMasterSid = &#63;.
	*
	* @param contractMasterSid the contract master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract alias master
	* @throws NoSuchContractAliasMasterException if a matching contract alias master could not be found
	*/
	public static ContractAliasMaster findByContractSystemId_Last(
		int contractMasterSid,
		OrderByComparator<ContractAliasMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchContractAliasMasterException {
		return getPersistence()
				   .findByContractSystemId_Last(contractMasterSid,
			orderByComparator);
	}

	/**
	* Returns the last contract alias master in the ordered set where contractMasterSid = &#63;.
	*
	* @param contractMasterSid the contract master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract alias master, or <code>null</code> if a matching contract alias master could not be found
	*/
	public static ContractAliasMaster fetchByContractSystemId_Last(
		int contractMasterSid,
		OrderByComparator<ContractAliasMaster> orderByComparator) {
		return getPersistence()
				   .fetchByContractSystemId_Last(contractMasterSid,
			orderByComparator);
	}

	/**
	* Returns the contract alias masters before and after the current contract alias master in the ordered set where contractMasterSid = &#63;.
	*
	* @param contractAliasMasterSid the primary key of the current contract alias master
	* @param contractMasterSid the contract master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contract alias master
	* @throws NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
	*/
	public static ContractAliasMaster[] findByContractSystemId_PrevAndNext(
		int contractAliasMasterSid, int contractMasterSid,
		OrderByComparator<ContractAliasMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchContractAliasMasterException {
		return getPersistence()
				   .findByContractSystemId_PrevAndNext(contractAliasMasterSid,
			contractMasterSid, orderByComparator);
	}

	/**
	* Removes all the contract alias masters where contractMasterSid = &#63; from the database.
	*
	* @param contractMasterSid the contract master sid
	*/
	public static void removeByContractSystemId(int contractMasterSid) {
		getPersistence().removeByContractSystemId(contractMasterSid);
	}

	/**
	* Returns the number of contract alias masters where contractMasterSid = &#63;.
	*
	* @param contractMasterSid the contract master sid
	* @return the number of matching contract alias masters
	*/
	public static int countByContractSystemId(int contractMasterSid) {
		return getPersistence().countByContractSystemId(contractMasterSid);
	}

	/**
	* Caches the contract alias master in the entity cache if it is enabled.
	*
	* @param contractAliasMaster the contract alias master
	*/
	public static void cacheResult(ContractAliasMaster contractAliasMaster) {
		getPersistence().cacheResult(contractAliasMaster);
	}

	/**
	* Caches the contract alias masters in the entity cache if it is enabled.
	*
	* @param contractAliasMasters the contract alias masters
	*/
	public static void cacheResult(
		List<ContractAliasMaster> contractAliasMasters) {
		getPersistence().cacheResult(contractAliasMasters);
	}

	/**
	* Creates a new contract alias master with the primary key. Does not add the contract alias master to the database.
	*
	* @param contractAliasMasterSid the primary key for the new contract alias master
	* @return the new contract alias master
	*/
	public static ContractAliasMaster create(int contractAliasMasterSid) {
		return getPersistence().create(contractAliasMasterSid);
	}

	/**
	* Removes the contract alias master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAliasMasterSid the primary key of the contract alias master
	* @return the contract alias master that was removed
	* @throws NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
	*/
	public static ContractAliasMaster remove(int contractAliasMasterSid)
		throws com.stpl.app.exception.NoSuchContractAliasMasterException {
		return getPersistence().remove(contractAliasMasterSid);
	}

	public static ContractAliasMaster updateImpl(
		ContractAliasMaster contractAliasMaster) {
		return getPersistence().updateImpl(contractAliasMaster);
	}

	/**
	* Returns the contract alias master with the primary key or throws a {@link NoSuchContractAliasMasterException} if it could not be found.
	*
	* @param contractAliasMasterSid the primary key of the contract alias master
	* @return the contract alias master
	* @throws NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
	*/
	public static ContractAliasMaster findByPrimaryKey(
		int contractAliasMasterSid)
		throws com.stpl.app.exception.NoSuchContractAliasMasterException {
		return getPersistence().findByPrimaryKey(contractAliasMasterSid);
	}

	/**
	* Returns the contract alias master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contractAliasMasterSid the primary key of the contract alias master
	* @return the contract alias master, or <code>null</code> if a contract alias master with the primary key could not be found
	*/
	public static ContractAliasMaster fetchByPrimaryKey(
		int contractAliasMasterSid) {
		return getPersistence().fetchByPrimaryKey(contractAliasMasterSid);
	}

	public static java.util.Map<java.io.Serializable, ContractAliasMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the contract alias masters.
	*
	* @return the contract alias masters
	*/
	public static List<ContractAliasMaster> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ContractAliasMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ContractAliasMaster> findAll(int start, int end,
		OrderByComparator<ContractAliasMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ContractAliasMaster> findAll(int start, int end,
		OrderByComparator<ContractAliasMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the contract alias masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of contract alias masters.
	*
	* @return the number of contract alias masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ContractAliasMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContractAliasMasterPersistence, ContractAliasMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ContractAliasMasterPersistence.class);
}