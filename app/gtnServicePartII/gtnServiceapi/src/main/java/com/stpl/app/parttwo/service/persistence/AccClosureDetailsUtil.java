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

import com.stpl.app.parttwo.model.AccClosureDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the acc closure details service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.AccClosureDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccClosureDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.AccClosureDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class AccClosureDetailsUtil {
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
	public static void clearCache(AccClosureDetails accClosureDetails) {
		getPersistence().clearCache(accClosureDetails);
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
	public static List<AccClosureDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccClosureDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccClosureDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccClosureDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccClosureDetails update(AccClosureDetails accClosureDetails) {
		return getPersistence().update(accClosureDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccClosureDetails update(
		AccClosureDetails accClosureDetails, ServiceContext serviceContext) {
		return getPersistence().update(accClosureDetails, serviceContext);
	}

	/**
	* Caches the acc closure details in the entity cache if it is enabled.
	*
	* @param accClosureDetails the acc closure details
	*/
	public static void cacheResult(AccClosureDetails accClosureDetails) {
		getPersistence().cacheResult(accClosureDetails);
	}

	/**
	* Caches the acc closure detailses in the entity cache if it is enabled.
	*
	* @param accClosureDetailses the acc closure detailses
	*/
	public static void cacheResult(List<AccClosureDetails> accClosureDetailses) {
		getPersistence().cacheResult(accClosureDetailses);
	}

	/**
	* Creates a new acc closure details with the primary key. Does not add the acc closure details to the database.
	*
	* @param accClosureDetailsSid the primary key for the new acc closure details
	* @return the new acc closure details
	*/
	public static AccClosureDetails create(int accClosureDetailsSid) {
		return getPersistence().create(accClosureDetailsSid);
	}

	/**
	* Removes the acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureDetailsSid the primary key of the acc closure details
	* @return the acc closure details that was removed
	* @throws NoSuchAccClosureDetailsException if a acc closure details with the primary key could not be found
	*/
	public static AccClosureDetails remove(int accClosureDetailsSid)
		throws com.stpl.app.parttwo.exception.NoSuchAccClosureDetailsException {
		return getPersistence().remove(accClosureDetailsSid);
	}

	public static AccClosureDetails updateImpl(
		AccClosureDetails accClosureDetails) {
		return getPersistence().updateImpl(accClosureDetails);
	}

	/**
	* Returns the acc closure details with the primary key or throws a {@link NoSuchAccClosureDetailsException} if it could not be found.
	*
	* @param accClosureDetailsSid the primary key of the acc closure details
	* @return the acc closure details
	* @throws NoSuchAccClosureDetailsException if a acc closure details with the primary key could not be found
	*/
	public static AccClosureDetails findByPrimaryKey(int accClosureDetailsSid)
		throws com.stpl.app.parttwo.exception.NoSuchAccClosureDetailsException {
		return getPersistence().findByPrimaryKey(accClosureDetailsSid);
	}

	/**
	* Returns the acc closure details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accClosureDetailsSid the primary key of the acc closure details
	* @return the acc closure details, or <code>null</code> if a acc closure details with the primary key could not be found
	*/
	public static AccClosureDetails fetchByPrimaryKey(int accClosureDetailsSid) {
		return getPersistence().fetchByPrimaryKey(accClosureDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, AccClosureDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the acc closure detailses.
	*
	* @return the acc closure detailses
	*/
	public static List<AccClosureDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure detailses
	* @param end the upper bound of the range of acc closure detailses (not inclusive)
	* @return the range of acc closure detailses
	*/
	public static List<AccClosureDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure detailses
	* @param end the upper bound of the range of acc closure detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of acc closure detailses
	*/
	public static List<AccClosureDetails> findAll(int start, int end,
		OrderByComparator<AccClosureDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure detailses
	* @param end the upper bound of the range of acc closure detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of acc closure detailses
	*/
	public static List<AccClosureDetails> findAll(int start, int end,
		OrderByComparator<AccClosureDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the acc closure detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of acc closure detailses.
	*
	* @return the number of acc closure detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AccClosureDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AccClosureDetailsPersistence, AccClosureDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AccClosureDetailsPersistence.class);
}