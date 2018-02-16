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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.parttwo.exception.NoSuchVwCompanyParentDetailsException;
import com.stpl.app.parttwo.model.VwCompanyParentDetails;

/**
 * The persistence interface for the vw company parent details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.VwCompanyParentDetailsPersistenceImpl
 * @see VwCompanyParentDetailsUtil
 * @generated
 */
@ProviderType
public interface VwCompanyParentDetailsPersistence extends BasePersistence<VwCompanyParentDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwCompanyParentDetailsUtil} to access the vw company parent details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw company parent details in the entity cache if it is enabled.
	*
	* @param vwCompanyParentDetails the vw company parent details
	*/
	public void cacheResult(VwCompanyParentDetails vwCompanyParentDetails);

	/**
	* Caches the vw company parent detailses in the entity cache if it is enabled.
	*
	* @param vwCompanyParentDetailses the vw company parent detailses
	*/
	public void cacheResult(
		java.util.List<VwCompanyParentDetails> vwCompanyParentDetailses);

	/**
	* Creates a new vw company parent details with the primary key. Does not add the vw company parent details to the database.
	*
	* @param companyParentDetailsSid the primary key for the new vw company parent details
	* @return the new vw company parent details
	*/
	public VwCompanyParentDetails create(int companyParentDetailsSid);

	/**
	* Removes the vw company parent details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyParentDetailsSid the primary key of the vw company parent details
	* @return the vw company parent details that was removed
	* @throws NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
	*/
	public VwCompanyParentDetails remove(int companyParentDetailsSid)
		throws NoSuchVwCompanyParentDetailsException;

	public VwCompanyParentDetails updateImpl(
		VwCompanyParentDetails vwCompanyParentDetails);

	/**
	* Returns the vw company parent details with the primary key or throws a {@link NoSuchVwCompanyParentDetailsException} if it could not be found.
	*
	* @param companyParentDetailsSid the primary key of the vw company parent details
	* @return the vw company parent details
	* @throws NoSuchVwCompanyParentDetailsException if a vw company parent details with the primary key could not be found
	*/
	public VwCompanyParentDetails findByPrimaryKey(int companyParentDetailsSid)
		throws NoSuchVwCompanyParentDetailsException;

	/**
	* Returns the vw company parent details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyParentDetailsSid the primary key of the vw company parent details
	* @return the vw company parent details, or <code>null</code> if a vw company parent details with the primary key could not be found
	*/
	public VwCompanyParentDetails fetchByPrimaryKey(int companyParentDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, VwCompanyParentDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw company parent detailses.
	*
	* @return the vw company parent detailses
	*/
	public java.util.List<VwCompanyParentDetails> findAll();

	/**
	* Returns a range of all the vw company parent detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company parent detailses
	* @param end the upper bound of the range of vw company parent detailses (not inclusive)
	* @return the range of vw company parent detailses
	*/
	public java.util.List<VwCompanyParentDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vw company parent detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company parent detailses
	* @param end the upper bound of the range of vw company parent detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw company parent detailses
	*/
	public java.util.List<VwCompanyParentDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwCompanyParentDetails> orderByComparator);

	/**
	* Returns an ordered range of all the vw company parent detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCompanyParentDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw company parent detailses
	* @param end the upper bound of the range of vw company parent detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw company parent detailses
	*/
	public java.util.List<VwCompanyParentDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwCompanyParentDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw company parent detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw company parent detailses.
	*
	* @return the number of vw company parent detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}