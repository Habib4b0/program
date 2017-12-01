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
 * Provides a wrapper for {@link MasterDataFilesLocalService}.
 *
 * @author
 * @see MasterDataFilesLocalService
 * @generated
 */
@ProviderType
public class MasterDataFilesLocalServiceWrapper
	implements MasterDataFilesLocalService,
		ServiceWrapper<MasterDataFilesLocalService> {
	public MasterDataFilesLocalServiceWrapper(
		MasterDataFilesLocalService masterDataFilesLocalService) {
		_masterDataFilesLocalService = masterDataFilesLocalService;
	}

	/**
	* Adds the master data files to the database. Also notifies the appropriate model listeners.
	*
	* @param masterDataFiles the master data files
	* @return the master data files that was added
	*/
	@Override
	public com.stpl.app.model.MasterDataFiles addMasterDataFiles(
		com.stpl.app.model.MasterDataFiles masterDataFiles) {
		return _masterDataFilesLocalService.addMasterDataFiles(masterDataFiles);
	}

	/**
	* Creates a new master data files with the primary key. Does not add the master data files to the database.
	*
	* @param masterDataFilesSid the primary key for the new master data files
	* @return the new master data files
	*/
	@Override
	public com.stpl.app.model.MasterDataFiles createMasterDataFiles(
		int masterDataFilesSid) {
		return _masterDataFilesLocalService.createMasterDataFiles(masterDataFilesSid);
	}

	/**
	* Deletes the master data files with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param masterDataFilesSid the primary key of the master data files
	* @return the master data files that was removed
	* @throws PortalException if a master data files with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MasterDataFiles deleteMasterDataFiles(
		int masterDataFilesSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _masterDataFilesLocalService.deleteMasterDataFiles(masterDataFilesSid);
	}

	/**
	* Deletes the master data files from the database. Also notifies the appropriate model listeners.
	*
	* @param masterDataFiles the master data files
	* @return the master data files that was removed
	*/
	@Override
	public com.stpl.app.model.MasterDataFiles deleteMasterDataFiles(
		com.stpl.app.model.MasterDataFiles masterDataFiles) {
		return _masterDataFilesLocalService.deleteMasterDataFiles(masterDataFiles);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _masterDataFilesLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _masterDataFilesLocalService.dynamicQuery();
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
		return _masterDataFilesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _masterDataFilesLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _masterDataFilesLocalService.dynamicQuery(dynamicQuery, start,
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
		return _masterDataFilesLocalService.dynamicQueryCount(dynamicQuery);
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
		return _masterDataFilesLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.MasterDataFiles fetchMasterDataFiles(
		int masterDataFilesSid) {
		return _masterDataFilesLocalService.fetchMasterDataFiles(masterDataFilesSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _masterDataFilesLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _masterDataFilesLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the master data files with the primary key.
	*
	* @param masterDataFilesSid the primary key of the master data files
	* @return the master data files
	* @throws PortalException if a master data files with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.MasterDataFiles getMasterDataFiles(
		int masterDataFilesSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _masterDataFilesLocalService.getMasterDataFiles(masterDataFilesSid);
	}

	/**
	* Returns a range of all the master data fileses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataFilesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of master data fileses
	* @param end the upper bound of the range of master data fileses (not inclusive)
	* @return the range of master data fileses
	*/
	@Override
	public java.util.List<com.stpl.app.model.MasterDataFiles> getMasterDataFileses(
		int start, int end) {
		return _masterDataFilesLocalService.getMasterDataFileses(start, end);
	}

	/**
	* Returns the number of master data fileses.
	*
	* @return the number of master data fileses
	*/
	@Override
	public int getMasterDataFilesesCount() {
		return _masterDataFilesLocalService.getMasterDataFilesesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _masterDataFilesLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _masterDataFilesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the master data files in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param masterDataFiles the master data files
	* @return the master data files that was updated
	*/
	@Override
	public com.stpl.app.model.MasterDataFiles updateMasterDataFiles(
		com.stpl.app.model.MasterDataFiles masterDataFiles) {
		return _masterDataFilesLocalService.updateMasterDataFiles(masterDataFiles);
	}

	@Override
	public MasterDataFilesLocalService getWrappedService() {
		return _masterDataFilesLocalService;
	}

	@Override
	public void setWrappedService(
		MasterDataFilesLocalService masterDataFilesLocalService) {
		_masterDataFilesLocalService = masterDataFilesLocalService;
	}

	private MasterDataFilesLocalService _masterDataFilesLocalService;
}