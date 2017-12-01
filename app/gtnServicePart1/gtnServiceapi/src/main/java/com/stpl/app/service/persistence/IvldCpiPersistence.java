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

import com.stpl.app.exception.NoSuchIvldCpiException;
import com.stpl.app.model.IvldCpi;

/**
 * The persistence interface for the ivld cpi service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldCpiPersistenceImpl
 * @see IvldCpiUtil
 * @generated
 */
@ProviderType
public interface IvldCpiPersistence extends BasePersistence<IvldCpi> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldCpiUtil} to access the ivld cpi persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld cpi in the entity cache if it is enabled.
	*
	* @param ivldCpi the ivld cpi
	*/
	public void cacheResult(IvldCpi ivldCpi);

	/**
	* Caches the ivld cpis in the entity cache if it is enabled.
	*
	* @param ivldCpis the ivld cpis
	*/
	public void cacheResult(java.util.List<IvldCpi> ivldCpis);

	/**
	* Creates a new ivld cpi with the primary key. Does not add the ivld cpi to the database.
	*
	* @param ivldCpiSid the primary key for the new ivld cpi
	* @return the new ivld cpi
	*/
	public IvldCpi create(int ivldCpiSid);

	/**
	* Removes the ivld cpi with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCpiSid the primary key of the ivld cpi
	* @return the ivld cpi that was removed
	* @throws NoSuchIvldCpiException if a ivld cpi with the primary key could not be found
	*/
	public IvldCpi remove(int ivldCpiSid) throws NoSuchIvldCpiException;

	public IvldCpi updateImpl(IvldCpi ivldCpi);

	/**
	* Returns the ivld cpi with the primary key or throws a {@link NoSuchIvldCpiException} if it could not be found.
	*
	* @param ivldCpiSid the primary key of the ivld cpi
	* @return the ivld cpi
	* @throws NoSuchIvldCpiException if a ivld cpi with the primary key could not be found
	*/
	public IvldCpi findByPrimaryKey(int ivldCpiSid)
		throws NoSuchIvldCpiException;

	/**
	* Returns the ivld cpi with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldCpiSid the primary key of the ivld cpi
	* @return the ivld cpi, or <code>null</code> if a ivld cpi with the primary key could not be found
	*/
	public IvldCpi fetchByPrimaryKey(int ivldCpiSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldCpi> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld cpis.
	*
	* @return the ivld cpis
	*/
	public java.util.List<IvldCpi> findAll();

	/**
	* Returns a range of all the ivld cpis.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld cpis
	* @param end the upper bound of the range of ivld cpis (not inclusive)
	* @return the range of ivld cpis
	*/
	public java.util.List<IvldCpi> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld cpis.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld cpis
	* @param end the upper bound of the range of ivld cpis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld cpis
	*/
	public java.util.List<IvldCpi> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldCpi> orderByComparator);

	/**
	* Returns an ordered range of all the ivld cpis.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCpiModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld cpis
	* @param end the upper bound of the range of ivld cpis (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld cpis
	*/
	public java.util.List<IvldCpi> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldCpi> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld cpis from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld cpis.
	*
	* @return the number of ivld cpis
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}