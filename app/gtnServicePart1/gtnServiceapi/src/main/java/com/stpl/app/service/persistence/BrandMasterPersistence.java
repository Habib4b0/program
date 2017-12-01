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

import com.stpl.app.exception.NoSuchBrandMasterException;
import com.stpl.app.model.BrandMaster;

/**
 * The persistence interface for the brand master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.BrandMasterPersistenceImpl
 * @see BrandMasterUtil
 * @generated
 */
@ProviderType
public interface BrandMasterPersistence extends BasePersistence<BrandMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BrandMasterUtil} to access the brand master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the brand master in the entity cache if it is enabled.
	*
	* @param brandMaster the brand master
	*/
	public void cacheResult(BrandMaster brandMaster);

	/**
	* Caches the brand masters in the entity cache if it is enabled.
	*
	* @param brandMasters the brand masters
	*/
	public void cacheResult(java.util.List<BrandMaster> brandMasters);

	/**
	* Creates a new brand master with the primary key. Does not add the brand master to the database.
	*
	* @param brandMasterSid the primary key for the new brand master
	* @return the new brand master
	*/
	public BrandMaster create(int brandMasterSid);

	/**
	* Removes the brand master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param brandMasterSid the primary key of the brand master
	* @return the brand master that was removed
	* @throws NoSuchBrandMasterException if a brand master with the primary key could not be found
	*/
	public BrandMaster remove(int brandMasterSid)
		throws NoSuchBrandMasterException;

	public BrandMaster updateImpl(BrandMaster brandMaster);

	/**
	* Returns the brand master with the primary key or throws a {@link NoSuchBrandMasterException} if it could not be found.
	*
	* @param brandMasterSid the primary key of the brand master
	* @return the brand master
	* @throws NoSuchBrandMasterException if a brand master with the primary key could not be found
	*/
	public BrandMaster findByPrimaryKey(int brandMasterSid)
		throws NoSuchBrandMasterException;

	/**
	* Returns the brand master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param brandMasterSid the primary key of the brand master
	* @return the brand master, or <code>null</code> if a brand master with the primary key could not be found
	*/
	public BrandMaster fetchByPrimaryKey(int brandMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, BrandMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the brand masters.
	*
	* @return the brand masters
	*/
	public java.util.List<BrandMaster> findAll();

	/**
	* Returns a range of all the brand masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of brand masters
	* @param end the upper bound of the range of brand masters (not inclusive)
	* @return the range of brand masters
	*/
	public java.util.List<BrandMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the brand masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of brand masters
	* @param end the upper bound of the range of brand masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of brand masters
	*/
	public java.util.List<BrandMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BrandMaster> orderByComparator);

	/**
	* Returns an ordered range of all the brand masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of brand masters
	* @param end the upper bound of the range of brand masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of brand masters
	*/
	public java.util.List<BrandMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BrandMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the brand masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of brand masters.
	*
	* @return the number of brand masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}