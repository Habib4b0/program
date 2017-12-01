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

import com.stpl.app.exception.NoSuchStChDiscountProjMasterException;
import com.stpl.app.model.StChDiscountProjMaster;

/**
 * The persistence interface for the st ch discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StChDiscountProjMasterPersistenceImpl
 * @see StChDiscountProjMasterUtil
 * @generated
 */
@ProviderType
public interface StChDiscountProjMasterPersistence extends BasePersistence<StChDiscountProjMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StChDiscountProjMasterUtil} to access the st ch discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st ch discount proj master in the entity cache if it is enabled.
	*
	* @param stChDiscountProjMaster the st ch discount proj master
	*/
	public void cacheResult(StChDiscountProjMaster stChDiscountProjMaster);

	/**
	* Caches the st ch discount proj masters in the entity cache if it is enabled.
	*
	* @param stChDiscountProjMasters the st ch discount proj masters
	*/
	public void cacheResult(
		java.util.List<StChDiscountProjMaster> stChDiscountProjMasters);

	/**
	* Creates a new st ch discount proj master with the primary key. Does not add the st ch discount proj master to the database.
	*
	* @param stChDiscountProjMasterPK the primary key for the new st ch discount proj master
	* @return the new st ch discount proj master
	*/
	public StChDiscountProjMaster create(
		StChDiscountProjMasterPK stChDiscountProjMasterPK);

	/**
	* Removes the st ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
	* @return the st ch discount proj master that was removed
	* @throws NoSuchStChDiscountProjMasterException if a st ch discount proj master with the primary key could not be found
	*/
	public StChDiscountProjMaster remove(
		StChDiscountProjMasterPK stChDiscountProjMasterPK)
		throws NoSuchStChDiscountProjMasterException;

	public StChDiscountProjMaster updateImpl(
		StChDiscountProjMaster stChDiscountProjMaster);

	/**
	* Returns the st ch discount proj master with the primary key or throws a {@link NoSuchStChDiscountProjMasterException} if it could not be found.
	*
	* @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
	* @return the st ch discount proj master
	* @throws NoSuchStChDiscountProjMasterException if a st ch discount proj master with the primary key could not be found
	*/
	public StChDiscountProjMaster findByPrimaryKey(
		StChDiscountProjMasterPK stChDiscountProjMasterPK)
		throws NoSuchStChDiscountProjMasterException;

	/**
	* Returns the st ch discount proj master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
	* @return the st ch discount proj master, or <code>null</code> if a st ch discount proj master with the primary key could not be found
	*/
	public StChDiscountProjMaster fetchByPrimaryKey(
		StChDiscountProjMasterPK stChDiscountProjMasterPK);

	@Override
	public java.util.Map<java.io.Serializable, StChDiscountProjMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st ch discount proj masters.
	*
	* @return the st ch discount proj masters
	*/
	public java.util.List<StChDiscountProjMaster> findAll();

	/**
	* Returns a range of all the st ch discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch discount proj masters
	* @param end the upper bound of the range of st ch discount proj masters (not inclusive)
	* @return the range of st ch discount proj masters
	*/
	public java.util.List<StChDiscountProjMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st ch discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch discount proj masters
	* @param end the upper bound of the range of st ch discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st ch discount proj masters
	*/
	public java.util.List<StChDiscountProjMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StChDiscountProjMaster> orderByComparator);

	/**
	* Returns an ordered range of all the st ch discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st ch discount proj masters
	* @param end the upper bound of the range of st ch discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st ch discount proj masters
	*/
	public java.util.List<StChDiscountProjMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StChDiscountProjMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st ch discount proj masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st ch discount proj masters.
	*
	* @return the number of st ch discount proj masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}