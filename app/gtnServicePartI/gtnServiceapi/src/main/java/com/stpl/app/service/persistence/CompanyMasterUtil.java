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

import com.stpl.app.model.CompanyMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the company master service. This utility wraps {@link com.stpl.app.service.persistence.impl.CompanyMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyMasterPersistence
 * @see com.stpl.app.service.persistence.impl.CompanyMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class CompanyMasterUtil {
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
	public static void clearCache(CompanyMaster companyMaster) {
		getPersistence().clearCache(companyMaster);
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
	public static List<CompanyMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CompanyMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CompanyMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CompanyMaster update(CompanyMaster companyMaster) {
		return getPersistence().update(companyMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CompanyMaster update(CompanyMaster companyMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(companyMaster, serviceContext);
	}

	/**
	* Returns all the company masters where companyNo = &#63;.
	*
	* @param companyNo the company no
	* @return the matching company masters
	*/
	public static List<CompanyMaster> findByCompanyNo(
		java.lang.String companyNo) {
		return getPersistence().findByCompanyNo(companyNo);
	}

	/**
	* Returns a range of all the company masters where companyNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyNo the company no
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyNo(
		java.lang.String companyNo, int start, int end) {
		return getPersistence().findByCompanyNo(companyNo, start, end);
	}

	/**
	* Returns an ordered range of all the company masters where companyNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyNo the company no
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyNo(
		java.lang.String companyNo, int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .findByCompanyNo(companyNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the company masters where companyNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyNo the company no
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyNo(
		java.lang.String companyNo, int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyNo(companyNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first company master in the ordered set where companyNo = &#63;.
	*
	* @param companyNo the company no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public static CompanyMaster findByCompanyNo_First(
		java.lang.String companyNo,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyNo_First(companyNo, orderByComparator);
	}

	/**
	* Returns the first company master in the ordered set where companyNo = &#63;.
	*
	* @param companyNo the company no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public static CompanyMaster fetchByCompanyNo_First(
		java.lang.String companyNo,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyNo_First(companyNo, orderByComparator);
	}

	/**
	* Returns the last company master in the ordered set where companyNo = &#63;.
	*
	* @param companyNo the company no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public static CompanyMaster findByCompanyNo_Last(
		java.lang.String companyNo,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyNo_Last(companyNo, orderByComparator);
	}

	/**
	* Returns the last company master in the ordered set where companyNo = &#63;.
	*
	* @param companyNo the company no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public static CompanyMaster fetchByCompanyNo_Last(
		java.lang.String companyNo,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyNo_Last(companyNo, orderByComparator);
	}

	/**
	* Returns the company masters before and after the current company master in the ordered set where companyNo = &#63;.
	*
	* @param companyMasterSid the primary key of the current company master
	* @param companyNo the company no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public static CompanyMaster[] findByCompanyNo_PrevAndNext(
		int companyMasterSid, java.lang.String companyNo,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyNo_PrevAndNext(companyMasterSid, companyNo,
			orderByComparator);
	}

	/**
	* Removes all the company masters where companyNo = &#63; from the database.
	*
	* @param companyNo the company no
	*/
	public static void removeByCompanyNo(java.lang.String companyNo) {
		getPersistence().removeByCompanyNo(companyNo);
	}

	/**
	* Returns the number of company masters where companyNo = &#63;.
	*
	* @param companyNo the company no
	* @return the number of matching company masters
	*/
	public static int countByCompanyNo(java.lang.String companyNo) {
		return getPersistence().countByCompanyNo(companyNo);
	}

	/**
	* Returns all the company masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the matching company masters
	*/
	public static List<CompanyMaster> findByCompanyId(
		java.lang.String companyStringId) {
		return getPersistence().findByCompanyId(companyStringId);
	}

	/**
	* Returns a range of all the company masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end) {
		return getPersistence().findByCompanyId(companyStringId, start, end);
	}

	/**
	* Returns an ordered range of all the company masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyStringId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the company masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyStringId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public static CompanyMaster findByCompanyId_First(
		java.lang.String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyId_First(companyStringId, orderByComparator);
	}

	/**
	* Returns the first company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public static CompanyMaster fetchByCompanyId_First(
		java.lang.String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyStringId, orderByComparator);
	}

	/**
	* Returns the last company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public static CompanyMaster findByCompanyId_Last(
		java.lang.String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyId_Last(companyStringId, orderByComparator);
	}

	/**
	* Returns the last company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public static CompanyMaster fetchByCompanyId_Last(
		java.lang.String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyStringId, orderByComparator);
	}

	/**
	* Returns the company masters before and after the current company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyMasterSid the primary key of the current company master
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public static CompanyMaster[] findByCompanyId_PrevAndNext(
		int companyMasterSid, java.lang.String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(companyMasterSid,
			companyStringId, orderByComparator);
	}

	/**
	* Removes all the company masters where companyStringId = &#63; from the database.
	*
	* @param companyStringId the company string ID
	*/
	public static void removeByCompanyId(java.lang.String companyStringId) {
		getPersistence().removeByCompanyId(companyStringId);
	}

	/**
	* Returns the number of company masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the number of matching company masters
	*/
	public static int countByCompanyId(java.lang.String companyStringId) {
		return getPersistence().countByCompanyId(companyStringId);
	}

	/**
	* Returns all the company masters where companyName = &#63;.
	*
	* @param companyName the company name
	* @return the matching company masters
	*/
	public static List<CompanyMaster> findByCompanyName(
		java.lang.String companyName) {
		return getPersistence().findByCompanyName(companyName);
	}

	/**
	* Returns a range of all the company masters where companyName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyName the company name
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyName(
		java.lang.String companyName, int start, int end) {
		return getPersistence().findByCompanyName(companyName, start, end);
	}

	/**
	* Returns an ordered range of all the company masters where companyName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyName the company name
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyName(
		java.lang.String companyName, int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .findByCompanyName(companyName, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the company masters where companyName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyName the company name
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyName(
		java.lang.String companyName, int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyName(companyName, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first company master in the ordered set where companyName = &#63;.
	*
	* @param companyName the company name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public static CompanyMaster findByCompanyName_First(
		java.lang.String companyName,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyName_First(companyName, orderByComparator);
	}

	/**
	* Returns the first company master in the ordered set where companyName = &#63;.
	*
	* @param companyName the company name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public static CompanyMaster fetchByCompanyName_First(
		java.lang.String companyName,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyName_First(companyName, orderByComparator);
	}

	/**
	* Returns the last company master in the ordered set where companyName = &#63;.
	*
	* @param companyName the company name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public static CompanyMaster findByCompanyName_Last(
		java.lang.String companyName,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyName_Last(companyName, orderByComparator);
	}

	/**
	* Returns the last company master in the ordered set where companyName = &#63;.
	*
	* @param companyName the company name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public static CompanyMaster fetchByCompanyName_Last(
		java.lang.String companyName,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyName_Last(companyName, orderByComparator);
	}

	/**
	* Returns the company masters before and after the current company master in the ordered set where companyName = &#63;.
	*
	* @param companyMasterSid the primary key of the current company master
	* @param companyName the company name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public static CompanyMaster[] findByCompanyName_PrevAndNext(
		int companyMasterSid, java.lang.String companyName,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyName_PrevAndNext(companyMasterSid,
			companyName, orderByComparator);
	}

	/**
	* Removes all the company masters where companyName = &#63; from the database.
	*
	* @param companyName the company name
	*/
	public static void removeByCompanyName(java.lang.String companyName) {
		getPersistence().removeByCompanyName(companyName);
	}

	/**
	* Returns the number of company masters where companyName = &#63;.
	*
	* @param companyName the company name
	* @return the number of matching company masters
	*/
	public static int countByCompanyName(java.lang.String companyName) {
		return getPersistence().countByCompanyName(companyName);
	}

	/**
	* Returns all the company masters where companyType = &#63;.
	*
	* @param companyType the company type
	* @return the matching company masters
	*/
	public static List<CompanyMaster> findByCompanyType(int companyType) {
		return getPersistence().findByCompanyType(companyType);
	}

	/**
	* Returns a range of all the company masters where companyType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyType the company type
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyType(int companyType,
		int start, int end) {
		return getPersistence().findByCompanyType(companyType, start, end);
	}

	/**
	* Returns an ordered range of all the company masters where companyType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyType the company type
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyType(int companyType,
		int start, int end, OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .findByCompanyType(companyType, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the company masters where companyType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyType the company type
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyType(int companyType,
		int start, int end, OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyType(companyType, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first company master in the ordered set where companyType = &#63;.
	*
	* @param companyType the company type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public static CompanyMaster findByCompanyType_First(int companyType,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyType_First(companyType, orderByComparator);
	}

	/**
	* Returns the first company master in the ordered set where companyType = &#63;.
	*
	* @param companyType the company type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public static CompanyMaster fetchByCompanyType_First(int companyType,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyType_First(companyType, orderByComparator);
	}

	/**
	* Returns the last company master in the ordered set where companyType = &#63;.
	*
	* @param companyType the company type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public static CompanyMaster findByCompanyType_Last(int companyType,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyType_Last(companyType, orderByComparator);
	}

	/**
	* Returns the last company master in the ordered set where companyType = &#63;.
	*
	* @param companyType the company type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public static CompanyMaster fetchByCompanyType_Last(int companyType,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyType_Last(companyType, orderByComparator);
	}

	/**
	* Returns the company masters before and after the current company master in the ordered set where companyType = &#63;.
	*
	* @param companyMasterSid the primary key of the current company master
	* @param companyType the company type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public static CompanyMaster[] findByCompanyType_PrevAndNext(
		int companyMasterSid, int companyType,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyType_PrevAndNext(companyMasterSid,
			companyType, orderByComparator);
	}

	/**
	* Removes all the company masters where companyType = &#63; from the database.
	*
	* @param companyType the company type
	*/
	public static void removeByCompanyType(int companyType) {
		getPersistence().removeByCompanyType(companyType);
	}

	/**
	* Returns the number of company masters where companyType = &#63;.
	*
	* @param companyType the company type
	* @return the number of matching company masters
	*/
	public static int countByCompanyType(int companyType) {
		return getPersistence().countByCompanyType(companyType);
	}

	/**
	* Returns all the company masters where companyStatus = &#63;.
	*
	* @param companyStatus the company status
	* @return the matching company masters
	*/
	public static List<CompanyMaster> findByCompanyStatus(int companyStatus) {
		return getPersistence().findByCompanyStatus(companyStatus);
	}

	/**
	* Returns a range of all the company masters where companyStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStatus the company status
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyStatus(int companyStatus,
		int start, int end) {
		return getPersistence().findByCompanyStatus(companyStatus, start, end);
	}

	/**
	* Returns an ordered range of all the company masters where companyStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStatus the company status
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyStatus(int companyStatus,
		int start, int end, OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .findByCompanyStatus(companyStatus, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the company masters where companyStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStatus the company status
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyStatus(int companyStatus,
		int start, int end, OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyStatus(companyStatus, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first company master in the ordered set where companyStatus = &#63;.
	*
	* @param companyStatus the company status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public static CompanyMaster findByCompanyStatus_First(int companyStatus,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyStatus_First(companyStatus, orderByComparator);
	}

	/**
	* Returns the first company master in the ordered set where companyStatus = &#63;.
	*
	* @param companyStatus the company status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public static CompanyMaster fetchByCompanyStatus_First(int companyStatus,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyStatus_First(companyStatus, orderByComparator);
	}

	/**
	* Returns the last company master in the ordered set where companyStatus = &#63;.
	*
	* @param companyStatus the company status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public static CompanyMaster findByCompanyStatus_Last(int companyStatus,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyStatus_Last(companyStatus, orderByComparator);
	}

	/**
	* Returns the last company master in the ordered set where companyStatus = &#63;.
	*
	* @param companyStatus the company status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public static CompanyMaster fetchByCompanyStatus_Last(int companyStatus,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyStatus_Last(companyStatus, orderByComparator);
	}

	/**
	* Returns the company masters before and after the current company master in the ordered set where companyStatus = &#63;.
	*
	* @param companyMasterSid the primary key of the current company master
	* @param companyStatus the company status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public static CompanyMaster[] findByCompanyStatus_PrevAndNext(
		int companyMasterSid, int companyStatus,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyStatus_PrevAndNext(companyMasterSid,
			companyStatus, orderByComparator);
	}

	/**
	* Removes all the company masters where companyStatus = &#63; from the database.
	*
	* @param companyStatus the company status
	*/
	public static void removeByCompanyStatus(int companyStatus) {
		getPersistence().removeByCompanyStatus(companyStatus);
	}

	/**
	* Returns the number of company masters where companyStatus = &#63;.
	*
	* @param companyStatus the company status
	* @return the number of matching company masters
	*/
	public static int countByCompanyStatus(int companyStatus) {
		return getPersistence().countByCompanyStatus(companyStatus);
	}

	/**
	* Returns all the company masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the matching company masters
	*/
	public static List<CompanyMaster> findByCompanyUnique(
		java.lang.String companyStringId) {
		return getPersistence().findByCompanyUnique(companyStringId);
	}

	/**
	* Returns a range of all the company masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyUnique(
		java.lang.String companyStringId, int start, int end) {
		return getPersistence().findByCompanyUnique(companyStringId, start, end);
	}

	/**
	* Returns an ordered range of all the company masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyUnique(
		java.lang.String companyStringId, int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .findByCompanyUnique(companyStringId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the company masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company masters
	*/
	public static List<CompanyMaster> findByCompanyUnique(
		java.lang.String companyStringId, int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyUnique(companyStringId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public static CompanyMaster findByCompanyUnique_First(
		java.lang.String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyUnique_First(companyStringId, orderByComparator);
	}

	/**
	* Returns the first company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public static CompanyMaster fetchByCompanyUnique_First(
		java.lang.String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyUnique_First(companyStringId,
			orderByComparator);
	}

	/**
	* Returns the last company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master
	* @throws NoSuchCompanyMasterException if a matching company master could not be found
	*/
	public static CompanyMaster findByCompanyUnique_Last(
		java.lang.String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyUnique_Last(companyStringId, orderByComparator);
	}

	/**
	* Returns the last company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company master, or <code>null</code> if a matching company master could not be found
	*/
	public static CompanyMaster fetchByCompanyUnique_Last(
		java.lang.String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyUnique_Last(companyStringId, orderByComparator);
	}

	/**
	* Returns the company masters before and after the current company master in the ordered set where companyStringId = &#63;.
	*
	* @param companyMasterSid the primary key of the current company master
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public static CompanyMaster[] findByCompanyUnique_PrevAndNext(
		int companyMasterSid, java.lang.String companyStringId,
		OrderByComparator<CompanyMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence()
				   .findByCompanyUnique_PrevAndNext(companyMasterSid,
			companyStringId, orderByComparator);
	}

	/**
	* Removes all the company masters where companyStringId = &#63; from the database.
	*
	* @param companyStringId the company string ID
	*/
	public static void removeByCompanyUnique(java.lang.String companyStringId) {
		getPersistence().removeByCompanyUnique(companyStringId);
	}

	/**
	* Returns the number of company masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the number of matching company masters
	*/
	public static int countByCompanyUnique(java.lang.String companyStringId) {
		return getPersistence().countByCompanyUnique(companyStringId);
	}

	/**
	* Caches the company master in the entity cache if it is enabled.
	*
	* @param companyMaster the company master
	*/
	public static void cacheResult(CompanyMaster companyMaster) {
		getPersistence().cacheResult(companyMaster);
	}

	/**
	* Caches the company masters in the entity cache if it is enabled.
	*
	* @param companyMasters the company masters
	*/
	public static void cacheResult(List<CompanyMaster> companyMasters) {
		getPersistence().cacheResult(companyMasters);
	}

	/**
	* Creates a new company master with the primary key. Does not add the company master to the database.
	*
	* @param companyMasterSid the primary key for the new company master
	* @return the new company master
	*/
	public static CompanyMaster create(int companyMasterSid) {
		return getPersistence().create(companyMasterSid);
	}

	/**
	* Removes the company master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyMasterSid the primary key of the company master
	* @return the company master that was removed
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public static CompanyMaster remove(int companyMasterSid)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence().remove(companyMasterSid);
	}

	public static CompanyMaster updateImpl(CompanyMaster companyMaster) {
		return getPersistence().updateImpl(companyMaster);
	}

	/**
	* Returns the company master with the primary key or throws a {@link NoSuchCompanyMasterException} if it could not be found.
	*
	* @param companyMasterSid the primary key of the company master
	* @return the company master
	* @throws NoSuchCompanyMasterException if a company master with the primary key could not be found
	*/
	public static CompanyMaster findByPrimaryKey(int companyMasterSid)
		throws com.stpl.app.exception.NoSuchCompanyMasterException {
		return getPersistence().findByPrimaryKey(companyMasterSid);
	}

	/**
	* Returns the company master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyMasterSid the primary key of the company master
	* @return the company master, or <code>null</code> if a company master with the primary key could not be found
	*/
	public static CompanyMaster fetchByPrimaryKey(int companyMasterSid) {
		return getPersistence().fetchByPrimaryKey(companyMasterSid);
	}

	public static java.util.Map<java.io.Serializable, CompanyMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the company masters.
	*
	* @return the company masters
	*/
	public static List<CompanyMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @return the range of company masters
	*/
	public static List<CompanyMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of company masters
	*/
	public static List<CompanyMaster> findAll(int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company masters
	* @param end the upper bound of the range of company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of company masters
	*/
	public static List<CompanyMaster> findAll(int start, int end,
		OrderByComparator<CompanyMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the company masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of company masters.
	*
	* @return the number of company masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CompanyMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CompanyMasterPersistence, CompanyMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CompanyMasterPersistence.class);
}