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

import com.stpl.app.parttwo.exception.NoSuchCffDocDetailsException;
import com.stpl.app.parttwo.model.CffDocDetails;

/**
 * The persistence interface for the cff doc details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.CffDocDetailsPersistenceImpl
 * @see CffDocDetailsUtil
 * @generated
 */
@ProviderType
public interface CffDocDetailsPersistence extends BasePersistence<CffDocDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CffDocDetailsUtil} to access the cff doc details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cff doc details in the entity cache if it is enabled.
	*
	* @param cffDocDetails the cff doc details
	*/
	public void cacheResult(CffDocDetails cffDocDetails);

	/**
	* Caches the cff doc detailses in the entity cache if it is enabled.
	*
	* @param cffDocDetailses the cff doc detailses
	*/
	public void cacheResult(java.util.List<CffDocDetails> cffDocDetailses);

	/**
	* Creates a new cff doc details with the primary key. Does not add the cff doc details to the database.
	*
	* @param cffDocDetailsSid the primary key for the new cff doc details
	* @return the new cff doc details
	*/
	public CffDocDetails create(int cffDocDetailsSid);

	/**
	* Removes the cff doc details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffDocDetailsSid the primary key of the cff doc details
	* @return the cff doc details that was removed
	* @throws NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
	*/
	public CffDocDetails remove(int cffDocDetailsSid)
		throws NoSuchCffDocDetailsException;

	public CffDocDetails updateImpl(CffDocDetails cffDocDetails);

	/**
	* Returns the cff doc details with the primary key or throws a {@link NoSuchCffDocDetailsException} if it could not be found.
	*
	* @param cffDocDetailsSid the primary key of the cff doc details
	* @return the cff doc details
	* @throws NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
	*/
	public CffDocDetails findByPrimaryKey(int cffDocDetailsSid)
		throws NoSuchCffDocDetailsException;

	/**
	* Returns the cff doc details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffDocDetailsSid the primary key of the cff doc details
	* @return the cff doc details, or <code>null</code> if a cff doc details with the primary key could not be found
	*/
	public CffDocDetails fetchByPrimaryKey(int cffDocDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, CffDocDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cff doc detailses.
	*
	* @return the cff doc detailses
	*/
	public java.util.List<CffDocDetails> findAll();

	/**
	* Returns a range of all the cff doc detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff doc detailses
	* @param end the upper bound of the range of cff doc detailses (not inclusive)
	* @return the range of cff doc detailses
	*/
	public java.util.List<CffDocDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cff doc detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff doc detailses
	* @param end the upper bound of the range of cff doc detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff doc detailses
	*/
	public java.util.List<CffDocDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffDocDetails> orderByComparator);

	/**
	* Returns an ordered range of all the cff doc detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff doc detailses
	* @param end the upper bound of the range of cff doc detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff doc detailses
	*/
	public java.util.List<CffDocDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffDocDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cff doc detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cff doc detailses.
	*
	* @return the number of cff doc detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}