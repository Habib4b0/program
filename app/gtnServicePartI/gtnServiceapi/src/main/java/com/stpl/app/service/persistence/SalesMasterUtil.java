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

import com.stpl.app.model.SalesMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the sales master service. This utility wraps {@link com.stpl.app.service.persistence.impl.SalesMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SalesMasterPersistence
 * @see com.stpl.app.service.persistence.impl.SalesMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class SalesMasterUtil {
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
	public static void clearCache(SalesMaster salesMaster) {
		getPersistence().clearCache(salesMaster);
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
	public static List<SalesMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SalesMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SalesMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SalesMaster update(SalesMaster salesMaster) {
		return getPersistence().update(salesMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SalesMaster update(SalesMaster salesMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(salesMaster, serviceContext);
	}

	/**
	* Returns all the sales masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the matching sales masters
	*/
	public static List<SalesMaster> findByAccountId(java.lang.String accountId) {
		return getPersistence().findByAccountId(accountId);
	}

	/**
	* Returns a range of all the sales masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public static List<SalesMaster> findByAccountId(
		java.lang.String accountId, int start, int end) {
		return getPersistence().findByAccountId(accountId, start, end);
	}

	/**
	* Returns an ordered range of all the sales masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .findByAccountId(accountId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sales masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountId(accountId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sales master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByAccountId_First(
		java.lang.String accountId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByAccountId_First(accountId, orderByComparator);
	}

	/**
	* Returns the first sales master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByAccountId_First(
		java.lang.String accountId,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .fetchByAccountId_First(accountId, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByAccountId_Last(java.lang.String accountId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByAccountId_Last(accountId, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByAccountId_Last(
		java.lang.String accountId,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .fetchByAccountId_Last(accountId, orderByComparator);
	}

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where accountId = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public static SalesMaster[] findByAccountId_PrevAndNext(
		int salesMasterSid, java.lang.String accountId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByAccountId_PrevAndNext(salesMasterSid, accountId,
			orderByComparator);
	}

	/**
	* Removes all the sales masters where accountId = &#63; from the database.
	*
	* @param accountId the account ID
	*/
	public static void removeByAccountId(java.lang.String accountId) {
		getPersistence().removeByAccountId(accountId);
	}

	/**
	* Returns the number of sales masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the number of matching sales masters
	*/
	public static int countByAccountId(java.lang.String accountId) {
		return getPersistence().countByAccountId(accountId);
	}

	/**
	* Returns all the sales masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the matching sales masters
	*/
	public static List<SalesMaster> findByItemNo(java.lang.String itemNo) {
		return getPersistence().findByItemNo(itemNo);
	}

	/**
	* Returns a range of all the sales masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public static List<SalesMaster> findByItemNo(java.lang.String itemNo,
		int start, int end) {
		return getPersistence().findByItemNo(itemNo, start, end);
	}

	/**
	* Returns an ordered range of all the sales masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByItemNo(java.lang.String itemNo,
		int start, int end, OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .findByItemNo(itemNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sales masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByItemNo(java.lang.String itemNo,
		int start, int end, OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemNo(itemNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sales master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByItemNo_First(java.lang.String itemNo,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence().findByItemNo_First(itemNo, orderByComparator);
	}

	/**
	* Returns the first sales master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByItemNo_First(java.lang.String itemNo,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence().fetchByItemNo_First(itemNo, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByItemNo_Last(java.lang.String itemNo,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence().findByItemNo_Last(itemNo, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByItemNo_Last(java.lang.String itemNo,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence().fetchByItemNo_Last(itemNo, orderByComparator);
	}

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where itemNo = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public static SalesMaster[] findByItemNo_PrevAndNext(int salesMasterSid,
		java.lang.String itemNo,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByItemNo_PrevAndNext(salesMasterSid, itemNo,
			orderByComparator);
	}

	/**
	* Removes all the sales masters where itemNo = &#63; from the database.
	*
	* @param itemNo the item no
	*/
	public static void removeByItemNo(java.lang.String itemNo) {
		getPersistence().removeByItemNo(itemNo);
	}

	/**
	* Returns the number of sales masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the number of matching sales masters
	*/
	public static int countByItemNo(java.lang.String itemNo) {
		return getPersistence().countByItemNo(itemNo);
	}

	/**
	* Returns all the sales masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the matching sales masters
	*/
	public static List<SalesMaster> findByItemId(java.lang.String itemId) {
		return getPersistence().findByItemId(itemId);
	}

	/**
	* Returns a range of all the sales masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public static List<SalesMaster> findByItemId(java.lang.String itemId,
		int start, int end) {
		return getPersistence().findByItemId(itemId, start, end);
	}

	/**
	* Returns an ordered range of all the sales masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByItemId(java.lang.String itemId,
		int start, int end, OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .findByItemId(itemId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sales masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByItemId(java.lang.String itemId,
		int start, int end, OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemId(itemId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sales master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByItemId_First(java.lang.String itemId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence().findByItemId_First(itemId, orderByComparator);
	}

	/**
	* Returns the first sales master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByItemId_First(java.lang.String itemId,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence().fetchByItemId_First(itemId, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByItemId_Last(java.lang.String itemId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence().findByItemId_Last(itemId, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByItemId_Last(java.lang.String itemId,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence().fetchByItemId_Last(itemId, orderByComparator);
	}

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where itemId = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public static SalesMaster[] findByItemId_PrevAndNext(int salesMasterSid,
		java.lang.String itemId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByItemId_PrevAndNext(salesMasterSid, itemId,
			orderByComparator);
	}

	/**
	* Removes all the sales masters where itemId = &#63; from the database.
	*
	* @param itemId the item ID
	*/
	public static void removeByItemId(java.lang.String itemId) {
		getPersistence().removeByItemId(itemId);
	}

	/**
	* Returns the number of sales masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the number of matching sales masters
	*/
	public static int countByItemId(java.lang.String itemId) {
		return getPersistence().countByItemId(itemId);
	}

	/**
	* Returns all the sales masters where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @return the matching sales masters
	*/
	public static List<SalesMaster> findBySalesId(java.lang.String salesId) {
		return getPersistence().findBySalesId(salesId);
	}

	/**
	* Returns a range of all the sales masters where salesId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param salesId the sales ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public static List<SalesMaster> findBySalesId(java.lang.String salesId,
		int start, int end) {
		return getPersistence().findBySalesId(salesId, start, end);
	}

	/**
	* Returns an ordered range of all the sales masters where salesId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param salesId the sales ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findBySalesId(java.lang.String salesId,
		int start, int end, OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .findBySalesId(salesId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sales masters where salesId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param salesId the sales ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findBySalesId(java.lang.String salesId,
		int start, int end, OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySalesId(salesId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findBySalesId_First(java.lang.String salesId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence().findBySalesId_First(salesId, orderByComparator);
	}

	/**
	* Returns the first sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchBySalesId_First(java.lang.String salesId,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence().fetchBySalesId_First(salesId, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findBySalesId_Last(java.lang.String salesId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence().findBySalesId_Last(salesId, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchBySalesId_Last(java.lang.String salesId,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence().fetchBySalesId_Last(salesId, orderByComparator);
	}

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where salesId = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public static SalesMaster[] findBySalesId_PrevAndNext(int salesMasterSid,
		java.lang.String salesId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findBySalesId_PrevAndNext(salesMasterSid, salesId,
			orderByComparator);
	}

	/**
	* Removes all the sales masters where salesId = &#63; from the database.
	*
	* @param salesId the sales ID
	*/
	public static void removeBySalesId(java.lang.String salesId) {
		getPersistence().removeBySalesId(salesId);
	}

	/**
	* Returns the number of sales masters where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @return the number of matching sales masters
	*/
	public static int countBySalesId(java.lang.String salesId) {
		return getPersistence().countBySalesId(salesId);
	}

	/**
	* Returns all the sales masters where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @return the matching sales masters
	*/
	public static List<SalesMaster> findByAccountNo(java.lang.String accountNo) {
		return getPersistence().findByAccountNo(accountNo);
	}

	/**
	* Returns a range of all the sales masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public static List<SalesMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end) {
		return getPersistence().findByAccountNo(accountNo, start, end);
	}

	/**
	* Returns an ordered range of all the sales masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .findByAccountNo(accountNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sales masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountNo(accountNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sales master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByAccountNo_First(
		java.lang.String accountNo,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByAccountNo_First(accountNo, orderByComparator);
	}

	/**
	* Returns the first sales master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByAccountNo_First(
		java.lang.String accountNo,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .fetchByAccountNo_First(accountNo, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByAccountNo_Last(java.lang.String accountNo,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByAccountNo_Last(accountNo, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByAccountNo_Last(
		java.lang.String accountNo,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .fetchByAccountNo_Last(accountNo, orderByComparator);
	}

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where accountNo = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public static SalesMaster[] findByAccountNo_PrevAndNext(
		int salesMasterSid, java.lang.String accountNo,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByAccountNo_PrevAndNext(salesMasterSid, accountNo,
			orderByComparator);
	}

	/**
	* Removes all the sales masters where accountNo = &#63; from the database.
	*
	* @param accountNo the account no
	*/
	public static void removeByAccountNo(java.lang.String accountNo) {
		getPersistence().removeByAccountNo(accountNo);
	}

	/**
	* Returns the number of sales masters where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @return the number of matching sales masters
	*/
	public static int countByAccountNo(java.lang.String accountNo) {
		return getPersistence().countByAccountNo(accountNo);
	}

	/**
	* Returns all the sales masters where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @return the matching sales masters
	*/
	public static List<SalesMaster> findByContractId(
		java.lang.String contractId) {
		return getPersistence().findByContractId(contractId);
	}

	/**
	* Returns a range of all the sales masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public static List<SalesMaster> findByContractId(
		java.lang.String contractId, int start, int end) {
		return getPersistence().findByContractId(contractId, start, end);
	}

	/**
	* Returns an ordered range of all the sales masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByContractId(
		java.lang.String contractId, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .findByContractId(contractId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sales masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByContractId(
		java.lang.String contractId, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByContractId(contractId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sales master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByContractId_First(
		java.lang.String contractId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByContractId_First(contractId, orderByComparator);
	}

	/**
	* Returns the first sales master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByContractId_First(
		java.lang.String contractId,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .fetchByContractId_First(contractId, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByContractId_Last(
		java.lang.String contractId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByContractId_Last(contractId, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByContractId_Last(
		java.lang.String contractId,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .fetchByContractId_Last(contractId, orderByComparator);
	}

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where contractId = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public static SalesMaster[] findByContractId_PrevAndNext(
		int salesMasterSid, java.lang.String contractId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByContractId_PrevAndNext(salesMasterSid, contractId,
			orderByComparator);
	}

	/**
	* Removes all the sales masters where contractId = &#63; from the database.
	*
	* @param contractId the contract ID
	*/
	public static void removeByContractId(java.lang.String contractId) {
		getPersistence().removeByContractId(contractId);
	}

	/**
	* Returns the number of sales masters where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @return the number of matching sales masters
	*/
	public static int countByContractId(java.lang.String contractId) {
		return getPersistence().countByContractId(contractId);
	}

	/**
	* Returns all the sales masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the matching sales masters
	*/
	public static List<SalesMaster> findByCompanyId(
		java.lang.String companyStringId) {
		return getPersistence().findByCompanyId(companyStringId);
	}

	/**
	* Returns a range of all the sales masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public static List<SalesMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end) {
		return getPersistence().findByCompanyId(companyStringId, start, end);
	}

	/**
	* Returns an ordered range of all the sales masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyStringId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the sales masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyStringId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first sales master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByCompanyId_First(
		java.lang.String companyStringId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByCompanyId_First(companyStringId, orderByComparator);
	}

	/**
	* Returns the first sales master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByCompanyId_First(
		java.lang.String companyStringId,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyStringId, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByCompanyId_Last(
		java.lang.String companyStringId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByCompanyId_Last(companyStringId, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByCompanyId_Last(
		java.lang.String companyStringId,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyStringId, orderByComparator);
	}

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where companyStringId = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public static SalesMaster[] findByCompanyId_PrevAndNext(
		int salesMasterSid, java.lang.String companyStringId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(salesMasterSid,
			companyStringId, orderByComparator);
	}

	/**
	* Removes all the sales masters where companyStringId = &#63; from the database.
	*
	* @param companyStringId the company string ID
	*/
	public static void removeByCompanyId(java.lang.String companyStringId) {
		getPersistence().removeByCompanyId(companyStringId);
	}

	/**
	* Returns the number of sales masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the number of matching sales masters
	*/
	public static int countByCompanyId(java.lang.String companyStringId) {
		return getPersistence().countByCompanyId(companyStringId);
	}

	/**
	* Returns all the sales masters where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @return the matching sales masters
	*/
	public static List<SalesMaster> findByContractNo(
		java.lang.String contractNo) {
		return getPersistence().findByContractNo(contractNo);
	}

	/**
	* Returns a range of all the sales masters where contractNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractNo the contract no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public static List<SalesMaster> findByContractNo(
		java.lang.String contractNo, int start, int end) {
		return getPersistence().findByContractNo(contractNo, start, end);
	}

	/**
	* Returns an ordered range of all the sales masters where contractNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractNo the contract no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByContractNo(
		java.lang.String contractNo, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .findByContractNo(contractNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sales masters where contractNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractNo the contract no
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findByContractNo(
		java.lang.String contractNo, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByContractNo(contractNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sales master in the ordered set where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByContractNo_First(
		java.lang.String contractNo,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByContractNo_First(contractNo, orderByComparator);
	}

	/**
	* Returns the first sales master in the ordered set where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByContractNo_First(
		java.lang.String contractNo,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .fetchByContractNo_First(contractNo, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findByContractNo_Last(
		java.lang.String contractNo,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByContractNo_Last(contractNo, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchByContractNo_Last(
		java.lang.String contractNo,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .fetchByContractNo_Last(contractNo, orderByComparator);
	}

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where contractNo = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param contractNo the contract no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public static SalesMaster[] findByContractNo_PrevAndNext(
		int salesMasterSid, java.lang.String contractNo,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findByContractNo_PrevAndNext(salesMasterSid, contractNo,
			orderByComparator);
	}

	/**
	* Removes all the sales masters where contractNo = &#63; from the database.
	*
	* @param contractNo the contract no
	*/
	public static void removeByContractNo(java.lang.String contractNo) {
		getPersistence().removeByContractNo(contractNo);
	}

	/**
	* Returns the number of sales masters where contractNo = &#63;.
	*
	* @param contractNo the contract no
	* @return the number of matching sales masters
	*/
	public static int countByContractNo(java.lang.String contractNo) {
		return getPersistence().countByContractNo(contractNo);
	}

	/**
	* Returns all the sales masters where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @return the matching sales masters
	*/
	public static List<SalesMaster> findBySalesUnique(java.lang.String salesId) {
		return getPersistence().findBySalesUnique(salesId);
	}

	/**
	* Returns a range of all the sales masters where salesId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param salesId the sales ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of matching sales masters
	*/
	public static List<SalesMaster> findBySalesUnique(
		java.lang.String salesId, int start, int end) {
		return getPersistence().findBySalesUnique(salesId, start, end);
	}

	/**
	* Returns an ordered range of all the sales masters where salesId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param salesId the sales ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findBySalesUnique(
		java.lang.String salesId, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .findBySalesUnique(salesId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sales masters where salesId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param salesId the sales ID
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sales masters
	*/
	public static List<SalesMaster> findBySalesUnique(
		java.lang.String salesId, int start, int end,
		OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySalesUnique(salesId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findBySalesUnique_First(
		java.lang.String salesId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findBySalesUnique_First(salesId, orderByComparator);
	}

	/**
	* Returns the first sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchBySalesUnique_First(
		java.lang.String salesId,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .fetchBySalesUnique_First(salesId, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master
	* @throws NoSuchSalesMasterException if a matching sales master could not be found
	*/
	public static SalesMaster findBySalesUnique_Last(java.lang.String salesId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findBySalesUnique_Last(salesId, orderByComparator);
	}

	/**
	* Returns the last sales master in the ordered set where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sales master, or <code>null</code> if a matching sales master could not be found
	*/
	public static SalesMaster fetchBySalesUnique_Last(
		java.lang.String salesId,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence()
				   .fetchBySalesUnique_Last(salesId, orderByComparator);
	}

	/**
	* Returns the sales masters before and after the current sales master in the ordered set where salesId = &#63;.
	*
	* @param salesMasterSid the primary key of the current sales master
	* @param salesId the sales ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public static SalesMaster[] findBySalesUnique_PrevAndNext(
		int salesMasterSid, java.lang.String salesId,
		OrderByComparator<SalesMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence()
				   .findBySalesUnique_PrevAndNext(salesMasterSid, salesId,
			orderByComparator);
	}

	/**
	* Removes all the sales masters where salesId = &#63; from the database.
	*
	* @param salesId the sales ID
	*/
	public static void removeBySalesUnique(java.lang.String salesId) {
		getPersistence().removeBySalesUnique(salesId);
	}

	/**
	* Returns the number of sales masters where salesId = &#63;.
	*
	* @param salesId the sales ID
	* @return the number of matching sales masters
	*/
	public static int countBySalesUnique(java.lang.String salesId) {
		return getPersistence().countBySalesUnique(salesId);
	}

	/**
	* Caches the sales master in the entity cache if it is enabled.
	*
	* @param salesMaster the sales master
	*/
	public static void cacheResult(SalesMaster salesMaster) {
		getPersistence().cacheResult(salesMaster);
	}

	/**
	* Caches the sales masters in the entity cache if it is enabled.
	*
	* @param salesMasters the sales masters
	*/
	public static void cacheResult(List<SalesMaster> salesMasters) {
		getPersistence().cacheResult(salesMasters);
	}

	/**
	* Creates a new sales master with the primary key. Does not add the sales master to the database.
	*
	* @param salesMasterSid the primary key for the new sales master
	* @return the new sales master
	*/
	public static SalesMaster create(int salesMasterSid) {
		return getPersistence().create(salesMasterSid);
	}

	/**
	* Removes the sales master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param salesMasterSid the primary key of the sales master
	* @return the sales master that was removed
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public static SalesMaster remove(int salesMasterSid)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence().remove(salesMasterSid);
	}

	public static SalesMaster updateImpl(SalesMaster salesMaster) {
		return getPersistence().updateImpl(salesMaster);
	}

	/**
	* Returns the sales master with the primary key or throws a {@link NoSuchSalesMasterException} if it could not be found.
	*
	* @param salesMasterSid the primary key of the sales master
	* @return the sales master
	* @throws NoSuchSalesMasterException if a sales master with the primary key could not be found
	*/
	public static SalesMaster findByPrimaryKey(int salesMasterSid)
		throws com.stpl.app.exception.NoSuchSalesMasterException {
		return getPersistence().findByPrimaryKey(salesMasterSid);
	}

	/**
	* Returns the sales master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param salesMasterSid the primary key of the sales master
	* @return the sales master, or <code>null</code> if a sales master with the primary key could not be found
	*/
	public static SalesMaster fetchByPrimaryKey(int salesMasterSid) {
		return getPersistence().fetchByPrimaryKey(salesMasterSid);
	}

	public static java.util.Map<java.io.Serializable, SalesMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sales masters.
	*
	* @return the sales masters
	*/
	public static List<SalesMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @return the range of sales masters
	*/
	public static List<SalesMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sales masters
	*/
	public static List<SalesMaster> findAll(int start, int end,
		OrderByComparator<SalesMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sales masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sales masters
	* @param end the upper bound of the range of sales masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sales masters
	*/
	public static List<SalesMaster> findAll(int start, int end,
		OrderByComparator<SalesMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sales masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sales masters.
	*
	* @return the number of sales masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SalesMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SalesMasterPersistence, SalesMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(SalesMasterPersistence.class);
}