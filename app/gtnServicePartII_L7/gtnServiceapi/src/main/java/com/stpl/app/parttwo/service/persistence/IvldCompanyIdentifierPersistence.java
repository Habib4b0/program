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

import com.stpl.app.parttwo.exception.NoSuchIvldCompanyIdentifierException;
import com.stpl.app.parttwo.model.IvldCompanyIdentifier;

/**
 * The persistence interface for the ivld company identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldCompanyIdentifierPersistenceImpl
 * @see IvldCompanyIdentifierUtil
 * @generated
 */
@ProviderType
public interface IvldCompanyIdentifierPersistence extends BasePersistence<IvldCompanyIdentifier> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldCompanyIdentifierUtil} to access the ivld company identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld company identifier in the entity cache if it is enabled.
	*
	* @param ivldCompanyIdentifier the ivld company identifier
	*/
	public void cacheResult(IvldCompanyIdentifier ivldCompanyIdentifier);

	/**
	* Caches the ivld company identifiers in the entity cache if it is enabled.
	*
	* @param ivldCompanyIdentifiers the ivld company identifiers
	*/
	public void cacheResult(
		java.util.List<IvldCompanyIdentifier> ivldCompanyIdentifiers);

	/**
	* Creates a new ivld company identifier with the primary key. Does not add the ivld company identifier to the database.
	*
	* @param ivldCompanyIdentifierSid the primary key for the new ivld company identifier
	* @return the new ivld company identifier
	*/
	public IvldCompanyIdentifier create(int ivldCompanyIdentifierSid);

	/**
	* Removes the ivld company identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	* @return the ivld company identifier that was removed
	* @throws NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
	*/
	public IvldCompanyIdentifier remove(int ivldCompanyIdentifierSid)
		throws NoSuchIvldCompanyIdentifierException;

	public IvldCompanyIdentifier updateImpl(
		IvldCompanyIdentifier ivldCompanyIdentifier);

	/**
	* Returns the ivld company identifier with the primary key or throws a {@link NoSuchIvldCompanyIdentifierException} if it could not be found.
	*
	* @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	* @return the ivld company identifier
	* @throws NoSuchIvldCompanyIdentifierException if a ivld company identifier with the primary key could not be found
	*/
	public IvldCompanyIdentifier findByPrimaryKey(int ivldCompanyIdentifierSid)
		throws NoSuchIvldCompanyIdentifierException;

	/**
	* Returns the ivld company identifier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	* @return the ivld company identifier, or <code>null</code> if a ivld company identifier with the primary key could not be found
	*/
	public IvldCompanyIdentifier fetchByPrimaryKey(int ivldCompanyIdentifierSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldCompanyIdentifier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld company identifiers.
	*
	* @return the ivld company identifiers
	*/
	public java.util.List<IvldCompanyIdentifier> findAll();

	/**
	* Returns a range of all the ivld company identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company identifiers
	* @param end the upper bound of the range of ivld company identifiers (not inclusive)
	* @return the range of ivld company identifiers
	*/
	public java.util.List<IvldCompanyIdentifier> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld company identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company identifiers
	* @param end the upper bound of the range of ivld company identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld company identifiers
	*/
	public java.util.List<IvldCompanyIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldCompanyIdentifier> orderByComparator);

	/**
	* Returns an ordered range of all the ivld company identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company identifiers
	* @param end the upper bound of the range of ivld company identifiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld company identifiers
	*/
	public java.util.List<IvldCompanyIdentifier> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldCompanyIdentifier> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld company identifiers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld company identifiers.
	*
	* @return the number of ivld company identifiers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}