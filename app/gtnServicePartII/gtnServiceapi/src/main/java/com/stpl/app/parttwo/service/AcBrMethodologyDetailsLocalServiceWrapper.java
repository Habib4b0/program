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
 * Provides a wrapper for {@link AcBrMethodologyDetailsLocalService}.
 *
 * @author
 * @see AcBrMethodologyDetailsLocalService
 * @generated
 */
@ProviderType
public class AcBrMethodologyDetailsLocalServiceWrapper
	implements AcBrMethodologyDetailsLocalService,
		ServiceWrapper<AcBrMethodologyDetailsLocalService> {
	public AcBrMethodologyDetailsLocalServiceWrapper(
		AcBrMethodologyDetailsLocalService acBrMethodologyDetailsLocalService) {
		_acBrMethodologyDetailsLocalService = acBrMethodologyDetailsLocalService;
	}

	/**
	* Adds the ac br methodology details to the database. Also notifies the appropriate model listeners.
	*
	* @param acBrMethodologyDetails the ac br methodology details
	* @return the ac br methodology details that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.AcBrMethodologyDetails addAcBrMethodologyDetails(
		com.stpl.app.parttwo.model.AcBrMethodologyDetails acBrMethodologyDetails) {
		return _acBrMethodologyDetailsLocalService.addAcBrMethodologyDetails(acBrMethodologyDetails);
	}

	/**
	* Creates a new ac br methodology details with the primary key. Does not add the ac br methodology details to the database.
	*
	* @param acBrMethodologyDetailsSid the primary key for the new ac br methodology details
	* @return the new ac br methodology details
	*/
	@Override
	public com.stpl.app.parttwo.model.AcBrMethodologyDetails createAcBrMethodologyDetails(
		int acBrMethodologyDetailsSid) {
		return _acBrMethodologyDetailsLocalService.createAcBrMethodologyDetails(acBrMethodologyDetailsSid);
	}

	/**
	* Deletes the ac br methodology details from the database. Also notifies the appropriate model listeners.
	*
	* @param acBrMethodologyDetails the ac br methodology details
	* @return the ac br methodology details that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.AcBrMethodologyDetails deleteAcBrMethodologyDetails(
		com.stpl.app.parttwo.model.AcBrMethodologyDetails acBrMethodologyDetails) {
		return _acBrMethodologyDetailsLocalService.deleteAcBrMethodologyDetails(acBrMethodologyDetails);
	}

	/**
	* Deletes the ac br methodology details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
	* @return the ac br methodology details that was removed
	* @throws PortalException if a ac br methodology details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.AcBrMethodologyDetails deleteAcBrMethodologyDetails(
		int acBrMethodologyDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _acBrMethodologyDetailsLocalService.deleteAcBrMethodologyDetails(acBrMethodologyDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _acBrMethodologyDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _acBrMethodologyDetailsLocalService.dynamicQuery();
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
		return _acBrMethodologyDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _acBrMethodologyDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _acBrMethodologyDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _acBrMethodologyDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _acBrMethodologyDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.AcBrMethodologyDetails fetchAcBrMethodologyDetails(
		int acBrMethodologyDetailsSid) {
		return _acBrMethodologyDetailsLocalService.fetchAcBrMethodologyDetails(acBrMethodologyDetailsSid);
	}

	/**
	* Returns the ac br methodology details with the primary key.
	*
	* @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
	* @return the ac br methodology details
	* @throws PortalException if a ac br methodology details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.AcBrMethodologyDetails getAcBrMethodologyDetails(
		int acBrMethodologyDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _acBrMethodologyDetailsLocalService.getAcBrMethodologyDetails(acBrMethodologyDetailsSid);
	}

	/**
	* Returns a range of all the ac br methodology detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ac br methodology detailses
	* @param end the upper bound of the range of ac br methodology detailses (not inclusive)
	* @return the range of ac br methodology detailses
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.AcBrMethodologyDetails> getAcBrMethodologyDetailses(
		int start, int end) {
		return _acBrMethodologyDetailsLocalService.getAcBrMethodologyDetailses(start,
			end);
	}

	/**
	* Returns the number of ac br methodology detailses.
	*
	* @return the number of ac br methodology detailses
	*/
	@Override
	public int getAcBrMethodologyDetailsesCount() {
		return _acBrMethodologyDetailsLocalService.getAcBrMethodologyDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _acBrMethodologyDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _acBrMethodologyDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _acBrMethodologyDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _acBrMethodologyDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ac br methodology details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param acBrMethodologyDetails the ac br methodology details
	* @return the ac br methodology details that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.AcBrMethodologyDetails updateAcBrMethodologyDetails(
		com.stpl.app.parttwo.model.AcBrMethodologyDetails acBrMethodologyDetails) {
		return _acBrMethodologyDetailsLocalService.updateAcBrMethodologyDetails(acBrMethodologyDetails);
	}

	@Override
	public AcBrMethodologyDetailsLocalService getWrappedService() {
		return _acBrMethodologyDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		AcBrMethodologyDetailsLocalService acBrMethodologyDetailsLocalService) {
		_acBrMethodologyDetailsLocalService = acBrMethodologyDetailsLocalService;
	}

	private AcBrMethodologyDetailsLocalService _acBrMethodologyDetailsLocalService;
}