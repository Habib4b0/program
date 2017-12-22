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

package com.stpl.app.parttwo.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldCompanyIdentifierLocalService}.
 *
 * @author
 * @see IvldCompanyIdentifierLocalService
 * @generated
 */
@ProviderType
public class IvldCompanyIdentifierLocalServiceWrapper
	implements IvldCompanyIdentifierLocalService,
		ServiceWrapper<IvldCompanyIdentifierLocalService> {
	public IvldCompanyIdentifierLocalServiceWrapper(
		IvldCompanyIdentifierLocalService ivldCompanyIdentifierLocalService) {
		_ivldCompanyIdentifierLocalService = ivldCompanyIdentifierLocalService;
	}

	/**
	* Adds the ivld company identifier to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyIdentifier the ivld company identifier
	* @return the ivld company identifier that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyIdentifier addIvldCompanyIdentifier(
		com.stpl.app.parttwo.model.IvldCompanyIdentifier ivldCompanyIdentifier) {
		return _ivldCompanyIdentifierLocalService.addIvldCompanyIdentifier(ivldCompanyIdentifier);
	}

	/**
	* Creates a new ivld company identifier with the primary key. Does not add the ivld company identifier to the database.
	*
	* @param ivldCompanyIdentifierSid the primary key for the new ivld company identifier
	* @return the new ivld company identifier
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyIdentifier createIvldCompanyIdentifier(
		int ivldCompanyIdentifierSid) {
		return _ivldCompanyIdentifierLocalService.createIvldCompanyIdentifier(ivldCompanyIdentifierSid);
	}

	/**
	* Deletes the ivld company identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	* @return the ivld company identifier that was removed
	* @throws PortalException if a ivld company identifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyIdentifier deleteIvldCompanyIdentifier(
		int ivldCompanyIdentifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldCompanyIdentifierLocalService.deleteIvldCompanyIdentifier(ivldCompanyIdentifierSid);
	}

	/**
	* Deletes the ivld company identifier from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyIdentifier the ivld company identifier
	* @return the ivld company identifier that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyIdentifier deleteIvldCompanyIdentifier(
		com.stpl.app.parttwo.model.IvldCompanyIdentifier ivldCompanyIdentifier) {
		return _ivldCompanyIdentifierLocalService.deleteIvldCompanyIdentifier(ivldCompanyIdentifier);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldCompanyIdentifierLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldCompanyIdentifierLocalService.dynamicQuery();
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
		return _ivldCompanyIdentifierLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldCompanyIdentifierLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldCompanyIdentifierLocalService.dynamicQuery(dynamicQuery,
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
		return _ivldCompanyIdentifierLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldCompanyIdentifierLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.IvldCompanyIdentifier fetchIvldCompanyIdentifier(
		int ivldCompanyIdentifierSid) {
		return _ivldCompanyIdentifierLocalService.fetchIvldCompanyIdentifier(ivldCompanyIdentifierSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldCompanyIdentifierLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldCompanyIdentifierLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld company identifier with the primary key.
	*
	* @param ivldCompanyIdentifierSid the primary key of the ivld company identifier
	* @return the ivld company identifier
	* @throws PortalException if a ivld company identifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyIdentifier getIvldCompanyIdentifier(
		int ivldCompanyIdentifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldCompanyIdentifierLocalService.getIvldCompanyIdentifier(ivldCompanyIdentifierSid);
	}

	/**
	* Returns a range of all the ivld company identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company identifiers
	* @param end the upper bound of the range of ivld company identifiers (not inclusive)
	* @return the range of ivld company identifiers
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.IvldCompanyIdentifier> getIvldCompanyIdentifiers(
		int start, int end) {
		return _ivldCompanyIdentifierLocalService.getIvldCompanyIdentifiers(start,
			end);
	}

	/**
	* Returns the number of ivld company identifiers.
	*
	* @return the number of ivld company identifiers
	*/
	@Override
	public int getIvldCompanyIdentifiersCount() {
		return _ivldCompanyIdentifierLocalService.getIvldCompanyIdentifiersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldCompanyIdentifierLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldCompanyIdentifierLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld company identifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyIdentifier the ivld company identifier
	* @return the ivld company identifier that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyIdentifier updateIvldCompanyIdentifier(
		com.stpl.app.parttwo.model.IvldCompanyIdentifier ivldCompanyIdentifier) {
		return _ivldCompanyIdentifierLocalService.updateIvldCompanyIdentifier(ivldCompanyIdentifier);
	}

	@Override
	public IvldCompanyIdentifierLocalService getWrappedService() {
		return _ivldCompanyIdentifierLocalService;
	}

	@Override
	public void setWrappedService(
		IvldCompanyIdentifierLocalService ivldCompanyIdentifierLocalService) {
		_ivldCompanyIdentifierLocalService = ivldCompanyIdentifierLocalService;
	}

	private IvldCompanyIdentifierLocalService _ivldCompanyIdentifierLocalService;
}