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

import com.stpl.app.exception.NoSuchProjectionProdDetailsException;
import com.stpl.app.model.ProjectionProdDetails;

/**
 * The persistence interface for the projection prod details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ProjectionProdDetailsPersistenceImpl
 * @see ProjectionProdDetailsUtil
 * @generated
 */
@ProviderType
public interface ProjectionProdDetailsPersistence extends BasePersistence<ProjectionProdDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectionProdDetailsUtil} to access the projection prod details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the projection prod details in the entity cache if it is enabled.
	*
	* @param projectionProdDetails the projection prod details
	*/
	public void cacheResult(ProjectionProdDetails projectionProdDetails);

	/**
	* Caches the projection prod detailses in the entity cache if it is enabled.
	*
	* @param projectionProdDetailses the projection prod detailses
	*/
	public void cacheResult(
		java.util.List<ProjectionProdDetails> projectionProdDetailses);

	/**
	* Creates a new projection prod details with the primary key. Does not add the projection prod details to the database.
	*
	* @param productDetailsId the primary key for the new projection prod details
	* @return the new projection prod details
	*/
	public ProjectionProdDetails create(int productDetailsId);

	/**
	* Removes the projection prod details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param productDetailsId the primary key of the projection prod details
	* @return the projection prod details that was removed
	* @throws NoSuchProjectionProdDetailsException if a projection prod details with the primary key could not be found
	*/
	public ProjectionProdDetails remove(int productDetailsId)
		throws NoSuchProjectionProdDetailsException;

	public ProjectionProdDetails updateImpl(
		ProjectionProdDetails projectionProdDetails);

	/**
	* Returns the projection prod details with the primary key or throws a {@link NoSuchProjectionProdDetailsException} if it could not be found.
	*
	* @param productDetailsId the primary key of the projection prod details
	* @return the projection prod details
	* @throws NoSuchProjectionProdDetailsException if a projection prod details with the primary key could not be found
	*/
	public ProjectionProdDetails findByPrimaryKey(int productDetailsId)
		throws NoSuchProjectionProdDetailsException;

	/**
	* Returns the projection prod details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param productDetailsId the primary key of the projection prod details
	* @return the projection prod details, or <code>null</code> if a projection prod details with the primary key could not be found
	*/
	public ProjectionProdDetails fetchByPrimaryKey(int productDetailsId);

	@Override
	public java.util.Map<java.io.Serializable, ProjectionProdDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the projection prod detailses.
	*
	* @return the projection prod detailses
	*/
	public java.util.List<ProjectionProdDetails> findAll();

	/**
	* Returns a range of all the projection prod detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection prod detailses
	* @param end the upper bound of the range of projection prod detailses (not inclusive)
	* @return the range of projection prod detailses
	*/
	public java.util.List<ProjectionProdDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the projection prod detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection prod detailses
	* @param end the upper bound of the range of projection prod detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projection prod detailses
	*/
	public java.util.List<ProjectionProdDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionProdDetails> orderByComparator);

	/**
	* Returns an ordered range of all the projection prod detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection prod detailses
	* @param end the upper bound of the range of projection prod detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of projection prod detailses
	*/
	public java.util.List<ProjectionProdDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionProdDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the projection prod detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of projection prod detailses.
	*
	* @return the number of projection prod detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}