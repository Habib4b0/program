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

import com.stpl.app.model.ActualsMaster;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the actuals master service. This utility wraps {@link com.stpl.app.service.persistence.impl.ActualsMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ActualsMasterPersistence
 * @see com.stpl.app.service.persistence.impl.ActualsMasterPersistenceImpl
 * @generated
 */
@ProviderType
public class ActualsMasterUtil {
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
	public static void clearCache(ActualsMaster actualsMaster) {
		getPersistence().clearCache(actualsMaster);
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
	public static List<ActualsMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ActualsMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ActualsMaster> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ActualsMaster update(ActualsMaster actualsMaster) {
		return getPersistence().update(actualsMaster);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ActualsMaster update(ActualsMaster actualsMaster,
		ServiceContext serviceContext) {
		return getPersistence().update(actualsMaster, serviceContext);
	}

	/**
	* Returns all the actuals masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the matching actuals masters
	*/
	public static List<ActualsMaster> findByAccountId(
		java.lang.String accountId) {
		return getPersistence().findByAccountId(accountId);
	}

	/**
	* Returns a range of all the actuals masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public static List<ActualsMaster> findByAccountId(
		java.lang.String accountId, int start, int end) {
		return getPersistence().findByAccountId(accountId, start, end);
	}

	/**
	* Returns an ordered range of all the actuals masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .findByAccountId(accountId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the actuals masters where accountId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountId the account ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByAccountId(
		java.lang.String accountId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountId(accountId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first actuals master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByAccountId_First(
		java.lang.String accountId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByAccountId_First(accountId, orderByComparator);
	}

	/**
	* Returns the first actuals master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByAccountId_First(
		java.lang.String accountId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByAccountId_First(accountId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByAccountId_Last(
		java.lang.String accountId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByAccountId_Last(accountId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where accountId = &#63;.
	*
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByAccountId_Last(
		java.lang.String accountId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByAccountId_Last(accountId, orderByComparator);
	}

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where accountId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param accountId the account ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster[] findByAccountId_PrevAndNext(
		int actualsMasterSid, java.lang.String accountId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByAccountId_PrevAndNext(actualsMasterSid, accountId,
			orderByComparator);
	}

	/**
	* Removes all the actuals masters where accountId = &#63; from the database.
	*
	* @param accountId the account ID
	*/
	public static void removeByAccountId(java.lang.String accountId) {
		getPersistence().removeByAccountId(accountId);
	}

	/**
	* Returns the number of actuals masters where accountId = &#63;.
	*
	* @param accountId the account ID
	* @return the number of matching actuals masters
	*/
	public static int countByAccountId(java.lang.String accountId) {
		return getPersistence().countByAccountId(accountId);
	}

	/**
	* Returns all the actuals masters where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @return the matching actuals masters
	*/
	public static List<ActualsMaster> findByActualId(java.lang.String actualId) {
		return getPersistence().findByActualId(actualId);
	}

	/**
	* Returns a range of all the actuals masters where actualId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actualId the actual ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public static List<ActualsMaster> findByActualId(
		java.lang.String actualId, int start, int end) {
		return getPersistence().findByActualId(actualId, start, end);
	}

	/**
	* Returns an ordered range of all the actuals masters where actualId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actualId the actual ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByActualId(
		java.lang.String actualId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .findByActualId(actualId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the actuals masters where actualId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actualId the actual ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByActualId(
		java.lang.String actualId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByActualId(actualId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByActualId_First(
		java.lang.String actualId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence().findByActualId_First(actualId, orderByComparator);
	}

	/**
	* Returns the first actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByActualId_First(
		java.lang.String actualId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByActualId_First(actualId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByActualId_Last(java.lang.String actualId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence().findByActualId_Last(actualId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByActualId_Last(
		java.lang.String actualId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence().fetchByActualId_Last(actualId, orderByComparator);
	}

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster[] findByActualId_PrevAndNext(
		int actualsMasterSid, java.lang.String actualId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByActualId_PrevAndNext(actualsMasterSid, actualId,
			orderByComparator);
	}

	/**
	* Removes all the actuals masters where actualId = &#63; from the database.
	*
	* @param actualId the actual ID
	*/
	public static void removeByActualId(java.lang.String actualId) {
		getPersistence().removeByActualId(actualId);
	}

	/**
	* Returns the number of actuals masters where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @return the number of matching actuals masters
	*/
	public static int countByActualId(java.lang.String actualId) {
		return getPersistence().countByActualId(actualId);
	}

	/**
	* Returns all the actuals masters where divisionId = &#63;.
	*
	* @param divisionId the division ID
	* @return the matching actuals masters
	*/
	public static List<ActualsMaster> findByDivisionId(
		java.lang.String divisionId) {
		return getPersistence().findByDivisionId(divisionId);
	}

	/**
	* Returns a range of all the actuals masters where divisionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param divisionId the division ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public static List<ActualsMaster> findByDivisionId(
		java.lang.String divisionId, int start, int end) {
		return getPersistence().findByDivisionId(divisionId, start, end);
	}

	/**
	* Returns an ordered range of all the actuals masters where divisionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param divisionId the division ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByDivisionId(
		java.lang.String divisionId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .findByDivisionId(divisionId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the actuals masters where divisionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param divisionId the division ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByDivisionId(
		java.lang.String divisionId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDivisionId(divisionId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first actuals master in the ordered set where divisionId = &#63;.
	*
	* @param divisionId the division ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByDivisionId_First(
		java.lang.String divisionId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByDivisionId_First(divisionId, orderByComparator);
	}

	/**
	* Returns the first actuals master in the ordered set where divisionId = &#63;.
	*
	* @param divisionId the division ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByDivisionId_First(
		java.lang.String divisionId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByDivisionId_First(divisionId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where divisionId = &#63;.
	*
	* @param divisionId the division ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByDivisionId_Last(
		java.lang.String divisionId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByDivisionId_Last(divisionId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where divisionId = &#63;.
	*
	* @param divisionId the division ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByDivisionId_Last(
		java.lang.String divisionId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByDivisionId_Last(divisionId, orderByComparator);
	}

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where divisionId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param divisionId the division ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster[] findByDivisionId_PrevAndNext(
		int actualsMasterSid, java.lang.String divisionId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByDivisionId_PrevAndNext(actualsMasterSid, divisionId,
			orderByComparator);
	}

	/**
	* Removes all the actuals masters where divisionId = &#63; from the database.
	*
	* @param divisionId the division ID
	*/
	public static void removeByDivisionId(java.lang.String divisionId) {
		getPersistence().removeByDivisionId(divisionId);
	}

	/**
	* Returns the number of actuals masters where divisionId = &#63;.
	*
	* @param divisionId the division ID
	* @return the number of matching actuals masters
	*/
	public static int countByDivisionId(java.lang.String divisionId) {
		return getPersistence().countByDivisionId(divisionId);
	}

	/**
	* Returns all the actuals masters where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @return the matching actuals masters
	*/
	public static List<ActualsMaster> findByContractId(
		java.lang.String contractId) {
		return getPersistence().findByContractId(contractId);
	}

	/**
	* Returns a range of all the actuals masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public static List<ActualsMaster> findByContractId(
		java.lang.String contractId, int start, int end) {
		return getPersistence().findByContractId(contractId, start, end);
	}

	/**
	* Returns an ordered range of all the actuals masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByContractId(
		java.lang.String contractId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .findByContractId(contractId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the actuals masters where contractId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractId the contract ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByContractId(
		java.lang.String contractId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByContractId(contractId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first actuals master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByContractId_First(
		java.lang.String contractId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByContractId_First(contractId, orderByComparator);
	}

	/**
	* Returns the first actuals master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByContractId_First(
		java.lang.String contractId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByContractId_First(contractId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByContractId_Last(
		java.lang.String contractId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByContractId_Last(contractId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByContractId_Last(
		java.lang.String contractId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByContractId_Last(contractId, orderByComparator);
	}

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where contractId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param contractId the contract ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster[] findByContractId_PrevAndNext(
		int actualsMasterSid, java.lang.String contractId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByContractId_PrevAndNext(actualsMasterSid, contractId,
			orderByComparator);
	}

	/**
	* Removes all the actuals masters where contractId = &#63; from the database.
	*
	* @param contractId the contract ID
	*/
	public static void removeByContractId(java.lang.String contractId) {
		getPersistence().removeByContractId(contractId);
	}

	/**
	* Returns the number of actuals masters where contractId = &#63;.
	*
	* @param contractId the contract ID
	* @return the number of matching actuals masters
	*/
	public static int countByContractId(java.lang.String contractId) {
		return getPersistence().countByContractId(contractId);
	}

	/**
	* Returns all the actuals masters where provisionId = &#63;.
	*
	* @param provisionId the provision ID
	* @return the matching actuals masters
	*/
	public static List<ActualsMaster> findByProvisionId(
		java.lang.String provisionId) {
		return getPersistence().findByProvisionId(provisionId);
	}

	/**
	* Returns a range of all the actuals masters where provisionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param provisionId the provision ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public static List<ActualsMaster> findByProvisionId(
		java.lang.String provisionId, int start, int end) {
		return getPersistence().findByProvisionId(provisionId, start, end);
	}

	/**
	* Returns an ordered range of all the actuals masters where provisionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param provisionId the provision ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByProvisionId(
		java.lang.String provisionId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .findByProvisionId(provisionId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the actuals masters where provisionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param provisionId the provision ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByProvisionId(
		java.lang.String provisionId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByProvisionId(provisionId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first actuals master in the ordered set where provisionId = &#63;.
	*
	* @param provisionId the provision ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByProvisionId_First(
		java.lang.String provisionId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByProvisionId_First(provisionId, orderByComparator);
	}

	/**
	* Returns the first actuals master in the ordered set where provisionId = &#63;.
	*
	* @param provisionId the provision ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByProvisionId_First(
		java.lang.String provisionId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByProvisionId_First(provisionId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where provisionId = &#63;.
	*
	* @param provisionId the provision ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByProvisionId_Last(
		java.lang.String provisionId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByProvisionId_Last(provisionId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where provisionId = &#63;.
	*
	* @param provisionId the provision ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByProvisionId_Last(
		java.lang.String provisionId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByProvisionId_Last(provisionId, orderByComparator);
	}

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where provisionId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param provisionId the provision ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster[] findByProvisionId_PrevAndNext(
		int actualsMasterSid, java.lang.String provisionId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByProvisionId_PrevAndNext(actualsMasterSid,
			provisionId, orderByComparator);
	}

	/**
	* Removes all the actuals masters where provisionId = &#63; from the database.
	*
	* @param provisionId the provision ID
	*/
	public static void removeByProvisionId(java.lang.String provisionId) {
		getPersistence().removeByProvisionId(provisionId);
	}

	/**
	* Returns the number of actuals masters where provisionId = &#63;.
	*
	* @param provisionId the provision ID
	* @return the number of matching actuals masters
	*/
	public static int countByProvisionId(java.lang.String provisionId) {
		return getPersistence().countByProvisionId(provisionId);
	}

	/**
	* Returns all the actuals masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the matching actuals masters
	*/
	public static List<ActualsMaster> findByItemId(java.lang.String itemId) {
		return getPersistence().findByItemId(itemId);
	}

	/**
	* Returns a range of all the actuals masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public static List<ActualsMaster> findByItemId(java.lang.String itemId,
		int start, int end) {
		return getPersistence().findByItemId(itemId, start, end);
	}

	/**
	* Returns an ordered range of all the actuals masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByItemId(java.lang.String itemId,
		int start, int end, OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .findByItemId(itemId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the actuals masters where itemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemId the item ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByItemId(java.lang.String itemId,
		int start, int end, OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemId(itemId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first actuals master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByItemId_First(java.lang.String itemId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence().findByItemId_First(itemId, orderByComparator);
	}

	/**
	* Returns the first actuals master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByItemId_First(java.lang.String itemId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence().fetchByItemId_First(itemId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByItemId_Last(java.lang.String itemId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence().findByItemId_Last(itemId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where itemId = &#63;.
	*
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByItemId_Last(java.lang.String itemId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence().fetchByItemId_Last(itemId, orderByComparator);
	}

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where itemId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param itemId the item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster[] findByItemId_PrevAndNext(
		int actualsMasterSid, java.lang.String itemId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByItemId_PrevAndNext(actualsMasterSid, itemId,
			orderByComparator);
	}

	/**
	* Removes all the actuals masters where itemId = &#63; from the database.
	*
	* @param itemId the item ID
	*/
	public static void removeByItemId(java.lang.String itemId) {
		getPersistence().removeByItemId(itemId);
	}

	/**
	* Returns the number of actuals masters where itemId = &#63;.
	*
	* @param itemId the item ID
	* @return the number of matching actuals masters
	*/
	public static int countByItemId(java.lang.String itemId) {
		return getPersistence().countByItemId(itemId);
	}

	/**
	* Returns all the actuals masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the matching actuals masters
	*/
	public static List<ActualsMaster> findByItemNo(java.lang.String itemNo) {
		return getPersistence().findByItemNo(itemNo);
	}

	/**
	* Returns a range of all the actuals masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public static List<ActualsMaster> findByItemNo(java.lang.String itemNo,
		int start, int end) {
		return getPersistence().findByItemNo(itemNo, start, end);
	}

	/**
	* Returns an ordered range of all the actuals masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByItemNo(java.lang.String itemNo,
		int start, int end, OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .findByItemNo(itemNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the actuals masters where itemNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param itemNo the item no
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByItemNo(java.lang.String itemNo,
		int start, int end, OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemNo(itemNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first actuals master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByItemNo_First(java.lang.String itemNo,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence().findByItemNo_First(itemNo, orderByComparator);
	}

	/**
	* Returns the first actuals master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByItemNo_First(java.lang.String itemNo,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence().fetchByItemNo_First(itemNo, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByItemNo_Last(java.lang.String itemNo,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence().findByItemNo_Last(itemNo, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByItemNo_Last(java.lang.String itemNo,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence().fetchByItemNo_Last(itemNo, orderByComparator);
	}

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where itemNo = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param itemNo the item no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster[] findByItemNo_PrevAndNext(
		int actualsMasterSid, java.lang.String itemNo,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByItemNo_PrevAndNext(actualsMasterSid, itemNo,
			orderByComparator);
	}

	/**
	* Removes all the actuals masters where itemNo = &#63; from the database.
	*
	* @param itemNo the item no
	*/
	public static void removeByItemNo(java.lang.String itemNo) {
		getPersistence().removeByItemNo(itemNo);
	}

	/**
	* Returns the number of actuals masters where itemNo = &#63;.
	*
	* @param itemNo the item no
	* @return the number of matching actuals masters
	*/
	public static int countByItemNo(java.lang.String itemNo) {
		return getPersistence().countByItemNo(itemNo);
	}

	/**
	* Returns all the actuals masters where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @return the matching actuals masters
	*/
	public static List<ActualsMaster> findByAccountNo(
		java.lang.String accountNo) {
		return getPersistence().findByAccountNo(accountNo);
	}

	/**
	* Returns a range of all the actuals masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public static List<ActualsMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end) {
		return getPersistence().findByAccountNo(accountNo, start, end);
	}

	/**
	* Returns an ordered range of all the actuals masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .findByAccountNo(accountNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the actuals masters where accountNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountNo the account no
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByAccountNo(
		java.lang.String accountNo, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountNo(accountNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first actuals master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByAccountNo_First(
		java.lang.String accountNo,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByAccountNo_First(accountNo, orderByComparator);
	}

	/**
	* Returns the first actuals master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByAccountNo_First(
		java.lang.String accountNo,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByAccountNo_First(accountNo, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByAccountNo_Last(
		java.lang.String accountNo,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByAccountNo_Last(accountNo, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByAccountNo_Last(
		java.lang.String accountNo,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByAccountNo_Last(accountNo, orderByComparator);
	}

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where accountNo = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param accountNo the account no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster[] findByAccountNo_PrevAndNext(
		int actualsMasterSid, java.lang.String accountNo,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByAccountNo_PrevAndNext(actualsMasterSid, accountNo,
			orderByComparator);
	}

	/**
	* Removes all the actuals masters where accountNo = &#63; from the database.
	*
	* @param accountNo the account no
	*/
	public static void removeByAccountNo(java.lang.String accountNo) {
		getPersistence().removeByAccountNo(accountNo);
	}

	/**
	* Returns the number of actuals masters where accountNo = &#63;.
	*
	* @param accountNo the account no
	* @return the number of matching actuals masters
	*/
	public static int countByAccountNo(java.lang.String accountNo) {
		return getPersistence().countByAccountNo(accountNo);
	}

	/**
	* Returns all the actuals masters where marketId = &#63;.
	*
	* @param marketId the market ID
	* @return the matching actuals masters
	*/
	public static List<ActualsMaster> findByMarketId(java.lang.String marketId) {
		return getPersistence().findByMarketId(marketId);
	}

	/**
	* Returns a range of all the actuals masters where marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param marketId the market ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public static List<ActualsMaster> findByMarketId(
		java.lang.String marketId, int start, int end) {
		return getPersistence().findByMarketId(marketId, start, end);
	}

	/**
	* Returns an ordered range of all the actuals masters where marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param marketId the market ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByMarketId(
		java.lang.String marketId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .findByMarketId(marketId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the actuals masters where marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param marketId the market ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByMarketId(
		java.lang.String marketId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMarketId(marketId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first actuals master in the ordered set where marketId = &#63;.
	*
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByMarketId_First(
		java.lang.String marketId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence().findByMarketId_First(marketId, orderByComparator);
	}

	/**
	* Returns the first actuals master in the ordered set where marketId = &#63;.
	*
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByMarketId_First(
		java.lang.String marketId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByMarketId_First(marketId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where marketId = &#63;.
	*
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByMarketId_Last(java.lang.String marketId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence().findByMarketId_Last(marketId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where marketId = &#63;.
	*
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByMarketId_Last(
		java.lang.String marketId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence().fetchByMarketId_Last(marketId, orderByComparator);
	}

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where marketId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster[] findByMarketId_PrevAndNext(
		int actualsMasterSid, java.lang.String marketId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByMarketId_PrevAndNext(actualsMasterSid, marketId,
			orderByComparator);
	}

	/**
	* Removes all the actuals masters where marketId = &#63; from the database.
	*
	* @param marketId the market ID
	*/
	public static void removeByMarketId(java.lang.String marketId) {
		getPersistence().removeByMarketId(marketId);
	}

	/**
	* Returns the number of actuals masters where marketId = &#63;.
	*
	* @param marketId the market ID
	* @return the number of matching actuals masters
	*/
	public static int countByMarketId(java.lang.String marketId) {
		return getPersistence().countByMarketId(marketId);
	}

	/**
	* Returns all the actuals masters where brandId = &#63;.
	*
	* @param brandId the brand ID
	* @return the matching actuals masters
	*/
	public static List<ActualsMaster> findByBrandId(java.lang.String brandId) {
		return getPersistence().findByBrandId(brandId);
	}

	/**
	* Returns a range of all the actuals masters where brandId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param brandId the brand ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public static List<ActualsMaster> findByBrandId(java.lang.String brandId,
		int start, int end) {
		return getPersistence().findByBrandId(brandId, start, end);
	}

	/**
	* Returns an ordered range of all the actuals masters where brandId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param brandId the brand ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByBrandId(java.lang.String brandId,
		int start, int end, OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .findByBrandId(brandId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the actuals masters where brandId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param brandId the brand ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByBrandId(java.lang.String brandId,
		int start, int end, OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByBrandId(brandId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first actuals master in the ordered set where brandId = &#63;.
	*
	* @param brandId the brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByBrandId_First(java.lang.String brandId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence().findByBrandId_First(brandId, orderByComparator);
	}

	/**
	* Returns the first actuals master in the ordered set where brandId = &#63;.
	*
	* @param brandId the brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByBrandId_First(java.lang.String brandId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence().fetchByBrandId_First(brandId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where brandId = &#63;.
	*
	* @param brandId the brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByBrandId_Last(java.lang.String brandId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence().findByBrandId_Last(brandId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where brandId = &#63;.
	*
	* @param brandId the brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByBrandId_Last(java.lang.String brandId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence().fetchByBrandId_Last(brandId, orderByComparator);
	}

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where brandId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param brandId the brand ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster[] findByBrandId_PrevAndNext(
		int actualsMasterSid, java.lang.String brandId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByBrandId_PrevAndNext(actualsMasterSid, brandId,
			orderByComparator);
	}

	/**
	* Removes all the actuals masters where brandId = &#63; from the database.
	*
	* @param brandId the brand ID
	*/
	public static void removeByBrandId(java.lang.String brandId) {
		getPersistence().removeByBrandId(brandId);
	}

	/**
	* Returns the number of actuals masters where brandId = &#63;.
	*
	* @param brandId the brand ID
	* @return the number of matching actuals masters
	*/
	public static int countByBrandId(java.lang.String brandId) {
		return getPersistence().countByBrandId(brandId);
	}

	/**
	* Returns all the actuals masters where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @return the matching actuals masters
	*/
	public static List<ActualsMaster> findByActualsUnique(
		java.lang.String actualId) {
		return getPersistence().findByActualsUnique(actualId);
	}

	/**
	* Returns a range of all the actuals masters where actualId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actualId the actual ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of matching actuals masters
	*/
	public static List<ActualsMaster> findByActualsUnique(
		java.lang.String actualId, int start, int end) {
		return getPersistence().findByActualsUnique(actualId, start, end);
	}

	/**
	* Returns an ordered range of all the actuals masters where actualId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actualId the actual ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByActualsUnique(
		java.lang.String actualId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .findByActualsUnique(actualId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the actuals masters where actualId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param actualId the actual ID
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching actuals masters
	*/
	public static List<ActualsMaster> findByActualsUnique(
		java.lang.String actualId, int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByActualsUnique(actualId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByActualsUnique_First(
		java.lang.String actualId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByActualsUnique_First(actualId, orderByComparator);
	}

	/**
	* Returns the first actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByActualsUnique_First(
		java.lang.String actualId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByActualsUnique_First(actualId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master
	* @throws NoSuchActualsMasterException if a matching actuals master could not be found
	*/
	public static ActualsMaster findByActualsUnique_Last(
		java.lang.String actualId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByActualsUnique_Last(actualId, orderByComparator);
	}

	/**
	* Returns the last actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching actuals master, or <code>null</code> if a matching actuals master could not be found
	*/
	public static ActualsMaster fetchByActualsUnique_Last(
		java.lang.String actualId,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence()
				   .fetchByActualsUnique_Last(actualId, orderByComparator);
	}

	/**
	* Returns the actuals masters before and after the current actuals master in the ordered set where actualId = &#63;.
	*
	* @param actualsMasterSid the primary key of the current actuals master
	* @param actualId the actual ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster[] findByActualsUnique_PrevAndNext(
		int actualsMasterSid, java.lang.String actualId,
		OrderByComparator<ActualsMaster> orderByComparator)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence()
				   .findByActualsUnique_PrevAndNext(actualsMasterSid, actualId,
			orderByComparator);
	}

	/**
	* Removes all the actuals masters where actualId = &#63; from the database.
	*
	* @param actualId the actual ID
	*/
	public static void removeByActualsUnique(java.lang.String actualId) {
		getPersistence().removeByActualsUnique(actualId);
	}

	/**
	* Returns the number of actuals masters where actualId = &#63;.
	*
	* @param actualId the actual ID
	* @return the number of matching actuals masters
	*/
	public static int countByActualsUnique(java.lang.String actualId) {
		return getPersistence().countByActualsUnique(actualId);
	}

	/**
	* Caches the actuals master in the entity cache if it is enabled.
	*
	* @param actualsMaster the actuals master
	*/
	public static void cacheResult(ActualsMaster actualsMaster) {
		getPersistence().cacheResult(actualsMaster);
	}

	/**
	* Caches the actuals masters in the entity cache if it is enabled.
	*
	* @param actualsMasters the actuals masters
	*/
	public static void cacheResult(List<ActualsMaster> actualsMasters) {
		getPersistence().cacheResult(actualsMasters);
	}

	/**
	* Creates a new actuals master with the primary key. Does not add the actuals master to the database.
	*
	* @param actualsMasterSid the primary key for the new actuals master
	* @return the new actuals master
	*/
	public static ActualsMaster create(int actualsMasterSid) {
		return getPersistence().create(actualsMasterSid);
	}

	/**
	* Removes the actuals master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param actualsMasterSid the primary key of the actuals master
	* @return the actuals master that was removed
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster remove(int actualsMasterSid)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence().remove(actualsMasterSid);
	}

	public static ActualsMaster updateImpl(ActualsMaster actualsMaster) {
		return getPersistence().updateImpl(actualsMaster);
	}

	/**
	* Returns the actuals master with the primary key or throws a {@link NoSuchActualsMasterException} if it could not be found.
	*
	* @param actualsMasterSid the primary key of the actuals master
	* @return the actuals master
	* @throws NoSuchActualsMasterException if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster findByPrimaryKey(int actualsMasterSid)
		throws com.stpl.app.exception.NoSuchActualsMasterException {
		return getPersistence().findByPrimaryKey(actualsMasterSid);
	}

	/**
	* Returns the actuals master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param actualsMasterSid the primary key of the actuals master
	* @return the actuals master, or <code>null</code> if a actuals master with the primary key could not be found
	*/
	public static ActualsMaster fetchByPrimaryKey(int actualsMasterSid) {
		return getPersistence().fetchByPrimaryKey(actualsMasterSid);
	}

	public static java.util.Map<java.io.Serializable, ActualsMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the actuals masters.
	*
	* @return the actuals masters
	*/
	public static List<ActualsMaster> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the actuals masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @return the range of actuals masters
	*/
	public static List<ActualsMaster> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the actuals masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of actuals masters
	*/
	public static List<ActualsMaster> findAll(int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the actuals masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of actuals masters
	* @param end the upper bound of the range of actuals masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of actuals masters
	*/
	public static List<ActualsMaster> findAll(int start, int end,
		OrderByComparator<ActualsMaster> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the actuals masters from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of actuals masters.
	*
	* @return the number of actuals masters
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ActualsMasterPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ActualsMasterPersistence, ActualsMasterPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ActualsMasterPersistence.class);
}