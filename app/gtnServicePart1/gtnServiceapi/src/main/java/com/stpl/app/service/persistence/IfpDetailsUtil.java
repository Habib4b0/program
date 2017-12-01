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

import com.stpl.app.model.IfpDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ifp details service. This utility wraps {@link com.stpl.app.service.persistence.impl.IfpDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.IfpDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class IfpDetailsUtil {
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
	public static void clearCache(IfpDetails ifpDetails) {
		getPersistence().clearCache(ifpDetails);
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
	public static List<IfpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IfpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IfpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IfpDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IfpDetails update(IfpDetails ifpDetails) {
		return getPersistence().update(ifpDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IfpDetails update(IfpDetails ifpDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(ifpDetails, serviceContext);
	}

	/**
	* Returns all the ifp detailses where ifpModelSid = &#63;.
	*
	* @param ifpModelSid the ifp model sid
	* @return the matching ifp detailses
	*/
	public static List<IfpDetails> findByItemFamilyPlanDetails(int ifpModelSid) {
		return getPersistence().findByItemFamilyPlanDetails(ifpModelSid);
	}

	/**
	* Returns a range of all the ifp detailses where ifpModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpModelSid the ifp model sid
	* @param start the lower bound of the range of ifp detailses
	* @param end the upper bound of the range of ifp detailses (not inclusive)
	* @return the range of matching ifp detailses
	*/
	public static List<IfpDetails> findByItemFamilyPlanDetails(
		int ifpModelSid, int start, int end) {
		return getPersistence()
				   .findByItemFamilyPlanDetails(ifpModelSid, start, end);
	}

	/**
	* Returns an ordered range of all the ifp detailses where ifpModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpModelSid the ifp model sid
	* @param start the lower bound of the range of ifp detailses
	* @param end the upper bound of the range of ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp detailses
	*/
	public static List<IfpDetails> findByItemFamilyPlanDetails(
		int ifpModelSid, int start, int end,
		OrderByComparator<IfpDetails> orderByComparator) {
		return getPersistence()
				   .findByItemFamilyPlanDetails(ifpModelSid, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ifp detailses where ifpModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpModelSid the ifp model sid
	* @param start the lower bound of the range of ifp detailses
	* @param end the upper bound of the range of ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp detailses
	*/
	public static List<IfpDetails> findByItemFamilyPlanDetails(
		int ifpModelSid, int start, int end,
		OrderByComparator<IfpDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByItemFamilyPlanDetails(ifpModelSid, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ifp details in the ordered set where ifpModelSid = &#63;.
	*
	* @param ifpModelSid the ifp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp details
	* @throws NoSuchIfpDetailsException if a matching ifp details could not be found
	*/
	public static IfpDetails findByItemFamilyPlanDetails_First(
		int ifpModelSid, OrderByComparator<IfpDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpDetailsException {
		return getPersistence()
				   .findByItemFamilyPlanDetails_First(ifpModelSid,
			orderByComparator);
	}

	/**
	* Returns the first ifp details in the ordered set where ifpModelSid = &#63;.
	*
	* @param ifpModelSid the ifp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp details, or <code>null</code> if a matching ifp details could not be found
	*/
	public static IfpDetails fetchByItemFamilyPlanDetails_First(
		int ifpModelSid, OrderByComparator<IfpDetails> orderByComparator) {
		return getPersistence()
				   .fetchByItemFamilyPlanDetails_First(ifpModelSid,
			orderByComparator);
	}

	/**
	* Returns the last ifp details in the ordered set where ifpModelSid = &#63;.
	*
	* @param ifpModelSid the ifp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp details
	* @throws NoSuchIfpDetailsException if a matching ifp details could not be found
	*/
	public static IfpDetails findByItemFamilyPlanDetails_Last(int ifpModelSid,
		OrderByComparator<IfpDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpDetailsException {
		return getPersistence()
				   .findByItemFamilyPlanDetails_Last(ifpModelSid,
			orderByComparator);
	}

	/**
	* Returns the last ifp details in the ordered set where ifpModelSid = &#63;.
	*
	* @param ifpModelSid the ifp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp details, or <code>null</code> if a matching ifp details could not be found
	*/
	public static IfpDetails fetchByItemFamilyPlanDetails_Last(
		int ifpModelSid, OrderByComparator<IfpDetails> orderByComparator) {
		return getPersistence()
				   .fetchByItemFamilyPlanDetails_Last(ifpModelSid,
			orderByComparator);
	}

	/**
	* Returns the ifp detailses before and after the current ifp details in the ordered set where ifpModelSid = &#63;.
	*
	* @param ifpDetailsSid the primary key of the current ifp details
	* @param ifpModelSid the ifp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp details
	* @throws NoSuchIfpDetailsException if a ifp details with the primary key could not be found
	*/
	public static IfpDetails[] findByItemFamilyPlanDetails_PrevAndNext(
		int ifpDetailsSid, int ifpModelSid,
		OrderByComparator<IfpDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpDetailsException {
		return getPersistence()
				   .findByItemFamilyPlanDetails_PrevAndNext(ifpDetailsSid,
			ifpModelSid, orderByComparator);
	}

	/**
	* Removes all the ifp detailses where ifpModelSid = &#63; from the database.
	*
	* @param ifpModelSid the ifp model sid
	*/
	public static void removeByItemFamilyPlanDetails(int ifpModelSid) {
		getPersistence().removeByItemFamilyPlanDetails(ifpModelSid);
	}

	/**
	* Returns the number of ifp detailses where ifpModelSid = &#63;.
	*
	* @param ifpModelSid the ifp model sid
	* @return the number of matching ifp detailses
	*/
	public static int countByItemFamilyPlanDetails(int ifpModelSid) {
		return getPersistence().countByItemFamilyPlanDetails(ifpModelSid);
	}

	/**
	* Caches the ifp details in the entity cache if it is enabled.
	*
	* @param ifpDetails the ifp details
	*/
	public static void cacheResult(IfpDetails ifpDetails) {
		getPersistence().cacheResult(ifpDetails);
	}

	/**
	* Caches the ifp detailses in the entity cache if it is enabled.
	*
	* @param ifpDetailses the ifp detailses
	*/
	public static void cacheResult(List<IfpDetails> ifpDetailses) {
		getPersistence().cacheResult(ifpDetailses);
	}

	/**
	* Creates a new ifp details with the primary key. Does not add the ifp details to the database.
	*
	* @param ifpDetailsSid the primary key for the new ifp details
	* @return the new ifp details
	*/
	public static IfpDetails create(int ifpDetailsSid) {
		return getPersistence().create(ifpDetailsSid);
	}

	/**
	* Removes the ifp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ifpDetailsSid the primary key of the ifp details
	* @return the ifp details that was removed
	* @throws NoSuchIfpDetailsException if a ifp details with the primary key could not be found
	*/
	public static IfpDetails remove(int ifpDetailsSid)
		throws com.stpl.app.exception.NoSuchIfpDetailsException {
		return getPersistence().remove(ifpDetailsSid);
	}

	public static IfpDetails updateImpl(IfpDetails ifpDetails) {
		return getPersistence().updateImpl(ifpDetails);
	}

	/**
	* Returns the ifp details with the primary key or throws a {@link NoSuchIfpDetailsException} if it could not be found.
	*
	* @param ifpDetailsSid the primary key of the ifp details
	* @return the ifp details
	* @throws NoSuchIfpDetailsException if a ifp details with the primary key could not be found
	*/
	public static IfpDetails findByPrimaryKey(int ifpDetailsSid)
		throws com.stpl.app.exception.NoSuchIfpDetailsException {
		return getPersistence().findByPrimaryKey(ifpDetailsSid);
	}

	/**
	* Returns the ifp details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ifpDetailsSid the primary key of the ifp details
	* @return the ifp details, or <code>null</code> if a ifp details with the primary key could not be found
	*/
	public static IfpDetails fetchByPrimaryKey(int ifpDetailsSid) {
		return getPersistence().fetchByPrimaryKey(ifpDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, IfpDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ifp detailses.
	*
	* @return the ifp detailses
	*/
	public static List<IfpDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp detailses
	* @param end the upper bound of the range of ifp detailses (not inclusive)
	* @return the range of ifp detailses
	*/
	public static List<IfpDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp detailses
	* @param end the upper bound of the range of ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ifp detailses
	*/
	public static List<IfpDetails> findAll(int start, int end,
		OrderByComparator<IfpDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp detailses
	* @param end the upper bound of the range of ifp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ifp detailses
	*/
	public static List<IfpDetails> findAll(int start, int end,
		OrderByComparator<IfpDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ifp detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ifp detailses.
	*
	* @return the number of ifp detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IfpDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IfpDetailsPersistence, IfpDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IfpDetailsPersistence.class);
}