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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.parttwo.model.StArpOutbound;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the st arp outbound service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.StArpOutboundPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StArpOutboundPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.StArpOutboundPersistenceImpl
 * @generated
 */
@ProviderType
public class StArpOutboundUtil {
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
	public static void clearCache(StArpOutbound stArpOutbound) {
		getPersistence().clearCache(stArpOutbound);
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
	public static List<StArpOutbound> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StArpOutbound> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StArpOutbound> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StArpOutbound> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StArpOutbound update(StArpOutbound stArpOutbound) {
		return getPersistence().update(stArpOutbound);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StArpOutbound update(StArpOutbound stArpOutbound,
		ServiceContext serviceContext) {
		return getPersistence().update(stArpOutbound, serviceContext);
	}

	/**
	* Caches the st arp outbound in the entity cache if it is enabled.
	*
	* @param stArpOutbound the st arp outbound
	*/
	public static void cacheResult(StArpOutbound stArpOutbound) {
		getPersistence().cacheResult(stArpOutbound);
	}

	/**
	* Caches the st arp outbounds in the entity cache if it is enabled.
	*
	* @param stArpOutbounds the st arp outbounds
	*/
	public static void cacheResult(List<StArpOutbound> stArpOutbounds) {
		getPersistence().cacheResult(stArpOutbounds);
	}

	/**
	* Creates a new st arp outbound with the primary key. Does not add the st arp outbound to the database.
	*
	* @param stArpOutboundPK the primary key for the new st arp outbound
	* @return the new st arp outbound
	*/
	public static StArpOutbound create(StArpOutboundPK stArpOutboundPK) {
		return getPersistence().create(stArpOutboundPK);
	}

	/**
	* Removes the st arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stArpOutboundPK the primary key of the st arp outbound
	* @return the st arp outbound that was removed
	* @throws NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
	*/
	public static StArpOutbound remove(StArpOutboundPK stArpOutboundPK)
		throws com.stpl.app.parttwo.exception.NoSuchStArpOutboundException {
		return getPersistence().remove(stArpOutboundPK);
	}

	public static StArpOutbound updateImpl(StArpOutbound stArpOutbound) {
		return getPersistence().updateImpl(stArpOutbound);
	}

	/**
	* Returns the st arp outbound with the primary key or throws a {@link NoSuchStArpOutboundException} if it could not be found.
	*
	* @param stArpOutboundPK the primary key of the st arp outbound
	* @return the st arp outbound
	* @throws NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
	*/
	public static StArpOutbound findByPrimaryKey(
		StArpOutboundPK stArpOutboundPK)
		throws com.stpl.app.parttwo.exception.NoSuchStArpOutboundException {
		return getPersistence().findByPrimaryKey(stArpOutboundPK);
	}

	/**
	* Returns the st arp outbound with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param stArpOutboundPK the primary key of the st arp outbound
	* @return the st arp outbound, or <code>null</code> if a st arp outbound with the primary key could not be found
	*/
	public static StArpOutbound fetchByPrimaryKey(
		StArpOutboundPK stArpOutboundPK) {
		return getPersistence().fetchByPrimaryKey(stArpOutboundPK);
	}

	public static java.util.Map<java.io.Serializable, StArpOutbound> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the st arp outbounds.
	*
	* @return the st arp outbounds
	*/
	public static List<StArpOutbound> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<StArpOutbound> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<StArpOutbound> findAll(int start, int end,
		OrderByComparator<StArpOutbound> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<StArpOutbound> findAll(int start, int end,
		OrderByComparator<StArpOutbound> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the st arp outbounds from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of st arp outbounds.
	*
	* @return the number of st arp outbounds
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StArpOutboundPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StArpOutboundPersistence, StArpOutboundPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StArpOutboundPersistence.class);
}