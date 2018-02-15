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

import com.stpl.app.model.AuditMasterInbound;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the audit master inbound service. This utility wraps {@link com.stpl.app.service.persistence.impl.AuditMasterInboundPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AuditMasterInboundPersistence
 * @see com.stpl.app.service.persistence.impl.AuditMasterInboundPersistenceImpl
 * @generated
 */
@ProviderType
public class AuditMasterInboundUtil {
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
	public static void clearCache(AuditMasterInbound auditMasterInbound) {
		getPersistence().clearCache(auditMasterInbound);
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
	public static List<AuditMasterInbound> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuditMasterInbound> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuditMasterInbound> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AuditMasterInbound> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AuditMasterInbound update(
		AuditMasterInbound auditMasterInbound) {
		return getPersistence().update(auditMasterInbound);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AuditMasterInbound update(
		AuditMasterInbound auditMasterInbound, ServiceContext serviceContext) {
		return getPersistence().update(auditMasterInbound, serviceContext);
	}

	/**
	* Caches the audit master inbound in the entity cache if it is enabled.
	*
	* @param auditMasterInbound the audit master inbound
	*/
	public static void cacheResult(AuditMasterInbound auditMasterInbound) {
		getPersistence().cacheResult(auditMasterInbound);
	}

	/**
	* Caches the audit master inbounds in the entity cache if it is enabled.
	*
	* @param auditMasterInbounds the audit master inbounds
	*/
	public static void cacheResult(List<AuditMasterInbound> auditMasterInbounds) {
		getPersistence().cacheResult(auditMasterInbounds);
	}

	/**
	* Creates a new audit master inbound with the primary key. Does not add the audit master inbound to the database.
	*
	* @param auditInboundSid the primary key for the new audit master inbound
	* @return the new audit master inbound
	*/
	public static AuditMasterInbound create(int auditInboundSid) {
		return getPersistence().create(auditInboundSid);
	}

	/**
	* Removes the audit master inbound with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditInboundSid the primary key of the audit master inbound
	* @return the audit master inbound that was removed
	* @throws NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
	*/
	public static AuditMasterInbound remove(int auditInboundSid)
		throws com.stpl.app.exception.NoSuchAuditMasterInboundException {
		return getPersistence().remove(auditInboundSid);
	}

	public static AuditMasterInbound updateImpl(
		AuditMasterInbound auditMasterInbound) {
		return getPersistence().updateImpl(auditMasterInbound);
	}

	/**
	* Returns the audit master inbound with the primary key or throws a {@link NoSuchAuditMasterInboundException} if it could not be found.
	*
	* @param auditInboundSid the primary key of the audit master inbound
	* @return the audit master inbound
	* @throws NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
	*/
	public static AuditMasterInbound findByPrimaryKey(int auditInboundSid)
		throws com.stpl.app.exception.NoSuchAuditMasterInboundException {
		return getPersistence().findByPrimaryKey(auditInboundSid);
	}

	/**
	* Returns the audit master inbound with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditInboundSid the primary key of the audit master inbound
	* @return the audit master inbound, or <code>null</code> if a audit master inbound with the primary key could not be found
	*/
	public static AuditMasterInbound fetchByPrimaryKey(int auditInboundSid) {
		return getPersistence().fetchByPrimaryKey(auditInboundSid);
	}

	public static java.util.Map<java.io.Serializable, AuditMasterInbound> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the audit master inbounds.
	*
	* @return the audit master inbounds
	*/
	public static List<AuditMasterInbound> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the audit master inbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit master inbounds
	* @param end the upper bound of the range of audit master inbounds (not inclusive)
	* @return the range of audit master inbounds
	*/
	public static List<AuditMasterInbound> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the audit master inbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit master inbounds
	* @param end the upper bound of the range of audit master inbounds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of audit master inbounds
	*/
	public static List<AuditMasterInbound> findAll(int start, int end,
		OrderByComparator<AuditMasterInbound> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit master inbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit master inbounds
	* @param end the upper bound of the range of audit master inbounds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of audit master inbounds
	*/
	public static List<AuditMasterInbound> findAll(int start, int end,
		OrderByComparator<AuditMasterInbound> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the audit master inbounds from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of audit master inbounds.
	*
	* @return the number of audit master inbounds
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AuditMasterInboundPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AuditMasterInboundPersistence, AuditMasterInboundPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AuditMasterInboundPersistence.class);
}