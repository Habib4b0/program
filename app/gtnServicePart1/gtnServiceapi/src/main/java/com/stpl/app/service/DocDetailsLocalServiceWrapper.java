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
 * Provides a wrapper for {@link DocDetailsLocalService}.
 *
 * @author
 * @see DocDetailsLocalService
 * @generated
 */
@ProviderType
public class DocDetailsLocalServiceWrapper implements DocDetailsLocalService,
	ServiceWrapper<DocDetailsLocalService> {
	public DocDetailsLocalServiceWrapper(
		DocDetailsLocalService docDetailsLocalService) {
		_docDetailsLocalService = docDetailsLocalService;
	}

	/**
	* Adds the doc details to the database. Also notifies the appropriate model listeners.
	*
	* @param docDetails the doc details
	* @return the doc details that was added
	*/
	@Override
	public com.stpl.app.model.DocDetails addDocDetails(
		com.stpl.app.model.DocDetails docDetails) {
		return _docDetailsLocalService.addDocDetails(docDetails);
	}

	/**
	* Creates a new doc details with the primary key. Does not add the doc details to the database.
	*
	* @param docDetailsId the primary key for the new doc details
	* @return the new doc details
	*/
	@Override
	public com.stpl.app.model.DocDetails createDocDetails(int docDetailsId) {
		return _docDetailsLocalService.createDocDetails(docDetailsId);
	}

	/**
	* Deletes the doc details from the database. Also notifies the appropriate model listeners.
	*
	* @param docDetails the doc details
	* @return the doc details that was removed
	*/
	@Override
	public com.stpl.app.model.DocDetails deleteDocDetails(
		com.stpl.app.model.DocDetails docDetails) {
		return _docDetailsLocalService.deleteDocDetails(docDetails);
	}

	/**
	* Deletes the doc details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param docDetailsId the primary key of the doc details
	* @return the doc details that was removed
	* @throws PortalException if a doc details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.DocDetails deleteDocDetails(int docDetailsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _docDetailsLocalService.deleteDocDetails(docDetailsId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _docDetailsLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _docDetailsLocalService.dynamicQuery();
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
		return _docDetailsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _docDetailsLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _docDetailsLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _docDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
		return _docDetailsLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.stpl.app.model.DocDetails fetchDocDetails(int docDetailsId) {
		return _docDetailsLocalService.fetchDocDetails(docDetailsId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _docDetailsLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the doc details with the primary key.
	*
	* @param docDetailsId the primary key of the doc details
	* @return the doc details
	* @throws PortalException if a doc details with the primary key could not be found
	*/
	@Override
	public com.stpl.app.model.DocDetails getDocDetails(int docDetailsId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _docDetailsLocalService.getDocDetails(docDetailsId);
	}

	/**
	* Returns a range of all the doc detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of doc detailses
	* @param end the upper bound of the range of doc detailses (not inclusive)
	* @return the range of doc detailses
	*/
	@Override
	public java.util.List<com.stpl.app.model.DocDetails> getDocDetailses(
		int start, int end) {
		return _docDetailsLocalService.getDocDetailses(start, end);
	}

	/**
	* Returns the number of doc detailses.
	*
	* @return the number of doc detailses
	*/
	@Override
	public int getDocDetailsesCount() {
		return _docDetailsLocalService.getDocDetailsesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _docDetailsLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _docDetailsLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _docDetailsLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the doc details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param docDetails the doc details
	* @return the doc details that was updated
	*/
	@Override
	public com.stpl.app.model.DocDetails updateDocDetails(
		com.stpl.app.model.DocDetails docDetails) {
		return _docDetailsLocalService.updateDocDetails(docDetails);
	}

	@Override
	public DocDetailsLocalService getWrappedService() {
		return _docDetailsLocalService;
	}

	@Override
	public void setWrappedService(DocDetailsLocalService docDetailsLocalService) {
		_docDetailsLocalService = docDetailsLocalService;
	}

	private DocDetailsLocalService _docDetailsLocalService;
}