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

import com.stpl.app.exception.NoSuchIvldReturnsException;
import com.stpl.app.model.IvldReturns;

/**
 * The persistence interface for the ivld returns service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldReturnsPersistenceImpl
 * @see IvldReturnsUtil
 * @generated
 */
@ProviderType
public interface IvldReturnsPersistence extends BasePersistence<IvldReturns> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldReturnsUtil} to access the ivld returns persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld returns in the entity cache if it is enabled.
	*
	* @param ivldReturns the ivld returns
	*/
	public void cacheResult(IvldReturns ivldReturns);

	/**
	* Caches the ivld returnses in the entity cache if it is enabled.
	*
	* @param ivldReturnses the ivld returnses
	*/
	public void cacheResult(java.util.List<IvldReturns> ivldReturnses);

	/**
	* Creates a new ivld returns with the primary key. Does not add the ivld returns to the database.
	*
	* @param ivldReturnsSid the primary key for the new ivld returns
	* @return the new ivld returns
	*/
	public IvldReturns create(int ivldReturnsSid);

	/**
	* Removes the ivld returns with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldReturnsSid the primary key of the ivld returns
	* @return the ivld returns that was removed
	* @throws NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
	*/
	public IvldReturns remove(int ivldReturnsSid)
		throws NoSuchIvldReturnsException;

	public IvldReturns updateImpl(IvldReturns ivldReturns);

	/**
	* Returns the ivld returns with the primary key or throws a {@link NoSuchIvldReturnsException} if it could not be found.
	*
	* @param ivldReturnsSid the primary key of the ivld returns
	* @return the ivld returns
	* @throws NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
	*/
	public IvldReturns findByPrimaryKey(int ivldReturnsSid)
		throws NoSuchIvldReturnsException;

	/**
	* Returns the ivld returns with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldReturnsSid the primary key of the ivld returns
	* @return the ivld returns, or <code>null</code> if a ivld returns with the primary key could not be found
	*/
	public IvldReturns fetchByPrimaryKey(int ivldReturnsSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldReturns> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld returnses.
	*
	* @return the ivld returnses
	*/
	public java.util.List<IvldReturns> findAll();

	/**
	* Returns a range of all the ivld returnses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld returnses
	* @param end the upper bound of the range of ivld returnses (not inclusive)
	* @return the range of ivld returnses
	*/
	public java.util.List<IvldReturns> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld returnses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld returnses
	* @param end the upper bound of the range of ivld returnses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld returnses
	*/
	public java.util.List<IvldReturns> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldReturns> orderByComparator);

	/**
	* Returns an ordered range of all the ivld returnses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld returnses
	* @param end the upper bound of the range of ivld returnses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld returnses
	*/
	public java.util.List<IvldReturns> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldReturns> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld returnses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld returnses.
	*
	* @return the number of ivld returnses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}