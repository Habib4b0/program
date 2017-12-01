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

import com.stpl.app.exception.NoSuchStNmDiscountProjectionException;
import com.stpl.app.model.StNmDiscountProjection;

/**
 * The persistence interface for the st nm discount projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StNmDiscountProjectionPersistenceImpl
 * @see StNmDiscountProjectionUtil
 * @generated
 */
@ProviderType
public interface StNmDiscountProjectionPersistence extends BasePersistence<StNmDiscountProjection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StNmDiscountProjectionUtil} to access the st nm discount projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st nm discount projection in the entity cache if it is enabled.
	*
	* @param stNmDiscountProjection the st nm discount projection
	*/
	public void cacheResult(StNmDiscountProjection stNmDiscountProjection);

	/**
	* Caches the st nm discount projections in the entity cache if it is enabled.
	*
	* @param stNmDiscountProjections the st nm discount projections
	*/
	public void cacheResult(
		java.util.List<StNmDiscountProjection> stNmDiscountProjections);

	/**
	* Creates a new st nm discount projection with the primary key. Does not add the st nm discount projection to the database.
	*
	* @param stNmDiscountProjectionPK the primary key for the new st nm discount projection
	* @return the new st nm discount projection
	*/
	public StNmDiscountProjection create(
		StNmDiscountProjectionPK stNmDiscountProjectionPK);

	/**
	* Removes the st nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmDiscountProjectionPK the primary key of the st nm discount projection
	* @return the st nm discount projection that was removed
	* @throws NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
	*/
	public StNmDiscountProjection remove(
		StNmDiscountProjectionPK stNmDiscountProjectionPK)
		throws NoSuchStNmDiscountProjectionException;

	public StNmDiscountProjection updateImpl(
		StNmDiscountProjection stNmDiscountProjection);

	/**
	* Returns the st nm discount projection with the primary key or throws a {@link NoSuchStNmDiscountProjectionException} if it could not be found.
	*
	* @param stNmDiscountProjectionPK the primary key of the st nm discount projection
	* @return the st nm discount projection
	* @throws NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
	*/
	public StNmDiscountProjection findByPrimaryKey(
		StNmDiscountProjectionPK stNmDiscountProjectionPK)
		throws NoSuchStNmDiscountProjectionException;

	/**
	* Returns the st nm discount projection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmDiscountProjectionPK the primary key of the st nm discount projection
	* @return the st nm discount projection, or <code>null</code> if a st nm discount projection with the primary key could not be found
	*/
	public StNmDiscountProjection fetchByPrimaryKey(
		StNmDiscountProjectionPK stNmDiscountProjectionPK);

	@Override
	public java.util.Map<java.io.Serializable, StNmDiscountProjection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st nm discount projections.
	*
	* @return the st nm discount projections
	*/
	public java.util.List<StNmDiscountProjection> findAll();

	/**
	* Returns a range of all the st nm discount projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount projections
	* @param end the upper bound of the range of st nm discount projections (not inclusive)
	* @return the range of st nm discount projections
	*/
	public java.util.List<StNmDiscountProjection> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st nm discount projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount projections
	* @param end the upper bound of the range of st nm discount projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm discount projections
	*/
	public java.util.List<StNmDiscountProjection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmDiscountProjection> orderByComparator);

	/**
	* Returns an ordered range of all the st nm discount projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount projections
	* @param end the upper bound of the range of st nm discount projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm discount projections
	*/
	public java.util.List<StNmDiscountProjection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmDiscountProjection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st nm discount projections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st nm discount projections.
	*
	* @return the number of st nm discount projections
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}