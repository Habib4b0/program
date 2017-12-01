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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.model.GcmCompanyLink;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the gcm company link service. This utility wraps {@link com.stpl.app.service.persistence.impl.GcmCompanyLinkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmCompanyLinkPersistence
 * @see com.stpl.app.service.persistence.impl.GcmCompanyLinkPersistenceImpl
 * @generated
 */
@ProviderType
public class GcmCompanyLinkUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(GcmCompanyLink gcmCompanyLink) {
		getPersistence().clearCache(gcmCompanyLink);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<GcmCompanyLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<GcmCompanyLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<GcmCompanyLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<GcmCompanyLink> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static GcmCompanyLink update(GcmCompanyLink gcmCompanyLink) {
		return getPersistence().update(gcmCompanyLink);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static GcmCompanyLink update(GcmCompanyLink gcmCompanyLink,
		ServiceContext serviceContext) {
		return getPersistence().update(gcmCompanyLink, serviceContext);
	}

	/**
	* Caches the gcm company link in the entity cache if it is enabled.
	*
	* @param gcmCompanyLink the gcm company link
	*/
	public static void cacheResult(GcmCompanyLink gcmCompanyLink) {
		getPersistence().cacheResult(gcmCompanyLink);
	}

	/**
	* Caches the gcm company links in the entity cache if it is enabled.
	*
	* @param gcmCompanyLinks the gcm company links
	*/
	public static void cacheResult(List<GcmCompanyLink> gcmCompanyLinks) {
		getPersistence().cacheResult(gcmCompanyLinks);
	}

	/**
	* Creates a new gcm company link with the primary key. Does not add the gcm company link to the database.
	*
	* @param gcmCompanyLinkSid the primary key for the new gcm company link
	* @return the new gcm company link
	*/
	public static GcmCompanyLink create(int gcmCompanyLinkSid) {
		return getPersistence().create(gcmCompanyLinkSid);
	}

	/**
	* Removes the gcm company link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gcmCompanyLinkSid the primary key of the gcm company link
	* @return the gcm company link that was removed
	* @throws NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
	*/
	public static GcmCompanyLink remove(int gcmCompanyLinkSid)
		throws com.stpl.app.exception.NoSuchGcmCompanyLinkException {
		return getPersistence().remove(gcmCompanyLinkSid);
	}

	public static GcmCompanyLink updateImpl(GcmCompanyLink gcmCompanyLink) {
		return getPersistence().updateImpl(gcmCompanyLink);
	}

	/**
	* Returns the gcm company link with the primary key or throws a {@link NoSuchGcmCompanyLinkException} if it could not be found.
	*
	* @param gcmCompanyLinkSid the primary key of the gcm company link
	* @return the gcm company link
	* @throws NoSuchGcmCompanyLinkException if a gcm company link with the primary key could not be found
	*/
	public static GcmCompanyLink findByPrimaryKey(int gcmCompanyLinkSid)
		throws com.stpl.app.exception.NoSuchGcmCompanyLinkException {
		return getPersistence().findByPrimaryKey(gcmCompanyLinkSid);
	}

	/**
	* Returns the gcm company link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gcmCompanyLinkSid the primary key of the gcm company link
	* @return the gcm company link, or <code>null</code> if a gcm company link with the primary key could not be found
	*/
	public static GcmCompanyLink fetchByPrimaryKey(int gcmCompanyLinkSid) {
		return getPersistence().fetchByPrimaryKey(gcmCompanyLinkSid);
	}

	public static java.util.Map<java.io.Serializable, GcmCompanyLink> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the gcm company links.
	*
	* @return the gcm company links
	*/
	public static List<GcmCompanyLink> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<GcmCompanyLink> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<GcmCompanyLink> findAll(int start, int end,
		OrderByComparator<GcmCompanyLink> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<GcmCompanyLink> findAll(int start, int end,
		OrderByComparator<GcmCompanyLink> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the gcm company links from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of gcm company links.
	*
	* @return the number of gcm company links
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static GcmCompanyLinkPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<GcmCompanyLinkPersistence, GcmCompanyLinkPersistence> _serviceTracker =
		ServiceTrackerFactory.open(GcmCompanyLinkPersistence.class);
}