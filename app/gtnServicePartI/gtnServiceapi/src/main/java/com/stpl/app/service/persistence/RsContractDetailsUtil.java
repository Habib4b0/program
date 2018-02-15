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

import com.stpl.app.model.RsContractDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the rs contract details service. This utility wraps {@link com.stpl.app.service.persistence.impl.RsContractDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsContractDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.RsContractDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class RsContractDetailsUtil {
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
	public static void clearCache(RsContractDetails rsContractDetails) {
		getPersistence().clearCache(rsContractDetails);
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
	public static List<RsContractDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RsContractDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RsContractDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RsContractDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RsContractDetails update(RsContractDetails rsContractDetails) {
		return getPersistence().update(rsContractDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RsContractDetails update(
		RsContractDetails rsContractDetails, ServiceContext serviceContext) {
		return getPersistence().update(rsContractDetails, serviceContext);
	}

	/**
	* Returns all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @return the matching rs contract detailses
	*/
	public static List<RsContractDetails> findByRebateScheduleDetails(
		int rsContractSid, int itemMasterSid) {
		return getPersistence()
				   .findByRebateScheduleDetails(rsContractSid, itemMasterSid);
	}

	/**
	* Returns a range of all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @return the range of matching rs contract detailses
	*/
	public static List<RsContractDetails> findByRebateScheduleDetails(
		int rsContractSid, int itemMasterSid, int start, int end) {
		return getPersistence()
				   .findByRebateScheduleDetails(rsContractSid, itemMasterSid,
			start, end);
	}

	/**
	* Returns an ordered range of all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rs contract detailses
	*/
	public static List<RsContractDetails> findByRebateScheduleDetails(
		int rsContractSid, int itemMasterSid, int start, int end,
		OrderByComparator<RsContractDetails> orderByComparator) {
		return getPersistence()
				   .findByRebateScheduleDetails(rsContractSid, itemMasterSid,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rs contract detailses
	*/
	public static List<RsContractDetails> findByRebateScheduleDetails(
		int rsContractSid, int itemMasterSid, int start, int end,
		OrderByComparator<RsContractDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRebateScheduleDetails(rsContractSid, itemMasterSid,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs contract details
	* @throws NoSuchRsContractDetailsException if a matching rs contract details could not be found
	*/
	public static RsContractDetails findByRebateScheduleDetails_First(
		int rsContractSid, int itemMasterSid,
		OrderByComparator<RsContractDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsContractDetailsException {
		return getPersistence()
				   .findByRebateScheduleDetails_First(rsContractSid,
			itemMasterSid, orderByComparator);
	}

	/**
	* Returns the first rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rs contract details, or <code>null</code> if a matching rs contract details could not be found
	*/
	public static RsContractDetails fetchByRebateScheduleDetails_First(
		int rsContractSid, int itemMasterSid,
		OrderByComparator<RsContractDetails> orderByComparator) {
		return getPersistence()
				   .fetchByRebateScheduleDetails_First(rsContractSid,
			itemMasterSid, orderByComparator);
	}

	/**
	* Returns the last rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs contract details
	* @throws NoSuchRsContractDetailsException if a matching rs contract details could not be found
	*/
	public static RsContractDetails findByRebateScheduleDetails_Last(
		int rsContractSid, int itemMasterSid,
		OrderByComparator<RsContractDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsContractDetailsException {
		return getPersistence()
				   .findByRebateScheduleDetails_Last(rsContractSid,
			itemMasterSid, orderByComparator);
	}

	/**
	* Returns the last rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rs contract details, or <code>null</code> if a matching rs contract details could not be found
	*/
	public static RsContractDetails fetchByRebateScheduleDetails_Last(
		int rsContractSid, int itemMasterSid,
		OrderByComparator<RsContractDetails> orderByComparator) {
		return getPersistence()
				   .fetchByRebateScheduleDetails_Last(rsContractSid,
			itemMasterSid, orderByComparator);
	}

	/**
	* Returns the rs contract detailses before and after the current rs contract details in the ordered set where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractDetailsSid the primary key of the current rs contract details
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rs contract details
	* @throws NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
	*/
	public static RsContractDetails[] findByRebateScheduleDetails_PrevAndNext(
		int rsContractDetailsSid, int rsContractSid, int itemMasterSid,
		OrderByComparator<RsContractDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchRsContractDetailsException {
		return getPersistence()
				   .findByRebateScheduleDetails_PrevAndNext(rsContractDetailsSid,
			rsContractSid, itemMasterSid, orderByComparator);
	}

	/**
	* Removes all the rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63; from the database.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	*/
	public static void removeByRebateScheduleDetails(int rsContractSid,
		int itemMasterSid) {
		getPersistence()
			.removeByRebateScheduleDetails(rsContractSid, itemMasterSid);
	}

	/**
	* Returns the number of rs contract detailses where rsContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param rsContractSid the rs contract sid
	* @param itemMasterSid the item master sid
	* @return the number of matching rs contract detailses
	*/
	public static int countByRebateScheduleDetails(int rsContractSid,
		int itemMasterSid) {
		return getPersistence()
				   .countByRebateScheduleDetails(rsContractSid, itemMasterSid);
	}

	/**
	* Caches the rs contract details in the entity cache if it is enabled.
	*
	* @param rsContractDetails the rs contract details
	*/
	public static void cacheResult(RsContractDetails rsContractDetails) {
		getPersistence().cacheResult(rsContractDetails);
	}

	/**
	* Caches the rs contract detailses in the entity cache if it is enabled.
	*
	* @param rsContractDetailses the rs contract detailses
	*/
	public static void cacheResult(List<RsContractDetails> rsContractDetailses) {
		getPersistence().cacheResult(rsContractDetailses);
	}

	/**
	* Creates a new rs contract details with the primary key. Does not add the rs contract details to the database.
	*
	* @param rsContractDetailsSid the primary key for the new rs contract details
	* @return the new rs contract details
	*/
	public static RsContractDetails create(int rsContractDetailsSid) {
		return getPersistence().create(rsContractDetailsSid);
	}

	/**
	* Removes the rs contract details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsContractDetailsSid the primary key of the rs contract details
	* @return the rs contract details that was removed
	* @throws NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
	*/
	public static RsContractDetails remove(int rsContractDetailsSid)
		throws com.stpl.app.exception.NoSuchRsContractDetailsException {
		return getPersistence().remove(rsContractDetailsSid);
	}

	public static RsContractDetails updateImpl(
		RsContractDetails rsContractDetails) {
		return getPersistence().updateImpl(rsContractDetails);
	}

	/**
	* Returns the rs contract details with the primary key or throws a {@link NoSuchRsContractDetailsException} if it could not be found.
	*
	* @param rsContractDetailsSid the primary key of the rs contract details
	* @return the rs contract details
	* @throws NoSuchRsContractDetailsException if a rs contract details with the primary key could not be found
	*/
	public static RsContractDetails findByPrimaryKey(int rsContractDetailsSid)
		throws com.stpl.app.exception.NoSuchRsContractDetailsException {
		return getPersistence().findByPrimaryKey(rsContractDetailsSid);
	}

	/**
	* Returns the rs contract details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rsContractDetailsSid the primary key of the rs contract details
	* @return the rs contract details, or <code>null</code> if a rs contract details with the primary key could not be found
	*/
	public static RsContractDetails fetchByPrimaryKey(int rsContractDetailsSid) {
		return getPersistence().fetchByPrimaryKey(rsContractDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, RsContractDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the rs contract detailses.
	*
	* @return the rs contract detailses
	*/
	public static List<RsContractDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the rs contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @return the range of rs contract detailses
	*/
	public static List<RsContractDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the rs contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rs contract detailses
	*/
	public static List<RsContractDetails> findAll(int start, int end,
		OrderByComparator<RsContractDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the rs contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs contract detailses
	* @param end the upper bound of the range of rs contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rs contract detailses
	*/
	public static List<RsContractDetails> findAll(int start, int end,
		OrderByComparator<RsContractDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the rs contract detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of rs contract detailses.
	*
	* @return the number of rs contract detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RsContractDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RsContractDetailsPersistence, RsContractDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(RsContractDetailsPersistence.class);
}