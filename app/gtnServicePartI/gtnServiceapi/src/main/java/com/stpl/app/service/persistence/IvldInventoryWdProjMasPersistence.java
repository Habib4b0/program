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

import com.stpl.app.exception.NoSuchIvldInventoryWdProjMasException;
import com.stpl.app.model.IvldInventoryWdProjMas;

/**
 * The persistence interface for the ivld inventory wd proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldInventoryWdProjMasPersistenceImpl
 * @see IvldInventoryWdProjMasUtil
 * @generated
 */
@ProviderType
public interface IvldInventoryWdProjMasPersistence extends BasePersistence<IvldInventoryWdProjMas> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldInventoryWdProjMasUtil} to access the ivld inventory wd proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld inventory wd proj mas in the entity cache if it is enabled.
	*
	* @param ivldInventoryWdProjMas the ivld inventory wd proj mas
	*/
	public void cacheResult(IvldInventoryWdProjMas ivldInventoryWdProjMas);

	/**
	* Caches the ivld inventory wd proj mases in the entity cache if it is enabled.
	*
	* @param ivldInventoryWdProjMases the ivld inventory wd proj mases
	*/
	public void cacheResult(
		java.util.List<IvldInventoryWdProjMas> ivldInventoryWdProjMases);

	/**
	* Creates a new ivld inventory wd proj mas with the primary key. Does not add the ivld inventory wd proj mas to the database.
	*
	* @param ivldInventoryWdProjMasSid the primary key for the new ivld inventory wd proj mas
	* @return the new ivld inventory wd proj mas
	*/
	public IvldInventoryWdProjMas create(int ivldInventoryWdProjMasSid);

	/**
	* Removes the ivld inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
	* @return the ivld inventory wd proj mas that was removed
	* @throws NoSuchIvldInventoryWdProjMasException if a ivld inventory wd proj mas with the primary key could not be found
	*/
	public IvldInventoryWdProjMas remove(int ivldInventoryWdProjMasSid)
		throws NoSuchIvldInventoryWdProjMasException;

	public IvldInventoryWdProjMas updateImpl(
		IvldInventoryWdProjMas ivldInventoryWdProjMas);

	/**
	* Returns the ivld inventory wd proj mas with the primary key or throws a {@link NoSuchIvldInventoryWdProjMasException} if it could not be found.
	*
	* @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
	* @return the ivld inventory wd proj mas
	* @throws NoSuchIvldInventoryWdProjMasException if a ivld inventory wd proj mas with the primary key could not be found
	*/
	public IvldInventoryWdProjMas findByPrimaryKey(
		int ivldInventoryWdProjMasSid)
		throws NoSuchIvldInventoryWdProjMasException;

	/**
	* Returns the ivld inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
	* @return the ivld inventory wd proj mas, or <code>null</code> if a ivld inventory wd proj mas with the primary key could not be found
	*/
	public IvldInventoryWdProjMas fetchByPrimaryKey(
		int ivldInventoryWdProjMasSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldInventoryWdProjMas> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld inventory wd proj mases.
	*
	* @return the ivld inventory wd proj mases
	*/
	public java.util.List<IvldInventoryWdProjMas> findAll();

	/**
	* Returns a range of all the ivld inventory wd proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld inventory wd proj mases
	* @param end the upper bound of the range of ivld inventory wd proj mases (not inclusive)
	* @return the range of ivld inventory wd proj mases
	*/
	public java.util.List<IvldInventoryWdProjMas> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld inventory wd proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld inventory wd proj mases
	* @param end the upper bound of the range of ivld inventory wd proj mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld inventory wd proj mases
	*/
	public java.util.List<IvldInventoryWdProjMas> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldInventoryWdProjMas> orderByComparator);

	/**
	* Returns an ordered range of all the ivld inventory wd proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld inventory wd proj mases
	* @param end the upper bound of the range of ivld inventory wd proj mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld inventory wd proj mases
	*/
	public java.util.List<IvldInventoryWdProjMas> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldInventoryWdProjMas> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld inventory wd proj mases from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld inventory wd proj mases.
	*
	* @return the number of ivld inventory wd proj mases
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}