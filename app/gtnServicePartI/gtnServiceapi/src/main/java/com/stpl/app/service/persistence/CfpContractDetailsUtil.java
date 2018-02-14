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

import com.stpl.app.model.CfpContractDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the cfp contract details service. This utility wraps {@link com.stpl.app.service.persistence.impl.CfpContractDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpContractDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.CfpContractDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class CfpContractDetailsUtil {
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
	public static void clearCache(CfpContractDetails cfpContractDetails) {
		getPersistence().clearCache(cfpContractDetails);
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
	public static List<CfpContractDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CfpContractDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CfpContractDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CfpContractDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CfpContractDetails update(
		CfpContractDetails cfpContractDetails) {
		return getPersistence().update(cfpContractDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CfpContractDetails update(
		CfpContractDetails cfpContractDetails, ServiceContext serviceContext) {
		return getPersistence().update(cfpContractDetails, serviceContext);
	}

	/**
	* Returns all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @return the matching cfp contract detailses
	*/
	public static List<CfpContractDetails> findByCFPDetails(
		int companyMasterSid, int cfpContractSid) {
		return getPersistence()
				   .findByCFPDetails(companyMasterSid, cfpContractSid);
	}

	/**
	* Returns a range of all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @return the range of matching cfp contract detailses
	*/
	public static List<CfpContractDetails> findByCFPDetails(
		int companyMasterSid, int cfpContractSid, int start, int end) {
		return getPersistence()
				   .findByCFPDetails(companyMasterSid, cfpContractSid, start,
			end);
	}

	/**
	* Returns an ordered range of all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cfp contract detailses
	*/
	public static List<CfpContractDetails> findByCFPDetails(
		int companyMasterSid, int cfpContractSid, int start, int end,
		OrderByComparator<CfpContractDetails> orderByComparator) {
		return getPersistence()
				   .findByCFPDetails(companyMasterSid, cfpContractSid, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cfp contract detailses
	*/
	public static List<CfpContractDetails> findByCFPDetails(
		int companyMasterSid, int cfpContractSid, int start, int end,
		OrderByComparator<CfpContractDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCFPDetails(companyMasterSid, cfpContractSid, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp contract details
	* @throws NoSuchCfpContractDetailsException if a matching cfp contract details could not be found
	*/
	public static CfpContractDetails findByCFPDetails_First(
		int companyMasterSid, int cfpContractSid,
		OrderByComparator<CfpContractDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpContractDetailsException {
		return getPersistence()
				   .findByCFPDetails_First(companyMasterSid, cfpContractSid,
			orderByComparator);
	}

	/**
	* Returns the first cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp contract details, or <code>null</code> if a matching cfp contract details could not be found
	*/
	public static CfpContractDetails fetchByCFPDetails_First(
		int companyMasterSid, int cfpContractSid,
		OrderByComparator<CfpContractDetails> orderByComparator) {
		return getPersistence()
				   .fetchByCFPDetails_First(companyMasterSid, cfpContractSid,
			orderByComparator);
	}

	/**
	* Returns the last cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp contract details
	* @throws NoSuchCfpContractDetailsException if a matching cfp contract details could not be found
	*/
	public static CfpContractDetails findByCFPDetails_Last(
		int companyMasterSid, int cfpContractSid,
		OrderByComparator<CfpContractDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpContractDetailsException {
		return getPersistence()
				   .findByCFPDetails_Last(companyMasterSid, cfpContractSid,
			orderByComparator);
	}

	/**
	* Returns the last cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp contract details, or <code>null</code> if a matching cfp contract details could not be found
	*/
	public static CfpContractDetails fetchByCFPDetails_Last(
		int companyMasterSid, int cfpContractSid,
		OrderByComparator<CfpContractDetails> orderByComparator) {
		return getPersistence()
				   .fetchByCFPDetails_Last(companyMasterSid, cfpContractSid,
			orderByComparator);
	}

	/**
	* Returns the cfp contract detailses before and after the current cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param cfpContractDetailsSid the primary key of the current cfp contract details
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cfp contract details
	* @throws NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
	*/
	public static CfpContractDetails[] findByCFPDetails_PrevAndNext(
		int cfpContractDetailsSid, int companyMasterSid, int cfpContractSid,
		OrderByComparator<CfpContractDetails> orderByComparator)
		throws com.stpl.app.exception.NoSuchCfpContractDetailsException {
		return getPersistence()
				   .findByCFPDetails_PrevAndNext(cfpContractDetailsSid,
			companyMasterSid, cfpContractSid, orderByComparator);
	}

	/**
	* Removes all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63; from the database.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	*/
	public static void removeByCFPDetails(int companyMasterSid,
		int cfpContractSid) {
		getPersistence().removeByCFPDetails(companyMasterSid, cfpContractSid);
	}

	/**
	* Returns the number of cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @return the number of matching cfp contract detailses
	*/
	public static int countByCFPDetails(int companyMasterSid, int cfpContractSid) {
		return getPersistence()
				   .countByCFPDetails(companyMasterSid, cfpContractSid);
	}

	/**
	* Caches the cfp contract details in the entity cache if it is enabled.
	*
	* @param cfpContractDetails the cfp contract details
	*/
	public static void cacheResult(CfpContractDetails cfpContractDetails) {
		getPersistence().cacheResult(cfpContractDetails);
	}

	/**
	* Caches the cfp contract detailses in the entity cache if it is enabled.
	*
	* @param cfpContractDetailses the cfp contract detailses
	*/
	public static void cacheResult(
		List<CfpContractDetails> cfpContractDetailses) {
		getPersistence().cacheResult(cfpContractDetailses);
	}

	/**
	* Creates a new cfp contract details with the primary key. Does not add the cfp contract details to the database.
	*
	* @param cfpContractDetailsSid the primary key for the new cfp contract details
	* @return the new cfp contract details
	*/
	public static CfpContractDetails create(int cfpContractDetailsSid) {
		return getPersistence().create(cfpContractDetailsSid);
	}

	/**
	* Removes the cfp contract details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpContractDetailsSid the primary key of the cfp contract details
	* @return the cfp contract details that was removed
	* @throws NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
	*/
	public static CfpContractDetails remove(int cfpContractDetailsSid)
		throws com.stpl.app.exception.NoSuchCfpContractDetailsException {
		return getPersistence().remove(cfpContractDetailsSid);
	}

	public static CfpContractDetails updateImpl(
		CfpContractDetails cfpContractDetails) {
		return getPersistence().updateImpl(cfpContractDetails);
	}

	/**
	* Returns the cfp contract details with the primary key or throws a {@link NoSuchCfpContractDetailsException} if it could not be found.
	*
	* @param cfpContractDetailsSid the primary key of the cfp contract details
	* @return the cfp contract details
	* @throws NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
	*/
	public static CfpContractDetails findByPrimaryKey(int cfpContractDetailsSid)
		throws com.stpl.app.exception.NoSuchCfpContractDetailsException {
		return getPersistence().findByPrimaryKey(cfpContractDetailsSid);
	}

	/**
	* Returns the cfp contract details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cfpContractDetailsSid the primary key of the cfp contract details
	* @return the cfp contract details, or <code>null</code> if a cfp contract details with the primary key could not be found
	*/
	public static CfpContractDetails fetchByPrimaryKey(
		int cfpContractDetailsSid) {
		return getPersistence().fetchByPrimaryKey(cfpContractDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, CfpContractDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the cfp contract detailses.
	*
	* @return the cfp contract detailses
	*/
	public static List<CfpContractDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the cfp contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @return the range of cfp contract detailses
	*/
	public static List<CfpContractDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cfp contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cfp contract detailses
	*/
	public static List<CfpContractDetails> findAll(int start, int end,
		OrderByComparator<CfpContractDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the cfp contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cfp contract detailses
	*/
	public static List<CfpContractDetails> findAll(int start, int end,
		OrderByComparator<CfpContractDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the cfp contract detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cfp contract detailses.
	*
	* @return the number of cfp contract detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CfpContractDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CfpContractDetailsPersistence, CfpContractDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CfpContractDetailsPersistence.class);
}