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

import com.stpl.app.exception.NoSuchCompanyGroupDetailsException;
import com.stpl.app.model.CompanyGroupDetails;

/**
 * The persistence interface for the company group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CompanyGroupDetailsPersistenceImpl
 * @see CompanyGroupDetailsUtil
 * @generated
 */
@ProviderType
public interface CompanyGroupDetailsPersistence extends BasePersistence<CompanyGroupDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CompanyGroupDetailsUtil} to access the company group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the company group details in the entity cache if it is enabled.
	*
	* @param companyGroupDetails the company group details
	*/
	public void cacheResult(CompanyGroupDetails companyGroupDetails);

	/**
	* Caches the company group detailses in the entity cache if it is enabled.
	*
	* @param companyGroupDetailses the company group detailses
	*/
	public void cacheResult(
		java.util.List<CompanyGroupDetails> companyGroupDetailses);

	/**
	* Creates a new company group details with the primary key. Does not add the company group details to the database.
	*
	* @param companyGroupDetailsSid the primary key for the new company group details
	* @return the new company group details
	*/
	public CompanyGroupDetails create(int companyGroupDetailsSid);

	/**
	* Removes the company group details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyGroupDetailsSid the primary key of the company group details
	* @return the company group details that was removed
	* @throws NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
	*/
	public CompanyGroupDetails remove(int companyGroupDetailsSid)
		throws NoSuchCompanyGroupDetailsException;

	public CompanyGroupDetails updateImpl(
		CompanyGroupDetails companyGroupDetails);

	/**
	* Returns the company group details with the primary key or throws a {@link NoSuchCompanyGroupDetailsException} if it could not be found.
	*
	* @param companyGroupDetailsSid the primary key of the company group details
	* @return the company group details
	* @throws NoSuchCompanyGroupDetailsException if a company group details with the primary key could not be found
	*/
	public CompanyGroupDetails findByPrimaryKey(int companyGroupDetailsSid)
		throws NoSuchCompanyGroupDetailsException;

	/**
	* Returns the company group details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyGroupDetailsSid the primary key of the company group details
	* @return the company group details, or <code>null</code> if a company group details with the primary key could not be found
	*/
	public CompanyGroupDetails fetchByPrimaryKey(int companyGroupDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, CompanyGroupDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the company group detailses.
	*
	* @return the company group detailses
	*/
	public java.util.List<CompanyGroupDetails> findAll();

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
	public java.util.List<CompanyGroupDetails> findAll(int start, int end);

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
	public java.util.List<CompanyGroupDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyGroupDetails> orderByComparator);

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
	public java.util.List<CompanyGroupDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyGroupDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the company group detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of company group detailses.
	*
	* @return the number of company group detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}