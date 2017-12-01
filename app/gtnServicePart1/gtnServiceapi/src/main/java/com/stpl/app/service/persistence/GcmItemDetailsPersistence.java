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

import com.stpl.app.exception.NoSuchGcmItemDetailsException;
import com.stpl.app.model.GcmItemDetails;

/**
 * The persistence interface for the gcm item details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.GcmItemDetailsPersistenceImpl
 * @see GcmItemDetailsUtil
 * @generated
 */
@ProviderType
public interface GcmItemDetailsPersistence extends BasePersistence<GcmItemDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GcmItemDetailsUtil} to access the gcm item details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the gcm item details in the entity cache if it is enabled.
	*
	* @param gcmItemDetails the gcm item details
	*/
	public void cacheResult(GcmItemDetails gcmItemDetails);

	/**
	* Caches the gcm item detailses in the entity cache if it is enabled.
	*
	* @param gcmItemDetailses the gcm item detailses
	*/
	public void cacheResult(java.util.List<GcmItemDetails> gcmItemDetailses);

	/**
	* Creates a new gcm item details with the primary key. Does not add the gcm item details to the database.
	*
	* @param gcmItemDetailsSid the primary key for the new gcm item details
	* @return the new gcm item details
	*/
	public GcmItemDetails create(int gcmItemDetailsSid);

	/**
	* Removes the gcm item details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmItemDetailsSid the primary key of the gcm item details
	* @return the gcm item details that was removed
	* @throws NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
	*/
	public GcmItemDetails remove(int gcmItemDetailsSid)
		throws NoSuchGcmItemDetailsException;

	public GcmItemDetails updateImpl(GcmItemDetails gcmItemDetails);

	/**
	* Returns the gcm item details with the primary key or throws a {@link NoSuchGcmItemDetailsException} if it could not be found.
	*
	* @param gcmItemDetailsSid the primary key of the gcm item details
	* @return the gcm item details
	* @throws NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
	*/
	public GcmItemDetails findByPrimaryKey(int gcmItemDetailsSid)
		throws NoSuchGcmItemDetailsException;

	/**
	* Returns the gcm item details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gcmItemDetailsSid the primary key of the gcm item details
	* @return the gcm item details, or <code>null</code> if a gcm item details with the primary key could not be found
	*/
	public GcmItemDetails fetchByPrimaryKey(int gcmItemDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, GcmItemDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the gcm item detailses.
	*
	* @return the gcm item detailses
	*/
	public java.util.List<GcmItemDetails> findAll();

	/**
	* Returns a range of all the gcm item detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm item detailses
	* @param end the upper bound of the range of gcm item detailses (not inclusive)
	* @return the range of gcm item detailses
	*/
	public java.util.List<GcmItemDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the gcm item detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm item detailses
	* @param end the upper bound of the range of gcm item detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gcm item detailses
	*/
	public java.util.List<GcmItemDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GcmItemDetails> orderByComparator);

	/**
	* Returns an ordered range of all the gcm item detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm item detailses
	* @param end the upper bound of the range of gcm item detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of gcm item detailses
	*/
	public java.util.List<GcmItemDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GcmItemDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the gcm item detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of gcm item detailses.
	*
	* @return the number of gcm item detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}