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

import com.stpl.app.model.FormulaDetailsMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the formula details master service. This utility wraps {@link com.stpl.app.service.persistence.impl.FormulaDetailsMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FormulaDetailsMasterPersistence
 * @see com.stpl.app.service.persistence.impl.FormulaDetailsMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class FormulaDetailsMasterUtil {
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
	public static void clearCache(FormulaDetailsMaster formulaDetailsMaster) {
		getPersistence().clearCache(formulaDetailsMaster);
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
	public static List<FormulaDetailsMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FormulaDetailsMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FormulaDetailsMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FormulaDetailsMaster update(
		FormulaDetailsMaster formulaDetailsMaster) {
		return getPersistence().update(formulaDetailsMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FormulaDetailsMaster update(
		FormulaDetailsMaster formulaDetailsMaster, ServiceContext serviceContext) {
		return getPersistence().update(formulaDetailsMaster, serviceContext);
	}

	/**
	* Returns all the formula details masters where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @return the matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByFormulaId(
		java.lang.String formulaId) {
		return getPersistence().findByFormulaId(formulaId);
	}

	/**
	* Returns a range of all the formula details masters where formulaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaId the formula ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByFormulaId(
		java.lang.String formulaId, int start, int end) {
		return getPersistence().findByFormulaId(formulaId, start, end);
	}

	/**
	* Returns an ordered range of all the formula details masters where formulaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaId the formula ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByFormulaId(
		java.lang.String formulaId, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .findByFormulaId(formulaId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the formula details masters where formulaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaId the formula ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByFormulaId(
		java.lang.String formulaId, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByFormulaId(formulaId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByFormulaId_First(
		java.lang.String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByFormulaId_First(formulaId, orderByComparator);
	}

	/**
	* Returns the first formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByFormulaId_First(
		java.lang.String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByFormulaId_First(formulaId, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByFormulaId_Last(
		java.lang.String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByFormulaId_Last(formulaId, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByFormulaId_Last(
		java.lang.String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByFormulaId_Last(formulaId, orderByComparator);
	}

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public static FormulaDetailsMaster[] findByFormulaId_PrevAndNext(
		int formulaDetailsMasterSid, java.lang.String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByFormulaId_PrevAndNext(formulaDetailsMasterSid,
			formulaId, orderByComparator);
	}

	/**
	* Removes all the formula details masters where formulaId = &#63; from the database.
	*
	* @param formulaId the formula ID
	*/
	public static void removeByFormulaId(java.lang.String formulaId) {
		getPersistence().removeByFormulaId(formulaId);
	}

	/**
	* Returns the number of formula details masters where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @return the number of matching formula details masters
	*/
	public static int countByFormulaId(java.lang.String formulaId) {
		return getPersistence().countByFormulaId(formulaId);
	}

	/**
	* Returns all the formula details masters where formulaNo = &#63;.
	*
	* @param formulaNo the formula no
	* @return the matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByFormulaNo(
		java.lang.String formulaNo) {
		return getPersistence().findByFormulaNo(formulaNo);
	}

	/**
	* Returns a range of all the formula details masters where formulaNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaNo the formula no
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByFormulaNo(
		java.lang.String formulaNo, int start, int end) {
		return getPersistence().findByFormulaNo(formulaNo, start, end);
	}

	/**
	* Returns an ordered range of all the formula details masters where formulaNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaNo the formula no
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByFormulaNo(
		java.lang.String formulaNo, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .findByFormulaNo(formulaNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the formula details masters where formulaNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaNo the formula no
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByFormulaNo(
		java.lang.String formulaNo, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByFormulaNo(formulaNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first formula details master in the ordered set where formulaNo = &#63;.
	*
	* @param formulaNo the formula no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByFormulaNo_First(
		java.lang.String formulaNo,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByFormulaNo_First(formulaNo, orderByComparator);
	}

	/**
	* Returns the first formula details master in the ordered set where formulaNo = &#63;.
	*
	* @param formulaNo the formula no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByFormulaNo_First(
		java.lang.String formulaNo,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByFormulaNo_First(formulaNo, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where formulaNo = &#63;.
	*
	* @param formulaNo the formula no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByFormulaNo_Last(
		java.lang.String formulaNo,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByFormulaNo_Last(formulaNo, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where formulaNo = &#63;.
	*
	* @param formulaNo the formula no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByFormulaNo_Last(
		java.lang.String formulaNo,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByFormulaNo_Last(formulaNo, orderByComparator);
	}

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where formulaNo = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param formulaNo the formula no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public static FormulaDetailsMaster[] findByFormulaNo_PrevAndNext(
		int formulaDetailsMasterSid, java.lang.String formulaNo,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByFormulaNo_PrevAndNext(formulaDetailsMasterSid,
			formulaNo, orderByComparator);
	}

	/**
	* Removes all the formula details masters where formulaNo = &#63; from the database.
	*
	* @param formulaNo the formula no
	*/
	public static void removeByFormulaNo(java.lang.String formulaNo) {
		getPersistence().removeByFormulaNo(formulaNo);
	}

	/**
	* Returns the number of formula details masters where formulaNo = &#63;.
	*
	* @param formulaNo the formula no
	* @return the number of matching formula details masters
	*/
	public static int countByFormulaNo(java.lang.String formulaNo) {
		return getPersistence().countByFormulaNo(formulaNo);
	}

	/**
	* Returns all the formula details masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByItemId(
		java.lang.String itemId) {
		return getPersistence().findByItemId(itemId);
	}

	/**
	* Returns a range of all the formula details masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByItemId(
		java.lang.String itemId, int start, int end) {
		return getPersistence().findByItemId(itemId, start, end);
	}

	/**
	* Returns an ordered range of all the formula details masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByItemId(
		java.lang.String itemId, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .findByItemId(itemId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the formula details masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByItemId(
		java.lang.String itemId, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemId(itemId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first formula details master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByItemId_First(
		java.lang.String itemId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence().findByItemId_First(itemId, orderByComparator);
	}

	/**
	* Returns the first formula details master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByItemId_First(
		java.lang.String itemId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence().fetchByItemId_First(itemId, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByItemId_Last(
		java.lang.String itemId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence().findByItemId_Last(itemId, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByItemId_Last(
		java.lang.String itemId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence().fetchByItemId_Last(itemId, orderByComparator);
	}

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where itemId = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public static FormulaDetailsMaster[] findByItemId_PrevAndNext(
		int formulaDetailsMasterSid, java.lang.String itemId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByItemId_PrevAndNext(formulaDetailsMasterSid, itemId,
			orderByComparator);
	}

	/**
	* Removes all the formula details masters where itemId = &#63; from the database.
	*
	* @param itemId the item ID
	*/
	public static void removeByItemId(java.lang.String itemId) {
		getPersistence().removeByItemId(itemId);
	}

	/**
	* Returns the number of formula details masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the number of matching formula details masters
	*/
	public static int countByItemId(java.lang.String itemId) {
		return getPersistence().countByItemId(itemId);
	}

	/**
	* Returns all the formula details masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByCompanyId(
		java.lang.String companyStringId) {
		return getPersistence().findByCompanyId(companyStringId);
	}

	/**
	* Returns a range of all the formula details masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end) {
		return getPersistence().findByCompanyId(companyStringId, start, end);
	}

	/**
	* Returns an ordered range of all the formula details masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyStringId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the formula details masters where companyStringId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringId the company string ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByCompanyId(
		java.lang.String companyStringId, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyStringId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first formula details master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByCompanyId_First(
		java.lang.String companyStringId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByCompanyId_First(companyStringId, orderByComparator);
	}

	/**
	* Returns the first formula details master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByCompanyId_First(
		java.lang.String companyStringId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyStringId, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByCompanyId_Last(
		java.lang.String companyStringId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByCompanyId_Last(companyStringId, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByCompanyId_Last(
		java.lang.String companyStringId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyStringId, orderByComparator);
	}

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where companyStringId = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param companyStringId the company string ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public static FormulaDetailsMaster[] findByCompanyId_PrevAndNext(
		int formulaDetailsMasterSid, java.lang.String companyStringId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(formulaDetailsMasterSid,
			companyStringId, orderByComparator);
	}

	/**
	* Removes all the formula details masters where companyStringId = &#63; from the database.
	*
	* @param companyStringId the company string ID
	*/
	public static void removeByCompanyId(java.lang.String companyStringId) {
		getPersistence().removeByCompanyId(companyStringId);
	}

	/**
	* Returns the number of formula details masters where companyStringId = &#63;.
	*
	* @param companyStringId the company string ID
	* @return the number of matching formula details masters
	*/
	public static int countByCompanyId(java.lang.String companyStringId) {
		return getPersistence().countByCompanyId(companyStringId);
	}

	/**
	* Returns all the formula details masters where startDate = &#63;.
	*
	* @param startDate the start date
	* @return the matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByStartDate(Date startDate) {
		return getPersistence().findByStartDate(startDate);
	}

	/**
	* Returns a range of all the formula details masters where startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param startDate the start date
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByStartDate(Date startDate,
		int start, int end) {
		return getPersistence().findByStartDate(startDate, start, end);
	}

	/**
	* Returns an ordered range of all the formula details masters where startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param startDate the start date
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByStartDate(Date startDate,
		int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .findByStartDate(startDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the formula details masters where startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param startDate the start date
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByStartDate(Date startDate,
		int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByStartDate(startDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first formula details master in the ordered set where startDate = &#63;.
	*
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByStartDate_First(Date startDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByStartDate_First(startDate, orderByComparator);
	}

	/**
	* Returns the first formula details master in the ordered set where startDate = &#63;.
	*
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByStartDate_First(Date startDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByStartDate_First(startDate, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where startDate = &#63;.
	*
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByStartDate_Last(Date startDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByStartDate_Last(startDate, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where startDate = &#63;.
	*
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByStartDate_Last(Date startDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByStartDate_Last(startDate, orderByComparator);
	}

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where startDate = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public static FormulaDetailsMaster[] findByStartDate_PrevAndNext(
		int formulaDetailsMasterSid, Date startDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByStartDate_PrevAndNext(formulaDetailsMasterSid,
			startDate, orderByComparator);
	}

	/**
	* Removes all the formula details masters where startDate = &#63; from the database.
	*
	* @param startDate the start date
	*/
	public static void removeByStartDate(Date startDate) {
		getPersistence().removeByStartDate(startDate);
	}

	/**
	* Returns the number of formula details masters where startDate = &#63;.
	*
	* @param startDate the start date
	* @return the number of matching formula details masters
	*/
	public static int countByStartDate(Date startDate) {
		return getPersistence().countByStartDate(startDate);
	}

	/**
	* Returns all the formula details masters where endDate = &#63;.
	*
	* @param endDate the end date
	* @return the matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByEndDate(Date endDate) {
		return getPersistence().findByEndDate(endDate);
	}

	/**
	* Returns a range of all the formula details masters where endDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param endDate the end date
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByEndDate(Date endDate,
		int start, int end) {
		return getPersistence().findByEndDate(endDate, start, end);
	}

	/**
	* Returns an ordered range of all the formula details masters where endDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param endDate the end date
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByEndDate(Date endDate,
		int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .findByEndDate(endDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the formula details masters where endDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param endDate the end date
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByEndDate(Date endDate,
		int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByEndDate(endDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first formula details master in the ordered set where endDate = &#63;.
	*
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByEndDate_First(Date endDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence().findByEndDate_First(endDate, orderByComparator);
	}

	/**
	* Returns the first formula details master in the ordered set where endDate = &#63;.
	*
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByEndDate_First(Date endDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence().fetchByEndDate_First(endDate, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where endDate = &#63;.
	*
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByEndDate_Last(Date endDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence().findByEndDate_Last(endDate, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where endDate = &#63;.
	*
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByEndDate_Last(Date endDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence().fetchByEndDate_Last(endDate, orderByComparator);
	}

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where endDate = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param endDate the end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public static FormulaDetailsMaster[] findByEndDate_PrevAndNext(
		int formulaDetailsMasterSid, Date endDate,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByEndDate_PrevAndNext(formulaDetailsMasterSid, endDate,
			orderByComparator);
	}

	/**
	* Removes all the formula details masters where endDate = &#63; from the database.
	*
	* @param endDate the end date
	*/
	public static void removeByEndDate(Date endDate) {
		getPersistence().removeByEndDate(endDate);
	}

	/**
	* Returns the number of formula details masters where endDate = &#63;.
	*
	* @param endDate the end date
	* @return the number of matching formula details masters
	*/
	public static int countByEndDate(Date endDate) {
		return getPersistence().countByEndDate(endDate);
	}

	/**
	* Returns all the formula details masters where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @return the matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByFormulaDetailsUnique(
		java.lang.String formulaId) {
		return getPersistence().findByFormulaDetailsUnique(formulaId);
	}

	/**
	* Returns a range of all the formula details masters where formulaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaId the formula ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByFormulaDetailsUnique(
		java.lang.String formulaId, int start, int end) {
		return getPersistence().findByFormulaDetailsUnique(formulaId, start, end);
	}

	/**
	* Returns an ordered range of all the formula details masters where formulaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaId the formula ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByFormulaDetailsUnique(
		java.lang.String formulaId, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .findByFormulaDetailsUnique(formulaId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the formula details masters where formulaId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param formulaId the formula ID
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching formula details masters
	*/
	public static List<FormulaDetailsMaster> findByFormulaDetailsUnique(
		java.lang.String formulaId, int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByFormulaDetailsUnique(formulaId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByFormulaDetailsUnique_First(
		java.lang.String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByFormulaDetailsUnique_First(formulaId,
			orderByComparator);
	}

	/**
	* Returns the first formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByFormulaDetailsUnique_First(
		java.lang.String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByFormulaDetailsUnique_First(formulaId,
			orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master
	* @throws NoSuchFormulaDetailsMasterException if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster findByFormulaDetailsUnique_Last(
		java.lang.String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByFormulaDetailsUnique_Last(formulaId, orderByComparator);
	}

	/**
	* Returns the last formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching formula details master, or <code>null</code> if a matching formula details master could not be found
	*/
	public static FormulaDetailsMaster fetchByFormulaDetailsUnique_Last(
		java.lang.String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByFormulaDetailsUnique_Last(formulaId,
			orderByComparator);
	}

	/**
	* Returns the formula details masters before and after the current formula details master in the ordered set where formulaId = &#63;.
	*
	* @param formulaDetailsMasterSid the primary key of the current formula details master
	* @param formulaId the formula ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public static FormulaDetailsMaster[] findByFormulaDetailsUnique_PrevAndNext(
		int formulaDetailsMasterSid, java.lang.String formulaId,
		OrderByComparator<FormulaDetailsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence()
				   .findByFormulaDetailsUnique_PrevAndNext(formulaDetailsMasterSid,
			formulaId, orderByComparator);
	}

	/**
	* Removes all the formula details masters where formulaId = &#63; from the database.
	*
	* @param formulaId the formula ID
	*/
	public static void removeByFormulaDetailsUnique(java.lang.String formulaId) {
		getPersistence().removeByFormulaDetailsUnique(formulaId);
	}

	/**
	* Returns the number of formula details masters where formulaId = &#63;.
	*
	* @param formulaId the formula ID
	* @return the number of matching formula details masters
	*/
	public static int countByFormulaDetailsUnique(java.lang.String formulaId) {
		return getPersistence().countByFormulaDetailsUnique(formulaId);
	}

	/**
	* Caches the formula details master in the entity cache if it is enabled.
	*
	* @param formulaDetailsMaster the formula details master
	*/
	public static void cacheResult(FormulaDetailsMaster formulaDetailsMaster) {
		getPersistence().cacheResult(formulaDetailsMaster);
	}

	/**
	* Caches the formula details masters in the entity cache if it is enabled.
	*
	* @param formulaDetailsMasters the formula details masters
	*/
	public static void cacheResult(
		List<FormulaDetailsMaster> formulaDetailsMasters) {
		getPersistence().cacheResult(formulaDetailsMasters);
	}

	/**
	* Creates a new formula details master with the primary key. Does not add the formula details master to the database.
	*
	* @param formulaDetailsMasterSid the primary key for the new formula details master
	* @return the new formula details master
	*/
	public static FormulaDetailsMaster create(int formulaDetailsMasterSid) {
		return getPersistence().create(formulaDetailsMasterSid);
	}

	/**
	* Removes the formula details master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param formulaDetailsMasterSid the primary key of the formula details master
	* @return the formula details master that was removed
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public static FormulaDetailsMaster remove(int formulaDetailsMasterSid)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence().remove(formulaDetailsMasterSid);
	}

	public static FormulaDetailsMaster updateImpl(
		FormulaDetailsMaster formulaDetailsMaster) {
		return getPersistence().updateImpl(formulaDetailsMaster);
	}

	/**
	* Returns the formula details master with the primary key or throws a {@link NoSuchFormulaDetailsMasterException} if it could not be found.
	*
	* @param formulaDetailsMasterSid the primary key of the formula details master
	* @return the formula details master
	* @throws NoSuchFormulaDetailsMasterException if a formula details master with the primary key could not be found
	*/
	public static FormulaDetailsMaster findByPrimaryKey(
		int formulaDetailsMasterSid)
		throws com.stpl.app.exception.NoSuchFormulaDetailsMasterException {
		return getPersistence().findByPrimaryKey(formulaDetailsMasterSid);
	}

	/**
	* Returns the formula details master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param formulaDetailsMasterSid the primary key of the formula details master
	* @return the formula details master, or <code>null</code> if a formula details master with the primary key could not be found
	*/
	public static FormulaDetailsMaster fetchByPrimaryKey(
		int formulaDetailsMasterSid) {
		return getPersistence().fetchByPrimaryKey(formulaDetailsMasterSid);
	}

	public static java.util.Map<java.io.Serializable, FormulaDetailsMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the formula details masters.
	*
	* @return the formula details masters
	*/
	public static List<FormulaDetailsMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the formula details masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @return the range of formula details masters
	*/
	public static List<FormulaDetailsMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the formula details masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of formula details masters
	*/
	public static List<FormulaDetailsMaster> findAll(int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the formula details masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FormulaDetailsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of formula details masters
	* @param end the upper bound of the range of formula details masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of formula details masters
	*/
	public static List<FormulaDetailsMaster> findAll(int start, int end,
		OrderByComparator<FormulaDetailsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the formula details masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of formula details masters.
	*
	* @return the number of formula details masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static FormulaDetailsMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FormulaDetailsMasterPersistence, FormulaDetailsMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(FormulaDetailsMasterPersistence.class);
}