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

import com.stpl.app.exception.NoSuchNmSalesProjectionException;
import com.stpl.app.model.NmSalesProjection;

/**
 * The persistence interface for the nm sales projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.NmSalesProjectionPersistenceImpl
 * @see NmSalesProjectionUtil
 * @generated
 */
@ProviderType
public interface NmSalesProjectionPersistence extends BasePersistence<NmSalesProjection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NmSalesProjectionUtil} to access the nm sales projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the nm sales projection in the entity cache if it is enabled.
	*
	* @param nmSalesProjection the nm sales projection
	*/
	public void cacheResult(NmSalesProjection nmSalesProjection);

	/**
	* Caches the nm sales projections in the entity cache if it is enabled.
	*
	* @param nmSalesProjections the nm sales projections
	*/
	public void cacheResult(
		java.util.List<NmSalesProjection> nmSalesProjections);

	/**
	* Creates a new nm sales projection with the primary key. Does not add the nm sales projection to the database.
	*
	* @param nmSalesProjectionPK the primary key for the new nm sales projection
	* @return the new nm sales projection
	*/
	public NmSalesProjection create(NmSalesProjectionPK nmSalesProjectionPK);

	/**
	* Removes the nm sales projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmSalesProjectionPK the primary key of the nm sales projection
	* @return the nm sales projection that was removed
	* @throws NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
	*/
	public NmSalesProjection remove(NmSalesProjectionPK nmSalesProjectionPK)
		throws NoSuchNmSalesProjectionException;

	public NmSalesProjection updateImpl(NmSalesProjection nmSalesProjection);

	/**
	* Returns the nm sales projection with the primary key or throws a {@link NoSuchNmSalesProjectionException} if it could not be found.
	*
	* @param nmSalesProjectionPK the primary key of the nm sales projection
	* @return the nm sales projection
	* @throws NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
	*/
	public NmSalesProjection findByPrimaryKey(
		NmSalesProjectionPK nmSalesProjectionPK)
		throws NoSuchNmSalesProjectionException;

	/**
	* Returns the nm sales projection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param nmSalesProjectionPK the primary key of the nm sales projection
	* @return the nm sales projection, or <code>null</code> if a nm sales projection with the primary key could not be found
	*/
	public NmSalesProjection fetchByPrimaryKey(
		NmSalesProjectionPK nmSalesProjectionPK);

	@Override
	public java.util.Map<java.io.Serializable, NmSalesProjection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the nm sales projections.
	*
	* @return the nm sales projections
	*/
	public java.util.List<NmSalesProjection> findAll();

	/**
	* Returns a range of all the nm sales projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm sales projections
	* @param end the upper bound of the range of nm sales projections (not inclusive)
	* @return the range of nm sales projections
	*/
	public java.util.List<NmSalesProjection> findAll(int start, int end);

	/**
	* Returns an ordered range of all the nm sales projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm sales projections
	* @param end the upper bound of the range of nm sales projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm sales projections
	*/
	public java.util.List<NmSalesProjection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NmSalesProjection> orderByComparator);

	/**
	* Returns an ordered range of all the nm sales projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm sales projections
	* @param end the upper bound of the range of nm sales projections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm sales projections
	*/
	public java.util.List<NmSalesProjection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NmSalesProjection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the nm sales projections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of nm sales projections.
	*
	* @return the number of nm sales projections
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}