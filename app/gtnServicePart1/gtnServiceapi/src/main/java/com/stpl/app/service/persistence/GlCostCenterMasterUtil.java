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

import com.stpl.app.model.GlCostCenterMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the gl cost center master service. This utility wraps {@link com.stpl.app.service.persistence.impl.GlCostCenterMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GlCostCenterMasterPersistence
 * @see com.stpl.app.service.persistence.impl.GlCostCenterMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class GlCostCenterMasterUtil {
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
	public static void clearCache(GlCostCenterMaster glCostCenterMaster) {
		getPersistence().clearCache(glCostCenterMaster);
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
	public static List<GlCostCenterMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GlCostCenterMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GlCostCenterMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GlCostCenterMaster update(
		GlCostCenterMaster glCostCenterMaster) {
		return getPersistence().update(glCostCenterMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GlCostCenterMaster update(
		GlCostCenterMaster glCostCenterMaster, ServiceContext serviceContext) {
		return getPersistence().update(glCostCenterMaster, serviceContext);
	}

	/**
	* Returns all the gl cost center masters where companyCostCenter = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @return the matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByCompanyCostCenter(
		java.lang.String companyCostCenter) {
		return getPersistence().findByCompanyCostCenter(companyCostCenter);
	}

	/**
	* Returns a range of all the gl cost center masters where companyCostCenter = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCostCenter the company cost center
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @return the range of matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByCompanyCostCenter(
		java.lang.String companyCostCenter, int start, int end) {
		return getPersistence()
				   .findByCompanyCostCenter(companyCostCenter, start, end);
	}

	/**
	* Returns an ordered range of all the gl cost center masters where companyCostCenter = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCostCenter the company cost center
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByCompanyCostCenter(
		java.lang.String companyCostCenter, int start, int end,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence()
				   .findByCompanyCostCenter(companyCostCenter, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl cost center masters where companyCostCenter = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCostCenter the company cost center
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByCompanyCostCenter(
		java.lang.String companyCostCenter, int start, int end,
		OrderByComparator<GlCostCenterMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyCostCenter(companyCostCenter, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first gl cost center master in the ordered set where companyCostCenter = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster findByCompanyCostCenter_First(
		java.lang.String companyCostCenter,
		OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence()
				   .findByCompanyCostCenter_First(companyCostCenter,
			orderByComparator);
	}

	/**
	* Returns the first gl cost center master in the ordered set where companyCostCenter = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster fetchByCompanyCostCenter_First(
		java.lang.String companyCostCenter,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyCostCenter_First(companyCostCenter,
			orderByComparator);
	}

	/**
	* Returns the last gl cost center master in the ordered set where companyCostCenter = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster findByCompanyCostCenter_Last(
		java.lang.String companyCostCenter,
		OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence()
				   .findByCompanyCostCenter_Last(companyCostCenter,
			orderByComparator);
	}

	/**
	* Returns the last gl cost center master in the ordered set where companyCostCenter = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster fetchByCompanyCostCenter_Last(
		java.lang.String companyCostCenter,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyCostCenter_Last(companyCostCenter,
			orderByComparator);
	}

	/**
	* Returns the gl cost center masters before and after the current gl cost center master in the ordered set where companyCostCenter = &#63;.
	*
	* @param glCostCenterMasterSid the primary key of the current gl cost center master
	* @param companyCostCenter the company cost center
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
	*/
	public static GlCostCenterMaster[] findByCompanyCostCenter_PrevAndNext(
		int glCostCenterMasterSid, java.lang.String companyCostCenter,
		OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence()
				   .findByCompanyCostCenter_PrevAndNext(glCostCenterMasterSid,
			companyCostCenter, orderByComparator);
	}

	/**
	* Removes all the gl cost center masters where companyCostCenter = &#63; from the database.
	*
	* @param companyCostCenter the company cost center
	*/
	public static void removeByCompanyCostCenter(
		java.lang.String companyCostCenter) {
		getPersistence().removeByCompanyCostCenter(companyCostCenter);
	}

	/**
	* Returns the number of gl cost center masters where companyCostCenter = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @return the number of matching gl cost center masters
	*/
	public static int countByCompanyCostCenter(
		java.lang.String companyCostCenter) {
		return getPersistence().countByCompanyCostCenter(companyCostCenter);
	}

	/**
	* Returns all the gl cost center masters where ndc8 = &#63;.
	*
	* @param ndc8 the ndc8
	* @return the matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByNdc8(java.lang.String ndc8) {
		return getPersistence().findByNdc8(ndc8);
	}

	/**
	* Returns a range of all the gl cost center masters where ndc8 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ndc8 the ndc8
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @return the range of matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByNdc8(java.lang.String ndc8,
		int start, int end) {
		return getPersistence().findByNdc8(ndc8, start, end);
	}

	/**
	* Returns an ordered range of all the gl cost center masters where ndc8 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ndc8 the ndc8
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByNdc8(java.lang.String ndc8,
		int start, int end,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence().findByNdc8(ndc8, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl cost center masters where ndc8 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ndc8 the ndc8
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByNdc8(java.lang.String ndc8,
		int start, int end,
		OrderByComparator<GlCostCenterMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByNdc8(ndc8, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first gl cost center master in the ordered set where ndc8 = &#63;.
	*
	* @param ndc8 the ndc8
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster findByNdc8_First(java.lang.String ndc8,
		OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence().findByNdc8_First(ndc8, orderByComparator);
	}

	/**
	* Returns the first gl cost center master in the ordered set where ndc8 = &#63;.
	*
	* @param ndc8 the ndc8
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster fetchByNdc8_First(java.lang.String ndc8,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence().fetchByNdc8_First(ndc8, orderByComparator);
	}

	/**
	* Returns the last gl cost center master in the ordered set where ndc8 = &#63;.
	*
	* @param ndc8 the ndc8
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster findByNdc8_Last(java.lang.String ndc8,
		OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence().findByNdc8_Last(ndc8, orderByComparator);
	}

	/**
	* Returns the last gl cost center master in the ordered set where ndc8 = &#63;.
	*
	* @param ndc8 the ndc8
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster fetchByNdc8_Last(java.lang.String ndc8,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence().fetchByNdc8_Last(ndc8, orderByComparator);
	}

	/**
	* Returns the gl cost center masters before and after the current gl cost center master in the ordered set where ndc8 = &#63;.
	*
	* @param glCostCenterMasterSid the primary key of the current gl cost center master
	* @param ndc8 the ndc8
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
	*/
	public static GlCostCenterMaster[] findByNdc8_PrevAndNext(
		int glCostCenterMasterSid, java.lang.String ndc8,
		OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence()
				   .findByNdc8_PrevAndNext(glCostCenterMasterSid, ndc8,
			orderByComparator);
	}

	/**
	* Removes all the gl cost center masters where ndc8 = &#63; from the database.
	*
	* @param ndc8 the ndc8
	*/
	public static void removeByNdc8(java.lang.String ndc8) {
		getPersistence().removeByNdc8(ndc8);
	}

	/**
	* Returns the number of gl cost center masters where ndc8 = &#63;.
	*
	* @param ndc8 the ndc8
	* @return the number of matching gl cost center masters
	*/
	public static int countByNdc8(java.lang.String ndc8) {
		return getPersistence().countByNdc8(ndc8);
	}

	/**
	* Returns all the gl cost center masters where companyCode = &#63;.
	*
	* @param companyCode the company code
	* @return the matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByCompanyCode(
		java.lang.String companyCode) {
		return getPersistence().findByCompanyCode(companyCode);
	}

	/**
	* Returns a range of all the gl cost center masters where companyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCode the company code
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @return the range of matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByCompanyCode(
		java.lang.String companyCode, int start, int end) {
		return getPersistence().findByCompanyCode(companyCode, start, end);
	}

	/**
	* Returns an ordered range of all the gl cost center masters where companyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCode the company code
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByCompanyCode(
		java.lang.String companyCode, int start, int end,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence()
				   .findByCompanyCode(companyCode, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl cost center masters where companyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCode the company code
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByCompanyCode(
		java.lang.String companyCode, int start, int end,
		OrderByComparator<GlCostCenterMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyCode(companyCode, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first gl cost center master in the ordered set where companyCode = &#63;.
	*
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster findByCompanyCode_First(
		java.lang.String companyCode,
		OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence()
				   .findByCompanyCode_First(companyCode, orderByComparator);
	}

	/**
	* Returns the first gl cost center master in the ordered set where companyCode = &#63;.
	*
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster fetchByCompanyCode_First(
		java.lang.String companyCode,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyCode_First(companyCode, orderByComparator);
	}

	/**
	* Returns the last gl cost center master in the ordered set where companyCode = &#63;.
	*
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster findByCompanyCode_Last(
		java.lang.String companyCode,
		OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence()
				   .findByCompanyCode_Last(companyCode, orderByComparator);
	}

	/**
	* Returns the last gl cost center master in the ordered set where companyCode = &#63;.
	*
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster fetchByCompanyCode_Last(
		java.lang.String companyCode,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyCode_Last(companyCode, orderByComparator);
	}

	/**
	* Returns the gl cost center masters before and after the current gl cost center master in the ordered set where companyCode = &#63;.
	*
	* @param glCostCenterMasterSid the primary key of the current gl cost center master
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
	*/
	public static GlCostCenterMaster[] findByCompanyCode_PrevAndNext(
		int glCostCenterMasterSid, java.lang.String companyCode,
		OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence()
				   .findByCompanyCode_PrevAndNext(glCostCenterMasterSid,
			companyCode, orderByComparator);
	}

	/**
	* Removes all the gl cost center masters where companyCode = &#63; from the database.
	*
	* @param companyCode the company code
	*/
	public static void removeByCompanyCode(java.lang.String companyCode) {
		getPersistence().removeByCompanyCode(companyCode);
	}

	/**
	* Returns the number of gl cost center masters where companyCode = &#63;.
	*
	* @param companyCode the company code
	* @return the number of matching gl cost center masters
	*/
	public static int countByCompanyCode(java.lang.String companyCode) {
		return getPersistence().countByCompanyCode(companyCode);
	}

	/**
	* Returns all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @return the matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByGlCostCenterUnique(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode) {
		return getPersistence()
				   .findByGlCostCenterUnique(companyCostCenter, ndc8,
			companyCode);
	}

	/**
	* Returns a range of all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @return the range of matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByGlCostCenterUnique(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode, int start, int end) {
		return getPersistence()
				   .findByGlCostCenterUnique(companyCostCenter, ndc8,
			companyCode, start, end);
	}

	/**
	* Returns an ordered range of all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByGlCostCenterUnique(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode, int start, int end,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence()
				   .findByGlCostCenterUnique(companyCostCenter, ndc8,
			companyCode, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gl cost center masters
	*/
	public static List<GlCostCenterMaster> findByGlCostCenterUnique(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode, int start, int end,
		OrderByComparator<GlCostCenterMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGlCostCenterUnique(companyCostCenter, ndc8,
			companyCode, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster findByGlCostCenterUnique_First(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode,
		OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence()
				   .findByGlCostCenterUnique_First(companyCostCenter, ndc8,
			companyCode, orderByComparator);
	}

	/**
	* Returns the first gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster fetchByGlCostCenterUnique_First(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence()
				   .fetchByGlCostCenterUnique_First(companyCostCenter, ndc8,
			companyCode, orderByComparator);
	}

	/**
	* Returns the last gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster findByGlCostCenterUnique_Last(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode,
		OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence()
				   .findByGlCostCenterUnique_Last(companyCostCenter, ndc8,
			companyCode, orderByComparator);
	}

	/**
	* Returns the last gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gl cost center master, or <code>null</code> if a matching gl cost center master could not be found
	*/
	public static GlCostCenterMaster fetchByGlCostCenterUnique_Last(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence()
				   .fetchByGlCostCenterUnique_Last(companyCostCenter, ndc8,
			companyCode, orderByComparator);
	}

	/**
	* Returns the gl cost center masters before and after the current gl cost center master in the ordered set where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param glCostCenterMasterSid the primary key of the current gl cost center master
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
	*/
	public static GlCostCenterMaster[] findByGlCostCenterUnique_PrevAndNext(
		int glCostCenterMasterSid, java.lang.String companyCostCenter,
		java.lang.String ndc8, java.lang.String companyCode,
		OrderByComparator<GlCostCenterMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence()
				   .findByGlCostCenterUnique_PrevAndNext(glCostCenterMasterSid,
			companyCostCenter, ndc8, companyCode, orderByComparator);
	}

	/**
	* Removes all the gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63; from the database.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	*/
	public static void removeByGlCostCenterUnique(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode) {
		getPersistence()
			.removeByGlCostCenterUnique(companyCostCenter, ndc8, companyCode);
	}

	/**
	* Returns the number of gl cost center masters where companyCostCenter = &#63; and ndc8 = &#63; and companyCode = &#63;.
	*
	* @param companyCostCenter the company cost center
	* @param ndc8 the ndc8
	* @param companyCode the company code
	* @return the number of matching gl cost center masters
	*/
	public static int countByGlCostCenterUnique(
		java.lang.String companyCostCenter, java.lang.String ndc8,
		java.lang.String companyCode) {
		return getPersistence()
				   .countByGlCostCenterUnique(companyCostCenter, ndc8,
			companyCode);
	}

	/**
	* Caches the gl cost center master in the entity cache if it is enabled.
	*
	* @param glCostCenterMaster the gl cost center master
	*/
	public static void cacheResult(GlCostCenterMaster glCostCenterMaster) {
		getPersistence().cacheResult(glCostCenterMaster);
	}

	/**
	* Caches the gl cost center masters in the entity cache if it is enabled.
	*
	* @param glCostCenterMasters the gl cost center masters
	*/
	public static void cacheResult(List<GlCostCenterMaster> glCostCenterMasters) {
		getPersistence().cacheResult(glCostCenterMasters);
	}

	/**
	* Creates a new gl cost center master with the primary key. Does not add the gl cost center master to the database.
	*
	* @param glCostCenterMasterSid the primary key for the new gl cost center master
	* @return the new gl cost center master
	*/
	public static GlCostCenterMaster create(int glCostCenterMasterSid) {
		return getPersistence().create(glCostCenterMasterSid);
	}

	/**
	* Removes the gl cost center master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param glCostCenterMasterSid the primary key of the gl cost center master
	* @return the gl cost center master that was removed
	* @throws NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
	*/
	public static GlCostCenterMaster remove(int glCostCenterMasterSid)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence().remove(glCostCenterMasterSid);
	}

	public static GlCostCenterMaster updateImpl(
		GlCostCenterMaster glCostCenterMaster) {
		return getPersistence().updateImpl(glCostCenterMaster);
	}

	/**
	* Returns the gl cost center master with the primary key or throws a {@link NoSuchGlCostCenterMasterException} if it could not be found.
	*
	* @param glCostCenterMasterSid the primary key of the gl cost center master
	* @return the gl cost center master
	* @throws NoSuchGlCostCenterMasterException if a gl cost center master with the primary key could not be found
	*/
	public static GlCostCenterMaster findByPrimaryKey(int glCostCenterMasterSid)
		throws com.stpl.app.exception.NoSuchGlCostCenterMasterException {
		return getPersistence().findByPrimaryKey(glCostCenterMasterSid);
	}

	/**
	* Returns the gl cost center master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param glCostCenterMasterSid the primary key of the gl cost center master
	* @return the gl cost center master, or <code>null</code> if a gl cost center master with the primary key could not be found
	*/
	public static GlCostCenterMaster fetchByPrimaryKey(
		int glCostCenterMasterSid) {
		return getPersistence().fetchByPrimaryKey(glCostCenterMasterSid);
	}

	public static java.util.Map<java.io.Serializable, GlCostCenterMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the gl cost center masters.
	*
	* @return the gl cost center masters
	*/
	public static List<GlCostCenterMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the gl cost center masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @return the range of gl cost center masters
	*/
	public static List<GlCostCenterMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the gl cost center masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gl cost center masters
	*/
	public static List<GlCostCenterMaster> findAll(int start, int end,
		OrderByComparator<GlCostCenterMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the gl cost center masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GlCostCenterMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gl cost center masters
	* @param end the upper bound of the range of gl cost center masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of gl cost center masters
	*/
	public static List<GlCostCenterMaster> findAll(int start, int end,
		OrderByComparator<GlCostCenterMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the gl cost center masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of gl cost center masters.
	*
	* @return the number of gl cost center masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static GlCostCenterMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<GlCostCenterMasterPersistence, GlCostCenterMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(GlCostCenterMasterPersistence.class);
}