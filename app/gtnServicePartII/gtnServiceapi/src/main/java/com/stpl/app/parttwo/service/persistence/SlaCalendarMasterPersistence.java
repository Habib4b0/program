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

package com.stpl.app.parttwo.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.stpl.app.parttwo.exception.NoSuchSlaCalendarMasterException;
import com.stpl.app.parttwo.model.SlaCalendarMaster;

/**
 * The persistence interface for the sla calendar master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.SlaCalendarMasterPersistenceImpl
 * @see SlaCalendarMasterUtil
 * @generated
 */
@ProviderType
public interface SlaCalendarMasterPersistence extends BasePersistence<SlaCalendarMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SlaCalendarMasterUtil} to access the sla calendar master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the sla calendar master in the entity cache if it is enabled.
	*
	* @param slaCalendarMaster the sla calendar master
	*/
	public void cacheResult(SlaCalendarMaster slaCalendarMaster);

	/**
	* Caches the sla calendar masters in the entity cache if it is enabled.
	*
	* @param slaCalendarMasters the sla calendar masters
	*/
	public void cacheResult(
		java.util.List<SlaCalendarMaster> slaCalendarMasters);

	/**
	* Creates a new sla calendar master with the primary key. Does not add the sla calendar master to the database.
	*
	* @param slaCalendarMasterSid the primary key for the new sla calendar master
	* @return the new sla calendar master
	*/
	public SlaCalendarMaster create(int slaCalendarMasterSid);

	/**
	* Removes the sla calendar master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param slaCalendarMasterSid the primary key of the sla calendar master
	* @return the sla calendar master that was removed
	* @throws NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
	*/
	public SlaCalendarMaster remove(int slaCalendarMasterSid)
		throws NoSuchSlaCalendarMasterException;

	public SlaCalendarMaster updateImpl(SlaCalendarMaster slaCalendarMaster);

	/**
	* Returns the sla calendar master with the primary key or throws a {@link NoSuchSlaCalendarMasterException} if it could not be found.
	*
	* @param slaCalendarMasterSid the primary key of the sla calendar master
	* @return the sla calendar master
	* @throws NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
	*/
	public SlaCalendarMaster findByPrimaryKey(int slaCalendarMasterSid)
		throws NoSuchSlaCalendarMasterException;

	/**
	* Returns the sla calendar master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param slaCalendarMasterSid the primary key of the sla calendar master
	* @return the sla calendar master, or <code>null</code> if a sla calendar master with the primary key could not be found
	*/
	public SlaCalendarMaster fetchByPrimaryKey(int slaCalendarMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, SlaCalendarMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sla calendar masters.
	*
	* @return the sla calendar masters
	*/
	public java.util.List<SlaCalendarMaster> findAll();

	/**
	* Returns a range of all the sla calendar masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar masters
	* @param end the upper bound of the range of sla calendar masters (not inclusive)
	* @return the range of sla calendar masters
	*/
	public java.util.List<SlaCalendarMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the sla calendar masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar masters
	* @param end the upper bound of the range of sla calendar masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sla calendar masters
	*/
	public java.util.List<SlaCalendarMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SlaCalendarMaster> orderByComparator);

	/**
	* Returns an ordered range of all the sla calendar masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar masters
	* @param end the upper bound of the range of sla calendar masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sla calendar masters
	*/
	public java.util.List<SlaCalendarMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SlaCalendarMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the sla calendar masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sla calendar masters.
	*
	* @return the number of sla calendar masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}