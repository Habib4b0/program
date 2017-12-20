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

import com.stpl.app.exception.NoSuchRsDetailsException;
import com.stpl.app.model.RsDetails;

/**
 * The persistence interface for the rs details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.RsDetailsPersistenceImpl
 * @see RsDetailsUtil
 * @generated
 */
@ProviderType
public interface RsDetailsPersistence extends BasePersistence<RsDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RsDetailsUtil} to access the rs details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the rs details in the entity cache if it is enabled.
	*
	* @param rsDetails the rs details
	*/
	public void cacheResult(RsDetails rsDetails);

	/**
	* Caches the rs detailses in the entity cache if it is enabled.
	*
	* @param rsDetailses the rs detailses
	*/
	public void cacheResult(java.util.List<RsDetails> rsDetailses);

	/**
	* Creates a new rs details with the primary key. Does not add the rs details to the database.
	*
	* @param rsDetailsSid the primary key for the new rs details
	* @return the new rs details
	*/
	public RsDetails create(int rsDetailsSid);

	/**
	* Removes the rs details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param rsDetailsSid the primary key of the rs details
	* @return the rs details that was removed
	* @throws NoSuchRsDetailsException if a rs details with the primary key could not be found
	*/
	public RsDetails remove(int rsDetailsSid) throws NoSuchRsDetailsException;

	public RsDetails updateImpl(RsDetails rsDetails);

	/**
	* Returns the rs details with the primary key or throws a {@link NoSuchRsDetailsException} if it could not be found.
	*
	* @param rsDetailsSid the primary key of the rs details
	* @return the rs details
	* @throws NoSuchRsDetailsException if a rs details with the primary key could not be found
	*/
	public RsDetails findByPrimaryKey(int rsDetailsSid)
		throws NoSuchRsDetailsException;

	/**
	* Returns the rs details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param rsDetailsSid the primary key of the rs details
	* @return the rs details, or <code>null</code> if a rs details with the primary key could not be found
	*/
	public RsDetails fetchByPrimaryKey(int rsDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, RsDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the rs detailses.
	*
	* @return the rs detailses
	*/
	public java.util.List<RsDetails> findAll();

	/**
	* Returns a range of all the rs detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs detailses
	* @param end the upper bound of the range of rs detailses (not inclusive)
	* @return the range of rs detailses
	*/
	public java.util.List<RsDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the rs detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs detailses
	* @param end the upper bound of the range of rs detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of rs detailses
	*/
	public java.util.List<RsDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsDetails> orderByComparator);

	/**
	* Returns an ordered range of all the rs detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of rs detailses
	* @param end the upper bound of the range of rs detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of rs detailses
	*/
	public java.util.List<RsDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RsDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the rs detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of rs detailses.
	*
	* @return the number of rs detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}