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

import com.stpl.app.parttwo.exception.NoSuchIvldItemMasterException;
import com.stpl.app.parttwo.model.IvldItemMaster;

/**
 * The persistence interface for the ivld item master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldItemMasterPersistenceImpl
 * @see IvldItemMasterUtil
 * @generated
 */
@ProviderType
public interface IvldItemMasterPersistence extends BasePersistence<IvldItemMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldItemMasterUtil} to access the ivld item master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld item master in the entity cache if it is enabled.
	*
	* @param ivldItemMaster the ivld item master
	*/
	public void cacheResult(IvldItemMaster ivldItemMaster);

	/**
	* Caches the ivld item masters in the entity cache if it is enabled.
	*
	* @param ivldItemMasters the ivld item masters
	*/
	public void cacheResult(java.util.List<IvldItemMaster> ivldItemMasters);

	/**
	* Creates a new ivld item master with the primary key. Does not add the ivld item master to the database.
	*
	* @param ivldItemMasterSid the primary key for the new ivld item master
	* @return the new ivld item master
	*/
	public IvldItemMaster create(int ivldItemMasterSid);

	/**
	* Removes the ivld item master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemMasterSid the primary key of the ivld item master
	* @return the ivld item master that was removed
	* @throws NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
	*/
	public IvldItemMaster remove(int ivldItemMasterSid)
		throws NoSuchIvldItemMasterException;

	public IvldItemMaster updateImpl(IvldItemMaster ivldItemMaster);

	/**
	* Returns the ivld item master with the primary key or throws a {@link NoSuchIvldItemMasterException} if it could not be found.
	*
	* @param ivldItemMasterSid the primary key of the ivld item master
	* @return the ivld item master
	* @throws NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
	*/
	public IvldItemMaster findByPrimaryKey(int ivldItemMasterSid)
		throws NoSuchIvldItemMasterException;

	/**
	* Returns the ivld item master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldItemMasterSid the primary key of the ivld item master
	* @return the ivld item master, or <code>null</code> if a ivld item master with the primary key could not be found
	*/
	public IvldItemMaster fetchByPrimaryKey(int ivldItemMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldItemMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld item masters.
	*
	* @return the ivld item masters
	*/
	public java.util.List<IvldItemMaster> findAll();

	/**
	* Returns a range of all the ivld item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item masters
	* @param end the upper bound of the range of ivld item masters (not inclusive)
	* @return the range of ivld item masters
	*/
	public java.util.List<IvldItemMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item masters
	* @param end the upper bound of the range of ivld item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld item masters
	*/
	public java.util.List<IvldItemMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldItemMaster> orderByComparator);

	/**
	* Returns an ordered range of all the ivld item masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item masters
	* @param end the upper bound of the range of ivld item masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld item masters
	*/
	public java.util.List<IvldItemMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldItemMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld item masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld item masters.
	*
	* @return the number of ivld item masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}