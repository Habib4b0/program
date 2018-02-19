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

import com.stpl.app.exception.NoSuchProjectionCustHierarchyException;
import com.stpl.app.model.ProjectionCustHierarchy;

/**
 * The persistence interface for the projection cust hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ProjectionCustHierarchyPersistenceImpl
 * @see ProjectionCustHierarchyUtil
 * @generated
 */
@ProviderType
public interface ProjectionCustHierarchyPersistence extends BasePersistence<ProjectionCustHierarchy> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectionCustHierarchyUtil} to access the projection cust hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the projection cust hierarchy in the entity cache if it is enabled.
	*
	* @param projectionCustHierarchy the projection cust hierarchy
	*/
	public void cacheResult(ProjectionCustHierarchy projectionCustHierarchy);

	/**
	* Caches the projection cust hierarchies in the entity cache if it is enabled.
	*
	* @param projectionCustHierarchies the projection cust hierarchies
	*/
	public void cacheResult(
		java.util.List<ProjectionCustHierarchy> projectionCustHierarchies);

	/**
	* Creates a new projection cust hierarchy with the primary key. Does not add the projection cust hierarchy to the database.
	*
	* @param projectionCustHierarchySid the primary key for the new projection cust hierarchy
	* @return the new projection cust hierarchy
	*/
	public ProjectionCustHierarchy create(int projectionCustHierarchySid);

	/**
	* Removes the projection cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionCustHierarchySid the primary key of the projection cust hierarchy
	* @return the projection cust hierarchy that was removed
	* @throws NoSuchProjectionCustHierarchyException if a projection cust hierarchy with the primary key could not be found
	*/
	public ProjectionCustHierarchy remove(int projectionCustHierarchySid)
		throws NoSuchProjectionCustHierarchyException;

	public ProjectionCustHierarchy updateImpl(
		ProjectionCustHierarchy projectionCustHierarchy);

	/**
	* Returns the projection cust hierarchy with the primary key or throws a {@link NoSuchProjectionCustHierarchyException} if it could not be found.
	*
	* @param projectionCustHierarchySid the primary key of the projection cust hierarchy
	* @return the projection cust hierarchy
	* @throws NoSuchProjectionCustHierarchyException if a projection cust hierarchy with the primary key could not be found
	*/
	public ProjectionCustHierarchy findByPrimaryKey(
		int projectionCustHierarchySid)
		throws NoSuchProjectionCustHierarchyException;

	/**
	* Returns the projection cust hierarchy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionCustHierarchySid the primary key of the projection cust hierarchy
	* @return the projection cust hierarchy, or <code>null</code> if a projection cust hierarchy with the primary key could not be found
	*/
	public ProjectionCustHierarchy fetchByPrimaryKey(
		int projectionCustHierarchySid);

	@Override
	public java.util.Map<java.io.Serializable, ProjectionCustHierarchy> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the projection cust hierarchies.
	*
	* @return the projection cust hierarchies
	*/
	public java.util.List<ProjectionCustHierarchy> findAll();

	/**
	* Returns a range of all the projection cust hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection cust hierarchies
	* @param end the upper bound of the range of projection cust hierarchies (not inclusive)
	* @return the range of projection cust hierarchies
	*/
	public java.util.List<ProjectionCustHierarchy> findAll(int start, int end);

	/**
	* Returns an ordered range of all the projection cust hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection cust hierarchies
	* @param end the upper bound of the range of projection cust hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projection cust hierarchies
	*/
	public java.util.List<ProjectionCustHierarchy> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionCustHierarchy> orderByComparator);

	/**
	* Returns an ordered range of all the projection cust hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection cust hierarchies
	* @param end the upper bound of the range of projection cust hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of projection cust hierarchies
	*/
	public java.util.List<ProjectionCustHierarchy> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionCustHierarchy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the projection cust hierarchies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of projection cust hierarchies.
	*
	* @return the number of projection cust hierarchies
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}