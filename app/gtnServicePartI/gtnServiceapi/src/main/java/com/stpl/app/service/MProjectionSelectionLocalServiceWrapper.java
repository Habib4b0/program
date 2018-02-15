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
 * Provides a wrapper for {@link MProjectionSelectionLocalService}.
 *
 * @author
 * @see MProjectionSelectionLocalService
 * @generated
 */
@ProviderType
public class MProjectionSelectionLocalServiceWrapper
	implements MProjectionSelectionLocalService,
		ServiceWrapper<MProjectionSelectionLocalService> {
	public MProjectionSelectionLocalServiceWrapper(
		MProjectionSelectionLocalService mProjectionSelectionLocalService) {
		_mProjectionSelectionLocalService = mProjectionSelectionLocalService;
	}

	/**
	* Adds the m projection selection to the database. Also notifies the appropriate model listeners.
	*
	* @param mProjectionSelection the m projection selection
	* @return the m projection selection that was added
	*/
	@Override
	public com.stpl.app.model.MProjectionSelection addMProjectionSelection(
		com.stpl.app.model.MProjectionSelection mProjectionSelection) {
		return _mProjectionSelectionLocalService.addMProjectionSelection(mProjectionSelection);
	}

	/**
	* Creates a new m projection selection with the primary key. Does not add the m projection selection to the database.
	*
	* @param mProjectionSelectionSid the primary key for the new m projection selection
	* @return the new m projection selection
	*/
	@Override
	public com.stpl.app.model.MProjectionSelection createMProjectionSelection(
		int mProjectionSelectionSid) {
		return _mProjectionSelectionLocalService.createMProjectionSelection(mProjectionSelectionSid);
	}

	/**
	* Deletes the m projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mProjectionSelectionSid the primary key of the m projection selection
	* @return the m projection selection that was removed
	* @throws PortalException if a m projection selection with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MProjectionSelection deleteMProjectionSelection(
		int mProjectionSelectionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mProjectionSelectionLocalService.deleteMProjectionSelection(mProjectionSelectionSid);
	}

	/**
	* Deletes the m projection selection from the database. Also notifies the appropriate model listeners.
	*
	* @param mProjectionSelection the m projection selection
	* @return the m projection selection that was removed
	*/
	@Override
	public com.stpl.app.model.MProjectionSelection deleteMProjectionSelection(
		com.stpl.app.model.MProjectionSelection mProjectionSelection) {
		return _mProjectionSelectionLocalService.deleteMProjectionSelection(mProjectionSelection);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mProjectionSelectionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mProjectionSelectionLocalService.dynamicQuery();
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
		return _mProjectionSelectionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _mProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
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
		return _mProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _mProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.MProjectionSelection fetchMProjectionSelection(
		int mProjectionSelectionSid) {
		return _mProjectionSelectionLocalService.fetchMProjectionSelection(mProjectionSelectionSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _mProjectionSelectionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _mProjectionSelectionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the m projection selection with the primary key.
	*
	* @param mProjectionSelectionSid the primary key of the m projection selection
	* @return the m projection selection
	* @throws PortalException if a m projection selection with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MProjectionSelection getMProjectionSelection(
		int mProjectionSelectionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mProjectionSelectionLocalService.getMProjectionSelection(mProjectionSelectionSid);
	}

	/**
	* Returns a range of all the m projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of m projection selections
	* @param end the upper bound of the range of m projection selections (not inclusive)
	* @return the range of m projection selections
	*/
	@Override
	public java.util.List<com.stpl.app.model.MProjectionSelection> getMProjectionSelections(
		int start, int end) {
		return _mProjectionSelectionLocalService.getMProjectionSelections(start,
			end);
	}

	/**
	* Returns the number of m projection selections.
	*
	* @return the number of m projection selections
	*/
	@Override
	public int getMProjectionSelectionsCount() {
		return _mProjectionSelectionLocalService.getMProjectionSelectionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _mProjectionSelectionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _mProjectionSelectionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the m projection selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mProjectionSelection the m projection selection
	* @return the m projection selection that was updated
	*/
	@Override
	public com.stpl.app.model.MProjectionSelection updateMProjectionSelection(
		com.stpl.app.model.MProjectionSelection mProjectionSelection) {
		return _mProjectionSelectionLocalService.updateMProjectionSelection(mProjectionSelection);
	}

	@Override
	public MProjectionSelectionLocalService getWrappedService() {
		return _mProjectionSelectionLocalService;
	}

	@Override
	public void setWrappedService(
		MProjectionSelectionLocalService mProjectionSelectionLocalService) {
		_mProjectionSelectionLocalService = mProjectionSelectionLocalService;
	}

	private MProjectionSelectionLocalService _mProjectionSelectionLocalService;
}