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
 * Provides a wrapper for {@link StAccClosureDetailsLocalService}.
 *
 * @author
 * @see StAccClosureDetailsLocalService
 * @generated
 */
@ProviderType
public class StAccClosureDetailsLocalServiceWrapper
	implements StAccClosureDetailsLocalService,
		ServiceWrapper<StAccClosureDetailsLocalService> {
	public StAccClosureDetailsLocalServiceWrapper(
		StAccClosureDetailsLocalService stAccClosureDetailsLocalService) {
		_stAccClosureDetailsLocalService = stAccClosureDetailsLocalService;
	}

	/**
	* Adds the st acc closure details to the database. Also notifies the appropriate model listeners.
	*
	* @param stAccClosureDetails the st acc closure details
	* @return the st acc closure details that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.StAccClosureDetails addStAccClosureDetails(
		com.stpl.app.parttwo.model.StAccClosureDetails stAccClosureDetails) {
		return _stAccClosureDetailsLocalService.addStAccClosureDetails(stAccClosureDetails);
	}

	/**
	* Creates a new st acc closure details with the primary key. Does not add the st acc closure details to the database.
	*
	* @param accClosureMasterSid the primary key for the new st acc closure details
	* @return the new st acc closure details
	*/
	@Override
	public com.stpl.app.parttwo.model.StAccClosureDetails createStAccClosureDetails(
		int accClosureMasterSid) {
		return _stAccClosureDetailsLocalService.createStAccClosureDetails(accClosureMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stAccClosureDetailsLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the st acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accClosureMasterSid the primary key of the st acc closure details
	* @return the st acc closure details that was removed
	* @throws PortalException if a st acc closure details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.StAccClosureDetails deleteStAccClosureDetails(
		int accClosureMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stAccClosureDetailsLocalService.deleteStAccClosureDetails(accClosureMasterSid);
	}

	/**
	* Deletes the st acc closure details from the database. Also notifies the appropriate model listeners.
	*
	* @param stAccClosureDetails the st acc closure details
	* @return the st acc closure details that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.StAccClosureDetails deleteStAccClosureDetails(
		com.stpl.app.parttwo.model.StAccClosureDetails stAccClosureDetails) {
		return _stAccClosureDetailsLocalService.deleteStAccClosureDetails(stAccClosureDetails);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stAccClosureDetailsLocalService.dynamicQuery();
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
		return _stAccClosureDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stAccClosureDetailsLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stAccClosureDetailsLocalService.dynamicQuery(dynamicQuery,
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
		return _stAccClosureDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stAccClosureDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.StAccClosureDetails fetchStAccClosureDetails(
		int accClosureMasterSid) {
		return _stAccClosureDetailsLocalService.fetchStAccClosureDetails(accClosureMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stAccClosureDetailsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stAccClosureDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stAccClosureDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stAccClosureDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the st acc closure details with the primary key.
	*
	* @param accClosureMasterSid the primary key of the st acc closure details
	* @return the st acc closure details
	* @throws PortalException if a st acc closure details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.StAccClosureDetails getStAccClosureDetails(
		int accClosureMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stAccClosureDetailsLocalService.getStAccClosureDetails(accClosureMasterSid);
	}

	/**
	* Returns a range of all the st acc closure detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of st acc closure detailses
	* @param end the upper bound of the range of st acc closure detailses (not inclusive)
	* @return the range of st acc closure detailses
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.StAccClosureDetails> getStAccClosureDetailses(
		int start, int end) {
		return _stAccClosureDetailsLocalService.getStAccClosureDetailses(start,
			end);
	}

	/**
	* Returns the number of st acc closure detailses.
	*
	* @return the number of st acc closure detailses
	*/
	@Override
	public int getStAccClosureDetailsesCount() {
		return _stAccClosureDetailsLocalService.getStAccClosureDetailsesCount();
	}

	/**
	* Updates the st acc closure details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stAccClosureDetails the st acc closure details
	* @return the st acc closure details that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.StAccClosureDetails updateStAccClosureDetails(
		com.stpl.app.parttwo.model.StAccClosureDetails stAccClosureDetails) {
		return _stAccClosureDetailsLocalService.updateStAccClosureDetails(stAccClosureDetails);
	}

	@Override
	public StAccClosureDetailsLocalService getWrappedService() {
		return _stAccClosureDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		StAccClosureDetailsLocalService stAccClosureDetailsLocalService) {
		_stAccClosureDetailsLocalService = stAccClosureDetailsLocalService;
	}

	private StAccClosureDetailsLocalService _stAccClosureDetailsLocalService;
}