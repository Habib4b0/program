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

import com.stpl.app.exception.NoSuchGcmContractDetailsException;
import com.stpl.app.model.GcmContractDetails;

/**
 * The persistence interface for the gcm contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.GcmContractDetailsPersistenceImpl
 * @see GcmContractDetailsUtil
 * @generated
 */
@ProviderType
public interface GcmContractDetailsPersistence extends BasePersistence<GcmContractDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GcmContractDetailsUtil} to access the gcm contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the gcm contract details in the entity cache if it is enabled.
	*
	* @param gcmContractDetails the gcm contract details
	*/
	public void cacheResult(GcmContractDetails gcmContractDetails);

	/**
	* Caches the gcm contract detailses in the entity cache if it is enabled.
	*
	* @param gcmContractDetailses the gcm contract detailses
	*/
	public void cacheResult(
		java.util.List<GcmContractDetails> gcmContractDetailses);

	/**
	* Creates a new gcm contract details with the primary key. Does not add the gcm contract details to the database.
	*
	* @param gcmContractDetailsSid the primary key for the new gcm contract details
	* @return the new gcm contract details
	*/
	public GcmContractDetails create(int gcmContractDetailsSid);

	/**
	* Removes the gcm contract details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmContractDetailsSid the primary key of the gcm contract details
	* @return the gcm contract details that was removed
	* @throws NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
	*/
	public GcmContractDetails remove(int gcmContractDetailsSid)
		throws NoSuchGcmContractDetailsException;

	public GcmContractDetails updateImpl(GcmContractDetails gcmContractDetails);

	/**
	* Returns the gcm contract details with the primary key or throws a {@link NoSuchGcmContractDetailsException} if it could not be found.
	*
	* @param gcmContractDetailsSid the primary key of the gcm contract details
	* @return the gcm contract details
	* @throws NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
	*/
	public GcmContractDetails findByPrimaryKey(int gcmContractDetailsSid)
		throws NoSuchGcmContractDetailsException;

	/**
	* Returns the gcm contract details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gcmContractDetailsSid the primary key of the gcm contract details
	* @return the gcm contract details, or <code>null</code> if a gcm contract details with the primary key could not be found
	*/
	public GcmContractDetails fetchByPrimaryKey(int gcmContractDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, GcmContractDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the gcm contract detailses.
	*
	* @return the gcm contract detailses
	*/
	public java.util.List<GcmContractDetails> findAll();

	/**
	* Returns a range of all the gcm contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm contract detailses
	* @param end the upper bound of the range of gcm contract detailses (not inclusive)
	* @return the range of gcm contract detailses
	*/
	public java.util.List<GcmContractDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the gcm contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm contract detailses
	* @param end the upper bound of the range of gcm contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gcm contract detailses
	*/
	public java.util.List<GcmContractDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GcmContractDetails> orderByComparator);

	/**
	* Returns an ordered range of all the gcm contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm contract detailses
	* @param end the upper bound of the range of gcm contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of gcm contract detailses
	*/
	public java.util.List<GcmContractDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GcmContractDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the gcm contract detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of gcm contract detailses.
	*
	* @return the number of gcm contract detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}