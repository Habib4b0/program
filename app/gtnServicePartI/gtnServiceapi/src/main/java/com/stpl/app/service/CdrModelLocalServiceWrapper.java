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
 * Provides a wrapper for {@link CdrModelLocalService}.
 *
 * @author
 * @see CdrModelLocalService
 * @generated
 */
@ProviderType
public class CdrModelLocalServiceWrapper implements CdrModelLocalService,
	ServiceWrapper<CdrModelLocalService> {
	public CdrModelLocalServiceWrapper(
		CdrModelLocalService cdrModelLocalService) {
		_cdrModelLocalService = cdrModelLocalService;
	}

	/**
	* Adds the cdr model to the database. Also notifies the appropriate model listeners.
	*
	* @param cdrModel the cdr model
	* @return the cdr model that was added
	*/
	@Override
	public com.stpl.app.model.CdrModel addCdrModel(
		com.stpl.app.model.CdrModel cdrModel) {
		return _cdrModelLocalService.addCdrModel(cdrModel);
	}

	/**
	* Creates a new cdr model with the primary key. Does not add the cdr model to the database.
	*
	* @param cdrModelSid the primary key for the new cdr model
	* @return the new cdr model
	*/
	@Override
	public com.stpl.app.model.CdrModel createCdrModel(int cdrModelSid) {
		return _cdrModelLocalService.createCdrModel(cdrModelSid);
	}

	/**
	* Deletes the cdr model from the database. Also notifies the appropriate model listeners.
	*
	* @param cdrModel the cdr model
	* @return the cdr model that was removed
	*/
	@Override
	public com.stpl.app.model.CdrModel deleteCdrModel(
		com.stpl.app.model.CdrModel cdrModel) {
		return _cdrModelLocalService.deleteCdrModel(cdrModel);
	}

	/**
	* Deletes the cdr model with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cdrModelSid the primary key of the cdr model
	* @return the cdr model that was removed
	* @throws PortalException if a cdr model with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CdrModel deleteCdrModel(int cdrModelSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cdrModelLocalService.deleteCdrModel(cdrModelSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cdrModelLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cdrModelLocalService.dynamicQuery();
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
		return _cdrModelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cdrModelLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cdrModelLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _cdrModelLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cdrModelLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.stpl.app.model.CdrModel fetchCdrModel(int cdrModelSid) {
		return _cdrModelLocalService.fetchCdrModel(cdrModelSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cdrModelLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cdr model with the primary key.
	*
	* @param cdrModelSid the primary key of the cdr model
	* @return the cdr model
	* @throws PortalException if a cdr model with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CdrModel getCdrModel(int cdrModelSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cdrModelLocalService.getCdrModel(cdrModelSid);
	}

	/**
	* Returns a range of all the cdr models.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cdr models
	* @param end the upper bound of the range of cdr models (not inclusive)
	* @return the range of cdr models
	*/
	@Override
	public java.util.List<com.stpl.app.model.CdrModel> getCdrModels(int start,
		int end) {
		return _cdrModelLocalService.getCdrModels(start, end);
	}

	/**
	* Returns the number of cdr models.
	*
	* @return the number of cdr models
	*/
	@Override
	public int getCdrModelsCount() {
		return _cdrModelLocalService.getCdrModelsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cdrModelLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cdrModelLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cdrModelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cdr model in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cdrModel the cdr model
	* @return the cdr model that was updated
	*/
	@Override
	public com.stpl.app.model.CdrModel updateCdrModel(
		com.stpl.app.model.CdrModel cdrModel) {
		return _cdrModelLocalService.updateCdrModel(cdrModel);
	}

	@Override
	public CdrModelLocalService getWrappedService() {
		return _cdrModelLocalService;
	}

	@Override
	public void setWrappedService(CdrModelLocalService cdrModelLocalService) {
		_cdrModelLocalService = cdrModelLocalService;
	}

	private CdrModelLocalService _cdrModelLocalService;
}