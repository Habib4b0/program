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

import com.stpl.app.model.PriceScheduleDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the price schedule details service. This utility wraps {@link com.stpl.app.service.persistence.impl.PriceScheduleDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PriceScheduleDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.PriceScheduleDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class PriceScheduleDetailsUtil {
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
	public static void clearCache(PriceScheduleDetails priceScheduleDetails) {
		getPersistence().clearCache(priceScheduleDetails);
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
	public static List<PriceScheduleDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PriceScheduleDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PriceScheduleDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PriceScheduleDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PriceScheduleDetails update(
		PriceScheduleDetails priceScheduleDetails) {
		return getPersistence().update(priceScheduleDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PriceScheduleDetails update(
		PriceScheduleDetails priceScheduleDetails, ServiceContext serviceContext) {
		return getPersistence().update(priceScheduleDetails, serviceContext);
	}

	/**
	* Returns all the price schedule detailses where priceScheduleSystemId = &#63;.
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @return the matching price schedule detailses
	*/
	public static List<PriceScheduleDetails> findByPriceScheduleMasterDetails(
		int priceScheduleSystemId) {
		return getPersistence()
				   .findByPriceScheduleMasterDetails(priceScheduleSystemId);
	}

	/**
	* Returns a range of all the price schedule detailses where priceScheduleSystemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @return the range of matching price schedule detailses
	*/
	public static List<PriceScheduleDetails> findByPriceScheduleMasterDetails(
		int priceScheduleSystemId, int start, int end) {
		return getPersistence()
				   .findByPriceScheduleMasterDetails(priceScheduleSystemId,
			start, end);
	}

	/**
	* Returns an ordered range of all the price schedule detailses where priceScheduleSystemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching price schedule detailses
	*/
	public static List<PriceScheduleDetails> findByPriceScheduleMasterDetails(
		int priceScheduleSystemId, int start, int end,
		OrderByComparator<PriceScheduleDetails> orderByComparator) {
		return getPersistence()
				   .findByPriceScheduleMasterDetails(priceScheduleSystemId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the price schedule detailses where priceScheduleSystemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching price schedule detailses
	*/
	public static List<PriceScheduleDetails> findByPriceScheduleMasterDetails(
		int priceScheduleSystemId, int start, int end,
		OrderByComparator<PriceScheduleDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPriceScheduleMasterDetails(priceScheduleSystemId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule details
	* @throws NoSuchPriceScheduleDetailsException if a matching price schedule details could not be found
	*/
	public static PriceScheduleDetails findByPriceScheduleMasterDetails_First(
		int priceScheduleSystemId,
		OrderByComparator<PriceScheduleDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleDetailsException {
		return getPersistence()
				   .findByPriceScheduleMasterDetails_First(priceScheduleSystemId,
			orderByComparator);
	}

	/**
	* Returns the first price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price schedule details, or <code>null</code> if a matching price schedule details could not be found
	*/
	public static PriceScheduleDetails fetchByPriceScheduleMasterDetails_First(
		int priceScheduleSystemId,
		OrderByComparator<PriceScheduleDetails> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleMasterDetails_First(priceScheduleSystemId,
			orderByComparator);
	}

	/**
	* Returns the last price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule details
	* @throws NoSuchPriceScheduleDetailsException if a matching price schedule details could not be found
	*/
	public static PriceScheduleDetails findByPriceScheduleMasterDetails_Last(
		int priceScheduleSystemId,
		OrderByComparator<PriceScheduleDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleDetailsException {
		return getPersistence()
				   .findByPriceScheduleMasterDetails_Last(priceScheduleSystemId,
			orderByComparator);
	}

	/**
	* Returns the last price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price schedule details, or <code>null</code> if a matching price schedule details could not be found
	*/
	public static PriceScheduleDetails fetchByPriceScheduleMasterDetails_Last(
		int priceScheduleSystemId,
		OrderByComparator<PriceScheduleDetails> orderByComparator) {
		return getPersistence()
				   .fetchByPriceScheduleMasterDetails_Last(priceScheduleSystemId,
			orderByComparator);
	}

	/**
	* Returns the price schedule detailses before and after the current price schedule details in the ordered set where priceScheduleSystemId = &#63;.
	*
	* @param psDetailsSystemId the primary key of the current price schedule details
	* @param priceScheduleSystemId the price schedule system ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price schedule details
	* @throws NoSuchPriceScheduleDetailsException if a price schedule details with the primary key could not be found
	*/
	public static PriceScheduleDetails[] findByPriceScheduleMasterDetails_PrevAndNext(
		int psDetailsSystemId, int priceScheduleSystemId,
		OrderByComparator<PriceScheduleDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchPriceScheduleDetailsException {
		return getPersistence()
				   .findByPriceScheduleMasterDetails_PrevAndNext(psDetailsSystemId,
			priceScheduleSystemId, orderByComparator);
	}

	/**
	* Removes all the price schedule detailses where priceScheduleSystemId = &#63; from the database.
	*
	* @param priceScheduleSystemId the price schedule system ID
	*/
	public static void removeByPriceScheduleMasterDetails(
		int priceScheduleSystemId) {
		getPersistence()
			.removeByPriceScheduleMasterDetails(priceScheduleSystemId);
	}

	/**
	* Returns the number of price schedule detailses where priceScheduleSystemId = &#63;.
	*
	* @param priceScheduleSystemId the price schedule system ID
	* @return the number of matching price schedule detailses
	*/
	public static int countByPriceScheduleMasterDetails(
		int priceScheduleSystemId) {
		return getPersistence()
				   .countByPriceScheduleMasterDetails(priceScheduleSystemId);
	}

	/**
	* Caches the price schedule details in the entity cache if it is enabled.
	*
	* @param priceScheduleDetails the price schedule details
	*/
	public static void cacheResult(PriceScheduleDetails priceScheduleDetails) {
		getPersistence().cacheResult(priceScheduleDetails);
	}

	/**
	* Caches the price schedule detailses in the entity cache if it is enabled.
	*
	* @param priceScheduleDetailses the price schedule detailses
	*/
	public static void cacheResult(
		List<PriceScheduleDetails> priceScheduleDetailses) {
		getPersistence().cacheResult(priceScheduleDetailses);
	}

	/**
	* Creates a new price schedule details with the primary key. Does not add the price schedule details to the database.
	*
	* @param psDetailsSystemId the primary key for the new price schedule details
	* @return the new price schedule details
	*/
	public static PriceScheduleDetails create(int psDetailsSystemId) {
		return getPersistence().create(psDetailsSystemId);
	}

	/**
	* Removes the price schedule details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param psDetailsSystemId the primary key of the price schedule details
	* @return the price schedule details that was removed
	* @throws NoSuchPriceScheduleDetailsException if a price schedule details with the primary key could not be found
	*/
	public static PriceScheduleDetails remove(int psDetailsSystemId)
		throws com.stpl.app.exception.NoSuchPriceScheduleDetailsException {
		return getPersistence().remove(psDetailsSystemId);
	}

	public static PriceScheduleDetails updateImpl(
		PriceScheduleDetails priceScheduleDetails) {
		return getPersistence().updateImpl(priceScheduleDetails);
	}

	/**
	* Returns the price schedule details with the primary key or throws a {@link NoSuchPriceScheduleDetailsException} if it could not be found.
	*
	* @param psDetailsSystemId the primary key of the price schedule details
	* @return the price schedule details
	* @throws NoSuchPriceScheduleDetailsException if a price schedule details with the primary key could not be found
	*/
	public static PriceScheduleDetails findByPrimaryKey(int psDetailsSystemId)
		throws com.stpl.app.exception.NoSuchPriceScheduleDetailsException {
		return getPersistence().findByPrimaryKey(psDetailsSystemId);
	}

	/**
	* Returns the price schedule details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param psDetailsSystemId the primary key of the price schedule details
	* @return the price schedule details, or <code>null</code> if a price schedule details with the primary key could not be found
	*/
	public static PriceScheduleDetails fetchByPrimaryKey(int psDetailsSystemId) {
		return getPersistence().fetchByPrimaryKey(psDetailsSystemId);
	}

	public static java.util.Map<java.io.Serializable, PriceScheduleDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the price schedule detailses.
	*
	* @return the price schedule detailses
	*/
	public static List<PriceScheduleDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the price schedule detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @return the range of price schedule detailses
	*/
	public static List<PriceScheduleDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the price schedule detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of price schedule detailses
	*/
	public static List<PriceScheduleDetails> findAll(int start, int end,
		OrderByComparator<PriceScheduleDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the price schedule detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceScheduleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of price schedule detailses
	* @param end the upper bound of the range of price schedule detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of price schedule detailses
	*/
	public static List<PriceScheduleDetails> findAll(int start, int end,
		OrderByComparator<PriceScheduleDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the price schedule detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of price schedule detailses.
	*
	* @return the number of price schedule detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PriceScheduleDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PriceScheduleDetailsPersistence, PriceScheduleDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(PriceScheduleDetailsPersistence.class);
}