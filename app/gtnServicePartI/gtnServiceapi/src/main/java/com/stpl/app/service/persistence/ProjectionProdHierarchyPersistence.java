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

import com.stpl.app.exception.NoSuchProjectionProdHierarchyException;
import com.stpl.app.model.ProjectionProdHierarchy;

/**
 * The persistence interface for the projection prod hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ProjectionProdHierarchyPersistenceImpl
 * @see ProjectionProdHierarchyUtil
 * @generated
 */
@ProviderType
public interface ProjectionProdHierarchyPersistence extends BasePersistence<ProjectionProdHierarchy> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectionProdHierarchyUtil} to access the projection prod hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the projection prod hierarchy in the entity cache if it is enabled.
	*
	* @param projectionProdHierarchy the projection prod hierarchy
	*/
	public void cacheResult(ProjectionProdHierarchy projectionProdHierarchy);

	/**
	* Caches the projection prod hierarchies in the entity cache if it is enabled.
	*
	* @param projectionProdHierarchies the projection prod hierarchies
	*/
	public void cacheResult(
		java.util.List<ProjectionProdHierarchy> projectionProdHierarchies);

	/**
	* Creates a new projection prod hierarchy with the primary key. Does not add the projection prod hierarchy to the database.
	*
	* @param projectionProdHierarchySid the primary key for the new projection prod hierarchy
	* @return the new projection prod hierarchy
	*/
	public ProjectionProdHierarchy create(int projectionProdHierarchySid);

	/**
	* Removes the projection prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionProdHierarchySid the primary key of the projection prod hierarchy
	* @return the projection prod hierarchy that was removed
	* @throws NoSuchProjectionProdHierarchyException if a projection prod hierarchy with the primary key could not be found
	*/
	public ProjectionProdHierarchy remove(int projectionProdHierarchySid)
		throws NoSuchProjectionProdHierarchyException;

	public ProjectionProdHierarchy updateImpl(
		ProjectionProdHierarchy projectionProdHierarchy);

	/**
	* Returns the projection prod hierarchy with the primary key or throws a {@link NoSuchProjectionProdHierarchyException} if it could not be found.
	*
	* @param projectionProdHierarchySid the primary key of the projection prod hierarchy
	* @return the projection prod hierarchy
	* @throws NoSuchProjectionProdHierarchyException if a projection prod hierarchy with the primary key could not be found
	*/
	public ProjectionProdHierarchy findByPrimaryKey(
		int projectionProdHierarchySid)
		throws NoSuchProjectionProdHierarchyException;

	/**
	* Returns the projection prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionProdHierarchySid the primary key of the projection prod hierarchy
	* @return the projection prod hierarchy, or <code>null</code> if a projection prod hierarchy with the primary key could not be found
	*/
	public ProjectionProdHierarchy fetchByPrimaryKey(
		int projectionProdHierarchySid);

	@Override
	public java.util.Map<java.io.Serializable, ProjectionProdHierarchy> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the projection prod hierarchies.
	*
	* @return the projection prod hierarchies
	*/
	public java.util.List<ProjectionProdHierarchy> findAll();

	/**
	* Returns a range of all the projection prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection prod hierarchies
	* @param end the upper bound of the range of projection prod hierarchies (not inclusive)
	* @return the range of projection prod hierarchies
	*/
	public java.util.List<ProjectionProdHierarchy> findAll(int start, int end);

	/**
	* Returns an ordered range of all the projection prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection prod hierarchies
	* @param end the upper bound of the range of projection prod hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projection prod hierarchies
	*/
	public java.util.List<ProjectionProdHierarchy> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionProdHierarchy> orderByComparator);

	/**
	* Returns an ordered range of all the projection prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection prod hierarchies
	* @param end the upper bound of the range of projection prod hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of projection prod hierarchies
	*/
	public java.util.List<ProjectionProdHierarchy> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionProdHierarchy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the projection prod hierarchies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of projection prod hierarchies.
	*
	* @return the number of projection prod hierarchies
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}