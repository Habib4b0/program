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

import com.stpl.app.parttwo.exception.NoSuchSlaCalendarDetailsException;
import com.stpl.app.parttwo.model.SlaCalendarDetails;

/**
 * The persistence interface for the sla calendar details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.parttwo.service.persistence.impl.SlaCalendarDetailsPersistenceImpl
 * @see SlaCalendarDetailsUtil
 * @generated
 */
@ProviderType
public interface SlaCalendarDetailsPersistence extends BasePersistence<SlaCalendarDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SlaCalendarDetailsUtil} to access the sla calendar details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the sla calendar details in the entity cache if it is enabled.
	*
	* @param slaCalendarDetails the sla calendar details
	*/
	public void cacheResult(SlaCalendarDetails slaCalendarDetails);

	/**
	* Caches the sla calendar detailses in the entity cache if it is enabled.
	*
	* @param slaCalendarDetailses the sla calendar detailses
	*/
	public void cacheResult(
		java.util.List<SlaCalendarDetails> slaCalendarDetailses);

	/**
	* Creates a new sla calendar details with the primary key. Does not add the sla calendar details to the database.
	*
	* @param slaCalendarDetailsSid the primary key for the new sla calendar details
	* @return the new sla calendar details
	*/
	public SlaCalendarDetails create(int slaCalendarDetailsSid);

	/**
	* Removes the sla calendar details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param slaCalendarDetailsSid the primary key of the sla calendar details
	* @return the sla calendar details that was removed
	* @throws NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
	*/
	public SlaCalendarDetails remove(int slaCalendarDetailsSid)
		throws NoSuchSlaCalendarDetailsException;

	public SlaCalendarDetails updateImpl(SlaCalendarDetails slaCalendarDetails);

	/**
	* Returns the sla calendar details with the primary key or throws a {@link NoSuchSlaCalendarDetailsException} if it could not be found.
	*
	* @param slaCalendarDetailsSid the primary key of the sla calendar details
	* @return the sla calendar details
	* @throws NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
	*/
	public SlaCalendarDetails findByPrimaryKey(int slaCalendarDetailsSid)
		throws NoSuchSlaCalendarDetailsException;

	/**
	* Returns the sla calendar details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param slaCalendarDetailsSid the primary key of the sla calendar details
	* @return the sla calendar details, or <code>null</code> if a sla calendar details with the primary key could not be found
	*/
	public SlaCalendarDetails fetchByPrimaryKey(int slaCalendarDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, SlaCalendarDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the sla calendar detailses.
	*
	* @return the sla calendar detailses
	*/
	public java.util.List<SlaCalendarDetails> findAll();

	/**
	* Returns a range of all the sla calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar detailses
	* @param end the upper bound of the range of sla calendar detailses (not inclusive)
	* @return the range of sla calendar detailses
	*/
	public java.util.List<SlaCalendarDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the sla calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar detailses
	* @param end the upper bound of the range of sla calendar detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sla calendar detailses
	*/
	public java.util.List<SlaCalendarDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SlaCalendarDetails> orderByComparator);

	/**
	* Returns an ordered range of all the sla calendar detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sla calendar detailses
	* @param end the upper bound of the range of sla calendar detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sla calendar detailses
	*/
	public java.util.List<SlaCalendarDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SlaCalendarDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the sla calendar detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sla calendar detailses.
	*
	* @return the number of sla calendar detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}