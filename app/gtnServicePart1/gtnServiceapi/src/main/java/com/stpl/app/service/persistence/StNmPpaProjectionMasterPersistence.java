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

import com.stpl.app.exception.NoSuchStNmPpaProjectionMasterException;
import com.stpl.app.model.StNmPpaProjectionMaster;

/**
 * The persistence interface for the st nm ppa projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StNmPpaProjectionMasterPersistenceImpl
 * @see StNmPpaProjectionMasterUtil
 * @generated
 */
@ProviderType
public interface StNmPpaProjectionMasterPersistence extends BasePersistence<StNmPpaProjectionMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StNmPpaProjectionMasterUtil} to access the st nm ppa projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st nm ppa projection master in the entity cache if it is enabled.
	*
	* @param stNmPpaProjectionMaster the st nm ppa projection master
	*/
	public void cacheResult(StNmPpaProjectionMaster stNmPpaProjectionMaster);

	/**
	* Caches the st nm ppa projection masters in the entity cache if it is enabled.
	*
	* @param stNmPpaProjectionMasters the st nm ppa projection masters
	*/
	public void cacheResult(
		java.util.List<StNmPpaProjectionMaster> stNmPpaProjectionMasters);

	/**
	* Creates a new st nm ppa projection master with the primary key. Does not add the st nm ppa projection master to the database.
	*
	* @param stNmPpaProjectionMasterPK the primary key for the new st nm ppa projection master
	* @return the new st nm ppa projection master
	*/
	public StNmPpaProjectionMaster create(
		StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK);

	/**
	* Removes the st nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
	* @return the st nm ppa projection master that was removed
	* @throws NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
	*/
	public StNmPpaProjectionMaster remove(
		StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
		throws NoSuchStNmPpaProjectionMasterException;

	public StNmPpaProjectionMaster updateImpl(
		StNmPpaProjectionMaster stNmPpaProjectionMaster);

	/**
	* Returns the st nm ppa projection master with the primary key or throws a {@link NoSuchStNmPpaProjectionMasterException} if it could not be found.
	*
	* @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
	* @return the st nm ppa projection master
	* @throws NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
	*/
	public StNmPpaProjectionMaster findByPrimaryKey(
		StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
		throws NoSuchStNmPpaProjectionMasterException;

	/**
	* Returns the st nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
	* @return the st nm ppa projection master, or <code>null</code> if a st nm ppa projection master with the primary key could not be found
	*/
	public StNmPpaProjectionMaster fetchByPrimaryKey(
		StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK);

	@Override
	public java.util.Map<java.io.Serializable, StNmPpaProjectionMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st nm ppa projection masters.
	*
	* @return the st nm ppa projection masters
	*/
	public java.util.List<StNmPpaProjectionMaster> findAll();

	/**
	* Returns a range of all the st nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm ppa projection masters
	* @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
	* @return the range of st nm ppa projection masters
	*/
	public java.util.List<StNmPpaProjectionMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm ppa projection masters
	* @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm ppa projection masters
	*/
	public java.util.List<StNmPpaProjectionMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmPpaProjectionMaster> orderByComparator);

	/**
	* Returns an ordered range of all the st nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm ppa projection masters
	* @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm ppa projection masters
	*/
	public java.util.List<StNmPpaProjectionMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmPpaProjectionMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st nm ppa projection masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st nm ppa projection masters.
	*
	* @return the number of st nm ppa projection masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}