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

import com.stpl.app.model.CfpContract;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cfp contract service. This utility wraps {@link com.stpl.app.service.persistence.impl.CfpContractPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpContractPersistence
 * @see com.stpl.app.service.persistence.impl.CfpContractPersistenceImpl
 * @generated
 */
@ProviderType
public class CfpContractUtil {
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
	public static void clearCache(CfpContract cfpContract) {
		getPersistence().clearCache(cfpContract);
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
	public static List<CfpContract> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CfpContract> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CfpContract> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CfpContract> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CfpContract update(CfpContract cfpContract) {
		return getPersistence().update(cfpContract);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CfpContract update(CfpContract cfpContract,
		ServiceContext serviceContext) {
		return getPersistence().update(cfpContract, serviceContext);
	}

	/**
	* Caches the cfp contract in the entity cache if it is enabled.
	*
	* @param cfpContract the cfp contract
	*/
	public static void cacheResult(CfpContract cfpContract) {
		getPersistence().cacheResult(cfpContract);
	}

	/**
	* Caches the cfp contracts in the entity cache if it is enabled.
	*
	* @param cfpContracts the cfp contracts
	*/
	public static void cacheResult(List<CfpContract> cfpContracts) {
		getPersistence().cacheResult(cfpContracts);
	}

	/**
	* Creates a new cfp contract with the primary key. Does not add the cfp contract to the database.
	*
	* @param cfpContractSid the primary key for the new cfp contract
	* @return the new cfp contract
	*/
	public static CfpContract create(int cfpContractSid) {
		return getPersistence().create(cfpContractSid);
	}

	/**
	* Removes the cfp contract with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpContractSid the primary key of the cfp contract
	* @return the cfp contract that was removed
	* @throws NoSuchCfpContractException if a cfp contract with the primary key could not be found
	*/
	public static CfpContract remove(int cfpContractSid)
		throws com.stpl.app.exception.NoSuchCfpContractException {
		return getPersistence().remove(cfpContractSid);
	}

	public static CfpContract updateImpl(CfpContract cfpContract) {
		return getPersistence().updateImpl(cfpContract);
	}

	/**
	* Returns the cfp contract with the primary key or throws a {@link NoSuchCfpContractException} if it could not be found.
	*
	* @param cfpContractSid the primary key of the cfp contract
	* @return the cfp contract
	* @throws NoSuchCfpContractException if a cfp contract with the primary key could not be found
	*/
	public static CfpContract findByPrimaryKey(int cfpContractSid)
		throws com.stpl.app.exception.NoSuchCfpContractException {
		return getPersistence().findByPrimaryKey(cfpContractSid);
	}

	/**
	* Returns the cfp contract with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cfpContractSid the primary key of the cfp contract
	* @return the cfp contract, or <code>null</code> if a cfp contract with the primary key could not be found
	*/
	public static CfpContract fetchByPrimaryKey(int cfpContractSid) {
		return getPersistence().fetchByPrimaryKey(cfpContractSid);
	}

	public static java.util.Map<java.io.Serializable, CfpContract> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cfp contracts.
	*
	* @return the cfp contracts
	*/
	public static List<CfpContract> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CfpContract> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CfpContract> findAll(int start, int end,
		OrderByComparator<CfpContract> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CfpContract> findAll(int start, int end,
		OrderByComparator<CfpContract> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cfp contracts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cfp contracts.
	*
	* @return the number of cfp contracts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CfpContractPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CfpContractPersistence, CfpContractPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CfpContractPersistence.class);
}