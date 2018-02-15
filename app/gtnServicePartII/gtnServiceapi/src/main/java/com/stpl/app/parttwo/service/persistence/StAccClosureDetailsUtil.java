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

import com.stpl.app.parttwo.model.StAccClosureDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st acc closure details service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.StAccClosureDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAccClosureDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.StAccClosureDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class StAccClosureDetailsUtil {
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
	public static void clearCache(StAccClosureDetails stAccClosureDetails) {
		getPersistence().clearCache(stAccClosureDetails);
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
	public static List<StAccClosureDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StAccClosureDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StAccClosureDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StAccClosureDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StAccClosureDetails update(
		StAccClosureDetails stAccClosureDetails) {
		return getPersistence().update(stAccClosureDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StAccClosureDetails update(
		StAccClosureDetails stAccClosureDetails, ServiceContext serviceContext) {
		return getPersistence().update(stAccClosureDetails, serviceContext);
	}

	/**
	* Caches the st acc closure details in the entity cache if it is enabled.
	*
	* @param stAccClosureDetails the st acc closure details
	*/
	public static void cacheResult(StAccClosureDetails stAccClosureDetails) {
		getPersistence().cacheResult(stAccClosureDetails);
	}

	/**
	* Caches the st acc closure detailses in the entity cache if it is enabled.
	*
	* @param stAccClosureDetailses the st acc closure detailses
	*/
	public static void cacheResult(
		List<StAccClosureDetails> stAccClosureDetailses) {
		getPersistence().cacheResult(stAccClosureDetailses);
	}

	/**
	* Creates a new st acc closure details with the primary key. Does not add the st acc closure details to the database.
	*
	* @param accClosureMasterSid the primary key for the new st acc closure details
	* @return the new st acc closure details
	*/
	public static StAccClosureDetails create(int accClosureMasterSid) {
		return getPersistence().create(accClosureMasterSid);
	}

	/**
	* Removes the st acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureMasterSid the primary key of the st acc closure details
	* @return the st acc closure details that was removed
	* @throws NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
	*/
	public static StAccClosureDetails remove(int accClosureMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchStAccClosureDetailsException {
		return getPersistence().remove(accClosureMasterSid);
	}

	public static StAccClosureDetails updateImpl(
		StAccClosureDetails stAccClosureDetails) {
		return getPersistence().updateImpl(stAccClosureDetails);
	}

	/**
	* Returns the st acc closure details with the primary key or throws a {@link NoSuchStAccClosureDetailsException} if it could not be found.
	*
	* @param accClosureMasterSid the primary key of the st acc closure details
	* @return the st acc closure details
	* @throws NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
	*/
	public static StAccClosureDetails findByPrimaryKey(int accClosureMasterSid)
		throws com.stpl.app.parttwo.exception.NoSuchStAccClosureDetailsException {
		return getPersistence().findByPrimaryKey(accClosureMasterSid);
	}

	/**
	* Returns the st acc closure details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accClosureMasterSid the primary key of the st acc closure details
	* @return the st acc closure details, or <code>null</code> if a st acc closure details with the primary key could not be found
	*/
	public static StAccClosureDetails fetchByPrimaryKey(int accClosureMasterSid) {
		return getPersistence().fetchByPrimaryKey(accClosureMasterSid);
	}

	public static java.util.Map<java.io.Serializable, StAccClosureDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st acc closure detailses.
	*
	* @return the st acc closure detailses
	*/
	public static List<StAccClosureDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the st acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st acc closure detailses
	* @param end the upper bound of the range of st acc closure detailses (not inclusive)
	* @return the range of st acc closure detailses
	*/
	public static List<StAccClosureDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the st acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st acc closure detailses
	* @param end the upper bound of the range of st acc closure detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st acc closure detailses
	*/
	public static List<StAccClosureDetails> findAll(int start, int end,
		OrderByComparator<StAccClosureDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the st acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st acc closure detailses
	* @param end the upper bound of the range of st acc closure detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st acc closure detailses
	*/
	public static List<StAccClosureDetails> findAll(int start, int end,
		OrderByComparator<StAccClosureDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st acc closure detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st acc closure detailses.
	*
	* @return the number of st acc closure detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StAccClosureDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StAccClosureDetailsPersistence, StAccClosureDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StAccClosureDetailsPersistence.class);
}