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

import com.stpl.app.parttwo.exception.NoSuchAccClosureDetailsException;
import com.stpl.app.parttwo.model.AccClosureDetails;

/**
 * The persistence interface for the acc closure details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.AccClosureDetailsPersistenceImpl
 * @see AccClosureDetailsUtil
 * @generated
 */
@ProviderType
public interface AccClosureDetailsPersistence extends BasePersistence<AccClosureDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccClosureDetailsUtil} to access the acc closure details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the acc closure details in the entity cache if it is enabled.
	*
	* @param accClosureDetails the acc closure details
	*/
	public void cacheResult(AccClosureDetails accClosureDetails);

	/**
	* Caches the acc closure detailses in the entity cache if it is enabled.
	*
	* @param accClosureDetailses the acc closure detailses
	*/
	public void cacheResult(
		java.util.List<AccClosureDetails> accClosureDetailses);

	/**
	* Creates a new acc closure details with the primary key. Does not add the acc closure details to the database.
	*
	* @param accClosureDetailsSid the primary key for the new acc closure details
	* @return the new acc closure details
	*/
	public AccClosureDetails create(int accClosureDetailsSid);

	/**
	* Removes the acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureDetailsSid the primary key of the acc closure details
	* @return the acc closure details that was removed
	* @throws NoSuchAccClosureDetailsException if a acc closure details with the primary key could not be found
	*/
	public AccClosureDetails remove(int accClosureDetailsSid)
		throws NoSuchAccClosureDetailsException;

	public AccClosureDetails updateImpl(AccClosureDetails accClosureDetails);

	/**
	* Returns the acc closure details with the primary key or throws a {@link NoSuchAccClosureDetailsException} if it could not be found.
	*
	* @param accClosureDetailsSid the primary key of the acc closure details
	* @return the acc closure details
	* @throws NoSuchAccClosureDetailsException if a acc closure details with the primary key could not be found
	*/
	public AccClosureDetails findByPrimaryKey(int accClosureDetailsSid)
		throws NoSuchAccClosureDetailsException;

	/**
	* Returns the acc closure details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accClosureDetailsSid the primary key of the acc closure details
	* @return the acc closure details, or <code>null</code> if a acc closure details with the primary key could not be found
	*/
	public AccClosureDetails fetchByPrimaryKey(int accClosureDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, AccClosureDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the acc closure detailses.
	*
	* @return the acc closure detailses
	*/
	public java.util.List<AccClosureDetails> findAll();

	/**
	* Returns a range of all the acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure detailses
	* @param end the upper bound of the range of acc closure detailses (not inclusive)
	* @return the range of acc closure detailses
	*/
	public java.util.List<AccClosureDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure detailses
	* @param end the upper bound of the range of acc closure detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of acc closure detailses
	*/
	public java.util.List<AccClosureDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccClosureDetails> orderByComparator);

	/**
	* Returns an ordered range of all the acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of acc closure detailses
	* @param end the upper bound of the range of acc closure detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of acc closure detailses
	*/
	public java.util.List<AccClosureDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccClosureDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the acc closure detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of acc closure detailses.
	*
	* @return the number of acc closure detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}