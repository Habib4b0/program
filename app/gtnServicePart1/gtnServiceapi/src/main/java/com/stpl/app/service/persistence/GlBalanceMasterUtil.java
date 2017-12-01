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

import com.stpl.app.model.GlBalanceMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the gl balance master service. This utility wraps {@link com.stpl.app.service.persistence.impl.GlBalanceMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GlBalanceMasterPersistence
 * @see com.stpl.app.service.persistence.impl.GlBalanceMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class GlBalanceMasterUtil {
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
	public static void clearCache(GlBalanceMaster glBalanceMaster) {
		getPersistence().clearCache(glBalanceMaster);
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
	public static List<GlBalanceMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GlBalanceMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GlBalanceMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GlBalanceMaster update(GlBalanceMaster glBalanceMaster) {
		return getPersistence().update(glBalanceMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GlBalanceMaster update(GlBalanceMaster glBalanceMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(glBalanceMaster, serviceContext);
	}

	/**
	* Returns all the gl balance masters where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @return the matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByAccountNo(
		java.lang.String accountNo) {
		return getPersistence().findByAccountNo(accountNo);
	}

	/**
	* Returns a range of all the gl balance masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end) {
		return getPersistence().findByAccountNo(accountNo, start, end);
	}

	/**
	* Returns an ordered range of all the gl balance masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .findByAccountNo(accountNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl balance masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountNo(accountNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first gl balance master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByAccountNo_First(
		java.lang.String accountNo,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByAccountNo_First(accountNo, orderByComparator);
	}

	/**
	* Returns the first gl balance master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByAccountNo_First(
		java.lang.String accountNo,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .fetchByAccountNo_First(accountNo, orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByAccountNo_Last(
		java.lang.String accountNo,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByAccountNo_Last(accountNo, orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByAccountNo_Last(
		java.lang.String accountNo,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .fetchByAccountNo_Last(accountNo, orderByComparator);
	}

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where accountNo = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public static GlBalanceMaster[] findByAccountNo_PrevAndNext(
		int glBalanceMasterSid, java.lang.String accountNo,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByAccountNo_PrevAndNext(glBalanceMasterSid, accountNo,
			orderByComparator);
	}

	/**
	* Removes all the gl balance masters where accountNo = &#63; from the database.
	*
	* @param accountNo the account no
	*/
	public static void removeByAccountNo(java.lang.String accountNo) {
		getPersistence().removeByAccountNo(accountNo);
	}

	/**
	* Returns the number of gl balance masters where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @return the number of matching gl balance masters
	*/
	public static int countByAccountNo(java.lang.String accountNo) {
		return getPersistence().countByAccountNo(accountNo);
	}

	/**
	* Returns all the gl balance masters where isActive = &#63;.
	*
	* @param isActive the is active
	* @return the matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByIsActive(
		java.lang.String isActive) {
		return getPersistence().findByIsActive(isActive);
	}

	/**
	* Returns a range of all the gl balance masters where isActive = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isActive the is active
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByIsActive(
		java.lang.String isActive, int start, int end) {
		return getPersistence().findByIsActive(isActive, start, end);
	}

	/**
	* Returns an ordered range of all the gl balance masters where isActive = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isActive the is active
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByIsActive(
		java.lang.String isActive, int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .findByIsActive(isActive, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl balance masters where isActive = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isActive the is active
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByIsActive(
		java.lang.String isActive, int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByIsActive(isActive, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first gl balance master in the ordered set where isActive = &#63;.
	*
	* @param isActive the is active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByIsActive_First(
		java.lang.String isActive,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence().findByIsActive_First(isActive, orderByComparator);
	}

	/**
	* Returns the first gl balance master in the ordered set where isActive = &#63;.
	*
	* @param isActive the is active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByIsActive_First(
		java.lang.String isActive,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .fetchByIsActive_First(isActive, orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where isActive = &#63;.
	*
	* @param isActive the is active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByIsActive_Last(
		java.lang.String isActive,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence().findByIsActive_Last(isActive, orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where isActive = &#63;.
	*
	* @param isActive the is active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByIsActive_Last(
		java.lang.String isActive,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence().fetchByIsActive_Last(isActive, orderByComparator);
	}

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where isActive = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param isActive the is active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public static GlBalanceMaster[] findByIsActive_PrevAndNext(
		int glBalanceMasterSid, java.lang.String isActive,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByIsActive_PrevAndNext(glBalanceMasterSid, isActive,
			orderByComparator);
	}

	/**
	* Removes all the gl balance masters where isActive = &#63; from the database.
	*
	* @param isActive the is active
	*/
	public static void removeByIsActive(java.lang.String isActive) {
		getPersistence().removeByIsActive(isActive);
	}

	/**
	* Returns the number of gl balance masters where isActive = &#63;.
	*
	* @param isActive the is active
	* @return the number of matching gl balance masters
	*/
	public static int countByIsActive(java.lang.String isActive) {
		return getPersistence().countByIsActive(isActive);
	}

	/**
	* Returns all the gl balance masters where uploadDate = &#63;.
	*
	* @param uploadDate the upload date
	* @return the matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByUploadDate(Date uploadDate) {
		return getPersistence().findByUploadDate(uploadDate);
	}

	/**
	* Returns a range of all the gl balance masters where uploadDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uploadDate the upload date
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByUploadDate(Date uploadDate,
		int start, int end) {
		return getPersistence().findByUploadDate(uploadDate, start, end);
	}

	/**
	* Returns an ordered range of all the gl balance masters where uploadDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uploadDate the upload date
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByUploadDate(Date uploadDate,
		int start, int end, OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .findByUploadDate(uploadDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl balance masters where uploadDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uploadDate the upload date
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByUploadDate(Date uploadDate,
		int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUploadDate(uploadDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first gl balance master in the ordered set where uploadDate = &#63;.
	*
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByUploadDate_First(Date uploadDate,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByUploadDate_First(uploadDate, orderByComparator);
	}

	/**
	* Returns the first gl balance master in the ordered set where uploadDate = &#63;.
	*
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByUploadDate_First(Date uploadDate,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .fetchByUploadDate_First(uploadDate, orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where uploadDate = &#63;.
	*
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByUploadDate_Last(Date uploadDate,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByUploadDate_Last(uploadDate, orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where uploadDate = &#63;.
	*
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByUploadDate_Last(Date uploadDate,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .fetchByUploadDate_Last(uploadDate, orderByComparator);
	}

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where uploadDate = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public static GlBalanceMaster[] findByUploadDate_PrevAndNext(
		int glBalanceMasterSid, Date uploadDate,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByUploadDate_PrevAndNext(glBalanceMasterSid,
			uploadDate, orderByComparator);
	}

	/**
	* Removes all the gl balance masters where uploadDate = &#63; from the database.
	*
	* @param uploadDate the upload date
	*/
	public static void removeByUploadDate(Date uploadDate) {
		getPersistence().removeByUploadDate(uploadDate);
	}

	/**
	* Returns the number of gl balance masters where uploadDate = &#63;.
	*
	* @param uploadDate the upload date
	* @return the number of matching gl balance masters
	*/
	public static int countByUploadDate(Date uploadDate) {
		return getPersistence().countByUploadDate(uploadDate);
	}

	/**
	* Returns all the gl balance masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByAccountId(
		java.lang.String accountId) {
		return getPersistence().findByAccountId(accountId);
	}

	/**
	* Returns a range of all the gl balance masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByAccountId(
		java.lang.String accountId, int start, int end) {
		return getPersistence().findByAccountId(accountId, start, end);
	}

	/**
	* Returns an ordered range of all the gl balance masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .findByAccountId(accountId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl balance masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountId(accountId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first gl balance master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByAccountId_First(
		java.lang.String accountId,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByAccountId_First(accountId, orderByComparator);
	}

	/**
	* Returns the first gl balance master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByAccountId_First(
		java.lang.String accountId,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .fetchByAccountId_First(accountId, orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByAccountId_Last(
		java.lang.String accountId,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByAccountId_Last(accountId, orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByAccountId_Last(
		java.lang.String accountId,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .fetchByAccountId_Last(accountId, orderByComparator);
	}

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where accountId = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public static GlBalanceMaster[] findByAccountId_PrevAndNext(
		int glBalanceMasterSid, java.lang.String accountId,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByAccountId_PrevAndNext(glBalanceMasterSid, accountId,
			orderByComparator);
	}

	/**
	* Removes all the gl balance masters where accountId = &#63; from the database.
	*
	* @param accountId the account ID
	*/
	public static void removeByAccountId(java.lang.String accountId) {
		getPersistence().removeByAccountId(accountId);
	}

	/**
	* Returns the number of gl balance masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the number of matching gl balance masters
	*/
	public static int countByAccountId(java.lang.String accountId) {
		return getPersistence().countByAccountId(accountId);
	}

	/**
	* Returns all the gl balance masters where year = &#63;.
	*
	* @param year the year
	* @return the matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByYear(java.lang.String year) {
		return getPersistence().findByYear(year);
	}

	/**
	* Returns a range of all the gl balance masters where year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param year the year
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByYear(java.lang.String year,
		int start, int end) {
		return getPersistence().findByYear(year, start, end);
	}

	/**
	* Returns an ordered range of all the gl balance masters where year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param year the year
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByYear(java.lang.String year,
		int start, int end, OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence().findByYear(year, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl balance masters where year = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param year the year
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByYear(java.lang.String year,
		int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByYear(year, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first gl balance master in the ordered set where year = &#63;.
	*
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByYear_First(java.lang.String year,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence().findByYear_First(year, orderByComparator);
	}

	/**
	* Returns the first gl balance master in the ordered set where year = &#63;.
	*
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByYear_First(java.lang.String year,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence().fetchByYear_First(year, orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where year = &#63;.
	*
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByYear_Last(java.lang.String year,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence().findByYear_Last(year, orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where year = &#63;.
	*
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByYear_Last(java.lang.String year,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence().fetchByYear_Last(year, orderByComparator);
	}

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where year = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param year the year
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public static GlBalanceMaster[] findByYear_PrevAndNext(
		int glBalanceMasterSid, java.lang.String year,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByYear_PrevAndNext(glBalanceMasterSid, year,
			orderByComparator);
	}

	/**
	* Removes all the gl balance masters where year = &#63; from the database.
	*
	* @param year the year
	*/
	public static void removeByYear(java.lang.String year) {
		getPersistence().removeByYear(year);
	}

	/**
	* Returns the number of gl balance masters where year = &#63;.
	*
	* @param year the year
	* @return the number of matching gl balance masters
	*/
	public static int countByYear(java.lang.String year) {
		return getPersistence().countByYear(year);
	}

	/**
	* Returns all the gl balance masters where period = &#63;.
	*
	* @param period the period
	* @return the matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByPeriod(java.lang.String period) {
		return getPersistence().findByPeriod(period);
	}

	/**
	* Returns a range of all the gl balance masters where period = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param period the period
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByPeriod(java.lang.String period,
		int start, int end) {
		return getPersistence().findByPeriod(period, start, end);
	}

	/**
	* Returns an ordered range of all the gl balance masters where period = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param period the period
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByPeriod(java.lang.String period,
		int start, int end, OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .findByPeriod(period, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl balance masters where period = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param period the period
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByPeriod(java.lang.String period,
		int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPeriod(period, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first gl balance master in the ordered set where period = &#63;.
	*
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByPeriod_First(java.lang.String period,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence().findByPeriod_First(period, orderByComparator);
	}

	/**
	* Returns the first gl balance master in the ordered set where period = &#63;.
	*
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByPeriod_First(java.lang.String period,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence().fetchByPeriod_First(period, orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where period = &#63;.
	*
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByPeriod_Last(java.lang.String period,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence().findByPeriod_Last(period, orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where period = &#63;.
	*
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByPeriod_Last(java.lang.String period,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence().fetchByPeriod_Last(period, orderByComparator);
	}

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where period = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param period the period
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public static GlBalanceMaster[] findByPeriod_PrevAndNext(
		int glBalanceMasterSid, java.lang.String period,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByPeriod_PrevAndNext(glBalanceMasterSid, period,
			orderByComparator);
	}

	/**
	* Removes all the gl balance masters where period = &#63; from the database.
	*
	* @param period the period
	*/
	public static void removeByPeriod(java.lang.String period) {
		getPersistence().removeByPeriod(period);
	}

	/**
	* Returns the number of gl balance masters where period = &#63;.
	*
	* @param period the period
	* @return the number of matching gl balance masters
	*/
	public static int countByPeriod(java.lang.String period) {
		return getPersistence().countByPeriod(period);
	}

	/**
	* Returns all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @return the matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByGlBalanceUnique(
		java.lang.String accountNo, java.lang.String period, Date uploadDate) {
		return getPersistence()
				   .findByGlBalanceUnique(accountNo, period, uploadDate);
	}

	/**
	* Returns a range of all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByGlBalanceUnique(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		int start, int end) {
		return getPersistence()
				   .findByGlBalanceUnique(accountNo, period, uploadDate, start,
			end);
	}

	/**
	* Returns an ordered range of all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByGlBalanceUnique(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		int start, int end, OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .findByGlBalanceUnique(accountNo, period, uploadDate, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl balance masters
	*/
	public static List<GlBalanceMaster> findByGlBalanceUnique(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGlBalanceUnique(accountNo, period, uploadDate, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByGlBalanceUnique_First(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByGlBalanceUnique_First(accountNo, period, uploadDate,
			orderByComparator);
	}

	/**
	* Returns the first gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByGlBalanceUnique_First(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .fetchByGlBalanceUnique_First(accountNo, period, uploadDate,
			orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master
	* @throws NoSuchGlBalanceMasterException if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster findByGlBalanceUnique_Last(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByGlBalanceUnique_Last(accountNo, period, uploadDate,
			orderByComparator);
	}

	/**
	* Returns the last gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl balance master, or <code>null</code> if a matching gl balance master could not be found
	*/
	public static GlBalanceMaster fetchByGlBalanceUnique_Last(
		java.lang.String accountNo, java.lang.String period, Date uploadDate,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence()
				   .fetchByGlBalanceUnique_Last(accountNo, period, uploadDate,
			orderByComparator);
	}

	/**
	* Returns the gl balance masters before and after the current gl balance master in the ordered set where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param glBalanceMasterSid the primary key of the current gl balance master
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public static GlBalanceMaster[] findByGlBalanceUnique_PrevAndNext(
		int glBalanceMasterSid, java.lang.String accountNo,
		java.lang.String period, Date uploadDate,
		OrderByComparator<GlBalanceMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence()
				   .findByGlBalanceUnique_PrevAndNext(glBalanceMasterSid,
			accountNo, period, uploadDate, orderByComparator);
	}

	/**
	* Removes all the gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63; from the database.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	*/
	public static void removeByGlBalanceUnique(java.lang.String accountNo,
		java.lang.String period, Date uploadDate) {
		getPersistence().removeByGlBalanceUnique(accountNo, period, uploadDate);
	}

	/**
	* Returns the number of gl balance masters where accountNo = &#63; and period = &#63; and uploadDate = &#63;.
	*
	* @param accountNo the account no
	* @param period the period
	* @param uploadDate the upload date
	* @return the number of matching gl balance masters
	*/
	public static int countByGlBalanceUnique(java.lang.String accountNo,
		java.lang.String period, Date uploadDate) {
		return getPersistence()
				   .countByGlBalanceUnique(accountNo, period, uploadDate);
	}

	/**
	* Caches the gl balance master in the entity cache if it is enabled.
	*
	* @param glBalanceMaster the gl balance master
	*/
	public static void cacheResult(GlBalanceMaster glBalanceMaster) {
		getPersistence().cacheResult(glBalanceMaster);
	}

	/**
	* Caches the gl balance masters in the entity cache if it is enabled.
	*
	* @param glBalanceMasters the gl balance masters
	*/
	public static void cacheResult(List<GlBalanceMaster> glBalanceMasters) {
		getPersistence().cacheResult(glBalanceMasters);
	}

	/**
	* Creates a new gl balance master with the primary key. Does not add the gl balance master to the database.
	*
	* @param glBalanceMasterSid the primary key for the new gl balance master
	* @return the new gl balance master
	*/
	public static GlBalanceMaster create(int glBalanceMasterSid) {
		return getPersistence().create(glBalanceMasterSid);
	}

	/**
	* Removes the gl balance master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param glBalanceMasterSid the primary key of the gl balance master
	* @return the gl balance master that was removed
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public static GlBalanceMaster remove(int glBalanceMasterSid)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence().remove(glBalanceMasterSid);
	}

	public static GlBalanceMaster updateImpl(GlBalanceMaster glBalanceMaster) {
		return getPersistence().updateImpl(glBalanceMaster);
	}

	/**
	* Returns the gl balance master with the primary key or throws a {@link NoSuchGlBalanceMasterException} if it could not be found.
	*
	* @param glBalanceMasterSid the primary key of the gl balance master
	* @return the gl balance master
	* @throws NoSuchGlBalanceMasterException if a gl balance master with the primary key could not be found
	*/
	public static GlBalanceMaster findByPrimaryKey(int glBalanceMasterSid)
		throws com.stpl.app.exception.NoSuchGlBalanceMasterException {
		return getPersistence().findByPrimaryKey(glBalanceMasterSid);
	}

	/**
	* Returns the gl balance master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param glBalanceMasterSid the primary key of the gl balance master
	* @return the gl balance master, or <code>null</code> if a gl balance master with the primary key could not be found
	*/
	public static GlBalanceMaster fetchByPrimaryKey(int glBalanceMasterSid) {
		return getPersistence().fetchByPrimaryKey(glBalanceMasterSid);
	}

	public static java.util.Map<java.io.Serializable, GlBalanceMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the gl balance masters.
	*
	* @return the gl balance masters
	*/
	public static List<GlBalanceMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the gl balance masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @return the range of gl balance masters
	*/
	public static List<GlBalanceMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the gl balance masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gl balance masters
	*/
	public static List<GlBalanceMaster> findAll(int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl balance masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlBalanceMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl balance masters
	* @param end the upper bound of the range of gl balance masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of gl balance masters
	*/
	public static List<GlBalanceMaster> findAll(int start, int end,
		OrderByComparator<GlBalanceMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the gl balance masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of gl balance masters.
	*
	* @return the number of gl balance masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static GlBalanceMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<GlBalanceMasterPersistence, GlBalanceMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(GlBalanceMasterPersistence.class);
}