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

import com.stpl.app.parttwo.exception.NoSuchVwcompanyIdentifierException;
import com.stpl.app.parttwo.model.VwcompanyIdentifier;

/**
 * The persistence interface for the vwcompany identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.VwcompanyIdentifierPersistenceImpl
 * @see VwcompanyIdentifierUtil
 * @generated
 */
@ProviderType
public interface VwcompanyIdentifierPersistence extends BasePersistence<VwcompanyIdentifier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VwcompanyIdentifierUtil} to access the vwcompany identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the vwcompany identifier in the entity cache if it is enabled.
	*
	* @param vwcompanyIdentifier the vwcompany identifier
	*/
	public void cacheResult(VwcompanyIdentifier vwcompanyIdentifier);

	/**
	* Caches the vwcompany identifiers in the entity cache if it is enabled.
	*
	* @param vwcompanyIdentifiers the vwcompany identifiers
	*/
	public void cacheResult(
		java.util.List<VwcompanyIdentifier> vwcompanyIdentifiers);

	/**
	* Creates a new vwcompany identifier with the primary key. Does not add the vwcompany identifier to the database.
	*
	* @param companyIdentifierSid the primary key for the new vwcompany identifier
	* @return the new vwcompany identifier
	*/
	public VwcompanyIdentifier create(int companyIdentifierSid);

	/**
	* Removes the vwcompany identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param companyIdentifierSid the primary key of the vwcompany identifier
	* @return the vwcompany identifier that was removed
	* @throws NoSuchVwcompanyIdentifierException if a vwcompany identifier with the primary key could not be found
	*/
	public VwcompanyIdentifier remove(int companyIdentifierSid)
		throws NoSuchVwcompanyIdentifierException;

	public VwcompanyIdentifier updateImpl(
		VwcompanyIdentifier vwcompanyIdentifier);

	/**
	* Returns the vwcompany identifier with the primary key or throws a {@link NoSuchVwcompanyIdentifierException} if it could not be found.
	*
	* @param companyIdentifierSid the primary key of the vwcompany identifier
	* @return the vwcompany identifier
	* @throws NoSuchVwcompanyIdentifierException if a vwcompany identifier with the primary key could not be found
	*/
	public VwcompanyIdentifier findByPrimaryKey(int companyIdentifierSid)
		throws NoSuchVwcompanyIdentifierException;

	/**
	* Returns the vwcompany identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param companyIdentifierSid the primary key of the vwcompany identifier
	* @return the vwcompany identifier, or <code>null</code> if a vwcompany identifier with the primary key could not be found
	*/
	public VwcompanyIdentifier fetchByPrimaryKey(int companyIdentifierSid);

	@Override
	public java.util.Map<java.io.Serializable, VwcompanyIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the vwcompany identifiers.
	*
	* @return the vwcompany identifiers
	*/
	public java.util.List<VwcompanyIdentifier> findAll();

	/**
	* Returns a range of all the vwcompany identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vwcompany identifiers
	* @param end the upper bound of the range of vwcompany identifiers (not inclusive)
	* @return the range of vwcompany identifiers
	*/
	public java.util.List<VwcompanyIdentifier> findAll(int start, int end);

	/**
	* Returns an ordered range of all the vwcompany identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vwcompany identifiers
	* @param end the upper bound of the range of vwcompany identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of vwcompany identifiers
	*/
	public java.util.List<VwcompanyIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwcompanyIdentifier> orderByComparator);

	/**
	* Returns an ordered range of all the vwcompany identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwcompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vwcompany identifiers
	* @param end the upper bound of the range of vwcompany identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of vwcompany identifiers
	*/
	public java.util.List<VwcompanyIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<VwcompanyIdentifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the vwcompany identifiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of vwcompany identifiers.
	*
	* @return the number of vwcompany identifiers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}