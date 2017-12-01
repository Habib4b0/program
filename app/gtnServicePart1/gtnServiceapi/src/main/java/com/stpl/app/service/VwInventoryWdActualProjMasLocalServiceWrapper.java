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
 * Provides a wrapper for {@link VwInventoryWdActualProjMasLocalService}.
 *
 * @author
 * @see VwInventoryWdActualProjMasLocalService
 * @generated
 */
@ProviderType
public class VwInventoryWdActualProjMasLocalServiceWrapper
	implements VwInventoryWdActualProjMasLocalService,
		ServiceWrapper<VwInventoryWdActualProjMasLocalService> {
	public VwInventoryWdActualProjMasLocalServiceWrapper(
		VwInventoryWdActualProjMasLocalService vwInventoryWdActualProjMasLocalService) {
		_vwInventoryWdActualProjMasLocalService = vwInventoryWdActualProjMasLocalService;
	}

	/**
	* Adds the vw inventory wd actual proj mas to the database. Also notifies the appropriate model listeners.
	*
	* @param vwInventoryWdActualProjMas the vw inventory wd actual proj mas
	* @return the vw inventory wd actual proj mas that was added
	*/
	@Override
	public com.stpl.app.model.VwInventoryWdActualProjMas addVwInventoryWdActualProjMas(
		com.stpl.app.model.VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		return _vwInventoryWdActualProjMasLocalService.addVwInventoryWdActualProjMas(vwInventoryWdActualProjMas);
	}

	/**
	* Creates a new vw inventory wd actual proj mas with the primary key. Does not add the vw inventory wd actual proj mas to the database.
	*
	* @param inventoryWdActualProjMasSid the primary key for the new vw inventory wd actual proj mas
	* @return the new vw inventory wd actual proj mas
	*/
	@Override
	public com.stpl.app.model.VwInventoryWdActualProjMas createVwInventoryWdActualProjMas(
		int inventoryWdActualProjMasSid) {
		return _vwInventoryWdActualProjMasLocalService.createVwInventoryWdActualProjMas(inventoryWdActualProjMasSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwInventoryWdActualProjMasLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the vw inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
	* @return the vw inventory wd actual proj mas that was removed
	* @throws PortalException if a vw inventory wd actual proj mas with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.VwInventoryWdActualProjMas deleteVwInventoryWdActualProjMas(
		int inventoryWdActualProjMasSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwInventoryWdActualProjMasLocalService.deleteVwInventoryWdActualProjMas(inventoryWdActualProjMasSid);
	}

	/**
	* Deletes the vw inventory wd actual proj mas from the database. Also notifies the appropriate model listeners.
	*
	* @param vwInventoryWdActualProjMas the vw inventory wd actual proj mas
	* @return the vw inventory wd actual proj mas that was removed
	*/
	@Override
	public com.stpl.app.model.VwInventoryWdActualProjMas deleteVwInventoryWdActualProjMas(
		com.stpl.app.model.VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		return _vwInventoryWdActualProjMasLocalService.deleteVwInventoryWdActualProjMas(vwInventoryWdActualProjMas);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _vwInventoryWdActualProjMasLocalService.dynamicQuery();
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
		return _vwInventoryWdActualProjMasLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwInventoryWdActualProjMasLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwInventoryWdActualProjMasLocalService.dynamicQuery(dynamicQuery,
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
		return _vwInventoryWdActualProjMasLocalService.dynamicQueryCount(dynamicQuery);
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
		return _vwInventoryWdActualProjMasLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.VwInventoryWdActualProjMas fetchVwInventoryWdActualProjMas(
		int inventoryWdActualProjMasSid) {
		return _vwInventoryWdActualProjMasLocalService.fetchVwInventoryWdActualProjMas(inventoryWdActualProjMasSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _vwInventoryWdActualProjMasLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _vwInventoryWdActualProjMasLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _vwInventoryWdActualProjMasLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwInventoryWdActualProjMasLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the vw inventory wd actual proj mas with the primary key.
	*
	* @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
	* @return the vw inventory wd actual proj mas
	* @throws PortalException if a vw inventory wd actual proj mas with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.VwInventoryWdActualProjMas getVwInventoryWdActualProjMas(
		int inventoryWdActualProjMasSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwInventoryWdActualProjMasLocalService.getVwInventoryWdActualProjMas(inventoryWdActualProjMasSid);
	}

	/**
	* Returns a range of all the vw inventory wd actual proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw inventory wd actual proj mases
	* @param end the upper bound of the range of vw inventory wd actual proj mases (not inclusive)
	* @return the range of vw inventory wd actual proj mases
	*/
	@Override
	public java.util.List<com.stpl.app.model.VwInventoryWdActualProjMas> getVwInventoryWdActualProjMases(
		int start, int end) {
		return _vwInventoryWdActualProjMasLocalService.getVwInventoryWdActualProjMases(start,
			end);
	}

	/**
	* Returns the number of vw inventory wd actual proj mases.
	*
	* @return the number of vw inventory wd actual proj mases
	*/
	@Override
	public int getVwInventoryWdActualProjMasesCount() {
		return _vwInventoryWdActualProjMasLocalService.getVwInventoryWdActualProjMasesCount();
	}

	/**
	* Updates the vw inventory wd actual proj mas in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param vwInventoryWdActualProjMas the vw inventory wd actual proj mas
	* @return the vw inventory wd actual proj mas that was updated
	*/
	@Override
	public com.stpl.app.model.VwInventoryWdActualProjMas updateVwInventoryWdActualProjMas(
		com.stpl.app.model.VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		return _vwInventoryWdActualProjMasLocalService.updateVwInventoryWdActualProjMas(vwInventoryWdActualProjMas);
	}

	@Override
	public VwInventoryWdActualProjMasLocalService getWrappedService() {
		return _vwInventoryWdActualProjMasLocalService;
	}

	@Override
	public void setWrappedService(
		VwInventoryWdActualProjMasLocalService vwInventoryWdActualProjMasLocalService) {
		_vwInventoryWdActualProjMasLocalService = vwInventoryWdActualProjMasLocalService;
	}

	private VwInventoryWdActualProjMasLocalService _vwInventoryWdActualProjMasLocalService;
}