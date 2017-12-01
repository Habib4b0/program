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

import com.stpl.app.exception.NoSuchCfpDetailsException;
import com.stpl.app.model.CfpDetails;

/**
 * The persistence interface for the cfp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CfpDetailsPersistenceImpl
 * @see CfpDetailsUtil
 * @generated
 */
@ProviderType
public interface CfpDetailsPersistence extends BasePersistence<CfpDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CfpDetailsUtil} to access the cfp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the cfp detailses where cfpModelSid = &#63;.
	*
	* @param cfpModelSid the cfp model sid
	* @return the matching cfp detailses
	*/
	public java.util.List<CfpDetails> findByCfpModelSid(int cfpModelSid);

	/**
	* Returns a range of all the cfp detailses where cfpModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpModelSid the cfp model sid
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @return the range of matching cfp detailses
	*/
	public java.util.List<CfpDetails> findByCfpModelSid(int cfpModelSid,
		int start, int end);

	/**
	* Returns an ordered range of all the cfp detailses where cfpModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpModelSid the cfp model sid
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cfp detailses
	*/
	public java.util.List<CfpDetails> findByCfpModelSid(int cfpModelSid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CfpDetails> orderByComparator);

	/**
	* Returns an ordered range of all the cfp detailses where cfpModelSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param cfpModelSid the cfp model sid
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cfp detailses
	*/
	public java.util.List<CfpDetails> findByCfpModelSid(int cfpModelSid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CfpDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cfp details in the ordered set where cfpModelSid = &#63;.
	*
	* @param cfpModelSid the cfp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp details
	* @throws NoSuchCfpDetailsException if a matching cfp details could not be found
	*/
	public CfpDetails findByCfpModelSid_First(int cfpModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<CfpDetails> orderByComparator)
		throws NoSuchCfpDetailsException;

	/**
	* Returns the first cfp details in the ordered set where cfpModelSid = &#63;.
	*
	* @param cfpModelSid the cfp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp details, or <code>null</code> if a matching cfp details could not be found
	*/
	public CfpDetails fetchByCfpModelSid_First(int cfpModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<CfpDetails> orderByComparator);

	/**
	* Returns the last cfp details in the ordered set where cfpModelSid = &#63;.
	*
	* @param cfpModelSid the cfp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp details
	* @throws NoSuchCfpDetailsException if a matching cfp details could not be found
	*/
	public CfpDetails findByCfpModelSid_Last(int cfpModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<CfpDetails> orderByComparator)
		throws NoSuchCfpDetailsException;

	/**
	* Returns the last cfp details in the ordered set where cfpModelSid = &#63;.
	*
	* @param cfpModelSid the cfp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp details, or <code>null</code> if a matching cfp details could not be found
	*/
	public CfpDetails fetchByCfpModelSid_Last(int cfpModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<CfpDetails> orderByComparator);

	/**
	* Returns the cfp detailses before and after the current cfp details in the ordered set where cfpModelSid = &#63;.
	*
	* @param cfpDetailsSid the primary key of the current cfp details
	* @param cfpModelSid the cfp model sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cfp details
	* @throws NoSuchCfpDetailsException if a cfp details with the primary key could not be found
	*/
	public CfpDetails[] findByCfpModelSid_PrevAndNext(int cfpDetailsSid,
		int cfpModelSid,
		com.liferay.portal.kernel.util.OrderByComparator<CfpDetails> orderByComparator)
		throws NoSuchCfpDetailsException;

	/**
	* Removes all the cfp detailses where cfpModelSid = &#63; from the database.
	*
	* @param cfpModelSid the cfp model sid
	*/
	public void removeByCfpModelSid(int cfpModelSid);

	/**
	* Returns the number of cfp detailses where cfpModelSid = &#63;.
	*
	* @param cfpModelSid the cfp model sid
	* @return the number of matching cfp detailses
	*/
	public int countByCfpModelSid(int cfpModelSid);

	/**
	* Caches the cfp details in the entity cache if it is enabled.
	*
	* @param cfpDetails the cfp details
	*/
	public void cacheResult(CfpDetails cfpDetails);

	/**
	* Caches the cfp detailses in the entity cache if it is enabled.
	*
	* @param cfpDetailses the cfp detailses
	*/
	public void cacheResult(java.util.List<CfpDetails> cfpDetailses);

	/**
	* Creates a new cfp details with the primary key. Does not add the cfp details to the database.
	*
	* @param cfpDetailsSid the primary key for the new cfp details
	* @return the new cfp details
	*/
	public CfpDetails create(int cfpDetailsSid);

	/**
	* Removes the cfp details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpDetailsSid the primary key of the cfp details
	* @return the cfp details that was removed
	* @throws NoSuchCfpDetailsException if a cfp details with the primary key could not be found
	*/
	public CfpDetails remove(int cfpDetailsSid)
		throws NoSuchCfpDetailsException;

	public CfpDetails updateImpl(CfpDetails cfpDetails);

	/**
	* Returns the cfp details with the primary key or throws a {@link NoSuchCfpDetailsException} if it could not be found.
	*
	* @param cfpDetailsSid the primary key of the cfp details
	* @return the cfp details
	* @throws NoSuchCfpDetailsException if a cfp details with the primary key could not be found
	*/
	public CfpDetails findByPrimaryKey(int cfpDetailsSid)
		throws NoSuchCfpDetailsException;

	/**
	* Returns the cfp details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cfpDetailsSid the primary key of the cfp details
	* @return the cfp details, or <code>null</code> if a cfp details with the primary key could not be found
	*/
	public CfpDetails fetchByPrimaryKey(int cfpDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, CfpDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cfp detailses.
	*
	* @return the cfp detailses
	*/
	public java.util.List<CfpDetails> findAll();

	/**
	* Returns a range of all the cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @return the range of cfp detailses
	*/
	public java.util.List<CfpDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cfp detailses
	*/
	public java.util.List<CfpDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CfpDetails> orderByComparator);

	/**
	* Returns an ordered range of all the cfp detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp detailses
	* @param end the upper bound of the range of cfp detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cfp detailses
	*/
	public java.util.List<CfpDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CfpDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cfp detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cfp detailses.
	*
	* @return the number of cfp detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}