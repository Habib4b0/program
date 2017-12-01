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

import com.stpl.app.exception.NoSuchMParityLookupException;
import com.stpl.app.model.MParityLookup;

/**
 * The persistence interface for the m parity lookup service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.MParityLookupPersistenceImpl
 * @see MParityLookupUtil
 * @generated
 */
@ProviderType
public interface MParityLookupPersistence extends BasePersistence<MParityLookup> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MParityLookupUtil} to access the m parity lookup persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the m parity lookup in the entity cache if it is enabled.
	*
	* @param mParityLookup the m parity lookup
	*/
	public void cacheResult(MParityLookup mParityLookup);

	/**
	* Caches the m parity lookups in the entity cache if it is enabled.
	*
	* @param mParityLookups the m parity lookups
	*/
	public void cacheResult(java.util.List<MParityLookup> mParityLookups);

	/**
	* Creates a new m parity lookup with the primary key. Does not add the m parity lookup to the database.
	*
	* @param mParityLookupSid the primary key for the new m parity lookup
	* @return the new m parity lookup
	*/
	public MParityLookup create(int mParityLookupSid);

	/**
	* Removes the m parity lookup with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mParityLookupSid the primary key of the m parity lookup
	* @return the m parity lookup that was removed
	* @throws NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
	*/
	public MParityLookup remove(int mParityLookupSid)
		throws NoSuchMParityLookupException;

	public MParityLookup updateImpl(MParityLookup mParityLookup);

	/**
	* Returns the m parity lookup with the primary key or throws a {@link NoSuchMParityLookupException} if it could not be found.
	*
	* @param mParityLookupSid the primary key of the m parity lookup
	* @return the m parity lookup
	* @throws NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
	*/
	public MParityLookup findByPrimaryKey(int mParityLookupSid)
		throws NoSuchMParityLookupException;

	/**
	* Returns the m parity lookup with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mParityLookupSid the primary key of the m parity lookup
	* @return the m parity lookup, or <code>null</code> if a m parity lookup with the primary key could not be found
	*/
	public MParityLookup fetchByPrimaryKey(int mParityLookupSid);

	@Override
	public java.util.Map<java.io.Serializable, MParityLookup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the m parity lookups.
	*
	* @return the m parity lookups
	*/
	public java.util.List<MParityLookup> findAll();

	/**
	* Returns a range of all the m parity lookups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m parity lookups
	* @param end the upper bound of the range of m parity lookups (not inclusive)
	* @return the range of m parity lookups
	*/
	public java.util.List<MParityLookup> findAll(int start, int end);

	/**
	* Returns an ordered range of all the m parity lookups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m parity lookups
	* @param end the upper bound of the range of m parity lookups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of m parity lookups
	*/
	public java.util.List<MParityLookup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MParityLookup> orderByComparator);

	/**
	* Returns an ordered range of all the m parity lookups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m parity lookups
	* @param end the upper bound of the range of m parity lookups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of m parity lookups
	*/
	public java.util.List<MParityLookup> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MParityLookup> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the m parity lookups from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of m parity lookups.
	*
	* @return the number of m parity lookups
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}