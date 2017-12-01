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
 * Provides a wrapper for {@link CcpMapLocalService}.
 *
 * @author
 * @see CcpMapLocalService
 * @generated
 */
@ProviderType
public class CcpMapLocalServiceWrapper implements CcpMapLocalService,
	ServiceWrapper<CcpMapLocalService> {
	public CcpMapLocalServiceWrapper(CcpMapLocalService ccpMapLocalService) {
		_ccpMapLocalService = ccpMapLocalService;
	}

	/**
	* Adds the ccp map to the database. Also notifies the appropriate model listeners.
	*
	* @param ccpMap the ccp map
	* @return the ccp map that was added
	*/
	@Override
	public com.stpl.app.model.CcpMap addCcpMap(com.stpl.app.model.CcpMap ccpMap) {
		return _ccpMapLocalService.addCcpMap(ccpMap);
	}

	/**
	* Creates a new ccp map with the primary key. Does not add the ccp map to the database.
	*
	* @param ccpMapSid the primary key for the new ccp map
	* @return the new ccp map
	*/
	@Override
	public com.stpl.app.model.CcpMap createCcpMap(int ccpMapSid) {
		return _ccpMapLocalService.createCcpMap(ccpMapSid);
	}

	/**
	* Deletes the ccp map from the database. Also notifies the appropriate model listeners.
	*
	* @param ccpMap the ccp map
	* @return the ccp map that was removed
	*/
	@Override
	public com.stpl.app.model.CcpMap deleteCcpMap(
		com.stpl.app.model.CcpMap ccpMap) {
		return _ccpMapLocalService.deleteCcpMap(ccpMap);
	}

	/**
	* Deletes the ccp map with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ccpMapSid the primary key of the ccp map
	* @return the ccp map that was removed
	* @throws PortalException if a ccp map with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CcpMap deleteCcpMap(int ccpMapSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ccpMapLocalService.deleteCcpMap(ccpMapSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ccpMapLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ccpMapLocalService.dynamicQuery();
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
		return _ccpMapLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ccpMapLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ccpMapLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _ccpMapLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ccpMapLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.stpl.app.model.CcpMap fetchCcpMap(int ccpMapSid) {
		return _ccpMapLocalService.fetchCcpMap(ccpMapSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ccpMapLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the ccp map with the primary key.
	*
	* @param ccpMapSid the primary key of the ccp map
	* @return the ccp map
	* @throws PortalException if a ccp map with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.CcpMap getCcpMap(int ccpMapSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ccpMapLocalService.getCcpMap(ccpMapSid);
	}

	/**
	* Returns a range of all the ccp maps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ccp maps
	* @param end the upper bound of the range of ccp maps (not inclusive)
	* @return the range of ccp maps
	*/
	@Override
	public java.util.List<com.stpl.app.model.CcpMap> getCcpMaps(int start,
		int end) {
		return _ccpMapLocalService.getCcpMaps(start, end);
	}

	/**
	* Returns the number of ccp maps.
	*
	* @return the number of ccp maps
	*/
	@Override
	public int getCcpMapsCount() {
		return _ccpMapLocalService.getCcpMapsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ccpMapLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ccpMapLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ccpMapLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the ccp map in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ccpMap the ccp map
	* @return the ccp map that was updated
	*/
	@Override
	public com.stpl.app.model.CcpMap updateCcpMap(
		com.stpl.app.model.CcpMap ccpMap) {
		return _ccpMapLocalService.updateCcpMap(ccpMap);
	}

	@Override
	public CcpMapLocalService getWrappedService() {
		return _ccpMapLocalService;
	}

	@Override
	public void setWrappedService(CcpMapLocalService ccpMapLocalService) {
		_ccpMapLocalService = ccpMapLocalService;
	}

	private CcpMapLocalService _ccpMapLocalService;
}