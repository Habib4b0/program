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

import com.stpl.app.parttwo.exception.NoSuchIvldAccrualInboundException;
import com.stpl.app.parttwo.model.IvldAccrualInbound;

/**
 * The persistence interface for the ivld accrual inbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.IvldAccrualInboundPersistenceImpl
 * @see IvldAccrualInboundUtil
 * @generated
 */
@ProviderType
public interface IvldAccrualInboundPersistence extends BasePersistence<IvldAccrualInbound> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IvldAccrualInboundUtil} to access the ivld accrual inbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ivld accrual inbound in the entity cache if it is enabled.
	*
	* @param ivldAccrualInbound the ivld accrual inbound
	*/
	public void cacheResult(IvldAccrualInbound ivldAccrualInbound);

	/**
	* Caches the ivld accrual inbounds in the entity cache if it is enabled.
	*
	* @param ivldAccrualInbounds the ivld accrual inbounds
	*/
	public void cacheResult(
		java.util.List<IvldAccrualInbound> ivldAccrualInbounds);

	/**
	* Creates a new ivld accrual inbound with the primary key. Does not add the ivld accrual inbound to the database.
	*
	* @param ivldAccrualInboundSid the primary key for the new ivld accrual inbound
	* @return the new ivld accrual inbound
	*/
	public IvldAccrualInbound create(int ivldAccrualInboundSid);

	/**
	* Removes the ivld accrual inbound with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
	* @return the ivld accrual inbound that was removed
	* @throws NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
	*/
	public IvldAccrualInbound remove(int ivldAccrualInboundSid)
		throws NoSuchIvldAccrualInboundException;

	public IvldAccrualInbound updateImpl(IvldAccrualInbound ivldAccrualInbound);

	/**
	* Returns the ivld accrual inbound with the primary key or throws a {@link NoSuchIvldAccrualInboundException} if it could not be found.
	*
	* @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
	* @return the ivld accrual inbound
	* @throws NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
	*/
	public IvldAccrualInbound findByPrimaryKey(int ivldAccrualInboundSid)
		throws NoSuchIvldAccrualInboundException;

	/**
	* Returns the ivld accrual inbound with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
	* @return the ivld accrual inbound, or <code>null</code> if a ivld accrual inbound with the primary key could not be found
	*/
	public IvldAccrualInbound fetchByPrimaryKey(int ivldAccrualInboundSid);

	@Override
	public java.util.Map<java.io.Serializable, IvldAccrualInbound> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ivld accrual inbounds.
	*
	* @return the ivld accrual inbounds
	*/
	public java.util.List<IvldAccrualInbound> findAll();

	/**
	* Returns a range of all the ivld accrual inbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld accrual inbounds
	* @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
	* @return the range of ivld accrual inbounds
	*/
	public java.util.List<IvldAccrualInbound> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ivld accrual inbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld accrual inbounds
	* @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ivld accrual inbounds
	*/
	public java.util.List<IvldAccrualInbound> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldAccrualInbound> orderByComparator);

	/**
	* Returns an ordered range of all the ivld accrual inbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld accrual inbounds
	* @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ivld accrual inbounds
	*/
	public java.util.List<IvldAccrualInbound> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IvldAccrualInbound> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ivld accrual inbounds from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ivld accrual inbounds.
	*
	* @return the number of ivld accrual inbounds
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}