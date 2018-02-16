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

import com.stpl.app.model.ImtdSalesBasisDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the imtd sales basis details service. This utility wraps {@link com.stpl.app.service.persistence.impl.ImtdSalesBasisDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdSalesBasisDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.ImtdSalesBasisDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class ImtdSalesBasisDetailsUtil {
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
	public static void clearCache(ImtdSalesBasisDetails imtdSalesBasisDetails) {
		getPersistence().clearCache(imtdSalesBasisDetails);
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
	public static List<ImtdSalesBasisDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ImtdSalesBasisDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ImtdSalesBasisDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ImtdSalesBasisDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ImtdSalesBasisDetails update(
		ImtdSalesBasisDetails imtdSalesBasisDetails) {
		return getPersistence().update(imtdSalesBasisDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ImtdSalesBasisDetails update(
		ImtdSalesBasisDetails imtdSalesBasisDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(imtdSalesBasisDetails, serviceContext);
	}

	/**
	* Caches the imtd sales basis details in the entity cache if it is enabled.
	*
	* @param imtdSalesBasisDetails the imtd sales basis details
	*/
	public static void cacheResult(ImtdSalesBasisDetails imtdSalesBasisDetails) {
		getPersistence().cacheResult(imtdSalesBasisDetails);
	}

	/**
	* Caches the imtd sales basis detailses in the entity cache if it is enabled.
	*
	* @param imtdSalesBasisDetailses the imtd sales basis detailses
	*/
	public static void cacheResult(
		List<ImtdSalesBasisDetails> imtdSalesBasisDetailses) {
		getPersistence().cacheResult(imtdSalesBasisDetailses);
	}

	/**
	* Creates a new imtd sales basis details with the primary key. Does not add the imtd sales basis details to the database.
	*
	* @param imtdSalesBasisDetailsSid the primary key for the new imtd sales basis details
	* @return the new imtd sales basis details
	*/
	public static ImtdSalesBasisDetails create(int imtdSalesBasisDetailsSid) {
		return getPersistence().create(imtdSalesBasisDetailsSid);
	}

	/**
	* Removes the imtd sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
	* @return the imtd sales basis details that was removed
	* @throws NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
	*/
	public static ImtdSalesBasisDetails remove(int imtdSalesBasisDetailsSid)
		throws com.stpl.app.exception.NoSuchImtdSalesBasisDetailsException {
		return getPersistence().remove(imtdSalesBasisDetailsSid);
	}

	public static ImtdSalesBasisDetails updateImpl(
		ImtdSalesBasisDetails imtdSalesBasisDetails) {
		return getPersistence().updateImpl(imtdSalesBasisDetails);
	}

	/**
	* Returns the imtd sales basis details with the primary key or throws a {@link NoSuchImtdSalesBasisDetailsException} if it could not be found.
	*
	* @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
	* @return the imtd sales basis details
	* @throws NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
	*/
	public static ImtdSalesBasisDetails findByPrimaryKey(
		int imtdSalesBasisDetailsSid)
		throws com.stpl.app.exception.NoSuchImtdSalesBasisDetailsException {
		return getPersistence().findByPrimaryKey(imtdSalesBasisDetailsSid);
	}

	/**
	* Returns the imtd sales basis details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
	* @return the imtd sales basis details, or <code>null</code> if a imtd sales basis details with the primary key could not be found
	*/
	public static ImtdSalesBasisDetails fetchByPrimaryKey(
		int imtdSalesBasisDetailsSid) {
		return getPersistence().fetchByPrimaryKey(imtdSalesBasisDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, ImtdSalesBasisDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the imtd sales basis detailses.
	*
	* @return the imtd sales basis detailses
	*/
	public static List<ImtdSalesBasisDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the imtd sales basis detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd sales basis detailses
	* @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
	* @return the range of imtd sales basis detailses
	*/
	public static List<ImtdSalesBasisDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the imtd sales basis detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd sales basis detailses
	* @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd sales basis detailses
	*/
	public static List<ImtdSalesBasisDetails> findAll(int start, int end,
		OrderByComparator<ImtdSalesBasisDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the imtd sales basis detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd sales basis detailses
	* @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd sales basis detailses
	*/
	public static List<ImtdSalesBasisDetails> findAll(int start, int end,
		OrderByComparator<ImtdSalesBasisDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the imtd sales basis detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of imtd sales basis detailses.
	*
	* @return the number of imtd sales basis detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ImtdSalesBasisDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImtdSalesBasisDetailsPersistence, ImtdSalesBasisDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ImtdSalesBasisDetailsPersistence.class);
}