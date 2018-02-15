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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.parttwo.model.CffCustomViewDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cff custom view details service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.CffCustomViewDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffCustomViewDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.CffCustomViewDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class CffCustomViewDetailsUtil {
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
	public static void clearCache(CffCustomViewDetails cffCustomViewDetails) {
		getPersistence().clearCache(cffCustomViewDetails);
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
	public static List<CffCustomViewDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CffCustomViewDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CffCustomViewDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CffCustomViewDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CffCustomViewDetails update(
		CffCustomViewDetails cffCustomViewDetails) {
		return getPersistence().update(cffCustomViewDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CffCustomViewDetails update(
		CffCustomViewDetails cffCustomViewDetails, ServiceContext serviceContext) {
		return getPersistence().update(cffCustomViewDetails, serviceContext);
	}

	/**
	* Caches the cff custom view details in the entity cache if it is enabled.
	*
	* @param cffCustomViewDetails the cff custom view details
	*/
	public static void cacheResult(CffCustomViewDetails cffCustomViewDetails) {
		getPersistence().cacheResult(cffCustomViewDetails);
	}

	/**
	* Caches the cff custom view detailses in the entity cache if it is enabled.
	*
	* @param cffCustomViewDetailses the cff custom view detailses
	*/
	public static void cacheResult(
		List<CffCustomViewDetails> cffCustomViewDetailses) {
		getPersistence().cacheResult(cffCustomViewDetailses);
	}

	/**
	* Creates a new cff custom view details with the primary key. Does not add the cff custom view details to the database.
	*
	* @param cffCustomViewDetailsSid the primary key for the new cff custom view details
	* @return the new cff custom view details
	*/
	public static CffCustomViewDetails create(int cffCustomViewDetailsSid) {
		return getPersistence().create(cffCustomViewDetailsSid);
	}

	/**
	* Removes the cff custom view details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustomViewDetailsSid the primary key of the cff custom view details
	* @return the cff custom view details that was removed
	* @throws NoSuchCffCustomViewDetailsException if a cff custom view details with the primary key could not be found
	*/
	public static CffCustomViewDetails remove(int cffCustomViewDetailsSid)
		throws com.stpl.app.parttwo.exception.NoSuchCffCustomViewDetailsException {
		return getPersistence().remove(cffCustomViewDetailsSid);
	}

	public static CffCustomViewDetails updateImpl(
		CffCustomViewDetails cffCustomViewDetails) {
		return getPersistence().updateImpl(cffCustomViewDetails);
	}

	/**
	* Returns the cff custom view details with the primary key or throws a {@link NoSuchCffCustomViewDetailsException} if it could not be found.
	*
	* @param cffCustomViewDetailsSid the primary key of the cff custom view details
	* @return the cff custom view details
	* @throws NoSuchCffCustomViewDetailsException if a cff custom view details with the primary key could not be found
	*/
	public static CffCustomViewDetails findByPrimaryKey(
		int cffCustomViewDetailsSid)
		throws com.stpl.app.parttwo.exception.NoSuchCffCustomViewDetailsException {
		return getPersistence().findByPrimaryKey(cffCustomViewDetailsSid);
	}

	/**
	* Returns the cff custom view details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffCustomViewDetailsSid the primary key of the cff custom view details
	* @return the cff custom view details, or <code>null</code> if a cff custom view details with the primary key could not be found
	*/
	public static CffCustomViewDetails fetchByPrimaryKey(
		int cffCustomViewDetailsSid) {
		return getPersistence().fetchByPrimaryKey(cffCustomViewDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, CffCustomViewDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cff custom view detailses.
	*
	* @return the cff custom view detailses
	*/
	public static List<CffCustomViewDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cff custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff custom view detailses
	* @param end the upper bound of the range of cff custom view detailses (not inclusive)
	* @return the range of cff custom view detailses
	*/
	public static List<CffCustomViewDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cff custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff custom view detailses
	* @param end the upper bound of the range of cff custom view detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff custom view detailses
	*/
	public static List<CffCustomViewDetails> findAll(int start, int end,
		OrderByComparator<CffCustomViewDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cff custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff custom view detailses
	* @param end the upper bound of the range of cff custom view detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff custom view detailses
	*/
	public static List<CffCustomViewDetails> findAll(int start, int end,
		OrderByComparator<CffCustomViewDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cff custom view detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cff custom view detailses.
	*
	* @return the number of cff custom view detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CffCustomViewDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CffCustomViewDetailsPersistence, CffCustomViewDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CffCustomViewDetailsPersistence.class);
}