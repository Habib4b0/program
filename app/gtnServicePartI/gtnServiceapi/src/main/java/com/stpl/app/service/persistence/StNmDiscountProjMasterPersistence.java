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

import com.stpl.app.exception.NoSuchStNmDiscountProjMasterException;
import com.stpl.app.model.StNmDiscountProjMaster;

/**
 * The persistence interface for the st nm discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StNmDiscountProjMasterPersistenceImpl
 * @see StNmDiscountProjMasterUtil
 * @generated
 */
@ProviderType
public interface StNmDiscountProjMasterPersistence extends BasePersistence<StNmDiscountProjMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StNmDiscountProjMasterUtil} to access the st nm discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st nm discount proj master in the entity cache if it is enabled.
	*
	* @param stNmDiscountProjMaster the st nm discount proj master
	*/
	public void cacheResult(StNmDiscountProjMaster stNmDiscountProjMaster);

	/**
	* Caches the st nm discount proj masters in the entity cache if it is enabled.
	*
	* @param stNmDiscountProjMasters the st nm discount proj masters
	*/
	public void cacheResult(
		java.util.List<StNmDiscountProjMaster> stNmDiscountProjMasters);

	/**
	* Creates a new st nm discount proj master with the primary key. Does not add the st nm discount proj master to the database.
	*
	* @param stNmDiscountProjMasterPK the primary key for the new st nm discount proj master
	* @return the new st nm discount proj master
	*/
	public StNmDiscountProjMaster create(
		StNmDiscountProjMasterPK stNmDiscountProjMasterPK);

	/**
	* Removes the st nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
	* @return the st nm discount proj master that was removed
	* @throws NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
	*/
	public StNmDiscountProjMaster remove(
		StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
		throws NoSuchStNmDiscountProjMasterException;

	public StNmDiscountProjMaster updateImpl(
		StNmDiscountProjMaster stNmDiscountProjMaster);

	/**
	* Returns the st nm discount proj master with the primary key or throws a {@link NoSuchStNmDiscountProjMasterException} if it could not be found.
	*
	* @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
	* @return the st nm discount proj master
	* @throws NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
	*/
	public StNmDiscountProjMaster findByPrimaryKey(
		StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
		throws NoSuchStNmDiscountProjMasterException;

	/**
	* Returns the st nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
	* @return the st nm discount proj master, or <code>null</code> if a st nm discount proj master with the primary key could not be found
	*/
	public StNmDiscountProjMaster fetchByPrimaryKey(
		StNmDiscountProjMasterPK stNmDiscountProjMasterPK);

	@Override
	public java.util.Map<java.io.Serializable, StNmDiscountProjMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st nm discount proj masters.
	*
	* @return the st nm discount proj masters
	*/
	public java.util.List<StNmDiscountProjMaster> findAll();

	/**
	* Returns a range of all the st nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount proj masters
	* @param end the upper bound of the range of st nm discount proj masters (not inclusive)
	* @return the range of st nm discount proj masters
	*/
	public java.util.List<StNmDiscountProjMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount proj masters
	* @param end the upper bound of the range of st nm discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm discount proj masters
	*/
	public java.util.List<StNmDiscountProjMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmDiscountProjMaster> orderByComparator);

	/**
	* Returns an ordered range of all the st nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm discount proj masters
	* @param end the upper bound of the range of st nm discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm discount proj masters
	*/
	public java.util.List<StNmDiscountProjMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmDiscountProjMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st nm discount proj masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st nm discount proj masters.
	*
	* @return the number of st nm discount proj masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}