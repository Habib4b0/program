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
 * Provides a wrapper for {@link PsModelLocalService}.
 *
 * @author
 * @see PsModelLocalService
 * @generated
 */
@ProviderType
public class PsModelLocalServiceWrapper implements PsModelLocalService,
	ServiceWrapper<PsModelLocalService> {
	public PsModelLocalServiceWrapper(PsModelLocalService psModelLocalService) {
		_psModelLocalService = psModelLocalService;
	}

	/**
	* Adds the ps model to the database. Also notifies the appropriate model listeners.
	*
	* @param psModel the ps model
	* @return the ps model that was added
	*/
	@Override
	public com.stpl.app.model.PsModel addPsModel(
		com.stpl.app.model.PsModel psModel) {
		return _psModelLocalService.addPsModel(psModel);
	}

	/**
	* Creates a new ps model with the primary key. Does not add the ps model to the database.
	*
	* @param psModelSid the primary key for the new ps model
	* @return the new ps model
	*/
	@Override
	public com.stpl.app.model.PsModel createPsModel(int psModelSid) {
		return _psModelLocalService.createPsModel(psModelSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _psModelLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the ps model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param psModelSid the primary key of the ps model
	* @return the ps model that was removed
	* @throws PortalException if a ps model with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PsModel deletePsModel(int psModelSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _psModelLocalService.deletePsModel(psModelSid);
	}

	/**
	* Deletes the ps model from the database. Also notifies the appropriate model listeners.
	*
	* @param psModel the ps model
	* @return the ps model that was removed
	*/
	@Override
	public com.stpl.app.model.PsModel deletePsModel(
		com.stpl.app.model.PsModel psModel) {
		return _psModelLocalService.deletePsModel(psModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _psModelLocalService.dynamicQuery();
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
		return _psModelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _psModelLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _psModelLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _psModelLocalService.dynamicQueryCount(dynamicQuery);
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
		return _psModelLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.stpl.app.model.PsModel fetchPsModel(int psModelSid) {
		return _psModelLocalService.fetchPsModel(psModelSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _psModelLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _psModelLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _psModelLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _psModelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the ps model with the primary key.
	*
	* @param psModelSid the primary key of the ps model
	* @return the ps model
	* @throws PortalException if a ps model with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.PsModel getPsModel(int psModelSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _psModelLocalService.getPsModel(psModelSid);
	}

	/**
	* Returns a range of all the ps models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ps models
	* @param end the upper bound of the range of ps models (not inclusive)
	* @return the range of ps models
	*/
	@Override
	public java.util.List<com.stpl.app.model.PsModel> getPsModels(int start,
		int end) {
		return _psModelLocalService.getPsModels(start, end);
	}

	/**
	* Returns the number of ps models.
	*
	* @return the number of ps models
	*/
	@Override
	public int getPsModelsCount() {
		return _psModelLocalService.getPsModelsCount();
	}

	/**
	* Updates the ps model in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param psModel the ps model
	* @return the ps model that was updated
	*/
	@Override
	public com.stpl.app.model.PsModel updatePsModel(
		com.stpl.app.model.PsModel psModel) {
		return _psModelLocalService.updatePsModel(psModel);
	}

	@Override
	public PsModelLocalService getWrappedService() {
		return _psModelLocalService;
	}

	@Override
	public void setWrappedService(PsModelLocalService psModelLocalService) {
		_psModelLocalService = psModelLocalService;
	}

	private PsModelLocalService _psModelLocalService;
}