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

import com.stpl.app.model.CfpDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cfp details service. This utility wraps {@link com.stpl.app.service.persistence.impl.CfpDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.CfpDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class CfpDetailsUtil {
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
	public static void clearCache(CfpDetails cfpDetails) {
		getPersistence().clearCache(cfpDetails);
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
	public static List<CfpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CfpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CfpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CfpDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CfpDetails update(CfpDetails cfpDetails) {
		return getPersistence().update(cfpDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CfpDetails update(CfpDetails cfpDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(cfpDetails, serviceContext);
	}

	/**
	* Returns all the cfp detailses where cfpModelSid = &#63;.
	*
	* @param cfpModelSid the cfp model sid
	* @return the matching cfp detailses
	*/
	public static List<CfpDetails> findByCfpModelSid(int cfpModelSid) {
		return getPersistence().findByCfpModelSid(cfpModelSid);
	}

	/**
	* Returns a range of all the cfp detailses where cfpModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpModelSid the cfp model sid
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @return the range of matching cfp detailses
	*/
	public static List<CfpDetails> findByCfpModelSid(int cfpModelSid,
		int start, int end) {
		return getPersistence().findByCfpModelSid(cfpModelSid, start, end);
	}

	/**
	* Returns an ordered range of all the cfp detailses where cfpModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpModelSid the cfp model sid
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cfp detailses
	*/
	public static List<CfpDetails> findByCfpModelSid(int cfpModelSid,
		int start, int end, OrderByComparator<CfpDetails> orderByComparator) {
		return getPersistence()
				   .findByCfpModelSid(cfpModelSid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cfp detailses where cfpModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpModelSid the cfp model sid
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cfp detailses
	*/
	public static List<CfpDetails> findByCfpModelSid(int cfpModelSid,
		int start, int end, OrderByComparator<CfpDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCfpModelSid(cfpModelSid, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first cfp details in the ordered set where cfpModelSid = &#63;.
	*
	* @param cfpModelSid the cfp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp details
	* @throws NoSuchCfpDetailsException if a matching cfp details could not be found
	*/
	public static CfpDetails findByCfpModelSid_First(int cfpModelSid,
		OrderByComparator<CfpDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpDetailsException {
		return getPersistence()
				   .findByCfpModelSid_First(cfpModelSid, orderByComparator);
	}

	/**
	* Returns the first cfp details in the ordered set where cfpModelSid = &#63;.
	*
	* @param cfpModelSid the cfp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp details, or <code>null</code> if a matching cfp details could not be found
	*/
	public static CfpDetails fetchByCfpModelSid_First(int cfpModelSid,
		OrderByComparator<CfpDetails> orderByComparator) {
		return getPersistence()
				   .fetchByCfpModelSid_First(cfpModelSid, orderByComparator);
	}

	/**
	* Returns the last cfp details in the ordered set where cfpModelSid = &#63;.
	*
	* @param cfpModelSid the cfp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp details
	* @throws NoSuchCfpDetailsException if a matching cfp details could not be found
	*/
	public static CfpDetails findByCfpModelSid_Last(int cfpModelSid,
		OrderByComparator<CfpDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpDetailsException {
		return getPersistence()
				   .findByCfpModelSid_Last(cfpModelSid, orderByComparator);
	}

	/**
	* Returns the last cfp details in the ordered set where cfpModelSid = &#63;.
	*
	* @param cfpModelSid the cfp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp details, or <code>null</code> if a matching cfp details could not be found
	*/
	public static CfpDetails fetchByCfpModelSid_Last(int cfpModelSid,
		OrderByComparator<CfpDetails> orderByComparator) {
		return getPersistence()
				   .fetchByCfpModelSid_Last(cfpModelSid, orderByComparator);
	}

	/**
	* Returns the cfp detailses before and after the current cfp details in the ordered set where cfpModelSid = &#63;.
	*
	* @param cfpDetailsSid the primary key of the current cfp details
	* @param cfpModelSid the cfp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cfp details
	* @throws NoSuchCfpDetailsException if a cfp details with the primary key could not be found
	*/
	public static CfpDetails[] findByCfpModelSid_PrevAndNext(
		int cfpDetailsSid, int cfpModelSid,
		OrderByComparator<CfpDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpDetailsException {
		return getPersistence()
				   .findByCfpModelSid_PrevAndNext(cfpDetailsSid, cfpModelSid,
			orderByComparator);
	}

	/**
	* Removes all the cfp detailses where cfpModelSid = &#63; from the database.
	*
	* @param cfpModelSid the cfp model sid
	*/
	public static void removeByCfpModelSid(int cfpModelSid) {
		getPersistence().removeByCfpModelSid(cfpModelSid);
	}

	/**
	* Returns the number of cfp detailses where cfpModelSid = &#63;.
	*
	* @param cfpModelSid the cfp model sid
	* @return the number of matching cfp detailses
	*/
	public static int countByCfpModelSid(int cfpModelSid) {
		return getPersistence().countByCfpModelSid(cfpModelSid);
	}

	/**
	* Caches the cfp details in the entity cache if it is enabled.
	*
	* @param cfpDetails the cfp details
	*/
	public static void cacheResult(CfpDetails cfpDetails) {
		getPersistence().cacheResult(cfpDetails);
	}

	/**
	* Caches the cfp detailses in the entity cache if it is enabled.
	*
	* @param cfpDetailses the cfp detailses
	*/
	public static void cacheResult(List<CfpDetails> cfpDetailses) {
		getPersistence().cacheResult(cfpDetailses);
	}

	/**
	* Creates a new cfp details with the primary key. Does not add the cfp details to the database.
	*
	* @param cfpDetailsSid the primary key for the new cfp details
	* @return the new cfp details
	*/
	public static CfpDetails create(int cfpDetailsSid) {
		return getPersistence().create(cfpDetailsSid);
	}

	/**
	* Removes the cfp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpDetailsSid the primary key of the cfp details
	* @return the cfp details that was removed
	* @throws NoSuchCfpDetailsException if a cfp details with the primary key could not be found
	*/
	public static CfpDetails remove(int cfpDetailsSid)
		throws com.stpl.app.exception.NoSuchCfpDetailsException {
		return getPersistence().remove(cfpDetailsSid);
	}

	public static CfpDetails updateImpl(CfpDetails cfpDetails) {
		return getPersistence().updateImpl(cfpDetails);
	}

	/**
	* Returns the cfp details with the primary key or throws a {@link NoSuchCfpDetailsException} if it could not be found.
	*
	* @param cfpDetailsSid the primary key of the cfp details
	* @return the cfp details
	* @throws NoSuchCfpDetailsException if a cfp details with the primary key could not be found
	*/
	public static CfpDetails findByPrimaryKey(int cfpDetailsSid)
		throws com.stpl.app.exception.NoSuchCfpDetailsException {
		return getPersistence().findByPrimaryKey(cfpDetailsSid);
	}

	/**
	* Returns the cfp details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cfpDetailsSid the primary key of the cfp details
	* @return the cfp details, or <code>null</code> if a cfp details with the primary key could not be found
	*/
	public static CfpDetails fetchByPrimaryKey(int cfpDetailsSid) {
		return getPersistence().fetchByPrimaryKey(cfpDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, CfpDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cfp detailses.
	*
	* @return the cfp detailses
	*/
	public static List<CfpDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @return the range of cfp detailses
	*/
	public static List<CfpDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cfp detailses
	*/
	public static List<CfpDetails> findAll(int start, int end,
		OrderByComparator<CfpDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cfp detailses
	*/
	public static List<CfpDetails> findAll(int start, int end,
		OrderByComparator<CfpDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cfp detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cfp detailses.
	*
	* @return the number of cfp detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CfpDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CfpDetailsPersistence, CfpDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CfpDetailsPersistence.class);
}