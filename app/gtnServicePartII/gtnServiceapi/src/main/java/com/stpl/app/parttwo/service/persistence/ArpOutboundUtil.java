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

import com.stpl.app.parttwo.model.ArpOutbound;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the arp outbound service. This utility wraps {@link com.stpl.app.parttwo.service.persistence.impl.ArpOutboundPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ArpOutboundPersistence
 * @see com.stpl.app.parttwo.service.persistence.impl.ArpOutboundPersistenceImpl
 * @generated
 */
@ProviderType
public class ArpOutboundUtil {
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
	public static void clearCache(ArpOutbound arpOutbound) {
		getPersistence().clearCache(arpOutbound);
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
	public static List<ArpOutbound> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ArpOutbound> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ArpOutbound> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ArpOutbound> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ArpOutbound update(ArpOutbound arpOutbound) {
		return getPersistence().update(arpOutbound);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ArpOutbound update(ArpOutbound arpOutbound,
		ServiceContext serviceContext) {
		return getPersistence().update(arpOutbound, serviceContext);
	}

	/**
	* Caches the arp outbound in the entity cache if it is enabled.
	*
	* @param arpOutbound the arp outbound
	*/
	public static void cacheResult(ArpOutbound arpOutbound) {
		getPersistence().cacheResult(arpOutbound);
	}

	/**
	* Caches the arp outbounds in the entity cache if it is enabled.
	*
	* @param arpOutbounds the arp outbounds
	*/
	public static void cacheResult(List<ArpOutbound> arpOutbounds) {
		getPersistence().cacheResult(arpOutbounds);
	}

	/**
	* Creates a new arp outbound with the primary key. Does not add the arp outbound to the database.
	*
	* @param arpOutboundPK the primary key for the new arp outbound
	* @return the new arp outbound
	*/
	public static ArpOutbound create(ArpOutboundPK arpOutboundPK) {
		return getPersistence().create(arpOutboundPK);
	}

	/**
	* Removes the arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param arpOutboundPK the primary key of the arp outbound
	* @return the arp outbound that was removed
	* @throws NoSuchArpOutboundException if a arp outbound with the primary key could not be found
	*/
	public static ArpOutbound remove(ArpOutboundPK arpOutboundPK)
		throws com.stpl.app.parttwo.exception.NoSuchArpOutboundException {
		return getPersistence().remove(arpOutboundPK);
	}

	public static ArpOutbound updateImpl(ArpOutbound arpOutbound) {
		return getPersistence().updateImpl(arpOutbound);
	}

	/**
	* Returns the arp outbound with the primary key or throws a {@link NoSuchArpOutboundException} if it could not be found.
	*
	* @param arpOutboundPK the primary key of the arp outbound
	* @return the arp outbound
	* @throws NoSuchArpOutboundException if a arp outbound with the primary key could not be found
	*/
	public static ArpOutbound findByPrimaryKey(ArpOutboundPK arpOutboundPK)
		throws com.stpl.app.parttwo.exception.NoSuchArpOutboundException {
		return getPersistence().findByPrimaryKey(arpOutboundPK);
	}

	/**
	* Returns the arp outbound with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param arpOutboundPK the primary key of the arp outbound
	* @return the arp outbound, or <code>null</code> if a arp outbound with the primary key could not be found
	*/
	public static ArpOutbound fetchByPrimaryKey(ArpOutboundPK arpOutboundPK) {
		return getPersistence().fetchByPrimaryKey(arpOutboundPK);
	}

	public static java.util.Map<java.io.Serializable, ArpOutbound> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the arp outbounds.
	*
	* @return the arp outbounds
	*/
	public static List<ArpOutbound> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the arp outbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of arp outbounds
	* @param end the upper bound of the range of arp outbounds (not inclusive)
	* @return the range of arp outbounds
	*/
	public static List<ArpOutbound> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the arp outbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of arp outbounds
	* @param end the upper bound of the range of arp outbounds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of arp outbounds
	*/
	public static List<ArpOutbound> findAll(int start, int end,
		OrderByComparator<ArpOutbound> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the arp outbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of arp outbounds
	* @param end the upper bound of the range of arp outbounds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of arp outbounds
	*/
	public static List<ArpOutbound> findAll(int start, int end,
		OrderByComparator<ArpOutbound> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the arp outbounds from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of arp outbounds.
	*
	* @return the number of arp outbounds
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ArpOutboundPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ArpOutboundPersistence, ArpOutboundPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ArpOutboundPersistence.class);
}