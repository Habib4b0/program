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

import com.stpl.app.model.CustomViewDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the custom view details service. This utility wraps {@link com.stpl.app.service.persistence.impl.CustomViewDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomViewDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.CustomViewDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class CustomViewDetailsUtil {
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
	public static void clearCache(CustomViewDetails customViewDetails) {
		getPersistence().clearCache(customViewDetails);
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
	public static List<CustomViewDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CustomViewDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CustomViewDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CustomViewDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CustomViewDetails update(CustomViewDetails customViewDetails) {
		return getPersistence().update(customViewDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CustomViewDetails update(
		CustomViewDetails customViewDetails, ServiceContext serviceContext) {
		return getPersistence().update(customViewDetails, serviceContext);
	}

	/**
	* Caches the custom view details in the entity cache if it is enabled.
	*
	* @param customViewDetails the custom view details
	*/
	public static void cacheResult(CustomViewDetails customViewDetails) {
		getPersistence().cacheResult(customViewDetails);
	}

	/**
	* Caches the custom view detailses in the entity cache if it is enabled.
	*
	* @param customViewDetailses the custom view detailses
	*/
	public static void cacheResult(List<CustomViewDetails> customViewDetailses) {
		getPersistence().cacheResult(customViewDetailses);
	}

	/**
	* Creates a new custom view details with the primary key. Does not add the custom view details to the database.
	*
	* @param customViewDetailsSid the primary key for the new custom view details
	* @return the new custom view details
	*/
	public static CustomViewDetails create(int customViewDetailsSid) {
		return getPersistence().create(customViewDetailsSid);
	}

	/**
	* Removes the custom view details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customViewDetailsSid the primary key of the custom view details
	* @return the custom view details that was removed
	* @throws NoSuchCustomViewDetailsException if a custom view details with the primary key could not be found
	*/
	public static CustomViewDetails remove(int customViewDetailsSid)
		throws com.stpl.app.exception.NoSuchCustomViewDetailsException {
		return getPersistence().remove(customViewDetailsSid);
	}

	public static CustomViewDetails updateImpl(
		CustomViewDetails customViewDetails) {
		return getPersistence().updateImpl(customViewDetails);
	}

	/**
	* Returns the custom view details with the primary key or throws a {@link NoSuchCustomViewDetailsException} if it could not be found.
	*
	* @param customViewDetailsSid the primary key of the custom view details
	* @return the custom view details
	* @throws NoSuchCustomViewDetailsException if a custom view details with the primary key could not be found
	*/
	public static CustomViewDetails findByPrimaryKey(int customViewDetailsSid)
		throws com.stpl.app.exception.NoSuchCustomViewDetailsException {
		return getPersistence().findByPrimaryKey(customViewDetailsSid);
	}

	/**
	* Returns the custom view details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param customViewDetailsSid the primary key of the custom view details
	* @return the custom view details, or <code>null</code> if a custom view details with the primary key could not be found
	*/
	public static CustomViewDetails fetchByPrimaryKey(int customViewDetailsSid) {
		return getPersistence().fetchByPrimaryKey(customViewDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, CustomViewDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the custom view detailses.
	*
	* @return the custom view detailses
	*/
	public static List<CustomViewDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view detailses
	* @param end the upper bound of the range of custom view detailses (not inclusive)
	* @return the range of custom view detailses
	*/
	public static List<CustomViewDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view detailses
	* @param end the upper bound of the range of custom view detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of custom view detailses
	*/
	public static List<CustomViewDetails> findAll(int start, int end,
		OrderByComparator<CustomViewDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view detailses
	* @param end the upper bound of the range of custom view detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of custom view detailses
	*/
	public static List<CustomViewDetails> findAll(int start, int end,
		OrderByComparator<CustomViewDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the custom view detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of custom view detailses.
	*
	* @return the number of custom view detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CustomViewDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CustomViewDetailsPersistence, CustomViewDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CustomViewDetailsPersistence.class);
}