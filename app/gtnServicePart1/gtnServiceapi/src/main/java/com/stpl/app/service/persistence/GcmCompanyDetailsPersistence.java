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

import com.stpl.app.exception.NoSuchGcmCompanyDetailsException;
import com.stpl.app.model.GcmCompanyDetails;

/**
 * The persistence interface for the gcm company details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.GcmCompanyDetailsPersistenceImpl
 * @see GcmCompanyDetailsUtil
 * @generated
 */
@ProviderType
public interface GcmCompanyDetailsPersistence extends BasePersistence<GcmCompanyDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GcmCompanyDetailsUtil} to access the gcm company details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the gcm company details in the entity cache if it is enabled.
	*
	* @param gcmCompanyDetails the gcm company details
	*/
	public void cacheResult(GcmCompanyDetails gcmCompanyDetails);

	/**
	* Caches the gcm company detailses in the entity cache if it is enabled.
	*
	* @param gcmCompanyDetailses the gcm company detailses
	*/
	public void cacheResult(
		java.util.List<GcmCompanyDetails> gcmCompanyDetailses);

	/**
	* Creates a new gcm company details with the primary key. Does not add the gcm company details to the database.
	*
	* @param gcmCompanyDetailsSid the primary key for the new gcm company details
	* @return the new gcm company details
	*/
	public GcmCompanyDetails create(int gcmCompanyDetailsSid);

	/**
	* Removes the gcm company details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmCompanyDetailsSid the primary key of the gcm company details
	* @return the gcm company details that was removed
	* @throws NoSuchGcmCompanyDetailsException if a gcm company details with the primary key could not be found
	*/
	public GcmCompanyDetails remove(int gcmCompanyDetailsSid)
		throws NoSuchGcmCompanyDetailsException;

	public GcmCompanyDetails updateImpl(GcmCompanyDetails gcmCompanyDetails);

	/**
	* Returns the gcm company details with the primary key or throws a {@link NoSuchGcmCompanyDetailsException} if it could not be found.
	*
	* @param gcmCompanyDetailsSid the primary key of the gcm company details
	* @return the gcm company details
	* @throws NoSuchGcmCompanyDetailsException if a gcm company details with the primary key could not be found
	*/
	public GcmCompanyDetails findByPrimaryKey(int gcmCompanyDetailsSid)
		throws NoSuchGcmCompanyDetailsException;

	/**
	* Returns the gcm company details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gcmCompanyDetailsSid the primary key of the gcm company details
	* @return the gcm company details, or <code>null</code> if a gcm company details with the primary key could not be found
	*/
	public GcmCompanyDetails fetchByPrimaryKey(int gcmCompanyDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, GcmCompanyDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the gcm company detailses.
	*
	* @return the gcm company detailses
	*/
	public java.util.List<GcmCompanyDetails> findAll();

	/**
	* Returns a range of all the gcm company detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmCompanyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm company detailses
	* @param end the upper bound of the range of gcm company detailses (not inclusive)
	* @return the range of gcm company detailses
	*/
	public java.util.List<GcmCompanyDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the gcm company detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmCompanyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm company detailses
	* @param end the upper bound of the range of gcm company detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gcm company detailses
	*/
	public java.util.List<GcmCompanyDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GcmCompanyDetails> orderByComparator);

	/**
	* Returns an ordered range of all the gcm company detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmCompanyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm company detailses
	* @param end the upper bound of the range of gcm company detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of gcm company detailses
	*/
	public java.util.List<GcmCompanyDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GcmCompanyDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the gcm company detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of gcm company detailses.
	*
	* @return the number of gcm company detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}