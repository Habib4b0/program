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

import com.stpl.app.parttwo.exception.NoSuchStArpOutboundException;
import com.stpl.app.parttwo.model.StArpOutbound;

/**
 * The persistence interface for the st arp outbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.StArpOutboundPersistenceImpl
 * @see StArpOutboundUtil
 * @generated
 */
@ProviderType
public interface StArpOutboundPersistence extends BasePersistence<StArpOutbound> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StArpOutboundUtil} to access the st arp outbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the st arp outbound in the entity cache if it is enabled.
	*
	* @param stArpOutbound the st arp outbound
	*/
	public void cacheResult(StArpOutbound stArpOutbound);

	/**
	* Caches the st arp outbounds in the entity cache if it is enabled.
	*
	* @param stArpOutbounds the st arp outbounds
	*/
	public void cacheResult(java.util.List<StArpOutbound> stArpOutbounds);

	/**
	* Creates a new st arp outbound with the primary key. Does not add the st arp outbound to the database.
	*
	* @param stArpOutboundPK the primary key for the new st arp outbound
	* @return the new st arp outbound
	*/
	public StArpOutbound create(StArpOutboundPK stArpOutboundPK);

	/**
	* Removes the st arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stArpOutboundPK the primary key of the st arp outbound
	* @return the st arp outbound that was removed
	* @throws NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
	*/
	public StArpOutbound remove(StArpOutboundPK stArpOutboundPK)
		throws NoSuchStArpOutboundException;

	public StArpOutbound updateImpl(StArpOutbound stArpOutbound);

	/**
	* Returns the st arp outbound with the primary key or throws a {@link NoSuchStArpOutboundException} if it could not be found.
	*
	* @param stArpOutboundPK the primary key of the st arp outbound
	* @return the st arp outbound
	* @throws NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
	*/
	public StArpOutbound findByPrimaryKey(StArpOutboundPK stArpOutboundPK)
		throws NoSuchStArpOutboundException;

	/**
	* Returns the st arp outbound with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stArpOutboundPK the primary key of the st arp outbound
	* @return the st arp outbound, or <code>null</code> if a st arp outbound with the primary key could not be found
	*/
	public StArpOutbound fetchByPrimaryKey(StArpOutboundPK stArpOutboundPK);

	@Override
	public java.util.Map<java.io.Serializable, StArpOutbound> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the st arp outbounds.
	*
	* @return the st arp outbounds
	*/
	public java.util.List<StArpOutbound> findAll();

	/**
	* Returns a range of all the st arp outbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st arp outbounds
	* @param end the upper bound of the range of st arp outbounds (not inclusive)
	* @return the range of st arp outbounds
	*/
	public java.util.List<StArpOutbound> findAll(int start, int end);

	/**
	* Returns an ordered range of all the st arp outbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st arp outbounds
	* @param end the upper bound of the range of st arp outbounds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of st arp outbounds
	*/
	public java.util.List<StArpOutbound> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StArpOutbound> orderByComparator);

	/**
	* Returns an ordered range of all the st arp outbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st arp outbounds
	* @param end the upper bound of the range of st arp outbounds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of st arp outbounds
	*/
	public java.util.List<StArpOutbound> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StArpOutbound> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the st arp outbounds from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of st arp outbounds.
	*
	* @return the number of st arp outbounds
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}