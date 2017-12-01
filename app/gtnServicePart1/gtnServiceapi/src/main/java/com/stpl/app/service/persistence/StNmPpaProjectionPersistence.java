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

import com.stpl.app.exception.NoSuchStNmPpaProjectionException;
import com.stpl.app.model.StNmPpaProjection;

/**
 * The persistence interface for the st nm ppa projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StNmPpaProjectionPersistenceImpl
 * @see StNmPpaProjectionUtil
 * @generated
 */
@ProviderType
public interface StNmPpaProjectionPersistence extends BasePersistence<StNmPpaProjection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StNmPpaProjectionUtil} to access the st nm ppa projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st nm ppa projection in the entity cache if it is enabled.
	*
	* @param stNmPpaProjection the st nm ppa projection
	*/
	public void cacheResult(StNmPpaProjection stNmPpaProjection);

	/**
	* Caches the st nm ppa projections in the entity cache if it is enabled.
	*
	* @param stNmPpaProjections the st nm ppa projections
	*/
	public void cacheResult(
		java.util.List<StNmPpaProjection> stNmPpaProjections);

	/**
	* Creates a new st nm ppa projection with the primary key. Does not add the st nm ppa projection to the database.
	*
	* @param stNmPpaProjectionPK the primary key for the new st nm ppa projection
	* @return the new st nm ppa projection
	*/
	public StNmPpaProjection create(StNmPpaProjectionPK stNmPpaProjectionPK);

	/**
	* Removes the st nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmPpaProjectionPK the primary key of the st nm ppa projection
	* @return the st nm ppa projection that was removed
	* @throws NoSuchStNmPpaProjectionException if a st nm ppa projection with the primary key could not be found
	*/
	public StNmPpaProjection remove(StNmPpaProjectionPK stNmPpaProjectionPK)
		throws NoSuchStNmPpaProjectionException;

	public StNmPpaProjection updateImpl(StNmPpaProjection stNmPpaProjection);

	/**
	* Returns the st nm ppa projection with the primary key or throws a {@link NoSuchStNmPpaProjectionException} if it could not be found.
	*
	* @param stNmPpaProjectionPK the primary key of the st nm ppa projection
	* @return the st nm ppa projection
	* @throws NoSuchStNmPpaProjectionException if a st nm ppa projection with the primary key could not be found
	*/
	public StNmPpaProjection findByPrimaryKey(
		StNmPpaProjectionPK stNmPpaProjectionPK)
		throws NoSuchStNmPpaProjectionException;

	/**
	* Returns the st nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmPpaProjectionPK the primary key of the st nm ppa projection
	* @return the st nm ppa projection, or <code>null</code> if a st nm ppa projection with the primary key could not be found
	*/
	public StNmPpaProjection fetchByPrimaryKey(
		StNmPpaProjectionPK stNmPpaProjectionPK);

	@Override
	public java.util.Map<java.io.Serializable, StNmPpaProjection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st nm ppa projections.
	*
	* @return the st nm ppa projections
	*/
	public java.util.List<StNmPpaProjection> findAll();

	/**
	* Returns a range of all the st nm ppa projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm ppa projections
	* @param end the upper bound of the range of st nm ppa projections (not inclusive)
	* @return the range of st nm ppa projections
	*/
	public java.util.List<StNmPpaProjection> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st nm ppa projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm ppa projections
	* @param end the upper bound of the range of st nm ppa projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm ppa projections
	*/
	public java.util.List<StNmPpaProjection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmPpaProjection> orderByComparator);

	/**
	* Returns an ordered range of all the st nm ppa projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm ppa projections
	* @param end the upper bound of the range of st nm ppa projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm ppa projections
	*/
	public java.util.List<StNmPpaProjection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmPpaProjection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st nm ppa projections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st nm ppa projections.
	*
	* @return the number of st nm ppa projections
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}