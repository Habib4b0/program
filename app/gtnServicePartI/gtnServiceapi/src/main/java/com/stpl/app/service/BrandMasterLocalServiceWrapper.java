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
 * Provides a wrapper for {@link BrandMasterLocalService}.
 *
 * @author
 * @see BrandMasterLocalService
 * @generated
 */
@ProviderType
public class BrandMasterLocalServiceWrapper implements BrandMasterLocalService,
	ServiceWrapper<BrandMasterLocalService> {
	public BrandMasterLocalServiceWrapper(
		BrandMasterLocalService brandMasterLocalService) {
		_brandMasterLocalService = brandMasterLocalService;
	}

	/**
	* Adds the brand master to the database. Also notifies the appropriate model listeners.
	*
	* @param brandMaster the brand master
	* @return the brand master that was added
	*/
	@Override
	public com.stpl.app.model.BrandMaster addBrandMaster(
		com.stpl.app.model.BrandMaster brandMaster) {
		return _brandMasterLocalService.addBrandMaster(brandMaster);
	}

	/**
	* Creates a new brand master with the primary key. Does not add the brand master to the database.
	*
	* @param brandMasterSid the primary key for the new brand master
	* @return the new brand master
	*/
	@Override
	public com.stpl.app.model.BrandMaster createBrandMaster(int brandMasterSid) {
		return _brandMasterLocalService.createBrandMaster(brandMasterSid);
	}

	/**
	* Deletes the brand master from the database. Also notifies the appropriate model listeners.
	*
	* @param brandMaster the brand master
	* @return the brand master that was removed
	*/
	@Override
	public com.stpl.app.model.BrandMaster deleteBrandMaster(
		com.stpl.app.model.BrandMaster brandMaster) {
		return _brandMasterLocalService.deleteBrandMaster(brandMaster);
	}

	/**
	* Deletes the brand master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param brandMasterSid the primary key of the brand master
	* @return the brand master that was removed
	* @throws PortalException if a brand master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.BrandMaster deleteBrandMaster(int brandMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _brandMasterLocalService.deleteBrandMaster(brandMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _brandMasterLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _brandMasterLocalService.dynamicQuery();
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
		return _brandMasterLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _brandMasterLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _brandMasterLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _brandMasterLocalService.dynamicQueryCount(dynamicQuery);
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
		return _brandMasterLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.BrandMaster fetchBrandMaster(int brandMasterSid) {
		return _brandMasterLocalService.fetchBrandMaster(brandMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _brandMasterLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the brand master with the primary key.
	*
	* @param brandMasterSid the primary key of the brand master
	* @return the brand master
	* @throws PortalException if a brand master with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.BrandMaster getBrandMaster(int brandMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _brandMasterLocalService.getBrandMaster(brandMasterSid);
	}

	/**
	* Returns a range of all the brand masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of brand masters
	* @param end the upper bound of the range of brand masters (not inclusive)
	* @return the range of brand masters
	*/
	@Override
	public java.util.List<com.stpl.app.model.BrandMaster> getBrandMasters(
		int start, int end) {
		return _brandMasterLocalService.getBrandMasters(start, end);
	}

	/**
	* Returns the number of brand masters.
	*
	* @return the number of brand masters
	*/
	@Override
	public int getBrandMastersCount() {
		return _brandMasterLocalService.getBrandMastersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _brandMasterLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _brandMasterLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _brandMasterLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the brand master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param brandMaster the brand master
	* @return the brand master that was updated
	*/
	@Override
	public com.stpl.app.model.BrandMaster updateBrandMaster(
		com.stpl.app.model.BrandMaster brandMaster) {
		return _brandMasterLocalService.updateBrandMaster(brandMaster);
	}

	@Override
	public BrandMasterLocalService getWrappedService() {
		return _brandMasterLocalService;
	}

	@Override
	public void setWrappedService(
		BrandMasterLocalService brandMasterLocalService) {
		_brandMasterLocalService = brandMasterLocalService;
	}

	private BrandMasterLocalService _brandMasterLocalService;
}