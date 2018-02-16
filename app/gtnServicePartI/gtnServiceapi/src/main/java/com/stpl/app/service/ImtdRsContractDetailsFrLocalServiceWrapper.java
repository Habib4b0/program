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
 * Provides a wrapper for {@link ImtdRsContractDetailsFrLocalService}.
 *
 * @author
 * @see ImtdRsContractDetailsFrLocalService
 * @generated
 */
@ProviderType
public class ImtdRsContractDetailsFrLocalServiceWrapper
	implements ImtdRsContractDetailsFrLocalService,
		ServiceWrapper<ImtdRsContractDetailsFrLocalService> {
	public ImtdRsContractDetailsFrLocalServiceWrapper(
		ImtdRsContractDetailsFrLocalService imtdRsContractDetailsFrLocalService) {
		_imtdRsContractDetailsFrLocalService = imtdRsContractDetailsFrLocalService;
	}

	/**
	* Adds the imtd rs contract details fr to the database. Also notifies the appropriate model listeners.
	*
	* @param imtdRsContractDetailsFr the imtd rs contract details fr
	* @return the imtd rs contract details fr that was added
	*/
	@Override
	public com.stpl.app.model.ImtdRsContractDetailsFr addImtdRsContractDetailsFr(
		com.stpl.app.model.ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		return _imtdRsContractDetailsFrLocalService.addImtdRsContractDetailsFr(imtdRsContractDetailsFr);
	}

	/**
	* Creates a new imtd rs contract details fr with the primary key. Does not add the imtd rs contract details fr to the database.
	*
	* @param imtdRsContractDetailsFrSid the primary key for the new imtd rs contract details fr
	* @return the new imtd rs contract details fr
	*/
	@Override
	public com.stpl.app.model.ImtdRsContractDetailsFr createImtdRsContractDetailsFr(
		int imtdRsContractDetailsFrSid) {
		return _imtdRsContractDetailsFrLocalService.createImtdRsContractDetailsFr(imtdRsContractDetailsFrSid);
	}

	/**
	* Deletes the imtd rs contract details fr from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdRsContractDetailsFr the imtd rs contract details fr
	* @return the imtd rs contract details fr that was removed
	*/
	@Override
	public com.stpl.app.model.ImtdRsContractDetailsFr deleteImtdRsContractDetailsFr(
		com.stpl.app.model.ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		return _imtdRsContractDetailsFrLocalService.deleteImtdRsContractDetailsFr(imtdRsContractDetailsFr);
	}

	/**
	* Deletes the imtd rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
	* @return the imtd rs contract details fr that was removed
	* @throws PortalException if a imtd rs contract details fr with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ImtdRsContractDetailsFr deleteImtdRsContractDetailsFr(
		int imtdRsContractDetailsFrSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _imtdRsContractDetailsFrLocalService.deleteImtdRsContractDetailsFr(imtdRsContractDetailsFrSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _imtdRsContractDetailsFrLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _imtdRsContractDetailsFrLocalService.dynamicQuery();
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
		return _imtdRsContractDetailsFrLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _imtdRsContractDetailsFrLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _imtdRsContractDetailsFrLocalService.dynamicQuery(dynamicQuery,
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
		return _imtdRsContractDetailsFrLocalService.dynamicQueryCount(dynamicQuery);
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
		return _imtdRsContractDetailsFrLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ImtdRsContractDetailsFr fetchImtdRsContractDetailsFr(
		int imtdRsContractDetailsFrSid) {
		return _imtdRsContractDetailsFrLocalService.fetchImtdRsContractDetailsFr(imtdRsContractDetailsFrSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _imtdRsContractDetailsFrLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the imtd rs contract details fr with the primary key.
	*
	* @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
	* @return the imtd rs contract details fr
	* @throws PortalException if a imtd rs contract details fr with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ImtdRsContractDetailsFr getImtdRsContractDetailsFr(
		int imtdRsContractDetailsFrSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _imtdRsContractDetailsFrLocalService.getImtdRsContractDetailsFr(imtdRsContractDetailsFrSid);
	}

	/**
	* Returns a range of all the imtd rs contract details frs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd rs contract details frs
	* @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
	* @return the range of imtd rs contract details frs
	*/
	@Override
	public java.util.List<com.stpl.app.model.ImtdRsContractDetailsFr> getImtdRsContractDetailsFrs(
		int start, int end) {
		return _imtdRsContractDetailsFrLocalService.getImtdRsContractDetailsFrs(start,
			end);
	}

	/**
	* Returns the number of imtd rs contract details frs.
	*
	* @return the number of imtd rs contract details frs
	*/
	@Override
	public int getImtdRsContractDetailsFrsCount() {
		return _imtdRsContractDetailsFrLocalService.getImtdRsContractDetailsFrsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _imtdRsContractDetailsFrLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _imtdRsContractDetailsFrLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _imtdRsContractDetailsFrLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the imtd rs contract details fr in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param imtdRsContractDetailsFr the imtd rs contract details fr
	* @return the imtd rs contract details fr that was updated
	*/
	@Override
	public com.stpl.app.model.ImtdRsContractDetailsFr updateImtdRsContractDetailsFr(
		com.stpl.app.model.ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		return _imtdRsContractDetailsFrLocalService.updateImtdRsContractDetailsFr(imtdRsContractDetailsFr);
	}

	@Override
	public ImtdRsContractDetailsFrLocalService getWrappedService() {
		return _imtdRsContractDetailsFrLocalService;
	}

	@Override
	public void setWrappedService(
		ImtdRsContractDetailsFrLocalService imtdRsContractDetailsFrLocalService) {
		_imtdRsContractDetailsFrLocalService = imtdRsContractDetailsFrLocalService;
	}

	private ImtdRsContractDetailsFrLocalService _imtdRsContractDetailsFrLocalService;
}