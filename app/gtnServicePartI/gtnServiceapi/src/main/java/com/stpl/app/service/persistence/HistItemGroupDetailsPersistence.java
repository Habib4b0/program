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

import com.stpl.app.exception.NoSuchHistItemGroupDetailsException;
import com.stpl.app.model.HistItemGroupDetails;

/**
 * The persistence interface for the hist item group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.HistItemGroupDetailsPersistenceImpl
 * @see HistItemGroupDetailsUtil
 * @generated
 */
@ProviderType
public interface HistItemGroupDetailsPersistence extends BasePersistence<HistItemGroupDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HistItemGroupDetailsUtil} to access the hist item group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the hist item group details in the entity cache if it is enabled.
	*
	* @param histItemGroupDetails the hist item group details
	*/
	public void cacheResult(HistItemGroupDetails histItemGroupDetails);

	/**
	* Caches the hist item group detailses in the entity cache if it is enabled.
	*
	* @param histItemGroupDetailses the hist item group detailses
	*/
	public void cacheResult(
		java.util.List<HistItemGroupDetails> histItemGroupDetailses);

	/**
	* Creates a new hist item group details with the primary key. Does not add the hist item group details to the database.
	*
	* @param histItemGroupDetailsPK the primary key for the new hist item group details
	* @return the new hist item group details
	*/
	public HistItemGroupDetails create(
		HistItemGroupDetailsPK histItemGroupDetailsPK);

	/**
	* Removes the hist item group details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histItemGroupDetailsPK the primary key of the hist item group details
	* @return the hist item group details that was removed
	* @throws NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
	*/
	public HistItemGroupDetails remove(
		HistItemGroupDetailsPK histItemGroupDetailsPK)
		throws NoSuchHistItemGroupDetailsException;

	public HistItemGroupDetails updateImpl(
		HistItemGroupDetails histItemGroupDetails);

	/**
	* Returns the hist item group details with the primary key or throws a {@link NoSuchHistItemGroupDetailsException} if it could not be found.
	*
	* @param histItemGroupDetailsPK the primary key of the hist item group details
	* @return the hist item group details
	* @throws NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
	*/
	public HistItemGroupDetails findByPrimaryKey(
		HistItemGroupDetailsPK histItemGroupDetailsPK)
		throws NoSuchHistItemGroupDetailsException;

	/**
	* Returns the hist item group details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param histItemGroupDetailsPK the primary key of the hist item group details
	* @return the hist item group details, or <code>null</code> if a hist item group details with the primary key could not be found
	*/
	public HistItemGroupDetails fetchByPrimaryKey(
		HistItemGroupDetailsPK histItemGroupDetailsPK);

	@Override
	public java.util.Map<java.io.Serializable, HistItemGroupDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the hist item group detailses.
	*
	* @return the hist item group detailses
	*/
	public java.util.List<HistItemGroupDetails> findAll();

	/**
	* Returns a range of all the hist item group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist item group detailses
	* @param end the upper bound of the range of hist item group detailses (not inclusive)
	* @return the range of hist item group detailses
	*/
	public java.util.List<HistItemGroupDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the hist item group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist item group detailses
	* @param end the upper bound of the range of hist item group detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist item group detailses
	*/
	public java.util.List<HistItemGroupDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistItemGroupDetails> orderByComparator);

	/**
	* Returns an ordered range of all the hist item group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist item group detailses
	* @param end the upper bound of the range of hist item group detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist item group detailses
	*/
	public java.util.List<HistItemGroupDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistItemGroupDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the hist item group detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of hist item group detailses.
	*
	* @return the number of hist item group detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}