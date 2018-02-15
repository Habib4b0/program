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

import com.stpl.app.exception.NoSuchIvldLotMasterException;
import com.stpl.app.model.IvldLotMaster;

/**
 * The persistence interface for the ivld lot master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldLotMasterPersistenceImpl
 * @see IvldLotMasterUtil
 * @generated
 */
@ProviderType
public interface IvldLotMasterPersistence extends BasePersistence<IvldLotMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldLotMasterUtil} to access the ivld lot master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld lot master in the entity cache if it is enabled.
	*
	* @param ivldLotMaster the ivld lot master
	*/
	public void cacheResult(IvldLotMaster ivldLotMaster);

	/**
	* Caches the ivld lot masters in the entity cache if it is enabled.
	*
	* @param ivldLotMasters the ivld lot masters
	*/
	public void cacheResult(java.util.List<IvldLotMaster> ivldLotMasters);

	/**
	* Creates a new ivld lot master with the primary key. Does not add the ivld lot master to the database.
	*
	* @param ivldLotMasterSid the primary key for the new ivld lot master
	* @return the new ivld lot master
	*/
	public IvldLotMaster create(int ivldLotMasterSid);

	/**
	* Removes the ivld lot master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldLotMasterSid the primary key of the ivld lot master
	* @return the ivld lot master that was removed
	* @throws NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
	*/
	public IvldLotMaster remove(int ivldLotMasterSid)
		throws NoSuchIvldLotMasterException;

	public IvldLotMaster updateImpl(IvldLotMaster ivldLotMaster);

	/**
	* Returns the ivld lot master with the primary key or throws a {@link NoSuchIvldLotMasterException} if it could not be found.
	*
	* @param ivldLotMasterSid the primary key of the ivld lot master
	* @return the ivld lot master
	* @throws NoSuchIvldLotMasterException if a ivld lot master with the primary key could not be found
	*/
	public IvldLotMaster findByPrimaryKey(int ivldLotMasterSid)
		throws NoSuchIvldLotMasterException;

	/**
	* Returns the ivld lot master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldLotMasterSid the primary key of the ivld lot master
	* @return the ivld lot master, or <code>null</code> if a ivld lot master with the primary key could not be found
	*/
	public IvldLotMaster fetchByPrimaryKey(int ivldLotMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldLotMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld lot masters.
	*
	* @return the ivld lot masters
	*/
	public java.util.List<IvldLotMaster> findAll();

	/**
	* Returns a range of all the ivld lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld lot masters
	* @param end the upper bound of the range of ivld lot masters (not inclusive)
	* @return the range of ivld lot masters
	*/
	public java.util.List<IvldLotMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld lot masters
	* @param end the upper bound of the range of ivld lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld lot masters
	*/
	public java.util.List<IvldLotMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldLotMaster> orderByComparator);

	/**
	* Returns an ordered range of all the ivld lot masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldLotMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld lot masters
	* @param end the upper bound of the range of ivld lot masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld lot masters
	*/
	public java.util.List<IvldLotMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldLotMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld lot masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld lot masters.
	*
	* @return the number of ivld lot masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}