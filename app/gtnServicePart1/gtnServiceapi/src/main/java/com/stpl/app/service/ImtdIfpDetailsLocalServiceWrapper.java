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
 * Provides a wrapper for {@link ImtdIfpDetailsLocalService}.
 *
 * @author
 * @see ImtdIfpDetailsLocalService
 * @generated
 */
@ProviderType
public class ImtdIfpDetailsLocalServiceWrapper
	implements ImtdIfpDetailsLocalService,
		ServiceWrapper<ImtdIfpDetailsLocalService> {
	public ImtdIfpDetailsLocalServiceWrapper(
		ImtdIfpDetailsLocalService imtdIfpDetailsLocalService) {
		_imtdIfpDetailsLocalService = imtdIfpDetailsLocalService;
	}

	/**
	* Adds the imtd ifp details to the database. Also notifies the appropriate model listeners.
	*
	* @param imtdIfpDetails the imtd ifp details
	* @return the imtd ifp details that was added
	*/
	@Override
	public com.stpl.app.model.ImtdIfpDetails addImtdIfpDetails(
		com.stpl.app.model.ImtdIfpDetails imtdIfpDetails) {
		return _imtdIfpDetailsLocalService.addImtdIfpDetails(imtdIfpDetails);
	}

	/**
	* Creates a new imtd ifp details with the primary key. Does not add the imtd ifp details to the database.
	*
	* @param imtdIfpDetailsSid the primary key for the new imtd ifp details
	* @return the new imtd ifp details
	*/
	@Override
	public com.stpl.app.model.ImtdIfpDetails createImtdIfpDetails(
		int imtdIfpDetailsSid) {
		return _imtdIfpDetailsLocalService.createImtdIfpDetails(imtdIfpDetailsSid);
	}

	/**
	* Deletes the imtd ifp details from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdIfpDetails the imtd ifp details
	* @return the imtd ifp details that was removed
	*/
	@Override
	public com.stpl.app.model.ImtdIfpDetails deleteImtdIfpDetails(
		com.stpl.app.model.ImtdIfpDetails imtdIfpDetails) {
		return _imtdIfpDetailsLocalService.deleteImtdIfpDetails(imtdIfpDetails);
	}

	/**
	* Deletes the imtd ifp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdIfpDetailsSid the primary key of the imtd ifp details
	* @return the imtd ifp details that was removed
	* @throws PortalException if a imtd ifp details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ImtdIfpDetails deleteImtdIfpDetails(
		int imtdIfpDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _imtdIfpDetailsLocalService.deleteImtdIfpDetails(imtdIfpDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _imtdIfpDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _imtdIfpDetailsLocalService.dynamicQuery();
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
		return _imtdIfpDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _imtdIfpDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _imtdIfpDetailsLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _imtdIfpDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _imtdIfpDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ImtdIfpDetails fetchImtdIfpDetails(
		int imtdIfpDetailsSid) {
		return _imtdIfpDetailsLocalService.fetchImtdIfpDetails(imtdIfpDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _imtdIfpDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the imtd ifp details with the primary key.
	*
	* @param imtdIfpDetailsSid the primary key of the imtd ifp details
	* @return the imtd ifp details
	* @throws PortalException if a imtd ifp details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ImtdIfpDetails getImtdIfpDetails(
		int imtdIfpDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _imtdIfpDetailsLocalService.getImtdIfpDetails(imtdIfpDetailsSid);
	}

	/**
	* Returns a range of all the imtd ifp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdIfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd ifp detailses
	* @param end the upper bound of the range of imtd ifp detailses (not inclusive)
	* @return the range of imtd ifp detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.ImtdIfpDetails> getImtdIfpDetailses(
		int start, int end) {
		return _imtdIfpDetailsLocalService.getImtdIfpDetailses(start, end);
	}

	/**
	* Returns the number of imtd ifp detailses.
	*
	* @return the number of imtd ifp detailses
	*/
	@Override
	public int getImtdIfpDetailsesCount() {
		return _imtdIfpDetailsLocalService.getImtdIfpDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _imtdIfpDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _imtdIfpDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _imtdIfpDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the imtd ifp details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param imtdIfpDetails the imtd ifp details
	* @return the imtd ifp details that was updated
	*/
	@Override
	public com.stpl.app.model.ImtdIfpDetails updateImtdIfpDetails(
		com.stpl.app.model.ImtdIfpDetails imtdIfpDetails) {
		return _imtdIfpDetailsLocalService.updateImtdIfpDetails(imtdIfpDetails);
	}

	@Override
	public ImtdIfpDetailsLocalService getWrappedService() {
		return _imtdIfpDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		ImtdIfpDetailsLocalService imtdIfpDetailsLocalService) {
		_imtdIfpDetailsLocalService = imtdIfpDetailsLocalService;
	}

	private ImtdIfpDetailsLocalService _imtdIfpDetailsLocalService;
}