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

import com.stpl.app.model.TransactionModuleDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the transaction module details service. This utility wraps {@link com.stpl.app.service.persistence.impl.TransactionModuleDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see TransactionModuleDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.TransactionModuleDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class TransactionModuleDetailsUtil {
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
	public static void clearCache(
		TransactionModuleDetails transactionModuleDetails) {
		getPersistence().clearCache(transactionModuleDetails);
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
	public static List<TransactionModuleDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TransactionModuleDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TransactionModuleDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TransactionModuleDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TransactionModuleDetails update(
		TransactionModuleDetails transactionModuleDetails) {
		return getPersistence().update(transactionModuleDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TransactionModuleDetails update(
		TransactionModuleDetails transactionModuleDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(transactionModuleDetails, serviceContext);
	}

	/**
	* Caches the transaction module details in the entity cache if it is enabled.
	*
	* @param transactionModuleDetails the transaction module details
	*/
	public static void cacheResult(
		TransactionModuleDetails transactionModuleDetails) {
		getPersistence().cacheResult(transactionModuleDetails);
	}

	/**
	* Caches the transaction module detailses in the entity cache if it is enabled.
	*
	* @param transactionModuleDetailses the transaction module detailses
	*/
	public static void cacheResult(
		List<TransactionModuleDetails> transactionModuleDetailses) {
		getPersistence().cacheResult(transactionModuleDetailses);
	}

	/**
	* Creates a new transaction module details with the primary key. Does not add the transaction module details to the database.
	*
	* @param transactionModuleDetailsSid the primary key for the new transaction module details
	* @return the new transaction module details
	*/
	public static TransactionModuleDetails create(
		int transactionModuleDetailsSid) {
		return getPersistence().create(transactionModuleDetailsSid);
	}

	/**
	* Removes the transaction module details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param transactionModuleDetailsSid the primary key of the transaction module details
	* @return the transaction module details that was removed
	* @throws NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
	*/
	public static TransactionModuleDetails remove(
		int transactionModuleDetailsSid)
		throws com.stpl.app.exception.NoSuchTransactionModuleDetailsException {
		return getPersistence().remove(transactionModuleDetailsSid);
	}

	public static TransactionModuleDetails updateImpl(
		TransactionModuleDetails transactionModuleDetails) {
		return getPersistence().updateImpl(transactionModuleDetails);
	}

	/**
	* Returns the transaction module details with the primary key or throws a {@link NoSuchTransactionModuleDetailsException} if it could not be found.
	*
	* @param transactionModuleDetailsSid the primary key of the transaction module details
	* @return the transaction module details
	* @throws NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
	*/
	public static TransactionModuleDetails findByPrimaryKey(
		int transactionModuleDetailsSid)
		throws com.stpl.app.exception.NoSuchTransactionModuleDetailsException {
		return getPersistence().findByPrimaryKey(transactionModuleDetailsSid);
	}

	/**
	* Returns the transaction module details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param transactionModuleDetailsSid the primary key of the transaction module details
	* @return the transaction module details, or <code>null</code> if a transaction module details with the primary key could not be found
	*/
	public static TransactionModuleDetails fetchByPrimaryKey(
		int transactionModuleDetailsSid) {
		return getPersistence().fetchByPrimaryKey(transactionModuleDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, TransactionModuleDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the transaction module detailses.
	*
	* @return the transaction module detailses
	*/
	public static List<TransactionModuleDetails> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<TransactionModuleDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<TransactionModuleDetails> findAll(int start, int end,
		OrderByComparator<TransactionModuleDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<TransactionModuleDetails> findAll(int start, int end,
		OrderByComparator<TransactionModuleDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the transaction module detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of transaction module detailses.
	*
	* @return the number of transaction module detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TransactionModuleDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TransactionModuleDetailsPersistence, TransactionModuleDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(TransactionModuleDetailsPersistence.class);
}