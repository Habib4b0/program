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

import com.stpl.app.exception.NoSuchChDiscountProjMasterException;
import com.stpl.app.model.ChDiscountProjMaster;

/**
 * The persistence interface for the ch discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.ChDiscountProjMasterPersistenceImpl
 * @see ChDiscountProjMasterUtil
 * @generated
 */
@ProviderType
public interface ChDiscountProjMasterPersistence extends BasePersistence<ChDiscountProjMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChDiscountProjMasterUtil} to access the ch discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ch discount proj master in the entity cache if it is enabled.
	*
	* @param chDiscountProjMaster the ch discount proj master
	*/
	public void cacheResult(ChDiscountProjMaster chDiscountProjMaster);

	/**
	* Caches the ch discount proj masters in the entity cache if it is enabled.
	*
	* @param chDiscountProjMasters the ch discount proj masters
	*/
	public void cacheResult(
		java.util.List<ChDiscountProjMaster> chDiscountProjMasters);

	/**
	* Creates a new ch discount proj master with the primary key. Does not add the ch discount proj master to the database.
	*
	* @param chDiscountProjMasterPK the primary key for the new ch discount proj master
	* @return the new ch discount proj master
	*/
	public ChDiscountProjMaster create(
		ChDiscountProjMasterPK chDiscountProjMasterPK);

	/**
	* Removes the ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chDiscountProjMasterPK the primary key of the ch discount proj master
	* @return the ch discount proj master that was removed
	* @throws NoSuchChDiscountProjMasterException if a ch discount proj master with the primary key could not be found
	*/
	public ChDiscountProjMaster remove(
		ChDiscountProjMasterPK chDiscountProjMasterPK)
		throws NoSuchChDiscountProjMasterException;

	public ChDiscountProjMaster updateImpl(
		ChDiscountProjMaster chDiscountProjMaster);

	/**
	* Returns the ch discount proj master with the primary key or throws a {@link NoSuchChDiscountProjMasterException} if it could not be found.
	*
	* @param chDiscountProjMasterPK the primary key of the ch discount proj master
	* @return the ch discount proj master
	* @throws NoSuchChDiscountProjMasterException if a ch discount proj master with the primary key could not be found
	*/
	public ChDiscountProjMaster findByPrimaryKey(
		ChDiscountProjMasterPK chDiscountProjMasterPK)
		throws NoSuchChDiscountProjMasterException;

	/**
	* Returns the ch discount proj master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param chDiscountProjMasterPK the primary key of the ch discount proj master
	* @return the ch discount proj master, or <code>null</code> if a ch discount proj master with the primary key could not be found
	*/
	public ChDiscountProjMaster fetchByPrimaryKey(
		ChDiscountProjMasterPK chDiscountProjMasterPK);

	@Override
	public java.util.Map<java.io.Serializable, ChDiscountProjMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ch discount proj masters.
	*
	* @return the ch discount proj masters
	*/
	public java.util.List<ChDiscountProjMaster> findAll();

	/**
	* Returns a range of all the ch discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch discount proj masters
	* @param end the upper bound of the range of ch discount proj masters (not inclusive)
	* @return the range of ch discount proj masters
	*/
	public java.util.List<ChDiscountProjMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ch discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch discount proj masters
	* @param end the upper bound of the range of ch discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ch discount proj masters
	*/
	public java.util.List<ChDiscountProjMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChDiscountProjMaster> orderByComparator);

	/**
	* Returns an ordered range of all the ch discount proj masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch discount proj masters
	* @param end the upper bound of the range of ch discount proj masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ch discount proj masters
	*/
	public java.util.List<ChDiscountProjMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChDiscountProjMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ch discount proj masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ch discount proj masters.
	*
	* @return the number of ch discount proj masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}