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

import com.stpl.app.exception.NoSuchUsergroupBusinessroleException;
import com.stpl.app.model.UsergroupBusinessrole;

/**
 * The persistence interface for the usergroup businessrole service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.UsergroupBusinessrolePersistenceImpl
 * @see UsergroupBusinessroleUtil
 * @generated
 */
@ProviderType
public interface UsergroupBusinessrolePersistence extends BasePersistence<UsergroupBusinessrole> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UsergroupBusinessroleUtil} to access the usergroup businessrole persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the usergroup businessrole in the entity cache if it is enabled.
	*
	* @param usergroupBusinessrole the usergroup businessrole
	*/
	public void cacheResult(UsergroupBusinessrole usergroupBusinessrole);

	/**
	* Caches the usergroup businessroles in the entity cache if it is enabled.
	*
	* @param usergroupBusinessroles the usergroup businessroles
	*/
	public void cacheResult(
		java.util.List<UsergroupBusinessrole> usergroupBusinessroles);

	/**
	* Creates a new usergroup businessrole with the primary key. Does not add the usergroup businessrole to the database.
	*
	* @param usergroupBusinessroleSid the primary key for the new usergroup businessrole
	* @return the new usergroup businessrole
	*/
	public UsergroupBusinessrole create(int usergroupBusinessroleSid);

	/**
	* Removes the usergroup businessrole with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param usergroupBusinessroleSid the primary key of the usergroup businessrole
	* @return the usergroup businessrole that was removed
	* @throws NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
	*/
	public UsergroupBusinessrole remove(int usergroupBusinessroleSid)
		throws NoSuchUsergroupBusinessroleException;

	public UsergroupBusinessrole updateImpl(
		UsergroupBusinessrole usergroupBusinessrole);

	/**
	* Returns the usergroup businessrole with the primary key or throws a {@link NoSuchUsergroupBusinessroleException} if it could not be found.
	*
	* @param usergroupBusinessroleSid the primary key of the usergroup businessrole
	* @return the usergroup businessrole
	* @throws NoSuchUsergroupBusinessroleException if a usergroup businessrole with the primary key could not be found
	*/
	public UsergroupBusinessrole findByPrimaryKey(int usergroupBusinessroleSid)
		throws NoSuchUsergroupBusinessroleException;

	/**
	* Returns the usergroup businessrole with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param usergroupBusinessroleSid the primary key of the usergroup businessrole
	* @return the usergroup businessrole, or <code>null</code> if a usergroup businessrole with the primary key could not be found
	*/
	public UsergroupBusinessrole fetchByPrimaryKey(int usergroupBusinessroleSid);

	@Override
	public java.util.Map<java.io.Serializable, UsergroupBusinessrole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the usergroup businessroles.
	*
	* @return the usergroup businessroles
	*/
	public java.util.List<UsergroupBusinessrole> findAll();

	/**
	* Returns a range of all the usergroup businessroles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of usergroup businessroles
	* @param end the upper bound of the range of usergroup businessroles (not inclusive)
	* @return the range of usergroup businessroles
	*/
	public java.util.List<UsergroupBusinessrole> findAll(int start, int end);

	/**
	* Returns an ordered range of all the usergroup businessroles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of usergroup businessroles
	* @param end the upper bound of the range of usergroup businessroles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of usergroup businessroles
	*/
	public java.util.List<UsergroupBusinessrole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UsergroupBusinessrole> orderByComparator);

	/**
	* Returns an ordered range of all the usergroup businessroles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of usergroup businessroles
	* @param end the upper bound of the range of usergroup businessroles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of usergroup businessroles
	*/
	public java.util.List<UsergroupBusinessrole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UsergroupBusinessrole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the usergroup businessroles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of usergroup businessroles.
	*
	* @return the number of usergroup businessroles
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}