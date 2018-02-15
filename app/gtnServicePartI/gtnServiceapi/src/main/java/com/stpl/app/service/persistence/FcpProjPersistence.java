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

import com.stpl.app.exception.NoSuchFcpProjException;
import com.stpl.app.model.FcpProj;

/**
 * The persistence interface for the fcp proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.FcpProjPersistenceImpl
 * @see FcpProjUtil
 * @generated
 */
@ProviderType
public interface FcpProjPersistence extends BasePersistence<FcpProj> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FcpProjUtil} to access the fcp proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the fcp proj in the entity cache if it is enabled.
	*
	* @param fcpProj the fcp proj
	*/
	public void cacheResult(FcpProj fcpProj);

	/**
	* Caches the fcp projs in the entity cache if it is enabled.
	*
	* @param fcpProjs the fcp projs
	*/
	public void cacheResult(java.util.List<FcpProj> fcpProjs);

	/**
	* Creates a new fcp proj with the primary key. Does not add the fcp proj to the database.
	*
	* @param fcpProjPK the primary key for the new fcp proj
	* @return the new fcp proj
	*/
	public FcpProj create(FcpProjPK fcpProjPK);

	/**
	* Removes the fcp proj with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fcpProjPK the primary key of the fcp proj
	* @return the fcp proj that was removed
	* @throws NoSuchFcpProjException if a fcp proj with the primary key could not be found
	*/
	public FcpProj remove(FcpProjPK fcpProjPK) throws NoSuchFcpProjException;

	public FcpProj updateImpl(FcpProj fcpProj);

	/**
	* Returns the fcp proj with the primary key or throws a {@link NoSuchFcpProjException} if it could not be found.
	*
	* @param fcpProjPK the primary key of the fcp proj
	* @return the fcp proj
	* @throws NoSuchFcpProjException if a fcp proj with the primary key could not be found
	*/
	public FcpProj findByPrimaryKey(FcpProjPK fcpProjPK)
		throws NoSuchFcpProjException;

	/**
	* Returns the fcp proj with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fcpProjPK the primary key of the fcp proj
	* @return the fcp proj, or <code>null</code> if a fcp proj with the primary key could not be found
	*/
	public FcpProj fetchByPrimaryKey(FcpProjPK fcpProjPK);

	@Override
	public java.util.Map<java.io.Serializable, FcpProj> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the fcp projs.
	*
	* @return the fcp projs
	*/
	public java.util.List<FcpProj> findAll();

	/**
	* Returns a range of all the fcp projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fcp projs
	* @param end the upper bound of the range of fcp projs (not inclusive)
	* @return the range of fcp projs
	*/
	public java.util.List<FcpProj> findAll(int start, int end);

	/**
	* Returns an ordered range of all the fcp projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fcp projs
	* @param end the upper bound of the range of fcp projs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of fcp projs
	*/
	public java.util.List<FcpProj> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FcpProj> orderByComparator);

	/**
	* Returns an ordered range of all the fcp projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of fcp projs
	* @param end the upper bound of the range of fcp projs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of fcp projs
	*/
	public java.util.List<FcpProj> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FcpProj> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the fcp projs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of fcp projs.
	*
	* @return the number of fcp projs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}