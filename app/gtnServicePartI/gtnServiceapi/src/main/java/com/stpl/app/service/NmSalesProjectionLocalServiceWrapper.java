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
 * Provides a wrapper for {@link NmSalesProjectionLocalService}.
 *
 * @author
 * @see NmSalesProjectionLocalService
 * @generated
 */
@ProviderType
public class NmSalesProjectionLocalServiceWrapper
	implements NmSalesProjectionLocalService,
		ServiceWrapper<NmSalesProjectionLocalService> {
	public NmSalesProjectionLocalServiceWrapper(
		NmSalesProjectionLocalService nmSalesProjectionLocalService) {
		_nmSalesProjectionLocalService = nmSalesProjectionLocalService;
	}

	/**
	* Adds the nm sales projection to the database. Also notifies the appropriate model listeners.
	*
	* @param nmSalesProjection the nm sales projection
	* @return the nm sales projection that was added
	*/
	@Override
	public com.stpl.app.model.NmSalesProjection addNmSalesProjection(
		com.stpl.app.model.NmSalesProjection nmSalesProjection) {
		return _nmSalesProjectionLocalService.addNmSalesProjection(nmSalesProjection);
	}

	/**
	* Creates a new nm sales projection with the primary key. Does not add the nm sales projection to the database.
	*
	* @param nmSalesProjectionPK the primary key for the new nm sales projection
	* @return the new nm sales projection
	*/
	@Override
	public com.stpl.app.model.NmSalesProjection createNmSalesProjection(
		com.stpl.app.service.persistence.NmSalesProjectionPK nmSalesProjectionPK) {
		return _nmSalesProjectionLocalService.createNmSalesProjection(nmSalesProjectionPK);
	}

	/**
	* Deletes the nm sales projection from the database. Also notifies the appropriate model listeners.
	*
	* @param nmSalesProjection the nm sales projection
	* @return the nm sales projection that was removed
	*/
	@Override
	public com.stpl.app.model.NmSalesProjection deleteNmSalesProjection(
		com.stpl.app.model.NmSalesProjection nmSalesProjection) {
		return _nmSalesProjectionLocalService.deleteNmSalesProjection(nmSalesProjection);
	}

	/**
	* Deletes the nm sales projection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param nmSalesProjectionPK the primary key of the nm sales projection
	* @return the nm sales projection that was removed
	* @throws PortalException if a nm sales projection with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NmSalesProjection deleteNmSalesProjection(
		com.stpl.app.service.persistence.NmSalesProjectionPK nmSalesProjectionPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmSalesProjectionLocalService.deleteNmSalesProjection(nmSalesProjectionPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmSalesProjectionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _nmSalesProjectionLocalService.dynamicQuery();
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
		return _nmSalesProjectionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nmSalesProjectionLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _nmSalesProjectionLocalService.dynamicQuery(dynamicQuery, start,
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
		return _nmSalesProjectionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _nmSalesProjectionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.NmSalesProjection fetchNmSalesProjection(
		com.stpl.app.service.persistence.NmSalesProjectionPK nmSalesProjectionPK) {
		return _nmSalesProjectionLocalService.fetchNmSalesProjection(nmSalesProjectionPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _nmSalesProjectionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _nmSalesProjectionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the nm sales projection with the primary key.
	*
	* @param nmSalesProjectionPK the primary key of the nm sales projection
	* @return the nm sales projection
	* @throws PortalException if a nm sales projection with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.NmSalesProjection getNmSalesProjection(
		com.stpl.app.service.persistence.NmSalesProjectionPK nmSalesProjectionPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmSalesProjectionLocalService.getNmSalesProjection(nmSalesProjectionPK);
	}

	/**
	* Returns a range of all the nm sales projections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of nm sales projections
	* @param end the upper bound of the range of nm sales projections (not inclusive)
	* @return the range of nm sales projections
	*/
	@Override
	public java.util.List<com.stpl.app.model.NmSalesProjection> getNmSalesProjections(
		int start, int end) {
		return _nmSalesProjectionLocalService.getNmSalesProjections(start, end);
	}

	/**
	* Returns the number of nm sales projections.
	*
	* @return the number of nm sales projections
	*/
	@Override
	public int getNmSalesProjectionsCount() {
		return _nmSalesProjectionLocalService.getNmSalesProjectionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _nmSalesProjectionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _nmSalesProjectionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the nm sales projection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param nmSalesProjection the nm sales projection
	* @return the nm sales projection that was updated
	*/
	@Override
	public com.stpl.app.model.NmSalesProjection updateNmSalesProjection(
		com.stpl.app.model.NmSalesProjection nmSalesProjection) {
		return _nmSalesProjectionLocalService.updateNmSalesProjection(nmSalesProjection);
	}

	@Override
	public NmSalesProjectionLocalService getWrappedService() {
		return _nmSalesProjectionLocalService;
	}

	@Override
	public void setWrappedService(
		NmSalesProjectionLocalService nmSalesProjectionLocalService) {
		_nmSalesProjectionLocalService = nmSalesProjectionLocalService;
	}

	private NmSalesProjectionLocalService _nmSalesProjectionLocalService;
}