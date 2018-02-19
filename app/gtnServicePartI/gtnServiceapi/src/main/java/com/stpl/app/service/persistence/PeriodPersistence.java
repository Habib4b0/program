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

import com.stpl.app.exception.NoSuchPeriodException;
import com.stpl.app.model.Period;

/**
 * The persistence interface for the period service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.PeriodPersistenceImpl
 * @see PeriodUtil
 * @generated
 */
@ProviderType
public interface PeriodPersistence extends BasePersistence<Period> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PeriodUtil} to access the period persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the period in the entity cache if it is enabled.
	*
	* @param period the period
	*/
	public void cacheResult(Period period);

	/**
	* Caches the periods in the entity cache if it is enabled.
	*
	* @param periods the periods
	*/
	public void cacheResult(java.util.List<Period> periods);

	/**
	* Creates a new period with the primary key. Does not add the period to the database.
	*
	* @param periodSid the primary key for the new period
	* @return the new period
	*/
	public Period create(int periodSid);

	/**
	* Removes the period with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param periodSid the primary key of the period
	* @return the period that was removed
	* @throws NoSuchPeriodException if a period with the primary key could not be found
	*/
	public Period remove(int periodSid) throws NoSuchPeriodException;

	public Period updateImpl(Period period);

	/**
	* Returns the period with the primary key or throws a {@link NoSuchPeriodException} if it could not be found.
	*
	* @param periodSid the primary key of the period
	* @return the period
	* @throws NoSuchPeriodException if a period with the primary key could not be found
	*/
	public Period findByPrimaryKey(int periodSid) throws NoSuchPeriodException;

	/**
	* Returns the period with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param periodSid the primary key of the period
	* @return the period, or <code>null</code> if a period with the primary key could not be found
	*/
	public Period fetchByPrimaryKey(int periodSid);

	@Override
	public java.util.Map<java.io.Serializable, Period> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the periods.
	*
	* @return the periods
	*/
	public java.util.List<Period> findAll();

	/**
	* Returns a range of all the periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @return the range of periods
	*/
	public java.util.List<Period> findAll(int start, int end);

	/**
	* Returns an ordered range of all the periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of periods
	*/
	public java.util.List<Period> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Period> orderByComparator);

	/**
	* Returns an ordered range of all the periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of periods
	*/
	public java.util.List<Period> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Period> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the periods from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of periods.
	*
	* @return the number of periods
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}