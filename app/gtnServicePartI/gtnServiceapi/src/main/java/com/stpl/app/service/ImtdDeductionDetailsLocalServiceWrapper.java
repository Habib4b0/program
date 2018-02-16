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
 * Provides a wrapper for {@link ImtdDeductionDetailsLocalService}.
 *
 * @author
 * @see ImtdDeductionDetailsLocalService
 * @generated
 */
@ProviderType
public class ImtdDeductionDetailsLocalServiceWrapper
	implements ImtdDeductionDetailsLocalService,
		ServiceWrapper<ImtdDeductionDetailsLocalService> {
	public ImtdDeductionDetailsLocalServiceWrapper(
		ImtdDeductionDetailsLocalService imtdDeductionDetailsLocalService) {
		_imtdDeductionDetailsLocalService = imtdDeductionDetailsLocalService;
	}

	/**
	* Adds the imtd deduction details to the database. Also notifies the appropriate model listeners.
	*
	* @param imtdDeductionDetails the imtd deduction details
	* @return the imtd deduction details that was added
	*/
	@Override
	public com.stpl.app.model.ImtdDeductionDetails addImtdDeductionDetails(
		com.stpl.app.model.ImtdDeductionDetails imtdDeductionDetails) {
		return _imtdDeductionDetailsLocalService.addImtdDeductionDetails(imtdDeductionDetails);
	}

	/**
	* Creates a new imtd deduction details with the primary key. Does not add the imtd deduction details to the database.
	*
	* @param imtdDeductionDetailsSid the primary key for the new imtd deduction details
	* @return the new imtd deduction details
	*/
	@Override
	public com.stpl.app.model.ImtdDeductionDetails createImtdDeductionDetails(
		int imtdDeductionDetailsSid) {
		return _imtdDeductionDetailsLocalService.createImtdDeductionDetails(imtdDeductionDetailsSid);
	}

	/**
	* Deletes the imtd deduction details from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdDeductionDetails the imtd deduction details
	* @return the imtd deduction details that was removed
	*/
	@Override
	public com.stpl.app.model.ImtdDeductionDetails deleteImtdDeductionDetails(
		com.stpl.app.model.ImtdDeductionDetails imtdDeductionDetails) {
		return _imtdDeductionDetailsLocalService.deleteImtdDeductionDetails(imtdDeductionDetails);
	}

	/**
	* Deletes the imtd deduction details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param imtdDeductionDetailsSid the primary key of the imtd deduction details
	* @return the imtd deduction details that was removed
	* @throws PortalException if a imtd deduction details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ImtdDeductionDetails deleteImtdDeductionDetails(
		int imtdDeductionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _imtdDeductionDetailsLocalService.deleteImtdDeductionDetails(imtdDeductionDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _imtdDeductionDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _imtdDeductionDetailsLocalService.dynamicQuery();
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
		return _imtdDeductionDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdDeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _imtdDeductionDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdDeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _imtdDeductionDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _imtdDeductionDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _imtdDeductionDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ImtdDeductionDetails fetchImtdDeductionDetails(
		int imtdDeductionDetailsSid) {
		return _imtdDeductionDetailsLocalService.fetchImtdDeductionDetails(imtdDeductionDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _imtdDeductionDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the imtd deduction details with the primary key.
	*
	* @param imtdDeductionDetailsSid the primary key of the imtd deduction details
	* @return the imtd deduction details
	* @throws PortalException if a imtd deduction details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ImtdDeductionDetails getImtdDeductionDetails(
		int imtdDeductionDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _imtdDeductionDetailsLocalService.getImtdDeductionDetails(imtdDeductionDetailsSid);
	}

	/**
	* Returns a range of all the imtd deduction detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdDeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of imtd deduction detailses
	* @param end the upper bound of the range of imtd deduction detailses (not inclusive)
	* @return the range of imtd deduction detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.ImtdDeductionDetails> getImtdDeductionDetailses(
		int start, int end) {
		return _imtdDeductionDetailsLocalService.getImtdDeductionDetailses(start,
			end);
	}

	/**
	* Returns the number of imtd deduction detailses.
	*
	* @return the number of imtd deduction detailses
	*/
	@Override
	public int getImtdDeductionDetailsesCount() {
		return _imtdDeductionDetailsLocalService.getImtdDeductionDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _imtdDeductionDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _imtdDeductionDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _imtdDeductionDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the imtd deduction details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param imtdDeductionDetails the imtd deduction details
	* @return the imtd deduction details that was updated
	*/
	@Override
	public com.stpl.app.model.ImtdDeductionDetails updateImtdDeductionDetails(
		com.stpl.app.model.ImtdDeductionDetails imtdDeductionDetails) {
		return _imtdDeductionDetailsLocalService.updateImtdDeductionDetails(imtdDeductionDetails);
	}

	@Override
	public ImtdDeductionDetailsLocalService getWrappedService() {
		return _imtdDeductionDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		ImtdDeductionDetailsLocalService imtdDeductionDetailsLocalService) {
		_imtdDeductionDetailsLocalService = imtdDeductionDetailsLocalService;
	}

	private ImtdDeductionDetailsLocalService _imtdDeductionDetailsLocalService;
}