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

import com.stpl.app.model.RebatePlanMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the rebate plan master service. This utility wraps {@link com.stpl.app.service.persistence.impl.RebatePlanMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebatePlanMasterPersistence
 * @see com.stpl.app.service.persistence.impl.RebatePlanMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class RebatePlanMasterUtil {
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
	public static void clearCache(RebatePlanMaster rebatePlanMaster) {
		getPersistence().clearCache(rebatePlanMaster);
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
	public static List<RebatePlanMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RebatePlanMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RebatePlanMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RebatePlanMaster update(RebatePlanMaster rebatePlanMaster) {
		return getPersistence().update(rebatePlanMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RebatePlanMaster update(RebatePlanMaster rebatePlanMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(rebatePlanMaster, serviceContext);
	}

	/**
	* Returns all the rebate plan masters where rebatePlanId = &#63;.
	*
	* @param rebatePlanId the rebate plan ID
	* @return the matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanId(
		java.lang.String rebatePlanId) {
		return getPersistence().findByRebatePlanId(rebatePlanId);
	}

	/**
	* Returns a range of all the rebate plan masters where rebatePlanId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanId the rebate plan ID
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanId(
		java.lang.String rebatePlanId, int start, int end) {
		return getPersistence().findByRebatePlanId(rebatePlanId, start, end);
	}

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanId the rebate plan ID
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanId(
		java.lang.String rebatePlanId, int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .findByRebatePlanId(rebatePlanId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanId the rebate plan ID
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanId(
		java.lang.String rebatePlanId, int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRebatePlanId(rebatePlanId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanId = &#63;.
	*
	* @param rebatePlanId the rebate plan ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster findByRebatePlanId_First(
		java.lang.String rebatePlanId,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanId_First(rebatePlanId, orderByComparator);
	}

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanId = &#63;.
	*
	* @param rebatePlanId the rebate plan ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster fetchByRebatePlanId_First(
		java.lang.String rebatePlanId,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .fetchByRebatePlanId_First(rebatePlanId, orderByComparator);
	}

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanId = &#63;.
	*
	* @param rebatePlanId the rebate plan ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster findByRebatePlanId_Last(
		java.lang.String rebatePlanId,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanId_Last(rebatePlanId, orderByComparator);
	}

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanId = &#63;.
	*
	* @param rebatePlanId the rebate plan ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster fetchByRebatePlanId_Last(
		java.lang.String rebatePlanId,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .fetchByRebatePlanId_Last(rebatePlanId, orderByComparator);
	}

	/**
	* Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanId = &#63;.
	*
	* @param rebatePlanMasterSid the primary key of the current rebate plan master
	* @param rebatePlanId the rebate plan ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rebate plan master
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public static RebatePlanMaster[] findByRebatePlanId_PrevAndNext(
		int rebatePlanMasterSid, java.lang.String rebatePlanId,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanId_PrevAndNext(rebatePlanMasterSid,
			rebatePlanId, orderByComparator);
	}

	/**
	* Removes all the rebate plan masters where rebatePlanId = &#63; from the database.
	*
	* @param rebatePlanId the rebate plan ID
	*/
	public static void removeByRebatePlanId(java.lang.String rebatePlanId) {
		getPersistence().removeByRebatePlanId(rebatePlanId);
	}

	/**
	* Returns the number of rebate plan masters where rebatePlanId = &#63;.
	*
	* @param rebatePlanId the rebate plan ID
	* @return the number of matching rebate plan masters
	*/
	public static int countByRebatePlanId(java.lang.String rebatePlanId) {
		return getPersistence().countByRebatePlanId(rebatePlanId);
	}

	/**
	* Returns all the rebate plan masters where rebatePlanNo = &#63;.
	*
	* @param rebatePlanNo the rebate plan no
	* @return the matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanNo(
		java.lang.String rebatePlanNo) {
		return getPersistence().findByRebatePlanNo(rebatePlanNo);
	}

	/**
	* Returns a range of all the rebate plan masters where rebatePlanNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanNo the rebate plan no
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanNo(
		java.lang.String rebatePlanNo, int start, int end) {
		return getPersistence().findByRebatePlanNo(rebatePlanNo, start, end);
	}

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanNo the rebate plan no
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanNo(
		java.lang.String rebatePlanNo, int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .findByRebatePlanNo(rebatePlanNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanNo the rebate plan no
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanNo(
		java.lang.String rebatePlanNo, int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRebatePlanNo(rebatePlanNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanNo = &#63;.
	*
	* @param rebatePlanNo the rebate plan no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster findByRebatePlanNo_First(
		java.lang.String rebatePlanNo,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanNo_First(rebatePlanNo, orderByComparator);
	}

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanNo = &#63;.
	*
	* @param rebatePlanNo the rebate plan no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster fetchByRebatePlanNo_First(
		java.lang.String rebatePlanNo,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .fetchByRebatePlanNo_First(rebatePlanNo, orderByComparator);
	}

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanNo = &#63;.
	*
	* @param rebatePlanNo the rebate plan no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster findByRebatePlanNo_Last(
		java.lang.String rebatePlanNo,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanNo_Last(rebatePlanNo, orderByComparator);
	}

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanNo = &#63;.
	*
	* @param rebatePlanNo the rebate plan no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster fetchByRebatePlanNo_Last(
		java.lang.String rebatePlanNo,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .fetchByRebatePlanNo_Last(rebatePlanNo, orderByComparator);
	}

	/**
	* Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanNo = &#63;.
	*
	* @param rebatePlanMasterSid the primary key of the current rebate plan master
	* @param rebatePlanNo the rebate plan no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rebate plan master
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public static RebatePlanMaster[] findByRebatePlanNo_PrevAndNext(
		int rebatePlanMasterSid, java.lang.String rebatePlanNo,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanNo_PrevAndNext(rebatePlanMasterSid,
			rebatePlanNo, orderByComparator);
	}

	/**
	* Removes all the rebate plan masters where rebatePlanNo = &#63; from the database.
	*
	* @param rebatePlanNo the rebate plan no
	*/
	public static void removeByRebatePlanNo(java.lang.String rebatePlanNo) {
		getPersistence().removeByRebatePlanNo(rebatePlanNo);
	}

	/**
	* Returns the number of rebate plan masters where rebatePlanNo = &#63;.
	*
	* @param rebatePlanNo the rebate plan no
	* @return the number of matching rebate plan masters
	*/
	public static int countByRebatePlanNo(java.lang.String rebatePlanNo) {
		return getPersistence().countByRebatePlanNo(rebatePlanNo);
	}

	/**
	* Returns all the rebate plan masters where rebatePlanName = &#63;.
	*
	* @param rebatePlanName the rebate plan name
	* @return the matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanName(
		java.lang.String rebatePlanName) {
		return getPersistence().findByRebatePlanName(rebatePlanName);
	}

	/**
	* Returns a range of all the rebate plan masters where rebatePlanName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanName the rebate plan name
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanName(
		java.lang.String rebatePlanName, int start, int end) {
		return getPersistence().findByRebatePlanName(rebatePlanName, start, end);
	}

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanName the rebate plan name
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanName(
		java.lang.String rebatePlanName, int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .findByRebatePlanName(rebatePlanName, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanName the rebate plan name
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanName(
		java.lang.String rebatePlanName, int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRebatePlanName(rebatePlanName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanName = &#63;.
	*
	* @param rebatePlanName the rebate plan name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster findByRebatePlanName_First(
		java.lang.String rebatePlanName,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanName_First(rebatePlanName, orderByComparator);
	}

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanName = &#63;.
	*
	* @param rebatePlanName the rebate plan name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster fetchByRebatePlanName_First(
		java.lang.String rebatePlanName,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .fetchByRebatePlanName_First(rebatePlanName,
			orderByComparator);
	}

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanName = &#63;.
	*
	* @param rebatePlanName the rebate plan name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster findByRebatePlanName_Last(
		java.lang.String rebatePlanName,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanName_Last(rebatePlanName, orderByComparator);
	}

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanName = &#63;.
	*
	* @param rebatePlanName the rebate plan name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster fetchByRebatePlanName_Last(
		java.lang.String rebatePlanName,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .fetchByRebatePlanName_Last(rebatePlanName, orderByComparator);
	}

	/**
	* Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanName = &#63;.
	*
	* @param rebatePlanMasterSid the primary key of the current rebate plan master
	* @param rebatePlanName the rebate plan name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rebate plan master
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public static RebatePlanMaster[] findByRebatePlanName_PrevAndNext(
		int rebatePlanMasterSid, java.lang.String rebatePlanName,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanName_PrevAndNext(rebatePlanMasterSid,
			rebatePlanName, orderByComparator);
	}

	/**
	* Removes all the rebate plan masters where rebatePlanName = &#63; from the database.
	*
	* @param rebatePlanName the rebate plan name
	*/
	public static void removeByRebatePlanName(java.lang.String rebatePlanName) {
		getPersistence().removeByRebatePlanName(rebatePlanName);
	}

	/**
	* Returns the number of rebate plan masters where rebatePlanName = &#63;.
	*
	* @param rebatePlanName the rebate plan name
	* @return the number of matching rebate plan masters
	*/
	public static int countByRebatePlanName(java.lang.String rebatePlanName) {
		return getPersistence().countByRebatePlanName(rebatePlanName);
	}

	/**
	* Returns all the rebate plan masters where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanStatus the rebate plan status
	* @return the matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanStatus(
		int rebatePlanStatus) {
		return getPersistence().findByRebatePlanStatus(rebatePlanStatus);
	}

	/**
	* Returns a range of all the rebate plan masters where rebatePlanStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanStatus the rebate plan status
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanStatus(
		int rebatePlanStatus, int start, int end) {
		return getPersistence()
				   .findByRebatePlanStatus(rebatePlanStatus, start, end);
	}

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanStatus the rebate plan status
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanStatus(
		int rebatePlanStatus, int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .findByRebatePlanStatus(rebatePlanStatus, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanStatus the rebate plan status
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanStatus(
		int rebatePlanStatus, int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRebatePlanStatus(rebatePlanStatus, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanStatus the rebate plan status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster findByRebatePlanStatus_First(
		int rebatePlanStatus,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanStatus_First(rebatePlanStatus,
			orderByComparator);
	}

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanStatus the rebate plan status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster fetchByRebatePlanStatus_First(
		int rebatePlanStatus,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .fetchByRebatePlanStatus_First(rebatePlanStatus,
			orderByComparator);
	}

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanStatus the rebate plan status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster findByRebatePlanStatus_Last(
		int rebatePlanStatus,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanStatus_Last(rebatePlanStatus,
			orderByComparator);
	}

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanStatus the rebate plan status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster fetchByRebatePlanStatus_Last(
		int rebatePlanStatus,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .fetchByRebatePlanStatus_Last(rebatePlanStatus,
			orderByComparator);
	}

	/**
	* Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanMasterSid the primary key of the current rebate plan master
	* @param rebatePlanStatus the rebate plan status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rebate plan master
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public static RebatePlanMaster[] findByRebatePlanStatus_PrevAndNext(
		int rebatePlanMasterSid, int rebatePlanStatus,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanStatus_PrevAndNext(rebatePlanMasterSid,
			rebatePlanStatus, orderByComparator);
	}

	/**
	* Removes all the rebate plan masters where rebatePlanStatus = &#63; from the database.
	*
	* @param rebatePlanStatus the rebate plan status
	*/
	public static void removeByRebatePlanStatus(int rebatePlanStatus) {
		getPersistence().removeByRebatePlanStatus(rebatePlanStatus);
	}

	/**
	* Returns the number of rebate plan masters where rebatePlanStatus = &#63;.
	*
	* @param rebatePlanStatus the rebate plan status
	* @return the number of matching rebate plan masters
	*/
	public static int countByRebatePlanStatus(int rebatePlanStatus) {
		return getPersistence().countByRebatePlanStatus(rebatePlanStatus);
	}

	/**
	* Returns all the rebate plan masters where rebatePlanType = &#63;.
	*
	* @param rebatePlanType the rebate plan type
	* @return the matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanType(
		int rebatePlanType) {
		return getPersistence().findByRebatePlanType(rebatePlanType);
	}

	/**
	* Returns a range of all the rebate plan masters where rebatePlanType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanType the rebate plan type
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanType(
		int rebatePlanType, int start, int end) {
		return getPersistence().findByRebatePlanType(rebatePlanType, start, end);
	}

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanType the rebate plan type
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanType(
		int rebatePlanType, int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .findByRebatePlanType(rebatePlanType, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the rebate plan masters where rebatePlanType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param rebatePlanType the rebate plan type
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching rebate plan masters
	*/
	public static List<RebatePlanMaster> findByRebatePlanType(
		int rebatePlanType, int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRebatePlanType(rebatePlanType, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanType = &#63;.
	*
	* @param rebatePlanType the rebate plan type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster findByRebatePlanType_First(
		int rebatePlanType,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanType_First(rebatePlanType, orderByComparator);
	}

	/**
	* Returns the first rebate plan master in the ordered set where rebatePlanType = &#63;.
	*
	* @param rebatePlanType the rebate plan type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster fetchByRebatePlanType_First(
		int rebatePlanType,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .fetchByRebatePlanType_First(rebatePlanType,
			orderByComparator);
	}

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanType = &#63;.
	*
	* @param rebatePlanType the rebate plan type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master
	* @throws NoSuchRebatePlanMasterException if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster findByRebatePlanType_Last(
		int rebatePlanType,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanType_Last(rebatePlanType, orderByComparator);
	}

	/**
	* Returns the last rebate plan master in the ordered set where rebatePlanType = &#63;.
	*
	* @param rebatePlanType the rebate plan type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching rebate plan master, or <code>null</code> if a matching rebate plan master could not be found
	*/
	public static RebatePlanMaster fetchByRebatePlanType_Last(
		int rebatePlanType,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence()
				   .fetchByRebatePlanType_Last(rebatePlanType, orderByComparator);
	}

	/**
	* Returns the rebate plan masters before and after the current rebate plan master in the ordered set where rebatePlanType = &#63;.
	*
	* @param rebatePlanMasterSid the primary key of the current rebate plan master
	* @param rebatePlanType the rebate plan type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next rebate plan master
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public static RebatePlanMaster[] findByRebatePlanType_PrevAndNext(
		int rebatePlanMasterSid, int rebatePlanType,
		OrderByComparator<RebatePlanMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence()
				   .findByRebatePlanType_PrevAndNext(rebatePlanMasterSid,
			rebatePlanType, orderByComparator);
	}

	/**
	* Removes all the rebate plan masters where rebatePlanType = &#63; from the database.
	*
	* @param rebatePlanType the rebate plan type
	*/
	public static void removeByRebatePlanType(int rebatePlanType) {
		getPersistence().removeByRebatePlanType(rebatePlanType);
	}

	/**
	* Returns the number of rebate plan masters where rebatePlanType = &#63;.
	*
	* @param rebatePlanType the rebate plan type
	* @return the number of matching rebate plan masters
	*/
	public static int countByRebatePlanType(int rebatePlanType) {
		return getPersistence().countByRebatePlanType(rebatePlanType);
	}

	/**
	* Caches the rebate plan master in the entity cache if it is enabled.
	*
	* @param rebatePlanMaster the rebate plan master
	*/
	public static void cacheResult(RebatePlanMaster rebatePlanMaster) {
		getPersistence().cacheResult(rebatePlanMaster);
	}

	/**
	* Caches the rebate plan masters in the entity cache if it is enabled.
	*
	* @param rebatePlanMasters the rebate plan masters
	*/
	public static void cacheResult(List<RebatePlanMaster> rebatePlanMasters) {
		getPersistence().cacheResult(rebatePlanMasters);
	}

	/**
	* Creates a new rebate plan master with the primary key. Does not add the rebate plan master to the database.
	*
	* @param rebatePlanMasterSid the primary key for the new rebate plan master
	* @return the new rebate plan master
	*/
	public static RebatePlanMaster create(int rebatePlanMasterSid) {
		return getPersistence().create(rebatePlanMasterSid);
	}

	/**
	* Removes the rebate plan master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rebatePlanMasterSid the primary key of the rebate plan master
	* @return the rebate plan master that was removed
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public static RebatePlanMaster remove(int rebatePlanMasterSid)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence().remove(rebatePlanMasterSid);
	}

	public static RebatePlanMaster updateImpl(RebatePlanMaster rebatePlanMaster) {
		return getPersistence().updateImpl(rebatePlanMaster);
	}

	/**
	* Returns the rebate plan master with the primary key or throws a {@link NoSuchRebatePlanMasterException} if it could not be found.
	*
	* @param rebatePlanMasterSid the primary key of the rebate plan master
	* @return the rebate plan master
	* @throws NoSuchRebatePlanMasterException if a rebate plan master with the primary key could not be found
	*/
	public static RebatePlanMaster findByPrimaryKey(int rebatePlanMasterSid)
		throws com.stpl.app.exception.NoSuchRebatePlanMasterException {
		return getPersistence().findByPrimaryKey(rebatePlanMasterSid);
	}

	/**
	* Returns the rebate plan master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rebatePlanMasterSid the primary key of the rebate plan master
	* @return the rebate plan master, or <code>null</code> if a rebate plan master with the primary key could not be found
	*/
	public static RebatePlanMaster fetchByPrimaryKey(int rebatePlanMasterSid) {
		return getPersistence().fetchByPrimaryKey(rebatePlanMasterSid);
	}

	public static java.util.Map<java.io.Serializable, RebatePlanMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the rebate plan masters.
	*
	* @return the rebate plan masters
	*/
	public static List<RebatePlanMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the rebate plan masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @return the range of rebate plan masters
	*/
	public static List<RebatePlanMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the rebate plan masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rebate plan masters
	*/
	public static List<RebatePlanMaster> findAll(int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the rebate plan masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rebate plan masters
	* @param end the upper bound of the range of rebate plan masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rebate plan masters
	*/
	public static List<RebatePlanMaster> findAll(int start, int end,
		OrderByComparator<RebatePlanMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the rebate plan masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of rebate plan masters.
	*
	* @return the number of rebate plan masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RebatePlanMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RebatePlanMasterPersistence, RebatePlanMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(RebatePlanMasterPersistence.class);
}