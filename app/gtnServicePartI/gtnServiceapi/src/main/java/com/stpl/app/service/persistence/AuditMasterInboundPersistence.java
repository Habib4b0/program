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

import com.stpl.app.exception.NoSuchAuditMasterInboundException;
import com.stpl.app.model.AuditMasterInbound;

/**
 * The persistence interface for the audit master inbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.AuditMasterInboundPersistenceImpl
 * @see AuditMasterInboundUtil
 * @generated
 */
@ProviderType
public interface AuditMasterInboundPersistence extends BasePersistence<AuditMasterInbound> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuditMasterInboundUtil} to access the audit master inbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the audit master inbound in the entity cache if it is enabled.
	*
	* @param auditMasterInbound the audit master inbound
	*/
	public void cacheResult(AuditMasterInbound auditMasterInbound);

	/**
	* Caches the audit master inbounds in the entity cache if it is enabled.
	*
	* @param auditMasterInbounds the audit master inbounds
	*/
	public void cacheResult(
		java.util.List<AuditMasterInbound> auditMasterInbounds);

	/**
	* Creates a new audit master inbound with the primary key. Does not add the audit master inbound to the database.
	*
	* @param auditInboundSid the primary key for the new audit master inbound
	* @return the new audit master inbound
	*/
	public AuditMasterInbound create(int auditInboundSid);

	/**
	* Removes the audit master inbound with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditInboundSid the primary key of the audit master inbound
	* @return the audit master inbound that was removed
	* @throws NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
	*/
	public AuditMasterInbound remove(int auditInboundSid)
		throws NoSuchAuditMasterInboundException;

	public AuditMasterInbound updateImpl(AuditMasterInbound auditMasterInbound);

	/**
	* Returns the audit master inbound with the primary key or throws a {@link NoSuchAuditMasterInboundException} if it could not be found.
	*
	* @param auditInboundSid the primary key of the audit master inbound
	* @return the audit master inbound
	* @throws NoSuchAuditMasterInboundException if a audit master inbound with the primary key could not be found
	*/
	public AuditMasterInbound findByPrimaryKey(int auditInboundSid)
		throws NoSuchAuditMasterInboundException;

	/**
	* Returns the audit master inbound with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditInboundSid the primary key of the audit master inbound
	* @return the audit master inbound, or <code>null</code> if a audit master inbound with the primary key could not be found
	*/
	public AuditMasterInbound fetchByPrimaryKey(int auditInboundSid);

	@Override
	public java.util.Map<java.io.Serializable, AuditMasterInbound> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the audit master inbounds.
	*
	* @return the audit master inbounds
	*/
	public java.util.List<AuditMasterInbound> findAll();

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
	public java.util.List<AuditMasterInbound> findAll(int start, int end);

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
	public java.util.List<AuditMasterInbound> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditMasterInbound> orderByComparator);

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
	public java.util.List<AuditMasterInbound> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditMasterInbound> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the audit master inbounds from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of audit master inbounds.
	*
	* @return the number of audit master inbounds
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}