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
 * Provides a wrapper for {@link IvldItemIdentifierLocalService}.
 *
 * @author
 * @see IvldItemIdentifierLocalService
 * @generated
 */
@ProviderType
public class IvldItemIdentifierLocalServiceWrapper
	implements IvldItemIdentifierLocalService,
		ServiceWrapper<IvldItemIdentifierLocalService> {
	public IvldItemIdentifierLocalServiceWrapper(
		IvldItemIdentifierLocalService ivldItemIdentifierLocalService) {
		_ivldItemIdentifierLocalService = ivldItemIdentifierLocalService;
	}

	/**
	* Adds the ivld item identifier to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemIdentifier the ivld item identifier
	* @return the ivld item identifier that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldItemIdentifier addIvldItemIdentifier(
		com.stpl.app.parttwo.model.IvldItemIdentifier ivldItemIdentifier) {
		return _ivldItemIdentifierLocalService.addIvldItemIdentifier(ivldItemIdentifier);
	}

	/**
	* Creates a new ivld item identifier with the primary key. Does not add the ivld item identifier to the database.
	*
	* @param ivldItemIdentifierSid the primary key for the new ivld item identifier
	* @return the new ivld item identifier
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldItemIdentifier createIvldItemIdentifier(
		int ivldItemIdentifierSid) {
		return _ivldItemIdentifierLocalService.createIvldItemIdentifier(ivldItemIdentifierSid);
	}

	/**
	* Deletes the ivld item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemIdentifierSid the primary key of the ivld item identifier
	* @return the ivld item identifier that was removed
	* @throws PortalException if a ivld item identifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldItemIdentifier deleteIvldItemIdentifier(
		int ivldItemIdentifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldItemIdentifierLocalService.deleteIvldItemIdentifier(ivldItemIdentifierSid);
	}

	/**
	* Deletes the ivld item identifier from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldItemIdentifier the ivld item identifier
	* @return the ivld item identifier that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldItemIdentifier deleteIvldItemIdentifier(
		com.stpl.app.parttwo.model.IvldItemIdentifier ivldItemIdentifier) {
		return _ivldItemIdentifierLocalService.deleteIvldItemIdentifier(ivldItemIdentifier);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldItemIdentifierLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldItemIdentifierLocalService.dynamicQuery();
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
		return _ivldItemIdentifierLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldItemIdentifierLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldItemIdentifierLocalService.dynamicQuery(dynamicQuery,
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
		return _ivldItemIdentifierLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldItemIdentifierLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.IvldItemIdentifier fetchIvldItemIdentifier(
		int ivldItemIdentifierSid) {
		return _ivldItemIdentifierLocalService.fetchIvldItemIdentifier(ivldItemIdentifierSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldItemIdentifierLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldItemIdentifierLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld item identifier with the primary key.
	*
	* @param ivldItemIdentifierSid the primary key of the ivld item identifier
	* @return the ivld item identifier
	* @throws PortalException if a ivld item identifier with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldItemIdentifier getIvldItemIdentifier(
		int ivldItemIdentifierSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldItemIdentifierLocalService.getIvldItemIdentifier(ivldItemIdentifierSid);
	}

	/**
	* Returns a range of all the ivld item identifiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld item identifiers
	* @param end the upper bound of the range of ivld item identifiers (not inclusive)
	* @return the range of ivld item identifiers
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.IvldItemIdentifier> getIvldItemIdentifiers(
		int start, int end) {
		return _ivldItemIdentifierLocalService.getIvldItemIdentifiers(start, end);
	}

	/**
	* Returns the number of ivld item identifiers.
	*
	* @return the number of ivld item identifiers
	*/
	@Override
	public int getIvldItemIdentifiersCount() {
		return _ivldItemIdentifierLocalService.getIvldItemIdentifiersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldItemIdentifierLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldItemIdentifierLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld item identifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldItemIdentifier the ivld item identifier
	* @return the ivld item identifier that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldItemIdentifier updateIvldItemIdentifier(
		com.stpl.app.parttwo.model.IvldItemIdentifier ivldItemIdentifier) {
		return _ivldItemIdentifierLocalService.updateIvldItemIdentifier(ivldItemIdentifier);
	}

	@Override
	public IvldItemIdentifierLocalService getWrappedService() {
		return _ivldItemIdentifierLocalService;
	}

	@Override
	public void setWrappedService(
		IvldItemIdentifierLocalService ivldItemIdentifierLocalService) {
		_ivldItemIdentifierLocalService = ivldItemIdentifierLocalService;
	}

	private IvldItemIdentifierLocalService _ivldItemIdentifierLocalService;
}