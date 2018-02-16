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

import com.stpl.app.model.ContractMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the contract master service. This utility wraps {@link com.stpl.app.service.persistence.impl.ContractMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ContractMasterPersistence
 * @see com.stpl.app.service.persistence.impl.ContractMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class ContractMasterUtil {
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
	public static void clearCache(ContractMaster contractMaster) {
		getPersistence().clearCache(contractMaster);
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
	public static List<ContractMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContractMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContractMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContractMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContractMaster update(ContractMaster contractMaster) {
		return getPersistence().update(contractMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContractMaster update(ContractMaster contractMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(contractMaster, serviceContext);
	}

	/**
	* Caches the contract master in the entity cache if it is enabled.
	*
	* @param contractMaster the contract master
	*/
	public static void cacheResult(ContractMaster contractMaster) {
		getPersistence().cacheResult(contractMaster);
	}

	/**
	* Caches the contract masters in the entity cache if it is enabled.
	*
	* @param contractMasters the contract masters
	*/
	public static void cacheResult(List<ContractMaster> contractMasters) {
		getPersistence().cacheResult(contractMasters);
	}

	/**
	* Creates a new contract master with the primary key. Does not add the contract master to the database.
	*
	* @param contractMasterSid the primary key for the new contract master
	* @return the new contract master
	*/
	public static ContractMaster create(int contractMasterSid) {
		return getPersistence().create(contractMasterSid);
	}

	/**
	* Removes the contract master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractMasterSid the primary key of the contract master
	* @return the contract master that was removed
	* @throws NoSuchContractMasterException if a contract master with the primary key could not be found
	*/
	public static ContractMaster remove(int contractMasterSid)
		throws com.stpl.app.exception.NoSuchContractMasterException {
		return getPersistence().remove(contractMasterSid);
	}

	public static ContractMaster updateImpl(ContractMaster contractMaster) {
		return getPersistence().updateImpl(contractMaster);
	}

	/**
	* Returns the contract master with the primary key or throws a {@link NoSuchContractMasterException} if it could not be found.
	*
	* @param contractMasterSid the primary key of the contract master
	* @return the contract master
	* @throws NoSuchContractMasterException if a contract master with the primary key could not be found
	*/
	public static ContractMaster findByPrimaryKey(int contractMasterSid)
		throws com.stpl.app.exception.NoSuchContractMasterException {
		return getPersistence().findByPrimaryKey(contractMasterSid);
	}

	/**
	* Returns the contract master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contractMasterSid the primary key of the contract master
	* @return the contract master, or <code>null</code> if a contract master with the primary key could not be found
	*/
	public static ContractMaster fetchByPrimaryKey(int contractMasterSid) {
		return getPersistence().fetchByPrimaryKey(contractMasterSid);
	}

	public static java.util.Map<java.io.Serializable, ContractMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the contract masters.
	*
	* @return the contract masters
	*/
	public static List<ContractMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the contract masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract masters
	* @param end the upper bound of the range of contract masters (not inclusive)
	* @return the range of contract masters
	*/
	public static List<ContractMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the contract masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract masters
	* @param end the upper bound of the range of contract masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of contract masters
	*/
	public static List<ContractMaster> findAll(int start, int end,
		OrderByComparator<ContractMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the contract masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract masters
	* @param end the upper bound of the range of contract masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of contract masters
	*/
	public static List<ContractMaster> findAll(int start, int end,
		OrderByComparator<ContractMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the contract masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of contract masters.
	*
	* @return the number of contract masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ContractMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContractMasterPersistence, ContractMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ContractMasterPersistence.class);
}