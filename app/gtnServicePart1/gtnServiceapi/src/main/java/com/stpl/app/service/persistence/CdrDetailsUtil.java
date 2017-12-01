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

import com.stpl.app.model.CdrDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cdr details service. This utility wraps {@link com.stpl.app.service.persistence.impl.CdrDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CdrDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.CdrDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class CdrDetailsUtil {
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
	public static void clearCache(CdrDetails cdrDetails) {
		getPersistence().clearCache(cdrDetails);
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
	public static List<CdrDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CdrDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CdrDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CdrDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CdrDetails update(CdrDetails cdrDetails) {
		return getPersistence().update(cdrDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CdrDetails update(CdrDetails cdrDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(cdrDetails, serviceContext);
	}

	/**
	* Caches the cdr details in the entity cache if it is enabled.
	*
	* @param cdrDetails the cdr details
	*/
	public static void cacheResult(CdrDetails cdrDetails) {
		getPersistence().cacheResult(cdrDetails);
	}

	/**
	* Caches the cdr detailses in the entity cache if it is enabled.
	*
	* @param cdrDetailses the cdr detailses
	*/
	public static void cacheResult(List<CdrDetails> cdrDetailses) {
		getPersistence().cacheResult(cdrDetailses);
	}

	/**
	* Creates a new cdr details with the primary key. Does not add the cdr details to the database.
	*
	* @param cdrDetailsSid the primary key for the new cdr details
	* @return the new cdr details
	*/
	public static CdrDetails create(int cdrDetailsSid) {
		return getPersistence().create(cdrDetailsSid);
	}

	/**
	* Removes the cdr details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cdrDetailsSid the primary key of the cdr details
	* @return the cdr details that was removed
	* @throws NoSuchCdrDetailsException if a cdr details with the primary key could not be found
	*/
	public static CdrDetails remove(int cdrDetailsSid)
		throws com.stpl.app.exception.NoSuchCdrDetailsException {
		return getPersistence().remove(cdrDetailsSid);
	}

	public static CdrDetails updateImpl(CdrDetails cdrDetails) {
		return getPersistence().updateImpl(cdrDetails);
	}

	/**
	* Returns the cdr details with the primary key or throws a {@link NoSuchCdrDetailsException} if it could not be found.
	*
	* @param cdrDetailsSid the primary key of the cdr details
	* @return the cdr details
	* @throws NoSuchCdrDetailsException if a cdr details with the primary key could not be found
	*/
	public static CdrDetails findByPrimaryKey(int cdrDetailsSid)
		throws com.stpl.app.exception.NoSuchCdrDetailsException {
		return getPersistence().findByPrimaryKey(cdrDetailsSid);
	}

	/**
	* Returns the cdr details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cdrDetailsSid the primary key of the cdr details
	* @return the cdr details, or <code>null</code> if a cdr details with the primary key could not be found
	*/
	public static CdrDetails fetchByPrimaryKey(int cdrDetailsSid) {
		return getPersistence().fetchByPrimaryKey(cdrDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, CdrDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cdr detailses.
	*
	* @return the cdr detailses
	*/
	public static List<CdrDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cdr detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cdr detailses
	* @param end the upper bound of the range of cdr detailses (not inclusive)
	* @return the range of cdr detailses
	*/
	public static List<CdrDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cdr detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cdr detailses
	* @param end the upper bound of the range of cdr detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cdr detailses
	*/
	public static List<CdrDetails> findAll(int start, int end,
		OrderByComparator<CdrDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cdr detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cdr detailses
	* @param end the upper bound of the range of cdr detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cdr detailses
	*/
	public static List<CdrDetails> findAll(int start, int end,
		OrderByComparator<CdrDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cdr detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cdr detailses.
	*
	* @return the number of cdr detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CdrDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CdrDetailsPersistence, CdrDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CdrDetailsPersistence.class);
}