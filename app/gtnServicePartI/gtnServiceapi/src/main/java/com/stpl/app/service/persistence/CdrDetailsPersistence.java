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

import com.stpl.app.exception.NoSuchCdrDetailsException;
import com.stpl.app.model.CdrDetails;

/**
 * The persistence interface for the cdr details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CdrDetailsPersistenceImpl
 * @see CdrDetailsUtil
 * @generated
 */
@ProviderType
public interface CdrDetailsPersistence extends BasePersistence<CdrDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CdrDetailsUtil} to access the cdr details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cdr details in the entity cache if it is enabled.
	*
	* @param cdrDetails the cdr details
	*/
	public void cacheResult(CdrDetails cdrDetails);

	/**
	* Caches the cdr detailses in the entity cache if it is enabled.
	*
	* @param cdrDetailses the cdr detailses
	*/
	public void cacheResult(java.util.List<CdrDetails> cdrDetailses);

	/**
	* Creates a new cdr details with the primary key. Does not add the cdr details to the database.
	*
	* @param cdrDetailsSid the primary key for the new cdr details
	* @return the new cdr details
	*/
	public CdrDetails create(int cdrDetailsSid);

	/**
	* Removes the cdr details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cdrDetailsSid the primary key of the cdr details
	* @return the cdr details that was removed
	* @throws NoSuchCdrDetailsException if a cdr details with the primary key could not be found
	*/
	public CdrDetails remove(int cdrDetailsSid)
		throws NoSuchCdrDetailsException;

	public CdrDetails updateImpl(CdrDetails cdrDetails);

	/**
	* Returns the cdr details with the primary key or throws a {@link NoSuchCdrDetailsException} if it could not be found.
	*
	* @param cdrDetailsSid the primary key of the cdr details
	* @return the cdr details
	* @throws NoSuchCdrDetailsException if a cdr details with the primary key could not be found
	*/
	public CdrDetails findByPrimaryKey(int cdrDetailsSid)
		throws NoSuchCdrDetailsException;

	/**
	* Returns the cdr details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cdrDetailsSid the primary key of the cdr details
	* @return the cdr details, or <code>null</code> if a cdr details with the primary key could not be found
	*/
	public CdrDetails fetchByPrimaryKey(int cdrDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, CdrDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cdr detailses.
	*
	* @return the cdr detailses
	*/
	public java.util.List<CdrDetails> findAll();

	/**
	* Returns a range of all the cdr detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cdr detailses
	* @param end the upper bound of the range of cdr detailses (not inclusive)
	* @return the range of cdr detailses
	*/
	public java.util.List<CdrDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cdr detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cdr detailses
	* @param end the upper bound of the range of cdr detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cdr detailses
	*/
	public java.util.List<CdrDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CdrDetails> orderByComparator);

	/**
	* Returns an ordered range of all the cdr detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cdr detailses
	* @param end the upper bound of the range of cdr detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cdr detailses
	*/
	public java.util.List<CdrDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CdrDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cdr detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cdr detailses.
	*
	* @return the number of cdr detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}