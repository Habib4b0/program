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

import com.stpl.app.parttwo.exception.NoSuchCffCustomViewDetailsException;
import com.stpl.app.parttwo.model.CffCustomViewDetails;

/**
 * The persistence interface for the cff custom view details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.CffCustomViewDetailsPersistenceImpl
 * @see CffCustomViewDetailsUtil
 * @generated
 */
@ProviderType
public interface CffCustomViewDetailsPersistence extends BasePersistence<CffCustomViewDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CffCustomViewDetailsUtil} to access the cff custom view details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cff custom view details in the entity cache if it is enabled.
	*
	* @param cffCustomViewDetails the cff custom view details
	*/
	public void cacheResult(CffCustomViewDetails cffCustomViewDetails);

	/**
	* Caches the cff custom view detailses in the entity cache if it is enabled.
	*
	* @param cffCustomViewDetailses the cff custom view detailses
	*/
	public void cacheResult(
		java.util.List<CffCustomViewDetails> cffCustomViewDetailses);

	/**
	* Creates a new cff custom view details with the primary key. Does not add the cff custom view details to the database.
	*
	* @param cffCustomViewDetailsSid the primary key for the new cff custom view details
	* @return the new cff custom view details
	*/
	public CffCustomViewDetails create(int cffCustomViewDetailsSid);

	/**
	* Removes the cff custom view details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustomViewDetailsSid the primary key of the cff custom view details
	* @return the cff custom view details that was removed
	* @throws NoSuchCffCustomViewDetailsException if a cff custom view details with the primary key could not be found
	*/
	public CffCustomViewDetails remove(int cffCustomViewDetailsSid)
		throws NoSuchCffCustomViewDetailsException;

	public CffCustomViewDetails updateImpl(
		CffCustomViewDetails cffCustomViewDetails);

	/**
	* Returns the cff custom view details with the primary key or throws a {@link NoSuchCffCustomViewDetailsException} if it could not be found.
	*
	* @param cffCustomViewDetailsSid the primary key of the cff custom view details
	* @return the cff custom view details
	* @throws NoSuchCffCustomViewDetailsException if a cff custom view details with the primary key could not be found
	*/
	public CffCustomViewDetails findByPrimaryKey(int cffCustomViewDetailsSid)
		throws NoSuchCffCustomViewDetailsException;

	/**
	* Returns the cff custom view details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffCustomViewDetailsSid the primary key of the cff custom view details
	* @return the cff custom view details, or <code>null</code> if a cff custom view details with the primary key could not be found
	*/
	public CffCustomViewDetails fetchByPrimaryKey(int cffCustomViewDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, CffCustomViewDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cff custom view detailses.
	*
	* @return the cff custom view detailses
	*/
	public java.util.List<CffCustomViewDetails> findAll();

	/**
	* Returns a range of all the cff custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff custom view detailses
	* @param end the upper bound of the range of cff custom view detailses (not inclusive)
	* @return the range of cff custom view detailses
	*/
	public java.util.List<CffCustomViewDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cff custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff custom view detailses
	* @param end the upper bound of the range of cff custom view detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff custom view detailses
	*/
	public java.util.List<CffCustomViewDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffCustomViewDetails> orderByComparator);

	/**
	* Returns an ordered range of all the cff custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff custom view detailses
	* @param end the upper bound of the range of cff custom view detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff custom view detailses
	*/
	public java.util.List<CffCustomViewDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffCustomViewDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cff custom view detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cff custom view detailses.
	*
	* @return the number of cff custom view detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}