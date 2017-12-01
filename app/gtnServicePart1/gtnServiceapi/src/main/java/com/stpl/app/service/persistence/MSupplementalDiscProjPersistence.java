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

import com.stpl.app.exception.NoSuchMSupplementalDiscProjException;
import com.stpl.app.model.MSupplementalDiscProj;

/**
 * The persistence interface for the m supplemental disc proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.MSupplementalDiscProjPersistenceImpl
 * @see MSupplementalDiscProjUtil
 * @generated
 */
@ProviderType
public interface MSupplementalDiscProjPersistence extends BasePersistence<MSupplementalDiscProj> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MSupplementalDiscProjUtil} to access the m supplemental disc proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the m supplemental disc proj in the entity cache if it is enabled.
	*
	* @param mSupplementalDiscProj the m supplemental disc proj
	*/
	public void cacheResult(MSupplementalDiscProj mSupplementalDiscProj);

	/**
	* Caches the m supplemental disc projs in the entity cache if it is enabled.
	*
	* @param mSupplementalDiscProjs the m supplemental disc projs
	*/
	public void cacheResult(
		java.util.List<MSupplementalDiscProj> mSupplementalDiscProjs);

	/**
	* Creates a new m supplemental disc proj with the primary key. Does not add the m supplemental disc proj to the database.
	*
	* @param projectionDetailsSid the primary key for the new m supplemental disc proj
	* @return the new m supplemental disc proj
	*/
	public MSupplementalDiscProj create(int projectionDetailsSid);

	/**
	* Removes the m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the m supplemental disc proj
	* @return the m supplemental disc proj that was removed
	* @throws NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
	*/
	public MSupplementalDiscProj remove(int projectionDetailsSid)
		throws NoSuchMSupplementalDiscProjException;

	public MSupplementalDiscProj updateImpl(
		MSupplementalDiscProj mSupplementalDiscProj);

	/**
	* Returns the m supplemental disc proj with the primary key or throws a {@link NoSuchMSupplementalDiscProjException} if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the m supplemental disc proj
	* @return the m supplemental disc proj
	* @throws NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
	*/
	public MSupplementalDiscProj findByPrimaryKey(int projectionDetailsSid)
		throws NoSuchMSupplementalDiscProjException;

	/**
	* Returns the m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the m supplemental disc proj
	* @return the m supplemental disc proj, or <code>null</code> if a m supplemental disc proj with the primary key could not be found
	*/
	public MSupplementalDiscProj fetchByPrimaryKey(int projectionDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, MSupplementalDiscProj> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the m supplemental disc projs.
	*
	* @return the m supplemental disc projs
	*/
	public java.util.List<MSupplementalDiscProj> findAll();

	/**
	* Returns a range of all the m supplemental disc projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m supplemental disc projs
	* @param end the upper bound of the range of m supplemental disc projs (not inclusive)
	* @return the range of m supplemental disc projs
	*/
	public java.util.List<MSupplementalDiscProj> findAll(int start, int end);

	/**
	* Returns an ordered range of all the m supplemental disc projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m supplemental disc projs
	* @param end the upper bound of the range of m supplemental disc projs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of m supplemental disc projs
	*/
	public java.util.List<MSupplementalDiscProj> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSupplementalDiscProj> orderByComparator);

	/**
	* Returns an ordered range of all the m supplemental disc projs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m supplemental disc projs
	* @param end the upper bound of the range of m supplemental disc projs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of m supplemental disc projs
	*/
	public java.util.List<MSupplementalDiscProj> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MSupplementalDiscProj> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the m supplemental disc projs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of m supplemental disc projs.
	*
	* @return the number of m supplemental disc projs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}