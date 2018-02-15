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

import com.stpl.app.exception.NoSuchCompanyParentDetailsException;
import com.stpl.app.model.CompanyParentDetails;

/**
 * The persistence interface for the company parent details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CompanyParentDetailsPersistenceImpl
 * @see CompanyParentDetailsUtil
 * @generated
 */
@ProviderType
public interface CompanyParentDetailsPersistence extends BasePersistence<CompanyParentDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CompanyParentDetailsUtil} to access the company parent details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the company parent details in the entity cache if it is enabled.
	*
	* @param companyParentDetails the company parent details
	*/
	public void cacheResult(CompanyParentDetails companyParentDetails);

	/**
	* Caches the company parent detailses in the entity cache if it is enabled.
	*
	* @param companyParentDetailses the company parent detailses
	*/
	public void cacheResult(
		java.util.List<CompanyParentDetails> companyParentDetailses);

	/**
	* Creates a new company parent details with the primary key. Does not add the company parent details to the database.
	*
	* @param companyParentDetailsSid the primary key for the new company parent details
	* @return the new company parent details
	*/
	public CompanyParentDetails create(int companyParentDetailsSid);

	/**
	* Removes the company parent details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyParentDetailsSid the primary key of the company parent details
	* @return the company parent details that was removed
	* @throws NoSuchCompanyParentDetailsException if a company parent details with the primary key could not be found
	*/
	public CompanyParentDetails remove(int companyParentDetailsSid)
		throws NoSuchCompanyParentDetailsException;

	public CompanyParentDetails updateImpl(
		CompanyParentDetails companyParentDetails);

	/**
	* Returns the company parent details with the primary key or throws a {@link NoSuchCompanyParentDetailsException} if it could not be found.
	*
	* @param companyParentDetailsSid the primary key of the company parent details
	* @return the company parent details
	* @throws NoSuchCompanyParentDetailsException if a company parent details with the primary key could not be found
	*/
	public CompanyParentDetails findByPrimaryKey(int companyParentDetailsSid)
		throws NoSuchCompanyParentDetailsException;

	/**
	* Returns the company parent details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyParentDetailsSid the primary key of the company parent details
	* @return the company parent details, or <code>null</code> if a company parent details with the primary key could not be found
	*/
	public CompanyParentDetails fetchByPrimaryKey(int companyParentDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, CompanyParentDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the company parent detailses.
	*
	* @return the company parent detailses
	*/
	public java.util.List<CompanyParentDetails> findAll();

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
	public java.util.List<CompanyParentDetails> findAll(int start, int end);

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
	public java.util.List<CompanyParentDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyParentDetails> orderByComparator);

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
	public java.util.List<CompanyParentDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CompanyParentDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the company parent detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of company parent detailses.
	*
	* @return the number of company parent detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}