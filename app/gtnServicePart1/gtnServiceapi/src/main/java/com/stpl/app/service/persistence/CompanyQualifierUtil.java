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

import com.stpl.app.model.CompanyQualifier;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the company qualifier service. This utility wraps {@link com.stpl.app.service.persistence.impl.CompanyQualifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyQualifierPersistence
 * @see com.stpl.app.service.persistence.impl.CompanyQualifierPersistenceImpl
 * @generated
 */
@ProviderType
public class CompanyQualifierUtil {
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
	public static void clearCache(CompanyQualifier companyQualifier) {
		getPersistence().clearCache(companyQualifier);
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
	public static List<CompanyQualifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CompanyQualifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CompanyQualifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CompanyQualifier> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CompanyQualifier update(CompanyQualifier companyQualifier) {
		return getPersistence().update(companyQualifier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CompanyQualifier update(CompanyQualifier companyQualifier,
		ServiceContext serviceContext) {
		return getPersistence().update(companyQualifier, serviceContext);
	}

	/**
	* Returns all the company qualifiers where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @return the matching company qualifiers
	*/
	public static List<CompanyQualifier> findByCompanyCrtQualifierName(
		java.lang.String companyQualifierName) {
		return getPersistence()
				   .findByCompanyCrtQualifierName(companyQualifierName);
	}

	/**
	* Returns a range of all the company qualifiers where companyQualifierName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyQualifierName the company qualifier name
	* @param start the lower bound of the range of company qualifiers
	* @param end the upper bound of the range of company qualifiers (not inclusive)
	* @return the range of matching company qualifiers
	*/
	public static List<CompanyQualifier> findByCompanyCrtQualifierName(
		java.lang.String companyQualifierName, int start, int end) {
		return getPersistence()
				   .findByCompanyCrtQualifierName(companyQualifierName, start,
			end);
	}

	/**
	* Returns an ordered range of all the company qualifiers where companyQualifierName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyQualifierName the company qualifier name
	* @param start the lower bound of the range of company qualifiers
	* @param end the upper bound of the range of company qualifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company qualifiers
	*/
	public static List<CompanyQualifier> findByCompanyCrtQualifierName(
		java.lang.String companyQualifierName, int start, int end,
		OrderByComparator<CompanyQualifier> orderByComparator) {
		return getPersistence()
				   .findByCompanyCrtQualifierName(companyQualifierName, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the company qualifiers where companyQualifierName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyQualifierName the company qualifier name
	* @param start the lower bound of the range of company qualifiers
	* @param end the upper bound of the range of company qualifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company qualifiers
	*/
	public static List<CompanyQualifier> findByCompanyCrtQualifierName(
		java.lang.String companyQualifierName, int start, int end,
		OrderByComparator<CompanyQualifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyCrtQualifierName(companyQualifierName, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first company qualifier in the ordered set where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company qualifier
	* @throws NoSuchCompanyQualifierException if a matching company qualifier could not be found
	*/
	public static CompanyQualifier findByCompanyCrtQualifierName_First(
		java.lang.String companyQualifierName,
		OrderByComparator<CompanyQualifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyQualifierException {
		return getPersistence()
				   .findByCompanyCrtQualifierName_First(companyQualifierName,
			orderByComparator);
	}

	/**
	* Returns the first company qualifier in the ordered set where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
	*/
	public static CompanyQualifier fetchByCompanyCrtQualifierName_First(
		java.lang.String companyQualifierName,
		OrderByComparator<CompanyQualifier> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyCrtQualifierName_First(companyQualifierName,
			orderByComparator);
	}

	/**
	* Returns the last company qualifier in the ordered set where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company qualifier
	* @throws NoSuchCompanyQualifierException if a matching company qualifier could not be found
	*/
	public static CompanyQualifier findByCompanyCrtQualifierName_Last(
		java.lang.String companyQualifierName,
		OrderByComparator<CompanyQualifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyQualifierException {
		return getPersistence()
				   .findByCompanyCrtQualifierName_Last(companyQualifierName,
			orderByComparator);
	}

	/**
	* Returns the last company qualifier in the ordered set where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
	*/
	public static CompanyQualifier fetchByCompanyCrtQualifierName_Last(
		java.lang.String companyQualifierName,
		OrderByComparator<CompanyQualifier> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyCrtQualifierName_Last(companyQualifierName,
			orderByComparator);
	}

	/**
	* Returns the company qualifiers before and after the current company qualifier in the ordered set where companyQualifierName = &#63;.
	*
	* @param companyQualifierSid the primary key of the current company qualifier
	* @param companyQualifierName the company qualifier name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company qualifier
	* @throws NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
	*/
	public static CompanyQualifier[] findByCompanyCrtQualifierName_PrevAndNext(
		int companyQualifierSid, java.lang.String companyQualifierName,
		OrderByComparator<CompanyQualifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyQualifierException {
		return getPersistence()
				   .findByCompanyCrtQualifierName_PrevAndNext(companyQualifierSid,
			companyQualifierName, orderByComparator);
	}

	/**
	* Removes all the company qualifiers where companyQualifierName = &#63; from the database.
	*
	* @param companyQualifierName the company qualifier name
	*/
	public static void removeByCompanyCrtQualifierName(
		java.lang.String companyQualifierName) {
		getPersistence().removeByCompanyCrtQualifierName(companyQualifierName);
	}

	/**
	* Returns the number of company qualifiers where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @return the number of matching company qualifiers
	*/
	public static int countByCompanyCrtQualifierName(
		java.lang.String companyQualifierName) {
		return getPersistence()
				   .countByCompanyCrtQualifierName(companyQualifierName);
	}

	/**
	* Returns the company qualifier where companyQualifierName = &#63; or throws a {@link NoSuchCompanyQualifierException} if it could not be found.
	*
	* @param companyQualifierName the company qualifier name
	* @return the matching company qualifier
	* @throws NoSuchCompanyQualifierException if a matching company qualifier could not be found
	*/
	public static CompanyQualifier findByCompanyCrtQualifierByName(
		java.lang.String companyQualifierName)
		throws com.stpl.app.exception.NoSuchCompanyQualifierException {
		return getPersistence()
				   .findByCompanyCrtQualifierByName(companyQualifierName);
	}

	/**
	* Returns the company qualifier where companyQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyQualifierName the company qualifier name
	* @return the matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
	*/
	public static CompanyQualifier fetchByCompanyCrtQualifierByName(
		java.lang.String companyQualifierName) {
		return getPersistence()
				   .fetchByCompanyCrtQualifierByName(companyQualifierName);
	}

	/**
	* Returns the company qualifier where companyQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyQualifierName the company qualifier name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
	*/
	public static CompanyQualifier fetchByCompanyCrtQualifierByName(
		java.lang.String companyQualifierName, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCompanyCrtQualifierByName(companyQualifierName,
			retrieveFromCache);
	}

	/**
	* Removes the company qualifier where companyQualifierName = &#63; from the database.
	*
	* @param companyQualifierName the company qualifier name
	* @return the company qualifier that was removed
	*/
	public static CompanyQualifier removeByCompanyCrtQualifierByName(
		java.lang.String companyQualifierName)
		throws com.stpl.app.exception.NoSuchCompanyQualifierException {
		return getPersistence()
				   .removeByCompanyCrtQualifierByName(companyQualifierName);
	}

	/**
	* Returns the number of company qualifiers where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @return the number of matching company qualifiers
	*/
	public static int countByCompanyCrtQualifierByName(
		java.lang.String companyQualifierName) {
		return getPersistence()
				   .countByCompanyCrtQualifierByName(companyQualifierName);
	}

	/**
	* Caches the company qualifier in the entity cache if it is enabled.
	*
	* @param companyQualifier the company qualifier
	*/
	public static void cacheResult(CompanyQualifier companyQualifier) {
		getPersistence().cacheResult(companyQualifier);
	}

	/**
	* Caches the company qualifiers in the entity cache if it is enabled.
	*
	* @param companyQualifiers the company qualifiers
	*/
	public static void cacheResult(List<CompanyQualifier> companyQualifiers) {
		getPersistence().cacheResult(companyQualifiers);
	}

	/**
	* Creates a new company qualifier with the primary key. Does not add the company qualifier to the database.
	*
	* @param companyQualifierSid the primary key for the new company qualifier
	* @return the new company qualifier
	*/
	public static CompanyQualifier create(int companyQualifierSid) {
		return getPersistence().create(companyQualifierSid);
	}

	/**
	* Removes the company qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyQualifierSid the primary key of the company qualifier
	* @return the company qualifier that was removed
	* @throws NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
	*/
	public static CompanyQualifier remove(int companyQualifierSid)
		throws com.stpl.app.exception.NoSuchCompanyQualifierException {
		return getPersistence().remove(companyQualifierSid);
	}

	public static CompanyQualifier updateImpl(CompanyQualifier companyQualifier) {
		return getPersistence().updateImpl(companyQualifier);
	}

	/**
	* Returns the company qualifier with the primary key or throws a {@link NoSuchCompanyQualifierException} if it could not be found.
	*
	* @param companyQualifierSid the primary key of the company qualifier
	* @return the company qualifier
	* @throws NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
	*/
	public static CompanyQualifier findByPrimaryKey(int companyQualifierSid)
		throws com.stpl.app.exception.NoSuchCompanyQualifierException {
		return getPersistence().findByPrimaryKey(companyQualifierSid);
	}

	/**
	* Returns the company qualifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyQualifierSid the primary key of the company qualifier
	* @return the company qualifier, or <code>null</code> if a company qualifier with the primary key could not be found
	*/
	public static CompanyQualifier fetchByPrimaryKey(int companyQualifierSid) {
		return getPersistence().fetchByPrimaryKey(companyQualifierSid);
	}

	public static java.util.Map<java.io.Serializable, CompanyQualifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the company qualifiers.
	*
	* @return the company qualifiers
	*/
	public static List<CompanyQualifier> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the company qualifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company qualifiers
	* @param end the upper bound of the range of company qualifiers (not inclusive)
	* @return the range of company qualifiers
	*/
	public static List<CompanyQualifier> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the company qualifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company qualifiers
	* @param end the upper bound of the range of company qualifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of company qualifiers
	*/
	public static List<CompanyQualifier> findAll(int start, int end,
		OrderByComparator<CompanyQualifier> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the company qualifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company qualifiers
	* @param end the upper bound of the range of company qualifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of company qualifiers
	*/
	public static List<CompanyQualifier> findAll(int start, int end,
		OrderByComparator<CompanyQualifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the company qualifiers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of company qualifiers.
	*
	* @return the number of company qualifiers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CompanyQualifierPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CompanyQualifierPersistence, CompanyQualifierPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CompanyQualifierPersistence.class);
}