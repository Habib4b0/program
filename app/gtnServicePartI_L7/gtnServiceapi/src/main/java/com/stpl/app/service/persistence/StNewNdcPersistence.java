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

import com.stpl.app.exception.NoSuchStNewNdcException;
import com.stpl.app.model.StNewNdc;

/**
 * The persistence interface for the st new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StNewNdcPersistenceImpl
 * @see StNewNdcUtil
 * @generated
 */
@ProviderType
public interface StNewNdcPersistence extends BasePersistence<StNewNdc> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StNewNdcUtil} to access the st new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st new ndc in the entity cache if it is enabled.
	*
	* @param stNewNdc the st new ndc
	*/
	public void cacheResult(StNewNdc stNewNdc);

	/**
	* Caches the st new ndcs in the entity cache if it is enabled.
	*
	* @param stNewNdcs the st new ndcs
	*/
	public void cacheResult(java.util.List<StNewNdc> stNewNdcs);

	/**
	* Creates a new st new ndc with the primary key. Does not add the st new ndc to the database.
	*
	* @param stNewNdcPK the primary key for the new st new ndc
	* @return the new st new ndc
	*/
	public StNewNdc create(StNewNdcPK stNewNdcPK);

	/**
	* Removes the st new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNewNdcPK the primary key of the st new ndc
	* @return the st new ndc that was removed
	* @throws NoSuchStNewNdcException if a st new ndc with the primary key could not be found
	*/
	public StNewNdc remove(StNewNdcPK stNewNdcPK)
		throws NoSuchStNewNdcException;

	public StNewNdc updateImpl(StNewNdc stNewNdc);

	/**
	* Returns the st new ndc with the primary key or throws a {@link NoSuchStNewNdcException} if it could not be found.
	*
	* @param stNewNdcPK the primary key of the st new ndc
	* @return the st new ndc
	* @throws NoSuchStNewNdcException if a st new ndc with the primary key could not be found
	*/
	public StNewNdc findByPrimaryKey(StNewNdcPK stNewNdcPK)
		throws NoSuchStNewNdcException;

	/**
	* Returns the st new ndc with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNewNdcPK the primary key of the st new ndc
	* @return the st new ndc, or <code>null</code> if a st new ndc with the primary key could not be found
	*/
	public StNewNdc fetchByPrimaryKey(StNewNdcPK stNewNdcPK);

	@Override
	public java.util.Map<java.io.Serializable, StNewNdc> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st new ndcs.
	*
	* @return the st new ndcs
	*/
	public java.util.List<StNewNdc> findAll();

	/**
	* Returns a range of all the st new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st new ndcs
	* @param end the upper bound of the range of st new ndcs (not inclusive)
	* @return the range of st new ndcs
	*/
	public java.util.List<StNewNdc> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st new ndcs
	* @param end the upper bound of the range of st new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st new ndcs
	*/
	public java.util.List<StNewNdc> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNewNdc> orderByComparator);

	/**
	* Returns an ordered range of all the st new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st new ndcs
	* @param end the upper bound of the range of st new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st new ndcs
	*/
	public java.util.List<StNewNdc> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNewNdc> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st new ndcs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st new ndcs.
	*
	* @return the number of st new ndcs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}