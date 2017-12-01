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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.exception.NoSuchCompanyIdentifierException;
import com.stpl.app.model.CompanyIdentifier;

import java.util.Date;

/**
 * The persistence interface for the company identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CompanyIdentifierPersistenceImpl
 * @see CompanyIdentifierUtil
 * @generated
 */
@ProviderType
public interface CompanyIdentifierPersistence extends BasePersistence<CompanyIdentifier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CompanyIdentifierUtil} to access the company identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @return the matching company identifiers
	*/
	public java.util.List<CompanyIdentifier> findByCompanyCrtIdentifier(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate);

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
	public java.util.List<CompanyIdentifier> findByCompanyCrtIdentifier(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate, int start, int end);

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
	public java.util.List<CompanyIdentifier> findByCompanyCrtIdentifier(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator);

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
	public java.util.List<CompanyIdentifier> findByCompanyCrtIdentifier(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator,
		boolean retrieveFromCache);

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
	public CompanyIdentifier findByCompanyCrtIdentifier_First(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator)
		throws NoSuchCompanyIdentifierException;

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
	public CompanyIdentifier fetchByCompanyCrtIdentifier_First(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator);

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
	public CompanyIdentifier findByCompanyCrtIdentifier_Last(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator)
		throws NoSuchCompanyIdentifierException;

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
	public CompanyIdentifier fetchByCompanyCrtIdentifier_Last(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator);

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
	public CompanyIdentifier[] findByCompanyCrtIdentifier_PrevAndNext(
		int companyStringIdentifierSid,
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator)
		throws NoSuchCompanyIdentifierException;

	/**
	* Removes all the company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63; from the database.
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	*/
	public void removeByCompanyCrtIdentifier(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate);

	/**
	* Returns the number of company identifiers where companyStringIdentifierValue = &#63; and companyQualifierSid = &#63; and identifierStatus = &#63; and startDate = &#63;.
	*
	* @param companyStringIdentifierValue the company string identifier value
	* @param companyQualifierSid the company qualifier sid
	* @param identifierStatus the identifier status
	* @param startDate the start date
	* @return the number of matching company identifiers
	*/
	public int countByCompanyCrtIdentifier(
		java.lang.String companyStringIdentifierValue, int companyQualifierSid,
		int identifierStatus, Date startDate);

	/**
	* Returns all the company identifiers where companyMasterSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @return the matching company identifiers
	*/
	public java.util.List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
		int companyMasterSid);

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
	public java.util.List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
		int companyMasterSid, int start, int end);

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
	public java.util.List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
		int companyMasterSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator);

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
	public java.util.List<CompanyIdentifier> findByCompanyCrtIdentifierDetails(
		int companyMasterSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first company identifier in the ordered set where companyMasterSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company identifier
	* @throws NoSuchCompanyIdentifierException if a matching company identifier could not be found
	*/
	public CompanyIdentifier findByCompanyCrtIdentifierDetails_First(
		int companyMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator)
		throws NoSuchCompanyIdentifierException;

	/**
	* Returns the first company identifier in the ordered set where companyMasterSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company identifier, or <code>null</code> if a matching company identifier could not be found
	*/
	public CompanyIdentifier fetchByCompanyCrtIdentifierDetails_First(
		int companyMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator);

	/**
	* Returns the last company identifier in the ordered set where companyMasterSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company identifier
	* @throws NoSuchCompanyIdentifierException if a matching company identifier could not be found
	*/
	public CompanyIdentifier findByCompanyCrtIdentifierDetails_Last(
		int companyMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator)
		throws NoSuchCompanyIdentifierException;

	/**
	* Returns the last company identifier in the ordered set where companyMasterSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company identifier, or <code>null</code> if a matching company identifier could not be found
	*/
	public CompanyIdentifier fetchByCompanyCrtIdentifierDetails_Last(
		int companyMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator);

	/**
	* Returns the company identifiers before and after the current company identifier in the ordered set where companyMasterSid = &#63;.
	*
	* @param companyStringIdentifierSid the primary key of the current company identifier
	* @param companyMasterSid the company master sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company identifier
	* @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	*/
	public CompanyIdentifier[] findByCompanyCrtIdentifierDetails_PrevAndNext(
		int companyStringIdentifierSid, int companyMasterSid,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator)
		throws NoSuchCompanyIdentifierException;

	/**
	* Removes all the company identifiers where companyMasterSid = &#63; from the database.
	*
	* @param companyMasterSid the company master sid
	*/
	public void removeByCompanyCrtIdentifierDetails(int companyMasterSid);

	/**
	* Returns the number of company identifiers where companyMasterSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @return the number of matching company identifiers
	*/
	public int countByCompanyCrtIdentifierDetails(int companyMasterSid);

	/**
	* Caches the company identifier in the entity cache if it is enabled.
	*
	* @param companyIdentifier the company identifier
	*/
	public void cacheResult(CompanyIdentifier companyIdentifier);

	/**
	* Caches the company identifiers in the entity cache if it is enabled.
	*
	* @param companyIdentifiers the company identifiers
	*/
	public void cacheResult(
		java.util.List<CompanyIdentifier> companyIdentifiers);

	/**
	* Creates a new company identifier with the primary key. Does not add the company identifier to the database.
	*
	* @param companyStringIdentifierSid the primary key for the new company identifier
	* @return the new company identifier
	*/
	public CompanyIdentifier create(int companyStringIdentifierSid);

	/**
	* Removes the company identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyStringIdentifierSid the primary key of the company identifier
	* @return the company identifier that was removed
	* @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	*/
	public CompanyIdentifier remove(int companyStringIdentifierSid)
		throws NoSuchCompanyIdentifierException;

	public CompanyIdentifier updateImpl(CompanyIdentifier companyIdentifier);

	/**
	* Returns the company identifier with the primary key or throws a {@link NoSuchCompanyIdentifierException} if it could not be found.
	*
	* @param companyStringIdentifierSid the primary key of the company identifier
	* @return the company identifier
	* @throws NoSuchCompanyIdentifierException if a company identifier with the primary key could not be found
	*/
	public CompanyIdentifier findByPrimaryKey(int companyStringIdentifierSid)
		throws NoSuchCompanyIdentifierException;

	/**
	* Returns the company identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyStringIdentifierSid the primary key of the company identifier
	* @return the company identifier, or <code>null</code> if a company identifier with the primary key could not be found
	*/
	public CompanyIdentifier fetchByPrimaryKey(int companyStringIdentifierSid);

	@Override
	public java.util.Map<java.io.Serializable, CompanyIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the company identifiers.
	*
	* @return the company identifiers
	*/
	public java.util.List<CompanyIdentifier> findAll();

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
	public java.util.List<CompanyIdentifier> findAll(int start, int end);

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
	public java.util.List<CompanyIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator);

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
	public java.util.List<CompanyIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyIdentifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the company identifiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of company identifiers.
	*
	* @return the number of company identifiers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}