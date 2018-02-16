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

import com.stpl.app.model.ImtdItemPriceRebateDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the imtd item price rebate details service. This utility wraps {@link com.stpl.app.service.persistence.impl.ImtdItemPriceRebateDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdItemPriceRebateDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.ImtdItemPriceRebateDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class ImtdItemPriceRebateDetailsUtil {
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
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
		getPersistence().clearCache(imtdItemPriceRebateDetails);
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
	public static List<ImtdItemPriceRebateDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ImtdItemPriceRebateDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ImtdItemPriceRebateDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ImtdItemPriceRebateDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ImtdItemPriceRebateDetails update(
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
		return getPersistence().update(imtdItemPriceRebateDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ImtdItemPriceRebateDetails update(
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails,
		ServiceContext serviceContext) {
		return getPersistence()
				   .update(imtdItemPriceRebateDetails, serviceContext);
	}

	/**
	* Caches the imtd item price rebate details in the entity cache if it is enabled.
	*
	* @param imtdItemPriceRebateDetails the imtd item price rebate details
	*/
	public static void cacheResult(
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
		getPersistence().cacheResult(imtdItemPriceRebateDetails);
	}

	/**
	* Caches the imtd item price rebate detailses in the entity cache if it is enabled.
	*
	* @param imtdItemPriceRebateDetailses the imtd item price rebate detailses
	*/
	public static void cacheResult(
		List<ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses) {
		getPersistence().cacheResult(imtdItemPriceRebateDetailses);
	}

	/**
	* Creates a new imtd item price rebate details with the primary key. Does not add the imtd item price rebate details to the database.
	*
	* @param imtdItemPriceRebateSid the primary key for the new imtd item price rebate details
	* @return the new imtd item price rebate details
	*/
	public static ImtdItemPriceRebateDetails create(int imtdItemPriceRebateSid) {
		return getPersistence().create(imtdItemPriceRebateSid);
	}

	/**
	* Removes the imtd item price rebate details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
	* @return the imtd item price rebate details that was removed
	* @throws NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
	*/
	public static ImtdItemPriceRebateDetails remove(int imtdItemPriceRebateSid)
		throws com.stpl.app.exception.NoSuchImtdItemPriceRebateDetailsException {
		return getPersistence().remove(imtdItemPriceRebateSid);
	}

	public static ImtdItemPriceRebateDetails updateImpl(
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
		return getPersistence().updateImpl(imtdItemPriceRebateDetails);
	}

	/**
	* Returns the imtd item price rebate details with the primary key or throws a {@link NoSuchImtdItemPriceRebateDetailsException} if it could not be found.
	*
	* @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
	* @return the imtd item price rebate details
	* @throws NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
	*/
	public static ImtdItemPriceRebateDetails findByPrimaryKey(
		int imtdItemPriceRebateSid)
		throws com.stpl.app.exception.NoSuchImtdItemPriceRebateDetailsException {
		return getPersistence().findByPrimaryKey(imtdItemPriceRebateSid);
	}

	/**
	* Returns the imtd item price rebate details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
	* @return the imtd item price rebate details, or <code>null</code> if a imtd item price rebate details with the primary key could not be found
	*/
	public static ImtdItemPriceRebateDetails fetchByPrimaryKey(
		int imtdItemPriceRebateSid) {
		return getPersistence().fetchByPrimaryKey(imtdItemPriceRebateSid);
	}

	public static java.util.Map<java.io.Serializable, ImtdItemPriceRebateDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the imtd item price rebate detailses.
	*
	* @return the imtd item price rebate detailses
	*/
	public static List<ImtdItemPriceRebateDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the imtd item price rebate detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd item price rebate detailses
	* @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
	* @return the range of imtd item price rebate detailses
	*/
	public static List<ImtdItemPriceRebateDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the imtd item price rebate detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd item price rebate detailses
	* @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd item price rebate detailses
	*/
	public static List<ImtdItemPriceRebateDetails> findAll(int start, int end,
		OrderByComparator<ImtdItemPriceRebateDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the imtd item price rebate detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd item price rebate detailses
	* @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd item price rebate detailses
	*/
	public static List<ImtdItemPriceRebateDetails> findAll(int start, int end,
		OrderByComparator<ImtdItemPriceRebateDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the imtd item price rebate detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of imtd item price rebate detailses.
	*
	* @return the number of imtd item price rebate detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ImtdItemPriceRebateDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImtdItemPriceRebateDetailsPersistence, ImtdItemPriceRebateDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ImtdItemPriceRebateDetailsPersistence.class);
}