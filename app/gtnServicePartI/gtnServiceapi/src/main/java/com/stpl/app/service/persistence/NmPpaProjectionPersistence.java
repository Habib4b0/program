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

import com.stpl.app.exception.NoSuchNmPpaProjectionException;
import com.stpl.app.model.NmPpaProjection;

/**
 * The persistence interface for the nm ppa projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.NmPpaProjectionPersistenceImpl
 * @see NmPpaProjectionUtil
 * @generated
 */
@ProviderType
public interface NmPpaProjectionPersistence extends BasePersistence<NmPpaProjection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NmPpaProjectionUtil} to access the nm ppa projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the nm ppa projection in the entity cache if it is enabled.
	*
	* @param nmPpaProjection the nm ppa projection
	*/
	public void cacheResult(NmPpaProjection nmPpaProjection);

	/**
	* Caches the nm ppa projections in the entity cache if it is enabled.
	*
	* @param nmPpaProjections the nm ppa projections
	*/
	public void cacheResult(java.util.List<NmPpaProjection> nmPpaProjections);

	/**
	* Creates a new nm ppa projection with the primary key. Does not add the nm ppa projection to the database.
	*
	* @param nmPpaProjectionPK the primary key for the new nm ppa projection
	* @return the new nm ppa projection
	*/
	public NmPpaProjection create(NmPpaProjectionPK nmPpaProjectionPK);

	/**
	* Removes the nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmPpaProjectionPK the primary key of the nm ppa projection
	* @return the nm ppa projection that was removed
	* @throws NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
	*/
	public NmPpaProjection remove(NmPpaProjectionPK nmPpaProjectionPK)
		throws NoSuchNmPpaProjectionException;

	public NmPpaProjection updateImpl(NmPpaProjection nmPpaProjection);

	/**
	* Returns the nm ppa projection with the primary key or throws a {@link NoSuchNmPpaProjectionException} if it could not be found.
	*
	* @param nmPpaProjectionPK the primary key of the nm ppa projection
	* @return the nm ppa projection
	* @throws NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
	*/
	public NmPpaProjection findByPrimaryKey(NmPpaProjectionPK nmPpaProjectionPK)
		throws NoSuchNmPpaProjectionException;

	/**
	* Returns the nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nmPpaProjectionPK the primary key of the nm ppa projection
	* @return the nm ppa projection, or <code>null</code> if a nm ppa projection with the primary key could not be found
	*/
	public NmPpaProjection fetchByPrimaryKey(
		NmPpaProjectionPK nmPpaProjectionPK);

	@Override
	public java.util.Map<java.io.Serializable, NmPpaProjection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the nm ppa projections.
	*
	* @return the nm ppa projections
	*/
	public java.util.List<NmPpaProjection> findAll();

	/**
	* Returns a range of all the nm ppa projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projections
	* @param end the upper bound of the range of nm ppa projections (not inclusive)
	* @return the range of nm ppa projections
	*/
	public java.util.List<NmPpaProjection> findAll(int start, int end);

	/**
	* Returns an ordered range of all the nm ppa projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projections
	* @param end the upper bound of the range of nm ppa projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm ppa projections
	*/
	public java.util.List<NmPpaProjection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NmPpaProjection> orderByComparator);

	/**
	* Returns an ordered range of all the nm ppa projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projections
	* @param end the upper bound of the range of nm ppa projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm ppa projections
	*/
	public java.util.List<NmPpaProjection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NmPpaProjection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the nm ppa projections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of nm ppa projections.
	*
	* @return the number of nm ppa projections
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}