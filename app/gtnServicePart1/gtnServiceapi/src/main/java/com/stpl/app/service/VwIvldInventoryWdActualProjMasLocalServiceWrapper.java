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
 * Provides a wrapper for {@link VwIvldInventoryWdActualProjMasLocalService}.
 *
 * @author
 * @see VwIvldInventoryWdActualProjMasLocalService
 * @generated
 */
@ProviderType
public class VwIvldInventoryWdActualProjMasLocalServiceWrapper
	implements VwIvldInventoryWdActualProjMasLocalService,
		ServiceWrapper<VwIvldInventoryWdActualProjMasLocalService> {
	public VwIvldInventoryWdActualProjMasLocalServiceWrapper(
		VwIvldInventoryWdActualProjMasLocalService vwIvldInventoryWdActualProjMasLocalService) {
		_vwIvldInventoryWdActualProjMasLocalService = vwIvldInventoryWdActualProjMasLocalService;
	}

	/**
	* Adds the vw ivld inventory wd actual proj mas to the database. Also notifies the appropriate model listeners.
	*
	* @param vwIvldInventoryWdActualProjMas the vw ivld inventory wd actual proj mas
	* @return the vw ivld inventory wd actual proj mas that was added
	*/
	@Override
	public com.stpl.app.model.VwIvldInventoryWdActualProjMas addVwIvldInventoryWdActualProjMas(
		com.stpl.app.model.VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
		return _vwIvldInventoryWdActualProjMasLocalService.addVwIvldInventoryWdActualProjMas(vwIvldInventoryWdActualProjMas);
	}

	/**
	* Creates a new vw ivld inventory wd actual proj mas with the primary key. Does not add the vw ivld inventory wd actual proj mas to the database.
	*
	* @param ivldInventoryWdActualProjMasSid the primary key for the new vw ivld inventory wd actual proj mas
	* @return the new vw ivld inventory wd actual proj mas
	*/
	@Override
	public com.stpl.app.model.VwIvldInventoryWdActualProjMas createVwIvldInventoryWdActualProjMas(
		int ivldInventoryWdActualProjMasSid) {
		return _vwIvldInventoryWdActualProjMasLocalService.createVwIvldInventoryWdActualProjMas(ivldInventoryWdActualProjMasSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwIvldInventoryWdActualProjMasLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the vw ivld inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
	* @return the vw ivld inventory wd actual proj mas that was removed
	* @throws PortalException if a vw ivld inventory wd actual proj mas with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.VwIvldInventoryWdActualProjMas deleteVwIvldInventoryWdActualProjMas(
		int ivldInventoryWdActualProjMasSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwIvldInventoryWdActualProjMasLocalService.deleteVwIvldInventoryWdActualProjMas(ivldInventoryWdActualProjMasSid);
	}

	/**
	* Deletes the vw ivld inventory wd actual proj mas from the database. Also notifies the appropriate model listeners.
	*
	* @param vwIvldInventoryWdActualProjMas the vw ivld inventory wd actual proj mas
	* @return the vw ivld inventory wd actual proj mas that was removed
	*/
	@Override
	public com.stpl.app.model.VwIvldInventoryWdActualProjMas deleteVwIvldInventoryWdActualProjMas(
		com.stpl.app.model.VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
		return _vwIvldInventoryWdActualProjMasLocalService.deleteVwIvldInventoryWdActualProjMas(vwIvldInventoryWdActualProjMas);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _vwIvldInventoryWdActualProjMasLocalService.dynamicQuery();
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
		return _vwIvldInventoryWdActualProjMasLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwIvldInventoryWdActualProjMasLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _vwIvldInventoryWdActualProjMasLocalService.dynamicQuery(dynamicQuery,
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
		return _vwIvldInventoryWdActualProjMasLocalService.dynamicQueryCount(dynamicQuery);
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
		return _vwIvldInventoryWdActualProjMasLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.VwIvldInventoryWdActualProjMas fetchVwIvldInventoryWdActualProjMas(
		int ivldInventoryWdActualProjMasSid) {
		return _vwIvldInventoryWdActualProjMasLocalService.fetchVwIvldInventoryWdActualProjMas(ivldInventoryWdActualProjMasSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _vwIvldInventoryWdActualProjMasLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _vwIvldInventoryWdActualProjMasLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _vwIvldInventoryWdActualProjMasLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwIvldInventoryWdActualProjMasLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the vw ivld inventory wd actual proj mas with the primary key.
	*
	* @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
	* @return the vw ivld inventory wd actual proj mas
	* @throws PortalException if a vw ivld inventory wd actual proj mas with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.VwIvldInventoryWdActualProjMas getVwIvldInventoryWdActualProjMas(
		int ivldInventoryWdActualProjMasSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _vwIvldInventoryWdActualProjMasLocalService.getVwIvldInventoryWdActualProjMas(ivldInventoryWdActualProjMasSid);
	}

	/**
	* Returns a range of all the vw ivld inventory wd actual proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of vw ivld inventory wd actual proj mases
	* @param end the upper bound of the range of vw ivld inventory wd actual proj mases (not inclusive)
	* @return the range of vw ivld inventory wd actual proj mases
	*/
	@Override
	public java.util.List<com.stpl.app.model.VwIvldInventoryWdActualProjMas> getVwIvldInventoryWdActualProjMases(
		int start, int end) {
		return _vwIvldInventoryWdActualProjMasLocalService.getVwIvldInventoryWdActualProjMases(start,
			end);
	}

	/**
	* Returns the number of vw ivld inventory wd actual proj mases.
	*
	* @return the number of vw ivld inventory wd actual proj mases
	*/
	@Override
	public int getVwIvldInventoryWdActualProjMasesCount() {
		return _vwIvldInventoryWdActualProjMasLocalService.getVwIvldInventoryWdActualProjMasesCount();
	}

	/**
	* Updates the vw ivld inventory wd actual proj mas in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param vwIvldInventoryWdActualProjMas the vw ivld inventory wd actual proj mas
	* @return the vw ivld inventory wd actual proj mas that was updated
	*/
	@Override
	public com.stpl.app.model.VwIvldInventoryWdActualProjMas updateVwIvldInventoryWdActualProjMas(
		com.stpl.app.model.VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
		return _vwIvldInventoryWdActualProjMasLocalService.updateVwIvldInventoryWdActualProjMas(vwIvldInventoryWdActualProjMas);
	}

	@Override
	public VwIvldInventoryWdActualProjMasLocalService getWrappedService() {
		return _vwIvldInventoryWdActualProjMasLocalService;
	}

	@Override
	public void setWrappedService(
		VwIvldInventoryWdActualProjMasLocalService vwIvldInventoryWdActualProjMasLocalService) {
		_vwIvldInventoryWdActualProjMasLocalService = vwIvldInventoryWdActualProjMasLocalService;
	}

	private VwIvldInventoryWdActualProjMasLocalService _vwIvldInventoryWdActualProjMasLocalService;
}