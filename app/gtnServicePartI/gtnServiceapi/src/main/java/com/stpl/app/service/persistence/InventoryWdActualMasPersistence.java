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

import com.stpl.app.exception.NoSuchInventoryWdActualMasException;
import com.stpl.app.model.InventoryWdActualMas;

/**
 * The persistence interface for the inventory wd actual mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.InventoryWdActualMasPersistenceImpl
 * @see InventoryWdActualMasUtil
 * @generated
 */
@ProviderType
public interface InventoryWdActualMasPersistence extends BasePersistence<InventoryWdActualMas> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link InventoryWdActualMasUtil} to access the inventory wd actual mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the inventory wd actual mas in the entity cache if it is enabled.
	*
	* @param inventoryWdActualMas the inventory wd actual mas
	*/
	public void cacheResult(InventoryWdActualMas inventoryWdActualMas);

	/**
	* Caches the inventory wd actual mases in the entity cache if it is enabled.
	*
	* @param inventoryWdActualMases the inventory wd actual mases
	*/
	public void cacheResult(
		java.util.List<InventoryWdActualMas> inventoryWdActualMases);

	/**
	* Creates a new inventory wd actual mas with the primary key. Does not add the inventory wd actual mas to the database.
	*
	* @param inventoryWdActualMasSid the primary key for the new inventory wd actual mas
	* @return the new inventory wd actual mas
	*/
	public InventoryWdActualMas create(int inventoryWdActualMasSid);

	/**
	* Removes the inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
	* @return the inventory wd actual mas that was removed
	* @throws NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
	*/
	public InventoryWdActualMas remove(int inventoryWdActualMasSid)
		throws NoSuchInventoryWdActualMasException;

	public InventoryWdActualMas updateImpl(
		InventoryWdActualMas inventoryWdActualMas);

	/**
	* Returns the inventory wd actual mas with the primary key or throws a {@link NoSuchInventoryWdActualMasException} if it could not be found.
	*
	* @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
	* @return the inventory wd actual mas
	* @throws NoSuchInventoryWdActualMasException if a inventory wd actual mas with the primary key could not be found
	*/
	public InventoryWdActualMas findByPrimaryKey(int inventoryWdActualMasSid)
		throws NoSuchInventoryWdActualMasException;

	/**
	* Returns the inventory wd actual mas with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
	* @return the inventory wd actual mas, or <code>null</code> if a inventory wd actual mas with the primary key could not be found
	*/
	public InventoryWdActualMas fetchByPrimaryKey(int inventoryWdActualMasSid);

	@Override
	public java.util.Map<java.io.Serializable, InventoryWdActualMas> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the inventory wd actual mases.
	*
	* @return the inventory wd actual mases
	*/
	public java.util.List<InventoryWdActualMas> findAll();

	/**
	* Returns a range of all the inventory wd actual mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd actual mases
	* @param end the upper bound of the range of inventory wd actual mases (not inclusive)
	* @return the range of inventory wd actual mases
	*/
	public java.util.List<InventoryWdActualMas> findAll(int start, int end);

	/**
	* Returns an ordered range of all the inventory wd actual mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd actual mases
	* @param end the upper bound of the range of inventory wd actual mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of inventory wd actual mases
	*/
	public java.util.List<InventoryWdActualMas> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<InventoryWdActualMas> orderByComparator);

	/**
	* Returns an ordered range of all the inventory wd actual mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of inventory wd actual mases
	* @param end the upper bound of the range of inventory wd actual mases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of inventory wd actual mases
	*/
	public java.util.List<InventoryWdActualMas> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<InventoryWdActualMas> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the inventory wd actual mases from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of inventory wd actual mases.
	*
	* @return the number of inventory wd actual mases
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}