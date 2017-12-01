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
 * Provides a wrapper for {@link IvldAverageShelfLifeLocalService}.
 *
 * @author
 * @see IvldAverageShelfLifeLocalService
 * @generated
 */
@ProviderType
public class IvldAverageShelfLifeLocalServiceWrapper
	implements IvldAverageShelfLifeLocalService,
		ServiceWrapper<IvldAverageShelfLifeLocalService> {
	public IvldAverageShelfLifeLocalServiceWrapper(
		IvldAverageShelfLifeLocalService ivldAverageShelfLifeLocalService) {
		_ivldAverageShelfLifeLocalService = ivldAverageShelfLifeLocalService;
	}

	/**
	* Adds the ivld average shelf life to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldAverageShelfLife the ivld average shelf life
	* @return the ivld average shelf life that was added
	*/
	@Override
	public com.stpl.app.model.IvldAverageShelfLife addIvldAverageShelfLife(
		com.stpl.app.model.IvldAverageShelfLife ivldAverageShelfLife) {
		return _ivldAverageShelfLifeLocalService.addIvldAverageShelfLife(ivldAverageShelfLife);
	}

	/**
	* Creates a new ivld average shelf life with the primary key. Does not add the ivld average shelf life to the database.
	*
	* @param ivldAverageShelfLifeSid the primary key for the new ivld average shelf life
	* @return the new ivld average shelf life
	*/
	@Override
	public com.stpl.app.model.IvldAverageShelfLife createIvldAverageShelfLife(
		int ivldAverageShelfLifeSid) {
		return _ivldAverageShelfLifeLocalService.createIvldAverageShelfLife(ivldAverageShelfLifeSid);
	}

	/**
	* Deletes the ivld average shelf life with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
	* @return the ivld average shelf life that was removed
	* @throws PortalException if a ivld average shelf life with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldAverageShelfLife deleteIvldAverageShelfLife(
		int ivldAverageShelfLifeSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldAverageShelfLifeLocalService.deleteIvldAverageShelfLife(ivldAverageShelfLifeSid);
	}

	/**
	* Deletes the ivld average shelf life from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldAverageShelfLife the ivld average shelf life
	* @return the ivld average shelf life that was removed
	*/
	@Override
	public com.stpl.app.model.IvldAverageShelfLife deleteIvldAverageShelfLife(
		com.stpl.app.model.IvldAverageShelfLife ivldAverageShelfLife) {
		return _ivldAverageShelfLifeLocalService.deleteIvldAverageShelfLife(ivldAverageShelfLife);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldAverageShelfLifeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldAverageShelfLifeLocalService.dynamicQuery();
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
		return _ivldAverageShelfLifeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldAverageShelfLifeLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldAverageShelfLifeLocalService.dynamicQuery(dynamicQuery,
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
		return _ivldAverageShelfLifeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldAverageShelfLifeLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.IvldAverageShelfLife fetchIvldAverageShelfLife(
		int ivldAverageShelfLifeSid) {
		return _ivldAverageShelfLifeLocalService.fetchIvldAverageShelfLife(ivldAverageShelfLifeSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldAverageShelfLifeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldAverageShelfLifeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld average shelf life with the primary key.
	*
	* @param ivldAverageShelfLifeSid the primary key of the ivld average shelf life
	* @return the ivld average shelf life
	* @throws PortalException if a ivld average shelf life with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.IvldAverageShelfLife getIvldAverageShelfLife(
		int ivldAverageShelfLifeSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldAverageShelfLifeLocalService.getIvldAverageShelfLife(ivldAverageShelfLifeSid);
	}

	/**
	* Returns a range of all the ivld average shelf lifes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldAverageShelfLifeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld average shelf lifes
	* @param end the upper bound of the range of ivld average shelf lifes (not inclusive)
	* @return the range of ivld average shelf lifes
	*/
	@Override
	public java.util.List<com.stpl.app.model.IvldAverageShelfLife> getIvldAverageShelfLifes(
		int start, int end) {
		return _ivldAverageShelfLifeLocalService.getIvldAverageShelfLifes(start,
			end);
	}

	/**
	* Returns the number of ivld average shelf lifes.
	*
	* @return the number of ivld average shelf lifes
	*/
	@Override
	public int getIvldAverageShelfLifesCount() {
		return _ivldAverageShelfLifeLocalService.getIvldAverageShelfLifesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldAverageShelfLifeLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldAverageShelfLifeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld average shelf life in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldAverageShelfLife the ivld average shelf life
	* @return the ivld average shelf life that was updated
	*/
	@Override
	public com.stpl.app.model.IvldAverageShelfLife updateIvldAverageShelfLife(
		com.stpl.app.model.IvldAverageShelfLife ivldAverageShelfLife) {
		return _ivldAverageShelfLifeLocalService.updateIvldAverageShelfLife(ivldAverageShelfLife);
	}

	@Override
	public IvldAverageShelfLifeLocalService getWrappedService() {
		return _ivldAverageShelfLifeLocalService;
	}

	@Override
	public void setWrappedService(
		IvldAverageShelfLifeLocalService ivldAverageShelfLifeLocalService) {
		_ivldAverageShelfLifeLocalService = ivldAverageShelfLifeLocalService;
	}

	private IvldAverageShelfLifeLocalService _ivldAverageShelfLifeLocalService;
}