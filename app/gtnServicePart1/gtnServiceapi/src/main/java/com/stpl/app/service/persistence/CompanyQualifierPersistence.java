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

import com.stpl.app.exception.NoSuchCompanyQualifierException;
import com.stpl.app.model.CompanyQualifier;

/**
 * The persistence interface for the company qualifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CompanyQualifierPersistenceImpl
 * @see CompanyQualifierUtil
 * @generated
 */
@ProviderType
public interface CompanyQualifierPersistence extends BasePersistence<CompanyQualifier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CompanyQualifierUtil} to access the company qualifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the company qualifiers where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @return the matching company qualifiers
	*/
	public java.util.List<CompanyQualifier> findByCompanyCrtQualifierName(
		java.lang.String companyQualifierName);

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
	public java.util.List<CompanyQualifier> findByCompanyCrtQualifierName(
		java.lang.String companyQualifierName, int start, int end);

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
	public java.util.List<CompanyQualifier> findByCompanyCrtQualifierName(
		java.lang.String companyQualifierName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyQualifier> orderByComparator);

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
	public java.util.List<CompanyQualifier> findByCompanyCrtQualifierName(
		java.lang.String companyQualifierName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyQualifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first company qualifier in the ordered set where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company qualifier
	* @throws NoSuchCompanyQualifierException if a matching company qualifier could not be found
	*/
	public CompanyQualifier findByCompanyCrtQualifierName_First(
		java.lang.String companyQualifierName,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyQualifier> orderByComparator)
		throws NoSuchCompanyQualifierException;

	/**
	* Returns the first company qualifier in the ordered set where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
	*/
	public CompanyQualifier fetchByCompanyCrtQualifierName_First(
		java.lang.String companyQualifierName,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyQualifier> orderByComparator);

	/**
	* Returns the last company qualifier in the ordered set where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company qualifier
	* @throws NoSuchCompanyQualifierException if a matching company qualifier could not be found
	*/
	public CompanyQualifier findByCompanyCrtQualifierName_Last(
		java.lang.String companyQualifierName,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyQualifier> orderByComparator)
		throws NoSuchCompanyQualifierException;

	/**
	* Returns the last company qualifier in the ordered set where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
	*/
	public CompanyQualifier fetchByCompanyCrtQualifierName_Last(
		java.lang.String companyQualifierName,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyQualifier> orderByComparator);

	/**
	* Returns the company qualifiers before and after the current company qualifier in the ordered set where companyQualifierName = &#63;.
	*
	* @param companyQualifierSid the primary key of the current company qualifier
	* @param companyQualifierName the company qualifier name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next company qualifier
	* @throws NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
	*/
	public CompanyQualifier[] findByCompanyCrtQualifierName_PrevAndNext(
		int companyQualifierSid, java.lang.String companyQualifierName,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyQualifier> orderByComparator)
		throws NoSuchCompanyQualifierException;

	/**
	* Removes all the company qualifiers where companyQualifierName = &#63; from the database.
	*
	* @param companyQualifierName the company qualifier name
	*/
	public void removeByCompanyCrtQualifierName(
		java.lang.String companyQualifierName);

	/**
	* Returns the number of company qualifiers where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @return the number of matching company qualifiers
	*/
	public int countByCompanyCrtQualifierName(
		java.lang.String companyQualifierName);

	/**
	* Returns the company qualifier where companyQualifierName = &#63; or throws a {@link NoSuchCompanyQualifierException} if it could not be found.
	*
	* @param companyQualifierName the company qualifier name
	* @return the matching company qualifier
	* @throws NoSuchCompanyQualifierException if a matching company qualifier could not be found
	*/
	public CompanyQualifier findByCompanyCrtQualifierByName(
		java.lang.String companyQualifierName)
		throws NoSuchCompanyQualifierException;

	/**
	* Returns the company qualifier where companyQualifierName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyQualifierName the company qualifier name
	* @return the matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
	*/
	public CompanyQualifier fetchByCompanyCrtQualifierByName(
		java.lang.String companyQualifierName);

	/**
	* Returns the company qualifier where companyQualifierName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyQualifierName the company qualifier name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching company qualifier, or <code>null</code> if a matching company qualifier could not be found
	*/
	public CompanyQualifier fetchByCompanyCrtQualifierByName(
		java.lang.String companyQualifierName, boolean retrieveFromCache);

	/**
	* Removes the company qualifier where companyQualifierName = &#63; from the database.
	*
	* @param companyQualifierName the company qualifier name
	* @return the company qualifier that was removed
	*/
	public CompanyQualifier removeByCompanyCrtQualifierByName(
		java.lang.String companyQualifierName)
		throws NoSuchCompanyQualifierException;

	/**
	* Returns the number of company qualifiers where companyQualifierName = &#63;.
	*
	* @param companyQualifierName the company qualifier name
	* @return the number of matching company qualifiers
	*/
	public int countByCompanyCrtQualifierByName(
		java.lang.String companyQualifierName);

	/**
	* Caches the company qualifier in the entity cache if it is enabled.
	*
	* @param companyQualifier the company qualifier
	*/
	public void cacheResult(CompanyQualifier companyQualifier);

	/**
	* Caches the company qualifiers in the entity cache if it is enabled.
	*
	* @param companyQualifiers the company qualifiers
	*/
	public void cacheResult(java.util.List<CompanyQualifier> companyQualifiers);

	/**
	* Creates a new company qualifier with the primary key. Does not add the company qualifier to the database.
	*
	* @param companyQualifierSid the primary key for the new company qualifier
	* @return the new company qualifier
	*/
	public CompanyQualifier create(int companyQualifierSid);

	/**
	* Removes the company qualifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyQualifierSid the primary key of the company qualifier
	* @return the company qualifier that was removed
	* @throws NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
	*/
	public CompanyQualifier remove(int companyQualifierSid)
		throws NoSuchCompanyQualifierException;

	public CompanyQualifier updateImpl(CompanyQualifier companyQualifier);

	/**
	* Returns the company qualifier with the primary key or throws a {@link NoSuchCompanyQualifierException} if it could not be found.
	*
	* @param companyQualifierSid the primary key of the company qualifier
	* @return the company qualifier
	* @throws NoSuchCompanyQualifierException if a company qualifier with the primary key could not be found
	*/
	public CompanyQualifier findByPrimaryKey(int companyQualifierSid)
		throws NoSuchCompanyQualifierException;

	/**
	* Returns the company qualifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyQualifierSid the primary key of the company qualifier
	* @return the company qualifier, or <code>null</code> if a company qualifier with the primary key could not be found
	*/
	public CompanyQualifier fetchByPrimaryKey(int companyQualifierSid);

	@Override
	public java.util.Map<java.io.Serializable, CompanyQualifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the company qualifiers.
	*
	* @return the company qualifiers
	*/
	public java.util.List<CompanyQualifier> findAll();

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
	public java.util.List<CompanyQualifier> findAll(int start, int end);

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
	public java.util.List<CompanyQualifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyQualifier> orderByComparator);

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
	public java.util.List<CompanyQualifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyQualifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the company qualifiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of company qualifiers.
	*
	* @return the number of company qualifiers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}