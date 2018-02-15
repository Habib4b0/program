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

import com.stpl.app.exception.NoSuchProjectionMasterException;
import com.stpl.app.model.ProjectionMaster;

/**
 * The persistence interface for the projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ProjectionMasterPersistenceImpl
 * @see ProjectionMasterUtil
 * @generated
 */
@ProviderType
public interface ProjectionMasterPersistence extends BasePersistence<ProjectionMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectionMasterUtil} to access the projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the projection master in the entity cache if it is enabled.
	*
	* @param projectionMaster the projection master
	*/
	public void cacheResult(ProjectionMaster projectionMaster);

	/**
	* Caches the projection masters in the entity cache if it is enabled.
	*
	* @param projectionMasters the projection masters
	*/
	public void cacheResult(java.util.List<ProjectionMaster> projectionMasters);

	/**
	* Creates a new projection master with the primary key. Does not add the projection master to the database.
	*
	* @param projectionMasterSid the primary key for the new projection master
	* @return the new projection master
	*/
	public ProjectionMaster create(int projectionMasterSid);

	/**
	* Removes the projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionMasterSid the primary key of the projection master
	* @return the projection master that was removed
	* @throws NoSuchProjectionMasterException if a projection master with the primary key could not be found
	*/
	public ProjectionMaster remove(int projectionMasterSid)
		throws NoSuchProjectionMasterException;

	public ProjectionMaster updateImpl(ProjectionMaster projectionMaster);

	/**
	* Returns the projection master with the primary key or throws a {@link NoSuchProjectionMasterException} if it could not be found.
	*
	* @param projectionMasterSid the primary key of the projection master
	* @return the projection master
	* @throws NoSuchProjectionMasterException if a projection master with the primary key could not be found
	*/
	public ProjectionMaster findByPrimaryKey(int projectionMasterSid)
		throws NoSuchProjectionMasterException;

	/**
	* Returns the projection master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionMasterSid the primary key of the projection master
	* @return the projection master, or <code>null</code> if a projection master with the primary key could not be found
	*/
	public ProjectionMaster fetchByPrimaryKey(int projectionMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, ProjectionMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the projection masters.
	*
	* @return the projection masters
	*/
	public java.util.List<ProjectionMaster> findAll();

	/**
	* Returns a range of all the projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection masters
	* @param end the upper bound of the range of projection masters (not inclusive)
	* @return the range of projection masters
	*/
	public java.util.List<ProjectionMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection masters
	* @param end the upper bound of the range of projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projection masters
	*/
	public java.util.List<ProjectionMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionMaster> orderByComparator);

	/**
	* Returns an ordered range of all the projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projection masters
	* @param end the upper bound of the range of projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of projection masters
	*/
	public java.util.List<ProjectionMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectionMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the projection masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of projection masters.
	*
	* @return the number of projection masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}