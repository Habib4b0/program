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

import com.stpl.app.exception.NoSuchCustomViewMasterException;
import com.stpl.app.model.CustomViewMaster;

/**
 * The persistence interface for the custom view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CustomViewMasterPersistenceImpl
 * @see CustomViewMasterUtil
 * @generated
 */
@ProviderType
public interface CustomViewMasterPersistence extends BasePersistence<CustomViewMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CustomViewMasterUtil} to access the custom view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the custom view master in the entity cache if it is enabled.
	*
	* @param customViewMaster the custom view master
	*/
	public void cacheResult(CustomViewMaster customViewMaster);

	/**
	* Caches the custom view masters in the entity cache if it is enabled.
	*
	* @param customViewMasters the custom view masters
	*/
	public void cacheResult(java.util.List<CustomViewMaster> customViewMasters);

	/**
	* Creates a new custom view master with the primary key. Does not add the custom view master to the database.
	*
	* @param customViewMasterSid the primary key for the new custom view master
	* @return the new custom view master
	*/
	public CustomViewMaster create(int customViewMasterSid);

	/**
	* Removes the custom view master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param customViewMasterSid the primary key of the custom view master
	* @return the custom view master that was removed
	* @throws NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
	*/
	public CustomViewMaster remove(int customViewMasterSid)
		throws NoSuchCustomViewMasterException;

	public CustomViewMaster updateImpl(CustomViewMaster customViewMaster);

	/**
	* Returns the custom view master with the primary key or throws a {@link NoSuchCustomViewMasterException} if it could not be found.
	*
	* @param customViewMasterSid the primary key of the custom view master
	* @return the custom view master
	* @throws NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
	*/
	public CustomViewMaster findByPrimaryKey(int customViewMasterSid)
		throws NoSuchCustomViewMasterException;

	/**
	* Returns the custom view master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param customViewMasterSid the primary key of the custom view master
	* @return the custom view master, or <code>null</code> if a custom view master with the primary key could not be found
	*/
	public CustomViewMaster fetchByPrimaryKey(int customViewMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, CustomViewMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the custom view masters.
	*
	* @return the custom view masters
	*/
	public java.util.List<CustomViewMaster> findAll();

	/**
	* Returns a range of all the custom view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view masters
	* @param end the upper bound of the range of custom view masters (not inclusive)
	* @return the range of custom view masters
	*/
	public java.util.List<CustomViewMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the custom view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view masters
	* @param end the upper bound of the range of custom view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of custom view masters
	*/
	public java.util.List<CustomViewMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomViewMaster> orderByComparator);

	/**
	* Returns an ordered range of all the custom view masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of custom view masters
	* @param end the upper bound of the range of custom view masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of custom view masters
	*/
	public java.util.List<CustomViewMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CustomViewMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the custom view masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of custom view masters.
	*
	* @return the number of custom view masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}