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

import com.stpl.app.exception.NoSuchStNmActualPpaException;
import com.stpl.app.model.StNmActualPpa;

/**
 * The persistence interface for the st nm actual ppa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StNmActualPpaPersistenceImpl
 * @see StNmActualPpaUtil
 * @generated
 */
@ProviderType
public interface StNmActualPpaPersistence extends BasePersistence<StNmActualPpa> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StNmActualPpaUtil} to access the st nm actual ppa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st nm actual ppa in the entity cache if it is enabled.
	*
	* @param stNmActualPpa the st nm actual ppa
	*/
	public void cacheResult(StNmActualPpa stNmActualPpa);

	/**
	* Caches the st nm actual ppas in the entity cache if it is enabled.
	*
	* @param stNmActualPpas the st nm actual ppas
	*/
	public void cacheResult(java.util.List<StNmActualPpa> stNmActualPpas);

	/**
	* Creates a new st nm actual ppa with the primary key. Does not add the st nm actual ppa to the database.
	*
	* @param stNmActualPpaPK the primary key for the new st nm actual ppa
	* @return the new st nm actual ppa
	*/
	public StNmActualPpa create(StNmActualPpaPK stNmActualPpaPK);

	/**
	* Removes the st nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stNmActualPpaPK the primary key of the st nm actual ppa
	* @return the st nm actual ppa that was removed
	* @throws NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
	*/
	public StNmActualPpa remove(StNmActualPpaPK stNmActualPpaPK)
		throws NoSuchStNmActualPpaException;

	public StNmActualPpa updateImpl(StNmActualPpa stNmActualPpa);

	/**
	* Returns the st nm actual ppa with the primary key or throws a {@link NoSuchStNmActualPpaException} if it could not be found.
	*
	* @param stNmActualPpaPK the primary key of the st nm actual ppa
	* @return the st nm actual ppa
	* @throws NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
	*/
	public StNmActualPpa findByPrimaryKey(StNmActualPpaPK stNmActualPpaPK)
		throws NoSuchStNmActualPpaException;

	/**
	* Returns the st nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stNmActualPpaPK the primary key of the st nm actual ppa
	* @return the st nm actual ppa, or <code>null</code> if a st nm actual ppa with the primary key could not be found
	*/
	public StNmActualPpa fetchByPrimaryKey(StNmActualPpaPK stNmActualPpaPK);

	@Override
	public java.util.Map<java.io.Serializable, StNmActualPpa> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st nm actual ppas.
	*
	* @return the st nm actual ppas
	*/
	public java.util.List<StNmActualPpa> findAll();

	/**
	* Returns a range of all the st nm actual ppas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual ppas
	* @param end the upper bound of the range of st nm actual ppas (not inclusive)
	* @return the range of st nm actual ppas
	*/
	public java.util.List<StNmActualPpa> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st nm actual ppas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual ppas
	* @param end the upper bound of the range of st nm actual ppas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st nm actual ppas
	*/
	public java.util.List<StNmActualPpa> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmActualPpa> orderByComparator);

	/**
	* Returns an ordered range of all the st nm actual ppas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st nm actual ppas
	* @param end the upper bound of the range of st nm actual ppas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st nm actual ppas
	*/
	public java.util.List<StNmActualPpa> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StNmActualPpa> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st nm actual ppas from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st nm actual ppas.
	*
	* @return the number of st nm actual ppas
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}