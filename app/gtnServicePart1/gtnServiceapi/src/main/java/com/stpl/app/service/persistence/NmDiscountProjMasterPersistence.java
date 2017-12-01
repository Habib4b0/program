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

import com.stpl.app.exception.NoSuchNmDiscountProjMasterException;
import com.stpl.app.model.NmDiscountProjMaster;

/**
 * The persistence interface for the nm discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.NmDiscountProjMasterPersistenceImpl
 * @see NmDiscountProjMasterUtil
 * @generated
 */
@ProviderType
public interface NmDiscountProjMasterPersistence extends BasePersistence<NmDiscountProjMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NmDiscountProjMasterUtil} to access the nm discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the nm discount proj master in the entity cache if it is enabled.
	*
	* @param nmDiscountProjMaster the nm discount proj master
	*/
	public void cacheResult(NmDiscountProjMaster nmDiscountProjMaster);

	/**
	* Caches the nm discount proj masters in the entity cache if it is enabled.
	*
	* @param nmDiscountProjMasters the nm discount proj masters
	*/
	public void cacheResult(
		java.util.List<NmDiscountProjMaster> nmDiscountProjMasters);

	/**
	* Creates a new nm discount proj master with the primary key. Does not add the nm discount proj master to the database.
	*
	* @param projectionDetailsSid the primary key for the new nm discount proj master
	* @return the new nm discount proj master
	*/
	public NmDiscountProjMaster create(int projectionDetailsSid);

	/**
	* Removes the nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the nm discount proj master
	* @return the nm discount proj master that was removed
	* @throws NoSuchNmDiscountProjMasterException if a nm discount proj master with the primary key could not be found
	*/
	public NmDiscountProjMaster remove(int projectionDetailsSid)
		throws NoSuchNmDiscountProjMasterException;

	public NmDiscountProjMaster updateImpl(
		NmDiscountProjMaster nmDiscountProjMaster);

	/**
	* Returns the nm discount proj master with the primary key or throws a {@link NoSuchNmDiscountProjMasterException} if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the nm discount proj master
	* @return the nm discount proj master
	* @throws NoSuchNmDiscountProjMasterException if a nm discount proj master with the primary key could not be found
	*/
	public NmDiscountProjMaster findByPrimaryKey(int projectionDetailsSid)
		throws NoSuchNmDiscountProjMasterException;

	/**
	* Returns the nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the nm discount proj master
	* @return the nm discount proj master, or <code>null</code> if a nm discount proj master with the primary key could not be found
	*/
	public NmDiscountProjMaster fetchByPrimaryKey(int projectionDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, NmDiscountProjMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the nm discount proj masters.
	*
	* @return the nm discount proj masters
	*/
	public java.util.List<NmDiscountProjMaster> findAll();

	/**
	* Returns a range of all the nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm discount proj masters
	* @param end the upper bound of the range of nm discount proj masters (not inclusive)
	* @return the range of nm discount proj masters
	*/
	public java.util.List<NmDiscountProjMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm discount proj masters
	* @param end the upper bound of the range of nm discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm discount proj masters
	*/
	public java.util.List<NmDiscountProjMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NmDiscountProjMaster> orderByComparator);

	/**
	* Returns an ordered range of all the nm discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm discount proj masters
	* @param end the upper bound of the range of nm discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm discount proj masters
	*/
	public java.util.List<NmDiscountProjMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NmDiscountProjMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the nm discount proj masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of nm discount proj masters.
	*
	* @return the number of nm discount proj masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}