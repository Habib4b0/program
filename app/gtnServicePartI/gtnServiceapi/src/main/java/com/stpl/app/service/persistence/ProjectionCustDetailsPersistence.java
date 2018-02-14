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

import com.stpl.app.exception.NoSuchProjectionCustDetailsException;
import com.stpl.app.model.ProjectionCustDetails;

/**
 * The persistence interface for the projection cust details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ProjectionCustDetailsPersistenceImpl
 * @see ProjectionCustDetailsUtil
 * @generated
 */
@ProviderType
public interface ProjectionCustDetailsPersistence extends BasePersistence<ProjectionCustDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectionCustDetailsUtil} to access the projection cust details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the projection cust details in the entity cache if it is enabled.
	*
	* @param projectionCustDetails the projection cust details
	*/
	public void cacheResult(ProjectionCustDetails projectionCustDetails);

	/**
	* Caches the projection cust detailses in the entity cache if it is enabled.
	*
	* @param projectionCustDetailses the projection cust detailses
	*/
	public void cacheResult(
		java.util.List<ProjectionCustDetails> projectionCustDetailses);

	/**
	* Creates a new projection cust details with the primary key. Does not add the projection cust details to the database.
	*
	* @param customerDetailsId the primary key for the new projection cust details
	* @return the new projection cust details
	*/
	public ProjectionCustDetails create(int customerDetailsId);

	/**
	* Removes the projection cust details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customerDetailsId the primary key of the projection cust details
	* @return the projection cust details that was removed
	* @throws NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
	*/
	public ProjectionCustDetails remove(int customerDetailsId)
		throws NoSuchProjectionCustDetailsException;

	public ProjectionCustDetails updateImpl(
		ProjectionCustDetails projectionCustDetails);

	/**
	* Returns the projection cust details with the primary key or throws a {@link NoSuchProjectionCustDetailsException} if it could not be found.
	*
	* @param customerDetailsId the primary key of the projection cust details
	* @return the projection cust details
	* @throws NoSuchProjectionCustDetailsException if a projection cust details with the primary key could not be found
	*/
	public ProjectionCustDetails findByPrimaryKey(int customerDetailsId)
		throws NoSuchProjectionCustDetailsException;

	/**
	* Returns the projection cust details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param customerDetailsId the primary key of the projection cust details
	* @return the projection cust details, or <code>null</code> if a projection cust details with the primary key could not be found
	*/
	public ProjectionCustDetails fetchByPrimaryKey(int customerDetailsId);

	@Override
	public java.util.Map<java.io.Serializable, ProjectionCustDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the projection cust detailses.
	*
	* @return the projection cust detailses
	*/
	public java.util.List<ProjectionCustDetails> findAll();

	/**
	* Returns a range of all the projection cust detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection cust detailses
	* @param end the upper bound of the range of projection cust detailses (not inclusive)
	* @return the range of projection cust detailses
	*/
	public java.util.List<ProjectionCustDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the projection cust detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection cust detailses
	* @param end the upper bound of the range of projection cust detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projection cust detailses
	*/
	public java.util.List<ProjectionCustDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionCustDetails> orderByComparator);

	/**
	* Returns an ordered range of all the projection cust detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionCustDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection cust detailses
	* @param end the upper bound of the range of projection cust detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of projection cust detailses
	*/
	public java.util.List<ProjectionCustDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionCustDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the projection cust detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of projection cust detailses.
	*
	* @return the number of projection cust detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}