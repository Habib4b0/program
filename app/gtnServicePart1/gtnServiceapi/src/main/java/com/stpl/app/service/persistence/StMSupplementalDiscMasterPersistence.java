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

import com.stpl.app.exception.NoSuchStMSupplementalDiscMasterException;
import com.stpl.app.model.StMSupplementalDiscMaster;

/**
 * The persistence interface for the st m supplemental disc master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.StMSupplementalDiscMasterPersistenceImpl
 * @see StMSupplementalDiscMasterUtil
 * @generated
 */
@ProviderType
public interface StMSupplementalDiscMasterPersistence extends BasePersistence<StMSupplementalDiscMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StMSupplementalDiscMasterUtil} to access the st m supplemental disc master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st m supplemental disc master in the entity cache if it is enabled.
	*
	* @param stMSupplementalDiscMaster the st m supplemental disc master
	*/
	public void cacheResult(StMSupplementalDiscMaster stMSupplementalDiscMaster);

	/**
	* Caches the st m supplemental disc masters in the entity cache if it is enabled.
	*
	* @param stMSupplementalDiscMasters the st m supplemental disc masters
	*/
	public void cacheResult(
		java.util.List<StMSupplementalDiscMaster> stMSupplementalDiscMasters);

	/**
	* Creates a new st m supplemental disc master with the primary key. Does not add the st m supplemental disc master to the database.
	*
	* @param stMSupplementalDiscMasterPK the primary key for the new st m supplemental disc master
	* @return the new st m supplemental disc master
	*/
	public StMSupplementalDiscMaster create(
		StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK);

	/**
	* Removes the st m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
	* @return the st m supplemental disc master that was removed
	* @throws NoSuchStMSupplementalDiscMasterException if a st m supplemental disc master with the primary key could not be found
	*/
	public StMSupplementalDiscMaster remove(
		StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
		throws NoSuchStMSupplementalDiscMasterException;

	public StMSupplementalDiscMaster updateImpl(
		StMSupplementalDiscMaster stMSupplementalDiscMaster);

	/**
	* Returns the st m supplemental disc master with the primary key or throws a {@link NoSuchStMSupplementalDiscMasterException} if it could not be found.
	*
	* @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
	* @return the st m supplemental disc master
	* @throws NoSuchStMSupplementalDiscMasterException if a st m supplemental disc master with the primary key could not be found
	*/
	public StMSupplementalDiscMaster findByPrimaryKey(
		StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
		throws NoSuchStMSupplementalDiscMasterException;

	/**
	* Returns the st m supplemental disc master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
	* @return the st m supplemental disc master, or <code>null</code> if a st m supplemental disc master with the primary key could not be found
	*/
	public StMSupplementalDiscMaster fetchByPrimaryKey(
		StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK);

	@Override
	public java.util.Map<java.io.Serializable, StMSupplementalDiscMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st m supplemental disc masters.
	*
	* @return the st m supplemental disc masters
	*/
	public java.util.List<StMSupplementalDiscMaster> findAll();

	/**
	* Returns a range of all the st m supplemental disc masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m supplemental disc masters
	* @param end the upper bound of the range of st m supplemental disc masters (not inclusive)
	* @return the range of st m supplemental disc masters
	*/
	public java.util.List<StMSupplementalDiscMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st m supplemental disc masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m supplemental disc masters
	* @param end the upper bound of the range of st m supplemental disc masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st m supplemental disc masters
	*/
	public java.util.List<StMSupplementalDiscMaster> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<StMSupplementalDiscMaster> orderByComparator);

	/**
	* Returns an ordered range of all the st m supplemental disc masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st m supplemental disc masters
	* @param end the upper bound of the range of st m supplemental disc masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st m supplemental disc masters
	*/
	public java.util.List<StMSupplementalDiscMaster> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<StMSupplementalDiscMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st m supplemental disc masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st m supplemental disc masters.
	*
	* @return the number of st m supplemental disc masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}