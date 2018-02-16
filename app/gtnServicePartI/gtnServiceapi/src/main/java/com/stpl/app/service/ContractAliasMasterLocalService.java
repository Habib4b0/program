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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.stpl.app.model.ContractAliasMaster;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ContractAliasMaster. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author
 * @see ContractAliasMasterLocalServiceUtil
 * @see com.stpl.app.service.base.ContractAliasMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ContractAliasMasterLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ContractAliasMasterLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContractAliasMasterLocalServiceUtil} to access the contract alias master local service. Add custom service methods to {@link com.stpl.app.service.impl.ContractAliasMasterLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the contract alias master to the database. Also notifies the appropriate model listeners.
	*
	* @param contractAliasMaster the contract alias master
	* @return the contract alias master that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ContractAliasMaster addContractAliasMaster(
		ContractAliasMaster contractAliasMaster);

	/**
	* Creates a new contract alias master with the primary key. Does not add the contract alias master to the database.
	*
	* @param contractAliasMasterSid the primary key for the new contract alias master
	* @return the new contract alias master
	*/
	public ContractAliasMaster createContractAliasMaster(
		int contractAliasMasterSid);

	/**
	* Deletes the contract alias master from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAliasMaster the contract alias master
	* @return the contract alias master that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ContractAliasMaster deleteContractAliasMaster(
		ContractAliasMaster contractAliasMaster);

	/**
	* Deletes the contract alias master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAliasMasterSid the primary key of the contract alias master
	* @return the contract alias master that was removed
	* @throws PortalException if a contract alias master with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ContractAliasMaster deleteContractAliasMaster(
		int contractAliasMasterSid) throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ContractAliasMaster fetchContractAliasMaster(
		int contractAliasMasterSid);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the contract alias master with the primary key.
	*
	* @param contractAliasMasterSid the primary key of the contract alias master
	* @return the contract alias master
	* @throws PortalException if a contract alias master with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ContractAliasMaster getContractAliasMaster(
		int contractAliasMasterSid) throws PortalException;

	/**
	* Returns a range of all the contract alias masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract alias masters
	* @param end the upper bound of the range of contract alias masters (not inclusive)
	* @return the range of contract alias masters
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ContractAliasMaster> getContractAliasMasters(int start, int end);

	/**
	* Returns the number of contract alias masters.
	*
	* @return the number of contract alias masters
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getContractAliasMastersCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Updates the contract alias master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contractAliasMaster the contract alias master
	* @return the contract alias master that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ContractAliasMaster updateContractAliasMaster(
		ContractAliasMaster contractAliasMaster);
}