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

import com.stpl.app.model.ImtdPsDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the imtd ps details service. This utility wraps {@link com.stpl.app.service.persistence.impl.ImtdPsDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdPsDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.ImtdPsDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class ImtdPsDetailsUtil {
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
	public static void clearCache(ImtdPsDetails imtdPsDetails) {
		getPersistence().clearCache(imtdPsDetails);
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
	public static List<ImtdPsDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ImtdPsDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ImtdPsDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ImtdPsDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ImtdPsDetails update(ImtdPsDetails imtdPsDetails) {
		return getPersistence().update(imtdPsDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ImtdPsDetails update(ImtdPsDetails imtdPsDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(imtdPsDetails, serviceContext);
	}

	/**
	* Caches the imtd ps details in the entity cache if it is enabled.
	*
	* @param imtdPsDetails the imtd ps details
	*/
	public static void cacheResult(ImtdPsDetails imtdPsDetails) {
		getPersistence().cacheResult(imtdPsDetails);
	}

	/**
	* Caches the imtd ps detailses in the entity cache if it is enabled.
	*
	* @param imtdPsDetailses the imtd ps detailses
	*/
	public static void cacheResult(List<ImtdPsDetails> imtdPsDetailses) {
		getPersistence().cacheResult(imtdPsDetailses);
	}

	/**
	* Creates a new imtd ps details with the primary key. Does not add the imtd ps details to the database.
	*
	* @param imtdPsDetailsSid the primary key for the new imtd ps details
	* @return the new imtd ps details
	*/
	public static ImtdPsDetails create(int imtdPsDetailsSid) {
		return getPersistence().create(imtdPsDetailsSid);
	}

	/**
	* Removes the imtd ps details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdPsDetailsSid the primary key of the imtd ps details
	* @return the imtd ps details that was removed
	* @throws NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
	*/
	public static ImtdPsDetails remove(int imtdPsDetailsSid)
		throws com.stpl.app.exception.NoSuchImtdPsDetailsException {
		return getPersistence().remove(imtdPsDetailsSid);
	}

	public static ImtdPsDetails updateImpl(ImtdPsDetails imtdPsDetails) {
		return getPersistence().updateImpl(imtdPsDetails);
	}

	/**
	* Returns the imtd ps details with the primary key or throws a {@link NoSuchImtdPsDetailsException} if it could not be found.
	*
	* @param imtdPsDetailsSid the primary key of the imtd ps details
	* @return the imtd ps details
	* @throws NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
	*/
	public static ImtdPsDetails findByPrimaryKey(int imtdPsDetailsSid)
		throws com.stpl.app.exception.NoSuchImtdPsDetailsException {
		return getPersistence().findByPrimaryKey(imtdPsDetailsSid);
	}

	/**
	* Returns the imtd ps details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdPsDetailsSid the primary key of the imtd ps details
	* @return the imtd ps details, or <code>null</code> if a imtd ps details with the primary key could not be found
	*/
	public static ImtdPsDetails fetchByPrimaryKey(int imtdPsDetailsSid) {
		return getPersistence().fetchByPrimaryKey(imtdPsDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, ImtdPsDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the imtd ps detailses.
	*
	* @return the imtd ps detailses
	*/
	public static List<ImtdPsDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the imtd ps detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ps detailses
	* @param end the upper bound of the range of imtd ps detailses (not inclusive)
	* @return the range of imtd ps detailses
	*/
	public static List<ImtdPsDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the imtd ps detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ps detailses
	* @param end the upper bound of the range of imtd ps detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd ps detailses
	*/
	public static List<ImtdPsDetails> findAll(int start, int end,
		OrderByComparator<ImtdPsDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the imtd ps detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ps detailses
	* @param end the upper bound of the range of imtd ps detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd ps detailses
	*/
	public static List<ImtdPsDetails> findAll(int start, int end,
		OrderByComparator<ImtdPsDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the imtd ps detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of imtd ps detailses.
	*
	* @return the number of imtd ps detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ImtdPsDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImtdPsDetailsPersistence, ImtdPsDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ImtdPsDetailsPersistence.class);
}