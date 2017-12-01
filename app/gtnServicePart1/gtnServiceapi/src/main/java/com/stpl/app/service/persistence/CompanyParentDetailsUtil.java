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

import com.stpl.app.model.CompanyParentDetails;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the company parent details service. This utility wraps {@link com.stpl.app.service.persistence.impl.CompanyParentDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyParentDetailsPersistence
 * @see com.stpl.app.service.persistence.impl.CompanyParentDetailsPersistenceImpl
 * @generated
 */
@ProviderType
public class CompanyParentDetailsUtil {
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
	public static void clearCache(CompanyParentDetails companyParentDetails) {
		getPersistence().clearCache(companyParentDetails);
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
	public static List<CompanyParentDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CompanyParentDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CompanyParentDetails> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CompanyParentDetails> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CompanyParentDetails update(
		CompanyParentDetails companyParentDetails) {
		return getPersistence().update(companyParentDetails);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CompanyParentDetails update(
		CompanyParentDetails companyParentDetails, ServiceContext serviceContext) {
		return getPersistence().update(companyParentDetails, serviceContext);
	}

	/**
	* Caches the company parent details in the entity cache if it is enabled.
	*
	* @param companyParentDetails the company parent details
	*/
	public static void cacheResult(CompanyParentDetails companyParentDetails) {
		getPersistence().cacheResult(companyParentDetails);
	}

	/**
	* Caches the company parent detailses in the entity cache if it is enabled.
	*
	* @param companyParentDetailses the company parent detailses
	*/
	public static void cacheResult(
		List<CompanyParentDetails> companyParentDetailses) {
		getPersistence().cacheResult(companyParentDetailses);
	}

	/**
	* Creates a new company parent details with the primary key. Does not add the company parent details to the database.
	*
	* @param companyParentDetailsSid the primary key for the new company parent details
	* @return the new company parent details
	*/
	public static CompanyParentDetails create(int companyParentDetailsSid) {
		return getPersistence().create(companyParentDetailsSid);
	}

	/**
	* Removes the company parent details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyParentDetailsSid the primary key of the company parent details
	* @return the company parent details that was removed
	* @throws NoSuchCompanyParentDetailsException if a company parent details with the primary key could not be found
	*/
	public static CompanyParentDetails remove(int companyParentDetailsSid)
		throws com.stpl.app.exception.NoSuchCompanyParentDetailsException {
		return getPersistence().remove(companyParentDetailsSid);
	}

	public static CompanyParentDetails updateImpl(
		CompanyParentDetails companyParentDetails) {
		return getPersistence().updateImpl(companyParentDetails);
	}

	/**
	* Returns the company parent details with the primary key or throws a {@link NoSuchCompanyParentDetailsException} if it could not be found.
	*
	* @param companyParentDetailsSid the primary key of the company parent details
	* @return the company parent details
	* @throws NoSuchCompanyParentDetailsException if a company parent details with the primary key could not be found
	*/
	public static CompanyParentDetails findByPrimaryKey(
		int companyParentDetailsSid)
		throws com.stpl.app.exception.NoSuchCompanyParentDetailsException {
		return getPersistence().findByPrimaryKey(companyParentDetailsSid);
	}

	/**
	* Returns the company parent details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyParentDetailsSid the primary key of the company parent details
	* @return the company parent details, or <code>null</code> if a company parent details with the primary key could not be found
	*/
	public static CompanyParentDetails fetchByPrimaryKey(
		int companyParentDetailsSid) {
		return getPersistence().fetchByPrimaryKey(companyParentDetailsSid);
	}

	public static java.util.Map<java.io.Serializable, CompanyParentDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the company parent detailses.
	*
	* @return the company parent detailses
	*/
	public static List<CompanyParentDetails> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the company parent detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company parent detailses
	* @param end the upper bound of the range of company parent detailses (not inclusive)
	* @return the range of company parent detailses
	*/
	public static List<CompanyParentDetails> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the company parent detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company parent detailses
	* @param end the upper bound of the range of company parent detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of company parent detailses
	*/
	public static List<CompanyParentDetails> findAll(int start, int end,
		OrderByComparator<CompanyParentDetails> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the company parent detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company parent detailses
	* @param end the upper bound of the range of company parent detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of company parent detailses
	*/
	public static List<CompanyParentDetails> findAll(int start, int end,
		OrderByComparator<CompanyParentDetails> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the company parent detailses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of company parent detailses.
	*
	* @return the number of company parent detailses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CompanyParentDetailsPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CompanyParentDetailsPersistence, CompanyParentDetailsPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CompanyParentDetailsPersistence.class);
}