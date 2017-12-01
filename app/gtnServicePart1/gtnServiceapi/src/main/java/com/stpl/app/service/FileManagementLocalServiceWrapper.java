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
 * Provides a wrapper for {@link FileManagementLocalService}.
 *
 * @author
 * @see FileManagementLocalService
 * @generated
 */
@ProviderType
public class FileManagementLocalServiceWrapper
	implements FileManagementLocalService,
		ServiceWrapper<FileManagementLocalService> {
	public FileManagementLocalServiceWrapper(
		FileManagementLocalService fileManagementLocalService) {
		_fileManagementLocalService = fileManagementLocalService;
	}

	/**
	* Adds the file management to the database. Also notifies the appropriate model listeners.
	*
	* @param fileManagement the file management
	* @return the file management that was added
	*/
	@Override
	public com.stpl.app.model.FileManagement addFileManagement(
		com.stpl.app.model.FileManagement fileManagement) {
		return _fileManagementLocalService.addFileManagement(fileManagement);
	}

	/**
	* Creates a new file management with the primary key. Does not add the file management to the database.
	*
	* @param fileManagementSid the primary key for the new file management
	* @return the new file management
	*/
	@Override
	public com.stpl.app.model.FileManagement createFileManagement(
		int fileManagementSid) {
		return _fileManagementLocalService.createFileManagement(fileManagementSid);
	}

	/**
	* Deletes the file management from the database. Also notifies the appropriate model listeners.
	*
	* @param fileManagement the file management
	* @return the file management that was removed
	*/
	@Override
	public com.stpl.app.model.FileManagement deleteFileManagement(
		com.stpl.app.model.FileManagement fileManagement) {
		return _fileManagementLocalService.deleteFileManagement(fileManagement);
	}

	/**
	* Deletes the file management with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fileManagementSid the primary key of the file management
	* @return the file management that was removed
	* @throws PortalException if a file management with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.FileManagement deleteFileManagement(
		int fileManagementSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fileManagementLocalService.deleteFileManagement(fileManagementSid);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fileManagementLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fileManagementLocalService.dynamicQuery();
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
		return _fileManagementLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fileManagementLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _fileManagementLocalService.dynamicQuery(dynamicQuery, start,
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
		return _fileManagementLocalService.dynamicQueryCount(dynamicQuery);
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
		return _fileManagementLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.FileManagement fetchFileManagement(
		int fileManagementSid) {
		return _fileManagementLocalService.fetchFileManagement(fileManagementSid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _fileManagementLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the file management with the primary key.
	*
	* @param fileManagementSid the primary key of the file management
	* @return the file management
	* @throws PortalException if a file management with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.FileManagement getFileManagement(
		int fileManagementSid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fileManagementLocalService.getFileManagement(fileManagementSid);
	}

	/**
	* Returns a range of all the file managements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FileManagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of file managements
	* @param end the upper bound of the range of file managements (not inclusive)
	* @return the range of file managements
	*/
	@Override
	public java.util.List<com.stpl.app.model.FileManagement> getFileManagements(
		int start, int end) {
		return _fileManagementLocalService.getFileManagements(start, end);
	}

	/**
	* Returns the number of file managements.
	*
	* @return the number of file managements
	*/
	@Override
	public int getFileManagementsCount() {
		return _fileManagementLocalService.getFileManagementsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _fileManagementLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _fileManagementLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _fileManagementLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the file management in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param fileManagement the file management
	* @return the file management that was updated
	*/
	@Override
	public com.stpl.app.model.FileManagement updateFileManagement(
		com.stpl.app.model.FileManagement fileManagement) {
		return _fileManagementLocalService.updateFileManagement(fileManagement);
	}

	@Override
	public FileManagementLocalService getWrappedService() {
		return _fileManagementLocalService;
	}

	@Override
	public void setWrappedService(
		FileManagementLocalService fileManagementLocalService) {
		_fileManagementLocalService = fileManagementLocalService;
	}

	private FileManagementLocalService _fileManagementLocalService;
}