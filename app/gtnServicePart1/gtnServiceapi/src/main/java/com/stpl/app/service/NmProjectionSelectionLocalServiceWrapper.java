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
 * Provides a wrapper for {@link NmProjectionSelectionLocalService}.
 *
 * @author
 * @see NmProjectionSelectionLocalService
 * @generated
 */
@ProviderType
public class NmProjectionSelectionLocalServiceWrapper
	implements NmProjectionSelectionLocalService,
		ServiceWrapper<NmProjectionSelectionLocalService> {
	public NmProjectionSelectionLocalServiceWrapper(
		NmProjectionSelectionLocalService nmProjectionSelectionLocalService) {
		_nmProjectionSelectionLocalService = nmProjectionSelectionLocalService;
	}

	/**
	* Adds the nm projection selection to the database. Also notifies the appropriate model listeners.
	*
	* @param nmProjectionSelection the nm projection selection
	* @return the nm projection selection that was added
	*/
	@Override
	public com.stpl.app.model.NmProjectionSelection addNmProjectionSelection(
		com.stpl.app.model.NmProjectionSelection nmProjectionSelection) {
		return _nmProjectionSelectionLocalService.addNmProjectionSelection(nmProjectionSelection);
	}

	/**
	* Creates a new nm projection selection with the primary key. Does not add the nm projection selection to the database.
	*
	* @param nmProjectionSelectionSid the primary key for the new nm projection selection
	* @return the new nm projection selection
	*/
	@Override
	public com.stpl.app.model.NmProjectionSelection createNmProjectionSelection(
		int nmProjectionSelectionSid) {
		return _nmProjectionSelectionLocalService.createNmProjectionSelection(nmProjectionSelectionSid);
	}

	/**
	* Deletes the nm projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmProjectionSelectionSid the primary key of the nm projection selection
	* @return the nm projection selection that was removed
	* @throws PortalException if a nm projection selection with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NmProjectionSelection deleteNmProjectionSelection(
		int nmProjectionSelectionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmProjectionSelectionLocalService.deleteNmProjectionSelection(nmProjectionSelectionSid);
	}

	/**
	* Deletes the nm projection selection from the database. Also notifies the appropriate model listeners.
	*
	* @param nmProjectionSelection the nm projection selection
	* @return the nm projection selection that was removed
	*/
	@Override
	public com.stpl.app.model.NmProjectionSelection deleteNmProjectionSelection(
		com.stpl.app.model.NmProjectionSelection nmProjectionSelection) {
		return _nmProjectionSelectionLocalService.deleteNmProjectionSelection(nmProjectionSelection);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmProjectionSelectionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _nmProjectionSelectionLocalService.dynamicQuery();
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
		return _nmProjectionSelectionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nmProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nmProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
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
		return _nmProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _nmProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.NmProjectionSelection fetchNmProjectionSelection(
		int nmProjectionSelectionSid) {
		return _nmProjectionSelectionLocalService.fetchNmProjectionSelection(nmProjectionSelectionSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _nmProjectionSelectionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _nmProjectionSelectionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the nm projection selection with the primary key.
	*
	* @param nmProjectionSelectionSid the primary key of the nm projection selection
	* @return the nm projection selection
	* @throws PortalException if a nm projection selection with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NmProjectionSelection getNmProjectionSelection(
		int nmProjectionSelectionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmProjectionSelectionLocalService.getNmProjectionSelection(nmProjectionSelectionSid);
	}

	/**
	* Returns a range of all the nm projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm projection selections
	* @param end the upper bound of the range of nm projection selections (not inclusive)
	* @return the range of nm projection selections
	*/
	@Override
	public java.util.List<com.stpl.app.model.NmProjectionSelection> getNmProjectionSelections(
		int start, int end) {
		return _nmProjectionSelectionLocalService.getNmProjectionSelections(start,
			end);
	}

	/**
	* Returns the number of nm projection selections.
	*
	* @return the number of nm projection selections
	*/
	@Override
	public int getNmProjectionSelectionsCount() {
		return _nmProjectionSelectionLocalService.getNmProjectionSelectionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _nmProjectionSelectionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmProjectionSelectionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the nm projection selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nmProjectionSelection the nm projection selection
	* @return the nm projection selection that was updated
	*/
	@Override
	public com.stpl.app.model.NmProjectionSelection updateNmProjectionSelection(
		com.stpl.app.model.NmProjectionSelection nmProjectionSelection) {
		return _nmProjectionSelectionLocalService.updateNmProjectionSelection(nmProjectionSelection);
	}

	@Override
	public NmProjectionSelectionLocalService getWrappedService() {
		return _nmProjectionSelectionLocalService;
	}

	@Override
	public void setWrappedService(
		NmProjectionSelectionLocalService nmProjectionSelectionLocalService) {
		_nmProjectionSelectionLocalService = nmProjectionSelectionLocalService;
	}

	private NmProjectionSelectionLocalService _nmProjectionSelectionLocalService;
}