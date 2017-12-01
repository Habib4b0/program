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

import com.stpl.app.model.LotMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the lot master service. This utility wraps {@link com.stpl.app.service.persistence.impl.LotMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see LotMasterPersistence
 * @see com.stpl.app.service.persistence.impl.LotMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class LotMasterUtil {
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
	public static void clearCache(LotMaster lotMaster) {
		getPersistence().clearCache(lotMaster);
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
	public static List<LotMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LotMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LotMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LotMaster update(LotMaster lotMaster) {
		return getPersistence().update(lotMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LotMaster update(LotMaster lotMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(lotMaster, serviceContext);
	}

	/**
	* Returns all the lot masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the matching lot masters
	*/
	public static List<LotMaster> findByItemId(java.lang.String itemId) {
		return getPersistence().findByItemId(itemId);
	}

	/**
	* Returns a range of all the lot masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of matching lot masters
	*/
	public static List<LotMaster> findByItemId(java.lang.String itemId,
		int start, int end) {
		return getPersistence().findByItemId(itemId, start, end);
	}

	/**
	* Returns an ordered range of all the lot masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lot masters
	*/
	public static List<LotMaster> findByItemId(java.lang.String itemId,
		int start, int end, OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence()
				   .findByItemId(itemId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the lot masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lot masters
	*/
	public static List<LotMaster> findByItemId(java.lang.String itemId,
		int start, int end, OrderByComparator<LotMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemId(itemId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first lot master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public static LotMaster findByItemId_First(java.lang.String itemId,
		OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence().findByItemId_First(itemId, orderByComparator);
	}

	/**
	* Returns the first lot master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public static LotMaster fetchByItemId_First(java.lang.String itemId,
		OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence().fetchByItemId_First(itemId, orderByComparator);
	}

	/**
	* Returns the last lot master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public static LotMaster findByItemId_Last(java.lang.String itemId,
		OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence().findByItemId_Last(itemId, orderByComparator);
	}

	/**
	* Returns the last lot master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public static LotMaster fetchByItemId_Last(java.lang.String itemId,
		OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence().fetchByItemId_Last(itemId, orderByComparator);
	}

	/**
	* Returns the lot masters before and after the current lot master in the ordered set where itemId = &#63;.
	*
	* @param lotMasterSid the primary key of the current lot master
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next lot master
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public static LotMaster[] findByItemId_PrevAndNext(int lotMasterSid,
		java.lang.String itemId, OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence()
				   .findByItemId_PrevAndNext(lotMasterSid, itemId,
			orderByComparator);
	}

	/**
	* Removes all the lot masters where itemId = &#63; from the database.
	*
	* @param itemId the item ID
	*/
	public static void removeByItemId(java.lang.String itemId) {
		getPersistence().removeByItemId(itemId);
	}

	/**
	* Returns the number of lot masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the number of matching lot masters
	*/
	public static int countByItemId(java.lang.String itemId) {
		return getPersistence().countByItemId(itemId);
	}

	/**
	* Returns all the lot masters where lotNo = &#63;.
	*
	* @param lotNo the lot no
	* @return the matching lot masters
	*/
	public static List<LotMaster> findByLotNo(java.lang.String lotNo) {
		return getPersistence().findByLotNo(lotNo);
	}

	/**
	* Returns a range of all the lot masters where lotNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lotNo the lot no
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of matching lot masters
	*/
	public static List<LotMaster> findByLotNo(java.lang.String lotNo,
		int start, int end) {
		return getPersistence().findByLotNo(lotNo, start, end);
	}

	/**
	* Returns an ordered range of all the lot masters where lotNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lotNo the lot no
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lot masters
	*/
	public static List<LotMaster> findByLotNo(java.lang.String lotNo,
		int start, int end, OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence().findByLotNo(lotNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the lot masters where lotNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lotNo the lot no
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lot masters
	*/
	public static List<LotMaster> findByLotNo(java.lang.String lotNo,
		int start, int end, OrderByComparator<LotMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLotNo(lotNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first lot master in the ordered set where lotNo = &#63;.
	*
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public static LotMaster findByLotNo_First(java.lang.String lotNo,
		OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence().findByLotNo_First(lotNo, orderByComparator);
	}

	/**
	* Returns the first lot master in the ordered set where lotNo = &#63;.
	*
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public static LotMaster fetchByLotNo_First(java.lang.String lotNo,
		OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence().fetchByLotNo_First(lotNo, orderByComparator);
	}

	/**
	* Returns the last lot master in the ordered set where lotNo = &#63;.
	*
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public static LotMaster findByLotNo_Last(java.lang.String lotNo,
		OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence().findByLotNo_Last(lotNo, orderByComparator);
	}

	/**
	* Returns the last lot master in the ordered set where lotNo = &#63;.
	*
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public static LotMaster fetchByLotNo_Last(java.lang.String lotNo,
		OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence().fetchByLotNo_Last(lotNo, orderByComparator);
	}

	/**
	* Returns the lot masters before and after the current lot master in the ordered set where lotNo = &#63;.
	*
	* @param lotMasterSid the primary key of the current lot master
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next lot master
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public static LotMaster[] findByLotNo_PrevAndNext(int lotMasterSid,
		java.lang.String lotNo, OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence()
				   .findByLotNo_PrevAndNext(lotMasterSid, lotNo,
			orderByComparator);
	}

	/**
	* Removes all the lot masters where lotNo = &#63; from the database.
	*
	* @param lotNo the lot no
	*/
	public static void removeByLotNo(java.lang.String lotNo) {
		getPersistence().removeByLotNo(lotNo);
	}

	/**
	* Returns the number of lot masters where lotNo = &#63;.
	*
	* @param lotNo the lot no
	* @return the number of matching lot masters
	*/
	public static int countByLotNo(java.lang.String lotNo) {
		return getPersistence().countByLotNo(lotNo);
	}

	/**
	* Returns all the lot masters where lotExpirationDate = &#63;.
	*
	* @param lotExpirationDate the lot expiration date
	* @return the matching lot masters
	*/
	public static List<LotMaster> findByLotExpirationDate(
		Date lotExpirationDate) {
		return getPersistence().findByLotExpirationDate(lotExpirationDate);
	}

	/**
	* Returns a range of all the lot masters where lotExpirationDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lotExpirationDate the lot expiration date
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of matching lot masters
	*/
	public static List<LotMaster> findByLotExpirationDate(
		Date lotExpirationDate, int start, int end) {
		return getPersistence()
				   .findByLotExpirationDate(lotExpirationDate, start, end);
	}

	/**
	* Returns an ordered range of all the lot masters where lotExpirationDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lotExpirationDate the lot expiration date
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lot masters
	*/
	public static List<LotMaster> findByLotExpirationDate(
		Date lotExpirationDate, int start, int end,
		OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence()
				   .findByLotExpirationDate(lotExpirationDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the lot masters where lotExpirationDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lotExpirationDate the lot expiration date
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lot masters
	*/
	public static List<LotMaster> findByLotExpirationDate(
		Date lotExpirationDate, int start, int end,
		OrderByComparator<LotMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLotExpirationDate(lotExpirationDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first lot master in the ordered set where lotExpirationDate = &#63;.
	*
	* @param lotExpirationDate the lot expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public static LotMaster findByLotExpirationDate_First(
		Date lotExpirationDate, OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence()
				   .findByLotExpirationDate_First(lotExpirationDate,
			orderByComparator);
	}

	/**
	* Returns the first lot master in the ordered set where lotExpirationDate = &#63;.
	*
	* @param lotExpirationDate the lot expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public static LotMaster fetchByLotExpirationDate_First(
		Date lotExpirationDate, OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence()
				   .fetchByLotExpirationDate_First(lotExpirationDate,
			orderByComparator);
	}

	/**
	* Returns the last lot master in the ordered set where lotExpirationDate = &#63;.
	*
	* @param lotExpirationDate the lot expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public static LotMaster findByLotExpirationDate_Last(
		Date lotExpirationDate, OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence()
				   .findByLotExpirationDate_Last(lotExpirationDate,
			orderByComparator);
	}

	/**
	* Returns the last lot master in the ordered set where lotExpirationDate = &#63;.
	*
	* @param lotExpirationDate the lot expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public static LotMaster fetchByLotExpirationDate_Last(
		Date lotExpirationDate, OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence()
				   .fetchByLotExpirationDate_Last(lotExpirationDate,
			orderByComparator);
	}

	/**
	* Returns the lot masters before and after the current lot master in the ordered set where lotExpirationDate = &#63;.
	*
	* @param lotMasterSid the primary key of the current lot master
	* @param lotExpirationDate the lot expiration date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next lot master
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public static LotMaster[] findByLotExpirationDate_PrevAndNext(
		int lotMasterSid, Date lotExpirationDate,
		OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence()
				   .findByLotExpirationDate_PrevAndNext(lotMasterSid,
			lotExpirationDate, orderByComparator);
	}

	/**
	* Removes all the lot masters where lotExpirationDate = &#63; from the database.
	*
	* @param lotExpirationDate the lot expiration date
	*/
	public static void removeByLotExpirationDate(Date lotExpirationDate) {
		getPersistence().removeByLotExpirationDate(lotExpirationDate);
	}

	/**
	* Returns the number of lot masters where lotExpirationDate = &#63;.
	*
	* @param lotExpirationDate the lot expiration date
	* @return the number of matching lot masters
	*/
	public static int countByLotExpirationDate(Date lotExpirationDate) {
		return getPersistence().countByLotExpirationDate(lotExpirationDate);
	}

	/**
	* Returns all the lot masters where lastLotSoldDate = &#63;.
	*
	* @param lastLotSoldDate the last lot sold date
	* @return the matching lot masters
	*/
	public static List<LotMaster> findByLastLotSoldDate(Date lastLotSoldDate) {
		return getPersistence().findByLastLotSoldDate(lastLotSoldDate);
	}

	/**
	* Returns a range of all the lot masters where lastLotSoldDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lastLotSoldDate the last lot sold date
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of matching lot masters
	*/
	public static List<LotMaster> findByLastLotSoldDate(Date lastLotSoldDate,
		int start, int end) {
		return getPersistence()
				   .findByLastLotSoldDate(lastLotSoldDate, start, end);
	}

	/**
	* Returns an ordered range of all the lot masters where lastLotSoldDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lastLotSoldDate the last lot sold date
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lot masters
	*/
	public static List<LotMaster> findByLastLotSoldDate(Date lastLotSoldDate,
		int start, int end, OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence()
				   .findByLastLotSoldDate(lastLotSoldDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the lot masters where lastLotSoldDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lastLotSoldDate the last lot sold date
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lot masters
	*/
	public static List<LotMaster> findByLastLotSoldDate(Date lastLotSoldDate,
		int start, int end, OrderByComparator<LotMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLastLotSoldDate(lastLotSoldDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first lot master in the ordered set where lastLotSoldDate = &#63;.
	*
	* @param lastLotSoldDate the last lot sold date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public static LotMaster findByLastLotSoldDate_First(Date lastLotSoldDate,
		OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence()
				   .findByLastLotSoldDate_First(lastLotSoldDate,
			orderByComparator);
	}

	/**
	* Returns the first lot master in the ordered set where lastLotSoldDate = &#63;.
	*
	* @param lastLotSoldDate the last lot sold date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public static LotMaster fetchByLastLotSoldDate_First(Date lastLotSoldDate,
		OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence()
				   .fetchByLastLotSoldDate_First(lastLotSoldDate,
			orderByComparator);
	}

	/**
	* Returns the last lot master in the ordered set where lastLotSoldDate = &#63;.
	*
	* @param lastLotSoldDate the last lot sold date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public static LotMaster findByLastLotSoldDate_Last(Date lastLotSoldDate,
		OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence()
				   .findByLastLotSoldDate_Last(lastLotSoldDate,
			orderByComparator);
	}

	/**
	* Returns the last lot master in the ordered set where lastLotSoldDate = &#63;.
	*
	* @param lastLotSoldDate the last lot sold date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public static LotMaster fetchByLastLotSoldDate_Last(Date lastLotSoldDate,
		OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence()
				   .fetchByLastLotSoldDate_Last(lastLotSoldDate,
			orderByComparator);
	}

	/**
	* Returns the lot masters before and after the current lot master in the ordered set where lastLotSoldDate = &#63;.
	*
	* @param lotMasterSid the primary key of the current lot master
	* @param lastLotSoldDate the last lot sold date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next lot master
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public static LotMaster[] findByLastLotSoldDate_PrevAndNext(
		int lotMasterSid, Date lastLotSoldDate,
		OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence()
				   .findByLastLotSoldDate_PrevAndNext(lotMasterSid,
			lastLotSoldDate, orderByComparator);
	}

	/**
	* Removes all the lot masters where lastLotSoldDate = &#63; from the database.
	*
	* @param lastLotSoldDate the last lot sold date
	*/
	public static void removeByLastLotSoldDate(Date lastLotSoldDate) {
		getPersistence().removeByLastLotSoldDate(lastLotSoldDate);
	}

	/**
	* Returns the number of lot masters where lastLotSoldDate = &#63;.
	*
	* @param lastLotSoldDate the last lot sold date
	* @return the number of matching lot masters
	*/
	public static int countByLastLotSoldDate(Date lastLotSoldDate) {
		return getPersistence().countByLastLotSoldDate(lastLotSoldDate);
	}

	/**
	* Returns all the lot masters where itemId = &#63; and lotNo = &#63;.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @return the matching lot masters
	*/
	public static List<LotMaster> findByLotUnique(java.lang.String itemId,
		java.lang.String lotNo) {
		return getPersistence().findByLotUnique(itemId, lotNo);
	}

	/**
	* Returns a range of all the lot masters where itemId = &#63; and lotNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of matching lot masters
	*/
	public static List<LotMaster> findByLotUnique(java.lang.String itemId,
		java.lang.String lotNo, int start, int end) {
		return getPersistence().findByLotUnique(itemId, lotNo, start, end);
	}

	/**
	* Returns an ordered range of all the lot masters where itemId = &#63; and lotNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lot masters
	*/
	public static List<LotMaster> findByLotUnique(java.lang.String itemId,
		java.lang.String lotNo, int start, int end,
		OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence()
				   .findByLotUnique(itemId, lotNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the lot masters where itemId = &#63; and lotNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lot masters
	*/
	public static List<LotMaster> findByLotUnique(java.lang.String itemId,
		java.lang.String lotNo, int start, int end,
		OrderByComparator<LotMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLotUnique(itemId, lotNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public static LotMaster findByLotUnique_First(java.lang.String itemId,
		java.lang.String lotNo, OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence()
				   .findByLotUnique_First(itemId, lotNo, orderByComparator);
	}

	/**
	* Returns the first lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public static LotMaster fetchByLotUnique_First(java.lang.String itemId,
		java.lang.String lotNo, OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence()
				   .fetchByLotUnique_First(itemId, lotNo, orderByComparator);
	}

	/**
	* Returns the last lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master
	* @throws NoSuchLotMasterException if a matching lot master could not be found
	*/
	public static LotMaster findByLotUnique_Last(java.lang.String itemId,
		java.lang.String lotNo, OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence()
				   .findByLotUnique_Last(itemId, lotNo, orderByComparator);
	}

	/**
	* Returns the last lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching lot master, or <code>null</code> if a matching lot master could not be found
	*/
	public static LotMaster fetchByLotUnique_Last(java.lang.String itemId,
		java.lang.String lotNo, OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence()
				   .fetchByLotUnique_Last(itemId, lotNo, orderByComparator);
	}

	/**
	* Returns the lot masters before and after the current lot master in the ordered set where itemId = &#63; and lotNo = &#63;.
	*
	* @param lotMasterSid the primary key of the current lot master
	* @param itemId the item ID
	* @param lotNo the lot no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next lot master
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public static LotMaster[] findByLotUnique_PrevAndNext(int lotMasterSid,
		java.lang.String itemId, java.lang.String lotNo,
		OrderByComparator<LotMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence()
				   .findByLotUnique_PrevAndNext(lotMasterSid, itemId, lotNo,
			orderByComparator);
	}

	/**
	* Removes all the lot masters where itemId = &#63; and lotNo = &#63; from the database.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	*/
	public static void removeByLotUnique(java.lang.String itemId,
		java.lang.String lotNo) {
		getPersistence().removeByLotUnique(itemId, lotNo);
	}

	/**
	* Returns the number of lot masters where itemId = &#63; and lotNo = &#63;.
	*
	* @param itemId the item ID
	* @param lotNo the lot no
	* @return the number of matching lot masters
	*/
	public static int countByLotUnique(java.lang.String itemId,
		java.lang.String lotNo) {
		return getPersistence().countByLotUnique(itemId, lotNo);
	}

	/**
	* Caches the lot master in the entity cache if it is enabled.
	*
	* @param lotMaster the lot master
	*/
	public static void cacheResult(LotMaster lotMaster) {
		getPersistence().cacheResult(lotMaster);
	}

	/**
	* Caches the lot masters in the entity cache if it is enabled.
	*
	* @param lotMasters the lot masters
	*/
	public static void cacheResult(List<LotMaster> lotMasters) {
		getPersistence().cacheResult(lotMasters);
	}

	/**
	* Creates a new lot master with the primary key. Does not add the lot master to the database.
	*
	* @param lotMasterSid the primary key for the new lot master
	* @return the new lot master
	*/
	public static LotMaster create(int lotMasterSid) {
		return getPersistence().create(lotMasterSid);
	}

	/**
	* Removes the lot master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lotMasterSid the primary key of the lot master
	* @return the lot master that was removed
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public static LotMaster remove(int lotMasterSid)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence().remove(lotMasterSid);
	}

	public static LotMaster updateImpl(LotMaster lotMaster) {
		return getPersistence().updateImpl(lotMaster);
	}

	/**
	* Returns the lot master with the primary key or throws a {@link NoSuchLotMasterException} if it could not be found.
	*
	* @param lotMasterSid the primary key of the lot master
	* @return the lot master
	* @throws NoSuchLotMasterException if a lot master with the primary key could not be found
	*/
	public static LotMaster findByPrimaryKey(int lotMasterSid)
		throws com.stpl.app.exception.NoSuchLotMasterException {
		return getPersistence().findByPrimaryKey(lotMasterSid);
	}

	/**
	* Returns the lot master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lotMasterSid the primary key of the lot master
	* @return the lot master, or <code>null</code> if a lot master with the primary key could not be found
	*/
	public static LotMaster fetchByPrimaryKey(int lotMasterSid) {
		return getPersistence().fetchByPrimaryKey(lotMasterSid);
	}

	public static java.util.Map<java.io.Serializable, LotMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the lot masters.
	*
	* @return the lot masters
	*/
	public static List<LotMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @return the range of lot masters
	*/
	public static List<LotMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of lot masters
	*/
	public static List<LotMaster> findAll(int start, int end,
		OrderByComparator<LotMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lot masters
	* @param end the upper bound of the range of lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of lot masters
	*/
	public static List<LotMaster> findAll(int start, int end,
		OrderByComparator<LotMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the lot masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of lot masters.
	*
	* @return the number of lot masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LotMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LotMasterPersistence, LotMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(LotMasterPersistence.class);
}