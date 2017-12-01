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

import com.stpl.app.model.CpiIndexMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the cpi index master service. This utility wraps {@link com.stpl.app.service.persistence.impl.CpiIndexMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CpiIndexMasterPersistence
 * @see com.stpl.app.service.persistence.impl.CpiIndexMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class CpiIndexMasterUtil {
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
	public static void clearCache(CpiIndexMaster cpiIndexMaster) {
		getPersistence().clearCache(cpiIndexMaster);
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
	public static List<CpiIndexMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CpiIndexMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CpiIndexMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CpiIndexMaster update(CpiIndexMaster cpiIndexMaster) {
		return getPersistence().update(cpiIndexMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CpiIndexMaster update(CpiIndexMaster cpiIndexMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(cpiIndexMaster, serviceContext);
	}

	/**
	* Returns all the cpi index masters where status = &#63;.
	*
	* @param status the status
	* @return the matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByStatus(java.lang.String status) {
		return getPersistence().findByStatus(status);
	}

	/**
	* Returns a range of all the cpi index masters where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByStatus(java.lang.String status,
		int start, int end) {
		return getPersistence().findByStatus(status, start, end);
	}

	/**
	* Returns an ordered range of all the cpi index masters where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByStatus(java.lang.String status,
		int start, int end, OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cpi index masters where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByStatus(java.lang.String status,
		int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cpi index master in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster findByStatus_First(java.lang.String status,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the first cpi index master in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster fetchByStatus_First(java.lang.String status,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the last cpi index master in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster findByStatus_Last(java.lang.String status,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the last cpi index master in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster fetchByStatus_Last(java.lang.String status,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the cpi index masters before and after the current cpi index master in the ordered set where status = &#63;.
	*
	* @param cpiIndexMasterSid the primary key of the current cpi index master
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public static CpiIndexMaster[] findByStatus_PrevAndNext(
		int cpiIndexMasterSid, java.lang.String status,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByStatus_PrevAndNext(cpiIndexMasterSid, status,
			orderByComparator);
	}

	/**
	* Removes all the cpi index masters where status = &#63; from the database.
	*
	* @param status the status
	*/
	public static void removeByStatus(java.lang.String status) {
		getPersistence().removeByStatus(status);
	}

	/**
	* Returns the number of cpi index masters where status = &#63;.
	*
	* @param status the status
	* @return the number of matching cpi index masters
	*/
	public static int countByStatus(java.lang.String status) {
		return getPersistence().countByStatus(status);
	}

	/**
	* Returns all the cpi index masters where indexId = &#63;.
	*
	* @param indexId the index ID
	* @return the matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByIndexId(java.lang.String indexId) {
		return getPersistence().findByIndexId(indexId);
	}

	/**
	* Returns a range of all the cpi index masters where indexId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexId the index ID
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByIndexId(java.lang.String indexId,
		int start, int end) {
		return getPersistence().findByIndexId(indexId, start, end);
	}

	/**
	* Returns an ordered range of all the cpi index masters where indexId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexId the index ID
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByIndexId(java.lang.String indexId,
		int start, int end, OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .findByIndexId(indexId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cpi index masters where indexId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexId the index ID
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByIndexId(java.lang.String indexId,
		int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByIndexId(indexId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cpi index master in the ordered set where indexId = &#63;.
	*
	* @param indexId the index ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster findByIndexId_First(java.lang.String indexId,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence().findByIndexId_First(indexId, orderByComparator);
	}

	/**
	* Returns the first cpi index master in the ordered set where indexId = &#63;.
	*
	* @param indexId the index ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster fetchByIndexId_First(
		java.lang.String indexId,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence().fetchByIndexId_First(indexId, orderByComparator);
	}

	/**
	* Returns the last cpi index master in the ordered set where indexId = &#63;.
	*
	* @param indexId the index ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster findByIndexId_Last(java.lang.String indexId,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence().findByIndexId_Last(indexId, orderByComparator);
	}

	/**
	* Returns the last cpi index master in the ordered set where indexId = &#63;.
	*
	* @param indexId the index ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster fetchByIndexId_Last(java.lang.String indexId,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence().fetchByIndexId_Last(indexId, orderByComparator);
	}

	/**
	* Returns the cpi index masters before and after the current cpi index master in the ordered set where indexId = &#63;.
	*
	* @param cpiIndexMasterSid the primary key of the current cpi index master
	* @param indexId the index ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public static CpiIndexMaster[] findByIndexId_PrevAndNext(
		int cpiIndexMasterSid, java.lang.String indexId,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByIndexId_PrevAndNext(cpiIndexMasterSid, indexId,
			orderByComparator);
	}

	/**
	* Removes all the cpi index masters where indexId = &#63; from the database.
	*
	* @param indexId the index ID
	*/
	public static void removeByIndexId(java.lang.String indexId) {
		getPersistence().removeByIndexId(indexId);
	}

	/**
	* Returns the number of cpi index masters where indexId = &#63;.
	*
	* @param indexId the index ID
	* @return the number of matching cpi index masters
	*/
	public static int countByIndexId(java.lang.String indexId) {
		return getPersistence().countByIndexId(indexId);
	}

	/**
	* Returns all the cpi index masters where indexValue = &#63;.
	*
	* @param indexValue the index value
	* @return the matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByIndexValue(
		java.lang.String indexValue) {
		return getPersistence().findByIndexValue(indexValue);
	}

	/**
	* Returns a range of all the cpi index masters where indexValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexValue the index value
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByIndexValue(
		java.lang.String indexValue, int start, int end) {
		return getPersistence().findByIndexValue(indexValue, start, end);
	}

	/**
	* Returns an ordered range of all the cpi index masters where indexValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexValue the index value
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByIndexValue(
		java.lang.String indexValue, int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .findByIndexValue(indexValue, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cpi index masters where indexValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexValue the index value
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByIndexValue(
		java.lang.String indexValue, int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByIndexValue(indexValue, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cpi index master in the ordered set where indexValue = &#63;.
	*
	* @param indexValue the index value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster findByIndexValue_First(
		java.lang.String indexValue,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByIndexValue_First(indexValue, orderByComparator);
	}

	/**
	* Returns the first cpi index master in the ordered set where indexValue = &#63;.
	*
	* @param indexValue the index value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster fetchByIndexValue_First(
		java.lang.String indexValue,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .fetchByIndexValue_First(indexValue, orderByComparator);
	}

	/**
	* Returns the last cpi index master in the ordered set where indexValue = &#63;.
	*
	* @param indexValue the index value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster findByIndexValue_Last(
		java.lang.String indexValue,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByIndexValue_Last(indexValue, orderByComparator);
	}

	/**
	* Returns the last cpi index master in the ordered set where indexValue = &#63;.
	*
	* @param indexValue the index value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster fetchByIndexValue_Last(
		java.lang.String indexValue,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .fetchByIndexValue_Last(indexValue, orderByComparator);
	}

	/**
	* Returns the cpi index masters before and after the current cpi index master in the ordered set where indexValue = &#63;.
	*
	* @param cpiIndexMasterSid the primary key of the current cpi index master
	* @param indexValue the index value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public static CpiIndexMaster[] findByIndexValue_PrevAndNext(
		int cpiIndexMasterSid, java.lang.String indexValue,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByIndexValue_PrevAndNext(cpiIndexMasterSid, indexValue,
			orderByComparator);
	}

	/**
	* Removes all the cpi index masters where indexValue = &#63; from the database.
	*
	* @param indexValue the index value
	*/
	public static void removeByIndexValue(java.lang.String indexValue) {
		getPersistence().removeByIndexValue(indexValue);
	}

	/**
	* Returns the number of cpi index masters where indexValue = &#63;.
	*
	* @param indexValue the index value
	* @return the number of matching cpi index masters
	*/
	public static int countByIndexValue(java.lang.String indexValue) {
		return getPersistence().countByIndexValue(indexValue);
	}

	/**
	* Returns all the cpi index masters where indexType = &#63;.
	*
	* @param indexType the index type
	* @return the matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByIndexType(
		java.lang.String indexType) {
		return getPersistence().findByIndexType(indexType);
	}

	/**
	* Returns a range of all the cpi index masters where indexType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexType the index type
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByIndexType(
		java.lang.String indexType, int start, int end) {
		return getPersistence().findByIndexType(indexType, start, end);
	}

	/**
	* Returns an ordered range of all the cpi index masters where indexType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexType the index type
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByIndexType(
		java.lang.String indexType, int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .findByIndexType(indexType, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cpi index masters where indexType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexType the index type
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByIndexType(
		java.lang.String indexType, int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByIndexType(indexType, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first cpi index master in the ordered set where indexType = &#63;.
	*
	* @param indexType the index type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster findByIndexType_First(
		java.lang.String indexType,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByIndexType_First(indexType, orderByComparator);
	}

	/**
	* Returns the first cpi index master in the ordered set where indexType = &#63;.
	*
	* @param indexType the index type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster fetchByIndexType_First(
		java.lang.String indexType,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .fetchByIndexType_First(indexType, orderByComparator);
	}

	/**
	* Returns the last cpi index master in the ordered set where indexType = &#63;.
	*
	* @param indexType the index type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster findByIndexType_Last(
		java.lang.String indexType,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByIndexType_Last(indexType, orderByComparator);
	}

	/**
	* Returns the last cpi index master in the ordered set where indexType = &#63;.
	*
	* @param indexType the index type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster fetchByIndexType_Last(
		java.lang.String indexType,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .fetchByIndexType_Last(indexType, orderByComparator);
	}

	/**
	* Returns the cpi index masters before and after the current cpi index master in the ordered set where indexType = &#63;.
	*
	* @param cpiIndexMasterSid the primary key of the current cpi index master
	* @param indexType the index type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public static CpiIndexMaster[] findByIndexType_PrevAndNext(
		int cpiIndexMasterSid, java.lang.String indexType,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByIndexType_PrevAndNext(cpiIndexMasterSid, indexType,
			orderByComparator);
	}

	/**
	* Removes all the cpi index masters where indexType = &#63; from the database.
	*
	* @param indexType the index type
	*/
	public static void removeByIndexType(java.lang.String indexType) {
		getPersistence().removeByIndexType(indexType);
	}

	/**
	* Returns the number of cpi index masters where indexType = &#63;.
	*
	* @param indexType the index type
	* @return the number of matching cpi index masters
	*/
	public static int countByIndexType(java.lang.String indexType) {
		return getPersistence().countByIndexType(indexType);
	}

	/**
	* Returns all the cpi index masters where effectiveDate = &#63;.
	*
	* @param effectiveDate the effective date
	* @return the matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByEffectiveDate(Date effectiveDate) {
		return getPersistence().findByEffectiveDate(effectiveDate);
	}

	/**
	* Returns a range of all the cpi index masters where effectiveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param effectiveDate the effective date
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByEffectiveDate(Date effectiveDate,
		int start, int end) {
		return getPersistence().findByEffectiveDate(effectiveDate, start, end);
	}

	/**
	* Returns an ordered range of all the cpi index masters where effectiveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param effectiveDate the effective date
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByEffectiveDate(Date effectiveDate,
		int start, int end, OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .findByEffectiveDate(effectiveDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the cpi index masters where effectiveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param effectiveDate the effective date
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByEffectiveDate(Date effectiveDate,
		int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByEffectiveDate(effectiveDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first cpi index master in the ordered set where effectiveDate = &#63;.
	*
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster findByEffectiveDate_First(Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByEffectiveDate_First(effectiveDate, orderByComparator);
	}

	/**
	* Returns the first cpi index master in the ordered set where effectiveDate = &#63;.
	*
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster fetchByEffectiveDate_First(
		Date effectiveDate, OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .fetchByEffectiveDate_First(effectiveDate, orderByComparator);
	}

	/**
	* Returns the last cpi index master in the ordered set where effectiveDate = &#63;.
	*
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster findByEffectiveDate_Last(Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByEffectiveDate_Last(effectiveDate, orderByComparator);
	}

	/**
	* Returns the last cpi index master in the ordered set where effectiveDate = &#63;.
	*
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster fetchByEffectiveDate_Last(Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .fetchByEffectiveDate_Last(effectiveDate, orderByComparator);
	}

	/**
	* Returns the cpi index masters before and after the current cpi index master in the ordered set where effectiveDate = &#63;.
	*
	* @param cpiIndexMasterSid the primary key of the current cpi index master
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public static CpiIndexMaster[] findByEffectiveDate_PrevAndNext(
		int cpiIndexMasterSid, Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByEffectiveDate_PrevAndNext(cpiIndexMasterSid,
			effectiveDate, orderByComparator);
	}

	/**
	* Removes all the cpi index masters where effectiveDate = &#63; from the database.
	*
	* @param effectiveDate the effective date
	*/
	public static void removeByEffectiveDate(Date effectiveDate) {
		getPersistence().removeByEffectiveDate(effectiveDate);
	}

	/**
	* Returns the number of cpi index masters where effectiveDate = &#63;.
	*
	* @param effectiveDate the effective date
	* @return the number of matching cpi index masters
	*/
	public static int countByEffectiveDate(Date effectiveDate) {
		return getPersistence().countByEffectiveDate(effectiveDate);
	}

	/**
	* Returns all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @return the matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByCpiIndexUnique(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate) {
		return getPersistence()
				   .findByCpiIndexUnique(indexId, status, indexType,
			effectiveDate);
	}

	/**
	* Returns a range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByCpiIndexUnique(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate, int start, int end) {
		return getPersistence()
				   .findByCpiIndexUnique(indexId, status, indexType,
			effectiveDate, start, end);
	}

	/**
	* Returns an ordered range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByCpiIndexUnique(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate, int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .findByCpiIndexUnique(indexId, status, indexType,
			effectiveDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cpi index masters
	*/
	public static List<CpiIndexMaster> findByCpiIndexUnique(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate, int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCpiIndexUnique(indexId, status, indexType,
			effectiveDate, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster findByCpiIndexUnique_First(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByCpiIndexUnique_First(indexId, status, indexType,
			effectiveDate, orderByComparator);
	}

	/**
	* Returns the first cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster fetchByCpiIndexUnique_First(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCpiIndexUnique_First(indexId, status, indexType,
			effectiveDate, orderByComparator);
	}

	/**
	* Returns the last cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master
	* @throws NoSuchCpiIndexMasterException if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster findByCpiIndexUnique_Last(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByCpiIndexUnique_Last(indexId, status, indexType,
			effectiveDate, orderByComparator);
	}

	/**
	* Returns the last cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cpi index master, or <code>null</code> if a matching cpi index master could not be found
	*/
	public static CpiIndexMaster fetchByCpiIndexUnique_Last(
		java.lang.String indexId, java.lang.String status,
		java.lang.String indexType, Date effectiveDate,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCpiIndexUnique_Last(indexId, status, indexType,
			effectiveDate, orderByComparator);
	}

	/**
	* Returns the cpi index masters before and after the current cpi index master in the ordered set where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param cpiIndexMasterSid the primary key of the current cpi index master
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public static CpiIndexMaster[] findByCpiIndexUnique_PrevAndNext(
		int cpiIndexMasterSid, java.lang.String indexId,
		java.lang.String status, java.lang.String indexType,
		Date effectiveDate, OrderByComparator<CpiIndexMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence()
				   .findByCpiIndexUnique_PrevAndNext(cpiIndexMasterSid,
			indexId, status, indexType, effectiveDate, orderByComparator);
	}

	/**
	* Removes all the cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63; from the database.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	*/
	public static void removeByCpiIndexUnique(java.lang.String indexId,
		java.lang.String status, java.lang.String indexType, Date effectiveDate) {
		getPersistence()
			.removeByCpiIndexUnique(indexId, status, indexType, effectiveDate);
	}

	/**
	* Returns the number of cpi index masters where indexId = &#63; and status = &#63; and indexType = &#63; and effectiveDate = &#63;.
	*
	* @param indexId the index ID
	* @param status the status
	* @param indexType the index type
	* @param effectiveDate the effective date
	* @return the number of matching cpi index masters
	*/
	public static int countByCpiIndexUnique(java.lang.String indexId,
		java.lang.String status, java.lang.String indexType, Date effectiveDate) {
		return getPersistence()
				   .countByCpiIndexUnique(indexId, status, indexType,
			effectiveDate);
	}

	/**
	* Caches the cpi index master in the entity cache if it is enabled.
	*
	* @param cpiIndexMaster the cpi index master
	*/
	public static void cacheResult(CpiIndexMaster cpiIndexMaster) {
		getPersistence().cacheResult(cpiIndexMaster);
	}

	/**
	* Caches the cpi index masters in the entity cache if it is enabled.
	*
	* @param cpiIndexMasters the cpi index masters
	*/
	public static void cacheResult(List<CpiIndexMaster> cpiIndexMasters) {
		getPersistence().cacheResult(cpiIndexMasters);
	}

	/**
	* Creates a new cpi index master with the primary key. Does not add the cpi index master to the database.
	*
	* @param cpiIndexMasterSid the primary key for the new cpi index master
	* @return the new cpi index master
	*/
	public static CpiIndexMaster create(int cpiIndexMasterSid) {
		return getPersistence().create(cpiIndexMasterSid);
	}

	/**
	* Removes the cpi index master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cpiIndexMasterSid the primary key of the cpi index master
	* @return the cpi index master that was removed
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public static CpiIndexMaster remove(int cpiIndexMasterSid)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence().remove(cpiIndexMasterSid);
	}

	public static CpiIndexMaster updateImpl(CpiIndexMaster cpiIndexMaster) {
		return getPersistence().updateImpl(cpiIndexMaster);
	}

	/**
	* Returns the cpi index master with the primary key or throws a {@link NoSuchCpiIndexMasterException} if it could not be found.
	*
	* @param cpiIndexMasterSid the primary key of the cpi index master
	* @return the cpi index master
	* @throws NoSuchCpiIndexMasterException if a cpi index master with the primary key could not be found
	*/
	public static CpiIndexMaster findByPrimaryKey(int cpiIndexMasterSid)
		throws com.stpl.app.exception.NoSuchCpiIndexMasterException {
		return getPersistence().findByPrimaryKey(cpiIndexMasterSid);
	}

	/**
	* Returns the cpi index master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cpiIndexMasterSid the primary key of the cpi index master
	* @return the cpi index master, or <code>null</code> if a cpi index master with the primary key could not be found
	*/
	public static CpiIndexMaster fetchByPrimaryKey(int cpiIndexMasterSid) {
		return getPersistence().fetchByPrimaryKey(cpiIndexMasterSid);
	}

	public static java.util.Map<java.io.Serializable, CpiIndexMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cpi index masters.
	*
	* @return the cpi index masters
	*/
	public static List<CpiIndexMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cpi index masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @return the range of cpi index masters
	*/
	public static List<CpiIndexMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cpi index masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cpi index masters
	*/
	public static List<CpiIndexMaster> findAll(int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cpi index masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CpiIndexMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cpi index masters
	* @param end the upper bound of the range of cpi index masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cpi index masters
	*/
	public static List<CpiIndexMaster> findAll(int start, int end,
		OrderByComparator<CpiIndexMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cpi index masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cpi index masters.
	*
	* @return the number of cpi index masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CpiIndexMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CpiIndexMasterPersistence, CpiIndexMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CpiIndexMasterPersistence.class);
}