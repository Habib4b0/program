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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.parttwo.exception.NoSuchCffProdHierarchyException;
import com.stpl.app.parttwo.model.CffProdHierarchy;

/**
 * The persistence interface for the cff prod hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.CffProdHierarchyPersistenceImpl
 * @see CffProdHierarchyUtil
 * @generated
 */
@ProviderType
public interface CffProdHierarchyPersistence extends BasePersistence<CffProdHierarchy> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CffProdHierarchyUtil} to access the cff prod hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cff prod hierarchy in the entity cache if it is enabled.
	*
	* @param cffProdHierarchy the cff prod hierarchy
	*/
	public void cacheResult(CffProdHierarchy cffProdHierarchy);

	/**
	* Caches the cff prod hierarchies in the entity cache if it is enabled.
	*
	* @param cffProdHierarchies the cff prod hierarchies
	*/
	public void cacheResult(java.util.List<CffProdHierarchy> cffProdHierarchies);

	/**
	* Creates a new cff prod hierarchy with the primary key. Does not add the cff prod hierarchy to the database.
	*
	* @param cffProdHierarchySid the primary key for the new cff prod hierarchy
	* @return the new cff prod hierarchy
	*/
	public CffProdHierarchy create(int cffProdHierarchySid);

	/**
	* Removes the cff prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffProdHierarchySid the primary key of the cff prod hierarchy
	* @return the cff prod hierarchy that was removed
	* @throws NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
	*/
	public CffProdHierarchy remove(int cffProdHierarchySid)
		throws NoSuchCffProdHierarchyException;

	public CffProdHierarchy updateImpl(CffProdHierarchy cffProdHierarchy);

	/**
	* Returns the cff prod hierarchy with the primary key or throws a {@link NoSuchCffProdHierarchyException} if it could not be found.
	*
	* @param cffProdHierarchySid the primary key of the cff prod hierarchy
	* @return the cff prod hierarchy
	* @throws NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
	*/
	public CffProdHierarchy findByPrimaryKey(int cffProdHierarchySid)
		throws NoSuchCffProdHierarchyException;

	/**
	* Returns the cff prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffProdHierarchySid the primary key of the cff prod hierarchy
	* @return the cff prod hierarchy, or <code>null</code> if a cff prod hierarchy with the primary key could not be found
	*/
	public CffProdHierarchy fetchByPrimaryKey(int cffProdHierarchySid);

	@Override
	public java.util.Map<java.io.Serializable, CffProdHierarchy> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cff prod hierarchies.
	*
	* @return the cff prod hierarchies
	*/
	public java.util.List<CffProdHierarchy> findAll();

	/**
	* Returns a range of all the cff prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff prod hierarchies
	* @param end the upper bound of the range of cff prod hierarchies (not inclusive)
	* @return the range of cff prod hierarchies
	*/
	public java.util.List<CffProdHierarchy> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cff prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff prod hierarchies
	* @param end the upper bound of the range of cff prod hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff prod hierarchies
	*/
	public java.util.List<CffProdHierarchy> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffProdHierarchy> orderByComparator);

	/**
	* Returns an ordered range of all the cff prod hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff prod hierarchies
	* @param end the upper bound of the range of cff prod hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff prod hierarchies
	*/
	public java.util.List<CffProdHierarchy> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffProdHierarchy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cff prod hierarchies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cff prod hierarchies.
	*
	* @return the number of cff prod hierarchies
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}