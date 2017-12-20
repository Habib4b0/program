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

import com.stpl.app.model.ImtdIfpDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the imtd ifp details service. This utility wraps {@link com.stpl.app.service.persistence.impl.ImtdIfpDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdIfpDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.ImtdIfpDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class ImtdIfpDetailsUtil {
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
	public static void clearCache(ImtdIfpDetails imtdIfpDetails) {
		getPersistence().clearCache(imtdIfpDetails);
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
	public static List<ImtdIfpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ImtdIfpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ImtdIfpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ImtdIfpDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ImtdIfpDetails update(ImtdIfpDetails imtdIfpDetails) {
		return getPersistence().update(imtdIfpDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ImtdIfpDetails update(ImtdIfpDetails imtdIfpDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(imtdIfpDetails, serviceContext);
	}

	/**
	* Returns all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @return the matching imtd ifp detailses
	*/
	public static List<ImtdIfpDetails> findByTempIfpSearch(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate) {
		return getPersistence()
				   .findByTempIfpSearch(usersSid, sessionId, imtdCreateddate);
	}

	/**
	* Returns a range of all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @return the range of matching imtd ifp detailses
	*/
	public static List<ImtdIfpDetails> findByTempIfpSearch(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate, int start, int end) {
		return getPersistence()
				   .findByTempIfpSearch(usersSid, sessionId, imtdCreateddate,
			start, end);
	}

	/**
	* Returns an ordered range of all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching imtd ifp detailses
	*/
	public static List<ImtdIfpDetails> findByTempIfpSearch(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate, int start, int end,
		OrderByComparator<ImtdIfpDetails> orderByComparator) {
		return getPersistence()
				   .findByTempIfpSearch(usersSid, sessionId, imtdCreateddate,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching imtd ifp detailses
	*/
	public static List<ImtdIfpDetails> findByTempIfpSearch(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate, int start, int end,
		OrderByComparator<ImtdIfpDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTempIfpSearch(usersSid, sessionId, imtdCreateddate,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching imtd ifp details
	* @throws NoSuchImtdIfpDetailsException if a matching imtd ifp details could not be found
	*/
	public static ImtdIfpDetails findByTempIfpSearch_First(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate,
		OrderByComparator<ImtdIfpDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchImtdIfpDetailsException {
		return getPersistence()
				   .findByTempIfpSearch_First(usersSid, sessionId,
			imtdCreateddate, orderByComparator);
	}

	/**
	* Returns the first imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching imtd ifp details, or <code>null</code> if a matching imtd ifp details could not be found
	*/
	public static ImtdIfpDetails fetchByTempIfpSearch_First(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate,
		OrderByComparator<ImtdIfpDetails> orderByComparator) {
		return getPersistence()
				   .fetchByTempIfpSearch_First(usersSid, sessionId,
			imtdCreateddate, orderByComparator);
	}

	/**
	* Returns the last imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching imtd ifp details
	* @throws NoSuchImtdIfpDetailsException if a matching imtd ifp details could not be found
	*/
	public static ImtdIfpDetails findByTempIfpSearch_Last(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate,
		OrderByComparator<ImtdIfpDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchImtdIfpDetailsException {
		return getPersistence()
				   .findByTempIfpSearch_Last(usersSid, sessionId,
			imtdCreateddate, orderByComparator);
	}

	/**
	* Returns the last imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching imtd ifp details, or <code>null</code> if a matching imtd ifp details could not be found
	*/
	public static ImtdIfpDetails fetchByTempIfpSearch_Last(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate,
		OrderByComparator<ImtdIfpDetails> orderByComparator) {
		return getPersistence()
				   .fetchByTempIfpSearch_Last(usersSid, sessionId,
			imtdCreateddate, orderByComparator);
	}

	/**
	* Returns the imtd ifp detailses before and after the current imtd ifp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param imtdIfpDetailsSid the primary key of the current imtd ifp details
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next imtd ifp details
	* @throws NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
	*/
	public static ImtdIfpDetails[] findByTempIfpSearch_PrevAndNext(
		int imtdIfpDetailsSid, int usersSid, java.lang.String sessionId,
		Date imtdCreateddate,
		OrderByComparator<ImtdIfpDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchImtdIfpDetailsException {
		return getPersistence()
				   .findByTempIfpSearch_PrevAndNext(imtdIfpDetailsSid,
			usersSid, sessionId, imtdCreateddate, orderByComparator);
	}

	/**
	* Removes all the imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63; from the database.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	*/
	public static void removeByTempIfpSearch(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate) {
		getPersistence()
			.removeByTempIfpSearch(usersSid, sessionId, imtdCreateddate);
	}

	/**
	* Returns the number of imtd ifp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreateddate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreateddate the imtd createddate
	* @return the number of matching imtd ifp detailses
	*/
	public static int countByTempIfpSearch(int usersSid,
		java.lang.String sessionId, Date imtdCreateddate) {
		return getPersistence()
				   .countByTempIfpSearch(usersSid, sessionId, imtdCreateddate);
	}

	/**
	* Caches the imtd ifp details in the entity cache if it is enabled.
	*
	* @param imtdIfpDetails the imtd ifp details
	*/
	public static void cacheResult(ImtdIfpDetails imtdIfpDetails) {
		getPersistence().cacheResult(imtdIfpDetails);
	}

	/**
	* Caches the imtd ifp detailses in the entity cache if it is enabled.
	*
	* @param imtdIfpDetailses the imtd ifp detailses
	*/
	public static void cacheResult(List<ImtdIfpDetails> imtdIfpDetailses) {
		getPersistence().cacheResult(imtdIfpDetailses);
	}

	/**
	* Creates a new imtd ifp details with the primary key. Does not add the imtd ifp details to the database.
	*
	* @param imtdIfpDetailsSid the primary key for the new imtd ifp details
	* @return the new imtd ifp details
	*/
	public static ImtdIfpDetails create(int imtdIfpDetailsSid) {
		return getPersistence().create(imtdIfpDetailsSid);
	}

	/**
	* Removes the imtd ifp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdIfpDetailsSid the primary key of the imtd ifp details
	* @return the imtd ifp details that was removed
	* @throws NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
	*/
	public static ImtdIfpDetails remove(int imtdIfpDetailsSid)
		throws com.stpl.app.exception.NoSuchImtdIfpDetailsException {
		return getPersistence().remove(imtdIfpDetailsSid);
	}

	public static ImtdIfpDetails updateImpl(ImtdIfpDetails imtdIfpDetails) {
		return getPersistence().updateImpl(imtdIfpDetails);
	}

	/**
	* Returns the imtd ifp details with the primary key or throws a {@link NoSuchImtdIfpDetailsException} if it could not be found.
	*
	* @param imtdIfpDetailsSid the primary key of the imtd ifp details
	* @return the imtd ifp details
	* @throws NoSuchImtdIfpDetailsException if a imtd ifp details with the primary key could not be found
	*/
	public static ImtdIfpDetails findByPrimaryKey(int imtdIfpDetailsSid)
		throws com.stpl.app.exception.NoSuchImtdIfpDetailsException {
		return getPersistence().findByPrimaryKey(imtdIfpDetailsSid);
	}

	/**
	* Returns the imtd ifp details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdIfpDetailsSid the primary key of the imtd ifp details
	* @return the imtd ifp details, or <code>null</code> if a imtd ifp details with the primary key could not be found
	*/
	public static ImtdIfpDetails fetchByPrimaryKey(int imtdIfpDetailsSid) {
		return getPersistence().fetchByPrimaryKey(imtdIfpDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, ImtdIfpDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the imtd ifp detailses.
	*
	* @return the imtd ifp detailses
	*/
	public static List<ImtdIfpDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the imtd ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @return the range of imtd ifp detailses
	*/
	public static List<ImtdIfpDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the imtd ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd ifp detailses
	*/
	public static List<ImtdIfpDetails> findAll(int start, int end,
		OrderByComparator<ImtdIfpDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the imtd ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd ifp detailses
	*/
	public static List<ImtdIfpDetails> findAll(int start, int end,
		OrderByComparator<ImtdIfpDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the imtd ifp detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of imtd ifp detailses.
	*
	* @return the number of imtd ifp detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ImtdIfpDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImtdIfpDetailsPersistence, ImtdIfpDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ImtdIfpDetailsPersistence.class);
}