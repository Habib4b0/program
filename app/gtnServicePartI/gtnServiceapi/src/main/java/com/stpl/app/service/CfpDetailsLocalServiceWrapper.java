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
 * Provides a wrapper for {@link CfpDetailsLocalService}.
 *
 * @author
 * @see CfpDetailsLocalService
 * @generated
 */
@ProviderType
public class CfpDetailsLocalServiceWrapper implements CfpDetailsLocalService,
	ServiceWrapper<CfpDetailsLocalService> {
	public CfpDetailsLocalServiceWrapper(
		CfpDetailsLocalService cfpDetailsLocalService) {
		_cfpDetailsLocalService = cfpDetailsLocalService;
	}

	/**
	* Adds the cfp details to the database. Also notifies the appropriate model listeners.
	*
	* @param cfpDetails the cfp details
	* @return the cfp details that was added
	*/
	@Override
	public com.stpl.app.model.CfpDetails addCfpDetails(
		com.stpl.app.model.CfpDetails cfpDetails) {
		return _cfpDetailsLocalService.addCfpDetails(cfpDetails);
	}

	/**
	* Creates a new cfp details with the primary key. Does not add the cfp details to the database.
	*
	* @param cfpDetailsSid the primary key for the new cfp details
	* @return the new cfp details
	*/
	@Override
	public com.stpl.app.model.CfpDetails createCfpDetails(int cfpDetailsSid) {
		return _cfpDetailsLocalService.createCfpDetails(cfpDetailsSid);
	}

	/**
	* Deletes the cfp details from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpDetails the cfp details
	* @return the cfp details that was removed
	*/
	@Override
	public com.stpl.app.model.CfpDetails deleteCfpDetails(
		com.stpl.app.model.CfpDetails cfpDetails) {
		return _cfpDetailsLocalService.deleteCfpDetails(cfpDetails);
	}

	/**
	* Deletes the cfp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpDetailsSid the primary key of the cfp details
	* @return the cfp details that was removed
	* @throws PortalException if a cfp details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CfpDetails deleteCfpDetails(int cfpDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cfpDetailsLocalService.deleteCfpDetails(cfpDetailsSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cfpDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cfpDetailsLocalService.dynamicQuery();
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
		return _cfpDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cfpDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _cfpDetailsLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _cfpDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _cfpDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.CfpDetails fetchCfpDetails(int cfpDetailsSid) {
		return _cfpDetailsLocalService.fetchCfpDetails(cfpDetailsSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _cfpDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the cfp details with the primary key.
	*
	* @param cfpDetailsSid the primary key of the cfp details
	* @return the cfp details
	* @throws PortalException if a cfp details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CfpDetails getCfpDetails(int cfpDetailsSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cfpDetailsLocalService.getCfpDetails(cfpDetailsSid);
	}

	/**
	* Returns a range of all the cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @return the range of cfp detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.CfpDetails> getCfpDetailses(
		int start, int end) {
		return _cfpDetailsLocalService.getCfpDetailses(start, end);
	}

	/**
	* Returns the number of cfp detailses.
	*
	* @return the number of cfp detailses
	*/
	@Override
	public int getCfpDetailsesCount() {
		return _cfpDetailsLocalService.getCfpDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _cfpDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _cfpDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _cfpDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cfp details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param cfpDetails the cfp details
	* @return the cfp details that was updated
	*/
	@Override
	public com.stpl.app.model.CfpDetails updateCfpDetails(
		com.stpl.app.model.CfpDetails cfpDetails) {
		return _cfpDetailsLocalService.updateCfpDetails(cfpDetails);
	}

	@Override
	public CfpDetailsLocalService getWrappedService() {
		return _cfpDetailsLocalService;
	}

	@Override
	public void setWrappedService(CfpDetailsLocalService cfpDetailsLocalService) {
		_cfpDetailsLocalService = cfpDetailsLocalService;
	}

	private CfpDetailsLocalService _cfpDetailsLocalService;
}