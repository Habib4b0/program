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
 * Provides a wrapper for {@link ChProjectionSelectionLocalService}.
 *
 * @author
 * @see ChProjectionSelectionLocalService
 * @generated
 */
@ProviderType
public class ChProjectionSelectionLocalServiceWrapper
	implements ChProjectionSelectionLocalService,
		ServiceWrapper<ChProjectionSelectionLocalService> {
	public ChProjectionSelectionLocalServiceWrapper(
		ChProjectionSelectionLocalService chProjectionSelectionLocalService) {
		_chProjectionSelectionLocalService = chProjectionSelectionLocalService;
	}

	/**
	* Adds the ch projection selection to the database. Also notifies the appropriate model listeners.
	*
	* @param chProjectionSelection the ch projection selection
	* @return the ch projection selection that was added
	*/
	@Override
	public com.stpl.app.model.ChProjectionSelection addChProjectionSelection(
		com.stpl.app.model.ChProjectionSelection chProjectionSelection) {
		return _chProjectionSelectionLocalService.addChProjectionSelection(chProjectionSelection);
	}

	/**
	* Creates a new ch projection selection with the primary key. Does not add the ch projection selection to the database.
	*
	* @param chProjectionSelectionSid the primary key for the new ch projection selection
	* @return the new ch projection selection
	*/
	@Override
	public com.stpl.app.model.ChProjectionSelection createChProjectionSelection(
		int chProjectionSelectionSid) {
		return _chProjectionSelectionLocalService.createChProjectionSelection(chProjectionSelectionSid);
	}

	/**
	* Deletes the ch projection selection from the database. Also notifies the appropriate model listeners.
	*
	* @param chProjectionSelection the ch projection selection
	* @return the ch projection selection that was removed
	*/
	@Override
	public com.stpl.app.model.ChProjectionSelection deleteChProjectionSelection(
		com.stpl.app.model.ChProjectionSelection chProjectionSelection) {
		return _chProjectionSelectionLocalService.deleteChProjectionSelection(chProjectionSelection);
	}

	/**
	* Deletes the ch projection selection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param chProjectionSelectionSid the primary key of the ch projection selection
	* @return the ch projection selection that was removed
	* @throws PortalException if a ch projection selection with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ChProjectionSelection deleteChProjectionSelection(
		int chProjectionSelectionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chProjectionSelectionLocalService.deleteChProjectionSelection(chProjectionSelectionSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chProjectionSelectionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _chProjectionSelectionLocalService.dynamicQuery();
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
		return _chProjectionSelectionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _chProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _chProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
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
		return _chProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _chProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.ChProjectionSelection fetchChProjectionSelection(
		int chProjectionSelectionSid) {
		return _chProjectionSelectionLocalService.fetchChProjectionSelection(chProjectionSelectionSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _chProjectionSelectionLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the ch projection selection with the primary key.
	*
	* @param chProjectionSelectionSid the primary key of the ch projection selection
	* @return the ch projection selection
	* @throws PortalException if a ch projection selection with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.ChProjectionSelection getChProjectionSelection(
		int chProjectionSelectionSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chProjectionSelectionLocalService.getChProjectionSelection(chProjectionSelectionSid);
	}

	/**
	* Returns a range of all the ch projection selections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ch projection selections
	* @param end the upper bound of the range of ch projection selections (not inclusive)
	* @return the range of ch projection selections
	*/
	@Override
	public java.util.List<com.stpl.app.model.ChProjectionSelection> getChProjectionSelections(
		int start, int end) {
		return _chProjectionSelectionLocalService.getChProjectionSelections(start,
			end);
	}

	/**
	* Returns the number of ch projection selections.
	*
	* @return the number of ch projection selections
	*/
	@Override
	public int getChProjectionSelectionsCount() {
		return _chProjectionSelectionLocalService.getChProjectionSelectionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _chProjectionSelectionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _chProjectionSelectionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _chProjectionSelectionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ch projection selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param chProjectionSelection the ch projection selection
	* @return the ch projection selection that was updated
	*/
	@Override
	public com.stpl.app.model.ChProjectionSelection updateChProjectionSelection(
		com.stpl.app.model.ChProjectionSelection chProjectionSelection) {
		return _chProjectionSelectionLocalService.updateChProjectionSelection(chProjectionSelection);
	}

	@Override
	public ChProjectionSelectionLocalService getWrappedService() {
		return _chProjectionSelectionLocalService;
	}

	@Override
	public void setWrappedService(
		ChProjectionSelectionLocalService chProjectionSelectionLocalService) {
		_chProjectionSelectionLocalService = chProjectionSelectionLocalService;
	}

	private ChProjectionSelectionLocalService _chProjectionSelectionLocalService;
}