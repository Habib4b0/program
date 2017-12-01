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

import com.stpl.app.model.ImtdCfpDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the imtd cfp details service. This utility wraps {@link com.stpl.app.service.persistence.impl.ImtdCfpDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdCfpDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.ImtdCfpDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class ImtdCfpDetailsUtil {
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
	public static void clearCache(ImtdCfpDetails imtdCfpDetails) {
		getPersistence().clearCache(imtdCfpDetails);
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
	public static List<ImtdCfpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ImtdCfpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ImtdCfpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ImtdCfpDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ImtdCfpDetails update(ImtdCfpDetails imtdCfpDetails) {
		return getPersistence().update(imtdCfpDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ImtdCfpDetails update(ImtdCfpDetails imtdCfpDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(imtdCfpDetails, serviceContext);
	}

	/**
	* Returns all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @return the matching imtd cfp detailses
	*/
	public static List<ImtdCfpDetails> findByImtdCfpSearch(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate) {
		return getPersistence()
				   .findByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate);
	}

	/**
	* Returns a range of all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param start the lower bound of the range of imtd cfp detailses
	* @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	* @return the range of matching imtd cfp detailses
	*/
	public static List<ImtdCfpDetails> findByImtdCfpSearch(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate, int start, int end) {
		return getPersistence()
				   .findByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate,
			start, end);
	}

	/**
	* Returns an ordered range of all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param start the lower bound of the range of imtd cfp detailses
	* @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching imtd cfp detailses
	*/
	public static List<ImtdCfpDetails> findByImtdCfpSearch(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate, int start, int end,
		OrderByComparator<ImtdCfpDetails> orderByComparator) {
		return getPersistence()
				   .findByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param start the lower bound of the range of imtd cfp detailses
	* @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching imtd cfp detailses
	*/
	public static List<ImtdCfpDetails> findByImtdCfpSearch(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate, int start, int end,
		OrderByComparator<ImtdCfpDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching imtd cfp details
	* @throws NoSuchImtdCfpDetailsException if a matching imtd cfp details could not be found
	*/
	public static ImtdCfpDetails findByImtdCfpSearch_First(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate,
		OrderByComparator<ImtdCfpDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchImtdCfpDetailsException {
		return getPersistence()
				   .findByImtdCfpSearch_First(usersSid, sessionId,
			imtdCreatedDate, orderByComparator);
	}

	/**
	* Returns the first imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching imtd cfp details, or <code>null</code> if a matching imtd cfp details could not be found
	*/
	public static ImtdCfpDetails fetchByImtdCfpSearch_First(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate,
		OrderByComparator<ImtdCfpDetails> orderByComparator) {
		return getPersistence()
				   .fetchByImtdCfpSearch_First(usersSid, sessionId,
			imtdCreatedDate, orderByComparator);
	}

	/**
	* Returns the last imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching imtd cfp details
	* @throws NoSuchImtdCfpDetailsException if a matching imtd cfp details could not be found
	*/
	public static ImtdCfpDetails findByImtdCfpSearch_Last(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate,
		OrderByComparator<ImtdCfpDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchImtdCfpDetailsException {
		return getPersistence()
				   .findByImtdCfpSearch_Last(usersSid, sessionId,
			imtdCreatedDate, orderByComparator);
	}

	/**
	* Returns the last imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching imtd cfp details, or <code>null</code> if a matching imtd cfp details could not be found
	*/
	public static ImtdCfpDetails fetchByImtdCfpSearch_Last(
		java.lang.String usersSid, java.lang.String sessionId,
		Date imtdCreatedDate,
		OrderByComparator<ImtdCfpDetails> orderByComparator) {
		return getPersistence()
				   .fetchByImtdCfpSearch_Last(usersSid, sessionId,
			imtdCreatedDate, orderByComparator);
	}

	/**
	* Returns the imtd cfp detailses before and after the current imtd cfp details in the ordered set where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param imtdCfpDetailsSid the primary key of the current imtd cfp details
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next imtd cfp details
	* @throws NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
	*/
	public static ImtdCfpDetails[] findByImtdCfpSearch_PrevAndNext(
		int imtdCfpDetailsSid, java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate,
		OrderByComparator<ImtdCfpDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchImtdCfpDetailsException {
		return getPersistence()
				   .findByImtdCfpSearch_PrevAndNext(imtdCfpDetailsSid,
			usersSid, sessionId, imtdCreatedDate, orderByComparator);
	}

	/**
	* Removes all the imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63; from the database.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	*/
	public static void removeByImtdCfpSearch(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate) {
		getPersistence()
			.removeByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate);
	}

	/**
	* Returns the number of imtd cfp detailses where usersSid = &#63; and sessionId = &#63; and imtdCreatedDate = &#63;.
	*
	* @param usersSid the users sid
	* @param sessionId the session ID
	* @param imtdCreatedDate the imtd created date
	* @return the number of matching imtd cfp detailses
	*/
	public static int countByImtdCfpSearch(java.lang.String usersSid,
		java.lang.String sessionId, Date imtdCreatedDate) {
		return getPersistence()
				   .countByImtdCfpSearch(usersSid, sessionId, imtdCreatedDate);
	}

	/**
	* Caches the imtd cfp details in the entity cache if it is enabled.
	*
	* @param imtdCfpDetails the imtd cfp details
	*/
	public static void cacheResult(ImtdCfpDetails imtdCfpDetails) {
		getPersistence().cacheResult(imtdCfpDetails);
	}

	/**
	* Caches the imtd cfp detailses in the entity cache if it is enabled.
	*
	* @param imtdCfpDetailses the imtd cfp detailses
	*/
	public static void cacheResult(List<ImtdCfpDetails> imtdCfpDetailses) {
		getPersistence().cacheResult(imtdCfpDetailses);
	}

	/**
	* Creates a new imtd cfp details with the primary key. Does not add the imtd cfp details to the database.
	*
	* @param imtdCfpDetailsSid the primary key for the new imtd cfp details
	* @return the new imtd cfp details
	*/
	public static ImtdCfpDetails create(int imtdCfpDetailsSid) {
		return getPersistence().create(imtdCfpDetailsSid);
	}

	/**
	* Removes the imtd cfp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdCfpDetailsSid the primary key of the imtd cfp details
	* @return the imtd cfp details that was removed
	* @throws NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
	*/
	public static ImtdCfpDetails remove(int imtdCfpDetailsSid)
		throws com.stpl.app.exception.NoSuchImtdCfpDetailsException {
		return getPersistence().remove(imtdCfpDetailsSid);
	}

	public static ImtdCfpDetails updateImpl(ImtdCfpDetails imtdCfpDetails) {
		return getPersistence().updateImpl(imtdCfpDetails);
	}

	/**
	* Returns the imtd cfp details with the primary key or throws a {@link NoSuchImtdCfpDetailsException} if it could not be found.
	*
	* @param imtdCfpDetailsSid the primary key of the imtd cfp details
	* @return the imtd cfp details
	* @throws NoSuchImtdCfpDetailsException if a imtd cfp details with the primary key could not be found
	*/
	public static ImtdCfpDetails findByPrimaryKey(int imtdCfpDetailsSid)
		throws com.stpl.app.exception.NoSuchImtdCfpDetailsException {
		return getPersistence().findByPrimaryKey(imtdCfpDetailsSid);
	}

	/**
	* Returns the imtd cfp details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdCfpDetailsSid the primary key of the imtd cfp details
	* @return the imtd cfp details, or <code>null</code> if a imtd cfp details with the primary key could not be found
	*/
	public static ImtdCfpDetails fetchByPrimaryKey(int imtdCfpDetailsSid) {
		return getPersistence().fetchByPrimaryKey(imtdCfpDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, ImtdCfpDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the imtd cfp detailses.
	*
	* @return the imtd cfp detailses
	*/
	public static List<ImtdCfpDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the imtd cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd cfp detailses
	* @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	* @return the range of imtd cfp detailses
	*/
	public static List<ImtdCfpDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the imtd cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd cfp detailses
	* @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd cfp detailses
	*/
	public static List<ImtdCfpDetails> findAll(int start, int end,
		OrderByComparator<ImtdCfpDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the imtd cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdCfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd cfp detailses
	* @param end the upper bound of the range of imtd cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd cfp detailses
	*/
	public static List<ImtdCfpDetails> findAll(int start, int end,
		OrderByComparator<ImtdCfpDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the imtd cfp detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of imtd cfp detailses.
	*
	* @return the number of imtd cfp detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ImtdCfpDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImtdCfpDetailsPersistence, ImtdCfpDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ImtdCfpDetailsPersistence.class);
}