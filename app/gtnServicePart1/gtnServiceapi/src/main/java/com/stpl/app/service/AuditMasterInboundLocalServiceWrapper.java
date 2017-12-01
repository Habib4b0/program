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

package com.stpl.app.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AuditMasterInboundLocalService}.
 *
 * @author
 * @see AuditMasterInboundLocalService
 * @generated
 */
@ProviderType
public class AuditMasterInboundLocalServiceWrapper
	implements AuditMasterInboundLocalService,
		ServiceWrapper<AuditMasterInboundLocalService> {
	public AuditMasterInboundLocalServiceWrapper(
		AuditMasterInboundLocalService auditMasterInboundLocalService) {
		_auditMasterInboundLocalService = auditMasterInboundLocalService;
	}

	/**
	* Adds the audit master inbound to the database. Also notifies the appropriate model listeners.
	*
	* @param auditMasterInbound the audit master inbound
	* @return the audit master inbound that was added
	*/
	@Override
	public com.stpl.app.model.AuditMasterInbound addAuditMasterInbound(
		com.stpl.app.model.AuditMasterInbound auditMasterInbound) {
		return _auditMasterInboundLocalService.addAuditMasterInbound(auditMasterInbound);
	}

	/**
	* Creates a new audit master inbound with the primary key. Does not add the audit master inbound to the database.
	*
	* @param auditInboundSid the primary key for the new audit master inbound
	* @return the new audit master inbound
	*/
	@Override
	public com.stpl.app.model.AuditMasterInbound createAuditMasterInbound(
		int auditInboundSid) {
		return _auditMasterInboundLocalService.createAuditMasterInbound(auditInboundSid);
	}

	/**
	* Deletes the audit master inbound from the database. Also notifies the appropriate model listeners.
	*
	* @param auditMasterInbound the audit master inbound
	* @return the audit master inbound that was removed
	*/
	@Override
	public com.stpl.app.model.AuditMasterInbound deleteAuditMasterInbound(
		com.stpl.app.model.AuditMasterInbound auditMasterInbound) {
		return _auditMasterInboundLocalService.deleteAuditMasterInbound(auditMasterInbound);
	}

	/**
	* Deletes the audit master inbound with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditInboundSid the primary key of the audit master inbound
	* @return the audit master inbound that was removed
	* @throws PortalException if a audit master inbound with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.AuditMasterInbound deleteAuditMasterInbound(
		int auditInboundSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditMasterInboundLocalService.deleteAuditMasterInbound(auditInboundSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditMasterInboundLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _auditMasterInboundLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _auditMasterInboundLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _auditMasterInboundLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _auditMasterInboundLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _auditMasterInboundLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _auditMasterInboundLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.AuditMasterInbound fetchAuditMasterInbound(
		int auditInboundSid) {
		return _auditMasterInboundLocalService.fetchAuditMasterInbound(auditInboundSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _auditMasterInboundLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the audit master inbound with the primary key.
	*
	* @param auditInboundSid the primary key of the audit master inbound
	* @return the audit master inbound
	* @throws PortalException if a audit master inbound with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.AuditMasterInbound getAuditMasterInbound(
		int auditInboundSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditMasterInboundLocalService.getAuditMasterInbound(auditInboundSid);
	}

	/**
	* Returns a range of all the audit master inbounds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AuditMasterInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit master inbounds
	* @param end the upper bound of the range of audit master inbounds (not inclusive)
	* @return the range of audit master inbounds
	*/
	@Override
	public java.util.List<com.stpl.app.model.AuditMasterInbound> getAuditMasterInbounds(
		int start, int end) {
		return _auditMasterInboundLocalService.getAuditMasterInbounds(start, end);
	}

	/**
	* Returns the number of audit master inbounds.
	*
	* @return the number of audit master inbounds
	*/
	@Override
	public int getAuditMasterInboundsCount() {
		return _auditMasterInboundLocalService.getAuditMasterInboundsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _auditMasterInboundLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _auditMasterInboundLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditMasterInboundLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the audit master inbound in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditMasterInbound the audit master inbound
	* @return the audit master inbound that was updated
	*/
	@Override
	public com.stpl.app.model.AuditMasterInbound updateAuditMasterInbound(
		com.stpl.app.model.AuditMasterInbound auditMasterInbound) {
		return _auditMasterInboundLocalService.updateAuditMasterInbound(auditMasterInbound);
	}

	@Override
	public AuditMasterInboundLocalService getWrappedService() {
		return _auditMasterInboundLocalService;
	}

	@Override
	public void setWrappedService(
		AuditMasterInboundLocalService auditMasterInboundLocalService) {
		_auditMasterInboundLocalService = auditMasterInboundLocalService;
	}

	private AuditMasterInboundLocalService _auditMasterInboundLocalService;
}