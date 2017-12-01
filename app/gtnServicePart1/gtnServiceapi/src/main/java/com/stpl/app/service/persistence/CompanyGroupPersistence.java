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

import com.stpl.app.exception.NoSuchCompanyGroupException;
import com.stpl.app.model.CompanyGroup;

/**
 * The persistence interface for the company group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CompanyGroupPersistenceImpl
 * @see CompanyGroupUtil
 * @generated
 */
@ProviderType
public interface CompanyGroupPersistence extends BasePersistence<CompanyGroup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CompanyGroupUtil} to access the company group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the company group in the entity cache if it is enabled.
	*
	* @param companyGroup the company group
	*/
	public void cacheResult(CompanyGroup companyGroup);

	/**
	* Caches the company groups in the entity cache if it is enabled.
	*
	* @param companyGroups the company groups
	*/
	public void cacheResult(java.util.List<CompanyGroup> companyGroups);

	/**
	* Creates a new company group with the primary key. Does not add the company group to the database.
	*
	* @param companyGroupSid the primary key for the new company group
	* @return the new company group
	*/
	public CompanyGroup create(int companyGroupSid);

	/**
	* Removes the company group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyGroupSid the primary key of the company group
	* @return the company group that was removed
	* @throws NoSuchCompanyGroupException if a company group with the primary key could not be found
	*/
	public CompanyGroup remove(int companyGroupSid)
		throws NoSuchCompanyGroupException;

	public CompanyGroup updateImpl(CompanyGroup companyGroup);

	/**
	* Returns the company group with the primary key or throws a {@link NoSuchCompanyGroupException} if it could not be found.
	*
	* @param companyGroupSid the primary key of the company group
	* @return the company group
	* @throws NoSuchCompanyGroupException if a company group with the primary key could not be found
	*/
	public CompanyGroup findByPrimaryKey(int companyGroupSid)
		throws NoSuchCompanyGroupException;

	/**
	* Returns the company group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyGroupSid the primary key of the company group
	* @return the company group, or <code>null</code> if a company group with the primary key could not be found
	*/
	public CompanyGroup fetchByPrimaryKey(int companyGroupSid);

	@Override
	public java.util.Map<java.io.Serializable, CompanyGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the company groups.
	*
	* @return the company groups
	*/
	public java.util.List<CompanyGroup> findAll();

	/**
	* Returns a range of all the company groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company groups
	* @param end the upper bound of the range of company groups (not inclusive)
	* @return the range of company groups
	*/
	public java.util.List<CompanyGroup> findAll(int start, int end);

	/**
	* Returns an ordered range of all the company groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company groups
	* @param end the upper bound of the range of company groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of company groups
	*/
	public java.util.List<CompanyGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyGroup> orderByComparator);

	/**
	* Returns an ordered range of all the company groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of company groups
	* @param end the upper bound of the range of company groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of company groups
	*/
	public java.util.List<CompanyGroup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyGroup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the company groups from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of company groups.
	*
	* @return the number of company groups
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}