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

import com.stpl.app.exception.NoSuchDeductionCalendarMasterException;
import com.stpl.app.model.DeductionCalendarMaster;

/**
 * The persistence interface for the deduction calendar master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.DeductionCalendarMasterPersistenceImpl
 * @see DeductionCalendarMasterUtil
 * @generated
 */
@ProviderType
public interface DeductionCalendarMasterPersistence extends BasePersistence<DeductionCalendarMaster> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DeductionCalendarMasterUtil} to access the deduction calendar master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the deduction calendar master in the entity cache if it is enabled.
	*
	* @param deductionCalendarMaster the deduction calendar master
	*/
	public void cacheResult(DeductionCalendarMaster deductionCalendarMaster);

	/**
	* Caches the deduction calendar masters in the entity cache if it is enabled.
	*
	* @param deductionCalendarMasters the deduction calendar masters
	*/
	public void cacheResult(
		java.util.List<DeductionCalendarMaster> deductionCalendarMasters);

	/**
	* Creates a new deduction calendar master with the primary key. Does not add the deduction calendar master to the database.
	*
	* @param deductionCalendarMasterSid the primary key for the new deduction calendar master
	* @return the new deduction calendar master
	*/
	public DeductionCalendarMaster create(int deductionCalendarMasterSid);

	/**
	* Removes the deduction calendar master with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deductionCalendarMasterSid the primary key of the deduction calendar master
	* @return the deduction calendar master that was removed
	* @throws NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
	*/
	public DeductionCalendarMaster remove(int deductionCalendarMasterSid)
		throws NoSuchDeductionCalendarMasterException;

	public DeductionCalendarMaster updateImpl(
		DeductionCalendarMaster deductionCalendarMaster);

	/**
	* Returns the deduction calendar master with the primary key or throws a {@link NoSuchDeductionCalendarMasterException} if it could not be found.
	*
	* @param deductionCalendarMasterSid the primary key of the deduction calendar master
	* @return the deduction calendar master
	* @throws NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
	*/
	public DeductionCalendarMaster findByPrimaryKey(
		int deductionCalendarMasterSid)
		throws NoSuchDeductionCalendarMasterException;

	/**
	* Returns the deduction calendar master with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param deductionCalendarMasterSid the primary key of the deduction calendar master
	* @return the deduction calendar master, or <code>null</code> if a deduction calendar master with the primary key could not be found
	*/
	public DeductionCalendarMaster fetchByPrimaryKey(
		int deductionCalendarMasterSid);

	@Override
	public java.util.Map<java.io.Serializable, DeductionCalendarMaster> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the deduction calendar masters.
	*
	* @return the deduction calendar masters
	*/
	public java.util.List<DeductionCalendarMaster> findAll();

	/**
	* Returns a range of all the deduction calendar masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction calendar masters
	* @param end the upper bound of the range of deduction calendar masters (not inclusive)
	* @return the range of deduction calendar masters
	*/
	public java.util.List<DeductionCalendarMaster> findAll(int start, int end);

	/**
	* Returns an ordered range of all the deduction calendar masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction calendar masters
	* @param end the upper bound of the range of deduction calendar masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of deduction calendar masters
	*/
	public java.util.List<DeductionCalendarMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeductionCalendarMaster> orderByComparator);

	/**
	* Returns an ordered range of all the deduction calendar masters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deduction calendar masters
	* @param end the upper bound of the range of deduction calendar masters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of deduction calendar masters
	*/
	public java.util.List<DeductionCalendarMaster> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DeductionCalendarMaster> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the deduction calendar masters from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of deduction calendar masters.
	*
	* @return the number of deduction calendar masters
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}