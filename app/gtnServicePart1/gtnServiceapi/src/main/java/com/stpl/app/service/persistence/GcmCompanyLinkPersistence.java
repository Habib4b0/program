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

import com.stpl.app.exception.NoSuchGcmCompanyLinkException;
import com.stpl.app.model.GcmCompanyLink;

/**
 * The persistence interface for the gcm company link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.GcmCompanyLinkPersistenceImpl
 * @see GcmCompanyLinkUtil
 * @generated
 */
@ProviderType
public interface GcmCompanyLinkPersistence extends BasePersistence<GcmCompanyLink> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GcmCompanyLinkUtil} to access the gcm company link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the gcm company link in the entity cache if it is enabled.
	*
	* @param gcmCompanyLink the gcm company link
	*/
	public void cacheResult(GcmCompanyLink gcmCompanyLink);

	/**
	* Caches the gcm company links in the entity cache if it is enabled.
	*
	* @param gcmCompanyLinks the gcm company links
	*/
	public void cacheResult(java.util.List<GcmCompanyLink> gcmCompanyLinks);

	/**
	* Creates a new gcm company link with the primary key. Does not add the gcm company link to the database.
	*
	* @param gcmCompanyLinkSid the primary key for the new gcm company link
	* @return the new gcm company link
	*/
	public GcmCompanyLink create(int gcmCompanyLinkSid);

	/**
	* Removes the gcm company link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmCompanyLinkSid the primary key of the gcm company link
	* @return the gcm company link that was removed
	* @throws NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
	*/
	public GcmCompanyLink remove(int gcmCompanyLinkSid)
		throws NoSuchGcmCompanyLinkException;

	public GcmCompanyLink updateImpl(GcmCompanyLink gcmCompanyLink);

	/**
	* Returns the gcm company link with the primary key or throws a {@link NoSuchGcmCompanyLinkException} if it could not be found.
	*
	* @param gcmCompanyLinkSid the primary key of the gcm company link
	* @return the gcm company link
	* @throws NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
	*/
	public GcmCompanyLink findByPrimaryKey(int gcmCompanyLinkSid)
		throws NoSuchGcmCompanyLinkException;

	/**
	* Returns the gcm company link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gcmCompanyLinkSid the primary key of the gcm company link
	* @return the gcm company link, or <code>null</code> if a gcm company link with the primary key could not be found
	*/
	public GcmCompanyLink fetchByPrimaryKey(int gcmCompanyLinkSid);

	@Override
	public java.util.Map<java.io.Serializable, GcmCompanyLink> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the gcm company links.
	*
	* @return the gcm company links
	*/
	public java.util.List<GcmCompanyLink> findAll();

	/**
	* Returns a range of all the gcm company links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm company links
	* @param end the upper bound of the range of gcm company links (not inclusive)
	* @return the range of gcm company links
	*/
	public java.util.List<GcmCompanyLink> findAll(int start, int end);

	/**
	* Returns an ordered range of all the gcm company links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm company links
	* @param end the upper bound of the range of gcm company links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gcm company links
	*/
	public java.util.List<GcmCompanyLink> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GcmCompanyLink> orderByComparator);

	/**
	* Returns an ordered range of all the gcm company links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmCompanyLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gcm company links
	* @param end the upper bound of the range of gcm company links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of gcm company links
	*/
	public java.util.List<GcmCompanyLink> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<GcmCompanyLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the gcm company links from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of gcm company links.
	*
	* @return the number of gcm company links
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}