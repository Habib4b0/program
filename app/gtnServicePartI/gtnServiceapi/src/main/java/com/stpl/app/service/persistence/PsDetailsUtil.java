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

import com.stpl.app.model.PsDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ps details service. This utility wraps {@link com.stpl.app.service.persistence.impl.PsDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.PsDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class PsDetailsUtil {
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
	public static void clearCache(PsDetails psDetails) {
		getPersistence().clearCache(psDetails);
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
	public static List<PsDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PsDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PsDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PsDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PsDetails update(PsDetails psDetails) {
		return getPersistence().update(psDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PsDetails update(PsDetails psDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(psDetails, serviceContext);
	}

	/**
	* Returns all the ps detailses where psModelSid = &#63;.
	*
	* @param psModelSid the ps model sid
	* @return the matching ps detailses
	*/
	public static List<PsDetails> findByPriceScheduleMasterDetails(
		int psModelSid) {
		return getPersistence().findByPriceScheduleMasterDetails(psModelSid);
	}

	/**
	* Returns a range of all the ps detailses where psModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psModelSid the ps model sid
	* @param start the lower bound of the range of ps detailses
	* @param end the upper bound of the range of ps detailses (not inclusive)
	* @return the range of matching ps detailses
	*/
	public static List<PsDetails> findByPriceScheduleMasterDetails(
		int psModelSid, int start, int end) {
		return getPersistence()
				   .findByPriceScheduleMasterDetails(psModelSid, start, end);
	}

	/**
	* Returns an ordered range of all the ps detailses where psModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psModelSid the ps model sid
	* @param start the lower bound of the range of ps detailses
	* @param end the upper bound of the range of ps detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ps detailses
	*/
	public static List<PsDetails> findByPriceScheduleMasterDetails(
		int psModelSid, int start, int end,
		OrderByComparator<PsDetails> orderByComparator) {
		return getPersistence()
				   .findByPriceScheduleMasterDetails(psModelSid, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ps detailses where psModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param psModelSid the ps model sid
	* @param start the lower bound of the range of ps detailses
	* @param end the upper bound of the range of ps detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ps detailses
	*/
	public static List<PsDetails> findByPriceScheduleMasterDetails(
		int psModelSid, int start, int end,
		OrderByComparator<PsDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPriceScheduleMasterDetails(psModelSid, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ps details in the ordered set where psModelSid = &#63;.
	*
	* @param psModelSid the ps model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps details
	* @throws NoSuchPsDetailsException if a matching ps details could not be found
	*/
	public static PsDetails findByPriceScheduleMasterDetails_First(
		int psModelSid, OrderByComparator<PsDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsDetailsException {
		return getPersistence()
				   .findByPriceScheduleMasterDetails_First(psModelSid,
			orderByComparator);
	}

	/**
	* Returns the first ps details in the ordered set where psModelSid = &#63;.
	*
	* @param psModelSid the ps model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ps details, or <code>null</code> if a matching ps details could not be found
	*/
	public static PsDetails fetchByPriceScheduleMasterDetails_First(
		int psModelSid, OrderByComparator<PsDetails> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleMasterDetails_First(psModelSid,
			orderByComparator);
	}

	/**
	* Returns the last ps details in the ordered set where psModelSid = &#63;.
	*
	* @param psModelSid the ps model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps details
	* @throws NoSuchPsDetailsException if a matching ps details could not be found
	*/
	public static PsDetails findByPriceScheduleMasterDetails_Last(
		int psModelSid, OrderByComparator<PsDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsDetailsException {
		return getPersistence()
				   .findByPriceScheduleMasterDetails_Last(psModelSid,
			orderByComparator);
	}

	/**
	* Returns the last ps details in the ordered set where psModelSid = &#63;.
	*
	* @param psModelSid the ps model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ps details, or <code>null</code> if a matching ps details could not be found
	*/
	public static PsDetails fetchByPriceScheduleMasterDetails_Last(
		int psModelSid, OrderByComparator<PsDetails> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleMasterDetails_Last(psModelSid,
			orderByComparator);
	}

	/**
	* Returns the ps detailses before and after the current ps details in the ordered set where psModelSid = &#63;.
	*
	* @param psDetailsSid the primary key of the current ps details
	* @param psModelSid the ps model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ps details
	* @throws NoSuchPsDetailsException if a ps details with the primary key could not be found
	*/
	public static PsDetails[] findByPriceScheduleMasterDetails_PrevAndNext(
		int psDetailsSid, int psModelSid,
		OrderByComparator<PsDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchPsDetailsException {
		return getPersistence()
				   .findByPriceScheduleMasterDetails_PrevAndNext(psDetailsSid,
			psModelSid, orderByComparator);
	}

	/**
	* Removes all the ps detailses where psModelSid = &#63; from the database.
	*
	* @param psModelSid the ps model sid
	*/
	public static void removeByPriceScheduleMasterDetails(int psModelSid) {
		getPersistence().removeByPriceScheduleMasterDetails(psModelSid);
	}

	/**
	* Returns the number of ps detailses where psModelSid = &#63;.
	*
	* @param psModelSid the ps model sid
	* @return the number of matching ps detailses
	*/
	public static int countByPriceScheduleMasterDetails(int psModelSid) {
		return getPersistence().countByPriceScheduleMasterDetails(psModelSid);
	}

	/**
	* Caches the ps details in the entity cache if it is enabled.
	*
	* @param psDetails the ps details
	*/
	public static void cacheResult(PsDetails psDetails) {
		getPersistence().cacheResult(psDetails);
	}

	/**
	* Caches the ps detailses in the entity cache if it is enabled.
	*
	* @param psDetailses the ps detailses
	*/
	public static void cacheResult(List<PsDetails> psDetailses) {
		getPersistence().cacheResult(psDetailses);
	}

	/**
	* Creates a new ps details with the primary key. Does not add the ps details to the database.
	*
	* @param psDetailsSid the primary key for the new ps details
	* @return the new ps details
	*/
	public static PsDetails create(int psDetailsSid) {
		return getPersistence().create(psDetailsSid);
	}

	/**
	* Removes the ps details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param psDetailsSid the primary key of the ps details
	* @return the ps details that was removed
	* @throws NoSuchPsDetailsException if a ps details with the primary key could not be found
	*/
	public static PsDetails remove(int psDetailsSid)
		throws com.stpl.app.exception.NoSuchPsDetailsException {
		return getPersistence().remove(psDetailsSid);
	}

	public static PsDetails updateImpl(PsDetails psDetails) {
		return getPersistence().updateImpl(psDetails);
	}

	/**
	* Returns the ps details with the primary key or throws a {@link NoSuchPsDetailsException} if it could not be found.
	*
	* @param psDetailsSid the primary key of the ps details
	* @return the ps details
	* @throws NoSuchPsDetailsException if a ps details with the primary key could not be found
	*/
	public static PsDetails findByPrimaryKey(int psDetailsSid)
		throws com.stpl.app.exception.NoSuchPsDetailsException {
		return getPersistence().findByPrimaryKey(psDetailsSid);
	}

	/**
	* Returns the ps details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param psDetailsSid the primary key of the ps details
	* @return the ps details, or <code>null</code> if a ps details with the primary key could not be found
	*/
	public static PsDetails fetchByPrimaryKey(int psDetailsSid) {
		return getPersistence().fetchByPrimaryKey(psDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, PsDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ps detailses.
	*
	* @return the ps detailses
	*/
	public static List<PsDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ps detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps detailses
	* @param end the upper bound of the range of ps detailses (not inclusive)
	* @return the range of ps detailses
	*/
	public static List<PsDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ps detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps detailses
	* @param end the upper bound of the range of ps detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ps detailses
	*/
	public static List<PsDetails> findAll(int start, int end,
		OrderByComparator<PsDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ps detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps detailses
	* @param end the upper bound of the range of ps detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ps detailses
	*/
	public static List<PsDetails> findAll(int start, int end,
		OrderByComparator<PsDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ps detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ps detailses.
	*
	* @return the number of ps detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PsDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PsDetailsPersistence, PsDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(PsDetailsPersistence.class);
}