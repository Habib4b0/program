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

import com.stpl.app.exception.NoSuchCfpContractDetailsException;
import com.stpl.app.model.CfpContractDetails;

/**
 * The persistence interface for the cfp contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see com.stpl.app.service.persistence.impl.CfpContractDetailsPersistenceImpl
 * @see CfpContractDetailsUtil
 * @generated
 */
@ProviderType
public interface CfpContractDetailsPersistence extends BasePersistence<CfpContractDetails> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CfpContractDetailsUtil} to access the cfp contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @return the matching cfp contract detailses
	*/
	public java.util.List<CfpContractDetails> findByCFPDetails(
		int companyMasterSid, int cfpContractSid);

	/**
	* Returns a range of all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @return the range of matching cfp contract detailses
	*/
	public java.util.List<CfpContractDetails> findByCFPDetails(
		int companyMasterSid, int cfpContractSid, int start, int end);

	/**
	* Returns an ordered range of all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cfp contract detailses
	*/
	public java.util.List<CfpContractDetails> findByCFPDetails(
		int companyMasterSid, int cfpContractSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CfpContractDetails> orderByComparator);

	/**
	* Returns an ordered range of all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching cfp contract detailses
	*/
	public java.util.List<CfpContractDetails> findByCFPDetails(
		int companyMasterSid, int cfpContractSid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CfpContractDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp contract details
	* @throws NoSuchCfpContractDetailsException if a matching cfp contract details could not be found
	*/
	public CfpContractDetails findByCFPDetails_First(int companyMasterSid,
		int cfpContractSid,
		com.liferay.portal.kernel.util.OrderByComparator<CfpContractDetails> orderByComparator)
		throws NoSuchCfpContractDetailsException;

	/**
	* Returns the first cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cfp contract details, or <code>null</code> if a matching cfp contract details could not be found
	*/
	public CfpContractDetails fetchByCFPDetails_First(int companyMasterSid,
		int cfpContractSid,
		com.liferay.portal.kernel.util.OrderByComparator<CfpContractDetails> orderByComparator);

	/**
	* Returns the last cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp contract details
	* @throws NoSuchCfpContractDetailsException if a matching cfp contract details could not be found
	*/
	public CfpContractDetails findByCFPDetails_Last(int companyMasterSid,
		int cfpContractSid,
		com.liferay.portal.kernel.util.OrderByComparator<CfpContractDetails> orderByComparator)
		throws NoSuchCfpContractDetailsException;

	/**
	* Returns the last cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cfp contract details, or <code>null</code> if a matching cfp contract details could not be found
	*/
	public CfpContractDetails fetchByCFPDetails_Last(int companyMasterSid,
		int cfpContractSid,
		com.liferay.portal.kernel.util.OrderByComparator<CfpContractDetails> orderByComparator);

	/**
	* Returns the cfp contract detailses before and after the current cfp contract details in the ordered set where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param cfpContractDetailsSid the primary key of the current cfp contract details
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cfp contract details
	* @throws NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
	*/
	public CfpContractDetails[] findByCFPDetails_PrevAndNext(
		int cfpContractDetailsSid, int companyMasterSid, int cfpContractSid,
		com.liferay.portal.kernel.util.OrderByComparator<CfpContractDetails> orderByComparator)
		throws NoSuchCfpContractDetailsException;

	/**
	* Removes all the cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63; from the database.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	*/
	public void removeByCFPDetails(int companyMasterSid, int cfpContractSid);

	/**
	* Returns the number of cfp contract detailses where companyMasterSid = &#63; and cfpContractSid = &#63;.
	*
	* @param companyMasterSid the company master sid
	* @param cfpContractSid the cfp contract sid
	* @return the number of matching cfp contract detailses
	*/
	public int countByCFPDetails(int companyMasterSid, int cfpContractSid);

	/**
	* Caches the cfp contract details in the entity cache if it is enabled.
	*
	* @param cfpContractDetails the cfp contract details
	*/
	public void cacheResult(CfpContractDetails cfpContractDetails);

	/**
	* Caches the cfp contract detailses in the entity cache if it is enabled.
	*
	* @param cfpContractDetailses the cfp contract detailses
	*/
	public void cacheResult(
		java.util.List<CfpContractDetails> cfpContractDetailses);

	/**
	* Creates a new cfp contract details with the primary key. Does not add the cfp contract details to the database.
	*
	* @param cfpContractDetailsSid the primary key for the new cfp contract details
	* @return the new cfp contract details
	*/
	public CfpContractDetails create(int cfpContractDetailsSid);

	/**
	* Removes the cfp contract details with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param cfpContractDetailsSid the primary key of the cfp contract details
	* @return the cfp contract details that was removed
	* @throws NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
	*/
	public CfpContractDetails remove(int cfpContractDetailsSid)
		throws NoSuchCfpContractDetailsException;

	public CfpContractDetails updateImpl(CfpContractDetails cfpContractDetails);

	/**
	* Returns the cfp contract details with the primary key or throws a {@link NoSuchCfpContractDetailsException} if it could not be found.
	*
	* @param cfpContractDetailsSid the primary key of the cfp contract details
	* @return the cfp contract details
	* @throws NoSuchCfpContractDetailsException if a cfp contract details with the primary key could not be found
	*/
	public CfpContractDetails findByPrimaryKey(int cfpContractDetailsSid)
		throws NoSuchCfpContractDetailsException;

	/**
	* Returns the cfp contract details with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param cfpContractDetailsSid the primary key of the cfp contract details
	* @return the cfp contract details, or <code>null</code> if a cfp contract details with the primary key could not be found
	*/
	public CfpContractDetails fetchByPrimaryKey(int cfpContractDetailsSid);

	@Override
	public java.util.Map<java.io.Serializable, CfpContractDetails> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the cfp contract detailses.
	*
	* @return the cfp contract detailses
	*/
	public java.util.List<CfpContractDetails> findAll();

	/**
	* Returns a range of all the cfp contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @return the range of cfp contract detailses
	*/
	public java.util.List<CfpContractDetails> findAll(int start, int end);

	/**
	* Returns an ordered range of all the cfp contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cfp contract detailses
	*/
	public java.util.List<CfpContractDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CfpContractDetails> orderByComparator);

	/**
	* Returns an ordered range of all the cfp contract detailses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cfp contract detailses
	* @param end the upper bound of the range of cfp contract detailses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of cfp contract detailses
	*/
	public java.util.List<CfpContractDetails> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CfpContractDetails> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the cfp contract detailses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of cfp contract detailses.
	*
	* @return the number of cfp contract detailses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}