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
 * Provides a wrapper for {@link AhTempDetailsLocalService}.
 *
 * @author
 * @see AhTempDetailsLocalService
 * @generated
 */
@ProviderType
public class AhTempDetailsLocalServiceWrapper
	implements AhTempDetailsLocalService,
		ServiceWrapper<AhTempDetailsLocalService> {
	public AhTempDetailsLocalServiceWrapper(
		AhTempDetailsLocalService ahTempDetailsLocalService) {
		_ahTempDetailsLocalService = ahTempDetailsLocalService;
	}

	/**
	* Adds the ah temp details to the database. Also notifies the appropriate model listeners.
	*
	* @param ahTempDetails the ah temp details
	* @return the ah temp details that was added
	*/
	@Override
	public com.stpl.app.parttwo.model.AhTempDetails addAhTempDetails(
		com.stpl.app.parttwo.model.AhTempDetails ahTempDetails) {
		return _ahTempDetailsLocalService.addAhTempDetails(ahTempDetails);
	}

	/**
	* Creates a new ah temp details with the primary key. Does not add the ah temp details to the database.
	*
	* @param componentMasterSid the primary key for the new ah temp details
	* @return the new ah temp details
	*/
	@Override
	public com.stpl.app.parttwo.model.AhTempDetails createAhTempDetails(
		int componentMasterSid) {
		return _ahTempDetailsLocalService.createAhTempDetails(componentMasterSid);
	}

	/**
	* Deletes the ah temp details from the database. Also notifies the appropriate model listeners.
	*
	* @param ahTempDetails the ah temp details
	* @return the ah temp details that was removed
	*/
	@Override
	public com.stpl.app.parttwo.model.AhTempDetails deleteAhTempDetails(
		com.stpl.app.parttwo.model.AhTempDetails ahTempDetails) {
		return _ahTempDetailsLocalService.deleteAhTempDetails(ahTempDetails);
	}

	/**
	* Deletes the ah temp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param componentMasterSid the primary key of the ah temp details
	* @return the ah temp details that was removed
	* @throws PortalException if a ah temp details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.AhTempDetails deleteAhTempDetails(
		int componentMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ahTempDetailsLocalService.deleteAhTempDetails(componentMasterSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ahTempDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ahTempDetailsLocalService.dynamicQuery();
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
		return _ahTempDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ahTempDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ahTempDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
		return _ahTempDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ahTempDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.parttwo.model.AhTempDetails fetchAhTempDetails(
		int componentMasterSid) {
		return _ahTempDetailsLocalService.fetchAhTempDetails(componentMasterSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ahTempDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the ah temp details with the primary key.
	*
	* @param componentMasterSid the primary key of the ah temp details
	* @return the ah temp details
	* @throws PortalException if a ah temp details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.parttwo.model.AhTempDetails getAhTempDetails(
		int componentMasterSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ahTempDetailsLocalService.getAhTempDetails(componentMasterSid);
	}

	/**
	* Returns a range of all the ah temp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AhTempDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ah temp detailses
	* @param end the upper bound of the range of ah temp detailses (not inclusive)
	* @return the range of ah temp detailses
	*/
	@Override
	public java.util.List<com.stpl.app.parttwo.model.AhTempDetails> getAhTempDetailses(
		int start, int end) {
		return _ahTempDetailsLocalService.getAhTempDetailses(start, end);
	}

	/**
	* Returns the number of ah temp detailses.
	*
	* @return the number of ah temp detailses
	*/
	@Override
	public int getAhTempDetailsesCount() {
		return _ahTempDetailsLocalService.getAhTempDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ahTempDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ahTempDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ahTempDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ah temp details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ahTempDetails the ah temp details
	* @return the ah temp details that was updated
	*/
	@Override
	public com.stpl.app.parttwo.model.AhTempDetails updateAhTempDetails(
		com.stpl.app.parttwo.model.AhTempDetails ahTempDetails) {
		return _ahTempDetailsLocalService.updateAhTempDetails(ahTempDetails);
	}

	@Override
	public AhTempDetailsLocalService getWrappedService() {
		return _ahTempDetailsLocalService;
	}

	@Override
	public void setWrappedService(
		AhTempDetailsLocalService ahTempDetailsLocalService) {
		_ahTempDetailsLocalService = ahTempDetailsLocalService;
	}

	private AhTempDetailsLocalService _ahTempDetailsLocalService;
}