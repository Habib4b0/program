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

import com.stpl.app.parttwo.exception.NoSuchStAccClosureDetailsException;
import com.stpl.app.parttwo.model.StAccClosureDetails;

/**
 * The persistence interface for the st acc closure details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.StAccClosureDetailsPersistenceImpl
 * @see StAccClosureDetailsUtil
 * @generated
 */
@ProviderType
public interface StAccClosureDetailsPersistence extends BasePersistence<StAccClosureDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StAccClosureDetailsUtil} to access the st acc closure details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st acc closure details in the entity cache if it is enabled.
	*
	* @param stAccClosureDetails the st acc closure details
	*/
	public void cacheResult(StAccClosureDetails stAccClosureDetails);

	/**
	* Caches the st acc closure detailses in the entity cache if it is enabled.
	*
	* @param stAccClosureDetailses the st acc closure detailses
	*/
	public void cacheResult(
		java.util.List<StAccClosureDetails> stAccClosureDetailses);

	/**
	* Creates a new st acc closure details with the primary key. Does not add the st acc closure details to the database.
	*
	* @param accClosureMasterSid the primary key for the new st acc closure details
	* @return the new st acc closure details
	*/
	public StAccClosureDetails create(int accClosureMasterSid);

	/**
	* Removes the st acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureMasterSid the primary key of the st acc closure details
	* @return the st acc closure details that was removed
	* @throws NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
	*/
	public StAccClosureDetails remove(int accClosureMasterSid)
		throws NoSuchStAccClosureDetailsException;

	public StAccClosureDetails updateImpl(
		StAccClosureDetails stAccClosureDetails);

	/**
	* Returns the st acc closure details with the primary key or throws a {@link NoSuchStAccClosureDetailsException} if it could not be found.
	*
	* @param accClosureMasterSid the primary key of the st acc closure details
	* @return the st acc closure details
	* @throws NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
	*/
	public StAccClosureDetails findByPrimaryKey(int accClosureMasterSid)
		throws NoSuchStAccClosureDetailsException;

	/**
	* Returns the st acc closure details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accClosureMasterSid the primary key of the st acc closure details
	* @return the st acc closure details, or <code>null</code> if a st acc closure details with the primary key could not be found
	*/
	public StAccClosureDetails fetchByPrimaryKey(int accClosureMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, StAccClosureDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st acc closure detailses.
	*
	* @return the st acc closure detailses
	*/
	public java.util.List<StAccClosureDetails> findAll();

	/**
	* Returns a range of all the st acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st acc closure detailses
	* @param end the upper bound of the range of st acc closure detailses (not inclusive)
	* @return the range of st acc closure detailses
	*/
	public java.util.List<StAccClosureDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st acc closure detailses
	* @param end the upper bound of the range of st acc closure detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st acc closure detailses
	*/
	public java.util.List<StAccClosureDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StAccClosureDetails> orderByComparator);

	/**
	* Returns an ordered range of all the st acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st acc closure detailses
	* @param end the upper bound of the range of st acc closure detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st acc closure detailses
	*/
	public java.util.List<StAccClosureDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StAccClosureDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st acc closure detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st acc closure detailses.
	*
	* @return the number of st acc closure detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}