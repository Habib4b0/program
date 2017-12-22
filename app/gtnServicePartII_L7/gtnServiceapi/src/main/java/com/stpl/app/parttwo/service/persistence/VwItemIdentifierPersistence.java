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

import com.stpl.app.parttwo.exception.NoSuchVwItemIdentifierException;
import com.stpl.app.parttwo.model.VwItemIdentifier;

/**
 * The persistence interface for the vw item identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.VwItemIdentifierPersistenceImpl
 * @see VwItemIdentifierUtil
 * @generated
 */
@ProviderType
public interface VwItemIdentifierPersistence extends BasePersistence<VwItemIdentifier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwItemIdentifierUtil} to access the vw item identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vw item identifier in the entity cache if it is enabled.
	*
	* @param vwItemIdentifier the vw item identifier
	*/
	public void cacheResult(VwItemIdentifier vwItemIdentifier);

	/**
	* Caches the vw item identifiers in the entity cache if it is enabled.
	*
	* @param vwItemIdentifiers the vw item identifiers
	*/
	public void cacheResult(java.util.List<VwItemIdentifier> vwItemIdentifiers);

	/**
	* Creates a new vw item identifier with the primary key. Does not add the vw item identifier to the database.
	*
	* @param itemIdentifierSid the primary key for the new vw item identifier
	* @return the new vw item identifier
	*/
	public VwItemIdentifier create(int itemIdentifierSid);

	/**
	* Removes the vw item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param itemIdentifierSid the primary key of the vw item identifier
	* @return the vw item identifier that was removed
	* @throws NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
	*/
	public VwItemIdentifier remove(int itemIdentifierSid)
		throws NoSuchVwItemIdentifierException;

	public VwItemIdentifier updateImpl(VwItemIdentifier vwItemIdentifier);

	/**
	* Returns the vw item identifier with the primary key or throws a {@link NoSuchVwItemIdentifierException} if it could not be found.
	*
	* @param itemIdentifierSid the primary key of the vw item identifier
	* @return the vw item identifier
	* @throws NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
	*/
	public VwItemIdentifier findByPrimaryKey(int itemIdentifierSid)
		throws NoSuchVwItemIdentifierException;

	/**
	* Returns the vw item identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param itemIdentifierSid the primary key of the vw item identifier
	* @return the vw item identifier, or <code>null</code> if a vw item identifier with the primary key could not be found
	*/
	public VwItemIdentifier fetchByPrimaryKey(int itemIdentifierSid);

	@Override
	public java.util.Map<java.io.Serializable, VwItemIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vw item identifiers.
	*
	* @return the vw item identifiers
	*/
	public java.util.List<VwItemIdentifier> findAll();

	/**
	* Returns a range of all the vw item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item identifiers
	* @param end the upper bound of the range of vw item identifiers (not inclusive)
	* @return the range of vw item identifiers
	*/
	public java.util.List<VwItemIdentifier> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vw item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item identifiers
	* @param end the upper bound of the range of vw item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vw item identifiers
	*/
	public java.util.List<VwItemIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwItemIdentifier> orderByComparator);

	/**
	* Returns an ordered range of all the vw item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw item identifiers
	* @param end the upper bound of the range of vw item identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vw item identifiers
	*/
	public java.util.List<VwItemIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwItemIdentifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vw item identifiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vw item identifiers.
	*
	* @return the number of vw item identifiers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}