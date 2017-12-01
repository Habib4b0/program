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
 * Provides a wrapper for {@link AdditionalNotesLocalService}.
 *
 * @author
 * @see AdditionalNotesLocalService
 * @generated
 */
@ProviderType
public class AdditionalNotesLocalServiceWrapper
	implements AdditionalNotesLocalService,
		ServiceWrapper<AdditionalNotesLocalService> {
	public AdditionalNotesLocalServiceWrapper(
		AdditionalNotesLocalService additionalNotesLocalService) {
		_additionalNotesLocalService = additionalNotesLocalService;
	}

	/**
	* Adds the additional notes to the database. Also notifies the appropriate model listeners.
	*
	* @param additionalNotes the additional notes
	* @return the additional notes that was added
	*/
	@Override
	public com.stpl.app.model.AdditionalNotes addAdditionalNotes(
		com.stpl.app.model.AdditionalNotes additionalNotes) {
		return _additionalNotesLocalService.addAdditionalNotes(additionalNotes);
	}

	/**
	* Creates a new additional notes with the primary key. Does not add the additional notes to the database.
	*
	* @param additionalNotesId the primary key for the new additional notes
	* @return the new additional notes
	*/
	@Override
	public com.stpl.app.model.AdditionalNotes createAdditionalNotes(
		int additionalNotesId) {
		return _additionalNotesLocalService.createAdditionalNotes(additionalNotesId);
	}

	/**
	* Deletes the additional notes from the database. Also notifies the appropriate model listeners.
	*
	* @param additionalNotes the additional notes
	* @return the additional notes that was removed
	*/
	@Override
	public com.stpl.app.model.AdditionalNotes deleteAdditionalNotes(
		com.stpl.app.model.AdditionalNotes additionalNotes) {
		return _additionalNotesLocalService.deleteAdditionalNotes(additionalNotes);
	}

	/**
	* Deletes the additional notes with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param additionalNotesId the primary key of the additional notes
	* @return the additional notes that was removed
	* @throws PortalException if a additional notes with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.AdditionalNotes deleteAdditionalNotes(
		int additionalNotesId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _additionalNotesLocalService.deleteAdditionalNotes(additionalNotesId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _additionalNotesLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _additionalNotesLocalService.dynamicQuery();
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
		return _additionalNotesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _additionalNotesLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _additionalNotesLocalService.dynamicQuery(dynamicQuery, start,
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
		return _additionalNotesLocalService.dynamicQueryCount(dynamicQuery);
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
		return _additionalNotesLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.AdditionalNotes fetchAdditionalNotes(
		int additionalNotesId) {
		return _additionalNotesLocalService.fetchAdditionalNotes(additionalNotesId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _additionalNotesLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the additional notes with the primary key.
	*
	* @param additionalNotesId the primary key of the additional notes
	* @return the additional notes
	* @throws PortalException if a additional notes with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.AdditionalNotes getAdditionalNotes(
		int additionalNotesId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _additionalNotesLocalService.getAdditionalNotes(additionalNotesId);
	}

	/**
	* Returns a range of all the additional noteses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AdditionalNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of additional noteses
	* @param end the upper bound of the range of additional noteses (not inclusive)
	* @return the range of additional noteses
	*/
	@Override
	public java.util.List<com.stpl.app.model.AdditionalNotes> getAdditionalNoteses(
		int start, int end) {
		return _additionalNotesLocalService.getAdditionalNoteses(start, end);
	}

	/**
	* Returns the number of additional noteses.
	*
	* @return the number of additional noteses
	*/
	@Override
	public int getAdditionalNotesesCount() {
		return _additionalNotesLocalService.getAdditionalNotesesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _additionalNotesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _additionalNotesLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _additionalNotesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the additional notes in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param additionalNotes the additional notes
	* @return the additional notes that was updated
	*/
	@Override
	public com.stpl.app.model.AdditionalNotes updateAdditionalNotes(
		com.stpl.app.model.AdditionalNotes additionalNotes) {
		return _additionalNotesLocalService.updateAdditionalNotes(additionalNotes);
	}

	@Override
	public AdditionalNotesLocalService getWrappedService() {
		return _additionalNotesLocalService;
	}

	@Override
	public void setWrappedService(
		AdditionalNotesLocalService additionalNotesLocalService) {
		_additionalNotesLocalService = additionalNotesLocalService;
	}

	private AdditionalNotesLocalService _additionalNotesLocalService;
}