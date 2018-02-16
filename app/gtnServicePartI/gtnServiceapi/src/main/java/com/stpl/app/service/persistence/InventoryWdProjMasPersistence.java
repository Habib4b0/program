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

import com.stpl.app.exception.NoSuchInventoryWdProjMasException;
import com.stpl.app.model.InventoryWdProjMas;

/**
 * The persistence interface for the inventory wd proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.InventoryWdProjMasPersistenceImpl
 * @see InventoryWdProjMasUtil
 * @generated
 */
@ProviderType
public interface InventoryWdProjMasPersistence extends BasePersistence<InventoryWdProjMas> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link InventoryWdProjMasUtil} to access the inventory wd proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the inventory wd proj mas in the entity cache if it is enabled.
	*
	* @param inventoryWdProjMas the inventory wd proj mas
	*/
	public void cacheResult(InventoryWdProjMas inventoryWdProjMas);

	/**
	* Caches the inventory wd proj mases in the entity cache if it is enabled.
	*
	* @param inventoryWdProjMases the inventory wd proj mases
	*/
	public void cacheResult(
		java.util.List<InventoryWdProjMas> inventoryWdProjMases);

	/**
	* Creates a new inventory wd proj mas with the primary key. Does not add the inventory wd proj mas to the database.
	*
	* @param inventoryWdProjMasSid the primary key for the new inventory wd proj mas
	* @return the new inventory wd proj mas
	*/
	public InventoryWdProjMas create(int inventoryWdProjMasSid);

	/**
	* Removes the inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
	* @return the inventory wd proj mas that was removed
	* @throws NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
	*/
	public InventoryWdProjMas remove(int inventoryWdProjMasSid)
		throws NoSuchInventoryWdProjMasException;

	public InventoryWdProjMas updateImpl(InventoryWdProjMas inventoryWdProjMas);

	/**
	* Returns the inventory wd proj mas with the primary key or throws a {@link NoSuchInventoryWdProjMasException} if it could not be found.
	*
	* @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
	* @return the inventory wd proj mas
	* @throws NoSuchInventoryWdProjMasException if a inventory wd proj mas with the primary key could not be found
	*/
	public InventoryWdProjMas findByPrimaryKey(int inventoryWdProjMasSid)
		throws NoSuchInventoryWdProjMasException;

	/**
	* Returns the inventory wd proj mas with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
	* @return the inventory wd proj mas, or <code>null</code> if a inventory wd proj mas with the primary key could not be found
	*/
	public InventoryWdProjMas fetchByPrimaryKey(int inventoryWdProjMasSid);

	@Override
	public java.util.Map<java.io.Serializable, InventoryWdProjMas> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the inventory wd proj mases.
	*
	* @return the inventory wd proj mases
	*/
	public java.util.List<InventoryWdProjMas> findAll();

	/**
	* Returns a range of all the inventory wd proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd proj mases
	* @param end the upper bound of the range of inventory wd proj mases (not inclusive)
	* @return the range of inventory wd proj mases
	*/
	public java.util.List<InventoryWdProjMas> findAll(int start, int end);

	/**
	* Returns an ordered range of all the inventory wd proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd proj mases
	* @param end the upper bound of the range of inventory wd proj mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of inventory wd proj mases
	*/
	public java.util.List<InventoryWdProjMas> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<InventoryWdProjMas> orderByComparator);

	/**
	* Returns an ordered range of all the inventory wd proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd proj mases
	* @param end the upper bound of the range of inventory wd proj mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of inventory wd proj mases
	*/
	public java.util.List<InventoryWdProjMas> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<InventoryWdProjMas> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the inventory wd proj mases from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of inventory wd proj mases.
	*
	* @return the number of inventory wd proj mases
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}