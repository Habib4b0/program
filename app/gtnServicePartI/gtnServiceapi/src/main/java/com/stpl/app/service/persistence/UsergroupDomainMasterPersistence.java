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

import com.stpl.app.exception.NoSuchUsergroupDomainMasterException;
import com.stpl.app.model.UsergroupDomainMaster;

/**
 * The persistence interface for the usergroup domain master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.UsergroupDomainMasterPersistenceImpl
 * @see UsergroupDomainMasterUtil
 * @generated
 */
@ProviderType
public interface UsergroupDomainMasterPersistence extends BasePersistence<UsergroupDomainMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UsergroupDomainMasterUtil} to access the usergroup domain master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the usergroup domain master in the entity cache if it is enabled.
	*
	* @param usergroupDomainMaster the usergroup domain master
	*/
	public void cacheResult(UsergroupDomainMaster usergroupDomainMaster);

	/**
	* Caches the usergroup domain masters in the entity cache if it is enabled.
	*
	* @param usergroupDomainMasters the usergroup domain masters
	*/
	public void cacheResult(
		java.util.List<UsergroupDomainMaster> usergroupDomainMasters);

	/**
	* Creates a new usergroup domain master with the primary key. Does not add the usergroup domain master to the database.
	*
	* @param usergroupDomainSid the primary key for the new usergroup domain master
	* @return the new usergroup domain master
	*/
	public UsergroupDomainMaster create(int usergroupDomainSid);

	/**
	* Removes the usergroup domain master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param usergroupDomainSid the primary key of the usergroup domain master
	* @return the usergroup domain master that was removed
	* @throws NoSuchUsergroupDomainMasterException if a usergroup domain master with the primary key could not be found
	*/
	public UsergroupDomainMaster remove(int usergroupDomainSid)
		throws NoSuchUsergroupDomainMasterException;

	public UsergroupDomainMaster updateImpl(
		UsergroupDomainMaster usergroupDomainMaster);

	/**
	* Returns the usergroup domain master with the primary key or throws a {@link NoSuchUsergroupDomainMasterException} if it could not be found.
	*
	* @param usergroupDomainSid the primary key of the usergroup domain master
	* @return the usergroup domain master
	* @throws NoSuchUsergroupDomainMasterException if a usergroup domain master with the primary key could not be found
	*/
	public UsergroupDomainMaster findByPrimaryKey(int usergroupDomainSid)
		throws NoSuchUsergroupDomainMasterException;

	/**
	* Returns the usergroup domain master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param usergroupDomainSid the primary key of the usergroup domain master
	* @return the usergroup domain master, or <code>null</code> if a usergroup domain master with the primary key could not be found
	*/
	public UsergroupDomainMaster fetchByPrimaryKey(int usergroupDomainSid);

	@Override
	public java.util.Map<java.io.Serializable, UsergroupDomainMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the usergroup domain masters.
	*
	* @return the usergroup domain masters
	*/
	public java.util.List<UsergroupDomainMaster> findAll();

	/**
	* Returns a range of all the usergroup domain masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of usergroup domain masters
	* @param end the upper bound of the range of usergroup domain masters (not inclusive)
	* @return the range of usergroup domain masters
	*/
	public java.util.List<UsergroupDomainMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the usergroup domain masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of usergroup domain masters
	* @param end the upper bound of the range of usergroup domain masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of usergroup domain masters
	*/
	public java.util.List<UsergroupDomainMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UsergroupDomainMaster> orderByComparator);

	/**
	* Returns an ordered range of all the usergroup domain masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of usergroup domain masters
	* @param end the upper bound of the range of usergroup domain masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of usergroup domain masters
	*/
	public java.util.List<UsergroupDomainMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UsergroupDomainMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the usergroup domain masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of usergroup domain masters.
	*
	* @return the number of usergroup domain masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}