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
 * Provides a wrapper for {@link IvldCompanyTradeClassLocalService}.
 *
 * @author
 * @see IvldCompanyTradeClassLocalService
 * @generated
 */
@ProviderType
public class IvldCompanyTradeClassLocalServiceWrapper
	implements IvldCompanyTradeClassLocalService,
		ServiceWrapper<IvldCompanyTradeClassLocalService> {
	public IvldCompanyTradeClassLocalServiceWrapper(
		IvldCompanyTradeClassLocalService ivldCompanyTradeClassLocalService) {
		_ivldCompanyTradeClassLocalService = ivldCompanyTradeClassLocalService;
	}

	/**
	* Adds the ivld company trade class to the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyTradeClass the ivld company trade class
	* @return the ivld company trade class that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyTradeClass addIvldCompanyTradeClass(
		com.stpl.app.parttwo.model.IvldCompanyTradeClass ivldCompanyTradeClass) {
		return _ivldCompanyTradeClassLocalService.addIvldCompanyTradeClass(ivldCompanyTradeClass);
	}

	/**
	* Creates a new ivld company trade class with the primary key. Does not add the ivld company trade class to the database.
	*
	* @param ivldCompanyTradeClassSid the primary key for the new ivld company trade class
	* @return the new ivld company trade class
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyTradeClass createIvldCompanyTradeClass(
		int ivldCompanyTradeClassSid) {
		return _ivldCompanyTradeClassLocalService.createIvldCompanyTradeClass(ivldCompanyTradeClassSid);
	}

	/**
	* Deletes the ivld company trade class with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
	* @return the ivld company trade class that was removed
	* @throws PortalException if a ivld company trade class with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyTradeClass deleteIvldCompanyTradeClass(
		int ivldCompanyTradeClassSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldCompanyTradeClassLocalService.deleteIvldCompanyTradeClass(ivldCompanyTradeClassSid);
	}

	/**
	* Deletes the ivld company trade class from the database. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyTradeClass the ivld company trade class
	* @return the ivld company trade class that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyTradeClass deleteIvldCompanyTradeClass(
		com.stpl.app.parttwo.model.IvldCompanyTradeClass ivldCompanyTradeClass) {
		return _ivldCompanyTradeClassLocalService.deleteIvldCompanyTradeClass(ivldCompanyTradeClass);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldCompanyTradeClassLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ivldCompanyTradeClassLocalService.dynamicQuery();
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
		return _ivldCompanyTradeClassLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldCompanyTradeClassLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ivldCompanyTradeClassLocalService.dynamicQuery(dynamicQuery,
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
		return _ivldCompanyTradeClassLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ivldCompanyTradeClassLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.IvldCompanyTradeClass fetchIvldCompanyTradeClass(
		int ivldCompanyTradeClassSid) {
		return _ivldCompanyTradeClassLocalService.fetchIvldCompanyTradeClass(ivldCompanyTradeClassSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ivldCompanyTradeClassLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ivldCompanyTradeClassLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the ivld company trade class with the primary key.
	*
	* @param ivldCompanyTradeClassSid the primary key of the ivld company trade class
	* @return the ivld company trade class
	* @throws PortalException if a ivld company trade class with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyTradeClass getIvldCompanyTradeClass(
		int ivldCompanyTradeClassSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldCompanyTradeClassLocalService.getIvldCompanyTradeClass(ivldCompanyTradeClassSid);
	}

	/**
	* Returns a range of all the ivld company trade classes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCompanyTradeClassModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ivld company trade classes
	* @param end the upper bound of the range of ivld company trade classes (not inclusive)
	* @return the range of ivld company trade classes
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.IvldCompanyTradeClass> getIvldCompanyTradeClasses(
		int start, int end) {
		return _ivldCompanyTradeClassLocalService.getIvldCompanyTradeClasses(start,
			end);
	}

	/**
	* Returns the number of ivld company trade classes.
	*
	* @return the number of ivld company trade classes
	*/
	@Override
	public int getIvldCompanyTradeClassesCount() {
		return _ivldCompanyTradeClassLocalService.getIvldCompanyTradeClassesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ivldCompanyTradeClassLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ivldCompanyTradeClassLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ivld company trade class in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ivldCompanyTradeClass the ivld company trade class
	* @return the ivld company trade class that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.IvldCompanyTradeClass updateIvldCompanyTradeClass(
		com.stpl.app.parttwo.model.IvldCompanyTradeClass ivldCompanyTradeClass) {
		return _ivldCompanyTradeClassLocalService.updateIvldCompanyTradeClass(ivldCompanyTradeClass);
	}

	@Override
	public IvldCompanyTradeClassLocalService getWrappedService() {
		return _ivldCompanyTradeClassLocalService;
	}

	@Override
	public void setWrappedService(
		IvldCompanyTradeClassLocalService ivldCompanyTradeClassLocalService) {
		_ivldCompanyTradeClassLocalService = ivldCompanyTradeClassLocalService;
	}

	private IvldCompanyTradeClassLocalService _ivldCompanyTradeClassLocalService;
}