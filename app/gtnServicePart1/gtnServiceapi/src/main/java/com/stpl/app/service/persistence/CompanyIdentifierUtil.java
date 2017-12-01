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

import com.stpl.app.model.CompanyIdentifier;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the company identifier service. This utility wraps {@link com.stpl.app.service.persistence.impl.CompanyIdentifierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CompanyIdentifierPersistence
 * @see com.stpl.app.service.persistence.impl.CompanyIdentifierPersistenceImpl
 * @generated
 */
@ProviderType
public class CompanyIdentifierUtil {
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
	public static void clearCache(CompanyIdentifier companyIdentifier) {
		getPersistence().clearCache(companyIdentifier);
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
	public static List<CompanyIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CompanyIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CompanyIdentifier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CompanyIdentifier update(CompanyIdentifier companyIdentifier) {
		return getPersistence().update(companyIdentifier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CompanyIdentifier update(
		CompanyIdentifier companyIdentifier, ServiceContext serviceContext) {
		return getPersistence().update(companyIdentifier, serviceContext);
	}

	/**
	* Returns all the company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @return the matching company identifiers
	*/
	public static List<CompanyIdentifier> findByCompanyCrtIdentifier(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate) {
		return getPersistence()
				   .findByCompanyCrtIdentifier(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate);
	}

	/**
	* Returns a range of all the company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param start the lower bound of the range of company identifiers
	* @param end the upper bound of the range of company identifiers (not inclusive)
	* @return the range of matching company identifiers
	*/
	public static List<CompanyIdentifier> findByCompanyCrtIdentifier(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate, int start, int end) {
		return getPersistence()
				   .findByCompanyCrtIdentifier(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate, start, end);
	}

	/**
	* Returns an ordered range of all the company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param start the lower bound of the range of company identifiers
	* @param end the upper bound of the range of company identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company identifiers
	*/
	public static List<CompanyIdentifier> findByCompanyCrtIdentifier(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate, int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		return getPersistence()
				   .findByCompanyCrtIdentifier(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param start the lower bound of the range of company identifiers
	* @param end the upper bound of the range of company identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company identifiers
	*/
	public static List<CompanyIdentifier> findByCompanyCrtIdentifier(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate, int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyCrtIdentifier(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first company identifier in the ordered set where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company identifier
	* @throws NoSuchCompanyIdentifierException if a matching company identifier could not be found
	*/
	public static CompanyIdentifier findByCompanyCrtIdentifier_First(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<CompanyIdentifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyIdentifierException {
		return getPersistence()
				   .findByCompanyCrtIdentifier_First(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate, orderByComparator);
	}

	/**
	* Returns the first company identifier in the ordered set where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company identifier, or <code>null</code> if a matching company identifier could not be found
	*/
	public static CompanyIdentifier fetchByCompanyCrtIdentifier_First(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyCrtIdentifier_First(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate, orderByComparator);
	}

	/**
	* Returns the last company identifier in the ordered set where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company identifier
	* @throws NoSuchCompanyIdentifierException if a matching company identifier could not be found
	*/
	public static CompanyIdentifier findByCompanyCrtIdentifier_Last(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<CompanyIdentifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyIdentifierException {
		return getPersistence()
				   .findByCompanyCrtIdentifier_Last(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate, orderByComparator);
	}

	/**
	* Returns the last company identifier in the ordered set where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company identifier, or <code>null</code> if a matching company identifier could not be found
	*/
	public static CompanyIdentifier fetchByCompanyCrtIdentifier_Last(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyCrtIdentifier_Last(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate, orderByComparator);
	}

	/**
	* Returns the company identifiers before and after the current company identifier in the ordered set where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param companyStringIdentifierSid the primary key of the current company identifier
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company identifier
	* @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	*/
	public static CompanyIdentifier[] findByCompanyCrtIdentifier_PrevAndNext(
		int companyStringIdentifierSid,
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		OrderByComparator<CompanyIdentifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyIdentifierException {
		return getPersistence()
				   .findByCompanyCrtIdentifier_PrevAndNext(companyStringIdentifierSid,
			companyStringIdentifierValue, companyQualifierSid,
			identifierStatus, startDate, orderByComparator);
	}

	/**
	* Removes all the company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63; from the database.
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	*/
	public static void removeByCompanyCrtIdentifier(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate) {
		getPersistence()
			.removeByCompanyCrtIdentifier(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate);
	}

	/**
	* Returns the number of company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @return the number of matching company identifiers
	*/
	public static int countByCompanyCrtIdentifier(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate) {
		return getPersistence()
				   .countByCompanyCrtIdentifier(companyStringIdentifierValue,
			companyQualifierSid, identifierStatus, startDate);
	}

	/**
	* Returns all the company identifiers where companyMasterSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @return the matching company identifiers
	*/
	public static List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
		int companyMasterSid) {
		return getPersistence()
				   .findByCompanyCrtIdentifierDetails(companyMasterSid);
	}

	/**
	* Returns a range of all the company identifiers where companyMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyMasterSid the company master sid
	* @param start the lower bound of the range of company identifiers
	* @param end the upper bound of the range of company identifiers (not inclusive)
	* @return the range of matching company identifiers
	*/
	public static List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
		int companyMasterSid, int start, int end) {
		return getPersistence()
				   .findByCompanyCrtIdentifierDetails(companyMasterSid, start,
			end);
	}

	/**
	* Returns an ordered range of all the company identifiers where companyMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyMasterSid the company master sid
	* @param start the lower bound of the range of company identifiers
	* @param end the upper bound of the range of company identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching company identifiers
	*/
	public static List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
		int companyMasterSid, int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		return getPersistence()
				   .findByCompanyCrtIdentifierDetails(companyMasterSid, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the company identifiers where companyMasterSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyMasterSid the company master sid
	* @param start the lower bound of the range of company identifiers
	* @param end the upper bound of the range of company identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching company identifiers
	*/
	public static List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
		int companyMasterSid, int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyCrtIdentifierDetails(companyMasterSid, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first company identifier in the ordered set where companyMasterSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company identifier
	* @throws NoSuchCompanyIdentifierException if a matching company identifier could not be found
	*/
	public static CompanyIdentifier findByCompanyCrtIdentifierDetails_First(
		int companyMasterSid,
		OrderByComparator<CompanyIdentifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyIdentifierException {
		return getPersistence()
				   .findByCompanyCrtIdentifierDetails_First(companyMasterSid,
			orderByComparator);
	}

	/**
	* Returns the first company identifier in the ordered set where companyMasterSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company identifier, or <code>null</code> if a matching company identifier could not be found
	*/
	public static CompanyIdentifier fetchByCompanyCrtIdentifierDetails_First(
		int companyMasterSid,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyCrtIdentifierDetails_First(companyMasterSid,
			orderByComparator);
	}

	/**
	* Returns the last company identifier in the ordered set where companyMasterSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company identifier
	* @throws NoSuchCompanyIdentifierException if a matching company identifier could not be found
	*/
	public static CompanyIdentifier findByCompanyCrtIdentifierDetails_Last(
		int companyMasterSid,
		OrderByComparator<CompanyIdentifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyIdentifierException {
		return getPersistence()
				   .findByCompanyCrtIdentifierDetails_Last(companyMasterSid,
			orderByComparator);
	}

	/**
	* Returns the last company identifier in the ordered set where companyMasterSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company identifier, or <code>null</code> if a matching company identifier could not be found
	*/
	public static CompanyIdentifier fetchByCompanyCrtIdentifierDetails_Last(
		int companyMasterSid,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyCrtIdentifierDetails_Last(companyMasterSid,
			orderByComparator);
	}

	/**
	* Returns the company identifiers before and after the current company identifier in the ordered set where companyMasterSid = &#63;.
	*
	* @param companyStringIdentifierSid the primary key of the current company identifier
	* @param companyMasterSid the company master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company identifier
	* @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	*/
	public static CompanyIdentifier[] findByCompanyCrtIdentifierDetails_PrevAndNext(
		int companyStringIdentifierSid, int companyMasterSid,
		OrderByComparator<CompanyIdentifier> orderByComparator)
		throws com.stpl.app.exception.NoSuchCompanyIdentifierException {
		return getPersistence()
				   .findByCompanyCrtIdentifierDetails_PrevAndNext(companyStringIdentifierSid,
			companyMasterSid, orderByComparator);
	}

	/**
	* Removes all the company identifiers where companyMasterSid = &#63; from the database.
	*
	* @param companyMasterSid the company master sid
	*/
	public static void removeByCompanyCrtIdentifierDetails(int companyMasterSid) {
		getPersistence().removeByCompanyCrtIdentifierDetails(companyMasterSid);
	}

	/**
	* Returns the number of company identifiers where companyMasterSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @return the number of matching company identifiers
	*/
	public static int countByCompanyCrtIdentifierDetails(int companyMasterSid) {
		return getPersistence()
				   .countByCompanyCrtIdentifierDetails(companyMasterSid);
	}

	/**
	* Caches the company identifier in the entity cache if it is enabled.
	*
	* @param companyIdentifier the company identifier
	*/
	public static void cacheResult(CompanyIdentifier companyIdentifier) {
		getPersistence().cacheResult(companyIdentifier);
	}

	/**
	* Caches the company identifiers in the entity cache if it is enabled.
	*
	* @param companyIdentifiers the company identifiers
	*/
	public static void cacheResult(List<CompanyIdentifier> companyIdentifiers) {
		getPersistence().cacheResult(companyIdentifiers);
	}

	/**
	* Creates a new company identifier with the primary key. Does not add the company identifier to the database.
	*
	* @param companyStringIdentifierSid the primary key for the new company identifier
	* @return the new company identifier
	*/
	public static CompanyIdentifier create(int companyStringIdentifierSid) {
		return getPersistence().create(companyStringIdentifierSid);
	}

	/**
	* Removes the company identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyStringIdentifierSid the primary key of the company identifier
	* @return the company identifier that was removed
	* @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	*/
	public static CompanyIdentifier remove(int companyStringIdentifierSid)
		throws com.stpl.app.exception.NoSuchCompanyIdentifierException {
		return getPersistence().remove(companyStringIdentifierSid);
	}

	public static CompanyIdentifier updateImpl(
		CompanyIdentifier companyIdentifier) {
		return getPersistence().updateImpl(companyIdentifier);
	}

	/**
	* Returns the company identifier with the primary key or throws a {@link NoSuchCompanyIdentifierException} if it could not be found.
	*
	* @param companyStringIdentifierSid the primary key of the company identifier
	* @return the company identifier
	* @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	*/
	public static CompanyIdentifier findByPrimaryKey(
		int companyStringIdentifierSid)
		throws com.stpl.app.exception.NoSuchCompanyIdentifierException {
		return getPersistence().findByPrimaryKey(companyStringIdentifierSid);
	}

	/**
	* Returns the company identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyStringIdentifierSid the primary key of the company identifier
	* @return the company identifier, or <code>null</code> if a company identifier with the primary key could not be found
	*/
	public static CompanyIdentifier fetchByPrimaryKey(
		int companyStringIdentifierSid) {
		return getPersistence().fetchByPrimaryKey(companyStringIdentifierSid);
	}

	public static java.util.Map<java.io.Serializable, CompanyIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the company identifiers.
	*
	* @return the company identifiers
	*/
	public static List<CompanyIdentifier> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the company identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company identifiers
	* @param end the upper bound of the range of company identifiers (not inclusive)
	* @return the range of company identifiers
	*/
	public static List<CompanyIdentifier> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the company identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company identifiers
	* @param end the upper bound of the range of company identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of company identifiers
	*/
	public static List<CompanyIdentifier> findAll(int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the company identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company identifiers
	* @param end the upper bound of the range of company identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of company identifiers
	*/
	public static List<CompanyIdentifier> findAll(int start, int end,
		OrderByComparator<CompanyIdentifier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the company identifiers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of company identifiers.
	*
	* @return the number of company identifiers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CompanyIdentifierPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CompanyIdentifierPersistence, CompanyIdentifierPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CompanyIdentifierPersistence.class);
}