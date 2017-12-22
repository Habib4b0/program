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

import com.stpl.app.parttwo.exception.NoSuchCffCustHierarchyException;
import com.stpl.app.parttwo.model.CffCustHierarchy;

/**
 * The persistence interface for the cff cust hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.CffCustHierarchyPersistenceImpl
 * @see CffCustHierarchyUtil
 * @generated
 */
@ProviderType
public interface CffCustHierarchyPersistence extends BasePersistence<CffCustHierarchy> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CffCustHierarchyUtil} to access the cff cust hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cff cust hierarchy in the entity cache if it is enabled.
	*
	* @param cffCustHierarchy the cff cust hierarchy
	*/
	public void cacheResult(CffCustHierarchy cffCustHierarchy);

	/**
	* Caches the cff cust hierarchies in the entity cache if it is enabled.
	*
	* @param cffCustHierarchies the cff cust hierarchies
	*/
	public void cacheResult(java.util.List<CffCustHierarchy> cffCustHierarchies);

	/**
	* Creates a new cff cust hierarchy with the primary key. Does not add the cff cust hierarchy to the database.
	*
	* @param cffCustHierarchySid the primary key for the new cff cust hierarchy
	* @return the new cff cust hierarchy
	*/
	public CffCustHierarchy create(int cffCustHierarchySid);

	/**
	* Removes the cff cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cffCustHierarchySid the primary key of the cff cust hierarchy
	* @return the cff cust hierarchy that was removed
	* @throws NoSuchCffCustHierarchyException if a cff cust hierarchy with the primary key could not be found
	*/
	public CffCustHierarchy remove(int cffCustHierarchySid)
		throws NoSuchCffCustHierarchyException;

	public CffCustHierarchy updateImpl(CffCustHierarchy cffCustHierarchy);

	/**
	* Returns the cff cust hierarchy with the primary key or throws a {@link NoSuchCffCustHierarchyException} if it could not be found.
	*
	* @param cffCustHierarchySid the primary key of the cff cust hierarchy
	* @return the cff cust hierarchy
	* @throws NoSuchCffCustHierarchyException if a cff cust hierarchy with the primary key could not be found
	*/
	public CffCustHierarchy findByPrimaryKey(int cffCustHierarchySid)
		throws NoSuchCffCustHierarchyException;

	/**
	* Returns the cff cust hierarchy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cffCustHierarchySid the primary key of the cff cust hierarchy
	* @return the cff cust hierarchy, or <code>null</code> if a cff cust hierarchy with the primary key could not be found
	*/
	public CffCustHierarchy fetchByPrimaryKey(int cffCustHierarchySid);

	@Override
	public java.util.Map<java.io.Serializable, CffCustHierarchy> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cff cust hierarchies.
	*
	* @return the cff cust hierarchies
	*/
	public java.util.List<CffCustHierarchy> findAll();

	/**
	* Returns a range of all the cff cust hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff cust hierarchies
	* @param end the upper bound of the range of cff cust hierarchies (not inclusive)
	* @return the range of cff cust hierarchies
	*/
	public java.util.List<CffCustHierarchy> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cff cust hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff cust hierarchies
	* @param end the upper bound of the range of cff cust hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cff cust hierarchies
	*/
	public java.util.List<CffCustHierarchy> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffCustHierarchy> orderByComparator);

	/**
	* Returns an ordered range of all the cff cust hierarchies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cff cust hierarchies
	* @param end the upper bound of the range of cff cust hierarchies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cff cust hierarchies
	*/
	public java.util.List<CffCustHierarchy> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CffCustHierarchy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cff cust hierarchies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cff cust hierarchies.
	*
	* @return the number of cff cust hierarchies
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}