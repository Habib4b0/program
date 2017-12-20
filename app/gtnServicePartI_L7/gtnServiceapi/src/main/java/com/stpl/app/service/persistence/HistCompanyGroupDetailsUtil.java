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

import com.stpl.app.model.HistCompanyGroupDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the hist company group details service. This utility wraps {@link com.stpl.app.service.persistence.impl.HistCompanyGroupDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistCompanyGroupDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.HistCompanyGroupDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class HistCompanyGroupDetailsUtil {
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
		HistCompanyGroupDetails histCompanyGroupDetails) {
		getPersistence().clearCache(histCompanyGroupDetails);
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
	public static List<HistCompanyGroupDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HistCompanyGroupDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HistCompanyGroupDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HistCompanyGroupDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HistCompanyGroupDetails update(
		HistCompanyGroupDetails histCompanyGroupDetails) {
		return getPersistence().update(histCompanyGroupDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HistCompanyGroupDetails update(
		HistCompanyGroupDetails histCompanyGroupDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(histCompanyGroupDetails, serviceContext);
	}

	/**
	* Caches the hist company group details in the entity cache if it is enabled.
	*
	* @param histCompanyGroupDetails the hist company group details
	*/
	public static void cacheResult(
		HistCompanyGroupDetails histCompanyGroupDetails) {
		getPersistence().cacheResult(histCompanyGroupDetails);
	}

	/**
	* Caches the hist company group detailses in the entity cache if it is enabled.
	*
	* @param histCompanyGroupDetailses the hist company group detailses
	*/
	public static void cacheResult(
		List<HistCompanyGroupDetails> histCompanyGroupDetailses) {
		getPersistence().cacheResult(histCompanyGroupDetailses);
	}

	/**
	* Creates a new hist company group details with the primary key. Does not add the hist company group details to the database.
	*
	* @param histCompanyGroupDetailsPK the primary key for the new hist company group details
	* @return the new hist company group details
	*/
	public static HistCompanyGroupDetails create(
		HistCompanyGroupDetailsPK histCompanyGroupDetailsPK) {
		return getPersistence().create(histCompanyGroupDetailsPK);
	}

	/**
	* Removes the hist company group details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histCompanyGroupDetailsPK the primary key of the hist company group details
	* @return the hist company group details that was removed
	* @throws NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
	*/
	public static HistCompanyGroupDetails remove(
		HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
		throws com.stpl.app.exception.NoSuchHistCompanyGroupDetailsException {
		return getPersistence().remove(histCompanyGroupDetailsPK);
	}

	public static HistCompanyGroupDetails updateImpl(
		HistCompanyGroupDetails histCompanyGroupDetails) {
		return getPersistence().updateImpl(histCompanyGroupDetails);
	}

	/**
	* Returns the hist company group details with the primary key or throws a {@link NoSuchHistCompanyGroupDetailsException} if it could not be found.
	*
	* @param histCompanyGroupDetailsPK the primary key of the hist company group details
	* @return the hist company group details
	* @throws NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
	*/
	public static HistCompanyGroupDetails findByPrimaryKey(
		HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
		throws com.stpl.app.exception.NoSuchHistCompanyGroupDetailsException {
		return getPersistence().findByPrimaryKey(histCompanyGroupDetailsPK);
	}

	/**
	* Returns the hist company group details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param histCompanyGroupDetailsPK the primary key of the hist company group details
	* @return the hist company group details, or <code>null</code> if a hist company group details with the primary key could not be found
	*/
	public static HistCompanyGroupDetails fetchByPrimaryKey(
		HistCompanyGroupDetailsPK histCompanyGroupDetailsPK) {
		return getPersistence().fetchByPrimaryKey(histCompanyGroupDetailsPK);
	}

	public static java.util.Map<java.io.Serializable, HistCompanyGroupDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the hist company group detailses.
	*
	* @return the hist company group detailses
	*/
	public static List<HistCompanyGroupDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the hist company group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist company group detailses
	* @param end the upper bound of the range of hist company group detailses (not inclusive)
	* @return the range of hist company group detailses
	*/
	public static List<HistCompanyGroupDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the hist company group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist company group detailses
	* @param end the upper bound of the range of hist company group detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist company group detailses
	*/
	public static List<HistCompanyGroupDetails> findAll(int start, int end,
		OrderByComparator<HistCompanyGroupDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the hist company group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist company group detailses
	* @param end the upper bound of the range of hist company group detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist company group detailses
	*/
	public static List<HistCompanyGroupDetails> findAll(int start, int end,
		OrderByComparator<HistCompanyGroupDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the hist company group detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of hist company group detailses.
	*
	* @return the number of hist company group detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HistCompanyGroupDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistCompanyGroupDetailsPersistence, HistCompanyGroupDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(HistCompanyGroupDetailsPersistence.class);
}