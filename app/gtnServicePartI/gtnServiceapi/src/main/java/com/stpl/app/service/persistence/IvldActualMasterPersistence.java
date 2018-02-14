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

import com.stpl.app.exception.NoSuchIvldActualMasterException;
import com.stpl.app.model.IvldActualMaster;

/**
 * The persistence interface for the ivld actual master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldActualMasterPersistenceImpl
 * @see IvldActualMasterUtil
 * @generated
 */
@ProviderType
public interface IvldActualMasterPersistence extends BasePersistence<IvldActualMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldActualMasterUtil} to access the ivld actual master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld actual master in the entity cache if it is enabled.
	*
	* @param ivldActualMaster the ivld actual master
	*/
	public void cacheResult(IvldActualMaster ivldActualMaster);

	/**
	* Caches the ivld actual masters in the entity cache if it is enabled.
	*
	* @param ivldActualMasters the ivld actual masters
	*/
	public void cacheResult(java.util.List<IvldActualMaster> ivldActualMasters);

	/**
	* Creates a new ivld actual master with the primary key. Does not add the ivld actual master to the database.
	*
	* @param ivldActualMasterSid the primary key for the new ivld actual master
	* @return the new ivld actual master
	*/
	public IvldActualMaster create(int ivldActualMasterSid);

	/**
	* Removes the ivld actual master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldActualMasterSid the primary key of the ivld actual master
	* @return the ivld actual master that was removed
	* @throws NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
	*/
	public IvldActualMaster remove(int ivldActualMasterSid)
		throws NoSuchIvldActualMasterException;

	public IvldActualMaster updateImpl(IvldActualMaster ivldActualMaster);

	/**
	* Returns the ivld actual master with the primary key or throws a {@link NoSuchIvldActualMasterException} if it could not be found.
	*
	* @param ivldActualMasterSid the primary key of the ivld actual master
	* @return the ivld actual master
	* @throws NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
	*/
	public IvldActualMaster findByPrimaryKey(int ivldActualMasterSid)
		throws NoSuchIvldActualMasterException;

	/**
	* Returns the ivld actual master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldActualMasterSid the primary key of the ivld actual master
	* @return the ivld actual master, or <code>null</code> if a ivld actual master with the primary key could not be found
	*/
	public IvldActualMaster fetchByPrimaryKey(int ivldActualMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldActualMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld actual masters.
	*
	* @return the ivld actual masters
	*/
	public java.util.List<IvldActualMaster> findAll();

	/**
	* Returns a range of all the ivld actual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld actual masters
	* @param end the upper bound of the range of ivld actual masters (not inclusive)
	* @return the range of ivld actual masters
	*/
	public java.util.List<IvldActualMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld actual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld actual masters
	* @param end the upper bound of the range of ivld actual masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld actual masters
	*/
	public java.util.List<IvldActualMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldActualMaster> orderByComparator);

	/**
	* Returns an ordered range of all the ivld actual masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld actual masters
	* @param end the upper bound of the range of ivld actual masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld actual masters
	*/
	public java.util.List<IvldActualMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldActualMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld actual masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld actual masters.
	*
	* @return the number of ivld actual masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}