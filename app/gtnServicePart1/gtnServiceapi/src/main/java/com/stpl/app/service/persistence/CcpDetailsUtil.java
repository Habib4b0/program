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

import com.stpl.app.model.CcpDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ccp details service. This utility wraps {@link com.stpl.app.service.persistence.impl.CcpDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CcpDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.CcpDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class CcpDetailsUtil {
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
	public static void clearCache(CcpDetails ccpDetails) {
		getPersistence().clearCache(ccpDetails);
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
	public static List<CcpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CcpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CcpDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CcpDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CcpDetails update(CcpDetails ccpDetails) {
		return getPersistence().update(ccpDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CcpDetails update(CcpDetails ccpDetails,
		ServiceContext serviceContext) {
		return getPersistence().update(ccpDetails, serviceContext);
	}

	/**
	* Caches the ccp details in the entity cache if it is enabled.
	*
	* @param ccpDetails the ccp details
	*/
	public static void cacheResult(CcpDetails ccpDetails) {
		getPersistence().cacheResult(ccpDetails);
	}

	/**
	* Caches the ccp detailses in the entity cache if it is enabled.
	*
	* @param ccpDetailses the ccp detailses
	*/
	public static void cacheResult(List<CcpDetails> ccpDetailses) {
		getPersistence().cacheResult(ccpDetailses);
	}

	/**
	* Creates a new ccp details with the primary key. Does not add the ccp details to the database.
	*
	* @param ccpDetailsSid the primary key for the new ccp details
	* @return the new ccp details
	*/
	public static CcpDetails create(int ccpDetailsSid) {
		return getPersistence().create(ccpDetailsSid);
	}

	/**
	* Removes the ccp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ccpDetailsSid the primary key of the ccp details
	* @return the ccp details that was removed
	* @throws NoSuchCcpDetailsException if a ccp details with the primary key could not be found
	*/
	public static CcpDetails remove(int ccpDetailsSid)
		throws com.stpl.app.exception.NoSuchCcpDetailsException {
		return getPersistence().remove(ccpDetailsSid);
	}

	public static CcpDetails updateImpl(CcpDetails ccpDetails) {
		return getPersistence().updateImpl(ccpDetails);
	}

	/**
	* Returns the ccp details with the primary key or throws a {@link NoSuchCcpDetailsException} if it could not be found.
	*
	* @param ccpDetailsSid the primary key of the ccp details
	* @return the ccp details
	* @throws NoSuchCcpDetailsException if a ccp details with the primary key could not be found
	*/
	public static CcpDetails findByPrimaryKey(int ccpDetailsSid)
		throws com.stpl.app.exception.NoSuchCcpDetailsException {
		return getPersistence().findByPrimaryKey(ccpDetailsSid);
	}

	/**
	* Returns the ccp details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ccpDetailsSid the primary key of the ccp details
	* @return the ccp details, or <code>null</code> if a ccp details with the primary key could not be found
	*/
	public static CcpDetails fetchByPrimaryKey(int ccpDetailsSid) {
		return getPersistence().fetchByPrimaryKey(ccpDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, CcpDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ccp detailses.
	*
	* @return the ccp detailses
	*/
	public static List<CcpDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ccp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ccp detailses
	* @param end the upper bound of the range of ccp detailses (not inclusive)
	* @return the range of ccp detailses
	*/
	public static List<CcpDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ccp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ccp detailses
	* @param end the upper bound of the range of ccp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ccp detailses
	*/
	public static List<CcpDetails> findAll(int start, int end,
		OrderByComparator<CcpDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ccp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ccp detailses
	* @param end the upper bound of the range of ccp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ccp detailses
	*/
	public static List<CcpDetails> findAll(int start, int end,
		OrderByComparator<CcpDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ccp detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ccp detailses.
	*
	* @return the number of ccp detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CcpDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CcpDetailsPersistence, CcpDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CcpDetailsPersistence.class);
}