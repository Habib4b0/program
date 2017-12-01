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

import com.stpl.app.exception.NoSuchStFederalNewNdcException;
import com.stpl.app.model.StFederalNewNdc;

/**
 * The persistence interface for the st federal new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StFederalNewNdcPersistenceImpl
 * @see StFederalNewNdcUtil
 * @generated
 */
@ProviderType
public interface StFederalNewNdcPersistence extends BasePersistence<StFederalNewNdc> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StFederalNewNdcUtil} to access the st federal new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st federal new ndc in the entity cache if it is enabled.
	*
	* @param stFederalNewNdc the st federal new ndc
	*/
	public void cacheResult(StFederalNewNdc stFederalNewNdc);

	/**
	* Caches the st federal new ndcs in the entity cache if it is enabled.
	*
	* @param stFederalNewNdcs the st federal new ndcs
	*/
	public void cacheResult(java.util.List<StFederalNewNdc> stFederalNewNdcs);

	/**
	* Creates a new st federal new ndc with the primary key. Does not add the st federal new ndc to the database.
	*
	* @param stFederalNewNdcPK the primary key for the new st federal new ndc
	* @return the new st federal new ndc
	*/
	public StFederalNewNdc create(StFederalNewNdcPK stFederalNewNdcPK);

	/**
	* Removes the st federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stFederalNewNdcPK the primary key of the st federal new ndc
	* @return the st federal new ndc that was removed
	* @throws NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
	*/
	public StFederalNewNdc remove(StFederalNewNdcPK stFederalNewNdcPK)
		throws NoSuchStFederalNewNdcException;

	public StFederalNewNdc updateImpl(StFederalNewNdc stFederalNewNdc);

	/**
	* Returns the st federal new ndc with the primary key or throws a {@link NoSuchStFederalNewNdcException} if it could not be found.
	*
	* @param stFederalNewNdcPK the primary key of the st federal new ndc
	* @return the st federal new ndc
	* @throws NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
	*/
	public StFederalNewNdc findByPrimaryKey(StFederalNewNdcPK stFederalNewNdcPK)
		throws NoSuchStFederalNewNdcException;

	/**
	* Returns the st federal new ndc with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stFederalNewNdcPK the primary key of the st federal new ndc
	* @return the st federal new ndc, or <code>null</code> if a st federal new ndc with the primary key could not be found
	*/
	public StFederalNewNdc fetchByPrimaryKey(
		StFederalNewNdcPK stFederalNewNdcPK);

	@Override
	public java.util.Map<java.io.Serializable, StFederalNewNdc> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st federal new ndcs.
	*
	* @return the st federal new ndcs
	*/
	public java.util.List<StFederalNewNdc> findAll();

	/**
	* Returns a range of all the st federal new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st federal new ndcs
	* @param end the upper bound of the range of st federal new ndcs (not inclusive)
	* @return the range of st federal new ndcs
	*/
	public java.util.List<StFederalNewNdc> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st federal new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st federal new ndcs
	* @param end the upper bound of the range of st federal new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st federal new ndcs
	*/
	public java.util.List<StFederalNewNdc> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StFederalNewNdc> orderByComparator);

	/**
	* Returns an ordered range of all the st federal new ndcs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st federal new ndcs
	* @param end the upper bound of the range of st federal new ndcs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st federal new ndcs
	*/
	public java.util.List<StFederalNewNdc> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StFederalNewNdc> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st federal new ndcs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st federal new ndcs.
	*
	* @return the number of st federal new ndcs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}