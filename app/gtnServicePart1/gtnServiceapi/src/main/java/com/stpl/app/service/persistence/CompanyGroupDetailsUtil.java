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

import com.stpl.app.model.CompanyGroupDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the company group details service. This utility wraps {@link com.stpl.app.service.persistence.impl.CompanyGroupDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyGroupDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.CompanyGroupDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class CompanyGroupDetailsUtil {
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
	public static void clearCache(CompanyGroupDetails companyGroupDetails) {
		getPersistence().clearCache(companyGroupDetails);
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
	public static List<CompanyGroupDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CompanyGroupDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CompanyGroupDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CompanyGroupDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CompanyGroupDetails update(
		CompanyGroupDetails companyGroupDetails) {
		return getPersistence().update(companyGroupDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CompanyGroupDetails update(
		CompanyGroupDetails companyGroupDetails, ServiceContext serviceContext) {
		return getPersistence().update(companyGroupDetails, serviceContext);
	}

	/**
	* Caches the company group details in the entity cache if it is enabled.
	*
	* @param companyGroupDetails the company group details
	*/
	public static void cacheResult(CompanyGroupDetails companyGroupDetails) {
		getPersistence().cacheResult(companyGroupDetails);
	}

	/**
	* Caches the company group detailses in the entity cache if it is enabled.
	*
	* @param companyGroupDetailses the company group detailses
	*/
	public static void cacheResult(
		List<CompanyGroupDetails> companyGroupDetailses) {
		getPersistence().cacheResult(companyGroupDetailses);
	}

	/**
	* Creates a new company group details with the primary key. Does not add the company group details to the database.
	*
	* @param companyGroupDetailsSid the primary key for the new company group details
	* @return the new company group details
	*/
	public static CompanyGroupDetails create(int companyGroupDetailsSid) {
		return getPersistence().create(companyGroupDetailsSid);
	}

	/**
	* Removes the company group details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyGroupDetailsSid the primary key of the company group details
	* @return the company group details that was removed
	* @throws NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
	*/
	public static CompanyGroupDetails remove(int companyGroupDetailsSid)
		throws com.stpl.app.exception.NoSuchCompanyGroupDetailsException {
		return getPersistence().remove(companyGroupDetailsSid);
	}

	public static CompanyGroupDetails updateImpl(
		CompanyGroupDetails companyGroupDetails) {
		return getPersistence().updateImpl(companyGroupDetails);
	}

	/**
	* Returns the company group details with the primary key or throws a {@link NoSuchCompanyGroupDetailsException} if it could not be found.
	*
	* @param companyGroupDetailsSid the primary key of the company group details
	* @return the company group details
	* @throws NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
	*/
	public static CompanyGroupDetails findByPrimaryKey(
		int companyGroupDetailsSid)
		throws com.stpl.app.exception.NoSuchCompanyGroupDetailsException {
		return getPersistence().findByPrimaryKey(companyGroupDetailsSid);
	}

	/**
	* Returns the company group details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyGroupDetailsSid the primary key of the company group details
	* @return the company group details, or <code>null</code> if a company group details with the primary key could not be found
	*/
	public static CompanyGroupDetails fetchByPrimaryKey(
		int companyGroupDetailsSid) {
		return getPersistence().fetchByPrimaryKey(companyGroupDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, CompanyGroupDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the company group detailses.
	*
	* @return the company group detailses
	*/
	public static List<CompanyGroupDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the company group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company group detailses
	* @param end the upper bound of the range of company group detailses (not inclusive)
	* @return the range of company group detailses
	*/
	public static List<CompanyGroupDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the company group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company group detailses
	* @param end the upper bound of the range of company group detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of company group detailses
	*/
	public static List<CompanyGroupDetails> findAll(int start, int end,
		OrderByComparator<CompanyGroupDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the company group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company group detailses
	* @param end the upper bound of the range of company group detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of company group detailses
	*/
	public static List<CompanyGroupDetails> findAll(int start, int end,
		OrderByComparator<CompanyGroupDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the company group detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of company group detailses.
	*
	* @return the number of company group detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CompanyGroupDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CompanyGroupDetailsPersistence, CompanyGroupDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CompanyGroupDetailsPersistence.class);
}