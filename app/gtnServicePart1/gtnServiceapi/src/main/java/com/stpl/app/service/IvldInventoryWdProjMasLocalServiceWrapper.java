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
 * Provides a wrapper for {@link IvldInventoryWdProjMasLocalService}.
 *
 * @author
 * @see IvldInventoryWdProjMasLocalService
 * @generated
 */
@ProviderType
public class IvldInventoryWdProjMasLocalServiceWrapper
	implements IvldInventoryWdProjMasLocalService,
		ServiceWrapper<IvldInventoryWdProjMasLocalService> {
	public IvldInventoryWdProjMasLocalServiceWrapper(
		IvldInventoryWdProjMasLocalService ivldInventoryWdProjMasLocalService) {
		_ivldInventoryWdProjMasLocalService = ivldInventoryWdProjMasLocalService;
	}

	/**
	* Adds the ivld inventory wd proj mas to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldInventoryWdProjMas the ivld inventory wd proj mas
	* @return the ivld inventory wd proj mas that was added
	*/
	@Override
	public com.stpl.app.model.IvldInventoryWdProjMas addIvldInventoryWdProjMas(
		com.stpl.app.model.IvldInventoryWdProjMas ivldInventoryWdProjMas) {
		return _ivldInventoryWdProjMasLocalService.addIvldInventoryWdProjMas(ivldInventoryWdProjMas);
	}

	/**
	* Creates a new ivld inventory wd proj mas with the primary key. Does not add the ivld inventory wd proj mas to the database.
	*
	* @param ivldInventoryWdProjMasSid the primary key for the new ivld inventory wd proj mas
	* @return the new ivld inventory wd proj mas
	*/
	@Override
	public com.stpl.app.model.IvldInventoryWdProjMas createIvldInventoryWdProjMas(
		int ivldInventoryWdProjMasSid) {
		return _ivldInventoryWdProjMasLocalService.createIvldInventoryWdProjMas(ivldInventoryWdProjMasSid);
	}

	/**
	* Deletes the ivld inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
	* @return the ivld inventory wd proj mas that was removed
	* @throws PortalException if a ivld inventory wd proj mas with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldInventoryWdProjMas deleteIvldInventoryWdProjMas(
		int ivldInventoryWdProjMasSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldInventoryWdProjMasLocalService.deleteIvldInventoryWdProjMas(ivldInventoryWdProjMasSid);
	}

	/**
	* Deletes the ivld inventory wd proj mas from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldInventoryWdProjMas the ivld inventory wd proj mas
	* @return the ivld inventory wd proj mas that was removed
	*/
	@Override
	public com.stpl.app.model.IvldInventoryWdProjMas deleteIvldInventoryWdProjMas(
		com.stpl.app.model.IvldInventoryWdProjMas ivldInventoryWdProjMas) {
		return _ivldInventoryWdProjMasLocalService.deleteIvldInventoryWdProjMas(ivldInventoryWdProjMas);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldInventoryWdProjMasLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldInventoryWdProjMasLocalService.dynamicQuery();
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
		return _ivldInventoryWdProjMasLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldInventoryWdProjMasLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldInventoryWdProjMasLocalService.dynamicQuery(dynamicQuery,
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
		return _ivldInventoryWdProjMasLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldInventoryWdProjMasLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.IvldInventoryWdProjMas fetchIvldInventoryWdProjMas(
		int ivldInventoryWdProjMasSid) {
		return _ivldInventoryWdProjMasLocalService.fetchIvldInventoryWdProjMas(ivldInventoryWdProjMasSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldInventoryWdProjMasLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldInventoryWdProjMasLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld inventory wd proj mas with the primary key.
	*
	* @param ivldInventoryWdProjMasSid the primary key of the ivld inventory wd proj mas
	* @return the ivld inventory wd proj mas
	* @throws PortalException if a ivld inventory wd proj mas with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldInventoryWdProjMas getIvldInventoryWdProjMas(
		int ivldInventoryWdProjMasSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldInventoryWdProjMasLocalService.getIvldInventoryWdProjMas(ivldInventoryWdProjMasSid);
	}

	/**
	* Returns a range of all the ivld inventory wd proj mases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldInventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld inventory wd proj mases
	* @param end the upper bound of the range of ivld inventory wd proj mases (not inclusive)
	* @return the range of ivld inventory wd proj mases
	*/
	@Override
	public java.util.List<com.stpl.app.model.IvldInventoryWdProjMas> getIvldInventoryWdProjMases(
		int start, int end) {
		return _ivldInventoryWdProjMasLocalService.getIvldInventoryWdProjMases(start,
			end);
	}

	/**
	* Returns the number of ivld inventory wd proj mases.
	*
	* @return the number of ivld inventory wd proj mases
	*/
	@Override
	public int getIvldInventoryWdProjMasesCount() {
		return _ivldInventoryWdProjMasLocalService.getIvldInventoryWdProjMasesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldInventoryWdProjMasLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldInventoryWdProjMasLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld inventory wd proj mas in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldInventoryWdProjMas the ivld inventory wd proj mas
	* @return the ivld inventory wd proj mas that was updated
	*/
	@Override
	public com.stpl.app.model.IvldInventoryWdProjMas updateIvldInventoryWdProjMas(
		com.stpl.app.model.IvldInventoryWdProjMas ivldInventoryWdProjMas) {
		return _ivldInventoryWdProjMasLocalService.updateIvldInventoryWdProjMas(ivldInventoryWdProjMas);
	}

	@Override
	public IvldInventoryWdProjMasLocalService getWrappedService() {
		return _ivldInventoryWdProjMasLocalService;
	}

	@Override
	public void setWrappedService(
		IvldInventoryWdProjMasLocalService ivldInventoryWdProjMasLocalService) {
		_ivldInventoryWdProjMasLocalService = ivldInventoryWdProjMasLocalService;
	}

	private IvldInventoryWdProjMasLocalService _ivldInventoryWdProjMasLocalService;
}