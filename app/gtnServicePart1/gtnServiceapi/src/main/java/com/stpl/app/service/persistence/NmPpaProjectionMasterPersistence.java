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

import com.stpl.app.exception.NoSuchNmPpaProjectionMasterException;
import com.stpl.app.model.NmPpaProjectionMaster;

/**
 * The persistence interface for the nm ppa projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.NmPpaProjectionMasterPersistenceImpl
 * @see NmPpaProjectionMasterUtil
 * @generated
 */
@ProviderType
public interface NmPpaProjectionMasterPersistence extends BasePersistence<NmPpaProjectionMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NmPpaProjectionMasterUtil} to access the nm ppa projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the nm ppa projection master in the entity cache if it is enabled.
	*
	* @param nmPpaProjectionMaster the nm ppa projection master
	*/
	public void cacheResult(NmPpaProjectionMaster nmPpaProjectionMaster);

	/**
	* Caches the nm ppa projection masters in the entity cache if it is enabled.
	*
	* @param nmPpaProjectionMasters the nm ppa projection masters
	*/
	public void cacheResult(
		java.util.List<NmPpaProjectionMaster> nmPpaProjectionMasters);

	/**
	* Creates a new nm ppa projection master with the primary key. Does not add the nm ppa projection master to the database.
	*
	* @param projectionDetailsSid the primary key for the new nm ppa projection master
	* @return the new nm ppa projection master
	*/
	public NmPpaProjectionMaster create(int projectionDetailsSid);

	/**
	* Removes the nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectionDetailsSid the primary key of the nm ppa projection master
	* @return the nm ppa projection master that was removed
	* @throws NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
	*/
	public NmPpaProjectionMaster remove(int projectionDetailsSid)
		throws NoSuchNmPpaProjectionMasterException;

	public NmPpaProjectionMaster updateImpl(
		NmPpaProjectionMaster nmPpaProjectionMaster);

	/**
	* Returns the nm ppa projection master with the primary key or throws a {@link NoSuchNmPpaProjectionMasterException} if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the nm ppa projection master
	* @return the nm ppa projection master
	* @throws NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
	*/
	public NmPpaProjectionMaster findByPrimaryKey(int projectionDetailsSid)
		throws NoSuchNmPpaProjectionMasterException;

	/**
	* Returns the nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectionDetailsSid the primary key of the nm ppa projection master
	* @return the nm ppa projection master, or <code>null</code> if a nm ppa projection master with the primary key could not be found
	*/
	public NmPpaProjectionMaster fetchByPrimaryKey(int projectionDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, NmPpaProjectionMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the nm ppa projection masters.
	*
	* @return the nm ppa projection masters
	*/
	public java.util.List<NmPpaProjectionMaster> findAll();

	/**
	* Returns a range of all the nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projection masters
	* @param end the upper bound of the range of nm ppa projection masters (not inclusive)
	* @return the range of nm ppa projection masters
	*/
	public java.util.List<NmPpaProjectionMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projection masters
	* @param end the upper bound of the range of nm ppa projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of nm ppa projection masters
	*/
	public java.util.List<NmPpaProjectionMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NmPpaProjectionMaster> orderByComparator);

	/**
	* Returns an ordered range of all the nm ppa projection masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm ppa projection masters
	* @param end the upper bound of the range of nm ppa projection masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of nm ppa projection masters
	*/
	public java.util.List<NmPpaProjectionMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NmPpaProjectionMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the nm ppa projection masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of nm ppa projection masters.
	*
	* @return the number of nm ppa projection masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}