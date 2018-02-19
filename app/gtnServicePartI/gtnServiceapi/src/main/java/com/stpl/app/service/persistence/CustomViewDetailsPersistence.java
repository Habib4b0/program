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

import com.stpl.app.exception.NoSuchCustomViewDetailsException;
import com.stpl.app.model.CustomViewDetails;

/**
 * The persistence interface for the custom view details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CustomViewDetailsPersistenceImpl
 * @see CustomViewDetailsUtil
 * @generated
 */
@ProviderType
public interface CustomViewDetailsPersistence extends BasePersistence<CustomViewDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CustomViewDetailsUtil} to access the custom view details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the custom view details in the entity cache if it is enabled.
	*
	* @param customViewDetails the custom view details
	*/
	public void cacheResult(CustomViewDetails customViewDetails);

	/**
	* Caches the custom view detailses in the entity cache if it is enabled.
	*
	* @param customViewDetailses the custom view detailses
	*/
	public void cacheResult(
		java.util.List<CustomViewDetails> customViewDetailses);

	/**
	* Creates a new custom view details with the primary key. Does not add the custom view details to the database.
	*
	* @param customViewDetailsSid the primary key for the new custom view details
	* @return the new custom view details
	*/
	public CustomViewDetails create(int customViewDetailsSid);

	/**
	* Removes the custom view details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customViewDetailsSid the primary key of the custom view details
	* @return the custom view details that was removed
	* @throws NoSuchCustomViewDetailsException if a custom view details with the primary key could not be found
	*/
	public CustomViewDetails remove(int customViewDetailsSid)
		throws NoSuchCustomViewDetailsException;

	public CustomViewDetails updateImpl(CustomViewDetails customViewDetails);

	/**
	* Returns the custom view details with the primary key or throws a {@link NoSuchCustomViewDetailsException} if it could not be found.
	*
	* @param customViewDetailsSid the primary key of the custom view details
	* @return the custom view details
	* @throws NoSuchCustomViewDetailsException if a custom view details with the primary key could not be found
	*/
	public CustomViewDetails findByPrimaryKey(int customViewDetailsSid)
		throws NoSuchCustomViewDetailsException;

	/**
	* Returns the custom view details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param customViewDetailsSid the primary key of the custom view details
	* @return the custom view details, or <code>null</code> if a custom view details with the primary key could not be found
	*/
	public CustomViewDetails fetchByPrimaryKey(int customViewDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, CustomViewDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the custom view detailses.
	*
	* @return the custom view detailses
	*/
	public java.util.List<CustomViewDetails> findAll();

	/**
	* Returns a range of all the custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view detailses
	* @param end the upper bound of the range of custom view detailses (not inclusive)
	* @return the range of custom view detailses
	*/
	public java.util.List<CustomViewDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view detailses
	* @param end the upper bound of the range of custom view detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of custom view detailses
	*/
	public java.util.List<CustomViewDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomViewDetails> orderByComparator);

	/**
	* Returns an ordered range of all the custom view detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view detailses
	* @param end the upper bound of the range of custom view detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of custom view detailses
	*/
	public java.util.List<CustomViewDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomViewDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the custom view detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of custom view detailses.
	*
	* @return the number of custom view detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}