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

import com.stpl.app.parttwo.exception.NoSuchCffDetailsException;
import com.stpl.app.parttwo.model.CffDetails;

/**
 * The persistence interface for the cff details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.CffDetailsPersistenceImpl
 * @see CffDetailsUtil
 * @generated
 */
@ProviderType
public interface CffDetailsPersistence extends BasePersistence<CffDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CffDetailsUtil} to access the cff details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cff details in the entity cache if it is enabled.
	*
	* @param cffDetails the cff details
	*/
	public void cacheResult(CffDetails cffDetails);

	/**
	* Caches the cff detailses in the entity cache if it is enabled.
	*
	* @param cffDetailses the cff detailses
	*/
	public void cacheResult(java.util.List<CffDetails> cffDetailses);

	/**
	* Creates a new cff details with the primary key. Does not add the cff details to the database.
	*
	* @param cffDetailsSid the primary key for the new cff details
	* @return the new cff details
	*/
	public CffDetails create(int cffDetailsSid);

	/**
	* Removes the cff details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffDetailsSid the primary key of the cff details
	* @return the cff details that was removed
	* @throws NoSuchCffDetailsException if a cff details with the primary key could not be found
	*/
	public CffDetails remove(int cffDetailsSid)
		throws NoSuchCffDetailsException;

	public CffDetails updateImpl(CffDetails cffDetails);

	/**
	* Returns the cff details with the primary key or throws a {@link NoSuchCffDetailsException} if it could not be found.
	*
	* @param cffDetailsSid the primary key of the cff details
	* @return the cff details
	* @throws NoSuchCffDetailsException if a cff details with the primary key could not be found
	*/
	public CffDetails findByPrimaryKey(int cffDetailsSid)
		throws NoSuchCffDetailsException;

	/**
	* Returns the cff details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffDetailsSid the primary key of the cff details
	* @return the cff details, or <code>null</code> if a cff details with the primary key could not be found
	*/
	public CffDetails fetchByPrimaryKey(int cffDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, CffDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cff detailses.
	*
	* @return the cff detailses
	*/
	public java.util.List<CffDetails> findAll();

	/**
	* Returns a range of all the cff detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff detailses
	* @param end the upper bound of the range of cff detailses (not inclusive)
	* @return the range of cff detailses
	*/
	public java.util.List<CffDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cff detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff detailses
	* @param end the upper bound of the range of cff detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff detailses
	*/
	public java.util.List<CffDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffDetails> orderByComparator);

	/**
	* Returns an ordered range of all the cff detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff detailses
	* @param end the upper bound of the range of cff detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff detailses
	*/
	public java.util.List<CffDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cff detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cff detailses.
	*
	* @return the number of cff detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}