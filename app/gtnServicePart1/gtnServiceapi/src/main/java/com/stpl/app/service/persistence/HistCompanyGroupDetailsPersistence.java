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

import com.stpl.app.exception.NoSuchHistCompanyGroupDetailsException;
import com.stpl.app.model.HistCompanyGroupDetails;

/**
 * The persistence interface for the hist company group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.HistCompanyGroupDetailsPersistenceImpl
 * @see HistCompanyGroupDetailsUtil
 * @generated
 */
@ProviderType
public interface HistCompanyGroupDetailsPersistence extends BasePersistence<HistCompanyGroupDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HistCompanyGroupDetailsUtil} to access the hist company group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the hist company group details in the entity cache if it is enabled.
	*
	* @param histCompanyGroupDetails the hist company group details
	*/
	public void cacheResult(HistCompanyGroupDetails histCompanyGroupDetails);

	/**
	* Caches the hist company group detailses in the entity cache if it is enabled.
	*
	* @param histCompanyGroupDetailses the hist company group detailses
	*/
	public void cacheResult(
		java.util.List<HistCompanyGroupDetails> histCompanyGroupDetailses);

	/**
	* Creates a new hist company group details with the primary key. Does not add the hist company group details to the database.
	*
	* @param histCompanyGroupDetailsPK the primary key for the new hist company group details
	* @return the new hist company group details
	*/
	public HistCompanyGroupDetails create(
		HistCompanyGroupDetailsPK histCompanyGroupDetailsPK);

	/**
	* Removes the hist company group details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param histCompanyGroupDetailsPK the primary key of the hist company group details
	* @return the hist company group details that was removed
	* @throws NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
	*/
	public HistCompanyGroupDetails remove(
		HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
		throws NoSuchHistCompanyGroupDetailsException;

	public HistCompanyGroupDetails updateImpl(
		HistCompanyGroupDetails histCompanyGroupDetails);

	/**
	* Returns the hist company group details with the primary key or throws a {@link NoSuchHistCompanyGroupDetailsException} if it could not be found.
	*
	* @param histCompanyGroupDetailsPK the primary key of the hist company group details
	* @return the hist company group details
	* @throws NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
	*/
	public HistCompanyGroupDetails findByPrimaryKey(
		HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
		throws NoSuchHistCompanyGroupDetailsException;

	/**
	* Returns the hist company group details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param histCompanyGroupDetailsPK the primary key of the hist company group details
	* @return the hist company group details, or <code>null</code> if a hist company group details with the primary key could not be found
	*/
	public HistCompanyGroupDetails fetchByPrimaryKey(
		HistCompanyGroupDetailsPK histCompanyGroupDetailsPK);

	@Override
	public java.util.Map<java.io.Serializable, HistCompanyGroupDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the hist company group detailses.
	*
	* @return the hist company group detailses
	*/
	public java.util.List<HistCompanyGroupDetails> findAll();

	/**
	* Returns a range of all the hist company group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist company group detailses
	* @param end the upper bound of the range of hist company group detailses (not inclusive)
	* @return the range of hist company group detailses
	*/
	public java.util.List<HistCompanyGroupDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the hist company group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist company group detailses
	* @param end the upper bound of the range of hist company group detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist company group detailses
	*/
	public java.util.List<HistCompanyGroupDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistCompanyGroupDetails> orderByComparator);

	/**
	* Returns an ordered range of all the hist company group detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist company group detailses
	* @param end the upper bound of the range of hist company group detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist company group detailses
	*/
	public java.util.List<HistCompanyGroupDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistCompanyGroupDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the hist company group detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of hist company group detailses.
	*
	* @return the number of hist company group detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}