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

import com.stpl.app.parttwo.exception.NoSuchIvldCompanyMasterException;
import com.stpl.app.parttwo.model.IvldCompanyMaster;

/**
 * The persistence interface for the ivld company master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldCompanyMasterPersistenceImpl
 * @see IvldCompanyMasterUtil
 * @generated
 */
@ProviderType
public interface IvldCompanyMasterPersistence extends BasePersistence<IvldCompanyMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldCompanyMasterUtil} to access the ivld company master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld company master in the entity cache if it is enabled.
	*
	* @param ivldCompanyMaster the ivld company master
	*/
	public void cacheResult(IvldCompanyMaster ivldCompanyMaster);

	/**
	* Caches the ivld company masters in the entity cache if it is enabled.
	*
	* @param ivldCompanyMasters the ivld company masters
	*/
	public void cacheResult(
		java.util.List<IvldCompanyMaster> ivldCompanyMasters);

	/**
	* Creates a new ivld company master with the primary key. Does not add the ivld company master to the database.
	*
	* @param ivldCompanyMasterSid the primary key for the new ivld company master
	* @return the new ivld company master
	*/
	public IvldCompanyMaster create(int ivldCompanyMasterSid);

	/**
	* Removes the ivld company master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyMasterSid the primary key of the ivld company master
	* @return the ivld company master that was removed
	* @throws NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
	*/
	public IvldCompanyMaster remove(int ivldCompanyMasterSid)
		throws NoSuchIvldCompanyMasterException;

	public IvldCompanyMaster updateImpl(IvldCompanyMaster ivldCompanyMaster);

	/**
	* Returns the ivld company master with the primary key or throws a {@link NoSuchIvldCompanyMasterException} if it could not be found.
	*
	* @param ivldCompanyMasterSid the primary key of the ivld company master
	* @return the ivld company master
	* @throws NoSuchIvldCompanyMasterException if a ivld company master with the primary key could not be found
	*/
	public IvldCompanyMaster findByPrimaryKey(int ivldCompanyMasterSid)
		throws NoSuchIvldCompanyMasterException;

	/**
	* Returns the ivld company master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldCompanyMasterSid the primary key of the ivld company master
	* @return the ivld company master, or <code>null</code> if a ivld company master with the primary key could not be found
	*/
	public IvldCompanyMaster fetchByPrimaryKey(int ivldCompanyMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldCompanyMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld company masters.
	*
	* @return the ivld company masters
	*/
	public java.util.List<IvldCompanyMaster> findAll();

	/**
	* Returns a range of all the ivld company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company masters
	* @param end the upper bound of the range of ivld company masters (not inclusive)
	* @return the range of ivld company masters
	*/
	public java.util.List<IvldCompanyMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company masters
	* @param end the upper bound of the range of ivld company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld company masters
	*/
	public java.util.List<IvldCompanyMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldCompanyMaster> orderByComparator);

	/**
	* Returns an ordered range of all the ivld company masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company masters
	* @param end the upper bound of the range of ivld company masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld company masters
	*/
	public java.util.List<IvldCompanyMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldCompanyMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld company masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld company masters.
	*
	* @return the number of ivld company masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}