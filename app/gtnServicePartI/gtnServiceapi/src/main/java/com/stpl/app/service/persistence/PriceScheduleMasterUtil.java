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

import com.stpl.app.model.PriceScheduleMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the price schedule master service. This utility wraps {@link com.stpl.app.service.persistence.impl.PriceScheduleMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PriceScheduleMasterPersistence
 * @see com.stpl.app.service.persistence.impl.PriceScheduleMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class PriceScheduleMasterUtil {
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
	public static void clearCache(PriceScheduleMaster priceScheduleMaster) {
		getPersistence().clearCache(priceScheduleMaster);
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
	public static List<PriceScheduleMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PriceScheduleMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PriceScheduleMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PriceScheduleMaster update(
		PriceScheduleMaster priceScheduleMaster) {
		return getPersistence().update(priceScheduleMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PriceScheduleMaster update(
		PriceScheduleMaster priceScheduleMaster, ServiceContext serviceContext) {
		return getPersistence().update(priceScheduleMaster, serviceContext);
	}

	/**
	* Returns all the price schedule masters where priceScheduleId = &#63;.
	*
	* @param priceScheduleId the price schedule ID
	* @return the matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleId(
		java.lang.String priceScheduleId) {
		return getPersistence().findByPriceScheduleId(priceScheduleId);
	}

	/**
	* Returns a range of all the price schedule masters where priceScheduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleId the price schedule ID
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleId(
		java.lang.String priceScheduleId, int start, int end) {
		return getPersistence()
				   .findByPriceScheduleId(priceScheduleId, start, end);
	}

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleId the price schedule ID
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleId(
		java.lang.String priceScheduleId, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .findByPriceScheduleId(priceScheduleId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleId the price schedule ID
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleId(
		java.lang.String priceScheduleId, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPriceScheduleId(priceScheduleId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleId = &#63;.
	*
	* @param priceScheduleId the price schedule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster findByPriceScheduleId_First(
		java.lang.String priceScheduleId,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleId_First(priceScheduleId,
			orderByComparator);
	}

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleId = &#63;.
	*
	* @param priceScheduleId the price schedule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster fetchByPriceScheduleId_First(
		java.lang.String priceScheduleId,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleId_First(priceScheduleId,
			orderByComparator);
	}

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleId = &#63;.
	*
	* @param priceScheduleId the price schedule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster findByPriceScheduleId_Last(
		java.lang.String priceScheduleId,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleId_Last(priceScheduleId,
			orderByComparator);
	}

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleId = &#63;.
	*
	* @param priceScheduleId the price schedule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster fetchByPriceScheduleId_Last(
		java.lang.String priceScheduleId,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleId_Last(priceScheduleId,
			orderByComparator);
	}

	/**
	* Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleId = &#63;.
	*
	* @param priceScheduleSystemId the primary key of the current price schedule master
	* @param priceScheduleId the price schedule ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price schedule master
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public static PriceScheduleMaster[] findByPriceScheduleId_PrevAndNext(
		int priceScheduleSystemId, java.lang.String priceScheduleId,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleId_PrevAndNext(priceScheduleSystemId,
			priceScheduleId, orderByComparator);
	}

	/**
	* Removes all the price schedule masters where priceScheduleId = &#63; from the database.
	*
	* @param priceScheduleId the price schedule ID
	*/
	public static void removeByPriceScheduleId(java.lang.String priceScheduleId) {
		getPersistence().removeByPriceScheduleId(priceScheduleId);
	}

	/**
	* Returns the number of price schedule masters where priceScheduleId = &#63;.
	*
	* @param priceScheduleId the price schedule ID
	* @return the number of matching price schedule masters
	*/
	public static int countByPriceScheduleId(java.lang.String priceScheduleId) {
		return getPersistence().countByPriceScheduleId(priceScheduleId);
	}

	/**
	* Returns all the price schedule masters where priceScheduleNo = &#63;.
	*
	* @param priceScheduleNo the price schedule no
	* @return the matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleNo(
		java.lang.String priceScheduleNo) {
		return getPersistence().findByPriceScheduleNo(priceScheduleNo);
	}

	/**
	* Returns a range of all the price schedule masters where priceScheduleNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleNo the price schedule no
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleNo(
		java.lang.String priceScheduleNo, int start, int end) {
		return getPersistence()
				   .findByPriceScheduleNo(priceScheduleNo, start, end);
	}

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleNo the price schedule no
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleNo(
		java.lang.String priceScheduleNo, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .findByPriceScheduleNo(priceScheduleNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleNo the price schedule no
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleNo(
		java.lang.String priceScheduleNo, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPriceScheduleNo(priceScheduleNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleNo = &#63;.
	*
	* @param priceScheduleNo the price schedule no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster findByPriceScheduleNo_First(
		java.lang.String priceScheduleNo,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleNo_First(priceScheduleNo,
			orderByComparator);
	}

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleNo = &#63;.
	*
	* @param priceScheduleNo the price schedule no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster fetchByPriceScheduleNo_First(
		java.lang.String priceScheduleNo,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleNo_First(priceScheduleNo,
			orderByComparator);
	}

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleNo = &#63;.
	*
	* @param priceScheduleNo the price schedule no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster findByPriceScheduleNo_Last(
		java.lang.String priceScheduleNo,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleNo_Last(priceScheduleNo,
			orderByComparator);
	}

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleNo = &#63;.
	*
	* @param priceScheduleNo the price schedule no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster fetchByPriceScheduleNo_Last(
		java.lang.String priceScheduleNo,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleNo_Last(priceScheduleNo,
			orderByComparator);
	}

	/**
	* Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleNo = &#63;.
	*
	* @param priceScheduleSystemId the primary key of the current price schedule master
	* @param priceScheduleNo the price schedule no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price schedule master
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public static PriceScheduleMaster[] findByPriceScheduleNo_PrevAndNext(
		int priceScheduleSystemId, java.lang.String priceScheduleNo,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleNo_PrevAndNext(priceScheduleSystemId,
			priceScheduleNo, orderByComparator);
	}

	/**
	* Removes all the price schedule masters where priceScheduleNo = &#63; from the database.
	*
	* @param priceScheduleNo the price schedule no
	*/
	public static void removeByPriceScheduleNo(java.lang.String priceScheduleNo) {
		getPersistence().removeByPriceScheduleNo(priceScheduleNo);
	}

	/**
	* Returns the number of price schedule masters where priceScheduleNo = &#63;.
	*
	* @param priceScheduleNo the price schedule no
	* @return the number of matching price schedule masters
	*/
	public static int countByPriceScheduleNo(java.lang.String priceScheduleNo) {
		return getPersistence().countByPriceScheduleNo(priceScheduleNo);
	}

	/**
	* Returns all the price schedule masters where priceScheduleName = &#63;.
	*
	* @param priceScheduleName the price schedule name
	* @return the matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleName(
		java.lang.String priceScheduleName) {
		return getPersistence().findByPriceScheduleName(priceScheduleName);
	}

	/**
	* Returns a range of all the price schedule masters where priceScheduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleName the price schedule name
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleName(
		java.lang.String priceScheduleName, int start, int end) {
		return getPersistence()
				   .findByPriceScheduleName(priceScheduleName, start, end);
	}

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleName the price schedule name
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleName(
		java.lang.String priceScheduleName, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .findByPriceScheduleName(priceScheduleName, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleName the price schedule name
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleName(
		java.lang.String priceScheduleName, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPriceScheduleName(priceScheduleName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleName = &#63;.
	*
	* @param priceScheduleName the price schedule name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster findByPriceScheduleName_First(
		java.lang.String priceScheduleName,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleName_First(priceScheduleName,
			orderByComparator);
	}

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleName = &#63;.
	*
	* @param priceScheduleName the price schedule name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster fetchByPriceScheduleName_First(
		java.lang.String priceScheduleName,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleName_First(priceScheduleName,
			orderByComparator);
	}

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleName = &#63;.
	*
	* @param priceScheduleName the price schedule name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster findByPriceScheduleName_Last(
		java.lang.String priceScheduleName,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleName_Last(priceScheduleName,
			orderByComparator);
	}

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleName = &#63;.
	*
	* @param priceScheduleName the price schedule name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster fetchByPriceScheduleName_Last(
		java.lang.String priceScheduleName,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleName_Last(priceScheduleName,
			orderByComparator);
	}

	/**
	* Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleName = &#63;.
	*
	* @param priceScheduleSystemId the primary key of the current price schedule master
	* @param priceScheduleName the price schedule name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price schedule master
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public static PriceScheduleMaster[] findByPriceScheduleName_PrevAndNext(
		int priceScheduleSystemId, java.lang.String priceScheduleName,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleName_PrevAndNext(priceScheduleSystemId,
			priceScheduleName, orderByComparator);
	}

	/**
	* Removes all the price schedule masters where priceScheduleName = &#63; from the database.
	*
	* @param priceScheduleName the price schedule name
	*/
	public static void removeByPriceScheduleName(
		java.lang.String priceScheduleName) {
		getPersistence().removeByPriceScheduleName(priceScheduleName);
	}

	/**
	* Returns the number of price schedule masters where priceScheduleName = &#63;.
	*
	* @param priceScheduleName the price schedule name
	* @return the number of matching price schedule masters
	*/
	public static int countByPriceScheduleName(
		java.lang.String priceScheduleName) {
		return getPersistence().countByPriceScheduleName(priceScheduleName);
	}

	/**
	* Returns all the price schedule masters where priceScheduleType = &#63;.
	*
	* @param priceScheduleType the price schedule type
	* @return the matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleType(
		java.lang.String priceScheduleType) {
		return getPersistence().findByPriceScheduleType(priceScheduleType);
	}

	/**
	* Returns a range of all the price schedule masters where priceScheduleType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleType the price schedule type
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleType(
		java.lang.String priceScheduleType, int start, int end) {
		return getPersistence()
				   .findByPriceScheduleType(priceScheduleType, start, end);
	}

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleType the price schedule type
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleType(
		java.lang.String priceScheduleType, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .findByPriceScheduleType(priceScheduleType, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleType the price schedule type
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleType(
		java.lang.String priceScheduleType, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPriceScheduleType(priceScheduleType, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleType = &#63;.
	*
	* @param priceScheduleType the price schedule type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster findByPriceScheduleType_First(
		java.lang.String priceScheduleType,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleType_First(priceScheduleType,
			orderByComparator);
	}

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleType = &#63;.
	*
	* @param priceScheduleType the price schedule type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster fetchByPriceScheduleType_First(
		java.lang.String priceScheduleType,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleType_First(priceScheduleType,
			orderByComparator);
	}

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleType = &#63;.
	*
	* @param priceScheduleType the price schedule type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster findByPriceScheduleType_Last(
		java.lang.String priceScheduleType,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleType_Last(priceScheduleType,
			orderByComparator);
	}

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleType = &#63;.
	*
	* @param priceScheduleType the price schedule type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster fetchByPriceScheduleType_Last(
		java.lang.String priceScheduleType,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleType_Last(priceScheduleType,
			orderByComparator);
	}

	/**
	* Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleType = &#63;.
	*
	* @param priceScheduleSystemId the primary key of the current price schedule master
	* @param priceScheduleType the price schedule type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price schedule master
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public static PriceScheduleMaster[] findByPriceScheduleType_PrevAndNext(
		int priceScheduleSystemId, java.lang.String priceScheduleType,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleType_PrevAndNext(priceScheduleSystemId,
			priceScheduleType, orderByComparator);
	}

	/**
	* Removes all the price schedule masters where priceScheduleType = &#63; from the database.
	*
	* @param priceScheduleType the price schedule type
	*/
	public static void removeByPriceScheduleType(
		java.lang.String priceScheduleType) {
		getPersistence().removeByPriceScheduleType(priceScheduleType);
	}

	/**
	* Returns the number of price schedule masters where priceScheduleType = &#63;.
	*
	* @param priceScheduleType the price schedule type
	* @return the number of matching price schedule masters
	*/
	public static int countByPriceScheduleType(
		java.lang.String priceScheduleType) {
		return getPersistence().countByPriceScheduleType(priceScheduleType);
	}

	/**
	* Returns all the price schedule masters where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleStatus the price schedule status
	* @return the matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleStatus(
		java.lang.String priceScheduleStatus) {
		return getPersistence().findByPriceScheduleStatus(priceScheduleStatus);
	}

	/**
	* Returns a range of all the price schedule masters where priceScheduleStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleStatus the price schedule status
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleStatus(
		java.lang.String priceScheduleStatus, int start, int end) {
		return getPersistence()
				   .findByPriceScheduleStatus(priceScheduleStatus, start, end);
	}

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleStatus the price schedule status
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleStatus(
		java.lang.String priceScheduleStatus, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .findByPriceScheduleStatus(priceScheduleStatus, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the price schedule masters where priceScheduleStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleStatus the price schedule status
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching price schedule masters
	*/
	public static List<PriceScheduleMaster> findByPriceScheduleStatus(
		java.lang.String priceScheduleStatus, int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPriceScheduleStatus(priceScheduleStatus, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleStatus the price schedule status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster findByPriceScheduleStatus_First(
		java.lang.String priceScheduleStatus,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleStatus_First(priceScheduleStatus,
			orderByComparator);
	}

	/**
	* Returns the first price schedule master in the ordered set where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleStatus the price schedule status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster fetchByPriceScheduleStatus_First(
		java.lang.String priceScheduleStatus,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleStatus_First(priceScheduleStatus,
			orderByComparator);
	}

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleStatus the price schedule status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master
	* @throws NoSuchPriceScheduleMasterException if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster findByPriceScheduleStatus_Last(
		java.lang.String priceScheduleStatus,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleStatus_Last(priceScheduleStatus,
			orderByComparator);
	}

	/**
	* Returns the last price schedule master in the ordered set where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleStatus the price schedule status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule master, or <code>null</code> if a matching price schedule master could not be found
	*/
	public static PriceScheduleMaster fetchByPriceScheduleStatus_Last(
		java.lang.String priceScheduleStatus,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleStatus_Last(priceScheduleStatus,
			orderByComparator);
	}

	/**
	* Returns the price schedule masters before and after the current price schedule master in the ordered set where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleSystemId the primary key of the current price schedule master
	* @param priceScheduleStatus the price schedule status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price schedule master
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public static PriceScheduleMaster[] findByPriceScheduleStatus_PrevAndNext(
		int priceScheduleSystemId, java.lang.String priceScheduleStatus,
		OrderByComparator<PriceScheduleMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence()
				   .findByPriceScheduleStatus_PrevAndNext(priceScheduleSystemId,
			priceScheduleStatus, orderByComparator);
	}

	/**
	* Removes all the price schedule masters where priceScheduleStatus = &#63; from the database.
	*
	* @param priceScheduleStatus the price schedule status
	*/
	public static void removeByPriceScheduleStatus(
		java.lang.String priceScheduleStatus) {
		getPersistence().removeByPriceScheduleStatus(priceScheduleStatus);
	}

	/**
	* Returns the number of price schedule masters where priceScheduleStatus = &#63;.
	*
	* @param priceScheduleStatus the price schedule status
	* @return the number of matching price schedule masters
	*/
	public static int countByPriceScheduleStatus(
		java.lang.String priceScheduleStatus) {
		return getPersistence().countByPriceScheduleStatus(priceScheduleStatus);
	}

	/**
	* Caches the price schedule master in the entity cache if it is enabled.
	*
	* @param priceScheduleMaster the price schedule master
	*/
	public static void cacheResult(PriceScheduleMaster priceScheduleMaster) {
		getPersistence().cacheResult(priceScheduleMaster);
	}

	/**
	* Caches the price schedule masters in the entity cache if it is enabled.
	*
	* @param priceScheduleMasters the price schedule masters
	*/
	public static void cacheResult(
		List<PriceScheduleMaster> priceScheduleMasters) {
		getPersistence().cacheResult(priceScheduleMasters);
	}

	/**
	* Creates a new price schedule master with the primary key. Does not add the price schedule master to the database.
	*
	* @param priceScheduleSystemId the primary key for the new price schedule master
	* @return the new price schedule master
	*/
	public static PriceScheduleMaster create(int priceScheduleSystemId) {
		return getPersistence().create(priceScheduleSystemId);
	}

	/**
	* Removes the price schedule master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param priceScheduleSystemId the primary key of the price schedule master
	* @return the price schedule master that was removed
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public static PriceScheduleMaster remove(int priceScheduleSystemId)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence().remove(priceScheduleSystemId);
	}

	public static PriceScheduleMaster updateImpl(
		PriceScheduleMaster priceScheduleMaster) {
		return getPersistence().updateImpl(priceScheduleMaster);
	}

	/**
	* Returns the price schedule master with the primary key or throws a {@link NoSuchPriceScheduleMasterException} if it could not be found.
	*
	* @param priceScheduleSystemId the primary key of the price schedule master
	* @return the price schedule master
	* @throws NoSuchPriceScheduleMasterException if a price schedule master with the primary key could not be found
	*/
	public static PriceScheduleMaster findByPrimaryKey(
		int priceScheduleSystemId)
		throws com.stpl.app.exception.NoSuchPriceScheduleMasterException {
		return getPersistence().findByPrimaryKey(priceScheduleSystemId);
	}

	/**
	* Returns the price schedule master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param priceScheduleSystemId the primary key of the price schedule master
	* @return the price schedule master, or <code>null</code> if a price schedule master with the primary key could not be found
	*/
	public static PriceScheduleMaster fetchByPrimaryKey(
		int priceScheduleSystemId) {
		return getPersistence().fetchByPrimaryKey(priceScheduleSystemId);
	}

	public static java.util.Map<java.io.Serializable, PriceScheduleMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the price schedule masters.
	*
	* @return the price schedule masters
	*/
	public static List<PriceScheduleMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the price schedule masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @return the range of price schedule masters
	*/
	public static List<PriceScheduleMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the price schedule masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of price schedule masters
	*/
	public static List<PriceScheduleMaster> findAll(int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the price schedule masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule masters
	* @param end the upper bound of the range of price schedule masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of price schedule masters
	*/
	public static List<PriceScheduleMaster> findAll(int start, int end,
		OrderByComparator<PriceScheduleMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the price schedule masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of price schedule masters.
	*
	* @return the number of price schedule masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PriceScheduleMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PriceScheduleMasterPersistence, PriceScheduleMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(PriceScheduleMasterPersistence.class);
}