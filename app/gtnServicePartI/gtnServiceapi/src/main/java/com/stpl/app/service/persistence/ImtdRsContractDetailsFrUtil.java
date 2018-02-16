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

import com.stpl.app.model.ImtdRsContractDetailsFr;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the imtd rs contract details fr service. This utility wraps {@link com.stpl.app.service.persistence.impl.ImtdRsContractDetailsFrPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsContractDetailsFrPersistence
 * @see com.stpl.app.service.persistence.impl.ImtdRsContractDetailsFrPersistenceImpl
 * @generated
 */
@ProviderType
public class ImtdRsContractDetailsFrUtil {
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
	public static void clearCache(
		ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		getPersistence().clearCache(imtdRsContractDetailsFr);
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
	public static List<ImtdRsContractDetailsFr> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ImtdRsContractDetailsFr> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ImtdRsContractDetailsFr> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ImtdRsContractDetailsFr> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ImtdRsContractDetailsFr update(
		ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		return getPersistence().update(imtdRsContractDetailsFr);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ImtdRsContractDetailsFr update(
		ImtdRsContractDetailsFr imtdRsContractDetailsFr,
		ServiceContext serviceContext) {
		return getPersistence().update(imtdRsContractDetailsFr, serviceContext);
	}

	/**
	* Caches the imtd rs contract details fr in the entity cache if it is enabled.
	*
	* @param imtdRsContractDetailsFr the imtd rs contract details fr
	*/
	public static void cacheResult(
		ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		getPersistence().cacheResult(imtdRsContractDetailsFr);
	}

	/**
	* Caches the imtd rs contract details frs in the entity cache if it is enabled.
	*
	* @param imtdRsContractDetailsFrs the imtd rs contract details frs
	*/
	public static void cacheResult(
		List<ImtdRsContractDetailsFr> imtdRsContractDetailsFrs) {
		getPersistence().cacheResult(imtdRsContractDetailsFrs);
	}

	/**
	* Creates a new imtd rs contract details fr with the primary key. Does not add the imtd rs contract details fr to the database.
	*
	* @param imtdRsContractDetailsFrSid the primary key for the new imtd rs contract details fr
	* @return the new imtd rs contract details fr
	*/
	public static ImtdRsContractDetailsFr create(int imtdRsContractDetailsFrSid) {
		return getPersistence().create(imtdRsContractDetailsFrSid);
	}

	/**
	* Removes the imtd rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
	* @return the imtd rs contract details fr that was removed
	* @throws NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
	*/
	public static ImtdRsContractDetailsFr remove(int imtdRsContractDetailsFrSid)
		throws com.stpl.app.exception.NoSuchImtdRsContractDetailsFrException {
		return getPersistence().remove(imtdRsContractDetailsFrSid);
	}

	public static ImtdRsContractDetailsFr updateImpl(
		ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		return getPersistence().updateImpl(imtdRsContractDetailsFr);
	}

	/**
	* Returns the imtd rs contract details fr with the primary key or throws a {@link NoSuchImtdRsContractDetailsFrException} if it could not be found.
	*
	* @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
	* @return the imtd rs contract details fr
	* @throws NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
	*/
	public static ImtdRsContractDetailsFr findByPrimaryKey(
		int imtdRsContractDetailsFrSid)
		throws com.stpl.app.exception.NoSuchImtdRsContractDetailsFrException {
		return getPersistence().findByPrimaryKey(imtdRsContractDetailsFrSid);
	}

	/**
	* Returns the imtd rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
	* @return the imtd rs contract details fr, or <code>null</code> if a imtd rs contract details fr with the primary key could not be found
	*/
	public static ImtdRsContractDetailsFr fetchByPrimaryKey(
		int imtdRsContractDetailsFrSid) {
		return getPersistence().fetchByPrimaryKey(imtdRsContractDetailsFrSid);
	}

	public static java.util.Map<java.io.Serializable, ImtdRsContractDetailsFr> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the imtd rs contract details frs.
	*
	* @return the imtd rs contract details frs
	*/
	public static List<ImtdRsContractDetailsFr> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the imtd rs contract details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd rs contract details frs
	* @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
	* @return the range of imtd rs contract details frs
	*/
	public static List<ImtdRsContractDetailsFr> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the imtd rs contract details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd rs contract details frs
	* @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of imtd rs contract details frs
	*/
	public static List<ImtdRsContractDetailsFr> findAll(int start, int end,
		OrderByComparator<ImtdRsContractDetailsFr> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the imtd rs contract details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd rs contract details frs
	* @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of imtd rs contract details frs
	*/
	public static List<ImtdRsContractDetailsFr> findAll(int start, int end,
		OrderByComparator<ImtdRsContractDetailsFr> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the imtd rs contract details frs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of imtd rs contract details frs.
	*
	* @return the number of imtd rs contract details frs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ImtdRsContractDetailsFrPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImtdRsContractDetailsFrPersistence, ImtdRsContractDetailsFrPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ImtdRsContractDetailsFrPersistence.class);
}