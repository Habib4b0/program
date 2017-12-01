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

import com.stpl.app.exception.NoSuchIvldGlCostCenterException;
import com.stpl.app.model.IvldGlCostCenter;

/**
 * The persistence interface for the ivld gl cost center service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.IvldGlCostCenterPersistenceImpl
 * @see IvldGlCostCenterUtil
 * @generated
 */
@ProviderType
public interface IvldGlCostCenterPersistence extends BasePersistence<IvldGlCostCenter> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldGlCostCenterUtil} to access the ivld gl cost center persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld gl cost center in the entity cache if it is enabled.
	*
	* @param ivldGlCostCenter the ivld gl cost center
	*/
	public void cacheResult(IvldGlCostCenter ivldGlCostCenter);

	/**
	* Caches the ivld gl cost centers in the entity cache if it is enabled.
	*
	* @param ivldGlCostCenters the ivld gl cost centers
	*/
	public void cacheResult(java.util.List<IvldGlCostCenter> ivldGlCostCenters);

	/**
	* Creates a new ivld gl cost center with the primary key. Does not add the ivld gl cost center to the database.
	*
	* @param ivldGlCostCenterSid the primary key for the new ivld gl cost center
	* @return the new ivld gl cost center
	*/
	public IvldGlCostCenter create(int ivldGlCostCenterSid);

	/**
	* Removes the ivld gl cost center with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldGlCostCenterSid the primary key of the ivld gl cost center
	* @return the ivld gl cost center that was removed
	* @throws NoSuchIvldGlCostCenterException if a ivld gl cost center with the primary key could not be found
	*/
	public IvldGlCostCenter remove(int ivldGlCostCenterSid)
		throws NoSuchIvldGlCostCenterException;

	public IvldGlCostCenter updateImpl(IvldGlCostCenter ivldGlCostCenter);

	/**
	* Returns the ivld gl cost center with the primary key or throws a {@link NoSuchIvldGlCostCenterException} if it could not be found.
	*
	* @param ivldGlCostCenterSid the primary key of the ivld gl cost center
	* @return the ivld gl cost center
	* @throws NoSuchIvldGlCostCenterException if a ivld gl cost center with the primary key could not be found
	*/
	public IvldGlCostCenter findByPrimaryKey(int ivldGlCostCenterSid)
		throws NoSuchIvldGlCostCenterException;

	/**
	* Returns the ivld gl cost center with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldGlCostCenterSid the primary key of the ivld gl cost center
	* @return the ivld gl cost center, or <code>null</code> if a ivld gl cost center with the primary key could not be found
	*/
	public IvldGlCostCenter fetchByPrimaryKey(int ivldGlCostCenterSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldGlCostCenter> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld gl cost centers.
	*
	* @return the ivld gl cost centers
	*/
	public java.util.List<IvldGlCostCenter> findAll();

	/**
	* Returns a range of all the ivld gl cost centers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld gl cost centers
	* @param end the upper bound of the range of ivld gl cost centers (not inclusive)
	* @return the range of ivld gl cost centers
	*/
	public java.util.List<IvldGlCostCenter> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld gl cost centers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld gl cost centers
	* @param end the upper bound of the range of ivld gl cost centers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld gl cost centers
	*/
	public java.util.List<IvldGlCostCenter> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldGlCostCenter> orderByComparator);

	/**
	* Returns an ordered range of all the ivld gl cost centers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld gl cost centers
	* @param end the upper bound of the range of ivld gl cost centers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld gl cost centers
	*/
	public java.util.List<IvldGlCostCenter> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldGlCostCenter> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld gl cost centers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld gl cost centers.
	*
	* @return the number of ivld gl cost centers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}