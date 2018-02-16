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

import com.stpl.app.model.IfpContractDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ifp contract details service. This utility wraps {@link com.stpl.app.service.persistence.impl.IfpContractDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpContractDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.IfpContractDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class IfpContractDetailsUtil {
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
	public static void clearCache(IfpContractDetails ifpContractDetails) {
		getPersistence().clearCache(ifpContractDetails);
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
	public static List<IfpContractDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IfpContractDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IfpContractDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IfpContractDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IfpContractDetails update(
		IfpContractDetails ifpContractDetails) {
		return getPersistence().update(ifpContractDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IfpContractDetails update(
		IfpContractDetails ifpContractDetails, ServiceContext serviceContext) {
		return getPersistence().update(ifpContractDetails, serviceContext);
	}

	/**
	* Returns all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param ifpContractSid the ifp contract sid
	* @param itemMasterSid the item master sid
	* @return the matching ifp contract detailses
	*/
	public static List<IfpContractDetails> findByIFPDetails(
		int ifpContractSid, int itemMasterSid) {
		return getPersistence().findByIFPDetails(ifpContractSid, itemMasterSid);
	}

	/**
	* Returns a range of all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpContractSid the ifp contract sid
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of ifp contract detailses
	* @param end the upper bound of the range of ifp contract detailses (not inclusive)
	* @return the range of matching ifp contract detailses
	*/
	public static List<IfpContractDetails> findByIFPDetails(
		int ifpContractSid, int itemMasterSid, int start, int end) {
		return getPersistence()
				   .findByIFPDetails(ifpContractSid, itemMasterSid, start, end);
	}

	/**
	* Returns an ordered range of all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpContractSid the ifp contract sid
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of ifp contract detailses
	* @param end the upper bound of the range of ifp contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ifp contract detailses
	*/
	public static List<IfpContractDetails> findByIFPDetails(
		int ifpContractSid, int itemMasterSid, int start, int end,
		OrderByComparator<IfpContractDetails> orderByComparator) {
		return getPersistence()
				   .findByIFPDetails(ifpContractSid, itemMasterSid, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ifpContractSid the ifp contract sid
	* @param itemMasterSid the item master sid
	* @param start the lower bound of the range of ifp contract detailses
	* @param end the upper bound of the range of ifp contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ifp contract detailses
	*/
	public static List<IfpContractDetails> findByIFPDetails(
		int ifpContractSid, int itemMasterSid, int start, int end,
		OrderByComparator<IfpContractDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByIFPDetails(ifpContractSid, itemMasterSid, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param ifpContractSid the ifp contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp contract details
	* @throws NoSuchIfpContractDetailsException if a matching ifp contract details could not be found
	*/
	public static IfpContractDetails findByIFPDetails_First(
		int ifpContractSid, int itemMasterSid,
		OrderByComparator<IfpContractDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpContractDetailsException {
		return getPersistence()
				   .findByIFPDetails_First(ifpContractSid, itemMasterSid,
			orderByComparator);
	}

	/**
	* Returns the first ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param ifpContractSid the ifp contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ifp contract details, or <code>null</code> if a matching ifp contract details could not be found
	*/
	public static IfpContractDetails fetchByIFPDetails_First(
		int ifpContractSid, int itemMasterSid,
		OrderByComparator<IfpContractDetails> orderByComparator) {
		return getPersistence()
				   .fetchByIFPDetails_First(ifpContractSid, itemMasterSid,
			orderByComparator);
	}

	/**
	* Returns the last ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param ifpContractSid the ifp contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp contract details
	* @throws NoSuchIfpContractDetailsException if a matching ifp contract details could not be found
	*/
	public static IfpContractDetails findByIFPDetails_Last(int ifpContractSid,
		int itemMasterSid,
		OrderByComparator<IfpContractDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpContractDetailsException {
		return getPersistence()
				   .findByIFPDetails_Last(ifpContractSid, itemMasterSid,
			orderByComparator);
	}

	/**
	* Returns the last ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param ifpContractSid the ifp contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ifp contract details, or <code>null</code> if a matching ifp contract details could not be found
	*/
	public static IfpContractDetails fetchByIFPDetails_Last(
		int ifpContractSid, int itemMasterSid,
		OrderByComparator<IfpContractDetails> orderByComparator) {
		return getPersistence()
				   .fetchByIFPDetails_Last(ifpContractSid, itemMasterSid,
			orderByComparator);
	}

	/**
	* Returns the ifp contract detailses before and after the current ifp contract details in the ordered set where ifpContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param ifpContractDetailsSid the primary key of the current ifp contract details
	* @param ifpContractSid the ifp contract sid
	* @param itemMasterSid the item master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ifp contract details
	* @throws NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
	*/
	public static IfpContractDetails[] findByIFPDetails_PrevAndNext(
		int ifpContractDetailsSid, int ifpContractSid, int itemMasterSid,
		OrderByComparator<IfpContractDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchIfpContractDetailsException {
		return getPersistence()
				   .findByIFPDetails_PrevAndNext(ifpContractDetailsSid,
			ifpContractSid, itemMasterSid, orderByComparator);
	}

	/**
	* Removes all the ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63; from the database.
	*
	* @param ifpContractSid the ifp contract sid
	* @param itemMasterSid the item master sid
	*/
	public static void removeByIFPDetails(int ifpContractSid, int itemMasterSid) {
		getPersistence().removeByIFPDetails(ifpContractSid, itemMasterSid);
	}

	/**
	* Returns the number of ifp contract detailses where ifpContractSid = &#63; and itemMasterSid = &#63;.
	*
	* @param ifpContractSid the ifp contract sid
	* @param itemMasterSid the item master sid
	* @return the number of matching ifp contract detailses
	*/
	public static int countByIFPDetails(int ifpContractSid, int itemMasterSid) {
		return getPersistence().countByIFPDetails(ifpContractSid, itemMasterSid);
	}

	/**
	* Caches the ifp contract details in the entity cache if it is enabled.
	*
	* @param ifpContractDetails the ifp contract details
	*/
	public static void cacheResult(IfpContractDetails ifpContractDetails) {
		getPersistence().cacheResult(ifpContractDetails);
	}

	/**
	* Caches the ifp contract detailses in the entity cache if it is enabled.
	*
	* @param ifpContractDetailses the ifp contract detailses
	*/
	public static void cacheResult(
		List<IfpContractDetails> ifpContractDetailses) {
		getPersistence().cacheResult(ifpContractDetailses);
	}

	/**
	* Creates a new ifp contract details with the primary key. Does not add the ifp contract details to the database.
	*
	* @param ifpContractDetailsSid the primary key for the new ifp contract details
	* @return the new ifp contract details
	*/
	public static IfpContractDetails create(int ifpContractDetailsSid) {
		return getPersistence().create(ifpContractDetailsSid);
	}

	/**
	* Removes the ifp contract details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ifpContractDetailsSid the primary key of the ifp contract details
	* @return the ifp contract details that was removed
	* @throws NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
	*/
	public static IfpContractDetails remove(int ifpContractDetailsSid)
		throws com.stpl.app.exception.NoSuchIfpContractDetailsException {
		return getPersistence().remove(ifpContractDetailsSid);
	}

	public static IfpContractDetails updateImpl(
		IfpContractDetails ifpContractDetails) {
		return getPersistence().updateImpl(ifpContractDetails);
	}

	/**
	* Returns the ifp contract details with the primary key or throws a {@link NoSuchIfpContractDetailsException} if it could not be found.
	*
	* @param ifpContractDetailsSid the primary key of the ifp contract details
	* @return the ifp contract details
	* @throws NoSuchIfpContractDetailsException if a ifp contract details with the primary key could not be found
	*/
	public static IfpContractDetails findByPrimaryKey(int ifpContractDetailsSid)
		throws com.stpl.app.exception.NoSuchIfpContractDetailsException {
		return getPersistence().findByPrimaryKey(ifpContractDetailsSid);
	}

	/**
	* Returns the ifp contract details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ifpContractDetailsSid the primary key of the ifp contract details
	* @return the ifp contract details, or <code>null</code> if a ifp contract details with the primary key could not be found
	*/
	public static IfpContractDetails fetchByPrimaryKey(
		int ifpContractDetailsSid) {
		return getPersistence().fetchByPrimaryKey(ifpContractDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, IfpContractDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ifp contract detailses.
	*
	* @return the ifp contract detailses
	*/
	public static List<IfpContractDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ifp contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp contract detailses
	* @param end the upper bound of the range of ifp contract detailses (not inclusive)
	* @return the range of ifp contract detailses
	*/
	public static List<IfpContractDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ifp contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp contract detailses
	* @param end the upper bound of the range of ifp contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ifp contract detailses
	*/
	public static List<IfpContractDetails> findAll(int start, int end,
		OrderByComparator<IfpContractDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ifp contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ifp contract detailses
	* @param end the upper bound of the range of ifp contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ifp contract detailses
	*/
	public static List<IfpContractDetails> findAll(int start, int end,
		OrderByComparator<IfpContractDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ifp contract detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ifp contract detailses.
	*
	* @return the number of ifp contract detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IfpContractDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IfpContractDetailsPersistence, IfpContractDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IfpContractDetailsPersistence.class);
}