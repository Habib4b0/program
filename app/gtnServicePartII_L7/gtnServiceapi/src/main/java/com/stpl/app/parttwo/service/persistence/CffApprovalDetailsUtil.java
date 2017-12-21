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

import com.stpl.app.parttwo.model.CffApprovalDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cff approval details service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.CffApprovalDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffApprovalDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.CffApprovalDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class CffApprovalDetailsUtil {
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
	public static void clearCache(CffApprovalDetails cffApprovalDetails) {
		getPersistence().clearCache(cffApprovalDetails);
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
	public static List<CffApprovalDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CffApprovalDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CffApprovalDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CffApprovalDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CffApprovalDetails update(
		CffApprovalDetails cffApprovalDetails) {
		return getPersistence().update(cffApprovalDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CffApprovalDetails update(
		CffApprovalDetails cffApprovalDetails, ServiceContext serviceContext) {
		return getPersistence().update(cffApprovalDetails, serviceContext);
	}

	/**
	* Caches the cff approval details in the entity cache if it is enabled.
	*
	* @param cffApprovalDetails the cff approval details
	*/
	public static void cacheResult(CffApprovalDetails cffApprovalDetails) {
		getPersistence().cacheResult(cffApprovalDetails);
	}

	/**
	* Caches the cff approval detailses in the entity cache if it is enabled.
	*
	* @param cffApprovalDetailses the cff approval detailses
	*/
	public static void cacheResult(
		List<CffApprovalDetails> cffApprovalDetailses) {
		getPersistence().cacheResult(cffApprovalDetailses);
	}

	/**
	* Creates a new cff approval details with the primary key. Does not add the cff approval details to the database.
	*
	* @param cffApprovalDetailsSid the primary key for the new cff approval details
	* @return the new cff approval details
	*/
	public static CffApprovalDetails create(int cffApprovalDetailsSid) {
		return getPersistence().create(cffApprovalDetailsSid);
	}

	/**
	* Removes the cff approval details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffApprovalDetailsSid the primary key of the cff approval details
	* @return the cff approval details that was removed
	* @throws NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
	*/
	public static CffApprovalDetails remove(int cffApprovalDetailsSid)
		throws com.stpl.app.parttwo.exception.NoSuchCffApprovalDetailsException {
		return getPersistence().remove(cffApprovalDetailsSid);
	}

	public static CffApprovalDetails updateImpl(
		CffApprovalDetails cffApprovalDetails) {
		return getPersistence().updateImpl(cffApprovalDetails);
	}

	/**
	* Returns the cff approval details with the primary key or throws a {@link NoSuchCffApprovalDetailsException} if it could not be found.
	*
	* @param cffApprovalDetailsSid the primary key of the cff approval details
	* @return the cff approval details
	* @throws NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
	*/
	public static CffApprovalDetails findByPrimaryKey(int cffApprovalDetailsSid)
		throws com.stpl.app.parttwo.exception.NoSuchCffApprovalDetailsException {
		return getPersistence().findByPrimaryKey(cffApprovalDetailsSid);
	}

	/**
	* Returns the cff approval details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffApprovalDetailsSid the primary key of the cff approval details
	* @return the cff approval details, or <code>null</code> if a cff approval details with the primary key could not be found
	*/
	public static CffApprovalDetails fetchByPrimaryKey(
		int cffApprovalDetailsSid) {
		return getPersistence().fetchByPrimaryKey(cffApprovalDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, CffApprovalDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cff approval detailses.
	*
	* @return the cff approval detailses
	*/
	public static List<CffApprovalDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cff approval detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff approval detailses
	* @param end the upper bound of the range of cff approval detailses (not inclusive)
	* @return the range of cff approval detailses
	*/
	public static List<CffApprovalDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cff approval detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff approval detailses
	* @param end the upper bound of the range of cff approval detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff approval detailses
	*/
	public static List<CffApprovalDetails> findAll(int start, int end,
		OrderByComparator<CffApprovalDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cff approval detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff approval detailses
	* @param end the upper bound of the range of cff approval detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff approval detailses
	*/
	public static List<CffApprovalDetails> findAll(int start, int end,
		OrderByComparator<CffApprovalDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cff approval detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cff approval detailses.
	*
	* @return the number of cff approval detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CffApprovalDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CffApprovalDetailsPersistence, CffApprovalDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CffApprovalDetailsPersistence.class);
}