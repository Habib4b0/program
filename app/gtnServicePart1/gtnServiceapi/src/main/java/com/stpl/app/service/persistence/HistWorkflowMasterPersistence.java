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

package com.stpl.app.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.exception.NoSuchHistWorkflowMasterException;
import com.stpl.app.model.HistWorkflowMaster;

/**
 * The persistence interface for the hist workflow master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.HistWorkflowMasterPersistenceImpl
 * @see HistWorkflowMasterUtil
 * @generated
 */
@ProviderType
public interface HistWorkflowMasterPersistence extends BasePersistence<HistWorkflowMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HistWorkflowMasterUtil} to access the hist workflow master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the hist workflow master in the entity cache if it is enabled.
	*
	* @param histWorkflowMaster the hist workflow master
	*/
	public void cacheResult(HistWorkflowMaster histWorkflowMaster);

	/**
	* Caches the hist workflow masters in the entity cache if it is enabled.
	*
	* @param histWorkflowMasters the hist workflow masters
	*/
	public void cacheResult(
		java.util.List<HistWorkflowMaster> histWorkflowMasters);

	/**
	* Creates a new hist workflow master with the primary key. Does not add the hist workflow master to the database.
	*
	* @param workflowMasterSid the primary key for the new hist workflow master
	* @return the new hist workflow master
	*/
	public HistWorkflowMaster create(int workflowMasterSid);

	/**
	* Removes the hist workflow master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workflowMasterSid the primary key of the hist workflow master
	* @return the hist workflow master that was removed
	* @throws NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
	*/
	public HistWorkflowMaster remove(int workflowMasterSid)
		throws NoSuchHistWorkflowMasterException;

	public HistWorkflowMaster updateImpl(HistWorkflowMaster histWorkflowMaster);

	/**
	* Returns the hist workflow master with the primary key or throws a {@link NoSuchHistWorkflowMasterException} if it could not be found.
	*
	* @param workflowMasterSid the primary key of the hist workflow master
	* @return the hist workflow master
	* @throws NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
	*/
	public HistWorkflowMaster findByPrimaryKey(int workflowMasterSid)
		throws NoSuchHistWorkflowMasterException;

	/**
	* Returns the hist workflow master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param workflowMasterSid the primary key of the hist workflow master
	* @return the hist workflow master, or <code>null</code> if a hist workflow master with the primary key could not be found
	*/
	public HistWorkflowMaster fetchByPrimaryKey(int workflowMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, HistWorkflowMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the hist workflow masters.
	*
	* @return the hist workflow masters
	*/
	public java.util.List<HistWorkflowMaster> findAll();

	/**
	* Returns a range of all the hist workflow masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist workflow masters
	* @param end the upper bound of the range of hist workflow masters (not inclusive)
	* @return the range of hist workflow masters
	*/
	public java.util.List<HistWorkflowMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the hist workflow masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist workflow masters
	* @param end the upper bound of the range of hist workflow masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of hist workflow masters
	*/
	public java.util.List<HistWorkflowMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistWorkflowMaster> orderByComparator);

	/**
	* Returns an ordered range of all the hist workflow masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of hist workflow masters
	* @param end the upper bound of the range of hist workflow masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of hist workflow masters
	*/
	public java.util.List<HistWorkflowMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistWorkflowMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the hist workflow masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of hist workflow masters.
	*
	* @return the number of hist workflow masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}