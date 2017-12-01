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

import com.stpl.app.exception.NoSuchProjectionDetailsException;
import com.stpl.app.model.ProjectionDetails;

/**
 * The persistence interface for the projection details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ProjectionDetailsPersistenceImpl
 * @see ProjectionDetailsUtil
 * @generated
 */
@ProviderType
public interface ProjectionDetailsPersistence extends BasePersistence<ProjectionDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectionDetailsUtil} to access the projection details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the projection details in the entity cache if it is enabled.
	*
	* @param projectionDetails the projection details
	*/
	public void cacheResult(ProjectionDetails projectionDetails);

	/**
	* Caches the projection detailses in the entity cache if it is enabled.
	*
	* @param projectionDetailses the projection detailses
	*/
	public void cacheResult(
		java.util.List<ProjectionDetails> projectionDetailses);

	/**
	* Creates a new projection details with the primary key. Does not add the projection details to the database.
	*
	* @param projectionDetailsSid the primary key for the new projection details
	* @return the new projection details
	*/
	public ProjectionDetails create(int projectionDetailsSid);

	/**
	* Removes the projection details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the projection details
	* @return the projection details that was removed
	* @throws NoSuchProjectionDetailsException if a projection details with the primary key could not be found
	*/
	public ProjectionDetails remove(int projectionDetailsSid)
		throws NoSuchProjectionDetailsException;

	public ProjectionDetails updateImpl(ProjectionDetails projectionDetails);

	/**
	* Returns the projection details with the primary key or throws a {@link NoSuchProjectionDetailsException} if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the projection details
	* @return the projection details
	* @throws NoSuchProjectionDetailsException if a projection details with the primary key could not be found
	*/
	public ProjectionDetails findByPrimaryKey(int projectionDetailsSid)
		throws NoSuchProjectionDetailsException;

	/**
	* Returns the projection details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the projection details
	* @return the projection details, or <code>null</code> if a projection details with the primary key could not be found
	*/
	public ProjectionDetails fetchByPrimaryKey(int projectionDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, ProjectionDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the projection detailses.
	*
	* @return the projection detailses
	*/
	public java.util.List<ProjectionDetails> findAll();

	/**
	* Returns a range of all the projection detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection detailses
	* @param end the upper bound of the range of projection detailses (not inclusive)
	* @return the range of projection detailses
	*/
	public java.util.List<ProjectionDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the projection detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection detailses
	* @param end the upper bound of the range of projection detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projection detailses
	*/
	public java.util.List<ProjectionDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionDetails> orderByComparator);

	/**
	* Returns an ordered range of all the projection detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection detailses
	* @param end the upper bound of the range of projection detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of projection detailses
	*/
	public java.util.List<ProjectionDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the projection detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of projection detailses.
	*
	* @return the number of projection detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}